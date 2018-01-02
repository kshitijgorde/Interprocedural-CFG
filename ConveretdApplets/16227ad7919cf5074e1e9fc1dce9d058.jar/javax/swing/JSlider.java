// 
// Decompiled by Procyon v0.5.30
// 

package javax.swing;

import javax.accessibility.AccessibleStateSet;
import javax.accessibility.AccessibleRole;
import javax.accessibility.AccessibleValue;
import java.io.Serializable;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.Enumeration;
import javax.swing.plaf.ComponentUI;
import javax.accessibility.AccessibleState;
import javax.swing.plaf.SliderUI;
import javax.accessibility.AccessibleContext;
import java.beans.PropertyChangeListener;
import java.util.Hashtable;
import java.util.EventListener;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.util.Dictionary;
import javax.accessibility.Accessible;

public class JSlider extends JComponent implements SwingConstants, Accessible
{
    private static final String uiClassID = "SliderUI";
    private boolean paintTicks;
    private boolean paintTrack;
    private boolean paintLabels;
    private boolean isInverted;
    protected BoundedRangeModel sliderModel;
    protected int majorTickSpacing;
    protected int minorTickSpacing;
    protected boolean snapToTicks;
    boolean snapToValue;
    protected int orientation;
    private Dictionary labelTable;
    protected ChangeListener changeListener;
    protected transient ChangeEvent changeEvent;
    static /* synthetic */ Class class$javax$swing$event$ChangeListener;
    
    public JSlider() {
        this(0, 0, 100, 50);
    }
    
    public JSlider(final int n) {
        this(n, 0, 100, 50);
    }
    
    public JSlider(final int n, final int n2) {
        this(0, n, n2, 50);
    }
    
    public JSlider(final int n, final int n2, final int n3) {
        this(0, n, n2, n3);
    }
    
    public JSlider(final int orientation, final int n, final int n2, final int n3) {
        this.paintTicks = false;
        this.paintTrack = true;
        this.paintLabels = false;
        this.isInverted = false;
        this.snapToTicks = false;
        this.snapToValue = true;
        this.changeListener = this.createChangeListener();
        this.changeEvent = null;
        this.checkOrientation(orientation);
        this.orientation = orientation;
        (this.sliderModel = new DefaultBoundedRangeModel(n3, 0, n, n2)).addChangeListener(this.changeListener);
        this.updateUI();
    }
    
    public JSlider(final BoundedRangeModel model) {
        this.paintTicks = false;
        this.paintTrack = true;
        this.paintLabels = false;
        this.isInverted = false;
        this.snapToTicks = false;
        this.snapToValue = true;
        this.changeListener = this.createChangeListener();
        this.changeEvent = null;
        this.orientation = 0;
        this.setModel(model);
        this.sliderModel.addChangeListener(this.changeListener);
        this.updateUI();
    }
    
    public void addChangeListener(final ChangeListener changeListener) {
        super.listenerList.add((JSlider.class$javax$swing$event$ChangeListener != null) ? JSlider.class$javax$swing$event$ChangeListener : (JSlider.class$javax$swing$event$ChangeListener = class$("javax.swing.event.ChangeListener")), changeListener);
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
    
    protected ChangeListener createChangeListener() {
        return new ModelListener();
    }
    
    public Hashtable createStandardLabels(final int n) {
        return this.createStandardLabels(n, this.getMinimum());
    }
    
    public Hashtable createStandardLabels(final int n, final int n2) {
        if (n2 > this.getMaximum() || n2 < this.getMinimum()) {
            throw new IllegalArgumentException("Slider label start point out of range.");
        }
        if (n <= 0) {
            throw new IllegalArgumentException("Label incremement must be > 0");
        }
        final JSlider$1.SmartHashtable smartHashtable = new JSlider$1.SmartHashtable(n, n2);
        if (this.getLabelTable() != null && this.getLabelTable() instanceof PropertyChangeListener) {
            this.removePropertyChangeListener((PropertyChangeListener)this.getLabelTable());
        }
        this.addPropertyChangeListener(smartHashtable);
        return smartHashtable;
    }
    
    protected void fireStateChanged() {
        final Object[] listenerList = super.listenerList.getListenerList();
        for (int i = listenerList.length - 2; i >= 0; i -= 2) {
            if (listenerList[i] == ((JSlider.class$javax$swing$event$ChangeListener != null) ? JSlider.class$javax$swing$event$ChangeListener : (JSlider.class$javax$swing$event$ChangeListener = class$("javax.swing.event.ChangeListener")))) {
                if (this.changeEvent == null) {
                    this.changeEvent = new ChangeEvent(this);
                }
                ((ChangeListener)listenerList[i + 1]).stateChanged(this.changeEvent);
            }
        }
    }
    
    public AccessibleContext getAccessibleContext() {
        if (super.accessibleContext == null) {
            super.accessibleContext = new AccessibleJSlider();
        }
        return super.accessibleContext;
    }
    
    public int getExtent() {
        return this.getModel().getExtent();
    }
    
    public boolean getInverted() {
        return this.isInverted;
    }
    
    public Dictionary getLabelTable() {
        return this.labelTable;
    }
    
    public int getMajorTickSpacing() {
        return this.majorTickSpacing;
    }
    
    public int getMaximum() {
        return this.getModel().getMaximum();
    }
    
    public int getMinimum() {
        return this.getModel().getMinimum();
    }
    
    public int getMinorTickSpacing() {
        return this.minorTickSpacing;
    }
    
    public BoundedRangeModel getModel() {
        return this.sliderModel;
    }
    
    public int getOrientation() {
        return this.orientation;
    }
    
    public boolean getPaintLabels() {
        return this.paintLabels;
    }
    
    public boolean getPaintTicks() {
        return this.paintTicks;
    }
    
    public boolean getPaintTrack() {
        return this.paintTrack;
    }
    
    public boolean getSnapToTicks() {
        return this.snapToTicks;
    }
    
    boolean getSnapToValue() {
        return this.snapToValue;
    }
    
    public SliderUI getUI() {
        return (SliderUI)super.ui;
    }
    
    public String getUIClassID() {
        return "SliderUI";
    }
    
    public int getValue() {
        return this.getModel().getValue();
    }
    
    public boolean getValueIsAdjusting() {
        return this.getModel().getValueIsAdjusting();
    }
    
    protected String paramString() {
        return String.valueOf(super.paramString()) + ",isInverted=" + (this.isInverted ? "true" : "false") + ",majorTickSpacing=" + this.majorTickSpacing + ",minorTickSpacing=" + this.minorTickSpacing + ",orientation=" + ((this.orientation == 0) ? "HORIZONTAL" : "VERTICAL") + ",paintLabels=" + (this.paintLabels ? "true" : "false") + ",paintTicks=" + (this.paintTicks ? "true" : "false") + ",paintTrack=" + (this.paintTrack ? "true" : "false") + ",snapToTicks=" + (this.snapToTicks ? "true" : "false") + ",snapToValue=" + (this.snapToValue ? "true" : "false");
    }
    
    public void removeChangeListener(final ChangeListener changeListener) {
        super.listenerList.remove((JSlider.class$javax$swing$event$ChangeListener != null) ? JSlider.class$javax$swing$event$ChangeListener : (JSlider.class$javax$swing$event$ChangeListener = class$("javax.swing.event.ChangeListener")), changeListener);
    }
    
    public void setExtent(final int extent) {
        this.getModel().setExtent(extent);
    }
    
    public void setInverted(final boolean isInverted) {
        final boolean isInverted2 = this.isInverted;
        this.firePropertyChange("inverted", isInverted2, this.isInverted = isInverted);
        if (isInverted != isInverted2) {
            this.repaint();
        }
    }
    
    public void setLabelTable(final Dictionary labelTable) {
        final Dictionary labelTable2 = this.labelTable;
        this.labelTable = labelTable;
        this.updateLabelUIs();
        this.firePropertyChange("labelTable", labelTable2, this.labelTable);
        if (labelTable != labelTable2) {
            this.revalidate();
            this.repaint();
        }
    }
    
    public void setMajorTickSpacing(final int majorTickSpacing) {
        final int majorTickSpacing2 = this.majorTickSpacing;
        this.majorTickSpacing = majorTickSpacing;
        if (this.labelTable == null && this.getMajorTickSpacing() > 0 && this.getPaintLabels()) {
            this.setLabelTable(this.createStandardLabels(this.getMajorTickSpacing()));
        }
        this.firePropertyChange("majorTickSpacing", majorTickSpacing2, this.majorTickSpacing);
        if (this.majorTickSpacing != majorTickSpacing2 && this.getPaintTicks()) {
            this.repaint();
        }
    }
    
    public void setMaximum(final int maximum) {
        final int maximum2 = this.getModel().getMaximum();
        this.getModel().setMaximum(maximum);
        this.firePropertyChange("maximum", new Integer(maximum2), new Integer(maximum));
    }
    
    public void setMinimum(final int minimum) {
        final int minimum2 = this.getModel().getMinimum();
        this.getModel().setMinimum(minimum);
        this.firePropertyChange("minimum", new Integer(minimum2), new Integer(minimum));
    }
    
    public void setMinorTickSpacing(final int minorTickSpacing) {
        final int minorTickSpacing2 = this.minorTickSpacing;
        this.firePropertyChange("minorTickSpacing", minorTickSpacing2, this.minorTickSpacing = minorTickSpacing);
        if (this.minorTickSpacing != minorTickSpacing2 && this.getPaintTicks()) {
            this.repaint();
        }
    }
    
    public void setModel(final BoundedRangeModel sliderModel) {
        final BoundedRangeModel model = this.getModel();
        if (model != null) {
            model.removeChangeListener(this.changeListener);
        }
        if ((this.sliderModel = sliderModel) != null) {
            sliderModel.addChangeListener(this.changeListener);
            if (super.accessibleContext != null) {
                super.accessibleContext.firePropertyChange("AccessibleValue", (model == null) ? null : new Integer(model.getValue()), (sliderModel == null) ? null : new Integer(sliderModel.getValue()));
            }
        }
        this.firePropertyChange("model", model, this.sliderModel);
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
    
    public void setPaintLabels(final boolean paintLabels) {
        final boolean paintLabels2 = this.paintLabels;
        this.paintLabels = paintLabels;
        if (this.labelTable == null && this.getMajorTickSpacing() > 0) {
            this.setLabelTable(this.createStandardLabels(this.getMajorTickSpacing()));
        }
        this.firePropertyChange("paintLabels", paintLabels2, this.paintLabels);
        if (this.paintLabels != paintLabels2) {
            this.revalidate();
            this.repaint();
        }
    }
    
    public void setPaintTicks(final boolean paintTicks) {
        final boolean paintTicks2 = this.paintTicks;
        this.firePropertyChange("paintTicks", paintTicks2, this.paintTicks = paintTicks);
        if (this.paintTicks != paintTicks2) {
            this.revalidate();
            this.repaint();
        }
    }
    
    public void setPaintTrack(final boolean paintTrack) {
        final boolean paintTrack2 = this.paintTrack;
        this.firePropertyChange("paintTrack", paintTrack2, this.paintTrack = paintTrack);
        if (this.paintTrack != paintTrack2) {
            this.repaint();
        }
    }
    
    public void setSnapToTicks(final boolean snapToTicks) {
        this.firePropertyChange("snapToTicks", this.snapToTicks, this.snapToTicks = snapToTicks);
    }
    
    void setSnapToValue(final boolean snapToValue) {
        this.firePropertyChange("snapToValue", this.snapToValue, this.snapToValue = snapToValue);
    }
    
    public void setUI(final SliderUI ui) {
        super.setUI(ui);
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
    
    protected void updateLabelUIs() {
        if (this.getLabelTable() == null) {
            return;
        }
        final Enumeration<Object> keys = this.getLabelTable().keys();
        while (keys.hasMoreElements()) {
            final Object value = this.getLabelTable().get(keys.nextElement());
            if (value instanceof JComponent) {
                final JComponent component = (JComponent)value;
                component.updateUI();
                component.setSize(component.getPreferredSize());
            }
        }
    }
    
    public void updateUI() {
        this.updateLabelUIs();
        this.setUI((SliderUI)UIManager.getUI(this));
    }
    
    private void writeObject(final ObjectOutputStream objectOutputStream) throws IOException {
        objectOutputStream.defaultWriteObject();
        if (super.ui != null && this.getUIClassID().equals("SliderUI")) {
            super.ui.installUI(this);
        }
    }
    
    private class ModelListener implements ChangeListener, Serializable
    {
        public void stateChanged(final ChangeEvent changeEvent) {
            JSlider.this.fireStateChanged();
        }
    }
    
    protected class AccessibleJSlider extends AccessibleJComponent implements AccessibleValue
    {
        public AccessibleRole getAccessibleRole() {
            return AccessibleRole.SLIDER;
        }
        
        public AccessibleStateSet getAccessibleStateSet() {
            final AccessibleStateSet accessibleStateSet = super.getAccessibleStateSet();
            if (JSlider.this.getValueIsAdjusting()) {
                accessibleStateSet.add(AccessibleState.BUSY);
            }
            if (JSlider.this.getOrientation() == 1) {
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
            return new Integer(JSlider.this.getValue());
        }
        
        public Number getMaximumAccessibleValue() {
            return new Integer(JSlider.this.getMaximum());
        }
        
        public Number getMinimumAccessibleValue() {
            return new Integer(JSlider.this.getMinimum());
        }
        
        public boolean setCurrentAccessibleValue(final Number n) {
            if (n instanceof Integer) {
                JSlider.this.setValue(n.intValue());
                return true;
            }
            return false;
        }
    }
}
