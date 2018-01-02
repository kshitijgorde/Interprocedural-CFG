// 
// Decompiled by Procyon v0.5.30
// 

package z.A.A.A.B;

import java.util.HashMap;
import java.io.InputStream;
import z.A.A.B.A.B;
import z.A.A.B.A.A;
import java.io.File;
import z.A.A.B.A.C;
import z.A.A.A.E;
import z.A.A.A.D;

public class X implements D
{
    private final byte[] V;
    private boolean N;
    private E O;
    private static final int[] P;
    private static final int I = 12;
    private static final int T = 1;
    private static final int B = 2;
    private static final int E = 3;
    private static final int U = 4;
    private static final int Q = 5;
    private static final int C = 6;
    private static final int R = 7;
    private static final int D = 8;
    private static final int G = 9;
    private static final int S = 10;
    private static final int J = 11;
    private static final int M = 12;
    public static final int K = 34665;
    public static final int F = 40965;
    public static final int W = 34853;
    public static final int L = 37500;
    public static final int H = 6;
    static /* synthetic */ Class class$z$A$A$A$B$g;
    static /* synthetic */ Class class$z$A$A$A$B$_;
    static /* synthetic */ Class class$z$A$A$A$B$f;
    static /* synthetic */ Class class$z$A$A$A$B$d;
    static /* synthetic */ Class class$z$A$A$A$B$A;
    static /* synthetic */ Class class$z$A$A$A$B$U;
    static /* synthetic */ Class class$z$A$A$A$B$M;
    static /* synthetic */ Class class$z$A$A$A$B$C;
    static /* synthetic */ Class class$z$A$A$A$B$b;
    static /* synthetic */ Class class$z$A$A$A$B$W;
    static /* synthetic */ Class class$z$A$A$A$B$E;
    static /* synthetic */ Class class$z$A$A$A$B$V;
    static /* synthetic */ Class class$z$A$A$A$B$I;
    static /* synthetic */ Class class$z$A$A$A$B$Z;
    static /* synthetic */ Class class$z$A$A$A$B$c;
    
    public X(final C c) {
        this(c.A((byte)(-31)));
    }
    
    public X(final File file) throws B {
        this(new A(file).A((byte)(-31)));
    }
    
    public X(final InputStream inputStream) throws B {
        this(new A(inputStream).A((byte)(-31)));
    }
    
    public X(final byte[] v) {
        this.V = v;
    }
    
    public E A() {
        return this.A(new E());
    }
    
    public E A(final E o) {
        this.O = o;
        if (this.V == null) {
            return this.O;
        }
        final g g = (g)this.O.B((X.class$z$A$A$A$B$g == null) ? (X.class$z$A$A$A$B$g = class$("z.A.A.A.B.g")) : X.class$z$A$A$A$B$g);
        if (this.V.length <= 14) {
            g.A("Exif data segment must contain at least 14 bytes");
            return this.O;
        }
        if (!"Exif\u0000\u0000".equals(new String(this.V, 0, 6))) {
            g.A("Exif data segment doesn't begin with 'Exif'");
            return this.O;
        }
        final String s = new String(this.V, 6, 2);
        if (!this.A(s)) {
            g.A("Unclear distinction between Motorola/Intel byte ordering: " + s);
            return this.O;
        }
        if (this.C(8) != 42) {
            g.A("Invalid Exif start - should have 0x2A at offset 8 in Exif header");
            return this.O;
        }
        int n = this.D(10) + 6;
        if (n >= this.V.length - 1) {
            g.A("First exif directory offset is beyond end of Exif data segment");
            n = 14;
        }
        this.A(g, new HashMap(), n, 6);
        this.A(g, 6);
        return this.O;
    }
    
    private void A(final g g, final int n) {
        if (!g.N(259)) {
            return;
        }
        if (!g.N(514) || !g.N(513)) {
            return;
        }
        try {
            final int i = g.I(513);
            final byte[] array = new byte[g.I(514)];
            for (int j = 0; j < array.length; ++j) {
                array[j] = this.V[n + i + j];
            }
            g.A(61441, array);
        }
        catch (Throwable t) {
            g.A("Unable to extract thumbnail: " + t.getMessage());
        }
    }
    
    private boolean A(final String s) {
        if ("MM".equals(s)) {
            this.N = true;
        }
        else {
            if (!"II".equals(s)) {
                return false;
            }
            this.N = false;
        }
        return true;
    }
    
    private void A(final z.A.A.A.A a, final HashMap hashMap, final int n, final int n2) {
        if (hashMap.containsKey(new Integer(n))) {
            return;
        }
        hashMap.put(new Integer(n), "processed");
        if (n >= this.V.length || n < 0) {
            a.A("Ignored directory marked to start outside data segement");
            return;
        }
        if (!this.C(n, n2)) {
            a.A("Illegally sized directory");
            return;
        }
        final int c = this.C(n);
        for (int i = 0; i < c; ++i) {
            final int b = this.B(n, i);
            final int c2 = this.C(b);
            final int c3 = this.C(b + 2);
            if (c3 < 1 || c3 > 12) {
                a.A("Invalid format code: " + c3);
            }
            else {
                final int d = this.D(b + 4);
                if (d < 0) {
                    a.A("Negative component count in EXIF");
                }
                else {
                    final int n3 = d * X.P[c3];
                    final int a2 = this.A(n3, b, n2);
                    if (a2 < 0 || a2 > this.V.length) {
                        a.A("Illegal pointer offset value in EXIF");
                    }
                    else if (n3 < 0 || a2 + n3 > this.V.length) {
                        a.A("Illegal number of bytes: " + n3);
                    }
                    else {
                        final int n4 = n2 + this.D(a2);
                        switch (c2) {
                            case 34665: {
                                this.A(this.O.B((X.class$z$A$A$A$B$g == null) ? (X.class$z$A$A$A$B$g = class$("z.A.A.A.B.g")) : X.class$z$A$A$A$B$g), hashMap, n4, n2);
                                break;
                            }
                            case 40965: {
                                this.A(this.O.B((X.class$z$A$A$A$B$_ == null) ? (X.class$z$A$A$A$B$_ = class$("z.A.A.A.B._")) : X.class$z$A$A$A$B$_), hashMap, n4, n2);
                                break;
                            }
                            case 34853: {
                                this.A(this.O.B((X.class$z$A$A$A$B$f == null) ? (X.class$z$A$A$A$B$f = class$("z.A.A.A.B.f")) : X.class$z$A$A$A$B$f), hashMap, n4, n2);
                                break;
                            }
                            case 37500: {
                                this.A(a2, hashMap, n2);
                                break;
                            }
                            default: {
                                this.A(a, c2, a2, d, c3);
                                break;
                            }
                        }
                    }
                }
            }
        }
        final int d2 = this.D(this.B(n, c));
        if (d2 != 0) {
            final int n5 = d2 + n2;
            if (n5 >= this.V.length) {
                return;
            }
            if (n5 < n) {
                return;
            }
            this.A(a, hashMap, n5, n2);
        }
    }
    
    private void A(final int n, final HashMap hashMap, final int n2) {
        final z.A.A.A.A b = this.O.B((X.class$z$A$A$A$B$g == null) ? (X.class$z$A$A$A$B$g = class$("z.A.A.A.B.g")) : X.class$z$A$A$A$B$g);
        if (b == null) {
            return;
        }
        final String k = b.K(271);
        final String s = new String(this.V, n, 2);
        final String s2 = new String(this.V, n, 3);
        final String s3 = new String(this.V, n, 4);
        final String s4 = new String(this.V, n, 5);
        final String s5 = new String(this.V, n, 6);
        final String s6 = new String(this.V, n, 7);
        final String s7 = new String(this.V, n, 8);
        if ("OLYMP".equals(s4) || "EPSON".equals(s4) || "AGFA".equals(s3)) {
            this.A(this.O.B((X.class$z$A$A$A$B$d == null) ? (X.class$z$A$A$A$B$d = class$("z.A.A.A.B.d")) : X.class$z$A$A$A$B$d), hashMap, n + 8, n2);
        }
        else if (k != null && k.trim().toUpperCase().startsWith("NIKON")) {
            if ("Nikon".equals(s4)) {
                if (this.V[n + 6] == 1) {
                    this.A(this.O.B((X.class$z$A$A$A$B$A == null) ? (X.class$z$A$A$A$B$A = class$("z.A.A.A.B.A")) : X.class$z$A$A$A$B$A), hashMap, n + 8, n2);
                }
                else if (this.V[n + 6] == 2) {
                    this.A(this.O.B((X.class$z$A$A$A$B$U == null) ? (X.class$z$A$A$A$B$U = class$("z.A.A.A.B.U")) : X.class$z$A$A$A$B$U), hashMap, n + 18, n + 10);
                }
                else {
                    b.A("Unsupported makernote data ignored.");
                }
            }
            else {
                this.A(this.O.B((X.class$z$A$A$A$B$U == null) ? (X.class$z$A$A$A$B$U = class$("z.A.A.A.B.U")) : X.class$z$A$A$A$B$U), hashMap, n, n2);
            }
        }
        else if ("SONY CAM".equals(s7) || "SONY DSC".equals(s7)) {
            this.A(this.O.B((X.class$z$A$A$A$B$M == null) ? (X.class$z$A$A$A$B$M = class$("z.A.A.A.B.M")) : X.class$z$A$A$A$B$M), hashMap, n + 12, n2);
        }
        else if ("KDK".equals(s2)) {
            this.A(this.O.B((X.class$z$A$A$A$B$C == null) ? (X.class$z$A$A$A$B$C = class$("z.A.A.A.B.C")) : X.class$z$A$A$A$B$C), hashMap, n + 20, n2);
        }
        else if ("Canon".equalsIgnoreCase(k)) {
            this.A(this.O.B((X.class$z$A$A$A$B$b == null) ? (X.class$z$A$A$A$B$b = class$("z.A.A.A.B.b")) : X.class$z$A$A$A$B$b), hashMap, n, n2);
        }
        else if (k != null && k.toUpperCase().startsWith("CASIO")) {
            if ("QVC\u0000\u0000\u0000".equals(s5)) {
                this.A(this.O.B((X.class$z$A$A$A$B$W == null) ? (X.class$z$A$A$A$B$W = class$("z.A.A.A.B.W")) : X.class$z$A$A$A$B$W), hashMap, n + 6, n2);
            }
            else {
                this.A(this.O.B((X.class$z$A$A$A$B$E == null) ? (X.class$z$A$A$A$B$E = class$("z.A.A.A.B.E")) : X.class$z$A$A$A$B$E), hashMap, n, n2);
            }
        }
        else if ("FUJIFILM".equals(s7) || "Fujifilm".equalsIgnoreCase(k)) {
            final boolean n3 = this.N;
            this.N = false;
            this.A(this.O.B((X.class$z$A$A$A$B$V == null) ? (X.class$z$A$A$A$B$V = class$("z.A.A.A.B.V")) : X.class$z$A$A$A$B$V), hashMap, n + this.D(n + 8), n2);
            this.N = n3;
        }
        else if (k != null && k.toUpperCase().startsWith("MINOLTA")) {
            this.A(this.O.B((X.class$z$A$A$A$B$d == null) ? (X.class$z$A$A$A$B$d = class$("z.A.A.A.B.d")) : X.class$z$A$A$A$B$d), hashMap, n, n2);
        }
        else if ("KC".equals(s) || "MINOL".equals(s4) || "MLY".equals(s2) || "+M+M+M+M".equals(s7)) {
            b.A("Unsupported Konica/Minolta data ignored.");
        }
        else if ("KYOCERA".equals(s6)) {
            this.A(this.O.B((X.class$z$A$A$A$B$I == null) ? (X.class$z$A$A$A$B$I = class$("z.A.A.A.B.I")) : X.class$z$A$A$A$B$I), hashMap, n + 22, n2);
        }
        else if ("Panasonic\u0000\u0000\u0000".equals(new String(this.V, n, 12))) {
            this.A(this.O.B((X.class$z$A$A$A$B$Z == null) ? (X.class$z$A$A$A$B$Z = class$("z.A.A.A.B.Z")) : X.class$z$A$A$A$B$Z), hashMap, n + 12, n2);
        }
        else if ("AOC\u0000".equals(s3)) {
            this.A(this.O.B((X.class$z$A$A$A$B$W == null) ? (X.class$z$A$A$A$B$W = class$("z.A.A.A.B.W")) : X.class$z$A$A$A$B$W), hashMap, n + 6, n);
        }
        else if (k != null && (k.toUpperCase().startsWith("PENTAX") || k.toUpperCase().startsWith("ASAHI"))) {
            this.A(this.O.B((X.class$z$A$A$A$B$c == null) ? (X.class$z$A$A$A$B$c = class$("z.A.A.A.B.c")) : X.class$z$A$A$A$B$c), hashMap, n, n);
        }
        else {
            b.A("Unsupported makernote data ignored.");
        }
    }
    
    private boolean C(final int n, final int n2) {
        return 2 + 12 * this.C(n) + 4 + n + n2 < this.V.length;
    }
    
    private void A(final z.A.A.A.A a, final int n, final int n2, final int n3, final int n4) {
        switch (n4) {
            case 7: {
                final byte[] array = new byte[n3];
                for (int n5 = n3 * X.P[n4], i = 0; i < n5; ++i) {
                    array[i] = this.V[n2 + i];
                }
                a.A(n, array);
                break;
            }
            case 2: {
                a.A(n, this.A(n2, n3));
                break;
            }
            case 5:
            case 10: {
                if (n3 == 1) {
                    a.A(n, new z.A.A.C.A(this.D(n2), this.D(n2 + 4)));
                    break;
                }
                final z.A.A.C.A[] array2 = new z.A.A.C.A[n3];
                for (int j = 0; j < n3; ++j) {
                    array2[j] = new z.A.A.C.A(this.D(n2 + 8 * j), this.D(n2 + 4 + 8 * j));
                }
                a.A(n, array2);
                break;
            }
            case 1:
            case 6: {
                if (n3 == 1) {
                    a.A(n, this.V[n2]);
                    break;
                }
                final int[] array3 = new int[n3];
                for (int k = 0; k < n3; ++k) {
                    array3[k] = this.V[n2 + k];
                }
                a.A(n, array3);
                break;
            }
            case 11:
            case 12: {
                if (n3 == 1) {
                    a.A(n, this.V[n2]);
                    break;
                }
                final int[] array4 = new int[n3];
                for (int l = 0; l < n3; ++l) {
                    array4[l] = this.V[n2 + l];
                }
                a.A(n, array4);
                break;
            }
            case 3:
            case 8: {
                if (n3 == 1) {
                    a.A(n, this.C(n2));
                    break;
                }
                final int[] array5 = new int[n3];
                for (int n6 = 0; n6 < n3; ++n6) {
                    array5[n6] = this.C(n2 + n6 * 2);
                }
                a.A(n, array5);
                break;
            }
            case 4:
            case 9: {
                if (n3 == 1) {
                    a.A(n, this.D(n2));
                    break;
                }
                final int[] array6 = new int[n3];
                for (int n7 = 0; n7 < n3; ++n7) {
                    array6[n7] = this.D(n2 + n7 * 4);
                }
                a.A(n, array6);
                break;
            }
            default: {
                a.A("Unknown format code " + n4 + " for tag " + n);
                break;
            }
        }
    }
    
    private int A(final int n, final int n2, final int n3) {
        if (n <= 4) {
            return n2 + 8;
        }
        final int d = this.D(n2 + 8);
        if (d + n > this.V.length) {
            return -1;
        }
        return n3 + d;
    }
    
    private String A(final int n, final int n2) {
        int n3;
        for (n3 = 0; n + n3 < this.V.length && this.V[n + n3] != 0 && n3 < n2; ++n3) {}
        return new String(this.V, n, n3);
    }
    
    private int B(final int n, final int n2) {
        return n + 2 + 12 * n2;
    }
    
    private int C(final int n) {
        if (n < 0 || n + 2 > this.V.length) {
            throw new ArrayIndexOutOfBoundsException("attempt to read data outside of exif segment (index " + n + " where max index is " + (this.V.length - 1) + ")");
        }
        if (this.N) {
            return (this.V[n] << 8 & 0xFF00) | (this.V[n + 1] & 0xFF);
        }
        return (this.V[n + 1] << 8 & 0xFF00) | (this.V[n] & 0xFF);
    }
    
    private int D(final int n) {
        if (n < 0 || n + 4 > this.V.length) {
            throw new ArrayIndexOutOfBoundsException("attempt to read data outside of exif segment (index " + n + " where max index is " + (this.V.length - 1) + ")");
        }
        if (this.N) {
            return (this.V[n] << 24 & 0xFF000000) | (this.V[n + 1] << 16 & 0xFF0000) | (this.V[n + 2] << 8 & 0xFF00) | (this.V[n + 3] & 0xFF);
        }
        return (this.V[n + 3] << 24 & 0xFF000000) | (this.V[n + 2] << 16 & 0xFF0000) | (this.V[n + 1] << 8 & 0xFF00) | (this.V[n] & 0xFF);
    }
    
    static /* synthetic */ Class class$(final String s) {
        try {
            return Class.forName(s);
        }
        catch (ClassNotFoundException ex) {
            throw new NoClassDefFoundError().initCause(ex);
        }
    }
    
    static {
        P = new int[] { 0, 1, 1, 2, 4, 8, 1, 1, 2, 4, 8, 4, 8 };
    }
}
