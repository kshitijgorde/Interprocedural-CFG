// 
// Decompiled by Procyon v0.5.30
// 

package com.easypano.tw;

import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.Graphics;
import java.awt.Font;
import com.easypano.tw.d.p;
import com.easypano.tw.d.m;
import java.awt.Cursor;
import java.util.Vector;
import java.awt.ItemSelectable;

public class l extends f implements ItemSelectable
{
    protected int l;
    protected boolean m;
    protected dc n;
    private Vector o;
    
    public l() {
        this.l = 20;
        this.m = false;
        this.n = null;
        this.o = new Vector();
        this.setCursor(Cursor.getPredefinedCursor(12));
        this.a(new m(this));
        this.a(new dc(this));
        this.b(true);
    }
    
    public void setFont(final Font font) {
        super.setFont(font);
        this.m = true;
        final Graphics graphics = this.getGraphics();
        Label_0030: {
            if (com.easypano.tw.h.q != 0) {
                break Label_0030;
            }
            if (graphics == null) {
                return;
            }
            try {
                this.a(graphics);
            }
            finally {
                graphics.dispose();
            }
        }
        graphics.dispose();
    }
    
    private void a(final Graphics graphics) {
        Graphics graphics2 = graphics;
        if (com.easypano.tw.h.q == 0) {
            if (graphics == null) {
                return;
            }
            graphics2 = graphics;
        }
        final int height = graphics2.getFontMetrics(this.getFont()).getHeight();
        if (height > 16) {
            this.l = height + 4;
        }
        this.m = false;
    }
    
    public void a(final dc n) {
        this.n = n;
    }
    
    public dc e() {
        return this.n;
    }
    
    public int f() {
        return this.l;
    }
    
    public void b(final int n) {
    }
    
    public synchronized void addItemListener(final ItemListener itemListener) {
        this.o.addElement(itemListener);
    }
    
    public synchronized void removeItemListener(final ItemListener itemListener) {
        this.o.removeElement(itemListener);
    }
    
    public Object[] getSelectedObjects() {
        return null;
    }
    
    protected void a(final ItemEvent itemEvent) {
        Vector<ItemListener> vector = null;
        synchronized (this) {
            vector = (Vector<ItemListener>)this.o.clone();
        }
        int n = 0;
        while (true) {
            Label_0054: {
                if (com.easypano.tw.h.q == 0) {
                    break Label_0054;
                }
                vector.elementAt(n).itemStateChanged(itemEvent);
                ++n;
            }
            if (n >= this.o.size()) {
                return;
            }
            continue;
        }
    }
    
    public void g() {
        this.repaint();
    }
    
    public void h() {
        final int q = com.easypano.tw.h.q;
        l l = this;
        Label_0080: {
            if (q == 0) {
                if (this.e().c() >= 0) {
                    l = this;
                    if (q != 0) {
                        break Label_0080;
                    }
                    if (this.e().c() < this.e().f().size()) {
                        this.a(new ItemEvent(this, 701, this.e().f().elementAt(this.e().c()), 1));
                        if (q == 0) {
                            return;
                        }
                    }
                }
                l = this;
            }
        }
        l.a(new ItemEvent(this, 701, null, 1));
    }
    
    public void paint(final Graphics graphics) {
        l l = this;
        if (com.easypano.tw.h.q == 0) {
            if (this.m) {
                this.a(graphics);
            }
            l = this;
        }
        l.paint(graphics);
    }
    
    public void setBounds(final int n, final int n2, final int n3, final int n4) {
        super.setBounds(n, n2, n3, n4);
    }
}
