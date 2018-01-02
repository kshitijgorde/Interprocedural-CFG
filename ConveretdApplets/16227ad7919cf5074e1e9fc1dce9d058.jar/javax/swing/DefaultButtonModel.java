// 
// Decompiled by Procyon v0.5.30
// 

package javax.swing;

import java.awt.ItemSelectable;
import java.awt.event.ItemEvent;
import java.awt.event.ActionEvent;
import java.awt.event.ItemListener;
import javax.swing.event.ChangeListener;
import java.util.EventListener;
import java.awt.event.ActionListener;
import javax.swing.event.EventListenerList;
import javax.swing.event.ChangeEvent;
import java.io.Serializable;

public class DefaultButtonModel implements ButtonModel, Serializable
{
    protected int stateMask;
    protected String actionCommand;
    protected ButtonGroup group;
    protected int mnemonic;
    protected transient ChangeEvent changeEvent;
    protected EventListenerList listenerList;
    public static final int ARMED = 1;
    public static final int SELECTED = 2;
    public static final int PRESSED = 4;
    public static final int ENABLED = 8;
    public static final int ROLLOVER = 16;
    static /* synthetic */ Class class$javax$swing$event$ChangeListener;
    static /* synthetic */ Class class$java$awt$event$ActionListener;
    static /* synthetic */ Class class$java$awt$event$ItemListener;
    
    public DefaultButtonModel() {
        this.stateMask = 0;
        this.actionCommand = null;
        this.group = null;
        this.mnemonic = 0;
        this.changeEvent = null;
        this.listenerList = new EventListenerList();
        this.stateMask = 0;
        this.setEnabled(true);
    }
    
    public void addActionListener(final ActionListener actionListener) {
        this.listenerList.add((DefaultButtonModel.class$java$awt$event$ActionListener != null) ? DefaultButtonModel.class$java$awt$event$ActionListener : (DefaultButtonModel.class$java$awt$event$ActionListener = class$("java.awt.event.ActionListener")), actionListener);
    }
    
    public void addChangeListener(final ChangeListener changeListener) {
        this.listenerList.add((DefaultButtonModel.class$javax$swing$event$ChangeListener != null) ? DefaultButtonModel.class$javax$swing$event$ChangeListener : (DefaultButtonModel.class$javax$swing$event$ChangeListener = class$("javax.swing.event.ChangeListener")), changeListener);
    }
    
    public void addItemListener(final ItemListener itemListener) {
        this.listenerList.add((DefaultButtonModel.class$java$awt$event$ItemListener != null) ? DefaultButtonModel.class$java$awt$event$ItemListener : (DefaultButtonModel.class$java$awt$event$ItemListener = class$("java.awt.event.ItemListener")), itemListener);
    }
    
    static /* synthetic */ Class class$(final String s) {
        try {
            return Class.forName(s);
        }
        catch (ClassNotFoundException ex) {
            throw new NoClassDefFoundError(ex.getMessage());
        }
    }
    
    protected void fireActionPerformed(final ActionEvent actionEvent) {
        final Object[] listenerList = this.listenerList.getListenerList();
        for (int i = listenerList.length - 2; i >= 0; i -= 2) {
            if (listenerList[i] == ((DefaultButtonModel.class$java$awt$event$ActionListener != null) ? DefaultButtonModel.class$java$awt$event$ActionListener : (DefaultButtonModel.class$java$awt$event$ActionListener = class$("java.awt.event.ActionListener")))) {
                ((ActionListener)listenerList[i + 1]).actionPerformed(actionEvent);
            }
        }
    }
    
    protected void fireItemStateChanged(final ItemEvent itemEvent) {
        final Object[] listenerList = this.listenerList.getListenerList();
        for (int i = listenerList.length - 2; i >= 0; i -= 2) {
            if (listenerList[i] == ((DefaultButtonModel.class$java$awt$event$ItemListener != null) ? DefaultButtonModel.class$java$awt$event$ItemListener : (DefaultButtonModel.class$java$awt$event$ItemListener = class$("java.awt.event.ItemListener")))) {
                ((ItemListener)listenerList[i + 1]).itemStateChanged(itemEvent);
            }
        }
    }
    
    protected void fireStateChanged() {
        final Object[] listenerList = this.listenerList.getListenerList();
        for (int i = listenerList.length - 2; i >= 0; i -= 2) {
            if (listenerList[i] == ((DefaultButtonModel.class$javax$swing$event$ChangeListener != null) ? DefaultButtonModel.class$javax$swing$event$ChangeListener : (DefaultButtonModel.class$javax$swing$event$ChangeListener = class$("javax.swing.event.ChangeListener")))) {
                if (this.changeEvent == null) {
                    this.changeEvent = new ChangeEvent(this);
                }
                ((ChangeListener)listenerList[i + 1]).stateChanged(this.changeEvent);
            }
        }
    }
    
    public String getActionCommand() {
        return this.actionCommand;
    }
    
    public int getMnemonic() {
        return this.mnemonic;
    }
    
    public Object[] getSelectedObjects() {
        return null;
    }
    
    public boolean isArmed() {
        return (this.stateMask & 0x1) != 0x0;
    }
    
    public boolean isEnabled() {
        return (this.stateMask & 0x8) != 0x0;
    }
    
    public boolean isPressed() {
        return (this.stateMask & 0x4) != 0x0;
    }
    
    public boolean isRollover() {
        return (this.stateMask & 0x10) != 0x0;
    }
    
    public boolean isSelected() {
        return (this.stateMask & 0x2) != 0x0;
    }
    
    public void removeActionListener(final ActionListener actionListener) {
        this.listenerList.remove((DefaultButtonModel.class$java$awt$event$ActionListener != null) ? DefaultButtonModel.class$java$awt$event$ActionListener : (DefaultButtonModel.class$java$awt$event$ActionListener = class$("java.awt.event.ActionListener")), actionListener);
    }
    
    public void removeChangeListener(final ChangeListener changeListener) {
        this.listenerList.remove((DefaultButtonModel.class$javax$swing$event$ChangeListener != null) ? DefaultButtonModel.class$javax$swing$event$ChangeListener : (DefaultButtonModel.class$javax$swing$event$ChangeListener = class$("javax.swing.event.ChangeListener")), changeListener);
    }
    
    public void removeItemListener(final ItemListener itemListener) {
        this.listenerList.remove((DefaultButtonModel.class$java$awt$event$ItemListener != null) ? DefaultButtonModel.class$java$awt$event$ItemListener : (DefaultButtonModel.class$java$awt$event$ItemListener = class$("java.awt.event.ItemListener")), itemListener);
    }
    
    public void setActionCommand(final String actionCommand) {
        this.actionCommand = actionCommand;
    }
    
    public void setArmed(final boolean b) {
        if (this.isArmed() == b || !this.isEnabled()) {
            return;
        }
        if (b) {
            this.stateMask |= 0x1;
        }
        else {
            this.stateMask &= 0xFFFFFFFE;
        }
        this.fireStateChanged();
    }
    
    public void setEnabled(final boolean b) {
        if (this.isEnabled() == b) {
            return;
        }
        if (b) {
            this.stateMask |= 0x8;
        }
        else {
            this.stateMask &= 0xFFFFFFF7;
        }
        this.fireStateChanged();
    }
    
    public void setGroup(final ButtonGroup group) {
        this.group = group;
    }
    
    public void setMnemonic(final int mnemonic) {
        this.mnemonic = mnemonic;
        this.fireStateChanged();
    }
    
    public void setPressed(final boolean b) {
        if (this.isPressed() == b || !this.isEnabled()) {
            return;
        }
        if (b) {
            this.stateMask |= 0x4;
        }
        else {
            this.stateMask &= 0xFFFFFFFB;
        }
        if (!this.isPressed() && this.isArmed()) {
            this.fireActionPerformed(new ActionEvent(this, 1001, this.getActionCommand()));
        }
        this.fireStateChanged();
    }
    
    public void setRollover(final boolean b) {
        if (this.isRollover() == b || !this.isEnabled()) {
            return;
        }
        if (b) {
            this.stateMask |= 0x10;
        }
        else {
            this.stateMask &= 0xFFFFFFEF;
        }
        this.fireStateChanged();
    }
    
    public void setSelected(final boolean b) {
        if (this.isSelected() == b) {
            return;
        }
        if (b) {
            this.stateMask |= 0x2;
        }
        else {
            this.stateMask &= 0xFFFFFFFD;
        }
        this.fireItemStateChanged(new ItemEvent(this, 701, this, b ? 1 : 2));
        this.fireStateChanged();
    }
}
