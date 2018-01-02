// 
// Decompiled by Procyon v0.5.30
// 

package a;

import java.awt.image.ImageProducer;
import java.awt.Image;
import java.awt.Component;
import java.util.Hashtable;

public final class dU
{
    private static Hashtable q;
    private static Hashtable w;
    
    private static Hashtable q(final boolean b) {
        if (b) {
            return dU.q;
        }
        return dU.w;
    }
    
    public static Image q(final Component component, final int n, final int n2, final String s) {
        if (n == 0 || n2 == 0) {
            System.out.println("Got 0 size: width=" + n + ", height=" + n2);
            return null;
        }
        final boolean w = a.w();
        final String string = n + ";" + n2;
        Image image;
        if ((image = q(w).get(string)) == null) {
            image = component.createImage(n, n2);
            q(w).put(string, image);
            System.out.println("Created image(" + n + "," + n2 + "), " + s + " - " + q(w).size());
            System.gc();
        }
        return image;
    }
    
    public static Image q(final Component component, final ImageProducer imageProducer, final String s) {
        final boolean w;
        Image image;
        if ((image = (Image)q(w = a.w()).get(imageProducer)) == null) {
            image = component.createImage(imageProducer);
            q(w).put(imageProducer, image);
            System.out.println("Created image(producer), " + s + " - " + q(w).size());
            System.gc();
        }
        return image;
    }
    
    static {
        dU.q = new Hashtable();
        dU.w = new Hashtable();
    }
}
