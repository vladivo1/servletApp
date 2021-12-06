package com.example.demo.filters;

import java.util.HashMap;
import java.util.Map;

public class UriRepository {

    public  static int uriID = 0;
    public static Map<Integer,String>  uriMap = new HashMap<>();

    public static void initUriMap(){

        uriMap.put(++uriID, "/demo/saveServlet");
        uriMap.put(++uriID, "/demo/loginServlet");
        uriMap.put(++uriID, "/demo/viewServlet");

    }

    public static boolean checkUriMap(String uri){
        if (uriMap.containsValue(uri)) {
            return true;
        }
        return false;

    }

}
