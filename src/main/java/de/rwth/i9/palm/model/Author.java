package de.rwth.i9.palm.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import de.rwth.i9.palm.persistence.PersistableResource;

@Entity
@Table( name = "author" )
public class Author extends PersistableResource
{
	@Column
	private String name;

	@Column
	private String firstname;

	@Column
	private String lastname;

	@Column
	private String email;

	/* few authors work for several institution */
	@ManyToMany( cascade = CascadeType.ALL, fetch = FetchType.LAZY )
	@JoinTable( name = "author_institution", joinColumns = @JoinColumn( name = "author_id" ), inverseJoinColumns = @JoinColumn( name = "institution_id" ) )
	private List<Institution> institutions;

	public String getName()
	{
		return name;
	}

	public void setName( String name )
	{
		this.name = name;
	}

	public String getFirstname()
	{
		return firstname;
	}

	public void setFirstname( String firstname )
	{
		this.firstname = firstname;
	}

	public String getLastname()
	{
		return lastname;
	}

	public void setLastname( String lastname )
	{
		this.lastname = lastname;
	}

	public String getEmail()
	{
		return email;
	}

	public void setEmail( String email )
	{
		this.email = email;
	}

	public List<Institution> getInstitution()
	{
		return institutions;
	}

	public void setInstitution( List<Institution> institutions )
	{
		this.institutions = institutions;
	}

	public Author addInstitution( final Institution institution )
	{
		if ( this.institutions == null )
			this.institutions = new ArrayList<Institution>();

		institutions.add( institution );
		return this;
	}
}
