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
    
    public g(final boolean b, final int n) {
        this.a = null;
        this.c = new byte[4096];
        this.d = false;
        try {
            final AudioFormat audioFormat = new AudioFormat(AudioFormat.Encoding.PCM_SIGNED, 8000.0f, 16, 1, 2, 8000.0f, false);
            final DataLine.Info info = new DataLine.Info((g.e == null) ? (g.e = class$(zkmToString("^\u0011[u_\u001a\u0003BaIP^^uJD\u001cHp\tg\u001fXfDQ4L`Fx\u0019Cq"))) : g.e, audioFormat);
            if (!AudioSystem.isLineSupported(info)) {
                System.out.println(zkmToString("x\u0019Cq\u0007Y\u0011YwO]\u001eJ4") + info + zkmToString("\u0014\u001eB`\u0007G\u0005]dHF\u0004Hp\t"));
            }
            this.a = (SourceDataLine)AudioSystem.getLine(info);
            this.b = 8000 * n;
            this.a.open(audioFormat, this.b);
        }
        catch (LineUnavailableException ex) {
            System.out.println(zkmToString("~\u0011[ut[\u0005Cp\u0007r\u0011DxBPQ"));
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
            System.out.println(n + "-" + n2 + zkmToString("\u0014M\r") + write);
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
                    c2 = '4';
                    break;
                }
                case 1: {
                    c2 = 'p';
                    break;
                }
                case 2: {
                    c2 = '-';
                    break;
                }
                case 3: {
                    c2 = '\u0014';
                    break;
                }
                default: {
                    c2 = '\'';
                    break;
                }
            }
            array[n] = (char)(c ^ c2);
        }
        return new String(charArray);
    }
}
