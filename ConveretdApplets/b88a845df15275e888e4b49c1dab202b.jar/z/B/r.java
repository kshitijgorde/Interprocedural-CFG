// 
// Decompiled by Procyon v0.5.30
// 

package z.B;

import java.io.IOException;
import java.awt.image.RenderedImage;
import java.awt.image.ColorModel;
import java.awt.image.Raster;
import java.io.OutputStream;

public abstract class r implements _
{
    protected OutputStream A;
    protected M B;
    
    public r(final OutputStream a, final M b) {
        this.A = a;
        this.B = b;
    }
    
    public M A() {
        return this.B;
    }
    
    public void A(final M b) {
        this.B = b;
    }
    
    public OutputStream B() {
        return this.A;
    }
    
    public void A(final Raster raster, final ColorModel colorModel) throws IOException {
        this.A(new L(raster, colorModel));
    }
    
    public abstract void A(final RenderedImage p0) throws IOException;
}
