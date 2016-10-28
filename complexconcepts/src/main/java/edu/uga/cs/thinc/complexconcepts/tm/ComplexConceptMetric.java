package edu.uga.cs.thinc.complexconcepts.tm;

import java.io.Serializable;

public class ComplexConceptMetric implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int unionClass;
	private int complementClass;
	private int intersectionClass;
	private int restriction;
	private int allValueForm;
	private int someValueForm;
	private int hasValues;
	private int exactCardinality;
	private int minCardinality;
	private int maxCardinality;
	private int qualifiedCardinality;
	private int minQualifiedCardinality;
	private int maxQualifiedCardinality;
	public int getUnionClass() {
		return unionClass;
	}
	public void setUnionClass(int unionClass) {
		this.unionClass = unionClass;
	}
	public int getComplementClass() {
		return complementClass;
	}
	public void setComplementClass(int complementClass) {
		this.complementClass = complementClass;
	}
	public int getIntersectionClass() {
		return intersectionClass;
	}
	public void setIntersectionClass(int intersectionClass) {
		this.intersectionClass = intersectionClass;
	}
	public int getRestriction() {
		return restriction;
	}
	public void setRestriction(int restriction) {
		this.restriction = restriction;
	}
	public int getAllValueForm() {
		return allValueForm;
	}
	public void setAllValueForm(int allValueForm) {
		this.allValueForm = allValueForm;
	}
	public int getSomeValueForm() {
		return someValueForm;
	}
	public void setSomeValueForm(int someValueForm) {
		this.someValueForm = someValueForm;
	}
	public int getHasValues() {
		return hasValues;
	}
	public void setHasValues(int hasValues) {
		this.hasValues = hasValues;
	}
	public int getExactCardinality() {
		return exactCardinality;
	}
	public void setExactCardinality(int exactCardinality) {
		this.exactCardinality = exactCardinality;
	}
	public int getMinCardinality() {
		return minCardinality;
	}
	public void setMinCardinality(int minCardinality) {
		this.minCardinality = minCardinality;
	}
	public int getMaxCardinality() {
		return maxCardinality;
	}
	public void setMaxCardinality(int maxCardinality) {
		this.maxCardinality = maxCardinality;
	}
	
	public int getQualifiedCardinality() {
		return qualifiedCardinality;
	}
	public void setQualifiedCardinality(int qualifiedCardinality) {
		this.qualifiedCardinality = qualifiedCardinality;
	}
	public int getMinQualifiedCardinality() {
		return minQualifiedCardinality;
	}
	public void setMinQualifiedCardinality(int minQualifiedCardinality) {
		this.minQualifiedCardinality = minQualifiedCardinality;
	}
	public int getMaxQualifiedCardinality() {
		return maxQualifiedCardinality;
	}
	public void setMaxQualifiedCardinality(int maxQualifiedCardinality) {
		this.maxQualifiedCardinality = maxQualifiedCardinality;
	}
	@Override
	public String toString() {
		return "ComplexConceptMetric [unionClass=" + unionClass + ", complementClass=" + complementClass
				+ ", intersectionClass=" + intersectionClass + ", restriction=" + restriction + ", allValueForm="
				+ allValueForm + ", someValueForm=" + someValueForm + ", hasValues=" + hasValues + ", exactCardinality="
				+ exactCardinality + ", minCardinality=" + minCardinality + ", maxCardinality=" + maxCardinality
				+ ", qualifiedCardinality=" + qualifiedCardinality + ", minQualifiedCardinality="
				+ minQualifiedCardinality + ", maxQualifiedCardinality=" + maxQualifiedCardinality + "]";
	}
	

}
