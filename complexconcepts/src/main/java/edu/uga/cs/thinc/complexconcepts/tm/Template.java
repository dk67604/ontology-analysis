package edu.uga.cs.thinc.complexconcepts.tm;

import java.util.ArrayList;
import java.util.Iterator;

import com.hp.hpl.jena.ontology.ComplementClass;
import com.hp.hpl.jena.ontology.IntersectionClass;
import com.hp.hpl.jena.ontology.OntClass;
import com.hp.hpl.jena.ontology.OntModel;
import com.hp.hpl.jena.ontology.OntProperty;
import com.hp.hpl.jena.ontology.Restriction;
import com.hp.hpl.jena.ontology.UnionClass;
import com.hp.hpl.jena.rdf.model.Literal;
import com.hp.hpl.jena.rdf.model.Property;
import com.hp.hpl.jena.rdf.model.RDFList;
import com.hp.hpl.jena.rdf.model.ResourceFactory;
import com.hp.hpl.jena.rdf.model.Statement;
import com.hp.hpl.jena.rdf.model.impl.StatementImpl;

public class Template {

	
	OntClass Subject = null;
	int revised = 0;

	ArrayList<LCardinalityTemplate> Ont1LC = new ArrayList<LCardinalityTemplate>();
	ArrayList<CCardinalityTemplate> Ont1CC = new ArrayList<CCardinalityTemplate>();
	ArrayList<Integer> cardinalityList=new ArrayList<Integer>();
	ArrayList<Integer> minCardinalityList=new ArrayList<Integer>();
	ArrayList<Integer> maxCardinalityList=new ArrayList<Integer>();
	ArrayList<Integer> allValueList=new ArrayList<Integer>();
	ArrayList<Integer> someValueList=new ArrayList<Integer>();
	ArrayList<ValueTemplate> Ont1V = new ArrayList<ValueTemplate>();
	ArrayList<HasValueTemplate> Ont1HV = new ArrayList<HasValueTemplate>();
	ArrayList<UnionTemplate> Ont1U = new ArrayList<UnionTemplate>();
	ArrayList<IntersectionTemplate> Ont1I = new ArrayList<IntersectionTemplate>();
	ArrayList<ComplementTemplate> Ont1C = new ArrayList<ComplementTemplate>();
	ArrayList<Integer> Ont1Q=new ArrayList<Integer>();
	ArrayList<Integer> Ont1MinQ=new ArrayList<Integer>();
	ArrayList<Integer> Ont1MaxQ=new ArrayList<Integer>();


	public OntClass getSubject(){
		return this.Subject;
	}


	public ComplexConceptMetric init(OntModel model1,ComplexConceptMetric ccm){
		
		String OWL = model1.getNsPrefixURI("owl");
		Property ON_CLASS = ResourceFactory.createProperty(OWL + "onClass"); 
		Property ON_DATA_RANGE = ResourceFactory.createProperty(OWL + "onDataRange");
		Property QUALIFIED_CARDINALITY = ResourceFactory.createProperty(OWL + "qualifiedCardinality");
		Property MIN_QUALIFIED_CARDINALITY = ResourceFactory.createProperty(OWL + "minQualifiedCardinality");
		Property MAX_QUALIFIED_CARDINALITY = ResourceFactory.createProperty(OWL + "maxQualifiedCardinality");
		//Initializing model 1
		Iterator <Restriction> AnonRestrs = model1.listRestrictions();
		int qCount=0,minQCount=0,maxQCount=0,cardCount=0,minCardCount=0,maxCardCount=0,allValueCount=0,someValueCount=0;
		while(AnonRestrs.hasNext()){
			Restriction r = AnonRestrs.next();
			
			try{
				if (r.isCardinalityRestriction())	{

					if(r.getOnProperty().getRange()==null){
						LCardinalityTemplate c = new LCardinalityTemplate(r);
						Ont1LC.add(c);
					}
					else{
						CCardinalityTemplate c = new CCardinalityTemplate(r);
						Ont1CC.add(c);
					}
				}
			  
				
				if (r.isAllValuesFromRestriction())	{
					ValueTemplate c = new ValueTemplate(r);
					Ont1V.add(c);
				}
				if (r.isSomeValuesFromRestriction()){
					ValueTemplate c = new ValueTemplate(r);
					Ont1V.add(c);
				}
				if (r.isHasValueRestriction()) {
					HasValueTemplate c = new HasValueTemplate(r);
					Ont1HV.add(c);

				}
				if (r.getProperty(ON_CLASS) != null || r.getProperty(ON_DATA_RANGE) != null) { 
					   if (r.getProperty(QUALIFIED_CARDINALITY) != null) { 
					    Ont1Q.add(++qCount);
					   } else if (r.getProperty(MIN_QUALIFIED_CARDINALITY) != null) { 
						   Ont1MinQ.add(++minQCount);
					   } else if (r.getProperty(MAX_QUALIFIED_CARDINALITY) != null) { 
						    Ont1MaxQ.add(++maxQCount);
					   } 
			}
			}
			catch(Exception e){}
		} // end While

		Iterator<UnionClass> AnonClass1 = model1.listUnionClasses();
		while(AnonClass1.hasNext()){
			OntClass O = AnonClass1.next();
			UnionTemplate U = new UnionTemplate(O);
			Ont1U.add(U);
		}

		Iterator<IntersectionClass> anonInt= model1.listIntersectionClasses();
		while(anonInt.hasNext()){
			OntClass O = anonInt.next();
			IntersectionTemplate U = new IntersectionTemplate(O);
			Ont1I.add(U);
		}

		Iterator<ComplementClass> annonComp= model1.listComplementClasses();
		while(annonComp.hasNext()){
			OntClass O = annonComp.next();
			ComplementTemplate U = new ComplementTemplate(O);
			Ont1C.add(U);
		}
		
		Iterator<LCardinalityTemplate> itrLC=Ont1LC.iterator();
		Iterator<CCardinalityTemplate> itrCC=Ont1CC.iterator();
		Iterator<ValueTemplate> itrValueTemp=Ont1V.iterator();
		while(itrLC.hasNext()){
			LCardinalityTemplate lc=itrLC.next();
			if(lc.GetCardinalityType()==0){
				minCardinalityList.add(++minCardCount);
			}
			if(lc.GetCardinalityType()==1){
				cardinalityList.add(++cardCount);
			}
			if(lc.GetCardinalityType()==2){
				maxCardinalityList.add(++maxCardCount);
			}
		}
		
		while(itrCC.hasNext()){
			CCardinalityTemplate cc=itrCC.next();
			if(cc.GetCardinalityType()==0){
				minCardinalityList.add(++minCardCount);
			}
			if(cc.GetCardinalityType()==1){
				cardinalityList.add(++cardCount);
			}
			if(cc.GetCardinalityType()==2){
				maxCardinalityList.add(++maxCardCount);
			}
		}
		while (itrValueTemp.hasNext()){
			ValueTemplate valueTemp=itrValueTemp.next();
			if(valueTemp.getValueType()==0){
				allValueList.add(++allValueCount);
			}
			if(valueTemp.getValueType()==1){
				someValueList.add(++someValueCount);
			}
		}
		
		System.out.println("Ont1LC "+Ont1LC.size());
		
		System.out.println("Ont1CC "+Ont1CC.size());
		System.out.println("Ont1V "+Ont1V.size());
		System.out.println("Ont1HV "+Ont1HV.size());
		System.out.println("Ont1U "+Ont1U.size());
		System.out.println("Ont1C "+Ont1C.size());
		System.out.println("Ont1I "+Ont1I.size());
		System.out.println("Ont1Q "+Ont1Q.size());
		System.out.println("OntMinQ "+Ont1MinQ.size());
		System.out.println("OntMaxQ "+Ont1MaxQ.size());
		System.out.println("minCardinalityList "+minCardinalityList.size());
		System.out.println("cardinalityList "+cardinalityList.size());
		System.out.println("maxCardinalityList "+maxCardinalityList.size());
		System.out.println("allValueList "+allValueList.size());
		System.out.println("someValueList "+someValueList.size());
		ccm.setAllValueForm(allValueList.size());
		ccm.setComplementClass(Ont1C.size());
		ccm.setExactCardinality(cardinalityList.size());
		ccm.setHasValues(Ont1HV.size());
		ccm.setIntersectionClass(Ont1I.size());
		ccm.setMaxCardinality(maxCardinalityList.size());
		ccm.setMinCardinality(minCardinalityList.size());
		ccm.setSomeValueForm(someValueList.size());
		ccm.setUnionClass(Ont1U.size());
		ccm.setQualifiedCardinality(Ont1Q.size());
		ccm.setMinQualifiedCardinality(Ont1MinQ.size());
		ccm.setMaxQualifiedCardinality(Ont1MaxQ.size());
		return ccm;
	}


	}

