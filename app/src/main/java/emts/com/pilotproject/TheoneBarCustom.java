package emts.com.pilotproject;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;

/**
 * Created by User on 2015-08-31.
 */
public class TheoneBarCustom extends View {
    Paint mPaint;
    int mStrokeColor = Color.BLACK;
    float mStrokeWidth = 4;
    int mPoint;
    RectF mRect;
    Context context;

    public TheoneBarCustom(Context context) {
        super(context);
        this.context = context;

        init();
    }

    public TheoneBarCustom(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;

        init();
    }

    public TheoneBarCustom(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.context = context;

        init();
    }

    public TheoneBarCustom(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        this.context = context;

        init();
    }

    private void init() {
        mPaint = new Paint();
        mPaint.setColor(mStrokeColor);
        mPaint.setStrokeWidth(mStrokeWidth);
        mPaint.setAntiAlias(true); //smooth out the edges
        mPaint.setStrokeCap(Paint.Cap.ROUND);

        mRect = new RectF();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int parentWidth = MeasureSpec.getSize(widthMeasureSpec);
        int parentHeight = MeasureSpec.getSize(heightMeasureSpec);
        this.setMeasuredDimension(parentWidth, parentHeight);
        Log.e("theone ints", parentHeight + "::" + parentWidth);
        Log.e("theone spec", heightMeasureSpec + "::" + widthMeasureSpec);
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        float paddingLeft = getPaddingLeft();
        float paddingRight = getPaddingRight();
        float paddingTop = getPaddingTop();
        float paddingBottom = getPaddingBottom();

        float width = getWidth() - (paddingLeft + paddingRight);
        float height = getHeight() - (paddingBottom + paddingTop);
        Log.e("theone getWidth", width + "");
        Log.e("theone getHeight", height + "");

        mPaint.setColor(Color.parseColor("#F9F9F9"));
        mPaint.setStrokeWidth(2);
        //the holder rectangle bottom one
        canvas.drawRect(paddingLeft, paddingTop, width - paddingRight, height - paddingBottom, mPaint);

        float ratio = (width - paddingRight) / 1000;

        if (mPoint < 500) {
            //upto subsidy
            mPaint.setColor(Color.GREEN);
            canvas.drawRect(paddingLeft + 1, paddingTop + 1, 1 + (mPoint * ratio) + paddingRight, height - paddingBottom - 1, mPaint);

            //subsidy point mark
            mPaint.setColor(Color.BLUE);
            mPaint.setStrokeWidth(0);
            canvas.drawRect(1 + (499 * ratio) + (paddingLeft / 2), paddingTop - 10, 1 + (503 * ratio), height - paddingBottom + 10, mPaint);

        } else {
            //upto sibsidy point
            mPaint.setColor(Color.GREEN);
            canvas.drawRect(paddingLeft + 1, paddingTop + 1, 1 + (498 * ratio) + paddingRight, height - paddingBottom - 1, mPaint);

            //subsidy point mark
            mPaint.setColor(Color.BLUE);
            mPaint.setStrokeWidth(0);
            canvas.drawRect(1 + (499 * ratio), paddingTop - 10, 1 + (503 * ratio), height - paddingBottom + 10, mPaint);

            //beyound sibsidy
            mPaint.setColor(Color.RED);
            canvas.drawRect(1 + (500 * ratio) + 2, paddingTop + 1, width + paddingRight - ((mPoint - 499) * ratio), height - paddingBottom - 1, mPaint);
        }
        Log.e("theonebarcustom", "draw the rectangle");

        mPaint.setColor(mStrokeColor);
        canvas.drawArc(mRect, 0, mPoint, true, mPaint);
    }

    public void setValue(int value) {
        mPoint = value;
        invalidate();
    }

}
