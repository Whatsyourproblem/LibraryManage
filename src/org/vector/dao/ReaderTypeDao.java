/**
 * Company
 * Copyright (C) 2004-2019 All Rights Reserved.
 */
package org.vector.dao;

import org.vector.bean.ReaderType;

import java.util.List;

/**
 * @author Administrator
 * @version $Id ReaderTypeDao.java, v 0.1 2019-06-10 20:46 Administrator Exp $$
 */
public interface ReaderTypeDao {
    public List<ReaderType> selectAllInformation();

    public ReaderType selectSingleInformationById(ReaderType readerType);

    public ReaderType seletSingleInformationByName(ReaderType readerType);

    public void updateSingleInformation(ReaderType readerType);

    public void addInformation(ReaderType readerType);

    public void deleteSingleInformation(ReaderType readerType);
}