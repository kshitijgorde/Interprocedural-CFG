// 
// Decompiled by Procyon v0.5.30
// 

package com.tn.zxspectrum;

import java.awt.Graphics;

public interface ZxSpectrumScreenConverter
{
    public static final int ZX_SCREEN_XMIN = 0;
    public static final int ZX_SCREEN_XMAX = 352;
    public static final int ZX_SCREEN_YMIN = 0;
    public static final int ZX_SCREEN_YMAX = 312;
    public static final int ZX_BITMAP_XMIN = 48;
    public static final int ZX_BITMAP_XMAX = 304;
    public static final int ZX_BITMAP_YMIN = 64;
    public static final int ZX_BITMAP_YMAX = 256;
    
    void beginFrame();
    
    void endFrame();
    
    void refresh();
    
    void setFlash(final boolean p0);
    
    void setSourceWindow(final int p0, final int p1, final int p2, final int p3);
    
    void setTargetGraphics(final Graphics p0);
    
    void setTargetWindow(final int p0, final int p1, final int p2, final int p3);
    
    void updatePixelLine(final int[] p0, final int p1, final int p2, final int p3);
}
