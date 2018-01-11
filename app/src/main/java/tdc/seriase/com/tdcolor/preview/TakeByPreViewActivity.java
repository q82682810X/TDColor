package tdc.seriase.com.tdcolor.preview;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.hardware.Camera;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.SurfaceHolder;
import android.widget.Toast;

import java.io.IOException;

import permissions.dispatcher.NeedsPermission;
import permissions.dispatcher.RuntimePermissions;
import tdc.seriase.com.tdcolor.R;
import tdc.seriase.com.tdcolor.preview.callback.PreViewCallBack;
import tdc.seriase.com.tdcolor.utils.ColorNS;
import tdc.seriase.com.tdcolor.utils.NearColorSelector;

/**
 * Created by chen jc on 2018/1/8.
 */
@RuntimePermissions
public class TakeByPreViewActivity extends AppCompatActivity implements MySurfaceView.OnTouchXY {

    private MySurfaceView surfaceView;

    private SurfaceHolder surfaceHolder;

    private Camera mCamera;

    private SurfaceHolder.Callback callback;

    private PreViewCallBack preViewCallBack;

    private float touchX = -1;
    private float touchY = -1;

    private Toast mToast;

    public static Intent createIntent(Context context) {
        return new Intent(context, TakeByPreViewActivity.class);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_take_preview);

        surfaceView = findViewById(R.id.surfaceView);
        surfaceView.setListener(this);
        init();
    }

    @SuppressLint("ShowToast")
    private void showToast(String tip) {
        if (mToast == null) {
            mToast = Toast.makeText(TakeByPreViewActivity.this, tip, Toast.LENGTH_SHORT);
        } else {
            mToast.setText(tip);
        }
        mToast.show();
    }

    @NeedsPermission(Manifest.permission.CAMERA)
    public void init() {
        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.CAMERA)
                != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.CAMERA},
                    10001);
            showToast("请先获取权限...");
            return;
        }
        mCamera = Camera.open();
        surfaceHolder = surfaceView.getHolder();
        preViewCallBack = new PreViewCallBack();
        preViewCallBack.setListener(new PreViewCallBack.OnCatchListener() {
            @Override
            public void onCatch(Bitmap bitmap) {
                if (touchX > 0 && touchY > 0 && bitmap != null) {
                    int pixel = bitmap.getPixel(((int) touchX), ((int) touchY));
                    ColorNS nearestColor = NearColorSelector.getNearestColor(pixel);
                    showToast("获取的颜色为：" + nearestColor.getName());
                }
            }
        });
        callback = new SurfaceHolder.Callback() {
            @Override
            public void surfaceCreated(SurfaceHolder holder) {
                try {
                    if (mCamera == null) {
                        mCamera = Camera.open();
                    }
                    mCamera.setDisplayOrientation(90);
                    mCamera.setPreviewDisplay(surfaceHolder);
                    mCamera.setAutoFocusMoveCallback(new Camera.AutoFocusMoveCallback() {
                        @Override
                        public void onAutoFocusMoving(boolean start, Camera camera) {

                        }
                    });
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
                mCamera.startPreview();
            }

            @Override
            public void surfaceDestroyed(SurfaceHolder holder) {
                releaseCamera();
            }
        };
        try {
            mCamera.setPreviewDisplay(surfaceHolder);
            surfaceHolder.addCallback(callback);
            mCamera.setPreviewCallback(preViewCallBack);
        } catch (IOException e) {
            Toast.makeText(this, "开启照相机预览失败", Toast.LENGTH_SHORT).show();
            finish();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        releaseCamera();
    }

    private void releaseCamera() {
        if (mCamera != null) {
            mCamera.setPreviewCallback(null);
            mCamera.stopPreview();
            mCamera.release();
            mCamera = null;
        }
    }

    @Override
    public void touchXY(float x, float y) {
        this.touchX = x;
        this.touchY = y;
    }
}
