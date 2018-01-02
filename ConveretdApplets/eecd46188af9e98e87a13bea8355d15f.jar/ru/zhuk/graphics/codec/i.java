// 
// Decompiled by Procyon v0.5.30
// 

package ru.zhuk.graphics.codec;

import java.awt.image.DirectColorModel;
import java.awt.image.IndexColorModel;
import java.util.zip.InflaterInputStream;
import java.util.zip.Inflater;
import java.util.Enumeration;
import java.io.SequenceInputStream;
import java.io.IOException;
import java.awt.image.ImageConsumer;
import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.InputStream;
import java.util.Vector;
import java.util.Hashtable;
import java.awt.image.ColorModel;
import java.awt.image.ImageProducer;

public class i implements ImageProducer, Runnable
{
    private int a;
    private int D;
    private int d;
    private int t;
    private int J;
    private ColorModel K;
    private Object f;
    private int[] x;
    private byte[] v;
    private Hashtable n;
    private Vector j;
    private boolean F;
    private boolean m;
    private boolean I;
    InputStream B;
    DataInputStream e;
    private Thread r;
    private boolean w;
    private boolean b;
    private int s;
    private boolean g;
    private int o;
    private int G;
    private int k;
    private int u;
    private int p;
    private int i;
    private byte[] C;
    private boolean E;
    int H;
    int z;
    boolean L;
    static final int[] l;
    static final int[] c;
    static final int[] y;
    static final int[] q;
    static final int[] h;
    static final int[] A;
    
    public i(InputStream b) {
        this.d = -1;
        this.t = -1;
        this.J = 65535;
        this.w = false;
        this.b = false;
        this.s = 750;
        this.g = false;
        this.o = -1;
        this.G = -1;
        this.k = -1;
        this.u = -1;
        this.p = -1;
        this.L = true;
        this.j = new Vector();
        this.n = new Hashtable();
        if (!(b instanceof BufferedInputStream)) {
            b = new BufferedInputStream(b, 1024);
        }
        this.B = b;
        this.e = new DataInputStream(this.B);
    }
    
    public synchronized void addConsumer(final ImageConsumer imageConsumer) {
        if (this.j.contains(imageConsumer)) {
            return;
        }
        this.j.addElement(imageConsumer);
        try {
            this.a(imageConsumer);
            this.a(imageConsumer, 0, 0, this.d, this.t);
            if (this.m && this.isConsumer(imageConsumer)) {
                if (this.I) {
                    imageConsumer.imageComplete(1);
                }
                else {
                    imageConsumer.imageComplete(3);
                }
                this.removeConsumer(imageConsumer);
            }
        }
        catch (Exception ex) {
            if (this.isConsumer(imageConsumer)) {
                imageConsumer.imageComplete(1);
            }
        }
    }
    
    private void a(final int n) {
        final int a = this.a;
        final int i = this.i;
        final int n2 = ru.zhuk.graphics.codec.i.A[i];
        final int n3 = ru.zhuk.graphics.codec.i.c[i];
        final int n4 = ru.zhuk.graphics.codec.i.q[i] - n2;
        final int n5 = n + a - n2;
        int n6 = n + a * ru.zhuk.graphics.codec.i.h[i];
        int j;
        final int n7 = j = n + n3;
        if (this.k == 3) {
            final byte[] v = this.v;
            final int length = v.length;
            while (j <= n5) {
                final int n8 = j + n2;
                for (byte b = v[j++]; j < n8; ++j) {
                    v[j] = b;
                }
                j += n4;
            }
            final int n9 = n5 + n2;
            if (j < n9) {
                for (byte b2 = v[j++]; j < n9; ++j) {
                    v[j] = b2;
                }
            }
            if (length < n6) {
                n6 = length;
            }
            for (int k = n7 + a; k < n6; k += a) {
                System.arraycopy(v, n7, v, k, a - n3);
            }
        }
        else {
            final int[] x = this.x;
            final int length2 = x.length;
            while (j <= n5) {
                for (int n10 = j + n2, n11 = x[j++]; j < n10; ++j) {
                    x[j] = n11;
                }
                j += n4;
            }
            final int n12 = n5 + n2;
            if (j < n12) {
                for (int n13 = x[j++]; j < n12; ++j) {
                    x[j] = n13;
                }
            }
            if (length2 < n6) {
                n6 = length2;
            }
            for (int l = n7 + a; l < n6; l += a) {
                System.arraycopy(x, n7, x, l, a - n3);
            }
        }
    }
    
    private boolean a(final byte[] array, final int[] array2, final int[] array3, final int n, final int n2) {
        final int length = array2.length;
        switch (n) {
            case 0: {
                for (int i = 0; i < length; ++i) {
                    array2[i] = (0xFF & array[i]);
                }
                break;
            }
            case 1: {
                int j;
                for (j = 0; j < n2; ++j) {
                    array2[j] = (0xFF & array[j]);
                }
                while (j < length) {
                    array2[j] = (0xFF & array[j] + array2[j - n2]);
                    ++j;
                }
                break;
            }
            case 2: {
                if (array3 != null) {
                    for (int k = 0; k < length; ++k) {
                        array2[k] = (0xFF & array3[k] + array[k]);
                    }
                    break;
                }
                for (int l = 0; l < length; ++l) {
                    array2[l] = (0xFF & array[l]);
                }
                break;
            }
            case 3: {
                if (array3 != null) {
                    int n3;
                    for (n3 = 0; n3 < n2; ++n3) {
                        array2[n3] = (0xFF & (array3[n3] >> 1) + array[n3]);
                    }
                    while (n3 < length) {
                        array2[n3] = (0xFF & (array3[n3] + array2[n3 - n2] >> 1) + array[n3]);
                        ++n3;
                    }
                    break;
                }
                int n4;
                for (n4 = 0; n4 < n2; ++n4) {
                    array2[n4] = (0xFF & array[n4]);
                }
                while (n4 < length) {
                    array2[n4] = (0xFF & (array2[n4 - n2] >> 1) + array[n4]);
                    ++n4;
                }
                break;
            }
            case 4: {
                if (array3 != null) {
                    int n5;
                    for (n5 = 0; n5 < n2; ++n5) {
                        array2[n5] = (0xFF & array3[n5] + array[n5]);
                    }
                    while (n5 < length) {
                        final int n6 = array2[n5 - n2];
                        final int n7 = array3[n5];
                        final int n8 = array3[n5 - n2];
                        final byte b = (byte)(n6 + n7 - n8);
                        final byte b2 = (byte)((b > n6) ? (b - n6) : (n6 - b));
                        final byte b3 = (byte)((b > n7) ? (b - n7) : (n7 - b));
                        final byte b4 = (byte)((b > n8) ? (b - n8) : (n8 - b));
                        byte b5;
                        if (b2 <= b3 && b2 <= b4) {
                            b5 = (byte)n6;
                        }
                        else if (b3 <= b4) {
                            b5 = (byte)n7;
                        }
                        else {
                            b5 = (byte)n8;
                        }
                        array2[n5] = (0xFF & b5 + array[n5]);
                        ++n5;
                    }
                    break;
                }
                int n9;
                for (n9 = 0; n9 < n2; ++n9) {
                    array2[n9] = (0xFF & array[n9]);
                }
                while (n9 < length) {
                    array2[n9] = (0xFF & array2[n9 - n2] + array[n9]);
                    ++n9;
                }
                break;
            }
            default: {
                return false;
            }
        }
        return true;
    }
    
    private void l() throws IOException {
        this.e.skip(this.H);
    }
    
    private void f() throws IOException {
        this.e.skip(this.H);
    }
    
    private void k() throws IOException {
        if (this.L) {
            this.H = this.e.readInt();
            this.z = this.e.readInt();
            this.L = false;
        }
        switch (this.z) {
            case 1649100612: {
                this.l();
                break;
            }
            case 1665684045: {
                this.f();
                break;
            }
            case 1732332865: {
                this.b();
                break;
            }
            case 1749635924: {
                this.n();
                break;
            }
            case 1229209940: {
                this.d();
                break;
            }
            case 1229278788: {
                this.h();
                break;
            }
            case 1229472850: {
                this.p();
                break;
            }
            case 1883789683: {
                this.e();
                break;
            }
            case 1347179589: {
                this.s();
                break;
            }
            case 1933723988: {
                this.j();
                break;
            }
            case 1950701684: {
                this.a();
                break;
            }
            case 1950960965: {
                this.i();
                break;
            }
            case 1951551059: {
                this.q();
                break;
            }
            case 2052348020: {
                this.o();
                break;
            }
            default: {
                this.e.skip(this.H);
                break;
            }
        }
        this.e.readInt();
        this.L = true;
    }
    
    private void b() throws IOException {
        this.e.skip(this.H);
    }
    
    private void n() throws IOException {
        this.e.skip(this.H);
    }
    
    private void d() throws IOException {
        if (!this.w) {
            if (this.d == -1) {
                this.d = this.a;
            }
            if (this.t == -1) {
                this.t = this.D;
            }
            this.g();
            if (this.p != 0) {
                this.F = true;
            }
            final Vector vector;
            synchronized (this) {
                this.w = true;
                vector = (Vector)this.j.clone();
            }
            for (int i = 0; i < vector.size(); ++i) {
                this.a(vector.elementAt(i));
            }
        }
        this.m();
        this.a(0, 0, this.d, this.t);
    }
    
    private void h() throws IOException {
        this.m = true;
    }
    
    private void p() throws IOException {
        if (this.g) {
            throw new IOException("Extraneous IHDR chunk encountered.");
        }
        if (this.H != 13) {
            throw new IOException("IHDR chunk length wrong: ".concat(String.valueOf(String.valueOf(this.H))));
        }
        this.a = this.e.readInt();
        this.D = this.e.readInt();
        this.G = this.e.read();
        this.k = this.e.read();
        this.o = this.e.read();
        this.u = this.e.read();
        this.p = this.e.read();
    }
    
    private void s() throws IOException {
        if (this.k == 3) {
            this.C = new byte[this.H];
            this.e.readFully(this.C);
        }
        else {
            this.e.skip(this.H);
        }
    }
    
    private void e() throws IOException {
        this.e.skip(this.H);
    }
    
    private void j() throws IOException {
        this.e.skip(this.H);
    }
    
    private void a() throws IOException {
        this.e.skip(this.H);
    }
    
    private void i() throws IOException {
        if (this.H != 7) {
            System.err.println("tIME chunk length incorrect: ".concat(String.valueOf(String.valueOf(this.H))));
        }
        this.e.skip(this.H);
    }
    
    private void q() throws IOException {
        final int h = this.H;
        if (this.C == null) {
            throw new IOException("tRNS chunk encountered before pLTE");
        }
        int length = this.C.length;
        switch (this.k) {
            case 3: {
                this.E = true;
                int n = length / 3;
                final byte[] array = new byte[n];
                this.e.readFully(array, 0, this.H);
                final byte b = -1;
                for (int i = length; i < n; ++i) {
                    array[i] = b;
                }
                final byte[] c = new byte[length + n];
                for (int j = c.length; j > 0; c[--j] = array[--n], c[--j] = this.C[--length], c[--j] = this.C[--length], c[--j] = this.C[--length]) {}
                this.C = c;
                break;
            }
            default: {
                this.e.skip(this.H);
                break;
            }
        }
    }
    
    private void o() throws IOException {
        this.e.skip(this.H);
    }
    
    private void r() throws IOException {
        if (this.e.read() != 137 || this.e.read() != 80 || this.e.read() != 78 || this.e.read() != 71 || this.e.read() != 13 || this.e.read() != 10 || this.e.read() != 26 || this.e.read() != 10) {
            throw new IOException("Not a PNG File");
        }
    }
    
    private void a(final ImageConsumer imageConsumer) {
        if (this.w) {
            if (this.isConsumer(imageConsumer)) {
                imageConsumer.setDimensions(this.d, this.t);
            }
            if (this.isConsumer(imageConsumer)) {
                imageConsumer.setProperties(this.n);
            }
            if (this.isConsumer(imageConsumer)) {
                imageConsumer.setColorModel(this.K);
            }
            if (this.isConsumer(imageConsumer)) {
                imageConsumer.setHints(this.F ? 6 : 30);
            }
        }
    }
    
    private void a(final int[] array, int n, int n2) {
        int n3 = array[0];
        final int[] x = this.x;
        final int n4 = ru.zhuk.graphics.codec.i.q[this.i];
        int n5 = 0;
        Label_0429: {
            switch (this.k) {
                case 0: {
                    switch (this.G) {
                        case 1: {
                            for (int i = 0; i < n2; ++i, n += n4) {
                                if (n5 != 0) {
                                    --n5;
                                }
                                else {
                                    n5 = 7;
                                    n3 = array[i >> 3];
                                }
                                x[n] = (n3 >> n5 & 0x1);
                            }
                            break Label_0429;
                        }
                        case 2: {
                            for (int j = 0; j < n2; ++j, n += n4) {
                                if (n5 != 0) {
                                    n5 -= 2;
                                }
                                else {
                                    n5 = 6;
                                    n3 = array[j >> 2];
                                }
                                x[n] = (n3 >> n5 & 0x3);
                            }
                            break Label_0429;
                        }
                        case 4: {
                            for (int k = 0; k < n2; ++k, n += n4) {
                                if (n5 != 0) {
                                    n5 = 0;
                                }
                                else {
                                    n5 = 4;
                                    n3 = array[k >> 1];
                                }
                                x[n] = (n3 >> n5 & 0xF);
                            }
                            break Label_0429;
                        }
                        case 8: {
                            for (int l = 0; l < n2; x[n] = (byte)array[l++], n += n4) {}
                            break Label_0429;
                        }
                        case 16: {
                            n2 <<= 1;
                            for (int n6 = 0; n6 < n2; n6 += 2, n += n4) {
                                x[n] = array[n6];
                            }
                            break Label_0429;
                        }
                        default: {
                            break Label_0429;
                        }
                    }
                    break;
                }
                case 4: {
                    if (this.G == 8) {
                        for (int n7 = 0; n7 < n2; x[n] = (array[n7++] << 8 | array[n7++]), n += n4) {}
                        break;
                    }
                    n2 <<= 1;
                    int[] array2;
                    int n9;
                    int n10;
                    for (int n8 = 0; n8 < n2; n8 += 2, array2[n9] = (n10 | array[n8]), n8 += 2, n += n4) {
                        array2 = x;
                        n9 = n;
                        n10 = array[n8] << 8;
                    }
                    break;
                }
            }
        }
    }
    
    private void c(final int[] array, int n, final int n2) {
        int n3 = 0;
        int n4 = array[0];
        final byte[] v = this.v;
        final int n5 = ru.zhuk.graphics.codec.i.q[this.i];
        switch (this.G) {
            case 1: {
                for (int i = 0; i < n2; ++i, n += n5) {
                    if (n3 != 0) {
                        --n3;
                    }
                    else {
                        n3 = 7;
                        n4 = array[i >> 3];
                    }
                    v[n] = (byte)(n4 >> n3 & 0x1);
                }
                break;
            }
            case 2: {
                for (int j = 0; j < n2; ++j, n += n5) {
                    if (n3 != 0) {
                        n3 -= 2;
                    }
                    else {
                        n3 = 6;
                        n4 = array[j >> 2];
                    }
                    v[n] = (byte)(n4 >> n3 & 0x3);
                }
                break;
            }
            case 4: {
                for (int k = 0; k < n2; ++k, n += n5) {
                    if (n3 != 0) {
                        n3 = 0;
                    }
                    else {
                        n3 = 4;
                        n4 = array[k >> 1];
                    }
                    v[n] = (byte)(n4 >> n3 & 0xF);
                }
                break;
            }
            case 8: {
                for (int l = 0; l < n2; ++l, n += n5) {
                    v[n] = (byte)array[l];
                }
                break;
            }
        }
    }
    
    private void b(final int[] array, int n, int n2) {
        switch (this.k) {
            case 0:
            case 4: {
                this.a(array, n, n2);
                break;
            }
            case 2: {
                final int[] x = this.x;
                final int n3 = ru.zhuk.graphics.codec.i.q[this.i];
                if (this.G == 8) {
                    for (int i = 0; i < n2; x[n] = (array[i++] << 16 | array[i++] << 8 | array[i++]), n += n3) {}
                    break;
                }
                n2 <<= 1;
                int[] array2;
                int n4;
                int n5;
                int n6;
                for (int j = 0; j < n2; j += 2, n6 = (n5 | array[j] << 8), j += 2, array2[n4] = (n6 | array[j]), j += 2, n += n3) {
                    array2 = x;
                    n4 = n;
                    n5 = array[j] << 16;
                }
                break;
            }
            case 3: {
                this.c(array, n, n2);
                break;
            }
            case 6: {
                final int[] x2 = this.x;
                final int n7 = ru.zhuk.graphics.codec.i.q[this.i];
                if (this.G == 8) {
                    for (int k = 0; k < n2; x2[n] = (array[k++] << 16 | array[k++] << 8 | array[k++] | array[k++] << 24), n += n7) {}
                    break;
                }
                n2 <<= 1;
                int[] array3;
                int n8;
                int n9;
                int n10;
                int n11;
                for (int l = 0; l < n2; l += 2, n10 = (n9 | array[l] << 8), l += 2, n11 = (n10 | array[l]), l += 2, array3[n8] = (n11 | array[l] << 24), l += 2, n += n7) {
                    array3 = x2;
                    n8 = n;
                    n9 = array[l] << 16;
                }
                break;
            }
        }
    }
    
    public synchronized boolean isConsumer(final ImageConsumer imageConsumer) {
        return this.j.contains(imageConsumer);
    }
    
    private void m() throws IOException {
        long n = System.currentTimeMillis();
        final DataInputStream dataInputStream = new DataInputStream(new BufferedInputStream(new InflaterInputStream(new SequenceInputStream(new d(this)), new Inflater())));
        int g = 0;
        switch (this.k) {
            case 0:
            case 3: {
                g = this.G;
                break;
            }
            case 2: {
                g = 3 * this.G;
                break;
            }
            case 4: {
                g = this.G << 1;
                break;
            }
            case 6: {
                g = this.G << 2;
                break;
            }
            default: {
                throw new IOException("Unknown color type encountered.");
            }
        }
        final int n2 = g + 7 >> 3;
        this.i = (this.F ? 1 : 0);
        while (this.i < 8) {
            final int i = this.i;
            final int n3 = ru.zhuk.graphics.codec.i.y[i];
            final int n4 = ru.zhuk.graphics.codec.i.q[i];
            final int n5 = ru.zhuk.graphics.codec.i.c[i];
            final int n6 = (this.a - n5 + n4 - 1) / n4;
            final int n7 = n6 * n2;
            final int n8 = n6 * g >> 3;
            final int n9 = ru.zhuk.graphics.codec.i.l[i];
            if (this.D > n9) {
                if (n8 != 0) {
                    final int n10 = n3 * this.a;
                    final byte[] array = new byte[n8];
                    int[] array2 = new int[n8];
                    int[] array3 = null;
                    int[] array4 = new int[n8];
                    int n11 = n9;
                    int n12 = 0;
                    for (int n13 = n9 * this.a, j = n9; j < this.D; j += n3, n13 += n10) {
                        n12 += n3;
                        final int read = dataInputStream.read();
                        dataInputStream.readFully(array);
                        if (!this.a(array, array2, array3, read, n2)) {
                            throw new IOException("Unknown filter type: ".concat(String.valueOf(String.valueOf(read))));
                        }
                        this.b(array2, n13 + n5, n7);
                        if (this.F && i < 6) {
                            this.a(n13);
                        }
                        array3 = array2;
                        array2 = array4;
                        array4 = array3;
                        if (!this.b) {
                            final long currentTimeMillis = System.currentTimeMillis();
                            if (currentTimeMillis - n > this.s) {
                                this.a(0, n11, this.d, n12);
                                n12 = 0;
                                n11 = j + n3;
                                n = currentTimeMillis;
                            }
                        }
                        Thread.yield();
                    }
                    if (!this.F) {
                        break;
                    }
                    if (this.b || n12 > 0) {
                        this.a(0, 0, this.d, this.t);
                        n = System.currentTimeMillis();
                    }
                }
            }
            ++this.i;
        }
        while (dataInputStream.read() != -1) {
            System.err.println("Leftover data encountered.");
        }
    }
    
    public synchronized void removeConsumer(final ImageConsumer imageConsumer) {
        this.j.removeElement(imageConsumer);
    }
    
    public void requestTopDownLeftRightResend(final ImageConsumer imageConsumer) {
    }
    
    public void run() {
        try {
            this.r();
            while (!this.m && !this.I) {
                this.k();
            }
        }
        catch (Exception ex) {
            System.err.println("PNGImageProducer: ".concat(String.valueOf(String.valueOf(ex))));
            ex.printStackTrace(System.err);
            this.I = true;
        }
        synchronized (this) {
            for (int i = 0; i < this.j.size(); ++i) {
                final ImageConsumer imageConsumer = this.j.elementAt(i);
                if (this.I) {
                    imageConsumer.imageComplete(1);
                }
                else {
                    imageConsumer.imageComplete(3);
                }
            }
        }
    }
    
    private synchronized void a(final int n, final int n2, final int n3, final int n4) {
        final int n5 = this.a * n2 + n;
        final Enumeration<ImageConsumer> elements = this.j.elements();
        while (elements.hasMoreElements()) {
            final ImageConsumer imageConsumer = elements.nextElement();
            if (this.f != null && this.isConsumer(imageConsumer)) {
                if (this.f instanceof byte[]) {
                    imageConsumer.setPixels(n, n2, n3, n4, this.K, this.v, n5, this.a);
                }
                else {
                    imageConsumer.setPixels(n, n2, n3, n4, this.K, this.x, n5, this.a);
                }
            }
        }
    }
    
    private void a(final ImageConsumer imageConsumer, final int n, final int n2, final int n3, final int n4) {
        final int n5 = this.a * n2 + n;
        if (this.f != null && this.isConsumer(imageConsumer)) {
            if (this.f instanceof byte[]) {
                imageConsumer.setPixels(n, n2, n3, n4, this.K, this.v, n5, this.a);
            }
            else {
                imageConsumer.setPixels(n, n2, n3, n4, this.K, this.x, n5, this.a);
            }
        }
    }
    
    private void g() throws IOException {
        int n = 0;
        switch (this.G) {
            case 1: {
                n = 1;
                break;
            }
            case 2: {
                n = 3;
                break;
            }
            case 4: {
                n = 15;
                break;
            }
            case 8:
            case 16: {
                n = 255;
                break;
            }
        }
        final int n2 = this.d * this.t;
        switch (this.k) {
            case 3: {
                if (this.C == null) {
                    throw new IOException("No palette located");
                }
                this.v = new byte[n2];
                this.f = this.v;
                if (this.E) {
                    this.K = new IndexColorModel(this.G, this.C.length / 4, this.C, 0, true);
                    break;
                }
                this.K = new IndexColorModel(this.G, this.C.length / 3, this.C, 0, false);
                break;
            }
            case 0: {
                this.x = new int[n2];
                this.f = this.x;
                if (this.G < 8) {
                    this.K = new DirectColorModel(this.G, n, n, n);
                    break;
                }
                this.K = new DirectColorModel(8, n, n, n);
                break;
            }
            case 2: {
                this.x = new int[n2];
                this.f = this.x;
                this.K = new DirectColorModel(24, 16711680, 65280, 255);
                break;
            }
            case 4: {
                if (this.G < 8) {
                    final int n3 = n << this.G;
                    this.K = new DirectColorModel(this.G * 2, n3, n3, n3, n);
                }
                else {
                    final int n4 = n << 8;
                    this.K = new DirectColorModel(16, n4, n4, n4, n);
                }
                this.x = new int[n2];
                this.f = this.x;
                break;
            }
            case 6: {
                this.x = new int[n2];
                this.f = this.x;
                this.K = ColorModel.getRGBdefault();
                break;
            }
            default: {
                throw new IOException("Image has unknown color type");
            }
        }
    }
    
    private void c() {
        if (this.r == null) {
            synchronized (this) {
                this.r = new Thread(this);
                try {
                    this.r.setPriority(3);
                }
                catch (Exception ex) {}
                this.r.start();
            }
        }
    }
    
    public void startProduction(final ImageConsumer imageConsumer) {
        this.addConsumer(imageConsumer);
        this.c();
    }
    
    static {
        l = new int[] { 0, 0, 0, 4, 0, 2, 0, 1 };
        c = new int[] { 0, 0, 4, 0, 2, 0, 1, 0 };
        y = new int[] { 1, 8, 8, 8, 4, 4, 2, 2 };
        q = new int[] { 1, 8, 8, 4, 4, 2, 2, 1 };
        h = new int[] { 1, 8, 8, 4, 4, 2, 2, 1 };
        A = new int[] { 1, 8, 4, 4, 2, 2, 1, 1 };
    }
}
