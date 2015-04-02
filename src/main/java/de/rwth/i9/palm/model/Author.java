package de.rwth.i9.palm.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import de.rwth.i9.palm.persistence.PersistableResource;

@Entity
@Table( name = "author" )
public class Author extends PersistableResource
{
	/* the full name of the author, most commonly used */
	@Column
	private String name;

	@Column
	private String email;

	// relations
	/* other name of the author */
	@OneToMany( cascade = CascadeType.ALL )
	@JoinColumn( name = "author_id" )
	List<String> aliases;

	/* few authors work for several institution */
	@ManyToMany( cascade = CascadeType.ALL, fetch = FetchType.LAZY )
	@JoinTable( name = "author_institution", joinColumns = @JoinColumn( name = "author_id" ), inverseJoinColumns = @JoinColumn( name = "institution_id" ) )
	private List<Institution> institutions;

	@ManyToMany( cascade = CascadeType.ALL, fetch = FetchType.LAZY )
	@JoinTable( name = "author_interest", joinColumns = @JoinColumn( name = "author_id" ), inverseJoinColumns = @JoinColumn( name = "topic_id" ) )
	List<Topic> topics;

	public String getName()
	{
		return name;
	}

	public void setName( String name )
	{
		this.name = name;
	}

	public String getEmail()
	{
		return email;
	}

	public void setEmail( String email )
	{
		this.email = email;
	}

	public List<Institution> getInstitution()
	{
		return institutions;
	}

	public void setInstitution( List<Institution> institutions )
	{
		this.institutions = institutions;
	}

	public Author addInstitution( final Institution institution )
	{
		if ( this.institutions == null )
			this.institutions = new ArrayList<Institution>();

		institutions.add( institution );
		return this;
	}

	public List<Institution> getInstitutions()
	{
		return institutions;
	}

	public void setInstitutions( List<Institution> institutions )
	{
		this.institutions = institutions;
	}

	public List<Topic> getTopics()
	{
		return topics;
	}

	public void setTopics( List<Topic> topics )
	{
		this.topics = topics;
	}

	public Author addTopic( Topic topic )
	{
		if ( this.topics == null )
			this.topics = new ArrayList<Topic>();

		this.topics.add( topic );
		return this;
	}

	public List<String> getAliases()
	{
		return aliases;
	}

	public void setAliases( List<String> aliases )
	{
		this.aliases = aliases;
	}

	public Author addAlias( final String aliasName )
	{
		if ( this.aliases == null )
			this.aliases = new ArrayList<String>();

		this.aliases.add( aliasName );

		return this;
	}
}
