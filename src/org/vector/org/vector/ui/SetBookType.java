/**
 * Company
 * Copyright (C) 2004-2019 All Rights Reserved.
 */
package org.vector.org.vector.ui;

import org.vector.bean.BookType;
import org.vector.bean.Users;
import org.vector.daoipl.BookDaoIpl;
import org.vector.daoipl.BookTypeDaoIpl;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import java.awt.*;
import java.awt.event.*;
import java.util.List;

/**
 * @author Administrator
 * @version $Id SetBookType.java, v 0.1 2019-06-09 13:36 Administrator Exp $$
 */
public class SetBookType {
    private JFrame newframe;
    private JLayeredPane layeredPane = new JLayeredPane();
    private JLabel imglabel;
    private JLabel booktypelabel,booktypeidlabel,booktypenamelabel;
    private JTextField booktypejt,booktypeidjt,booktypenamejt;
    private JButton selectjb,addjb,updatejb,deletejb,backjb;
    private JTable table;
    private JScrollPane jspanel;
    private TableModel tm;

    public SetBookType(JFrame frame, Users users) {
        load(frame,users);
    }

    public void load(JFrame frame,Users users){
        setFrame();
        setBackground();
        setHead();
        setBody();
        setBottom();
        setButton(frame);
    }

    public void setFrame(){
        newframe = new JFrame("图书类别设置");
        newframe.setBounds(180,60,1000,700);
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
        booktypelabel = new JLabel("图书类型");
        booktypelabel.setBounds(50,25,300,75);
        booktypelabel.setFont(new Font("宋体", Font.PLAIN, 30));
        booktypelabel.setForeground(Color.red);
        layeredPane.add(booktypelabel,JLayeredPane.MODAL_LAYER);

        booktypejt = new JTextField();
        booktypejt.setBounds(200,45,600,35);
        booktypejt.setFont(new Font("宋体", Font.PLAIN, 25));
        booktypejt.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                int temp = e.getKeyChar();
                if(temp < 48 || temp > 57){
                    e.consume();
                }
            }
        });
        layeredPane.add(booktypejt,JLayeredPane.MODAL_LAYER);

        selectjb = new JButton("查询");
        selectjb.setBounds(825,45,100,35);
        selectjb.setFont(new Font("宋体", Font.PLAIN, 25));
        selectjb.setForeground(Color.green);
        selectjb.setContentAreaFilled(false);
        selectjb.setBorderPainted(false);
        selectjb.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                DefaultTableModel tableModel = (DefaultTableModel) table.getModel();    //获得表格模型

                String booktypeid = booktypejt.getText();
                if(booktypeid.equals("")){
                    JOptionPane.showMessageDialog(null, "查询内容为空!请输入!", "错误", JOptionPane.ERROR_MESSAGE);
                }else{
                    tableModel.setRowCount(0);  //清空所有行
                    BookType bookType = new BookType();
                    bookType.setId(booktypeid);
                    BookType bookTypes = new BookTypeDaoIpl().selectSingleIfmaById(bookType);

                    tableModel.addRow(new Object[]{
                            bookTypes.getId(),
                            bookTypes.getTypename()
                    });
                }
            }
        });
        layeredPane.add(selectjb,JLayeredPane.MODAL_LAYER);
    }

    public void setBody(){

        Object[] columnNames = {"图书类型编号","图书类型名称"};//表头(列名)
        Object[][] rowdata;

        List<BookType> list = new BookTypeDaoIpl().selectAllBookTypeInformation();
        rowdata = new Object[list.size()][2];
        for (int i = 0; i < list.size(); i++) {
            rowdata[i][0] = list.get(i).getId();
            rowdata[i][1] = list.get(i).getTypename();
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

        jspanel = new JScrollPane(table);
        jspanel.setBounds(75, 100,850, 300);
        layeredPane.add(jspanel, JLayeredPane.PALETTE_LAYER);

    }

    public void setBottom(){
        booktypeidlabel = new JLabel("图书类型编号");
        booktypeidlabel.setBounds(150,400,300,100);
        booktypeidlabel.setFont(new Font("宋体", Font.PLAIN, 35));
        booktypeidlabel.setForeground(Color.red);
        layeredPane.add(booktypeidlabel,JLayeredPane.MODAL_LAYER);

        booktypenamelabel = new JLabel("图书类型名称");
        booktypenamelabel.setBounds(150,475,300,100);
        booktypenamelabel.setFont(new Font("宋体", Font.PLAIN, 35));
        booktypenamelabel.setForeground(Color.red);
        layeredPane.add(booktypenamelabel,JLayeredPane.MODAL_LAYER);

        booktypeidjt = new JTextField();
        booktypeidjt.setBounds(500,425,350,50);
        booktypeidjt.setFont(new Font("宋体", Font.PLAIN, 35));
        booktypeidjt.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                int temp = e.getKeyChar();
                if(temp < 48 || temp > 57){
                    e.consume();
                }
            }
        });
        layeredPane.add(booktypeidjt,JLayeredPane.MODAL_LAYER);

        booktypenamejt = new JTextField();
        booktypenamejt.setBounds(500,500,350,50);
        booktypenamejt.setFont(new Font("宋体", Font.PLAIN, 35));
        layeredPane.add(booktypenamejt,JLayeredPane.MODAL_LAYER);
    }

    public void setButton(JFrame frame){
        addjb = new JButton("添加");
        addjb.setBounds(175,575,100,50);
        addjb.setFont(new Font("宋体", Font.PLAIN, 30));
        addjb.setForeground(Color.green);
        addjb.setContentAreaFilled(false);
        addjb.setBorderPainted(false);
        addjb.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String booktypeid = booktypeidjt.getText();
                String booktypename = booktypenamejt.getText();

                if(booktypeid.equals("") || booktypename.equals("")){
                    JOptionPane.showMessageDialog(null,"关键信息为空!请填写完整!","错误",JOptionPane.ERROR_MESSAGE);
                }else{
                    BookType bookType = new BookType();
                    bookType.setId(booktypeid);
                    bookType.setTypename(booktypename);

                    BookType bookTypes = new BookTypeDaoIpl().selectSingleInformationById(bookType);
                    if(bookTypes != null){
                        booktypeidjt.setText("");
                        booktypenamejt.setText("");
                        JOptionPane.showMessageDialog(null, "该类型已存在!请勿重复添加!", "错误", JOptionPane.ERROR_MESSAGE);
                    }else{
                        new BookTypeDaoIpl().addInformation(bookType);
                        JOptionPane.showMessageDialog(null, "添加成功！", "提示", JOptionPane.INFORMATION_MESSAGE);

                        DefaultTableModel tableModel = (DefaultTableModel) table.getModel();    //获得表格模型
                        tableModel.setRowCount(0);  //清空表格中的所有数据,保留表头

                        List<BookType> list = new BookTypeDaoIpl().selectAllBookTypeInformation();
                        for (int i = 0; i < list.size(); i++) {
                            tableModel.addRow(new Object[]{
                                    list.get(i).getId(),
                                    list.get(i).getTypename(),
                            });
                        }
                    }
                }
            }
        });
        layeredPane.add(addjb,JLayeredPane.MODAL_LAYER);

        updatejb = new JButton("修改");
        updatejb.setBounds(350,575,100,50);
        updatejb.setFont(new Font("宋体", Font.PLAIN, 30));
        updatejb.setForeground(Color.green);
        updatejb.setContentAreaFilled(false);
        updatejb.setBorderPainted(false);
        updatejb.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String booktypeid = booktypeidjt.getText();
                String booktypename = booktypenamejt.getText();

                if(booktypeid.equals("") || booktypename.equals("")){
                    JOptionPane.showMessageDialog(null,"关键信息为空!请填写完整!","错误",JOptionPane.ERROR_MESSAGE);
                }else{
                    BookType bookType = new BookType();
                    bookType.setId(booktypeid);
                    bookType.setTypename(booktypename);

                    BookType bookTypes = new BookTypeDaoIpl().selectSingleIfmaById(bookType);
                    if(bookTypes == null){
                        booktypeidjt.setText("");
                        JOptionPane.showMessageDialog(null, "该图书类型不存在!无法修改！", "错误", JOptionPane.ERROR_MESSAGE);
                    }else{
                        new BookTypeDaoIpl().updateSingleIfmaById(bookType);
                        JOptionPane.showMessageDialog(null, "修改成功！", "提示", JOptionPane.INFORMATION_MESSAGE);

                        DefaultTableModel tableModel = (DefaultTableModel) table.getModel();    //获得表格模型
                        tableModel.setRowCount(0);  //清空表格中的所有数据,保留表头

                        List<BookType> list = new BookTypeDaoIpl().selectAllBookTypeInformation();
                        for (int i = 0; i < list.size(); i++) {
                            tableModel.addRow(new Object[]{
                                    list.get(i).getId(),
                                    list.get(i).getTypename(),
                            });
                        }
                    }
                }

            }
        });
        layeredPane.add(updatejb,JLayeredPane.MODAL_LAYER);

        deletejb = new JButton("删除");
        deletejb.setBounds(525,575,100,50);
        deletejb.setFont(new Font("宋体", Font.PLAIN, 30));
        deletejb.setForeground(Color.green);
        deletejb.setContentAreaFilled(false);
        deletejb.setBorderPainted(false);
        deletejb.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String booktypeid = booktypeidjt.getText();
                String booktypename = booktypenamejt.getText();

                if(booktypeid.equals("") || booktypename.equals("")){
                    JOptionPane.showMessageDialog(null,"关键信息为空!请填写完整!","错误",JOptionPane.ERROR_MESSAGE);
                }else{
                    BookType bookType = new BookType();
                    bookType.setId(booktypeid);
                    bookType.setTypename(booktypename);

                    BookType bookTypes = new BookTypeDaoIpl().selectSingleIfmaById(bookType);
                    if(bookTypes == null){
                        booktypeidjt.setText("");
                        JOptionPane.showMessageDialog(null, "该图书类型不存在!无法删除！", "错误", JOptionPane.ERROR_MESSAGE);
                    }else{
                        new BookTypeDaoIpl().deleteSingleIfmaById(bookType);
                        JOptionPane.showMessageDialog(null, "删除成功！", "提示", JOptionPane.INFORMATION_MESSAGE);

                        DefaultTableModel tableModel = (DefaultTableModel) table.getModel();    //获得表格模型
                        tableModel.setRowCount(0);  //清空表格中的所有数据,保留表头

                        List<BookType> list = new BookTypeDaoIpl().selectAllBookTypeInformation();
                        for (int i = 0; i < list.size(); i++) {
                            tableModel.addRow(new Object[]{
                                    list.get(i).getId(),
                                    list.get(i).getTypename(),
                            });
                        }
                    }
                }
                }
        });
        layeredPane.add(deletejb,JLayeredPane.MODAL_LAYER);

        backjb = new JButton("返回");
        backjb.setBounds(700,575,100,50);
        backjb.setFont(new Font("宋体", Font.PLAIN, 30));
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
        //H6MD`}7KR`BV7R84O[1LYV0.png
        ImageIcon icon = new ImageIcon("img/H6MD`}7KR`BV7R84O[1LYV0.png");
        icon.setImage(icon.getImage().getScaledInstance(1000,700,Image.SCALE_DEFAULT));
        imglabel = new JLabel(icon);
        imglabel.setBounds(0,0,icon.getIconWidth(),icon.getIconHeight());
        layeredPane.add(imglabel,JLayeredPane.DEFAULT_LAYER);
    }
}