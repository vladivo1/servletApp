package com.example.demo.session;

import java.util.HashMap;
import java.util.Map;

public class LoginRepository {

     public static Map<String,String> mapUser = new HashMap<>();

     public static void initMapUser(){
         mapUser.put("admin", "password");
         mapUser.put("vlad", "vlad");

     }

     public static boolean chekMapUser( String login, String password) {
         if (mapUser.containsKey(login) && mapUser.get(login).equals(password)) {
             return true;
         }
         return false;
     }
}
