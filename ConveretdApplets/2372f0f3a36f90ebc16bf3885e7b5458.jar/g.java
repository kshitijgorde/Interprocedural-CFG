import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.Line;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.SourceDataLine;

// 
// Decompiled by Procyon v0.5.30
// 

public class g
{
    public SourceDataLine a;
    public int b;
    public byte[] c;
    public boolean d;
    public static Class e;
    
    public g(final boolean b, final int n, final int n2, final int n3) {
        this.a = null;
        this.c = new byte[4096];
        this.d = false;
        try {
            final AudioFormat audioFormat = new AudioFormat(AudioFormat.Encoding.PCM_SIGNED, n2, 16, n3, 2 * n3, n2, false);
            final DataLine.Info info = new DataLine.Info((g.e == null) ? (g.e = class$(zkmToString("\u001d\u0013\u0002+-Y\u0001\u001b?;\u0013\\\u0007+8\u0007\u001e\u0011.{$\u001d\u000186\u00126\u0015>4;\u001b\u001a/"))) : g.e, audioFormat);
            if (!AudioSystem.isLineSupported(info)) {
                System.out.println(zkmToString(";\u001b\u001a/u\u001a\u0013\u0000)=\u001e\u001c\u0013j") + info + zkmToString("W\u001c\u001b>u\u0004\u0007\u0004::\u0005\u0006\u0011.{"));
            }
            this.a = (SourceDataLine)AudioSystem.getLine(info);
            this.b = n2 * n3 * n;
            this.a.open(audioFormat, this.b);
        }
        catch (LineUnavailableException ex) {
            System.out.println(zkmToString("=\u0013\u0002+\u0006\u0018\u0007\u001a.u1\u0013\u001d&0\u0013S"));
            this.d = true;
        }
    }
    
    public synchronized int a() {
        return this.a.available() / 2;
    }
    
    public synchronized int b() {
        return (this.b - this.a.available()) / 2;
    }
    
    public synchronized int a(final byte[] array, final int n, final int n2) {
        final int write = this.a.write(array, n, n2);
        if (write != n2) {
            System.out.println(n + "-" + n2 + zkmToString("WOT") + write);
        }
        return write;
    }
    
    public synchronized void c() {
        if (this.a != null) {
            this.a.start();
        }
    }
    
    public synchronized void d() {
        if (this.a != null) {
            this.a.stop();
            this.a.flush();
            this.a.close();
            this.a = null;
        }
    }
    
    public synchronized void e() {
        if (this.a != null) {
            this.a.stop();
        }
    }
    
    public synchronized long f() {
        if (this.a != null) {
            return this.a.getFramePosition();
        }
        return -1L;
    }
    
    public static Class class$(final String s) {
        try {
            return Class.forName(s);
        }
        catch (ClassNotFoundException ex) {
            throw new NoClassDefFoundError(ex.getMessage());
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
                    c2 = 'w';
                    break;
                }
                case 1: {
                    c2 = 'r';
                    break;
                }
                case 2: {
                    c2 = 't';
                    break;
                }
                case 3: {
                    c2 = 'J';
                    break;
                }
                default: {
                    c2 = 'U';
                    break;
                }
            }
            array[n] = (char)(c ^ c2);
        }
        return new String(charArray);
    }
}
