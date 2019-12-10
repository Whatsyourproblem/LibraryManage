/**
 * Company
 * Copyright (C) 2004-2019 All Rights Reserved.
 */
package org.vector.dao;

import org.vector.bean.Reader;
import org.vector.bean.Users;

import java.util.List;

/**
 * @author Administrator
 * @version $Id ReaderDao.java, v 0.1 2019-05-29 18:28 Administrator Exp $$
 */
public interface ReaderDao {    //这个借口主要用于 读者信息类与数据库交互方法
    public Reader selectSingleReaderById(Reader reader);

    public void addReader(Reader reader);

    public Reader selcetSingleRedaerByIdcard(Reader reader);

    public List<Reader> selectAllReader();

    public List<Reader> selectAllReaderByType(Reader reader);

    public List<Reader> selectAllReaderByName(Reader reader);

    public List<Reader> selectAllReaderById(Reader reader);

    public void updateReader(Reader reader);

    public Reader selectSingleReaderByIdNameType(Reader reader);

    public void updatePosition(Reader reader);

    public void deletePosition(Reader reader);

}