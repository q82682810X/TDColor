package tdc.seriase.com.tdcolor.result;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.jph.takephoto.model.TImage;

import java.io.File;
import java.util.ArrayList;

import tdc.seriase.com.tdcolor.R;
import tdc.seriase.com.tdcolor.touch.TouchImageView;
import tdc.seriase.com.tdcolor.utils.ColorNS;
import tdc.seriase.com.tdcolor.utils.NearColorSelector;


public class ResultActivity extends Activity implements TouchImageView.OnTouchListener {

    ArrayList<TImage> images;

    private TouchImageView imageView;

    private TextView textView;

    private TextView showColor;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result_layout);
        imageView = findViewById(R.id.showResult);
        images = (ArrayList<TImage>) getIntent().getSerializableExtra("images");
        textView = findViewById(R.id.tvShowColor);
        showColor = findViewById(R.id.showColor);
        imageView.setListener(this);
        showResult();
    }

    private void showResult() {
        if (images != null && images.size() > 0) {
            Glide.with(this).load(new File(images.get(0).getOriginalPath())).into(imageView);
        }
    }


    @Override
    public void takeColor(int result) {
        ColorNS colorNS = NearColorSelector.getNearestColor(result);
        textView.setText(colorNS.getName());
        showColor.setBackgroundColor(colorNS.getValue());
    }
}
