// 
// Decompiled by Procyon v0.5.30
// 

package com.diginet.digichat.awt;

import java.awt.FontMetrics;
import java.awt.Dimension;
import java.awt.Label;

public class b2 extends Label
{
    private Dimension a;
    private String[] b;
    
    private final Dimension a() {
        if (this.a != null) {
            return this.a;
        }
        Dimension dimension = null;
        if (this.b != null && this.b.length > 0) {
            int n = -1;
            final FontMetrics fontMetrics = this.getFontMetrics(this.getFont());
            for (int i = 0; i < this.b.length; ++i) {
                final int stringWidth = fontMetrics.stringWidth(this.b[i]);
                if (stringWidth > n) {
                    n = stringWidth;
                }
            }
            if (n > -1) {
                dimension = new Dimension(n + 4, fontMetrics.getHeight());
            }
        }
        return dimension;
    }
    
    public final Dimension size() {
        return this.a();
    }
    
    public final Dimension minimumSize() {
        return this.a();
    }
    
    public final Dimension preferredSize() {
        return this.a();
    }
    
    public b2(final String s) {
        String[] b = null;
        if (s != null) {
            b = new String[] { s };
        }
        this.b = b;
    }
    
    public b2(final String[] b) {
        this.b = b;
    }
}
