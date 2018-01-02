import java.awt.Component;
import java.awt.image.ImageObserver;
import java.io.IOException;
import java.io.DataInputStream;
import java.util.zip.ZipInputStream;
import java.io.InputStream;
import java.awt.Font;
import java.awt.image.ImageProducer;
import java.net.MalformedURLException;
import java.awt.Event;
import java.net.URL;
import java.awt.Color;
import java.awt.MediaTracker;
import java.awt.Graphics;
import java.awt.Dimension;
import java.awt.image.PixelGrabber;
import java.awt.image.MemoryImageSource;
import java.awt.Image;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class Brainlink extends Applet implements Runnable
{
    int i;
    int i1;
    int x;
    int y;
    int color;
    int timer;
    int xpos;
    int[] pix;
    int[] zbuffer;
    int[] scnt;
    int[] adder;
    int[] xadder;
    int[] yadder;
    int[] zadder;
    int[] xs;
    int[] ys;
    int[] zs;
    int[] tex;
    int cnt;
    int cnt2;
    int cnter;
    int scrlsp;
    int trepeat;
    int fsize;
    int tupdate;
    int tlength;
    Image Img;
    int frameNumber;
    viewPoint camera;
    SinCos sincos;
    MemoryImageSource source;
    Thread killme;
    PixelGrabber grab;
    Dimension offDimension;
    Image offImage;
    Graphics offGraphics;
    MediaTracker tracer;
    int delay;
    int txtpos;
    int picxsize;
    int picysize;
    int cnt1;
    int partnr;
    Color c;
    Color backcolor;
    boolean explode;
    obj3d[] object1;
    PL[] poly_hullxs;
    PL[] poly_hullxe;
    String[] msgs;
    String[] urlnames;
    String OPTION;
    URL[] urls;
    int display;
    int sred;
    int sgreen;
    int sblue;
    
    int dotProduct(final point3d point3d, final point3d point3d2) {
        return point3d.rotoX * point3d2.rotoX + point3d.rotoY * point3d2.rotoY + point3d.rotoZ * point3d2.rotoZ;
    }
    
    public void line(final int n, final int n2, final int n3, final int n4, final int n5, final int n6, final int n7) {
        int n8 = n3 - n;
        int n9 = n4 - n2;
        if (n8 < 0) {
            n8 *= -1;
        }
        if (n9 <= 0) {
            n9 *= -1;
        }
        int n10;
        if (n8 > n9) {
            n10 = n9 * 2 - n8;
        }
        else {
            n10 = n8 * 2 - n9;
        }
        int n11;
        if (n8 > n9) {
            n11 = n9 * 2;
        }
        else {
            n11 = n8 * 2;
        }
        int i = n;
        int j = n2;
        final int n12 = (n9 - n8) * 2;
        if (n8 >= n9) {
            if (n < n3) {
                while (i < n3) {
                    if (n10 <= 0) {
                        n10 += n11;
                        ++i;
                    }
                    else {
                        n10 += n12;
                        ++i;
                        if (n2 < n4) {
                            ++j;
                        }
                        else {
                            --j;
                        }
                    }
                    if (i >= 0 && i < this.picxsize && j >= 0 && j < this.picysize) {
                        this.pix[i + j * this.picxsize] = -16777216 + (n5 << 16) + (n6 << 8) + n7;
                    }
                }
            }
            if (n > n3) {
                while (i > n3) {
                    if (n10 <= 0) {
                        n10 += n11;
                        --i;
                    }
                    else {
                        n10 += n12;
                        --i;
                        if (n2 < n4) {
                            ++j;
                        }
                        else {
                            --j;
                        }
                    }
                    if (i >= 0 && i < this.picxsize && j >= 0 && j < this.picysize) {
                        this.pix[i + j * this.picxsize] = -16777216 + (n5 << 16) + (n6 << 8) + n7;
                    }
                }
            }
        }
        if (n9 > n8) {
            if (j < n4) {
                while (j < n4) {
                    if (n10 <= 0) {
                        n10 += n11;
                        ++j;
                    }
                    else {
                        n10 -= n12;
                        ++j;
                        if (n > n3) {
                            --i;
                        }
                        else {
                            ++i;
                        }
                    }
                    if (i >= 0 && i < this.picxsize && j >= 0 && j < this.picysize) {
                        this.pix[i + j * this.picxsize] = -16777216 + (n5 << 16) + (n6 << 8) + n7;
                    }
                }
            }
            if (j > n4) {
                while (j > n4) {
                    if (n10 <= 0) {
                        n10 += n11;
                        --j;
                    }
                    else {
                        n10 -= n12;
                        --j;
                        if (n > n3) {
                            --i;
                        }
                        else {
                            ++i;
                        }
                    }
                    if (i >= 0 && i < this.picxsize && j >= 0 && j < this.picysize) {
                        this.pix[i + j * this.picxsize] = -16777216 + (n5 << 16) + (n6 << 8) + n7;
                    }
                }
            }
        }
    }
    
    void gzpoly3(final POLY3_2 poly3_2, final int n, final int n2, final int n3, final int n4, final int n5, final int n6, final int n7, final int n8, final int n9) {
        final int[] array = new int[3];
        int n10 = 0;
        array[2] = 2;
        if (poly3_2.y[0] < poly3_2.y[1]) {
            array[0] = 0;
            array[1] = 1;
        }
        else {
            array[array[0] = 1] = 0;
        }
        if (poly3_2.y[array[1]] > poly3_2.y[array[2]]) {
            final int n11 = array[2];
            array[2] = array[1];
            array[1] = n11;
        }
        if (poly3_2.y[array[0]] > poly3_2.y[array[1]]) {
            final int n12 = array[0];
            array[0] = array[1];
            array[1] = n12;
        }
        final int n13 = poly3_2.y[array[0]];
        final int n14 = poly3_2.y[array[2]];
        final int n15 = (poly3_2.x[array[1]] - poly3_2.x[array[0]] << 16) / (poly3_2.y[array[1]] - poly3_2.y[array[0]] + 1);
        final int n16 = (poly3_2.x[array[2]] - poly3_2.x[array[0]] << 16) / (poly3_2.y[array[2]] - poly3_2.y[array[0]] + 1);
        switch (array[0] * 3 + array[2]) {
            case 1: {
                n10 = 6;
                break;
            }
            case 2: {
                n10 = 3;
                break;
            }
            case 3: {
                n10 = 6;
                break;
            }
            case 5: {
                n10 = 5;
                break;
            }
            case 6: {
                n10 = 3;
                break;
            }
            case 7: {
                n10 = 5;
                break;
            }
        }
        if (n16 > n15) {
            n10 ^= 0xFF;
        }
        final int n17 = poly3_2.x[1] - poly3_2.x[0];
        final int abs = Math.abs(poly3_2.y[1] - poly3_2.y[0]);
        if ((n10 & 0x1) == 0x0) {
            int i = poly3_2.y[0];
            if (i + 200 > 0 && i + 200 < this.picysize + 200) {
                this.poly_hullxs[i + 200].x = poly3_2.x[0];
            }
            int n18 = n << 8;
            int n19 = n2 << 8;
            if (i + 200 > 0 && i + 200 < this.picysize + 200) {
                this.poly_hullxs[i + 200].mapx = n18;
            }
            if (i + 200 > 0 && i + 200 < this.picysize + 200) {
                this.poly_hullxs[i + 200].mapy = n19;
            }
            if (abs > 0) {
                final int n20 = (n17 << 8) / abs;
                int n21;
                if (poly3_2.y[1] > poly3_2.y[0]) {
                    n21 = 1;
                }
                else {
                    n21 = -1;
                }
                int n22 = poly3_2.x[0] << 8;
                final int n23 = (n3 - n << 8) / abs;
                final int n24 = (n4 - n2 << 8) / abs;
                while (i != poly3_2.y[1]) {
                    i += n21;
                    n22 += n20;
                    if (i + 200 > 0 && i + 200 < this.picysize + 200) {
                        this.poly_hullxs[i + 200].x = n22 / 256;
                    }
                    n18 += n23;
                    n19 += n24;
                    if (i + 200 > 0 && i + 200 < this.picysize + 200) {
                        this.poly_hullxs[i + 200].mapx = n18;
                    }
                    if (i + 200 > 0 && i + 200 < this.picysize + 200) {
                        this.poly_hullxs[i + 200].mapy = n19;
                    }
                }
            }
        }
        else {
            int j = poly3_2.y[0];
            if (j + 200 > 0 && j + 200 < this.picysize + 200) {
                this.poly_hullxe[j + 200].x = poly3_2.x[0];
            }
            int n25 = n << 8;
            int n26 = n2 << 8;
            if (j + 200 > 0 && j + 200 < this.picysize + 200) {
                this.poly_hullxe[j + 200].mapx = n25;
            }
            if (j + 200 > 0 && j + 200 < this.picysize + 200) {
                this.poly_hullxe[j + 200].mapy = n26;
            }
            if (abs > 0) {
                final int n27 = (n17 << 8) / abs;
                int n28;
                if (poly3_2.y[1] > poly3_2.y[0]) {
                    n28 = 1;
                }
                else {
                    n28 = -1;
                }
                int n29 = poly3_2.x[0] << 8;
                final int n30 = (n3 - n << 8) / abs;
                final int n31 = (n4 - n2 << 8) / abs;
                while (j != poly3_2.y[1]) {
                    j += n28;
                    n29 += n27;
                    if (j + 200 > 0 && j + 200 < this.picysize + 200) {
                        this.poly_hullxe[j + 200].x = n29 >> 8;
                    }
                    n25 += n30;
                    n26 += n31;
                    if (j + 200 > 0 && j + 200 < this.picysize + 200) {
                        this.poly_hullxe[j + 200].mapx = n25;
                    }
                    if (j + 200 > 0 && j + 200 < this.picysize + 200) {
                        this.poly_hullxe[j + 200].mapy = n26;
                    }
                }
            }
        }
        final int n32 = poly3_2.x[2] - poly3_2.x[1];
        final int abs2 = Math.abs(poly3_2.y[2] - poly3_2.y[1]);
        if ((n10 & 0x2) == 0x0) {
            int k = poly3_2.y[1];
            if (k + 200 > 0 && k + 200 < this.picysize + 200) {
                this.poly_hullxs[k + 200].x = poly3_2.x[1];
            }
            int n33 = n3 << 8;
            int n34 = n4 << 8;
            if (k + 200 > 0 && k + 200 < this.picysize + 200) {
                this.poly_hullxs[k + 200].mapx = n33;
            }
            if (k + 200 > 0 && k + 200 < this.picysize + 200) {
                this.poly_hullxs[k + 200].mapy = n34;
            }
            if (abs2 > 0) {
                final int n35 = (n32 << 8) / abs2;
                int n36;
                if (poly3_2.y[2] > poly3_2.y[1]) {
                    n36 = 1;
                }
                else {
                    n36 = -1;
                }
                int n37 = poly3_2.x[1] << 8;
                final int n38 = (n5 - n3 << 8) / abs2;
                final int n39 = (n6 - n4 << 8) / abs2;
                while (k != poly3_2.y[2]) {
                    k += n36;
                    n37 += n35;
                    if (k + 200 > 0 && k + 200 < this.picysize + 200) {
                        this.poly_hullxs[k + 200].x = n37 >> 8;
                    }
                    n33 += n38;
                    n34 += n39;
                    if (k + 200 > 0 && k + 200 < this.picysize + 200) {
                        this.poly_hullxs[k + 200].mapx = n33;
                    }
                    if (k + 200 > 0 && k + 200 < this.picysize + 200) {
                        this.poly_hullxs[k + 200].mapy = n34;
                    }
                }
            }
        }
        else {
            int l = poly3_2.y[1];
            if (l + 200 > 0 && l + 200 < this.picysize + 200) {
                this.poly_hullxe[l + 200].x = poly3_2.x[1];
            }
            int n40 = n3 << 8;
            int n41 = n4 << 8;
            if (l + 200 > 0 && l + 200 < this.picysize + 200) {
                this.poly_hullxe[l + 200].mapx = n40;
            }
            if (l + 200 > 0 && l + 200 < this.picysize + 200) {
                this.poly_hullxe[l + 200].mapy = n41;
            }
            if (abs2 > 0) {
                final int n42 = (n32 << 8) / abs2;
                int n43;
                if (poly3_2.y[2] > poly3_2.y[1]) {
                    n43 = 1;
                }
                else {
                    n43 = -1;
                }
                int n44 = poly3_2.x[1] << 8;
                final int n45 = (n5 - n3 << 8) / abs2;
                final int n46 = (n6 - n4 << 8) / abs2;
                while (l != poly3_2.y[2]) {
                    l += n43;
                    n44 += n42;
                    if (l + 200 > 0 && l + 200 < this.picysize + 200) {
                        this.poly_hullxe[l + 200].x = n44 >> 8;
                    }
                    n40 += n45;
                    n41 += n46;
                    if (l + 200 > 0 && l + 200 < this.picysize + 200) {
                        this.poly_hullxe[l + 200].mapx = n40;
                    }
                    if (l + 200 > 0 && l + 200 < this.picysize + 200) {
                        this.poly_hullxe[l + 200].mapy = n41;
                    }
                }
            }
        }
        final int n47 = poly3_2.x[0] - poly3_2.x[2];
        final int abs3 = Math.abs(poly3_2.y[0] - poly3_2.y[2]);
        if ((n10 & 0x4) == 0x0) {
            int n48 = poly3_2.y[2];
            if (n48 + 200 > 0 && n48 + 200 < this.picysize + 200) {
                this.poly_hullxs[n48 + 200].x = poly3_2.x[2];
            }
            int n49 = n5 << 8;
            int n50 = n6 << 8;
            if (n48 + 200 > 0 && n48 + 200 < this.picysize + 200) {
                this.poly_hullxs[n48 + 200].mapx = n49;
            }
            if (n48 + 200 > 0 && n48 + 200 < this.picysize + 200) {
                this.poly_hullxs[n48 + 200].mapy = n50;
            }
            if (abs3 > 0) {
                final int n51 = (n47 << 8) / abs3;
                int n52;
                if (poly3_2.y[0] > poly3_2.y[2]) {
                    n52 = 1;
                }
                else {
                    n52 = -1;
                }
                int n53 = poly3_2.x[2] << 8;
                final int n54 = (n - n5 << 8) / abs3;
                final int n55 = (n2 - n6 << 8) / abs3;
                while (n48 != poly3_2.y[0]) {
                    n48 += n52;
                    n53 += n51;
                    if (n48 + 200 > 0 && n48 + 200 < this.picysize + 200) {
                        this.poly_hullxs[n48 + 200].x = n53 >> 8;
                    }
                    n49 += n54;
                    n50 += n55;
                    if (n48 + 200 > 0 && n48 + 200 < this.picysize + 200) {
                        this.poly_hullxs[n48 + 200].mapx = n49;
                    }
                    if (n48 + 200 > 0 && n48 + 200 < this.picysize + 200) {
                        this.poly_hullxs[n48 + 200].mapy = n50;
                    }
                }
            }
        }
        else {
            int n56 = poly3_2.y[2];
            if (n56 + 200 > 0 && n56 + 200 < this.picysize + 200) {
                this.poly_hullxe[n56 + 200].x = poly3_2.x[2];
            }
            int n57 = n5 << 8;
            int n58 = n6 << 8;
            if (n56 + 200 > 0 && n56 + 200 < this.picysize + 200) {
                this.poly_hullxe[n56 + 200].mapx = n57;
            }
            if (n56 + 200 > 0 && n56 + 200 < this.picysize + 200) {
                this.poly_hullxe[n56 + 200].mapy = n58;
            }
            if (abs3 > 0) {
                final int n59 = (n47 << 8) / abs3;
                int n60;
                if (poly3_2.y[0] > poly3_2.y[2]) {
                    n60 = 1;
                }
                else {
                    n60 = -1;
                }
                int n61 = poly3_2.x[2] << 8;
                final int n62 = (n - n5 << 8) / abs3;
                final int n63 = (n2 - n6 << 8) / abs3;
                while (n56 != poly3_2.y[0]) {
                    n56 += n60;
                    n61 += n59;
                    if (n56 + 200 > 0 && n56 + 200 < this.picysize + 200) {
                        this.poly_hullxe[n56 + 200].x = n61 >> 8;
                    }
                    n57 += n62;
                    n58 += n63;
                    if (n56 + 200 > 0 && n56 + 200 < this.picysize + 200) {
                        this.poly_hullxe[n56 + 200].mapx = n57;
                    }
                    if (n56 + 200 > 0 && n56 + 200 < this.picysize + 200) {
                        this.poly_hullxe[n56 + 200].mapy = n58;
                    }
                }
            }
        }
        for (int n64 = n13; n64 <= n14; ++n64) {
            if (n64 > 0 && n64 < this.picysize + 100) {
                final int n65 = n64 * this.picxsize;
                int n66 = this.poly_hullxs[n64 + 200].mapx + 127 << 8;
                int n67 = this.poly_hullxs[n64 + 200].mapy + 127 << 8;
                final int n68 = this.poly_hullxe[n64 + 200].x - this.poly_hullxs[n64 + 200].x;
                int n69;
                int n70;
                if (n68 > 0) {
                    n69 = (this.poly_hullxe[n64 + 200].mapx - this.poly_hullxs[n64 + 200].mapx << 8) / n68;
                    n70 = (this.poly_hullxe[n64 + 200].mapy - this.poly_hullxs[n64 + 200].mapy << 8) / n68;
                }
                else {
                    n69 = 0;
                    n70 = 0;
                }
                for (int x = this.poly_hullxs[n64 + 200].x; x <= this.poly_hullxe[n64 + 200].x; ++x) {
                    if (x > 0 && x < this.picxsize && n64 > 0 && n64 < this.picysize && n67 >> 16 > this.zbuffer[x + n65]) {
                        int n71 = n66 >> 16;
                        if (n71 > 255) {
                            n71 = 255;
                        }
                        int n72 = (n7 >> 1) + (n71 >> 1);
                        int n73 = (n8 >> 1) + (n71 >> 1);
                        int n74 = (n9 >> 1) + (n71 >> 1);
                        if (n72 > 255) {
                            n72 = 255;
                        }
                        if (n73 > 255) {
                            n73 = 255;
                        }
                        if (n74 > 255) {
                            n74 = 255;
                        }
                        this.pix[x + n65] = -16777216 + (n72 << 16) + (n73 << 8) + n74;
                        this.zbuffer[x + n65] = n67 >> 16;
                    }
                    n66 += n69;
                    n67 += n70;
                }
            }
        }
    }
    
    public boolean handleEvent(final Event event) {
        if (this.explode) {
            return super.handleEvent(event);
        }
        switch (event.id) {
            case 501: {
                this.getAppletContext().showDocument(this.urls[this.partnr], this.OPTION);
                this.explode = true;
                for (int i = 0; i < this.cnt2 + 1; ++i) {
                    for (int j = 0; j < this.object1[i].numPoints; ++j) {
                        this.object1[i].point[j].yDeg = this.object1[i].yDeg;
                    }
                }
                return true;
            }
            case 503: {
                this.partnr = (this.partnr + 1) % 6;
                return true;
            }
            default: {
                return super.handleEvent(event);
            }
        }
    }
    
    public void Line_poly(final polygon polygon, final int n, final int n2, final int n3) {
        polygon.normal.calcLength();
        this.line(polygon.p1.x2d, polygon.p1.y2d, polygon.p2.x2d, polygon.p2.y2d, n, n2, n3);
        this.line(polygon.p2.x2d, polygon.p2.y2d, polygon.p3.x2d, polygon.p3.y2d, n, n2, n3);
        this.line(polygon.p3.x2d, polygon.p3.y2d, polygon.p1.x2d, polygon.p1.y2d, n, n2, n3);
    }
    
    public void Shade_poly(final polygon polygon) {
        int n = 0;
        int n2 = 0;
        int n3 = 0;
        final POLY3_2 poly3_2 = new POLY3_2();
        poly3_2.x[0] = polygon.p1.x2d;
        poly3_2.y[0] = polygon.p1.y2d;
        poly3_2.x[1] = polygon.p2.x2d;
        poly3_2.y[1] = polygon.p2.y2d;
        poly3_2.x[2] = polygon.p3.x2d;
        poly3_2.y[2] = polygon.p3.y2d;
        int n4 = 0;
        for (int i = 0; i < 3; ++i) {
            if (poly3_2.x[i] < 0 || poly3_2.x[i] > this.picxsize || poly3_2.y[i] < 0 || poly3_2.y[i] > this.picysize) {
                ++n4;
            }
        }
        if (n4 < 3) {
            polygon.p1.normal.calcLength();
            polygon.p2.normal.calcLength();
            polygon.p3.normal.calcLength();
            final double n5 = 1.0 + polygon.p1.normal.length;
            final double n6 = 1.0 + polygon.p2.normal.length;
            final double n7 = 1.0 + polygon.p3.normal.length;
            for (int j = 0; j < 1; ++j) {
                final int n8 = (int)((this.dotProduct(polygon.p1.normal, this.camera.lights[j]) << 8) / (this.camera.lights[j].length * n5));
                final int n9 = (int)((this.dotProduct(polygon.p2.normal, this.camera.lights[j]) << 8) / (this.camera.lights[j].length * n6));
                final int n10 = (int)((this.dotProduct(polygon.p3.normal, this.camera.lights[j]) << 8) / (this.camera.lights[j].length * n7));
                if (n8 > 0) {
                    n += n8;
                }
                if (n9 > 0) {
                    n2 += n9;
                }
                if (n10 > 0) {
                    n3 += n10;
                }
            }
            if (n > 255) {
                n = 255;
            }
            if (n2 > 255) {
                n2 = 255;
            }
            if (n3 > 255) {
                n3 = 255;
            }
            this.gzpoly3(poly3_2, polygon.color + n, polygon.p1.z3d, polygon.color + n2, polygon.p2.z3d, polygon.color + n3, polygon.p3.z3d, polygon.red, polygon.green, polygon.blue);
        }
    }
    
    public void init() {
        this.setBackground(this.c);
        final int n = 30;
        this.offGraphics = null;
        this.delay = ((n > 0) ? (1000 / n) : 100);
        this.picxsize = 240;
        this.picysize = 200;
        this.OPTION = this.getParameter("Target");
        if (this.OPTION == null) {
            this.OPTION = "_self";
        }
        for (int i = 0; i < 6; ++i) {
            this.msgs[i] = this.getParameter("Dest" + (i + 1));
            String s = this.getParameter("URL" + (i + 1));
            if (s.substring(0, 4).compareTo("http") != 0) {
                s = String.valueOf(this.getCodeBase().toString()) + s;
            }
            try {
                this.urls[i] = new URL(s);
            }
            catch (MalformedURLException ex) {
                this.getAppletContext().showStatus("Invalide URL");
            }
        }
        final String parameter = this.getParameter("Textred-Value");
        final String parameter2 = this.getParameter("Textgreen-Value");
        final String parameter3 = this.getParameter("Textblue-Value");
        int int1 = 255;
        int int2 = 255;
        int int3 = 255;
        if (parameter != null) {
            int1 = Integer.parseInt(parameter);
        }
        if (parameter2 != null) {
            int2 = Integer.parseInt(parameter2);
        }
        if (parameter3 != null) {
            int3 = Integer.parseInt(parameter3);
        }
        if (int1 < 0 || int1 > 255) {
            int1 = 255;
        }
        if (int2 < 0 || int2 > 255) {
            int2 = 255;
        }
        if (int3 < 0 || int3 > 255) {
            int3 = 255;
        }
        this.c = new Color(int1 / 255.0f, int2 / 255.0f, int3 / 255.0f);
        final String parameter4 = this.getParameter("Selectionred-Value");
        final String parameter5 = this.getParameter("Selectionreen-Value");
        final String parameter6 = this.getParameter("Selectionblue-Value");
        if (parameter4 != null) {
            this.sred = Integer.parseInt(parameter4);
        }
        if (parameter5 != null) {
            this.sgreen = Integer.parseInt(parameter5);
        }
        if (parameter6 != null) {
            this.sblue = Integer.parseInt(parameter6);
        }
        if (this.sred < 0 || this.sred > 255) {
            this.sred = 255;
        }
        if (this.sgreen < 0 || this.sgreen > 255) {
            this.sgreen = 255;
        }
        if (this.sblue < 0 || this.sblue > 255) {
            this.sblue = 255;
        }
        int int6;
        int int5;
        int int4 = int5 = (int6 = 0);
        final String parameter7 = this.getParameter("Backgroundred-Value");
        final String parameter8 = this.getParameter("Backgroundgreen-Value");
        final String parameter9 = this.getParameter("Backgroundblue-Value");
        if (parameter7 != null) {
            int5 = Integer.parseInt(parameter7);
        }
        if (parameter8 != null) {
            int4 = Integer.parseInt(parameter8);
        }
        if (parameter9 != null) {
            int6 = Integer.parseInt(parameter9);
        }
        if (int5 < 0 || int5 > 255) {
            int5 = 0;
        }
        if (int4 < 0 || int4 > 255) {
            int4 = 0;
        }
        if (int6 < 0 || int6 > 255) {
            int6 = 0;
        }
        this.backcolor = new Color(int5 / 255.0f, int4 / 255.0f, int6 / 255.0f);
        this.pix = new int[this.picxsize * this.picysize];
        this.zbuffer = new int[this.picxsize * this.picysize];
        (this.source = new MemoryImageSource(this.picxsize, this.picysize, this.pix, 0, this.picxsize)).setAnimated(true);
        this.source.setFullBufferUpdates(true);
        this.camera.setLight(0, 0, 0, 120);
        this.camera.lights[0].calcLength();
        this.Img = this.createImage(this.source);
        for (int j = 0; j < 4000; ++j) {
            this.poly_hullxs[j] = new PL();
            this.poly_hullxe[j] = new PL();
        }
        this.cnt = 0;
        final Font font = new Font("Arial", 1, 10);
        this.getFontMetrics(font);
        this.setFont(font);
        this.partnr = 0;
        this.getAppletContext().showStatus("Applet from OtakInc pure Realtime!");
        this.LoadData();
        for (int k = 0; k < 500; ++k) {
            this.scnt[k] = 0;
            this.adder[k] = -4 + (int)(Math.random() * 9.0);
            this.xadder[k] = -8 + (int)(Math.random() * 17.0);
            this.yadder[k] = -8 + (int)(Math.random() * 17.0);
            this.zadder[k] = -8 + (int)(Math.random() * 17.0);
            final int[] xs = this.xs;
            final int n2 = k;
            final int[] ys = this.ys;
            final int n3 = k;
            final int[] zs = this.zs;
            final int n4 = k;
            final boolean b = false;
            zs[n4] = (b ? 1 : 0);
            xs[n2] = (ys[n3] = (b ? 1 : 0));
        }
    }
    
    public void LoadData() {
        int i = 0;
        try {
            final ZipInputStream zipInputStream = new ZipInputStream((InputStream)new URL(this.getCodeBase(), "brain.dat").getContent());
            zipInputStream.getNextEntry();
            final DataInputStream dataInputStream = new DataInputStream(zipInputStream);
            this.cnt2 = dataInputStream.readInt();
            ++this.cnt2;
            this.object1 = new obj3d[this.cnt2 + 2];
            while (i < this.cnt2) {
                final int int1 = dataInputStream.readInt();
                final int int2 = dataInputStream.readInt();
                int j = 0;
                int k = 0;
                this.object1[i] = new obj3d(0, 0, 6144, int1, int2);
                while (j < int1) {
                    this.object1[i].addLocalPoint(dataInputStream.readInt(), dataInputStream.readInt(), -dataInputStream.readInt());
                    ++j;
                }
                while (k < int2) {
                    this.object1[i].addLocalPoly(dataInputStream.readInt(), dataInputStream.readInt(), dataInputStream.readInt(), dataInputStream.readInt(), dataInputStream.readInt(), dataInputStream.readInt());
                    ++k;
                }
                ++i;
            }
            dataInputStream.close();
        }
        catch (IOException ex) {
            this.getAppletContext().showStatus("FILE-ERROR");
        }
        for (int l = 0; l < this.cnt2; ++l) {
            this.object1[l].setGNormals();
            this.object1[l].localRotate(0, 270, 0, this.sincos);
            for (int n = 0; n < this.object1[l].numPoints; ++n) {
                this.object1[l].point[n].xDeg = 0;
                this.object1[l].point[n].yDeg = 270;
            }
        }
    }
    
    public void start() {
        if (this.killme == null) {
            (this.killme = new Thread(this)).setPriority(1);
            this.killme.start();
        }
    }
    
    public void update(final Graphics graphics) {
        this.paint(graphics);
    }
    
    public void ShowBrain() {
        for (int i = 0; i < this.picysize; ++i) {
            final int n = i * this.picxsize;
            for (int j = 0; j < this.picxsize; ++j) {
                this.pix[j + n] = 0;
                this.zbuffer[j + n] = -16711680;
            }
        }
        for (int k = 0; k < this.cnt2; ++k) {
            for (int l = 0; l < this.object1[k].numPolys; ++l) {
                if (this.explode) {
                    this.object1[k].poly[l].localRotate(this.scnt[l], this.scnt[l], this.scnt[l], this.sincos);
                    this.object1[k].poly[l].translate(this.xs[l], this.ys[l], this.zs[l]);
                }
                if (this.object1[k].poly[l].normal.rotoZ > 0) {
                    this.Shade_poly(this.object1[k].poly[l]);
                }
                if (this.explode) {
                    this.object1[k].poly[l].localRotate(-this.scnt[l], -this.scnt[l], -this.scnt[l], this.sincos);
                    this.object1[k].poly[l].translate(-this.xs[l], -this.ys[l], -this.zs[l]);
                }
            }
            if (!this.explode) {
                this.object1[k].localRotate(0, -3, 0, this.sincos);
            }
        }
        if (this.explode) {
            for (int n2 = 0; n2 < 500; ++n2) {
                final int[] scnt = this.scnt;
                final int n3 = n2;
                scnt[n3] += this.adder[n2];
                final int[] xs = this.xs;
                final int n4 = n2;
                xs[n4] += this.xadder[n2];
                final int[] ys = this.ys;
                final int n5 = n2;
                ys[n5] += this.yadder[n2];
                final int[] zs = this.zs;
                final int n6 = n2;
                zs[n6] += this.zadder[n2];
            }
        }
        if (!this.explode) {
            for (int n7 = 0; n7 < this.object1[this.partnr].numPolys; ++n7) {
                if (this.object1[this.partnr].poly[n7].normal.rotoZ > 0) {
                    this.Line_poly(this.object1[this.partnr].poly[n7], this.sred, this.sgreen, this.sblue);
                }
            }
        }
        this.camera.lights[0].localRotate(0, 0, 6, this.sincos);
        this.camera.lights[0].calcLength();
    }
    
    public void paint(final Graphics graphics) {
        final Dimension size = this.size();
        if (this.offGraphics == null || size.width != this.offDimension.width || size.height != this.offDimension.height) {
            this.offDimension = size;
            this.offImage = this.createImage(size.width, size.height);
            (this.offGraphics = this.offImage.getGraphics()).setFont(new Font("Arial", 1, 10));
        }
    }
    
    public void run() {
        long currentTimeMillis = System.currentTimeMillis();
        final Graphics graphics = this.getGraphics();
        while (this.killme == Thread.currentThread()) {
            ++this.frameNumber;
            final Dimension size = this.size();
            if (this.offGraphics == null || size.width != this.offDimension.width || size.height != this.offDimension.height) {
                this.offDimension = size;
                this.offImage = this.createImage(size.width, size.height);
                (this.offGraphics = this.offImage.getGraphics()).setFont(new Font("Arial", 1, 10));
            }
            this.offGraphics.setColor(Color.black);
            this.offGraphics.fillRect(0, 0, size.width, size.height);
            this.offGraphics.setColor(Color.black);
            this.ShowBrain();
            this.offGraphics.drawImage(this.Img, 0, 0, 240, 200, this.backcolor, this);
            this.offGraphics.setColor(this.c);
            this.offGraphics.drawString("Destination : " + this.msgs[this.partnr], 1, 185);
            this.offGraphics.setColor(Color.white);
            this.offGraphics.setFont(new Font("Arial", 1, 10));
            this.offGraphics.drawString("Demo-version", 1, 15);
            graphics.drawImage(this.offImage, 0, 0, this);
            this.Img.flush();
            try {
                currentTimeMillis += this.delay;
                Thread.sleep(Math.max(0L, currentTimeMillis - System.currentTimeMillis()));
            }
            catch (InterruptedException ex) {}
        }
    }
    
    public void stop() {
        (this.killme = null).stop();
        this.offGraphics = null;
        this.offImage = null;
    }
    
    public Brainlink() {
        this.scnt = new int[500];
        this.adder = new int[500];
        this.xadder = new int[500];
        this.yadder = new int[500];
        this.zadder = new int[500];
        this.xs = new int[500];
        this.ys = new int[500];
        this.zs = new int[500];
        this.frameNumber = -1;
        this.camera = new viewPoint(1);
        this.sincos = new SinCos();
        this.tracer = new MediaTracker(this);
        this.c = new Color(0.0f, 0.0f, 0.0f);
        this.explode = false;
        this.object1 = new obj3d[2];
        this.poly_hullxs = new PL[4000];
        this.poly_hullxe = new PL[4000];
        this.msgs = new String[6];
        this.urlnames = new String[6];
        this.urls = new URL[6];
        this.display = 1;
        this.sred = 255;
        this.sgreen = 255;
        this.sblue = 255;
    }
}
