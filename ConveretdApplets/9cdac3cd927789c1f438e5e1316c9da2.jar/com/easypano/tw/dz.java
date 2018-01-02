// 
// Decompiled by Procyon v0.5.30
// 

package com.easypano.tw;

import java.awt.Image;
import java.awt.Container;
import java.awt.Component;
import com.easypano.tw.c.p;
import java.awt.image.ImageObserver;
import com.easypano.tw.c.d;
import java.awt.Toolkit;

class dz implements cg
{
    m a;
    final /* synthetic */ TWViewer b;
    
    public dz(final TWViewer b, final m a) {
        this.b = b;
        this.a = null;
        this.a = a;
    }
    
    public void a(final int n, final String s) {
        final boolean q = g.q;
        this.a.b(n);
        int n2 = n;
        final int n3 = 15;
        if (!q) {
            if (n == n3) {
                final int n4 = 0;
                final int length = bz.j.length;
                System.arraycopy(bz.j, 0, bz.i, n4, length);
                final int n5 = n4 + length;
                final int length2 = cs.waitPix2.length;
                System.arraycopy(cs.waitPix2, 0, bz.i, n5, length2);
                final int n6 = n5 + length2;
                final int length3 = cf.f.length;
                System.arraycopy(cf.f, 0, bz.i, n6, length3);
                final int n7 = n6 + length3;
                final int length4 = ck.d.length;
                System.arraycopy(ck.d, 0, bz.i, n7, length4);
                System.arraycopy(bx.d, 0, bz.i, n7 + length4, bx.d.length);
                final Image image = Toolkit.getDefaultToolkit().createImage(bz.i);
                dt.a(image);
                final d d = new d(TWViewer.a(this.b));
                d.d(image);
                d.f(31);
                Label_0300: {
                    Label_0286: {
                        if (!q) {
                            if (image == null) {
                                break Label_0286;
                            }
                            TWViewer.a(this.b).setBounds((this.b.getBounds().width - image.getWidth(TWViewer.a(this.b))) / 2, (this.b.getBounds().height - image.getHeight(TWViewer.a(this.b))) / 2, image.getWidth(TWViewer.a(this.b)), image.getHeight(TWViewer.a(this.b)));
                        }
                        if (!q) {
                            break Label_0300;
                        }
                    }
                    TWViewer.a(this.b).setBounds(0, 0, 0, 0);
                }
                TWViewer.a(this.b).a(d);
                this.b.add(TWViewer.a(this.b));
                this.b.repaint();
            }
            n2 = n;
        }
        if (n2 >= n3) {
            TWViewer.a(this.b, new cc(TWViewer.b(this.b).g.a()));
            TWViewer.c(this.b).a();
            TWViewer.d(this.b).a(TWViewer.b(this.b).h);
            final Object[] c = TWViewer.b(this.b).e.c();
            TWViewer.a(this.b, TWViewer.b(this.b).e.d());
            TWViewer.a(this.b, TWViewer.b(this.b).e.e());
            TWViewer.a(this.b, TWViewer.b(this.b).e.f());
            TWViewer.a(this.b, TWViewer.b(this.b).e.g());
            TWViewer.a(this.b, TWViewer.b(this.b).e.h());
            TWViewer.a(this.b, TWViewer.b(this.b).e.i());
            TWViewer.a(this.b, TWViewer.b(this.b).e.j());
            TWViewer.b(this.b, TWViewer.b(this.b).e.k());
            int n8 = 0;
            TWViewer b;
            while (true) {
                while (true) {
                    Label_0634: {
                        if (!q) {
                            break Label_0634;
                        }
                        final Object o;
                        Object e = o = c[n8];
                        Label_0616: {
                            if (!q) {
                                if (!(o instanceof db)) {
                                    break Label_0616;
                                }
                                e = c[n8];
                            }
                            ((db)e).a(TWViewer.c(this.b));
                        }
                        this.b.add((Component)c[n8]);
                        ++n8;
                    }
                    if (n8 < c.length) {
                        continue;
                    }
                    break;
                }
                b = this.b;
                if (!q) {
                    final Object o;
                    final Object e;
                    final db db = (db)(o = (e = TWViewer.e(b)));
                    if (q) {
                        continue;
                    }
                    if (db != null) {
                        this.b.add(TWViewer.e(this.b).h(), 0);
                    }
                    this.b.t();
                    this.b.r();
                    this.b.v();
                    this.b.validate();
                    this.b.repaint();
                    TWViewer.a(this.b).c(false);
                    this.b.remove(this.a);
                    this.b.remove(TWViewer.a(this.b));
                    final TWViewer b2 = this.b;
                }
                break;
            }
            b.f();
            int n9 = 0;
            TWViewer twViewer2 = null;
            TWViewer twViewer = null;
        Label_0828_Outer:
            while (true) {
                while (true) {
                    Label_0784: {
                        if (!q) {
                            break Label_0784;
                        }
                        final Component component = this.b.getComponent(n9);
                        component.validate();
                        ++n9;
                    }
                    if (n9 < this.b.getComponentCount()) {
                        continue;
                    }
                    break;
                }
                twViewer = (twViewer2 = this.b);
                if (!q) {
                    final Component component;
                    final bu bu = (bu)(component = TWViewer.f(twViewer));
                    if (q) {
                        continue;
                    }
                    if (bu != null) {
                        int n10 = 0;
                        TWViewer b5 = null;
                        while (true) {
                        Label_0871_Outer:
                            while (true) {
                                Label_0890: {
                                    if (!q) {
                                        break Label_0890;
                                    }
                                    final TWViewer b3 = this.b;
                                    final dv[] b4 = TWViewer.b(b5).g.a(n10).b();
                                    int n11 = 0;
                                    while (true) {
                                        while (true) {
                                            Label_0874: {
                                                if (!q) {
                                                    break Label_0874;
                                                }
                                                b4[n11].a(TWViewer.f(this.b));
                                                ++n11;
                                            }
                                            if (n11 < b4.length) {
                                                continue Label_0871_Outer;
                                            }
                                            break;
                                        }
                                        if (q) {
                                            continue;
                                        }
                                        break;
                                    }
                                    ++n10;
                                }
                                if (n10 < TWViewer.b(this.b).g.a()) {
                                    continue Label_0828_Outer;
                                }
                                break;
                            }
                            TWViewer.f(this.b).a(TWViewer.c(this.b));
                            b5 = this.b;
                            if (q) {
                                continue;
                            }
                            break;
                        }
                        TWViewer.f(b5).repaint();
                    }
                    final TWViewer b6;
                    twViewer2 = (b6 = this.b);
                }
                break;
            }
            if (!q) {
                if (TWViewer.g(twViewer) != null) {
                    TWViewer.g(this.b).repaint();
                }
                this.a = null;
                TWViewer.c(this.b, null);
                TWViewer.a(this.b, TWViewer.b(this.b).e.b());
                this.b.repaint();
                twViewer2 = this.b;
            }
            final cf f = TWViewer.b(twViewer2).f;
            Label_1169: {
                if (!q) {
                    if (f.d()) {
                        this.b.playMovie(TWViewer.b(this.b).f.b());
                        if (!q) {
                            break Label_1169;
                        }
                    }
                    final cf f2 = TWViewer.b(this.b).f;
                }
                final ce a;
                final ce ce = a = f.a(TWViewer.b(this.b).f.b());
                Label_1157: {
                    final TWViewer b7;
                    Label_1148: {
                        final int a2;
                        Label_1141: {
                            if (q || a != null) {
                                a2 = a.a();
                                if (q) {
                                    break Label_1141;
                                }
                                if (a2 > 0) {
                                    this.b.switchToScene(ce.a(0).d);
                                    if (!q) {
                                        break Label_1169;
                                    }
                                }
                            }
                            b7 = this.b;
                            if (q) {
                                break Label_1148;
                            }
                            TWViewer.b(b7).g.a();
                        }
                        if (a2 <= 0) {
                            break Label_1157;
                        }
                        final TWViewer b8 = this.b;
                    }
                    b7.switchToScene(0);
                    if (!q) {
                        break Label_1169;
                    }
                }
                System.out.println(a("\b=[?\u007f#<\u001e"));
            }
            TWViewer.c(this.b).a(this.b);
            final TWViewer b9 = this.b;
            if (!q) {
                if (TWViewer.b(b9).a.a(a("07\t\u001fu)<\u000f\u0015l#"), a("6 \u0014\u001cy4")).equals(TWViewer.h(this.b))) {
                    return;
                }
                final TWViewer b10 = this.b;
            }
            final bu f3 = TWViewer.f(b9);
            if (!q) {
                if (f3 == null) {
                    return;
                }
                TWViewer.f(this.b);
            }
            f3.destroyResource();
            TWViewer.a(this.b, (bu)null);
            this.b.removeAll();
        }
    }
    
    public void a() {
    }
    
    private static String a(final String s) {
        final char[] charArray = s.toCharArray();
        final int length = charArray.length;
        int n = 0;
        while (true) {
            Label_0089: {
                if (length > 1) {
                    break Label_0089;
                }
                char[] array2;
                char[] array = array2 = charArray;
                int n3;
                int n2 = n3 = n;
                while (true) {
                    final char c = array2[n3];
                    char c2 = '\0';
                    switch (n % 5) {
                        case 0: {
                            c2 = 'F';
                            break;
                        }
                        case 1: {
                            c2 = 'R';
                            break;
                        }
                        case 2: {
                            c2 = '{';
                            break;
                        }
                        case 3: {
                            c2 = 'l';
                            break;
                        }
                        default: {
                            c2 = '\u001c';
                            break;
                        }
                    }
                    array[n2] = (char)(c ^ c2);
                    ++n;
                    if (length != 0) {
                        break;
                    }
                    array = (array2 = charArray);
                    n2 = (n3 = length);
                }
            }
            if (n >= length) {
                return new String(charArray);
            }
            continue;
        }
    }
}
