// 
// Decompiled by Procyon v0.5.30
// 

package com.diginet.digichat.awt;

import java.awt.Font;
import com.diginet.digichat.common.ScrollPattern;

public class ScrollItem extends ScrollPattern
{
    public int x;
    public int nWidth;
    public int nHeight;
    public int yText;
    public int[] nLimits;
    public Font fntText;
    
    public ScrollItem(final int n, final String s) {
        super(n, s);
        this.nLimits = null;
        this.fntText = null;
    }
}
