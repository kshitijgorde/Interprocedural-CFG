// 
// Decompiled by Procyon v0.5.30
// 

package com.controlj.green.applets.label;

import java.awt.Graphics;
import java.awt.Font;

public class TextState
{
    Font f;
    StringBuffer s;
    int x;
    int y;
    
    public TextState() {
        this.f = null;
        this.s = null;
        this.x = 0;
        this.y = 0;
        this.s = new StringBuffer();
    }
    
    public TextState copyAll() {
        final TextState tmp = this.copyState();
        if (this.s.length() == 0) {
            return tmp;
        }
        for (int i = 0; i < this.s.length(); ++i) {
            tmp.s.append(this.s.charAt(i));
        }
        return tmp;
    }
    
    public TextState copyState() {
        final TextState tmp = new TextState();
        tmp.f = this.f;
        tmp.x = this.x;
        tmp.y = this.y;
        return tmp;
    }
    
    public String toString() {
        return this.s.toString();
    }
    
    public boolean isEmpty() {
        return this.s.length() == 0;
    }
    
    public int getWidth(final Graphics g) {
        if (g == null || this.f == null || this.s.length() == 0) {
            return 0;
        }
        return g.getFontMetrics(this.f).stringWidth(this.s.toString());
    }
    
    public int getHeight(final Graphics g) {
        if (g == null || this.f == null) {
            return 0;
        }
        return g.getFontMetrics(this.f).getHeight();
    }
    
    public int getAscent(final Graphics g) {
        if (g == null || this.f == null) {
            return 0;
        }
        return g.getFontMetrics(this.f).getAscent();
    }
    
    public int getDescent(final Graphics g) {
        if (g == null || this.f == null) {
            return 0;
        }
        return g.getFontMetrics(this.f).getDescent();
    }
    
    public int getMaxAscent(final Graphics g) {
        if (g == null || this.f == null) {
            return 0;
        }
        return g.getFontMetrics(this.f).getMaxAscent();
    }
    
    public int getMaxDescent(final Graphics g) {
        if (g == null || this.f == null) {
            return 0;
        }
        return g.getFontMetrics(this.f).getMaxDescent();
    }
    
    public int getLeading(final Graphics g) {
        if (g == null || this.f == null) {
            return 0;
        }
        return g.getFontMetrics(this.f).getLeading();
    }
}
