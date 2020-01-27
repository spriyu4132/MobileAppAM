package com.example.application1.db;

public class Utils {

    public static String createUrl(String route)
    {
        return Constants.httpUrl + "/" + route;
    }
}
