import java.awt.Event;
import java.awt.event.ItemEvent;
import java.awt.event.MouseEvent;
import java.awt.event.AdjustmentEvent;
import java.awt.event.ActionEvent;
import java.awt.event.ComponentEvent;
import java.awt.image.ImageObserver;
import java.awt.Graphics;
import java.awt.image.ImageProducer;
import java.awt.Label;
import java.awt.Menu;
import java.awt.MenuBar;
import java.awt.Component;
import java.awt.LayoutManager;
import java.awt.FontMetrics;
import java.awt.image.MemoryImageSource;
import java.awt.Color;
import java.awt.Rectangle;
import java.awt.Scrollbar;
import java.awt.Choice;
import java.awt.MenuItem;
import java.awt.CheckboxMenuItem;
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

class QuantumRotatorFrame extends Frame implements ComponentListener, ActionListener, AdjustmentListener, MouseMotionListener, MouseListener, ItemListener
{
    Thread engine;
    Dimension winSize;
    Image dbimage;
    Image memimage;
    Random random;
    int gridSizeX;
    int gridSizeY;
    Button blankButton;
    Button normalizeButton;
    Button maximizeButton;
    Checkbox stoppedCheck;
    CheckboxMenuItem colorCheck;
    CheckboxMenuItem eCheckItem;
    CheckboxMenuItem xCheckItem;
    CheckboxMenuItem lCheckItem;
    CheckboxMenuItem alwaysNormItem;
    CheckboxMenuItem axesItem;
    MenuItem exitItem;
    Choice modeChooser;
    Scrollbar speedBar;
    Scrollbar resolutionBar;
    Scrollbar internalResBar;
    Scrollbar brightnessBar;
    Scrollbar phasorBar;
    View viewPotential;
    View viewX;
    View viewL;
    View viewStates;
    View[] viewList;
    int viewCount;
    Phasor[] phasors;
    int phasorCount;
    BasisState[] states;
    int stateCount;
    TextBox[] textBoxes;
    int textCount;
    double dragZoomStart;
    double zoom;
    double[] rotmatrix;
    Rectangle viewAxes;
    static final double pi = 3.141592653589793;
    static final double pi2 = 6.283185307179586;
    static final double root2 = 1.4142135623730951;
    static final double root2inv = 0.7071067811865476;
    static final double baseEnergy = 0.0;
    int[] xpoints;
    int[] ypoints;
    int[] floorValues;
    int selectedPaneHandle;
    PhaseColor[][] phaseColors;
    Color[] grayLevels;
    double resadj;
    boolean dragging;
    boolean editingFunc;
    MemoryImageSource imageSource;
    int[] pixels;
    int dataSize;
    int dataSizeTh;
    int dataSizePh;
    static int maxModes;
    static int maxDispCoefs;
    static int viewDistance;
    int pause;
    QuantumRotator applet;
    State selectedState;
    Phasor selectedPhasor;
    int selection;
    static final int SEL_NONE = 0;
    static final int SEL_POTENTIAL = 1;
    static final int SEL_X = 2;
    static final int SEL_STATES = 3;
    static final int SEL_HANDLE = 4;
    static final int MODE_ANGLE = 0;
    static final int MODE_GAUSS = 1;
    static final int MODE_GAUSSP = 2;
    static final int MODE_ZOOM = 3;
    boolean settingScale;
    double magDragStart;
    int dragX;
    int dragY;
    int dragStartX;
    int dragStartY;
    double t;
    public static final double epsilon = 0.01;
    static final int panePad = 4;
    static final int phaseColorCount = 50;
    double[][] func;
    double[][] funci;
    int phiIndex;
    int phiSector;
    double bestBrightness;
    double userBrightMult;
    double colorMult;
    boolean manualScale;
    Color gray2;
    FontMetrics fontMetrics;
    boolean useBufferedImage;
    FFT fft;
    QuantumRotatorCanvas cv;
    long lastTime;
    int scaleValue;
    
    public String getAppletInfo() {
        return "QuantumRotator by Paul Falstad";
    }
    
    int getrand(final int n) {
        int nextInt = this.random.nextInt();
        if (nextInt < 0) {
            nextInt = -nextInt;
        }
        return nextInt % n;
    }
    
    QuantumRotatorFrame(final QuantumRotator applet) {
        super("Rigid Rotator v1.5a");
        this.gridSizeX = 200;
        this.gridSizeY = 200;
        this.zoom = 19.9;
        this.dragging = false;
        this.editingFunc = false;
        this.selection = -1;
        this.userBrightMult = 1.0;
        this.scaleValue = -1;
        this.applet = applet;
    }
    
    public void init() {
        this.gray2 = new Color(127, 127, 127);
        if (new Double(System.getProperty("java.class.version")) >= 48.0) {
            this.useBufferedImage = true;
        }
        this.setLayout(new QuantumRotatorLayout());
        (this.cv = new QuantumRotatorCanvas(this)).addComponentListener(this);
        this.cv.addMouseMotionListener(this);
        this.cv.addMouseListener(this);
        this.add(this.cv);
        final MenuBar menuBar = new MenuBar();
        final Menu menu = new Menu("File");
        menuBar.add(menu);
        menu.add(this.exitItem = this.getMenuItem("Exit"));
        final Menu menu2 = new Menu("View");
        menuBar.add(menu2);
        menu2.add(this.eCheckItem = this.getCheckItem("Energy"));
        this.eCheckItem.setState(true);
        menu2.add(this.xCheckItem = this.getCheckItem("Position"));
        this.xCheckItem.setState(true);
        this.xCheckItem.disable();
        menu2.add(this.lCheckItem = this.getCheckItem("Angular Momentum (Z)"));
        menu2.addSeparator();
        menu2.add(this.colorCheck = this.getCheckItem("Phase as Color"));
        this.colorCheck.setState(true);
        final Menu menu3 = new Menu("Options");
        menuBar.add(menu3);
        this.alwaysNormItem = this.getCheckItem("Always Normalize");
        menu3.add(this.axesItem = this.getCheckItem("Show Axes"));
        this.axesItem.setState(true);
        this.setMenuBar(menuBar);
        (this.modeChooser = new Choice()).add("Mouse = Adjust View");
        this.modeChooser.add("Mouse = Create Gaussian");
        this.modeChooser.add("Mouse = Gaussian w/ Momentum");
        this.modeChooser.addItemListener(this);
        this.add(this.modeChooser);
        (this.stoppedCheck = new Checkbox("Stopped")).addItemListener(this);
        this.add(this.stoppedCheck);
        this.add(this.blankButton = new Button("Clear"));
        this.blankButton.addActionListener(this);
        this.add(this.normalizeButton = new Button("Normalize"));
        this.normalizeButton.addActionListener(this);
        this.add(this.maximizeButton = new Button("Maximize"));
        this.maximizeButton.addActionListener(this);
        this.add(new Label("Simulation Speed", 1));
        this.add(this.speedBar = new Scrollbar(0, 6, 1, 1, 200));
        this.speedBar.addAdjustmentListener(this);
        this.add(new Label("Brightness", 1));
        this.add(this.brightnessBar = new Scrollbar(0, 743, 1, 1, 4000));
        this.brightnessBar.addAdjustmentListener(this);
        this.add(new Label("Image Resolution", 1));
        this.add(this.resolutionBar = new Scrollbar(0, 150, 2, 20, 500));
        this.resolutionBar.addAdjustmentListener(this);
        this.add(new Label("Phasor Count", 1));
        this.add(this.phasorBar = new Scrollbar(0, 8, 1, 3, 30));
        this.phasorBar.addAdjustmentListener(this);
        this.add(new Label("http://www.falstad.com", 1));
        try {
            final String parameter = this.applet.getParameter("PAUSE");
            if (parameter != null) {
                this.pause = Integer.parseInt(parameter);
            }
        }
        catch (Exception ex) {}
        this.phaseColors = new PhaseColor[8][51];
        for (int i = 0; i != 8; ++i) {
            for (int j = 0; j <= 50; ++j) {
                this.phaseColors[i][j] = this.genPhaseColor(i, Math.atan(j / 50.0));
            }
        }
        this.grayLevels = new Color[256];
        for (int k = 0; k != 256; ++k) {
            this.grayLevels[k] = new Color(k, k, k);
        }
        this.rotmatrix = new double[9];
        this.setView();
        this.xpoints = new int[4];
        this.ypoints = new int[4];
        this.random = new Random();
        this.reinit();
        this.cv.setBackground(Color.black);
        this.cv.setForeground(Color.white);
        this.resize(580, 500);
        this.handleResize();
        final Dimension size = this.getSize();
        final Dimension screenSize = this.getToolkit().getScreenSize();
        this.setLocation((screenSize.width - size.width) / 2, (screenSize.height - size.height) / 2);
        this.setupStates();
        this.show();
    }
    
    void setView() {
        for (int i = 0; i != 9; ++i) {
            this.rotmatrix[i] = 0.0;
        }
        final double[] rotmatrix = this.rotmatrix;
        final int n = 0;
        final double[] rotmatrix2 = this.rotmatrix;
        final int n2 = 4;
        final double[] rotmatrix3 = this.rotmatrix;
        final int n3 = 8;
        final double n4 = 1.0;
        rotmatrix3[n3] = n4;
        rotmatrix[n] = (rotmatrix2[n2] = n4);
        this.rotate(0.0, -1.5707963267948966);
    }
    
    void setupStates() {
        this.stateCount = 1024;
        this.states = new BasisState[this.stateCount];
        int l = 0;
        int m = 0;
        for (int i = 0; i != this.stateCount; ++i) {
            final BasisState[] states = this.states;
            final int n = i;
            final BasisState basisState = new BasisState();
            states[n] = basisState;
            final BasisState basisState2 = basisState;
            ((State)basisState2).elevel = l * (l + 1);
            basisState2.l = l;
            basisState2.m = m;
            final int n2 = (m < 0) ? (-m) : m;
            final double pow = Math.pow(-1.0, m);
            final double sphericalNorm = this.sphericalNorm(l, n2);
            final BasisState basisState3 = basisState2;
            final double[] plm = new double[this.dataSizeTh];
            basisState3.plm = plm;
            final double[] array = plm;
            final double n3 = pow * (Math.pow(-1.0, m) * sphericalNorm);
            for (int j = 0; j != this.dataSizeTh; ++j) {
                array[j] = n3 * this.plgndr(l, n2, Math.cos(j * 3.141592653589793 / (this.dataSizeTh - 1)));
            }
            if (m < l) {
                ++m;
            }
            else {
                m = -(++l);
            }
        }
        ((Complex)this.states[13]).set(1.0);
    }
    
    MenuItem getMenuItem(final String s) {
        final MenuItem menuItem = new MenuItem(s);
        menuItem.addActionListener(this);
        return menuItem;
    }
    
    CheckboxMenuItem getCheckItem(final String s) {
        final CheckboxMenuItem checkboxMenuItem = new CheckboxMenuItem(s);
        checkboxMenuItem.addItemListener(this);
        return checkboxMenuItem;
    }
    
    PhaseColor genPhaseColor(final int n, double n2) {
        n2 += n * 3.141592653589793 / 4.0;
        n2 *= 0.954929658551372;
        final int n3 = (int)n2;
        final double n4 = n2 % 1.0;
        final double n5 = 1.0 - n4;
        PhaseColor phaseColor = null;
        switch (n3) {
            case 0:
            case 6: {
                phaseColor = new PhaseColor(1.0, n4, 0.0);
                break;
            }
            case 1: {
                phaseColor = new PhaseColor(n5, 1.0, 0.0);
                break;
            }
            case 2: {
                phaseColor = new PhaseColor(0.0, 1.0, n4);
                break;
            }
            case 3: {
                phaseColor = new PhaseColor(0.0, n5, 1.0);
                break;
            }
            case 4: {
                phaseColor = new PhaseColor(n4, 0.0, 1.0);
                break;
            }
            case 5: {
                phaseColor = new PhaseColor(1.0, 0.0, n5);
                break;
            }
        }
        return phaseColor;
    }
    
    void handleResize() {
        this.reinit();
    }
    
    void reinit() {
        this.setResolution();
        final Dimension size = this.cv.getSize();
        this.winSize = size;
        final Dimension dimension = size;
        if (this.winSize.width == 0) {
            return;
        }
        this.dbimage = this.createImage(dimension.width, dimension.height);
        this.setupDisplay();
    }
    
    void createPhasors() {
        final boolean b = false;
        this.textCount = (b ? 1 : 0);
        this.phasorCount = (b ? 1 : 0);
        if (this.viewStates == null) {
            return;
        }
        int n = this.viewStates.height / this.phasorBar.getValue();
        if (n < 7) {
            n = 7;
        }
        int n2 = this.viewStates.width / 2;
        int y = this.viewStates.y;
        int n3 = 0;
        int n4 = 0;
        this.textBoxes = new TextBox[10];
        this.phasorCount = this.phasorBar.getValue();
        this.phasorCount *= this.phasorCount;
        this.phasors = new Phasor[this.phasorCount];
        for (int i = 0; i != this.phasorCount; ++i) {
            final Phasor[] phasors = this.phasors;
            final int n5 = i;
            final Phasor phasor = new Phasor(n2, y, n, n);
            phasors[n5] = phasor;
            phasor.state = this.states[i];
            n2 += n;
            if (++n4 > n3) {
                n2 -= n * (2 * n3 + 2);
                y += n;
                n4 = -(++n3);
            }
        }
    }
    
    void setInitialOrbital() {
        if (this.phasorCount == 0) {
            return;
        }
        for (int i = 0; i != this.stateCount; ++i) {
            if (((Complex)this.states[i]).mag > 0.0) {
                return;
            }
        }
        for (int j = 0; j != this.phasorCount; ++j) {
            if (this.phasors[j].state instanceof BasisState) {
                ((Complex)this.phasors[j].state).set(1.0);
                return;
            }
        }
    }
    
    int createBasisPhasors(int n, final int n2, final int n3, int n4, final int n5, final int n6) {
        for (int i = 0; i != n6 * 2 + 1; ++i) {
            final Phasor[] phasors = this.phasors;
            final int n7 = n4;
            final Phasor phasor = new Phasor(n, n2, n3, n3);
            phasors[n7] = phasor;
            phasor.state = this.getState(n5, n6, i - n6);
            n += n3;
            ++n4;
        }
        return n4;
    }
    
    void createText(final String s, final int n, final int n2, final int n3) {
        this.textBoxes[this.textCount++] = new TextBox(n + 10, n2, this.winSize.width - n, n3, s);
    }
    
    void setupDisplay() {
        if (this.winSize == null) {
            return;
        }
        final int n = (this.viewPotential == null) ? 50 : this.viewPotential.height;
        final int n2 = (this.viewStates == null) ? 96 : this.viewStates.height;
        final View view = null;
        this.viewStates = view;
        this.viewL = view;
        this.viewPotential = view;
        this.viewX = view;
        this.viewList = new View[10];
        int viewCount = 0;
        if (this.eCheckItem.getState()) {
            this.viewList[viewCount++] = (this.viewPotential = new View());
        }
        if (this.xCheckItem.getState()) {
            this.viewList[viewCount++] = (this.viewX = new View());
        }
        if (this.lCheckItem.getState()) {
            this.viewList[viewCount++] = (this.viewL = new View());
        }
        this.viewList[viewCount++] = (this.viewStates = new View());
        this.viewCount = viewCount;
        int viewCount2 = this.viewCount;
        int height = this.winSize.height;
        if (n > 0 && this.viewPotential != null) {
            --viewCount2;
            height -= n;
        }
        if (n2 > 0 && this.viewStates != null) {
            --viewCount2;
            height -= n2;
        }
        final int n3 = height - 8 * (this.viewCount - 1);
        int n4 = 0;
        for (int i = 0; i != this.viewCount; ++i) {
            final View view2 = this.viewList[i];
            int height2 = (viewCount2 == 0) ? n3 : (n3 / viewCount2);
            if (view2 == this.viewPotential && n > 0) {
                height2 = n;
            }
            else if (view2 == this.viewStates && n2 > 0) {
                height2 = n2;
            }
            if ((view2.paneY = n4) > 0) {
                n4 += 4;
            }
            view2.x = 0;
            view2.width = this.winSize.width;
            view2.y = n4;
            view2.height = height2;
            n4 += height2 + 4;
        }
        this.setSubViews();
    }
    
    void setSubViews() {
        this.pixels = null;
        if (this.useBufferedImage) {
            try {
                final Class<?> forName = Class.forName("java.awt.image.BufferedImage");
                final Class<?> forName2 = Class.forName("java.awt.image.DataBufferInt");
                final Class<?> forName3 = Class.forName("java.awt.image.Raster");
                this.memimage = (Image)forName.getConstructor(Integer.TYPE, Integer.TYPE, Integer.TYPE).newInstance(new Integer(this.viewX.width), new Integer(this.viewX.height), new Integer(1));
                this.pixels = (int[])forName2.getMethod("getData", (Class[])null).invoke(forName3.getMethod("getDataBuffer", (Class<?>[])null).invoke(forName.getMethod("getRaster", (Class[])null).invoke(this.memimage, (Object[])null), (Object[])null), (Object[])null);
            }
            catch (Exception ex) {
                System.out.println("BufferedImage failed");
            }
        }
        if (this.pixels == null) {
            this.pixels = new int[this.viewX.width * this.viewX.height];
            for (int i = 0; i != this.viewX.width * this.viewX.height; ++i) {
                this.pixels[i] = -16777216;
            }
            (this.imageSource = new MemoryImageSource(this.viewX.width, this.viewX.height, this.pixels, 0, this.viewX.width)).setAnimated(true);
            this.imageSource.setFullBufferUpdates(true);
            this.memimage = this.cv.createImage(this.imageSource);
        }
        final int n = (int)(this.min(this.viewX.width, this.viewX.height) / 4.0);
        this.viewAxes = new Rectangle(this.viewX.x + this.winSize.width - n, this.viewX.y, n, n);
        this.floorValues = null;
        this.createPhasors();
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
        if (n > n2) {
            return n;
        }
        return n2;
    }
    
    double min(final double n, final double n2) {
        if (n < n2) {
            return n;
        }
        return n2;
    }
    
    void setResolution() {
        final int gridSizeX = this.gridSizeX;
        final int n = this.resolutionBar.getValue() & 0xFFFFFFFE;
        this.gridSizeY = n;
        this.gridSizeX = n;
        if (gridSizeX == this.gridSizeX) {
            return;
        }
        this.dataSize = this.gridSizeX * 4;
        this.dataSize = 128;
        this.dataSizePh = this.dataSize;
        this.dataSizeTh = this.dataSize + 1;
        this.func = new double[this.dataSizeTh][this.dataSizePh];
        this.funci = new double[this.dataSizeTh][this.dataSizePh];
        System.out.print("setResolution " + this.dataSize + " " + this.gridSizeX + "\n");
        this.fft = new FFT(this.dataSizePh);
        this.resadj = 50.0 / this.dataSize;
    }
    
    void computeView(final double n) {
        final boolean state = this.colorCheck.getState();
        final double n2 = 1.0 / this.zoom;
        final double[] rotmatrix = this.rotmatrix;
        final double n3 = this.viewX.width / this.viewX.height;
        final double n4 = this.dataSize / 2.0;
        final double n5 = this.dataSize / 2.0;
        final double n6 = this.dataSize / 2.0;
        double n7 = n2;
        double n8 = n2;
        if (n3 < 1.0) {
            n8 /= n3;
        }
        else {
            n7 *= n3;
        }
        final double n9 = 0.5;
        final double n10 = n9 * n9;
        final int n11 = this.dataSizePh - 1;
        for (int i = 0; i != this.gridSizeX; ++i) {
            for (int j = 0; j != this.gridSizeY; ++j) {
                final double n12 = (2 * i / this.gridSizeX - 1.0) * n7;
                final double n13 = -(2 * j / this.gridSizeY - 1.0) * n8;
                final double n14 = rotmatrix[2] * QuantumRotatorFrame.viewDistance;
                final double n15 = rotmatrix[5] * QuantumRotatorFrame.viewDistance;
                final double n16 = rotmatrix[8] * QuantumRotatorFrame.viewDistance;
                final double n17 = rotmatrix[0] * n12 + rotmatrix[1] * n13 - rotmatrix[2];
                final double n18 = rotmatrix[3] * n12 + rotmatrix[4] * n13 - rotmatrix[5];
                final double n19 = rotmatrix[6] * n12 + rotmatrix[7] * n13 - rotmatrix[8];
                Math.sqrt(n12 * n12 + n13 * n13 + 1.0);
                final double n20 = n17 * n17 + n18 * n18 + n19 * n19;
                final double n21 = 2.0 * (n17 * n14 + n18 * n15 + n19 * n16);
                final double n22 = n21 * n21 - 4.0 * n20 * (n14 * n14 + n15 * n15 + n16 * n16 - n10);
                if (n22 < 0.0) {
                    if (state) {
                        final double n23 = 40.0 / this.colorMult;
                        this.fillSquare(i, j, n23, n23, n23);
                    }
                    else {
                        this.fillSquare(i, j, 0.0, 0.0, 64.0 / this.colorMult);
                    }
                }
                else {
                    final double n24 = (-n21 - Math.sqrt(n22)) / (2.0 * n20);
                    final double n25 = (n14 + n17 * n24) * n4;
                    final double n26 = (n15 + n18 * n24) * n5;
                    final double n27 = Math.acos((n16 + n19 * n24) * n6 / (n4 * 0.5)) / 3.141592653589793 * (this.dataSizeTh - 1);
                    final int n28 = (int)n27;
                    final double n29 = n27 - n28;
                    final double calcPhiComponent = this.calcPhiComponent(n25, n26);
                    final int n30 = (int)calcPhiComponent;
                    final double n31 = calcPhiComponent - n30;
                    final int n32 = n30 + 1 & n11;
                    final double n33 = this.func[n28][n30] * (1.0 - n29) * (1.0 - n31) + this.func[n28 + 1][n30] * n29 * (1.0 - n31) + this.func[n28][n32] * (1.0 - n29) * n31 + this.func[n28 + 1][n32] * n29 * n31;
                    final double n34 = this.funci[n28][n30] * (1.0 - n29) * (1.0 - n31) + this.funci[n28 + 1][n30] * n29 * (1.0 - n31) + this.funci[n28][n32] * (1.0 - n29) * n31 + this.funci[n28 + 1][n32] * n29 * n31;
                    double n36;
                    double n37;
                    double n38;
                    if (state) {
                        final double n35 = n33 * n33 + n34 * n34;
                        final PhaseColor phaseColor = this.getPhaseColor(n33, n34);
                        n36 = phaseColor.r * n35;
                        n37 = phaseColor.g * n35;
                        n38 = phaseColor.b * n35;
                    }
                    else {
                        n37 = (n36 = (n38 = n33 * n33 + n34 * n34));
                    }
                    this.fillSquare(i, j, n36, n37, n38);
                }
            }
        }
    }
    
    PhaseColor getPhaseColor(final double n, final double n2) {
        if (n == 0.0 && n2 == 0.0) {
            return this.phaseColors[0][0];
        }
        int n3;
        double n4;
        if (n2 >= 0.0) {
            if (n >= 0.0) {
                if (n >= n2) {
                    n3 = 0;
                    n4 = n2 / n;
                }
                else {
                    n3 = 1;
                    n4 = 1.0 - n / n2;
                }
            }
            else if (-n <= n2) {
                n3 = 2;
                n4 = -n / n2;
            }
            else {
                n3 = 3;
                n4 = 1.0 + n2 / n;
            }
        }
        else if (n <= 0.0) {
            if (n2 >= n) {
                n3 = 4;
                n4 = n2 / n;
            }
            else {
                n3 = 5;
                n4 = 1.0 - n / n2;
            }
        }
        else if (-n2 >= n) {
            n3 = 6;
            n4 = -n / n2;
        }
        else {
            n3 = 7;
            n4 = 1.0 + n2 / n;
        }
        return this.phaseColors[n3][(int)(n4 * 50.0)];
    }
    
    double calcPhiComponent(final double n, final double n2) {
        final int n3 = this.dataSizePh / 8;
        if (n == 0.0 && n2 == 0.0) {
            return 0.0;
        }
        int n4;
        double n5;
        if (n2 >= 0.0) {
            if (n >= 0.0) {
                if (n >= n2) {
                    n4 = 0;
                    n5 = n2 / n;
                }
                else {
                    n4 = 1;
                    n5 = 1.0 - n / n2;
                }
            }
            else if (-n <= n2) {
                n4 = 2;
                n5 = -n / n2;
            }
            else {
                n4 = 3;
                n5 = 1.0 + n2 / n;
            }
        }
        else if (n <= 0.0) {
            if (n2 >= n) {
                n4 = 4;
                n5 = n2 / n;
            }
            else {
                n4 = 5;
                n5 = 1.0 - n / n2;
            }
        }
        else if (-n2 >= n) {
            n4 = 6;
            n5 = -n / n2;
        }
        else {
            n4 = 7;
            n5 = 1.0 + n2 / n;
        }
        return (n4 + n5) * n3;
    }
    
    void genFunc(final double n) {
        final int n2 = this.dataSizePh * 2;
        final int n3 = n2 - 1;
        final double[] array = new double[n2];
        for (int i = 0; i != this.dataSizeTh; ++i) {
            for (int j = 0; j != n2; ++j) {
                array[j] = 0.0;
            }
            for (int k = 0; k != this.stateCount; ++k) {
                final BasisState basisState = this.states[k];
                final int n4 = n3 & -basisState.m * 2;
                final double[] array2 = array;
                final int n5 = n4;
                array2[n5] += ((Complex)basisState).re * basisState.plm[i];
                final double[] array3 = array;
                final int n6 = n4 + 1;
                array3[n6] += ((Complex)basisState).im * basisState.plm[i];
            }
            this.fft.transform(array, true);
            for (int l = 0; l != this.dataSizePh; ++l) {
                this.func[i][l] = array[l * 2] * n;
                this.funci[i][l] = array[l * 2 + 1] * n;
            }
        }
    }
    
    void transform() {
        final int n = this.dataSizePh * 2;
        final int n2 = n - 1;
        for (int i = 0; i != this.stateCount; ++i) {
            ((Complex)this.states[i]).set(0.0);
        }
        this.t = 0.0;
        final double[] array = new double[n];
        for (int j = 0; j != this.dataSizeTh; ++j) {
            final double sin = Math.sin(j * 3.141592653589793 / (this.dataSizeTh - 1));
            for (int k = 0; k != this.dataSizePh; ++k) {
                array[k * 2] = this.func[j][k];
                array[k * 2 + 1] = this.funci[j][k];
            }
            this.fft.transform(array, false);
            for (int l = 0; l != this.stateCount; ++l) {
                final BasisState basisState = this.states[l];
                final int n3 = n2 & -basisState.m * 2;
                ((Complex)basisState).quickAdd(array[n3] * basisState.plm[j] * sin, array[n3 + 1] * basisState.plm[j] * sin);
            }
        }
        for (int n4 = 0; n4 != this.stateCount; ++n4) {
            ((Complex)this.states[n4]).setMagPhase();
        }
        this.maximize();
    }
    
    int sign(final double n) {
        if (n < 0.0) {
            return -1;
        }
        return 1;
    }
    
    public void paint(final Graphics graphics) {
        this.cv.repaint();
    }
    
    public void updateQuantumRotator(final Graphics graphics) {
        if (this.winSize == null || this.winSize.width == 0) {
            return;
        }
        final Graphics graphics2 = this.dbimage.getGraphics();
        graphics2.setColor(this.cv.getBackground());
        graphics2.fillRect(0, 0, this.winSize.width, this.winSize.height);
        graphics2.setColor(this.cv.getForeground());
        if (this.fontMetrics == null) {
            this.fontMetrics = graphics2.getFontMetrics();
        }
        boolean b = false;
        double n = 0.0;
        if (!this.stoppedCheck.getState()) {
            final int value = this.speedBar.getValue();
            final long currentTimeMillis = System.currentTimeMillis();
            if (this.lastTime != 0L) {
                n = value * 6.25E-6 * (currentTimeMillis - this.lastTime);
            }
            this.lastTime = currentTimeMillis;
            this.t += n;
        }
        else {
            this.lastTime = 0L;
            b = true;
        }
        double n2 = 0.0;
        if (this.alwaysNormItem.getState()) {
            this.normalize();
        }
        if (!this.editingFunc && n != 0.0) {
            b = false;
            for (int i = 0; i != this.stateCount; ++i) {
                final BasisState basisState = this.states[i];
                if (((Complex)basisState).mag < 0.01) {
                    ((Complex)basisState).set(0.0);
                }
                else {
                    ((Complex)basisState).rotate(-(((State)basisState).elevel + 0.0) * n);
                }
            }
        }
        for (int j = 0; j != this.stateCount; ++j) {
            n2 += ((Complex)this.states[j]).magSquared();
        }
        double n3 = 1.0 / n2;
        if (n2 == 0.0) {
            n3 = 0.0;
        }
        final double sqrt = Math.sqrt(n3);
        this.genFunc(sqrt);
        this.colorMult = Math.exp(this.brightnessBar.getValue() / 100.0);
        this.computeView(sqrt);
        for (int k = 1; k != this.viewCount; ++k) {
            graphics2.setColor((k == this.selectedPaneHandle) ? Color.yellow : Color.gray);
            graphics2.drawLine(0, this.viewList[k].paneY, this.winSize.width, this.viewList[k].paneY);
        }
        if (this.viewPotential != null) {
            final double n4 = 0.2;
            graphics2.setColor(Color.darkGray);
            final int n5 = this.viewPotential.y + this.viewPotential.height - 1;
            if (this.floorValues == null) {
                this.floorValues = new int[n5 + 1];
            }
            for (int l = 0; l <= n5; ++l) {
                this.floorValues[l] = 0;
            }
            for (int n6 = 0; n6 != this.stateCount; ++n6) {
                final BasisState basisState2 = this.states[n6];
                final double elevel = ((State)basisState2).elevel;
                final int n7 = (int)(224.0 * ((Complex)basisState2).magSquared()) + 1;
                final int n8 = n5 - (int)(n4 * elevel);
                if (n8 >= 0 && n8 <= n5) {
                    final int[] floorValues = this.floorValues;
                    final int n9 = n8;
                    floorValues[n9] += n7;
                }
            }
            for (int n10 = 0; n10 <= n5; ++n10) {
                if (this.floorValues[n10] != 0) {
                    int n11 = this.floorValues[n10] + 32;
                    if (n11 > 255) {
                        n11 = 255;
                    }
                    graphics2.setColor(this.grayLevels[n11]);
                    graphics2.drawLine(0, n10, this.winSize.width, n10);
                }
            }
            if (n2 != 0.0) {
                double n12 = 0.0;
                for (int n13 = 0; n13 != this.stateCount; ++n13) {
                    final BasisState basisState3 = this.states[n13];
                    n12 += ((Complex)basisState3).magSquared() * n3 * ((State)basisState3).elevel;
                }
                final int n14 = n5 - (int)(n4 * n12);
                graphics2.setColor(Color.red);
                graphics2.drawLine(0, n14, this.winSize.width, n14);
            }
            if (this.selectedState != null && !this.dragging) {
                graphics2.setColor(Color.yellow);
                final int n15 = n5 - (int)(n4 * this.selectedState.elevel);
                graphics2.drawLine(0, n15, this.winSize.width, n15);
            }
        }
        if (this.viewL != null) {
            final int n16 = 32;
            final int n17 = 3;
            final int n18 = (n16 * 2 + 1) * n17;
            final double[] array = new double[n18];
            this.calcLz(array, n18, n16, n17, false);
            this.drawFunction(graphics2, this.viewL, array, n18, n17, false);
        }
        if (this.imageSource != null) {
            this.imageSource.newPixels();
        }
        graphics2.drawImage(this.memimage, this.viewX.x, this.viewX.y, null);
        graphics2.setColor(Color.white);
        if (this.axesItem.getState()) {
            this.drawAxes(graphics2);
        }
        for (int n19 = 0; n19 != this.textCount; ++n19) {
            final TextBox textBox = this.textBoxes[n19];
            graphics2.drawString(textBox.text, textBox.x, textBox.y + (textBox.height + this.fontMetrics.getAscent() - this.fontMetrics.getDescent()) / 2);
        }
        graphics2.setColor(Color.yellow);
        if (this.selectedState != null) {
            this.centerString(graphics2, this.selectedState.getText(), this.viewX.y + this.viewX.height - 5);
        }
        if (this.viewStates != null) {
            this.drawPhasors(graphics2, this.viewStates);
            graphics2.setColor(Color.white);
            final int width = this.phasors[0].width;
            final int n20 = this.winSize.width - width / 2;
            final int n21 = this.viewStates.y + width / 2;
            final double n22 = 2.0;
            final double cos = Math.cos(-n22 * this.t + 1.5707963267948966);
            final double sin = Math.sin(-n22 * this.t + 1.5707963267948966);
            final int n23 = width / 2;
            final int n24 = (int)(cos * n23);
            final int n25 = (int)(-sin * n23);
            graphics2.drawOval(n20 - n23, n21 - n23, width, width);
            graphics2.drawLine(n20, n21, n20 + n24, n21 + n25);
            graphics2.drawLine(n20 + n24, n21 + n25 - 1, n20 + n24, n21 + n25 + 1);
            graphics2.drawLine(n20 + n24 - 1, n21 + n25, n20 + n24 + 1, n21 + n25);
        }
        graphics.drawImage(this.dbimage, 0, 0, this);
        if (!b) {
            this.cv.repaint(this.pause);
        }
    }
    
    void fillSquare(final int n, final int n2, double n3, double n4, double n5) {
        final int width = this.viewX.width;
        final int height = this.viewX.height;
        final int n6 = n * width / this.gridSizeX;
        final int n7 = n2 * height / this.gridSizeY;
        final int n8 = (n + 1) * width / this.gridSizeX;
        final int n9 = (n2 + 1) * height / this.gridSizeY;
        n3 *= this.colorMult;
        n4 *= this.colorMult;
        n5 *= this.colorMult;
        if (n3 == 0.0 && n4 == 0.0 && n5 == 0.0) {
            final int n10 = n9 * this.viewX.width;
            for (int i = n6; i < n8; ++i) {
                for (int j = n7 * this.viewX.width; j < n10; j += this.viewX.width) {
                    this.pixels[i + j] = -16777216;
                }
            }
            return;
        }
        final double max = this.max(n3, this.max(n4, n5));
        if (max > 255.0) {
            final double n11 = max / 255.0;
            n3 /= n11;
            n4 /= n11;
            n5 /= n11;
        }
        final int n12 = -16777216 + ((int)n3 << 16) | (int)n4 << 8 | (int)n5;
        final int n13 = n9 * this.viewX.width;
        for (int k = n6; k < n8; ++k) {
            for (int l = n7 * this.viewX.width; l < n13; l += this.viewX.width) {
                this.pixels[k + l] = n12;
            }
        }
    }
    
    public void centerString(final Graphics graphics, final String s, final int n) {
        graphics.drawString(s, (this.winSize.width - this.fontMetrics.stringWidth(s)) / 2, n);
    }
    
    boolean visibleFace(final int n, final int n2, final int n3) {
        return (n - QuantumRotatorFrame.viewDistance * this.rotmatrix[2]) * n + (n2 - QuantumRotatorFrame.viewDistance * this.rotmatrix[5]) * n2 + (n3 - QuantumRotatorFrame.viewDistance * this.rotmatrix[8]) * n3 < 0.0;
    }
    
    void drawPhasors(final Graphics graphics, final View view) {
        for (int i = 0; i != this.phasorCount; ++i) {
            final Phasor phasor = this.phasors[i];
            final State state = phasor.state;
            final int width = phasor.width;
            final int n = width / 2;
            final int n2 = phasor.x + n;
            final int n3 = phasor.y + n;
            graphics.setColor((this.selectedState != null && this.selectedState.elevel == state.elevel) ? Color.yellow : ((((Complex)state).mag == 0.0) ? this.gray2 : Color.white));
            graphics.drawOval(n2 - n, n3 - n, width, width);
            final int n4 = (int)(((Complex)state).re * n);
            final int n5 = (int)(-((Complex)state).im * n);
            graphics.drawLine(n2, n3, n2 + n4, n3 + n5);
            graphics.drawLine(n2 + n4, n3 + n5 - 1, n2 + n4, n3 + n5 + 1);
            graphics.drawLine(n2 + n4 - 1, n3 + n5, n2 + n4 + 1, n3 + n5);
        }
    }
    
    void drawFunction(final Graphics graphics, final View view, final double[] array, final int n, final int n2, final boolean b) {
        double n3 = 0.0;
        double n4 = 0.0;
        double n5 = 0.0;
        double n6 = 0.0;
        final int width = this.winSize.width;
        final int n8;
        final int n7 = n8 = (b ? (width / (n - 1)) : (width * (n / 2) / (n - 1)));
        for (int i = 0; i != n; ++i) {
            final int n9 = width * i / (n - 1);
            final double n10 = array[i];
            final double n11 = n10 * n10;
            if (n11 > n5) {
                n5 = n11;
            }
            final int n12 = n9 - n8;
            n3 += n11 * n12;
            n4 += n11 * n12 * n12;
            n6 += n11;
        }
        final int n13 = n7;
        final double n14 = n3 / n6;
        final double n15 = n4 / n6;
        final double sqrt = Math.sqrt(n5);
        Math.sqrt(n15 - n14 * n14);
        int n16 = 0;
        view.scale = 1.0 / sqrt;
        if (view.scale > 1.0E8) {
            view.scale = 1.0E8;
        }
        graphics.setColor(Color.gray);
        graphics.drawLine(n7, view.y, n7, view.y + view.height);
        final double n17 = 0.9 * view.height;
        final int n18 = view.y + view.height / 2 + (int)n17 / 2;
        final double n19 = n17 * view.scale;
        graphics.setColor(Color.white);
        int n20 = -1;
        for (int j = 0; j != n; ++j) {
            final int n21 = width * j / (n - 1);
            final int n22 = n18 - (int)(n19 * array[j]);
            if (j % n2 == 1) {
                graphics.setColor(Color.gray);
                graphics.drawLine(n21, n18, n21, n18 + 4);
                graphics.setColor(Color.white);
            }
            if (n20 != -1) {
                graphics.drawLine(n20, n16, n21, n22);
            }
            n20 = n21;
            n16 = n22;
        }
        if (n5 > 0.0) {
            final double n23 = n14 + (n13 + 0.5);
            graphics.setColor(Color.red);
            graphics.drawLine((int)n23, view.y, (int)n23, view.y + view.height);
        }
    }
    
    void drawAxes(final Graphics graphics) {
        graphics.setColor(Color.white);
        final double n = 0.5;
        this.map3d(0.0, 0.0, 0.0, this.xpoints, this.ypoints, 0, this.viewAxes);
        this.map3d(n, 0.0, 0.0, this.xpoints, this.ypoints, 1, this.viewAxes);
        this.drawArrow(graphics, "x", this.xpoints[0], this.ypoints[0], this.xpoints[1], this.ypoints[1]);
        this.map3d(0.0, n, 0.0, this.xpoints, this.ypoints, 1, this.viewAxes);
        this.drawArrow(graphics, "y", this.xpoints[0], this.ypoints[0], this.xpoints[1], this.ypoints[1]);
        this.map3d(0.0, 0.0, n, this.xpoints, this.ypoints, 1, this.viewAxes);
        this.drawArrow(graphics, "z", this.xpoints[0], this.ypoints[0], this.xpoints[1], this.ypoints[1]);
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
    
    void map3d(final double n, final double n2, final double n3, final int[] array, final int[] array2, final int n4, final Rectangle rectangle) {
        final double[] rotmatrix = this.rotmatrix;
        final double n5 = n * rotmatrix[0] + n2 * rotmatrix[3] + n3 * rotmatrix[6];
        final double n6 = n * rotmatrix[1] + n2 * rotmatrix[4] + n3 * rotmatrix[7];
        final double n7 = QuantumRotatorFrame.viewDistance - (n * rotmatrix[2] + n2 * rotmatrix[5] + n3 * rotmatrix[8]);
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
    
    void unmap3d(final double[] array, final int n, final int n2, final double[] array2, final double[] array3) {
        double n3 = this.viewX.width * this.zoom / 2.0;
        double n4 = this.viewX.height * this.zoom / 2.0;
        final double n5 = this.viewX.width / this.viewX.height;
        if (n5 < 1.0) {
            n4 *= n5;
        }
        else {
            n3 /= n5;
        }
        final double n6 = (n - (this.viewX.x + this.viewX.width / 2)) / n3;
        final double n7 = -(n2 - (this.viewX.y + this.viewX.height / 2)) / n4;
        final double[] rotmatrix = this.rotmatrix;
        final double n8 = QuantumRotatorFrame.viewDistance * rotmatrix[2];
        final double n9 = QuantumRotatorFrame.viewDistance * rotmatrix[5];
        final double n10 = QuantumRotatorFrame.viewDistance * rotmatrix[8];
        final double n11 = n6 * rotmatrix[0] + n7 * rotmatrix[1] - rotmatrix[2];
        final double n12 = n6 * rotmatrix[3] + n7 * rotmatrix[4] - rotmatrix[5];
        final double n13 = n6 * rotmatrix[6] + n7 * rotmatrix[7] - rotmatrix[8];
        final double n14 = ((array3[0] - n8) * array2[0] + (array3[1] - n9) * array2[1] + (array3[2] - n10) * array2[2]) / (array2[0] * n11 + array2[1] * n12 + array2[2] * n13);
        array[0] = n8 + n11 * n14;
        array[1] = n9 + n12 * n14;
        array[2] = n10 + n13 * n14;
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
        if (actionEvent.getSource() == this.exitItem) {
            this.applet.destroyFrame();
            return;
        }
        this.cv.repaint();
        if (actionEvent.getSource() == this.blankButton) {
            this.doClear();
        }
        if (actionEvent.getSource() == this.normalizeButton) {
            this.normalize();
        }
        if (actionEvent.getSource() == this.maximizeButton) {
            this.maximize();
        }
    }
    
    void doGauss(int n, int n2, final int n3) {
        if (n < 0) {
            n = 0;
        }
        if (n2 < 0) {
            n2 = 0;
        }
        final double n4 = 500 / (n + 5);
        final double n5 = 500 / (n2 + 5);
        double n6 = n3 / 3.0;
        if (n6 > 24.0) {
            n6 = 24.0;
        }
        if (n6 < -24.0) {
            n6 = -24.0;
        }
        for (int i = 0; i != this.dataSizeTh; ++i) {
            final double n7 = i * 3.141592653589793 / (this.dataSizeTh - 1);
            final double cos = Math.cos(n7);
            final double sin = Math.sin(n7);
            for (int j = 0; j != this.dataSizePh; ++j) {
                final double n8 = j * 2 * 3.141592653589793 / this.dataSizePh;
                final double n9 = Math.cos(n8) * sin;
                final double n10 = Math.sin(n8) * sin;
                final double exp = Math.exp(-n4 * ((n10 + 1.0) * (n10 + 1.0) + n9 * n9) - n5 * cos * cos);
                this.func[i][j] = exp * Math.cos((n8 - 4.71238898038469) * n6);
                this.funci[i][j] = exp * Math.sin((n8 - 4.71238898038469) * n6);
            }
        }
        this.transform();
        this.editingFunc = true;
    }
    
    public void adjustmentValueChanged(final AdjustmentEvent adjustmentEvent) {
        System.out.print(String.valueOf(((Scrollbar)adjustmentEvent.getSource()).getValue()) + "\n");
        if (adjustmentEvent.getSource() == this.phasorBar) {
            this.setupDisplay();
        }
        if (adjustmentEvent.getSource() == this.brightnessBar) {
            this.userBrightMult = Math.exp(this.brightnessBar.getValue() / 100.0) / this.bestBrightness;
        }
        if (adjustmentEvent.getSource() == this.resolutionBar) {
            this.setResolution();
        }
        this.cv.repaint(this.pause);
    }
    
    public void mouseDragged(final MouseEvent mouseEvent) {
        this.dragging = true;
        this.edit(mouseEvent);
        this.dragX = mouseEvent.getX();
        this.dragY = mouseEvent.getY();
    }
    
    public void mouseMoved(final MouseEvent mouseEvent) {
        if (this.dragging) {
            return;
        }
        final int x = mouseEvent.getX();
        final int y = mouseEvent.getY();
        this.dragX = x;
        this.dragY = y;
        final int selectedPaneHandle = this.selectedPaneHandle;
        final int selection = this.selection;
        final State selectedState = this.selectedState;
        this.selectedPaneHandle = -1;
        this.selection = 0;
        this.selectedState = null;
        for (int i = 1; i != this.viewCount; ++i) {
            final int n = y - this.viewList[i].paneY;
            if (n >= -3 && n <= 3) {
                this.selectedPaneHandle = i;
                this.selection = 4;
            }
        }
        if (this.viewX != null && this.viewX.inside(x, y)) {
            this.selection = 2;
        }
        else if (this.viewPotential != null && this.viewPotential.contains(x, y)) {
            this.selection = 1;
        }
        else if (this.viewStates != null && this.viewStates.inside(x, y)) {
            this.findPhasor(this.viewStates, x, y);
        }
        if (selectedPaneHandle != this.selectedPaneHandle || selection != this.selection || selectedState != this.selectedState) {
            this.cv.repaint(this.pause);
        }
    }
    
    void findPhasor(final View view, final int n, final int n2) {
        for (int i = 0; i != this.phasorCount; ++i) {
            if (this.phasors[i].inside(n, n2)) {
                this.selectedPhasor = this.phasors[i];
                this.selectedState = this.selectedPhasor.state;
                this.selection = 3;
                return;
            }
        }
    }
    
    public void mouseClicked(final MouseEvent mouseEvent) {
        if (this.selection == 3) {
            this.editMagClick();
        }
        if (mouseEvent.getClickCount() == 2 && this.selectedState != null) {
            this.enterSelectedState();
        }
    }
    
    void enterSelectedState() {
        for (int i = 0; i != this.stateCount; ++i) {
            if (this.states[i] != this.selectedState) {
                ((Complex)this.states[i]).set(0.0);
            }
        }
        ((Complex)this.selectedState).set(1.0);
        this.cv.repaint(this.pause);
    }
    
    public void mouseEntered(final MouseEvent mouseEvent) {
    }
    
    public void mouseExited(final MouseEvent mouseEvent) {
        if (!this.dragging && this.selection != 0) {
            this.selectedPaneHandle = -1;
            this.selectedState = null;
            this.selectedPhasor = null;
            this.selection = 0;
            this.cv.repaint(this.pause);
        }
    }
    
    public void mousePressed(final MouseEvent mouseEvent) {
        if ((mouseEvent.getModifiers() & 0x10) == 0x0) {
            return;
        }
        final int x = mouseEvent.getX();
        this.dragStartX = x;
        this.dragX = x;
        final int y = mouseEvent.getY();
        this.dragStartY = y;
        this.dragY = y;
        this.dragZoomStart = this.zoom;
        this.dragging = true;
        this.edit(mouseEvent);
    }
    
    public void mouseReleased(final MouseEvent mouseEvent) {
        if (this.dragging) {
            this.cv.repaint();
        }
        final boolean b = false;
        this.editingFunc = b;
        this.dragging = b;
    }
    
    public void itemStateChanged(final ItemEvent itemEvent) {
        if (itemEvent.getItemSelectable() instanceof CheckboxMenuItem) {
            this.setupDisplay();
            this.cv.repaint(this.pause);
            return;
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
            case 4: {
                this.editHandle(y);
            }
            case 3: {
                this.editMag(x, y);
            }
            case 2: {
                this.editX(x, y);
            }
            default: {}
        }
    }
    
    void editHandle(final int n) {
        final int n2 = n - this.viewList[this.selectedPaneHandle].paneY;
        final View view = this.viewList[this.selectedPaneHandle - 1];
        final View view2 = this.viewList[this.selectedPaneHandle];
        final int n3 = 10;
        if (view.height + n2 < n3 || view2.height - n2 < n3) {
            return;
        }
        final View view3 = view;
        view3.height += n2;
        final View view4 = view2;
        view4.height -= n2;
        final View view5 = view2;
        view5.y += n2;
        final View view6 = view2;
        view6.paneY += n2;
        this.cv.repaint(this.pause);
        this.setSubViews();
    }
    
    void editX(final int n, final int n2) {
        final int selectedIndex = this.modeChooser.getSelectedIndex();
        if (selectedIndex == 0) {
            this.rotate((this.dragX - n) / 40.0, -(this.dragY - n2) / 40.0);
            this.cv.repaint(this.pause);
            return;
        }
        if (selectedIndex == 1) {
            this.doGauss(n - this.dragStartX, n2 - this.dragStartY, 0);
            this.setView();
            this.cv.repaint();
            return;
        }
        if (selectedIndex == 2) {
            final int n3 = n - this.dragStartX;
            final int n4 = n2 - this.dragStartY;
            this.doGauss(n4, n4, n3);
            this.setView();
            this.cv.repaint();
            return;
        }
        if (selectedIndex == 3) {
            this.zoom = this.dragZoomStart + (this.dragX - this.dragStartX) / 20.0;
            if (this.zoom < 0.1) {
                this.zoom = 0.1;
            }
            System.out.println(this.zoom);
            this.cv.repaint(this.pause);
        }
    }
    
    void editMag(int n, int n2) {
        if (this.selectedPhasor == null) {
            return;
        }
        final int n3 = this.selectedPhasor.width / 2;
        final int n4 = this.selectedPhasor.x + n3;
        final int n5 = this.selectedPhasor.y + n3;
        n -= n4;
        n2 -= n5;
        double n6 = Math.sqrt(n * n + n2 * n2) / n3;
        final double atan2 = Math.atan2(-n2, n);
        if (n6 > 10.0) {
            n6 = 0.0;
        }
        if (n6 > 1.0) {
            n6 = 1.0;
        }
        ((Complex)this.selectedState).setMagPhase(n6, atan2);
        this.cv.repaint(this.pause);
    }
    
    void editMagClick() {
        if (this.selectedState == null) {
            return;
        }
        if (this.magDragStart < 0.5) {
            ((Complex)this.selectedState).set(1.0, 0.0);
        }
        else {
            ((Complex)this.selectedState).set(0.0);
        }
        this.cv.repaint(this.pause);
    }
    
    void calcLz(final double[] array, final int n, final int n2, final int n3, final boolean b) {
        int n4 = n / 2;
        for (int i = 0; i != n; ++i) {
            array[i] = 0.0;
        }
        if (b) {
            n4 = 1;
        }
        for (int j = 0; j != this.stateCount; ++j) {
            final BasisState basisState = this.states[j];
            if (basisState.l <= n2) {
                if (b) {
                    final int n5 = n4 + basisState.m * basisState.m * n3;
                    array[n5] += ((Complex)basisState).magSquared();
                }
                else {
                    final int n6 = n4 + basisState.m * n3;
                    array[n6] += ((Complex)basisState).magSquared();
                }
            }
        }
        for (int k = 0; k != n; ++k) {
            array[k] = Math.sqrt(array[k]);
        }
    }
    
    void doClear() {
        for (int i = 0; i != this.stateCount; ++i) {
            ((Complex)this.states[i]).set(0.0);
        }
    }
    
    void normalize() {
        double n = 0.0;
        for (int i = 0; i != this.stateCount; ++i) {
            n += ((Complex)this.states[i]).magSquared();
        }
        if (n == 0.0) {
            return;
        }
        final double n2 = 1.0 / Math.sqrt(n);
        for (int j = 0; j != this.stateCount; ++j) {
            ((Complex)this.states[j]).mult(n2);
        }
        this.cv.repaint(this.pause);
    }
    
    void maximize() {
        double mag = 0.0;
        for (int i = 0; i != this.stateCount; ++i) {
            if (((Complex)this.states[i]).mag > mag) {
                mag = ((Complex)this.states[i]).mag;
            }
        }
        if (mag == 0.0) {
            return;
        }
        for (int j = 0; j != this.stateCount; ++j) {
            ((Complex)this.states[j]).mult(1.0 / mag);
            if (((Complex)this.states[j]).mag < 0.01) {
                ((Complex)this.states[j]).set(0.0);
            }
        }
        this.cv.repaint(this.pause);
    }
    
    BasisState getState(final int n, final int n2, final int n3) {
        final int n4 = n - 1;
        return this.states[n4 * (n4 + 1) * (2 * n4 + 1) / 6 + n2 * n2 + n2 + n3];
    }
    
    double radialNorm(final int n, final int n2) {
        this.factorial(n + n2);
        return Math.sqrt(4.0 * this.factorial(n + n2) / (n * n * n * n * this.factorial(n - n2 - 1))) / this.factorial(2 * n2 + 1);
    }
    
    double sphericalNorm(final int n, final int n2) {
        return Math.sqrt((2 * n + 1) * this.factorial(n - n2) / (12.566370614359172 * this.factorial(n + n2)));
    }
    
    double factorial(int i) {
        double n;
        for (n = 1.0; i > 1; n *= i--) {}
        return n;
    }
    
    double plgndr(final int n, final int n2, final double n3) {
        double n4 = 0.0;
        if (n2 < 0 || n2 > n || Math.abs(n3) > 1.0) {
            System.out.print("bad arguments in plgndr\n");
        }
        double n5 = 1.0;
        if (n2 > 0) {
            final double sqrt = Math.sqrt((1.0 - n3) * (1.0 + n3));
            double n6 = 1.0;
            for (int i = 1; i <= n2; ++i) {
                n5 *= -n6 * sqrt;
                n6 += 2.0;
            }
        }
        if (n == n2) {
            return n5;
        }
        double n7 = n3 * (2 * n2 + 1) * n5;
        if (n == n2 + 1) {
            return n7;
        }
        for (int j = n2 + 2; j <= n; ++j) {
            n4 = (n3 * (2 * j - 1) * n7 - (j + n2 - 1) * n5) / (j - n2);
            n5 = n7;
            n7 = n4;
        }
        return n4;
    }
    
    double hypser(int n, int n2, final double n3) {
        double n4 = 1.0;
        double n5 = 1.0;
        for (int i = 1; i <= 1000; ++i) {
            n4 *= n * n3 / (i * n2);
            if (n4 == 0.0) {
                return n5;
            }
            n5 += n4;
            ++n;
            ++n2;
        }
        System.out.print("convergence failure in hypser\n");
        return 0.0;
    }
    
    static {
        QuantumRotatorFrame.maxModes = 10;
        QuantumRotatorFrame.maxDispCoefs = 8;
        QuantumRotatorFrame.viewDistance = 12;
    }
    
    class Phasor extends Rectangle
    {
        State state;
        
        Phasor(final int n, final int n2, final int n3, final int n4) {
            super(n, n2, n3, n4);
        }
    }
    
    abstract class State extends Complex
    {
        double elevel;
        
        void setBasisActive() {
        }
        
        abstract String getText();
    }
    
    class BasisState extends State
    {
        int l;
        int m;
        double[] plm;
        
        String getText() {
            return "l = " + this.l + ", m = " + this.m;
        }
    }
    
    class Complex
    {
        public double re;
        public double im;
        public double mag;
        public double phase;
        
        Complex() {
            final double n = 0.0;
            this.phase = n;
            this.mag = n;
            this.im = n;
            this.re = n;
        }
        
        Complex(final double n, final double n2) {
            this.set(n, n2);
        }
        
        double magSquared() {
            return this.mag * this.mag;
        }
        
        void set(final double re, final double im) {
            this.re = re;
            this.im = im;
            this.setMagPhase();
        }
        
        void set(final double re) {
            this.re = re;
            this.im = 0.0;
            this.setMagPhase();
        }
        
        void set(final Complex complex) {
            this.re = complex.re;
            this.im = complex.im;
            this.mag = complex.mag;
            this.phase = complex.phase;
        }
        
        void add(final double n) {
            this.re += n;
            this.setMagPhase();
        }
        
        void add(final double n, final double n2) {
            this.re += n;
            this.im += n2;
            this.setMagPhase();
        }
        
        void add(final Complex complex) {
            this.re += complex.re;
            this.im += complex.im;
            this.setMagPhase();
        }
        
        void quickAdd(final double n, final double n2) {
            this.re += n;
            this.im += n2;
        }
        
        void square() {
            this.set(this.re * this.re - this.im * this.im, 2.0 * this.re * this.im);
        }
        
        void mult(final double n, final double n2) {
            this.set(this.re * n - this.im * n2, this.re * n2 + this.im * n);
        }
        
        void mult(final double n) {
            this.re *= n;
            this.im *= n;
            this.mag *= n;
        }
        
        void mult(final Complex complex) {
            this.mult(complex.re, complex.im);
        }
        
        void setMagPhase() {
            this.mag = Math.sqrt(this.re * this.re + this.im * this.im);
            this.phase = Math.atan2(this.im, this.re);
        }
        
        void setMagPhase(final double mag, final double phase) {
            this.mag = mag;
            this.phase = phase;
            this.re = mag * Math.cos(phase);
            this.im = mag * Math.sin(phase);
        }
        
        void rotate(final double n) {
            this.setMagPhase(this.mag, (this.phase + n) % 6.283185307179586);
        }
        
        void conjugate() {
            this.im = -this.im;
            this.phase = -this.phase;
        }
    }
    
    class PhaseColor
    {
        public double r;
        public double g;
        public double b;
        
        PhaseColor(final double r, final double g, final double b) {
            this.r = r;
            this.g = g;
            this.b = b;
        }
    }
    
    class View extends Rectangle
    {
        double scale;
        int paneY;
        int[] pixels;
        
        View() {
        }
        
        View(final View view) {
            super(view);
        }
    }
    
    class TextBox extends Rectangle
    {
        String text;
        
        TextBox(final int n, final int n2, final int n3, final int n4, final String text) {
            super(n, n2, n3, n4);
            this.text = text;
        }
    }
}
