// 
// Decompiled by Procyon v0.5.30
// 

package com.cc.A;

import java.io.UnsupportedEncodingException;
import java.awt.Font;
import java.awt.Color;
import com.cc.B.E;
import java.awt.Point;
import com.cc.D.D;
import java.io.IOException;
import java.io.InputStream;
import java.io.DataInputStream;
import java.io.ByteArrayInputStream;
import com.cc.D.B;

public class A
{
    private boolean J;
    private String D;
    private String F;
    public boolean C;
    private boolean I;
    private boolean H;
    public com.cc.C.A E;
    public com.cc.B.A K;
    public B[] B;
    private boolean A;
    private boolean G;
    
    private A() {
        this.D = "Cp1252";
        this.F = "Cp1252";
        this.E = new com.cc.C.A();
        this.K = new com.cc.B.A();
        this.A = false;
    }
    
    public static A A(final byte[] array, final String s, final String s2) throws IOException {
        final DataInputStream dataInputStream = new DataInputStream(new ByteArrayInputStream(array));
        final A a = new A();
        final byte byte1 = dataInputStream.readByte();
        E(dataInputStream, a);
        A(dataInputStream, a, s, s2);
        A(dataInputStream, a, byte1);
        a.E.s = A(dataInputStream, a.F);
        A(dataInputStream, a);
        D(dataInputStream, a);
        C(dataInputStream, a);
        a.A();
        return a;
    }
    
    private void A() {
        if (this.B == null) {
            return;
        }
        for (int i = 0; i < this.B.length; ++i) {
            for (int j = 0; j < this.B[i].D.length; ++j) {
                final D d = this.B[i].D[j];
                this.A = false;
                this.A(d.K - 1, d.I - 1, d.E() == 0, d);
            }
        }
    }
    
    void A(final int n, final int n2, final boolean g, final D d) {
        int x = n;
        int y = n2;
        final boolean n3 = this.K.n;
        final Point point = new Point();
        final E[][] l = this.K.L();
        if (g) {
            while (x >= 0 && l[x][y].B()) {
                d.A(l[x][y]);
                --x;
                if (!this.K.n && (l[x + 1][y].Y & 0x2) != 0x0) {
                    break;
                }
            }
            point.x = x + 1;
            point.y = y;
            for (int n4 = n + 1; n4 < this.K.A && l[n4][y].B(); ++n4) {
                if (!n3 && (l[n4][y].Y & 0x2) != 0x0) {
                    break;
                }
                d.A(l[n4][y]);
            }
        }
        else {
            while (y >= 0 && l[x][y].B()) {
                d.A(l[x][y]);
                --y;
                if (!n3 && (l[x][y + 1].Y & 0x4) != 0x0) {
                    break;
                }
            }
            point.y = y + 1;
            point.x = x;
            for (int n5 = n2 + 1; n5 < this.K.D && l[x][n5].B() && (n3 || (l[x][n5].Y & 0x4) == 0x0); ++n5) {
                d.A(l[x][n5]);
            }
        }
        final int n6 = g ? 1 : 0;
        final C a = l[point.x][point.y].A();
        if (!this.A && a.D[n6] != 0 && !a.A[n6]) {
            this.G = g;
            this.A(point);
            this.A = true;
            this.A(point.x, point.y, this.G, d);
            return;
        }
        if (a.D[n6] > 0) {
            this.A(a.D[n6] - 1, a.C[n6] - 1, a.B[n6] == 1, d);
        }
    }
    
    void A(final Point point) {
        final E[][] l = this.K.L();
        final boolean n = this.K.n;
        int x = point.x;
        int y = point.y;
        if (!this.G) {
            while (y >= 0 && l[x][y].B()) {
                --y;
                if (!n && (l[x][y + 1].Y & 0x4) != 0x0) {
                    break;
                }
            }
            ++y;
        }
        else {
            while (x >= 0 && l[x][y].B()) {
                --x;
                if (!n && (l[x + 1][y].Y & 0x2) != 0x0) {
                    break;
                }
            }
            ++x;
        }
        final C a = l[x][y].A();
        if (a.A[this.G] || a.D[this.G] == 0) {
            point.x = x;
            point.y = y;
        }
        else {
            ++x;
            ++y;
            for (int i = 0; i < 2; ++i) {
                for (int j = 0; j < this.K.A; ++j) {
                    for (int k = 0; k < this.K.D; ++k) {
                        final C a2 = l[j][k].A();
                        if (a2.D[i] == x && a2.C[i] == y && a2.B[i] == 1 == this.G) {
                            point.x = j;
                            point.y = k;
                            this.G = (i == 1);
                            this.A(point);
                            return;
                        }
                    }
                }
            }
        }
    }
    
    private static void C(final DataInputStream dataInputStream, final A a) throws IOException {
        final byte byte1 = dataInputStream.readByte();
        if (byte1 != 0) {
            final Color w = new Color(dataInputStream.readInt());
            byte byte2 = dataInputStream.readByte();
            final byte byte3 = dataInputStream.readByte();
            String s;
            if ((byte2 & 0x80) == 0x0) {
                s = "SansSerif";
            }
            else {
                s = "TimesRoman";
                byte2 &= 0x7F;
            }
            a.E.M = new Font(s, 0, byte2);
            final byte byte4 = dataInputStream.readByte();
            a.E.\u00c7 = dataInputStream.readInt();
            a.E.K = dataInputStream.readInt();
            a.E.w = w;
            a.E.W = ((byte3 & 0x4) != 0x0);
            a.E.y = ((byte4 & 0x8) != 0x0);
            a.B = new B[byte1];
            a.E.R = ((byte4 & 0x1) != 0x0);
            a.E.ª = a.E.R;
            for (byte b = 0; b < byte1; ++b) {
                a.B[b] = new B();
                a.E.O = ((byte4 & 0x2) != 0x0);
                A(dataInputStream, a.B[b], a, b, byte3);
            }
        }
    }
    
    private static void A(final DataInputStream dataInputStream, final B b, final A a, final int n, final byte b2) throws IOException {
        b.E = new com.cc.D.A(new com.cc.D.A._A[] { new com.cc.D.A._A(F(dataInputStream, a), (b2 & 0x1) != 0x0, (b2 & 0x2) != 0x0, com.cc.D.A._A.E) });
        final int int1 = dataInputStream.readInt();
        b.D = new D[int1];
        for (int i = 0; i < b.D.length; ++i) {
            b.D[i] = new D(n, a.K);
        }
        for (int j = 0; j < int1; ++j) {
            byte byte1 = dataInputStream.readByte();
            byte byte2 = dataInputStream.readByte();
            if ((byte2 & 0x80) != 0x0) {
                b.D[j].J = 1;
                byte2 &= 0x7F;
            }
            if ((byte1 & 0x80) != 0x0) {
                byte1 &= 0x7F;
                a.K.A(byte1 - 1, byte2 - 1, false).A().A[b.D[j].J] = true;
                A(dataInputStream, b.D[j].J, a.K.A(byte1 - 1, byte2 - 1, false), a);
            }
            b.D[j].K = byte1;
            b.D[j].I = byte2;
            b.D[j].E = F(dataInputStream, a);
            if (a.H) {
                final String a2 = A(dataInputStream, a.F);
                if (a2.length() > 0) {
                    b.D[j].B(a2);
                }
            }
            b.D[j].C(A(dataInputStream, a.F));
        }
    }
    
    private static void A(final DataInputStream dataInputStream, final int n, final E e, final A a) throws IOException {
        final byte byte1 = dataInputStream.readByte();
        if (byte1 != 0) {
            byte byte2 = dataInputStream.readByte();
            final boolean b = (byte2 & 0x80) != 0x0;
            if (b) {
                byte2 &= 0x7F;
            }
            e.A().D[n] = byte1;
            e.A().C[n] = byte2;
            A(dataInputStream, e.A().B[n] = (byte)(b ? 1 : 0), a.K.A(byte1 - 1, byte2 - 1, false), a);
        }
        else {
            e.A().D[n] = -1;
        }
    }
    
    private static void D(final DataInputStream dataInputStream, final A a) throws IOException {
        a.K.A(dataInputStream.readByte(), dataInputStream.readByte());
        final byte byte1 = dataInputStream.readByte();
        a.K.J = ((byte1 & 0x4) != 0x0);
        a.K.p = (byte1 & 0x3);
        a.K.µ = ((byte1 & 0x8) != 0x0);
        if (a.K.µ) {
            a.K.E = new Color(dataInputStream.readInt());
        }
        a.K.L = dataInputStream.readByte();
        final byte byte2 = dataInputStream.readByte();
        a.K.z = ((byte2 & 0x4) != 0x0);
        a.K.h = ((byte2 & 0x1) != 0x0);
        if (a.K.h || (byte2 & 0x2) != 0x0) {
            final byte[] array = new byte[dataInputStream.readByte()];
            dataInputStream.readFully(array);
            a.K.Y = new String(array, a.D);
            a.K.G = true;
        }
        a.K._ = new Color(dataInputStream.readInt());
        a.K.w = new Color(dataInputStream.readInt());
        a.K.e = new Color(dataInputStream.readInt());
        if (a.I) {
            a.K.y = new Color(dataInputStream.readInt());
        }
        else {
            a.K.y = a.K.e;
        }
        a.K.X = new Color(dataInputStream.readInt());
        a.K.a = new Color(dataInputStream.readInt());
        G(dataInputStream, a);
        if (a.K.z) {
            for (int i = 0; i < a.K.A; ++i) {
                if ((a.K.A(i, 0, false).Y & 0x2) != 0x0) {
                    a.K.T = i;
                    break;
                }
            }
            for (int j = 0; j < a.K.A; ++j) {
                if ((a.K.A(0, j, false).Y & 0x4) != 0x0) {
                    a.K.M = j;
                    break;
                }
            }
        }
        if (a.K.F) {
            a.K.I = new String[a.K.A];
            a.K.H = new String[a.K.D];
            for (int k = 1; k <= a.K.A; ++k) {
                a.K.I[k - 1] = F(dataInputStream, a);
            }
            for (int l = 1; l <= a.K.D; ++l) {
                a.K.H[l - 1] = F(dataInputStream, a);
            }
        }
    }
    
    private static void G(final DataInputStream dataInputStream, final A a) throws IOException, UnsupportedEncodingException {
        String s = null;
        final byte[] array = new byte[a.K.A * a.K.D];
        dataInputStream.readFully(array);
        final String s2 = new String(array, a.D);
        if (a.K.p == 0) {
            final byte[] array2 = new byte[dataInputStream.readInt()];
            dataInputStream.readFully(array2);
            s = new String(array2, a.D);
        }
        dataInputStream.readFully(array);
        int n = 0;
        int n2 = 0;
        for (int i = 0; i < a.K.D; ++i) {
            for (int j = 0; j < a.K.A; ++j) {
                final char char1 = s2.charAt(i * a.K.A + j);
                final byte b = array[i * a.K.A + j];
                final E e = new E(b, char1);
                if (e.B()) {
                    ++n2;
                }
                if ((b & 0x1) != 0x0) {
                    String b2 = null;
                    if (a.K.p == 0) {
                        b2 = s.substring(n * 3, n * 3 + 3).trim();
                    }
                    else if (a.K.p != 2) {
                        b2 = Integer.toString(n + 1);
                    }
                    ++n;
                    e.B = b2;
                }
                if (e.L) {
                    e.P = B(dataInputStream, a);
                }
                a.K.A(e, j, i);
            }
        }
        if (dataInputStream.readBoolean()) {
            final byte[] array3 = new byte[n2];
            dataInputStream.readFully(array3);
            final String s3 = new String(array3, a.D);
            int n3 = 0;
            for (int k = 0; k < a.K.D; ++k) {
                for (int l = 0; l < a.K.A; ++l) {
                    final E a2 = a.K.A(l, k, false);
                    if (a2.B()) {
                        a2.M = String.valueOf(s3.charAt(n3));
                        ++n3;
                    }
                }
            }
        }
        else {
            a.K.µ = false;
        }
        for (int n4 = 0; n4 < a.K.D; ++n4) {
            for (int n5 = 0; n5 < a.K.A; ++n5) {
                final E a3 = a.K.A(n5, n4, false);
                if ((a3.Y & 0x8) != 0x0) {
                    a3.U = new Color(dataInputStream.readInt());
                }
                if ((a3.Y & 0x10) != 0x0) {
                    a3.O = new Color(dataInputStream.readInt());
                }
            }
        }
    }
    
    private static String B(final DataInputStream dataInputStream, final A a) throws IOException {
        final byte[] array = new byte[dataInputStream.readByte() & 0xFF];
        dataInputStream.readFully(array);
        return new String(array, a.D);
    }
    
    private static void E(final DataInputStream dataInputStream, final A a) throws IOException {
        final byte byte1 = dataInputStream.readByte();
        a.E.b = ((byte1 & 0x8) != 0x0);
        a.K.F = ((byte1 & 0x20) != 0x0);
        a.E.t = ((byte1 & 0x80) != 0x0);
        a.K.r = ((byte1 & 0x1) != 0x0);
        a.E.¤ = ((byte1 & 0x4) != 0x0);
        a.E.T = ((byte1 & 0x40) != 0x0);
        a.K.n = ((byte1 & 0x2) != 0x0);
        a.J = ((byte1 & 0x10) != 0x0);
    }
    
    private static void A(final DataInputStream dataInputStream, final A a) throws IOException {
        final byte byte1 = dataInputStream.readByte();
        byte byte2;
        if ((byte1 & 0x40) != 0x0) {
            a.E.L = byte1 - 64;
            byte2 = dataInputStream.readByte();
        }
        else {
            a.E.L = byte1;
            byte2 = 0;
        }
        a.H = ((byte2 & 0x1) != 0x0);
        a.C = ((byte2 & 0x2) != 0x0);
        a.I = ((byte2 & 0x4) != 0x0);
    }
    
    private static void A(final DataInputStream dataInputStream, final A a, final int n) throws IOException {
        if ((n & 0x1) != 0x0) {
            a.E.H.F(F(dataInputStream, a));
        }
        if ((n & 0x2) != 0x0) {
            a.E.H.C(F(dataInputStream, a));
        }
        if ((n & 0x4) != 0x0) {
            a.E.H.A(F(dataInputStream, a));
        }
        if ((n & 0x8) != 0x0) {
            a.E.H.B(F(dataInputStream, a));
        }
        if ((n & 0x10) != 0x0) {
            a.E.H.E(F(dataInputStream, a));
        }
        if ((n & 0x20) != 0x0) {
            a.E.H.G(F(dataInputStream, a));
        }
        if ((n & 0x40) != 0x0) {
            a.E.H.D(F(dataInputStream, a));
        }
    }
    
    private static String F(final DataInputStream dataInputStream, final A a) throws IOException {
        final byte[] array = new byte[dataInputStream.readByte() & 0xFF];
        dataInputStream.readFully(array);
        return new String(array, a.F);
    }
    
    private static String A(final DataInputStream dataInputStream, final String s) throws IOException {
        final byte[] array = new byte[dataInputStream.readShort() & 0xFFFF];
        dataInputStream.readFully(array);
        return new String(array, s);
    }
    
    private static void A(final DataInputStream dataInputStream, final A a, final String f, final String d) throws IOException {
        if (a.J) {
            a.D = A(dataInputStream.readByte() & 0xFF);
            a.F = A(dataInputStream.readByte() & 0xFF);
            if (f != null) {
                a.F = f;
            }
        }
        a.E.D = d;
        if (a.E.D == null) {
            a.E.D = a.D;
        }
        a.E.A = a.D;
        a.E.¥ = a.F;
    }
    
    private static String A(final int n) {
        if (n == 161) {
            return "Cp1253";
        }
        if (n == 162) {
            return "Cp1254";
        }
        if (n == 238) {
            return "Cp1250";
        }
        if (n == 204) {
            return "Cp1251";
        }
        if (n == 186) {
            return "Cp1257";
        }
        if (n == 129) {
            return "KSC5601";
        }
        if (n == 128) {
            return "SJIS";
        }
        if (n == 148) {
            return "GB2312";
        }
        if (n == 136) {
            return "Big5";
        }
        if (n == 177) {
            return "Cp1255";
        }
        if (n == 163) {
            return "Cp1258";
        }
        if (n == 222) {
            return "Cp874";
        }
        if (n == 255) {
            return "UTF8";
        }
        return "Cp1252";
    }
}
