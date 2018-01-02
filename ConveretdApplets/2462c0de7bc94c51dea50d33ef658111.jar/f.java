import sun.audio.AudioPlayer;
import java.io.InputStream;

// 
// Decompiled by Procyon v0.5.30
// 

public class f
{
    public boolean a;
    public g b;
    
    public f(final boolean a, final int n, final boolean b) {
        this.a = false;
        this.b = null;
        this.a = a;
        try {
            if (b) {
                this.a(a, n);
            }
        }
        catch (Exception ex) {
            System.out.println(zkmToString("\u0019BG;\u0001!L4\u001e\u00159I"));
            ex.printStackTrace();
        }
    }
    
    public void a(final boolean b, final int n) {
        try {
            System.out.println(zkmToString("\u001d{*Q\u00162_\u0014\u0018\u000f9\r") + System.getProperty(zkmToString("=L\u0011\u0010N!H\u0015\u0002\t8C")));
            this.b = new g(b, n);
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
        return this.b.a();
    }
    
    public int b() {
        return this.b.b();
    }
    
    public int a(int n) {
        if (!this.a && this.b != null) {
            n += this.b();
        }
        return n;
    }
    
    public int a(final byte[] array, final int n, final int n2) {
        return this.b.a(array, n, n2);
    }
    
    public void c() {
        this.b.c();
    }
    
    public void d() {
        this.b.d();
    }
    
    public void e() {
        this.b.e();
    }
    
    public long f() {
        if (this.b == null) {
            return 0L;
        }
        return this.b.f();
    }
    
    public void a(final InputStream inputStream) {
        try {
            if (!this.a) {
                if (this.b != null) {
                    this.c();
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
                    this.d();
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
    
    public void a(final h h, final boolean b) {
        try {
            if (!this.a) {
                if (this.b != null) {
                    if (b) {
                        this.e();
                    }
                    else {
                        this.c();
                    }
                }
                else if (h != null) {
                    System.out.println(zkmToString(">C]Q") + h);
                    if (h != null) {
                        System.out.println(zkmToString(">CI\u001e\u00179H\u0015K@") + h.aa);
                    }
                    if (!b || h == null || h.aa == null || !h.aa.c) {
                        h.j = true;
                        AudioPlayer.player.stop(h);
                        h.j = false;
                    }
                    if (!b) {
                        AudioPlayer.player.start(h);
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
                    c2 = 'W';
                    break;
                }
                case 1: {
                    c2 = '-';
                    break;
                }
                case 2: {
                    c2 = 'g';
                    break;
                }
                case 3: {
                    c2 = 'q';
                    break;
                }
                default: {
                    c2 = '`';
                    break;
                }
            }
            array[n] = (char)(c ^ c2);
        }
        return new String(charArray);
    }
}
