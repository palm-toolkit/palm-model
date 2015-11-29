package de.rwth.i9.palm.model;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.search.annotations.Indexed;

import de.rwth.i9.palm.persistence.PersistableResource;

@Entity
@Table( name = "circle_author" )
@Indexed
public class CircleAuthor extends PersistableResource
{

	@Column
	private String name;

	@Column
	private java.sql.Timestamp creationDate;

	@ManyToOne( cascade = CascadeType.ALL )
	@JoinColumn( name = "creator_id" )
	private User creator;

	@Column
	@Lob
	private String description;

	@ManyToMany( cascade = CascadeType.ALL, fetch = FetchType.LAZY )
	@JoinTable( name = "circle_author_author", joinColumns = @JoinColumn( name = "circle_id" ) , inverseJoinColumns = @JoinColumn( name = "author_id" ) )
	private Set<Author> authors;

	public String getName()
	{
		return name;
	}

	public void setName( String name )
	{
		this.name = name;
	}

	public java.sql.Timestamp getCreationDate()
	{
		return creationDate;
	}

	public void setCreationDate( java.sql.Timestamp creationDate )
	{
		this.creationDate = creationDate;
	}

	public User getCreator()
	{
		return creator;
	}

	public void setCreator( User creator )
	{
		this.creator = creator;
	}

	public String getDescription()
	{
		return description;
	}

	public void setDescription( String description )
	{
		this.description = description;
	}

	public Set<Author> getAuthors()
	{
		return authors;
	}

	public void setAuthors( Set<Author> authors )
	{
		this.authors = authors;
	}

}
