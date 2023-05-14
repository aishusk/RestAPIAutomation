package helpers;

import utilities.PropertiesReader;

public class APIHelper {

    private static final PropertiesReader propertiesReader = new PropertiesReader();

    public static String hostUrl = String.format("%s/api/users",propertiesReader.getHost());
}
