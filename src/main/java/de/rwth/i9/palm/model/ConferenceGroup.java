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

import de.rwth.i9.palm.persistence.PersistableResource;

@Entity
@Table( name = "conference_group" )
public class ConferenceGroup extends PersistableResource
{
	@Column
	private String name;

	@Column
	@Lob
	private String description;

	@Enumerated( EnumType.STRING )
	@Column( length = 16 )
	private ConferenceType conferenceType;

	@Column( length = 24 )
	private String notation;

	@Column
	private String knowledgeGroup;

	@OneToMany( cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "conferenceGroup" )
	private List<Conference> conferences;

	public ConferenceType getConferenceType()
	{
		return conferenceType;
	}

	public void setConferenceType( ConferenceType conferenceType )
	{
		this.conferenceType = conferenceType;
	}

	public String getDescription()
	{
		return description;
	}

	public void setDescription( String description )
	{
		this.description = description;
	}

	public List<Conference> getConferences()
	{
		return conferences;
	}

	public void setConferences( List<Conference> conferences )
	{
		this.conferences = conferences;
	}

	public ConferenceGroup addConference( Conference conference )
	{
		if ( this.conferences == null )
			this.conferences = new ArrayList<Conference>();
		this.conferences.add( conference );
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

	public String getKnowledgeGroup()
	{
		return knowledgeGroup;
	}

	public void setKnowledgeGroup( String knowledgeGroup )
	{
		this.knowledgeGroup = knowledgeGroup;
	}

	public String getNotation()
	{
		return notation;
	}

	public void setNotation( String notation )
	{
		this.notation = notation;
	}
}
