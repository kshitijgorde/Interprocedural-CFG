// 
// Decompiled by Procyon v0.5.30
// 

package edu.wise.graph;

import java.awt.Color;
import java.awt.Font;

public class StyleSheet
{
    public static final Font SMALL_FONT;
    public static final Font REGULAR_FONT;
    public static final Font REGULAR_FONT_BOLD;
    public static final Font REGULAR_ITALIC;
    public static final Font BIG_FONT;
    public static final Font LABEL_FONT;
    public static final Color ENABLED_BACKGROUND;
    public static final Color ENABLED_HIGHLIGHT;
    public static final Color ENABLED_FOREGROUND;
    public static final Color ENABLED_GRAY;
    public static final Color DISABLED_BACKGROUND;
    public static final Color DISABLED_HIGHLIGHT;
    public static final Color DISABLED_FOREGROUND;
    public static final Color DISABLED_GRAY;
    public static final Color TURQUOISE;
    public static final Color NOISE_COLOR;
    public static final Color SIGNAL_COLOR;
    public static final Color YELLOW;
    public static final Color DPR_COLOR;
    public static final Color CRIT_COLOR;
    
    static {
        SMALL_FONT = new Font("SansSerif", 0, 9);
        REGULAR_FONT = new Font("SansSerif", 0, 10);
        REGULAR_FONT_BOLD = new Font("SansSerif", 1, 10);
        REGULAR_ITALIC = new Font("SansSerif", 2, 10);
        BIG_FONT = new Font("SansSerif", 0, 10);
        LABEL_FONT = new Font("SansSerif", 1, 10);
        ENABLED_BACKGROUND = Color.white;
        ENABLED_HIGHLIGHT = new Color(255, 255, 204);
        ENABLED_FOREGROUND = Color.black;
        ENABLED_GRAY = Color.lightGray;
        DISABLED_BACKGROUND = Color.white;
        DISABLED_HIGHLIGHT = Color.lightGray;
        DISABLED_FOREGROUND = Color.lightGray;
        DISABLED_GRAY = Color.lightGray;
        TURQUOISE = new Color(204, 255, 255);
        NOISE_COLOR = Color.blue;
        SIGNAL_COLOR = Color.red;
        YELLOW = new Color(255, 255, 204);
        DPR_COLOR = Color.magenta;
        CRIT_COLOR = Color.lightGray;
    }
}
