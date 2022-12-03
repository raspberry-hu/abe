package com.abe.post;

import com.abe.bean.aberequest;
import com.abe.mapper.AberequestMapper;
import com.abe.service.AberequestService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.List;

@Component
@Slf4j
public class AberequestSQLpost {
    @Autowired
    private AberequestMapper aberequestMapper;
    @Autowired
    private AberequestService aberequestService;

    public static AberequestSQLpost sqLpost;
    @PostConstruct
    public void  init(){
        sqLpost=this;
        sqLpost.aberequestMapper=this.aberequestMapper;
        sqLpost.aberequestService=this.aberequestService;
    }
    public List<aberequest> providerSearch(Integer providerId){
        QueryWrapper<aberequest> queryWrapper = new QueryWrapper<aberequest>();
        queryWrapper.eq("provider_id",providerId);
        queryWrapper.select("id","provider_id","requester_id","file_id","attribute","privatekey");
        try{
            List<aberequest> aberequests = sqLpost.aberequestMapper.selectList(queryWrapper);

            return aberequests;
        } finally {

        }
    }
    public List<aberequest> requestSearch(Integer requestId){
        QueryWrapper<aberequest> queryWrapper = new QueryWrapper<aberequest>();
        queryWrapper.eq("requester_id",requestId);
        queryWrapper.select("id","provider_id","requester_id","file_id","attribute","privatekey");
        try{
            List<aberequest> aberequests = sqLpost.aberequestMapper.selectList(queryWrapper);
            return aberequests;
        } finally {

        }
    }
    public String addRequest(Integer id, Integer fileId, Integer providerId, String policy){
        aberequest aberequest = new aberequest();
        aberequest.setFileId(fileId);
        aberequest.setRequesterId(id);
        aberequest.setProviderId(providerId);
        aberequest.setAttribute(policy);
        sqLpost.aberequestMapper.insert(aberequest);
        return "插入成功";
    }
}
