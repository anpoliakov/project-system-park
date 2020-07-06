package model.util;

public abstract class Constants {
    static{
        OK_OPERATION = 1;
        DEFAULT_OPERATION = 0;
        ERROR_OPERATION = -1;
        PATH_REGISTER_SERVLET = "/registration";
        PATH_LOGIN_SERVLET = "/login";
        ENCODING_DEFAULT = "UTF-8";
        HOME_PAGE = "/view/systemPark.html";
        ATT_ID_PERSON = "idPerson";
        ATT_LOGIN_PERSON = "loginPerson";

    }

    public static final int OK_OPERATION;
    public static final int DEFAULT_OPERATION;
    public static final int ERROR_OPERATION;
    public static final String PATH_REGISTER_SERVLET;
    public static final String PATH_LOGIN_SERVLET;
    public static final String ENCODING_DEFAULT;
    public static final String HOME_PAGE;
    public static final String ATT_ID_PERSON;
    public static final String ATT_LOGIN_PERSON;
}
