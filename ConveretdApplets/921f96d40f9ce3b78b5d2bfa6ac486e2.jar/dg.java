import java.awt.image.ImageObserver;
import java.awt.event.ItemEvent;
import java.awt.event.ActionEvent;
import java.awt.event.ItemListener;
import java.awt.event.ActionListener;
import java.util.Vector;
import java.awt.ItemSelectable;

// 
// Decompiled by Procyon v0.5.30
// 

public class dg extends dh implements ItemSelectable
{
    Vector p;
    Vector d;
    
    public dg(final a a) {
        super(null, a, 1000, 0, false);
        this.p = new Vector();
        this.d = new Vector();
    }
    
    public final int p() {
        return super.a.size();
    }
    
    public final void p(final ActionListener actionListener) {
        this.p.addElement(actionListener);
    }
    
    public final void removeItemListener(final ItemListener itemListener) {
        this.d.removeElement(itemListener);
    }
    
    public final void addItemListener(final ItemListener itemListener) {
        this.d.addElement(itemListener);
    }
    
    public final Object[] getSelectedObjects() {
        final String p = this.p();
        if (p == null) {
            return new Object[0];
        }
        return new Object[] { p };
    }
    
    public final void p(final ActionEvent actionEvent) {
        for (int i = 0; i < this.p.size(); ++i) {
            ((ActionListener)this.p.elementAt(i)).actionPerformed(actionEvent);
        }
    }
    
    public final void p(final ItemEvent itemEvent) {
        for (int i = 0; i < this.d.size(); ++i) {
            ((ItemListener)this.d.elementAt(i)).itemStateChanged(itemEvent);
        }
    }
    
    public final String p(final int n) {
        if (n < 0 || n >= super.a.size()) {
            return null;
        }
        return super.a.elementAt(n).toString();
    }
    
    public final void p(final int n) {
        if (n < 0 || n >= super.a.size()) {
            return;
        }
        final dj dj = super.a.elementAt(n);
        super.a.removeElementAt(n);
        this.p(dj);
    }
    
    public final void d(final String s, int n) {
        if (n < 0 || n >= super.a.size()) {
            n = 0;
        }
        final dr dr = new dr(s, super.c, super.i, this, super.h);
        super.a.insertElementAt(dr, n);
        this.d(dr);
    }
    
    public final void n(final String s) {
        final dr dr = new dr(s, super.c, super.i, this, super.h);
        super.a.addElement(dr);
        this.d(dr);
    }
    
    public final String p() {
        for (int i = 0; i < super.a.size(); ++i) {
            final dj dj = super.a.elementAt(i);
            if (dj.p()) {
                return dj.toString();
            }
        }
        return null;
    }
    
    public final void d() {
        super.a.removeAllElements();
        super.k = 0;
        super.v.setValues(0, super.j, 0, super.j);
        this.repaint();
    }
    
    public final void p(final String s, final int n) {
        for (int i = 0; i < super.a.size(); ++i) {
            final dj dj = super.a.elementAt(i);
            if (dj.toString().equalsIgnoreCase(s)) {
                dj.p(n);
            }
        }
        this.repaint();
    }
    
    public final void a(final String s) {
        for (int i = 0; i < super.a.size(); ++i) {
            final dj dj = super.a.elementAt(i);
            if (dj.toString().equalsIgnoreCase(s)) {
                dj.p(super.l);
            }
            else {
                dj.p(super.i);
            }
        }
        this.repaint();
    }
    
    public final void v(final String s) {
        int n = 0;
        for (int i = 0; i < super.a.size(); ++i) {
            final dj dj = super.a.elementAt(i);
            if (dj.toString().equalsIgnoreCase(s)) {
                if (n < super.v.getValue()) {
                    super.v.setValues(n, super.j, 0, super.k);
                }
                else if (n > super.v.getValue() + super.j - 20) {
                    super.v.setValues(n - super.j + 20, super.j, 0, super.k);
                }
                this.repaint();
                return;
            }
            n += dj.p();
        }
    }
    
    public final void n(final dj dj) {
        if (!dj.p()) {
            for (int i = 0; i < super.a.size(); ++i) {
                ((dj)super.a.elementAt(i)).p(false);
            }
            dj.p(true);
            this.p(new ItemEvent(this, 701, dj, 1));
        }
        else {
            dj.p(false);
            this.p(new ItemEvent(this, 701, dj, 2));
        }
        this.repaint();
    }
    
    public final void a(final dj dj) {
        for (int i = 0; i < super.a.size(); ++i) {
            ((dj)super.a.elementAt(i)).p(false);
        }
        dj.p(true);
        this.p(new ActionEvent(this, 1001, dj.toString()));
        this.repaint();
    }
}
