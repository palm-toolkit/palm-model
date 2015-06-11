package de.rwth.i9.palm.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;

import de.rwth.i9.palm.persistence.PersistableResource;

@Entity( name = "subject" )
public class Subject extends PersistableResource
{
	@Column
	private String label;

	@Column( columnDefinition = "bit default 1" )
	private boolean keyword = true;

	@Column( columnDefinition = "bit default 1" )
	private boolean tag = true;

	@Column
	private String resourceUri;

	@ManyToMany( mappedBy = "subjects", cascade = CascadeType.ALL )
	private List<Publication> publications;

	public List<Publication> getPublications()
	{
		return publications;
	}

	public void setPublications( List<Publication> publications )
	{
		this.publications = publications;
	}

	public Subject addPublication( Publication publication )
	{
		if ( this.publications == null )
			this.publications = new ArrayList<Publication>();
		this.publications.add( publication );
		return this;
	}

	public String getLabel()
	{
		return label;
	}

	public boolean isKeyword()
	{
		return keyword;
	}

	public void setKeyword( boolean keyword )
	{
		this.keyword = keyword;
	}

	public boolean isTag()
	{
		return tag;
	}

	public void setTag( boolean tag )
	{
		this.tag = tag;
	}

	public String getResourceUri()
	{
		return resourceUri;
	}

	public void setResourceUri( String resourceUri )
	{
		this.resourceUri = resourceUri;
	}

	public void setLabel( String label )
	{
		this.label = label;
	}
}
