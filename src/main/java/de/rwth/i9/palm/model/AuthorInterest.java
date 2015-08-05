package de.rwth.i9.palm.model;

import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapKeyColumn;

import de.rwth.i9.palm.persistence.PersistableResource;

@Entity( name = "author_interest" )
public class AuthorInterest extends PersistableResource
{
	@ElementCollection
	@MapKeyColumn( name = "term" )
	@Column( name = "weight" )
	@CollectionTable( name = "term_weight" )
	Map<String, Double> termWeights;

	@Column
	Date year;

	@Column( columnDefinition = "bit default 1" )
	private boolean valid = true;

	// relation
	@ManyToOne
	@JoinColumn( name = "author_interest_profile_id" )
	private AuthorInterestProfile authorInterestProfile;

	// getter & setter

	public Map<String, Double> getTermWeights()
	{
		return termWeights;
	}

	public void setTermWeights( Map<String, Double> termWeights )
	{
		this.termWeights = termWeights;
	}

	public AuthorInterest addTerm( String term, double weight )
	{
		if ( termWeights == null )
			termWeights = new LinkedHashMap<String, Double>();

		termWeights.put( term, weight );

		return this;
	}

}
