package utile;

import java.io.IOException;
import java.util.Properties;

public final class Util {
    private static final Properties PROPERTIES = new Properties();

    static {
        loadProperties();
    }

    private Util(){}

    public static String get(String key){
        return PROPERTIES.getProperty(key);
    }

    private static void loadProperties(){
        try(var inputStream = Util.class
                .getClassLoader().getResourceAsStream("application.properties")){
            PROPERTIES.load(inputStream);
        } catch (IOException e){
            throw new RuntimeException(e);
        }
    }
}
