// 
// Decompiled by Procyon v0.5.30
// 

package org.jfree.chart.title;

import java.io.ObjectInputStream;
import java.io.IOException;
import org.jfree.io.SerialUtilities;
import java.io.ObjectOutputStream;
import org.jfree.util.PaintUtilities;
import org.jfree.chart.plot.PlotRenderingInfo;
import org.jfree.chart.plot.PlotOrientation;
import java.awt.Shape;
import org.jfree.chart.axis.AxisSpace;
import org.jfree.chart.plot.Plot;
import java.awt.geom.Rectangle2D;
import org.jfree.ui.RectangleEdge;
import org.jfree.data.Range;
import org.jfree.chart.block.LengthConstraintType;
import org.jfree.ui.Size2D;
import org.jfree.chart.block.RectangleConstraint;
import java.awt.Graphics2D;
import org.jfree.chart.event.TitleChangeEvent;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Stroke;
import java.awt.Paint;
import org.jfree.chart.axis.AxisLocation;
import org.jfree.chart.axis.ValueAxis;
import org.jfree.chart.renderer.PaintScale;
import org.jfree.util.PublicCloneable;

public class PaintScaleLegend extends Title implements PublicCloneable
{
    private PaintScale scale;
    private ValueAxis axis;
    private AxisLocation axisLocation;
    private double axisOffset;
    private double stripWidth;
    private boolean stripOutlineVisible;
    private transient Paint stripOutlinePaint;
    private transient Stroke stripOutlineStroke;
    private transient Paint backgroundPaint;
    private static final int SUBDIVISIONS = 200;
    
    public PaintScaleLegend(final PaintScale scale, final ValueAxis axis) {
        if (axis == null) {
            throw new IllegalArgumentException("Null 'axis' argument.");
        }
        this.scale = scale;
        this.axis = axis;
        this.axisLocation = AxisLocation.BOTTOM_OR_LEFT;
        this.axisOffset = 0.0;
        this.stripWidth = 15.0;
        this.stripOutlineVisible = false;
        this.stripOutlinePaint = Color.gray;
        this.stripOutlineStroke = new BasicStroke(0.5f);
        this.backgroundPaint = Color.white;
    }
    
    public PaintScale getScale() {
        return this.scale;
    }
    
    public void setScale(final PaintScale scale) {
        if (scale == null) {
            throw new IllegalArgumentException("Null 'scale' argument.");
        }
        this.scale = scale;
        this.notifyListeners(new TitleChangeEvent(this));
    }
    
    public ValueAxis getAxis() {
        return this.axis;
    }
    
    public void setAxis(final ValueAxis axis) {
        if (axis == null) {
            throw new IllegalArgumentException("Null 'axis' argument.");
        }
        this.axis = axis;
        this.notifyListeners(new TitleChangeEvent(this));
    }
    
    public AxisLocation getAxisLocation() {
        return this.axisLocation;
    }
    
    public void setAxisLocation(final AxisLocation location) {
        if (location == null) {
            throw new IllegalArgumentException("Null 'location' argument.");
        }
        this.axisLocation = location;
        this.notifyListeners(new TitleChangeEvent(this));
    }
    
    public double getAxisOffset() {
        return this.axisOffset;
    }
    
    public void setAxisOffset(final double offset) {
        this.axisOffset = offset;
        this.notifyListeners(new TitleChangeEvent(this));
    }
    
    public double getStripWidth() {
        return this.stripWidth;
    }
    
    public void setStripWidth(final double width) {
        this.stripWidth = width;
        this.notifyListeners(new TitleChangeEvent(this));
    }
    
    public boolean isStripOutlineVisible() {
        return this.stripOutlineVisible;
    }
    
    public void setStripOutlineVisible(final boolean visible) {
        this.stripOutlineVisible = visible;
        this.notifyListeners(new TitleChangeEvent(this));
    }
    
    public Paint getStripOutlinePaint() {
        return this.stripOutlinePaint;
    }
    
    public void setStripOutlinePaint(final Paint paint) {
        if (paint == null) {
            throw new IllegalArgumentException("Null 'paint' argument.");
        }
        this.stripOutlinePaint = paint;
        this.notifyListeners(new TitleChangeEvent(this));
    }
    
    public Stroke getStripOutlineStroke() {
        return this.stripOutlineStroke;
    }
    
    public void setStripOutlineStroke(final Stroke stroke) {
        if (stroke == null) {
            throw new IllegalArgumentException("Null 'stroke' argument.");
        }
        this.stripOutlineStroke = stroke;
        this.notifyListeners(new TitleChangeEvent(this));
    }
    
    public Paint getBackgroundPaint() {
        return this.backgroundPaint;
    }
    
    public void setBackgroundPaint(final Paint paint) {
        this.backgroundPaint = paint;
        this.notifyListeners(new TitleChangeEvent(this));
    }
    
    public Size2D arrange(final Graphics2D g2, final RectangleConstraint constraint) {
        final RectangleConstraint cc = this.toContentConstraint(constraint);
        final LengthConstraintType w = cc.getWidthConstraintType();
        final LengthConstraintType h = cc.getHeightConstraintType();
        Size2D contentSize = null;
        if (w == LengthConstraintType.NONE) {
            if (h == LengthConstraintType.NONE) {
                contentSize = new Size2D(this.getWidth(), this.getHeight());
            }
            else {
                if (h == LengthConstraintType.RANGE) {
                    throw new RuntimeException("Not yet implemented.");
                }
                if (h == LengthConstraintType.FIXED) {
                    throw new RuntimeException("Not yet implemented.");
                }
            }
        }
        else if (w == LengthConstraintType.RANGE) {
            if (h == LengthConstraintType.NONE) {
                throw new RuntimeException("Not yet implemented.");
            }
            if (h == LengthConstraintType.RANGE) {
                contentSize = this.arrangeRR(g2, cc.getWidthRange(), cc.getHeightRange());
            }
            else if (h == LengthConstraintType.FIXED) {
                throw new RuntimeException("Not yet implemented.");
            }
        }
        else if (w == LengthConstraintType.FIXED) {
            if (h == LengthConstraintType.NONE) {
                throw new RuntimeException("Not yet implemented.");
            }
            if (h == LengthConstraintType.RANGE) {
                throw new RuntimeException("Not yet implemented.");
            }
            if (h == LengthConstraintType.FIXED) {
                throw new RuntimeException("Not yet implemented.");
            }
        }
        return new Size2D(this.calculateTotalWidth(contentSize.getWidth()), this.calculateTotalHeight(contentSize.getHeight()));
    }
    
    protected Size2D arrangeRR(final Graphics2D g2, final Range widthRange, final Range heightRange) {
        final RectangleEdge position = this.getPosition();
        if (position == RectangleEdge.TOP || position == RectangleEdge.BOTTOM) {
            final float maxWidth = (float)widthRange.getUpperBound();
            final AxisSpace space = this.axis.reserveSpace(g2, null, new Rectangle2D.Double(0.0, 0.0, maxWidth, 100.0), RectangleEdge.BOTTOM, null);
            return new Size2D(maxWidth, this.stripWidth + this.axisOffset + space.getTop() + space.getBottom());
        }
        if (position == RectangleEdge.LEFT || position == RectangleEdge.RIGHT) {
            final float maxHeight = (float)heightRange.getUpperBound();
            final AxisSpace space = this.axis.reserveSpace(g2, null, new Rectangle2D.Double(0.0, 0.0, 100.0, maxHeight), RectangleEdge.RIGHT, null);
            return new Size2D(this.stripWidth + this.axisOffset + space.getLeft() + space.getRight(), maxHeight);
        }
        throw new RuntimeException("Unrecognised position.");
    }
    
    public void draw(final Graphics2D g2, final Rectangle2D area) {
        this.draw(g2, area, null);
    }
    
    public Object draw(final Graphics2D g2, final Rectangle2D area, final Object params) {
        Rectangle2D target = (Rectangle2D)area.clone();
        target = this.trimMargin(target);
        if (this.backgroundPaint != null) {
            g2.setPaint(this.backgroundPaint);
            g2.fill(target);
        }
        this.getBorder().draw(g2, target);
        this.getBorder().getInsets().trim(target);
        target = this.trimPadding(target);
        final double base = this.axis.getLowerBound();
        final double increment = this.axis.getRange().getLength() / 200.0;
        final Rectangle2D r = new Rectangle2D.Double();
        if (RectangleEdge.isTopOrBottom(this.getPosition())) {
            final RectangleEdge axisEdge = Plot.resolveRangeAxisLocation(this.axisLocation, PlotOrientation.HORIZONTAL);
            final double ww = Math.ceil(target.getWidth() / 200.0);
            if (axisEdge == RectangleEdge.TOP) {
                for (int i = 0; i < 200; ++i) {
                    final double v = base + i * increment;
                    final Paint p = this.scale.getPaint(v);
                    final double vv = this.axis.valueToJava2D(v, target, RectangleEdge.BOTTOM);
                    r.setRect(vv, target.getMaxY() - this.stripWidth, ww, this.stripWidth);
                    g2.setPaint(p);
                    g2.fill(r);
                }
                g2.setPaint(this.stripOutlinePaint);
                g2.setStroke(this.stripOutlineStroke);
                g2.draw(new Rectangle2D.Double(target.getMinX(), target.getMaxY() - this.stripWidth, target.getWidth(), this.stripWidth));
                this.axis.draw(g2, target.getMaxY() - this.stripWidth - this.axisOffset, target, target, RectangleEdge.TOP, null);
            }
            else if (axisEdge == RectangleEdge.BOTTOM) {
                for (int i = 0; i < 200; ++i) {
                    final double v = base + i * increment;
                    final Paint p = this.scale.getPaint(v);
                    final double vv = this.axis.valueToJava2D(v, target, RectangleEdge.BOTTOM);
                    r.setRect(vv, target.getMinY(), ww, this.stripWidth);
                    g2.setPaint(p);
                    g2.fill(r);
                }
                g2.setPaint(this.stripOutlinePaint);
                g2.setStroke(this.stripOutlineStroke);
                g2.draw(new Rectangle2D.Double(target.getMinX(), target.getMinY(), target.getWidth(), this.stripWidth));
                this.axis.draw(g2, target.getMinY() + this.stripWidth + this.axisOffset, target, target, RectangleEdge.BOTTOM, null);
            }
        }
        else {
            final RectangleEdge axisEdge = Plot.resolveRangeAxisLocation(this.axisLocation, PlotOrientation.VERTICAL);
            final double hh = Math.ceil(target.getHeight() / 200.0);
            if (axisEdge == RectangleEdge.LEFT) {
                for (int i = 0; i < 200; ++i) {
                    final double v = base + i * increment;
                    final Paint p = this.scale.getPaint(v);
                    final double vv = this.axis.valueToJava2D(v, target, RectangleEdge.LEFT);
                    r.setRect(target.getMaxX() - this.stripWidth, vv - hh, this.stripWidth, hh);
                    g2.setPaint(p);
                    g2.fill(r);
                }
                g2.setPaint(this.stripOutlinePaint);
                g2.setStroke(this.stripOutlineStroke);
                g2.draw(new Rectangle2D.Double(target.getMaxX() - this.stripWidth, target.getMinY(), this.stripWidth, target.getHeight()));
                this.axis.draw(g2, target.getMaxX() - this.stripWidth - this.axisOffset, target, target, RectangleEdge.LEFT, null);
            }
            else if (axisEdge == RectangleEdge.RIGHT) {
                for (int i = 0; i < 200; ++i) {
                    final double v = base + i * increment;
                    final Paint p = this.scale.getPaint(v);
                    final double vv = this.axis.valueToJava2D(v, target, RectangleEdge.LEFT);
                    r.setRect(target.getMinX(), vv - hh, this.stripWidth, hh);
                    g2.setPaint(p);
                    g2.fill(r);
                }
                g2.setPaint(this.stripOutlinePaint);
                g2.setStroke(this.stripOutlineStroke);
                g2.draw(new Rectangle2D.Double(target.getMinX(), target.getMinY(), this.stripWidth, target.getHeight()));
                this.axis.draw(g2, target.getMinX() + this.stripWidth + this.axisOffset, target, target, RectangleEdge.RIGHT, null);
            }
        }
        return null;
    }
    
    public boolean equals(final Object obj) {
        if (!(obj instanceof PaintScaleLegend)) {
            return false;
        }
        final PaintScaleLegend that = (PaintScaleLegend)obj;
        return this.scale.equals(that.scale) && this.axis.equals(that.axis) && this.axisLocation.equals(that.axisLocation) && this.axisOffset == that.axisOffset && this.stripWidth == that.stripWidth && this.stripOutlineVisible == that.stripOutlineVisible && PaintUtilities.equal(this.stripOutlinePaint, that.stripOutlinePaint) && this.stripOutlineStroke.equals(that.stripOutlineStroke) && PaintUtilities.equal(this.backgroundPaint, that.backgroundPaint) && super.equals(obj);
    }
    
    private void writeObject(final ObjectOutputStream stream) throws IOException {
        stream.defaultWriteObject();
        SerialUtilities.writePaint(this.backgroundPaint, stream);
        SerialUtilities.writePaint(this.stripOutlinePaint, stream);
        SerialUtilities.writeStroke(this.stripOutlineStroke, stream);
    }
    
    private void readObject(final ObjectInputStream stream) throws IOException, ClassNotFoundException {
        stream.defaultReadObject();
        this.backgroundPaint = SerialUtilities.readPaint(stream);
        this.stripOutlinePaint = SerialUtilities.readPaint(stream);
        this.stripOutlineStroke = SerialUtilities.readStroke(stream);
    }
}
