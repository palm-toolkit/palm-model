package de.rwth.i9.palm.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import de.rwth.i9.palm.persistence.PersistableResource;

@Entity
@Table( name = "term_value" )
public class TermValue extends PersistableResource
{
	@Column( unique = true, nullable = false, length = 50 )
	private String term;

	public String getTerm()
	{
		return term;
	}

	public void setTerm( String term )
	{
		this.term = term;
	}

}
