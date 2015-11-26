package de.rwth.i9.palm.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.Lob;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.search.annotations.Analyze;
import org.hibernate.search.annotations.ContainedIn;
import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.Index;
import org.hibernate.search.annotations.Indexed;
import org.hibernate.search.annotations.Store;

import de.rwth.i9.palm.persistence.PersistableResource;

@Entity
@Table( name = "academic_event_group" )
@Indexed
public class EventGroup extends PersistableResource
{
	@Column
	@Field( index = Index.YES, analyze = Analyze.NO, store = Store.YES )
	private String name;

	@Column
	@Lob
	private String description;

	@Enumerated( EnumType.STRING )
	@Column( length = 16 )
	private PublicationType publicationType;

	@Column
	@Field( index = Index.YES, analyze = Analyze.NO, store = Store.YES )
	private String notation;

	@Column
	private String dblpUrl;

	@ContainedIn
	@OneToMany( cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "eventGroup" )
	private List<Event> events;

	@Column
	private java.sql.Timestamp requestDate;

	public PublicationType getPublicationType()
	{
		return publicationType;
	}

	public void setPublicationType( PublicationType publicationType )
	{
		this.publicationType = publicationType;
	}

	public String getDescription()
	{
		return description;
	}

	public void setDescription( String description )
	{
		this.description = description;
	}

	public List<Event> getEvents()
	{
		return events;
	}

	public void setEvents( List<Event> events )
	{
		this.events = events;
	}


	public EventGroup addEvent( Event event )
	{
		if ( this.events == null )
			this.events = new ArrayList<Event>();
		this.events.add( event );
		return this;
	}

	public String getName()
	{
		return name;
	}

	public void setName( String name )
	{
		this.name = name;
	}

	public String getDblpUrl()
	{
		return dblpUrl;
	}

	public void setDblpUrl( String dblpUrl )
	{
		this.dblpUrl = dblpUrl;
	}

	public String getNotation()
	{
		return notation;
	}

	public void setNotation( String notation )
	{
		this.notation = notation;
	}

	public java.sql.Timestamp getRequestDate()
	{
		return requestDate;
	}

	public void setRequestDate( java.sql.Timestamp requestDate )
	{
		this.requestDate = requestDate;
	}

}
