package tdc.seriase.com.tdcolor.touch;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.v7.widget.AppCompatImageView;
import android.util.AttributeSet;
import android.view.MotionEvent;

/**
 * Created by chen jc on 2018/1/6.
 */

public class TouchImageView extends AppCompatImageView {


    private static final float CIRCLE_R = 75; //获取手指点击的半径

    private float rangeWidth = 2 * CIRCLE_R;

    private int tipCircle = 3;

    private Paint mPaint;

    private float touchX = -1;
    private float touchY = -1;

    private OnTouchListener listener;

    private Canvas mCanvas;

    public TouchImageView(Context context) {
        super(context);
        init();
    }


    public TouchImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public TouchImageView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        setDrawingCacheEnabled(true);
        mPaint = new Paint();
        mPaint.setColor(Color.RED);
        mPaint.setStyle(Paint.Style.STROKE);
    }


    public void setListener(OnTouchListener listener) {
        this.listener = listener;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {

        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            if (event.getX() < rangeWidth) {
                touchX = rangeWidth;
            } else if (event.getX() > (getWidth() - rangeWidth)) {
                touchX = getWidth() - rangeWidth;
            } else {
                touchX = event.getX();
            }

            if (event.getY() < rangeWidth) {
                touchY = rangeWidth;
            } else if (event.getY() > (getHeight() - rangeWidth)) {
                touchY = getHeight() - rangeWidth;
            } else {
                touchY = event.getY();
            }
            invalidate();

            Bitmap drawingCache = getDrawingCache();
            if (drawingCache != null && listener != null) {

                int pixel = drawingCache.getPixel(((int) touchX), ((int) touchY));

                listener.takeColor(pixel);

            }

        }

        return super.onTouchEvent(event);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {
        return super.dispatchTouchEvent(event);
    }

    public interface OnTouchListener {
        void takeColor(int result);
    }

    @SuppressLint("DrawAllocation")
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (touchY > 0 && touchX > 0) {
            canvas.drawCircle(touchX, touchY, CIRCLE_R, mPaint);
            canvas.drawCircle(touchX, touchY, tipCircle, mPaint);
        }
    }
}
