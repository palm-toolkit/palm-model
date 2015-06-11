package de.rwth.i9.palm.model;

import java.util.ArrayList;
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
import javax.persistence.OneToMany;
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
@AnalyzerDef( 
		name = "customanalyzer", 
		tokenizer = @TokenizerDef( factory = StandardTokenizerFactory.class ), 
		filters = { 
			@TokenFilterDef( factory = LowerCaseFilterFactory.class ), 
			@TokenFilterDef( factory = SnowballPorterFilterFactory.class, params = { @Parameter( name = "language", value = "English" ) } ) 
			} 
		)
public class Publication extends PersistableResource
{
	@Column( unique = true, nullable = false )
	@Field( index = Index.YES, analyze = Analyze.YES, store = Store.YES )
	private String title;
	
	/* comma separated author list */
	@Column
	private String authorString;

	@Column
	@Lob
	@Field( index = Index.YES, termVector = TermVector.WITH_POSITION_OFFSETS, store = Store.YES )
	private String abstractText;

	@Column
	@Lob
	@Field( index = Index.YES, termVector = TermVector.WITH_POSITION_OFFSETS, store = Store.YES )
	@Analyzer( definition = "customanalyzer" )
	private String abstractTokenized;

	@Column
	@Lob
	@Field( index = Index.YES, termVector = TermVector.WITH_POSITION_OFFSETS, store = Store.YES )
	private String contentText;

	@Column
	@Lob
	@Field( index = Index.YES, termVector = TermVector.WITH_POSITION_OFFSETS, store = Store.YES )
	@Analyzer( definition = "customanalyzer" )
	private String contentTextTokenized;

	@Column
	@Lob
	@Field( index = Index.YES, termVector = TermVector.WITH_POSITION_OFFSETS, store = Store.YES )
	private String citationText;

	@Column( columnDefinition = "int default 0" )
	private int numberOfCitation;

	@Column( columnDefinition = "varchar(15) default 'english'" )
	private String language;

	// relations
	@ManyToMany( cascade = CascadeType.ALL, fetch = FetchType.LAZY )
	@JoinTable( name = "publication_keyword", joinColumns = @JoinColumn( name = "publication_id" ), inverseJoinColumns = @JoinColumn( name = "keyword_id" ) )
	private List<Subject> subjects;

	@OneToMany( cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "publication" )
	private List<PublicationTopic> publicationTopics;
	
	@ManyToOne( cascade = CascadeType.ALL, fetch = FetchType.LAZY )
	@JoinColumn( name = "conference_id" )
	private Conference conference;
	
	@ManyToMany( cascade = CascadeType.ALL, fetch = FetchType.LAZY )
	@JoinTable( name = "publication_author", joinColumns = @JoinColumn( name = "publication_id" ), inverseJoinColumns = @JoinColumn( name = "author_id" ) )
	private List<Author> coAuthors;

	@ManyToMany( cascade = CascadeType.ALL, fetch = FetchType.LAZY )
	@JoinTable( name = "publication_cites", joinColumns = @JoinColumn( name = "publication_id" ), inverseJoinColumns = @JoinColumn( name = "publication_cites_id" ) )
	private List<Publication> publicationCitess;

	@ManyToMany( cascade = CascadeType.ALL, fetch = FetchType.LAZY )
	@JoinTable( name = "publication_citedby", joinColumns = @JoinColumn( name = "publication_id" ), inverseJoinColumns = @JoinColumn( name = "publication_citedby_id" ) )
	private List<Publication> publicationCitedBys;

	@OneToMany( cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "publication" )
	private List<Reference> references;

	@OneToMany( cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "publication" )
	private List<PublicationHistory> publicationHistories;

	@OneToMany( cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "publication" )
	private List<PublicationSource> publicationSources;

	public Conference getConference()
	{
		return conference;
	}

	public void setConference( Conference conference )
	{
		this.conference = conference;
	}

	public List<Reference> getReferences()
	{
		return references;
	}

	public void setReferences( List<Reference> references )
	{
		this.references = references;
	}

	public Publication addReference( Reference reference )
	{
		if ( this.references == null )
			this.references = new ArrayList<Reference>();
		this.references.add( reference );

		return this;
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

	public String getAbstractTokenized()
	{
		return abstractTokenized;
	}

	public void setAbstractTokenized( String abstractTokenized )
	{
		this.abstractTokenized = abstractTokenized;
	}

	public String getContentText()
	{
		return contentText;
	}

	public void setContentText( String contentText )
	{
		this.contentText = contentText;
	}

	public String getContentTextTokenized()
	{
		return contentTextTokenized;
	}

	public void setContentTextTokenized( String contentTextTokenized )
	{
		this.contentTextTokenized = contentTextTokenized;
	}

	public List<Subject> getKeywords()
	{
		return subjects;
	}

	public void setKeywords( List<Subject> subjects )
	{
		this.subjects = subjects;
	}

	public Publication addKeyword( final Subject subject )
	{
		if ( this.subjects == null )
			this.subjects = new ArrayList<Subject>();

		subjects.add( subject );
		return this;
	}

	public List<PublicationTopic> getTopics()
	{
		return publicationTopics;
	}

	public void setTopics( List<PublicationTopic> publicationTopics )
	{
		this.publicationTopics = publicationTopics;
	}

	public Publication addTopic( final PublicationTopic publicationTopic )
	{
		if ( this.publicationTopics == null )
			this.publicationTopics = new ArrayList<PublicationTopic>();

		publicationTopics.add( publicationTopic );
		return this;
	}

	public Conference getVenue()
	{
		return conference;
	}

	public void setVenue( Conference conference )
	{
		this.conference = conference;
	}

	public List<Author> getCoAuthors()
	{
		return coAuthors;
	}

	public void setCoAuthors( List<Author> coAuthors )
	{
		this.coAuthors = coAuthors;
	}

	public Publication addCoAuthor( final Author author )
	{
		if ( this.coAuthors == null )
			this.coAuthors = new ArrayList<Author>();

		this.coAuthors.add( author );
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

		this.publicationCitess.add( publicationCites );
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

		this.publicationCitedBys.add( publicationCiteBy );
		return this;
	}

	public String getAuthorString()
	{
		return authorString;
	}

	public void setAuthorString( String authorString )
	{
		this.authorString = authorString;
	}

	public int getNumberOfCitation()
	{
		return numberOfCitation;
	}

	public void setNumberOfCitation( int numberOfCitation )
	{
		this.numberOfCitation = numberOfCitation;
	}

	public String getCitationText()
	{
		return citationText;
	}

	public void setCitationText( String citationText )
	{
		this.citationText = citationText;
	}

	public String getLanguage()
	{
		return language;
	}

	public void setLanguage( String language )
	{
		this.language = language;
	}

	public List<Subject> getSubjects()
	{
		return subjects;
	}

	public void setSubjects( List<Subject> subjects )
	{
		this.subjects = subjects;
	}

	public Publication adddSubject( Subject subject )
	{
		if ( this.subjects == null )
			this.subjects = new ArrayList<Subject>();

		this.subjects.add( subject );

		return this;
	}

	public List<PublicationHistory> getPublicationHistories()
	{
		return publicationHistories;
	}

	public void setPublicationHistories( List<PublicationHistory> publicationHistories )
	{
		this.publicationHistories = publicationHistories;
	}

	public Publication addPublicationHistory( PublicationHistory publicationHistory )
	{
		if ( this.publicationHistories == null )
			this.publicationHistories = new ArrayList<PublicationHistory>();
		this.publicationHistories.add( publicationHistory );
		return this;
	}

	public List<PublicationSource> getPublicationSources()
	{
		return publicationSources;
	}

	public void setPublicationSources( List<PublicationSource> publicationSources )
	{
		this.publicationSources = publicationSources;
	}

	public Publication addPublicationSource( PublicationSource publicationSource )
	{
		if ( this.publicationSources == null )
			this.publicationSources = new ArrayList<PublicationSource>();
		this.publicationSources.add( publicationSource );
		return this;
	}

}

