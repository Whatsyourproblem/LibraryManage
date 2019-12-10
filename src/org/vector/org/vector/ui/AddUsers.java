/**
 * Company
 * Copyright (C) 2004-2019 All Rights Reserved.
 */
package org.vector.org.vector.ui;

import org.vector.bean.ReaderType;
import org.vector.bean.Users;
import org.vector.daoipl.ReaderTypeDaoIpl;
import org.vector.daoipl.UsersDaoIpl;
import org.vector.org.vector.service.Check;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.List;

/**
 * @author Administrator
 * @version $Id AddUsers.java, v 0.1 2019-06-12 20:31 Administrator Exp $$
 */
public class AddUsers {
    private JFrame newframe;
    private JLayeredPane layeredPane = new JLayeredPane();
    private JLabel imglabel;
    private JLabel usernamelabel,passwordlabel,usertypelabel,idcardlabel;
    private JTextField usernamejt,idcardlabeljt;
    private JPasswordField passwordjt;
    private JButton addjb,backjb;
    private JComboBox jc;

    public AddUsers(JFrame frame, Users users) {
        load(frame,users);
    }

    public void load(JFrame frame,Users users){
        setFrame();
        setBackground();
        setConsist();
        setButton(frame);
    }
    public void setFrame(){
        newframe = new JFrame();
        newframe.setBounds(180,60,600,400);
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

    public void setConsist(){
        usernamelabel = new JLabel("用户名");
        usernamelabel.setBounds(50,25,300,100);
        usernamelabel.setFont(new Font("宋体", Font.PLAIN, 40));
        usernamelabel.setForeground(Color.red);
        layeredPane.add(usernamelabel,JLayeredPane.MODAL_LAYER);

        passwordlabel = new JLabel("密码");
        passwordlabel.setBounds(67,100,300,100);
        passwordlabel.setFont(new Font("宋体", Font.PLAIN, 40));
        passwordlabel.setForeground(Color.red);
        layeredPane.add(passwordlabel,JLayeredPane.MODAL_LAYER);

        idcardlabel = new JLabel("身份证号");
        idcardlabel.setBounds(25,175,300,100);
        idcardlabel.setFont(new Font("宋体", Font.PLAIN, 40));
        idcardlabel.setForeground(Color.red);
        layeredPane.add(idcardlabel,JLayeredPane.MODAL_LAYER);

        usernamejt = new JTextField();
        usernamejt.setBounds(225,50,300,50);
        usernamejt.setFont(new Font("宋体", Font.PLAIN, 40));
        layeredPane.add(usernamejt,JLayeredPane.MODAL_LAYER);

        passwordjt = new JPasswordField();
        passwordjt.setBounds(225,125,300,50);
        passwordjt.setFont(new Font("宋体", Font.PLAIN, 40));
        layeredPane.add(passwordjt,JLayeredPane.MODAL_LAYER);

        idcardlabeljt = new JTextField();
        idcardlabeljt.setBounds(225,200,300,50);
        idcardlabeljt.setFont(new Font("宋体", Font.PLAIN, 40));
        idcardlabeljt.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                int temp = e.getKeyChar();
                if(temp < 48 || temp > 57){
                    e.consume();
                }
            }
        });
        layeredPane.add(idcardlabeljt,JLayeredPane.MODAL_LAYER);

    }

    public void setButton(JFrame frame){
        addjb = new JButton("添加");
        addjb.setBounds(150,300,100,50);
        addjb.setFont(new Font("宋体", Font.PLAIN, 30));
        addjb.setForeground(Color.green);
        addjb.setContentAreaFilled(false);
        addjb.setBorderPainted(false);
        addjb.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = usernamejt.getText();
                String password = passwordjt.getText();
                String idcard = idcardlabeljt.getText();

                if(username.equals("") || password.equals("") || idcard.equals("")){
                    JOptionPane.showMessageDialog(null,"关键信息为空!请重新输入!","错误",JOptionPane.ERROR_MESSAGE);
                }else{
                    int flag;
                    flag = Check.checkIdcard(idcard);
                    if(flag != 1){
                        idcardlabeljt.setText("");
                        JOptionPane.showMessageDialog(null,"身份证格式错误!请重新输入!","错误",JOptionPane.ERROR_MESSAGE);
                    }else{
                        Users users = new Users();
                        users.setUsername(username);
                        Users user = new UsersDaoIpl().selectSgUserByName(users);

                        if(user != null){
                            usernamejt.getText();
                            JOptionPane.showMessageDialog(null,"该用户已经存在!无法添加!","错误",JOptionPane.ERROR_MESSAGE);
                        }else{
                            users.setPassword(password);
                            users.setUseridcard(idcard);

                            new UsersDaoIpl().addUser(users);
                            JOptionPane.showMessageDialog(null,"添加成功!","提示",JOptionPane.INFORMATION_MESSAGE);
                        }
                    }
                }
            }
        });
        layeredPane.add(addjb,JLayeredPane.MODAL_LAYER);

        backjb = new JButton("返回");
        backjb.setBounds(350,300,100,50);
        backjb.setFont(new Font("宋体", Font.PLAIN, 30));
        backjb.setForeground(Color.green);
        backjb.setContentAreaFilled(false);
        backjb.setBorderPainted(false);
        backjb.addActionListener(new ActionListener() {
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
        ImageIcon icon = new ImageIcon("img/V5E%_1KU)WCMS1TLFB9HQND.png");
        icon.setImage(icon.getImage().getScaledInstance(600,400,Image.SCALE_DEFAULT));
        imglabel = new JLabel(icon);
        imglabel.setBounds(0,0,icon.getIconWidth(),icon.getIconHeight());
        layeredPane.add(imglabel,JLayeredPane.DEFAULT_LAYER);
    }
}