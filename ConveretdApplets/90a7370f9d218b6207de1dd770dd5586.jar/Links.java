import java.awt.Component;
import java.awt.image.ImageObserver;
import java.io.IOException;
import java.io.DataInputStream;
import java.io.InputStream;
import java.awt.Event;
import java.awt.Font;
import java.awt.image.ImageProducer;
import java.net.MalformedURLException;
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

public class Links extends Applet implements Runnable
{
    int i;
    int i1;
    int x;
    int y;
    int color;
    int timer;
    int[] pix;
    int[] zbuffer;
    int cnt;
    int cnt1;
    int cnt2;
    int cnter;
    int pr;
    int pb;
    int pg;
    Image Img;
    double[] Stab;
    double[] Ctab;
    int nroflights;
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
    boolean pos;
    boolean move;
    int delay;
    int nrOfStars;
    int picxsize;
    int picysize;
    Color c;
    int xpos;
    int ypos;
    point3d origin;
    point3d transl;
    obj3d[] object1;
    obj3d[] stars;
    int[] wx;
    int[] wy;
    String[] text;
    String OPTION;
    PL[] poly_hullxs;
    PL[] poly_hullxe;
    int display;
    int partnr;
    URL[] urls;
    
    int dotProduct(final point3d point3d, final point3d point3d2) {
        return point3d.rotoX * point3d2.rotoX + point3d.rotoY * point3d2.rotoY + point3d.rotoZ * point3d2.rotoZ;
    }
    
    void zpoly3(final POLY3_2 poly3_2, final int n, final int n2, final int n3, final int n4, final int n5, final int n6) {
        final int[] array = new int[3];
        int n7 = 0;
        array[2] = 2;
        if (poly3_2.y[0] < poly3_2.y[1]) {
            array[0] = 0;
            array[1] = 1;
        }
        else {
            array[array[0] = 1] = 0;
        }
        if (poly3_2.y[array[1]] > poly3_2.y[array[2]]) {
            final int n8 = array[2];
            array[2] = array[1];
            array[1] = n8;
        }
        if (poly3_2.y[array[0]] > poly3_2.y[array[1]]) {
            final int n9 = array[0];
            array[0] = array[1];
            array[1] = n9;
        }
        final int n10 = poly3_2.y[array[0]];
        final int n11 = poly3_2.y[array[2]];
        final int n12 = (poly3_2.x[array[1]] - poly3_2.x[array[0]] << 16) / (poly3_2.y[array[1]] - poly3_2.y[array[0]] + 1);
        final int n13 = (poly3_2.x[array[2]] - poly3_2.x[array[0]] << 16) / (poly3_2.y[array[2]] - poly3_2.y[array[0]] + 1);
        switch (array[0] * 3 + array[2]) {
            case 1: {
                n7 = 6;
                break;
            }
            case 2: {
                n7 = 3;
                break;
            }
            case 3: {
                n7 = 6;
                break;
            }
            case 5: {
                n7 = 5;
                break;
            }
            case 6: {
                n7 = 3;
                break;
            }
            case 7: {
                n7 = 5;
                break;
            }
        }
        if (n13 > n12) {
            n7 ^= 0xFF;
        }
        final int n14 = poly3_2.x[1] - poly3_2.x[0];
        final int abs = Math.abs(poly3_2.y[1] - poly3_2.y[0]);
        if ((n7 & 0x1) == 0x0) {
            int i = poly3_2.y[0];
            if (i + 200 > 0 && i + 200 < this.picysize + 200) {
                this.poly_hullxs[i + 200].x = poly3_2.x[0];
            }
            int n15 = n << 8;
            if (i + 200 > 0 && i + 200 < this.picysize + 200) {
                this.poly_hullxs[i + 200].mapx = n15;
            }
            if (abs > 0) {
                final int n16 = (n14 << 8) / abs;
                int n17;
                if (poly3_2.y[1] > poly3_2.y[0]) {
                    n17 = 1;
                }
                else {
                    n17 = -1;
                }
                int n18 = poly3_2.x[0] << 8;
                final int n19 = (n2 - n << 8) / abs;
                while (i != poly3_2.y[1]) {
                    i += n17;
                    n18 += n16;
                    if (i + 200 > 0 && i + 200 < this.picysize + 200) {
                        this.poly_hullxs[i + 200].x = n18 >> 8;
                    }
                    n15 += n19;
                    if (i + 200 > 0 && i + 200 < this.picysize + 200) {
                        this.poly_hullxs[i + 200].mapx = n15;
                    }
                }
            }
        }
        else {
            int j = poly3_2.y[0];
            if (j + 200 > 0 && j + 200 < this.picysize + 200) {
                this.poly_hullxe[j + 200].x = poly3_2.x[0];
            }
            int n20 = n << 8;
            if (j + 200 > 0 && j + 200 < this.picysize + 200) {
                this.poly_hullxe[j + 200].mapx = n20;
            }
            if (abs > 0) {
                final int n21 = (n14 << 8) / abs;
                int n22;
                if (poly3_2.y[1] > poly3_2.y[0]) {
                    n22 = 1;
                }
                else {
                    n22 = -1;
                }
                int n23 = poly3_2.x[0] << 8;
                final int n24 = (n2 - n << 8) / abs;
                while (j != poly3_2.y[1]) {
                    j += n22;
                    n23 += n21;
                    if (j + 200 > 0 && j + 200 < this.picysize + 200) {
                        this.poly_hullxe[j + 200].x = n23 >> 8;
                    }
                    n20 += n24;
                    if (j + 200 > 0 && j + 200 < this.picysize + 200) {
                        this.poly_hullxe[j + 200].mapx = n20;
                    }
                }
            }
        }
        final int n25 = poly3_2.x[2] - poly3_2.x[1];
        final int abs2 = Math.abs(poly3_2.y[2] - poly3_2.y[1]);
        if ((n7 & 0x2) == 0x0) {
            int k = poly3_2.y[1];
            if (k + 200 > 0 && k + 200 < this.picysize + 200) {
                this.poly_hullxs[k + 200].x = poly3_2.x[1];
            }
            int n26 = n2 << 8;
            if (k + 200 > 0 && k + 200 < this.picysize + 200) {
                this.poly_hullxs[k + 200].mapx = n26;
            }
            if (abs2 > 0) {
                final int n27 = (n25 << 8) / abs2;
                int n28;
                if (poly3_2.y[2] > poly3_2.y[1]) {
                    n28 = 1;
                }
                else {
                    n28 = -1;
                }
                int n29 = poly3_2.x[1] << 8;
                final int n30 = (n3 - n2 << 8) / abs2;
                while (k != poly3_2.y[2]) {
                    k += n28;
                    n29 += n27;
                    if (k + 200 > 0 && k + 200 < this.picysize + 200) {
                        this.poly_hullxs[k + 200].x = n29 >> 8;
                    }
                    n26 += n30;
                    if (k + 200 > 0 && k + 200 < this.picysize + 200) {
                        this.poly_hullxs[k + 200].mapx = n26;
                    }
                }
            }
        }
        else {
            int l = poly3_2.y[1];
            if (l + 200 > 0 && l + 200 < this.picysize + 200) {
                this.poly_hullxe[l + 200].x = poly3_2.x[1];
            }
            int n31 = n2 << 8;
            if (l + 200 > 0 && l + 200 < this.picysize + 200) {
                this.poly_hullxe[l + 200].mapx = n31;
            }
            if (abs2 > 0) {
                final int n32 = (n25 << 8) / abs2;
                int n33;
                if (poly3_2.y[2] > poly3_2.y[1]) {
                    n33 = 1;
                }
                else {
                    n33 = -1;
                }
                int n34 = poly3_2.x[1] << 8;
                final int n35 = (n3 - n2 << 8) / abs2;
                while (l != poly3_2.y[2]) {
                    l += n33;
                    n34 += n32;
                    if (l + 200 > 0 && l + 200 < this.picysize + 200) {
                        this.poly_hullxe[l + 200].x = n34 >> 8;
                    }
                    n31 += n35;
                    if (l + 200 > 0 && l + 200 < this.picysize + 200) {
                        this.poly_hullxe[l + 200].mapx = n31;
                    }
                }
            }
        }
        final int n36 = poly3_2.x[0] - poly3_2.x[2];
        final int abs3 = Math.abs(poly3_2.y[0] - poly3_2.y[2]);
        if ((n7 & 0x4) == 0x0) {
            int n37 = poly3_2.y[2];
            if (n37 + 200 > 0 && n37 + 200 < this.picysize + 200) {
                this.poly_hullxs[n37 + 200].x = poly3_2.x[2];
            }
            int n38 = n3 << 8;
            if (n37 + 200 > 0 && n37 + 200 < this.picysize + 200) {
                this.poly_hullxs[n37 + 200].mapx = n38;
            }
            if (abs3 > 0) {
                final int n39 = (n36 << 8) / abs3;
                int n40;
                if (poly3_2.y[0] > poly3_2.y[2]) {
                    n40 = 1;
                }
                else {
                    n40 = -1;
                }
                int n41 = poly3_2.x[2] << 8;
                final int n42 = (n - n3 << 8) / abs3;
                while (n37 != poly3_2.y[0]) {
                    n37 += n40;
                    n41 += n39;
                    if (n37 + 200 > 0 && n37 + 200 < this.picysize + 200) {
                        this.poly_hullxs[n37 + 200].x = n41 >> 8;
                    }
                    n38 += n42;
                    if (n37 + 200 > 0 && n37 + 200 < this.picysize + 200) {
                        this.poly_hullxs[n37 + 200].mapx = n38;
                    }
                }
            }
        }
        else {
            int n43 = poly3_2.y[2];
            if (n43 + 200 > 0 && n43 + 200 < this.picysize + 200) {
                this.poly_hullxe[n43 + 200].x = poly3_2.x[2];
            }
            int n44 = n3 << 8;
            if (n43 + 200 > 0 && n43 + 200 < this.picysize + 200) {
                this.poly_hullxe[n43 + 200].mapx = n44;
            }
            if (abs3 > 0) {
                final int n45 = (n36 << 8) / abs3;
                int n46;
                if (poly3_2.y[0] > poly3_2.y[2]) {
                    n46 = 1;
                }
                else {
                    n46 = -1;
                }
                int n47 = poly3_2.x[2] << 8;
                final int n48 = (n - n3 << 8) / abs3;
                while (n43 != poly3_2.y[0]) {
                    n43 += n46;
                    n47 += n45;
                    if (n43 + 200 > 0 && n43 + 200 < this.picysize + 200) {
                        this.poly_hullxe[n43 + 200].x = n47 >> 8;
                    }
                    n44 += n48;
                    if (n43 + 200 > 0 && n43 + 200 < this.picysize + 200) {
                        this.poly_hullxe[n43 + 200].mapx = n44;
                    }
                }
            }
        }
        for (int n49 = n10; n49 <= n11; ++n49) {
            if (n49 > 0 && n49 < this.picysize + 100) {
                final int n50 = n49 * this.picxsize;
                int n51 = this.poly_hullxs[n49 + 200].mapx + 127 << 8;
                final int n52 = this.poly_hullxe[n49 + 200].x - this.poly_hullxs[n49 + 200].x;
                int n53;
                if (n52 > 0) {
                    n53 = (this.poly_hullxe[n49 + 200].mapx - this.poly_hullxs[n49 + 200].mapx << 8) / n52;
                }
                else {
                    n53 = 0;
                }
                for (int x = this.poly_hullxs[n49 + 200].x; x <= this.poly_hullxe[n49 + 200].x; ++x) {
                    if (x > 0 && x < this.picxsize && n49 > 0 && n49 < this.picysize && n51 >> 16 < this.zbuffer[x + n50]) {
                        this.pix[x + n50] = -16777216 + (n4 << 16) + (n5 << 8) + n6;
                        this.zbuffer[x + n50] = n51 >> 16;
                    }
                    n51 += n53;
                }
            }
        }
    }
    
    public void Circle(final int n, final int n2, final int n3, final int n4, final int n5, final int n6) {
        int i = 0;
        int n7 = n3;
        if (n + i > 0 && n + i < this.picxsize && n2 + n7 > 0 && n2 + n7 < this.picysize) {
            this.pix[n + i + (n2 + n7) * this.picxsize] = -16777216 + (n4 << 16) + (n5 << 8) + n6;
        }
        if (n - i > 0 && n - i < this.picxsize && n2 + n7 > 0 && n2 + n7 < this.picysize) {
            this.pix[n - i + (n2 + n7) * this.picxsize] = -16777216 + (n4 << 16) + (n5 << 8) + n6;
        }
        if (n + i > 0 && n + i < this.picxsize && n2 - n7 > 0 && n2 - n7 < this.picysize) {
            this.pix[n + i + (n2 - n7) * this.picxsize] = -16777216 + (n4 << 16) + (n5 << 8) + n6;
        }
        if (n - i > 0 && n - i < this.picxsize && n2 - n7 > 0 && n2 - n7 < this.picysize) {
            this.pix[n - i + (n2 - n7) * this.picxsize] = -16777216 + (n4 << 16) + (n5 << 8) + n6;
        }
        if (n + n7 > 0 && n + n7 < this.picxsize && n2 + i > 0 && n2 + i < this.picysize) {
            this.pix[n + n7 + (n2 + i) * this.picxsize] = -16777216 + (n4 << 16) + (n5 << 8) + n6;
        }
        if (n - n7 > 0 && n - n7 < this.picxsize && n2 + i > 0 && n2 + i < this.picysize) {
            this.pix[n - n7 + (n2 + i) * this.picxsize] = -16777216 + (n4 << 16) + (n5 << 8) + n6;
        }
        if (n + n7 > 0 && n + n7 < this.picxsize && n2 - i > 0 && n2 - i < this.picysize) {
            this.pix[n + n7 + (n2 - i) * this.picxsize] = -16777216 + (n4 << 16) + (n5 << 8) + n6;
        }
        if (n - n7 > 0 && n - n7 < this.picxsize && n2 - i > 0 && n2 - i < this.picysize) {
            this.pix[n - n7 + (n2 - i) * this.picxsize] = -16777216 + (n4 << 16) + (n5 << 8) + n6;
        }
        int n8 = 1 - n3;
        while (i < n7) {
            if (n8 < 0) {
                ++i;
            }
            else {
                ++i;
                --n7;
            }
            if (n8 < 0) {
                n8 = n8 + 2 * i + 1;
            }
            else {
                n8 = n8 + 2 * (i - n7) + 1;
            }
            if (n + i > 0 && n + i < this.picxsize && n2 + n7 > 0 && n2 + n7 < this.picysize) {
                this.pix[n + i + (n2 + n7) * this.picxsize] = -16777216 + (n4 << 16) + (n5 << 8) + n6;
            }
            if (n - i > 0 && n - i < this.picxsize && n2 + n7 > 0 && n2 + n7 < this.picysize) {
                this.pix[n - i + (n2 + n7) * this.picxsize] = -16777216 + (n4 << 16) + (n5 << 8) + n6;
            }
            if (n + i > 0 && n + i < this.picxsize && n2 - n7 > 0 && n2 - n7 < this.picysize) {
                this.pix[n + i + (n2 - n7) * this.picxsize] = -16777216 + (n4 << 16) + (n5 << 8) + n6;
            }
            if (n - i > 0 && n - i < this.picxsize && n2 - n7 > 0 && n2 - n7 < this.picysize) {
                this.pix[n - i + (n2 - n7) * this.picxsize] = -16777216 + (n4 << 16) + (n5 << 8) + n6;
            }
            if (n + n7 > 0 && n + n7 < this.picxsize && n2 + i > 0 && n2 + i < this.picysize) {
                this.pix[n + n7 + (n2 + i) * this.picxsize] = -16777216 + (n4 << 16) + (n5 << 8) + n6;
            }
            if (n - n7 > 0 && n - n7 < this.picxsize && n2 + i > 0 && n2 + i < this.picysize) {
                this.pix[n - n7 + (n2 + i) * this.picxsize] = -16777216 + (n4 << 16) + (n5 << 8) + n6;
            }
            if (n + n7 > 0 && n + n7 < this.picxsize && n2 - i > 0 && n2 - i < this.picysize) {
                this.pix[n + n7 + (n2 - i) * this.picxsize] = -16777216 + (n4 << 16) + (n5 << 8) + n6;
            }
            if (n - n7 > 0 && n - n7 < this.picxsize && n2 - i > 0 && n2 - i < this.picysize) {
                this.pix[n - n7 + (n2 - i) * this.picxsize] = -16777216 + (n4 << 16) + (n5 << 8) + n6;
            }
        }
    }
    
    public void Set3dpixel(final point3d point3d) {
        final int x2d = point3d.x2d;
        final int y2d = point3d.y2d;
        final int n = 255;
        if (point3d.z3d > 0 && x2d > 0 && x2d < this.picxsize && y2d > 0 && y2d < this.picysize) {
            this.pix[x2d + y2d * this.picxsize] = -16777216 + (n << 16) + (n << 8) + n;
        }
    }
    
    public void init() {
        this.setBackground(Color.black);
        final int n = 30;
        this.offGraphics = null;
        this.delay = ((n > 0) ? (1000 / n) : 100);
        this.picxsize = 240;
        this.picysize = 200;
        this.OPTION = this.getParameter("Target");
        final String parameter = this.getParameter("NumberOfStars");
        if (parameter != null) {
            this.nrOfStars = Integer.parseInt(parameter);
        }
        else {
            this.nrOfStars = 0;
        }
        this.wx = new int[this.nrOfStars];
        this.wy = new int[this.nrOfStars];
        this.text = new String[this.nrOfStars * 3];
        for (int i = 0; i < this.nrOfStars; ++i) {
            final String parameter2 = this.getParameter("Star" + (i + 1) + "-X");
            if (parameter2 != null) {
                this.wx[i] = Integer.parseInt(parameter2);
            }
            else {
                this.wx[i] = 0;
            }
            final String parameter3 = this.getParameter("Star" + (i + 1) + "-Y");
            if (parameter3 != null) {
                this.wy[i] = Integer.parseInt(parameter3);
            }
            else {
                this.wy[i] = 0;
            }
            this.text[i * 3] = this.getParameter("Linkname" + (i + 1));
            this.text[1 + i * 3] = this.getParameter("Categorie" + (i + 1));
            this.text[2 + i * 3] = this.getParameter("Distance" + (i + 1));
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
        if (this.OPTION == null) {
            this.OPTION = "_self";
        }
        final String parameter4 = this.getParameter("Textred-value");
        final String parameter5 = this.getParameter("Textgreen-value");
        final String parameter6 = this.getParameter("Textblue-value");
        int int1 = 255;
        int int2 = 255;
        int int3 = 255;
        if (parameter4 != null) {
            int1 = Integer.parseInt(parameter4);
        }
        if (parameter5 != null) {
            int2 = Integer.parseInt(parameter5);
        }
        if (parameter6 != null) {
            int3 = Integer.parseInt(parameter6);
        }
        this.c = new Color(int1 / 255.0f, int2 / 255.0f, int3 / 255.0f);
        final String parameter7 = this.getParameter("Planetred-value");
        final String parameter8 = this.getParameter("Planetgreen-value");
        final String parameter9 = this.getParameter("Planetblue-value");
        final int pr = 255;
        this.pb = pr;
        this.pg = pr;
        this.pr = pr;
        if (parameter7 != null) {
            this.pr = Integer.parseInt(parameter7);
        }
        if (parameter8 != null) {
            this.pg = Integer.parseInt(parameter8);
        }
        if (parameter9 != null) {
            this.pb = Integer.parseInt(parameter9);
        }
        this.pix = new int[this.picxsize * this.picysize];
        this.zbuffer = new int[this.picxsize * this.picysize];
        (this.source = new MemoryImageSource(this.picxsize, this.picysize, this.pix, 0, this.picxsize)).setAnimated(true);
        this.source.setFullBufferUpdates(true);
        this.camera.setLight(0, 0, 0, 50);
        this.camera.lights[0].calcLength();
        this.Img = this.createImage(this.source);
        for (int j = 0; j < 360; ++j) {
            this.Stab[j] = Math.sin(j * 3.141592653589793 / 180.0);
            this.Ctab[j] = Math.cos(j * 3.141592653589793 / 180.0);
        }
        for (int k = 0; k < 4000; ++k) {
            this.poly_hullxs[k] = new PL();
            this.poly_hullxe[k] = new PL();
        }
        this.cnt = 0;
        final Font font = new Font("Arial", 0, 10);
        this.getFontMetrics(font);
        this.setFont(font);
        this.partnr = 0;
        this.LoadData();
        this.cnt = 0;
    }
    
    public boolean handleEvent(final Event event) {
        switch (event.id) {
            case 503: {
                this.xpos = event.x;
                this.ypos = event.y;
                return true;
            }
            case 504: {
                this.xpos = event.x;
                this.ypos = event.y;
                return true;
            }
            case 501: {
                if (!this.move) {
                    this.getAppletContext().showDocument(this.urls[this.cnt], this.OPTION);
                    this.getAppletContext().showStatus("cnt " + this.cnt);
                    this.pos = true;
                }
                return true;
            }
            default: {
                return super.handleEvent(event);
            }
        }
    }
    
    public void LoadData() {
        int i = 0;
        try {
            final DataInputStream dataInputStream = new DataInputStream((InputStream)new URL(this.getCodeBase(), "sphere.3dc").getContent());
            this.cnt2 = dataInputStream.readInt();
            ++this.cnt2;
            this.object1 = new obj3d[this.cnt2 + 2];
            while (i < this.cnt2) {
                final int int1 = dataInputStream.readInt();
                final int int2 = dataInputStream.readInt();
                int j = 0;
                int k = 0;
                this.object1[i] = new obj3d(-200, -310, 4192, int1, int2 * 2);
                while (j < int1) {
                    this.object1[i].addLocalPoint(dataInputStream.readInt(), dataInputStream.readInt(), -dataInputStream.readInt());
                    ++j;
                }
                while (k < int2 * 2) {
                    this.object1[i].addLocalPoly(dataInputStream.readInt(), dataInputStream.readInt(), dataInputStream.readInt(), this.pr, this.pb, this.pg);
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
            this.object1[l].CamRotate(0, 0, 0, this.origin, this.sincos);
            this.object1[l].localRotate(90, 0, 0, this.sincos);
        }
        this.stars[0] = new obj3d(0, 0, 512, 500, 10);
        for (int n = 0; n < 300; ++n) {
            this.stars[0].addLocalPoint(-200 + (int)(Math.random() * 400.0), -200 + (int)(Math.random() * 400.0), -1000 + (int)(Math.random() * 2000.0));
        }
        this.stars[0].localRotate(0, 0, 0, this.sincos);
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
    
    public void Flat_poly(final polygon polygon) {
        final POLY3_2 poly3_2 = new POLY3_2();
        poly3_2.x[0] = polygon.p1.x2d;
        poly3_2.y[0] = polygon.p1.y2d;
        poly3_2.x[1] = polygon.p2.x2d;
        poly3_2.y[1] = polygon.p2.y2d;
        poly3_2.x[2] = polygon.p3.x2d;
        poly3_2.y[2] = polygon.p3.y2d;
        int n = 0;
        for (int i = 0; i < 3; ++i) {
            if (poly3_2.x[i] < 0 || poly3_2.x[i] > this.picxsize || poly3_2.y[i] < 0 || poly3_2.y[i] > this.picysize) {
                ++n;
            }
        }
        if (n < 3) {
            int n2 = 0;
            polygon.normal.calcLength();
            final double n3 = 1.0 + polygon.normal.length;
            for (int j = 0; j < this.nroflights; ++j) {
                final int n4 = (int)((this.dotProduct(polygon.normal, this.camera.lights[j]) << 8) / (this.camera.lights[j].length * n3));
                if (n4 > 0) {
                    n2 += n4;
                }
            }
            if (n2 > 255) {
                n2 = 255;
            }
            int n5 = (polygon.red >> 1) + (n2 >> 1);
            int n6 = (polygon.green >> 1) + (n2 >> 1);
            int n7 = (polygon.blue >> 1) + (n2 >> 1);
            if (n5 > 255) {
                n5 = 255;
            }
            if (n6 > 255) {
                n6 = 255;
            }
            if (n7 > 255) {
                n7 = 255;
            }
            if (polygon.p1.z3d > 0 && polygon.p2.z3d > 0 && polygon.p3.z3d > 0) {
                this.zpoly3(poly3_2, polygon.p1.z3d, polygon.p2.z3d, polygon.p3.z3d, n5, n6, n7);
            }
        }
    }
    
    public void ShowPart() {
        for (int i = 0; i < this.picysize; ++i) {
            final int n = i * this.picxsize;
            for (int j = 0; j < this.picxsize; ++j) {
                this.pix[j + n] = 0;
                this.zbuffer[j + n] = 16711680;
            }
        }
        if (this.cnter > 19) {
            for (int k = 0; k < this.nrOfStars; ++k) {
                for (int l = 0; l < 2; ++l) {
                    this.Circle(this.wx[k], this.wy[k], l, 255, 255, 255);
                }
            }
        }
        for (int n2 = 0; n2 < this.stars[0].numPoints; ++n2) {
            this.Set3dpixel(this.stars[0].point[n2]);
        }
        if (this.cnter > 20 && !this.move) {
            for (int n3 = 0; n3 < this.cnt2; ++n3) {
                for (int n4 = 0; n4 < this.object1[n3].numPolys; ++n4) {
                    if (this.object1[n3].poly[n4].normal.rotoZ > 0) {
                        this.Flat_poly(this.object1[n3].poly[n4]);
                    }
                }
            }
        }
        if (this.pos) {
            this.object1[0].translate(10, 10, -100);
        }
        ++this.cnter;
        this.object1[0].localRotate(0, 3, 0, this.sincos);
        if (this.cnter < 20) {
            this.stars[0].localRotate(0, 1, 1, this.sincos);
        }
    }
    
    public void paint(final Graphics graphics) {
        final Dimension size = this.size();
        if (this.offGraphics == null || size.width != this.offDimension.width || size.height != this.offDimension.height) {
            this.offDimension = size;
            this.offImage = this.createImage(size.width, size.height);
            this.offGraphics = this.offImage.getGraphics();
        }
    }
    
    public void run() {
        long currentTimeMillis = System.currentTimeMillis();
        final Graphics graphics = this.getGraphics();
        while (this.killme != null) {
            ++this.frameNumber;
            final Dimension size = this.size();
            if (this.offGraphics == null || size.width != this.offDimension.width || size.height != this.offDimension.height) {
                this.offDimension = size;
                this.offImage = this.createImage(size.width, size.height);
                this.offGraphics = this.offImage.getGraphics();
            }
            this.offGraphics.setColor(Color.black);
            this.offGraphics.fillRect(0, 0, size.width, size.height);
            this.ShowPart();
            this.offGraphics.drawImage(this.Img, 0, 0, 240, 200, null, this);
            this.offGraphics.setColor(this.c);
            if (this.cnter > 19) {
                this.offGraphics.drawLine(this.xpos - 4, this.ypos - 4, this.xpos + 4, this.ypos - 4);
                this.offGraphics.drawLine(this.xpos - 4, this.ypos - 4, this.xpos - 4, this.ypos + 4);
                this.offGraphics.drawLine(this.xpos - 4, this.ypos + 4, this.xpos + 4, this.ypos + 4);
                this.offGraphics.drawLine(this.xpos + 4, this.ypos - 4, this.xpos + 4, this.ypos + 4);
                this.offGraphics.drawLine(0, this.ypos, this.xpos - 4, this.ypos);
                this.offGraphics.drawLine(this.xpos + 4, this.ypos, 240, this.ypos);
                this.offGraphics.drawLine(this.xpos, 0, this.xpos, this.ypos - 4);
                this.offGraphics.drawLine(this.xpos, 145, this.xpos, this.ypos + 4);
                if (!this.move) {
                    this.offGraphics.drawString("Planet " + this.text[this.cnt * 3], 100, 160);
                    this.offGraphics.drawString("Category " + this.text[this.cnt * 3 + 1], 100, 175);
                    this.offGraphics.drawString("Distance " + this.text[this.cnt * 3 + 2], 100, 190);
                }
            }
            int cnt = 0;
            this.move = true;
            while (cnt < this.nrOfStars && this.move) {
                if (this.xpos > this.wx[cnt] - 2 && this.xpos < this.wx[cnt] + 2 && this.ypos < this.wy[cnt] + 2 && this.ypos > this.wy[cnt] - 2) {
                    this.move = false;
                    this.cnt = cnt;
                }
                ++cnt;
            }
            this.offGraphics.setColor(Color.white);
            this.offGraphics.setFont(new Font("Arial", 1, 10));
            this.offGraphics.drawString("Demo-version", 1, 15);
            this.getAppletContext().showStatus("Star-map-link V001 (c) OtakInc 1999");
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
        this.killme.stop();
        this.offGraphics = null;
        this.offImage = null;
        this.killme = null;
    }
    
    public Links() {
        this.cnt2 = 230;
        this.Stab = new double[361];
        this.Ctab = new double[361];
        this.nroflights = 1;
        this.frameNumber = -1;
        this.camera = new viewPoint(1);
        this.sincos = new SinCos();
        this.tracer = new MediaTracker(this);
        this.pos = false;
        this.move = true;
        this.nrOfStars = 5;
        this.c = new Color(0.07f, 0.97f, 0.14f);
        this.xpos = 30;
        this.ypos = 30;
        this.origin = new point3d(0, 0, 0);
        this.transl = new point3d(0, 0, 0);
        this.object1 = new obj3d[4];
        this.stars = new obj3d[1];
        this.poly_hullxs = new PL[4000];
        this.poly_hullxe = new PL[4000];
        this.display = 1;
        this.partnr = 1;
        this.urls = new URL[5];
    }
}
