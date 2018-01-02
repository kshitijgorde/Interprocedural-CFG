import java.awt.Event;
import java.awt.event.ItemEvent;
import java.awt.event.MouseEvent;
import java.awt.event.AdjustmentEvent;
import java.awt.event.ActionEvent;
import java.awt.event.ComponentEvent;
import java.awt.image.ImageProducer;
import java.awt.FontMetrics;
import java.awt.image.ImageObserver;
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

class EMBoxFrame extends Frame implements ComponentListener, ActionListener, AdjustmentListener, MouseMotionListener, MouseListener, ItemListener
{
    Thread engine;
    Dimension winSize;
    Image dbimage;
    Random random;
    int gridSizeX;
    int gridSizeY;
    Button clearButton;
    Button resetPartButton;
    static boolean waveguide;
    Checkbox memoryImageSourceCheck;
    Checkbox stoppedCheck;
    Checkbox stopOscCheck;
    Checkbox spectrumCheck;
    Checkbox sidesCheck;
    Choice modeChooser;
    Choice sliceChooser;
    Choice emChooser;
    Choice dispChooser;
    static final int EMCHOICE_E = 0;
    static final int EMCHOICE_B = 1;
    static final int EMCHOICE_EB = 2;
    static final int EMCHOICE_J = 3;
    static final int EMCHOICE_Q = 4;
    static final int DISP_PART = 0;
    static final int DISP_FIELD_MAG = 1;
    static final int DISP_FIELD_X = 2;
    static final int DISP_FIELD_Y = 3;
    static final int DISP_FIELD_Z = 4;
    static final int DISP_FIELD_COL = 5;
    static final int DISP_VECTORS = 6;
    Scrollbar speedBar;
    Scrollbar partSpeedBar;
    Scrollbar resolutionBar;
    Scrollbar vecDensityBar;
    Scrollbar brightnessBar;
    Scrollbar widthBar;
    Scrollbar heightBar;
    Scrollbar partCountBar;
    Scrollbar freqBar;
    double dragZoomStart;
    double zoom;
    double sliceval;
    double[] rotmatrix;
    double[] cameraPos;
    double selectedMinOmega;
    double selectedMaxOmega;
    Rectangle view3d;
    Rectangle view3d_e;
    Rectangle view3d_b;
    Rectangle viewAxes;
    Rectangle viewSpectrum;
    Rectangle[] viewFreq;
    double colorMult;
    static final double pi = 3.141592653589793;
    static final double pi2 = 6.283185307179586;
    static final int gridsize = 80;
    static final double densitygroupsize = 0.505;
    static final int densitygridsize = 4;
    static final int maxParticleCount = 1000;
    int vectorSpacing;
    int[] xpoints;
    int[] ypoints;
    int[][] slicerPoints;
    double[][] sliceFaces;
    double[] sliceFace;
    Particle[] particles;
    int[][][] density;
    int[] spectrum;
    static final int spectrumSpacing = 60;
    float[][][] func;
    double boxwidth;
    double boxheight;
    double boxdepth;
    int boxGuideMult;
    boolean dragging;
    boolean selectedSlice;
    MemoryImageSource imageSource;
    int[] pixels;
    int maxTerms;
    int maxModes;
    int maxDispCoefs;
    int maxZDispCoefs;
    int viewDistance;
    Mode[] modes;
    int modeCount;
    int pause;
    EMBox applet;
    int selection;
    static final int SEL_NONE = 0;
    static final int SEL_3D = 1;
    static final int SEL_MAG = 2;
    static final int SEL_SPECTRUM = 3;
    static final int MODE_ANGLE = 0;
    static final int MODE_ZOOM = 1;
    static final int SLICE_NONE = 0;
    static final int SLICE_X = 1;
    static final int SLICE_Y = 2;
    static final int SLICE_Z = 3;
    int selectedCoefX;
    int selectedCoefY;
    int selectedCoefZ;
    boolean selectedCoefTEMode;
    static final int sampleCount = 15;
    int[] sampleMult;
    int curfieldno;
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
    int[][] sidemap;
    DynControl[] dynControls;
    EMBoxCanvas cv;
    boolean allQuiet;
    double logep2;
    int rediscount;
    boolean boundCheck;
    double[] oldY;
    double[] rk_k1;
    double[] rk_k2;
    double[] rk_k3;
    double[] rk_k4;
    double[] rk_yn;
    double[] rk_Y;
    double[] rk_Yhalf;
    double[] rk_oldY;
    
    public String getAppletInfo() {
        return "EMBox by Paul Falstad";
    }
    
    int getrand(final int n) {
        int nextInt = this.random.nextInt();
        if (nextInt < 0) {
            nextInt = -nextInt;
        }
        return nextInt % n;
    }
    
    EMBoxFrame(final EMBox applet) {
        super("EM Modes Applet");
        this.engine = null;
        this.gridSizeX = 200;
        this.gridSizeY = 200;
        this.zoom = 6.5;
        this.sliceval = 0.0;
        this.vectorSpacing = 16;
        this.boxwidth = 2.0;
        this.boxheight = 2.0;
        this.boxdepth = 2.0;
        this.boxGuideMult = 1;
        this.dragging = false;
        this.maxTerms = 16;
        this.maxModes = 10;
        this.maxDispCoefs = 5;
        this.maxZDispCoefs = 5;
        this.viewDistance = 12;
        this.modeCount = 0;
        this.selection = -1;
        this.selectedCoefX = -1;
        this.t = 0.0;
        this.logep2 = 0.0;
        this.rk_k1 = new double[6];
        this.rk_k2 = new double[6];
        this.rk_k3 = new double[6];
        this.rk_k4 = new double[6];
        this.rk_yn = new double[6];
        this.rk_Y = new double[6];
        this.rk_Yhalf = new double[6];
        this.rk_oldY = new double[6];
        this.applet = applet;
    }
    
    public void init() {
        final String property = System.getProperty("os.name");
        final String property2 = System.getProperty("java.version");
        boolean b = false;
        int n = 54;
        if (property.indexOf("Windows") == 0) {
            n = 100;
            if (property2.indexOf("1.1") == 0) {
                b = true;
            }
        }
        this.setLayout(new EMBoxLayout());
        (this.cv = new EMBoxCanvas(this)).addComponentListener(this);
        this.cv.addMouseMotionListener(this);
        this.cv.addMouseListener(this);
        this.add(this.cv);
        this.add(this.clearButton = new Button("Clear"));
        this.clearButton.addActionListener(this);
        this.add(this.resetPartButton = new Button("Reset Particles"));
        this.resetPartButton.addActionListener(this);
        (this.stoppedCheck = new Checkbox("Stop")).addItemListener(this);
        this.add(this.stoppedCheck);
        (this.stopOscCheck = new Checkbox("Stop Oscillation")).addItemListener(this);
        this.add(this.stopOscCheck);
        (this.spectrumCheck = new Checkbox("Show Spectrum")).addItemListener(this);
        this.add(this.spectrumCheck);
        (this.memoryImageSourceCheck = new Checkbox("Alternate Rendering", b)).addItemListener(this);
        this.add(this.memoryImageSourceCheck);
        (this.sidesCheck = new Checkbox("Show Sides")).addItemListener(this);
        this.add(this.sidesCheck);
        (this.modeChooser = new Choice()).add("Mouse = Adjust Angle");
        this.modeChooser.add("Mouse = Adjust Zoom");
        this.modeChooser.addItemListener(this);
        this.add(this.modeChooser);
        (this.sliceChooser = new Choice()).add("No Slicing");
        this.sliceChooser.add("Show X Slice");
        this.sliceChooser.add("Show Y Slice");
        this.sliceChooser.add("Show Z Slice");
        this.sliceChooser.addItemListener(this);
        this.add(this.sliceChooser);
        (this.emChooser = new Choice()).add("Show Electric Field");
        this.emChooser.add("Show Magnetic Field");
        this.emChooser.add("Show Both Fields");
        this.emChooser.add("Show Current");
        this.emChooser.add("Show Charge");
        this.emChooser.addItemListener(this);
        this.add(this.emChooser);
        (this.dispChooser = new Choice()).add("Show Particles on Field Lines");
        this.dispChooser.add("Show Field Magnitude");
        this.dispChooser.add("Show Field X");
        this.dispChooser.add("Show Field Y");
        this.dispChooser.add("Show Field Z");
        this.dispChooser.add("Show Field (tri-color)");
        this.dispChooser.add("Show Field Vectors");
        this.dispChooser.addItemListener(this);
        this.add(this.dispChooser);
        this.dispChooser.select(5);
        this.dynControls = new DynControl[6];
        this.add(new Label("Oscillation Speed", 1));
        this.add(this.speedBar = new Scrollbar(0, 15, 1, 1, 200));
        this.speedBar.addAdjustmentListener(this);
        final Label label;
        this.add(label = new Label("Number of Particles", 1));
        this.add(this.partCountBar = new Scrollbar(0, 500, 1, 1, 1000));
        this.partCountBar.addAdjustmentListener(this);
        this.dynControls[0] = new DynControl(this.partCountBar, label, 0);
        final Label label2;
        this.add(label2 = new Label("Particle Speed", 1));
        this.add(this.partSpeedBar = new Scrollbar(0, 90, 1, 1, 200));
        this.partSpeedBar.addAdjustmentListener(this);
        this.dynControls[1] = new DynControl(this.partSpeedBar, label2, 0);
        final Label label3;
        this.add(label3 = new Label("Brightness", 1));
        this.add(this.brightnessBar = new Scrollbar(0, 28, 1, 1, 200));
        this.brightnessBar.addAdjustmentListener(this);
        this.dynControls[2] = new DynControl(this.brightnessBar, label3, 1, 6);
        final Label label4;
        this.add(label4 = new Label("Image Resolution", 1));
        this.add(this.resolutionBar = new Scrollbar(0, n, 1, 20, 200));
        this.resolutionBar.addAdjustmentListener(this);
        this.dynControls[3] = new DynControl(this.resolutionBar, label4, 1);
        final Label label5;
        this.add(label5 = new Label("Vector Density", 1));
        this.add(this.vecDensityBar = new Scrollbar(0, 10, 1, 2, 64));
        this.vecDensityBar.addAdjustmentListener(this);
        this.dynControls[4] = new DynControl(this.vecDensityBar, label5, 6);
        this.add(new Label("Width", 1));
        this.add(this.widthBar = new Scrollbar(0, 10, 1, 5, 31));
        this.widthBar.addAdjustmentListener(this);
        this.add(new Label("Height", 1));
        this.add(this.heightBar = new Scrollbar(0, 10, 1, 5, 31));
        this.heightBar.addAdjustmentListener(this);
        final Label label6;
        this.add(label6 = new Label("Driving Frequency", 1));
        this.add(this.freqBar = new Scrollbar(0, 10, 1, 5, 50));
        this.freqBar.addAdjustmentListener(this);
        this.add(new Label("http://www.falstad.com", 1));
        try {
            final String parameter = this.applet.getParameter("PAUSE");
            if (parameter != null) {
                this.pause = Integer.parseInt(parameter);
            }
        }
        catch (Exception ex) {}
        if (!(EMBoxFrame.waveguide = this.applet.getParameter("waveguide").equals("true"))) {
            this.vecDensityBar.setValue(16);
        }
        if (EMBoxFrame.waveguide) {
            this.boxGuideMult = 3;
            this.boxdepth *= this.boxGuideMult;
            this.maxZDispCoefs = 2;
            this.zoom = 3.25;
            this.spectrumCheck.hide();
        }
        else {
            this.freqBar.hide();
            label6.hide();
        }
        this.modes = new Mode[this.maxModes];
        this.addMode(1, 0, 1, true).magcoef = 1.0;
        this.slicerPoints = new int[2][10];
        this.sliceFaces = new double[4][3];
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
        this.rotate(-1.5707963267948966, 0.0);
        this.rotate(-0.7853981633974483, 0.7853981633974483);
        this.xpoints = new int[4];
        this.ypoints = new int[4];
        this.density = new int[4][4][4];
        this.sampleMult = new int[15];
        for (int i = 1; i < 15; i += 2) {
            this.sampleMult[i] = 4;
            this.sampleMult[i + 1] = 2;
        }
        this.sampleMult[0] = (this.sampleMult[14] = 1);
        this.sidemap = new int[6][3];
        for (int j = 0; j != 3; ++j) {
            this.sidemap[j * 2][j] = 1;
            this.sidemap[j * 2 + 1][j] = -1;
        }
        this.random = new Random();
        this.particles = new Particle[1000];
        for (int k = 0; k != 1000; ++k) {
            this.particles[k] = new Particle();
        }
        this.reinit();
        this.cv.setBackground(Color.black);
        this.cv.setForeground(Color.white);
        this.resize(500, 550);
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
        int width = this.view3d.width;
        if (this.emChooser.getSelectedIndex() == 2) {
            width /= 2;
        }
        this.pixels = new int[width * this.view3d.height];
        for (int i = 0; i != width * this.view3d.height; ++i) {
            this.pixels[i] = -16777216;
        }
        this.imageSource = new MemoryImageSource(width, this.view3d.height, this.pixels, 0, width);
        this.resetParticles();
        this.setDynamicControls();
    }
    
    int getTermWidth() {
        return 8;
    }
    
    void resetDensityGroups() {
        for (int i = 0; i != 4; ++i) {
            for (int j = 0; j != 4; ++j) {
                for (int k = 0; k != 4; ++k) {
                    this.density[i][j][k] = 0;
                }
            }
        }
        final int selectedIndex = this.sliceChooser.getSelectedIndex();
        final boolean b = selectedIndex > 0;
        int particleCount;
        int l;
        for (particleCount = this.getParticleCount(), l = 0; l != particleCount; ++l) {
            final Particle particle = this.particles[l];
            if (b) {
                particle.pos[selectedIndex - 1] = this.sliceval;
            }
            this.addToDensityGroup(particle);
        }
        while (l != 1000) {
            this.particles[l].lifetime = -100;
            ++l;
        }
    }
    
    int addToDensityGroup(final Particle particle) {
        final int n = (int)((particle.pos[0] + 1.0) / 0.505);
        final int n2 = (int)((particle.pos[1] + 1.0) / 0.505);
        final int n3 = (int)((particle.pos[2] + 1.0) / 0.505);
        int n4 = 0;
        try {
            n4 = ++this.density[n][n2][n3];
            if (n4 > 1000) {
                System.out.print(n + " " + n2 + " " + n3 + " " + this.density[n][n2][n3] + "\n");
            }
        }
        catch (Exception ex) {
            System.out.print(particle.pos[0] + " " + particle.pos[1] + " " + particle.pos[2] + "\n");
            ex.printStackTrace();
        }
        return n4;
    }
    
    void removeFromDensityGroup(final Particle particle) {
        final int n = (int)((particle.pos[0] + 1.0) / 0.505);
        final int n2 = (int)((particle.pos[1] + 1.0) / 0.505);
        final int n3 = (int)((particle.pos[2] + 1.0) / 0.505);
        try {
            final int[] array = this.density[n][n2];
            final int n4 = n3;
            final int n5 = array[n4] - 1;
            array[n4] = n5;
            if (n5 < 0) {
                System.out.print(n + " " + n2 + " " + n3 + " " + this.density[n][n2][n3] + "\n");
            }
        }
        catch (Exception ex) {
            System.out.print(particle.pos[0] + " " + particle.pos[1] + " " + particle.pos[2] + "\n");
            ex.printStackTrace();
        }
    }
    
    void positionParticle(final Particle particle) {
        int n = 0;
        int n2 = 0;
        int n3 = 0;
        int n4 = 10000;
        final int getrand = this.getrand(4);
        final int getrand2 = this.getrand(4);
        final int getrand3 = this.getrand(4);
        final int n5 = 3;
        for (int i = 0; i != 4; ++i) {
            for (int j = 0; j != 4; ++j) {
                for (int k = 0; k != 4; ++k) {
                    final int n6 = (getrand + i) % 4;
                    final int n7 = (getrand2 + j) % 4;
                    final int n8 = (getrand3 + k) % 4;
                    if (this.sidesCheck.getState() && n6 != 0 && n6 != n5 && n7 != 0 && n7 != n5) {
                        if (EMBoxFrame.waveguide) {
                            continue;
                        }
                        if (n8 != 0 && n8 != n5) {
                            continue;
                        }
                    }
                    if (this.density[n6][n7][n8] <= n4) {
                        n = n6;
                        n2 = n7;
                        n3 = n8;
                        n4 = this.density[n6][n7][n8];
                    }
                }
            }
        }
        particle.pos[0] = n * 0.505 + this.getrand(100) * 0.505 / 100.0 - 1.0;
        particle.pos[1] = n2 * 0.505 + this.getrand(100) * 0.505 / 100.0 - 1.0;
        particle.pos[2] = n3 * 0.505 + this.getrand(100) * 0.505 / 100.0 - 1.0;
        particle.lifetime = 500;
        if (this.sidesCheck.getState()) {
            int side;
            if (n == n5) {
                side = 0;
            }
            else if (n == 0) {
                side = 1;
            }
            else if (n2 == n5) {
                side = 2;
            }
            else if (n2 == 0) {
                side = 3;
            }
            else if (n3 == n5) {
                side = 4;
            }
            else {
                side = 5;
            }
            if (EMBoxFrame.waveguide && side >= 4) {
                particle.lifetime = -1;
            }
            particle.side = side;
            particle.pos[particle.side / 2] = this.sidemap[particle.side][particle.side / 2];
        }
    }
    
    int getParticleCount() {
        return this.partCountBar.getValue();
    }
    
    void resetParticles() {
        for (int particleCount = this.getParticleCount(), i = 0; i != particleCount; ++i) {
            final Particle particle = this.particles[i];
            for (int j = 0; j != 3; ++j) {
                particle.pos[j] = this.getrand(200) / 100.0 - 1.0;
            }
            particle.lifetime = i * 2;
            if (this.sidesCheck.getState()) {
                particle.side = this.getrand(EMBoxFrame.waveguide ? 4 : 6);
                particle.pos[particle.side / 2] = this.sidemap[particle.side][particle.side / 2];
            }
        }
        this.resetDensityGroups();
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
        this.func = new float[this.gridSizeX][this.gridSizeY][3];
        this.regenData();
    }
    
    void regenData() {
        for (int i = 0; i != this.modeCount; ++i) {
            final ModeData modeData = this.modes[i].modeDatas[0];
            final ModeData modeData2 = this.modes[i].modeDatas[1];
            final float[][][] array = null;
            modeData2.data = array;
            modeData.data = array;
        }
    }
    
    void setupDisplay() {
        final int n = 2;
        final int maxZDispCoefs = this.maxZDispCoefs;
        final int n2 = this.getTermWidth() * (this.maxDispCoefs + 1) * n;
        final int n3 = this.spectrumCheck.getState() ? (this.getTermWidth() * 6) : 0;
        this.view3d = new Rectangle(0, 0, this.winSize.width, this.winSize.height - n2 - n3);
        this.view3d_e = new Rectangle(this.view3d);
        final Rectangle view3d_e = this.view3d_e;
        view3d_e.width /= 2;
        this.view3d_b = new Rectangle(this.view3d);
        final Rectangle view3d_b = this.view3d_b;
        view3d_b.width /= 2;
        final Rectangle view3d_b2 = this.view3d_b;
        view3d_b2.x += this.view3d_b.width;
        if (this.spectrumCheck.getState()) {
            this.viewSpectrum = new Rectangle(0, this.view3d.height, this.winSize.width, n3);
        }
        else {
            this.viewSpectrum = null;
        }
        this.viewAxes = new Rectangle(this.winSize.width - 100, 0, 100, 100);
        this.viewFreq = new Rectangle[this.maxZDispCoefs * 2];
        final int n5;
        final int n4 = n5 = this.getTermWidth() * this.maxDispCoefs;
        final int termWidth = this.getTermWidth();
        final int n6 = (this.winSize.width - (n4 * 4 + termWidth * 3)) / 2;
        for (int i = 0; i != this.maxZDispCoefs * 2; ++i) {
            this.viewFreq[i] = new Rectangle(n6 + i % maxZDispCoefs * (n4 + termWidth), this.view3d.height + n3 + i / maxZDispCoefs * (n5 + termWidth), n4, n5);
        }
    }
    
    void computeFunction(final Rectangle rectangle, final int n) {
        final double n2 = 3.141592653589793 / this.maxTerms;
        Math.cos(this.t);
        final double n3 = 1.0 / this.zoom;
        final double[] rotmatrix = this.rotmatrix;
        final float n4 = (float)this.boxwidth / 2.0f;
        final float n5 = (float)this.boxheight / 2.0f;
        final float n6 = (float)this.boxdepth / 2.0f;
        final double n7 = rectangle.width / rectangle.height;
        int selectedIndex = this.dispChooser.getSelectedIndex();
        boolean state = this.sidesCheck.getState();
        int n8 = n;
        if (n == 4) {
            n8 = 0;
            this.genData(0);
            selectedIndex = -1;
            state = true;
        }
        else if (n == 3) {
            n8 = 1;
            this.genData(1);
            state = true;
        }
        else {
            this.genData(n);
        }
        for (int i = 0; i != this.gridSizeX; ++i) {
            for (int j = 0; j != this.gridSizeY; ++j) {
                final double n9 = 0.0;
                final double n10 = this.viewDistance;
                double n11 = (2 * i / this.gridSizeX - 1.0) * n3;
                double n12 = -(2 * j / this.gridSizeY - 1.0) * n3;
                if (n7 < 1.0) {
                    n12 /= n7;
                }
                else {
                    n11 *= n7;
                }
                final double n13 = -1.0;
                final double n14 = rotmatrix[0] * n9 + rotmatrix[2] * n10;
                final double n15 = rotmatrix[5] * n10;
                final double n16 = rotmatrix[6] * n9 + rotmatrix[8] * n10;
                final double n17 = rotmatrix[0] * n11 + rotmatrix[1] * n12 + rotmatrix[2] * n13;
                final double n18 = rotmatrix[3] * n11 + rotmatrix[4] * n12 + rotmatrix[5] * n13;
                final double n19 = rotmatrix[6] * n11 + rotmatrix[7] * n12 + rotmatrix[8] * n13;
                final float n20 = (float)Math.sqrt(n17 * n17 + n18 * n18 + n19 * n19);
                float n21 = 0.0f;
                float n22 = 0.0f;
                float n23 = 0.0f;
                final double n24 = (-n4 - n14) / n17;
                final double n25 = (n4 - n14) / n17;
                final double n26 = (-n5 - n15) / n18;
                final double n27 = (n5 - n15) / n18;
                final double n28 = (-n6 - n16) / n19;
                final double n29 = (n6 - n16) / n19;
                float n30 = (float)(this.max(this.min(n24, n25), this.max(this.min(n26, n27), this.min(n28, n29))) + 0.001);
                final float n31 = (float)(this.min(this.max(n24, n25), this.min(this.max(n26, n27), this.max(n28, n29))) - 0.001);
                if (n31 < n30) {
                    final float[] array = this.func[i][j];
                    final int n32 = 0;
                    final float[] array2 = this.func[i][j];
                    final int n33 = 1;
                    final float[] array3 = this.func[i][j];
                    final int n34 = 2;
                    final float n35 = 0.0f;
                    array3[n34] = n35;
                    array[n32] = (array2[n33] = n35);
                }
                else {
                    final float n36 = (n31 - n30) / 14.0f;
                    float n37 = (n31 - n30) * n20;
                    final float n38 = (float)(this.maxTerms / this.boxwidth);
                    final float n39 = (float)(this.maxTerms / this.boxheight);
                    final float n40 = (float)(this.maxTerms / this.boxdepth);
                    int n41 = 15;
                    final int selectedIndex2 = this.sliceChooser.getSelectedIndex();
                    if (selectedIndex2 > 0) {
                        double n42;
                        if (selectedIndex2 == 1) {
                            n42 = (this.sliceval * n4 - n14) / n17;
                        }
                        else if (selectedIndex2 == 2) {
                            n42 = (this.sliceval * n5 - n15) / n18;
                        }
                        else {
                            n42 = (this.sliceval * n6 - n16) / n19;
                        }
                        if (n42 < n30 || n42 > n31) {
                            final float[] array4 = this.func[i][j];
                            final int n43 = 0;
                            final float[] array5 = this.func[i][j];
                            final int n44 = 1;
                            final float[] array6 = this.func[i][j];
                            final int n45 = 2;
                            final float n46 = 0.0f;
                            array6[n45] = n46;
                            array4[n43] = (array5[n44] = n46);
                            continue;
                        }
                        n30 = (float)n42;
                        n37 = 2.0f;
                        n41 = 1;
                    }
                    if (state) {
                        n41 = 1;
                        n37 = 4.0f;
                    }
                    final float n47 = (float)n14;
                    final float n48 = (float)n15;
                    final float n49 = (float)n16;
                    final float n50 = (float)n17;
                    final float n51 = (float)n18;
                    final float n52 = (float)n19;
                    int k;
                    for (k = 0; k < n41; ++k) {
                        final float n53 = n30 + k * n36;
                        final float n54 = n47 + n50 * n53;
                        final float n55 = n48 + n51 * n53;
                        final float n56 = n49 + n52 * n53;
                        final int n57 = (int)((n54 + n4) * n38);
                        final int n58 = (int)((n55 + n5) * n39);
                        final int n59 = (int)((n56 + n6) * n40);
                        float n60 = 0.0f;
                        float n61 = 0.0f;
                        float n62 = 0.0f;
                        for (int l = 0; l != this.modeCount; ++l) {
                            final ModeData modeData = this.modes[l].modeDatas[n8];
                            final double n63 = modeData.zmode_xymult[n59];
                            final double n64 = modeData.zmode_zmult[n59];
                            n60 += (float)(modeData.data[n57][n58][0] * n63);
                            n61 += (float)(modeData.data[n57][n58][1] * n63);
                            n62 += (float)(modeData.data[n57][n58][2] * n64);
                        }
                        if (n == 3) {
                            if (n54 < -0.99) {
                                n60 = 0.0f;
                                final float n65 = n61;
                                n61 = -n62;
                                n62 = n65;
                            }
                            else if (n54 > 0.99) {
                                n60 = 0.0f;
                                final float n66 = n61;
                                n61 = n62;
                                n62 = -n66;
                            }
                            else if (n55 < -0.99) {
                                n61 = 0.0f;
                                final float n67 = n60;
                                n60 = n62;
                                n62 = -n67;
                            }
                            else if (n55 > 0.99) {
                                n61 = 0.0f;
                                final float n68 = n60;
                                n60 = -n62;
                                n62 = n68;
                            }
                            else if (n56 < -0.99 && !EMBoxFrame.waveguide) {
                                n62 = 0.0f;
                                final float n69 = n60;
                                n60 = -n61;
                                n61 = n69;
                            }
                            else if (n56 > 0.99 && !EMBoxFrame.waveguide) {
                                n62 = 0.0f;
                                final float n70 = n60;
                                n60 = n61;
                                n61 = -n70;
                            }
                            else {
                                n61 = (n60 = (n62 = 0.0f));
                            }
                        }
                        if (selectedIndex == 5) {
                            final float abs = Math.abs(n60);
                            final float abs2 = Math.abs(n61);
                            final float abs3 = Math.abs(n62);
                            n21 += this.sampleMult[k] * abs;
                            n22 += this.sampleMult[k] * abs2;
                            n23 += this.sampleMult[k] * abs3;
                        }
                        else {
                            float n71 = 0.0f;
                            switch (selectedIndex) {
                                case 1: {
                                    n71 = (float)Math.sqrt(n60 * n60 + n61 * n61 + n62 * n62);
                                    break;
                                }
                                case 2: {
                                    n71 = n60;
                                    break;
                                }
                                case 3: {
                                    n71 = n61;
                                    break;
                                }
                                case 4: {
                                    n71 = n62;
                                    break;
                                }
                                case -1: {
                                    if (n54 < -0.99) {
                                        n71 = n60;
                                        break;
                                    }
                                    if (n54 > 0.99) {
                                        n71 = -n60;
                                        break;
                                    }
                                    if (n55 < -0.99) {
                                        n71 = n61;
                                        break;
                                    }
                                    if (n55 > 0.99) {
                                        n71 = -n61;
                                        break;
                                    }
                                    if (n56 < -0.99 && !EMBoxFrame.waveguide) {
                                        n71 = n62;
                                        break;
                                    }
                                    if (n56 > 0.99 && !EMBoxFrame.waveguide) {
                                        n71 = -n62;
                                        break;
                                    }
                                    n71 = 0.0f;
                                    break;
                                }
                            }
                            if (n71 < 0.0f) {
                                n21 += this.sampleMult[k] * Math.abs(n71);
                            }
                            else {
                                n22 += this.sampleMult[k] * n71;
                            }
                        }
                    }
                    final float n72 = n21 * (n37 / k);
                    final float n73 = n22 * (n37 / k);
                    final float n74 = n23 * (n37 / k);
                    this.func[i][j][0] = n72;
                    this.func[i][j][1] = n73;
                    this.func[i][j][2] = n74;
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
    
    public void updateEMBox(final Graphics graphics) {
        if (this.winSize == null || this.winSize.width == 0) {
            return;
        }
        final Graphics graphics2 = this.dbimage.getGraphics();
        graphics2.setColor(this.cv.getBackground());
        graphics2.fillRect(0, 0, this.winSize.width, this.winSize.height);
        graphics2.setColor(this.cv.getForeground());
        this.allQuiet = true;
        final int selectedIndex = this.dispChooser.getSelectedIndex();
        if (!this.stoppedCheck.getState() && !this.stopOscCheck.getState()) {
            final int value = this.speedBar.getValue();
            double n = value * 0.004;
            if (selectedIndex == 6 || selectedIndex == 0) {
                n /= 4.0;
            }
            this.t += n + value * this.getrand(20) * 2.7279275E-4;
            if (this.modeCount > 0) {
                this.allQuiet = false;
            }
        }
        for (int i = 0; i != this.modeCount; ++i) {
            final Mode mode = this.modes[i];
            mode.phasecoef = (mode.omega * this.t + mode.phasecoefadj) % 6.283185307179586;
            final double cos = Math.cos(mode.phasecoef);
            final double sin = Math.sin(mode.phasecoef);
            mode.ephaseshift = (EMBoxFrame.waveguide ? (-mode.phasecoef) : 0.0);
            mode.bphaseshift = (EMBoxFrame.waveguide ? (-mode.phasecoef) : 0.0);
            mode.ephasemult = (EMBoxFrame.waveguide ? mode.magcoef : (sin * mode.magcoef));
            mode.bphasemult = (EMBoxFrame.waveguide ? mode.magcoef : (cos * mode.magcoef));
            this.calcModeMults(mode, true);
        }
        if (this.emChooser.getSelectedIndex() == 2) {
            this.doDisplay(this.view3d_b, graphics2, 1);
            this.doDisplay(this.view3d_e, graphics2, 0);
        }
        else {
            this.doDisplay(this.view3d, graphics2, this.emChooser.getSelectedIndex());
        }
        graphics2.setColor(Color.black);
        graphics2.fillRect(0, this.view3d.height, this.winSize.width, this.winSize.height - this.view3d.height);
        for (int j = 0; j != this.maxZDispCoefs; ++j) {
            this.drawFrequencies(graphics2, j, false);
            this.drawFrequencies(graphics2, j, true);
        }
        this.map3d(0.0, 0.0, 0.0, this.xpoints, this.ypoints, 0, this.viewAxes);
        final Color color = (selectedIndex == 1 || selectedIndex == 0) ? Color.white : Color.gray;
        this.map3d(1.0, 0.0, 0.0, this.xpoints, this.ypoints, 1, this.viewAxes);
        graphics2.setColor((selectedIndex == 5) ? Color.red : ((selectedIndex == 2) ? Color.green : color));
        this.drawArrow(graphics2, "x", this.xpoints[0], this.ypoints[0], this.xpoints[1], this.ypoints[1]);
        if (selectedIndex == 2) {
            this.map3d(-1.0, 0.0, 0.0, this.xpoints, this.ypoints, 1, this.viewAxes);
            graphics2.setColor(Color.red);
            this.drawArrow(graphics2, null, this.xpoints[0], this.ypoints[0], this.xpoints[1], this.ypoints[1]);
        }
        this.map3d(0.0, 1.0, 0.0, this.xpoints, this.ypoints, 1, this.viewAxes);
        graphics2.setColor((selectedIndex == 5) ? Color.green : ((selectedIndex == 3) ? Color.green : color));
        this.drawArrow(graphics2, "y", this.xpoints[0], this.ypoints[0], this.xpoints[1], this.ypoints[1]);
        if (selectedIndex == 3) {
            this.map3d(0.0, -1.0, 0.0, this.xpoints, this.ypoints, 1, this.viewAxes);
            graphics2.setColor(Color.red);
            this.drawArrow(graphics2, null, this.xpoints[0], this.ypoints[0], this.xpoints[1], this.ypoints[1]);
        }
        this.map3d(0.0, 0.0, 1.0, this.xpoints, this.ypoints, 1, this.viewAxes);
        graphics2.setColor((selectedIndex == 5) ? Color.blue : ((selectedIndex == 4) ? Color.green : color));
        this.drawArrow(graphics2, "z", this.xpoints[0], this.ypoints[0], this.xpoints[1], this.ypoints[1]);
        if (selectedIndex == 4) {
            this.map3d(0.0, 0.0, -1.0, this.xpoints, this.ypoints, 1, this.viewAxes);
            graphics2.setColor(Color.red);
            this.drawArrow(graphics2, null, this.xpoints[0], this.ypoints[0], this.xpoints[1], this.ypoints[1]);
        }
        if (this.viewSpectrum != null) {
            final int n2 = (int)(((this.selectedCoefX == -1) ? 0.0 : this.getOmega(this.selectedCoefX, this.selectedCoefY, this.selectedCoefZ)) * 60.0);
            final int n3 = (int)(this.selectedMinOmega * 60.0);
            final int n4 = (int)(this.selectedMaxOmega * 60.0);
            final int n5 = this.viewSpectrum.height - 10;
            final int n6 = this.viewSpectrum.y + this.viewSpectrum.height - 5;
            for (int k = 1; k != this.winSize.width; ++k) {
                if (this.spectrum[k] != 0) {
                    int n7 = (int)(n5 * (0.2 + Math.log(this.spectrum[k]) / 4.0));
                    if (n7 > n5) {
                        n7 = n5;
                    }
                    graphics2.setColor((k == n2 || (k >= n3 && k < n4)) ? Color.yellow : Color.gray);
                    graphics2.drawLine(k, n6, k, n6 - n7);
                }
            }
        }
        if (this.selectedCoefX != -1) {
            String s = "Selected mode = " + (this.selectedCoefTEMode ? "TE (" : "TM (") + this.selectedCoefX + "," + this.selectedCoefY;
            if (!EMBoxFrame.waveguide) {
                s = s + "," + this.selectedCoefZ;
            }
            final String string = s + ")";
            final FontMetrics fontMetrics = graphics2.getFontMetrics();
            graphics2.setColor(Color.yellow);
            graphics2.drawString(string, (this.winSize.width - fontMetrics.stringWidth(string)) / 2, this.view3d.y + this.view3d.height - fontMetrics.getDescent() - 2);
        }
        graphics.drawImage(this.dbimage, 0, 0, this);
        if (!this.allQuiet) {
            this.cv.repaint(this.pause);
        }
    }
    
    void doDisplay(final Rectangle view, final Graphics g, final int realfieldno) {
        final boolean state = this.memoryImageSourceCheck.getState();
        this.colorMult = this.brightnessBar.getValue() * 3;
        final int width = view.width;
        final int height = view.height;
        final int selectedIndex = this.sliceChooser.getSelectedIndex();
        final boolean b = selectedIndex > 0;
        this.curfieldno = realfieldno;
        this.drawCube(g, view, true);
        this.unmap3d(this.cameraPos = new double[3], view.width / 2, view.height / 2, this.viewDistance, view);
        int selectedIndex2 = this.dispChooser.getSelectedIndex();
        if (realfieldno == 4) {
            selectedIndex2 = 1;
        }
        if (selectedIndex2 == 6) {
            final Particle particle = this.particles[0];
            final DrawData drawData = new DrawData();
            drawData.mult = this.colorMult / 30.0;
            drawData.g = g;
            drawData.view = view;
            drawData.fieldno = realfieldno;
            if ((drawData.realfieldno = realfieldno) == 3) {
                drawData.fieldno = 1;
            }
            this.vectorSpacing = this.vecDensityBar.getValue();
            this.genData(drawData.fieldno);
            final double n = 0.5 * this.sliceval + 0.5;
            if (this.sidesCheck.getState()) {
                this.drawVectorsX(0.0, drawData);
                this.drawVectorsX(1.0, drawData);
                this.drawVectorsY(0.0, drawData);
                this.drawVectorsY(1.0, drawData);
                if (!EMBoxFrame.waveguide) {
                    this.drawVectorsZ(0.0, drawData);
                    this.drawVectorsZ(1.0, drawData);
                }
            }
            else if (!b) {
                this.vectorSpacing /= 2;
                for (int i = 0; i != this.vectorSpacing; ++i) {
                    final double n2 = i * (1.0 / (this.vectorSpacing - 1));
                    for (int j = 0; j != this.vectorSpacing; ++j) {
                        final double n3 = j * (1.0 / (this.vectorSpacing - 1));
                        for (int k = 0; k != this.vectorSpacing * this.boxGuideMult; ++k) {
                            this.drawVector(drawData, n2, n3, k * (1.0 / (this.vectorSpacing * this.boxGuideMult - 1)));
                        }
                    }
                }
            }
            else if (selectedIndex == 1) {
                this.drawVectorsX(n, drawData);
            }
            else if (selectedIndex == 2) {
                this.drawVectorsY(n, drawData);
            }
            else if (selectedIndex == 3) {
                this.drawVectorsZ(n, drawData);
            }
        }
        else if (selectedIndex2 == 0) {
            final int n4 = this.getParticleCount() / 2;
            final int n5 = n4 * ((realfieldno == 3) ? 1 : realfieldno);
            if (!this.stoppedCheck.getState()) {
                this.moveParticles(n5, n4);
                this.allQuiet = false;
            }
            g.setColor(Color.white);
            for (int l = n5; l != n4 + n5; ++l) {
                final double[] pos = this.particles[l].pos;
                this.map3d(pos[0], pos[1], pos[2], this.xpoints, this.ypoints, 0, view);
                if (this.xpoints[0] >= 0 && this.xpoints[0] < this.winSize.width && this.ypoints[0] >= 0) {
                    if (this.ypoints[0] < this.winSize.height) {
                        g.fillRect(this.xpoints[0], this.ypoints[0] - 1, 2, 2);
                    }
                }
            }
        }
        else if (this.modeCount > 0) {
            this.computeFunction(view, realfieldno);
            for (int n6 = 0; n6 != this.gridSizeX; ++n6) {
                for (int n7 = 0; n7 != this.gridSizeY; ++n7) {
                    final int n8 = n6 * width / this.gridSizeX;
                    final int n9 = n7 * height / this.gridSizeY;
                    final int n10 = (n6 + 1) * width / this.gridSizeX;
                    final int n11 = (n7 + 1) * height / this.gridSizeY;
                    final int n12 = -16777216 + (this.getColorValue(n6, n7, 0) << 16) | this.getColorValue(n6, n7, 1) << 8 | this.getColorValue(n6, n7, 2);
                    if (state) {
                        for (int n13 = n8; n13 < n10; ++n13) {
                            for (int n14 = n9; n14 < n11; ++n14) {
                                this.pixels[n13 + n14 * width] = n12;
                            }
                        }
                    }
                    else {
                        g.setColor(new Color(n12));
                        g.fillRect(view.x + n8, view.y + n9, n10 - n8, n11 - n9);
                    }
                }
            }
            if (state) {
                g.drawImage(this.cv.createImage(this.imageSource), view.x, view.y, null);
            }
        }
        this.drawCube(g, view, false);
    }
    
    void drawVectorsX(final double n, final DrawData drawData) {
        for (int i = 0; i != this.vectorSpacing * this.boxGuideMult; ++i) {
            for (int j = 0; j != this.vectorSpacing; ++j) {
                this.drawVector(drawData, n, j * (1.0 / (this.vectorSpacing - 1)), i * (1.0 / (this.vectorSpacing * this.boxGuideMult - 1)));
            }
        }
    }
    
    void drawVectorsY(final double n, final DrawData drawData) {
        for (int i = 0; i != this.vectorSpacing; ++i) {
            for (int j = 0; j != this.vectorSpacing * this.boxGuideMult; ++j) {
                this.drawVector(drawData, i * (1.0 / (this.vectorSpacing - 1)), n, j * (1.0 / (this.vectorSpacing * this.boxGuideMult - 1)));
            }
        }
    }
    
    void drawVectorsZ(final double n, final DrawData drawData) {
        for (int i = 0; i != this.vectorSpacing; ++i) {
            for (int j = 0; j != this.vectorSpacing; ++j) {
                this.drawVector(drawData, i * (1.0 / (this.vectorSpacing - 1)), j * (1.0 / (this.vectorSpacing - 1)), n);
            }
        }
    }
    
    void drawArrow(final Graphics graphics, final String s, final int n, final int n2, final int n3, final int n4) {
        this.drawArrow(graphics, s, n, n2, n3, n4, 5);
    }
    
    void drawArrow(final Graphics graphics, final String s, final int n, final int n2, final int n3, final int n4, final int n5) {
        graphics.drawLine(n, n2, n3, n4);
        final double sqrt = Math.sqrt((n3 - n) * (n3 - n) + (n4 - n2) * (n4 - n2));
        if (sqrt > n5 / 2) {
            final double n6 = (n3 - n) / sqrt;
            final double n7 = (n4 - n2) / sqrt;
            graphics.drawLine(n3, n4, (int)(n7 * n5 - n6 * n5 + n3), (int)(-n6 * n5 - n7 * n5 + n4));
            graphics.drawLine(n3, n4, (int)(-n7 * n5 - n6 * n5 + n3), (int)(n6 * n5 - n7 * n5 + n4));
            if (s != null) {
                graphics.drawString(s, (int)(n3 + n6 * 10.0), (int)(n4 + n7 * 10.0));
            }
        }
    }
    
    void drawVector(final DrawData drawData, double n, double n2, double n3) {
        final int fieldno = drawData.fieldno;
        final Particle particle = this.particles[0];
        int n4 = (int)(n * this.maxTerms);
        int n5 = (int)(n2 * this.maxTerms);
        int n6 = (int)(n3 * this.maxTerms);
        if (n4 >= this.maxTerms) {
            n4 = this.maxTerms - 1;
        }
        if (n5 >= this.maxTerms) {
            n5 = this.maxTerms - 1;
        }
        if (n6 >= this.maxTerms) {
            n6 = this.maxTerms - 1;
        }
        double n7 = 0.0;
        double n8 = 0.0;
        double n9 = 0.0;
        for (int i = 0; i != this.modeCount; ++i) {
            final ModeData modeData = this.modes[i].modeDatas[fieldno];
            final double n10 = modeData.zmode_xymult[n6];
            final double n11 = modeData.zmode_zmult[n6];
            n7 += modeData.data[n4][n5][0] * n10;
            n8 += modeData.data[n4][n5][1] * n10;
            n9 += modeData.data[n4][n5][2] * n11;
        }
        if (drawData.realfieldno == 3) {
            if (n <= 0.01) {
                n7 = 0.0;
                final double n12 = n8;
                n8 = -n9;
                n9 = n12;
            }
            else if (n >= 0.99) {
                n7 = 0.0;
                final double n13 = n8;
                n8 = n9;
                n9 = -n13;
            }
            else if (n2 <= 0.01) {
                n8 = 0.0;
                final double n14 = n7;
                n7 = n9;
                n9 = -n14;
            }
            else if (n2 >= 0.99) {
                n8 = 0.0;
                final double n15 = n7;
                n7 = -n9;
                n9 = n15;
            }
            else if (n3 <= 0.01 && !EMBoxFrame.waveguide) {
                n9 = 0.0;
                final double n16 = n7;
                n7 = -n8;
                n8 = n16;
            }
            else if (n3 >= 0.99 && !EMBoxFrame.waveguide) {
                n9 = 0.0;
                final double n17 = n7;
                n7 = n8;
                n8 = -n17;
            }
            else {
                n8 = (n7 = (n9 = 0.0));
            }
        }
        if (n7 == 0.0) {
            n7 = 1.0E-4;
        }
        final double sqrt = Math.sqrt(n7 * n7 + n8 * n8 + n9 * n9);
        final double n18 = n7 / sqrt;
        final double n19 = n8 / sqrt;
        final double n20 = n9 / sqrt;
        double n21 = sqrt * drawData.mult;
        int n22;
        if (n21 > 1.0) {
            if (n21 > 2.0) {
                n21 = 2.0;
            }
            n22 = (0xFF00FF00 | (int)((n21 - 1.0) * 255.0) * 65537);
        }
        else {
            n22 = (0xFF000000 | (int)(n21 * 255.0) << 8);
        }
        drawData.g.setColor(new Color(n22));
        final double n23 = 1.0 / (this.vectorSpacing - 1);
        n = n * 2.0 - 1.0;
        n2 = n2 * 2.0 - 1.0;
        n3 = n3 * 2.0 - 1.0;
        this.map3d(n, n2, n3, this.xpoints, this.ypoints, 0, drawData.view);
        this.map3d(n + n23 * n18 * 2.0 / this.boxwidth, n2 + n23 * n19 * 2.0 / this.boxheight, n3 + n23 * n20 / this.boxGuideMult, this.xpoints, this.ypoints, 1, drawData.view);
        this.drawArrow(drawData.g, null, this.xpoints[0], this.ypoints[0], this.xpoints[1], this.ypoints[1], 2);
    }
    
    boolean visibleFace(final int n, final int n2, final int n3) {
        return (n - this.viewDistance * this.rotmatrix[2]) * n + (n2 - this.viewDistance * this.rotmatrix[5]) * n2 + (n3 - this.viewDistance * this.rotmatrix[8]) * n3 < 0.0;
    }
    
    void drawCube(final Graphics graphics, final Rectangle rectangle, final boolean b) {
        final int selectedIndex = this.sliceChooser.getSelectedIndex();
        int n = b ? 0 : 8;
        for (int i = 0; i != 6; ++i) {
            final int n2 = (i == 0) ? -1 : ((i == 1) ? 1 : 0);
            final int n3 = (i == 2) ? -1 : ((i == 3) ? 1 : 0);
            final int n4 = (i == 4) ? -1 : ((i == 5) ? 1 : 0);
            if (b || this.visibleFace(n2, n3, n4)) {
                final double[] array = new double[3];
                for (int j = 0; j != 4; ++j) {
                    this.computeFace(i, j, array);
                    this.map3d(array[0], array[1], array[2], this.xpoints, this.ypoints, j, rectangle);
                }
                graphics.setColor(Color.gray);
                graphics.drawPolygon(this.xpoints, this.ypoints, 4);
                if (selectedIndex != 0 && i / 2 != selectedIndex - 1) {
                    if (this.selectedSlice) {
                        graphics.setColor(Color.yellow);
                    }
                    final boolean b2 = selectedIndex == 1;
                    final int n5 = (selectedIndex == 3) ? 1 : 2;
                    this.computeFace(i, 0, array);
                    array[selectedIndex - 1] = this.sliceval;
                    this.map3d(array[0], array[1], array[2], this.slicerPoints[0], this.slicerPoints[1], n, rectangle);
                    this.computeFace(i, 2, array);
                    array[selectedIndex - 1] = this.sliceval;
                    this.map3d(array[0], array[1], array[2], this.slicerPoints[0], this.slicerPoints[1], n + 1, rectangle);
                    graphics.drawLine(this.slicerPoints[0][n], this.slicerPoints[1][n], this.slicerPoints[0][n + 1], this.slicerPoints[1][n + 1]);
                    if (b) {
                        this.sliceFaces[n / 2][0] = n2;
                        this.sliceFaces[n / 2][1] = n3;
                        this.sliceFaces[n / 2][2] = n4;
                        n += 2;
                    }
                }
            }
        }
    }
    
    void computeFace(final int n, int n2, final double[] array) {
        final int n3 = n >> 1;
        array[n3] = (((n & 0x1) == 0x0) ? -1.0 : 1.0);
        for (int i = 0; i != 3; ++i) {
            if (i != n3) {
                array[i] = (((n2 >> 1 ^ (n2 & 0x1)) == 0x0) ? -1.0 : 1.0);
                n2 >>= 1;
            }
        }
    }
    
    void map3d(double n, double n2, double n3, final int[] array, final int[] array2, final int n4, final Rectangle rectangle) {
        if (rectangle != this.viewAxes) {
            n *= this.boxwidth / 2.0;
            n2 *= this.boxheight / 2.0;
            n3 *= this.boxdepth / 2.0;
        }
        final double[] rotmatrix = this.rotmatrix;
        final double n5 = n * rotmatrix[0] + n2 * rotmatrix[3] + n3 * rotmatrix[6];
        final double n6 = n * rotmatrix[1] + n2 * rotmatrix[4] + n3 * rotmatrix[7];
        final double n7 = this.viewDistance - (n * rotmatrix[2] + n2 * rotmatrix[5] + n3 * rotmatrix[8]);
        double n8 = rectangle.width * this.zoom / 2.0;
        double n9 = rectangle.height * this.zoom / 2.0;
        final double n10 = rectangle.width / rectangle.height;
        if (n10 < 1.0) {
            n9 *= n10;
        }
        else {
            n8 /= n10;
        }
        array[n4] = rectangle.x + rectangle.width / 2 + (int)(n8 * n5 / n7);
        array2[n4] = rectangle.y + rectangle.height / 2 - (int)(n9 * n6 / n7);
    }
    
    void unmap3d(final double[] array, final int n, final int n2, final double n3, final Rectangle rectangle) {
        double n4 = rectangle.width * this.zoom / 2.0;
        double n5 = rectangle.height * this.zoom / 2.0;
        final double n6 = rectangle.width / rectangle.height;
        if (n6 < 1.0) {
            n5 *= n6;
        }
        else {
            n4 /= n6;
        }
        final double n7 = this.viewDistance - n3;
        final double n8 = (n - (rectangle.x + rectangle.width / 2)) * n7 / n4;
        final double n9 = -(n2 - (rectangle.y + rectangle.height / 2)) * n7 / n5;
        final double[] rotmatrix = this.rotmatrix;
        array[0] = (n8 * rotmatrix[0] + n9 * rotmatrix[1] + n3 * rotmatrix[2]) / (this.boxwidth / 2.0);
        array[1] = (n8 * rotmatrix[3] + n9 * rotmatrix[4] + n3 * rotmatrix[5]) / (this.boxheight / 2.0);
        array[2] = (n8 * rotmatrix[6] + n9 * rotmatrix[7] + n3 * rotmatrix[8]) / (this.boxdepth / 2.0);
    }
    
    void unmap3d(final double[] array, final int n, final int n2, final double[] array2, final double[] array3, final Rectangle rectangle) {
        double n3 = rectangle.width * this.zoom / 2.0;
        double n4 = rectangle.height * this.zoom / 2.0;
        final double n5 = rectangle.width / rectangle.height;
        if (n5 < 1.0) {
            n4 *= n5;
        }
        else {
            n3 /= n5;
        }
        final double n6 = (n - (rectangle.x + rectangle.width / 2)) / n3;
        final double n7 = -(n2 - (rectangle.y + rectangle.height / 2)) / n4;
        final double[] rotmatrix = this.rotmatrix;
        final double n8 = n6 * rotmatrix[0] + n7 * rotmatrix[1] - rotmatrix[2];
        final double n9 = n6 * rotmatrix[3] + n7 * rotmatrix[4] - rotmatrix[5];
        final double n10 = n6 * rotmatrix[6] + n7 * rotmatrix[7] - rotmatrix[8];
        final double n11 = n8 / (this.boxwidth / 2.0);
        final double n12 = n9 / (this.boxheight / 2.0);
        final double n13 = n10 / (this.boxdepth / 2.0);
        final double n14 = ((array3[0] - this.cameraPos[0]) * array2[0] + (array3[1] - this.cameraPos[1]) * array2[1] + (array3[2] - this.cameraPos[2]) * array2[2]) / (array2[0] * n11 + array2[1] * n12 + array2[2] * n13);
        array[0] = this.cameraPos[0] + n11 * n14;
        array[1] = this.cameraPos[1] + n12 * n14;
        array[2] = this.cameraPos[2] + n13 * n14;
    }
    
    void drawFrequencies(final Graphics graphics, final int n, final boolean b) {
        final Rectangle rectangle = this.viewFreq[n + (b ? 0 : this.maxZDispCoefs)];
        final int termWidth = this.getTermWidth();
        graphics.setColor(Color.white);
        if (!this.legalMode(1, 1, n, b)) {
            return;
        }
        int i;
        int n2;
        for (n2 = (i = (this.legalMode(1, 0, n, b) ? 0 : 1)); i <= this.maxDispCoefs; ++i) {
            final int n3 = i * termWidth;
            final int n4 = (i == 0) ? 1 : n2;
            graphics.drawLine(rectangle.x + n4 * termWidth, n3 + rectangle.y, rectangle.x + termWidth * this.maxDispCoefs, n3 + rectangle.y);
            graphics.drawLine(rectangle.x + n3, rectangle.y + n4 * termWidth, rectangle.x + n3, rectangle.y + termWidth * this.maxDispCoefs);
        }
        final int n5 = 65536;
        final int n6 = 256;
        for (int j = 0; j != this.modeCount; ++j) {
            final Mode mode = this.modes[j];
            if (mode.z == n) {
                if (mode.teMode == b) {
                    final int n7 = rectangle.x + mode.x * termWidth;
                    final int n8 = rectangle.y + mode.y * termWidth;
                    int logcoef = this.logcoef(mode.magcoef);
                    if (logcoef < -255) {
                        logcoef = -255;
                    }
                    if (logcoef > 255) {
                        logcoef = 255;
                    }
                    if (logcoef < 0) {
                        graphics.setColor(new Color(-16777216 + n5 * -logcoef));
                    }
                    else {
                        graphics.setColor(new Color(-16777216 + n6 * logcoef));
                    }
                    graphics.fillRect(n7 + 1, n8 + 1, termWidth - 1, termWidth - 1);
                    final int n9 = (int)(mode.phasecoefadj * termWidth * 0.15915494309189535);
                    if (n9 > 0) {
                        graphics.setColor(Color.blue);
                        graphics.drawLine(n7 + n9, n8 + 1, n7 + n9, n8 + termWidth);
                    }
                }
            }
        }
        if (EMBoxFrame.waveguide) {
            for (int k = 0; k != this.maxDispCoefs; ++k) {
                for (int l = 0; l != this.maxDispCoefs; ++l) {
                    final int n10 = rectangle.x + k * termWidth;
                    final int n11 = rectangle.y + l * termWidth;
                    if (this.basicLegalMode(k, l, n, b)) {
                        if (!this.legalMode(k, l, n, b)) {
                            graphics.setColor(Color.white);
                            graphics.drawLine(n10, n11 + termWidth, n10 + termWidth, n11);
                        }
                    }
                }
            }
        }
        if (this.selectedCoefX != -1 && !EMBoxFrame.waveguide) {
            final double omega = this.getOmega(this.selectedCoefX, this.selectedCoefY, this.selectedCoefZ);
            graphics.setColor(Color.yellow);
            for (int n12 = n2; n12 != this.maxDispCoefs; ++n12) {
                for (int n13 = n2; n13 != this.maxDispCoefs; ++n13) {
                    final int n14 = rectangle.x + n12 * termWidth;
                    final int n15 = rectangle.y + n13 * termWidth;
                    if (this.getOmega(n12, n13, n) == omega) {
                        graphics.drawRect(n14, n15, termWidth, termWidth);
                    }
                }
            }
        }
        if (this.selectedMinOmega > 0.0 && this.selectedMaxOmega > 0.0) {
            graphics.setColor(Color.yellow);
            for (int n16 = n2; n16 != this.maxDispCoefs; ++n16) {
                for (int n17 = n2; n17 != this.maxDispCoefs; ++n17) {
                    final int n18 = rectangle.x + n16 * termWidth;
                    final int n19 = rectangle.y + n17 * termWidth;
                    final double omega2 = this.getOmega(n16, n17, n);
                    if (omega2 >= this.selectedMinOmega && omega2 < this.selectedMaxOmega) {
                        graphics.drawRect(n18, n19, termWidth, termWidth);
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
    
    void moveParticles(final int n, final int n2) {
        int n3 = 0;
        final int selectedIndex = this.sliceChooser.getSelectedIndex();
        final boolean b = selectedIndex > 0;
        for (int i = n; i != n2 + n; ++i) {
            final Particle particle = this.particles[i];
            this.removeFromDensityGroup(particle);
            this.moveParticle(particle);
            final double[] pos = particle.pos;
            if (pos[0] < -1.0 || pos[0] > 1.0 || pos[1] < -1.0 || pos[1] > 1.0 || pos[2] < -1.0 || pos[2] > 1.0) {
                if (!this.sidesCheck.getState()) {
                    particle.lifetime = -1;
                }
                else {
                    int side = -1;
                    for (int j = 0; j != 3; ++j) {
                        if (pos[j] < -1.0) {
                            side = j * 2 + 1;
                        }
                        else if (pos[j] > 1.0) {
                            side = j * 2;
                        }
                    }
                    if (side == particle.side || (EMBoxFrame.waveguide && side >= 4)) {
                        particle.lifetime = -1;
                    }
                    else {
                        particle.side = side;
                        particle.pos[particle.side / 2] = this.sidemap[particle.side][particle.side / 2];
                    }
                }
            }
            if (particle.lifetime-- < 0) {
                this.positionParticle(particle);
            }
            if (b) {
                pos[selectedIndex - 1] = this.sliceval;
            }
            final int addToDensityGroup = this.addToDensityGroup(particle);
            if (addToDensityGroup > n3) {
                n3 = addToDensityGroup;
            }
        }
        int n4 = 4 * this.getParticleCount() / 64;
        if (b) {
            n4 = 2 * this.getParticleCount() / 16;
        }
        if (n3 > n4) {
            this.redistribute(n3);
        }
    }
    
    void redistribute(final int n) {
        if (n < 5) {
            return;
        }
        ++this.rediscount;
        final int n2 = 4 * this.getParticleCount() / 64;
        int n3 = 0;
        for (int particleCount = this.getParticleCount(), i = this.rediscount % 4; i < particleCount; i += 4) {
            final Particle particle = this.particles[i];
            if (this.density[(int)((particle.pos[0] + 1.0) / 0.505)][(int)((particle.pos[1] + 1.0) / 0.505)][(int)((particle.pos[2] + 1.0) / 0.505)] > n2) {
                particle.lifetime = -1;
                ++n3;
            }
        }
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
        if (actionEvent.getSource() == this.resetPartButton) {
            this.resetParticles();
            this.cv.repaint();
        }
    }
    
    public void adjustmentValueChanged(final AdjustmentEvent adjustmentEvent) {
        System.out.print(((Scrollbar)adjustmentEvent.getSource()).getValue() + "\n");
        if (adjustmentEvent.getSource() == this.widthBar || adjustmentEvent.getSource() == this.heightBar) {
            this.setWidthHeight();
        }
        if (adjustmentEvent.getSource() == this.freqBar) {
            this.setFrequency();
        }
        if (adjustmentEvent.getSource() == this.resolutionBar) {
            this.setMaxTerms();
        }
        if (adjustmentEvent.getSource() == this.partCountBar) {
            this.resetDensityGroups();
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
        this.setFrequency();
    }
    
    void setFrequency() {
        for (int i = 0; i != this.modeCount; ++i) {
            final Mode mode = this.modes[i];
            mode.zwavenum = this.getWaveNum(mode.x, mode.y);
            if (mode.zwavenum <= 0.0) {
                this.deleteMode(i--);
            }
        }
        this.calcSpectrum();
    }
    
    void calcSpectrum() {
        if (this.winSize == null) {
            return;
        }
        this.spectrum = new int[this.winSize.width];
        for (int i = 0; i != this.maxDispCoefs; ++i) {
            for (int j = 0; j != this.maxDispCoefs; ++j) {
                for (int k = 0; k != this.maxDispCoefs; ++k) {
                    if (this.legalMode(i, j, k, true) || this.legalMode(i, j, k, false)) {
                        final int n = (int)(this.getOmega(i, j, k) * 60.0);
                        if (n < this.winSize.width) {
                            final int[] spectrum = this.spectrum;
                            final int n2 = n;
                            ++spectrum[n2];
                        }
                    }
                }
            }
        }
    }
    
    boolean csInRange(final int n, final int n2, final int n3) {
        if (n2 < n3) {
            return n >= n2 - 5 && n <= n3 + 5;
        }
        return n >= n3 - 5 && n <= n2 + 5;
    }
    
    void checkSlice(int n, final int n2) {
        if (this.sliceChooser.getSelectedIndex() == 0) {
            this.selectedSlice = false;
            return;
        }
        this.selectedSlice = false;
        if (this.emChooser.getSelectedIndex() == 2) {
            n -= (this.view3d_e.inside(n, n2) ? 0 : this.view3d_b.x);
        }
        for (int i = 0; i != 8; i += 2) {
            final int n3 = this.slicerPoints[0][i];
            final int n4 = this.slicerPoints[0][i + 1];
            final int n5 = this.slicerPoints[1][i];
            final int n6 = this.slicerPoints[1][i + 1];
            if (this.csInRange(n, n3, n4)) {
                if (this.csInRange(n2, n5, n6)) {
                    double n7;
                    if (n3 == n4) {
                        n7 = Math.abs(n - n3);
                    }
                    else {
                        final double n8 = (n6 - n5) / (n4 - n3);
                        double n9 = n2 - (n5 - n8 * n3 + n8 * n);
                        if (n9 < 0.0) {
                            n9 = -n9;
                        }
                        n7 = n9 / Math.sqrt(1.0 + n8 * n8);
                    }
                    if (n7 < 6.0) {
                        this.selectedSlice = true;
                        this.sliceFace = this.sliceFaces[i / 2];
                        break;
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
        final boolean selectedSlice = this.selectedSlice;
        this.checkSlice(this.dragX, this.dragY);
        if (selectedSlice != this.selectedSlice) {
            this.cv.repaint(this.pause);
        }
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
            this.selectedMinOmega = (x - 2) / 60.0;
            this.selectedMaxOmega = (x + 2) / 60.0;
        }
        for (int i = 0; i != this.maxZDispCoefs * 2; ++i) {
            final Rectangle rectangle = this.viewFreq[i];
            if (rectangle.inside(x, y)) {
                final int termWidth = this.getTermWidth();
                this.selectedCoefX = (x - rectangle.x) / termWidth;
                this.selectedCoefY = (y - rectangle.y) / termWidth;
                this.selectedCoefZ = i % this.maxZDispCoefs;
                this.selectedCoefTEMode = (i < this.maxZDispCoefs);
                if (this.selectedCoefX >= this.maxDispCoefs) {
                    this.selectedCoefX = -1;
                }
                if (this.selectedCoefY >= this.maxDispCoefs) {
                    this.selectedCoefX = -1;
                }
                if (this.selectedCoefX != -1) {
                    this.selection = 2;
                }
            }
        }
        if (!this.legalMode(this.selectedCoefX, this.selectedCoefY, this.selectedCoefZ, this.selectedCoefTEMode)) {
            final int selectedCoefX2 = -1;
            this.selectedCoefZ = selectedCoefX2;
            this.selectedCoefY = selectedCoefX2;
            this.selectedCoefX = selectedCoefX2;
        }
        if (this.selectedCoefX != selectedCoefX || this.selectedCoefY != selectedCoefY || this.selectedCoefZ != selectedCoefZ) {
            this.cv.repaint(this.pause);
        }
    }
    
    boolean legalMode(final int n, final int n2, final int n3, final boolean b) {
        if (EMBoxFrame.waveguide) {
            if (n3 != 1) {
                return false;
            }
            if (this.getWaveNum(n, n2) <= 0.0) {
                return false;
            }
        }
        return this.basicLegalMode(n, n2, n3, b);
    }
    
    boolean basicLegalMode(final int n, final int n2, final int n3, final boolean b) {
        if (b) {
            return n3 != 0 && (n != 0 || n2 != 0);
        }
        return n != 0 && n2 != 0;
    }
    
    public void mouseClicked(final MouseEvent mouseEvent) {
        if (this.selection == 2) {
            this.editMagClick();
        }
        if (mouseEvent.getClickCount() == 2 && this.selectedCoefX != -1) {
            while (this.modeCount > 0) {
                this.deleteMode(0);
            }
            this.addMode(this.selectedCoefX, this.selectedCoefY, this.selectedCoefZ, this.selectedCoefTEMode).magcoef = 1.0;
            this.cv.repaint(this.pause);
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
        if (itemEvent.getItemSelectable() == this.dispChooser) {
            this.setDynamicControls();
        }
        if (itemEvent.getItemSelectable() == this.sliceChooser) {
            this.setDynamicControls();
        }
        if (itemEvent.getItemSelectable() == this.emChooser) {
            this.reinit();
        }
        this.cv.repaint(this.pause);
    }
    
    void setDynamicControls() {
        final int selectedIndex = this.emChooser.getSelectedIndex();
        int selectedIndex2 = this.dispChooser.getSelectedIndex();
        if (selectedIndex == 4) {
            this.sliceChooser.select(0);
            this.sliceChooser.disable();
            this.dispChooser.select(1);
            this.dispChooser.disable();
        }
        else {
            if (selectedIndex == 3 && selectedIndex2 != 6) {
                this.sliceChooser.disable();
                this.sliceChooser.select(0);
            }
            else {
                this.sliceChooser.enable();
            }
            this.dispChooser.enable();
        }
        if (selectedIndex2 != 0 && selectedIndex2 != 6 && this.sliceChooser.getSelectedIndex() == 0) {
            selectedIndex2 = 1;
        }
        if (selectedIndex == 4 || (selectedIndex == 3 && selectedIndex2 != 6)) {
            this.sidesCheck.disable();
            this.sidesCheck.setState(true);
        }
        else if (this.sliceChooser.getSelectedIndex() == 0 && (selectedIndex2 != 0 || selectedIndex == 3)) {
            this.sidesCheck.enable();
            this.sidesCheck.setState(selectedIndex == 3);
        }
        else {
            this.sidesCheck.disable();
            this.sidesCheck.setState(false);
        }
        final int n = 1 << selectedIndex2;
        for (int n2 = 0; this.dynControls[n2] != null; ++n2) {
            final DynControl dynControl = this.dynControls[n2];
            if ((dynControl.flags & n) > 0) {
                dynControl.bar.show();
                dynControl.label.show();
            }
            else {
                dynControl.bar.hide();
                dynControl.label.hide();
            }
        }
        if (this.dispChooser.getSelectedIndex() == 0) {
            this.resetPartButton.enable();
            this.stopOscCheck.enable();
        }
        else {
            this.resetPartButton.disable();
            this.stopOscCheck.disable();
            this.stopOscCheck.setState(false);
        }
        if (this.dispChooser.getSelectedIndex() == 0) {
            this.resetParticles();
        }
        this.validate();
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
        if (this.selectedSlice) {
            final double[] array = new double[3];
            Rectangle view3d = this.view3d;
            if (this.emChooser.getSelectedIndex() == 2) {
                view3d = (this.view3d_e.inside(n, n2) ? this.view3d_e : this.view3d_b);
            }
            this.unmap3d(array, this.dragX, this.dragY, this.sliceFace, this.sliceFace, view3d);
            switch (this.sliceChooser.getSelectedIndex()) {
                case 1: {
                    this.sliceval = array[0];
                    break;
                }
                case 2: {
                    this.sliceval = array[1];
                    break;
                }
                case 3: {
                    this.sliceval = array[2];
                    break;
                }
            }
            if (this.sliceval < -0.99) {
                this.sliceval = -0.99;
            }
            if (this.sliceval > 0.99) {
                this.sliceval = 0.99;
            }
            this.resetDensityGroups();
            this.cv.repaint(this.pause);
        }
        else if (this.modeChooser.getSelectedIndex() == 0) {
            this.rotate((this.oldDragX - n) / 40.0, -(this.oldDragY - n2) / 40.0);
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
    
    void genData(final int n) {
        final double n2 = 3.141592653589793 / (this.maxTerms - 1);
        final boolean b = n == 0;
        for (int i = 0; i != this.modeCount; ++i) {
            final Mode mode = this.modes[i];
            final ModeData modeData = mode.modeDatas[n];
            if (modeData.data == null) {
                modeData.zmode_xymult = new float[this.maxTerms];
                modeData.zmode_zmult = new float[this.maxTerms];
            }
            this.calcModeMults(mode, false);
            final double n3 = EMBoxFrame.waveguide ? (2.0 * n2 * mode.zwavenum) : n2;
            final double n4 = EMBoxFrame.waveguide ? 1.5707963267948966 : 0.0;
            for (int j = 0; j != this.maxTerms; ++j) {
                if (b) {
                    modeData.zmode_xymult[j] = (float)(Math.sin(j * n3 * mode.z + mode.ephaseshift) * mode.ephasemult);
                    modeData.zmode_zmult[j] = (float)(Math.cos(j * n3 * mode.z + mode.ephaseshift) * mode.ephasemult);
                }
                else {
                    modeData.zmode_xymult[j] = (float)(Math.cos(j * n3 * mode.z + mode.bphaseshift - n4) * mode.bphasemult);
                    modeData.zmode_zmult[j] = (float)(Math.sin(j * n3 * mode.z + mode.bphaseshift + n4) * mode.bphasemult);
                }
            }
            if (modeData.data == null) {
                modeData.data = new float[this.maxTerms][this.maxTerms][3];
                for (int k = 0; k != this.maxTerms; ++k) {
                    for (int l = 0; l != this.maxTerms; ++l) {
                        double n5;
                        double n6;
                        double n7;
                        if (b) {
                            n5 = Math.cos(k * mode.x * n2) * Math.sin(l * mode.y * n2) * mode.exmult;
                            n6 = Math.sin(k * mode.x * n2) * Math.cos(l * mode.y * n2) * mode.eymult;
                            n7 = Math.sin(k * mode.x * n2) * Math.sin(l * mode.y * n2) * mode.ezmult;
                        }
                        else {
                            n5 = Math.sin(k * mode.x * n2) * Math.cos(l * mode.y * n2) * mode.bxmult;
                            n6 = Math.cos(k * mode.x * n2) * Math.sin(l * mode.y * n2) * mode.bymult;
                            n7 = Math.cos(k * mode.x * n2) * Math.cos(l * mode.y * n2) * mode.bzmult;
                        }
                        modeData.data[k][l][0] = (float)n5;
                        modeData.data[k][l][1] = (float)n6;
                        modeData.data[k][l][2] = (float)n7;
                    }
                }
            }
        }
    }
    
    void calcModeMults(final Mode mode, final boolean b) {
        final double n = mode.x / this.boxwidth;
        final double n2 = mode.y / this.boxheight;
        final double n3 = EMBoxFrame.waveguide ? mode.zwavenum : (mode.z / this.boxdepth);
        final double n4 = EMBoxFrame.waveguide ? -1.0 : 1.0;
        if (mode.teMode) {
            this.calcMults(mode, b ? mode.ephasemult : 1.0, n2, -n, 0.0, b ? mode.bphasemult : 1.0, n * n3, n2 * n3, -n4 * (n * n + n2 * n2));
        }
        else {
            this.calcMults(mode, b ? mode.ephasemult : 1.0, n * n3, n2 * n3, -(n * n + n2 * n2), b ? mode.bphasemult : 1.0, n2 * n4, -n4 * n, 0.0);
        }
    }
    
    void calcMults(final Mode mode, final double n, final double n2, final double n3, final double n4, final double n5, final double n6, final double n7, final double n8) {
        final double n9 = n / Math.sqrt(n2 * n2 + n3 * n3 + n4 * n4);
        mode.exmult = n2 * n9;
        mode.eymult = n3 * n9;
        mode.ezmult = n4 * n9;
        final double n10 = n5 / Math.sqrt(n6 * n6 + n7 * n7 + n8 * n8);
        mode.bxmult = n6 * n10;
        mode.bymult = n7 * n10;
        mode.bzmult = n8 * n10;
    }
    
    void calcField(final Particle particle, final double[] array, final double[] array2) {
        final double n = 1.5707963267948966;
        final double n2 = array2[0] + 1.0;
        final double n3 = array2[1] + 1.0;
        final double n4 = array2[2] + 1.0;
        final int n5 = 0;
        final int n6 = 1;
        final int n7 = 2;
        final double n8 = 0.0;
        array[n7] = n8;
        array[n5] = (array[n6] = n8);
        final boolean b = this.curfieldno == 0;
        final double n9 = EMBoxFrame.waveguide ? 1.5707963267948966 : 0.0;
        for (int i = 0; i != this.modeCount; ++i) {
            final Mode mode = this.modes[i];
            final double n10 = EMBoxFrame.waveguide ? (2.0 * n * mode.zwavenum) : n;
            if (b) {
                final int n11 = 0;
                array[n11] += mode.exmult * Math.cos(n2 * mode.x * n) * Math.sin(n3 * mode.y * n) * Math.sin(n4 * mode.z * n10 + mode.ephaseshift);
                final int n12 = 1;
                array[n12] += mode.eymult * Math.sin(n2 * mode.x * n) * Math.cos(n3 * mode.y * n) * Math.sin(n4 * mode.z * n10 + mode.ephaseshift);
                final int n13 = 2;
                array[n13] += mode.ezmult * Math.sin(n2 * mode.x * n) * Math.sin(n3 * mode.y * n) * Math.cos(n4 * mode.z * n10 + mode.ephaseshift);
            }
            else {
                final int n14 = 0;
                array[n14] += mode.bxmult * Math.sin(n2 * mode.x * n) * Math.cos(n3 * mode.y * n) * Math.cos(n4 * mode.z * n10 + mode.bphaseshift - n9);
                final int n15 = 1;
                array[n15] += mode.bymult * Math.cos(n2 * mode.x * n) * Math.sin(n3 * mode.y * n) * Math.cos(n4 * mode.z * n10 + mode.bphaseshift - n9);
                final int n16 = 2;
                array[n16] += mode.bzmult * Math.cos(n2 * mode.x * n) * Math.cos(n3 * mode.y * n) * Math.sin(n4 * mode.z * n10 + mode.bphaseshift + n9);
            }
        }
        if (this.curfieldno == 3) {
            switch (particle.side) {
                case 0: {
                    array[0] = 0.0;
                    final double n17 = array[1];
                    array[1] = array[2];
                    array[2] = -n17;
                    break;
                }
                case 1: {
                    array[0] = 0.0;
                    final double n18 = array[1];
                    array[1] = -array[2];
                    array[2] = n18;
                    break;
                }
                case 2: {
                    array[1] = 0.0;
                    final double n19 = array[0];
                    array[0] = -array[2];
                    array[2] = n19;
                    break;
                }
                case 3: {
                    array[1] = 0.0;
                    final double n20 = array[0];
                    array[0] = array[2];
                    array[2] = -n20;
                    break;
                }
                case 4: {
                    array[2] = 0.0;
                    final double n21 = array[0];
                    array[0] = array[1];
                    array[1] = -n21;
                    break;
                }
                case 5: {
                    array[2] = 0.0;
                    final double n22 = array[0];
                    array[0] = -array[1];
                    array[1] = n22;
                    break;
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
    
    Mode addMode(final int x, final int y, final int z, final boolean teMode) {
        if (this.modeCount == this.maxModes) {
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
        mode2.teMode = teMode;
        mode2.magcoef = 0.0;
        mode2.phasecoef = 0.0;
        mode2.phasecoefadj = 0.0;
        mode2.omega = this.getOmega(x, y, z);
        mode2.zwavenum = this.getWaveNum(x, y);
        (mode2.modeDatas = new ModeData[2])[0] = new ModeData();
        mode2.modeDatas[1] = new ModeData();
        return this.modes[this.modeCount++] = mode2;
    }
    
    double getWaveNum(final int n, final int n2) {
        if (!EMBoxFrame.waveguide) {
            return 1.0;
        }
        return Math.sqrt(this.freqBar.getValue() * 0.2 - (n * n / (this.boxwidth * this.boxwidth) + n2 * n2 / (this.boxheight * this.boxheight)));
    }
    
    double getOmega(final int n, final int n2, final int n3) {
        if (EMBoxFrame.waveguide) {
            return 1.0;
        }
        return Math.sqrt(n * n / (this.boxwidth * this.boxwidth) + n2 * n2 / (this.boxheight * this.boxheight) + n3 * n3 / 4.0);
    }
    
    Mode findSelectedMode() {
        for (int i = 0; i != this.modeCount; ++i) {
            final Mode mode = this.modes[i];
            if (this.selectedCoefX == mode.x && this.selectedCoefY == mode.y && this.selectedCoefZ == mode.z && this.selectedCoefTEMode == mode.teMode) {
                return mode;
            }
        }
        return this.addMode(this.selectedCoefX, this.selectedCoefY, this.selectedCoefZ, this.selectedCoefTEMode);
    }
    
    void rk(final Particle particle, final int n, final double n2, final double[] array, final double n3) {
        if (n == 3) {
            final double n4 = n3 * 0.0016 * this.partSpeedBar.getValue();
            for (int i = 0; i != n; ++i) {
                this.rk_yn[i] = array[i];
            }
            this.calcField(particle, this.rk_k1, this.rk_yn);
            for (int j = 0; j != n; ++j) {
                this.rk_yn[j] = array[j] + 0.5 * n4 * this.rk_k1[j];
            }
            this.calcField(particle, this.rk_k2, this.rk_yn);
            for (int k = 0; k != n; ++k) {
                this.rk_yn[k] = array[k] + 0.5 * n4 * this.rk_k2[k];
            }
            this.calcField(particle, this.rk_k3, this.rk_yn);
            for (int l = 0; l != n; ++l) {
                this.rk_yn[l] = array[l] + n4 * this.rk_k3[l];
            }
            this.calcField(particle, this.rk_k4, this.rk_yn);
            for (int n5 = 0; n5 != n; ++n5) {
                array[n5] += n4 * (this.rk_k1[n5] + 2.0 * (this.rk_k2[n5] + this.rk_k3[n5]) + this.rk_k4[n5]) / 6.0;
            }
        }
    }
    
    void moveParticle(final Particle particle) {
        this.dispChooser.getSelectedIndex();
        final double n = 1.0;
        final double n2 = 0.001;
        final int n3 = 3;
        final double[] rk_Y = this.rk_Y;
        final double[] rk_Yhalf = this.rk_Yhalf;
        this.oldY = this.rk_oldY;
        for (int i = 0; i != 3; ++i) {
            final double[] oldY = this.oldY;
            final int n4 = i;
            final double[] array = rk_Y;
            final int n5 = i;
            final double[] array2 = rk_Yhalf;
            final int n6 = i;
            final double n7 = particle.pos[i];
            array2[n6] = n7;
            oldY[n4] = (array[n5] = n7);
        }
        double n8 = 0.0;
        double stepsize = particle.stepsize;
        int n9 = 0;
        final double n10 = 1.0E-4;
        while (n8 >= 0.0 && n8 < 1.0) {
            if (n8 + stepsize > 1.0) {
                stepsize = 1.0 - n8;
            }
            this.boundCheck = false;
            this.rk(particle, n3, 0.0, rk_Y, stepsize);
            this.rk(particle, n3, 0.0, rk_Yhalf, stepsize * 0.5);
            this.rk(particle, n3, 0.0, rk_Yhalf, stepsize * 0.5);
            if (this.boundCheck) {
                particle.pos[0] = -100.0;
                return;
            }
            final double n11 = Math.abs(rk_Y[0] - rk_Yhalf[0]) + Math.abs(rk_Y[1] - rk_Yhalf[1]) + Math.abs(rk_Y[2] - rk_Yhalf[2]);
            if (n11 > n2 && stepsize > n10) {
                stepsize *= 0.75;
                if (stepsize < n10) {
                    stepsize = n10;
                }
                for (int j = 0; j != n3; ++j) {
                    rk_Y[j] = (rk_Yhalf[j] = this.oldY[j]);
                }
            }
            else {
                if (n11 < n2 * 0.5) {
                    stepsize *= 1.25;
                    if (stepsize > n) {
                        stepsize = n;
                    }
                }
                for (int k = 0; k != n3; ++k) {
                    this.oldY[k] = (rk_Yhalf[k] = rk_Y[k]);
                }
                n8 += stepsize;
                ++n9;
            }
        }
        particle.stepsize = stepsize;
        for (int l = 0; l != 3; ++l) {
            particle.pos[l] = rk_Y[l];
        }
    }
    
    class DynControl
    {
        public Scrollbar bar;
        public Label label;
        public int flags;
        
        DynControl(final Scrollbar bar, final Label label, final int n) {
            this.bar = bar;
            this.label = label;
            this.flags = 1 << n;
        }
        
        DynControl(final Scrollbar bar, final Label label, final int n, final int n2) {
            this.bar = bar;
            this.label = label;
            this.flags = (1 << n | 1 << n2);
        }
    }
    
    class Mode
    {
        public int x;
        public int y;
        public int z;
        public boolean teMode;
        public double magcoef;
        public double phasecoef;
        public double ephasemult;
        public double bphasemult;
        public double phasecoefadj;
        public double omega;
        public double ephaseshift;
        public double bphaseshift;
        public double zwavenum;
        public double exmult;
        public double eymult;
        public double ezmult;
        public double bxmult;
        public double bymult;
        public double bzmult;
        public ModeData[] modeDatas;
    }
    
    class ModeData
    {
        public float[][][] data;
        public float[] zmode_xymult;
        public float[] zmode_zmult;
    }
    
    class DrawData
    {
        public Graphics g;
        public double mult;
        public Rectangle view;
        public int fieldno;
        public int realfieldno;
    }
    
    class Particle
    {
        public double[] pos;
        public double stepsize;
        public int lifetime;
        public int side;
        
        Particle() {
            this.pos = new double[3];
            this.stepsize = 1.0;
        }
    }
}
