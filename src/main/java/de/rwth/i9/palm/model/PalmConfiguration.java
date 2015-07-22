package de.rwth.i9.palm.model;

import java.util.LinkedHashMap;
import java.util.Map;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.MapKeyColumn;

import de.rwth.i9.palm.persistence.PersistableResource;

@Entity( name = "config" )
public class PalmConfiguration extends PersistableResource
{
	@Column( unique = true )
	String name;

	@ElementCollection
	@MapKeyColumn( name = "name" )
	@Column( name = "value" )
	@CollectionTable( name = "config_map", joinColumns = @JoinColumn( name = "palm_configuration_id" ) )
	Map<String, String> configMap = new LinkedHashMap<String, String>();

	public String getName()
	{
		return name;
	}

	public void setName( String name )
	{
		this.name = name;
	}

	public Map<String, String> getConfigMap()
	{
		return configMap;
	}

	public void setConfigMap( Map<String, String> configMap )
	{
		this.configMap = configMap;
	}

	public PalmConfiguration addConfigMap( String key, String value )
	{
		if ( configMap == null )
			configMap = new LinkedHashMap<String, String>();

		configMap.put( key, value );

		return this;
	}
}
