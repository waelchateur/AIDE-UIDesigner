package com.bielsoft.uidesigner.widget;

import android.content.Context;
import android.graphics.Color;
import android.widget.LinearLayout;
import com.bielsoft.uidesigner.R;
import com.bielsoft.uidesigner.item.WidgetProperty;
import java.util.List;

public class Widget extends LinearLayout
{
    private String widgetId;
    private String widgetType;
    private List<WidgetProperty> properties;
    
    public Widget(Context context)
    {
        super(context);
    }

    public void setWidgetId(String widgetId)
    {
        this.widgetId = widgetId;
    }

    public String getWidgetId()
    {
        return widgetId;
    }

    public void setWidgetType(String widgetType)
    {
        this.widgetType = widgetType;
    }

    public String getWidgetType()
    {
        return widgetType;
    }

    public void setProperties(List<WidgetProperty> properties)
    {
        this.properties = properties;
    }

    public List<WidgetProperty> getProperties()
    {
        return properties;
    }
    
    public void select()
    {
        setBackgroundColor(getResources().getColor(R.color.colorWidgetSelected));
    }

    public void unselect()
    {
        setBackgroundColor(Color.TRANSPARENT);
    }
}
