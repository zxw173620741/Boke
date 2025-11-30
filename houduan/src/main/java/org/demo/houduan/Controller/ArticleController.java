package org.demo.houduan.Controller;

import org.demo.houduan.Entity.Article;
import org.demo.houduan.Service.ArticleService;
import org.demo.houduan.common.Result;
import org.demo.houduan.Vo.ArticleVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/article") // 定义统一前缀
public class ArticleController {

    @Autowired
    private ArticleService articleService;

    /**
     * 获取文章列表接口
     * 请求地址: GET /api/article/list
     */
    @GetMapping("/list")
    public Result<List<ArticleVO>> list() {
        // 1. 调用 Service 获取数据
        List<ArticleVO> list = articleService.listAll();

        return Result.success(list);
    }
    @PostMapping("/add")
    public Result publish(@RequestBody Article article) {
        // 简单校验
        if (article.getContent() == null || article.getContent().trim().isEmpty()) {
            return Result.error("内容不能为空");
        }
        if (article.getUserId() == null) {
            return Result.error("未获取Ts用户ID");
        }

        articleService.publish(article);
        return Result.success("发布成功");
    }
}