import java.io.Serializable;

// 
// Decompiled by Procyon v0.5.30
// 

public class b5 implements Serializable
{
    private byte[] a;
    private byte b;
    private byte c;
    public int d;
    public int e;
    public int f;
    public int g;
    private int h;
    public int i;
    public long j;
    public int k;
    public boolean l;
    private int m;
    private String n;
    
    public byte[] a() {
        return this.a;
    }
    
    public int b() {
        return this.m;
    }
    
    public void a(final byte b, final byte c) {
        this.b = b;
        this.c = c;
    }
    
    public void c() {
        this.b(this.b);
        this.a(this.c);
    }
    
    public b5() {
        this.a = new byte[6072];
        this.d = 0;
        this.e = 0;
        this.f = 0;
        this.g = 0;
        this.h = 0;
        this.i = 0;
        this.j = 0L;
        this.k = 0;
        this.l = false;
        this.n = "";
    }
    
    public byte d() {
        return this.b;
    }
    
    public byte e() {
        return this.c;
    }
    
    public int f() {
        return this.h;
    }
    
    public synchronized void a(final int h) {
        this.h = h;
    }
    
    public synchronized void a(final byte[] a) {
        this.a = a;
    }
    
    private void a(final byte b) {
        this.e = (b & 0x3);
        if ((b & 0x4) == 0x4) {
            this.f = 1;
        }
        else {
            this.f = 0;
        }
    }
    
    private void b(final byte b) {
        if ((b & 0x1) == 0x1) {
            this.d = 0;
        }
        else if ((b & 0x2) == 0x2) {
            this.d = 6;
        }
        else if ((b & 0x8) == 0x8) {
            this.d = 2;
        }
        else {
            this.d = 1;
        }
        if ((b & 0x4) == 0x4) {
            if ((b & 0x8) == 0x8) {
                if ((b & 0x10) == 0x10) {
                    this.d = 3;
                }
                else {
                    this.d = 4;
                }
            }
            else {
                this.d = 5;
            }
        }
        this.g = 0;
        this.i = 0;
        this.l = false;
        if ((b & 0x20) == 0x20) {
            if ((b & 0x40) == 0x40) {
                this.g = 2;
            }
            else {
                this.g = 1;
            }
        }
        if ((b & 0x80) == 0x80) {
            this.l = true;
            this.i = 1;
        }
    }
    
    public String toString() {
        return String.valueOf(this.d) + "," + this.g + "," + this.i + "," + this.e;
    }
    
    public void b(final int m) {
        this.m = m;
    }
}
