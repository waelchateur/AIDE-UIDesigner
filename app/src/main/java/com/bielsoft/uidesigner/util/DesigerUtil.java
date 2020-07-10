package com.bielsoft.uidesigner.util;

import android.content.Context;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.os.Vibrator;
import android.support.annotation.ColorInt;
import android.support.annotation.StringRes;
import android.support.v4.graphics.drawable.DrawableCompat;
import android.util.TypedValue;
import android.widget.Toast;
import com.bielsoft.uidesigner.DesignerApp;

public class DesigerUtil
{
    public static Toast message(@StringRes int i)
    {
        return Toast.makeText(DesignerApp.getContext(), i, Toast.LENGTH_SHORT);
    }

    public static Toast message(String s)
    {
        return Toast.makeText(DesignerApp.getContext(), s, Toast.LENGTH_SHORT);
    }

    public static void vibrate()
    {
        ((Vibrator) DesignerApp.getContext().getSystemService(Context.VIBRATOR_SERVICE)).vibrate(100);
    }

    public static Integer dwp()
    {
        return DesignerApp.getContext().getResources().getDisplayMetrics().widthPixels;
    }

    public static Integer dhp()
    {
        return DesignerApp.getContext().getResources().getDisplayMetrics().heightPixels;
    }

    public static Integer dip(float dp)
    {
        return (int)TypedValue.applyDimension(
            TypedValue.COMPLEX_UNIT_DIP,
            dp,
            DesignerApp.getContext().getResources().getDisplayMetrics());
    }

    public static Drawable setTint(Drawable drawable, @ColorInt int color)
    {
        drawable.clearColorFilter();
        drawable.setColorFilter(color, PorterDuff.Mode.SRC_IN);
        drawable.invalidateSelf();
        Drawable draw = DrawableCompat.wrap(drawable).mutate();
        DrawableCompat.setTint(draw, color);
        return draw;
	}
}
