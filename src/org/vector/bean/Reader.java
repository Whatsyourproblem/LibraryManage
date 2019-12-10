/**
 * Company
 * Copyright (C) 2004-2019 All Rights Reserved.
 */
package org.vector.bean;

/**
 * @author Administrator
 * @version $Id Reader.java, v 0.1 2019-05-27 19:44 Administrator Exp $$
 */
public class Reader {   //读者实体类
    private String readerid;    //读者编号
    private String type;   //读者类型 教师,学生
    private String name;    //读者姓名
    private int age;    //读者年龄
    private String sex; //读者性别
    private String phone;   //读者手机号
    private int position;   //借阅状态,借了多少本
    private String idcard;  //身份证号,用于用户界面
    private String dept;    //读者所在部门
    public Reader() {
    }



    public Reader(String readerid, String type, String name, int age, String sex, String phone, int position,String idcard, String dept) {
        this.readerid = readerid;
        this.type = type;
        this.name = name;
        this.age = age;
        this.sex = sex;
        this.phone = phone;
        this.position = position;
        this.idcard = idcard;
        this.dept = dept;
    }

    public String getReaderid() {
        return readerid;
    }

    public void setReaderid(String readerid) {
        this.readerid = readerid;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getDept() {
        return dept;
    }

    public void setDept(String dept) {
        this.dept = dept;
    }
    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public String getIdcard() {
        return idcard;
    }

    public void setIdcard(String idcard) {
        this.idcard = idcard;
    }

    @Override
    public String toString() {
        return "Reader{" +
                "readerid=" + readerid +
                ", type='" + type + '\'' +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", sex='" + sex + '\'' +
                ", phone='" + phone + '\'' +
                ", position=" + position +
                ", idcard='" + idcard + '\'' +
                ", dept='" + dept + '\'' +
                '}';
    }
}