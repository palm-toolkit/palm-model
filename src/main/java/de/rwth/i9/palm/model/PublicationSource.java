package de.rwth.i9.palm.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import de.rwth.i9.palm.persistence.PersistableResource;

@Entity
@Table( name = "publication_source" )
public class PublicationSource extends PersistableResource
{
	@Column
	@Lob
	private String title;

	/* comma separated author list */
	@Column
	@Lob
	private String authorString;

	/* comma separated author list */
	@Column
	private String authorAffiliation;

	@Column
	@Lob
	private String abstractText;

	@Column
	@Lob
	private String contentText;

	@Column
	@Lob
	private String keyword;
	
	@Column
	private String tag;
	
	@Column( length = 20 )
	private String date;
	
	@Column( length = 20 )
	private String dateFormat;

	@Column
	private String venue;

	@Column
	private String publicationType;
	
	@Column
	private String publisher;
	
	@Column( length = 5 )
	private String volume;
	
	@Column( length = 20 )
	private String issue;
	
	@Column( length = 20 )
	private String pages;
	
	@Column
	private int citedBy;

	@Column
	private String sourceUrl;

	@Column
	private String venueUrl;

	@Enumerated( EnumType.STRING )
	@Column( length = 16 )
	private SourceType sourceType;
	
	@Enumerated( EnumType.STRING )
	@Column( length = 16 )
	private SourceMethod sourceMethod;

	@Column
	private String mainSource;

	@Column
	@Lob
	private String mainSourceUrl;

	@Column
	@Lob
	private String additionalInformation;

	@ManyToOne
	@JoinColumn( name = "publication_id" )
	private Publication publication;

	public Publication getPublication()
	{
		return publication;
	}

	public void setPublication( Publication publication )
	{
		this.publication = publication;
	}

	public String getTitle()
	{
		return title;
	}

	public void setTitle( String title )
	{
		this.title = title;
	}

	public String getAbstractText()
	{
		return abstractText;
	}

	public void setAbstractText( String abstractText )
	{
		this.abstractText = abstractText;
	}

	public String getContentText()
	{
		return contentText;
	}

	public void setContentText( String contentText )
	{
		this.contentText = contentText;
	}

	public String getAuthorAffiliation()
	{
		return authorAffiliation;
	}

	public void setAuthorAffiliation( String authorAffiliation )
	{
		this.authorAffiliation = authorAffiliation;
	}

	public String getAuthorString()
	{
		return authorString;
	}

	public void setAuthorString( String authorString )
	{
		this.authorString = authorString;
	}

	public String getKeyword()
	{
		return keyword;
	}

	public void setKeyword( String keyword )
	{
		this.keyword = keyword;
	}

	public String getTag()
	{
		return tag;
	}

	public void setTag( String tag )
	{
		this.tag = tag;
	}

	public String getSourceUrl()
	{
		return sourceUrl;
	}

	public void setSourceUrl( String sourceUrl )
	{
		this.sourceUrl = sourceUrl;
	}

	public String getPublicationType()
	{
		return publicationType;
	}

	public void setPublicationType( String publicationType )
	{
		this.publicationType = publicationType;
	}

	public String getDate()
	{
		return date;
	}

	public void setDate( String date )
	{
		this.date = date;
	}

	public String getPublisher()
	{
		return publisher;
	}

	public void setPublisher( String publisher )
	{
		this.publisher = publisher;
	}

	public String getVolume()
	{
		return volume;
	}

	public void setVolume( String volume )
	{
		this.volume = volume;
	}

	public String getIssue()
	{
		return issue;
	}

	public void setIssue( String issue )
	{
		this.issue = issue;
	}

	public String getPages()
	{
		return pages;
	}

	public void setPages( String pages )
	{
		this.pages = pages;
	}

	public int getCitedBy()
	{
		return citedBy;
	}

	public void setCitedBy( int citedBy )
	{
		this.citedBy = citedBy;
	}

	public SourceMethod getSourceMethod()
	{
		return sourceMethod;
	}

	public void setSourceMethod( SourceMethod sourceMethod )
	{
		this.sourceMethod = sourceMethod;
	}

	public String getAdditionalInformation()
	{
		return additionalInformation;
	}

	public void setAdditionalInformation( String additionalInformation )
	{
		this.additionalInformation = additionalInformation;
	}
	
}