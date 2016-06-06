package www.manycat.net.rangeseekbar;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.SeekBar;

import java.util.ArrayList;

/**
 * Created by David on 2016/6/5.
 */
public class RangeSeekBar extends SeekBar{

    private Paint mPaint;

    private ArrayList<String> rangeList;

    private int fontSize, rangeLength;

    private float range;

    public RangeSeekBar(Context context) {
        super(context);
    }

    public RangeSeekBar(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    public RangeSeekBar(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(h, w, oldh, oldw);
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

//            int paddingLeft = 40;
//            int paddingRight = 40;

            int paddingLeft = getPaddingLeft();
            int paddingRight = getPaddingRight();

            // Seekbar寬度
            float maxWidth = c.getWidth();

            // Seekbar高度
            float maxHeight = c.getHeight();

            // 每個range間距的高度
            float rangeHeight = ( maxHeight - paddingLeft - paddingRight ) / (rangeLength - 1);

            // text x座標(預設為1/4寬)
            float x_point = maxWidth/4;

            // text 起點y座標
            int rangeStartPointY = ( paddingLeft - fontSize/2) * (-1);

            for( int i=0; i < rangeLength; i++)
            {
                float rangePointY = 0;

                if( i == 0 )
                {
                    //第一筆資料
                    rangePointY = rangeStartPointY;
                }
                else
                {
                    rangePointY = -1 * rangeHeight * i + rangeStartPointY;
                }

                // 處理text文字paddingBottom
                rangePointY = rangePointY - fontSize/8;

                mPaint = new Paint();
                mPaint.setTextSize(fontSize);//設定字體大小
                mPaint.setColor(Color.BLACK);//設定字體顏色
                c.drawText( rangeList.get(i), x_point, rangePointY, mPaint);
            }
        }
    }

    public void setRangeAndText( ArrayList<String> rangeList, int fontSize)
    {
        this.rangeList = rangeList;
        this.fontSize = fontSize;

        // 資料筆數
        rangeLength = rangeList.size();

        // 設定range%數
        range = (float)100/(rangeLength-1);

        int newPaddingTop = (int) (getLayoutParams().width*(-1) + getLayoutParams().width/4);
        setPadding( getPaddingLeft(), newPaddingTop, getPaddingRight(), getPaddingBottom());
        invalidate();
    }

    public void setProgressByText(String targetString)
    {
        for( int i=0; i < rangeLength; i++)
        {
            if( rangeList.get(i).toString().equals(targetString) )
            {
                setProgress((int)(i*range));
            }
        }
    }

    public String getProgressText()
    {
        int progress = getProgress();

        int level = (int) (progress / range);

        return rangeList.get(level).toString();
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
                //每次拖动SeekBar都会调用
                onSizeChanged(getWidth(), getHeight(), 0, 0);
                break;

            case MotionEvent.ACTION_CANCEL:
                break;
        }
        return true;
    }

}
