// 
// Decompiled by Procyon v0.5.30
// 

package ji.filter;

import ji.v1event.af;
import ji.document.ad;
import java.awt.Component;
import ji.v1event.a6;
import ji.io.ac;
import ji.util.d;
import ji.io.hp;
import ji.image.dx;

public class e4 extends e5
{
    private String[] a;
    
    public e4() {
        this.a = new String[] { "doc", "htm", "html", "image", "jp2", "jp", "djvu", "png", "jpeg", "jpg", "pix", "zip", "fob", "xls", "txt", "text", "xls", "pdf" };
    }
    
    protected boolean a(final aa7 aa7, final dx dx, final int g, final hp hp) throws Exception {
        this.a(dx, "BMPCoreHeader", "Yes (OS/2)");
        aa7.g = g;
        aa7.h = hp.d();
        aa7.i = hp.d();
        aa7.j = hp.d();
        aa7.k = hp.d();
        this.a(dx, "bcSize", "".concat(String.valueOf(String.valueOf(aa7.g))));
        this.a(dx, "bcWidth", "".concat(String.valueOf(String.valueOf(aa7.h))));
        this.a(dx, "bcHeight", "".concat(String.valueOf(String.valueOf(aa7.i))));
        this.a(dx, "bcPlanes", "".concat(String.valueOf(String.valueOf(aa7.j))));
        this.a(dx, "bcBitCount", "".concat(String.valueOf(String.valueOf(aa7.k))));
        switch (aa7.k) {
            case 1: {
                aa7.m = new aa9[2];
                dx.z = 1;
                dx.aa = 1;
                break;
            }
            case 4: {
                aa7.m = new aa9[16];
                dx.z = 4;
                dx.aa = 1;
                break;
            }
            case 8: {
                aa7.m = new aa9[256];
                dx.z = 8;
                dx.aa = 1;
                break;
            }
            case 24: {
                aa7.m = null;
                dx.z = 8;
                dx.aa = 3;
                break;
            }
            default: {
                hp.a();
                return false;
            }
        }
        this.a(dx, "bitsPerSample", "".concat(String.valueOf(String.valueOf(dx.z))));
        this.a(dx, "samplesPerPixel", "".concat(String.valueOf(String.valueOf(dx.aa))));
        aa7.l = (int)(hp.g() - aa7.e);
        if (aa7.m != null) {
            for (int i = 0; i < aa7.m.length; ++i) {
                aa7.m[i] = new aa9();
                aa7.m[i].a = (byte)(hp.c() & 0xFF);
                aa7.m[i].b = (byte)(hp.c() & 0xFF);
                aa7.m[i].c = (byte)(hp.c() & 0xFF);
            }
        }
        dx.l = "BMP (OS/2)";
        dx.ac = 100.0;
        dx.ad = 100.0;
        this.a(dx, "BitCount", "".concat(String.valueOf(String.valueOf(aa7.k))));
        this.a(dx, "Planes", "".concat(String.valueOf(String.valueOf(aa7.j))));
        dx.m = aa7.h;
        dx.n = aa7.i;
        return true;
    }
    
    public final dx loadImageHeaderInternal(final fh fh) throws Exception {
        final String m = fh.m;
        final String c = ji.util.d.c(fh.f, m, fh.n);
        if (c != null) {
            final String lowerCase = c.toLowerCase();
            for (int i = 0; i < this.a.length; ++i) {
                if (lowerCase.equals(this.a[i].toLowerCase())) {
                    return null;
                }
            }
        }
        if (m != null) {
            final String lowerCase2 = m.toLowerCase();
            for (int j = 0; j < this.a.length; ++j) {
                if (lowerCase2.equals(this.a[j].toLowerCase())) {
                    return null;
                }
            }
        }
        final hp hp = new hp(fh.b);
        final dx dx = new dx();
        dx.an = 1;
        dx.l = "BMP";
        dx.a = 3;
        final aa7 aa7 = new aa7();
        hp.a(dx.an);
        hp.a(0L);
        aa7.a = hp.d();
        if (aa7.a != 19778) {
            hp.a();
            return null;
        }
        aa7.b = hp.e();
        aa7.c = hp.d();
        aa7.d = hp.d();
        aa7.e = hp.e();
        final int e = hp.e();
        if (e == 12) {
            aa7.f = true;
        }
        else {
            if (e != 40) {
                hp.a();
                return null;
            }
            aa7.f = false;
        }
        if (aa7.f) {
            if (!this.a(aa7, dx, e, hp)) {
                return null;
            }
        }
        else {
            this.a(dx, "BMPCoreHeader", "No (Windows)");
            if (!this.a(aa7, dx, e, hp)) {
                return null;
            }
            if (aa7.g == 0) {
                aa7.g = (int)(hp.g() - aa7.e);
            }
            aa7.l = aa7.g;
            dx.l = "BMP (WIndows)";
        }
        dx.u = 1;
        dx.i = fh.b.v();
        dx.ax = aa7.a();
        this.a(dx, "Width", "".concat(String.valueOf(String.valueOf(dx.m))));
        this.a(dx, "Height", "".concat(String.valueOf(String.valueOf(dx.n))));
        dx.am = dx.z * dx.aa;
        dx.an = 0;
        dx.ar = false;
        dx.at = 0;
        hp.a();
        return dx;
    }
    
    protected int[] a(final aa7 aa7, final int n) {
        int[] array = null;
        if (aa7.m != null) {
            if (n < 2) {
                if (aa7.m[0].c == 0) {}
            }
            else {
                array = new int[aa7.m.length];
                for (int i = 0; i < aa7.m.length; ++i) {
                    array[i] = (0xFF000000 | (aa7.m[i].c & 0xFF) << 16 | (aa7.m[i].b & 0xFF) << 8 | (aa7.m[i].a & 0xFF));
                }
            }
        }
        return array;
    }
    
    public final int[] a(final ac ac, final dx dx) throws Exception {
        int[] array = null;
        try {
            final aa7 aa7 = (aa7)dx.ax;
            if (aa7.f) {
                array = this.a(aa7, dx.am);
            }
            else {
                array = this.a(aa7, dx.am);
            }
        }
        finally {}
        return array;
    }
    
    public final void fillDibInternal(final fh fh) throws Exception {
        try {
            super.c = true;
            super.b = false;
            final byte[] array = null;
            boolean b = false;
            if (fh.g != null) {
                if (super.c == null) {
                    super.c = new a6(this, 4, "");
                }
                if (fh.d.am >= 24 && ji.util.d.du() && fh.d.ak) {
                    super.c = new a6(this, 23, "");
                }
            }
            final hp hp = new hp(fh.b);
            hp.a(fh.d.an);
            final aa7 aa7 = (aa7)fh.d.ax;
            switch (fh.d.am) {
                case 1: {
                    fh.c.b(1, true, fh.o);
                    break;
                }
                case 4: {
                    fh.c.b(2, true, fh.o);
                    break;
                }
                case 8: {
                    fh.c.b(3, true, fh.o);
                    break;
                }
                case 24: {
                    fh.c.b(4, false, fh.o);
                    break;
                }
            }
            try {
                hp.a((long)aa7.e);
                final int l = aa7.l;
                final byte[] array2 = new byte[l];
                hp.a(array2, 0, l);
                fh.c.a(this.a(fh.b, fh.d), fh.d);
                if (aa7.f) {
                    if (aa7.m != null && fh.d.am < 2 && aa7.m[0].c == 0) {
                        b = true;
                    }
                }
                else if (aa7.m != null && fh.d.am < 2 && aa7.m[0].c == 0) {
                    b = true;
                }
                this.a(fh.c, array2, aa7, l, fh.d, fh.g, array, fh.o, b);
            }
            finally {
                hp.a();
            }
        }
        finally {
            super.c = false;
        }
    }
    
    public int isFileType(final ac ac, final String s, final String s2, final ad ad, final boolean b, final String s3, final String s4, final af af, final boolean b2) {
        hp hp = null;
        try {
            hp = new hp(ac);
            hp.a(1);
            hp.a(0L);
            if (hp.d() != 19778) {
                return 0;
            }
            hp.e();
            hp.d();
            hp.d();
            hp.e();
            final int e = hp.e();
            if (e != 12 && e != 40) {
                return 0;
            }
            return 2;
        }
        catch (Exception ex) {
            ji.util.d.a(ex);
            return 0;
        }
        finally {
            if (hp != null) {
                hp.a();
            }
        }
    }
    
    class aa7 extends aa8
    {
        int a;
        int b;
        int c;
        int d;
        int e;
        boolean f;
        int g;
        int h;
        int i;
        int j;
        int k;
        int l;
        aa9[] m;
        
        aa7() {
            this.a = 0;
            this.b = 0;
            this.c = 0;
            this.d = 0;
            this.e = 0;
            this.f = false;
            this.m = null;
        }
        
        public aa7 a() {
            final aa7 aa7 = (aa7)super.a(new aa7());
            aa7.a = this.a;
            aa7.b = this.b;
            aa7.c = this.c;
            aa7.d = this.d;
            aa7.e = this.e;
            aa7.l = this.l;
            aa7.f = this.f;
            aa7.g = this.g;
            aa7.h = this.h;
            aa7.i = this.i;
            aa7.j = this.j;
            aa7.k = this.k;
            if (this.m != null) {
                aa7.m = new aa9[this.m.length];
                for (int i = 0; i < this.m.length; ++i) {
                    aa7.m[i] = new aa9();
                    aa7.m[i].a = this.m[i].a;
                    aa7.m[i].b = this.m[i].b;
                    aa7.m[i].c = this.m[i].c;
                }
            }
            return aa7;
        }
    }
    
    class aa9
    {
        byte a;
        byte b;
        byte c;
        
        aa9(final e4 e4) {
        }
    }
}
