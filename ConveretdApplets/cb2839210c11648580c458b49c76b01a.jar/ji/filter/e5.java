// 
// Decompiled by Procyon v0.5.30
// 

package ji.filter;

import java.awt.Color;
import ji.document.ad;
import ji.util.e;
import ji.v1event.af;
import java.awt.Component;
import ji.image.ds;
import ji.io.ac;
import ji.util.d;
import ji.io.h;
import ji.util.i;
import ji.io.hp;
import java.util.Hashtable;
import ji.image.dx;
import ji.v1event.a6;

public class e5 extends cj
{
    protected int a;
    protected int b;
    protected a6 c;
    private String[] d;
    
    public e5() {
        this.a = 0;
        this.b = 0;
        this.c = null;
        this.d = new String[] { "doc", "htm", "html", "image", "jp2", "jp", "djvu", "png", "jpeg", "jpg", "pix", "zip", "fob", "xls", "txt", "text", "xls", "pdf" };
    }
    
    protected final void a(final dx dx, final String s, final String s2) {
        try {
            if (dx.bk == null) {
                dx.bk = new Hashtable(5);
            }
            dx.bk.put(s, s2);
        }
        catch (Exception ex) {}
    }
    
    protected boolean a(final aa8 aa8, final dx dx, final int a, final hp hp) throws Exception {
        aa8.a = a;
        aa8.b = hp.e();
        aa8.c = hp.e();
        aa8.d = hp.d();
        aa8.e = hp.d();
        aa8.f = hp.e();
        aa8.g = hp.e();
        aa8.h = hp.e();
        aa8.i = hp.e();
        aa8.j = hp.e();
        aa8.k = hp.e();
        this.a(dx, "biSize", "".concat(String.valueOf(String.valueOf(aa8.a))));
        this.a(dx, "biWidth", "".concat(String.valueOf(String.valueOf(aa8.b))));
        this.a(dx, "biHeight", "".concat(String.valueOf(String.valueOf(aa8.c))));
        this.a(dx, "biBitCount", "".concat(String.valueOf(String.valueOf(aa8.e))));
        this.a(dx, "biCompression", "".concat(String.valueOf(String.valueOf(aa8.f))));
        this.a(dx, "biSizeImage", "".concat(String.valueOf(String.valueOf(aa8.g))));
        this.a(dx, "biXPelsPerMeter", "".concat(String.valueOf(String.valueOf(aa8.h))));
        this.a(dx, "biYPelsPerMeter", "".concat(String.valueOf(String.valueOf(aa8.i))));
        this.a(dx, "biClrUsed", "".concat(String.valueOf(String.valueOf(aa8.j))));
        this.a(dx, "biClrImportant", "".concat(String.valueOf(String.valueOf(aa8.k))));
        if (i.c(165) && aa8.f == 0 && aa8.e == 0) {
            if (aa8.d != 1) {
                hp.a();
                return false;
            }
            h.d(super.a, "Win-DIB seems to have zero bit count so assuming 24 bit image with RGB compression....");
            aa8.f = 0;
            aa8.e = 24;
        }
        if (aa8.f < 0 || aa8.f > 2) {
            hp.a();
            return false;
        }
        if (aa8.d != 1 && aa8.d != 2 && !i.c(165)) {
            hp.a();
            return false;
        }
        switch (aa8.e) {
            case 1: {
                aa8.m = new aba[2];
                dx.z = 1;
                dx.aa = 1;
                aa8.a += 8;
                break;
            }
            case 4: {
                aa8.m = new aba[16];
                dx.z = 4;
                dx.aa = 1;
                aa8.a += 64;
                break;
            }
            case 8: {
                aa8.m = new aba[256];
                dx.z = 8;
                dx.aa = 1;
                aa8.a += 1024;
                break;
            }
            case 24: {
                aa8.m = null;
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
        if (aa8.m != null) {
            if (a == 124) {
                hp.a((long)a);
            }
            for (int i = 0; i < aa8.m.length; ++i) {
                aa8.m[i] = new aba();
                aa8.m[i].a = (byte)(hp.c() & 0xFF);
                aa8.m[i].b = (byte)(hp.c() & 0xFF);
                aa8.m[i].c = (byte)(hp.c() & 0xFF);
                aa8.m[i].d = (byte)(hp.c() & 0xFF);
            }
        }
        this.a(dx, "BitCount", "".concat(String.valueOf(String.valueOf(aa8.e))));
        this.a(dx, "Planes", "".concat(String.valueOf(String.valueOf(aa8.d))));
        switch (aa8.f) {
            case 0: {
                this.a(dx, "Compression", "RGB");
                break;
            }
            case 1: {
                this.a(dx, "Compression", "RLE8");
                break;
            }
            case 2: {
                this.a(dx, "Compression", "RLE4");
                break;
            }
        }
        if (aa8.h > 0) {
            dx.ac = aa8.h / 1000.0 * 25.4;
        }
        else {
            dx.ac = 100.0;
        }
        if (aa8.i > 0) {
            dx.ad = aa8.i / 1000.0 * 25.4;
        }
        else {
            dx.ad = 100.0;
        }
        dx.m = aa8.b;
        dx.n = aa8.c;
        return true;
    }
    
    public final String getFilterName() {
        return "jiFilterWinDib";
    }
    
    public dx loadImageHeaderInternal(final fh fh) throws Exception {
        final String m = fh.m;
        final String c = ji.util.d.c(fh.f, m, fh.n);
        if (c != null) {
            final String lowerCase = c.toLowerCase();
            for (int i = 0; i < this.d.length; ++i) {
                if (lowerCase.equals(this.d[i].toLowerCase())) {
                    return null;
                }
            }
        }
        if (m != null) {
            final String lowerCase2 = m.toLowerCase();
            for (int j = 0; j < this.d.length; ++j) {
                if (lowerCase2.equals(this.d[j].toLowerCase())) {
                    return null;
                }
            }
        }
        final hp hp = new hp(fh.b);
        final dx dx = new dx();
        dx.an = 1;
        dx.l = "Windows DIB";
        final aa8 aa8 = new aa8();
        hp.a(dx.an);
        hp.a(0L);
        final int e = hp.e();
        this.a(dx, "BMPCoreHeader", "No (Windows)");
        if (!this.a(aa8, dx, e, hp)) {
            return null;
        }
        dx.l = "BMP (Windows)";
        dx.a = 4;
        dx.u = 1;
        dx.i = fh.b.v();
        dx.ax = aa8.a();
        this.a(dx, "Width", "".concat(String.valueOf(String.valueOf(dx.m))));
        this.a(dx, "Height", "".concat(String.valueOf(String.valueOf(dx.n))));
        dx.am = dx.z * dx.aa;
        dx.an = 0;
        dx.ar = false;
        dx.at = 0;
        hp.a();
        return dx;
    }
    
    protected int[] a(final aa8 aa8, final int n) {
        int[] array = null;
        if (aa8.m != null) {
            if (n < 2) {
                if (aa8.m[0].c == 0) {}
            }
            else {
                array = new int[aa8.m.length];
                for (int i = 0; i < aa8.m.length; ++i) {
                    array[i] = (0xFF000000 | (aa8.m[i].c & 0xFF) << 16 | (aa8.m[i].b & 0xFF) << 8 | (aa8.m[i].a & 0xFF));
                }
            }
        }
        return array;
    }
    
    public int[] getPalette(final ac ac, final dx dx, final String s) throws Exception {
        int[] a = null;
        try {
            a = this.a((aa8)dx.ax, dx.am);
        }
        finally {}
        return a;
    }
    
    public aa8 a(final dx dx, final int[] array) {
        final aa8 aa8 = new aa8();
        if (dx.z == 1 && dx.aa == 1) {
            aa8.e = 1;
            aa8.f = 0;
            aa8.m = new aba[2];
            aa8.j = 2;
            aa8.k = 2;
        }
        else if (dx.z == 4 && dx.aa == 1) {
            aa8.e = 4;
            aa8.f = 0;
            aa8.m = new aba[16];
        }
        else if (dx.z == 8 && dx.aa == 1) {
            aa8.e = 8;
            aa8.f = 0;
            aa8.m = new aba[256];
        }
        else if (dx.z == 8 && dx.aa == 3) {
            aa8.e = 24;
            aa8.f = 0;
        }
        else if (dx.z == 24 && dx.aa == 3) {
            aa8.e = 24;
            aa8.f = 0;
        }
        else {
            h.d(super.a, String.valueOf(String.valueOf(new StringBuffer("Unable to convert imager to WinDIB! bitsPerSample=").append(dx.z).append(", samplesPerPixel=").append(dx.aa))));
            aa8.e = 24;
            aa8.f = 0;
        }
        final int m = dx.m;
        aa8.c = dx.n;
        aa8.b = m;
        aa8.d = 1;
        aa8.a = 124;
        aa8.h = (int)(dx.ac / 25.4 * 1000.0);
        aa8.i = (int)(dx.ad / 25.4 * 1000.0);
        if (aa8.m != null) {
            for (int i = 0; i < aa8.m.length; ++i) {
                aa8.m[i] = new aba();
                aa8.m[i].c = (byte)((array[i] & 0xFF0000) >> 16);
                aa8.m[i].b = (byte)((array[i] & 0xFF00) >> 8);
                aa8.m[i].a = (byte)(array[i] & 0xFF);
            }
            aa8.l = 124 + aa8.m.length * 4;
        }
        else {
            aa8.l = 124;
        }
        return aa8;
    }
    
    public byte[] a(final dx dx, final ds ds) throws Exception {
        aa8 aa8 = null;
        final int m = dx.m;
        final int n = dx.n;
        if (i.c(5)) {
            h.d(super.a, "WinDIB: Convert2: width = ".concat(String.valueOf(String.valueOf(m))));
        }
        if (i.c(5)) {
            h.d(super.a, "WinDIB: Convert2: height = ".concat(String.valueOf(String.valueOf(n))));
        }
        byte[] array = null;
        int n2 = 0;
        if (i.c(5)) {
            h.d(super.a, "WinDIB: Convert2: depth = ".concat(String.valueOf(String.valueOf(dx.am))));
        }
        switch (dx.am) {
            case 1: {
                aa8 = this.a(dx, new int[] { 0, 16777215 });
                int i = n - 1;
                final byte[] a = ds.a(i, true);
                if (a == null) {
                    h.c(super.a, "WinDIB: Convert2: No Byte Line");
                    return null;
                }
                try {
                    if (ji.util.i.c(5)) {
                        h.d(super.a, "WinDIB: Convert2: boundary = ".concat(String.valueOf(String.valueOf(a.length))));
                    }
                }
                catch (Exception ex) {
                    ji.util.d.a(ex);
                }
                final int length = a.length;
                aa8.g = length * n;
                array = new byte[aa8.g];
                try {
                    if (ji.util.i.c(5)) {
                        h.d(super.a, "WinDIB: Convert2: image size = ".concat(String.valueOf(String.valueOf(aa8.g))));
                    }
                }
                catch (Exception ex2) {
                    ji.util.d.a(ex2);
                }
                System.arraycopy(a, 0, array, n2, a.length);
                while (i >= 0) {
                    final byte[] a2 = ds.a(i, true);
                    System.arraycopy(a2, 0, array, n2, a2.length);
                    n2 += length;
                    --i;
                }
                for (int j = 0; j < aa8.g; ++j) {
                    array[j] ^= -1;
                }
                break;
            }
            case 8: {
                aa8 = this.a(dx, b(ds));
                final byte[] ag = ds.ag();
                int n3 = m;
                final int n4 = m % 2;
                if (n4 > 0) {
                    n3 += 2 - n4;
                }
                int n5 = n3;
                if (n5 % 4 > 0) {
                    n5 = n5 + 4 - n5 % 4;
                }
                final int n6 = n5 * n;
                int n7 = 0;
                int n8 = n6 - n5;
                final byte[] array2 = new byte[n6];
                for (int k = 0; k < n; ++k) {
                    System.arraycopy(ag, n7, array2, n8, n3);
                    n7 += n3;
                    n8 -= n5;
                }
                aa8.g = array2.length;
                array = array2;
                break;
            }
            case 4: {
                aa8 = this.a(dx, a(ds));
                final byte[] ag2 = ds.ag();
                int n9 = m / 2;
                final int n10 = m % 2;
                if (n10 > 0) {
                    n9 += 2 - n10;
                }
                int n11 = n9;
                if (n11 % 4 > 0) {
                    n11 = n11 + 4 - n11 % 4;
                }
                final int n12 = n11 * n;
                int n13 = 0;
                int n14 = n12 - n11;
                final byte[] array3 = new byte[n12];
                for (int l = 0; l < n; ++l) {
                    System.arraycopy(ag2, n13, array3, n14, n9);
                    n13 += n9;
                    n14 -= n11;
                }
                aa8.g = array3.length;
                array = array3;
                break;
            }
            case 24: {
                aa8 = this.a(dx, ds.l());
                int n15 = m * 3;
                final int n16 = n15 % 4;
                if (n16 > 0) {
                    n15 += 4 - n16;
                }
                final int n17 = n15 - m * 3;
                aa8.g = n * n15;
                array = new byte[aa8.g];
                final int[] array4 = new int[m];
                for (int n18 = n - 1; n18 >= 0; --n18) {
                    ds.c(n18);
                    ds.a(array4, m);
                    for (int n19 = 0; n19 < array4.length; ++n19) {
                        array[n2] = (byte)array4[n19];
                        array[n2 + 1] = (byte)(array4[n19] >> 8);
                        array[n2 + 2] = (byte)(array4[n19] >> 16);
                        n2 += 3;
                    }
                    n2 += n17;
                }
                break;
            }
        }
        try {
            if (i.c(5)) {
                h.d(super.a, "WinDIB: Convert2: Compression = ".concat(String.valueOf(String.valueOf(aa8.f))));
            }
        }
        catch (Exception ex3) {
            ji.util.d.a(ex3);
        }
        if (aa8.f > 0) {
            if (aa8.f == 1) {
                array = this.b(array, aa8.g, dx);
            }
            else if (aa8.f == 2) {
                array = this.a(array, aa8.g, dx);
            }
        }
        final byte[] b = aa8.b();
        final int length2 = b.length;
        final byte[] array5 = new byte[array.length + length2];
        System.arraycopy(b, 0, array5, 0, length2);
        System.arraycopy(array, 0, array5, length2, array.length);
        return array5;
    }
    
    public void fillDibInternal(final fh fh) throws Exception {
        super.c = true;
        try {
            super.b = false;
            final byte[] array = null;
            boolean b = false;
            if (fh.g != null && this.c == null) {
                this.c = new a6(this, 4, "");
            }
            super.b = false;
            final hp hp = new hp(fh.b);
            hp.a(fh.d.an);
            final aa8 aa8 = (aa8)fh.d.ax;
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
                hp.a((long)aa8.a);
                final int g = aa8.g;
                final byte[] array2 = new byte[g];
                hp.a(array2, 0, g);
                fh.c.a(this.getPalette(fh.b, fh.d, fh.u), fh.d);
                if (aa8.m != null && fh.d.am < 2 && aa8.m[0].c == 0) {
                    b = true;
                }
                this.a(fh.c, array2, aa8, g, fh.d, fh.g, array, fh.o, b);
            }
            finally {
                hp.a();
            }
        }
        finally {
            super.c = false;
        }
        if (super.b) {
            fh.c.a(fh.o);
        }
    }
    
    protected void a(final ds ds, byte[] array, final aa8 aa8, final int n, final dx dx, final af af, byte[] array2, final Component component, final boolean b) throws Exception {
        try {
            if (i.c(5)) {
                h.d(super.a, "WinDIB: Read: Compression = ".concat(String.valueOf(String.valueOf(aa8.f))));
            }
            if (i.c(5)) {
                h.d(super.a, "WinDIB: Read: Source size = ".concat(String.valueOf(String.valueOf(array.length))));
            }
        }
        catch (Exception ex4) {}
        if (aa8.f > 0) {
            if (aa8.f == 1) {
                array = this.b(array, n, dx, af);
            }
            else if (aa8.f == 2) {
                array = this.a(array, n, dx, af);
            }
        }
        try {
            if (i.c(5)) {
                h.d(super.a, "WinDIB: Read: depth = ".concat(String.valueOf(String.valueOf(dx.am))));
            }
            if (i.c(5)) {
                h.d(super.a, "WinDIB: Read: width = ".concat(String.valueOf(String.valueOf(dx.m))));
            }
            if (i.c(5)) {
                h.d(super.a, "WinDIB: Read: height = ".concat(String.valueOf(String.valueOf(dx.n))));
            }
        }
        catch (Exception ex5) {}
        switch (dx.am) {
            case 1: {
                int n2 = dx.m / 8;
                if (dx.m % 8 > 0) {
                    ++n2;
                }
                int n3 = n2;
                final int n4 = n3 % 4;
                if (n4 > 0) {
                    n3 += 4 - n4;
                }
                try {
                    if (i.c(5)) {
                        h.d(super.a, "WinDIB: Read: boundary  = ".concat(String.valueOf(String.valueOf(n3))));
                    }
                }
                catch (Exception ex6) {}
                final byte[] array3 = new byte[8 + n3];
                final short[] array4 = new short[dx.m];
                final int m = dx.m;
                try {
                    if (b) {
                        for (int i = 0; i < n; ++i) {
                            array[i] ^= -1;
                        }
                    }
                    for (int n5 = dx.n - 1; n5 >= 0 && !super.b; --n5) {
                        final int max = Math.max(n5 * n3, 0);
                        final int min = Math.min(n3, array.length - max - 1);
                        if (min > 0) {
                            System.arraycopy(array, max, array3, 0, min);
                            ds.a(array4, ji.util.d.a(0, m, array3, 0, array4));
                        }
                    }
                    break;
                }
                catch (Exception ex) {
                    ex.printStackTrace();
                    break;
                }
            }
            case 4: {
                array2 = ds.ag();
                try {
                    int n6 = dx.m / 2;
                    final int n7 = dx.m % 2;
                    if (n7 > 0) {
                        n6 += 2 - n7;
                    }
                    int n8 = n6;
                    final int n9 = n8 % 4;
                    if (n9 > 0) {
                        n8 += 4 - n9;
                    }
                    for (int n10 = dx.n, n11 = 0; n11 < n10 && !super.b; ++n11) {
                        System.arraycopy(array, n11 * n8, array2, (n10 - n11 - 1) * n6, n6);
                    }
                    break;
                }
                catch (Exception ex2) {
                    ex2.printStackTrace();
                    break;
                }
            }
            case 8: {
                array2 = ds.ag();
                try {
                    int j = dx.m;
                    final int n12 = j % 4;
                    if (n12 > 0) {
                        j += 4 - n12;
                    }
                    final int k = dx.m;
                    for (int n13 = dx.n, n14 = 0; n14 < n13 && !super.b; ++n14) {
                        System.arraycopy(array, n14 * j, array2, (n13 - n14 - 1) * k, k);
                    }
                    break;
                }
                catch (Exception ex7) {
                    break;
                }
            }
            case 24: {
                if (i.c(87)) {
                    ds.e(true);
                }
                int n15 = dx.m * 3;
                final int n16 = n15 % 4;
                if (n16 > 0) {
                    n15 += 4 - n16;
                }
                final int[] array5 = new int[dx.m];
                this.a = Math.max(dx.n / 15, 16);
                this.a = Math.min(this.a, dx.n);
                this.b = this.a;
                try {
                    final int l = dx.m;
                    for (int n17 = dx.n, n18 = 0; n18 < n17 && !super.b; ++n18) {
                        final int n19 = (n17 - n18 - 1) * n15;
                        for (int n20 = 0; n20 < l; ++n20) {
                            final int n21 = n20 * 3;
                            array5[n20] = (0xFF000000 | (array[n19 + n21] & 0xFF) | (array[n19 + n21 + 1] & 0xFF) << 8 | (array[n19 + n21 + 2] & 0xFF) << 16);
                        }
                        ds.a(array5, dx.m, component, n18 + 1, n18 + 1, true);
                        --this.b;
                        if (this.b <= 0 && af != null) {
                            this.c.a("".concat(String.valueOf(String.valueOf(100 * n18 / n17))));
                            af.a(this.c);
                            this.b = this.a;
                        }
                    }
                    if (af != null) {
                        this.c.a("100");
                        af.a(this.c);
                    }
                }
                catch (Exception ex3) {
                    if (ji.util.d.cy()) {
                        ex3.printStackTrace();
                    }
                }
                finally {
                    if (array5 != null) {
                        ds.e(component);
                    }
                }
                break;
            }
        }
        if (i.c(5)) {
            h.d(super.a, "WinDIB: Read: now processed image data");
        }
    }
    
    public boolean isAborted(final dx dx, final String s) {
        return super.b;
    }
    
    public void clearAbort(final dx dx, final String s) {
        super.b = false;
    }
    
    public final void abort(final dx dx) {
        try {
            if (e.ai()) {
                super.b = true;
            }
        }
        catch (Exception ex) {}
    }
    
    protected final byte[] a(final byte[] array, final int n, final dx dx) {
        return array;
    }
    
    protected final byte[] a(final byte[] array, final int n, final dx dx, final af af) throws Exception {
        int n2 = dx.m / 2;
        final int n3 = dx.m % 2;
        if (n3 > 0) {
            n2 += 2 - n3;
        }
        final int n4 = n2 * dx.n;
        final byte[] array2 = new byte[n4];
        int n5 = 0;
        int n6 = 0;
        int n7 = 0;
        int n8 = 0;
        try {
            this.a = n4 / 15;
            this.b = this.a;
            int n9 = array[n5++] & 0xFF;
            while (n5 < n && n6 < n4 && !super.b) {
                if (n9 == 0) {
                    n9 = (array[n5++] & 0xFF);
                    if (n9 == 0) {
                        if (n5 % 2 > 0) {
                            ++n5;
                        }
                        final int n10 = n6 % 4;
                        if (n10 > 0) {
                            n6 += n10;
                        }
                        n8 = 0;
                    }
                    else {
                        if (n9 == 1) {
                            break;
                        }
                        if (n9 == 2) {
                            for (int n11 = array[n5++] & 0xFF, i = 0; i < n11; ++i) {
                                if (++n8 == 2) {
                                    n8 = 0;
                                    ++n6;
                                    --this.b;
                                }
                            }
                            final int n12 = n6 + (array[n5++] & 0xFF) * n2;
                            break;
                        }
                        int n13 = 0;
                        for (int j = 0; j < n9; ++j) {
                            int n14;
                            if (n13 == 0) {
                                n14 = (array[n5] >> 4 & 0xF);
                            }
                            else {
                                n14 = (array[n5] & 0xF);
                            }
                            if (++n13 == 2) {
                                n13 = 0;
                                ++n5;
                            }
                            if (n8 == 0) {
                                final byte[] array3 = array2;
                                final int n15 = n6;
                                array3[n15] |= (byte)(n14 << 4);
                            }
                            else {
                                final byte[] array4 = array2;
                                final int n16 = n6;
                                array4[n16] |= (byte)n14;
                            }
                            if (++n8 == 2) {
                                n8 = 0;
                                ++n6;
                                --this.b;
                            }
                        }
                        if (n5 % 2 > 0) {
                            ++n5;
                        }
                    }
                }
                else {
                    final int n17 = array[n5] & 0xF0;
                    final byte b = (byte)(array[n5++] & 0xF);
                    for (int k = 0; k < n9; ++k) {
                        if (n8 == 0) {
                            final byte[] array5 = array2;
                            final int n18 = n6;
                            array5[n18] |= (byte)n17;
                        }
                        else {
                            final byte[] array6 = array2;
                            final int n19 = n6;
                            array6[n19] |= b;
                        }
                        if (++n8 == 2) {
                            n8 = 0;
                            ++n6;
                            --this.b;
                        }
                    }
                }
                if (n5 < n4) {
                    n9 = (array[n5++] & 0xFF);
                }
                if (this.b <= 0 && af != null) {
                    n7 += this.a;
                    this.c.a("".concat(String.valueOf(String.valueOf(100 * n7 / n4))));
                    af.a(this.c);
                    this.b = this.a;
                }
            }
            if (af != null) {
                this.c.a("100");
                af.a(this.c);
            }
        }
        catch (Exception ex) {
            ji.util.d.b(ex);
        }
        return array2;
    }
    
    public final void close(final dx dx, final ad ad) {
    }
    
    protected final byte[] b(final byte[] array, final int n, final dx dx) {
        return array;
    }
    
    public static int[] a(final ds ds) {
        int[] l = ds.l();
        if (l == null) {
            l = new int[16];
            final int red = Color.white.getRed();
            final int n = 15;
            int n2 = red;
            for (int i = 0; i < 16; ++i) {
                n2 -= Math.max(red - n, 0);
                l[i] = (0xFF000000 | n2 << 16 | n2 << 8 | n2);
            }
        }
        return l;
    }
    
    public static int[] b(final ds ds) {
        int[] l = ds.l();
        if (l == null) {
            l = new int[256];
            for (int i = 0; i < 256; ++i) {
                l[i] = (0xFF000000 | i << 16 | i << 8 | i);
            }
        }
        else {
            for (int j = 0; j < l.length; ++j) {
                l[j] |= 0xFF000000;
            }
        }
        return l;
    }
    
    protected final byte[] b(final byte[] array, final int n, final dx dx, final af af) throws Exception {
        final int n2 = dx.m * dx.n;
        final byte[] array2 = new byte[n2];
        final int m = dx.m;
        int n3 = 0;
        int n4 = 0;
        int n5 = 0;
        try {
            this.a = n2 / 15;
            this.b = this.a;
            int n6 = array[n3++] & 0xFF;
            while (n3 < n && n4 < n2 && !super.b) {
                if (n6 == 0) {
                    n6 = (array[n3++] & 0xFF);
                    if (n6 == 0) {
                        if (n3 % 2 > 0) {
                            ++n3;
                        }
                        final int n7 = n4 % 4;
                        if (n7 > 0) {
                            n4 += n7;
                        }
                    }
                    else {
                        if (n6 == 1) {
                            break;
                        }
                        if (n6 == 2) {
                            final int n8 = n4 + (array[n3++] & 0xFF) + (array[n3++] & 0xFF) * m;
                            break;
                        }
                        for (int i = 0; i < n6; ++i) {
                            array2[n4++] = array[n3++];
                            --this.b;
                        }
                        if (n3 % 2 > 0) {
                            ++n3;
                        }
                    }
                }
                else {
                    final byte b = array[n3++];
                    for (int j = 0; j < n6; ++j) {
                        array2[n4++] = b;
                    }
                    this.b -= n6;
                }
                if (n3 < n2) {
                    n6 = (array[n3++] & 0xFF);
                }
                if (this.b <= 0 && af != null) {
                    n5 += this.a;
                    this.c.a("".concat(String.valueOf(String.valueOf(100 * n5 / n2))));
                    af.a(this.c);
                    this.b = this.a;
                }
            }
            if (af != null) {
                this.c.a("100");
                af.a(this.c);
            }
        }
        catch (Exception ex) {
            ji.util.d.b(ex);
        }
        return array2;
    }
    
    public String c(final String s) {
        return this.getFilterName();
    }
    
    protected class aa8
    {
        int a;
        int b;
        int c;
        int d;
        int e;
        int f;
        int g;
        int h;
        int i;
        int j;
        int k;
        int l;
        aba[] m;
        
        protected aa8() {
            this.l = 0;
            this.m = null;
        }
        
        private aa8 a() {
            return this.a(new aa8());
        }
        
        protected aa8 a(final aa8 aa8) {
            aa8.a = this.a;
            aa8.b = this.b;
            aa8.c = this.c;
            aa8.d = this.d;
            aa8.e = this.e;
            aa8.f = this.f;
            aa8.g = this.g;
            aa8.h = this.h;
            aa8.i = this.i;
            aa8.j = this.j;
            aa8.k = this.k;
            aa8.l = this.l;
            if (this.m != null) {
                aa8.m = new aba[this.m.length];
                for (int i = 0; i < this.m.length; ++i) {
                    aa8.m[i] = new aba();
                    aa8.m[i].a = this.m[i].a;
                    aa8.m[i].b = this.m[i].b;
                    aa8.m[i].c = this.m[i].c;
                    aa8.m[i].d = this.m[i].d;
                }
            }
            return aa8;
        }
        
        public byte[] b() {
            final byte[] array = new byte[this.l];
            int n = 0;
            this.a(this.a, array, n);
            n += 4;
            this.a(this.b, array, n);
            n += 4;
            this.a(this.c, array, n);
            n += 4;
            this.b(this.d, array, n);
            n += 2;
            this.b(this.e, array, n);
            n += 2;
            this.a(this.f, array, n);
            n += 4;
            this.a(this.g, array, n);
            n += 4;
            this.a(this.h, array, n);
            n += 4;
            this.a(this.i, array, n);
            n += 4;
            this.a(this.j, array, n);
            n += 4;
            this.a(this.k, array, n);
            n += 20;
            array[n] = 66;
            array[n + 1] = 71;
            array[n + 2] = 82;
            array[n + 3] = 115;
            int a = this.a;
            if (this.m != null) {
                for (int i = 0; i < this.m.length; ++i) {
                    array[a] = this.m[i].a;
                    array[a + 1] = this.m[i].b;
                    array[a + 2] = this.m[i].c;
                    array[a + 3] = this.m[i].d;
                    a += 4;
                }
            }
            return array;
        }
        
        private byte[] a(final int n, final byte[] array, final int n2) {
            array[n2] = (byte)n;
            array[n2 + 1] = (byte)(n >> 8);
            array[n2 + 2] = (byte)(n >> 16);
            array[n2 + 3] = (byte)(n >> 24);
            return array;
        }
        
        private byte[] b(final int n, final byte[] array, final int n2) {
            array[n2] = (byte)n;
            array[n2 + 1] = (byte)(n >> 8);
            return array;
        }
    }
    
    protected class aba
    {
        byte a;
        byte b;
        byte c;
        byte d;
        
        protected aba(final e5 e5) {
        }
    }
}
