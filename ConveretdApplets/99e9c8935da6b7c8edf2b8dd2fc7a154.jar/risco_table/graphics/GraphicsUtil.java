// 
// Decompiled by Procyon v0.5.30
// 

package risco_table.graphics;

import java.awt.Color;

public class GraphicsUtil
{
    private static final Color WHITE_BRIGHTER;
    private static final Color WHITE_DARKER;
    private static final Color BLACK_BRIGHTER;
    private static final Color BLACK_DARKER;
    private static final double FACTOR = 0.5;
    
    static {
        WHITE_BRIGHTER = new Color(200, 200, 200);
        WHITE_DARKER = new Color(140, 140, 140);
        BLACK_BRIGHTER = new Color(125, 125, 125);
        BLACK_DARKER = new Color(75, 75, 75);
    }
    
    public static Color brighter(final Color color) {
        if (color.equals(Color.white)) {
            return GraphicsUtil.WHITE_BRIGHTER;
        }
        if (color.equals(Color.black)) {
            return GraphicsUtil.BLACK_BRIGHTER;
        }
        int red = color.getRed();
        red += (int)((255 - red) * 0.5);
        int blue = color.getBlue();
        blue += (int)((255 - blue) * 0.5);
        int green = color.getGreen();
        green += (int)((255 - green) * 0.5);
        return new Color(Math.min(red, 255), Math.min(green, 255), Math.min(blue, 255));
    }
    
    public static Color darker(final Color color) {
        if (color.equals(Color.white)) {
            return GraphicsUtil.WHITE_DARKER;
        }
        if (color.equals(Color.black)) {
            return GraphicsUtil.BLACK_DARKER;
        }
        return color.darker();
    }
}
