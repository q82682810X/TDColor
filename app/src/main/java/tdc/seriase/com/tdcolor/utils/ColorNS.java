package tdc.seriase.com.tdcolor.utils;

import android.graphics.Color;

/**
 * Created by chen jc on 2018/1/3.
 */

public enum ColorNS {

    LightPink(0xFFB6C1, "浅粉红"),
    Pink(0xFFC0CB, "粉红"),
    Crimson(0xDC143C, "深红(猩红)"),
    LavenderBlush(0xFFF0F5, "淡紫红"),
    PaleVioletRed(0xDB7093, "弱紫罗兰红"),
    MediumVioletRed(0xC71585, "中紫罗兰红"),
    Orchid(0xDA70D6, "暗紫色"),
    DarkMagenta(0x8B008B, "深洋红"),
    Purple(0x800080, "紫色"),
    Indigo(0x4B0082, "靛青/紫兰色"),
    MediumSlateBlue(0x7B68EE, "中暗蓝色(中板岩蓝)"),
    Lavender(0xE6E6FA, "淡紫色(熏衣草淡紫)"),
    GhostWhite(0xF8F8FF, "幽灵白"),
    Blue(0x0000FF, "纯蓝"),
    MediumBlue(0x0000CD, "中蓝色"),
    DarkBlue(0x00008B, "暗蓝色"),
    RoyalBlue(0x4169E1, "皇家蓝/宝蓝"),
    LightSlateGray(0x778899, "亮蓝灰(亮石板灰)"),
    LightCyan(0xE0FFFF, "淡青色"),
    SlateGray(0x708090, "灰石色(石板灰)"),
    Cyan(0x00FFFF, "青色"),
    SpringGreen(0x00FF7F, "春绿色"),
    SeaGreen(0x2E8B57, "海洋绿"),
    LightGreen(0x90EE90, "淡绿色"),
    Green(0x008000, "纯绿"),
    GreenYellow(0xADFF2F, "绿黄色"),
    DarkOliveGreen(0xF5F5DC, "暗橄榄绿"),
    Beige(0x708090, "米色/灰棕色"),
    LightYellow(0xFFFFE0, "浅黄色"),
    Yellow(0xFFFF00, "纯黄"),
    Olive(0x808000, "橄榄"),
    Gold(0xFFD700, "金色"),
    Orange(0xFFA500, "橙色"),
    Tan(0xD2B48C, "茶色"),
    DarkOrange(0xFF8C00, "深橙色"),
    Chocolate(0xD2691E, "巧克力色"),
    OrangeRed(0xFF8C00, "橙红色"),
    Tomato(0xFF6347, "番茄红"),
    Snow(0xFFFAFA, "雪白色"),
    Red(0xFF0000, "纯红"),
    Brown(0xA52A2A, "棕色"),
    DarkRed(0x8B0000, "深红色"),
    White(0xFFFFFF, "纯白"),
    Silver(0xC0C0C0, "银灰色"),
    Gray(0x808080, "灰色"),
    Black(0x000000, "纯黑"),
    LightGrey(0xD3D3D3, "浅灰色"),
    DeepSkyBlue(0x00BFFF, "深天蓝");

    ColorNS(int value, String name) {
        this.value = value;
        this.name = name;
    }

    private int value;

    private String name;

    public int getValue() {
        int red = (value & 0xff0000) >> 16;
        int green = (value & 0x00ff00) >> 8;
        int blue = (value & 0x0000ff);
        return Color.rgb(red, green, blue);
    }

    public String getName() {
        return name;
    }

}
