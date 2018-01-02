// 
// Decompiled by Procyon v0.5.30
// 

package jclass.base;

import java.awt.Graphics;
import java.awt.Color;

public class Border
{
    public static final int NONE = 0;
    public static final int ETCHED_IN = 1;
    public static final int ETCHED_OUT = 2;
    public static final int IN = 3;
    public static final int OUT = 4;
    public static final int PLAIN = 5;
    public static final int FRAME_IN = 6;
    public static final int FRAME_OUT = 7;
    public static final int CONTROL_IN = 8;
    public static final int CONTROL_OUT = 9;
    public static final int NUM_BORDER_STYLES = 10;
    public static final String[] border_strings;
    public static final int[] border_values;
    private static final Color WHITE_BRIGHTER;
    private static final Color WHITE_DARKER;
    private static final Color BLACK_BRIGHTER;
    private static final Color BLACK_DARKER;
    private static final double FACTOR = 0.5;
    
    public static void drawTopLines(final Graphics graphics, final int n, final int n2, final int n3, final int n4, final int n5, final Color color) {
        graphics.setColor(color);
        for (int i = 0; i < n; ++i) {
            graphics.drawLine(n2 + i, n3 + i, n2 + n4 - (i + 1), n3 + i);
            graphics.drawLine(n2 + i, n3 + i + 1, n2 + i, n3 + n5 - (i + 1));
        }
    }
    
    public static void drawBottomLines(final Graphics graphics, final int n, final int n2, final int n3, final int n4, final int n5, final Color color) {
        graphics.setColor(color);
        for (int i = 1; i <= n; ++i) {
            graphics.drawLine(n2 + i - 1, n3 + n5 - i, n2 + n4 - i, n3 + n5 - i);
            graphics.drawLine(n2 + n4 - i, n3 + i - 1, n2 + n4 - i, n3 + n5 - i);
        }
    }
    
    public static void drawNormal(final Graphics graphics, final int n, final int n2, final int n3, final int n4, final int n5, final Color color, final Color color2) {
        drawTopLines(graphics, n, n2, n3, n4, n5, color);
        drawBottomLines(graphics, n, n2, n3, n4, n5, color2);
    }
    
    public static Color brighter(final Color color) {
        if (color.equals(Color.white)) {
            return Border.WHITE_BRIGHTER;
        }
        if (color.equals(Color.black)) {
            return Border.BLACK_BRIGHTER;
        }
        final int red = color.getRed();
        final int n = red + (int)((255 - red) * 0.5);
        final int blue = color.getBlue();
        final int n2 = blue + (int)((255 - blue) * 0.5);
        final int green = color.getGreen();
        return new Color(Math.min(n, 255), Math.min(green + (int)((255 - green) * 0.5), 255), Math.min(n2, 255));
    }
    
    public static Color darker(final Color color) {
        if (color.equals(Color.white)) {
            return Border.WHITE_DARKER;
        }
        if (color.equals(Color.black)) {
            return Border.BLACK_DARKER;
        }
        return color.darker();
    }
    
    public static void draw(final Graphics graphics, final int n, final int n2, final int n3, final int n4, final int n5, final int n6, final Color color, final Color color2) {
        draw(graphics, n, n2, n3, n4, n5, n6, brighter(color), darker(color), color2);
    }
    
    public static void draw(final Graphics graphics, final int n, final int n2, final int n3, final int n4, final int n5, final int n6, final Color color, final Color color2, final Color color3) {
        final Color color4 = graphics.getColor();
        switch (n) {
            case 5: {
                drawNormal(graphics, n2, n3, n4, n5, n6, color3, color3);
                break;
            }
            case 3: {
                drawNormal(graphics, n2, n3, n4, n5, n6, color2, color);
                break;
            }
            case 4: {
                drawNormal(graphics, n2, n3, n4, n5, n6, color, color2);
                break;
            }
            case 8: {
                if (n2 == 2) {
                    drawNormal(graphics, 1, n3 + 1, n4 + 1, n5 - 2, n6 - 2, Color.black, color);
                    drawTopLines(graphics, 1, n3, n4, n5, n6, color2);
                    break;
                }
                drawNormal(graphics, n2, n3, n4, n5, n6, color2, color);
                break;
            }
            case 9: {
                if (n2 == 2) {
                    drawNormal(graphics, 1, n3, n4, n5, n6, Color.white, Color.black);
                    drawBottomLines(graphics, 1, n3 + 1, n4 + 1, n5 - 2, n6 - 2, color2);
                    break;
                }
                drawNormal(graphics, n2, n3, n4, n5, n6, color, color2);
                break;
            }
            case 1: {
                final int n7 = n2 % 2;
                final int n8 = (n7 == 0) ? (n2 / 2) : (n2 / 2 + 1);
                final int n9 = n2 - n8;
                drawNormal(graphics, n8, n3, n4, n5, n6, color2, color);
                drawNormal(graphics, n9, n3 + n8, n4 + n8, n5 - n2 - n7, n6 - n2 - n7, color, color2);
                break;
            }
            case 2: {
                final int n10 = n2 % 2;
                final int n11 = (n10 == 0) ? (n2 / 2) : (n2 / 2 + 1);
                final int n12 = n2 - n11;
                drawNormal(graphics, n11, n3, n4, n5, n6, color, color2);
                drawNormal(graphics, n12, n3 + n11, n4 + n11, n5 - n2 - n10, n6 - n2 - n10, color2, color);
                break;
            }
            case 7: {
                drawNormal(graphics, n2, n3, n4, n5, n6, color, color2);
                drawNormal(graphics, n2 - 1, n3, n4, n5, n6, color3, color3);
                break;
            }
            case 6: {
                drawNormal(graphics, n2, n3, n4, n5, n6, color2, color);
                drawNormal(graphics, n2 - 1, n3, n4, n5, n6, color3, color3);
                break;
            }
        }
        graphics.setColor(color4);
    }
    
    static {
        border_strings = new String[] { "None", "Etched_In", "Etched_Out", "In", "Out", "Plain", "Frame_In", "Frame_Out", "Control_In", "Control_Out" };
        border_values = new int[] { 0, 1, 2, 3, 4, 5, 6, 7, 8, 9 };
        WHITE_BRIGHTER = new Color(200, 200, 200);
        WHITE_DARKER = new Color(140, 140, 140);
        BLACK_BRIGHTER = new Color(125, 125, 125);
        BLACK_DARKER = new Color(75, 75, 75);
    }
}
