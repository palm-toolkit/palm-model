package de.rwth.i9.palm.model;

import java.util.LinkedHashMap;
import java.util.Map;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.MapKeyColumn;

import de.rwth.i9.palm.persistence.PersistableResource;

@Entity( name = "config" )
public class PalmConfiguration extends PersistableResource
{
	@Column( unique = true )
	String name;

	@ElementCollection
	@MapKeyColumn( name = "key" )
	@Column( name = "value" )
	@CollectionTable( name = "configuration_map" )
	Map<String, String> configurationMap;

	public String getName()
	{
		return name;
	}

	public void setName( String name )
	{
		this.name = name;
	}

	public Map<String, String> getConfigurationMap()
	{
		return configurationMap;
	}

	public void setConfigurationMap( Map<String, String> configurationMap )
	{
		this.configurationMap = configurationMap;
	}

	public PalmConfiguration addConfigurationMap( String key, String value )
	{
		if ( configurationMap == null )
			configurationMap = new LinkedHashMap<String, String>();

		configurationMap.put( key, value );

		return this;
	}
}
