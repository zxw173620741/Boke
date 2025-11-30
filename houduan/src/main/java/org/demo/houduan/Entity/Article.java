package org.demo.houduan.Entity;

import lombok.Data;
import java.time.LocalDateTime;

/**
 * 文章实体类
 * 对应数据库表: article
 */
@Data
public class Article {

    /**
     * 文章ID (主键)
     */
    private Long id;

    /**
     * 作者ID (关联 user 表)
     */
    private Long userId;

    /**
     * 文章内容
     */
    private String content;

    /**
     * 图片地址 (多张用逗号分隔或 JSON 字符串)
     */
    private String images;

    /**
     * 发布来源 (例如: 网页版, iPhone 15 Pro)
     */
    private String source;

    /**
     * 点赞数
     */
    private Integer likeCount;

    /**
     * 评论数
     */
    private Integer commentCount;

    /**
     * 转发数
     */
    private Integer shareCount;

    /**
     * 发布时间
     */
    private LocalDateTime createTime;

    /**
     * 修改时间
     */
    private LocalDateTime updateTime;
}