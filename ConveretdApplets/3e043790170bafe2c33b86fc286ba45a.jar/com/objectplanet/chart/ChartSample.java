// 
// Decompiled by Procyon v0.5.30
// 

package com.objectplanet.chart;

import java.awt.Color;
import java.io.Serializable;

public class ChartSample implements Serializable
{
    protected Double value;
    protected String label;
    Color labelColor;
    private static final Color DEFAULT_COLOR;
    private int index;
    Object key;
    private boolean selected;
    
    public static String formatNumber(final double n, final int n2) {
        final long n3 = (long)Math.pow(10.0, n2);
        final long n4 = (long)(n * n3);
        final long n5 = Math.abs(n4) % n3 + n3;
        String s = "";
        if (n2 > 0) {
            s = "." + (n5 + "").substring(1);
        }
        long n6 = n4 / n3;
        if (n6 == 0L) {
            String s2;
            if (n < 0.0) {
                s2 = "-0" + s;
            }
            else {
                s2 = "0" + s;
            }
            return s2;
        }
        if (n6 > 0L) {
            while (n6 >= 1L) {
                final long n7 = n6 % 1000L + 1000L;
                if (n7 >= 1000L && n6 >= 1000L) {
                    s = " " + (n7 + "").substring(1) + s;
                }
                else {
                    s = n6 % 1000L + s;
                }
                n6 /= 1000L;
            }
        }
        else if (n6 < 0L) {
            while (n6 <= -1L) {
                final long n8 = n6 % 1000L - 1000L;
                if (n8 <= -1000L && n6 <= -1000L) {
                    s = " " + (n8 + "").substring(2) + s;
                }
                else {
                    s = n6 % 1000L + s;
                }
                n6 /= 1000L;
            }
        }
        return s;
    }
    
    public void set(final double n, final String label, final Object key) {
        this.value = new Double(n);
        this.label = label;
        this.key = key;
    }
    
    public void setValue(final double n) {
        this.value = new Double(n);
    }
    
    public long getValue() {
        if (this.value != null) {
            return (long)(Object)this.value;
        }
        return 0L;
    }
    
    public String toString() {
        if (this.value != null) {
            return this.label + " " + (double)this.value;
        }
        return this.label;
    }
    
    public ChartSample(final int index) {
        this.index = index;
    }
    
    public ChartSample(final int index, final long n, final String label, final Object key) {
        this.index = index;
        this.value = new Double(n);
        this.label = label;
        this.key = key;
    }
    
    public boolean isSelected() {
        return this.selected;
    }
    
    public double getFloatValue() {
        if (this.value != null) {
            return this.value;
        }
        return 0.0;
    }
    
    static {
        DEFAULT_COLOR = Color.black;
    }
    
    public void setLabelColor(final Color labelColor) {
        this.labelColor = labelColor;
    }
    
    void setIndex(final int index) {
        this.index = index;
    }
    
    public int getIndex() {
        return this.index;
    }
    
    public boolean setSelection(final boolean selected) {
        return this.selected = selected;
    }
    
    public Object getKey() {
        return this.key;
    }
    
    public boolean toggleSelection() {
        return this.selected = !this.selected;
    }
    
    public void setLabel(final String label) {
        this.label = label;
    }
    
    public String getLabel() {
        return this.label;
    }
}
