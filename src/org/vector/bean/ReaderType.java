/**
 * Company
 * Copyright (C) 2004-2019 All Rights Reserved.
 */
package org.vector.bean;

/**
 * @author Administrator
 * @version $Id ReaderType.java, v 0.1 2019-05-29 21:59 Administrator Exp $$
 */
public class ReaderType {   //读者类型实体类
    private String id;  //读者类型编号
    private String name;    //读者类型名称
    private String maxborrownum;    //最多
    private String limitation;

    public ReaderType() {
    }

    public ReaderType(String id, String name, String maxborrownum, String limitation) {
        this.id = id;
        this.name = name;
        this.maxborrownum = maxborrownum;
        this.limitation = limitation;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMaxborrownum() {
        return maxborrownum;
    }

    public void setMaxborrownum(String maxborrownum) {
        this.maxborrownum = maxborrownum;
    }

    public String getLimitation() {
        return limitation;
    }

    public void setLimitation(String limitation) {
        this.limitation = limitation;
    }
}