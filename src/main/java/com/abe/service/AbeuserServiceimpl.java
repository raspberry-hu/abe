package com.abe.service;

import com.abe.mapper.AbeuserMapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.abe.bean.abeuser;
import org.springframework.stereotype.Service;

@Service
public class AbeuserServiceimpl extends ServiceImpl<AbeuserMapper, abeuser> implements AbeuserService{
}
