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
import javax.persistence.MapKeyColumn;

import de.rwth.i9.palm.persistence.PersistableResource;

@Entity( name = "topic" )
public class Topic extends PersistableResource
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
	@Column( columnDefinition = "Decimal(4,5) default '0.00000'" )
	double composition;

	// relations
	@ManyToMany( cascade = CascadeType.ALL, fetch = FetchType.LAZY )
	@JoinTable( name = "topic_topicbroader", joinColumns = @JoinColumn( name = "topic_id", referencedColumnName = "id" ), inverseJoinColumns = @JoinColumn( name = "broader_id" ) )
	private List<Topic> broaders;

	@ManyToMany( cascade = CascadeType.ALL, fetch = FetchType.LAZY )
	@JoinTable( name = "topic_topicnarrower", joinColumns = @JoinColumn( name = "topic_id", referencedColumnName = "id" ), inverseJoinColumns = @JoinColumn( name = "narrower_id" ) )
	private List<Topic> narrowers;

	// getter & setter

	public Map<String, Double> getTermValues()
	{
		return termValues;
	}

	public void setTermValues( Map<String, Double> termValues )
	{
		this.termValues = termValues;
	}

	public Topic addTerm( String term, double value )
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

	public Topic addBroader( final Topic broader )
	{
		if ( broader == null )
			this.broaders = new ArrayList<Topic>();

		this.broaders.add( broader );

		return this;
	}

	public List<Topic> getBroaders()
	{
		return this.broaders;
	}

	public void setBroaders( final List<Topic> broaders )
	{
		this.broaders = broaders;
	}

	public Topic addNarrower( final Topic narrower )
	{
		if ( this.narrowers == null )
			this.narrowers = new ArrayList<Topic>();

		this.narrowers.add( narrower );

		return this;
	}

	public List<Topic> getNarrowers()
	{
		return this.narrowers;
	}

	public void setNarrowers( final List<Topic> narrowers )
	{
		this.narrowers = narrowers;
	}
}
