// 
// Decompiled by Procyon v0.5.30
// 

package org.jfree.chart.plot;

import java.awt.BasicStroke;
import java.io.ObjectInputStream;
import java.io.IOException;
import org.jfree.io.SerialUtilities;
import java.io.ObjectOutputStream;
import org.jfree.util.ObjectUtils;
import org.jfree.util.ArrayUtils;
import java.util.Iterator;
import java.awt.geom.Line2D;
import org.jfree.chart.axis.ValueTick;
import java.util.List;
import org.jfree.chart.axis.AxisState;
import java.awt.Insets;
import java.awt.Composite;
import java.awt.AlphaComposite;
import java.awt.Shape;
import org.jfree.ui.RectangleEdge;
import org.jfree.chart.axis.AxisSpace;
import java.awt.geom.Rectangle2D;
import java.awt.Graphics2D;
import org.jfree.chart.event.PlotChangeEvent;
import java.awt.Color;
import org.jfree.chart.event.AxisChangeListener;
import java.util.ResourceBundle;
import org.jfree.chart.axis.ValueAxis;
import org.jfree.data.Range;
import java.awt.Paint;
import java.awt.Stroke;
import java.io.Serializable;

public class FastScatterPlot extends Plot implements ValueAxisPlot, Cloneable, Serializable
{
    public static final Stroke DEFAULT_GRIDLINE_STROKE;
    public static final Paint DEFAULT_GRIDLINE_PAINT;
    private float[][] data;
    private Range xDataRange;
    private Range yDataRange;
    private ValueAxis domainAxis;
    private ValueAxis rangeAxis;
    private transient Paint paint;
    private boolean domainGridlinesVisible;
    private transient Stroke domainGridlineStroke;
    private transient Paint domainGridlinePaint;
    private boolean rangeGridlinesVisible;
    private transient Stroke rangeGridlineStroke;
    private transient Paint rangeGridlinePaint;
    protected static ResourceBundle localizationResources;
    
    public FastScatterPlot() {
        this(null, null, null);
    }
    
    public FastScatterPlot(final float[][] data, final ValueAxis domainAxis, final ValueAxis rangeAxis) {
        this.data = data;
        this.xDataRange = this.calculateXDataRange(data);
        this.yDataRange = this.calculateYDataRange(data);
        this.domainAxis = domainAxis;
        if (domainAxis != null) {
            domainAxis.setPlot(this);
            domainAxis.addChangeListener(this);
        }
        if ((this.rangeAxis = rangeAxis) != null) {
            rangeAxis.setPlot(this);
            rangeAxis.addChangeListener(this);
        }
        this.paint = Color.red;
        this.domainGridlinesVisible = true;
        this.domainGridlinePaint = FastScatterPlot.DEFAULT_GRIDLINE_PAINT;
        this.domainGridlineStroke = FastScatterPlot.DEFAULT_GRIDLINE_STROKE;
        this.rangeGridlinesVisible = true;
        this.rangeGridlinePaint = FastScatterPlot.DEFAULT_GRIDLINE_PAINT;
        this.rangeGridlineStroke = FastScatterPlot.DEFAULT_GRIDLINE_STROKE;
    }
    
    public String getPlotType() {
        return FastScatterPlot.localizationResources.getString("Fast_Scatter_Plot");
    }
    
    public ValueAxis getDomainAxis() {
        return this.domainAxis;
    }
    
    public ValueAxis getRangeAxis() {
        return this.rangeAxis;
    }
    
    public Paint getPaint() {
        return this.paint;
    }
    
    public void setPaint(final Paint paint) {
        this.paint = paint;
        this.notifyListeners(new PlotChangeEvent(this));
    }
    
    public boolean isDomainGridlinesVisible() {
        return this.domainGridlinesVisible;
    }
    
    public void setDomainGridlinesVisible(final boolean visible) {
        if (this.domainGridlinesVisible != visible) {
            this.domainGridlinesVisible = visible;
            this.notifyListeners(new PlotChangeEvent(this));
        }
    }
    
    public Stroke getDomainGridlineStroke() {
        return this.domainGridlineStroke;
    }
    
    public void setDomainGridlineStroke(final Stroke stroke) {
        this.domainGridlineStroke = stroke;
        this.notifyListeners(new PlotChangeEvent(this));
    }
    
    public Paint getDomainGridlinePaint() {
        return this.domainGridlinePaint;
    }
    
    public void setDomainGridlinePaint(final Paint paint) {
        this.domainGridlinePaint = paint;
        this.notifyListeners(new PlotChangeEvent(this));
    }
    
    public boolean isRangeGridlinesVisible() {
        return this.rangeGridlinesVisible;
    }
    
    public void setRangeGridlinesVisible(final boolean visible) {
        if (this.rangeGridlinesVisible != visible) {
            this.rangeGridlinesVisible = visible;
            this.notifyListeners(new PlotChangeEvent(this));
        }
    }
    
    public Stroke getRangeGridlineStroke() {
        return this.rangeGridlineStroke;
    }
    
    public void setRangeGridlineStroke(final Stroke stroke) {
        this.rangeGridlineStroke = stroke;
        this.notifyListeners(new PlotChangeEvent(this));
    }
    
    public Paint getRangeGridlinePaint() {
        return this.rangeGridlinePaint;
    }
    
    public void setRangeGridlinePaint(final Paint paint) {
        this.rangeGridlinePaint = paint;
        this.notifyListeners(new PlotChangeEvent(this));
    }
    
    public void draw(final Graphics2D g2, final Rectangle2D plotArea, final PlotState parentState, final PlotRenderingInfo info) {
        if (info != null) {
            info.setPlotArea(plotArea);
        }
        final Insets insets = this.getInsets();
        if (insets != null) {
            plotArea.setRect(plotArea.getX() + insets.left, plotArea.getY() + insets.top, plotArea.getWidth() - insets.left - insets.right, plotArea.getHeight() - insets.top - insets.bottom);
        }
        AxisSpace space = new AxisSpace();
        space = this.domainAxis.reserveSpace(g2, this, plotArea, RectangleEdge.BOTTOM, space);
        space = this.rangeAxis.reserveSpace(g2, this, plotArea, RectangleEdge.LEFT, space);
        final Rectangle2D dataArea = space.shrink(plotArea, null);
        if (info != null) {
            info.setDataArea(dataArea);
        }
        this.drawBackground(g2, dataArea);
        AxisState domainAxisState = null;
        AxisState rangeAxisState = null;
        if (this.domainAxis != null) {
            domainAxisState = this.domainAxis.draw(g2, dataArea.getMaxY(), plotArea, dataArea, RectangleEdge.BOTTOM, info);
        }
        if (this.rangeAxis != null) {
            rangeAxisState = this.rangeAxis.draw(g2, dataArea.getMinX(), plotArea, dataArea, RectangleEdge.LEFT, info);
        }
        this.drawDomainGridlines(g2, dataArea, domainAxisState.getTicks());
        this.drawRangeGridlines(g2, dataArea, rangeAxisState.getTicks());
        final Shape originalClip = g2.getClip();
        final Composite originalComposite = g2.getComposite();
        g2.clip(dataArea);
        g2.setComposite(AlphaComposite.getInstance(3, this.getForegroundAlpha()));
        this.render(g2, dataArea, info, null);
        g2.setClip(originalClip);
        g2.setComposite(originalComposite);
        this.drawOutline(g2, dataArea);
    }
    
    public void render(final Graphics2D g2, final Rectangle2D dataArea, final PlotRenderingInfo info, final CrosshairState crosshairState) {
        g2.setPaint(Color.red);
        if (this.data != null) {
            for (int i = 0; i < this.data[0].length; ++i) {
                final float x = this.data[0][i];
                final float y = this.data[1][i];
                final int transX = (int)this.domainAxis.valueToJava2D(x, dataArea, RectangleEdge.BOTTOM);
                final int transY = (int)this.rangeAxis.valueToJava2D(y, dataArea, RectangleEdge.LEFT);
                g2.fillRect(transX, transY, 1, 1);
            }
        }
    }
    
    protected void drawDomainGridlines(final Graphics2D g2, final Rectangle2D dataArea, final List ticks) {
        if (this.isDomainGridlinesVisible()) {
            final Stroke gridStroke = this.getDomainGridlineStroke();
            final Paint gridPaint = this.getDomainGridlinePaint();
            if (gridStroke != null && gridPaint != null) {
                for (final ValueTick tick : ticks) {
                    final double v = this.domainAxis.valueToJava2D(tick.getValue(), dataArea, RectangleEdge.BOTTOM);
                    final Line2D line = new Line2D.Double(v, dataArea.getMinY(), v, dataArea.getMaxY());
                    g2.setPaint(gridPaint);
                    g2.setStroke(gridStroke);
                    g2.draw(line);
                }
            }
        }
    }
    
    protected void drawRangeGridlines(final Graphics2D g2, final Rectangle2D dataArea, final List ticks) {
        if (this.isRangeGridlinesVisible()) {
            final Stroke gridStroke = this.getRangeGridlineStroke();
            final Paint gridPaint = this.getRangeGridlinePaint();
            if (gridStroke != null && gridPaint != null) {
                for (final ValueTick tick : ticks) {
                    final double v = this.rangeAxis.valueToJava2D(tick.getValue(), dataArea, RectangleEdge.LEFT);
                    final Line2D line = new Line2D.Double(dataArea.getMinX(), v, dataArea.getMaxX(), v);
                    g2.setPaint(gridPaint);
                    g2.setStroke(gridStroke);
                    g2.draw(line);
                }
            }
        }
    }
    
    public Range getDataRange(final ValueAxis axis) {
        Range result = null;
        if (axis == this.domainAxis) {
            result = this.xDataRange;
        }
        else if (axis == this.rangeAxis) {
            result = this.yDataRange;
        }
        return result;
    }
    
    private Range calculateXDataRange(final float[][] data) {
        Range result = null;
        if (data != null) {
            float lowest = Float.POSITIVE_INFINITY;
            float highest = Float.NEGATIVE_INFINITY;
            for (int i = 0; i < data[0].length; ++i) {
                final float v = data[0][i];
                if (v < lowest) {
                    lowest = v;
                }
                if (v > highest) {
                    highest = v;
                }
            }
            if (lowest <= highest) {
                result = new Range(lowest, highest);
            }
        }
        return result;
    }
    
    private Range calculateYDataRange(final float[][] data) {
        Range result = null;
        if (data != null) {
            float lowest = Float.POSITIVE_INFINITY;
            float highest = Float.NEGATIVE_INFINITY;
            for (int i = 0; i < data[0].length; ++i) {
                final float v = data[1][i];
                if (v < lowest) {
                    lowest = v;
                }
                if (v > highest) {
                    highest = v;
                }
            }
            if (lowest <= highest) {
                result = new Range(lowest, highest);
            }
        }
        return result;
    }
    
    public void zoomHorizontalAxes(final double factor) {
        this.domainAxis.resizeRange(factor);
    }
    
    public void zoomHorizontalAxes(final double lowerPercent, final double upperPercent) {
        this.domainAxis.zoomRange(lowerPercent, upperPercent);
    }
    
    public void zoomVerticalAxes(final double factor) {
        this.rangeAxis.resizeRange(factor);
    }
    
    public void zoomVerticalAxes(final double lowerPercent, final double upperPercent) {
        this.rangeAxis.zoomRange(lowerPercent, upperPercent);
    }
    
    public boolean equals(final Object object) {
        if (object == null) {
            return false;
        }
        if (object == this) {
            return true;
        }
        if (super.equals(object) && object instanceof FastScatterPlot) {
            final FastScatterPlot fsp = (FastScatterPlot)object;
            final boolean b0 = ArrayUtils.equal(this.data, fsp.data);
            final boolean b2 = ObjectUtils.equal(this.domainAxis, fsp.domainAxis);
            final boolean b3 = ObjectUtils.equal(this.rangeAxis, fsp.rangeAxis);
            final boolean b4 = ObjectUtils.equal(this.paint, fsp.paint);
            final boolean b5 = this.domainGridlinesVisible == fsp.domainGridlinesVisible;
            final boolean b6 = ObjectUtils.equal(this.domainGridlinePaint, fsp.domainGridlinePaint);
            final boolean b7 = ObjectUtils.equal(this.domainGridlineStroke, fsp.domainGridlineStroke);
            final boolean b8 = this.rangeGridlinesVisible == fsp.rangeGridlinesVisible;
            final boolean b9 = ObjectUtils.equal(this.rangeGridlinePaint, fsp.rangeGridlinePaint);
            final boolean b10 = ObjectUtils.equal(this.rangeGridlineStroke, fsp.rangeGridlineStroke);
            return b0 && b2 && b3 && b4 && b5 && b6 && b7 && b8 && b9 && b10;
        }
        return false;
    }
    
    public Object clone() throws CloneNotSupportedException {
        final FastScatterPlot clone = (FastScatterPlot)super.clone();
        if (this.data != null) {
            clone.data = ArrayUtils.clone(this.data);
        }
        if (this.domainAxis != null) {
            (clone.domainAxis = (ValueAxis)this.domainAxis.clone()).setPlot(clone);
            clone.domainAxis.addChangeListener(clone);
        }
        if (this.rangeAxis != null) {
            (clone.rangeAxis = (ValueAxis)this.rangeAxis.clone()).setPlot(clone);
            clone.rangeAxis.addChangeListener(clone);
        }
        return clone;
    }
    
    private void writeObject(final ObjectOutputStream stream) throws IOException {
        stream.defaultWriteObject();
        SerialUtilities.writePaint(this.paint, stream);
        SerialUtilities.writeStroke(this.domainGridlineStroke, stream);
        SerialUtilities.writePaint(this.domainGridlinePaint, stream);
        SerialUtilities.writeStroke(this.rangeGridlineStroke, stream);
        SerialUtilities.writePaint(this.rangeGridlinePaint, stream);
    }
    
    private void readObject(final ObjectInputStream stream) throws IOException, ClassNotFoundException {
        stream.defaultReadObject();
        this.paint = SerialUtilities.readPaint(stream);
        this.domainGridlineStroke = SerialUtilities.readStroke(stream);
        this.domainGridlinePaint = SerialUtilities.readPaint(stream);
        this.rangeGridlineStroke = SerialUtilities.readStroke(stream);
        this.rangeGridlinePaint = SerialUtilities.readPaint(stream);
        if (this.domainAxis != null) {
            this.domainAxis.addChangeListener(this);
        }
        if (this.rangeAxis != null) {
            this.rangeAxis.addChangeListener(this);
        }
    }
    
    static {
        DEFAULT_GRIDLINE_STROKE = new BasicStroke(0.5f, 0, 2, 0.0f, new float[] { 2.0f, 2.0f }, 0.0f);
        DEFAULT_GRIDLINE_PAINT = Color.lightGray;
        FastScatterPlot.localizationResources = ResourceBundle.getBundle("org.jfree.chart.plot.LocalizationBundle");
    }
}
