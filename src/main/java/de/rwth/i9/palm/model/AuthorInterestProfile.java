package de.rwth.i9.palm.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import de.rwth.i9.palm.persistence.PersistableResource;

@Entity
@Table( name = "author_interest_profile" )
public class AuthorInterestProfile extends PersistableResource
{
	@Column( unique = true, nullable = false )
	private String name;

	@Column
	Date created;

	@Column
	@Lob
	private String description;

	// relation
	@ManyToOne
	@JoinColumn( name = "author_id" )
	private Author author;

	@OneToMany( cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "authorInterestProfile", orphanRemoval = true )
	List<AuthorInterest> authorInterests;

	// getter & setter



	public AuthorInterestProfile addAuthorInterest( AuthorInterest authorInterest )
	{
		if ( this.authorInterests == null )
			authorInterests = new ArrayList<AuthorInterest>();

		authorInterests.add( authorInterest );
		return this;
	}
}
