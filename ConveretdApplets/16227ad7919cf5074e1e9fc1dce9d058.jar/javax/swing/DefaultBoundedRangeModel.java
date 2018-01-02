// 
// Decompiled by Procyon v0.5.30
// 

package javax.swing;

import java.util.EventListener;
import javax.swing.event.ChangeListener;
import javax.swing.event.EventListenerList;
import javax.swing.event.ChangeEvent;
import java.io.Serializable;

public class DefaultBoundedRangeModel implements BoundedRangeModel, Serializable
{
    protected transient ChangeEvent changeEvent;
    protected EventListenerList listenerList;
    private int value;
    private int extent;
    private int min;
    private int max;
    private boolean isAdjusting;
    static /* synthetic */ Class class$javax$swing$event$ChangeListener;
    
    public DefaultBoundedRangeModel() {
        this.changeEvent = null;
        this.listenerList = new EventListenerList();
        this.value = 0;
        this.extent = 0;
        this.min = 0;
        this.max = 100;
        this.isAdjusting = false;
    }
    
    public DefaultBoundedRangeModel(final int value, final int extent, final int min, final int max) {
        this.changeEvent = null;
        this.listenerList = new EventListenerList();
        this.value = 0;
        this.extent = 0;
        this.min = 0;
        this.max = 100;
        this.isAdjusting = false;
        if (max >= min && value >= min && value + extent >= value && value + extent <= max) {
            this.value = value;
            this.extent = extent;
            this.min = min;
            this.max = max;
            return;
        }
        throw new IllegalArgumentException("invalid range properties");
    }
    
    public void addChangeListener(final ChangeListener changeListener) {
        this.listenerList.add((DefaultBoundedRangeModel.class$javax$swing$event$ChangeListener != null) ? DefaultBoundedRangeModel.class$javax$swing$event$ChangeListener : (DefaultBoundedRangeModel.class$javax$swing$event$ChangeListener = class$("javax.swing.event.ChangeListener")), changeListener);
    }
    
    static /* synthetic */ Class class$(final String s) {
        try {
            return Class.forName(s);
        }
        catch (ClassNotFoundException ex) {
            throw new NoClassDefFoundError(ex.getMessage());
        }
    }
    
    protected void fireStateChanged() {
        final Object[] listenerList = this.listenerList.getListenerList();
        for (int i = listenerList.length - 2; i >= 0; i -= 2) {
            if (listenerList[i] == ((DefaultBoundedRangeModel.class$javax$swing$event$ChangeListener != null) ? DefaultBoundedRangeModel.class$javax$swing$event$ChangeListener : (DefaultBoundedRangeModel.class$javax$swing$event$ChangeListener = class$("javax.swing.event.ChangeListener")))) {
                if (this.changeEvent == null) {
                    this.changeEvent = new ChangeEvent(this);
                }
                ((ChangeListener)listenerList[i + 1]).stateChanged(this.changeEvent);
            }
        }
    }
    
    public int getExtent() {
        return this.extent;
    }
    
    public int getMaximum() {
        return this.max;
    }
    
    public int getMinimum() {
        return this.min;
    }
    
    public int getValue() {
        return this.value;
    }
    
    public boolean getValueIsAdjusting() {
        return this.isAdjusting;
    }
    
    public void removeChangeListener(final ChangeListener changeListener) {
        this.listenerList.remove((DefaultBoundedRangeModel.class$javax$swing$event$ChangeListener != null) ? DefaultBoundedRangeModel.class$javax$swing$event$ChangeListener : (DefaultBoundedRangeModel.class$javax$swing$event$ChangeListener = class$("javax.swing.event.ChangeListener")), changeListener);
    }
    
    public void setExtent(final int n) {
        int max = Math.max(0, n);
        if (this.value + max > this.max) {
            max = this.max - this.value;
        }
        this.setRangeProperties(this.value, max, this.min, this.max, this.isAdjusting);
    }
    
    public void setMaximum(final int n) {
        final int min = Math.min(n, this.min);
        final int min2 = Math.min(n, this.value);
        this.setRangeProperties(min2, Math.min(n - min2, this.extent), min, n, this.isAdjusting);
    }
    
    public void setMinimum(final int n) {
        final int max = Math.max(n, this.max);
        final int max2 = Math.max(n, this.value);
        this.setRangeProperties(max2, Math.min(max - max2, this.extent), n, max, this.isAdjusting);
    }
    
    public void setRangeProperties(final int value, int extent, int min, int max, final boolean isAdjusting) {
        if (min > max) {
            min = max;
        }
        if (value > max) {
            max = value;
        }
        if (value < min) {
            min = value;
        }
        if (extent + value > max) {
            extent = max - value;
        }
        if (extent < 0) {
            extent = 0;
        }
        if (value != this.value || extent != this.extent || min != this.min || max != this.max || isAdjusting != this.isAdjusting) {
            this.value = value;
            this.extent = extent;
            this.min = min;
            this.max = max;
            this.isAdjusting = isAdjusting;
            this.fireStateChanged();
        }
    }
    
    public void setValue(final int n) {
        int max = Math.max(n, this.min);
        if (max + this.extent > this.max) {
            max = this.max - this.extent;
        }
        this.setRangeProperties(max, this.extent, this.min, this.max, this.isAdjusting);
    }
    
    public void setValueIsAdjusting(final boolean b) {
        this.setRangeProperties(this.value, this.extent, this.min, this.max, b);
    }
    
    public String toString() {
        return String.valueOf(this.getClass().getName()) + "[" + ("value=" + this.getValue() + ", " + "extent=" + this.getExtent() + ", " + "min=" + this.getMinimum() + ", " + "max=" + this.getMaximum() + ", " + "adj=" + this.getValueIsAdjusting()) + "]";
    }
}
