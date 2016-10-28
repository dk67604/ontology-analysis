package edu.uga.cs.thinc.complexconcepts.tm;

import com.hp.hpl.jena.ontology.HasValueRestriction;
import com.hp.hpl.jena.ontology.Restriction;
import com.hp.hpl.jena.rdf.model.Literal;

class HasValueTemplate extends RstrTemplate {
//	Ontliteral literal;
//	getLiteral()
	public HasValueTemplate(Restriction r){
		Subject = r.asClass();
		HasValueRestriction Has = r.asHasValueRestriction();
		prop = Has.getOnProperty();
		hasvalue = (Literal)Has.getHasValue();
	}
	public Literal getValue(){
		return this.hasvalue;
	}
	
}