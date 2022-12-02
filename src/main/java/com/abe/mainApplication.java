package com.abe;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

//应用
@SpringBootApplication
//扫描包的范围为主函数同级及之下的子包内容
//可以通过其他方法或注解改变扫描范围
@MapperScan("com.abe.mapper")
public class mainApplication {
    public static void main(String[] args) {
        SpringApplication.run(mainApplication.class,args);
    }
}
