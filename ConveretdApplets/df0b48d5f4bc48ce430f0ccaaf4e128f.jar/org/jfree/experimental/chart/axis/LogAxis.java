// 
// Decompiled by Procyon v0.5.30
// 

package org.jfree.experimental.chart.axis;

import org.jfree.chart.axis.NumberTick;
import org.jfree.ui.TextAnchor;
import java.util.ArrayList;
import java.util.List;
import org.jfree.chart.axis.AxisState;
import org.jfree.chart.plot.PlotRenderingInfo;
import java.awt.Graphics2D;
import org.jfree.chart.plot.Plot;
import org.jfree.chart.plot.ValueAxisPlot;
import org.jfree.ui.RectangleEdge;
import java.awt.geom.Rectangle2D;
import org.jfree.chart.axis.Axis;
import org.jfree.chart.event.AxisChangeEvent;
import org.jfree.data.Range;
import org.jfree.chart.axis.NumberAxis;
import java.text.NumberFormat;
import org.jfree.chart.axis.NumberTickUnit;
import org.jfree.chart.axis.ValueAxis;

public class LogAxis extends ValueAxis
{
    private double base;
    private double baseLog;
    private double smallestValue;
    private NumberTickUnit tickUnit;
    private NumberFormat numberFormatOverride;
    private int minorTickCount;
    
    public LogAxis() {
        this(null);
    }
    
    public LogAxis(final String label) {
        super(label, NumberAxis.createIntegerTickUnits());
        this.base = 10.0;
        this.baseLog = Math.log(10.0);
        this.smallestValue = 1.0E-100;
        this.setDefaultAutoRange(new Range(0.01, 1.0));
        this.tickUnit = new NumberTickUnit(1.0);
        this.minorTickCount = 10;
        this.setTickMarksVisible(false);
    }
    
    public double getBase() {
        return this.base;
    }
    
    public void setBase(final double base) {
        if (base <= 1.0) {
            throw new IllegalArgumentException("Requires 'base' > 1.0.");
        }
        this.base = base;
        this.baseLog = Math.log(base);
        this.notifyListeners(new AxisChangeEvent(this));
    }
    
    public double getSmallestValue() {
        return this.smallestValue;
    }
    
    public void setSmallestValue(final double value) {
        if (value <= 0.0) {
            throw new IllegalArgumentException("Requires 'value' > 0.0.");
        }
        this.smallestValue = value;
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
    
    public int getMinorTickCount() {
        return this.minorTickCount;
    }
    
    public void setMinorTickCount(final int count) {
        if (count <= 0) {
            throw new IllegalArgumentException("Requires 'count' > 0.");
        }
        this.minorTickCount = count;
        this.notifyListeners(new AxisChangeEvent(this));
    }
    
    public double calculateLog(final double value) {
        return Math.log(value) / this.baseLog;
    }
    
    public double calculateValue(final double log) {
        return Math.pow(this.base, log);
    }
    
    public double java2DToValue(final double java2DValue, final Rectangle2D area, final RectangleEdge edge) {
        final Range range = this.getRange();
        final double axisMin = this.calculateLog(range.getLowerBound());
        final double axisMax = this.calculateLog(range.getUpperBound());
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
        double log = 0.0;
        if (this.isInverted()) {
            log = axisMax - (java2DValue - min) / (max - min) * (axisMax - axisMin);
        }
        else {
            log = axisMin + (java2DValue - min) / (max - min) * (axisMax - axisMin);
        }
        return this.calculateValue(log);
    }
    
    public double valueToJava2D(double value, final Rectangle2D area, final RectangleEdge edge) {
        final Range range = this.getRange();
        final double axisMin = this.calculateLog(range.getLowerBound());
        final double axisMax = this.calculateLog(range.getUpperBound());
        value = this.calculateLog(value);
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
                r = this.getDefaultAutoRange();
            }
            double upper = r.getUpperBound();
            double lower = r.getLowerBound();
            final double range = upper - lower;
            final double fixedAutoRange = this.getFixedAutoRange();
            if (fixedAutoRange > 0.0) {
                lower = Math.max(upper - fixedAutoRange, this.smallestValue);
            }
            else {
                final double minRange = this.getAutoRangeMinimumSize();
                if (range < minRange) {
                    final double expand = (minRange - range) / 2.0;
                    upper += expand;
                    lower -= expand;
                }
            }
            this.setRange(new Range(lower, upper), false, false);
        }
    }
    
    public AxisState draw(final Graphics2D g2, final double cursor, final Rectangle2D plotArea, final Rectangle2D dataArea, final RectangleEdge edge, final PlotRenderingInfo plotState) {
        AxisState state = null;
        if (!this.isVisible()) {
            state = new AxisState(cursor);
            final List ticks = this.refreshTicks(g2, state, dataArea, edge);
            state.setTicks(ticks);
            return state;
        }
        state = this.drawTickMarksAndLabels(g2, cursor, plotArea, dataArea, edge);
        state = this.drawLabel(this.getLabel(), g2, plotArea, dataArea, edge, state);
        return state;
    }
    
    public List refreshTicks(final Graphics2D g2, final AxisState state, final Rectangle2D dataArea, final RectangleEdge edge) {
        List result = new ArrayList();
        if (RectangleEdge.isTopOrBottom(edge)) {
            result = this.refreshTicksHorizontal(g2, dataArea, edge);
        }
        else if (RectangleEdge.isLeftOrRight(edge)) {
            result = this.refreshTicksVertical(g2, dataArea, edge);
        }
        return result;
    }
    
    protected List refreshTicksHorizontal(final Graphics2D g2, final Rectangle2D dataArea, final RectangleEdge edge) {
        final Range range = this.getRange();
        final List ticks = new ArrayList();
        final double start = Math.floor(this.calculateLog(this.getLowerBound()));
        for (double end = Math.ceil(this.calculateLog(this.getUpperBound())), current = start; current <= end; current += this.tickUnit.getSize()) {
            final double v = this.calculateValue(current);
            if (range.contains(v)) {
                ticks.add(new NumberTick(new Double(v), this.createTickLabel(v), TextAnchor.TOP_CENTER, TextAnchor.CENTER, 0.0));
            }
            final double next = Math.pow(this.base, current + this.tickUnit.getSize());
            for (int i = 1; i < this.minorTickCount; ++i) {
                final double minorV = v + i * ((next - v) / this.minorTickCount);
                if (range.contains(minorV)) {
                    ticks.add(new NumberTick(new Double(minorV), "", TextAnchor.TOP_CENTER, TextAnchor.CENTER, 0.0));
                }
            }
        }
        return ticks;
    }
    
    protected List refreshTicksVertical(final Graphics2D g2, final Rectangle2D dataArea, final RectangleEdge edge) {
        final Range range = this.getRange();
        final List ticks = new ArrayList();
        final double start = Math.floor(this.calculateLog(this.getLowerBound()));
        for (double end = Math.ceil(this.calculateLog(this.getUpperBound())), current = start; current <= end; current += this.tickUnit.getSize()) {
            final double v = this.calculateValue(current);
            if (range.contains(v)) {
                ticks.add(new NumberTick(new Double(v), this.createTickLabel(v), TextAnchor.CENTER_RIGHT, TextAnchor.CENTER, 0.0));
            }
            final double next = Math.pow(this.base, current + this.tickUnit.getSize());
            for (int i = 1; i < this.minorTickCount; ++i) {
                final double minorV = v + i * ((next - v) / this.minorTickCount);
                if (range.contains(minorV)) {
                    ticks.add(new NumberTick(new Double(minorV), "", TextAnchor.CENTER_RIGHT, TextAnchor.CENTER, 0.0));
                }
            }
        }
        return ticks;
    }
    
    private String createTickLabel(final double value) {
        if (this.numberFormatOverride != null) {
            return this.numberFormatOverride.format(value);
        }
        return this.tickUnit.valueToString(value);
    }
}
