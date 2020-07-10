package com.bielsoft.uidesigner.widget;

import android.content.Context;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import com.bielsoft.uidesigner.R;
import com.bielsoft.uidesigner.util.DesigerUtil;

public class WidgetLinearLayout extends Widget
{
    private CustomLayout linear;

    public WidgetLinearLayout(Context context)
    {
        super(context);

        linear = new CustomLayout(context);
        linear.setOrientation(LinearLayout.VERTICAL);
        linear.setBackgroundDrawable(getResources().getDrawable(R.drawable.background_designer));
        addView(linear);
    }

    public CustomLayout getLinear()
    {
        return linear;
    }

    @Override
    public void setOrientation(int orientation)
    {
        super.setOrientation(orientation);
        linear.setOrientation(orientation);
    }

    @Override
    public void setLayoutParams(ViewGroup.LayoutParams params)
    {
        super.setLayoutParams(params);
        linear.setLayoutParams(params);
    }

    private class CustomLayout extends Widget
    {
        public CustomLayout(Context context)
        {
            super(context);
        }

        @Override
        protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec)
        {
            int widthSize = MeasureSpec.getSize(widthMeasureSpec);
            int widthMode = MeasureSpec.getMode(widthMeasureSpec);

            if (widthMode == MeasureSpec.AT_MOST ||
                widthMode == MeasureSpec.UNSPECIFIED)
            {
                widthSize = DesigerUtil.dip(100);
            }

            int heightSize = MeasureSpec.getSize(heightMeasureSpec);
            int heightMode = MeasureSpec.getMode(heightMeasureSpec);

            if (heightMode == MeasureSpec.AT_MOST ||
                heightMode == MeasureSpec.UNSPECIFIED)
            {
                heightSize = DesigerUtil.dip(100);
            }

            setMeasuredDimension(widthSize, heightSize);
        }
    }
}
