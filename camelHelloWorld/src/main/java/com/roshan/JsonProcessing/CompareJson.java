package com.roshan.JsonProcessing;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;
import com.google.common.base.Charsets;
import com.google.common.io.Resources;

public class CompareJson {

	public static void main(String args[]) {
		System.out.println("Strting Json Analysis !!! ");
		try {

			String jsonString1 = Resources.toString(Resources.getResource("parseJSON.json"), Charsets.UTF_8);
			JSONObject jsonObject1 = new JSONObject(jsonString1);
			JSONArray jsonArray1 = new JSONArray(jsonObject1.get("studends").toString());
			List<Map<String,Object>> jMapList1 = new ArrayList<Map<String,Object>>();
			for(int i=0;i<jsonArray1.length();i++){
				jMapList1.add(JsonstringToMap.jsonString2Map(jsonArray1.getString(i)));
				
			}
			
			String jsonString2 = Resources.toString(Resources.getResource("NewJSON.json"), Charsets.UTF_8);
			JSONObject jsonObject2 = new JSONObject(jsonString2);
			JSONArray jsonArray2 = new JSONArray(jsonObject2.get("studends").toString());
			List<Map<String,Object>> jMapList2 = new ArrayList<Map<String,Object>>();
			for(int i=0;i<jsonArray2.length();i++){
				jMapList2.add(JsonstringToMap.jsonString2Map(jsonArray2.getString(i)));
				
			}			
			CompareJson(jMapList1,jMapList2);
		} catch (JSONException e) {
			e.printStackTrace();
		} catch (IOException e) {			
			e.printStackTrace();
		}
	}

	private static void CompareJson(final List<Map<String, Object>> jMapList1, final List<Map<String, Object>> jMapList2) {
		
		for( Map<String, Object> jsonEntry1:jMapList1)
		{
			
			for( Map<String, Object> jsonEntry2:jMapList2)
			{
				if(jsonEntry1.get("name").toString().equals(jsonEntry2.get("name").toString())){
					System.out.println("Coparing JSON for:" + jsonEntry1.get("name").toString());
					compare(jsonEntry1,jsonEntry2);
					
				}
			}
			
		}
	}

	private static void compare(Map<String, Object> mapA, Map<String, Object> mapB) {
		int diff=0;
		try{
			
			
	        for (String k : mapB.keySet())
	        {
	            if (!mapA.get(k).equals(mapB.get(k))){
	                System.out.println("Difftent in Key : "+k +" |  Value A: " +mapA.get(k) +" | Value B: "+ mapB.get(k));
	                diff++;
	            }
	        } 
	        
	    } catch (NullPointerException np) {
	    	System.out.println("Nullpointer :");
	    }
		if(diff==0){
			System.out.println("JSON are Equal");
		}
		System.out.println("End==========================");
		
	}
}
