// 
// Decompiled by Procyon v0.5.30
// 

package z.B;

import java.io.OutputStream;
import java.awt.image.RenderedImage;

public final class V extends J
{
    static /* synthetic */ Class class$z$B$c;
    static /* synthetic */ Class class$z$B$I;
    
    public String D() {
        return "tiff";
    }
    
    public Class B() {
        return (V.class$z$B$c == null) ? (V.class$z$B$c = class$("z.B.c")) : V.class$z$B$c;
    }
    
    public Class E() {
        return (V.class$z$B$I == null) ? (V.class$z$B$I = class$("z.B.I")) : V.class$z$B$I;
    }
    
    public boolean B(final RenderedImage renderedImage, final M m) {
        return true;
    }
    
    protected _ A(final OutputStream outputStream, final M m) {
        return new B(outputStream, m);
    }
    
    protected t A(final X x, final A a) {
        return new S(x, a);
    }
    
    public int A() {
        return 4;
    }
    
    public boolean A(final byte[] array) {
        return (array[0] == 73 && array[1] == 73 && array[2] == 42 && array[3] == 0) || (array[0] == 77 && array[1] == 77 && array[2] == 0 && array[3] == 42);
    }
    
    static /* synthetic */ Class class$(final String s) {
        try {
            return Class.forName(s);
        }
        catch (ClassNotFoundException ex) {
            throw new NoClassDefFoundError().initCause(ex);
        }
    }
}
