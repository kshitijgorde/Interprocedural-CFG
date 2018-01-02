// 
// Decompiled by Procyon v0.5.30
// 

package daeja4.cbf;

import java.io.UnsupportedEncodingException;
import java.io.IOException;
import java.io.OutputStream;
import java.io.InputStream;
import ji.util.nr;

public class nq
{
    nr a;
    int b;
    int c;
    int d;
    InputStream e;
    OutputStream f;
    private long g;
    private long h;
    private String i;
    private String j;
    private static String k;
    
    public nq(final InputStream e, final String s) throws IOException {
        this.d = 0;
        this.e = e;
        this.a = new nr(4096);
        this.b = 0;
        this.c = 0;
        final byte[] array = new byte[16];
        this.e.read(array);
        this.g = nh.b(array, 0, 4);
        this.h = nh.b(array, 4, 4);
        this.i = nh.d(array, 8, 4);
        this.j = nh.d(array, 12, 4);
        this.a(s);
    }
    
    public boolean a() {
        return "LZFu".equals(this.i);
    }
    
    public void a(final OutputStream f) throws Exception {
        if (this.e == null) {
            throw new IllegalStateException("Decompression has already occured.");
        }
        this.f = f;
        final byte[] array = { 0 };
        while (this.e.read(array) != -1) {
            final byte b = array[0];
            for (int i = 0; i < 8; ++i) {
                byte[] a;
                if ((b >> i & 0x1) == 0x1) {
                    if (this.e.read(array) == -1) {
                        throw new Exception("Chunk error 1");
                    }
                    final byte b2 = array[0];
                    if (this.e.read(array) == -1) {
                        throw new Exception("Chunk error 2");
                    }
                    final byte b3 = array[0];
                    a = this.a.a((b2 & 0xFF) << 4 | (b3 & 0xFF) >> 4, (b3 & 0xF) + 2);
                }
                else {
                    if (this.e.read(array) == 0) {
                        throw new Exception("Chunk error 3");
                    }
                    a = array;
                }
                this.d += a.length;
                this.a.a(a);
                this.f.write(a);
            }
        }
        this.f.flush();
        this.f = null;
        this.e.close();
        this.e = null;
    }
    
    public String toString() {
        final StringBuffer sb = new StringBuffer(this.getClass().getName());
        sb.append(":  compressedLength=").append("; uncompressedLength=").append(this.g).append("; lzId=").append(this.i).append("; crcChecksum=").append(this.j).append("\n\t").append(this.a);
        return sb.toString();
    }
    
    private void a(final String s) {
        if (s == null) {
            return;
        }
        try {
            this.a.a(s.getBytes("US-ASCII"));
        }
        catch (UnsupportedEncodingException ex) {
            ex.printStackTrace();
            this.a.a(s.getBytes());
        }
    }
    
    static {
        nq.k = new String(new byte[] { 0 });
    }
}
