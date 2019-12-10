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
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * @author Administrator
 * @version $Id Frame.java, v 0.1 2019-05-27 19:56 Administrator Exp $$
 */
public class Frame {       //登陆主框体
    private JFrame frame;
    private JLayeredPane layeredPane = new JLayeredPane();
    private JTextField jt;
    private JPasswordField jp;
    private JLabel idlabel;
    private JLabel passwordlabel;
    private JLabel titlelabel;
    private JLabel printlabel;
    private JButton registjb;
    private JButton loginjb;
    private JButton quitjb;
    private JButton findjb;
    private JButton agreementjb;
    private JCheckBox jc;

    public Frame() {
        load();
    }
    public void load(){
        setFrame();
        setTitle();
        setLabel();
        setBackground();
        setField();
        setButton();
        setAgreement();
    }




    public void setFrame(){     //设置窗体
        frame = new JFrame();
        frame.setBounds(180,60,1600,900);
        frame.setLayeredPane(layeredPane);  //设置分层结构
        frame.setUndecorated(true);
        frame.setResizable(false);
        frame.setVisible(true);
    }


    public void setTitle() {   //设置标题
        titlelabel = new JLabel("欢迎来到图书馆管理系统");
        titlelabel.setBounds(150,40,800,300);
        titlelabel.setFont(new Font("宋体", Font.PLAIN, 70));
        titlelabel.setForeground(Color.red);

        layeredPane.add(titlelabel,JLayeredPane.PALETTE_LAYER);
    }

    public void setLabel() {      //设置idlabel和passwordlabel
        idlabel = new JLabel("用户名");
        idlabel.setBounds(900,400,200,200);
        idlabel.setForeground(Color.RED);
        idlabel.setFont(new Font("宋体", Font.PLAIN, 40));

        passwordlabel = new JLabel("密码");
        passwordlabel.setBounds(920,470,200,200);
        passwordlabel.setForeground(Color.RED);
        passwordlabel.setFont(new Font("宋体", Font.PLAIN, 40));

        layeredPane.add(idlabel,JLayeredPane.MODAL_LAYER);
        layeredPane.add(passwordlabel,JLayeredPane.MODAL_LAYER);

    }

    public void setField(){     //设置文本框和密码框
        jt = new JTextField();
        jt.setBounds(1050,475,400,50);
        jt.setFont(new Font("宋体", Font.PLAIN, 40));

        jp = new JPasswordField();
        jp.setBounds(1050,545,400,50);
        jp.setFont(new Font("宋体", Font.PLAIN, 40));

        layeredPane.add(jt,JLayeredPane.MODAL_LAYER);
        layeredPane.add(jp,JLayeredPane.MODAL_LAYER);

    }

    public void setButton(){    //设置regist,login,quit按钮
        registjb = new JButton("注册");
        registjb.setBounds(1050,615,100,50);
        registjb.setFont(new Font("宋体", Font.PLAIN, 32));
        registjb.setForeground(Color.CYAN);
        registjb.setContentAreaFilled(false);   //设置按钮透明
        registjb.setBorderPainted(false);       //去除按钮边框
        registjb.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Regist regist = new Regist();
            }
        });


        loginjb = new JButton("登录");
        loginjb.setBounds(1200,615,100,50);
        loginjb.setFont(new Font("宋体", Font.PLAIN, 32));
        loginjb.setForeground(Color.cyan);
        loginjb.setContentAreaFilled(false);
        loginjb.setBorderPainted(false);
        loginjb.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = jt.getText();
                String password = jp.getText();
                if(username.equals("") || password.equals("")){     //关键信息为空
                    JOptionPane.showMessageDialog(null,"关键信息为空!请填写完整!","错误",JOptionPane.ERROR_MESSAGE);
                }else{
                    int flag;
                    Users users = new Users();
                    users.setUsername(username);
                    users.setPassword(password);

                    Users user = new UsersDaoIpl().selectSgUserByName(users);   //创建一个对象来保存数据库中被查找出来的对象

                    if(user == null){ //如果查询不到该用户
                        JOptionPane.showMessageDialog(null,"用户不存在!请重新输入","错误",JOptionPane.ERROR_MESSAGE);
                        jt.setText("");
                    }else{
                        if(!jc.isSelected()){  //条款未被选中
                            JOptionPane.showMessageDialog(null,"请阅读并同意相关服务条款和隐私政策","错误",JOptionPane.ERROR_MESSAGE);
                        }else{  //同意条款
                            if(password.equals(user.getPassword())){
                                JOptionPane.showMessageDialog(null,"登录成功!","提示",JOptionPane.INFORMATION_MESSAGE);
                                frame.dispose();
                                new MangerLogin(user);
                            }else{
                                jp.setText("");
                                JOptionPane.showMessageDialog(null,"密码错误!请重新输入!","错误",JOptionPane.ERROR_MESSAGE);
                            }

                        }
                    }
                }
            }
        });


        quitjb = new JButton("退出");
        quitjb.setBounds(1350,615,100,50);
        quitjb.setFont(new Font("宋体", Font.PLAIN, 32));
        quitjb.setForeground(Color.cyan);
        quitjb.setContentAreaFilled(false);
        quitjb.setBorderPainted(false);
        quitjb.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {    //退出
                System.exit(0);
            }
        });


        layeredPane.add(registjb,JLayeredPane.MODAL_LAYER);
        layeredPane.add(loginjb,JLayeredPane.MODAL_LAYER);
        layeredPane.add(quitjb,JLayeredPane.MODAL_LAYER);
    }

    public void setAgreement(){
        findjb = new JButton("找回密码");
        findjb.setBounds(1000,685,120,50);
        findjb.setFont(new Font("宋体", Font.PLAIN, 20));
        findjb.setForeground(Color.blue);
        findjb.setContentAreaFilled(false);
        findjb.setBorderPainted(false);
        findjb.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new FindPassword();
            }
        });

        jc = new JCheckBox();
        jc.setBounds(1120,685,20,50);
        jc.setContentAreaFilled(false);
        jc.setBorderPainted(false);

        agreementjb = new JButton("我已阅读并同意相关服务条款和隐私政策");
        agreementjb.setBounds(1120,685,400,50);
        agreementjb.setFont(new Font("宋体", Font.PLAIN, 20));
        agreementjb.setForeground(Color.red);
        agreementjb.setContentAreaFilled(false);
        agreementjb.setBorderPainted(false);
        agreementjb.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new Agreement();
            }
        });

        layeredPane.add(findjb,JLayeredPane.MODAL_LAYER);
        layeredPane.add(jc,JLayeredPane.MODAL_LAYER);
        layeredPane.add(agreementjb,JLayeredPane.MODAL_LAYER);
    }

    public void setBackground() {
        ImageIcon icon1 = new ImageIcon("img/HTFCSGFU3NV[SH)A){KLPV9.png");
        icon1.setImage(icon1.getImage().getScaledInstance(1600, 900,Image.SCALE_DEFAULT ));
        printlabel = new JLabel(icon1);
        printlabel.setBounds(0, 0, icon1.getIconWidth(), icon1.getIconHeight());
        layeredPane.add(printlabel,JLayeredPane.DEFAULT_LAYER);
    }

    public static void main(String[] args) {
        new Frame();
    }
}