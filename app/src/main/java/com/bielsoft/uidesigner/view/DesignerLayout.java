package com.bielsoft.uidesigner.view;

import android.content.Context;
import android.graphics.Canvas;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.DragEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import com.bielsoft.uidesigner.DesignerApp;
import com.bielsoft.uidesigner.R;
import com.bielsoft.uidesigner.util.DesigerUtil;
import com.bielsoft.uidesigner.widget.Widget;
import com.bielsoft.uidesigner.widget.WidgetLinearLayout;

public class DesignerLayout extends FrameLayout
{
    protected boolean isEditMode = true;

    public static View view_location;
    public static int defaultIndex;
    public static int index;
    public static boolean addedInLayout;
    public static View mView;
    public static View mWidget;

    public DesignerLayout(Context context)
    {
        super(context);
        init(null);
    }

    public DesignerLayout(Context context, AttributeSet attrs)
    {
        super(context, attrs);
        init(attrs);
    }

    public DesignerLayout(Context context, @Nullable AttributeSet attrs, int defStyleAttr)
    {
        super(context, attrs, defStyleAttr);
        init(attrs);
    }

    protected void init(@Nullable AttributeSet attrs)
    {
        setOnDragListener(onDragListener);

        view_location = new View(getContext());
        view_location.setBackgroundColor(getResources().getColor(R.color.colorViewLocation));
        view_location.setLayoutParams(new ViewGroup.LayoutParams(0, 0));
    }

    public boolean isEmpty()
    {
        return getChildCount() <= 0;
    }

    public void setEditMode(boolean isEditMode)
    {
        this.isEditMode = isEditMode;
    }

    public void toggleEditMode()
    {
        isEditMode = !isEditMode;
    }

    public boolean isEditMode()
    {
        return isEditMode;
    }

    @Override
    protected void onDraw(@NonNull Canvas canvas)
    {
        super.onDraw(canvas);
        int i = 0;
        while(i < getChildCount())
        {
            changeLongClick(getChildAt(i));
            i++;
        }
    }

    private void changeLongClick(View view)
    {
        if(view instanceof Widget)
        {
            view.setOnLongClickListener(onLongClickListener);
        }
        if(view instanceof WidgetLinearLayout)
        {
            view.setOnDragListener(onDragListener);
        }
        if(view instanceof ViewGroup)
        {
            ViewGroup vg = (ViewGroup) view;
            int i = 0;
            while(i < getChildCount())
            {
                changeLongClick(vg.getChildAt(i));
                i++;
            }
        }
    }
    
    public View.OnLongClickListener onLongClickListener = new View.OnLongClickListener()
    {
        @Override
        public boolean onLongClick(View v)
        {
            ViewGroup vg = (ViewGroup) v.getParent();
            view_location.getLayoutParams().width = v.getWidth();
            view_location.getLayoutParams().height = v.getHeight();

            if (DesignerLayout.this == vg.getParent())
            {
                for (int i = 0; i < getChildCount(); i++)
                {
                    if (vg == getChildAt(i))
                    {
                        defaultIndex = i;
                    }
                }
            }
            else
            {
                defaultIndex = -1;
            }

            if (v.startDrag(null, new View.DragShadowBuilder(v), v, 0))
            {
                ViewGroup.LayoutParams params = new ViewGroup.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT
                );

                Context context = DesignerApp.getContext();

                LinearLayout linear = new LinearLayout(context);
                linear.setOnDragListener(onDragListener);
                linear.setLayoutParams(params);

                mWidget = vg;

                if (DesignerLayout.this == vg.getParent())
                {
                    removeView(vg);
                }
                DesigerUtil.vibrate();
            }
            return true;
        }
    };

    public View.OnDragListener onDragListener = new View.OnDragListener()
    {
        @Override
        public boolean onDrag(View v, DragEvent event)
        {
            switch (event.getAction())
            {
                case DragEvent.ACTION_DRAG_STARTED :
                    index = getChildCount();
                    addedInLayout = false;
                    mView = null;
                    break;
                case DragEvent.ACTION_DROP :
                    if (view_location.getParent() != null)
                    {
                        addWidgetInLayout(mWidget, index);
                    }
                    break;
                case DragEvent.ACTION_DRAG_LOCATION :
                    if (mView == v)
                    {
                        break;
                    }

                    mView = v;

                    if (mView != DesignerLayout.this)
                    {
                        for (int i = 0; i < getChildCount(); i++)
                        {
                            if (v == getChildAt(i))
                            {
                                index = i;
                            }
                        }
                    }

                    try
                    {
                        removeView(view_location);
                    }
                    catch (Exception e)
                    {}

                    try
                    {
                        addView(view_location, index);
                    }
                    catch (Exception e)
                    {}
                    break;
                case DragEvent.ACTION_DRAG_EXITED :
                    try
                    {
                        removeView(view_location);
                    }
                    catch (Exception e)
                    {}
                    if (mView == DesignerLayout.this)
                    {
                        index = getChildCount();
                    }
                    mView = null;
                    break;
                case DragEvent.ACTION_DRAG_ENDED :
                    if (defaultIndex != -1 && !addedInLayout)
                    {
                        addWidgetInLayout(mWidget, defaultIndex);
                    }

                    try
                    {
                        removeView(view_location);
                    }
                    catch (Exception e)
                    {}
                    break;
            }
            return true;
        }
    };

    private void addWidgetInLayout(View v, int index)
    {
        addView(v, index);
        addedInLayout = true;
    }
}
