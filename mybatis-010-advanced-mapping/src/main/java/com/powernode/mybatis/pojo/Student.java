package com.powernode.mybatis.pojo;

/**
 * @PROJECT_NAME: mybatis
 * @DESCRIPTION: 学生信息
 * @USER: 11240
 * @DATE: 2022/10/22 22:51
 */
public class Student {//Student是多的一方
    private Integer sid;
    private String sname;
    private Clazz clazz;//Clazz是一的一方
    public Student() {
    }

    public Student(Integer sid, String sname) {
        this.sid = sid;
        this.sname = sname;
    }

    @Override
    public String toString() {
        return "Student{" +
                "sid=" + sid +
                ", sname='" + sname + '\'' +
                ", clazz=" + clazz +
                '}';
    }

    public Clazz getClazz() {
        return clazz;
    }

    public void setClazz(Clazz clazz) {
        this.clazz = clazz;
    }

    public Integer getSid() {
        return sid;
    }

    public void setSid(Integer sid) {
        this.sid = sid;
    }

    public String getSname() {
        return sname;
    }

    public void setSname(String sname) {
        this.sname = sname;
    }
}
