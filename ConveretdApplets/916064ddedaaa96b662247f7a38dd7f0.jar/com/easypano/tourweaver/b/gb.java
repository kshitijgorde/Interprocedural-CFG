// 
// Decompiled by Procyon v0.5.30
// 

package com.easypano.tourweaver.b;

import java.awt.event.ActionEvent;
import java.awt.image.ImageObserver;
import java.awt.Rectangle;
import java.awt.Point;
import java.util.Enumeration;
import java.awt.event.MouseEvent;
import java.awt.event.KeyEvent;
import java.awt.Component;
import com.easypano.tourweaver.g;
import java.awt.Graphics;
import java.util.Vector;
import java.awt.Image;
import com.easypano.tourweaver.d.e;
import java.awt.event.ActionListener;
import java.awt.Panel;

public class gb extends Panel implements ActionListener, e
{
    private static final long serialVersionUID = 717781752746577769L;
    Image a;
    b b;
    Vector c;
    Image d;
    b e;
    int f;
    int g;
    Image h;
    Graphics i;
    g j;
    private static String[] z;
    
    public gb() {
        this.c = new Vector();
        this.f = 0;
        this.g = 0;
        (this.b = new b()).a((ActionListener)this);
        com.easypano.tourweaver.a.e.a(this.d);
        this.add(this.b);
        (this.e = new b()).addAttributes(gb.z[2], gb.z[1]);
        this.enableEvents(56L);
    }
    
    public void processKeyEvent(final KeyEvent keyEvent) {
        System.out.println(gb.z[0] + keyEvent);
    }
    
    public void a(final e e) {
        if (e instanceof x) {
            this.c.addElement(e);
        }
    }
    
    public void processMouseEvent(final MouseEvent mouseEvent) {
        switch (mouseEvent.getID()) {
            case 501: {
                this.f = mouseEvent.getX();
                this.g = mouseEvent.getY();
                break;
            }
        }
    }
    
    public void a() {
        final boolean u = com.easypano.tourweaver.b.f.u;
        final Enumeration<x> elements = this.c.elements();
        while (elements.hasMoreElements()) {
            elements.nextElement().destroy();
            if (u) {
                return;
            }
            if (u) {
                break;
            }
        }
        this.b.destroy();
        this.c.removeAllElements();
    }
    
    public void processMouseMotionEvent(final MouseEvent mouseEvent) {
        final int id = mouseEvent.getID();
        if (!com.easypano.tourweaver.b.f.u) {
            if (id != 506) {
                return;
            }
            mouseEvent.getX();
        }
        final int n = id;
        final int y = mouseEvent.getY();
        final Point location = this.getLocation();
        this.setLocation(location.x + (n - this.f), location.y + (y - this.g));
    }
    
    public void setBounds(final int n, final int n2, final int n3, final int n4) {
        int n5 = n3;
        final int width = this.getBounds().width;
        Label_0034: {
            Label_0029: {
                if (!com.easypano.tourweaver.b.f.u) {
                    if (n3 != width) {
                        break Label_0029;
                    }
                    n5 = n4;
                    final int height = this.getBounds().height;
                }
                if (n5 == width) {
                    break Label_0034;
                }
            }
            this.h = null;
        }
        super.setBounds(n, n2, n3, n4);
        this.b.setBounds(n3 - 20, 6, 15, 15);
    }
    
    public void b(final String s) {
        final boolean u = com.easypano.tourweaver.b.f.u;
        x x = null;
        final Enumeration<x> elements = (Enumeration<x>)this.c.elements();
        while (elements.hasMoreElements()) {
            final x x2 = elements.nextElement();
            final String name = x2.getName();
            if ((u || name != null) && name.equals(s)) {
                x = x2;
                break;
            }
        }
        final x x3 = x;
        if (!u) {
            if (x3 == null) {
                return;
            }
            this.a = x.e();
        }
        x3.a(this.b);
        int n = 0;
        int n2 = 0;
        final int width = x.getWidth();
        final int height = x.getHeight();
        final Rectangle bounds = this.getParent().getBounds();
        if (!u) {
            Label_0332: {
                switch (x.f()) {
                    case 0: {
                        if (u) {
                            break Label_0332;
                        }
                        break;
                    }
                    case 1: {
                        n = (bounds.width - width) / 2;
                        if (u) {
                            break Label_0332;
                        }
                        break;
                    }
                    case 2: {
                        n = bounds.width - width;
                        if (u) {
                            break Label_0332;
                        }
                        break;
                    }
                    case 3: {
                        n2 = (bounds.height - height) / 2;
                        if (u) {
                            break Label_0332;
                        }
                        break;
                    }
                    case 4: {
                        n = (bounds.width - width) / 2;
                        n2 = (bounds.height - height) / 2;
                        if (u) {
                            break Label_0332;
                        }
                        break;
                    }
                    case 5: {
                        n = bounds.width - width;
                        n2 = (bounds.height - height) / 2;
                        if (u) {
                            break Label_0332;
                        }
                        break;
                    }
                    case 6: {
                        n2 = bounds.height - height;
                        if (u) {
                            break Label_0332;
                        }
                        break;
                    }
                    case 7: {
                        n = (bounds.width - width) / 2;
                        n2 = bounds.height - height;
                        if (u) {
                            break Label_0332;
                        }
                        break;
                    }
                    case 8: {
                        n = bounds.width - width;
                        n2 = bounds.height - height;
                        break;
                    }
                }
            }
            this.setBounds(n, n2, width, height);
            this.setVisible(true);
        }
        this.repaint();
    }
    
    public void update(final Graphics graphics) {
        this.paint(graphics);
    }
    
    public void paint(final Graphics graphics) {
        final boolean u = com.easypano.tourweaver.b.f.u;
        gb gb = this;
        gb gb2 = this;
        gb gb3 = this;
        Label_0063: {
            if (!u) {
                if (this.h == null) {
                    gb = this;
                    gb2 = this;
                    gb3 = this;
                    if (u) {
                        break Label_0063;
                    }
                    if (this.getBounds().width != 0) {
                        this.h = this.createImage(this.getBounds().width, this.getBounds().height);
                        this.i = this.h.getGraphics();
                    }
                }
                gb = this;
                gb2 = this;
                gb3 = this;
            }
        }
        if (!u) {
            if (gb3.i == null) {
                return;
            }
            gb = this;
            gb2 = this;
        }
        if (!u) {
            if (gb2.a != null) {
                this.i.drawImage(this.a, 0, 0, this);
            }
            gb = this;
        }
        gb.paint(this.i);
        graphics.drawImage(this.h, 0, 0, this);
    }
    
    public void actionPerformed(final ActionEvent actionEvent) {
        this.setVisible(false);
    }
    
    public void addAttributes(final String s, final String s2) {
    }
    
    public void a(final Image image, final String s) {
        final boolean u = com.easypano.tourweaver.b.f.u;
        final Enumeration<x> elements = this.c.elements();
        while (elements.hasMoreElements()) {
            elements.nextElement().a(image, s);
            if (u) {
                break;
            }
        }
    }
    
    public void b(final e e) {
        final boolean b = e instanceof a;
        if (!com.easypano.tourweaver.b.f.u) {
            if (b) {
                this.remove((Component)e);
                this.c.removeElement(e);
            }
        }
    }
    
    public e a(final String s) {
        final boolean u = com.easypano.tourweaver.b.f.u;
        final Enumeration<x> elements = (Enumeration<x>)this.c.elements();
        while (elements.hasMoreElements()) {
            final x x = elements.nextElement();
            final String name = x.getName();
            Label_0062: {
                final x x2;
                if (!u) {
                    if (name == null) {
                        break Label_0062;
                    }
                    x2 = x;
                    if (u) {
                        return x2;
                    }
                    x2.getName();
                }
                if (name.equals(s)) {
                    return x2;
                }
            }
            if (u) {
                break;
            }
        }
        return null;
    }
    
    public Enumeration d() {
        return this.c.elements();
    }
    
    public void a(final g j) {
        this.j = j;
    }
    
    static {
        final String[] z = new String[3];
        final int n = 0;
        final char[] charArray = "P\u001a\u001fAY~\u0011\u0012$Fu_\"a[z\u0016\nRF~\b\u0003v\u000f".toCharArray();
        int length;
        int n3;
        final int n2 = n3 = (length = charArray.length);
        int n4 = 0;
        while (true) {
            Label_0097: {
                if (n2 > 1) {
                    break Label_0097;
                }
                length = (n3 = n4);
                do {
                    final char c = charArray[n3];
                    char c2 = '\0';
                    switch (n4 % 5) {
                        case 0: {
                            c2 = '\u001b';
                            break;
                        }
                        case 1: {
                            c2 = '\u007f';
                            break;
                        }
                        case 2: {
                            c2 = 'f';
                            break;
                        }
                        case 3: {
                            c2 = '\u0004';
                            break;
                        }
                        default: {
                            c2 = '/';
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
        final char[] charArray2 = "t\u000f\u0003jZi\u0013Nw[i\u0016\bc\u000fl\b\u0011*Jz\f\u001ftNu\u0010Hg@vSFw[i\u0016\bc\u000fD\u001d\neLpV".toCharArray();
        int length2;
        int n7;
        final int n6 = n7 = (length2 = charArray2.length);
        int n8 = 0;
        while (true) {
            Label_0213: {
                if (n6 > 1) {
                    break Label_0213;
                }
                length2 = (n7 = n8);
                do {
                    final char c3 = charArray2[n7];
                    char c4 = '\0';
                    switch (n8 % 5) {
                        case 0: {
                            c4 = '\u001b';
                            break;
                        }
                        case 1: {
                            c4 = '\u007f';
                            break;
                        }
                        case 2: {
                            c4 = 'f';
                            break;
                        }
                        case 3: {
                            c4 = '\u0004';
                            break;
                        }
                        default: {
                            c4 = '/';
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
        z[n5] = new String(charArray2).intern();
        final int n9 = 2;
        final char[] charArray3 = "z\u001c\u0012m@u".toCharArray();
        int length3;
        int n11;
        final int n10 = n11 = (length3 = charArray3.length);
        int n12 = 0;
        while (true) {
            Label_0329: {
                if (n10 > 1) {
                    break Label_0329;
                }
                length3 = (n11 = n12);
                do {
                    final char c5 = charArray3[n11];
                    char c6 = '\0';
                    switch (n12 % 5) {
                        case 0: {
                            c6 = '\u001b';
                            break;
                        }
                        case 1: {
                            c6 = '\u007f';
                            break;
                        }
                        case 2: {
                            c6 = 'f';
                            break;
                        }
                        case 3: {
                            c6 = '\u0004';
                            break;
                        }
                        default: {
                            c6 = '/';
                            break;
                        }
                    }
                    charArray3[length3] = (char)(c5 ^ c6);
                    ++n12;
                } while (n10 == 0);
            }
            if (n10 <= n12) {
                z[n9] = new String(charArray3).intern();
                gb.z = z;
                return;
            }
            continue;
        }
    }
}
