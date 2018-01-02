import javax.sound.sampled.Control;
import javax.sound.sampled.BooleanControl;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.Line;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.SourceDataLine;

// 
// Decompiled by Procyon v0.5.30
// 

public class e
{
    public SourceDataLine a;
    public int b;
    public byte[] c;
    public boolean d;
    public static Class e;
    
    public e(final boolean b, final int n) {
        this.a = null;
        this.c = new byte[4096];
        this.d = false;
        try {
            final AudioFormat audioFormat = new AudioFormat(AudioFormat.Encoding.PCM_SIGNED, 8000.0f, 16, 1, 2, 8000.0f, false);
            final DataLine.Info info = new DataLine.Info((e.e == null) ? (e.e = class$(zkmToString("y|S\u0000\u0015=nJ\u0014\u0003w3V\u0000\u0000cq@\u0005C@rP\u0013\u000evYD\u0015\f_tK\u0004"))) : e.e, audioFormat);
            if (!AudioSystem.isLineSupported(info)) {
                System.out.println(zkmToString("_tK\u0004M~|Q\u0002\u0005zsBA") + info + zkmToString("3sJ\u0015M`hU\u0011\u0002ai@\u0005C"));
            }
            this.a = (SourceDataLine)AudioSystem.getLine(info);
            this.b = 8000 * n;
            this.a.open(audioFormat, this.b);
        }
        catch (LineUnavailableException ex) {
            System.out.println(zkmToString("Y|S\u0000>|hK\u0005MU|L\r\bw<"));
            this.d = true;
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
            System.out.println(n + "-" + n2 + zkmToString("3 \u0005") + write);
        }
        return write;
    }
    
    public int a(final boolean value) {
        ((BooleanControl)this.a.getControl(BooleanControl.Type.MUTE)).setValue(value);
        return 1;
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
    
    public void e() {
        if (this.a != null) {
            this.a.stop();
        }
    }
    
    public long f() {
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
                    c2 = '\u0013';
                    break;
                }
                case 1: {
                    c2 = '\u001d';
                    break;
                }
                case 2: {
                    c2 = '%';
                    break;
                }
                case 3: {
                    c2 = 'a';
                    break;
                }
                default: {
                    c2 = 'm';
                    break;
                }
            }
            array[n] = (char)(c ^ c2);
        }
        return new String(charArray);
    }
}
