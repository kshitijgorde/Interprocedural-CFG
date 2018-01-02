// 
// Decompiled by Procyon v0.5.30
// 

package jmaster.jumploader.model.api.B;

import jmaster.jumploader.model.impl.image.WatermarkConfig;
import java.awt.image.BufferedImage;
import java.awt.Dimension;
import java.io.IOException;
import java.awt.Image;
import java.io.File;

public interface A
{
    public static final int M = 0;
    public static final int C = 1000;
    public static final String L = "fast";
    public static final String I = "smooth";
    public static final String K = "bilinear";
    public static final String D = "bmp";
    public static final String B = "gif";
    public static final String G = "png";
    public static final String F = "jpg";
    public static final String H = "jpe";
    public static final String J = "jpeg";
    public static final String A = "tif";
    public static final String E = "tiff";
    
    boolean B(final File p0);
    
    Image A(final File p0, final int p1, final int p2, final String p3) throws IOException;
    
    Image C(final File p0) throws IOException;
    
    Image A(final File p0, final Integer p1) throws IOException;
    
    Image A(final File p0, final Dimension p1) throws IOException;
    
    Image A(final File p0, final Integer p1, final Dimension p2) throws IOException;
    
    Image F(final Image p0);
    
    Image A(final Image p0);
    
    Image C(final Image p0);
    
    Image G(final Image p0);
    
    Image E(final Image p0);
    
    BufferedImage D(final Image p0);
    
    BufferedImage B(final Image p0);
    
    Image C(final Image p0, final int p1, final int p2, final String p3, final boolean p4);
    
    Image A(final Image p0, final double p1, final String p2, final boolean p3);
    
    Image A(final Image p0, final int p1, final int p2, final String p3, final boolean p4);
    
    Image A(final Image p0, final int p1, final int p2, final String p3);
    
    Image B(final Image p0, final int p1, final int p2, final String p3, final boolean p4);
    
    File A(final Image p0, final File p1, final int p2) throws IOException;
    
    void A(final BufferedImage p0, final WatermarkConfig p1);
    
    Dimension A(final File p0);
}
