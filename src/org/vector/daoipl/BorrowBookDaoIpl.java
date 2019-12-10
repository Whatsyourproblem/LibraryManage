/**
 * Company
 * Copyright (C) 2004-2019 All Rights Reserved.
 */
package org.vector.daoipl;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.vector.bean.BorrowBook;
import org.vector.dao.BorrowBookDao;
import org.vector.dao.Conn;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

/**
 * @author Administrator
 * @version $Id BorrowBookDaoIpl.java, v 0.1 2019-06-03 19:41 Administrator Exp $$
 */
public class BorrowBookDaoIpl implements BorrowBookDao {

    @Override
    public List<BorrowBook> selectAllBorrowBook() {    //查找所有借阅信息
        Connection conn = Conn.getConnection();
        QueryRunner qr = new QueryRunner();
        String sql = "select * from borrowbook";
        try {
            List<BorrowBook> list = qr.query(conn,sql,new BeanListHandler<BorrowBook>(BorrowBook.class));
            return list;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }

    }

    @Override
    public void addBorrowBookInformation(BorrowBook borrowbook) {   //向表中添加数据
        Connection conn = Conn.getConnection();
        QueryRunner qr = new QueryRunner();
        String sql = "insert into borrowbook (readerid,ISBN,bookname,borrowdate,returndate,fine) values (?,?,?,?,?,?)";
        Object[] params = {borrowbook.getReaderid(),borrowbook.getISBN(),borrowbook.getBookname(),borrowbook.getBorrowdate(),borrowbook.getReturndate(),borrowbook.getFine()};
        try {
            qr.update(conn,sql,params);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public BorrowBook selectSingleBorrowBook(BorrowBook borrowBook) {   //通过isbn来查找借阅信息
        Connection conn = Conn.getConnection();
        QueryRunner qr = new QueryRunner();
        String sql = "select * from borrowbook where ISBN = ?";
        try {
            BorrowBook borrowBooks = qr.query(conn,sql,new BeanHandler<BorrowBook>(BorrowBook.class),borrowBook.getISBN());
            return borrowBooks;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public void updateSingleBorrowBook(BorrowBook borrowBook) { //通过isbn和readerid来修改归还时间
        Connection conn = Conn.getConnection();
        QueryRunner qr = new QueryRunner();
        String sql = "update borrowbook set returndate = ?,fine = ? where ISBN = ? and readerid = ?";
        Object[] params = {borrowBook.getReturndate(),borrowBook.getFine(),borrowBook.getISBN(),borrowBook.getReaderid()};
        try {
            qr.update(conn,sql,params);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void updatePosition1(BorrowBook borrowBook) { //修改借阅状态,借书时间
        Connection conn = Conn.getConnection();
        QueryRunner qr = new QueryRunner();
        String sql = "update borrowbook set borrowdate = ?,returndate = ? ,fine = ?,position = ? where ISBN = ?";
        Object[] params = {borrowBook.getBorrowdate(),null,null,borrowBook.getPosition(),borrowBook.getISBN()};
        try {
            qr.update(conn,sql,params);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void updatePosition2(BorrowBook borrowBook) {//修改借阅状态,还书时间
        Connection conn = Conn.getConnection();
        QueryRunner qr = new QueryRunner();
        String sql = "update borrowbook set returndate = ?,fine = ?,position = ? where ISBN = ?";
        Object[] params = {borrowBook.getReturndate(),borrowBook.getFine(),borrowBook.getPosition(),borrowBook.getISBN()};
        try {
            qr.update(conn,sql,params);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}