package utilities;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertiesReader {

    private Properties properties;
    public PropertiesReader(){
        properties = new Properties();
        String env = null != System.getenv("env") ? System.getenv("env") : "staging";
        InputStream inputStream = this.getClass().getClassLoader().getResourceAsStream(env+".properties");
        try {
            properties.load(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Properties getProperties(){
        return  properties;
    }
    public String getHost(){
        return properties.getProperty("host");
    }
}
