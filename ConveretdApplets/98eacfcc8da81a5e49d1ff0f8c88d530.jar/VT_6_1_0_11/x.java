// 
// Decompiled by Procyon v0.5.30
// 

package VT_6_1_0_11;

import java.io.IOException;
import java.io.DataInputStream;
import java.io.ByteArrayInputStream;
import java.io.OutputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;

public class x extends g
{
    private static byte[] j;
    private static byte[] k;
    private br l;
    private aW m;
    private ae n;
    private dq o;
    private cX p;
    private A q;
    
    protected x() {
    }
    
    public x(final int n, final int n2, final int n3, final int n4, final int n5, final int n6, final int n7, final int n8, final byte[] array, final int n9) {
        this.l = new br(49, 1, n3, n4, 65, 0, 320);
        this.m = new aW(n8, null);
        this.q = new A(n9);
        this.h = this.d();
        this.i = this.h.length + n9 + 4;
    }
    
    public x(final int n, final int n2, final int n3, final int n4, final int n5, final int n6) {
        this.l = new br(1, n2, n3, n4);
        this.m = new aW(n5);
        this.q = new A(n6);
        this.h = this.d();
        this.i = this.h.length + n6 + 4;
    }
    
    public x(final InputStream inputStream) {
        final byte[] array = new byte[4];
        inputStream.read(array);
        if (!VT_6_1_0_11.g.a(array, x.j)) {
            throw new y("This audio stream does not appear to be a RIFF stream.");
        }
        inputStream.read(array);
        this.i = VT_6_1_0_11.g.a(array);
        inputStream.read(array);
        if (!VT_6_1_0_11.g.a(array, x.k)) {
            throw new y("This audio stream does not appear to be an Audio .wav stream.");
        }
        this.a(inputStream);
    }
    
    protected void a(final InputStream inputStream) {
        final ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream(60);
        final byte[] array = new byte[4];
        inputStream.read(array);
        while (!VT_6_1_0_11.g.a(array, x.g)) {
            if (VT_6_1_0_11.g.a(array, x.a)) {
                (this.l = new br(inputStream)).b(byteArrayOutputStream);
            }
            else if (VT_6_1_0_11.g.a(array, x.b)) {
                (this.m = new aW(inputStream)).b(byteArrayOutputStream);
            }
            else if (VT_6_1_0_11.g.a(array, x.c)) {
                (this.n = new ae(inputStream)).b(byteArrayOutputStream);
            }
            else if (VT_6_1_0_11.g.a(array, x.d)) {
                (this.o = new dq(inputStream)).b(byteArrayOutputStream);
            }
            else if (VT_6_1_0_11.g.a(array, x.e)) {
                (this.p = new cX(inputStream)).b(byteArrayOutputStream);
            }
            inputStream.read(array);
        }
        inputStream.read(array);
        (this.q = new A(VT_6_1_0_11.g.a(array))).a(byteArrayOutputStream);
        this.h = byteArrayOutputStream.toByteArray();
    }
    
    protected final void a() {
        try {
            this.a(new DataInputStream(new ByteArrayInputStream(this.h)));
        }
        catch (IOException ex) {}
    }
    
    private byte[] d() {
        final ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream(60);
        try {
            if (this.l != null) {
                this.l.b(byteArrayOutputStream);
            }
            if (this.m != null) {
                this.m.b(byteArrayOutputStream);
            }
            if (this.n != null) {
                this.n.b(byteArrayOutputStream);
            }
            if (this.o != null) {
                this.o.b(byteArrayOutputStream);
            }
            if (this.p != null) {
                this.p.b(byteArrayOutputStream);
            }
            if (this.q != null) {
                this.q.a(byteArrayOutputStream);
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
        outputStream.write(x.j);
        outputStream.write(VT_6_1_0_11.g.a(this.i));
        outputStream.write(x.k);
    }
    
    public final int a(final byte[] array, final int n) {
        System.arraycopy(x.j, 0, array, n, 4);
        System.arraycopy(VT_6_1_0_11.g.a(this.i), 0, array, n + 4, 4);
        System.arraycopy(x.k, 0, array, n + 8, 4);
        return 12;
    }
    
    public final br b() {
        return this.l;
    }
    
    public final A c() {
        return this.q;
    }
    
    public String toString() {
        final StringBuffer sb;
        (sb = new StringBuffer("    WAVE FILE\n")).append("  HEADER\n");
        sb.append("RIFF identifier (4): ").append("RIFF").append("\n");
        sb.append("Chunk size      (4): ").append(this.i).append("\n");
        sb.append("Form            (4): ").append("WAVE").append("\n");
        if (this.l != null) {
            sb.append(this.l);
        }
        if (this.m != null) {
            sb.append(this.m);
        }
        if (this.q != null) {
            sb.append(this.q);
        }
        return sb.toString();
    }
    
    static {
        x.j = new byte[] { 82, 73, 70, 70 };
        x.k = new byte[] { 87, 65, 86, 69 };
    }
}
