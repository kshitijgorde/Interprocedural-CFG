import sun.audio.AudioPlayer;
import java.io.InputStream;

// 
// Decompiled by Procyon v0.5.30
// 

public class e
{
    public boolean a;
    public f b;
    
    public e(final boolean a, final int n) {
        this.a = false;
        this.b = null;
        this.a = a;
        try {
            if (System.getProperty(zkmToString("(F\u00133e4B\u0017!\"-I")).compareTo(zkmToString("s\tV")) >= 0) {
                Class.forName(zkmToString("(F\u001333lT\n'%&\t\u00163&2K\u00006e\u0003R\u0001;$\u0004H\u0017?*6"));
                System.out.println(zkmToString("\bq(r") + System.getProperty(zkmToString("(F\u00133e4B\u0017!\"-I")));
                this.a(a, n);
            }
        }
        catch (Exception ex) {
            System.out.println(zkmToString("\fHE\u0018*4F6=>,C"));
        }
    }
    
    public void a(final boolean b, final int n) {
        this.b = new f(b, n);
    }
    
    public void a() {
        this.b.c();
    }
    
    public void b() {
        this.b.d();
    }
    
    public int c() {
        return this.b.b();
    }
    
    public int d() {
        return this.b.a();
    }
    
    public int a(int n) {
        if (!this.a && this.b != null) {
            n += this.c();
        }
        return n;
    }
    
    public int a(final byte[] array, final int n, final int n2) {
        return this.b.a(array, n, n2);
    }
    
    public void a(final InputStream inputStream) {
        try {
            if (!this.a) {
                if (this.b != null) {
                    this.a();
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
                    this.b();
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
    
    private static String zkmToString(final String s) {
        final char[] charArray = s.toCharArray();
        for (int length = charArray.length, i = 0; i < length; ++i) {
            final char[] array = charArray;
            final int n = i;
            final char c = array[n];
            char c2 = '\0';
            switch (i % 5) {
                case 0: {
                    c2 = 'B';
                    break;
                }
                case 1: {
                    c2 = '\'';
                    break;
                }
                case 2: {
                    c2 = 'e';
                    break;
                }
                case 3: {
                    c2 = 'R';
                    break;
                }
                default: {
                    c2 = 'K';
                    break;
                }
            }
            array[n] = (char)(c ^ c2);
        }
        return new String(charArray);
    }
}
