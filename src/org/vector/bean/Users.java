/**
 * Company
 * Copyright (C) 2004-2019 All Rights Reserved.
 */
package org.vector.bean;

/**
 * @author Administrator
 * @version $Id Users.java, v 0.1 2019-05-28 10:56 Administrator Exp $$
 */
public class Users {
    private String username;
    private String password;
    private String useridcard;

    public Users() {

    }

    public Users(String username, String password, String useridcard) {
        this.username = username;
        this.password = password;
        this.useridcard = useridcard;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUseridcard() {
        return useridcard;
    }

    public void setUseridcard(String useridcard) {
        this.useridcard = useridcard;
    }
}