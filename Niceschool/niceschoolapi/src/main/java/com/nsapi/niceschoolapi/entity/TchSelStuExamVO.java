package com.nsapi.niceschoolapi.entity;

public class TchSelStuExamVO {
    private String tid;
    private Integer stime;
    private Integer entertime;
    private Integer classid;
    private String classname;

    public String getClassname() {
        return classname;
    }

    public void setClassname(String classname) {
        this.classname = classname;
    }

    public String getTid() {
        return tid;
    }

    public void setTid(String tid) {
        this.tid = tid;
    }

    public Integer getStime() {
        return stime;
    }

    public void setStime(Integer stime) {
        this.stime = stime;
    }

    public Integer getEntertime() {
        return entertime;
    }

    public void setEntertime(Integer entertime) {
        this.entertime = entertime;
    }

    public Integer getClassid() {
        return classid;
    }

    public void setClassid(Integer classid) {
        this.classid = classid;
    }
}