package com.abe.bean;

import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import org.springframework.data.relational.core.sql.In;

import static com.baomidou.mybatisplus.annotation.IdType.AUTO;
@Data
public class aberequest {
    @TableId(type = AUTO)
    private Integer id;
    private Integer providerId;
    private Integer requesterId;
    private Integer fileId;
    private String attribute;
    private String privatekey;
}
