/**
 * Company
 * Copyright (C) 2004-2019 All Rights Reserved.
 */
package org.vector.org.vector.ui;

import org.vector.bean.Users;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Set;

/**
 * @author Administrator
 * @version $Id MangerLogin.java, v 0.1 2019-05-28 13:19 Administrator Exp $$
 */
public class MangerLogin {  //管理员界面
    private JFrame frame;
    private JMenuBar jmbar;
    private JMenu jm1,jm2,jm3,jm4,jm5;
    private JMenuItem item1,item2,item3,item4,item5,item6,item7,item8,item9,item10,item11,item12,item13;
    private JLabel imglabel;

    public MangerLogin(Users users) {
        load(users);
    }

    public void load(Users users){
        setFrame();
        setMenuBar(users);
        setBackground();
    }

    public void setFrame(){     //设置主框架
        frame = new JFrame();
        frame.setBounds(180,60,1600,900);
        frame.setUndecorated(true);
        frame.setResizable(false);
        frame.setVisible(true);

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

    public void setMenuBar(Users users){       //设置菜单

        jmbar = new JMenuBar();

        jm1 = new JMenu("读者信息管理");
        item1 = new JMenuItem("读者信息添加");
        item1.setFont(new Font("宋体", Font.PLAIN, 20));
        item1.setForeground(Color.RED);
        item1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.setVisible(false);
                new AddReadInformation(frame,users);
            }
        });

        item2 = new JMenuItem("读者信息的查询与修改");
        item2.setFont(new Font("宋体", Font.PLAIN, 20));
        item2.setForeground(Color.RED);
        item2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.setVisible(false);
                new qdReadInformation(frame,users);
            }
        });

        jm1.add(item1);
        jm1.add(item2);
        jm1.setFont(new Font("宋体", Font.PLAIN, 25));
        jm1.setForeground(Color.MAGENTA);

        jm2 = new JMenu("图书信息管理");
        item3 = new JMenuItem("图书信息添加");
        item3.setFont(new Font("宋体", Font.PLAIN, 20));
        item3.setForeground(Color.RED);
        item3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.setVisible(false);
                new AddBookInformation(frame,users);
            }
        });

        item4 = new JMenuItem("图书信息查询与修改");
        item4.setFont(new Font("宋体", Font.PLAIN, 20));
        item4.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.setVisible(false);
                new qdBookInformation(frame,users);
            }
        });
        item4.setForeground(Color.RED);

        jm2.add(item3);
        jm2.add(item4);
        jm2.setFont(new Font("宋体", Font.PLAIN, 25));
        jm2.setForeground(Color.MAGENTA);

        jm3 = new JMenu("图书借阅管理");
        item5 = new JMenuItem("图书借阅");
        item5.setFont(new Font("宋体", Font.PLAIN, 20));
        item5.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.setVisible(false);
                new BookBorrowInformation(frame,users);
            }
        });
        item5.setForeground(Color.RED);

        item6 = new JMenuItem("图书归还");
        item6.setFont(new Font("宋体", Font.PLAIN, 20));
        item6.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.setVisible(false);
                new BookReturnInformation(frame,users);
            }
        });
        item6.setForeground(Color.RED);

        jm3.add(item5);
        jm3.add(item6);
        jm3.setFont(new Font("宋体", Font.PLAIN, 25));
        jm3.setForeground(Color.MAGENTA);

        jm4 = new JMenu("基础信息管理");
        item7 = new JMenuItem("图书类别设置");
        item7.setFont(new Font("宋体", Font.PLAIN, 20));
        item7.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.setVisible(false);
                new SetBookType(frame,users);
            }
        });
        item7.setForeground(Color.RED);

        item8 = new JMenuItem("读者类别设置");
        item8.setFont(new Font("宋体", Font.PLAIN, 20));
        item8.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.setVisible(false);
                new SetReaderType(frame,users);
            }
        });
        item8.setForeground(Color.RED);

        item9 = new JMenuItem("罚金设置");
        item9.setFont(new Font("宋体", Font.PLAIN, 20));
        item9.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.setVisible(false);
                new Fineset(frame,users);
            }
        });
        item9.setForeground(Color.RED);

        jm4.add(item7);
        jm4.add(item8);
        jm4.add(item9);
        jm4.setFont(new Font("宋体", Font.PLAIN, 25));
        jm4.setForeground(Color.MAGENTA);

        jm5 = new JMenu("用户管理");
        item10 = new JMenuItem("添加用户");
        item10.setFont(new Font("宋体", Font.PLAIN, 20));
        item10.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.setVisible(false);
                new AddUsers(frame,users);
            }
        });
        item10.setForeground(Color.RED);

        item11 = new JMenuItem("删除用户");
        item11.setFont(new Font("宋体", Font.PLAIN, 20));
        item11.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.setVisible(false);
                new DeleteUsers(frame,users);
            }
        });
        item11.setForeground(Color.RED);

        item12 = new JMenuItem("查找用户信息");
        item12.setFont(new Font("宋体", Font.PLAIN, 20));
        item12.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.setVisible(false);
                new SelectUsers(frame,users);
            }
        });
        item12.setForeground(Color.RED);

        jm5.add(item10);
        jm5.add(item11);
        jm5.add(item12);
        jm5.setFont(new Font("宋体", Font.PLAIN, 25));
        jm5.setForeground(Color.MAGENTA);

        jmbar.add(jm1);
        jmbar.add(jm2);
        jmbar.add(jm3);
        jmbar.add(jm4);
        jmbar.add(jm5);

        frame.setJMenuBar(jmbar);
    }

    public void setBackground(){
        ImageIcon icon = new ImageIcon("img/6]PKD}1W73CF1(4R54`~KL6.png");
        icon.setImage(icon.getImage().getScaledInstance(1600,900,Image.SCALE_DEFAULT));
        imglabel = new JLabel(icon);
        imglabel.setBounds(0,0,icon.getIconWidth(),icon.getIconHeight());
        frame.add(imglabel);
    }
}