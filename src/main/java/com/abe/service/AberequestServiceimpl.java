package com.abe.service;

import com.abe.bean.aberequest;
import com.abe.mapper.AberequestMapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

@Service
public class AberequestServiceimpl extends ServiceImpl<AberequestMapper, aberequest> implements AberequestService{
}
