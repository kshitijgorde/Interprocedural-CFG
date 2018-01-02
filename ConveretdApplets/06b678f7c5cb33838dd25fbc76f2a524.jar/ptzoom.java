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
    ImageData idata;
    byte[][] hsdata;
    private double[][] mt;
    private double[][] md;
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
    public double exposure;
    double gamma;
    boolean antialias;
    Vector scaledImages;
    Vector scaledHSImages;
    boolean single;
    
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
        this.exposure = 1.0;
        this.gamma = 1.4;
        this.antialias = false;
        this.scaledImages = null;
        this.scaledHSImages = null;
        this.single = false;
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
        this.exposure = 1.0;
        this.gamma = 1.4;
        this.antialias = false;
        this.scaledImages = null;
        this.scaledHSImages = null;
        this.single = false;
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
        this.exposure = 1.0;
        this.gamma = 1.4;
        this.antialias = false;
        this.scaledImages = null;
        this.scaledHSImages = null;
        this.single = false;
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
    
    public ptzoom(final ptviewer pv, final int[][] array, final double chfov, final double cyaw, final double cpitch, final double croll) {
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
        this.exposure = 1.0;
        this.gamma = 1.4;
        this.antialias = false;
        this.scaledImages = null;
        this.scaledHSImages = null;
        this.single = false;
        this.pv = pv;
        this.chfov = chfov;
        this.cyaw = cyaw;
        this.cpitch = cpitch;
        this.croll = croll;
        this.width = array[0].length;
        this.height = array.length;
        this.hsdata = new byte[this.height][this.width];
        if (this.hsimage == null) {
            for (int i = 0; i < this.height; ++i) {
                for (int j = 0; j < this.width; ++j) {
                    this.hsdata[i][j] = -1;
                }
            }
        }
        this.idata = new ImageData(array, this.pv);
        this.setStub(new ptstub(this.pv, ""));
        this.max_oversampling = 1000.0;
    }
    
    public void init() {
        this.mt = new double[3][3];
        this.md = new double[3][3];
        final String parameter = this.getParameter("PTViewer");
        if (parameter != null) {
            this.PTViewer = parameter;
        }
        final String parameter2 = this.getParameter("file");
        if (parameter2 != null) {
            this.filename = parameter2;
        }
        final String parameter3 = this.getParameter("mask");
        if (parameter3 != null) {
            this.maskname = parameter3;
        }
        final String parameter4 = this.getParameter("hsimage");
        if (parameter4 != null) {
            this.hsimage = parameter4;
        }
        final String parameter5 = this.getParameter("exposure");
        if (parameter5 != null) {
            this.exposure = Double.valueOf(parameter5);
        }
        final String parameter6 = this.getParameter("gamma");
        if (parameter6 != null) {
            this.gamma = Double.valueOf(parameter6);
        }
        final String parameter7 = this.getParameter("fov");
        if (parameter7 != null) {
            this.chfov = Double.valueOf(parameter7);
        }
        final String parameter8 = this.getParameter("pan");
        if (parameter8 != null) {
            this.cyaw = Double.valueOf(parameter8);
        }
        final String parameter9 = this.getParameter("tilt");
        if (parameter9 != null) {
            this.cpitch = Double.valueOf(parameter9);
        }
        final String parameter10 = this.getParameter("rot");
        if (parameter10 != null) {
            this.croll = Double.valueOf(parameter10);
        }
        final String parameter11 = this.getParameter("showSHS");
        if (parameter11 != null) {
            this.showSHS = Integer.parseInt(parameter11);
        }
        final String parameter12 = this.getParameter("progress");
        if (parameter12 != null && parameter12.equalsIgnoreCase("true")) {
            this.progress = true;
        }
        if (this.getParameter("antialias") != null) {
            this.antialias = true;
            this.max_oversampling = 1.5;
        }
        final String parameter13 = this.getParameter("oversampling");
        if (parameter13 != null) {
            this.max_oversampling = Double.valueOf(parameter13);
        }
        if (this.getParameter("single") != null) {
            this.single = true;
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
                if (this.zoom_image == null) {
                    if (this.loadImages == null) {
                        (this.loadImages = new Thread(this)).start();
                    }
                }
                else {
                    this.load_zoom_image();
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
    
    public synchronized void set(final double chfov, final double cyaw, final double cpitch, final double croll, final int[][] data) {
        this.chfov = chfov;
        this.cyaw = cyaw;
        this.cpitch = cpitch;
        this.croll = croll;
        if (data != this.idata.data) {
            this.width = data[0].length;
            this.height = data.length;
            if (this.hsdata[0].length != this.width || this.hsdata.length != this.height) {
                this.hsdata = new byte[this.height][this.width];
                for (int i = 0; i < this.height; ++i) {
                    for (int j = 0; j < this.width; ++j) {
                        this.hsdata[i][j] = -1;
                    }
                }
            }
            this.idata.data = data;
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
    
    public synchronized void setExposure(final double exposure) {
        this.exposure = exposure;
    }
    
    public void run() {
        this.load_zoom_image();
    }
    
    void load_zoom_image() {
        if (this.zoom_image == null) {
            this.idata = new ImageData(this.filename, this.pv, this.progress, this.exposure, this.gamma);
        }
        else {
            this.idata = new ImageData(this.zoom_image, this.pv);
        }
        this.width = this.idata.width;
        this.height = this.idata.height;
        if (this.maskname != null) {
            final Image loadImage = this.pv.loadImage(this.maskname);
            if (loadImage != null && loadImage.getWidth(null) == this.width && loadImage.getHeight(null) == this.height) {
                this.pv.ptImageToAlpha(this.idata.data, loadImage);
            }
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
                final Image loadImage2 = this.pv.loadImage(this.hsimage);
                if (loadImage2 != null && loadImage2.getWidth(null) == this.width && loadImage2.getHeight(null) == this.height) {
                    final int[][] array = new int[this.height][this.width];
                    this.pv.ptImageTo2DArray(array, loadImage2);
                    for (int n = 0; n < this.height; ++n) {
                        for (int n2 = 0; n2 < this.width; ++n2) {
                            this.hsdata[n][n2] = (byte)(array[n][n2] >> 24);
                        }
                    }
                    System.gc();
                }
            }
        }
        this.SetDefMatrix();
        if (this.single) {
            this.pv.hfov_max = this.chfov;
            if (this.pv.hfov > this.chfov) {
                this.pv.hfov = this.chfov;
            }
            if (this.pv.yaw_max > this.chfov / 2.0) {
                this.pv.yaw_max = this.chfov / 2.0;
            }
            if (this.pv.yaw_min < -this.chfov / 2.0) {
                this.pv.yaw_min = -this.chfov / 2.0;
            }
            final double pitch_max = 90.0 - this.pv.math_view2pano(0, 0, this.width, this.height, 360, 180, 0.0, 0.0, this.chfov)[1];
            if (this.pv.pitch_max > pitch_max) {
                this.pv.pitch_max = pitch_max;
            }
            if (this.pv.pitch_min < -pitch_max) {
                this.pv.pitch_min = -pitch_max;
            }
        }
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
    
    public void paint(final Graphics graphics) {
        if (this.pv == null || this.hsdata == null || this.idata == null || this.idata.data == null || this.pv.vdata == null) {
            return;
        }
        if (this.antialias && this.scaledImages == null) {
            (this.scaledImages = new Vector()).addElement(this.idata);
            ImageData imageData = this.idata;
            final double n = this.pv.hfov_max / (this.pv.vwidth * this.chfov * this.max_oversampling);
            int n2 = 0;
            while (imageData != null && imageData.width * n > 1.0) {
                imageData = this.pv.im_halfsize(imageData);
                this.scaledImages.addElement(imageData);
                ++n2;
            }
            (this.scaledHSImages = new Vector()).addElement(this.hsdata);
            byte[][] array = this.hsdata;
            while (array != null && array[0].length * n > 1.0) {
                array = this.pv.hsim_halfsize(array);
                this.scaledHSImages.addElement(array);
            }
        }
        if (this.pv.dirty && (this.antialias || this.pv.hfov * this.width < this.max_oversampling * this.chfov * this.pv.vwidth)) {
            ImageData idata = this.idata;
            byte[][] hsdata = this.hsdata;
            if (this.antialias && this.scaledImages != null) {
                final double n3 = this.pv.hfov / (this.pv.vwidth * this.chfov * this.max_oversampling);
                int n4 = 0;
                for (int width = idata.width; width * n3 > 1.0; width /= 2) {
                    ++n4;
                }
                if (this.scaledImages.elementAt(n4) != null) {
                    idata = (ImageData)this.scaledImages.elementAt(n4);
                    hsdata = (byte[][])this.scaledHSImages.elementAt(n4);
                }
            }
            switch (this.pv.quality) {
                case 0: {
                    this.pv.dirty = !this.SetView(idata, hsdata, false);
                    break;
                }
                case 1: {
                    if (this.pv.panning || this.pv.lastframe > this.pv.frames) {
                        this.SetView(idata, hsdata, false);
                        break;
                    }
                    this.pv.dirty = !this.SetView(idata, hsdata, true);
                    break;
                }
                case 2: {
                    if (this.pv.panning) {
                        this.SetView(idata, hsdata, false);
                        break;
                    }
                    this.pv.dirty = !this.SetView(idata, hsdata, true);
                    break;
                }
                case 3: {
                    this.pv.dirty = !this.SetView(idata, hsdata, true);
                    break;
                }
            }
        }
    }
    
    boolean SetView(final ImageData imageData, final byte[][] array, final boolean b) {
        boolean b2 = true;
        this.SetMatrix(this.pv.pitch * 2.0 * 3.141592653589793 / 360.0, (this.pv.yaw - this.cyaw) * 2.0 * 3.141592653589793 / 360.0, 1);
        if (this.position == 1) {
            this.ShiftCubeFace(1.5707963267948966);
        }
        else if (this.position == 2) {
            this.ShiftCubeFace(-1.5707963267948966);
        }
        else if (this.position == 3) {
            this.TiltCubeFace(-1.5707963267948966);
        }
        else if (this.position == 4) {
            this.TiltCubeFace(1.5707963267948966);
        }
        final double n = this.pv.vwidth / (2.0 * Math.tan(this.pv.hfov * 3.141592653589793 / 180.0 / 2.0));
        final double n2 = imageData.width / (2.0 * Math.tan(this.chfov * 3.141592653589793 / 180.0 / 2.0));
        for (int i = 0; i < 3; ++i) {
            final double[] array2 = this.mt[2];
            final int n3 = i;
            array2[n3] *= n;
        }
        for (int j = 0; j < 3; ++j) {
            for (int k = 0; k < 2; ++k) {
                final double[] array3 = this.mt[j];
                final int n4 = k;
                array3[n4] *= n2;
            }
        }
        double n5;
        for (n5 = 256.0; n * n * n5 / 256.0 > 1.0E7; n5 /= 2.0) {}
        final int n6 = (int)(n5 * this.mt[0][0] + 0.5);
        final int n7 = (int)(n5 * this.mt[0][1] + 0.5);
        final int n8 = (int)(n5 * this.mt[0][2] + 0.5);
        final int n9 = (int)(n5 * this.mt[1][0] + 0.5);
        final int n10 = (int)(n5 * this.mt[1][1] + 0.5);
        final int n11 = (int)(n5 * this.mt[1][2] + 0.5);
        final int n12 = (int)(n5 * this.mt[2][0] + 0.5);
        final int n13 = (int)(n5 * this.mt[2][1] + 0.5);
        final int n14 = (int)(n5 * this.mt[2][2] + 0.5);
        final int n15 = (this.pv.vwidth - 1) / 2;
        final int n16 = this.pv.vheight / 2;
        final int n17 = imageData.width * 128 + 64;
        final int n18 = imageData.height * 128 + 64;
        final int n19 = imageData.height - 1;
        final int n20 = imageData.width - 1;
        final byte[] array4 = array[0];
        this.idata.setLut(this.exposure);
        if (b) {
            for (int l = 0, n21 = -n16 * n11 + n14 + 128, n22 = -n16 * n9 + n12, n23 = -n16 * n10 + n13; l < this.pv.vheight; ++l, n21 += n11, n22 += n9, n23 += n10) {
                final int n24 = this.pv.vwidth * l;
                int n25 = 0;
                int n26 = n24;
                final int n27 = -n15 * n8 + n21;
                final int n28 = -n15 * n6 + n22;
                final int n29 = -n15 * n7 + n23;
                while (n25 < this.pv.vwidth) {
                    if (this.pv.vdata[n26] == 0) {
                        final int n30 = (n27 + n25 * n8) / 256;
                        if (n30 <= 0) {
                            b2 = false;
                        }
                        else {
                            final int n31 = (n28 + n25 * n6) / n30 + n17;
                            final int n32 = (n29 + n25 * n7) / n30 + n18;
                            final int n33 = n31 & 0xFF;
                            int n34 = n31 >> 8;
                            final int n35 = n32 & 0xFF;
                            int n36 = n32 >> 8;
                            if (n34 >= 0 && n36 >= 0 && n36 < n19 && n34 < n20) {
                                if ((imageData.getPixel(n34, n36) & 0xFF000000) != 0x0) {
                                    this.pv.hs_vdata[n26] = array[n36][n34];
                                    this.pv.vdata[n26] = imageData.getPixel(n34, n36, n33, n35);
                                }
                                else {
                                    b2 = false;
                                }
                            }
                            else {
                                if (n36 == -1) {
                                    n36 = 0;
                                }
                                else if (n36 == imageData.height) {
                                    n36 = n19;
                                }
                                if (n34 == -1) {
                                    n34 = 0;
                                }
                                else if (n34 == imageData.width) {
                                    n34 = n20;
                                }
                                final int pixel;
                                if (n34 >= 0 && n34 < imageData.width && n36 >= 0 && n36 < imageData.height && ((pixel = imageData.getPixel(n34, n36)) & 0xFF000000) != 0x0) {
                                    this.pv.vdata[n26] = pixel;
                                    this.pv.hs_vdata[n26] = array[n36][n34];
                                }
                                else {
                                    b2 = false;
                                }
                            }
                        }
                    }
                    ++n25;
                    ++n26;
                }
            }
        }
        else {
            final int n37 = n17 + 128;
            final int n38 = n18 + 128;
            for (int n39 = 0, n40 = -n16 * n11 + n14 + 128, n41 = -n16 * n9 + n12, n42 = -n16 * n10 + n13; n39 < this.pv.vheight; ++n39, n40 += n11, n41 += n9, n42 += n10) {
                final int n43 = this.pv.vwidth * n39;
                int n44 = 0;
                int n45 = n43;
                final int n46 = -n15 * n8 + n40;
                final int n47 = -n15 * n6 + n41;
                final int n48 = -n15 * n7 + n42;
                while (n44 < this.pv.vwidth) {
                    if (this.pv.vdata[n45] == 0) {
                        final int n49 = (n46 + n44 * n8) / 256;
                        if (n49 <= 0) {
                            b2 = false;
                        }
                        else {
                            int n50 = (n47 + n44 * n6) / n49 + n37 >> 8;
                            int n51 = (n48 + n44 * n7) / n49 + n38 >> 8;
                            final int pixel2;
                            if (n50 >= 0 && n50 < imageData.width && n51 >= 0 && n51 < imageData.height && ((pixel2 = imageData.getPixel(n50, n51)) & 0xFF000000) != 0x0) {
                                this.pv.vdata[n45] = pixel2;
                            }
                            else {
                                if (n50 == -1) {
                                    n50 = 0;
                                }
                                else if (n50 == imageData.width) {
                                    n50 = n20;
                                }
                                if (n51 == -1) {
                                    n51 = 0;
                                }
                                else if (n51 == imageData.height) {
                                    n51 = n19;
                                }
                                final int pixel3;
                                if (n50 >= 0 && n50 < imageData.width && n51 >= 0 && n51 < imageData.height && ((pixel3 = imageData.getPixel(n50, n51)) & 0xFF000000) != 0x0) {
                                    this.pv.vdata[n45] = pixel3;
                                }
                                else {
                                    b2 = false;
                                }
                            }
                        }
                    }
                    ++n44;
                    ++n45;
                }
            }
        }
        return b2;
    }
    
    void SetMatrix(final double n, final double n2, final int n3) {
        final double[][] array = new double[3][3];
        final double[][] array2 = new double[3][3];
        final double[][] array3 = new double[3][3];
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
            this.pv.matrix_matrix_mult(array, array2, array3);
        }
        else {
            this.pv.matrix_matrix_mult(array2, array, array3);
        }
        this.pv.matrix_matrix_mult(array3, this.md, this.mt);
    }
    
    void SetDefMatrix() {
        final double[][] array = new double[3][3];
        final double[][] array2 = new double[3][3];
        final double n = -this.cpitch * 2.0 * 3.141592653589793 / 360.0;
        final double n2 = -this.croll * 2.0 * 3.141592653589793 / 360.0;
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
        array2[0][1] = Math.sin(n2);
        array2[0][2] = 0.0;
        array2[1][0] = -array2[0][1];
        array2[1][1] = array2[0][0];
        array2[1][2] = 0.0;
        array2[2][0] = 0.0;
        array2[2][1] = 0.0;
        array2[2][2] = 1.0;
        this.pv.matrix_matrix_mult(array, array2, this.md);
    }
    
    public String getAppletInfo() {
        return "topFrame";
    }
    
    void ShiftCubeFace(final double n) {
        final double[][] array = new double[3][3];
        final double[][] array2 = new double[3][3];
        array2[0][0] = Math.cos(n);
        array2[0][1] = 0.0;
        array2[0][2] = -Math.sin(n);
        array2[1][0] = 0.0;
        array2[1][1] = 1.0;
        array2[1][2] = 0.0;
        array2[2][0] = -array2[0][2];
        array2[2][1] = 0.0;
        array2[2][2] = array2[0][0];
        this.pv.matrix_matrix_mult(this.mt, array2, array);
        for (int i = 0; i < 3; ++i) {
            for (int j = 0; j < 3; ++j) {
                this.mt[i][j] = array[i][j];
            }
        }
    }
    
    void TiltCubeFace(final double n) {
        final double[][] array = new double[3][3];
        final double[][] array2 = new double[3][3];
        array[0][0] = 1.0;
        array[0][1] = 0.0;
        array[0][2] = 0.0;
        array[1][0] = 0.0;
        array[1][1] = Math.cos(n);
        array[1][2] = Math.sin(n);
        array[2][0] = 0.0;
        array[2][1] = -array[1][2];
        array[2][2] = array[1][1];
        this.pv.matrix_matrix_mult(this.mt, array, array2);
        for (int i = 0; i < 3; ++i) {
            for (int j = 0; j < 3; ++j) {
                this.mt[i][j] = array2[i][j];
            }
        }
    }
}
