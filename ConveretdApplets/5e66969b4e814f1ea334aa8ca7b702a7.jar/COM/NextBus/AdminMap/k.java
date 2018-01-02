// 
// Decompiled by Procyon v0.5.30
// 

package COM.NextBus.AdminMap;

import java.util.Iterator;
import java.awt.image.ImageObserver;
import java.awt.Color;
import java.util.HashMap;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;
import java.awt.Point;
import java.awt.Dimension;
import java.awt.FontMetrics;
import java.awt.Font;

public final class k
{
    private static final Font a;
    private static final Font b;
    private FontMetrics c;
    private Dimension d;
    private int e;
    private int f;
    private int g;
    private int h;
    private Point i;
    private String[][] j;
    private aq[] k;
    private List l;
    private O m;
    
    public k(final O m) {
        this.c = null;
        this.d = null;
        this.e = 0;
        this.i = null;
        this.j = new String[0][];
        this.l = null;
        this.m = m;
    }
    
    private void b() {
        if (this.k != null) {
            if (this.j.length != this.k.length) {
                this.j = new String[this.k.length][];
            }
            for (int i = 0; i < this.k.length; ++i) {
                this.j[i] = this.k[i].f();
            }
            this.d = null;
        }
        this.m.a("Repainting map due to bubble window update. Time=" + System.currentTimeMillis() % 100000L);
        this.m.f.b();
    }
    
    public final synchronized void a(aq[] k, final Point i) {
        aq[] array;
        for (int length = (array = k).length, j = 0; j < length; ++j) {
            for (int l = j; l < length; ++l) {
                final aq aq = array[j];
                final aq aq2 = array[l];
                if (aq.i().compareTo(aq2.i()) > 0) {
                    array[j] = aq2;
                    array[l] = aq;
                }
            }
        }
        final aq[] array2;
        k = (array2 = array);
        boolean b = false;
        Label_0224: {
            if (this.k == null) {
                b = true;
            }
            else if (array2.length != this.l.size()) {
                b = true;
            }
            else {
                for (int n = 0; n < array2.length; ++n) {
                    final String[] array3 = this.l.get(n);
                    final String[] f = array2[n].f();
                    if (array3.length != f.length) {
                        b = true;
                        break Label_0224;
                    }
                    for (int n2 = 0; n2 < f.length; ++n2) {
                        if (!f[n2].equals(array3[n2])) {
                            b = true;
                            break Label_0224;
                        }
                    }
                }
                b = false;
            }
        }
        if (b) {
            final aq[] array4 = k;
            this.l = new ArrayList();
            aq[] array5;
            for (int length2 = (array5 = array4).length, n3 = 0; n3 < length2; ++n3) {
                this.l.add(array5[n3].f());
            }
            this.k = k;
            this.i = i;
            this.b();
        }
    }
    
    public final synchronized void a() {
        if (this.k != null) {
            this.k = null;
            this.b();
        }
    }
    
    public final synchronized void a(final Graphics graphics, final Dimension dimension) {
        if (this.k == null || this.k.length <= 0) {
            return;
        }
        graphics.setFont(COM.NextBus.AdminMap.k.a);
        if (this.c == null) {
            this.c = graphics.getFontMetrics();
            this.f = this.c.getMaxAscent();
            this.g = this.c.getHeight();
            this.h = this.c.stringWidth("x");
        }
        if (this.d == null) {
            int max = 0;
            this.e = 0;
            int n = 0;
            int i = 0;
            for (int j = 0; j < this.j.length; ++j) {
                n += this.j[j].length;
                for (int k = 0; k < this.j[j].length; ++k) {
                    final String[] a;
                    if (!(a = a(this.j[j][k]))[0].startsWith("~")) {
                        this.e = Math.max(this.e, this.c.stringWidth(a[0]));
                        if (a[1] != null) {
                            graphics.setFont(COM.NextBus.AdminMap.k.b);
                            max = Math.max(max, this.c.stringWidth(a[1]));
                            graphics.setFont(COM.NextBus.AdminMap.k.a);
                        }
                    }
                    else {
                        ++i;
                    }
                }
            }
            int n2;
            if ((n2 = this.e + max + 4) < 1) {
                n2 = 100;
            }
            while (i > 0) {
                for (int n3 = i, n4 = 0; n4 < this.j.length && n3 == i; ++n4) {
                    for (int n5 = 0; n5 < this.j[n4].length && n3 == i; ++n5) {
                        if (this.j[n4][n5].startsWith("~")) {
                            String s = this.j[n4][n5].substring("~".length()).trim();
                            final ArrayList<String> list = new ArrayList<String>();
                            while (this.c.stringWidth(s) > n2) {
                                int n6 = 0;
                                final String s2 = s;
                                for (int n7 = 0; n7 < s.length() && s2 == s; ++n7) {
                                    if (s.charAt(n7) == ' ') {
                                        n6 = n7;
                                    }
                                    if (this.c.stringWidth(s.substring(0, n7 + 1)) > n2) {
                                        if (n6 < 1) {
                                            n6 = n7 + 1;
                                        }
                                        list.add(s.substring(0, n6));
                                        s = s.substring(n6);
                                    }
                                }
                                s = s.trim();
                            }
                            list.add(s);
                            if (list.size() == 1) {
                                this.j[n4][n5] = (String)list.get(0);
                            }
                            else {
                                final int length = this.j[n4].length;
                                final int n8 = n - length;
                                final int n9 = length + (list.size() - 1);
                                n = n8 + n9;
                                final String[] array = new String[n9];
                                int n10 = 0;
                                for (int l = 0; l < n5; ++l) {
                                    array[n10++] = this.j[n4][l];
                                }
                                for (int n11 = 0; n11 < list.size(); ++n11) {
                                    array[n10++] = (String)list.get(n11);
                                }
                                for (int n12 = n5 + 1; n12 < this.j[n4].length; ++n12) {
                                    array[n10++] = this.j[n4][n12];
                                }
                                this.j[n4] = array;
                            }
                            --i;
                        }
                    }
                }
            }
            n2 += 36;
            this.d = new Dimension(n2 + (this.h << 1), this.g * n + (this.j.length + 1));
        }
        int n13 = this.i.x - this.d.width / 2;
        int n14;
        if ((n14 = this.i.y - this.d.height - 11) < this.m.P() && this.i.y << 1 < dimension.height && (n14 = this.i.y + 12) < this.m.P()) {
            n14 = this.m.P() + 12;
        }
        if (n13 + this.d.width > dimension.width - 6) {
            n13 = dimension.width - this.d.width - 6;
        }
        if (n13 < 6) {
            n13 = 6;
        }
        final HashMap<Color, Object> hashMap = new HashMap<Color, Object>();
        for (int n15 = 0; n15 < this.j.length; ++n15) {
            final Color g = this.k[n15].g();
            final Integer n16;
            Integer n17;
            if ((n16 = hashMap.get(g)) == null) {
                n17 = new Integer(1);
            }
            else {
                n17 = new Integer(n16 + 1);
            }
            hashMap.put(g, n17);
        }
        int intValue = 0;
        Color white = Color.white;
        for (final Color color : hashMap.keySet()) {
            final Integer n18;
            if ((n18 = hashMap.get(color)) > intValue) {
                intValue = n18;
                white = color;
            }
        }
        graphics.setColor(white);
        graphics.fillRect(n13, n14, this.d.width, this.d.height);
        int n19 = this.f + 1 + n14;
        for (int n20 = 0; n20 < this.j.length; ++n20) {
            for (int n21 = 0; n21 < this.j[n20].length; ++n21) {
                graphics.setColor(this.k[n20].h());
                graphics.fillRect(n13 + 1, n19 - this.f + n21 * this.g, this.d.width - 2, this.g);
            }
            if (this.k[n20].j() != null) {
                graphics.drawImage(this.k[n20].j(), n13 + this.h, n19 - 7, null);
            }
            for (int n22 = 0; n22 < this.j[n20].length; ++n22) {
                graphics.setColor(this.k[n20].g());
                final String[] a2 = a(this.j[n20][n22]);
                graphics.setFont(COM.NextBus.AdminMap.k.a);
                graphics.drawString(a2[0], n13 + this.h + 36 + this.e - this.c.stringWidth(a2[0]), n19);
                if (a2[1] != null) {
                    graphics.setFont(COM.NextBus.AdminMap.k.b);
                    graphics.drawString(a2[1], n13 + this.h + 36 + this.e, n19);
                }
                n19 += this.g;
            }
            ++n19;
        }
    }
    
    private static String[] a(final String s) {
        String substring = s;
        String substring2 = null;
        final int index;
        if ((index = s.indexOf(":")) != -1) {
            substring = s.substring(0, index + 1);
            substring2 = s.substring(index + 1);
        }
        final String[] array;
        (array = new String[2])[0] = substring;
        array[1] = substring2;
        return array;
    }
    
    static {
        a = new Font("SansSerif", 0, 12);
        b = new Font("SansSerif", 1, 12);
    }
}
