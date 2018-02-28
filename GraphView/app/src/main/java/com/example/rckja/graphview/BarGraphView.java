package com.example.rckja.graphview;

import android.app.Application;
import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;
import android.widget.LinearLayout;

import java.text.SimpleDateFormat;
import java.util.List;

/**
 * Created by rckja on 24-02-2018.
 */

public class BarGraphView extends View {

    public static final int DEFAULT_COLOR = Color.parseColor("#cccccc");
    public int mBarColor = Color.parseColor("#6c7b8b");
    public int mMaxColor = Color.parseColor("#27408B");
    private List<Attendance> mAttendance;
    float width;
    public BarGraphView(Context context) {
        super(context);
        init(context);
    }
    private Paint paint;

    public BarGraphView(Context context, AttributeSet attributes) {
        super(context,attributes);
        TypedArray typedArray = context.obtainStyledAttributes(attributes, R.styleable.graphColor);
        mBarColor = typedArray.getColor(R.styleable.graphColor_barColor, mBarColor);
        mMaxColor =typedArray.getColor(R.styleable.graphColor_maxColor,mMaxColor);
        typedArray.recycle();
        init(context);
        setWillNotDraw(false);
    }
    public void addList(List<Attendance> attendance){
        if (attendance != null)
            mAttendance =attendance;
    }

    public void init(Context context) {
         width = dpToPx(getWidth() );

    }

    public Object getItem(int position) {
        return mAttendance.get(position);
    }

    @Override
    //Overirde OnDraw to draw a custom Graph.
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        float border = dpToPx(26);
        float horstart =1* border +0;
        float height =dpToPx(300);
        width = canvas.getWidth();
        float graphHeight = height - (2 * border);
        float graphwidth = width - (2 * border);
        float barBorder =dpToPx(12);
            paint =new Paint();
            paint.setTextAlign(Paint.Align.LEFT);
            paint.setColor(Color.BLACK);
            paint.setStrokeWidth(5);
            float startY = graphHeight -border*2 ;
            canvas.drawLine(horstart,graphHeight,graphwidth +border, graphHeight,paint); // Plot X Axis for th graph
            canvas.drawLine(horstart, graphHeight, 0+1*border, border,paint);// Plot X Axis for th graph
            float yHeight = startY -border;
            paint.setColor(Color.BLACK);
            paint.setTextSize(40);
            canvas.drawText("0", 1*border-dpToPx(15), graphHeight, paint);
        if(mAttendance != null)
        {
            float max = getMax();
            float min = getMin();
            float diff = max - min;
            paint.setTextAlign(Paint.Align.CENTER);
            //Marks the highest Attendance Count on the Y axis.
            canvas.drawText( String.valueOf(max).split("\\.")[0] , 1*border-dpToPx(13), border + 2*barBorder, paint);
                paint.setStrokeWidth(5);
                float xWidth =(graphwidth-border) - (6 *barBorder);
                    float colWidth =xWidth /5;
                    for (int i = 0; i < mAttendance.size(); i++) {
                        Attendance objAttendance =(Attendance) getItem(i);
                        float val = objAttendance.mAttendanceCount - min;
                        float h;
                        if(Math.abs(val) !=0.0){
                            float rat = val / diff;
                            h = graphHeight * rat;
                        }
                        else

                        {
                            if(mAttendance.size() ==1 || min==max)
                                h=graphHeight;
                            else
                                h= graphHeight/5;
                            //mAttendance.size() ==1 ? h=graphheight : h= graphheight/5;

                        }
                        if(h< graphHeight/5)
                            h=graphHeight/5;
                        float startPOs =horstart +( i* colWidth) + (i +1)*barBorder;
                        if(Math.abs(objAttendance.mAttendanceCount) ==Math.abs(max) )
                             paint.setColor(mMaxColor);
                        else
                            paint.setColor(mBarColor);
                        //objAttendane.mAttendanceCount == max ? paint.setColor(mMaxColor) :paint.setColor(mBarColor);
                        canvas.drawRect(startPOs, (border - h)+barBorder + graphHeight, startPOs + colWidth , graphHeight, paint); //Plots the bar for particular date with height
                                                                                                                                              // in proportion to min and max.
                        float horLabelXPosition  =startPOs + colWidth/2;
                        float horLabelYPosition =graphHeight +dpToPx(20);
                        SimpleDateFormat formatter = new SimpleDateFormat("MM/dd");
                        String today = formatter.format(objAttendance.mDate);
                        canvas.drawText(today , horLabelXPosition , horLabelYPosition, paint);
                    }
        }
    }
    //Return max from List<Attendance> mAttendance.
    private float getMax() {
        float largest = Integer.MIN_VALUE;
        for (int i = 0; i < mAttendance.size(); i++) {
            Attendance objAttendance =(Attendance) getItem(i);
            if (objAttendance.mAttendanceCount >largest)
            largest = objAttendance.mAttendanceCount;
        }
        return largest;
    }
    //Return min from List<Attendance> mAttendance.
    private float getMin() {
        float smallest = Integer.MAX_VALUE;
        for (int i = 0; i < mAttendance.size(); i++) {
            Attendance objAttendance =(Attendance) getItem(i);
            if (objAttendance.mAttendanceCount < smallest)
            smallest = objAttendance.mAttendanceCount;
        }
        return smallest;
    }
    private static int dpToPx(int dpValue) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dpValue,Resources.getSystem().getDisplayMetrics());
    }
}
