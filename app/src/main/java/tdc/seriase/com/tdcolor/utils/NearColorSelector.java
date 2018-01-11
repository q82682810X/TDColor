package tdc.seriase.com.tdcolor.utils;

import android.util.Log;

/**
 * Created by chen jc on 2018/1/3.
 * 获取近似颜色的工具
 */

public class NearColorSelector {


    public static ColorNS getNearestColor(int color) {
        ColorNS res = null;

        int offset = -1;//记录当前最低的偏移量

        int red = (color & 0xff0000) >> 16;
        int green = (color & 0x00ff00) >> 8;
        int blue = (color & 0x0000ff);

        for (ColorNS ns : ColorNS.values()) {
            int rgb = ns.getValue();

            int r = (rgb & 0xff0000) >> 16;
            int g = (rgb & 0x00ff00) >> 8;
            int b = (rgb & 0x0000ff);
            //计算与当前颜色的偏移量
            int shift = (red - r) * (red - r) + (green - g) * (green - g) + (blue - b) * (blue - b);
            Log.i("calculate:", ns.getName() + " 偏移量 :" + shift);
            if (offset < 0) {
                offset = shift;
                res = ns;
            } else if (offset > shift) {
                offset = shift;
                res = ns;
            }
        }

        return res;
    }

}
