package de.rwth.i9.palm.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import de.rwth.i9.palm.persistence.PersistableResource;

@Entity
@Table( name = "algorithm" )
public class Algorithm extends PersistableResource
{
	@Column( length = 32 )
	private String name;

	@Column
	private String purpose;

	@Column
	@Lob
	private String description;

	// relations
	@OneToMany( cascade = CascadeType.ALL )
	@JoinColumn( name = "algorithm_id" )
	List<RunTime> runTimes;

	// getter & setter

	public String getName()
	{
		return name;
	}

	public void setName( String name )
	{
		this.name = name;
	}

	public String getPurpose()
	{
		return purpose;
	}

	public void setPurpose( String purpose )
	{
		this.purpose = purpose;
	}

	public String getDescription()
	{
		return description;
	}

	public void setDescription( String description )
	{
		this.description = description;
	}

	public List<RunTime> getRunTimes()
	{
		return runTimes;
	}

	public void setRunTimes( List<RunTime> runTimes )
	{
		this.runTimes = runTimes;
	}

	public Algorithm addRunTime( final RunTime runtime )
	{
		if ( this.runTimes == null )
			this.runTimes = new ArrayList<RunTime>();

		this.runTimes.add( runtime );
		return this;
	}
}
