import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.google.common.base.Charsets;
import com.google.common.io.Resources;


public class JsonMap {

	public static void main(String[] args) {

		try {
			
			String jsonString = Resources.toString(Resources.getResource("parseJSON.json"), Charsets.UTF_8);			
			JSONObject jsonObject= new JSONObject(jsonString);
			JSONArray jsonArray= new JSONArray(jsonObject.get("buIdRefData").toString());
			for(int i=0;i<jsonArray.length();i++)
			{
				getDetails(jsonArray.getJSONObject(i).toString());
			}
			
		} catch (JsonGenerationException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (JSONException e) {
						e.printStackTrace();
		}
	}
	
	
	private static void getDetails(String jsonString)
	{
		
		try {
			String buId=null;
			String name = null;
			String number= null;
			
			Map<String, Object> myMap=jsonString2Map(jsonString);
			Map<String, Object> divisionDepartment = jsonString2Map(myMap.get("divisionDepartment").toString());
			if(divisionDepartment.get("buId")!=null)
			{
				buId=divisionDepartment.get("buId").toString();
				//name= divisionDepartment.get("name").toString(); 
				number=divisionDepartment.get("number").toString();
			}
			else{
				Map<String, Object> department = jsonString2Map(divisionDepartment.get("department").toString());
				buId=department.get("buId").toString();
				//name= department.get("name").toString(); 
				number=department.get("number").toString();
			}
			System.out.println("buId : "+buId);
			//System.out.println("name : "+name);
			System.out.println("number : "+number);
			
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
	}
	private static Map<String, Object> jsonString2Map(String jsonString)
			throws JSONException {
		Map<String, Object> keys = new HashMap();
		JSONObject jsonObject = new JSONObject(jsonString);
		Iterator<?> keyset = jsonObject.keys();
		while (keyset.hasNext()) {
			String key = (String) keyset.next();
			Object value = jsonObject.get(key);	
			keys.put(key, value);
		}
		return keys;
	}


}