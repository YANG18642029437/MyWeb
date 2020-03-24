package com.czxy.markdown.controller;

import com.czxy.markdown.domain.Markdown;
import com.czxy.markdown.domain.MarkdownVo;
import com.czxy.markdown.service.MarkdownService;
import com.czxy.user.domain.User;
import com.czxy.vo.BaseResult;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("markdown")
public class MarkdownController {

    @Resource
    private MarkdownService markdownService;

    @Resource
    private ThreadLocal<User> threadLocal;

    /**
     * 查询所有的 文档
     * @return
     */
    @PostMapping("findAll")
    public BaseResult findAll(@RequestBody MarkdownVo vo){
        User user = threadLocal.get();
        System.out.println(vo +"" + user);
        vo.setUid(user.getUid());
        List<Markdown> list =  markdownService.findAllByUid(vo);
//        System.out.println(list);
        return BaseResult.ok("查询成功",list);
    }

    @PostMapping("findAllByPage")
    public BaseResult findAllByPage(@RequestBody MarkdownVo vo){
//        threadLocal.get()
        return BaseResult.ok("成功");
    }


    /**
     * 添加文档
     * @param markdown
     * @return
     */
    @PostMapping("save")
    public BaseResult save(@RequestBody Markdown markdown){
        User user = threadLocal.get();
        Markdown markdown1 = markdownService.save(user,markdown);
        return BaseResult.ok("添加成功",markdown1);
    }

    /**
     *
     * @return
     */
    @PostMapping("update")
    public BaseResult update(@RequestBody Markdown markdown){
        markdownService.update(markdown);
        return BaseResult.ok("修改成功");
    }

    @DeleteMapping("delete/{mid}")
    public BaseResult delete(@PathVariable("mid")String mid){
        markdownService.delete(mid);
        return BaseResult.ok("删除成功");
    }

    @GetMapping("findById/{mid}")
    public BaseResult findById(@PathVariable("mid")String mid){
        Markdown markdown = markdownService.findById(mid);
        if (markdown != null) {
            return BaseResult.ok("查询成功",markdown);
        }
        return BaseResult.error("数据丢失");
    }



}
