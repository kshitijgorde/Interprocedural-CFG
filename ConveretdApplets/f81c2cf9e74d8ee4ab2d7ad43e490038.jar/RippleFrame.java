import java.awt.Point;
import java.awt.TextArea;
import java.awt.Dialog;
import java.awt.Insets;
import java.util.StringTokenizer;
import java.awt.event.ItemEvent;
import java.awt.event.AdjustmentEvent;
import java.awt.event.ActionEvent;
import java.awt.event.ComponentEvent;
import java.awt.event.MouseEvent;
import java.awt.FontMetrics;
import java.awt.image.ImageObserver;
import java.awt.Graphics;
import java.awt.Event;
import java.awt.image.ImageProducer;
import java.awt.Component;
import java.awt.LayoutManager;
import java.lang.reflect.Method;
import java.awt.Color;
import java.awt.image.MemoryImageSource;
import java.awt.Label;
import java.awt.Scrollbar;
import java.util.Vector;
import java.awt.Choice;
import java.awt.Checkbox;
import java.awt.Button;
import java.awt.Container;
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

class RippleFrame extends Frame implements ComponentListener, ActionListener, AdjustmentListener, MouseMotionListener, MouseListener, ItemListener
{
    Thread engine;
    Dimension winSize;
    Image dbimage;
    Random random;
    int gridSizeX;
    int gridSizeY;
    int gridSizeXY;
    int gw;
    int windowWidth;
    int windowHeight;
    int windowOffsetX;
    int windowOffsetY;
    int windowBottom;
    int windowRight;
    public static final int sourceRadius = 7;
    public static final double freqMult = 0.0233333;
    Container main;
    Button blankButton;
    Button blankWallsButton;
    Button borderButton;
    Button exportButton;
    Checkbox stoppedCheck;
    Checkbox fixedEndsCheck;
    Checkbox view3dCheck;
    Choice modeChooser;
    Choice sourceChooser;
    Choice setupChooser;
    Choice colorChooser;
    Vector setupList;
    Setup setup;
    Scrollbar dampingBar;
    Scrollbar speedBar;
    Scrollbar freqBar;
    Scrollbar resBar;
    Scrollbar brightnessBar;
    Scrollbar auxBar;
    Label auxLabel;
    double dampcoef;
    double freqTimeZero;
    double movingSourcePos;
    double brightMult;
    static final double pi = 3.141592653589793;
    float[] func;
    float[] funci;
    float[] damp;
    boolean[] walls;
    boolean[] exceptional;
    int[] medium;
    OscSource[] sources;
    static final int MODE_SETFUNC = 0;
    static final int MODE_WALLS = 1;
    static final int MODE_MEDIUM = 2;
    static final int MODE_FUNCHOLD = 3;
    int dragX;
    int dragY;
    int dragStartX;
    int dragStartY;
    int selectedSource;
    int sourceIndex;
    int freqBarValue;
    boolean dragging;
    boolean dragClear;
    boolean dragSet;
    public boolean useFrame;
    boolean showControls;
    double t;
    MemoryImageSource imageSource;
    int[] pixels;
    int sourceCount;
    boolean sourcePlane;
    boolean sourceMoving;
    boolean increaseResolution;
    boolean adjustResolution;
    int sourceFreqCount;
    int sourceWaveform;
    int auxFunction;
    long startTime;
    Color wallColor;
    Color posColor;
    Color negColor;
    Color zeroColor;
    Color medColor;
    Color posMedColor;
    Color negMedColor;
    Color sourceColor;
    Color[][] schemeColors;
    Method timerMethod;
    int timerDiv;
    ImportDialog impDialog;
    static final int mediumMax = 191;
    static final double mediumMaxIndex = 0.5;
    static final int SWF_SIN = 0;
    static final int SWF_SQUARE = 1;
    static final int SWF_PULSE = 2;
    static final int AUX_NONE = 0;
    static final int AUX_PHASE = 1;
    static final int AUX_FREQ = 2;
    static final int AUX_SPEED = 3;
    static final int SRC_NONE = 0;
    static final int SRC_1S1F = 1;
    static final int SRC_2S1F = 3;
    static final int SRC_2S2F = 4;
    static final int SRC_4S1F = 6;
    static final int SRC_1S1F_PULSE = 8;
    static final int SRC_1S1F_MOVING = 9;
    static final int SRC_1S1F_PLANE = 10;
    static final int SRC_2S1F_PLANE = 12;
    static final int SRC_1S1F_PLANE_PULSE = 14;
    static final int SRC_1S1F_PLANE_PHASE = 15;
    static final int SRC_6S1F = 16;
    static final int SRC_8S1F = 17;
    static final int SRC_10S1F = 18;
    static final int SRC_12S1F = 19;
    static final int SRC_16S1F = 20;
    static final int SRC_20S1F = 21;
    RippleCanvas cv;
    Ripple applet;
    boolean useBufferedImage;
    boolean shown;
    long lastTime;
    long lastFrameTime;
    long secTime;
    int frames;
    int steps;
    int framerate;
    int steprate;
    boolean moveRight;
    boolean moveDown;
    int filterCount;
    double realxmx;
    double realxmy;
    double realymz;
    double realzmy;
    double realzmx;
    double realymadd;
    double realzmadd;
    double viewAngle;
    double viewAngleDragStart;
    double viewZoom;
    double viewZoomDragStart;
    double viewAngleCos;
    double viewAngleSin;
    double viewHeight;
    double viewHeightDragStart;
    double scalex;
    double scaley;
    int centerX3d;
    int centerY3d;
    int[] xpoints;
    int[] ypoints;
    final double viewDistance = 66.0;
    double scaleMult;
    
    public String getAppletInfo() {
        return "Ripple by Paul Falstad";
    }
    
    int getrand(final int n) {
        int nextInt = this.random.nextInt();
        if (nextInt < 0) {
            nextInt = -nextInt;
        }
        return nextInt % n;
    }
    
    RippleFrame(final Ripple applet) {
        super("Ripple Tank Applet v1.7e");
        this.engine = null;
        this.windowWidth = 50;
        this.windowHeight = 50;
        this.windowOffsetX = 0;
        this.windowOffsetY = 0;
        this.windowBottom = 0;
        this.windowRight = 0;
        this.movingSourcePos = 0.0;
        this.brightMult = 1.0;
        this.dragStartX = -1;
        this.selectedSource = -1;
        this.sourceCount = -1;
        this.sourcePlane = false;
        this.sourceMoving = false;
        this.increaseResolution = false;
        this.adjustResolution = true;
        this.sourceFreqCount = -1;
        this.sourceWaveform = 0;
        this.useBufferedImage = false;
        this.shown = false;
        this.lastTime = 0L;
        this.secTime = 0L;
        this.frames = 0;
        this.steps = 0;
        this.framerate = 0;
        this.steprate = 0;
        this.moveRight = true;
        this.moveDown = true;
        this.viewAngle = 3.141592653589793;
        this.viewZoom = 0.775;
        this.viewAngleCos = -1.0;
        this.viewAngleSin = 0.0;
        this.viewHeight = -38.0;
        this.xpoints = new int[4];
        this.ypoints = new int[4];
        this.applet = applet;
        this.useFrame = true;
        this.showControls = true;
        this.adjustResolution = true;
    }
    
    public void init() {
        try {
            if (this.applet != null) {
                final String parameter = this.applet.getParameter("useFrame");
                if (parameter != null && parameter.equalsIgnoreCase("false")) {
                    this.useFrame = false;
                }
                final String parameter2 = this.applet.getParameter("showControls");
                if (parameter2 != null && parameter2.equalsIgnoreCase("false")) {
                    this.showControls = false;
                }
            }
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
        if (this.useFrame) {
            this.main = this;
        }
        else {
            this.main = this.applet;
        }
        this.setupList = new Vector();
        for (Setup next = new SingleSourceSetup(); next != null; next = next.createNext()) {
            this.setupList.addElement(next);
        }
        System.getProperty("os.name");
        final int n = 110;
        if (new Double(System.getProperty("java.class.version")) >= 48.0) {
            this.useBufferedImage = true;
        }
        try {
            final Class<?> forName = Class.forName("java.lang.System");
            this.timerMethod = forName.getMethod("nanoTime", (Class[])null);
            this.timerDiv = 1000000;
            if (this.timerMethod == null) {
                this.timerMethod = forName.getMethod("currentTimeMillis", (Class[])null);
                this.timerDiv = 1;
            }
        }
        catch (Exception ex2) {
            ex2.printStackTrace();
        }
        this.sources = new OscSource[20];
        this.main.setLayout(new RippleLayout());
        (this.cv = new RippleCanvas(this)).addComponentListener(this);
        this.cv.addMouseMotionListener(this);
        this.cv.addMouseListener(this);
        this.main.add(this.cv);
        this.setupChooser = new Choice();
        for (int i = 0; i != this.setupList.size(); ++i) {
            this.setupChooser.add("Setup: " + ((Setup)this.setupList.elementAt(i)).getName());
        }
        this.setupChooser.addItemListener(this);
        if (this.showControls) {
            this.main.add(this.setupChooser);
        }
        (this.sourceChooser = new Choice()).add("No Sources");
        this.sourceChooser.add("1 Src, 1 Freq");
        this.sourceChooser.add("1 Src, 2 Freq");
        this.sourceChooser.add("2 Src, 1 Freq");
        this.sourceChooser.add("2 Src, 2 Freq");
        this.sourceChooser.add("3 Src, 1 Freq");
        this.sourceChooser.add("4 Src, 1 Freq");
        this.sourceChooser.add("1 Src, 1 Freq (Square)");
        this.sourceChooser.add("1 Src, 1 Freq (Pulse)");
        this.sourceChooser.add("1 Moving Src");
        this.sourceChooser.add("1 Plane Src, 1 Freq");
        this.sourceChooser.add("1 Plane Src, 2 Freq");
        this.sourceChooser.add("2 Plane Src, 1 Freq");
        this.sourceChooser.add("2 Plane Src, 2 Freq");
        this.sourceChooser.add("1 Plane 1 Freq (Pulse)");
        this.sourceChooser.add("1 Plane 1 Freq w/Phase");
        this.sourceChooser.add("6 Src, 1 Freq");
        this.sourceChooser.add("8 Src, 1 Freq");
        this.sourceChooser.add("10 Src, 1 Freq");
        this.sourceChooser.add("12 Src, 1 Freq");
        this.sourceChooser.add("16 Src, 1 Freq");
        this.sourceChooser.add("20 Src, 1 Freq");
        this.sourceChooser.select(1);
        this.sourceChooser.addItemListener(this);
        if (this.showControls) {
            this.main.add(this.sourceChooser);
        }
        (this.modeChooser = new Choice()).add("Mouse = Edit Wave");
        this.modeChooser.add("Mouse = Edit Walls");
        this.modeChooser.add("Mouse = Edit Medium");
        this.modeChooser.add("Mouse = Hold Wave");
        this.modeChooser.addItemListener(this);
        if (this.showControls) {
            this.main.add(this.modeChooser);
        }
        else {
            this.modeChooser.select(1);
        }
        (this.colorChooser = new Choice()).addItemListener(this);
        if (this.showControls) {
            this.main.add(this.colorChooser);
        }
        this.blankButton = new Button("Clear Waves");
        if (this.showControls) {
            this.main.add(this.blankButton);
        }
        this.blankButton.addActionListener(this);
        this.blankWallsButton = new Button("Clear Walls");
        if (this.showControls) {
            this.main.add(this.blankWallsButton);
        }
        this.blankWallsButton.addActionListener(this);
        this.borderButton = new Button("Add Border");
        if (this.showControls) {
            this.main.add(this.borderButton);
        }
        this.borderButton.addActionListener(this);
        this.exportButton = new Button("Import/Export");
        if (this.showControls) {
            this.main.add(this.exportButton);
        }
        this.exportButton.addActionListener(this);
        (this.stoppedCheck = new Checkbox("Stopped")).addItemListener(this);
        if (this.showControls) {
            this.main.add(this.stoppedCheck);
        }
        (this.fixedEndsCheck = new Checkbox("Fixed Edges", true)).addItemListener(this);
        if (this.showControls) {
            this.main.add(this.fixedEndsCheck);
        }
        (this.view3dCheck = new Checkbox("3-D View")).addItemListener(this);
        if (this.showControls) {
            this.main.add(this.view3dCheck);
        }
        final Label label = new Label("Simulation Speed", 1);
        this.speedBar = new Scrollbar(0, 8, 1, 1, 100);
        if (this.showControls) {
            this.main.add(label);
            this.main.add(this.speedBar);
        }
        this.speedBar.addAdjustmentListener(this);
        final Label label2 = new Label("Resolution", 1);
        this.resBar = new Scrollbar(0, n, 5, 5, 400);
        if (this.showControls) {
            this.main.add(label2);
            this.main.add(this.resBar);
        }
        this.resBar.addAdjustmentListener(this);
        this.setResolution();
        final Label label3 = new Label("Damping", 1);
        (this.dampingBar = new Scrollbar(0, 10, 1, 2, 100)).addAdjustmentListener(this);
        if (this.showControls) {
            this.main.add(label3);
            this.main.add(this.dampingBar);
        }
        final Label label4 = new Label("Source Frequency", 1);
        (this.freqBar = new Scrollbar(0, this.freqBarValue = 15, 1, 1, 30)).addAdjustmentListener(this);
        if (this.showControls) {
            this.main.add(label4);
            this.main.add(this.freqBar);
        }
        final Label label5 = new Label("Brightness", 1);
        (this.brightnessBar = new Scrollbar(0, 27, 1, 1, 1200)).addAdjustmentListener(this);
        if (this.showControls) {
            this.main.add(label5);
            this.main.add(this.brightnessBar);
        }
        this.auxLabel = new Label("", 1);
        (this.auxBar = new Scrollbar(0, 1, 1, 1, 30)).addAdjustmentListener(this);
        if (this.showControls) {
            this.main.add(this.auxLabel);
            this.main.add(this.auxBar);
        }
        if (this.showControls) {
            this.main.add(new Label("http://www.falstad.com"));
        }
        this.schemeColors = new Color[20][8];
        try {
            final String parameter3 = this.applet.getParameter("setup");
            if (parameter3 != null) {
                this.setupChooser.select(Integer.parseInt(parameter3));
            }
            final String parameter4 = this.applet.getParameter("setupClass");
            if (parameter4 != null) {
                int n2;
                for (n2 = 0; n2 != this.setupList.size() && !this.setupList.elementAt(n2).getClass().getName().equalsIgnoreCase("RippleFrame$" + parameter4); ++n2) {}
                if (n2 != this.setupList.size()) {
                    this.setupChooser.select(n2);
                }
            }
            for (int j = 0; j != 20; ++j) {
                final String parameter5 = this.applet.getParameter("colorScheme" + (j + 1));
                if (parameter5 == null) {
                    break;
                }
                this.decodeColorScheme(j, parameter5);
            }
        }
        catch (Exception ex3) {
            if (this.applet != null) {
                ex3.printStackTrace();
            }
        }
        if (this.colorChooser.getItemCount() == 0) {
            this.addDefaultColorScheme();
        }
        this.doColor();
        this.random = new Random();
        this.setDamping();
        this.setup = this.setupList.elementAt(this.setupChooser.getSelectedIndex());
        this.reinit();
        this.cv.setBackground(Color.black);
        this.cv.setForeground(Color.lightGray);
        this.startTime = this.getTimeMillis();
        if (this.useFrame) {
            this.resize(800, 640);
            this.handleResize();
            final Dimension size = this.getSize();
            final Dimension screenSize = this.getToolkit().getScreenSize();
            this.setLocation((screenSize.width - size.width) / 2, (screenSize.height - size.height) / 2);
            this.show();
        }
        else {
            this.hide();
            this.handleResize();
            this.applet.validate();
        }
        this.main.requestFocus();
    }
    
    void reinit() {
        this.reinit(true);
    }
    
    void reinit(final boolean b) {
        this.sourceCount = -1;
        System.out.print("reinit " + this.gridSizeX + " " + this.gridSizeY + "\n");
        this.gridSizeXY = this.gridSizeX * this.gridSizeY;
        this.gw = this.gridSizeY;
        this.func = new float[this.gridSizeXY];
        this.funci = new float[this.gridSizeXY];
        this.damp = new float[this.gridSizeXY];
        this.exceptional = new boolean[this.gridSizeXY];
        this.medium = new int[this.gridSizeXY];
        this.walls = new boolean[this.gridSizeXY];
        for (int i = 0; i != this.gridSizeXY; ++i) {
            this.damp[i] = 1.0f;
        }
        for (int j = 0; j != this.windowOffsetX; ++j) {
            for (int k = 0; k != this.gridSizeX; ++k) {
                final float[] damp = this.damp;
                final int n = j + k * this.gw;
                final float[] damp2 = this.damp;
                final int n2 = this.gridSizeX - 1 - j + this.gw * k;
                final float[] damp3 = this.damp;
                final int n3 = k + this.gw * j;
                final float[] damp4 = this.damp;
                final int n4 = k + (this.gridSizeY - 1 - j) * this.gw;
                final float n5 = (float)(0.999 - (this.windowOffsetX - j) * 0.002);
                damp3[n3] = (damp4[n4] = n5);
                damp[n] = (damp2[n2] = n5);
            }
        }
        if (b) {
            this.doSetup();
        }
    }
    
    public void triggerShow() {
        if (!this.shown) {
            this.show();
        }
        this.shown = true;
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
            this.destroyFrame();
            return true;
        }
        return super.handleEvent(event);
    }
    
    void destroyFrame() {
        if (this.applet == null) {
            this.dispose();
        }
        else {
            this.applet.destroyFrame();
        }
    }
    
    void doBlank() {
        for (int i = 0; i != this.gridSizeXY; ++i) {
            this.func[i] = (this.funci[i] = 1.0E-10f);
        }
    }
    
    void doBlankWalls() {
        for (int i = 0; i != this.gridSizeXY; ++i) {
            this.walls[i] = false;
            this.medium[i] = 0;
        }
        this.calcExceptions();
    }
    
    void doBorder() {
        for (int i = 0; i < this.gridSizeX; ++i) {
            this.setWall(i, this.windowOffsetY);
            this.setWall(i, this.windowBottom);
        }
        for (int j = 0; j < this.gridSizeY; ++j) {
            this.setWall(this.windowOffsetX, j);
            this.setWall(this.windowRight, j);
        }
        this.calcExceptions();
    }
    
    void setWall(final int n, final int n2) {
        this.walls[n + this.gw * n2] = true;
    }
    
    void setWall(final int n, final int n2, final boolean b) {
        this.walls[n + this.gw * n2] = b;
    }
    
    void setMedium(final int n, final int n2, final int n3) {
        this.medium[n + this.gw * n2] = n3;
    }
    
    long getTimeMillis() {
        try {
            return (long)this.timerMethod.invoke(null, new Object[0]) / this.timerDiv;
        }
        catch (Exception ex) {
            ex.printStackTrace();
            return 0L;
        }
    }
    
    void calcExceptions() {
        for (int i = 0; i != this.gridSizeX; ++i) {
            for (int j = 0; j < this.windowOffsetY; ++j) {
                this.walls[i + this.gw * j] = this.walls[i + this.gw * this.windowOffsetY];
                this.walls[i + this.gw * (this.gridSizeY - j - 1)] = this.walls[i + this.gw * (this.gridSizeY - this.windowOffsetY - 1)];
            }
        }
        for (int k = 0; k < this.gridSizeY; ++k) {
            for (int l = 0; l < this.windowOffsetX; ++l) {
                this.walls[l + this.gw * k] = this.walls[this.windowOffsetX + this.gw * k];
                this.walls[this.gridSizeX - l - 1 + this.gw * k] = this.walls[this.gridSizeX - this.windowOffsetX - 1 + this.gw * k];
            }
        }
        for (int n = 1; n < this.gridSizeX - 1; ++n) {
            for (int n2 = 1; n2 < this.gridSizeY - 1; ++n2) {
                final int n3 = n + this.gw * n2;
                this.exceptional[n3] = (this.walls[n3 - 1] || this.walls[n3 + 1] || this.walls[n3 - this.gw] || this.walls[n3 + this.gw] || this.walls[n3] || this.medium[n3] != this.medium[n3 - 1] || this.medium[n3] != this.medium[n3 + 1]);
                if (((n == 1 || n == this.gridSizeX - 2) && this.medium[n3] != this.medium[this.gridSizeX - 1 - n + this.gw * (n2 + 1)]) || this.medium[n3] != this.medium[this.gridSizeX - 1 - n + this.gw * (n2 - 1)]) {
                    this.exceptional[n3] = true;
                }
            }
        }
        final boolean[] exceptional = this.exceptional;
        final int n4 = 1 + this.gw;
        final boolean[] exceptional2 = this.exceptional;
        final int n5 = this.gridSizeX - 2 + this.gw;
        final boolean[] exceptional3 = this.exceptional;
        final int n6 = 1 + (this.gridSizeY - 2) * this.gw;
        final boolean[] exceptional4 = this.exceptional;
        final int n7 = this.gridSizeX - 2 + (this.gridSizeY - 2) * this.gw;
        final boolean b = true;
        exceptional3[n6] = (exceptional4[n7] = b);
        exceptional[n4] = (exceptional2[n5] = b);
    }
    
    void centerString(final Graphics graphics, final String s, final int n) {
        graphics.drawString(s, (this.winSize.width - graphics.getFontMetrics().stringWidth(s)) / 2, n);
    }
    
    public void paint(final Graphics graphics) {
        this.cv.repaint();
    }
    
    public void updateRipple(final Graphics graphics) {
        if (this.winSize == null || this.winSize.width == 0) {
            this.handleResize();
            return;
        }
        if (this.increaseResolution) {
            this.increaseResolution = false;
            if (this.resBar.getValue() < 495) {
                this.setResolution(this.resBar.getValue() + 10);
            }
        }
        final long timeMillis = this.getTimeMillis();
        double n = 0.0;
        if (!this.stoppedCheck.getState()) {
            n = 5 * 0.05;
        }
        boolean b = this.dragging && this.selectedSource == -1 && !this.view3dCheck.getState() && this.modeChooser.getSelectedIndex() == 0;
        if (this.stoppedCheck.getState()) {
            b = true;
        }
        final int value = this.speedBar.getValue();
        if (!b) {
            final int n2 = this.gridSizeX - 1;
            final int n3 = this.gridSizeY - 1;
            for (int i = 0; i != value; ++i) {
                int n4;
                int n5;
                int n6;
                if (this.moveDown) {
                    n4 = 1;
                    n5 = n3;
                    n6 = 1;
                    this.moveDown = false;
                }
                else {
                    n4 = n3 - 1;
                    n5 = 0;
                    n6 = -1;
                    this.moveDown = true;
                }
                this.moveRight = this.moveDown;
                float n7 = 0.0f;
                float n8 = 0.0f;
                int n9 = -1;
                for (int j = n4; j != n5; j += n6) {
                    int n10;
                    int n11;
                    int n12;
                    if (this.moveRight) {
                        n10 = 1;
                        n11 = 1;
                        n12 = n2;
                        this.moveRight = false;
                    }
                    else {
                        n10 = -1;
                        n11 = n2 - 1;
                        n12 = 0;
                        this.moveRight = true;
                    }
                    for (int k = j * this.gw + n11; k != j * this.gw + n12; k += n10) {
                        float n13 = this.func[k - 1];
                        float n14 = this.func[k + 1];
                        float n15 = this.func[k - this.gw];
                        float n16 = this.func[k + this.gw];
                        float n17 = (n14 + n13 + n16 + n15) * 0.25f;
                        if (this.exceptional[k]) {
                            if (n9 != this.medium[k]) {
                                n9 = this.medium[k];
                                final double n18 = n * (1.0 - 0.002617801047120419 * n9);
                                final float n19 = (float)Math.sin(n18 / 2.0);
                                n7 = (float)(Math.sin(n18) * this.dampcoef);
                                n8 = (float)(1.0 - Math.sqrt(4.0f * n19 * n19 - n7 * n7));
                            }
                            if (this.walls[k]) {
                                continue;
                            }
                            if (this.fixedEndsCheck.getState()) {
                                if (this.walls[k - 1]) {
                                    n13 = 0.0f;
                                }
                                if (this.walls[k + 1]) {
                                    n14 = 0.0f;
                                }
                                if (this.walls[k - this.gw]) {
                                    n15 = 0.0f;
                                }
                                if (this.walls[k + this.gw]) {
                                    n16 = 0.0f;
                                }
                            }
                            else {
                                if (this.walls[k - 1]) {
                                    n13 = (this.walls[k + 1] ? this.func[k] : this.func[k + 1]);
                                }
                                if (this.walls[k + 1]) {
                                    n14 = (this.walls[k - 1] ? this.func[k] : this.func[k - 1]);
                                }
                                if (this.walls[k - this.gw]) {
                                    n15 = (this.walls[k + this.gw] ? this.func[k] : this.func[k + this.gw]);
                                }
                                if (this.walls[k + this.gw]) {
                                    n16 = (this.walls[k - this.gw] ? this.func[k] : this.func[k - this.gw]);
                                }
                            }
                            n17 = (n14 + n13 + n16 + n15) * 0.25f;
                        }
                        float n20;
                        float n21;
                        if (this.damp[k] == 1.0f) {
                            n20 = this.func[k] - n17;
                            n21 = this.funci[k];
                        }
                        else {
                            n20 = (this.func[k] - n17) * this.damp[k];
                            n21 = this.funci[k] * this.damp[k];
                        }
                        this.func[k] = n17 + n20 * n8 - n21 * n7;
                        this.funci[k] = n21 * n8 + n20 * n7;
                    }
                }
                this.t += n;
                if (this.sourceCount > 0) {
                    double n23;
                    double n22 = n23 = this.freqBar.getValue() * (this.t - this.freqTimeZero) * 0.0233333;
                    boolean b2 = false;
                    switch (this.auxFunction) {
                        case 2: {
                            n23 = this.auxBar.getValue() * this.t * 0.0233333;
                            break;
                        }
                        case 1: {
                            n23 = n22 + (this.auxBar.getValue() - 1) * 0.10833078115826873;
                            break;
                        }
                    }
                    double cos = 0.0;
                    double cos2 = 0.0;
                    switch (this.sourceWaveform) {
                        case 0: {
                            cos = Math.cos(n22);
                            if (this.sourceCount >= (this.sourcePlane ? 4 : 2)) {
                                cos2 = Math.cos(n23);
                                break;
                            }
                            if (this.sourceFreqCount == 2) {
                                cos = (cos + Math.cos(n23)) * 0.5;
                                break;
                            }
                            break;
                        }
                        case 1: {
                            n22 %= 6.283185307179586;
                            cos = ((n22 < 3.141592653589793) ? 1.0 : -1.0);
                            break;
                        }
                        case 2: {
                            n22 %= 6.283185307179586;
                            double n24 = 0.7853981633974483;
                            final double n25 = this.freqBar.getValue() * 0.2;
                            if (n25 < n24) {
                                n24 = n25;
                            }
                            cos = ((n22 > n24) ? 0.0 : Math.sin(n22 * 3.141592653589793 / n24));
                            if (n22 > n24 * 2.0) {
                                b2 = true;
                                break;
                            }
                            break;
                        }
                    }
                    for (int l = 0; l != this.sourceCount; ++l) {
                        if (l % 2 == 0) {
                            this.sources[l].v = (float)(cos * this.setup.sourceStrength());
                        }
                        else {
                            this.sources[l].v = (float)(cos2 * this.setup.sourceStrength());
                        }
                    }
                    if (this.sourcePlane) {
                        if (!b2) {
                            for (int n26 = 0; n26 != this.sourceCount / 2; ++n26) {
                                final OscSource oscSource = this.sources[n26 * 2];
                                final OscSource oscSource2 = this.sources[n26 * 2 + 1];
                                this.drawPlaneSource(oscSource.x, oscSource.y, oscSource2.x, oscSource2.y, this.sources[n26].v, n22);
                            }
                        }
                    }
                    else {
                        if (this.sourceMoving) {
                            this.movingSourcePos += n * 0.02 * this.auxBar.getValue();
                            final double movingSourcePos = this.movingSourcePos;
                            final int n27 = this.windowHeight - 3;
                            int n28 = (int)(movingSourcePos % (n27 * 2));
                            if (n28 > n27) {
                                n28 = 2 * n27 - n28;
                            }
                            this.sources[0].y = n28 + (this.windowOffsetY + 1);
                        }
                        for (int n29 = 0; n29 != this.sourceCount; ++n29) {
                            final OscSource oscSource3 = this.sources[n29];
                            this.func[oscSource3.x + this.gw * oscSource3.y] = oscSource3.v;
                            this.funci[oscSource3.x + this.gw * oscSource3.y] = 0.0f;
                        }
                    }
                }
                this.setup.eachFrame();
                ++this.steps;
                this.filterGrid();
            }
        }
        this.brightMult = Math.exp(this.brightnessBar.getValue() / 100.0 - 5.0);
        if (this.view3dCheck.getState()) {
            this.draw3dView();
        }
        else {
            this.draw2dView();
        }
        if (this.imageSource != null) {
            this.imageSource.newPixels();
        }
        graphics.drawImage(this.dbimage, 0, 0, this);
        if (this.dragStartX >= 0 && !this.view3dCheck.getState()) {
            final String string = "(" + this.dragStartX * this.windowWidth / this.winSize.width + "," + (this.windowHeight - 1 - this.dragStartY * this.windowHeight / this.winSize.height) + ")";
            graphics.setColor(Color.white);
            final FontMetrics fontMetrics = graphics.getFontMetrics();
            final int n30 = 5 + fontMetrics.getAscent();
            graphics.fillRect(0, this.winSize.height - n30, fontMetrics.stringWidth(string) + 10, n30);
            graphics.setColor(Color.black);
            graphics.drawString(string, 5, this.winSize.height - 5);
        }
        if (!this.stoppedCheck.getState()) {
            final long n31 = this.getTimeMillis() - timeMillis;
            if (this.adjustResolution && n31 > 0L && timeMillis < this.startTime + 1000L && this.windowOffsetX * n31 / value < 55L) {
                this.increaseResolution = true;
                this.startTime = timeMillis;
            }
            if (this.dragging && this.selectedSource == -1 && this.modeChooser.getSelectedIndex() == 3) {
                this.editFuncPoint(this.dragX, this.dragY);
            }
            this.cv.repaint(0L);
        }
    }
    
    void filterGrid() {
        if (this.fixedEndsCheck.getState()) {
            return;
        }
        if (this.sourceCount > 0 && this.freqBarValue > 23) {
            return;
        }
        if (this.sourceFreqCount >= 2 && this.auxBar.getValue() > 23) {
            return;
        }
        if (++this.filterCount < 10) {
            return;
        }
        this.filterCount = 0;
        for (int i = this.windowOffsetY; i < this.windowBottom; ++i) {
            for (int j = this.windowOffsetX; j < this.windowRight; ++j) {
                final int n = j + i * this.gw;
                if (!this.walls[n]) {
                    if (this.func[n - 1] < 0.0f && this.func[n] > 0.0f && this.func[n + 1] < 0.0f && !this.walls[n + 1] && !this.walls[n - 1]) {
                        this.func[n] = (this.func[n - 1] + this.func[n + 1]) / 2.0f;
                    }
                    if (this.func[n - this.gw] < 0.0f && this.func[n] > 0.0f && this.func[n + this.gw] < 0.0f && !this.walls[n - this.gw] && !this.walls[n + this.gw]) {
                        this.func[n] = (this.func[n - this.gw] + this.func[n + this.gw]) / 2.0f;
                    }
                    if (this.func[n - 1] > 0.0f && this.func[n] < 0.0f && this.func[n + 1] > 0.0f && !this.walls[n + 1] && !this.walls[n - 1]) {
                        this.func[n] = (this.func[n - 1] + this.func[n + 1]) / 2.0f;
                    }
                    if (this.func[n - this.gw] > 0.0f && this.func[n] < 0.0f && this.func[n + this.gw] > 0.0f && !this.walls[n - this.gw] && !this.walls[n + this.gw]) {
                        this.func[n] = (this.func[n - this.gw] + this.func[n + this.gw]) / 2.0f;
                    }
                }
            }
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
        int n5 = this.sourceColor.getRed() << 16 | this.sourceColor.getGreen() << 8 | this.sourceColor.getBlue() | 0xFF000000;
        if (n == this.selectedSource) {
            n5 ^= 0xFFFFFF;
        }
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
    
    void draw2dView() {
        for (int i = 0; i != this.windowHeight; ++i) {
            int n = this.winSize.width * (i * this.winSize.height / this.windowHeight);
            int n2 = (i + this.windowOffsetY) * this.gw + this.windowOffsetX;
            final int n3 = i * this.winSize.height / this.windowHeight;
            final int n4 = (i + 1) * this.winSize.height / this.windowHeight;
            for (int j = 0; j != this.windowWidth; ++j, ++n2) {
                final int n5 = j * this.winSize.width / this.windowWidth;
                final int n6 = (j + 1) * this.winSize.width / this.windowWidth;
                final int n7 = j + this.windowOffsetX;
                double n8 = this.func[n2] * this.brightMult;
                if (n8 < -1.0) {
                    n8 = -1.0;
                }
                if (n8 > 1.0) {
                    n8 = 1.0;
                }
                int red;
                int green;
                int blue;
                if (this.walls[n2]) {
                    red = this.wallColor.getRed();
                    green = this.wallColor.getGreen();
                    blue = this.wallColor.getBlue();
                }
                else if (n8 < 0.0) {
                    final double n9 = -n8;
                    final double n10 = 1.0 - n9;
                    final double n11 = this.medium[n2] * 0.003921414846476609;
                    final double n12 = 1.0 - n11;
                    final double n13 = n9 * n12;
                    final double n14 = n10 * n12;
                    final double n15 = n9 * n11;
                    final double n16 = n10 * n11;
                    red = (int)(this.negColor.getRed() * n13 + this.zeroColor.getRed() * n14 + this.negMedColor.getRed() * n15 + this.medColor.getRed() * n16);
                    green = (int)(this.negColor.getGreen() * n13 + this.zeroColor.getGreen() * n14 + this.negMedColor.getGreen() * n15 + this.medColor.getGreen() * n16);
                    blue = (int)(this.negColor.getBlue() * n13 + this.zeroColor.getBlue() * n14 + this.negMedColor.getBlue() * n15 + this.medColor.getBlue() * n16);
                }
                else {
                    final double n17 = n8;
                    final double n18 = 1.0 - n8;
                    final double n19 = this.medium[n2] * 0.003921414846476609;
                    final double n20 = 1.0 - n19;
                    final double n21 = n17 * n20;
                    final double n22 = n18 * n20;
                    final double n23 = n17 * n19;
                    final double n24 = n18 * n19;
                    red = (int)(this.posColor.getRed() * n21 + this.zeroColor.getRed() * n22 + this.posMedColor.getRed() * n23 + this.medColor.getRed() * n24);
                    green = (int)(this.posColor.getGreen() * n21 + this.zeroColor.getGreen() * n22 + this.posMedColor.getGreen() * n23 + this.medColor.getGreen() * n24);
                    blue = (int)(this.posColor.getBlue() * n21 + this.zeroColor.getBlue() * n22 + this.posMedColor.getBlue() * n23 + this.medColor.getBlue() * n24);
                }
                final int n25 = 0xFF000000 | red << 16 | green << 8 | blue;
                for (int k = 0; k != n6 - n5; ++k, ++n) {
                    for (int l = 0; l != n4 - n3; ++l) {
                        this.pixels[n + l * this.winSize.width] = n25;
                    }
                }
            }
        }
        final int n26 = (this.gridSizeY / 2 - this.windowOffsetY) * this.winSize.height / this.windowHeight;
        for (int n27 = 0; n27 != this.sourceCount; ++n27) {
            final OscSource oscSource = this.sources[n27];
            this.plotSource(n27, oscSource.getScreenX(), oscSource.getScreenY());
        }
    }
    
    void map3d(final double n, final double n2, final double n3, final int[] array, final int[] array2, final int n4) {
        final double n5 = this.realxmx * n + this.realxmy * n2;
        final double n6 = this.realymz * n3 + this.realymadd;
        final double n7 = this.realzmx * n + this.realzmy * n2 + this.realzmadd;
        array[n4] = this.centerX3d + (int)(n5 / n7);
        array2[n4] = this.centerY3d - (int)(n6 / n7);
    }
    
    void scaleworld() {
        this.scalex = this.viewZoom * (this.winSize.width / 4) * 66.0 / 8.0;
        this.scaley = -this.scalex;
        final int n = (int)(this.scaley * this.viewHeight / 66.0);
        this.centerX3d = this.winSize.width / 2;
        this.centerY3d = this.winSize.height / 2 - n;
        this.scaleMult = 16.0 / (this.windowWidth / 2);
        this.realxmx = -this.viewAngleCos * this.scaleMult * this.scalex;
        this.realxmy = this.viewAngleSin * this.scaleMult * this.scalex;
        this.realymz = -this.brightMult * this.scaley;
        this.realzmy = this.viewAngleCos * this.scaleMult;
        this.realzmx = this.viewAngleSin * this.scaleMult;
        this.realymadd = -this.viewHeight * this.scaley;
        this.realzmadd = 66.0;
    }
    
    void draw3dView() {
        final int n = this.gridSizeX / 2;
        this.scaleworld();
        final int n2 = this.windowRight - 1;
        int windowOffsetY;
        int n3;
        int n4;
        if (this.viewAngleCos > 0.0) {
            windowOffsetY = n2;
            n3 = this.windowOffsetY - 1;
            n4 = -1;
        }
        else {
            windowOffsetY = this.windowOffsetY;
            n3 = n2 + 1;
            n4 = 1;
        }
        int windowOffsetX;
        int n5;
        int n6;
        if (this.viewAngleSin < 0.0) {
            windowOffsetX = this.windowOffsetX;
            n5 = n2 + 1;
            n6 = 1;
        }
        else {
            windowOffsetX = n2;
            n5 = this.windowOffsetX - 1;
            n6 = -1;
        }
        final boolean b = this.viewAngleSin * n6 < this.viewAngleCos * n4;
        for (int i = 0; i != this.winSize.width * this.winSize.height; ++i) {
            this.pixels[i] = -16777216;
        }
        final double n7 = 0.1;
        final double n8 = n7 * n7;
        for (int j = windowOffsetX; j != n5; j += n6) {
            for (int k = windowOffsetY; k != n3; k += n4) {
                if (!b) {
                    j = windowOffsetX;
                }
                while (j != n5) {
                    final int n9 = j + this.gw * k;
                    this.map3d(j - n, k - n, this.func[n9], this.xpoints, this.ypoints, 0);
                    this.map3d(j + 1 - n, k - n, this.func[n9 + 1], this.xpoints, this.ypoints, 1);
                    this.map3d(j - n, k + 1 - n, this.func[n9 + this.gw], this.xpoints, this.ypoints, 2);
                    this.map3d(j + 1 - n, k + 1 - n, this.func[n9 + this.gw + 1], this.xpoints, this.ypoints, 3);
                    final double n10 = this.func[n9 + 1] - this.func[n9];
                    final double n11 = this.func[n9 + this.gw] - this.func[n9];
                    final int computeColor = this.computeColor(n9, (n10 + n11 + n7) * 0.5780346820809249 / Math.sqrt(n10 * n10 + n11 * n11 + n8));
                    this.fillTriangle(this.xpoints[0], this.ypoints[0], this.xpoints[1], this.ypoints[1], this.xpoints[3], this.ypoints[3], computeColor);
                    this.fillTriangle(this.xpoints[0], this.ypoints[0], this.xpoints[2], this.ypoints[2], this.xpoints[3], this.ypoints[3], computeColor);
                    if (b) {
                        break;
                    }
                    j += n6;
                }
            }
            if (!b) {
                break;
            }
        }
    }
    
    int computeColor(final int n, double n2) {
        final double n3 = this.func[n] * this.brightMult;
        if (n2 < 0.0) {
            n2 = 0.0;
        }
        if (n2 > 1.0) {
            n2 = 1.0;
        }
        n2 = 0.5 + n2 * 0.5;
        double n4 = (n3 < 0.0) ? (-n3) : 0.0;
        double n5 = (n3 > 0.0) ? n3 : 0.0;
        if (n4 > 1.0) {
            n4 = 1.0;
        }
        if (n5 > 1.0) {
            n5 = 1.0;
        }
        if (n5 < 0.0) {
            n5 = 0.0;
        }
        if (n4 < 0.0) {
            n4 = 0.0;
        }
        double n7;
        final double n6 = n7 = (1.0 - (n4 + n5)) * n2;
        if (this.medium[n] > 0) {
            n7 *= 1.0 - this.medium[n] * 0.003921414846476609;
        }
        final double n8 = 0.6;
        return 0xFF000000 | (int)((n2 * n4 + n8 * n7) * 255.0) << 16 | (int)((n2 * n5 + n8 * n7) * 255.0) << 8 | (int)(n8 * n6 * 255.0);
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
        if (i >= this.winSize.width) {
            i = this.winSize.width - 1;
            if (n3 >= this.winSize.width) {
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
            if (interp >= this.winSize.height) {
                interp = this.winSize.height - 1;
            }
            while (j <= interp) {
                this.pixels[i + j * this.winSize.width] = n6;
                ++j;
            }
            i += n7;
            if (i < 0 || i >= this.winSize.width) {
                return;
            }
        }
    }
    
    int abs(final int n) {
        return (n < 0) ? (-n) : n;
    }
    
    void drawPlaneSource(int n, int n2, int n3, int n4, final float n5, final double n6) {
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
            this.func[n + this.gw * n2] = n5;
            this.funci[n + this.gw * n2] = 0.0f;
        }
        else if (this.abs(n4 - n2) > this.abs(n3 - n)) {
            final double n7 = this.sign(n4 - n2);
            for (int n8 = n2; n8 != n4 + n7; n8 += (int)n7) {
                final int n9 = n + (n3 - n) * (n8 - n2) / (n4 - n2);
                final double n10 = n7 * (n8 - n2) / (n4 - n2);
                final int n11 = n9 + this.gw * n8;
                this.func[n11] = this.setup.calcSourcePhase(n10, n5, n6);
                this.funci[n11] = 0.0f;
            }
        }
        else {
            final double n12 = this.sign(n3 - n);
            for (int n13 = n; n13 != n3 + n12; n13 += (int)n12) {
                final int n14 = n2 + (n4 - n2) * (n13 - n) / (n3 - n);
                final double n15 = n12 * (n13 - n) / (n3 - n);
                final int n16 = n13 + this.gw * n14;
                this.func[n16] = this.setup.calcSourcePhase(n15, n5, n6);
                this.funci[n16] = 0.0f;
            }
        }
    }
    
    int sign(final int n) {
        return (n < 0) ? -1 : ((n == 0) ? 0 : 1);
    }
    
    void edit(final MouseEvent mouseEvent) {
        if (this.view3dCheck.getState()) {
            return;
        }
        final int x = mouseEvent.getX();
        final int y = mouseEvent.getY();
        if (this.selectedSource != -1) {
            final int n = x * this.windowWidth / this.winSize.width;
            final int n2 = y * this.windowHeight / this.winSize.height;
            if (n >= 0 && n2 >= 0 && n < this.windowWidth && n2 < this.windowHeight) {
                this.sources[this.selectedSource].x = n + this.windowOffsetX;
                this.sources[this.selectedSource].y = n2 + this.windowOffsetY;
            }
            return;
        }
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
    }
    
    void editFuncPoint(final int n, final int n2) {
        final int n3 = n * this.windowWidth / this.winSize.width + this.windowOffsetX + (n2 * this.windowHeight / this.winSize.height + this.windowOffsetY) * this.gw;
        if (this.modeChooser.getSelectedIndex() == 1) {
            if (!this.dragSet && !this.dragClear) {
                this.dragClear = this.walls[n3];
                this.dragSet = !this.dragClear;
            }
            this.walls[n3] = this.dragSet;
            this.calcExceptions();
            this.func[n3] = (this.funci[n3] = 0.0f);
        }
        else if (this.modeChooser.getSelectedIndex() == 2) {
            if (!this.dragSet && !this.dragClear) {
                this.dragClear = (this.medium[n3] > 0);
                this.dragSet = !this.dragClear;
            }
            this.medium[n3] = (this.dragSet ? 191 : 0);
            this.calcExceptions();
        }
        else {
            if (!this.dragSet && !this.dragClear) {
                this.dragClear = (this.func[n3] > 0.1);
                this.dragSet = !this.dragClear;
            }
            this.func[n3] = (this.dragSet ? 1.0f : -1.0f);
            this.funci[n3] = 0.0f;
        }
        this.cv.repaint(0L);
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
    
    void setDamping() {
        this.dampcoef = 1.0;
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
        if (actionEvent.getSource() == this.blankButton) {
            this.doBlank();
            this.cv.repaint();
        }
        if (actionEvent.getSource() == this.blankWallsButton) {
            this.doBlankWalls();
            this.cv.repaint();
        }
        if (actionEvent.getSource() == this.borderButton) {
            this.doBorder();
            this.cv.repaint();
        }
        if (actionEvent.getSource() == this.exportButton) {
            this.doImport();
        }
    }
    
    public void adjustmentValueChanged(final AdjustmentEvent adjustmentEvent) {
        System.out.print(((Scrollbar)adjustmentEvent.getSource()).getValue() + "\n");
        if (adjustmentEvent.getSource() == this.resBar) {
            this.setResolution();
            this.reinit();
        }
        if (adjustmentEvent.getSource() == this.dampingBar) {
            this.setDamping();
        }
        if (adjustmentEvent.getSource() == this.brightnessBar) {
            this.cv.repaint(0L);
        }
        if (adjustmentEvent.getSource() == this.freqBar) {
            this.setFreq();
        }
    }
    
    void setFreqBar(final int n) {
        this.freqBar.setValue(n);
        this.freqBarValue = n;
        this.freqTimeZero = 0.0;
    }
    
    void setFreq() {
        final double n = this.freqBarValue * 0.0233333;
        this.freqBarValue = this.freqBar.getValue();
        this.freqTimeZero = this.t - n * (this.t - this.freqTimeZero) / (this.freqBarValue * 0.0233333);
    }
    
    void setResolution() {
        final int value = this.resBar.getValue();
        this.windowHeight = value;
        this.windowWidth = value;
        int n = this.windowWidth / 9;
        if (n < 20) {
            n = 20;
        }
        final int n2 = n;
        this.windowOffsetY = n2;
        this.windowOffsetX = n2;
        this.gridSizeX = this.windowWidth + this.windowOffsetX * 2;
        this.gridSizeY = this.windowHeight + this.windowOffsetY * 2;
        this.windowBottom = this.windowOffsetY + this.windowHeight - 1;
        this.windowRight = this.windowOffsetX + this.windowWidth - 1;
    }
    
    void setResolution(final int value) {
        this.resBar.setValue(value);
        this.setResolution();
        this.reinit();
    }
    
    public void mouseDragged(final MouseEvent mouseEvent) {
        if (this.view3dCheck.getState()) {
            this.view3dDrag(mouseEvent);
        }
        if (!this.dragging) {
            this.selectSource(mouseEvent);
        }
        this.dragging = true;
        this.edit(mouseEvent);
        this.adjustResolution = false;
        this.cv.repaint(0L);
    }
    
    public void mouseMoved(final MouseEvent mouseEvent) {
        if (this.dragging) {
            return;
        }
        final int x = mouseEvent.getX();
        final int y = mouseEvent.getY();
        final int n = x;
        this.dragX = n;
        this.dragStartX = n;
        final int n2 = y;
        this.dragY = n2;
        this.dragStartY = n2;
        this.viewAngleDragStart = this.viewAngle;
        this.viewHeightDragStart = this.viewHeight;
        this.selectSource(mouseEvent);
        if (this.stoppedCheck.getState()) {
            this.cv.repaint(0L);
        }
    }
    
    void view3dDrag(final MouseEvent mouseEvent) {
        final int x = mouseEvent.getX();
        final int y = mouseEvent.getY();
        this.viewAngle = (this.dragStartX - x) / 40.0 + this.viewAngleDragStart;
        while (this.viewAngle < 0.0) {
            this.viewAngle += 6.283185307179586;
        }
        while (this.viewAngle >= 6.283185307179586) {
            this.viewAngle -= 6.283185307179586;
        }
        this.viewAngleCos = Math.cos(this.viewAngle);
        this.viewAngleSin = Math.sin(this.viewAngle);
        this.viewHeight = (this.dragStartY - y) / 10.0 + this.viewHeightDragStart;
        this.cv.repaint();
    }
    
    public void mouseClicked(final MouseEvent mouseEvent) {
    }
    
    public void mouseEntered(final MouseEvent mouseEvent) {
    }
    
    public void mouseExited(final MouseEvent mouseEvent) {
        this.dragStartX = -1;
    }
    
    public void mousePressed(final MouseEvent mouseEvent) {
        this.adjustResolution = false;
        this.mouseMoved(mouseEvent);
        if ((mouseEvent.getModifiers() & 0x10) == 0x0) {
            return;
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
        if (itemEvent.getItemSelectable() == this.stoppedCheck) {
            this.cv.repaint();
            return;
        }
        if (itemEvent.getItemSelectable() == this.sourceChooser && this.sourceChooser.getSelectedIndex() != this.sourceIndex) {
            this.setSources();
        }
        if (itemEvent.getItemSelectable() == this.setupChooser) {
            this.doSetup();
        }
        if (itemEvent.getItemSelectable() == this.colorChooser) {
            this.doColor();
        }
    }
    
    void doSetup() {
        this.t = 0.0;
        if (this.resBar.getValue() < 32) {
            this.setResolution(32);
        }
        this.doBlank();
        this.doBlankWalls();
        this.sourceCount = -1;
        this.sourceChooser.select(1);
        this.dampingBar.setValue(10);
        this.setFreqBar(5);
        this.setBrightness(10);
        this.auxBar.setValue(1);
        this.fixedEndsCheck.setState(true);
        (this.setup = this.setupList.elementAt(this.setupChooser.getSelectedIndex())).select();
        this.setup.doSetupSources();
        this.calcExceptions();
        this.setDamping();
    }
    
    void setBrightness(final int n) {
        this.brightnessBar.setValue((int)((Math.log(n / 5.0) + 5.0) * 100.0));
    }
    
    void doColor() {
        final int selectedIndex = this.colorChooser.getSelectedIndex();
        this.wallColor = this.schemeColors[selectedIndex][0];
        this.posColor = this.schemeColors[selectedIndex][1];
        this.negColor = this.schemeColors[selectedIndex][2];
        this.zeroColor = this.schemeColors[selectedIndex][3];
        this.posMedColor = this.schemeColors[selectedIndex][4];
        this.negMedColor = this.schemeColors[selectedIndex][5];
        this.medColor = this.schemeColors[selectedIndex][6];
        this.sourceColor = this.schemeColors[selectedIndex][7];
    }
    
    void addDefaultColorScheme() {
        final String[] array = { "#808080 #00ffff #000000 #008080 #0000ff #000000 #000080 #ffffff", "#808080 #00ff00 #ff0000 #000000 #00ffff #ff00ff #0000ff #0000ff", "#800000 #00ffff #0000ff #000000 #80c8c8 #8080c8 #808080 #ffffff", "#800000 #ffffff #000000 #808080 #0000ff #000000 #000080 #00ff00", "#800000 #ffff00 #0000ff #000000 #ffff80 #8080ff #808080 #ffffff", "#808080 #00ff00 #ff0000 #FFFFFF #00ffff #ff00ff #0000ff #0000ff", "#FF0000 #00FF00 #0000FF #FFFF00 #00FFFF #FF00FF #FFFFFF #000000" };
        for (int i = 0; i != 7; ++i) {
            this.decodeColorScheme(i, array[i]);
        }
    }
    
    void decodeColorScheme(final int n, final String s) {
        final StringTokenizer stringTokenizer = new StringTokenizer(s);
        while (stringTokenizer.hasMoreTokens()) {
            for (int i = 0; i != 8; ++i) {
                this.schemeColors[n][i] = Color.decode(stringTokenizer.nextToken());
            }
        }
        this.colorChooser.add("Color Scheme " + (n + 1));
    }
    
    void addMedium() {
        for (int i = 0; i != this.gridSizeX; ++i) {
            for (int j = this.gridSizeY / 2; j != this.gridSizeY; ++j) {
                this.medium[i + j * this.gw] = 191;
            }
        }
    }
    
    void setSources() {
        this.sourceIndex = this.sourceChooser.getSelectedIndex();
        final int sourceCount = this.sourceCount;
        final boolean sourcePlane = this.sourcePlane;
        this.sourceFreqCount = 1;
        this.sourcePlane = (this.sourceChooser.getSelectedIndex() >= 10 && this.sourceChooser.getSelectedIndex() < 16);
        this.sourceMoving = false;
        this.sourceWaveform = 0;
        this.sourceCount = 1;
        boolean b = false;
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
            case 8: {
                this.sourceWaveform = 2;
                break;
            }
            case 9: {
                this.sourceMoving = true;
                break;
            }
            case 11: {
                this.sourceFreqCount = 2;
                break;
            }
            case 12: {
                this.sourceCount = 2;
                break;
            }
            case 13: {
                final int n = 2;
                this.sourceFreqCount = n;
                this.sourceCount = n;
                break;
            }
            case 14: {
                this.sourceWaveform = 2;
                break;
            }
            case 15: {
                b = true;
                break;
            }
            case 16: {
                this.sourceCount = 6;
                break;
            }
            case 17: {
                this.sourceCount = 8;
                break;
            }
            case 18: {
                this.sourceCount = 10;
                break;
            }
            case 19: {
                this.sourceCount = 12;
                break;
            }
            case 20: {
                this.sourceCount = 16;
                break;
            }
            case 21: {
                this.sourceCount = 20;
                break;
            }
        }
        if (this.sourceFreqCount >= 2) {
            this.auxFunction = 2;
            this.auxBar.setValue(this.freqBar.getValue());
            if (this.sourceCount == 2) {
                this.auxLabel.setText("Source 2 Frequency");
            }
            else {
                this.auxLabel.setText("2nd Frequency");
            }
        }
        else if (this.sourceCount == 2 || this.sourceCount >= 4 || b) {
            this.auxFunction = 1;
            this.auxBar.setValue(1);
            this.auxLabel.setText("Phase Difference");
        }
        else if (this.sourceMoving) {
            this.auxFunction = 3;
            this.auxBar.setValue(7);
            this.auxLabel.setText("Source Speed");
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
                this.sources[0] = new OscSource(this.windowOffsetX, this.windowOffsetY + 1);
                this.sources[1] = new OscSource(n2, this.windowOffsetY + 1);
                this.sources[2] = new OscSource(this.windowOffsetX, n3);
                this.sources[3] = new OscSource(n2, n3);
            }
        }
        else if (sourceCount != this.sourceCount || sourcePlane) {
            this.sources[0] = new OscSource(this.gridSizeX / 2, this.windowOffsetY + 1);
            this.sources[1] = new OscSource(this.gridSizeX / 2, this.gridSizeY - this.windowOffsetY - 2);
            this.sources[2] = new OscSource(this.windowOffsetX + 1, this.gridSizeY / 2);
            this.sources[3] = new OscSource(this.gridSizeX - this.windowOffsetX - 2, this.gridSizeY / 2);
            for (int i = 4; i < this.sourceCount; ++i) {
                this.sources[i] = new OscSource(this.windowOffsetX + 1 + i * 2, this.gridSizeY / 2);
            }
        }
    }
    
    void doImport() {
        if (this.impDialog != null) {
            this.requestFocus();
            this.impDialog.setVisible(false);
            this.impDialog = null;
        }
        String s = "$ 0 " + this.resBar.getValue() + " " + this.sourceChooser.getSelectedIndex() + " " + this.colorChooser.getSelectedIndex() + " " + this.fixedEndsCheck.getState() + " " + this.view3dCheck.getState() + " " + this.speedBar.getValue() + " " + this.freqBar.getValue() + " " + this.brightnessBar.getValue() + " " + this.auxBar.getValue() + "\n";
        for (int i = 0; i != this.sourceCount; ++i) {
            final OscSource oscSource = this.sources[i];
            s = s + "s " + oscSource.x + " " + oscSource.y + "\n";
        }
        int j = 0;
        while (j != this.gridSizeXY) {
            if (j >= this.gridSizeX) {
                final int n = j;
                while (j < this.gridSizeXY && this.walls[j] == this.walls[j - this.gridSizeX] && this.medium[j] == this.medium[j - this.gridSizeX]) {
                    ++j;
                }
                if (j > n) {
                    s = s + "l " + (j - n) + "\n";
                    continue;
                }
            }
            final boolean b = this.walls[j];
            final int n2 = this.medium[j];
            int n3 = 0;
            while (j < this.gridSizeXY && this.walls[j] == b && this.medium[j] == n2) {
                ++n3;
                ++j;
            }
            s = s + (b ? "w " : "c ") + n3 + " " + n2 + "\n";
        }
        (this.impDialog = new ImportDialog(this, s)).show();
    }
    
    void readImport(final String s) {
        final byte[] bytes = s.getBytes();
        final int length = s.length();
        int n = 0;
        int n2 = 0;
        this.setupChooser.select(0);
        this.setup = this.setupList.elementAt(0);
        int j;
        for (int i = 0; i < length; i += j) {
            int n3 = 0;
            j = 0;
            while (j != length - i) {
                if (bytes[j + i] == 10 || bytes[j + i] == 13) {
                    n3 = j++;
                    if (j + i < bytes.length && bytes[j + i] == 10) {
                        ++j;
                        break;
                    }
                    break;
                }
                else {
                    ++j;
                }
            }
            final StringTokenizer stringTokenizer = new StringTokenizer(new String(bytes, i, n3));
            if (stringTokenizer.hasMoreTokens()) {
                final char char1 = stringTokenizer.nextToken().charAt(0);
                try {
                    if (char1 == '$') {
                        new Integer(stringTokenizer.nextToken());
                        this.resBar.setValue(new Integer(stringTokenizer.nextToken()));
                        this.setResolution();
                        this.reinit(false);
                        this.sourceChooser.select(new Integer(stringTokenizer.nextToken()));
                        this.setSources();
                        this.colorChooser.select(new Integer(stringTokenizer.nextToken()));
                        this.doColor();
                        this.fixedEndsCheck.setState(stringTokenizer.nextToken().compareTo("true") == 0);
                        this.view3dCheck.setState(stringTokenizer.nextToken().compareTo("true") == 0);
                        this.speedBar.setValue(new Integer(stringTokenizer.nextToken()));
                        this.freqBar.setValue(new Integer(stringTokenizer.nextToken()));
                        this.brightnessBar.setValue(new Integer(stringTokenizer.nextToken()));
                        this.auxBar.setValue(new Integer(stringTokenizer.nextToken()));
                    }
                    else if (char1 == 'w' || char1 == 'c') {
                        final boolean b = char1 == 'w';
                        int k = new Integer(stringTokenizer.nextToken());
                        final int intValue = new Integer(stringTokenizer.nextToken());
                        while (k > 0) {
                            this.walls[n] = b;
                            this.medium[n] = intValue;
                            --k;
                            ++n;
                        }
                    }
                    else if (char1 == 'l') {
                        for (int l = new Integer(stringTokenizer.nextToken()); l > 0; --l, ++n) {
                            this.walls[n] = this.walls[n - this.gridSizeX];
                            this.medium[n] = this.medium[n - this.gridSizeX];
                        }
                    }
                    else if (char1 == 's') {
                        final int intValue2 = new Integer(stringTokenizer.nextToken());
                        final int intValue3 = new Integer(stringTokenizer.nextToken());
                        this.sources[n2].x = intValue2;
                        this.sources[n2].y = intValue3;
                        ++n2;
                    }
                    else {
                        System.out.println("unknown type!");
                    }
                }
                catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        }
        this.calcExceptions();
        this.setDamping();
    }
    
    void setupMode(final int n, final int n2, final int n3, final int n4, final int n5, final int n6) {
        for (int i = 0; i != n3; ++i) {
            for (int j = 0; j != n4; ++j) {
                final int n7 = i + n + this.gw * (j + n2);
                this.func[n7] = (float)(Math.sin(3.141592653589793 * n5 * (i + 1) / (n3 + 1)) * Math.sin(3.141592653589793 * n6 * (j + 1) / (n4 + 1)));
                this.funci[n7] = 0.0f;
            }
        }
    }
    
    void setupAcousticMode(final int n, final int n2, final int n3, final int n4, final int n5, final int n6) {
        if (n5 == 0 && n6 == 0) {
            return;
        }
        for (int i = 0; i != n3; ++i) {
            for (int j = 0; j != n4; ++j) {
                final int n7 = i + n + this.gw * (j + n2);
                this.func[n7] = (float)(Math.cos(3.141592653589793 * n5 * i / (n3 - 1)) * Math.cos(3.141592653589793 * n6 * j / (n4 - 1)));
                this.funci[n7] = 0.0f;
            }
        }
    }
    
    class OscSource
    {
        int x;
        int y;
        float v;
        
        OscSource(final int x, final int y) {
            this.x = x;
            this.y = y;
        }
        
        int getScreenX() {
            return ((this.x - RippleFrame.this.windowOffsetX) * RippleFrame.this.winSize.width + RippleFrame.this.winSize.width / 2) / RippleFrame.this.windowWidth;
        }
        
        int getScreenY() {
            return ((this.y - RippleFrame.this.windowOffsetY) * RippleFrame.this.winSize.height + RippleFrame.this.winSize.height / 2) / RippleFrame.this.windowHeight;
        }
    }
    
    class ImportDialogLayout implements LayoutManager
    {
        public void addLayoutComponent(final String s, final Component component) {
        }
        
        public void removeLayoutComponent(final Component component) {
        }
        
        public Dimension preferredLayoutSize(final Container container) {
            return new Dimension(500, 500);
        }
        
        public Dimension minimumLayoutSize(final Container container) {
            return new Dimension(100, 100);
        }
        
        public void layoutContainer(final Container container) {
            final Insets insets = container.insets();
            final int n = container.size().width - insets.left - insets.right;
            final int n2 = container.size().height - (insets.top + insets.bottom);
            if (container.getComponentCount() == 0) {
                return;
            }
            final Dimension preferredSize = container.getComponent(container.getComponentCount() - 1).getPreferredSize();
            container.getComponent(0).move(insets.left, insets.top);
            final int n3 = container.size().width - insets.left - insets.right;
            final int n4 = container.size().height - insets.top - insets.bottom - preferredSize.height;
            container.getComponent(0).resize(n3, n4);
            final int n5 = n4 + insets.top;
            int n6 = 0;
            for (int i = 1; i < container.getComponentCount(); ++i) {
                final Component component = container.getComponent(i);
                if (component.isVisible()) {
                    final Dimension preferredSize2 = component.getPreferredSize();
                    component.move(insets.left + n6, n5);
                    component.resize(preferredSize2.width, preferredSize2.height);
                    n6 += preferredSize2.width;
                }
            }
        }
    }
    
    class ImportDialog extends Dialog implements ActionListener
    {
        RippleFrame rframe;
        Button importButton;
        Button clearButton;
        Button closeButton;
        TextArea text;
        
        ImportDialog(final RippleFrame rframe, final String s) {
            super(rframe, (s.length() > 0) ? "Export" : "Import", false);
            this.rframe = rframe;
            this.setLayout(new ImportDialogLayout());
            this.add(this.text = new TextArea(s, 10, 60, 0));
            this.add(this.importButton = new Button("Import"));
            this.importButton.addActionListener(this);
            this.add(this.clearButton = new Button("Clear"));
            this.clearButton.addActionListener(this);
            this.add(this.closeButton = new Button("Close"));
            this.closeButton.addActionListener(this);
            final Point locationOnScreen = this.rframe.getLocationOnScreen();
            this.resize(400, 300);
            final Dimension size = this.getSize();
            this.setLocation(locationOnScreen.x + (RippleFrame.this.winSize.width - size.width) / 2, locationOnScreen.y + (RippleFrame.this.winSize.height - size.height) / 2);
            this.show();
            if (s.length() > 0) {
                this.text.selectAll();
            }
        }
        
        public void actionPerformed(final ActionEvent actionEvent) {
            final Object source = actionEvent.getSource();
            if (source == this.importButton) {
                this.rframe.readImport(this.text.getText());
                this.setVisible(false);
            }
            if (source == this.closeButton) {
                this.setVisible(false);
            }
            if (source == this.clearButton) {
                this.text.setText("");
            }
        }
        
        public boolean handleEvent(final Event event) {
            if (event.id == 201) {
                this.rframe.requestFocus();
                this.setVisible(false);
                return true;
            }
            return super.handleEvent(event);
        }
    }
    
    abstract class Setup
    {
        abstract String getName();
        
        abstract void select();
        
        void doSetupSources() {
            RippleFrame.this.setSources();
        }
        
        void deselect() {
        }
        
        double sourceStrength() {
            return 1.0;
        }
        
        abstract Setup createNext();
        
        void eachFrame() {
        }
        
        float calcSourcePhase(final double n, final float n2, final double n3) {
            return n2;
        }
    }
    
    class SingleSourceSetup extends Setup
    {
        String getName() {
            return "Single Source";
        }
        
        void select() {
            RippleFrame.this.setFreqBar(15);
            RippleFrame.this.setBrightness(27);
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
            RippleFrame.this.setFreqBar(15);
            RippleFrame.this.setBrightness(19);
        }
        
        void doSetupSources() {
            RippleFrame.this.sourceChooser.select(3);
            RippleFrame.this.setSources();
            RippleFrame.this.sources[0].y = RippleFrame.this.gridSizeY / 2 - 8;
            RippleFrame.this.sources[1].y = RippleFrame.this.gridSizeY / 2 + 8;
            final OscSource oscSource = RippleFrame.this.sources[0];
            final OscSource oscSource2 = RippleFrame.this.sources[1];
            final int n = RippleFrame.this.gridSizeX / 2;
            oscSource2.x = n;
            oscSource.x = n;
        }
        
        Setup createNext() {
            return new QuadrupleSourceSetup();
        }
    }
    
    class QuadrupleSourceSetup extends Setup
    {
        String getName() {
            return "Four Sources";
        }
        
        void select() {
            RippleFrame.this.sourceChooser.select(6);
            RippleFrame.this.setFreqBar(15);
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
            RippleFrame.this.sourceChooser.select(10);
            final int n = RippleFrame.this.gridSizeX / 2;
            final int n2 = RippleFrame.this.windowOffsetY + 8;
            for (int i = 0; i != RippleFrame.this.gridSizeX; ++i) {
                RippleFrame.this.setWall(i, n2);
            }
            for (int j = -8; j <= 8; ++j) {
                RippleFrame.this.setWall(n + j, n2, false);
            }
            RippleFrame.this.setBrightness(7);
            RippleFrame.this.setFreqBar(25);
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
            RippleFrame.this.sourceChooser.select(10);
            final int n = RippleFrame.this.gridSizeX / 2;
            final int n2 = RippleFrame.this.windowOffsetY + 4;
            for (int i = 0; i != RippleFrame.this.gridSizeX; ++i) {
                RippleFrame.this.setWall(i, n2);
            }
            for (int j = 0; j != 3; ++j) {
                RippleFrame.this.setWall(n - 5 - j, n2, false);
                RippleFrame.this.setWall(n + 5 + j, n2, false);
            }
            RippleFrame.this.brightnessBar.setValue(488);
            RippleFrame.this.setFreqBar(22);
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
            RippleFrame.this.sourceChooser.select(10);
            final int n = RippleFrame.this.gridSizeX / 2;
            final int n2 = RippleFrame.this.windowOffsetY + 4;
            for (int i = 0; i != RippleFrame.this.gridSizeX; ++i) {
                RippleFrame.this.setWall(i, n2);
            }
            for (int j = -1; j <= 1; ++j) {
                RippleFrame.this.setWall(n - 12 + j, n2, false);
                RippleFrame.this.setWall(n + j, n2, false);
                RippleFrame.this.setWall(n + 12 + j, n2, false);
            }
            RippleFrame.this.setBrightness(12);
            RippleFrame.this.setFreqBar(22);
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
            final int n = RippleFrame.this.gridSizeX / 2;
            final int n2 = RippleFrame.this.windowOffsetY + 12;
            for (int i = -15; i <= 15; ++i) {
                RippleFrame.this.setWall(n + i, n2);
            }
            RippleFrame.this.setBrightness(280);
            RippleFrame.this.setFreqBar(20);
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
            RippleFrame.this.sourceChooser.select(10);
            final int n = RippleFrame.this.windowOffsetX + RippleFrame.this.windowWidth / 2;
            for (int i = RippleFrame.this.windowWidth / 2; i < RippleFrame.this.windowWidth; ++i) {
                RippleFrame.this.setWall(RippleFrame.this.windowOffsetX + i, RippleFrame.this.windowOffsetY + 3);
            }
            RippleFrame.this.setBrightness(4);
            RippleFrame.this.setFreqBar(25);
        }
        
        Setup createNext() {
            return new DipoleSourceSetup();
        }
    }
    
    class DipoleSourceSetup extends Setup
    {
        String getName() {
            return "Dipole Source";
        }
        
        void doSetupSources() {
            RippleFrame.this.sourceChooser.select(3);
            RippleFrame.this.setSources();
            final OscSource oscSource = RippleFrame.this.sources[0];
            final OscSource oscSource2 = RippleFrame.this.sources[1];
            final int n = RippleFrame.this.gridSizeY / 2;
            oscSource2.y = n;
            oscSource.y = n;
            RippleFrame.this.sources[0].x = RippleFrame.this.gridSizeX / 2 - 1;
            RippleFrame.this.sources[1].x = RippleFrame.this.gridSizeX / 2 + 1;
            RippleFrame.this.auxBar.setValue(29);
            RippleFrame.this.setFreqBar(13);
        }
        
        void select() {
            RippleFrame.this.setBrightness(33);
        }
        
        Setup createNext() {
            return new LateralQuadrupoleSetup();
        }
    }
    
    class LateralQuadrupoleSetup extends Setup
    {
        String getName() {
            return "Lateral Quadrupole";
        }
        
        void doSetupSources() {
            RippleFrame.this.sourceChooser.select(6);
            RippleFrame.this.setSources();
            final OscSource oscSource = RippleFrame.this.sources[0];
            final OscSource oscSource2 = RippleFrame.this.sources[2];
            final int n = RippleFrame.this.gridSizeY / 2;
            oscSource2.y = n;
            oscSource.y = n;
            RippleFrame.this.sources[0].x = RippleFrame.this.gridSizeX / 2 - 2;
            RippleFrame.this.sources[2].x = RippleFrame.this.gridSizeX / 2 + 2;
            final OscSource oscSource3 = RippleFrame.this.sources[1];
            final OscSource oscSource4 = RippleFrame.this.sources[3];
            final int n2 = RippleFrame.this.gridSizeX / 2;
            oscSource4.x = n2;
            oscSource3.x = n2;
            RippleFrame.this.sources[1].y = RippleFrame.this.gridSizeY / 2 - 2;
            RippleFrame.this.sources[3].y = RippleFrame.this.gridSizeY / 2 + 2;
            RippleFrame.this.setFreqBar(13);
            RippleFrame.this.auxBar.setValue(29);
        }
        
        void select() {
            RippleFrame.this.setBrightness(33);
        }
        
        Setup createNext() {
            return new LinearQuadrupoleSetup();
        }
    }
    
    class LinearQuadrupoleSetup extends Setup
    {
        String getName() {
            return "Linear Quadrupole";
        }
        
        void doSetupSources() {
            RippleFrame.this.sourceChooser.select(6);
            RippleFrame.this.setSources();
            final OscSource oscSource = RippleFrame.this.sources[0];
            final OscSource oscSource2 = RippleFrame.this.sources[1];
            final OscSource oscSource3 = RippleFrame.this.sources[2];
            final OscSource oscSource4 = RippleFrame.this.sources[3];
            final int n = RippleFrame.this.gridSizeY / 2;
            oscSource4.y = n;
            oscSource3.y = n;
            oscSource2.y = n;
            oscSource.y = n;
            RippleFrame.this.sources[0].x = RippleFrame.this.gridSizeX / 2 - 3;
            RippleFrame.this.sources[2].x = RippleFrame.this.gridSizeX / 2 + 3;
            RippleFrame.this.sources[1].x = RippleFrame.this.gridSizeX / 2 + 1;
            RippleFrame.this.sources[3].x = RippleFrame.this.gridSizeX / 2 - 1;
            RippleFrame.this.auxBar.setValue(29);
            RippleFrame.this.setFreqBar(13);
        }
        
        void select() {
            RippleFrame.this.setBrightness(33);
        }
        
        Setup createNext() {
            return new HexapoleSetup();
        }
    }
    
    class HexapoleSetup extends Setup
    {
        String getName() {
            return "Hexapole";
        }
        
        void doSetupSources() {
            RippleFrame.this.sourceChooser.select(16);
            RippleFrame.this.setSources();
            this.doMultipole(6, 4.0);
            RippleFrame.this.setFreqBar(22);
            RippleFrame.this.auxBar.setValue(29);
        }
        
        void doMultipole(final int n, final double n2) {
            for (int i = 0; i != n; ++i) {
                final double n3 = Math.round(n2 * Math.cos(6.283185307179586 * i / n));
                final double n4 = Math.round(n2 * Math.sin(6.283185307179586 * i / n));
                RippleFrame.this.sources[i].x = RippleFrame.this.gridSizeX / 2 + (int)n3;
                RippleFrame.this.sources[i].y = RippleFrame.this.gridSizeY / 2 + (int)n4;
            }
        }
        
        void select() {
            RippleFrame.this.brightnessBar.setValue(648);
        }
        
        Setup createNext() {
            return new OctupoleSetup();
        }
    }
    
    class OctupoleSetup extends HexapoleSetup
    {
        String getName() {
            return "Octupole";
        }
        
        void doSetupSources() {
            RippleFrame.this.sourceChooser.select(17);
            RippleFrame.this.setSources();
            this.doMultipole(8, 4.0);
            RippleFrame.this.setFreqBar(22);
            RippleFrame.this.auxBar.setValue(29);
        }
        
        Setup createNext() {
            return new Multi12Setup();
        }
    }
    
    class Multi12Setup extends HexapoleSetup
    {
        String getName() {
            return "12-Pole";
        }
        
        void doSetupSources() {
            RippleFrame.this.sourceChooser.select(19);
            RippleFrame.this.setSources();
            this.doMultipole(12, 6.0);
            RippleFrame.this.setFreqBar(22);
            RippleFrame.this.auxBar.setValue(29);
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
            RippleFrame.this.sourceChooser.select(10);
            RippleFrame.this.setFreqBar(15);
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
            RippleFrame.this.setBrightness(4);
            RippleFrame.this.setFreqBar(17);
        }
        
        void doSetupSources() {
            RippleFrame.this.sourceChooser.select(12);
            RippleFrame.this.setSources();
            final OscSource oscSource = RippleFrame.this.sources[0];
            final OscSource oscSource2 = RippleFrame.this.sources[1];
            final int windowOffsetY = RippleFrame.this.windowOffsetY;
            oscSource2.y = windowOffsetY;
            oscSource.y = windowOffsetY;
            RippleFrame.this.sources[0].x = RippleFrame.this.windowOffsetX + 1;
            final OscSource oscSource3 = RippleFrame.this.sources[2];
            final OscSource oscSource4 = RippleFrame.this.sources[3];
            final int windowOffsetX = RippleFrame.this.windowOffsetX;
            oscSource4.x = windowOffsetX;
            oscSource3.x = windowOffsetX;
            RippleFrame.this.sources[2].y = RippleFrame.this.windowOffsetY + 1;
            RippleFrame.this.sources[3].y = RippleFrame.this.windowOffsetY + RippleFrame.this.windowHeight - 1;
        }
        
        Setup createNext() {
            return new PhasedArray1Setup();
        }
    }
    
    class PhasedArray1Setup extends Setup
    {
        String getName() {
            return "Phased Array 1";
        }
        
        void select() {
            RippleFrame.this.setBrightness(5);
            RippleFrame.this.setFreqBar(17);
        }
        
        void doSetupSources() {
            RippleFrame.this.sourceChooser.select(15);
            RippleFrame.this.setSources();
            final OscSource oscSource = RippleFrame.this.sources[0];
            final OscSource oscSource2 = RippleFrame.this.sources[1];
            final int n = RippleFrame.this.gridSizeX / 2;
            oscSource2.x = n;
            oscSource.x = n;
            RippleFrame.this.sources[0].y = RippleFrame.this.gridSizeY / 2 - 12;
            RippleFrame.this.sources[1].y = RippleFrame.this.gridSizeY / 2 + 12;
            RippleFrame.this.auxBar.setValue(5);
        }
        
        float calcSourcePhase(double n, final float n2, final double n3) {
            n *= (RippleFrame.this.auxBar.getValue() - 15) * 3.8 * RippleFrame.this.freqBar.getValue() * 0.0233333;
            return (float)Math.sin(n3 + n);
        }
        
        Setup createNext() {
            return new PhasedArray2Setup();
        }
    }
    
    class PhasedArray2Setup extends PhasedArray1Setup
    {
        String getName() {
            return "Phased Array 2";
        }
        
        void doSetupSources() {
            RippleFrame.this.sourceChooser.select(15);
            RippleFrame.this.setSources();
            final OscSource oscSource = RippleFrame.this.sources[0];
            final OscSource oscSource2 = RippleFrame.this.sources[1];
            final int n = RippleFrame.this.windowOffsetX + 1;
            oscSource2.x = n;
            oscSource.x = n;
            RippleFrame.this.sources[0].y = RippleFrame.this.windowOffsetY + 1;
            RippleFrame.this.sources[1].y = RippleFrame.this.windowOffsetY + RippleFrame.this.windowHeight - 2;
            RippleFrame.this.auxBar.setValue(5);
        }
        
        float calcSourcePhase(double sqrt, final float n, final double n2) {
            final double n3 = RippleFrame.this.auxBar.getValue() * 2.5 / 30.0;
            sqrt -= 0.5;
            sqrt = Math.sqrt(sqrt * sqrt + n3 * n3);
            sqrt *= RippleFrame.this.freqBar.getValue() * 0.0233333 * 108.0;
            return (float)Math.sin(n2 + sqrt);
        }
        
        Setup createNext() {
            return new PhasedArray3Setup();
        }
    }
    
    class PhasedArray3Setup extends PhasedArray2Setup
    {
        String getName() {
            return "Phased Array 3";
        }
        
        float calcSourcePhase(double sqrt, final float n, final double n2) {
            final double n3 = RippleFrame.this.auxBar.getValue() * 2.5 / 30.0;
            sqrt -= 0.5;
            sqrt = Math.sqrt(sqrt * sqrt + n3 * n3);
            sqrt *= RippleFrame.this.freqBar.getValue() * 0.0233333 * 108.0;
            return (float)Math.sin(n2 - sqrt);
        }
        
        Setup createNext() {
            return new DopplerSetup();
        }
    }
    
    class DopplerSetup extends Setup
    {
        String getName() {
            return "Doppler Effect 1";
        }
        
        void select() {
            RippleFrame.this.sourceChooser.select(9);
            RippleFrame.this.setFreqBar(13);
            RippleFrame.this.setBrightness(20);
            RippleFrame.this.fixedEndsCheck.setState(false);
        }
        
        Setup createNext() {
            return new Doppler2Setup();
        }
    }
    
    class Doppler2Setup extends Setup
    {
        double wall;
        int dir;
        int waiting;
        
        String getName() {
            return "Doppler Effect 2";
        }
        
        void select() {
            this.wall = RippleFrame.this.gridSizeY / 2.0;
            this.dir = 1;
            this.waiting = 0;
            RippleFrame.this.setFreqBar(13);
            RippleFrame.this.setBrightness(220);
        }
        
        void doSetupSources() {
            RippleFrame.this.sourceChooser.select(1);
            RippleFrame.this.setSources();
            RippleFrame.this.sources[0].x = RippleFrame.this.windowOffsetX + 1;
            RippleFrame.this.sources[0].y = RippleFrame.this.windowOffsetY + 1;
        }
        
        void eachFrame() {
            if (this.waiting > 0) {
                --this.waiting;
                return;
            }
            final int n = (int)this.wall;
            this.wall += this.dir * 0.04;
            final int n2 = (int)this.wall;
            if (n != n2) {
                for (int i = RippleFrame.this.windowOffsetX + RippleFrame.this.windowWidth / 3; i <= RippleFrame.this.gridSizeX - 1; ++i) {
                    RippleFrame.this.setWall(i, n, false);
                    RippleFrame.this.setWall(i, n2);
                    final int n3 = i + n * RippleFrame.this.gw;
                    if (n2 < n) {
                        RippleFrame.this.func[n3] = (RippleFrame.this.funci[n3] = 0.0f);
                    }
                    else if (n > 1) {
                        RippleFrame.this.func[n3] = RippleFrame.this.func[n3 - RippleFrame.this.gw] / 2.0f;
                        RippleFrame.this.funci[n3] = RippleFrame.this.funci[n3 - RippleFrame.this.gw] / 2.0f;
                    }
                }
                int n4;
                int j;
                for (n4 = (n2 - RippleFrame.this.windowOffsetY) / 2 + RippleFrame.this.windowOffsetY, j = RippleFrame.this.windowOffsetY; j < n4; ++j) {
                    RippleFrame.this.setWall(RippleFrame.this.gridSizeX / 2, j);
                }
                RippleFrame.this.setWall(RippleFrame.this.gridSizeX / 2, j, false);
                RippleFrame.this.calcExceptions();
            }
            if (n2 == RippleFrame.this.windowOffsetY + RippleFrame.this.windowHeight / 4 || n2 == RippleFrame.this.windowOffsetY + RippleFrame.this.windowHeight * 3 / 4) {
                this.dir = -this.dir;
                this.waiting = 1000;
            }
        }
        
        Setup createNext() {
            return new SonicBoomSetup();
        }
    }
    
    class SonicBoomSetup extends Setup
    {
        String getName() {
            return "Sonic Boom";
        }
        
        void select() {
            RippleFrame.this.sourceChooser.select(9);
            RippleFrame.this.setFreqBar(13);
            RippleFrame.this.setBrightness(20);
            RippleFrame.this.fixedEndsCheck.setState(false);
        }
        
        void doSetupSources() {
            RippleFrame.this.setSources();
            RippleFrame.this.auxBar.setValue(30);
        }
        
        Setup createNext() {
            return new BigModeSetup();
        }
    }
    
    class BigModeSetup extends Setup
    {
        String getName() {
            return "Big 1x1 Mode";
        }
        
        void select() {
            RippleFrame.this.sourceChooser.select(0);
            final int n = RippleFrame.this.windowWidth * 3 / 4;
            final int n2 = RippleFrame.this.windowOffsetX + RippleFrame.this.windowWidth / 2 - n / 2;
            final int n3 = RippleFrame.this.windowOffsetY + RippleFrame.this.windowHeight / 2 - n / 2;
            for (int i = 0; i != n + 2; ++i) {
                RippleFrame.this.setWall(n2 + i - 1, n3 - 1);
                RippleFrame.this.setWall(n2 + i - 1, n3 + n);
                RippleFrame.this.setWall(n2 - 1, n3 + i - 1);
                RippleFrame.this.setWall(n2 + n, n3 + i - 1);
            }
            RippleFrame.this.setupMode(n2, n3, n, n, 1, 1);
            RippleFrame.this.dampingBar.setValue(1);
        }
        
        Setup createNext() {
            return new OneByOneModesSetup();
        }
    }
    
    class OneByOneModesSetup extends Setup
    {
        String getName() {
            return "1x1 Modes";
        }
        
        void select() {
            RippleFrame.this.sourceChooser.select(0);
            for (int n = 1, n2 = 5; n + n2 < RippleFrame.this.windowHeight; n += n2 + 1) {
                final int n3 = (n + n2) * (RippleFrame.this.windowWidth - 8) / RippleFrame.this.windowHeight + 6;
                final int n4 = n + RippleFrame.this.windowOffsetY;
                final int n5 = RippleFrame.this.windowOffsetX + 1;
                for (int i = 0; i != n3 + 2; ++i) {
                    RippleFrame.this.setWall(n5 + i - 1, n4 - 1);
                    RippleFrame.this.setWall(n5 + i - 1, n4 + n2);
                }
                for (int j = 0; j != n2 + 2; ++j) {
                    RippleFrame.this.setWall(n5 - 1, n4 + j - 1);
                    RippleFrame.this.setWall(n5 + n3, n4 + j - 1);
                }
                RippleFrame.this.setupMode(n5, n4, n3, n2, 1, 1);
            }
            RippleFrame.this.dampingBar.setValue(1);
        }
        
        Setup createNext() {
            return new OneByNModesSetup();
        }
    }
    
    class OneByNModesSetup extends Setup
    {
        String getName() {
            return "1xN Modes";
        }
        
        void select() {
            RippleFrame.this.sourceChooser.select(0);
            int n = 1;
            final int n2 = 5;
            final int n3 = RippleFrame.this.windowWidth - 2;
            for (int n4 = 1; n + n2 < RippleFrame.this.windowHeight; n += n2 + 1, ++n4) {
                final int n5 = n + RippleFrame.this.windowOffsetY;
                final int n6 = RippleFrame.this.windowOffsetX + 1;
                for (int i = 0; i != n3 + 2; ++i) {
                    RippleFrame.this.setWall(n6 + i - 1, n5 - 1);
                    RippleFrame.this.setWall(n6 + i - 1, n5 + n2);
                }
                for (int j = 0; j != n2 + 2; ++j) {
                    RippleFrame.this.setWall(n6 - 1, n5 + j - 1);
                    RippleFrame.this.setWall(n6 + n3, n5 + j - 1);
                }
                RippleFrame.this.setupMode(n6, n5, n3, n2, n4, 1);
            }
            RippleFrame.this.dampingBar.setValue(1);
        }
        
        Setup createNext() {
            return new NByNModesSetup();
        }
    }
    
    class NByNModesSetup extends Setup
    {
        String getName() {
            return "NxN Modes";
        }
        
        void select() {
            RippleFrame.this.sourceChooser.select(0);
            int n = 3;
            if (RippleFrame.this.resBar.getValue() >= 70) {
                ++n;
            }
            if (RippleFrame.this.resBar.getValue() >= 100) {
                ++n;
            }
            final int n2 = RippleFrame.this.windowHeight / n - 2;
            final int n3 = RippleFrame.this.windowWidth / n - 2;
            for (int i = 1; i <= n; ++i) {
                for (int j = 1; j <= n; ++j) {
                    final int n4 = RippleFrame.this.windowOffsetX + 1 + (n2 + 1) * (j - 1);
                    final int n5 = RippleFrame.this.windowOffsetY + 1 + (n3 + 1) * (i - 1);
                    for (int k = 0; k != n3 + 2; ++k) {
                        RippleFrame.this.setWall(n4 + k - 1, n5 - 1);
                        RippleFrame.this.setWall(n4 + k - 1, n5 + n2);
                    }
                    for (int l = 0; l != n2 + 2; ++l) {
                        RippleFrame.this.setWall(n4 - 1, n5 + l - 1);
                        RippleFrame.this.setWall(n4 + n3, n5 + l - 1);
                    }
                    RippleFrame.this.setupMode(n4, n5, n3, n2, i, j);
                }
            }
            RippleFrame.this.dampingBar.setValue(1);
        }
        
        Setup createNext() {
            return new OneByNModeCombosSetup();
        }
    }
    
    class OneByNModeCombosSetup extends Setup
    {
        String getName() {
            return "1xN Mode Combos";
        }
        
        void select() {
            RippleFrame.this.sourceChooser.select(0);
            int n = 1;
            final int n2 = 5;
            final int n3 = RippleFrame.this.windowWidth - 2;
            while (n + n2 < RippleFrame.this.windowHeight) {
                final int i = RippleFrame.this.getrand(12) + 1;
                int n4;
                do {
                    n4 = RippleFrame.this.getrand(12) + 1;
                } while (i == n4);
                final int n5 = n + RippleFrame.this.windowOffsetY;
                final int n6 = RippleFrame.this.windowOffsetX + 1;
                for (int j = 0; j != n3 + 2; ++j) {
                    RippleFrame.this.setWall(n6 + j - 1, n5 - 1);
                    RippleFrame.this.setWall(n6 + j - 1, n5 + n2);
                }
                for (int k = 0; k != n2 + 2; ++k) {
                    RippleFrame.this.setWall(n6 - 1, n5 + k - 1);
                    RippleFrame.this.setWall(n6 + n3, n5 + k - 1);
                }
                for (int l = 0; l != n3; ++l) {
                    for (int n7 = 0; n7 != n2; ++n7) {
                        final int n8 = l + n6 + RippleFrame.this.gw * (n7 + n5);
                        RippleFrame.this.func[n8] = (float)(Math.sin(i * 3.141592653589793 * (l + 1) / (n3 + 1)) * Math.sin(3.141592653589793 * (n7 + 1) / (n2 + 1)) * 0.5 + Math.sin(n4 * 3.141592653589793 * (l + 1) / (n3 + 1)) * Math.sin(3.141592653589793 * (n7 + 1) / (n2 + 1)) * 0.5);
                        RippleFrame.this.funci[n8] = 0.0f;
                    }
                }
                n += n2 + 1;
            }
            RippleFrame.this.dampingBar.setValue(1);
        }
        
        Setup createNext() {
            return new NByNModeCombosSetup();
        }
    }
    
    class NByNModeCombosSetup extends Setup
    {
        String getName() {
            return "NxN Mode Combos";
        }
        
        void select() {
            RippleFrame.this.sourceChooser.select(0);
            int n = 3;
            if (RippleFrame.this.resBar.getValue() >= 70) {
                ++n;
            }
            if (RippleFrame.this.resBar.getValue() >= 100) {
                ++n;
            }
            final int n2 = RippleFrame.this.windowHeight / n - 2;
            final int n3 = RippleFrame.this.windowWidth / n - 2;
            for (int i = 1; i <= n; ++i) {
                for (int j = 1; j <= n; ++j) {
                    final int n4 = RippleFrame.this.getrand(4) + 1;
                    final int n5 = RippleFrame.this.getrand(4) + 1;
                    int n6;
                    int n7;
                    do {
                        n6 = RippleFrame.this.getrand(4) + 1;
                        n7 = RippleFrame.this.getrand(4) + 1;
                    } while (n4 == n6 && n5 == n7);
                    final int n8 = RippleFrame.this.windowOffsetX + 1 + (n2 + 1) * (i - 1);
                    final int n9 = RippleFrame.this.windowOffsetY + 1 + (n3 + 1) * (j - 1);
                    for (int k = 0; k != n3 + 2; ++k) {
                        RippleFrame.this.setWall(n8 + k - 1, n9 - 1);
                        RippleFrame.this.setWall(n8 + k - 1, n9 + n2);
                    }
                    for (int l = 0; l != n2 + 2; ++l) {
                        RippleFrame.this.setWall(n8 - 1, n9 + l - 1);
                        RippleFrame.this.setWall(n8 + n3, n9 + l - 1);
                    }
                    for (int n10 = 0; n10 != n3; ++n10) {
                        for (int n11 = 0; n11 != n2; ++n11) {
                            final int n12 = n10 + n8 + RippleFrame.this.gw * (n11 + n9);
                            RippleFrame.this.func[n12] = (float)(Math.sin(n4 * 3.141592653589793 * (n10 + 1) / (n3 + 1)) * Math.sin(n5 * 3.141592653589793 * (n11 + 1) / (n2 + 1)) * 0.5 + Math.sin(n6 * 3.141592653589793 * (n10 + 1) / (n3 + 1)) * Math.sin(n7 * 3.141592653589793 * (n11 + 1) / (n2 + 1)) * 0.5);
                            RippleFrame.this.funci[n12] = 0.0f;
                        }
                    }
                }
            }
            RippleFrame.this.dampingBar.setValue(1);
        }
        
        Setup createNext() {
            return new ZeroByOneModesSetup();
        }
    }
    
    class ZeroByOneModesSetup extends Setup
    {
        String getName() {
            return "0x1 Acoustic Modes";
        }
        
        void select() {
            RippleFrame.this.fixedEndsCheck.setState(false);
            RippleFrame.this.sourceChooser.select(0);
            for (int n = 1, n2 = 5; n + n2 < RippleFrame.this.windowHeight; n += n2 + 1) {
                final int n3 = (n + n2) * (RippleFrame.this.windowWidth - 8) / RippleFrame.this.windowHeight + 6;
                final int n4 = n + RippleFrame.this.windowOffsetY;
                final int n5 = RippleFrame.this.windowOffsetX + 1;
                for (int i = 0; i != n3 + 2; ++i) {
                    RippleFrame.this.setWall(n5 + i - 1, n4 - 1);
                    RippleFrame.this.setWall(n5 + i - 1, n4 + n2);
                }
                for (int j = 0; j != n2 + 2; ++j) {
                    RippleFrame.this.setWall(n5 - 1, n4 + j - 1);
                    RippleFrame.this.setWall(n5 + n3, n4 + j - 1);
                }
                RippleFrame.this.setupAcousticMode(n5, n4, n3, n2, 1, 0);
            }
            RippleFrame.this.dampingBar.setValue(1);
        }
        
        Setup createNext() {
            return new ZeroByNModesSetup();
        }
    }
    
    class ZeroByNModesSetup extends Setup
    {
        String getName() {
            return "0xN Acoustic Modes";
        }
        
        void select() {
            RippleFrame.this.fixedEndsCheck.setState(false);
            RippleFrame.this.sourceChooser.select(0);
            int n = 1;
            final int n2 = 5;
            final int n3 = RippleFrame.this.windowWidth - 2;
            for (int n4 = 1; n + n2 < RippleFrame.this.windowHeight; n += n2 + 1, ++n4) {
                final int n5 = n + RippleFrame.this.windowOffsetY;
                final int n6 = RippleFrame.this.windowOffsetX + 1;
                for (int i = 0; i != n3 + 2; ++i) {
                    RippleFrame.this.setWall(n6 + i - 1, n5 - 1);
                    RippleFrame.this.setWall(n6 + i - 1, n5 + n2);
                }
                for (int j = 0; j != n2 + 2; ++j) {
                    RippleFrame.this.setWall(n6 - 1, n5 + j - 1);
                    RippleFrame.this.setWall(n6 + n3, n5 + j - 1);
                }
                RippleFrame.this.setupAcousticMode(n6, n5, n3, n2, n4, 0);
            }
            RippleFrame.this.dampingBar.setValue(1);
        }
        
        Setup createNext() {
            return new NByNAcoModesSetup();
        }
    }
    
    class NByNAcoModesSetup extends Setup
    {
        String getName() {
            return "NxN Acoustic Modes";
        }
        
        void select() {
            RippleFrame.this.fixedEndsCheck.setState(false);
            RippleFrame.this.sourceChooser.select(0);
            int n = 2;
            if (RippleFrame.this.resBar.getValue() >= 70) {
                ++n;
            }
            final int n2 = RippleFrame.this.windowHeight / (n + 1) - 2;
            final int n3 = RippleFrame.this.windowWidth / (n + 1) - 2;
            for (int i = 0; i <= n; ++i) {
                for (int j = 0; j <= n; ++j) {
                    final int n4 = RippleFrame.this.windowOffsetX + 1 + (n2 + 1) * j;
                    final int n5 = RippleFrame.this.windowOffsetY + 1 + (n3 + 1) * i;
                    for (int k = 0; k != n3 + 2; ++k) {
                        RippleFrame.this.setWall(n4 + k - 1, n5 - 1);
                        RippleFrame.this.setWall(n4 + k - 1, n5 + n2);
                    }
                    for (int l = 0; l != n2 + 2; ++l) {
                        RippleFrame.this.setWall(n4 - 1, n5 + l - 1);
                        RippleFrame.this.setWall(n4 + n3, n5 + l - 1);
                    }
                    RippleFrame.this.setupAcousticMode(n4, n5, n3, n2, i, j);
                }
            }
            RippleFrame.this.dampingBar.setValue(1);
        }
        
        Setup createNext() {
            return new CoupledCavitiesSetup();
        }
    }
    
    class CoupledCavitiesSetup extends Setup
    {
        String getName() {
            return "Coupled Cavities";
        }
        
        void select() {
            RippleFrame.this.fixedEndsCheck.setState(false);
            RippleFrame.this.sourceChooser.select(0);
            for (int n = 1, n2 = 5; n + n2 < RippleFrame.this.windowHeight; n += n2 + 3) {
                final int n3 = 35;
                final int n4 = n + RippleFrame.this.windowOffsetY;
                final int n5 = RippleFrame.this.windowOffsetX + 1;
                for (int i = 0; i != n3 + 2; ++i) {
                    RippleFrame.this.setWall(n5 + i - 1, n4 - 1);
                    RippleFrame.this.setWall(n5 + i - 1, n4 + n2);
                }
                for (int j = 0; j != n2 + 2; ++j) {
                    RippleFrame.this.setWall(n5 - 1, n4 + j - 1);
                    RippleFrame.this.setWall(n5 + n3, n4 + j - 1);
                }
                for (int k = 0; k != 2; ++k) {
                    RippleFrame.this.setWall(n5 + n3 / 2, n4 + k);
                    RippleFrame.this.setWall(n5 + n3 / 2, n4 + 4 - k);
                }
                RippleFrame.this.setupAcousticMode(n5, n4, n3 / 2, n2, 1, 0);
            }
            RippleFrame.this.dampingBar.setValue(1);
        }
        
        Setup createNext() {
            return new BeatsSetup();
        }
    }
    
    class BeatsSetup extends Setup
    {
        String getName() {
            return "Beats";
        }
        
        void doSetupSources() {
            RippleFrame.this.sourceChooser.select(4);
            RippleFrame.this.setSources();
            RippleFrame.this.auxBar.setValue(24);
            final OscSource oscSource = RippleFrame.this.sources[0];
            final OscSource oscSource2 = RippleFrame.this.sources[1];
            final int n = RippleFrame.this.gridSizeY / 2;
            oscSource2.y = n;
            oscSource.y = n;
            RippleFrame.this.sources[0].x = RippleFrame.this.gridSizeX / 2 - 2;
            RippleFrame.this.sources[1].x = RippleFrame.this.gridSizeX / 2 + 2;
        }
        
        void select() {
            RippleFrame.this.setBrightness(25);
            RippleFrame.this.setFreqBar(18);
        }
        
        Setup createNext() {
            return new SlowMediumSetup();
        }
    }
    
    class SlowMediumSetup extends Setup
    {
        String getName() {
            return "Slow Medium";
        }
        
        void select() {
            RippleFrame.this.addMedium();
            RippleFrame.this.setFreqBar(10);
            RippleFrame.this.setBrightness(33);
        }
        
        Setup createNext() {
            return new RefractionSetup();
        }
    }
    
    class RefractionSetup extends Setup
    {
        String getName() {
            return "Refraction";
        }
        
        void doSetupSources() {
            RippleFrame.this.sourceChooser.select(14);
            RippleFrame.this.setSources();
            RippleFrame.this.sources[0].x = RippleFrame.this.windowOffsetX;
            RippleFrame.this.sources[0].y = RippleFrame.this.windowOffsetY + RippleFrame.this.windowHeight / 4;
            RippleFrame.this.sources[1].x = RippleFrame.this.windowOffsetX + RippleFrame.this.windowWidth / 3;
            RippleFrame.this.sources[1].y = RippleFrame.this.windowOffsetY;
            RippleFrame.this.addMedium();
            RippleFrame.this.setFreqBar(1);
            RippleFrame.this.setBrightness(33);
        }
        
        void select() {
        }
        
        Setup createNext() {
            return new InternalReflectionSetup();
        }
    }
    
    class InternalReflectionSetup extends Setup
    {
        String getName() {
            return "Internal Reflection";
        }
        
        void doSetupSources() {
            RippleFrame.this.sourceChooser.select(14);
            RippleFrame.this.setSources();
            RippleFrame.this.sources[0].x = RippleFrame.this.windowOffsetX;
            RippleFrame.this.sources[0].y = RippleFrame.this.windowOffsetY + RippleFrame.this.windowHeight * 2 / 3;
            RippleFrame.this.sources[1].x = RippleFrame.this.windowOffsetX + RippleFrame.this.windowWidth / 3;
            RippleFrame.this.sources[1].y = RippleFrame.this.windowOffsetY + RippleFrame.this.windowHeight - 1;
            RippleFrame.this.addMedium();
            RippleFrame.this.setFreqBar(1);
            RippleFrame.this.setBrightness(33);
        }
        
        void select() {
        }
        
        Setup createNext() {
            return new CoatingSetup();
        }
    }
    
    class CoatingSetup extends Setup
    {
        String getName() {
            return "Anti-Reflective Coating";
        }
        
        void select() {
            RippleFrame.this.sourceChooser.select(1);
            RippleFrame.this.addMedium();
            final double sqrt = Math.sqrt(0.5);
            Math.sqrt(sqrt);
            final int n = (int)((1.0 - sqrt) * 191.0 / 0.5);
            for (int i = 0; i != RippleFrame.this.gridSizeX; ++i) {
                for (int j = RippleFrame.this.gridSizeY / 2 - 4; j != RippleFrame.this.gridSizeY / 2; ++j) {
                    RippleFrame.this.medium[i + j * RippleFrame.this.gw] = n;
                }
            }
            RippleFrame.this.setFreqBar(6);
            RippleFrame.this.setBrightness(28);
        }
        
        Setup createNext() {
            return new ZonePlateEvenSetup();
        }
    }
    
    class ZonePlateEvenSetup extends Setup
    {
        int zoneq;
        
        ZonePlateEvenSetup() {
            this.zoneq = 1;
        }
        
        String getName() {
            return "Zone Plate (Even)";
        }
        
        void doSetupSources() {
        }
        
        void select() {
            RippleFrame.this.sourceChooser.select(10);
            RippleFrame.this.setSources();
            if (RippleFrame.this.resBar.getValue() < 42) {
                RippleFrame.this.setResolution(42);
            }
            final int freqBar = 30;
            RippleFrame.this.setFreqBar(freqBar);
            final double n = 25.0 / (freqBar * 2 / 5);
            final int n2 = RippleFrame.this.sources[0].y + 1;
            final int n3 = RippleFrame.this.windowOffsetY + RippleFrame.this.windowHeight / 2 - n2;
            final int n4 = RippleFrame.this.gridSizeX / 2;
            for (int i = 0; i != RippleFrame.this.windowWidth; ++i) {
                final int n5 = RippleFrame.this.windowOffsetX + i;
                final int n6 = n4 - n5;
                RippleFrame.this.setWall(n5, n2, ((int)((Math.sqrt(n6 * n6 + n3 * n3) - n3) / n) & 0x1) == this.zoneq);
                RippleFrame.this.setWall(RippleFrame.this.windowOffsetX, n2);
                RippleFrame.this.setWall(RippleFrame.this.windowOffsetX + RippleFrame.this.windowWidth - 1, n2);
            }
            RippleFrame.this.setBrightness((this.zoneq == 1) ? 4 : 7);
        }
        
        Setup createNext() {
            return new ZonePlateOddSetup();
        }
    }
    
    class ZonePlateOddSetup extends ZonePlateEvenSetup
    {
        ZonePlateOddSetup() {
            this.zoneq = 0;
        }
        
        String getName() {
            return "Zone Plate (Odd)";
        }
        
        Setup createNext() {
            return new CircleSetup();
        }
    }
    
    class CircleSetup extends Setup
    {
        boolean circle;
        
        CircleSetup() {
            this.circle = true;
        }
        
        String getName() {
            return "Circle";
        }
        
        void doSetupSources() {
        }
        
        void select() {
            final int n = RippleFrame.this.windowWidth / 2 - 2;
            final double n2 = n * n;
            double n3 = n2 / 2.0;
            if (this.circle) {
                n3 = n2;
            }
            final int n4 = RippleFrame.this.windowWidth / 2 + RippleFrame.this.windowOffsetX;
            final int y = RippleFrame.this.windowHeight / 2 + RippleFrame.this.windowOffsetY;
            int i = -1;
            for (int j = 0; j <= n; ++j) {
                int n5 = (int)(Math.sqrt((1.0 - j * j / n2) * n3) + 1.5);
                if (j == n) {
                    n5 = 0;
                }
                if (i == -1) {
                    i = n5;
                }
                while (i >= n5) {
                    RippleFrame.this.setWall(n4 + j, y + i);
                    RippleFrame.this.setWall(n4 - j, y + i);
                    RippleFrame.this.setWall(n4 + j, y - i);
                    RippleFrame.this.setWall(n4 - j, y - i);
                    --i;
                }
                i = n5;
            }
            final int n6 = (int)Math.sqrt(n2 - n3);
            RippleFrame.this.sourceChooser.select(8);
            RippleFrame.this.setSources();
            RippleFrame.this.sources[0].x = n4 - n6;
            RippleFrame.this.sources[0].y = y;
            RippleFrame.this.setFreqBar(1);
            RippleFrame.this.setBrightness(16);
        }
        
        Setup createNext() {
            return new EllipseSetup();
        }
    }
    
    class EllipseSetup extends CircleSetup
    {
        EllipseSetup() {
            this.circle = false;
        }
        
        String getName() {
            return "Ellipse";
        }
        
        Setup createNext() {
            return new ResonantCavitiesSetup();
        }
    }
    
    class ResonantCavitiesSetup extends Setup
    {
        String getName() {
            return "Resonant Cavities 1";
        }
        
        void select() {
            RippleFrame.this.sourceChooser.select(10);
            int i = 1;
            final int n = 5;
            final int n2 = RippleFrame.this.windowOffsetY + 11;
            while (i + n < RippleFrame.this.windowWidth) {
                final int n3 = (i + n) * (RippleFrame.this.windowHeight - 18) / RippleFrame.this.windowWidth + 6;
                final int n4 = i + RippleFrame.this.windowOffsetX;
                for (int j = 0; j != n3 + 2; ++j) {
                    RippleFrame.this.setWall(n4 - 1, n2 + j - 1);
                    RippleFrame.this.setWall(n4 + n, n2 + j - 1);
                }
                for (int k = 0; k != n + 2; ++k) {
                    RippleFrame.this.setWall(n4 + k - 1, n2 - 1);
                    RippleFrame.this.setWall(n4 + k - 1, n2 + n3);
                }
                RippleFrame.this.setWall(n4 + n / 2, n2 - 1, false);
                i += n + 1;
            }
            while (i < RippleFrame.this.windowWidth) {
                RippleFrame.this.setWall(i + RippleFrame.this.windowOffsetX, n2 - 1);
                ++i;
            }
            RippleFrame.this.setBrightness(30);
            RippleFrame.this.setFreqBar(15);
        }
        
        double sourceStrength() {
            return 0.1;
        }
        
        Setup createNext() {
            return new ResonantCavities2Setup();
        }
    }
    
    class ResonantCavities2Setup extends Setup
    {
        String getName() {
            return "Resonant Cavities 2";
        }
        
        void select() {
            RippleFrame.this.sourceChooser.select(10);
            int i = 1;
            final int n = 5;
            final int n2 = RippleFrame.this.windowOffsetY + 11;
            for (int n3 = 5; i + n < RippleFrame.this.windowWidth; i += n + 1, ++n3) {
                final int n4 = i + RippleFrame.this.windowOffsetX;
                for (int j = 0; j != n3 + 2; ++j) {
                    RippleFrame.this.setWall(n4 - 1, n2 + j - 1);
                    RippleFrame.this.setWall(n4 + n, n2 + j - 1);
                }
                for (int k = 0; k != n + 2; ++k) {
                    RippleFrame.this.setWall(n4 + k - 1, n2 + n3);
                }
            }
            while (i < RippleFrame.this.windowWidth) {
                RippleFrame.this.setWall(i + RippleFrame.this.windowOffsetX, n2 - 1);
                ++i;
            }
            RippleFrame.this.setBrightness(30);
            RippleFrame.this.setFreqBar(16);
        }
        
        double sourceStrength() {
            return 0.03;
        }
        
        Setup createNext() {
            return new RoomResonanceSetup();
        }
    }
    
    class RoomResonanceSetup extends Setup
    {
        String getName() {
            return "Room Resonance";
        }
        
        void select() {
            RippleFrame.this.sourceChooser.select(6);
            RippleFrame.this.setSources();
            final int n = 17;
            final int n2 = 17;
            for (int i = 1; i <= 2; ++i) {
                for (int j = 1; j <= 2; ++j) {
                    final int n3 = RippleFrame.this.windowOffsetX + 1 + (n + 1) * (j - 1);
                    final int n4 = RippleFrame.this.windowOffsetY + 1 + (n2 + 1) * (i - 1);
                    for (int k = 0; k != n2 + 2; ++k) {
                        RippleFrame.this.setWall(n3 + k - 1, n4 - 1);
                        RippleFrame.this.setWall(n3 + k - 1, n4 + n);
                    }
                    for (int l = 0; l != n + 2; ++l) {
                        RippleFrame.this.setWall(n3 - 1, n4 + l - 1);
                        RippleFrame.this.setWall(n3 + n2, n4 + l - 1);
                    }
                }
            }
            final OscSource oscSource = RippleFrame.this.sources[0];
            final OscSource oscSource2 = RippleFrame.this.sources[2];
            final int n5 = RippleFrame.this.windowOffsetX + 2;
            oscSource2.x = n5;
            oscSource.x = n5;
            final OscSource oscSource3 = RippleFrame.this.sources[0];
            final OscSource oscSource4 = RippleFrame.this.sources[1];
            final int n6 = RippleFrame.this.windowOffsetY + 2;
            oscSource4.y = n6;
            oscSource3.y = n6;
            final OscSource oscSource5 = RippleFrame.this.sources[1];
            final OscSource oscSource6 = RippleFrame.this.sources[3];
            final int n7 = RippleFrame.this.windowOffsetX + 1 + n2 + (n2 + 1) / 2;
            oscSource6.x = n7;
            oscSource5.x = n7;
            final OscSource oscSource7 = RippleFrame.this.sources[2];
            final OscSource oscSource8 = RippleFrame.this.sources[3];
            final int n8 = RippleFrame.this.windowOffsetY + 1 + n + (n + 1) / 2;
            oscSource8.y = n8;
            oscSource7.y = n8;
            RippleFrame.this.fixedEndsCheck.setState(false);
            RippleFrame.this.dampingBar.setValue(10);
            RippleFrame.this.setBrightness(3);
        }
        
        void doSetupSources() {
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
            RippleFrame.this.sourceChooser.select(10);
            int i = 1;
            int n = 3;
            final int n2 = RippleFrame.this.windowOffsetY + 3;
            final int n3 = RippleFrame.this.windowHeight - 2;
            while (i + n < RippleFrame.this.windowWidth) {
                final int n4 = i + RippleFrame.this.windowOffsetX;
                for (int j = 0; j != n3; ++j) {
                    RippleFrame.this.setWall(n4 - 1, n2 + j - 1);
                    RippleFrame.this.setWall(n4 + n, n2 + j - 1);
                }
                ++n;
                i += n;
            }
            while (i < RippleFrame.this.windowWidth) {
                RippleFrame.this.setWall(i + RippleFrame.this.windowOffsetX, n2 - 1);
                ++i;
            }
            RippleFrame.this.setBrightness(6);
            RippleFrame.this.setFreqBar(14);
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
            RippleFrame.this.setFreqBar(8);
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
            RippleFrame.this.sourceChooser.select(10);
            final int n = 8;
            final int n2 = RippleFrame.this.windowOffsetY + 3;
            final int n3 = RippleFrame.this.windowHeight - 2;
            for (int i = 1; i < RippleFrame.this.windowWidth; ++i) {
                RippleFrame.this.setWall(i + RippleFrame.this.windowOffsetX, n2 - 1);
            }
            for (int n4 = 1, n5 = 0; n4 + n < RippleFrame.this.windowWidth && n5 < n; n4 += n + 1) {
                final int n6 = n4 + RippleFrame.this.windowOffsetX;
                for (int j = 0; j != n3; ++j) {
                    RippleFrame.this.setWall(n6 - 1, n2 + j - 1);
                    RippleFrame.this.setWall(n6 + n, n2 + j - 1);
                }
                RippleFrame.this.setWall(n6 + n5++, n2 - 1, false);
            }
            RippleFrame.this.setBrightness(89);
            RippleFrame.this.setFreqBar(16);
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
            super.select();
            RippleFrame.this.setBrightness(29);
            RippleFrame.this.setFreqBar(20);
            RippleFrame.this.fixedEndsCheck.setState(false);
        }
        
        Setup createNext() {
            return new Waveguides5Setup();
        }
    }
    
    class Waveguides5Setup extends Waveguides3Setup
    {
        String getName() {
            return "Waveguides 5";
        }
        
        void select() {
            RippleFrame.this.sourceChooser.select(10);
            final int n = 8;
            final int n2 = RippleFrame.this.windowOffsetY + 2;
            final int n3 = RippleFrame.this.windowHeight - 1;
            for (int n4 = 1; n4 + n < RippleFrame.this.windowWidth; n4 += n + 1) {
                final int n5 = n4 + RippleFrame.this.windowOffsetX;
                for (int i = 0; i != n3; ++i) {
                    RippleFrame.this.setWall(n5 - 1, n2 + i - 1);
                    RippleFrame.this.setWall(n5 + n, n2 + i - 1);
                }
            }
            RippleFrame.this.setBrightness(9);
            RippleFrame.this.setFreqBar(22);
        }
        
        void eachFrame() {
            final int n = RippleFrame.this.windowOffsetY + 1;
            for (int n2 = 8, n3 = 1, n4 = 1; n3 + n2 < RippleFrame.this.windowWidth; n3 += n2 + 1, ++n4) {
                final int n5 = n3 + RippleFrame.this.windowOffsetX;
                int n7 = 0;
                int n6 = 0;
                switch (n4) {
                    case 1: {
                        n6 = (n7 = 1);
                        break;
                    }
                    case 2: {
                        n6 = (n7 = 2);
                        break;
                    }
                    case 3: {
                        n7 = 1;
                        n6 = 2;
                        break;
                    }
                    case 4: {
                        n6 = (n7 = 3);
                        break;
                    }
                    case 5: {
                        n7 = 1;
                        n6 = 3;
                        break;
                    }
                    case 6: {
                        n7 = 2;
                        n6 = 3;
                        break;
                    }
                    default: {
                        n6 = (n7 = 0);
                        break;
                    }
                }
                for (int i = 0; i != n2; ++i) {
                    final float[] func = RippleFrame.this.func;
                    final int n8 = n5 + i + RippleFrame.this.gw * n;
                    func[n8] *= (float)(0.5 * (Math.sin(3.141592653589793 * n7 * (i + 1) / (n2 + 1)) + Math.sin(3.141592653589793 * n6 * (i + 1) / (n2 + 1))));
                }
            }
        }
        
        Setup createNext() {
            return new ParabolicMirror1Setup();
        }
    }
    
    class ParabolicMirror1Setup extends Setup
    {
        String getName() {
            return "Parabolic Mirror 1";
        }
        
        void select() {
            if (RippleFrame.this.resBar.getValue() < 50) {
                RippleFrame.this.setResolution(50);
            }
            final int x = RippleFrame.this.windowWidth / 2 + RippleFrame.this.windowOffsetX;
            int i = 0;
            final int n = RippleFrame.this.windowHeight / 2;
            final int n2 = RippleFrame.this.windowHeight + RippleFrame.this.windowOffsetY - 2;
            final int n3 = RippleFrame.this.windowWidth / 2 - 2;
            double n4 = n3 * n3 * 0.5 / n;
            if (n4 > 20.0) {
                n4 = 20.0;
            }
            for (int j = 0; j <= n; ++j) {
                int n5;
                for (n5 = (int)(Math.sqrt(2.0 * n4 * j) + 1.5); i <= n5; ++i) {
                    RippleFrame.this.setWall(x - i, n2 - j);
                    RippleFrame.this.setWall(x + i, n2 - j);
                }
                i = n5;
            }
            RippleFrame.this.setSources();
            RippleFrame.this.sources[0].x = x;
            RippleFrame.this.sources[0].y = (int)(n2 - 1 - n4 / 2.0);
            RippleFrame.this.setBrightness(18);
        }
        
        void doSetupSources() {
        }
        
        Setup createNext() {
            return new ParabolicMirror2Setup();
        }
    }
    
    class ParabolicMirror2Setup extends ParabolicMirror1Setup
    {
        String getName() {
            return "Parabolic Mirror 2";
        }
        
        void doSetupSources() {
            RippleFrame.this.sourceChooser.select(10);
            RippleFrame.this.brightnessBar.setValue(370);
            RippleFrame.this.setFreqBar(15);
            RippleFrame.this.setSources();
        }
        
        Setup createNext() {
            return new SoundDuctSetup();
        }
    }
    
    class SoundDuctSetup extends Setup
    {
        String getName() {
            return "Sound Duct";
        }
        
        void select() {
            RippleFrame.this.sourceChooser.select(8);
            RippleFrame.this.fixedEndsCheck.setState(false);
            final int n = RippleFrame.this.windowOffsetX + RippleFrame.this.windowWidth / 2;
            for (int i = 0; i != RippleFrame.this.windowHeight - 12; ++i) {
                RippleFrame.this.setWall(n - 3, i + RippleFrame.this.windowOffsetY + 6);
                RippleFrame.this.setWall(n + 3, i + RippleFrame.this.windowOffsetY + 6);
            }
            RippleFrame.this.setFreqBar(1);
            RippleFrame.this.setBrightness(60);
        }
        
        Setup createNext() {
            return new BaffledPistonSetup();
        }
    }
    
    class BaffledPistonSetup extends Setup
    {
        String getName() {
            return "Baffled Piston";
        }
        
        void select() {
            RippleFrame.this.sourceChooser.select(10);
            RippleFrame.this.fixedEndsCheck.setState(false);
            for (int i = 0; i != RippleFrame.this.gridSizeY; ++i) {
                RippleFrame.this.setWall(RippleFrame.this.windowOffsetX + 2, i);
            }
            for (int j = 0; j <= 11; ++j) {
                RippleFrame.this.setWall(RippleFrame.this.windowOffsetX, j + RippleFrame.this.gridSizeY / 2 - 5);
                if (j != 0 && j != 11) {
                    RippleFrame.this.setWall(RippleFrame.this.windowOffsetX + 2, j + RippleFrame.this.gridSizeY / 2 - 5, false);
                }
            }
            RippleFrame.this.setWall(RippleFrame.this.windowOffsetX + 1, RippleFrame.this.gridSizeY / 2 - 5);
            RippleFrame.this.setWall(RippleFrame.this.windowOffsetX + 1, RippleFrame.this.gridSizeY / 2 + 6);
            RippleFrame.this.setFreqBar(24);
            RippleFrame.this.setSources();
            final OscSource oscSource = RippleFrame.this.sources[0];
            final OscSource oscSource2 = RippleFrame.this.sources[1];
            final int n = RippleFrame.this.windowOffsetX + 1;
            oscSource2.x = n;
            oscSource.x = n;
            RippleFrame.this.sources[0].y = RippleFrame.this.gridSizeY / 2 - 4;
            RippleFrame.this.sources[1].y = RippleFrame.this.gridSizeY / 2 + 5;
            RippleFrame.this.setBrightness(18);
        }
        
        void doSetupSources() {
        }
        
        Setup createNext() {
            return new LowPassFilter1Setup();
        }
    }
    
    class LowPassFilter1Setup extends Setup
    {
        String getName() {
            return "Low-Pass Filter 1";
        }
        
        void select() {
            if (RippleFrame.this.resBar.getValue() < 43) {
                RippleFrame.this.setResolution(43);
            }
            RippleFrame.this.fixedEndsCheck.setState(false);
            for (int i = 0; i != RippleFrame.this.windowWidth; ++i) {
                RippleFrame.this.setWall(i + RippleFrame.this.windowOffsetX, RippleFrame.this.windowOffsetY + 9);
            }
            final int n = RippleFrame.this.gridSizeX / 2;
            for (int j = 1; j <= 4; ++j) {
                for (int k = -7; k <= 7; ++k) {
                    RippleFrame.this.setWall(n + k, RippleFrame.this.windowOffsetY + 9 * j);
                }
            }
            for (int l = 0; l <= 4; ++l) {
                for (int n2 = -4; n2 <= 4; ++n2) {
                    RippleFrame.this.setWall(n + n2, RippleFrame.this.windowOffsetY + 9 * l, false);
                }
            }
            for (int n3 = 0; n3 != 27; ++n3) {
                RippleFrame.this.setWall(n + 7, RippleFrame.this.windowOffsetY + 9 + n3);
                RippleFrame.this.setWall(n - 7, RippleFrame.this.windowOffsetY + 9 + n3);
            }
            RippleFrame.this.setBrightness(38);
        }
        
        Setup createNext() {
            return new LowPassFilter2Setup();
        }
    }
    
    class LowPassFilter2Setup extends LowPassFilter1Setup
    {
        String getName() {
            return "Low-Pass Filter 2";
        }
        
        void select() {
            super.select();
            RippleFrame.this.setFreqBar(17);
        }
        
        Setup createNext() {
            return new HighPassFilter1Setup();
        }
    }
    
    class HighPassFilter1Setup extends Setup
    {
        String getName() {
            return "High-Pass Filter 1";
        }
        
        void select() {
            if (RippleFrame.this.resBar.getValue() < 43) {
                RippleFrame.this.setResolution(43);
            }
            RippleFrame.this.fixedEndsCheck.setState(false);
            for (int i = 0; i != RippleFrame.this.windowWidth; ++i) {
                for (int j = 0; j <= 25; j += 5) {
                    RippleFrame.this.setWall(i + RippleFrame.this.windowOffsetX, RippleFrame.this.windowOffsetY + 9 + j);
                }
            }
            final int n = RippleFrame.this.gridSizeX / 2;
            for (int k = 0; k <= 25; k += 5) {
                for (int l = -4; l <= 4; ++l) {
                    RippleFrame.this.setWall(n + l, RippleFrame.this.windowOffsetY + 9 + k, false);
                }
            }
            RippleFrame.this.setBrightness(62);
            RippleFrame.this.setFreqBar(17);
        }
        
        Setup createNext() {
            return new HighPassFilter2Setup();
        }
    }
    
    class HighPassFilter2Setup extends HighPassFilter1Setup
    {
        String getName() {
            return "High-Pass Filter 2";
        }
        
        void select() {
            super.select();
            RippleFrame.this.setFreqBar(7);
        }
        
        Setup createNext() {
            return new BandStopFilter1Setup();
        }
    }
    
    class BandStopFilter1Setup extends Setup
    {
        String getName() {
            return "Band-Stop Filter 1";
        }
        
        void select() {
            if (RippleFrame.this.resBar.getValue() < 43) {
                RippleFrame.this.setResolution(43);
            }
            RippleFrame.this.fixedEndsCheck.setState(false);
            for (int i = 0; i != RippleFrame.this.windowWidth; ++i) {
                RippleFrame.this.setWall(i + RippleFrame.this.windowOffsetX, RippleFrame.this.windowOffsetY + 9);
            }
            final int n = RippleFrame.this.gridSizeX / 2;
            for (int j = 1; j <= 2; ++j) {
                for (int k = -11; k <= 11; ++k) {
                    if (k <= -5 || k >= 5) {
                        RippleFrame.this.setWall(n + k, RippleFrame.this.windowOffsetY + 9 + 9 * j);
                    }
                }
            }
            for (int l = 0; l <= 1; ++l) {
                for (int n2 = -4; n2 <= 4; ++n2) {
                    RippleFrame.this.setWall(n + n2, RippleFrame.this.windowOffsetY + 9 + l * 26, false);
                }
            }
            for (int n3 = 0; n3 <= 18; ++n3) {
                RippleFrame.this.setWall(n + 11, RippleFrame.this.windowOffsetY + 9 + n3);
                RippleFrame.this.setWall(n - 11, RippleFrame.this.windowOffsetY + 9 + n3);
            }
            for (int n4 = 0; n4 != 3; ++n4) {
                for (int n5 = 0; n5 != 3; ++n5) {
                    for (int n6 = 9; n6 <= 18; n6 += 9) {
                        RippleFrame.this.setWall(n + 5 + n4, RippleFrame.this.windowOffsetY + n6 + n5);
                        RippleFrame.this.setWall(n + 5 + n4, RippleFrame.this.windowOffsetY + 9 + n6 - n5);
                        RippleFrame.this.setWall(n - 5 - n4, RippleFrame.this.windowOffsetY + n6 + n5);
                        RippleFrame.this.setWall(n - 5 - n4, RippleFrame.this.windowOffsetY + 9 + n6 - n5);
                    }
                }
            }
            RippleFrame.this.setBrightness(38);
            RippleFrame.this.setFreqBar(2);
        }
        
        Setup createNext() {
            return new BandStopFilter2Setup();
        }
    }
    
    class BandStopFilter2Setup extends BandStopFilter1Setup
    {
        String getName() {
            return "Band-Stop Filter 2";
        }
        
        void select() {
            super.select();
            RippleFrame.this.setFreqBar(10);
        }
        
        Setup createNext() {
            return new BandStopFilter3Setup();
        }
    }
    
    class BandStopFilter3Setup extends BandStopFilter1Setup
    {
        String getName() {
            return "Band-Stop Filter 3";
        }
        
        void select() {
            super.select();
            RippleFrame.this.setFreqBar(4);
        }
        
        Setup createNext() {
            return new PlanarConvexLensSetup();
        }
    }
    
    class PlanarConvexLensSetup extends Setup
    {
        String getName() {
            return "Planar Convex Lens";
        }
        
        void select() {
            if (RippleFrame.this.resBar.getValue() < 42) {
                RippleFrame.this.setResolution(42);
            }
            RippleFrame.this.sourceChooser.select(10);
            final int n = RippleFrame.this.gridSizeX / 2;
            final int n2 = RippleFrame.this.windowHeight / 8 + RippleFrame.this.windowOffsetY;
            int n3 = RippleFrame.this.windowWidth / 3 - 2;
            final int n4 = 5;
            final double n5 = 0.75 * RippleFrame.this.windowHeight * 0.5;
            final double n6 = n5 - n4;
            final double n7 = n5 * n5;
            if (n3 > n5) {
                n3 = (int)n5;
            }
            for (int i = 0; i <= n3; ++i) {
                for (int j = 2 + (int)(Math.sqrt(n7 - i * i) - n6 + 0.5); j >= 0; --j) {
                    RippleFrame.this.setMedium(n + i, n2 + j, 95);
                    RippleFrame.this.setMedium(n - i, n2 + j, 95);
                }
            }
            RippleFrame.this.setFreqBar(19);
            RippleFrame.this.setBrightness(6);
        }
        
        Setup createNext() {
            return new BiconvexLensSetup();
        }
    }
    
    class BiconvexLensSetup extends Setup
    {
        String getName() {
            return "Biconvex Lens";
        }
        
        void select() {
            if (RippleFrame.this.resBar.getValue() < 50) {
                RippleFrame.this.setResolution(50);
            }
            RippleFrame.this.setSources();
            final int n = RippleFrame.this.gridSizeX / 2;
            final int n2 = RippleFrame.this.gridSizeY / 2;
            int n3 = RippleFrame.this.windowWidth / 3 - 2;
            final int n4 = 10;
            final double n5 = 0.375 * RippleFrame.this.windowHeight * 0.5;
            final double n6 = n5 - n4;
            final double n7 = n5 * n5;
            if (n3 > n5) {
                n3 = (int)n5;
            }
            for (int i = 0; i <= n3; ++i) {
                for (int j = 1 + (int)(Math.sqrt(n7 - i * i) - n6 + 0.5); j >= 0; --j) {
                    RippleFrame.this.setMedium(n + i, n2 + j, 95);
                    RippleFrame.this.setMedium(n - i, n2 + j, 95);
                    RippleFrame.this.setMedium(n + i, n2 - j, 95);
                    RippleFrame.this.setMedium(n - i, n2 - j, 95);
                }
            }
            RippleFrame.this.setFreqBar(19);
            RippleFrame.this.setBrightness(66);
            RippleFrame.this.sources[0].y = n2 - (2 + 2 * (int)n5);
        }
        
        void doSetupSources() {
        }
        
        Setup createNext() {
            return new PlanarConcaveSetup();
        }
    }
    
    class PlanarConcaveSetup extends Setup
    {
        String getName() {
            return "Planar Concave Lens";
        }
        
        void select() {
            RippleFrame.this.sourceChooser.select(10);
            final int n = RippleFrame.this.gridSizeX / 2;
            final int n2 = RippleFrame.this.windowHeight / 8 + RippleFrame.this.windowOffsetY;
            int n3 = RippleFrame.this.windowWidth / 5;
            final int n4 = 5;
            final double n5 = 0.25 * RippleFrame.this.windowHeight * 0.5;
            final double n6 = n5 - n4;
            final double n7 = n5 * n5;
            if (n3 > n5) {
                n3 = (int)n5;
            }
            for (int i = 0; i <= n3; ++i) {
                for (int j = n4 + 2 - (int)(Math.sqrt(n7 - i * i) - n6 + 0.5); j >= 0; --j) {
                    RippleFrame.this.setMedium(n + i, n2 + j, 95);
                    RippleFrame.this.setMedium(n - i, n2 + j, 95);
                }
            }
            for (int k = 0; k != RippleFrame.this.windowWidth; ++k) {
                if (RippleFrame.this.medium[RippleFrame.this.windowOffsetX + k + RippleFrame.this.gw * n2] == 0) {
                    RippleFrame.this.setWall(RippleFrame.this.windowOffsetX + k, n2);
                }
            }
            RippleFrame.this.setFreqBar(19);
        }
        
        Setup createNext() {
            return new CircularPrismSetup();
        }
    }
    
    class CircularPrismSetup extends Setup
    {
        String getName() {
            return "Circular Prism";
        }
        
        void select() {
            RippleFrame.this.sourceChooser.select(10);
            final int n = RippleFrame.this.gridSizeX / 2;
            final int n2 = RippleFrame.this.gridSizeY / 2;
            final int n4;
            final int n3 = n4 = RippleFrame.this.windowWidth / 3 - 2;
            final double n5 = (n3 * n3 + n4 * n4) / (2.0 * n4);
            final double n6 = n5 - n4;
            final double n7 = n5 * n5;
            for (int i = 0; i < n3; ++i) {
                for (int j = (int)(Math.sqrt(n7 - i * i) - n6 + 0.5); j >= 0; --j) {
                    RippleFrame.this.setMedium(n + i, n2 + j, 191);
                    RippleFrame.this.setMedium(n - i, n2 + j, 191);
                    RippleFrame.this.setMedium(n + i, n2 - j, 191);
                    RippleFrame.this.setMedium(n - i, n2 - j, 191);
                }
            }
            for (int k = 0; k != RippleFrame.this.windowWidth; ++k) {
                if (RippleFrame.this.medium[RippleFrame.this.windowOffsetX + k + RippleFrame.this.gw * n2] == 0) {
                    RippleFrame.this.setWall(RippleFrame.this.windowOffsetX + k, n2);
                }
            }
            RippleFrame.this.setFreqBar(9);
        }
        
        Setup createNext() {
            return new RightAnglePrismSetup();
        }
    }
    
    class RightAnglePrismSetup extends Setup
    {
        String getName() {
            return "Right-Angle Prism";
        }
        
        void select() {
            if (RippleFrame.this.resBar.getValue() < 42) {
                RippleFrame.this.setResolution(42);
            }
            RippleFrame.this.sourceChooser.select(10);
            final int n = RippleFrame.this.gridSizeX / 2;
            final int n2 = RippleFrame.this.gridSizeY / 2;
            int n4;
            for (int n3 = n4 = RippleFrame.this.windowWidth / 4, i = -n3; i < n3; ++i) {
                for (int j = -n4; j <= i; ++j) {
                    RippleFrame.this.setMedium(n + i, n2 + j, 191);
                }
            }
            for (int k = 0; k != RippleFrame.this.windowWidth; ++k) {
                if (RippleFrame.this.medium[RippleFrame.this.windowOffsetX + k + RippleFrame.this.gw * (n2 - n4)] == 0) {
                    RippleFrame.this.setWall(RippleFrame.this.windowOffsetX + k, n2 - n4);
                }
            }
            RippleFrame.this.setFreqBar(11);
        }
        
        Setup createNext() {
            return new PorroPrismSetup();
        }
    }
    
    class PorroPrismSetup extends Setup
    {
        String getName() {
            return "Porro Prism";
        }
        
        void select() {
            if (RippleFrame.this.resBar.getValue() < 42) {
                RippleFrame.this.setResolution(42);
            }
            RippleFrame.this.sourceChooser.select(10);
            RippleFrame.this.setSources();
            final int n = RippleFrame.this.gridSizeX / 2;
            RippleFrame.this.sources[1].x = n - 1;
            final int n3;
            final int n2 = n3 = RippleFrame.this.windowWidth / 2;
            final int n4 = RippleFrame.this.gridSizeY / 2 - n3 / 2;
            for (int i = -n2; i < n2; ++i) {
                for (int n5 = n3 + 1 - ((i < 0) ? (-i) : i), j = 0; j <= n5; ++j) {
                    RippleFrame.this.setMedium(n + i, n4 + j, 191);
                }
            }
            for (int k = 0; k != n4; ++k) {
                if (RippleFrame.this.medium[n + RippleFrame.this.gw * (k + RippleFrame.this.windowOffsetY)] == 0) {
                    RippleFrame.this.setWall(n, k + RippleFrame.this.windowOffsetY);
                }
            }
            RippleFrame.this.setFreqBar(11);
        }
        
        void doSetupSources() {
        }
        
        Setup createNext() {
            return new ScatteringSetup();
        }
    }
    
    class ScatteringSetup extends Setup
    {
        String getName() {
            return "Scattering";
        }
        
        void select() {
            RippleFrame.this.sourceChooser.select(14);
            RippleFrame.this.setWall(RippleFrame.this.gridSizeX / 2, RippleFrame.this.gridSizeY / 2);
            RippleFrame.this.setFreqBar(1);
            RippleFrame.this.dampingBar.setValue(40);
            RippleFrame.this.setBrightness(52);
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
            RippleFrame.this.setSources();
            RippleFrame.this.sources[0].x = RippleFrame.this.windowOffsetX;
            RippleFrame.this.sources[0].y = RippleFrame.this.windowOffsetY + RippleFrame.this.windowHeight * 3 / 4;
            RippleFrame.this.setBrightness(75);
            RippleFrame.this.setFreqBar(23);
            for (int i = 0; i != RippleFrame.this.windowWidth; ++i) {
                RippleFrame.this.setWall(i + RippleFrame.this.windowOffsetX, RippleFrame.this.windowOffsetY + RippleFrame.this.windowHeight - 1);
            }
        }
        
        void doSetupSources() {
        }
        
        Setup createNext() {
            return new TempGradient1();
        }
    }
    
    class TempGradient1 extends Setup
    {
        String getName() {
            return "Temperature Gradient 1";
        }
        
        void select() {
            final int n = RippleFrame.this.windowOffsetY + RippleFrame.this.windowHeight / 2;
            final int n2 = RippleFrame.this.windowOffsetY + RippleFrame.this.windowHeight * 3 / 4;
            final int n3 = RippleFrame.this.windowOffsetY + RippleFrame.this.windowHeight * 7 / 8;
            for (int i = 0; i != RippleFrame.this.gridSizeY; ++i) {
                int n4;
                if (i < n) {
                    n4 = 0;
                }
                else if (i > n2) {
                    n4 = 191;
                }
                else {
                    n4 = 191 * (i - n) / (n2 - n);
                }
                for (int j = 0; j != RippleFrame.this.gridSizeX; ++j) {
                    RippleFrame.this.setMedium(j, i, n4);
                }
            }
            for (int k = n3; k < RippleFrame.this.windowOffsetY + RippleFrame.this.windowHeight; ++k) {
                RippleFrame.this.setWall(RippleFrame.this.gridSizeX / 2, k);
            }
            RippleFrame.this.setBrightness(33);
        }
        
        void doSetupSources() {
            RippleFrame.this.setSources();
            RippleFrame.this.sources[0].x = RippleFrame.this.windowOffsetX + 2;
            RippleFrame.this.sources[0].y = RippleFrame.this.windowOffsetY + RippleFrame.this.windowHeight - 2;
        }
        
        Setup createNext() {
            return new TempGradient2();
        }
    }
    
    class TempGradient2 extends Setup
    {
        String getName() {
            return "Temperature Gradient 2";
        }
        
        void select() {
            final int n = RippleFrame.this.windowOffsetY + RippleFrame.this.windowHeight / 2 - RippleFrame.this.windowHeight / 8;
            final int n2 = RippleFrame.this.windowOffsetY + RippleFrame.this.windowHeight / 2 + RippleFrame.this.windowHeight / 8;
            for (int i = 0; i != RippleFrame.this.gridSizeY; ++i) {
                int n3;
                if (i < n) {
                    n3 = 191;
                }
                else if (i > n2) {
                    n3 = 0;
                }
                else {
                    n3 = 191 * (n2 - i) / (n2 - n);
                }
                for (int j = 0; j != RippleFrame.this.gridSizeX; ++j) {
                    RippleFrame.this.setMedium(j, i, n3);
                }
            }
            RippleFrame.this.setBrightness(31);
        }
        
        void doSetupSources() {
            RippleFrame.this.setSources();
            RippleFrame.this.sources[0].x = RippleFrame.this.windowOffsetX + 2;
            RippleFrame.this.sources[0].y = RippleFrame.this.windowOffsetY + RippleFrame.this.windowHeight / 4;
        }
        
        Setup createNext() {
            return new TempGradient3();
        }
    }
    
    class TempGradient3 extends Setup
    {
        String getName() {
            return "Temperature Gradient 3";
        }
        
        void select() {
            final int n = RippleFrame.this.windowOffsetY + RippleFrame.this.windowHeight / 2 - RippleFrame.this.windowHeight / 5;
            final int n2 = RippleFrame.this.windowOffsetY + RippleFrame.this.windowHeight / 2 + RippleFrame.this.windowHeight / 5;
            final int n3 = RippleFrame.this.gridSizeY / 2;
            for (int i = 0; i != RippleFrame.this.gridSizeY; ++i) {
                int n4;
                if (i < n || i > n2) {
                    n4 = 191;
                }
                else if (i > n3) {
                    n4 = 191 * (i - n3) / (n2 - n3);
                }
                else {
                    n4 = 191 * (n3 - i) / (n3 - n);
                }
                for (int j = 0; j != RippleFrame.this.gridSizeX; ++j) {
                    RippleFrame.this.setMedium(j, i, n4);
                }
            }
            RippleFrame.this.setBrightness(31);
        }
        
        void doSetupSources() {
            RippleFrame.this.setSources();
            RippleFrame.this.sources[0].x = RippleFrame.this.windowOffsetX + 2;
            RippleFrame.this.sources[0].y = RippleFrame.this.windowOffsetY + RippleFrame.this.windowHeight / 4;
        }
        
        Setup createNext() {
            return new TempGradient4();
        }
    }
    
    class TempGradient4 extends TempGradient3
    {
        String getName() {
            return "Temperature Gradient 4";
        }
        
        void select() {
            final int n = RippleFrame.this.windowOffsetY + RippleFrame.this.windowHeight / 2 - RippleFrame.this.windowHeight / 5;
            final int n2 = RippleFrame.this.windowOffsetY + RippleFrame.this.windowHeight / 2 + RippleFrame.this.windowHeight / 5;
            final int n3 = RippleFrame.this.gridSizeY / 2;
            for (int i = 0; i != RippleFrame.this.gridSizeY; ++i) {
                int n4;
                if (i < n || i > n2) {
                    n4 = 0;
                }
                else if (i > n3) {
                    n4 = 191 * (n2 - i) / (n2 - n3);
                }
                else {
                    n4 = 191 * (i - n) / (n3 - n);
                }
                for (int j = 0; j != RippleFrame.this.gridSizeX; ++j) {
                    RippleFrame.this.setMedium(j, i, n4);
                }
            }
            RippleFrame.this.setBrightness(31);
        }
        
        Setup createNext() {
            return new DispersionSetup();
        }
    }
    
    class DispersionSetup extends Setup
    {
        String getName() {
            return "Dispersion";
        }
        
        void select() {
            RippleFrame.this.sourceChooser.select(4);
            for (int i = 0; i != RippleFrame.this.gridSizeY; ++i) {
                RippleFrame.this.setWall(RippleFrame.this.gridSizeX / 2, i);
            }
            for (int j = 0; j != RippleFrame.this.gridSizeX; ++j) {
                for (int k = 0; k != RippleFrame.this.gridSizeY; ++k) {
                    RippleFrame.this.setMedium(j, k, 63);
                }
            }
            RippleFrame.this.fixedEndsCheck.setState(false);
            RippleFrame.this.setBrightness(16);
        }
        
        void doSetupSources() {
            RippleFrame.this.setSources();
            RippleFrame.this.sources[0].x = RippleFrame.this.gridSizeX / 2 - 2;
            RippleFrame.this.sources[1].x = RippleFrame.this.gridSizeX / 2 + 2;
            final OscSource oscSource = RippleFrame.this.sources[0];
            final OscSource oscSource2 = RippleFrame.this.sources[1];
            final int n = RippleFrame.this.windowOffsetY + 1;
            oscSource2.y = n;
            oscSource.y = n;
            RippleFrame.this.setFreqBar(7);
            RippleFrame.this.auxBar.setValue(30);
        }
        
        Setup createNext() {
            return null;
        }
    }
}
