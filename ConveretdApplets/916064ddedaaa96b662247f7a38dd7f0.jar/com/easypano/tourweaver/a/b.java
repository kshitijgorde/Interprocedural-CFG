// 
// Decompiled by Procyon v0.5.30
// 

package com.easypano.tourweaver.a;

import com.easypano.tourweaver.b.a;
import java.util.Vector;

public class b
{
    Vector a;
    String b;
    String c;
    boolean d;
    b e;
    private static String[] z;
    
    public b() {
        this.a = new Vector();
        this.d = false;
        this.e = null;
    }
    
    public b(final String b) {
        this.a = new Vector();
        this.d = false;
        this.e = null;
        this.b = b;
    }
    
    public void a(final boolean d) {
        this.d = d;
    }
    
    public boolean a() {
        return this.d;
    }
    
    public void a(final String b) {
        this.b = b;
    }
    
    public String b() {
        return this.b;
    }
    
    public void b(final String c) {
        this.c = c;
    }
    
    public String c() {
        return this.c;
    }
    
    public void a(final b e) {
        this.e = e;
    }
    
    public b d() {
        return this.e;
    }
    
    public void b(final b b) {
        this.a.addElement(b);
        b.a(this);
    }
    
    public void c(final b b) {
        this.a.removeElement(b);
    }
    
    public Vector c(final String s) {
        final int l = com.easypano.tourweaver.a.e.l;
        final Vector<b> vector = new Vector<b>();
        int i = 0;
        while (i < this.a.size()) {
            final Vector<b> a;
            final Vector vector2 = a = (Vector<b>)this.a;
            if (l != 0) {
                return a;
            }
            final b b = vector2.elementAt(i);
            if (l == 0) {
                if (s != null && s.equals(b.b())) {
                    vector.addElement(b);
                }
                ++i;
            }
            if (l != 0) {
                break;
            }
        }
        return vector;
    }
    
    public Vector e() {
        return this.a;
    }
    
    public boolean f() {
        return this.a.isEmpty();
    }
    
    public void g() {
        int l = com.easypano.tourweaver.a.e.l;
        int n = 0;
        b b = this.e;
        while (b != null) {
            ++n;
            b = b.d();
            if (l != 0) {
                break;
            }
        }
        int i = 0;
        while (true) {
            while (i < n) {
                System.out.print(com.easypano.tourweaver.a.b.z[0]);
                ++i;
                if (l != 0) {
                    if (l == 0) {
                        if (this.c != null) {
                            System.out.print(com.easypano.tourweaver.a.b.z[1] + this.c);
                        }
                        System.out.println();
                    }
                    int j = 0;
                    while (j < this.a.size()) {
                        ((b)this.a.elementAt(j)).g();
                        ++j;
                        if (l != 0) {
                            break;
                        }
                    }
                    if (com.easypano.tourweaver.b.a.o != 0) {
                        com.easypano.tourweaver.a.e.l = ++l;
                    }
                    return;
                }
                if (l != 0) {
                    break;
                }
            }
            System.out.print(this.b);
            continue;
        }
    }
    
    static {
        final String[] z = new String[2];
        final int n = 0;
        final char[] charArray = "]vE\u0007".toCharArray();
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
                            c2 = '}';
                            break;
                        }
                        case 1: {
                            c2 = 'V';
                            break;
                        }
                        case 2: {
                            c2 = 'e';
                            break;
                        }
                        case 3: {
                            c2 = '\'';
                            break;
                        }
                        default: {
                            c2 = 'd';
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
        final char[] charArray2 = "]vE\u0007D".toCharArray();
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
                            c4 = '}';
                            break;
                        }
                        case 1: {
                            c4 = 'V';
                            break;
                        }
                        case 2: {
                            c4 = 'e';
                            break;
                        }
                        case 3: {
                            c4 = '\'';
                            break;
                        }
                        default: {
                            c4 = 'd';
                            break;
                        }
                    }
                    charArray2[length2] = (char)(c3 ^ c4);
                    ++n8;
                } while (n6 == 0);
            }
            if (n6 <= n8) {
                z[n5] = new String(charArray2).intern();
                b.z = z;
                return;
            }
            continue;
        }
    }
}
