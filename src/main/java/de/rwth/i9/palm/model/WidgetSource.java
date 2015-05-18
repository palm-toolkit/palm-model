package de.rwth.i9.palm.model;

/**
 * 
 * @author sigit
 *	INCLUDE 
 *		The widget content is from internal template file, appended with Freemarker <#include [FILE_NAME]>
 *	AJAX
 *		The widget content is from internal source, but requested using ajax.
 *	EXTERNAL
 *		The widget content is from external source, displayed using iFrame container
 */
public enum WidgetSource
{
	INCLUDE, AJAX, EXTERNAL
}
