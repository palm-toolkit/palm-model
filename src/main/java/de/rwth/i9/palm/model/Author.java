package de.rwth.i9.palm.model;

import java.text.Normalizer;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;
import java.util.regex.Pattern;

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
	@Column( length = 100 )
	@Field( index = Index.YES, analyze = Analyze.YES, store = Store.YES )
	@Analyzer( definition = "authoranalyzer" )
	private String name;

	@Column( length = 70 )
	@Field( index = Index.YES, analyze = Analyze.YES, store = Store.YES )
	@Analyzer( definition = "authoranalyzer" )
	private String firstName;

	@Column( length = 30 )
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
	
	@Column
	private java.sql.Timestamp requestDate;
	
	@Column(columnDefinition = "int default 0")
	private int citedBy;

	// relations

	/* other name of the author */
	@OneToMany( cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "author", orphanRemoval = true )
	private Set<AuthorAlias> aliases;

	@ManyToOne( cascade = CascadeType.ALL, fetch = FetchType.LAZY )
	@JoinColumn( name = "location_id" )
	private Location based_near;

	@ManyToMany( mappedBy = "coAuthors" )
	private Set<Publication> publications;

	@OneToMany( cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "author" )
	private Set<InterestAuthor> interestAuthors;

	/* few authors probably work for several institutions */
	@ManyToMany( cascade = CascadeType.ALL, fetch = FetchType.LAZY )
	@JoinTable( name = "author_institution", joinColumns = @JoinColumn( name = "author_id" ) , inverseJoinColumns = @JoinColumn( name = "institution_id" ) )
	@IndexedEmbedded
	private Set<Institution> institutions;

	@OneToMany( cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "author", orphanRemoval = true )
	private Set<AuthorSource> authorSources;

	@OneToMany( cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "author", orphanRemoval = true )
	private Set<AuthorInterestProfile> authorInterestProfiles;

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

	public void setCompleteName( String name )
	{
		this.setName( name );
		String[] splitName = name.split( " " );
		this.setLastName( splitName[splitName.length - 1] );

		String firstName = name.substring( 0, name.length() - lastName.length() ).trim();
		if ( !firstName.equals( "" ) )
			this.setFirstName( firstName );
	}

	public void setEmail( String email )
	{
		this.email = email;
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

	public String getDepartment()
	{
		return department;
	}

	public void setDepartment( String department )
	{
		this.department = department;
	}

	public Set<Publication> getPublications()
	{
		return publications;
	}

	public void setPublications( Set<Publication> publications )
	{
		this.publications = publications;
	}

	public Author addPublication( final Publication publication )
	{
		if ( this.publications == null )
			this.publications = new LinkedHashSet<Publication>();

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

	public Set<AuthorSource> getAuthorSources()
	{
		return authorSources;
	}

	public void setAuthorSources( Set<AuthorSource> authorSources )
	{
		if ( this.authorSources == null )
			this.authorSources = new LinkedHashSet<AuthorSource>();
		this.authorSources.clear();
		this.authorSources.addAll( authorSources );
	}

	public Author addAuthorSource( AuthorSource auhtorSource )
	{
		if ( this.authorSources == null )
		{
			this.authorSources = new LinkedHashSet<AuthorSource>();
			this.authorSources.add( auhtorSource );
		}
		else
		{
			boolean updateSourceUrl = false;
			for ( AuthorSource eachAuhorSource : this.authorSources )
			{
				if ( eachAuhorSource.getSourceType().equals( auhtorSource.getSourceType() ) )
				{
					eachAuhorSource.setSourceUrl( auhtorSource.getSourceUrl() );
					updateSourceUrl = true;
				}
			}
			if ( !updateSourceUrl )
				this.authorSources.add( auhtorSource );
		}

		return this;
	}

	public java.sql.Timestamp getRequestDate()
	{
		return requestDate;
	}

	public void setRequestDate( java.sql.Timestamp requestDate )
	{
		this.requestDate = requestDate;
	}

	public Set<Institution> getInstitutions()
	{
		return institutions;
	}

	public void setInstitutions( Set<Institution> institutions )
	{
		this.institutions = institutions;
	}

	public Author addInstitution( Institution institution )
	{
		if ( this.institutions == null )
			this.institutions = new HashSet<Institution>();

		this.institutions.add( institution );

		return this;
	}

	public int getCitedBy()
	{
		return citedBy;
	}

	public void setCitedBy( int citedBy )
	{
		this.citedBy = citedBy;
	}

	public Set<AuthorAlias> getAliases()
	{
		return aliases;
	}

	public void setAliases( Set<AuthorAlias> aliases )
	{
		this.aliases = aliases;
	}
	
	public Author addAlias( AuthorAlias authorAlias )
	{
		if ( this.aliases == null )
			this.aliases = new LinkedHashSet<AuthorAlias>();

		// check if alias already exist
		else
		{
			for ( AuthorAlias eachAuthorAlias : this.aliases )
			{
				if ( eachAuthorAlias.getFirstName().equals( authorAlias.getFirstName() ) )
					return this;
			}
		}

		// new or not duplicated, then added to hashset
		this.aliases.add( authorAlias );

		return this;
	}

	public Set<AuthorInterestProfile> getAuthorInterestProfiles()
	{
		return authorInterestProfiles;
	}

	public void setAuthorInterestProfiles( Set<AuthorInterestProfile> authorInterestProfiles )
	{
		this.authorInterestProfiles = authorInterestProfiles;
	}

	public Author addAuthorInterestProfiles( AuthorInterestProfile authorInterestProfile )
	{
		if ( this.authorInterestProfiles == null )
			this.authorInterestProfiles = new LinkedHashSet<AuthorInterestProfile>();

		this.authorInterestProfiles.add( authorInterestProfile );

		return this;
	}

	public Set<InterestAuthor> getInterestAuthors()
	{
		return interestAuthors;
	}

	public void setInterestAuthors( Set<InterestAuthor> interestAuthors )
	{
		this.interestAuthors = interestAuthors;
	}

	public Author addInterestAuthor( InterestAuthor interestAuthor )
	{
		if ( this.interestAuthors == null )
			this.interestAuthors = new HashSet<InterestAuthor>();

		this.interestAuthors.add( interestAuthor );

		return this;
	}

	public boolean hasCoAuthorWith( Publication publication, Author coAuthor )
	{
		if ( this.publications.contains( publication ) )
		{
			for ( Publication pub : this.publications )
			{
				if ( pub.equals( publication ) )
				{
					if ( pub.getCoAuthors().contains( coAuthor ) )
						return true;
					break;
				}
			}
		}
		return false;
	}

	public boolean isAliasNameFromFirstName( String[] firstNameSplit )
	{
		if ( firstNameSplit == null || firstNameSplit.length == 0 )
			return false;

		String[] coAuthorDbFirstNameSplit = this.getFirstName().split( " " );
		int maxIndex = ( coAuthorDbFirstNameSplit.length > firstNameSplit.length ) ? firstNameSplit.length : coAuthorDbFirstNameSplit.length;
		boolean firstWordMatch = false;
		boolean firstWordStartWithMatch = false;
		for ( int i = 0; i < maxIndex; i++ )
		{
			if ( i == 0 )
			{
				if ( coAuthorDbFirstNameSplit[0].startsWith( firstNameSplit[0] ) && firstNameSplit[0].startsWith( coAuthorDbFirstNameSplit[0] ) )
					firstWordMatch = true;
				if ( coAuthorDbFirstNameSplit[0].startsWith( firstNameSplit[0] ) || firstNameSplit[0].startsWith( coAuthorDbFirstNameSplit[0] ) )
					firstWordStartWithMatch = true;
			}
			// TODO: for now only check first word/letter
			break;
		}
		if ( firstWordMatch || firstWordStartWithMatch )
			return true;

		return false;
	}

	public void setPossibleNames( String name )
	{
		// check name length after normalization to ASCII
		String nameAscii = name.replaceAll( "[^a-zA-Z ]", "" );
		
		if ( nameAscii.length() == name.length() )
		{
			this.setCompleteName( name );
		}
		else
		{

			@SuppressWarnings( "serial" )
			Map<Character, String> LIGATURES = new HashMap<Character, String>()
			{
				{
					put( 'ä', "ae" );
					put( 'ü', "ue" );
					put( 'ö', "oe" );
					put( 'ß', "ss" );
					put( 'Æ', "AE" );
					put( 'æ', "ae" );
					put( 'œ', "oe" );
					put( 'þ', "th" );
					put( 'ĳ', "ij" );
					put( 'ð', "dh" );
					put( 'Æ', "AE" );
					put( 'Œ', "OE" );
					put( 'Þ', "TH" );
					put( 'Ð', "DH" );
					put( 'Ĳ', "IJ" );
				}
			};

			// name combination 1
			StringBuilder sb = new StringBuilder();
			for ( int i = 0; i < name.length(); i++ )
			{
				char c = name.charAt( i );
				String l = LIGATURES.get( c );
				if ( l != null )
				{
					sb.append( l );
				}
				else if ( c < 0xc0 )
				{
					sb.append( c ); // ASCII and C1 control codes
				}
				else
				{
					// anything else, including diacritics
					l = Normalizer.normalize( Character.toString( c ), Normalizer.Form.NFKD ).replaceAll( "[\\p{InCombiningDiacriticalMarks}]+", "" );
					sb.append( l );
				}
			}
			String aliasName = sb.toString();

			// name combination 2
			String nfdNormalizedString = Normalizer.normalize( name, Normalizer.Form.NFD );
			Pattern pattern = Pattern.compile( "\\p{InCombiningDiacriticalMarks}+" );
			String mainName = pattern.matcher( nfdNormalizedString ).replaceAll( "" );

			this.setCompleteName( mainName.replaceAll( "[^a-zA-Z ]", "" ) );

			AuthorAlias authorAlias1 = new AuthorAlias();
			authorAlias1.setCompleteName( name );
			authorAlias1.setAuthor( this );
			this.addAlias( authorAlias1 );

			if ( !mainName.equals( aliasName ) )
			{
				AuthorAlias authorAlias2 = new AuthorAlias();
				authorAlias2.setCompleteName( aliasName );
				authorAlias2.setAuthor( this );
				this.addAlias( authorAlias2 );
			}

		}
	}

}
