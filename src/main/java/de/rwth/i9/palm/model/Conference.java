package de.rwth.i9.palm.model;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import de.rwth.i9.palm.persistence.PersistableResource;

@Entity
@Table( name = "conference" )
public class Conference extends PersistableResource
{
	@Column
	private String name;

	@Column
	private Date year;

	@Column
	private String type;

	@Column
	private String notation;

	@Column
	private String knowledgeGroup;

	@OneToOne( cascade = CascadeType.ALL, fetch = FetchType.LAZY )
	private Location location;

	public String getName()
	{
		return name;
	}

	public void setName( String name )
	{
		this.name = name;
	}

	public Date getYear()
	{
		return year;
	}

	public void setYear( Date year )
	{
		this.year = year;
	}

	public Location getLocation()
	{
		return location;
	}

	public void setLocation( Location location )
	{
		this.location = location;
	}

	public String getType()
	{
		return type;
	}

	public void setType( String type )
	{
		this.type = type;
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
