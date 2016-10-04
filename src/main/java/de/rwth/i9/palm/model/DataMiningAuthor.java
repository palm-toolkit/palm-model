package de.rwth.i9.palm.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.search.annotations.Analyze;
import org.hibernate.search.annotations.Analyzer;
import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.Index;
import org.hibernate.search.annotations.Indexed;
import org.hibernate.search.annotations.Store;

import de.rwth.i9.palm.persistence.PersistableResource;

@Entity
@Table( name = "author" )
@Indexed
public class DataMiningAuthor extends PersistableResource
{

	@Column( length = 100 )
	@Field( index = Index.YES, analyze = Analyze.YES, store = Store.YES )
	@Analyzer( definition = "authoranalyzer" )
	private String name;

	@Column( columnDefinition = "int default 0" )
	private int citedBy;

	@Column( columnDefinition = "int default 0" )
	private int noPublication;

	@OneToOne( fetch = FetchType.EAGER, mappedBy = "author" )
	private AuthorInterestFlat author_interest_flat;

	public AuthorInterestFlat getAuthor_interest_flat()
	{
		return author_interest_flat;
	}

	public void setAuthor_interest_flat( AuthorInterestFlat author_interest_flat )
	{
		this.author_interest_flat = author_interest_flat;
	}

	public String getName()
	{
		return name;
	}

	public void setName( String name )
	{
		this.name = name;
	}

	public int getCitedBy()
	{
		return citedBy;
	}

	public void setCitedBy( int citedBy )
	{
		this.citedBy = citedBy;
	}

	public int getNoPublication()
	{
		return noPublication;
	}

	public void setNoPublication( int noPublication )
	{
		this.noPublication = noPublication;
	}

	public Object getJsonStub()
	{
		final String _id = this.getId();
		final String _name = this.getName();
		return new Object()
		{
			public final String id = _id;
			public final String name = _name;
		};
	}
}
