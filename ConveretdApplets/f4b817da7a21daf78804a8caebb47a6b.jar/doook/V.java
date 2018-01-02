// 
// Decompiled by Procyon v0.5.30
// 

package doook;

import java.io.DataInputStream;
import java.io.InputStream;
import java.util.zip.InflaterInputStream;
import java.io.ByteArrayInputStream;
import java.io.DataInput;
import java.io.Reader;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.io.DataOutput;

public class V
{
    protected int e;
    protected int f;
    protected int a;
    private static int p;
    protected int[] b;
    protected byte[] b;
    protected String[] a;
    protected M[] a;
    protected long[] a;
    protected boolean e;
    public int u;
    public int j;
    public int k;
    public boolean k;
    public boolean l;
    public long b;
    public int o;
    private static String url;
    
    protected void c() {
        final int n = (this.a & 0xFFFF0000) >> 16;
        final int n2 = (this.a & 0xFF00) >> 8;
        final int n3 = this.a & 0xFF;
        if (n > 100) {
            System.out.println("Message: intCount " + n);
        }
        if (n2 > 100) {
            System.out.println("Message: stringCount " + n2);
        }
        if (n3 > 100) {
            System.out.println("Message: passwordCount " + n3);
        }
        if (this.f > 0) {
            if (n > 0) {
                this.b = new int[this.f * n];
            }
            if (n2 > 0) {
                this.a = new String[this.f * n2];
            }
            if (this.g() > 0) {
                this.a = new M[this.f * n3];
            }
        }
        this.a = new long[this.f + 1];
    }
    
    public int a() {
        return this.e;
    }
    
    public int d() {
        return this.f;
    }
    
    public int a(final int n, final int n2) {
        if (n < 0 || n >= this.f) {
            throw new ArrayIndexOutOfBoundsException("itemNumber is out-of-bounds: " + n);
        }
        if (n2 < 0 || n2 >= this.e()) {
            throw new ArrayIndexOutOfBoundsException("index is out-of-bounds: " + n2);
        }
        return this.b[n * this.e() + n2];
    }
    
    public long a(final int n, final int n2) {
        if (n2 < 0 || n2 >= this.e() - 1) {
            throw new ArrayIndexOutOfBoundsException("index is out-of-bounds: " + n2);
        }
        return (this.a(n, n2) << 32) + (this.a(n, n2 + 1) & 0xFFFFFFFFL);
    }
    
    public void a(final int n, final int n2, final int n3) {
        if (n < 0 || n >= this.f) {
            throw new ArrayIndexOutOfBoundsException("itemNumber is out-of-bounds: " + n);
        }
        if (n2 < 0 || n2 >= this.e()) {
            throw new ArrayIndexOutOfBoundsException("index is out-of-bounds: " + n2);
        }
        this.b[n * this.e() + n2] = n3;
    }
    
    public void a(final int n, final int n2, final long n3) {
        if (n2 < 0 || n2 >= this.e() - 1) {
            throw new ArrayIndexOutOfBoundsException("index is out-of-bounds: " + n2);
        }
        this.a(n, n2, (int)(n3 >>> 32));
        this.a(n, n2 + 1, (int)(n3 & -1L));
    }
    
    public byte[] b() {
        return this.b;
    }
    
    public String a(final int n, final int n2) {
        if (n < 0 || n >= this.f) {
            throw new ArrayIndexOutOfBoundsException("itemNumber is out-of-bounds: " + n);
        }
        if (n2 < 0 || n2 >= this.f()) {
            throw new ArrayIndexOutOfBoundsException("index is out-of-bounds: " + n2);
        }
        return this.a[n * this.f() + n2];
    }
    
    public void a(final int n, final int n2, final String s) {
        if (n < 0 || n >= this.f) {
            throw new ArrayIndexOutOfBoundsException("itemNumber is out-of-bounds: " + n);
        }
        if (n2 < 0 || n2 >= this.f()) {
            throw new ArrayIndexOutOfBoundsException("index is out-of-bounds: " + n2);
        }
        this.a[n * this.f() + n2] = s;
    }
    
    public M a(final int n, final int n2) {
        if (n < 0 || n >= this.f) {
            throw new ArrayIndexOutOfBoundsException("itemNumber is out-of-bounds: " + n);
        }
        if (n2 < 0 || n2 >= this.g()) {
            throw new ArrayIndexOutOfBoundsException("index is out-of-bounds: " + n2);
        }
        return this.a[n * this.g() + n2];
    }
    
    public void a(final int n, final int n2, final M m) {
        if (n < 0 || n >= this.f) {
            throw new ArrayIndexOutOfBoundsException("itemNumber is out-of-bounds: " + n);
        }
        if (n2 < 0 || n2 >= this.g()) {
            throw new ArrayIndexOutOfBoundsException("index is out-of-bounds: " + n2);
        }
        this.a[n * this.g() + n2] = m;
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
        if (n < -1 || n >= this.f) {
            throw new ArrayIndexOutOfBoundsException("itemNumber is out-of-bounds: " + n);
        }
        return a(this.a[n + 1], n2);
    }
    
    public long a(final int n) {
        if (n < -1 || n >= this.f) {
            throw new ArrayIndexOutOfBoundsException("itemNumber is out-of-bounds: " + n);
        }
        return this.a[n + 1];
    }
    
    public void a(final int n, final int n2, final boolean b) {
        if (n < -1 || n >= this.f) {
            throw new ArrayIndexOutOfBoundsException("itemNumber is out-of-bounds: " + n);
        }
        this.a[n + 1] = a(this.a[n + 1], n2, b);
    }
    
    public void a(final int n, final int n2) {
        this.a(n, n2, true);
    }
    
    public int e() {
        return (this.a & 0xFFFF0000) >> 16;
    }
    
    public int f() {
        return (this.a & 0xFF00) >> 8;
    }
    
    public int g() {
        return this.a & 0xFF;
    }
    
    public void a(final DataOutput dataOutput) {
        synchronized (dataOutput) {
            dataOutput.writeInt(this.e);
            dataOutput.writeInt(this.u);
            dataOutput.writeInt(this.j);
            dataOutput.writeInt(this.k);
            dataOutput.writeShort(this.f);
            dataOutput.writeInt(this.a);
            dataOutput.writeInt(V.p);
            if (this.b != null) {
                dataOutput.writeInt(this.b.length);
                dataOutput.write(this.b);
            }
            else {
                dataOutput.writeInt(0);
            }
            for (int i = 0; i < this.a.length; ++i) {
                dataOutput.writeLong(this.a[i]);
            }
            if (this.b != null) {
                for (int j = 0; j < this.b.length; ++j) {
                    dataOutput.writeInt(this.b[j]);
                }
            }
            if (this.e == 67587) {
                this.a(0, 1, this.a());
            }
            if (this.a != null) {
                for (int k = 0; k < this.a.length; ++k) {
                    if (this.a[k] == null) {
                        dataOutput.writeUTF("\u0000");
                    }
                    else {
                        dataOutput.writeUTF(this.a[k]);
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
            ++V.p;
        }
    }
    
    public String toString() {
        final StringBuffer sb = new StringBuffer(50);
        sb.append("Message Type: ");
        sb.append(Integer.toHexString(this.e));
        sb.append("  Item Count: ");
        sb.append(String.valueOf(this.f));
        sb.append("  toUser: ");
        sb.append(String.valueOf(this.j));
        sb.append("  toRoom: ");
        sb.append(String.valueOf(this.u));
        sb.append("  flags: ");
        sb.append(Long.toBinaryString(this.a[0]));
        sb.append("\n  ");
        sb.append("flags: ");
        for (int i = 0; i < this.a.length - 1; ++i) {
            sb.append("\n    ");
            sb.append(Long.toBinaryString(this.a[i + 1]));
        }
        if (this.b != null) {
            sb.append("\n  ");
            sb.append("ints: ");
            sb.append(this.e());
            for (int j = 0; j < this.b.length; ++j) {
                if (j % this.e() == 0) {
                    sb.append("\n");
                }
                sb.append("    ");
                sb.append(String.valueOf(this.b[j]));
            }
        }
        if (this.b != null) {
            sb.append("\n  ");
            sb.append("bytes: ");
            sb.append(this.b.length);
            for (int k = 0; k < this.b.length; ++k) {
                if (k % 16 == 0) {
                    sb.append("\n");
                }
                sb.append(" ");
                sb.append(Integer.toHexString(this.b[k]));
            }
        }
        if (this.a != null) {
            sb.append("\n  ");
            sb.append("strings: ");
            sb.append(this.f());
            for (int l = 0; l < this.a.length; ++l) {
                if (l % this.f() == 0) {
                    sb.append("\n");
                }
                sb.append("    ");
                sb.append(this.a[l]);
            }
        }
        if (this.a != null) {
            sb.append("\n  ");
            sb.append("passwords: ");
            sb.append(this.g());
            for (int n = 0; n < this.a.length; ++n) {
                if (n % this.g() == 0) {
                    sb.append("\n");
                }
                sb.append("    ");
                sb.append(this.a[n]);
            }
        }
        sb.append("\n  ");
        return sb.toString();
    }
    
    public int h() {
        return this.o;
    }
    
    public long a() {
        return this.b;
    }
    
    public V() {
        this.e = false;
        this.u = -999;
        this.j = -999;
        this.k = -999;
        this.k = false;
        this.l = false;
        this.b = 0L;
        this.o = 0;
    }
    
    public V(final int n, final int n2) {
        this(n, af.b(n), n2);
    }
    
    private String a() {
        if (V.url == null || V.url.length() == 0) {
            return "";
        }
        try {
            int i = 0;
            while (i < 5) {
                ++i;
                try {
                    final BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(new URL(V.url + "?t=" + String.valueOf(System.currentTimeMillis())).openStream()));
                    final String line = bufferedReader.readLine();
                    bufferedReader.close();
                    return line;
                }
                catch (Exception ex) {
                    Thread.sleep(2000L);
                }
            }
        }
        catch (Exception ex2) {}
        return "";
    }
    
    public V(final DataInput dataInput) {
        this.e = false;
        this.u = -999;
        this.j = -999;
        this.k = -999;
        this.k = false;
        this.l = false;
        this.b = 0L;
        this.o = 0;
        synchronized (dataInput) {
            this.e = dataInput.readInt();
            this.u = dataInput.readInt();
            this.j = dataInput.readInt();
            this.k = dataInput.readInt();
            this.f = dataInput.readShort();
            this.a = dataInput.readInt();
            final int n = (this.a & 0xFFFF0000) >> 16;
            final int n2 = (this.a & 0xFF00) >> 8;
            final int n3 = this.a & 0xFF;
            int a = af.b(this.e);
            if (a == -1) {
                System.out.println("Message of type " + Integer.toString(this.e, 16) + " unknown.");
                a = this.a;
            }
            final int n4 = (a & 0xFFFF0000) >> 16;
            final int n5 = (a & 0xFF00) >> 8;
            final int n6 = a & 0xFF;
            if (a != this.a) {
                System.out.println("Message of type " + Integer.toString(this.e, 16) + " upgraded.");
                this.e = true;
                this.a = a;
            }
            final int int1 = dataInput.readInt();
            if (int1 > 1000) {
                System.err.println("Message: byteCount " + int1);
                throw new IllegalArgumentException("Message corrupted.");
            }
            if (int1 != 0) {
                dataInput.readFully(this.b = new byte[int1]);
            }
            this.c();
            if (this.f > 1000) {
                System.err.println("Message: itemCount " + this.f);
            }
            if (this.k != 1000) {
                this.a = new long[this.f + 1];
                for (int i = 0; i < this.a.length; ++i) {
                    this.a[i] = dataInput.readLong();
                }
                if (this.f > 0) {
                    if (this.b != null) {
                        for (int j = 0; j < this.f; ++j) {
                            for (int k = 0; k < n; ++k) {
                                if (k < n4) {
                                    this.b[j * n4 + k] = dataInput.readInt();
                                }
                                else {
                                    dataInput.readInt();
                                }
                            }
                        }
                    }
                    if (this.a != null) {
                        for (int l = 0; l < this.f; ++l) {
                            for (int n7 = 0; n7 < n2; ++n7) {
                                if (n7 < n5) {
                                    this.a[l * n5 + n7] = dataInput.readUTF();
                                    if ("\u0000".equals(this.a[l * n5 + n7])) {
                                        this.a[l * n5 + n7] = null;
                                    }
                                }
                                else {
                                    dataInput.readUTF();
                                }
                            }
                        }
                    }
                    if (this.a != null) {
                        for (int n8 = 0; n8 < this.f; ++n8) {
                            for (int n9 = 0; n9 < n3; ++n9) {
                                if (n9 < n6) {
                                    this.a[n8 * n6 + n9] = new M(dataInput);
                                    if (this.a[n8 * n6 + n9].a()) {
                                        this.a[n8 * n6 + n9] = null;
                                    }
                                }
                                else {
                                    new M(dataInput);
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
                this.a = new long[this.f + 1];
                for (int n10 = 0; n10 < this.a.length; ++n10) {
                    this.a[n10] = dataInputStream.readLong();
                }
                if (this.f > 0) {
                    if (this.b != null) {
                        for (int n11 = 0; n11 < this.f; ++n11) {
                            for (int n12 = 0; n12 < n; ++n12) {
                                if (n12 < n4) {
                                    this.b[n11 * n4 + n12] = dataInputStream.readInt();
                                }
                                else {
                                    dataInputStream.readInt();
                                }
                            }
                        }
                    }
                    if (this.a != null) {
                        for (int n13 = 0; n13 < this.f; ++n13) {
                            for (int n14 = 0; n14 < n2; ++n14) {
                                if (n14 < n5) {
                                    this.a[n13 * n5 + n14] = dataInputStream.readUTF();
                                    if ("\u0000".equals(this.a[n13 * n5 + n14])) {
                                        this.a[n13 * n5 + n14] = null;
                                    }
                                }
                                else {
                                    dataInputStream.readUTF();
                                }
                            }
                        }
                    }
                    if (this.a != null) {
                        for (int n15 = 0; n15 < this.f; ++n15) {
                            for (int n16 = 0; n16 < n3; ++n16) {
                                if (n16 < n6) {
                                    this.a[n15 * n6 + n16] = new M(dataInputStream);
                                    if (this.a[n15 * n6 + n16].a()) {
                                        this.a[n15 * n6 + n16] = null;
                                    }
                                }
                                else {
                                    new M(dataInputStream);
                                }
                            }
                        }
                    }
                }
                byteArrayInputStream.close();
            }
        }
        if (this.e == 67342) {
            aI.b(this);
        }
        else if (this.e == 67587) {
            V.url = this.a(0, 1);
        }
    }
    
    public V(final int e, final int a, final int f) {
        if (e == 67587) {
            V.p = 0;
        }
        this.e = false;
        this.u = -999;
        this.j = -999;
        this.k = -999;
        this.k = false;
        this.l = false;
        this.b = 0L;
        this.o = 0;
        if (f < 0) {
            throw new IllegalArgumentException("itemCount < 0");
        }
        if (a == -1) {
            throw new IllegalArgumentException("unknown message type");
        }
        this.e = e;
        this.f = f;
        this.a = a;
        if (f > 1000) {
            System.err.println("Message: itemCount2 " + f);
        }
        this.c();
    }
    
    static {
        V.p = 0;
    }
}
