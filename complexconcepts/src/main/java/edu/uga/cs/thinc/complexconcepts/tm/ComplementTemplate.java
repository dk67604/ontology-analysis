package edu.uga.cs.thinc.complexconcepts.tm;

import com.hp.hpl.jena.ontology.ComplementClass;
import com.hp.hpl.jena.ontology.OntClass;

class ComplementTemplate extends ClassTemplate {
	public ComplementTemplate(OntClass n){
		Subject = n;
		ComplementClass UC = Subject.asComplementClass();
		CompClass = UC.getOperand();
	}

	public OntClass getSEQ(){
		return this.CompClass;
	}
}