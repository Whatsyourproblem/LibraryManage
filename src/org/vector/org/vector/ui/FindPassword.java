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
 * @version $Id FindPassword.java, v 0.1 2019-05-28 17:11 Administrator Exp $$
 */
public class FindPassword {     //找回密码
    private JFrame frame;
    private JLayeredPane layeredPane = new JLayeredPane();
    private JLabel usernamelabel,idcardlabel,imglabel,titlelabel;
    private JTextField usernamejt,idcardjt;
    private JButton okjb,resetjb,backjb;

    public FindPassword() {
        load();
    }

    public void load(){
        setFrame();
        setLabel();
        setBackground();
        setButton();
    }

    public void setFrame(){
        frame = new JFrame();
        frame.setLayeredPane(layeredPane);
        frame.setBounds(600,200,600,400);
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

    public void setLabel(){ //设置标签和文本框
        titlelabel = new JLabel("密码找回~~~");     //标题标签
        titlelabel.setBounds(100,25,400,70);
        titlelabel.setFont(new Font("宋体", Font.PLAIN, 70));
        titlelabel.setForeground(Color.green);
        layeredPane.add(titlelabel,JLayeredPane.MODAL_LAYER);

        usernamelabel = new JLabel("用户名");  //用户名标签
        usernamelabel.setBounds(50,125,250,100);
        usernamelabel.setFont(new Font("宋体", Font.PLAIN, 40));
        usernamelabel.setForeground(Color.red);
        layeredPane.add(usernamelabel,JLayeredPane.MODAL_LAYER);

        usernamejt = new JTextField();      //用户名文本框
        usernamejt.setBounds(200,150,350,50);
        usernamejt.setFont(new Font("宋体", Font.PLAIN, 40));
        layeredPane.add(usernamejt,JLayeredPane.MODAL_LAYER);

        idcardlabel = new JLabel("身份证号");       //与上类似
        idcardlabel.setBounds(25,200,300,100);
        idcardlabel.setFont(new Font("宋体", Font.PLAIN, 40));
        idcardlabel.setForeground(Color.red);
        layeredPane.add(idcardlabel,JLayeredPane.MODAL_LAYER);

        idcardjt = new JTextField();
        idcardjt.setBounds(200,225,350,50);
        idcardjt.setFont(new Font("宋体", Font.PLAIN, 40));
        idcardjt.addKeyListener(new KeyAdapter() {  //给身份证文本框设置监听,使其不能输入除了数字外的其他字符
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

    public void setButton(){
        okjb = new JButton("确定");
        okjb.setBounds(200,300,100,50);
        okjb.setFont(new Font("宋体", Font.PLAIN, 32));
        okjb.setForeground(Color.blue);
        okjb.setContentAreaFilled(false);
        okjb.setBorderPainted(false);
        okjb.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = usernamejt.getText();
                String idcard = idcardjt.getText();
                if(username.equals("") || idcard.equals("")){       //关键信息为空
                    JOptionPane.showMessageDialog(null,"关键信息为空!,请输入!","错误",JOptionPane.ERROR_MESSAGE);
                }else{
                    int flag;
                    flag = Check.checkIdcard(idcard);
                    if (flag != 1){ //身份证号不规范
                        JOptionPane.showMessageDialog(null,"身份证号不规范!请重新输入!","错误",JOptionPane.ERROR_MESSAGE);
                        idcardjt.setText("");
                    }else{
                        Users users = new Users();
                        users.setUsername(username);
                        users.setUseridcard(idcard);
                        if(new UsersDaoIpl().selectSingleUser(users) == null){      //查询不到用户
                            usernamejt.setText("");
                            idcardjt.setText("");
                            JOptionPane.showMessageDialog(null,"用户名或身份证号错误!","错误",JOptionPane.ERROR_MESSAGE);
                        }else{  //返回了该用户
                            Users user = new Users();
                            user = new UsersDaoIpl().selectSingleUser(users);
                            JOptionPane.showMessageDialog(null,"您的密码为" + user.getPassword() + "!请妥善保管!","提示",JOptionPane.INFORMATION_MESSAGE);
                        }
                    }

                }
            }
        });
        layeredPane.add(okjb,JLayeredPane.MODAL_LAYER);

        resetjb = new JButton("重置");
        resetjb.setBounds(325,300,100,50);
        resetjb.setFont(new Font("宋体", Font.PLAIN, 32));
        resetjb.setForeground(Color.blue);
        resetjb.setContentAreaFilled(false);
        resetjb.setBorderPainted(false);
        resetjb.addActionListener(new ActionListener() {    //给重置按钮设置监听方法
            @Override
            public void actionPerformed(ActionEvent e) {
                usernamejt.setText("");
                idcardjt.setText("");
                JOptionPane.showMessageDialog(null,"重置成功!","提示",JOptionPane.INFORMATION_MESSAGE);
            }
        });
        layeredPane.add(resetjb,JLayeredPane.MODAL_LAYER);

        backjb = new JButton("返回");
        backjb.setBounds(450,300,100,50);
        backjb.setFont(new Font("宋体", Font.PLAIN, 32));
        backjb.setForeground(Color.blue);
        backjb.setContentAreaFilled(false);
        backjb.setBorderPainted(false);
        backjb.addActionListener(new ActionListener() { //给返回按钮设置监听方法
            @Override
            public void actionPerformed(ActionEvent e) {
                if(JOptionPane.YES_OPTION == JOptionPane.showConfirmDialog(null,"你确定要返回吗?","提问",JOptionPane.YES_NO_OPTION)){
                    frame.dispose();    //设置提示框,增加用户体验
                }
            }
        });
        layeredPane.add(backjb,JLayeredPane.MODAL_LAYER);
    }

    public void setBackground(){
        ImageIcon icon = new ImageIcon("img/9E~FI`DO7JHV$4(T`QHB5O7.png");
        icon.getImage().getScaledInstance(600,400, Image.SCALE_DEFAULT);
        imglabel = new JLabel(icon);
        imglabel.setBounds(0,0,icon.getIconWidth(),icon.getIconHeight());
        layeredPane.add(imglabel,JLayeredPane.DEFAULT_LAYER);
    }
}