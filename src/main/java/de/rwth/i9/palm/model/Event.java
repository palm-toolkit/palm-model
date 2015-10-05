package de.rwth.i9.palm.model;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.search.annotations.Analyze;
import org.hibernate.search.annotations.Boost;
import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.Index;
import org.hibernate.search.annotations.Indexed;
import org.hibernate.search.annotations.IndexedEmbedded;
import org.hibernate.search.annotations.Store;

import de.rwth.i9.palm.persistence.PersistableResource;

@Entity
@Table( name = "academic_event" )
@Indexed
public class Event extends PersistableResource
{
	@Column
	private Date date;

	@Column( length = 10 )
	private String dateFormat;

	@Column( length = 4 )
	@Field( index = Index.YES, analyze = Analyze.NO, store = Store.YES )
	private String year;

	/* from dblp */
	@Column
	private String url;

	@Column( length = 5 )
	private String volume;

	@Column( length = 5 )
	private String number;

	@ManyToOne
	@JoinColumn( name = "academic_event_group_id" )
	@IndexedEmbedded
	@Boost( 2.0f )
	private EventGroup eventGroup;

	@OneToOne( cascade = CascadeType.ALL, fetch = FetchType.LAZY )
	private Location location;

	@OneToMany( cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "event" )
	private Set<Publication> publications;

	public Date getDate()
	{
		return date;
	}

	public void setDate( Date date )
	{
		this.date = date;
	}

	public Location getLocation()
	{
		return location;
	}

	public void setLocation( Location location )
	{
		this.location = location;
	}

	public String getYear()
	{
		return year;
	}

	public void setYear( String year )
	{
		this.year = year;
	}

	public EventGroup getEventGroup()
	{
		return eventGroup;
	}

	public void setEventGroup( EventGroup eventGroup )
	{
		this.eventGroup = eventGroup;
	}

	public String getUrl()
	{
		return url;
	}

	public void setUrl( String url )
	{
		this.url = url;
	}

	public String getVolume()
	{
		return volume;
	}

	public void setVolume( String volume )
	{
		this.volume = volume;
	}

	public String getNumber()
	{
		return number;
	}

	public void setNumber( String number )
	{
		this.number = number;
	}

	public String getDateFormat()
	{
		return dateFormat;
	}

	public void setDateFormat( String dateFormat )
	{
		this.dateFormat = dateFormat;
	}

	public Set<Publication> getPublications()
	{
		return publications;
	}

	public void setPublications( Set<Publication> publications )
	{
		this.publications = publications;
	}

	public Event addPublication( Publication publication )
	{
		if ( this.publications == null )
			this.publications = new HashSet<Publication>();

		this.publications.add( publication );

		return this;
	}
}
