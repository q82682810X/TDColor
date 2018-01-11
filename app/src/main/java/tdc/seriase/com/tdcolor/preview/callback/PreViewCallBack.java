package tdc.seriase.com.tdcolor.preview.callback;

import android.graphics.Bitmap;
import android.graphics.ImageFormat;
import android.graphics.Rect;
import android.graphics.YuvImage;
import android.hardware.Camera;

import java.io.ByteArrayOutputStream;

import static android.graphics.BitmapFactory.decodeByteArray;

/**
 * Created by chen jc on 2018/1/8.
 */

public class PreViewCallBack implements Camera.PreviewCallback {

    public interface OnCatchListener {
        void onCatch(Bitmap bitmap);
    }

    private OnCatchListener listener;

    private static boolean ACTION = false;

    public void setListener(OnCatchListener listener) {
        this.listener = listener;
    }

    @Override
    public void onPreviewFrame(byte[] data, Camera camera) {
        if (listener != null && data != null && ACTION) {
            Camera.Size size = camera.getParameters().getPreviewSize();
            try {
                YuvImage image = new YuvImage(data, ImageFormat.NV21, size.width, size.height, null);
                ByteArrayOutputStream stream = new ByteArrayOutputStream();
                image.compressToJpeg(new Rect(0, 0, size.width, size.height), 80, stream);
                Bitmap bmp = decodeByteArray(stream.toByteArray(), 0, stream.size());
                listener.onCatch(bmp);
                stream.close();
            } catch (Exception ex) {
            }
            ACTION = false;
        }
    }

    public static void click() {
        ACTION = true;
    }

}
