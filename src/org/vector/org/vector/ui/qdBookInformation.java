/**
 * Company
 * Copyright (C) 2004-2019 All Rights Reserved.
 */
package org.vector.org.vector.ui;

import org.vector.bean.Book;
import org.vector.bean.BookType;
import org.vector.bean.Reader;
import org.vector.bean.Users;
import org.vector.daoipl.BookDaoIpl;
import org.vector.daoipl.BookTypeDaoIpl;
import org.vector.daoipl.ReaderDaoIpl;
import org.vector.org.vector.service.Check;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.table.TableModel;
import java.awt.*;
import java.awt.event.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.regex.Pattern;

/**
 * @author Administrator
 * @version $Id qdBookInformation.java, v 0.1 2019-06-01 18:36 Administrator Exp $$
 */
public class qdBookInformation {
    private JFrame newframe;
    private JScrollPane jspanel;
    private JPanel panel1,panel2;
    private JTable table;
    private JComboBox jc1,jc2;
    private JTextField selectjt;
    private JButton selectjb,backjb1,updatejb,backjb2;
    private JLabel imglabel;
    private JLabel isbnlabel,booktypelabel,booknamelabel,authorlabel,publishlabel,datelabel,numlabel,pricelabel;
    private JTextField isbnjt,booknamejt,authorjt,publishjt,datejt,numjt,pricejt;
    private TableModel tm;
    private JTabbedPane tabbedPane = new JTabbedPane();

    public qdBookInformation(JFrame frame, Users users) {
        load(frame,users);
    }

    public void load(JFrame frame,Users users){
        setFrame();
        //setBackground();
        setMenu();
        setTabb1(frame);
        setTabb2(frame);
    }

    public void setFrame(){
        newframe = new JFrame();
        newframe.setBounds(300, 150, 1200, 850);
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

    public void setMenu(){  //设置选项卡面板
        panel1 = new JPanel();
        panel1.setLayout(null);
        panel2 = new JPanel();
        panel2.setLayout(null);
        tabbedPane.add("图书信息查询",panel1);
        tabbedPane.add("图书信息修改",panel2);
        tabbedPane.setBounds(0,0,1200,35);
        tabbedPane.setFont(new Font("宋体", Font.PLAIN, 25));
        tabbedPane.setForeground(Color.MAGENTA);
        newframe.add(tabbedPane);
    }

    public void setTabb1(JFrame frame){ //选项卡1
        //最上面的两个玩意
        jc1 = new JComboBox();
        jc1.addItem("ISBN");
        jc1.addItem("类别");
        jc1.addItem("名称");
        jc1.setBounds(150,50,200,50);
        jc1.setFont(new Font("宋体", Font.PLAIN, 40));
        panel1.add(jc1);

        selectjt = new JTextField();
        selectjt.setBounds(375,50,600,50);
        selectjt.setFont(new Font("宋体", Font.PLAIN, 40));
        panel1.add(selectjt);


        //表格
        Object[] columnNames = {"ISBN","图书类型","图书名称","作者","出版社","出版日期","印刷次数","单价"};
        Object[][] rowdata;

        List<Book> list = new BookDaoIpl().selectAllBookInformation();
        rowdata = new Object[list.size()][8];
        for (int i = 0; i < list.size(); i++) {
            rowdata[i][0] = list.get(i).getISBN();
            rowdata[i][1] = list.get(i).getTypeid();
            rowdata[i][2] = list.get(i).getBookname();
            rowdata[i][3] = list.get(i).getAuthor();
            rowdata[i][4] = list.get(i).getPublish();
            rowdata[i][5] = list.get(i).getPublishdate();
            rowdata[i][6] = list.get(i).getPublishtime();
            rowdata[i][7] = list.get(i).getUnitprice();
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
        TableColumn tableColumn3 = table.getColumnModel().getColumn(2);
        tableColumn3.setPreferredWidth(150);
        TableColumn tableColumn4 = table.getColumnModel().getColumn(3);
        tableColumn4.setPreferredWidth(150);
        TableColumn tableColumn5 = table.getColumnModel().getColumn(4);
        tableColumn5.setPreferredWidth(150);
        TableColumn tableColumn6 = table.getColumnModel().getColumn(5);
        tableColumn6.setPreferredWidth(150);
        TableColumn tableColumn7 = table.getColumnModel().getColumn(6);

        jspanel = new JScrollPane(table);
        jspanel.setBounds(50, 125, 1100, 600);
        panel1.add(jspanel);

        //两个按钮
        selectjb = new JButton("查询");
        selectjb.setBounds(425,750,100,50);
        selectjb.setFont(new Font("宋体", Font.PLAIN, 32));
        selectjb.setForeground(Color.blue);
        selectjb.setContentAreaFilled(false);
        selectjb.setBorderPainted(false);
        selectjb.addActionListener(new ActionListener() {   // 给查询按钮设置监听方法
            @Override
            public void actionPerformed(ActionEvent e) {
                String type = (String) jc1.getSelectedItem();

                DefaultTableModel tableModel = (DefaultTableModel) table.getModel();    //获得表格模型
                String s1 = selectjt.getText();
                if("ISBN".equals(type)){

                    if(s1.equals("")){
                        JOptionPane.showMessageDialog(null, "查询内容为空!请输入!", "错误", JOptionPane.ERROR_MESSAGE);
                    }else{
                        tableModel.setRowCount(0);  //清空所有行
                        Book book = new Book();
                        book.setISBN(s1);
                        Book books = new BookDaoIpl().selectSingleBookByIsbn(book); //如果图书被删除,这里会报空指针异常的错误,先不修改
                        tableModel.addRow(new Object[]{
                                books.getISBN(),
                                books.getTypeid(),
                                books.getBookname(),
                                books.getAuthor(),
                                books.getPublish(),
                                books.getPublishdate(),
                                books.getPublishtime(),
                                books.getUnitprice()
                        });
                    }
                }else if("类别".equals(type)){
                    if(s1.equals("")){
                        JOptionPane.showMessageDialog(null, "查询内容为空!请输入!", "错误", JOptionPane.ERROR_MESSAGE);
                    }else{
                        tableModel.setRowCount(0);  //清空所有行
                        Book book = new Book();
                        book.setTypeid(s1);
                        List<Book> list = new BookDaoIpl().selectBookInformationByType(book);
                        for (int i = 0; i < list.size(); i++) { //添加表中的数据
                            tableModel.addRow(new Object[]{
                                    list.get(i).getISBN(),
                                    list.get(i).getTypeid(),
                                    list.get(i).getBookname(),
                                    list.get(i).getAuthor(),
                                    list.get(i).getPublish(),
                                    list.get(i).getPublishdate(),
                                    list.get(i).getPublishtime(),
                                    list.get(i).getUnitprice()
                            });
                        }
                    }
                }else{
                    if(s1.equals("")){
                        JOptionPane.showMessageDialog(null, "查询内容为空!请输入!", "错误", JOptionPane.ERROR_MESSAGE);
                    }else{
                        tableModel.setRowCount(0);  //清空所有行
                        Book book = new Book();
                        book.setBookname(s1);
                        List<Book> list = new BookDaoIpl().selectBookInformationByBookName(book);
                        for (int i = 0; i < list.size(); i++) { //添加表中的数据
                            tableModel.addRow(new Object[]{
                                    list.get(i).getISBN(),
                                    list.get(i).getTypeid(),
                                    list.get(i).getBookname(),
                                    list.get(i).getAuthor(),
                                    list.get(i).getPublish(),
                                    list.get(i).getPublishdate(),
                                    list.get(i).getPublishtime(),
                                    list.get(i).getUnitprice()
                            });
                        }
                    }
                }

            }
        });
        panel1.add(selectjb);

        backjb1 = new JButton("返回");
        backjb1.setBounds(675,750,100,50);
        backjb1.setFont(new Font("宋体", Font.PLAIN, 32));
        backjb1.setForeground(Color.blue);
        backjb1.setContentAreaFilled(false);
        backjb1.setBorderPainted(false);
        backjb1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (JOptionPane.YES_OPTION == JOptionPane.showConfirmDialog(null, "你确定要返回吗?", "提问", JOptionPane.YES_NO_OPTION)) {
                    newframe.setVisible(false);
                    frame.setVisible(true);
                }
            }
        });
        panel1.add(backjb1);
    }

    public void setTabb2(JFrame frame){ //选项卡2
        isbnlabel = new JLabel("ISBN");
        isbnlabel.setBounds(300,0,300,100);
        isbnlabel.setFont(new Font("宋体", Font.PLAIN, 40));
        isbnlabel.setForeground(Color.red);
        panel2.add(isbnlabel);

        booktypelabel = new JLabel("图书类别");
        booktypelabel.setBounds(257,75,300,100);
        booktypelabel.setFont(new Font("宋体", Font.PLAIN, 40));
        booktypelabel.setForeground(Color.red);
        panel2.add(booktypelabel);

        booknamelabel = new JLabel("图书名称");   //设置书名标签
        booknamelabel.setBounds(257,150,300,100);
        booknamelabel.setFont(new Font("宋体", Font.PLAIN, 40));
        booknamelabel.setForeground(Color.red);
        panel2.add(booknamelabel);

        authorlabel = new JLabel("作者");
        authorlabel.setBounds(300,225,300,100);
        authorlabel.setFont(new Font("宋体", Font.PLAIN, 40));
        authorlabel.setForeground(Color.red);
        panel2.add(authorlabel);

        publishlabel = new JLabel("出版社");
        publishlabel.setBounds(282,300,300,100);
        publishlabel.setFont(new Font("宋体", Font.PLAIN, 40));
        publishlabel.setForeground(Color.red);
        panel2.add(publishlabel);

        datelabel = new JLabel("出版日期");
        datelabel.setBounds(257,375,300,100);
        datelabel.setFont(new Font("宋体", Font.PLAIN, 40));
        datelabel.setForeground(Color.red);
        panel2.add(datelabel);

        numlabel = new JLabel("印刷次数");
        numlabel.setBounds(257,450,300,100);
        numlabel.setFont(new Font("宋体", Font.PLAIN, 40));
        numlabel.setForeground(Color.red);
        panel2.add(numlabel);

        pricelabel = new JLabel("单价");
        pricelabel.setBounds(300,525,300,100);
        pricelabel.setFont(new Font("宋体", Font.PLAIN, 40));
        pricelabel.setForeground(Color.red);
        panel2.add(pricelabel);

        isbnjt = new JTextField();
        isbnjt.setBounds(500,25,400,50);
        isbnjt.setFont(new Font("宋体", Font.PLAIN, 40));
        isbnjt.addKeyListener(new KeyAdapter() {   //给isbn文本框设置键盘监听,使其只能输入数字
            @Override
            public void keyTyped(KeyEvent e) {
                int temp = e.getKeyChar();
                if(temp < 48 || temp > 57){
                    e.consume();
                }
            }
        });
        panel2.add(isbnjt);

        jc2 = new JComboBox();
        List<BookType> list = new BookTypeDaoIpl().selectAllBookTypeInformation();
        for (BookType bookType:list) {
            jc2.addItem(bookType.getTypename());
        }
        jc2.setBounds(500,100,400,50);
        jc2.setFont(new Font("宋体", Font.PLAIN, 40));
        panel2.add(jc2);

        booknamejt = new JTextField();
        booknamejt.setBounds(500,175,400,50);
        booknamejt.setFont(new Font("宋体", Font.PLAIN, 40));
        panel2.add(booknamejt);

        authorjt = new JTextField();
        authorjt.setBounds(500,250,400,50);
        authorjt.setFont(new Font("宋体", Font.PLAIN, 40));
        panel2.add(authorjt);

        publishjt = new JTextField();
        publishjt.setBounds(500,325,400,50);
        publishjt.setFont(new Font("宋体", Font.PLAIN, 40));
        panel2.add(publishjt);

        datejt = new JTextField();
        datejt.setBounds(500,400,400,50);
        datejt.setFont(new Font("宋体", Font.PLAIN, 40));
        panel2.add(datejt);

        numjt = new JTextField();
        numjt.setBounds(500,475,400,50);
        numjt.setFont(new Font("宋体", Font.PLAIN, 40));
        numjt.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                int temp = e.getKeyChar();
                if(temp < 48 || temp > 57){
                    e.consume();
                }
            }
        });
        panel2.add(numjt);

        pricejt = new JTextField();
        pricejt.setBounds(500,550,400,50);
        pricejt.setFont(new Font("宋体", Font.PLAIN, 40));
        pricejt.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                int temp = e.getKeyChar();
                if(temp < 48 || temp > 57){
                    e.consume();
                }
            }
        });
        panel2.add(pricejt);

        updatejb = new JButton("修改");
        updatejb.setBounds(425,675,100,50);
        updatejb.setFont(new Font("宋体", Font.PLAIN, 32));
        updatejb.setForeground(Color.blue);
        updatejb.setContentAreaFilled(false);
        updatejb.setBorderPainted(false);
        updatejb.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Date date = null;
                Pattern p = Pattern.compile("^((\\d{2}(([02468][048])|([13579][26]))[\\-\\/\\s]?((((0?[13578])|(1[02]))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(3[01])))|(((0?[469])|(11))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(30)))|(0?2[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])))))|(\\d{2}(([02468][1235679])|([13579][01345789]))[\\-\\/\\s]?((((0?[13578])|(1[02]))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(3[01])))|(((0?[469])|(11))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(30)))|(0?2[\\-\\/\\s]?((0?[1-9])|(1[0-9])|(2[0-8]))))))?$");

                String isbn = isbnjt.getText();
                String booktype = (String) jc2.getSelectedItem();
                String bookname = booknamejt.getText();
                String author = authorjt.getText();
                String publish = publishjt.getText();
                String publishdate = datejt.getText();
                String publishtime = numjt.getText();
                String prices = pricejt.getText();

                SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
                if(isbn.equals("") || booktype.equals("") || bookname.equals("") || author.equals("") || publish.equals("") || publishdate.equals("") || publishtime.equals("") || prices.equals("")){
                    JOptionPane.showMessageDialog(null, "关键信息为空,请填写完整!", "错误", JOptionPane.ERROR_MESSAGE);
                }else{
                    int flag;
                    flag = Check.checkIsbn(isbn);
                    if(flag != 1){
                        isbnjt.setText("");
                        JOptionPane.showMessageDialog(null, "ISBN不符合规范！请重新输入!", "错误", JOptionPane.ERROR_MESSAGE);
                    }else{
                        Book book = new Book();
                        book.setISBN(isbn);
                        Book books = new BookDaoIpl().selectSingleBookByIsbn(book);
                        if(books == null){
                            isbnjt.setText("");
                            JOptionPane.showMessageDialog(null, "图书不存在!无法修改!", "错误", JOptionPane.ERROR_MESSAGE);
                        }else{
                            if(!p.matcher(datejt.getText()).matches()){
                                datejt.setText("");
                                JOptionPane.showMessageDialog(null,"出版时间格式错误!正确格式为:2012-12-06,请重新输入!","错误",JOptionPane.ERROR_MESSAGE);
                            }else{
                                if(!Check.checkNum(publishtime)){
                                    numjt.setText("");
                                    JOptionPane.showMessageDialog(null,"印刷次数不合理!","错误",JOptionPane.ERROR_MESSAGE);
                                }else{
                                    if(!Check.checkPrice(prices)){
                                        pricejt.setText("");
                                        JOptionPane.showMessageDialog(null,"书的价钱不合理!","错误",JOptionPane.ERROR_MESSAGE);
                                    }else{
                                        try {
                                            date = format.parse(datejt.getText());
                                        } catch (ParseException ex) {
                                            ex.printStackTrace();
                                        }
                                        book.setAuthor(author);
                                        book.setBookname(bookname);
                                        book.setPublish(publish);
                                        book.setPublishdate(date);
                                        book.setPublishtime(numjt.getText());
                                        book.setTypeid(booktype);
                                        book.setUnitprice(pricejt.getText());
                                        new BookDaoIpl().updateBookInformationByIsbn(book);
                                        JOptionPane.showMessageDialog(null,"修改成功!","提示",JOptionPane.INFORMATION_MESSAGE);

                                        DefaultTableModel tableModel = (DefaultTableModel) table.getModel();    //获得表格模型
                                        tableModel.setRowCount(0);  //清空表格中的所有数据,保留表头
                                        List<Book> list = new BookDaoIpl().selectAllBookInformation();
                                        for (int i = 0; i < list.size(); i++) {
                                            tableModel.addRow(new Object[]{
                                                    list.get(i).getISBN(),
                                                    list.get(i).getTypeid(),
                                                    list.get(i).getBookname(),
                                                    list.get(i).getAuthor(),
                                                    list.get(i).getPublish(),
                                                    list.get(i).getPublishdate(),
                                                    list.get(i).getPublishtime(),
                                                    list.get(i).getUnitprice()
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
        panel2.add(updatejb);

        backjb2 = new JButton("返回");
        backjb2.setBounds(675,675,100,50);
        backjb2.setFont(new Font("宋体", Font.PLAIN, 32));
        backjb2.setForeground(Color.blue);
        backjb2.setContentAreaFilled(false);
        backjb2.setBorderPainted(false);
        backjb2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(JOptionPane.YES_OPTION == JOptionPane.showConfirmDialog(null,"你确定要返回吗?","提问",JOptionPane.YES_NO_OPTION)){
                    newframe.setVisible(false);
                    frame.setVisible(true);
                }
            }
        });
        panel2.add(backjb2);
    }

    public void setBackground(){
        ImageIcon icon = new ImageIcon("img/HLW]4@)OI[AM0Y(CW8J2L{F.png");
        icon.setImage(icon.getImage().getScaledInstance(1200, 850, Image.SCALE_DEFAULT));
        imglabel = new JLabel(icon);
        imglabel.setBounds(0, 0, icon.getIconWidth(), icon.getIconHeight());
        newframe.add(imglabel);
    }
}