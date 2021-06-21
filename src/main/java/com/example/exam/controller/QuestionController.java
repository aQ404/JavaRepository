package com.example.exam.controller;

import com.example.exam.mapper.QuestionMapper;
import com.example.exam.pojo.Question;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/question")
public class QuestionController {
    @Autowired
    private QuestionMapper questionMapper;

    @RequestMapping("/showQuestion")
    public String ShowQuestion(Model model){
        List<Question> questionList = questionMapper.selectList(null);
        model.addAttribute("questionList",questionList);
        return "showquestion";
    }

    @RequestMapping("/add")
    public String Add(Model model){
        return "addquestion";
    }

    @RequestMapping("/addQuestion")
    public String AddQuestion(Model model,String questionType,String questionContent,String questionDescription,String questionOptionA,
                              String questionOptionB,String questionOptionC,String questionOptionD,String questionAnalysisOptionA,
                              String questionAnalysisOptionB,String questionAnalysisOptionC,String questionAnalysisOptionD,String questionAnswer,
                              String questionDifficulty,String questionKnowledges){
        Question question = new Question();
        question.setQuestionType(questionType);
        question.setQuestionContent(questionContent);
        question.setQuestionDescription(questionDescription);
        question.setQuestionOptionA(questionOptionA);
        question.setQuestionOptionB(questionOptionB);
        question.setQuestionOptionC(questionOptionC);
        question.setQuestionOptionD(questionOptionD);
        question.setQuestionAnalysisOptionA(questionAnalysisOptionA);
        question.setQuestionAnalysisOptionB(questionAnalysisOptionB);
        question.setQuestionAnalysisOptionC(questionAnalysisOptionC);
        question.setQuestionAnalysisOptionD(questionAnalysisOptionD);
        question.setQuestionAnswer(questionAnswer);
        question.setQuestionDifficulty(questionDifficulty);
        question.setQuestionKnowledges(questionKnowledges);
        questionMapper.insert(question);
        List<Question> questionList = questionMapper.selectList(null);
        model.addAttribute("questionList",questionList);
        return "showquestion";
    }
    @RequestMapping("/update")
    public String Update(Model model,Integer questionId){
        Question question = questionMapper.selectById(questionId);
        model.addAttribute("question",question);
        return "updatequestion";
    }
    @RequestMapping("/updateQuestion")
    public String UpdateQuestion(Model model,String questionType,String questionContent,String questionDescription,String questionOptionA,
                                 String questionOptionB,String questionOptionC,String questionOptionD,String questionAnalysisOptionA,
                                 String questionAnalysisOptionB,String questionAnalysisOptionC,String questionAnalysisOptionD,String questionAnswer,
                                 String questionDifficulty,String questionKnowledges,Integer questionId){
        Question question = new Question(questionId, questionType, questionContent, questionDescription, questionOptionA, questionOptionB, questionOptionC, questionOptionD,
                questionAnalysisOptionA, questionAnalysisOptionB, questionAnalysisOptionC, questionAnalysisOptionD, questionAnswer, questionDifficulty, questionKnowledges);
        questionMapper.updateById(question);
        List<Question> questionList = questionMapper.selectList(null);
        model.addAttribute("questionList",questionList);
        return "showquestion";
    }
    @RequestMapping("/deleteQuestion")
    public String DeleteQuestion(Model model,Integer questionId){
        questionMapper.deleteById(questionId);
        List<Question> questionList = questionMapper.selectList(null);
        model.addAttribute("questionList",questionList);
        return "showquestion";
    }
}
