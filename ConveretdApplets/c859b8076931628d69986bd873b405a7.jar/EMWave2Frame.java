import java.awt.event.ItemEvent;
import java.awt.event.AdjustmentEvent;
import java.awt.event.ActionEvent;
import java.awt.event.ComponentEvent;
import java.awt.event.MouseEvent;
import java.awt.image.ImageObserver;
import java.awt.Graphics;
import java.awt.Event;
import java.awt.image.ImageProducer;
import java.awt.Color;
import java.awt.Component;
import java.awt.LayoutManager;
import java.awt.image.MemoryImageSource;
import java.awt.Label;
import java.awt.Scrollbar;
import java.util.Vector;
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

class EMWave2Frame extends Frame implements ComponentListener, ActionListener, AdjustmentListener, MouseMotionListener, MouseListener, ItemListener
{
    Thread engine;
    Dimension winSize;
    Image dbimage;
    Random random;
    int gridSizeX;
    int gridSizeY;
    int gridSizeXY;
    int windowWidth;
    int windowHeight;
    int windowOffsetX;
    int windowOffsetY;
    public static final int sourceRadius = 7;
    public static final double freqMult = 0.01166665;
    Button clearButton;
    Button ClearAllButton;
    Checkbox stoppedCheck;
    Choice modeChooser;
    Choice viewChooser;
    Choice sourceChooser;
    Choice setupChooser;
    Vector setupList;
    Setup setup;
    Scrollbar speedBar;
    Scrollbar forceBar;
    Scrollbar resBar;
    Scrollbar brightnessBar;
    Scrollbar lineDensityBar;
    Scrollbar auxBar;
    Scrollbar adjustBar;
    Label auxLabel;
    Label adjustLabel;
    double forceTimeZero;
    double sourceMult;
    static final double pi = 3.141592653589793;
    OscElement[] grid;
    int gw;
    OscSource[] sources;
    static final int MODE_PERF_CONDUCTOR = 0;
    static final int MODE_GOOD_CONDUCTOR = 1;
    static final int MODE_FAIR_CONDUCTOR = 2;
    static final int MODE_J_POS = 3;
    static final int MODE_J_NEG = 4;
    static final int MODE_FERROMAG = 5;
    static final int MODE_DIAMAG = 6;
    static final int MODE_MEDIUM = 7;
    static final int MODE_M_DOWN = 8;
    static final int MODE_M_UP = 9;
    static final int MODE_M_LEFT = 10;
    static final int MODE_M_RIGHT = 11;
    static final int MODE_RESONANT = 12;
    static final int MODE_CLEAR = 13;
    static final int MODE_ADJUST = 14;
    static final int MODE_ADJ_CONDUCT = 14;
    static final int MODE_ADJ_PERM = 15;
    static final int MODE_ADJ_J = 16;
    static final int MODE_ADJ_MEDIUM = 17;
    static final int MODE_ADJ_MAG_DIR = 18;
    static final int MODE_ADJ_MAG_STR = 19;
    static final int VIEW_E = 0;
    static final int VIEW_B = 1;
    static final int VIEW_B_LINES = 2;
    static final int VIEW_B_STRENGTH = 3;
    static final int VIEW_J = 4;
    static final int VIEW_E_B = 5;
    static final int VIEW_E_B_LINES = 6;
    static final int VIEW_E_B_J = 7;
    static final int VIEW_E_B_LINES_J = 8;
    static final int VIEW_H = 9;
    static final int VIEW_M = 10;
    static final int VIEW_TYPE = 11;
    static final int VIEW_A = 12;
    static final int VIEW_POYNTING = 13;
    static final int VIEW_ENERGY = 14;
    static final int VIEW_POYNTING_ENERGY = 15;
    static final int VIEW_FORCE = 16;
    static final int VIEW_EFF_CUR = 17;
    static final int VIEW_MAG_CHARGE = 18;
    static final int VIEW_CURL_E = 19;
    static final int VIEW_BX = 20;
    static final int VIEW_BY = 21;
    static final int VIEW_HX = 22;
    static final int VIEW_HY = 23;
    static final int VIEW_NONE = -1;
    static final int TYPE_CONDUCTOR = 1;
    static final int TYPE_DIAMAGNET = 2;
    static final int TYPE_FERROMAGNET = 3;
    static final int TYPE_MAGNET = 4;
    static final int TYPE_CURRENT = 5;
    static final int TYPE_MEDIUM = 6;
    static final int TYPE_NONE = 0;
    static final double mhmult = 12.0;
    int dragX;
    int dragY;
    int selectedSource;
    int forceBarValue;
    boolean dragging;
    boolean dragClear;
    boolean dragSet;
    double t;
    int pause;
    MemoryImageSource imageSource;
    int[] pixels;
    int sourceCount;
    boolean sourcePlane;
    int sourceFreqCount;
    int sourceWaveform;
    int auxFunction;
    int adjustSelectX1;
    int adjustSelectY1;
    int adjustSelectX2;
    int adjustSelectY2;
    static final int mediumMax = 191;
    static final double mediumMaxIndex = 0.5;
    static final int SWF_SIN = 0;
    static final int SWF_PACKET = 1;
    static final int AUX_NONE = 0;
    static final int AUX_PHASE = 1;
    static final int AUX_FREQ = 2;
    static final int AUX_SPEED = 3;
    static final int SRC_NONE = 0;
    static final int SRC_1S1F = 1;
    static final int SRC_2S1F = 3;
    static final int SRC_2S2F = 4;
    static final int SRC_4S1F = 6;
    static final int SRC_1S1F_PACKET = 7;
    static final int SRC_1S1F_PLANE = 8;
    static final int SRC_2S1F_PLANE = 10;
    static final int SRC_1S1F_PLANE_PACKET = 12;
    EMWave2Canvas cv;
    EMWave2 applet;
    boolean useBufferedImage;
    long lastTime;
    byte[] linegrid;
    byte[][] forceMap;
    double[][] forceVecs;
    int filterCount;
    
    public String getAppletInfo() {
        return "EMWave2 by Paul Falstad";
    }
    
    int getrand(final int n) {
        int nextInt = this.random.nextInt();
        if (nextInt < 0) {
            nextInt = -nextInt;
        }
        return nextInt % n;
    }
    
    EMWave2Frame(final EMWave2 applet) {
        super("TM Electrodynamics Applet v1.4a");
        this.engine = null;
        this.windowWidth = 50;
        this.windowHeight = 50;
        this.windowOffsetX = 0;
        this.windowOffsetY = 0;
        this.sourceCount = -1;
        this.sourcePlane = false;
        this.sourceFreqCount = -1;
        this.sourceWaveform = 0;
        this.useBufferedImage = false;
        this.lastTime = 0L;
        this.filterCount = 0;
        this.applet = applet;
    }
    
    public void init() {
        if (new Double(System.getProperty("java.class.version")) >= 48.0) {
            this.useBufferedImage = true;
        }
        this.setupList = new Vector();
        Setup next = new SingleSourceSetup();
        int n = 0;
        while (next != null) {
            this.setupList.addElement(next);
            next = next.createNext();
            if (n++ > 300) {
                System.out.print("setup loop?\n");
                return;
            }
        }
        System.getProperty("os.name");
        this.sources = new OscSource[4];
        this.setLayout(new EMWave2Layout());
        (this.cv = new EMWave2Canvas(this)).addComponentListener(this);
        this.cv.addMouseMotionListener(this);
        this.cv.addMouseListener(this);
        this.add(this.cv);
        this.setupChooser = new Choice();
        for (int i = 0; i != this.setupList.size(); ++i) {
            this.setupChooser.add("Setup: " + ((Setup)this.setupList.elementAt(i)).getName());
        }
        this.setup = this.setupList.elementAt(0);
        this.setupChooser.addItemListener(this);
        this.add(this.setupChooser);
        (this.sourceChooser = new Choice()).add("No Sources");
        this.sourceChooser.add("1 Src, 1 Freq");
        this.sourceChooser.add("1 Src, 2 Freq");
        this.sourceChooser.add("2 Src, 1 Freq");
        this.sourceChooser.add("2 Src, 2 Freq");
        this.sourceChooser.add("3 Src, 1 Freq");
        this.sourceChooser.add("4 Src, 1 Freq");
        this.sourceChooser.add("1 Src, 1 Freq (Packet)");
        this.sourceChooser.add("1 Plane Src, 1 Freq");
        this.sourceChooser.add("1 Plane Src, 2 Freq");
        this.sourceChooser.add("2 Plane Src, 1 Freq");
        this.sourceChooser.add("2 Plane Src, 2 Freq");
        this.sourceChooser.add("1 Plane 1 Freq (Packet)");
        this.sourceChooser.select(1);
        this.sourceChooser.addItemListener(this);
        this.add(this.sourceChooser);
        (this.modeChooser = new Choice()).add("Mouse = Add Perf. Conductor");
        this.modeChooser.add("Mouse = Add Good Conductor");
        this.modeChooser.add("Mouse = Add Fair Conductor");
        this.modeChooser.add("Mouse = Add Current (+)");
        this.modeChooser.add("Mouse = Add Current (-)");
        this.modeChooser.add("Mouse = Add Ferromagnet");
        this.modeChooser.add("Mouse = Add Diamagnet");
        this.modeChooser.add("Mouse = Add Dielectric");
        this.modeChooser.add("Mouse = Add Magnet (Down)");
        this.modeChooser.add("Mouse = Add Magnet (Up)");
        this.modeChooser.add("Mouse = Add Magnet (Left)");
        this.modeChooser.add("Mouse = Add Magnet (Right)");
        this.modeChooser.add("Mouse = Add Resonant Medium");
        this.modeChooser.add("Mouse = Clear");
        this.modeChooser.add("Mouse = Adjust Conductivity");
        this.modeChooser.add("Mouse = Adjust Permeability");
        this.modeChooser.add("Mouse = Adjust Current");
        this.modeChooser.add("Mouse = Adjust Dielectric");
        this.modeChooser.add("Mouse = Adjust Mag Dir");
        this.modeChooser.add("Mouse = Adjust Mag Strength");
        this.modeChooser.addItemListener(this);
        this.add(this.modeChooser);
        (this.viewChooser = new Choice()).add("Show Electric Field (E)");
        this.viewChooser.add("Show Magnetic Field (B)");
        this.viewChooser.add("Show B Lines");
        this.viewChooser.add("Show B Strength");
        this.viewChooser.add("Show Current (j)");
        this.viewChooser.add("Show E/B");
        this.viewChooser.add("Show E/B lines");
        this.viewChooser.add("Show E/B/j");
        this.viewChooser.add("Show E/B lines/j");
        this.viewChooser.add("Show Mag. Intensity (H)");
        this.viewChooser.add("Show Magnetization (M)");
        this.viewChooser.add("Show Material Type");
        this.viewChooser.add("Show Vec. Potential");
        this.viewChooser.add("Show Poynting Vector");
        this.viewChooser.add("Show Energy Density");
        this.viewChooser.add("Show Poynting/Energy");
        this.viewChooser.add("Show Force");
        this.viewChooser.add("Show Effective Current");
        this.viewChooser.add("Show Magnetic Charge");
        this.viewChooser.add("Show Curl E");
        this.viewChooser.add("Show Bx");
        this.viewChooser.add("Show By");
        this.viewChooser.add("Show Hx");
        this.viewChooser.add("Show Hy");
        this.viewChooser.addItemListener(this);
        this.add(this.viewChooser);
        this.viewChooser.select(7);
        this.add(this.clearButton = new Button("Clear Fields"));
        this.clearButton.addActionListener(this);
        this.add(this.ClearAllButton = new Button("Clear All"));
        this.ClearAllButton.addActionListener(this);
        (this.stoppedCheck = new Checkbox("Stopped")).addItemListener(this);
        this.add(this.stoppedCheck);
        this.add(new Label("Simulation Speed", 1));
        this.add(this.speedBar = new Scrollbar(0, 180, 1, 1, 2000));
        this.speedBar.addAdjustmentListener(this);
        this.add(new Label("Resolution", 1));
        this.add(this.resBar = new Scrollbar(0, 40, 5, 16, 140));
        this.resBar.addAdjustmentListener(this);
        this.setResolution();
        this.add(new Label("Source Frequency", 1));
        this.add(this.forceBar = new Scrollbar(0, this.forceBarValue = 10, 1, 1, 40));
        this.forceBar.addAdjustmentListener(this);
        this.add(new Label("Brightness", 1));
        this.add(this.brightnessBar = new Scrollbar(0, 10, 1, 1, 2000));
        this.brightnessBar.addAdjustmentListener(this);
        this.add(new Label("Line Density", 1));
        this.add(this.lineDensityBar = new Scrollbar(0, 50, 1, 10, 100));
        this.lineDensityBar.addAdjustmentListener(this);
        this.add(this.auxLabel = new Label("", 1));
        this.add(this.auxBar = new Scrollbar(0, 1, 1, 1, 40));
        this.auxBar.addAdjustmentListener(this);
        this.add(this.adjustLabel = new Label("", 1));
        this.add(this.adjustBar = new Scrollbar(0, 50, 1, 0, 102));
        this.adjustBar.addAdjustmentListener(this);
        this.add(new Label("http://www.falstad.com"));
        try {
            final String parameter = this.applet.getParameter("PAUSE");
            if (parameter != null) {
                this.pause = Integer.parseInt(parameter);
            }
        }
        catch (Exception ex) {}
        this.random = new Random();
        this.reinit();
        this.setup = this.setupList.elementAt(0);
        this.cv.setBackground(Color.black);
        this.cv.setForeground(Color.lightGray);
        this.setModeChooser();
        this.resize(660, 500);
        this.handleResize();
        final Dimension size = this.getSize();
        final Dimension screenSize = this.getToolkit().getScreenSize();
        this.setLocation((screenSize.width - size.width) / 2, (screenSize.height - size.height) / 2);
        this.show();
    }
    
    void reinit() {
        this.sourceCount = -1;
        this.adjustSelectX1 = -1;
        this.grid = new OscElement[this.gridSizeXY];
        for (int i = 0; i != this.gridSizeXY; ++i) {
            this.grid[i] = new OscElement();
        }
        this.doSetup();
    }
    
    void setDamping() {
        for (int i = 0; i != this.gridSizeXY; ++i) {
            this.grid[i].damp = 1.0;
            if (this.grid[i].medium > 0) {
                this.grid[i].damp = 0.99;
            }
        }
        for (int j = 0; j != this.windowOffsetX; ++j) {
            for (int k = 0; k != this.gridSizeX; ++k) {
                final double exp = Math.exp(-(this.windowOffsetX - j) * 0.002);
                final OscElement oscElement = this.grid[j + k * this.gw];
                final OscElement oscElement2 = this.grid[this.gridSizeX - 1 - j + this.gw * k];
                final OscElement oscElement3 = this.grid[k + j * this.gw];
                final OscElement oscElement4 = this.grid[k + this.gw * (this.gridSizeY - 1 - j)];
                final double n = exp;
                oscElement4.damp = n;
                oscElement3.damp = n;
                oscElement2.damp = n;
                oscElement.damp = n;
            }
        }
    }
    
    void handleResize() {
        final Dimension size = this.cv.getSize();
        this.winSize = size;
        final Dimension dimension = size;
        if (this.winSize.width == 0) {
            return;
        }
        this.pixels = null;
        if (this.useBufferedImage) {
            try {
                final Class<?> forName = Class.forName("java.awt.image.BufferedImage");
                final Class<?> forName2 = Class.forName("java.awt.image.DataBufferInt");
                final Class<?> forName3 = Class.forName("java.awt.image.Raster");
                this.dbimage = (Image)forName.getConstructor(Integer.TYPE, Integer.TYPE, Integer.TYPE).newInstance(new Integer(dimension.width), new Integer(dimension.height), new Integer(1));
                this.pixels = (int[])forName2.getMethod("getData", (Class[])null).invoke(forName3.getMethod("getDataBuffer", (Class[])null).invoke(forName.getMethod("getRaster", (Class[])null).invoke(this.dbimage, (Object[])null), (Object[])null), (Object[])null);
            }
            catch (Exception ex) {
                System.out.println("BufferedImage failed");
            }
        }
        if (this.pixels == null) {
            this.pixels = new int[dimension.width * dimension.height];
            for (int i = 0; i != dimension.width * dimension.height; ++i) {
                this.pixels[i] = -16777216;
            }
            (this.imageSource = new MemoryImageSource(dimension.width, dimension.height, this.pixels, 0, dimension.width)).setAnimated(true);
            this.imageSource.setFullBufferUpdates(true);
            this.dbimage = this.cv.createImage(this.imageSource);
        }
    }
    
    public boolean handleEvent(final Event event) {
        if (event.id == 201) {
            this.applet.destroyFrame();
            return true;
        }
        return super.handleEvent(event);
    }
    
    void doClear() {
        for (int i = 0; i < this.gridSizeXY; ++i) {
            final OscElement oscElement = this.grid[i];
            final OscElement oscElement2 = this.grid[i];
            final double n = 1.0E-10;
            oscElement2.dazdt = n;
            oscElement.az = n;
            this.grid[i].epos = 0.0f;
            if (this.grid[i].resonant) {
                this.grid[i].jz = 0.0;
            }
        }
        this.t = 0.0;
        this.doFilter();
    }
    
    void doClearAll() {
        for (int i = 0; i < this.gridSizeXY; ++i) {
            final OscElement oscElement = this.grid[i];
            oscElement.jz = 0.0;
            final OscElement oscElement2 = oscElement;
            final OscElement oscElement3 = oscElement;
            final double n = 1.000000013351432E-10;
            oscElement3.dazdt = n;
            oscElement2.az = n;
            oscElement.boundary = false;
            oscElement.gray = false;
            oscElement.resonant = false;
            oscElement.conductivity = 0.0f;
            oscElement.perm = 1.0f;
            oscElement.medium = 0;
            final OscElement oscElement4 = oscElement;
            final OscElement oscElement5 = oscElement;
            final float n2 = 0.0f;
            oscElement5.my = n2;
            oscElement4.mx = n2;
            oscElement.epos = 0.0f;
        }
        this.setDamping();
        this.sourceChooser.select(0);
        this.setSources();
    }
    
    void calcBoundaries() {
        int n = 0;
        for (int i = 0; i < this.gridSizeX; ++i) {
            for (int j = 0; j < this.windowOffsetY; ++j) {
                this.grid[i + this.gw * j].conductivity = this.grid[i + this.gw * this.windowOffsetY].conductivity;
                this.grid[i + this.gw * (this.gridSizeY - j - 1)].conductivity = this.grid[i + this.gw * (this.gridSizeY - this.windowOffsetY - 1)].conductivity;
            }
        }
        for (int k = 0; k < this.gridSizeY; ++k) {
            for (int l = 0; l < this.windowOffsetX; ++l) {
                this.grid[l + this.gw * k].conductivity = this.grid[this.windowOffsetX + this.gw * k].conductivity;
                this.grid[this.gridSizeX - l - 1 + this.gw * k].conductivity = this.grid[this.gridSizeX - this.windowOffsetX - 1 + this.gw * k].conductivity;
            }
        }
        for (int n2 = 1; n2 < this.gridSizeX - 1; ++n2) {
            for (int n3 = 1; n3 < this.gridSizeY - 1; ++n3) {
                final int n4 = n2 + this.gw * n3;
                final OscElement oscElement = this.grid[n4];
                final double n5 = oscElement.perm;
                final int medium = oscElement.medium;
                final double n6 = oscElement.mx;
                final double n7 = oscElement.my;
                final OscElement oscElement2 = this.grid[n4 - 1];
                final OscElement oscElement3 = this.grid[n4 + 1];
                final OscElement oscElement4 = this.grid[n4 - this.gw];
                final OscElement oscElement5 = this.grid[n4 + this.gw];
                oscElement.gray = (oscElement.conductivity > 0.0f || oscElement.medium != 0 || oscElement.perm != 1.0f || oscElement.mx != 0.0f || oscElement.my != 0.0f || oscElement.resonant);
                if (oscElement2.perm != n5 || oscElement3.perm != n5 || oscElement4.perm != n5 || oscElement5.perm != n5 || oscElement2.medium != medium || oscElement3.medium != medium || oscElement4.medium != medium || oscElement5.medium != medium || oscElement2.mx != n6 || oscElement3.mx != n6 || oscElement4.mx != n6 || oscElement5.mx != n6 || oscElement2.my != n7 || oscElement3.my != n7 || oscElement4.my != n7 || oscElement5.my != n7 || oscElement.resonant) {
                    oscElement.boundary = true;
                    ++n;
                }
                else {
                    oscElement.boundary = false;
                }
            }
        }
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
    
    public void updateEMWave2(final Graphics graphics) {
        if (this.winSize == null || this.winSize.width == 0) {
            this.handleResize();
            return;
        }
        double n = 0.0;
        if (!this.stoppedCheck.getState()) {
            n = 5 * 0.05;
        }
        int dragging = this.dragging ? 1 : 0;
        if (this.stoppedCheck.getState()) {
            dragging = 1;
        }
        final double n2 = this.speedBar.getValue() / 2.0;
        if (dragging != 0) {
            this.lastTime = 0L;
        }
        else {
            if (this.lastTime == 0L) {
                this.lastTime = System.currentTimeMillis();
            }
            if (n2 * (System.currentTimeMillis() - this.lastTime) < 1000.0) {
                dragging = 1;
            }
        }
        if (dragging == 0) {
            final int n3 = this.gridSizeX - 1;
            final int n4 = this.gridSizeY - 1;
            int n5 = 1;
            long currentTimeMillis;
            while (true) {
                this.doSources(n, false);
                this.setup.doStep();
                double n6 = 1.0;
                int medium = 0;
                for (int i = 1; i != n4; ++i) {
                    int j = i * this.gw + 1;
                    final int n7 = j + n3 - 1;
                    OscElement oscElement = this.grid[j - 1];
                    OscElement oscElement2 = this.grid[j];
                    while (j != n7) {
                        final OscElement oscElement3 = oscElement;
                        oscElement = oscElement2;
                        oscElement2 = this.grid[j + 1];
                        final OscElement oscElement4 = this.grid[j - this.gw];
                        final OscElement oscElement5 = this.grid[j + this.gw];
                        if (oscElement.conductivity > 0.0f) {
                            oscElement.jz = 0.0;
                        }
                        double n9;
                        if (oscElement.boundary) {
                            if (oscElement.resonant) {
                                oscElement.jz = oscElement.jz * 0.999 + -oscElement.dazdt * 0.001 - oscElement.epos * 0.02;
                                final OscElement oscElement6 = oscElement;
                                oscElement6.epos += (float)(oscElement.jz * 0.2);
                            }
                            if (medium != oscElement.medium) {
                                medium = oscElement.medium;
                                final double n8 = 1.0 - 0.002617801047120419 * medium;
                                n6 = n8 * n8;
                            }
                            final double az = oscElement.az;
                            n9 = oscElement.perm * (((oscElement2.az - az) / oscElement2.perm + (oscElement3.az - az) / oscElement3.perm + (oscElement5.az - az) / oscElement5.perm + (oscElement4.az - az) / oscElement4.perm) * 0.25) + (oscElement3.my - oscElement2.my + oscElement5.mx - oscElement4.mx + oscElement.jz);
                        }
                        else {
                            n9 = oscElement.jz - (oscElement.az - (oscElement2.az + oscElement3.az + oscElement5.az + oscElement4.az) * 0.25);
                        }
                        oscElement.dazdt = oscElement.dazdt * oscElement.damp + n9 * n6;
                        ++j;
                    }
                }
                final double n10 = n * n;
                for (int k = 1; k != n4; ++k) {
                    for (int l = k * this.gw + 1; l != l - 1 + n3; ++l) {
                        final OscElement oscElement7 = this.grid[l];
                        if (oscElement7.conductivity > 0.0f) {
                            final double jz = -oscElement7.dazdt * oscElement7.conductivity;
                            oscElement7.jz = jz;
                            final OscElement oscElement8 = oscElement7;
                            oscElement8.dazdt += jz;
                        }
                        final OscElement oscElement9 = oscElement7;
                        oscElement9.az += oscElement7.dazdt * n10;
                    }
                }
                this.t += n;
                this.filterGrid();
                currentTimeMillis = System.currentTimeMillis();
                if (currentTimeMillis - this.lastTime > 200L || n5 * 1000 >= n2 * (currentTimeMillis - this.lastTime)) {
                    break;
                }
                ++n5;
            }
            this.lastTime = currentTimeMillis;
        }
        this.renderGrid();
        final int n11 = (this.gridSizeY / 2 - this.windowOffsetY) * this.winSize.height / this.windowHeight;
        for (int n12 = 0; n12 < this.sourceCount; ++n12) {
            final OscSource oscSource = this.sources[n12];
            this.plotSource(n12, oscSource.getScreenX(), oscSource.getScreenY());
        }
        if (this.adjustSelectX1 != -1) {
            this.plotRect(this.adjustSelectX1 * this.winSize.width / this.windowWidth, this.adjustSelectY1 * this.winSize.height / this.windowHeight, (this.adjustSelectX2 + 1) * this.winSize.width / this.windowWidth - 1, (this.adjustSelectY2 + 1) * this.winSize.height / this.windowHeight - 1, 65792 * this.getrand(255) - 16777216);
        }
        if (this.imageSource != null) {
            this.imageSource.newPixels();
        }
        graphics.drawImage(this.dbimage, 0, 0, this);
        if (!this.stoppedCheck.getState()) {
            this.cv.repaint(this.pause);
        }
    }
    
    void plotRect(final int n, final int n2, final int n3, final int n4, final int n5) {
        for (int i = n; i <= n3; ++i) {
            this.plotPixel(i, n2, n5);
            this.plotPixel(i, n4, n5);
        }
        for (int j = n2; j <= n4; ++j) {
            this.plotPixel(n, j, n5);
            this.plotPixel(n3, j, n5);
        }
    }
    
    void plotPixel(final int n, final int n2, final int n3) {
        if (n < 0 || n >= this.winSize.width) {
            return;
        }
        try {
            this.pixels[n + n2 * this.winSize.width] = n3;
        }
        catch (Exception ex) {}
    }
    
    void plotSource(final int n, final int n2, final int n3) {
        final int n4 = 7;
        final int n5 = (n == this.selectedSource) ? -16711681 : -1;
        for (int i = 0; i <= n4; ++i) {
            final int n6 = (int)(Math.sqrt(n4 * n4 - i * i) + 0.5);
            this.plotPixel(n2 + i, n3 + n6, n5);
            this.plotPixel(n2 + n6, n3 + i, n5);
            this.plotPixel(n2 + i, n3 - n6, n5);
            this.plotPixel(n2 - n6, n3 + i, n5);
            this.plotPixel(n2 - i, n3 + n6, n5);
            this.plotPixel(n2 + n6, n3 - i, n5);
            this.plotPixel(n2 - i, n3 - n6, n5);
            this.plotPixel(n2 - n6, n3 - i, n5);
            this.plotPixel(n2, n3 + i, n5);
            this.plotPixel(n2, n3 - i, n5);
            this.plotPixel(n2 + i, n3, n5);
            this.plotPixel(n2 - i, n3, n5);
        }
    }
    
    void renderGrid() {
        final double n = this.brightnessBar.getValue() / 50.0;
        double n2 = 1.0;
        final int selectedIndex = this.viewChooser.getSelectedIndex();
        if (selectedIndex == 16) {
            this.calcForce();
        }
        boolean b = false;
        int n6;
        int n5;
        int n4;
        int n3 = n4 = (n5 = (n6 = -1));
        switch (selectedIndex) {
            case 0:
            case 3:
            case 4:
            case 11:
            case 12:
            case 14:
            case 17:
            case 18:
            case 20:
            case 21:
            case 22:
            case 23: {
                n3 = (n4 = selectedIndex);
                break;
            }
            case 1:
            case 9:
            case 10:
            case 13:
            case 19: {
                n6 = (n5 = selectedIndex);
                break;
            }
            case 2: {
                b = true;
                break;
            }
            case 16: {
                n3 = (n4 = 0);
                n6 = (n5 = 16);
                break;
            }
            case 5: {
                n3 = (n4 = 0);
                n6 = (n5 = 1);
                n2 = 0.3;
                break;
            }
            case 7: {
                n4 = 0;
                n3 = 4;
                n6 = (n5 = 1);
                n2 = 0.3;
                break;
            }
            case 6: {
                n3 = (n4 = 0);
                b = true;
                n2 = 0.3;
                break;
            }
            case 8: {
                n4 = 0;
                n3 = 4;
                b = true;
                n2 = 0.3;
                break;
            }
            case 15: {
                n3 = (n4 = 14);
                n6 = (n5 = 13);
                break;
            }
        }
        for (int i = 0; i != this.windowHeight; ++i) {
            int n7 = this.winSize.width * (i * this.winSize.height / this.windowHeight);
            for (int n8 = (i + this.windowOffsetY) * this.gw + this.windowOffsetX, j = 0; j != this.windowWidth; ++j, ++n8) {
                final int n9 = j * this.winSize.width / this.windowWidth;
                final int n10 = i * this.winSize.height / this.windowHeight;
                final int n11 = (j + 1) * this.winSize.width / this.windowWidth;
                final int n12 = (i + 1) * this.winSize.height / this.windowHeight;
                final int n13 = j + this.windowOffsetX;
                final int n14 = i + this.windowOffsetY;
                int n15 = n4;
                int n16 = n5;
                int n17 = 0;
                int n18 = 0;
                int n19 = 0;
                final OscElement oscElement = this.grid[n8];
                if (oscElement.gray || oscElement.jz != 0.0) {
                    n18 = (n17 = (n19 = 64));
                    if (oscElement.conductivity > 0.0f || (oscElement.jz != 0.0 && !oscElement.resonant)) {
                        n16 = n6;
                        n15 = n3;
                    }
                }
                if (n15 != -1) {
                    double n20 = 0.0;
                    switch (n15) {
                        case 0: {
                            n20 = -oscElement.dazdt * n2;
                            break;
                        }
                        case 12: {
                            n20 = oscElement.az * 0.2;
                            break;
                        }
                        case 4: {
                            n20 = oscElement.jz;
                            break;
                        }
                        case 20: {
                            n20 = this.grid[n8 - this.gw].az - this.grid[n8 + this.gw].az;
                            break;
                        }
                        case 21: {
                            n20 = -(this.grid[n8 + 1].az - this.grid[n8 - 1].az);
                            break;
                        }
                        case 22: {
                            n20 = (this.grid[n8 - this.gw].az - this.grid[n8 + this.gw].az) / oscElement.perm - oscElement.mx * 12.0;
                            break;
                        }
                        case 23: {
                            n20 = -((this.grid[n8 + 1].az - this.grid[n8 - 1].az) / oscElement.perm - oscElement.my * 12.0);
                            break;
                        }
                        case 17: {
                            n20 = this.getMagY(n8 - 1) - this.getMagY(n8 + 1) + this.getMagX(n8 + this.gw) - this.getMagX(n8 - this.gw);
                            break;
                        }
                        case 18: {
                            n20 = this.grid[n8 - 1].mx - this.grid[n8 + 1].mx + this.grid[n8 - this.gw].my - this.grid[n8 + this.gw].my;
                            break;
                        }
                        case 3: {
                            final double n21 = this.grid[n8 - this.gw].az - this.grid[n8 + this.gw].az;
                            final double n22 = this.grid[n8 + 1].az - this.grid[n8 - 1].az;
                            n20 = Math.sqrt(n21 * n21 + n22 * n22);
                            break;
                        }
                        case 14: {
                            final double n23 = 1.0 / (1.0 - oscElement.medium * 0.5 / 191.0);
                            final double n24 = n23 * n23;
                            final double n25 = this.grid[n8 - this.gw].az - this.grid[n8 + this.gw].az;
                            final double n26 = this.grid[n8 + 1].az - this.grid[n8 - 1].az;
                            n20 = 0.4 * ((n25 * n25 + n26 * n26) / oscElement.perm + oscElement.dazdt * oscElement.dazdt * n24);
                            break;
                        }
                    }
                    double n27 = n20 * n;
                    if (n27 < -1.0) {
                        n27 = -1.0;
                    }
                    if (n27 > 1.0) {
                        n27 = 1.0;
                    }
                    if (n15 == 11) {
                        double n28 = 0.0;
                        double n29 = 0.0;
                        double n30 = 0.0;
                        if (oscElement.resonant) {
                            n28 = 1.0;
                            n29 = 0.75;
                            n30 = 0.5;
                        }
                        else if (oscElement.perm < 1.0f) {
                            n28 = 1.0f - oscElement.perm;
                        }
                        else if (oscElement.perm > 1.0f) {
                            n29 = (oscElement.perm - 1.0f) / 30.0f;
                        }
                        else if (oscElement.mx != 0.0f || oscElement.my != 0.0f) {
                            n28 = 0.53;
                            n29 = 0.27;
                            n30 = 0.63;
                        }
                        else if (oscElement.medium > 0) {
                            n28 = oscElement.medium / 191.0;
                            n29 = n28 * 0.5;
                        }
                        else if (oscElement.conductivity > 0.0f) {
                            n30 = (n29 = oscElement.conductivity);
                            if (oscElement.conductivity == 1.0f) {
                                n28 = 1.0;
                            }
                        }
                        else if (oscElement.jz > 0.0) {
                            n29 = (n28 = oscElement.jz * n);
                        }
                        else if (oscElement.jz < 0.0) {
                            n30 = -oscElement.jz * n;
                        }
                        final double clamp = this.clamp(n28);
                        final double clamp2 = this.clamp(n29);
                        final double clamp3 = this.clamp(n30);
                        n17 += (int)(clamp * (255 - n17));
                        n18 += (int)(clamp2 * (255 - n18));
                        n19 += (int)(clamp3 * (255 - n19));
                    }
                    else if (n15 == 4 || n15 == 17 || n15 == 14) {
                        if (n27 < 0.0) {
                            n19 += (int)(-n27 * (255 - n19));
                        }
                        else {
                            n17 += (int)(n27 * (255 - n17));
                            n18 += (int)(n27 * (255 - n18));
                        }
                    }
                    else if (n27 < 0.0) {
                        n17 += (int)(-n27 * (255 - n17));
                    }
                    else {
                        n18 += (int)(n27 * (255 - n18));
                    }
                }
                final int col = 0xFF000000 | n17 << 16 | n18 << 8 | n19;
                for (int k = 0; k != n11 - n9; ++k, ++n7) {
                    for (int l = 0; l != n12 - n10; ++l) {
                        this.pixels[n7 + l * this.winSize.width] = col;
                    }
                }
                oscElement.col = col;
                if (n16 != -1) {
                    double n31 = 0.0;
                    double n32 = 0.0;
                    switch (n16) {
                        case 1: {
                            n31 = this.grid[n8 - this.gw].az - this.grid[n8 + this.gw].az;
                            n32 = this.grid[n8 + 1].az - this.grid[n8 - 1].az;
                            break;
                        }
                        case 9: {
                            n31 = (this.grid[n8 - this.gw].az - this.grid[n8 + this.gw].az) / oscElement.perm - 12.0 * oscElement.mx;
                            n32 = (this.grid[n8 + 1].az - this.grid[n8 - 1].az) / oscElement.perm - 12.0 * oscElement.my;
                            break;
                        }
                        case 10: {
                            final double n33 = 1.0f - 1.0f / oscElement.perm;
                            n31 = (this.grid[n8 - this.gw].az - this.grid[n8 + this.gw].az) * n33 + oscElement.mx;
                            n32 = (this.grid[n8 + 1].az - this.grid[n8 - 1].az) * n33 + oscElement.my;
                            break;
                        }
                        case 13: {
                            n32 = 5.0 * oscElement.dazdt * (this.grid[n8 - this.gw].az - this.grid[n8 + this.gw].az) / oscElement.perm;
                            n31 = -5.0 * oscElement.dazdt * (this.grid[n8 + 1].az - this.grid[n8 - 1].az) / oscElement.perm;
                            break;
                        }
                        case 16: {
                            n31 = this.forceVecs[this.forceMap[n13][n14]][0];
                            n32 = this.forceVecs[this.forceMap[n13][n14]][1];
                            break;
                        }
                        case 19: {
                            n31 = -5.0 * (this.grid[n8 - this.gw].dazdt - this.grid[n8 + this.gw].dazdt);
                            n32 = -5.0 * (this.grid[n8 + 1].dazdt - this.grid[n8 - 1].dazdt);
                            break;
                        }
                    }
                    final double sqrt = Math.sqrt(n31 * n31 + n32 * n32);
                    if (sqrt > 0.0) {
                        n31 /= sqrt;
                        n32 /= sqrt;
                    }
                    double n34 = sqrt * n;
                    int n36;
                    if (n34 > 1.0) {
                        if (n34 > 2.0) {
                            n34 = 2.0;
                        }
                        final double n35 = n34 - 1.0;
                        n36 = 255;
                        n17 += (int)(n35 * (255 - n17));
                        n19 += (int)(n35 * (255 - n19));
                    }
                    else {
                        n36 = n18 + (int)(n34 * (255 - n18));
                    }
                    final int n37 = 0xFF000000 | n17 << 16 | n36 << 8 | n19;
                    final int n38 = (n11 - n9) / 2;
                    final int n39 = (n12 - n10) / 2;
                    final int n40 = n9 + n38 - (int)(n38 * n31);
                    final int n41 = n10 + n39 - (int)(n39 * n32);
                    final int n42 = n9 + n38 + (int)(n38 * n31);
                    final int n43 = n10 + n39 + (int)(n39 * n32);
                    this.drawLine(n40, n41, n42, n43, n37);
                    final int n44 = 3;
                    this.drawLine(n42, n43, (int)(n32 * n44 - n31 * n44 + n42), (int)(-n31 * n44 - n32 * n44 + n43), n37);
                    this.drawLine(n42, n43, (int)(-n32 * n44 - n31 * n44 + n42), (int)(n31 * n44 - n32 * n44 + n43), n37);
                }
            }
        }
        if (b) {
            this.renderLines();
            this.lineDensityBar.enable();
        }
        else {
            this.lineDensityBar.disable();
        }
    }
    
    void drawLine(int n, int n2, int n3, int n4, final int n5) {
        if (n < 0) {
            n = 0;
        }
        if (n2 < 0) {
            n2 = 0;
        }
        if (n3 < 0) {
            n3 = 0;
        }
        if (n4 < 0) {
            n4 = 0;
        }
        if (n >= this.winSize.width - 1) {
            n = this.winSize.width - 1;
        }
        if (n2 >= this.winSize.height - 1) {
            n2 = this.winSize.height - 1;
        }
        if (n3 >= this.winSize.width - 1) {
            n3 = this.winSize.width - 1;
        }
        if (n4 >= this.winSize.height - 1) {
            n4 = this.winSize.height - 1;
        }
        final int abs = this.abs(n3 - n);
        final int abs2 = this.abs(n4 - n2);
        if (abs > abs2) {
            if (n > n3) {
                final int n6 = n;
                n = n3;
                n3 = n6;
                final int n7 = n2;
                n2 = n4;
                n4 = n7;
            }
            for (int i = n; i <= n3; ++i) {
                this.pixels[i + (n2 + (n4 - n2) * (i - n) / abs) * this.winSize.width] = n5;
            }
        }
        else if (abs2 > 0) {
            if (n2 > n4) {
                final int n8 = n;
                n = n3;
                n3 = n8;
                final int n9 = n2;
                n2 = n4;
                n4 = n9;
            }
            for (int j = n2; j <= n4; ++j) {
                this.pixels[n + (n3 - n) * (j - n2) / abs2 + j * this.winSize.width] = n5;
            }
        }
    }
    
    double getMagX(final int n) {
        final OscElement oscElement = this.grid[n];
        return (this.grid[n - this.gw].az - this.grid[n + this.gw].az) * (1.0f - 1.0f / oscElement.perm) + oscElement.mx;
    }
    
    double getMagY(final int n) {
        final OscElement oscElement = this.grid[n];
        return (this.grid[n + 1].az - this.grid[n - 1].az) * (1.0f - 1.0f / oscElement.perm) + oscElement.my;
    }
    
    double clamp(final double n) {
        return (n < 0.0) ? 0.0 : ((n > 1.0) ? 1.0 : n);
    }
    
    void doSources(final double n, final boolean b) {
        if (this.sourceCount == 0) {
            return;
        }
        double n3;
        final double n2 = n3 = this.forceBar.getValue() * (this.t - this.forceTimeZero) * 0.01166665;
        switch (this.auxFunction) {
            case 2: {
                n3 = this.auxBar.getValue() * this.t * 0.01166665;
                break;
            }
            case 1: {
                int n4 = this.auxBar.getValue() - 1;
                if (n4 > 38) {
                    n4 = 38;
                }
                n3 = n2 + n4 * 0.08267349088394192;
                break;
            }
        }
        double sin = 0.0;
        double sin2 = 0.0;
        switch (this.sourceWaveform) {
            case 0: {
                sin = Math.sin(n2);
                if (this.sourceCount >= 2) {
                    sin2 = Math.sin(n3);
                    break;
                }
                if (this.sourceFreqCount == 2) {
                    sin = (sin + Math.sin(n3)) * 0.5;
                    break;
                }
                break;
            }
            case 1: {
                final double n5 = n2 % 6.283185307179586 / (0.01166665 * this.forceBar.getValue()) - 10.0;
                sin = Math.exp(-0.01 * n5 * n5) * Math.sin(n5 * 0.2);
                if (n5 < 0.0) {
                    this.doFilter();
                    break;
                }
                break;
            }
        }
        if (b) {
            sin2 = (sin = 0.0);
        }
        final OscSource oscSource = this.sources[0];
        final OscSource oscSource2 = this.sources[2];
        final double n6 = (float)(2.0 * sin * this.sourceMult);
        oscSource2.v = n6;
        oscSource.v = n6;
        final OscSource oscSource3 = this.sources[1];
        final OscSource oscSource4 = this.sources[3];
        final double n7 = (float)(2.0 * sin2 * this.sourceMult);
        oscSource4.v = n7;
        oscSource3.v = n7;
        if (this.sourcePlane) {
            for (int i = 0; i != this.sourceCount / 2; ++i) {
                final OscSource oscSource5 = this.sources[i * 2];
                final OscSource oscSource6 = this.sources[i * 2 + 1];
                this.drawPlaneSource(oscSource5.x, oscSource5.y, oscSource6.x, oscSource6.y, this.sources[i].v * 0.1);
            }
        }
        else {
            for (int j = 0; j != this.sourceCount; ++j) {
                final OscSource oscSource7 = this.sources[j];
                this.grid[oscSource7.x + this.gw * oscSource7.y].jz = oscSource7.v;
            }
        }
    }
    
    int abs(final int n) {
        return (n < 0) ? (-n) : n;
    }
    
    void drawPlaneSource(int n, int n2, int n3, int n4, final double jz) {
        if (n2 == n4) {
            if (n == this.windowOffsetX) {
                n = 0;
            }
            if (n3 == this.windowOffsetX) {
                n3 = 0;
            }
            if (n == this.windowOffsetX + this.windowWidth - 1) {
                n = this.gridSizeX - 1;
            }
            if (n3 == this.windowOffsetX + this.windowWidth - 1) {
                n3 = this.gridSizeX - 1;
            }
        }
        if (n == n3) {
            if (n2 == this.windowOffsetY) {
                n2 = 0;
            }
            if (n4 == this.windowOffsetY) {
                n4 = 0;
            }
            if (n2 == this.windowOffsetY + this.windowHeight - 1) {
                n2 = this.gridSizeY - 1;
            }
            if (n4 == this.windowOffsetY + this.windowHeight - 1) {
                n4 = this.gridSizeY - 1;
            }
        }
        if (n == n3 && n2 == n4) {
            this.grid[n + this.gw * n2].jz = jz;
        }
        else if (this.abs(n4 - n2) > this.abs(n3 - n)) {
            for (int sign = this.sign(n4 - n2), i = n2; i != n4 + sign; i += sign) {
                this.grid[n + (n3 - n) * (i - n2) / (n4 - n2) + this.gw * i].jz = jz;
            }
        }
        else {
            for (int sign2 = this.sign(n3 - n), j = n; j != n3 + sign2; j += sign2) {
                this.grid[j + this.gw * (n2 + (n4 - n2) * (j - n) / (n3 - n))].jz = jz;
            }
        }
    }
    
    int sign(final int n) {
        return (n < 0) ? -1 : ((n == 0) ? 0 : 1);
    }
    
    void renderLines() {
        double n = 0.0;
        double n2 = 0.0;
        final int value = this.lineDensityBar.getValue();
        final int n3 = value * value;
        if (this.linegrid == null) {
            this.linegrid = new byte[n3];
        }
        final double n4 = value / this.windowWidth;
        double n5 = -1.0;
        double n6 = 0.0;
        int n7 = 0;
        final double n8 = this.brightnessBar.getValue() / 50.0;
        int n9 = 1;
        double n10 = -1.0;
        int n11 = -1;
        int n12 = 0;
        int n13 = 0;
        for (int i = 0; i != n3; ++i) {
            this.linegrid[i] = 0;
        }
        int n14 = -1;
        int n15 = -1;
    Block_9:
        while (true) {
            if (n7-- == 0 || n == 0.0) {
                Label_0231: {
                    if (n9 == 1) {
                        while (true) {
                            for (int n16 = n12 + value * n13; this.linegrid[n16] != 0; ++n16) {
                                if (++n12 == value) {
                                    if (++n13 == value) {
                                        if (n12 == value && n13 == value) {
                                            break Block_9;
                                        }
                                        n5 = n12 / n4;
                                        n6 = n13 / n4;
                                        break Label_0231;
                                    }
                                    else {
                                        n12 = 0;
                                    }
                                }
                            }
                            continue;
                        }
                    }
                }
                n = n5 + 0.48 / n4;
                n2 = n6 + 0.48 / n4;
                n7 = 40;
                n9 = -n9;
                n15 = (n14 = -1);
            }
            if (n < 0.0 || n2 < 0.0 || n >= this.windowWidth || n2 >= this.windowHeight) {
                n = 0.0;
            }
            else {
                final int n17 = (int)(n * n4);
                final int n18 = (int)(n2 * n4);
                boolean b = true;
                if (n17 != n14 || n18 != n15) {
                    final byte[] linegrid = this.linegrid;
                    final int n19 = n17 + value * n18;
                    if (++linegrid[n19] > 2) {
                        n = 0.0;
                        continue;
                    }
                    n14 = n17;
                    n15 = n18;
                }
                else {
                    b = false;
                }
                final int n20 = this.windowOffsetX + (int)n + this.gw * (this.windowOffsetY + (int)n2);
                final OscElement oscElement = this.grid[n20];
                final double n21 = this.grid[n20 - this.gw].az - this.grid[n20 + this.gw].az;
                final double n22 = this.grid[n20 + 1].az - this.grid[n20 - 1].az;
                final double sqrt = Math.sqrt(n21 * n21 + n22 * n22);
                if (sqrt == 0.0) {
                    n = 0.0;
                }
                else {
                    final double n23 = n21 / sqrt;
                    final double n24 = n22 / sqrt;
                    final double n25 = n;
                    final double n26 = n2;
                    n += 0.5 * n23 * n9;
                    n2 += 0.5 * n24 * n9;
                    double n27 = sqrt * n8;
                    int col = this.grid[n20].col;
                    if (n27 != n10 || col != n11) {
                        int n28 = col >> 16 & 0xFF;
                        final int n29 = col >> 8 & 0xFF;
                        int n30 = col & 0xFF;
                        int n31;
                        if (n27 > 1.0) {
                            if (n27 > 2.0) {
                                n27 = 2.0;
                            }
                            --n27;
                            n31 = 255;
                            n28 += (int)(n27 * (255 - n28));
                            n30 += (int)(n27 * (255 - n30));
                        }
                        else {
                            n31 = n29 + (int)(n27 * (255 - n29));
                        }
                        col = (0xFF000000 | n28 << 16 | n31 << 8 | n30);
                        n10 = n27;
                        n11 = col;
                    }
                    final int n32 = (int)(n25 * this.winSize.width / this.windowWidth);
                    final int n33 = (int)(n26 * this.winSize.height / this.windowHeight);
                    final int n34 = (int)(n * this.winSize.width / this.windowWidth);
                    final int n35 = (int)(n2 * this.winSize.height / this.windowHeight);
                    this.drawLine(n32, n33, n34, n35, col);
                    if (!b || this.linegrid[n17 + value * n18] != 1 || (n17 & 0x3) != 0x0 || (n18 & 0x3) != 0x0) {
                        continue;
                    }
                    final int n36 = 5;
                    this.drawLine(n34, n35, (int)(n24 * n36 - n23 * n36 + n34), (int)(-n23 * n36 - n24 * n36 + n35), col);
                    this.drawLine(n34, n35, (int)(-n24 * n36 - n23 * n36 + n34), (int)(n23 * n36 - n24 * n36 + n35), col);
                }
            }
        }
    }
    
    void calcForce() {
        this.forceMap = new byte[this.gridSizeX][this.gridSizeY];
        this.forceVecs = new double[256][2];
        byte b = 1;
        for (int i = this.windowOffsetX; i != this.windowWidth + this.windowOffsetX; ++i) {
            for (int j = this.windowOffsetY; j != this.windowHeight + this.windowOffsetY; ++j) {
                if (this.forceMap[i][j] == 0) {
                    if (this.grid[i + this.gw * j].feelsForce()) {
                        this.forceVecs[b][0] = (this.forceVecs[b][1] = 0.0);
                        final int n = i;
                        final int n2 = j;
                        final byte b2 = b;
                        ++b;
                        this.forceSearch(n, n2, b2);
                    }
                }
            }
        }
    }
    
    void forceSearch(final int n, final int n2, final byte b) {
        if (this.forceMap[n][n2] != 0) {
            return;
        }
        if (n < this.windowOffsetX || n2 < this.windowOffsetY || n >= this.windowOffsetX + this.windowWidth || n2 >= this.windowOffsetY + this.windowHeight) {
            return;
        }
        final int n3 = n + n2 * this.gw;
        final double n4 = this.getMagX(n3 - 1) - this.getMagX(n3 + 1) + this.getMagY(n3 - this.gw) - this.getMagY(n3 + this.gw);
        final double n5 = this.grid[n3 - this.gw].az - this.grid[n3 + this.gw].az;
        final double n6 = this.grid[n3 + 1].az - this.grid[n3 - 1].az;
        final double[] array = this.forceVecs[b];
        final int n7 = 0;
        array[n7] += n4 * n5 + this.grid[n3].jz * n6;
        final double[] array2 = this.forceVecs[b];
        final int n8 = 1;
        array2[n8] += n4 * n6 - this.grid[n3].jz * n5;
        if (this.grid[n3].feelsForce()) {
            this.forceSearch(n - 1, n2, this.forceMap[n][n2] = b);
            this.forceSearch(n + 1, n2, b);
            this.forceSearch(n, n2 - 1, b);
            this.forceSearch(n, n2 + 1, b);
        }
    }
    
    void filterGrid() {
        if ((this.filterCount++ & 0x3) != 0x0) {
            return;
        }
        if (this.filterCount > 200) {
            return;
        }
        final double n = (this.forceBar.getValue() > 7 && this.sourceCount > 0 && this.sourceWaveform == 0) ? 40.0 : 8.0;
        final double n2 = 4.0 + n;
        for (int i = 1; i < this.gridSizeY - 1; ++i) {
            for (int j = 1; j < this.gridSizeX - 1; ++j) {
                final int n3 = j + i * this.gw;
                final OscElement oscElement = this.grid[n3];
                if (oscElement.jz == 0.0) {
                    if (oscElement.conductivity <= 0.0f) {
                        if (oscElement.perm == this.grid[n3 - 1].perm && oscElement.perm == this.grid[n3 + 1].perm && oscElement.perm == this.grid[n3 - this.gw].perm) {
                            if (oscElement.perm == this.grid[n3 + this.gw].perm) {
                                if (this.grid[n3 - 1].my - this.grid[n3 + 1].my + this.grid[n3 + this.gw].mx - this.grid[n3 - this.gw].mx == 0.0) {
                                    oscElement.az = (oscElement.az * n + this.grid[n3 - 1].az + this.grid[n3 + 1].az + this.grid[n3 - this.gw].az + this.grid[n3 + this.gw].az) / n2;
                                }
                            }
                        }
                    }
                }
            }
        }
    }
    
    void noFilter() {
        this.filterCount = 200;
    }
    
    void doFilter() {
        this.filterCount %= 4;
    }
    
    void edit(final MouseEvent mouseEvent) {
        final int x = mouseEvent.getX();
        final int y = mouseEvent.getY();
        if (this.selectedSource != -1) {
            this.doSources(1.0, true);
            final int n = x * this.windowWidth / this.winSize.width;
            final int n2 = y * this.windowHeight / this.winSize.height;
            final OscSource oscSource = this.sources[this.selectedSource];
            if (n >= 0 && n2 >= 0 && n < this.windowWidth && n2 < this.windowHeight) {
                final int x2 = oscSource.x;
                final int y2 = oscSource.y;
                oscSource.x = n + this.windowOffsetX;
                oscSource.y = n2 + this.windowOffsetY;
                this.cv.repaint(this.pause);
            }
            return;
        }
        if (this.modeChooser.getSelectedIndex() < 14) {
            if (this.dragX == x && this.dragY == y) {
                this.editFuncPoint(x, y);
            }
            else if (this.abs(y - this.dragY) > this.abs(x - this.dragX)) {
                final int n3 = (y < this.dragY) ? x : this.dragX;
                final int n4 = (y < this.dragY) ? y : this.dragY;
                final int n5 = (y > this.dragY) ? x : this.dragX;
                final int n6 = (y > this.dragY) ? y : this.dragY;
                this.dragX = x;
                this.dragY = y;
                for (int i = n4; i <= n6; ++i) {
                    this.editFuncPoint(n3 + (n5 - n3) * (i - n4) / (n6 - n4), i);
                }
            }
            else {
                final int n7 = (x < this.dragX) ? x : this.dragX;
                final int n8 = (x < this.dragX) ? y : this.dragY;
                final int n9 = (x > this.dragX) ? x : this.dragX;
                final int n10 = (x > this.dragX) ? y : this.dragY;
                this.dragX = x;
                this.dragY = y;
                for (int j = n7; j <= n9; ++j) {
                    this.editFuncPoint(j, n8 + (n10 - n8) * (j - n7) / (n9 - n7));
                }
            }
            this.calcBoundaries();
            this.cv.repaint(this.pause);
            return;
        }
        final int n11 = x * this.windowWidth / this.winSize.width;
        final int n12 = y * this.windowHeight / this.winSize.height;
        if (this.adjustSelectX1 == -1) {
            final int n13 = n11;
            this.adjustSelectX2 = n13;
            this.adjustSelectX1 = n13;
            final int n14 = n12;
            this.adjustSelectY2 = n14;
            this.adjustSelectY1 = n14;
            this.adjustBar.enable();
            return;
        }
        this.adjustSelectX1 = this.max(0, this.min(n11, this.adjustSelectX1));
        this.adjustSelectX2 = this.min(this.windowWidth - 1, this.max(n11, this.adjustSelectX2));
        this.adjustSelectY1 = this.max(0, this.min(n12, this.adjustSelectY1));
        this.adjustSelectY2 = this.min(this.windowHeight - 1, this.max(n12, this.adjustSelectY2));
        this.adjustBar.enable();
    }
    
    int min(final int n, final int n2) {
        return (n < n2) ? n : n2;
    }
    
    int max(final int n, final int n2) {
        return (n > n2) ? n : n2;
    }
    
    void editFuncPoint(final int n, final int n2) {
        final int n3 = n * this.windowWidth / this.winSize.width;
        final int n4 = n2 * this.windowHeight / this.winSize.height;
        if (n3 < 0 || n3 >= this.windowWidth || n4 < 0 || n4 >= this.windowHeight) {
            return;
        }
        final int n5 = n3 + this.windowOffsetX;
        final int n6 = n4 + this.windowOffsetY;
        final OscElement oscElement = this.grid[n5 + this.gw * n6];
        this.doFilter();
        if (!this.dragSet && !this.dragClear) {
            this.dragClear = (oscElement.conductivity != 0.0f || oscElement.medium != 0 || oscElement.mx != 0.0f || oscElement.my != 0.0f || oscElement.perm != 1.0f || oscElement.jz != 0.0 || oscElement.resonant);
            this.dragSet = !this.dragClear;
        }
        oscElement.conductivity = 0.0f;
        oscElement.medium = 0;
        final OscElement oscElement2 = oscElement;
        final OscElement oscElement3 = oscElement;
        final float n7 = 0.0f;
        oscElement3.my = n7;
        oscElement2.mx = n7;
        oscElement.perm = 1.0f;
        oscElement.jz = 0.0;
        oscElement.resonant = false;
        if (this.dragClear) {
            return;
        }
        switch (this.modeChooser.getSelectedIndex()) {
            case 3: {
                oscElement.jz = 1.0;
                break;
            }
            case 4: {
                oscElement.jz = -1.0;
                break;
            }
            case 5: {
                this.addPerm(n5, n6, 5.0);
                break;
            }
            case 6: {
                this.addPerm(n5, n6, 0.5);
                break;
            }
            case 7: {
                oscElement.medium = 191;
                break;
            }
            case 8: {
                oscElement.my = 1.0f;
                break;
            }
            case 9: {
                oscElement.my = -1.0f;
                break;
            }
            case 10: {
                oscElement.mx = -1.0f;
                break;
            }
            case 11: {
                oscElement.mx = 1.0f;
                break;
            }
            case 0: {
                this.addConductor(n5, n6, 1.0);
                break;
            }
            case 1: {
                this.addConductor(n5, n6, 0.9);
                break;
            }
            case 2: {
                this.addConductor(n5, n6, 0.5);
                break;
            }
            case 12: {
                oscElement.resonant = true;
                break;
            }
        }
    }
    
    void selectSource(final MouseEvent mouseEvent) {
        final int x = mouseEvent.getX();
        final int y = mouseEvent.getY();
        for (int i = 0; i != this.sourceCount; ++i) {
            final OscSource oscSource = this.sources[i];
            final int screenX = oscSource.getScreenX();
            final int screenY = oscSource.getScreenY();
            if (49 > (screenX - x) * (screenX - x) + (screenY - y) * (screenY - y)) {
                this.selectedSource = i;
                return;
            }
        }
        this.selectedSource = -1;
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
        this.cv.repaint(100L);
    }
    
    public void actionPerformed(final ActionEvent actionEvent) {
        if (actionEvent.getSource() == this.clearButton) {
            this.doClear();
            this.cv.repaint();
        }
        if (actionEvent.getSource() == this.ClearAllButton) {
            this.doClearAll();
            this.cv.repaint();
        }
    }
    
    public void adjustmentValueChanged(final AdjustmentEvent adjustmentEvent) {
        System.out.print(((Scrollbar)adjustmentEvent.getSource()).getValue() + "\n");
        if (adjustmentEvent.getSource() == this.resBar) {
            this.setResolution();
            this.reinit();
            this.cv.repaint(this.pause);
        }
        if (adjustmentEvent.getSource() == this.brightnessBar) {
            this.cv.repaint(this.pause);
        }
        if (adjustmentEvent.getSource() == this.lineDensityBar) {
            this.cv.repaint(this.pause);
            this.linegrid = null;
        }
        if (adjustmentEvent.getSource() == this.forceBar) {
            this.setForce();
        }
        if (adjustmentEvent.getSource() == this.adjustBar) {
            this.doAdjust();
        }
    }
    
    void setForceBar(final int n) {
        this.forceBar.setValue(n);
        this.forceBarValue = n;
        this.forceTimeZero = 0.0;
    }
    
    void setForce() {
        final double n = this.forceBarValue * 0.01166665;
        this.forceBarValue = this.forceBar.getValue();
        this.forceTimeZero = this.t - n * (this.t - this.forceTimeZero) / (this.forceBarValue * 0.01166665);
    }
    
    void setResolution() {
        final int value = this.resBar.getValue();
        this.windowHeight = value;
        this.windowWidth = value;
        final int n = 20;
        this.windowOffsetY = n;
        this.windowOffsetX = n;
        this.gridSizeX = this.windowWidth + this.windowOffsetX * 2;
        this.gridSizeY = this.windowHeight + this.windowOffsetY * 2;
        this.gridSizeXY = this.gridSizeX * this.gridSizeY;
        this.gw = this.gridSizeX;
        System.out.println("gridsize " + this.gridSizeX + " window " + this.windowWidth);
        this.linegrid = null;
    }
    
    void setResolution(final int value) {
        this.resBar.setValue(value);
        this.setResolution();
        this.reinit();
    }
    
    void doAdjust() {
        if (this.adjustSelectX1 == -1) {
            return;
        }
        int value = this.adjustBar.getValue();
        if (value < 1) {
            value = 1;
        }
        if (value > 99) {
            value = 100;
        }
        if (this.modeChooser.getSelectedIndex() == 15 && value < 3) {
            value = 3;
        }
        final float conductivity = value / 100.0f;
        for (int i = this.adjustSelectY1; i <= this.adjustSelectY2; ++i) {
            for (int j = this.adjustSelectX1; j <= this.adjustSelectX2; ++j) {
                final OscElement oscElement = this.grid[j + this.windowOffsetX + this.gw * (i + this.windowOffsetY)];
                switch (this.modeChooser.getSelectedIndex()) {
                    case 14: {
                        if (oscElement.getType() == 1) {
                            oscElement.conductivity = conductivity;
                            break;
                        }
                        break;
                    }
                    case 15: {
                        if (oscElement.getType() == 3) {
                            oscElement.perm = value / 2.0f;
                            break;
                        }
                        break;
                    }
                    case 16: {
                        if (oscElement.getType() == 5) {
                            oscElement.jz = ((oscElement.jz < 0.0) ? (-conductivity) : ((double)conductivity));
                            break;
                        }
                        break;
                    }
                    case 17: {
                        if (oscElement.getType() == 6) {
                            oscElement.medium = (int)(conductivity * 191.0f);
                            break;
                        }
                        break;
                    }
                    case 18: {
                        if (oscElement.getType() == 4) {
                            final double sqrt = Math.sqrt(oscElement.mx * oscElement.mx + oscElement.my * oscElement.my);
                            oscElement.mx = (float)(Math.cos(conductivity * 2.0f * 3.141592653589793) * sqrt);
                            oscElement.my = (float)(-(Math.sin(conductivity * 2.0f * 3.141592653589793) * sqrt));
                            break;
                        }
                        break;
                    }
                    case 19: {
                        if (oscElement.getType() == 4) {
                            final float n = (float)(conductivity / Math.sqrt(oscElement.mx * oscElement.mx + oscElement.my * oscElement.my));
                            final OscElement oscElement2 = oscElement;
                            oscElement2.mx *= n;
                            final OscElement oscElement3 = oscElement;
                            oscElement3.my *= n;
                            break;
                        }
                        break;
                    }
                }
            }
        }
        this.calcBoundaries();
    }
    
    public void mouseDragged(final MouseEvent mouseEvent) {
        if (!this.dragging) {
            this.selectSource(mouseEvent);
        }
        this.dragging = true;
        this.edit(mouseEvent);
    }
    
    public void mouseMoved(final MouseEvent mouseEvent) {
        if ((mouseEvent.getModifiers() & 0x10) != 0x0) {
            return;
        }
        final int x = mouseEvent.getX();
        final int y = mouseEvent.getY();
        this.dragX = x;
        this.dragY = y;
        this.selectSource(mouseEvent);
    }
    
    public void mouseClicked(final MouseEvent mouseEvent) {
    }
    
    public void mouseEntered(final MouseEvent mouseEvent) {
    }
    
    public void mouseExited(final MouseEvent mouseEvent) {
        this.selectedSource = -1;
        this.cv.repaint();
    }
    
    public void mousePressed(final MouseEvent mouseEvent) {
        if ((mouseEvent.getModifiers() & 0x10) == 0x0) {
            return;
        }
        this.adjustSelectX1 = -1;
        this.adjustBar.disable();
        final int x = mouseEvent.getX();
        final int y = mouseEvent.getY();
        this.dragX = x;
        this.dragY = y;
        if (!this.dragging) {
            this.selectSource(mouseEvent);
        }
        this.dragging = true;
        this.edit(mouseEvent);
    }
    
    public void mouseReleased(final MouseEvent mouseEvent) {
        if ((mouseEvent.getModifiers() & 0x10) == 0x0) {
            return;
        }
        this.dragging = false;
        final boolean b = false;
        this.dragClear = b;
        this.dragSet = b;
        this.cv.repaint();
    }
    
    public void itemStateChanged(final ItemEvent itemEvent) {
        this.cv.repaint(this.pause);
        if (itemEvent.getItemSelectable() == this.stoppedCheck) {
            return;
        }
        if (itemEvent.getItemSelectable() == this.sourceChooser) {
            this.setSources();
            this.doFilter();
        }
        if (itemEvent.getItemSelectable() == this.setupChooser) {
            this.doSetup();
        }
        if (itemEvent.getItemSelectable() == this.modeChooser) {
            this.setModeChooser();
        }
    }
    
    void setModeChooser() {
        if (this.modeChooser.getSelectedIndex() < 14) {
            this.adjustLabel.hide();
            this.adjustBar.hide();
            this.validate();
            this.adjustSelectX1 = -1;
            return;
        }
        switch (this.modeChooser.getSelectedIndex()) {
            case 14: {
                this.adjustLabel.setText("Conductivity");
                break;
            }
            case 15: {
                this.adjustLabel.setText("Permeability");
                break;
            }
            case 16: {
                this.adjustLabel.setText("Current");
                break;
            }
            case 17: {
                this.adjustLabel.setText("Dielectric Constant");
                break;
            }
            case 18: {
                this.adjustLabel.setText("Direction");
                break;
            }
            case 19: {
                this.adjustLabel.setText("Strength");
                break;
            }
        }
        this.adjustLabel.show();
        this.adjustBar.show();
        if (this.adjustSelectX1 == -1) {
            this.adjustBar.disable();
        }
        else {
            this.adjustBar.enable();
        }
        this.validate();
    }
    
    void doSetup() {
        this.t = 0.0;
        this.doClearAll();
        this.sourceCount = -1;
        this.filterCount = 0;
        this.sourceChooser.select(1);
        this.setForceBar(10);
        this.brightnessBar.setValue(100);
        this.auxBar.setValue(1);
        (this.setup = this.setupList.elementAt(this.setupChooser.getSelectedIndex())).select();
        this.setup.doSetupSources();
        this.calcBoundaries();
        this.setDamping();
    }
    
    void addMedium() {
        for (int i = 0; i != this.gridSizeX; ++i) {
            for (int j = this.gridSizeY / 2; j != this.gridSizeY; ++j) {
                this.grid[i + this.gw * j].medium = 191;
            }
        }
    }
    
    void addCondMedium(final double n) {
        this.conductFillRect(0, this.gridSizeY / 2, this.gridSizeX - 1, this.gridSizeY - 1, n);
    }
    
    void addResMedium() {
        for (int i = 0; i != this.gridSizeX; ++i) {
            for (int j = this.gridSizeY / 2; j != this.gridSizeY; ++j) {
                this.grid[i + this.gw * j].resonant = true;
            }
        }
    }
    
    void addUniformField() {
        final float n = 2.0f / this.windowHeight;
        for (int i = 0; i != this.gridSizeY; ++i) {
            this.grid[this.windowOffsetX + this.gw * i].jz = n;
            this.grid[this.windowOffsetX + this.windowWidth - 1 + this.gw * i].jz = -n;
        }
    }
    
    void addSolenoid(final int n, final int n2, final int n3, final int n4, final double jz) {
        for (int i = n2; i <= n4; ++i) {
            this.grid[n + this.gw * i].jz = jz;
            this.grid[n3 + this.gw * i].jz = -jz;
        }
    }
    
    void addMagnet(final int n, final int n2, final int n3, final int n4, final double n5) {
        for (int i = n2; i <= n4; ++i) {
            for (int j = n; j <= n3; ++j) {
                this.grid[j + this.gw * i].my = (float)n5;
            }
        }
    }
    
    void addMagnet(final int n, final int n2, final int n3, final int n4, final double n5, final double n6) {
        for (int i = n2; i <= n4; ++i) {
            for (int j = n; j <= n3; ++j) {
                this.grid[j + this.gw * i].mx = (float)n5;
                this.grid[j + this.gw * i].my = (float)n6;
            }
        }
    }
    
    void setSources() {
        if (this.sourceCount > 0) {
            this.doSources(1.0, true);
        }
        this.sourceMult = 1.0;
        final int sourceCount = this.sourceCount;
        final boolean sourcePlane = this.sourcePlane;
        this.sourceFreqCount = 1;
        this.sourcePlane = (this.sourceChooser.getSelectedIndex() >= 8);
        this.sourceWaveform = 0;
        this.sourceCount = 1;
        switch (this.sourceChooser.getSelectedIndex()) {
            case 0: {
                this.sourceCount = 0;
                break;
            }
            case 2: {
                this.sourceFreqCount = 2;
                break;
            }
            case 3: {
                this.sourceCount = 2;
                break;
            }
            case 4: {
                this.sourceCount = 2;
                this.sourceFreqCount = 2;
                break;
            }
            case 5: {
                this.sourceCount = 3;
                break;
            }
            case 6: {
                this.sourceCount = 4;
                break;
            }
            case 7: {
                this.sourceWaveform = 1;
                break;
            }
            case 9: {
                this.sourceFreqCount = 2;
                break;
            }
            case 10: {
                this.sourceCount = 2;
                break;
            }
            case 11: {
                final int n = 2;
                this.sourceFreqCount = n;
                this.sourceCount = n;
                break;
            }
            case 12: {
                this.sourceWaveform = 1;
                break;
            }
        }
        if (this.sourceFreqCount >= 2) {
            this.auxFunction = 2;
            this.auxBar.setValue(this.forceBar.getValue());
            if (this.sourceCount == 2) {
                this.auxLabel.setText("Source 2 Frequency");
            }
            else {
                this.auxLabel.setText("2nd Frequency");
            }
        }
        else if (this.sourceCount == 2 || this.sourceCount == 4) {
            this.auxFunction = 1;
            this.auxBar.setValue(1);
            this.auxLabel.setText("Phase Difference");
        }
        else {
            this.auxFunction = 0;
            this.auxBar.hide();
            this.auxLabel.hide();
        }
        if (this.auxFunction != 0) {
            this.auxBar.show();
            this.auxLabel.show();
        }
        this.validate();
        if (this.sourcePlane) {
            this.sourceCount *= 2;
            if (!sourcePlane || sourceCount != this.sourceCount) {
                final int n2 = this.windowOffsetX + this.windowWidth - 1;
                final int n3 = this.windowOffsetY + this.windowHeight - 1;
                this.sources[0] = new OscSource(this.windowOffsetX, this.windowOffsetY);
                this.sources[1] = new OscSource(n2, this.windowOffsetY);
                this.sources[2] = new OscSource(this.windowOffsetX, n3);
                this.sources[3] = new OscSource(n2, n3);
            }
        }
        else if (sourceCount != this.sourceCount || sourcePlane) {
            this.sources[0] = new OscSource(this.gridSizeX / 2, this.windowOffsetY + 1);
            this.sources[1] = new OscSource(this.gridSizeX / 2, this.gridSizeY - this.windowOffsetY - 2);
            this.sources[2] = new OscSource(this.windowOffsetX + 1, this.gridSizeY / 2);
            this.sources[3] = new OscSource(this.gridSizeX - this.windowOffsetX - 2, this.gridSizeY / 2);
        }
    }
    
    void setupMode(final int n, final int n2, final int n3, final int n4, final int n5, final int n6) {
        for (int i = 0; i != n3; ++i) {
            for (int j = 0; j != n4; ++j) {
                this.grid[i + n + this.gw * (j + n2)].az = 2.0 * (Math.sin(3.141592653589793 * n5 * (i + 1) / (n3 + 1)) * Math.sin(3.141592653589793 * n6 * (j + 1) / (n4 + 1)));
                this.grid[i + n + this.gw * (j + n2)].dazdt = 0.0;
            }
        }
        this.noFilter();
    }
    
    double zeroj(final int n, final int n2) {
        final double n3 = (n2 + 0.5 * n - 0.25) * 3.141592654;
        final double n4 = 4 * n * n;
        final double n5 = 8.0 * n3;
        double n6 = n3 - (n4 - 1.0) / n5 - 4.0 * (n4 - 1.0) * (7.0 * n4 - 31.0) / (3.0 * n5 * n5 * n5);
        final double[] array = new double[n + 3];
        for (int i = 1; i <= 5; ++i) {
            this.bess(n + 1, n6, array);
            n6 -= array[n + 1] / (-array[n + 2] + n / n6 * array[n + 1]);
        }
        return n6;
    }
    
    void bess(final int n, final double n2, final double[] array) {
        final int n3 = 2 * ((((n > n2) ? n : ((int)n2)) + 15) / 2 + 1);
        final double[] array2 = new double[n3 + 2];
        array2[n3 + 1] = 0.0;
        array2[n3] = 1.0;
        final double n4 = 1.0E-16;
        for (int i = n3 - 2; i >= 0; --i) {
            array2[i + 1] = 2 * (i + 1) / (n2 + n4) * array2[i + 2] - array2[i + 3];
        }
        double n5 = array2[1];
        for (int j = 2; j <= n3; j += 2) {
            n5 += 2.0 * array2[j + 1];
        }
        for (int k = 0; k <= n; ++k) {
            array[k + 1] = array2[k + 1] / n5;
        }
    }
    
    void addThickWire(int n, int n2, int n3, double n4) {
        final int n5 = 4;
        n *= n5;
        n2 *= n5;
        n3 *= n5;
        n4 /= n5 * n5;
        for (int i = -n3; i <= n3; ++i) {
            for (int n6 = (int)Math.sqrt(n3 * n3 - i * i), j = -n6; j <= n6; ++j) {
                final OscElement oscElement = this.grid[(i + n) / n5 + this.gw * ((j + n2) / n5)];
                oscElement.jz += n4;
            }
        }
    }
    
    void addWireCircle(final int n, final int n2, int n3, double n4, final int n5, final int n6) {
        final int n7 = 4;
        n3 *= n7;
        n4 /= n7 * n7;
        for (int i = n5; i != n6; ++i) {
            final OscElement oscElement = this.grid[n + (int)(Math.cos(i * 3.141592653589793 / 180.0) * n3 / n7) + this.gw * (n2 - (int)(Math.sin(i * 3.141592653589793 / 180.0) * n3 / n7))];
            oscElement.jz += n4;
        }
    }
    
    void addConductor(final int n, final int n2, final double n3) {
        final OscElement oscElement = this.grid[n + this.gw * n2];
        oscElement.conductivity = (float)n3;
        if (n3 == 1.0) {
            final OscElement oscElement2 = oscElement;
            final OscElement oscElement3 = oscElement;
            final double n4 = 0.0;
            oscElement3.dazdt = n4;
            oscElement2.az = n4;
        }
    }
    
    void addPerm(final int n, final int n2, final double n3) {
        final OscElement oscElement = this.grid[n + this.gw * n2];
        oscElement.perm = (float)n3;
        oscElement.conductivity = ((n3 == 1.0) ? 0.0f : 0.5f);
    }
    
    void conductFillRect(final int n, final int n2, final int n3, final int n4, final double n5) {
        for (int i = n; i <= n3; ++i) {
            for (int j = n2; j <= n4; ++j) {
                this.addConductor(i, j, (float)n5);
            }
        }
    }
    
    void conductDrawRect(final int n, final int n2, final int n3, final int n4, final double n5) {
        final float n6 = (float)n5;
        for (int i = n; i <= n3; ++i) {
            this.addConductor(i, n2, n6);
            this.addConductor(i, n4, n6);
        }
        for (int j = n2; j <= n4; ++j) {
            this.addConductor(n, j, n6);
            this.addConductor(n3, j, n6);
        }
    }
    
    void permDrawRect(final int n, final int n2, final int n3, final int n4, final double n5) {
        for (int i = n; i <= n3; ++i) {
            this.addPerm(i, n2, n5);
            this.addPerm(i, n4, n5);
        }
        for (int j = n2; j <= n4; ++j) {
            this.addPerm(n, j, n5);
            this.addPerm(n3, j, n5);
        }
    }
    
    void permFillRect(final int n, final int n2, final int n3, final int n4, final double n5) {
        for (int i = n; i <= n3; ++i) {
            for (int j = n2; j <= n4; ++j) {
                this.addPerm(i, j, n5);
            }
        }
    }
    
    class OscSource
    {
        int x;
        int y;
        double v;
        
        OscSource(final int x, final int y) {
            this.x = x;
            this.y = y;
        }
        
        int getScreenX() {
            return ((this.x - EMWave2Frame.this.windowOffsetX) * EMWave2Frame.this.winSize.width + EMWave2Frame.this.winSize.width / 2) / EMWave2Frame.this.windowWidth;
        }
        
        int getScreenY() {
            return ((this.y - EMWave2Frame.this.windowOffsetY) * EMWave2Frame.this.winSize.height + EMWave2Frame.this.winSize.height / 2) / EMWave2Frame.this.windowHeight;
        }
    }
    
    class OscElement
    {
        float perm;
        float conductivity;
        float mx;
        float my;
        double jz;
        float epos;
        double damp;
        double az;
        double dazdt;
        int medium;
        int col;
        boolean boundary;
        boolean gray;
        boolean resonant;
        
        int getType() {
            if (this.perm < 1.0f) {
                return 2;
            }
            if (this.perm > 1.0f) {
                return 3;
            }
            if (this.mx != 0.0f || this.my != 0.0f) {
                return 4;
            }
            if (this.medium > 0) {
                return 6;
            }
            if (this.conductivity > 0.0f) {
                return 1;
            }
            if (this.jz != 0.0) {
                return 5;
            }
            return 0;
        }
        
        boolean feelsForce() {
            final int type = this.getType();
            return type != 0 && type != 6;
        }
    }
    
    abstract class Setup
    {
        abstract String getName();
        
        void select() {
        }
        
        void deselect() {
        }
        
        void valueChanged(final Scrollbar scrollbar) {
        }
        
        void doStep() {
        }
        
        void doSetupSources() {
            EMWave2Frame.this.setSources();
        }
        
        abstract Setup createNext();
    }
    
    class SingleSourceSetup extends Setup
    {
        String getName() {
            return "Single Source";
        }
        
        void select() {
            EMWave2Frame.this.setForceBar(30);
        }
        
        Setup createNext() {
            return new DoubleSourceSetup();
        }
    }
    
    class DoubleSourceSetup extends Setup
    {
        String getName() {
            return "Two Sources";
        }
        
        void select() {
            EMWave2Frame.this.setForceBar(30);
        }
        
        void doSetupSources() {
            EMWave2Frame.this.sourceChooser.select(3);
            EMWave2Frame.this.setSources();
            EMWave2Frame.this.sources[0].y = EMWave2Frame.this.gridSizeY / 2 - 8;
            EMWave2Frame.this.sources[1].y = EMWave2Frame.this.gridSizeY / 2 + 8;
            final OscSource oscSource = EMWave2Frame.this.sources[0];
            final OscSource oscSource2 = EMWave2Frame.this.sources[1];
            final int n = EMWave2Frame.this.gridSizeX / 2;
            oscSource2.x = n;
            oscSource.x = n;
        }
        
        Setup createNext() {
            return new PlaneWaveSetup();
        }
    }
    
    class PlaneWaveSetup extends Setup
    {
        String getName() {
            return "Plane Wave";
        }
        
        void select() {
            EMWave2Frame.this.sourceChooser.select(8);
            EMWave2Frame.this.brightnessBar.setValue(225);
            EMWave2Frame.this.setForceBar(30);
        }
        
        Setup createNext() {
            return new IntersectingPlaneWavesSetup();
        }
    }
    
    class IntersectingPlaneWavesSetup extends Setup
    {
        String getName() {
            return "Intersecting Planes";
        }
        
        void select() {
            EMWave2Frame.this.brightnessBar.setValue(70);
            EMWave2Frame.this.setForceBar(34);
        }
        
        void doSetupSources() {
            EMWave2Frame.this.sourceChooser.select(10);
            EMWave2Frame.this.setSources();
            final OscSource oscSource = EMWave2Frame.this.sources[0];
            final OscSource oscSource2 = EMWave2Frame.this.sources[1];
            final int windowOffsetY = EMWave2Frame.this.windowOffsetY;
            oscSource2.y = windowOffsetY;
            oscSource.y = windowOffsetY;
            EMWave2Frame.this.sources[0].x = EMWave2Frame.this.windowOffsetX + 1;
            final OscSource oscSource3 = EMWave2Frame.this.sources[2];
            final OscSource oscSource4 = EMWave2Frame.this.sources[3];
            final int windowOffsetX = EMWave2Frame.this.windowOffsetX;
            oscSource4.x = windowOffsetX;
            oscSource3.x = windowOffsetX;
            EMWave2Frame.this.sources[2].y = EMWave2Frame.this.windowOffsetY + 1;
            EMWave2Frame.this.sources[3].y = EMWave2Frame.this.windowOffsetY + EMWave2Frame.this.windowHeight - 1;
        }
        
        Setup createNext() {
            return new SingleWireSetup();
        }
    }
    
    class SingleWireSetup extends Setup
    {
        String getName() {
            return "Single Wire";
        }
        
        void select() {
            EMWave2Frame.this.sourceChooser.select(0);
            EMWave2Frame.this.grid[EMWave2Frame.this.gridSizeX / 2 + EMWave2Frame.this.gw * (EMWave2Frame.this.gridSizeY / 2)].jz = 1.0;
            EMWave2Frame.this.brightnessBar.setValue(200);
        }
        
        Setup createNext() {
            return new DoubleWireSetup();
        }
    }
    
    class DoubleWireSetup extends Setup
    {
        String getName() {
            return "Wire Pair";
        }
        
        void select() {
            EMWave2Frame.this.sourceChooser.select(0);
            EMWave2Frame.this.grid[EMWave2Frame.this.gridSizeX / 2 + EMWave2Frame.this.gw * (EMWave2Frame.this.gridSizeY / 2 - 3)].jz = 1.0;
            EMWave2Frame.this.grid[EMWave2Frame.this.gridSizeX / 2 + EMWave2Frame.this.gw * (EMWave2Frame.this.gridSizeY / 2 + 3)].jz = 1.0;
            EMWave2Frame.this.brightnessBar.setValue(200);
        }
        
        Setup createNext() {
            return new DipoleWireSetup();
        }
    }
    
    class DipoleWireSetup extends Setup
    {
        String getName() {
            return "Dipole Wire Pair";
        }
        
        void select() {
            EMWave2Frame.this.sourceChooser.select(0);
            EMWave2Frame.this.grid[EMWave2Frame.this.gridSizeX / 2 + EMWave2Frame.this.gw * (EMWave2Frame.this.gridSizeY / 2 - 3)].jz = 1.0;
            EMWave2Frame.this.grid[EMWave2Frame.this.gridSizeX / 2 + EMWave2Frame.this.gw * (EMWave2Frame.this.gridSizeY / 2 + 3)].jz = -1.0;
            EMWave2Frame.this.brightnessBar.setValue(200);
        }
        
        Setup createNext() {
            return new MagnetPairSetup();
        }
    }
    
    class MagnetPairSetup extends Setup
    {
        String getName() {
            return "Magnet Pair";
        }
        
        void select() {
            EMWave2Frame.this.sourceChooser.select(0);
            EMWave2Frame.this.addMagnet(EMWave2Frame.this.gridSizeX / 2 - EMWave2Frame.this.windowWidth / 4 - 3, EMWave2Frame.this.gridSizeY / 2 - 2, EMWave2Frame.this.gridSizeX / 2 - EMWave2Frame.this.windowWidth / 4 + 3, EMWave2Frame.this.gridSizeY / 2 + 2, -0.2);
            EMWave2Frame.this.addMagnet(EMWave2Frame.this.gridSizeX / 2 + EMWave2Frame.this.windowWidth / 4 - 3, EMWave2Frame.this.gridSizeY / 2 - 2, EMWave2Frame.this.gridSizeX / 2 + EMWave2Frame.this.windowWidth / 4 + 3, EMWave2Frame.this.gridSizeY / 2 + 2, -0.2);
        }
        
        Setup createNext() {
            return new MagnetPairOppSetup();
        }
    }
    
    class MagnetPairOppSetup extends Setup
    {
        String getName() {
            return "Magnet Pair, Opp";
        }
        
        void select() {
            EMWave2Frame.this.sourceChooser.select(0);
            EMWave2Frame.this.addMagnet(EMWave2Frame.this.gridSizeX / 2 - EMWave2Frame.this.windowWidth / 4 - 3, EMWave2Frame.this.gridSizeY / 2 - 2, EMWave2Frame.this.gridSizeX / 2 - EMWave2Frame.this.windowWidth / 4 + 3, EMWave2Frame.this.gridSizeY / 2 + 2, -0.2);
            EMWave2Frame.this.addMagnet(EMWave2Frame.this.gridSizeX / 2 + EMWave2Frame.this.windowWidth / 4 - 3, EMWave2Frame.this.gridSizeY / 2 - 2, EMWave2Frame.this.gridSizeX / 2 + EMWave2Frame.this.windowWidth / 4 + 3, EMWave2Frame.this.gridSizeY / 2 + 2, 0.2);
        }
        
        Setup createNext() {
            return new MagnetPairStackedSetup();
        }
    }
    
    class MagnetPairStackedSetup extends Setup
    {
        String getName() {
            return "Magnet Pair Stacked";
        }
        
        void select() {
            EMWave2Frame.this.sourceChooser.select(0);
            EMWave2Frame.this.addMagnet(EMWave2Frame.this.gridSizeX / 2 - 3, EMWave2Frame.this.gridSizeY / 2 - 10, EMWave2Frame.this.gridSizeX / 2 + 3, EMWave2Frame.this.gridSizeY / 2 - 5, -0.2);
            EMWave2Frame.this.addMagnet(EMWave2Frame.this.gridSizeX / 2 - 3, EMWave2Frame.this.gridSizeY / 2 + 5, EMWave2Frame.this.gridSizeX / 2 + 3, EMWave2Frame.this.gridSizeY / 2 + 10, -0.2);
        }
        
        Setup createNext() {
            return new MagnetPairStackedOppSetup();
        }
    }
    
    class MagnetPairStackedOppSetup extends Setup
    {
        String getName() {
            return "Magnet Pair Stacked Opp";
        }
        
        void select() {
            EMWave2Frame.this.sourceChooser.select(0);
            EMWave2Frame.this.addMagnet(EMWave2Frame.this.gridSizeX / 2 - 3, EMWave2Frame.this.gridSizeY / 2 - 10, EMWave2Frame.this.gridSizeX / 2 + 3, EMWave2Frame.this.gridSizeY / 2 - 5, 0.2);
            EMWave2Frame.this.addMagnet(EMWave2Frame.this.gridSizeX / 2 - 3, EMWave2Frame.this.gridSizeY / 2 + 5, EMWave2Frame.this.gridSizeX / 2 + 3, EMWave2Frame.this.gridSizeY / 2 + 10, -0.2);
        }
        
        Setup createNext() {
            return new UniformFieldSetup();
        }
    }
    
    class UniformFieldSetup extends Setup
    {
        String getName() {
            return "Uniform Field";
        }
        
        void select() {
            EMWave2Frame.this.sourceChooser.select(0);
            EMWave2Frame.this.addUniformField();
            EMWave2Frame.this.brightnessBar.setValue(225);
        }
        
        Setup createNext() {
            return new ApertureFieldSetup();
        }
    }
    
    class ApertureFieldSetup extends Setup
    {
        String getName() {
            return "Field Near Aperture";
        }
        
        void select() {
            EMWave2Frame.this.sourceChooser.select(0);
            final float n = 2.0f / EMWave2Frame.this.windowHeight;
            for (int i = 0; i != EMWave2Frame.this.gridSizeY; ++i) {
                EMWave2Frame.this.grid[EMWave2Frame.this.windowOffsetX + EMWave2Frame.this.gw * i].jz = n;
            }
            final int n2 = EMWave2Frame.this.gridSizeX / 2 - EMWave2Frame.this.windowWidth / 6;
            final int n3 = EMWave2Frame.this.gridSizeX / 2 + EMWave2Frame.this.windowWidth / 6;
            EMWave2Frame.this.conductDrawRect(n2, EMWave2Frame.this.windowOffsetY, n2, EMWave2Frame.this.windowOffsetY + EMWave2Frame.this.windowHeight - 1, 1.0);
            EMWave2Frame.this.conductDrawRect(n2, EMWave2Frame.this.gridSizeY / 2 - 6, n2, EMWave2Frame.this.gridSizeY / 2 + 6, 0.0);
            EMWave2Frame.this.brightnessBar.setValue(740);
        }
        
        Setup createNext() {
            return new SolenoidSetup();
        }
    }
    
    class SolenoidSetup extends Setup
    {
        String getName() {
            return "Solenoid";
        }
        
        void select() {
            EMWave2Frame.this.sourceChooser.select(0);
            final int n = EMWave2Frame.this.windowHeight / 3;
            final double n2 = 2.0 / n;
            final int n3 = EMWave2Frame.this.gridSizeY / 2;
            EMWave2Frame.this.addSolenoid(EMWave2Frame.this.gridSizeX / 2 - 3, n3 - n, EMWave2Frame.this.gridSizeX / 2 + 3, n3 + n, n2);
        }
        
        Setup createNext() {
            return new ToroidalSolenoidSetup();
        }
    }
    
    class ToroidalSolenoidSetup extends Setup
    {
        String getName() {
            return "Toroidal Solenoid";
        }
        
        void select() {
            EMWave2Frame.this.sourceChooser.select(0);
            EMWave2Frame.this.addWireCircle(EMWave2Frame.this.gridSizeX / 2, EMWave2Frame.this.gridSizeY / 2, EMWave2Frame.this.windowHeight / 3, -0.08333333333333333, 0, 360);
            EMWave2Frame.this.addWireCircle(EMWave2Frame.this.gridSizeX / 2, EMWave2Frame.this.gridSizeY / 2, EMWave2Frame.this.windowHeight / 6, 0.08333333333333333, 0, 360);
            EMWave2Frame.this.brightnessBar.setValue(400);
        }
        
        Setup createNext() {
            return new CylinderSetup();
        }
    }
    
    class CylinderSetup extends Setup
    {
        String getName() {
            return "Sphere";
        }
        
        void select() {
            EMWave2Frame.this.sourceChooser.select(0);
            final int n = 4;
            final int n2 = EMWave2Frame.this.gridSizeX / 2 * n;
            final int n3 = EMWave2Frame.this.gridSizeY / 2 * n;
            final int n4 = EMWave2Frame.this.windowHeight / 5 * n;
            final double n5 = -1.0 / (n4 * n4);
            for (int i = -n4; i <= n4; ++i) {
                for (int n6 = (int)Math.sqrt(n4 * n4 - i * i), j = -n6; j <= n6; ++j) {
                    final OscElement oscElement = EMWave2Frame.this.grid[(i + n2) / n + EMWave2Frame.this.gw * ((j + n3) / n)];
                    oscElement.my += (float)n5;
                }
            }
            EMWave2Frame.this.brightnessBar.setValue(450);
        }
        
        Setup createNext() {
            return new ThickWireSetup();
        }
    }
    
    class ThickWireSetup extends Setup
    {
        String getName() {
            return "Thick Wire";
        }
        
        void select() {
            final int n = EMWave2Frame.this.windowWidth / 4;
            EMWave2Frame.this.addThickWire(EMWave2Frame.this.gridSizeX / 2, EMWave2Frame.this.gridSizeY / 2, n, 1.0 / (n * n));
            EMWave2Frame.this.sourceChooser.select(0);
        }
        
        Setup createNext() {
            return new HoleInWire1Setup();
        }
    }
    
    class HoleInWire1Setup extends Setup
    {
        String getName() {
            return "Hole In Wire 1";
        }
        
        void select() {
            final int n = EMWave2Frame.this.windowWidth / 3;
            double n2 = (int)(1.0 / (n * n) * 1024.0) / 1024.0;
            if (n2 == 0.0) {
                n2 = 9.765625E-4;
            }
            EMWave2Frame.this.addThickWire(EMWave2Frame.this.gridSizeX / 2, EMWave2Frame.this.gridSizeY / 2, n, n2);
            EMWave2Frame.this.addThickWire(EMWave2Frame.this.gridSizeX / 2, EMWave2Frame.this.gridSizeY / 2, n * 2 / 3, -n2);
            EMWave2Frame.this.sourceChooser.select(0);
            EMWave2Frame.this.brightnessBar.setValue(450);
        }
        
        Setup createNext() {
            return new HoleInWire2Setup();
        }
    }
    
    class HoleInWire2Setup extends Setup
    {
        String getName() {
            return "Hole In Wire 2";
        }
        
        void select() {
            final int n = EMWave2Frame.this.windowWidth / 3;
            double n2 = (int)(1.0 / (n * n) * 1024.0) / 1024.0;
            if (n2 == 0.0) {
                n2 = 9.765625E-4;
            }
            EMWave2Frame.this.addThickWire(EMWave2Frame.this.gridSizeX / 2, EMWave2Frame.this.gridSizeY / 2, n, n2);
            EMWave2Frame.this.addThickWire(EMWave2Frame.this.gridSizeX / 2 + n / 4, EMWave2Frame.this.gridSizeY / 2, n / 2, -n2);
            EMWave2Frame.this.sourceChooser.select(0);
            EMWave2Frame.this.brightnessBar.setValue(450);
        }
        
        Setup createNext() {
            return new FerromagnetSetup();
        }
    }
    
    class FerromagnetSetup extends Setup
    {
        String getName() {
            return "Ferromagnet";
        }
        
        void select() {
            EMWave2Frame.this.sourceChooser.select(0);
            EMWave2Frame.this.addSolenoid(EMWave2Frame.this.gridSizeX / 2 - 2, EMWave2Frame.this.gridSizeY / 2 - 2, EMWave2Frame.this.gridSizeX / 2 + 2, EMWave2Frame.this.gridSizeY / 2 + 2, 0.4);
            EMWave2Frame.this.permFillRect(EMWave2Frame.this.windowOffsetX + 3, EMWave2Frame.this.gridSizeY / 2 + 4, EMWave2Frame.this.windowOffsetX + EMWave2Frame.this.windowWidth - 4, EMWave2Frame.this.gridSizeY / 2 + 8, 5.0);
            EMWave2Frame.this.brightnessBar.setValue(200);
        }
        
        Setup createNext() {
            return new DiamagnetSetup();
        }
    }
    
    class DiamagnetSetup extends Setup
    {
        String getName() {
            return "Diamagnet";
        }
        
        void select() {
            EMWave2Frame.this.sourceChooser.select(0);
            EMWave2Frame.this.addSolenoid(EMWave2Frame.this.gridSizeX / 2 - 2, EMWave2Frame.this.gridSizeY / 2 - 2, EMWave2Frame.this.gridSizeX / 2 + 2, EMWave2Frame.this.gridSizeY / 2 + 2, 0.4);
            EMWave2Frame.this.permFillRect(EMWave2Frame.this.windowOffsetX + 3, EMWave2Frame.this.gridSizeY / 2 + 4, EMWave2Frame.this.windowOffsetX + EMWave2Frame.this.windowWidth - 4, EMWave2Frame.this.gridSizeY / 2 + 8, 0.5);
            EMWave2Frame.this.brightnessBar.setValue(200);
        }
        
        Setup createNext() {
            return new MeissnerEffectSetup();
        }
    }
    
    class MeissnerEffectSetup extends Setup
    {
        String getName() {
            return "Meissner Effect";
        }
        
        void select() {
            EMWave2Frame.this.sourceChooser.select(0);
            EMWave2Frame.this.addSolenoid(EMWave2Frame.this.gridSizeX / 2 - 2, EMWave2Frame.this.gridSizeY / 2 - 2, EMWave2Frame.this.gridSizeX / 2 + 2, EMWave2Frame.this.gridSizeY / 2 + 2, 0.4);
            EMWave2Frame.this.conductFillRect(EMWave2Frame.this.windowOffsetX + 3, EMWave2Frame.this.gridSizeY / 2 + 4, EMWave2Frame.this.windowOffsetX + EMWave2Frame.this.windowWidth - 4, EMWave2Frame.this.gridSizeY / 2 + 8, 1.0);
            EMWave2Frame.this.brightnessBar.setValue(200);
        }
        
        Setup createNext() {
            return new HorseshoeSetup();
        }
    }
    
    class HorseshoeSetup extends Setup
    {
        String getName() {
            return "Horseshoe Magnet";
        }
        
        void select() {
            EMWave2Frame.this.sourceChooser.select(0);
            final int n = EMWave2Frame.this.windowHeight / 3;
            final int n2 = EMWave2Frame.this.windowHeight / 6;
            EMWave2Frame.this.addWireCircle(EMWave2Frame.this.gridSizeX / 2, EMWave2Frame.this.gridSizeY / 2, n, -0.08333333333333333, 0, 180);
            EMWave2Frame.this.addWireCircle(EMWave2Frame.this.gridSizeX / 2, EMWave2Frame.this.gridSizeY / 2, n2, 0.08333333333333333, 0, 180);
            for (int i = 0; i != n2; ++i) {
                for (int j = -n; j <= n; ++j) {
                    EMWave2Frame.this.grid[EMWave2Frame.this.gridSizeX / 2 + j + EMWave2Frame.this.gw * (EMWave2Frame.this.gridSizeY / 2 + i)].jz = EMWave2Frame.this.grid[EMWave2Frame.this.gridSizeX / 2 + j + EMWave2Frame.this.gw * (EMWave2Frame.this.gridSizeY / 2 + i - 1)].jz;
                }
            }
            EMWave2Frame.this.brightnessBar.setValue(400);
        }
        
        Setup createNext() {
            return new Horseshoe2Setup();
        }
    }
    
    class Horseshoe2Setup extends HorseshoeSetup
    {
        String getName() {
            return "Horseshoe + Load";
        }
        
        void select() {
            super.select();
            final int n = EMWave2Frame.this.windowHeight / 3;
            final int n2 = EMWave2Frame.this.windowHeight / 6;
            EMWave2Frame.this.permFillRect(EMWave2Frame.this.gridSizeX / 2 - n - 3, EMWave2Frame.this.gridSizeY / 2 + n2, EMWave2Frame.this.gridSizeX / 2 + n + 3, EMWave2Frame.this.gridSizeY / 2 + n2 * 2, 5.0);
            EMWave2Frame.this.brightnessBar.setValue(225);
        }
        
        Setup createNext() {
            return new MagneticShielding1Setup();
        }
    }
    
    class MagneticShielding1Setup extends Setup
    {
        String getName() {
            return "Magnetic Shielding 1";
        }
        
        void select() {
            EMWave2Frame.this.sourceChooser.select(0);
            EMWave2Frame.this.addSolenoid(EMWave2Frame.this.gridSizeX / 2 - 2, EMWave2Frame.this.gridSizeY / 2 - 2, EMWave2Frame.this.gridSizeX / 2 + 2, EMWave2Frame.this.gridSizeY / 2 + 2, 0.4);
            EMWave2Frame.this.permDrawRect(EMWave2Frame.this.windowOffsetX + 3, EMWave2Frame.this.gridSizeY / 2 + 4, EMWave2Frame.this.windowOffsetX + EMWave2Frame.this.windowWidth - 4, EMWave2Frame.this.gridSizeY / 2 + 5, 10.0);
        }
        
        Setup createNext() {
            return new MagneticShielding2Setup();
        }
    }
    
    class MagneticShielding2Setup extends Setup
    {
        String getName() {
            return "Magnetic Shielding 2";
        }
        
        void select() {
            EMWave2Frame.this.sourceChooser.select(0);
            EMWave2Frame.this.addSolenoid(EMWave2Frame.this.gridSizeX / 2 - 2, EMWave2Frame.this.gridSizeY / 2 - 2, EMWave2Frame.this.gridSizeX / 2 + 2, EMWave2Frame.this.gridSizeY / 2 + 2, 0.4);
            for (int i = 6; i <= 8; ++i) {
                EMWave2Frame.this.permDrawRect(EMWave2Frame.this.gridSizeX / 2 - i, EMWave2Frame.this.gridSizeY / 2 - i, EMWave2Frame.this.gridSizeX / 2 + i, EMWave2Frame.this.gridSizeY / 2 + i, 10.0);
            }
        }
        
        Setup createNext() {
            return new MagneticShielding3Setup();
        }
    }
    
    class MagneticShielding3Setup extends Setup
    {
        String getName() {
            return "Magnetic Shielding 3";
        }
        
        void select() {
            EMWave2Frame.this.sourceChooser.select(0);
            final int n = EMWave2Frame.this.gridSizeX / 2;
            final int n2 = EMWave2Frame.this.gridSizeY / 2;
            EMWave2Frame.this.addSolenoid(n - 1, n2 - 1, n + 1, n2 + 1, 4.0);
            EMWave2Frame.this.brightnessBar.setValue(340);
            for (int i = 0; i != 360; i += 3) {
                final double n3 = 4.9;
                EMWave2Frame.this.addPerm(n + (int)(Math.cos(i * 3.141592653589793 / 180.0) * n3), n2 - (int)(Math.sin(i * 3.141592653589793 / 180.0) * n3), 5.0);
                final double n4 = 5.9;
                EMWave2Frame.this.addPerm(n + (int)(Math.cos(i * 3.141592653589793 / 180.0) * n4), n2 - (int)(Math.sin(i * 3.141592653589793 / 180.0) * n4), 5.0);
            }
        }
        
        Setup createNext() {
            return new MagneticShielding4Setup();
        }
    }
    
    class MagneticShielding4Setup extends Setup
    {
        String getName() {
            return "Magnetic Shielding 4";
        }
        
        void select() {
            EMWave2Frame.this.sourceChooser.select(0);
            for (int i = 6; i <= 8; ++i) {
                EMWave2Frame.this.permDrawRect(EMWave2Frame.this.gridSizeX / 2 - i, EMWave2Frame.this.gridSizeY / 2 - i, EMWave2Frame.this.gridSizeX / 2 + i, EMWave2Frame.this.gridSizeY / 2 + i, 10.0);
            }
            EMWave2Frame.this.addUniformField();
            EMWave2Frame.this.brightnessBar.setValue(250);
        }
        
        Setup createNext() {
            return new MagneticCircuit1Setup();
        }
    }
    
    class MagneticCircuit1Setup extends Setup
    {
        String getName() {
            return "Magnetic Circuit 1";
        }
        
        void select() {
            EMWave2Frame.this.sourceChooser.select(0);
            for (int i = 6; i <= 9; ++i) {
                EMWave2Frame.this.permDrawRect(EMWave2Frame.this.gridSizeX / 2 - i, EMWave2Frame.this.gridSizeY / 2 - i, EMWave2Frame.this.gridSizeX / 2 + i, EMWave2Frame.this.gridSizeY / 2 + i, 10.0);
            }
            EMWave2Frame.this.addSolenoid(EMWave2Frame.this.gridSizeX / 2 + 5, EMWave2Frame.this.gridSizeY / 2 - 1, EMWave2Frame.this.gridSizeX / 2 + 10, EMWave2Frame.this.gridSizeY / 2 + 1, 0.2);
        }
        
        Setup createNext() {
            return new MagneticCircuit2Setup();
        }
    }
    
    class MagneticCircuit2Setup extends MagneticCircuit1Setup
    {
        String getName() {
            return "Magnetic Circuit 2";
        }
        
        void select() {
            super.select();
            EMWave2Frame.this.permFillRect(EMWave2Frame.this.gridSizeX / 2 - 9, EMWave2Frame.this.gridSizeY / 2 - 1, EMWave2Frame.this.gridSizeX / 2 - 6, EMWave2Frame.this.gridSizeY / 2 + 1, 1.0);
        }
        
        Setup createNext() {
            return new MonopoleAttemptSetup();
        }
    }
    
    class MonopoleAttemptSetup extends Setup
    {
        String getName() {
            return "Monopole Attempt";
        }
        
        void select() {
            EMWave2Frame.this.sourceChooser.select(0);
            int n = EMWave2Frame.this.windowWidth / 5;
            final int n2 = EMWave2Frame.this.gridSizeX / 2;
            final int n3 = EMWave2Frame.this.gridSizeY / 2;
            for (int i = 0; i != 3; ++i) {
                for (int j = -n + 1; j < n; ++j) {
                    EMWave2Frame.this.grid[n2 - n + EMWave2Frame.this.gw * (n3 + j)].mx = -1.0f;
                    EMWave2Frame.this.grid[n2 + n + EMWave2Frame.this.gw * (n3 + j)].mx = 1.0f;
                    EMWave2Frame.this.grid[n2 + j + EMWave2Frame.this.gw * (n3 - n)].my = -1.0f;
                    EMWave2Frame.this.grid[n2 + j + EMWave2Frame.this.gw * (n3 + n)].my = 1.0f;
                }
                ++n;
            }
        }
        
        Setup createNext() {
            return new QuadrupoleLensSetup();
        }
    }
    
    class QuadrupoleLensSetup extends Setup
    {
        String getName() {
            return "Quadrupole Lens";
        }
        
        void select() {
            EMWave2Frame.this.sourceChooser.select(0);
            final int n = EMWave2Frame.this.gridSizeX / 2 - 1;
            final int n2 = EMWave2Frame.this.windowWidth / 4;
            final int n3 = EMWave2Frame.this.gridSizeX / 2;
            final int n4 = EMWave2Frame.this.gridSizeY / 2;
            final float n5 = 1.0f / n2;
            for (int i = -n; i <= n; ++i) {
                for (int j = (int)Math.sqrt(i * i + n2 * n2); j <= n; ++j) {
                    EMWave2Frame.this.grid[n3 + i + EMWave2Frame.this.gw * (n4 + j)].my = -n5;
                    EMWave2Frame.this.grid[n3 + i + EMWave2Frame.this.gw * (n4 - j)].my = n5;
                    EMWave2Frame.this.grid[n3 + j + EMWave2Frame.this.gw * (n4 + i)].mx = n5;
                    EMWave2Frame.this.grid[n3 - j + EMWave2Frame.this.gw * (n4 + i)].mx = -n5;
                }
            }
        }
        
        Setup createNext() {
            return new HalbachArraySetup();
        }
    }
    
    class HalbachArraySetup extends Setup
    {
        String getName() {
            return "Halbach Array";
        }
        
        void select() {
            EMWave2Frame.this.brightnessBar.setValue(80);
            EMWave2Frame.this.sourceChooser.select(0);
            final int n = 5;
            final int n2 = EMWave2Frame.this.gridSizeY / 2 - n / 2;
            final int n3 = EMWave2Frame.this.gridSizeY / 2 + n / 2;
            final int n4 = EMWave2Frame.this.gridSizeX / 2 - n / 2 - 2 * n;
            final int n5 = n - 1;
            EMWave2Frame.this.addMagnet(n4, n2, n4 + n5, n3, -0.2, 0.0);
            final int n6 = n4 + n;
            EMWave2Frame.this.addMagnet(n6, n2, n6 + n5, n3, 0.0, -0.2);
            final int n7 = n6 + n;
            EMWave2Frame.this.addMagnet(n7, n2, n7 + n5, n3, 0.2, 0.0);
            final int n8 = n7 + n;
            EMWave2Frame.this.addMagnet(n8, n2, n8 + n5, n3, 0.0, 0.2);
            final int n9 = n8 + n;
            EMWave2Frame.this.addMagnet(n9, n2, n9 + n5, n3, -0.2, 0.0);
        }
        
        Setup createNext() {
            return new HalbachArray2Setup();
        }
    }
    
    class HalbachArray2Setup extends Setup
    {
        String getName() {
            return "Halbach Array (long)";
        }
        
        void select() {
            EMWave2Frame.this.brightnessBar.setValue(80);
            EMWave2Frame.this.sourceChooser.select(0);
            final int n = 3;
            final int n2 = EMWave2Frame.this.gridSizeY / 2 - 1;
            final int n3 = EMWave2Frame.this.gridSizeY / 2 + 1;
            int n4 = EMWave2Frame.this.windowOffsetX + (EMWave2Frame.this.windowWidth - EMWave2Frame.this.windowWidth / n * n) / 2;
            final int n5 = n - 1;
            double n6 = -0.2;
            double n7 = 0.0;
            for (int i = 0; i != EMWave2Frame.this.windowWidth / n; ++i) {
                EMWave2Frame.this.addMagnet(n4, n2, n4 + n5, n3, n6, n7);
                n4 += n;
                final double n8 = n6;
                n6 = -n7;
                n7 = n8;
            }
        }
        
        Setup createNext() {
            return new HalbachArray3Setup();
        }
    }
    
    class HalbachArray3Setup extends Setup
    {
        String getName() {
            return "Halbach Array (dipole)";
        }
        
        void select() {
            EMWave2Frame.this.brightnessBar.setValue(47);
            EMWave2Frame.this.sourceChooser.select(0);
            final int n = EMWave2Frame.this.windowWidth / 3;
            final int n2 = n / 2;
            for (int i = -n; i <= n; ++i) {
                for (int j = -n; j <= n; ++j) {
                    final double sqrt = Math.sqrt(i * i + j * j);
                    if (sqrt <= n + 0.9) {
                        if (sqrt >= n2) {
                            double n3 = Math.atan2(j, i) * 180.0 / 3.141592653589793 + 22.5 + 45.0;
                            if (n3 < 0.0) {
                                n3 += 360.0;
                            }
                            final int n4 = (int)(n3 / 45.0);
                            final float n5 = ((n4 & 0x2) == 0x0) ? 0.2f : -0.2f;
                            float mx = 0.0f;
                            float my = 0.0f;
                            if ((n4 & 0x1) == 0x0) {
                                mx = n5;
                            }
                            else {
                                my = n5;
                            }
                            EMWave2Frame.this.grid[EMWave2Frame.this.gridSizeX / 2 + i + EMWave2Frame.this.gw * (EMWave2Frame.this.gridSizeY / 2 + j)].mx = mx;
                            EMWave2Frame.this.grid[EMWave2Frame.this.gridSizeX / 2 + i + EMWave2Frame.this.gw * (EMWave2Frame.this.gridSizeY / 2 + j)].my = my;
                        }
                    }
                }
            }
        }
        
        Setup createNext() {
            return new HalbachArray4Setup();
        }
    }
    
    class HalbachArray4Setup extends Setup
    {
        String getName() {
            return "Halbach Array (quadrupole)";
        }
        
        void select() {
            EMWave2Frame.this.brightnessBar.setValue(255);
            EMWave2Frame.this.sourceChooser.select(0);
            final int n = EMWave2Frame.this.windowWidth / 3;
            final int n2 = n * 2 / 3;
            for (int i = -n; i <= n; ++i) {
                for (int j = -n; j <= n; ++j) {
                    final double sqrt = Math.sqrt(i * i + j * j);
                    if (sqrt <= n + 0.9) {
                        if (sqrt >= n2) {
                            double n3 = Math.atan2(j, i) * 180.0 / 3.141592653589793 + 11.25;
                            if (n3 < 0.0) {
                                n3 += 360.0;
                            }
                            final double n4 = -1.5707963267948966 + 4.71238898038469 * (int)(n3 / 22.5) / 4.0;
                            final float n5 = (float)Math.cos(n4);
                            final float n6 = (float)Math.sin(n4);
                            final float n7 = -0.06f;
                            EMWave2Frame.this.grid[EMWave2Frame.this.gridSizeX / 2 + i + EMWave2Frame.this.gw * (EMWave2Frame.this.gridSizeY / 2 + j)].mx = n5 * n7;
                            EMWave2Frame.this.grid[EMWave2Frame.this.gridSizeX / 2 + i + EMWave2Frame.this.gw * (EMWave2Frame.this.gridSizeY / 2 + j)].my = n6 * n7;
                        }
                    }
                }
            }
        }
        
        Setup createNext() {
            return new DielectricSetup();
        }
    }
    
    class DielectricSetup extends Setup
    {
        String getName() {
            return "Dielectric";
        }
        
        void select() {
            EMWave2Frame.this.sourceChooser.select(7);
            EMWave2Frame.this.addMedium();
            EMWave2Frame.this.setForceBar(4);
            EMWave2Frame.this.brightnessBar.setValue(1000);
            EMWave2Frame.this.noFilter();
        }
        
        Setup createNext() {
            return new ConductReflectSetup();
        }
    }
    
    class ConductReflectSetup extends Setup
    {
        String getName() {
            return "Fair Conductor Reflection";
        }
        
        void select() {
            EMWave2Frame.this.sourceChooser.select(7);
            EMWave2Frame.this.addCondMedium(0.5);
            EMWave2Frame.this.setForceBar(4);
            EMWave2Frame.this.brightnessBar.setValue(800);
        }
        
        Setup createNext() {
            return new Conduct2ReflectSetup();
        }
    }
    
    class Conduct2ReflectSetup extends Setup
    {
        String getName() {
            return "Poor Conductor Reflection";
        }
        
        void select() {
            EMWave2Frame.this.sourceChooser.select(7);
            EMWave2Frame.this.addCondMedium(0.1);
            EMWave2Frame.this.setForceBar(4);
            EMWave2Frame.this.brightnessBar.setValue(800);
        }
        
        Setup createNext() {
            return new SkinEffect1Setup();
        }
    }
    
    class SkinEffect1Setup extends Setup
    {
        String getName() {
            return "Skin Effect 1";
        }
        
        void select() {
            EMWave2Frame.this.sourceChooser.select(1);
            EMWave2Frame.this.addCondMedium(0.33);
            EMWave2Frame.this.setForceBar(6);
            EMWave2Frame.this.brightnessBar.setValue(800);
        }
        
        Setup createNext() {
            return new SkinEffect2Setup();
        }
    }
    
    class SkinEffect2Setup extends Setup
    {
        String getName() {
            return "Skin Effect 2";
        }
        
        void select() {
            EMWave2Frame.this.sourceChooser.select(1);
            EMWave2Frame.this.addCondMedium(0.33);
            EMWave2Frame.this.setForceBar(40);
            EMWave2Frame.this.brightnessBar.setValue(800);
        }
        
        Setup createNext() {
            return new ResonantAbsSetup();
        }
    }
    
    class ResonantAbsSetup extends Setup
    {
        String getName() {
            return "Resonant Absorption";
        }
        
        void select() {
            EMWave2Frame.this.addResMedium();
            EMWave2Frame.this.setForceBar(23);
            EMWave2Frame.this.brightnessBar.setValue(200);
            EMWave2Frame.this.noFilter();
        }
        
        Setup createNext() {
            return new Dispersion1Setup();
        }
    }
    
    class Dispersion1Setup extends ResonantAbsSetup
    {
        String getName() {
            return "Dispersion 1";
        }
        
        void select() {
            super.select();
            EMWave2Frame.this.setForceBar(14);
        }
        
        Setup createNext() {
            return new Dispersion2Setup();
        }
    }
    
    class Dispersion2Setup extends ResonantAbsSetup
    {
        String getName() {
            return "Dispersion 2";
        }
        
        void select() {
            super.select();
            EMWave2Frame.this.setForceBar(21);
        }
        
        Setup createNext() {
            return new Dispersion3Setup();
        }
    }
    
    class Dispersion3Setup extends ResonantAbsSetup
    {
        String getName() {
            return "Dispersion 3";
        }
        
        void select() {
            super.select();
            EMWave2Frame.this.setForceBar(25);
        }
        
        Setup createNext() {
            return new Dispersion4Setup();
        }
    }
    
    class Dispersion4Setup extends ResonantAbsSetup
    {
        String getName() {
            return "Dispersion 4";
        }
        
        void select() {
            super.select();
            EMWave2Frame.this.setForceBar(39);
        }
        
        Setup createNext() {
            return new DiffusionSetup();
        }
    }
    
    class DiffusionSetup extends Setup
    {
        String getName() {
            return "Magnetic Diffusion";
        }
        
        void select() {
            EMWave2Frame.this.sourceChooser.select(0);
            EMWave2Frame.this.conductFillRect(1, 1, EMWave2Frame.this.gridSizeX - 2, EMWave2Frame.this.gridSizeY - 2, 0.2);
            EMWave2Frame.this.addConductor(EMWave2Frame.this.gridSizeX / 2, EMWave2Frame.this.gridSizeY / 2, 0.0);
            EMWave2Frame.this.grid[EMWave2Frame.this.gridSizeX / 2 + EMWave2Frame.this.gw * (EMWave2Frame.this.gridSizeY / 2)].jz = 1.0;
            EMWave2Frame.this.brightnessBar.setValue(800);
        }
        
        Setup createNext() {
            return new OscRingSetup();
        }
    }
    
    class OscRingSetup extends Setup
    {
        String getName() {
            return "Oscillating Ring";
        }
        
        void doSetupSources() {
            EMWave2Frame.this.sourceChooser.select(3);
            EMWave2Frame.this.setSources();
            final OscSource oscSource = EMWave2Frame.this.sources[0];
            final OscSource oscSource2 = EMWave2Frame.this.sources[1];
            final int n = EMWave2Frame.this.gridSizeY / 2;
            oscSource2.y = n;
            oscSource.y = n;
            EMWave2Frame.this.sources[0].x = EMWave2Frame.this.gridSizeX / 2 - 4;
            EMWave2Frame.this.sources[1].x = EMWave2Frame.this.gridSizeX / 2 + 4;
            EMWave2Frame.this.auxBar.setValue(40);
        }
        
        void select() {
            EMWave2Frame.this.setForceBar(26);
            EMWave2Frame.this.brightnessBar.setValue(86);
        }
        
        Setup createNext() {
            return new OscRingPairSetup();
        }
    }
    
    class OscRingPairSetup extends Setup
    {
        String getName() {
            return "Oscillating Ring Pair";
        }
        
        void doSetupSources() {
            EMWave2Frame.this.sourceChooser.select(6);
            EMWave2Frame.this.setSources();
            final OscSource oscSource = EMWave2Frame.this.sources[0];
            final OscSource oscSource2 = EMWave2Frame.this.sources[1];
            final int n = EMWave2Frame.this.gridSizeY / 2 - 2;
            oscSource2.y = n;
            oscSource.y = n;
            final OscSource oscSource3 = EMWave2Frame.this.sources[2];
            final OscSource oscSource4 = EMWave2Frame.this.sources[3];
            final int n2 = EMWave2Frame.this.gridSizeY / 2 + 2;
            oscSource4.y = n2;
            oscSource3.y = n2;
            final OscSource oscSource5 = EMWave2Frame.this.sources[0];
            final OscSource oscSource6 = EMWave2Frame.this.sources[3];
            final int n3 = EMWave2Frame.this.gridSizeX / 2 - 2;
            oscSource6.x = n3;
            oscSource5.x = n3;
            final OscSource oscSource7 = EMWave2Frame.this.sources[1];
            final OscSource oscSource8 = EMWave2Frame.this.sources[2];
            final int n4 = EMWave2Frame.this.gridSizeX / 2 + 2;
            oscSource8.x = n4;
            oscSource7.x = n4;
            EMWave2Frame.this.auxBar.setValue(40);
        }
        
        void select() {
            EMWave2Frame.this.setForceBar(26);
            EMWave2Frame.this.brightnessBar.setValue(86);
        }
        
        Setup createNext() {
            return new OscRingInductionSetup();
        }
    }
    
    class OscRingInductionSetup extends Setup
    {
        String getName() {
            return "Ring Induction";
        }
        
        void doSetupSources() {
            EMWave2Frame.this.sourceChooser.select(3);
            EMWave2Frame.this.setSources();
            final OscSource oscSource = EMWave2Frame.this.sources[0];
            final OscSource oscSource2 = EMWave2Frame.this.sources[1];
            final int n = EMWave2Frame.this.gridSizeY / 2 - 2;
            oscSource2.y = n;
            oscSource.y = n;
            EMWave2Frame.this.sources[0].x = EMWave2Frame.this.gridSizeX / 2 - 4;
            EMWave2Frame.this.sources[1].x = EMWave2Frame.this.gridSizeX / 2 + 4;
            EMWave2Frame.this.auxBar.setValue(40);
        }
        
        void select() {
            EMWave2Frame.this.setForceBar(12);
            EMWave2Frame.this.brightnessBar.setValue(140);
            EMWave2Frame.this.addConductor(EMWave2Frame.this.gridSizeX / 2 - 4, EMWave2Frame.this.gridSizeY / 2 + 2, 0.5);
            EMWave2Frame.this.addConductor(EMWave2Frame.this.gridSizeX / 2 + 4, EMWave2Frame.this.gridSizeY / 2 + 2, 0.5);
        }
        
        Setup createNext() {
            return new WireInductionSetup();
        }
    }
    
    class WireInductionSetup extends Setup
    {
        String getName() {
            return "Wire Induction";
        }
        
        void doSetupSources() {
            EMWave2Frame.this.setForceBar(12);
            EMWave2Frame.this.sourceChooser.select(1);
            EMWave2Frame.this.setSources();
            EMWave2Frame.this.sources[0].y = EMWave2Frame.this.gridSizeY / 2 - 2;
            EMWave2Frame.this.sources[0].x = EMWave2Frame.this.gridSizeX / 2;
        }
        
        void select() {
            EMWave2Frame.this.brightnessBar.setValue(140);
            EMWave2Frame.this.addConductor(EMWave2Frame.this.gridSizeX / 2, EMWave2Frame.this.gridSizeY / 2 + 2, 0.5);
        }
        
        Setup createNext() {
            return new OscRingEddy1Setup();
        }
    }
    
    class OscRingEddy1Setup extends OscRingSetup
    {
        String getName() {
            return "Ring + Fair Conductor";
        }
        
        void select() {
            EMWave2Frame.this.brightnessBar.setValue(280);
            EMWave2Frame.this.setForceBar(3);
            EMWave2Frame.this.conductFillRect(0, EMWave2Frame.this.gridSizeY / 2 + 3, EMWave2Frame.this.gridSizeX - 1, EMWave2Frame.this.gridSizeY / 2 + 5, 0.5);
        }
        
        Setup createNext() {
            return new OscRingEddy2Setup();
        }
    }
    
    class OscRingEddy2Setup extends OscRingSetup
    {
        String getName() {
            return "Ring + Poor Conductor";
        }
        
        void select() {
            EMWave2Frame.this.brightnessBar.setValue(280);
            EMWave2Frame.this.setForceBar(3);
            EMWave2Frame.this.conductFillRect(0, EMWave2Frame.this.gridSizeY / 2 + 3, EMWave2Frame.this.gridSizeX - 1, EMWave2Frame.this.gridSizeY / 2 + 5, 0.1);
        }
        
        Setup createNext() {
            return new WireEddy1Setup();
        }
    }
    
    class WireEddy1Setup extends Setup
    {
        String getName() {
            return "Wire + Fair Conductor";
        }
        
        void doSetupSources() {
            EMWave2Frame.this.setForceBar(3);
            EMWave2Frame.this.sourceChooser.select(1);
            EMWave2Frame.this.setSources();
            EMWave2Frame.this.sources[0].y = EMWave2Frame.this.gridSizeY / 2;
            EMWave2Frame.this.sources[0].x = EMWave2Frame.this.gridSizeX / 2;
        }
        
        void select() {
            EMWave2Frame.this.brightnessBar.setValue(280);
            EMWave2Frame.this.conductFillRect(0, EMWave2Frame.this.gridSizeY / 2 + 3, EMWave2Frame.this.gridSizeX - 1, EMWave2Frame.this.gridSizeY / 2 + 5, 0.5);
        }
        
        Setup createNext() {
            return new WireEddy2Setup();
        }
    }
    
    class WireEddy2Setup extends WireEddy1Setup
    {
        String getName() {
            return "Wire + Poor Conductor";
        }
        
        void select() {
            EMWave2Frame.this.brightnessBar.setValue(280);
            EMWave2Frame.this.conductFillRect(0, EMWave2Frame.this.gridSizeY / 2 + 3, EMWave2Frame.this.gridSizeX - 1, EMWave2Frame.this.gridSizeY / 2 + 5, 0.1);
        }
        
        Setup createNext() {
            return new OscRingPermSetup();
        }
    }
    
    class OscRingPermSetup extends Setup
    {
        String getName() {
            return "Rings + Ferromagnet";
        }
        
        void doSetupSources() {
            EMWave2Frame.this.sourceChooser.select(3);
            EMWave2Frame.this.setSources();
            final OscSource oscSource = EMWave2Frame.this.sources[0];
            final OscSource oscSource2 = EMWave2Frame.this.sources[1];
            final int n = EMWave2Frame.this.gridSizeY / 2;
            oscSource2.y = n;
            oscSource.y = n;
            EMWave2Frame.this.sources[0].x = EMWave2Frame.this.gridSizeX / 2 - 4;
            EMWave2Frame.this.sources[1].x = EMWave2Frame.this.gridSizeX / 2 + 4;
            EMWave2Frame.this.auxBar.setValue(40);
        }
        
        void select() {
            EMWave2Frame.this.setForceBar(6);
            EMWave2Frame.this.brightnessBar.setValue(94);
            EMWave2Frame.this.addConductor(EMWave2Frame.this.gridSizeX / 2 - 4, EMWave2Frame.this.gridSizeY / 2 + 10, 0.5);
            EMWave2Frame.this.addConductor(EMWave2Frame.this.gridSizeX / 2 + 4, EMWave2Frame.this.gridSizeY / 2 + 10, 0.5);
            EMWave2Frame.this.addConductor(EMWave2Frame.this.gridSizeX / 2 - 4, EMWave2Frame.this.gridSizeY / 2 - 10, 0.5);
            EMWave2Frame.this.addConductor(EMWave2Frame.this.gridSizeX / 2 + 4, EMWave2Frame.this.gridSizeY / 2 - 10, 0.5);
            EMWave2Frame.this.permFillRect(EMWave2Frame.this.gridSizeX / 2 - 2, EMWave2Frame.this.gridSizeY / 2 - 1, EMWave2Frame.this.gridSizeX / 2 + 2, EMWave2Frame.this.windowOffsetY + EMWave2Frame.this.windowHeight - 1, 50.0);
            EMWave2Frame.this.conductFillRect(EMWave2Frame.this.gridSizeX / 2 - 2, EMWave2Frame.this.gridSizeY / 2 - 1, EMWave2Frame.this.gridSizeX / 2 + 2, EMWave2Frame.this.windowOffsetY + EMWave2Frame.this.windowHeight - 1, 0.05);
        }
        
        Setup createNext() {
            return new SolenoidOscSetup();
        }
    }
    
    class SolenoidOscSetup extends Setup
    {
        String getName() {
            return "Osc. Solenoid";
        }
        
        void select() {
            EMWave2Frame.this.sourceChooser.select(10);
            EMWave2Frame.this.setSources();
            final int n = EMWave2Frame.this.windowHeight / 3;
            final int n2 = EMWave2Frame.this.gridSizeY / 2;
            final OscSource oscSource = EMWave2Frame.this.sources[0];
            final OscSource oscSource2 = EMWave2Frame.this.sources[1];
            final int n3 = EMWave2Frame.this.gridSizeX / 2 - 3;
            oscSource2.x = n3;
            oscSource.x = n3;
            final OscSource oscSource3 = EMWave2Frame.this.sources[2];
            final OscSource oscSource4 = EMWave2Frame.this.sources[3];
            final int n4 = EMWave2Frame.this.gridSizeX / 2 + 3;
            oscSource4.x = n4;
            oscSource3.x = n4;
            final OscSource oscSource5 = EMWave2Frame.this.sources[0];
            final OscSource oscSource6 = EMWave2Frame.this.sources[2];
            final int n5 = n2 - n;
            oscSource6.y = n5;
            oscSource5.y = n5;
            final OscSource oscSource7 = EMWave2Frame.this.sources[1];
            final OscSource oscSource8 = EMWave2Frame.this.sources[3];
            final int n6 = n2 + n;
            oscSource8.y = n6;
            oscSource7.y = n6;
            EMWave2Frame.this.auxBar.setValue(40);
            EMWave2Frame.this.setForceBar(9);
        }
        
        void doSetupSources() {
        }
        
        Setup createNext() {
            return new TransformerSetup();
        }
    }
    
    class TransformerSetup extends SolenoidOscSetup
    {
        String getName() {
            return "Transformer";
        }
        
        void select() {
            super.select();
            final int n = EMWave2Frame.this.windowHeight / 3;
            final int n2 = EMWave2Frame.this.gridSizeY / 2;
            EMWave2Frame.this.conductDrawRect(EMWave2Frame.this.gridSizeX / 2 - 5, n2 - n, EMWave2Frame.this.gridSizeX / 2 - 5, n2 + n, 0.9);
            EMWave2Frame.this.conductDrawRect(EMWave2Frame.this.gridSizeX / 2 + 5, n2 - n, EMWave2Frame.this.gridSizeX / 2 + 5, n2 + n, 0.9);
            EMWave2Frame.this.brightnessBar.setValue(340);
        }
        
        Setup createNext() {
            return new ToroidalSolenoidOscSetup();
        }
    }
    
    class ToroidalSolenoidOscSetup extends Setup
    {
        String getName() {
            return "Osc Toroidal Solenoid";
        }
        
        void select() {
            EMWave2Frame.this.setSources();
            EMWave2Frame.this.sources[0].x = EMWave2Frame.this.windowOffsetX;
            EMWave2Frame.this.sources[0].y = EMWave2Frame.this.windowOffsetY;
            EMWave2Frame.this.sourceChooser.select(0);
            EMWave2Frame.this.brightnessBar.setValue(300);
            EMWave2Frame.this.setForceBar(8);
        }
        
        void doSetupSources() {
        }
        
        void doStep() {
            final double n = EMWave2Frame.this.grid[EMWave2Frame.this.windowOffsetX + EMWave2Frame.this.gw * EMWave2Frame.this.windowOffsetY].jz * 30.0;
            for (int i = 0; i != EMWave2Frame.this.windowWidth; ++i) {
                for (int j = 0; j != EMWave2Frame.this.windowHeight; ++j) {
                    EMWave2Frame.this.grid[i + EMWave2Frame.this.windowOffsetX + EMWave2Frame.this.gw * (j + EMWave2Frame.this.windowOffsetY)].jz = 0.0;
                }
            }
            EMWave2Frame.this.addWireCircle(EMWave2Frame.this.gridSizeX / 2, EMWave2Frame.this.gridSizeY / 2, EMWave2Frame.this.windowHeight / 3, -n / 360.0, 0, 360);
            EMWave2Frame.this.addWireCircle(EMWave2Frame.this.gridSizeX / 2, EMWave2Frame.this.gridSizeY / 2, EMWave2Frame.this.windowHeight / 6, n / 360.0, 0, 360);
        }
        
        Setup createNext() {
            return new CoaxCableSetup();
        }
    }
    
    class CoaxCableSetup extends Setup
    {
        String getName() {
            return "Coaxial Cable";
        }
        
        void select() {
            EMWave2Frame.this.setSources();
            EMWave2Frame.this.sources[0].x = EMWave2Frame.this.windowOffsetX;
            EMWave2Frame.this.sources[0].y = EMWave2Frame.this.windowOffsetY;
            EMWave2Frame.this.brightnessBar.setValue(300);
            EMWave2Frame.this.setForceBar(8);
        }
        
        void doSetupSources() {
        }
        
        void doStep() {
            final double n = EMWave2Frame.this.grid[EMWave2Frame.this.windowOffsetX + EMWave2Frame.this.gw * EMWave2Frame.this.windowOffsetY].jz * 30.0;
            for (int i = 0; i != EMWave2Frame.this.windowWidth; ++i) {
                for (int j = 0; j != EMWave2Frame.this.windowHeight; ++j) {
                    EMWave2Frame.this.grid[i + EMWave2Frame.this.windowOffsetX + EMWave2Frame.this.gw * (j + EMWave2Frame.this.windowOffsetY)].jz = 0.0;
                }
            }
            EMWave2Frame.this.addWireCircle(EMWave2Frame.this.gridSizeX / 2, EMWave2Frame.this.gridSizeY / 2, 3, -n / 360.0, 0, 360);
            EMWave2Frame.this.grid[EMWave2Frame.this.gridSizeX / 2 + EMWave2Frame.this.gw * (EMWave2Frame.this.gridSizeY / 2)].jz = n / 16.0;
        }
        
        Setup createNext() {
            return new CondInOscFieldSetup();
        }
    }
    
    class CondInOscFieldSetup extends Setup
    {
        String getName() {
            return "Cond. in Osc. Field";
        }
        
        void doSetupSources() {
        }
        
        void select() {
            EMWave2Frame.this.sourceChooser.select(10);
            EMWave2Frame.this.setSources();
            final int n = EMWave2Frame.this.windowHeight / 3;
            final int n2 = EMWave2Frame.this.gridSizeY / 2;
            final OscSource oscSource = EMWave2Frame.this.sources[0];
            final OscSource oscSource2 = EMWave2Frame.this.sources[1];
            final int windowOffsetX = EMWave2Frame.this.windowOffsetX;
            oscSource2.x = windowOffsetX;
            oscSource.x = windowOffsetX;
            final OscSource oscSource3 = EMWave2Frame.this.sources[2];
            final OscSource oscSource4 = EMWave2Frame.this.sources[3];
            final int n3 = EMWave2Frame.this.windowOffsetX + EMWave2Frame.this.windowWidth - 1;
            oscSource4.x = n3;
            oscSource3.x = n3;
            final OscSource oscSource5 = EMWave2Frame.this.sources[0];
            final OscSource oscSource6 = EMWave2Frame.this.sources[2];
            final boolean b = false;
            oscSource6.y = (b ? 1 : 0);
            oscSource5.y = (b ? 1 : 0);
            final OscSource oscSource7 = EMWave2Frame.this.sources[1];
            final OscSource oscSource8 = EMWave2Frame.this.sources[3];
            final int n4 = EMWave2Frame.this.gridSizeY - 1;
            oscSource8.y = n4;
            oscSource7.y = n4;
            EMWave2Frame.this.conductFillRect(EMWave2Frame.this.gridSizeX / 2 - 4, EMWave2Frame.this.gridSizeY / 2 - 4, EMWave2Frame.this.gridSizeX / 2 + 4, EMWave2Frame.this.gridSizeY / 2 + 4, 0.4);
            EMWave2Frame.this.setForceBar(2);
            EMWave2Frame.this.auxBar.setValue(40);
        }
        
        Setup createNext() {
            return new MovingWireSetup();
        }
    }
    
    class MovingWireSetup extends Setup
    {
        double y;
        int dir;
        int delay;
        int stopDelay;
        int filtstep;
        
        MovingWireSetup() {
            this.filtstep = 0;
        }
        
        String getName() {
            return "Moving Wire";
        }
        
        void select() {
            EMWave2Frame.this.sourceChooser.select(0);
            this.y = EMWave2Frame.this.windowOffsetY;
            this.dir = 1;
            this.delay = 0;
            this.stopDelay = 200;
            EMWave2Frame.this.brightnessBar.setValue(200);
        }
        
        void doStep() {
            if (this.delay > 0) {
                --this.delay;
                this.filt();
                return;
            }
            final int n = (int)this.y;
            for (int i = 0; i != 2; ++i) {
                final int n2 = EMWave2Frame.this.gridSizeX / 2 + i + EMWave2Frame.this.gw * n;
                EMWave2Frame.this.grid[n2].jz = 0.0;
                EMWave2Frame.this.grid[n2 + EMWave2Frame.this.gw].jz = 0.0;
                EMWave2Frame.this.grid[n2 + EMWave2Frame.this.gw + EMWave2Frame.this.gw].jz = 0.0;
            }
            this.y += this.dir * 0.06;
            final int n3 = (int)this.y;
            if (n != n3) {
                if (n3 == EMWave2Frame.this.gridSizeY / 2) {
                    this.delay = this.stopDelay;
                }
                if (n3 == EMWave2Frame.this.windowOffsetY || n3 == EMWave2Frame.this.windowOffsetY + EMWave2Frame.this.windowHeight - 3) {
                    this.dir = -this.dir;
                    this.delay = this.stopDelay;
                }
            }
            final int n4 = n3;
            final float n5 = (float)(this.y - n4);
            for (int j = 0; j != 2; ++j) {
                final int n6 = EMWave2Frame.this.gridSizeX / 2 + j + EMWave2Frame.this.gw * n4;
                EMWave2Frame.this.grid[n6].jz = (1.0f - n5) * 0.25;
                EMWave2Frame.this.grid[n6 + EMWave2Frame.this.gw].jz = 0.25;
                EMWave2Frame.this.grid[n6 + EMWave2Frame.this.gw + EMWave2Frame.this.gw].jz = n5 * 0.25;
            }
            this.filt();
            EMWave2Frame.this.calcBoundaries();
        }
        
        void filt() {
            final int n = EMWave2Frame.this.gridSizeX / 2;
            for (int n2 = (int)this.y, n3 = 10, i = n2 - n3; i <= n2 + n3; ++i) {
                for (int j = n - n3; j <= n + n3; ++j) {
                    final int n4 = j + i * EMWave2Frame.this.gw;
                    final OscElement oscElement = EMWave2Frame.this.grid[n4];
                    if (oscElement.jz == 0.0) {
                        if (oscElement.conductivity <= 0.0f) {
                            final double n5 = 8.0 + Math.sqrt((i - n2) * (i - n2) + (j - n) * (j - n));
                            oscElement.az = (oscElement.az * n5 + EMWave2Frame.this.grid[n4 - 1].az + EMWave2Frame.this.grid[n4 + 1].az + EMWave2Frame.this.grid[n4 - EMWave2Frame.this.gw].az + EMWave2Frame.this.grid[n4 + EMWave2Frame.this.gw].az) / (4.0 + n5);
                        }
                    }
                }
            }
        }
        
        Setup createNext() {
            return new MovingWireTubeSetup();
        }
    }
    
    class MovingWireTubeSetup extends MovingWireSetup
    {
        String getName() {
            return "Moving Wire in Tube";
        }
        
        void select() {
            super.select();
            int n = 4;
            EMWave2Frame.this.conductFillRect(EMWave2Frame.this.gridSizeX / 2 - n, EMWave2Frame.this.windowOffsetY, EMWave2Frame.this.gridSizeX / 2 - n, EMWave2Frame.this.windowOffsetY + EMWave2Frame.this.windowHeight, 0.6);
            ++n;
            EMWave2Frame.this.conductFillRect(EMWave2Frame.this.gridSizeX / 2 + n, EMWave2Frame.this.windowOffsetY, EMWave2Frame.this.gridSizeX / 2 + n, EMWave2Frame.this.windowOffsetY + EMWave2Frame.this.windowHeight, 0.6);
            super.stopDelay = 500;
            EMWave2Frame.this.brightnessBar.setValue(500);
        }
        
        Setup createNext() {
            return new MovingMagnetSetup();
        }
    }
    
    class MovingMagnetSetup extends Setup
    {
        double y;
        int dir;
        int delay;
        int filtstep;
        
        MovingMagnetSetup() {
            this.filtstep = 0;
        }
        
        String getName() {
            return "Moving Magnet in Tube";
        }
        
        void select() {
            EMWave2Frame.this.sourceChooser.select(0);
            final int n = 5;
            EMWave2Frame.this.conductFillRect(EMWave2Frame.this.gridSizeX / 2 - n, EMWave2Frame.this.windowOffsetY, EMWave2Frame.this.gridSizeX / 2 - n, EMWave2Frame.this.windowOffsetY + EMWave2Frame.this.windowHeight, 0.6);
            EMWave2Frame.this.conductFillRect(EMWave2Frame.this.gridSizeX / 2 + n, EMWave2Frame.this.windowOffsetY, EMWave2Frame.this.gridSizeX / 2 + n, EMWave2Frame.this.windowOffsetY + EMWave2Frame.this.windowHeight, 0.6);
            this.y = EMWave2Frame.this.windowOffsetY;
            this.dir = 1;
            this.delay = 0;
            EMWave2Frame.this.brightnessBar.setValue(250);
        }
        
        void doStep() {
            if (this.delay > 0) {
                --this.delay;
                this.filt();
                return;
            }
            final int n = (int)this.y;
            for (int i = -3; i <= 3; ++i) {
                for (int j = 0; j <= 2; ++j) {
                    EMWave2Frame.this.grid[EMWave2Frame.this.gridSizeX / 2 + i + EMWave2Frame.this.gw * (n + j)].my = 0.0f;
                }
            }
            this.y += this.dir * 0.06;
            final int n2 = (int)this.y;
            if (n != n2) {
                if (n2 == EMWave2Frame.this.gridSizeY / 2) {
                    this.delay = 500;
                }
                if (n2 == EMWave2Frame.this.windowOffsetY || n2 == EMWave2Frame.this.windowOffsetY + EMWave2Frame.this.windowHeight - 3) {
                    this.dir = -this.dir;
                    this.delay = 500;
                }
            }
            final int n3 = n2;
            final float n4 = (float)(this.y - n3);
            for (int k = -3; k <= 3; ++k) {
                final int n5 = EMWave2Frame.this.gridSizeX / 2 + k + EMWave2Frame.this.gw * n3;
                EMWave2Frame.this.grid[n5].my = -(1.0f - n4);
                EMWave2Frame.this.grid[n5 + EMWave2Frame.this.gw].my = -1.0f;
                EMWave2Frame.this.grid[n5 + EMWave2Frame.this.gw + EMWave2Frame.this.gw].my = -n4;
            }
            EMWave2Frame.this.calcBoundaries();
            this.filt();
        }
        
        void filt() {
            final int n = EMWave2Frame.this.gridSizeX / 2;
            final int n2 = (int)this.y;
            final int n3 = 12;
            final double n4 = 8.0;
            final double n5 = 4.0 + n4;
            for (int i = n2 - n3; i <= n2 + n3; ++i) {
                for (int j = n - n3; j <= n + n3; ++j) {
                    final int n6 = j + EMWave2Frame.this.gw * i;
                    final OscElement oscElement = EMWave2Frame.this.grid[n6];
                    if (oscElement.jz == 0.0) {
                        if (oscElement.conductivity <= 0.0f) {
                            if (EMWave2Frame.this.grid[n6 - 1].my - EMWave2Frame.this.grid[n6 + 1].my + EMWave2Frame.this.grid[n6 + EMWave2Frame.this.gw].mx - EMWave2Frame.this.grid[n6 - EMWave2Frame.this.gw].mx == 0.0) {
                                oscElement.az = (oscElement.az * n4 + EMWave2Frame.this.grid[n6 - 1].az + EMWave2Frame.this.grid[n6 + 1].az + EMWave2Frame.this.grid[n6 - EMWave2Frame.this.gw].az + EMWave2Frame.this.grid[n6 + EMWave2Frame.this.gw].az) / n5;
                            }
                        }
                    }
                }
            }
        }
        
        Setup createNext() {
            return new RotatingMagnet1Setup();
        }
    }
    
    class RotatingMagnet1Setup extends Setup
    {
        double mt;
        
        String getName() {
            return "Rotating Magnet 1";
        }
        
        void select() {
            EMWave2Frame.this.sourceChooser.select(0);
            EMWave2Frame.this.grid[EMWave2Frame.this.gridSizeX / 2 + EMWave2Frame.this.gw * (EMWave2Frame.this.gridSizeY / 2)].mx = 1.0f;
            EMWave2Frame.this.calcBoundaries();
            EMWave2Frame.this.setForceBar(10);
            this.mt = 0.0;
            EMWave2Frame.this.brightnessBar.setValue(500);
        }
        
        void doStep() {
            this.mt += EMWave2Frame.this.forceBar.getValue() * 0.003;
            EMWave2Frame.this.grid[EMWave2Frame.this.gridSizeX / 2 + EMWave2Frame.this.gw * (EMWave2Frame.this.gridSizeY / 2)].mx = (float)Math.cos(this.mt);
            EMWave2Frame.this.grid[EMWave2Frame.this.gridSizeX / 2 + EMWave2Frame.this.gw * (EMWave2Frame.this.gridSizeY / 2)].my = (float)(-Math.sin(this.mt));
            EMWave2Frame.this.doFilter();
        }
        
        Setup createNext() {
            return new RotatingMagnet2Setup();
        }
    }
    
    class RotatingMagnet2Setup extends RotatingMagnet1Setup
    {
        String getName() {
            return "Rotating Magnet 2";
        }
        
        void doStep() {
            super.mt += EMWave2Frame.this.forceBar.getValue() * 0.003;
            EMWave2Frame.this.grid[EMWave2Frame.this.gridSizeX / 2 + EMWave2Frame.this.gw * (EMWave2Frame.this.gridSizeY / 2)].mx = (float)Math.cos(super.mt);
            EMWave2Frame.this.grid[EMWave2Frame.this.gridSizeX / 2 + EMWave2Frame.this.gw * (EMWave2Frame.this.gridSizeY / 2)].my = (float)(-Math.abs(Math.sin(super.mt)));
            EMWave2Frame.this.doFilter();
            EMWave2Frame.this.brightnessBar.setValue(500);
        }
        
        Setup createNext() {
            return new Scattering1Setup();
        }
    }
    
    class Scattering1Setup extends Setup
    {
        int ctr;
        
        String getName() {
            return "Scattering 1";
        }
        
        void select() {
            EMWave2Frame.this.sourceChooser.select(8);
            EMWave2Frame.this.brightnessBar.setValue(100);
            EMWave2Frame.this.setForceBar(23);
            for (int i = EMWave2Frame.this.gridSizeX / 2 - 1; i <= EMWave2Frame.this.gridSizeX / 2 + 1; ++i) {
                for (int j = EMWave2Frame.this.gridSizeY / 2 - 1; j <= EMWave2Frame.this.gridSizeY / 2 + 1; ++j) {
                    EMWave2Frame.this.grid[i + EMWave2Frame.this.gw * j].resonant = true;
                }
            }
        }
        
        void doStep() {
            ++this.ctr;
            if (this.ctr >= 600 && this.ctr <= 700) {
                EMWave2Frame.this.sourceMult = 1.0 - (this.ctr - 600) * 0.01;
            }
            else if (this.ctr >= 1100) {
                EMWave2Frame.this.sourceMult = (this.ctr - 1100) * 0.01;
                if (this.ctr == 1200) {
                    this.ctr = 0;
                }
            }
        }
        
        Setup createNext() {
            return new Scattering2Setup();
        }
    }
    
    class Scattering2Setup extends Scattering1Setup
    {
        int ctr;
        
        String getName() {
            return "Scattering 2";
        }
        
        void select() {
            super.select();
            EMWave2Frame.this.setForceBar(16);
        }
        
        Setup createNext() {
            return new BigModeSetup();
        }
    }
    
    class BigModeSetup extends Setup
    {
        String getName() {
            return "Big TM11 Mode";
        }
        
        void select() {
            EMWave2Frame.this.sourceChooser.select(0);
            final int n = EMWave2Frame.this.windowWidth * 3 / 4;
            final int n2 = EMWave2Frame.this.windowOffsetX + EMWave2Frame.this.windowWidth / 2 - n / 2;
            final int n3 = EMWave2Frame.this.windowOffsetY + EMWave2Frame.this.windowHeight / 2 - n / 2;
            for (int i = 1; i != 4; ++i) {
                EMWave2Frame.this.conductDrawRect(n2 - i, n3 - i, n2 + n + i - 1, n3 + n + i - 1, 1.0);
            }
            EMWave2Frame.this.setupMode(n2, n3, n, n, 1, 1);
            EMWave2Frame.this.brightnessBar.setValue(200);
        }
        
        Setup createNext() {
            return new OneByOneModesSetup();
        }
    }
    
    class OneByOneModesSetup extends Setup
    {
        String getName() {
            return "TM11 Modes";
        }
        
        void select() {
            EMWave2Frame.this.sourceChooser.select(0);
            for (int n = 1, n2 = 5; n + n2 < EMWave2Frame.this.windowHeight; n += n2 + 2) {
                final int n3 = (n + n2) * (EMWave2Frame.this.windowWidth - 8) / EMWave2Frame.this.windowHeight + 6;
                final int n4 = n + EMWave2Frame.this.windowOffsetY;
                final int n5 = EMWave2Frame.this.windowOffsetX + 1;
                EMWave2Frame.this.conductDrawRect(n5 - 1, n4 - 1, n5 + n3, n4 + n2, 1.0);
                EMWave2Frame.this.setupMode(n5, n4, n3, n2, 1, 1);
            }
        }
        
        Setup createNext() {
            return new OneByNModesSetup();
        }
    }
    
    class OneByNModesSetup extends Setup
    {
        String getName() {
            return "TMn1 Modes";
        }
        
        void select() {
            EMWave2Frame.this.sourceChooser.select(0);
            int n = 1;
            final int n2 = 8;
            final int n3 = EMWave2Frame.this.windowWidth - 2;
            for (int n4 = 1; n + n2 < EMWave2Frame.this.windowHeight; n += n2 + 2, ++n4) {
                final int n5 = n + EMWave2Frame.this.windowOffsetY;
                final int n6 = EMWave2Frame.this.windowOffsetX + 1;
                EMWave2Frame.this.conductDrawRect(n6 - 1, n5 - 1, n6 + n3, n5 + n2, 1.0);
                EMWave2Frame.this.setupMode(n6, n5, n3, n2, n4, 1);
            }
        }
        
        Setup createNext() {
            return new NByNModesSetup();
        }
    }
    
    class NByNModesSetup extends Setup
    {
        String getName() {
            return "TMnn Modes";
        }
        
        void select() {
            EMWave2Frame.this.sourceChooser.select(0);
            int n = 3;
            if (EMWave2Frame.this.resBar.getValue() >= 70) {
                ++n;
            }
            if (EMWave2Frame.this.resBar.getValue() >= 100) {
                ++n;
            }
            final int n2 = EMWave2Frame.this.windowHeight / n - 2;
            final int n3 = EMWave2Frame.this.windowWidth / n - 2;
            for (int i = 1; i <= n; ++i) {
                for (int j = 1; j <= n; ++j) {
                    final int n4 = EMWave2Frame.this.windowOffsetX + 1 + (n2 + 2) * (j - 1);
                    final int n5 = EMWave2Frame.this.windowOffsetY + 1 + (n3 + 2) * (i - 1);
                    EMWave2Frame.this.conductDrawRect(n4 - 1, n5 - 1, n4 + n3, n5 + n2, 1.0);
                    EMWave2Frame.this.setupMode(n4, n5, n3, n2, i, j);
                }
            }
        }
        
        Setup createNext() {
            return new OneByNModeCombosSetup();
        }
    }
    
    class OneByNModeCombosSetup extends Setup
    {
        String getName() {
            return "TMn1 Mode Combos";
        }
        
        void select() {
            EMWave2Frame.this.sourceChooser.select(0);
            int n = 1;
            final int n2 = 8;
            final int n3 = EMWave2Frame.this.windowWidth - 2;
            while (n + n2 < EMWave2Frame.this.windowHeight) {
                final int i = EMWave2Frame.this.getrand(8) + 1;
                int n4;
                do {
                    n4 = EMWave2Frame.this.getrand(8) + 1;
                } while (i == n4);
                final int n5 = n + EMWave2Frame.this.windowOffsetY;
                final int n6 = EMWave2Frame.this.windowOffsetX + 1;
                EMWave2Frame.this.conductDrawRect(n6 - 1, n5 - 1, n6 + n3, n5 + n2, 1.0);
                for (int j = 0; j != n3; ++j) {
                    for (int k = 0; k != n2; ++k) {
                        EMWave2Frame.this.grid[j + n6 + EMWave2Frame.this.gw * (k + n5)].az = 2.0 * (Math.sin(i * 3.141592653589793 * (j + 1) / (n3 + 1)) * Math.sin(3.141592653589793 * (k + 1) / (n2 + 1)) * 0.5 + Math.sin(n4 * 3.141592653589793 * (j + 1) / (n3 + 1)) * Math.sin(3.141592653589793 * (k + 1) / (n2 + 1)) * 0.5);
                        EMWave2Frame.this.grid[j + n6 + EMWave2Frame.this.gw * (k + n5)].dazdt = 0.0;
                    }
                }
                n += n2 + 2;
            }
            EMWave2Frame.this.noFilter();
        }
        
        Setup createNext() {
            return new NByNModeCombosSetup();
        }
    }
    
    class NByNModeCombosSetup extends Setup
    {
        String getName() {
            return "TMnn Mode Combos";
        }
        
        void select() {
            EMWave2Frame.this.sourceChooser.select(0);
            int n = 2;
            if (EMWave2Frame.this.resBar.getValue() >= 70) {
                ++n;
            }
            if (EMWave2Frame.this.resBar.getValue() >= 100) {
                ++n;
            }
            final int n2 = EMWave2Frame.this.windowHeight / n - 2;
            final int n3 = EMWave2Frame.this.windowWidth / n - 2;
            for (int i = 1; i <= n; ++i) {
                for (int j = 1; j <= n; ++j) {
                    final int n4 = EMWave2Frame.this.getrand(4) + 1;
                    final int n5 = EMWave2Frame.this.getrand(4) + 1;
                    int n6;
                    int n7;
                    do {
                        n6 = EMWave2Frame.this.getrand(4) + 1;
                        n7 = EMWave2Frame.this.getrand(4) + 1;
                    } while (n4 == n6 && n5 == n7);
                    final int n8 = EMWave2Frame.this.windowOffsetX + 1 + (n2 + 2) * (i - 1);
                    final int n9 = EMWave2Frame.this.windowOffsetY + 1 + (n3 + 2) * (j - 1);
                    EMWave2Frame.this.conductDrawRect(n8 - 1, n9 - 1, n8 + n3, n9 + n2, 1.0);
                    for (int k = 0; k != n3; ++k) {
                        for (int l = 0; l != n2; ++l) {
                            EMWave2Frame.this.grid[k + n8 + EMWave2Frame.this.gw * (l + n9)].az = 2.0 * (Math.sin(n4 * 3.141592653589793 * (k + 1) / (n3 + 1)) * Math.sin(n5 * 3.141592653589793 * (l + 1) / (n2 + 1)) * 0.5 + Math.sin(n6 * 3.141592653589793 * (k + 1) / (n3 + 1)) * Math.sin(n7 * 3.141592653589793 * (l + 1) / (n2 + 1)) * 0.5);
                            EMWave2Frame.this.grid[k + n8 + EMWave2Frame.this.gw * (l + n9)].dazdt = 0.0;
                        }
                    }
                }
            }
            EMWave2Frame.this.noFilter();
        }
        
        Setup createNext() {
            return new TriangleModesSetup();
        }
    }
    
    class TriangleModesSetup extends Setup
    {
        String getName() {
            return "Triangle Modes";
        }
        
        void select() {
            EMWave2Frame.this.sourceChooser.select(0);
            for (int i = 0; i != 2; ++i) {
                for (int j = 0; j != 2; ++j) {
                    final int n = EMWave2Frame.this.windowOffsetX + EMWave2Frame.this.windowWidth * i / 2 + 1;
                    final int n2 = EMWave2Frame.this.windowOffsetY + EMWave2Frame.this.windowHeight * j / 2 + 1;
                    final int n3 = EMWave2Frame.this.windowWidth / 2 - 2;
                    final int n4 = EMWave2Frame.this.windowHeight / 2 - 2;
                    for (int k = 0; k != n3; ++k) {
                        EMWave2Frame.this.conductDrawRect(n + k + 1, n2 + k, n + n3, n2 + k, 1.0);
                    }
                    EMWave2Frame.this.conductDrawRect(n - 1, n2 - 1, n + n3, n2 + n4, 1.0);
                    int n5 = 0;
                    int n6 = 0;
                    switch (j * 2 + i) {
                        case 0: {
                            n5 = 1;
                            n6 = 2;
                            break;
                        }
                        case 1: {
                            n5 = 1;
                            n6 = 3;
                            break;
                        }
                        case 2: {
                            n5 = 2;
                            n6 = 3;
                            break;
                        }
                        case 3: {
                            n5 = 1;
                            n6 = 4;
                            break;
                        }
                    }
                    for (int l = 0; l != n4; ++l) {
                        int n7;
                        for (n7 = 0; n7 <= l; ++n7) {
                            EMWave2Frame.this.grid[n + n7 + EMWave2Frame.this.gw * (n2 + l)].az = Math.sin(n5 * 3.141592653589793 * (n7 + 1) / (n3 + 2)) * Math.sin(n6 * 3.141592653589793 * (l + 2) / (n4 + 2)) - Math.sin(n6 * 3.141592653589793 * (n7 + 1) / (n3 + 2)) * Math.sin(n5 * 3.141592653589793 * (l + 2) / (n4 + 2));
                        }
                        EMWave2Frame.this.grid[n + n7 + EMWave2Frame.this.gw * (n2 + l)].dazdt = 0.0;
                    }
                }
            }
            EMWave2Frame.this.brightnessBar.setValue(114);
            EMWave2Frame.this.noFilter();
        }
        
        Setup createNext() {
            return new CircleModes1Setup();
        }
    }
    
    class CircleModes1Setup extends Setup
    {
        String getName() {
            return "Circular Modes 1";
        }
        
        void select() {
            EMWave2Frame.this.sourceChooser.select(0);
            for (int i = 0; i != 2; ++i) {
                for (int j = 0; j != 2; ++j) {
                    final int n = EMWave2Frame.this.windowOffsetX + EMWave2Frame.this.windowWidth * i / 2 + 1;
                    final int n2 = EMWave2Frame.this.windowOffsetY + EMWave2Frame.this.windowHeight * j / 2;
                    final int n3 = EMWave2Frame.this.windowWidth / 2 - 2;
                    EMWave2Frame.this.conductFillRect(n - 1, n2 - 1, n + n3 + 1, n2 + (EMWave2Frame.this.windowHeight / 2 - 2) + 1, 1.0);
                    final int n4 = n3 / 2;
                    final double[] array = new double[3];
                    final double n5 = EMWave2Frame.this.zeroj(i, j + 1) / n4;
                    double n6 = 1.0;
                    switch (j * 2 + i) {
                        case 1: {
                            n6 = 1.6666666666666667;
                            break;
                        }
                        case 2: {
                            n6 = 2.0;
                            break;
                        }
                        case 3: {
                            n6 = 8.333333333333334;
                            break;
                        }
                    }
                    for (int k = -n4; k <= n4; ++k) {
                        final int n7 = (int)Math.sqrt(n4 * n4 - k * k - 1.0E-5);
                        EMWave2Frame.this.conductFillRect(n + n4 + k, n2 + n4 - n7, n + n4 + k, n2 + n4 + n7, 0.0);
                        for (int l = -n7; l <= n7; ++l) {
                            final double sqrt = Math.sqrt(k * k + l * l);
                            final double n8 = sqrt * n5;
                            double n9 = (i == 0) ? 1.0 : (l / sqrt);
                            if (sqrt == 0.0) {
                                n9 = ((i == 0) ? 1.0 : 0.0);
                            }
                            EMWave2Frame.this.bess(i, n8, array);
                            EMWave2Frame.this.grid[n + n4 + k + EMWave2Frame.this.gw * (n2 + n4 + l)].az = array[i + 1] * n9 * n6;
                        }
                    }
                }
            }
            EMWave2Frame.this.brightnessBar.setValue(200);
        }
        
        Setup createNext() {
            return new CircleModes2Setup();
        }
    }
    
    class CircleModes2Setup extends Setup
    {
        String getName() {
            return "Circular Modes 2";
        }
        
        void select() {
            EMWave2Frame.this.sourceChooser.select(0);
            final int n = EMWave2Frame.this.windowOffsetX + 1;
            final int n2 = EMWave2Frame.this.windowOffsetY + 1;
            final int n3 = EMWave2Frame.this.windowWidth - 2;
            EMWave2Frame.this.conductFillRect(n - 1, n2 - 1, n + n3 + 1, n2 + (EMWave2Frame.this.windowHeight - 2) + 1, 1.0);
            final int n4 = n3 / 2;
            final double[] array = new double[3];
            final double n5 = EMWave2Frame.this.zeroj(1, 1) / n4;
            final double n6 = 2 * n4 / 16.0;
            for (int i = -n4; i <= n4; ++i) {
                final int n7 = (int)Math.sqrt(n4 * n4 - i * i - 1.0E-5);
                EMWave2Frame.this.conductFillRect(n + n4 + i, n2 + n4 - n7, n + n4 + i, n2 + n4 + n7, 0.0);
                for (int j = -n7; j <= n7; ++j) {
                    final double sqrt = Math.sqrt(i * i + j * j);
                    final double n8 = sqrt * n5;
                    double n9 = j / sqrt;
                    double n10 = i / sqrt;
                    if (sqrt == 0.0) {
                        n10 = (n9 = 0.0);
                    }
                    EMWave2Frame.this.bess(1, n8, array);
                    EMWave2Frame.this.grid[n + n4 + i + EMWave2Frame.this.gw * (n2 + n4 + j)].az = array[2] * n9 * n6;
                    EMWave2Frame.this.grid[n + n4 + i + EMWave2Frame.this.gw * (n2 + n4 + j)].dazdt = array[2] * n10;
                }
            }
            EMWave2Frame.this.brightnessBar.setValue(200);
        }
        
        Setup createNext() {
            return new Waveguides1Setup();
        }
    }
    
    class Waveguides1Setup extends Setup
    {
        String getName() {
            return "Waveguides 1";
        }
        
        void select() {
            EMWave2Frame.this.sourceChooser.select(8);
            int n = 1;
            int n2 = 5;
            final int n3 = EMWave2Frame.this.windowOffsetY + 2;
            while (n + n2 < EMWave2Frame.this.windowWidth) {
                final int n4 = n + EMWave2Frame.this.windowOffsetX;
                EMWave2Frame.this.conductDrawRect(n4 - 1, n3 - 1, n4 - 1, EMWave2Frame.this.gridSizeY - 1, 1.0);
                EMWave2Frame.this.conductDrawRect(n4 + n2, n3 - 1, n4 + n2, EMWave2Frame.this.gridSizeY - 1, 1.0);
                n2 += 2;
                n += n2;
            }
            EMWave2Frame.this.conductDrawRect(n - 1 + EMWave2Frame.this.windowOffsetX, n3 - 1, EMWave2Frame.this.gridSizeX - 1, n3 - 1, 1.0);
            EMWave2Frame.this.brightnessBar.setValue(140);
            EMWave2Frame.this.setForceBar(28);
        }
        
        Setup createNext() {
            return new Waveguides2Setup();
        }
    }
    
    class Waveguides2Setup extends Waveguides1Setup
    {
        String getName() {
            return "Waveguides 2";
        }
        
        void select() {
            super.select();
            EMWave2Frame.this.setForceBar(17);
        }
        
        Setup createNext() {
            return new Waveguides3Setup();
        }
    }
    
    class Waveguides3Setup extends Setup
    {
        String getName() {
            return "Waveguides 3";
        }
        
        void select() {
            EMWave2Frame.this.sourceChooser.select(8);
            final int n = 8;
            final int n2 = EMWave2Frame.this.windowOffsetY + 2;
            EMWave2Frame.this.conductDrawRect(EMWave2Frame.this.windowOffsetX + 1, n2 - 1, EMWave2Frame.this.windowOffsetX + EMWave2Frame.this.windowWidth - 1, n2 - 1, 1.0);
            for (int n3 = 1, n4 = 0; n3 + n < EMWave2Frame.this.windowWidth && n4 < n; ++n4) {
                final int n5 = n3 + EMWave2Frame.this.windowOffsetX;
                EMWave2Frame.this.conductDrawRect(n5 - 1, n2 - 1, n5 - 1, EMWave2Frame.this.gridSizeY - 1, 1.0);
                EMWave2Frame.this.conductDrawRect(n5 + n, n2 - 1, n5 + n, EMWave2Frame.this.gridSizeY - 1, 1.0);
                EMWave2Frame.this.addConductor(n5 + n4++, n2 - 1, 0.0);
                n3 += n + 2;
                if (EMWave2Frame.this.resBar.getValue() == 32 && n4 == 2) {}
            }
            EMWave2Frame.this.brightnessBar.setValue(1000);
            EMWave2Frame.this.setForceBar(32);
        }
        
        Setup createNext() {
            return new Waveguides4Setup();
        }
    }
    
    class Waveguides4Setup extends Waveguides3Setup
    {
        String getName() {
            return "Waveguides 4";
        }
        
        void select() {
            EMWave2Frame.this.sourceChooser.select(8);
            final int n = 9;
            final int n2 = EMWave2Frame.this.windowOffsetY + 2;
            final int n3 = EMWave2Frame.this.windowHeight - 1;
            for (int n4 = 1; n4 + n < EMWave2Frame.this.windowWidth; n4 += n + 2) {
                final int n5 = n4 + EMWave2Frame.this.windowOffsetX;
                EMWave2Frame.this.conductDrawRect(n5 - 1, n2 - 1, n5 - 1, n2 + n3 - 2, 1.0);
                EMWave2Frame.this.conductDrawRect(n5 + n, n2 - 1, n5 + n, n2 + n3 - 2, 1.0);
            }
            EMWave2Frame.this.brightnessBar.setValue(480);
            EMWave2Frame.this.setForceBar(40);
        }
        
        void doStep() {
            final int windowOffsetY = EMWave2Frame.this.windowOffsetY;
            for (int n = 9, n2 = 1, n3 = 1; n2 + n < EMWave2Frame.this.windowWidth; n2 += n + 2, ++n3) {
                final int n4 = n2 + EMWave2Frame.this.windowOffsetX;
                int n6 = 0;
                int n5 = 0;
                switch (n3) {
                    case 1: {
                        n5 = (n6 = 1);
                        break;
                    }
                    case 2: {
                        n5 = (n6 = 2);
                        break;
                    }
                    case 3: {
                        n5 = (n6 = 3);
                        break;
                    }
                    case 4: {
                        n6 = 1;
                        n5 = 2;
                        break;
                    }
                    case 5: {
                        n6 = 1;
                        n5 = 3;
                        break;
                    }
                    case 6: {
                        n6 = 2;
                        n5 = 3;
                        break;
                    }
                    default: {
                        n5 = (n6 = 0);
                        break;
                    }
                }
                for (int i = 0; i != n; ++i) {
                    EMWave2Frame.this.grid[n4 + i + EMWave2Frame.this.gw * windowOffsetY].az = EMWave2Frame.this.grid[n4 + i + EMWave2Frame.this.gw * windowOffsetY].jz * (Math.sin(3.141592653589793 * n6 * (i + 1) / (n + 1)) + Math.sin(3.141592653589793 * n5 * (i + 1) / (n + 1)));
                    EMWave2Frame.this.grid[n4 + i + EMWave2Frame.this.gw * windowOffsetY].jz = 0.0;
                }
            }
        }
        
        Setup createNext() {
            return new ResonantCavitiesSetup();
        }
    }
    
    class ResonantCavitiesSetup extends Setup
    {
        String getName() {
            return "Resonant Cavities";
        }
        
        void select() {
            EMWave2Frame.this.sourceChooser.select(8);
            int i = 1;
            final int n = 5;
            final int n2 = EMWave2Frame.this.windowOffsetY + 11;
            while (i + n < EMWave2Frame.this.windowWidth) {
                final int n3 = (i + n) * (EMWave2Frame.this.windowHeight - 18) / EMWave2Frame.this.windowWidth + 6;
                final int n4 = i + EMWave2Frame.this.windowOffsetX;
                for (int j = 0; j != n3 + 2; ++j) {
                    EMWave2Frame.this.addConductor(n4 - 1, n2 + j - 1, 1.0);
                    EMWave2Frame.this.addConductor(n4 + n, n2 + j - 1, 1.0);
                }
                for (int k = 0; k != n + 2; ++k) {
                    EMWave2Frame.this.addConductor(n4 + k - 1, n2 - 1, 1.0);
                    EMWave2Frame.this.addConductor(n4 + k - 1, n2 + n3, 1.0);
                }
                EMWave2Frame.this.addConductor(n4 + n / 2, n2 - 1, 0.0);
                i += n + 2;
            }
            --i;
            while (i < EMWave2Frame.this.windowWidth) {
                EMWave2Frame.this.addConductor(i + EMWave2Frame.this.windowOffsetX, n2 - 1, 1.0);
                ++i;
            }
            EMWave2Frame.this.brightnessBar.setValue(300);
            EMWave2Frame.this.setForceBar(38);
        }
        
        Setup createNext() {
            return new SingleSlitSetup();
        }
    }
    
    class SingleSlitSetup extends Setup
    {
        String getName() {
            return "Single Slit";
        }
        
        void select() {
            EMWave2Frame.this.sourceChooser.select(8);
            final int n = EMWave2Frame.this.gridSizeX / 2;
            final int n2 = EMWave2Frame.this.windowOffsetY + 4;
            EMWave2Frame.this.conductFillRect(0, n2, EMWave2Frame.this.gridSizeX - 1, n2 + 2, 1.0);
            EMWave2Frame.this.conductFillRect(n - 7, n2, n + 7, n2 + 2, 0.0);
            EMWave2Frame.this.brightnessBar.setValue(275);
            EMWave2Frame.this.setForceBar(35);
        }
        
        Setup createNext() {
            return new DoubleSlitSetup();
        }
    }
    
    class DoubleSlitSetup extends Setup
    {
        String getName() {
            return "Double Slit";
        }
        
        void select() {
            EMWave2Frame.this.sourceChooser.select(8);
            final int n = EMWave2Frame.this.gridSizeX / 2;
            final int n2 = EMWave2Frame.this.windowOffsetY + 4;
            EMWave2Frame.this.conductFillRect(0, n2, EMWave2Frame.this.gridSizeX - 1, n2 + 2, 1.0);
            EMWave2Frame.this.conductFillRect(n - 7, n2, n - 5, n2 + 2, 0.0);
            EMWave2Frame.this.conductFillRect(n + 5, n2, n + 7, n2 + 2, 0.0);
            EMWave2Frame.this.brightnessBar.setValue(366);
            EMWave2Frame.this.setForceBar(35);
        }
        
        Setup createNext() {
            return new TripleSlitSetup();
        }
    }
    
    class TripleSlitSetup extends Setup
    {
        String getName() {
            return "Triple Slit";
        }
        
        void select() {
            EMWave2Frame.this.sourceChooser.select(8);
            final int n = EMWave2Frame.this.gridSizeX / 2;
            final int n2 = EMWave2Frame.this.windowOffsetY + 4;
            EMWave2Frame.this.conductFillRect(0, n2, EMWave2Frame.this.gridSizeX - 1, n2 + 2, 1.0);
            EMWave2Frame.this.conductFillRect(n - 13, n2, n - 11, n2 + 2, 0.0);
            EMWave2Frame.this.conductFillRect(n - 1, n2, n + 1, n2 + 2, 0.0);
            EMWave2Frame.this.conductFillRect(n + 11, n2, n + 13, n2 + 2, 0.0);
            EMWave2Frame.this.brightnessBar.setValue(310);
            EMWave2Frame.this.setForceBar(35);
        }
        
        Setup createNext() {
            return new ObstacleSetup();
        }
    }
    
    class ObstacleSetup extends Setup
    {
        String getName() {
            return "Obstacle";
        }
        
        void select() {
            EMWave2Frame.this.sourceChooser.select(8);
            final int n = EMWave2Frame.this.gridSizeX / 2;
            final int n2 = EMWave2Frame.this.windowOffsetY + 6;
            EMWave2Frame.this.conductFillRect(n - 7, n2, n + 7, n2 + 2, 1.0);
            EMWave2Frame.this.brightnessBar.setValue(400);
            EMWave2Frame.this.setForceBar(35);
        }
        
        Setup createNext() {
            return new HalfPlaneSetup();
        }
    }
    
    class HalfPlaneSetup extends Setup
    {
        String getName() {
            return "Half Plane";
        }
        
        void select() {
            EMWave2Frame.this.sourceChooser.select(8);
            final int n = EMWave2Frame.this.windowOffsetX + EMWave2Frame.this.windowWidth / 2;
            EMWave2Frame.this.conductFillRect(EMWave2Frame.this.windowOffsetX + EMWave2Frame.this.windowWidth * 2 / 3, EMWave2Frame.this.windowOffsetY + 3, EMWave2Frame.this.windowOffsetY + EMWave2Frame.this.windowWidth - 1, EMWave2Frame.this.windowOffsetY + 5, 1.0);
            EMWave2Frame.this.brightnessBar.setValue(150);
            EMWave2Frame.this.setForceBar(35);
        }
        
        Setup createNext() {
            return new LloydsMirrorSetup();
        }
    }
    
    class LloydsMirrorSetup extends Setup
    {
        String getName() {
            return "Lloyd's Mirror";
        }
        
        void select() {
            EMWave2Frame.this.setSources();
            EMWave2Frame.this.sources[0].x = EMWave2Frame.this.windowOffsetX;
            EMWave2Frame.this.sources[0].y = EMWave2Frame.this.windowOffsetY + EMWave2Frame.this.windowHeight * 3 / 4;
            EMWave2Frame.this.brightnessBar.setValue(120);
            EMWave2Frame.this.setForceBar(40);
            EMWave2Frame.this.conductDrawRect(0, EMWave2Frame.this.windowOffsetY + EMWave2Frame.this.windowHeight - 1, EMWave2Frame.this.gridSizeX - 1, EMWave2Frame.this.windowOffsetY + EMWave2Frame.this.windowHeight - 1, 1.0);
        }
        
        void doSetupSources() {
        }
        
        Setup createNext() {
            return null;
        }
    }
}
