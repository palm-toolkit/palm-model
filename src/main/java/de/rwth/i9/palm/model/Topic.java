package de.rwth.i9.palm.model;

import java.util.LinkedHashMap;
import java.util.Map;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.MapKeyColumn;
import javax.persistence.Table;

@Entity
@Table( name = "topic" )
public class Topic extends Concept
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
	@Column( columnDefinition = "int default 0.0" )
	double composition;

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
}
