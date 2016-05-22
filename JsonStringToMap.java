
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

public class JsonStringToMap {

	public static void main(String[] args) {

		try {

			String jsonString = Resources.toString(Resources.getResource("CasHOM.json"), Charsets.UTF_8);
			//System.out.println(jsonString);
			JSONObject jsonObject = new JSONObject(jsonString);
			JSONArray jsonArray = new JSONArray(jsonObject.get("buIdRefData").toString());
			for (int i = 0; i < jsonArray.length(); i++) {
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

	private static void getDetails(String jsonString) {

		HomeOfficeMerchandise hOM = new HomeOfficeMerchandise();

		try {
			Long buId = null;
			String deptName = null;
			Long divNum = null;
			Long deptNum = null;
			String deptType = null;

			Map<String, Object> myMap = jsonString2Map(jsonString);
			Iterator<Map.Entry<String, Object>> mapIt = myMap.entrySet().iterator();
			while (mapIt.hasNext()) {
				 Map.Entry<String, Object> entry = mapIt.next();
				    System.out.println( entry.getKey() + " ----> " + entry.getValue());
			}
			
			//System.out.println(myMap);
			/*Map<String, Object> divisionDepartment = jsonString2Map(myMap.get("divisionDepartment").toString());
			Map<String, Object> department = jsonString2Map(divisionDepartment.get("department").toString());

			buId =  Long.parseLong(department.get("buId").toString());
			hOM.setBuId(buId);

			deptNum = Long.parseLong(department.get("number").toString());
			hOM.setDeptNum(deptNum);
			divNum =  Long.parseLong(divisionDepartment.get("number").toString());
			hOM.setDivNum(divNum);
			deptName = department.get("name").toString();
			hOM.setDeptName(deptName);
			hOM.setDeptType(null);
			String hoDeptFullName = divNum + "-" + deptNum +" "+deptName +" "+deptType;
			hOM.setHoDeptFullName(hoDeptFullName);

			System.out.println(hOM);*/
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	private static Map<String, Object> jsonString2Map(String jsonString) throws JSONException
	{
		return jsonString2Map( jsonString, null);
	}

	private static Map<String, Object> jsonString2Map(String jsonString,String prependKey)
			throws JSONException {
		Map<String, Object> jsonMap = new HashMap<String, Object>();		
		JSONObject jsonObject = new JSONObject(jsonString);
		Iterator<?> keyset = jsonObject.keys();
		while (keyset.hasNext()) {
			String key = (String) keyset.next();
			String actualKey=key;
			if(null!= prependKey){
				actualKey=prependKey+"."+key;
			}
			Object value = jsonObject.get(key);
			if (value instanceof JSONObject) {				
				Map<String, Object> temp = jsonString2Map(value.toString(), actualKey);
				jsonMap.putAll(temp);
			}			
			jsonMap.put(actualKey, value);
		}
		return jsonMap;
	}

}
