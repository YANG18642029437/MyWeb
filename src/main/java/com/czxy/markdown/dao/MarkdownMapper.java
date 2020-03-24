package com.czxy.markdown.dao;

import com.czxy.markdown.domain.Markdown;
import com.czxy.markdown.domain.MarkdownVo;
import org.apache.ibatis.annotations.Select;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface MarkdownMapper extends Mapper<Markdown> {

    @Select("select * from markdown where uid = #{uid}")
    List<Markdown> selectByUid(MarkdownVo vo);

    @Select("select * from markdown where uid != #{uid} and share = '1'")
    List<Markdown> selectByShare(MarkdownVo vo);
}
