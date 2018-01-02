// 
// Decompiled by Procyon v0.5.30
// 

package com.pzzl.utils;

import java.awt.Color;

public class Tag
{
    public static final int RED = 1;
    public static final int BLUE = 2;
    public static final int GREEN = 3;
    public static final int COLOR = 4;
    public static final int VALUE = 5;
    public static final int NUMBER = 6;
    public static final int SQUARE = 7;
    public static final int LETTERBOX = 8;
    public static final int GRID = 9;
    public static final int CODEBREAKER = 10;
    public static final int ROW = 11;
    public static final int COLUMN = 12;
    public static final int ROWS = 13;
    public static final int COLUMNS = 14;
    String \u0101;
    String \u0102;
    
    public Tag(final int n) {
        this.\u0101 = "<" + n + ">";
        this.\u0102 = "</" + n + ">";
    }
    
    public Tag(final String s) {
        this.\u0101 = "<" + s + ">";
        this.\u0102 = "</" + s + ">";
    }
    
    public String getString(final String s) {
        final int n = s.toLowerCase().indexOf(this.\u0101, 0) + this.\u0101.length();
        final int index = s.toLowerCase().indexOf(this.\u0102);
        if (n >= 0 & index > 0) {
            return s.substring(n, index);
        }
        return "";
    }
    
    public int getInteger(final String s) {
        final int n = s.toLowerCase().indexOf(this.\u0101.toLowerCase(), 0) + this.\u0101.length();
        final int index = s.toLowerCase().indexOf(this.\u0102.toLowerCase(), 0);
        if (n >= 0 & index > 0) {
            try {
                return Integer.parseInt(s.substring(n, index));
            }
            catch (NumberFormatException ex) {
                return 0;
            }
        }
        return 0;
    }
    
    public Color getColor(final String s) {
        final int n = s.toLowerCase().indexOf(this.\u0101, 0) + this.\u0101.length();
        final int index = s.toLowerCase().indexOf(this.\u0102);
        if (n >= 0 & index > 0) {
            final String substring = s.substring(n, index);
            return new Color(new Tag(1).getInteger(substring), new Tag(3).getInteger(substring), new Tag(2).getInteger(substring));
        }
        return Color.white;
    }
    
    public String remove(String concat) {
        final int n = concat.toLowerCase().indexOf(this.\u0101, 0) + this.\u0101.length();
        final int index = concat.toLowerCase().indexOf(this.\u0102);
        if (n >= 0 & index > 0) {
            concat = concat.substring(0, n).concat(concat.substring(index + this.\u0102.length(), concat.length()));
        }
        return concat;
    }
    
    public String toString() {
        return String.valueOf(this.\u0101) + this.\u0102;
    }
    
    public String toString(final String s) {
        return String.valueOf(this.\u0101) + s + this.\u0102;
    }
    
    public String toString(final int n) {
        return String.valueOf(this.\u0101) + n + this.\u0102;
    }
    
    public String toString(final Color color) {
        return String.valueOf(this.\u0101) + new Tag(1).toString(color.getRed()) + new Tag(3).toString(color.getGreen()) + new Tag(2).toString(color.getBlue()) + this.\u0102;
    }
}
