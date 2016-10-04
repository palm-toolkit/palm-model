package de.rwth.i9.palm.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Type;
import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.Store;

import de.rwth.i9.palm.persistence.PersistableResource;

@Entity
@Table( name = "author_interest_flat" )
public class AuthorInterestFlat extends PersistableResource
{

	@Column
	@Type( type = "text" )
	@Field( store = Store.YES )
	private String interests;

	@OneToOne( fetch = FetchType.LAZY )
	@JoinColumn( name = "author_id" )
	private DataMiningAuthor author;

	public String getInterests()
	{
		return interests;
	}

	public void setInterests( String interests )
	{
		this.interests = interests;
	}

	public void setAuthor_id( String author_id )
	{
		if ( this.author == null )
		{
			this.author = new DataMiningAuthor();
			this.author.setId( author_id );
		}
	}
}