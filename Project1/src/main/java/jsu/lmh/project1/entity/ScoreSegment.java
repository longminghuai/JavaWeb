package jsu.lmh.project1.entity;

import lombok.Data;

/**
 * 一分一段
 */
@Data
public class ScoreSegment {
    Integer score; // 分数
    Integer count; // 该分数的考生人数
    String seg;//分数段
    Integer total;//合计
}

