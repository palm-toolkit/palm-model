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

	@Column( name = "position_" )
	private int position;

	@Column
	private String title;

	@Column
	private String widgetGroup;

	@Enumerated( EnumType.STRING )
	@Column( length = 16 )
	private WidgetStatus widgetStatus;

	@Enumerated( EnumType.STRING )
	@Column( length = 16 )
	private WidgetSource widgetSource;

	@Column
	@Lob
	private String sourcePath;

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

	public String getSourcePath()
	{
		return this.sourcePath;
	}

	public void setSourcePath( String sourcePath )
	{
		this.sourcePath = sourcePath;
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

	public String getWidgetGroup()
	{
		return widgetGroup;
	}

	public void setWidgetGroup( String widgetGroup )
	{
		this.widgetGroup = widgetGroup;
	}

	public int getPosition()
	{
		return position;
	}

	public void setPosition( int position )
	{
		this.position = position;
	}

	public WidgetStatus getWidgetStatus()
	{
		return widgetStatus;
	}

	public void setWidgetStatus( WidgetStatus widgetStatus )
	{
		this.widgetStatus = widgetStatus;
	}

	public WidgetSource getWidgetSource()
	{
		return widgetSource;
	}

	public void setWidgetSource( WidgetSource widgetSource )
	{
		this.widgetSource = widgetSource;
	}

}
