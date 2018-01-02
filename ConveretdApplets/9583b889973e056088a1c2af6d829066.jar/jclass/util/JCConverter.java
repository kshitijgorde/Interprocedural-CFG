// 
// Decompiled by Procyon v0.5.30
// 

package jclass.util;

import java.util.Date;
import java.awt.Point;
import java.awt.Dimension;
import java.awt.Insets;
import java.awt.Font;
import java.awt.Color;
import java.awt.Image;
import java.awt.Component;
import java.applet.Applet;

public class JCConverter
{
    public String getParam(final Applet applet, final Component component, final String s, final String s2) {
        return JCUtilConverter.getParam(applet, component, s, s2);
    }
    
    public int toInt(final String s, final int n) {
        return JCUtilConverter.toInt(s, n);
    }
    
    public double toDouble(final String s, final double n) {
        return JCUtilConverter.toDouble(s, n);
    }
    
    public boolean toBoolean(final String s, final boolean b) {
        return JCUtilConverter.toBoolean(s, b);
    }
    
    public int[] toIntList(final String s, final char c, final int[] array) {
        return JCUtilConverter.toIntList(s, c, array);
    }
    
    public static String[] toStringList(final String s) {
        return JCUtilConverter.toStringList(s);
    }
    
    public JCVector toVector(final Component component, final String s, final char c, final boolean b, final JCVector jcVector) {
        return JCUtilConverter.toVector(component, s, c, b, jcVector);
    }
    
    public JCVector toVector(final Component component, final String s, final char c, final boolean b) {
        return JCUtilConverter.toVector(component, s, c, b);
    }
    
    public Object toJCString(final Component component, final String s, final Object o) {
        final Object cellValue = ConvertUtil.toCellValue(component, s, true);
        if (cellValue != null) {
            return cellValue;
        }
        return o;
    }
    
    public Image toImage(final Component component, final String s, final Image image) {
        return JCUtilConverter.toImage(component, s, image);
    }
    
    public Image toImage(final Component component, final String s) {
        return JCUtilConverter.toImage(component, s);
    }
    
    public Image[] toImageList(final Component component, final String s, final Image[] array) {
        return JCUtilConverter.toImageList(component, s, array);
    }
    
    public Color[] toColorList(final String s, final Color[] array) {
        return JCUtilConverter.toColorList(s, array);
    }
    
    public Color toColor(final String s, final Color color) {
        return JCUtilConverter.toColor(s, color);
    }
    
    public Color toColor(final String s) {
        return JCUtilConverter.toColor(s);
    }
    
    public Font toFont(final String s, final Font font) {
        return JCUtilConverter.toFont(s, font);
    }
    
    public Font toFont(final String s) {
        return JCUtilConverter.toFont(s);
    }
    
    public int toEnum(final String s, final String s2, final String[] array, final int[] array2, final int n) {
        return JCUtilConverter.toEnum(s, s2, array, array2, n);
    }
    
    public long toEnum(final String s, final String s2, final String[] array, final long[] array2, final long n) {
        return JCUtilConverter.toEnum(s, s2, array, array2, n);
    }
    
    public int[] toEnumList(final String s, final String s2, final String[] array, final int[] array2, final int[] array3) {
        return JCUtilConverter.toEnumList(s, s2, array, array2, array3);
    }
    
    public Insets toInsets(final String s, final Insets insets) {
        return JCUtilConverter.toInsets(s, insets);
    }
    
    public Dimension toDimension(final String s, final Dimension dimension) {
        return JCUtilConverter.toDimension(s, dimension);
    }
    
    public Point toPoint(final String s, final Point point) {
        return JCUtilConverter.toPoint(s, point);
    }
    
    public Date toDate(final String s, final Date date) {
        return JCUtilConverter.toDate(s, date);
    }
}
