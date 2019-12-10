/**
 * Company
 * Copyright (C) 2004-2019 All Rights Reserved.
 */
package org.vector.org.vector.ui;

import org.vector.bean.Users;
import org.vector.daoipl.UsersDaoIpl;
import sun.swing.UIAction;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.table.TableModel;
import java.awt.*;
import java.awt.event.*;
import java.util.List;

/**
 * @author Administrator
 * @version $Id SelectUsers.java, v 0.1 2019-06-13 20:02 Administrator Exp $$
 */
public class SelectUsers {
    private JFrame newframe;
    private JLayeredPane layeredPane = new JLayeredPane();
    private JLabel imglabel;
    private JComboBox jc;
    private JTextField jt;
    private JTable table;
    private TableModel tm;
    private JButton selectjb,backjb;
    private JScrollPane jspanel;
    public SelectUsers(JFrame frame, Users users) {
        load(frame);
    }

    public void load(JFrame frame){
        setFrame();
        setBackground();
        setBody();
        setButton(frame);
    }

    public void setFrame(){
        newframe = new JFrame();
        newframe.setBounds(180,60,800,700);
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

    public void setBody(){
        jc = new JComboBox();
        jc.addItem("用户名");
        jc.addItem("身份证号");
        jc.setBounds(75,50,150,50);
        jc.setFont(new Font("宋体", Font.PLAIN, 30));
        layeredPane.add(jc,JLayeredPane.MODAL_LAYER);

        jt = new JTextField();
        jt.setBounds(250,50,475,50);
        jt.setFont(new Font("宋体", Font.PLAIN, 30));
        layeredPane.add(jt,JLayeredPane.MODAL_LAYER);

        Object[] columnNames = {"用户名","密码","身份证号"};//表头(列名)
        Object[][] rowdata;

        List<Users> list = new UsersDaoIpl().selectAllUser();
        rowdata = new Object[list.size()][3];
        for (int i = 0; i < list.size(); i++) {
            rowdata[i][0] = list.get(i).getUsername();
            rowdata[i][1] = list.get(i).getPassword();
            rowdata[i][2] = list.get(i).getUseridcard();
        }

        tm = new DefaultTableModel(rowdata, columnNames);    //利用表格模型来创建表格
        table = new JTable(tm);


        table.setRowHeight(25); //设置行高
        table.setPreferredScrollableViewportSize(new Dimension(800, 200));
        table.setFont(new Font("宋体", Font.PLAIN, 20));//表格字体
        table.setForeground(Color.magenta);//表格字体颜色

        table.getTableHeader().setFont(new Font("宋体", Font.PLAIN, 22)); //设置表头字体
        table.getTableHeader().setForeground(Color.blue);//设置表头字体颜色
        table.getTableHeader().setReorderingAllowed(false); //禁止用户拖动表头使之重新排序
        table.getTableHeader().setResizingAllowed(false);   //禁止用户手动扩大表头

        TableColumn tableColumn1 = table.getColumnModel().getColumn(0);
        tableColumn1.setPreferredWidth(100);
        TableColumn tableColumn2 = table.getColumnModel().getColumn(1);
        tableColumn2.setPreferredWidth(100);
        TableColumn tableColumn3 = table.getColumnModel().getColumn(2);
        tableColumn3.setPreferredWidth(100);

        jspanel = new JScrollPane(table);
        jspanel.setBounds(50, 125, 700, 450);
        layeredPane.add(jspanel, JLayeredPane.PALETTE_LAYER);
    }

    public void setButton(JFrame frame){
        selectjb = new JButton("查询");
        selectjb.setBounds(250,600,100,50);
        selectjb.setFont(new Font("宋体", Font.PLAIN, 32));
        selectjb.setForeground(Color.green);
        selectjb.setContentAreaFilled(false);
        selectjb.setBorderPainted(false);
        selectjb.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                DefaultTableModel tableModel = (DefaultTableModel) table.getModel();    //获得表格模型
                String type = (String) jc.getSelectedItem();
                String con = jt.getText();
                if(type.equals("用户名")){
                    if(con.equals("")){
                        JOptionPane.showMessageDialog(null, "查询内容为空!请输入!", "错误", JOptionPane.ERROR_MESSAGE);
                    }else{
                        tableModel.setRowCount(0);
                        Users users = new Users();
                        users.setUsername(con);
                        Users user = new UsersDaoIpl().selectSgUserByName(users);

                        tableModel.addRow(new Object[]{
                                user.getUsername(),
                                user.getPassword(),
                                user.getUseridcard()
                        });
                    }
                }else{
                    if(con.equals("")){
                        JOptionPane.showMessageDialog(null, "查询内容为空!请输入!", "错误", JOptionPane.ERROR_MESSAGE);
                    }else{
                        tableModel.setRowCount(0);
                        Users users = new Users();
                        users.setUseridcard(con);

                        List<Users> list = new UsersDaoIpl().selectUserByIdcard(users);

                        for (int i = 0; i < list.size(); i++) {
                            tableModel.addRow(new Object[]{
                                    list.get(i).getUsername(),
                                    list.get(i).getPassword(),
                                    list.get(i).getUseridcard()
                            });
                        }
                    }
                }
            }
        });
        layeredPane.add(selectjb,JLayeredPane.MODAL_LAYER);

        backjb = new JButton("返回");
        backjb.setBounds(450,600,100,50);
        backjb.setFont(new Font("宋体", Font.PLAIN, 32));
        backjb.setForeground(Color.green);
        backjb.setContentAreaFilled(false);
        backjb.setBorderPainted(false);
        backjb.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (JOptionPane.YES_OPTION == JOptionPane.showConfirmDialog(null, "你确定要返回吗?", "提问", JOptionPane.YES_NO_OPTION)) {
                    newframe.setVisible(false);
                    frame.setVisible(true);
                }
            }
        });
        layeredPane.add(backjb,JLayeredPane.MODAL_LAYER);
    }

    public void setBackground(){
        ImageIcon icon = new ImageIcon("img/9{ST)RP8VS2[M`AF7GO{F{E.png");
        icon.setImage(icon.getImage().getScaledInstance(800,700,Image.SCALE_DEFAULT));
        imglabel = new JLabel(icon);
        imglabel.setBounds(0,0,icon.getIconWidth(),icon.getIconHeight());
        layeredPane.add(imglabel,JLayeredPane.DEFAULT_LAYER);
    }
}