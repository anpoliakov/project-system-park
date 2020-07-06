package model.util;

import org.apache.log4j.Logger;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public final class ConfigurationManager {
    final static Logger logger = Logger.getLogger(ConfigurationManager.class);
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
            logger.error("Путь не найден", e);
        }finally {
            if(streamConfigDataBase != null){
                try {
                    streamConfigDataBase.close();
                    streamRequestsSQL.close();
                } catch (IOException e) {
                    logger.error("Не удалось закрыть поток", e);
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
        return properties.getProperty("USER_NAME_DB");
    }

    // получение из properties пароля к БД
    public String getPasswordDB(){
        return properties.getProperty("PASSWORD_DB");
    }

    // получение из properties пути к драйверу
    public String getDriverDB(){
        return properties.getProperty("DRIVER_DB");
    }

    // получение из properties URL для подключения
    public String getUrlDB(){
        return properties.getProperty("URL_DB");
    }

    // Получаем нужный размер для Connection pool
    public int getSizePool(){
        return Integer.parseInt(properties.getProperty("SIZE_POOL"));
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
