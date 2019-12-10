package org.vector.dao;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import org.apache.commons.dbutils.QueryRunner;

import javax.sql.DataSource;
import java.sql.SQLException;

public class BaseUtils {
    // 第一步 初始化 c3p0连接池
    private static DataSource dataSource = null;//DataSource 数据源
    //加载src目录下的配置文件
    static {
        dataSource = new ComboPooledDataSource();
    }
    public static QueryRunner getQueryRunner() {
        //第一步 创建 QueryRunner 对象 传入连接池对象
        QueryRunner query = new QueryRunner(dataSource);
        //为什么要这样做 为的是从数据源中获取连接 不用关闭
        return query;
    }
    /***
     * 公共方法
     * @param sql
     * @param arr
     * @return
     */
    //为什么要操作jdbc? 实现对数据库的增删改查
    public static boolean addUpdateDelet(String sql,Object[] arr) {
        QueryRunner qr=getQueryRunner();
        int count;
        try {
            count = qr.update(sql, arr);
            if(count>0){
                return true;
            }else{
                return false;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}