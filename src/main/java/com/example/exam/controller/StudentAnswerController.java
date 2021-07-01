package com.example.exam.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.exam.mapper.QuestionMapper;
import com.example.exam.mapper.StudentAnswerMapper;
import com.example.exam.mapper.StudentMapper;
import com.example.exam.pojo.Question;
import com.example.exam.pojo.Student;
import com.example.exam.pojo.StudentAnswer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

@Controller
@RequestMapping("/studentAnswer")
public class StudentAnswerController {
    @Autowired
    private StudentAnswerMapper studentAnswerMapper;
    @Autowired
    private QuestionMapper questionMapper;
    @Autowired
    private StudentMapper studentMapper;

    @RequestMapping("/showStudentAnswer")
    public String ShowStudentAnswer(Model model,Integer studentId,Integer studentAnswerNums,Integer studentAnswerFrequency,String studentAnswer,Integer studentLearnQuestion,
                                    Integer studentAnswerA,Integer studentAnswerB, Integer studentAnswerC,Integer studentAnswerD,Integer questionId) {
        Page<Question> page = new Page<>(studentAnswerNums, 1);
        questionMapper.selectPage(page, null);
        if (studentAnswer != null ) {
            StudentAnswer answer = new StudentAnswer();
            answer.setStudentId(studentId);
            answer.setQuestionId(questionId);
            answer.setStudentAnswer(studentAnswer);
            answer.setStudentAnswerA(studentAnswerA);
            answer.setStudentAnswerB(studentAnswerB);
            answer.setStudentAnswerC(studentAnswerC);
            answer.setStudentAnswerD(studentAnswerD);
            answer.setStudentLearnQuestion(studentLearnQuestion);
            studentAnswerMapper.insert(answer);
            Student student = studentMapper.selectById(studentId);
            student.setStudentAnswerNums(studentAnswerNums);
            student.setStudentTotalAnswerNums(student.getStudentTotalAnswerNums()+1);
            studentMapper.updateById(student);
        }
        List<Question> questionList = new ArrayList<>();
        questionList = page.getRecords();
        if (questionList.size() != 0) {
            Question question = questionList.get(0);
            model.addAttribute("question", question);
            model.addAttribute("studentAnswerNums", studentAnswerNums);
            model.addAttribute("studentId", studentId);
            return "showstudentanswer";
        } else {
            Student student = studentMapper.selectById(studentId);
            student.setStudentAnswerFrequency(student.getStudentAnswerFrequency()+1);
            student.setStudentAnswerNums(0);
            studentMapper.updateById(student);
            model.addAttribute("student",student);
            return "finishquestionBank";
        }
    }
    @RequestMapping("/showStudentAnswerAgain")
    public String ShowStudentAnswerAgain(Model model,Integer studentId,Integer studentAnswerNums,Integer studentAnswerFrequency,String studentAnswerd,Integer studentLearnQuestion,
                                         Integer studentAnswerA,Integer studentAnswerB, Integer studentAnswerC,Integer studentAnswerD,Integer questionId){
        QueryWrapper<StudentAnswer> wrapper = new QueryWrapper<>();
        wrapper.eq("studentId",studentId);
        List<StudentAnswer> studentIdList = studentAnswerMapper.selectList(wrapper);
        if (studentAnswerd!=null||studentLearnQuestion!=null){
            StudentAnswer studentAnswer = new StudentAnswer();
            studentAnswer.setStudentId(studentId);
            studentAnswer.setQuestionId(questionId);
            studentAnswer.setStudentAnswer(studentAnswerd);
            studentAnswer.setStudentAnswerA(studentAnswerA);
            studentAnswer.setStudentAnswerB(studentAnswerB);
            studentAnswer.setStudentAnswerC(studentAnswerC);
            studentAnswer.setStudentAnswerD(studentAnswerD);
            studentAnswer.setStudentLearnQuestion(studentLearnQuestion);
            QueryWrapper<StudentAnswer> queryWrapper = new QueryWrapper<>();
            queryWrapper.eq("studentId",studentId);
            queryWrapper.eq("questionId",questionId);
            studentAnswerMapper.update(studentAnswer,queryWrapper);
            Student student = studentMapper.selectById(studentId);
            student.setStudentAnswerNums(studentAnswerNums);
            student.setStudentTotalAnswerNums(student.getStudentTotalAnswerNums()+1);
            studentMapper.updateById(student);
        }
        Page<StudentAnswer> page = new Page<>(studentAnswerNums,1);
        List<StudentAnswer> studentAnswerList = studentAnswerMapper.selectPage(page, wrapper).getRecords();
        List<Question> questions = questionMapper.selectList(null);
        if (studentAnswerList.size() != 0 && studentAnswerList.size() != questions.size()){
            for (int i=0;;i++){
                if (studentAnswerList.get(0).getStudentLearnQuestion()==1){
                    studentAnswerNums = studentAnswerNums+1;
                    page.setCurrent(studentAnswerNums);
                    studentAnswerList=studentAnswerMapper.selectPage(page,wrapper).getRecords();
                }else {
                    break;
                }
            }
            Question question = questionMapper.selectById(studentAnswerList.get(0).getQuestionId());
            int[] Rabbit = new int[4];
            for (int i = 0; i < Rabbit.length; i++) {
                Rabbit[i] = new Random().nextInt(4);
                for (int j = 0; j < i; j++) {
                    if (Rabbit[i] == Rabbit[j]) {
                        i--;
                        break;
                    }
                }
            }
            StudentAnswer studentAnswer = studentAnswerList.get(0);
            HashMap<Integer, String> map = new HashMap<>();
            HashMap<Integer, Integer> map1 = new HashMap<>();
            map.put(Rabbit[0],question.getQuestionOptionA());
            map.put(Rabbit[1],question.getQuestionOptionB());
            map.put(Rabbit[2],question.getQuestionOptionC());
            map.put(Rabbit[3],question.getQuestionOptionD());
            map1.put(Rabbit[0],studentAnswer.getStudentAnswerA());
            map1.put(Rabbit[1],studentAnswer.getStudentAnswerB());
            map1.put(Rabbit[2],studentAnswer.getStudentAnswerC());
            map1.put(Rabbit[3],studentAnswer.getStudentAnswerD());

            for (int i=0;i<Rabbit.length;i++){
                if (Rabbit[i]==0){
                    question.setQuestionOptionA(map.get(0));
                    studentAnswer.setStudentAnswerA(map1.get(0));
                }else if (Rabbit[i]==1){
                    question.setQuestionOptionB(map.get(1));
                    studentAnswer.setStudentAnswerB(map1.get(1));
                }else if (Rabbit[i]==2){
                    question.setQuestionOptionC(map.get(2));
                    studentAnswer.setStudentAnswerC(map1.get(2));
                }else{
                    question.setQuestionOptionD(map.get(3));
                    studentAnswer.setStudentAnswerD(map1.get(3));
                }
            }
            questionMapper.updateById(question);
            studentAnswerMapper.updateById(studentAnswer);
            model.addAttribute("question",question);
            model.addAttribute("studentId",studentId);
            model.addAttribute("studentAnswerNums",studentAnswerNums);
            model.addAttribute("studentAnswer",studentAnswer);
            return "showstudentanswerAgain";
        }else{
            Student student = studentMapper.selectById(studentId);
            student.setStudentAnswerNums(0);
            student.setStudentAnswerFrequency(student.getStudentAnswerFrequency()+1);
            studentMapper.updateById(student);
            model.addAttribute("student",student);

            return "finishquestionBank";
        }
    }
    @RequestMapping("/returnMain")
    public String ReturnMain(Model model,Integer studentId){
        Student student = studentMapper.selectById(studentId);
        List<Question> questionList = questionMapper.selectList(null);
//        model.addAttribute("studentAnswerdNums",studentAnswerList.size());
        model.addAttribute("questionNums",questionList.size());
        model.addAttribute("student",student);
        return "studentMain";
    }
}
