package de.rwth.i9.palm.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import de.rwth.i9.palm.persistence.PersistableResource;

@Entity
@Table( name = "weighting_runtime" )
public class WeightingRuntime extends PersistableResource
{
	@Column
	private Date rundate;

	@Column
	@Lob
	private String notes;

	@Column
	private String outputPath;

	// relation
	@ManyToOne( cascade = CascadeType.ALL, fetch = FetchType.LAZY )
	@JoinColumn( name = "weighting_algorithm_id" )
	private WeightingAlgorithm weightingAlgorithm;

	@ManyToOne( cascade = CascadeType.ALL, fetch = FetchType.LAZY )
	@JoinColumn( name = "user_id" )
	private User user;

	@OneToMany( cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "weightingRuntime" )
	List<WeightingTopic> weightingTopics;

	// getter & setter
	public Date getRundate()
	{
		return rundate;
	}

	public WeightingAlgorithm getWeightingAlgorithm()
	{
		return weightingAlgorithm;
	}

	public void setWeightingAlgorithm( WeightingAlgorithm weightingAlgorithm )
	{
		this.weightingAlgorithm = weightingAlgorithm;
	}

	public User getUser()
	{
		return user;
	}

	public void setUser( User user )
	{
		this.user = user;
	}

	public void setRundate( Date rundate )
	{
		this.rundate = rundate;
	}

	public List<WeightingTopic> getTopics()
	{
		return weightingTopics;
	}

	public void setTopics( List<WeightingTopic> weightingTopics )
	{
		this.weightingTopics = weightingTopics;
	}

	public String getNotes()
	{
		return notes;
	}

	public void setNotes( String notes )
	{
		this.notes = notes;
	}

	public WeightingRuntime addTopic( WeightingTopic weightingTopic )
	{
		if ( this.weightingTopics == null )
			weightingTopics = new ArrayList<WeightingTopic>();

		weightingTopics.add( weightingTopic );
		return this;
	}

	public String getOutputPath()
	{
		return outputPath;
	}

	public void setOutputPath( String outputPath )
	{
		this.outputPath = outputPath;
	}
}
