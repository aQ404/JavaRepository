package com.example.exam.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class StudentAnswer {
    @TableId(type = IdType.AUTO)
    private Integer studentAnswerId;
    private Integer studentId;
    private Integer questionId;
    private String studentAnswer;
    private Integer studentAnswerA;
    private Integer studentAnswerB;
    private Integer studentAnswerC;
    private Integer studentAnswerD;
    private Integer studentLearnQuestion;
}
