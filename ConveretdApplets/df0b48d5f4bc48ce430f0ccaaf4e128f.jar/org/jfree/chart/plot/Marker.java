// 
// Decompiled by Procyon v0.5.30
// 

package org.jfree.chart.plot;

import java.io.ObjectInputStream;
import java.io.IOException;
import org.jfree.io.SerialUtilities;
import java.io.ObjectOutputStream;
import org.jfree.util.ObjectUtilities;
import org.jfree.util.PaintUtilities;
import java.util.EventListener;
import org.jfree.chart.event.MarkerChangeListener;
import org.jfree.chart.event.MarkerChangeEvent;
import java.awt.BasicStroke;
import java.awt.Color;
import javax.swing.event.EventListenerList;
import org.jfree.ui.LengthAdjustmentType;
import org.jfree.ui.RectangleInsets;
import org.jfree.ui.TextAnchor;
import org.jfree.ui.RectangleAnchor;
import java.awt.Font;
import java.awt.Stroke;
import java.awt.Paint;
import java.io.Serializable;

public abstract class Marker implements Cloneable, Serializable
{
    private static final long serialVersionUID = -734389651405327166L;
    private transient Paint paint;
    private transient Stroke stroke;
    private transient Paint outlinePaint;
    private transient Stroke outlineStroke;
    private float alpha;
    private String label;
    private Font labelFont;
    private transient Paint labelPaint;
    private RectangleAnchor labelAnchor;
    private TextAnchor labelTextAnchor;
    private RectangleInsets labelOffset;
    private LengthAdjustmentType labelOffsetType;
    private transient EventListenerList listenerList;
    static /* synthetic */ Class class$org$jfree$chart$event$MarkerChangeListener;
    
    protected Marker() {
        this(Color.gray);
    }
    
    protected Marker(final Paint paint) {
        this(paint, new BasicStroke(0.5f), Color.gray, new BasicStroke(0.5f), 0.8f);
    }
    
    protected Marker(final Paint paint, final Stroke stroke, final Paint outlinePaint, final Stroke outlineStroke, final float alpha) {
        this.label = null;
        if (paint == null) {
            throw new IllegalArgumentException("Null 'paint' argument.");
        }
        if (stroke == null) {
            throw new IllegalArgumentException("Null 'stroke' argument.");
        }
        if (alpha < 0.0f || alpha > 1.0f) {
            throw new IllegalArgumentException("The 'alpha' value must be in the range 0.0f to 1.0f");
        }
        this.paint = paint;
        this.stroke = stroke;
        this.outlinePaint = outlinePaint;
        this.outlineStroke = outlineStroke;
        this.alpha = alpha;
        this.labelFont = new Font("SansSerif", 0, 9);
        this.labelPaint = Color.black;
        this.labelAnchor = RectangleAnchor.TOP_LEFT;
        this.labelOffset = new RectangleInsets(3.0, 3.0, 3.0, 3.0);
        this.labelOffsetType = LengthAdjustmentType.CONTRACT;
        this.labelTextAnchor = TextAnchor.CENTER;
        this.listenerList = new EventListenerList();
    }
    
    public Paint getPaint() {
        return this.paint;
    }
    
    public void setPaint(final Paint paint) {
        if (paint == null) {
            throw new IllegalArgumentException("Null 'paint' argument.");
        }
        this.paint = paint;
        this.notifyListeners(new MarkerChangeEvent(this));
    }
    
    public Stroke getStroke() {
        return this.stroke;
    }
    
    public void setStroke(final Stroke stroke) {
        if (stroke == null) {
            throw new IllegalArgumentException("Null 'stroke' argument.");
        }
        this.stroke = stroke;
        this.notifyListeners(new MarkerChangeEvent(this));
    }
    
    public Paint getOutlinePaint() {
        return this.outlinePaint;
    }
    
    public void setOutlinePaint(final Paint paint) {
        this.outlinePaint = paint;
        this.notifyListeners(new MarkerChangeEvent(this));
    }
    
    public Stroke getOutlineStroke() {
        return this.outlineStroke;
    }
    
    public void setOutlineStroke(final Stroke stroke) {
        this.outlineStroke = stroke;
        this.notifyListeners(new MarkerChangeEvent(this));
    }
    
    public float getAlpha() {
        return this.alpha;
    }
    
    public void setAlpha(final float alpha) {
        if (alpha < 0.0f || alpha > 1.0f) {
            throw new IllegalArgumentException("The 'alpha' value must be in the range 0.0f to 1.0f");
        }
        this.alpha = alpha;
        this.notifyListeners(new MarkerChangeEvent(this));
    }
    
    public String getLabel() {
        return this.label;
    }
    
    public void setLabel(final String label) {
        this.label = label;
        this.notifyListeners(new MarkerChangeEvent(this));
    }
    
    public Font getLabelFont() {
        return this.labelFont;
    }
    
    public void setLabelFont(final Font font) {
        if (font == null) {
            throw new IllegalArgumentException("Null 'font' argument.");
        }
        this.labelFont = font;
        this.notifyListeners(new MarkerChangeEvent(this));
    }
    
    public Paint getLabelPaint() {
        return this.labelPaint;
    }
    
    public void setLabelPaint(final Paint paint) {
        if (paint == null) {
            throw new IllegalArgumentException("Null 'paint' argument.");
        }
        this.labelPaint = paint;
        this.notifyListeners(new MarkerChangeEvent(this));
    }
    
    public RectangleAnchor getLabelAnchor() {
        return this.labelAnchor;
    }
    
    public void setLabelAnchor(final RectangleAnchor anchor) {
        if (anchor == null) {
            throw new IllegalArgumentException("Null 'anchor' argument.");
        }
        this.labelAnchor = anchor;
        this.notifyListeners(new MarkerChangeEvent(this));
    }
    
    public RectangleInsets getLabelOffset() {
        return this.labelOffset;
    }
    
    public void setLabelOffset(final RectangleInsets offset) {
        if (offset == null) {
            throw new IllegalArgumentException("Null 'offset' argument.");
        }
        this.labelOffset = offset;
        this.notifyListeners(new MarkerChangeEvent(this));
    }
    
    public LengthAdjustmentType getLabelOffsetType() {
        return this.labelOffsetType;
    }
    
    public void setLabelOffsetType(final LengthAdjustmentType adj) {
        if (adj == null) {
            throw new IllegalArgumentException("Null 'adj' argument.");
        }
        this.labelOffsetType = adj;
        this.notifyListeners(new MarkerChangeEvent(this));
    }
    
    public TextAnchor getLabelTextAnchor() {
        return this.labelTextAnchor;
    }
    
    public void setLabelTextAnchor(final TextAnchor anchor) {
        if (anchor == null) {
            throw new IllegalArgumentException("Null 'anchor' argument.");
        }
        this.labelTextAnchor = anchor;
        this.notifyListeners(new MarkerChangeEvent(this));
    }
    
    public void addChangeListener(final MarkerChangeListener listener) {
        this.listenerList.add((Marker.class$org$jfree$chart$event$MarkerChangeListener == null) ? (Marker.class$org$jfree$chart$event$MarkerChangeListener = class$("org.jfree.chart.event.MarkerChangeListener")) : Marker.class$org$jfree$chart$event$MarkerChangeListener, listener);
    }
    
    public void removeChangeListener(final MarkerChangeListener listener) {
        this.listenerList.remove((Marker.class$org$jfree$chart$event$MarkerChangeListener == null) ? (Marker.class$org$jfree$chart$event$MarkerChangeListener = class$("org.jfree.chart.event.MarkerChangeListener")) : Marker.class$org$jfree$chart$event$MarkerChangeListener, listener);
    }
    
    public void notifyListeners(final MarkerChangeEvent event) {
        final Object[] listeners = this.listenerList.getListenerList();
        for (int i = listeners.length - 2; i >= 0; i -= 2) {
            if (listeners[i] == ((Marker.class$org$jfree$chart$event$MarkerChangeListener == null) ? (Marker.class$org$jfree$chart$event$MarkerChangeListener = class$("org.jfree.chart.event.MarkerChangeListener")) : Marker.class$org$jfree$chart$event$MarkerChangeListener)) {
                ((MarkerChangeListener)listeners[i + 1]).markerChanged(event);
            }
        }
    }
    
    public EventListener[] getListeners(final Class listenerType) {
        return this.listenerList.getListeners((Class<EventListener>)listenerType);
    }
    
    public boolean equals(final Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof Marker)) {
            return false;
        }
        final Marker that = (Marker)obj;
        return PaintUtilities.equal(this.paint, that.paint) && ObjectUtilities.equal(this.stroke, that.stroke) && PaintUtilities.equal(this.outlinePaint, that.outlinePaint) && ObjectUtilities.equal(this.outlineStroke, that.outlineStroke) && this.alpha == that.alpha && ObjectUtilities.equal(this.label, that.label) && ObjectUtilities.equal(this.labelFont, that.labelFont) && PaintUtilities.equal(this.labelPaint, that.labelPaint) && this.labelAnchor == that.labelAnchor && this.labelTextAnchor == that.labelTextAnchor && ObjectUtilities.equal(this.labelOffset, that.labelOffset) && this.labelOffsetType.equals(that.labelOffsetType);
    }
    
    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
    
    private void writeObject(final ObjectOutputStream stream) throws IOException {
        stream.defaultWriteObject();
        SerialUtilities.writePaint(this.paint, stream);
        SerialUtilities.writeStroke(this.stroke, stream);
        SerialUtilities.writePaint(this.outlinePaint, stream);
        SerialUtilities.writeStroke(this.outlineStroke, stream);
        SerialUtilities.writePaint(this.labelPaint, stream);
    }
    
    private void readObject(final ObjectInputStream stream) throws IOException, ClassNotFoundException {
        stream.defaultReadObject();
        this.paint = SerialUtilities.readPaint(stream);
        this.stroke = SerialUtilities.readStroke(stream);
        this.outlinePaint = SerialUtilities.readPaint(stream);
        this.outlineStroke = SerialUtilities.readStroke(stream);
        this.labelPaint = SerialUtilities.readPaint(stream);
    }
    
    static /* synthetic */ Class class$(final String x0) {
        try {
            return Class.forName(x0);
        }
        catch (ClassNotFoundException x) {
            throw new NoClassDefFoundError(x.getMessage());
        }
    }
}
