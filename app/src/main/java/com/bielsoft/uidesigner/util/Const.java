package com.bielsoft.uidesigner.util;

import android.graphics.drawable.Drawable;
import android.os.Environment;
import android.support.annotation.DrawableRes;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.bielsoft.uidesigner.DesignerApp;
import com.bielsoft.uidesigner.R;
import com.bielsoft.uidesigner.item.WidgetGroup;
import com.bielsoft.uidesigner.item.WidgetProperty;
import com.bielsoft.uidesigner.widget.WidgetButton;
import com.bielsoft.uidesigner.widget.WidgetImageView;
import com.bielsoft.uidesigner.widget.WidgetLinearLayout;
import com.bielsoft.uidesigner.widget.WidgetTextView;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class Const
{
    public static File APP_PATH = new File(Environment.getExternalStorageDirectory(), "AppUIDesigner");
    public static File LAYOUTS_PATH = new File(APP_PATH, "layout");
    public static File DRAWABLES_PATH = new File(APP_PATH, "drawable");
    public static File MIPMAP_PATH = new File(APP_PATH, "mipmap");
    public static File VALUES_PATH = new File(APP_PATH, "values");
    public static File COLORS_FILE = new File(VALUES_PATH, "colors.xml");

    public static Drawable getDrawable(@DrawableRes int resId)
    {
        return DesignerApp.getContext().getResources().getDrawable(resId); 
    }

    public static List<WidgetGroup> getWidgetsGroup()
    {
        List<WidgetGroup> list = new ArrayList<>();
        //Layouts
        list.add(new WidgetGroup("Layouts"));

        WidgetLinearLayout wl = new WidgetLinearLayout(DesignerApp.getContext());
        wl.setWidgetType(LinearLayout.class.getSimpleName());
        wl.setProperties(new ArrayList<WidgetProperty>());
        wl.setOrientation(LinearLayout.VERTICAL);
        list.add(new WidgetGroup("LinearLayout (vertical)", getDrawable(R.drawable.ic_viewgroup_linear_vertical), wl));

        wl = new WidgetLinearLayout(DesignerApp.getContext());
        wl.setWidgetType(LinearLayout.class.getSimpleName());
        wl.setProperties(new ArrayList<WidgetProperty>());
        wl.setOrientation(LinearLayout.HORIZONTAL);
        list.add(new WidgetGroup("LinearLayout (horizontal)", getDrawable(R.drawable.ic_viewgroup_linear_horizontal), wl));

        //Widgets
        list.add(new WidgetGroup("Widgets"));

        WidgetButton wb = new WidgetButton(DesignerApp.getContext());
        wb.setWidgetType(Button.class.getSimpleName());
        wb.setProperties(new ArrayList<WidgetProperty>());
        wb.getTextView().setText(wb.getWidgetType());
        list.add(new WidgetGroup("Button", getDrawable(R.drawable.ic_button), wb));

        //Views
        list.add(new WidgetGroup("Views"));

        WidgetTextView wt = new WidgetTextView(DesignerApp.getContext());
        wt.setWidgetType(TextView.class.getSimpleName());
        wt.setProperties(new ArrayList<WidgetProperty>());
        wt.getTextView().setText(wt.getWidgetType());
        list.add(new WidgetGroup("TextView", getDrawable(R.drawable.ic_textview), wt));

        WidgetImageView wi = new WidgetImageView(DesignerApp.getContext());
        wi.setWidgetType(ImageView.class.getSimpleName());
        wi.setProperties(new ArrayList<WidgetProperty>());
        list.add(new WidgetGroup("ImageView", getDrawable(R.drawable.ic_imageview), wi));

        return list;
    }
}
