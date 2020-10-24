package utils;

import java.io.IOException;
import java.util.Properties;

/**
 * @program: javaweb
 * 描述：
 * @author: LJQ
 * @create: 2020-09-10 10:46
 **/
public class PropUtils {
    private static Properties properties=new Properties();

    static {
        try {
            properties.load(PropUtils.class.getResourceAsStream("/jdbc.properties"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static String get(String key){
        return properties.getProperty(key);
    }
}
