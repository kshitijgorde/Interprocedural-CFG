import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.Line;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.SourceDataLine;

// 
// Decompiled by Procyon v0.5.30
// 

public class f
{
    public SourceDataLine a;
    public int b;
    public static Class c;
    
    public f(final boolean b, final int n) {
        this.a = null;
        try {
            final AudioFormat audioFormat = new AudioFormat(AudioFormat.Encoding.PCM_SIGNED, 8000.0f, 16, 1, 2, 8000.0f, false);
            final DataLine.Info info = new DataLine.Info((f.c == null) ? (f.c = class$(zkmToString("\u0005N5JgA\\,^q\u000b\u00010Jr\u001fC&O1<@6Y|\nk\"_~#F-N"))) : f.c, audioFormat);
            while (!AudioSystem.isLineSupported(info)) {
                System.out.println(zkmToString("#F-N?\u0002N7Hw\u0006A$\u000b") + info + zkmToString("OA,_?\u001cZ3[p\u001d[&O1"));
                Thread.currentThread();
                Thread.sleep(250L);
            }
            this.a = (SourceDataLine)AudioSystem.getLine(info);
            this.b = 4000;
            this.a.open(audioFormat, this.b);
        }
        catch (InterruptedException ex) {}
        catch (LineUnavailableException ex2) {
            System.out.println(zkmToString("%N5JL\u0000Z-O?)N*Gz\u000b\u000e"));
        }
    }
    
    public int a() {
        return this.a.available() / 2;
    }
    
    public int b() {
        return (this.b - this.a.available()) / 2;
    }
    
    public int a(final byte[] array, final int n, final int n2) {
        final int write = this.a.write(array, n, n2);
        if (write != n2) {
            System.out.println(n + "-" + n2 + zkmToString("O\u0012c") + write);
        }
        return write;
    }
    
    public void c() {
        if (this.a != null) {
            this.a.start();
        }
    }
    
    public void d() {
        if (this.a != null) {
            this.a.flush();
            this.a.stop();
            this.a.close();
            this.a = null;
        }
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
                    c2 = 'o';
                    break;
                }
                case 1: {
                    c2 = '/';
                    break;
                }
                case 2: {
                    c2 = 'C';
                    break;
                }
                case 3: {
                    c2 = '+';
                    break;
                }
                default: {
                    c2 = '\u001f';
                    break;
                }
            }
            array[n] = (char)(c ^ c2);
        }
        return new String(charArray);
    }
}
