import java.util.Hashtable;
import java.awt.image.ColorModel;
import java.util.zip.Inflater;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.image.ImageProducer;
import java.awt.image.ImageConsumer;

// 
// Decompiled by Procyon v0.5.30
// 

public class al extends am implements ImageConsumer
{
    private l a;
    private ImageProducer b;
    private boolean c;
    private boolean d;
    
    void a(final l a) {
        this.a = a;
        final Image image = Toolkit.getDefaultToolkit().createImage(super.a, super.b, super.c);
        (this.b = image.getSource()).startProduction(this);
        this.b();
        if (this.c && a.b != null) {
            this.a(a.b);
        }
        image.flush();
        this.a = null;
    }
    
    private synchronized void b() {
        this.d = true;
        try {
            while (true) {
                Label_0015: {
                    if (!c.l) {
                        break Label_0015;
                    }
                    this.wait();
                }
                if (this.d && this.b != null) {
                    continue;
                }
                break;
            }
        }
        catch (InterruptedException ex) {
            this.c = false;
        }
        finally {
            this.d = false;
            this.b = null;
        }
    }
    
    private void a(final int[] array) {
        final boolean l = c.l;
        if (super.d == null) {
            int length = array.length;
            while (true) {
                Label_0031: {
                    if (!l) {
                        break Label_0031;
                    }
                    --length;
                    final int n = length;
                    array[n] |= 0xFF000000;
                }
                if (length <= 0) {
                    return;
                }
                continue;
            }
        }
        else {
            final byte[] array2 = new byte[array.length];
            try {
                final Inflater inflater = new Inflater();
                inflater.setInput(super.d, super.e, super.f);
                inflater.inflate(array2);
            }
            catch (Exception ex) {}
            int n2 = 0;
            while (true) {
                Label_0275: {
                    if (!l) {
                        break Label_0275;
                    }
                    final int n3 = array[n2];
                    int n4 = array2[n2];
                    if (n4 < 0) {
                        n4 += 256;
                    }
                    int n5 = n3 & 0xFF;
                    int n6 = n3 >> 8 & 0xFF;
                    int n7 = n3 >> 16 & 0xFF;
                    if (super.g) {
                        if (n5 > n4) {
                            n5 = n4;
                        }
                        if (n6 > n4) {
                            n6 = n4;
                        }
                        if (n7 > n4) {
                            n7 = n4;
                        }
                        if (n4 > 0) {
                            n5 = (n5 << 8) / n4;
                            n6 = (n6 << 8) / n4;
                            n7 = (n7 << 8) / n4;
                            if (n5 > 255) {
                                n5 = 255;
                            }
                            if (n6 > 255) {
                                n6 = 255;
                            }
                            if (n7 > 255) {
                                n7 = 255;
                            }
                        }
                    }
                    array[n2] = (n5 | n6 << 8 | n7 << 16 | n4 << 24);
                    ++n2;
                }
                if (n2 >= array.length) {
                    return;
                }
                continue;
            }
        }
    }
    
    public synchronized void imageComplete(final int n) {
        this.c = (n == 3);
        this.d = false;
        if (this.b != null) {
            this.b.removeConsumer(this);
        }
        this.b = null;
        this.notify();
    }
    
    public void setColorModel(final ColorModel colorModel) {
    }
    
    public void setDimensions(final int c, final int d) {
        this.a.c = c;
        this.a.d = d;
    }
    
    public void setHints(final int n) {
    }
    
    public void setPixels(final int n, final int n2, final int n3, final int n4, final ColorModel colorModel, final byte[] array, final int n5, final int n6) {
    }
    
    public void setPixels(final int n, final int n2, final int n3, final int n4, final ColorModel colorModel, final int[] array, final int n5, final int n6) {
        if (this.a.b == null) {
            this.a.b = new int[this.a.c * this.a.d];
        }
        int n7 = 0;
        while (true) {
            Label_0081: {
                if (!c.l) {
                    break Label_0081;
                }
                System.arraycopy(array, n5 + n7 * n6, this.a.b, this.a.c * (n2 + n7) + n, n3);
                ++n7;
            }
            if (n7 >= n4) {
                return;
            }
            continue;
        }
    }
    
    public void setProperties(final Hashtable hashtable) {
    }
}
