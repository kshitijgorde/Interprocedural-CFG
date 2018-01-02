// 
// Decompiled by Procyon v0.5.30
// 

package pa.a.b.a;

import java.awt.image.ImageProducer;
import java.awt.image.PixelGrabber;
import java.awt.Image;

public final class c
{
    public static final boolean for;
    public static final boolean a;
    public static final boolean int;
    public static final boolean if;
    public static final boolean do;
    
    static {
        for = System.getProperty("java.version").startsWith("1.1.5");
        a = System.getProperty("java.version").startsWith("1.0");
        int = System.getProperty("os.name").startsWith("Mac");
        if = System.getProperty("java.vendor").startsWith("Microsoft");
        do = (c.if && !c.a);
    }
    
    public static void a(final Image image, final int n, final int n2, final int n3, final int n4, final int[] array, final int n5, final int n6) throws InterruptedException {
        new PixelGrabber(image, n, n2, n3, n4, array, n5, n6).grabPixels();
    }
    
    public static void a(final ImageProducer imageProducer, final int n, final int n2, final int n3, final int n4, final int[] array, final int n5, final int n6) throws InterruptedException {
        new PixelGrabber(imageProducer, n, n2, n3, n4, array, n5, n6).grabPixels();
    }
}
