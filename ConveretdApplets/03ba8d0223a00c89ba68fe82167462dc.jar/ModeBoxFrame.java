import java.awt.Event;
import java.awt.event.ItemEvent;
import java.awt.event.MouseEvent;
import java.awt.event.AdjustmentEvent;
import java.awt.event.ActionEvent;
import java.awt.event.ComponentEvent;
import java.awt.FontMetrics;
import java.awt.image.ImageObserver;
import java.awt.image.ImageProducer;
import java.awt.Graphics;
import java.awt.Color;
import java.awt.Label;
import java.awt.Component;
import java.awt.LayoutManager;
import java.awt.image.MemoryImageSource;
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

class ModeBoxFrame extends Frame implements ComponentListener, ActionListener, AdjustmentListener, MouseMotionListener, MouseListener, ItemListener
{
    Thread engine;
    Dimension winSize;
    Image dbimage;
    Random random;
    int gridSizeX;
    int gridSizeY;
    Button clearButton;
    Checkbox memoryImageSourceCheck;
    Checkbox stoppedCheck;
    Checkbox spectrumCheck;
    Choice modeChooser;
    Scrollbar speedBar;
    Scrollbar resolutionBar;
    Scrollbar brightnessBar;
    Scrollbar widthBar;
    Scrollbar heightBar;
    double dragZoomStart;
    double zoom;
    double[] rotmatrix;
    double selectedMinOmega;
    double selectedMaxOmega;
    Rectangle view3d;
    Rectangle viewSpectrum;
    Rectangle[] viewFreq;
    double colorMult;
    static final double pi = 3.141592653589793;
    static final double pi2 = 6.283185307179586;
    int[] xpoints;
    int[] ypoints;
    int[] spectrum;
    static final int spectrumSpacing = 50;
    double[][][] func;
    double[][][] data;
    double boxwidth;
    double boxheight;
    boolean dragging;
    MemoryImageSource imageSource;
    int[] pixels;
    int maxTerms;
    static int maxModes;
    static int maxDispCoefs;
    static int viewDistance;
    Mode[] modes;
    int modeCount;
    int pause;
    ModeBox applet;
    int selection;
    static final int SEL_NONE = 0;
    static final int SEL_3D = 1;
    static final int SEL_MAG = 2;
    static final int SEL_SPECTRUM = 3;
    static final int MODE_ANGLE = 0;
    static final int MODE_ZOOM = 1;
    int selectedCoefX;
    int selectedCoefY;
    int selectedCoefZ;
    static final int sampleCount = 15;
    int[] sampleMult;
    double magDragStart;
    int dragX;
    int dragY;
    int oldDragX;
    int oldDragY;
    int dragStartX;
    int dragStartY;
    double t;
    public static final double epsilon = 1.0E-5;
    public static final double epsilon2 = 0.003;
    ModeBoxCanvas cv;
    double logep2;
    
    public String getAppletInfo() {
        return "ModeBox by Paul Falstad";
    }
    
    int getrand(final int n) {
        int nextInt = this.random.nextInt();
        if (nextInt < 0) {
            nextInt = -nextInt;
        }
        return nextInt % n;
    }
    
    ModeBoxFrame(final ModeBox applet) {
        super("Box Modes Applet");
        this.engine = null;
        this.gridSizeX = 200;
        this.gridSizeY = 200;
        this.zoom = 6.5;
        this.boxwidth = 2.0;
        this.boxheight = 2.0;
        this.dragging = false;
        this.maxTerms = 16;
        this.modeCount = 0;
        this.selection = -1;
        this.selectedCoefX = -1;
        this.t = 0.0;
        this.logep2 = 0.0;
        this.applet = applet;
    }
    
    public void init() {
        final String property = System.getProperty("os.name");
        final String property2 = System.getProperty("java.version");
        boolean b = false;
        int n = 32;
        if (property.indexOf("Windows") == 0) {
            n = 48;
            if (property2.indexOf("1.1") == 0) {
                b = true;
            }
        }
        this.setLayout(new ModeBoxLayout());
        (this.cv = new ModeBoxCanvas(this)).addComponentListener(this);
        this.cv.addMouseMotionListener(this);
        this.cv.addMouseListener(this);
        this.add(this.cv);
        this.add(this.clearButton = new Button("Clear"));
        this.clearButton.addActionListener(this);
        (this.stoppedCheck = new Checkbox("Stopped")).addItemListener(this);
        this.add(this.stoppedCheck);
        (this.spectrumCheck = new Checkbox("Show Spectrum")).addItemListener(this);
        this.add(this.spectrumCheck);
        (this.memoryImageSourceCheck = new Checkbox("Alternate Rendering", b)).addItemListener(this);
        this.add(this.memoryImageSourceCheck);
        (this.modeChooser = new Choice()).add("Mouse = Adjust Angle");
        this.modeChooser.add("Mouse = Adjust Zoom");
        this.modeChooser.addItemListener(this);
        this.add(this.modeChooser);
        this.add(new Label("Simulation Speed", 1));
        this.add(this.speedBar = new Scrollbar(0, 40, 1, 1, 200));
        this.speedBar.addAdjustmentListener(this);
        this.add(new Label("Brightness", 1));
        this.add(this.brightnessBar = new Scrollbar(0, 28, 1, 1, 200));
        this.brightnessBar.addAdjustmentListener(this);
        this.add(new Label("Image Resolution", 1));
        this.add(this.resolutionBar = new Scrollbar(0, n, 2, 20, 200));
        this.resolutionBar.addAdjustmentListener(this);
        this.add(new Label("Width", 1));
        this.add(this.widthBar = new Scrollbar(0, 10, 1, 5, 31));
        this.widthBar.addAdjustmentListener(this);
        this.add(new Label("Height", 1));
        this.add(this.heightBar = new Scrollbar(0, 10, 1, 5, 31));
        this.heightBar.addAdjustmentListener(this);
        this.add(new Label("http://www.falstad.com", 1));
        try {
            final String parameter = this.applet.getParameter("PAUSE");
            if (parameter != null) {
                this.pause = Integer.parseInt(parameter);
            }
        }
        catch (Exception ex) {}
        this.modes = new Mode[ModeBoxFrame.maxModes];
        this.addMode(1, 0, 0).magcoef = 1.0;
        this.rotmatrix = new double[9];
        final double[] rotmatrix = this.rotmatrix;
        final int n2 = 0;
        final double[] rotmatrix2 = this.rotmatrix;
        final int n3 = 4;
        final double[] rotmatrix3 = this.rotmatrix;
        final int n4 = 8;
        final double n5 = 1.0;
        rotmatrix3[n4] = n5;
        rotmatrix[n2] = (rotmatrix2[n3] = n5);
        this.xpoints = new int[2];
        this.ypoints = new int[2];
        this.sampleMult = new int[15];
        for (int i = 1; i < 15; i += 2) {
            this.sampleMult[i] = 4;
            this.sampleMult[i + 1] = 2;
        }
        this.sampleMult[0] = (this.sampleMult[14] = 1);
        this.random = new Random();
        this.reinit();
        this.cv.setBackground(Color.black);
        this.cv.setForeground(Color.white);
        this.resize(500, 500);
        this.handleResize();
        this.show();
    }
    
    void handleResize() {
        this.reinit();
    }
    
    void reinit() {
        this.setMaxTerms();
        final Dimension size = this.cv.getSize();
        this.winSize = size;
        final Dimension dimension = size;
        if (this.winSize.width == 0) {
            return;
        }
        this.calcSpectrum();
        this.dbimage = this.createImage(dimension.width, dimension.height);
        this.setupDisplay();
        this.pixels = new int[this.view3d.width * this.view3d.height];
        for (int i = 0; i != this.view3d.width * this.view3d.height; ++i) {
            this.pixels[i] = -16777216;
        }
        this.imageSource = new MemoryImageSource(this.view3d.width, this.view3d.height, this.pixels, 0, this.view3d.width);
    }
    
    int getTermWidth() {
        return 8;
    }
    
    void rotate(final double n, final double n2) {
        final double cos = Math.cos(n);
        final double sin = Math.sin(n);
        final double cos2 = Math.cos(n2);
        final double sin2 = Math.sin(n2);
        final double[] array = { cos, -sin * sin2, cos2 * sin, 0.0, cos2, sin2, -sin, -cos * sin2, cos * cos2 };
        final double[] rotmatrix = this.rotmatrix;
        this.rotmatrix = new double[9];
        for (int i = 0; i != 3; ++i) {
            for (int j = 0; j != 3; ++j) {
                double n3 = 0.0;
                for (int k = 0; k != 3; ++k) {
                    n3 += rotmatrix[k + i * 3] * array[j + k * 3];
                }
                this.rotmatrix[j + i * 3] = n3;
            }
        }
    }
    
    double max(final double n, final double n2) {
        return (n > n2) ? n : n2;
    }
    
    double min(final double n, final double n2) {
        return (n < n2) ? n : n2;
    }
    
    void setMaxTerms() {
        final int n = this.resolutionBar.getValue() & 0xFFFFFFFE;
        this.gridSizeY = n;
        this.gridSizeX = n;
        this.maxTerms = this.gridSizeX;
        if (this.maxTerms > 100) {
            this.maxTerms = 100;
        }
        this.data = new double[this.maxTerms][this.maxTerms][this.maxTerms];
        this.func = new double[this.gridSizeX][this.gridSizeY][2];
    }
    
    void setupDisplay() {
        final int n = 2;
        final int n2 = 4;
        final int n3 = this.getTermWidth() * (ModeBoxFrame.maxDispCoefs + 1) * n;
        final int n4 = this.spectrumCheck.getState() ? (this.getTermWidth() * 6) : 0;
        this.view3d = new Rectangle(0, 0, this.winSize.width, this.winSize.height - n3 - n4);
        if (this.spectrumCheck.getState()) {
            this.viewSpectrum = new Rectangle(0, this.view3d.height, this.winSize.width, n4);
        }
        else {
            this.viewSpectrum = null;
        }
        this.viewFreq = new Rectangle[ModeBoxFrame.maxDispCoefs];
        final int n6;
        final int n5 = n6 = this.getTermWidth() * ModeBoxFrame.maxDispCoefs;
        final int termWidth = this.getTermWidth();
        final int n7 = (this.winSize.width - (n5 * 4 + termWidth * 3)) / 2;
        for (int i = 0; i != ModeBoxFrame.maxDispCoefs; ++i) {
            this.viewFreq[i] = new Rectangle(n7 + i % n2 * (n5 + termWidth), this.view3d.height + n4 + i / n2 * (n6 + termWidth), n5, n6);
        }
    }
    
    void computeFunction() {
        this.genData(false);
        final double n = 3.14159265 / this.maxTerms;
        Math.cos(this.t);
        final double n2 = 1.0 / this.zoom;
        final double[] rotmatrix = this.rotmatrix;
        final double n3 = this.boxwidth / 2.0;
        final double n4 = this.boxheight / 2.0;
        final double n5 = this.view3d.width / this.view3d.height;
        for (int i = 0; i != this.gridSizeX; ++i) {
            for (int j = 0; j != this.gridSizeY; ++j) {
                final double n6 = 0.0;
                final double n7 = ModeBoxFrame.viewDistance;
                double n8 = (2 * i / this.gridSizeX - 1.0) * n2;
                double n9 = (2 * j / this.gridSizeY - 1.0) * n2;
                if (n5 < 1.0) {
                    n9 /= n5;
                }
                else {
                    n8 *= n5;
                }
                final double n10 = -1.0;
                final double n11 = rotmatrix[0] * n6 + rotmatrix[2] * n7;
                final double n12 = rotmatrix[5] * n7;
                final double n13 = rotmatrix[6] * n6 + rotmatrix[8] * n7;
                final double n14 = rotmatrix[0] * n8 + rotmatrix[1] * n9 + rotmatrix[2] * n10;
                final double n15 = rotmatrix[3] * n8 + rotmatrix[4] * n9 + rotmatrix[5] * n10;
                final double n16 = rotmatrix[6] * n8 + rotmatrix[7] * n9 + rotmatrix[8] * n10;
                final double sqrt = Math.sqrt(n14 * n14 + n15 * n15 + n16 * n16);
                double n17 = 0.0;
                double n18 = 0.0;
                final double n19 = (-n3 - n11) / n14;
                final double n20 = (n3 - n11) / n14;
                final double n21 = (-n4 - n12) / n15;
                final double n22 = (n4 - n12) / n15;
                final double n23 = (-1.0 - n13) / n16;
                final double n24 = (1.0 - n13) / n16;
                final double n25 = this.max(this.min(n19, n20), this.max(this.min(n21, n22), this.min(n23, n24))) + 0.001;
                final double n26 = this.min(this.max(n19, n20), this.min(this.max(n21, n22), this.max(n23, n24))) - 0.001;
                if (n26 < n25) {
                    this.func[i][j][0] = (this.func[i][j][1] = 0.0);
                }
                else {
                    final double n27 = (n26 - n25) / 14.0;
                    final double n28 = (n26 - n25) * sqrt;
                    final double n29 = this.maxTerms / this.boxwidth;
                    final double n30 = this.maxTerms / this.boxheight;
                    final double n31 = this.maxTerms / 2.0;
                    int k;
                    for (k = 0; k < 15; ++k) {
                        final double n32 = n25 + k * n27;
                        final double n33 = this.data[(int)((n11 + n14 * n32 + n3) * n29)][(int)((n12 + n15 * n32 + n4) * n30)][(int)((n13 + n16 * n32 + 1.0) * n31)];
                        if (n33 < 0.0) {
                            n17 += this.sampleMult[k] * Math.abs(n33);
                        }
                        else {
                            n18 += this.sampleMult[k] * n33;
                        }
                    }
                    final double n34 = n17 * (n28 / k);
                    final double n35 = n18 * (n28 / k);
                    this.func[i][j][0] = n34;
                    this.func[i][j][1] = n35;
                }
            }
        }
    }
    
    int sign(final double n) {
        return (n < 0.0) ? -1 : 1;
    }
    
    public void paint(final Graphics graphics) {
        this.cv.repaint();
    }
    
    public void updateModeBox(final Graphics graphics) {
        if (this.winSize == null || this.winSize.width == 0) {
            return;
        }
        final boolean state = this.memoryImageSourceCheck.getState();
        final Graphics graphics2 = this.dbimage.getGraphics();
        graphics2.setColor(this.cv.getBackground());
        graphics2.fillRect(0, 0, this.winSize.width, this.winSize.height);
        graphics2.setColor(this.cv.getForeground());
        boolean b = true;
        if (!this.stoppedCheck.getState()) {
            this.t += this.speedBar.getValue() * 0.00625;
            if (this.modeCount > 0) {
                b = false;
            }
        }
        for (int i = 0; i != this.modeCount; ++i) {
            final Mode mode = this.modes[i];
            mode.phasecoef = (mode.omega * this.t + mode.phasecoefadj) % 6.283185307179586;
            mode.phasecoefcos = Math.cos(mode.phasecoef);
            mode.phasemult = mode.phasecoefcos * mode.magcoef;
        }
        if (this.modeCount != 0) {
            this.computeFunction();
        }
        this.colorMult = this.brightnessBar.getValue() * 3;
        final int width = this.view3d.width;
        final int height = this.view3d.height;
        if (this.modeCount > 0) {
            for (int j = 0; j != this.gridSizeX; ++j) {
                for (int k = 0; k != this.gridSizeY; ++k) {
                    final int n = j * width / this.gridSizeX;
                    final int n2 = k * height / this.gridSizeY;
                    final int n3 = (j + 1) * width / this.gridSizeX;
                    final int n4 = (k + 1) * height / this.gridSizeY;
                    final int n5 = -16777216 + (this.getColorValue(j, k, 0) << 16) | this.getColorValue(j, k, 1) << 8;
                    if (state) {
                        for (int l = n; l < n3; ++l) {
                            for (int n6 = n2; n6 < n4; ++n6) {
                                this.pixels[l + n6 * this.view3d.width] = n5;
                            }
                        }
                    }
                    else {
                        graphics2.setColor(new Color(n5));
                        graphics2.fillRect(n, n2, n3 - n, n4 - n2);
                    }
                }
            }
            if (state) {
                graphics2.drawImage(this.cv.createImage(this.imageSource), 0, 0, null);
            }
        }
        graphics2.setColor(Color.white);
        for (int n7 = 0; n7 != 8; ++n7) {
            final int n8 = ((n7 & 0x1) == 0x0) ? -1 : 1;
            final int n9 = ((n7 & 0x2) == 0x0) ? -1 : 1;
            final int n10 = ((n7 & 0x4) == 0x0) ? -1 : 1;
            if (n8 == -1 && (this.visibleFace(0, n9, 0) || this.visibleFace(0, 0, n10))) {
                this.map3d(-1.0, n9, n10, this.xpoints, this.ypoints, 0);
                this.map3d(1.0, n9, n10, this.xpoints, this.ypoints, 1);
                graphics2.drawLine(this.xpoints[0], this.ypoints[0], this.xpoints[1], this.ypoints[1]);
            }
            if (n9 == -1 && (this.visibleFace(n8, 0, 0) || this.visibleFace(0, 0, n10))) {
                this.map3d(n8, -1.0, n10, this.xpoints, this.ypoints, 0);
                this.map3d(n8, 1.0, n10, this.xpoints, this.ypoints, 1);
                graphics2.drawLine(this.xpoints[0], this.ypoints[0], this.xpoints[1], this.ypoints[1]);
            }
            if (n10 == -1 && (this.visibleFace(n8, 0, 0) || this.visibleFace(0, n9, 0))) {
                this.map3d(n8, n9, -1.0, this.xpoints, this.ypoints, 0);
                this.map3d(n8, n9, 1.0, this.xpoints, this.ypoints, 1);
                graphics2.drawLine(this.xpoints[0], this.ypoints[0], this.xpoints[1], this.ypoints[1]);
            }
        }
        graphics2.setColor(Color.black);
        graphics2.fillRect(0, this.view3d.height, this.winSize.width, this.winSize.height - this.view3d.height);
        for (int n11 = 0; n11 != ModeBoxFrame.maxDispCoefs; ++n11) {
            this.drawFrequencies(graphics2, n11);
        }
        if (this.viewSpectrum != null) {
            final int n12 = (int)(((this.selectedCoefX == -1) ? 0.0 : this.getOmega(this.selectedCoefX, this.selectedCoefY, this.selectedCoefZ)) * 50.0);
            final int n13 = (int)(this.selectedMinOmega * 50.0);
            final int n14 = (int)(this.selectedMaxOmega * 50.0);
            final int n15 = this.viewSpectrum.height - 10;
            final int n16 = this.viewSpectrum.y + this.viewSpectrum.height - 5;
            for (int n17 = 1; n17 != this.winSize.width; ++n17) {
                if (this.spectrum[n17] != 0) {
                    int n18 = (int)(n15 * (0.2 + Math.log(this.spectrum[n17]) / 4.0));
                    if (n18 > n15) {
                        n18 = n15;
                    }
                    graphics2.setColor((n17 == n12 || (n17 >= n13 && n17 < n14)) ? Color.yellow : Color.gray);
                    graphics2.drawLine(n17, n16, n17, n16 - n18);
                }
            }
        }
        if (this.selectedCoefX != -1) {
            final String string = "Selected mode = (" + this.selectedCoefX + "," + this.selectedCoefY + "," + this.selectedCoefZ + ")";
            final FontMetrics fontMetrics = graphics2.getFontMetrics();
            graphics2.setColor(Color.yellow);
            graphics2.drawString(string, (this.winSize.width - fontMetrics.stringWidth(string)) / 2, this.view3d.y + this.view3d.height - fontMetrics.getDescent() - 2);
        }
        graphics.drawImage(this.dbimage, 0, 0, this);
        if (!b) {
            this.cv.repaint(this.pause);
        }
    }
    
    boolean visibleFace(final int n, final int n2, final int n3) {
        return (n - ModeBoxFrame.viewDistance * this.rotmatrix[2]) * n + (n2 - ModeBoxFrame.viewDistance * this.rotmatrix[5]) * n2 + (n3 - ModeBoxFrame.viewDistance * this.rotmatrix[8]) * n3 < 0.0;
    }
    
    void map3d(double n, double n2, final double n3, final int[] array, final int[] array2, final int n4) {
        n *= this.boxwidth / 2.0;
        n2 *= this.boxheight / 2.0;
        final double[] rotmatrix = this.rotmatrix;
        final double n5 = n * rotmatrix[0] + n2 * rotmatrix[3] + n3 * rotmatrix[6];
        final double n6 = n * rotmatrix[1] + n2 * rotmatrix[4] + n3 * rotmatrix[7];
        final double n7 = ModeBoxFrame.viewDistance - (n * rotmatrix[2] + n2 * rotmatrix[5] + n3 * rotmatrix[8]);
        double n8 = this.view3d.width * this.zoom / 2.0;
        double n9 = this.view3d.height * this.zoom / 2.0;
        final double n10 = this.view3d.width / this.view3d.height;
        if (n10 < 1.0) {
            n9 *= n10;
        }
        else {
            n8 /= n10;
        }
        array[n4] = this.view3d.width / 2 + (int)(n8 * n5 / n7);
        array2[n4] = this.view3d.height / 2 + (int)(n9 * n6 / n7);
    }
    
    void drawFrequencies(final Graphics graphics, final int n) {
        final Rectangle rectangle = this.viewFreq[n];
        final int termWidth = this.getTermWidth();
        graphics.setColor(Color.white);
        int i;
        int n2;
        for (n2 = (i = 0); i <= ModeBoxFrame.maxDispCoefs; ++i) {
            final int n3 = i * termWidth;
            graphics.drawLine(rectangle.x + n2 * termWidth, n3 + rectangle.y, rectangle.x + termWidth * ModeBoxFrame.maxDispCoefs, n3 + rectangle.y);
            graphics.drawLine(rectangle.x + n3, rectangle.y + n2 * termWidth, rectangle.x + n3, rectangle.y + termWidth * ModeBoxFrame.maxDispCoefs);
        }
        final int n4 = 65536;
        final int n5 = 256;
        for (int j = 0; j != this.modeCount; ++j) {
            final Mode mode = this.modes[j];
            if (mode.z == n) {
                final int n6 = rectangle.x + mode.x * termWidth;
                final int n7 = rectangle.y + mode.y * termWidth;
                int logcoef = this.logcoef(mode.magcoef);
                if (logcoef < -255) {
                    logcoef = -255;
                }
                if (logcoef > 255) {
                    logcoef = 255;
                }
                if (logcoef < 0) {
                    graphics.setColor(new Color(-16777216 + n4 * -logcoef));
                }
                else {
                    graphics.setColor(new Color(-16777216 + n5 * logcoef));
                }
                graphics.fillRect(n6 + 1, n7 + 1, termWidth - 1, termWidth - 1);
                final int n8 = (int)(mode.phasecoefadj * termWidth * 0.15915494309189535);
                if (n8 > 0) {
                    graphics.setColor(Color.blue);
                    graphics.drawLine(n6 + n8, n7 + 1, n6 + n8, n7 + termWidth);
                }
            }
        }
        if (this.selectedCoefX != -1) {
            final double omega = this.getOmega(this.selectedCoefX, this.selectedCoefY, this.selectedCoefZ);
            graphics.setColor(Color.yellow);
            for (int k = n2; k != ModeBoxFrame.maxDispCoefs; ++k) {
                for (int l = n2; l != ModeBoxFrame.maxDispCoefs; ++l) {
                    final int n9 = rectangle.x + k * termWidth;
                    final int n10 = rectangle.y + l * termWidth;
                    if (this.getOmega(k, l, n) == omega) {
                        graphics.drawRect(n9, n10, termWidth, termWidth);
                    }
                }
            }
        }
        if (this.selectedMinOmega > 0.0 && this.selectedMaxOmega > 0.0) {
            graphics.setColor(Color.yellow);
            for (int n11 = n2; n11 != ModeBoxFrame.maxDispCoefs; ++n11) {
                for (int n12 = n2; n12 != ModeBoxFrame.maxDispCoefs; ++n12) {
                    final int n13 = rectangle.x + n11 * termWidth;
                    final int n14 = rectangle.y + n12 * termWidth;
                    final double omega2 = this.getOmega(n11, n12, n);
                    if (omega2 >= this.selectedMinOmega && omega2 < this.selectedMaxOmega) {
                        graphics.drawRect(n13, n14, termWidth, termWidth);
                    }
                }
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
    
    int getColorValue(final int n, final int n2, final int n3) {
        int n4 = (int)(this.func[n][n2][n3] * this.colorMult);
        if (n4 > 255) {
            n4 = 255;
        }
        return n4;
    }
    
    public void componentHidden(final ComponentEvent componentEvent) {
    }
    
    public void componentMoved(final ComponentEvent componentEvent) {
    }
    
    public void componentShown(final ComponentEvent componentEvent) {
        this.cv.repaint();
    }
    
    public void componentResized(final ComponentEvent componentEvent) {
        this.handleResize();
        this.cv.repaint(this.pause);
    }
    
    public void actionPerformed(final ActionEvent actionEvent) {
        if (actionEvent.getSource() == this.clearButton) {
            while (this.modeCount > 0) {
                this.deleteMode(0);
            }
            this.cv.repaint();
        }
    }
    
    public void adjustmentValueChanged(final AdjustmentEvent adjustmentEvent) {
        if (adjustmentEvent.getSource() == this.widthBar || adjustmentEvent.getSource() == this.heightBar) {
            this.setWidthHeight();
        }
        if (adjustmentEvent.getSource() == this.resolutionBar) {
            this.setMaxTerms();
        }
        this.cv.repaint(this.pause);
    }
    
    void setWidthHeight() {
        this.boxwidth = this.widthBar.getValue() / 5.0;
        this.boxheight = this.heightBar.getValue() / 5.0;
        for (int i = 0; i != this.modeCount; ++i) {
            final Mode mode = this.modes[i];
            mode.omega = this.getOmega(mode.x, mode.y, mode.z);
        }
        this.calcSpectrum();
    }
    
    void calcSpectrum() {
        if (this.winSize == null) {
            return;
        }
        this.spectrum = new int[this.winSize.width];
        for (int i = 0; i != ModeBoxFrame.maxDispCoefs; ++i) {
            for (int j = 0; j != ModeBoxFrame.maxDispCoefs; ++j) {
                for (int k = 0; k != ModeBoxFrame.maxDispCoefs; ++k) {
                    final int n = (int)(this.getOmega(i, j, k) * 50.0);
                    if (n < this.winSize.width) {
                        final int[] spectrum = this.spectrum;
                        final int n2 = n;
                        ++spectrum[n2];
                    }
                }
            }
        }
    }
    
    public void mouseDragged(final MouseEvent mouseEvent) {
        this.dragging = true;
        this.oldDragX = this.dragX;
        this.oldDragY = this.dragY;
        this.dragX = mouseEvent.getX();
        this.dragY = mouseEvent.getY();
        this.edit(mouseEvent);
    }
    
    public void mouseMoved(final MouseEvent mouseEvent) {
        if ((mouseEvent.getModifiers() & 0x10) != 0x0) {
            if (this.selection != -1) {
                this.dragging = true;
            }
            return;
        }
        final int x = mouseEvent.getX();
        final int y = mouseEvent.getY();
        this.oldDragX = this.dragX;
        this.oldDragY = this.dragY;
        this.dragX = x;
        this.dragY = y;
        final int selectedCoefX = this.selectedCoefX;
        final int selectedCoefY = this.selectedCoefY;
        final int selectedCoefZ = this.selectedCoefZ;
        this.selectedCoefX = -1;
        this.selectedCoefY = -1;
        this.selectedCoefZ = -1;
        this.selection = 0;
        final double n = 0.0;
        this.selectedMaxOmega = n;
        this.selectedMinOmega = n;
        if (this.view3d.inside(x, y)) {
            this.selection = 1;
        }
        if (this.viewSpectrum != null && this.viewSpectrum.inside(x, y)) {
            this.selection = 3;
            this.selectedMinOmega = (x - 2) / 50.0;
            this.selectedMaxOmega = (x + 2) / 50.0;
        }
        for (int i = 0; i != ModeBoxFrame.maxDispCoefs; ++i) {
            final Rectangle rectangle = this.viewFreq[i];
            if (rectangle.inside(x, y)) {
                final int termWidth = this.getTermWidth();
                this.selectedCoefX = (x - rectangle.x) / termWidth;
                this.selectedCoefY = (y - rectangle.y) / termWidth;
                this.selectedCoefZ = i;
                if (this.selectedCoefX >= ModeBoxFrame.maxDispCoefs) {
                    this.selectedCoefX = -1;
                }
                if (this.selectedCoefY >= ModeBoxFrame.maxDispCoefs) {
                    this.selectedCoefX = -1;
                }
                if (this.selectedCoefX != -1) {
                    this.selection = 2;
                }
            }
        }
        if (this.selectedCoefX != selectedCoefX || this.selectedCoefY != selectedCoefY || this.selectedCoefZ != selectedCoefZ) {
            this.cv.repaint(this.pause);
        }
    }
    
    public void mouseClicked(final MouseEvent mouseEvent) {
        if (this.selection == 2) {
            this.editMagClick();
        }
    }
    
    public void mouseEntered(final MouseEvent mouseEvent) {
    }
    
    public void mouseExited(final MouseEvent mouseEvent) {
        if (!this.dragging && this.selection != -1) {
            final int selectedCoefX = -1;
            this.selectedCoefZ = selectedCoefX;
            this.selectedCoefY = selectedCoefX;
            this.selectedCoefX = selectedCoefX;
            this.cv.repaint(this.pause);
        }
    }
    
    public void mousePressed(final MouseEvent mouseEvent) {
        if ((mouseEvent.getModifiers() & 0x10) == 0x0) {
            return;
        }
        final int x = mouseEvent.getX();
        this.dragStartX = x;
        this.oldDragX = x;
        final int y = mouseEvent.getY();
        this.dragStartY = y;
        this.oldDragY = y;
        this.dragZoomStart = this.zoom;
        if (this.selectedCoefX != -1) {
            this.magDragStart = this.findSelectedMode().magcoef;
        }
        this.dragging = true;
        this.edit(mouseEvent);
    }
    
    public void mouseReleased(final MouseEvent mouseEvent) {
        if (this.dragging) {
            this.cv.repaint();
        }
        this.dragging = false;
    }
    
    public void itemStateChanged(final ItemEvent itemEvent) {
        if (itemEvent.getItemSelectable() == this.spectrumCheck) {
            this.setupDisplay();
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
    
    void edit(final MouseEvent mouseEvent) {
        if (this.selection == 0) {
            return;
        }
        final int x = mouseEvent.getX();
        final int y = mouseEvent.getY();
        switch (this.selection) {
            case 2: {
                this.editMag(x, y);
                break;
            }
            case 1: {
                this.edit3d(x, y);
                break;
            }
        }
    }
    
    void edit3d(final int n, final int n2) {
        if (this.modeChooser.getSelectedIndex() == 0) {
            this.rotate((this.oldDragX - n) / 40.0, (this.oldDragY - n2) / 40.0);
            this.cv.repaint(this.pause);
        }
        else if (this.modeChooser.getSelectedIndex() == 1) {
            this.zoom = this.dragZoomStart + (n - this.dragStartX) / 20.0;
            if (this.zoom < 0.1) {
                this.zoom = 0.1;
            }
            this.cv.repaint(this.pause);
        }
    }
    
    void editMag(final int n, final int n2) {
        if (this.selectedCoefX == -1) {
            return;
        }
        double magcoef = (this.dragStartY - n2) / 20.0 + this.magDragStart;
        if (magcoef < -1.0) {
            magcoef = -1.0;
        }
        if (magcoef > 1.0) {
            magcoef = 1.0;
        }
        double phasecoefadj = (n - this.dragStartX) / 10.0;
        if (phasecoefadj < 0.0) {
            phasecoefadj = 0.0;
        }
        if (phasecoefadj > 6.283185307179586) {
            phasecoefadj = 6.283185307179586;
        }
        final Mode selectedMode = this.findSelectedMode();
        if (selectedMode.magcoef == magcoef && selectedMode.phasecoefadj == phasecoefadj) {
            return;
        }
        selectedMode.magcoef = magcoef;
        selectedMode.phasecoefadj = phasecoefadj;
        this.cv.repaint(this.pause);
    }
    
    void editMagClick() {
        if (this.selectedCoefX == -1) {
            return;
        }
        final Mode selectedMode = this.findSelectedMode();
        if (this.magDragStart < 0.5) {
            selectedMode.magcoef = 1.0;
        }
        else {
            selectedMode.magcoef = 0.0;
        }
        selectedMode.phasecoefadj = 0.0;
        if (selectedMode.magcoef == 0.0) {
            this.deleteMode(selectedMode);
        }
        this.cv.repaint(this.pause);
    }
    
    void genData(final boolean b) {
        final double n = 3.14159265 / this.maxTerms;
        for (int i = 0; i != this.modeCount; ++i) {
            final Mode mode = this.modes[i];
            if (mode.tableSize != this.maxTerms) {
                mode.xtable = new double[this.maxTerms];
                mode.ytable = new double[this.maxTerms];
                mode.ztable = new double[this.maxTerms];
                mode.tableSize = this.maxTerms;
            }
            for (int j = 0; j != this.maxTerms; ++j) {
                mode.xtable[j] = Math.cos(j * mode.x * n) * mode.phasemult;
                mode.ytable[j] = Math.cos(j * mode.y * n);
                mode.ztable[j] = Math.cos(j * mode.z * n);
            }
            if (i == 0) {
                for (int k = 0; k != this.maxTerms; ++k) {
                    for (int l = 0; l != this.maxTerms; ++l) {
                        for (int n2 = 0; n2 != this.maxTerms; ++n2) {
                            this.data[k][l][n2] = mode.xtable[k] * mode.ytable[l] * mode.ztable[n2];
                        }
                    }
                }
            }
            else {
                for (int n3 = 0; n3 != this.maxTerms; ++n3) {
                    for (int n4 = 0; n4 != this.maxTerms; ++n4) {
                        for (int n5 = 0; n5 != this.maxTerms; ++n5) {
                            final double[] array = this.data[n3][n4];
                            final int n6 = n5;
                            array[n6] += mode.xtable[n3] * mode.ytable[n4] * mode.ztable[n5];
                        }
                    }
                }
            }
        }
    }
    
    void deleteMode(int i) {
        while (i < this.modeCount - 1) {
            this.modes[i] = this.modes[i + 1];
            ++i;
        }
        --this.modeCount;
    }
    
    void deleteMode(final Mode mode) {
        for (int i = 0; i != this.modeCount; ++i) {
            if (this.modes[i] == mode) {
                this.deleteMode(i);
                return;
            }
        }
    }
    
    Mode addMode(final int x, final int y, final int z) {
        if (this.modeCount == ModeBoxFrame.maxModes) {
            double magcoef = 1.0;
            int n = 0;
            for (int i = 0; i != this.modeCount; ++i) {
                final Mode mode = this.modes[i];
                if (mode.magcoef < magcoef) {
                    magcoef = mode.magcoef;
                    n = i;
                }
            }
            this.deleteMode(n);
        }
        final Mode mode2 = new Mode();
        mode2.x = x;
        mode2.y = y;
        mode2.z = z;
        mode2.magcoef = 0.0;
        mode2.phasecoef = 0.0;
        mode2.phasecoefcos = 1.0;
        mode2.phasecoefadj = 0.0;
        mode2.omega = this.getOmega(x, y, z);
        return this.modes[this.modeCount++] = mode2;
    }
    
    double getOmega(final int n, final int n2, final int n3) {
        return Math.sqrt(n * n / (this.boxwidth * this.boxwidth) + n2 * n2 / (this.boxheight * this.boxheight) + n3 * n3 / 4.0);
    }
    
    Mode findSelectedMode() {
        for (int i = 0; i != this.modeCount; ++i) {
            final Mode mode = this.modes[i];
            if (this.selectedCoefX == mode.x && this.selectedCoefY == mode.y && this.selectedCoefZ == mode.z) {
                return mode;
            }
        }
        return this.addMode(this.selectedCoefX, this.selectedCoefY, this.selectedCoefZ);
    }
    
    static {
        ModeBoxFrame.maxModes = 10;
        ModeBoxFrame.maxDispCoefs = 8;
        ModeBoxFrame.viewDistance = 12;
    }
    
    class Mode
    {
        public int x;
        public int y;
        public int z;
        public double magcoef;
        public double phasecoef;
        public double phasecoefcos;
        public double phasemult;
        public double phasecoefadj;
        public double omega;
        int tableSize;
        public double[] xtable;
        public double[] ytable;
        public double[] ztable;
    }
}
