package com.example.exam.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.exam.mapper.QuestionMapper;
import com.example.exam.mapper.StudentAnswerMapper;
import com.example.exam.mapper.StudentMapper;
import com.example.exam.mapper.TeacherMapper;
import com.example.exam.pojo.Question;
import com.example.exam.pojo.Student;
import com.example.exam.pojo.StudentAnswer;
import com.example.exam.pojo.Teacher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.HashMap;
import java.util.List;

@Controller
public class LoginController {
    @Autowired
    private StudentMapper studentMapper;
    @Autowired
    private TeacherMapper teacherMapper;
    @Autowired
    private QuestionMapper questionMapper;
    @Autowired
    private StudentAnswerMapper studentAnswerMapper;

    @PostMapping("/login")
    public String Login(String username, String password,String type, Model model) {
        HashMap<String, Object> map = new HashMap<>();
        if (type.equals("student")) {
            map.put("studentName", username);
            map.put("studentPassword", password);
            List<Student> students = studentMapper.selectByMap(map);
            List<Question> questionList = questionMapper.selectList(null);

            if (students.size() == 1) {
                Student student = students.get(0);
                QueryWrapper<StudentAnswer> wrapper = new QueryWrapper<>();
                wrapper.eq("studentId",student.getStudentId());
                List<StudentAnswer> studentAnswerList = studentAnswerMapper.selectList(wrapper);
//                model.addAttribute("studentAnswerdNums",studentAnswerList.size());
                model.addAttribute("student",student);
                model.addAttribute("questionNums",questionList.size());
                return "studentMain";
            }
        } else{
            map.put("teacherName", username);
            map.put("teacherPassword", password);
            List<Teacher> teachers = teacherMapper.selectByMap(map);
            if (teachers.size() == 1) {
                model.addAttribute("username", teacherMapper.selectByMap(map).get(0).getTeacherName());
                return "teacherMain";
            }
        }
        return "login";
    }
    @RequestMapping("/sign")
    public String sign(Model model){
        return "signup";
    }
    @RequestMapping("/signUp")
    public String SignUp(Model model,String username,String password,String passwordAgain){
        if (password.equals(passwordAgain)){
            Student student = new Student();
            student.setStudentName(username);
            student.setStudentPassword(password);
            studentMapper.insert(student);
        }
        return "login";
    }
}
