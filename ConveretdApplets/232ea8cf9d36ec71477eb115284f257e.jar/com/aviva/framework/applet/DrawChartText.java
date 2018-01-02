// 
// Decompiled by Procyon v0.5.30
// 

package com.aviva.framework.applet;

public class DrawChartText
{
    private String name;
    private double value;
    
    public DrawChartText(final String txt, final double val) {
        this.name = txt;
        this.value = val;
    }
    
    public void setName(final String theName) {
        this.name = theName;
    }
    
    public void setValue(final double theValue) {
        this.value = theValue;
    }
    
    public String getName() {
        return this.name;
    }
    
    public double getValue() {
        return this.value;
    }
}
