// 
// Decompiled by Procyon v0.5.30
// 

package zp.a.a.a.c;

import java.awt.image.ImageProducer;
import java.awt.image.MemoryImageSource;
import java.awt.image.PixelGrabber;
import java.util.Hashtable;
import java.awt.Toolkit;
import java.awt.Image;

public final class e
{
    public static final int if = 250;
    public static final int case = 150;
    public static final int int = 70;
    public static final int try = 24;
    public static final int byte = 2;
    public static final int do = 140;
    public static Image else;
    public static Image new;
    private static Toolkit char;
    private static Hashtable for;
    private static boolean a;
    
    public static Image a(final String s) {
        return e.for.get(s);
    }
    
    public static void if(final Image else1) {
        e.else = else1;
    }
    
    public static void a(final Image new1) {
        e.new = new1;
    }
    
    public static void a() {
        final int[] array = new int[9360];
        try {
            new PixelGrabber(e.else.getSource(), 0, 0, 390, 24, array, 0, 390).grabPixels();
        }
        catch (Exception ex) {}
        final String[] array2 = { "logo", "iSee", "reset_on", "reset_off", "sand_watch" };
        (e.for = new Hashtable(array2.length)).put(array2[0], a(array));
        e.for.put(array2[3], a(array, 1));
        e.for.put(array2[2], a(array, 2));
    }
    
    private static Image a(final int[] array) {
        final int[] array2 = new int[6000];
        for (int i = 0; i < 24; ++i) {
            for (int j = 0; j < 250; ++j) {
                array2[i * 250 + j] = array[i * 390 + j];
            }
        }
        return e.char.createImage(new MemoryImageSource(250, 24, array2, 0, 250));
    }
    
    private static Image a(final int[] array, final int n) {
        final int[] array2 = new int[1680];
        for (int i = 0; i < 24; ++i) {
            for (int j = 0; j < 70; ++j) {
                array2[i * 70 + j] = array[i * 390 + (70 * n - 70) + 250 + j];
            }
        }
        return e.char.createImage(new MemoryImageSource(70, 24, array2, 0, 70));
    }
    
    static {
        e.new = null;
        e.char = Toolkit.getDefaultToolkit();
        e.a = false;
    }
}
