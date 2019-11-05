package com.nsapi.niceschoolapi.controller;

import com.nsapi.niceschoolapi.entity.LayuiResult;
import com.nsapi.niceschoolapi.entity.TchExamDB;
import com.nsapi.niceschoolapi.entity.TeacherDB;
import com.nsapi.niceschoolapi.service.GradeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;

@Controller
public class GradeController {
    @Autowired
    private GradeService gradeService;

    @RequestMapping("/selectGrade")
    public String pageSelgrade(Model model){
        Integer sid = 1;
        //  查询学生个人信息
        List<Map> selStudentMessage = gradeService.selStudentMessage(sid);
        model.addAttribute("selStudentMessage",selStudentMessage);
        return "view/student/selGrade";
    }

    //  根据学期查看成绩
    @RequestMapping("selGrade")
    @ResponseBody
    public LayuiResult<Map> selGrade(Integer stime){
        Integer sid = 1;
        if(stime == null){
            List<Map> selGrade = gradeService.selGrade(sid,1);
            LayuiResult result = new LayuiResult();
            result.setData(selGrade);
            return result;
        }else{
            List<Map> selGrade = gradeService.selGrade(sid,stime);
            LayuiResult result = new LayuiResult();
            result.setData(selGrade);
            return result;
        }

    }

    //  管理员/教师 详情页面查询学生成绩
    @RequestMapping("/selExam")
    public String selExam(Integer sid, Integer stime, Model model){
        if(stime == null){
            stime = 1;
            List<Map> selGrade = gradeService.selGrade(sid,stime);
            model.addAttribute("selGrade",selGrade);
            model.addAttribute("sid",sid);
            model.addAttribute("stime",stime);
            return "view/student/selStuExam";
        }else{
            List<Map> selGrade = gradeService.selGrade(sid,stime);
            model.addAttribute("selGrade",selGrade);
            model.addAttribute("sid",sid);
            model.addAttribute("stime",stime);
            return "view/student/selStuExam";
        }


    }

    //  管理员详情页面查询教师教评成绩
    @RequestMapping("/selTeaExam")
    public String selTeaExam(Integer tid, Model model){
        List<TchExamDB> selTeaExam = gradeService.selTeaExam(tid);
        model.addAttribute("selTeaExam",selTeaExam);
        return "view/teacher/selTeaExam";
    }

    //  教师个人成绩查询
    @RequestMapping("/selTeacherGrade")
    public String selGrade(Model model){
        Integer tid = 1;
        List<TchExamDB> selTeaExam = gradeService.selTeaExam(tid);
        //  查询教师个人信息
        List<TeacherDB> selTea = gradeService.selTea(tid);
        model.addAttribute("selTea",selTea);
        model.addAttribute("selTeaExam",selTeaExam);
        return "view/teacher/selGrade";
    }

}