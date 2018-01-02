// 
// Decompiled by Procyon v0.5.30
// 

package com.diginet.digichat.network;

import java.io.DataInputStream;
import java.io.DataInput;
import com.diginet.digichat.common.u;
import java.io.IOException;
import java.io.InputStream;
import com.diginet.util.w;
import java.io.ByteArrayInputStream;
import java.io.OutputStream;
import java.io.DataOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataOutput;
import com.diginet.digichat.common.v;

public class t
{
    public static byte a;
    public static byte[] b;
    protected int c;
    protected int d;
    protected int e;
    protected int[] f;
    protected byte[] g;
    protected String[] h;
    protected v[] i;
    protected long[] j;
    protected boolean k;
    public int l;
    public int m;
    public int n;
    public boolean o;
    public boolean p;
    public long q;
    public int r;
    
    protected final void a() {
        final int n = (this.e & 0xFFFF0000) >> 16;
        final int n2 = (this.e & 0xFF00) >> 8;
        final int n3 = this.e & 0xFF;
        if (n > 100) {
            System.out.println("Message: intCount " + n);
        }
        if (n2 > 100) {
            System.out.println("Message: stringCount " + n2);
        }
        if (n3 > 100) {
            System.out.println("Message: passwordCount " + n3);
        }
        if (this.d > 0) {
            if (n > 0) {
                this.f = new int[this.d * n];
            }
            if (n2 > 0) {
                this.h = new String[this.d * n2];
            }
            if (this.g() > 0) {
                this.i = new v[this.d * n3];
            }
        }
        this.j = new long[2 * this.d + 2];
    }
    
    public final int b() {
        return this.c;
    }
    
    public final int c() {
        return this.d;
    }
    
    public final int a(final int n, final int n2) throws ArrayIndexOutOfBoundsException {
        if (n < 0 || n >= this.d) {
            throw new ArrayIndexOutOfBoundsException("itemNumber is out-of-bounds: " + n);
        }
        if (n2 < 0 || n2 >= this.e()) {
            throw new ArrayIndexOutOfBoundsException("index is out-of-bounds: " + n2);
        }
        return this.f[n * this.e() + n2];
    }
    
    public final long b(final int n, final int n2) throws ArrayIndexOutOfBoundsException {
        if (n2 < 0 || n2 >= this.e() - 1) {
            throw new ArrayIndexOutOfBoundsException("index is out-of-bounds: " + n2);
        }
        return (this.a(n, n2) << 32) + (this.a(n, n2 + 1) & 0xFFFFFFFFL);
    }
    
    public final void a(final int n, final int n2, final int n3) throws ArrayIndexOutOfBoundsException {
        if (n < 0 || n >= this.d) {
            throw new ArrayIndexOutOfBoundsException("itemNumber is out-of-bounds: " + n);
        }
        if (n2 < 0 || n2 >= this.e()) {
            throw new ArrayIndexOutOfBoundsException("index is out-of-bounds: " + n2);
        }
        this.f[n * this.e() + n2] = n3;
    }
    
    public final void a(final int n, final int n2, final long n3) {
        if (n2 < 0 || n2 >= this.e() - 1) {
            throw new ArrayIndexOutOfBoundsException("index is out-of-bounds: " + n2);
        }
        this.a(n, n2, (int)(n3 >>> 32));
        this.a(n, n2 + 1, (int)(n3 & -1L));
    }
    
    public final byte[] d() throws ArrayIndexOutOfBoundsException {
        return this.g;
    }
    
    public final void a(final byte[] g) throws ArrayIndexOutOfBoundsException {
        this.g = g;
    }
    
    public final String c(final int n, final int n2) throws ArrayIndexOutOfBoundsException {
        if (n < 0 || n >= this.d) {
            throw new ArrayIndexOutOfBoundsException("itemNumber is out-of-bounds: " + n);
        }
        if (n2 < 0 || n2 >= this.f()) {
            throw new ArrayIndexOutOfBoundsException("index is out-of-bounds: " + n2);
        }
        return this.h[n * this.f() + n2];
    }
    
    public final void a(final int n, final int n2, final String s) throws ArrayIndexOutOfBoundsException {
        if (n < 0 || n >= this.d) {
            throw new ArrayIndexOutOfBoundsException("itemNumber is out-of-bounds: " + n);
        }
        if (n2 < 0 || n2 >= this.f()) {
            throw new ArrayIndexOutOfBoundsException("index is out-of-bounds: " + n2);
        }
        this.h[n * this.f() + n2] = s;
    }
    
    public final v d(final int n, final int n2) throws ArrayIndexOutOfBoundsException {
        if (n < 0 || n >= this.d) {
            throw new ArrayIndexOutOfBoundsException("itemNumber is out-of-bounds: " + n);
        }
        if (n2 < 0 || n2 >= this.g()) {
            throw new ArrayIndexOutOfBoundsException("index is out-of-bounds: " + n2);
        }
        return this.i[n * this.g() + n2];
    }
    
    public final void a(final int n, final int n2, final v v) throws ArrayIndexOutOfBoundsException {
        if (n < 0 || n >= this.d) {
            throw new ArrayIndexOutOfBoundsException("itemNumber is out-of-bounds: " + n);
        }
        if (n2 < 0 || n2 >= this.g()) {
            throw new ArrayIndexOutOfBoundsException("index is out-of-bounds: " + n2);
        }
        this.i[n * this.g() + n2] = v;
    }
    
    public static final boolean a(final long n, final int n2) {
        if (n2 < 0 || n2 > 63) {
            throw new ArrayIndexOutOfBoundsException("flag index must be between 0 and 63, inclusive: " + n2);
        }
        return (n & 1L << n2) != 0x0L;
    }
    
    public static final boolean a(final long[] array, final int n) {
        if (n < 0 || n > 127) {
            throw new ArrayIndexOutOfBoundsException("flag index must be between 0 and 127, inclusive: " + n);
        }
        final long n2 = (n < 64) ? (1L << n) : (1L << n - 64);
        if (n < 64) {
            return (array[0] & n2) != 0x0L;
        }
        return (array[1] & n2) != 0x0L;
    }
    
    public static final long a(long n, final int n2, final boolean b) {
        if (n2 < 0 || n2 > 127) {
            throw new ArrayIndexOutOfBoundsException("flag index must be between 0 and 127, inclusive:" + n2);
        }
        final long n3 = (n2 < 64) ? (1L << n2) : (1L << n2 - 64);
        if (b) {
            n |= n3;
        }
        else {
            n &= (n3 ^ -1L);
        }
        return n;
    }
    
    public static final long[] a(final long[] array, final int n, final boolean b) {
        if (n < 0 || n > 127) {
            throw new ArrayIndexOutOfBoundsException("flag index must be between 0 and 127, inclusive:" + n);
        }
        final long n2 = (n < 64) ? (1L << n) : (1L << n - 64);
        if (n < 64) {
            if (b) {
                final int n3 = 0;
                array[n3] |= n2;
            }
            else {
                final int n4 = 0;
                array[n4] &= (n2 ^ -1L);
            }
        }
        else if (b) {
            final int n5 = 1;
            array[n5] |= n2;
        }
        else {
            final int n6 = 1;
            array[n6] &= (n2 ^ -1L);
        }
        return array;
    }
    
    public final boolean e(final int n, final int n2) throws ArrayIndexOutOfBoundsException {
        if (n < -1 || n >= this.d) {
            throw new ArrayIndexOutOfBoundsException("itemNumber is out-of-bounds: " + n);
        }
        return a(new long[] { this.j[2 * n + 2], this.j[2 * n + 3] }, n2);
    }
    
    public final long[] a(final int n) throws ArrayIndexOutOfBoundsException {
        if (n < -1 || n >= this.d) {
            throw new ArrayIndexOutOfBoundsException("itemNumber is out-of-bounds: " + n);
        }
        return new long[] { this.j[2 * n + 2], this.j[2 * n + 3] };
    }
    
    public final void a(final int n, final int n2, final boolean b) throws ArrayIndexOutOfBoundsException {
        if (n < -1 || n >= this.d) {
            throw new ArrayIndexOutOfBoundsException("itemNumber is out-of-bounds: " + n);
        }
        if (n2 < 64) {
            this.j[2 * n + 2] = a(this.j[2 * n + 2], n2, b);
        }
        else {
            this.j[2 * n + 3] = a(this.j[2 * n + 3], n2, b);
        }
    }
    
    public final void f(final int n, final int n2) {
        this.a(n, n2, true);
    }
    
    public final void a(final int n, final long[] array) throws ArrayIndexOutOfBoundsException {
        if (n < -1 || n >= this.d) {
            throw new ArrayIndexOutOfBoundsException("itemNumber is out-of-bounds: " + n);
        }
        this.j[2 * n + 2] = array[0];
        this.j[2 * n + 3] = array[1];
    }
    
    public final int e() {
        return (this.e & 0xFFFF0000) >> 16;
    }
    
    public final int f() {
        return (this.e & 0xFF00) >> 8;
    }
    
    public final int g() {
        return this.e & 0xFF;
    }
    
    public final void a(final DataOutput dataOutput, final boolean b) throws IOException {
        synchronized (dataOutput) {
            ByteArrayOutputStream byteArrayOutputStream = null;
            DataOutput dataOutput2;
            if (b) {
                byteArrayOutputStream = new ByteArrayOutputStream();
                dataOutput2 = new DataOutputStream(byteArrayOutputStream);
            }
            else {
                dataOutput2 = dataOutput;
            }
            dataOutput2.writeInt(this.c);
            dataOutput2.writeInt(this.l);
            dataOutput2.writeInt(this.m);
            dataOutput2.writeInt(this.n);
            dataOutput2.writeShort(this.d);
            dataOutput2.writeInt(this.e);
            if (this.g != null) {
                dataOutput2.writeInt(this.g.length);
                dataOutput2.write(this.g);
            }
            else {
                dataOutput2.writeInt(0);
            }
            for (int i = 0; i < this.j.length; ++i) {
                dataOutput2.writeLong(this.j[i]);
            }
            if (this.f != null) {
                for (int j = 0; j < this.f.length; ++j) {
                    dataOutput2.writeInt(this.f[j]);
                }
            }
            if (this.h != null) {
                for (int k = 0; k < this.h.length; ++k) {
                    if (this.h[k] == null) {
                        dataOutput2.writeUTF("\u0000");
                    }
                    else {
                        dataOutput2.writeUTF(this.h[k]);
                    }
                }
            }
            if (this.i != null) {
                for (int l = 0; l < this.i.length; ++l) {
                    if (this.i[l] == null) {
                        dataOutput2.writeInt(0);
                    }
                    else {
                        this.i[l].a(dataOutput2);
                    }
                }
            }
            if (b) {
                final ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(byteArrayOutputStream.toByteArray());
                final ByteArrayOutputStream byteArrayOutputStream2 = new ByteArrayOutputStream();
                final w w = new w();
                w.a(2);
                w.a(t.b);
                w.a(byteArrayInputStream, byteArrayOutputStream2);
                byteArrayOutputStream2.flush();
                byteArrayOutputStream2.close();
                final byte[] byteArray = byteArrayOutputStream2.toByteArray();
                dataOutput.writeInt(byteArray.length);
                dataOutput.write(byteArray);
            }
        }
    }
    
    public final void a(final DataOutput dataOutput) throws IOException {
        this.a(dataOutput, true);
    }
    
    public final String toString() {
        final StringBuffer sb = new StringBuffer(50);
        sb.append("Message Type: ");
        sb.append(Integer.toHexString(this.c));
        sb.append("  Item Count: ");
        sb.append(String.valueOf(this.d));
        sb.append("  toUser: ");
        sb.append(String.valueOf(this.m));
        sb.append("  toRoom: ");
        sb.append(String.valueOf(this.l));
        sb.append("  flags: ");
        sb.append(Long.toBinaryString(this.j[0]) + " " + Long.toBinaryString(this.j[1]));
        sb.append("\n  ");
        sb.append("flags: ");
        for (int i = 0; i < this.j.length - 2; ++i) {
            sb.append("\n    ");
            sb.append(Long.toBinaryString(this.j[i + 2]));
        }
        if (this.f != null) {
            sb.append("\n  ");
            sb.append("ints: ");
            sb.append(this.e());
            for (int j = 0; j < this.f.length; ++j) {
                if (j % this.e() == 0) {
                    sb.append("\n");
                }
                sb.append("    ");
                sb.append(String.valueOf(this.f[j]));
            }
        }
        if (this.g != null) {
            sb.append("\n  ");
            sb.append("bytes: ");
            sb.append(this.g.length);
            for (int k = 0; k < this.g.length; ++k) {
                if (k % 16 == 0) {
                    sb.append("\n");
                }
                sb.append(" ");
                sb.append(Integer.toHexString(this.g[k]));
            }
        }
        if (this.h != null) {
            sb.append("\n  ");
            sb.append("strings: ");
            sb.append(this.f());
            for (int l = 0; l < this.h.length; ++l) {
                if (l % this.f() == 0) {
                    sb.append("\n");
                }
                sb.append("    ");
                sb.append(this.h[l]);
            }
        }
        if (this.i != null) {
            sb.append("\n  ");
            sb.append("passwords: ");
            sb.append(this.g());
            for (int n = 0; n < this.i.length; ++n) {
                if (n % this.g() == 0) {
                    sb.append("\n");
                }
                sb.append("    ");
                sb.append(this.i[n]);
            }
        }
        sb.append("\n  ");
        return sb.toString();
    }
    
    public final int h() {
        return this.r;
    }
    
    public final long i() {
        return this.q;
    }
    
    public t() {
        this.k = false;
        this.l = -999;
        this.m = -999;
        this.n = -999;
        this.o = false;
        this.p = false;
        this.q = 0L;
        this.r = 0;
    }
    
    public t(final int n, final int n2) throws IllegalArgumentException {
        this(n, u.a(n), n2);
    }
    
    public t(final DataInput dataInput, final boolean b) throws IOException {
        this.k = false;
        this.l = -999;
        this.m = -999;
        this.n = -999;
        this.o = false;
        this.p = false;
        this.q = 0L;
        this.r = 0;
        synchronized (dataInput) {
            DataInput dataInput2;
            if (b) {
                final byte[] array = new byte[dataInput.readInt()];
                dataInput.readFully(array);
                final ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(array);
                final ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                final w w = new w();
                w.a(2);
                w.a(t.b);
                w.b(byteArrayInputStream, byteArrayOutputStream);
                byteArrayInputStream.close();
                dataInput2 = new DataInputStream(new ByteArrayInputStream(byteArrayOutputStream.toByteArray()));
            }
            else {
                dataInput2 = dataInput;
            }
            this.c = dataInput2.readInt();
            this.l = dataInput2.readInt();
            this.m = dataInput2.readInt();
            this.n = dataInput2.readInt();
            this.d = dataInput2.readShort();
            this.e = dataInput2.readInt();
            final int n = (this.e & 0xFFFF0000) >> 16;
            final int n2 = (this.e & 0xFF00) >> 8;
            final int n3 = this.e & 0xFF;
            int e = u.a(this.c);
            if (e == -1) {
                System.out.println("Message of type " + Integer.toString(this.c, 16) + " unknown.");
                e = this.e;
            }
            final int n4 = (e & 0xFFFF0000) >> 16;
            final int n5 = (e & 0xFF00) >> 8;
            final int n6 = e & 0xFF;
            if (e != this.e) {
                System.out.println("Message of type " + Integer.toString(this.c, 16) + " upgraded.");
                this.k = true;
                this.e = e;
            }
            final int int1 = dataInput2.readInt();
            if (int1 > 30000) {
                System.err.println("Message: byteCount " + int1);
                throw new IllegalArgumentException("Message corrupted.");
            }
            if (int1 != 0) {
                dataInput2.readFully(this.g = new byte[int1]);
            }
            this.a();
            if (this.d > 1000) {
                System.err.println("Message: itemCount " + this.d);
            }
            this.j = new long[2 * this.d + 2];
            for (int i = 0; i < this.j.length; ++i) {
                this.j[i] = dataInput2.readLong();
            }
            if (this.d > 0) {
                if (this.f != null) {
                    for (int j = 0; j < this.d; ++j) {
                        for (int k = 0; k < n; ++k) {
                            if (k < n4) {
                                this.f[j * n4 + k] = dataInput2.readInt();
                            }
                            else {
                                dataInput2.readInt();
                            }
                        }
                    }
                }
                if (this.h != null) {
                    for (int l = 0; l < this.d; ++l) {
                        for (int n7 = 0; n7 < n2; ++n7) {
                            if (n7 < n5) {
                                this.h[l * n5 + n7] = dataInput2.readUTF();
                                if ("\u0000".equals(this.h[l * n5 + n7])) {
                                    this.h[l * n5 + n7] = null;
                                }
                            }
                            else {
                                dataInput2.readUTF();
                            }
                        }
                    }
                }
                if (this.i != null) {
                    for (int n8 = 0; n8 < this.d; ++n8) {
                        for (int n9 = 0; n9 < n3; ++n9) {
                            if (n9 < n6) {
                                this.i[n8 * n6 + n9] = new v(dataInput2);
                                if (this.i[n8 * n6 + n9].a()) {
                                    this.i[n8 * n6 + n9] = null;
                                }
                            }
                            else {
                                new v(dataInput2);
                            }
                        }
                    }
                }
            }
        }
    }
    
    public t(final DataInput dataInput) throws IOException {
        this(dataInput, true);
    }
    
    public t(final int c, final int e, final int d) {
        this.k = false;
        this.l = -999;
        this.m = -999;
        this.n = -999;
        this.o = false;
        this.p = false;
        this.q = 0L;
        this.r = 0;
        if (d < 0) {
            throw new IllegalArgumentException("itemCount < 0");
        }
        if (e == -1) {
            throw new IllegalArgumentException("unknown message type");
        }
        this.c = c;
        this.d = d;
        this.e = e;
        if (d > 1000) {
            System.err.println("Message: itemCount2 " + d);
        }
        this.a();
    }
    
    static {
        t.a = -95;
        t.b = null;
    }
}
