// 
// Decompiled by Procyon v0.5.30
// 

package doook;

import java.io.DataInputStream;
import java.io.InputStream;
import java.util.zip.InflaterInputStream;
import java.io.ByteArrayInputStream;
import java.io.DataInput;
import java.io.DataOutput;

public class cD
{
    protected int g;
    protected int h;
    protected int i;
    private static int ae;
    protected int[] f;
    protected byte[] e;
    protected String[] f;
    protected a[] a;
    protected long[] a;
    protected boolean k;
    public int o;
    public int j;
    public int k;
    public boolean s;
    public boolean t;
    public long s;
    public int ad;
    
    protected void a() {
        final int n = (this.i & 0xFFFF0000) >> 16;
        final int n2 = (this.i & 0xFF00) >> 8;
        final int n3 = this.i & 0xFF;
        if (n > 100) {
            System.out.println("Message: intCount " + n);
        }
        if (n2 > 100) {
            System.out.println("Message: stringCount " + n2);
        }
        if (n3 > 100) {
            System.out.println("Message: passwordCount " + n3);
        }
        if (this.h > 0) {
            if (n > 0) {
                this.f = new int[this.h * n];
            }
            if (n2 > 0) {
                this.f = new String[this.h * n2];
            }
            if (this.k() > 0) {
                this.a = new a[this.h * n3];
            }
        }
        this.a = new long[this.h + 1];
    }
    
    public int b() {
        return this.g;
    }
    
    public int g() {
        return this.h;
    }
    
    public int b(final int n, final int n2) {
        if (n < 0 || n >= this.h) {
            throw new ArrayIndexOutOfBoundsException("itemNumber is out-of-bounds: " + n);
        }
        if (n2 < 0 || n2 >= this.c()) {
            throw new ArrayIndexOutOfBoundsException("index is out-of-bounds: " + n2);
        }
        return this.f[n * this.c() + n2];
    }
    
    public long a(final int n, final int n2) {
        if (n2 < 0 || n2 >= this.c() - 1) {
            throw new ArrayIndexOutOfBoundsException("index is out-of-bounds: " + n2);
        }
        return (this.b(n, n2) << 32) + (this.b(n, n2 + 1) & 0xFFFFFFFFL);
    }
    
    public void a(final int n, final int n2, final int n3) {
        if (n < 0 || n >= this.h) {
            throw new ArrayIndexOutOfBoundsException("itemNumber is out-of-bounds: " + n);
        }
        if (n2 < 0 || n2 >= this.c()) {
            throw new ArrayIndexOutOfBoundsException("index is out-of-bounds: " + n2);
        }
        this.f[n * this.c() + n2] = n3;
    }
    
    public void a(final int n, final int n2, final long n3) {
        if (n2 < 0 || n2 >= this.c() - 1) {
            throw new ArrayIndexOutOfBoundsException("index is out-of-bounds: " + n2);
        }
        this.a(n, n2, (int)(n3 >>> 32));
        this.a(n, n2 + 1, (int)(n3 & -1L));
    }
    
    public byte[] b() {
        return this.e;
    }
    
    public void a(final byte[] e) {
        this.e = e;
    }
    
    public String a(final int n, final int n2) {
        if (n < 0 || n >= this.h) {
            throw new ArrayIndexOutOfBoundsException("itemNumber is out-of-bounds: " + n);
        }
        if (n2 < 0 || n2 >= this.d()) {
            throw new ArrayIndexOutOfBoundsException("index is out-of-bounds: " + n2);
        }
        return this.f[n * this.d() + n2];
    }
    
    public void a(final int n, final int n2, final String s) {
        if (n < 0 || n >= this.h) {
            throw new ArrayIndexOutOfBoundsException("itemNumber is out-of-bounds: " + n);
        }
        if (n2 < 0 || n2 >= this.d()) {
            throw new ArrayIndexOutOfBoundsException("index is out-of-bounds: " + n2);
        }
        this.f[n * this.d() + n2] = s;
    }
    
    public a a(final int n, final int n2) {
        if (n < 0 || n >= this.h) {
            throw new ArrayIndexOutOfBoundsException("itemNumber is out-of-bounds: " + n);
        }
        if (n2 < 0 || n2 >= this.k()) {
            throw new ArrayIndexOutOfBoundsException("index is out-of-bounds: " + n2);
        }
        return this.a[n * this.k() + n2];
    }
    
    public void a(final int n, final int n2, final a a) {
        if (n < 0 || n >= this.h) {
            throw new ArrayIndexOutOfBoundsException("itemNumber is out-of-bounds: " + n);
        }
        if (n2 < 0 || n2 >= this.k()) {
            throw new ArrayIndexOutOfBoundsException("index is out-of-bounds: " + n2);
        }
        this.a[n * this.k() + n2] = a;
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
            n &= ~n3;
        }
        return n;
    }
    
    public boolean b(final int n, final int n2) {
        if (n < -1 || n >= this.h) {
            throw new ArrayIndexOutOfBoundsException("itemNumber is out-of-bounds: " + n);
        }
        return a(this.a[n + 1], n2);
    }
    
    public long a(final int n) {
        if (n < -1 || n >= this.h) {
            throw new ArrayIndexOutOfBoundsException("itemNumber is out-of-bounds: " + n);
        }
        return this.a[n + 1];
    }
    
    public void a(final int n, final int n2, final boolean b) {
        if (n < -1 || n >= this.h) {
            throw new ArrayIndexOutOfBoundsException("itemNumber is out-of-bounds: " + n);
        }
        this.a[n + 1] = a(this.a[n + 1], n2, b);
    }
    
    public void b(final int n, final int n2) {
        this.a(n, n2, true);
    }
    
    public void a(final int n, final long n2) {
        if (n < -1 || n >= this.h) {
            throw new ArrayIndexOutOfBoundsException("itemNumber is out-of-bounds: " + n);
        }
        this.a[n + 1] = n2;
    }
    
    public int c() {
        return (this.i & 0xFFFF0000) >> 16;
    }
    
    public int d() {
        return (this.i & 0xFF00) >> 8;
    }
    
    public int k() {
        return this.i & 0xFF;
    }
    
    public void a(final DataOutput dataOutput) {
        synchronized (dataOutput) {
            dataOutput.writeInt(this.g);
            dataOutput.writeInt(this.o);
            dataOutput.writeInt(this.j);
            dataOutput.writeInt(this.k);
            dataOutput.writeShort(this.h);
            dataOutput.writeInt(this.i);
            dataOutput.writeInt(cD.ae);
            if (this.e != null) {
                dataOutput.writeInt(this.e.length);
                dataOutput.write(this.e);
            }
            else {
                dataOutput.writeInt(0);
            }
            for (int i = 0; i < this.a.length; ++i) {
                dataOutput.writeLong(this.a[i]);
            }
            if (this.f != null) {
                for (int j = 0; j < this.f.length; ++j) {
                    dataOutput.writeInt(this.f[j]);
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
            if (this.a != null) {
                for (int l = 0; l < this.a.length; ++l) {
                    if (this.a[l] == null) {
                        dataOutput.writeInt(0);
                    }
                    else {
                        this.a[l].a(dataOutput);
                    }
                }
            }
            ++cD.ae;
        }
    }
    
    public String toString() {
        final StringBuffer sb = new StringBuffer(50);
        sb.append("Message Type: ");
        sb.append(Integer.toHexString(this.g));
        sb.append("  Item Count: ");
        sb.append(String.valueOf(this.h));
        sb.append("  toUser: ");
        sb.append(String.valueOf(this.j));
        sb.append("  toRoom: ");
        sb.append(String.valueOf(this.o));
        sb.append("  flags: ");
        sb.append(Long.toBinaryString(this.a[0]));
        sb.append("\n  ");
        sb.append("flags: ");
        for (int i = 0; i < this.a.length - 1; ++i) {
            sb.append("\n    ");
            sb.append(Long.toBinaryString(this.a[i + 1]));
        }
        if (this.f != null) {
            sb.append("\n  ");
            sb.append("ints: ");
            sb.append(this.c());
            for (int j = 0; j < this.f.length; ++j) {
                if (j % this.c() == 0) {
                    sb.append("\n");
                }
                sb.append("    ");
                sb.append(String.valueOf(this.f[j]));
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
            sb.append(this.d());
            for (int l = 0; l < this.f.length; ++l) {
                if (l % this.d() == 0) {
                    sb.append("\n");
                }
                sb.append("    ");
                sb.append(this.f[l]);
            }
        }
        if (this.a != null) {
            sb.append("\n  ");
            sb.append("passwords: ");
            sb.append(this.k());
            for (int n = 0; n < this.a.length; ++n) {
                if (n % this.k() == 0) {
                    sb.append("\n");
                }
                sb.append("    ");
                sb.append(this.a[n]);
            }
        }
        sb.append("\n  ");
        return sb.toString();
    }
    
    public int l() {
        return this.ad;
    }
    
    public long c() {
        return this.s;
    }
    
    public cD() {
        this.k = false;
        this.o = -999;
        this.j = -999;
        this.k = -999;
        this.s = false;
        this.t = false;
        this.s = 0L;
        this.ad = 0;
    }
    
    public cD(final int n, final int n2) {
        this(n, cu.a(n), n2);
    }
    
    public cD(final DataInput dataInput) {
        this.k = false;
        this.o = -999;
        this.j = -999;
        this.k = -999;
        this.s = false;
        this.t = false;
        this.s = 0L;
        this.ad = 0;
        synchronized (dataInput) {
            this.g = dataInput.readInt();
            this.o = dataInput.readInt();
            this.j = dataInput.readInt();
            this.k = dataInput.readInt();
            this.h = dataInput.readShort();
            this.i = dataInput.readInt();
            final int n = (this.i & 0xFFFF0000) >> 16;
            final int n2 = (this.i & 0xFF00) >> 8;
            final int n3 = this.i & 0xFF;
            int i = cu.a(this.g);
            if (i == -1) {
                System.out.println("Message of type " + Integer.toString(this.g, 16) + " unknown.");
                i = this.i;
            }
            final int n4 = (i & 0xFFFF0000) >> 16;
            final int n5 = (i & 0xFF00) >> 8;
            final int n6 = i & 0xFF;
            if (i != this.i) {
                System.out.println("Message of type " + Integer.toString(this.g, 16) + " upgraded.");
                this.k = true;
                this.i = i;
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
            if (this.h > 1000) {
                System.err.println("Message: itemCount " + this.h);
            }
            if (this.k != 1000) {
                this.a = new long[this.h + 1];
                for (int j = 0; j < this.a.length; ++j) {
                    this.a[j] = dataInput.readLong();
                }
                if (this.h > 0) {
                    if (this.f != null) {
                        for (int k = 0; k < this.h; ++k) {
                            for (int l = 0; l < n; ++l) {
                                if (l < n4) {
                                    this.f[k * n4 + l] = dataInput.readInt();
                                }
                                else {
                                    dataInput.readInt();
                                }
                            }
                        }
                    }
                    if (this.f != null) {
                        for (int n7 = 0; n7 < this.h; ++n7) {
                            for (int n8 = 0; n8 < n2; ++n8) {
                                if (n8 < n5) {
                                    this.f[n7 * n5 + n8] = dataInput.readUTF();
                                    if ("\u0000".equals(this.f[n7 * n5 + n8])) {
                                        this.f[n7 * n5 + n8] = null;
                                    }
                                }
                                else {
                                    dataInput.readUTF();
                                }
                            }
                        }
                    }
                    if (this.a != null) {
                        for (int n9 = 0; n9 < this.h; ++n9) {
                            for (int n10 = 0; n10 < n3; ++n10) {
                                if (n10 < n6) {
                                    this.a[n9 * n6 + n10] = new a(dataInput);
                                    if (this.a[n9 * n6 + n10].a()) {
                                        this.a[n9 * n6 + n10] = null;
                                    }
                                }
                                else {
                                    new a(dataInput);
                                }
                            }
                        }
                    }
                }
            }
            else {
                final byte[] array = new byte[dataInput.readInt()];
                dataInput.readFully(array, 0, array.length);
                final ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(array);
                final DataInputStream dataInputStream = new DataInputStream(new InflaterInputStream(byteArrayInputStream));
                this.a = new long[this.h + 1];
                for (int n11 = 0; n11 < this.a.length; ++n11) {
                    this.a[n11] = dataInputStream.readLong();
                }
                if (this.h > 0) {
                    if (this.f != null) {
                        for (int n12 = 0; n12 < this.h; ++n12) {
                            for (int n13 = 0; n13 < n; ++n13) {
                                if (n13 < n4) {
                                    this.f[n12 * n4 + n13] = dataInputStream.readInt();
                                }
                                else {
                                    dataInputStream.readInt();
                                }
                            }
                        }
                    }
                    if (this.f != null) {
                        for (int n14 = 0; n14 < this.h; ++n14) {
                            for (int n15 = 0; n15 < n2; ++n15) {
                                if (n15 < n5) {
                                    this.f[n14 * n5 + n15] = dataInputStream.readUTF();
                                    if ("\u0000".equals(this.f[n14 * n5 + n15])) {
                                        this.f[n14 * n5 + n15] = null;
                                    }
                                }
                                else {
                                    dataInputStream.readUTF();
                                }
                            }
                        }
                    }
                    if (this.a != null) {
                        for (int n16 = 0; n16 < this.h; ++n16) {
                            for (int n17 = 0; n17 < n3; ++n17) {
                                if (n17 < n6) {
                                    this.a[n16 * n6 + n17] = new a(dataInputStream);
                                    if (this.a[n16 * n6 + n17].a()) {
                                        this.a[n16 * n6 + n17] = null;
                                    }
                                }
                                else {
                                    new a(dataInputStream);
                                }
                            }
                        }
                    }
                }
                byteArrayInputStream.close();
            }
            if (this.g == 262400) {
                cG.E(this);
            }
            else if (this.g == 262656) {
                cG.F(this);
            }
        }
    }
    
    public cD(final int g, final int i, final int h) {
        if (g == 67587) {
            cD.ae = 0;
        }
        this.k = false;
        this.o = -999;
        this.j = -999;
        this.k = -999;
        this.s = false;
        this.t = false;
        this.s = 0L;
        this.ad = 0;
        if (h < 0) {
            throw new IllegalArgumentException("itemCount < 0");
        }
        if (i == -1) {
            throw new IllegalArgumentException("unknown message type");
        }
        this.g = g;
        this.h = h;
        this.i = i;
        if (h > 1000) {
            System.err.println("Message: itemCount2 " + h);
        }
        this.a();
    }
    
    static {
        cD.ae = 0;
    }
}
