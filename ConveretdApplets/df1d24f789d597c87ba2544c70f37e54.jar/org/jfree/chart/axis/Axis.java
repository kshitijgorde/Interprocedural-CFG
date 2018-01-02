// 
// Decompiled by Procyon v0.5.30
// 

package org.jfree.chart.axis;

import org.jfree.util.Log;
import java.awt.BasicStroke;
import java.awt.Color;
import java.io.ObjectInputStream;
import java.io.IOException;
import org.jfree.io.SerialUtilities;
import java.io.ObjectOutputStream;
import org.jfree.util.ObjectUtils;
import java.awt.geom.Line2D;
import org.jfree.ui.RefineryUtilities;
import org.jfree.ui.TextAnchor;
import java.awt.FontMetrics;
import java.awt.Shape;
import java.awt.geom.AffineTransform;
import org.jfree.text.TextUtilities;
import org.jfree.chart.event.AxisChangeListener;
import java.util.List;
import org.jfree.chart.plot.PlotRenderingInfo;
import org.jfree.ui.RectangleEdge;
import java.awt.geom.Rectangle2D;
import java.awt.Graphics2D;
import org.jfree.chart.event.AxisChangeEvent;
import org.jfree.util.LogContext;
import javax.swing.event.EventListenerList;
import org.jfree.chart.plot.Plot;
import java.awt.Stroke;
import java.awt.Insets;
import java.awt.Paint;
import java.awt.Font;
import java.io.Serializable;

public abstract class Axis implements Cloneable, Serializable
{
    public static final boolean DEFAULT_AXIS_VISIBLE = true;
    public static final Font DEFAULT_AXIS_LABEL_FONT;
    public static final Paint DEFAULT_AXIS_LABEL_PAINT;
    public static final Insets DEFAULT_AXIS_LABEL_INSETS;
    public static final Paint DEFAULT_AXIS_LINE_PAINT;
    public static final Stroke DEFAULT_AXIS_LINE_STROKE;
    public static final boolean DEFAULT_TICK_LABELS_VISIBLE = true;
    public static final Font DEFAULT_TICK_LABEL_FONT;
    public static final Paint DEFAULT_TICK_LABEL_PAINT;
    public static final Insets DEFAULT_TICK_LABEL_INSETS;
    public static final boolean DEFAULT_TICK_MARKS_VISIBLE = true;
    public static final Stroke DEFAULT_TICK_MARK_STROKE;
    public static final Paint DEFAULT_TICK_MARK_PAINT;
    public static final float DEFAULT_TICK_MARK_INSIDE_LENGTH = 0.0f;
    public static final float DEFAULT_TICK_MARK_OUTSIDE_LENGTH = 2.0f;
    private boolean visible;
    private String label;
    private Font labelFont;
    private transient Paint labelPaint;
    private Insets labelInsets;
    private double labelAngle;
    private boolean axisLineVisible;
    private transient Stroke axisLineStroke;
    private transient Paint axisLinePaint;
    private boolean tickLabelsVisible;
    private Font tickLabelFont;
    private transient Paint tickLabelPaint;
    private Insets tickLabelInsets;
    private boolean tickMarksVisible;
    private float tickMarkInsideLength;
    private float tickMarkOutsideLength;
    private transient Stroke tickMarkStroke;
    private transient Paint tickMarkPaint;
    private double fixedDimension;
    private transient Plot plot;
    private transient EventListenerList listenerList;
    protected static final LogContext LOGGER;
    static /* synthetic */ Class class$org$jfree$chart$axis$Axis;
    static /* synthetic */ Class class$org$jfree$chart$event$AxisChangeListener;
    
    protected Axis(final String label) {
        this.label = label;
        this.visible = true;
        this.labelFont = Axis.DEFAULT_AXIS_LABEL_FONT;
        this.labelPaint = Axis.DEFAULT_AXIS_LABEL_PAINT;
        this.labelInsets = Axis.DEFAULT_AXIS_LABEL_INSETS;
        this.labelAngle = 0.0;
        this.axisLineVisible = true;
        this.axisLinePaint = Axis.DEFAULT_AXIS_LINE_PAINT;
        this.axisLineStroke = Axis.DEFAULT_AXIS_LINE_STROKE;
        this.tickLabelsVisible = true;
        this.tickLabelFont = Axis.DEFAULT_TICK_LABEL_FONT;
        this.tickLabelPaint = Axis.DEFAULT_TICK_LABEL_PAINT;
        this.tickLabelInsets = Axis.DEFAULT_TICK_LABEL_INSETS;
        this.tickMarksVisible = true;
        this.tickMarkStroke = Axis.DEFAULT_TICK_MARK_STROKE;
        this.tickMarkPaint = Axis.DEFAULT_TICK_MARK_PAINT;
        this.tickMarkInsideLength = 0.0f;
        this.tickMarkOutsideLength = 2.0f;
        this.plot = null;
        this.listenerList = new EventListenerList();
    }
    
    public boolean isVisible() {
        return this.visible;
    }
    
    public void setVisible(final boolean flag) {
        if (flag != this.visible) {
            this.visible = flag;
            this.notifyListeners(new AxisChangeEvent(this));
        }
    }
    
    public String getLabel() {
        return this.label;
    }
    
    public void setLabel(final String label) {
        final String existing = this.label;
        if (existing != null) {
            if (!existing.equals(label)) {
                this.label = label;
                this.notifyListeners(new AxisChangeEvent(this));
            }
        }
        else if (label != null) {
            this.label = label;
            this.notifyListeners(new AxisChangeEvent(this));
        }
    }
    
    public Font getLabelFont() {
        return this.labelFont;
    }
    
    public void setLabelFont(final Font font) {
        if (font == null) {
            throw new IllegalArgumentException("Null 'font' argument.");
        }
        if (!this.labelFont.equals(font)) {
            this.labelFont = font;
            this.notifyListeners(new AxisChangeEvent(this));
        }
    }
    
    public Paint getLabelPaint() {
        return this.labelPaint;
    }
    
    public void setLabelPaint(final Paint paint) {
        if (paint == null) {
            throw new IllegalArgumentException("Axis.setLabelPaint(...): null not permitted.");
        }
        this.labelPaint = paint;
        this.notifyListeners(new AxisChangeEvent(this));
    }
    
    public Insets getLabelInsets() {
        return this.labelInsets;
    }
    
    public void setLabelInsets(final Insets insets) {
        if (insets == null) {
            throw new IllegalArgumentException("Null 'insets' argument.");
        }
        if (!insets.equals(this.labelInsets)) {
            this.labelInsets = insets;
            this.notifyListeners(new AxisChangeEvent(this));
        }
    }
    
    public double getLabelAngle() {
        return this.labelAngle;
    }
    
    public void setLabelAngle(final double angle) {
        this.labelAngle = angle;
        this.notifyListeners(new AxisChangeEvent(this));
    }
    
    public boolean isAxisLineVisible() {
        return this.axisLineVisible;
    }
    
    public void setAxisLineVisible(final boolean visible) {
        this.axisLineVisible = visible;
        this.notifyListeners(new AxisChangeEvent(this));
    }
    
    public Paint getAxisLinePaint() {
        return this.axisLinePaint;
    }
    
    public void setAxisLinePaint(final Paint paint) {
        if (paint == null) {
            throw new IllegalArgumentException("Null 'paint' argument.");
        }
        this.axisLinePaint = paint;
        this.notifyListeners(new AxisChangeEvent(this));
    }
    
    public Stroke getAxisLineStroke() {
        return this.axisLineStroke;
    }
    
    public void setAxisLineStroke(final Stroke stroke) {
        if (stroke == null) {
            throw new IllegalArgumentException("Null 'stroke' argument.");
        }
        this.axisLineStroke = stroke;
        this.notifyListeners(new AxisChangeEvent(this));
    }
    
    public boolean isTickLabelsVisible() {
        return this.tickLabelsVisible;
    }
    
    public void setTickLabelsVisible(final boolean flag) {
        if (flag != this.tickLabelsVisible) {
            this.tickLabelsVisible = flag;
            this.notifyListeners(new AxisChangeEvent(this));
        }
    }
    
    public Font getTickLabelFont() {
        return this.tickLabelFont;
    }
    
    public void setTickLabelFont(final Font font) {
        if (font == null) {
            throw new IllegalArgumentException("Null 'font' argument.");
        }
        if (!this.tickLabelFont.equals(font)) {
            this.tickLabelFont = font;
            this.notifyListeners(new AxisChangeEvent(this));
        }
    }
    
    public Paint getTickLabelPaint() {
        return this.tickLabelPaint;
    }
    
    public void setTickLabelPaint(final Paint paint) {
        if (paint == null) {
            throw new IllegalArgumentException("Null 'paint' argument.");
        }
        this.tickLabelPaint = paint;
        this.notifyListeners(new AxisChangeEvent(this));
    }
    
    public Insets getTickLabelInsets() {
        return this.tickLabelInsets;
    }
    
    public void setTickLabelInsets(final Insets insets) {
        if (insets == null) {
            throw new IllegalArgumentException("Null 'insets' argument.");
        }
        if (!this.tickLabelInsets.equals(insets)) {
            this.tickLabelInsets = insets;
            this.notifyListeners(new AxisChangeEvent(this));
        }
    }
    
    public boolean isTickMarksVisible() {
        return this.tickMarksVisible;
    }
    
    public void setTickMarksVisible(final boolean flag) {
        if (flag != this.tickMarksVisible) {
            this.tickMarksVisible = flag;
            this.notifyListeners(new AxisChangeEvent(this));
        }
    }
    
    public float getTickMarkInsideLength() {
        return this.tickMarkInsideLength;
    }
    
    public void setTickMarkInsideLength(final float length) {
        this.tickMarkInsideLength = length;
        this.notifyListeners(new AxisChangeEvent(this));
    }
    
    public float getTickMarkOutsideLength() {
        return this.tickMarkOutsideLength;
    }
    
    public void setTickMarkOutsideLength(final float length) {
        this.tickMarkOutsideLength = length;
        this.notifyListeners(new AxisChangeEvent(this));
    }
    
    public Stroke getTickMarkStroke() {
        return this.tickMarkStroke;
    }
    
    public void setTickMarkStroke(final Stroke stroke) {
        if (stroke == null) {
            throw new IllegalArgumentException("Axis.setTickMarkStroke(...): null not permitted.");
        }
        if (!this.tickMarkStroke.equals(stroke)) {
            this.tickMarkStroke = stroke;
            this.notifyListeners(new AxisChangeEvent(this));
        }
    }
    
    public Paint getTickMarkPaint() {
        return this.tickMarkPaint;
    }
    
    public void setTickMarkPaint(final Paint paint) {
        if (paint == null) {
            throw new IllegalArgumentException("Axis.setTickMarkPaint(...): null not permitted.");
        }
        if (!this.tickMarkPaint.equals(paint)) {
            this.tickMarkPaint = paint;
            this.notifyListeners(new AxisChangeEvent(this));
        }
    }
    
    public Plot getPlot() {
        return this.plot;
    }
    
    public void setPlot(final Plot plot) {
        this.plot = plot;
        this.configure();
    }
    
    public double getFixedDimension() {
        return this.fixedDimension;
    }
    
    public void setFixedDimension(final double dimension) {
        this.fixedDimension = dimension;
    }
    
    public abstract void configure();
    
    public abstract AxisSpace reserveSpace(final Graphics2D p0, final Plot p1, final Rectangle2D p2, final RectangleEdge p3, final AxisSpace p4);
    
    public abstract AxisState draw(final Graphics2D p0, final double p1, final Rectangle2D p2, final Rectangle2D p3, final RectangleEdge p4, final PlotRenderingInfo p5);
    
    public abstract List refreshTicks(final Graphics2D p0, final AxisState p1, final Rectangle2D p2, final Rectangle2D p3, final RectangleEdge p4);
    
    public void addChangeListener(final AxisChangeListener listener) {
        this.listenerList.add((Axis.class$org$jfree$chart$event$AxisChangeListener == null) ? (Axis.class$org$jfree$chart$event$AxisChangeListener = class$("org.jfree.chart.event.AxisChangeListener")) : Axis.class$org$jfree$chart$event$AxisChangeListener, listener);
    }
    
    public void removeChangeListener(final AxisChangeListener listener) {
        this.listenerList.remove((Axis.class$org$jfree$chart$event$AxisChangeListener == null) ? (Axis.class$org$jfree$chart$event$AxisChangeListener = class$("org.jfree.chart.event.AxisChangeListener")) : Axis.class$org$jfree$chart$event$AxisChangeListener, listener);
    }
    
    protected void notifyListeners(final AxisChangeEvent event) {
        final Object[] listeners = this.listenerList.getListenerList();
        for (int i = listeners.length - 2; i >= 0; i -= 2) {
            if (listeners[i] == ((Axis.class$org$jfree$chart$event$AxisChangeListener == null) ? (Axis.class$org$jfree$chart$event$AxisChangeListener = class$("org.jfree.chart.event.AxisChangeListener")) : Axis.class$org$jfree$chart$event$AxisChangeListener)) {
                ((AxisChangeListener)listeners[i + 1]).axisChanged(event);
            }
        }
    }
    
    protected Rectangle2D getLabelEnclosure(final Graphics2D g2, final RectangleEdge edge) {
        Rectangle2D result = new Rectangle2D.Double();
        final String axisLabel = this.getLabel();
        if (axisLabel != null) {
            final FontMetrics fm = g2.getFontMetrics(this.getLabelFont());
            final Rectangle2D bounds = TextUtilities.getTextBounds(axisLabel, g2, fm);
            final Insets insets = this.getLabelInsets();
            bounds.setRect(bounds.getX(), bounds.getY(), bounds.getWidth() + insets.left + insets.right, bounds.getHeight() + insets.top + insets.bottom);
            double angle = this.getLabelAngle();
            if (edge == RectangleEdge.LEFT || edge == RectangleEdge.RIGHT) {
                angle -= 1.5707963267948966;
            }
            final double x = bounds.getCenterX();
            final double y = bounds.getCenterY();
            final AffineTransform transformer = AffineTransform.getRotateInstance(angle, x, y);
            final Shape labelBounds = transformer.createTransformedShape(bounds);
            result = labelBounds.getBounds2D();
        }
        return result;
    }
    
    protected AxisState drawLabel(final String label, final Graphics2D g2, final Rectangle2D plotArea, final Rectangle2D dataArea, final RectangleEdge edge, final AxisState state) {
        if (Axis.LOGGER.isDebugEnabled()) {
            Axis.LOGGER.debug("Entering drawLabel() method, cursor = " + state.getCursor());
        }
        if (state == null) {
            throw new IllegalArgumentException("Axis.drawLabel: null state not permitted.");
        }
        if (label == null || label.equals("")) {
            return state;
        }
        final Font font = this.getLabelFont();
        final Insets insets = this.getLabelInsets();
        g2.setFont(font);
        g2.setPaint(this.getLabelPaint());
        final FontMetrics fm = g2.getFontMetrics();
        Rectangle2D labelBounds = TextUtilities.getTextBounds(label, g2, fm);
        if (edge == RectangleEdge.TOP) {
            final AffineTransform t = AffineTransform.getRotateInstance(this.getLabelAngle(), labelBounds.getCenterX(), labelBounds.getCenterY());
            final Shape rotatedLabelBounds = t.createTransformedShape(labelBounds);
            labelBounds = rotatedLabelBounds.getBounds2D();
            final double labelx = dataArea.getCenterX();
            final double labely = state.getCursor() - insets.bottom - labelBounds.getHeight() / 2.0;
            RefineryUtilities.drawRotatedString(label, g2, (float)labelx, (float)labely, TextAnchor.CENTER, TextAnchor.CENTER, this.getLabelAngle());
            state.cursorUp(insets.top + labelBounds.getHeight() + insets.bottom);
        }
        else if (edge == RectangleEdge.BOTTOM) {
            final AffineTransform t = AffineTransform.getRotateInstance(this.getLabelAngle(), labelBounds.getCenterX(), labelBounds.getCenterY());
            final Shape rotatedLabelBounds = t.createTransformedShape(labelBounds);
            labelBounds = rotatedLabelBounds.getBounds2D();
            final double labelx = dataArea.getCenterX();
            final double labely = state.getCursor() + insets.top + labelBounds.getHeight() / 2.0;
            RefineryUtilities.drawRotatedString(label, g2, (float)labelx, (float)labely, TextAnchor.CENTER, TextAnchor.CENTER, this.getLabelAngle());
            state.cursorDown(insets.top + labelBounds.getHeight() + insets.bottom);
        }
        else if (edge == RectangleEdge.LEFT) {
            final AffineTransform t = AffineTransform.getRotateInstance(this.getLabelAngle() - 1.5707963267948966, labelBounds.getCenterX(), labelBounds.getCenterY());
            final Shape rotatedLabelBounds = t.createTransformedShape(labelBounds);
            labelBounds = rotatedLabelBounds.getBounds2D();
            final double labelx = state.getCursor() - insets.right - labelBounds.getWidth() / 2.0;
            final double labely = dataArea.getCenterY();
            RefineryUtilities.drawRotatedString(label, g2, (float)labelx, (float)labely, TextAnchor.CENTER, TextAnchor.CENTER, this.getLabelAngle() - 1.5707963267948966);
            state.cursorLeft(insets.left + labelBounds.getWidth() + insets.right);
        }
        else if (edge == RectangleEdge.RIGHT) {
            final AffineTransform t = AffineTransform.getRotateInstance(this.getLabelAngle() + 1.5707963267948966, labelBounds.getCenterX(), labelBounds.getCenterY());
            final Shape rotatedLabelBounds = t.createTransformedShape(labelBounds);
            labelBounds = rotatedLabelBounds.getBounds2D();
            final double labelx = state.getCursor() + insets.left + labelBounds.getWidth() / 2.0;
            final double labely = dataArea.getY() + dataArea.getHeight() / 2.0;
            RefineryUtilities.drawRotatedString(label, g2, (float)labelx, (float)labely, TextAnchor.CENTER, TextAnchor.CENTER, this.getLabelAngle() + 1.5707963267948966);
            state.cursorRight(insets.left + labelBounds.getWidth() + insets.right);
        }
        return state;
    }
    
    protected void drawAxisLine(final Graphics2D g2, final double cursor, final Rectangle2D dataArea, final RectangleEdge edge) {
        Line2D axisLine = null;
        if (edge == RectangleEdge.TOP) {
            axisLine = new Line2D.Double(dataArea.getX(), cursor, dataArea.getMaxX(), cursor);
        }
        else if (edge == RectangleEdge.BOTTOM) {
            axisLine = new Line2D.Double(dataArea.getX(), cursor, dataArea.getMaxX(), cursor);
        }
        else if (edge == RectangleEdge.LEFT) {
            axisLine = new Line2D.Double(cursor, dataArea.getY(), cursor, dataArea.getMaxY());
        }
        else if (edge == RectangleEdge.RIGHT) {
            axisLine = new Line2D.Double(cursor, dataArea.getY(), cursor, dataArea.getMaxY());
        }
        g2.setPaint(this.axisLinePaint);
        g2.setStroke(this.axisLineStroke);
        g2.draw(axisLine);
    }
    
    public Object clone() throws CloneNotSupportedException {
        final Axis clone = (Axis)super.clone();
        if (this.labelInsets != null) {
            clone.labelInsets = (Insets)this.labelInsets.clone();
        }
        if (this.tickLabelInsets != null) {
            clone.tickLabelInsets = (Insets)this.tickLabelInsets.clone();
        }
        clone.plot = null;
        clone.listenerList = new EventListenerList();
        return clone;
    }
    
    public boolean equals(final Object obj) {
        if (obj == null) {
            return false;
        }
        if (obj == this) {
            return true;
        }
        if (obj instanceof Axis) {
            final Axis axis = (Axis)obj;
            final boolean b0 = this.visible == axis.visible;
            final boolean b2 = ObjectUtils.equal(this.label, axis.label);
            final boolean b3 = ObjectUtils.equal(this.labelFont, axis.labelFont);
            final boolean b4 = ObjectUtils.equal(this.labelPaint, axis.labelPaint);
            final boolean b5 = ObjectUtils.equal(this.labelInsets, axis.labelInsets);
            final boolean b6 = Math.abs(this.labelAngle - axis.labelAngle) < 1.0E-7;
            final boolean b7 = this.axisLineVisible == axis.axisLineVisible;
            final boolean b8 = ObjectUtils.equal(this.axisLineStroke, axis.axisLineStroke);
            final boolean b9 = ObjectUtils.equal(this.axisLinePaint, axis.axisLinePaint);
            final boolean b10 = this.tickLabelsVisible == axis.tickLabelsVisible;
            final boolean b11 = ObjectUtils.equal(this.tickLabelFont, axis.tickLabelFont);
            final boolean b12 = ObjectUtils.equal(this.tickLabelPaint, axis.tickLabelPaint);
            final boolean b13 = ObjectUtils.equal(this.tickLabelInsets, axis.tickLabelInsets);
            final boolean b14 = this.tickMarksVisible == axis.tickMarksVisible;
            final boolean b15 = Math.abs(this.tickMarkInsideLength - axis.tickMarkInsideLength) < 1.0E-6;
            final boolean b16 = Math.abs(this.tickMarkOutsideLength - axis.tickMarkOutsideLength) < 1.0E-6;
            final boolean b17 = ObjectUtils.equal(this.tickMarkPaint, axis.tickMarkPaint);
            final boolean b18 = ObjectUtils.equal(this.tickMarkStroke, axis.tickMarkStroke);
            final boolean b19 = Math.abs(this.fixedDimension - axis.fixedDimension) < 1.0E-6;
            return b0 && b2 && b3 && b4 && b5 && b6 && b7 && b8 && b9 && b10 && b11 && b12 && b13 && b14 && b15 && b16 && b17 && b18 && b19;
        }
        return false;
    }
    
    private void writeObject(final ObjectOutputStream stream) throws IOException {
        stream.defaultWriteObject();
        SerialUtilities.writePaint(this.labelPaint, stream);
        SerialUtilities.writePaint(this.tickLabelPaint, stream);
        SerialUtilities.writeStroke(this.axisLineStroke, stream);
        SerialUtilities.writePaint(this.axisLinePaint, stream);
        SerialUtilities.writeStroke(this.tickMarkStroke, stream);
        SerialUtilities.writePaint(this.tickMarkPaint, stream);
    }
    
    private void readObject(final ObjectInputStream stream) throws IOException, ClassNotFoundException {
        stream.defaultReadObject();
        this.labelPaint = SerialUtilities.readPaint(stream);
        this.tickLabelPaint = SerialUtilities.readPaint(stream);
        this.axisLineStroke = SerialUtilities.readStroke(stream);
        this.axisLinePaint = SerialUtilities.readPaint(stream);
        this.tickMarkStroke = SerialUtilities.readStroke(stream);
        this.tickMarkPaint = SerialUtilities.readPaint(stream);
        this.listenerList = new EventListenerList();
    }
    
    static /* synthetic */ Class class$(final String x0) {
        try {
            return Class.forName(x0);
        }
        catch (ClassNotFoundException x) {
            throw new NoClassDefFoundError(x.getMessage());
        }
    }
    
    static {
        DEFAULT_AXIS_LABEL_FONT = new Font("SansSerif", 0, 12);
        DEFAULT_AXIS_LABEL_PAINT = Color.black;
        DEFAULT_AXIS_LABEL_INSETS = new Insets(3, 3, 3, 3);
        DEFAULT_AXIS_LINE_PAINT = Color.gray;
        DEFAULT_AXIS_LINE_STROKE = new BasicStroke(1.0f);
        DEFAULT_TICK_LABEL_FONT = new Font("SansSerif", 0, 10);
        DEFAULT_TICK_LABEL_PAINT = Color.black;
        DEFAULT_TICK_LABEL_INSETS = new Insets(2, 4, 2, 4);
        DEFAULT_TICK_MARK_STROKE = new BasicStroke(1.0f);
        DEFAULT_TICK_MARK_PAINT = Color.gray;
        LOGGER = Log.createContext((Axis.class$org$jfree$chart$axis$Axis == null) ? (Axis.class$org$jfree$chart$axis$Axis = class$("org.jfree.chart.axis.Axis")) : Axis.class$org$jfree$chart$axis$Axis);
    }
}
