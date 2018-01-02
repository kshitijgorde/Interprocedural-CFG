// 
// Decompiled by Procyon v0.5.30
// 

package z.B;

import java.io.IOException;
import java.awt.image.RenderedImage;
import java.io.InputStream;

public class Y extends b
{
    public Y(final InputStream inputStream, final A a) {
        super(inputStream, a);
    }
    
    public RenderedImage B(final int n) throws IOException {
        if (n != 0) {
            throw new IOException(m.A("BMPImageDecoder8"));
        }
        return new Z(this.A);
    }
}
