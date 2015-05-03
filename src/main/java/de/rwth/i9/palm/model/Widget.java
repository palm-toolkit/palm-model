package de.rwth.i9.palm.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Lob;
import javax.persistence.Table;

import de.rwth.i9.palm.persistence.PersistableResource;

@Entity
@Table( name = "widget" )
public class Widget extends PersistableResource
{
	@Enumerated( EnumType.STRING )
	@Column( length = 16 )
	private WidgetType widgetType;

	@Column
	private String title;

	@Column
	private String source;

	@Column
	@Lob
	private String sourceUrl;

	@Column
	@Lob
	private String information;

	@Column( columnDefinition = "bit default 1" )
	private boolean closeEnabled = true;

	@Column( columnDefinition = "bit default 1" )
	private boolean minimizeEnabled = true;

	@Column( columnDefinition = "bit default 1" )
	private boolean maximizeEnabled = true;

	@Enumerated( EnumType.STRING )
	@Column( length = 8 )
	private WidgetWidth widgetWidth;

	// getter / setter

	public WidgetType getWidgetType()
	{
		return widgetType;
	}

	public void setWidgetType( WidgetType widgetType )
	{
		this.widgetType = widgetType;
	}

	public String getTitle()
	{
		return title;
	}

	public void setTitle( String title )
	{
		this.title = title;
	}

	public String getSource()
	{
		return source;
	}

	public void setSource( String source )
	{
		this.source = source;
	}

	public String getSourceUrl()
	{
		return sourceUrl;
	}

	public void setSourceUrl( String sourceUrl )
	{
		this.sourceUrl = sourceUrl;
	}

	public String getInformation()
	{
		return information;
	}

	public void setInformation( String information )
	{
		this.information = information;
	}

	public boolean isCloseEnabled()
	{
		return closeEnabled;
	}

	public void setCloseEnabled( boolean closeEnabled )
	{
		this.closeEnabled = closeEnabled;
	}

	public boolean isMinimizeEnabled()
	{
		return minimizeEnabled;
	}

	public void setMinimizeEnabled( boolean minimizeEnabled )
	{
		this.minimizeEnabled = minimizeEnabled;
	}

	public boolean isMaximizeEnabled()
	{
		return maximizeEnabled;
	}

	public void setMaximizeEnabled( boolean maximizeEnabled )
	{
		this.maximizeEnabled = maximizeEnabled;
	}

	public WidgetWidth getWidgetWidth()
	{
		return widgetWidth;
	}

	public void setWidgetWidth( WidgetWidth widgetWidth )
	{
		this.widgetWidth = widgetWidth;
	}

}
