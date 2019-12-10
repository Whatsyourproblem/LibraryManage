/**
 * Company
 * Copyright (C) 2004-2019 All Rights Reserved.
 */
package org.vector.dao;

import org.vector.bean.BorrowBook;

import java.util.List;

/**
 * @author Administrator
 * @version $Id BorrowBookDao.java, v 0.1 2019-06-03 19:41 Administrator Exp $$
 */
public interface BorrowBookDao {

    public List<BorrowBook> selectAllBorrowBook();

    public void addBorrowBookInformation(BorrowBook borrowbook);

    public BorrowBook selectSingleBorrowBook(BorrowBook borrowBook);

    public void updateSingleBorrowBook(BorrowBook borrowBook);

    public void updatePosition1(BorrowBook borrowBook);

    public void updatePosition2(BorrowBook borrowBook);
}