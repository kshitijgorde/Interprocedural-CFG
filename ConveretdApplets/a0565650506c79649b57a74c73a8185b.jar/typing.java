import java.awt.image.PixelGrabber;
import java.awt.MediaTracker;
import java.awt.Component;
import java.io.OutputStream;
import java.io.IOException;
import java.net.Socket;
import java.awt.event.FocusEvent;
import java.awt.event.KeyEvent;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.image.ImageObserver;
import java.awt.image.ColorModel;
import java.awt.Graphics;
import java.awt.image.ImageProducer;
import sun.audio.AudioPlayer;
import java.io.InputStream;
import java.io.DataInputStream;
import java.awt.Image;
import java.awt.image.MemoryImageSource;
import java.awt.image.DirectColorModel;
import java.awt.event.FocusListener;
import java.awt.event.KeyListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseListener;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class typing extends Applet implements Runnable, MouseListener, MouseMotionListener, KeyListener, FocusListener
{
    int a;
    int b;
    int[] c;
    int d;
    int e;
    int f;
    int g;
    int h;
    int i;
    int j;
    String k;
    float l;
    int m;
    b n;
    j o;
    d p;
    h q;
    h r;
    i[] s;
    i t;
    int u;
    Thread v;
    int[] w;
    DirectColorModel x;
    MemoryImageSource y;
    Image z;
    int A;
    int[] B;
    String C;
    int D;
    int E;
    float[] F;
    int G;
    int H;
    int I;
    int J;
    int K;
    long L;
    long M;
    long N;
    int O;
    f P;
    boolean Q;
    boolean R;
    boolean S;
    boolean T;
    boolean U;
    boolean V;
    int W;
    int X;
    int Y;
    int Z;
    int aa;
    boolean[] ab;
    boolean[] ac;
    boolean ad;
    int ae;
    byte[] af;
    byte[] ag;
    byte[] ah;
    byte[] ai;
    float[] aj;
    int ak;
    int al;
    float[][] am;
    String[] an;
    String[][] ao;
    
    final void a() {
        int int1 = 0;
        int int2 = 0;
        final String parameter;
        if ((parameter = this.getParameter("ox")) != null) {
            int1 = Integer.parseInt(parameter);
        }
        final String parameter2;
        if ((parameter2 = this.getParameter("oy")) != null) {
            int2 = Integer.parseInt(parameter2);
        }
        this.k = this.getParameter("id");
        if (this.k == null) {
            this.k = "";
        }
        this.e();
        this.g();
        this.j();
        this.a(50);
        this.P.a("font.gif", 8, 16);
        (this.s[0] = new Object() {
            private final typing e;
            e[] a;
            int b;
            int c;
            int[] d;
            
            final void a(final String s, final int b) {
                this.a = new e[b];
                this.b = b;
                this.c = 0;
                final int[] a;
                if ((a = this.a(new DataInputStream(this.getClass().getResourceAsStream(s)))) == null) {
                    return;
                }
                for (int i = 0; i < this.b; ++i) {
                    this.a[i] = new InputStream(a) {
                        int[] a;
                        String b;
                        int[] c;
                        boolean d;
                        boolean e;
                        float f;
                        float g;
                        float h;
                        int i;
                        int j;
                        
                        {
                            this.a = new int[256];
                            this.b = "0011222233333333444444444444444455555555555555555555555555555555666666666666666666666666666666666666666666666666666666666666666677777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777";
                            this.d = false;
                            this.e = false;
                            this.f = 1.0f;
                            this.g = 1.0f;
                            this.h = 0.0f;
                            this.i = 0;
                            this.j = array.length;
                            this.c = new int[this.j];
                            for (int i = 0; i < this.j; ++i) {
                                this.c[i] = array[i];
                            }
                            for (int j = 0; j < 256; ++j) {
                                this.a[j] = this.b.charAt(j) - '0';
                            }
                        }
                        
                        public final synchronized int read() {
                            return -1;
                        }
                        
                        public final synchronized int read(final byte[] array, final int n, int n2) {
                            if (!this.d) {
                                return -1;
                            }
                            this.i = (int)this.h;
                            if (this.i >= this.j) {
                                return -1;
                            }
                            if (this.i + n2 > this.j) {
                                n2 = this.j - this.i;
                            }
                            if (n2 <= 0) {
                                return 0;
                            }
                            for (int i = 0; i < n2; ++i) {
                                array[n + i] = this.a(this.a() << 8);
                            }
                            return n2;
                        }
                        
                        private int a() {
                            this.h += this.g;
                            this.i = (int)this.h;
                            if (this.i >= this.j) {
                                this.i = 0;
                                this.h = 0.0f;
                                if (!this.e) {
                                    this.d = false;
                                }
                                return 0;
                            }
                            return (int)(this.c[this.i] * this.f);
                        }
                        
                        private byte a(int n) {
                            Label_0022: {
                                int n2;
                                if (n > 32767) {
                                    n2 = 32767;
                                }
                                else {
                                    if (n >= -32768) {
                                        break Label_0022;
                                    }
                                    n2 = -32768;
                                }
                                n = n2;
                            }
                            final int n3;
                            if ((n3 = (n >> 8 & 0x80)) != 0) {
                                n = -n;
                            }
                            if (n > 32635) {
                                n = 32635;
                            }
                            n += 132;
                            final int n4 = this.a[n >> 7 & 0xFF];
                            byte b;
                            if ((b = (byte)~(n3 | n4 << 4 | (n >> n4 + 3 & 0xF))) == 0) {
                                b = 2;
                            }
                            return b;
                        }
                    };
                }
            }
            
            final void a() {
                if (this.e.m == 0) {
                    return;
                }
                if (this.a[this.c] == null) {
                    return;
                }
                try {
                    AudioPlayer.player.stop(this.a[this.c]);
                    this.a[this.c].d = true;
                    this.a[this.c].e = false;
                    this.a[this.c].i = 0;
                    this.a[this.c].h = 0.0f;
                    AudioPlayer.player.start(this.a[this.c]);
                }
                catch (Exception ex) {}
                if (++this.c == this.b) {
                    this.c = 0;
                }
            }
            
            private int[] a(final DataInputStream dataInputStream) {
                int int2;
                try {
                    if (dataInputStream.readInt() != 779316836) {
                        return null;
                    }
                    final int int1;
                    if ((int1 = dataInputStream.readInt()) < 24) {
                        return null;
                    }
                    int2 = dataInputStream.readInt();
                    if (dataInputStream.readInt() != 1) {
                        return null;
                    }
                    dataInputStream.readInt();
                    if (dataInputStream.readInt() != 1) {
                        return null;
                    }
                    dataInputStream.skipBytes(int1 - 24);
                }
                catch (Exception ex) {
                    return null;
                }
                final int[] array = new int[int2];
                for (int i = 0; i < int2; ++i) {
                    try {
                        array[i] = this.a(dataInputStream.read()) >> 8;
                    }
                    catch (Exception ex2) {
                        return null;
                    }
                }
                return array;
            }
            
            private int a(int n) {
                final int n2 = (n ^= -1) & 0x80;
                final int n3 = n >> 4 & 0x7;
                int n4 = this.d[n3] + ((n & 0xF) << n3 + 3);
                if (n2 != 0) {
                    n4 = -n4;
                }
                return (short)n4;
            }
            
            {
                this.e = typing;
                this.e = typing;
                this.d = new int[] { 0, 132, 396, 924, 1980, 4092, 8316, 16764 };
            }
        }).a("crash.au", 10);
        (this.s[1] = new Object() {
            private final typing e;
            e[] a;
            int b;
            int c;
            int[] d;
            
            final void a(final String s, final int b) {
                this.a = new e[b];
                this.b = b;
                this.c = 0;
                final int[] a;
                if ((a = this.a(new DataInputStream(this.getClass().getResourceAsStream(s)))) == null) {
                    return;
                }
                for (int i = 0; i < this.b; ++i) {
                    this.a[i] = new InputStream(a) {
                        int[] a;
                        String b;
                        int[] c;
                        boolean d;
                        boolean e;
                        float f;
                        float g;
                        float h;
                        int i;
                        int j;
                        
                        {
                            this.a = new int[256];
                            this.b = "0011222233333333444444444444444455555555555555555555555555555555666666666666666666666666666666666666666666666666666666666666666677777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777";
                            this.d = false;
                            this.e = false;
                            this.f = 1.0f;
                            this.g = 1.0f;
                            this.h = 0.0f;
                            this.i = 0;
                            this.j = array.length;
                            this.c = new int[this.j];
                            for (int i = 0; i < this.j; ++i) {
                                this.c[i] = array[i];
                            }
                            for (int j = 0; j < 256; ++j) {
                                this.a[j] = this.b.charAt(j) - '0';
                            }
                        }
                        
                        public final synchronized int read() {
                            return -1;
                        }
                        
                        public final synchronized int read(final byte[] array, final int n, int n2) {
                            if (!this.d) {
                                return -1;
                            }
                            this.i = (int)this.h;
                            if (this.i >= this.j) {
                                return -1;
                            }
                            if (this.i + n2 > this.j) {
                                n2 = this.j - this.i;
                            }
                            if (n2 <= 0) {
                                return 0;
                            }
                            for (int i = 0; i < n2; ++i) {
                                array[n + i] = this.a(this.a() << 8);
                            }
                            return n2;
                        }
                        
                        private int a() {
                            this.h += this.g;
                            this.i = (int)this.h;
                            if (this.i >= this.j) {
                                this.i = 0;
                                this.h = 0.0f;
                                if (!this.e) {
                                    this.d = false;
                                }
                                return 0;
                            }
                            return (int)(this.c[this.i] * this.f);
                        }
                        
                        private byte a(int n) {
                            Label_0022: {
                                int n2;
                                if (n > 32767) {
                                    n2 = 32767;
                                }
                                else {
                                    if (n >= -32768) {
                                        break Label_0022;
                                    }
                                    n2 = -32768;
                                }
                                n = n2;
                            }
                            final int n3;
                            if ((n3 = (n >> 8 & 0x80)) != 0) {
                                n = -n;
                            }
                            if (n > 32635) {
                                n = 32635;
                            }
                            n += 132;
                            final int n4 = this.a[n >> 7 & 0xFF];
                            byte b;
                            if ((b = (byte)~(n3 | n4 << 4 | (n >> n4 + 3 & 0xF))) == 0) {
                                b = 2;
                            }
                            return b;
                        }
                    };
                }
            }
            
            final void a() {
                if (this.e.m == 0) {
                    return;
                }
                if (this.a[this.c] == null) {
                    return;
                }
                try {
                    AudioPlayer.player.stop(this.a[this.c]);
                    this.a[this.c].d = true;
                    this.a[this.c].e = false;
                    this.a[this.c].i = 0;
                    this.a[this.c].h = 0.0f;
                    AudioPlayer.player.start(this.a[this.c]);
                }
                catch (Exception ex) {}
                if (++this.c == this.b) {
                    this.c = 0;
                }
            }
            
            private int[] a(final DataInputStream dataInputStream) {
                int int2;
                try {
                    if (dataInputStream.readInt() != 779316836) {
                        return null;
                    }
                    final int int1;
                    if ((int1 = dataInputStream.readInt()) < 24) {
                        return null;
                    }
                    int2 = dataInputStream.readInt();
                    if (dataInputStream.readInt() != 1) {
                        return null;
                    }
                    dataInputStream.readInt();
                    if (dataInputStream.readInt() != 1) {
                        return null;
                    }
                    dataInputStream.skipBytes(int1 - 24);
                }
                catch (Exception ex) {
                    return null;
                }
                final int[] array = new int[int2];
                for (int i = 0; i < int2; ++i) {
                    try {
                        array[i] = this.a(dataInputStream.read()) >> 8;
                    }
                    catch (Exception ex2) {
                        return null;
                    }
                }
                return array;
            }
            
            private int a(int n) {
                final int n2 = (n ^= -1) & 0x80;
                final int n3 = n >> 4 & 0x7;
                int n4 = this.d[n3] + ((n & 0xF) << n3 + 3);
                if (n2 != 0) {
                    n4 = -n4;
                }
                return (short)n4;
            }
            
            {
                this.e = typing;
                this.e = typing;
                this.d = new int[] { 0, 132, 396, 924, 1980, 4092, 8316, 16764 };
            }
        }).a("wood.au", 10);
        (this.s[2] = new Object() {
            private final typing e;
            e[] a;
            int b;
            int c;
            int[] d;
            
            final void a(final String s, final int b) {
                this.a = new e[b];
                this.b = b;
                this.c = 0;
                final int[] a;
                if ((a = this.a(new DataInputStream(this.getClass().getResourceAsStream(s)))) == null) {
                    return;
                }
                for (int i = 0; i < this.b; ++i) {
                    this.a[i] = new InputStream(a) {
                        int[] a;
                        String b;
                        int[] c;
                        boolean d;
                        boolean e;
                        float f;
                        float g;
                        float h;
                        int i;
                        int j;
                        
                        {
                            this.a = new int[256];
                            this.b = "0011222233333333444444444444444455555555555555555555555555555555666666666666666666666666666666666666666666666666666666666666666677777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777";
                            this.d = false;
                            this.e = false;
                            this.f = 1.0f;
                            this.g = 1.0f;
                            this.h = 0.0f;
                            this.i = 0;
                            this.j = array.length;
                            this.c = new int[this.j];
                            for (int i = 0; i < this.j; ++i) {
                                this.c[i] = array[i];
                            }
                            for (int j = 0; j < 256; ++j) {
                                this.a[j] = this.b.charAt(j) - '0';
                            }
                        }
                        
                        public final synchronized int read() {
                            return -1;
                        }
                        
                        public final synchronized int read(final byte[] array, final int n, int n2) {
                            if (!this.d) {
                                return -1;
                            }
                            this.i = (int)this.h;
                            if (this.i >= this.j) {
                                return -1;
                            }
                            if (this.i + n2 > this.j) {
                                n2 = this.j - this.i;
                            }
                            if (n2 <= 0) {
                                return 0;
                            }
                            for (int i = 0; i < n2; ++i) {
                                array[n + i] = this.a(this.a() << 8);
                            }
                            return n2;
                        }
                        
                        private int a() {
                            this.h += this.g;
                            this.i = (int)this.h;
                            if (this.i >= this.j) {
                                this.i = 0;
                                this.h = 0.0f;
                                if (!this.e) {
                                    this.d = false;
                                }
                                return 0;
                            }
                            return (int)(this.c[this.i] * this.f);
                        }
                        
                        private byte a(int n) {
                            Label_0022: {
                                int n2;
                                if (n > 32767) {
                                    n2 = 32767;
                                }
                                else {
                                    if (n >= -32768) {
                                        break Label_0022;
                                    }
                                    n2 = -32768;
                                }
                                n = n2;
                            }
                            final int n3;
                            if ((n3 = (n >> 8 & 0x80)) != 0) {
                                n = -n;
                            }
                            if (n > 32635) {
                                n = 32635;
                            }
                            n += 132;
                            final int n4 = this.a[n >> 7 & 0xFF];
                            byte b;
                            if ((b = (byte)~(n3 | n4 << 4 | (n >> n4 + 3 & 0xF))) == 0) {
                                b = 2;
                            }
                            return b;
                        }
                    };
                }
            }
            
            final void a() {
                if (this.e.m == 0) {
                    return;
                }
                if (this.a[this.c] == null) {
                    return;
                }
                try {
                    AudioPlayer.player.stop(this.a[this.c]);
                    this.a[this.c].d = true;
                    this.a[this.c].e = false;
                    this.a[this.c].i = 0;
                    this.a[this.c].h = 0.0f;
                    AudioPlayer.player.start(this.a[this.c]);
                }
                catch (Exception ex) {}
                if (++this.c == this.b) {
                    this.c = 0;
                }
            }
            
            private int[] a(final DataInputStream dataInputStream) {
                int int2;
                try {
                    if (dataInputStream.readInt() != 779316836) {
                        return null;
                    }
                    final int int1;
                    if ((int1 = dataInputStream.readInt()) < 24) {
                        return null;
                    }
                    int2 = dataInputStream.readInt();
                    if (dataInputStream.readInt() != 1) {
                        return null;
                    }
                    dataInputStream.readInt();
                    if (dataInputStream.readInt() != 1) {
                        return null;
                    }
                    dataInputStream.skipBytes(int1 - 24);
                }
                catch (Exception ex) {
                    return null;
                }
                final int[] array = new int[int2];
                for (int i = 0; i < int2; ++i) {
                    try {
                        array[i] = this.a(dataInputStream.read()) >> 8;
                    }
                    catch (Exception ex2) {
                        return null;
                    }
                }
                return array;
            }
            
            private int a(int n) {
                final int n2 = (n ^= -1) & 0x80;
                final int n3 = n >> 4 & 0x7;
                int n4 = this.d[n3] + ((n & 0xF) << n3 + 3);
                if (n2 != 0) {
                    n4 = -n4;
                }
                return (short)n4;
            }
            
            {
                this.e = typing;
                this.e = typing;
                this.d = new int[] { 0, 132, 396, 924, 1980, 4092, 8316, 16764 };
            }
        }).a("electronic.au", 10);
        this.t.a("fa.au", 10);
        this.n.a();
        this.o.a();
        this.p.a();
        this.q.a("bg.gif");
        int n;
        int i = n = int2;
        Label_0270: {
            break Label_0270;
            do {
                int n2;
                int j = n2 = int1;
                final int n4;
                Label_0255: {
                    break Label_0255;
                    do {
                        final int n3;
                        this.a(this.q, n3, n4, 64, 64, 0, 0, 64, 64);
                        j = (n2 = n3 + 64);
                        n3 = n2;
                    } while (j < this.a);
                }
                i = (n = n4 + 64);
                n4 = n;
            } while (i < this.b);
        }
        this.a(0, this.e, 400, 60, -16777216);
        this.a(0, 0, 400, 2, -16777216);
        this.a(0, 0, 2, 400, -16777216);
        this.a(398, 0, 2, 400, -16777216);
        this.P.b(80, 11, "time", 0, 0, 0, 0, 102, 0, 0, 255);
        this.P.b(150, 11, "type");
        this.P.b(220, 11, "miss");
        this.P.b(290, 11, "speed");
        this.P.b(360, 11, "dot");
        this.P.a(8, 346, "STONE", 0, 0, 0, 0, 100, 100, 100, 255);
        this.P.a(8, 364, "CEMENT", 0, 0, 0, 0, 150, 150, 150, 255);
        this.P.a(72, 346, "PLANT", 0, 0, 0, 0, 0, 96, 0, 255);
        this.P.a(72, 364, "SNOW", 0, 0, 0, 0, 255, 255, 255, 255);
        this.P.a(136, 346, "BOMB", 0, 0, 0, 0, 128, 0, 0, 255);
        this.P.n = -1;
        this.P.a(136, 364, "THUNDER", 0, 0, 0, 0, 255, 255, 0, 255);
        this.P.n = 0;
        this.P.a(136, 382, "SPLINE", 0, 0, 0, 0, 255, 0, 85, 255);
        this.P.a(200, 364, "CLEAR", 0, 0, 0, 0, 255, 255, 255, 255);
        this.P.a(200, 382, "RESET", 0, 0, 0, 255, 255, 255, 255, 255);
        this.P.a(279, 382, "(C) 2006 ha55ii", 0, 0, 0, 255, 244, 197, 114, 255);
        this.c();
        this.r.a(this.a, this.b);
        for (int k = 0; k < this.a * this.b; ++k) {
            this.r.c[k] = this.w[k];
        }
    }
    
    final void b() {
        this.a(this.r);
        this.f();
        this.h();
        this.k();
        this.n.c();
        this.n.d();
        this.o.c();
        this.o.d();
        this.p.c();
        this.p.d();
        final int x = this.X;
        final int y = this.Y;
        this.P.b(80, 29, b(this.f), 0, 0, 0, 0, 102, 0, 0, 255);
        this.P.b(150, 29, String.valueOf(this.g), 0, 0, 0, 0, 102, 0, 0, 255);
        this.P.b(220, 29, String.valueOf(this.h), 0, 0, 0, 0, 102, 0, 0, 255);
        this.i = 100 * this.f / Math.max(this.g - this.h, 1);
        this.P.b(290, 29, c(this.i), 0, 0, 0, 0, 102, 0, 0, 255);
        this.P.b(360, 29, String.valueOf(this.j), 0, 0, 0, 0, 102, 0, 0, 255);
        if (this.a(0, 346, 255, 54)) {
            final int n = (x - 0) / 64;
            final int n2 = (y - 346) / 18;
            this.a(7 + n * 64, 345 + n2 * 18, 58, 16, -16777216, -12566464);
            if (this.W != 0) {
                final int a;
                if ((a = a(n * 3 + n2, 0, 11)) == 10) {
                    this.o.b();
                    this.p.b();
                }
                else if (a == 11) {
                    this.n.b();
                    this.o.b();
                    this.p.b();
                    this.f = 0;
                    this.g = 0;
                    this.h = 0;
                    this.l = 0.0f;
                }
                else if (a != 2 && a != 5 && a != 9) {
                    this.d = a;
                }
            }
        }
        this.a(2 + this.d / 3 * 64, 351 + this.d % 3 * 18, 4, 4, -65536);
        final String[] array = { "off", "A", "B", "C" };
        Object object;
        int n3;
        int n4;
        String s;
        int n5;
        int n6;
        int n7;
        int n8;
        int n9;
        int n10;
        int n11;
        if (this.a(279, 364, 72, 16)) {
            if (this.W != 0) {
                this.m = b(this.m + this.W, 0, 3);
                if (this.m > 0) {
                    this.s[this.m - 1].a();
                }
            }
            object = this.P;
            n3 = 279;
            n4 = 364;
            s = "Sound " + array[this.m];
            n5 = 0;
            n6 = 0;
            n7 = 0;
            n8 = 255;
            n9 = 255;
            n10 = 0;
            n11 = 0;
        }
        else {
            object = this.P;
            n3 = 279;
            n4 = 364;
            s = "Sound " + array[this.m];
            n5 = 0;
            n6 = 0;
            n7 = 0;
            n8 = 255;
            n9 = 244;
            n10 = 197;
            n11 = 114;
        }
        object.a(n3, n4, s, n5, n6, n7, n8, n9, n10, n11, 255);
        this.a(359, 364);
        this.a(0, 0, 0, 0, 0, 0, 0, 255);
    }
    
    public void init() {
        this.w = new int[this.a * this.b];
        this.x = new DirectColorModel(32, 16711680, 65280, 255);
        (this.y = new MemoryImageSource(this.a, this.b, this.w, 0, this.a)).setFullBufferUpdates(true);
        this.y.setAnimated(true);
        this.z = this.createImage(this.y);
        this.l();
        this.a();
        System.out.println("Copyright(C) 2006 ha55ii, http://dan-ball.jp/");
    }
    
    public void start() {
        if (this.v == null) {
            (this.v = new Thread(this)).start();
        }
    }
    
    public void run() {
        while (this.v != null) {
            this.b();
            this.paint(this.getGraphics());
            try {
                this.A = this.d();
                Thread.sleep(this.A);
            }
            catch (InterruptedException ex) {}
        }
    }
    
    public void update(final Graphics graphics) {
        this.paint(graphics);
    }
    
    public void paint(final Graphics graphics) {
        this.y.newPixels(this.w, this.x, 0, this.a);
        graphics.drawImage(this.z, 0, 0, null);
    }
    
    public void stop() {
        this.v = null;
    }
    
    final void c() {
        this.E = this.c.length;
        typing typing = this;
        int d = 0;
        Label_0047: {
            break Label_0047;
            do {
                typing = this;
                d = this.D + 1;
                typing.D = d;
            } while (this.D < this.E - 1 && this.C.charAt(this.D) == this.c[this.D]);
        }
        if (this.D == this.E - 1 && this.C.charAt(this.D) == this.c[this.D]) {
            this.B[0] = 1;
            ++this.D;
        }
        if (this.D != this.E) {
            this.w = null;
        }
    }
    
    final void a(final h object) {
        for (int i = this.a * this.b - 1; i >= 0; --i) {
            this.w[i] = object.c[i];
        }
    }
    
    final void a(float n, float n2, final float n3, final float n4, final int n5) {
        final float n6 = n3 - n;
        final float n7 = n4 - n2;
        final int n8 = (int)(Math.max(Math.abs(n6), Math.abs(n7)) + 1.0f);
        final float n9 = n6 / n8;
        final float n10 = n7 / n8;
        for (int i = 0; i < n8; ++i) {
            this.w[(int)(n2 + 0.5) * this.a + (int)(n + 0.5)] = n5;
            n += n9;
            n2 += n10;
        }
    }
    
    final void a(int a, int i, final int n, final int n2, final int n3) {
        final int a2 = a(a + n, 0, this.a);
        final int a3 = a(i + n2, 0, this.b);
        a = a(a, 0, this.a);
        int n4;
        int j;
        for (i = a(i, 0, this.b); i < a3; ++i) {
            n4 = i * this.a + a;
            for (j = a; j < a2; ++j) {
                this.w[n4++] = n3;
            }
        }
    }
    
    final void a(final h object, final int n, final int n2, final int n3, final int n4, final int n5, final int n6, final int n7, final int n8) {
        final int n9 = (n < 0) ? (-n) : 0;
        final int n10 = (n2 < 0) ? (-n2) : 0;
        final int n11 = (n + n3 > this.a) ? (n3 - (n + n3 - this.a)) : n3;
        for (int n12 = (n2 + n4 > this.b) ? (n4 - (n2 + n4 - this.b)) : n4, i = n10; i < n12; ++i) {
            int n13 = (n2 + i) * this.a + (n + n9);
            final int n14 = (n6 + i * n8 / n4) * object.a + n5;
            for (int j = n9; j < n11; ++j, ++n13) {
                final int n15 = n14 + j * n7 / n3;
                if (object.c[n15] != 0) {
                    this.w[n13] = object.c[n15];
                }
            }
        }
    }
    
    final void a(final int n, final int n2, final int n3, final int n4, final int n5, final int n6) {
        int n7 = n2 * this.a + n;
        for (int i = 0; i < n4; ++i) {
            for (int j = 0; j < n3; ++j) {
                if (this.w[n7] == n5) {
                    this.w[n7] = n6;
                }
                ++n7;
            }
            n7 += this.a - n3;
        }
    }
    
    static void a(final c object, final c object2, final float n, final float n2) {
        object2.b += n;
        object2.a(n2);
        object.b(object2);
    }
    
    final void a(final c object, final float n, final c object2, final c object3, final c object4, final c object5) {
        final float n2 = n * n * n;
        final float n3 = n * n;
        final float n4 = n2 * this.F[0] + n3 * this.F[4] + n * this.F[8] + this.F[12];
        final float n5 = n2 * this.F[1] - n3 + this.F[13];
        final float n6 = n2 * this.F[2] + n3 * this.F[6] + n * this.F[10] + this.F[14];
        final float n7 = n2 * this.F[3];
        object.a = object2.a * n4 + object3.a * n5 + object4.a * n6 + object5.a * n7;
        object.b = object2.b * n4 + object3.b * n5 + object4.b * n6 + object5.b * n7;
    }
    
    final void a(final int n) {
        this.G = 0;
        this.H = 0;
        this.K = 1000 / n;
        this.L = System.currentTimeMillis();
        this.M = this.L + this.K;
        this.N = this.L;
        this.I = 0;
        this.J = 0;
    }
    
    final int d() {
        ++this.G;
        ++this.O;
        this.L = System.currentTimeMillis();
        int n;
        if ((n = (int)(this.M - this.L)) < 1) {
            n = 1;
        }
        this.I += n;
        this.M += this.K;
        if (this.L + n >= this.N) {
            this.J = this.I;
            this.I = 0;
            this.H = this.G;
            this.G = 0;
            this.M = this.L + this.K;
            this.N = this.L + 1000L;
        }
        return n;
    }
    
    final void a(final int n, final int n2) {
        this.P.a(8, 16);
        this.P.a(255, 255, 255, 0);
        this.P.b(48, 48, 48, 255);
        this.P.a(n, n2, String.valueOf(this.H) + "fps");
        if (this.u == 1) {
            this.P.a(n + 48, n2, String.valueOf(this.J) + "sl", 255, 255, 255, 255, 0, 0, 0, 255);
        }
    }
    
    final void e() {
        this.addMouseListener(this);
        this.addMouseMotionListener(this);
    }
    
    final void f() {
        this.Q = (this.R && !this.S);
        this.T = (this.U && !this.V);
        this.R = this.S;
        this.U = this.V;
        this.W = (this.Q ? 1 : (this.T ? -1 : 0));
        this.X = this.Z;
        this.Y = this.aa;
        if (this.D != this.E) {
            this.w = null;
        }
    }
    
    final boolean a(final int n, final int n2, final int n3, final int n4) {
        return this.X >= n && n + n3 >= this.X && this.Y >= n2 && n2 + n4 >= this.Y;
    }
    
    public void mouseEntered(final MouseEvent mouseEvent) {
    }
    
    public void mouseExited(final MouseEvent mouseEvent) {
    }
    
    public void mousePressed(final MouseEvent mouseEvent) {
        if ((mouseEvent.getModifiers() & 0x10) != 0x0) {
            this.S = true;
        }
        if ((mouseEvent.getModifiers() & 0x4) != 0x0) {
            this.V = true;
        }
    }
    
    public void mouseReleased(final MouseEvent mouseEvent) {
        if ((mouseEvent.getModifiers() & 0x10) != 0x0) {
            this.S = false;
        }
        if ((mouseEvent.getModifiers() & 0x4) != 0x0) {
            this.V = false;
        }
    }
    
    public void mouseClicked(final MouseEvent mouseEvent) {
    }
    
    public void mouseDragged(final MouseEvent mouseEvent) {
        final Point point = mouseEvent.getPoint();
        this.Z = point.x;
        this.aa = point.y;
    }
    
    public void mouseMoved(final MouseEvent mouseEvent) {
        final Point point = mouseEvent.getPoint();
        this.Z = point.x;
        this.aa = point.y;
    }
    
    final void g() {
        this.addKeyListener(this);
        for (int i = 0; i < 256; ++i) {
            this.ab[i] = false;
            this.ac[i] = false;
        }
        this.addFocusListener(this);
        if (this.u == 1) {
            this.requestFocus();
        }
    }
    
    final void h() {
        for (int i = 0; i < 256; ++i) {
            this.ab[i] = this.ac[i];
            this.ac[i] = false;
        }
    }
    
    public void keyPressed(final KeyEvent keyEvent) {
    }
    
    public void keyReleased(final KeyEvent keyEvent) {
    }
    
    public void keyTyped(final KeyEvent keyEvent) {
        final char keyChar;
        if ((keyChar = keyEvent.getKeyChar()) < '\u0100') {
            this.ac[keyChar] = true;
            if (keyChar >= 'A' && keyChar <= 'Z') {
                this.ac[keyChar + ' '] = true;
            }
            if (keyChar >= 'a' && keyChar <= 'z') {
                this.ac[keyChar - ' '] = true;
            }
        }
    }
    
    public void focusGained(final FocusEvent focusEvent) {
        this.ad = true;
    }
    
    public void focusLost(final FocusEvent focusEvent) {
        this.ad = false;
    }
    
    final void a(final int n, final int n2, final int n3, final int n4, final int n5, final int n6, final int n7, final int n8) {
        if (!this.ad) {
            this.ae += 4;
            if (this.ae >= 224) {
                this.ae = 32;
            }
            final int n9 = (int)(this.am[this.ae][1] * 255.0f);
            this.P.b(this.a / 2, this.b / 2, "CLICK TO PLAY", n, n2, n3, n4 * n9 / 255, n5, n6, n7, n8 * n9 / 255);
            return;
        }
        this.ae = 0;
    }
    
    final String[] a(final String s) {
        final String[] array = new String[100];
        for (int i = 0; i < 100; ++i) {
            array[i] = "";
        }
        try {
            final String s2 = new String(this.ai);
            final String string = String.valueOf(new String(this.af)) + s + new String(this.ag) + s2 + new String(this.ah);
            final Socket socket;
            final InputStream inputStream = (socket = new Socket(s2, 80)).getInputStream();
            final OutputStream outputStream = socket.getOutputStream();
            for (int j = 0; j < string.length(); ++j) {
                outputStream.write(string.charAt(j));
            }
            outputStream.flush();
            int n = 0;
            int read;
            while ((read = inputStream.read()) != -1) {
                if (read == 61) {
                    int read2;
                    while ((read2 = inputStream.read()) != -1 && read2 != 10) {
                        final String[] array2 = array;
                        final int n2 = n;
                        array2[n2] = String.valueOf(array2[n2]) + (char)read2;
                    }
                    ++n;
                }
            }
            if (!array[0].equals("ok")) {
                array[0] = "error";
                array[1] = "1";
            }
            outputStream.close();
            inputStream.close();
            socket.close();
        }
        catch (IOException ex) {
            array[0] = "error";
            array[1] = "2";
        }
        return array;
    }
    
    static float a(final float n) {
        return (float)Math.sin(n);
    }
    
    static float b(final float n) {
        return (float)Math.cos(n);
    }
    
    static float i() {
        return (float)Math.random();
    }
    
    final void j() {
        for (int i = 0; i < 1024; ++i) {
            this.aj[i] = i / 1024.0f;
        }
        for (int j = 0; j < 1024; ++j) {
            final int n = (int)(i() * 1024.0f);
            final float n2 = this.aj[j];
            this.aj[j] = this.aj[n];
            this.aj[n] = n2;
        }
        this.ak = ((int)(i() * 1024.0f) & 0x3FF);
        this.al = ((int)(i() * 512.0f) | 0x1);
    }
    
    final void k() {
        this.ak = (this.ak + (int)(i() * 1024.0f) & 0x3FF);
        this.al = ((int)(i() * 512.0f) | 0x1);
        if (this.B[0] == 0) {
            for (int i = 0; i < 1024; ++i) {
                this.aj[i] = 0.0f;
            }
        }
    }
    
    final float c(final float n) {
        this.ak += this.al;
        this.ak &= 0x3FF;
        return this.aj[this.ak] * n;
    }
    
    final float a(final float n, final float n2) {
        this.ak += this.al;
        this.ak &= 0x3FF;
        return this.aj[this.ak] * (n2 - n) + n;
    }
    
    final int b(final int n, final int n2) {
        this.ak += this.al;
        this.ak &= 0x3FF;
        if (this.aj[this.ak] > 0.5) {
            return n;
        }
        return n2;
    }
    
    final void l() {
        int i;
        for (i = 0; i < 512; ++i) {
            final float n = 360.0f * i / 512 * 3.1415927f / 180.0f;
            this.am[i][0] = b(n);
            this.am[i][1] = a(n);
        }
        this.am[i][0] = this.am[0][0];
        this.am[i][1] = this.am[0][1];
    }
    
    static int a(final int n, final int n2, final int n3) {
        if (n < n2) {
            return n2;
        }
        if (n > n3) {
            return n3;
        }
        return n;
    }
    
    static float a(final float n, final float n2, final float n3) {
        if (n < n2) {
            return n2;
        }
        if (n > n3) {
            return n3;
        }
        return n;
    }
    
    static int b(final int n, final int n2, final int n3) {
        if (n < n2) {
            return n3;
        }
        if (n > n3) {
            return n2;
        }
        return n;
    }
    
    static String b(int n) {
        final String s = "";
        final int n2 = n / 3000;
        String s2 = String.valueOf(new StringBuffer(String.valueOf(s)).append((n2 > 0) ? new StringBuffer(String.valueOf(n2)).append(":").toString() : "0:").toString()) + (n %= 3000) / 50 + ":";
        final int n3;
        if ((n3 = n % 50 * 2) < 10) {
            s2 = String.valueOf(s2) + "0";
        }
        return String.valueOf(s2) + n3;
    }
    
    static String c(final int n) {
        String s = String.valueOf("") + n / 5000 + ".";
        final int n2;
        if ((n2 = n % 5000 * 2) < 10) {
            s = String.valueOf(s) + "0";
        }
        if (n2 < 100) {
            s = String.valueOf(s) + "0";
        }
        if (n2 < 1000) {
            s = String.valueOf(s) + "0";
        }
        return String.valueOf(s) + n2 + "s";
    }
    
    public typing() {
        this.a = 400;
        this.b = 400;
        this.c = new int[] { 104, 116, 116, 112, 58, 47, 47, 100, 97, 110, 45, 98, 97, 108, 108, 46, 106, 112, 47 };
        this.e = 340;
        this.k = "";
        this.m = 1;
        this.n = new Object() {
            private final typing r = typing;
            int a;
            int b;
            String c;
            int d;
            String e;
            String f;
            int g;
            int h;
            int i;
            int[] j = new int[276];
            float k;
            int[] l = new int[300];
            int m;
            int n;
            String o;
            int p;
            int[] q = { -16777216, -8355712, -16777216, -16752640, -1, -16777216, -8388608, -256, -65451, -16777216 };
            
            final void a() {
                this.a = 0;
                this.b = 0;
                this.k = 0.0f;
                this.n = 0;
                this.o = "";
                this.p = 0;
                for (int i = 0; i < 276; ++i) {
                    this.j[i] = 0;
                }
                for (int j = 0; j < this.r.an.length; ++j) {
                    this.l[j] = j;
                }
                for (int k = 0; k < this.r.an.length; ++k) {
                    final int n = (int)(this.r.c(this.r.an.length - 1.0f) + 0.5f);
                    final int n2 = this.l[k];
                    this.l[k] = this.l[n];
                    this.l[n] = n2;
                }
                this.m = 0;
            }
            
            final void b() {
                this.a = 0;
                this.b = 0;
                this.k = 0.0f;
                this.n = 0;
                this.o = "";
                this.p = 0;
            }
            
            private String e() {
                String string = "";
                int n;
                int i = n = 0;
                Label_0106: {
                    break Label_0106;
                    do {
                        final int n3;
                        int n2;
                        StringBuffer sb;
                        if ((n2 = this.a(n3)) == 34) {
                            n2 = this.a(n3 + 1);
                            sb = new StringBuffer(String.valueOf(string)).append(this.r.ao[n2][this.j[n2]].charAt(0));
                        }
                        else {
                            sb = new StringBuffer(String.valueOf(string)).append(this.r.ao[n2][this.j[n2]]);
                        }
                        string = sb.toString();
                        i = (n = n3 + this.a(n2, n3));
                        n3 = n;
                    } while (i < this.c.length());
                }
                return string;
            }
            
            private int a(final int n, final int n2) {
                if (n <= 91) {
                    return 1;
                }
                final char char1 = this.c.charAt(n2);
                if (this.r.ao[char1 - ((char1 < '\u30a1') ? 12353 : 12449)].length < this.r.ao[n].length - this.j[n]) {
                    return 2;
                }
                return 1;
            }
            
            private int a(final int n) {
                final char char1 = this.c.charAt(n);
                int n2 = char1 - ((char1 < '\u30a1') ? '\u3041' : '\u30a1');
                if (n + 1 < this.c.length()) {
                    final char char2 = this.c.charAt(n + 1);
                    final char c;
                    int n4;
                    final int n3 = ((c = (char)(char2 - ((char2 < '\u30a1') ? '\u3041' : '\u30a1'))) == '\0') ? (n4 = 0) : ((c == '\u0002') ? (n4 = 1) : ((c == '\u0004') ? (n4 = 2) : ((c == '\u0006') ? (n4 = 3) : ((c == '\b') ? (n4 = 4) : ((c == 'B') ? (n4 = 5) : ((c == 'D') ? (n4 = 6) : ((c == 'F') ? (n4 = 7) : (n4 = -1))))))));
                    final int n5 = n4;
                    if (n3 != -1) {
                        if (n2 == 5) {
                            n2 = 92 + n5;
                        }
                        if (n2 == 83) {
                            n2 = 100 + n5;
                        }
                        if (n2 == 12) {
                            n2 = 108 + n5;
                        }
                        if (n2 == 13) {
                            n2 = 116 + n5;
                        }
                        if (n2 == 14) {
                            n2 = 124 + n5;
                        }
                        if (n2 == 15) {
                            n2 = 132 + n5;
                        }
                        if (n2 == 22) {
                            n2 = 140 + n5;
                        }
                        if (n2 == 23) {
                            n2 = 148 + n5;
                        }
                        if (n2 == 24) {
                            n2 = 156 + n5;
                        }
                        if (n2 == 32) {
                            n2 = 164 + n5;
                        }
                        if (n2 == 33) {
                            n2 = 172 + n5;
                        }
                        if (n2 == 35) {
                            n2 = 180 + n5;
                        }
                        if (n2 == 37) {
                            n2 = 188 + n5;
                        }
                        if (n2 == 38) {
                            n2 = 196 + n5;
                        }
                        if (n2 == 39) {
                            n2 = 204 + n5;
                        }
                        if (n2 == 40) {
                            n2 = 212 + n5;
                        }
                        if (n2 == 42) {
                            n2 = 220 + n5;
                        }
                        if (n2 == 49) {
                            n2 = 228 + n5;
                        }
                        if (n2 == 50) {
                            n2 = 236 + n5;
                        }
                        if (n2 == 51) {
                            n2 = 244 + n5;
                        }
                        if (n2 == 52) {
                            n2 = 252 + n5;
                        }
                        if (n2 == 62) {
                            n2 = 260 + n5;
                        }
                        if (n2 == 73) {
                            n2 = 268 + n5;
                        }
                    }
                }
                return n2;
            }
            
            private int b(final int n) {
                final int a;
                if ((a = this.a(this.d)) != 34) {
                    for (int i = 0; i < this.r.ao[a].length; ++i) {
                        int n2;
                        for (n2 = 0; n2 < this.f.length() && this.f.charAt(n2) == this.r.ao[a][i].charAt(n2); ++n2) {}
                        if (this.f.length() == n2 && n == this.r.ao[a][i].charAt(n2)) {
                            if (n != this.r.ao[a][this.j[a]].charAt(n2)) {
                                this.j[a] = i;
                            }
                            this.e = String.valueOf(this.e) + (char)n;
                            this.f = String.valueOf(this.f) + (char)n;
                            if (this.r.ao[a][i].length() == n2 + 1) {
                                this.f = "";
                                this.d += this.a(a, this.d);
                            }
                            return 1;
                        }
                    }
                    return 0;
                }
                final int a2 = this.a(this.d + 1);
                if (n != this.r.ao[a2][this.j[a2]].charAt(0)) {
                    return 0;
                }
                this.e = String.valueOf(this.e) + (char)n;
                this.f = "";
                ++this.d;
                return 1;
            }
            
            final void c() {
                switch (this.a) {
                    case 0: {
                        this.c = this.r.an[this.l[this.m]];
                        final int m = this.m;
                        final int a = typing.a((int)this.r.a(this.m - this.r.an.length / 4, (float)this.m), 0, this.m);
                        final int n = this.l[m];
                        this.l[m] = this.l[a];
                        this.l[a] = n;
                        this.m = typing.b(this.m + 1, 0, this.r.an.length - 1);
                        this.d = 0;
                        this.e = "";
                        this.f = "";
                        this.g = 0;
                        this.a = 1;
                        this.h = this.r.a / 2 - this.e().length() * this.r.P.b / 2;
                        this.i = 110 - this.r.P.c / 2;
                    }
                    case 1: {
                        int n2 = 0;
                        if (this.g == 1) {
                            final typing r = this.r;
                            ++r.f;
                            final typing r2 = this.r;
                            r2.l += this.r.H;
                            this.k = typing.a(this.k * 0.992f - 0.1f, 0.0f, 200.0f);
                        }
                        for (int i = 0; i < 255; ++i) {
                            if (this.r.ab[i]) {
                                n2 = i;
                            }
                        }
                        if (n2 != 0) {
                            this.g = 1;
                            if (this.b(n2) == 1) {
                                final int n3 = this.h + this.r.P.b * (this.e.length() - 1);
                                final int a2 = this.r.P.a(this.e.charAt(this.e.length() - 1));
                                for (int j = 0; j < this.r.P.c; ++j) {
                                    for (int k = 0; k < this.r.P.b; ++k) {
                                        if (this.r.P.a.c[a2 + j * this.r.P.a.a + k] == -16777216) {
                                            this.r.o.a(n3 + k, this.i + j, this.r.a(-0.03f, 0.03f) + (k - this.r.P.b / 2) * 0.08f, this.r.a(-0.03f, 0.03f) + (j - this.r.P.c / 2) * 0.08f, this.q[this.r.d]);
                                            for (int n4 = 0; n4 < 2 && ((n4 != 0 || this.k >= 50.0f) && (n4 != 1 || this.k >= 80.0f)); ++n4) {
                                                this.r.o.a(n3 + k, this.i + j, this.r.a(-0.12f, 0.12f) + (k - this.r.P.b / 2) * 0.08f, this.r.a(-0.12f, 0.12f) + (j - this.r.P.c / 2) * 0.08f, this.q[this.r.d]);
                                            }
                                        }
                                    }
                                }
                                final typing r3 = this.r;
                                ++r3.g;
                                this.k = typing.a(this.k + 5.0f, 0.0f, 200.0f);
                                if (this.r.m > 0) {
                                    this.r.s[this.r.m - 1].a();
                                }
                                if (this.r.g >= 100 && this.n == 0) {
                                    this.n = 1;
                                }
                                if (this.r.g >= 300 && this.n == 2) {
                                    this.n = 3;
                                }
                                if (this.d == this.c.length()) {
                                    this.a = 2;
                                    if (this.k > 100.0f) {
                                        for (int l = 0; l < this.c.length(); ++l) {
                                            this.h = this.r.a / 2 - this.c.length() * this.r.P.b * 2 / 2;
                                            this.i = 80 - this.r.P.c / 2;
                                            final int n5 = this.h + this.r.P.b * 2 * l;
                                            final int a3 = this.r.P.a(this.c.charAt(l));
                                            for (int n6 = 0; n6 < this.r.P.c; ++n6) {
                                                for (int n7 = 0; n7 < this.r.P.b * 2; ++n7) {
                                                    if (this.r.P.a.c[a3 + n6 * this.r.P.a.a + n7] == -16777216) {
                                                        this.r.o.a(n5 + n7, this.i + n6, 0.0f, 0.0f, this.q[this.r.d]);
                                                    }
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                            else {
                                final typing r4 = this.r;
                                ++r4.h;
                                this.k = typing.a(this.k - 10.0f, 0.0f, 200.0f);
                                this.r.t.a();
                            }
                            return;
                        }
                        break;
                    }
                    case 2: {
                        ++this.b;
                        if (this.b == 10) {
                            this.b = 0;
                            this.a = 0;
                            if (this.n == 1 || this.n == 3) {
                                ++this.n;
                                Object object;
                                String string;
                                if (this.r.l / this.r.f > 45.0f) {
                                    final String[] a4;
                                    if ((a4 = this.r.a("http://dan-ball.jp/score/typing.php?a=" + this.r.k + "&b=" + ((this.n != 2) ? 1 : 0) + "&c=" + this.r.f + "&d=" + this.r.g + "&e=" + this.r.h + "&f=" + this.r.i))[0].equals("ok")) {
                                        object = this;
                                        string = a4[1];
                                    }
                                    else {
                                        object = this;
                                        string = "E" + a4[1];
                                    }
                                }
                                else {
                                    object = this;
                                    string = "-";
                                }
                                object.o = string;
                                this.p = 40;
                            }
                            return;
                        }
                        break;
                    }
                }
            }
            
            final void d() {
                this.r.P.a(3, 3, "RANK", 0, 0, 0, 0, 102, 0, 0, 255);
                this.r.P.a(8 + this.p, 16 + this.p * 2);
                this.r.P.a(19 - this.o.length() * 4, 21, this.o, 0, 0, 0, 0, 255, 0, 0, 255);
                this.r.P.a(8, 16);
                this.p = typing.a(this.p - 1, 0, 100);
                if (this.k <= 100.0f || this.a != 2) {
                    this.r.P.b(this.r.a / 2, 80, this.c, 0, 0, 0, 0, 0, 0, 0, 255);
                }
                String s = "";
                String s2 = "";
                final String e = this.e();
                int i;
                for (i = 0; i < this.e.length(); ++i) {
                    s = String.valueOf(s) + this.e.charAt(i);
                }
                while (i < e.length()) {
                    s = String.valueOf(s) + " ";
                    ++i;
                }
                int j;
                for (j = 0; j < this.e.length(); ++j) {
                    s2 = String.valueOf(s2) + " ";
                }
                while (j < e.length()) {
                    s2 = String.valueOf(s2) + e.charAt(j);
                    ++j;
                }
                this.r.P.a(this.h, this.i, s, 0, 0, 0, 0, 64, 32, 0, 32);
                this.r.P.a(this.h, this.i, s2, 0, 0, 0, 0, 0, 0, 0, 255);
                this.r.a(149.0f, 49.0f, 250.0f, 49.0f, -12582912);
                this.r.a(149.0f, 58.0f, 250.0f, 58.0f, -12582912);
                this.r.a(149.0f, 49.0f, 149.0f, 58.0f, -12582912);
                this.r.a(250.0f, 49.0f, 250.0f, 58.0f, -12582912);
                int n = -10185216;
                if (this.k >= 50.0f) {
                    n = -32768;
                }
                if (this.k >= 80.0f) {
                    n = -3670016;
                }
                if (this.k > 100.0f) {
                    n = -1;
                }
                this.r.a(150, 50, (int)this.k, 8, n);
            }
            
            {
                this.r = typing;
            }
        };
        this.o = new Object() {
            private final typing d = typing;
            g[] a;
            int b;
            int c;
            
            final void a() {
                this.b = 9999;
                this.c = 0;
                this.a = new g[this.b];
                for (int i = 0; i < this.b; ++i) {
                    this.a[i] = new Object() {
                        c a = new Object() {
                            float a;
                            float b;
                            
                            {
                                this.a = 0.0f;
                                this.b = 0.0f;
                            }
                            
                            {
                                this.a = a;
                                this.b = b;
                            }
                            
                            final c a(final float a, final float b) {
                                this.a = a;
                                this.b = b;
                                return this;
                            }
                            
                            final c a(final c object) {
                                this.a = object.a;
                                this.b = object.b;
                                return this;
                            }
                            
                            final c b(final c object) {
                                this.a += object.a;
                                this.b += object.b;
                                return this;
                            }
                            
                            final c a(final float n) {
                                this.a *= n;
                                this.b *= n;
                                return this;
                            }
                        };
                        c b = new Object() {
                            float a;
                            float b;
                            
                            {
                                this.a = 0.0f;
                                this.b = 0.0f;
                            }
                            
                            {
                                this.a = a;
                                this.b = b;
                            }
                            
                            final c a(final float a, final float b) {
                                this.a = a;
                                this.b = b;
                                return this;
                            }
                            
                            final c a(final c object) {
                                this.a = object.a;
                                this.b = object.b;
                                return this;
                            }
                            
                            final c b(final c object) {
                                this.a += object.a;
                                this.b += object.b;
                                return this;
                            }
                            
                            final c a(final float n) {
                                this.a *= n;
                                this.b *= n;
                                return this;
                            }
                        };
                        int c = 0;
                        
                        final void a(final float n, final float n2, final float n3, final float n4, final int c) {
                            this.a.a(n, n2);
                            this.b.a(n3, n4);
                            this.c = c;
                        }
                        
                        final void a(final g object) {
                            this.a.a(object.a);
                            this.b.a(object.b);
                            this.c = object.c;
                        }
                    };
                }
            }
            
            final void b() {
                this.c = 0;
            }
            
            final void a(final float n, final float n2, final float n3, final float n4, final int n5) {
                if (this.c == this.b) {
                    return;
                }
                this.a[this.c].a(n, n2, n3, n4, n5);
                ++this.c;
            }
            
            final void c() {
                for (int i = 0; i < this.c; ++i) {
                    typing.a(this.a[i].a, this.a[i].b, 0.02f, 0.99f);
                    this.a[i].a.a = typing.a(this.a[i].a.a, 1.0f, this.d.a - 2);
                    this.a[i].a.b = typing.a(this.a[i].a.b, 1.0f, this.d.e - 2);
                    if (this.d.p.a((int)this.a[i].a.a, (int)this.a[i].a.b, this.a[i].c)) {
                        final Object object = this.a[i--];
                        final Object a = this.a;
                        final int c = this.c - 1;
                        this.c = c;
                        object.a(a[c]);
                    }
                }
            }
            
            final void d() {
                for (int i = 0; i < this.c; ++i) {
                    this.d.w[(int)this.a[i].a.b * this.d.a + (int)this.a[i].a.a] = this.a[i].c;
                }
            }
            
            {
                this.d = typing;
            }
            
            static typing a(final j object) {
                return object.d;
            }
        };
        this.p = new Object() {
            private final typing g = typing;
            int[] a;
            int[] b;
            int[] c;
            int d;
            int[][] e = { { 0, -1, 1, -400, -401, -399 }, { 0, 1, -1, -400, -399, -401 } };
            c[] f = new c[4];
            
            final void a() {
                this.a = new int[this.g.a * this.g.b];
                this.b = new int[this.g.a * this.g.b];
                this.c = new int[this.g.a * this.g.b];
                this.d = this.g.a * this.g.b;
                for (int i = 0; i < this.d; ++i) {
                    this.a[i] = 0;
                }
                for (int j = 0; j < this.d; ++j) {
                    this.b[j] = 0;
                }
                for (int k = 0; k < this.d; ++k) {
                    this.c[k] = 0;
                }
                for (int l = 0; l < 4; ++l) {
                    this.f[l] = new Object(0.0f, 0.0f) {
                        float a;
                        float b;
                        
                        {
                            this.a = 0.0f;
                            this.b = 0.0f;
                        }
                        
                        {
                            this.a = a;
                            this.b = b;
                        }
                        
                        final c a(final float a, final float b) {
                            this.a = a;
                            this.b = b;
                            return this;
                        }
                        
                        final c a(final c object) {
                            this.a = object.a;
                            this.b = object.b;
                            return this;
                        }
                        
                        final c b(final c object) {
                            this.a += object.a;
                            this.b += object.b;
                            return this;
                        }
                        
                        final c a(final float n) {
                            this.a *= n;
                            this.b *= n;
                            return this;
                        }
                    };
                }
            }
            
            final void b() {
                this.d = this.g.a * this.g.b;
                for (int i = 0; i < this.d; ++i) {
                    this.a[i] = 0;
                    this.b[i] = 0;
                    this.c[i] = 0;
                }
                for (int j = 0; j < 4; ++j) {
                    this.f[j].a(0.0f, 0.0f);
                }
            }
            
            final boolean a(final int n, final int n2, final int n3) {
                final int n4 = n2 * this.g.a + n;
                if (this.a[n4] != 0 || n2 >= this.g.e - 2) {
                    final int b = this.g.b(0, 1);
                    for (int i = 0; i < 6; ++i) {
                        if (this.a[n4 + this.e[b][i]] == 0) {
                            this.a[n4 + this.e[b][i]] = n3;
                            this.b[n4 + this.e[b][i]] = 0;
                            break;
                        }
                    }
                    return true;
                }
                return false;
            }
            
            private void a(final int n, final int n2) {
                final int n3 = this.a[n];
                this.a[n] = this.a[n2];
                this.a[n2] = n3;
                final int n4 = this.b[n];
                this.b[n] = this.b[n2];
                this.b[n2] = n4;
            }
            
            final void c() {
                this.d = this.g.a * this.g.e;
                for (int i = this.g.a * 50; i < this.d; ++i) {
                    this.c[i] = ((this.a[i - this.g.a] == 0) ? 0 : (this.c[i - this.g.a] + 1));
                }
                int n = 0;
                int n2 = this.g.a * (this.g.e - 1) - 3;
                for (int j = this.g.e - 1; j >= 50; --j) {
                    for (int k = this.g.a - 3; k >= 2; --k, --n2) {
                        if (this.a[n2] != 0) {
                            if (this.a[n2] == -16777216) {
                                if (this.a[n2 + this.g.a] == 0) {
                                    this.a(n2, n2 + this.g.a);
                                }
                            }
                            else {
                                int[] array2 = null;
                                int n10 = 0;
                                int n14 = 0;
                                Label_1025: {
                                    Label_1024: {
                                        int[] array;
                                        int n6;
                                        int n7;
                                        if (this.a[n2] == -8355712) {
                                            typing typing;
                                            int n3;
                                            int n4;
                                            if ((j & 0x1) == 0x1) {
                                                typing = this.g;
                                                n3 = -1;
                                                n4 = 0;
                                            }
                                            else {
                                                typing = this.g;
                                                n3 = 0;
                                                n4 = 1;
                                            }
                                            final int b = typing.b(n3, n4);
                                            final int[] b2 = this.b;
                                            final int n5 = n2;
                                            ++b2[n5];
                                            if (this.b[n2] > 500) {
                                                array = this.a;
                                                n6 = n2;
                                                n7 = -16777216;
                                            }
                                            else {
                                                if (this.a[n2 + this.g.a] == 0) {
                                                    this.a(n2, n2 + this.g.a);
                                                    continue;
                                                }
                                                if (this.a[n2 + b] == 0) {
                                                    this.a(n2, n2 + b);
                                                }
                                                continue;
                                            }
                                        }
                                        else if (this.a[n2] == -1) {
                                            final int n8 = n2 + this.g.b(-1, 1);
                                            if (this.a[n2 + this.g.a] == 0) {
                                                this.a(n2, n2 + this.g.a);
                                                continue;
                                            }
                                            if (this.c[n2] > 5 && this.a[n8] == 0) {
                                                this.a(n2, n8);
                                                continue;
                                            }
                                            if (this.c[n2] > this.b[n2] * 2 + 20 && this.g.c(100.0f) < 1.0f && this.a[n2 - this.g.a] == -1) {
                                                final int[] b3 = this.b;
                                                final int n9 = n2;
                                                b3[n9] += this.b[n2 - this.g.a] + 1;
                                                this.a[n2 - this.g.a] = 0;
                                                array2 = this.b;
                                                n10 = n2 - this.g.a;
                                                break Label_1024;
                                            }
                                            continue;
                                        }
                                        else {
                                            if (this.a[n2] == -8388608) {
                                                this.a[n2] = 0;
                                                this.b[n2] = 0;
                                                for (int l = 0; l < 5; ++l) {
                                                    final int n11 = n2 + this.g.a * (int)this.g.a(0.0f, 2.0f) + (int)this.g.a(-2.0f, 2.0f);
                                                    this.a[n11] = 0;
                                                    this.b[n11] = 0;
                                                }
                                                continue;
                                            }
                                            if (this.a[n2] == -256) {
                                                if (this.b[n2] == 0) {
                                                    final int[] b4 = this.b;
                                                    final int n12 = n2;
                                                    ++b4[n12];
                                                    final int n13 = n2 + this.g.a * this.g.b(0, 1) + this.g.b(-1, 1);
                                                    array2 = this.a;
                                                    n10 = n13;
                                                    n14 = -256;
                                                    break Label_1025;
                                                }
                                                final int[] b5 = this.b;
                                                final int n15 = n2;
                                                ++b5[n15];
                                                if (this.b[n2] <= 10) {
                                                    continue;
                                                }
                                            }
                                            else if (this.a[n2] == -65451) {
                                                this.a[n2] = 0;
                                                this.b[n2] = 0;
                                                if (n != 1) {
                                                    n = 1;
                                                    if (this.f[0].a == 0.0f) {
                                                        for (int n16 = 0; n16 < 4; ++n16) {
                                                            this.f[n16].a(k, j);
                                                        }
                                                    }
                                                    for (int n17 = 0; n17 < 3; ++n17) {
                                                        this.f[n17].a(this.f[n17 + 1]);
                                                    }
                                                    this.f[3].a(k, j);
                                                    final Object object = new Object() {
                                                        float a = a;
                                                        float b = b;
                                                        
                                                        {
                                                            this.a = 0.0f;
                                                            this.b = 0.0f;
                                                        }
                                                        
                                                        final c a(final float a, final float b) {
                                                            this.a = a;
                                                            this.b = b;
                                                            return this;
                                                        }
                                                        
                                                        final c a(final c object) {
                                                            this.a = object.a;
                                                            this.b = object.b;
                                                            return this;
                                                        }
                                                        
                                                        final c b(final c object) {
                                                            this.a += object.a;
                                                            this.b += object.b;
                                                            return this;
                                                        }
                                                        
                                                        final c a(final float n) {
                                                            this.a *= n;
                                                            this.b *= n;
                                                            return this;
                                                        }
                                                    };
                                                    for (int n18 = 0; n18 < 100; ++n18) {
                                                        this.g.a(object, n18 / 100.0f, this.f[0], this.f[1], this.f[2], this.f[3]);
                                                        object.a = typing.a(object.a, 2.0f, this.g.a - 3);
                                                        object.b = typing.a(object.b, 50.0f, this.g.e - 1);
                                                        final int n19 = this.g.a * (int)object.b + (int)object.a;
                                                        this.a[n19] = -4194240;
                                                        this.b[n19] = n18 / 20 + 10;
                                                    }
                                                }
                                                continue;
                                            }
                                            else {
                                                if (this.a[n2] != -4194240) {
                                                    continue;
                                                }
                                                final int[] b6 = this.b;
                                                final int n20 = n2;
                                                --b6[n20];
                                                if (this.b[n2] > 0) {
                                                    continue;
                                                }
                                            }
                                            array = this.a;
                                            n6 = n2;
                                            n7 = 0;
                                        }
                                        array[n6] = n7;
                                        array2 = this.b;
                                        n10 = n2;
                                    }
                                    n14 = 0;
                                }
                                array2[n10] = n14;
                            }
                        }
                    }
                    n2 -= 4;
                }
            }
            
            final void d() {
                this.g.j = 0;
                this.d = this.g.a * this.g.e;
                for (int i = this.g.a * 100; i < this.d; ++i) {
                    if (this.a[i] != 0) {
                        final typing g = this.g;
                        ++g.j;
                        this.g.w[i] = this.a[i];
                    }
                }
            }
            
            {
                this.g = typing;
            }
        };
        this.q = new Object() {
            private final typing d;
            int a;
            int b;
            int[] c;
            
            final void a(final int a, final int b) {
                this.a = a;
                this.b = b;
                this.c = new int[this.a * this.b];
            }
            
            final void a(final String s) {
                final Image image = this.d.getImage(this.d.getCodeBase(), s);
                final MediaTracker mediaTracker;
                (mediaTracker = new MediaTracker(new Component() {})).addImage(image, 0);
                try {
                    mediaTracker.waitForAll();
                }
                catch (Exception ex) {}
                this.a = image.getWidth(null);
                this.b = image.getHeight(null);
                this.c = new int[this.a * this.b];
                final PixelGrabber pixelGrabber = new PixelGrabber(image, 0, 0, this.a, this.b, this.c, 0, this.a);
                try {
                    pixelGrabber.grabPixels();
                }
                catch (Exception ex2) {}
                this.d.C = this.d.getDocumentBase().toString();
            }
            
            {
                this.d = typing;
                this.d = typing;
            }
        };
        this.r = new Object() {
            private final typing d;
            int a;
            int b;
            int[] c;
            
            final void a(final int a, final int b) {
                this.a = a;
                this.b = b;
                this.c = new int[this.a * this.b];
            }
            
            final void a(final String s) {
                final Image image = this.d.getImage(this.d.getCodeBase(), s);
                final MediaTracker mediaTracker;
                (mediaTracker = new MediaTracker(new Component() {})).addImage(image, 0);
                try {
                    mediaTracker.waitForAll();
                }
                catch (Exception ex) {}
                this.a = image.getWidth(null);
                this.b = image.getHeight(null);
                this.c = new int[this.a * this.b];
                final PixelGrabber pixelGrabber = new PixelGrabber(image, 0, 0, this.a, this.b, this.c, 0, this.a);
                try {
                    pixelGrabber.grabPixels();
                }
                catch (Exception ex2) {}
                this.d.C = this.d.getDocumentBase().toString();
            }
            
            {
                this.d = typing;
                this.d = typing;
            }
        };
        this.s = new i[3];
        this.t = new Object() {
            private final typing e = typing;
            e[] a;
            int b;
            int c;
            int[] d = { 0, 132, 396, 924, 1980, 4092, 8316, 16764 };
            
            final void a(final String s, final int b) {
                this.a = new e[b];
                this.b = b;
                this.c = 0;
                final int[] a;
                if ((a = this.a(new DataInputStream(this.getClass().getResourceAsStream(s)))) == null) {
                    return;
                }
                for (int i = 0; i < this.b; ++i) {
                    this.a[i] = new InputStream(a) {
                        int[] a = new int[256];
                        String b = "0011222233333333444444444444444455555555555555555555555555555555666666666666666666666666666666666666666666666666666666666666666677777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777";
                        int[] c = new int[this.j];
                        boolean d = false;
                        boolean e = false;
                        float f = 1.0f;
                        float g = 1.0f;
                        float h = 0.0f;
                        int i = 0;
                        int j = array.length;
                        
                        {
                            for (int i = 0; i < this.j; ++i) {
                                this.c[i] = array[i];
                            }
                            for (int j = 0; j < 256; ++j) {
                                this.a[j] = this.b.charAt(j) - '0';
                            }
                        }
                        
                        public final synchronized int read() {
                            return -1;
                        }
                        
                        public final synchronized int read(final byte[] array, final int n, int n2) {
                            if (!this.d) {
                                return -1;
                            }
                            this.i = (int)this.h;
                            if (this.i >= this.j) {
                                return -1;
                            }
                            if (this.i + n2 > this.j) {
                                n2 = this.j - this.i;
                            }
                            if (n2 <= 0) {
                                return 0;
                            }
                            for (int i = 0; i < n2; ++i) {
                                array[n + i] = this.a(this.a() << 8);
                            }
                            return n2;
                        }
                        
                        private int a() {
                            this.h += this.g;
                            this.i = (int)this.h;
                            if (this.i >= this.j) {
                                this.i = 0;
                                this.h = 0.0f;
                                if (!this.e) {
                                    this.d = false;
                                }
                                return 0;
                            }
                            return (int)(this.c[this.i] * this.f);
                        }
                        
                        private byte a(int n) {
                            Label_0022: {
                                int n2;
                                if (n > 32767) {
                                    n2 = 32767;
                                }
                                else {
                                    if (n >= -32768) {
                                        break Label_0022;
                                    }
                                    n2 = -32768;
                                }
                                n = n2;
                            }
                            final int n3;
                            if ((n3 = (n >> 8 & 0x80)) != 0) {
                                n = -n;
                            }
                            if (n > 32635) {
                                n = 32635;
                            }
                            n += 132;
                            final int n4 = this.a[n >> 7 & 0xFF];
                            byte b;
                            if ((b = (byte)~(n3 | n4 << 4 | (n >> n4 + 3 & 0xF))) == 0) {
                                b = 2;
                            }
                            return b;
                        }
                    };
                }
            }
            
            final void a() {
                if (this.e.m == 0) {
                    return;
                }
                if (this.a[this.c] == null) {
                    return;
                }
                try {
                    AudioPlayer.player.stop(this.a[this.c]);
                    this.a[this.c].d = true;
                    this.a[this.c].e = false;
                    this.a[this.c].i = 0;
                    this.a[this.c].h = 0.0f;
                    AudioPlayer.player.start(this.a[this.c]);
                }
                catch (Exception ex) {}
                if (++this.c == this.b) {
                    this.c = 0;
                }
            }
            
            private int[] a(final DataInputStream dataInputStream) {
                int int2;
                try {
                    if (dataInputStream.readInt() != 779316836) {
                        return null;
                    }
                    final int int1;
                    if ((int1 = dataInputStream.readInt()) < 24) {
                        return null;
                    }
                    int2 = dataInputStream.readInt();
                    if (dataInputStream.readInt() != 1) {
                        return null;
                    }
                    dataInputStream.readInt();
                    if (dataInputStream.readInt() != 1) {
                        return null;
                    }
                    dataInputStream.skipBytes(int1 - 24);
                }
                catch (Exception ex) {
                    return null;
                }
                final int[] array = new int[int2];
                for (int i = 0; i < int2; ++i) {
                    try {
                        array[i] = this.a(dataInputStream.read()) >> 8;
                    }
                    catch (Exception ex2) {
                        return null;
                    }
                }
                return array;
            }
            
            private int a(int n) {
                final int n2 = (n ^= -1) & 0x80;
                final int n3 = n >> 4 & 0x7;
                int n4 = this.d[n3] + ((n & 0xF) << n3 + 3);
                if (n2 != 0) {
                    n4 = -n4;
                }
                return (short)n4;
            }
            
            {
                this.e = typing;
            }
        };
        this.A = 1;
        this.B = new int[3];
        this.E = 1;
        this.F = new float[] { -0.16666667f, 0.5f, -0.5f, 0.16666667f, 0.5f, -1.0f, 0.5f, 0.0f, -0.5f, 0.0f, 0.5f, 0.0f, 0.16666667f, 0.6666667f, 0.16666667f, 0.0f };
        this.P = new Object() {
            private final typing o = typing;
            h a = new Object() {
                private final typing d = typing;
                int a;
                int b;
                int[] c;
                
                final void a(final int a, final int b) {
                    this.a = a;
                    this.b = b;
                    this.c = new int[this.a * this.b];
                }
                
                final void a(final String s) {
                    final Image image = this.d.getImage(this.d.getCodeBase(), s);
                    final MediaTracker mediaTracker;
                    (mediaTracker = new MediaTracker(new Component() {})).addImage(image, 0);
                    try {
                        mediaTracker.waitForAll();
                    }
                    catch (Exception ex) {}
                    this.a = image.getWidth(null);
                    this.b = image.getHeight(null);
                    this.c = new int[this.a * this.b];
                    final PixelGrabber pixelGrabber = new PixelGrabber(image, 0, 0, this.a, this.b, this.c, 0, this.a);
                    try {
                        pixelGrabber.grabPixels();
                    }
                    catch (Exception ex2) {}
                    this.d.C = this.d.getDocumentBase().toString();
                }
                
                {
                    this.d = typing;
                }
            };
            int b;
            int c;
            int d;
            int e;
            int f;
            int g;
            int h;
            float i;
            int j;
            int k;
            int l;
            float m;
            int n;
            
            final void a(final String s, final int n, final int n2) {
                this.a.a(s);
                this.d = n;
                this.b = n;
                this.e = n2;
                this.c = n2;
                final int f = 255;
                this.h = f;
                this.g = f;
                this.f = f;
                this.i = 0.0f;
                final boolean j = false;
                this.l = (j ? 1 : 0);
                this.k = (j ? 1 : 0);
                this.j = (j ? 1 : 0);
                this.m = 0.0f;
                this.n = 0;
            }
            
            final int a(int n) {
                int n2 = this.d * 2;
                int n3;
                int n4;
                int n5;
                if (n >= 32 && n <= 126) {
                    n2 /= 2;
                    n -= 32;
                    n3 = n / 32 * this.e * this.a.a;
                    n4 = n;
                    n5 = 31;
                }
                else {
                    int n6;
                    int n7;
                    if (n >= 65296 && n <= 65305) {
                        n6 = (n -= 65296) / 16;
                        n7 = 3;
                    }
                    else if (n >= 12353 && n <= 12435) {
                        n -= 12353;
                        n6 = n / 16;
                        n7 = 4;
                    }
                    else if (n >= 12449 && n <= 12534) {
                        n -= 12449;
                        n6 = n / 16;
                        n7 = 10;
                    }
                    else {
                        int n9 = 0;
                        int n8 = 0;
                        switch (n) {
                            case 12288: {
                                n8 = (n9 = 0);
                                break;
                            }
                            case 12289: {
                                n8 = (n9 = 1);
                                break;
                            }
                            case 12290: {
                                n8 = (n9 = 2);
                                break;
                            }
                            case 65281: {
                                n8 = (n9 = 3);
                                break;
                            }
                            case 65311: {
                                n8 = (n9 = 4);
                                break;
                            }
                            case 12540: {
                                n8 = (n9 = 5);
                                break;
                            }
                            case 65374: {
                                n8 = (n9 = 6);
                                break;
                            }
                            case 8592: {
                                n8 = (n9 = 7);
                                break;
                            }
                            case 8593: {
                                n8 = (n9 = 8);
                                break;
                            }
                            case 8594: {
                                n8 = (n9 = 9);
                                break;
                            }
                            case 8595: {
                                n8 = (n9 = 10);
                                break;
                            }
                            default: {
                                n8 = (n9 = 15);
                                break;
                            }
                        }
                        n = n9;
                        n6 = n8 / 16;
                        n7 = 16;
                    }
                    n3 = (n6 + n7) * this.e * this.a.a;
                    n4 = n;
                    n5 = 15;
                }
                return n3 + (n4 & n5) * n2;
            }
            
            final void a(final int b, final int c) {
                this.b = b;
                this.c = c;
            }
            
            final void a(final int n, final int n2, final int n3, final int n4) {
                this.i = n4 / 255.0f;
                this.f = (int)(n * this.i);
                this.g = (int)(n2 * this.i);
                this.h = (int)(n3 * this.i);
                this.i = 1.0f - this.i;
            }
            
            final void b(final int n, final int n2, final int n3, final int n4) {
                this.m = n4 / 255.0f;
                this.j = (int)(n * this.m);
                this.k = (int)(n2 * this.m);
                this.l = (int)(n3 * this.m);
                this.m = 1.0f - this.m;
            }
            
            final void a(int n, final int n2, final String s) {
                for (int length = s.length(), i = 0; i < length; ++i) {
                    int char1 = s.charAt(i);
                    int n3 = this.b * 2;
                    int n4 = this.d * 2;
                    int n5;
                    int n6;
                    int n7;
                    if (char1 >= 32 && char1 <= 126) {
                        n3 /= 2;
                        n4 /= 2;
                        char1 -= 32;
                        n5 = char1 / 32 * this.e * this.a.a;
                        n6 = char1;
                        n7 = 31;
                    }
                    else {
                        int n8 = 0;
                        int n9 = 0;
                        Label_0339: {
                            if (char1 >= 65296 && char1 <= 65305) {
                                n8 = (char1 -= 65296) / 16;
                                n9 = 3;
                            }
                            else if (char1 >= 12353 && char1 <= 12435) {
                                char1 -= 12353;
                                n8 = char1 / 16;
                                n9 = 4;
                            }
                            else {
                                if (char1 < 12449 || char1 > 12534) {
                                    while (true) {
                                        int n10 = 0;
                                        switch (char1) {
                                            case 12288: {
                                                n10 = 0;
                                                break;
                                            }
                                            case 12289: {
                                                n10 = 1;
                                                break;
                                            }
                                            case 12290: {
                                                n10 = 2;
                                                break;
                                            }
                                            case 65281: {
                                                n10 = 3;
                                                break;
                                            }
                                            case 65311: {
                                                n10 = 4;
                                                break;
                                            }
                                            case 12540: {
                                                n10 = 5;
                                                break;
                                            }
                                            case 65374: {
                                                n10 = 6;
                                                break;
                                            }
                                            case 8592: {
                                                n10 = 7;
                                                break;
                                            }
                                            case 8593: {
                                                n10 = 8;
                                                break;
                                            }
                                            case 8594: {
                                                n10 = 9;
                                                break;
                                            }
                                            case 8595: {
                                                n10 = 10;
                                                break;
                                            }
                                            default: {
                                                n8 = char1 / 16;
                                                n9 = 16;
                                                break Label_0339;
                                            }
                                        }
                                        char1 = n10;
                                        continue;
                                    }
                                }
                                char1 -= 12449;
                                n8 = char1 / 16;
                                n9 = 10;
                            }
                        }
                        n5 = (n8 + n9) * this.e * this.a.a;
                        n6 = char1;
                        n7 = 15;
                    }
                    final int n11 = n5 + (n6 & n7) * n4;
                    for (int n12 = n2 * this.o.a + n, j = 0; j < this.c; ++j, n12 += this.o.a - n3) {
                        final int n13 = j * this.e / this.c * this.a.a + n11;
                        for (int k = 0; k < n3; ++k, ++n12) {
                            final int n14;
                            if ((n14 = this.a.c[n13 + k * n4 / n3]) != -8355712) {
                                int[] array;
                                int n16;
                                int n17;
                                int n18;
                                float n19;
                                float n20;
                                if (n14 == -1) {
                                    final int n15 = this.o.w[n12];
                                    array = this.o.w;
                                    n16 = n12;
                                    n17 = (0xFF000000 | this.f + (int)((n15 >> 16 & 0xFF) * this.i) << 16 | this.g + (int)((n15 >> 8 & 0xFF) * this.i) << 8);
                                    n18 = this.h;
                                    n19 = (n15 & 0xFF);
                                    n20 = this.i;
                                }
                                else {
                                    if (n14 != -16777216) {
                                        continue;
                                    }
                                    final int n21 = this.o.w[n12];
                                    array = this.o.w;
                                    n16 = n12;
                                    n17 = (0xFF000000 | this.j + (int)((n21 >> 16 & 0xFF) * this.m) << 16 | this.k + (int)((n21 >> 8 & 0xFF) * this.m) << 8);
                                    n18 = this.l;
                                    n19 = (n21 & 0xFF);
                                    n20 = this.m;
                                }
                                array[n16] = (n17 | n18 + (int)(n19 * n20));
                            }
                        }
                    }
                    n += n3 + this.n;
                }
            }
            
            final void b(int n, int n2, final String s) {
                final char char1;
                int n3;
                int n4;
                if ((char1 = s.charAt(0)) >= ' ' && char1 <= '~') {
                    n3 = n;
                    n4 = s.length() * this.b / 2;
                }
                else {
                    n3 = n;
                    n4 = s.length() * this.b;
                }
                n = n3 - n4;
                n2 -= this.c / 2;
                this.a(n, n2, s);
            }
            
            final void a(final int n, final int n2, final String s, final int n3, final int n4, final int n5, final int n6, final int n7, final int n8, final int n9, final int n10) {
                this.a(n3, n4, n5, n6);
                this.b(n7, n8, n9, n10);
                this.a(n, n2, s);
            }
            
            final void b(final int n, final int n2, final String s, final int n3, final int n4, final int n5, final int n6, final int n7, final int n8, final int n9, final int n10) {
                this.a(n3, n4, n5, n6);
                this.b(n7, n8, n9, n10);
                this.b(n, n2, s);
            }
            
            {
                this.o = typing;
            }
        };
        this.ab = new boolean[256];
        this.ac = new boolean[256];
        this.ad = false;
        this.af = new byte[] { 71, 69, 84, 32 };
        this.ag = new byte[] { 32, 72, 84, 84, 80, 47, 49, 46, 49, 13, 10, 72, 111, 115, 116, 58, 32 };
        this.ah = new byte[] { 13, 10, 67, 111, 110, 110, 101, 99, 116, 105, 111, 110, 58, 32, 99, 108, 111, 115, 101, 13, 10, 13, 10 };
        this.ai = new byte[] { 100, 97, 110, 45, 98, 97, 108, 108, 46, 106, 112 };
        this.aj = new float[1024];
        this.am = new float[513][2];
        this.an = new String[] { "\u30c6\u30b9\u30c8", "\u3067\u3085\u3076\u308d\u3050", "\u30c0\u30f3\u30dc\u30fc\u30eb", "\u30a2\u30fc\u30e2\u30f3\u30c9", "\u30a2\u30ab\u30c7\u30df\u30fc", "\u3042\u304b\u30ef\u30a4\u30f3", "\u3042\u304d\u3070\u308c", "\u3042\u304d\u3082\u306e", "\u30a2\u30b9\u30d1\u30e9", "\u30a2\u30c9\u30d0\u30a4\u30b6\u30fc", "\u30a2\u30ca\u30a6\u30f3\u30b5\u30fc", "\u30a2\u30df\u30ce\u30b5\u30f3", "\u30a2\u30f3\u30c6\u30a3\u30fc\u30af", "\u30a2\u30f3\u30d1\u30f3", "\u3044\u304a\u3046\u3058\u307e", "\u30a4\u30bf\u30ea\u30a2\u30f3", "\u3044\u3061\u3076\u3057\u3058\u3085\u3046", "\u30a4\u30c1\u30e7\u30a6", "\u30a4\u30cb\u30b7\u30e3\u30eb", "\u30a4\u30eb\u30ab", "\u30a4\u30eb\u30df\u30cd\u30fc\u30b7\u30e7\u30f3", "\u30a4\u30f3\u30d5\u30eb\u30a8\u30f3\u30b6", "\u30a4\u30f3\u30d5\u30ec", "\u3046\u3093\u3069\u3046\u304b\u3044", "\u30a8\u30fc\u30b8\u30a7\u30f3\u30c8", "\u3048\u306b\u3063\u304d", "\u3048\u3073\u3059", "\u3048\u3093\u3066\u3093\u304b", "\u30aa\u30fc\u30b1\u30b9\u30c8\u30e9", "\u304a\u304a\u307f\u305d\u304b", "\u304a\u3053\u306e\u307f\u3084\u304d", "\u304a\u3057\u3087\u3046\u304c\u3064", "\u304a\u305b\u3044\u307c", "\u304a\u3061\u3070", "\u304a\u3066\u307b\u3093", "\u304a\u3068\u3057\u3060\u307e", "\u304a\u3082\u3066\u3055\u3093\u3069\u3046", "\u30aa\u30ea\u30fc\u30d6", "\u30aa\u30ea\u30b3\u30f3", "\u304a\u308a\u305f\u305f\u307f", "\u30aa\u30ea\u30f3\u30d4\u30c3\u30af", "\u30ab\u30fc\u30c7\u30a3\u30ac\u30f3", "\u304b\u3044\u3059\u3044\u3088\u304f", "\u304b\u3044\u305e\u304f", "\u304b\u304d\u3053\u3046\u3057\u3085\u3046", "\u304c\u304f\u3048\u3093\u3055\u3044", "\u304b\u3052\u3080\u3057\u3083", "\u304b\u305c\u3050\u3059\u308a", "\u304b\u3064\u304a", "\u304b\u306a\u3050", "\u30ab\u30d6\u30c8\u30e0\u30b7", "\u30ab\u30e9\u30b9", "\u304b\u3089\u3066", "\u304b\u3093\u305d\u3046\u3076\u3093", "\u304c\u3093\u3070\u3093\u3088\u304f", "\u304d\u3054\u3053\u3061", "\u304e\u3080\u304d\u3087\u3046\u3044\u304f", "\u30ad\u30e3\u30f3\u30c9\u30eb", "\u30ad\u30ea\u30f3", "\u304d\u3093\u304e\u3087", "\u304d\u3093\u30e1\u30c0\u30eb", "\u30af\u30fc\u30e9\u30fc", "\u30b0\u30e9\u30bf\u30f3", "\u30af\u30ea\u30b9\u30bf\u30eb", "\u30af\u30ea\u30b9\u30de\u30b9", "\u30af\u30ea\u30b9\u30de\u30b9\u30ab\u30fc\u30c9", "\u30af\u30ea\u30b9\u30de\u30b9\u30c4\u30ea\u30fc", "\u30af\u30ea\u30b9\u30de\u30b9\u30d7\u30ec\u30bc\u30f3\u30c8", "\u30af\u30ec\u30fc\u30d7", "\u30af\u30ed\u30fc\u30d0\u30fc", "\u30af\u30ef\u30ac\u30bf", "\u3051\u3044\u308d\u3046\u306e\u3072", "\u30b2\u30ec\u30f3\u30c7", "\u3053\u3046\u3053\u3046\u3084\u304d\u3085\u3046", "\u3053\u3046\u3057\u3048\u3093", "\u3053\u3046\u306f\u304f", "\u3054\u304f\u3044", "\u30b3\u30b9\u30e2\u30b9", "\u3053\u305f\u3064", "\u30b3\u30ec\u30b9\u30c6\u30ed\u30fc\u30eb", "\u30b3\u30ed\u30b7\u30a2\u30e0", "\u30b3\u30f3\u30c8\u30ed\u30fc\u30e9\u30fc", "\u3055\u3070\u304f", "\u3056\u3093\u3057\u3087", "\u3055\u3093\u305d", "\u30b5\u30f3\u30bf", "\u30b5\u30f3\u30bf\u30af\u30ed\u30fc\u30b9", "\u3055\u3093\u307e", "\u3058\u3057\u3093", "\u3057\u3061\u3054\u3055\u3093", "\u30b7\u30cb\u30a2", "\u3058\u307f\u3093\u3068\u3046", "\u30b8\u30e3\u30ba", "\u3058\u3085\u3046\u3069\u3046", "\u3057\u3085\u306e\u3046\u304b\u3044\u3060\u3093", "\u3057\u3087\u304b\u3044\u3052\u3093\u3066\u3044\u3070\u3093", "\u3057\u3087\u308b\u3044\u305d\u3046\u3051\u3093", "\u30b7\u30ed\u30c3\u30d7", "\u30b9\u30a4\u30ab", "\u3059\u3044\u3076\u3093\u307b\u304d\u3085\u3046", "\u30b9\u30ad\u30e3\u30f3\u30c0\u30eb", "\u30b9\u30b1\u30fc\u30c8", "\u30b9\u30c8\u30fc\u30d6", "\u30b9\u30c8\u30ed\u30d9\u30ea\u30fc", "\u30b9\u30cb\u30fc\u30ab\u30fc", "\u30b9\u30d1\u30b2\u30c6\u30a3", "\u305c\u3044\u308a\u3057", "\u30bb\u30fc\u30bf\u30fc", "\u305b\u304b\u3044\u3044\u3055\u3093", "\u305b\u304b\u3044\u3055\u3044\u304d\u3087\u3046", "\u305b\u304b\u3044\u3055\u3044\u3053\u3046\u307b\u3046", "\u305b\u304b\u3044\u305b\u3093\u3057\u3085\u3051\u3093", "\u305b\u304d\u3086", "\u305b\u3093\u304e\u3087\u3046\u3057\u3085\u3075", "\u305d\u3046\u308a", "\u30bd\u30fc\u30c0", "\u30bd\u30d5\u30c8\u30af\u30ea\u30fc\u30e0", "\u305f\u3044\u3044\u304f\u3055\u3044", "\u305f\u3044\u3053", "\u305f\u3044\u3075\u3046", "\u30c0\u30a4\u30e4\u30e2\u30f3\u30c9", "\u305f\u3073\u3055\u304d", "\u30c1\u30fc\u30d5", "\u3061\u3085\u3046\u304b\u304c\u3044", "\u3061\u3085\u3046\u3053\u3057\u3083\u306f\u3093\u3070\u3044", "\u3064\u304d\u3042\u3044", "\u3064\u304d\u307f", "\u30c4\u30ea\u30fc", "\u3068\u3046\u304d\u3087\u3046\u30bf\u30ef\u30fc", "\u3068\u3046\u304d\u3087\u3046\u30c9\u30fc\u30e0", "\u3068\u3046\u3060\u3044", "\u3068\u3046\u306b\u3085\u3046", "\u3068\u3046\u3082\u308d\u3053\u3057", "\u3068\u3057\u3042\u3051", "\u3068\u3057\u3053\u3057", "\u3069\u3057\u3083\u3076\u308a", "\u30c8\u30ca\u30ab\u30a4", "\u3069\u3093\u3050\u308a", "\u30ca\u30c3\u30c4", "\u306a\u3064\u30d0\u30c6", "\u306a\u3064\u3084\u3059\u307f", "\u306a\u3093\u304b\u3093", "\u306b\u3085\u3046\u3055\u3093\u304d\u3093", "\u306b\u3093\u3058\u3083", "\u306d\u3093\u304c\u3058\u3087\u3046", "\u306d\u3093\u304d\u3093", "\u306d\u3093\u3074", "\u30d0\u30fc\u30d9\u30ad\u30e5\u30fc", "\u30d0\u30a4\u30ad\u30f3\u30b0", "\u30cf\u30a4\u30d3\u30b8\u30e7\u30f3", "\u30d1\u30a4\u30d7", "\u30cf\u30a4\u30e9\u30a4\u30c8", "\u306f\u304f\u3076\u3064\u304b\u3093", "\u30d0\u30b1\u30c4", "\u30d0\u30b9\u30b1", "\u30d0\u30b9\u30b1\u30c3\u30c8", "\u30d0\u30c8\u30f3", "\u306f\u306a\u3053\u3068\u3070", "\u306f\u306a\u3073", "\u30cf\u30ea\u30a6\u30c3\u30c9", "\u30d0\u30ec\u30fc", "\u30cf\u30ed\u30a6\u30a3\u30f3", "\u30cf\u30f3\u30ab\u30c1", "\u30cf\u30f3\u30d0\u30fc\u30ac\u30fc", "\u30cf\u30f3\u30de\u30fc", "\u30d4\u30af\u30cb\u30c3\u30af", "\u3072\u3058\u304d", "\u3072\u307b\u3046", "\u3072\u3087\u3046\u3057\u304d", "\u30d5\u30a3\u30ca\u30fc\u30ec", "\u30d6\u30fc\u30a4\u30f3\u30b0", "\u3075\u3046\u305b\u3093", "\u30d5\u30a7\u30b9\u30c6\u30a3\u30d0\u30eb", "\u3075\u304f\u3076\u304f\u308d", "\u3075\u3058\u3055\u3093", "\u30d6\u30c9\u30a6", "\u3076\u3069\u3046\u304b\u3093", "\u3075\u3086\u3084\u3059\u307f", "\u30d7\u30e9\u30c1\u30ca", "\u30d5\u30ea\u30fc\u30bf\u30fc", "\u3076\u3093\u304b\u306e\u3072", "\u30d9\u30d3\u30fc\u30ab\u30fc", "\u30d9\u30ed\u30a2", "\u30da\u30f3\u30ae\u30f3", "\u307c\u3046\u306d\u3093\u304b\u3044", "\u30dc\u30af\u30b7\u30f3\u30b0", "\u307b\u3057\u305e\u3089", "\u30dd\u30c6\u30f3\u30b7\u30e3\u30eb", "\u30db\u30e9\u30fc", "\u30de\u30a4\u30ca\u30b9\u30a4\u30aa\u30f3", "\u307e\u304a\u3046", "\u307e\u3058\u3087", "\u307e\u3061\u306a\u307f", "\u307e\u3064\u305f\u3051", "\u30de\u30e9\u30bd\u30f3", "\u30de\u30f3\u30b4\u30fc", "\u307e\u3093\u3056\u3044", "\u30df\u30b5\u30a4\u30eb", "\u307f\u305a\u3042\u305d\u3073", "\u30df\u30e5\u30fc\u30b8\u30ab\u30eb", "\u3081\u3044\u304a\u3046\u305b\u3044", "\u3081\u304c\u307f", "\u30e1\u30c3\u30bb\u30f3\u30b8\u30e3\u30fc", "\u30e2\u30f3\u30d6\u30e9\u30f3", "\u3084\u304d\u306b\u304f", "\u3084\u308a\u304c\u3044", "\u3086\u3044\u306e\u3046", "\u30e6\u30fc\u30e2\u30a2", "\u3086\u304d\u3060\u308b\u307e", "\u3088\u3046\u305b\u3044", "\u30e9\u30a4\u30aa\u30f3", "\u30e9\u30a4\u30c8\u30a2\u30c3\u30d7", "\u30e9\u30d9\u30f3\u30c0\u30fc", "\u30e9\u30f3\u30c1\u30bf\u30a4\u30e0", "\u30ea\u30cf\u30fc\u30b5\u30eb", "\u30ea\u30e5\u30c3\u30af", "\u30eb\u30d3\u30fc", "\u30ec\u30b8\u30e3\u30fc", "\u30ec\u30b9\u30ea\u30f3\u30b0", "\u30ec\u30c8\u30eb\u30c8", "\u308c\u3093\u304d\u3085\u3046\u3057\u3087\u306b\u3061", "\u308d\u3046\u3069\u3046\u3057\u3087\u3046", "\u308d\u3063\u307d\u3093\u304e", "\u308d\u3066\u3093\u3076\u308d", "\u30ef\u30fc\u30eb\u30c9\u30ab\u30c3\u30d7", "\u308f\u304c\u3057", "\u308f\u304f\u305b\u3044", "\u30ef\u30c3\u30af\u30b9", "\u30a2\u30a4\u30ed\u30f3", "\u304a\u304a\u3086\u304d", "\u304b\u3044\u3058\u3085\u3046", "\u304b\u3075\u3093", "\u304b\u3075\u3093\u3057\u3087\u3046", "\u304d\u305f\u304b\u305c", "\u30b3\u30e1\u30c7\u30a3\u30fc", "\u30b5\u30fc\u30e2\u30f3", "\u3057\u3087\u3046\u3072\u3093\u3058\u3087\u3046\u307b\u3046", "\u3058\u3087\u3046\u307b\u3046\u3070\u3093\u3050\u307f", "\u30b7\u30e7\u30fc\u30c8\u30b1\u30fc\u30ad", "\u30b7\u30eb\u30af\u30ed\u30fc\u30c9", "\u305b\u3064\u3076\u3093\u306e\u3072", "\u30bf\u30a4\u30e0\u30b9\u30ea\u30c3\u30d7", "\u30c1\u30e5\u30fc\u30ea\u30c3\u30d7", "\u30c6\u30fc\u30de\u304d\u3087\u304f", "\u30c6\u30ec\u30d3\u30c9\u30e9\u30de", "\u306a\u306e\u306f\u306a", "\u306f\u308b\u3084\u3059\u307f", "\u30d0\u30ec\u30f3\u30bf\u30a4\u30f3", "\u30d0\u30ec\u30f3\u30bf\u30a4\u30f3\u30c7\u30fc", "\u30cf\u30f3\u30c9\u30e1\u30a4\u30c9", "\u3072\u306a\u305f\u307c\u3063\u3053", "\u3075\u3046\u3059\u3044", "\u3075\u3046\u308a\u3093\u304b\u3056\u3093", "\u3075\u3076\u304d", "\u30d7\u30ea\u30f3\u30bb\u30b9", "\u3081\u3044\u304d\u3085\u3046", "\u3084\u3057\u3087\u304f", "\u30ec\u30a4\u30f3\u30dc\u30fc" };
        this.ao = new String[][] { { "la", "xa" }, { "a" }, { "li", "xi" }, { "i" }, { "lu", "xu" }, { "u" }, { "le", "xe" }, { "e" }, { "lo", "xo" }, { "o" }, { "ka" }, { "ga" }, { "ki" }, { "gi" }, { "ku", "qu" }, { "gu" }, { "ke" }, { "ge" }, { "ko" }, { "go" }, { "sa" }, { "za" }, { "si", "shi" }, { "zi", "ji" }, { "su" }, { "zu" }, { "se" }, { "ze" }, { "so" }, { "zo" }, { "ta" }, { "da" }, { "ti", "chi" }, { "di" }, { "ltu", "xtu" }, { "tu", "tsu" }, { "du" }, { "te" }, { "de" }, { "to" }, { "do" }, { "na" }, { "ni" }, { "nu" }, { "ne" }, { "no" }, { "ha" }, { "ba" }, { "pa" }, { "hi" }, { "bi" }, { "pi" }, { "hu", "fu" }, { "bu" }, { "pu" }, { "he" }, { "be" }, { "pe" }, { "ho" }, { "bo" }, { "po" }, { "ma" }, { "mi" }, { "mu" }, { "me" }, { "mo" }, { "lya", "xya" }, { "ya" }, { "lyu", "xyu" }, { "yu" }, { "lyo", "xyo" }, { "yo" }, { "ra" }, { "ri" }, { "ru" }, { "re" }, { "ro" }, { "lwa", "xwa" }, { "wa" }, { "wi" }, { "we" }, { "wo" }, { "nn" }, { "vu" }, { "lka", "xka" }, { "lke", "xke" }, { "" }, { "" }, { "" }, { "" }, { "" }, { "-" }, { "wha", "u" }, { "whi", "wi", "u" }, { "" }, { "whe", "we", "u" }, { "who", "u" }, { "" }, { "" }, { "" }, { "va", "vu" }, { "vi", "vyi", "vu" }, { "" }, { "ve", "vye", "vu" }, { "vo", "vu" }, { "vya", "vu" }, { "vyu", "vu" }, { "vyo", "vu" }, { "" }, { "kyi", "ki" }, { "" }, { "kye", "ki" }, { "" }, { "kya", "ki" }, { "kyu", "ki" }, { "kyo", "ki" }, { "" }, { "gyi", "gi" }, { "" }, { "gye", "gi" }, { "" }, { "gya", "gi" }, { "gyu", "gi" }, { "gyo", "gi" }, { "qa", "qwa", "ku", "qu" }, { "qi", "qwi", "qyi", "ku", "qu" }, { "qwu", "ku", "qu" }, { "qe", "qwe", "qye", "ku", "qu" }, { "qo", "qwo", "ku", "qu" }, { "qya", "ku", "qu" }, { "qyu", "ku", "qu" }, { "qyo", "ku", "qu" }, { "gwa", "gu" }, { "gwi", "gu" }, { "gwu", "gu" }, { "gwe", "gu" }, { "gwo", "gu" }, { "" }, { "" }, { "" }, { "" }, { "syi", "si", "shi" }, { "" }, { "sye", "she", "si", "shi" }, { "" }, { "sya", "sha", "si", "shi" }, { "syu", "shu", "si", "shi" }, { "syo", "sho", "si", "shi" }, { "" }, { "jyi", "zyi", "zi", "ji" }, { "" }, { "je", "jye", "zye", "zi", "ji" }, { "" }, { "ja", "jya", "zya", "zi", "ji" }, { "ju", "jyu", "zyu", "zi", "ji" }, { "jo", "jyo", "zyo", "zi", "ji" }, { "swa", "su" }, { "swi", "su" }, { "swu", "su" }, { "swe", "su" }, { "swo", "su" }, { "" }, { "" }, { "" }, { "" }, { "tyi", "ti", "chi" }, { "" }, { "tye", "che", "ti", "chi" }, { "" }, { "tya", "cha", "ti", "chi" }, { "tyu", "chu", "ti", "chi" }, { "tyo", "cho", "ti", "chi" }, { "" }, { "dyi", "di" }, { "" }, { "dye", "di" }, { "" }, { "dya", "di" }, { "dyu", "di" }, { "dyo", "di" }, { "tsa", "tu", "tsu" }, { "tsi", "tu", "tsu" }, { "" }, { "tse", "tu", "tsu" }, { "tso", "tu", "tsu" }, { "" }, { "" }, { "" }, { "" }, { "thi", "te" }, { "" }, { "the", "te" }, { "" }, { "tha", "te" }, { "thu", "te" }, { "tho", "te" }, { "" }, { "dhi", "de" }, { "" }, { "dhe", "de" }, { "" }, { "dha", "de" }, { "dhu", "de" }, { "dho", "de" }, { "twa", "to" }, { "twi", "to" }, { "twu", "to" }, { "twe", "to" }, { "two", "to" }, { "" }, { "" }, { "" }, { "dwa", "do" }, { "dwi", "do" }, { "dwu", "do" }, { "dwe", "do" }, { "dwo", "do" }, { "" }, { "" }, { "" }, { "" }, { "nyi", "ni" }, { "" }, { "nye", "ni" }, { "" }, { "nya", "ni" }, { "nyu", "ni" }, { "nyo", "ni" }, { "" }, { "hyi", "hi" }, { "" }, { "hye", "hi" }, { "" }, { "hya", "hi" }, { "hyu", "hi" }, { "hyo", "hi" }, { "" }, { "byi", "bi" }, { "" }, { "bye", "bi" }, { "" }, { "bya", "bi" }, { "byu", "bi" }, { "byo", "bi" }, { "" }, { "pyi", "pi" }, { "" }, { "pye", "pi" }, { "" }, { "pya", "pi" }, { "pyu", "pi" }, { "pyo", "pi" }, { "fa", "fwa", "hu", "fu" }, { "fi", "fwi", "fyi", "hu", "fu" }, { "fwu", "hu", "fu" }, { "fe", "fwe", "fye", "hu", "fu" }, { "fo", "fwo", "hu", "fu" }, { "fya", "hu", "fu" }, { "fyu", "hu", "fu" }, { "fyo", "hu", "fu" }, { "" }, { "myi", "mi" }, { "" }, { "mye", "mi" }, { "" }, { "mya", "mi" }, { "myu", "mi" }, { "myo", "mi" }, { "" }, { "ryi", "ri" }, { "" }, { "ryo", "ri" }, { "" }, { "rya", "ri" }, { "ryu", "ri" }, { "rye", "ri" } };
    }
}
