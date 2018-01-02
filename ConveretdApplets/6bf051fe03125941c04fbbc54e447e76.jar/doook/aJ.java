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

public class aJ
{
    protected int c;
    protected int t;
    protected int g;
    private static int ao;
    protected int[] d;
    protected byte[] e;
    protected String[] e;
    protected r[] a;
    protected long[] a;
    protected boolean r;
    public int C;
    public int j;
    public int k;
    public boolean v;
    public boolean w;
    public long h;
    public int s;
    
    protected void f() {
        final int n = (this.g & 0xFFFF0000) >> 16;
        final int n2 = (this.g & 0xFF00) >> 8;
        final int n3 = this.g & 0xFF;
        if (n > 100) {
            System.out.println("Message: intCount " + n);
        }
        if (n2 > 100) {
            System.out.println("Message: stringCount " + n2);
        }
        if (n3 > 100) {
            System.out.println("Message: passwordCount " + n3);
        }
        if (this.t > 0) {
            if (n > 0) {
                this.d = new int[this.t * n];
            }
            if (n2 > 0) {
                this.e = new String[this.t * n2];
            }
            if (this.i() > 0) {
                this.a = new r[this.t * n3];
            }
        }
        this.a = new long[this.t + 1];
    }
    
    public int b() {
        return this.c;
    }
    
    public int g() {
        return this.t;
    }
    
    public int b(final int n, final int n2) {
        if (n < 0 || n >= this.t) {
            throw new ArrayIndexOutOfBoundsException("itemNumber is out-of-bounds: " + n);
        }
        if (n2 < 0 || n2 >= this.c()) {
            throw new ArrayIndexOutOfBoundsException("index is out-of-bounds: " + n2);
        }
        return this.d[n * this.c() + n2];
    }
    
    public long a(final int n, final int n2) {
        if (n2 < 0 || n2 >= this.c() - 1) {
            throw new ArrayIndexOutOfBoundsException("index is out-of-bounds: " + n2);
        }
        return (this.b(n, n2) << 32) + (this.b(n, n2 + 1) & 0xFFFFFFFFL);
    }
    
    public void a(final int n, final int n2, final int n3) {
        if (n < 0 || n >= this.t) {
            throw new ArrayIndexOutOfBoundsException("itemNumber is out-of-bounds: " + n);
        }
        if (n2 < 0 || n2 >= this.c()) {
            throw new ArrayIndexOutOfBoundsException("index is out-of-bounds: " + n2);
        }
        this.d[n * this.c() + n2] = n3;
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
    
    public String a(final int n, final int n2) {
        if (n < 0 || n >= this.t) {
            throw new ArrayIndexOutOfBoundsException("itemNumber is out-of-bounds: " + n);
        }
        if (n2 < 0 || n2 >= this.d()) {
            throw new ArrayIndexOutOfBoundsException("index is out-of-bounds: " + n2);
        }
        return this.e[n * this.d() + n2];
    }
    
    public void a(final int n, final int n2, final String s) {
        if (n < 0 || n >= this.t) {
            throw new ArrayIndexOutOfBoundsException("itemNumber is out-of-bounds: " + n);
        }
        if (n2 < 0 || n2 >= this.d()) {
            throw new ArrayIndexOutOfBoundsException("index is out-of-bounds: " + n2);
        }
        this.e[n * this.d() + n2] = s;
    }
    
    public r a(final int n, final int n2) {
        if (n < 0 || n >= this.t) {
            throw new ArrayIndexOutOfBoundsException("itemNumber is out-of-bounds: " + n);
        }
        if (n2 < 0 || n2 >= this.i()) {
            throw new ArrayIndexOutOfBoundsException("index is out-of-bounds: " + n2);
        }
        return this.a[n * this.i() + n2];
    }
    
    public void a(final int n, final int n2, final r r) {
        if (n < 0 || n >= this.t) {
            throw new ArrayIndexOutOfBoundsException("itemNumber is out-of-bounds: " + n);
        }
        if (n2 < 0 || n2 >= this.i()) {
            throw new ArrayIndexOutOfBoundsException("index is out-of-bounds: " + n2);
        }
        this.a[n * this.i() + n2] = r;
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
    
    public boolean a(final int n, final int n2) {
        if (n < -1 || n >= this.t) {
            throw new ArrayIndexOutOfBoundsException("itemNumber is out-of-bounds: " + n);
        }
        return a(this.a[n + 1], n2);
    }
    
    public long a(final int n) {
        if (n < -1 || n >= this.t) {
            throw new ArrayIndexOutOfBoundsException("itemNumber is out-of-bounds: " + n);
        }
        return this.a[n + 1];
    }
    
    public void a(final int n, final int n2, final boolean b) {
        if (n < -1 || n >= this.t) {
            throw new ArrayIndexOutOfBoundsException("itemNumber is out-of-bounds: " + n);
        }
        this.a[n + 1] = a(this.a[n + 1], n2, b);
    }
    
    public void b(final int n, final int n2) {
        this.a(n, n2, true);
    }
    
    public int c() {
        return (this.g & 0xFFFF0000) >> 16;
    }
    
    public int d() {
        return (this.g & 0xFF00) >> 8;
    }
    
    public int i() {
        return this.g & 0xFF;
    }
    
    public void a(final DataOutput dataOutput) {
        synchronized (dataOutput) {
            dataOutput.writeInt(this.c);
            dataOutput.writeInt(this.C);
            dataOutput.writeInt(this.j);
            dataOutput.writeInt(this.k);
            dataOutput.writeShort(this.t);
            dataOutput.writeInt(this.g);
            dataOutput.writeInt(aJ.ao);
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
            if (this.d != null) {
                for (int j = 0; j < this.d.length; ++j) {
                    dataOutput.writeInt(this.d[j]);
                }
            }
            if (this.e != null) {
                for (int k = 0; k < this.e.length; ++k) {
                    if (this.e[k] == null) {
                        dataOutput.writeUTF("\u0000");
                    }
                    else {
                        dataOutput.writeUTF(this.e[k]);
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
            ++aJ.ao;
        }
    }
    
    public String toString() {
        final StringBuffer sb = new StringBuffer(50);
        sb.append("Message Type: ");
        sb.append(Integer.toHexString(this.c));
        sb.append("  Item Count: ");
        sb.append(String.valueOf(this.t));
        sb.append("  toUser: ");
        sb.append(String.valueOf(this.j));
        sb.append("  toRoom: ");
        sb.append(String.valueOf(this.C));
        sb.append("  flags: ");
        sb.append(Long.toBinaryString(this.a[0]));
        sb.append("\n  ");
        sb.append("flags: ");
        for (int i = 0; i < this.a.length - 1; ++i) {
            sb.append("\n    ");
            sb.append(Long.toBinaryString(this.a[i + 1]));
        }
        if (this.d != null) {
            sb.append("\n  ");
            sb.append("ints: ");
            sb.append(this.c());
            for (int j = 0; j < this.d.length; ++j) {
                if (j % this.c() == 0) {
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
        if (this.e != null) {
            sb.append("\n  ");
            sb.append("strings: ");
            sb.append(this.d());
            for (int l = 0; l < this.e.length; ++l) {
                if (l % this.d() == 0) {
                    sb.append("\n");
                }
                sb.append("    ");
                sb.append(this.e[l]);
            }
        }
        if (this.a != null) {
            sb.append("\n  ");
            sb.append("passwords: ");
            sb.append(this.i());
            for (int n = 0; n < this.a.length; ++n) {
                if (n % this.i() == 0) {
                    sb.append("\n");
                }
                sb.append("    ");
                sb.append(this.a[n]);
            }
        }
        sb.append("\n  ");
        return sb.toString();
    }
    
    public int j() {
        return this.s;
    }
    
    public long c() {
        return this.h;
    }
    
    public aJ() {
        this.r = false;
        this.C = -999;
        this.j = -999;
        this.k = -999;
        this.v = false;
        this.w = false;
        this.h = 0L;
        this.s = 0;
    }
    
    public aJ(final int n, final int n2) {
        this(n, doook.t.a(n), n2);
    }
    
    public aJ(final DataInput dataInput) {
        this.r = false;
        this.C = -999;
        this.j = -999;
        this.k = -999;
        this.v = false;
        this.w = false;
        this.h = 0L;
        this.s = 0;
        synchronized (dataInput) {
            this.c = dataInput.readInt();
            this.C = dataInput.readInt();
            this.j = dataInput.readInt();
            this.k = dataInput.readInt();
            this.t = dataInput.readShort();
            this.g = dataInput.readInt();
            final int n = (this.g & 0xFFFF0000) >> 16;
            final int n2 = (this.g & 0xFF00) >> 8;
            final int n3 = this.g & 0xFF;
            int g = doook.t.a(this.c);
            if (g == -1) {
                System.out.println("Message of type " + Integer.toString(this.c, 16) + " unknown.");
                g = this.g;
            }
            final int n4 = (g & 0xFFFF0000) >> 16;
            final int n5 = (g & 0xFF00) >> 8;
            final int n6 = g & 0xFF;
            if (g != this.g) {
                System.out.println("Message of type " + Integer.toString(this.c, 16) + " upgraded.");
                this.r = true;
                this.g = g;
            }
            final int int1 = dataInput.readInt();
            if (int1 > 1000) {
                System.err.println("Message: byteCount " + int1);
                throw new IllegalArgumentException("Message corrupted.");
            }
            if (int1 != 0) {
                dataInput.readFully(this.e = new byte[int1]);
            }
            this.f();
            if (this.t > 1000) {
                System.err.println("Message: itemCount " + this.t);
            }
            if (this.k != 1000) {
                this.a = new long[this.t + 1];
                for (int i = 0; i < this.a.length; ++i) {
                    this.a[i] = dataInput.readLong();
                }
                if (this.t > 0) {
                    if (this.d != null) {
                        for (int j = 0; j < this.t; ++j) {
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
                    if (this.e != null) {
                        for (int l = 0; l < this.t; ++l) {
                            for (int n7 = 0; n7 < n2; ++n7) {
                                if (n7 < n5) {
                                    this.e[l * n5 + n7] = dataInput.readUTF();
                                    if ("\u0000".equals(this.e[l * n5 + n7])) {
                                        this.e[l * n5 + n7] = null;
                                    }
                                }
                                else {
                                    dataInput.readUTF();
                                }
                            }
                        }
                    }
                    if (this.a != null) {
                        for (int n8 = 0; n8 < this.t; ++n8) {
                            for (int n9 = 0; n9 < n3; ++n9) {
                                if (n9 < n6) {
                                    this.a[n8 * n6 + n9] = new r(dataInput);
                                    if (this.a[n8 * n6 + n9].b()) {
                                        this.a[n8 * n6 + n9] = null;
                                    }
                                }
                                else {
                                    new r(dataInput);
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
                this.a = new long[this.t + 1];
                for (int n10 = 0; n10 < this.a.length; ++n10) {
                    this.a[n10] = dataInputStream.readLong();
                }
                if (this.t > 0) {
                    if (this.d != null) {
                        for (int n11 = 0; n11 < this.t; ++n11) {
                            for (int n12 = 0; n12 < n; ++n12) {
                                if (n12 < n4) {
                                    this.d[n11 * n4 + n12] = dataInputStream.readInt();
                                }
                                else {
                                    dataInputStream.readInt();
                                }
                            }
                        }
                    }
                    if (this.e != null) {
                        for (int n13 = 0; n13 < this.t; ++n13) {
                            for (int n14 = 0; n14 < n2; ++n14) {
                                if (n14 < n5) {
                                    this.e[n13 * n5 + n14] = dataInputStream.readUTF();
                                    if ("\u0000".equals(this.e[n13 * n5 + n14])) {
                                        this.e[n13 * n5 + n14] = null;
                                    }
                                }
                                else {
                                    dataInputStream.readUTF();
                                }
                            }
                        }
                    }
                    if (this.a != null) {
                        for (int n15 = 0; n15 < this.t; ++n15) {
                            for (int n16 = 0; n16 < n3; ++n16) {
                                if (n16 < n6) {
                                    this.a[n15 * n6 + n16] = new r(dataInputStream);
                                    if (this.a[n15 * n6 + n16].b()) {
                                        this.a[n15 * n6 + n16] = null;
                                    }
                                }
                                else {
                                    new r(dataInputStream);
                                }
                            }
                        }
                    }
                }
                byteArrayInputStream.close();
            }
        }
        if (this.c == 67342) {
            F.a(this);
        }
    }
    
    public aJ(final int c, final int g, final int t) {
        if (c == 67587) {
            aJ.ao = 0;
        }
        this.r = false;
        this.C = -999;
        this.j = -999;
        this.k = -999;
        this.v = false;
        this.w = false;
        this.h = 0L;
        this.s = 0;
        if (t < 0) {
            throw new IllegalArgumentException("itemCount < 0");
        }
        if (g == -1) {
            throw new IllegalArgumentException("unknown message type");
        }
        this.c = c;
        this.t = t;
        this.g = g;
        if (t > 1000) {
            System.err.println("Message: itemCount2 " + t);
        }
        this.f();
    }
    
    static {
        aJ.ao = 0;
    }
}
