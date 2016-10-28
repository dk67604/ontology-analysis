package edu.uga.cs.thinc.bioportalrest.utilities;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

public class RestConnector {
	static final String REST_URL = "http://data.bioontology.org";
    
  
    public static String getJsonContent(String urlToGet,String API_KEY) {
        URL url;
        HttpURLConnection conn;
        BufferedReader rd;
        String line;
        String result = "";
        try {
        	
            url = new URL(urlToGet);
            conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setRequestProperty("Authorization", "apikey token=" + API_KEY);
            conn.setRequestProperty("Accept", "application/json");
            int responseCode=conn.getResponseCode();
            if(responseCode==500){
            	System.out.println("Response Code:"+responseCode);
            	return null;
            }
            rd = new BufferedReader(
                    new InputStreamReader(conn.getInputStream()));
            while ((line = rd.readLine()) != null) {
                result += line;
            }
            rd.close();
            
        } catch(FileNotFoundException e){
        	System.out.println("Fail to get string response");
        	return null;
        }
        
        catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }
    
    public static String getOntologyName(String ontologyUrl) throws MalformedURLException{
    	URL url=new URL(ontologyUrl);
    	String urlPath=url.getPath();
    	String[] tempArray=urlPath.split("/");
    //	System.out.println(tempArray[2]);
    	return tempArray[2];
    }
    
	public static JsonNode jsonToNode(String json) {
		JsonNode root = null;
		ObjectMapper mapper = new ObjectMapper();
		try {
			root = mapper.readTree(json);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return root;
	}
}
