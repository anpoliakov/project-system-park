package models.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public final class ConfigurationManager {
    private static volatile ConfigurationManager instance;
    private Properties properties;

    private final String PATH_FILE_CONFIG_DB = "configurationDB.properties";
    private final String PATH_FILE_REQUESTS_SQL = "requestsSQL.properties";

    private InputStream streamConfigDataBase;
    private InputStream streamRequestsSQL;

    /* КОНСТРУКТОР */
    private ConfigurationManager() {
        properties = new Properties();
        // метод getClassLoader() возвращает загрузчик загрузивший этот класс
        try {
            streamConfigDataBase = this.getClass().getClassLoader().getResourceAsStream(PATH_FILE_CONFIG_DB);
            streamRequestsSQL = this.getClass().getClassLoader().getResourceAsStream(PATH_FILE_REQUESTS_SQL);
            properties.load(streamConfigDataBase);
            properties.load(streamRequestsSQL);
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if(streamConfigDataBase != null){
                try {
                    streamConfigDataBase.close();
                    streamRequestsSQL.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /* ОСНОВА СИНГЛТОН ПАТТЕРНА */
    public static ConfigurationManager getInstance(){
        if(instance == null){
            synchronized(ConfigurationManager.class){
                if(instance == null){
                    instance = new ConfigurationManager();
                }
            }
        }
        return instance;
    }


    // получение из properties имени юзера для БД
    public String getUserNameDB(){
        return properties.getProperty("userNameDB");
    }

    // получение из properties пароля к БД
    public String getPasswordDB(){
        return properties.getProperty("passwordDB");
    }

    // получение из properties пути к драйверу
    public String getDriverDB(){
        return properties.getProperty("driverDB");
    }

    // получение из properties URL для подключения
    public String getUrlDB(){
        return properties.getProperty("urlDB");
    }

    // Получаем нужный размер для Connection pool
    public int getSizePool(){
        return Integer.parseInt(properties.getProperty("sizePool"));
    }



    /* Возвращает SQL запрос по ключу */
    public String getSQLRequest(String nameKey){
        String valueKey = null;

        if(properties.containsKey(nameKey)){
            valueKey = properties.getProperty(nameKey);
        }else{
            // EXCEPTION !!! NO search key
            System.out.println("НЕТ искомого ключа - " + nameKey + " смотри класс ConfigurationManager");
        }
        return valueKey;
    }

    /*
    * Можно добавить КЛЮЧ проверки - так как папка resources тоже упакуется в проект (а вдруг мы хотим подтянуть настройки
    * с левого файла)
    *
    * */

}
