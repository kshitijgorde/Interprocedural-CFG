import java.awt.Color;
import java.awt.event.MouseEvent;
import java.awt.image.ImageObserver;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseListener;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class MovingFractalTree extends Applet implements Runnable, MouseListener, MouseMotionListener
{
    private static final long serialVersionUID = 1L;
    private boolean rotate_with_vectors;
    private boolean rotate_with_quats;
    private final int N_ITER_MAX = 11;
    private final int N_ITER_MIN = 2;
    private final int N_ITER_INICIAL = 6;
    private int n_iter;
    private final double N_TIMES = 20.0;
    private int tpoints;
    private int[] nodo;
    private double[] x;
    private double[] y;
    private double[] z;
    private final double BRANCH_FACTOR_2D;
    private final double BRANCH_FACTOR_3D;
    private double width;
    private int screen_width;
    private double offsetx;
    private double offsety;
    private double kz;
    private final double colormax = 210.0;
    private final double colormin = 0.0;
    private final double colorrango = 210.0;
    private final double DIAGO2D;
    private final double DIAGO3D;
    private double multi;
    private double suma;
    private double vd;
    private int screen_height;
    private boolean perspective;
    private int tipoArbol;
    private int tipoArbolBak;
    private double qw;
    private double qx;
    private double qy;
    private double qz;
    private double theta;
    private double nx;
    private double ny;
    private double nz;
    private double start_time;
    private double sintheta;
    private double costheta;
    private String info_n_iter;
    private String info_dimens;
    private int offset_fontX1;
    private int offset_fontX2;
    private int offset_fontY1;
    private double n_redraws;
    private double time_elapsed;
    private boolean doubleclicked;
    private int border_width;
    private int interior_width;
    private int interior_height;
    private int fontsize;
    private int mx;
    private int my;
    private boolean mouse_pressed;
    private int azimuth;
    private int elevation;
    private final int INTERIORGREY = 254;
    private final int TOPE = 243;
    private final int BORDERGREY = 230;
    private final int BUTTON3_MASK = 4;
    private final int BUTTON3_DOWN_MASK = 4096;
    Image dbImage;
    Graphics dbg;
    
    public MovingFractalTree() {
        this.rotate_with_vectors = false;
        this.rotate_with_quats = !this.rotate_with_vectors;
        this.tpoints = (int)Math.pow(2.0, 12.0);
        this.nodo = new int[8];
        this.x = new double[this.tpoints];
        this.y = new double[this.tpoints];
        this.z = new double[this.tpoints];
        this.BRANCH_FACTOR_2D = 1.0 / Math.pow(2.0, 0.5);
        this.BRANCH_FACTOR_3D = 1.0 / Math.pow(2.0, 0.3333333333333333);
        this.DIAGO2D = Math.sqrt(1.5);
        this.DIAGO3D = Math.sqrt(1.0 + Math.pow(0.5, 0.6666666666666666) + Math.pow(0.5, 1.3333333333333333));
        this.perspective = true;
        this.tipoArbol = 0;
        this.tipoArbolBak = 0;
        this.n_redraws = 0.0;
        this.doubleclicked = false;
        this.mouse_pressed = false;
    }
    
    public void init() {
        this.addMouseListener(this);
        this.addMouseMotionListener(this);
        this.screen_width = this.getSize().width;
        this.offsetx = this.screen_width / 2;
        this.offsety = this.screen_width / 2;
        this.screen_height = this.getSize().height;
        this.border_width = this.screen_width / 50;
        this.interior_width = this.screen_width - 2 * this.border_width;
        this.interior_height = this.screen_height - 2 * this.border_width;
        this.fontsize = (int)(this.screen_width / 3.8);
        this.setFont(new Font("Monospaced", 1, this.fontsize));
        final double wid_heigh_char_ratio = 0.61;
        final double marginX = 0.05859375;
        this.offset_fontX1 = (int)(this.screen_width * marginX);
        this.offset_fontY1 = this.offset_fontX1;
        this.offset_fontY1 = this.screen_height - this.offset_fontY1 - (int)(this.fontsize * 0.052);
        this.offset_fontX2 = this.screen_width - this.offset_fontX1 - (int)(3.0 * wid_heigh_char_ratio * this.fontsize);
        this.n_iter = 11;
        this.initCommon();
        try {
            Thread.sleep(200L);
        }
        catch (InterruptedException e) {
            e.printStackTrace();
        }
        this.n_iter = 6;
        this.initCommon();
    }
    
    public void initCommon() {
        if (this.rotate_with_vectors) {
            this.generateRandomUnitVectorAndSmallAngle();
        }
        else {
            this.generateRandomUnitQuaternionWithSmallAngle();
        }
        this.info_n_iter = new StringBuffer().append(this.n_iter).toString();
        if (this.tipoArbol == 0) {
            this.width = 0.9 * this.screen_width;
            this.initTree2D();
            this.info_dimens = "2-D";
            this.multi = 210.0 / (this.DIAGO2D * this.width);
        }
        if (this.tipoArbol == 1) {
            this.width = 0.6 * this.screen_width;
            if (this.tipoArbolBak == 0) {
                this.initTree3D();
            }
            if (this.tipoArbolBak == 1) {
                this.initTree3D();
            }
            this.info_dimens = "3-D";
            this.multi = 210.0 / (this.DIAGO3D * this.width);
        }
        if (this.tipoArbol == 2) {
            this.width = 0.6 * this.screen_width;
            if (this.tipoArbolBak == 0) {
                this.initTree3D();
            }
            if (this.tipoArbolBak == 2) {
                this.initTree3D();
            }
            this.info_dimens = "Pln";
            this.multi = 210.0 / (this.DIAGO3D * this.width);
        }
        this.tipoArbolBak = this.tipoArbol;
        this.vd = this.width * 0.7;
        this.suma = 105.0;
        this.n_redraws = 0.0;
        this.start_time = System.currentTimeMillis();
    }
    
    public void simpleInitCommon() {
        this.info_n_iter = new StringBuffer().append(this.n_iter).toString();
        this.n_redraws = 0.0;
        this.start_time = System.currentTimeMillis();
    }
    
    public void start() {
        final Thread th = new Thread(this);
        th.start();
    }
    
    public void stop() {
    }
    
    public void destroy() {
    }
    
    public void run() {
        Thread.currentThread().setPriority(1);
        while (true) {
            try {
                Thread.sleep(44L);
            }
            catch (InterruptedException ex) {}
            Thread.currentThread().setPriority(10);
            this.repaint();
        }
    }
    
    public void paint(final Graphics g) {
        if (!this.mouse_pressed) {
            if (Math.random() < 0.05) {
                if (this.rotate_with_vectors) {
                    this.generateRandomUnitVectorAndSmallAngle();
                }
                else {
                    this.generateRandomUnitQuaternionWithSmallAngle();
                }
            }
            if (!this.rotate_with_quats) {
                this.rotateEntireFigureWithVectorAndAngle();
            }
            else {
                this.rotateEntireFigureWithQuaternions();
            }
        }
        this.drawTree(g);
        if (this.tipoArbol == 0) {
            this.rotateBranchesRandomly();
        }
        ++this.n_redraws;
        this.time_elapsed = System.currentTimeMillis() - this.start_time;
    }
    
    public void initTree2D() {
        this.x[this.tpoints / 2] = 0.0;
        this.y[this.tpoints / 2] = 0.0;
        this.z[this.tpoints / 2] = 0.0;
        long counter = 0L;
        for (int i = 0; i < 11; ++i) {
            final double half_branch_length = this.width * Math.pow(this.BRANCH_FACTOR_2D, i + 4);
            int j;
            for (int paso = j = (int)Math.rint(this.tpoints / 4 * Math.pow(0.5, i)); j < this.tpoints; j += 4 * paso) {
                ++counter;
                final int jp = j + paso;
                final int j2p = j + 2 * paso;
                if (i % 2 != 1) {
                    this.x[j] = this.x[jp] - half_branch_length;
                    this.x[j2p] = this.x[jp] + half_branch_length;
                    this.y[j] = this.y[jp];
                    this.y[j2p] = this.y[jp];
                }
                else {
                    this.x[j] = this.x[jp];
                    this.x[j2p] = this.x[jp];
                    this.y[j] = this.y[jp] - half_branch_length;
                    this.y[j2p] = this.y[jp] + half_branch_length;
                }
                this.z[j] = 0.0;
                this.z[j2p] = 0.0;
            }
        }
    }
    
    public void initTree3D() {
        this.x[this.tpoints / 2] = 0.0;
        this.y[this.tpoints / 2] = 0.0;
        this.z[this.tpoints / 2] = 0.0;
        long counter = 0L;
        for (int i = 0; i < 11; ++i) {
            final double half_branch_length = this.width * Math.pow(this.BRANCH_FACTOR_3D, i + 6);
            int j;
            for (int paso = j = (int)Math.rint(this.tpoints / 4 * Math.pow(0.5, i)); j < this.tpoints; j += 4 * paso) {
                ++counter;
                final int jp = j + paso;
                final int j2p = j + 2 * paso;
                if (i % 3 == 0) {
                    this.x[j] = this.x[jp] - half_branch_length;
                    this.x[j2p] = this.x[jp] + half_branch_length;
                    this.y[j] = this.y[jp];
                    this.y[j2p] = this.y[jp];
                    this.z[j] = this.z[jp];
                    this.z[j2p] = this.z[jp];
                }
                if (i % 3 == 1) {
                    this.x[j] = this.x[jp];
                    this.x[j2p] = this.x[jp];
                    this.y[j] = this.y[jp] - half_branch_length;
                    this.y[j2p] = this.y[jp] + half_branch_length;
                    this.z[j] = this.z[jp];
                    this.z[j2p] = this.z[jp];
                }
                if (i % 3 == 2) {
                    this.x[j] = this.x[jp];
                    this.x[j2p] = this.x[jp];
                    this.y[j] = this.y[jp];
                    this.y[j2p] = this.y[jp];
                    this.z[j] = this.z[jp] - half_branch_length;
                    this.z[j2p] = this.z[jp] + half_branch_length;
                }
            }
        }
    }
    
    private void drawTree(final Graphics g) {
        int t = 3;
        if (this.n_iter < t) {
            t = this.n_iter;
        }
        for (int i = 0; i < t; ++i) {
            final int paso = (int)Math.rint(this.tpoints / 4 * Math.pow(0.5, i));
            if (this.perspective) {
                for (int j = paso; j < this.tpoints; j += 4 * paso) {
                    if (this.tipoArbol < 2) {
                        this.dibujaRamasConPers(j, paso, g);
                    }
                    else {
                        this.dibujaPlanosConPers(j, paso, i, g);
                    }
                }
            }
            else {
                for (int j = paso; j < this.tpoints; j += 4 * paso) {
                    if (this.tipoArbol < 2) {
                        this.dibujaRamasSinPers(j, paso, g);
                    }
                    else {
                        this.dibujaPlanosSinPers(j, paso, i, g);
                    }
                }
            }
        }
        if (this.n_iter > 3) {
            for (int k = 0; k < 8; ++k) {
                this.nodo[k] = (2 * k + 1) * this.tpoints / 16;
            }
            for (int i = 0; i < 8; ++i) {
                for (int l = i + 1; l < 8; ++l) {
                    if (this.z[this.nodo[i]] < this.z[this.nodo[l]]) {
                        final int guarda = this.nodo[i];
                        this.nodo[i] = this.nodo[l];
                        this.nodo[l] = guarda;
                    }
                }
            }
            for (int k = 0; k < 8; ++k) {
                for (int m = t; m < this.n_iter; ++m) {
                    final int paso2 = (int)Math.rint(this.tpoints / 4 * Math.pow(0.5, m));
                    if (this.perspective) {
                        for (int s = (int)(Math.pow(2.0, m - 2) - 1.0) * paso2, j2 = this.nodo[k] - s; j2 < this.nodo[k] + s; j2 += 4 * paso2) {
                            if (this.tipoArbol < 2) {
                                this.dibujaRamasConPers(j2, paso2, g);
                            }
                            else {
                                this.dibujaPlanosConPers(j2, paso2, m, g);
                            }
                        }
                    }
                    else {
                        for (int s = (int)(Math.pow(2.0, m - 2) - 1.0) * paso2, j2 = this.nodo[k] - s; j2 < this.nodo[k] + s; j2 += 4 * paso2) {
                            if (this.tipoArbol < 2) {
                                this.dibujaRamasSinPers(j2, paso2, g);
                            }
                            else {
                                this.dibujaPlanosSinPers(j2, paso2, m, g);
                            }
                        }
                    }
                }
            }
        }
    }
    
    private void rotateBranchesRandomly() {
        for (int i = 0; i < 11; ++i) {
            long counter = 0L;
            int j;
            for (int paso = j = (int)Math.rint(this.tpoints / 4 * Math.pow(0.5, i)); j < this.tpoints; j += 2 * paso) {
                final double ax = this.x[j];
                final double ay = this.y[j];
                final double az = this.z[j];
                final int p2 = 2 * paso;
                ++counter;
                double bx;
                double by;
                double bz;
                if (counter % 2L != 0L) {
                    final int jplsp2 = j + p2;
                    bx = this.x[jplsp2];
                    by = this.y[jplsp2];
                    bz = this.z[jplsp2];
                }
                else {
                    final int jmnsp2 = j - p2;
                    bx = this.x[jmnsp2];
                    by = this.y[jmnsp2];
                    bz = this.z[jmnsp2];
                }
                final double xdif = bx - ax;
                final double ydif = by - ay;
                final double zdif = bz - az;
                final double mn = Math.sqrt(xdif * xdif + ydif * ydif + zdif * zdif);
                final double nx = xdif / mn;
                final double ny = ydif / mn;
                final double nz = zdif / mn;
                final double theta = 4.0 * (Math.random() - 0.5) * 3.141592653589793 / 180.0;
                final double costheta = Math.cos(theta);
                final double sintheta = Math.sin(theta);
                for (int k = j - paso + 1; k < j + paso; ++k) {
                    final double px = this.x[k] - ax;
                    final double py = this.y[k] - ay;
                    final double pz = this.z[k] - az;
                    final double npescalar = nx * px + ny * py + nz * pz;
                    final double nnpx = npescalar * nx;
                    final double nnpy = npescalar * ny;
                    final double nnpz = npescalar * nz;
                    final double cx = costheta * (px - nnpx) + nnpx;
                    final double cy = costheta * (py - nnpy) + nnpy;
                    final double cz = costheta * (pz - nnpz) + nnpz;
                    this.x[k] = sintheta * (ny * pz - nz * py) + cx + ax;
                    this.y[k] = sintheta * (nz * px - nx * pz) + cy + ay;
                    this.z[k] = sintheta * (nx * py - ny * px) + cz + az;
                }
            }
        }
    }
    
    private void generateRandomUnitQuaternionWithSmallAngle() {
        final double theta = 3.0 * Math.random() * 3.141592653589793 / 180.0;
        final double halph_theta = theta / 2.0;
        this.qw = Math.cos(halph_theta);
        final double x = Math.random() - 0.5;
        final double y = Math.random() - 0.5;
        final double z = Math.random() - 0.5;
        final double modu = Math.sqrt(x * x + y * y + z * z);
        final double sinhalphtheta = Math.sin(halph_theta);
        this.qx = sinhalphtheta * x / modu;
        this.qy = sinhalphtheta * y / modu;
        this.qz = sinhalphtheta * z / modu;
    }
    
    private void generateRandomUnitVectorAndSmallAngle() {
        this.theta = 3.0 * Math.random() * 3.141592653589793 / 180.0;
        final double x = Math.random() - 0.5;
        final double y = Math.random() - 0.5;
        final double z = Math.random() - 0.5;
        final double modu = Math.sqrt(x * x + y * y + z * z);
        this.sintheta = Math.sin(this.theta);
        this.costheta = Math.cos(this.theta);
        this.nx = x / modu;
        this.ny = y / modu;
        this.nz = z / modu;
    }
    
    private void rotateEntireFigureWithQuaternions() {
        for (int i = 1; i < this.tpoints; ++i) {
            final double px = this.x[i];
            final double py = this.y[i];
            final double pz = this.z[i];
            final double wqp = -this.qx * px - this.qy * py - this.qz * pz;
            final double xqp = this.qw * px + this.qy * pz - this.qz * py;
            final double yqp = this.qw * py - this.qx * pz + this.qz * px;
            final double zqp = this.qw * pz + this.qx * py - this.qy * px;
            this.x[i] = -wqp * this.qx + xqp * this.qw - yqp * this.qz + zqp * this.qy;
            this.y[i] = -wqp * this.qy + xqp * this.qz + yqp * this.qw - zqp * this.qx;
            this.z[i] = -wqp * this.qz - xqp * this.qy + yqp * this.qx + zqp * this.qw;
        }
    }
    
    private void rotateEntireFigureWithVectorAndAngle() {
        for (int i = 1; i < this.tpoints; ++i) {
            final double px = this.x[i];
            final double py = this.y[i];
            final double pz = this.z[i];
            final double npescalar = px * this.nx + py * this.ny + pz * this.nz;
            final double nnpx = npescalar * this.nx;
            final double nnpy = npescalar * this.ny;
            final double nnpz = npescalar * this.nz;
            this.x[i] = this.sintheta * (this.ny * pz - this.nz * py) + this.costheta * (px - nnpx) + nnpx;
            this.y[i] = this.sintheta * (this.nz * px - this.nx * pz) + this.costheta * (py - nnpy) + nnpy;
            this.z[i] = this.sintheta * (this.nx * py - this.ny * px) + this.costheta * (pz - nnpz) + nnpz;
        }
    }
    
    public void update(final Graphics g) {
        if (this.dbImage == null) {
            this.dbImage = this.createImage(this.getSize().width, this.getSize().height);
            this.dbg = this.dbImage.getGraphics();
        }
        this.greyBackground();
        this.paint(this.dbg);
        g.drawImage(this.dbImage, 0, 0, this);
    }
    
    public void mousePressed(final MouseEvent e) {
        this.mx = e.getX();
        this.my = e.getY();
        e.consume();
        this.mouse_pressed = true;
    }
    
    public void mouseReleased(final MouseEvent e) {
        this.mouse_pressed = false;
    }
    
    public void mouseEntered(final MouseEvent e) {
    }
    
    public void mouseExited(final MouseEvent e) {
    }
    
    public void mouseMoved(final MouseEvent e) {
    }
    
    public void mouseDragged(final MouseEvent e) {
        final int new_mx = e.getX();
        final int new_my = e.getY();
        this.azimuth = -new_mx + this.mx;
        this.elevation = new_my - this.my;
        this.mx = new_mx;
        this.my = new_my;
        double modu = Math.sqrt(this.elevation * this.elevation + this.azimuth * this.azimuth);
        if (modu == 0.0) {
            modu = 1.0E-4;
        }
        final double theta = modu * 3.141592653589793 / this.screen_width;
        this.sintheta = Math.sin(theta);
        this.costheta = Math.cos(theta);
        this.nx = this.elevation / modu;
        this.ny = this.azimuth / modu;
        this.nz = 0.0;
        final double halph_theta = theta / 2.0;
        this.qw = Math.cos(halph_theta);
        final double sinhalphtheta = Math.sin(halph_theta);
        this.qx = sinhalphtheta * this.elevation / modu;
        this.qy = sinhalphtheta * this.azimuth / modu;
        this.qz = 0.0;
        if (this.rotate_with_vectors) {
            this.rotateEntireFigureWithVectorAndAngle();
        }
        else {
            this.rotateEntireFigureWithQuaternions();
        }
        this.repaint();
        e.consume();
    }
    
    public void mouseClicked(final MouseEvent e) {
        final int ev = e.getModifiers();
        if (e.getClickCount() == 2) {
            if (ev != 4 & ev != 4096) {
                ++this.tipoArbol;
                if (this.tipoArbol == 3) {
                    this.tipoArbol = 0;
                }
                if (this.n_iter != 2) {
                    --this.n_iter;
                }
                else {
                    this.n_iter = 11;
                }
                this.doubleclicked = true;
            }
            this.initCommon();
        }
        else {
            if (ev != 4 & ev != 4096) {
                ++this.n_iter;
                if (this.n_iter > 11) {
                    this.n_iter = 2;
                }
            }
            else {
                --this.n_iter;
                if (this.n_iter < 2) {
                    this.n_iter = 11;
                }
            }
            this.doubleclicked = false;
        }
        this.simpleInitCommon();
    }
    
    public void greyBackground() {
        Color cl = new Color(230, 230, 230);
        this.dbg.setColor(cl);
        this.dbg.fillRect(0, 0, this.screen_width, this.screen_height);
        cl = new Color(254, 254, 254);
        this.dbg.setColor(cl);
        this.dbg.fillRect(this.border_width, this.border_width, this.interior_width, this.interior_height);
        int infogreyslow = (int)this.time_elapsed / 12 + 140;
        int infogreyfast = (int)this.time_elapsed / 4 + 140;
        if (infogreyslow > 243) {
            infogreyslow = 243;
        }
        if (infogreyfast > 243) {
            infogreyfast = 243;
        }
        cl = new Color(infogreyslow, infogreyslow, infogreyslow);
        this.dbg.setColor(cl);
        if (!this.doubleclicked) {
            this.dbg.drawString(this.info_n_iter, this.offset_fontX1, this.offset_fontY1);
        }
        else {
            this.dbg.drawString(this.info_dimens, this.offset_fontX2, this.offset_fontY1);
            cl = new Color(infogreyfast, infogreyfast, infogreyfast);
            this.dbg.setColor(cl);
            this.dbg.drawString(this.info_n_iter, this.offset_fontX1, this.offset_fontY1);
        }
    }
    
    public void dibujaPlanosConPers(final int j, final int paso, final int i, final Graphics g) {
        final int j2 = j + 2 * paso;
        final int j3 = j - paso / 2;
        final int j4 = j + paso / 2;
        final int j5 = j2 - paso / 2;
        final int j6 = j2 + paso / 2;
        this.kz = this.vd / (this.vd + this.z[j]);
        final double kz2 = this.vd / (this.vd + this.z[j3]);
        final double kz3 = this.vd / (this.vd + this.z[j4]);
        final double kz4 = this.vd / (this.vd + this.z[j5]);
        final double kz5 = this.vd / (this.vd + this.z[j6]);
        final int x0 = (int)(this.x[j] * this.kz + this.offsetx);
        final int y0 = (int)(this.y[j] * this.kz + this.offsety);
        final int x2 = (int)(this.x[j3] * kz2 + this.offsetx);
        final int y2 = (int)(this.y[j3] * kz2 + this.offsety);
        final int x3 = (int)(this.x[j4] * kz3 + this.offsetx);
        final int y3 = (int)(this.y[j4] * kz3 + this.offsety);
        this.kz = this.vd / (this.vd + this.z[j2]);
        final int x4 = (int)(this.x[j2] * this.kz + this.offsetx);
        final int y4 = (int)(this.y[j2] * this.kz + this.offsety);
        final int x5 = (int)(this.x[j5] * kz4 + this.offsetx);
        final int y5 = (int)(this.y[j5] * kz4 + this.offsety);
        final int x6 = (int)(this.x[j6] * kz5 + this.offsetx);
        final int y6 = (int)(this.y[j6] * kz5 + this.offsety);
        final int gris = (int)(this.z[j + paso] * this.multi + this.suma);
        g.setColor(new Color(gris, gris, gris));
        if (i != 0) {
            g.drawLine(x0, y0, x4, y4);
        }
        if (i < this.n_iter - 1) {
            g.drawLine(x2, y2, x5, y5);
            g.drawLine(x3, y3, x6, y6);
        }
    }
    
    public void dibujaPlanosSinPers(final int j, final int paso, final int i, final Graphics g) {
        final int j2 = j + 2 * paso;
        final int j3 = j - paso / 2;
        final int j4 = j + paso / 2;
        final int j5 = j2 - paso / 2;
        final int j6 = j2 + paso / 2;
        final int x0 = (int)(this.x[j] + this.offsetx);
        final int y0 = (int)(this.y[j] + this.offsety);
        final int x2 = (int)(this.x[j3] + this.offsetx);
        final int y2 = (int)(this.y[j3] + this.offsety);
        final int x3 = (int)(this.x[j4] + this.offsetx);
        final int y3 = (int)(this.y[j4] + this.offsety);
        final int x4 = (int)(this.x[j2] + this.offsetx);
        final int y4 = (int)(this.y[j2] + this.offsety);
        final int x5 = (int)(this.x[j5] + this.offsetx);
        final int y5 = (int)(this.y[j5] + this.offsety);
        final int x6 = (int)(this.x[j6] + this.offsetx);
        final int y6 = (int)(this.y[j6] + this.offsety);
        final int gris = (int)(this.z[j + paso] * this.multi + this.suma);
        g.setColor(new Color(gris, gris, gris));
        if (i != 0) {
            g.drawLine(x0, y0, x4, y4);
        }
        if (i < this.n_iter - 1) {
            g.drawLine(x2, y2, x5, y5);
            g.drawLine(x3, y3, x6, y6);
        }
    }
    
    public void dibujaRamasConPers(final int j, final int paso, final Graphics g) {
        this.kz = this.vd / (this.vd + this.z[j]);
        final int x0 = (int)(this.x[j] * this.kz + this.offsetx);
        final int y0 = (int)(this.y[j] * this.kz + this.offsety);
        final int j2 = j + 2 * paso;
        this.kz = this.vd / (this.vd + this.z[j2]);
        final int x2 = (int)(this.x[j2] * this.kz + this.offsetx);
        final int y2 = (int)(this.y[j2] * this.kz + this.offsety);
        final int gris = (int)(this.z[j + paso] * this.multi + this.suma);
        g.setColor(new Color(gris, gris, gris));
        g.drawLine(x0, y0, x2, y2);
    }
    
    public void dibujaRamasSinPers(final int j, final int paso, final Graphics g) {
        final int x0 = (int)(this.x[j] + this.offsetx);
        final int y0 = (int)(this.y[j] + this.offsety);
        final int j2 = j + 2 * paso;
        final int x2 = (int)(this.x[j2] + this.offsetx);
        final int y2 = (int)(this.y[j2] + this.offsety);
        final int gris = (int)(this.z[j + paso] * this.multi + this.suma);
        g.setColor(new Color(gris, gris, gris));
        g.drawLine(x0, y0, x2, y2);
    }
}
