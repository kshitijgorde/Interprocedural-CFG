// 
// Decompiled by Procyon v0.5.30
// 

package ji.awt;

import java.awt.event.KeyEvent;
import java.awt.Choice;
import java.awt.event.ItemListener;
import java.awt.event.MouseListener;
import java.awt.event.FocusListener;
import java.awt.Rectangle;
import java.awt.event.KeyListener;
import ji.v1event.ar;
import ji.util.e;
import java.awt.Component;
import ji.util.d;
import java.awt.LayoutManager;
import java.util.EventListener;
import ji.v1base.jiPanel;

public class eb extends jiPanel
{
    u5 a;
    u5 b;
    boolean c;
    private int d;
    private int e;
    private String f;
    EventListener[] g;
    boolean h;
    u5 i;
    int j;
    String k;
    
    public eb(final String f, final LayoutManager layout) {
        super(f);
        this.c = false;
        this.f = null;
        this.g = new EventListener[0];
        this.h = false;
        this.j = -1;
        this.k = null;
        this.f = f;
        this.setBorderStyle(0);
        this.setLayout(layout);
        (this.b = new u5()).add(ji.util.d.b(430, "jiChoice"));
        this.b.setEnabled(false);
        this.a = new u5();
        super.add(this.b);
        this.b.setVisible(false);
        super.add(this.a);
        ji.util.e.a(f, this);
        ji.util.d.a(f, this);
        ji.util.d.c(this);
        ji.util.e.a(f, this.a, true);
        this.a.addKeyListener(this);
    }
    
    public eb(final String s) {
        this(s, null);
    }
    
    public void setBounds(final int n, final int n2, final int d, final int e) {
        this.d = d;
        this.e = e;
        this.b.setBounds(0, 0, this.d, this.e);
        this.a.setBounds(0, 0, this.d, this.e);
        super.setBounds(n, n2, d, e);
    }
    
    public void setBounds(final Rectangle rectangle) {
        this.setBounds(rectangle.x, rectangle.y, rectangle.width, rectangle.height);
    }
    
    protected void a(final EventListener eventListener) {
        synchronized (this.g) {
            final EventListener[] g = new EventListener[this.g.length + 1];
            int i;
            for (i = 0; i < this.g.length; ++i) {
                g[i] = this.g[i];
                this.g[i] = null;
            }
            g[i] = eventListener;
        }
        // monitorexit(this.g = g)
    }
    
    protected void b(final EventListener eventListener) {
        synchronized (this.g) {
            if (this.g == null) {
                // monitorexit(this.g)
                return;
            }
            int n = 0;
            for (int i = 0; i < this.g.length; ++i) {
                if (this.g[i].equals(eventListener)) {
                    ++n;
                }
            }
            if (n > 0) {
                final EventListener[] g = new EventListener[this.g.length - n];
                int n2 = 0;
                for (int j = 0; j < this.g.length; ++j) {
                    if (!this.g[j].equals(eventListener)) {
                        g[n2++] = this.g[j];
                    }
                    this.g[j] = null;
                }
                this.g = g;
            }
        }
        // monitorexit(this.g)
    }
    
    public void addKeyListener(final KeyListener keyListener) {
        this.a(keyListener);
        this.a.addKeyListener(keyListener);
    }
    
    public void removeKeyListener(final KeyListener keyListener) {
        this.b(keyListener);
        this.a.removeKeyListener(keyListener);
    }
    
    public void addFocusListener(final FocusListener focusListener) {
        this.a(focusListener);
        this.a.addFocusListener(focusListener);
    }
    
    public void removeFocusListener(final FocusListener focusListener) {
        this.b(focusListener);
        this.a.removeFocusListener(focusListener);
    }
    
    public void addMouseListener(final MouseListener mouseListener) {
        this.a(mouseListener);
        this.a.addMouseListener(mouseListener);
    }
    
    public void removeMouseListener(final MouseListener mouseListener) {
        this.b(mouseListener);
        this.a.removeMouseListener(mouseListener);
    }
    
    public void a(final String s) {
        if (!this.h) {
            this.a.add(s);
        }
        else {
            this.i.add(s);
        }
    }
    
    public void a() {
        if (this.i == null) {
            (this.i = new u5()).setBounds(0, 0, this.d, this.e);
        }
        else {
            this.i.removeAll();
        }
        super.remove(1);
        this.b.setVisible(true);
        this.j = -1;
        this.k = null;
        this.h = true;
        this.validate();
    }
    
    public void releaseResources() {
        if (!this.c) {
            this.c = true;
            if (!ji.util.d.ao(this.f)) {
                this.removeNotify();
            }
            this.setRightMouseEmulator(false);
            this.clearWheelHandler();
            this.removeKeyListener(this);
            ji.util.e.b(this.f, this);
            ji.util.d.b(this.f, this);
            ji.util.d.d(this);
            ji.util.e.a(this.f, this.a, false);
            super.remove(this.a);
            this.a.removeKeyListener(this);
            this.a = null;
        }
    }
    
    public void b() {
        for (int i = this.a.getItemCount(); i > 0; --i) {
            this.i.insert(this.a.getItem(i), 0);
        }
        synchronized (this.g) {
            for (int j = 0; j < this.g.length; ++j) {
                final EventListener eventListener = this.g[j];
                if (eventListener instanceof KeyListener) {
                    this.a.removeKeyListener((KeyListener)eventListener);
                    this.i.addKeyListener((KeyListener)eventListener);
                }
                else if (eventListener instanceof FocusListener) {
                    this.a.removeFocusListener((FocusListener)eventListener);
                    this.i.addFocusListener((FocusListener)eventListener);
                }
                else if (eventListener instanceof MouseListener) {
                    this.a.removeMouseListener((MouseListener)eventListener);
                    this.i.addMouseListener((MouseListener)eventListener);
                }
                else if (eventListener instanceof ItemListener) {
                    this.a.removeItemListener((ItemListener)eventListener);
                    this.i.addItemListener((ItemListener)eventListener);
                }
            }
        }
        // monitorexit(this.g)
        this.a.removeAll();
        this.a = this.i;
        this.i = null;
        if (this.j > -1) {
            this.a.select(this.j);
        }
        else if (this.k != null) {
            this.a.select(this.k);
        }
        super.add(this.a, 1);
        this.a.setBounds(0, 0, this.d, this.e);
        this.b.setVisible(false);
        this.h = false;
        this.validate();
    }
    
    public void remove(final int n) {
        this.a.remove(n);
    }
    
    public void removeAll() {
        this.a.removeAll();
    }
    
    public Choice c() {
        return this.a;
    }
    
    public synchronized String d() {
        return this.a.getSelectedItem();
    }
    
    public int e() {
        return this.a.getSelectedIndex();
    }
    
    public synchronized void b(final String k) {
        if (this.h) {
            this.j = -1;
            this.k = k;
        }
        else {
            this.a.select(k);
        }
    }
    
    public synchronized void a(final ItemListener itemListener) {
        this.a((EventListener)itemListener);
        this.a.addItemListener(itemListener);
    }
    
    public void keyTyped(final KeyEvent keyEvent) {
    }
    
    public void keyPressed(final KeyEvent keyEvent) {
    }
    
    public void keyReleased(final KeyEvent keyEvent) {
    }
    
    public void setFocusRingId(final String s) {
        if (this.a != null) {
            this.a.a(s);
        }
        if (this.i != null) {
            this.i.a(s);
        }
    }
    
    public String getFocusRingId() {
        if (this.a != null) {
            return this.a.getFocusRingId();
        }
        return "";
    }
    
    private class u5 extends Choice implements ar
    {
        private boolean a;
        private boolean b;
        private String c;
        
        public u5() {
            this.a = true;
            this.b = false;
            this.c = null;
        }
        
        public boolean ignoreTAB() {
            return this.b;
        }
        
        public boolean acceptFocus() {
            return this.a;
        }
        
        public void a(final String c) {
            this.c = c;
        }
        
        public String getFocusRingId() {
            return this.c;
        }
        
        public void transferFocus() {
        }
    }
}
