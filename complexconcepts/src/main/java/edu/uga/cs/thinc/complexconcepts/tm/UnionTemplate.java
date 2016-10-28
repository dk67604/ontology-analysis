package edu.uga.cs.thinc.complexconcepts.tm;

import com.hp.hpl.jena.ontology.OntClass;
import com.hp.hpl.jena.ontology.UnionClass;
import com.hp.hpl.jena.rdf.model.RDFList;

class UnionTemplate extends ClassTemplate {
	public UnionTemplate(OntClass n){
		Subject = n;
		UnionClass UC = Subject.asUnionClass();
		SEQ = UC.getOperands();
	}
	public RDFList getSEQ(){
		return this.SEQ;
	}
}