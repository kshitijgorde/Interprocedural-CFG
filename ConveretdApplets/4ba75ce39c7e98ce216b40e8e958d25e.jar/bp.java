import java.awt.Dimension;
import java.awt.image.ImageObserver;
import java.awt.Component;
import java.awt.MediaTracker;
import java.awt.Panel;
import java.awt.image.ImageProducer;
import java.awt.image.ImageFilter;
import java.awt.image.FilteredImageSource;
import java.awt.Toolkit;
import java.awt.Image;
import java.io.IOException;
import java.io.DataInputStream;
import java.net.URL;
import java.io.InputStream;
import java.util.Vector;

// 
// Decompiled by Procyon v0.5.30
// 

class bp extends bo
{
    protected Vector a;
    protected InputStream b;
    protected w c;
    protected URL d;
    boolean e;
    
    void a(final g g) {
        this.a.addElement(g);
    }
    
    bp(final URL d) {
        this.a = new Vector();
        this.e = (System.getProperty("os.name").startsWith("Win") && System.getProperty("java.vendor").startsWith("Netscape") && System.getProperty("java.version").compareTo("1.1") >= 0);
        this.d = d;
    }
    
    void a(final InputStream b) {
        this.b = b;
    }
    
    protected int a(final byte[] array, final int n) {
        for (int i = array.length - 2; i > n; --i) {
            if (array[i] == -1) {
                if (array[i + 1] == -39) {
                    return i;
                }
                if (array[i + 1] == -38) {
                    return i;
                }
            }
        }
        throw new RuntimeException();
    }
    
    void a(final w c) {
        this.c = c;
        this.start();
    }
    
    protected byte[] a(final DataInputStream dataInputStream) throws IOException {
        byte[] b = new byte[6];
        dataInputStream.readFully(b, 0, 6);
        int n2;
        for (int n = 2; b[n + 1] != -64; n += 2 + n2) {
            if (b[n + 1] == -38) {
                return b;
            }
            n2 = (b[n + 2] << 8 & 0xFF00) + (b[n + 3] & 0xFF);
            b = this.b(b, n + 4 + (n2 + 2));
            dataInputStream.readFully(b, n + 4, n2 + 2);
        }
        return b;
    }
    
    protected void a(final w w, final int n) throws IOException, RuntimeException, InterruptedException {
        final byte[] a = this.a(new DataInputStream(this.b));
        int length = a.length;
        final byte[] b = this.b(a, n);
        while (true) {
            Thread.yield();
            final int min = Math.min(Math.max(1024, this.b.available()), b.length - length);
            if (min == 0) {
                break;
            }
            int i = 0;
            while (i < min) {
                if (this.e) {
                    i += this.b.read(b, length + i, b.length - length - i);
                }
                else {
                    i += this.b.read(b, length + i, min - i);
                }
            }
            length += i;
            Thread.yield();
            try {
                final int a2 = this.a(b, length - i);
                if (b[a2 + 1] != -38) {
                    this.a(w, new bs(this.d).a(b, 0, a2 + 1));
                    this.a(new f(2));
                    return;
                }
                b[a2 + 1] = -39;
                this.a(w, new bs(this.d).a(b, 0, a2 + 1));
                this.a(new f(1));
                b[a2 + 1] = -38;
            }
            catch (RuntimeException ex) {}
        }
    }
    
    protected void a(final w w, final Image image) throws InterruptedException {
        final Image image2 = Toolkit.getDefaultToolkit().createImage(new FilteredImageSource(image.getSource(), new c(this)));
        final MediaTracker mediaTracker = new MediaTracker(new Panel());
        mediaTracker.addImage(image2, 0);
        mediaTracker.waitForAll();
        final Dimension dimension = new Dimension(image2.getWidth(null) & image.getWidth(null), image2.getHeight(null) & image.getHeight(null));
        w.a(image, dimension, new float[] { dimension.width / 2.0f - 0.5f, dimension.height / 2.0f - 0.5f });
    }
    
    protected void a(final f f) {
        for (int i = 0; i < this.a.size(); ++i) {
            ((g)this.a.elementAt(i)).a(f);
        }
        try {
            Thread.sleep(0L);
        }
        catch (InterruptedException ex) {}
    }
    
    public void run() {
        try {
            this.c.a("DefViewpoint", new float[] { 0.0f, 0.0f, 0.0f, 0.1f });
            this.a(this.c, 0);
        }
        catch (ThreadDeath threadDeath) {
            throw threadDeath;
        }
        catch (Throwable t) {
            t.printStackTrace();
            this.a(new f(t));
        }
        finally {
            this.a.removeAllElements();
        }
    }
    
    protected byte[] b(final byte[] array, final int n) {
        final byte[] array2 = new byte[n];
        System.arraycopy(array, 0, array2, 0, Math.min(array.length, n));
        return array2;
    }
}
