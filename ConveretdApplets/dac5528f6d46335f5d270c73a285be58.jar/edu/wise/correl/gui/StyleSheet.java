// 
// Decompiled by Procyon v0.5.30
// 

package edu.wise.correl.gui;

import java.awt.Color;
import java.awt.Font;

public class StyleSheet
{
    public static final Font f_reg;
    public static final Font f_big;
    public static final Font labelFont;
    public static final Font f_sm;
    public static final Color PRED;
    public static final Color ERR;
    public static final Color TOT;
    public static final Color BACKGROUND;
    public static final Color FOREGROUND;
    
    static {
        f_reg = new Font("SansSerif", 0, 9);
        f_big = new Font("SansSerif", 0, 12);
        labelFont = new Font("SansSerif", 1, 9);
        f_sm = new Font("SansSerif", 0, 9);
        PRED = new Color(0, 0, 255);
        ERR = new Color(255, 0, 0);
        TOT = new Color(100, 100, 100);
        BACKGROUND = Color.white;
        FOREGROUND = Color.black;
    }
    
    public static Color randomColor(final int n) {
        return (new Color[] { new Color(102, 41, 255), new Color(153, 0, 153), new Color(41, 102, 41), new Color(41, 41, 153), new Color(255, 41, 0), new Color(0, 204, 41), new Color(102, 41, 255), new Color(0, 41, 41), new Color(153, 41, 41), new Color(41, 153, 204), new Color(204, 41, 255), new Color(204, 0, 102), new Color(0, 0, 102), new Color(102, 0, 41), new Color(255, 41, 204), new Color(0, 255, 41) })[n % 16];
    }
}
