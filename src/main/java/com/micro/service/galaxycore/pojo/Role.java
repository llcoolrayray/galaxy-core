package com.micro.service.galaxycore.pojo;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * @author Wang.Rui.Barney
 * @date 2024/01/23 10:50
 * @description
 */
@Getter
@Setter
public class Role implements Serializable {
    private long id;
    private String name;
}
