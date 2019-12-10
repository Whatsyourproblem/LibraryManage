/**
 * Company
 * Copyright (C) 2004-2019 All Rights Reserved.
 */
package org.vector.org.vector.ui;

import org.vector.bean.*;
import org.vector.daoipl.BookDaoIpl;
import org.vector.daoipl.BorrowBookDaoIpl;
import org.vector.daoipl.ReaderDaoIpl;
import org.vector.daoipl.ReaderTypeDaoIpl;
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
 * @version $Id BookBorrowInformation.java, v 0.1 2019-06-02 14:58 Administrator Exp $$
 */
public class BookBorrowInformation {      //图书借阅
    private JFrame newframe;
    private JLayeredPane layeredPane = new JLayeredPane();
    private JLabel imglabel;
    private JLabel readeridlabel,readernamelabel,readertypelabel;
    private JTextField readeridjt,readernamejt,readertypejt;
    private TableModel tm;
    private JTable table;
    private JScrollPane jspanel;
    private JLabel isbnlabel,typelabel,namelabel,authorlabel,publishlabel,publishdatelabel,numlabel,pricelabel,nowdatelabel,operatorlabel;
    private JTextField isbnjt,typejt,namejt,authorjt,publishjt,publishdatejt,numjt,pricejt,nowdatejt,operatorjt;
    private JButton borrowjb,backjb;

    public BookBorrowInformation(JFrame frame, Users users) {
        load(frame,users);
    }

    public void load(JFrame frame,Users users){
        setFrame();
        setBackground();
        setHead();
        setBody();
        setBottom(users);
        setButton(frame);
    }

    public void setFrame(){
        newframe = new JFrame();
        newframe.setBounds(180,60,1200,900);
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

    public void setHead(){      //设置最上方的组件
        readeridlabel = new JLabel("读者编号");
        readeridlabel.setBounds(87,5,200,100);
        readeridlabel.setFont(new Font("宋体", Font.PLAIN, 30));
        readeridlabel.setForeground(Color.red);
        layeredPane.add(readeridlabel,JLayeredPane.PALETTE_LAYER);

        readernamelabel = new JLabel("读者姓名");
        readernamelabel.setBounds(437,5,200,100);
        readernamelabel.setFont(new Font("宋体", Font.PLAIN, 30));
        readernamelabel.setForeground(Color.red);
        layeredPane.add(readernamelabel,JLayeredPane.PALETTE_LAYER);

        readertypelabel = new JLabel("读者类型");
        readertypelabel.setBounds(787,5,200,100);
        readertypelabel.setFont(new Font("宋体", Font.PLAIN, 30));
        readertypelabel.setForeground(Color.red);
        layeredPane.add(readertypelabel,JLayeredPane.PALETTE_LAYER);

        readeridjt = new JTextField();
        readeridjt.setBounds(212,37,200,40);
        readeridjt.setFont(new Font("宋体", Font.PLAIN, 22));
        readeridjt.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                int temp = e.getKeyChar();
                if(temp < 48 || temp > 57){
                    e.consume();
                }
            }
        });
        readeridjt.addFocusListener(new FocusAdapter() {
            @Override
            public void focusLost(FocusEvent e) {
                Reader reader = new Reader();
                reader.setReaderid(readeridjt.getText());
                Reader readers = new ReaderDaoIpl().selectSingleReaderById(reader);

                if(readers == null){

                }else{
                    readernamejt.setText(readers.getName());
                    readertypejt.setText(readers.getType());
                }


            }
        });
        layeredPane.add(readeridjt,JLayeredPane.PALETTE_LAYER);

        readernamejt = new JTextField();
        readernamejt.setBounds(562,37,200,40);
        readernamejt.setFont(new Font("宋体", Font.PLAIN, 22));
        layeredPane.add(readernamejt,JLayeredPane.PALETTE_LAYER);

        readertypejt = new JTextField();
        readertypejt.setBounds(912,37,200,40);
        readertypejt.setFont(new Font("宋体", Font.PLAIN, 22));
        layeredPane.add(readertypejt,JLayeredPane.PALETTE_LAYER);
    }

    public void setBody(){  //设置主体部分
        Object[] columnNames = {"读者编号","图书编号","图书名称","借书日期","本书是否被借阅"};
        Object[][] rowdata;

        List<BorrowBook> list = new BorrowBookDaoIpl().selectAllBorrowBook();
        rowdata = new Object[list.size()][5];
        for (int i = 0; i < list.size(); i++) {
            rowdata[i][0] = list.get(i).getReaderid();
            rowdata[i][1] = list.get(i).getISBN();
            rowdata[i][2] = list.get(i).getBookname();
            rowdata[i][3] = list.get(i).getBorrowdate();
            rowdata[i][4] = list.get(i).getPosition();
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
        tableColumn1.setPreferredWidth(150);
        TableColumn tableColumn2 = table.getColumnModel().getColumn(1);
        tableColumn2.setPreferredWidth(150);
        TableColumn tableColumn3 = table.getColumnModel().getColumn(2);
        tableColumn3.setPreferredWidth(150);

        jspanel = new JScrollPane(table);
        jspanel.setBounds(75,100,1050,300);
        layeredPane.add(jspanel,JLayeredPane.PALETTE_LAYER);

    }

    public void setBottom(Users users){    //设置界面的底部
        isbnlabel = new JLabel("ISBN");
        isbnlabel.setBounds(85,400,300,100);
        isbnlabel.setFont(new Font("宋体", Font.PLAIN, 40));
        isbnlabel.setForeground(Color.red);
        layeredPane.add(isbnlabel,JLayeredPane.MODAL_LAYER);

        namelabel = new JLabel("图书名称");
        namelabel.setBounds(45,475,300,100);
        namelabel.setFont(new Font("宋体", Font.PLAIN, 40));
        namelabel.setForeground(Color.red);
        layeredPane.add(namelabel,JLayeredPane.MODAL_LAYER);

        publishlabel = new JLabel("出版社");
        publishlabel.setBounds(65,550,300,100);
        publishlabel.setFont(new Font("宋体", Font.PLAIN, 40));
        publishlabel.setForeground(Color.red);
        layeredPane.add(publishlabel,JLayeredPane.MODAL_LAYER);

        numlabel = new JLabel("印刷次数");
        numlabel.setBounds(45,625,300,100);
        numlabel.setFont(new Font("宋体", Font.PLAIN, 40));
        numlabel.setForeground(Color.red);
        layeredPane.add(numlabel,JLayeredPane.MODAL_LAYER);

        nowdatelabel = new JLabel("当前日期");
        nowdatelabel.setBounds(45,700,300,100);
        nowdatelabel.setFont(new Font("宋体", Font.PLAIN, 40));
        nowdatelabel.setForeground(Color.red);
        layeredPane.add(nowdatelabel,JLayeredPane.MODAL_LAYER);

        typelabel = new JLabel("图书类别");
        typelabel.setBounds(610,400,300,100);
        typelabel.setFont(new Font("宋体", Font.PLAIN, 40));
        typelabel.setForeground(Color.red);
        layeredPane.add(typelabel,JLayeredPane.MODAL_LAYER);

        authorlabel = new JLabel("作者");
        authorlabel.setBounds(650,475,300,100);
        authorlabel.setFont(new Font("宋体", Font.PLAIN, 40));
        authorlabel.setForeground(Color.red);
        layeredPane.add(authorlabel,JLayeredPane.MODAL_LAYER);

        publishdatelabel = new JLabel("出版日期");
        publishdatelabel.setBounds(610,550,300,100);
        publishdatelabel.setFont(new Font("宋体", Font.PLAIN, 40));
        publishdatelabel.setForeground(Color.red);
        layeredPane.add(publishdatelabel,JLayeredPane.MODAL_LAYER);

        pricelabel = new JLabel("单价");
        pricelabel.setBounds(650,625,300,100);
        pricelabel.setFont(new Font("宋体", Font.PLAIN, 40));
        pricelabel.setForeground(Color.red);
        layeredPane.add(pricelabel,JLayeredPane.MODAL_LAYER);

        operatorlabel = new JLabel("操作人员");
        operatorlabel.setBounds(610,700,300,100);
        operatorlabel.setFont(new Font("宋体", Font.PLAIN, 40));
        operatorlabel.setForeground(Color.red);
        layeredPane.add(operatorlabel,JLayeredPane.MODAL_LAYER);

        isbnjt = new JTextField();
        isbnjt.setBounds(210,425,300,50);
        isbnjt.setFont(new Font("宋体", Font.PLAIN, 40));
        isbnjt.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                int temp = e.getKeyChar();
                if(temp < 48 || temp > 57){
                    e.consume();
                }
            }
        });
        isbnjt.addFocusListener(new FocusAdapter() {
            @Override
            public void focusLost(FocusEvent e) {
                Book book = new Book();
                book.setISBN(isbnjt.getText());

                Book books = new BookDaoIpl().selectSingleBookByIsbn(book);

                if(books == null){

                }else{
                    typejt.setText(books.getTypeid());
                    namejt.setText(books.getBookname());
                    authorjt.setText(books.getAuthor());
                    publishjt.setText(books.getPublish());
                    publishdatejt.setText(books.getPublishdate().toString());
                    numjt.setText(books.getPublishtime());
                    pricejt.setText(books.getUnitprice());
                }
            }
        });
        layeredPane.add(isbnjt,JLayeredPane.MODAL_LAYER);

        namejt = new JTextField();
        namejt.setBounds(210,500,300,50);
        namejt.setFont(new Font("宋体", Font.PLAIN, 40));
        layeredPane.add(namejt,JLayeredPane.MODAL_LAYER);

        publishjt = new JTextField();
        publishjt.setBounds(210,575,300,50);
        publishjt.setFont(new Font("宋体", Font.PLAIN, 40));
        layeredPane.add(publishjt,JLayeredPane.MODAL_LAYER);

        numjt = new JTextField();
        numjt.setBounds(210,650,300,50);
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
        layeredPane.add(numjt,JLayeredPane.MODAL_LAYER);

        nowdatejt = new JTextField();
        nowdatejt.setBounds(210,725,300,50);
        nowdatejt.setFont(new Font("宋体", Font.PLAIN, 40));
        layeredPane.add(nowdatejt,JLayeredPane.MODAL_LAYER);

        typejt = new JTextField();
        typejt.setBounds(810,425,300,50);
        typejt.setFont(new Font("宋体", Font.PLAIN, 40));
        layeredPane.add(typejt,JLayeredPane.MODAL_LAYER);

        authorjt = new JTextField();
        authorjt.setBounds(810,500,300,50);
        authorjt.setFont(new Font("宋体", Font.PLAIN, 40));
        layeredPane.add(authorjt,JLayeredPane.MODAL_LAYER);

        publishdatejt = new JTextField();
        publishdatejt.setBounds(810,575,300,50);
        publishdatejt.setFont(new Font("宋体", Font.PLAIN, 40));
        layeredPane.add(publishdatejt,JLayeredPane.MODAL_LAYER);

        pricejt = new JTextField();
        pricejt.setBounds(810,650,300,50);
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

        operatorjt = new JTextField();
        operatorjt.setBounds(810,725,300,50);
        operatorjt.setFont(new Font("宋体", Font.PLAIN, 40));
        layeredPane.add(operatorjt,JLayeredPane.MODAL_LAYER);

        operatorjt.setEditable(false);
        operatorjt.setText(users.getUsername());

    }

    public void setButton(JFrame frame){        //设置按钮
        borrowjb = new JButton("借阅");
        borrowjb.setBounds(450,825,100,50);
        borrowjb.setFont(new Font("宋体", Font.PLAIN, 32));
        borrowjb.setForeground(Color.blue);
        borrowjb.setContentAreaFilled(false);
        borrowjb.setBorderPainted(false);
        borrowjb.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Date date = null;
                Pattern p = Pattern.compile("^((\\d{2}(([02468][048])|([13579][26]))[\\-\\/\\s]?((((0?[13578])|(1[02]))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(3[01])))|(((0?[469])|(11))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(30)))|(0?2[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])))))|(\\d{2}(([02468][1235679])|([13579][01345789]))[\\-\\/\\s]?((((0?[13578])|(1[02]))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(3[01])))|(((0?[469])|(11))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(30)))|(0?2[\\-\\/\\s]?((0?[1-9])|(1[0-9])|(2[0-8]))))))?$");
                SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");

                String readerid = readeridjt.getText();
                String readername = readernamejt.getText();
                String readertype = readertypejt.getText();
                if (readerid.equals("") || readername.equals("") || readertype.equals("")){
                    JOptionPane.showMessageDialog(null, "关键信息为空,请填写完整!", "错误", JOptionPane.ERROR_MESSAGE);
                }else{
                    int flag = Check.checkId(readerid);
                    if(flag != 1){  //读者编号格式错误
                        readeridjt.setText("");
                        JOptionPane.showMessageDialog(null,"读者编号格式错误！请重新输入!","错误",JOptionPane.ERROR_MESSAGE);
                    }else{  //正确
                        Reader reader = new Reader();
                        reader.setReaderid(readerid);
                        reader.setName(readername);
                        reader.setType(readertype);

                        Reader readers = new ReaderDaoIpl().selectSingleReaderByIdNameType(reader);
                        if(readers == null){
                            readeridjt.setText("");
                            readernamejt.setText("");
                            readertypejt.setText("");
                            JOptionPane.showMessageDialog(null,"读者信息不存在!无法借阅!","错误",JOptionPane.ERROR_MESSAGE);
                        }else{  //读者信息存在
                            String isbn = isbnjt.getText();
                            String booktype = typejt.getText();
                            String bookname = namejt.getText();
                            String author = authorjt.getText();
                            String publish = publishjt.getText();
                            String publishdate = publishdatejt.getText();
                            String num = numjt.getText();
                            String price = pricejt.getText();
                            String nowdate = nowdatejt.getText();

                            if(isbn.equals("") || booktype.equals("") || bookname.equals("") || author.equals("") || publish.equals("") || publishdate.equals("") || num.equals("") || price.equals("") || nowdate.equals("")){
                                JOptionPane.showMessageDialog(null,"关键信息为空!请重新输入!","错误",JOptionPane.ERROR_MESSAGE);
                            }else{
                                int temp = Check.checkIsbn(isbn);
                                if(temp != 1){
                                    isbnjt.setText("");
                                    JOptionPane.showMessageDialog(null,"ISBN编号不符合标准!请重新输入!","错误",JOptionPane.ERROR_MESSAGE);
                                }else{
                                    Book book = new Book();
                                    book.setISBN(isbn);
                                    Book books = new BookDaoIpl().selectSingleBookByIsbn(book);
                                    if(books == null){
                                        isbnjt.setText("");
                                        JOptionPane.showMessageDialog(null,"不存在该书!无法借阅!","错误",JOptionPane.ERROR_MESSAGE);
                                    }else{
                                        if(!p.matcher(publishdate).matches()){  //出版时间格式错误
                                            publishdatejt.setText("");
                                            JOptionPane.showMessageDialog(null,"出版时间格式错误!正确格式为:2012-12-06,请重新输入!","错误",JOptionPane.ERROR_MESSAGE);
                                        }else{
                                            if(!Check.checkNum(num)){
                                                numjt.setText("");
                                                JOptionPane.showMessageDialog(null,"印刷次数不合理!","错误",JOptionPane.ERROR_MESSAGE);
                                            }else{
                                                if(!Check.checkPrice(price)){
                                                    pricejt.setText("");
                                                    JOptionPane.showMessageDialog(null,"书的价钱不合理!","错误",JOptionPane.ERROR_MESSAGE);
                                                }else{
                                                    if(!p.matcher(nowdate).matches()){  //当前时间格式错误
                                                        nowdatejt.setText("");
                                                        JOptionPane.showMessageDialog(null,"当前时间格式错误!正确格式为:2012-12-06,请重新输入!","错误",JOptionPane.ERROR_MESSAGE);
                                                    }else{
                                                        ReaderType readerType = new ReaderType();
                                                        readerType.setName(readertype);
                                                        ReaderType readerTypes = new ReaderTypeDaoIpl().seletSingleInformationByName(readerType);
                                                        int x = Integer.parseInt(readerTypes.getMaxborrownum());
                                                        System.out.println(x);
                                                        if(readers.getPosition() >= x){
                                                            JOptionPane.showMessageDialog(null,"超出最大借阅数量!无法借阅!","错误",JOptionPane.ERROR_MESSAGE);
                                                        }else{
                                                            BorrowBook borrowbook = new BorrowBook();
                                                            borrowbook.setReaderid(readerid);
                                                            borrowbook.setISBN(isbn);
                                                            borrowbook.setBookname(bookname);
                                                            try {
                                                                date = format.parse(nowdate);
                                                            } catch (ParseException ex) {
                                                                ex.printStackTrace();
                                                            }
                                                            borrowbook.setBorrowdate(date);

                                                             //此段代码为了防止反复借一本书而出错
                                                            BorrowBook borrowBook = new BorrowBook();
                                                            borrowBook.setISBN(isbn);
                                                            BorrowBook borrowBooks = new BorrowBookDaoIpl().selectSingleBorrowBook(borrowBook);
                                                            if(borrowBooks == null){
                                                                new BorrowBookDaoIpl().addBorrowBookInformation(borrowbook);
                                                                borrowbook.setPosition("仍在借阅中");
                                                                new BorrowBookDaoIpl().updatePosition1(borrowbook);
                                                            }else{
                                                                borrowbook.setPosition("仍在借阅中");
                                                                new BorrowBookDaoIpl().updatePosition1(borrowbook);
                                                            }

                                                            JOptionPane.showMessageDialog(null,"借阅成功","提示",JOptionPane.INFORMATION_MESSAGE);

                                                            new BookDaoIpl().addBooksInformation(books);        //备份数据
                                                            new BookDaoIpl().deleteSingleBookInformationByIsbn(books); //暂时删除此书
                                                            new ReaderDaoIpl().updatePosition(readers); //给该读者的借阅状态加1

                                                            DefaultTableModel tableModel = (DefaultTableModel) table.getModel();    //获得表格模型
                                                            tableModel.setRowCount(0);  //清空表格中的所有数据,保留表头

                                                            List<BorrowBook> list = new BorrowBookDaoIpl().selectAllBorrowBook();
                                                            for (int i = 0; i < list.size(); i++) { //更新表格
                                                                tableModel.addRow(new Object[]{
                                                                        list.get(i).getReaderid(),
                                                                        list.get(i).getISBN(),
                                                                        list.get(i).getBookname(),
                                                                        list.get(i).getBorrowdate(),
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
                            }
                        }
                    }
                }
            }
        });
        layeredPane.add(borrowjb,JLayeredPane.MODAL_LAYER);

        backjb = new JButton("返回");
        backjb.setBounds(650,825,100,50);
        backjb.setFont(new Font("宋体", Font.PLAIN, 32));
        backjb.setForeground(Color.blue);
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
        //_U(7HMXJG8H8$GPVD18X`{F.png
        ImageIcon icon = new ImageIcon("img/_U(7HMXJG8H8$GPVD18X`{F.png");
        icon.setImage(icon.getImage().getScaledInstance(1200,900,Image.SCALE_DEFAULT));
        imglabel = new JLabel(icon);
        imglabel.setBounds(0,0,icon.getIconWidth(),icon.getIconHeight());
        layeredPane.add(imglabel,JLayeredPane.DEFAULT_LAYER);
    }

}