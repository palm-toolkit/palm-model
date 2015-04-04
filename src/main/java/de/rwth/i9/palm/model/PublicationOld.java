package de.rwth.i9.palm.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Lob;
import javax.persistence.Table;

import de.rwth.i9.palm.persistence.PersistableResource;

@Entity
@Table( name = "publication_old" )
public class PublicationOld extends PersistableResource
{

	@Column
	private String year;

	@Column
	private String title;
	
	/* comma separated author list */
	@Column
	private String authors;

	@Column
	@Lob
	private String abstractText;

	@Column
	private String keywords;
	
	@Column( columnDefinition = "int default 0" )
	private int citations;

	@Column
	private String tags;

	public String getYear()
	{
		return year;
	}

	public void setYear( String year )
	{
		this.year = year;
	}

	public String getTitle()
	{
		return title;
	}

	public void setTitle( String title )
	{
		this.title = title;
	}

	public String getAuthors()
	{
		return authors;
	}

	public void setAuthors( String authors )
	{
		this.authors = authors;
	}

	public String getAbstractText()
	{
		return abstractText;
	}

	public void setAbstractText( String abstractText )
	{
		this.abstractText = abstractText;
	}

	public String getKeywords()
	{
		return keywords;
	}

	public void setKeywords( String keywords )
	{
		this.keywords = keywords;
	}

	public int getCitations()
	{
		return citations;
	}

	public void setCitations( int citations )
	{
		this.citations = citations;
	}

	public String getTags()
	{
		return tags;
	}

	public void setTags( String tags )
	{
		this.tags = tags;
	}

}

