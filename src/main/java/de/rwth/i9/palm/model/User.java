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
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import de.rwth.i9.palm.persistence.PersistableResource;

@Entity
@Table( name = "user" )
public class User extends PersistableResource
{
	@Column( length = 80 )
	private String name;

	@Column( length = 80 )
	private String email;

	@Column
	private String password;

	@Column( columnDefinition = "bit default 1" )
	private boolean enabled = true;

	@Column
	private Date lastLogin;

	@Column
	private Date lastLogout;

	@Transient
	private String sessionId;

	// relations
	@ManyToMany( cascade = CascadeType.ALL, fetch = FetchType.LAZY )
	@JoinTable( name = "user_function", joinColumns = @JoinColumn( name = "user_id" ), inverseJoinColumns = @JoinColumn( name = "function_id" ) )
	private List<Function> functions;

	@ManyToOne( cascade = CascadeType.ALL, fetch = FetchType.LAZY )
	@JoinColumn( name = "role_id" )
	private Role role;

	@OneToMany( cascade = CascadeType.ALL, fetch = FetchType.LAZY )
	@JoinColumn( name = "user_id" )
	private List<Dataset> datasets;

	// getter and setter

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

	public String getPassword()
	{
		return password;
	}

	public void setPassword( String password )
	{
		this.password = password;
	}

	public boolean isEnabled()
	{
		return enabled;
	}

	public void setEnabled( boolean enabled )
	{
		this.enabled = enabled;
	}

	public Date getLastLogin()
	{
		return lastLogin;
	}

	public void setLastLogin( Date lastLogin )
	{
		this.lastLogin = lastLogin;
	}

	public Date getLastLogout()
	{
		return lastLogout;
	}

	public void setLastLogout( Date lastLogout )
	{
		this.lastLogout = lastLogout;
	}

	public String getSessionId()
	{
		return sessionId;
	}

	public void setSessionId( String sessionId )
	{
		this.sessionId = sessionId;
	}

	public List<Function> getFunctions()
	{
		return functions;
	}

	public User addFunction( final Function function )
	{
		if ( this.functions == null )
			this.functions = new ArrayList<Function>();

		this.functions.add( function );

		return this;
	}

	public void setFunctions( List<Function> functions )
	{
		this.functions = functions;
	}

	public List<Dataset> getDatasets()
	{
		return datasets;
	}

	public void setDatasets( List<Dataset> datasets )
	{
		this.datasets = datasets;
	}

	public User addDataset( final Dataset dataset )
	{
		if ( this.datasets == null )
			this.datasets = new ArrayList<Dataset>();

		this.datasets.add( dataset );

		return this;
	}
}
