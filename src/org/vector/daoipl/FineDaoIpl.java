/**
 * Company
 * Copyright (C) 2004-2019 All Rights Reserved.
 */
package org.vector.daoipl;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.vector.bean.Fine;
import org.vector.dao.Conn;
import org.vector.dao.FineDao;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * @author Administrator
 * @version $Id FineDaoIpl.java, v 0.1 2019-06-09 21:24 Administrator Exp $$
 */
public class FineDaoIpl implements FineDao {
    @Override
    public void update(Fine fine) {
        Connection conn = Conn.getConnection();
        QueryRunner qr = new QueryRunner();
        String sql = "update fineset set fineset = ?";
        Object[] params = {fine.getFineset()};
        try {
            qr.update(conn,sql,params);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Fine select() {
        Connection conn = Conn.getConnection();
        QueryRunner qr = new QueryRunner();
        String sql = "select * from fineset";
        try {
            Fine fine = qr.query(conn,sql,new BeanHandler<Fine>(Fine.class));
            return fine;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
}