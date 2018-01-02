// 
// Decompiled by Procyon v0.5.30
// 

package com.mindprod.common11;

import java.awt.Component;
import java.awt.LayoutManager;
import java.awt.Color;
import java.awt.TextArea;
import java.awt.Container;

public final class VersionCheck
{
    private static final boolean DEBUGGING = false;
    
    public static boolean isJavaVersionOK(final int wantedMajor, final int wantedMinor, final int wantedBugFix) {
        try {
            try {
                String ver = System.getProperty("java.version");
                if (ver == null) {
                    return false;
                }
                ver = ver.trim();
                if (ver.length() < 2) {
                    return false;
                }
                int dex = ver.indexOf(46);
                if (dex < 0) {
                    ver = ver.charAt(0) + '.' + ver.substring(1);
                    dex = 1;
                }
                final int gotMajor = Integer.parseInt(ver.substring(0, dex));
                if (gotMajor < wantedMajor) {
                    return false;
                }
                if (gotMajor > wantedMajor) {
                    return true;
                }
                ver = ver.substring(dex + 1);
                if (ver.endsWith("beta")) {
                    ver = ver.substring(0, ver.length() - "beta".length());
                }
                if (ver.endsWith("-rc")) {
                    ver = ver.substring(0, ver.length() - "-rc".length());
                }
                dex = ver.lastIndexOf(95);
                if (dex >= 0) {
                    ver = ver.substring(0, dex);
                }
                final char ch = ver.charAt(ver.length() - 1);
                if (!Character.isDigit(ch)) {
                    ver = ver.substring(0, ver.length() - 1);
                    if (ch == 'x' || ch == 'X') {
                        ver += '9';
                    }
                }
                dex = ver.indexOf(46);
                if (dex < 0) {
                    ver += ".0";
                    dex = ver.indexOf(46);
                }
                final int gotMinor = Integer.parseInt(ver.substring(0, dex));
                if (gotMinor < wantedMinor) {
                    return false;
                }
                if (gotMinor > wantedMinor) {
                    return true;
                }
                ver = ver.substring(dex + 1);
                final int gotBugFix = Integer.parseInt(ver);
                return gotBugFix >= wantedBugFix;
            }
            catch (NumberFormatException e) {
                return false;
            }
        }
        catch (StringIndexOutOfBoundsException e2) {
            return false;
        }
    }
    
    public static boolean isJavaVersionOK(final int wantedMajor, final int wantedMinor, final int wantedBugFix, final Container container) {
        if (isJavaVersionOK(wantedMajor, wantedMinor, wantedBugFix)) {
            return true;
        }
        final String error = "Error: You need Java " + wantedMajor + "." + wantedMinor + "." + wantedBugFix + " or later to run this Applet.\n" + "You are currently running under Java " + System.getProperty("java.version") + ".\n" + "Get the latest Java from http://java.com/en/index.jsp";
        final TextArea complain = new TextArea(error, 3, 42, 3);
        complain.setEditable(false);
        complain.setBackground(Color.white);
        complain.setForeground(Color.red);
        complain.setSize(300, 50);
        container.setLayout(null);
        container.add(complain);
        System.err.println(error);
        return false;
    }
}
