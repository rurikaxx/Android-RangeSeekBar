package www.manycat.net.rangeseekbar;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.SeekBar;

import java.util.ArrayList;

/**
 * Created by David on 2016/6/5.
 */
public class RangeSeekBar extends SeekBar{

    private Paint mPaint;

    private ArrayList<String> rangeList;

    private int fontSize;

    /**
     * Size of text mark.
     */
    private int mTextSize;

    public RangeSeekBar(Context context) {
        super(context);
        initPaint();
    }

    public RangeSeekBar(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        initPaint();
    }

    public RangeSeekBar(Context context, AttributeSet attrs) {
        super(context, attrs);
        initPaint();
    }

    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(h, w, oldh, oldw);
    }

    private void initPaint() {
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);

        mPaint.setAntiAlias(true);
        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setTextSize(20);
    }

    @Override
    protected synchronized void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(heightMeasureSpec, widthMeasureSpec);
        setMeasuredDimension(getMeasuredHeight(), getMeasuredWidth());
    }

    protected void onDraw(Canvas c) {

        //将SeekBar转转90度
        c.rotate(-90);
        //将旋转后的视图移动回来
        c.translate(-getHeight(),0);
        super.onDraw(c);

        if( this.rangeList != null )
        {
            c.save();
            c.rotate(90);

            // int rangeStartPointY = fontSize/2 * (-1);
            int rangeStartPointY = (40 - fontSize/2) * (-1);

            int rangLength = rangeList.size();

            float maxHeight = c.getHeight();

            float maxWidth = c.getWidth();

            float rangeHeight = (maxHeight+rangeStartPointY-40)/(rangLength-1);

            float x_point = maxWidth/2 + fontSize;

            for( int i=0; i < rangLength; i++)
            {
                float rangePointY = 0;

                if( i == 0 )
                {
                    rangePointY = rangeStartPointY;
                }
                else if( i == (rangLength-1) )
                {
                    rangePointY = -1 * (maxHeight-40);
                }
                else
                {
                    rangePointY = -1 * rangeHeight * i + rangeStartPointY;
                }

                Log.i( "David", " rangePointY == " + rangePointY);

                mPaint = new Paint();
                mPaint.setTextSize(fontSize);//設定字體大小
                mPaint.setColor(Color.BLACK);//設定字體顏色
                c.drawText( rangeList.get(i), x_point, rangePointY, mPaint);
            }
        }
    }

    public void onSetRangeAndText( ArrayList<String> rangeList, int fontSize)
    {
        this.rangeList = rangeList;
        this.fontSize = fontSize;
        invalidate();
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (!isEnabled()) {
            return false;
        }

        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
            case MotionEvent.ACTION_MOVE:
            case MotionEvent.ACTION_UP:
                int i=0;
                //获取滑动的距离
                i = getMax() - (int) (getMax() * event.getY() / getHeight());

                //设置进度
                int rangLength = rangeList.size();

                float range = 100/(rangLength-1);

                int clickLevel = (int)(i / range);

                int setPoint = 0;

                if( i > range/2 )
                {
                    if( i - clickLevel * range > range/2)
                    {
                        setPoint = (int) ((clickLevel+1) * range);
                    }
                    else
                    {
                        setPoint = (int) (clickLevel * range);
                    }
                }

                setProgress(setPoint);
                // Log.i("David", " ================================== ");
//                Log.i("David", " getProgress " + getProgress()+"");
                //每次拖动SeekBar都会调用
                onSizeChanged(getWidth(), getHeight(), 0, 0);
//                Log.i("David", " getWidth " + getWidth()+"");
//                Log.i("David", " getHeight" + getHeight()+"");
//                Log.i("David", " ================================== ");
                break;

            case MotionEvent.ACTION_CANCEL:
                break;
        }
        return true;
    }

}
