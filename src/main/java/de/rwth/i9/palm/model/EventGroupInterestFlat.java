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
@Table( name = "eventgroup_interest_flat" )
public class EventGroupInterestFlat extends PersistableResource
{

	@Column
	@Type( type = "text" )
	@Field( store = Store.YES )
	private String interests;

	@OneToOne( fetch = FetchType.LAZY )
	@JoinColumn( name = "eventgroup_id" )
	private DataMiningEventGroup eventGroup;

	public String getInterests()
	{
		return interests;
	}

	public void setInterests( String interests )
	{
		this.interests = interests;
	}

	public void setEventGroup_id( String eventGroup_id )
	{
		if ( this.eventGroup == null )
		{
			this.eventGroup = new DataMiningEventGroup();
			this.eventGroup.setId( eventGroup_id );
		}
	}
}