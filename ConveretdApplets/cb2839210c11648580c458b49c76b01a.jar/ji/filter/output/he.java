// 
// Decompiled by Procyon v0.5.30
// 

package ji.filter.output;

import java.io.IOException;
import javax.imageio.ImageIO;
import java.io.OutputStream;
import java.awt.image.RenderedImage;

public class he extends hd
{
    protected String a() {
        return "jpeg";
    }
    
    public String b() {
        return "jpg";
    }
    
    public boolean c() {
        return false;
    }
    
    public boolean a(final RenderedImage renderedImage, final OutputStream outputStream) throws IOException {
        return ImageIO.write(renderedImage, this.a(), outputStream);
    }
}
