// 
// Decompiled by Procyon v0.5.30
// 

package com.magelang;

import java.awt.Color;

public class Colors
{
    public static Color lightSkyBlue3;
    
    static {
        Colors.lightSkyBlue3 = new Color(141, 182, 205);
    }
    
    public static String getJavaInitializationString(final Color color) {
        if (color.equals(Color.black)) {
            return "java.awt.Color.black";
        }
        if (color.equals(Color.blue)) {
            return "java.awt.Color.blue";
        }
        if (color.equals(Color.cyan)) {
            return "java.awt.Color.cyan";
        }
        if (color.equals(Color.darkGray)) {
            return "java.awt.Color.darkGray";
        }
        if (color.equals(Color.gray)) {
            return "java.awt.Color.gray";
        }
        if (color.equals(Color.green)) {
            return "java.awt.Color.green";
        }
        if (color.equals(Color.lightGray)) {
            return "java.awt.Color.lightGray";
        }
        if (color.equals(Color.magenta)) {
            return "java.awt.Color.magenta";
        }
        if (color.equals(Color.orange)) {
            return "java.awt.Color.orange";
        }
        if (color.equals(Color.pink)) {
            return "java.awt.Color.pink";
        }
        if (color.equals(Color.red)) {
            return "java.awt.Color.red";
        }
        if (color.equals(Color.white)) {
            return "java.awt.Color.white";
        }
        if (color.equals(Color.yellow)) {
            return "java.awt.Color.yellow";
        }
        return String.valueOf("new java.awt.Color(") + color.getRed() + "," + color.getGreen() + "," + color.getBlue() + ")";
    }
}
