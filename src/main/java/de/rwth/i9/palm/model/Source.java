package de.rwth.i9.palm.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Lob;
import javax.persistence.Table;

import de.rwth.i9.palm.persistence.PersistableResource;

@Entity
@Table( name = "source" )
public class Source extends PersistableResource
{
	@Column( nullable = false, unique = true )
	private String name;
	
	@Column
	@Lob
	private String description;

	@Enumerated( EnumType.STRING )
	@Column( length = 16, nullable = false, unique = true )
	private SourceType SourceType;

	@Column( columnDefinition = "bit default 1" )
	private boolean active = true;

	public void setDescription( String description )
	{
		this.description = description;
	}

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

	public SourceType getSourceType()
	{
		return SourceType;
	}

	public void setSourceType( SourceType sourceType )
	{
		SourceType = sourceType;
	}

	public boolean isActive()
	{
		return active;
	}

	public void setActive( boolean active )
	{
		this.active = active;
	}


}
