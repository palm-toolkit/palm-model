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
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.apache.lucene.analysis.core.LowerCaseFilterFactory;
import org.apache.lucene.analysis.standard.StandardTokenizerFactory;
import org.hibernate.search.annotations.Analyze;
import org.hibernate.search.annotations.Analyzer;
import org.hibernate.search.annotations.AnalyzerDef;
import org.hibernate.search.annotations.Boost;
import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.Index;
import org.hibernate.search.annotations.Indexed;
import org.hibernate.search.annotations.IndexedEmbedded;
import org.hibernate.search.annotations.Store;
import org.hibernate.search.annotations.TokenFilterDef;
import org.hibernate.search.annotations.TokenizerDef;

import de.rwth.i9.palm.persistence.PersistableResource;

@Entity
@Table( name = "author" )
@Indexed
@AnalyzerDef( 
		name = "authoranalyzer", 
		tokenizer = @TokenizerDef( factory = StandardTokenizerFactory.class ), 
		filters = { 
			@TokenFilterDef( factory = LowerCaseFilterFactory.class ) 
			} 
		)
public class Author extends PersistableResource
{
	/* the full name of the author, most commonly used */
	@Column
	@Field( index = Index.YES, analyze = Analyze.YES, store = Store.YES )
	@Analyzer( definition = "authoranalyzer" )
	private String name;

	@Column
	@Field( index = Index.YES, analyze = Analyze.YES, store = Store.YES )
	@Analyzer( definition = "authoranalyzer" )
	private String firstName;

	@Column
	@Field( index = Index.YES, analyze = Analyze.YES, store = Store.YES )
	@Analyzer( definition = "authoranalyzer" )
	@Boost( 3.0f )
	private String lastName;

	@Column
	private String otherDetail;

	@Column
	private String department;

	@Column
	private String email;

	@Column
	private String photoUrl;

	@Column( length = 24 )
	private String googleScholarId;

	// relations

	@ManyToOne( cascade = CascadeType.ALL, fetch = FetchType.LAZY )
	@JoinColumn( name = "location_id" )
	private Location based_near;

	@ManyToMany( mappedBy = "coAuthors", cascade = CascadeType.ALL )
	private List<Publication> publications;

	@ManyToMany( cascade = CascadeType.ALL, fetch = FetchType.LAZY )
	@JoinTable( name = "author_coauthor", joinColumns = @JoinColumn( name = "author_id" ), inverseJoinColumns = @JoinColumn( name = "author_coauthor_id" ) )
	private List<Author> coAuthors;

	/* other name of the author */
	@OneToMany( cascade = CascadeType.ALL )
	@JoinColumn( name = "author_id" )
	private List<AuthorAlias> aliases;

	/* few authors work for several institution */
	@ManyToMany( cascade = CascadeType.ALL, fetch = FetchType.LAZY )
	@JoinTable( name = "author_institution", joinColumns = @JoinColumn( name = "author_id" ), inverseJoinColumns = @JoinColumn( name = "institution_id" ) )
	@IndexedEmbedded
	private List<Institution> institutions;

	@ManyToMany( cascade = CascadeType.ALL, fetch = FetchType.LAZY )
	@JoinTable( name = "author_interest", joinColumns = @JoinColumn( name = "author_id" ), inverseJoinColumns = @JoinColumn( name = "topic_id" ) )
	private List<PublicationTopic> publicationTopics;

	@OneToMany( cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "author" )
	private List<AuthorSource> authorSources;

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

	public Author addInstitution( final Institution institution )
	{
		if ( this.institutions == null )
			this.institutions = new ArrayList<Institution>();

		institutions.add( institution );
		return this;
	}

	public List<Institution> getInstitutions()
	{
		return institutions;
	}

	public void setInstitutions( List<Institution> institutions )
	{
		this.institutions = institutions;
	}

	public List<PublicationTopic> getTopics()
	{
		return publicationTopics;
	}

	public void setTopics( List<PublicationTopic> publicationTopics )
	{
		this.publicationTopics = publicationTopics;
	}

	public Author addTopic( PublicationTopic publicationTopic )
	{
		if ( this.publicationTopics == null )
			this.publicationTopics = new ArrayList<PublicationTopic>();

		this.publicationTopics.add( publicationTopic );
		return this;
	}

	public List<AuthorAlias> getAliases()
	{
		return aliases;
	}

	public void setAliases( List<AuthorAlias> aliases )
	{
		this.aliases = aliases;
	}

	public Author addAlias( final AuthorAlias aliasName )
	{
		if ( this.aliases == null )
			this.aliases = new ArrayList<AuthorAlias>();

		this.aliases.add( aliasName );

		return this;
	}

	public String getFirstName()
	{
		return firstName;
	}

	public void setFirstName( String firstName )
	{
		this.firstName = firstName;
	}

	public String getLastName()
	{
		return lastName;
	}

	public void setLastName( String lastName )
	{
		this.lastName = lastName;
	}

	public List<Author> getCoAuthors()
	{
		return coAuthors;
	}

	public void setCoAuthors( List<Author> coAuthors )
	{
		this.coAuthors = coAuthors;
	}

	public Author addCoAuthor( final Author coAuthor )
	{
		if ( this.coAuthors == null )
			this.coAuthors = new ArrayList<Author>();

		this.coAuthors.add( coAuthor );

		return this;
	}

	public String getDepartment()
	{
		return department;
	}

	public void setDepartment( String department )
	{
		this.department = department;
	}

	public List<Publication> getPublications()
	{
		return publications;
	}

	public void setPublications( List<Publication> publications )
	{
		this.publications = publications;
	}

	public Author addPublication( final Publication publication )
	{
		if ( this.publications == null )
			this.publications = new ArrayList<Publication>();

		this.publications.add( publication );

		return this;
	}

	public Location getBased_near()
	{
		return based_near;
	}

	public void setBased_near( Location based_near )
	{
		this.based_near = based_near;
	}

	public String getGoogleScholarId()
	{
		return googleScholarId;
	}

	public void setGoogleScholarId( String googleScholarId )
	{
		this.googleScholarId = googleScholarId;
	}

	public List<PublicationTopic> getPublicationTopics()
	{
		return publicationTopics;
	}

	public void setPublicationTopics( List<PublicationTopic> publicationTopics )
	{
		this.publicationTopics = publicationTopics;
	}

	public String getOtherDetail()
	{
		return otherDetail;
	}

	public void setOtherDetail( String otherDetail )
	{
		this.otherDetail = otherDetail;
	}

	public String getPhotoUrl()
	{
		return photoUrl;
	}

	public void setPhotoUrl( String photoUrl )
	{
		this.photoUrl = photoUrl;
	}

	public List<AuthorSource> getAuthorSources()
	{
		return authorSources;
	}

	public void setAuthorSources( List<AuthorSource> authorSources )
	{
		this.authorSources = authorSources;
	}

	public Author addAuthorSource( AuthorSource auhtorSource )
	{
		if ( this.authorSources == null )
			this.authorSources = new ArrayList<AuthorSource>();
		this.authorSources.add( auhtorSource );
		return this;
	}

}
