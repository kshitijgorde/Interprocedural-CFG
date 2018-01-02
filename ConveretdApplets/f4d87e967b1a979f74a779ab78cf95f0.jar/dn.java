import en.ErrorDialog;
import java.awt.image.PixelGrabber;
import java.awt.image.ImageObserver;
import java.awt.Image;
import java.net.URL;
import java.applet.Applet;
import java.util.Vector;
import java.awt.MediaTracker;
import java.awt.Component;
import java.io.Serializable;

// 
// Decompiled by Procyon v0.5.30
// 

public class dn extends m implements Runnable, Serializable
{
    public c p;
    public n d;
    protected static final Thread[] a;
    protected static final Component n;
    protected static final MediaTracker v;
    protected static final Vector i;
    protected static final Vector l;
    public static Applet b;
    protected String c;
    
    public dn() {
        this.p = new c("size constrained", this, 1, false);
        this.d = new n("fixed dimension", this, 1, false);
        this.p.p(false);
    }
    
    public final void p(final Applet b) {
        dn.b = b;
    }
    
    public final void p() {
        System.out.println("ImageStream(tm) thread stopped...");
        dn.i.removeAllElements();
        dn.l.removeAllElements();
        if (dn.a[0] != null && dn.a[0].isAlive()) {
            dn.a[0].stop();
        }
        dn.a[0] = null;
    }
    
    public final void p(final String c) throws Exception {
        this.c = c;
        if (c == null) {
            return;
        }
        final long currentTimeMillis = System.currentTimeMillis();
        final String s = new String(String.valueOf(String.valueOf(dn.b.getCodeBase().toString())).concat(String.valueOf(String.valueOf(c))));
        synchronized (dn.v) {
            try {
                final Image image = dn.b.getImage(new URL(s));
                if (image == null) {
                    throw new Exception("ImageBase: applet.getImage() failed");
                }
                dn.v.addImage(image, 0);
                dn.v.waitForID(0, 0L);
                if (dn.v.isErrorID(0)) {
                    throw new Exception(String.valueOf(String.valueOf(new StringBuffer("ImageBase: Error loading [").append(c).append("]. ").append("Check if file exists and file format."))));
                }
                dn.v.removeImage(image, 0);
                this.p(image, this);
            }
            catch (InterruptedException ex) {
                ex.printStackTrace();
            }
        }
        // monitorexit(dn.v)
        System.out.println(String.valueOf(String.valueOf(new StringBuffer("Image.loadFrom(").append(s).append(") took ").append(System.currentTimeMillis() - currentTimeMillis).append("ms"))));
    }
    
    private final synchronized void p(final Image image, final Object o) throws Exception {
        final int width = image.getWidth(null);
        final int height = image.getHeight(null);
        if (width == -1 || height == -1) {
            throw new Exception("ImageBase: Unable to get image dimension.");
        }
        int d = width;
        int p2 = height;
        int n = 0;
        int n2 = 0;
        int n3 = 0;
        int n4 = 0;
        if (this.p.p()) {
            d = this.d.d();
            p2 = this.d.p();
            if (d < width) {
                n = (width - d) / 2;
            }
            else {
                n3 = (d - width) / 2;
            }
            if (p2 < height) {
                n2 = (height - p2) / 2;
            }
            else {
                n4 = (p2 - height) / 2;
            }
        }
        final int[] array = new int[d * p2];
        if (!new PixelGrabber(image, n, n2, d, p2, array, n4 * d + n3, d).grabPixels()) {
            throw new Exception("ImageBase: Cannot grab pixels");
        }
        if (o instanceof da) {
            ((da)o).d.p(d, p2, array);
        }
        else if (o instanceof w) {
            final w w = (w)o;
            final byte[] array2 = new byte[array.length];
            for (int i = 0; i < array.length; ++i) {
                array2[i] = (byte)(array[i] & 0xFF);
            }
            w.d.p(d, p2, array2);
        }
    }
    
    public final void run() {
        System.out.println("ImageStream(tm) thread running...");
        int n = 0;
        int n2 = 0;
    Label_0012_Outer:
        while (true) {
            while (true) {
                try {
                    int i = 0;
                Block_5:
                    while (true) {
                        if (n < dn.i.size()) {
                            n2 = 0;
                            for (i = 0; i < dn.i.size(); ++i) {
                                final Image image = dn.i.elementAt(i);
                                if (image == null) {
                                    ++n;
                                }
                                else {
                                    final long currentTimeMillis = System.currentTimeMillis();
                                    dn.v.waitForID(i + 1);
                                    if (dn.v.isErrorID(i + 1)) {
                                        break Block_5;
                                    }
                                    this.p(image, dn.l.elementAt(i));
                                    System.out.println(String.valueOf(String.valueOf(new StringBuffer("ImageStream: ID").append(i + 1).append(" loaded in ").append(System.currentTimeMillis() - currentTimeMillis).append("ms."))));
                                    dn.v.removeImage(image, i + 1);
                                    dn.i.setElementAt(null, i);
                                    dn.l.setElementAt(null, i);
                                }
                            }
                        }
                        else {
                            Thread.currentThread();
                            Thread.sleep(200L);
                            if (++n2 <= 15) {
                                continue Label_0012_Outer;
                            }
                            this.p();
                        }
                    }
                    throw new Exception(String.valueOf(String.valueOf(new StringBuffer("ImageStream: Error loading [").append(((dn)dn.l.elementAt(i)).c).append("] ID = ").append(i + 1).append("\nCheck if file exists and file format."))));
                }
                catch (InterruptedException ex) {
                    ex.printStackTrace();
                    this.p();
                    continue Label_0012_Outer;
                }
                catch (Exception ex2) {
                    ex2.printStackTrace();
                    final ErrorDialog errorDialog = new ErrorDialog("WaterLogic ImageStream(tm)", ex2.getMessage(), ex2);
                    continue Label_0012_Outer;
                }
                continue;
            }
        }
    }
    
    static {
        a = new Thread[1];
        n = new di();
        v = new MediaTracker(dn.n);
        i = new Vector();
        l = new Vector();
    }
}
