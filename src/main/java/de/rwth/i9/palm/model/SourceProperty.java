package de.rwth.i9.palm.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import de.rwth.i9.palm.persistence.PersistableResource;

@Entity
@Table( name = "source_property" )
public class SourceProperty extends PersistableResource
{
	@Column( length = 20 )
	String mainIdentifier;

	@Column( length = 20 )
	String secondaryIdentifier;

	@Column
	@Lob
	String value;

	@Column
	Date lastModified;

	@Column
	String expiredEvery;

	// relation
	@ManyToOne
	@JoinColumn( name = "source_id" )
	private Source source;

	public String getMainIdentifier()
	{
		return mainIdentifier;
	}

	public void setMainIdentifier( String mainIdentifier )
	{
		this.mainIdentifier = mainIdentifier;
	}

	public String getSecondaryIdentifier()
	{
		return secondaryIdentifier;
	}

	public void setSecondaryIdentifier( String secondaryIdentifier )
	{
		this.secondaryIdentifier = secondaryIdentifier;
	}

	public String getValue()
	{
		return value;
	}

	public void setValue( String value )
	{
		this.value = value;
	}

	public Date getLastModified()
	{
		return lastModified;
	}

	public void setLastModified( Date lastModified )
	{
		this.lastModified = lastModified;
	}

	public String getExpiredEvery()
	{
		return expiredEvery;
	}

	public void setExpiredEvery( String expiredEvery )
	{
		this.expiredEvery = expiredEvery;
	}

	public Source getSource()
	{
		return source;
	}

	public void setSource( Source source )
	{
		this.source = source;
	}

}
