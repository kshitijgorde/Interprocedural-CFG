// 
// Decompiled by Procyon v0.5.30
// 

package org.jfree.chart.plot;

import java.awt.geom.Ellipse2D;
import java.awt.BasicStroke;
import org.jfree.chart.axis.AxisLocation;
import java.io.ObjectInputStream;
import java.io.IOException;
import org.jfree.io.SerialUtilities;
import java.io.ObjectOutputStream;
import org.jfree.util.PaintUtilities;
import org.jfree.util.ObjectUtilities;
import org.jfree.ui.RectangleEdge;
import org.jfree.chart.event.MarkerChangeEvent;
import org.jfree.chart.event.ChartChangeEventType;
import org.jfree.data.general.DatasetChangeEvent;
import org.jfree.chart.event.AxisChangeEvent;
import org.jfree.text.TextBlock;
import org.jfree.text.TextBlockAnchor;
import org.jfree.text.TextMeasurer;
import org.jfree.text.TextUtilities;
import org.jfree.text.G2TextMeasurer;
import org.jfree.ui.Align;
import java.awt.image.ImageObserver;
import java.awt.Composite;
import java.awt.AlphaComposite;
import java.awt.GradientPaint;
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
import org.jfree.data.general.DatasetGroup;
import java.awt.Shape;
import java.awt.Paint;
import java.awt.Stroke;
import org.jfree.ui.RectangleInsets;
import java.io.Serializable;
import org.jfree.util.PublicCloneable;
import org.jfree.chart.LegendItemSource;
import org.jfree.chart.event.MarkerChangeListener;
import org.jfree.data.general.DatasetChangeListener;
import org.jfree.chart.event.AxisChangeListener;

public abstract class Plot implements AxisChangeListener, DatasetChangeListener, MarkerChangeListener, LegendItemSource, PublicCloneable, Cloneable, Serializable
{
    private static final long serialVersionUID = -8831571430103671324L;
    public static final Number ZERO;
    public static final RectangleInsets DEFAULT_INSETS;
    public static final Stroke DEFAULT_OUTLINE_STROKE;
    public static final Paint DEFAULT_OUTLINE_PAINT;
    public static final float DEFAULT_FOREGROUND_ALPHA = 1.0f;
    public static final float DEFAULT_BACKGROUND_ALPHA = 1.0f;
    public static final Paint DEFAULT_BACKGROUND_PAINT;
    public static final int MINIMUM_WIDTH_TO_DRAW = 10;
    public static final int MINIMUM_HEIGHT_TO_DRAW = 10;
    public static final Shape DEFAULT_LEGEND_ITEM_BOX;
    public static final Shape DEFAULT_LEGEND_ITEM_CIRCLE;
    private Plot parent;
    private DatasetGroup datasetGroup;
    private String noDataMessage;
    private Font noDataMessageFont;
    private transient Paint noDataMessagePaint;
    private RectangleInsets insets;
    private boolean outlineVisible;
    private transient Stroke outlineStroke;
    private transient Paint outlinePaint;
    private transient Paint backgroundPaint;
    private transient Image backgroundImage;
    private int backgroundImageAlignment;
    private float backgroundImageAlpha;
    private float foregroundAlpha;
    private float backgroundAlpha;
    private DrawingSupplier drawingSupplier;
    private transient EventListenerList listenerList;
    static /* synthetic */ Class class$org$jfree$chart$event$PlotChangeListener;
    
    protected Plot() {
        this.backgroundImageAlignment = 15;
        this.backgroundImageAlpha = 0.5f;
        this.parent = null;
        this.insets = Plot.DEFAULT_INSETS;
        this.backgroundPaint = Plot.DEFAULT_BACKGROUND_PAINT;
        this.backgroundAlpha = 1.0f;
        this.backgroundImage = null;
        this.outlineVisible = true;
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
        this.notifyListeners(new PlotChangeEvent(this));
    }
    
    public Font getNoDataMessageFont() {
        return this.noDataMessageFont;
    }
    
    public void setNoDataMessageFont(final Font font) {
        if (font == null) {
            throw new IllegalArgumentException("Null 'font' argument.");
        }
        this.noDataMessageFont = font;
        this.notifyListeners(new PlotChangeEvent(this));
    }
    
    public Paint getNoDataMessagePaint() {
        return this.noDataMessagePaint;
    }
    
    public void setNoDataMessagePaint(final Paint paint) {
        if (paint == null) {
            throw new IllegalArgumentException("Null 'paint' argument.");
        }
        this.noDataMessagePaint = paint;
        this.notifyListeners(new PlotChangeEvent(this));
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
    
    public RectangleInsets getInsets() {
        return this.insets;
    }
    
    public void setInsets(final RectangleInsets insets) {
        this.setInsets(insets, true);
    }
    
    public void setInsets(final RectangleInsets insets, final boolean notify) {
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
    
    public float getBackgroundImageAlpha() {
        return this.backgroundImageAlpha;
    }
    
    public void setBackgroundImageAlpha(final float alpha) {
        if (alpha < 0.0f || alpha > 1.0f) {
            throw new IllegalArgumentException("The 'alpha' value must be in the range 0.0f to 1.0f.");
        }
        if (this.backgroundImageAlpha != alpha) {
            this.backgroundImageAlpha = alpha;
            this.notifyListeners(new PlotChangeEvent(this));
        }
    }
    
    public boolean isOutlineVisible() {
        return this.outlineVisible;
    }
    
    public void setOutlineVisible(final boolean visible) {
        this.outlineVisible = visible;
        this.notifyListeners(new PlotChangeEvent(this));
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
    
    public abstract void draw(final Graphics2D p0, final Rectangle2D p1, final Point2D p2, final PlotState p3, final PlotRenderingInfo p4);
    
    public void drawBackground(final Graphics2D g2, final Rectangle2D area) {
        this.fillBackground(g2, area);
        this.drawBackgroundImage(g2, area);
    }
    
    protected void fillBackground(final Graphics2D g2, final Rectangle2D area) {
        this.fillBackground(g2, area, PlotOrientation.VERTICAL);
    }
    
    protected void fillBackground(final Graphics2D g2, final Rectangle2D area, final PlotOrientation orientation) {
        if (orientation == null) {
            throw new IllegalArgumentException("Null 'orientation' argument.");
        }
        if (this.backgroundPaint == null) {
            return;
        }
        Paint p = this.backgroundPaint;
        if (p instanceof GradientPaint) {
            final GradientPaint gp = (GradientPaint)p;
            if (orientation == PlotOrientation.VERTICAL) {
                p = new GradientPaint((float)area.getCenterX(), (float)area.getMaxY(), gp.getColor1(), (float)area.getCenterX(), (float)area.getMinY(), gp.getColor2());
            }
            else if (orientation == PlotOrientation.HORIZONTAL) {
                p = new GradientPaint((float)area.getMinX(), (float)area.getCenterY(), gp.getColor1(), (float)area.getMaxX(), (float)area.getCenterY(), gp.getColor2());
            }
        }
        final Composite originalComposite = g2.getComposite();
        g2.setComposite(AlphaComposite.getInstance(3, this.backgroundAlpha));
        g2.setPaint(p);
        g2.fill(area);
        g2.setComposite(originalComposite);
    }
    
    public void drawBackgroundImage(final Graphics2D g2, final Rectangle2D area) {
        if (this.backgroundImage != null) {
            final Composite originalComposite = g2.getComposite();
            g2.setComposite(AlphaComposite.getInstance(3, this.backgroundImageAlpha));
            final Rectangle2D dest = new Rectangle2D.Double(0.0, 0.0, this.backgroundImage.getWidth(null), this.backgroundImage.getHeight(null));
            Align.align(dest, area, this.backgroundImageAlignment);
            g2.drawImage(this.backgroundImage, (int)dest.getX(), (int)dest.getY(), (int)dest.getWidth() + 1, (int)dest.getHeight() + 1, null);
            g2.setComposite(originalComposite);
        }
    }
    
    public void drawOutline(final Graphics2D g2, final Rectangle2D area) {
        if (!this.outlineVisible) {
            return;
        }
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
            final TextBlock block = TextUtilities.createTextBlock(this.noDataMessage, this.noDataMessageFont, this.noDataMessagePaint, 0.9f * (float)area.getWidth(), new G2TextMeasurer(g2));
            block.draw(g2, (float)area.getCenterX(), (float)area.getCenterY(), TextBlockAnchor.CENTER);
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
        newEvent.setType(ChartChangeEventType.DATASET_UPDATED);
        this.notifyListeners(newEvent);
    }
    
    public void markerChanged(final MarkerChangeEvent event) {
        this.notifyListeners(new PlotChangeEvent(this));
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
    
    public boolean equals(final Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof Plot)) {
            return false;
        }
        final Plot that = (Plot)obj;
        return ObjectUtilities.equal(this.noDataMessage, that.noDataMessage) && ObjectUtilities.equal(this.noDataMessageFont, that.noDataMessageFont) && PaintUtilities.equal(this.noDataMessagePaint, that.noDataMessagePaint) && ObjectUtilities.equal(this.insets, that.insets) && this.outlineVisible == that.outlineVisible && ObjectUtilities.equal(this.outlineStroke, that.outlineStroke) && PaintUtilities.equal(this.outlinePaint, that.outlinePaint) && PaintUtilities.equal(this.backgroundPaint, that.backgroundPaint) && ObjectUtilities.equal(this.backgroundImage, that.backgroundImage) && this.backgroundImageAlignment == that.backgroundImageAlignment && this.backgroundImageAlpha == that.backgroundImageAlpha && this.foregroundAlpha == that.foregroundAlpha && this.backgroundAlpha == that.backgroundAlpha && this.drawingSupplier.equals(that.drawingSupplier);
    }
    
    public Object clone() throws CloneNotSupportedException {
        final Plot clone = (Plot)super.clone();
        if (this.datasetGroup != null) {
            clone.datasetGroup = (DatasetGroup)ObjectUtilities.clone(this.datasetGroup);
        }
        clone.drawingSupplier = (DrawingSupplier)ObjectUtilities.clone(this.drawingSupplier);
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
            throw new IllegalStateException("resolveDomainAxisLocation()");
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
            throw new IllegalStateException("resolveRangeAxisLocation()");
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
        DEFAULT_INSETS = new RectangleInsets(4.0, 8.0, 4.0, 8.0);
        DEFAULT_OUTLINE_STROKE = new BasicStroke(0.5f);
        DEFAULT_OUTLINE_PAINT = Color.gray;
        DEFAULT_BACKGROUND_PAINT = Color.white;
        DEFAULT_LEGEND_ITEM_BOX = new Rectangle2D.Double(-4.0, -4.0, 8.0, 8.0);
        DEFAULT_LEGEND_ITEM_CIRCLE = new Ellipse2D.Double(-4.0, -4.0, 8.0, 8.0);
    }
}
