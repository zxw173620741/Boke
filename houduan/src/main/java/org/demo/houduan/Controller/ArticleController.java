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
     * 请求地址: GET /api/article/list?userId=1
     * @param userId (可选) 当前登录用户的ID，用于判断是否点赞过
     */
    @GetMapping("/list")
    public Result<List<ArticleVO>> list(@RequestParam(required = false) Long userId) {
        // 1. 调用 Service 获取数据，传入 userId 让他去计算 isLiked 字段
        List<ArticleVO> list = articleService.listAll(userId);

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
    /**
     * ⭐ 新增：点赞/取消点赞接口
     * 请求地址: POST /api/article/like/{articleId}?userId=1
     */
    @PostMapping("/like/{articleId}")
    public Result like(@PathVariable Long articleId, @RequestParam Long userId) {
        if (userId == null) {
            return Result.error("请先登录才能点赞");
        }

        // 调用 Service 处理点赞逻辑
        articleService.likeArticle(articleId, userId);

        return Result.success("操作成功");
    }
}