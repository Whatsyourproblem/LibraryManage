/**
 * Company
 * Copyright (C) 2004-2019 All Rights Reserved.
 */
package org.vector.daoipl;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.vector.bean.ReaderType;
import org.vector.dao.Conn;
import org.vector.dao.ReaderTypeDao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

/**
 * @author Administrator
 * @version $Id ReaderTypeDaoIpl.java, v 0.1 2019-06-10 20:47 Administrator Exp $$
 */
public class ReaderTypeDaoIpl implements ReaderTypeDao {

    @Override
    public List<ReaderType> selectAllInformation() {    //查询所有的读者类型信息
        Connection conn = Conn.getConnection();
        QueryRunner qr = new QueryRunner();
        String sql = "select * from readertype";
        try {
            List<ReaderType> list = qr.query(conn,sql,new BeanListHandler<ReaderType>(ReaderType.class));
            return list;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public ReaderType selectSingleInformationById(ReaderType readerType) {  //通过读者类型编号来查询表
        Connection conn = Conn.getConnection();
        QueryRunner qr = new QueryRunner();
        String sql = "select * from readertype where id = ? or name = ?";
        Object[] params = {readerType.getId(),readerType.getName()};
        try {
            ReaderType readerTypes = qr.query(conn,sql,new BeanHandler<ReaderType>(ReaderType.class),params);
            return readerTypes;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public ReaderType seletSingleInformationByName(ReaderType readerType) { //通过类型名字来查询数据
        Connection conn = Conn.getConnection();
        QueryRunner qr = new QueryRunner();
        String sql = "select * from readertype where name = ?";
        try {
            ReaderType readerTypes = qr.query(conn,sql,new BeanHandler<ReaderType>(ReaderType.class),readerType.getName());
            return readerTypes;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public void updateSingleInformation(ReaderType readerType) {    //通过id来修改数据
        Connection conn = Conn.getConnection();
        QueryRunner qr = new QueryRunner();
        String sql = "update readertype set name = ?,maxborrownum = ?,limitation = ? where id = ?";
        Object[] params = {readerType.getName(),readerType.getMaxborrownum(),readerType.getLimitation(),readerType.getId()};
        try {
            qr.update(conn,sql,params);
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void addInformation(ReaderType readerType) {     //添加信息
        Connection conn = Conn.getConnection();
        QueryRunner qr = new QueryRunner();
        String sql = "insert into readertype (id,name,maxborrownum,limitation) values (?,?,?,?)";
        Object[] params = {readerType.getId(),readerType.getName(),readerType.getMaxborrownum(),readerType.getLimitation()};
        try {
            qr.update(conn,sql,params);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteSingleInformation(ReaderType readerType) {    //通过读者类型id来删除某个用户
        Connection conn = Conn.getConnection();
        QueryRunner qr = new QueryRunner();
        String sql = "delete from readertype where id = ? and name = ? and maxborrownum = ? and limitation = ?";
        Object[] params = {readerType.getId(),readerType.getName(),readerType.getMaxborrownum(),readerType.getLimitation()};
        try {
            qr.update(conn,sql,params);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}