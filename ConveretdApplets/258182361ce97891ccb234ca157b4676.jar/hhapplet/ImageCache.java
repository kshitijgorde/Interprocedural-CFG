// 
// Decompiled by Procyon v0.5.30
// 

package hhapplet;

import java.awt.Toolkit;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.awt.Image;
import java.applet.Applet;
import java.util.Hashtable;

public class ImageCache
{
    private Hashtable m_Imagehash;
    private Applet m_applet;
    private static ImageCache m_imageCache;
    
    public static void createInstance(final Applet applet) {
        ImageCache.m_imageCache = new ImageCache(applet);
    }
    
    public ImageCache(final Applet applet) {
        this.m_Imagehash = null;
        this.m_applet = null;
        this.m_applet = applet;
        this.m_Imagehash = new Hashtable();
    }
    
    public String GetImagePrefixName(final int n) {
        switch (n) {
            case 0: {
                return "topimage";
            }
            case 1: {
                return "topimageopen";
            }
            default: {
                return "";
            }
        }
    }
    
    public synchronized Image GetImage(String replace) {
        URL url = null;
        boolean b = true;
        if (replace == null) {
            return null;
        }
        try {
            if (replace.toLowerCase().startsWith("zip:")) {
                return this.m_Imagehash.get(replace.substring(4, replace.length()));
            }
            replace = replace.replace('|', ':');
            url = new URL(replace);
        }
        catch (MalformedURLException ex) {
            b = false;
        }
        if (!b && this.m_applet != null) {
            try {
                url = new URL(this.m_applet.getDocumentBase(), replace);
            }
            catch (MalformedURLException ex2) {}
        }
        if (url == null) {
            return null;
        }
        final Object value = this.m_Imagehash.get(url);
        if (value != null && value instanceof Image) {
            return (Image)value;
        }
        if (this.m_applet != null) {
            Image image;
            try {
                image = this.m_applet.getImage(url);
            }
            catch (Exception ex3) {
                return null;
            }
            if (image != null) {
                this.m_Imagehash.put(url, image);
            }
            return image;
        }
        return null;
    }
    
    static {
        ImageCache.m_imageCache = null;
    }
    
    public Image GetGif(final String s) {
        return this.GetImage(s + ".gif");
    }
    
    public Image GetGif(final int n) {
        return this.GetGif(n, 0);
    }
    
    public Image GetGif(final int n, final int n2) {
        return this.GetGif(this.GetImagePrefixName(n2) + String.valueOf(n));
    }
    
    public void putImage(final String s, final long n, final Object o) {
        if (o instanceof InputStream) {
            final InputStream inputStream = (InputStream)o;
            if (this.m_Imagehash.get(s) == null) {
                try {
                    if (n < 65535L) {
                        final int n2 = (int)n;
                        final byte[] array = new byte[n2];
                        int i;
                        int read;
                        for (i = inputStream.read(array); i < n2; i += read) {
                            final byte[] array2 = new byte[n2 - i];
                            read = inputStream.read(array2);
                            if (read == 0) {
                                break;
                            }
                            for (int n3 = 0; n3 < read && n3 < n2 - i; ++n3) {
                                array[i + n3] = array2[n3];
                            }
                        }
                        if (n2 == i) {
                            this.m_Imagehash.put(s, Toolkit.getDefaultToolkit().createImage(array));
                        }
                    }
                }
                catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        }
    }
    
    public static ImageCache getInstance() {
        return ImageCache.m_imageCache;
    }
}
