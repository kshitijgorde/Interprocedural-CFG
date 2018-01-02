// 
// Decompiled by Procyon v0.5.30
// 

package com.easypano.tourweaver.b;

import java.awt.image.ImageObserver;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.Image;
import java.util.Vector;
import java.awt.Container;
import java.awt.Rectangle;
import com.easypano.tourweaver.a.e;
import com.easypano.tourweaver.PlayerListener;
import java.awt.event.ActionListener;

public class g extends f implements ActionListener, PlayerListener
{
    b v;
    o w;
    int x;
    int y;
    private static String[] z;
    
    public g() {
        this.x = 0;
        this.y = 4;
        this.v = new b();
        this.w = new o();
    }
    
    public void movieStoped(final String s) {
    }
    
    public void addAttributes(final String s, final String s2) {
        final boolean u = f.u;
        this.w.addAttributes(s, s2);
        super.addAttributes(s, s2);
        final boolean equals = s.equals(g.z[0]);
        if (!u) {
            if (equals) {
                this.x = com.easypano.tourweaver.a.e.a(s2, 0);
                if (!u) {
                    return;
                }
            }
            s.equals(g.z[1]);
        }
        if (equals) {
            this.y = com.easypano.tourweaver.a.e.a(s2, 0);
        }
    }
    
    public void setBounds(final int n, final int n2, final int n3, final int n4) {
        super.setBounds(n, n2, n3, n4);
        final b v = this.v;
        if (!f.u) {
            if (v == null) {
                return;
            }
            final b v2 = this.v;
        }
        v.setBounds(n3 - 17, 1, 16, n4 - 2);
    }
    
    public void a(final t t) {
        this.w.a(t);
    }
    
    private void h() {
        final boolean u = f.u;
        final int x = this.x;
        g g = null;
        Label_0043: {
            Container parent = null;
            Label_0038: {
                if (!u) {
                    if (x == 0) {
                        return;
                    }
                    parent = this;
                    if (u) {
                        break Label_0038;
                    }
                    final int y = this.y;
                }
                if (x == 0) {
                    return;
                }
                g = this;
                if (u) {
                    break Label_0043;
                }
                parent = this.getParent();
            }
            if (parent != null) {
                g = this;
                break Label_0043;
            }
            return;
        }
        int height = g.x * this.y + 2;
        g g2 = this;
        if (!u) {
            if (this.y > this.w.q()) {
                height = this.x * this.w.q() + 2;
            }
            g2 = this;
        }
        final Rectangle bounds = g2.getBounds();
        if (this.w.x.m().width > bounds.width) {
            height += 17;
        }
        final Rectangle bounds2 = new Rectangle();
        final Rectangle bounds3 = this.getParent().getBounds();
        bounds2.x = bounds.x;
        bounds2.y = bounds.y + bounds.height;
        bounds2.width = bounds.width;
        bounds2.height = height;
        if (!u) {
            if (bounds2.y + height > bounds3.height) {
                bounds2.y = bounds.y - height;
            }
            this.w.setBounds(bounds2);
            this.w.doLayout();
        }
    }
    
    public boolean l() {
        return this.w.n();
    }
    
    public void a(final Vector vector, final String s) {
        this.w.a(vector, s);
    }
    
    public void a(final e e) {
        if (e instanceof b) {
            (this.v = (b)e).a((ActionListener)this);
            super.a(e);
            this.setBounds(this.getBounds());
            if (!f.u) {
                return;
            }
        }
        this.w.a(e);
    }
    
    public void a(final com.easypano.tourweaver.g f) {
        super.f = f;
        this.v.a(f);
        this.w.a(f);
    }
    
    public void a(final Image image, final String s) {
        this.w.a(image, s);
        this.x = this.w.L;
        super.a(image, s);
    }
    
    public void actionPerformed(final ActionEvent actionEvent) {
        final boolean u = f.u;
        this.h();
        g g = this;
        g g2 = null;
        Label_0052: {
            Label_0051: {
                if (!u) {
                    if (this.w.getParent() != null) {
                        g2 = this;
                        if (u) {
                            break Label_0052;
                        }
                        if (this.w.isVisible()) {
                            break Label_0051;
                        }
                    }
                    g = this;
                }
                g.f.addListbox(this.w);
                if (!u) {
                    return;
                }
            }
            g2 = this;
        }
        g2.f.removeListbox();
    }
    
    public void paint(final Graphics graphics) {
        final boolean u = f.u;
        graphics.clearRect(0, 0, this.getBounds().width, this.getBounds().height);
        g g = this;
        g g2 = this;
        if (!u) {
            if (this.w.p() != null) {
                g g3 = this;
                if (!u) {
                    if (super.d != null) {
                        graphics.setColor(super.d);
                        graphics.fillRect(0, 0, this.getBounds().width, this.getBounds().height);
                    }
                    g3 = this;
                }
                final Image p = g3.w.p();
                final Rectangle rectangle = new Rectangle();
                this.w.a(rectangle);
                graphics.drawImage(p, 1, (this.getBounds().height - rectangle.height) / 2, rectangle.width + 1, (rectangle.height + this.getBounds().height) / 2, rectangle.x, rectangle.y, rectangle.x + rectangle.width, rectangle.y + rectangle.height, this);
            }
            g = this;
            g2 = this;
        }
        if (!u) {
            if (g2.a != null) {
                graphics.setColor(super.a);
                graphics.drawLine(1, 0, this.getBounds().width - 2, 0);
                graphics.drawLine(0, 1, 0, this.getBounds().height - 2);
                graphics.drawLine(this.getBounds().width - 1, 1, this.getBounds().width - 1, this.getBounds().height - 2);
                graphics.drawLine(1, this.getBounds().height - 1, this.getBounds().width - 2, this.getBounds().height - 1);
            }
            g = this;
        }
        g.paint(graphics);
    }
    
    public void sceneSwitching(final String s) {
    }
    
    public void sceneSwitched(final String s) {
        this.w.sceneSwitched(s);
        this.repaint();
    }
    
    public void movieSwitching(final String s) {
        this.w.movieSwitching(s);
        this.repaint();
    }
    
    public void mapSwitching(final String s) {
    }
    
    public void mapSwitched(final String s) {
        this.w.mapSwitched(s);
        this.repaint();
    }
    
    public void moviePaused(final String s) {
    }
    
    static {
        final String[] z = new String[2];
        final int n = 0;
        final char[] charArray = "{\"#%zw?! F".toCharArray();
        int length;
        int n3;
        final int n2 = n3 = (length = charArray.length);
        int n4 = 0;
        while (true) {
            Label_0098: {
                if (n2 > 1) {
                    break Label_0098;
                }
                length = (n3 = n4);
                do {
                    final char c = charArray[n3];
                    char c2 = '\0';
                    switch (n4 % 5) {
                        case 0: {
                            c2 = '\u0012';
                            break;
                        }
                        case 1: {
                            c2 = 'V';
                            break;
                        }
                        case 2: {
                            c2 = 'F';
                            break;
                        }
                        case 3: {
                            c2 = 'H';
                            break;
                        }
                        default: {
                            c2 = '2';
                            break;
                        }
                    }
                    charArray[length] = (char)(c ^ c2);
                    ++n4;
                } while (n2 == 0);
            }
            if (n2 > n4) {
                continue;
            }
            break;
        }
        z[n] = new String(charArray).intern();
        final int n5 = 1;
        final char[] charArray2 = "a>)?{f3+".toCharArray();
        int length2;
        int n7;
        final int n6 = n7 = (length2 = charArray2.length);
        int n8 = 0;
        while (true) {
            Label_0214: {
                if (n6 > 1) {
                    break Label_0214;
                }
                length2 = (n7 = n8);
                do {
                    final char c3 = charArray2[n7];
                    char c4 = '\0';
                    switch (n8 % 5) {
                        case 0: {
                            c4 = '\u0012';
                            break;
                        }
                        case 1: {
                            c4 = 'V';
                            break;
                        }
                        case 2: {
                            c4 = 'F';
                            break;
                        }
                        case 3: {
                            c4 = 'H';
                            break;
                        }
                        default: {
                            c4 = '2';
                            break;
                        }
                    }
                    charArray2[length2] = (char)(c3 ^ c4);
                    ++n8;
                } while (n6 == 0);
            }
            if (n6 <= n8) {
                z[n5] = new String(charArray2).intern();
                g.z = z;
                return;
            }
            continue;
        }
    }
}
