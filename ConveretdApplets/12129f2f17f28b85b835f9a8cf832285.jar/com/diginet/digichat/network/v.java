// 
// Decompiled by Procyon v0.5.30
// 

package com.diginet.digichat.network;

import java.io.DataInput;
import com.diginet.digichat.common.w;
import java.io.IOException;
import java.io.DataOutput;
import com.diginet.digichat.common.x;

public class v
{
    protected int a;
    protected int b;
    protected int c;
    protected int[] d;
    protected byte[] e;
    protected String[] f;
    protected x[] g;
    protected long[] h;
    protected boolean i;
    public int j;
    public int k;
    public int l;
    public boolean m;
    public boolean n;
    public long o;
    public int p;
    
    protected void a() {
        final int n = (this.c & 0xFFFF0000) >> 16;
        final int n2 = (this.c & 0xFF00) >> 8;
        final int n3 = this.c & 0xFF;
        if (n > 100) {
            System.out.println("Message: intCount " + n);
        }
        if (n2 > 100) {
            System.out.println("Message: stringCount " + n2);
        }
        if (n3 > 100) {
            System.out.println("Message: passwordCount " + n3);
        }
        if (this.b > 0) {
            if (n > 0) {
                this.d = new int[this.b * n];
            }
            if (n2 > 0) {
                this.f = new String[this.b * n2];
            }
            if (this.g() > 0) {
                this.g = new x[this.b * n3];
            }
        }
        this.h = new long[this.b + 1];
    }
    
    public int b() {
        return this.a;
    }
    
    public int c() {
        return this.b;
    }
    
    public int a(final int n, final int n2) throws ArrayIndexOutOfBoundsException {
        if (n < 0 || n >= this.b) {
            throw new ArrayIndexOutOfBoundsException("itemNumber is out-of-bounds: " + n);
        }
        if (n2 < 0 || n2 >= this.e()) {
            throw new ArrayIndexOutOfBoundsException("index is out-of-bounds: " + n2);
        }
        return this.d[n * this.e() + n2];
    }
    
    public long b(final int n, final int n2) throws ArrayIndexOutOfBoundsException {
        if (n2 < 0 || n2 >= this.e() - 1) {
            throw new ArrayIndexOutOfBoundsException("index is out-of-bounds: " + n2);
        }
        return (this.a(n, n2) << 32) + (this.a(n, n2 + 1) & 0xFFFFFFFFL);
    }
    
    public void a(final int n, final int n2, final int n3) throws ArrayIndexOutOfBoundsException {
        if (n < 0 || n >= this.b) {
            throw new ArrayIndexOutOfBoundsException("itemNumber is out-of-bounds: " + n);
        }
        if (n2 < 0 || n2 >= this.e()) {
            throw new ArrayIndexOutOfBoundsException("index is out-of-bounds: " + n2);
        }
        this.d[n * this.e() + n2] = n3;
    }
    
    public void a(final int n, final int n2, final long n3) {
        if (n2 < 0 || n2 >= this.e() - 1) {
            throw new ArrayIndexOutOfBoundsException("index is out-of-bounds: " + n2);
        }
        this.a(n, n2, (int)(n3 >>> 32));
        this.a(n, n2 + 1, (int)(n3 & -1L));
    }
    
    public byte[] d() throws ArrayIndexOutOfBoundsException {
        return this.e;
    }
    
    public void a(final byte[] e) throws ArrayIndexOutOfBoundsException {
        this.e = e;
    }
    
    public String c(final int n, final int n2) throws ArrayIndexOutOfBoundsException {
        if (n < 0 || n >= this.b) {
            throw new ArrayIndexOutOfBoundsException("itemNumber is out-of-bounds: " + n);
        }
        if (n2 < 0 || n2 >= this.f()) {
            throw new ArrayIndexOutOfBoundsException("index is out-of-bounds: " + n2);
        }
        return this.f[n * this.f() + n2];
    }
    
    public void a(final int n, final int n2, final String s) throws ArrayIndexOutOfBoundsException {
        if (n < 0 || n >= this.b) {
            throw new ArrayIndexOutOfBoundsException("itemNumber is out-of-bounds: " + n);
        }
        if (n2 < 0 || n2 >= this.f()) {
            throw new ArrayIndexOutOfBoundsException("index is out-of-bounds: " + n2);
        }
        this.f[n * this.f() + n2] = s;
    }
    
    public x d(final int n, final int n2) throws ArrayIndexOutOfBoundsException {
        if (n < 0 || n >= this.b) {
            throw new ArrayIndexOutOfBoundsException("itemNumber is out-of-bounds: " + n);
        }
        if (n2 < 0 || n2 >= this.g()) {
            throw new ArrayIndexOutOfBoundsException("index is out-of-bounds: " + n2);
        }
        return this.g[n * this.g() + n2];
    }
    
    public void a(final int n, final int n2, final x x) throws ArrayIndexOutOfBoundsException {
        if (n < 0 || n >= this.b) {
            throw new ArrayIndexOutOfBoundsException("itemNumber is out-of-bounds: " + n);
        }
        if (n2 < 0 || n2 >= this.g()) {
            throw new ArrayIndexOutOfBoundsException("index is out-of-bounds: " + n2);
        }
        this.g[n * this.g() + n2] = x;
    }
    
    public static final boolean a(final long n, final int n2) {
        if (n2 < 0 || n2 > 63) {
            throw new ArrayIndexOutOfBoundsException("flag index must be between 0 and 63, inclusive: " + n2);
        }
        return (n & 1L << n2) != 0x0L;
    }
    
    public static final long a(long n, final int n2, final boolean b) {
        if (n2 < 0 || n2 > 63) {
            throw new ArrayIndexOutOfBoundsException("flag index must be between 0 and 63, inclusive: " + n2);
        }
        final long n3 = 1L << n2;
        if (b) {
            n |= n3;
        }
        else {
            n &= (n3 ^ -1L);
        }
        return n;
    }
    
    public boolean e(final int n, final int n2) throws ArrayIndexOutOfBoundsException {
        if (n < -1 || n >= this.b) {
            throw new ArrayIndexOutOfBoundsException("itemNumber is out-of-bounds: " + n);
        }
        return a(this.h[n + 1], n2);
    }
    
    public long a(final int n) throws ArrayIndexOutOfBoundsException {
        if (n < -1 || n >= this.b) {
            throw new ArrayIndexOutOfBoundsException("itemNumber is out-of-bounds: " + n);
        }
        return this.h[n + 1];
    }
    
    public void a(final int n, final int n2, final boolean b) throws ArrayIndexOutOfBoundsException {
        if (n < -1 || n >= this.b) {
            throw new ArrayIndexOutOfBoundsException("itemNumber is out-of-bounds: " + n);
        }
        this.h[n + 1] = a(this.h[n + 1], n2, b);
    }
    
    public void f(final int n, final int n2) {
        this.a(n, n2, true);
    }
    
    public int e() {
        return (this.c & 0xFFFF0000) >> 16;
    }
    
    public int f() {
        return (this.c & 0xFF00) >> 8;
    }
    
    public int g() {
        return this.c & 0xFF;
    }
    
    public void a(final DataOutput dataOutput) throws IOException {
        synchronized (dataOutput) {
            dataOutput.writeInt(this.a);
            dataOutput.writeInt(this.j);
            dataOutput.writeInt(this.k);
            dataOutput.writeInt(this.l);
            dataOutput.writeShort(this.b);
            dataOutput.writeInt(this.c);
            if (this.e != null) {
                dataOutput.writeInt(this.e.length);
                dataOutput.write(this.e);
            }
            else {
                dataOutput.writeInt(0);
            }
            for (int i = 0; i < this.h.length; ++i) {
                dataOutput.writeLong(this.h[i]);
            }
            if (this.d != null) {
                for (int j = 0; j < this.d.length; ++j) {
                    dataOutput.writeInt(this.d[j]);
                }
            }
            if (this.f != null) {
                for (int k = 0; k < this.f.length; ++k) {
                    if (this.f[k] == null) {
                        dataOutput.writeUTF("\u0000");
                    }
                    else {
                        dataOutput.writeUTF(this.f[k]);
                    }
                }
            }
            if (this.g != null) {
                for (int l = 0; l < this.g.length; ++l) {
                    if (this.g[l] == null) {
                        dataOutput.writeInt(0);
                    }
                    else {
                        this.g[l].a(dataOutput);
                    }
                }
            }
        }
    }
    
    public String toString() {
        final StringBuffer sb = new StringBuffer(50);
        sb.append("Message Type: ");
        sb.append(Integer.toHexString(this.a));
        sb.append("  Item Count: ");
        sb.append(String.valueOf(this.b));
        sb.append("  toUser: ");
        sb.append(String.valueOf(this.k));
        sb.append("  toRoom: ");
        sb.append(String.valueOf(this.j));
        sb.append("  flags: ");
        sb.append(Long.toBinaryString(this.h[0]));
        sb.append("\n  ");
        sb.append("flags: ");
        for (int i = 0; i < this.h.length - 1; ++i) {
            sb.append("\n    ");
            sb.append(Long.toBinaryString(this.h[i + 1]));
        }
        if (this.d != null) {
            sb.append("\n  ");
            sb.append("ints: ");
            sb.append(this.e());
            for (int j = 0; j < this.d.length; ++j) {
                if (j % this.e() == 0) {
                    sb.append("\n");
                }
                sb.append("    ");
                sb.append(String.valueOf(this.d[j]));
            }
        }
        if (this.e != null) {
            sb.append("\n  ");
            sb.append("bytes: ");
            sb.append(this.e.length);
            for (int k = 0; k < this.e.length; ++k) {
                if (k % 16 == 0) {
                    sb.append("\n");
                }
                sb.append(" ");
                sb.append(Integer.toHexString(this.e[k]));
            }
        }
        if (this.f != null) {
            sb.append("\n  ");
            sb.append("strings: ");
            sb.append(this.f());
            for (int l = 0; l < this.f.length; ++l) {
                if (l % this.f() == 0) {
                    sb.append("\n");
                }
                sb.append("    ");
                sb.append(this.f[l]);
            }
        }
        if (this.g != null) {
            sb.append("\n  ");
            sb.append("passwords: ");
            sb.append(this.g());
            for (int n = 0; n < this.g.length; ++n) {
                if (n % this.g() == 0) {
                    sb.append("\n");
                }
                sb.append("    ");
                sb.append(this.g[n]);
            }
        }
        sb.append("\n  ");
        return sb.toString();
    }
    
    public int h() {
        return this.p;
    }
    
    public long i() {
        return this.o;
    }
    
    public v() {
        this.i = false;
        this.j = -999;
        this.k = -999;
        this.l = -999;
        this.m = false;
        this.n = false;
        this.o = 0L;
        this.p = 0;
    }
    
    public v(final int n, final int n2) throws IllegalArgumentException {
        this(n, w.a(n), n2);
    }
    
    public v(final DataInput dataInput) throws IOException {
        this.i = false;
        this.j = -999;
        this.k = -999;
        this.l = -999;
        this.m = false;
        this.n = false;
        this.o = 0L;
        this.p = 0;
        synchronized (dataInput) {
            this.a = dataInput.readInt();
            this.j = dataInput.readInt();
            this.k = dataInput.readInt();
            this.l = dataInput.readInt();
            this.b = dataInput.readShort();
            this.c = dataInput.readInt();
            final int n = (this.c & 0xFFFF0000) >> 16;
            final int n2 = (this.c & 0xFF00) >> 8;
            final int n3 = this.c & 0xFF;
            int c = w.a(this.a);
            if (c == -1) {
                System.out.println("Message of type " + Integer.toString(this.a, 16) + " unknown.");
                c = this.c;
            }
            final int n4 = (c & 0xFFFF0000) >> 16;
            final int n5 = (c & 0xFF00) >> 8;
            final int n6 = c & 0xFF;
            if (c != this.c) {
                System.out.println("Message of type " + Integer.toString(this.a, 16) + " upgraded.");
                this.i = true;
                this.c = c;
            }
            final int int1 = dataInput.readInt();
            if (int1 > 1000) {
                System.err.println("Message: byteCount " + int1);
                throw new IllegalArgumentException("Message corrupted.");
            }
            if (int1 != 0) {
                dataInput.readFully(this.e = new byte[int1]);
            }
            this.a();
            if (this.b > 1000) {
                System.err.println("Message: itemCount " + this.b);
            }
            this.h = new long[this.b + 1];
            for (int i = 0; i < this.h.length; ++i) {
                this.h[i] = dataInput.readLong();
            }
            if (this.b > 0) {
                if (this.d != null) {
                    for (int j = 0; j < this.b; ++j) {
                        for (int k = 0; k < n; ++k) {
                            if (k < n4) {
                                this.d[j * n4 + k] = dataInput.readInt();
                            }
                            else {
                                dataInput.readInt();
                            }
                        }
                    }
                }
                if (this.f != null) {
                    for (int l = 0; l < this.b; ++l) {
                        for (int n7 = 0; n7 < n2; ++n7) {
                            if (n7 < n5) {
                                this.f[l * n5 + n7] = dataInput.readUTF();
                                if ("\u0000".equals(this.f[l * n5 + n7])) {
                                    this.f[l * n5 + n7] = null;
                                }
                            }
                            else {
                                dataInput.readUTF();
                            }
                        }
                    }
                }
                if (this.g != null) {
                    for (int n8 = 0; n8 < this.b; ++n8) {
                        for (int n9 = 0; n9 < n3; ++n9) {
                            if (n9 < n6) {
                                this.g[n8 * n6 + n9] = new x(dataInput);
                                if (this.g[n8 * n6 + n9].a()) {
                                    this.g[n8 * n6 + n9] = null;
                                }
                            }
                            else {
                                new x(dataInput);
                            }
                        }
                    }
                }
            }
        }
    }
    
    public v(final int a, final int c, final int b) {
        this.i = false;
        this.j = -999;
        this.k = -999;
        this.l = -999;
        this.m = false;
        this.n = false;
        this.o = 0L;
        this.p = 0;
        if (b < 0) {
            throw new IllegalArgumentException("itemCount < 0");
        }
        if (c == -1) {
            throw new IllegalArgumentException("unknown message type");
        }
        this.a = a;
        this.b = b;
        this.c = c;
        if (b > 1000) {
            System.err.println("Message: itemCount2 " + b);
        }
        this.a();
    }
}
