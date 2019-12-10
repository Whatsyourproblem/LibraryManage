/**
 * Company
 * Copyright (C) 2004-2019 All Rights Reserved.
 */
package org.vector.dao;

import org.vector.bean.Book;
import org.vector.bean.BookType;

import java.util.List;

/**
 * @author Administrator
 * @version $Id BookTypeDao.java, v 0.1 2019-06-09 17:22 Administrator Exp $$
 */
public interface BookTypeDao {
    public List<BookType> selectAllBookTypeInformation();

    public BookType selectSingleInformationById(BookType bookType);

    public void addInformation(BookType bookType);

    public BookType selectSingleIfmaById(BookType bookType);

    public void updateSingleIfmaById(BookType bookType);

    public void deleteSingleIfmaById(BookType bookType);
}