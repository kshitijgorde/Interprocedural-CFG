// 
// Decompiled by Procyon v0.5.30
// 

package com.iseemedia.apps.tourclients40.iip;

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
import com.iseemedia.apps.tourclients40.util.a;
import java.awt.Component;

public final class f
{
    private int w;
    public int a;
    public b b;
    public Component c;
    public String d;
    public a e;
    public String f;
    public Dimension g;
    public com.iseemedia.apps.tourclients40.iip.a[] h;
    public byte[][] i;
    public int j;
    public int k;
    public boolean l;
    public int m;
    public int[][][] n;
    public e[] o;
    public Hashtable p;
    public int q;
    public int r;
    public Rectangle s;
    public Rectangle t;
    public Rectangle u;
    public int[] v;
    private int x;
    
    public f() throws d {
        this.w = 0;
        this.a = 500;
        this.i = new byte[255][];
        this.l = false;
        this.m = -1;
        this.n = null;
        this.p = new Hashtable();
        this.q = 0;
        this.r = 0;
        this.s = new Rectangle();
        new Rectangle();
        this.t = new Rectangle();
        this.u = new Rectangle();
        this.v = new int[4];
    }
    
    public f(final String s, final b b, final Component c, final int a, final int x) throws d {
        this.w = 0;
        this.a = 500;
        this.i = new byte[255][];
        this.l = false;
        this.m = -1;
        this.n = null;
        this.p = new Hashtable();
        this.q = 0;
        this.r = 0;
        this.s = new Rectangle();
        new Rectangle();
        this.t = new Rectangle();
        this.u = new Rectangle();
        this.v = new int[4];
        this.j = -1;
        this.c = c;
        this.b = b;
        this.x = x;
        if (0 < a) {
            this.a = a;
        }
        else {
            this.a = 500;
        }
        this.o = new e[this.a];
        for (int i = 0; i < this.a; ++i) {
            this.o[i] = new e();
        }
        if (!this.a(s)) {
            throw new d("Invalid Image URL");
        }
        if (this.c == null) {
            throw new d("Invalid Component parameter in IipImage");
        }
        if (this.b == null) {
            throw new d("Invalid CallBackForPaint parameter in IipImage");
        }
        this.d();
        if (this.j < 3 || this.g.width <= 0) {
            throw new d("Invalid Image Size");
        }
        this.h = new com.iseemedia.apps.tourclients40.iip.a[this.j + 1];
        this.c();
        this.l = true;
    }
    
    public final void a() {
        if (this.r >= 0) {
            this.b();
        }
        this.r = 0;
    }
    
    public final boolean a(final String s) {
        final int n = s.indexOf("://") + 3;
        final int index = s.indexOf("/", n);
        if (n < 3 || index < 0) {
            return false;
        }
        this.d = s.substring(0, index);
        final int n2;
        if ((n2 = s.toLowerCase().indexOf("fif=", index) + 4) < 4) {
            return false;
        }
        this.f = s.substring(n2);
        return true;
    }
    
    public final long a(final int n, final int n2, final int n3) {
        return n2 + (n3 << 24) + (n << 48);
    }
    
    public final int[] a(final int[] array, final boolean b) {
        final int n = array[0];
        int n2 = array[1];
        int n3 = array[2];
        array[3] = 0;
        if (n == this.w) {
            return this.n[n2][n3];
        }
        final long a = this.a(n, n2, n3);
        final int a2;
        if ((a2 = this.a(a)) >= 0) {
            this.o[a2].b = this.a;
            return this.o[a2].a;
        }
        int[] array2 = null;
        if ((b || this.r <= this.q) && this.h[n].a[n2][n3] != null && this.h[n].a[n2][n3].c) {
            array2 = this.b(a);
            this.h[n].a(n2, n3, array2);
            ++this.r;
        }
        else {
            if (this.p.containsKey(new Long(a))) {
                array[3] = 1;
            }
            else {
                array[3] = 2;
            }
            for (int i = n - 1; i >= 0; --i) {
                n2 /= 2;
                n3 /= 2;
                final long a3 = this.a(i, n2, n3);
                array[0] = i;
                array[1] = n2;
                array[2] = n3;
                if (i == this.w) {
                    return this.n[n2][n3];
                }
                final int a4;
                if ((a4 = this.a(a3)) >= 0) {
                    this.o[a4].b = this.a;
                    return this.o[a4].a;
                }
                if ((b || this.r <= this.q) && this.h[i].a[n2][n3] != null) {
                    array2 = this.b(a3);
                    this.h[i].a(n2, n3, array2);
                    ++this.r;
                    break;
                }
            }
        }
        return array2;
    }
    
    public final int a(final long n) {
        for (int i = 0; i < this.a; ++i) {
            if (this.o[i].c == n) {
                return i;
            }
        }
        return -1;
    }
    
    public final void b() {
        for (int i = 0; i < this.a; ++i) {
            if (this.o[i].b > 0) {
                final e e = this.o[i];
                --e.b;
            }
        }
    }
    
    public final int[] b(final long c) {
        int n = 0;
        int n2 = this.a;
        for (int i = 0; i < this.a; ++i) {
            if (this.o[i].b < n2) {
                n2 = this.o[i].b;
                n = i;
            }
        }
        final int[] a = this.o[n].a;
        this.o[n].b = this.a;
        this.o[n].c = c;
        return a;
    }
    
    public final synchronized boolean a(final Rectangle rectangle, final Rectangle rectangle2, final com.iseemedia.image.b b, final boolean b2, final com.iseemedia.apps.tourclients40.util.b b3, final boolean b4) throws d {
        boolean b5 = false;
        boolean b6 = false;
        if (this.g.width <= 0 || !this.l) {
            return false;
        }
        if (rectangle.width <= 0 || rectangle.height <= 0 || rectangle2.width <= 0 || rectangle2.height <= 0) {
            throw new d("Invalid Image Size");
        }
        this.k = this.j;
        for (int width = rectangle.width; width * 2 <= this.g.width; width *= 2, --this.k) {}
        if (this.k > this.j) {
            this.k = this.j;
        }
        if (this.k < this.w) {
            this.k = this.w;
        }
        final float n = rectangle.width / this.h[this.k].c.width * 64.0f;
        final float n2 = rectangle.height / this.h[this.k].c.height * 64.0f;
        final float n3 = (rectangle2.x - rectangle.x) / rectangle.width;
        final float n4 = (rectangle2.y - rectangle.y) / rectangle.height;
        final int n5 = (int)(n3 * this.h[this.k].c.width / 64.0f);
        int n6 = (int)(n4 * this.h[this.k].c.height / 64.0f);
        final float n7 = 0.0f - (n3 - n5 * 64.0f / this.h[this.k].c.width) * (this.h[this.k].c.width / 64.0f) * n;
        final float n8 = 0.0f - (n4 - n6 * 64.0f / this.h[this.k].c.height) * (this.h[this.k].c.height / 64.0f) * n2;
        this.s.x = (int)Math.ceil(n7);
        this.s.y = (int)Math.ceil(n8);
        this.s.width = (int)Math.ceil(n);
        this.s.height = (int)Math.ceil(n2);
        int n9 = n5;
        final float n10 = rectangle2.height;
        final float n11 = rectangle2.width;
        while (this.s.y < n10) {
            if (n6 == this.h[this.k].b.height - 1 && this.s.y + this.s.height < rectangle2.height) {
                this.s.height = rectangle2.height - this.s.y;
            }
            while (this.s.x < n11) {
                if (n9 == this.h[this.k].b.width - 1 && this.s.width + this.s.x < rectangle2.width) {
                    this.s.width = rectangle2.width - this.s.x;
                }
                final int a;
                if ((a = this.a(b, rectangle2, this.k, n9, n6, this.s, b2)) == 2) {
                    b5 = true;
                }
                final Rectangle s = this.s;
                s.x += this.s.width;
                ++n9;
            }
            this.s.x = (int)Math.ceil(n7);
            final Rectangle s2 = this.s;
            s2.y += this.s.height;
            ++n6;
            n9 = n5;
        }
        if (b2 && b5) {
            b6 = true;
            this.a(true);
            this.m = this.k;
            this.h[this.m].c();
            if (b4) {
                b3.a();
                this.b(this.k, rectangle2, rectangle);
                b3.b();
            }
            else {
                this.a(this.k, rectangle2, rectangle);
                this.h[this.m].a();
                if (!com.iseemedia.apps.tourclients40.resource.a.b && !com.iseemedia.apps.tourclients40.resource.a.c) {
                    b3.a();
                }
            }
        }
        return b6;
    }
    
    public final boolean a(final int n, final Rectangle rectangle, final Rectangle rectangle2) {
        boolean b = false;
        final float n2 = (rectangle.x - rectangle2.x) / rectangle2.width;
        final float n3 = (rectangle.y - rectangle2.y) / rectangle2.height;
        int n4;
        if ((n4 = (int)(n2 * this.h[n].c.width / 64.0f)) < 0) {
            n4 = 0;
        }
        int n5;
        if ((n5 = (int)(n3 * this.h[n].c.height / 64.0f)) < 0) {
            n5 = 0;
        }
        int n6;
        if ((n6 = (int)((n2 + rectangle.width / rectangle2.width) * this.h[n].c.width / 64.0f)) >= this.h[n].b.width - 1) {
            n6 = this.h[n].b.width - 1;
        }
        int n7;
        if ((n7 = (int)((n3 + rectangle.height / rectangle2.height) * this.h[n].c.height / 64.0f)) >= this.h[n].b.height - 1) {
            n7 = this.h[n].b.height - 1;
        }
        for (int i = 0; i <= 1 + (n7 - n5) / 2; ++i) {
            final int n8;
            if ((n8 = (n7 + n5) / 2 + i) <= n7) {
                for (int j = n4; j <= n6; ++j) {
                    final Long n9 = new Long(this.a(n, j, n8));
                    if (this.h[n].a[j][n8] == null && !this.p.containsKey(n9)) {
                        this.h[n].a(j, n8);
                        this.p.put(n9, n9);
                        b = true;
                    }
                }
            }
            final int n10;
            if ((n10 = (n7 + n5) / 2 - i) >= n5 && i > 0) {
                for (int k = n4; k <= n6; ++k) {
                    final Long n11 = new Long(this.a(n, k, n10));
                    if (this.h[n].a[k][n10] == null && !this.p.containsKey(n11)) {
                        this.h[n].a(k, n10);
                        this.p.put(n11, n11);
                        b = true;
                    }
                }
            }
        }
        this.h[this.m].a();
        return b;
    }
    
    public final int a(final com.iseemedia.image.b b, final Rectangle rectangle, final int n, final int n2, final int n3, final Rectangle rectangle2, final boolean b2) {
        this.v[0] = n;
        this.v[1] = n2;
        this.v[2] = n3;
        if (rectangle2.y + rectangle2.height < 0 || rectangle2.y > rectangle.height || rectangle2.x + rectangle2.width < 0 || rectangle2.x > rectangle.width) {
            return 3;
        }
        final int[] a = this.a(this.v, b2);
        final int n4 = this.v[3];
        final float n5 = 1.0f / rectangle2.width;
        final float n6 = 1.0f / rectangle2.height;
        float n7 = 0.0f;
        float n8 = 0.0f;
        float n9 = 0.0f;
        float n10 = 0.0f;
        float n11 = 1.0f;
        if (this.v[0] < n) {
            final int n12 = this.v[1] << n - this.v[0];
            final int n13 = this.v[1] + 1 << n - this.v[0];
            final int n14 = this.v[2] << n - this.v[0];
            final int n15 = this.v[2] + 1 << n - this.v[0];
            n11 = 1.0f / (1 << n - this.v[0]);
            n7 = (n2 - n12) / (n13 - n12);
            n8 = (n13 - (n2 + 1)) / (n13 - n12);
            n9 = (n3 - n14) / (n15 - n14);
            n10 = (n15 - (n3 + 1)) / (n15 - n14);
        }
        float n16 = 0.0f;
        float n17 = 0.0f;
        if (this.v[1] == this.h[this.v[0]].b.width - 1 && this.h[this.v[0]].c.width % 64 > 0) {
            n16 = (64.0f - this.h[this.v[0]].c.width % 64) / 64.0f - n8;
            if (this.h[this.j].c.width % this.h[this.v[0]].c.width > 0) {
                n16 -= 0.015625f;
            }
            if (n16 < 0.0f) {
                n16 = 0.0f;
            }
        }
        if (this.v[2] == this.h[this.v[0]].b.height - 1 && this.h[this.v[0]].c.height % 64 > 0) {
            n17 = (64.0f - this.h[this.v[0]].c.height % 64) / 64.0f - n10;
            if (this.h[this.j].c.height % this.h[this.v[0]].c.height > 0) {
                n17 -= 0.015625f;
            }
            if (n17 < 0.0f) {
                n17 = 0.0f;
            }
        }
        int n18 = 0;
        int n19 = 0;
        int n20 = 0;
        int n21 = 0;
        if (rectangle2.x < 0) {
            n18 = -rectangle2.x;
        }
        if (rectangle2.x + rectangle2.width > rectangle.width) {
            n19 = rectangle2.x + rectangle2.width - rectangle.width;
        }
        if (rectangle2.y < 0) {
            n20 = -rectangle2.y;
        }
        if (rectangle2.y + rectangle2.height > rectangle.height) {
            n21 = rectangle2.y + rectangle2.height - rectangle.height;
        }
        this.u.x = rectangle2.x;
        this.u.y = rectangle2.y;
        this.u.width = rectangle2.width;
        this.u.height = rectangle2.height;
        if (this.u.y < 0) {
            final Rectangle u = this.u;
            u.height += this.u.y;
            this.u.y = 0;
        }
        if (this.u.y + this.u.height > rectangle.height) {
            this.u.height = rectangle.height - this.u.y;
        }
        if (this.u.x < 0) {
            final Rectangle u2 = this.u;
            u2.width += this.u.x;
            this.u.x = 0;
        }
        if (this.u.x + this.u.width > rectangle.width) {
            this.u.width = rectangle.width - this.u.x;
        }
        final float n22 = n18 * n11 * n5;
        float n23 = n19 * n11 * n5;
        final float n24 = n20 * n11 * n6;
        float n25;
        if ((n25 = n21 * n11 * n6) < n17) {
            n25 = 0.0f;
        }
        else {
            n17 = 0.0f;
        }
        if (n23 < n16) {
            n23 = 0.0f;
        }
        else {
            n16 = 0.0f;
        }
        this.t.x = (int)((n22 + n7) * 64.0f);
        this.t.y = (int)((n24 + n9) * 64.0f);
        this.t.width = 64 - (int)Math.ceil((n23 + n8 + n16) * 64.0f) - this.t.x;
        this.t.height = 64 - (int)Math.ceil((n25 + n10 + n17) * 64.0f) - this.t.y;
        a(a, this.t, b, this.u);
        return n4;
    }
    
    public static final void a(final int[] array, final Rectangle rectangle, final com.iseemedia.image.b b, final Rectangle rectangle2) {
        if (rectangle2.x >= b.c || rectangle2.x + rectangle2.width > b.c) {
            return;
        }
        if (rectangle2.y >= b.d || rectangle2.y + rectangle2.height > b.d) {
            return;
        }
        if (rectangle2.width <= 0 || rectangle2.height <= 0 || rectangle.height < 0 || rectangle.width < 0) {
            return;
        }
        if (rectangle.y < 0) {
            rectangle.y = 0;
        }
        else if (rectangle.y > 64) {
            rectangle.y = 64;
        }
        if (rectangle.height + rectangle.y > 64) {
            rectangle.height = 64 - rectangle.y;
        }
        if (rectangle.x < 0) {
            rectangle.x = 0;
        }
        else if (rectangle.x > 64) {
            rectangle.x = 64;
        }
        if (rectangle.width + rectangle.x > 64) {
            rectangle.width = 64 - rectangle.x;
        }
        final int n = (int)(256.0f * (rectangle.width / rectangle2.width));
        final int n2 = (int)(256.0f * (rectangle.height / rectangle2.height));
        int i;
        final int n3 = (i = b.c * rectangle2.y + rectangle2.x) + rectangle2.height * b.c;
        int n4 = 0;
        final int n5 = 64 * rectangle.y + rectangle.x << 8;
        while (i < n3) {
            int n6 = n5 + (n4 >> 8 << 14);
            for (int n7 = i + rectangle2.width, j = i; j < n7; ++j) {
                b.b[j] = array[n6 >> 8];
                n6 += n;
            }
            n4 += n2;
            i += b.c;
        }
    }
    
    public final boolean a(final Dimension dimension) {
        if (this.g != null && this.g.width > 0 && this.l) {
            dimension.width = this.g.width;
            dimension.height = this.g.height;
            return true;
        }
        return false;
    }
    
    public final void a(final boolean b) {
        try {
            if (this.m >= 0) {
                this.h[this.m].b();
                this.m = -1;
            }
            if (b) {
                this.p.clear();
            }
        }
        catch (Exception ex) {}
    }
    
    public final void c() {
        for (int i = 0; i <= this.j; ++i) {
            this.h[i] = new com.iseemedia.apps.tourclients40.iip.a(i, this.a(i), this.b(i), this.e, this.f, this.b, this.i, this.x);
        }
    }
    
    public final Dimension a(final int n) {
        final double pow = Math.pow(2.0, this.j - n);
        final int n2 = (int)(this.g.width / pow + 1.0 - 1.0 / pow);
        final int n3 = (int)(this.g.height / pow + 1.0 - 1.0 / pow);
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
    
    public final Dimension b(final int n) {
        final int n2 = (int)Math.pow(2.0, this.j - n);
        return new Dimension(this.g.width / n2, this.g.height / n2);
    }
    
    public final void d() throws d {
        DataInputStream e;
        try {
            e = this.e();
        }
        catch (SecurityException ex3) {
            throw new d("Viewer must reside on Image Server's IP address");
        }
        catch (Exception ex4) {
            throw new d("Invalid IIP Server in URL parameter");
        }
        try {
            this.a(e);
        }
        catch (Exception ex2) {
            final Exception ex = ex2;
            if (ex2 instanceof d) {
                throw (d)ex;
            }
            throw new d("Internal parsing failure");
        }
    }
    
    public final DataInputStream e() throws Exception {
        final StringBuffer append = new StringBuffer(200).append("fif=").append(this.f).append("&obj=basic-info&obj=comp-group,2,*&til=0,*&obj=iip,1.0");
        this.e = new a(this.d);
        return this.e.a(append.toString());
    }
    
    public final void a(final DataInputStream dataInputStream) throws Exception {
        final Vector vector = new Vector();
        final Hashtable hashtable = new Hashtable();
        final Hashtable hashtable2 = new Hashtable<String, Object>();
        try {
            while (true) {
                final g b;
                if ((b = this.b(dataInputStream)) != null) {
                    if (b.a == null) {
                        continue;
                    }
                    final String lowerCase = new String(b.a, 0).trim().toLowerCase();
                    String s = null;
                    if (b.b instanceof String) {
                        s = (String)b.b;
                    }
                    if (lowerCase.equals("fpx-version") || lowerCase.equals("iip")) {
                        new Float(s);
                    }
                    else if (lowerCase.equals("max-size")) {
                        this.g = new Dimension(new Integer(s.substring(0, s.indexOf(32))), new Integer(s.substring(s.indexOf(32) + 1)));
                    }
                    else if (lowerCase.equals("resolution-number")) {
                        this.j = new Integer(s) - 1;
                    }
                    else if (lowerCase.startsWith("comp-group")) {
                        hashtable2.put(lowerCase, b.b);
                    }
                    else if (lowerCase.startsWith("tile")) {
                        this.a(vector, b.a, (byte[])b.b);
                    }
                    else if (lowerCase.startsWith("error") && b.b instanceof byte[]) {
                        this.a(hashtable, (byte[])b.b);
                    }
                    else {
                        if (lowerCase.startsWith("colorspace") && s.charAt(6) != '3') {
                            break;
                        }
                        continue;
                    }
                }
            }
            throw new d("Only three-channel images are supported");
        }
        catch (EOFException ex) {
            dataInputStream.close();
            this.a(hashtable);
            if (!hashtable2.isEmpty()) {
                this.b(hashtable2);
            }
            if (!vector.isEmpty()) {
                this.a(vector);
            }
        }
    }
    
    public final void a(final Hashtable hashtable, final byte[] array) {
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
    
    public final void a(final Vector vector, final byte[] array, final byte[] array2) {
        final StringBuffer append;
        final int length = (append = new StringBuffer("/").append(array2.length).append(":")).length();
        final byte[] array3 = new byte[array.length + array2.length + length];
        System.arraycopy(array, 0, array3, 0, array.length);
        System.arraycopy(array2, 0, array3, array.length + length, array2.length);
        for (int i = 0; i < length; ++i) {
            array3[array.length + i] = (byte)append.charAt(i);
        }
        vector.addElement(array3);
    }
    
    public final void a(final Hashtable hashtable) throws d {
        if (this.g == null) {
            final StringBuffer sb = new StringBuffer("Error:");
            final Enumeration<String> keys = hashtable.keys();
            while (keys.hasMoreElements()) {
                sb.append(" ").append(keys.nextElement());
            }
            if (sb.equals("Error:")) {
                sb.append(" Internal parsing failure.");
            }
            throw new d(sb.toString());
        }
    }
    
    public final void b(final Hashtable hashtable) {
        final Enumeration<String> keys = (Enumeration<String>)hashtable.keys();
        while (keys.hasMoreElements()) {
            final String s = keys.nextElement();
            final byte[] array;
            if ((array = hashtable.get(s)).length >= 4) {
                System.arraycopy(array, 2, this.i[new Integer(s.substring(s.lastIndexOf(",") + 1)) - 1] = new byte[array.length - 4], 0, array.length - 4);
            }
        }
    }
    
    public final void a(final Vector vector) throws d {
        final Dimension a = this.a(this.w);
        final URL a2 = this.e.a();
        if (null != this.n) {
            this.n = null;
        }
        this.n = new int[a.width][a.height][];
        for (int i = 0; i < a.width; ++i) {
            for (int j = 0; j < a.height; ++j) {
                try {
                    this.n[i][j] = new int[4096];
                }
                catch (Exception ex3) {
                    throw new d("Memory allocation error");
                }
            }
        }
        final Enumeration<byte[]> elements = vector.elements();
        while (elements.hasMoreElements()) {
            final byte[] array = elements.nextElement();
            try {
                final c c;
                if (!c.a(a2, this.n[(c = new c(new DataInputStream(new ByteArrayInputStream(array)), this.i, this.w)).a() % a.width][c.a() / a.width])) {
                    throw new d("Internal parsing failure");
                }
                continue;
            }
            catch (Exception ex2) {
                final Exception ex = ex2;
                if (ex2 instanceof d) {
                    throw (d)ex;
                }
                throw new d("Internal parsing failure");
            }
        }
    }
    
    public final g b(final DataInputStream dataInputStream) throws Exception {
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
            return new g(array2, array3);
        }
        return new g(array2, dataInputStream.readLine().trim());
    }
    
    public final void c(final int m) {
        this.m = m;
        this.h[this.m].c();
    }
    
    public final void a(final int n, final Rectangle rectangle) {
        this.a(n, rectangle, true);
    }
    
    public final void a(final int n, final Rectangle rectangle, final boolean b) {
        for (int i = 0; i <= rectangle.height / 2; ++i) {
            final int n2;
            if ((n2 = rectangle.y + rectangle.height / 2 + i) < rectangle.y + rectangle.height) {
                this.h[n].a(n2, rectangle.x, rectangle.x + rectangle.width - 1);
                for (int j = rectangle.x; j < rectangle.x + rectangle.width; ++j) {
                    if (this.h[n].a[j][n2] == null) {
                        final Long n3 = new Long(this.a(n, j, n2));
                        this.p.put(n3, n3);
                    }
                }
            }
            final int n4;
            if ((n4 = rectangle.y + rectangle.height / 2 - i) >= rectangle.y && i > 0) {
                this.h[n].a(n4, rectangle.x, rectangle.x + rectangle.width - 1);
                for (int k = rectangle.x; k < rectangle.x + rectangle.width; ++k) {
                    if (this.h[n].a[k][n4] == null) {
                        final Long n5 = new Long(this.a(n, k, n4));
                        this.p.put(n5, n5);
                    }
                }
            }
        }
        if (b) {
            this.h[this.m].a();
        }
    }
    
    public final void b(final int n, final Rectangle rectangle) {
        this.b(n, rectangle, true);
    }
    
    public final void b(final int n, final Rectangle rectangle, final boolean b) {
        for (int i = 0; i <= rectangle.height / 2; ++i) {
            final int n2;
            if ((n2 = rectangle.y + rectangle.height / 2 + i) < rectangle.y + rectangle.height) {
                this.h[n].a(n2, rectangle.x, rectangle.x + rectangle.width - 1);
                for (int j = rectangle.x; j < rectangle.x + rectangle.width; ++j) {
                    if (this.h[n].a[j][n2] == null) {
                        final Long n3 = new Long(this.a(n, j, n2));
                        this.p.put(n3, n3);
                    }
                }
            }
            final int n4;
            if ((n4 = rectangle.y + rectangle.height / 2 - i) >= rectangle.y && i > 0) {
                this.h[n].a(n4, rectangle.x, rectangle.x + rectangle.width - 1);
                for (int k = rectangle.x; k < rectangle.x + rectangle.width; ++k) {
                    if (this.h[n].a[k][n4] == null) {
                        final Long n5 = new Long(this.a(n, k, n4));
                        this.p.put(n5, n5);
                    }
                }
            }
        }
        if (b) {
            this.h[this.m].run();
        }
    }
    
    public final boolean b(final int n, final Rectangle rectangle, final Rectangle rectangle2) {
        boolean b = false;
        final float n2 = (rectangle.x - rectangle2.x) / rectangle2.width;
        final float n3 = (rectangle.y - rectangle2.y) / rectangle2.height;
        int n4;
        if ((n4 = (int)(n2 * this.h[n].c.width / 64.0f)) < 0) {
            n4 = 0;
        }
        int n5;
        if ((n5 = (int)(n3 * this.h[n].c.height / 64.0f)) < 0) {
            n5 = 0;
        }
        int n6;
        if ((n6 = (int)((n2 + rectangle.width / rectangle2.width) * this.h[n].c.width / 64.0f)) >= this.h[n].b.width - 1) {
            n6 = this.h[n].b.width - 1;
        }
        int n7;
        if ((n7 = (int)((n3 + rectangle.height / rectangle2.height) * this.h[n].c.height / 64.0f)) >= this.h[n].b.height - 1) {
            n7 = this.h[n].b.height - 1;
        }
        for (int i = 0; i <= 1 + (n7 - n5) / 2; ++i) {
            final int n8;
            if ((n8 = (n7 + n5) / 2 + i) <= n7) {
                for (int j = n4; j <= n6; ++j) {
                    final Long n9 = new Long(this.a(n, j, n8));
                    if (this.h[n].a[j][n8] == null && !this.p.containsKey(n9)) {
                        this.h[n].a(j, n8);
                        this.p.put(n9, n9);
                        b = true;
                    }
                }
            }
            final int n10;
            if ((n10 = (n7 + n5) / 2 - i) >= n5 && i > 0) {
                for (int k = n4; k <= n6; ++k) {
                    final Long n11 = new Long(this.a(n, k, n10));
                    if (this.h[n].a[k][n10] == null && !this.p.containsKey(n11)) {
                        this.h[n].a(k, n10);
                        this.p.put(n11, n11);
                        b = true;
                    }
                }
            }
        }
        this.h[this.m].run();
        return b;
    }
    
    public final DataInputStream f() throws Exception {
        final StringBuffer append = new StringBuffer(200).append("fif=").append(this.f).append("&til=" + String.valueOf(this.w) + ",*&obj=iip,1.0");
        this.e = new a(this.d);
        return this.e.a(append.toString());
    }
    
    public final void d(final int w) throws d {
        this.w = w;
        DataInputStream f;
        try {
            f = this.f();
        }
        catch (SecurityException ex3) {
            throw new d("Viewer must reside on Image Server's IP address");
        }
        catch (Exception ex4) {
            throw new d("Invalid IIP Server in URL parameter");
        }
        try {
            this.a(f);
        }
        catch (Exception ex2) {
            final Exception ex = ex2;
            if (ex2 instanceof d) {
                throw (d)ex;
            }
            throw new d("Internal parsing failure");
        }
    }
    
    public final void g() {
        for (int i = 0; i < this.a; ++i) {
            this.o[i].a = null;
            this.o[i] = null;
        }
        for (int j = 0; j <= this.j; ++j) {
            for (int k = 0; k < this.h[j].b.width; ++k) {
                for (int l = 0; l < this.h[j].b.height; ++l) {
                    this.h[j].a[k][l] = null;
                }
            }
            this.h[j].a = null;
            this.h[j] = null;
        }
        this.h = null;
        this.o = null;
        this.e = null;
    }
}
