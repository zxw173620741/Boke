package org.demo.houduan.Mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.demo.houduan.Entity.ArticleLike;

@Mapper
public interface ArticleLikeMapper {
    // 插入点赞记录
    void insert(ArticleLike articleLike);

    // 删除点赞记录
    void delete(@Param("articleId") Long articleId, @Param("userId") Long userId);

    // 查询是否点过赞 (用于校验)
    int countByArticleAndUser(@Param("articleId") Long articleId, @Param("userId") Long userId);
}