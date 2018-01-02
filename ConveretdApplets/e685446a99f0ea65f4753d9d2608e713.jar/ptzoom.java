import java.awt.Graphics;
import java.awt.image.ImageObserver;
import java.applet.AppletStub;
import java.util.Vector;
import java.awt.Image;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class ptzoom extends Applet implements Runnable
{
    String PTViewer;
    ptviewer pv;
    Thread loadImages;
    String filename;
    String maskname;
    int width;
    int height;
    int[][] idata;
    byte[][] hsdata;
    private double[][] f1;
    private double[][] f2;
    double cpitch;
    double cyaw;
    double croll;
    double chfov;
    int showSHS;
    boolean progress;
    Image zoom_image;
    String hsimage;
    boolean ready;
    double max_oversampling;
    int position;
    boolean antialias;
    Vector scaledImages;
    Vector scaledHSImages;
    
    public ptzoom() {
        this.PTViewer = "ptviewer";
        this.pv = null;
        this.loadImages = null;
        this.filename = "image";
        this.maskname = null;
        this.idata = null;
        this.hsdata = null;
        this.cpitch = 0.0;
        this.cyaw = 0.0;
        this.croll = 0.0;
        this.chfov = 50.0;
        this.showSHS = -1;
        this.progress = false;
        this.zoom_image = null;
        this.hsimage = null;
        this.ready = false;
        this.max_oversampling = 8.0;
        this.position = 0;
        this.antialias = false;
        this.scaledImages = null;
        this.scaledHSImages = null;
    }
    
    public ptzoom(final ptviewer pv, final String s) {
        this.PTViewer = "ptviewer";
        this.pv = null;
        this.loadImages = null;
        this.filename = "image";
        this.maskname = null;
        this.idata = null;
        this.hsdata = null;
        this.cpitch = 0.0;
        this.cyaw = 0.0;
        this.croll = 0.0;
        this.chfov = 50.0;
        this.showSHS = -1;
        this.progress = false;
        this.zoom_image = null;
        this.hsimage = null;
        this.ready = false;
        this.max_oversampling = 8.0;
        this.position = 0;
        this.antialias = false;
        this.scaledImages = null;
        this.scaledHSImages = null;
        this.pv = pv;
        this.setStub(new ptstub(this.pv, s));
    }
    
    public ptzoom(final ptviewer pv, final Image zoom_image, final byte[] array, final String s) {
        this.PTViewer = "ptviewer";
        this.pv = null;
        this.loadImages = null;
        this.filename = "image";
        this.maskname = null;
        this.idata = null;
        this.hsdata = null;
        this.cpitch = 0.0;
        this.cyaw = 0.0;
        this.croll = 0.0;
        this.chfov = 50.0;
        this.showSHS = -1;
        this.progress = false;
        this.zoom_image = null;
        this.hsimage = null;
        this.ready = false;
        this.max_oversampling = 8.0;
        this.position = 0;
        this.antialias = false;
        this.scaledImages = null;
        this.scaledHSImages = null;
        this.pv = pv;
        this.zoom_image = zoom_image;
        if (array != null && array.length == zoom_image.getWidth(null) * zoom_image.getHeight(null)) {
            this.hsdata = new byte[zoom_image.getHeight(null)][zoom_image.getWidth(null)];
            for (int i = 0; i < zoom_image.getHeight(null); ++i) {
                System.arraycopy(array, i * zoom_image.getWidth(null), this.hsdata[i], 0, zoom_image.getWidth(null));
            }
        }
        this.setStub(new ptstub(this.pv, s));
    }
    
    public ptzoom(final ptviewer pv, final int[][] idata, final double chfov, final double cyaw, final double cpitch, final double croll) {
        this.PTViewer = "ptviewer";
        this.pv = null;
        this.loadImages = null;
        this.filename = "image";
        this.maskname = null;
        this.idata = null;
        this.hsdata = null;
        this.cpitch = 0.0;
        this.cyaw = 0.0;
        this.croll = 0.0;
        this.chfov = 50.0;
        this.showSHS = -1;
        this.progress = false;
        this.zoom_image = null;
        this.hsimage = null;
        this.ready = false;
        this.max_oversampling = 8.0;
        this.position = 0;
        this.antialias = false;
        this.scaledImages = null;
        this.scaledHSImages = null;
        this.pv = pv;
        this.chfov = chfov;
        this.cyaw = cyaw;
        this.cpitch = cpitch;
        this.croll = croll;
        this.width = idata[0].length;
        this.height = idata.length;
        this.hsdata = new byte[this.height][this.width];
        if (this.hsimage == null) {
            for (int i = 0; i < this.height; ++i) {
                for (int j = 0; j < this.width; ++j) {
                    this.hsdata[i][j] = -1;
                }
            }
        }
        this.idata = idata;
        this.setStub(new ptstub(this.pv, ""));
        this.max_oversampling = 1000.0;
    }
    
    public void init() {
        this.f1 = new double[3][3];
        this.f2 = new double[3][3];
        final String parameter;
        if ((parameter = this.getParameter("PTViewer")) != null) {
            this.PTViewer = parameter;
        }
        final String parameter2;
        if ((parameter2 = this.getParameter("file")) != null) {
            this.filename = parameter2;
        }
        final String parameter3;
        if ((parameter3 = this.getParameter("mask")) != null) {
            this.maskname = parameter3;
        }
        final String parameter4;
        if ((parameter4 = this.getParameter("hsimage")) != null) {
            this.hsimage = parameter4;
        }
        final String parameter5;
        if ((parameter5 = this.getParameter("fov")) != null) {
            this.chfov = Double.valueOf(parameter5);
        }
        final String parameter6;
        if ((parameter6 = this.getParameter("pan")) != null) {
            this.cyaw = Double.valueOf(parameter6);
        }
        final String parameter7;
        if ((parameter7 = this.getParameter("tilt")) != null) {
            this.cpitch = Double.valueOf(parameter7);
        }
        final String parameter8;
        if ((parameter8 = this.getParameter("rot")) != null) {
            this.croll = Double.valueOf(parameter8);
        }
        final String parameter9;
        if ((parameter9 = this.getParameter("showSHS")) != null) {
            this.showSHS = Integer.parseInt(parameter9);
        }
        final String parameter10;
        if ((parameter10 = this.getParameter("progress")) != null && parameter10.equalsIgnoreCase("true")) {
            this.progress = true;
        }
        if (this.getParameter("antialias") != null) {
            this.antialias = true;
            this.max_oversampling = 1.5;
        }
        final String parameter11;
        if ((parameter11 = this.getParameter("oversampling")) != null) {
            this.max_oversampling = Double.valueOf(parameter11);
        }
    }
    
    public void start() {
        while (this.pv == null) {
            try {
                this.pv = (ptviewer)this.getAppletContext().getApplet(this.PTViewer);
            }
            catch (Exception ex) {
                try {
                    Thread.sleep(2000L);
                }
                catch (InterruptedException ex2) {
                    return;
                }
            }
        }
        if (this.pv != null) {
            this.pv.startCommunicating(this);
            if (this.idata == null) {
                if (this.zoom_image != null) {
                    this.load_zoom_image();
                    return;
                }
                if (this.loadImages == null) {
                    (this.loadImages = new Thread(this)).start();
                }
            }
            else {
                this.SetDefMatrix();
                this.pv.dirty = true;
                this.pv.repaint();
                this.ready = true;
            }
        }
    }
    
    public synchronized void set(final double chfov, final double cyaw, final double cpitch, final double croll, final int[][] idata) {
        this.chfov = chfov;
        this.cyaw = cyaw;
        this.cpitch = cpitch;
        this.croll = croll;
        if (idata != this.idata) {
            this.width = idata[0].length;
            this.height = idata.length;
            if (this.hsdata[0].length != this.width || this.hsdata.length != this.height) {
                this.hsdata = new byte[this.height][this.width];
                for (int i = 0; i < this.height; ++i) {
                    for (int j = 0; j < this.width; ++j) {
                        this.hsdata[i][j] = -1;
                    }
                }
            }
            this.idata = idata;
        }
        this.SetDefMatrix();
        this.pv.dirty = true;
        this.pv.repaint();
    }
    
    public void set(final double n, final double n2, final double n3, final double n4, final int[][] array, final int position) {
        this.position = position;
        this.set(n, n2, n3, n4, array);
    }
    
    public void stop() {
        if (this.pv != null) {
            if (this.loadImages != null) {
                this.pv.stopThread(this.loadImages);
                this.loadImages = null;
            }
            if (this.showSHS >= 0 && this.showSHS < this.pv.numshs) {
                this.pv.shs_imode[this.showSHS] = 0;
            }
            this.pv.stopCommunicating(this);
        }
        this.scaledImages = null;
        this.scaledHSImages = null;
    }
    
    public void run() {
        this.load_zoom_image();
    }
    
    void load_zoom_image() {
        Image image;
        if (this.zoom_image == null) {
            if (this.progress) {
                this.pv.ready = false;
                this.pv.percent[0] = 0;
                this.pv.repaint();
                image = this.pv.loadImageProgress(this.filename);
            }
            else {
                image = this.pv.loadImage(this.filename);
            }
        }
        else {
            image = this.zoom_image;
        }
        if (image != null) {
            this.width = image.getWidth(null);
            this.height = image.getHeight(null);
            int[][] idata;
            try {
                idata = new int[this.height][this.width];
            }
            catch (Exception ex) {
                return;
            }
            this.pv.ptImageTo2DArray(idata, image);
            final Image loadImage;
            if (this.maskname != null && (loadImage = this.pv.loadImage(this.maskname)) != null && loadImage.getWidth(null) == this.width && loadImage.getHeight(null) == this.height) {
                this.pv.ptImageToAlpha(idata, loadImage);
            }
            if (this.hsdata == null) {
                this.hsdata = new byte[this.height][this.width];
                if (this.hsimage == null) {
                    if (this.pv.filename != null && this.pv.filename.toLowerCase().endsWith(".mov")) {
                        for (int i = 0; i < this.height; ++i) {
                            for (int j = 0; j < this.width; ++j) {
                                this.hsdata[i][j] = 0;
                            }
                        }
                    }
                    else {
                        for (int k = 0; k < this.height; ++k) {
                            for (int l = 0; l < this.width; ++l) {
                                this.hsdata[k][l] = -1;
                            }
                        }
                    }
                }
                else {
                    final Image loadImage2;
                    if ((loadImage2 = this.pv.loadImage(this.hsimage)) != null && loadImage2.getWidth(null) == this.width && loadImage2.getHeight(null) == this.height) {
                        final int[][] ai = new int[this.height][this.width];
                        this.pv.ptImageTo2DArray(ai, loadImage2);
                        for (int n = 0; n < this.height; ++n) {
                            for (int n2 = 0; n2 < this.width; ++n2) {
                                this.hsdata[n][n2] = (byte)(ai[n][n2] >> 24);
                            }
                        }
                        System.gc();
                    }
                }
            }
            this.SetDefMatrix();
            this.idata = idata;
            this.pv.dirty = true;
            if (this.showSHS >= 0 && this.showSHS < this.pv.numshs) {
                this.pv.shs_imode[this.showSHS] = 2;
            }
            if (this.progress) {
                this.pv.ready = true;
            }
            this.pv.repaint();
            this.ready = true;
        }
    }
    
    public void paint(final Graphics graphics) {
        if (this.pv == null || this.idata == null || this.pv.vdata == null) {
            return;
        }
        if (this.antialias && this.scaledImages == null) {
            (this.scaledImages = new Vector()).addElement(this.idata);
            int[][] ai = this.idata;
            final double n = this.pv.hfov_max / (this.pv.vwidth * this.chfov * this.max_oversampling);
            int n2 = 0;
            while (ai != null && ai[0].length * n > 1.0) {
                ai = this.pv.im_halfsize(ai);
                this.scaledImages.addElement(ai);
                ++n2;
            }
            (this.scaledHSImages = new Vector()).addElement(this.hsdata);
            byte[][] abyte0 = this.hsdata;
            while (abyte0 != null && abyte0[0].length * n > 1.0) {
                abyte0 = this.pv.im_halfsize(abyte0);
                this.scaledHSImages.addElement(abyte0);
            }
        }
        if (this.pv.dirty && (this.antialias || this.pv.hfov * this.width < this.max_oversampling * this.chfov * this.pv.vwidth)) {
            int[][] idata = this.idata;
            byte[][] hsdata = this.hsdata;
            if (this.antialias && this.scaledImages != null) {
                final double n3 = this.pv.hfov / (this.pv.vwidth * this.chfov * this.max_oversampling);
                int n4 = 0;
                for (int length = this.idata[0].length; length * n3 > 1.0; length >>= 1) {
                    ++n4;
                }
                if (this.scaledImages.elementAt(n4) != null) {
                    idata = (int[][])this.scaledImages.elementAt(n4);
                    hsdata = (byte[][])this.scaledHSImages.elementAt(n4);
                }
            }
            switch (this.pv.quality) {
                case 0: {
                    this.pv.dirty = !this.SetView(idata, hsdata, false);
                }
                case 1: {
                    if (this.pv.panning || this.pv.lastframe > this.pv.frames) {
                        this.SetView(idata, hsdata, false);
                        return;
                    }
                    this.pv.dirty = !this.SetView(idata, hsdata, true);
                }
                case 2: {
                    if (this.pv.panning) {
                        this.SetView(idata, hsdata, false);
                        return;
                    }
                    this.pv.dirty = !this.SetView(idata, hsdata, true);
                }
                case 3: {
                    this.pv.dirty = !this.SetView(idata, hsdata, true);
                    break;
                }
            }
        }
    }
    
    boolean SetView(final int[][] array, final byte[][] array2, final boolean b) {
        boolean b2 = true;
        final int length = array[0].length;
        final int length2 = array.length;
        this.SetMatrix(this.pv.pitch * 2.0 * 3.141592653589793 / 360.0, (this.pv.yaw - this.cyaw) * 2.0 * 3.141592653589793 / 360.0, 1);
        if (this.position == 1) {
            this.ShiftCubeFace(1.5707963267948966);
        }
        else if (this.position == 2) {
            this.ShiftCubeFace(-1.5707963267948966);
        }
        final double n = this.pv.vwidth / (2.0 * Math.tan(this.pv.hfov * 3.141592653589793 / 180.0 / 2.0));
        final double n2 = length / (2.0 * Math.tan(this.chfov * 3.141592653589793 / 180.0 / 2.0));
        for (int i = 0; i < 3; ++i) {
            final double[] array3 = this.f1[2];
            final int n3 = i;
            array3[n3] *= n;
        }
        for (int j = 0; j < 3; ++j) {
            for (int k = 0; k < 2; ++k) {
                final double[] array4 = this.f1[j];
                final int n4 = k;
                array4[n4] *= n2;
            }
        }
        double n5 = 256.0;
        Label_0250: {
            break Label_0250;
            double n6;
            do {
                n5 /= 2.0;
                n6 = n;
            } while (n6 * n6 * n5 / 256.0 > 1.0E7);
        }
        final int n7 = (int)(n5 * this.f1[0][0] + 0.5);
        final int n8 = (int)(n5 * this.f1[0][1] + 0.5);
        final int n9 = (int)(n5 * this.f1[0][2] + 0.5);
        final int n10 = (int)(n5 * this.f1[1][0] + 0.5);
        final int n11 = (int)(n5 * this.f1[1][1] + 0.5);
        final int n12 = (int)(n5 * this.f1[1][2] + 0.5);
        final int n13 = (int)(n5 * this.f1[2][0] + 0.5);
        final int n14 = (int)(n5 * this.f1[2][1] + 0.5);
        final int n15 = (int)(n5 * this.f1[2][2] + 0.5);
        final int n16 = this.pv.vwidth - 1 >> 1;
        final int n17 = this.pv.vheight >> 1;
        final int n18 = (length << 7) + 64;
        final int n19 = (length2 << 7) + 64;
        final int n20 = length2 - 1;
        final int n21 = length - 1;
        int n22 = 0;
        int[] array5 = array[0];
        int[] array6 = array[1];
        byte[] array7 = array2[0];
        if (b) {
            for (int l = 0, n23 = -n17 * n12 + n15 + 128, n24 = -n17 * n10 + n13, n25 = -n17 * n11 + n14; l < this.pv.vheight; ++l, n23 += n12, n24 += n10, n25 += n11) {
                final int n26 = this.pv.vwidth * l;
                int n27 = 0;
                int n28 = n26;
                final int n29 = -n16 * n9 + n23;
                final int n30 = -n16 * n7 + n24;
                final int n31 = -n16 * n8 + n25;
                while (n27 < this.pv.vwidth) {
                    if (this.pv.vdata[n28] == 0) {
                        final int n32;
                        if ((n32 = n29 + n27 * n9 >> 8) <= 0) {
                            b2 = false;
                        }
                        else {
                            final int n33 = (n30 + n27 * n7) / n32 + n18;
                            final int n34 = (n31 + n27 * n8) / n32 + n19;
                            final int n35 = n33 & 0xFF;
                            int n36 = n33 >> 8;
                            final int n37 = n34 & 0xFF;
                            int n38;
                            if ((n38 = n34 >> 8) == n22 && n36 >= 0 && n36 < n21) {
                                final int m;
                                if (((m = array5[n36]) & 0xFF000000) != 0x0) {
                                    this.pv.hs_vdata[n28] = array7[n36];
                                    this.pv.vdata[n28] = ptviewer.bil(m, array5[n36], array6[n36++], array6[n36], n35, n37);
                                }
                                else {
                                    b2 = false;
                                }
                            }
                            else if (n36 >= 0 && n38 >= 0 && n38 < n20 && n36 < n21) {
                                n22 = n38;
                                array7 = array2[n38];
                                array5 = array[n38++];
                                array6 = array[n38];
                                final int i2;
                                if (((i2 = array5[n36]) & 0xFF000000) != 0x0) {
                                    this.pv.hs_vdata[n28] = array7[n36];
                                    this.pv.vdata[n28] = ptviewer.bil(i2, array5[n36], array6[n36++], array6[n36], n35, n37);
                                }
                                else {
                                    b2 = false;
                                }
                            }
                            else {
                                if (n38 == -1) {
                                    n38 = 0;
                                }
                                else if (n38 == length2) {
                                    n38 = n20;
                                }
                                if (n36 == -1) {
                                    n36 = 0;
                                }
                                else if (n36 == length) {
                                    n36 = n21;
                                }
                                final int n39;
                                if (n36 >= 0 && n36 < length && n38 >= 0 && n38 < length2 && ((n39 = array[n38][n36]) & 0xFF000000) != 0x0) {
                                    this.pv.vdata[n28] = n39;
                                    this.pv.hs_vdata[n28] = array2[n38][n36];
                                }
                                else {
                                    b2 = false;
                                }
                            }
                        }
                    }
                    ++n27;
                    ++n28;
                }
            }
        }
        else {
            final int n40 = n18 + 128;
            final int n41 = n19 + 128;
            for (int n42 = 0, n43 = -n17 * n12 + n15 + 128, n44 = -n17 * n10 + n13, n45 = -n17 * n11 + n14; n42 < this.pv.vheight; ++n42, n43 += n12, n44 += n10, n45 += n11) {
                final int n46 = this.pv.vwidth * n42;
                int n47 = 0;
                int n48 = n46;
                final int n49 = -n16 * n9 + n43;
                final int n50 = -n16 * n7 + n44;
                final int n51 = -n16 * n8 + n45;
                while (n47 < this.pv.vwidth) {
                    if (this.pv.vdata[n48] == 0) {
                        final int n52;
                        if ((n52 = n49 + n47 * n9 >> 8) <= 0) {
                            b2 = false;
                        }
                        else {
                            int n53 = (n50 + n47 * n7) / n52 + n40 >> 8;
                            int n54 = (n51 + n47 * n8) / n52 + n41 >> 8;
                            final int n55;
                            if (n53 >= 0 && n53 < length && n54 >= 0 && n54 < length2 && ((n55 = array[n54][n53]) & 0xFF000000) != 0x0) {
                                this.pv.vdata[n48] = n55;
                            }
                            else {
                                if (n53 == -1) {
                                    n53 = 0;
                                }
                                else if (n53 == length) {
                                    n53 = n21;
                                }
                                if (n54 == -1) {
                                    n54 = 0;
                                }
                                else if (n54 == length2) {
                                    n54 = n20;
                                }
                                final int n56;
                                if (n53 >= 0 && n53 < length && n54 >= 0 && n54 < length2 && ((n56 = array[n54][n53]) & 0xFF000000) != 0x0) {
                                    this.pv.vdata[n48] = n56;
                                }
                                else {
                                    b2 = false;
                                }
                            }
                        }
                    }
                    ++n47;
                    ++n48;
                }
            }
        }
        return b2;
    }
    
    void SetMatrix(final double n, final double n2, final int n3) {
        final double[][] array = new double[3][3];
        final double[][] array2 = new double[3][3];
        final double[][] ad = new double[3][3];
        array[0][0] = 1.0;
        array[0][1] = 0.0;
        array[0][2] = 0.0;
        array[1][0] = 0.0;
        array[1][1] = Math.cos(n);
        array[1][2] = Math.sin(n);
        array[2][0] = 0.0;
        array[2][1] = -array[1][2];
        array[2][2] = array[1][1];
        array2[0][0] = Math.cos(n2);
        array2[0][1] = 0.0;
        array2[0][2] = -Math.sin(n2);
        array2[1][0] = 0.0;
        array2[1][1] = 1.0;
        array2[1][2] = 0.0;
        array2[2][0] = -array2[0][2];
        array2[2][1] = 0.0;
        array2[2][2] = array2[0][0];
        if (n3 == 1) {
            this.pv.matrix_matrix_mult(array, array2, ad);
        }
        else {
            this.pv.matrix_matrix_mult(array2, array, ad);
        }
        this.pv.matrix_matrix_mult(ad, this.f2, this.f1);
    }
    
    void SetDefMatrix() {
        final double[][] ad1 = new double[3][3];
        final double[][] ad2 = new double[3][3];
        final double n = -this.cpitch * 2.0 * 3.141592653589793 / 360.0;
        final double n2 = -this.croll * 2.0 * 3.141592653589793 / 360.0;
        ad1[0][0] = 1.0;
        ad1[0][1] = 0.0;
        ad1[0][2] = 0.0;
        ad1[1][0] = 0.0;
        ad1[1][1] = Math.cos(n);
        ad1[1][2] = Math.sin(n);
        ad1[2][0] = 0.0;
        ad1[2][1] = -ad1[1][2];
        ad1[2][2] = ad1[1][1];
        ad2[0][0] = Math.cos(n2);
        ad2[0][1] = Math.sin(n2);
        ad2[0][2] = 0.0;
        ad2[1][0] = -ad2[0][1];
        ad2[1][1] = ad2[0][0];
        ad2[1][2] = 0.0;
        ad2[2][0] = 0.0;
        ad2[2][1] = 0.0;
        ad2[2][2] = 1.0;
        this.pv.matrix_matrix_mult(ad2, ad1, this.f2);
    }
    
    public String getAppletInfo() {
        return "topFrame";
    }
    
    void ShiftCubeFace(final double n) {
        final double[][] ad2 = new double[3][3];
        final double[][] ad3;
        (ad3 = new double[3][3])[0][0] = Math.cos(n);
        ad3[0][1] = 0.0;
        ad3[0][2] = -Math.sin(n);
        ad3[1][0] = 0.0;
        ad3[1][1] = 1.0;
        ad3[1][2] = 0.0;
        ad3[2][0] = -ad3[0][2];
        ad3[2][1] = 0.0;
        ad3[2][2] = ad3[0][0];
        this.pv.matrix_matrix_mult(this.f1, ad3, ad2);
        for (int i = 0; i < 3; ++i) {
            for (int j = 0; j < 3; ++j) {
                this.f1[i][j] = ad2[i][j];
            }
        }
    }
}
