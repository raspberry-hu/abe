package com.abe.bean;

import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import static com.baomidou.mybatisplus.annotation.IdType.AUTO;

@Data
public class abeuser {
    @TableId(type = AUTO)
    private Long id;
    private int userid;
    private String masterkey;
    private String publickey;
}
