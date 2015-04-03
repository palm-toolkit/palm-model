package de.rwth.i9.palm.model;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity( name = "tag" )
public class Tag extends Concept
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
