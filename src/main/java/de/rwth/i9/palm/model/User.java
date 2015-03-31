package de.rwth.i9.palm.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import de.rwth.i9.palm.persistence.PersistableResource;

@Entity
@Table( name = "user" )
public class User extends PersistableResource
{
	@Column
	private String name;

	@Column
	private String email;

	@OneToOne( cascade = CascadeType.ALL, fetch = FetchType.LAZY )
	private Role role;

	public String getName()
	{
		return name;
	}

	public void setName( String name )
	{
		this.name = name;
	}

	public String getEmail()
	{
		return email;
	}

	public void setEmail( String email )
	{
		this.email = email;
	}

	public Role getRole()
	{
		return role;
	}

	public void setRole( Role role )
	{
		this.role = role;
	}
}
