// 
// Decompiled by Procyon v0.5.30
// 

package com.easypano.tourweaver.d;

import java.util.Enumeration;
import java.awt.Image;
import java.util.Vector;

class f implements e
{
    Vector a;
    boolean b;
    public boolean c;
    public String d;
    boolean e;
    String f;
    private static String[] z;
    
    f() {
        this.a = new Vector();
        this.b = false;
        this.c = false;
        this.d = null;
        this.e = true;
        this.f = null;
    }
    
    public void addAttributes(final String s, final String s2) {
        final boolean u = i.u;
        boolean b3;
        boolean equals;
        boolean b2;
        final boolean b = b2 = (equals = (b3 = s.equals(com.easypano.tourweaver.d.f.z[5])));
        if (!u) {
            if (b) {
                this.b = com.easypano.tourweaver.a.e.e(s2);
                if (!u) {
                    return;
                }
            }
            final boolean b4;
            b2 = (b4 = (equals = (b3 = s.equals(com.easypano.tourweaver.d.f.z[2]))));
        }
        if (!u) {
            if (b) {
                this.c = com.easypano.tourweaver.a.e.e(s2);
                if (!u) {
                    return;
                }
            }
            equals = (b2 = (b3 = s.equals(com.easypano.tourweaver.d.f.z[1])));
        }
        if (!u) {
            if (b2) {
                this.d = s2;
                this.d = this.d.replace('\\', '/');
                if (!u) {
                    return;
                }
            }
            b3 = (equals = s.equals(com.easypano.tourweaver.d.f.z[3]));
        }
        if (!u) {
            if (equals) {
                this.e = com.easypano.tourweaver.a.e.e(s2);
                if (!u) {
                    return;
                }
            }
            b3 = s.equals(com.easypano.tourweaver.d.f.z[4]);
        }
        if (b3) {
            this.f = s2;
        }
    }
    
    public boolean a() {
        return this.e;
    }
    
    public String b() {
        return this.f;
    }
    
    public boolean c() {
        return this.b;
    }
    
    public void a(final Image image, final String s) {
        final boolean u = i.u;
        final Enumeration d = this.d();
        while (d.hasMoreElements()) {
            d.nextElement().a(image, s);
            if (u) {
                break;
            }
        }
    }
    
    public void a(final e e) {
        Vector vector2;
        final Vector vector = vector2 = this.a;
        e e2 = e;
        if (!i.u) {
            if (vector.contains(e)) {
                return;
            }
            vector2 = this.a;
            e2 = e;
        }
        vector2.addElement(e2);
    }
    
    public void b(final e e) {
        this.a.remove(e);
    }
    
    public e a(final String s) {
        return null;
    }
    
    public void setName(final String s) {
    }
    
    public String getName() {
        return new String(com.easypano.tourweaver.d.f.z[0]);
    }
    
    public Enumeration d() {
        return this.a.elements();
    }
    
    static {
        final String[] z = new String[6];
        final int n = 0;
        final char[] charArray = "\u0017aVy".toCharArray();
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
                            c2 = 'e';
                            break;
                        }
                        case 1: {
                            c2 = '\u000e';
                            break;
                        }
                        case 2: {
                            c2 = '9';
                            break;
                        }
                        case 3: {
                            c2 = '\r';
                            break;
                        }
                        default: {
                            c2 = 'l';
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
        final char[] charArray2 = "\u0015|Vg\t\u0006zil\u0018\r".toCharArray();
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
                            c4 = 'e';
                            break;
                        }
                        case 1: {
                            c4 = '\u000e';
                            break;
                        }
                        case 2: {
                            c4 = '9';
                            break;
                        }
                        case 3: {
                            c4 = '\r';
                            break;
                        }
                        default: {
                            c4 = 'l';
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
        final char[] charArray3 = "\f}i\u007f\t\u0013g\\z".toCharArray();
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
                            c6 = 'e';
                            break;
                        }
                        case 1: {
                            c6 = '\u000e';
                            break;
                        }
                        case 2: {
                            c6 = '9';
                            break;
                        }
                        case 3: {
                            c6 = '\r';
                            break;
                        }
                        default: {
                            c6 = 'l';
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
        final char[] charArray4 = "\f}|c\r\u0007b\\i*\u0010bU^\u000f\u0017".toCharArray();
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
                            c8 = 'e';
                            break;
                        }
                        case 1: {
                            c8 = '\u000e';
                            break;
                        }
                        case 2: {
                            c8 = '9';
                            break;
                        }
                        case 3: {
                            c8 = '\r';
                            break;
                        }
                        default: {
                            c8 = 'l';
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
        final char[] charArray5 = "\u0004mMd\u0003\u000b".toCharArray();
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
                            c10 = 'e';
                            break;
                        }
                        case 1: {
                            c10 = '\u000e';
                            break;
                        }
                        case 2: {
                            c10 = '9';
                            break;
                        }
                        case 3: {
                            c10 = '\r';
                            break;
                        }
                        default: {
                            c10 = 'l';
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
        final char[] charArray6 = "\f}kh\u000b\f}Mh\u001e".toCharArray();
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
                            c12 = 'e';
                            break;
                        }
                        case 1: {
                            c12 = '\u000e';
                            break;
                        }
                        case 2: {
                            c12 = '9';
                            break;
                        }
                        case 3: {
                            c12 = '\r';
                            break;
                        }
                        default: {
                            c12 = 'l';
                            break;
                        }
                    }
                    charArray6[length6] = (char)(c11 ^ c12);
                    ++n24;
                } while (n22 == 0);
            }
            if (n22 <= n24) {
                z[n21] = new String(charArray6).intern();
                f.z = z;
                return;
            }
            continue;
        }
    }
}
