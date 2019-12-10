/**
 * Company
 * Copyright (C) 2004-2019 All Rights Reserved.
 */
package org.vector.org.vector.service;

import org.vector.bean.Users;
import org.vector.daoipl.UsersDaoIpl;

import java.util.List;

/**
 * @author Administrator
 * @version $Id Check.java, v 0.1 2019-05-28 12:45 Administrator Exp $$
 */
public class Check {
    public static int isExist(Users users){
        List<Users> list = new UsersDaoIpl().selectAllUser();
        for (Users user:list) {
            if(users.getUsername().equals(user.getUsername())){//用户名已经存在
                return 0;   //用0表示用户名已经存在
            }
        }
        return 1;   //用1表示用户名不存在
    }

    public static int checkIdcard(String s){    //验证身份证是否符合标准
        int flag = 0;
        char[] arr = s.toCharArray();
        for (int i = 0;i < arr.length;i++ ){
            if(arr[i] < 48 || arr[i] > 57){
                flag = 1;
                break;
            }
        }
        if(flag == 0 && (arr.length == 18)){
            return 1;   //符合
        }else{
            return 2;   //不符合
        }
    }

    public static int checkPhone(String s){ //检查电话号码是否符合规范
        int flag = 0;
        char[] arr = s.toCharArray();
        for (int i = 0;i < arr.length;i++ ){
            if(arr[i] < 48 || arr[i] > 57){
                flag = 1;
                break;
            }
        }
        if(flag == 0 && (arr.length == 11)){
            return 1;   //符合
        }else{
            return 2;   //不符合
        }
    }

    public static int checkAge(int age){   //验证年龄
        if(age < 0 || age > 150){
            return 2;   //不符合
        }
        return 1;   //符合
    }

    public static int checkId(String id){   //验证编号
        int flag = 0;
        char[] arr = id.toCharArray();
        for (int i = 0;i < arr.length;i++ ){
            if(arr[i] < 48 || arr[i] > 57){
                flag = 1;
            }
        }
        if(flag == 0 && arr.length == 8){
            return 1; //符合
        }else{
            return 2;//不符合
        }
    }

    public static int checkIsbn(String isbn){
        int flag = 0;
        char[] arr = isbn.toCharArray();
        for(int i = 0;i < arr.length;i++){
            if(arr[i] < 48 || arr[i] > 57){
                flag = 1;
            }
        }
        if(flag == 0 && arr.length == 8){
            return 1;
        }else{
            return 2;
        }
    }

    public static boolean checkNum(String num){ //检查印刷次数
        int flag = 0;
        char[] arr = num.toCharArray();
        for(int i = 0;i < arr.length;i++){
            if(arr[i] < 48 || arr[i] > 57){
                flag = 1;
            }
        }
        if(flag == 0 && arr.length <= 3){
            return true;
        }else{
            return false;
        }
    }

    public static boolean checkPrice(String price){ //检查价格
        int flag = 0;
        Double b = Double.parseDouble(price);
        if(b < 0 || b > 100000000){
            return false;
        }else{
            return true;
        }
    }
}