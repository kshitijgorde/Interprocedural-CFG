// 
// Decompiled by Procyon v0.5.30
// 

package org.jfree.chart.title;

import java.io.ObjectInputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import org.jfree.util.ObjectUtils;
import org.jfree.chart.event.TitleChangeListener;
import java.awt.geom.Rectangle2D;
import java.awt.Graphics2D;
import org.jfree.chart.event.TitleChangeEvent;
import javax.swing.event.EventListenerList;
import org.jfree.ui.Spacer;
import org.jfree.ui.VerticalAlignment;
import org.jfree.ui.HorizontalAlignment;
import org.jfree.ui.RectangleEdge;
import java.io.Serializable;

public abstract class Title implements Cloneable, Serializable
{
    public static final int TOP = 0;
    public static final int BOTTOM = 1;
    public static final int RIGHT = 2;
    public static final int LEFT = 3;
    public static final int NORTH = 0;
    public static final int SOUTH = 1;
    public static final int EAST = 2;
    public static final int WEST = 3;
    public static final int CENTER = 4;
    public static final int MIDDLE = 4;
    public static final RectangleEdge DEFAULT_POSITION;
    public static final HorizontalAlignment DEFAULT_HORIZONTAL_ALIGNMENT;
    public static final VerticalAlignment DEFAULT_VERTICAL_ALIGNMENT;
    public static final Spacer DEFAULT_SPACER;
    private RectangleEdge position;
    private HorizontalAlignment horizontalAlignment;
    private VerticalAlignment verticalAlignment;
    private Spacer spacer;
    private transient EventListenerList listenerList;
    private boolean notify;
    static /* synthetic */ Class class$org$jfree$chart$event$TitleChangeListener;
    
    protected Title() {
        this(Title.DEFAULT_POSITION, Title.DEFAULT_HORIZONTAL_ALIGNMENT, Title.DEFAULT_VERTICAL_ALIGNMENT, Title.DEFAULT_SPACER);
    }
    
    protected Title(final RectangleEdge position, final HorizontalAlignment horizontalAlignment, final VerticalAlignment verticalAlignment) {
        this(position, horizontalAlignment, verticalAlignment, Title.DEFAULT_SPACER);
    }
    
    protected Title(final RectangleEdge position, final HorizontalAlignment horizontalAlignment, final VerticalAlignment verticalAlignment, final Spacer spacer) {
        if (position == null) {
            throw new IllegalArgumentException("Argument 'position' cannot be null.");
        }
        if (horizontalAlignment == null) {
            throw new IllegalArgumentException("Argument 'horizontalAlignment' cannot be null.");
        }
        if (verticalAlignment == null) {
            throw new IllegalArgumentException("Argument 'verticalAlignment' cannot be null.");
        }
        if (spacer == null) {
            throw new IllegalArgumentException("Argument 'spacer' cannot be null.");
        }
        this.position = position;
        this.horizontalAlignment = horizontalAlignment;
        this.verticalAlignment = verticalAlignment;
        this.spacer = spacer;
        this.listenerList = new EventListenerList();
        this.notify = true;
    }
    
    public RectangleEdge getPosition() {
        return this.position;
    }
    
    public void setPosition(final RectangleEdge position) {
        if (position == null) {
            throw new IllegalArgumentException("Null 'position' argument.");
        }
        if (this.position != position) {
            this.position = position;
            this.notifyListeners(new TitleChangeEvent(this));
        }
    }
    
    public HorizontalAlignment getHorizontalAlignment() {
        return this.horizontalAlignment;
    }
    
    public void setHorizontalAlignment(final HorizontalAlignment alignment) {
        if (alignment == null) {
            throw new IllegalArgumentException("Null 'alignment' argument.");
        }
        if (this.horizontalAlignment != alignment) {
            this.horizontalAlignment = alignment;
            this.notifyListeners(new TitleChangeEvent(this));
        }
    }
    
    public VerticalAlignment getVerticalAlignment() {
        return this.verticalAlignment;
    }
    
    public void setVerticalAlignment(final VerticalAlignment alignment) {
        if (alignment == null) {
            throw new IllegalArgumentException("Argument 'alignment' cannot be null.");
        }
        if (this.verticalAlignment != alignment) {
            this.verticalAlignment = alignment;
            this.notifyListeners(new TitleChangeEvent(this));
        }
    }
    
    public Spacer getSpacer() {
        return this.spacer;
    }
    
    public void setSpacer(final Spacer spacer) {
        if (spacer == null) {
            throw new NullPointerException("AbstractTitle.setSpacer(): null not permitted.");
        }
        if (!this.spacer.equals(spacer)) {
            this.spacer = spacer;
            this.notifyListeners(new TitleChangeEvent(this));
        }
    }
    
    public boolean getNotify() {
        return this.notify;
    }
    
    public void setNotify(final boolean flag) {
        this.notify = flag;
    }
    
    public abstract float getPreferredWidth(final Graphics2D p0, final float p1);
    
    public abstract float getPreferredHeight(final Graphics2D p0, final float p1);
    
    public abstract void draw(final Graphics2D p0, final Rectangle2D p1);
    
    public Object clone() throws CloneNotSupportedException {
        final Title duplicate = (Title)super.clone();
        duplicate.listenerList = new EventListenerList();
        return duplicate;
    }
    
    public void addChangeListener(final TitleChangeListener listener) {
        this.listenerList.add((Title.class$org$jfree$chart$event$TitleChangeListener == null) ? (Title.class$org$jfree$chart$event$TitleChangeListener = class$("org.jfree.chart.event.TitleChangeListener")) : Title.class$org$jfree$chart$event$TitleChangeListener, listener);
    }
    
    public void removeChangeListener(final TitleChangeListener listener) {
        this.listenerList.remove((Title.class$org$jfree$chart$event$TitleChangeListener == null) ? (Title.class$org$jfree$chart$event$TitleChangeListener = class$("org.jfree.chart.event.TitleChangeListener")) : Title.class$org$jfree$chart$event$TitleChangeListener, listener);
    }
    
    protected void notifyListeners(final TitleChangeEvent event) {
        if (this.notify) {
            final Object[] listeners = this.listenerList.getListenerList();
            for (int i = listeners.length - 2; i >= 0; i -= 2) {
                if (listeners[i] == ((Title.class$org$jfree$chart$event$TitleChangeListener == null) ? (Title.class$org$jfree$chart$event$TitleChangeListener = class$("org.jfree.chart.event.TitleChangeListener")) : Title.class$org$jfree$chart$event$TitleChangeListener)) {
                    ((TitleChangeListener)listeners[i + 1]).titleChanged(event);
                }
            }
        }
    }
    
    public boolean equals(final Object obj) {
        if (obj == null) {
            return false;
        }
        if (obj == this) {
            return true;
        }
        if (obj instanceof Title) {
            final Title t = (Title)obj;
            return this.position == t.position && this.horizontalAlignment == t.horizontalAlignment && this.verticalAlignment == t.verticalAlignment && ObjectUtils.equal(this.spacer, t.spacer) && this.notify == t.notify;
        }
        return false;
    }
    
    public int hashCode() {
        int result = 193;
        result = 37 * result + ObjectUtils.hashCode(this.position);
        result = 37 * result + ObjectUtils.hashCode(this.horizontalAlignment);
        result = 37 * result + ObjectUtils.hashCode(this.verticalAlignment);
        result = 37 * result + ObjectUtils.hashCode(this.spacer);
        return result;
    }
    
    private void writeObject(final ObjectOutputStream stream) throws IOException {
        stream.defaultWriteObject();
    }
    
    private void readObject(final ObjectInputStream stream) throws IOException, ClassNotFoundException {
        stream.defaultReadObject();
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
        DEFAULT_POSITION = RectangleEdge.TOP;
        DEFAULT_HORIZONTAL_ALIGNMENT = HorizontalAlignment.CENTER;
        DEFAULT_VERTICAL_ALIGNMENT = VerticalAlignment.CENTER;
        DEFAULT_SPACER = new Spacer(0, 0.01, 0.15, 0.01, 0.15);
    }
}
