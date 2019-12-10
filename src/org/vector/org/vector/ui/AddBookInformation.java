/**
 * Company
 * Copyright (C) 2004-2019 All Rights Reserved.
 */
package org.vector.org.vector.ui;

import org.vector.bean.Book;
import org.vector.bean.BookType;
import org.vector.bean.Users;
import org.vector.daoipl.BookDaoIpl;
import org.vector.daoipl.BookTypeDaoIpl;
import org.vector.org.vector.service.Check;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.regex.Pattern;

/**
 * @author Administrator
 * @version $Id AddBookInformation.java, v 0.1 2019-06-01 11:07 Administrator Exp $$
 */
public class AddBookInformation {   //图书信息添加
    private JFrame newframe;
    private JLayeredPane layeredPane = new JLayeredPane();
    private JLabel imglabel;
    private JLabel isbnlabel,booktypelabel,booknamelabel,authorlabel,publishlabel,publishdatelabel,numlabel,pricelabel;
    private JTextField isbnjt,booknamejt,authorjt,publishjt,publishdatejt,numjt,pricejt;
    private JComboBox jc;
    private JButton okjb,resetjb,backjb;
    public AddBookInformation(JFrame frame, Users users) {
        load(frame,users);
    }

    public void load(JFrame frame,Users users){
        setFrame();
        setBackground();
        setMainframe();
        setButton(frame);
    }

    public void setFrame(){
        newframe = new JFrame("图书信息添加");
        newframe.setBounds(180,60,1200,550);
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

    public void setMainframe(){

        isbnlabel = new JLabel("ISBN"); //设置isbnlabel标签
        isbnlabel.setBounds(75,50,300,100);
        isbnlabel.setFont(new Font("宋体", Font.PLAIN, 40));
        isbnlabel.setForeground(Color.red);
        layeredPane.add(isbnlabel,JLayeredPane.MODAL_LAYER);

        booknamelabel = new JLabel("书名");   //设置书名标签
        booknamelabel.setBounds(75,125,300,100);
        booknamelabel.setFont(new Font("宋体", Font.PLAIN, 40));
        booknamelabel.setForeground(Color.red);
        layeredPane.add(booknamelabel,JLayeredPane.MODAL_LAYER);

        publishlabel = new JLabel("出版社");
        publishlabel.setBounds(55,200,300,100);
        publishlabel.setFont(new Font("宋体", Font.PLAIN, 40));
        publishlabel.setForeground(Color.red);
        layeredPane.add(publishlabel,JLayeredPane.MODAL_LAYER);

        numlabel = new JLabel("印刷次数");
        numlabel.setBounds(35,275,300,100);
        numlabel.setFont(new Font("宋体", Font.PLAIN, 40));
        numlabel.setForeground(Color.red);
        layeredPane.add(numlabel,JLayeredPane.MODAL_LAYER);

        isbnjt = new JTextField();  //设置isbn文本框
        isbnjt.setBounds(225,75,300,50);
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
        layeredPane.add(isbnjt,JLayeredPane.MODAL_LAYER);

        booknamejt = new JTextField();
        booknamejt.setBounds(225,150,300,50);
        booknamejt.setFont(new Font("宋体", Font.PLAIN, 40));
        layeredPane.add(booknamejt,JLayeredPane.MODAL_LAYER);

        publishjt = new JTextField();
        publishjt.setBounds(225,225,300,50);
        publishjt.setFont(new Font("宋体", Font.PLAIN, 40));
        layeredPane.add(publishjt,JLayeredPane.MODAL_LAYER);

        numjt = new JTextField();
        numjt.setBounds(225,300,300,50);
        numjt.setFont(new Font("宋体", Font.PLAIN, 40));
        numjt.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                int temp = e.getKeyChar();
                if(temp < 48 || temp > 57 ){
                    e.consume();
                }
            }
        });
        layeredPane.add(numjt,JLayeredPane.MODAL_LAYER);

        booktypelabel = new JLabel("图书类型"); //设置图书类型标签
        booktypelabel.setBounds(625,50,300,100);
        booktypelabel.setFont(new Font("宋体", Font.PLAIN, 40));
        booktypelabel.setForeground(Color.red);
        layeredPane.add(booktypelabel,JLayeredPane.MODAL_LAYER);

        authorlabel = new JLabel("作者");
        authorlabel.setBounds(665,125,300,100);
        authorlabel.setFont(new Font("宋体", Font.PLAIN, 40));
        authorlabel.setForeground(Color.red);
        layeredPane.add(authorlabel,JLayeredPane.MODAL_LAYER);

        publishdatelabel = new JLabel("出版日期");
        publishdatelabel.setBounds(625,200,300,100);
        publishdatelabel.setFont(new Font("宋体", Font.PLAIN, 40));
        publishdatelabel.setForeground(Color.red);
        layeredPane.add(publishdatelabel,JLayeredPane.MODAL_LAYER);

        pricelabel = new JLabel("单价");
        pricelabel.setBounds(665,275,300,100);
        pricelabel.setFont(new Font("宋体", Font.PLAIN, 40));
        pricelabel.setForeground(Color.red);
        layeredPane.add(pricelabel,JLayeredPane.MODAL_LAYER);

        jc = new JComboBox();
        List<BookType> list = new BookTypeDaoIpl().selectAllBookTypeInformation();
        for (BookType bookType:list) {
            jc.addItem(bookType.getTypename());
        }
        jc.setBounds(815,75,300,50);
        jc.setFont(new Font("宋体", Font.PLAIN, 40));
        layeredPane.add(jc,JLayeredPane.MODAL_LAYER);

        authorjt = new JTextField();
        authorjt.setBounds(815,150,300,50);
        authorjt.setFont(new Font("宋体", Font.PLAIN, 40));
        layeredPane.add(authorjt,JLayeredPane.MODAL_LAYER);

        publishdatejt = new JTextField();
        publishdatejt.setBounds(815,225,300,50);
        publishdatejt.setFont(new Font("宋体", Font.PLAIN, 40));
        layeredPane.add(publishdatejt,JLayeredPane.MODAL_LAYER);

        pricejt = new JTextField();
        pricejt.setBounds(815,300,300,50);
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
        layeredPane.add(pricejt,JLayeredPane.MODAL_LAYER);


    }

    public void setButton(JFrame frame){
        okjb = new JButton("添加");
        okjb.setBounds(350,425,100,50);
        okjb.setFont(new Font("宋体", Font.PLAIN, 32));
        okjb.setForeground(Color.blue);
        okjb.setContentAreaFilled(false);
        okjb.setBorderPainted(false);
        okjb.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Date date = null;
                Pattern p = Pattern.compile("^((\\d{2}(([02468][048])|([13579][26]))[\\-\\/\\s]?((((0?[13578])|(1[02]))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(3[01])))|(((0?[469])|(11))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(30)))|(0?2[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])))))|(\\d{2}(([02468][1235679])|([13579][01345789]))[\\-\\/\\s]?((((0?[13578])|(1[02]))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(3[01])))|(((0?[469])|(11))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(30)))|(0?2[\\-\\/\\s]?((0?[1-9])|(1[0-9])|(2[0-8]))))))?$");
                String isbn = isbnjt.getText();
                String booktype = (String) jc.getSelectedItem();
                String bookname = booknamejt.getText();
                String author = authorjt.getText();
                String publish = publishjt.getText();
                String publishdate = publishdatejt.getText();
                String publishtime = numjt.getText();
                String prices = pricejt.getText();
                SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");


                if(isbn.equals("") || booktype.equals("") || bookname.equals("") || author.equals("") || publish.equals("") || publishdate.equals("") || publishtime.equals("") || prices.equals("")){
                    JOptionPane.showMessageDialog(null, "关键信息为空,请填写完整!", "错误", JOptionPane.ERROR_MESSAGE);
                }else{
                    int flag;
                    flag = Check.checkIsbn(isbn);
                    int num = Integer.parseInt(numjt.getText());
                    double price = Double.parseDouble(pricejt.getText());

                    Book book = new Book();
                    book.setISBN(isbn);
                    if(flag != 1){  //isbn编号不符合标准
                        isbnjt.setText("");
                        JOptionPane.showMessageDialog(null,"ISBN编号不符合标准!请重新输入!","错误",JOptionPane.ERROR_MESSAGE);
                    }else{
                        Book books = new BookDaoIpl().selectSingleBookByIsbn(book);
                        if(books != null){  //数据存在
                            isbnjt.setText("");
                            JOptionPane.showMessageDialog(null,"该书的数据信息已经存在!无法添加!","错误",JOptionPane.ERROR_MESSAGE);
                        } else{ //时间格式错误
                            if(!p.matcher(publishdatejt.getText()).matches()){
                                publishdatejt.setText("");
                                JOptionPane.showMessageDialog(null,"出版时间格式错误!正确格式为:2012-12-06,请重新输入!","错误",JOptionPane.ERROR_MESSAGE);
                            }else{
                                if(num <= 0 || num > 100){  //印刷次数太离谱
                                    numjt.setText("");
                                    JOptionPane.showMessageDialog(null,"请您走点心好吗!我很累的!","错误",JOptionPane.ERROR_MESSAGE);
                                } else{
                                    if(price <= 0 || price > 100000000){    //书贵的离谱
                                        pricejt.setText("");
                                        JOptionPane.showMessageDialog(null,"请您走点心好吗!我很累的!","错误",JOptionPane.ERROR_MESSAGE);
                                    }else{
                                        try {
                                            date = format.parse(publishdatejt.getText());
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
                                        new BookDaoIpl().addBookInformation(book);
                                        JOptionPane.showMessageDialog(null,"添加成功!","提示",JOptionPane.INFORMATION_MESSAGE);
                                    }
                                }
                            }
                        }
                    }
                }


            }
        });
        layeredPane.add(okjb,JLayeredPane.MODAL_LAYER);

        resetjb = new JButton("重置");
        resetjb.setBounds(600,425,100,50);
        resetjb.setFont(new Font("宋体", Font.PLAIN, 32));
        resetjb.setForeground(Color.blue);
        resetjb.setContentAreaFilled(false);
        resetjb.setBorderPainted(false);
        resetjb.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                isbnjt.setText("");
                booknamejt.setText("");
                authorjt.setText("");
                publishjt.setText("");
                publishdatejt.setText("");
                numjt.setText("");
                pricejt.setText("");
                JOptionPane.showMessageDialog(null,"重置成功!","提示",JOptionPane.INFORMATION_MESSAGE);
            }
        });
        layeredPane.add(resetjb,JLayeredPane.MODAL_LAYER);

        backjb = new JButton("返回");
        backjb.setBounds(850,425,100,50);
        backjb.setFont(new Font("宋体", Font.PLAIN, 32));
        backjb.setForeground(Color.blue);
        backjb.setContentAreaFilled(false);
        backjb.setBorderPainted(false);
        backjb.addActionListener(new ActionListener() { //给返回案件设置监听事件
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
        ImageIcon icon = new ImageIcon("img/%_%191$(~5C_O8KD0AQX1KS.png");
        icon.setImage(icon.getImage().getScaledInstance(1200,550,Image.SCALE_DEFAULT));
        imglabel = new JLabel(icon);
        imglabel.setBounds(0,0,icon.getIconWidth(),icon.getIconHeight());
        layeredPane.add(imglabel,JLayeredPane.DEFAULT_LAYER);
    }
}