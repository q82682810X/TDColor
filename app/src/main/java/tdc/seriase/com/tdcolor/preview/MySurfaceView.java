package tdc.seriase.com.tdcolor.preview;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.SurfaceView;

import tdc.seriase.com.tdcolor.preview.callback.PreViewCallBack;

/**
 * Created by chen jc on 2018/1/8.
 */

public class MySurfaceView extends SurfaceView {

    public interface OnTouchXY {
        void touchXY(float x, float y);
    }

    private OnTouchXY listener;

    public void setListener(OnTouchXY listener) {
        this.listener = listener;
    }

    public MySurfaceView(Context context) {
        super(context);
    }

    public MySurfaceView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MySurfaceView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_DOWN && listener != null) {
            listener.touchXY(event.getX(), event.getY());
            PreViewCallBack.click();
        }
        return super.onTouchEvent(event);
    }
}
