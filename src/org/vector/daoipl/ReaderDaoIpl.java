/**
 * Company
 * Copyright (C) 2004-2019 All Rights Reserved.
 */
package org.vector.daoipl;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.vector.bean.Reader;
import org.vector.bean.Users;
import org.vector.dao.Conn;
import org.vector.dao.ReaderDao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

/**
 * @author Administrator
 * @version $Id ReaderDaoIpl.java, v 0.1 2019-05-29 18:30 Administrator Exp $$
 */
public class ReaderDaoIpl implements ReaderDao { //该类实现ReaderDao接口
    @Override
    public Reader selectSingleReaderById(Reader reader) {   //通过ID来查询用户
        Connection conn = Conn.getConnection();
        QueryRunner qr = new QueryRunner();
        String sql = "select * from reader where readerid = ?";
        Object[] params = {reader.getReaderid()};
        try {
            Reader readers = qr.query(conn,sql,new BeanHandler<Reader>(Reader.class),params);
            return readers;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }

    }

    @Override
    public void addReader(Reader reader) {  //将读者数据添加到数据库
        Connection conn = Conn.getConnection();
        QueryRunner qr = new QueryRunner();
        String sql = "insert into reader (readerid,type,name,age,sex,phone,idcard,dept) values (?,?,?,?,?,?,?,?) ";
        Object[] params = {reader.getReaderid(),reader.getType(),reader.getName(),reader.getAge(),reader.getSex(),reader.getPhone(),reader.getIdcard(),reader.getDept()};
        try {
            qr.update(conn,sql,params);
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @Override
    public Reader selcetSingleRedaerByIdcard(Reader reader) {   //通过用户的身份证号来查找用户
        Connection conn = Conn.getConnection();
        QueryRunner qr = new QueryRunner();
        String sql = "select * from reader where idcard = ?";
        try {
            Reader readers = qr.query(conn,sql,new BeanHandler<Reader>(Reader.class),reader.getIdcard());
            return readers;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<Reader> selectAllReader() { //查询所有用户
        Connection conn = Conn.getConnection();
        QueryRunner qr = new QueryRunner();
        String sql = "select * from reader";
        try {
            List<Reader> list = qr.query(conn,sql,new BeanListHandler<Reader>(Reader.class));
            return list;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }

    }

    @Override
    public List<Reader> selectAllReaderByType(Reader reader) {  //查找表中类型为xx的数据
        Connection conn = Conn.getConnection();
        QueryRunner qr = new QueryRunner();
        String sql = "select * from reader where type = ?";
        try {
            List<Reader> list = qr.query(conn,sql,new BeanListHandler<Reader>(Reader.class),reader.getType());
            return list;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }

    }

    @Override
    public List<Reader> selectAllReaderByName(Reader reader) {  //查找表中名字为xx的用户
        Connection conn = Conn.getConnection();
        QueryRunner qr = new QueryRunner();
        String sql = "select * from reader where name = ?";
        try {
            List<Reader> list = qr.query(conn,sql,new BeanListHandler<Reader>(Reader.class),reader.getName());
            return list;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }

    }

    @Override
    public List<Reader> selectAllReaderById(Reader reader) {    //查找表中身份证号为xx的用户和
        Connection conn = Conn.getConnection();
        QueryRunner qr = new QueryRunner();
        String sql = "select * from reader where idcard = ?";
        try {
            List<Reader> list = qr.query(conn,sql,new BeanListHandler<Reader>(Reader.class),reader.getIdcard());
            return list;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public void updateReader(Reader reader) {   //修改用户数据
        Connection conn = Conn.getConnection();
        QueryRunner qr = new QueryRunner();
        String sql = "update reader set type = ?,name = ?,age = ?,sex = ?,phone = ?,idcard = ?,dept = ? where readerid = ?";
        Object[] params = {reader.getType(),reader.getName(),reader.getAge(),reader.getSex(),reader.getPhone(),reader.getIdcard(),reader.getDept(),reader.getReaderid()};
        try {
            qr.update(conn,sql,params);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Reader selectSingleReaderByIdNameType(Reader reader) {   //通过三个条件来查找读者数据
        Connection conn = Conn.getConnection();
        QueryRunner qr = new QueryRunner();
        String sql = "select * from reader where readerid = ? and name = ? and type = ?";
        Object[] params = {reader.getReaderid(),reader.getName(),reader.getType()};
        try {
            Reader readers = qr.query(conn,sql,new BeanHandler<Reader>(Reader.class),params);
            return readers;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }

    }

    @Override
    public void updatePosition(Reader reader) { //该方法用于修改借阅状态
        Connection conn = Conn.getConnection();
        QueryRunner qr = new QueryRunner();
        String sql = "update reader set position = ? where readerid = ?";
        Object[] params = {reader.getPosition() + 1,reader.getReaderid()};
        try {
            qr.update(conn,sql,params);
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void deletePosition(Reader reader) { //修改借阅状态
        Connection conn = Conn.getConnection();
        QueryRunner qr = new QueryRunner();
        String sql = "update reader set position = ? where readerid = ?";
        Object[] params = {reader.getPosition() - 1,reader.getReaderid()};
        try {
            qr.update(conn,sql,params);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}