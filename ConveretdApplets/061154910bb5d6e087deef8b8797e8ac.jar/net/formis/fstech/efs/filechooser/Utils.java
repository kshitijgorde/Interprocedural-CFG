// 
// Decompiled by Procyon v0.5.30
// 

package net.formis.fstech.efs.filechooser;

import javax.imageio.stream.ImageInputStream;
import java.util.Iterator;
import java.io.IOException;
import javax.imageio.stream.FileImageInputStream;
import javax.imageio.ImageReader;
import javax.imageio.ImageIO;
import java.awt.Dimension;
import java.net.URL;
import javax.swing.ImageIcon;
import java.io.File;

public class Utils
{
    public static final String jpeg = "jpeg";
    public static final String jpg = "jpg";
    public static final String gif = "gif";
    public static final String tiff = "tiff";
    public static final String tif = "tif";
    public static final String png = "png";
    
    public static String getExtension(final File f) {
        String ext = null;
        final String s = f.getName();
        final int i = s.lastIndexOf(46);
        if (i > 0 && i < s.length() - 1) {
            ext = s.substring(i + 1).toLowerCase();
        }
        return ext;
    }
    
    protected static ImageIcon createImageIcon(final String path) {
        final URL imgURL = Utils.class.getResource(path);
        if (imgURL != null) {
            return new ImageIcon(imgURL);
        }
        System.err.println("Couldn't find file: " + path);
        return null;
    }
    
    public static Dimension getImageDim(final File img) {
        Dimension result = null;
        final String suffix = getExtension(img);
        final Iterator<ImageReader> iter = ImageIO.getImageReadersBySuffix(suffix);
        if (iter.hasNext()) {
            final ImageReader reader = iter.next();
            try {
                final ImageInputStream stream = new FileImageInputStream(img);
                reader.setInput(stream);
                final int width = reader.getWidth(reader.getMinIndex());
                final int height = reader.getHeight(reader.getMinIndex());
                result = new Dimension(width, height);
            }
            catch (IOException e) {
                e.printStackTrace();
            }
            finally {
                reader.dispose();
            }
        }
        else {
            System.out.println("No reader found for given format: " + suffix);
        }
        return result;
    }
}
