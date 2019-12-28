package com.restaurant.arrifqiaziz.data;

public class Constant {

    private static final String WEB_URL = "http://www.pakartanaman.com";
    private static final String WEB_URL_API = WEB_URL + "/skripsi/public";

    //auth
    public static final String LOGIN = WEB_URL_API + "/login/";
    public static final String REGISTER = WEB_URL_API + "/register/";
    public static final String GET_PROFILE = WEB_URL_API + "/user";
    public static final String UPDATE_PROFILE = WEB_URL_API + "/user/update";

    public static final String LIST_RESTAURANT = WEB_URL_API + "/list/rm";
    public static final String SEARCH_RESTAURANT = WEB_URL_API + "/list/cari";
    public static final String CREATE_RESTAURANT = WEB_URL_API + "/restaurant/create";
    public static final String DELETE_RESTAURANT = WEB_URL_API + "/restaurant/delete";

}