// 
// Decompiled by Procyon v0.5.30
// 

package symantec.itools.util;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.beans.VetoableChangeListener;
import java.beans.PropertyChangeListener;
import java.awt.event.ActionEvent;
import java.awt.AWTEventMulticaster;
import java.beans.PropertyVetoException;
import symantec.itools.beans.PropertyChangeSupport;
import symantec.itools.beans.VetoableChangeSupport;
import java.awt.event.ActionListener;
import java.awt.Component;
import java.io.Serializable;

public class Timer implements Runnable, Serializable
{
    protected Component target;
    protected int eventType;
    protected boolean repeat;
    protected boolean repeating;
    protected boolean execute;
    protected boolean live;
    protected int delay;
    protected String actionCommand;
    protected ActionListener actionListener;
    protected transient Thread thread;
    private VetoableChangeSupport vetos;
    private PropertyChangeSupport changes;
    
    public Timer() {
        this(1000, false);
    }
    
    public Timer(final int d) {
        this(d, false);
    }
    
    public Timer(final boolean r) {
        this(1000, r);
    }
    
    public Timer(final int d, final boolean r) {
        this.vetos = new VetoableChangeSupport(this);
        this.changes = new PropertyChangeSupport(this);
        this.delay = d;
        this.repeat = r;
        this.execute = false;
        this.thread = new Thread(this);
    }
    
    public Timer(final Component t) {
        this(1000);
    }
    
    public Timer(final Component t, final int d) {
        this(d, false);
    }
    
    public Timer(final Component t, final int d, final boolean r) {
        this(d, r);
    }
    
    public Timer(final Component t, final int d, final boolean r, final int e) {
        this(d, r);
    }
    
    public void setDelay(final int d) throws PropertyVetoException {
        final Integer newValue = new Integer(d);
        final Integer oldValue = new Integer(this.delay);
        this.vetos.fireVetoableChange("delay", oldValue, newValue);
        this.delay = d;
        this.changes.firePropertyChange("delay", oldValue, newValue);
    }
    
    public int getDelay() {
        return this.delay;
    }
    
    public void setRepeat(final boolean f) throws PropertyVetoException {
        final Boolean newValue = new Boolean(f);
        final Boolean oldValue = new Boolean(this.repeat);
        this.vetos.fireVetoableChange("repeat", oldValue, newValue);
        this.repeat = f;
        this.changes.firePropertyChange("repeat", oldValue, newValue);
    }
    
    public boolean isRepeat() {
        return this.repeat;
    }
    
    public boolean getRepeat() {
        return this.isRepeat();
    }
    
    public void pause() {
        this.execute = false;
    }
    
    public void resume() {
        if (!this.execute) {
            this.execute = true;
            this.thread.resume();
        }
    }
    
    public void start() {
        this.execute = true;
        this.live = true;
        if (this.thread.isAlive()) {
            this.thread.resume();
        }
        else {
            (this.thread = new Thread(this)).start();
        }
    }
    
    public void start(final int d) throws PropertyVetoException {
        this.setDelay(d);
        this.start();
    }
    
    public void start(final boolean r) throws PropertyVetoException {
        this.setRepeat(r);
        this.start();
    }
    
    public void start(final int d, final boolean r) throws PropertyVetoException {
        this.setDelay(d);
        this.setRepeat(r);
        this.start();
    }
    
    public void stop() {
        this.execute = false;
        this.repeating = false;
        this.live = false;
        this.thread.resume();
    }
    
    public void run() {
        if (!this.execute) {
            this.thread.suspend();
        }
        try {
            while (this.live) {
                do {
                    this.repeating = this.repeat;
                    Thread.sleep(this.delay);
                    if (this.execute) {
                        this.sourceActionEvent();
                    }
                } while (this.repeating && this.live);
                if ((!this.execute && this.live) || !this.repeating) {
                    this.thread.suspend();
                }
            }
        }
        catch (InterruptedException ex) {}
    }
    
    public void setActionCommand(final String command) throws PropertyVetoException {
        final String oldValue = this.actionCommand;
        this.vetos.fireVetoableChange("actionCommand", oldValue, command);
        this.actionCommand = command;
        this.changes.firePropertyChange("actionCommand", oldValue, command);
    }
    
    public String getActionCommand() {
        return this.actionCommand;
    }
    
    public void addActionListener(final ActionListener l) {
        this.actionListener = AWTEventMulticaster.add(this.actionListener, l);
    }
    
    public void removeActionListener(final ActionListener l) {
        this.actionListener = AWTEventMulticaster.remove(this.actionListener, l);
    }
    
    public void sourceActionEvent() {
        if (this.actionListener != null) {
            this.actionListener.actionPerformed(new ActionEvent(this, 1001, this.actionCommand));
        }
    }
    
    public void addPropertyChangeListener(final PropertyChangeListener listener) {
        this.changes.addPropertyChangeListener(listener);
    }
    
    public void removePropertyChangeListener(final PropertyChangeListener listener) {
        this.changes.removePropertyChangeListener(listener);
    }
    
    public void addVetoableChangeListener(final VetoableChangeListener listener) {
        this.vetos.addVetoableChangeListener(listener);
    }
    
    public void removeVetoableChangeListener(final VetoableChangeListener listener) {
        this.vetos.removeVetoableChangeListener(listener);
    }
    
    private void readObject(final ObjectInputStream in) throws IOException, ClassNotFoundException {
        in.defaultReadObject();
        this.execute = false;
        this.thread = new Thread(this);
    }
}
