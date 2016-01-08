package de.rwth.i9.palm.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.search.annotations.Indexed;
import org.hibernate.search.annotations.IndexedEmbedded;

import de.rwth.i9.palm.persistence.PersistableType;

@Entity
@Indexed
@Table( name = "publication_author" )
public class PublicationAuthor extends PersistableType
{
	@OneToOne
	@JoinColumn( name = "publication_id" )
	@IndexedEmbedded
	private Publication publication;

	@OneToOne
	@JoinColumn( name = "author_id" )
	@IndexedEmbedded
	private Author author;

	@Column( name = "position_", columnDefinition = "int default 0" )
	private int position;

	public Publication getPublication()
	{
		return publication;
	}

	public void setPublication( Publication publication )
	{
		this.publication = publication;
	}

	public Author getAuthor()
	{
		return author;
	}

	public void setAuthor( Author author )
	{
		this.author = author;
	}

	public int getPosition()
	{
		return position;
	}

	public void setPosition( int position )
	{
		this.position = position;
	}

}

