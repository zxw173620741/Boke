package org.demo.houduan.Service;

import org.demo.houduan.Entity.Article;
import org.demo.houduan.Entity.ArticleLike; // 引入点赞实体
import org.demo.houduan.Mapper.ArticleLikeMapper; // 引入点赞Mapper
import org.demo.houduan.Mapper.ArticleMapper;
import org.demo.houduan.Vo.ArticleVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional; // 引入事务注解

import java.util.List;

@Service
public class ArticleService {

    @Autowired
    private ArticleMapper articleMapper;

    @Autowired // ⭐ 注入点赞Mapper，否则会报错
    private ArticleLikeMapper articleLikeMapper;

    /**
     * 获取列表 (传入当前用户ID，用于判断是否点赞)
     */
    public List<ArticleVO> listAll(Long currentUserId) {
        return articleMapper.selectAllArticles(currentUserId);
    }

    /**
     * 发布文章
     */
    public void publish(Article article) {
        // 这里可以设置一些默认值，比如来源
        if (article.getSource() == null) {
            article.setSource("网页版");
        }
        articleMapper.insert(article);
    }

    /**
     * ⭐ 核心功能：点赞或取消点赞
     * 逻辑：
     * 1. 先查 article_like 表，看这个人有没有赞过这篇文章
     * 2. 如果没赞过 -> 插入记录，文章点赞数+1
     * 3. 如果赞过   -> 删除记录，文章点赞数-1
     */
    @Transactional // 开启事务，保证两步操作（改记录表、改统计数）要么都成功，要么都回滚
    public void likeArticle(Long articleId, Long userId) {
        // 1. 判断是否已经点过赞
        int count = articleLikeMapper.countByArticleAndUser(articleId, userId);

        if (count == 0) {
            // --- 没赞过 -> 执行点赞 ---
            ArticleLike like = new ArticleLike();
            like.setArticleId(articleId);
            like.setUserId(userId);
            articleLikeMapper.insert(like);

            // 文章表计数 +1
            articleMapper.increaseLikeCount(articleId);
        } else {
            // --- 赞过 -> 取消点赞 ---
            articleLikeMapper.delete(articleId, userId);

            // 文章表计数 -1
            articleMapper.decreaseLikeCount(articleId);
        }
    }
}