// 
// Decompiled by Procyon v0.5.30
// 

package pa.a.b.a;

import java.util.Enumeration;
import java.awt.Component;
import java.awt.MediaTracker;
import java.awt.Canvas;
import java.awt.image.ImageProducer;
import java.awt.image.ImageFilter;
import java.awt.image.FilteredImageSource;
import java.awt.image.CropImageFilter;
import java.util.Hashtable;
import java.net.URL;
import java.applet.AppletContext;
import java.awt.Toolkit;
import java.awt.Image;

public final class f
{
    protected static Image char;
    public static final int try = 50;
    public static final int goto = 29;
    public static final int do = 24;
    public static final int byte = 30;
    public static final int else = 870;
    private static Toolkit for;
    private static AppletContext int;
    private static URL new;
    private static Hashtable case;
    private static String if;
    private static URL a;
    
    static {
        f.for = Toolkit.getDefaultToolkit();
    }
    
    public static Image a(final String s) {
        return f.case.get(s);
    }
    
    public static Image a() {
        return f.char;
    }
    
    public static void a(final Image char1) {
        f.char = char1;
        (f.case = new Hashtable()).put("logo", f.for.createImage(new FilteredImageSource(f.char.getSource(), new CropImageFilter(174, 48, 64, 24))));
        f.case.put("zoomIn_off", f.for.createImage(new FilteredImageSource(f.char.getSource(), new CropImageFilter(0, 0, 29, 24))));
        f.case.put("zoomOut_off", f.for.createImage(new FilteredImageSource(f.char.getSource(), new CropImageFilter(29, 0, 29, 24))));
        f.case.put("pan_off", f.for.createImage(new FilteredImageSource(f.char.getSource(), new CropImageFilter(58, 0, 29, 24))));
        f.case.put("turn_off", f.for.createImage(new FilteredImageSource(f.char.getSource(), new CropImageFilter(87, 0, 29, 24))));
        f.case.put("reset_off", f.for.createImage(new FilteredImageSource(f.char.getSource(), new CropImageFilter(116, 0, 29, 24))));
        f.case.put("hotspot_off", f.for.createImage(new FilteredImageSource(f.char.getSource(), new CropImageFilter(145, 0, 29, 24))));
        f.case.put("zoomIn_on", f.for.createImage(new FilteredImageSource(f.char.getSource(), new CropImageFilter(0, 48, 29, 24))));
        f.case.put("zoomOut_on", f.for.createImage(new FilteredImageSource(f.char.getSource(), new CropImageFilter(29, 48, 29, 24))));
        f.case.put("pan_on", f.for.createImage(new FilteredImageSource(f.char.getSource(), new CropImageFilter(58, 48, 29, 24))));
        f.case.put("turn_on", f.for.createImage(new FilteredImageSource(f.char.getSource(), new CropImageFilter(87, 48, 29, 24))));
        f.case.put("reset_on", f.for.createImage(new FilteredImageSource(f.char.getSource(), new CropImageFilter(116, 48, 29, 24))));
        f.case.put("hotspot_on", f.for.createImage(new FilteredImageSource(f.char.getSource(), new CropImageFilter(145, 48, 29, 24))));
        f.case.put("zoomIn_disabled", f.for.createImage(new FilteredImageSource(f.char.getSource(), new CropImageFilter(0, 72, 29, 24))));
        f.case.put("zoomOut_disabled", f.for.createImage(new FilteredImageSource(f.char.getSource(), new CropImageFilter(29, 72, 29, 24))));
        f.case.put("pan_disabled", f.for.createImage(new FilteredImageSource(f.char.getSource(), new CropImageFilter(58, 72, 29, 24))));
        f.case.put("turn_disabled", f.for.createImage(new FilteredImageSource(f.char.getSource(), new CropImageFilter(87, 72, 29, 24))));
        f.case.put("reset_disabled", f.for.createImage(new FilteredImageSource(f.char.getSource(), new CropImageFilter(116, 72, 29, 24))));
        f.case.put("hotspot_disabled", f.for.createImage(new FilteredImageSource(f.char.getSource(), new CropImageFilter(145, 72, 29, 24))));
        f.case.put("logo_off", f.for.createImage(new FilteredImageSource(f.char.getSource(), new CropImageFilter(174, 0, 64, 24))));
        f.case.put("panel", f.for.createImage(new FilteredImageSource(f.char.getSource(), new CropImageFilter(253, 0, 10, 24))));
        try {
            final MediaTracker mediaTracker = new MediaTracker(new Canvas());
            int n = 0;
            final Enumeration<Image> elements = f.case.elements();
            while (elements.hasMoreElements()) {
                mediaTracker.addImage(elements.nextElement(), n++);
            }
            mediaTracker.waitForAll();
        }
        catch (Throwable t) {}
    }
    
    public static void a(final Image image, final Image image2) {
        f.case.put("logo", image);
        f.case.put("logo_off", image2);
    }
}
