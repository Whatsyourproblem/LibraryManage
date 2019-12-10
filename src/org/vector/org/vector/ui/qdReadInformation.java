/**
 * Company
 * Copyright (C) 2004-2019 All Rights Reserved.
 */
package org.vector.org.vector.ui;

import org.vector.bean.Reader;
import org.vector.bean.Users;
import org.vector.daoipl.ReaderDaoIpl;
import org.vector.org.vector.service.Check;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.table.TableModel;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Administrator
 * @version $Id qdReadInformation.java, v 0.1 2019-05-29 20:12 Administrator Exp $$
 */
public class qdReadInformation {
    private JFrame newframe;
    private JLabel imglabel;
    private JLayeredPane layeredPane = new JLayeredPane();
    private JComboBox jc1, jc2;      //两个下拉框
    private JTextField selectjt, readeridjt, namejt, agejt, phonejt, deptjt, idcardjt;
    private JLabel readeridlabel, namelabel, typelabel, sexlabel, agelabel, phonelabel, deptlabel, idcardlabel;
    private JButton selectjb, updatejb, resetjb, backjb;
    private JRadioButton manjr, womanjr;
    private JTable table;
    private JScrollPane jspanel;
    private TableModel tm;


    public qdReadInformation(JFrame frame, Users users) {
        load(frame, users);
    }

    public void load(JFrame frame, Users users) {
        setFrame();
        setBackground();
        setTop();
        setMiddle(users);
        setBotton();
        setButton(frame);
    }

    public void setFrame() { //设置框架
        newframe = new JFrame();
        newframe.setBounds(300, 150, 1200, 850);
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
                newframe.setLocation(p.x + e.getX() - origin.x, p.y + e.getY() - origin.y);
            }
        });

    }

    public void setTop() {   //设置最上方
        jc1 = new JComboBox();
        jc1.addItem("读者类型");
        jc1.addItem("读者姓名");
        jc1.addItem("身份证号");
        jc1.addItem("读者编号");
        jc1.setBounds(100, 35, 200, 50);
        jc1.setFont(new Font("宋体", Font.PLAIN, 40));
        layeredPane.add(jc1, JLayeredPane.PALETTE_LAYER);

        selectjt = new JTextField();
        selectjt.setBounds(325, 35, 650, 50);
        selectjt.setFont(new Font("宋体", Font.PLAIN, 40));
        layeredPane.add(selectjt, JLayeredPane.PALETTE_LAYER);

        selectjb = new JButton("查询");
        selectjb.setBounds(995, 35, 125, 50);
        selectjb.setFont(new Font("宋体", Font.PLAIN, 40));
        selectjb.setForeground(Color.blue);
        selectjb.setContentAreaFilled(false);
        selectjb.setBorderPainted(false);
        selectjb.addActionListener(new ActionListener() {   //给查询设置监听方法
            @Override
            public void actionPerformed(ActionEvent e) {    //更新表格中的数据
                String type = (String) jc1.getSelectedItem();
                String s1 = selectjt.getText();
                DefaultTableModel tableModel = (DefaultTableModel) table.getModel();    //获得表格模型
                if ("读者类型".equals(type)) {
                    if (s1.equals("")) {
                        JOptionPane.showMessageDialog(null, "查询内容为空!请输入!", "错误", JOptionPane.ERROR_MESSAGE);
                    } else {
                        tableModel.setRowCount(0);
                        Reader reader1 = new Reader();
                        reader1.setType(s1);
                        List<Reader> list = new ReaderDaoIpl().selectAllReaderByType(reader1);
                        for (int i = 0; i < list.size(); i++) { //添加表中的数据
                            tableModel.addRow(new Object[]{
                                    list.get(i).getReaderid(),
                                    list.get(i).getType(),
                                    list.get(i).getName(),
                                    list.get(i).getAge(),
                                    list.get(i).getSex(),
                                    list.get(i).getPhone(),
                                    list.get(i).getIdcard(),
                                    list.get(i).getDept(),
                                    list.get(i).getPosition()
                            });
                        }
                    }

                } else if ("读者姓名".equals(type)) {
                    if (s1.equals("")) {
                        JOptionPane.showMessageDialog(null, "查询内容为空!请输入!", "错误", JOptionPane.ERROR_MESSAGE);
                    } else {
                        tableModel.setRowCount(0);
                        String s2 = selectjt.getText();
                        Reader reader2 = new Reader();
                        reader2.setName(s2);
                        List<Reader> list = new ReaderDaoIpl().selectAllReaderByName(reader2);
                        for (int i = 0; i < list.size(); i++) {
                            tableModel.addRow(new Object[]{
                                    list.get(i).getReaderid(),
                                    list.get(i).getType(),
                                    list.get(i).getName(),
                                    list.get(i).getAge(),
                                    list.get(i).getSex(),
                                    list.get(i).getPhone(),
                                    list.get(i).getIdcard(),
                                    list.get(i).getDept(),
                                    list.get(i).getPosition()
                            });
                        }
                    }

                } else if ("读者编号".equals(type)) {
                    if (s1.equals("")) {
                        JOptionPane.showMessageDialog(null, "查询内容为空!请输入!", "错误", JOptionPane.ERROR_MESSAGE);
                    } else {
                        tableModel.setRowCount(0);
                        String s3 = selectjt.getText();
                        Reader reader3 = new Reader();
                        reader3.setReaderid(s3);
                        Reader reader = new ReaderDaoIpl().selectSingleReaderById(reader3);
                        reader.getReaderid();
                        reader.getType();
                        reader.getName();
                        tableModel.addRow(new Object[]{
                                reader.getReaderid(),
                                reader.getType(),
                                reader.getName(),
                                reader.getAge(),
                                reader.getSex(),
                                reader.getPhone(),
                                reader.getIdcard(),
                                reader.getDept(),
                                reader.getPosition()
                        });
                    }

                } else {
                    if (s1.equals("")) {
                        JOptionPane.showMessageDialog(null, "查询内容为空!请输入!", "错误", JOptionPane.ERROR_MESSAGE);
                    } else {
                        tableModel.setRowCount(0);
                        String s4 = selectjt.getText();
                        Reader reader4 = new Reader();
                        reader4.setIdcard(s4);
                        List<Reader> list = new ReaderDaoIpl().selectAllReaderById(reader4);
                        for (int i = 0; i < list.size(); i++) {
                            tableModel.addRow(new Object[]{
                                    list.get(i).getReaderid(),
                                    list.get(i).getType(),
                                    list.get(i).getName(),
                                    list.get(i).getAge(),
                                    list.get(i).getSex(),
                                    list.get(i).getPhone(),
                                    list.get(i).getIdcard(),
                                    list.get(i).getDept(),
                                    list.get(i).getPosition()
                            });
                        }
                    }

                }
            }
        });
        layeredPane.add(selectjb, JLayeredPane.PALETTE_LAYER);
    }

    public void setMiddle(Users users) {        //设置中间部分,主要内容为添加一张可下拉的表格

        Object[] columnNames = {"读者编号", "读者类型", "读者姓名", "年龄", "性别", "联系电话", "身份证号", "所在院系", "借阅状态"};//表头(列名)
        Object[][] rowdata;

        List<Reader> list = new ReaderDaoIpl().selectAllReader();
        rowdata = new Object[list.size()][9];
        for (int i = 0; i < list.size(); i++) {
            rowdata[i][0] = list.get(i).getReaderid();
            rowdata[i][1] = list.get(i).getType();
            rowdata[i][2] = list.get(i).getName();
            rowdata[i][3] = list.get(i).getAge();
            rowdata[i][4] = list.get(i).getSex();
            rowdata[i][5] = list.get(i).getPhone();
            rowdata[i][6] = list.get(i).getIdcard();
            rowdata[i][7] = list.get(i).getDept();
            rowdata[i][8] = list.get(i).getPosition();
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

        //美化表格,设置列宽
        TableColumn tableColumn1 = table.getColumnModel().getColumn(0);
        tableColumn1.setPreferredWidth(65);
        TableColumn tableColumn2 = table.getColumnModel().getColumn(1);
        tableColumn2.setPreferredWidth(65);
        TableColumn tableColumn3 = table.getColumnModel().getColumn(2);
        tableColumn3.setPreferredWidth(65);
        TableColumn tableColumn4 = table.getColumnModel().getColumn(3);
        tableColumn4.setPreferredWidth(25);
        TableColumn tableColumn5 = table.getColumnModel().getColumn(4);
        tableColumn5.setPreferredWidth(25);
        TableColumn tableColumn6 = table.getColumnModel().getColumn(5);
        tableColumn6.setPreferredWidth(100);
        TableColumn tableColumn7 = table.getColumnModel().getColumn(6);
        tableColumn7.setPreferredWidth(150);
        TableColumn tableColumn8 = table.getColumnModel().getColumn(7);
        tableColumn8.setPreferredWidth(150);
        TableColumn tableColumn9 = table.getColumnModel().getColumn(8);
        tableColumn9.setPreferredWidth(65);

        jspanel = new JScrollPane(table);
        jspanel.setBounds(25, 100, 1150, 300);
        layeredPane.add(jspanel, JLayeredPane.PALETTE_LAYER);
    }

    public void setBotton() {    //设置下面的部分 主要是各标签以及按钮
        readeridlabel = new JLabel("读者编号");
        readeridlabel.setBounds(50, 425, 300, 100);
        readeridlabel.setFont(new Font("宋体", Font.PLAIN, 40));
        readeridlabel.setForeground(Color.RED);
        layeredPane.add(readeridlabel, JLayeredPane.MODAL_LAYER);

        readeridjt = new JTextField();
        readeridjt.setBounds(225, 450, 300, 50);
        readeridjt.setFont(new Font("宋体", Font.PLAIN, 40));
        readeridjt.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                int temp = e.getKeyChar();
                if (temp < 48 || temp > 57) {
                    e.consume();
                }
            }
        });
        layeredPane.add(readeridjt, JLayeredPane.MODAL_LAYER);

        namelabel = new JLabel("姓名");   //设置姓名标签
        namelabel.setBounds(675, 425, 200, 100);
        namelabel.setFont(new Font("宋体", Font.PLAIN, 40));
        namelabel.setForeground(Color.red);
        layeredPane.add(namelabel, JLayeredPane.MODAL_LAYER);

        namejt = new JTextField();  //设置姓名文本框
        namejt.setBounds(825, 450, 300, 50);
        namejt.setFont(new Font("宋体", Font.PLAIN, 40));
        layeredPane.add(namejt, JLayeredPane.MODAL_LAYER);

        typelabel = new JLabel("读者类型"); //设置文本类型标签
        typelabel.setBounds(50, 500, 300, 100);
        typelabel.setFont(new Font("宋体", Font.PLAIN, 40));
        typelabel.setForeground(Color.red);
        layeredPane.add(typelabel, JLayeredPane.MODAL_LAYER);

        jc2 = new JComboBox();   //设置文本类型下拉框
        jc2.addItem("学生");
        jc2.addItem("教师");
        jc2.addItem("管理员");
        jc2.setBounds(225, 525, 300, 50);
        jc2.setFont(new Font("宋体", Font.PLAIN, 40));
        layeredPane.add(jc2, JLayeredPane.MODAL_LAYER);

        sexlabel = new JLabel("性别");    //设置性别标签
        sexlabel.setBounds(675, 500, 200, 100);
        sexlabel.setFont(new Font("宋体", Font.PLAIN, 40));
        sexlabel.setForeground(Color.red);
        layeredPane.add(sexlabel, JLayeredPane.MODAL_LAYER);

        manjr = new JRadioButton("男", true);  //设置性别单选框
        manjr.setBounds(875, 525, 125, 50);
        manjr.setFont(new Font("宋体", Font.PLAIN, 40));
        manjr.setForeground(Color.orange);
        manjr.setContentAreaFilled(false);
        manjr.setBorderPainted(false);
        layeredPane.add(manjr, JLayeredPane.MODAL_LAYER);

        womanjr = new JRadioButton("女");
        womanjr.setBounds(1000, 525, 125, 50);
        womanjr.setFont(new Font("宋体", Font.PLAIN, 40));
        womanjr.setForeground(Color.orange);
        womanjr.setContentAreaFilled(false);
        womanjr.setBorderPainted(false);
        layeredPane.add(womanjr, JLayeredPane.MODAL_LAYER);

        ButtonGroup group = new ButtonGroup();
        group.add(manjr);
        group.add(womanjr);

        agelabel = new JLabel("年龄");    //设置年龄标签
        agelabel.setBounds(100, 575, 200, 100);
        agelabel.setFont(new Font("宋体", Font.PLAIN, 40));
        agelabel.setForeground(Color.red);
        layeredPane.add(agelabel, JLayeredPane.MODAL_LAYER);

        agejt = new JTextField();   //设置年龄框
        agejt.setBounds(225, 600, 300, 50);
        agejt.setFont(new Font("宋体", Font.PLAIN, 40));
        agejt.addKeyListener(new KeyAdapter() { //给年龄框设置监听,使其只能输入数字
            @Override
            public void keyTyped(KeyEvent e) {
                int temp = e.getKeyChar();
                if (temp < 48 || temp > 57) {
                    e.consume();
                }
            }
        });
        layeredPane.add(agejt, JLayeredPane.MODAL_LAYER);

        phonelabel = new JLabel("电话");  //电话标签
        phonelabel.setBounds(675, 575, 200, 100);
        phonelabel.setFont(new Font("宋体", Font.PLAIN, 40));
        phonelabel.setForeground(Color.red);
        layeredPane.add(phonelabel, JLayeredPane.MODAL_LAYER);

        phonejt = new JTextField(); //电话文本框
        phonejt.setBounds(825, 600, 300, 50);
        phonejt.setFont(new Font("宋体", Font.PLAIN, 40));
        phonejt.addKeyListener(new KeyAdapter() {   //给电话框设置监听方法使其只能输入数字
            @Override
            public void keyTyped(KeyEvent e) {
                int temp = e.getKeyChar();
                if (temp < 48 || temp > 57) {
                    e.consume();
                }
            }
        });
        layeredPane.add(phonejt, JLayeredPane.MODAL_LAYER);

        deptlabel = new JLabel("所在部门"); //设置部门标签
        deptlabel.setBounds(50, 650, 300, 100);
        deptlabel.setFont(new Font("宋体", Font.PLAIN, 40));
        deptlabel.setForeground(Color.red);
        layeredPane.add(deptlabel, JLayeredPane.MODAL_LAYER);

        deptjt = new JTextField();  //设置部门文本框
        deptjt.setBounds(225, 675, 300, 50);
        deptjt.setFont(new Font("宋体", Font.PLAIN, 40));
        layeredPane.add(deptjt, JLayeredPane.MODAL_LAYER);

        idcardlabel = new JLabel("身份证号");   //身份证号标签
        idcardlabel.setBounds(650, 650, 300, 100);
        idcardlabel.setFont(new Font("宋体", Font.PLAIN, 40));
        idcardlabel.setForeground(Color.red);
        layeredPane.add(idcardlabel, JLayeredPane.MODAL_LAYER);

        idcardjt = new JTextField();    //身份证号文本框
        idcardjt.setBounds(825, 675, 300, 50);
        idcardjt.setFont(new Font("宋体", Font.PLAIN, 40));
        idcardjt.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                int temp = e.getKeyChar();
                if (temp < 48 || temp > 57) {
                    e.consume();
                }
            }
        });
        layeredPane.add(idcardjt, JLayeredPane.MODAL_LAYER);
    }

    public void setButton(JFrame frame) {
        updatejb = new JButton("修改");
        updatejb.setBounds(325, 775, 100, 50);
        updatejb.setFont(new Font("宋体", Font.PLAIN, 32));
        updatejb.setForeground(Color.green);
        updatejb.setContentAreaFilled(false);
        updatejb.setBorderPainted(false);
        updatejb.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String id = readeridjt.getText();
                String name = namejt.getText();
                String type = (String) jc2.getSelectedItem(); //过去下拉框中选中的
                String sex;
                if (manjr.isSelected()) {
                    sex = manjr.getText();
                } else {
                    sex = womanjr.getText();
                }
                String ages = agejt.getText();
                String phone = phonejt.getText();
                String idcard = idcardjt.getText();
                String dept = deptjt.getText();
                if (id.equals("") || name.equals("") || type.equals("") || sex.equals("") || ages.equals("") || phone.equals("") || idcard.equals("") || dept.equals("")) {
                    JOptionPane.showMessageDialog(null, "关键信息为空,请填写完整!", "错误", JOptionPane.ERROR_MESSAGE);
                } else {

                    int age = Integer.parseInt(ages.equals("") ? "0" : ages);   //将年龄转换成int型

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

                    int num = Check.checkId(id);
                    if (num != 1) {  //判断id是否符合规范 8位数字
                        readeridjt.setText("");
                        JOptionPane.showMessageDialog(null, "读者编号不符合规范！请重新输入!", "错误", JOptionPane.ERROR_MESSAGE);
                    } else {
                        if (reader == null) { //用户数据不存在,无法修改
                            readeridjt.setText("");
                            JOptionPane.showMessageDialog(null, "用户不存在!无法修改!", "错误", JOptionPane.ERROR_MESSAGE);
                        } else {//判断你要秀的数据(年龄)
                            int flag;
                            flag = Check.checkAge(readers.getAge());
                            if (flag != 1) {  //年龄不符合规范
                                agejt.setText("");
                                JOptionPane.showMessageDialog(null, "年龄太奇怪！请重新输入!", "错误", JOptionPane.ERROR_MESSAGE);
                            } else {  //年龄符合,判断手机号是否符合规范
                                int temp;
                                temp = Check.checkPhone(readers.getPhone());
                                if (temp != 1) {      //手机号不符合规范
                                    phonejt.setText("");
                                    JOptionPane.showMessageDialog(null, "手机号不符合规范！请重新输入!", "错误", JOptionPane.ERROR_MESSAGE);
                                } else { //判断身份证号
                                    int mnm = Check.checkIdcard(readers.getIdcard());
                                    if (mnm != 1) {   //身份证不符合标准
                                        idcardjt.setText("");
                                        JOptionPane.showMessageDialog(null, "身份证号不符合规范！请重新输入!", "错误", JOptionPane.ERROR_MESSAGE);
                                    } else {  //身份证符合
                                        new ReaderDaoIpl().updateReader(readers);
                                        JOptionPane.showMessageDialog(null, "修改成功！", "提示", JOptionPane.INFORMATION_MESSAGE);

                                        //动态修改表中的数据
                                        DefaultTableModel tableModel = (DefaultTableModel) table.getModel();    //获得表格模型
                                        tableModel.setRowCount(0);  //清空表格中的所有数据,保留表头
                                        List<Reader> list = new ReaderDaoIpl().selectAllReader();
                                        for (int i = 0; i < list.size(); i++) {
                                            tableModel.addRow(new Object[]{
                                                    list.get(i).getReaderid(),
                                                    list.get(i).getType(),
                                                    list.get(i).getName(),
                                                    list.get(i).getAge(),
                                                    list.get(i).getSex(),
                                                    list.get(i).getPhone(),
                                                    list.get(i).getIdcard(),
                                                    list.get(i).getDept(),
                                                    list.get(i).getPosition()
                                            });
                                        }
                                    }
                                }
                            }
                        }
                    }

                }
            }
        });
        layeredPane.add(updatejb, JLayeredPane.MODAL_LAYER);

        resetjb = new JButton("重置");
        resetjb.setBounds(525, 775, 100, 50);
        resetjb.setFont(new Font("宋体", Font.PLAIN, 32));
        resetjb.setForeground(Color.green);
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
                JOptionPane.showMessageDialog(null, "重置成功!", "提示", JOptionPane.INFORMATION_MESSAGE);
            }
        });
        layeredPane.add(resetjb, JLayeredPane.MODAL_LAYER);

        backjb = new JButton("返回");
        backjb.setBounds(725, 775, 100, 50);
        backjb.setFont(new Font("宋体", Font.PLAIN, 32));
        backjb.setForeground(Color.green);
        backjb.setContentAreaFilled(false);
        backjb.setBorderPainted(false);
        backjb.addActionListener(new ActionListener() { //给返回案件设置监听事件
            @Override
            public void actionPerformed(ActionEvent e) {
                if (JOptionPane.YES_OPTION == JOptionPane.showConfirmDialog(null, "你确定要返回吗?", "提问", JOptionPane.YES_NO_OPTION)) {
                    newframe.setVisible(false);
                    frame.setVisible(true);
                }
            }
        });
        layeredPane.add(backjb, JLayeredPane.MODAL_LAYER);
    }

    public void setBackground() { //设置背景图片
        ImageIcon icon = new ImageIcon("img/)L%(3IN$Q9]%@}45_ZXA2PY.png");
        icon.setImage(icon.getImage().getScaledInstance(1200, 850, Image.SCALE_DEFAULT));
        imglabel = new JLabel(icon);
        imglabel.setBounds(0, 0, icon.getIconWidth(), icon.getIconHeight());
        layeredPane.add(imglabel, JLayeredPane.DEFAULT_LAYER);
    }

}