package com.example.exam.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.exam.pojo.Question;
import org.springframework.stereotype.Repository;

@Repository
public interface QuestionMapper extends BaseMapper<Question> {
}
