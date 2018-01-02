import sun.audio.AudioPlayer;
import java.io.InputStream;

// 
// Decompiled by Procyon v0.5.30
// 

public class f
{
    public boolean a;
    public g b;
    public h c;
    
    public f(final boolean a, final int n, final boolean b, final int n2, final int n3) {
        this.a = false;
        this.b = null;
        this.c = null;
        this.a = a;
        try {
            if (b) {
                this.b(a, n, n2, n3);
            }
            else {
                this.a(a, n, n2, n3);
            }
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    
    public void a(final boolean b, final int n, final int n2, final int n3) {
        try {
            try {
                System.out.println(zkmToString("8[A+\f\u0017\u007f\u007fb\u0015\u001c-") + System.getProperty(zkmToString("\u0018lzjT\u0004h~x\u0013\u001dc")));
                this.c = new h(b, n, n2, n3);
            }
            catch (Exception ex) {
                this.a = true;
            }
        }
        catch (IllegalAccessError illegalAccessError) {}
    }
    
    public void b(final boolean b, final int n, final int n2, final int n3) {
        try {
            System.out.println(zkmToString("8[A+\f\u0017\u007f\u007fb\u0015\u001c-") + System.getProperty(zkmToString("\u0018lzjT\u0004h~x\u0013\u001dc")));
            this.b = new g(b, n, n2, n3);
            this.a = this.b.d;
            if (this.a) {
                this.b = null;
            }
        }
        catch (Exception ex) {
            this.a = true;
        }
    }
    
    public int a() {
        if (this.b != null) {
            return this.b.a();
        }
        if (this.c != null) {
            return this.c.a();
        }
        return 0;
    }
    
    public int b() {
        return this.b.b();
    }
    
    public int c() {
        return this.c.b();
    }
    
    public int a(int n) {
        if (!this.a) {
            if (this.b != null) {
                n += this.b();
            }
            if (this.c != null) {
                n += this.c();
            }
        }
        return n;
    }
    
    public int a(final byte[] array, final int n, final int n2) {
        return this.b.a(array, n, n2);
    }
    
    public int b(final byte[] array, final int n, final int n2) {
        return this.c.a(array, n, n2);
    }
    
    public void d() {
        this.b.e();
    }
    
    public void e() {
        this.b.c();
    }
    
    public void f() {
        this.b.d();
    }
    
    public void g() {
        this.c.c();
    }
    
    public void h() {
        this.c.f();
    }
    
    public void i() {
        this.c.d();
    }
    
    public void j() {
        this.c.e();
    }
    
    public long k() {
        if (this.b == null) {
            return 0L;
        }
        return this.b.f();
    }
    
    public long l() {
        if (this.c == null) {
            return 0L;
        }
        return this.c.g();
    }
    
    public void a(final InputStream inputStream) {
        try {
            if (!this.a) {
                if (this.b != null) {
                    this.e();
                }
                else if (this.c != null) {
                    this.i();
                }
                else {
                    AudioPlayer.player.start(inputStream);
                }
            }
        }
        catch (SecurityException ex) {
            ex.printStackTrace();
        }
    }
    
    public void b(final InputStream inputStream) {
        try {
            if (!this.a) {
                if (this.b != null) {
                    this.f();
                }
                else if (this.c != null) {
                    this.j();
                }
                else {
                    AudioPlayer.player.stop(inputStream);
                }
            }
        }
        catch (SecurityException ex) {
            ex.printStackTrace();
        }
    }
    
    public void a(final i i, final boolean b) {
        try {
            if (!this.a) {
                if (this.b != null) {
                    if (b) {
                        this.d();
                    }
                    else {
                        this.e();
                    }
                }
                else if (this.c != null) {
                    if (b) {
                        this.h();
                    }
                    else {
                        this.i();
                    }
                }
                else if (i != null) {
                    if (!b || i == null || i.ab == null || !i.ab.c) {
                        i.k = true;
                        AudioPlayer.player.stop(i);
                        i.k = false;
                    }
                    if (!b) {
                        AudioPlayer.player.start(i);
                    }
                }
            }
        }
        catch (SecurityException ex) {
            ex.printStackTrace();
        }
    }
    
    private static String zkmToString(final String s) {
        final char[] charArray = s.toCharArray();
        for (int length = charArray.length, i = 0; i < length; ++i) {
            final char[] array = charArray;
            final int n = i;
            final char c = array[n];
            char c2 = '\0';
            switch (i % 5) {
                case 0: {
                    c2 = 'r';
                    break;
                }
                case 1: {
                    c2 = '\r';
                    break;
                }
                case 2: {
                    c2 = '\f';
                    break;
                }
                case 3: {
                    c2 = '\u000b';
                    break;
                }
                default: {
                    c2 = 'z';
                    break;
                }
            }
            array[n] = (char)(c ^ c2);
        }
        return new String(charArray);
    }
}
