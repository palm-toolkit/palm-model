package de.rwth.i9.palm.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Lob;
import javax.persistence.Table;

import org.apache.lucene.analysis.core.LowerCaseFilterFactory;
import org.apache.lucene.analysis.snowball.SnowballPorterFilterFactory;
import org.apache.lucene.analysis.standard.StandardTokenizerFactory;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;
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
@Table( name = "publication_old" )
@JsonIgnoreProperties( { } )
@Indexed
@AnalyzerDef( name = "customanalyzer", tokenizer = @TokenizerDef( factory = StandardTokenizerFactory.class ), filters = { @TokenFilterDef( factory = LowerCaseFilterFactory.class ), @TokenFilterDef( factory = SnowballPorterFilterFactory.class, params = { @Parameter( name = "language", value = "English" ) } ) } )
public class PublicationOld extends PersistableResource
{

	@Column
	private String year;

	@Column
	private String title;
	
	/* comma separated author list */
	@Column
	private String authors;

	@Column
	@Lob
	@Field( index = Index.YES, termVector = TermVector.WITH_POSITION_OFFSETS, store = Store.YES )
	private String abstractText;

	@Column
	private String keywords;
	
	@Column
	private int citations;

	@Column
	private String tags;

	public String getYear()
	{
		return year;
	}

	public void setYear( String year )
	{
		this.year = year;
	}

	public String getTitle()
	{
		return title;
	}

	public void setTitle( String title )
	{
		this.title = title;
	}

	public String getAuthors()
	{
		return authors;
	}

	public void setAuthors( String authors )
	{
		this.authors = authors;
	}

	public String getAbstractText()
	{
		return abstractText;
	}

	public void setAbstractText( String abstractText )
	{
		this.abstractText = abstractText;
	}

	public String getKeywords()
	{
		return keywords;
	}

	public void setKeywords( String keywords )
	{
		this.keywords = keywords;
	}

	public int getCitations()
	{
		return citations;
	}

	public void setCitations( int citations )
	{
		this.citations = citations;
	}

	public String getTags()
	{
		return tags;
	}

	public void setTags( String tags )
	{
		this.tags = tags;
	}

}

