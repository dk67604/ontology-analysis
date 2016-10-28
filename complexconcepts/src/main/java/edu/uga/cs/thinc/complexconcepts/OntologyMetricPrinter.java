package edu.uga.cs.thinc.complexconcepts;
import java.io.File;
import java.io.IOException;

import com.hp.hpl.jena.ontology.OntClass;
import com.hp.hpl.jena.ontology.OntModel;
import com.hp.hpl.jena.ontology.OntModelSpec;
import com.hp.hpl.jena.rdf.model.ModelFactory;
import com.hp.hpl.jena.util.iterator.ExtendedIterator;

import edu.uga.cs.thinc.complexconcepts.tm.ComplexConceptMetric;
import edu.uga.cs.thinc.complexconcepts.tm.Template;

public class OntologyMetricPrinter {
	public static void main(String[] args) throws IOException {
		//String err = "";
		//String uri="http://data.bioontology.org/ontologies/ORTH/download?apikey=6fb1ca19-fb4c-45ec-b4a5-23e1aa0bda98&download_format=rdf";
		OntologyMetricPrinter omp=new OntologyMetricPrinter();
		omp.getComplexConcept("CCONT");
	}


	
	public ComplexConceptMetric getComplexConcept(String ontology){
		String err = "";
		ComplexConceptMetric ccm=new ComplexConceptMetric();
		String uri="http://data.bioontology.org/ontologies/"+ontology+"/download?apikey=6fb1ca19-fb4c-45ec-b4a5-23e1aa0bda98&download_format=rdf";
		try {
			
			OntModel m = ModelFactory.createOntologyModel(OntModelSpec.OWL_MEM);
			OntModel model = (OntModel) m.read(uri);
			int namedCls = getSize(model.listNamedClasses());
			int objectproperties = getSize(model.listObjectProperties());
			int datatypeproperties = getSize(model.listDatatypeProperties());
			int restrictions = getSize(model.listRestrictions());
			System.out.println(namedCls + "," + objectproperties + ","
					+ datatypeproperties + "," + restrictions);
			Template template=new Template();
			ccm.setRestriction(restrictions);
			ccm=template.init(model,ccm);
			
		} catch (Exception e) {
			// e.printStackTrace();
			err = err + e + "Unable to process " + "\n";
			System.err.println(err);
			return null;
		}
		// }
		
		System.out.println(ccm);
		return ccm;
	    
	}
	private static int getSize(ExtendedIterator listNamedClasses) {
		int i = 0;
		while (listNamedClasses.hasNext()) {
			listNamedClasses.next();
			i++;
		}
		return i;
	}
}
