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
        b b = this;
        if (a.c == 0) {
            if (this.isConsumer(imageConsumer)) {
                return;
            }
            b = this;
        }
        b.h.addElement(imageConsumer);
    }
    
    public void removeConsumer(final ImageConsumer imageConsumer) {
        final boolean consumer = this.isConsumer(imageConsumer);
        if (a.c == 0) {
            if (consumer) {
                this.h.removeElement(imageConsumer);
            }
        }
    }
    
    public boolean isConsumer(final ImageConsumer imageConsumer) {
        int index;
        final int n = index = this.h.indexOf(imageConsumer);
        if (a.c == 0) {
            if (n > -1) {
                index = (true ? 1 : 0);
            }
            else {
                index = (false ? 1 : 0);
            }
        }
        return index != 0;
    }
    
    public void startProduction(final ImageConsumer imageConsumer) {
        this.addConsumer(imageConsumer);
        final Thread i = this.i;
        if (a.c == 0) {
            if (i != null) {
                return;
            }
            this.i = new Thread(this);
            final Thread j = this.i;
        }
        i.start();
    }
    
    public void run() {
        final int c = a.c;
        int[] a = null;
        int n = 0;
        long currentTimeMillis = System.currentTimeMillis();
        final int f = this.f;
        long currentTimeMillis2;
        long n2;
        long n3;
        int f2;
        Hashtable<Object, Object> hashtable;
        Hashtable<Object, Object> properties;
        Enumeration elements;
        ImageConsumer imageConsumer;
        Label_0077_Outer:Label_0146_Outer:
        while (true) {
            this.f = f;
            currentTimeMillis2 = System.currentTimeMillis();
            if (currentTimeMillis2 < currentTimeMillis) {
                currentTimeMillis = 0L;
            }
            n2 = currentTimeMillis2 - currentTimeMillis;
            n3 = lcmp(n2, (long)this.f);
            while (true) {
                Label_0095: {
                    if (c != 0) {
                        break Label_0095;
                    }
                    Label_0091: {
                        if (n3 <= 0) {
                            break Label_0091;
                        }
                        n2 = this.f;
                        if (c == 0) {
                            break Label_0091;
                        }
                        n2 >>= 1;
                        this.f >>= 1;
                    }
                    f2 = this.f;
                }
                if (n3 > 2000) {
                    continue Label_0146_Outer;
                }
                break;
            }
            while (true) {
                Label_0362: {
                    Label_0139: {
                        if (n > 0) {
                            a = this.a(n2);
                            Label_0133: {
                                if (c != 0) {
                                    break Label_0133;
                                }
                                if (a != null) {
                                    break Label_0139;
                                }
                                try {
                                    Thread.currentThread();
                                    Thread.sleep(this.f);
                                    break Label_0362;
                                }
                                catch (InterruptedException ex) {
                                    return;
                                }
                            }
                        }
                    }
                    hashtable = new Hashtable<Object, Object>();
                    properties = hashtable;
                    elements = ((Vector)this.h.clone()).elements();
                    while (true) {
                        Label_0172: {
                            if (c != 0) {
                                break Label_0172;
                            }
                            while (!elements.hasMoreElements()) {
                                try {
                                    Thread.currentThread();
                                    Thread.sleep(0L);
                                    if (c != 0 || c != 0) {
                                        continue Label_0077_Outer;
                                    }
                                }
                                catch (InterruptedException ex2) {
                                    return;
                                }
                                ++n;
                                if (n2 >= this.f) {
                                    break Label_0362;
                                }
                                continue Label_0077_Outer;
                            }
                        }
                        imageConsumer = elements.nextElement();
                        if (c == 0) {
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
                        }
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
                Label_0450: {
                    if (c == 0) {
                        break Label_0450;
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
                this.notifyObservers(new String(a("wey\u0003rJcq\u0002ogxv\b")));
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
        for (int length = charArray.length, i = 0; i < length; ++i) {
            final char[] array = charArray;
            final int n = i;
            final char c = array[n];
            char c2 = '\0';
            switch (i % 5) {
                case 0: {
                    c2 = '#';
                    break;
                }
                case 1: {
                    c2 = '\u0017';
                    break;
                }
                case 2: {
                    c2 = '\u0018';
                    break;
                }
                case 3: {
                    c2 = 'm';
                    break;
                }
                default: {
                    c2 = '\u0001';
                    break;
                }
            }
            array[n] = (char)(c ^ c2);
        }
        return new String(charArray);
    }
}
