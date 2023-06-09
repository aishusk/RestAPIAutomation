package utilities;

import com.opencsv.CSVReader;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.TreeMap;

public class TestBase {


    @DataProvider
    public Object[] readCSV(){
        Object[] result = new Object[4];
        int counter = 0;
        URL url = getClass().getResource("TestData.csv");
        File file = new File(url.getPath());
        try(CSVReader csvReader = new CSVReader(new FileReader(file))){
            String[] data;
            while((data = csvReader.readNext())!=null){
              result[counter++] =
                      new Object[]{data[0],data[1]};
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }
}
