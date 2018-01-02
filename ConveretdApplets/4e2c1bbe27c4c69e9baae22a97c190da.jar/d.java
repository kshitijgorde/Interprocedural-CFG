import sun.audio.AudioPlayer;
import java.io.InputStream;

// 
// Decompiled by Procyon v0.5.30
// 

public class d
{
    public boolean a;
    public e b;
    
    public d(final boolean a, final int n, final boolean b) {
        this.a = false;
        this.b = null;
        this.a = a;
        try {
            if (b) {
                this.a(a, n);
            }
        }
        catch (Exception ex) {
            System.out.println(zkmToString("@\u000be\u001c{x\u0005\u00169o`\u0000"));
            ex.printStackTrace();
        }
    }
    
    public void a(final boolean b, final int n) {
        try {
            System.out.println(zkmToString("D2\bvlk\u00166?u`D") + System.getProperty(zkmToString("d\u0005374x\u00017%sa\n")));
            this.b = new e(b, n);
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
    
    public int a(final boolean b) {
        return this.b.a(b);
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
        catch (SecurityException ex) {}
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
        catch (SecurityException ex) {}
    }
    
    public void a(final f f, final boolean b) {
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
                else if (!b) {
                    f.i = true;
                    AudioPlayer.player.stop(f);
                    f.i = false;
                    AudioPlayer.player.start(f);
                }
            }
        }
        catch (SecurityException ex) {}
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
                    c2 = '\u000e';
                    break;
                }
                case 1: {
                    c2 = 'd';
                    break;
                }
                case 2: {
                    c2 = 'E';
                    break;
                }
                case 3: {
                    c2 = 'V';
                    break;
                }
                default: {
                    c2 = '\u001a';
                    break;
                }
            }
            array[n] = (char)(c ^ c2);
        }
        return new String(charArray);
    }
}
