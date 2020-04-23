package com.exampleofspringboot.demo.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @ClassName user
 * @Description TODO
 * @Author Y
 * @Date 2019-07-2820:43
 * @Version 1.0
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User implements Serializable {

    private String username ;

    private String EnglishName;

    private String sex;
}
