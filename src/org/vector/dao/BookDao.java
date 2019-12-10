/**
 * Company
 * Copyright (C) 2004-2019 All Rights Reserved.
 */
package org.vector.dao;

import org.vector.bean.Book;

import java.util.List;

/**
 * @author Administrator
 * @version $Id BookDao.java, v 0.1 2019-06-01 17:20 Administrator Exp $$
 */
public interface BookDao {  //用于放book类与数据库的交互方法
    public Book selectSingleBookByIsbn(Book book);

    public Book selectSingleBooksByIsbn(Book book);

    public void addBookInformation(Book book);

    public void addBooksInformation(Book book); //备份数据库

    public List<Book> selectAllBookInformation();

    public List<Book> selectBookInformationByType(Book book);

    public List<Book> selectBookInformationByBookName(Book book);

    public void updateBookInformationByIsbn(Book book);

    public void deleteSingleBookInformationByIsbn(Book book);

}