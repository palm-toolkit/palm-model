package de.rwth.i9.palm.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import de.rwth.i9.palm.persistence.PersistableResource;

@Entity
@Table( name = "reference" )
public class Reference extends PersistableResource
{
	@Column
	@Lob
	private String title;
	
	@Column
	private String sameAsUri;
	
	@Column
	private String author;
	
	@Column( length = 4 )
	private String year;
	
	@Column(columnDefinition = "int default 0")
	private int citedBy;
	
	@Column
	@Lob 
	private String citationContext;

	@ManyToOne
	@JoinColumn( name = "publication_id" )
	private Publication publication;

	public String getTitle()
	{
		return title;
	}

	public void setTitle( String title )
	{
		this.title = title;
	}

	public String getSameAsUri()
	{
		return sameAsUri;
	}

	public void setSameAsUri( String sameAsUri )
	{
		this.sameAsUri = sameAsUri;
	}

	public Publication getPublication()
	{
		return publication;
	}

	public void setPublication( Publication publication )
	{
		this.publication = publication;
	}
}
