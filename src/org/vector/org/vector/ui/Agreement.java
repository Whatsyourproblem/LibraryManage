/**
 * Company
 * Copyright (C) 2004-2019 All Rights Reserved.
 */
package org.vector.org.vector.ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/**
 * @author Administrator
 * @version $Id Agreement.java, v 0.1 2019-05-28 18:23 Administrator Exp $$
 */
public class Agreement {    //同意条款的界面
    private JFrame frame;
    private JLayeredPane layeredPane = new JLayeredPane();
    private JLabel titlelabel,imglabel;
    private JButton okjb;

    public Agreement() {
        load();
    }

    public void load(){
        setFrame();
        setBackground();
        setLabel();
        setButton();
    }

    public void setFrame(){ //设置框体
        frame = new JFrame();
        frame.setLayeredPane(layeredPane);
        frame.setUndecorated(true);
        frame.setResizable(false);
        frame.setBounds(300,500,600,300);
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

    public void setLabel(){
        titlelabel = new JLabel("无定天下第一,战盟是个**!");
        titlelabel.setBounds(50,10,500,200);
        titlelabel.setFont(new Font("宋体", Font.PLAIN, 40));
        titlelabel.setForeground(Color.blue);
        layeredPane.add(titlelabel,JLayeredPane.MODAL_LAYER);
    }

    public void setButton(){
        okjb = new JButton("确定");
        okjb.setBounds(200,100,200,200);
        okjb.setFont(new Font("宋体", Font.PLAIN, 40));
        okjb.setForeground(Color.pink);
        okjb.setContentAreaFilled(false);
        okjb.setBorderPainted(false);
        okjb.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
            }
        });
        layeredPane.add(okjb,JLayeredPane.MODAL_LAYER);
    }

    public void setBackground() {    //设置背景图片
        ImageIcon icon = new ImageIcon("img/4Z4C3HBEA6FNA@OOB@W@TDH.png");
        icon.getImage().getScaledInstance(600,300,Image.SCALE_DEFAULT);
        imglabel = new JLabel(icon);
        imglabel.setBounds(0,0,icon.getIconWidth(),icon.getIconHeight());
        layeredPane.add(imglabel,JLayeredPane.DEFAULT_LAYER);
    }
}