package com.abe.bean;

import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import static com.baomidou.mybatisplus.annotation.IdType.AUTO;
@Data
public class aberequest {
    @TableId(type = AUTO)
    private Integer id;
    private Integer provider_id;
    private Integer requester_id;
    private Integer file_id;
    private String attribute;
    private String privatekey;
}
