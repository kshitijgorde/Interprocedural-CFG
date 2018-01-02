// 
// Decompiled by Procyon v0.5.30
// 

package com.gingerbooth.websafe;

import java.awt.Color;
import courseware.util.Palettable;

public class SubsetPalette implements Palettable
{
    private Palettable palette;
    private int[] perturb;
    
    public SubsetPalette(final Palettable palette, final int[] perturb) {
        this.palette = palette;
        this.perturb = perturb;
    }
    
    public Color getColor(final int n) {
        return this.palette.getColor(n);
    }
    
    public int getNumColors() {
        return this.perturb.length;
    }
    
    public void setColor(final int n, final Color color) {
        this.palette.setColor(n, color);
    }
    
    public Color getOrderedColor(final int n) {
        return this.palette.getColor(this.perturb[n]);
    }
    
    public int getOrderedID(final int n) {
        return this.perturb[n];
    }
    
    public int getOrder(final int n) {
        int n2 = -1;
        for (int i = 0; i < this.perturb.length; ++i) {
            if (this.perturb[i] == n) {
                n2 = i;
            }
        }
        return n2;
    }
}
