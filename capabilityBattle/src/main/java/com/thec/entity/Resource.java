package com.thec.entity;

import lombok.Data;
import lombok.ToString;

/**
 * @author thec
 */
@Data
@ToString
public class Resource {
    private Integer id;
    private Integer uid;
    private String component;
    private String path;
    private String title;
    private String name;
}
