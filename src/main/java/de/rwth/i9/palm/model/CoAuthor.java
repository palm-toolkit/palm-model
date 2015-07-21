package de.rwth.i9.palm.model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import de.rwth.i9.palm.persistence.PersistableResource;

@Entity
@Table( name = "collaboration" )
public class CoAuthor extends PersistableResource
{

	@ManyToOne( cascade = CascadeType.ALL, fetch = FetchType.LAZY )
	@JoinColumn( name = "author_id" )
	private Author author;

	@ManyToOne( cascade = CascadeType.ALL, fetch = FetchType.LAZY )
	@JoinColumn( name = "coAuthor_id" )
	private Author coAuthor;


	public Author getAuthor()
	{
		return author;
	}

	public void setAuthor( Author author )
	{
		this.author = author;
	}

	public Author getCoAuthor()
	{
		return coAuthor;
	}

	public void setCoAuthor( Author coAuthor )
	{
		this.coAuthor = coAuthor;
	}

}
