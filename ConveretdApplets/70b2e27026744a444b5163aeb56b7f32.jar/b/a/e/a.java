// 
// Decompiled by Procyon v0.5.30
// 

package b.a.e;

import java.awt.MediaTracker;
import javax.swing.JPanel;
import java.io.InputStream;
import b.a.d.c;
import java.io.IOException;
import java.awt.Toolkit;
import java.io.ByteArrayOutputStream;
import java.util.Map;
import java.awt.Dimension;
import java.net.URL;
import java.awt.Image;
import java.awt.Component;

public class a extends Component implements Runnable
{
    protected Object a;
    protected Image b;
    protected String c;
    protected URL d;
    protected Image e;
    protected Class f;
    protected boolean g;
    protected boolean h;
    protected int i;
    protected int j;
    
    public synchronized int getWidth() {
        return this.i;
    }
    
    public synchronized int getHeight() {
        return this.j;
    }
    
    public synchronized Dimension getSize() {
        if (this.i < 0 || this.j < 0) {
            return null;
        }
        return new Dimension(this.i, this.j);
    }
    
    public synchronized boolean imageUpdate(final Image image, final int n, final int n2, final int n3, final int i, final int j) {
        if ((n & 0xC0) != 0x0) {
            this.g = false;
            this.h = true;
            return false;
        }
        if ((n & 0x1) != 0x0) {
            this.i = i;
        }
        if ((n & 0x2) != 0x0) {
            this.j = j;
        }
        return (n & 0x20) == 0x0;
    }
    
    public void run() {
        Image b = null;
        if (this.c != null) {
            b = a(this.c, this.f, this);
        }
        else if (this.d != null) {
            b = a(this.d, this);
        }
        else if (this.b != null) {
            b = a(this.b, this);
        }
        synchronized (this) {
            this.h = true;
            if (b != null) {
                this.b = b;
            }
            else {
                this.b = null;
                this.g = false;
            }
        }
    }
    
    public static Image a(final String s, final Class clazz) {
        return a(s, clazz, null);
    }
    
    public static Image a(final String s, final Class clazz, final Object o) {
        return a(s, clazz, o, null);
    }
    
    public static Image a(final String s, final Class clazz, final Object o, final Map map) {
        InputStream inputStream = null;
        byte[] a = null;
        Image image;
        try {
            inputStream = new b.a.b.a(s, clazz);
            final ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            final byte[] array = new byte[4096];
            int read;
            while ((read = inputStream.read(array)) > 0) {
                byteArrayOutputStream.write(array, 0, read);
            }
            byteArrayOutputStream.close();
            final byte[] byteArray = byteArrayOutputStream.toByteArray();
            image = Toolkit.getDefaultToolkit().createImage(byteArray);
            a = a(byteArray);
        }
        catch (IOException ex) {
            image = null;
        }
        if (inputStream != null) {
            try {
                inputStream.close();
            }
            catch (IOException ex2) {}
        }
        if (image == null) {
            return null;
        }
        if (a != null && map != null) {
            map.put("commentBytes", a);
            map.put("comment", c.a(a));
        }
        return a(image, o);
    }
    
    public static Image a(final URL url, final Object o) {
        final Image image = Toolkit.getDefaultToolkit().createImage(url);
        if (image != null) {
            return a(image, o);
        }
        return null;
    }
    
    protected static Image a(Image e, Object a) {
        boolean b = true;
        if (a instanceof a) {
            final a a2 = (a)a;
            synchronized (a2) {
                a2.e = e;
                a = a2.a;
            }
        }
        Component component;
        if (a instanceof Component) {
            component = (Component)a;
        }
        else {
            component = new JPanel();
            b = false;
        }
        final MediaTracker mediaTracker = new MediaTracker(component);
        mediaTracker.addImage(e, 0);
        try {
            mediaTracker.waitForID(0);
            if (mediaTracker.isErrorID(0) || !mediaTracker.checkID(0)) {
                e = null;
            }
        }
        catch (InterruptedException ex) {
            e = null;
        }
        if (b) {
            component.repaint();
        }
        return e;
    }
    
    public static byte[] a(final byte[] array) {
        if (array == null) {
            return null;
        }
        final int length = array.length;
        if (array.length >= 11 && array[0] == -1 && array[1] == -40 && array[2] == -1 && array[3] == -32 && array[6] == 74 && array[7] == 70 && array[8] == 73 && array[9] == 70 && array[10] == 0) {
            int n2;
            for (int i = 2; i < length; i += n2) {
                while (array[i] != -1 && i < length) {
                    ++i;
                }
                if (i >= length) {
                    return null;
                }
                while (array[i] == -1 && i < length) {
                    ++i;
                }
                if (i >= length + 3) {
                    return null;
                }
                final int n = array[i] & 0xFF;
                n2 = ((array[i + 1] & 0xFF) << 8 | (array[i + 2] & 0xFF));
                if (i + n2 >= length) {
                    return null;
                }
                if (n == 254 && n2 > 2) {
                    final byte[] array2 = new byte[n2 - 2];
                    System.arraycopy(array, i + 3, array2, 0, n2 - 2);
                    return array2;
                }
                if (n == 218) {
                    return null;
                }
            }
            return null;
        }
        return null;
    }
}
