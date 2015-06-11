package de.rwth.i9.palm.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Lob;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import de.rwth.i9.palm.persistence.PersistableResource;

@Entity
@Table( name = "extraction_service" )
public class ExtractionService extends PersistableResource
{
	@Column( length = 32 )
	private String name;

	@Column
	private String purpose;

	@Column
	@Lob
	private String description;

	// relations
	@OneToMany( cascade = CascadeType.ALL, mappedBy = "extractionService" )
	List<ExtractionRuntime> extractionRuntimes;

	// getter & setter

	public String getName()
	{
		return name;
	}

	public void setName( String name )
	{
		this.name = name;
	}

	public String getPurpose()
	{
		return purpose;
	}

	public void setPurpose( String purpose )
	{
		this.purpose = purpose;
	}

	public String getDescription()
	{
		return description;
	}

	public void setDescription( String description )
	{
		this.description = description;
	}

	public List<ExtractionRuntime> getExtractionRuntimes()
	{
		return extractionRuntimes;
	}

	public void setExtractionRuntimes( List<ExtractionRuntime> extractionRuntimes )
	{
		this.extractionRuntimes = extractionRuntimes;
	}

	public ExtractionService addExtractionRuntime( final ExtractionRuntime extractionRuntime )
	{
		if ( this.extractionRuntimes == null )
			this.extractionRuntimes = new ArrayList<ExtractionRuntime>();

		this.extractionRuntimes.add( extractionRuntime );
		return this;
	}
}
