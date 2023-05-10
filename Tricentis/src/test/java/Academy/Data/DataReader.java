package Academy.Data;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.io.FileUtils;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

public class DataReader {
	
	public List<HashMap<String, String>> getJsonDataToHashMap() throws IOException
	{
		//Read json file as a String
				String jsonContent = FileUtils.readFileToString(new File(System.getProperty("user.dir") + "\\src\\test\\java\\Academy\\Data\\LoginScenarios.json"), 
						StandardCharsets.UTF_8);
				
				//Convert this String to a HashMap with the Jackson Databind dependency
				ObjectMapper mapper = new ObjectMapper();
				List<HashMap<String, String>> data = mapper.readValue(jsonContent, new TypeReference<List<HashMap<String, String>>>(){});
				//As a result we get these HashMaps {hmap}, {hmap01} as a List!!!
				return data;		
		
	}


}
