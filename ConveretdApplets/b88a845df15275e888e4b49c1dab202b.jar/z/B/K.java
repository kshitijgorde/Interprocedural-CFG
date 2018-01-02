// 
// Decompiled by Procyon v0.5.30
// 

package z.B;

import java.io.IOException;
import java.io.FileInputStream;
import java.io.File;
import java.io.InputStream;
import java.io.OutputStream;
import java.awt.image.RenderedImage;

public final class K extends J
{
    static /* synthetic */ Class class$z$B$P;
    static /* synthetic */ Class class$java$lang$Object;
    
    public String D() {
        return "bmp";
    }
    
    public Class B() {
        return (K.class$z$B$P == null) ? (K.class$z$B$P = class$("z.B.P")) : K.class$z$B$P;
    }
    
    public Class E() {
        return (K.class$java$lang$Object == null) ? (K.class$java$lang$Object = class$("java.lang.Object")) : K.class$java$lang$Object;
    }
    
    public boolean B(final RenderedImage renderedImage, final M m) {
        final int transferType = renderedImage.getSampleModel().getTransferType();
        if (transferType == 1 || transferType == 2 || transferType == 3 || transferType == 4 || transferType == 5) {
            return false;
        }
        if (m != null) {
            if (!(m instanceof P)) {
                return false;
            }
            final int d = ((P)m).D();
            if (d == 0 || d == 2) {
                return false;
            }
        }
        return true;
    }
    
    protected _ A(final OutputStream outputStream, final M m) {
        M i = null;
        if (m != null) {
            i = m;
        }
        return new G(outputStream, i);
    }
    
    protected t A(final InputStream inputStream, final A a) {
        return new Y(inputStream, null);
    }
    
    protected t A(final File file, final A a) throws IOException {
        return new Y(new FileInputStream(file), null);
    }
    
    protected t A(final X x, final A a) {
        return new Y((InputStream)x, null);
    }
    
    public int A() {
        return 2;
    }
    
    public boolean A(final byte[] array) {
        return array[0] == 66 && array[1] == 77;
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
