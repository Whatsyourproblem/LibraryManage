/**
 * Company
 * Copyright (C) 2004-2019 All Rights Reserved.
 */
package org.vector.bean;

import java.util.Date;

/**
 * @author Administrator
 * @version $Id Book.java, v 0.1 2019-05-27 19:31 Administrator Exp $$
 */
public class Book { //book实体类
    private String ISBN;  //图书的ISBN
    private String typeid;  //图书类型
    private String bookname;    //图书名称
    private String author;  //作者
    private String publish; //出版社
    private Date publishdate;   //出版日期
    private String publishtime;  //印刷次数
    private String unitprice;   //单价

    public Book() {
    }

    public Book(String ISBN, String typeid, String bookname, String author, String publish, Date publishdate, String publishtime, String unitprice) {
        this.ISBN = ISBN;
        this.typeid = typeid;
        this.bookname = bookname;
        this.author = author;
        this.publish = publish;
        this.publishdate = publishdate;
        this.publishtime = publishtime;
        this.unitprice = unitprice;
    }

    public String getISBN() {
        return ISBN;
    }

    public void setISBN(String ISBN) {
        this.ISBN = ISBN;
    }

    public String getTypeid() {
        return typeid;
    }

    public void setTypeid(String typeid) {
        this.typeid = typeid;
    }

    public String getBookname() {
        return bookname;
    }

    public void setBookname(String bookname) {
        this.bookname = bookname;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getPublish() {
        return publish;
    }

    public void setPublish(String publish) {
        this.publish = publish;
    }

    public Date getPublishdate() {
        return publishdate;
    }

    public void setPublishdate(Date publishdate) {
        this.publishdate = publishdate;
    }

    public String getPublishtime() {
        return publishtime;
    }

    public void setPublishtime(String publishtime) {
        this.publishtime = publishtime;
    }

    public String getUnitprice() {
        return unitprice;
    }

    public void setUnitprice(String unitprice) {
        this.unitprice = unitprice;
    }

    @Override
    public String toString() {
        return "Book{" +
                "ISBN='" + ISBN + '\'' +
                ", typeid='" + typeid + '\'' +
                ", bookname='" + bookname + '\'' +
                ", author='" + author + '\'' +
                ", publish='" + publish + '\'' +
                ", publishdate=" + publishdate +
                ", publishtime=" + publishtime +
                ", unitprice=" + unitprice +
                '}';
    }
}