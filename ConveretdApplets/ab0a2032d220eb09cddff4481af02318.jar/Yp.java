import java.awt.MenuItem;
import java.awt.MenuShortcut;
import java.awt.event.ActionListener;
import java.awt.event.ItemListener;
import java.awt.CheckboxMenuItem;

// 
// Decompiled by Procyon v0.5.30
// 

public class Yp
{
    public static final boolean _a;
    CheckboxMenuItem aa;
    sq ba;
    
    public Yp(final String s, final boolean b) {
        this.aa = null;
        this.ba = null;
        if (Yp._a) {
            this.aa = new CheckboxMenuItem(s, b);
        }
        else {
            this.ba = new sq(s, b);
        }
    }
    
    public Yp(final String s) {
        this(s, false);
    }
    
    public synchronized void addItemListener(final ItemListener itemListener) {
        if (Yp._a) {
            this.aa.addItemListener(itemListener);
        }
        else {
            this.ba.addItemListener(itemListener);
        }
    }
    
    public synchronized void removeItemListener(final ItemListener itemListener) {
        if (Yp._a) {
            this.aa.removeItemListener(itemListener);
        }
        else {
            this.ba.removeItemListener(itemListener);
        }
    }
    
    public synchronized Object[] getSelectedObjects() {
        if (Yp._a) {
            return this.aa.getSelectedObjects();
        }
        return this.ba.getSelectedObjects();
    }
    
    public boolean getState() {
        if (Yp._a) {
            return this.aa.getState();
        }
        return this.ba.getState();
    }
    
    public synchronized void setState(final boolean b) {
        if (Yp._a) {
            this.aa.setState(b);
        }
        else {
            this.ba.setState(b);
        }
    }
    
    public synchronized void addActionListener(final ActionListener actionListener) {
        throw new UnsupportedOperationException();
    }
    
    public synchronized void removeActionListener(final ActionListener actionListener) {
        throw new UnsupportedOperationException();
    }
    
    public boolean isEnabled() {
        if (Yp._a) {
            return this.aa.isEnabled();
        }
        return this.ba.isEnabled();
    }
    
    public synchronized void setEnabled(final boolean b) {
        if (Yp._a) {
            this.aa.setEnabled(b);
        }
        else {
            this.ba.setEnabled(b);
        }
    }
    
    public MenuShortcut getShortcut() {
        if (Yp._a) {
            return this.aa.getShortcut();
        }
        return this.ba.getShortcut();
    }
    
    public void setShortcut(final MenuShortcut menuShortcut) {
        if (Yp._a) {
            this.aa.setShortcut(menuShortcut);
        }
        else {
            this.ba.setShortcut(menuShortcut);
        }
    }
    
    public void deleteShortcut() {
        if (Yp._a) {
            this.aa.deleteShortcut();
        }
        else {
            this.ba.deleteShortcut();
        }
    }
    
    public void setActionCommand(final String s) {
        if (Yp._a) {
            this.aa.setActionCommand(s);
        }
        else {
            this.ba.setActionCommand(s);
        }
    }
    
    public String getActionCommand() {
        if (Yp._a) {
            return this.aa.getActionCommand();
        }
        return this.ba.getActionCommand();
    }
    
    public String paramString() {
        if (Yp._a) {
            return this.aa.paramString();
        }
        return this.ba.paramString();
    }
    
    public MenuItem _() {
        if (Yp._a) {
            return this.aa;
        }
        return this.ba;
    }
    
    static {
        if (System.getProperty("os.name").indexOf("Windows") != -1) {
            _a = true;
        }
        else {
            _a = false;
        }
    }
}
