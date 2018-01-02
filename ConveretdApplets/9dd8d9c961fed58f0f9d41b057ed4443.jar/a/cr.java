// 
// Decompiled by Procyon v0.5.30
// 

package a;

import java.io.DataInput;
import java.io.OutputStream;
import java.io.DataOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataOutput;

public final class cr
{
    private int r;
    private int t;
    private int y;
    private int[] q;
    private byte[] q;
    private String[] q;
    private cm[] q;
    private long[] q;
    public int q;
    public int w;
    private int u;
    public int e;
    public boolean q;
    public long q;
    private int i;
    
    private void q() {
        final int n = this.y >> 16;
        final int n2 = this.y >> 8 & 0xFF;
        final int n3 = this.y & 0xFF;
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
                this.q = new int[this.t * n];
            }
            if (n2 > 0) {
                this.q = new String[this.t * n2];
            }
            if (this.t() > 0) {
                this.q = new cm[this.t * n3];
            }
        }
        this.q = new long[this.t + 1];
    }
    
    public final int q() {
        return this.r;
    }
    
    public final int w() {
        return this.t;
    }
    
    public final int q(final int n, final int n2) {
        if (n < 0 || n >= this.t) {
            throw new ArrayIndexOutOfBoundsException("itemNumber is out-of-bounds: " + n);
        }
        if (n2 < 0 || n2 >= this.e()) {
            throw new ArrayIndexOutOfBoundsException("index is out-of-bounds: " + n2);
        }
        return this.q[n * this.e() + n2];
    }
    
    public final void q(final int n, final int n2, final int n3) {
        if (n < 0 || n >= this.t) {
            throw new ArrayIndexOutOfBoundsException("itemNumber is out-of-bounds: " + n);
        }
        if (n2 < 0 || n2 >= this.e()) {
            throw new ArrayIndexOutOfBoundsException("index is out-of-bounds: " + n2);
        }
        this.q[n * this.e() + n2] = n3;
    }
    
    public final byte[] q() {
        return this.q;
    }
    
    public final void q(final byte[] q) {
        this.q = q;
    }
    
    public final String q(final int n, final int n2) {
        if (n < 0 || n >= this.t) {
            throw new ArrayIndexOutOfBoundsException("itemNumber is out-of-bounds: " + n);
        }
        if (n2 < 0 || n2 >= this.r()) {
            throw new ArrayIndexOutOfBoundsException("index is out-of-bounds: " + n2);
        }
        return this.q[n * this.r() + n2];
    }
    
    public final void q(final int n, final int n2, final String s) {
        if (this.t <= 0) {
            throw new ArrayIndexOutOfBoundsException("itemNumber is out-of-bounds: " + n2);
        }
        if (n2 < 0 || n2 >= this.r()) {
            throw new ArrayIndexOutOfBoundsException("index is out-of-bounds: " + n2);
        }
        this.q[0 * this.r() + n2] = s;
    }
    
    public final cm q(final int n, final int n2) {
        if (n < 0 || n >= this.t) {
            throw new ArrayIndexOutOfBoundsException("itemNumber is out-of-bounds: " + n);
        }
        if (0 >= this.t()) {
            throw new ArrayIndexOutOfBoundsException("index is out-of-bounds: " + 0);
        }
        return this.q[n * this.t()];
    }
    
    public final void q(final int n, final int n2, final cm cm) {
        if (this.t <= 0) {
            throw new ArrayIndexOutOfBoundsException("itemNumber is out-of-bounds: " + 0);
        }
        if (0 >= this.t()) {
            throw new ArrayIndexOutOfBoundsException("index is out-of-bounds: " + 0);
        }
        this.q[0 * this.t()] = cm;
    }
    
    public static final boolean q(final long n, final int n2) {
        if (n2 < 0 || n2 > 63) {
            throw new ArrayIndexOutOfBoundsException("flag index must be between 0 and 63, inclusive: " + n2);
        }
        return (n & 1L << n2) != 0x0L;
    }
    
    public final boolean q(final int n, final int n2) {
        if (n < -1 || n >= this.t) {
            throw new ArrayIndexOutOfBoundsException("itemNumber is out-of-bounds: " + n);
        }
        return q(this.q[n + 1], n2);
    }
    
    public final long q(final int n) {
        if (n < -1 || n >= this.t) {
            throw new ArrayIndexOutOfBoundsException("itemNumber is out-of-bounds: " + n);
        }
        return this.q[n + 1];
    }
    
    public final void q(int n, int n2, final boolean b) {
        if (n < -1 || n >= this.t) {
            throw new ArrayIndexOutOfBoundsException("itemNumber is out-of-bounds: " + n);
        }
        final long[] q = this.q;
        final int n3 = n + 1;
        final long n4 = this.q[n + 1];
        final int n5 = n2;
        n2 = (b ? 1 : 0);
        n = n5;
        final long n6 = n4;
        if (n < 0 || n > 63) {
            throw new ArrayIndexOutOfBoundsException("flag index must be between 0 and 63, inclusive: " + n);
        }
        final long n7 = 1L << n;
        long n8;
        if (n2 != 0) {
            n8 = (n6 | n7);
        }
        else {
            n8 = (n6 & ~n7);
        }
        q[n3] = n8;
    }
    
    public final void q(final int n, final int n2) {
        this.q(0, n2, true);
    }
    
    public final int e() {
        return this.y >> 16;
    }
    
    private int r() {
        return this.y >> 8 & 0xFF;
    }
    
    private int t() {
        return this.y & 0xFF;
    }
    
    public final void q(final DataOutput dataOutput) {
        synchronized (dataOutput) {
            dataOutput.writeInt(this.r);
            dataOutput.writeInt(this.q);
            dataOutput.writeInt(this.w);
            dataOutput.writeInt(this.e);
            dataOutput.writeShort(this.t);
            dataOutput.writeInt(this.y);
            if (this.q != null) {
                dataOutput.writeInt(this.q.length);
                dataOutput.write(this.q);
            }
            else {
                dataOutput.writeInt(0);
            }
            for (int i = 0; i < this.q.length; ++i) {
                dataOutput.writeLong(this.q[i]);
            }
            if (this.q != null) {
                for (int j = 0; j < this.q.length; ++j) {
                    dataOutput.writeInt(this.q[j]);
                }
            }
            if (this.q != null) {
                for (int k = 0; k < this.q.length; ++k) {
                    if (this.q[k] == null) {
                        dataOutput.writeUTF("\u0000");
                    }
                    else {
                        dataOutput.writeUTF(this.q[k]);
                    }
                }
            }
            if (this.q != null) {
                for (int l = 0; l < this.q.length; ++l) {
                    if (this.q[l] == null) {
                        dataOutput.writeInt(0);
                    }
                    else {
                        final cm cm;
                        if ((cm = this.q[l]).q == null) {
                            dataOutput.writeInt(0);
                        }
                        else {
                            dataOutput.writeInt(cm.q.length);
                            dataOutput.write(cm.q);
                        }
                    }
                }
            }
        }
    }
    
    public final byte[] w() {
        final ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        final DataOutputStream dataOutputStream = new DataOutputStream(byteArrayOutputStream);
        try {
            this.q(dataOutputStream);
        }
        catch (Exception ex) {}
        return byteArrayOutputStream.toByteArray();
    }
    
    public final String toString() {
        final StringBuffer sb;
        (sb = new StringBuffer(50)).append("Message Type: ");
        sb.append(Integer.toHexString(this.r));
        sb.append("  Item Count: ");
        sb.append(String.valueOf(this.t));
        sb.append("  toUser: ");
        sb.append(String.valueOf(this.w));
        sb.append("  toRoom: ");
        sb.append(String.valueOf(this.q));
        sb.append("  toWhom: ");
        sb.append(String.valueOf(0));
        sb.append("  flags: ");
        sb.append(Long.toBinaryString(this.q[0]));
        sb.append("\n  ");
        sb.append("flags: ");
        for (int i = 0; i < this.q.length - 1; ++i) {
            sb.append("\n    ");
            sb.append(Long.toBinaryString(this.q[i + 1]));
        }
        if (this.q != null) {
            sb.append("\n  ");
            sb.append("ints: ");
            sb.append(this.e());
            for (int j = 0; j < this.q.length; ++j) {
                if (j % this.e() == 0) {
                    sb.append("\n");
                }
                sb.append("    ");
                sb.append(String.valueOf(this.q[j]));
            }
        }
        if (this.q != null) {
            sb.append("\n  ");
            sb.append("bytes: ");
            sb.append(this.q.length);
            for (int k = 0; k < this.q.length; ++k) {
                if (k % 16 == 0) {
                    sb.append("\n");
                }
                sb.append(" ");
                sb.append(Integer.toHexString(this.q[k]));
            }
        }
        if (this.q != null) {
            sb.append("\n  ");
            sb.append("strings: ");
            sb.append(this.r());
            for (int l = 0; l < this.q.length; ++l) {
                if (l % this.r() == 0) {
                    sb.append("\n");
                }
                sb.append("    ");
                sb.append(this.q[l]);
            }
        }
        if (this.q != null) {
            sb.append("\n  ");
            sb.append("passwords: ");
            sb.append(this.t());
            for (int n = 0; n < this.q.length; ++n) {
                if (n % this.t() == 0) {
                    sb.append("\n");
                }
                sb.append("    ");
                sb.append(this.q[n]);
            }
        }
        sb.append("\n  ");
        return sb.toString();
    }
    
    public cr() {
        this.u = 0;
        this.q = -999;
        this.w = -999;
        this.e = -999;
        this.q = false;
        this.q = System.currentTimeMillis();
        this.i = 0;
    }
    
    public cr(final int n, final int n2) {
        this(n, cs.q(n), 1);
    }
    
    public cr(final DataInput dataInput) {
        this();
        synchronized (dataInput) {
            this.r = dataInput.readInt();
            this.q = dataInput.readInt();
            this.w = dataInput.readInt();
            this.e = dataInput.readInt();
            this.t = dataInput.readShort();
            this.y = dataInput.readInt();
            final int n = this.y >> 16;
            final int n2 = this.y >> 8 & 0xFF;
            final int n3 = this.y & 0xFF;
            int y;
            if ((y = cs.q(this.r)) == -1) {
                y = this.y;
            }
            final int n4 = y >> 16;
            final int n5 = y >> 8 & 0xFF;
            final int n6 = y & 0xFF;
            if (y != this.y) {
                this.y = y;
            }
            final int int1;
            if ((int1 = dataInput.readInt()) > 1000) {
                System.err.println("Message: byteCount " + int1);
                throw new IllegalArgumentException("Message corrupted.");
            }
            if (int1 > 0) {
                dataInput.readFully(this.q = new byte[int1]);
            }
            this.q();
            this.q = new long[this.t + 1];
            for (int i = 0; i < this.q.length; ++i) {
                this.q[i] = dataInput.readLong();
            }
            if (this.t > 0) {
                if (this.q != null) {
                    for (int j = 0; j < this.t; ++j) {
                        for (int k = 0; k < n; ++k) {
                            if (k < n4) {
                                this.q[j * n4 + k] = dataInput.readInt();
                            }
                            else {
                                dataInput.readInt();
                            }
                        }
                    }
                }
                if (this.q != null) {
                    for (int l = 0; l < this.t; ++l) {
                        for (int n7 = 0; n7 < n2; ++n7) {
                            if (n7 < n5) {
                                this.q[l * n5 + n7] = dataInput.readUTF();
                                if ("\u0000".equals(this.q[l * n5 + n7])) {
                                    this.q[l * n5 + n7] = null;
                                }
                            }
                            else {
                                dataInput.readUTF();
                            }
                        }
                    }
                }
                if (this.q != null) {
                    for (int n8 = 0; n8 < this.t; ++n8) {
                        for (int n9 = 0; n9 < n3; ++n9) {
                            if (n9 < n6) {
                                this.q[n8 * n6 + n9] = new cm(dataInput);
                                if (this.q[n8 * n6 + n9].q()) {
                                    this.q[n8 * n6 + n9] = null;
                                }
                            }
                            else {
                                new cm(dataInput);
                            }
                        }
                    }
                }
            }
        }
    }
    
    private cr(final int r, final int y, final int t) {
        this();
        if (t < 0) {
            throw new IllegalArgumentException("itemCount < 0");
        }
        if (y == -1) {
            throw new IllegalArgumentException("unknown message type");
        }
        this.r = r;
        this.t = t;
        this.y = y;
        this.q();
    }
}
