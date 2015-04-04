package de.rwth.i9.palm.model;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity( name = "keyword" )
public class Keyword extends Concept
{
	@Column
	private String label;

	public String getLabel()
	{
		return label;
	}

	public void setLabel( String label )
	{
		this.label = label;
	}
}
