// 
// Decompiled by Procyon v0.5.30
// 

package chat;

import java.io.DataInputStream;
import java.io.InputStream;
import java.util.zip.InflaterInputStream;
import java.io.ByteArrayInputStream;
import java.io.DataInput;

public final class m
{
    public int a;
    public int b;
    public int c;
    public int[] a;
    public byte[] a;
    public String[] a;
    public ap[] a;
    public long[] a;
    public int d;
    public int e;
    public int f;
    public static int g;
    
    private void a() {
        final int n = this.c >> 16;
        final int n2 = this.c >> 8 & 0xFF;
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
                this.a = new int[this.b * n];
            }
            if (n2 > 0) {
                this.a = new String[this.b * n2];
            }
            if (this.a() > 0) {
                this.a = new ap[this.b * n3];
            }
        }
        if (this.a == 67335 || this.a == 67334 || this.a == 67584) {
            this.a = new long[2 * this.b + 2];
            return;
        }
        this.a = new long[this.b + 1];
    }
    
    public final int a(final int n, final int n2) {
        if (n < 0 || n >= this.b) {
            throw new ArrayIndexOutOfBoundsException("itemNumber is out-of-bounds: " + n);
        }
        if (n2 < 0 || n2 >= this.c >> 16) {
            throw new ArrayIndexOutOfBoundsException("index is out-of-bounds: " + n2);
        }
        return this.a[n * (this.c >> 16) + n2];
    }
    
    public final void a(final int n, final int n2, final int n3) {
        if (n < 0 || n >= this.b) {
            throw new ArrayIndexOutOfBoundsException("itemNumber is out-of-bounds: " + n);
        }
        if (n2 < 0 || n2 >= this.c >> 16) {
            throw new ArrayIndexOutOfBoundsException("index is out-of-bounds: " + n2);
        }
        this.a[n * (this.c >> 16) + n2] = n3;
    }
    
    public final String a(final int n, final int n2) {
        if (n < 0 || n >= this.b) {
            throw new ArrayIndexOutOfBoundsException("itemNumber is out-of-bounds: " + n);
        }
        if (n2 < 0 || n2 >= this.b()) {
            throw new ArrayIndexOutOfBoundsException("index is out-of-bounds: " + n2);
        }
        return this.a[n * this.b() + n2];
    }
    
    public final void a(final int n, final String s) {
        if (this.b <= 0) {
            throw new ArrayIndexOutOfBoundsException("itemNumber is out-of-bounds: " + 0);
        }
        if (n < 0 || n >= this.b()) {
            throw new ArrayIndexOutOfBoundsException("index is out-of-bounds: " + n);
        }
        this.a[0 * this.b() + n] = s;
    }
    
    public final void a(final ap ap) {
        if (this.b <= 0) {
            throw new ArrayIndexOutOfBoundsException("itemNumber is out-of-bounds: " + 0);
        }
        if (0 >= this.a()) {
            throw new ArrayIndexOutOfBoundsException("index is out-of-bounds: " + 0);
        }
        this.a[0 * this.a()] = ap;
    }
    
    public static final boolean a(final long n, final int n2) {
        if (n2 < 0 || n2 > 63) {
            throw new ArrayIndexOutOfBoundsException("flag index must be between 0 and 63, inclusive: " + n2);
        }
        return (n & 1L << n2) != 0x0L;
    }
    
    public final boolean a(final int n, final int n2) {
        if (n < -1 || n >= this.b) {
            throw new ArrayIndexOutOfBoundsException("itemNumber is out-of-bounds: " + n);
        }
        return a(this.a[n + 1], n2);
    }
    
    public final long a(final int n) {
        if (n < -1 || n >= this.b) {
            throw new ArrayIndexOutOfBoundsException("itemNumber is out-of-bounds: " + n);
        }
        return this.a[n + 1];
    }
    
    public final void a(int n, final int n2, final boolean b) {
        if (this.b <= 0) {
            throw new ArrayIndexOutOfBoundsException("itemNumber is out-of-bounds: " + 0);
        }
        final long[] a = this.a;
        final int n3 = 1;
        final long n4 = this.a[1];
        n = (b ? 1 : 0);
        final long n5 = n4;
        if (n2 < 0 || n2 > 63) {
            throw new ArrayIndexOutOfBoundsException("flag index must be between 0 and 63, inclusive: " + n2);
        }
        final long n6 = 1L << n2;
        long n7;
        if (n != 0) {
            n7 = (n5 | n6);
        }
        else {
            n7 = (n5 & ~n6);
        }
        a[n3] = n7;
    }
    
    private int b() {
        return this.c >> 8 & 0xFF;
    }
    
    public final int a() {
        return this.c & 0xFF;
    }
    
    public final String toString() {
        final StringBuffer sb;
        (sb = new StringBuffer(50)).append("Message Type: ");
        sb.append(Integer.toHexString(this.a));
        sb.append("  Item Count: ");
        sb.append(String.valueOf(this.b));
        sb.append("  toUser: ");
        sb.append(String.valueOf(this.e));
        sb.append("  toRoom: ");
        sb.append(String.valueOf(this.d));
        sb.append("  flags: ");
        if (this.a == 67335 || this.a == 67334 || this.a == 67584) {
            sb.append(Long.toBinaryString(this.a[0]) + " " + Long.toBinaryString(this.a[1]));
        }
        else {
            sb.append(Long.toBinaryString(this.a[0]));
        }
        sb.append("\n  ");
        sb.append("flags: ");
        if (this.a == 67335 || this.a == 67334 || this.a == 67584) {
            for (int i = 0; i < this.a.length - 2; ++i) {
                sb.append("\n    ");
                sb.append(Long.toBinaryString(this.a[i + 2]));
            }
        }
        else {
            for (int j = 0; j < this.a.length - 1; ++j) {
                sb.append("\n    ");
                sb.append(Long.toBinaryString(this.a[j + 1]));
            }
        }
        if (this.a != null) {
            sb.append("\n  ");
            sb.append("ints: ");
            sb.append(this.c >> 16);
            for (int k = 0; k < this.a.length; ++k) {
                if (k % (this.c >> 16) == 0) {
                    sb.append("\n");
                }
                sb.append("    ");
                sb.append(String.valueOf(this.a[k]));
            }
        }
        if (this.a != null) {
            sb.append("\n  ");
            sb.append("bytes: ");
            sb.append(this.a.length);
            for (int l = 0; l < this.a.length; ++l) {
                if (l % 16 == 0) {
                    sb.append("\n");
                }
                sb.append(" ");
                sb.append(Integer.toHexString(this.a[l]));
            }
        }
        if (this.a != null) {
            sb.append("\n  ");
            sb.append("strings: ");
            sb.append(this.b());
            for (int n = 0; n < this.a.length; ++n) {
                if (n % this.b() == 0) {
                    sb.append("\n");
                }
                sb.append("    ");
                sb.append(this.a[n]);
            }
        }
        if (this.a != null) {
            sb.append("\n  ");
            sb.append("passwords: ");
            sb.append(this.a());
            for (int n2 = 0; n2 < this.a.length; ++n2) {
                if (n2 % this.a() == 0) {
                    sb.append("\n");
                }
                sb.append("    ");
                sb.append(this.a[n2]);
            }
        }
        sb.append("\n  ");
        return sb.toString();
    }
    
    public final long[] a(final int n) {
        if (n < -1 || n >= this.b) {
            throw new ArrayIndexOutOfBoundsException("itemNumber is out-of-bounds: " + n);
        }
        return new long[] { this.a[(n << 1) + 2], this.a[(n << 1) + 3] };
    }
    
    public m() {
        this.d = -999;
        this.e = -999;
        this.f = -999;
    }
    
    public m(final int n, final int n2) {
        this(n, Y.a(n), n2);
        if (n == 71427) {
            m.g = 0;
        }
    }
    
    public m(final DataInput dataInput) {
        this.d = -999;
        this.e = -999;
        this.f = -999;
        synchronized (dataInput) {
            final byte[] array = new byte[dataInput.readInt()];
            dataInput.readFully(array, 0, array.length);
            final DataInputStream dataInputStream = new DataInputStream(new InflaterInputStream(new ByteArrayInputStream(array)));
            this.a = dataInputStream.readInt();
            this.d = dataInputStream.readInt();
            this.e = dataInputStream.readInt();
            this.f = dataInputStream.readInt();
            this.b = dataInputStream.readShort();
            this.c = dataInputStream.readInt();
            final int n = this.c >> 16;
            final int n2 = this.c >> 8 & 0xFF;
            final int n3 = this.c & 0xFF;
            int c;
            if ((c = Y.a(this.a)) == -1) {
                System.out.println("Message of type " + Integer.toString(this.a, 16) + " unknown.");
                c = this.c;
            }
            final int n4 = c >> 16;
            final int n5 = c >> 8 & 0xFF;
            final int n6 = c & 0xFF;
            if (c != this.c) {
                System.out.println("Message of type " + Integer.toString(this.a, 16) + " upgraded.");
                this.c = c;
            }
            final int int1;
            if ((int1 = dataInputStream.readInt()) > 1000) {
                System.err.println("Message: byteCount " + int1);
                throw new IllegalArgumentException("Message corrupted.");
            }
            if (int1 != 0) {
                dataInputStream.readFully(this.a = new byte[int1]);
            }
            this.a();
            if (this.b > 1000) {
                System.err.println("Message: itemCount " + this.b);
            }
            this.a = new long[this.b + 1];
            if (this.a == 67335 || this.a == 67334 || this.a == 67584 || this.a == 67588) {
                this.a = new long[2 * this.b + 2];
                if (this.f == 0) {
                    for (int i = 0; i < this.a.length; ++i) {
                        this.a[i] = dataInputStream.readLong();
                    }
                }
                else {
                    for (int j = 0; j < this.b + 1; ++j) {
                        this.a[j << 1] = dataInputStream.readLong();
                        this.a[(j << 1) + 1] = 0L;
                    }
                }
            }
            else {
                for (int k = 0; k < this.a.length; ++k) {
                    this.a[k] = dataInputStream.readLong();
                }
            }
            if (this.b > 0) {
                if (this.a != null) {
                    for (int l = 0; l < this.b; ++l) {
                        for (int n7 = 0; n7 < n; ++n7) {
                            if (n7 < n4) {
                                this.a[l * n4 + n7] = dataInputStream.readInt();
                            }
                            else {
                                dataInputStream.readInt();
                            }
                        }
                    }
                }
                if (this.a != null) {
                    for (int n8 = 0; n8 < this.b; ++n8) {
                        for (int n9 = 0; n9 < n2; ++n9) {
                            if (n9 < n5) {
                                this.a[n8 * n5 + n9] = dataInputStream.readUTF();
                                if ("\u0000".equals(this.a[n8 * n5 + n9])) {
                                    this.a[n8 * n5 + n9] = null;
                                }
                            }
                            else {
                                dataInputStream.readUTF();
                            }
                        }
                    }
                }
                if (this.a != null) {
                    for (int n10 = 0; n10 < this.b; ++n10) {
                        for (int n11 = 0; n11 < n3; ++n11) {
                            if (n11 < n6) {
                                this.a[n10 * n6 + n11] = new ap(dataInputStream);
                                if (this.a[n10 * n6 + n11].a()) {
                                    this.a[n10 * n6 + n11] = null;
                                }
                            }
                            else {
                                new ap(dataInputStream);
                            }
                        }
                    }
                }
            }
        }
    }
    
    private m(final int a, final int c, final int b) {
        this.d = -999;
        this.e = -999;
        this.f = -999;
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
        if (this.a == 67335 || this.a == 67334) {
            this.f = 0;
        }
    }
}
