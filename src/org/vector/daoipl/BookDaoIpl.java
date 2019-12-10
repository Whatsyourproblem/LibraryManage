/**
 * Company
 * Copyright (C) 2004-2019 All Rights Reserved.
 */
package org.vector.daoipl;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.vector.bean.Book;
import org.vector.dao.BookDao;
import org.vector.dao.Conn;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

/**
 * @author Administrator
 * @version $Id BookDaoIpl.java, v 0.1 2019-06-01 17:21 Administrator Exp $$
 */
public class BookDaoIpl implements BookDao {//该类实现BookDao的方法
    @Override
    public Book selectSingleBookByIsbn(Book book) {     //通过ISBN编号来查询单个数据
        Connection conn = Conn.getConnection();
        QueryRunner qr = new QueryRunner();
        String sql = "select * from book where ISBN = ?";
        try {
            Book books = qr.query(conn,sql,new BeanHandler<Book>(Book.class),book.getISBN());
            return books;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public Book selectSingleBooksByIsbn(Book book) {
        Connection conn = Conn.getConnection();
        QueryRunner qr = new QueryRunner();
        String sql = "select * from books where ISBN = ?";
        try {
            Book books = qr.query(conn,sql,new BeanHandler<Book>(Book.class),book.getISBN());
            return books;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public void addBookInformation(Book book) {  //添加book的数据
        Connection conn = Conn.getConnection();
        QueryRunner qr = new QueryRunner();
        String sql = "insert into book (ISBN,typeid,bookname,author,publish,publishdate,publishtime,unitprice) values (?,?,?,?,?,?,?,?)";
        Object[] params = {book.getISBN(),book.getTypeid(),book.getBookname(),book.getAuthor(),book.getPublish(),book.getPublishdate(),book.getPublishtime(),book.getUnitprice()};
        try {
            qr.update(conn,sql,params);
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void addBooksInformation(Book book) {
        Connection conn = Conn.getConnection();
        QueryRunner qr = new QueryRunner();
        String sql = "insert into books (ISBN,typeid,bookname,author,publish,publishdate,publishtime,unitprice) values (?,?,?,?,?,?,?,?)";
        Object[] params = {book.getISBN(),book.getTypeid(),book.getBookname(),book.getAuthor(),book.getPublish(),book.getPublishdate(),book.getPublishtime(),book.getUnitprice()};
        try {
            qr.update(conn,sql,params);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Book> selectAllBookInformation() {  //查询所有的书
        Connection conn = Conn.getConnection();
        QueryRunner qr = new QueryRunner();
        String sql = "select * from book";
        try {
            List<Book> list = qr.query(conn,sql,new BeanListHandler<Book>(Book.class));
            return list;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<Book> selectBookInformationByType(Book book) {  //查询类别为xx的书
        Connection conn = Conn.getConnection();
        QueryRunner qr = new QueryRunner();
        String sql = "select * from book where typeid = ?";
        try {
            List<Book> list = qr.query(conn,sql,new BeanListHandler<Book>(Book.class),book.getTypeid());
            return list;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }

    }

    @Override
    public List<Book> selectBookInformationByBookName(Book book) {  //查询名字为xx的书
        Connection conn = Conn.getConnection();
        QueryRunner qr = new QueryRunner();
        String sql = "select * from book where bookname = ?";
        try {
            List<Book> list = qr.query(conn,sql,new BeanListHandler<Book>(Book.class),book.getBookname());
            return list;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public void updateBookInformationByIsbn(Book book) {
        Connection conn = Conn.getConnection();
        QueryRunner qr = new QueryRunner();
        String sql = "update book set typeid = ?,bookname = ?,author = ?,publish = ?,publishdate = ?,publishtime = ?,unitprice = ? where ISBN = ?";
        Object[] params = {book.getTypeid(),book.getBookname(),book.getAuthor(),book.getPublish(),book.getPublishdate(),book.getPublishtime(),book.getUnitprice(),book.getISBN()};
        try {
            qr.update(conn,sql,params);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteSingleBookInformationByIsbn(Book book) {  //通过isbn来删除某本书
        Connection conn = Conn.getConnection();
        QueryRunner qr = new QueryRunner();
        String sql = "delete from book where ISBN = ?";
        try {
            qr.update(conn,sql,book.getISBN());
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

}