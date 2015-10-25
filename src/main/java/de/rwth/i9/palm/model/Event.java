package de.rwth.i9.palm.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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

	@Column
	private String name;

	@Column
	private java.sql.Timestamp crawlDate;

	@Column( length = 4 )
	@Field( index = Index.YES, analyze = Analyze.NO, store = Store.YES )
	private String year;

	/* from dblp */
	@Column
	private String dblpUrl;

	@Column( length = 10 )
	private String volume;

	@Column( name = "position_", columnDefinition = "int default 0" )
	private int position;

	@ManyToOne
	@JoinColumn( name = "academic_event_group_id" )
	@IndexedEmbedded
	@Boost( 2.0f )
	private EventGroup eventGroup;

	@OneToOne( cascade = CascadeType.ALL, fetch = FetchType.LAZY )
	private Location location;

	// as List, therefore it can be sorted on hibernate query
	@OneToMany( cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "event" )
	private List<Publication> publications;

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

	public String getDblpUrl()
	{
		return dblpUrl;
	}

	public void setDblpUrl( String dblpUrl )
	{
		this.dblpUrl = dblpUrl;
	}

	public String getVolume()
	{
		return volume;
	}

	public void setVolume( String volume )
	{
		this.volume = volume;
	}

	public String getDateFormat()
	{
		return dateFormat;
	}

	public void setDateFormat( String dateFormat )
	{
		this.dateFormat = dateFormat;
	}

	public List<Publication> getPublications()
	{
		return publications;
	}

	public void setPublications( List<Publication> publications )
	{
		this.publications = publications;
	}

	public Event addPublication( Publication publication )
	{
		if ( this.publications == null )
			this.publications = new ArrayList<Publication>();

		this.publications.add( publication );

		return this;
	}

	public java.sql.Timestamp getCrawlDate()
	{
		return crawlDate;
	}

	public void setCrawlDate( java.sql.Timestamp crawlDate )
	{
		this.crawlDate = crawlDate;
	}

}
