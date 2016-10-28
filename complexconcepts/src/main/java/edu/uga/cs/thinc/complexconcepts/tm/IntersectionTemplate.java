package edu.uga.cs.thinc.complexconcepts.tm;

import com.hp.hpl.jena.ontology.IntersectionClass;
import com.hp.hpl.jena.ontology.OntClass;
import com.hp.hpl.jena.rdf.model.RDFList;

class IntersectionTemplate extends ClassTemplate {
	public IntersectionTemplate(OntClass n){
		Subject = n;
		IntersectionClass UC = Subject.asIntersectionClass();
		SEQ = UC.getOperands();
	}
	public RDFList getSEQ(){
		return this.SEQ;
	}
}