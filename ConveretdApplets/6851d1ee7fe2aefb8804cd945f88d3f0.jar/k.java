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
    public static final boolean uka;
    CheckboxMenuItem vka;
    Sh wka;
    
    public k(final String s, final boolean b) {
        this.vka = null;
        this.wka = null;
        if (k.uka) {
            this.vka = new CheckboxMenuItem(s, b);
        }
        else {
            this.wka = new Sh(s, b);
        }
    }
    
    public k(final String s) {
        this(s, false);
    }
    
    public synchronized void addItemListener(final ItemListener itemListener) {
        if (k.uka) {
            this.vka.addItemListener(itemListener);
        }
        else {
            this.wka.addItemListener(itemListener);
        }
    }
    
    public synchronized void removeItemListener(final ItemListener itemListener) {
        if (k.uka) {
            this.vka.removeItemListener(itemListener);
        }
        else {
            this.wka.removeItemListener(itemListener);
        }
    }
    
    public synchronized Object[] getSelectedObjects() {
        if (k.uka) {
            return this.vka.getSelectedObjects();
        }
        return this.wka.getSelectedObjects();
    }
    
    public synchronized boolean getState() {
        if (k.uka) {
            return this.vka.getState();
        }
        return this.wka.getState();
    }
    
    public synchronized void setState(final boolean b) {
        if (k.uka) {
            this.vka.setState(b);
        }
        else {
            this.wka.setState(b);
        }
    }
    
    public synchronized void addActionListener(final ActionListener actionListener) {
        throw new UnsupportedOperationException();
    }
    
    public synchronized void removeActionListener(final ActionListener actionListener) {
        throw new UnsupportedOperationException();
    }
    
    public synchronized boolean isEnabled() {
        if (k.uka) {
            return this.vka.isEnabled();
        }
        return this.wka.isEnabled();
    }
    
    public synchronized void setEnabled(final boolean b) {
        if (k.uka) {
            this.vka.setEnabled(b);
        }
        else {
            this.wka.setEnabled(b);
        }
    }
    
    public synchronized MenuShortcut getShortcut() {
        if (k.uka) {
            return this.vka.getShortcut();
        }
        return this.wka.getShortcut();
    }
    
    public synchronized void setShortcut(final MenuShortcut menuShortcut) {
        if (k.uka) {
            this.vka.setShortcut(menuShortcut);
        }
        else {
            this.wka.setShortcut(menuShortcut);
        }
    }
    
    public synchronized void deleteShortcut() {
        if (k.uka) {
            this.vka.deleteShortcut();
        }
        else {
            this.wka.deleteShortcut();
        }
    }
    
    public synchronized void setActionCommand(final String s) {
        if (k.uka) {
            this.vka.setActionCommand(s);
        }
        else {
            this.wka.setActionCommand(s);
        }
    }
    
    public synchronized String getActionCommand() {
        if (k.uka) {
            return this.vka.getActionCommand();
        }
        return this.wka.getActionCommand();
    }
    
    public synchronized String paramString() {
        if (k.uka) {
            return this.vka.paramString();
        }
        return this.wka.paramString();
    }
    
    public synchronized MenuItem b() {
        if (k.uka) {
            return this.vka;
        }
        return this.wka;
    }
    
    static {
        if (System.getProperty("os.name").indexOf("Windows") != -1) {
            uka = true;
        }
        else {
            uka = false;
        }
    }
}
