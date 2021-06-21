package com.example.exam;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.exam.mapper.QuestionMapper;
import com.example.exam.mapper.StudentAnswerMapper;
import com.example.exam.pojo.Question;
import com.example.exam.pojo.StudentAnswer;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@SpringBootTest
class ExamApplicationTests {
    @Autowired
    private QuestionMapper questionMapper;
    @Autowired
    private StudentAnswerMapper studentAnswerMapper;
    @Test
    void contextLoads() {
//        int n=1;
//        Page<StudentAnswer> page = new Page<>(n,1);
//        QueryWrapper<StudentAnswer> wrapper = new QueryWrapper<>();
//        wrapper.eq("studentId", "11");
//        List<StudentAnswer> studentAnswerList = studentAnswerMapper.selectPage(page, wrapper).getRecords();
//        studentAnswerList.forEach(System.out::println);
//        for (int i=0;;i++){
//            if (studentAnswerList.get(0).getStudentLearnQuestion()==1){
//                n=n+1;
//                page.setCurrent(n);
//                studentAnswerList=studentAnswerMapper.selectPage(page,wrapper).getRecords();
//                studentAnswerList.forEach(System.out::println);
//            }else
//                break;
//        }
//        ArrayList<Question> questionList = new ArrayList<>();
//        for (StudentAnswer studentAnswer : studentAnswerList){
//            questionList.add(questionMapper.selectById(studentAnswer.getQuestionId()));
//        }
//        questionList.forEach(System.out::println);
        QueryWrapper<StudentAnswer> wrapper = new QueryWrapper<>();
        wrapper.eq("studentId",13);
        wrapper.eq("questionId",2);
        StudentAnswer studentAnswer = new StudentAnswer();
        studentAnswer.setStudentLearnQuestion(1);
        studentAnswer.setStudentId(13);
        studentAnswer.setQuestionId(2);
        studentAnswer.setStudentAnswer("英语");
        System.out.println(studentAnswerMapper.update(studentAnswer,wrapper));
//        List<StudentAnswer> studentAnswerList = studentAnswerMapper.selectList(wrapper);
//        for (int i = 0; i < studentAnswerList.size(); i++) {
//            Page<Question> page = new Page<>(i, 1);
//            QueryWrapper<Question> studentAnswerQueryWrapper = new QueryWrapper<>();
//            studentAnswerQueryWrapper.eq("questionId", studentAnswerList.get(i).getQuestionId());
//            IPage<Question> questionIPage = questionMapper.selectPage(page, studentAnswerQueryWrapper);
//            List<Question> questionIPageRecords = questionIPage.getRecords();
//            questionIPageRecords.forEach(System.out::println);
////        StudentAnswer studentAnswer = new StudentAnswer();
////        studentAnswer.setStudentId(1);
////        studentAnswer.setQuestionId(2);
////        studentAnswerMapper.insert(studentAnswer);
////        List<StudentAnswer> studentAnswerList = studentAnswerMapper.selectList(null);
////        studentAnswerList.forEach(System.out::println);
//        }
    }
}
