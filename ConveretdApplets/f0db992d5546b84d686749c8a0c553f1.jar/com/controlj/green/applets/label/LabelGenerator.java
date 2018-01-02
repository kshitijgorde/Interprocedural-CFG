// 
// Decompiled by Procyon v0.5.30
// 

package com.controlj.green.applets.label;

import com.controlj.green.applets.Rounder;
import java.util.Locale;
import java.text.DecimalFormat;
import java.text.NumberFormat;

public class LabelGenerator
{
    protected NumberFormat formater;
    protected DecimalFormat formaterExp;
    protected Locale locale;
    protected int numberOfLabels;
    protected double labelStart;
    protected double labelEnd;
    protected double increment;
    protected String[] labels;
    private int count;
    
    public LabelGenerator() {
        this.numberOfLabels = 4;
        this.count = 0;
        this.formater = NumberFormat.getNumberInstance();
        (this.formaterExp = (DecimalFormat)NumberFormat.getNumberInstance()).applyPattern("0.0######E0");
    }
    
    public LabelGenerator(final Locale loc) {
        this.numberOfLabels = 4;
        this.count = 0;
        this.setLocale(loc);
    }
    
    public String makeLabel(final double value) {
        final double compare = Math.abs(value);
        if ((compare >= 1000000.0 || compare <= 1.0E-5) && compare != 0.0) {
            return this.formaterExp.format(value);
        }
        return this.formater.format(value);
    }
    
    public void setLocale(final Locale loc) {
        this.locale = loc;
        (this.formater = NumberFormat.getNumberInstance(this.locale)).setMaximumFractionDigits(8);
        (this.formaterExp = (DecimalFormat)NumberFormat.getNumberInstance(loc)).applyPattern("0.0######E0");
    }
    
    public Locale getLocale() {
        return this.locale;
    }
    
    public String[] generateLabels(final double minValue, final double maxValue) {
        this.increment = this.calculateIncrement(minValue, maxValue);
        this.labelStart = this.calculateStartValue(minValue, maxValue);
        this.count = Math.abs((int)Math.ceil((maxValue - this.labelStart) / this.increment) + 1);
        this.labelEnd = this.labelStart + this.increment * (this.count - 1);
        this.labels = new String[this.count];
        double val = this.labelStart;
        for (int i = 0; i < this.count; ++i) {
            this.labels[i] = this.makeLabel(val);
            val += this.increment;
        }
        return this.labels;
    }
    
    protected double calculateIncrement(final double minValue, final double maxValue) {
        return Rounder.round((maxValue - minValue) / this.numberOfLabels, 0);
    }
    
    protected double calculateStartValue(final double minValue, final double maxValue) {
        return Rounder.roundDownToIncrement(minValue, this.increment);
    }
    
    public double getStartValue() {
        return this.labelStart;
    }
    
    public double getEndValue() {
        return this.labelEnd;
    }
    
    public double getIncrement() {
        return this.increment;
    }
    
    public void setBaseNumberOfLabels(final int numLabels) {
        this.numberOfLabels = numLabels;
    }
}
