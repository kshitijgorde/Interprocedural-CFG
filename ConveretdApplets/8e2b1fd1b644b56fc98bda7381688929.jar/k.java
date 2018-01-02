import java.awt.MenuItem;
import java.awt.MenuShortcut;
import java.awt.event.ActionListener;
import java.awt.event.ItemListener;
import java.awt.CheckboxMenuItem;

// 
// Decompiled by Procyon v0.5.30
// 

public class k
{
    public static final boolean qsa;
    CheckboxMenuItem rsa;
    goto ssa;
    
    public k(final String s, final boolean b) {
        this.rsa = null;
        this.ssa = null;
        if (k.qsa) {
            this.rsa = new CheckboxMenuItem(s, b);
        }
        else {
            this.ssa = new goto(s, b);
        }
    }
    
    public k(final String s) {
        this(s, false);
    }
    
    public synchronized void addItemListener(final ItemListener itemListener) {
        if (k.qsa) {
            this.rsa.addItemListener(itemListener);
        }
        else {
            this.ssa.addItemListener(itemListener);
        }
    }
    
    public synchronized void removeItemListener(final ItemListener itemListener) {
        if (k.qsa) {
            this.rsa.removeItemListener(itemListener);
        }
        else {
            this.ssa.removeItemListener(itemListener);
        }
    }
    
    public synchronized Object[] getSelectedObjects() {
        if (k.qsa) {
            return this.rsa.getSelectedObjects();
        }
        return this.ssa.getSelectedObjects();
    }
    
    public synchronized boolean getState() {
        if (k.qsa) {
            return this.rsa.getState();
        }
        return this.ssa.getState();
    }
    
    public synchronized void setState(final boolean b) {
        if (k.qsa) {
            this.rsa.setState(b);
        }
        else {
            this.ssa.setState(b);
        }
    }
    
    public synchronized void addActionListener(final ActionListener actionListener) {
        throw new UnsupportedOperationException();
    }
    
    public synchronized void removeActionListener(final ActionListener actionListener) {
        throw new UnsupportedOperationException();
    }
    
    public synchronized boolean isEnabled() {
        if (k.qsa) {
            return this.rsa.isEnabled();
        }
        return this.ssa.isEnabled();
    }
    
    public synchronized void setEnabled(final boolean b) {
        if (k.qsa) {
            this.rsa.setEnabled(b);
        }
        else {
            this.ssa.setEnabled(b);
        }
    }
    
    public synchronized MenuShortcut getShortcut() {
        if (k.qsa) {
            return this.rsa.getShortcut();
        }
        return this.ssa.getShortcut();
    }
    
    public synchronized void setShortcut(final MenuShortcut menuShortcut) {
        if (k.qsa) {
            this.rsa.setShortcut(menuShortcut);
        }
        else {
            this.ssa.setShortcut(menuShortcut);
        }
    }
    
    public synchronized void deleteShortcut() {
        if (k.qsa) {
            this.rsa.deleteShortcut();
        }
        else {
            this.ssa.deleteShortcut();
        }
    }
    
    public synchronized void setActionCommand(final String s) {
        if (k.qsa) {
            this.rsa.setActionCommand(s);
        }
        else {
            this.ssa.setActionCommand(s);
        }
    }
    
    public synchronized String getActionCommand() {
        if (k.qsa) {
            return this.rsa.getActionCommand();
        }
        return this.ssa.getActionCommand();
    }
    
    public synchronized String paramString() {
        if (k.qsa) {
            return this.rsa.paramString();
        }
        return this.ssa.paramString();
    }
    
    public synchronized MenuItem _() {
        if (k.qsa) {
            return this.rsa;
        }
        return this.ssa;
    }
    
    static {
        if (System.getProperty("os.name").indexOf("Windows") != -1) {
            qsa = true;
        }
        else {
            qsa = false;
        }
    }
}
