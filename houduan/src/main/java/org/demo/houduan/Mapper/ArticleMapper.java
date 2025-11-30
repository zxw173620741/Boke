package org.demo.houduan.Mapper;

import org.apache.ibatis.annotations.Mapper;
import org.demo.houduan.Vo.ArticleVO;

import java.util.List;

@Mapper
public interface ArticleMapper {

    /**
     * 查询所有文章（包含作者信息）
     * @return 文章列表
     */
    List<ArticleVO> selectAllArticles();
}