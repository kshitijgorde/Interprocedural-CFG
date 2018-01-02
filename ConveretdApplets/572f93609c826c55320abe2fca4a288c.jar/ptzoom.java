import java.awt.Graphics;
import java.awt.image.ImageObserver;
import java.applet.AppletStub;
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
    int max_oversampling;
    int position;
    
    public ptzoom() {
        this.PTViewer = "ptviewer";
        this.filename = "image";
        this.chfov = 50.0;
        this.showSHS = -1;
        this.progress = false;
        this.ready = false;
        this.max_oversampling = 8;
    }
    
    public ptzoom(final ptviewer pv, final String s) {
        this.PTViewer = "ptviewer";
        this.filename = "image";
        this.chfov = 50.0;
        this.showSHS = -1;
        this.progress = false;
        this.ready = false;
        this.max_oversampling = 8;
        this.pv = pv;
        this.setStub(new ptstub(this.pv, s));
    }
    
    public ptzoom(final ptviewer pv, final Image zoom_image, final byte[] array, final String s) {
        this.PTViewer = "ptviewer";
        this.filename = "image";
        this.chfov = 50.0;
        this.showSHS = -1;
        this.progress = false;
        this.ready = false;
        this.max_oversampling = 8;
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
        this.filename = "image";
        this.chfov = 50.0;
        this.showSHS = -1;
        this.progress = false;
        this.ready = false;
        this.max_oversampling = 8;
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
        this.max_oversampling = 1000;
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
        final String parameter5 = this.getParameter("fov");
        if (parameter5 != null) {
            this.chfov = Double.valueOf(parameter5);
        }
        final String parameter6 = this.getParameter("pan");
        if (parameter6 != null) {
            this.cyaw = Double.valueOf(parameter6);
        }
        final String parameter7 = this.getParameter("tilt");
        if (parameter7 != null) {
            this.cpitch = Double.valueOf(parameter7);
        }
        final String parameter8 = this.getParameter("rot");
        if (parameter8 != null) {
            this.croll = Double.valueOf(parameter8);
        }
        final String parameter9 = this.getParameter("showSHS");
        if (parameter9 != null) {
            this.showSHS = Integer.parseInt(parameter9);
        }
        final String parameter10 = this.getParameter("progress");
        if (parameter10 != null && parameter10.equalsIgnoreCase("true")) {
            this.progress = true;
        }
        final String parameter11 = this.getParameter("oversampling");
        if (parameter11 != null) {
            this.max_oversampling = Integer.parseInt(parameter11);
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
                return;
            }
            this.SetDefMatrix();
            this.pv.dirty = true;
            this.pv.repaint();
            this.ready = true;
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
        if (image == null) {
            return;
        }
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
        if (this.maskname != null) {
            final Image loadImage = this.pv.loadImage(this.maskname);
            if (loadImage != null && loadImage.getWidth(null) == this.width && loadImage.getHeight(null) == this.height) {
                this.pv.ptImageToAlpha(idata, loadImage);
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
    
    public void paint(final Graphics graphics) {
        if (this.pv == null || this.idata == null || this.pv.vdata == null) {
            return;
        }
        if (this.pv.dirty && this.pv.hfov * this.width < this.max_oversampling * this.chfov * this.pv.vwidth) {
            switch (this.pv.quality) {
                case 0: {
                    this.pv.dirty = !this.SetView(false);
                }
                case 1: {
                    if (this.pv.panning || this.pv.lastframe > this.pv.frames) {
                        this.SetView(false);
                        return;
                    }
                    this.pv.dirty = !this.SetView(true);
                }
                case 2: {
                    if (this.pv.panning) {
                        this.SetView(false);
                        return;
                    }
                    this.pv.dirty = !this.SetView(true);
                }
                case 3: {
                    this.pv.dirty = !this.SetView(true);
                }
            }
        }
    }
    
    boolean SetView(final boolean b) {
        boolean b2 = true;
        this.SetMatrix(this.pv.pitch * 2.0 * 3.141592653589793 / 360.0, (this.pv.yaw - this.cyaw) * 2.0 * 3.141592653589793 / 360.0, 1);
        if (this.position == 1) {
            this.ShiftCubeFace(1.5707963267948966);
        }
        else if (this.position == 2) {
            this.ShiftCubeFace(-1.5707963267948966);
        }
        final double n = this.pv.vwidth / (2.0 * Math.tan(this.pv.hfov * 3.141592653589793 / 180.0 / 2.0));
        final double n2 = this.width / (2.0 * Math.tan(this.chfov * 3.141592653589793 / 180.0 / 2.0));
        for (int i = 0; i < 3; ++i) {
            final double[] array = this.mt[2];
            final int n3 = i;
            array[n3] *= n;
        }
        for (int j = 0; j < 3; ++j) {
            for (int k = 0; k < 2; ++k) {
                final double[] array2 = this.mt[j];
                final int n4 = k;
                array2[n4] *= n2;
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
        int n17 = this.width * 128 + 64;
        int n18 = this.height * 128 + 64;
        final int n19 = this.height - 1;
        final int n20 = this.width - 1;
        int n21 = 0;
        int[] array3 = this.idata[0];
        int[] array4 = this.idata[1];
        byte[] array5 = this.hsdata[0];
        if (b) {
            for (int l = 0, n22 = -n16 * n11 + n14 + 128, n23 = -n16 * n9 + n12, n24 = -n16 * n10 + n13; l < this.pv.vheight; ++l, n22 += n11, n23 += n9, n24 += n10) {
                final int n25 = this.pv.vwidth * l;
                int n26 = 0;
                int n27 = n25;
                final int n28 = -n15 * n8 + n22;
                final int n29 = -n15 * n6 + n23;
                final int n30 = -n15 * n7 + n24;
                while (n26 < this.pv.vwidth) {
                    if (this.pv.vdata[n27] == 0) {
                        final int n31 = (n28 + n26 * n8) / 256;
                        if (n31 <= 0) {
                            b2 = false;
                        }
                        else {
                            final int n32 = (n29 + n26 * n6) / n31 + n17;
                            final int n33 = (n30 + n26 * n7) / n31 + n18;
                            final int n34 = n32 & 0xFF;
                            int n35 = n32 >> 8;
                            final int n36 = n33 & 0xFF;
                            int n37 = n33 >> 8;
                            if (n37 == n21 && n35 >= 0 && n35 < n20) {
                                final int n38 = array3[n35];
                                if ((n38 & 0xFF000000) != 0x0) {
                                    this.pv.hs_vdata[n27] = array5[n35];
                                    this.pv.vdata[n27] = ptviewer.bil(n38, array3[n35], array4[n35++], array4[n35], n34, n36);
                                }
                            }
                            else if (n35 >= 0 && n37 >= 0 && n37 < n19 && n35 < n20) {
                                n21 = n37;
                                array5 = this.hsdata[n37];
                                array3 = this.idata[n37++];
                                array4 = this.idata[n37];
                                final int n39 = array3[n35];
                                if ((n39 & 0xFF000000) != 0x0) {
                                    this.pv.hs_vdata[n27] = array5[n35];
                                    this.pv.vdata[n27] = ptviewer.bil(n39, array3[n35], array4[n35++], array4[n35], n34, n36);
                                }
                            }
                            else {
                                if (n37 == -1) {
                                    n37 = 0;
                                }
                                else if (n37 == this.height) {
                                    n37 = n19;
                                }
                                if (n35 == -1) {
                                    n35 = 0;
                                }
                                else if (n35 == this.width) {
                                    n35 = n20;
                                }
                                final int n40;
                                if (n35 >= 0 && n35 < this.width && n37 >= 0 && n37 < this.height && ((n40 = this.idata[n37][n35]) & 0xFF000000) != 0x0) {
                                    this.pv.vdata[n27] = n40;
                                    this.pv.hs_vdata[n27] = this.hsdata[n37][n35];
                                }
                                else {
                                    b2 = false;
                                }
                            }
                        }
                    }
                    ++n26;
                    ++n27;
                }
            }
        }
        else {
            n17 += 128;
            n18 += 128;
            for (int n41 = 0, n42 = -n16 * n11 + n14 + 128, n43 = -n16 * n9 + n12, n44 = -n16 * n10 + n13; n41 < this.pv.vheight; ++n41, n42 += n11, n43 += n9, n44 += n10) {
                final int n45 = this.pv.vwidth * n41;
                int n46 = 0;
                int n47 = n45;
                final int n48 = -n15 * n8 + n42;
                final int n49 = -n15 * n6 + n43;
                final int n50 = -n15 * n7 + n44;
                while (n46 < this.pv.vwidth) {
                    if (this.pv.vdata[n47] == 0) {
                        final int n51 = (n48 + n46 * n8) / 256;
                        if (n51 <= 0) {
                            b2 = false;
                        }
                        else {
                            int n52 = (n49 + n46 * n6) / n51 + n17 >> 8;
                            int n53 = (n50 + n46 * n7) / n51 + n18 >> 8;
                            final int n54;
                            if (n52 >= 0 && n52 < this.width && n53 >= 0 && n53 < this.height && ((n54 = this.idata[n53][n52]) & 0xFF000000) != 0x0) {
                                this.pv.vdata[n47] = n54;
                            }
                            else {
                                if (n52 == -1) {
                                    n52 = 0;
                                }
                                else if (n52 == this.width) {
                                    n52 = n20;
                                }
                                if (n53 == -1) {
                                    n53 = 0;
                                }
                                else if (n53 == this.height) {
                                    n53 = n19;
                                }
                                final int n55;
                                if (n52 >= 0 && n52 < this.width && n53 >= 0 && n53 < this.height && ((n55 = this.idata[n53][n52]) & 0xFF000000) != 0x0) {
                                    this.pv.vdata[n47] = n55;
                                }
                                else {
                                    b2 = false;
                                }
                            }
                        }
                    }
                    ++n46;
                    ++n47;
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
        this.pv.matrix_matrix_mult(array2, array, this.md);
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
}