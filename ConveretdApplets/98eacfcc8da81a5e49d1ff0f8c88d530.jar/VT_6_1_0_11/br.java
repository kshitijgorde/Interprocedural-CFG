// 
// Decompiled by Procyon v0.5.30
// 

package VT_6_1_0_11;

import java.io.OutputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ByteArrayInputStream;
import java.io.InputStream;

public final class br extends g
{
    private int j;
    private int k;
    private int l;
    private int m;
    private int n;
    private int o;
    private int p;
    private byte[] q;
    private int r;
    private boolean s;
    
    private br(final int j, final int k, final int l, final int m, final int n, final int o, final byte[] q) {
        this.s = false;
        this.j = j;
        this.k = k;
        this.l = l;
        this.m = m;
        this.n = n;
        this.o = o;
        if (q != null) {
            this.s = true;
            this.p = q.length;
            this.q = q;
        }
        else {
            this.s = false;
        }
        this.h = this.g();
    }
    
    public br(final int n, final int n2, final int n3, final int n4, final int n5, final int n6, final int n7) {
        this(n, n2, n3, n4, n5, n6, VT_6_1_0_11.g.a((short)n7));
    }
    
    public br(final int n, final int n2, final int n3, final int n4) {
        this(n, n2, n3, n2 * n4 / 8 * n3, n2 * n4 / 8, n4, null);
    }
    
    public br(final InputStream inputStream) {
        this.s = false;
        this.a(inputStream);
    }
    
    protected final void a() {
        final ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(this.h);
        this.i = this.h.length;
        try {
            final byte[] array = new byte[2];
            byteArrayInputStream.read(array);
            this.j = VT_6_1_0_11.g.b(array);
            byteArrayInputStream.read(array);
            this.k = VT_6_1_0_11.g.b(array);
            final byte[] array2 = new byte[4];
            byteArrayInputStream.read(array2);
            this.l = VT_6_1_0_11.g.a(array2);
            byteArrayInputStream.read(array2);
            this.m = VT_6_1_0_11.g.a(array2);
            final byte[] array3 = new byte[2];
            byteArrayInputStream.read(array3);
            this.n = VT_6_1_0_11.g.b(array3);
            byteArrayInputStream.read(array3);
            this.o = VT_6_1_0_11.g.b(array3);
            if (this.i > 17) {
                this.s = true;
                byteArrayInputStream.read(array3);
                this.p = VT_6_1_0_11.g.b(array3);
                byteArrayInputStream.read(this.q = new byte[this.p]);
                if (this.j == 49 && this.p == 2) {
                    this.r = VT_6_1_0_11.g.b(this.q);
                }
            }
            else if (this.i > 16) {
                throw new y();
            }
        }
        catch (IOException ex) {}
    }
    
    private byte[] g() {
        if (this.s) {
            if (this.p < 0) {
                throw new IllegalArgumentException();
            }
            this.i = 18 + this.p;
        }
        else {
            this.i = 16;
        }
        final ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream(this.i);
        try {
            byteArrayOutputStream.write(VT_6_1_0_11.g.a((short)this.j));
            byteArrayOutputStream.write(VT_6_1_0_11.g.a((short)this.k));
            byteArrayOutputStream.write(VT_6_1_0_11.g.a(this.l));
            byteArrayOutputStream.write(VT_6_1_0_11.g.a(this.m));
            byteArrayOutputStream.write(VT_6_1_0_11.g.a((short)this.n));
            byteArrayOutputStream.write(VT_6_1_0_11.g.a((short)this.o));
            if (this.s) {
                byteArrayOutputStream.write(VT_6_1_0_11.g.a((short)this.p));
                byteArrayOutputStream.write(this.q);
            }
            return byteArrayOutputStream.toByteArray();
        }
        catch (IOException ex) {
            return byteArrayOutputStream.toByteArray();
        }
        finally {
            try {
                byteArrayOutputStream.close();
            }
            catch (IOException ex2) {}
        }
    }
    
    public final void a(final OutputStream outputStream) {
        outputStream.write(br.a);
        outputStream.write(VT_6_1_0_11.g.a(this.i));
    }
    
    public final int a(final byte[] array, final int n) {
        System.arraycopy(br.a, 0, array, n, 4);
        System.arraycopy(VT_6_1_0_11.g.a(this.i), 0, array, n + 4, 4);
        return 8;
    }
    
    public final int b() {
        return this.j;
    }
    
    public final int c() {
        return this.k;
    }
    
    public final int d() {
        return this.l;
    }
    
    public final int e() {
        return this.n;
    }
    
    public final int f() {
        return this.o;
    }
    
    public final String toString() {
        final StringBuffer sb;
        (sb = new StringBuffer("    FORMAT CHUNK\n")).append("  HEADER\n");
        sb.append("Chunk identifier     (4): ").append("fmt ").append("\n");
        sb.append("Chunk size           (4): ").append(this.i).append("\n");
        sb.append("  DATA\n");
        sb.append("Format Type          (2): ").append(this.j).append("\n");
        sb.append("Number of channels   (2): ").append(this.k).append("\n");
        sb.append("Sample Rate          (4): ").append(this.l).append("\n");
        sb.append("Avg Bytes per second (4): ").append(this.m).append("\n");
        sb.append("Block Size           (2): ").append(this.n).append("\n");
        sb.append("Num bits per sample  (2): ").append(this.o).append("\n");
        if (this.s) {
            sb.append("  EXTENDED\n");
            sb.append("Extra Byte Size      (2): ").append(this.p).append("\n");
            if (this.j == 49 && this.p == 2) {
                sb.append("Samples per block    (2): ").append(this.r).append("\n");
            }
        }
        return sb.toString();
    }
}
