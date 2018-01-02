// 
// Decompiled by Procyon v0.5.30
// 

package com.eventim.applet.a;

import java.util.StringTokenizer;
import com.eventim.common.transfer.saalplan.TextDetails;
import java.awt.geom.Rectangle2D;
import java.awt.Rectangle;
import com.eventim.common.transfer.saalplan.PixPoint;
import java.awt.Color;
import java.util.Hashtable;

public final class n
{
    static {
        new Hashtable<Color, Color>().put(Color.black, Color.lightGray);
    }
    
    public static Color a(Color color) {
        final Color color2 = color;
        final float n = 0.2f;
        color = color2;
        final float[] array = new float[3];
        Color.RGBtoHSB(color.getRed(), color.getGreen(), color.getBlue(), array);
        return Color.getHSBColor(array[0], Math.max(array[1] * n, 0.0f), array[2]);
    }
    
    public static Rectangle a(final PixPoint[] array) {
        int min2;
        int min = min2 = Integer.MAX_VALUE;
        int max2;
        int max = max2 = Integer.MIN_VALUE;
        for (int i = 0; i < array.length; ++i) {
            min2 = Math.min(min2, array[i].x);
            min = Math.min(min, array[i].y);
            max2 = Math.max(max2, array[i].x);
            max = Math.max(max, array[i].y);
        }
        return new Rectangle(min2, min, max2 - min2, max - min);
    }
    
    public static Rectangle2D b(final PixPoint[] array) {
        double min2;
        double min = min2 = Double.MAX_VALUE;
        double max2;
        double max = max2 = Double.MIN_VALUE;
        for (int i = 0; i < array.length; ++i) {
            min2 = Math.min(min2, array[i].x);
            min = Math.min(min, array[i].y);
            max2 = Math.max(max2, array[i].x);
            max = Math.max(max, array[i].y);
        }
        return new Rectangle2D.Double(min2, min, max2 - min2, max - min);
    }
    
    public static TextDetails[] a(final TextDetails textDetails) {
        if (textDetails != null && textDetails.text != null) {
            final StringTokenizer stringTokenizer = new StringTokenizer(textDetails.text, "\r\n");
            final short x = textDetails.coords[0].x;
            short y = textDetails.coords[0].y;
            final TextDetails[] array = new TextDetails[stringTokenizer.countTokens()];
            int n = 0;
            while (stringTokenizer.hasMoreElements()) {
                array[n] = new TextDetails(new PixPoint[] { new PixPoint(x, y) }, textDetails.horizontal, textDetails.textColor, textDetails.bgColor, textDetails.transparent, stringTokenizer.nextToken(), textDetails.bold, textDetails.italic, textDetails.logicalFont, textDetails.size);
                y += (short)Math.round(textDetails.size * 0.7f);
                ++n;
            }
            return array;
        }
        return null;
    }
}
