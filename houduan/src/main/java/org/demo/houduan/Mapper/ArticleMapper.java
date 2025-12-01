package org.demo.houduan.Mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.demo.houduan.Vo.ArticleVO;

import java.util.List;

@Mapper
public interface ArticleMapper {

    /**
     * 查询所有文章（包含作者信息）
     * @return 文章列表
     */
    List<ArticleVO> selectAllArticles(@Param("currentUserId") Long currentUserId);
    /**
     * 新增文章
     */
    int insert(org.demo.houduan.Entity.Article article);
    // ⭐ 新增：更新点赞数
    void increaseLikeCount(Long id);
    void decreaseLikeCount(Long id);
}