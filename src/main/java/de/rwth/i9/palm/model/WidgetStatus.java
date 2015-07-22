package de.rwth.i9.palm.model;

/**
 * <b>Documentation:</b><br/>
 *	{@code NONACTIVE }<br/>
 *		The widget is obsolete / under development, thus it is not choosable by the users.<br/>
 *	{@code ACTIVE}<br/>
 *		The widget is ready and working properly, thus it is choosable by the users.<br/>
 *	{@code DEFAULT}<br/>
 *		The widget is ACTIVE and set as default widget for conference, researcher 
 *		and publication pages (even without user login).<br/>
 *	{@code UNAPPROVED}<br/>
 *		The widget is new and have not yet been approved by the administrations.<br/>
 *
 * @author sigit
 */
public enum WidgetStatus
{
	DEFAULT, ACTIVE, NONACTIVE, UNAPPROVED
}
