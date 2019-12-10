/**
 * Company
 * Copyright (C) 2004-2019 All Rights Reserved.
 */
package org.vector.dao;

import org.vector.bean.Users;

import java.util.List;

/**
 * @author Administrator
 * @version $Id UsersDao.java, v 0.1 2019-05-28 12:41 Administrator Exp $$
 */
public interface UsersDao {
    public List<Users> selectAllUser();

    public void addUser(Users users);

    public Users selectSingleUser(Users users); //通过username和idcard来查

    public Users selectSgUserByName(Users users);   //通过username来查

    public  void deleteSingleUsers(Users users);    //通过username和idcard来删除

    public List<Users> selectUserByIdcard(Users users); //通过idcard来查询

}