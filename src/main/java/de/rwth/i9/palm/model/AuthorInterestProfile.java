package de.rwth.i9.palm.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import de.rwth.i9.palm.persistence.PersistableResource;

@Entity
@Table( name = "author_interest_profile" )
public class AuthorInterestProfile extends PersistableResource
{
	@Column( unique = true, nullable = false )
	private String name;

	@Column
	private String language;

	@Column
	private Date created;

	@Column
	@Lob
	private String description;

	// relation
	@ManyToOne
	@JoinColumn( name = "author_id" )
	private Author author;

	@OneToMany( cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "authorInterestProfile", orphanRemoval = true )
	List<AuthorInterest> authorInterests;

	// getter & setter

	public AuthorInterestProfile addAuthorInterest( AuthorInterest authorInterest )
	{
		if ( this.authorInterests == null )
			authorInterests = new ArrayList<AuthorInterest>();

		authorInterests.add( authorInterest );
		return this;
	}

	public String getName()
	{
		return name;
	}

	public void setName( String name )
	{
		this.name = name;
	}

	public String getLanguage()
	{
		return language;
	}

	public void setLanguage( String language )
	{
		this.language = language;
	}

	public Date getCreated()
	{
		return created;
	}

	public void setCreated( Date created )
	{
		this.created = created;
	}

	public String getDescription()
	{
		return description;
	}

	public void setDescription( String description )
	{
		this.description = description;
	}

	public Author getAuthor()
	{
		return author;
	}

	public void setAuthor( Author author )
	{
		this.author = author;
	}

	public List<AuthorInterest> getAuthorInterests()
	{
		return authorInterests;
	}

	public void setAuthorInterests( List<AuthorInterest> authorInterests )
	{
		this.authorInterests = authorInterests;
	}
}
