// 
// Decompiled by Procyon v0.5.30
// 

package ji.awt;

import java.awt.event.ItemListener;
import java.awt.event.MouseListener;
import java.awt.event.FocusListener;
import java.awt.Rectangle;
import java.awt.Checkbox;
import java.awt.event.KeyListener;
import ji.v1event.ar;
import ji.util.d;
import ji.util.e;
import java.awt.Component;
import java.awt.LayoutManager;
import java.awt.FlowLayout;
import java.awt.CheckboxGroup;
import java.util.EventListener;
import ji.v1base.jiPanel;

public class fs extends jiPanel
{
    private wi a;
    private EventListener[] b;
    private boolean c;
    private int d;
    private int e;
    private String f;
    
    public fs(final String f, final String s, final boolean b, final CheckboxGroup checkboxGroup) {
        super(f);
        this.a = null;
        this.b = new EventListener[0];
        this.c = false;
        this.f = null;
        this.f = f;
        this.setBorderStyle(0);
        this.setLayout(new FlowLayout());
        super.add(this.a = new wi(s, b, checkboxGroup));
        ji.util.e.a(f, this);
        ji.util.d.a(f, this);
        ji.util.d.c(this);
        ji.util.e.a(f, this.a, true);
        this.a.addKeyListener(this);
    }
    
    public Checkbox a() {
        return this.a;
    }
    
    public void a(final boolean state) {
        this.a.setState(state);
    }
    
    public void setBounds(final int n, final int n2, final int d, final int e) {
        this.d = d;
        this.e = e;
        this.a.setBounds(0, 0, this.d, this.e);
        super.setBounds(n, n2, d, e);
    }
    
    public void setBounds(final Rectangle rectangle) {
        this.setBounds(rectangle.x, rectangle.y, rectangle.width, rectangle.height);
    }
    
    protected void a(final EventListener eventListener) {
        synchronized (this.b) {
            final EventListener[] b = new EventListener[this.b.length + 1];
            int i;
            for (i = 0; i < this.b.length; ++i) {
                b[i] = this.b[i];
                this.b[i] = null;
            }
            b[i] = eventListener;
        }
        // monitorexit(this.b = b)
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
            ji.util.e.a(this.f, this, false);
            this.a.removeKeyListener(this);
            this.a = null;
        }
    }
    
    protected void b(final EventListener eventListener) {
        synchronized (this.b) {
            if (this.b == null) {
                // monitorexit(this.b)
                return;
            }
            int n = 0;
            for (int i = 0; i < this.b.length; ++i) {
                if (this.b[i].equals(eventListener)) {
                    ++n;
                }
            }
            if (n > 0) {
                final EventListener[] b = new EventListener[this.b.length - n];
                int n2 = 0;
                for (int j = 0; j < this.b.length; ++j) {
                    if (!this.b[j].equals(eventListener)) {
                        b[n2++] = this.b[j];
                    }
                    this.b[j] = null;
                }
                this.b = b;
            }
        }
        // monitorexit(this.b)
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
    
    public void a(final ItemListener itemListener) {
        this.a((EventListener)itemListener);
        this.a.addItemListener(itemListener);
    }
    
    public void setFocusRingId(final String s) {
        if (this.a != null) {
            this.a.a(s);
        }
    }
    
    public String getFocusRingId() {
        if (this.a != null) {
            return this.a.getFocusRingId();
        }
        return "";
    }
    
    private class wi extends Checkbox implements ar
    {
        private boolean a;
        private boolean b;
        private String c;
        
        public wi(final String s, final boolean b, final CheckboxGroup checkboxGroup) {
            super(s, b, checkboxGroup);
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
