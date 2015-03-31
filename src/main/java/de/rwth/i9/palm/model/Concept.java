package de.rwth.i9.palm.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

import de.rwth.i9.palm.persistence.PersistableResource;

@Entity
public class Concept extends PersistableResource
{
	@ManyToMany( cascade = CascadeType.ALL, fetch = FetchType.LAZY )
	@JoinTable( name = "concept_conceptbroader", joinColumns = @JoinColumn( name = "concept_id", referencedColumnName = "id" ), inverseJoinColumns = @JoinColumn( name = "broader_id" ) )
	private List<Concept> broader;

	@ManyToMany( cascade = CascadeType.ALL, fetch = FetchType.LAZY )
	@JoinTable( name = "concept_conceptnarrower", joinColumns = @JoinColumn( name = "concept_id", referencedColumnName = "id" ), inverseJoinColumns = @JoinColumn( name = "narrower_id" ) )
	private List<Concept> narrower;

	public Concept addBroader( final Concept concept )
	{
		if ( broader == null )
			broader = new ArrayList<Concept>();

		broader.add( concept );

		return this;
	}

	public List<Concept> getBroader()
	{
		return broader;
	}

	public void setBroader( final List<Concept> skos_broader )
	{
		broader = skos_broader;
	}

	public Concept addNarrower( final Concept concept )
	{
		if ( narrower == null )
			narrower = new ArrayList<Concept>();

		narrower.add( concept );

		return this;
	}

	public List<Concept> getNarrower()
	{
		return narrower;
	}

	public void setNarrower( final List<Concept> skos_narrower )
	{
		narrower = skos_narrower;
	}
}
