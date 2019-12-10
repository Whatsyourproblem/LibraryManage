/**
 * Company
 * Copyright (C) 2004-2019 All Rights Reserved.
 */
package org.vector.daoipl;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.vector.bean.BookType;
import org.vector.dao.BookTypeDao;
import org.vector.dao.Conn;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

/**
 * @author Administrator
 * @version $Id BookTypeDaoIpl.java, v 0.1 2019-06-09 17:22 Administrator Exp $$
 */
public class BookTypeDaoIpl implements BookTypeDao {
    @Override
    public List<BookType> selectAllBookTypeInformation() {
        Connection conn = Conn.getConnection();
        QueryRunner qr = new QueryRunner();
        String sql = "select * from booktype";
        try {
            List<BookType> list = qr.query(conn,sql,new BeanListHandler<BookType>(BookType.class));
            return list;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public BookType selectSingleInformationById(BookType bookType) {
        Connection conn = Conn.getConnection();
        QueryRunner qr = new QueryRunner();
        String sql = "select * from booktype where id = ? or typename = ?";
        Object[] params = {bookType.getId(),bookType.getTypename()};
        try {
            BookType bookTypes = qr.query(conn,sql,new BeanHandler<BookType>(BookType.class),params);
            return bookTypes;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public void addInformation(BookType bookType) {
        Connection conn = Conn.getConnection();
        QueryRunner qr = new QueryRunner();
        String sql = "insert into booktype (id,typename) values (?,?)";
        Object[] params = {bookType.getId(),bookType.getTypename()};
        try {
            qr.update(conn,sql,params);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public BookType selectSingleIfmaById(BookType bookType) {
        Connection conn = Conn.getConnection();
        QueryRunner qr = new QueryRunner();
        String sql = "select * from booktype where id = ?";
        try {
            BookType bookTypes = qr.query(conn,sql,new BeanHandler<BookType>(BookType.class),bookType.getId());
            return bookTypes;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public void updateSingleIfmaById(BookType bookType) {
        Connection conn = Conn.getConnection();
        QueryRunner qr = new QueryRunner();
        String sql = "update booktype set typename = ? where id = ?";
        Object[] params = {bookType.getTypename(),bookType.getId()};
        try {
            qr.update(conn,sql,params);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteSingleIfmaById(BookType bookType) {
        Connection conn = Conn.getConnection();
        QueryRunner qr = new QueryRunner();
        String sql = "delete from booktype where id = ? and typename = ?";
        Object[] params = {bookType.getId(),bookType.getTypename()};
        try {
            qr.update(conn,sql,params);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}