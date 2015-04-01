package de.rwth.i9.palm.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import de.rwth.i9.palm.persistence.PersistableResource;

@Entity
@Table( name = "source" )
public class Source extends PersistableResource
{
	@Column( unique = true, nullable = false )
	private String name;
	
	@Column
	@Lob
	private String description;

	public String getName()
	{
		return name;
	}

	public void setName( String name )
	{
		this.name = name;
	}

	public String getDescription()
	{
		return description;
	}

	public void setDescription( String description )
	{
		this.description = description;
	}

	// relations
	@OneToMany( cascade = CascadeType.ALL )
	@JoinColumn( name = "source_id" )
	private List<Publication> publications;

	public List<Publication> getPublications()
	{
		return publications;
	}

	public void setPublications( List<Publication> publications )
	{
		this.publications = publications;
	}

	public Source addPublication( final Publication publication )
	{
		if ( publications == null )
			publications = new ArrayList<Publication>();

		publications.add( publication );
		return this;
	}
}
