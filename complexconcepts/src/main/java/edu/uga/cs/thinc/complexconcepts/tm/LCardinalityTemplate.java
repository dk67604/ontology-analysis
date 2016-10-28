package edu.uga.cs.thinc.complexconcepts.tm;

import com.hp.hpl.jena.ontology.CardinalityRestriction;
import com.hp.hpl.jena.ontology.MaxCardinalityRestriction;
import com.hp.hpl.jena.ontology.MinCardinalityRestriction;
import com.hp.hpl.jena.ontology.Restriction;

class LCardinalityTemplate extends RstrTemplate {
	int CardinalityType = -1;			
	
	public LCardinalityTemplate(Restriction r){
		Subject = r.asClass();
		if(r.isMinCardinalityRestriction()){
			CardinalityType = 0;
			MinCardinalityRestriction MinCard =  r.asMinCardinalityRestriction();
			prop = MinCard.getOnProperty();
			CardinalityValue = MinCard.getMinCardinality();
		}
		if(r.isCardinalityRestriction()){
			CardinalityType = 1;
			CardinalityRestriction Card = r.asCardinalityRestriction();
			prop = Card.getOnProperty();
			CardinalityValue = Card.getCardinality();
		}
		if(r.isMaxCardinalityRestriction()){
			CardinalityType = 2;
			MaxCardinalityRestriction MaxCard = r.asMaxCardinalityRestriction();
			prop = MaxCard.getOnProperty();
			CardinalityValue = MaxCard.getMaxCardinality();
		}				
	}
	
	public int getCardinalityValue(){
		return this.CardinalityValue;
	}
	
	public int GetCardinalityType(){
		return this.CardinalityType;
	}
}