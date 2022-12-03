package com.abe.controller;


import com.abe.bean.abefile;
import com.abe.bean.abeuser;
import com.abe.post.AbefileSQLpost;
import com.abe.post.AberequestSQLpost;
import com.abe.post.AbeuserSQLpost;
import com.abe.tool.CommonResponse;
import com.abe.tool.Constant;
import com.abe.CPABE;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.json.JSONException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.security.GeneralSecurityException;
import java.util.UUID;


@RestController
public class ABEController {
    AbeuserSQLpost abeuserSQLpost=new AbeuserSQLpost();
    AbefileSQLpost abefileSQLpost=new AbefileSQLpost();
    AberequestSQLpost aberequestSQLpost= new AberequestSQLpost();
    private static String data;

    @PostMapping("/request")
    public CommonResponse requestFile(@RequestParam("id") Integer id,
                                      @RequestParam("file_id") Integer fileId,
                                      @RequestParam("policy") String policy){
        // 1. 现根据文件id得到对应的授权用户id
        abefile abefile = abefileSQLpost.selectFileId(fileId);
        if(abefile == null){
            return new CommonResponse<>(300, "文件不存在");
        }
        /*
            2. 插入请求
            id: 请求者id
            providerId: 文件提供者id-通过文件id查询
            fileId: 文件id
            policy: 策略
         */
        String response = null;
        try {
            response = aberequestSQLpost.addRequest(id, fileId, abefile.getUserId(), policy);
        } catch (Exception exception) {
            return new CommonResponse<>(300, "请求失败");
        }
        return new CommonResponse<>(200, response);
    }
}
