package com.example.collegehelper.ui.schedule;

import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.CalendarView;
import android.content.Context;
import android.view.ViewParent;
import android.view.MotionEvent;

public class scrollCalendar extends CalendarView {

    public scrollCalendar(Context context)
    {
        super(context);
    }

    public scrollCalendar(Context context, AttributeSet attrs)
    {
        super(context,attrs);
    }

    public scrollCalendar(Context context,AttributeSet attrs, int defStyle)
    {
        super(context,attrs,defStyle);
    }
    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        if (ev.getActionMasked() == MotionEvent.ACTION_DOWN) {
            ViewParent p= this.getParent();
            if (p != null)
                p.requestDisallowInterceptTouchEvent(true);
        }
        return false;
    }
}
