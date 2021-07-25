package com.es.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

import java.time.LocalDateTime;
import java.util.List;

/**
 * actiovity creation
 *
 * @author tallon
 * @version v1.0.0
 * @date 2021-07-24 16:51
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ActivityCreation {

    private static final String KEYBOARD_LAYOUT_DISABLED = "DISABLED";

    @Id
    private Long id;

    /**
     * 学生Oid
     */
    private String studentOid;

    /**
     * 活动ID
     */
    private Long activityId;

    /**
     * 作品ID
     */
    private String creationOid;

    /**
     * 活动名称
     */
    private String activityTitle;

    /**
     * 作品名称
     */
    private String creationTitle;

    /**
     * 示例工程ID
     */
    private Long typicalProjectId;

    /**
     * 键盘布局
     */
    private String keyboardLayout;


    /**
     * 潜力新作过期时间
     */
    private LocalDateTime potentialExpiresAt;

    /**
     * 标签
     */
    private List<String> tags;

    /**
     * 是否广场可见
     * 默认：true
     */
    private Boolean forEveryone;

    /**
     * 发布时间
     */
    private LocalDateTime lastPassedAt;

    /**
     * 创建时间
     */
    private LocalDateTime createdAt;

    /**
     * 修改时间
     */
    private LocalDateTime updatedAt;

    /**
     * 作品提交时间
     */
    private LocalDateTime submittedAt;

    /**
     * 作品版本号
     */
    private Float releaseVersion;

    /**
     * 浏览量
     */
    private Integer viewCount;

}
