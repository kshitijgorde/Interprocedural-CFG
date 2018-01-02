// 
// Decompiled by Procyon v0.5.30
// 

package chat;

import java.util.Hashtable;
import java.util.Vector;
import java.awt.Image;

public final class ao
{
    public int a;
    public int b;
    public int c;
    public int d;
    public int e;
    public int f;
    protected Image a;
    protected Image b;
    public String a;
    public String b;
    public Image c;
    public int[] a;
    public int[] b;
    public int[] c;
    public int g;
    public boolean a;
    public boolean b;
    public boolean c;
    public boolean d;
    public boolean e;
    public Vector a;
    public int h;
    public int i;
    public int j;
    public int k;
    public boolean f;
    public int l;
    public boolean g;
    
    private void b() {
        int n = 0;
        this.c = 0;
        while (this.c < 25) {
            int index = this.a.indexOf("://", n);
            final int index2;
            if (((index2 = this.a.indexOf("@", n)) < index || index == -1) && index2 != -1) {
                index = index2;
            }
            if (index == -1) {
                break;
            }
            final String a = this.a;
            final int n2 = index;
            final String s = a;
            int lastIndex;
            if ((lastIndex = s.lastIndexOf(32, n2 - 1)) == -1) {
                lastIndex = 0;
            }
            else {
                ++lastIndex;
            }
            int n3;
            if ((n3 = s.indexOf(32, n2 + 1)) == -1) {
                n3 = s.length();
            }
            while (n3 > lastIndex && ".:,!;>\"'?".indexOf(this.a.charAt(n3 - 1)) != -1) {
                --n3;
            }
            final int n5;
            final int n4 = (n5 = lastIndex + (n3 << 16)) & 0xFFFF;
            n = n5 >>> 16;
            if (n4 < index && n > index + 3 && this.a.lastIndexOf(46, n) > index) {
                this.b[3 * this.c] = n4;
                this.b[3 * this.c + 1] = n;
                this.b[3 * this.c + 2] = 255;
                ++this.c;
            }
            ++n;
        }
    }
    
    public final void a() {
        if (this.a == null) {
            this.c = this.b;
            this.g = this.c;
            return;
        }
        this.g = 0;
        for (int i = 0; i < this.a.size(); ++i) {
            final Hashtable<?, String> hashtable;
            final String s;
            if ((s = (hashtable = this.a.elementAt(i)).get("name")) != null && s.length() != 0) {
                int n = 0;
                int index;
                while ((index = this.a.indexOf(s, n)) != -1 && this.g != 25) {
                    n = index + s.length();
                    this.c[3 * this.g] = index;
                    this.c[3 * this.g + 1] = n;
                    this.c[3 * this.g + 2] = (int)hashtable.get("color");
                    ++this.g;
                    if (index == -1) {
                        break;
                    }
                }
            }
        }
        final int g;
        if ((g = this.g) > 0) {
            for (int j = 0; j < this.c; ++j) {
                this.c[3 * (j + g)] = this.b[j * 3];
                this.c[3 * (j + g) + 1] = this.b[j * 3 + 1];
                this.c[3 * this.g + 2] = 0;
                ++this.g;
            }
        }
        else {
            this.c = this.b;
            this.g = this.c;
        }
        for (int k = 0; k < this.g; ++k) {
            final int n2 = this.c[k * 3];
            final int n3 = this.c[k * 3 + 1];
            final int n4 = this.c[k * 3 + 2];
            int n5;
            for (n5 = k; n5 > 0 && this.c[3 * (n5 - 1)] > n2; --n5) {
                this.c[n5 * 3] = this.c[3 * (n5 - 1)];
                this.c[n5 * 3 + 1] = this.c[3 * (n5 - 1) + 1];
                this.c[n5 * 3 + 2] = this.c[3 * (n5 - 1) + 2];
            }
            this.c[n5 * 3] = n2;
            this.c[n5 * 3 + 1] = n3;
            this.c[n5 * 3 + 2] = n4;
        }
    }
    
    public ao(final String s, final aj aj, final b b) {
        final String c = aj.c;
        final int g = aj.g;
        if (!aj.a(62)) {
            aj.a(61);
        }
        aj.a(59);
        this(s, c, b, g);
        aj.a(23);
        this.h = aj.c;
    }
    
    public ao(final String s, final String s2, final b b, final int n) {
        this(s, s2, false, b, n, 0, 0);
    }
    
    private ao(final String a, final String b, final boolean b2, final b b3, final int e, final int i, final int l) {
        this.h = 0;
        this.i = 0;
        this.j = 0;
        this.c = 0;
        this.d = -1;
        this.f = -1;
        this.a = new int[31];
        this.b = new int[75];
        this.c = new int[75];
        this.a[0] = 0;
        this.a = a;
        this.b = b;
        if (b3 != null) {
            this.a = b3.a;
        }
        this.b = b2;
        this.e = e;
        this.k = 0;
        this.f = false;
        this.i = i;
        this.l = l;
        this.e = false;
        this.b();
        this.a();
    }
    
    public ao(final String s, final aj aj, final boolean b, final b b2, final int n, final int n2) {
        final String c = aj.c;
        final int g = aj.g;
        if (!aj.a(62)) {
            aj.a(61);
        }
        aj.a(59);
        this(s, c, b, b2, g, n, n2);
        aj.a(23);
        this.h = aj.c;
    }
}
