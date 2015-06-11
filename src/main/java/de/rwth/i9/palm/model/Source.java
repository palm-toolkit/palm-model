package de.rwth.i9.palm.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
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

	@Enumerated( EnumType.STRING )
	@Column( length = 16 )
	private SourceType SourceType;

	public void setDescription( String description )
	{
		this.description = description;
	}

	// relations
	@OneToMany( cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "source" )
	private List<PublicationSource> publicationSources;

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

	public void setPublicationSources( List<PublicationSource> publicationSources )
	{
		this.publicationSources = publicationSources;
	}

	public Source addPublicationSource( final PublicationSource publicationSource )
	{
		if ( this.publicationSources == null )
			this.publicationSources = new ArrayList<PublicationSource>();

		this.publicationSources.add( publicationSource );
		return this;
	}
}
