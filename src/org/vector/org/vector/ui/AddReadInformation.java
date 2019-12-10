/**
 * Company
 * Copyright (C) 2004-2019 All Rights Reserved.
 */
package org.vector.org.vector.ui;

import org.vector.bean.Reader;
import org.vector.bean.ReaderType;
import org.vector.bean.Users;
import org.vector.dao.ReaderDao;
import org.vector.daoipl.ReaderDaoIpl;
import org.vector.daoipl.ReaderTypeDaoIpl;
import org.vector.org.vector.service.Check;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.List;

/**
 * @author Administrator
 * @version $Id AddReadInformation.java, v 0.1 2019-05-28 22:03 Administrator Exp $$
 */
public class AddReadInformation {
    private JFrame newframe;        //新的frame;
    private JLayeredPane layeredPane = new JLayeredPane();
    private JLabel imglabel;
    private JLabel readeridlabel,typelabel,namelabel,agelabel,sexlabel,phonelabel,deptlabel,idcardlabel;
    private JTextField readeridjt,agejt,deptjt,namejt,phonejt,idcardjt;
    private JButton okjb,resetjb,backjb;
    private JComboBox jc;
    private JRadioButton manjr,womanjr;

    public AddReadInformation(JFrame frame, Users users) {
        load(frame,users);
    }

    public void load(JFrame frame,Users users){
        setFrame();
        setBackground();
        setContent();       //设置组件
        setButton(frame);
    }

    public void setFrame(){
        newframe = new JFrame("读者信息添加");
        newframe.setBounds(180,60,1200,550);
        newframe.setLayeredPane(layeredPane);
        newframe.setUndecorated(true);
        newframe.setResizable(false);
        newframe.setVisible(true);

        Point origin = new Point();
        newframe.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                origin.x = e.getX();
                origin.y = e.getY();
            }
        });
        newframe.addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
                Point p = newframe.getLocation();
                newframe.setLocation(p.x + e.getX() - origin.x, p.y + e.getY()- origin.y);
            }
        });
    }

    public void setContent(){   //设置各组件

        readeridlabel = new JLabel("读者编号"); //设置readerid标签
        readeridlabel.setBounds(100,50,300,100);
        readeridlabel.setFont(new Font("宋体", Font.PLAIN, 40));
        readeridlabel.setForeground(Color.red);
        layeredPane.add(readeridlabel,JLayeredPane.MODAL_LAYER);

        readeridjt = new JTextField();  //设置readerid文本框
        readeridjt.setBounds(275,75,300,50);
        readeridjt.setFont(new Font("宋体", Font.PLAIN, 40));
        readeridjt.addKeyListener(new KeyAdapter() {   //给readerid文本框设置键盘监听,使其只能输入数字
            @Override
            public void keyTyped(KeyEvent e) {
                int temp = e.getKeyChar();
                if(temp < 48 || temp > 57){
                    e.consume();
                }
            }
        });
        layeredPane.add(readeridjt,JLayeredPane.MODAL_LAYER);

        typelabel = new JLabel("读者类型"); //设置文本类型标签
        typelabel.setBounds(100,125,300,100);
        typelabel.setFont(new Font("宋体", Font.PLAIN, 40));
        typelabel.setForeground(Color.red);
        layeredPane.add(typelabel,JLayeredPane.MODAL_LAYER);

        jc = new JComboBox();   //设置文本类型下拉框
        List<ReaderType> list = new ReaderTypeDaoIpl().selectAllInformation();
        for (ReaderType readerType:list) {
            jc.addItem(readerType.getName());
        }
        jc.setBounds(275,150,300,50);
        jc.setFont(new Font("宋体", Font.PLAIN, 40));
        layeredPane.add(jc,JLayeredPane.MODAL_LAYER);

        agelabel = new JLabel("年龄");    //设置年龄标签
        agelabel.setBounds(145,200,200,100);
        agelabel.setFont(new Font("宋体", Font.PLAIN, 40));
        agelabel.setForeground(Color.red);
        layeredPane.add(agelabel,JLayeredPane.MODAL_LAYER);

        agejt = new JTextField();   //设置年龄框
        agejt.setBounds(275,225,300,50);
        agejt.setFont(new Font("宋体", Font.PLAIN, 40));
        agejt.addKeyListener(new KeyAdapter() { //给年龄框设置监听,使其只能输入数字
            @Override
            public void keyTyped(KeyEvent e) {
                int temp = e.getKeyChar();
                if(temp < 48 || temp > 57){
                    e.consume();
                }
            }
        });
        layeredPane.add(agejt,JLayeredPane.MODAL_LAYER);

        deptlabel = new JLabel("所在部门"); //设置部门标签
        deptlabel.setBounds(100,275,300,100);
        deptlabel.setFont(new Font("宋体", Font.PLAIN, 40));
        deptlabel.setForeground(Color.red);
        layeredPane.add(deptlabel,JLayeredPane.MODAL_LAYER);

        deptjt = new JTextField();  //设置部门文本框
        deptjt.setBounds(275,300,300,50);
        deptjt.setFont(new Font("宋体", Font.PLAIN, 40));
        layeredPane.add(deptjt,JLayeredPane.MODAL_LAYER);

        namelabel = new JLabel("姓名");   //设置姓名标签
        namelabel.setBounds(675,50,200,100);
        namelabel.setFont(new Font("宋体", Font.PLAIN, 40));
        namelabel.setForeground(Color.red);
        layeredPane.add(namelabel,JLayeredPane.MODAL_LAYER);

        namejt = new JTextField();  //设置姓名文本框
        namejt.setBounds(775,75,300,50);
        namejt.setFont(new Font("宋体", Font.PLAIN, 40));
        layeredPane.add(namejt,JLayeredPane.MODAL_LAYER);

        sexlabel = new JLabel("性别");    //设置性别标签
        sexlabel.setBounds(675,125,200,100);
        sexlabel.setFont(new Font("宋体", Font.PLAIN, 40));
        sexlabel.setForeground(Color.red);
        layeredPane.add(sexlabel,JLayeredPane.MODAL_LAYER);

        manjr = new JRadioButton("男",true);  //设置性别单选框
        manjr.setBounds(800,150,125,50);
        manjr.setFont(new Font("宋体", Font.PLAIN, 40));
        manjr.setForeground(Color.orange);
        manjr.setContentAreaFilled(false);
        manjr.setBorderPainted(false);
        layeredPane.add(manjr,JLayeredPane.MODAL_LAYER);

        womanjr = new JRadioButton("女");
        womanjr.setBounds(925,150,125,50);
        womanjr.setFont(new Font("宋体", Font.PLAIN, 40));
        womanjr.setForeground(Color.orange);
        womanjr.setContentAreaFilled(false);
        womanjr.setBorderPainted(false);
        layeredPane.add(womanjr,JLayeredPane.MODAL_LAYER);

        ButtonGroup group = new ButtonGroup();
        group.add(manjr);
        group.add(womanjr);

        phonelabel = new JLabel("电话");  //电话标签
        phonelabel.setBounds(675,200,200,100);
        phonelabel.setFont(new Font("宋体", Font.PLAIN, 40));
        phonelabel.setForeground(Color.red);
        layeredPane.add(phonelabel,JLayeredPane.MODAL_LAYER);

        phonejt = new JTextField(); //电话文本框
        phonejt.setBounds(775,225,300,50);
        phonejt.setFont(new Font("宋体", Font.PLAIN, 40));
        phonejt.addKeyListener(new KeyAdapter() {   //给电话框设置监听方法使其只能输入数字
            @Override
            public void keyTyped(KeyEvent e) {
                int temp = e.getKeyChar();
                if(temp < 48 || temp > 57){
                    e.consume();
                }
            }
        });
        layeredPane.add(phonejt,JLayeredPane.MODAL_LAYER);

        idcardlabel = new JLabel("身份证号");   //身份证号标签
        idcardlabel.setBounds(600,275,300,100);
        idcardlabel.setFont(new Font("宋体", Font.PLAIN, 40));
        idcardlabel.setForeground(Color.red);
        layeredPane.add(idcardlabel,JLayeredPane.MODAL_LAYER);

        idcardjt = new JTextField();    //身份证号文本框
        idcardjt.setBounds(775,300,300,50);
        idcardjt.setFont(new Font("宋体", Font.PLAIN, 40));
        layeredPane.add(idcardjt,JLayeredPane.MODAL_LAYER);

    }

    public void setButton(JFrame frame){    //设置按钮
        okjb = new JButton("添加");
        okjb.setBounds(350,425,100,50);
        okjb.setFont(new Font("宋体", Font.PLAIN, 32));
        okjb.setForeground(Color.blue);
        okjb.setContentAreaFilled(false);
        okjb.setBorderPainted(false);
        okjb.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String id = readeridjt.getText();
                String name = namejt.getText();
                String type = (String)jc.getSelectedItem(); //过去下拉框中选中的
                String sex;
                if(manjr.isSelected()){
                    sex = manjr.getText();
                }else{
                    sex = womanjr.getText();
                }
                String ages = agejt.getText();
                String phone = phonejt.getText();
                String idcard = idcardjt.getText();
                String dept = deptjt.getText();
                if(id.equals("") || name.equals("") || type.equals("") || sex.equals("") || ages.equals("") || phone.equals("") || idcard.equals("") || dept.equals("")){
                    JOptionPane.showMessageDialog(null,"关键信息为空,请填写完整!","错误",JOptionPane.ERROR_MESSAGE);
                }else{

                    int age = Integer.parseInt(ages);   //将年龄转换成int型

                    Reader readers = new Reader();
                    readers.setReaderid(id);
                    readers.setType(type);
                    readers.setName(name);
                    readers.setAge(age);
                    readers.setSex(sex);
                    readers.setPhone(phone);
                    readers.setIdcard(idcard);
                    readers.setDept(dept);

                    Reader reader = new ReaderDaoIpl().selectSingleReaderById(readers);//零时对象,用于判断
                    if(reader != null){ //用户数据已经存在,编号已经存在
                        readeridjt.setText("");
                        JOptionPane.showMessageDialog(null,"该用户信息已经存在！请重新输入!","错误",JOptionPane.ERROR_MESSAGE);
                    }else{
                        int num = Check.checkId(readers.getReaderid());
                        if (num != 1){  //判断id是否符合规范 8位数字
                            readeridjt.setText("");
                            JOptionPane.showMessageDialog(null,"读者编号不符合规范！请重新输入!","错误",JOptionPane.ERROR_MESSAGE);
                        }else{//判断你要插入的数据(年龄)
                            int flag;
                            flag = Check.checkAge(readers.getAge());
                            if(flag != 1){  //年龄不符合规范
                                agejt.setText("");
                                JOptionPane.showMessageDialog(null,"年龄太奇怪！请重新输入!","错误",JOptionPane.ERROR_MESSAGE);
                            }else{  //年龄符合,判断手机号是否符合规范
                                int temp;
                                temp = Check.checkPhone(readers.getPhone());
                                if(temp != 1){      //手机号不符合规范
                                    phonejt.setText("");
                                    JOptionPane.showMessageDialog(null,"手机号不符合规范！请重新输入!","错误",JOptionPane.ERROR_MESSAGE);
                                }else{ //判断身份证号
                                    int mnm = Check.checkIdcard(readers.getIdcard());
                                    if(mnm != 1){   //身份证不符合标准
                                        idcardjt.setText("");
                                        JOptionPane.showMessageDialog(null,"身份证号不符合规范！请重新输入!","错误",JOptionPane.ERROR_MESSAGE);
                                    }else{  //身份证符合
                                        new ReaderDaoIpl().addReader(readers);
                                        JOptionPane.showMessageDialog(null,"添加成功！","提示",JOptionPane.INFORMATION_MESSAGE);
                                    }
                                }
                                }
                            }
                        }

                }
            }
        });
        layeredPane.add(okjb,JLayeredPane.MODAL_LAYER);

        resetjb = new JButton("重置");
        resetjb.setBounds(600,425,100,50);
        resetjb.setFont(new Font("宋体", Font.PLAIN, 32));
        resetjb.setForeground(Color.blue);
        resetjb.setContentAreaFilled(false);
        resetjb.setBorderPainted(false);
        resetjb.addActionListener(new ActionListener() {    //给重置按钮设置监听方法
            @Override
            public void actionPerformed(ActionEvent e) {
                readeridjt.setText("");
                namejt.setText("");
                agejt.setText("");
                phonejt.setText("");
                deptjt.setText("");
                idcardjt.setText("");
                JOptionPane.showMessageDialog(null,"重置成功!","提示",JOptionPane.INFORMATION_MESSAGE);
            }
        });
        layeredPane.add(resetjb,JLayeredPane.MODAL_LAYER);

        backjb = new JButton("返回");
        backjb.setBounds(850,425,100,50);
        backjb.setFont(new Font("宋体", Font.PLAIN, 32));
        backjb.setForeground(Color.blue);
        backjb.setContentAreaFilled(false);
        backjb.setBorderPainted(false);
        backjb.addActionListener(new ActionListener() { //给返回案件设置监听事件
            @Override
            public void actionPerformed(ActionEvent e) {
                if(JOptionPane.YES_OPTION == JOptionPane.showConfirmDialog(null,"你确定要返回吗?","提问",JOptionPane.YES_NO_OPTION)){
                    newframe.setVisible(false);
                    frame.setVisible(true);
                }
            }
        });
        layeredPane.add(backjb,JLayeredPane.MODAL_LAYER);
    }

    public void setBackground(){
        ImageIcon icon = new ImageIcon("img/}9U38FAQ6I@U{0U9BI_)EA4.png");
        icon.setImage(icon.getImage().getScaledInstance(1200,550,Image.SCALE_DEFAULT));
        imglabel = new JLabel(icon);
        imglabel.setBounds(0,0,icon.getIconWidth(),icon.getIconHeight());
        layeredPane.add(imglabel,JLayeredPane.DEFAULT_LAYER);
    }

}