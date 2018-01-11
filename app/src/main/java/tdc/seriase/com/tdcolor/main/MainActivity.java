package tdc.seriase.com.tdcolor.main;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;

import com.jph.takephoto.app.TakePhoto;
import com.jph.takephoto.app.TakePhotoActivity;
import com.jph.takephoto.model.CropOptions;
import com.jph.takephoto.model.TResult;
import com.jph.takephoto.model.TakePhotoOptions;

import java.io.File;

import tdc.seriase.com.tdcolor.R;
import tdc.seriase.com.tdcolor.base.BasePresent;
import tdc.seriase.com.tdcolor.databinding.ActivityMainBinding;
import tdc.seriase.com.tdcolor.preview.TakeByPreViewActivity;
import tdc.seriase.com.tdcolor.result.ColorListActivity;
import tdc.seriase.com.tdcolor.result.ResultActivity;

public class MainActivity extends TakePhotoActivity implements MainContact.MainView {

    private BasePresent present;
    private ActivityMainBinding binding;

    private TakePhoto takePhoto;

    private static String BASE_IMAGE_PATH = Environment.getExternalStorageDirectory() + "/tdc/";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        binding.setActivity(this);
        present = new MainPresent(this);
        takePhoto = getTakePhoto();
    }

    @Override
    protected void onStart() {
        super.onStart();
        takePhoto.onEnableCompress(null, false);
        TakePhotoOptions.Builder builder = new TakePhotoOptions.Builder();
        builder.setWithOwnGallery(true);
        builder.setCorrectImage(true);
        takePhoto.setTakePhotoOptions(builder.create());
    }

    public void getByCapture() {
        File file = new File(BASE_IMAGE_PATH + System.currentTimeMillis() + ".jpg");
        if (!file.getParentFile().exists()) {
            file.getParentFile().mkdirs();
        }
        Uri imageUri = Uri.fromFile(file);

        CropOptions.Builder builder = new CropOptions.Builder();
        builder.setWithOwnCrop(true);
        takePhoto.onPickFromCaptureWithCrop(imageUri, builder.create());
    }

    public void takeByPreView() {
        Intent intent = TakeByPreViewActivity.createIntent(this);
        startActivity(intent);
    }

    public void getByGallery() {
        takePhoto.setTakePhotoOptions(null);
        takePhoto.onPickFromGallery();
    }

    public void getByDocument() {
        takePhoto.setTakePhotoOptions(null);
        takePhoto.onPickFromDocuments();
    }

    public void showColors() {
        Intent intent = ColorListActivity.createIntent(this);
        startActivity(intent);
    }

    @Override
    public void takeSuccess(TResult result) {
        super.takeSuccess(result);
        Intent intent = new Intent(this, ResultActivity.class);
        intent.putExtra("images", result.getImages());
        startActivity(intent);
    }

    @Override
    public void setPresenter(MainContact.MainPresent presenter) {
        this.present = presenter;
    }
}
