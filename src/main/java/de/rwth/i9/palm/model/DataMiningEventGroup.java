package de.rwth.i9.palm.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.search.annotations.Analyze;
import org.hibernate.search.annotations.Analyzer;
import org.hibernate.search.annotations.ContainedIn;
import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.Index;
import org.hibernate.search.annotations.Indexed;
import org.hibernate.search.annotations.Store;

import de.rwth.i9.palm.persistence.PersistableResource;

@Entity
@Table( name = "academic_event_group" )
@Indexed
public class DataMiningEventGroup extends PersistableResource
{

	@Column
	@Field( index = Index.YES, analyze = Analyze.YES, store = Store.YES )
	@Analyzer( definition = "eventanalyzer" )
	private String name;

	@ContainedIn
	@OneToMany( cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "eventGroup" )
	private List<Event> events;

	@OneToOne( fetch = FetchType.EAGER, mappedBy = "eventGroup" )
	private EventGroupInterestFlat eventgroup_interest_flat;

	public List<Event> getEvents()
	{
		return events;
	}

	public void setEvents( List<Event> events )
	{
		this.events = events;
	}

	public EventGroupInterestFlat getEventgroup_interest_flat()
	{
		return eventgroup_interest_flat;
	}

	public void setEventgroup_interest_flat( EventGroupInterestFlat eventgroup_interest_flat )
	{
		this.eventgroup_interest_flat = eventgroup_interest_flat;
	}

	public EventGroupInterestFlat getEventGroup_interest_flat()
	{
		return eventgroup_interest_flat;
	}

	public void setEventGroup_interest_flat( EventGroupInterestFlat eventgroup_interest_flat )
	{
		this.eventgroup_interest_flat = eventgroup_interest_flat;
	}

	public String getName()
	{
		return name;
	}

	public void setName( String name )
	{
		this.name = name;
	}

	public Object getJsonStub()
	{
		final String _id = this.getId();
		final String _name = this.getName();
		return new Object()
		{
			public final String id = _id;
			public final String name = _name;
		};
	}
}
