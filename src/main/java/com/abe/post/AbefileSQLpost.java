package com.abe.post;

import com.abe.bean.abefile;
import com.abe.mapper.AbefileMapper;
import com.abe.service.AbefileService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
@Slf4j
public class AbefileSQLpost {
    @Autowired
    private AbefileMapper abefileMapper;
    @Autowired
    private AbefileService abefileService;

    public static AbefileSQLpost sqLpost;
    @PostConstruct
    public void  init(){
        sqLpost=this;
        sqLpost.abefileMapper=this.abefileMapper;
        sqLpost.abefileService=this.abefileService;
    }
    public String addfile(Integer id,String file,String enfile,String policy){
        abefile abefile = new abefile();
        abefile.setUserId(id);
        abefile.setFileAddress(file);
        abefile.setEncryptedfileAddress(enfile);
        abefile.setPolicyAddress(policy);
        int insert = sqLpost.abefileMapper.insert(abefile);
        return "加密成功,id:"+abefile.getId();
    }
    public abefile selectByFileId(Integer id) {
        abefile temp = sqLpost.abefileMapper.selectById(id);
        return temp;
    }

    public abefile selectFileId(Integer id){
        System.out.println(id);
        return sqLpost.abefileMapper.selectById(id);
    }
}
