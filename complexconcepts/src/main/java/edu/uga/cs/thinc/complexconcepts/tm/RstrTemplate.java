package edu.uga.cs.thinc.complexconcepts.tm;

import com.hp.hpl.jena.ontology.OntClass;
import com.hp.hpl.jena.ontology.OntProperty;
import com.hp.hpl.jena.rdf.model.Literal;
import com.hp.hpl.jena.rdf.model.Resource;

public class RstrTemplate extends Template {
	int AllValueType = 0;
	int SomeValueType = 1;
	int MinCardinality = 0;
	int ExactCardinality = 1;
	int MaxCardinality = 2;

	OntProperty prop=null;
	OntClass objclass=null;
	Literal hasvalue=null;
	Resource R = null;
	int CardinalityValue = 0 ;
	
	public OntProperty getProperty(){
		return this.prop;
	};
	
	public OntClass getObjectClass(){
		return this.objclass;
	}
	


}