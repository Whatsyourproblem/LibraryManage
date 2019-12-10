/**
 * Company
 * Copyright (C) 2004-2019 All Rights Reserved.
 */
package org.vector.daoipl;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.vector.bean.Users;
import org.vector.dao.Conn;
import org.vector.dao.UsersDao;

import javax.swing.table.TableColumn;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

/**
 * @author Administrator
 * @version $Id UsersDaoIpl.java, v 0.1 2019-05-28 12:41 Administrator Exp $$
 */
public class UsersDaoIpl implements UsersDao {

    public List<Users> selectAllUser() {    //查询所有的用户
        Connection conn = Conn.getConnection();
        QueryRunner qr = new QueryRunner();
        String sql = "select * from users";
        try {
            List<Users> list = qr.query(conn,sql,new BeanListHandler<Users>(Users.class));
            return list;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public void addUser(Users users) {  //向表中插入数据
        Connection conn = Conn.getConnection();
        QueryRunner qr = new QueryRunner();
        String sql = "insert into users (username,password,useridcard) values (?,?,?)";
        Object[] params = {users.getUsername(),users.getPassword(),users.getUseridcard()};
        try {
            qr.update(conn,sql,params);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Users selectSingleUser(Users users) {    //返回单个用户,用于验证某个用户是否存在
        Connection conn = Conn.getConnection();
        QueryRunner qr = new QueryRunner();
        String sql = "select * from users where username = ? and useridcard = ?";
        Object[] params = {users.getUsername(),users.getUseridcard()};
        try {
            Users user = qr.query(conn,sql,new BeanHandler<Users>(Users.class),params);
            return user;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public Users selectSgUserByName(Users users) {  //通过username来查,返回单个用户
        Connection conn = Conn.getConnection();
        QueryRunner qr = new QueryRunner();
        String sql = "select * from users where username = ?";
        try {
            Users user = qr.query(conn,sql,new BeanHandler<Users>(Users.class),users.getUsername());
            return user;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public void deleteSingleUsers(Users users) {//通过username和idcard来删除
        Connection conn = Conn.getConnection();
        QueryRunner qr = new QueryRunner();
        String sql = "delete from users where username = ? and useridcard = ?";
        Object[] params = {users.getUsername(),users.getUseridcard()};
        try {
            qr.update(conn,sql,params);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Users> selectUserByIdcard(Users users) {
        Connection conn = Conn.getConnection();
        QueryRunner qr = new QueryRunner();
        String sql = "select * from users where useridcard = ?";
        try {
            List<Users> list = qr.query(conn,sql,new BeanListHandler<Users>(Users.class),users.getUseridcard());
            return list;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
}