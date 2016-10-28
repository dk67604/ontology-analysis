package edu.uga.cs.thinc.bioportalrest.beans;

import java.io.Serializable;

public class OntologyMapping implements Serializable{
	
	private static final long serialVersionUID = -3712745750907666422L;
	private String ontology;
	private String id;
	
	public String getOntology() {
		return ontology;
	}
	public void setOntology(String ontology) {
		this.ontology = ontology;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	@Override
	public String toString() {
		return "OntologyMapping [ontology=" + ontology + ", id=" + id + "]";
	}
	
	

}
