package TestComponents;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.io.FileUtils;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.List;

public class DemodatProviders {


    @Test(dataProvider = "getData")
    public void Login(HashMap<String,String> map) {
        System.out.println(map.get("userName"));
        System.out.println(map.get("password"));
    }


    @DataProvider
    public Object[][] getData() throws IOException {
       /* HashMap<String, String> map = new HashMap<String, String>();
        map.put("userName", "apurvanaik42");
        map.put("password", "ABC@1234");


        HashMap<String, String> map1 = new HashMap<String, String>();
        map1.put("userName", "ajinkya24");
        map1.put("password", "xyz9876");*/
        DemodatProviders demodatProviders =new DemodatProviders();
        List<HashMap<String, String>> data =demodatProviders.convertJsontoMap();



        return new Object[][]{{data.get(0)},{data.get(1)}};
    }



    public List<HashMap<String, String>> convertJsontoMap() throws IOException {
//Reading Json to String
        String jsonContent =FileUtils.readFileToString(new File(System.getProperty("user.dir")+"\\src\\test\\java\\TestComponents\\Dataprobvider.json"), StandardCharsets.UTF_8);
//C:\Users\91762\IdeaProjects\EcommorceFrameworksrc\test\java\TestComponents\Dataprobvider.json
        //C:\Users\91762\IdeaProjects\EcommorceFramework\src\test\java\TestComponents\Dataprobvider.json
//Converting String to List of HashMap
        ObjectMapper mapper =new ObjectMapper();
  List<HashMap<String,String>> data = mapper.readValue(jsonContent, new TypeReference<List<HashMap<String,String>>>() {
   });

   return data;


}}
