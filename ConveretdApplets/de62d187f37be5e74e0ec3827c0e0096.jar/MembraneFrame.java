import java.awt.event.ItemEvent;
import java.awt.Event;
import java.awt.event.AdjustmentEvent;
import java.awt.event.ActionEvent;
import java.awt.event.ComponentEvent;
import java.awt.event.MouseEvent;
import java.awt.image.ImageObserver;
import java.awt.Graphics;
import java.awt.image.ImageProducer;
import java.awt.image.MemoryImageSource;
import java.awt.Color;
import java.awt.Label;
import java.awt.Component;
import java.awt.LayoutManager;
import java.awt.Rectangle;
import java.awt.Scrollbar;
import java.awt.Choice;
import java.awt.Checkbox;
import java.awt.Button;
import java.util.Random;
import java.awt.Image;
import java.awt.Dimension;
import java.awt.event.ItemListener;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.AdjustmentListener;
import java.awt.event.ActionListener;
import java.awt.event.ComponentListener;
import java.awt.Frame;

// 
// Decompiled by Procyon v0.5.30
// 

class MembraneFrame extends Frame implements ComponentListener, ActionListener, AdjustmentListener, MouseMotionListener, MouseListener, ItemListener
{
    Thread engine;
    Dimension winSize;
    Image dbimage;
    Random random;
    int maxTerms;
    int maxDispTerms;
    int sampleCount;
    public static final double epsilon = 1.0E-5;
    public static final double epsilon2 = 0.003;
    Button sineButton;
    Button blankButton;
    Checkbox stoppedCheck;
    Checkbox freqCheck;
    Checkbox fixedCheck;
    Checkbox colorCheck;
    Choice modeChooser;
    Choice displayChooser;
    Choice display2Chooser;
    Scrollbar dampingBar;
    Scrollbar brightnessBar;
    Scrollbar speedBar;
    Scrollbar forceBar;
    Scrollbar resBar;
    Scrollbar phasorBar;
    Scrollbar aspectBar;
    View view3d;
    View view2d;
    Rectangle viewFreq;
    boolean showMode;
    boolean editingFunc;
    boolean dragStop;
    int cell2dWidth;
    int cell2dHeight;
    double aspectRatio;
    double[][] magcoef;
    double[][] dampcoef;
    double[][] phasecoef;
    double[][] phasecoefcos;
    double[][] phasecoefadj;
    double modephasecos;
    double[][] omega;
    double[] data;
    static final double pi = 3.141592653589793;
    double step;
    double[][] func;
    double[][] funci;
    int[] xpoints;
    int[] ypoints;
    int selectedCoefX;
    int selectedCoefY;
    int selectedGridX;
    int selectedGridY;
    double selectedGridFunc;
    static final int SEL_NONE = 0;
    static final int SEL_FUNC_3D = 1;
    static final int SEL_FUNC_2D = 2;
    static final int SEL_MAG = 3;
    static final int MODE_PLUCK = 0;
    static final int MODE_STRIKE = 1;
    static final int MODE_SHAPE = 2;
    static final int MODE_VIEW_ROTATE = 3;
    static final int MODE_VIEW_ZOOM = 4;
    static final int MODE_SHOW_MODE = 5;
    static final int DISP_3D_2D = 0;
    static final int DISP_3D = 1;
    static final int DISP_2D = 2;
    static final int DISP2_SOLID = 0;
    static final int DISP2_WIRE_XY = 1;
    static final int DISP2_WIRE_X = 2;
    static final int DISP2_WIRE_Y = 3;
    static final int COLOR_HEIGHT = 0;
    static final int COLOR_VEL = 1;
    static final int COLOR_NONE = 2;
    int selection;
    int dragX;
    int dragY;
    int dragStartX;
    int dragStartY;
    boolean dragSet;
    boolean dragClear;
    double viewAngle;
    double viewAngleDragStart;
    double viewZoom;
    double viewZoomDragStart;
    double viewAngleCos;
    double viewAngleSin;
    double viewHeight;
    double viewHeightDragStart;
    double viewDistance;
    double magDragStart;
    boolean view2dSwap;
    boolean view2dReflectX;
    boolean view2dReflectY;
    boolean dragging;
    boolean needPlay;
    double t;
    int pause;
    double scalex;
    double scaley;
    int centerX3d;
    int centerY3d;
    double topz;
    MembraneCanvas cv;
    Membrane applet;
    boolean useBufferedImage;
    long lastTime;
    int[] shadowBufferTop;
    int[] shadowBufferBottom;
    int[] shadowBufferTop2;
    int[] shadowBufferBottom2;
    double logep2;
    double realxmx;
    double realxmy;
    double realymz;
    double realzmy;
    double realzmx;
    double realymadd;
    double realzmadd;
    double scaleMult;
    
    public String getAppletInfo() {
        return "Membrane by Paul Falstad";
    }
    
    int getrand(final int n) {
        int nextInt = this.random.nextInt();
        if (nextInt < 0) {
            nextInt = -nextInt;
        }
        return nextInt % n;
    }
    
    MembraneFrame(final Membrane applet) {
        super("Oscillating Membrane Applet v1.5");
        this.engine = null;
        this.maxDispTerms = 10;
        this.aspectRatio = 1.0;
        this.viewZoom = 1.6;
        this.viewAngleCos = 1.0;
        this.viewAngleSin = 0.0;
        this.viewHeight = -14.0;
        this.topz = 3.0;
        this.useBufferedImage = false;
        this.logep2 = 0.0;
        this.applet = applet;
    }
    
    public void init() {
        this.xpoints = new int[4];
        this.ypoints = new int[4];
        if (new Double(System.getProperty("java.class.version")) >= 48.0) {
            this.useBufferedImage = true;
        }
        final int n = -1;
        this.selectedCoefY = n;
        this.selectedCoefX = n;
        this.setLayout(new MembraneLayout());
        (this.cv = new MembraneCanvas(this)).addComponentListener(this);
        this.cv.addMouseMotionListener(this);
        this.cv.addMouseListener(this);
        this.add(this.cv);
        this.add(this.sineButton = new Button("Fundamental"));
        this.sineButton.addActionListener(this);
        this.add(this.blankButton = new Button("Clear"));
        this.blankButton.addActionListener(this);
        (this.stoppedCheck = new Checkbox("Stopped")).addItemListener(this);
        this.add(this.stoppedCheck);
        (this.freqCheck = new Checkbox("Show Frequencies", true)).addItemListener(this);
        this.add(this.freqCheck);
        (this.fixedCheck = new Checkbox("Fixed Edges", true)).addItemListener(this);
        this.add(this.fixedCheck);
        (this.colorCheck = new Checkbox("Color", true)).addItemListener(this);
        this.add(this.colorCheck);
        (this.modeChooser = new Choice()).add("Mouse = Poke membrane");
        this.modeChooser.add("Mouse = Strike membrane");
        this.modeChooser.add("Mouse = Shape membrane");
        this.modeChooser.add("Mouse = Adjust view angle");
        this.modeChooser.add("Mouse = Adjust view zoom");
        this.modeChooser.add("Mouse = Show mode");
        this.modeChooser.addItemListener(this);
        this.add(this.modeChooser);
        (this.displayChooser = new Choice()).add("Display 3d+2d");
        this.displayChooser.add("Display 3d only");
        this.displayChooser.add("Display 2d only");
        this.displayChooser.addItemListener(this);
        this.add(this.displayChooser);
        this.displayChooser.select(1);
        (this.display2Chooser = new Choice()).add("3d view = Solid");
        this.display2Chooser.add("3d view = Wireframe x,y");
        this.display2Chooser.add("3d view = Wireframe x");
        this.display2Chooser.add("3d view = Wireframe y");
        this.display2Chooser.addItemListener(this);
        this.add(this.display2Chooser);
        this.add(new Label("Simulation Speed", 1));
        this.add(this.speedBar = new Scrollbar(0, 100, 1, 1, 250));
        this.speedBar.addAdjustmentListener(this);
        this.add(new Label("Damping", 1));
        this.add(this.dampingBar = new Scrollbar(0, 0, 5, 0, 100));
        this.dampingBar.addAdjustmentListener(this);
        this.add(new Label("Brightness", 1));
        this.add(this.brightnessBar = new Scrollbar(0, 10, 1, 0, 150));
        this.brightnessBar.addAdjustmentListener(this);
        this.add(new Label("Resolution", 1));
        this.add(this.resBar = new Scrollbar(0, 5, 1, 3, 8));
        this.resBar.addAdjustmentListener(this);
        this.add(new Label("Aspect Ratio", 1));
        this.add(this.aspectBar = new Scrollbar(0, 10, 1, 5, 31));
        this.aspectBar.addAdjustmentListener(this);
        this.add(new Label("Freq Display Count", 1));
        this.add(this.phasorBar = new Scrollbar(0, 10, 1, 5, 128));
        this.phasorBar.addAdjustmentListener(this);
        this.setResolution();
        try {
            final String parameter = this.applet.getParameter("PAUSE");
            if (parameter != null) {
                this.pause = Integer.parseInt(parameter);
            }
        }
        catch (Exception ex) {}
        this.random = new Random();
        this.setDamping();
        this.reinit();
        this.cv.setBackground(Color.black);
        this.cv.setForeground(Color.lightGray);
        this.resize(640, 640);
        this.handleResize();
        final Dimension size = this.getSize();
        final Dimension screenSize = this.getToolkit().getScreenSize();
        this.setLocation((screenSize.width - size.width) / 2, (screenSize.height - size.height) / 2);
        this.show();
    }
    
    void reinit() {
        this.doSine();
    }
    
    void handleResize() {
        final Dimension size = this.cv.getSize();
        this.winSize = size;
        final Dimension dimension = size;
        if (this.winSize.width == 0) {
            return;
        }
        this.dbimage = this.createImage(dimension.width, dimension.height);
        this.setupDisplay();
    }
    
    void setupDisplay() {
        final View view = null;
        this.view2d = view;
        this.view3d = view;
        this.viewFreq = null;
        if (this.winSize == null) {
            return;
        }
        switch (this.displayChooser.getSelectedIndex()) {
            case 1: {
                if (!this.freqCheck.getState()) {
                    this.view3d = new View(this.winSize);
                    break;
                }
                this.view3d = new View(0, 0, this.winSize.width, this.winSize.height / 2);
                this.viewFreq = new View(0, this.winSize.height / 2, this.winSize.width, this.winSize.height / 2);
                break;
            }
            case 2: {
                if (!this.freqCheck.getState()) {
                    this.view2d = new View(this.winSize);
                    break;
                }
                this.view2d = new View(0, 0, this.winSize.width, this.winSize.height / 2);
                this.viewFreq = new View(0, this.winSize.height / 2, this.winSize.width, this.winSize.height / 2);
                break;
            }
            default: {
                if (!this.freqCheck.getState()) {
                    this.view3d = new View(0, 0, this.winSize.width, this.winSize.height / 2);
                    this.view2d = new View(0, this.winSize.height / 2, this.winSize.width, this.winSize.height / 2);
                    break;
                }
                this.view3d = new View(0, 0, this.winSize.width / 2, this.winSize.height / 2);
                this.view2d = new View(this.winSize.width / 2, 0, this.winSize.width / 2, this.winSize.height / 2);
                this.viewFreq = new View(0, this.winSize.height / 2, this.winSize.width, this.winSize.height / 2);
                break;
            }
        }
        if (this.viewFreq != null) {
            this.viewFreq.x = (this.winSize.width - this.viewFreq.height) / 2;
            final Rectangle viewFreq = this.viewFreq;
            viewFreq.width -= this.viewFreq.x * 2;
        }
        if (this.view3d != null) {
            this.setupRaster(this.view3d);
        }
        if (this.view2d != null) {
            this.cell2dWidth = this.view2d.width / (this.sampleCount + 1);
            this.cell2dHeight = this.view2d.height / (this.sampleCount + 1);
            final double n = this.view2dSwap ? (1.0 / this.aspectRatio) : this.aspectRatio;
            if (this.cell2dWidth > this.cell2dHeight * n) {
                this.cell2dWidth = (int)(this.cell2dHeight * n);
            }
            if (this.cell2dHeight > this.cell2dWidth / n) {
                this.cell2dHeight = (int)(this.cell2dWidth / n);
            }
            final int width = this.cell2dWidth * (this.sampleCount + 1);
            final int height = this.cell2dHeight * (this.sampleCount + 1);
            final View view2d = this.view2d;
            view2d.x += (this.view2d.width - width) / 2;
            final View view2d2 = this.view2d;
            view2d2.y += (this.view2d.height - height) / 2;
            this.view2d.width = width;
            this.view2d.height = height;
            this.brightnessBar.enable();
            this.setupRaster(this.view2d);
        }
        else {
            this.brightnessBar.disable();
        }
    }
    
    void setupRaster(final View view) {
        view.pixels = null;
        if (this.useBufferedImage) {
            try {
                final Class<?> forName = Class.forName("java.awt.image.BufferedImage");
                final Class<?> forName2 = Class.forName("java.awt.image.DataBufferInt");
                final Class<?> forName3 = Class.forName("java.awt.image.Raster");
                view.memimage = (Image)forName.getConstructor(Integer.TYPE, Integer.TYPE, Integer.TYPE).newInstance(new Integer(view.width), new Integer(view.height), new Integer(1));
                view.pixels = (int[])forName2.getMethod("getData", (Class[])null).invoke(forName3.getMethod("getDataBuffer", (Class<?>[])null).invoke(forName.getMethod("getRaster", (Class[])null).invoke(view.memimage, (Object[])null), (Object[])null), (Object[])null);
            }
            catch (Exception ex) {
                System.out.println("BufferedImage failed");
            }
        }
        if (view.pixels == null) {
            view.pixels = new int[view.width * view.height];
            for (int i = 0; i != view.width * view.height; ++i) {
                view.pixels[i] = -16777216;
            }
            (view.imageSource = new MemoryImageSource(view.width, view.height, view.pixels, 0, view.width)).setAnimated(true);
            view.imageSource.setFullBufferUpdates(true);
            view.memimage = this.cv.createImage(view.imageSource);
        }
    }
    
    void doSine() {
        for (int i = 0; i != this.sampleCount; ++i) {
            for (int j = 0; j != this.sampleCount; ++j) {
                this.magcoef[i][j] = 0.0;
            }
        }
        if (this.fixedCheck.getState()) {
            this.magcoef[1][1] = 1.0;
        }
        else if (this.omega[0][1] < this.omega[1][0]) {
            this.magcoef[0][1] = 1.0;
        }
        else {
            this.magcoef[1][0] = 1.0;
        }
        this.doPlay();
    }
    
    void doBlank() {
        for (int i = 0; i <= this.sampleCount; ++i) {
            for (int j = 0; j <= this.sampleCount; ++j) {
                this.func[i][j] = 0.0;
            }
        }
        this.transform(true);
    }
    
    void transform(final boolean b) {
        this.t = 0.0;
        final int[] array = new int[2];
        array[0] = (array[1] = this.maxTerms * 2);
        final int n = this.maxTerms * 4;
        final int n2 = this.maxTerms * 2;
        final double n3 = this.fixedCheck.getState() ? -1.0 : 1.0;
        for (int i = 0; i != this.maxTerms * this.maxTerms * 8; ++i) {
            this.data[i] = 0.0;
        }
        for (int j = 0; j <= this.sampleCount; ++j) {
            for (int k = 0; k <= this.sampleCount; ++k) {
                final double n4 = b ? 0.0 : this.funci[j][k];
                this.data[j * 2 + k * n] = this.func[j][k];
                this.data[j * 2 + k * n + 1] = n4;
                if (j > 0) {
                    this.data[(n2 - j) * 2 + k * n] = n3 * this.func[j][k];
                    this.data[(n2 - j) * 2 + k * n + 1] = n3 * n4;
                    if (k > 0) {
                        this.data[(n2 - j) * 2 + (n2 - k) * n] = this.func[j][k];
                        this.data[(n2 - j) * 2 + (n2 - k) * n + 1] = n4;
                    }
                }
                if (k > 0) {
                    this.data[j * 2 + (n2 - k) * n] = n3 * this.func[j][k];
                    this.data[j * 2 + (n2 - k) * n + 1] = n3 * n4;
                }
            }
        }
        this.ndfft(this.data, array, 2, 1);
        final double n5 = -4.0 / (n2 * n2);
        for (int l = 0; l != this.maxTerms; ++l) {
            for (int n6 = 0; n6 != this.maxTerms; ++n6) {
                double n7 = this.data[l * 2 + n6 * n] * n5;
                double n8 = this.data[l * 2 + n6 * n + 1] * n5;
                if (n7 < 1.0E-5 && n7 > -1.0E-5) {
                    n7 = 0.0;
                }
                if (n8 < 1.0E-5 && n8 > -1.0E-5) {
                    n8 = 0.0;
                }
                if (b) {
                    n8 = 0.0;
                }
                double n9 = 1.0;
                if (n7 < 0.0) {
                    n7 = -n7;
                    n8 = -n8;
                    n9 = -1.0;
                }
                this.magcoef[l][n6] = Math.sqrt(n7 * n7 + n8 * n8) * n9;
                final double atan2 = Math.atan2(n8, n7);
                this.phasecoefadj[l][n6] = atan2;
                this.phasecoef[l][n6] = atan2;
            }
        }
        this.needPlay = true;
    }
    
    int getPanelHeight() {
        return this.winSize.height / 3;
    }
    
    void centerString(final Graphics graphics, final String s, final int n) {
        graphics.drawString(s, (this.winSize.width - graphics.getFontMetrics().stringWidth(s)) / 2, n);
    }
    
    public void paint(final Graphics graphics) {
        this.cv.repaint();
    }
    
    public void updateMembrane(final Graphics graphics) {
        final Graphics graphics2 = this.dbimage.getGraphics();
        if (this.winSize == null || this.winSize.width == 0) {
            return;
        }
        boolean b = true;
        double n = 0.0;
        if (!this.stoppedCheck.getState()) {
            final double n2 = Math.exp(this.speedBar.getValue() / 20.0) * 0.002;
            final long currentTimeMillis = System.currentTimeMillis();
            if (this.lastTime == 0L) {
                this.lastTime = currentTimeMillis;
            }
            n = n2 * ((currentTimeMillis - this.lastTime) * 0.0058823529411764705);
            this.t += n;
            this.lastTime = currentTimeMillis;
            b = false;
        }
        else {
            this.lastTime = 0L;
        }
        final Color color = new Color(76, 76, 76);
        final Color color2 = new Color(127, 127, 127);
        graphics2.setColor(this.cv.getBackground());
        graphics2.fillRect(0, 0, this.winSize.width, this.winSize.height);
        graphics2.setColor(this.cv.getForeground());
        if (this.dragStop) {
            this.t = 0.0;
            this.lastTime = 0L;
        }
        if (!this.editingFunc) {
            for (int i = 0; i != this.maxTerms; ++i) {
                for (int j = 0; j != this.maxTerms; ++j) {
                    if (this.magcoef[i][j] < 1.0E-5 && this.magcoef[i][j] > -1.0E-5) {
                        final double[] array = this.magcoef[i];
                        final int n3 = j;
                        final double[] array2 = this.phasecoef[i];
                        final int n4 = j;
                        final double[] array3 = this.phasecoefadj[i];
                        final int n5 = j;
                        final double n6 = 0.0;
                        array3[n5] = n6;
                        array[n3] = (array2[n4] = n6);
                    }
                    else {
                        final double[] array4 = this.magcoef[i];
                        final int n7 = j;
                        array4[n7] *= Math.exp(this.dampcoef[i][j] * n);
                        b = false;
                        this.phasecoef[i][j] = (this.omega[i][j] * this.t + this.phasecoefadj[i][j]) % 6.283185307179586;
                        this.phasecoefcos[i][j] = Math.cos(this.phasecoef[i][j]);
                    }
                }
            }
            this.genFunc(false);
        }
        final double n8 = this.brightnessBar.getValue() / 10.0;
        if (this.dragStop) {
            b = true;
        }
        if (this.showMode) {
            b = false;
            this.modephasecos = Math.cos(this.omega[this.selectedCoefX][this.selectedCoefY] * this.t + this.phasecoefadj[this.selectedCoefX][this.selectedCoefY]);
            if (this.magcoef[this.selectedCoefX][this.selectedCoefY] < 0.0) {
                this.modephasecos = -this.modephasecos;
            }
        }
        if (this.view3d != null) {
            this.draw3dView(graphics2);
        }
        if (this.view2d != null) {
            final int n9 = 65536;
            final int n10 = this.showMode ? 65792 : 256;
            if (this.viewAngle < 0.7853981633974483 || this.viewAngle >= 5.497787143782138) {
                final boolean b2 = false;
                this.view2dReflectX = b2;
                this.view2dSwap = b2;
                this.view2dReflectY = true;
            }
            else if (this.viewAngle >= 0.7853981633974483 && this.viewAngle < 2.356194490192345) {
                this.view2dSwap = true;
                final boolean b3 = false;
                this.view2dReflectY = b3;
                this.view2dReflectX = b3;
            }
            else if (this.viewAngle >= 2.356194490192345 && this.viewAngle < 3.9269908169872414) {
                this.view2dSwap = false;
                this.view2dReflectX = true;
                this.view2dReflectY = false;
            }
            else {
                this.view2dSwap = true;
                this.view2dReflectX = true;
                this.view2dReflectY = true;
            }
            graphics2.setColor(Color.white);
            graphics2.drawRect(this.view2d.x - 1, this.view2d.y - 1, this.view2d.width + 2, this.view2d.height + 2);
            final int[] pixels = this.view2d.pixels;
            for (int k = 0; k <= this.maxTerms; ++k) {
                for (int l = 0; l <= this.maxTerms; ++l) {
                    int n11 = this.showMode ? ((int)(255.0 * n8 * this.getMode(this.selectedCoefX, this.selectedCoefY, l, k) * this.modephasecos)) : ((int)(255.0 * n8 * this.func[l][k]));
                    if (n11 < -255) {
                        n11 = -255;
                    }
                    if (n11 > 255) {
                        n11 = 255;
                    }
                    int n12;
                    if (n11 < 0) {
                        n12 = -16777216 + n9 * -n11;
                    }
                    else {
                        n12 = -16777216 + n10 * n11;
                    }
                    int n13;
                    int n14;
                    if (this.view2dSwap) {
                        n13 = k;
                        n14 = l;
                    }
                    else {
                        n13 = l;
                        n14 = k;
                    }
                    if (this.view2dReflectX) {
                        n13 = this.maxTerms - n13;
                    }
                    if (this.view2dReflectY) {
                        n14 = this.maxTerms - n14;
                    }
                    final int n15 = this.cell2dWidth * (n13 + 1);
                    final int n16 = this.cell2dHeight * n14 * this.view2d.width;
                    final int n17 = n16 + this.cell2dHeight * this.view2d.width;
                    for (int n18 = this.cell2dWidth * n13; n18 != n15; ++n18) {
                        for (int n19 = n16; n19 != n17; n19 += this.view2d.width) {
                            pixels[n18 + n19] = n12;
                        }
                    }
                }
            }
            if (this.view2d.imageSource != null) {
                this.view2d.imageSource.newPixels();
            }
            graphics2.drawImage(this.view2d.memimage, this.view2d.x, this.view2d.y, null);
        }
        if (this.viewFreq != null) {
            final int termWidth = this.getTermWidth();
            graphics2.setColor(Color.white);
            int state;
            int n20;
            for (n20 = (state = (this.fixedCheck.getState() ? 1 : 0)); state <= this.maxDispTerms; ++state) {
                final int n21 = state * termWidth;
                graphics2.drawLine(this.viewFreq.x + n20 * termWidth, n21 + this.viewFreq.y, this.viewFreq.x + termWidth * this.maxDispTerms, n21 + this.viewFreq.y);
                graphics2.drawLine(this.viewFreq.x + n21, this.viewFreq.y + n20 * termWidth, this.viewFreq.x + n21, this.viewFreq.y + termWidth * this.maxDispTerms);
            }
            final int n22 = 65536;
            final int n23 = 256;
            for (int n24 = n20; n24 != this.maxDispTerms; ++n24) {
                for (int n25 = n20; n25 != this.maxDispTerms; ++n25) {
                    final int n26 = this.viewFreq.x + n24 * termWidth;
                    final int n27 = this.viewFreq.y + n25 * termWidth;
                    int logcoef = this.logcoef(this.magcoef[n24][n25]);
                    if (logcoef < -255) {
                        logcoef = -255;
                    }
                    if (logcoef > 255) {
                        logcoef = 255;
                    }
                    if (logcoef < 0) {
                        graphics2.setColor(new Color(-16777216 + n22 * -logcoef));
                    }
                    else {
                        graphics2.setColor(new Color(-16777216 + n23 * logcoef));
                    }
                    graphics2.fillRect(n26 + 1, n27 + 1, termWidth - 1, termWidth - 1);
                    final int n28 = (int)(this.phasecoefadj[n24][n25] * termWidth * 0.15915494309189535);
                    if (n28 > 0) {
                        graphics2.setColor(Color.blue);
                        graphics2.drawLine(n26 + n28, n27 + 1, n26 + n28, n27 + termWidth);
                    }
                    if (this.selectedCoefX != -1 && this.omega[this.selectedCoefX][this.selectedCoefY] == this.omega[n24][n25]) {
                        graphics2.setColor(Color.yellow);
                        graphics2.drawRect(n26, n27, termWidth, termWidth);
                    }
                }
            }
        }
        graphics.drawImage(this.dbimage, 0, 0, this);
        if (!this.stoppedCheck.getState() && !b) {
            this.cv.repaint(this.pause);
        }
    }
    
    void draw3dView(final Graphics graphics) {
        final int n = this.sampleCount / 2;
        this.scaleworld();
        final int n2 = (this.display2Chooser.getSelectedIndex() == 0) ? (this.sampleCount - 1) : this.sampleCount;
        int n3;
        int n4;
        int n5;
        if (this.viewAngleCos < 0.0) {
            n3 = n2;
            n4 = -1;
            n5 = -1;
        }
        else {
            n3 = 0;
            n4 = n2 + 1;
            n5 = 1;
        }
        int n6;
        int n7;
        int n8;
        if (this.viewAngleSin < 0.0) {
            n6 = 0;
            n7 = n2 + 1;
            n8 = 1;
        }
        else {
            n6 = n2;
            n7 = -1;
            n8 = -1;
        }
        final boolean b = -this.viewAngleSin * n8 > this.viewAngleCos * n5;
        if (this.display2Chooser.getSelectedIndex() == 0) {
            this.shadowBufferBottom = new int[this.view3d.width];
            this.shadowBufferTop = new int[this.view3d.width];
            this.shadowBufferBottom2 = new int[this.view3d.width];
            this.shadowBufferTop2 = new int[this.view3d.width];
            for (int i = 0; i != this.view3d.width; ++i) {
                this.shadowBufferBottom[i] = (this.shadowBufferBottom2[i] = 0);
                this.shadowBufferTop[i] = (this.shadowBufferTop2[i] = this.view3d.height - 1);
            }
            final int[] pixels = this.view3d.pixels;
            for (int j = 0; j != this.view3d.width * this.view3d.height; ++j) {
                pixels[j] = -16777216;
            }
            final double n9 = 0.1;
            final double n10 = n9 * n9;
            for (int k = n6; k != n7; k += n8) {
                for (int l = n3; l != n4; l += n5) {
                    if (!b) {
                        k = n6;
                    }
                    while (k != n7) {
                        this.map3d(k - n, l - n, this.func[k][l], this.xpoints, this.ypoints, 0);
                        this.map3d(k + 1 - n, l - n, this.func[k + 1][l], this.xpoints, this.ypoints, 1);
                        this.map3d(k - n, l + 1 - n, this.func[k][l + 1], this.xpoints, this.ypoints, 2);
                        this.map3d(k + 1 - n, l + 1 - n, this.func[k + 1][l + 1], this.xpoints, this.ypoints, 3);
                        final double n11 = this.func[k + 1][l] - this.func[k][l];
                        final double n12 = this.func[k][l + 1] - this.func[k][l];
                        final int computeColor = this.computeColor(k, l, (n11 + n12 + n9) * 0.5780346820809249 / Math.sqrt(n11 * n11 + n12 * n12 + n10));
                        this.fillTriangle(this.xpoints[0], this.ypoints[0], this.xpoints[1], this.ypoints[1], this.xpoints[3], this.ypoints[3], computeColor);
                        this.fillTriangle(this.xpoints[0], this.ypoints[0], this.xpoints[2], this.ypoints[2], this.xpoints[3], this.ypoints[3], computeColor);
                        if (b) {
                            break;
                        }
                        k += n8;
                    }
                    if (!b) {
                        for (int n13 = 0; n13 != this.view3d.width; ++n13) {
                            this.shadowBufferTop[n13] = this.shadowBufferTop2[n13];
                            this.shadowBufferBottom[n13] = this.shadowBufferBottom2[n13];
                        }
                    }
                }
                if (!b) {
                    break;
                }
                for (int n14 = 0; n14 != this.view3d.width; ++n14) {
                    this.shadowBufferTop[n14] = this.shadowBufferTop2[n14];
                    this.shadowBufferBottom[n14] = this.shadowBufferBottom2[n14];
                }
            }
            if (this.view3d.imageSource != null) {
                this.view3d.imageSource.newPixels();
            }
            graphics.drawImage(this.view3d.memimage, this.view3d.x, this.view3d.y, null);
        }
        else {
            final boolean b2 = this.display2Chooser.getSelectedIndex() != 3;
            final boolean b3 = this.display2Chooser.getSelectedIndex() != 2;
            for (int n15 = n6; n15 != n7; n15 += n8) {
                for (int n16 = n3; n16 != n4; n16 += n5) {
                    if (!b) {
                        n15 = n6;
                    }
                    while (n15 != n7) {
                        graphics.setColor(new Color(this.computeColor(n15, n16, 0.0)));
                        this.map3d(n15 - n, n16 - n, this.func[n15][n16], this.xpoints, this.ypoints, 0);
                        if (n15 < this.sampleCount && b2) {
                            this.map3d(n15 + 1 - n, n16 - n, this.func[n15 + 1][n16], this.xpoints, this.ypoints, 1);
                            graphics.drawLine(this.xpoints[0], this.ypoints[0], this.xpoints[1], this.ypoints[1]);
                        }
                        if (n16 < this.sampleCount && b3) {
                            this.map3d(n15 - n, n16 + 1 - n, this.func[n15][n16 + 1], this.xpoints, this.ypoints, 2);
                            graphics.drawLine(this.xpoints[0], this.ypoints[0], this.xpoints[2], this.ypoints[2]);
                        }
                        if (b) {
                            break;
                        }
                        n15 += n8;
                    }
                }
                if (!b) {
                    break;
                }
            }
        }
    }
    
    int computeColor(final int n, final int n2, double n3) {
        double n4 = this.func[n][n2];
        if (!this.colorCheck.getState() && !this.showMode) {
            n4 = 0.0;
            if (this.display2Chooser.getSelectedIndex() != 0) {
                return -1;
            }
        }
        if (n3 < 0.0) {
            n3 = 0.0;
        }
        if (n3 > 1.0) {
            n3 = 1.0;
        }
        n3 = 0.5 + n3 * 0.5;
        double n5 = (n4 < 0.0) ? (-n4) : 0.0;
        double n6 = (n4 > 0.0) ? n4 : 0.0;
        if (this.showMode) {
            final double n7 = this.getMode(this.selectedCoefX, this.selectedCoefY, n, n2) * this.modephasecos;
            if (n7 > 0.0) {
                n6 = (n5 = n7);
            }
            else {
                n5 = -n7;
                n6 = 0.0;
            }
        }
        if (n5 > 1.0) {
            n5 = 1.0;
        }
        if (n6 > 1.0) {
            n6 = 1.0;
        }
        if (n6 < 0.0) {
            n6 = 0.0;
        }
        if (n5 < 0.0) {
            n5 = 0.0;
        }
        double n8 = (1.0 - (n5 + n6)) * n3;
        if (this.showMode) {
            n8 = (1.0 - n5) * n3;
        }
        final double n9 = 0.6;
        return 0xFF000000 | (int)((n3 * n5 + n9 * n8) * 255.0) << 16 | (int)((n3 * n6 + n9 * n8) * 255.0) << 8 | (int)(n9 * n8 * 255.0);
    }
    
    void fillTriangle(final int n, final int n2, final int n3, final int n4, final int n5, final int n6, final int n7) {
        if (n > n3) {
            if (n3 > n5) {
                final int interp = this.interp(n, n2, n5, n6, n3);
                this.fillTriangle1(n5, n6, n3, n4, interp, n7);
                this.fillTriangle1(n, n2, n3, n4, interp, n7);
            }
            else if (n > n5) {
                final int interp2 = this.interp(n, n2, n3, n4, n5);
                this.fillTriangle1(n3, n4, n5, n6, interp2, n7);
                this.fillTriangle1(n, n2, n5, n6, interp2, n7);
            }
            else {
                final int interp3 = this.interp(n5, n6, n3, n4, n);
                this.fillTriangle1(n3, n4, n, n2, interp3, n7);
                this.fillTriangle1(n5, n6, n, n2, interp3, n7);
            }
        }
        else if (n > n5) {
            final int interp4 = this.interp(n3, n4, n5, n6, n);
            this.fillTriangle1(n5, n6, n, n2, interp4, n7);
            this.fillTriangle1(n3, n4, n, n2, interp4, n7);
        }
        else if (n3 > n5) {
            final int interp5 = this.interp(n3, n4, n, n2, n5);
            this.fillTriangle1(n, n2, n5, n6, interp5, n7);
            this.fillTriangle1(n3, n4, n5, n6, interp5, n7);
        }
        else {
            final int interp6 = this.interp(n5, n6, n, n2, n3);
            this.fillTriangle1(n, n2, n3, n4, interp6, n7);
            this.fillTriangle1(n5, n6, n3, n4, interp6, n7);
        }
    }
    
    int interp(final int n, final int n2, final int n3, final int n4, final int n5) {
        if (n == n3) {
            return n2;
        }
        if ((n5 < n && n5 < n3) || (n5 > n && n5 > n3)) {
            System.out.print("interp out of bounds\n");
        }
        return n2 + (n5 - n) * (n4 - n2) / (n3 - n);
    }
    
    void fillTriangle1(final int n, final int n2, final int n3, int n4, int n5, final int n6) {
        final int n7 = (n > n3) ? -1 : 1;
        int i = n;
        if (i < 0) {
            i = 0;
            if (n3 < 0) {
                return;
            }
        }
        if (i >= this.view3d.width) {
            i = this.view3d.width - 1;
            if (n3 >= this.view3d.width) {
                return;
            }
        }
        if (n4 > n5) {
            final int n8 = n4;
            n4 = n5;
            n5 = n8;
        }
        while (i != n3 + n7) {
            int j = this.interp(n, n2, n3, n4, i);
            int interp = this.interp(n, n2, n3, n5, i);
            if (j < 0) {
                j = 0;
            }
            if (interp >= this.view3d.height) {
                interp = this.view3d.height - 1;
            }
            if (this.shadowBufferTop2[i] > j) {
                this.shadowBufferTop2[i] = j;
            }
            if (this.shadowBufferBottom2[i] < interp) {
                this.shadowBufferBottom2[i] = interp;
            }
            final int n9 = this.shadowBufferTop[i];
            final int n10 = this.shadowBufferBottom[i];
            if (j < n9 || interp > n10) {
                while (j <= interp) {
                    if (j < n9 || j > n10) {
                        this.view3d.pixels[i + j * this.view3d.width] = n6;
                    }
                    ++j;
                }
            }
            i += n7;
            if (i < 0 || i >= this.view3d.width) {
                return;
            }
        }
    }
    
    void genFunc(final boolean b) {
        final int[] array = new int[2];
        array[0] = (array[1] = this.maxTerms * 2);
        final int n = this.maxTerms * 4;
        final int n2 = this.maxTerms * 2;
        final double n3 = (b || this.fixedCheck.getState()) ? -1.0 : 1.0;
        for (int i = 0; i != this.maxTerms * this.maxTerms * 8; ++i) {
            this.data[i] = 0.0;
        }
        for (int j = 0; j != this.sampleCount; ++j) {
            for (int k = 0; k != this.sampleCount; ++k) {
                final double n4 = this.phasecoefcos[j][k];
                final double sin = Math.sin(this.phasecoef[j][k]);
                final double n5 = -0.25 * this.magcoef[j][k];
                final double n6 = 0.0;
                final double n7 = n5 * n4 - n6 * sin;
                final double n8 = n6 * n4 + n5 * sin;
                this.data[j * 2 + k * n] = n7;
                this.data[j * 2 + k * n + 1] = n8;
                if (j > 0) {
                    this.data[(n2 - j) * 2 + k * n] = n3 * n7;
                    this.data[(n2 - j) * 2 + k * n + 1] = n3 * n8;
                    if (k > 0) {
                        this.data[(n2 - j) * 2 + (n2 - k) * n] = n7;
                        this.data[(n2 - j) * 2 + (n2 - k) * n + 1] = n8;
                    }
                }
                if (k > 0) {
                    this.data[j * 2 + (n2 - k) * n] = n3 * n7;
                    this.data[j * 2 + (n2 - k) * n + 1] = n3 * n8;
                }
            }
        }
        this.ndfft(this.data, array, 2, -1);
        for (int l = 0; l <= this.sampleCount; ++l) {
            for (int n9 = 0; n9 <= this.sampleCount; ++n9) {
                this.func[l][n9] = this.data[l * 2 + n9 * n];
                this.funci[l][n9] = this.data[l * 2 + n9 * n + 1];
            }
        }
    }
    
    int logcoef(double n) {
        final double n2 = 0.003;
        final int n3 = (n < 0.0) ? -1 : 1;
        n *= n3;
        if (n < n2) {
            return 0;
        }
        if (this.logep2 == 0.0) {
            this.logep2 = -Math.log(2.0 * n2);
        }
        return (int)(255 * n3 * (Math.log(n + n2) + this.logep2) / this.logep2);
    }
    
    void map3d(final double n, final double n2, final double n3, final int[] array, final int[] array2, final int n4) {
        final double n5 = this.realxmx * n + this.realxmy * n2;
        final double n6 = this.realymz * n3 + this.realymadd;
        final double n7 = this.realzmx * n + this.realzmy * n2 + this.realzmadd;
        array[n4] = this.centerX3d + (int)(n5 / n7);
        array2[n4] = this.centerY3d - (int)(n6 / n7);
    }
    
    void scaleworld() {
        this.scalex = this.viewZoom * (this.view3d.width / 4) * this.viewDistance / 8.0;
        this.scaley = -this.scalex;
        final int n = (int)(this.scaley * this.viewHeight / this.viewDistance);
        this.centerX3d = this.view3d.width / 2;
        this.centerY3d = this.view3d.height / 2 - n;
        this.scaleMult = 16.0 / this.sampleCount;
        this.realxmx = this.viewAngleCos * this.aspectRatio * this.scaleMult * this.scalex;
        this.realxmy = this.viewAngleSin * this.scaleMult * this.scalex;
        this.realymz = -4.0 * this.scaley;
        this.realzmy = this.viewAngleCos * this.scaleMult;
        this.realzmx = -this.viewAngleSin * this.aspectRatio * this.scaleMult;
        this.realymadd = -this.viewHeight * this.scaley;
        this.realzmadd = this.viewDistance;
    }
    
    void adjustZoom() {
        this.viewZoom = 5.0;
        final double n = (this.aspectRatio > 1.0) ? this.aspectRatio : 1.0;
        while (true) {
            this.scaleworld();
            if (Math.abs(n * this.scaleMult * this.scalex * this.sampleCount / (this.realzmadd * 2.0)) < this.winSize.width * 45 / 100) {
                break;
            }
            this.viewZoom -= 0.2;
        }
    }
    
    int getTermWidth() {
        return this.viewFreq.height / this.maxDispTerms;
    }
    
    void edit(final MouseEvent mouseEvent) {
        if (this.selection == 0) {
            return;
        }
        final int x = mouseEvent.getX();
        final int y = mouseEvent.getY();
        switch (this.selection) {
            case 3: {
                this.editMag(x, y);
                break;
            }
            case 2: {
                this.editFunc2D(x, y);
                break;
            }
            case 1: {
                this.editFunc3D(x, y);
                break;
            }
        }
    }
    
    void editMag(final int n, final int n2) {
        if (this.selectedCoefX == -1) {
            return;
        }
        if (this.modeChooser.getSelectedIndex() == 5) {
            this.showMode = true;
            this.cv.repaint(this.pause);
            return;
        }
        double n3 = (this.dragStartY - n2) / 20.0 + this.magDragStart;
        if (n3 < -1.0) {
            n3 = -1.0;
        }
        if (n3 > 1.0) {
            n3 = 1.0;
        }
        double n4 = (n - this.dragStartX) / 10.0;
        if (n4 < 0.0) {
            n4 = 0.0;
        }
        if (n4 > 6.283185307179586) {
            n4 = 6.283185307179586;
        }
        if (this.magcoef[this.selectedCoefX][this.selectedCoefY] == n3 && this.phasecoefadj[this.selectedCoefX][this.selectedCoefY] == n4) {
            return;
        }
        this.magcoef[this.selectedCoefX][this.selectedCoefY] = n3;
        this.phasecoefadj[this.selectedCoefX][this.selectedCoefY] = n4;
        this.cv.repaint(this.pause);
        this.needPlay = true;
    }
    
    void editMagClick() {
        if (this.selectedCoefX == -1) {
            return;
        }
        if (this.modeChooser.getSelectedIndex() == 5) {
            return;
        }
        if (this.magDragStart < 0.5) {
            this.magcoef[this.selectedCoefX][this.selectedCoefY] = 1.0;
        }
        else {
            this.magcoef[this.selectedCoefX][this.selectedCoefY] = 0.0;
        }
        this.phasecoefadj[this.selectedCoefX][this.selectedCoefY] = 0.0;
        this.cv.repaint(this.pause);
        this.doPlay();
    }
    
    void editFunc2D(int i, int j) {
        final int selectedGridX = this.selectedGridX;
        final int selectedGridY = this.selectedGridY;
        this.findGridPoint2D(i, j);
        if (this.modeChooser.getSelectedIndex() == 0) {
            this.editFuncPluck(this.selectedGridX, this.selectedGridY, 1.0);
            return;
        }
        if (this.modeChooser.getSelectedIndex() == 1) {
            this.editFuncStrike(this.selectedGridX, this.selectedGridY, 1.0);
            return;
        }
        final int n = selectedGridX;
        final int n2 = selectedGridY;
        final int selectedGridX2 = this.selectedGridX;
        final int selectedGridY2 = this.selectedGridY;
        if (n == selectedGridX2 && n2 == selectedGridY2) {
            this.editFuncPoint(selectedGridX2, selectedGridY2, 1.0);
        }
        else if (this.abs(selectedGridY2 - n2) > this.abs(selectedGridX2 - n)) {
            int sign;
            for (sign = this.sign(selectedGridY2 - n2), j = n2; j != selectedGridY2 + sign; j += sign) {
                i = n + (selectedGridX2 - n) * (j - n2) / (selectedGridY2 - n2);
                this.editFuncPoint(i, j, 1.0);
            }
        }
        else {
            int sign2;
            for (sign2 = this.sign(selectedGridX2 - n), i = n; i != selectedGridX2 + sign2; i += sign2) {
                j = n2 + (selectedGridY2 - n2) * (i - n) / (selectedGridX2 - n);
                this.editFuncPoint(i, j, 1.0);
            }
        }
        this.transform(false);
    }
    
    int sign(final int n) {
        return (n < 0) ? -1 : ((n == 0) ? 0 : 1);
    }
    
    int abs(final int n) {
        return (n < 0) ? (-n) : n;
    }
    
    void editFunc3D(final int n, final int n2) {
        if (this.modeChooser.getSelectedIndex() == 3) {
            this.viewAngle = (this.dragStartX - n) / 40.0 + this.viewAngleDragStart;
            while (this.viewAngle < 0.0) {
                this.viewAngle += 6.283185307179586;
            }
            while (this.viewAngle >= 6.283185307179586) {
                this.viewAngle -= 6.283185307179586;
            }
            this.viewAngleCos = Math.cos(this.viewAngle);
            this.viewAngleSin = Math.sin(this.viewAngle);
            this.viewHeight = (this.dragStartY - n2) / 10.0 + this.viewHeightDragStart;
            this.setupDisplay();
            this.cv.repaint(this.pause);
            return;
        }
        if (this.modeChooser.getSelectedIndex() == 4) {
            this.viewZoom = (n - this.dragStartX) / 40.0 + this.viewZoomDragStart;
            if (this.viewZoom < 0.1) {
                this.viewZoom = 0.1;
            }
            this.cv.repaint(this.pause);
            return;
        }
        if (this.selectedGridX == -1) {
            return;
        }
        double n3 = this.selectedGridFunc + (this.dragStartY - n2) / 40.0;
        if (n3 < -1.0) {
            n3 = -1.0;
        }
        if (n3 > 1.0) {
            n3 = 1.0;
        }
        if (this.modeChooser.getSelectedIndex() == 0) {
            this.editFuncPluck(this.selectedGridX, this.selectedGridY, n3);
            return;
        }
        if (this.modeChooser.getSelectedIndex() == 1) {
            this.editFuncStrike(this.selectedGridX, this.selectedGridY, n3);
            return;
        }
        this.editFuncPoint(this.selectedGridX, this.selectedGridY, n3);
        this.transform(false);
    }
    
    void editFuncPoint(final int n, final int n2, final double n3) {
        if (!this.dragSet && !this.dragClear) {
            this.dragClear = (this.func[n][n2] > 0.1);
            this.dragSet = !this.dragClear;
        }
        this.func[n][n2] = (this.dragSet ? n3 : 0.0);
        final boolean b = true;
        this.editingFunc = b;
        this.dragStop = b;
        this.cv.repaint(this.pause);
    }
    
    void editFuncPluck(int n, int n2, final double n3) {
        double n4 = 0.0;
        for (int i = 0; i != this.maxTerms; ++i) {
            this.magcoef[i][0] = (this.magcoef[0][i] = 0.0);
        }
        if (n == 0) {
            n = 1;
        }
        if (n2 == 0) {
            n2 = 1;
        }
        if (n == this.maxTerms) {
            n = this.maxTerms - 1;
        }
        if (n2 == this.maxTerms) {
            n2 = this.maxTerms - 1;
        }
        for (int j = 1; j != this.maxTerms; ++j) {
            for (int k = 1; k != this.maxTerms; ++k) {
                this.magcoef[j][k] = Math.sin(this.step * j * n) * Math.sin(this.step * k * n2) / (j * j * this.maxTerms * this.maxTerms + k * k * this.maxTerms * this.maxTerms * this.aspectRatio * this.aspectRatio);
                this.phasecoefadj[j][k] = 0.0;
                this.phasecoefcos[j][k] = 1.0;
                this.phasecoef[j][k] = 0.0;
                n4 += this.magcoef[j][k] * Math.sin(this.step * j * n) * Math.sin(this.step * k * n2);
            }
        }
        final double n5 = n3 / n4;
        for (int l = 1; l != this.maxTerms; ++l) {
            for (int n6 = 1; n6 != this.maxTerms; ++n6) {
                final double[] array = this.magcoef[l];
                final int n7 = n6;
                array[n7] *= n5;
            }
        }
        this.dragStop = true;
        if (!this.fixedCheck.getState()) {
            this.genFunc(true);
            this.transform(true);
        }
        this.needPlay = true;
        this.cv.repaint(this.pause);
    }
    
    void editFuncStrike(int n, int n2, final double n3) {
        this.dragStop = true;
        if (n == 0) {
            n = 1;
        }
        if (n2 == 0) {
            n2 = 1;
        }
        if (n == this.maxTerms) {
            n = this.maxTerms - 1;
        }
        if (n2 == this.maxTerms) {
            n2 = this.maxTerms - 1;
        }
        final double n4 = 3.0;
        for (int i = 0; i <= this.maxTerms; ++i) {
            for (int j = 0; j <= this.maxTerms; ++j) {
                this.funci[i][j] = 0.0;
                final double n5 = (i - n) * this.aspectRatio;
                final int n6 = j - n2;
                final double sqrt = Math.sqrt(n5 * n5 + n6 * n6);
                double n7 = 0.0;
                if (sqrt < n4) {
                    n7 = n3 * (n4 - sqrt);
                }
                this.func[i][j] = n7;
            }
        }
        this.transform(true);
        this.cv.repaint(this.pause);
    }
    
    public void componentHidden(final ComponentEvent componentEvent) {
    }
    
    public void componentMoved(final ComponentEvent componentEvent) {
    }
    
    public void componentShown(final ComponentEvent componentEvent) {
        this.cv.repaint(this.pause);
    }
    
    public void componentResized(final ComponentEvent componentEvent) {
        this.handleResize();
        this.cv.repaint(this.pause);
    }
    
    public void actionPerformed(final ActionEvent actionEvent) {
        if (actionEvent.getSource() == this.sineButton) {
            this.doSine();
            this.cv.repaint();
        }
        if (actionEvent.getSource() == this.blankButton) {
            this.doBlank();
            this.cv.repaint();
        }
    }
    
    public void adjustmentValueChanged(final AdjustmentEvent adjustmentEvent) {
        System.out.print(((Scrollbar)adjustmentEvent.getSource()).getValue() + "\n");
        if (adjustmentEvent.getSource() == this.dampingBar || adjustmentEvent.getSource() == this.speedBar) {
            this.setDamping();
        }
        if (adjustmentEvent.getSource() == this.resBar) {
            this.setResolution();
        }
        if (adjustmentEvent.getSource() == this.aspectBar) {
            this.setResolution();
            this.adjustZoom();
        }
        this.maxDispTerms = this.phasorBar.getValue();
        if (this.maxDispTerms > this.maxTerms) {
            this.maxDispTerms = this.maxTerms;
        }
        this.cv.repaint(this.pause);
    }
    
    public boolean handleEvent(final Event event) {
        if (event.id == 201) {
            this.applet.destroyFrame();
            return true;
        }
        return super.handleEvent(event);
    }
    
    void setResolution() {
        int value = this.resBar.getValue();
        this.sampleCount = 1;
        while (value-- > 0) {
            this.sampleCount *= 2;
        }
        if (this.sampleCount > 128) {
            this.sampleCount = 128;
        }
        if (this.sampleCount < 8) {
            this.sampleCount = 8;
        }
        final int maxTerms = this.maxTerms;
        this.maxTerms = this.sampleCount;
        System.out.println("samplecount = " + this.sampleCount);
        this.step = 3.141592653589793 / this.sampleCount;
        this.aspectRatio = this.aspectBar.getValue() / 10.0;
        this.viewDistance = 66.0;
        final double[][] magcoef = this.magcoef;
        this.magcoef = new double[this.maxTerms][this.maxTerms];
        this.phasecoef = new double[this.maxTerms][this.maxTerms];
        this.phasecoefcos = new double[this.maxTerms][this.maxTerms];
        this.phasecoefadj = new double[this.maxTerms][this.maxTerms];
        this.func = new double[this.maxTerms + 1][this.maxTerms + 1];
        this.funci = new double[this.maxTerms + 1][this.maxTerms + 1];
        this.data = new double[this.maxTerms * this.maxTerms * 2 * 4];
        this.omega = new double[this.maxTerms][this.maxTerms];
        for (int i = 0; i != this.maxTerms; ++i) {
            for (int j = 0; j != this.maxTerms; ++j) {
                this.omega[i][j] = Math.sqrt(i * i / (this.aspectRatio * this.aspectRatio) + j * j);
            }
        }
        final double n = 1.0 / this.omega[1][1];
        for (int k = 0; k != this.maxTerms; ++k) {
            for (int l = 0; l != this.maxTerms; ++l) {
                final double[] array = this.omega[k];
                final int n2 = l;
                array[n2] *= n;
            }
        }
        if (magcoef != null) {
            for (int n3 = 0; n3 != maxTerms && n3 != this.maxTerms; ++n3) {
                for (int n4 = 0; n4 != maxTerms && n4 != this.maxTerms; ++n4) {
                    this.magcoef[n3][n4] = magcoef[n3][n4];
                }
            }
        }
        this.setDamping();
        this.setupDisplay();
    }
    
    double getMode(final int n, final int n2, final int n3, final int n4) {
        if (this.fixedCheck.getState()) {
            return Math.sin(this.step * n3 * n) * Math.sin(this.step * n4 * n2);
        }
        return Math.cos(this.step * n3 * n) * Math.cos(this.step * n4 * n2);
    }
    
    void setDamping() {
        this.dampcoef = new double[this.maxTerms][this.maxTerms];
        for (int i = 0; i != this.maxTerms; ++i) {
            for (int j = 0; j != this.maxTerms; ++j) {
                if (i != 0 || j != 0) {
                    final double n = Math.exp(this.dampingBar.getValue() / 40.0) - 1.0;
                    this.dampcoef[i][j] = -(this.omega[i][j] * Math.sqrt(Math.sqrt(1.0 + n * n / (this.omega[i][j] * this.omega[i][j])) - 1.0)) * 0.003;
                }
            }
        }
    }
    
    void findGridPoint2D(final int n, final int n2) {
        this.selectedGridX = (n - this.view2d.x) / this.cell2dWidth;
        this.selectedGridY = (n2 - this.view2d.y) / this.cell2dHeight;
        final int state = this.fixedCheck.getState() ? 1 : 0;
        if (this.selectedGridX < state) {
            this.selectedGridX = state;
        }
        if (this.selectedGridY < state) {
            this.selectedGridY = state;
        }
        if (this.selectedGridX > this.sampleCount - state) {
            this.selectedGridX = this.sampleCount - state;
        }
        if (this.selectedGridY > this.sampleCount - state) {
            this.selectedGridY = this.sampleCount - state;
        }
        if (this.view2dSwap) {
            final int selectedGridX = this.selectedGridX;
            this.selectedGridX = this.selectedGridY;
            this.selectedGridY = selectedGridX;
        }
        if (this.view2dReflectX) {
            this.selectedGridX = this.maxTerms - this.selectedGridX;
        }
        if (this.view2dReflectY) {
            this.selectedGridY = this.maxTerms - this.selectedGridY;
        }
        this.selectedGridFunc = this.func[this.selectedGridX][this.selectedGridY];
    }
    
    void findGridPoint3D(final int n, final int n2) {
        final int n3 = this.sampleCount / 2;
        int n4 = 3600;
        final int n5 = -1;
        this.selectedGridY = n5;
        this.selectedGridX = n5;
        for (int i = 1; i < this.sampleCount; ++i) {
            for (int j = 1; j < this.sampleCount; ++j) {
                this.map3d(j - n3, i - n3, this.func[j][i], this.xpoints, this.ypoints, 0);
                final int n6 = this.xpoints[0] + this.view3d.x - n;
                final int n7 = this.ypoints[0] + this.view3d.y - n2;
                final int n8 = n6 * n6 + n7 * n7;
                if (n8 < n4) {
                    n4 = n8;
                    this.selectedGridX = j;
                    this.selectedGridY = i;
                }
            }
        }
        if (this.selectedGridX != -1) {
            this.selectedGridFunc = this.func[this.selectedGridX][this.selectedGridY];
        }
    }
    
    public void mouseDragged(final MouseEvent mouseEvent) {
        this.dragging = true;
        this.edit(mouseEvent);
    }
    
    public void mouseMoved(final MouseEvent mouseEvent) {
        if (this.dragging) {
            return;
        }
        final int x = mouseEvent.getX();
        final int y = mouseEvent.getY();
        this.dragX = x;
        this.dragY = y;
        this.getPanelHeight();
        final int selectedCoefX = this.selectedCoefX;
        final int selectedCoefY = this.selectedCoefY;
        this.selectedCoefX = -1;
        this.selectedCoefY = -1;
        this.selection = 0;
        if (this.view2d != null && this.view2d.inside(x, y)) {
            this.selection = 2;
        }
        else if (this.view3d != null && this.view3d.inside(x, y)) {
            this.selection = 1;
            this.findGridPoint3D(x, y);
        }
        else if (this.viewFreq != null && this.viewFreq.inside(x, y)) {
            final int termWidth = this.getTermWidth();
            this.selectedCoefX = (x - this.viewFreq.x) / termWidth;
            this.selectedCoefY = (y - this.viewFreq.y) / termWidth;
            if (this.selectedCoefX >= this.maxDispTerms) {
                final int n = -1;
                this.selectedCoefY = n;
                this.selectedCoefX = n;
            }
            if (this.selectedCoefY >= this.maxDispTerms) {
                final int n2 = -1;
                this.selectedCoefY = n2;
                this.selectedCoefX = n2;
            }
            if (this.selectedCoefX == 0 && this.fixedCheck.getState()) {
                this.selectedCoefX = 1;
            }
            if (this.selectedCoefY == 0 && this.fixedCheck.getState()) {
                this.selectedCoefY = 1;
            }
            if (this.selectedCoefX != -1 && this.selectedCoefY != -1) {
                this.selection = 3;
            }
        }
        if (this.selectedCoefX != selectedCoefX || this.selectedCoefY != selectedCoefY) {
            this.cv.repaint(this.pause);
        }
    }
    
    public void mouseClicked(final MouseEvent mouseEvent) {
        if (this.selection == 3) {
            this.editMagClick();
        }
        if (mouseEvent.getClickCount() == 2 && this.selectedCoefX != -1) {
            for (int i = 0; i != this.maxTerms; ++i) {
                for (int j = 0; j != this.maxTerms; ++j) {
                    if (this.selectedCoefX != i || this.selectedCoefY != j) {
                        this.magcoef[i][j] = 0.0;
                    }
                }
            }
            this.magcoef[this.selectedCoefX][this.selectedCoefY] = 1.0;
            this.cv.repaint(this.pause);
        }
    }
    
    public void mouseEntered(final MouseEvent mouseEvent) {
    }
    
    public void mouseExited(final MouseEvent mouseEvent) {
        if (!this.dragging && this.selectedCoefX != -1) {
            final int n = -1;
            this.selectedCoefY = n;
            this.selectedCoefX = n;
            this.cv.repaint(this.pause);
        }
    }
    
    public void mousePressed(final MouseEvent mouseEvent) {
        if ((mouseEvent.getModifiers() & 0x10) == 0x0) {
            return;
        }
        this.mouseMoved(mouseEvent);
        if (this.selection == 2) {
            this.findGridPoint2D(mouseEvent.getX(), mouseEvent.getY());
        }
        this.dragStartX = mouseEvent.getX();
        this.dragStartY = mouseEvent.getY();
        if (this.selectedCoefX != -1) {
            this.magDragStart = this.magcoef[this.selectedCoefX][this.selectedCoefY];
        }
        this.viewAngleDragStart = this.viewAngle;
        this.viewHeightDragStart = this.viewHeight;
        this.viewZoomDragStart = this.viewZoom;
        this.needPlay = false;
        this.dragging = true;
        this.edit(mouseEvent);
    }
    
    public void mouseReleased(final MouseEvent mouseEvent) {
        if ((mouseEvent.getModifiers() & 0x10) == 0x0) {
            return;
        }
        if (this.needPlay) {
            this.doPlay();
        }
        final boolean b = false;
        this.showMode = b;
        this.dragStop = b;
        this.editingFunc = b;
        this.dragging = b;
        final boolean b2 = false;
        this.dragClear = b2;
        this.dragSet = b2;
        this.mouseMoved(mouseEvent);
        this.cv.repaint(this.pause);
    }
    
    public void itemStateChanged(final ItemEvent itemEvent) {
        if (itemEvent.getItemSelectable() == this.stoppedCheck) {
            this.cv.repaint(this.pause);
            return;
        }
        if (itemEvent.getItemSelectable() == this.displayChooser || itemEvent.getItemSelectable() == this.freqCheck) {
            this.setupDisplay();
            this.cv.repaint(this.pause);
        }
        if (itemEvent.getItemSelectable() == this.display2Chooser || itemEvent.getItemSelectable() == this.colorCheck) {
            this.cv.repaint(this.pause);
        }
        if (itemEvent.getItemSelectable() == this.fixedCheck) {
            if (this.fixedCheck.getState()) {
                for (int i = 0; i != this.sampleCount; ++i) {
                    this.magcoef[i][0] = (this.magcoef[0][i] = 0.0);
                }
            }
            this.transform(true);
            this.cv.repaint(this.pause);
        }
    }
    
    void ndfft(final double[] array, final int[] array2, final int n, final int n2) {
        int n3 = 1;
        int n4 = 1;
        final double n5 = n2 * 2 * 3.141592653589793;
        for (int i = 0; i < n; ++i) {
            n3 *= array2[i];
        }
        for (final int n6 : array2) {
            final int n7 = n3 / (n6 * n4);
            final int n8 = 2 * n4;
            final int n9 = n8 * n6;
            final int n10 = n9 * n7;
            int n11 = 0;
            for (int k = 0; k < n9; k += n8) {
                if (k < n11) {
                    for (int l = k; l < k + n8; l += 2) {
                        for (int n12 = l; n12 < n10; n12 += n9) {
                            final int n13 = n11 + n12 - k;
                            final double n14 = array[n12];
                            final double n15 = array[n12 + 1];
                            array[n12] = array[n13];
                            array[n12 + 1] = array[n13 + 1];
                            array[n13] = n14;
                            array[n13 + 1] = n15;
                        }
                    }
                }
                int n16;
                for (n16 = n9 / 2; n16 > n8 && n11 > n16 - 1; n11 -= n16, n16 /= 2) {}
                n11 += n16;
            }
            int n18;
            for (int n17 = n8; n17 < n9; n17 = n18) {
                n18 = 2 * n17;
                final double n19 = n5 / (n18 / n8);
                double n20 = 1.0;
                double n21 = 0.0;
                final double sin = Math.sin(0.5 * n19);
                final double n22 = sin * (sin * -2.0);
                final double sin2 = Math.sin(n19);
                for (int n23 = 0; n23 < n17; n23 += n8) {
                    for (int n24 = n23; n24 < n23 + n8; n24 += 2) {
                        for (int n25 = n24; n25 < n10; n25 += n18) {
                            final int n26 = n25 + 1;
                            final int n27 = n25 + n17;
                            final int n28 = n27 + 1;
                            final double n29 = n20 * array[n27] - n21 * array[n28];
                            final double n30 = n20 * array[n28] + n21 * array[n27];
                            array[n27] = array[n25] - n29;
                            array[n28] = array[n26] - n30;
                            final int n31 = n25;
                            array[n31] += n29;
                            final int n32 = n26;
                            array[n32] += n30;
                        }
                    }
                    final double n33 = n20;
                    n20 += n20 * n22 - n21 * sin2;
                    n21 += n21 * n22 + n33 * sin2;
                }
            }
            n4 *= n6;
        }
    }
    
    void doPlay() {
    }
    
    class View extends Rectangle
    {
        int[] pixels;
        MemoryImageSource imageSource;
        Image memimage;
        
        View(final Dimension dimension) {
            super(dimension);
        }
        
        View(final int n, final int n2, final int n3, final int n4) {
            super(n, n2, n3, n4);
        }
    }
}
