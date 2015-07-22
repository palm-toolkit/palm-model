package de.rwth.i9.palm.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import de.rwth.i9.palm.persistence.PersistableResource;

@Entity
@Table( name = "extraction_runtime" )
public class ExtractionRuntime extends PersistableResource
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
	@JoinColumn( name = "extraction_service_id" )
	private ExtractionService extractionService;

	@ManyToOne( cascade = CascadeType.ALL, fetch = FetchType.LAZY )
	@JoinColumn( name = "user_id" )
	private User user;

	@OneToMany( cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "extractionRuntime" )
	List<PublicationTopic> publicationTopics;

//	@ManyToMany( cascade = CascadeType.ALL, fetch = FetchType.LAZY )
//	@JoinTable( name = "extraction_runtime_publication", joinColumns = @JoinColumn( name = "extraction_runtime_id" ), inverseJoinColumns = @JoinColumn( name = "publication_id" ) )

	// getter & setter
	public Date getRundate()
	{
		return rundate;
	}

	public ExtractionService getExtractionService()
	{
		return extractionService;
	}

	public void setExtractionService( ExtractionService extractionService )
	{
		this.extractionService = extractionService;
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

	public List<PublicationTopic> getTopics()
	{
		return publicationTopics;
	}

	public void setTopics( List<PublicationTopic> publicationTopics )
	{
		this.publicationTopics = publicationTopics;
	}

	public String getNotes()
	{
		return notes;
	}

	public void setNotes( String notes )
	{
		this.notes = notes;
	}

	public ExtractionRuntime addTopic( PublicationTopic publicationTopic )
	{
		if ( this.publicationTopics == null )
			publicationTopics = new ArrayList<PublicationTopic>();

		publicationTopics.add( publicationTopic );
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
