import java.awt.image.PixelGrabber;
import java.awt.image.ImageProducer;
import java.io.DataInputStream;
import java.io.IOException;
import java.awt.Toolkit;
import java.awt.Dimension;
import java.io.InputStream;
import java.net.URL;

// 
// Decompiled by Procyon v0.5.30
// 

class h extends g implements Runnable
{
    static final int a = -1;
    static final int b = 0;
    static final int c = 1;
    static final int d = 2;
    private static final int e = 0;
    private static final int f = 1;
    private static final int g = 2;
    URL h;
    private InputStream i;
    int j;
    float k;
    float l;
    float m;
    private int n;
    private q o;
    private q p;
    private Thread q;
    
    void b() {
        if (this.q != null) {
            this.q.stop();
            this.q = null;
            this.h = null;
            this.i = null;
        }
    }
    
    protected boolean a(final byte[] array, final Dimension dimension) {
        return this.a(Toolkit.getDefaultToolkit().createImage(array).getSource(), dimension);
    }
    
    boolean c() {
        return this.n == 2;
    }
    
    protected boolean b(final byte[] array, final Dimension dimension) {
        return this.a(new r(this.h, array).c.getSource(), dimension);
    }
    
    h(final URL h, final InputStream i) throws NullPointerException {
        this.h = null;
        this.i = null;
        this.j = 1;
        this.k = 1.0f;
        this.l = 0.0f;
        this.m = 0.0f;
        this.n = 0;
        this.o = new q();
        this.p = new q();
        this.q = null;
        if (h == null || i == null) {
            throw new NullPointerException();
        }
        this.h = h;
        this.i = i;
        (this.q = new Thread(this)).start();
    }
    
    h(final URL url) throws NullPointerException, IOException {
        this(url, url.openStream());
    }
    
    boolean d() {
        return this.n == 0;
    }
    
    j a() {
        if (!this.d() || super.b == null) {
            return super.a();
        }
        return new j(super.c.width / 2.0f - 0.5f, super.c.width / 2.0f - 0.5f);
    }
    
    protected boolean a(final i i, final InputStream inputStream) {
        final int a = q.a;
        try {
            final Dimension dimension = new Dimension(i.G & 0xFFFF, i.G >> 16 & 0xFFFF);
            final byte[] array = new byte[i.p];
            final f f = new f(-4427970394437748728L);
            int n = 0;
            int n2 = 0;
            while (true) {
                while (true) {
                    Label_0364: {
                        if (a == 0) {
                            break Label_0364;
                        }
                        Math.min(1024, array.length - n2);
                        final int n4;
                        final int n3 = n4;
                    Label_0111_Outer:
                        while (true) {
                            int a2 = 0;
                            int n5 = a2;
                            if (a != 0) {}
                        Label_0287_Outer:
                            while (true) {
                                int n6;
                                int n7;
                                int n8 = 0;
                                while (true) {
                                    if (n5 >= n3) {
                                        n6 = (n2 & 0xFFFFFFF8);
                                        n7 = (n2 + n5 & 0xFFFFFFF8);
                                        f.a(array, n6, n7 - n6);
                                        n8 = n;
                                        if (a == 0) {
                                            break;
                                        }
                                    }
                                    else {
                                        final int n9 = n5 + inputStream.read(array, n2 + n5, array.length - n2 - n5);
                                    }
                                    n5 = n8;
                                }
                                Label_0208: {
                                    if (n8 == 0) {
                                        int n10 = n6;
                                        while (true) {
                                            Label_0201: {
                                                if (a == 0) {
                                                    break Label_0201;
                                                }
                                                if (array[n10] == -1 && array[n10 + 1] == -38) {
                                                    n = n10;
                                                    if (a == 0) {
                                                        break Label_0208;
                                                    }
                                                }
                                                ++n10;
                                            }
                                            if (n10 < n7) {
                                                continue;
                                            }
                                            break;
                                        }
                                    }
                                }
                                if (n == 0) {
                                    break;
                                }
                                byte[] array2 = null;
                                int n11 = n7 - 2;
                                if (a == 0) {
                                    byte[] array3 = null;
                                    while (true) {
                                    Label_0312:
                                        while (true) {
                                            Label_0300: {
                                                if (a == 0) {
                                                    break Label_0300;
                                                }
                                                if (array[n11] != -1 || (array[n11 + 1] != -38 && array[n11 + 1] != -39)) {
                                                    --n11;
                                                    break Label_0300;
                                                }
                                                array2 = new byte[n11 + 2];
                                                System.arraycopy(array, 0, array2, 0, array2.length);
                                                array3[n11 + 1] = -39;
                                                break Label_0312;
                                            }
                                            if (n11 > Math.max(n6, n)) {
                                                continue Label_0287_Outer;
                                            }
                                            break;
                                        }
                                        n2 += n5;
                                        array3 = array2;
                                        if (a != 0) {
                                            continue;
                                        }
                                        break;
                                    }
                                    if (array3 != null) {
                                        try {
                                            a2 = (this.a(array2, dimension) ? 1 : 0);
                                            if (a != 0) {
                                                continue Label_0111_Outer;
                                            }
                                            if (a2 == 0) {
                                                return false;
                                            }
                                        }
                                        catch (NoSuchMethodError noSuchMethodError) {
                                            if (!this.b(array2, dimension)) {
                                                return false;
                                            }
                                        }
                                        this.j = 2;
                                        break;
                                    }
                                    break;
                                }
                                continue;
                            }
                        }
                    }
                    if (n2 < array.length) {
                        continue;
                    }
                    break;
                }
                final int n4 = 1;
                if (a == 0) {
                    return n4 != 0;
                }
                continue;
            }
        }
        catch (IOException ex) {
            return false;
        }
    }
    
    q e() {
        return new q(this.p);
    }
    
    protected boolean a(final InputStream inputStream) {
        try {
            final i i = new i();
            if (!i.a(inputStream)) {
                return false;
            }
            if (i.j < 6) {
                return false;
            }
            if (i.k != -112 && (i.k > -117 || i.k < -125)) {
                return false;
            }
            if (i.p == 0) {
                return false;
            }
            if (i.G == 0) {
                return false;
            }
            if (!this.a(i)) {
                return false;
            }
            final DataInputStream dataInputStream = new DataInputStream(inputStream);
            dataInputStream.skipBytes(i.m * 4);
            dataInputStream.skipBytes(i.n);
            dataInputStream.skipBytes(i.s);
            dataInputStream.skipBytes(i.t + i.u + i.D + i.C);
            dataInputStream.skipBytes(i.o);
            return this.a(i, inputStream);
        }
        catch (Exception ex) {
            return false;
        }
    }
    
    void a(final j j, final boolean b) {
        if (!this.d() && !b) {
            return;
        }
        j.a += super.d.a;
        j.b += super.d.b;
        if (this.d() && b) {
            j.b += super.c.height / 2;
        }
    }
    
    protected boolean a(final i i) {
        final int a = q.a;
        Label_0043: {
            if (i.q) {
                this.n = 0;
                if (a == 0) {
                    break Label_0043;
                }
            }
            if (i.r) {
                this.n = 2;
                if (a == 0) {
                    break Label_0043;
                }
            }
            this.n = 1;
        }
        this.k = i.l;
        this.p = new q(i.v, i.w, 1.0f);
        this.p = this.p.a();
        this.o = new q(i.y, -i.z, i.B);
        this.o = this.o.a();
        this.m = i.E;
        this.l = i.F;
        return true;
    }
    
    protected boolean a(final ImageProducer imageProducer, final Dimension dimension) {
        try {
            return (super.c == dimension || this.a(dimension)) && new PixelGrabber(imageProducer, 0, 0, super.c.width, super.c.height, super.b, 0, super.c.width).grabPixels();
        }
        catch (InterruptedException ex) {
            return false;
        }
    }
    
    public void run() {
        this.j = 1;
        final boolean a = this.a(this.i);
        this.q = null;
        this.i = null;
        this.j = (a ? 0 : -1);
    }
    
    q f() {
        return new q(this.o);
    }
}
