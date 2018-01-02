import java.io.EOFException;
import java.util.Date;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

// 
// Decompiled by Procyon v0.5.30
// 

class i
{
    static final int a = 6;
    static final int b = 452;
    static final short c = 25203;
    static final byte d = -112;
    static final byte e = -125;
    static final byte f = -117;
    short g;
    short h;
    int i;
    byte j;
    byte k;
    int l;
    int m;
    int n;
    int o;
    int p;
    boolean q;
    boolean r;
    int s;
    int t;
    int u;
    float v;
    float w;
    float x;
    float y;
    float z;
    float A;
    float B;
    int C;
    int D;
    float E;
    float F;
    int G;
    
    i() {
        this.g = 25203;
        this.h = 452;
        this.i = 0;
        this.j = 6;
        this.k = 0;
        this.l = 1;
        this.m = 0;
        this.n = 0;
        this.o = 0;
        this.p = 0;
        this.q = true;
        this.r = false;
        this.s = 0;
        this.t = 0;
        this.u = 0;
        this.v = 0.0f;
        this.w = 0.0f;
        this.x = 0.0f;
        this.y = 0.0f;
        this.z = 0.0f;
        this.A = 0.0f;
        this.B = 0.5f;
        this.C = 0;
        this.D = 0;
        this.E = 0.0f;
        this.F = 0.0f;
        this.G = 0;
    }
    
    protected boolean a() {
        switch (this.j) {
            default: {
                return false;
            }
            case 5:
            case 6: {
                return true;
            }
        }
    }
    
    boolean a(final InputStream inputStream) {
        final e e = new e(inputStream);
        try {
            this.g = e.readShort();
            this.h = e.readShort();
            this.i = e.readInt();
        }
        catch (IOException ex) {
            return false;
        }
        if (this.g != 25203) {
            return false;
        }
        final byte[] array = new byte[this.h - 8];
        try {
            e.readFully(array);
        }
        catch (IOException ex2) {
            return false;
        }
        if (a(array) != this.i) {
            return false;
        }
        try {
            final e e2 = new e(new ByteArrayInputStream(array));
            this.j = e2.readByte();
            e2.skip(5L);
            this.k = e2.readByte();
            e2.skip(1L);
            this.l = e2.readInt();
            this.m = e2.readInt();
            this.n = e2.readInt();
            this.o = e2.readInt();
            this.p = e2.readInt();
            e2.skip(4L);
            this.q = (e2.readByte() != 0);
            this.r = (e2.readByte() != 0);
            e2.skip(334L);
            this.s = e2.readInt();
            this.t = e2.readInt();
            this.u = e2.readInt();
            this.v = e2.readFloat();
            this.w = e2.readFloat();
            this.x = e2.readFloat();
            this.y = e2.readFloat();
            this.z = e2.readFloat();
            this.A = e2.readFloat();
            this.B = e2.readFloat();
            this.C = e2.readInt();
            this.D = e2.readInt();
            e2.skip(1L);
            final long n = (e2.readByte() & 0xFF) | (e2.readByte() & 0xFF) << 8 | (e2.readByte() & 0xFF) << 16;
            if (n != 0L && new Date(new Date(97, 0, 1).getTime() + n * 24L * 60L * 60L * 1000L).before(new Date())) {
                return false;
            }
            this.E = e2.readFloat();
            this.F = e2.readFloat();
            e2.skip(8L);
            this.G = e2.readInt();
            e2.skip(8L);
            final long n2 = (e2.readByte() & 0xFF) | (e2.readByte() & 0xFF) << 8 | (e2.readByte() & 0xFF) << 16;
            if (n2 != 0L && new Date(new Date(97, 0, 1).getTime() + n2 * 24L * 60L * 60L * 1000L).after(new Date())) {
                return false;
            }
        }
        catch (EOFException ex3) {}
        catch (IOException ex4) {
            return false;
        }
        return this.j <= 6 && (this.j >= 6 || this.a());
    }
    
    static final int a(final byte[] array) {
        final int a = q.a;
        int n = 0;
        int n2 = 0;
        while (true) {
            while (true) {
                Label_0073: {
                    if (a == 0) {
                        break Label_0073;
                    }
                    final byte b = array[n2];
                    final int n4;
                    int n3 = n4;
                    int n5 = 0;
                    do {
                        final boolean b2 = ((n & 0x1) ^ (n3 & 0x1)) != 0x0;
                        n >>>= 1;
                        if (b2) {
                            n ^= 0xA001;
                        }
                        n3 = (byte)(n3 >> 1 & 0x7F);
                    } while (++n5 < 9);
                    ++n2;
                }
                if (n2 < array.length) {
                    continue;
                }
                break;
            }
            final int n4 = n;
            if (a == 0) {
                return n4;
            }
            continue;
        }
    }
}
