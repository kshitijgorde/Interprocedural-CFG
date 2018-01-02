// 
// Decompiled by Procyon v0.5.30
// 

package com.easypano.tourweaver.b;

import java.util.Enumeration;
import java.awt.Image;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.Cursor;
import java.awt.Window;
import java.awt.Panel;
import java.awt.Component;
import java.awt.event.MouseEvent;
import java.awt.Rectangle;
import com.easypano.tourweaver.g;
import java.awt.Color;
import com.easypano.tourweaver.d.e;
import java.awt.Container;

public class a extends Container implements lb, e
{
    private static final long serialVersionUID = -4074649833261364756L;
    Color a;
    k b;
    bb c;
    Color d;
    int e;
    g f;
    int g;
    int h;
    int i;
    int j;
    int k;
    int l;
    Rectangle m;
    f n;
    public static int o;
    private static String[] z;
    
    public a() {
        this.a = null;
        this.b = null;
        this.c = null;
        this.d = null;
        this.e = 0;
        this.g = 0;
        this.h = 0;
        this.i = 0;
        this.j = 0;
        this.k = 0;
        this.l = 0;
        this.m = new Rectangle();
        this.enableEvents(56L);
    }
    
    public void processMouseEvent(final MouseEvent mouseEvent) {
        final boolean u = com.easypano.tourweaver.b.f.u;
        a a = this;
        if (!u) {
            if (!this.isEnabled()) {
                return;
            }
            super.processMouseEvent(mouseEvent);
            a = this;
        }
        if (a.b != null) {
            final int id = mouseEvent.getID();
            if (!u) {
                switch (id) {
                    case 501: {
                        this.requestFocus();
                        mouseEvent.getModifiers();
                        break;
                    }
                    default: {
                        return;
                    }
                }
            }
            final int n = id & 0x10;
            if (!u && n != 0) {
                this.b.setVisible(false);
                if (u) {
                    goto Label_0088;
                }
            }
            else if (n != 0) {
                this.b.a(this, mouseEvent.getX(), mouseEvent.getY());
            }
        }
    }
    
    protected Component i() {
        final boolean u = com.easypano.tourweaver.b.f.u;
        Container parent = this;
        Container container = null;
        while (!(parent instanceof Panel)) {
            container = parent;
            if (!u && !u) {
                if (container != null) {
                    final Container container2 = parent;
                    if (!u) {
                        if (!(container2 instanceof Window)) {
                            parent = parent.getParent();
                            if (!u) {
                                continue;
                            }
                        }
                    }
                }
            }
            return container;
        }
        return container;
    }
    
    protected void a(final Cursor cursor) {
        final boolean u = com.easypano.tourweaver.b.f.u;
        final f n = this.n;
        if (!u) {
            if (n == null) {
                this.setCursor(cursor);
                if (!u) {
                    return;
                }
            }
            final f n2 = this.n;
        }
        Container container = n;
        Container parent = null;
        Label_0082: {
            while (container.getParent() != null) {
                parent = container.getParent();
                if (u || u || parent instanceof Panel) {
                    break Label_0082;
                }
                final Container parent2 = container.getParent();
                if (!u) {
                    if (parent2 instanceof Window && !u) {
                        goto Label_0081;
                    }
                    container.getParent();
                }
                container = parent2;
                if (u) {
                    goto Label_0081;
                }
            }
            goto Label_0081;
        }
        parent.setCursor(cursor);
    }
    
    public void processMouseMotionEvent(final MouseEvent mouseEvent) {
        a a = this;
        if (!com.easypano.tourweaver.b.f.u) {
            if (!this.isEnabled()) {
                return;
            }
            a = this;
        }
        a.processMouseMotionEvent(mouseEvent);
    }
    
    public void processKeyEvent(final KeyEvent keyEvent) {
        a a = this;
        if (!com.easypano.tourweaver.b.f.u) {
            if (!this.isEnabled()) {
                return;
            }
            a = this;
        }
        a.processKeyEvent(keyEvent);
    }
    
    public void a(final Color a) {
        this.a = a;
    }
    
    public void b(final Color d) {
        this.d = d;
    }
    
    public Color j() {
        return this.a;
    }
    
    public void c() {
        final boolean u = com.easypano.tourweaver.b.f.u;
        final f n = this.n;
        if (!u) {
            if (n == null) {
                Graphics graphics = null;
                try {
                    this.paint(graphics = this.getGraphics());
                }
                catch (Exception ex) {}
                finally {
                    final Graphics graphics2 = graphics;
                    if (u || graphics2 != null) {
                        graphics2.dispose();
                    }
                }
                if (!u) {
                    return;
                }
            }
            final f n2 = this.n;
        }
        n.c();
    }
    
    public Container getParent() {
        Container container;
        final f f = (f)(container = this.n);
        if (!com.easypano.tourweaver.b.f.u) {
            if (f != null) {
                return this.n;
            }
            container = super.getParent();
        }
        return container;
    }
    
    public void a(final bb c) {
        this.c = c;
    }
    
    public bb a() {
        return this.c;
    }
    
    public void a(final k b) {
        this.b = b;
    }
    
    public k b() {
        return this.b;
    }
    
    protected void b(final String s) {
        final g f = this.f;
        if (!com.easypano.tourweaver.b.f.u) {
            if (f == null) {
                return;
            }
            final g f2 = this.f;
        }
        f.inputScript(s);
    }
    
    public void a(final int e) {
        this.e = e;
    }
    
    public int k() {
        return this.e;
    }
    
    public void addAttributes(final String s, final String name) {
        final boolean u = com.easypano.tourweaver.b.f.u;
        boolean b3;
        boolean equals;
        boolean b2;
        final boolean b = b2 = (equals = (b3 = s.equals(com.easypano.tourweaver.b.a.z[1])));
        if (!u) {
            if (b) {
                final Rectangle c = com.easypano.tourweaver.a.e.c(name);
                if (c != null) {
                    this.setBounds(c);
                }
                if (!u) {
                    return;
                }
            }
            final boolean b4;
            b2 = (b4 = (equals = (b3 = s.equals(com.easypano.tourweaver.b.a.z[0]))));
        }
        if (!u) {
            if (b) {
                this.setName(name);
                if (!u) {
                    return;
                }
            }
            equals = (b2 = (b3 = s.equals(com.easypano.tourweaver.b.a.z[4])));
        }
        if (!u) {
            if (b2) {
                this.a = com.easypano.tourweaver.a.e.b(name);
                if (!u) {
                    return;
                }
            }
            b3 = (equals = s.equals(com.easypano.tourweaver.b.a.z[3]));
        }
        if (!u) {
            if (equals) {
                this.d = com.easypano.tourweaver.a.e.b(name);
                if (!u) {
                    return;
                }
            }
            b3 = s.equals(com.easypano.tourweaver.b.a.z[2]);
        }
        if (b3) {
            this.e = com.easypano.tourweaver.a.e.a(name, 0);
        }
    }
    
    public void a(final Image image, final String s) {
    }
    
    public void a(final g f) {
        this.f = f;
        g g = f;
        if (!f.u) {
            if (f == null) {
                return;
            }
            g = f;
        }
        g.registerComponent(this);
    }
    
    public void a(int n, final int i, final int j, final int k, final int l) {
        final boolean u = com.easypano.tourweaver.b.f.u;
        final Rectangle bounds = this.getBounds();
        if (!u) {
            a a = null;
            Label_0415: {
                if (n != 502) {
                    int n4;
                    final int n3;
                    final int n2 = n3 = (n4 = this.g);
                    int n7;
                    final int n6;
                    final int n5 = n6 = (n7 = this.m.x);
                    final int h;
                    final int y;
                    Label_0231: {
                        Label_0215: {
                            final int n8;
                            final int n9;
                            Label_0131: {
                                if (!u) {
                                    if (n2 >= n5) {
                                        n8 = (n4 = this.g);
                                        n9 = (n7 = this.m.x + this.m.width);
                                        if (u) {
                                            break Label_0131;
                                        }
                                        if (n8 <= n9) {
                                            h = this.h;
                                            y = this.m.y;
                                            if (u) {
                                                break Label_0131;
                                            }
                                            if (h >= y) {
                                                final int h2 = this.h;
                                                final int n10 = this.m.y + this.m.height;
                                                if (u) {
                                                    break Label_0231;
                                                }
                                                if (h2 <= n10) {
                                                    break Label_0215;
                                                }
                                            }
                                        }
                                    }
                                    this.g = i;
                                    this.h = j;
                                    final int x = bounds.x;
                                }
                            }
                            if (!u) {
                                if (n2 >= n5) {
                                    final int n11 = bounds.x + bounds.width;
                                    if (!u) {
                                        if (i <= n11) {
                                            final int y2 = bounds.y;
                                            if (!u) {
                                                if (j >= y2) {
                                                    final int n12 = bounds.y + bounds.height;
                                                    if (!u) {
                                                        if (j <= n12) {
                                                            n = 504;
                                                            if (!u) {
                                                                break Label_0215;
                                                            }
                                                        }
                                                    }
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                            if (u) {
                                break Label_0231;
                            }
                            if (n8 != n9) {
                                return;
                            }
                        }
                        a = this;
                        if (u) {
                            break Label_0415;
                        }
                        n4 = this.g;
                        n7 = this.m.x;
                    }
                    if (h >= y) {
                        a = this;
                        if (u) {
                            break Label_0415;
                        }
                        if (this.g <= this.m.x + this.m.width) {
                            a = this;
                            if (u) {
                                break Label_0415;
                            }
                            if (this.h >= this.m.y) {
                                a = this;
                                if (u) {
                                    break Label_0415;
                                }
                                if (this.h <= this.m.y + this.m.height) {
                                    int n13 = i;
                                    Label_0379: {
                                        Label_0378: {
                                            if (!u) {
                                                if (i >= bounds.x) {
                                                    n13 = i;
                                                    if (u) {
                                                        break Label_0378;
                                                    }
                                                    if (i <= bounds.x + bounds.width) {
                                                        n13 = j;
                                                        if (u) {
                                                            break Label_0378;
                                                        }
                                                        if (j >= bounds.y) {
                                                            n13 = j;
                                                            if (u) {
                                                                break Label_0378;
                                                            }
                                                            if (j <= bounds.y + bounds.height) {
                                                                break Label_0379;
                                                            }
                                                        }
                                                    }
                                                }
                                                n13 = 505;
                                            }
                                        }
                                        n = n13;
                                    }
                                    this.g = i;
                                    this.h = j;
                                }
                            }
                        }
                    }
                }
                this.m.setBounds(bounds);
                this.i = i;
                this.j = j;
                this.k = k;
                a = this;
            }
            a.l = l;
        }
        final MouseEvent mouseEvent = new MouseEvent(this, n, System.currentTimeMillis(), k, i - bounds.x, j - bounds.y, l, false);
        final int id = mouseEvent.getID();
        final int n14 = 503;
        Label_0491: {
            if (!u) {
                if (id == n14) {
                    break Label_0491;
                }
                mouseEvent.getID();
            }
            if (id != n14) {
                this.processMouseEvent(mouseEvent);
                if (!u) {
                    return;
                }
            }
        }
        this.processMouseMotionEvent(mouseEvent);
    }
    
    public void a(final f n) {
        this.n = n;
    }
    
    public void a(final e e) {
    }
    
    public void b(final e e) {
    }
    
    public e a(final String s) {
        return null;
    }
    
    public Enumeration d() {
        return null;
    }
    
    public void destroy() {
    }
    
    static {
        final String[] z = new String[5];
        final int n = 0;
        final char[] charArray = "B*jl".toCharArray();
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
                            c2 = ',';
                            break;
                        }
                        case 1: {
                            c2 = 'K';
                            break;
                        }
                        case 2: {
                            c2 = '\u0007';
                            break;
                        }
                        case 3: {
                            c2 = '\t';
                            break;
                        }
                        default: {
                            c2 = '\f';
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
        final char[] charArray2 = "N$rgh_".toCharArray();
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
                            c4 = ',';
                            break;
                        }
                        case 1: {
                            c4 = 'K';
                            break;
                        }
                        case 2: {
                            c4 = '\u0007';
                            break;
                        }
                        case 3: {
                            c4 = '\t';
                            break;
                        }
                        default: {
                            c4 = '\f';
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
        final char[] charArray3 = "V\u0004umi^".toCharArray();
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
                            c6 = ',';
                            break;
                        }
                        case 1: {
                            c6 = 'K';
                            break;
                        }
                        case 2: {
                            c6 = '\u0007';
                            break;
                        }
                        case 3: {
                            c6 = '\t';
                            break;
                        }
                        default: {
                            c6 = '\f';
                            break;
                        }
                    }
                    charArray3[length3] = (char)(c5 ^ c6);
                    ++n12;
                } while (n10 == 0);
            }
            if (n10 > n12) {
                continue;
            }
            break;
        }
        z[n9] = new String(charArray3).intern();
        final int n13 = 3;
        final char[] charArray4 = "N,Df`C9".toCharArray();
        int length4;
        int n15;
        final int n14 = n15 = (length4 = charArray4.length);
        int n16 = 0;
        while (true) {
            Label_0446: {
                if (n14 > 1) {
                    break Label_0446;
                }
                length4 = (n15 = n16);
                do {
                    final char c7 = charArray4[n15];
                    char c8 = '\0';
                    switch (n16 % 5) {
                        case 0: {
                            c8 = ',';
                            break;
                        }
                        case 1: {
                            c8 = 'K';
                            break;
                        }
                        case 2: {
                            c8 = '\u0007';
                            break;
                        }
                        case 3: {
                            c8 = '\t';
                            break;
                        }
                        default: {
                            c8 = '\f';
                            break;
                        }
                    }
                    charArray4[length4] = (char)(c7 ^ c8);
                    ++n16;
                } while (n14 == 0);
            }
            if (n14 > n16) {
                continue;
            }
            break;
        }
        z[n13] = new String(charArray4).intern();
        final int n17 = 4;
        final char[] charArray5 = "N$umi^\bhec^".toCharArray();
        int length5;
        int n19;
        final int n18 = n19 = (length5 = charArray5.length);
        int n20 = 0;
        while (true) {
            Label_0562: {
                if (n18 > 1) {
                    break Label_0562;
                }
                length5 = (n19 = n20);
                do {
                    final char c9 = charArray5[n19];
                    char c10 = '\0';
                    switch (n20 % 5) {
                        case 0: {
                            c10 = ',';
                            break;
                        }
                        case 1: {
                            c10 = 'K';
                            break;
                        }
                        case 2: {
                            c10 = '\u0007';
                            break;
                        }
                        case 3: {
                            c10 = '\t';
                            break;
                        }
                        default: {
                            c10 = '\f';
                            break;
                        }
                    }
                    charArray5[length5] = (char)(c9 ^ c10);
                    ++n20;
                } while (n18 == 0);
            }
            if (n18 <= n20) {
                z[n17] = new String(charArray5).intern();
                a.z = z;
                return;
            }
            continue;
        }
    }
}
