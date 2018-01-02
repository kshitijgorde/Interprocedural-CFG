// 
// Decompiled by Procyon v0.5.30
// 

package com.easypano.tourweaver.b;

import java.awt.Container;
import java.awt.Cursor;
import java.awt.Shape;
import java.awt.event.MouseEvent;
import java.awt.Rectangle;
import java.awt.image.ImageObserver;
import java.awt.Graphics;
import com.easypano.tourweaver.f.e;
import java.util.Enumeration;
import java.awt.Image;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseListener;

public class v extends s implements MouseListener, MouseMotionListener
{
    Image y;
    Image z;
    d A;
    com.easypano.tourweaver.f.d B;
    boolean C;
    boolean D;
    Image E;
    int F;
    int G;
    boolean H;
    private static String[] I;
    
    public v() {
        this.y = null;
        this.z = null;
        this.A = null;
        this.D = false;
        this.F = 0;
        this.G = 0;
        this.H = false;
        this.addMouseListener(this);
        this.addMouseMotionListener(this);
    }
    
    public void a(final boolean c) {
        this.C = c;
    }
    
    public void destroy() {
        super.destroy();
        this.y = null;
        this.z = null;
    }
    
    public void c(final String s) {
        final boolean u = f.u;
        final Enumeration<c> elements = (Enumeration<c>)super.p.elements();
        while (elements.hasMoreElements()) {
            final c nextElement = elements.nextElement();
            Label_0097: {
                if (u || nextElement instanceof c) {
                    final c c = nextElement;
                    final boolean equals = s.equals(c.m());
                    final c c2;
                    Label_0093: {
                        if (!u) {
                            if (!equals) {
                                break Label_0097;
                            }
                            c2 = c;
                            if (u) {
                                break Label_0093;
                            }
                            c2.isVisible();
                        }
                        if (equals) {
                            c.setVisible(false);
                            if (!u) {
                                break Label_0097;
                            }
                        }
                    }
                    c2.setVisible(true);
                }
            }
            if (u) {
                break;
            }
        }
    }
    
    public void a(final double n, final double n2) {
        final boolean u = f.u;
        final Enumeration<com.easypano.tourweaver.f.e> elements = (Enumeration<com.easypano.tourweaver.f.e>)super.p.elements();
        while (elements.hasMoreElements()) {
            final com.easypano.tourweaver.f.e nextElement = elements.nextElement();
            if (u || nextElement instanceof com.easypano.tourweaver.f.e) {
                nextElement.a(n, n2);
            }
            if (u) {
                break;
            }
        }
    }
    
    public void a(final com.easypano.tourweaver.f.d b) {
        this.B = b;
    }
    
    public void a(final Image z) {
        this.z = z;
    }
    
    public void b(final Image image) {
        final boolean u = f.u;
        v v = this;
        if (!u) {
            if (this.D) {
                this.E = image;
                if (!u) {
                    return;
                }
            }
            v = this;
        }
        v.y = image;
    }
    
    public void a(final d a) {
        this.A = a;
    }
    
    public void d(final String s) {
        final boolean u = f.u;
        this.A = null;
        final Enumeration elements = super.p.elements();
        while (elements.hasMoreElements()) {
            final com.easypano.tourweaver.f.e nextElement = elements.nextElement();
            Label_0133: {
                if (u || nextElement instanceof com.easypano.tourweaver.f.e) {
                    final com.easypano.tourweaver.f.e e = nextElement;
                    final d d = (d)e;
                    final String f = e.f();
                    Label_0127: {
                        if (u || f != null) {
                            final boolean equals = f.equals(s);
                            Label_0122: {
                                if (!u) {
                                    if (!equals) {
                                        break Label_0127;
                                    }
                                    d.b(true);
                                    if (u) {
                                        break Label_0122;
                                    }
                                    e.e();
                                }
                                if (!equals) {
                                    break Label_0133;
                                }
                                this.A = (d)e;
                            }
                            if (!u) {
                                break Label_0133;
                            }
                        }
                    }
                    d.b(false);
                }
            }
            if (u) {
                break;
            }
        }
    }
    
    public void b(final boolean d) {
        this.D = d;
        this.z = null;
    }
    
    public void paint(final Graphics graphics) {
        final boolean u = f.u;
        System.currentTimeMillis();
        final Rectangle bounds = this.getBounds();
        v v = this;
        v v2 = this;
        if (!u) {
            if (this.D) {
                if (this.E == null) {
                    return;
                }
                graphics.drawImage(this.E, -bounds.x, -bounds.y, this);
                if (!u) {
                    return;
                }
            }
            v = this;
            v2 = this;
        }
        if (!u) {
            if (v2.d != null) {
                graphics.setColor(super.d);
                graphics.fillRect(0, 0, bounds.width, bounds.height);
            }
            v = this;
        }
        final Image z = v.z;
        v v3 = null;
        Label_0297: {
            if (!u) {
                if (z != null) {
                    graphics.drawImage(this.z, 0, 0, bounds.width, bounds.height, this);
                }
                v3 = this;
                if (u) {
                    break Label_0297;
                }
                final Image y = this.y;
            }
            if (z != null) {
                v3 = this;
                if (u) {
                    break Label_0297;
                }
                if (this.A != null) {
                    final Rectangle s = this.A.s();
                    graphics.drawImage(this.y, s.x, s.y, s.width, s.height, this);
                    v3 = this;
                    if (u) {
                        break Label_0297;
                    }
                    if (this.A != null) {
                        v3 = this;
                        if (u) {
                            break Label_0297;
                        }
                        if (this.A.a != null) {
                            graphics.setColor(this.A.a);
                            graphics.drawLine(this.A.p(), this.A.q(), this.A.Kb, this.A.Lb);
                            graphics.drawLine(this.A.p(), this.A.q(), this.A.Mb, this.A.Nb);
                        }
                    }
                }
            }
            v3 = this;
        }
        v3.paint(graphics);
    }
    
    public void mouseEntered(final MouseEvent mouseEvent) {
    }
    
    public void mouseClicked(final MouseEvent mouseEvent) {
        final boolean u = f.u;
        final int clickCount = mouseEvent.getClickCount();
        v v = null;
        Label_0041: {
            if (!u) {
                if (clickCount != 2) {
                    return;
                }
                v = this;
                if (u) {
                    break Label_0041;
                }
                final boolean c = this.C;
            }
            if (clickCount == 0) {
                this.b(com.easypano.tourweaver.b.v.I[1]);
                if (!u) {
                    return;
                }
            }
            v = this;
        }
        v.b(com.easypano.tourweaver.b.v.I[0]);
    }
    
    public void mouseExited(final MouseEvent mouseEvent) {
    }
    
    private boolean a(final int n, final int n2, final Shape shape) {
        final boolean u = f.u;
        boolean b2;
        final boolean b = b2 = (shape instanceof Rectangle);
        if (!u) {
            if (b) {
                return ((Rectangle)shape).contains(n, n2);
            }
            final boolean b3;
            b2 = (b3 = (shape instanceof kb));
        }
        if (!u) {
            if (b) {
                return ((kb)shape).contains(n, n2);
            }
            b2 = false;
        }
        return b2;
    }
    
    public void mousePressed(final MouseEvent mouseEvent) {
        final boolean u = f.u;
        this.F = mouseEvent.getX();
        this.G = mouseEvent.getY();
        this.a(Cursor.getPredefinedCursor(13));
        if (this.A != null) {
            int x = mouseEvent.getX();
            int y = mouseEvent.getY();
            final Shape h = this.A.h();
            v v = this;
            int h2;
            final int n = h2 = x;
            if (!u) {
                if (!this.a(n, y, h)) {
                    this.H = true;
                    this.b(com.easypano.tourweaver.b.v.I[2]);
                    return;
                }
                v = this;
                h2 = 0;
            }
            v.H = (h2 != 0);
            this.a(Cursor.getDefaultCursor());
            final Rectangle s = this.A.s();
            final int n2 = x;
            final int n3 = s.width + s.x;
            int n5 = 0;
            int l = 0;
            Label_0179: {
                if (!u) {
                    if (n2 > n3) {
                        x = s.width + s.x;
                    }
                    final int n4;
                    l = (n4 = (n5 = y));
                    if (u) {
                        break Label_0179;
                    }
                    final int n6 = s.height + s.y;
                }
                if (n2 > n3) {
                    y = s.height + s.y;
                }
                n5 = (l = (this.B.l() ? 1 : 0));
            }
            if (!u) {
                if (l != 0) {
                    return;
                }
                x -= this.A.p();
                n5 = this.A.q() - y;
            }
            this.B.b(Math.atan2(n5, x));
            this.B.d(this.A.n());
            this.B.e();
        }
    }
    
    public void mouseReleased(final MouseEvent mouseEvent) {
        this.a(Cursor.getDefaultCursor());
    }
    
    private void c(final int n, final int n2) {
        final Container parent = this.getParent();
        final boolean b = parent instanceof q;
        if (!f.u) {
            if (b) {
                ((q)parent).moveXYZ(n, n2, 0);
            }
        }
    }
    
    public void mouseDragged(final MouseEvent mouseEvent) {
        final boolean u = f.u;
        int x = mouseEvent.getX();
        int y = mouseEvent.getY();
        final int n = x - this.F;
        final int n2 = y - this.G;
        final d a = this.A;
        if (!u) {
            if (a == null) {
                this.c(n, n2);
                if (!u) {
                    return;
                }
            }
            final d a2 = this.A;
        }
        final Shape h = a.h();
        v v = this;
        if (!u) {
            if (!this.a(x, y, h)) {
                v v2 = this;
                if (!u) {
                    if (!this.H) {
                        return;
                    }
                    v2 = this;
                }
                v2.c(n, n2);
                return;
            }
            v = this;
        }
        final Rectangle s = v.A.s();
        final int n3 = x;
        final int n4 = s.width + s.x;
        int n6 = 0;
        int l = 0;
        Label_0182: {
            if (!u) {
                if (n3 > n4) {
                    x = s.width + s.x;
                }
                final int n5;
                l = (n5 = (n6 = y));
                if (u) {
                    break Label_0182;
                }
                final int n7 = s.height + s.y;
            }
            if (n3 > n4) {
                y = s.height + s.y;
            }
            n6 = (l = (this.B.l() ? 1 : 0));
        }
        if (!u) {
            if (l != 0) {
                return;
            }
            x -= this.A.p();
            n6 = this.A.q() - y;
        }
        this.B.b(Math.atan2(n6, x));
        this.B.e();
    }
    
    public void mouseMoved(final MouseEvent mouseEvent) {
    }
    
    public boolean imageUpdate(final Image image, final int n, final int n2, final int n3, final int n4, final int n5) {
        int n7;
        final int n6 = n7 = (n & 0x20);
        if (!f.u) {
            if (n6 != 32) {
                n7 = (true ? 1 : 0);
            }
            else {
                n7 = (false ? 1 : 0);
            }
        }
        return n7 != 0;
    }
    
    static {
        final String[] i = new String[3];
        final int n = 0;
        final char[] charArray = "[\u0017 `UK\u0003%gPL\n,z\u001bM\u001b;}]YO$uCH\u0006,cVLF".toCharArray();
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
                            c2 = '>';
                            break;
                        }
                        case 1: {
                            c2 = 'o';
                            break;
                        }
                        case 2: {
                            c2 = 'I';
                            break;
                        }
                        case 3: {
                            c2 = '\u0014';
                            break;
                        }
                        default: {
                            c2 = '3';
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
        i[n] = new String(charArray).intern();
        final int n5 = 1;
        final char[] charArray2 = "X\u001a%x@]\u001d,q]\u0016\u001c=fZP\biyRN\u0019 qD[\u001d`".toCharArray();
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
                            c4 = '>';
                            break;
                        }
                        case 1: {
                            c4 = 'o';
                            break;
                        }
                        case 2: {
                            c4 = 'I';
                            break;
                        }
                        case 3: {
                            c4 = '\u0014';
                            break;
                        }
                        default: {
                            c4 = '3';
                            break;
                        }
                    }
                    charArray2[length2] = (char)(c3 ^ c4);
                    ++n8;
                } while (n6 == 0);
            }
            if (n6 > n8) {
                continue;
            }
            break;
        }
        i[n5] = new String(charArray2).intern();
        final int n9 = 2;
        final char[] charArray3 = "M\u001b&d\u001b\u0017".toCharArray();
        int length3;
        int n11;
        final int n10 = n11 = (length3 = charArray3.length);
        int n12 = 0;
        while (true) {
            Label_0330: {
                if (n10 > 1) {
                    break Label_0330;
                }
                length3 = (n11 = n12);
                do {
                    final char c5 = charArray3[n11];
                    char c6 = '\0';
                    switch (n12 % 5) {
                        case 0: {
                            c6 = '>';
                            break;
                        }
                        case 1: {
                            c6 = 'o';
                            break;
                        }
                        case 2: {
                            c6 = 'I';
                            break;
                        }
                        case 3: {
                            c6 = '\u0014';
                            break;
                        }
                        default: {
                            c6 = '3';
                            break;
                        }
                    }
                    charArray3[length3] = (char)(c5 ^ c6);
                    ++n12;
                } while (n10 == 0);
            }
            if (n10 <= n12) {
                i[n9] = new String(charArray3).intern();
                v.I = i;
                return;
            }
            continue;
        }
    }
}
