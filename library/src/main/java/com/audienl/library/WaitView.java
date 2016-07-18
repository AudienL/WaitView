package com.audienl.library;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.os.SystemClock;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;

/**
 * @author audienl@qq.com on 2016/7/18.
 */
public class WaitView extends View {
    private Context context;

    private int mCircleColor;
    private int mPointColor;
    private int mStrokeWidth;
    private int mSpeed;

    private Paint mCirclePaint;
    private Paint mPointPaint;

    private int mWidth;
    private int mHeight;


    public WaitView(Context context) {
        this(context, null);
    }

    public WaitView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public WaitView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.context = context;

        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.WaitView, defStyleAttr, 0);
        mCircleColor = a.getColor(R.styleable.WaitView_circle_color, 0x55FFFFFF);
        mPointColor = a.getColor(R.styleable.WaitView_point_color, Color.YELLOW);
        mStrokeWidth = a.getDimensionPixelSize(R.styleable.WaitView_view_stroke_width, (int) dp2px(2));
        mSpeed = a.getInt(R.styleable.WaitView_speed, 8);
        a.recycle();

        mCirclePaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mCirclePaint.setStyle(Paint.Style.STROKE);
        mCirclePaint.setStrokeWidth(mStrokeWidth);
        mCirclePaint.setColor(mCircleColor);

        mPointPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPointPaint.setStyle(Paint.Style.STROKE);
        mPointPaint.setStrokeWidth(mStrokeWidth);
        mPointPaint.setColor(mPointColor);

        mWidth = (int) dp2px(60);
        mHeight = (int) dp2px(60);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);

        switch (widthMode) {
            case MeasureSpec.AT_MOST:
                break;
            case MeasureSpec.EXACTLY:
                mWidth = widthSize;
                break;
            case MeasureSpec.UNSPECIFIED:
                break;
        }

        switch (heightMode) {
            case MeasureSpec.AT_MOST:
                break;
            case MeasureSpec.EXACTLY:
                mHeight = heightSize;
                break;
            case MeasureSpec.UNSPECIFIED:
                break;
        }

        setMeasuredDimension(mWidth, mHeight);
    }

    int position = 0;

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        RectF rectF = new RectF(0 + mStrokeWidth / 2, 0 + mStrokeWidth / 2, mWidth - mStrokeWidth / 2, mHeight - mStrokeWidth / 2);
        canvas.drawArc(rectF, 0, 360, false, mCirclePaint);
        canvas.drawArc(rectF, position, 20, false, mPointPaint);
    }

    private boolean isStart = false;

    private void startAnimator() {
        if (isStart) return;
        isStart = true;
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (isStart) {
                    SystemClock.sleep(15);
                    position += mSpeed;
                    postInvalidate();
                }
            }
        }).start();
    }

    private void stopAnimator() {
        isStart = false;
    }

    @Override
    protected void onVisibilityChanged(View changedView, int visibility) {
        super.onVisibilityChanged(changedView, visibility);
        switch (visibility) {
            case VISIBLE:
                startAnimator();
                break;
            case INVISIBLE:
                stopAnimator();
                break;
            case GONE:
                stopAnimator();
                break;
        }
    }

    private float dp2px(int dp) {
        return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, getResources().getDisplayMetrics());
    }
}
