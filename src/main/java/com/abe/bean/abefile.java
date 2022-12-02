package com.abe.bean;

import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import static com.baomidou.mybatisplus.annotation.IdType.AUTO;

@Data
public class abefile {
    @TableId(type = AUTO)
    private Long id;
    private Long userid;
    private String fileid;
    private String file;
    private byte status;
    private String policy;
}
