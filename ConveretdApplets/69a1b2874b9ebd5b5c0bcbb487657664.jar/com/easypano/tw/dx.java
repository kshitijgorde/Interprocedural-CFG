// 
// Decompiled by Procyon v0.5.30
// 

package com.easypano.tw;

import java.awt.Container;
import java.awt.Component;
import com.easypano.tw.d.p;
import java.awt.image.ImageObserver;
import com.easypano.tw.d.d;
import java.awt.Image;

class dx implements cf
{
    n a;
    final /* synthetic */ TWViewer b;
    
    public dx(final TWViewer b, final n a) {
        this.b = b;
        this.a = null;
        this.a = a;
    }
    
    public void a(final int n, final String s) {
        final boolean q = h.q;
        this.a.b(n);
        int n2 = n;
        final int n3 = 15;
        if (!q) {
            if (n == n3) {
                final Image image = (Image)TWViewer.a(this.b).d.a(this.b.a(a("\f.J|\u001c\b$Jf[\u0011\"J\u007fU"), ""), 1);
                final d d = new d(TWViewer.b(this.b));
                d.d(image);
                d.f(31);
                Label_0193: {
                    Label_0179: {
                        if (!q) {
                            if (image == null) {
                                break Label_0179;
                            }
                            TWViewer.b(this.b).setBounds((this.b.getBounds().width - image.getWidth(TWViewer.b(this.b))) / 2, (this.b.getBounds().height - image.getHeight(TWViewer.b(this.b))) / 2, image.getWidth(TWViewer.b(this.b)), image.getHeight(TWViewer.b(this.b)));
                        }
                        if (!q) {
                            break Label_0193;
                        }
                    }
                    TWViewer.b(this.b).setBounds(0, 0, 0, 0);
                }
                TWViewer.b(this.b).a(d);
                this.b.add(TWViewer.b(this.b));
                this.b.repaint();
            }
            n2 = n;
        }
        if (n2 >= n3) {
            TWViewer.a(this.b, new cb(TWViewer.a(this.b).g.a()));
            TWViewer.c(this.b).a();
            TWViewer.d(this.b).a(TWViewer.a(this.b).h);
            final Object[] c = TWViewer.a(this.b).e.c();
            TWViewer.a(this.b, TWViewer.a(this.b).e.d());
            TWViewer.a(this.b, TWViewer.a(this.b).e.e());
            TWViewer.a(this.b, TWViewer.a(this.b).e.f());
            TWViewer.a(this.b, TWViewer.a(this.b).e.g());
            TWViewer.a(this.b, TWViewer.a(this.b).e.h());
            TWViewer.a(this.b, TWViewer.a(this.b).e.i());
            TWViewer.a(this.b, TWViewer.a(this.b).e.j());
            TWViewer.b(this.b, TWViewer.a(this.b).e.k());
            int n4 = c.length - 1;
            TWViewer b;
            while (true) {
                while (true) {
                    Label_0530: {
                        if (!q) {
                            break Label_0530;
                        }
                        final Object o;
                        Object e = o = c[n4];
                        Label_0511: {
                            if (!q) {
                                if (!(o instanceof db)) {
                                    break Label_0511;
                                }
                                e = c[n4];
                            }
                            ((db)e).a(TWViewer.c(this.b));
                        }
                        this.b.add((Component)c[n4], 0);
                        --n4;
                    }
                    if (n4 >= 0) {
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
                    this.b.q();
                    this.b.p();
                    this.b.r();
                    this.b.validate();
                    this.b.repaint();
                    TWViewer.b(this.b).c(false);
                    this.b.remove(this.a);
                    this.b.remove(TWViewer.b(this.b));
                    final TWViewer b2 = this.b;
                }
                break;
            }
            b.f();
            int n5 = 0;
            TWViewer twViewer2 = null;
            TWViewer twViewer = null;
        Label_0722_Outer:
            while (true) {
                while (true) {
                    Label_0678: {
                        if (!q) {
                            break Label_0678;
                        }
                        final Component component = this.b.getComponent(n5);
                        component.validate();
                        ++n5;
                    }
                    if (n5 < this.b.getComponentCount()) {
                        continue;
                    }
                    break;
                }
                twViewer = (twViewer2 = this.b);
                if (!q) {
                    final Component component;
                    final bt bt = (bt)(component = TWViewer.f(twViewer));
                    if (q) {
                        continue;
                    }
                    if (bt != null) {
                        int n6 = 0;
                        TWViewer b5 = null;
                        while (true) {
                        Label_0765_Outer:
                            while (true) {
                                Label_0784: {
                                    if (!q) {
                                        break Label_0784;
                                    }
                                    final TWViewer b3 = this.b;
                                    final dv[] b4 = TWViewer.a(b5).g.a(n6).b();
                                    int n7 = 0;
                                    while (true) {
                                        while (true) {
                                            Label_0768: {
                                                if (!q) {
                                                    break Label_0768;
                                                }
                                                b4[n7].a(TWViewer.f(this.b));
                                                ++n7;
                                            }
                                            if (n7 < b4.length) {
                                                continue Label_0765_Outer;
                                            }
                                            break;
                                        }
                                        if (q) {
                                            continue;
                                        }
                                        break;
                                    }
                                    ++n6;
                                }
                                if (n6 < TWViewer.a(this.b).g.a()) {
                                    continue Label_0722_Outer;
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
                TWViewer.a(this.b, TWViewer.a(this.b).e.b());
                this.b.repaint();
                twViewer2 = this.b;
            }
            final ce f = TWViewer.a(twViewer2).f;
            Label_1063: {
                if (!q) {
                    if (f.d()) {
                        this.b.playMovie(TWViewer.a(this.b).f.b());
                        if (!q) {
                            break Label_1063;
                        }
                    }
                    final ce f2 = TWViewer.a(this.b).f;
                }
                final cd a;
                final cd cd = a = f.a(TWViewer.a(this.b).f.b());
                Label_1051: {
                    final TWViewer b7;
                    Label_1042: {
                        final int a2;
                        Label_1035: {
                            if (q || a != null) {
                                a2 = a.a();
                                if (q) {
                                    break Label_1035;
                                }
                                if (a2 > 0) {
                                    this.b.switchToScene(cd.a(0).d);
                                    if (!q) {
                                        break Label_1063;
                                    }
                                }
                            }
                            b7 = this.b;
                            if (q) {
                                break Label_1042;
                            }
                            TWViewer.a(b7).g.a();
                        }
                        if (a2 <= 0) {
                            break Label_1051;
                        }
                        final TWViewer b8 = this.b;
                    }
                    b7.switchToScene(0);
                    if (!q) {
                        break Label_1063;
                    }
                }
                System.out.println(a("1*\u0003AQ\u001a+F"));
            }
            TWViewer.c(this.b).a(this.b);
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
                            c2 = '\u007f';
                            break;
                        }
                        case 1: {
                            c2 = 'E';
                            break;
                        }
                        case 2: {
                            c2 = '#';
                            break;
                        }
                        case 3: {
                            c2 = '\u0012';
                            break;
                        }
                        default: {
                            c2 = '2';
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
