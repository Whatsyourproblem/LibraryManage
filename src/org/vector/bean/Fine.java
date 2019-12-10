/**
 * Company
 * Copyright (C) 2004-2019 All Rights Reserved.
 */
package org.vector.bean;

/**
 * @author Administrator
 * @version $Id Fine.java, v 0.1 2019-06-09 18:34 Administrator Exp $$
 */
public class Fine { //罚金设置
    private String fineset;

    public Fine() {
    }

    public Fine(String fineset) {
        this.fineset = fineset;
    }

    public String getFineset() {
        return fineset;
    }

    public void setFineset(String fineset) {
        this.fineset = fineset;
    }
}