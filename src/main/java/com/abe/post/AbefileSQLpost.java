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
    // public String addfile(Long id,String fileid,String file,String policy){
    //     abefile abefile = new abefile();
    //     abefile.setUserid(id);
    //     abefile.setFileid(fileid);
    //     abefile.setFile(file);
    //     abefile.setPolicy(policy);
    //     sqLpost.abefileMapper.insert(abefile);
    //     return "加密成功";
    // }
    // 根据文件id查询用户id
    public abefile selectFileId(Integer id){
        System.out.println(id);
        return sqLpost.abefileMapper.selectById(id);
    }
}
