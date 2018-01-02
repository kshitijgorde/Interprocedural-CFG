import java.io.OutputStream;
import java.io.IOException;
import java.io.ByteArrayOutputStream;

// 
// Decompiled by Procyon v0.5.30
// 

public final class ay
{
    public int a;
    public int b;
    public int c;
    public int d;
    public boolean a;
    public ByteArrayOutputStream a;
    public int e;
    public int f;
    public boolean b;
    public boolean c;
    public boolean d;
    public byte[] a;
    private int g;
    public String a;
    
    public ay() {
        this.c = -1;
        this.d = 0;
        this.a = false;
        this.f = 7;
        this.b = false;
        this.c = true;
        this.d = false;
        this.a = null;
        this.g = 0;
        this.a = null;
    }
    
    public final void a(final int c) {
        if (c >= 0) {
            this.c = c;
        }
    }
    
    public final void b(final int g) {
        if (!this.c) {
            return;
        }
        this.g = g;
    }
    
    public final boolean a(final int[] array) {
        this.f = 7;
        boolean b = true;
        try {
            if (!this.d) {
                if (this.g == 0) {
                    this.a(256, 240);
                }
                else if (this.g == 1) {
                    this.a(128, 120);
                }
                else if (this.g == 2) {
                    this.a(64, 60);
                }
            }
            if (this.c) {
                this.c();
                this.a(this.a);
                if (this.c >= 0) {
                    this.d();
                }
            }
            this.a();
            this.b();
            this.a(array);
            this.c = false;
        }
        catch (IOException ex) {
            b = false;
        }
        return b;
    }
    
    public final byte[] a() {
        if (!(this.a = null)) {
            return null;
        }
        this.a = false;
        try {
            this.a.write(59);
            this.a.flush();
            if (this.b) {
                this.a.close();
            }
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
        this.b = false;
        this.c = true;
        return this.a.toByteArray();
    }
    
    public final void a(final float n) {
        if (n != 0.0f) {
            this.d = Math.round(100.0f / n);
        }
    }
    
    public final void a(final int a, final int b) {
        if (this.a && !this.c) {
            return;
        }
        this.a = a;
        this.b = b;
        if (this.a < 1) {
            this.a = 256;
        }
        if (this.b < 1) {
            this.b = 240;
        }
        this.d = true;
    }
    
    public final boolean a() {
        boolean a = true;
        this.b = false;
        try {
            this.a = new ByteArrayOutputStream();
            this.a("GIF89a");
        }
        catch (IOException ex) {
            a = false;
        }
        return this.a = a;
    }
    
    public final void a() throws IOException {
        if (this.d == 0) {
            return;
        }
        this.a.write(33);
        this.a.write(249);
        this.a.write(4);
        this.a.write(0);
        this.c(this.d);
        this.a.write(0);
        this.a.write(0);
    }
    
    public final void b() throws IOException {
        this.a.write(44);
        this.c(0);
        this.c(0);
        this.c(this.a);
        this.c(this.b);
        this.a.write(0);
    }
    
    public final void c() throws IOException {
        this.c(this.a);
        this.c(this.b);
        this.a.write(0xF0 | this.f);
        this.a.write(0);
        this.a.write(0);
    }
    
    public final void d() throws IOException {
        this.a.write(33);
        this.a.write(255);
        this.a.write(11);
        this.a("NETSCAPE2.0");
        this.a.write(3);
        this.a.write(1);
        this.c(this.c);
        this.a.write(0);
    }
    
    public final void a(final byte[] array) throws IOException {
        this.a.write(array, 0, array.length);
        for (int n = 768 - array.length, i = 0; i < n; ++i) {
            this.a.write(0);
        }
    }
    
    public final void a(final int[] array) throws IOException {
        this.e = 8;
        if (this.g == 0) {
            final byte[] array2 = new byte[array.length];
            for (int i = 0; i < array2.length; ++i) {
                array2[i] = (byte)(array[i] & 0xFF);
            }
            new aL(this.a, this.b, array2, this.e).b(this.a);
            return;
        }
        if (this.g == 1) {
            final byte[] array3 = new byte[array.length / 4];
            for (int j = 0; j < this.b * 2; j += 2) {
                for (int k = 0; k < this.a * 2; k += 2) {
                    array3[j / 2 * this.a + k / 2] = (byte)(array[j * this.a * 2 + k] & 0xFF);
                }
            }
            new aL(this.a, this.b, array3, this.e).b(this.a);
            return;
        }
        if (this.g == 2) {
            final byte[] array4 = new byte[array.length / 16];
            for (int l = 0; l < this.b * 4; l += 4) {
                for (int n = 0; n < this.a * 4; n += 4) {
                    array4[l / 4 * this.a + n / 4] = (byte)(array[(l + 2) * this.a * 4 + n + 2] & 0xFF);
                }
            }
            new aL(this.a, this.b, array4, this.e).b(this.a);
        }
    }
    
    public final void c(final int n) throws IOException {
        this.a.write(n & 0xFF);
        this.a.write(n >> 8 & 0xFF);
    }
    
    public final void a(final String s) throws IOException {
        for (int i = 0; i < s.length(); ++i) {
            this.a.write((byte)s.charAt(i));
        }
    }
}
