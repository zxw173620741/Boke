package org.demo.houduan.Entity;

import lombok.Data;
import java.time.LocalDateTime;

@Data
public class ArticleLike {
    private Long id;
    private Long articleId;
    private Long userId;
    private LocalDateTime createTime;
}