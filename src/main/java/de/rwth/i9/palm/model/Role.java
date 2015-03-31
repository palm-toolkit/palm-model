package de.rwth.i9.palm.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import de.rwth.i9.palm.persistence.PersistableResource;

@Entity
@Table( name = "role" )
public class Role extends PersistableResource
{
	@Column
	private String name;

	public String getName()
	{
		return name;
	}

	public void setName( String name )
	{
		this.name = name;
	}
}
