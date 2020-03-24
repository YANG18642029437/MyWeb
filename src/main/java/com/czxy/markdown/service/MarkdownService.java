package com.czxy.markdown.service;

import com.czxy.markdown.domain.Markdown;
import com.czxy.markdown.domain.MarkdownVo;
import com.czxy.user.domain.User;

import java.util.List;

public interface MarkdownService {
    List<Markdown> findAllByUid(MarkdownVo vo);

    Markdown save(User user, Markdown md);

    void update(Markdown markdown);

    void delete(String mid);

    Markdown findById(String mid);
}
