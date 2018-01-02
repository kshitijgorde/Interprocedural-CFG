import java.awt.MediaTracker;
import java.net.URLClassLoader;
import java.awt.Image;
import java.awt.Component;

// 
// Decompiled by Procyon v0.5.30
// 

public class instanceof
{
    private static String T = "\u1f2b\u1f1c\u1f1c\u1f01\u1f1c\u1f4e\u1f02\u1f01\u1f0f\u1f0a\u1f07\u1f00\u1f09\u1f4e\u1f07\u1f03\u1f0f\u1f09\u1f0b\u1f1d\u1f4f";
    private static String U = "\u1f2b\u1f1c\u1f1c\u1f01\u1f1c\u1f4e\u1f19\u1f06\u1f07\u1f02\u1f0b\u1f4e\u1f06\u1f0f\u1f00\u1f0a\u1f02\u1f07\u1f00\u1f09\u1f4e\u1f07\u1f03\u1f0f\u1f09\u1f0b\u1f1d\u1f54\u1f4e";
    
    public interface b(final String s, final int n, final int n2, final Component component) {
        return new interface(n, n2, this._(s, component));
    }
    
    public Image _(final String s, final Component component) {
        final Image image = component.getToolkit().createImage(((URLClassLoader)component.getClass().getClassLoader()).findResource(s));
        final MediaTracker mediaTracker = new MediaTracker(component);
        mediaTracker.addImage(image, 0);
        try {
            mediaTracker.waitForID(0);
            if (mediaTracker.isErrorAny()) {
                System.err.println(instanceof.T);
            }
        }
        catch (Exception ex) {
            System.err.println(instanceof.U.concat(String.valueOf(String.valueOf(ex.toString()))));
        }
        return image;
    }
    
    private static String c(final String s) {
        final int length = s.length();
        final char[] array = new char[length];
        for (int i = 0; i < length; ++i) {
            array[i] = (char)(s.charAt(i) ^ '\u1f6e');
        }
        return new String(array);
    }
    
    static {
        instanceof.T = c(instanceof.T);
        instanceof.U = c(instanceof.U);
    }
}
