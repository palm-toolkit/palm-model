package de.rwth.i9.palm.model;

import java.util.ArrayList;
import java.util.Date;
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
@Table( name = "runtime" )
public class RunTime extends PersistableResource
{
	@Column
	private Date rundate;

	@Column
	@Lob
	private String notes;

	@Column
	private String outputPath;

	@OneToMany( cascade = CascadeType.ALL )
	@JoinColumn( name = "runtime_id" )
	List<Topic> topics;

	// getter & setter
	public Date getRundate()
	{
		return rundate;
	}

	public void setRundate( Date rundate )
	{
		this.rundate = rundate;
	}

	public List<Topic> getTopics()
	{
		return topics;
	}

	public void setTopics( List<Topic> topics )
	{
		this.topics = topics;
	}

	public String getNotes()
	{
		return notes;
	}

	public void setNotes( String notes )
	{
		this.notes = notes;
	}

	public RunTime addTopic( Topic topic )
	{
		if ( this.topics == null )
			topics = new ArrayList<Topic>();

		topics.add( topic );
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
