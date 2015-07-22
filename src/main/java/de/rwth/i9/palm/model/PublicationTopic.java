package de.rwth.i9.palm.model;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.CascadeType;
import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.MapKeyColumn;

import de.rwth.i9.palm.persistence.PersistableResource;

@Entity( name = "publication_topic" )
public class PublicationTopic extends PersistableResource
{
	@ElementCollection
	@MapKeyColumn( name = "term" )
	@Column( name = "value" )
	@CollectionTable( name = "term_value" )
	Map<String, Double> termValues;

	/* collection of term with comma separated value */
	@Column
	String termString;

	/* percentage of topic composed in a document */
	@Column( columnDefinition = "Decimal(3,3) default '0.000'" )
	double composition;

	// relations
	@ManyToMany( cascade = CascadeType.ALL, fetch = FetchType.LAZY )
	@JoinTable( name = "publication_topic_broader", joinColumns = @JoinColumn( name = "publication_topic_id", referencedColumnName = "id" ), inverseJoinColumns = @JoinColumn( name = "broader_id" ) )
	private List<PublicationTopic> broaders;

	@ManyToMany( cascade = CascadeType.ALL, fetch = FetchType.LAZY )
	@JoinTable( name = "publication_topic_narrower", joinColumns = @JoinColumn( name = "publication_topic_id", referencedColumnName = "id" ), inverseJoinColumns = @JoinColumn( name = "narrower_id" ) )
	private List<PublicationTopic> narrowers;

	@ManyToOne( cascade = CascadeType.ALL, fetch = FetchType.LAZY )
	@JoinColumn( name = "extraction_runtime_id" )
	private ExtractionRuntime extractionRuntime;

	@ManyToOne( cascade = CascadeType.ALL, fetch = FetchType.LAZY )
	@JoinColumn( name = "publication_id" )
	private Publication publication;

	// getter & setter

	public Map<String, Double> getTermValues()
	{
		return termValues;
	}

	public void setTermValues( Map<String, Double> termValues )
	{
		this.termValues = termValues;
	}

	public PublicationTopic addTerm( String term, double value )
	{
		if ( termValues == null )
			termValues = new LinkedHashMap<String, Double>();

		termValues.put( term, value );

		return this;
	}

	public String getTermString()
	{
		return termString;
	}

	public void setTermString( String termString )
	{
		this.termString = termString;
	}

	public double getComposition()
	{
		return composition;
	}

	public void setComposition( double composition )
	{
		this.composition = composition;
	}

	public PublicationTopic addBroader( final PublicationTopic broader )
	{
		if ( broader == null )
			this.broaders = new ArrayList<PublicationTopic>();

		this.broaders.add( broader );

		return this;
	}

	public List<PublicationTopic> getBroaders()
	{
		return this.broaders;
	}

	public void setBroaders( final List<PublicationTopic> broaders )
	{
		this.broaders = broaders;
	}

	public PublicationTopic addNarrower( final PublicationTopic narrower )
	{
		if ( this.narrowers == null )
			this.narrowers = new ArrayList<PublicationTopic>();

		this.narrowers.add( narrower );

		return this;
	}

	public List<PublicationTopic> getNarrowers()
	{
		return this.narrowers;
	}

	public void setNarrowers( final List<PublicationTopic> narrowers )
	{
		this.narrowers = narrowers;
	}

	public ExtractionRuntime getExtractionRuntime()
	{
		return extractionRuntime;
	}

	public void setExtractionRuntime( ExtractionRuntime extractionRuntime )
	{
		this.extractionRuntime = extractionRuntime;
	}

	public Publication getPublication()
	{
		return publication;
	}

	public void setPublication( Publication publication )
	{
		this.publication = publication;
	}

}
