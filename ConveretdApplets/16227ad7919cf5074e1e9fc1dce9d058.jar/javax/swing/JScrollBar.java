// 
// Decompiled by Procyon v0.5.30
// 

package javax.swing;

import javax.accessibility.AccessibleStateSet;
import javax.accessibility.AccessibleRole;
import javax.accessibility.AccessibleValue;
import javax.swing.event.ChangeEvent;
import java.io.Serializable;
import java.io.IOException;
import java.io.ObjectOutputStream;
import javax.swing.plaf.ComponentUI;
import javax.accessibility.AccessibleState;
import java.awt.Component;
import javax.swing.plaf.ScrollBarUI;
import java.awt.Dimension;
import javax.accessibility.AccessibleContext;
import java.awt.event.AdjustmentEvent;
import java.util.EventListener;
import java.awt.event.AdjustmentListener;
import javax.swing.event.ChangeListener;
import javax.accessibility.Accessible;
import java.awt.Adjustable;

public class JScrollBar extends JComponent implements Adjustable, Accessible
{
    private static final String uiClassID = "ScrollBarUI";
    private ChangeListener fwdAdjustmentEvents;
    protected BoundedRangeModel model;
    protected int orientation;
    protected int unitIncrement;
    protected int blockIncrement;
    static /* synthetic */ Class class$java$awt$event$AdjustmentListener;
    
    public JScrollBar() {
        this(1);
    }
    
    public JScrollBar(final int n) {
        this(n, 0, 10, 0, 100);
    }
    
    public JScrollBar(final int orientation, final int n, final int n2, final int n3, final int n4) {
        this.fwdAdjustmentEvents = new ModelListener();
        this.checkOrientation(orientation);
        this.unitIncrement = 1;
        this.blockIncrement = ((n2 == 0) ? 1 : n2);
        this.orientation = orientation;
        (this.model = new DefaultBoundedRangeModel(n, n2, n3, n4)).addChangeListener(this.fwdAdjustmentEvents);
        this.updateUI();
    }
    
    public void addAdjustmentListener(final AdjustmentListener adjustmentListener) {
        super.listenerList.add((JScrollBar.class$java$awt$event$AdjustmentListener != null) ? JScrollBar.class$java$awt$event$AdjustmentListener : (JScrollBar.class$java$awt$event$AdjustmentListener = class$("java.awt.event.AdjustmentListener")), adjustmentListener);
    }
    
    private void checkOrientation(final int n) {
        switch (n) {
            default: {
                throw new IllegalArgumentException("orientation must be one of: VERTICAL, HORIZONTAL");
            }
            case 0:
            case 1: {}
        }
    }
    
    static /* synthetic */ Class class$(final String s) {
        try {
            return Class.forName(s);
        }
        catch (ClassNotFoundException ex) {
            throw new NoClassDefFoundError(ex.getMessage());
        }
    }
    
    protected void fireAdjustmentValueChanged(final int n, final int n2, final int n3) {
        final Object[] listenerList = super.listenerList.getListenerList();
        AdjustmentEvent adjustmentEvent = null;
        for (int i = listenerList.length - 2; i >= 0; i -= 2) {
            if (listenerList[i] == ((JScrollBar.class$java$awt$event$AdjustmentListener != null) ? JScrollBar.class$java$awt$event$AdjustmentListener : (JScrollBar.class$java$awt$event$AdjustmentListener = class$("java.awt.event.AdjustmentListener")))) {
                if (adjustmentEvent == null) {
                    adjustmentEvent = new AdjustmentEvent(this, n, n2, n3);
                }
                ((AdjustmentListener)listenerList[i + 1]).adjustmentValueChanged(adjustmentEvent);
            }
        }
    }
    
    public AccessibleContext getAccessibleContext() {
        if (super.accessibleContext == null) {
            super.accessibleContext = new AccessibleJScrollBar();
        }
        return super.accessibleContext;
    }
    
    public int getBlockIncrement() {
        return this.blockIncrement;
    }
    
    public int getBlockIncrement(final int n) {
        return this.blockIncrement;
    }
    
    public int getMaximum() {
        return this.getModel().getMaximum();
    }
    
    public Dimension getMaximumSize() {
        final Dimension preferredSize = this.getPreferredSize();
        if (this.getOrientation() == 1) {
            return new Dimension(preferredSize.width, 32767);
        }
        return new Dimension(32767, preferredSize.height);
    }
    
    public int getMinimum() {
        return this.getModel().getMinimum();
    }
    
    public Dimension getMinimumSize() {
        final Dimension preferredSize = this.getPreferredSize();
        if (this.orientation == 1) {
            return new Dimension(preferredSize.width, 5);
        }
        return new Dimension(5, preferredSize.height);
    }
    
    public BoundedRangeModel getModel() {
        return this.model;
    }
    
    public int getOrientation() {
        return this.orientation;
    }
    
    public ScrollBarUI getUI() {
        return (ScrollBarUI)super.ui;
    }
    
    public String getUIClassID() {
        return "ScrollBarUI";
    }
    
    public int getUnitIncrement() {
        return this.unitIncrement;
    }
    
    public int getUnitIncrement(final int n) {
        return this.unitIncrement;
    }
    
    public int getValue() {
        return this.getModel().getValue();
    }
    
    public boolean getValueIsAdjusting() {
        return this.getModel().getValueIsAdjusting();
    }
    
    public int getVisibleAmount() {
        return this.getModel().getExtent();
    }
    
    protected String paramString() {
        return String.valueOf(super.paramString()) + ",blockIncrement=" + this.blockIncrement + ",orientation=" + ((this.orientation == 0) ? "HORIZONTAL" : "VERTICAL") + ",unitIncrement=" + this.unitIncrement;
    }
    
    public void removeAdjustmentListener(final AdjustmentListener adjustmentListener) {
        super.listenerList.remove((JScrollBar.class$java$awt$event$AdjustmentListener != null) ? JScrollBar.class$java$awt$event$AdjustmentListener : (JScrollBar.class$java$awt$event$AdjustmentListener = class$("java.awt.event.AdjustmentListener")), adjustmentListener);
    }
    
    public void setBlockIncrement(final int blockIncrement) {
        this.firePropertyChange("blockIncrement", this.blockIncrement, this.blockIncrement = blockIncrement);
    }
    
    public void setEnabled(final boolean b) {
        super.setEnabled(b);
        final Component[] components = this.getComponents();
        for (int i = 0; i < components.length; ++i) {
            components[i].setEnabled(b);
        }
    }
    
    public void setMaximum(final int maximum) {
        this.getModel().setMaximum(maximum);
    }
    
    public void setMinimum(final int minimum) {
        this.getModel().setMinimum(minimum);
    }
    
    public void setModel(final BoundedRangeModel model) {
        Object o = null;
        final BoundedRangeModel model2 = this.model;
        if (this.model != null) {
            this.model.removeChangeListener(this.fwdAdjustmentEvents);
            o = new Integer(this.model.getValue());
        }
        this.model = model;
        if (this.model != null) {
            this.model.addChangeListener(this.fwdAdjustmentEvents);
        }
        this.firePropertyChange("model", model2, this.model);
        if (super.accessibleContext != null) {
            super.accessibleContext.firePropertyChange("AccessibleValue", o, new Integer(this.model.getValue()));
        }
    }
    
    public void setOrientation(final int orientation) {
        this.checkOrientation(orientation);
        final int orientation2 = this.orientation;
        this.firePropertyChange("orientation", orientation2, this.orientation = orientation);
        if (orientation2 != orientation && super.accessibleContext != null) {
            super.accessibleContext.firePropertyChange("AccessibleState", (orientation2 == 1) ? AccessibleState.VERTICAL : AccessibleState.HORIZONTAL, (orientation == 1) ? AccessibleState.VERTICAL : AccessibleState.HORIZONTAL);
        }
        if (orientation != orientation2) {
            this.revalidate();
        }
    }
    
    public void setUnitIncrement(final int unitIncrement) {
        this.firePropertyChange("unitIncrement", this.unitIncrement, this.unitIncrement = unitIncrement);
    }
    
    public void setValue(final int value) {
        final BoundedRangeModel model = this.getModel();
        final int value2 = model.getValue();
        model.setValue(value);
        if (super.accessibleContext != null) {
            super.accessibleContext.firePropertyChange("AccessibleValue", new Integer(value2), new Integer(model.getValue()));
        }
    }
    
    public void setValueIsAdjusting(final boolean valueIsAdjusting) {
        final BoundedRangeModel model = this.getModel();
        final boolean valueIsAdjusting2 = model.getValueIsAdjusting();
        model.setValueIsAdjusting(valueIsAdjusting);
        if (valueIsAdjusting2 != valueIsAdjusting && super.accessibleContext != null) {
            super.accessibleContext.firePropertyChange("AccessibleState", valueIsAdjusting2 ? AccessibleState.BUSY : null, valueIsAdjusting ? AccessibleState.BUSY : null);
        }
    }
    
    public void setValues(final int n, final int n2, final int n3, final int n4) {
        final BoundedRangeModel model = this.getModel();
        final int value = model.getValue();
        model.setRangeProperties(n, n2, n3, n4, model.getValueIsAdjusting());
        if (super.accessibleContext != null) {
            super.accessibleContext.firePropertyChange("AccessibleValue", new Integer(value), new Integer(model.getValue()));
        }
    }
    
    public void setVisibleAmount(final int extent) {
        this.getModel().setExtent(extent);
    }
    
    public void updateUI() {
        this.setUI(UIManager.getUI(this));
    }
    
    private void writeObject(final ObjectOutputStream objectOutputStream) throws IOException {
        objectOutputStream.defaultWriteObject();
        if (super.ui != null && this.getUIClassID().equals("ScrollBarUI")) {
            super.ui.installUI(this);
        }
    }
    
    private class ModelListener implements ChangeListener, Serializable
    {
        public void stateChanged(final ChangeEvent changeEvent) {
            JScrollBar.this.fireAdjustmentValueChanged(601, 5, JScrollBar.this.getValue());
        }
    }
    
    protected class AccessibleJScrollBar extends AccessibleJComponent implements AccessibleValue
    {
        public AccessibleRole getAccessibleRole() {
            return AccessibleRole.SCROLL_BAR;
        }
        
        public AccessibleStateSet getAccessibleStateSet() {
            final AccessibleStateSet accessibleStateSet = super.getAccessibleStateSet();
            if (JScrollBar.this.getValueIsAdjusting()) {
                accessibleStateSet.add(AccessibleState.BUSY);
            }
            if (JScrollBar.this.getOrientation() == 1) {
                accessibleStateSet.add(AccessibleState.VERTICAL);
            }
            else {
                accessibleStateSet.add(AccessibleState.HORIZONTAL);
            }
            return accessibleStateSet;
        }
        
        public AccessibleValue getAccessibleValue() {
            return this;
        }
        
        public Number getCurrentAccessibleValue() {
            return new Integer(JScrollBar.this.getValue());
        }
        
        public Number getMaximumAccessibleValue() {
            return new Integer(JScrollBar.this.getMaximum());
        }
        
        public Number getMinimumAccessibleValue() {
            return new Integer(JScrollBar.this.getMinimum());
        }
        
        public boolean setCurrentAccessibleValue(final Number n) {
            if (n instanceof Integer) {
                JScrollBar.this.setValue(n.intValue());
                return true;
            }
            return false;
        }
    }
}
