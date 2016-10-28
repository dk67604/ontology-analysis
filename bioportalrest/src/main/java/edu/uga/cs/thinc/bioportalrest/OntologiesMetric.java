package edu.uga.cs.thinc.bioportalrest;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import edu.uga.cs.thinc.bioportalrest.beans.MetricBean;
import edu.uga.cs.thinc.bioportalrest.utilities.RestConnector;
import edu.uga.cs.thinc.bioportalrest.utilities.SpreadsheetUtilities;
import edu.uga.cs.thinc.complexconcepts.OntologyMetricPrinter;
import edu.uga.cs.thinc.complexconcepts.tm.ComplexConceptMetric;

public class OntologiesMetric {

	static final String REST_URL = "http://data.bioontology.org";
    static final ObjectMapper mapper = new ObjectMapper();
    static final String API_KEY = "6fb1ca19-fb4c-45ec-b4a5-23e1aa0bda98";
    public static Map<String,MetricBean> getOntologiesMetric(String url) throws MalformedURLException{
    	Map<String,MetricBean> metricMap=new HashMap<>();
    	String metric_string = RestConnector.getJsonContent(REST_URL+url,API_KEY);
        JsonNode ontologiesMetric = RestConnector.jsonToNode(metric_string);
        System.out.println("Metric Size:"+ontologiesMetric.size());
        for(JsonNode ontologyMetric:ontologiesMetric){
        	MetricBean metricBean=new MetricBean();
        	String ontologyURL=ontologyMetric.get("id").asText();      
        	metricBean.setClasses(ontologyMetric.get("classes").asText());
        	metricBean.setCreated(ontologyMetric.get("created").asText());
        	metricBean.setIndividuals(ontologyMetric.get("individuals").asText());
        	metricBean.setProperties(ontologyMetric.get("properties").asText());
        	metricBean.setMaxDepth(ontologyMetric.get("maxDepth").asText());
        	metricBean.setMaxChildCount(ontologyMetric.get("maxChildCount").asText());
        	metricBean.setAverageChildCount(ontologyMetric.get("averageChildCount").asText());
        	metricBean.setClassesWithOneChild(ontologyMetric.get("classesWithOneChild").asText());
        	metricBean.setClassesWithMoreThan25Children(ontologyMetric.get("classesWithMoreThan25Children").asText());
        	metricBean.setClassesWithNoDefinition(ontologyMetric.get("classesWithNoDefinition").asText());
        	metricMap.put(RestConnector.getOntologyName(ontologyURL), metricBean);
        
        }
    	
    	return metricMap;
    }
    
    

    public Map<String,ComplexConceptMetric> getOntologies(String url){
    	ComplexConceptMetric cms=null;
    	OntologyMetricPrinter omp=new OntologyMetricPrinter();
    	Map<String,ComplexConceptMetric> metricMap=new HashMap<>();
    	String metric_string = RestConnector.getJsonContent(REST_URL+url,API_KEY);
        JsonNode ontologiesMetric = RestConnector.jsonToNode(metric_string);
        for(JsonNode ontologyMetric:ontologiesMetric){
        	String acronym=ontologyMetric.get("acronym").asText();
        	cms=omp.getComplexConcept(acronym);
        	metricMap.put(acronym, cms);
        }
    	return metricMap;
    }

    public static void main(String[] args){
    	try {
    		Map<String,MetricBean>  metricBeanMap=OntologiesMetric.getOntologiesMetric("/metrics");
    		Map<String,MetricBean> sortedMap=new TreeMap<>(metricBeanMap);
	        Map<String,JsonNode> mappingJsonNode=SpreadsheetUtilities.getMappingList(sortedMap,API_KEY);
	        Map<String,JsonNode> sortedmappingJsonNode=new TreeMap<>(mappingJsonNode);
    		Workbook workbook = new XSSFWorkbook();
		    workbook=SpreadsheetUtilities.createExcelSheet(workbook, sortedMap);
		    workbook=SpreadsheetUtilities.createMappingSheet(workbook, sortedMap,sortedmappingJsonNode);
		    FileOutputStream fileOutputStream=new FileOutputStream("/home/dkumar/OntologiesSheet.xlsx");
		    workbook.write(fileOutputStream);
		    fileOutputStream.close();
		    workbook.close();
		 	
		 	System.out.println("Excel sheet generated successfully...");
    	} catch (MalformedURLException e) {
			e.printStackTrace();
		}catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
    }
}
