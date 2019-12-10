/**
 * Company
 * Copyright (C) 2004-2019 All Rights Reserved.
 */
package org.vector.bean;

import java.util.Date;

/**
 * @author Administrator
 * @version $Id BorrowBook.java, v 0.1 2019-05-27 19:40 Administrator Exp $$
 */
public class BorrowBook {   //借阅图书实体类
    private String readerid;  //读者编号
    private String ISBN;  //图书ISBN
    private String bookname;    //图书名字
    private Date borrowdate;  //借阅日期
    private Date returndate;  //归还日期
    private String fine;    //罚金
    private String position;//借阅状态

    public BorrowBook() {
    }

    public BorrowBook(String readerid, String ISBN, String bookname, Date borrowdate, Date returndate, String fine, String position) {
        this.readerid = readerid;
        this.ISBN = ISBN;
        this.bookname = bookname;
        this.borrowdate = borrowdate;
        this.returndate = returndate;
        this.fine = fine;
        this.position = position;
    }

    public String getReaderid() {
        return readerid;
    }

    public void setReaderid(String readerid) {
        this.readerid = readerid;
    }

    public String getISBN() {
        return ISBN;
    }

    public void setISBN(String ISBN) {
        this.ISBN = ISBN;
    }

    public String getBookname() {
        return bookname;
    }

    public void setBookname(String bookname) {
        this.bookname = bookname;
    }

    public Date getBorrowdate() {
        return borrowdate;
    }

    public void setBorrowdate(Date borrowdate) {
        this.borrowdate = borrowdate;
    }

    public Date getReturndate() {
        return returndate;
    }

    public void setReturndate(Date returndate) {
        this.returndate = returndate;
    }

    public String getFine() {
        return fine;
    }

    public void setFine(String fine) {
        this.fine = fine;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }
}