package com.abe.bean;

import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import static com.baomidou.mybatisplus.annotation.IdType.AUTO;
@Data
public class aberequest {
    @TableId(type = AUTO)
    private Long id;
    private Long providerid;
    private Long requesterid;
    private Long fileid;
    private String privatekey;
}
