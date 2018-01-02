import java.awt.Button;
import java.awt.Component;
import java.awt.MediaTracker;
import java.awt.Label;
import java.awt.image.ImageObserver;
import java.io.IOException;
import java.awt.Color;
import java.awt.image.ImageProducer;
import java.awt.image.MemoryImageSource;
import java.awt.Toolkit;
import java.awt.AWTException;
import java.io.InputStream;
import java.awt.Image;

// 
// Decompiled by Procyon v0.5.30
// 

public class C40 extends C28
{
    int e;
    int h;
    int i;
    int k;
    Image l;
    public static String m;
    int n;
    
    public void finalize() {
        if (this.l != null) {
            this.l.flush();
        }
    }
    
    public C40(final int n, final int n2, final int n3, final int n4, final int n5, final int n6, final int n7, final C38 c38, final int n8, final InputStream inputStream, final int n9) throws IOException {
        this(n3, n4, n5, n6, n7);
        if (n9 != 0 && n9 != 3 && n9 != 2) {
            System.out.println("Warning 45629 " + n9);
            inputStream.read(new byte[n8]);
            return;
        }
        int[] array;
        if (n9 == 0) {
            array = new int[n8 * 8];
        }
        else {
            array = new int[n8];
        }
        for (int i = 0; i < array.length; ++i) {
            final int read = inputStream.read();
            if (n9 == 0) {
                try {
                    int n10 = 128;
                    for (int j = 0; j < 8; ++j) {
                        Color color;
                        if ((read & n10) > 0) {
                            color = c38.b(1);
                        }
                        else {
                            color = c38.b(0);
                        }
                        array[i * 8 + j] = color.getRGB();
                        n10 >>= 1;
                    }
                }
                catch (AWTException ex) {
                    System.out.println("ERROR 30023 " + ex);
                }
            }
            else {
                if (n9 != 2) {
                    if (n9 != 3) {
                        continue;
                    }
                }
                try {
                    array[i] = c38.b(read).getRGB();
                }
                catch (AWTException ex2) {
                    System.out.println("ERROR 3223 " + ex2);
                }
            }
        }
        this.l = Toolkit.getDefaultToolkit().createImage(new MemoryImageSource(n, n2, array, 0, n));
    }
    
    public C40(final Image l, final int n, final int n2, final int n3, final int n4, final int n5) {
        this(n2, n3, n4, n5, n);
        this.l = l;
    }
    
    public void b(final C15 c15, final int n, final double n2, final int n3, final int n4, final double n5, final int n6, final int n7) {
        if (!super.f || !super.C) {
            return;
        }
        final int n8 = (int)((this.k - n) * n2 + n3);
        final int n9 = (int)(n7 - ((this.e - n4) * n5 + n6));
        final int n10 = (int)((this.n - n) * n2 + n3);
        final int n11 = (int)(n7 - ((this.i - n4) * n5 + n6));
        if (this.l != null) {
            c15.J(this.l, n8, n11, n10 - n8, n11 - n9, null);
        }
        else {
            c15.l(n8, n10, n10 - n8, n11 - n9);
            c15.a(n8, n9, n10, n11);
            c15.a(n8, n11, n10, n9);
        }
    }
    
    public Object clone() {
        return new C40(this.l, this.h, this.k, this.e, this.n, this.i);
    }
    
    static {
        C40.m = "Copyright (c) 2000 - ZoomON Inc.  All Rights Reserved";
    }
    
    private C40(final int k, final int e, final int n, final int i, final int h) {
        this.k = k;
        this.e = e;
        this.n = n;
        this.i = i;
        this.h = h;
        super.P = Math.min(k, n);
        super.B = Math.min(e, i);
        super.H = Math.max(k, n) - Math.min(k, n);
        super.J = Math.max(e, i) - Math.min(e, i);
    }
    
    public void f(final int n, final int n2) {
        super.f(n, n2);
        this.k += n;
        this.e += n2;
    }
    
    private void a(final InputStream inputStream, final int n) {
        try {
            final byte[] array = new byte[n];
            inputStream.read(array);
            this.l = Toolkit.getDefaultToolkit().createImage(array);
            final MediaTracker mediaTracker = new MediaTracker(new Label(" "));
            mediaTracker.addImage(this.l, 0);
            try {
                mediaTracker.waitForID(0);
            }
            catch (InterruptedException ex) {}
        }
        catch (Throwable t) {
            System.out.println("Warning: #3429800 cr jp");
            this.l = null;
        }
    }
    
    public C40(final int n, final int n2, final int n3, final int n4, final int n5, final int n6, final int n7, final int n8, final boolean b, final InputStream inputStream, final int n9) throws IOException {
        this(n3, n4, n5, n6, n7);
        if (n9 == 6) {
            this.a(inputStream, n8);
            return;
        }
        final int[] array = new int[n8];
        for (int i = 0; i < n8; ++i) {
            final int read = inputStream.read();
            final int read2 = inputStream.read();
            final int read3 = inputStream.read();
            if (b) {
                inputStream.read();
            }
            array[i] = read << 16 + read2 << 8 + read3;
        }
        this.l = new Button().createImage(new MemoryImageSource(n, n2, array, 0, n));
    }
    
    public void a(final C20 c20, final int n, final double n2, final int n3, final int n4, final double n5, final int n6, final int n7) {
        System.out.println("ImageWidget::worldToWindowPaintPrint();");
    }
}
