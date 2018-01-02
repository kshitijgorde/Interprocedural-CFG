// 
// Decompiled by Procyon v0.5.30
// 

package z.A.A.B.A;

import java.io.IOException;
import java.util.Iterator;
import z.A.A.A.B.g;
import z.A.A.A.C;
import z.A.A.A.G;
import com.sun.image.codec.jpeg.JPEGDecodeParam;
import z.A.A.A.B.X;
import java.io.File;
import z.A.A.A.E;
import java.io.InputStream;

public class D
{
    static /* synthetic */ Class class$z$A$A$A$B$g;
    
    public static E A(final InputStream inputStream) throws B {
        return A(new A(inputStream));
    }
    
    public static E A(final File file) throws B {
        return A(new A(file));
    }
    
    public static E A(final A a) {
        final E e = new E();
        try {
            new X(a.A((byte)(-31))).A(e);
        }
        catch (B b) {}
        try {
            new z.A.A.A.C.D(a.A((byte)(-19))).A(e);
        }
        catch (B b2) {}
        try {
            new z.A.A.A.A.B(a.A((byte)(-64))).A(e);
        }
        catch (B b3) {}
        try {
            new z.A.A.A.A.A(a.A((byte)(-2))).A(e);
        }
        catch (B b4) {}
        return e;
    }
    
    public static E A(final JPEGDecodeParam jpegDecodeParam) {
        final E e = new E();
        final byte[][] markerData = jpegDecodeParam.getMarkerData(225);
        if (markerData != null && markerData[0].length > 0) {
            new X(markerData[0]).A(e);
        }
        final byte[][] markerData2 = jpegDecodeParam.getMarkerData(237);
        if (markerData2 != null && markerData2[0].length > 0) {
            new z.A.A.A.C.D(markerData2[0]).A(e);
        }
        final byte[][] markerData3 = jpegDecodeParam.getMarkerData(254);
        if (markerData3 != null && markerData3[0].length > 0) {
            new z.A.A.A.A.A(markerData3[0]).A(e);
        }
        return e;
    }
    
    public static void A(final String[] array) throws C, IOException {
        E a = null;
        try {
            a = A(new File(array[0]));
        }
        catch (Exception ex) {
            ex.printStackTrace(System.err);
            System.exit(1);
        }
        final Iterator a2 = a.A();
        while (a2.hasNext()) {
            final z.A.A.A.A a3 = a2.next();
            final Iterator e = a3.E();
            while (e.hasNext()) {
                final G g = e.next();
                try {
                    System.out.println("[" + a3.F() + "] " + g.A() + " = " + g.D());
                }
                catch (C c) {
                    System.err.println(c.getMessage());
                    System.err.println(g.C() + " " + g.A() + " (error)");
                }
            }
            if (a3.C()) {
                final Iterator b = a3.B();
                while (b.hasNext()) {
                    System.out.println("ERROR: " + b.next());
                }
            }
        }
        if (array.length > 1 && array[1].trim().equals("/thumb")) {
            final g g2 = (g)a.B((D.class$z$A$A$A$B$g == null) ? (D.class$z$A$A$A$B$g = class$("z.A.A.A.B.g")) : D.class$z$A$A$A$B$g);
            if (g2.J()) {
                System.out.println("Writing thumbnail...");
                g2.B(array[0].trim() + ".thumb.jpg");
            }
            else {
                System.out.println("No thumbnail data exists in this image");
            }
        }
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
