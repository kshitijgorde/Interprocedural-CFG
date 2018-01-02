import java.awt.event.ItemEvent;
import java.awt.event.ActionEvent;
import java.awt.event.ItemListener;
import java.util.Vector;
import java.awt.event.ActionListener;
import java.awt.ItemSelectable;
import java.awt.MenuItem;

// 
// Decompiled by Procyon v0.5.30
// 

public class Sh extends MenuItem implements ItemSelectable, ActionListener
{
    private boolean Bja;
    private String Cja;
    Vector Dja;
    private static final String Eja = "# ";
    private static final String Fja = "   ";
    
    public Sh(final String cja, final boolean bja) {
        super(bja ? ("# " + cja) : ("   " + cja));
        this.Bja = false;
        this.Dja = new Vector(1);
        this.Cja = cja;
        this.Bja = bja;
        super.addActionListener(this);
    }
    
    public Sh(final String s) {
        this(s, false);
    }
    
    public synchronized void addItemListener(final ItemListener itemListener) {
        if (itemListener != null && !this.Dja.contains(itemListener)) {
            this.Dja.addElement(itemListener);
        }
    }
    
    public synchronized void removeItemListener(final ItemListener itemListener) {
        if (itemListener != null) {
            this.Dja.removeElement(itemListener);
        }
    }
    
    public synchronized Object[] getSelectedObjects() {
        Object[] array = null;
        if (this.Bja) {
            array = new Object[] { this.Cja };
        }
        return array;
    }
    
    public synchronized boolean getState() {
        return this.Bja;
    }
    
    public synchronized void setState(final boolean bja) {
        this.Bja = bja;
        super.setLabel(bja ? ("# " + this.Cja) : ("   " + this.Cja));
    }
    
    public synchronized void addActionListener(final ActionListener actionListener) {
        throw new UnsupportedOperationException();
    }
    
    public synchronized void removeActionListener(final ActionListener actionListener) {
        throw new UnsupportedOperationException();
    }
    
    public void actionPerformed(final ActionEvent actionEvent) {
        this.setState(!this.Bja);
        ItemListener[] array = null;
        synchronized (this) {
            if (this.Dja.size() > 0) {
                array = new ItemListener[this.Dja.size()];
                for (int i = 0; i < this.Dja.size(); ++i) {
                    array[i] = (ItemListener)this.Dja.elementAt(i);
                }
            }
        }
        if (array != null) {
            final ItemEvent itemEvent = new ItemEvent(this, 701, this.getLabel(), this.Bja ? 1 : 2);
            for (int j = 0; j < array.length; ++j) {
                array[j].itemStateChanged(itemEvent);
            }
        }
    }
}
