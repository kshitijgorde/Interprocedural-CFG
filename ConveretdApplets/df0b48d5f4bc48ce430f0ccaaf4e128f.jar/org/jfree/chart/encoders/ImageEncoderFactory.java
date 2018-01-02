// 
// Decompiled by Procyon v0.5.30
// 

package org.jfree.chart.encoders;

import java.util.Hashtable;

public class ImageEncoderFactory
{
    private static Hashtable encoders;
    
    private static void init() {
        (ImageEncoderFactory.encoders = new Hashtable()).put("jpeg", "org.jfree.chart.encoders.SunJPEGEncoderAdapter");
        try {
            Class.forName("javax.imageio.ImageIO");
            Class.forName("org.jfree.chart.encoders.SunPNGEncoderAdapter");
            ImageEncoderFactory.encoders.put("png", "org.jfree.chart.encoders.SunPNGEncoderAdapter");
            ImageEncoderFactory.encoders.put("jpeg", "org.jfree.chart.encoders.SunJPEGEncoderAdapter");
        }
        catch (ClassNotFoundException e) {
            ImageEncoderFactory.encoders.put("png", "org.jfree.chart.encoders.KeypointPNGEncoderAdapter");
        }
    }
    
    public static void setImageEncoder(final String format, final String imageEncoderClassName) {
        ImageEncoderFactory.encoders.put(format, imageEncoderClassName);
    }
    
    public static ImageEncoder newInstance(final String format) {
        ImageEncoder imageEncoder = null;
        final String className = ImageEncoderFactory.encoders.get(format);
        if (className == null) {
            throw new IllegalArgumentException("Unsupported image format - " + format);
        }
        try {
            final Class imageEncoderClass = Class.forName(className);
            imageEncoder = imageEncoderClass.newInstance();
        }
        catch (Exception e) {
            throw new IllegalArgumentException(e.toString());
        }
        return imageEncoder;
    }
    
    public static ImageEncoder newInstance(final String format, final float quality) {
        final ImageEncoder imageEncoder = newInstance(format);
        imageEncoder.setQuality(quality);
        return imageEncoder;
    }
    
    public static ImageEncoder newInstance(final String format, final boolean encodingAlpha) {
        final ImageEncoder imageEncoder = newInstance(format);
        imageEncoder.setEncodingAlpha(encodingAlpha);
        return imageEncoder;
    }
    
    public static ImageEncoder newInstance(final String format, final float quality, final boolean encodingAlpha) {
        final ImageEncoder imageEncoder = newInstance(format);
        imageEncoder.setQuality(quality);
        imageEncoder.setEncodingAlpha(encodingAlpha);
        return imageEncoder;
    }
    
    static {
        ImageEncoderFactory.encoders = null;
        init();
    }
}
