/**
 * Company
 * Copyright (C) 2004-2019 All Rights Reserved.
 */
package org.vector.dao;

import org.vector.bean.Fine;

/**
 * @author Administrator
 * @version $Id FineDao.java, v 0.1 2019-06-09 21:24 Administrator Exp $$
 */
public interface FineDao {
    public void update(Fine fine);

    public Fine select();
}