// 
// Decompiled by Procyon v0.5.30
// 

package symantec.itools.awt.util.spinner;

import java.beans.PropertyChangeEvent;
import java.awt.event.ActionEvent;
import java.beans.PropertyChangeListener;
import java.awt.AWTEventMulticaster;
import java.beans.VetoableChangeListener;
import symantec.itools.resources.ErrorsBundle;
import java.awt.Dimension;
import java.beans.PropertyVetoException;
import java.awt.GridLayout;
import java.awt.Component;
import java.awt.Insets;
import java.awt.GridBagConstraints;
import java.awt.LayoutManager;
import java.awt.GridBagLayout;
import java.awt.TextField;
import java.awt.event.ActionListener;
import java.util.ResourceBundle;
import symantec.itools.beans.PropertyChangeSupport;
import symantec.itools.beans.VetoableChangeSupport;
import java.io.Serializable;
import symantec.itools.awt.Orientation;
import java.awt.Panel;

public abstract class Spinner extends Panel implements Orientation, Serializable
{
    protected static int ORIENTATION_DEFAULT;
    protected String text;
    protected int textWidth;
    protected int orientation;
    protected boolean wrappable;
    protected boolean editable;
    protected int min;
    protected int max;
    protected int current;
    protected int increment;
    protected VetoableChangeSupport vetos;
    protected PropertyChangeSupport changes;
    protected ResourceBundle errors;
    protected ActionListener actionListener;
    protected CurrentVeto currentVeto;
    protected MaxVeto maxVeto;
    protected MinVeto minVeto;
    protected Action action;
    protected boolean added;
    TextField textFld;
    SpinButtonPanel buttons;
    
    public Spinner() {
        this.min = 0;
        this.max = 0;
        this.increment = 1;
        this.current = 0;
        this.textWidth = 0;
        this.vetos = new VetoableChangeSupport(this);
        this.changes = new PropertyChangeSupport(this);
        this.added = false;
        final GridBagLayout gridBagLayout = new GridBagLayout();
        super.setLayout(gridBagLayout);
        this.setSize(61, 20);
        (this.textFld = new TextField()).setBounds(0, 0, 100, 20);
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.fill = 2;
        gbc.insets = new Insets(0, 0, 0, 0);
        ((GridBagLayout)this.getLayout()).setConstraints(this.textFld, gbc);
        this.add(this.textFld);
        (this.buttons = new SpinButtonPanel()).setLayout(new GridLayout(2, 1, 0, 0));
        this.buttons.setBounds(100, 0, 3, 20);
        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.weightx = 0.05;
        gbc.weighty = 1.0;
        gbc.fill = 1;
        gbc.insets = new Insets(0, 0, 0, 0);
        ((GridBagLayout)this.getLayout()).setConstraints(this.buttons, gbc);
        this.add(this.buttons);
        try {
            this.setWrappable(false);
            this.setOrientation(Spinner.ORIENTATION_DEFAULT);
        }
        catch (PropertyVetoException ex) {}
    }
    
    public void setEditable(final boolean f) throws PropertyVetoException {
        if (this.editable != f) {
            final Boolean oldValue = new Boolean(this.editable);
            final Boolean newValue = new Boolean(f);
            this.vetos.fireVetoableChange("editable", oldValue, newValue);
            this.editable = f;
            this.textFld.setEditable(this.editable);
            this.changes.firePropertyChange("editable", oldValue, newValue);
        }
    }
    
    public boolean getEditable() {
        return this.editable;
    }
    
    public boolean isEditable() {
        return this.editable;
    }
    
    public void setOrientation(final int o) throws PropertyVetoException {
        if (this.orientation != o) {
            final Integer oldValue = new Integer(this.orientation);
            final Integer newValue = new Integer(o);
            this.vetos.fireVetoableChange("orientation", oldValue, newValue);
            this.orientation = o;
            this.buttons.setOrientation(this.orientation);
            this.changes.firePropertyChange("orientation", oldValue, newValue);
        }
    }
    
    public int getOrientation() {
        return this.orientation;
    }
    
    public void setWrappable(final boolean f) throws PropertyVetoException {
        if (this.wrappable != f) {
            final Boolean oldValue = new Boolean(this.wrappable);
            final Boolean newValue = new Boolean(f);
            this.vetos.fireVetoableChange("wrappable", oldValue, newValue);
            this.wrappable = f;
            this.updateButtonStatus();
            this.changes.firePropertyChange("wrappable", oldValue, newValue);
        }
    }
    
    public boolean getWrappable() {
        return this.wrappable;
    }
    
    public boolean isWrappable() {
        return this.wrappable;
    }
    
    public Dimension getPreferredSize() {
        final Dimension textFldDim = this.textFld.getPreferredSize();
        final Dimension btnsDim = this.buttons.getPreferredSize();
        return new Dimension(textFldDim.width + btnsDim.width, Math.max(textFldDim.height, btnsDim.height));
    }
    
    public Dimension getMinimumSize() {
        return this.getPreferredSize();
    }
    
    public void setMin(final int i) throws PropertyVetoException {
        if (this.min != i) {
            final Integer oldValue = new Integer(this.min);
            final Integer newValue = new Integer(i);
            this.vetos.fireVetoableChange("min", oldValue, newValue);
            this.min = i;
            if (this.getCurrent() < this.min) {
                this.setCurrent(this.min);
            }
            else {
                this.updateButtonStatus();
            }
            this.changes.firePropertyChange("min", oldValue, newValue);
        }
    }
    
    public int getMin() {
        return this.min;
    }
    
    public void setMax(final int i) throws PropertyVetoException {
        if (this.max != i) {
            final Integer oldValue = new Integer(this.max);
            final Integer newValue = new Integer(i);
            this.vetos.fireVetoableChange("max", oldValue, newValue);
            this.max = i;
            if (this.getCurrent() > this.max) {
                this.setCurrent(this.max);
            }
            else {
                this.updateButtonStatus();
            }
            this.changes.firePropertyChange("max", oldValue, newValue);
        }
    }
    
    public int getMax() {
        return this.max;
    }
    
    public void setCurrent(final int i) throws PropertyVetoException {
        if (this.current != i) {
            final Integer oldValue = new Integer(this.current);
            final Integer newValue = new Integer(i);
            this.vetos.fireVetoableChange("current", oldValue, newValue);
            this.current = i;
            this.updateText(false);
            this.updateButtonStatus();
            this.changes.firePropertyChange("current", oldValue, newValue);
        }
    }
    
    public int getCurrent() {
        return this.current;
    }
    
    public void setNotifyWhilePressed(final boolean f) throws PropertyVetoException {
        if (f != this.buttons.getNotifyWhilePressed()) {
            final Boolean oldValue = new Boolean(this.getNotifyWhilePressed());
            final Boolean newValue = new Boolean(f);
            this.vetos.fireVetoableChange("notifyWhilePressed", oldValue, newValue);
            this.buttons.setNotifyWhilePressed(f);
            this.changes.firePropertyChange("notifyWhilePressed", oldValue, newValue);
        }
    }
    
    public boolean isNotifyWhilePressed() {
        return this.buttons.isNotifyWhilePressed();
    }
    
    public boolean getNotifyWhilePressed() {
        return this.isNotifyWhilePressed();
    }
    
    public void setDelay(final int d) throws PropertyVetoException {
        if (d != this.buttons.getDelay()) {
            final Integer oldValue = new Integer(this.buttons.getDelay());
            final Integer newValue = new Integer(d);
            this.vetos.fireVetoableChange("delay", oldValue, newValue);
            this.buttons.setDelay(d);
            this.changes.firePropertyChange("delay", oldValue, newValue);
        }
    }
    
    public int getDelay() {
        return this.buttons.getDelay();
    }
    
    public String getEntryFieldText() {
        return this.textFld.getText();
    }
    
    public void setLayout(final LayoutManager lm) {
    }
    
    public synchronized void addNotify() {
        super.addNotify();
        this.added = true;
        try {
            this.errors = ResourceBundle.getBundle("symantec.itools.resources.ErrorsBundle");
        }
        catch (Throwable t) {
            this.errors = new ErrorsBundle();
        }
        if (this.action == null) {
            this.action = new Action();
            this.buttons.addActionListener(this.action);
            this.textFld.addActionListener(this.action);
        }
        if (this.currentVeto == null) {
            this.addCurrentListener(this.currentVeto = new CurrentVeto());
        }
        if (this.maxVeto == null) {
            this.addMaxListener(this.maxVeto = new MaxVeto());
        }
        if (this.minVeto == null) {
            this.addMinListener(this.minVeto = new MinVeto());
        }
        this.updateText(true);
    }
    
    public synchronized void removeNotify() {
        if (this.action != null) {
            this.textFld.removeActionListener(this.action);
            this.buttons.removeActionListener(this.action);
            this.action = null;
        }
        if (this.currentVeto != null) {
            this.removeCurrentListener(this.currentVeto);
            this.currentVeto = null;
        }
        if (this.maxVeto != null) {
            this.removeMaxListener(this.maxVeto);
            this.maxVeto = null;
        }
        if (this.minVeto != null) {
            this.removeMinListener(this.minVeto);
            this.minVeto = null;
        }
        super.removeNotify();
    }
    
    public synchronized void addActionListener(final ActionListener l) {
        this.actionListener = AWTEventMulticaster.add(this.actionListener, l);
    }
    
    public synchronized void removeActionListener(final ActionListener l) {
        this.actionListener = AWTEventMulticaster.remove(this.actionListener, l);
    }
    
    public synchronized void addPropertyChangeListener(final PropertyChangeListener listener) {
        this.changes.addPropertyChangeListener(listener);
    }
    
    public synchronized void removePropertyChangeListener(final PropertyChangeListener listener) {
        this.changes.removePropertyChangeListener(listener);
    }
    
    public synchronized void addVetoableChangeListener(final VetoableChangeListener listener) {
        this.vetos.addVetoableChangeListener(listener);
    }
    
    public synchronized void removeVetoableChangeListener(final VetoableChangeListener listener) {
        this.vetos.removeVetoableChangeListener(listener);
    }
    
    public synchronized void addCurrentListener(final PropertyChangeListener listener) {
        this.changes.addPropertyChangeListener("current", listener);
    }
    
    public synchronized void removeCurrentListener(final PropertyChangeListener listener) {
        this.changes.removePropertyChangeListener("current", listener);
    }
    
    public synchronized void addCurrentListener(final VetoableChangeListener listener) {
        this.vetos.addVetoableChangeListener("current", listener);
    }
    
    public synchronized void removeCurrentListener(final VetoableChangeListener listener) {
        this.vetos.removeVetoableChangeListener("current", listener);
    }
    
    public synchronized void addMaxListener(final PropertyChangeListener listener) {
        this.changes.addPropertyChangeListener("max", listener);
    }
    
    public synchronized void removeMaxListener(final PropertyChangeListener listener) {
        this.changes.removePropertyChangeListener("max", listener);
    }
    
    public synchronized void addMaxListener(final VetoableChangeListener listener) {
        this.vetos.addVetoableChangeListener("max", listener);
    }
    
    public synchronized void removeMaxListener(final VetoableChangeListener listener) {
        this.vetos.removeVetoableChangeListener("max", listener);
    }
    
    public synchronized void addMinListener(final PropertyChangeListener listener) {
        this.changes.addPropertyChangeListener("min", listener);
    }
    
    public synchronized void removeMinListener(final PropertyChangeListener listener) {
        this.changes.removePropertyChangeListener("min", listener);
    }
    
    public synchronized void addMinListener(final VetoableChangeListener listener) {
        this.vetos.addVetoableChangeListener("min", listener);
    }
    
    public synchronized void removeMinListener(final VetoableChangeListener listener) {
        this.vetos.removeVetoableChangeListener("min", listener);
    }
    
    protected boolean isValidCurrentValue(final int i) {
        return i <= this.max && i >= this.min;
    }
    
    protected boolean isValidMaxValue(final int i) {
        return i >= this.min;
    }
    
    protected boolean isValidMinValue(final int i) {
        return i <= this.max;
    }
    
    protected void sourceActionEvent(final String s) {
        if (this.actionListener != null) {
            this.actionListener.actionPerformed(new ActionEvent(this, 1001, s));
        }
    }
    
    protected void scrollUp() {
        try {
            this.setCurrent(this.current + this.increment);
        }
        catch (PropertyVetoException ex) {
            if (this.wrappable) {
                try {
                    this.setCurrent(this.min);
                }
                catch (PropertyVetoException ex2) {}
            }
            else {
                try {
                    this.setCurrent(this.max);
                }
                catch (PropertyVetoException ex3) {}
            }
        }
        this.updateText(false);
    }
    
    protected void scrollDown() {
        try {
            this.setCurrent(this.current - this.increment);
        }
        catch (PropertyVetoException ex) {
            if (this.wrappable) {
                try {
                    this.setCurrent(this.max);
                }
                catch (PropertyVetoException ex2) {}
            }
            else {
                try {
                    this.setCurrent(this.min);
                }
                catch (PropertyVetoException ex3) {}
            }
        }
        this.updateText(false);
    }
    
    protected void updateText(final boolean force) {
        final String currentText = this.getCurrentText();
        if (force || !this.textFld.getText().equals(currentText)) {
            this.textFld.setText(currentText);
        }
    }
    
    protected void updateButtonStatus() {
        if (this.buttons != null) {
            if (this.wrappable) {
                this.buttons.setUpButtonEnabled(true);
                this.buttons.setDownButtonEnabled(true);
            }
            else if (this.current == this.max && this.current == this.min) {
                this.buttons.setUpButtonEnabled(false);
                this.buttons.setDownButtonEnabled(false);
            }
            else if (this.current == this.max) {
                this.buttons.setUpButtonEnabled(false);
                this.buttons.setDownButtonEnabled(true);
            }
            else if (this.current == this.min) {
                this.buttons.setUpButtonEnabled(true);
                this.buttons.setDownButtonEnabled(false);
            }
            else {
                this.buttons.setUpButtonEnabled(true);
                this.buttons.setDownButtonEnabled(true);
            }
        }
    }
    
    protected abstract String getCurrentText();
    
    class Action implements ActionListener, Serializable
    {
        public void actionPerformed(final ActionEvent e) {
            if (e.getSource() instanceof TextField && e.getSource() == Spinner.this.textFld) {
                Spinner.this.updateText(false);
                Spinner.this.requestFocus();
                return;
            }
            String cmdStr = "";
            final String actionCommand = e.getActionCommand();
            if (actionCommand.equals("Increment")) {
                Spinner.this.scrollUp();
                cmdStr = "ScrollUp";
                Spinner.this.sourceActionEvent(cmdStr);
            }
            else if (actionCommand.equals("Decrement")) {
                Spinner.this.scrollDown();
                cmdStr = "ScrollDown";
                Spinner.this.sourceActionEvent(cmdStr);
            }
        }
    }
    
    class CurrentVeto implements VetoableChangeListener, Serializable
    {
        public void vetoableChange(final PropertyChangeEvent e) throws PropertyVetoException {
            final int i = (int)e.getNewValue();
            if (!Spinner.this.isValidCurrentValue(i)) {
                throw new PropertyVetoException(String.valueOf(Spinner.this.errors.getString("InvalidCurrentValue")) + i, e);
            }
        }
    }
    
    class MaxVeto implements VetoableChangeListener, Serializable
    {
        public void vetoableChange(final PropertyChangeEvent e) throws PropertyVetoException {
            final int i = (int)e.getNewValue();
            if (!Spinner.this.isValidMaxValue(i)) {
                throw new PropertyVetoException(String.valueOf(Spinner.this.errors.getString("InvalidMaxValue")) + i, e);
            }
        }
    }
    
    class MinVeto implements VetoableChangeListener, Serializable
    {
        public void vetoableChange(final PropertyChangeEvent e) throws PropertyVetoException {
            final int i = (int)e.getNewValue();
            if (!Spinner.this.isValidMinValue(i)) {
                throw new PropertyVetoException(String.valueOf(Spinner.this.errors.getString("InvalidMinValue")) + i, e);
            }
        }
    }
}
