package de.rwth.i9.palm.model;

import java.util.HashSet;
import java.util.List;
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
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.search.annotations.Indexed;

import de.rwth.i9.palm.persistence.PersistableResource;

@Entity
@Table( name = "circle" )
@Indexed
public class Circle extends PersistableResource
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
	@JoinTable( name = "circle_author", joinColumns = @JoinColumn( name = "circle_id" ) , inverseJoinColumns = @JoinColumn( name = "author_id" ) )
	private Set<Author> authors;

	@ManyToMany( cascade = CascadeType.ALL, fetch = FetchType.LAZY )
	@JoinTable( name = "circle_publication", joinColumns = @JoinColumn( name = "circle_id" ) , inverseJoinColumns = @JoinColumn( name = "publication_id" ) )
	private Set<Publication> publications;

	@OneToMany( cascade = CascadeType.ALL, fetch = FetchType.LAZY )
	@JoinColumn( name = "circle_id" )
	private List<CircleWidget> circleWidgets;

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

	public Circle addAuthor( Author author )
	{
		if ( this.authors == null )
			this.authors = new HashSet<Author>();

		this.authors.add( author );

		return this;
	}

	public Set<Publication> getPublications()
	{
		return publications;
	}

	public void setPublications( Set<Publication> publications )
	{
		this.publications = publications;
	}

	public Circle addPublication( Publication publication )
	{
		if ( this.publications == null )
			this.publications = new HashSet<Publication>();

		this.publications.add( publication );

		return this;
	}

	public List<CircleWidget> getCircleWidgets()
	{
		return circleWidgets;
	}

	public void setCircleWidgets( List<CircleWidget> circleWidgets )
	{
		this.circleWidgets = circleWidgets;
	}

}
