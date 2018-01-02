import java.awt.image.ImageObserver;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.image.ColorModel;
import java.awt.image.PixelGrabber;
import java.util.Hashtable;
import java.util.Vector;
import java.awt.image.DirectColorModel;
import java.awt.image.ImageConsumer;
import java.awt.Image;
import java.awt.image.ImageProducer;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class b extends Applet implements Runnable, ImageProducer, d
{
    int a;
    int for;
    Image if;
    Thread do;
    ImageConsumer int;
    DirectColorModel new;
    
    public void a(final int n, final int n2) {
        final int[] array = new int[n * n2];
        final int[] array2 = new int[n * n2];
        final int[] array3 = new int[n * n2];
        final Vector<String> vector = new Vector<String>();
        int n3 = 0;
        while (true) {
            final String parameter = this.getParameter("pic" + Integer.toString(n3 + 1));
            if (parameter == null) {
                break;
            }
            ++n3;
            vector.addElement(parameter);
        }
        final Vector vector2 = new Vector<j>();
        final Hashtable<String, j> hashtable = new Hashtable<String, j>();
        int n4 = 0;
        final j j = new j();
        hashtable.put(j.getName(), j);
        final h h = new h();
        hashtable.put(h.getName(), (j)h);
        final a a = new a();
        hashtable.put(a.getName(), (j)a);
        final g g = new g();
        hashtable.put(g.getName(), (j)g);
        final c c = new c();
        hashtable.put(c.getName(), (j)c);
        final k k = new k();
        hashtable.put(k.getName(), (j)k);
        final e e = new e();
        hashtable.put(e.getName(), (j)e);
        while (true) {
            final String parameter2 = this.getParameter("eff" + Integer.toString(n4 + 1));
            if (parameter2 == null || parameter2.equals("")) {
                break;
            }
            System.err.print(n4);
            System.err.println(" " + parameter2);
            final j i = hashtable.get(parameter2);
            if (i != null) {
                vector2.addElement(i);
            }
            ++n4;
        }
        final int size = vector2.size();
        int int1 = 30;
        int int2 = 2;
        int int3 = 4;
        final long n5 = 1000 / int1;
        final String parameter3 = this.getParameter("picDelay");
        if (parameter3 != null) {
            try {
                int2 = Integer.parseInt(parameter3);
            }
            catch (NumberFormatException ex) {}
        }
        final String parameter4 = this.getParameter("effDelay");
        if (parameter4 != null) {
            try {
                int3 = Integer.parseInt(parameter4);
            }
            catch (NumberFormatException ex2) {}
        }
        final String parameter5 = this.getParameter("FPS");
        if (parameter5 != null) {
            try {
                int1 = Integer.parseInt(parameter5);
            }
            catch (NumberFormatException ex3) {}
        }
        int[] array4 = array;
        int[] array5 = array2;
        while (true) {
            int n6 = int2;
            for (int l = 0; l <= n3; ++l) {
                int if1 = int2;
                int do1 = int3;
                String for1 = null;
                final long currentTimeMillis = System.currentTimeMillis();
                if (l < n3) {
                    final i m = new i(vector.elementAt(l), int2, int3);
                    final PixelGrabber pixelGrabber = new PixelGrabber(this.getImage(this.getDocumentBase(), m.a()), 0, 0, n, n2, array5, 0, n);
                    try {
                        pixelGrabber.grabPixels();
                    }
                    catch (InterruptedException ex4) {
                        ++l;
                        continue;
                    }
                    if1 = m.if();
                    do1 = m.do();
                    for1 = m.for();
                }
                else {
                    for (int n7 = 0; n7 < n * n2; ++n7) {
                        array5[n7] = 0;
                    }
                }
                final int n8 = do1 * int1;
                if (l != 0) {
                    final long n9 = currentTimeMillis + n6 * 1000;
                    try {
                        Thread.sleep(Math.max(0L, n9 - System.currentTimeMillis()));
                    }
                    catch (InterruptedException ex5) {
                        break;
                    }
                }
                n6 = if1;
                d d = this;
                if (for1 == null || for1.equals("")) {
                    if (l > 0 && l < n3 && size > 0) {
                        d = vector2.elementAt((int)(Math.random() * (size - 1) + 0.5));
                    }
                }
                else {
                    final j j2 = hashtable.get(for1);
                    if (j2 != null) {
                        d = j2;
                    }
                }
                d.a();
                long currentTimeMillis2 = System.currentTimeMillis();
                for (int n10 = 0; n10 < n8; ++n10) {
                    d.transform(array5, array4, n, n2, n10, n8, array3);
                    this.a(array3);
                    currentTimeMillis2 += n5;
                    try {
                        Thread.sleep(Math.max(0L, currentTimeMillis2 - System.currentTimeMillis()));
                    }
                    catch (InterruptedException ex6) {
                        break;
                    }
                }
                this.a(array5);
                final int[] array6 = array5;
                array5 = array4;
                array4 = array6;
            }
        }
    }
    
    public synchronized void a(final Object o) {
        if (this.int != null) {
            this.int.setPixels(0, 0, this.a, this.for, this.new, (int[])o, 0, this.a);
            this.int.imageComplete(2);
        }
        this.if();
    }
    
    public void start() {
        if (this.do == null) {
            (this.do = new Thread(this)).start();
        }
    }
    
    public void run() {
        final Dimension size = this.size();
        this.a = size.width;
        this.for = size.height;
        this.new = new DirectColorModel(32, 16711680, 65280, 255, 0);
        this.if = Toolkit.getDefaultToolkit().createImage(this);
        this.a(this.a, this.for);
    }
    
    public void stop() {
        if (this.do != null && this.do.isAlive()) {
            this.do.stop();
        }
        this.do = null;
    }
    
    private synchronized void if() {
        this.getGraphics().drawImage(this.if, 0, 0, this.a, this.for, null);
    }
    
    public synchronized void addConsumer(final ImageConsumer int1) {
        (this.int = int1).setDimensions(this.a, this.for);
        this.int.setHints(30);
        this.int.setColorModel(this.new);
    }
    
    public synchronized boolean isConsumer(final ImageConsumer imageConsumer) {
        return true;
    }
    
    public synchronized void removeConsumer(final ImageConsumer imageConsumer) {
    }
    
    public void startProduction(final ImageConsumer imageConsumer) {
        this.addConsumer(imageConsumer);
    }
    
    public void requestTopDownLeftRightResend(final ImageConsumer imageConsumer) {
    }
    
    public void a() {
    }
    
    public void transform(final int[] array, final int[] array2, final int n, final int n2, final int n3, final int n4, final int[] array3) {
        final int n5 = n3 * 256 / (n4 - 1);
        for (int i = 0; i < n2; ++i) {
            for (int j = 0; j < n; ++j) {
                f.a(array3, array, array2, n, j, i, j, i, j, i, n5);
            }
        }
    }
    
    public String getName() {
        return "Fade Plugin";
    }
}
