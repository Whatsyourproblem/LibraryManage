/**
 * Company
 * Copyright (C) 2004-2019 All Rights Reserved.
 */
package org.vector.bean;

/**
 * @author Administrator
 * @version $Id BookType.java, v 0.1 2019-05-27 19:38 Administrator Exp $$
 */
public class BookType { //图书类型实体类
    private String id; //图书类型编号
    private String typename;    //图书类型名称

    public BookType() {
    }

    public BookType(String id, String typename) {
        this.id = id;
        this.typename = typename;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTypename() {
        return typename;
    }

    public void setTypename(String typename) {
        this.typename = typename;
    }

    @Override
    public String toString() {
        return "BookType{" +
                "id=" + id +
                ", typename='" + typename + '\'' +
                '}';
    }
}