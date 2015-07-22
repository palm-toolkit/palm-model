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

@Entity( name = "weighting_topic" )
public class WeightingTopic extends PersistableResource
{
	@ElementCollection
	@MapKeyColumn( name = "term" )
	@Column( name = "weight" )
	@CollectionTable( name = "term_weight" )
	Map<String, Double> termWeights;

	/* collection of term with comma separated weight */
	@Column
	String termString;

	/* percentage of topic composed in a document */
	@Column( columnDefinition = "Decimal(3,3) default '0.000'" )
	double composition;

	// relations
	@ManyToMany( cascade = CascadeType.ALL, fetch = FetchType.LAZY )
	@JoinTable( name = "weighting_topic_broader", joinColumns = @JoinColumn( name = "weighting_topic_id", referencedColumnName = "id" ), inverseJoinColumns = @JoinColumn( name = "broader_id" ) )
	private List<WeightingTopic> broaders;

	@ManyToMany( cascade = CascadeType.ALL, fetch = FetchType.LAZY )
	@JoinTable( name = "weighting_topic_narrower", joinColumns = @JoinColumn( name = "weighting_topic_id", referencedColumnName = "id" ), inverseJoinColumns = @JoinColumn( name = "narrower_id" ) )
	private List<WeightingTopic> narrowers;

	@ManyToOne( cascade = CascadeType.ALL, fetch = FetchType.LAZY )
	@JoinColumn( name = "extraction_runtime_id" )
	private WeightingRuntime weightingRuntime;

	@ManyToMany( cascade = CascadeType.ALL, fetch = FetchType.LAZY )
	@JoinTable( name = "weighting_topic_publication_topic", joinColumns = @JoinColumn( name = "weighting_topic_id", referencedColumnName = "id" ), inverseJoinColumns = @JoinColumn( name = "publication_topic_id" ) )
	private List<WeightingTopic> publicationTopics;

	// getter & setter

	public Map<String, Double> getTermWeights()
	{
		return termWeights;
	}

	public void setTermWeights( Map<String, Double> termWeights )
	{
		this.termWeights = termWeights;
	}

	public WeightingTopic addTerm( String term, double weight )
	{
		if ( termWeights == null )
			termWeights = new LinkedHashMap<String, Double>();

		termWeights.put( term, weight );

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

	public WeightingTopic addBroader( final WeightingTopic broader )
	{
		if ( broader == null )
			this.broaders = new ArrayList<WeightingTopic>();

		this.broaders.add( broader );

		return this;
	}

	public List<WeightingTopic> getBroaders()
	{
		return this.broaders;
	}

	public void setBroaders( final List<WeightingTopic> broaders )
	{
		this.broaders = broaders;
	}

	public WeightingTopic addNarrower( final WeightingTopic narrower )
	{
		if ( this.narrowers == null )
			this.narrowers = new ArrayList<WeightingTopic>();

		this.narrowers.add( narrower );

		return this;
	}

	public List<WeightingTopic> getNarrowers()
	{
		return this.narrowers;
	}

	public void setNarrowers( final List<WeightingTopic> narrowers )
	{
		this.narrowers = narrowers;
	}

	public WeightingRuntime getWeightingRuntime()
	{
		return weightingRuntime;
	}

	public void setWeightingRuntime( WeightingRuntime weightingRuntime )
	{
		this.weightingRuntime = weightingRuntime;
	}

}
