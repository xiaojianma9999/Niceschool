package com.nsapi.niceschoolapi.controller;

import com.nsapi.niceschoolapi.entity.PoliticsTypeDB;
import com.nsapi.niceschoolapi.entity.TeacherDB;
import com.nsapi.niceschoolapi.service.AddTeacherService;
import com.nsapi.niceschoolapi.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Controller
public class AddTeacherController {
    @Autowired
    private StudentService studentService;
    @Autowired
    private AddTeacherService addTeacherService;

    @RequestMapping("/addTeacherPage")
    public String addTeacherPage(Model model){
        //  查询政治面貌表
        List<PoliticsTypeDB> stupol = studentService.selPolitics();
        model.addAttribute("stupol",stupol);
        return "view/teacher/addTeacher";
    }



    //  添加教师
    @RequestMapping("/addTeacher")
    public String addTeacher(TeacherDB teacherDB, String birthday, String tertime) throws Exception{
        //  将接收到的时间进行类型转换
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Date date1=format.parse(birthday);
        Date date2=format.parse(tertime);
        teacherDB.setTbirthday(date1);
        teacherDB.setEntertime(date2);
        //  判断是否要插入新一年分割工号 1要插入，0不用插入
        Integer year = addTeacherService.selectYear();
        if(year>0){
            //  添加一条分割线
            Integer aa = addTeacherService.teaSegmentation();
        }
        //  生成教师工号
        String tchid = addTeacherService.selTchid();
        teacherDB.setTchid(tchid);
        //  添加教师
        Integer addTeacher = addTeacherService.addTeacher(teacherDB);
        return "redirect:selectTeacher";
    }
}