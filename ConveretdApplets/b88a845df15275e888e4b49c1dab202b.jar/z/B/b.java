// 
// Decompiled by Procyon v0.5.30
// 

package z.B;

import java.awt.image.RenderedImage;
import java.awt.image.Raster;
import java.io.IOException;
import java.io.InputStream;

public abstract class b implements t
{
    protected X A;
    protected A B;
    
    public b(final X a, final A b) {
        this.A = a;
        this.B = b;
    }
    
    public b(final InputStream inputStream, final A b) {
        this.A = new n(inputStream);
        this.B = b;
    }
    
    public A E() {
        return this.B;
    }
    
    public void A(final A b) {
        this.B = b;
    }
    
    public X C() {
        return this.A;
    }
    
    public int B() throws IOException {
        return 1;
    }
    
    public Raster A() throws IOException {
        return this.A(0);
    }
    
    public Raster A(final int n) throws IOException {
        return this.B(n).getData();
    }
    
    public RenderedImage D() throws IOException {
        return this.B(0);
    }
    
    public abstract RenderedImage B(final int p0) throws IOException;
}
