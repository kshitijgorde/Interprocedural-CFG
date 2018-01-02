// 
// Decompiled by Procyon v0.5.30
// 

package org.jfree.chart.plot;

import java.awt.BasicStroke;
import org.jfree.chart.axis.AxisLocation;
import java.io.ObjectInputStream;
import java.io.IOException;
import org.jfree.io.SerialUtilities;
import java.io.ObjectOutputStream;
import org.jfree.util.ObjectUtils;
import org.jfree.ui.RectangleEdge;
import org.jfree.data.DatasetChangeEvent;
import org.jfree.chart.event.AxisChangeEvent;
import java.awt.FontMetrics;
import org.jfree.text.TextUtilities;
import org.jfree.ui.Align;
import java.awt.image.ImageObserver;
import java.awt.Shape;
import java.awt.Composite;
import java.awt.AlphaComposite;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.awt.Graphics2D;
import org.jfree.chart.event.PlotChangeListener;
import org.jfree.chart.LegendItemCollection;
import org.jfree.chart.event.PlotChangeEvent;
import java.awt.Color;
import javax.swing.event.EventListenerList;
import java.awt.Image;
import java.awt.Font;
import org.jfree.data.DatasetGroup;
import java.awt.Paint;
import java.awt.Stroke;
import java.awt.Insets;
import java.io.Serializable;
import org.jfree.data.DatasetChangeListener;
import org.jfree.chart.event.AxisChangeListener;

public abstract class Plot implements AxisChangeListener, DatasetChangeListener, Serializable, Cloneable
{
    public static final Number ZERO;
    public static final Insets DEFAULT_INSETS;
    public static final Stroke DEFAULT_OUTLINE_STROKE;
    public static final Paint DEFAULT_OUTLINE_PAINT;
    public static final float DEFAULT_FOREGROUND_ALPHA = 1.0f;
    public static final float DEFAULT_BACKGROUND_ALPHA = 1.0f;
    public static final Paint DEFAULT_BACKGROUND_PAINT;
    public static final int MINIMUM_WIDTH_TO_DRAW = 10;
    public static final int MINIMUM_HEIGHT_TO_DRAW = 10;
    private Plot parent;
    private DatasetGroup datasetGroup;
    private String noDataMessage;
    private Font noDataMessageFont;
    private transient Paint noDataMessagePaint;
    private Insets insets;
    private transient Stroke outlineStroke;
    private transient Paint outlinePaint;
    private transient Paint backgroundPaint;
    private transient Image backgroundImage;
    private int backgroundImageAlignment;
    private float foregroundAlpha;
    private float backgroundAlpha;
    private DrawingSupplier drawingSupplier;
    private transient EventListenerList listenerList;
    private double dataAreaRatio;
    static /* synthetic */ Class class$org$jfree$chart$event$PlotChangeListener;
    
    protected Plot() {
        this.backgroundImageAlignment = 15;
        this.dataAreaRatio = 0.0;
        this.parent = null;
        this.insets = new Insets(Plot.DEFAULT_INSETS.top, Plot.DEFAULT_INSETS.left, Plot.DEFAULT_INSETS.bottom, Plot.DEFAULT_INSETS.right);
        this.backgroundPaint = Plot.DEFAULT_BACKGROUND_PAINT;
        this.backgroundAlpha = 1.0f;
        this.backgroundImage = null;
        this.outlineStroke = Plot.DEFAULT_OUTLINE_STROKE;
        this.outlinePaint = Plot.DEFAULT_OUTLINE_PAINT;
        this.foregroundAlpha = 1.0f;
        this.noDataMessage = null;
        this.noDataMessageFont = new Font("SansSerif", 0, 12);
        this.noDataMessagePaint = Color.black;
        this.drawingSupplier = new DefaultDrawingSupplier();
        this.listenerList = new EventListenerList();
    }
    
    public DatasetGroup getDatasetGroup() {
        return this.datasetGroup;
    }
    
    protected void setDatasetGroup(final DatasetGroup group) {
        this.datasetGroup = group;
    }
    
    public String getNoDataMessage() {
        return this.noDataMessage;
    }
    
    public void setNoDataMessage(final String message) {
        this.noDataMessage = message;
    }
    
    public Font getNoDataMessageFont() {
        return this.noDataMessageFont;
    }
    
    public void setNoDataMessageFont(final Font font) {
        this.noDataMessageFont = font;
    }
    
    public Paint getNoDataMessagePaint() {
        return this.noDataMessagePaint;
    }
    
    public void setNoDataMessagePaint(final Paint paint) {
        this.noDataMessagePaint = paint;
    }
    
    public abstract String getPlotType();
    
    public Plot getParent() {
        return this.parent;
    }
    
    public void setParent(final Plot parent) {
        this.parent = parent;
    }
    
    public Plot getRootPlot() {
        final Plot p = this.getParent();
        if (p == null) {
            return this;
        }
        return p.getRootPlot();
    }
    
    public boolean isSubplot() {
        return this.getParent() != null;
    }
    
    public Insets getInsets() {
        return this.insets;
    }
    
    public void setInsets(final Insets insets) {
        this.setInsets(insets, true);
    }
    
    public void setInsets(final Insets insets, final boolean notify) {
        if (insets == null) {
            throw new IllegalArgumentException("Null 'insets' argument.");
        }
        if (!this.insets.equals(insets)) {
            this.insets = insets;
            if (notify) {
                this.notifyListeners(new PlotChangeEvent(this));
            }
        }
    }
    
    public Paint getBackgroundPaint() {
        return this.backgroundPaint;
    }
    
    public void setBackgroundPaint(final Paint paint) {
        if (paint == null) {
            if (this.backgroundPaint != null) {
                this.backgroundPaint = null;
                this.notifyListeners(new PlotChangeEvent(this));
            }
        }
        else {
            if (this.backgroundPaint != null && this.backgroundPaint.equals(paint)) {
                return;
            }
            this.backgroundPaint = paint;
            this.notifyListeners(new PlotChangeEvent(this));
        }
    }
    
    public float getBackgroundAlpha() {
        return this.backgroundAlpha;
    }
    
    public void setBackgroundAlpha(final float alpha) {
        if (this.backgroundAlpha != alpha) {
            this.backgroundAlpha = alpha;
            this.notifyListeners(new PlotChangeEvent(this));
        }
    }
    
    public DrawingSupplier getDrawingSupplier() {
        DrawingSupplier result = null;
        final Plot p = this.getParent();
        if (p != null) {
            result = p.getDrawingSupplier();
        }
        else {
            result = this.drawingSupplier;
        }
        return result;
    }
    
    public void setDrawingSupplier(final DrawingSupplier supplier) {
        this.drawingSupplier = supplier;
        this.notifyListeners(new PlotChangeEvent(this));
    }
    
    public Image getBackgroundImage() {
        return this.backgroundImage;
    }
    
    public void setBackgroundImage(final Image image) {
        this.backgroundImage = image;
        this.notifyListeners(new PlotChangeEvent(this));
    }
    
    public int getBackgroundImageAlignment() {
        return this.backgroundImageAlignment;
    }
    
    public void setBackgroundImageAlignment(final int alignment) {
        if (this.backgroundImageAlignment != alignment) {
            this.backgroundImageAlignment = alignment;
            this.notifyListeners(new PlotChangeEvent(this));
        }
    }
    
    public Stroke getOutlineStroke() {
        return this.outlineStroke;
    }
    
    public void setOutlineStroke(final Stroke stroke) {
        if (stroke == null) {
            if (this.outlineStroke != null) {
                this.outlineStroke = null;
                this.notifyListeners(new PlotChangeEvent(this));
            }
        }
        else {
            if (this.outlineStroke != null && this.outlineStroke.equals(stroke)) {
                return;
            }
            this.outlineStroke = stroke;
            this.notifyListeners(new PlotChangeEvent(this));
        }
    }
    
    public Paint getOutlinePaint() {
        return this.outlinePaint;
    }
    
    public void setOutlinePaint(final Paint paint) {
        if (paint == null) {
            if (this.outlinePaint != null) {
                this.outlinePaint = null;
                this.notifyListeners(new PlotChangeEvent(this));
            }
        }
        else {
            if (this.outlinePaint != null && this.outlinePaint.equals(paint)) {
                return;
            }
            this.outlinePaint = paint;
            this.notifyListeners(new PlotChangeEvent(this));
        }
    }
    
    public float getForegroundAlpha() {
        return this.foregroundAlpha;
    }
    
    public void setForegroundAlpha(final float alpha) {
        if (this.foregroundAlpha != alpha) {
            this.foregroundAlpha = alpha;
            this.notifyListeners(new PlotChangeEvent(this));
        }
    }
    
    public LegendItemCollection getLegendItems() {
        return null;
    }
    
    public void addChangeListener(final PlotChangeListener listener) {
        this.listenerList.add((Plot.class$org$jfree$chart$event$PlotChangeListener == null) ? (Plot.class$org$jfree$chart$event$PlotChangeListener = class$("org.jfree.chart.event.PlotChangeListener")) : Plot.class$org$jfree$chart$event$PlotChangeListener, listener);
    }
    
    public void removeChangeListener(final PlotChangeListener listener) {
        this.listenerList.remove((Plot.class$org$jfree$chart$event$PlotChangeListener == null) ? (Plot.class$org$jfree$chart$event$PlotChangeListener = class$("org.jfree.chart.event.PlotChangeListener")) : Plot.class$org$jfree$chart$event$PlotChangeListener, listener);
    }
    
    public void notifyListeners(final PlotChangeEvent event) {
        final Object[] listeners = this.listenerList.getListenerList();
        for (int i = listeners.length - 2; i >= 0; i -= 2) {
            if (listeners[i] == ((Plot.class$org$jfree$chart$event$PlotChangeListener == null) ? (Plot.class$org$jfree$chart$event$PlotChangeListener = class$("org.jfree.chart.event.PlotChangeListener")) : Plot.class$org$jfree$chart$event$PlotChangeListener)) {
                ((PlotChangeListener)listeners[i + 1]).plotChanged(event);
            }
        }
    }
    
    public abstract void draw(final Graphics2D p0, final Rectangle2D p1, final PlotState p2, final PlotRenderingInfo p3);
    
    public void draw(final Graphics2D g2, final Rectangle2D area, final Point2D anchor, final PlotState parentState, final PlotRenderingInfo info) {
        this.draw(g2, area, parentState, info);
    }
    
    public void drawBackground(final Graphics2D g2, final Rectangle2D area) {
        this.fillBackground(g2, area);
        this.drawBackgroundImage(g2, area);
    }
    
    protected void fillBackground(final Graphics2D g2, final Rectangle2D area) {
        if (this.backgroundPaint != null) {
            final Composite originalComposite = g2.getComposite();
            g2.setComposite(AlphaComposite.getInstance(3, this.backgroundAlpha));
            g2.setPaint(this.backgroundPaint);
            g2.fill(area);
            g2.setComposite(originalComposite);
        }
    }
    
    protected void drawBackgroundImage(final Graphics2D g2, final Rectangle2D area) {
        if (this.backgroundImage != null) {
            final Composite originalComposite = g2.getComposite();
            g2.setComposite(AlphaComposite.getInstance(2, this.backgroundAlpha));
            final Rectangle2D dest = new Rectangle2D.Double(0.0, 0.0, this.backgroundImage.getWidth(null), this.backgroundImage.getHeight(null));
            Align.align(dest, area, this.backgroundImageAlignment);
            g2.drawImage(this.backgroundImage, (int)dest.getX(), (int)dest.getY(), (int)dest.getWidth() + 1, (int)dest.getHeight() + 1, null);
            g2.setComposite(originalComposite);
        }
    }
    
    public void drawOutline(final Graphics2D g2, final Rectangle2D area) {
        if (this.outlineStroke != null && this.outlinePaint != null) {
            g2.setStroke(this.outlineStroke);
            g2.setPaint(this.outlinePaint);
            g2.draw(area);
        }
    }
    
    protected void drawNoDataMessage(final Graphics2D g2, final Rectangle2D area) {
        final Shape savedClip = g2.getClip();
        g2.clip(area);
        final String message = this.noDataMessage;
        if (message != null) {
            g2.setFont(this.noDataMessageFont);
            g2.setPaint(this.noDataMessagePaint);
            final FontMetrics fm = g2.getFontMetrics(this.noDataMessageFont);
            final Rectangle2D bounds = TextUtilities.getTextBounds(message, g2, fm);
            final float x = (float)(area.getX() + area.getWidth() / 2.0 - bounds.getWidth() / 2.0);
            final float y = (float)(area.getMinY() + area.getHeight() / 2.0 - bounds.getHeight() / 2.0);
            g2.drawString(message, x, y);
        }
        g2.setClip(savedClip);
    }
    
    public void handleClick(final int x, final int y, final PlotRenderingInfo info) {
    }
    
    public void zoom(final double percent) {
    }
    
    public void axisChanged(final AxisChangeEvent event) {
        this.notifyListeners(new PlotChangeEvent(this));
    }
    
    public void datasetChanged(final DatasetChangeEvent event) {
        final PlotChangeEvent newEvent = new PlotChangeEvent(this);
        this.notifyListeners(newEvent);
    }
    
    protected double getRectX(final double x, final double w1, final double w2, final RectangleEdge edge) {
        double result = x;
        if (edge == RectangleEdge.LEFT) {
            result += w1;
        }
        else if (edge == RectangleEdge.RIGHT) {
            result += w2;
        }
        return result;
    }
    
    protected double getRectY(final double y, final double h1, final double h2, final RectangleEdge edge) {
        double result = y;
        if (edge == RectangleEdge.TOP) {
            result += h1;
        }
        else if (edge == RectangleEdge.BOTTOM) {
            result += h2;
        }
        return result;
    }
    
    public double getDataAreaRatio() {
        return this.dataAreaRatio;
    }
    
    public void setDataAreaRatio(final double ratio) {
        this.dataAreaRatio = ratio;
    }
    
    public boolean equals(final Object obj) {
        if (obj == null) {
            return false;
        }
        if (obj == this) {
            return true;
        }
        if (obj instanceof Plot) {
            final Plot p = (Plot)obj;
            final boolean b5 = ObjectUtils.equal(this.noDataMessage, p.noDataMessage);
            final boolean b6 = ObjectUtils.equal(this.noDataMessageFont, p.noDataMessageFont);
            final boolean b7 = ObjectUtils.equal(this.noDataMessagePaint, p.noDataMessagePaint);
            final boolean b8 = ObjectUtils.equal(this.insets, p.insets);
            final boolean b9 = ObjectUtils.equal(this.outlineStroke, p.outlineStroke);
            final boolean b10 = ObjectUtils.equal(this.outlinePaint, p.outlinePaint);
            final boolean b11 = ObjectUtils.equal(this.backgroundPaint, p.backgroundPaint);
            final boolean b12 = ObjectUtils.equal(this.backgroundImage, p.backgroundImage);
            final boolean b13 = this.backgroundImageAlignment == p.backgroundImageAlignment;
            final boolean b14 = this.foregroundAlpha == p.foregroundAlpha;
            final boolean b15 = this.backgroundAlpha == p.backgroundAlpha;
            return b5 && b6 && b7 && b8 && b9 && b10 && b11 && b12 && b13 && b14 && b15;
        }
        return false;
    }
    
    public Object clone() throws CloneNotSupportedException {
        final Plot clone = (Plot)super.clone();
        clone.datasetGroup = (DatasetGroup)ObjectUtils.clone(this.datasetGroup);
        clone.drawingSupplier = (DrawingSupplier)ObjectUtils.clone(this.drawingSupplier);
        clone.listenerList = new EventListenerList();
        return clone;
    }
    
    private void writeObject(final ObjectOutputStream stream) throws IOException {
        stream.defaultWriteObject();
        SerialUtilities.writePaint(this.noDataMessagePaint, stream);
        SerialUtilities.writeStroke(this.outlineStroke, stream);
        SerialUtilities.writePaint(this.outlinePaint, stream);
        SerialUtilities.writePaint(this.backgroundPaint, stream);
    }
    
    private void readObject(final ObjectInputStream stream) throws IOException, ClassNotFoundException {
        stream.defaultReadObject();
        this.noDataMessagePaint = SerialUtilities.readPaint(stream);
        this.outlineStroke = SerialUtilities.readStroke(stream);
        this.outlinePaint = SerialUtilities.readPaint(stream);
        this.backgroundPaint = SerialUtilities.readPaint(stream);
        this.listenerList = new EventListenerList();
    }
    
    public static RectangleEdge resolveDomainAxisLocation(final AxisLocation location, final PlotOrientation orientation) {
        if (location == null) {
            throw new IllegalArgumentException("Null 'location' argument.");
        }
        if (orientation == null) {
            throw new IllegalArgumentException("Null 'orientation' argument.");
        }
        RectangleEdge result = null;
        if (location == AxisLocation.TOP_OR_RIGHT) {
            if (orientation == PlotOrientation.HORIZONTAL) {
                result = RectangleEdge.RIGHT;
            }
            else if (orientation == PlotOrientation.VERTICAL) {
                result = RectangleEdge.TOP;
            }
        }
        else if (location == AxisLocation.TOP_OR_LEFT) {
            if (orientation == PlotOrientation.HORIZONTAL) {
                result = RectangleEdge.LEFT;
            }
            else if (orientation == PlotOrientation.VERTICAL) {
                result = RectangleEdge.TOP;
            }
        }
        else if (location == AxisLocation.BOTTOM_OR_RIGHT) {
            if (orientation == PlotOrientation.HORIZONTAL) {
                result = RectangleEdge.RIGHT;
            }
            else if (orientation == PlotOrientation.VERTICAL) {
                result = RectangleEdge.BOTTOM;
            }
        }
        else if (location == AxisLocation.BOTTOM_OR_LEFT) {
            if (orientation == PlotOrientation.HORIZONTAL) {
                result = RectangleEdge.LEFT;
            }
            else if (orientation == PlotOrientation.VERTICAL) {
                result = RectangleEdge.BOTTOM;
            }
        }
        if (result == null) {
            throw new IllegalStateException("XYPlot.resolveDomainAxisLocation(...)");
        }
        return result;
    }
    
    public static RectangleEdge resolveRangeAxisLocation(final AxisLocation location, final PlotOrientation orientation) {
        if (location == null) {
            throw new IllegalArgumentException("Null 'location' argument.");
        }
        if (orientation == null) {
            throw new IllegalArgumentException("Null 'orientation' argument.");
        }
        RectangleEdge result = null;
        if (location == AxisLocation.TOP_OR_RIGHT) {
            if (orientation == PlotOrientation.HORIZONTAL) {
                result = RectangleEdge.TOP;
            }
            else if (orientation == PlotOrientation.VERTICAL) {
                result = RectangleEdge.RIGHT;
            }
        }
        else if (location == AxisLocation.TOP_OR_LEFT) {
            if (orientation == PlotOrientation.HORIZONTAL) {
                result = RectangleEdge.TOP;
            }
            else if (orientation == PlotOrientation.VERTICAL) {
                result = RectangleEdge.LEFT;
            }
        }
        else if (location == AxisLocation.BOTTOM_OR_RIGHT) {
            if (orientation == PlotOrientation.HORIZONTAL) {
                result = RectangleEdge.BOTTOM;
            }
            else if (orientation == PlotOrientation.VERTICAL) {
                result = RectangleEdge.RIGHT;
            }
        }
        else if (location == AxisLocation.BOTTOM_OR_LEFT) {
            if (orientation == PlotOrientation.HORIZONTAL) {
                result = RectangleEdge.BOTTOM;
            }
            else if (orientation == PlotOrientation.VERTICAL) {
                result = RectangleEdge.LEFT;
            }
        }
        if (result == null) {
            throw new IllegalStateException("XYPlot.resolveRangeAxisLocation(...)");
        }
        return result;
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
        ZERO = new Integer(0);
        DEFAULT_INSETS = new Insets(4, 8, 4, 8);
        DEFAULT_OUTLINE_STROKE = new BasicStroke(0.5f);
        DEFAULT_OUTLINE_PAINT = Color.gray;
        DEFAULT_BACKGROUND_PAINT = Color.white;
    }
}
