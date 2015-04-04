package de.rwth.i9.palm.persistence;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

/**
 * Extends {@link PersistableType} with Uniform Resource Identifier , namely a
 * <i>URI</i>. Preparing for linking the relational database with linked-data
 * RDF
 */
@MappedSuperclass
public abstract class PersistableResource extends PersistableType
{

	// properties
	@Column( unique = true )
	private String uri;

	// getter / setter

	public PersistableResource()
	{
		super();
	}

	public String getURI()
	{
		return uri;
	}

	public void setURN( final String uri )
	{
		this.uri = uri;
	}

}
