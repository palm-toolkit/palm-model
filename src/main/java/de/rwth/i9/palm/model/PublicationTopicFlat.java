package de.rwth.i9.palm.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.Store;

import de.rwth.i9.palm.persistence.PersistableResource;

@Entity
@Table( name = "publication_topic_flat" )
public class PublicationTopicFlat extends PersistableResource
{

	@Column
	@Field( store = Store.YES )
	private String topics;

	@OneToOne( fetch = FetchType.LAZY )
	@JoinColumn( name = "publication_id" )
	private DataMiningPublication publication;

	public String getTopics()
	{
		return topics;
	}

	public void setTopics( String topics )
	{
		this.topics = topics;
	}

	public void setPublication_id( String publication_id )
	{
		if ( this.publication == null )
		{
			this.publication = new DataMiningPublication();
			this.publication.setId( publication_id );
		}
	}
}