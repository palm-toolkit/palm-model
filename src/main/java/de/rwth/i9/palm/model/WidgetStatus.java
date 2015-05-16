package de.rwth.i9.palm.model;

/**
 * 
 * @author sigit
 *	NONACTIVE 
 *		The widget is obsolete / under development, thus it is not choosable by the users.
 *	ACTIVE
 *		The widget is ready and working properly, thus it is choosable by the users.
 *	DEFAULT
 *		The widget is ACTIVE and set as default widget for conference, researcher 
 *		and publication pages (even without user login).
 *	UNAPPROVED
 *		The widget is new and have not yet been approved by the administrations.
 */
public enum WidgetStatus
{
	DEFAULT, ACTIVE, NONACTIVE, UNAPPROVED
}
