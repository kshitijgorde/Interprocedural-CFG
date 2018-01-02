// 
// Decompiled by Procyon v0.5.30
// 

package sTools;

import java.awt.event.AdjustmentEvent;
import java.awt.Insets;
import java.awt.event.AdjustmentListener;
import java.awt.Component;
import java.awt.LayoutManager;
import java.awt.BorderLayout;
import java.awt.TextField;
import java.awt.Scrollbar;
import java.awt.Adjustable;
import java.awt.Panel;

public class SSlider3 extends Panel implements Adjustable
{
    private Scrollbar scrollbar;
    private TextField valTextField;
    private double dValue;
    private double dMin;
    private double dMax;
    private String caption;
    private Format valFormat;
    private int vinset;
    private int hinset;
    
    public SSlider3() {
        this(0.0, 0.0, 1.0);
        this.dValue = 0.0;
        this.dMin = 0.0;
        this.dMax = 1.0;
    }
    
    public SSlider3(final double dValue, final double dMin, final double dMax) {
        this(0, 3, 0, 100);
        this.dValue = dValue;
        this.dMin = dMin;
        this.dMax = dMax;
    }
    
    private SSlider3(final int n, final int n2, final int n3, final int n4) {
        this.valFormat = new Format("%-+6.3g");
        this.vinset = 2;
        this.hinset = 2;
        (this.valTextField = new TextField(Integer.toString(n), 6)).setEditable(false);
        this.scrollbar = new Scrollbar(0, n, n2, n3, n4);
        this.setLayout(new BorderLayout());
        this.add(this.scrollbar, "Center");
        this.add(this.valTextField, "East");
        this.scrollbar.addAdjustmentListener(new 1());
    }
    
    public Insets getInsets() {
        return new Insets(this.vinset, this.hinset, this.vinset, this.hinset);
    }
    
    public void setVinset(final int vinset) {
        this.vinset = vinset;
        this.invalidate();
        if (this.getParent() != null) {
            this.getParent().validate();
        }
    }
    
    public int getVinset() {
        return this.vinset;
    }
    
    public void setHinset(final int hinset) {
        this.hinset = hinset;
        this.invalidate();
        if (this.getParent() != null) {
            this.getParent().validate();
        }
    }
    
    public int getHinset() {
        return this.hinset;
    }
    
    public void setDValue(final double dValue) {
        this.dValue = dValue;
        this.scrollbar.setValue((int)(this.scrollbar.getMinimum() + (this.scrollbar.getMaximum() - this.scrollbar.getMinimum() - this.scrollbar.getVisibleAmount()) * (dValue - this.dMin) / (this.dMax - this.dMin)));
    }
    
    public double getDValue() {
        return this.dValue;
    }
    
    public void setDMin(final double dMin) {
        this.dMin = dMin;
        this.scrollbar.setValue((int)((this.scrollbar.getMaximum() - this.scrollbar.getMinimum() - this.scrollbar.getVisibleAmount()) * (dMin - this.dMin) / (this.dMax - this.dMin)));
    }
    
    public double getDMin() {
        return this.dMin;
    }
    
    public void setDMax(final double dMax) {
        this.dMax = dMax;
        this.scrollbar.setValue((int)((this.scrollbar.getMaximum() - this.scrollbar.getMinimum() - this.scrollbar.getVisibleAmount()) * (dMax - this.dMin) / (this.dMax - this.dMin)));
    }
    
    public double getDMax() {
        return this.dMax;
    }
    
    public void addAdjustmentListener(final AdjustmentListener adjustmentListener) {
        this.scrollbar.addAdjustmentListener(adjustmentListener);
    }
    
    public void removeAdjustmentListener(final AdjustmentListener adjustmentListener) {
        this.scrollbar.removeAdjustmentListener(adjustmentListener);
    }
    
    public int getOrientation() {
        return this.scrollbar.getOrientation();
    }
    
    public void setOrientation(final int orientation) {
        this.scrollbar.setOrientation(orientation);
    }
    
    public int getValue() {
        return this.scrollbar.getValue();
    }
    
    public int getVisibleAmount() {
        return this.scrollbar.getVisibleAmount();
    }
    
    public int getMinimum() {
        return this.scrollbar.getMinimum();
    }
    
    public int getMaximum() {
        return this.scrollbar.getMaximum();
    }
    
    public int getUnitIncrement() {
        return this.scrollbar.getUnitIncrement();
    }
    
    public int getBlockIncrement() {
        return this.scrollbar.getBlockIncrement();
    }
    
    public void setValue(final int value) {
        this.scrollbar.setValue(value);
    }
    
    public void setVisibleAmount(final int visibleAmount) {
        this.scrollbar.setVisibleAmount(visibleAmount);
    }
    
    public void setMinimum(final int minimum) {
        this.scrollbar.setMinimum(minimum);
    }
    
    public void setMaximum(final int maximum) {
        this.scrollbar.setMaximum(maximum);
    }
    
    public void setUnitIncrement(final int unitIncrement) {
        this.scrollbar.setUnitIncrement(unitIncrement);
    }
    
    public void setBlockIncrement(final int blockIncrement) {
        this.scrollbar.setBlockIncrement(blockIncrement);
    }
    
    final /* synthetic */ Format access$571() {
        return this.valFormat;
    }
    
    final /* synthetic */ double access$371() {
        return this.dMax;
    }
    
    final /* synthetic */ double access$271() {
        return this.dMin;
    }
    
    final /* synthetic */ double access$149(final double dValue) {
        return this.dValue = dValue;
    }
    
    final /* synthetic */ double access$171() {
        return this.dValue;
    }
    
    final /* synthetic */ TextField access$471() {
        return this.valTextField;
    }
    
    final /* synthetic */ Scrollbar access$071() {
        return this.scrollbar;
    }
    
    class 1 implements AdjustmentListener
    {
        public void adjustmentValueChanged(final AdjustmentEvent adjustmentEvent) {
            SSlider3.this.access$149(SSlider3.this.access$271() + (SSlider3.this.access$371() - SSlider3.this.access$271()) * (SSlider3.this.access$071().getValue() - SSlider3.this.access$071().getMinimum()) / (SSlider3.this.access$071().getMaximum() - SSlider3.this.access$071().getMinimum() - SSlider3.this.access$071().getVisibleAmount()));
            SSlider3.this.access$471().setText(SSlider3.this.access$571().form(SSlider3.this.access$171()));
        }
    }
}
