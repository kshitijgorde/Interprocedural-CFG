// 
// Decompiled by Procyon v0.5.30
// 

package com.easypano.tourweaver.d;

import java.util.Enumeration;
import com.easypano.tourweaver.CommunicationAction;
import java.awt.Image;
import com.easypano.tourweaver.a.e;
import java.awt.Toolkit;
import com.easypano.tourweaver.a.a;
import com.easypano.tourweaver.i;
import java.util.Vector;
import com.easypano.tourweaver.p;

public class g implements p
{
    c f;
    Vector g;
    b h;
    String i;
    Vector j;
    private static String z;
    
    public g() {
        this.i = "";
        this.j = new Vector();
        this.f = new c(this);
        this.g = new Vector();
    }
    
    public void a(final i i) {
        this.g.addElement(i);
    }
    
    public void a() {
        this.f.e();
        this.f = null;
        g g = this;
        if (!com.easypano.tourweaver.d.i.u) {
            if (this.h != null) {
                this.h.b();
                this.h = null;
            }
            this.g.removeAllElements();
            g = this;
        }
        g.g = null;
    }
    
    public void b(final String i) {
        this.i = i;
    }
    
    public b c() {
        return this.h;
    }
    
    public void b() {
        this.f.d();
    }
    
    public void b(final Object o, final int n) {
        this.f.c(o, n);
    }
    
    public void a(final Object o, final int n) {
        this.f.b(o, n);
    }
    
    public void a(final a a) {
        final boolean u = com.easypano.tourweaver.d.i.u;
        (this.h = new b(a, this)).a(this.i);
        try {
            int i = 0;
            while (i < this.g.size()) {
                ((i)this.g.elementAt(i)).updateConfig(this.h);
                ++i;
                if (u) {
                    return;
                }
                if (u) {
                    break;
                }
            }
        }
        catch (NullPointerException ex) {
            System.out.println(com.easypano.tourweaver.d.g.z);
            ex.printStackTrace();
            return;
        }
        this.d();
    }
    
    public void d() {
        this.h.m();
    }
    
    public void a(final byte[] array, final String s) {
        final Image image = Toolkit.getDefaultToolkit().createImage(array);
        com.easypano.tourweaver.a.e.a(image);
        this.h.k().a(image, com.easypano.tourweaver.a.e.a(s));
    }
    
    public void a(final Object o, final String s) {
        final boolean u = com.easypano.tourweaver.d.i.u;
        int i = 0;
        while (i < this.g.size()) {
            ((i)this.g.elementAt(i)).updateObject(o, s);
            ++i;
            if (u) {
                break;
            }
        }
    }
    
    public void b(final byte[] array, final String s) {
        final boolean u = com.easypano.tourweaver.d.i.u;
        final Image image = Toolkit.getDefaultToolkit().createImage(array);
        com.easypano.tourweaver.a.e.a(image);
        int i = 0;
        while (i < this.g.size()) {
            ((i)this.g.elementAt(i)).updateObject(image, s);
            ++i;
            if (u) {
                break;
            }
        }
    }
    
    public void a(final CommunicationAction communicationAction) {
        this.j.addElement(communicationAction);
    }
    
    public void e() {
        final boolean u = com.easypano.tourweaver.d.i.u;
        final Enumeration<CommunicationAction> elements = this.j.elements();
        while (elements.hasMoreElements()) {
            elements.nextElement().skinLoaded();
            if (u) {
                break;
            }
        }
    }
    
    public void a(final String s) {
        this.f.a(s);
    }
    
    public void a(final String s, final byte[] array, final int n, final int n2, final int n3, final int n4, final int n5, final int n6, final int n7, final int n8, final int n9, final int n10) {
        final boolean u = com.easypano.tourweaver.d.i.u;
        int i = 0;
        while (i < this.g.size()) {
            final i j = this.g.elementAt(i);
            int n13;
            int n12;
            final int n11 = n12 = (n13 = (n & com.easypano.tourweaver.d.i.d));
            Label_0156: {
                if (!u) {
                    if (n11 != 0) {
                        j.updateScene(array, n2, n3, n7, n8, s);
                        if (!u) {
                            break Label_0156;
                        }
                    }
                    final int n14;
                    n12 = (n14 = (n13 = (n & com.easypano.tourweaver.d.i.b)));
                }
                if (!u) {
                    if (n11 != 0) {
                        j.updateScene(n7, n8, n10, n9, s);
                        if (!u) {
                            break Label_0156;
                        }
                    }
                    n13 = (n12 = (n & com.easypano.tourweaver.d.i.c));
                }
                if (!u) {
                    if (n12 != 0) {
                        j.updateScene(array, s, n7, n8);
                        if (!u) {
                            break Label_0156;
                        }
                    }
                    n13 = (n & com.easypano.tourweaver.d.i.e);
                }
                if (n13 != 0) {
                    j.updateScene(n4, n5, n6, s);
                }
            }
            ++i;
            if (u) {
                break;
            }
        }
    }
    
    static {
        final char[] charArray = "lvk0+hv{u-x3k:,xzou$w\u007fmu*\u007f`(7'{}(1'mgz:;{w(ybm|(!*{3f .r3m-!{c|<-p3\u007f41>g`'-ivf0&0".toCharArray();
        int length;
        int n2;
        final int n = n2 = (length = charArray.length);
        int n3 = 0;
        while (true) {
            Label_0094: {
                if (n > 1) {
                    break Label_0094;
                }
                length = (n2 = n3);
                do {
                    final char c = charArray[n2];
                    char c2 = '\0';
                    switch (n3 % 5) {
                        case 0: {
                            c2 = '\u001e';
                            break;
                        }
                        case 1: {
                            c2 = '\u0013';
                            break;
                        }
                        case 2: {
                            c2 = '\b';
                            break;
                        }
                        case 3: {
                            c2 = 'U';
                            break;
                        }
                        default: {
                            c2 = 'B';
                            break;
                        }
                    }
                    charArray[length] = (char)(c ^ c2);
                    ++n3;
                } while (n == 0);
            }
            if (n <= n3) {
                g.z = new String(charArray).intern();
                return;
            }
            continue;
        }
    }
}
