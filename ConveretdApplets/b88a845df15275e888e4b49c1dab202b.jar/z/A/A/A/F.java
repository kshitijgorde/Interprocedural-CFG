// 
// Decompiled by Procyon v0.5.30
// 

package z.A.A.A;

import java.util.Iterator;
import com.sun.image.codec.jpeg.JPEGImageDecoder;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.io.InputStream;
import com.sun.image.codec.jpeg.JPEGCodec;
import java.io.FileInputStream;
import z.A.A.B.A.A;
import z.A.A.A.B.X;
import z.A.A.B.A.B;
import z.A.A.B.A.D;
import java.io.File;

public class F
{
    public F(final String s) {
        final File file = new File(s);
        try {
            this.A(1, D.A(file));
        }
        catch (B b) {
            System.err.println("error 1a");
        }
        try {
            final E e = new E();
            new X(file).A(e);
            new z.A.A.A.C.D(file).A(e);
            this.A(2, e);
        }
        catch (B b2) {
            System.err.println("error 2a");
        }
        try {
            final A a = new A(file);
            final byte[] a2 = a.A((byte)(-31));
            final byte[] a3 = a.A((byte)(-19));
            final E e2 = new E();
            new X(a2).A(e2);
            new z.A.A.A.C.D(a3).A(e2);
            this.A(3, e2);
        }
        catch (B b3) {
            System.err.println("error 3a");
        }
        try {
            final JPEGImageDecoder jpegDecoder = JPEGCodec.createJPEGDecoder((InputStream)new FileInputStream(file));
            jpegDecoder.decodeAsBufferedImage();
            this.A(4, D.A(jpegDecoder.getJPEGDecodeParam()));
        }
        catch (FileNotFoundException ex) {
            System.err.println("error 4a");
        }
        catch (IOException ex2) {
            System.err.println("error 4b");
        }
    }
    
    private void A(final int n, final E e) {
        System.out.println();
        System.out.println("*** APPROACH " + n + " ***");
        System.out.println();
        final Iterator a = e.A();
        while (a.hasNext()) {
            final z.A.A.A.A a2 = a.next();
            final Iterator e2 = a2.E();
            while (e2.hasNext()) {
                System.out.println(e2.next());
            }
            if (a2.C()) {
                final Iterator b = a2.B();
                while (b.hasNext()) {
                    System.out.println("ERROR: " + b.next());
                }
            }
        }
    }
    
    public static void A(final String[] array) {
        new F("src/com/drew/metadata/test/withIptcExifGps.jpg");
    }
}
