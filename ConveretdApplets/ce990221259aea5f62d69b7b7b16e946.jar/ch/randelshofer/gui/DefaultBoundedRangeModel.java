// 
// Decompiled by Procyon v0.5.30
// 

package ch.randelshofer.gui;

import java.util.EventListener;
import ch.randelshofer.gui.event.ChangeListener;
import ch.randelshofer.gui.event.EventListenerList;
import ch.randelshofer.gui.event.ChangeEvent;

public class DefaultBoundedRangeModel implements BoundedRangeModel
{
    protected transient ChangeEvent changeEvent_;
    protected EventListenerList listenerList_;
    private int value_;
    private int extent_;
    private int min_;
    private int max_;
    private boolean isAdjusting_;
    static /* synthetic */ Class class$ch$randelshofer$gui$event$ChangeListener;
    
    public DefaultBoundedRangeModel() {
        this.changeEvent_ = null;
        this.listenerList_ = new EventListenerList();
        this.value_ = 0;
        this.extent_ = 0;
        this.min_ = 0;
        this.max_ = 100;
        this.isAdjusting_ = false;
    }
    
    public DefaultBoundedRangeModel(final int value_, final int extent_, final int min_, final int max_) {
        this.changeEvent_ = null;
        this.listenerList_ = new EventListenerList();
        this.value_ = 0;
        this.extent_ = 0;
        this.min_ = 0;
        this.max_ = 100;
        this.isAdjusting_ = false;
        if (max_ >= min_ && value_ >= min_ && value_ + extent_ <= max_) {
            this.value_ = value_;
            this.extent_ = extent_;
            this.min_ = min_;
            this.max_ = max_;
            return;
        }
        throw new IllegalArgumentException("invalid range properties");
    }
    
    public int getValue() {
        return this.value_;
    }
    
    public int getExtent() {
        return this.extent_;
    }
    
    public int getMinimum() {
        return this.min_;
    }
    
    public int getMaximum() {
        return this.max_;
    }
    
    public void setValue(final int n) {
        int max = Math.max(n, this.min_);
        if (max + this.extent_ > this.max_) {
            max = this.max_ - this.extent_;
        }
        this.setRangeProperties(max, this.extent_, this.min_, this.max_, this.isAdjusting_);
    }
    
    public void setExtent(final int n) {
        int max = Math.max(0, n);
        if (this.value_ + max > this.max_) {
            max = this.max_ - this.value_;
        }
        this.setRangeProperties(this.value_, max, this.min_, this.max_, this.isAdjusting_);
    }
    
    public void setMinimum(final int n) {
        final int max = Math.max(n, this.max_);
        final int max2 = Math.max(n, this.value_);
        this.setRangeProperties(max2, Math.min(max - max2, this.extent_), n, max, this.isAdjusting_);
    }
    
    public void setMaximum(final int n) {
        final int min = Math.min(n, this.min_);
        final int min2 = Math.min(n, this.value_);
        this.setRangeProperties(min2, Math.min(n - min2, this.extent_), min, n, this.isAdjusting_);
    }
    
    public void setValueIsAdjusting(final boolean b) {
        this.setRangeProperties(this.value_, this.extent_, this.min_, this.max_, b);
    }
    
    public boolean getValueIsAdjusting() {
        return this.isAdjusting_;
    }
    
    public void setRangeProperties(final int value_, int extent_, int min_, int max_, final boolean isAdjusting_) {
        if (min_ > max_) {
            min_ = max_;
        }
        if (value_ > max_) {
            max_ = value_;
        }
        if (value_ < min_) {
            min_ = value_;
        }
        if (extent_ + value_ > max_) {
            extent_ = max_ - value_;
        }
        if (extent_ < 0) {
            extent_ = 0;
        }
        if (value_ != this.value_ || extent_ != this.extent_ || min_ != this.min_ || max_ != this.max_ || isAdjusting_ != this.isAdjusting_) {
            this.value_ = value_;
            this.extent_ = extent_;
            this.min_ = min_;
            this.max_ = max_;
            this.isAdjusting_ = isAdjusting_;
            this.fireStateChanged();
        }
    }
    
    public void addChangeListener(final ChangeListener changeListener) {
        this.listenerList_.add((DefaultBoundedRangeModel.class$ch$randelshofer$gui$event$ChangeListener == null) ? (DefaultBoundedRangeModel.class$ch$randelshofer$gui$event$ChangeListener = class$("ch.randelshofer.gui.event.ChangeListener")) : DefaultBoundedRangeModel.class$ch$randelshofer$gui$event$ChangeListener, changeListener);
    }
    
    public void removeChangeListener(final ChangeListener changeListener) {
        this.listenerList_.remove((DefaultBoundedRangeModel.class$ch$randelshofer$gui$event$ChangeListener == null) ? (DefaultBoundedRangeModel.class$ch$randelshofer$gui$event$ChangeListener = class$("ch.randelshofer.gui.event.ChangeListener")) : DefaultBoundedRangeModel.class$ch$randelshofer$gui$event$ChangeListener, changeListener);
    }
    
    protected void fireStateChanged() {
        final Object[] listenerList = this.listenerList_.getListenerList();
        for (int i = listenerList.length - 2; i >= 0; i -= 2) {
            if (listenerList[i] == ((DefaultBoundedRangeModel.class$ch$randelshofer$gui$event$ChangeListener == null) ? (DefaultBoundedRangeModel.class$ch$randelshofer$gui$event$ChangeListener = class$("ch.randelshofer.gui.event.ChangeListener")) : DefaultBoundedRangeModel.class$ch$randelshofer$gui$event$ChangeListener)) {
                if (this.changeEvent_ == null) {
                    this.changeEvent_ = new ChangeEvent(this);
                }
                ((ChangeListener)listenerList[i + 1]).stateChanged(this.changeEvent_);
            }
        }
    }
    
    public String toString() {
        return this.getClass().getName() + "[" + ("value_=" + this.getValue() + ", " + "extent_=" + this.getExtent() + ", " + "min_=" + this.getMinimum() + ", " + "max_=" + this.getMaximum() + ", " + "adj=" + this.getValueIsAdjusting()) + "]";
    }
    
    static /* synthetic */ Class class$(final String s) {
        try {
            return Class.forName(s);
        }
        catch (ClassNotFoundException ex) {
            throw new NoClassDefFoundError(ex.getMessage());
        }
    }
}
