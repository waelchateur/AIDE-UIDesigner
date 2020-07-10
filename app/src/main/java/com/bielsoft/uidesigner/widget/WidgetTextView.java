package com.bielsoft.uidesigner.widget;

import android.content.Context;
import android.view.ViewGroup;
import android.widget.TextView;

public class WidgetTextView extends Widget
{
    private TextView textView;

    public WidgetTextView(Context context)
    {
        super(context);

        textView = new TextView(context);
        textView.setTextSize(10);
        addView(textView);
    }

    public TextView getTextView()
    {
        return textView;
    }

    @Override
    public void setLayoutParams(ViewGroup.LayoutParams params)
    {
        super.setLayoutParams(params);
        textView.setLayoutParams(params);
    }
}
