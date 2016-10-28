package edu.uga.cs.thinc.complexconcepts.tm;

import com.hp.hpl.jena.ontology.AllValuesFromRestriction;
import com.hp.hpl.jena.ontology.OntClass;
import com.hp.hpl.jena.ontology.Restriction;
import com.hp.hpl.jena.ontology.SomeValuesFromRestriction;

class ValueTemplate extends RstrTemplate {
	
	int ValueType = -1;
	
	public ValueTemplate(Restriction r){
		Subject = r.asClass();
		try{
		if (r.isAllValuesFromRestriction())	{
			ValueType = AllValueType;
			AllValuesFromRestriction All = r.asAllValuesFromRestriction();
			prop = All.getOnProperty();
			objclass = (OntClass)All.getAllValuesFrom();
		}
		else if (r.isSomeValuesFromRestriction())	{
			ValueType = SomeValueType;
			SomeValuesFromRestriction Some = r.asSomeValuesFromRestriction();
			prop = Some.getOnProperty();
			objclass = (OntClass)Some.getSomeValuesFrom();
		}
		else
			System.out.println("Can not get ValueType");
	}catch(Exception e){
		
	}
		}
	
	public int getValueType(){
		return ValueType;
	}
}