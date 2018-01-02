// 
// Decompiled by Procyon v0.5.30
// 

package org.jfree.chart;

import java.io.ObjectInputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import org.jfree.chart.event.LegendChangeEvent;
import org.jfree.chart.event.LegendChangeListener;
import java.awt.geom.Rectangle2D;
import java.awt.Graphics2D;
import javax.swing.event.EventListenerList;
import java.io.Serializable;

public abstract class Legend implements Serializable, Cloneable
{
    private static final int NORTHWEST = 160;
    private static final int NORTHEAST = 176;
    private static final int SOUTHEAST = 192;
    private static final int SOUTHWEST = 208;
    public static final int WEST = 0;
    public static final int WEST_NORTHWEST = 160;
    public static final int WEST_SOUTHWEST = 208;
    public static final int NORTH = 1;
    public static final int NORTH_NORTHWEST = 161;
    public static final int NORTH_NORTHEAST = 177;
    public static final int EAST = 2;
    public static final int EAST_NORTHEAST = 178;
    public static final int EAST_SOUTHEAST = 194;
    public static final int SOUTH = 3;
    public static final int SOUTH_SOUTHWEST = 211;
    public static final int SOUTH_SOUTHEAST = 195;
    protected static final int INVERTED = 2;
    protected static final int HORIZONTAL = 1;
    private int anchor;
    private JFreeChart chart;
    private transient EventListenerList listenerList;
    static /* synthetic */ Class class$org$jfree$chart$event$LegendChangeListener;
    
    public static Legend createInstance(final JFreeChart chart) {
        return new StandardLegend();
    }
    
    public Legend() {
        this.anchor = 3;
        this.listenerList = new EventListenerList();
    }
    
    protected Legend(final JFreeChart chart) {
        this();
        this.chart = chart;
    }
    
    public JFreeChart getChart() {
        return this.chart;
    }
    
    protected void registerChart(final JFreeChart chart) {
        this.chart = chart;
    }
    
    public abstract Rectangle2D draw(final Graphics2D p0, final Rectangle2D p1, final ChartRenderingInfo p2);
    
    public void addChangeListener(final LegendChangeListener listener) {
        this.listenerList.add((Legend.class$org$jfree$chart$event$LegendChangeListener == null) ? (Legend.class$org$jfree$chart$event$LegendChangeListener = class$("org.jfree.chart.event.LegendChangeListener")) : Legend.class$org$jfree$chart$event$LegendChangeListener, listener);
    }
    
    public void removeChangeListener(final LegendChangeListener listener) {
        this.listenerList.remove((Legend.class$org$jfree$chart$event$LegendChangeListener == null) ? (Legend.class$org$jfree$chart$event$LegendChangeListener = class$("org.jfree.chart.event.LegendChangeListener")) : Legend.class$org$jfree$chart$event$LegendChangeListener, listener);
    }
    
    protected void notifyListeners(final LegendChangeEvent event) {
        final Object[] listeners = this.listenerList.getListenerList();
        for (int i = listeners.length - 2; i >= 0; i -= 2) {
            if (listeners[i] == ((Legend.class$org$jfree$chart$event$LegendChangeListener == null) ? (Legend.class$org$jfree$chart$event$LegendChangeListener = class$("org.jfree.chart.event.LegendChangeListener")) : Legend.class$org$jfree$chart$event$LegendChangeListener)) {
                ((LegendChangeListener)listeners[i + 1]).legendChanged(event);
            }
        }
    }
    
    public int getAnchor() {
        return this.anchor;
    }
    
    public void setAnchor(final int anchor) {
        if (this.isValidAnchor(anchor)) {
            this.anchor = anchor;
            this.notifyListeners(new LegendChangeEvent(this));
        }
    }
    
    private boolean isValidAnchor(final int anchor) {
        switch (anchor) {
            case 0:
            case 1:
            case 2:
            case 3:
            case 160:
            case 161:
            case 177:
            case 178:
            case 194:
            case 195:
            case 208:
            case 211: {
                return true;
            }
            default: {
                return false;
            }
        }
    }
    
    protected boolean isAnchoredToTop() {
        switch (this.anchor) {
            case 1:
            case 160:
            case 161:
            case 177:
            case 178: {
                return true;
            }
            default: {
                return false;
            }
        }
    }
    
    protected boolean isAnchoredToMiddle() {
        return this.anchor == 2 || this.anchor == 0;
    }
    
    protected boolean isAnchoredToBottom() {
        switch (this.anchor) {
            case 3:
            case 194:
            case 195:
            case 208:
            case 211: {
                return true;
            }
            default: {
                return false;
            }
        }
    }
    
    protected boolean isAnchoredToLeft() {
        switch (this.anchor) {
            case 0:
            case 160:
            case 161:
            case 208:
            case 211: {
                return true;
            }
            default: {
                return false;
            }
        }
    }
    
    protected boolean isAnchoredToRight() {
        switch (this.anchor) {
            case 2:
            case 177:
            case 178:
            case 194:
            case 195: {
                return true;
            }
            default: {
                return false;
            }
        }
    }
    
    protected boolean isAnchoredToCenter() {
        return this.anchor == 1 || this.anchor == 3;
    }
    
    public boolean equals(final Object obj) {
        if (obj == null) {
            return false;
        }
        if (obj == this) {
            return true;
        }
        if (obj instanceof Legend) {
            final Legend l = (Legend)obj;
            return this.anchor == l.anchor;
        }
        return false;
    }
    
    private void writeObject(final ObjectOutputStream stream) throws IOException {
        stream.defaultWriteObject();
    }
    
    private void readObject(final ObjectInputStream stream) throws IOException, ClassNotFoundException {
        stream.defaultReadObject();
        this.listenerList = new EventListenerList();
    }
    
    protected Object clone() throws CloneNotSupportedException {
        final Legend ret = (Legend)super.clone();
        this.listenerList = new EventListenerList();
        return ret;
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
