/**
 * Company
 * Copyright (C) 2004-2019 All Rights Reserved.
 */
package org.vector.org.vector.ui;

import org.vector.bean.BookType;
import org.vector.bean.ReaderType;
import org.vector.bean.Users;
import org.vector.daoipl.BookTypeDaoIpl;
import org.vector.daoipl.ReaderTypeDaoIpl;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import java.awt.*;
import java.awt.event.*;
import java.util.List;

/**
 * @author Administrator
 * @version $Id SetReaderType.java, v 0.1 2019-06-10 20:19 Administrator Exp $$
 */
public class SetReaderType {    //用户类别设置
    private JFrame newframe;
    private JLayeredPane layeredPane = new JLayeredPane();
    private JLabel imglabel;
    private JLabel jl1,jl2,jl3,jl4,jl5;
    private JTextField jt1,jt2,jt3,jt4,jt5;
    private JButton jb1,jb2,jb3,jb4,jb5;
    private JTable table;
    private JScrollPane jspanel;
    private TableModel tm;

    public SetReaderType(JFrame frame, Users users) {
        load(frame);
    }
    public void load(JFrame frame){
        setFrame();
        setBackground();
        setHead();
        setBody();
        setBottom();
        setButton(frame);
    }

    public void setFrame(){
        newframe = new JFrame("读者类别设置");
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
        jl1 = new JLabel("读者类型");
        jl1.setBounds(50,25,300,75);
        jl1.setFont(new Font("宋体", Font.PLAIN, 30));
        jl1.setForeground(Color.red);
        layeredPane.add(jl1,JLayeredPane.MODAL_LAYER);

        jt1 = new JTextField();
        jt1.setBounds(200,45,600,35);
        jt1.setFont(new Font("宋体", Font.PLAIN, 25));
        jt1.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                int temp = e.getKeyChar();
                if(temp < 48 || temp > 57){
                    e.consume();
                }
            }
        });
        layeredPane.add(jt1,JLayeredPane.MODAL_LAYER);

        jb1 = new JButton("查询");
        jb1.setBounds(825,45,100,35);
        jb1.setFont(new Font("宋体", Font.PLAIN, 25));
        jb1.setForeground(Color.green);
        jb1.setContentAreaFilled(false);
        jb1.setBorderPainted(false);
        jb1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                DefaultTableModel tableModel = (DefaultTableModel) table.getModel();    //获得表格模型

                String readertypeid = jt1.getText();
                if(readertypeid.equals("")){
                    JOptionPane.showMessageDialog(null, "查询内容为空!请输入!", "错误", JOptionPane.ERROR_MESSAGE);
                }else{
                    tableModel.setRowCount(0);  //清空所有行

                    ReaderType readerType = new ReaderType();
                    readerType.setId(readertypeid);
                    ReaderType readerTypes = new ReaderTypeDaoIpl().selectSingleInformationById(readerType);

                    tableModel.addRow(new Object[]{
                            readerTypes.getId(),
                            readerTypes.getName(),
                            readerTypes.getMaxborrownum(),
                            readerTypes.getLimitation()
                    });
                }
            }
        });
        layeredPane.add(jb1,JLayeredPane.MODAL_LAYER);
    }

    public void setBody(){

        Object[] columnNames = {"读者类型编号","读者类型名称","可借图书数量","可借图书期限"};//表头(列名)
        Object[][] rowdata;

        List<ReaderType> list = new ReaderTypeDaoIpl().selectAllInformation();
        rowdata = new Object[list.size()][4];
        for (int i = 0; i < list.size(); i++) {
            rowdata[i][0] = list.get(i).getId();
            rowdata[i][1] = list.get(i).getName();
            rowdata[i][2] = list.get(i).getMaxborrownum();
            rowdata[i][3] = list.get(i).getLimitation();
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
        jl2 = new JLabel("读者类型编号");
        jl2.setBounds(50,425,300,75);
        jl2.setFont(new Font("宋体", Font.PLAIN, 30));
        jl2.setForeground(Color.red);
        layeredPane.add(jl2,JLayeredPane.MODAL_LAYER);

        jl4 = new JLabel("可借图书数量");
        jl4.setBounds(50,500,300,75);
        jl4.setFont(new Font("宋体", Font.PLAIN, 30));
        jl4.setForeground(Color.red);
        layeredPane.add(jl4,JLayeredPane.MODAL_LAYER);

        jt2 = new JTextField();
        jt2.setBounds(250,445,225,35);
        jt2.setFont(new Font("宋体", Font.PLAIN, 25));
        jt2.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                int temp = e.getKeyChar();
                if(temp < 48 || temp > 57){
                    e.consume();
                }
            }
        });
        layeredPane.add(jt2,JLayeredPane.MODAL_LAYER);

        jt4 = new JTextField();
        jt4.setBounds(250,520,225,35);
        jt4.setFont(new Font("宋体", Font.PLAIN, 25));
        jt4.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                int temp = e.getKeyChar();
                if(temp < 48 || temp > 57){
                    e.consume();
                }
            }
        });
        layeredPane.add(jt4,JLayeredPane.MODAL_LAYER);

        jl3 = new JLabel("读者类型名称");
        jl3.setBounds(500,425,300,75);
        jl3.setFont(new Font("宋体", Font.PLAIN, 30));
        jl3.setForeground(Color.red);
        layeredPane.add(jl3,JLayeredPane.MODAL_LAYER);

        jl5 = new JLabel("可借图书期限");
        jl5.setBounds(500,500,300,75);
        jl5.setFont(new Font("宋体", Font.PLAIN, 30));
        jl5.setForeground(Color.red);
        layeredPane.add(jl5,JLayeredPane.MODAL_LAYER);

        jt3 = new JTextField();
        jt3.setBounds(700,445,225,35);
        jt3.setFont(new Font("宋体", Font.PLAIN, 25));
        layeredPane.add(jt3,JLayeredPane.MODAL_LAYER);

        jt5 = new JTextField();
        jt5.setBounds(700,520,225,35);
        jt5.setFont(new Font("宋体", Font.PLAIN, 25));
        jt5.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                int temp = e.getKeyChar();
                if(temp < 48 || temp > 57){
                    e.consume();
                }
            }
        });
        layeredPane.add(jt5,JLayeredPane.MODAL_LAYER);
    }

    public void setButton(JFrame frame){
        jb2 = new JButton("添加");
        jb2.setBounds(225,600,100,50);
        jb2.setFont(new Font("宋体", Font.PLAIN, 30));
        jb2.setForeground(Color.green);
        jb2.setContentAreaFilled(false);
        jb2.setBorderPainted(false);
        jb2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String readertypeid = jt2.getText();
                String readertypename = jt3.getText();
                String maxborrowbooknum = jt4.getText();
                String limitation = jt5.getText();

                if(readertypeid.equals("") || readertypename.equals("") || maxborrowbooknum.equals("") || limitation.equals("")){
                    JOptionPane.showMessageDialog(null,"关键信息为空!请填写完整!","错误",JOptionPane.ERROR_MESSAGE);
                }else{
                    ReaderType readerType = new ReaderType();
                    readerType.setId(readertypeid);
                    readerType.setName(readertypename);
                    ReaderType readerTypes = new ReaderTypeDaoIpl().selectSingleInformationById(readerType);

                    if(readerTypes != null){
                        jt2.setText("");
                        jt3.setText("");
                        JOptionPane.showMessageDialog(null, "该类型已存在!请勿重复添加!", "错误", JOptionPane.ERROR_MESSAGE);
                    }else{
                        readerType.setMaxborrownum(maxborrowbooknum);
                        readerType.setLimitation(limitation);

                        new ReaderTypeDaoIpl().addInformation(readerType);

                        JOptionPane.showMessageDialog(null, "添加成功！", "提示", JOptionPane.INFORMATION_MESSAGE);

                        DefaultTableModel tableModel = (DefaultTableModel) table.getModel();    //获得表格模型
                        tableModel.setRowCount(0);  //清空表格中的所有数据,保留表头

                        List<ReaderType> list = new ReaderTypeDaoIpl().selectAllInformation();
                        for (int i = 0; i < list.size(); i++) {
                            tableModel.addRow(new Object[]{
                                    list.get(i).getId(),
                                    list.get(i).getName(),
                                    list.get(i).getMaxborrownum(),
                                    list.get(i).getLimitation()
                            });
                        }
                    }
                }
            }
        });
        layeredPane.add(jb2,JLayeredPane.MODAL_LAYER);

        jb3 = new JButton("修改");
        jb3.setBounds(375,600,100,50);
        jb3.setFont(new Font("宋体", Font.PLAIN, 30));
        jb3.setForeground(Color.green);
        jb3.setContentAreaFilled(false);
        jb3.setBorderPainted(false);
        jb3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String readertypeid = jt2.getText();
                String readertypename = jt3.getText();
                String maxborrowbooknum = jt4.getText();
                String limitation = jt5.getText();

                if(readertypeid.equals("") || readertypename.equals("") || maxborrowbooknum.equals("") || limitation.equals("")){
                    JOptionPane.showMessageDialog(null,"关键信息为空!请填写完整!","错误",JOptionPane.ERROR_MESSAGE);
                }else{
                    ReaderType readerType = new ReaderType();
                    readerType.setId(readertypeid);
                    readerType.setName(readertypename);
                    ReaderType readerTypes = new ReaderTypeDaoIpl().selectSingleInformationById(readerType);

                    if(readerTypes == null){
                        jt2.setText("");
                        jt3.setText("");
                        JOptionPane.showMessageDialog(null, "该读者类型不存在!无法修改!", "错误", JOptionPane.ERROR_MESSAGE);
                    }else{
                        readerType.setMaxborrownum(maxborrowbooknum);
                        readerType.setLimitation(limitation);
                        new ReaderTypeDaoIpl().updateSingleInformation(readerType);

                        JOptionPane.showMessageDialog(null, "修改成功！", "提示", JOptionPane.INFORMATION_MESSAGE);

                        DefaultTableModel tableModel = (DefaultTableModel) table.getModel();    //获得表格模型
                        tableModel.setRowCount(0);  //清空表格中的所有数据,保留表头

                        List<ReaderType> list = new ReaderTypeDaoIpl().selectAllInformation();
                        for (int i = 0; i < list.size(); i++) {
                            tableModel.addRow(new Object[]{
                                    list.get(i).getId(),
                                    list.get(i).getName(),
                                    list.get(i).getMaxborrownum(),
                                    list.get(i).getLimitation()
                            });
                        }
                    }
                }
            }
        });
        layeredPane.add(jb3,JLayeredPane.MODAL_LAYER);

        jb4 = new JButton("删除");
        jb4.setBounds(525,600,100,50);
        jb4.setFont(new Font("宋体", Font.PLAIN, 30));
        jb4.setForeground(Color.green);
        jb4.setContentAreaFilled(false);
        jb4.setBorderPainted(false);
        jb4.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String readertypeid = jt2.getText();
                String readertypename = jt3.getText();
                String maxborrowbooknum = jt4.getText();
                String limitation = jt5.getText();

                if(readertypeid.equals("") || readertypename.equals("") || maxborrowbooknum.equals("") || limitation.equals("")){
                    JOptionPane.showMessageDialog(null,"关键信息为空!请填写完整!","错误",JOptionPane.ERROR_MESSAGE);
                }else{
                    ReaderType readerType = new ReaderType();
                    readerType.setId(readertypeid);
                    readerType.setName(readertypename);
                    ReaderType readerTypes = new ReaderTypeDaoIpl().selectSingleInformationById(readerType);

                    if(readerTypes == null){
                        jt2.setText("");
                        jt3.setText("");
                        JOptionPane.showMessageDialog(null, "该读者类型不存在!无法删除!", "错误", JOptionPane.ERROR_MESSAGE);
                    }else{
                        readerType.setMaxborrownum(maxborrowbooknum);
                        readerType.setLimitation(limitation);
                        new ReaderTypeDaoIpl().deleteSingleInformation(readerType);

                        JOptionPane.showMessageDialog(null, "删除成功！", "提示", JOptionPane.INFORMATION_MESSAGE);

                        DefaultTableModel tableModel = (DefaultTableModel) table.getModel();    //获得表格模型
                        tableModel.setRowCount(0);  //清空表格中的所有数据,保留表头

                        List<ReaderType> list = new ReaderTypeDaoIpl().selectAllInformation();
                        for (int i = 0; i < list.size(); i++) {
                            tableModel.addRow(new Object[]{
                                    list.get(i).getId(),
                                    list.get(i).getName(),
                                    list.get(i).getMaxborrownum(),
                                    list.get(i).getLimitation()
                            });
                        }
                    }
                }
            }
        });
        layeredPane.add(jb4,JLayeredPane.MODAL_LAYER);

        jb5 = new JButton("返回");
        jb5.setBounds(675,600,100,50);
        jb5.setFont(new Font("宋体", Font.PLAIN, 30));
        jb5.setForeground(Color.green);
        jb5.setContentAreaFilled(false);
        jb5.setBorderPainted(false);
        jb5.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (JOptionPane.YES_OPTION == JOptionPane.showConfirmDialog(null, "你确定要返回吗?", "提问", JOptionPane.YES_NO_OPTION)) {
                    newframe.setVisible(false);
                    frame.setVisible(true);
                }
            }
        });
        layeredPane.add(jb5,JLayeredPane.MODAL_LAYER);
    }

    public void setBackground(){
        ImageIcon icon = new ImageIcon("img/1E3GOCD2BCYT65_B2J(X6(R.png");
        icon.setImage(icon.getImage().getScaledInstance(1000,700,Image.SCALE_DEFAULT));
        imglabel = new JLabel(icon);
        imglabel.setBounds(0,0,icon.getIconWidth(),icon.getIconHeight());
        layeredPane.add(imglabel,JLayeredPane.DEFAULT_LAYER);
    }
}