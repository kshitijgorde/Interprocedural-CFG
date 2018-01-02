// 
// Decompiled by Procyon v0.5.30
// 

package a;

import java.io.EOFException;
import java.io.DataInput;
import java.io.OutputStream;
import java.io.DataOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataOutput;

public class cJ
{
    private int t;
    private int y;
    private int u;
    private int[] q;
    private byte[] q;
    private String[] q;
    private cH[] q;
    private long[] q;
    private long[] w;
    public int q;
    public int w;
    private int i;
    public int e;
    public boolean q;
    private boolean w;
    public long q;
    public int r;
    
    private void q() {
        final int n = this.u >> 16;
        final int n2 = this.u >> 8 & 0xFF;
        final int n3 = this.u & 0xFF;
        if (n > 100) {
            System.out.println("Message: intCount " + n);
        }
        if (n2 > 100) {
            System.out.println("Message: stringCount " + n2);
        }
        if (n3 > 100) {
            System.out.println("Message: passwordCount " + n3);
        }
        if (this.y > 0) {
            if (n > 0) {
                this.q = new int[this.y * n];
            }
            if (n2 > 0) {
                this.q = new String[this.y * n2];
            }
            if (this.t() > 0) {
                this.q = new cH[this.y * n3];
            }
        }
        this.q = new long[this.y + 1];
        if (this.q()) {
            this.w = new long[this.y + 1];
        }
    }
    
    public final int q() {
        return this.t;
    }
    
    public final int w() {
        return this.y;
    }
    
    public final int q(final int n, final int n2) {
        if (n < 0 || n >= this.y) {
            throw new ArrayIndexOutOfBoundsException("itemNumber is out-of-bounds: " + n);
        }
        if (n2 < 0 || n2 >= this.e()) {
            throw new ArrayIndexOutOfBoundsException("index is out-of-bounds: " + n2);
        }
        return this.q[n * this.e() + n2];
    }
    
    public final void q(final int n, final int n2, final int n3) {
        if (n < 0 || n >= this.y) {
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
        if (n < 0 || n >= this.y) {
            throw new ArrayIndexOutOfBoundsException("itemNumber is out-of-bounds: " + n);
        }
        if (n2 < 0 || n2 >= this.r()) {
            throw new ArrayIndexOutOfBoundsException("index is out-of-bounds: " + n2);
        }
        return this.q[n * this.r() + n2];
    }
    
    public final void q(final int n, final int n2, final String s) {
        if (this.y <= 0) {
            throw new ArrayIndexOutOfBoundsException("itemNumber is out-of-bounds: " + n2);
        }
        if (n2 < 0 || n2 >= this.r()) {
            throw new ArrayIndexOutOfBoundsException("index is out-of-bounds: " + n2);
        }
        this.q[0 * this.r() + n2] = s;
    }
    
    public final cH q(final int n, final int n2) {
        if (n < 0 || n >= this.y) {
            throw new ArrayIndexOutOfBoundsException("itemNumber is out-of-bounds: " + n);
        }
        if (0 >= this.t()) {
            throw new ArrayIndexOutOfBoundsException("index is out-of-bounds: " + 0);
        }
        return this.q[n * this.t()];
    }
    
    public final void q(final int n, final int n2, final cH ch) {
        if (this.y <= 0) {
            throw new ArrayIndexOutOfBoundsException("itemNumber is out-of-bounds: " + 0);
        }
        if (0 >= this.t()) {
            throw new ArrayIndexOutOfBoundsException("index is out-of-bounds: " + 0);
        }
        this.q[0 * this.t()] = ch;
    }
    
    public static boolean q(final long n, final int n2) {
        if (n2 < 0 || n2 > 63) {
            throw new ArrayIndexOutOfBoundsException("flag index must be between 0 and 63, inclusive: " + n2);
        }
        return (n & 1L << n2) != 0x0L;
    }
    
    public final boolean q(final int n, final int n2) {
        if (n < -1 || n >= this.y) {
            throw new ArrayIndexOutOfBoundsException("itemNumber is out-of-bounds: " + n);
        }
        return q(this.q[n + 1], n2);
    }
    
    public final long q(final int n) {
        if (n < -1 || n >= this.y) {
            throw new ArrayIndexOutOfBoundsException("itemNumber is out-of-bounds: " + n);
        }
        return this.q[n + 1];
    }
    
    public final void q(int n, int n2, final boolean b) {
        if (n < -1 || n >= this.y) {
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
    
    public final long[] q(final int n) {
        if (n < -1 || n >= this.y) {
            throw new ArrayIndexOutOfBoundsException("itemNumber is out-of-bounds: " + n);
        }
        return new long[] { this.q[n + 1], this.q() ? this.w[n + 1] : 0L };
    }
    
    private int e() {
        return this.u >> 16;
    }
    
    private int r() {
        return this.u >> 8 & 0xFF;
    }
    
    private int t() {
        return this.u & 0xFF;
    }
    
    public void q(final DataOutput dataOutput) {
        synchronized (dataOutput) {
            dataOutput.writeInt(this.t);
            dataOutput.writeInt(this.q);
            dataOutput.writeInt(this.w);
            dataOutput.writeInt(this.e);
            dataOutput.writeShort(this.y);
            dataOutput.writeInt(this.u);
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
                    if (17237249 == this.t && k == 182) {
                        System.out.println("utf=" + this.q[k]);
                    }
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
                        this.q[l].q(dataOutput);
                    }
                }
            }
            if (this.q()) {
                for (int n = 0; n < this.w.length; ++n) {
                    dataOutput.writeLong(this.w[n]);
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
    
    public String toString() {
        final StringBuffer sb;
        (sb = new StringBuffer(50)).append("Message Type: ");
        sb.append(Integer.toHexString(this.t));
        sb.append("  Item Count: ");
        sb.append(String.valueOf(this.y));
        sb.append("  toUser: ");
        sb.append(String.valueOf(this.w));
        sb.append("  toRoom: ");
        sb.append(String.valueOf(this.q));
        sb.append("  toWhom: ");
        sb.append(String.valueOf(0));
        sb.append("  flags: ");
        sb.append(Long.toBinaryString(this.q[0]));
        if (this.q()) {
            sb.append(" " + Long.toBinaryString(this.w[0]));
        }
        sb.append("\n  ");
        sb.append("flags: ");
        if (this.q()) {
            for (int i = 0; i < this.q.length - 1; ++i) {
                sb.append("\n    ");
                sb.append(Long.toBinaryString(this.q[i + 1]));
                sb.append(" ");
                sb.append(Long.toBinaryString(this.w[i + 1]));
            }
        }
        else {
            for (int j = 0; j < this.q.length - 1; ++j) {
                sb.append("\n    ");
                sb.append(Long.toBinaryString(this.q[j + 1]));
            }
        }
        if (this.q != null) {
            sb.append("\n  ");
            sb.append("ints: ");
            sb.append(this.e());
            for (int k = 0; k < this.q.length; ++k) {
                if (k % this.e() == 0) {
                    sb.append("\n");
                }
                sb.append("    ");
                sb.append(String.valueOf(this.q[k]));
            }
        }
        if (this.q != null) {
            sb.append("\n  ");
            sb.append("bytes: ");
            sb.append(this.q.length);
            for (int l = 0; l < this.q.length; ++l) {
                if (l % 16 == 0) {
                    sb.append("\n");
                }
                sb.append(" ");
                sb.append(Integer.toHexString(this.q[l]));
            }
        }
        if (this.q != null) {
            sb.append("\n  ");
            sb.append("strings: ");
            sb.append(this.r());
            for (int n = 0; n < this.q.length; ++n) {
                if (n % this.r() == 0) {
                    sb.append("\n");
                }
                sb.append("    ");
                sb.append(this.q[n]);
            }
        }
        if (this.q != null) {
            sb.append("\n  ");
            sb.append("passwords: ");
            sb.append(this.t());
            for (int n2 = 0; n2 < this.q.length; ++n2) {
                if (n2 % this.t() == 0) {
                    sb.append("\n");
                }
                sb.append("    ");
                sb.append(this.q[n2]);
            }
        }
        sb.append("\n  ");
        return sb.toString();
    }
    
    public cJ() {
        this.w = new long[0];
        this.i = 0;
        this.q = -999;
        this.w = -999;
        this.e = -999;
        this.q = false;
        this.w = false;
        this.q = System.currentTimeMillis();
        this.r = 0;
    }
    
    public cJ(final int n, final int n2) {
        this(n, cK.q(n), 1);
    }
    
    public final void q(final DataInput dataInput) {
        synchronized (dataInput) {
            this.t = dataInput.readInt();
            this.q = dataInput.readInt();
            this.w = dataInput.readInt();
            this.e = dataInput.readInt();
            this.y = dataInput.readShort();
            this.u = dataInput.readInt();
            final int n = this.u >> 16;
            final int n2 = this.u >> 8 & 0xFF;
            final int n3 = this.u & 0xFF;
            int u;
            if ((u = cK.q(this.t)) == -1) {
                u = this.u;
            }
            final int n4 = u >> 16;
            final int n5 = u >> 8 & 0xFF;
            final int n6 = u & 0xFF;
            if (u != this.u) {
                this.u = u;
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
            for (int i = 0; i < this.q.length; ++i) {
                this.q[i] = dataInput.readLong();
            }
            if (this.y > 0) {
                if (this.q != null) {
                    for (int j = 0; j < this.y; ++j) {
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
                    for (int l = 0; l < this.y; ++l) {
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
                    for (int n8 = 0; n8 < this.y; ++n8) {
                        for (int n9 = 0; n9 < n3; ++n9) {
                            if (n9 < n6) {
                                this.q[n8 * n6 + n9] = new cH(dataInput);
                                if (this.q[n8 * n6 + n9].q()) {
                                    this.q[n8 * n6 + n9] = null;
                                }
                            }
                            else {
                                new cH(dataInput);
                            }
                        }
                    }
                }
                try {
                    if (this.q()) {
                        for (int n10 = 0; n10 < this.w.length; ++n10) {
                            this.w[n10] = dataInput.readLong();
                        }
                    }
                }
                catch (EOFException ex) {}
            }
        }
    }
    
    private cJ(final int t, final int u, final int y) {
        this();
        if (y < 0) {
            throw new IllegalArgumentException("itemCount < 0");
        }
        if (u == -1) {
            throw new IllegalArgumentException("unknown message type");
        }
        this.t = t;
        this.y = y;
        this.u = u;
        this.q();
    }
    
    public cJ(final cJ cj) {
        this(cj.t, cj.u, cj.y);
        final int n = this.u >> 16;
        final int n2 = this.u >> 8 & 0xFF;
        final int n3 = this.u & 0xFF;
        for (int i = 0; i < n * this.y; ++i) {
            this.q[i] = cj.q[i];
        }
        for (int j = 0; j < n2 * this.y; ++j) {
            this.q[j] = cj.q[j];
        }
        for (int k = 0; k < n3 * this.y; ++k) {
            this.q[k] = cj.q[k];
        }
        for (int l = 0; l <= this.y; ++l) {
            this.q[l] = cj.q[l];
        }
        if (cj.w.length > 0) {
            for (int n4 = 0; n4 <= this.y; ++n4) {
                this.w[n4] = cj.w[n4];
            }
        }
        this.q = cj.q;
        this.w = cj.w;
        this.e = cj.e;
        this.q = cj.q;
        this.w = cj.w;
        this.q = cj.q;
        this.r = cj.r;
    }
    
    private boolean q() {
        switch (this.t) {
            case 67334:
            case 67335:
            case 67584:
            case 17237761: {
                return true;
            }
            default: {
                return false;
            }
        }
    }
}
