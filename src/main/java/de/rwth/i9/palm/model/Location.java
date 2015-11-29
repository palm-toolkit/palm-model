package de.rwth.i9.palm.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import de.rwth.i9.palm.persistence.PersistableResource;

@Entity
@Table( name = "location" )
public class Location extends PersistableResource
{
	@Column
	private String address;

	@Column
	private String city;

	@Column
	private String state;

	@Column
	private double latitude;

	@Column
	private double longitude;

	@ManyToOne
	@JoinColumn( name = "country_id" )
	private Country country;

	public String getAddress()
	{
		return address;
	}

	public void setAddress( String address )
	{
		this.address = address;
	}

	public String getCity()
	{
		return city;
	}

	public void setCity( String city )
	{
		this.city = city;
	}

	public String getState()
	{
		return state;
	}

	public void setState( String state )
	{
		this.state = state;
	}

	public double getLatitude()
	{
		return latitude;
	}

	public void setLatitude( double latitude )
	{
		this.latitude = latitude;
	}

	public double getLongitude()
	{
		return longitude;
	}

	public void setLongitude( double longitude )
	{
		this.longitude = longitude;
	}

	public Country getCountry()
	{
		return country;
	}

	public void setCountry( Country country )
	{
		this.country = country;
	}
}
