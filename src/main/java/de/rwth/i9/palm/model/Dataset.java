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
@Table( name = "dataset" )
public class Dataset extends PersistableResource
{
	@Column( unique = true, nullable = false )
	private String name;
	
	@Column( length = 40 )
	private String type;

	@Column
	@Lob
	private String notes;

	public String getName()
	{
		return name;
	}

	public void setName( String name )
	{
		this.name = name;
	}

	public String getNotes()
	{
		return notes;
	}

	public void setNotes( String notes )
	{
		this.notes = notes;
	}

	// relations
	@OneToMany( cascade = CascadeType.ALL )
	@JoinColumn( name = "dataset_id" )
	private List<Publication> publications;

	public List<Publication> getPublications()
	{
		return publications;
	}

	public void setPublications( List<Publication> publications )
	{
		this.publications = publications;
	}

	public Dataset addPublication( final Publication publication )
	{
		if ( publications == null )
			publications = new ArrayList<Publication>();

		publications.add( publication );
		return this;
	}

	public String getType()
	{
		return type;
	}

	public void setType( String type )
	{
		this.type = type;
	}
}
