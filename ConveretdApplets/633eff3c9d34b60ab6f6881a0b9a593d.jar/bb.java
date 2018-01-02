import java.awt.image.ImageProducer;
import java.awt.image.MemoryImageSource;
import java.awt.Toolkit;
import java.awt.Dimension;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.util.Vector;

// 
// Decompiled by Procyon v0.5.30
// 

class bb extends y implements bi
{
    private static final int e = 49;
    private static final int f = 50;
    private static final int g = 51;
    private static final int h = 52;
    private static final int i = 53;
    private static final int j = 54;
    private static final int k = 55;
    private static final int l = 56;
    private static final int m = 57;
    private static final int n = 43;
    private static final int o = 45;
    private static final int p = 61;
    private static final int q = 32;
    private static final int r = 46;
    private static final int s = 1006;
    private static final int t = 1007;
    private static final int u = 1004;
    private static final int v = 1005;
    private static final int w = 1002;
    private static final int x = 1003;
    private static final int y = 1000;
    private static final int z = 1008;
    private static final int A = -1;
    private static final int B = 10;
    private static final int C;
    private int D;
    private static final Vector E;
    
    bb(final bk d) {
        this.D = 0;
        this.d = d;
        this.d.b().a(this);
        this.d.a((bi)this);
    }
    
    boolean a(final int n) {
        return n == 0;
    }
    
    boolean a(final float[] array) {
        if (!this.d.c(this)) {
            return false;
        }
        switch (this.D) {
            case 52:
            case 1006: {
                final int n = 0;
                array[n] -= this.c() * 3.0f;
                break;
            }
            case 56:
            case 1004: {
                final int n2 = 1;
                array[n2] += this.c() * 3.0f;
                break;
            }
            case 54:
            case 1007: {
                final int n3 = 0;
                array[n3] += this.c() * 3.0f;
                break;
            }
            case 50:
            case 1005: {
                final int n4 = 1;
                array[n4] -= this.c() * 3.0f;
                break;
            }
            case 55: {
                final int n5 = 0;
                array[n5] -= this.c() * 3.0f;
                final int n6 = 1;
                array[n6] += this.c() * 3.0f;
                break;
            }
            case 49: {
                final int n7 = 0;
                array[n7] -= this.c() * 3.0f;
                final int n8 = 1;
                array[n8] -= this.c() * 3.0f;
                break;
            }
            case 57: {
                final int n9 = 0;
                array[n9] += this.c() * 3.0f;
                final int n10 = 1;
                array[n10] += this.c() * 3.0f;
                break;
            }
            case 51: {
                final int n11 = 0;
                array[n11] += this.c() * 3.0f;
                final int n12 = 1;
                array[n12] -= this.c() * 3.0f;
                break;
            }
            case 43:
            case 61:
            case 1002: {
                array[3] = this.a(array[3], true);
                break;
            }
            case 45:
            case 1003: {
                array[3] = this.a(array[3], false);
                break;
            }
            default: {
                return false;
            }
        }
        return true;
    }
    
    public void a(final bh bh) {
        if (!this.d.b().E) {
            return;
        }
        if (this.d.b().L != null) {
            for (int i = 0; i < this.d.b().L.length; ++i) {
                if (this.d.b().L[i].a(new Point(this.d.b().getSize().width / 2, this.d.b().getSize().height / 2))) {
                    this.d.b().L[i].b(new Point(this.d.b().getSize().width / 2, this.d.b().getSize().height / 2));
                    this.d.b().L[i].K = true;
                    this.d.b().L[i].H = true;
                    this.d.b().L[i].J = true;
                }
                else {
                    this.d.b().L[i].K = false;
                    this.d.b().L[i].H = false;
                    this.d.b().L[i].J = false;
                }
            }
        }
    }
    
    void a() {
        this.d.b().b(this);
        this.d.b((bi)this);
    }
    
    boolean a(final Graphics graphics) {
        final Dimension size = this.d.b().size();
        final int n = 14;
        if (!this.d.b().E) {
            return false;
        }
        try {
            graphics.setColor(Color.black);
            graphics.drawLine((int)size.getWidth() / 2, (int)size.getHeight() / 2 - n / 2, (int)size.getWidth() / 2, (int)size.getHeight() / 2 + n / 2 - 1);
            graphics.drawLine((int)size.getWidth() / 2 - n / 2, (int)size.getHeight() / 2, (int)size.getWidth() / 2 + n / 2, (int)size.getHeight() / 2);
            graphics.setColor(Color.white);
            graphics.drawLine((int)size.getWidth() / 2 - 1, (int)size.getHeight() / 2 - n / 2, (int)size.getWidth() / 2 - 1, (int)size.getHeight() / 2 + n / 2 - 1);
            graphics.drawLine((int)size.getWidth() / 2 - n / 2 - 1, (int)size.getHeight() / 2 - 1, (int)size.getWidth() / 2 + n / 2 - 1, (int)size.getHeight() / 2 - 1);
        }
        catch (Exception ex) {}
        return false;
    }
    
    void a(final a a) {
        if (!this.d.b().J) {
            return;
        }
        Label_0212: {
            switch (a.id) {
                case 401: {
                    if (a.key != 10) {
                        break Label_0212;
                    }
                    if (this.d.b().L != null) {
                        for (int i = 0; i < this.d.b().L.length; ++i) {
                            if (this.d.b().L[i].a(new Point(this.d.b().getSize().width / 2, this.d.b().getSize().height / 2)) && this.d.b().L[i].a(this.d)) {
                                this.d.b().L[i].K = true;
                                break;
                            }
                        }
                        break;
                    }
                    break;
                }
                case 403: {
                    if (this.d.c(this)) {
                        break;
                    }
                    if (!bb.E.contains(new Integer(a.key))) {
                        break;
                    }
                    if (!this.d.a((y)this)) {
                        break;
                    }
                    try {
                        this.d.b().setCursor(Toolkit.getDefaultToolkit().createCustomCursor(Toolkit.getDefaultToolkit().createImage(new MemoryImageSource(16, 16, new int[256], 0, 16)), new Point(0, 0), ""));
                    }
                    catch (Exception ex) {}
                    this.d.b().E = true;
                    this.D = a.key;
                    break;
                }
                case 402:
                case 404: {
                    if (this.d.c(this)) {
                        if (a.key == this.D) {
                            this.d.b((y)this);
                            break;
                        }
                        break;
                    }
                    else {
                        if (a.key == 1000 || a.key == 53 || a.key == -1) {
                            this.d.a(new bc(this.d));
                            break;
                        }
                        if (a.key == 46 && (a.modifiers & 0x2) == 0x2) {
                            this.d.a((y)null);
                            break;
                        }
                        if (a.key == 32 && (a.modifiers & 0x2) == 0x2) {
                            this.d.a(new bf(this.d, 5));
                            break;
                        }
                        if (a.key == 1008 || a.key == bb.C) {
                            this.d.b().o();
                            break;
                        }
                        break;
                    }
                    break;
                }
                case 1005: {
                    this.d.b((y)this);
                    break;
                }
            }
        }
    }
    
    static {
        C = (System.getProperty("java.vendor").startsWith("Apple") ? 0 : 5);
        (E = new Vector()).addElement(new Integer(52));
        bb.E.addElement(new Integer(1006));
        bb.E.addElement(new Integer(56));
        bb.E.addElement(new Integer(1004));
        bb.E.addElement(new Integer(54));
        bb.E.addElement(new Integer(1007));
        bb.E.addElement(new Integer(50));
        bb.E.addElement(new Integer(1005));
        bb.E.addElement(new Integer(55));
        bb.E.addElement(new Integer(49));
        bb.E.addElement(new Integer(57));
        bb.E.addElement(new Integer(51));
        bb.E.addElement(new Integer(43));
        bb.E.addElement(new Integer(1002));
        bb.E.addElement(new Integer(61));
        bb.E.addElement(new Integer(45));
        bb.E.addElement(new Integer(1003));
        bb.E.addElement(new Integer(10));
    }
}
