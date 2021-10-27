package com.lzq.demo.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BlogQuery {
    private String title;
    private Long typeId;
    private boolean recommend;

}
