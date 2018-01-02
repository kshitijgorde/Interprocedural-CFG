// 
// Decompiled by Procyon v0.5.30
// 

package com.jinsight.jetchart;

import java.awt.image.ImageObserver;
import java.awt.Component;
import java.awt.MediaTracker;
import java.awt.Image;

public class ImageSerie extends GraphSerie implements h
{
    private Image[] H;
    private k I;
    
    public void setImages(final Image[] h) {
        this.H = h;
    }
    
    void b() {
        final boolean g = GraphSerie.G;
        if (super.d != null) {
            final MediaTracker mediaTracker = new MediaTracker(super.d);
            int n = 0;
            while (true) {
                while (true) {
                    Label_0045: {
                        if (!g) {
                            break Label_0045;
                        }
                        mediaTracker.addImage(this.H[n], n);
                        ++n;
                    }
                    if (n < this.H.length) {
                        continue;
                    }
                    break;
                }
                try {
                    mediaTracker.waitForAll();
                    if (g) {
                        continue;
                    }
                }
                catch (InterruptedException ex) {
                    ex.printStackTrace();
                    if (g) {}
                }
                break;
            }
        }
        int n2 = 0;
        while (true) {
            while (true) {
                Label_0168: {
                    if (!g) {
                        break Label_0168;
                    }
                    Label_0165: {
                        if (n2 < super.E && !g) {
                            break Label_0165;
                        }
                        final ImageSerie imageSerie = this;
                        super.e.drawImage(this.H[n2], super.A[n2] - imageSerie.H[n2].getWidth(null) / 2 + super.y, super.B[n2] - this.H[n2].getHeight(null) / 2 - super.y, null);
                    }
                    ++n2;
                }
                if (n2 < this.H.length) {
                    continue;
                }
                break;
            }
            final ImageSerie imageSerie = this;
            if (!g) {
                if (this.I == null) {
                    this.I = new k();
                }
                this.I.a(super.d, super.e);
                return;
            }
            continue;
        }
    }
    
    void f() {
        final boolean g = GraphSerie.G;
        int n = 0;
        while (true) {
            while (true) {
                Label_0044: {
                    if (!g) {
                        break Label_0044;
                    }
                    this.b(n, super.A[n] - 2 + super.y, super.B[n] - 2 - super.y);
                    ++n;
                }
                if (n < super.A.length) {
                    continue;
                }
                break;
            }
            if (!g) {
                return;
            }
            continue;
        }
    }
    
    public ImageSerie(final double[] array) {
        super(array, null);
    }
    
    public ImageSerie(final double[] array, final Image[] h) {
        this(array);
        this.H = h;
    }
}
