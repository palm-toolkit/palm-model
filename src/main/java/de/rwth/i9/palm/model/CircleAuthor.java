package de.rwth.i9.palm.model;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.search.annotations.Indexed;
import org.hibernate.search.annotations.IndexedEmbedded;

import de.rwth.i9.palm.persistence.PersistableType;

@Entity
@Indexed
@Table( name = "circle_author" )
public class CircleAuthor extends PersistableType
{
	@ManyToOne
	@JoinColumn( name = "circle_id" )
	@IndexedEmbedded
	private Circle circle;

	@ManyToOne
	@JoinColumn( name = "author_id" )
	@IndexedEmbedded
	private Author author;

	public Circle getCircle()
	{
		return circle;
	}

	public void setCircle( Circle circle )
	{
		this.circle = circle;
	}

	public Author getAuthor()
	{
		return author;
	}

	public void setAuthor( Author author )
	{
		this.author = author;
	}

}
