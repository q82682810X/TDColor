package tdc.seriase.com.tdcolor.result;

import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import java.util.Arrays;
import java.util.List;

import tdc.seriase.com.tdcolor.R;
import tdc.seriase.com.tdcolor.databinding.ColorsItemViewBinding;
import tdc.seriase.com.tdcolor.utils.ColorNS;

/**
 * Created by chen jc on 2018/1/11.
 */

public class ColorListActivity extends AppCompatActivity {

    private RecyclerView recyclerView;

    public static Intent createIntent(Context context) {
        return new Intent(context, ColorListActivity.class);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.show_color_list);
        init();
    }

    private void init() {
        recyclerView = findViewById(R.id.recyclerView);
        ColorNS[] values = ColorNS.values();
        List<ColorNS> datas = Arrays.asList(values);
        recyclerView.setAdapter(new ColorsAdapter(datas));
        recyclerView.setLayoutManager(new GridLayoutManager(this, 1));
    }


    static class ColorsAdapter extends RecyclerView.Adapter<ColorListActivity.ViewHolder> {

        private List<ColorNS> datas;

        ColorsAdapter(List<ColorNS> datas) {
            this.datas = datas;
        }

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            ColorsItemViewBinding binding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.colors_item_view, parent, false);
            return new ViewHolder(binding);
        }

        @Override
        public void onBindViewHolder(ViewHolder holder, int position) {
            holder.bindColor(datas.get(position));
        }

        @Override
        public int getItemCount() {
            return datas.size();
        }
    }

    static class ViewHolder extends RecyclerView.ViewHolder {

        ColorsItemViewBinding binding;

        ViewHolder(ColorsItemViewBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        void bindColor(ColorNS colorNS) {
            binding.setColorNs(colorNS);
        }
    }

}
