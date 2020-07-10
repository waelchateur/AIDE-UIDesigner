package com.bielsoft.uidesigner.widget;

import android.content.Context;
import android.view.Gravity;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

public class WidgetButton extends Widget
{
    private TextView textView;

    public WidgetButton(Context context)
    {
        super(context);

        textView = new TextView(context);
        textView.setBackground(new Button(context).getBackground());
        textView.setTextColor(new Button(context).getTextColors());
        textView.setGravity(Gravity.CENTER);
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
