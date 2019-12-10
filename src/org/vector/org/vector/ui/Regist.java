/**
 * Company
 * Copyright (C) 2004-2019 All Rights Reserved.
 */
package org.vector.org.vector.ui;

import org.vector.bean.Users;
import org.vector.daoipl.UsersDaoIpl;
import org.vector.org.vector.service.Check;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/**
 * @author Administrator
 * @version $Id Regist.java, v 0.1 2019-05-27 22:16 Administrator Exp $$
 */
public class Regist {
    private JFrame frame;
    private JLayeredPane layeredPane = new JLayeredPane();
    private JLabel welcomelabel,usernamelabel,passwordlabel,repasswordlabel,printlabel,idcardlabel;
    private JTextField usernamejt,idcardjt;
    private JPasswordField passwordjp,repasswordjp;
    private JButton okjb,resetjb,backjb;

    public Regist() {
        load();
    }

    public void load(){
        setFrame();
        setBackground();
        setTitle();
        setLabel();
        setButton();
    }

    public void setFrame(){ //设置窗体
        frame = new JFrame();
        frame.setBounds(300,300,800,525);
        frame.setLayeredPane(layeredPane);
        frame.setUndecorated(true);
        frame.setResizable(false);
        frame.setVisible(true);

        //网上找的无边框拖拽方法
        Point origin = new Point();
        frame.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                origin.x = e.getX();
                origin.y = e.getY();
            }
        });
        frame.addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
                Point p = frame.getLocation();
                frame.setLocation(p.x + e.getX() - origin.x, p.y + e.getY()- origin.y);
            }
        });
    }

    public void  setTitle(){    //设置标题
        welcomelabel = new JLabel("欢迎注册!!");
        welcomelabel.setBounds(75,25,500,100);
        welcomelabel.setFont(new Font("宋体", Font.PLAIN, 70));
        welcomelabel.setForeground(Color.pink);
        layeredPane.add(welcomelabel,JLayeredPane.PALETTE_LAYER);
    }

    public void setLabel(){     //设置标签以及文本框
        usernamelabel = new JLabel("用户名");
        usernamelabel.setBounds(125,125,250,100);
        usernamelabel.setFont(new Font("宋体", Font.PLAIN, 40));
        usernamelabel.setForeground(Color.magenta);
        layeredPane.add(usernamelabel,JLayeredPane.MODAL_LAYER);

        usernamejt = new JTextField();
        usernamejt.setBounds(300,150,400,50);
        usernamejt.setFont(new Font("宋体", Font.PLAIN, 40));
        layeredPane.add(usernamejt,JLayeredPane.MODAL_LAYER);

        passwordlabel = new JLabel("密码");
        passwordlabel.setBounds(150,200,200,100);
        passwordlabel.setFont(new Font("宋体", Font.PLAIN, 40));
        passwordlabel.setForeground(Color.magenta);
        layeredPane.add(passwordlabel,JLayeredPane.MODAL_LAYER);

        passwordjp = new JPasswordField();
        passwordjp.setBounds(300,225,400,50);
        passwordjp.setFont(new Font("宋体", Font.PLAIN, 40));
        layeredPane.add(passwordjp,JLayeredPane.MODAL_LAYER);

        repasswordlabel = new JLabel("重复密码");
        repasswordlabel.setBounds(100,275,300,100);
        repasswordlabel.setFont(new Font("宋体", Font.PLAIN, 40));
        repasswordlabel.setForeground(Color.magenta);
        layeredPane.add(repasswordlabel,JLayeredPane.MODAL_LAYER);

        repasswordjp = new JPasswordField();
        repasswordjp.setBounds(300,300,400,50);
        repasswordjp.setFont(new Font("宋体", Font.PLAIN, 40));
        layeredPane.add(repasswordjp,JLayeredPane.MODAL_LAYER);

        idcardlabel = new JLabel("身份证号");
        idcardlabel.setBounds(100,350,300,100);
        idcardlabel.setFont(new Font("宋体", Font.PLAIN, 40));
        idcardlabel.setForeground(Color.magenta);
        layeredPane.add(idcardlabel,JLayeredPane.MODAL_LAYER);

        idcardjt = new JTextField();
        idcardjt.setBounds(300,375,400,50);
        idcardjt.setFont(new Font("宋体", Font.PLAIN, 40));
        idcardjt.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                int temp = e.getKeyChar();
                if(temp < 48 || temp > 57){
                    e.consume();
                }
            }
        });
        layeredPane.add(idcardjt,JLayeredPane.MODAL_LAYER);
    }

    public void setButton(){    //设置按钮
        okjb = new JButton("确认");
        okjb.setBounds(300,450,100,50);
        okjb.setFont(new Font("宋体", Font.PLAIN, 32));
        okjb.setForeground(Color.CYAN);
        okjb.setContentAreaFilled(false);
        okjb.setBorderPainted(false);
        okjb.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int flag;
                String username = usernamejt.getText();
                String password = passwordjp.getText();
                String repassword = repasswordjp.getText();
                String usertype;
                String idcard = idcardjt.getText();

                Users users = new Users(username,password,idcard);

                if(username.equals("") || password.equals("") || repassword.equals("")){    //关键信息为空
                    JOptionPane.showMessageDialog(null,"关键信息为空,请填写完整!","错误",JOptionPane.ERROR_MESSAGE);
                }else{
                    flag = Check.isExist(users);
                    if(flag == 0){
                        usernamejt.setText("");
                        JOptionPane.showMessageDialog(null,"用户名已经存在！请重新输入!","错误",JOptionPane.ERROR_MESSAGE);
                    }else{
                        if(!password.equals(repassword)){
                            repasswordjp.setText("");
                            JOptionPane.showMessageDialog(null,"重复密码错误!,请重新输入!","错误",JOptionPane.ERROR_MESSAGE);
                        }else{
                            int temp;
                            temp = Check.checkIdcard(idcard);
                            if(temp != 1){  //身份证号不标准
                                JOptionPane.showMessageDialog(null,"身份证不符合标准!,请重新输入!","错误",JOptionPane.ERROR_MESSAGE);
                                idcardjt.setText("");
                            }else{  //身份证号标准
                                JOptionPane.showMessageDialog(null,"注册成功!","提示",JOptionPane.INFORMATION_MESSAGE);
                                new UsersDaoIpl().addUser(users);
                                frame.dispose();
                            }

                        }
                    }
                }
            }
        });
        layeredPane.add(okjb,JLayeredPane.MODAL_LAYER);

        resetjb = new JButton("重置");
        resetjb.setBounds(450,450,100,50);
        resetjb.setFont(new Font("宋体", Font.PLAIN, 32));
        resetjb.setForeground(Color.CYAN);
        resetjb.setContentAreaFilled(false);
        resetjb.setBorderPainted(false);
        resetjb.addActionListener(new ActionListener() {    //给重置按钮添加监听
            @Override
            public void actionPerformed(ActionEvent e) {
                usernamejt.setText("");
                passwordjp.setText("");
                repasswordjp.setText("");
                idcardjt.setText("");
                JOptionPane.showMessageDialog(null,"重置成功!","提示",JOptionPane.INFORMATION_MESSAGE);
            }
        });
        layeredPane.add(resetjb,JLayeredPane.MODAL_LAYER);

        backjb = new JButton("返回");
        backjb.setBounds(600,450,100,50);
        backjb.setFont(new Font("宋体", Font.PLAIN, 32));
        backjb.setForeground(Color.CYAN);
        backjb.setContentAreaFilled(false);
        backjb.setBorderPainted(false);
        backjb.addActionListener(new ActionListener() { //给返回按钮设置监听事件
            @Override
            public void actionPerformed(ActionEvent e) {
                if(JOptionPane.YES_OPTION == JOptionPane.showConfirmDialog(null,"你确定要返回吗?","提问",JOptionPane.YES_NO_OPTION)){
                    frame.dispose();    //设置提示框,增加用户体验
                }
            }
        });
        layeredPane.add(backjb,JLayeredPane.MODAL_LAYER);
    }

    public void setBackground(){ //设置背景图片
        ImageIcon icon = new ImageIcon("img/14a9312f5b164660a3f4c1b7c97aecf0.jpeg");
        icon.setImage(icon.getImage().getScaledInstance(800,525, Image.SCALE_DEFAULT));
        printlabel = new JLabel(icon);
        printlabel.setBounds(0,0,icon.getIconWidth(),icon.getIconHeight());
        layeredPane.add(printlabel,JLayeredPane.DEFAULT_LAYER);
    }
}