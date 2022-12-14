package com.abe.controller;


import com.abe.bean.abefile;
import com.abe.bean.aberequest;
import com.abe.bean.abeuser;
import com.abe.post.AbefileSQLpost;
import com.abe.post.AberequestSQLpost;
import com.abe.post.AbeuserSQLpost;
import com.abe.tool.CommonResponse;
import com.abe.tool.Constant;
import com.abe.CPABE;
import com.baomidou.mybatisplus.extension.api.IErrorCode;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.json.JSONException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.security.GeneralSecurityException;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.UUID;


@RestController
public class ABEController {
    AbeuserSQLpost abeuserSQLpost=new AbeuserSQLpost();
    AbefileSQLpost abefileSQLpost=new AbefileSQLpost();
    AberequestSQLpost aberequestSQLpost= new AberequestSQLpost();
    private static String data;
    @PostMapping("/initkey")
    public CommonResponse initKey(@RequestParam("userid") Integer id){
        String securityLabel="128";
        String pairingParametersFileName =  "security_params/security_level_" + securityLabel + "bits.properties";
        String dirproperty = System.getProperty("user.dir");
        String filedata=dirproperty+ Constant.abeFile;
        File file = new File(filedata);
        if ( !file.exists())   file.mkdir();

        String File=filedata+"/"+id;
        file = new File(File);
        if ( !file.exists())   file.mkdir();
        String pkFileName=File+"/security_level_"+securityLabel +"publickey";
        String mskFileName=File+"/security_level_"+securityLabel +"masterkey";
        CPABE.setup(pairingParametersFileName, pkFileName, mskFileName);
        String initKey = abeuserSQLpost.abeuserInitKey(id, pkFileName, mskFileName);
        CommonResponse<Object> commonResponse = new CommonResponse<>(200, initKey);
        return commonResponse;
    }
    @PostMapping("/encryptfile")
    public CommonResponse encryptFile(@RequestParam("userid") Integer id,@RequestParam("file") MultipartFile file, @RequestParam("policyfile") MultipartFile policyFile) throws IOException, GeneralSecurityException, JSONException {
        abeuser abeuser = abeuserSQLpost.getKey(id);
        if (abeuser == null) {
            return new CommonResponse<>(300, "????????????");
        }
        try {
            String dirproperty = System.getProperty("user.dir");
            String fileName = dirproperty + Constant.abeFile + "/" + id + "/or_" + file.getOriginalFilename();
            String ctFileName = dirproperty + Constant.abeFile + "/" + id + "/ct_" + file.getOriginalFilename();
            String policyFileName = dirproperty + Constant.abeFile + "/" + id + "/policy_" + file.getOriginalFilename();
            saveFile(file, fileName);
            saveFile(policyFile, policyFileName);
            FileInputStream fileInputStream = (FileInputStream) file.getInputStream();
            byte[] b = new byte[(int) file.getSize()];  //?????????????????????????????????
            fileInputStream.read(b);//????????????????????????b??????
            data = new String(b, StandardCharsets.UTF_8); //????????????????????????UTF-8??????????????????
            String pkFileName = abeuser.getPublickey();
            CPABE.kemEncrypt(data, policyFileName, pkFileName, ctFileName);
            String addfile = abefileSQLpost.addfile(id, fileName, ctFileName, policyFileName);
            return new CommonResponse<>(200, addfile);
        } catch (IOException e) {
            return new CommonResponse<>(500, e);
        } catch (GeneralSecurityException e) {
            return new CommonResponse<>(500, e);
        } catch (JSONException e) {
            return new CommonResponse<>(500, e);
        }
    }
    @PostMapping("/providersearch")
    public CommonResponse providerSearch(@RequestParam("userid") Integer id) {
        abeuser abeuser = abeuserSQLpost.getKey(id);
        if (abeuser == null) {
            return new CommonResponse<>(300, "????????????");
        }
        List<aberequest> aberequests = aberequestSQLpost.providerSearch(id);
        return new CommonResponse<>(200, aberequests);

    }
    @PostMapping("/requestersearch")
    public CommonResponse requesterSearch(@RequestParam("userid") Integer id) {
        abeuser abeuser = abeuserSQLpost.getKey(id);
        if (abeuser == null) {
            return new CommonResponse<>(300, "????????????");
        }
        List<aberequest> aberequests = aberequestSQLpost.requestSearch(id);
        return new CommonResponse<>(200, aberequests);
    }

    @PostMapping("/request")
    public CommonResponse requestFile(@RequestParam("id") Integer id,
                                      @RequestParam("file_id") Integer fileId,
                                      @RequestParam("policy") String policy){
        // 1. ???????????????id???????????????????????????id
        abefile abefile = abefileSQLpost.selectFileId(fileId);
        if(abefile == null){
            return new CommonResponse<>(300, "???????????????");
        }
        /*
            2. ????????????
            id: ?????????id
            providerId: ???????????????id-????????????id??????
            fileId: ??????id
            policy: ??????
         */
        System.out.println(abefile);
        String response = null;
        try {
            response = aberequestSQLpost.addRequest(id, fileId, abefile.getUserId(), policy);
        } catch (Exception exception) {
            return new CommonResponse<>(300, "????????????");
        }
        return new CommonResponse<>(200, response);
    }

    public static void download(HttpServletResponse response, String address) throws IOException {
        byte[] buf = address.getBytes();
        response.setContentType("application/octet-stream;charset=utf-8");
        response.addHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode("plaintext", "UTF-8"));
        response.addHeader("Content-Length", "" + buf.length);
        ServletOutputStream outputStream = response.getOutputStream();
        outputStream.write(buf, 0, buf.length);
        outputStream.close();
    }

    @PostMapping("decryptFile")
    public CommonResponse decryptFile(HttpServletResponse response, @RequestParam("id") Integer id) throws GeneralSecurityException, IOException {
        aberequest aberequestTemp = aberequestSQLpost.getKey(id);
        String privatekey = aberequestTemp.getPrivatekey();
        Integer fileid =  aberequestTemp.getFileId();
        String address = abefileSQLpost.selectByFileId(fileid).getEncryptedfileAddress();
        try {
            String temp = CPABE.kemDecrypt(address, privatekey);
            download(response, temp);
            return new CommonResponse<>(200, temp);
        } catch (GeneralSecurityException e){
            return new CommonResponse<>(500, e);
        } catch (IOException e) {
            return new CommonResponse<>(500,e);
        }
    }

    @PostMapping("authorizeFile")
    public CommonResponse authorizeFile(@RequestParam("id")Integer id) throws NoSuchAlgorithmException {
        aberequest userAttList = aberequestSQLpost.getKey(id);
        Integer providerId = userAttList.getProviderId();
        String temp = userAttList.getAttribute();
        String masterKey = abeuserSQLpost.getKey(providerId).getMasterkey();
        String publicKey = abeuserSQLpost.getKey(providerId).getPublickey();
        String dirproperty = System.getProperty("user.dir");
        String fileName = dirproperty + Constant.abeFile + "/" + id + "privateKey";
        String[] arr = temp.split(",");
        try {
            CPABE.keygen(arr,publicKey,masterKey,fileName);
            userAttList.setPrivatekey(fileName);
            aberequestSQLpost.updateRequest(userAttList);
            return new CommonResponse(200,"????????????");
        }catch (NoSuchAlgorithmException e){
            return new CommonResponse(500,e);
        }
    }

    private static void saveFile(MultipartFile file,String filename){
        FileOutputStream fileOutputStream = null;
        InputStream inputStream = null;
        try {
            if ((!file.isEmpty())) {
                //??????

                fileOutputStream = new FileOutputStream(filename);
                inputStream = file.getInputStream();
                IOUtils.copy(inputStream, fileOutputStream);
                fileOutputStream.flush();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                if (inputStream != null) {
                    inputStream.close();
                }
                if (fileOutputStream != null) {
                    fileOutputStream.close();
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
