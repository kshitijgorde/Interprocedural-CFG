// 
// Decompiled by Procyon v0.5.30
// 

package pa.a.a.a.d;

import java.awt.image.ImageFilter;
import java.awt.image.FilteredImageSource;
import java.awt.image.CropImageFilter;
import java.awt.image.PixelGrabber;
import java.awt.image.ImageProducer;
import java.awt.image.MemoryImageSource;
import java.util.Hashtable;
import java.awt.Toolkit;
import java.awt.Image;

public final class b
{
    public static final int int = 50;
    public static final int case = 24;
    public static final int if = 24;
    public static final int new = 18;
    public static final int byte = 432;
    public static Image char;
    public static Image a;
    private static Toolkit do;
    private static Hashtable try;
    private static boolean for;
    
    static {
        b.a = null;
        b.do = Toolkit.getDefaultToolkit();
        b.for = false;
    }
    
    private static Image a(final int[] array, final int n) {
        final int[] array2 = new int[576];
        for (int i = 0; i < 24; ++i) {
            for (int j = 0; j < 24; ++j) {
                array2[i * 24 + j] = array[i * 482 + (n - 1) * 24 + 50 + j];
            }
        }
        return b.do.createImage(new MemoryImageSource(24, 24, array2, 0, 24));
    }
    
    private static Image a(final int[] array) {
        final int[] array2 = new int[1200];
        for (int i = 0; i < 24; ++i) {
            for (int j = 0; j < 50; ++j) {
                array2[i * 50 + j] = array[i * 482 + j];
            }
        }
        return b.do.createImage(new MemoryImageSource(50, 24, array2, 0, 50));
    }
    
    public static Image a(final String s) {
        return b.try.get(s);
    }
    
    public static void if(final Image char1) {
        b.char = char1;
    }
    
    public static void a(final Image a) {
        b.a = a;
    }
    
    public static void a() {
        final int[] array = new int[11568];
        try {
            new PixelGrabber(b.char.getSource(), 0, 0, 482, 24, array, 0, 482).grabPixels();
        }
        catch (Exception ex) {}
        final String[] array2 = { "logo", "zoomIn_off", "zoomOut_off", "rotate_off", "pan_off", "reset_off", "hotspot_off", "info_off", "zoomIn_on", "zoomOut_on", "rotate_on", "pan_on", "reset_on", "hotspot_on", "info_on", "max_zoom", "sand_watch", "hotspot_gray", "fill" };
        (b.try = new Hashtable(array2.length)).put(array2[0], a(array));
        for (int i = 1; i < array2.length; ++i) {
            b.try.put(array2[i], a(array, i));
        }
        if (b.a != null) {
            b.try.put("mag_img_top", b.do.createImage(new FilteredImageSource(b.a.getSource(), new CropImageFilter(0, 0, 140, 6))));
            b.try.put("mag_img_left", b.do.createImage(new FilteredImageSource(b.a.getSource(), new CropImageFilter(0, 0, 6, 140))));
            b.try.put("mag_img_bottom", b.do.createImage(new FilteredImageSource(b.a.getSource(), new CropImageFilter(0, 134, 140, 6))));
            b.try.put("mag_img_right", b.do.createImage(new FilteredImageSource(b.a.getSource(), new CropImageFilter(134, 0, 6, 140))));
        }
    }
}
