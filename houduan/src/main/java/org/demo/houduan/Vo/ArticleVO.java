package org.demo.houduan.Vo;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.demo.houduan.Entity.Article;

/**
 * 文章视图对象
 * 继承自 Article 实体类，复用原有字段
 * 并额外包含作者信息，用于前端展示
 */
@Data
public class ArticleVO extends Article {

    /**
     * 作者用户名
     * 对应 user 表的 user_name
     */
    private String authorName;

    /**
     * 作者昵称
     * 对应 user 表的 nick_name
     */
    private String authorNickName;

    /**
     * 作者头像
     * (目前数据库 user 表还没这个字段，作为预留)
     */
    private String authorAvatar;
}