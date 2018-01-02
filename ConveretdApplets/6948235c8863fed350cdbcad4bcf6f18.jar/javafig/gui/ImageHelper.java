// 
// Decompiled by Procyon v0.5.30
// 

package javafig.gui;

import java.awt.image.FilteredImageSource;
import java.awt.image.ImageFilter;
import java.awt.Toolkit;
import java.awt.image.ImageProducer;
import javafig.utils.Pathname;
import java.awt.MediaTracker;
import java.awt.Image;
import java.awt.Frame;
import javafig.utils.ExceptionTracer;
import java.net.URL;
import java.awt.Component;

public class ImageHelper
{
    static Component visibleParent;
    static ConsoleMessage printer;
    static String appletServerName;
    static String appletBaseDirName;
    static URL appletCodeBase;
    static boolean debug;
    private static /* synthetic */ Class class$Ljavafig$gui$ImageHelper;
    
    public static void setConsole(final ConsoleMessage printer) {
        ImageHelper.printer = printer;
    }
    
    public static void message(final String s) {
        if (ImageHelper.printer != null) {
            ImageHelper.printer.consoleMessage(s);
        }
        else {
            System.out.println(s);
        }
    }
    
    public static void dbg(final String s) {
        if (ImageHelper.debug) {
            message(s);
        }
    }
    
    public static void trace(final Throwable t) {
        ExceptionTracer.trace(t);
    }
    
    public static void setVisibleParent(final Component visibleParent) {
        if (visibleParent.isShowing()) {
            ImageHelper.visibleParent = visibleParent;
        }
        else {
            message("-W- Don't call setVisibleParent() without a " + "visible AWT component! (ignored)");
        }
    }
    
    public static void checkParentIsVisible() {
        if (ImageHelper.visibleParent == null) {
            final Frame visibleParent = new Frame("AWT dummy frame for image creation");
            visibleParent.pack();
            ImageHelper.visibleParent = visibleParent;
        }
    }
    
    public static void setAppletServerName(final String appletServerName) {
        ImageHelper.appletServerName = appletServerName;
    }
    
    public static void setAppletBaseDir(final String appletBaseDirName) {
        ImageHelper.appletBaseDirName = appletBaseDirName;
    }
    
    public static Image loadResourceImage(String removeDoubleDots) {
        dbg("-I- loadResourceImage from: " + removeDoubleDots);
        checkParentIsVisible();
        Image image = null;
        final MediaTracker mediaTracker = new MediaTracker(ImageHelper.visibleParent);
        try {
            URL resource;
            if (ImageHelper.appletServerName == null) {
                resource = ((ImageHelper.class$Ljavafig$gui$ImageHelper != null) ? ImageHelper.class$Ljavafig$gui$ImageHelper : (ImageHelper.class$Ljavafig$gui$ImageHelper = class$("javafig.gui.ImageHelper"))).getResource(Pathname.removeDoubleDots(removeDoubleDots));
            }
            else {
                removeDoubleDots = Pathname.removeDoubleDots(ImageHelper.appletServerName + ImageHelper.appletBaseDirName + removeDoubleDots);
                dbg("-#- ImageHelper.loadResourceImage.applet: " + removeDoubleDots);
                resource = new URL(removeDoubleDots);
            }
            image = ImageHelper.visibleParent.getToolkit().getImage(resource);
            mediaTracker.addImage(image, 0);
            mediaTracker.waitForAll();
        }
        catch (Throwable t) {
            message("-E- loadResourceImage failed: " + t);
            message("-E- Could not find/access the resource: " + removeDoubleDots);
            trace(t);
        }
        if (mediaTracker.isErrorAny()) {
            message("-E- loadResourceImage failed: MediaTracker error" + mediaTracker.statusAll(false));
            message("-E- Could not find/acces the resource: " + removeDoubleDots);
            return null;
        }
        dbg("-I- got resource image: " + image);
        return image;
    }
    
    public static Image createImage(final int n, final int n2) {
        checkParentIsVisible();
        final Image image = ImageHelper.visibleParent.createImage(n, n2);
        if (image == null) {
            message("-E- failed to create image, due to AWT problems.");
        }
        return image;
    }
    
    public static Image createImage(final ImageProducer imageProducer) {
        checkParentIsVisible();
        return ImageHelper.visibleParent.createImage(imageProducer);
    }
    
    public static Image findImage(final String s, final String s2, final String s3) {
        dbg("-#- findImage() started: " + s + " " + s2 + " " + s3);
        Image image;
        if (s.equals("FILE")) {
            image = findImageFromFile(s2, s3);
        }
        else if (s.equals("URL")) {
            image = findImageFromURL(s2, s3);
        }
        else if (s.equals("RESOURCE")) {
            image = findImageFromResource(s2, s3);
        }
        else {
            image = findImageFromFile(s2, s3);
            if (image == null) {
                image = findImageFromResource(s2, s3);
            }
            if (image == null) {
                image = findImageFromURL(s2, s3);
            }
            if (image == null) {
                message("-E- Couldn't find image: " + s2 + " " + s3);
            }
        }
        return image;
    }
    
    public static Image findImageFromURL(final String s, final String s2) {
        String substring = new String(s);
        URL url = null;
        Image image = null;
        try {
            if (s2.indexOf("://") > 0) {
                image = findImageFromURL(s2);
            }
            else if (s2.startsWith("file:")) {
                image = findImageFromURL(s2);
            }
            else {
                if (substring.lastIndexOf("/") > 0) {
                    substring = substring.substring(0, substring.lastIndexOf("/"));
                }
                url = new URL(substring + "/" + s2);
                image = Toolkit.getDefaultToolkit().getImage(url);
            }
        }
        catch (Throwable t) {
            message("-E- findImageFromURL: " + t);
            message("-I- basename=" + substring + " imgname=" + s2);
            message("-I- imageURL=" + url);
            trace(t);
        }
        return image;
    }
    
    public static Image findImageFromURL(final String s) {
        URL url = null;
        Image image = null;
        try {
            url = new URL(s);
            image = Toolkit.getDefaultToolkit().getImage(url);
        }
        catch (Throwable t) {
            message("-W- invalid image URL: " + s);
            message("-I- imageURL=" + url);
            trace(t);
        }
        return image;
    }
    
    public static Image findImageFromFile(final String s, final String s2) {
        String s3 = new String(s);
        String s4 = null;
        Image image = null;
        try {
            if (s2.startsWith("/")) {
                s4 = s2;
            }
            else if (s2.indexOf(":\\") == 1) {
                s4 = s2;
            }
            else if (s3.lastIndexOf("/") > 0) {
                s3 = s3.substring(0, s3.lastIndexOf("/"));
                s4 = s3 + "/" + s2;
            }
            else if (s3.lastIndexOf("\\") > 0) {
                s3 = s3.substring(0, s3.lastIndexOf("\\"));
                s4 = s3 + "\\" + s2;
            }
            else {
                s4 = s2;
            }
            image = Toolkit.getDefaultToolkit().getImage(s4);
            if (image == null) {
                message("-W- ImageHelper: could not find image file: " + s4);
            }
        }
        catch (Throwable t) {
            message("-E- findImageFromFile: " + t);
            message("-I- basename=" + s3 + " imgname=" + s2);
            message("-I- image pathname=" + s4);
            trace(t);
        }
        return image;
    }
    
    public static Image findImageFromResource(final String s, final String s2) {
        String substring = new String(s);
        Image image = null;
        URL resource = null;
        try {
            if (substring.lastIndexOf("/") > 0) {
                substring = substring.substring(0, substring.lastIndexOf("/"));
            }
            resource = ((ImageHelper.class$Ljavafig$gui$ImageHelper != null) ? ImageHelper.class$Ljavafig$gui$ImageHelper : (ImageHelper.class$Ljavafig$gui$ImageHelper = class$("javafig.gui.ImageHelper"))).getResource(Pathname.removeDoubleDots(substring + "/" + s2));
            image = ImageHelper.visibleParent.getToolkit().getImage(resource);
        }
        catch (Throwable t) {
            message("-E- findImageFromResource: " + t);
            message("-I- basename=" + substring + " imgname=" + s2);
            message("-I- imageURL=" + resource);
            trace(t);
        }
        return image;
    }
    
    public static Image getFilteredImage(final Image image, final ImageFilter imageFilter) {
        dbg("-I- getFilteredImage...");
        return ImageHelper.visibleParent.createImage(new FilteredImageSource(image.getSource(), imageFilter));
    }
    
    public static Image getInvertedImage(final Image image) {
        dbg("-I- getInvertedImage...");
        return ImageHelper.visibleParent.createImage(new FilteredImageSource(image.getSource(), new InvertGrayFilter()));
    }
    
    public static void main(final String[] array) {
        message("-I- ImageHelper selftest...");
        loadResourceImage("/javafig/images/icon.gif");
        createImage(200, 300);
        message("-I- selftest ok (Use CNTL-C to kill the dummy frame)");
    }
    
    static Class class$(final String s) {
        try {
            return Class.forName(s);
        }
        catch (ClassNotFoundException ex) {
            throw new NoClassDefFoundError(ex.getMessage());
        }
    }
    
    static {
        ImageHelper.visibleParent = null;
        ImageHelper.printer = null;
        ImageHelper.appletServerName = null;
        ImageHelper.appletBaseDirName = null;
        ImageHelper.appletCodeBase = null;
        ImageHelper.debug = false;
    }
}
