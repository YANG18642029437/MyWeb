package com.czxy.markdown.domain;

import lombok.Data;
import lombok.ToString;

import javax.persistence.Id;

@Data
@ToString
public class Markdown {
    @Id
    private String mid;
    private String title;
    private String content_md;
    private Integer read_num;
    private Integer red_num;
    private String uid;
    private String share;
    private String subhead;
}
