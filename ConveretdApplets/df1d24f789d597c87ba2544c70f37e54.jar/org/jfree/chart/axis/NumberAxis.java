// 
// Decompiled by Procyon v0.5.30
// 

package org.jfree.chart.axis;

import org.jfree.util.ObjectUtils;
import org.jfree.ui.TextAnchor;
import java.util.ArrayList;
import java.awt.FontMetrics;
import java.awt.font.LineMetrics;
import java.awt.font.FontRenderContext;
import java.awt.Font;
import java.awt.Insets;
import java.util.Locale;
import java.text.DecimalFormat;
import java.util.List;
import org.jfree.chart.plot.PlotRenderingInfo;
import java.awt.Graphics2D;
import org.jfree.ui.RectangleEdge;
import java.awt.geom.Rectangle2D;
import org.jfree.chart.plot.Plot;
import org.jfree.data.Range;
import org.jfree.chart.plot.ValueAxisPlot;
import org.jfree.chart.event.AxisChangeEvent;
import java.text.NumberFormat;
import java.io.Serializable;

public class NumberAxis extends ValueAxis implements Cloneable, Serializable
{
    public static final boolean DEFAULT_AUTO_RANGE_INCLUDES_ZERO = true;
    public static final boolean DEFAULT_AUTO_RANGE_STICKY_ZERO = true;
    public static final NumberTickUnit DEFAULT_TICK_UNIT;
    public static final boolean DEFAULT_VERTICAL_TICK_LABELS = false;
    private boolean autoRangeIncludesZero;
    private boolean autoRangeStickyZero;
    private NumberTickUnit tickUnit;
    private NumberFormat numberFormatOverride;
    private MarkerAxisBand markerBand;
    
    public NumberAxis() {
        this(null);
    }
    
    public NumberAxis(final String label) {
        super(label, createStandardTickUnits());
        this.autoRangeIncludesZero = true;
        this.autoRangeStickyZero = true;
        this.tickUnit = NumberAxis.DEFAULT_TICK_UNIT;
        this.numberFormatOverride = null;
        this.markerBand = null;
    }
    
    public boolean autoRangeIncludesZero() {
        return this.autoRangeIncludesZero;
    }
    
    public void setAutoRangeIncludesZero(final boolean flag) {
        if (this.autoRangeIncludesZero != flag) {
            this.autoRangeIncludesZero = flag;
            if (this.isAutoRange()) {
                this.autoAdjustRange();
            }
            this.notifyListeners(new AxisChangeEvent(this));
        }
    }
    
    public boolean autoRangeStickyZero() {
        return this.autoRangeStickyZero;
    }
    
    public void setAutoRangeStickyZero(final boolean flag) {
        if (this.autoRangeStickyZero != flag) {
            this.autoRangeStickyZero = flag;
            if (this.isAutoRange()) {
                this.autoAdjustRange();
            }
            this.notifyListeners(new AxisChangeEvent(this));
        }
    }
    
    public NumberTickUnit getTickUnit() {
        return this.tickUnit;
    }
    
    public void setTickUnit(final NumberTickUnit unit) {
        this.setTickUnit(unit, true, true);
    }
    
    public void setTickUnit(final NumberTickUnit unit, final boolean notify, final boolean turnOffAutoSelect) {
        if (unit == null) {
            throw new IllegalArgumentException("Null 'unit' argument.");
        }
        this.tickUnit = unit;
        if (turnOffAutoSelect) {
            this.setAutoTickUnitSelection(false, false);
        }
        if (notify) {
            this.notifyListeners(new AxisChangeEvent(this));
        }
    }
    
    public NumberFormat getNumberFormatOverride() {
        return this.numberFormatOverride;
    }
    
    public void setNumberFormatOverride(final NumberFormat formatter) {
        this.numberFormatOverride = formatter;
        this.notifyListeners(new AxisChangeEvent(this));
    }
    
    public MarkerAxisBand getMarkerBand() {
        return this.markerBand;
    }
    
    public void setMarkerBand(final MarkerAxisBand band) {
        this.markerBand = band;
        this.notifyListeners(new AxisChangeEvent(this));
    }
    
    public void configure() {
        if (this.isAutoRange()) {
            this.autoAdjustRange();
        }
    }
    
    protected void autoAdjustRange() {
        final Plot plot = this.getPlot();
        if (plot == null) {
            return;
        }
        if (plot instanceof ValueAxisPlot) {
            final ValueAxisPlot vap = (ValueAxisPlot)plot;
            Range r = vap.getDataRange(this);
            if (r == null) {
                r = new Range(0.0, 1.0);
            }
            double upper = r.getUpperBound();
            double lower = r.getLowerBound();
            if (this.autoRangeIncludesZero()) {
                lower = Math.min(lower, 0.0);
                upper = Math.max(upper, 0.0);
            }
            final double range = upper - lower;
            final double fixedAutoRange = this.getFixedAutoRange();
            if (fixedAutoRange > 0.0) {
                lower = upper - fixedAutoRange;
            }
            else {
                final double minRange = this.getAutoRangeMinimumSize();
                if (range < minRange) {
                    final double expand = (minRange - range) / 2.0;
                    upper += expand;
                    lower -= expand;
                }
                if (this.autoRangeStickyZero()) {
                    if (upper <= 0.0) {
                        upper = Math.min(0.0, upper + this.getUpperMargin() * range);
                    }
                    else {
                        upper += this.getUpperMargin() * range;
                    }
                    if (lower >= 0.0) {
                        lower = Math.max(0.0, lower - this.getLowerMargin() * range);
                    }
                    else {
                        lower -= this.getLowerMargin() * range;
                    }
                }
                else {
                    upper += this.getUpperMargin() * range;
                    lower -= this.getLowerMargin() * range;
                }
            }
            this.setRange(new Range(lower, upper), false, false);
        }
    }
    
    public double translateValueToJava2D(final double value, final Rectangle2D area, final RectangleEdge edge) {
        return this.valueToJava2D(value, area, edge);
    }
    
    public double valueToJava2D(final double value, final Rectangle2D area, final RectangleEdge edge) {
        final Range range = this.getRange();
        final double axisMin = range.getLowerBound();
        final double axisMax = range.getUpperBound();
        double min = 0.0;
        double max = 0.0;
        if (RectangleEdge.isTopOrBottom(edge)) {
            min = area.getX();
            max = area.getMaxX();
        }
        else if (RectangleEdge.isLeftOrRight(edge)) {
            max = area.getMinY();
            min = area.getMaxY();
        }
        if (this.isInverted()) {
            return max - (value - axisMin) / (axisMax - axisMin) * (max - min);
        }
        return min + (value - axisMin) / (axisMax - axisMin) * (max - min);
    }
    
    public double translateJava2DToValue(final double java2DValue, final Rectangle2D area, final RectangleEdge edge) {
        return this.java2DToValue(java2DValue, area, edge);
    }
    
    public double java2DToValue(final double java2DValue, final Rectangle2D area, final RectangleEdge edge) {
        final Range range = this.getRange();
        final double axisMin = range.getLowerBound();
        final double axisMax = range.getUpperBound();
        double min = 0.0;
        double max = 0.0;
        if (RectangleEdge.isTopOrBottom(edge)) {
            min = area.getX();
            max = area.getMaxX();
        }
        else if (RectangleEdge.isLeftOrRight(edge)) {
            min = area.getMaxY();
            max = area.getY();
        }
        if (this.isInverted()) {
            return axisMax - (java2DValue - min) / (max - min) * (axisMax - axisMin);
        }
        return axisMin + (java2DValue - min) / (max - min) * (axisMax - axisMin);
    }
    
    public double calculateLowestVisibleTickValue() {
        final double unit = this.getTickUnit().getSize();
        final double index = Math.ceil(this.getRange().getLowerBound() / unit);
        return index * unit;
    }
    
    public double calculateHighestVisibleTickValue() {
        final double unit = this.getTickUnit().getSize();
        final double index = Math.floor(this.getRange().getUpperBound() / unit);
        return index * unit;
    }
    
    public int calculateVisibleTickCount() {
        final double unit = this.getTickUnit().getSize();
        final Range range = this.getRange();
        return (int)(Math.floor(range.getUpperBound() / unit) - Math.ceil(range.getLowerBound() / unit) + 1.0);
    }
    
    public AxisState draw(final Graphics2D g2, final double cursor, final Rectangle2D plotArea, final Rectangle2D dataArea, final RectangleEdge edge, final PlotRenderingInfo plotState) {
        AxisState state = null;
        if (!this.isVisible()) {
            state = new AxisState(cursor);
            final List ticks = this.refreshTicks(g2, state, plotArea, dataArea, edge);
            state.setTicks(ticks);
            return state;
        }
        state = this.drawTickMarksAndLabels(g2, cursor, plotArea, dataArea, edge);
        state = this.drawLabel(this.getLabel(), g2, plotArea, dataArea, edge, state);
        return state;
    }
    
    public static TickUnitSource createStandardTickUnits() {
        final TickUnits units = new TickUnits();
        units.add(new NumberTickUnit(1.0E-7, new DecimalFormat("0.0000000")));
        units.add(new NumberTickUnit(1.0E-6, new DecimalFormat("0.000000")));
        units.add(new NumberTickUnit(1.0E-5, new DecimalFormat("0.00000")));
        units.add(new NumberTickUnit(1.0E-4, new DecimalFormat("0.0000")));
        units.add(new NumberTickUnit(0.001, new DecimalFormat("0.000")));
        units.add(new NumberTickUnit(0.01, new DecimalFormat("0.00")));
        units.add(new NumberTickUnit(0.1, new DecimalFormat("0.0")));
        units.add(new NumberTickUnit(1.0, new DecimalFormat("0")));
        units.add(new NumberTickUnit(10.0, new DecimalFormat("0")));
        units.add(new NumberTickUnit(100.0, new DecimalFormat("0")));
        units.add(new NumberTickUnit(1000.0, new DecimalFormat("#,##0")));
        units.add(new NumberTickUnit(10000.0, new DecimalFormat("#,##0")));
        units.add(new NumberTickUnit(100000.0, new DecimalFormat("#,##0")));
        units.add(new NumberTickUnit(1000000.0, new DecimalFormat("#,###,##0")));
        units.add(new NumberTickUnit(1.0E7, new DecimalFormat("#,###,##0")));
        units.add(new NumberTickUnit(1.0E8, new DecimalFormat("#,###,##0")));
        units.add(new NumberTickUnit(1.0E9, new DecimalFormat("#,###,###,##0")));
        units.add(new NumberTickUnit(1.0E10, new DecimalFormat("#,###,###,##0")));
        units.add(new NumberTickUnit(1.0E11, new DecimalFormat("#,###,###,##0")));
        units.add(new NumberTickUnit(2.5E-7, new DecimalFormat("0.00000000")));
        units.add(new NumberTickUnit(2.5E-6, new DecimalFormat("0.0000000")));
        units.add(new NumberTickUnit(2.5E-5, new DecimalFormat("0.000000")));
        units.add(new NumberTickUnit(2.5E-4, new DecimalFormat("0.00000")));
        units.add(new NumberTickUnit(0.0025, new DecimalFormat("0.0000")));
        units.add(new NumberTickUnit(0.025, new DecimalFormat("0.000")));
        units.add(new NumberTickUnit(0.25, new DecimalFormat("0.00")));
        units.add(new NumberTickUnit(2.5, new DecimalFormat("0.0")));
        units.add(new NumberTickUnit(25.0, new DecimalFormat("0")));
        units.add(new NumberTickUnit(250.0, new DecimalFormat("0")));
        units.add(new NumberTickUnit(2500.0, new DecimalFormat("#,##0")));
        units.add(new NumberTickUnit(25000.0, new DecimalFormat("#,##0")));
        units.add(new NumberTickUnit(250000.0, new DecimalFormat("#,##0")));
        units.add(new NumberTickUnit(2500000.0, new DecimalFormat("#,###,##0")));
        units.add(new NumberTickUnit(2.5E7, new DecimalFormat("#,###,##0")));
        units.add(new NumberTickUnit(2.5E8, new DecimalFormat("#,###,##0")));
        units.add(new NumberTickUnit(2.5E9, new DecimalFormat("#,###,###,##0")));
        units.add(new NumberTickUnit(2.5E10, new DecimalFormat("#,###,###,##0")));
        units.add(new NumberTickUnit(2.5E11, new DecimalFormat("#,###,###,##0")));
        units.add(new NumberTickUnit(5.0E-7, new DecimalFormat("0.0000000")));
        units.add(new NumberTickUnit(5.0E-6, new DecimalFormat("0.000000")));
        units.add(new NumberTickUnit(5.0E-5, new DecimalFormat("0.00000")));
        units.add(new NumberTickUnit(5.0E-4, new DecimalFormat("0.0000")));
        units.add(new NumberTickUnit(0.005, new DecimalFormat("0.000")));
        units.add(new NumberTickUnit(0.05, new DecimalFormat("0.00")));
        units.add(new NumberTickUnit(0.5, new DecimalFormat("0.0")));
        units.add(new NumberTickUnit(5.0, new DecimalFormat("0")));
        units.add(new NumberTickUnit(50.0, new DecimalFormat("0")));
        units.add(new NumberTickUnit(500.0, new DecimalFormat("0")));
        units.add(new NumberTickUnit(5000.0, new DecimalFormat("#,##0")));
        units.add(new NumberTickUnit(50000.0, new DecimalFormat("#,##0")));
        units.add(new NumberTickUnit(500000.0, new DecimalFormat("#,##0")));
        units.add(new NumberTickUnit(5000000.0, new DecimalFormat("#,###,##0")));
        units.add(new NumberTickUnit(5.0E7, new DecimalFormat("#,###,##0")));
        units.add(new NumberTickUnit(5.0E8, new DecimalFormat("#,###,##0")));
        units.add(new NumberTickUnit(5.0E9, new DecimalFormat("#,###,###,##0")));
        units.add(new NumberTickUnit(5.0E10, new DecimalFormat("#,###,###,##0")));
        units.add(new NumberTickUnit(5.0E11, new DecimalFormat("#,###,###,##0")));
        return units;
    }
    
    public static TickUnitSource createIntegerTickUnits() {
        final TickUnits units = new TickUnits();
        units.add(new NumberTickUnit(1.0, new DecimalFormat("0")));
        units.add(new NumberTickUnit(2.0, new DecimalFormat("0")));
        units.add(new NumberTickUnit(5.0, new DecimalFormat("0")));
        units.add(new NumberTickUnit(10.0, new DecimalFormat("0")));
        units.add(new NumberTickUnit(20.0, new DecimalFormat("0")));
        units.add(new NumberTickUnit(50.0, new DecimalFormat("0")));
        units.add(new NumberTickUnit(100.0, new DecimalFormat("0")));
        units.add(new NumberTickUnit(200.0, new DecimalFormat("0")));
        units.add(new NumberTickUnit(500.0, new DecimalFormat("0")));
        units.add(new NumberTickUnit(1000.0, new DecimalFormat("#,##0")));
        units.add(new NumberTickUnit(2000.0, new DecimalFormat("#,##0")));
        units.add(new NumberTickUnit(5000.0, new DecimalFormat("#,##0")));
        units.add(new NumberTickUnit(10000.0, new DecimalFormat("#,##0")));
        units.add(new NumberTickUnit(20000.0, new DecimalFormat("#,##0")));
        units.add(new NumberTickUnit(50000.0, new DecimalFormat("#,##0")));
        units.add(new NumberTickUnit(100000.0, new DecimalFormat("#,##0")));
        units.add(new NumberTickUnit(200000.0, new DecimalFormat("#,##0")));
        units.add(new NumberTickUnit(500000.0, new DecimalFormat("#,##0")));
        units.add(new NumberTickUnit(1000000.0, new DecimalFormat("#,##0")));
        units.add(new NumberTickUnit(2000000.0, new DecimalFormat("#,##0")));
        units.add(new NumberTickUnit(5000000.0, new DecimalFormat("#,##0")));
        units.add(new NumberTickUnit(1.0E7, new DecimalFormat("#,##0")));
        units.add(new NumberTickUnit(2.0E7, new DecimalFormat("#,##0")));
        units.add(new NumberTickUnit(5.0E7, new DecimalFormat("#,##0")));
        units.add(new NumberTickUnit(1.0E8, new DecimalFormat("#,##0")));
        units.add(new NumberTickUnit(2.0E8, new DecimalFormat("#,##0")));
        units.add(new NumberTickUnit(5.0E8, new DecimalFormat("#,##0")));
        units.add(new NumberTickUnit(1.0E9, new DecimalFormat("#,##0")));
        units.add(new NumberTickUnit(2.0E9, new DecimalFormat("#,##0")));
        units.add(new NumberTickUnit(5.0E9, new DecimalFormat("#,##0")));
        units.add(new NumberTickUnit(1.0E10, new DecimalFormat("#,##0")));
        return units;
    }
    
    public static TickUnitSource createStandardTickUnits(final Locale locale) {
        final TickUnits units = new TickUnits();
        final NumberFormat numberFormat = NumberFormat.getNumberInstance(locale);
        units.add(new NumberTickUnit(1.0E-7, numberFormat));
        units.add(new NumberTickUnit(1.0E-6, numberFormat));
        units.add(new NumberTickUnit(1.0E-5, numberFormat));
        units.add(new NumberTickUnit(1.0E-4, numberFormat));
        units.add(new NumberTickUnit(0.001, numberFormat));
        units.add(new NumberTickUnit(0.01, numberFormat));
        units.add(new NumberTickUnit(0.1, numberFormat));
        units.add(new NumberTickUnit(1.0, numberFormat));
        units.add(new NumberTickUnit(10.0, numberFormat));
        units.add(new NumberTickUnit(100.0, numberFormat));
        units.add(new NumberTickUnit(1000.0, numberFormat));
        units.add(new NumberTickUnit(10000.0, numberFormat));
        units.add(new NumberTickUnit(100000.0, numberFormat));
        units.add(new NumberTickUnit(1000000.0, numberFormat));
        units.add(new NumberTickUnit(1.0E7, numberFormat));
        units.add(new NumberTickUnit(1.0E8, numberFormat));
        units.add(new NumberTickUnit(1.0E9, numberFormat));
        units.add(new NumberTickUnit(1.0E10, numberFormat));
        units.add(new NumberTickUnit(2.5E-7, numberFormat));
        units.add(new NumberTickUnit(2.5E-6, numberFormat));
        units.add(new NumberTickUnit(2.5E-5, numberFormat));
        units.add(new NumberTickUnit(2.5E-4, numberFormat));
        units.add(new NumberTickUnit(0.0025, numberFormat));
        units.add(new NumberTickUnit(0.025, numberFormat));
        units.add(new NumberTickUnit(0.25, numberFormat));
        units.add(new NumberTickUnit(2.5, numberFormat));
        units.add(new NumberTickUnit(25.0, numberFormat));
        units.add(new NumberTickUnit(250.0, numberFormat));
        units.add(new NumberTickUnit(2500.0, numberFormat));
        units.add(new NumberTickUnit(25000.0, numberFormat));
        units.add(new NumberTickUnit(250000.0, numberFormat));
        units.add(new NumberTickUnit(2500000.0, numberFormat));
        units.add(new NumberTickUnit(2.5E7, numberFormat));
        units.add(new NumberTickUnit(2.5E8, numberFormat));
        units.add(new NumberTickUnit(2.5E9, numberFormat));
        units.add(new NumberTickUnit(2.5E10, numberFormat));
        units.add(new NumberTickUnit(5.0E-7, numberFormat));
        units.add(new NumberTickUnit(5.0E-6, numberFormat));
        units.add(new NumberTickUnit(5.0E-5, numberFormat));
        units.add(new NumberTickUnit(5.0E-4, numberFormat));
        units.add(new NumberTickUnit(0.005, numberFormat));
        units.add(new NumberTickUnit(0.05, numberFormat));
        units.add(new NumberTickUnit(0.5, numberFormat));
        units.add(new NumberTickUnit(5.0, numberFormat));
        units.add(new NumberTickUnit(50.0, numberFormat));
        units.add(new NumberTickUnit(500.0, numberFormat));
        units.add(new NumberTickUnit(5000.0, numberFormat));
        units.add(new NumberTickUnit(50000.0, numberFormat));
        units.add(new NumberTickUnit(500000.0, numberFormat));
        units.add(new NumberTickUnit(5000000.0, numberFormat));
        units.add(new NumberTickUnit(5.0E7, numberFormat));
        units.add(new NumberTickUnit(5.0E8, numberFormat));
        units.add(new NumberTickUnit(5.0E9, numberFormat));
        units.add(new NumberTickUnit(5.0E10, numberFormat));
        return units;
    }
    
    public static TickUnitSource createIntegerTickUnits(final Locale locale) {
        final TickUnits units = new TickUnits();
        final NumberFormat numberFormat = NumberFormat.getNumberInstance(locale);
        units.add(new NumberTickUnit(1.0, numberFormat));
        units.add(new NumberTickUnit(2.0, numberFormat));
        units.add(new NumberTickUnit(5.0, numberFormat));
        units.add(new NumberTickUnit(10.0, numberFormat));
        units.add(new NumberTickUnit(20.0, numberFormat));
        units.add(new NumberTickUnit(50.0, numberFormat));
        units.add(new NumberTickUnit(100.0, numberFormat));
        units.add(new NumberTickUnit(200.0, numberFormat));
        units.add(new NumberTickUnit(500.0, numberFormat));
        units.add(new NumberTickUnit(1000.0, numberFormat));
        units.add(new NumberTickUnit(2000.0, numberFormat));
        units.add(new NumberTickUnit(5000.0, numberFormat));
        units.add(new NumberTickUnit(10000.0, numberFormat));
        units.add(new NumberTickUnit(20000.0, numberFormat));
        units.add(new NumberTickUnit(50000.0, numberFormat));
        units.add(new NumberTickUnit(100000.0, numberFormat));
        units.add(new NumberTickUnit(200000.0, numberFormat));
        units.add(new NumberTickUnit(500000.0, numberFormat));
        units.add(new NumberTickUnit(1000000.0, numberFormat));
        units.add(new NumberTickUnit(2000000.0, numberFormat));
        units.add(new NumberTickUnit(5000000.0, numberFormat));
        units.add(new NumberTickUnit(1.0E7, numberFormat));
        units.add(new NumberTickUnit(2.0E7, numberFormat));
        units.add(new NumberTickUnit(5.0E7, numberFormat));
        units.add(new NumberTickUnit(1.0E8, numberFormat));
        units.add(new NumberTickUnit(2.0E8, numberFormat));
        units.add(new NumberTickUnit(5.0E8, numberFormat));
        units.add(new NumberTickUnit(1.0E9, numberFormat));
        units.add(new NumberTickUnit(2.0E9, numberFormat));
        units.add(new NumberTickUnit(5.0E9, numberFormat));
        units.add(new NumberTickUnit(1.0E10, numberFormat));
        return units;
    }
    
    protected double estimateMaximumTickLabelHeight(final Graphics2D g2) {
        final Insets tickLabelInsets = this.getTickLabelInsets();
        double result = tickLabelInsets.top + tickLabelInsets.bottom;
        final Font tickLabelFont = this.getTickLabelFont();
        final FontRenderContext frc = g2.getFontRenderContext();
        result += tickLabelFont.getLineMetrics("123", frc).getHeight();
        return result;
    }
    
    protected double estimateMaximumTickLabelWidth(final Graphics2D g2, final TickUnit tickUnit) {
        final Insets tickLabelInsets = this.getTickLabelInsets();
        double result = tickLabelInsets.left + tickLabelInsets.right;
        if (this.isVerticalTickLabels()) {
            final FontRenderContext frc = g2.getFontRenderContext();
            final LineMetrics lm = this.getTickLabelFont().getLineMetrics("0", frc);
            result += lm.getHeight();
        }
        else {
            final FontMetrics fm = g2.getFontMetrics(this.getTickLabelFont());
            final Range range = this.getRange();
            final double lower = range.getLowerBound();
            final double upper = range.getUpperBound();
            final String lowerStr = tickUnit.valueToString(lower);
            final String upperStr = tickUnit.valueToString(upper);
            final double w1 = fm.stringWidth(lowerStr);
            final double w2 = fm.stringWidth(upperStr);
            result += Math.max(w1, w2);
        }
        return result;
    }
    
    protected void selectAutoTickUnit(final Graphics2D g2, final Rectangle2D drawArea, final Rectangle2D dataArea, final RectangleEdge edge) {
        if (RectangleEdge.isTopOrBottom(edge)) {
            this.selectHorizontalAutoTickUnit(g2, drawArea, dataArea, edge);
        }
        else if (RectangleEdge.isLeftOrRight(edge)) {
            this.selectVerticalAutoTickUnit(g2, drawArea, dataArea, edge);
        }
    }
    
    protected void selectHorizontalAutoTickUnit(final Graphics2D g2, final Rectangle2D drawArea, final Rectangle2D dataArea, final RectangleEdge edge) {
        final double zero = this.translateValueToJava2D(0.0, dataArea, edge);
        double tickLabelWidth = this.estimateMaximumTickLabelWidth(g2, this.getTickUnit());
        final TickUnitSource tickUnits = this.getStandardTickUnits();
        final TickUnit unit1 = tickUnits.getCeilingTickUnit(this.getTickUnit());
        final double x1 = this.translateValueToJava2D(unit1.getSize(), dataArea, edge);
        final double unit1Width = Math.abs(x1 - zero);
        final double guess = tickLabelWidth / unit1Width * unit1.getSize();
        NumberTickUnit unit2 = (NumberTickUnit)tickUnits.getCeilingTickUnit(guess);
        final double x2 = this.translateValueToJava2D(unit2.getSize(), dataArea, edge);
        final double unit2Width = Math.abs(x2 - zero);
        tickLabelWidth = this.estimateMaximumTickLabelWidth(g2, unit2);
        if (tickLabelWidth > unit2Width) {
            unit2 = (NumberTickUnit)tickUnits.getLargerTickUnit(unit2);
        }
        this.setTickUnit(unit2, false, false);
    }
    
    protected void selectVerticalAutoTickUnit(final Graphics2D g2, final Rectangle2D plotArea, final Rectangle2D dataArea, final RectangleEdge edge) {
        final double zero = this.translateValueToJava2D(0.0, dataArea, edge);
        double tickLabelHeight = this.estimateMaximumTickLabelHeight(g2);
        final TickUnitSource tickUnits = this.getStandardTickUnits();
        final TickUnit unit1 = tickUnits.getCeilingTickUnit(this.getTickUnit());
        final double y = this.translateValueToJava2D(unit1.getSize(), dataArea, edge);
        final double unitHeight = Math.abs(y - zero);
        final double guess = tickLabelHeight / unitHeight * unit1.getSize();
        NumberTickUnit unit2 = (NumberTickUnit)tickUnits.getCeilingTickUnit(guess);
        final double y2 = this.translateValueToJava2D(unit2.getSize(), dataArea, edge);
        final double unit2Height = Math.abs(y2 - zero);
        tickLabelHeight = this.estimateMaximumTickLabelHeight(g2);
        if (tickLabelHeight > unit2Height) {
            unit2 = (NumberTickUnit)tickUnits.getLargerTickUnit(unit2);
        }
        this.setTickUnit(unit2, false, false);
    }
    
    public List refreshTicks(final Graphics2D g2, final AxisState state, final Rectangle2D plotArea, final Rectangle2D dataArea, final RectangleEdge edge) {
        List result = new ArrayList();
        if (RectangleEdge.isTopOrBottom(edge)) {
            result = this.refreshHorizontalTicks(g2, state.getCursor(), plotArea, dataArea, edge);
        }
        else if (RectangleEdge.isLeftOrRight(edge)) {
            result = this.refreshVerticalTicks(g2, state.getCursor(), plotArea, dataArea, edge);
        }
        return result;
    }
    
    protected List refreshHorizontalTicks(final Graphics2D g2, final double cursor, final Rectangle2D plotArea, final Rectangle2D dataArea, final RectangleEdge edge) {
        final List result = new ArrayList();
        final Font tickLabelFont = this.getTickLabelFont();
        g2.setFont(tickLabelFont);
        if (this.isAutoTickUnitSelection()) {
            this.selectAutoTickUnit(g2, plotArea, dataArea, edge);
        }
        final double size = this.getTickUnit().getSize();
        final int count = this.calculateVisibleTickCount();
        final double lowestTickValue = this.calculateLowestVisibleTickValue();
        if (count <= 500) {
            for (int i = 0; i < count; ++i) {
                final double currentTickValue = lowestTickValue + i * size;
                final NumberFormat formatter = this.getNumberFormatOverride();
                String tickLabel;
                if (formatter != null) {
                    tickLabel = formatter.format(currentTickValue);
                }
                else {
                    tickLabel = this.getTickUnit().valueToString(currentTickValue);
                }
                TextAnchor anchor = null;
                TextAnchor rotationAnchor = null;
                double angle = 0.0;
                if (this.isVerticalTickLabels()) {
                    anchor = TextAnchor.CENTER_RIGHT;
                    rotationAnchor = TextAnchor.CENTER_RIGHT;
                    if (edge == RectangleEdge.TOP) {
                        angle = 1.5707963267948966;
                    }
                    else {
                        angle = -1.5707963267948966;
                    }
                }
                else if (edge == RectangleEdge.TOP) {
                    anchor = TextAnchor.BOTTOM_CENTER;
                    rotationAnchor = TextAnchor.BOTTOM_CENTER;
                }
                else {
                    anchor = TextAnchor.TOP_CENTER;
                    rotationAnchor = TextAnchor.TOP_CENTER;
                }
                final Tick tick = new NumberTick(new Double(currentTickValue), tickLabel, anchor, rotationAnchor, angle);
                result.add(tick);
            }
        }
        return result;
    }
    
    protected List refreshVerticalTicks(final Graphics2D g2, final double cursor, final Rectangle2D plotArea, final Rectangle2D dataArea, final RectangleEdge edge) {
        final List result = new ArrayList();
        result.clear();
        final Font tickLabelFont = this.getTickLabelFont();
        g2.setFont(tickLabelFont);
        if (this.isAutoTickUnitSelection()) {
            this.selectAutoTickUnit(g2, plotArea, dataArea, edge);
        }
        final double size = this.getTickUnit().getSize();
        final int count = this.calculateVisibleTickCount();
        final double lowestTickValue = this.calculateLowestVisibleTickValue();
        if (count <= 500) {
            for (int i = 0; i < count; ++i) {
                final double currentTickValue = lowestTickValue + i * size;
                final NumberFormat formatter = this.getNumberFormatOverride();
                String tickLabel;
                if (formatter != null) {
                    tickLabel = formatter.format(currentTickValue);
                }
                else {
                    tickLabel = this.getTickUnit().valueToString(currentTickValue);
                }
                TextAnchor anchor = null;
                TextAnchor rotationAnchor = null;
                double angle = 0.0;
                if (this.isVerticalTickLabels()) {
                    if (edge == RectangleEdge.LEFT) {
                        anchor = TextAnchor.BOTTOM_CENTER;
                        rotationAnchor = TextAnchor.BOTTOM_CENTER;
                        angle = -1.5707963267948966;
                    }
                    else {
                        anchor = TextAnchor.BOTTOM_CENTER;
                        rotationAnchor = TextAnchor.BOTTOM_CENTER;
                        angle = 1.5707963267948966;
                    }
                }
                else if (edge == RectangleEdge.LEFT) {
                    anchor = TextAnchor.CENTER_RIGHT;
                    rotationAnchor = TextAnchor.CENTER_RIGHT;
                }
                else {
                    anchor = TextAnchor.CENTER_LEFT;
                    rotationAnchor = TextAnchor.CENTER_LEFT;
                }
                final Tick tick = new NumberTick(new Double(currentTickValue), tickLabel, anchor, rotationAnchor, angle);
                result.add(tick);
            }
        }
        return result;
    }
    
    public Object clone() throws CloneNotSupportedException {
        final NumberAxis clone = (NumberAxis)super.clone();
        if (this.numberFormatOverride != null) {
            clone.numberFormatOverride = (NumberFormat)this.numberFormatOverride.clone();
        }
        return clone;
    }
    
    public boolean equals(final Object object) {
        if (object == null) {
            return false;
        }
        if (object == this) {
            return true;
        }
        if (object instanceof NumberAxis && super.equals(object)) {
            final NumberAxis axis = (NumberAxis)object;
            final boolean b0 = this.autoRangeIncludesZero == axis.autoRangeIncludesZero;
            final boolean b2 = this.autoRangeStickyZero == axis.autoRangeStickyZero;
            final boolean b3 = ObjectUtils.equal(this.tickUnit, axis.tickUnit);
            final boolean b4 = ObjectUtils.equal(this.numberFormatOverride, axis.numberFormatOverride);
            return b0 && b2 && b3 && b4;
        }
        return false;
    }
    
    static {
        DEFAULT_TICK_UNIT = new NumberTickUnit(1.0, new DecimalFormat("0"));
    }
}
