package com.bielsoft.uidesigner.widget;

import android.content.Context;
import android.view.ViewGroup;
import android.widget.ImageView;
import com.bielsoft.uidesigner.R;

public class WidgetImageView extends Widget
{
    private ImageView imageView;

    public WidgetImageView(Context context)
    {
        super(context);

        imageView = new ImageView(context);
        imageView.setImageResource(android.R.drawable.ic_delete);
        addView(imageView);
    }

    public ImageView getImageView()
    {
        return imageView;
    }

    @Override
    public void setLayoutParams(ViewGroup.LayoutParams params)
    {
        super.setLayoutParams(params);
        imageView.setLayoutParams(params);
    }
}
