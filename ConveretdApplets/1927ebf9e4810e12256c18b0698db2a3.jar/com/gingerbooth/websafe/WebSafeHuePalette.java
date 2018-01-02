// 
// Decompiled by Procyon v0.5.30
// 

package com.gingerbooth.websafe;

import java.util.Vector;
import java.awt.Color;
import courseware.util.Palettable;

public class WebSafeHuePalette implements Palettable
{
    private static Color[] colors;
    private static Vector extraColors;
    
    public Color getColor(final int n) {
        if (WebSafeHuePalette.colors[n] == null) {
            final int[] array = WebSafeHue.hues[n];
            WebSafeHuePalette.colors[n] = new Color(array[0], array[1], array[2]);
        }
        return WebSafeHuePalette.colors[n];
    }
    
    public int getNumColors() {
        return WebSafeHue.hues.length + WebSafeHuePalette.extraColors.size();
    }
    
    public void setColor(int n, final Color color) {
        if (n < 0) {
            return;
        }
        if (n < WebSafeHue.hues.length) {
            WebSafeHuePalette.colors[n] = color;
            return;
        }
        n -= WebSafeHue.hues.length;
        if (n < WebSafeHuePalette.extraColors.size()) {
            WebSafeHuePalette.extraColors.setElementAt(color, n);
            return;
        }
        WebSafeHuePalette.extraColors.ensureCapacity(n + 1);
        WebSafeHuePalette.extraColors.setElementAt(color, n + 1);
    }
    
    static {
        WebSafeHuePalette.colors = new Color[WebSafeHue.hues.length];
        WebSafeHuePalette.extraColors = new Vector(1, 1);
    }
}
