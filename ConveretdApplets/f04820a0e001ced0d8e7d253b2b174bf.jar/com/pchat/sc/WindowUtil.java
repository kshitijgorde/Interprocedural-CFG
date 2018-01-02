// 
// Decompiled by Procyon v0.5.30
// 

package com.pchat.sc;

import java.awt.Color;
import java.awt.Font;
import java.net.URL;
import java.applet.Applet;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.Component;

public class WindowUtil
{
    public static void center(final Component component) {
        final Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        final Dimension size = component.getSize();
        component.setLocation(new Point((screenSize.width - size.width) / 2, (screenSize.height - size.height) / 2));
    }
    
    public static void center(final Component component, final Component component2) throws Exception {
        final Point locationOnScreen = component.getLocationOnScreen();
        final Dimension size = component.getSize();
        final Dimension size2 = component2.getSize();
        locationOnScreen.translate((size.width - size2.width) / 2, (size.height - size2.height) / 2);
        component2.setLocation(locationOnScreen);
    }
    
    public static boolean isLink(String lowerCase) {
        if (lowerCase.length() < 5) {
            return false;
        }
        lowerCase = lowerCase.toLowerCase();
        if (lowerCase.startsWith("http://") || lowerCase.startsWith("https://") || lowerCase.startsWith("ftp://") || lowerCase.startsWith("file://") || lowerCase.startsWith("www.")) {
            return true;
        }
        if (lowerCase.indexOf(".com") > 0 || lowerCase.endsWith(".org") || lowerCase.endsWith(".net") || lowerCase.endsWith(".info") || lowerCase.endsWith(".biz") || lowerCase.endsWith(".edu") || lowerCase.endsWith(".gov") || lowerCase.endsWith(".mil") || lowerCase.endsWith(".us") || lowerCase.endsWith(".uk")) {
            return true;
        }
        final int lastIndex = lowerCase.lastIndexOf(".");
        final int index = lowerCase.indexOf("@");
        return lastIndex > 0 && index > 0 && index < lastIndex;
    }
    
    public static void loadURL(final Applet applet, final String s) {
        final URL figureURL = figureURL(s);
        if (figureURL == null) {
            return;
        }
        try {
            applet.getAppletContext().showDocument(figureURL, "_blank");
        }
        catch (Exception ex) {
            System.err.println("Err8493: " + s);
        }
    }
    
    private static URL figureURL(final String s) {
        if (s == null) {
            return null;
        }
        URL url = null;
        try {
            url = new URL(s);
        }
        catch (Exception ex) {
            if (s.indexOf(64) > 0) {
                try {
                    url = new URL("mailto:" + s);
                }
                catch (Exception ex2) {
                    System.err.println("invalid URL," + s);
                }
            }
            else {
                try {
                    url = new URL("http://" + s);
                }
                catch (Exception ex3) {
                    System.err.println("invalid URL:" + s);
                }
            }
        }
        return url;
    }
    
    public static Font changeSize(final Font font, final int n) {
        return new Font(font.getName(), font.getStyle(), n);
    }
    
    public static Font changeName(final Font font, final String s) {
        return new Font(s, font.getStyle(), font.getSize());
    }
    
    public static void changeStyle(final Component component, final boolean b, final boolean b2) {
        int n;
        if (b && b2) {
            n = 3;
        }
        else if (b && !b2) {
            n = 1;
        }
        else if (!b && b2) {
            n = 2;
        }
        else {
            n = 0;
        }
        final Font font = component.getFont();
        component.setFont(new Font(font.getName(), n, font.getSize()));
    }
    
    public static Color parseColor(final String s, final Color color) {
        if (s == null) {
            return color;
        }
        try {
            return new Color(Integer.parseInt(s, 16));
        }
        catch (NumberFormatException ex) {
            return color;
        }
    }
    
    public static int parseInt(final String s, final int n) {
        if (s == null) {
            return n;
        }
        int int1;
        try {
            int1 = Integer.parseInt(s);
        }
        catch (NumberFormatException ex) {
            int1 = n;
        }
        return int1;
    }
    
    public static String clientProps(final Component component, final String s, final String s2) {
        final StringBuffer sb = new StringBuffer();
        try {
            final String s3 = "os.name";
            fillClient(sb, s3, System.getProperty(s3, ""));
            final String s4 = "os.arch";
            fillClient(sb, s4, System.getProperty(s4, ""));
            final String s5 = "os.version";
            fillClient(sb, s5, System.getProperty(s5, ""));
            final String s6 = "java.vendor";
            fillClient(sb, s6, System.getProperty(s6, ""));
            final String s7 = "java.version";
            fillClient(sb, s7, System.getProperty(s7, ""));
            fillClient(sb, "para.ver", s);
            fillClient(sb, "para.rel", s2);
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
        final Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        fillClient(sb, "screen", screenSize.width + "x" + screenSize.height);
        return sb.toString();
    }
    
    private static void fillClient(final StringBuffer sb, final String s, final String s2) {
        sb.append(s);
        sb.append(":");
        sb.append(s2);
        sb.append("\n");
    }
}
