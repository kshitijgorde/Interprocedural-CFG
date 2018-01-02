// 
// Decompiled by Procyon v0.5.30
// 

package z.B;

import java.awt.image.RenderedImage;
import java.io.IOException;
import java.awt.image.ColorModel;
import java.awt.image.Raster;
import java.io.OutputStream;

public interface _
{
    M A();
    
    void A(final M p0);
    
    OutputStream B();
    
    void A(final Raster p0, final ColorModel p1) throws IOException;
    
    void A(final RenderedImage p0) throws IOException;
}
