import java.awt.image.ImageObserver;
import java.awt.Image;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

// 
// Decompiled by Procyon v0.5.30
// 

class cluster
{
    static BufferedImage[][] cliparr;
    static jigsaw context;
    static boolean ifrot;
    int lowx;
    int lowy;
    int highx;
    int highy;
    int posx;
    int posy;
    piece[] parr;
    int rotn;
    BufferedImage b;
    BufferedImage clip;
    
    void rotate(final boolean b) {
        final int n = this.b.getWidth() / 2;
        final int n2 = this.b.getWidth() - 1;
        final int n3 = (n + n != this.b.getWidth()) ? 1 : 0;
        if (b) {
            ++this.rotn;
        }
        else {
            --this.rotn;
        }
        this.rotn = (this.rotn + 4) % 4;
        if (b) {
            for (int i = 0; i < n + n3; ++i) {
                for (int j = 0; j < n; ++j) {
                    final int rgb = this.b.getRGB(i, j);
                    this.b.setRGB(i, j, this.b.getRGB(n2 - j, i));
                    this.b.setRGB(n2 - j, i, this.b.getRGB(n2 - i, n2 - j));
                    this.b.setRGB(n2 - i, n2 - j, this.b.getRGB(j, n2 - i));
                    this.b.setRGB(j, n2 - i, rgb);
                }
            }
        }
        else {
            for (int k = 0; k < n + n3; ++k) {
                for (int l = 0; l < n; ++l) {
                    final int rgb2 = this.b.getRGB(k, l);
                    this.b.setRGB(k, l, this.b.getRGB(l, n2 - k));
                    this.b.setRGB(l, n2 - k, this.b.getRGB(n2 - k, n2 - l));
                    this.b.setRGB(n2 - k, n2 - l, this.b.getRGB(n2 - l, k));
                    this.b.setRGB(n2 - l, k, rgb2);
                }
            }
        }
    }
    
    cluster join(final cluster cluster) {
        final piece[] parr = cluster.parr;
        int min = Math.min(this.lowx, cluster.lowx);
        int min2 = Math.min(this.lowy, cluster.lowy);
        int max = Math.max(this.highx, cluster.highx);
        int max2 = Math.max(this.highy, cluster.highy);
        int n = 0;
        int n2 = 0;
        cluster cluster2;
        if (min == cluster.lowx && min2 == cluster.lowy && max == cluster.highx && max2 == cluster.highy) {
            cluster2 = cluster;
        }
        else {
            if (cluster.ifrot) {
                while (max - min > max2 - min2) {
                    if (min2 <= 0) {
                        break;
                    }
                    --min2;
                }
                while (max - min > max2 - min2) {
                    ++max2;
                }
                while (max2 - min2 > max - min) {
                    if (min <= 0) {
                        break;
                    }
                    --min;
                }
                while (max2 - min2 > max - min) {
                    ++max;
                }
            }
            cluster2 = new cluster();
            cluster2.b = new BufferedImage((max - min + 1) * piece.pw + 2 * piece.wover, (max2 - min2 + 1) * piece.ph + 2 * piece.hover, 2);
            if (this.rotn == 0) {
                n = (cluster.lowx - min) * piece.pw;
                n2 = (cluster.lowy - min2) * piece.ph;
            }
            if (this.rotn == 1) {
                n = (cluster.lowy - min2) * piece.pw;
                n2 = -(cluster.highx - max) * piece.ph;
            }
            if (this.rotn == 2) {
                n = -(cluster.highx - max) * piece.pw;
                n2 = -(cluster.highy - max2) * piece.ph;
            }
            if (this.rotn == 3) {
                n = -(cluster.highy - max2) * piece.pw;
                n2 = (cluster.lowx - min) * piece.ph;
            }
            for (int i = 0; i < cluster.b.getWidth(); ++i) {
                for (int j = 0; j < cluster.b.getHeight(); ++j) {
                    cluster2.b.setRGB(n + i, n2 + j, cluster.b.getRGB(i, j));
                }
            }
            cluster2.posx = cluster.posx - n;
            cluster2.posy = cluster.posy - n2;
            cluster2.lowx = min;
            cluster2.highx = max;
            cluster2.lowy = min2;
            cluster2.highy = max2;
        }
        cluster2.parr = new piece[this.parr.length + parr.length];
        for (int k = 0; k < this.parr.length; ++k) {
            cluster2.parr[k] = this.parr[k];
        }
        for (int l = 0; l < parr.length; ++l) {
            cluster2.parr[l + this.parr.length] = parr[l];
        }
        if (this.rotn == 0) {
            n = (this.lowx - min) * piece.pw;
            n2 = (this.lowy - min2) * piece.ph;
        }
        if (this.rotn == 1) {
            n = (this.lowy - min2) * piece.pw;
            n2 = -(this.highx - max) * piece.ph;
        }
        if (this.rotn == 2) {
            n = -(this.highx - max) * piece.pw;
            n2 = -(this.highy - max2) * piece.ph;
        }
        if (this.rotn == 3) {
            n = -(this.highy - max2) * piece.pw;
            n2 = (this.lowx - min) * piece.ph;
        }
        for (int n3 = 0; n3 < this.b.getWidth(); ++n3) {
            for (int n4 = 0; n4 < this.b.getHeight(); ++n4) {
                final int n5 = this.b.getRGB(n3, n4) >>> 24;
                if (n5 == 255) {
                    cluster2.b.setRGB(n + n3, n2 + n4, this.b.getRGB(n3, n4));
                }
                else if (n5 != 0) {
                    cluster2.b.setRGB(n + n3, n2 + n4, ((cluster2.b.getRGB(n + n3, n2 + n4) | this.b.getRGB(n3, n4)) & 0xFFFFFF) + (n5 + (cluster2.b.getRGB(n + n3, n2 + n4) >>> 24) << 24));
                }
            }
        }
        cluster2.rotn = this.rotn;
        if (cluster.cliparr[max - min][max2 - min2] == null) {
            cluster.cliparr[max - min][max2 - min2] = new BufferedImage(cluster2.b.getWidth() + piece.wover, cluster2.b.getHeight() + piece.hover, 2);
        }
        cluster2.clip = cluster.cliparr[max - min][max2 - min2];
        return cluster2;
    }
    
    void paint(final Graphics graphics, final int n, final int n2) {
        graphics.drawImage(this.b, this.posx - n, this.posy - n2, cluster.context);
    }
}
