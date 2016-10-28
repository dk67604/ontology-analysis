package edu.uga.cs.thinc.bioportalrest.beans;

import java.io.Serializable;

public class MetricBean implements Serializable{

	private static final long serialVersionUID = 6959203635476572361L;

	private String classes;
	private String created;
	private String individuals;
	private String properties;
	private String maxDepth;
	private String maxChildCount;
	private String averageChildCount;
	private String classesWithOneChild;
	private String classesWithMoreThan25Children;
	private String classesWithNoDefinition;

	public String getClasses() {
		return classes;
	}
	public void setClasses(String classes) {
		this.classes = classes;
	}
	public String getCreated() {
		return created;
	}
	public void setCreated(String created) {
		this.created = created;
	}
	public String getProperties() {
		return properties;
	}
	public void setProperties(String properties) {
		this.properties = properties;
	}
	public String getMaxDepth() {
		return maxDepth;
	}
	public void setMaxDepth(String maxDepth) {
		this.maxDepth = maxDepth;
	}
	public String getMaxChildCount() {
		return maxChildCount;
	}
	public void setMaxChildCount(String maxChildCount) {
		this.maxChildCount = maxChildCount;
	}
	public String getAverageChildCount() {
		return averageChildCount;
	}
	public void setAverageChildCount(String averageChildCount) {
		this.averageChildCount = averageChildCount;
	}
	public String getClassesWithOneChild() {
		return classesWithOneChild;
	}
	public void setClassesWithOneChild(String classesWithOneChild) {
		this.classesWithOneChild = classesWithOneChild;
	}
	public String getClassesWithMoreThan25Children() {
		return classesWithMoreThan25Children;
	}
	public void setClassesWithMoreThan25Children(String classesWithMoreThan25Children) {
		this.classesWithMoreThan25Children = classesWithMoreThan25Children;
	}
	public String getClassesWithNoDefinition() {
		return classesWithNoDefinition;
	}
	public void setClassesWithNoDefinition(String classesWithNoDefinition) {
		this.classesWithNoDefinition = classesWithNoDefinition;
	}
	public String getIndividuals() {
		return individuals;
	}
	public void setIndividuals(String individuals) {
		this.individuals = individuals;
	}
	@Override
	public String toString() {
		return "MetricBean [classes=" + classes + ", created=" + created + ", individuals=" + individuals
				+ ", properties=" + properties + ", maxDepth=" + maxDepth + ", maxChildCount=" + maxChildCount
				+ ", averageChildCount=" + averageChildCount + ", classesWithOneChild=" + classesWithOneChild
				+ ", classesWithMoreThan25Children=" + classesWithMoreThan25Children + ", classesWithNoDefinition="
				+ classesWithNoDefinition + "]";
	}

	

}
