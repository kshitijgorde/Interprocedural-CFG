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

public class goto extends MenuItem implements ItemSelectable, ActionListener
{
    private boolean pqa;
    private String qqa;
    Vector rqa;
    private static final String sqa = "# ";
    private static final String tqa = "   ";
    
    public goto(final String qqa, final boolean pqa) {
        super(pqa ? ("# " + qqa) : ("   " + qqa));
        this.pqa = false;
        this.rqa = new Vector(1);
        this.qqa = qqa;
        this.pqa = pqa;
        super.addActionListener(this);
    }
    
    public goto(final String s) {
        this(s, false);
    }
    
    public synchronized void addItemListener(final ItemListener itemListener) {
        if (itemListener != null && !this.rqa.contains(itemListener)) {
            this.rqa.addElement(itemListener);
        }
    }
    
    public synchronized void removeItemListener(final ItemListener itemListener) {
        if (itemListener != null) {
            this.rqa.removeElement(itemListener);
        }
    }
    
    public synchronized Object[] getSelectedObjects() {
        Object[] array = null;
        if (this.pqa) {
            array = new Object[] { this.qqa };
        }
        return array;
    }
    
    public synchronized boolean getState() {
        return this.pqa;
    }
    
    public synchronized void setState(final boolean pqa) {
        this.pqa = pqa;
        super.setLabel(pqa ? ("# " + this.qqa) : ("   " + this.qqa));
    }
    
    public synchronized void addActionListener(final ActionListener actionListener) {
        throw new UnsupportedOperationException();
    }
    
    public synchronized void removeActionListener(final ActionListener actionListener) {
        throw new UnsupportedOperationException();
    }
    
    public void actionPerformed(final ActionEvent actionEvent) {
        this.setState(!this.pqa);
        ItemListener[] array = null;
        synchronized (this) {
            if (this.rqa.size() > 0) {
                array = new ItemListener[this.rqa.size()];
                for (int i = 0; i < this.rqa.size(); ++i) {
                    array[i] = (ItemListener)this.rqa.elementAt(i);
                }
            }
        }
        if (array != null) {
            final ItemEvent itemEvent = new ItemEvent(this, 701, this.getLabel(), this.pqa ? 1 : 2);
            for (int j = 0; j < array.length; ++j) {
                array[j].itemStateChanged(itemEvent);
            }
        }
    }
}
