package com.abe.post;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.abe.bean.abeuser;
import com.abe.mapper.AbeuserMapper;
import com.abe.service.AbeuserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
@Slf4j
public class AbeuserSQLpost {
    @Autowired
    private AbeuserMapper abeuserMapper;
    @Autowired
    private AbeuserService abeuserService;

    public static AbeuserSQLpost sqLpost;
    @PostConstruct
    public void  init(){
        sqLpost=this;
        sqLpost.abeuserMapper=this.abeuserMapper;
        sqLpost.abeuserService=this.abeuserService;
    }
    public String abeuserInitKey(Integer userID,String publickey, String masterkey){
        QueryWrapper<abeuser> queryWrapper = new QueryWrapper<abeuser>();
        queryWrapper.eq("user_id",userID).last("LIMIT 1");
        try {

            if (sqLpost.abeuserMapper.selectOne(queryWrapper) != null) {
                return "已进行了初始化操作，不可再次初始化！\n";

            } else {
                abeuser abeuser = new abeuser();
                abeuser.setUser_id(userID);
                abeuser.setMasterkey(masterkey);
                abeuser.setPublickey(publickey);
                sqLpost.abeuserMapper.insert(abeuser);
            }
        return "生成密钥成功";
        } finally {

        }
    }
    public abeuser getKey(Integer userID){
        QueryWrapper<abeuser> queryWrapper = new QueryWrapper<abeuser>();
        queryWrapper.eq("user",userID).last("LIMIT 1");
        try {
            abeuser abeuser = sqLpost.abeuserMapper.selectOne(queryWrapper);
            return abeuser;
        } finally {

        }
    }
}
