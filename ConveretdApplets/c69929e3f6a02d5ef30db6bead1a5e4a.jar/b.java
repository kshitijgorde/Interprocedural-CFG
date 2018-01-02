import java.util.Enumeration;
import java.util.Hashtable;
import java.awt.image.ImageConsumer;
import java.awt.image.DirectColorModel;
import java.util.Vector;
import java.awt.image.ColorModel;
import java.awt.image.ImageProducer;
import java.util.Observable;

// 
// Decompiled by Procyon v0.5.30
// 

public abstract class b extends Observable implements ImageProducer, Runnable
{
    protected int[] a;
    protected int[] b;
    protected int c;
    protected int d;
    protected int e;
    protected int f;
    protected ColorModel g;
    protected Vector h;
    protected Thread i;
    public static int j;
    
    public b(final int[] a, final int[] b, final int c, final int d, final int e, final int f) {
        this.g = new DirectColorModel(24, 16711680, 65280, 255);
        this.h = null;
        this.h = new Vector();
        this.a = a;
        this.b = b;
        this.c = c;
        this.d = d;
        this.e = e;
        this.f = f;
    }
    
    public void addConsumer(final ImageConsumer imageConsumer) {
        if (!this.isConsumer(imageConsumer)) {
            this.h.addElement(imageConsumer);
        }
    }
    
    public void removeConsumer(final ImageConsumer imageConsumer) {
        if (this.isConsumer(imageConsumer)) {
            this.h.removeElement(imageConsumer);
        }
    }
    
    public boolean isConsumer(final ImageConsumer imageConsumer) {
        return this.h.indexOf(imageConsumer) > -1;
    }
    
    public void startProduction(final ImageConsumer imageConsumer) {
        this.addConsumer(imageConsumer);
        if (this.i == null) {
            (this.i = new Thread(this)).start();
        }
    }
    
    public void run() {
        final int c = a.c;
        int[] a = null;
        int n = 0;
        long currentTimeMillis = System.currentTimeMillis();
        final int f = this.f;
        long currentTimeMillis2;
        long n2;
        Hashtable<Object, Object> hashtable;
        Hashtable<Object, Object> properties;
        Enumeration elements;
        ImageConsumer imageConsumer;
        Label_0072_Outer:Label_0136_Outer:
        while (true) {
            this.f = f;
            currentTimeMillis2 = System.currentTimeMillis();
            if (currentTimeMillis2 < currentTimeMillis) {
                currentTimeMillis = 0L;
            }
            n2 = currentTimeMillis2 - currentTimeMillis;
            while (true) {
                Label_0086: {
                    if (n2 <= this.f) {
                        break Label_0086;
                    }
                    n2 = this.f;
                    if (c == 0) {
                        break Label_0086;
                    }
                    n2 >>= 1;
                    this.f >>= 1;
                }
                if (this.f > 2000) {
                    continue Label_0136_Outer;
                }
                break;
            }
            while (true) {
                Label_0347: {
                    if (n > 0) {
                        a = this.a(n2);
                        if (a == null) {
                            try {
                                Thread.currentThread();
                                Thread.sleep(this.f);
                                break Label_0347;
                            }
                            catch (InterruptedException ex) {
                                return;
                            }
                        }
                    }
                    hashtable = new Hashtable<Object, Object>();
                    properties = hashtable;
                    elements = ((Vector)this.h.clone()).elements();
                    while (true) {
                        Label_0162: {
                            if (c != 0) {
                                break Label_0162;
                            }
                            while (!elements.hasMoreElements()) {
                                try {
                                    Thread.currentThread();
                                    Thread.sleep(0L);
                                    if (c != 0 || c != 0) {
                                        continue Label_0072_Outer;
                                    }
                                }
                                catch (InterruptedException ex2) {
                                    return;
                                }
                                ++n;
                                if (n2 >= this.f) {
                                    break Label_0347;
                                }
                                continue Label_0072_Outer;
                            }
                        }
                        imageConsumer = elements.nextElement();
                        if (n == 0) {
                            imageConsumer.setColorModel(this.g);
                            imageConsumer.setDimensions(this.c, this.d);
                            imageConsumer.setProperties(properties);
                            imageConsumer.setHints(1);
                            imageConsumer.setPixels(0, 0, this.c, this.d, this.g, this.a, 0, this.c);
                            imageConsumer.imageComplete(2);
                            if (c == 0) {
                                continue;
                            }
                        }
                        imageConsumer.setPixels(0, 0, this.c, this.d, this.g, a, 0, this.c);
                        imageConsumer.imageComplete(2);
                        continue;
                    }
                }
                hashtable = new Hashtable<Object, Object>();
                if (c == 0) {
                    break;
                }
                continue;
            }
        }
        final Enumeration<ImageConsumer> elements2 = (Enumeration<ImageConsumer>)((Vector)this.h.clone()).elements();
        while (true) {
            while (true) {
                Label_0435: {
                    if (c == 0) {
                        break Label_0435;
                    }
                    final Object nextElement = elements2.nextElement();
                    final ImageConsumer imageConsumer2 = (ImageConsumer)nextElement;
                    imageConsumer2.setPixels(0, 0, this.c, this.d, this.g, this.b, 0, this.c);
                    imageConsumer2.imageComplete(2);
                }
                if (elements2.hasMoreElements()) {
                    continue;
                }
                break;
            }
            this.setChanged();
            final Object nextElement = this;
            if (c == 0) {
                this.notifyObservers(new String(a("iSX,\rTUP-\u0010yNW'")));
                return;
            }
            continue;
        }
    }
    
    public boolean a() {
        return this.i.isAlive();
    }
    
    public abstract int[] a(final long p0);
    
    public void requestTopDownLeftRightResend(final ImageConsumer imageConsumer) {
    }
    
    private static String a(final String s) {
        final char[] charArray = s.toCharArray();
        final int length = charArray.length;
        int n = 0;
        while (true) {
            Label_0089: {
                if (length > 1) {
                    break Label_0089;
                }
                char[] array2;
                char[] array = array2 = charArray;
                int n3;
                int n2 = n3 = n;
                while (true) {
                    final char c = array2[n3];
                    char c2 = '\0';
                    switch (n % 5) {
                        case 0: {
                            c2 = '=';
                            break;
                        }
                        case 1: {
                            c2 = '!';
                            break;
                        }
                        case 2: {
                            c2 = '9';
                            break;
                        }
                        case 3: {
                            c2 = 'B';
                            break;
                        }
                        default: {
                            c2 = '~';
                            break;
                        }
                    }
                    array[n2] = (char)(c ^ c2);
                    ++n;
                    if (length != 0) {
                        break;
                    }
                    array = (array2 = charArray);
                    n2 = (n3 = length);
                }
            }
            if (n >= length) {
                return new String(charArray);
            }
            continue;
        }
    }
}
