// 
// Decompiled by Procyon v0.5.30
// 

package symantec.itools.awt.util.spinner;

import java.io.Serializable;
import java.awt.event.ActionEvent;
import java.awt.Dimension;
import java.awt.AWTEventMulticaster;
import java.beans.VetoableChangeListener;
import java.beans.PropertyChangeListener;
import java.awt.Component;
import java.beans.PropertyVetoException;
import java.awt.LayoutManager;
import java.awt.GridLayout;
import symantec.itools.beans.PropertyChangeSupport;
import symantec.itools.beans.VetoableChangeSupport;
import java.awt.event.ActionListener;
import symantec.itools.awt.DirectionButton;
import symantec.itools.awt.Orientation;
import java.awt.Panel;

public class SpinButtonPanel extends Panel implements Orientation
{
    protected int orientation;
    protected boolean notifyWhilePressed;
    protected int delay;
    DirectionButton incBtn;
    DirectionButton decBtn;
    protected ActionListener actionListener;
    private Action action;
    private VetoableChangeSupport vetos;
    private PropertyChangeSupport changes;
    
    public SpinButtonPanel() {
        this.vetos = new VetoableChangeSupport(this);
        this.changes = new PropertyChangeSupport(this);
        super.setLayout(new GridLayout(2, 1, 0, 0));
        this.setSize(104, 51);
        this.incBtn = new DirectionButton();
        try {
            this.incBtn.setDirection(2);
        }
        catch (PropertyVetoException ex) {}
        this.incBtn.setBounds(0, 0, 104, 25);
        this.add(this.incBtn);
        this.decBtn = new DirectionButton();
        try {
            this.decBtn.setDirection(3);
        }
        catch (PropertyVetoException ex2) {}
        this.decBtn.setBounds(0, 25, 104, 25);
        this.add(this.decBtn);
    }
    
    public void setOrientation(final int o) {
        if (o != this.orientation) {
            switch (this.orientation = o) {
                case 0: {
                    super.setLayout(new GridLayout(2, 1, 0, 0));
                    break;
                }
                case 1: {
                    super.setLayout(new GridLayout(1, 2, 0, 0));
                    break;
                }
            }
            this.invalidate();
            this.validate();
        }
    }
    
    public int getOrientation() {
        return this.orientation;
    }
    
    public void setNotifyWhilePressed(final boolean f) throws PropertyVetoException {
        this.incBtn.setNotifyWhilePressed(f);
        this.decBtn.setNotifyWhilePressed(f);
    }
    
    public boolean isNotifyWhilePressed() {
        return this.incBtn.isNotifyWhilePressed();
    }
    
    public boolean getNotifyWhilePressed() {
        return this.isNotifyWhilePressed();
    }
    
    public void setDelay(final int d) throws PropertyVetoException {
        this.incBtn.setNotifyDelay(d);
        this.decBtn.setNotifyDelay(d);
    }
    
    public int getDelay() {
        return this.incBtn.getNotifyDelay();
    }
    
    public void setLayout(final LayoutManager l) {
    }
    
    public synchronized void setEnabled(final boolean flag) {
        if (this.isEnabled() != flag) {
            if (flag) {
                super.enable();
                this.incBtn.setEnabled(true);
                this.decBtn.setEnabled(true);
            }
            else {
                super.disable();
                this.incBtn.setEnabled(false);
                this.decBtn.setEnabled(false);
            }
        }
    }
    
    public synchronized void enable() {
        this.setEnabled(true);
    }
    
    public synchronized void disable() {
        this.setEnabled(false);
    }
    
    public synchronized void setUpButtonEnabled(final boolean flag) {
        if (this.isUpButtonEnabled() != flag) {
            if (flag) {
                this.incBtn.setEnabled(true);
            }
            else {
                this.incBtn.setEnabled(false);
            }
        }
    }
    
    public boolean isUpButtonEnabled() {
        return this.incBtn.isEnabled();
    }
    
    public synchronized void setDownButtonEnabled(final boolean flag) {
        if (this.isDownButtonEnabled() != flag) {
            if (flag) {
                this.decBtn.setEnabled(true);
            }
            else {
                this.decBtn.setEnabled(false);
            }
        }
    }
    
    public boolean isDownButtonEnabled() {
        return this.decBtn.isEnabled();
    }
    
    public synchronized void enableUpButton() {
        this.setUpButtonEnabled(true);
    }
    
    public synchronized void enableDownButton() {
        this.setDownButtonEnabled(true);
    }
    
    public synchronized void disableUpButton() {
        this.setUpButtonEnabled(false);
    }
    
    public synchronized void disableDownButton() {
        this.setDownButtonEnabled(false);
    }
    
    public synchronized void addNotify() {
        super.addNotify();
        if (this.action == null) {
            this.action = new Action();
            this.incBtn.addActionListener(this.action);
            this.decBtn.addActionListener(this.action);
        }
    }
    
    public synchronized void removeNotify() {
        if (this.action != null) {
            this.incBtn.removeActionListener(this.action);
            this.decBtn.removeActionListener(this.action);
            this.action = null;
        }
        super.removeNotify();
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
    
    public synchronized void addActionListener(final ActionListener l) {
        this.actionListener = AWTEventMulticaster.add(this.actionListener, l);
    }
    
    public synchronized void removeActionListener(final ActionListener l) {
        this.actionListener = AWTEventMulticaster.remove(this.actionListener, l);
    }
    
    public Dimension getPreferredSize() {
        int h = 0;
        int w = 0;
        switch (this.orientation) {
            case 0: {
                w = Math.max(this.incBtn.getPreferredSize().width, this.decBtn.getPreferredSize().width);
                h = this.incBtn.getPreferredSize().height + this.decBtn.getPreferredSize().height;
                break;
            }
            case 1: {
                w = this.incBtn.getPreferredSize().width + this.decBtn.getPreferredSize().width;
                h = Math.max(this.incBtn.getPreferredSize().height, this.decBtn.getPreferredSize().height);
                break;
            }
        }
        return new Dimension(w, h);
    }
    
    protected void sourceActionEvent(final String actionCommand) {
        if (this.actionListener != null) {
            this.actionListener.actionPerformed(new ActionEvent(this, 1001, actionCommand));
        }
    }
    
    class Action implements ActionListener, Serializable
    {
        public void actionPerformed(final ActionEvent e) {
            final Object source = e.getSource();
            if (source == SpinButtonPanel.this.incBtn) {
                SpinButtonPanel.this.sourceActionEvent("Increment");
            }
            else if (source == SpinButtonPanel.this.decBtn) {
                SpinButtonPanel.this.sourceActionEvent("Decrement");
            }
        }
    }
}
