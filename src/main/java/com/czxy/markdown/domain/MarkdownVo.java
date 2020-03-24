package com.czxy.markdown.domain;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class MarkdownVo {
    private Integer pageNum;
    private Integer pageSize;
    /** 1,表示是查询自己，2，表示查询其他人分享 */
    private Integer isSelf;
    private String uid;
}
