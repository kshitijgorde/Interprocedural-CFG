// 
// Decompiled by Procyon v0.5.30
// 

package zp.a.a.a.e;

import java.net.URL;
import java.io.InputStream;
import java.io.ByteArrayInputStream;
import java.util.Enumeration;
import java.io.EOFException;
import java.util.Vector;
import java.io.DataInputStream;
import java.awt.Rectangle;
import java.util.Hashtable;
import java.awt.Dimension;
import java.awt.Component;

public class g
{
    public static final String for = "fif=";
    public static final String l = "&obj=basic-info&obj=comp-group,2,*&til=3,*&obj=iip,1.0";
    public static final int if = 0;
    public static final int f = 1;
    public static final int g = 2;
    public static final int c = 3;
    public int k;
    public f u;
    public Component o;
    public String else;
    public String j;
    public b t;
    public String i;
    public Dimension try;
    public Dimension r;
    public float char;
    public a[] case;
    public byte[][] d;
    public int a;
    public int m;
    public float byte;
    public boolean new;
    public boolean e;
    public int h;
    public int[][][] null;
    private h[] long;
    public Hashtable do;
    public int s;
    public int int;
    public Rectangle q;
    public Rectangle n;
    public Rectangle p;
    public Rectangle void;
    public int[] b;
    public zp.a.b.a.a goto;
    
    public g() throws c {
        this.k = 120;
        this.r = new Dimension();
        this.char = 0.0f;
        this.d = new byte[255][];
        this.new = false;
        this.e = false;
        this.h = -1;
        this.null = new int[8][8][];
        this.do = new Hashtable();
        this.s = 0;
        this.int = 0;
        this.q = new Rectangle();
        this.n = new Rectangle();
        this.p = new Rectangle();
        this.void = new Rectangle();
        this.b = new int[4];
        this.goto = new zp.a.b.a.a();
    }
    
    public g(final String s, final f u, final Component o, final int k) throws c {
        this.k = 120;
        this.r = new Dimension();
        this.char = 0.0f;
        this.d = new byte[255][];
        this.new = false;
        this.e = false;
        this.h = -1;
        this.null = new int[8][8][];
        this.do = new Hashtable();
        this.s = 0;
        this.int = 0;
        this.q = new Rectangle();
        this.n = new Rectangle();
        this.p = new Rectangle();
        this.void = new Rectangle();
        this.b = new int[4];
        this.goto = new zp.a.b.a.a();
        this.byte = 1.0f;
        this.a = -1;
        this.o = o;
        this.u = u;
        this.k = k;
        this.long = new h[this.k];
        for (int i = 0; i < this.k; ++i) {
            this.long[i] = new h();
        }
        if (!this.a(s)) {
            throw new c("LP Core Error: Invalid Image URL");
        }
        if (this.o == null) {
            throw new c("LP Core Error: Invalid Component parameter in IipImage");
        }
        if (this.u == null) {
            throw new c("LP Core Error: Invalid CallBackForPaint parameter in IipImage");
        }
        this.if();
        if (this.a < 3 || this.try.width <= 0) {
            throw new c("LP Core Error: Invalid Image Size");
        }
        this.case = new a[this.a + 1];
        this.for();
        this.new = true;
        if (this.j != null) {
            try {
                this.t = new b(this.else, this.j);
                this.goto.a(this.t.a("lookuptable"));
            }
            catch (Exception ex) {
                this.goto.a(0);
            }
        }
    }
    
    public zp.a.b.a.a a() {
        return this.goto;
    }
    
    public void a(final Dimension dimension, final int n) {
        this.char = n;
        this.r.width = dimension.width;
        this.r.height = dimension.height;
    }
    
    public void if(final int s) {
        this.s = s;
    }
    
    public void int() {
        if (this.int >= 0) {
            this.byte();
        }
        this.int = 0;
    }
    
    public boolean a(final String s) {
        final int n = s.indexOf("://") + 3;
        final int index = s.indexOf("/", n);
        if (n < 3 || index < 0) {
            return false;
        }
        this.else = s.substring(0, index);
        final int index2 = s.indexOf("?", index);
        this.j = null;
        if (index2 != -1) {
            this.j = s.substring(index, index2);
        }
        final int n2 = s.toLowerCase().indexOf("fif=", index) + 4;
        if (n2 < 4) {
            return false;
        }
        this.i = s.substring(n2);
        return true;
    }
    
    public long a(final int n, final int n2, final int n3) {
        return n2 + (n3 << 24) + (n << 48);
    }
    
    public int[] a(final int[] array, final boolean b) {
        final int n = array[0];
        int n2 = array[1];
        int n3 = array[2];
        array[3] = 0;
        if (n == 3) {
            return this.null[n2][n3];
        }
        final long a = this.a(n, n2, n3);
        final int a2 = this.a(a);
        if (a2 >= 0) {
            this.long[a2].if = this.k;
            return this.long[a2].do;
        }
        int[] array2 = null;
        if ((b || this.int <= this.s) && this.case[n].do[n2][n3] != null && this.case[n].do[n2][n3].null) {
            array2 = this.if(a);
            this.case[n].a(n2, n3, array2);
            ++this.int;
        }
        else {
            if (this.do.containsKey(new Long(a))) {
                array[3] = 1;
            }
            else {
                array[3] = 2;
            }
            for (int i = n - 1; i >= 3; --i) {
                n2 /= 2;
                n3 /= 2;
                final long a3 = this.a(i, n2, n3);
                array[0] = i;
                array[1] = n2;
                array[2] = n3;
                if (i == 3) {
                    return this.null[n2][n3];
                }
                final int a4 = this.a(a3);
                if (a4 >= 0) {
                    this.long[a4].if = this.k;
                    return this.long[a4].do;
                }
                if ((b || this.int <= this.s) && this.case[i].do[n2][n3] != null) {
                    array2 = this.if(a3);
                    this.case[i].a(n2, n3, array2);
                    ++this.int;
                    break;
                }
            }
        }
        return array2;
    }
    
    public int a(final long n) {
        for (int i = 0; i < this.k; ++i) {
            if (this.long[i].a == n) {
                return i;
            }
        }
        return -1;
    }
    
    public void byte() {
        for (int i = 0; i < this.k; ++i) {
            if (this.long[i].if > 0) {
                final h h = this.long[i];
                --h.if;
            }
        }
    }
    
    public int[] if(final long a) {
        int n = 0;
        int n2 = this.k;
        for (int i = 0; i < this.k; ++i) {
            if (this.long[i].if < n2) {
                n2 = this.long[i].if;
                n = i;
            }
        }
        final int[] do1 = this.long[n].do;
        this.long[n].if = this.k;
        this.long[n].a = a;
        return do1;
    }
    
    public synchronized boolean a(final Rectangle rectangle, final Rectangle rectangle2, final zp.a.b.b b, final boolean b2, final zp.a.a.a.a.a a) throws c {
        boolean b3 = false;
        boolean b4 = false;
        if (this.try.width <= 0 || !this.new) {
            return b4;
        }
        if (rectangle.width <= 0 || rectangle.height <= 0 || rectangle2.width <= 0 || rectangle2.height <= 0) {
            throw new c("LP Core Error: Invalid Image Size");
        }
        this.m = this.a;
        for (int width = rectangle.width; width * 2 <= this.try.width; width *= 2, --this.m) {}
        if (this.m > this.a) {
            this.m = this.a;
        }
        if (this.m < 3) {
            this.m = 3;
        }
        final int width2 = this.case[this.m].for.width;
        final int height = this.case[this.m].for.height;
        final int width3 = (64 * rectangle.width + width2 - 1) / width2;
        final int height2 = (64 * rectangle.height + height - 1) / height;
        final int x = -((rectangle2.x - rectangle.x) % width3);
        final int y = -((rectangle2.y - rectangle.y) % height2);
        final int n = (rectangle2.x - rectangle.x) / width3;
        final int n2 = (rectangle2.y - rectangle.y) / height2;
        this.q.y = y;
        this.q.height = height2;
        int n3 = n2;
        while (this.q.y < rectangle2.height) {
            this.q.x = x;
            this.q.width = width3;
            int n4 = n;
            while (this.q.x < rectangle2.width) {
                if (this.a(b, rectangle2, this.m, n4, n3, this.q, b2) == 2) {
                    b3 = true;
                }
                final Rectangle q = this.q;
                q.x += width3;
                ++n4;
            }
            final Rectangle q2 = this.q;
            q2.y += height2;
            ++n3;
        }
        if (b2 && b3) {
            b4 = true;
            this.a(true);
            this.h = this.m;
            this.case[this.h].if();
            this.a(this.m, rectangle2, rectangle);
            this.case[this.h].do();
            if (!zp.a.a.a.d.a.if && !zp.a.a.a.d.a.byte) {
                a.a();
            }
        }
        return b4;
    }
    
    public synchronized boolean a(final Rectangle rectangle, final Rectangle rectangle2, final zp.a.b.b b, final boolean b2, final zp.a.a.a.a.a a, final int n, final boolean b3) throws c {
        boolean b4 = false;
        boolean b5 = false;
        boolean b6 = false;
        if (this.try.width <= 0 || !this.new) {
            return b6;
        }
        if (rectangle.width <= 0 || rectangle.height <= 0 || rectangle2.width <= 0 || rectangle2.height <= 0) {
            throw new c("LP Core Error: Invalid Image Size");
        }
        this.m = this.a;
        for (int width = rectangle.width; width * 2 <= this.try.width; width *= 2, --this.m) {}
        if (this.m > this.a) {
            this.m = this.a;
        }
        if (this.m < 3) {
            this.m = 3;
        }
        final float n2 = rectangle.width / this.case[this.m].for.width * 64.0f;
        final float n3 = rectangle.height / this.case[this.m].for.height * 64.0f;
        final float n4 = (rectangle2.x - rectangle.x) / rectangle.width;
        final float n5 = (rectangle2.y - rectangle.y) / rectangle.height;
        final int n6 = (int)(n4 * this.case[this.m].for.width / 64.0f);
        int n7 = (int)(n5 * this.case[this.m].for.height / 64.0f);
        final float n8 = 0.0f - (n4 - n6 * 64.0f / this.case[this.m].for.width) * (this.case[this.m].for.width / 64.0f) * n2;
        final float n9 = 0.0f - (n5 - n7 * 64.0f / this.case[this.m].for.height) * (this.case[this.m].for.height / 64.0f) * n3;
        this.q.x = (int)Math.ceil(n8);
        this.q.y = (int)Math.ceil(n9);
        this.q.width = (int)Math.ceil(n2);
        this.q.height = (int)Math.ceil(n3);
        int n10 = n6;
        final float n11 = rectangle2.height;
        final float n12 = rectangle2.width;
        while (this.q.y < n11) {
            if (n7 == this.case[this.m].int.height - 1 && this.q.y + this.q.height < rectangle2.height) {
                this.q.height = rectangle2.height - this.q.y;
            }
            while (this.q.x < n12) {
                if (n10 == this.case[this.m].int.width - 1 && this.q.width + this.q.x < rectangle2.width) {
                    this.q.width = rectangle2.width - this.q.x;
                }
                final int a2 = this.a(b, rectangle2, this.m, n10, n7, this.q, b2);
                if (a2 == 2) {
                    b4 = true;
                }
                if (a2 == 1) {
                    b5 = true;
                }
                final Rectangle q = this.q;
                q.x += this.q.width;
                ++n10;
            }
            this.q.x = (int)Math.ceil(n8);
            final Rectangle q2 = this.q;
            q2.y += this.q.height;
            ++n7;
            n10 = n6;
        }
        final int n13 = rectangle.width / this.r.width;
        final int n14 = rectangle2.x - n % this.r.width * n13;
        final int n15 = rectangle.height / this.r.height;
        final int n16 = rectangle2.y - n / this.r.width * n15;
        this.n.width = rectangle2.width;
        this.n.height = rectangle2.height;
        if (b2 && (b4 || (this.e && b5))) {
            b6 = true;
            this.a(true);
            this.e = false;
            this.h = this.m;
            this.case[this.h].if();
            this.a(this.m, rectangle2, rectangle);
            this.case[this.h].do();
            a.a();
        }
        else if (b2 && this.case[this.m].if == 0) {
            boolean b7 = false;
            this.a(this.e = true);
            for (int n17 = 0; n17 <= this.char / 2.0f; ++n17) {
                int n18 = n + n17;
                if (n18 >= this.char) {
                    n18 -= (int)this.char;
                }
                final int n19 = n18 % this.r.width;
                final int n20 = n18 / this.r.width;
                this.n.x = n19 * n13 + n14;
                this.n.y = n20 * n15 + n16;
                b7 |= this.a(this.m, this.n, rectangle);
                if (b7) {
                    break;
                }
                int n21 = n - n17;
                if (n21 < 0) {
                    n21 += (int)this.char;
                }
                if (n21 != n18) {
                    final int n22 = n21 % this.r.width;
                    final int n23 = n21 / this.r.width;
                    this.n.x = n22 * n13 + n14;
                    this.n.y = n23 * n15 + n16;
                    b7 |= this.a(this.m, this.n, rectangle);
                    if (b7) {
                        break;
                    }
                }
            }
            if (b7) {
                this.h = this.m;
                this.case[this.h].if();
                this.case[this.h].do();
            }
        }
        return b6;
    }
    
    public boolean a(final int n, final Rectangle rectangle, final Rectangle rectangle2) {
        boolean b = false;
        final float n2 = (rectangle.x - rectangle2.x) / rectangle2.width;
        final float n3 = (rectangle.y - rectangle2.y) / rectangle2.height;
        int n4 = (int)(n2 * this.case[n].for.width / 64.0f);
        if (n4 < 0) {
            n4 = 0;
        }
        int n5 = (int)(n3 * this.case[n].for.height / 64.0f);
        if (n5 < 0) {
            n5 = 0;
        }
        int n6 = (int)((n2 + rectangle.width / rectangle2.width) * this.case[n].for.width / 64.0f);
        if (n6 >= this.case[n].int.width - 1) {
            n6 = this.case[n].int.width - 1;
        }
        int n7 = (int)((n3 + rectangle.height / rectangle2.height) * this.case[n].for.height / 64.0f);
        if (n7 >= this.case[n].int.height - 1) {
            n7 = this.case[n].int.height - 1;
        }
        for (int i = 0; i <= 1 + (n7 - n5) / 2; ++i) {
            final int n8 = (n7 + n5) / 2 + i;
            if (n8 <= n7) {
                for (int j = n4; j <= n6; ++j) {
                    final Long n9 = new Long(this.a(n, j, n8));
                    if (this.case[n].do[j][n8] == null && !this.do.containsKey(n9)) {
                        this.case[n].a(j, n8);
                        this.do.put(n9, n9);
                        b = true;
                    }
                }
            }
            final int n10 = (n7 + n5) / 2 - i;
            if (n10 >= n5 && i > 0) {
                for (int k = n4; k <= n6; ++k) {
                    final Long n11 = new Long(this.a(n, k, n10));
                    if (this.case[n].do[k][n10] == null && !this.do.containsKey(n11)) {
                        this.case[n].a(k, n10);
                        this.do.put(n11, n11);
                        b = true;
                    }
                }
            }
        }
        return b;
    }
    
    public int a(final zp.a.b.b b, final Rectangle rectangle, final int n, final int n2, final int n3, final Rectangle rectangle2, final boolean b2) {
        try {
            if (rectangle2.y + rectangle2.height < 0 || rectangle2.y > rectangle.height || rectangle2.x + rectangle2.width < 0 || rectangle2.x > rectangle.width) {
                return 3;
            }
            this.b[0] = n;
            this.b[1] = n2;
            this.b[2] = n3;
            final int[] a = this.a(this.b, b2);
            final int n4 = 1 << n - this.b[0];
            int width = rectangle2.width;
            int height = rectangle2.height;
            int x = rectangle2.x;
            int y = rectangle2.y;
            final int n5 = 16384 / n4 / width;
            final int n6 = 16384 / n4 / height;
            int n7 = 256 * (n2 - this.b[1] * n4) * (64 / n4);
            int n8 = 256 * (n3 - this.b[2] * n4) * (64 / n4);
            final int[] a2 = b.a;
            final int new1 = b.new;
            final int do1 = b.do;
            final boolean b3 = this.goto != null && this.goto.if() > 0;
            if (y < 0) {
                n8 -= n6 * y;
                height += y;
                y = 0;
            }
            int n9 = y + height;
            if (n9 > do1) {
                n9 = do1;
            }
            if (x < 0) {
                n7 -= n5 * x;
                width += x;
                x = 0;
            }
            int n10 = x + width;
            if (n10 > new1) {
                n10 = new1;
            }
            final int n11 = n9 * new1;
            if (n6 < 256 || n5 < 256) {
                for (int n12 = n8, i = y * new1; i < n11; i += new1, n12 += n6) {
                    final int n13 = (n12 >> 8) * 64;
                    final int n14 = i + n10;
                    if (b3) {
                        for (int n15 = n7 + (n13 << 8), j = i + x; j < n14; ++j, n15 += n5) {
                            a2[j] = this.goto.filterRGB(0, 0, a[n15 >> 8]);
                        }
                    }
                    else {
                        for (int n16 = n7 + (n13 << 8), k = i + x; k < n14; ++k, n16 += n5) {
                            a2[k] = a[n16 >> 8];
                        }
                    }
                }
            }
            else {
                for (int n17 = n8, l = y * new1; l < n11; l += new1, n17 += n6) {
                    final int n18 = (n17 >> 8) * 64;
                    for (int n19 = l + n10, n20 = n7 + (n18 << 8), n21 = l + x; n21 < n19; ++n21, n20 += n5) {
                        switch ((n20 >> 5 & 0x7) | (n17 >> 5 & 0x7) << 3) {
                            case 0: {
                                a2[n21] = a[n20 >> 8];
                                break;
                            }
                            case 1: {
                                a2[n21] = (3 * (a[n20 >> 8] >> 2 & 0x3F3F3F3F) + (a[(n20 >> 8) + 1] >> 2 & 0x3F3F3F3F) | 0xFF000000);
                                break;
                            }
                            case 2: {
                                a2[n21] = (3 * (a[n20 >> 8] >> 2 & 0x3F3F3F3F) + (a[(n20 >> 8) + 1] >> 2 & 0x3F3F3F3F) | 0xFF000000);
                                break;
                            }
                            case 3: {
                                a2[n21] = ((a[n20 >> 8] >> 1 & 0x7F7F7F7F) + (a[(n20 >> 8) + 1] >> 1 & 0x7F7F7F7F) | 0xFF000000);
                                break;
                            }
                            case 4: {
                                a2[n21] = ((a[n20 >> 8] >> 1 & 0x7F7F7F7F) + (a[(n20 >> 8) + 1] >> 1 & 0x7F7F7F7F) | 0xFF000000);
                                break;
                            }
                            case 5: {
                                a2[n21] = ((a[n20 >> 8] >> 2 & 0x3F3F3F3F) + 3 * (a[(n20 >> 8) + 1] >> 2 & 0x3F3F3F3F) | 0xFF000000);
                                break;
                            }
                            case 6: {
                                a2[n21] = ((a[n20 >> 8] >> 2 & 0x3F3F3F3F) + 3 * (a[(n20 >> 8) + 1] >> 2 & 0x3F3F3F3F) | 0xFF000000);
                                break;
                            }
                            case 7: {
                                a2[n21] = (a[(n20 >> 8) + 1] | 0xFF000000);
                                break;
                            }
                            case 8: {
                                a2[n21] = (3 * (a[n20 >> 8] >> 2 & 0x3F3F3F3F) + (a[(n20 >> 8) + 64] >> 2 & 0x3F3F3F3F) | 0xFF000000);
                                break;
                            }
                            case 9: {
                                a2[n21] = ((a[n20 >> 8] >> 1 & 0x7F7F7F7F) + (a[(n20 >> 8) + 1] >> 2 & 0x3F3F3F3F) + (a[(n20 >> 8) + 64] >> 2 & 0x3F3F3F3F) | 0xFF000000);
                                break;
                            }
                            case 10: {
                                a2[n21] = ((a[n20 >> 8] >> 1 & 0x7F7F7F7F) + (a[(n20 >> 8) + 1] >> 2 & 0x3F3F3F3F) + (a[(n20 >> 8) + 64] >> 2 & 0x3F3F3F3F) | 0xFF000000);
                                break;
                            }
                            case 11: {
                                a2[n21] = ((a[n20 >> 8] >> 1 & 0x7F7F7F7F) + (a[(n20 >> 8) + 1] >> 1 & 0x7F7F7F7F) | 0xFF000000);
                                break;
                            }
                            case 12: {
                                a2[n21] = ((a[n20 >> 8] >> 1 & 0x7F7F7F7F) + (a[(n20 >> 8) + 1] >> 1 & 0x7F7F7F7F) | 0xFF000000);
                                break;
                            }
                            case 13: {
                                a2[n21] = ((a[n20 >> 8] >> 2 & 0x3F3F3F3F) + (a[(n20 >> 8) + 1] >> 1 & 0x7F7F7F7F) + (a[(n20 >> 8) + 65] >> 2 & 0x3F3F3F3F) | 0xFF000000);
                                break;
                            }
                            case 14: {
                                a2[n21] = ((a[n20 >> 8] >> 2 & 0x3F3F3F3F) + (a[(n20 >> 8) + 1] >> 1 & 0x7F7F7F7F) + (a[(n20 >> 8) + 65] >> 2 & 0x3F3F3F3F) | 0xFF000000);
                                break;
                            }
                            case 15: {
                                a2[n21] = (3 * (a[(n20 >> 8) + 1] >> 2 & 0x3F3F3F3F) + (a[(n20 >> 8) + 65] >> 2 & 0x3F3F3F3F) | 0xFF000000);
                                break;
                            }
                            case 16: {
                                a2[n21] = (3 * (a[n20 >> 8] >> 2 & 0x3F3F3F3F) + (a[(n20 >> 8) + 64] >> 2 & 0x3F3F3F3F) | 0xFF000000);
                                break;
                            }
                            case 17: {
                                a2[n21] = ((a[n20 >> 8] >> 1 & 0x7F7F7F7F) + (a[(n20 >> 8) + 1] >> 2 & 0x3F3F3F3F) + (a[(n20 >> 8) + 64] >> 2 & 0x3F3F3F3F) | 0xFF000000);
                                break;
                            }
                            case 18: {
                                a2[n21] = ((a[n20 >> 8] >> 1 & 0x7F7F7F7F) + (a[(n20 >> 8) + 1] >> 2 & 0x3F3F3F3F) + (a[(n20 >> 8) + 64] >> 2 & 0x3F3F3F3F) | 0xFF000000);
                                break;
                            }
                            case 19: {
                                a2[n21] = ((a[n20 >> 8] >> 1 & 0x7F7F7F7F) + (a[(n20 >> 8) + 1] >> 1 & 0x7F7F7F7F) | 0xFF000000);
                                break;
                            }
                            case 20: {
                                a2[n21] = ((a[n20 >> 8] >> 1 & 0x7F7F7F7F) + (a[(n20 >> 8) + 1] >> 1 & 0x7F7F7F7F) | 0xFF000000);
                                break;
                            }
                            case 21: {
                                a2[n21] = ((a[n20 >> 8] >> 2 & 0x3F3F3F3F) + (a[(n20 >> 8) + 1] >> 1 & 0x7F7F7F7F) + (a[(n20 >> 8) + 65] >> 2 & 0x3F3F3F3F) | 0xFF000000);
                                break;
                            }
                            case 22: {
                                a2[n21] = ((a[n20 >> 8] >> 2 & 0x3F3F3F3F) + (a[(n20 >> 8) + 1] >> 1 & 0x7F7F7F7F) + (a[(n20 >> 8) + 65] >> 2 & 0x3F3F3F3F) | 0xFF000000);
                                break;
                            }
                            case 23: {
                                a2[n21] = (3 * (a[(n20 >> 8) + 1] >> 2 & 0x3F3F3F3F) + (a[(n20 >> 8) + 65] >> 2 & 0x3F3F3F3F) | 0xFF000000);
                                break;
                            }
                            case 24: {
                                a2[n21] = ((a[n20 >> 8] >> 1 & 0x7F7F7F7F) + (a[(n20 >> 8) + 64] >> 1 & 0x7F7F7F7F) | 0xFF000000);
                                break;
                            }
                            case 25: {
                                a2[n21] = ((a[n20 >> 8] >> 1 & 0x7F7F7F7F) + (a[(n20 >> 8) + 64] >> 1 & 0x7F7F7F7F) | 0xFF000000);
                                break;
                            }
                            case 26: {
                                a2[n21] = ((a[n20 >> 8] >> 1 & 0x7F7F7F7F) + (a[(n20 >> 8) + 64] >> 1 & 0x7F7F7F7F) | 0xFF000000);
                                break;
                            }
                            case 27: {
                                a2[n21] = ((a[n20 >> 8] >> 2 & 0x3F3F3F3F) + (a[(n20 >> 8) + 1] >> 2 & 0x3F3F3F3F) + (a[(n20 >> 8) + 64] >> 2 & 0x3F3F3F3F) + (a[(n20 >> 8) + 65] >> 2 & 0x3F3F3F3F) | 0xFF000000);
                                break;
                            }
                            case 28: {
                                a2[n21] = ((a[n20 >> 8] >> 2 & 0x3F3F3F3F) + (a[(n20 >> 8) + 1] >> 2 & 0x3F3F3F3F) + (a[(n20 >> 8) + 64] >> 2 & 0x3F3F3F3F) + (a[(n20 >> 8) + 65] >> 2 & 0x3F3F3F3F) | 0xFF000000);
                                break;
                            }
                            case 29: {
                                a2[n21] = ((a[(n20 >> 8) + 1] >> 1 & 0x7F7F7F7F) + (a[(n20 >> 8) + 65] >> 1 & 0x7F7F7F7F) | 0xFF000000);
                                break;
                            }
                            case 30: {
                                a2[n21] = ((a[(n20 >> 8) + 1] >> 1 & 0x7F7F7F7F) + (a[(n20 >> 8) + 65] >> 1 & 0x7F7F7F7F) | 0xFF000000);
                                break;
                            }
                            case 31: {
                                a2[n21] = ((a[(n20 >> 8) + 1] >> 1 & 0x7F7F7F7F) + (a[(n20 >> 8) + 65] >> 1 & 0x7F7F7F7F) | 0xFF000000);
                                break;
                            }
                            case 32: {
                                a2[n21] = ((a[n20 >> 8] >> 1 & 0x7F7F7F7F) + (a[(n20 >> 8) + 64] >> 1 & 0x7F7F7F7F) | 0xFF000000);
                                break;
                            }
                            case 33: {
                                a2[n21] = ((a[n20 >> 8] >> 1 & 0x7F7F7F7F) + (a[(n20 >> 8) + 64] >> 1 & 0x7F7F7F7F) | 0xFF000000);
                                break;
                            }
                            case 34: {
                                a2[n21] = ((a[n20 >> 8] >> 1 & 0x7F7F7F7F) + (a[(n20 >> 8) + 64] >> 1 & 0x7F7F7F7F) | 0xFF000000);
                                break;
                            }
                            case 35: {
                                a2[n21] = ((a[n20 >> 8] >> 2 & 0x3F3F3F3F) + (a[(n20 >> 8) + 1] >> 2 & 0x3F3F3F3F) + (a[(n20 >> 8) + 64] >> 2 & 0x3F3F3F3F) + (a[(n20 >> 8) + 65] >> 2 & 0x3F3F3F3F) | 0xFF000000);
                                break;
                            }
                            case 36: {
                                a2[n21] = ((a[n20 >> 8] >> 2 & 0x3F3F3F3F) + (a[(n20 >> 8) + 1] >> 2 & 0x3F3F3F3F) + (a[(n20 >> 8) + 64] >> 2 & 0x3F3F3F3F) + (a[(n20 >> 8) + 65] >> 2 & 0x3F3F3F3F) | 0xFF000000);
                                break;
                            }
                            case 37: {
                                a2[n21] = ((a[(n20 >> 8) + 1] >> 1 & 0x7F7F7F7F) + (a[(n20 >> 8) + 65] >> 1 & 0x7F7F7F7F) | 0xFF000000);
                                break;
                            }
                            case 38: {
                                a2[n21] = ((a[(n20 >> 8) + 1] >> 1 & 0x7F7F7F7F) + (a[(n20 >> 8) + 65] >> 1 & 0x7F7F7F7F) | 0xFF000000);
                                break;
                            }
                            case 39: {
                                a2[n21] = ((a[(n20 >> 8) + 1] >> 1 & 0x7F7F7F7F) + (a[(n20 >> 8) + 65] >> 1 & 0x7F7F7F7F) | 0xFF000000);
                                break;
                            }
                            case 40: {
                                a2[n21] = ((a[n20 >> 8] >> 2 & 0x3F3F3F3F) + 3 * (a[(n20 >> 8) + 64] >> 2 & 0x3F3F3F3F) | 0xFF000000);
                                break;
                            }
                            case 41: {
                                a2[n21] = ((a[n20 >> 8] >> 2 & 0x3F3F3F3F) + (a[(n20 >> 8) + 64] >> 1 & 0x7F7F7F7F) + (a[(n20 >> 8) + 65] >> 2 & 0x3F3F3F3F) | 0xFF000000);
                                break;
                            }
                            case 42: {
                                a2[n21] = ((a[n20 >> 8] >> 2 & 0x3F3F3F3F) + (a[(n20 >> 8) + 64] >> 1 & 0x7F7F7F7F) + (a[(n20 >> 8) + 65] >> 2 & 0x3F3F3F3F) | 0xFF000000);
                                break;
                            }
                            case 43: {
                                a2[n21] = ((a[(n20 >> 8) + 64] >> 1 & 0x7F7F7F7F) + (a[(n20 >> 8) + 65] >> 1 & 0x7F7F7F7F) | 0xFF000000);
                                break;
                            }
                            case 44: {
                                a2[n21] = ((a[(n20 >> 8) + 64] >> 1 & 0x7F7F7F7F) + (a[(n20 >> 8) + 65] >> 1 & 0x7F7F7F7F) | 0xFF000000);
                                break;
                            }
                            case 45: {
                                a2[n21] = ((a[(n20 >> 8) + 1] >> 2 & 0x3F3F3F3F) + (a[(n20 >> 8) + 64] >> 2 & 0x3F3F3F3F) + (a[(n20 >> 8) + 65] >> 1 & 0x7F7F7F7F) | 0xFF000000);
                                break;
                            }
                            case 46: {
                                a2[n21] = ((a[(n20 >> 8) + 1] >> 2 & 0x3F3F3F3F) + (a[(n20 >> 8) + 64] >> 2 & 0x3F3F3F3F) + (a[(n20 >> 8) + 65] >> 1 & 0x7F7F7F7F) | 0xFF000000);
                                break;
                            }
                            case 47: {
                                a2[n21] = ((a[(n20 >> 8) + 1] >> 2 & 0x3F3F3F3F) + 3 * (a[(n20 >> 8) + 65] >> 2 & 0x3F3F3F3F) | 0xFF000000);
                                break;
                            }
                            case 48: {
                                a2[n21] = ((a[n20 >> 8] >> 2 & 0x3F3F3F3F) + 3 * (a[(n20 >> 8) + 64] >> 2 & 0x3F3F3F3F) | 0xFF000000);
                                break;
                            }
                            case 49: {
                                a2[n21] = ((a[n20 >> 8] >> 2 & 0x3F3F3F3F) + (a[(n20 >> 8) + 64] >> 1 & 0x7F7F7F7F) + (a[(n20 >> 8) + 65] >> 2 & 0x3F3F3F3F) | 0xFF000000);
                                break;
                            }
                            case 50: {
                                a2[n21] = ((a[n20 >> 8] >> 2 & 0x3F3F3F3F) + (a[(n20 >> 8) + 64] >> 1 & 0x7F7F7F7F) + (a[(n20 >> 8) + 65] >> 2 & 0x3F3F3F3F) | 0xFF000000);
                                break;
                            }
                            case 51: {
                                a2[n21] = ((a[(n20 >> 8) + 64] >> 1 & 0x7F7F7F7F) + (a[(n20 >> 8) + 65] >> 1 & 0x7F7F7F7F) | 0xFF000000);
                                break;
                            }
                            case 52: {
                                a2[n21] = ((a[(n20 >> 8) + 64] >> 1 & 0x7F7F7F7F) + (a[(n20 >> 8) + 65] >> 1 & 0x7F7F7F7F) | 0xFF000000);
                                break;
                            }
                            case 53: {
                                a2[n21] = ((a[(n20 >> 8) + 1] >> 2 & 0x3F3F3F3F) + (a[(n20 >> 8) + 64] >> 2 & 0x3F3F3F3F) + (a[(n20 >> 8) + 65] >> 1 & 0x7F7F7F7F) | 0xFF000000);
                                break;
                            }
                            case 54: {
                                a2[n21] = ((a[(n20 >> 8) + 1] >> 2 & 0x3F3F3F3F) + (a[(n20 >> 8) + 64] >> 2 & 0x3F3F3F3F) + (a[(n20 >> 8) + 65] >> 1 & 0x7F7F7F7F) | 0xFF000000);
                                break;
                            }
                            case 55: {
                                a2[n21] = ((a[(n20 >> 8) + 1] >> 2 & 0x3F3F3F3F) + 3 * (a[(n20 >> 8) + 65] >> 2 & 0x3F3F3F3F) | 0xFF000000);
                                break;
                            }
                            case 56: {
                                a2[n21] = (a[(n20 >> 8) + 64] | 0xFF000000);
                                break;
                            }
                            case 57: {
                                a2[n21] = (3 * (a[(n20 >> 8) + 64] >> 2 & 0x3F3F3F3F) + (a[(n20 >> 8) + 65] >> 2 & 0x3F3F3F3F) | 0xFF000000);
                                break;
                            }
                            case 58: {
                                a2[n21] = (3 * (a[(n20 >> 8) + 64] >> 2 & 0x3F3F3F3F) + (a[(n20 >> 8) + 65] >> 2 & 0x3F3F3F3F) | 0xFF000000);
                                break;
                            }
                            case 59: {
                                a2[n21] = ((a[(n20 >> 8) + 64] >> 1 & 0x7F7F7F7F) + (a[(n20 >> 8) + 65] >> 1 & 0x7F7F7F7F) | 0xFF000000);
                                break;
                            }
                            case 60: {
                                a2[n21] = ((a[(n20 >> 8) + 64] >> 1 & 0x7F7F7F7F) + (a[(n20 >> 8) + 65] >> 1 & 0x7F7F7F7F) | 0xFF000000);
                                break;
                            }
                            case 61: {
                                a2[n21] = ((a[(n20 >> 8) + 64] >> 2 & 0x3F3F3F3F) + 3 * (a[(n20 >> 8) + 65] >> 2 & 0x3F3F3F3F) | 0xFF000000);
                                break;
                            }
                            case 62: {
                                a2[n21] = ((a[(n20 >> 8) + 64] >> 2 & 0x3F3F3F3F) + 3 * (a[(n20 >> 8) + 65] >> 2 & 0x3F3F3F3F) | 0xFF000000);
                                break;
                            }
                            case 63: {
                                a2[n21] = (a[(n20 >> 8) + 65] | 0xFF000000);
                                break;
                            }
                        }
                        if (b3) {
                            a2[n21] = this.goto.filterRGB(0, 0, a2[n21]);
                        }
                    }
                }
            }
            return this.b[3];
        }
        catch (Throwable t) {
            return 0;
        }
    }
    
    public boolean a(final Dimension dimension) {
        if (this.try != null && this.try.width > 0 && this.new) {
            dimension.width = this.try.width;
            dimension.height = this.try.height;
            return true;
        }
        return false;
    }
    
    public void a(final boolean b) {
        if (this.h >= 0) {
            this.case[this.h].for();
            this.h = -1;
        }
        if (b) {
            this.do.clear();
        }
    }
    
    public void for() {
        for (int i = 0; i <= this.a; ++i) {
            this.case[i] = new a(i, this.a(i), this.do(i), this.t, this.i, this.u, this.d);
        }
    }
    
    public Dimension a(final int n) {
        final double pow = Math.pow(2.0, this.a - n);
        final int n2 = (int)(this.try.width / pow + 1.0 - 1.0 / pow);
        final int n3 = (int)(this.try.height / pow + 1.0 - 1.0 / pow);
        int n4 = (int)(n2 / 64.0);
        int n5 = (int)(n3 / 64.0);
        if (n2 % 64 != 0) {
            ++n4;
        }
        if (n3 % 64 != 0) {
            ++n5;
        }
        return new Dimension(n4, n5);
    }
    
    public Dimension do(final int n) {
        final int n2 = (int)Math.pow(2.0, this.a - n);
        return new Dimension(this.try.width / n2, this.try.height / n2);
    }
    
    public void if() throws c {
        DataInputStream do1;
        try {
            do1 = this.do();
        }
        catch (SecurityException ex2) {
            throw new c("Java Error: Viewer must reside on Image Server's IP address");
        }
        catch (Exception ex3) {
            throw new c("LP Core Error: Invalid IIP Server in URL parameter");
        }
        try {
            this.if(do1);
        }
        catch (Exception ex) {
            if (ex instanceof c) {
                throw (c)ex;
            }
            throw new c("LP Core Error: Internal parsing failure");
        }
    }
    
    public DataInputStream do() throws Exception {
        final StringBuffer append = new StringBuffer(200).append("fif=").append(this.i).append("&obj=basic-info&obj=comp-group,2,*&til=3,*&obj=iip,1.0");
        if (this.j == null) {
            this.t = new b(this.else);
        }
        else {
            this.t = new b(this.else, this.j);
        }
        return this.t.a(append.toString());
    }
    
    public void if(final DataInputStream dataInputStream) throws Exception {
        final Vector vector = new Vector();
        final Hashtable hashtable = new Hashtable();
        final Hashtable hashtable2 = new Hashtable<String, Object>();
        try {
            while (true) {
                final d a = this.a(dataInputStream);
                if (a != null) {
                    if (a.a == null) {
                        continue;
                    }
                    final String lowerCase = new String(a.a, 0).trim().toLowerCase();
                    String s = null;
                    if (a.if instanceof String) {
                        s = (String)a.if;
                    }
                    if (lowerCase.equals("fpx-version") || lowerCase.equals("iip")) {
                        this.byte = new Float(s);
                    }
                    else if (lowerCase.equals("max-size")) {
                        this.try = new Dimension(new Integer(s.substring(0, s.indexOf(32))), new Integer(s.substring(s.indexOf(32) + 1)));
                    }
                    else if (lowerCase.equals("resolution-number")) {
                        this.a = new Integer(s) - 1;
                    }
                    else if (lowerCase.startsWith("comp-group")) {
                        hashtable2.put(lowerCase, a.if);
                    }
                    else if (lowerCase.startsWith("tile")) {
                        this.a(vector, a.a, (byte[])a.if);
                    }
                    else if (lowerCase.startsWith("error") && a.if instanceof byte[]) {
                        this.a(hashtable, (byte[])a.if);
                    }
                    else {
                        if (lowerCase.startsWith("colorspace") && s.charAt(6) != '3') {
                            break;
                        }
                        continue;
                    }
                }
            }
            throw new c("LP Core Error: Only three-channel images are supported");
        }
        catch (EOFException ex) {
            dataInputStream.close();
            this.a(hashtable);
            if (!hashtable2.isEmpty()) {
                this.if(hashtable2);
            }
            if (!vector.isEmpty()) {
                this.a(vector);
            }
        }
    }
    
    public void a(final Hashtable hashtable, final byte[] array) {
        final byte[] array2 = new byte[array.length];
        int i = 0;
        while (array[i++] != 0) {
            if (i >= array.length) {
                return;
            }
        }
        int n = 0;
        while (i < array.length) {
            if (array[i] != 0) {
                array2[n++] = array[i];
            }
            ++i;
        }
        hashtable.put(new String(array2, 0).trim(), " ");
    }
    
    public void a(final Vector vector, final byte[] array, final byte[] array2) {
        final StringBuffer append = new StringBuffer("/").append(array2.length).append(":");
        final int length = append.length();
        final byte[] array3 = new byte[array.length + array2.length + length];
        System.arraycopy(array, 0, array3, 0, array.length);
        System.arraycopy(array2, 0, array3, array.length + length, array2.length);
        for (int i = 0; i < length; ++i) {
            array3[array.length + i] = (byte)append.charAt(i);
        }
        vector.addElement(array3);
    }
    
    public void a(final Hashtable hashtable) throws c {
        if (this.try == null) {
            final StringBuffer sb = new StringBuffer("LP Core Error:");
            final Enumeration<String> keys = hashtable.keys();
            while (keys.hasMoreElements()) {
                sb.append(" ").append(keys.nextElement());
            }
            if (sb.equals("LP Core Error:")) {
                sb.append(" Internal parsing failure.");
            }
            throw new c(sb.toString());
        }
    }
    
    public void if(final Hashtable hashtable) {
        final Enumeration<String> keys = (Enumeration<String>)hashtable.keys();
        while (keys.hasMoreElements()) {
            final String s = keys.nextElement();
            final byte[] array = hashtable.get(s);
            if (array.length >= 4) {
                System.arraycopy(array, 2, this.d[new Integer(s.substring(s.lastIndexOf(",") + 1)) - 1] = new byte[array.length - 4], 0, array.length - 4);
            }
        }
    }
    
    public void a(final Vector vector) throws c {
        final Dimension a = this.a(3);
        final URL a2 = this.t.a();
        for (int i = 0; i < a.width; ++i) {
            for (int j = 0; j < a.height; ++j) {
                try {
                    this.null[i][j] = new int[4096];
                }
                catch (Exception ex2) {
                    throw new c("LP Core Error: Memory allocation error");
                }
            }
        }
        final Enumeration<byte[]> elements = vector.elements();
        while (elements.hasMoreElements()) {
            final byte[] array = elements.nextElement();
            try {
                final e e = new e(new DataInputStream(new ByteArrayInputStream(array)), this.d, 3);
                if (!e.a(a2, this.null[e.a() % a.width][e.a() / a.width])) {
                    throw new c("LP Core Error: Internal parsing failure");
                }
                continue;
            }
            catch (Exception ex) {
                if (ex instanceof c) {
                    throw (c)ex;
                }
                throw new c("LP Core Error: Internal parsing failure");
            }
            break;
        }
    }
    
    public d a(final DataInputStream dataInputStream) throws Exception {
        final byte[] array = new byte[40];
        byte b;
        for (b = dataInputStream.readByte(); b == 32 || b == 10 || b == 13 || b == 9; b = dataInputStream.readByte()) {}
        array[0] = b;
        int n = 1;
        byte byte1;
        while ((byte1 = dataInputStream.readByte()) != 58 && byte1 != 47) {
            array[n++] = byte1;
        }
        final byte[] array2 = new byte[n];
        System.arraycopy(array, 0, array2, 0, n);
        if (byte1 == 47) {
            int n2 = 0;
            byte byte2;
            while ((byte2 = dataInputStream.readByte()) >= 48 && byte2 <= 57) {
                n2 = n2 * 10 + (byte2 - 48);
            }
            final byte[] array3 = new byte[n2];
            dataInputStream.readFully(array3);
            return new d(array2, array3);
        }
        return new d(array2, dataInputStream.readLine().trim());
    }
    
    public int try() {
        return this.a;
    }
    
    public int new() {
        return this.m;
    }
    
    public void if(final int h, final Rectangle rectangle) {
        this.h = h;
        this.case[this.h].if();
        this.a(h, rectangle);
        this.case[this.h].do();
    }
    
    public void a(final int n, final Rectangle rectangle) {
        for (int i = 0; i <= rectangle.height / 2; ++i) {
            final int n2 = rectangle.y + rectangle.height / 2 + i;
            if (n2 < rectangle.y + rectangle.height) {
                this.case[n].a(n2, rectangle.x, rectangle.x + rectangle.width - 1);
                for (int j = rectangle.x; j < rectangle.x + rectangle.width; ++j) {
                    if (this.case[n].do[j][n2] == null) {
                        final Long n3 = new Long(this.a(n, j, n2));
                        this.do.put(n3, n3);
                    }
                }
            }
            final int n4 = rectangle.y + rectangle.height / 2 - i;
            if (n4 >= rectangle.y && i > 0) {
                this.case[n].a(n4, rectangle.x, rectangle.x + rectangle.width - 1);
                for (int k = rectangle.x; k < rectangle.x + rectangle.width; ++k) {
                    if (this.case[n].do[k][n4] == null) {
                        final Long n5 = new Long(this.a(n, k, n4));
                        this.do.put(n5, n5);
                    }
                }
            }
        }
    }
}
