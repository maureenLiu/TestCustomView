package com.maureen.testcustomview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

public class CircleView extends View {

    private  final String TAG = "CircleView";
    private Paint mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
    private int mColor = Color.RED;

    public CircleView(Context context) {
        super(context);
        init();
    }

    public CircleView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public CircleView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        mPaint.setColor(mColor);
    }

   /* @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        int width = getWidth();
        int height = getHeight();
        int radius = Math.min(width, height) / 2;
        canvas.drawCircle(width / 2, height / 2, radius, mPaint);
    } */

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        //Custom View extends View directly,  padding property set in layout doesn't work.
        //So need to consider the padding in onDraw().
        final int paddingLeft = getPaddingLeft();
        final int paddingRight = getPaddingRight();
        final int paddingTop = getPaddingTop();
        final int paddingButtom = getPaddingBottom();
        int width = getWidth() - paddingLeft - paddingRight;
        int height = getHeight() - paddingTop - paddingButtom;
        int radius = Math.min(width, height) / 2;
        canvas.drawCircle(paddingLeft + width/2, paddingTop + height/2, radius, mPaint);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        //set the wrap_content default size to 200px*200px
        int widthSpecMode = MeasureSpec.getMode(widthMeasureSpec);
        int widthSpecSize = MeasureSpec.getSize(widthMeasureSpec);
        int heightSpecMode = MeasureSpec.getMode(heightMeasureSpec);
        int heightSpecSize = MeasureSpec.getSize(heightMeasureSpec);
        if (widthSpecMode == MeasureSpec.AT_MOST && heightSpecMode == MeasureSpec.AT_MOST) {
            setMeasuredDimension(200, 200);
        } else if (widthSpecMode == MeasureSpec.AT_MOST) {
            setMeasuredDimension(200, heightSpecSize);
        } else if (heightMeasureSpec == MeasureSpec.AT_MOST){
            setMeasuredDimension(widthSpecSize, 200);
        }
    }


}
