// 
// Decompiled by Procyon v0.5.30
// 

package org.jfree.chart.axis;

import java.text.NumberFormat;
import java.awt.Font;
import org.jfree.ui.TextAnchor;
import org.jfree.text.TextUtilities;
import java.util.ArrayList;
import org.jfree.chart.plot.Plot;
import org.jfree.data.Range;
import org.jfree.chart.plot.ValueAxisPlot;
import java.util.Iterator;
import java.awt.Color;
import java.awt.BasicStroke;
import java.util.Vector;
import java.awt.Shape;
import org.jfree.chart.plot.PlotRenderingInfo;
import org.jfree.ui.RectangleEdge;
import java.awt.geom.Rectangle2D;
import java.awt.Graphics2D;
import org.jfree.chart.event.AxisChangeEvent;
import java.util.Arrays;
import java.util.List;
import java.awt.Paint;
import java.io.Serializable;

public class SymbolicAxis extends NumberAxis implements Serializable
{
    public static final Paint DEFAULT_SYMBOLIC_GRID_LINE_PAINT;
    private List symbolicValue;
    private List symbolicGridLineList;
    private transient Paint symbolicGridPaint;
    private boolean symbolicGridLinesVisible;
    
    public SymbolicAxis(final String label, final String[] sv) {
        super(label);
        this.symbolicGridLineList = null;
        this.symbolicValue = Arrays.asList(sv);
        this.symbolicGridLinesVisible = true;
        this.symbolicGridPaint = SymbolicAxis.DEFAULT_SYMBOLIC_GRID_LINE_PAINT;
        this.setAutoTickUnitSelection(false, false);
        this.setAutoRangeStickyZero(false);
    }
    
    public String[] getSymbolicValue() {
        String[] strToReturn = new String[this.symbolicValue.size()];
        strToReturn = this.symbolicValue.toArray(strToReturn);
        return strToReturn;
    }
    
    public Paint getSymbolicGridPaint() {
        return this.symbolicGridPaint;
    }
    
    public boolean isGridLinesVisible() {
        return this.symbolicGridLinesVisible;
    }
    
    public void setSymbolicGridLinesVisible(final boolean flag) {
        if (this.symbolicGridLinesVisible != flag) {
            this.symbolicGridLinesVisible = flag;
            this.notifyListeners(new AxisChangeEvent(this));
        }
    }
    
    protected void selectAutoTickUnit(final Graphics2D g2, final Rectangle2D drawArea, final Rectangle2D plotArea) {
        throw new UnsupportedOperationException();
    }
    
    public AxisState draw(final Graphics2D g2, final double cursor, final Rectangle2D plotArea, final Rectangle2D dataArea, final RectangleEdge edge, final PlotRenderingInfo plotState) {
        AxisState info = new AxisState(cursor);
        if (this.isVisible()) {
            info = super.draw(g2, cursor, plotArea, dataArea, edge, plotState);
        }
        if (this.symbolicGridLinesVisible) {
            this.drawSymbolicGridLines(g2, plotArea, dataArea, edge, info.getTicks());
        }
        return info;
    }
    
    public void drawSymbolicGridLines(final Graphics2D g2, final Rectangle2D plotArea, final Rectangle2D dataArea, final RectangleEdge edge, final List ticks) {
        final Shape savedClip = g2.getClip();
        g2.clip(dataArea);
        if (RectangleEdge.isTopOrBottom(edge)) {
            this.drawSymbolicGridLinesHorizontal(g2, plotArea, dataArea, true, ticks);
        }
        else if (RectangleEdge.isLeftOrRight(edge)) {
            this.drawSymbolicGridLinesVertical(g2, plotArea, dataArea, true, ticks);
        }
        g2.setClip(savedClip);
    }
    
    public void drawSymbolicGridLinesHorizontal(final Graphics2D g2, final Rectangle2D plotArea, final Rectangle2D dataArea, final boolean firstGridLineIsDark, final List ticks) {
        this.symbolicGridLineList = new Vector(ticks.size());
        boolean currentGridLineIsDark = firstGridLineIsDark;
        final double yy = dataArea.getY();
        double outlineStrokeWidth;
        if (this.getPlot().getOutlineStroke() != null) {
            outlineStrokeWidth = ((BasicStroke)this.getPlot().getOutlineStroke()).getLineWidth();
        }
        else {
            outlineStrokeWidth = 1.0;
        }
        for (final ValueTick tick : ticks) {
            final double xx1 = this.valueToJava2D(tick.getValue() - 0.5, dataArea, RectangleEdge.BOTTOM);
            final double xx2 = this.valueToJava2D(tick.getValue() + 0.5, dataArea, RectangleEdge.BOTTOM);
            if (currentGridLineIsDark) {
                g2.setPaint(this.symbolicGridPaint);
            }
            else {
                g2.setPaint(Color.white);
            }
            final Rectangle2D symbolicGridLine = new Rectangle2D.Double(xx1, yy + outlineStrokeWidth, xx2 - xx1, dataArea.getMaxY() - yy - outlineStrokeWidth);
            g2.fill(symbolicGridLine);
            this.symbolicGridLineList.add(symbolicGridLine);
            currentGridLineIsDark = !currentGridLineIsDark;
        }
        g2.setPaintMode();
    }
    
    public Rectangle2D.Double getSymbolicGridLine(final int position) {
        if (this.symbolicGridLineList != null) {
            return this.symbolicGridLineList.get(position);
        }
        return null;
    }
    
    protected void autoAdjustRange() {
        final Plot plot = this.getPlot();
        if (plot == null) {
            return;
        }
        if (plot instanceof ValueAxisPlot) {
            double upper = this.symbolicValue.size() - 1;
            double lower = 0.0;
            final double range = upper - lower;
            final double minRange = this.getAutoRangeMinimumSize();
            if (range < minRange) {
                upper = (upper + lower + minRange) / 2.0;
                lower = (upper + lower - minRange) / 2.0;
            }
            final double upperMargin = 0.5;
            final double lowerMargin = 0.5;
            if (this.autoRangeIncludesZero()) {
                if (this.autoRangeStickyZero()) {
                    if (upper <= 0.0) {
                        upper = 0.0;
                    }
                    else {
                        upper += upperMargin;
                    }
                    if (lower >= 0.0) {
                        lower = 0.0;
                    }
                    else {
                        lower -= lowerMargin;
                    }
                }
                else {
                    upper = Math.max(0.0, upper + upperMargin);
                    lower = Math.min(0.0, lower - lowerMargin);
                }
            }
            else if (this.autoRangeStickyZero()) {
                if (upper <= 0.0) {
                    upper = Math.min(0.0, upper + upperMargin);
                }
                else {
                    upper += upperMargin * range;
                }
                if (lower >= 0.0) {
                    lower = Math.max(0.0, lower - lowerMargin);
                }
                else {
                    lower -= lowerMargin;
                }
            }
            else {
                upper += upperMargin;
                lower -= lowerMargin;
            }
            this.setRange(new Range(lower, upper), false, false);
        }
    }
    
    public List refreshTicks(final Graphics2D g2, final AxisState state, final Rectangle2D plotArea, final Rectangle2D dataArea, final RectangleEdge edge) {
        List ticks = null;
        if (RectangleEdge.isTopOrBottom(edge)) {
            ticks = this.refreshTicksHorizontal(g2, state.getCursor(), plotArea, dataArea, edge);
        }
        else if (RectangleEdge.isLeftOrRight(edge)) {
            ticks = this.refreshTicksVertical(g2, state.getCursor(), plotArea, dataArea, edge);
        }
        return ticks;
    }
    
    public List refreshTicksHorizontal(final Graphics2D g2, final double cursor, final Rectangle2D plotArea, final Rectangle2D dataArea, final RectangleEdge edge) {
        final List ticks = new ArrayList();
        final Font tickLabelFont = this.getTickLabelFont();
        g2.setFont(tickLabelFont);
        final double size = this.getTickUnit().getSize();
        final int count = this.calculateVisibleTickCount();
        final double lowestTickValue = this.calculateLowestVisibleTickValue();
        double previousDrawnTickLabelPos = 0.0;
        double previousDrawnTickLabelLength = 0.0;
        if (count <= 500) {
            for (int i = 0; i < count; ++i) {
                final double currentTickValue = lowestTickValue + i * size;
                final double xx = this.valueToJava2D(currentTickValue, dataArea, edge);
                final NumberFormat formatter = this.getNumberFormatOverride();
                String tickLabel;
                if (formatter != null) {
                    tickLabel = formatter.format(currentTickValue);
                }
                else {
                    tickLabel = this.valueToString(currentTickValue);
                }
                final Rectangle2D bounds = TextUtilities.getTextBounds(tickLabel, g2, g2.getFontMetrics());
                final double tickLabelLength = this.isVerticalTickLabels() ? bounds.getHeight() : bounds.getWidth();
                boolean tickLabelsOverlapping = false;
                if (i > 0) {
                    final double avgTickLabelLength = (previousDrawnTickLabelLength + tickLabelLength) / 2.0;
                    if (Math.abs(xx - previousDrawnTickLabelPos) < avgTickLabelLength) {
                        tickLabelsOverlapping = true;
                    }
                }
                if (tickLabelsOverlapping) {
                    tickLabel = "";
                }
                else {
                    previousDrawnTickLabelPos = xx;
                    previousDrawnTickLabelLength = tickLabelLength;
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
                ticks.add(tick);
            }
        }
        return ticks;
    }
    
    public List refreshTicksVertical(final Graphics2D g2, final double cursor, final Rectangle2D plotArea, final Rectangle2D dataArea, final RectangleEdge edge) {
        final List ticks = new ArrayList();
        final Font tickLabelFont = this.getTickLabelFont();
        g2.setFont(tickLabelFont);
        final double size = this.getTickUnit().getSize();
        final int count = this.calculateVisibleTickCount();
        final double lowestTickValue = this.calculateLowestVisibleTickValue();
        double previousDrawnTickLabelPos = 0.0;
        double previousDrawnTickLabelLength = 0.0;
        if (count <= 500) {
            for (int i = 0; i < count; ++i) {
                final double currentTickValue = lowestTickValue + i * size;
                final double yy = this.valueToJava2D(currentTickValue, dataArea, edge);
                final NumberFormat formatter = this.getNumberFormatOverride();
                String tickLabel;
                if (formatter != null) {
                    tickLabel = formatter.format(currentTickValue);
                }
                else {
                    tickLabel = this.valueToString(currentTickValue);
                }
                final Rectangle2D bounds = TextUtilities.getTextBounds(tickLabel, g2, g2.getFontMetrics());
                final double tickLabelLength = this.isVerticalTickLabels() ? bounds.getWidth() : bounds.getHeight();
                boolean tickLabelsOverlapping = false;
                if (i > 0) {
                    final double avgTickLabelLength = (previousDrawnTickLabelLength + tickLabelLength) / 2.0;
                    if (Math.abs(yy - previousDrawnTickLabelPos) < avgTickLabelLength) {
                        tickLabelsOverlapping = true;
                    }
                    if (tickLabelsOverlapping) {
                        tickLabel = "";
                    }
                    else {
                        previousDrawnTickLabelPos = yy;
                        previousDrawnTickLabelLength = tickLabelLength;
                    }
                }
                TextAnchor anchor = null;
                TextAnchor rotationAnchor = null;
                double angle = 0.0;
                if (this.isVerticalTickLabels()) {
                    anchor = TextAnchor.BOTTOM_CENTER;
                    rotationAnchor = TextAnchor.BOTTOM_CENTER;
                    if (edge == RectangleEdge.LEFT) {
                        angle = -1.5707963267948966;
                    }
                    else {
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
                ticks.add(tick);
            }
        }
        return ticks;
    }
    
    public String valueToString(final double value) {
        String strToReturn;
        try {
            strToReturn = this.symbolicValue.get((int)value);
        }
        catch (IndexOutOfBoundsException ex) {
            strToReturn = new String("");
        }
        return strToReturn;
    }
    
    public void drawSymbolicGridLinesVertical(final Graphics2D g2, final Rectangle2D drawArea, final Rectangle2D plotArea, final boolean firstGridLineIsDark, final List ticks) {
        this.symbolicGridLineList = new Vector(ticks.size());
        boolean currentGridLineIsDark = firstGridLineIsDark;
        final double xx = plotArea.getX();
        double outlineStrokeWidth;
        if (this.getPlot().getOutlineStroke() != null) {
            outlineStrokeWidth = ((BasicStroke)this.getPlot().getOutlineStroke()).getLineWidth();
        }
        else {
            outlineStrokeWidth = 1.0;
        }
        for (final ValueTick tick : ticks) {
            final double yy1 = this.valueToJava2D(tick.getValue() + 0.5, plotArea, RectangleEdge.LEFT);
            final double yy2 = this.valueToJava2D(tick.getValue() - 0.5, plotArea, RectangleEdge.LEFT);
            if (currentGridLineIsDark) {
                g2.setPaint(this.symbolicGridPaint);
            }
            else {
                g2.setPaint(Color.white);
            }
            final Rectangle2D symbolicGridLine = new Rectangle2D.Double(xx + outlineStrokeWidth, yy1, plotArea.getMaxX() - xx - outlineStrokeWidth, yy2 - yy1);
            g2.fill(symbolicGridLine);
            this.symbolicGridLineList.add(symbolicGridLine);
            currentGridLineIsDark = !currentGridLineIsDark;
        }
        g2.setPaintMode();
    }
    
    static {
        DEFAULT_SYMBOLIC_GRID_LINE_PAINT = new Color(232, 234, 232, 128);
    }
}
