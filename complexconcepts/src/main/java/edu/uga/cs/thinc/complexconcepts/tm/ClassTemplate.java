package edu.uga.cs.thinc.complexconcepts.tm;



import com.hp.hpl.jena.ontology.OntClass;
import com.hp.hpl.jena.rdf.model.RDFList;

public class ClassTemplate extends Template {
	RDFList SEQ=null;
	OntClass CompClass = null;
	
	public void PrintList()
	{
		System.out.println(SEQ.asJavaList().toString());
	}
	
/*		class DisjointTemplate extends ClassTemplate {
		public DisjointTemplate(OntClass n){
			Subject = n;
			DisjointWith UC = Subject.asDisjointClass();
			SEQ = UC.getOperands();
		}
	}*/

}
