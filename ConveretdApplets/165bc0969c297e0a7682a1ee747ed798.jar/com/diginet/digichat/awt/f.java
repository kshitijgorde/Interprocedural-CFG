// 
// Decompiled by Procyon v0.5.30
// 

package com.diginet.digichat.awt;

import java.awt.Color;

public class f
{
    public static Color a(final String s) {
        return new Color(Integer.parseInt(s, 16));
    }
    
    public static String a(final Color color) {
        return Integer.toString(color.getRGB() & 0xFFFFFF, 16);
    }
}
