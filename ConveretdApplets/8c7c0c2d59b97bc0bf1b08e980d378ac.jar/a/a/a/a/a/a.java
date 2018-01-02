// 
// Decompiled by Procyon v0.5.30
// 

package a.a.a.a.a;

import java.util.Enumeration;
import java.awt.event.ActionEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.Vector;
import java.io.Serializable;

public class a implements Serializable
{
    public static final String int = "onceOnly";
    public static final String else = "delay";
    public static final long try = 1000L;
    public static final boolean a = false;
    static final long if = -7954930904657028678L;
    private transient a do;
    private transient Vector char;
    private PropertyChangeSupport byte;
    private boolean new;
    private boolean case;
    private long for;
    
    public a() {
        this.for = 1000L;
        this.case = false;
        this.byte = new PropertyChangeSupport(this);
    }
    
    public synchronized void do() {
        if (this.new) {
            return;
        }
        this.do = new a();
        this.new = true;
        this.do.start();
    }
    
    public synchronized void int() {
        if (!this.new) {
            return;
        }
        this.do.stop();
        this.do = null;
        this.new = false;
    }
    
    public long for() {
        return this.for;
    }
    
    public void a(final long for1) {
        if (this.for == for1) {
            return;
        }
        final long for2 = this.for;
        this.for = for1;
        this.byte.firePropertyChange("delay", new Long(for2), new Long(this.for));
    }
    
    public boolean if() {
        return this.case;
    }
    
    public void a(final boolean case1) {
        if (this.case == case1) {
            return;
        }
        this.case = case1;
        this.byte.firePropertyChange("onceOnly", this.case ? Boolean.FALSE : Boolean.TRUE, this.case ? Boolean.TRUE : Boolean.FALSE);
    }
    
    public void if(final PropertyChangeListener propertyChangeListener) {
        this.byte.addPropertyChangeListener(propertyChangeListener);
    }
    
    public void a(final PropertyChangeListener propertyChangeListener) {
        this.byte.removePropertyChangeListener(propertyChangeListener);
    }
    
    public void if(final c c) {
        if (this.char == null) {
            this.char = new Vector();
        }
        this.char.addElement(c);
    }
    
    public void a(final c c) {
        if (this.char == null) {
            return;
        }
        this.char.removeElement(c);
    }
    
    private void a() {
        if (this.char == null) {
            return;
        }
        final Vector vector;
        synchronized (this) {
            vector = (Vector)this.char.clone();
        }
        final Enumeration<c> elements = vector.elements();
        while (elements.hasMoreElements()) {
            elements.nextElement().a(new ActionEvent(this, 1001, "onTime"));
        }
    }
    
    class a extends Thread
    {
        public void run() {
            do {
                try {
                    Thread.sleep(a.a.a.a.a.a.this.for);
                }
                catch (InterruptedException ex) {}
                a.a.a.a.a.a.this.a();
            } while (!a.a.a.a.a.a.this.case);
            a.a.a.a.a.a.this.new = false;
        }
    }
}
