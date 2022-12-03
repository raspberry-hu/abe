package com.abe.bean;

import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import static com.baomidou.mybatisplus.annotation.IdType.AUTO;

@Data
public class abeuser {
    @TableId(type = AUTO)
    private Integer id;
    private Integer user_id;
    private String masterkey;
    private String publickey;
}
