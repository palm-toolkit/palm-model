package de.rwth.i9.palm.helper.comparator;

import java.util.Comparator;

import de.rwth.i9.palm.model.PublicationAuthor;

public class PublicationAuthorByPositionComparator implements Comparator<PublicationAuthor>
{

	@Override
	public int compare( final PublicationAuthor publicationAuthor1, final PublicationAuthor publicationAuthor2 )
	{
		if ( publicationAuthor1 == null && publicationAuthor2 == null )
			return 0;

		if ( publicationAuthor1 == null )
			return 1;

		if ( publicationAuthor2 == null )
			return -1;

		int noCitation1 = publicationAuthor1.getPosition();
		int noCitation2 = publicationAuthor2.getPosition();

		if ( noCitation1 < noCitation2 )
			return -1;

		if ( noCitation1 > noCitation2 )
			return 1;

		return 0;
	}

}