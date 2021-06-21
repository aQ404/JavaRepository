package com.example.exam.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Question {
    @TableId(type = IdType.AUTO)
    private Integer questionId;
    private String questionType;
    private String questionContent;
    private String questionDescription;
    private String questionOptionA;
    private String questionOptionB;
    private String questionOptionC;
    private String questionOptionD;
    private String questionAnalysisOptionA;
    private String questionAnalysisOptionB;
    private String questionAnalysisOptionC;
    private String questionAnalysisOptionD;
    private String questionAnswer;
    private String questionDifficulty;
    private String questionKnowledges;

}
