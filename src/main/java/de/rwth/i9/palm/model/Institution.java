package de.rwth.i9.palm.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import de.rwth.i9.palm.persistence.PersistableResource;

@Entity
@Table( name = "institution" )
public class Institution extends PersistableResource
{
	@Column
	private String name;

	@OneToOne( cascade = CascadeType.ALL, fetch = FetchType.LAZY )
	private Location location;

	public String getName()
	{
		return name;
	}

	public void setName( String name )
	{
		this.name = name;
	}

	public Location getLocation()
	{
		return location;
	}

	public void setLocation( Location location )
	{
		this.location = location;
	}
}
