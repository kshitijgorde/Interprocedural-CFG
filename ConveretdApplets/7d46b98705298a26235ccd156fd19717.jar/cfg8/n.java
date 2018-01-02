// 
// Decompiled by Procyon v0.5.30
// 

package cfg8;

import java.awt.image.ImageObserver;
import java.awt.Component;
import java.awt.Rectangle;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Color;

class n
{
    final g a;
    private w b;
    private Color c;
    private Image d;
    private String e;
    private String f;
    private String g;
    private p h;
    private static String[] z;
    
    n(final g a, final w b, final p h) {
        this.a = a;
        this.b = null;
        this.c = null;
        this.d = null;
        this.e = "";
        this.f = "";
        this.g = "";
        this.h = null;
        this.h = h;
        this.b = b;
        this.c = t.b(b.d(n.z[5]));
        this.e = b.d(n.z[4]);
        this.g = b.d(n.z[2]);
        this.f = b.d(n.z[3]);
    }
    
    protected void a(final Graphics graphics, final Rectangle rectangle, final int n, final int n2, final boolean b, final Component component) {
        final int a = RotationImageFilter.a;
        int x = b ? 1 : 0;
        final int n3 = 1;
        if (a == 0) {
            if ((b ? 1 : 0) == n3) {
                graphics.setColor(new Color(150, 220, 250));
                graphics.fillRect(rectangle.x + 1, rectangle.y + 1, rectangle.width - 2, rectangle.height - 1);
            }
            x = rectangle.x;
        }
        int n4 = x + n3;
        final int n5 = rectangle.y + rectangle.height / 2 + cfg8.g.j(this.a) / 2;
        final Image d = this.d;
        final Image image = null;
        Label_0364: {
            n n6 = null;
            Label_0295: {
                if (a == 0) {
                    Label_0223: {
                        if (d == image) {
                            final String d2 = this.b.d(n.z[1]);
                            if (d2.length() > 0) {
                                final Image a2 = cfg8.g.i(this.a).a(String.valueOf(cfg8.g.h(this.a)).concat(String.valueOf(d2)));
                                Label_0213: {
                                    Label_0207: {
                                        if (a == 0) {
                                            if (a2.getWidth(component) == n && a2.getHeight(component) == n2) {
                                                break Label_0207;
                                            }
                                            this.d = a2.getScaledInstance(n, n2, 2);
                                        }
                                        if (a == 0) {
                                            break Label_0213;
                                        }
                                    }
                                    this.d = a2;
                                }
                                if (a == 0) {
                                    break Label_0223;
                                }
                            }
                            this.d = null;
                        }
                    }
                    n6 = this;
                    if (a != 0) {
                        break Label_0295;
                    }
                    final Image d3 = this.d;
                }
                Label_0294: {
                    if (d != image) {
                        int n7 = n;
                        int n8 = n;
                        int n10;
                        final int n9 = n10 = 0;
                        if (a == 0) {
                            if (n <= n9) {
                                break Label_0294;
                            }
                            n7 = n2;
                            n8 = n2;
                            final int n11;
                            n10 = (n11 = 0);
                        }
                        if (a == 0) {
                            if (n8 <= n9) {
                                break Label_0294;
                            }
                            graphics.drawImage(this.d, n4 + 1, rectangle.y + 2, Color.white, component);
                            n7 = n4;
                            n10 = n + 2;
                        }
                        n4 = n7 + n10;
                        if (a == 0) {
                            break Label_0364;
                        }
                    }
                }
                n6 = this;
            }
            if (n6.c != null) {
                graphics.setColor(this.c);
                graphics.fillRect(n4 + 1, rectangle.y + 2, 20, rectangle.height - 3);
                graphics.setColor(Color.black);
                graphics.drawRect(n4 + 1, rectangle.y + 2, 20, rectangle.height - 3);
                n4 += 24;
            }
        }
        graphics.setColor(Color.black);
        graphics.drawString(this.b(), n4, n5 - 3);
    }
    
    protected Color a() {
        return this.c;
    }
    
    protected String b() {
        return this.a.s(this.b.b());
    }
    
    protected String c() {
        return this.e;
    }
    
    protected String d() {
        return this.b.d(n.z[0]);
    }
    
    protected String e() {
        return this.g;
    }
    
    protected boolean a(final String s) {
        final int a = RotationImageFilter.a;
        p p2;
        final p p = p2 = this.h;
        String b = null;
        Label_0047: {
            Label_0045: {
                if (a == 0) {
                    if (p == null) {
                        break Label_0045;
                    }
                    final p h;
                    p2 = (h = this.h);
                }
                if (a == 0) {
                    if (p.a()) {
                        return true;
                    }
                    p2 = this.h;
                }
                b = p2.b();
                if (a == 0) {
                    break Label_0047;
                }
            }
            b = s;
        }
        String f;
        final String s2 = f = this.f;
        if (a == 0) {
            if (s2.length() > 0) {
                final boolean o = this.a.o(this.f);
                if (a != 0 || o) {
                    return o;
                }
            }
            final String s3;
            f = (s3 = b);
        }
        final boolean a2;
        if (a == 0) {
            if (s2 == null) {
                return a2;
            }
            f = b;
        }
        final int length;
        final int n = length = f.length();
        if (a == 0) {
            if (n > 0) {
                a2 = this.a.a(this.b, b, true);
                if (a == 0) {
                    if (!a2) {
                        return true;
                    }
                }
            }
        }
        return a2;
    }
    
    static {
        final String[] z = new String[6];
        final int n = 0;
        final char[] charArray = "i\u0015}C".toCharArray();
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
                            c2 = '\u000f';
                            break;
                        }
                        case 1: {
                            c2 = 'y';
                            break;
                        }
                        case 2: {
                            c2 = '\u001c';
                            break;
                        }
                        case 3: {
                            c2 = '$';
                            break;
                        }
                        default: {
                            c2 = '\u0013';
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
        final char[] charArray2 = "f\u0014{".toCharArray();
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
                            c4 = '\u000f';
                            break;
                        }
                        case 1: {
                            c4 = 'y';
                            break;
                        }
                        case 2: {
                            c4 = '\u001c';
                            break;
                        }
                        case 3: {
                            c4 = '$';
                            break;
                        }
                        default: {
                            c4 = '\u0013';
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
        final char[] charArray3 = "}\u001erM~h".toCharArray();
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
                            c6 = '\u000f';
                            break;
                        }
                        case 1: {
                            c6 = 'y';
                            break;
                        }
                        case 2: {
                            c6 = '\u001c';
                            break;
                        }
                        case 3: {
                            c6 = '$';
                            break;
                        }
                        default: {
                            c6 = '\u0013';
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
        final char[] charArray4 = "i\u0010pPv}".toCharArray();
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
                            c8 = '\u000f';
                            break;
                        }
                        case 1: {
                            c8 = 'y';
                            break;
                        }
                        case 2: {
                            c8 = '\u001c';
                            break;
                        }
                        case 3: {
                            c8 = '$';
                            break;
                        }
                        default: {
                            c8 = '\u0013';
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
        final char[] charArray5 = "k\u0018hE".toCharArray();
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
                            c10 = '\u000f';
                            break;
                        }
                        case 1: {
                            c10 = 'y';
                            break;
                        }
                        case 2: {
                            c10 = '\u001c';
                            break;
                        }
                        case 3: {
                            c10 = '$';
                            break;
                        }
                        default: {
                            c10 = '\u0013';
                            break;
                        }
                    }
                    charArray5[length5] = (char)(c9 ^ c10);
                    ++n20;
                } while (n18 == 0);
            }
            if (n18 > n20) {
                continue;
            }
            break;
        }
        z[n17] = new String(charArray5).intern();
        final int n21 = 5;
        final char[] charArray6 = "}\u001e~".toCharArray();
        int length6;
        int n23;
        final int n22 = n23 = (length6 = charArray6.length);
        int n24 = 0;
        while (true) {
            Label_0678: {
                if (n22 > 1) {
                    break Label_0678;
                }
                length6 = (n23 = n24);
                do {
                    final char c11 = charArray6[n23];
                    char c12 = '\0';
                    switch (n24 % 5) {
                        case 0: {
                            c12 = '\u000f';
                            break;
                        }
                        case 1: {
                            c12 = 'y';
                            break;
                        }
                        case 2: {
                            c12 = '\u001c';
                            break;
                        }
                        case 3: {
                            c12 = '$';
                            break;
                        }
                        default: {
                            c12 = '\u0013';
                            break;
                        }
                    }
                    charArray6[length6] = (char)(c11 ^ c12);
                    ++n24;
                } while (n22 == 0);
            }
            if (n22 <= n24) {
                z[n21] = new String(charArray6).intern();
                cfg8.n.z = z;
                return;
            }
            continue;
        }
    }
}
