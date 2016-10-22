package de.rwth.i9.palm.model;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Lob;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.search.annotations.Analyze;
import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.Index;
import org.hibernate.search.annotations.Indexed;
import org.hibernate.search.annotations.Store;

import de.rwth.i9.palm.persistence.PersistableResource;

@Entity
@Table( name = "publication" )
@Indexed
public class DataMiningPublication extends PersistableResource
{
	@Column( nullable = false, length = 255 )
	@Field( index = Index.YES, analyze = Analyze.YES, store = Store.YES )
	@Lob
	private String title;

	@Column( columnDefinition = "int default 0" )
	private int citedBy;

	@OneToOne( fetch = FetchType.EAGER, mappedBy = "publication" )
	private PublicationTopicFlat publication_topic_flat;

	public PublicationTopicFlat getPublication_topic_flat()
	{
		return publication_topic_flat;
	}

	public void setPublication_topic_flat( PublicationTopicFlat publication_topic_flat )
	{
		this.publication_topic_flat = publication_topic_flat;
	}

	public int getCitedBy()
	{
		return citedBy;
	}

	public void setCitedBy( int citedBy )
	{
		this.citedBy = citedBy;
	}

	@OneToMany( cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "publication", orphanRemoval = true )
	private Set<PublicationTopic> publicationTopics;

	public String getTitle()
	{
		return title;
	}

	public void setTitle( String title )
	{
		this.title = title;
	}

	public Set<PublicationTopic> getPublicationTopics()
	{
		return publicationTopics;
	}

	public void setPublicationTopics( Set<PublicationTopic> publicationTopics )
	{
		this.publicationTopics = publicationTopics;
	}

	public Object getJsonStub()
	{
		final String _id = this.getId();
		return new Object()
		{
			public final String id = _id;
		};
	}

}

