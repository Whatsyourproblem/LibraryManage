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
 * @version $Id DeleteUsers.java, v 0.1 2019-06-13 19:09 Administrator Exp $$
 */
public class DeleteUsers {
    private JFrame newframe;
    private JLayeredPane layeredPane = new JLayeredPane();
    private JLabel imglabel;
    private JLabel readernamelabel,idcardlabel;
    private JTextField readernamejt,idcardlabeljt;
    private JButton deletejb,backjb;

    public DeleteUsers(JFrame frame, Users users) {
        load(frame);
    }

    public void load(JFrame frame){
        setFrame();
        setBackground();
        setConsist();
        setButton(frame);
    }

    public void setFrame(){
        newframe = new JFrame();
        newframe.setBounds(180,60,600,300);
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
        readernamelabel = new JLabel("用户名");
        readernamelabel.setBounds(75,25,300,100);
        readernamelabel.setFont(new Font("宋体", Font.PLAIN, 40));
        readernamelabel.setForeground(Color.red);
        layeredPane.add(readernamelabel,JLayeredPane.MODAL_LAYER);

        readernamejt = new JTextField();
        readernamejt.setBounds(225,50,300,50);
        readernamejt.setFont(new Font("宋体", Font.PLAIN, 40));
        layeredPane.add(readernamejt,JLayeredPane.MODAL_LAYER);

        idcardlabel = new JLabel("身份证号");
        idcardlabel.setBounds(55,100,300,100);
        idcardlabel.setFont(new Font("宋体", Font.PLAIN, 40));
        idcardlabel.setForeground(Color.red);
        layeredPane.add(idcardlabel,JLayeredPane.MODAL_LAYER);

        idcardlabeljt = new JTextField();
        idcardlabeljt.setBounds(225,125,300,50);
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
        deletejb = new JButton("删除");
        deletejb.setBounds(150,200,100,50);
        deletejb.setFont(new Font("宋体", Font.PLAIN, 30));
        deletejb.setForeground(Color.green);
        deletejb.setContentAreaFilled(false);
        deletejb.setBorderPainted(false);
        deletejb.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = readernamejt.getText();
                String idcard = idcardlabeljt.getText();

                if(username.equals("") || idcard.equals("")){
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

                        if(user == null){
                            readernamejt.setText("");
                            JOptionPane.showMessageDialog(null,"该用户不存在无法删除","错误",JOptionPane.ERROR_MESSAGE);
                        }else{
                            users.setUseridcard(idcard);
                            new UsersDaoIpl().deleteSingleUsers(users);

                            JOptionPane.showMessageDialog(null,"删除成功!","提示",JOptionPane.INFORMATION_MESSAGE);
                        }
                    }
                }
            }
        });
        layeredPane.add(deletejb,JLayeredPane.MODAL_LAYER);

        backjb = new JButton("返回");
        backjb.setBounds(350,200,100,50);
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
        ImageIcon icon = new ImageIcon("img/1G20F~K[DG~%~O2M@]2YPPU.png");
        icon.setImage(icon.getImage().getScaledInstance(600,300,Image.SCALE_DEFAULT));
        imglabel = new JLabel(icon);
        imglabel.setBounds(0,0,icon.getIconWidth(),icon.getIconHeight());
        layeredPane.add(imglabel,JLayeredPane.DEFAULT_LAYER);
    }
}