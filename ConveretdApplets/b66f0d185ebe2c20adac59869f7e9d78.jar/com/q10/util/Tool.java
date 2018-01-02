// 
// Decompiled by Procyon v0.5.30
// 

package com.q10.util;

import java.awt.Font;
import java.applet.Applet;
import java.awt.Color;

public class Tool
{
    public static Color cor(final String s) {
        return new Color((s != null) ? Integer.parseInt(s, 16) : 16777215);
    }
    
    public static Font setaFonte(final Applet applet, final String s, final String s2, final String s3) {
        final String parameter = applet.getParameter(s);
        final String parameter2 = applet.getParameter(s2);
        final String parameter3 = applet.getParameter(s3);
        return new Font((parameter != null) ? parameter : applet.getFont().getName(), (parameter3 == null) ? applet.getFont().getStyle() : (parameter3.equalsIgnoreCase("plain") ? 0 : (parameter3.equalsIgnoreCase("bold") ? 1 : 2)), (parameter2 != null) ? Integer.parseInt(parameter2) : applet.getFont().getSize());
    }
    
    public static boolean strToBool(final String s) {
        return "true".equalsIgnoreCase(s);
    }
}
