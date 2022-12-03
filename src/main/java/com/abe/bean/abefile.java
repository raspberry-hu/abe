package com.abe.bean;

import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import static com.baomidou.mybatisplus.annotation.IdType.AUTO;

@Data
public class abefile {
    @TableId(type = AUTO)
    private Integer id;
    private Integer user_id;
    private String file_address;
    private String encryptedfile_address;
    private String policy_address;
}
