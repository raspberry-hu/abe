package com.abe.service;

import com.abe.mapper.AbefileMapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.abe.bean.abefile;
import org.springframework.stereotype.Service;

@Service
public class AbefileServiceimpl  extends ServiceImpl<AbefileMapper, abefile> implements AbefileService{
}
