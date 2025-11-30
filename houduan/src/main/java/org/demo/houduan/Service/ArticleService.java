package org.demo.houduan.Service;

import org.demo.houduan.Entity.Article;
import org.demo.houduan.Mapper.ArticleMapper;
import org.demo.houduan.Vo.ArticleVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ArticleService {

    @Autowired
    private ArticleMapper articleMapper;

    /**
     * 获取所有文章列表（包含作者信息）
     * @return ArticleVO 列表
     */
    public List<ArticleVO> listAll() {
        // 直接调用 Mapper 里的关联查询方法
        return articleMapper.selectAllArticles();
    }
    public void publish(Article article) {
        // 这里可以设置一些默认值，比如来源
        if (article.getSource() == null) {
            article.setSource("网页版");
        }
        articleMapper.insert(article);
    }
}