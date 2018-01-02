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

public class sq extends MenuItem implements ItemSelectable, ActionListener
{
    private boolean Ha;
    private String Ia;
    Vector Ja;
    private static final String Ka = "# ";
    private static final String La = "   ";
    
    public sq(final String ia, final boolean ha) {
        super(ha ? ("# " + ia) : ("   " + ia));
        this.Ha = false;
        this.Ja = new Vector(1);
        this.Ia = ia;
        this.Ha = ha;
        super.addActionListener(this);
    }
    
    public sq(final String s) {
        this(s, false);
    }
    
    public synchronized void addItemListener(final ItemListener itemListener) {
        if (itemListener != null && !this.Ja.contains(itemListener)) {
            this.Ja.addElement(itemListener);
        }
    }
    
    public synchronized void removeItemListener(final ItemListener itemListener) {
        if (itemListener != null) {
            this.Ja.removeElement(itemListener);
        }
    }
    
    public synchronized Object[] getSelectedObjects() {
        Object[] array = null;
        if (this.Ha) {
            array = new Object[] { this.Ia };
        }
        return array;
    }
    
    public boolean getState() {
        return this.Ha;
    }
    
    public synchronized void setState(final boolean ha) {
        this.Ha = ha;
        super.setLabel(ha ? ("# " + this.Ia) : ("   " + this.Ia));
    }
    
    public synchronized void addActionListener(final ActionListener actionListener) {
        throw new UnsupportedOperationException();
    }
    
    public synchronized void removeActionListener(final ActionListener actionListener) {
        throw new UnsupportedOperationException();
    }
    
    public void actionPerformed(final ActionEvent actionEvent) {
        this.setState(!this.Ha);
        ItemListener[] array = null;
        synchronized (this) {
            if (this.Ja.size() > 0) {
                array = new ItemListener[this.Ja.size()];
                for (int i = 0; i < this.Ja.size(); ++i) {
                    array[i] = (ItemListener)this.Ja.elementAt(i);
                }
            }
        }
        if (array != null) {
            final ItemEvent itemEvent = new ItemEvent(this, 701, this.getLabel(), this.Ha ? 1 : 2);
            for (int j = 0; j < array.length; ++j) {
                array[j].itemStateChanged(itemEvent);
            }
        }
    }
}
