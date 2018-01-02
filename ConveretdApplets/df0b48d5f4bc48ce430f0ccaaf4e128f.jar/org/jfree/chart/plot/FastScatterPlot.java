// 
// Decompiled by Procyon v0.5.30
// 

package org.jfree.chart.plot;

import java.awt.BasicStroke;
import java.io.ObjectInputStream;
import java.io.IOException;
import org.jfree.io.SerialUtilities;
import java.io.ObjectOutputStream;
import org.jfree.util.PaintUtilities;
import org.jfree.util.ObjectUtilities;
import org.jfree.util.ArrayUtilities;
import java.util.Iterator;
import java.awt.geom.Line2D;
import org.jfree.chart.axis.ValueTick;
import java.util.List;
import org.jfree.chart.axis.AxisState;
import org.jfree.ui.RectangleInsets;
import java.awt.Composite;
import java.awt.AlphaComposite;
import java.awt.Shape;
import org.jfree.ui.RectangleEdge;
import org.jfree.chart.axis.AxisSpace;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.awt.Graphics2D;
import org.jfree.chart.event.PlotChangeEvent;
import java.awt.Color;
import org.jfree.chart.event.AxisChangeListener;
import org.jfree.chart.axis.NumberAxis;
import java.util.ResourceBundle;
import org.jfree.chart.axis.ValueAxis;
import org.jfree.data.Range;
import java.awt.Paint;
import java.awt.Stroke;
import java.io.Serializable;

public class FastScatterPlot extends Plot implements ValueAxisPlot, Zoomable, Cloneable, Serializable
{
    private static final long serialVersionUID = 7871545897358563521L;
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
        this(null, new NumberAxis("X"), new NumberAxis("Y"));
    }
    
    public FastScatterPlot(final float[][] data, final ValueAxis domainAxis, final ValueAxis rangeAxis) {
        if (domainAxis == null) {
            throw new IllegalArgumentException("Null 'domainAxis' argument.");
        }
        if (rangeAxis == null) {
            throw new IllegalArgumentException("Null 'rangeAxis' argument.");
        }
        this.data = data;
        this.xDataRange = this.calculateXDataRange(data);
        this.yDataRange = this.calculateYDataRange(data);
        (this.domainAxis = domainAxis).setPlot(this);
        this.domainAxis.addChangeListener(this);
        (this.rangeAxis = rangeAxis).setPlot(this);
        this.rangeAxis.addChangeListener(this);
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
    
    public float[][] getData() {
        return this.data;
    }
    
    public void setData(final float[][] data) {
        this.data = data;
        this.notifyListeners(new PlotChangeEvent(this));
    }
    
    public PlotOrientation getOrientation() {
        return PlotOrientation.VERTICAL;
    }
    
    public ValueAxis getDomainAxis() {
        return this.domainAxis;
    }
    
    public void setDomainAxis(final ValueAxis axis) {
        if (axis == null) {
            throw new IllegalArgumentException("Null 'axis' argument.");
        }
        this.domainAxis = axis;
        this.notifyListeners(new PlotChangeEvent(this));
    }
    
    public ValueAxis getRangeAxis() {
        return this.rangeAxis;
    }
    
    public void setRangeAxis(final ValueAxis axis) {
        if (axis == null) {
            throw new IllegalArgumentException("Null 'axis' argument.");
        }
        this.rangeAxis = axis;
        this.notifyListeners(new PlotChangeEvent(this));
    }
    
    public Paint getPaint() {
        return this.paint;
    }
    
    public void setPaint(final Paint paint) {
        if (paint == null) {
            throw new IllegalArgumentException("Null 'paint' argument.");
        }
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
        if (stroke == null) {
            throw new IllegalArgumentException("Null 'stroke' argument.");
        }
        this.domainGridlineStroke = stroke;
        this.notifyListeners(new PlotChangeEvent(this));
    }
    
    public Paint getDomainGridlinePaint() {
        return this.domainGridlinePaint;
    }
    
    public void setDomainGridlinePaint(final Paint paint) {
        if (paint == null) {
            throw new IllegalArgumentException("Null 'paint' argument.");
        }
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
        if (stroke == null) {
            throw new IllegalArgumentException("Null 'stroke' argument.");
        }
        this.rangeGridlineStroke = stroke;
        this.notifyListeners(new PlotChangeEvent(this));
    }
    
    public Paint getRangeGridlinePaint() {
        return this.rangeGridlinePaint;
    }
    
    public void setRangeGridlinePaint(final Paint paint) {
        if (paint == null) {
            throw new IllegalArgumentException("Null 'paint' argument.");
        }
        this.rangeGridlinePaint = paint;
        this.notifyListeners(new PlotChangeEvent(this));
    }
    
    public void draw(final Graphics2D g2, final Rectangle2D area, final Point2D anchor, final PlotState parentState, final PlotRenderingInfo info) {
        if (info != null) {
            info.setPlotArea(area);
        }
        final RectangleInsets insets = this.getInsets();
        insets.trim(area);
        AxisSpace space = new AxisSpace();
        space = this.domainAxis.reserveSpace(g2, this, area, RectangleEdge.BOTTOM, space);
        space = this.rangeAxis.reserveSpace(g2, this, area, RectangleEdge.LEFT, space);
        final Rectangle2D dataArea = space.shrink(area, null);
        if (info != null) {
            info.setDataArea(dataArea);
        }
        this.drawBackground(g2, dataArea);
        final AxisState domainAxisState = this.domainAxis.draw(g2, dataArea.getMaxY(), area, dataArea, RectangleEdge.BOTTOM, info);
        final AxisState rangeAxisState = this.rangeAxis.draw(g2, dataArea.getMinX(), area, dataArea, RectangleEdge.LEFT, info);
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
        g2.setPaint(this.paint);
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
            for (final ValueTick tick : ticks) {
                final double v = this.domainAxis.valueToJava2D(tick.getValue(), dataArea, RectangleEdge.BOTTOM);
                final Line2D line = new Line2D.Double(v, dataArea.getMinY(), v, dataArea.getMaxY());
                g2.setPaint(this.getDomainGridlinePaint());
                g2.setStroke(this.getDomainGridlineStroke());
                g2.draw(line);
            }
        }
    }
    
    protected void drawRangeGridlines(final Graphics2D g2, final Rectangle2D dataArea, final List ticks) {
        if (this.isRangeGridlinesVisible()) {
            for (final ValueTick tick : ticks) {
                final double v = this.rangeAxis.valueToJava2D(tick.getValue(), dataArea, RectangleEdge.LEFT);
                final Line2D line = new Line2D.Double(dataArea.getMinX(), v, dataArea.getMaxX(), v);
                g2.setPaint(this.getRangeGridlinePaint());
                g2.setStroke(this.getRangeGridlineStroke());
                g2.draw(line);
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
    
    public void zoomDomainAxes(final double factor, final PlotRenderingInfo info, final Point2D source) {
        this.domainAxis.resizeRange(factor);
    }
    
    public void zoomDomainAxes(final double lowerPercent, final double upperPercent, final PlotRenderingInfo info, final Point2D source) {
        this.domainAxis.zoomRange(lowerPercent, upperPercent);
    }
    
    public void zoomRangeAxes(final double factor, final PlotRenderingInfo info, final Point2D source) {
        this.rangeAxis.resizeRange(factor);
    }
    
    public void zoomRangeAxes(final double lowerPercent, final double upperPercent, final PlotRenderingInfo info, final Point2D source) {
        this.rangeAxis.zoomRange(lowerPercent, upperPercent);
    }
    
    public boolean isDomainZoomable() {
        return true;
    }
    
    public boolean isRangeZoomable() {
        return true;
    }
    
    public boolean equals(final Object obj) {
        if (obj == this) {
            return true;
        }
        if (!super.equals(obj)) {
            return false;
        }
        if (!(obj instanceof FastScatterPlot)) {
            return false;
        }
        final FastScatterPlot that = (FastScatterPlot)obj;
        return ArrayUtilities.equal(this.data, that.data) && ObjectUtilities.equal(this.domainAxis, that.domainAxis) && ObjectUtilities.equal(this.rangeAxis, that.rangeAxis) && PaintUtilities.equal(this.paint, that.paint) && this.domainGridlinesVisible == that.domainGridlinesVisible && PaintUtilities.equal(this.domainGridlinePaint, that.domainGridlinePaint) && ObjectUtilities.equal(this.domainGridlineStroke, that.domainGridlineStroke) && !this.rangeGridlinesVisible != that.rangeGridlinesVisible && PaintUtilities.equal(this.rangeGridlinePaint, that.rangeGridlinePaint) && ObjectUtilities.equal(this.rangeGridlineStroke, that.rangeGridlineStroke);
    }
    
    public Object clone() throws CloneNotSupportedException {
        final FastScatterPlot clone = (FastScatterPlot)super.clone();
        if (this.data != null) {
            clone.data = ArrayUtilities.clone(this.data);
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
