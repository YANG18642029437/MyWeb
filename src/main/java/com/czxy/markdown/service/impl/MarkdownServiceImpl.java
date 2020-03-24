package com.czxy.markdown.service.impl;

import com.czxy.markdown.dao.MarkdownMapper;
import com.czxy.markdown.domain.Markdown;
import com.czxy.markdown.domain.MarkdownVo;
import com.czxy.markdown.service.MarkdownService;
import com.czxy.user.domain.User;
import com.czxy.utils.StringUtils;
import com.github.pagehelper.PageHelper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Service
@Transactional
public class MarkdownServiceImpl implements MarkdownService {

    @Resource
    private MarkdownMapper markdownMapper;

    @Override
    public List<Markdown> findAllByUid(MarkdownVo vo) {
        PageHelper.startPage(vo.getPageNum(),vo.getPageSize());
        List<Markdown> list = null;
        if (vo.getIsSelf() == 1){
            list = markdownMapper.selectByUid(vo);
        }else {
            list = markdownMapper.selectByShare(vo);
        }
        return list;
    }

    @Override
    public Markdown save(User user, Markdown md) {
        md.setMid(StringUtils.UUID());
        md.setShare("1");
        md.setUid(user.getUid());
        md.setRead_num(0);
        md.setRed_num(0);
        markdownMapper.insertSelective(md);
        return md;
    }

    @Override
    public void update(Markdown markdown) {
        markdownMapper.updateByPrimaryKeySelective(markdown);
    }

    @Override
    public void delete(String mid) {
        markdownMapper.deleteByPrimaryKey(mid);
    }

    @Override
    public Markdown findById(String mid) {
        return markdownMapper.selectByPrimaryKey(mid);
    }
}
