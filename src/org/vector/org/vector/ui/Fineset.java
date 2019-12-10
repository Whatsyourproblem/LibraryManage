/**
 * Company
 * Copyright (C) 2004-2019 All Rights Reserved.
 */
package org.vector.org.vector.ui;

import org.vector.bean.Fine;
import org.vector.bean.Users;
import org.vector.daoipl.FineDaoIpl;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/**
 * @author Administrator
 * @version $Id Fineset.java, v 0.1 2019-06-09 18:34 Administrator Exp $$
 */
public class Fineset {
    private JFrame newframe;
    private JLayeredPane layeredPane = new JLayeredPane();
    private JLabel imglabel;
    private JLabel jl1,jl2;
    private JTextField jt;
    private JButton setjb,backjb;

    public Fineset(JFrame frame, Users users) {
        load(frame);
    }

    public void load(JFrame frame){
        setFrame();
        setBackground();
        setHead();
        setBottom(frame);
    }

    public void setFrame(){
        newframe = new JFrame("罚金设置");
        newframe.setBounds(180,60,800,200);
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

    public void setHead(){
        jl1 = new JLabel("罚金设置:");
        jl1.setBounds(75,10,300,100);
        jl1.setFont(new Font("宋体", Font.PLAIN, 40));
        jl1.setForeground(Color.red);
        layeredPane.add(jl1,JLayeredPane.MODAL_LAYER);

        jt = new JTextField();
        jt.setBounds(275,35,300,50);
        jt.setFont(new Font("宋体", Font.PLAIN, 40));
        jt.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                int temp = e.getKeyChar();
                if(temp < 48 || temp >57){
                    e.consume();
                }
            }
        });
        layeredPane.add(jt,JLayeredPane.MODAL_LAYER);

        jl2 = new JLabel("元/天");
        jl2.setBounds(600,10,300,100);
        jl2.setFont(new Font("宋体", Font.PLAIN, 40));
        jl2.setForeground(Color.red);
        layeredPane.add(jl2,JLayeredPane.MODAL_LAYER);
    }

    public void setBottom(JFrame frame){
        setjb = new JButton("设置");
        setjb.setBounds(250,115,100,50);
        setjb.setFont(new Font("宋体", Font.PLAIN, 32));
        setjb.setForeground(Color.green);
        setjb.setContentAreaFilled(false);
        setjb.setBorderPainted(false);
        setjb.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String fineset = jt.getText();
                if(fineset.equals("")){
                    JOptionPane.showMessageDialog(null, "关键信息为空!请填写完整!", "错误", JOptionPane.ERROR_MESSAGE);
                }else{
                    Fine fine = new Fine();
                    fine.setFineset(fineset);

                    new FineDaoIpl().update(fine);
                    JOptionPane.showMessageDialog(null,"设置成功!","提示",JOptionPane.INFORMATION_MESSAGE);
                }
            }
        });
        layeredPane.add(setjb,JLayeredPane.MODAL_LAYER);

        backjb = new JButton("返回");
        backjb.setBounds(450,115,100,50);
        backjb.setFont(new Font("宋体", Font.PLAIN, 32));
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
        //@W@2FRZ(B4EA4@46BAPNIW8.png
        ImageIcon icon = new ImageIcon("img/@W@2FRZ(B4EA4@46BAPNIW8.png");
        icon.setImage(icon.getImage().getScaledInstance(800,200,Image.SCALE_DEFAULT));
        imglabel = new JLabel(icon);
        imglabel.setBounds(0,0,icon.getIconWidth(),icon.getIconHeight());
        layeredPane.add(imglabel,JLayeredPane.DEFAULT_LAYER);
    }
}