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
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.apache.lucene.analysis.core.LowerCaseFilterFactory;
import org.apache.lucene.analysis.snowball.SnowballPorterFilterFactory;
import org.apache.lucene.analysis.standard.StandardTokenizerFactory;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.hibernate.search.annotations.Analyze;
import org.hibernate.search.annotations.Analyzer;
import org.hibernate.search.annotations.AnalyzerDef;
import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.Index;
import org.hibernate.search.annotations.Indexed;
import org.hibernate.search.annotations.Parameter;
import org.hibernate.search.annotations.Store;
import org.hibernate.search.annotations.TermVector;
import org.hibernate.search.annotations.TokenFilterDef;
import org.hibernate.search.annotations.TokenizerDef;

import de.rwth.i9.palm.persistence.PersistableResource;

@Entity
@Table( name = "publication" )
@JsonIgnoreProperties( { } )
@Indexed
@AnalyzerDef( name = "customanalyzer", tokenizer = @TokenizerDef( factory = StandardTokenizerFactory.class ), filters = { @TokenFilterDef( factory = LowerCaseFilterFactory.class ), @TokenFilterDef( factory = SnowballPorterFilterFactory.class, params = { @Parameter( name = "language", value = "English" ) } ) } )
public class Publication extends PersistableResource
{
	@Column( unique = true, nullable = false )
	@Field( index = Index.YES, analyze = Analyze.YES, store = Store.YES )
	private String title;
	
	@Column
	@Lob
	@Field( index = Index.YES, termVector = TermVector.WITH_POSITION_OFFSETS, store = Store.YES )
	private String abstractOriginal;

	@Column
	@Lob
	@Field( index = Index.YES, termVector = TermVector.WITH_POSITION_OFFSETS, store = Store.YES )
	@Analyzer( definition = "customanalyzer" )
	private String abstractTokenized;

	@Column
	@Lob
	@Field( index = Index.YES, termVector = TermVector.WITH_POSITION_OFFSETS, store = Store.YES )
	private String fulltextOriginal;

	@Column
	@Lob
	@Field( index = Index.YES, termVector = TermVector.WITH_POSITION_OFFSETS, store = Store.YES )
	@Analyzer( definition = "customanalyzer" )
	private String fulltextTokenized;

	@Column
	private Date createdAt;
	
	// relations	
	@ManyToMany( cascade = CascadeType.ALL, fetch = FetchType.LAZY )
	@JoinTable( name = "publication_keyword", joinColumns = @JoinColumn( name = "publication_id" ), inverseJoinColumns = @JoinColumn( name = "keyword_id" ) )
	private List<Keyword> keywords = new ArrayList<Keyword>();

	@ManyToMany( cascade = CascadeType.ALL, fetch = FetchType.LAZY )
	@JoinTable( name = "publication_topic", joinColumns = @JoinColumn( name = "publication_id" ), inverseJoinColumns = @JoinColumn( name = "publication_topic" ) )
	private List<Topic> topics = new ArrayList<Topic>();
	
	@ManyToOne( cascade = CascadeType.ALL, fetch = FetchType.LAZY )
	@JoinColumn( name = "venue_id" )
	private Venue venue;
	
	@ManyToMany( cascade = CascadeType.ALL, fetch = FetchType.LAZY )
	@JoinTable( name = "publication_author", joinColumns = @JoinColumn( name = "publication_id" ), inverseJoinColumns = @JoinColumn( name = "author_id" ) )
	private List<Author> authors;

	@ManyToMany( cascade = CascadeType.ALL, fetch = FetchType.LAZY )
	@JoinTable( name = "publication_cites", joinColumns = @JoinColumn( name = "publication_id" ), inverseJoinColumns = @JoinColumn( name = "publication_cites_id" ) )
	private List<Publication> publicationCitess = new ArrayList<Publication>();

	@ManyToMany( cascade = CascadeType.ALL, fetch = FetchType.LAZY )
	@JoinTable( name = "publication_citedby", joinColumns = @JoinColumn( name = "publication_id" ), inverseJoinColumns = @JoinColumn( name = "publication_citedby_id" ) )
	private List<Publication> publicationCitedBys = new ArrayList<Publication>();

	public String getTitle()
	{
		return title;
	}

	public void setTitle( String title )
	{
		this.title = title;
	}

	public String getAbstractOriginal()
	{
		return abstractOriginal;
	}

	public void setAbstractOriginal( String abstractOriginal )
	{
		this.abstractOriginal = abstractOriginal;
	}

	public String getAbstractTokenized()
	{
		return abstractTokenized;
	}

	public void setAbstractTokenized( String abstractTokenized )
	{
		this.abstractTokenized = abstractTokenized;
	}

	public String getFulltextOriginal()
	{
		return fulltextOriginal;
	}

	public void setFulltextOriginal( String fulltextOriginal )
	{
		this.fulltextOriginal = fulltextOriginal;
	}

	public String getFulltextTokenized()
	{
		return fulltextTokenized;
	}

	public void setFulltextTokenized( String fulltextTokenized )
	{
		this.fulltextTokenized = fulltextTokenized;
	}

	public Date getCreatedAt()
	{
		return createdAt;
	}

	public void setCreatedAt( Date createdAt )
	{
		this.createdAt = createdAt;
	}

	public List<Keyword> getKeywords()
	{
		return keywords;
	}

	public void setKeywords( List<Keyword> keywords )
	{
		this.keywords = keywords;
	}

	public Publication addKeyword( final Keyword keyword )
	{
		if ( this.keywords == null )
			this.keywords = new ArrayList<Keyword>();

		keywords.add( keyword );
		return this;
	}

	public List<Topic> getTopics()
	{
		return topics;
	}

	public void setTopics( List<Topic> topics )
	{
		this.topics = topics;
	}

	public Publication addTopic( final Topic topic )
	{
		if ( this.topics == null )
			this.topics = new ArrayList<Topic>();

		topics.add( topic );
		return this;
	}

	public Venue getVenue()
	{
		return venue;
	}

	public void setVenue( Venue venue )
	{
		this.venue = venue;
	}

	public List<Author> getAuthors()
	{
		return authors;
	}

	public void setAuthors( List<Author> authors )
	{
		this.authors = authors;
	}

	public Publication addAuthor( final Author author )
	{
		if ( this.authors == null )
			this.authors = new ArrayList<Author>();

		authors.add( author );
		return this;
	}

	public List<Publication> getPublicationCitess()
	{
		return publicationCitess;
	}

	public void setPublicationCitess( List<Publication> publicationCitess )
	{
		this.publicationCitess = publicationCitess;
	}

	public Publication addPublicationCites( final Publication publicationCites )
	{
		if ( this.publicationCitess == null )
			this.publicationCitess = new ArrayList<Publication>();

		publicationCitess.add( publicationCites );
		return this;
	}

	public List<Publication> getPublicationCitedBys()
	{
		return publicationCitedBys;
	}

	public void setPublicationCitedBys( List<Publication> publicationCitedBys )
	{
		this.publicationCitedBys = publicationCitedBys;
	}

	public Publication addPublicationCiteBy( final Publication publicationCiteBy )
	{
		if ( this.publicationCitedBys == null )
			this.publicationCitedBys = new ArrayList<Publication>();

		publicationCitedBys.add( publicationCiteBy );
		return this;
	}

}

