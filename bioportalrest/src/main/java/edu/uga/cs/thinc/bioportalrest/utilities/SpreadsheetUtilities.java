package edu.uga.cs.thinc.bioportalrest.utilities;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

import com.fasterxml.jackson.databind.JsonNode;

import edu.uga.cs.thinc.bioportalrest.beans.MetricBean;

public class SpreadsheetUtilities {
	static final String REST_URL = "http://data.bioontology.org";
	static String[] headers=new String[]{"Ontology","Classes","Cretaed","Properties","Individuals","MaxDepth","MaxChildCount",
										"AverageChildCount","ClassesWithOneChild","ClassesWithMoreThan25Children","ClassesWithNoDefinition"};
	static String[] complexheader=new String[]{"Boolean Classes","Union Classes","Intersection Classes","Complement Classes","Restrictions",
												"AllValuesFrom","SomeValueFrom","HasValue","Exact Cardinality","Minimum Cardinality","Maximum Cardinality",
												"Qualified Cardinality","Minimum Qualified Cardinality", "Maximum Qualified Cardinality"};
	
	public static Workbook createExcelSheet (Workbook workBook,Map<String,MetricBean> metricMap){
		Sheet sheet = workBook.createSheet("OntologiesMetric");
		Row row = sheet.createRow(0);
		for(int i=0;i<headers.length;i++){			
			 row.createCell(i).setCellValue(headers[i]);
		}
		
		int rowIndex=0;
		for (Map.Entry<String, MetricBean> entry : metricMap.entrySet()){
			MetricBean metricBean=entry.getValue();
			Row rowData=sheet.createRow(++rowIndex);
			rowData.createCell(0).setCellValue(entry.getKey());
			rowData.createCell(1).setCellValue(metricBean.getClasses());
			rowData.createCell(2).setCellValue(metricBean.getCreated());
			rowData.createCell(3).setCellValue(metricBean.getProperties());	
			rowData.createCell(4).setCellValue(metricBean.getIndividuals());
			rowData.createCell(5).setCellValue(metricBean.getMaxDepth());
			rowData.createCell(6).setCellValue(metricBean.getMaxChildCount());
			rowData.createCell(7).setCellValue(metricBean.getAverageChildCount());
			rowData.createCell(8).setCellValue(metricBean.getClassesWithOneChild());
			rowData.createCell(9).setCellValue(metricBean.getClassesWithMoreThan25Children());
			rowData.createCell(10).setCellValue(metricBean.getClassesWithNoDefinition());
			
		}
		
        System.out.println("Excel sheet row added to Workbook");
		return workBook;
		
	}
	
	public static Workbook createMappingSheet(Workbook workbook,Map<String,MetricBean> metricMap,Map<String, JsonNode> jsonNodeMap){
		Sheet sheet =workbook.createSheet("Ontology Mapping");
		Set<String> keySet=metricMap.keySet();
		Iterator<String> keyItr=keySet.iterator();
		int i=1;
		int j=1;
		Row row1 = sheet.createRow(0);
		while(keyItr.hasNext()){
			String tempOntology=keyItr.next();						
				row1.createCell(i++).setCellValue(tempOntology);
			
		}
		for (Map.Entry<String, JsonNode> entry : jsonNodeMap.entrySet()){
			Row row2 = sheet.createRow(j++);
			row2.createCell(0).setCellValue(entry.getKey());
			JsonNode jsonNode=entry.getValue();
			Iterator<Map.Entry<String,JsonNode>> fieldsIterator = jsonNode.fields();
			while (fieldsIterator.hasNext()){
				Map.Entry<String, JsonNode> entryMaping=fieldsIterator.next();
				//System.out.println("uu"+entryMaping.getKey());
				int index=getIndex(row1, entryMaping.getKey());
				if(index!=-1){
					row2.createCell(index).setCellValue(entryMaping.getValue().asText());
				}
			}
		}
		return workbook;
		
	}
	
	public static Map<String,JsonNode> getMappingList(Map<String,MetricBean> metricMap,String API_KEY){
		Map<String,JsonNode> mappingList=new HashMap<String,JsonNode>();
		Set<String> keySet=metricMap.keySet();
		Iterator<String> keyItr=keySet.iterator();
		String urlToget="/mappings/statistics/ontologies/";
		while(keyItr.hasNext()){
			String key=keyItr.next();
			String jsonString=RestConnector.getJsonContent(REST_URL+urlToget+key,API_KEY);
			if(jsonString!=null){
			JsonNode jsonNode=RestConnector.jsonToNode(jsonString);
			mappingList.put(key, jsonNode);
			}
		}
		System.out.println("Mapping List"+mappingList.size());
		return mappingList;
	}
	
	private static int getIndex(Row row,String key){
		int index=-1;

		for (int column=1; column<row.getLastCellNum(); column++) {
			   Cell cell = row.getCell(column);
			   if (cell == null || cell.getCellType() == Cell.CELL_TYPE_BLANK) {
				   System.out.println("Blank Cell");
			       continue;
			   }
			   if (cell.getCellType() == Cell.CELL_TYPE_STRING) {
			      String value = cell.getStringCellValue();
			      if (key.equalsIgnoreCase(value)) {
			         index = column;
			         break;
			      }
			   }
			}
		return index;
	}
	
}
