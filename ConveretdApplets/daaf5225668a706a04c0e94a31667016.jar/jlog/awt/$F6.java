// 
// Decompiled by Procyon v0.5.30
// 

package jlog.awt;

import java.util.Enumeration;
import java.awt.Component;
import java.util.Vector;

public class $F6 implements Runnable
{
    private Vector $G6;
    private Vector $H6;
    private Thread $OR;
    private Object $I6;
    private int $J6;
    private boolean $K6;
    private Object $L6;
    
    private void $M6() {
        synchronized (this.$L6) {
            if (this.$OR != null || !this.$K6 || this.$G6.isEmpty()) {
                // monitorexit(this.$L6)
                return;
            }
            (this.$OR = new Thread(this, this.getClass().getName())).start();
        }
        // monitorexit(this.$L6)
    }
    
    private int $N6(final Component component) {
        synchronized (this.$G6) {
            final int index = this.$G6.indexOf(component);
            if (index > -1) {
                component.setVisible((boolean)this.$H6.elementAt(index));
            }
            // monitorexit(this.$G6)
            return index;
        }
    }
    
    void $O6() {
        boolean visible = false;
        final Thread currentThread = Thread.currentThread();
        Vector<Component> vector = new Vector<Component>();
        try {
            while (currentThread == this.$OR) {
                if (this.$G6.isEmpty()) {
                    return;
                }
                synchronized (this.$G6) {
                    vector = (Vector<Component>)this.$G6.clone();
                }
                // monitorexit(this.$G6)
                visible ^= true;
                final Enumeration<Component> elements = vector.elements();
                while (elements.hasMoreElements() && currentThread == this.$OR) {
                    elements.nextElement().setVisible(visible);
                }
                for (long n = (int)(this.$J6 * (visible ? 0.7f : 0.3f)); currentThread == this.$OR && n > 0L; n -= 120L) {
                    Thread.sleep(120L);
                }
            }
        }
        catch (InterruptedException ex2) {}
        catch (Exception ex) {
            ex.printStackTrace();
        }
        final Enumeration<Component> elements2 = vector.elements();
        while (elements2.hasMoreElements()) {
            this.$N6(elements2.nextElement());
        }
    }
    
    public $F6(final int $j6) {
        this.$G6 = null;
        this.$H6 = null;
        this.$OR = null;
        this.$I6 = new Object();
        this.$K6 = false;
        this.$L6 = new Object();
        this.$J6 = $j6;
        this.$G6 = new Vector();
        this.$H6 = new Vector();
    }
    
    public void add(final Component component) {
        synchronized (this.$G6) {
            this.$H6.addElement(new Boolean(component.isVisible()));
            this.$G6.addElement(component);
        }
        // monitorexit(this.$G6)
        if (this.$K6) {
            this.$M6();
        }
    }
    
    public boolean contains(final Component component) {
        synchronized (this.$G6) {
            // monitorexit(this.$G6)
            return this.$G6.indexOf(component) != -1;
        }
    }
    
    public void remove(final Component component) {
        final int $n6 = this.$N6(component);
        if ($n6 > -1) {
            synchronized (this.$G6) {
                this.$G6.removeElement(component);
                this.$H6.removeElementAt($n6);
            }
            // monitorexit(this.$G6)
        }
    }
    
    public void run() {
        try {
            this.$O6();
        }
        finally {
            synchronized (this.$I6) {
                if (Thread.currentThread() == this.$OR) {
                    this.$OR = null;
                }
                this.$I6.notifyAll();
            }
            // monitorexit(this.$I6)
        }
    }
    
    public void start() {
        synchronized (this.$L6) {
            this.$K6 = true;
            this.$M6();
        }
        // monitorexit(this.$L6)
    }
    
    public void stop() {
        synchronized (this.$L6) {
            if (!this.$K6) {
                // monitorexit(this.$L6)
                return;
            }
            this.$K6 = false;
        }
        // monitorexit(this.$L6)
        synchronized (this.$I6) {
            if (this.$OR != null) {
                this.$OR.interrupt();
                this.$OR = null;
                try {
                    this.$I6.wait(4000L);
                }
                catch (InterruptedException ex) {}
            }
        }
        // monitorexit(this.$I6)
    }
}
