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
import java.awt.Color;
import java.awt.image.MemoryImageSource;
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

class AtomViewerFrame extends Frame implements ComponentListener, ActionListener, AdjustmentListener, MouseMotionListener, MouseListener, ItemListener
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
    CheckboxMenuItem l2CheckItem;
    CheckboxMenuItem rCheckItem;
    CheckboxMenuItem alwaysNormItem;
    CheckboxMenuItem cubicItem;
    CheckboxMenuItem dimensionsItem;
    CheckboxMenuItem axesItem;
    CheckboxMenuItem autoZoomItem;
    CheckboxMenuItem animatedZoomItem;
    CheckboxMenuItem[] samplesItems;
    int[] samplesNums;
    MenuItem exitItem;
    Choice modeChooser;
    Choice viewChooser;
    Choice sliceChooser;
    Choice nChooser;
    Choice lChooser;
    Choice mChooser;
    static final int SLICE_NONE = 0;
    static final int SLICE_X = 1;
    static final int SLICE_Y = 2;
    static final int SLICE_Z = 3;
    Scrollbar speedBar;
    Scrollbar resolutionBar;
    Scrollbar internalResBar;
    Scrollbar brightnessBar;
    Scrollbar scaleBar;
    View viewPotential;
    View viewX;
    View viewL;
    View viewL2;
    View viewStates;
    View viewRadial;
    View[] viewList;
    int viewCount;
    Orbital[] orbitals;
    int orbCount;
    Phasor[] phasors;
    int phasorCount;
    BasisState[] states;
    int stateCount;
    AlternateBasis realBasis;
    AlternateBasis n2l1xBasis;
    AlternateBasis n2l1yBasis;
    AlternateBasis n3l1xBasis;
    AlternateBasis n3l1yBasis;
    AlternateBasis n3l2xBasis;
    AlternateBasis n3l2yBasis;
    AlternateBasis n4l1xBasis;
    AlternateBasis n4l1yBasis;
    AlternateBasis n4l2xBasis;
    AlternateBasis n4l2yBasis;
    AlternateBasis n4l3xBasis;
    AlternateBasis n4l3yBasis;
    AlternateBasis n4l3CubicBasis;
    AlternateBasis spHybridBasis;
    AlternateBasis sp2HybridBasis;
    AlternateBasis sp3HybridBasis;
    AlternateBasis[] basisList;
    int basisCount;
    TextBox[] textBoxes;
    int textCount;
    boolean changingDerivedStates;
    boolean mouseDown;
    double dragZoomStart;
    double lastXRot;
    double lastYRot;
    double colorMult;
    double zoom;
    double[] rotmatrix;
    Rectangle viewAxes;
    static final double pi = 3.141592653589793;
    static final double pi2 = 6.283185307179586;
    static final double root2 = 1.4142135623730951;
    static final double root2inv = 0.7071067811865476;
    static final double baseEnergy = 0.55;
    int[] xpoints;
    int[] ypoints;
    int selectedPaneHandle;
    PhaseColor[] phaseColors;
    double resadj;
    boolean dragging;
    MemoryImageSource imageSource;
    int[] pixels;
    int sampleCount;
    int dataSize;
    static int maxModes;
    static int maxDispCoefs;
    static int viewDistance;
    int pause;
    AtomViewer applet;
    State selectedState;
    Phasor selectedPhasor;
    int selection;
    static final int SEL_NONE = 0;
    static final int SEL_POTENTIAL = 1;
    static final int SEL_X = 2;
    static final int SEL_STATES = 3;
    static final int SEL_HANDLE = 4;
    static final int MODE_ANGLE = 0;
    static final int MODE_ROTATE_X = 1;
    static final int MODE_ROTATE_Y = 2;
    static final int MODE_ROTATE_Z = 3;
    static final int MODE_SLICE = 5;
    static final int VIEW_REAL = 0;
    static final int VIEW_COMPLEX = 1;
    static final int VIEW_COMBO_REAL = 2;
    static final int VIEW_COMBO_COMP = 3;
    static final int VIEW_COMBO_N2L1 = 4;
    static final int VIEW_COMBO_N3L1 = 5;
    static final int VIEW_COMBO_N3L2 = 6;
    static final int VIEW_COMBO_N4L1 = 7;
    static final int VIEW_COMBO_N4L2 = 8;
    static final int VIEW_COMBO_N4L3 = 9;
    static final int VIEW_COMBO_HYBRID = 10;
    int[][] slicerPoints;
    double[][] sliceFaces;
    double[] sliceFace;
    int sliceFaceCount;
    double sliceval;
    int[] sampleMult;
    boolean selectedSlice;
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
    float funcr;
    float funci;
    int phiIndex;
    double bestBrightness;
    double userBrightMult;
    boolean manualScale;
    Color gray2;
    FontMetrics fontMetrics;
    AtomViewerCanvas cv;
    boolean useBufferedImage;
    double[] l1xArray;
    double[] l1yArray;
    static final double root6by4 = 0.6123724356957945;
    double[] l2xArray;
    double[] l2yArray;
    double[] l3xArray;
    double[] l3yArray;
    double[] l3CubicArray;
    double[] spHybridArray;
    double[] sp2HybridArray;
    double[] sp3HybridArray;
    String[] spHybridText;
    String[] sp2HybridText;
    String[] sp3HybridText;
    String[] codeLetter;
    String[] l1RealText;
    String[] l2RealText;
    String[] l3RealText;
    String[] l3CubicRealText;
    long lastTime;
    int frameLen;
    int scaleValue;
    
    public String getAppletInfo() {
        return "AtomViewer by Paul Falstad";
    }
    
    int getrand(final int n) {
        int nextInt = this.random.nextInt();
        if (nextInt < 0) {
            nextInt = -nextInt;
        }
        return nextInt % n;
    }
    
    AtomViewerFrame(final AtomViewer applet) {
        super("Hydrogenic Atom Viewer v1.5");
        this.gridSizeX = 200;
        this.gridSizeY = 200;
        this.samplesNums = new int[] { 9, 15, 25, 35, 45, 55 };
        this.dragging = false;
        this.selection = -1;
        this.userBrightMult = 1.0;
        this.useBufferedImage = false;
        this.l1xArray = new double[] { 0.5, 0.0, -0.7071067811865476, 0.0, 0.5, 0.0, 0.7071067811865476, 0.0, 0.0, 0.0, -0.7071067811865476, 0.0, 0.5, 0.0, 0.7071067811865476, 0.0, 0.5, 0.0 };
        this.l1yArray = new double[] { 0.5, 0.0, 0.0, -0.7071067811865476, -0.5, 0.0, 0.0, -0.7071067811865476, 0.0, 0.0, 0.0, -0.7071067811865476, 0.5, 0.0, 0.0, 0.7071067811865476, -0.5, 0.0 };
        this.l2xArray = new double[] { 0.25, 0.0, -0.5, 0.0, 0.6123724356957945, 0.0, -0.5, 0.0, 0.25, 0.0, -0.5, 0.0, 0.5, 0.0, 0.0, 0.0, -0.5, 0.0, 0.5, 0.0, 0.6123724356957945, 0.0, 0.0, 0.0, -0.5, 0.0, 0.0, 0.0, 0.6123724356957945, 0.0, -0.5, 0.0, -0.5, 0.0, 0.0, 0.0, 0.5, 0.0, 0.5, 0.0, 0.25, 0.0, 0.5, 0.0, 0.6123724356957945, 0.0, 0.5, 0.0, 0.25, 0.0 };
        this.l2yArray = new double[] { 0.25, 0.0, 0.0, -0.5, -0.6123724356957945, 0.0, 0.0, 0.5, 0.25, 0.0, -0.5, 0.0, 0.0, 0.5, 0.0, 0.0, 0.0, 0.5, 0.5, 0.0, -0.6123724356957945, 0.0, 0.0, 0.0, -0.5, 0.0, 0.0, 0.0, -0.6123724356957945, 0.0, -0.5, 0.0, 0.0, -0.5, 0.0, 0.0, 0.0, -0.5, 0.5, 0.0, 0.25, 0.0, 0.0, 0.5, -0.6123724356957945, 0.0, 0.0, -0.5, 0.25, 0.0 };
        this.l3xArray = new double[] { 0.125, 0.0, -0.306186, 0.0, 0.484123, 0.0, -0.559017, 0.0, 0.484123, 0.0, -0.306186, 0.0, 0.125, 0.0, -0.306186, 0.0, 0.5, 0.0, -0.395285, 0.0, 0.0, 0.0, 0.395285, 0.0, -0.5, 0.0, 0.306186, 0.0, 0.484123, 0.0, -0.395285, 0.0, -0.125, 0.0, 0.433013, 0.0, -0.125, 0.0, -0.395285, 0.0, 0.484123, 0.0, 0.559017, 0.0, 0.0, 0.0, -0.433013, 0.0, 0.0, 0.0, 0.433013, 0.0, 0.0, 0.0, -0.559017, 0.0, 0.484123, 0.0, 0.395285, 0.0, -0.125, 0.0, -0.433013, 0.0, -0.125, 0.0, 0.395285, 0.0, 0.484123, 0.0, -0.306186, 0.0, -0.5, 0.0, -0.395285, 0.0, 0.0, 0.0, 0.395285, 0.0, 0.5, 0.0, 0.306186, 0.0, 0.125, 0.0, 0.306186, 0.0, 0.484123, 0.0, 0.559017, 0.0, 0.484123, 0.0, 0.306186, 0.0, 0.125, 0.0 };
        this.l3yArray = new double[] { -0.125, 0.0, 0.0, 0.306186, 0.484123, 0.0, 0.0, -0.559017, -0.484123, 0.0, 0.0, 0.306186, 0.125, 0.0, 0.306186, 0.0, 0.0, -0.5, -0.395285, 0.0, 0.0, 0.0, -0.395285, 0.0, 0.0, 0.5, 0.306186, 0.0, -0.484123, 0.0, 0.0, 0.395285, -0.125, 0.0, 0.0, 0.433013, 0.125, 0.0, 0.0, 0.395285, 0.484123, 0.0, 0.0, 0.559017, 0.0, 0.0, 0.0, 0.433013, 0.0, 0.0, 0.0, 0.433013, 0.0, 0.0, 0.0, 0.559017, -0.484123, 0.0, 0.0, -0.395285, -0.125, 0.0, 0.0, -0.433013, 0.125, 0.0, 0.0, -0.395285, 0.484123, 0.0, 0.306186, 0.0, 0.0, 0.5, -0.395285, 0.0, 0.0, 0.0, -0.395285, 0.0, 0.0, -0.5, 0.306186, 0.0, -0.125, 0.0, 0.0, -0.306186, 0.484123, 0.0, 0.0, 0.559017, -0.484123, 0.0, 0.0, -0.306186, 0.125, 0.0 };
        this.l3CubicArray = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.559017, 0.0, 0.0, 0.0, -0.433013, 0.0, 0.0, 0.0, 0.433013, 0.0, 0.0, 0.0, -0.559017, 0.0, 0.0, 0.559017, 0.0, 0.0, 0.0, 0.433013, 0.0, 0.0, 0.0, 0.433013, 0.0, 0.0, 0.0, 0.559017, 0.0, 0.0, 0.7071067811865476, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.7071067811865476, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, -0.7071067811865476, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.7071067811865476, 0.0, 0.0, 0.433013, 0.0, 0.0, 0.0, 0.559017, 0.0, 0.0, 0.0, -0.559017, 0.0, 0.0, 0.0, -0.433013, 0.0, 0.0, 0.433013, 0.0, 0.0, 0.0, -0.559017, 0.0, 0.0, 0.0, -0.559017, 0.0, 0.0, 0.0, 0.433013 };
        this.spHybridArray = new double[] { -0.7071067811865476, 0.0, 0.0, 0.0, -0.7071067811865476, 0.0, 0.0, 0.0, -0.7071067811865476, 0.0, 0.0, 0.0, 0.7071067811865476, 0.0, 0.0, 0.0, 0.0, 0.0, 0.7071067811865476, 0.0, 0.0, 0.0, -0.7071067811865476, 0.0, 0.0, 0.0, 0.0, -0.7071067811865476, 0.0, 0.0, 0.0, -0.7071067811865476 };
        this.sp2HybridArray = new double[] { -0.57735, 0.0, 0.57735, 0.0, 0.0, 0.0, -0.57735, 0.0, -0.57735, 0.0, -0.288675, -0.5, 0.0, 0.0, 0.288675, -0.5, -0.57735, 0.0, -0.288675, 0.5, 0.0, 0.0, 0.288675, 0.5, 0.0, 0.0, 0.0, 0.0, 1.0, 0.0, 0.0, 0.0 };
        this.sp3HybridArray = new double[] { -0.5, 0.0, -0.3535533905932738, 0.3535533905932738, -0.5, 0.0, 0.3535533905932738, 0.3535533905932738, -0.5, 0.0, 0.3535533905932738, -0.3535533905932738, -0.5, 0.0, -0.3535533905932738, -0.3535533905932738, -0.5, 0.0, 0.3535533905932738, 0.3535533905932738, 0.5, 0.0, -0.3535533905932738, 0.3535533905932738, -0.5, 0.0, -0.3535533905932738, -0.3535533905932738, 0.5, 0.0, 0.3535533905932738, -0.3535533905932738 };
        this.spHybridText = new String[] { "2sp (1)", "2sp (2)", "2px", "2py" };
        this.sp2HybridText = new String[] { "2sp2 (1)", "2sp2 (2)", "2sp2 (3)", "2pz" };
        this.sp3HybridText = new String[] { "2sp3 (1)", "2sp3 (2)", "2sp3 (3)", "2sp3 (4)" };
        this.codeLetter = new String[] { "s", "p", "d", "f", "g", "h" };
        this.l1RealText = new String[] { "pz", "px", "py" };
        this.l2RealText = new String[] { "dz2", "dxz", "dyz", "d(x2-y2)", "dxy" };
        this.l3RealText = new String[] { "fz3", "fxz2", "fyz2", "fz(x2-y2)", "fxyz", "fx(x2-3y2)", "fy(3x2-y2)" };
        this.l3CubicRealText = new String[] { "fz3", "fx3", "fy3", "fz(x2-y2)", "fxyz", "fx(z2-y2)", "fy(z2-x2)" };
        this.scaleValue = -1;
        this.applet = applet;
    }
    
    public void init() {
        this.gray2 = new Color(127, 127, 127);
        final int n = 100;
        if (new Double(System.getProperty("java.class.version")) >= 48.0) {
            this.useBufferedImage = true;
        }
        this.setLayout(new AtomViewerLayout());
        (this.cv = new AtomViewerCanvas(this)).addComponentListener(this);
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
        menu2.add(this.lCheckItem = this.getCheckItem("Angular Momentum"));
        menu2.add(this.l2CheckItem = this.getCheckItem("Angular Momentum^2"));
        menu2.add(this.rCheckItem = this.getCheckItem("Radial Distribution"));
        menu2.addSeparator();
        menu2.add(this.colorCheck = this.getCheckItem("Phase as Color"));
        this.colorCheck.setState(true);
        final Menu menu3 = new Menu("Options");
        menuBar.add(menu3);
        this.alwaysNormItem = this.getCheckItem("Always Normalize");
        menu3.add(this.cubicItem = this.getCheckItem("Show Cubic f Orbitals"));
        menu3.add(this.dimensionsItem = this.getCheckItem("Show Dimensions"));
        menu3.add(this.axesItem = this.getCheckItem("Show Axes"));
        this.axesItem.setState(true);
        menu3.add(this.autoZoomItem = this.getCheckItem("Auto Scale"));
        this.autoZoomItem.setState(true);
        menu3.add(this.animatedZoomItem = this.getCheckItem("Animated Scaling"));
        this.animatedZoomItem.setState(true);
        this.setMenuBar(menuBar);
        final Menu menu4 = new Menu("Samples");
        menuBar.add(menu4);
        this.samplesItems = new CheckboxMenuItem[6];
        menu4.add(this.samplesItems[0] = this.getCheckItem("Samples = 9 (fastest)"));
        menu4.add(this.samplesItems[1] = this.getCheckItem("Samples = 15 (default)"));
        menu4.add(this.samplesItems[2] = this.getCheckItem("Samples = 25"));
        menu4.add(this.samplesItems[3] = this.getCheckItem("Samples = 35"));
        menu4.add(this.samplesItems[4] = this.getCheckItem("Samples = 45"));
        menu4.add(this.samplesItems[5] = this.getCheckItem("Samples = 55 (best)"));
        this.samplesItems[1].setState(true);
        (this.viewChooser = new Choice()).add("Real Orbitals (chem.)");
        this.viewChooser.add("Complex Orbitals (phys.)");
        this.viewChooser.add("Real Combinations (n=1-4)");
        this.viewChooser.add("Complex Combos (n=1-4)");
        this.viewChooser.add("Multiple Bases (n=2,l=1)");
        this.viewChooser.add("Multiple Bases (n=3,l=1)");
        this.viewChooser.add("Multiple Bases (n=3,l=2)");
        this.viewChooser.add("Multiple Bases (n=4,l=1)");
        this.viewChooser.add("Multiple Bases (n=4,l=2)");
        this.viewChooser.add("Multiple Bases (n=4,l=3)");
        this.viewChooser.add("Hybrid Bases");
        this.viewChooser.addItemListener(this);
        this.add(this.viewChooser);
        this.nChooser = new Choice();
        for (int i = 1; i <= 16; ++i) {
            this.nChooser.add("n = " + i);
        }
        this.nChooser.addItemListener(this);
        this.add(this.nChooser);
        this.nChooser.select(3);
        (this.lChooser = new Choice()).addItemListener(this);
        this.add(this.lChooser);
        (this.mChooser = new Choice()).addItemListener(this);
        this.add(this.mChooser);
        (this.sliceChooser = new Choice()).add("No Slicing");
        this.sliceChooser.add("Show X Slice");
        this.sliceChooser.add("Show Y Slice");
        this.sliceChooser.add("Show Z Slice");
        this.sliceChooser.addItemListener(this);
        this.add(this.sliceChooser);
        (this.modeChooser = new Choice()).add("Mouse = Adjust View");
        this.modeChooser.add("Mouse = Rotate X");
        this.modeChooser.add("Mouse = Rotate Y");
        this.modeChooser.add("Mouse = Rotate Z");
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
        this.setNValue();
        this.lChooser.select(3);
        this.setLValue();
        this.add(new Label("Simulation Speed", 1));
        this.add(this.speedBar = new Scrollbar(0, 40, 1, 1, 180));
        this.speedBar.addAdjustmentListener(this);
        this.add(new Label("Brightness", 1));
        this.add(this.brightnessBar = new Scrollbar(0, 240, 1, 1, 4000));
        this.brightnessBar.addAdjustmentListener(this);
        this.add(new Label("Image Resolution", 1));
        this.add(this.resolutionBar = new Scrollbar(0, n, 2, 20, 300));
        this.resolutionBar.addAdjustmentListener(this);
        this.add(new Label("Scale", 1));
        this.add(this.scaleBar = new Scrollbar(0, 75, 1, 5, 1620));
        this.scaleBar.addAdjustmentListener(this);
        this.add(new Label("http://www.falstad.com", 1));
        try {
            final String parameter = this.applet.getParameter("PAUSE");
            if (parameter != null) {
                this.pause = Integer.parseInt(parameter);
            }
        }
        catch (Exception ex) {}
        this.phaseColors = new PhaseColor[400];
        for (int j = 0; j != 8; ++j) {
            for (int k = 0; k != 50; ++k) {
                this.phaseColors[j * 50 + k] = this.genPhaseColor(j, Math.atan(k / 50.0));
            }
        }
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
        this.rotate(0.0, -1.5707963267948966);
        this.xpoints = new int[4];
        this.ypoints = new int[4];
        this.setupSimpson();
        this.setupStates();
        this.orbitalChanged();
        this.random = new Random();
        this.reinit();
        this.cv.setBackground(Color.black);
        this.cv.setForeground(Color.white);
        this.resize(580, 500);
        this.handleResize();
        final Dimension size = this.getSize();
        final Dimension screenSize = this.getToolkit().getScreenSize();
        this.setLocation((screenSize.width - size.width) / 2, (screenSize.height - size.height) / 2);
        this.show();
    }
    
    void setupStates() {
        final int n = 16;
        this.stateCount = n * (n + 1) * (2 * n + 1) / 6;
        this.states = new BasisState[this.stateCount];
        int n2 = 1;
        int l = 0;
        int m = 0;
        for (int i = 0; i != this.stateCount; ++i) {
            final BasisState[] states = this.states;
            final int n3 = i;
            final BasisState basisState = new BasisState();
            states[n3] = basisState;
            final BasisState basisState2 = basisState;
            ((State)basisState2).elevel = -1.0 / (2.0 * n2 * n2);
            basisState2.n = n2;
            if ((basisState2.m = m) < (basisState2.l = l)) {
                ++m;
            }
            else if (++l < n2) {
                m = -l;
            }
            else {
                ++n2;
                m = (l = 0);
            }
        }
        this.basisList = new AlternateBasis[17];
        this.basisCount = 0;
        this.realBasis = new AlternateBasis();
        final int n4 = 4;
        final AlternateBasis realBasis = this.realBasis;
        final int altStateCount = n4 * (n4 + 1) * (2 * n4 + 1) / 6;
        realBasis.altStateCount = altStateCount;
        final int n5 = altStateCount;
        this.realBasis.altStates = new DerivedState[n5];
        int n6 = 1;
        int n8;
        int n7 = n8 = 0;
        for (int j = 0; j != n5; ++j) {
            final DerivedState[] altStates = this.realBasis.altStates;
            final int n9 = j;
            final DerivedState derivedState = new DerivedState();
            altStates[n9] = derivedState;
            final DerivedState derivedState2 = derivedState;
            derivedState2.basis = this.realBasis;
            if (n7 == 0) {
                derivedState2.count = 1;
                (derivedState2.bstates = new BasisState[1])[0] = this.getState(n6, n8, 0);
                (derivedState2.coefs = new Complex[1])[0] = new Complex(1.0, 0.0);
            }
            else {
                final int n10 = n7 - 1;
                final int n11 = n10 / 2 + 1;
                derivedState2.count = 2;
                (derivedState2.bstates = new BasisState[2])[0] = this.getState(n6, n8, n11);
                derivedState2.bstates[1] = this.getState(n6, n8, -n11);
                derivedState2.coefs = new Complex[2];
                final double pow = Math.pow(-1.0, n11);
                if ((n10 & 0x1) == 0x0) {
                    derivedState2.coefs[0] = new Complex(pow * 0.7071067811865476, 0.0);
                    derivedState2.coefs[1] = new Complex(0.7071067811865476, 0.0);
                }
                else {
                    derivedState2.coefs[0] = new Complex(0.0, pow * 0.7071067811865476);
                    derivedState2.coefs[1] = new Complex(0.0, -0.7071067811865476);
                }
            }
            switch (n8) {
                case 0: {
                    derivedState2.text = String.valueOf(n6) + "s";
                    break;
                }
                case 1: {
                    derivedState2.text = String.valueOf(n6) + this.l1RealText[n7];
                    break;
                }
                case 2: {
                    derivedState2.text = String.valueOf(n6) + this.l2RealText[n7];
                    break;
                }
                case 3: {
                    derivedState2.text = String.valueOf(n6) + this.l3RealText[n7];
                    break;
                }
            }
            if (n7 < n8 * 2) {
                ++n7;
            }
            else if (++n8 < n6) {
                n7 = 0;
            }
            else {
                ++n6;
                n7 = (n8 = 0);
            }
        }
        this.n2l1xBasis = this.setupLBasis(2, 1, true, this.l1xArray);
        this.n2l1yBasis = this.setupLBasis(2, 1, false, this.l1yArray);
        this.n3l1xBasis = this.setupLBasis(3, 1, true, this.l1xArray);
        this.n3l1yBasis = this.setupLBasis(3, 1, false, this.l1yArray);
        this.n3l2xBasis = this.setupLBasis(3, 2, true, this.l2xArray);
        this.n3l2yBasis = this.setupLBasis(3, 2, false, this.l2yArray);
        this.n4l1xBasis = this.setupLBasis(4, 1, true, this.l1xArray);
        this.n4l1yBasis = this.setupLBasis(4, 1, false, this.l1yArray);
        this.n4l2xBasis = this.setupLBasis(4, 2, true, this.l2xArray);
        this.n4l2yBasis = this.setupLBasis(4, 2, false, this.l2yArray);
        this.n4l3xBasis = this.setupLBasis(4, 3, true, this.l3xArray);
        this.n4l3yBasis = this.setupLBasis(4, 3, false, this.l3yArray);
        this.n4l3CubicBasis = this.setupLBasis(4, 3, false, this.l3CubicArray);
        this.n4l3CubicBasis.n = 0;
        this.spHybridBasis = this.setupHybridBasis(this.spHybridArray, this.spHybridText);
        this.sp2HybridBasis = this.setupHybridBasis(this.sp2HybridArray, this.sp2HybridText);
        this.sp3HybridBasis = this.setupHybridBasis(this.sp3HybridArray, this.sp3HybridText);
    }
    
    AlternateBasis setupLBasis(final int n, final int l, final boolean xAxis, final double[] array) {
        final int n2 = l * 2 + 1;
        final AlternateBasis basis = new AlternateBasis();
        basis.n = n;
        basis.l = l;
        basis.xAxis = xAxis;
        final String s = xAxis ? "mx" : "my";
        basis.altStates = new DerivedState[n2];
        basis.altStateCount = n2;
        for (int i = 0; i != n2; ++i) {
            final DerivedState[] altStates = basis.altStates;
            final int n3 = i;
            final DerivedState derivedState = new DerivedState();
            altStates[n3] = derivedState;
            final DerivedState derivedState2 = derivedState;
            derivedState2.basis = basis;
            derivedState2.count = n2;
            derivedState2.bstates = new BasisState[n2];
            derivedState2.coefs = new Complex[n2];
            derivedState2.m = i - l;
            for (int j = 0; j != n2; ++j) {
                derivedState2.bstates[j] = this.getState(n, l, j - l);
                derivedState2.coefs[j] = new Complex();
            }
            if (array == this.l3CubicArray) {
                derivedState2.text = "4" + this.l3CubicRealText[i];
            }
            else {
                derivedState2.text = "n = " + n + ", l = " + l + ", " + s + " = " + derivedState2.m;
            }
        }
        int n4 = 0;
        for (int k = 0; k != n2; ++k) {
            for (int n5 = 0; n5 != n2; ++n5) {
                basis.altStates[k].coefs[n5].set(array[n4], array[n4 + 1]);
                n4 += 2;
            }
        }
        return basis;
    }
    
    AlternateBasis setupHybridBasis(final double[] array, final String[] array2) {
        final int n = 4;
        final AlternateBasis basis = new AlternateBasis();
        basis.altStates = new DerivedState[n];
        basis.altStateCount = n;
        for (int i = 0; i != n; ++i) {
            final DerivedState[] altStates = basis.altStates;
            final int n2 = i;
            final DerivedState derivedState = new DerivedState();
            altStates[n2] = derivedState;
            final DerivedState derivedState2 = derivedState;
            derivedState2.basis = basis;
            derivedState2.count = n;
            derivedState2.bstates = new BasisState[n];
            derivedState2.coefs = new Complex[n];
            derivedState2.text = array2[i];
            derivedState2.bstates[0] = this.getState(2, 0, 0);
            derivedState2.coefs[0] = new Complex();
            for (int j = 0; j != 3; ++j) {
                derivedState2.bstates[j + 1] = this.getState(2, 1, j - 1);
                derivedState2.coefs[j + 1] = new Complex();
            }
        }
        int n3 = 0;
        for (int k = 0; k != n; ++k) {
            for (int l = 0; l != n; ++l) {
                basis.altStates[k].coefs[l].set(array[n3], array[n3 + 1]);
                n3 += 2;
            }
        }
        return basis;
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
    
    void setupSimpson() {
        this.sampleCount = 15;
        for (int i = 0; i != this.samplesNums.length; ++i) {
            if (this.samplesItems[i].getState()) {
                this.sampleCount = this.samplesNums[i];
            }
        }
        System.out.print("sampleCount = " + this.sampleCount + "\n");
        this.sampleMult = new int[this.sampleCount];
        for (int j = 1; j < this.sampleCount; j += 2) {
            this.sampleMult[j] = 4;
            this.sampleMult[j + 1] = 2;
        }
        this.sampleMult[0] = (this.sampleMult[this.sampleCount - 1] = 1);
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
    
    void setupMenus() {
        switch (this.viewChooser.getSelectedIndex()) {
            case 0:
            case 1: {
                this.nChooser.show();
                this.lChooser.show();
                this.mChooser.show();
                this.modeChooser.hide();
                this.modeChooser.select(0);
                this.blankButton.hide();
                this.normalizeButton.hide();
                this.maximizeButton.hide();
                this.alwaysNormItem.disable();
                break;
            }
            default: {
                this.nChooser.hide();
                this.lChooser.hide();
                this.mChooser.hide();
                this.modeChooser.show();
                this.blankButton.show();
                this.normalizeButton.show();
                this.maximizeButton.show();
                this.alwaysNormItem.enable();
                break;
            }
        }
        if (this.viewChooser.getSelectedIndex() == 0) {
            this.cubicItem.enable();
        }
        else {
            this.cubicItem.disable();
        }
        this.validate();
    }
    
    void createPhasors() {
        final boolean b = false;
        this.textCount = (b ? 1 : 0);
        this.phasorCount = (b ? 1 : 0);
        for (int i = 0; i != this.basisCount; ++i) {
            this.basisList[i].active = false;
        }
        if (this.viewStates == null) {
            return;
        }
        final int n = this.viewStates.height / 4;
        int n2 = 0;
        int y = this.viewStates.y;
        int n3 = 1;
        int n4 = 0;
        int n5 = 0;
        this.textBoxes = new TextBox[10];
        switch (this.viewChooser.getSelectedIndex()) {
            case 2:
            case 3: {
                this.phasorCount = 30;
                this.phasors = new Phasor[this.phasorCount];
                for (int j = 0; j != this.phasorCount; ++j) {
                    final Phasor[] phasors = this.phasors;
                    final int n6 = j;
                    final Phasor phasor = new Phasor(n2, y, n, n);
                    phasors[n6] = phasor;
                    final Phasor phasor2 = phasor;
                    if (this.viewChooser.getSelectedIndex() == 2) {
                        phasor2.state = this.realBasis.altStates[j];
                    }
                    else {
                        phasor2.state = this.states[j];
                    }
                    n2 += n;
                    if (++n5 > n4) {
                        n2 += n;
                        n5 = -(++n4);
                        if (n4 >= n3) {
                            n2 = 0;
                            y += n;
                            ++n3;
                            n5 = (n4 = 0);
                        }
                    }
                }
                break;
            }
            case 4: {
                this.phasorCount = 12;
                this.phasors = new Phasor[this.phasorCount];
                final int basisPhasors = this.createBasisPhasors(n2, y, n, 0, 2, 1);
                this.createText("Lz", n2 + n * 3, y, n);
                final int n7 = y + n;
                final int altPhasors = this.createAltPhasors(n2, n7, n, basisPhasors, this.n2l1xBasis, 3, 0);
                this.createText("Lx", n2 + n * 3, n7, n);
                final int n8 = n7 + n;
                final int altPhasors2 = this.createAltPhasors(n2, n8, n, altPhasors, this.n2l1yBasis, 3, 0);
                this.createText("Ly", n2 + n * 3, n8, n);
                final int n9 = n8 + n;
                this.createAltPhasors(n2, n9, n, altPhasors2, this.realBasis, 3, 2);
                this.createText("Real (pz,px,py)", n2 + n * 3, n9, n);
                break;
            }
            case 5: {
                this.phasorCount = 12;
                this.phasors = new Phasor[this.phasorCount];
                final int basisPhasors2 = this.createBasisPhasors(n2, y, n, 0, 3, 1);
                this.createText("Lz", n2 + n * 3, y, n);
                final int n10 = y + n;
                final int altPhasors3 = this.createAltPhasors(n2, n10, n, basisPhasors2, this.n3l1xBasis, 3, 0);
                this.createText("Lx", n2 + n * 3, n10, n);
                final int n11 = n10 + n;
                final int altPhasors4 = this.createAltPhasors(n2, n11, n, altPhasors3, this.n3l1yBasis, 3, 0);
                this.createText("Ly", n2 + n * 3, n11, n);
                final int n12 = n11 + n;
                this.createAltPhasors(n2, n12, n, altPhasors4, this.realBasis, 3, 6);
                this.createText("Real (pz,px,py)", n2 + n * 3, n12, n);
                break;
            }
            case 6: {
                this.phasorCount = 20;
                this.phasors = new Phasor[this.phasorCount];
                final int basisPhasors3 = this.createBasisPhasors(n2, y, n, 0, 3, 2);
                this.createText("Lz", n2 + n * 5, y, n);
                final int n13 = y + n;
                final int altPhasors5 = this.createAltPhasors(n2, n13, n, basisPhasors3, this.n3l2xBasis, 5, 0);
                this.createText("Lx", n2 + n * 5, n13, n);
                final int n14 = n13 + n;
                final int altPhasors6 = this.createAltPhasors(n2, n14, n, altPhasors5, this.n3l2yBasis, 5, 0);
                this.createText("Ly", n2 + n * 5, n14, n);
                final int n15 = n14 + n;
                this.createAltPhasors(n2, n15, n, altPhasors6, this.realBasis, 5, 9);
                this.createText("Real", n2 + n * 5, n15, n);
                break;
            }
            case 7: {
                this.phasorCount = 12;
                this.phasors = new Phasor[this.phasorCount];
                final int basisPhasors4 = this.createBasisPhasors(n2, y, n, 0, 4, 1);
                this.createText("Lz", n2 + n * 3, y, n);
                final int n16 = y + n;
                final int altPhasors7 = this.createAltPhasors(n2, n16, n, basisPhasors4, this.n4l1xBasis, 3, 0);
                this.createText("Lx", n2 + n * 3, n16, n);
                final int n17 = n16 + n;
                final int altPhasors8 = this.createAltPhasors(n2, n17, n, altPhasors7, this.n4l1yBasis, 3, 0);
                this.createText("Ly", n2 + n * 3, n17, n);
                final int n18 = n17 + n;
                this.createAltPhasors(n2, n18, n, altPhasors8, this.realBasis, 3, 15);
                this.createText("Real (pz,px,py)", n2 + n * 3, n18, n);
                break;
            }
            case 8: {
                this.phasorCount = 20;
                this.phasors = new Phasor[this.phasorCount];
                final int basisPhasors5 = this.createBasisPhasors(n2, y, n, 0, 4, 2);
                this.createText("Lz", n2 + n * 5, y, n);
                final int n19 = y + n;
                final int altPhasors9 = this.createAltPhasors(n2, n19, n, basisPhasors5, this.n4l2xBasis, 5, 0);
                this.createText("Lx", n2 + n * 5, n19, n);
                final int n20 = n19 + n;
                final int altPhasors10 = this.createAltPhasors(n2, n20, n, altPhasors9, this.n4l2yBasis, 5, 0);
                this.createText("Ly", n2 + n * 5, n20, n);
                final int n21 = n20 + n;
                this.createAltPhasors(n2, n21, n, altPhasors10, this.realBasis, 5, 18);
                this.createText("Real", n2 + n * 5, n21, n);
                break;
            }
            case 9: {
                this.phasorCount = 35;
                this.phasors = new Phasor[this.phasorCount];
                final int n22 = this.viewStates.height / 5;
                final int basisPhasors6 = this.createBasisPhasors(n2, y, n22, 0, 4, 3);
                this.createText("Lz", n2 + n22 * 7, y, n22);
                final int n23 = y + n22;
                final int altPhasors11 = this.createAltPhasors(n2, n23, n22, basisPhasors6, this.n4l3xBasis, 7, 0);
                this.createText("Lx", n2 + n22 * 7, n23, n22);
                final int n24 = n23 + n22;
                final int altPhasors12 = this.createAltPhasors(n2, n24, n22, altPhasors11, this.n4l3yBasis, 7, 0);
                this.createText("Ly", n2 + n22 * 7, n24, n22);
                final int n25 = n24 + n22;
                final int altPhasors13 = this.createAltPhasors(n2, n25, n22, altPhasors12, this.realBasis, 7, 23);
                this.createText("Real (General)", n2 + n22 * 7, n25, n22);
                final int n26 = n25 + n22;
                this.createAltPhasors(n2, n26, n22, altPhasors13, this.n4l3CubicBasis, 7, 0);
                this.createText("Real (Cubic)", n2 + n22 * 7, n26, n22);
                break;
            }
            case 10: {
                final int n27 = this.viewStates.height / 5;
                this.phasorCount = 20;
                this.phasors = new Phasor[this.phasorCount];
                final int altPhasors14 = this.createAltPhasors(n2, y, n27, 0, this.spHybridBasis, 4, 0);
                this.createText("sp", n2 + n27 * 4, y, n27);
                final int n28 = y + n27;
                final int altPhasors15 = this.createAltPhasors(n2, n28, n27, altPhasors14, this.sp2HybridBasis, 4, 0);
                this.createText("sp2", n2 + n27 * 4, n28, n27);
                final int n29 = n28 + n27;
                int altPhasors16 = this.createAltPhasors(n2, n29, n27, altPhasors15, this.sp3HybridBasis, 4, 0);
                this.createText("sp3", n2 + n27 * 4, n29, n27);
                final int n30 = n29 + n27;
                this.phasors[altPhasors16] = new Phasor(n2, n30, n27, n27);
                this.phasors[altPhasors16++].state = this.getState(2, 0, 0);
                final int basisPhasors7 = this.createBasisPhasors(n2 + n27, n30, n27, altPhasors16, 2, 1);
                this.createText("Lz", n2 + n27 * 4, n30, n27);
                final int n31 = n30 + n27;
                this.createAltPhasors(n2, n31, n27, basisPhasors7, this.realBasis, 4, 1);
                this.createText("Real (s,pz,px,py)", n2 + n27 * 4, n31, n27);
                break;
            }
        }
        for (int k = 0; k != this.phasorCount; ++k) {
            this.phasors[k].state.setBasisActive();
        }
        for (int l = 0; l != this.basisCount; ++l) {
            if (this.basisList[l].active) {
                this.basisList[l].convertBasisToDerived();
                this.basisList[l].convertDerivedToBasis();
            }
        }
        if (this.viewChooser.getSelectedIndex() == 3) {
            for (int altStateCount = this.realBasis.altStateCount; altStateCount != this.stateCount; ++altStateCount) {
                ((Complex)this.states[altStateCount]).set(0.0);
            }
        }
        this.createOrbitals();
    }
    
    boolean higherStatesPresent() {
        for (int i = this.realBasis.altStateCount; i != this.stateCount; ++i) {
            if (((Complex)this.states[i]).mag > 0.0) {
                return true;
            }
        }
        return false;
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
                this.createOrbitals();
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
    
    int createAltPhasors(int n, final int n2, final int n3, int n4, final AlternateBasis alternateBasis, final int n5, final int n6) {
        for (int i = 0; i != n5; ++i) {
            final Phasor[] phasors = this.phasors;
            final int n7 = n4;
            final Phasor phasor = new Phasor(n, n2, n3, n3);
            phasors[n7] = phasor;
            phasor.state = alternateBasis.altStates[i + n6];
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
        final int n2 = (this.viewStates == null) ? 64 : this.viewStates.height;
        final View view = null;
        this.viewStates = view;
        this.viewL2 = view;
        this.viewL = view;
        this.viewRadial = view;
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
        if (this.l2CheckItem.getState()) {
            this.viewList[viewCount++] = (this.viewL2 = new View());
        }
        if (this.rCheckItem.getState()) {
            this.viewList[viewCount++] = (this.viewRadial = new View());
        }
        if (this.viewChooser.getSelectedIndex() > 1) {
            this.viewList[viewCount++] = (this.viewStates = new View());
        }
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
        final int n = (int)(this.min(this.viewX.width, this.viewX.height) / 3.0);
        this.viewAxes = new Rectangle(this.viewX.x + this.winSize.width - n, this.viewX.y, n, n);
        this.setupMenus();
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
        System.out.print("setResolution " + this.dataSize + " " + this.gridSizeX + " " + this.winSize + "\n");
        this.resadj = 50.0 / this.dataSize;
        this.precomputeAll();
    }
    
    int getN() {
        return this.nChooser.getSelectedIndex() + 1;
    }
    
    int getL() {
        return this.lChooser.getSelectedIndex();
    }
    
    int getM() {
        return this.mChooser.getSelectedIndex() - this.getL();
    }
    
    void setNValue() {
        final int n = this.nChooser.getSelectedIndex() + 1;
        final int selectedIndex = this.lChooser.getSelectedIndex();
        this.lChooser.removeAll();
        for (int i = 0; i < n; ++i) {
            this.lChooser.add("l = " + i + ((i < 6) ? (" (" + this.codeLetter[i] + ")") : ""));
        }
        if (selectedIndex < n && selectedIndex >= 0) {
            this.lChooser.select(selectedIndex);
        }
        this.setLValue();
    }
    
    void setLValue() {
        final int l = this.getL();
        this.mChooser.removeAll();
        if (this.viewChooser.getSelectedIndex() == 0) {
            if (l == 0) {
                this.mChooser.add(String.valueOf(this.getN()) + "s");
            }
            else if (l == 1) {
                for (int i = 0; i != 3; ++i) {
                    this.mChooser.add(String.valueOf(this.getN()) + this.l1RealText[i]);
                }
            }
            else if (l == 2) {
                for (int j = 0; j != 5; ++j) {
                    this.mChooser.add(String.valueOf(this.getN()) + this.l2RealText[j]);
                }
            }
            else if (l == 3 && !this.cubicItem.getState()) {
                for (int k = 0; k != 7; ++k) {
                    this.mChooser.add(String.valueOf(this.getN()) + this.l3RealText[k]);
                }
            }
            else if (l == 3 && this.cubicItem.getState()) {
                for (int n = 0; n != 7; ++n) {
                    this.mChooser.add(String.valueOf(this.getN()) + this.l3CubicRealText[n]);
                }
            }
            else {
                this.mChooser.add("m = 0");
                for (int n2 = 1; n2 <= l; ++n2) {
                    this.mChooser.add("m = +-" + n2 + " (+)");
                    this.mChooser.add("m = +-" + n2 + " (-)");
                }
            }
        }
        else {
            for (int n3 = -l; n3 <= l; ++n3) {
                this.mChooser.add("m = " + n3);
            }
            this.mChooser.select(l);
        }
        this.validate();
    }
    
    void computeView(final double n) {
        final boolean state = this.colorCheck.getState();
        for (int i = 0; i != this.orbCount; ++i) {
            this.orbitals[i].setupFrame(n);
        }
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
        final int selectedIndex = this.sliceChooser.getSelectedIndex();
        double n9 = 0.0;
        for (int j = 0; j != this.orbCount; ++j) {
            final double boundRadius = this.orbitals[j].getBoundRadius(this.colorMult);
            if (boundRadius > n9) {
                n9 = boundRadius;
            }
        }
        final double n10 = n9 * n9;
        for (int k = 0; k != this.gridSizeX; ++k) {
            for (int l = 0; l != this.gridSizeY; ++l) {
                final double n11 = (2 * k / this.gridSizeX - 1.0) * n7;
                final double n12 = -(2 * l / this.gridSizeY - 1.0) * n8;
                final double n13 = rotmatrix[2] * AtomViewerFrame.viewDistance;
                final double n14 = rotmatrix[5] * AtomViewerFrame.viewDistance;
                final double n15 = rotmatrix[8] * AtomViewerFrame.viewDistance;
                final double n16 = rotmatrix[0] * n11 + rotmatrix[1] * n12 - rotmatrix[2];
                final double n17 = rotmatrix[3] * n11 + rotmatrix[4] * n12 - rotmatrix[5];
                final double n18 = rotmatrix[6] * n11 + rotmatrix[7] * n12 - rotmatrix[8];
                final double sqrt = Math.sqrt(n11 * n11 + n12 * n12 + 1.0);
                float n19 = 0.0f;
                float n20 = 0.0f;
                float n21 = 0.0f;
                final double n22 = n16 * n16 + n17 * n17 + n18 * n18;
                final double n23 = 2.0 * (n16 * n13 + n17 * n14 + n18 * n15);
                final double n24 = n23 * n23 - 4.0 * n22 * (n13 * n13 + n14 * n14 + n15 * n15 - n10);
                if (n24 < 0.0) {
                    this.fillSquare(k, l, 0.0f, 0.0f, 0.0f);
                }
                else {
                    final double sqrt2 = Math.sqrt(n24);
                    double n25 = (-n23 - sqrt2) / (2.0 * n22);
                    double n26 = (-n23 + sqrt2) / (2.0 * n22);
                    if (selectedIndex != 0) {
                        double n27 = -100.0;
                        switch (selectedIndex) {
                            case 1: {
                                n27 = (this.sliceval - n13) / n16;
                                break;
                            }
                            case 2: {
                                n27 = (this.sliceval - n14) / n17;
                                break;
                            }
                            case 3: {
                                n27 = (this.sliceval - n15) / n18;
                                break;
                            }
                        }
                        if (n27 < n25 || n27 > n26) {
                            this.fillSquare(k, l, 0.0f, 0.0f, 0.0f);
                            continue;
                        }
                        n26 = (n25 = n27);
                    }
                    final double n28 = (n26 - n25) / (this.sampleCount - 1);
                    double n29 = (n26 - n25) * sqrt;
                    int n30 = this.sampleCount - 1;
                    int n31 = 1;
                    double n32 = (n13 + n16 * n25) * n4;
                    double n33 = (n14 + n17 * n25) * n5;
                    double n34 = (n15 + n18 * n25) * n6;
                    if (selectedIndex != 0) {
                        n30 = 1;
                        n31 = 0;
                        n29 = 2.0;
                        if (n32 > n4 || n33 > n5 || n34 > n6 || n32 < -n4 || n33 < -n5 || n34 < -n6) {
                            this.fillSquare(k, l, 0.0f, 0.0f, 0.0f);
                            continue;
                        }
                    }
                    final double n35 = n16 * (n28 * n4);
                    final double n36 = n17 * (n28 * n5);
                    final double n37 = n18 * (n28 * n6);
                    final int n38 = this.dataSize / 2;
                    while (n31 < n30) {
                        final double sqrt3 = Math.sqrt(n32 * n32 + n33 * n33 + n34 * n34);
                        final double n39 = n34 / sqrt3;
                        final int n40 = (int)sqrt3;
                        final int n41 = (int)(n39 * n38 + n38);
                        float n42 = 0.0f;
                        float n43 = 0.0f;
                        this.calcPhiComponent(n32, n33);
                        for (int n44 = 0; n44 != this.orbCount; ++n44) {
                            this.orbitals[n44].computePoint(n40, n41);
                            n42 += this.funcr;
                            n43 += this.funci;
                        }
                        if (state) {
                            final double n45 = n42 * n42 + n43 * n43;
                            if (n45 > 1.0) {
                                System.out.print("fv = " + n45 + "\n");
                            }
                            final double n46 = n45 * this.sampleMult[n31];
                            final PhaseColor phaseColor = this.getPhaseColor(n42, n43);
                            n19 += (float)(phaseColor.r * n46);
                            n20 += (float)(phaseColor.g * n46);
                            n21 += (float)(phaseColor.b * n46);
                        }
                        else {
                            n20 = (n19 = (n21 += (n42 * n42 + n43 * n43) * this.sampleMult[n31]));
                        }
                        n32 += n35;
                        n33 += n36;
                        n34 += n37;
                        ++n31;
                    }
                    this.fillSquare(k, l, (float)(n19 * (n29 / n31)), (float)(n20 * (n29 / n31)), (float)(n21 * (n29 / n31)));
                }
            }
        }
    }
    
    void fillSquare(final int n, final int n2, float n3, float n4, float n5) {
        final int width = this.viewX.width;
        final int height = this.viewX.height;
        final int n6 = n * width / this.gridSizeX;
        final int n7 = n2 * height / this.gridSizeY;
        final int n8 = (n + 1) * width / this.gridSizeX;
        final int n9 = (n2 + 1) * height / this.gridSizeY;
        n3 *= (float)this.colorMult;
        n4 *= (float)this.colorMult;
        n5 *= (float)this.colorMult;
        if (n3 == 0.0f && n4 == 0.0f && n5 == 0.0f) {
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
            n3 /= (float)n11;
            n4 /= (float)n11;
            n5 /= (float)n11;
        }
        final int n12 = -16777216 + ((int)n3 << 16) | (int)n4 << 8 | (int)n5;
        final int n13 = n9 * this.viewX.width;
        for (int k = n6; k < n8; ++k) {
            for (int l = n7 * this.viewX.width; l < n13; l += this.viewX.width) {
                this.pixels[k + l] = n12;
            }
        }
    }
    
    PhaseColor getPhaseColor(final double n, final double n2) {
        if (n == 0.0 && n2 == 0.0) {
            return this.phaseColors[0];
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
                    n3 = 50;
                    n4 = 1.0 - n / n2;
                }
            }
            else if (-n <= n2) {
                n3 = 100;
                n4 = -n / n2;
            }
            else {
                n3 = 150;
                n4 = 1.0 + n2 / n;
            }
        }
        else if (n <= 0.0) {
            if (n2 >= n) {
                n3 = 200;
                n4 = n2 / n;
            }
            else {
                n3 = 250;
                n4 = 1.0 - n / n2;
            }
        }
        else if (-n2 >= n) {
            n3 = 300;
            n4 = -n / n2;
        }
        else {
            n3 = 350;
            n4 = 1.0 + n2 / n;
        }
        return this.phaseColors[n3 + (int)(n4 * 49.0)];
    }
    
    void calcPhiComponent(final double n, final double n2) {
        if (n == 0.0 && n2 == 0.0) {
            this.phiIndex = 0;
            return;
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
        this.phiIndex = n3 * (this.dataSize + 1) + (int)(n4 * this.dataSize);
    }
    
    void setScale() {
        if (this.manualScale || !this.autoZoomItem.getState()) {
            return;
        }
        double n = 0.0;
        for (int i = 0; i != this.orbCount; ++i) {
            final double scaleRadius = this.orbitals[i].getScaleRadius();
            if (scaleRadius > n) {
                n = scaleRadius;
            }
        }
        final int n2 = (int)(n * 3.15);
        final int value = this.scaleBar.getValue();
        if (value != n2) {
            int n3 = n2 - value;
            if (n3 < -5 || n3 > 5) {
                n3 /= 3;
                if (n3 < -50) {
                    n3 = -50;
                }
                if (n3 > 50) {
                    n3 = 50;
                }
            }
            final double n4 = n3 * this.frameLen / 60.0;
            int n5 = (int)n4;
            if (n5 == 0) {
                n5 = ((n4 > 0.0) ? 1 : -1);
            }
            int value2 = value + n5;
            if (value2 > n2 && n5 > 0) {
                value2 = n2;
            }
            if (value2 < n2 && n5 < 0) {
                value2 = n2;
            }
            if (!this.animatedZoomItem.getState()) {
                value2 = n2;
            }
            this.scaleBar.setValue(value2);
            this.precomputeAll();
        }
    }
    
    void precomputeAll() {
        for (int i = 0; i != this.orbCount; ++i) {
            this.orbitals[i].precompute();
        }
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
    
    public void updateAtomViewer(final Graphics graphics) {
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
            final long currentTimeMillis = System.currentTimeMillis();
            if (this.lastTime != 0L) {
                final int frameLen = (int)(currentTimeMillis - this.lastTime);
                this.frameLen = frameLen;
                n = Math.exp(this.speedBar.getValue() * 0.04 - 9.0) * frameLen;
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
        for (int i = 0; i != this.stateCount; ++i) {
            final BasisState basisState = this.states[i];
            if (((Complex)basisState).mag < 0.01) {
                ((Complex)basisState).set(0.0);
            }
            else {
                if (n != 0.0) {
                    b = false;
                    ((Complex)basisState).rotate(-(((State)basisState).elevel + 0.55) * n);
                }
                n2 += ((Complex)basisState).magSquared();
            }
        }
        double brightness = 1.0 / n2;
        if (n2 == 0.0) {
            brightness = 0.0;
        }
        final double sqrt = Math.sqrt(brightness);
        final AlternateBasis alternateBasis = this.changingDerivedStates ? ((DerivedState)this.selectedState).basis : null;
        for (int j = 0; j != this.basisCount; ++j) {
            final AlternateBasis alternateBasis2 = this.basisList[j];
            if (alternateBasis2 != alternateBasis && alternateBasis2.active) {
                alternateBasis2.convertBasisToDerived();
            }
        }
        this.setScale();
        this.setBrightness(brightness);
        final boolean b2 = this.sliceChooser.getSelectedIndex() != 0;
        this.zoom = (b2 ? 8.0 : 16.55);
        this.colorMult = Math.exp(this.brightnessBar.getValue() / 100.0);
        this.computeView(sqrt);
        for (int k = 1; k != this.viewCount; ++k) {
            graphics2.setColor((k == this.selectedPaneHandle) ? Color.yellow : Color.gray);
            graphics2.drawLine(0, this.viewList[k].paneY, this.winSize.width, this.viewList[k].paneY);
        }
        if (this.viewPotential != null) {
            final double n3 = this.viewPotential.height * 1.9;
            graphics2.setColor(Color.darkGray);
            for (int l = 1; l != 16; ++l) {
                final int n4 = this.viewPotential.y - (int)(n3 * (-1.0 / (2.0 * l * l)));
                graphics2.drawLine(0, n4, this.winSize.width, n4);
            }
            final double scaler = this.getScaler();
            graphics2.setColor(Color.white);
            int n5 = -1;
            int n6 = -1;
            final int n7 = this.viewPotential.y + this.viewPotential.height - 1;
            for (int n8 = 0; n8 != this.winSize.width; ++n8) {
                double n9 = (n8 - this.winSize.width / 2) * scaler;
                if (n9 < 0.0) {
                    n9 = -n9;
                }
                if (n9 < 0.001) {
                    n9 = 0.001;
                }
                final int n10 = this.viewPotential.y - (int)(n3 * (-1.0 / n9));
                if (n10 > n7) {
                    if (n5 != -1) {
                        graphics2.drawLine(n5, n6, n5, n7);
                        n5 = -1;
                    }
                }
                else if (n5 == -1 && n8 > 0) {
                    graphics2.drawLine(n8, n7, n8, n10);
                    n5 = n8;
                    n6 = n10;
                }
                else {
                    if (n5 != -1) {
                        graphics2.drawLine(n5, n6, n8, n10);
                    }
                    n5 = n8;
                    n6 = n10;
                }
            }
            if (n2 != 0.0) {
                double n11 = 0.0;
                for (int n12 = 0; n12 != this.stateCount; ++n12) {
                    final BasisState basisState2 = this.states[n12];
                    n11 += ((Complex)basisState2).magSquared() * brightness * ((State)basisState2).elevel;
                }
                final int n13 = this.viewPotential.y - (int)(n3 * n11);
                graphics2.setColor(Color.red);
                graphics2.drawLine(0, n13, this.winSize.width, n13);
            }
            if (this.selectedState != null && !this.dragging) {
                graphics2.setColor(Color.yellow);
                final int n14 = this.viewPotential.y - (int)(n3 * this.selectedState.elevel);
                graphics2.drawLine(0, n14, this.winSize.width, n14);
            }
        }
        if (this.viewL != null) {
            final int n15 = 3;
            final int n16 = 3;
            final int n17 = (n15 * 2 + 1) * n16;
            final double[] array = new double[n17];
            if (!this.higherStatesPresent()) {
                this.calcLxy(array, n17, n15, n16, true, false);
                this.drawFunction(graphics2, this.viewL, 0, array, n17, n16, false);
                this.calcLxy(array, n17, n15, n16, false, false);
                this.drawFunction(graphics2, this.viewL, 1, array, n17, n16, false);
            }
            this.calcLz(array, n17, n15, n16, false);
            this.drawFunction(graphics2, this.viewL, 2, array, n17, n16, false);
        }
        if (this.viewL2 != null) {
            final int n18 = 3;
            final int n19 = (n18 * 2 + 1) * 3;
            final double[] array2 = new double[n19];
            final int n20 = 2;
            if (!this.higherStatesPresent()) {
                this.calcLxy(array2, n19, n18, n20, true, true);
                this.drawFunction(graphics2, this.viewL2, 0, array2, n19, n20, true);
                this.calcLxy(array2, n19, n18, n20, false, true);
                this.drawFunction(graphics2, this.viewL2, 1, array2, n19, n20, true);
            }
            this.calcLz(array2, n19, n18, n20, true);
            this.drawFunction(graphics2, this.viewL2, 2, array2, n19, n20, true);
        }
        if (this.viewRadial != null && this.orbCount == 1) {
            final Orbital orbital = this.orbitals[0];
            final int n21 = orbital.n;
            final int m = orbital.l;
            final double radialNorm = orbital.radialNorm(n21, m);
            final int n22 = this.viewRadial.width * 2;
            final double[] array3 = new double[n22];
            final double n23 = orbital.getScaleRadius() * 3.0;
            int n24 = n22;
            double n25 = -1.0;
            for (int n26 = 0; n26 != n22; ++n26) {
                final double n27 = n26 * n23 / n22 + 1.0E-8;
                final double n28 = 2.0 * n27 / n21;
                final double n29 = this.hypser(m + 1 - n21, 2 * m + 2, n28) * (Math.pow(n28, m) * radialNorm) * Math.exp(-n28 / 2.0) * radialNorm;
                array3[n26] = n29 * n29 * n27 * n27;
                if (array3[n26] > n25) {
                    n25 = array3[n26];
                }
                if (array3[n26] > n25 * 0.01) {
                    n24 = n26;
                }
            }
            this.drawRadialFunction(graphics2, array3, n24, n23 * n24 / n22);
        }
        if (this.imageSource != null) {
            this.imageSource.newPixels();
        }
        graphics2.drawImage(this.memimage, this.viewX.x, this.viewX.y, null);
        graphics2.setColor(Color.white);
        if (b2) {
            this.drawCube(graphics2, false);
        }
        if (this.axesItem.getState()) {
            this.drawAxes(graphics2);
        }
        for (int n30 = 0; n30 != this.textCount; ++n30) {
            final TextBox textBox = this.textBoxes[n30];
            graphics2.drawString(textBox.text, textBox.x, textBox.y + (textBox.height + this.fontMetrics.getAscent() - this.fontMetrics.getDescent()) / 2);
        }
        graphics2.setColor(Color.yellow);
        if (this.selectedState != null) {
            this.centerString(graphics2, this.selectedState.getText(), this.viewX.y + this.viewX.height - 5);
        }
        else if (this.dimensionsItem.getState()) {
            this.centerString(graphics2, "Screen width = " + (int)(this.winSize.width * this.getScaler() * 52.9463) + " pm", this.viewX.y + this.viewX.height - 5);
        }
        if (this.mouseDown) {
            final double n31 = 0.0;
            this.lastYRot = n31;
            this.lastXRot = n31;
        }
        else if (this.lastXRot != 0.0 || this.lastYRot != 0.0) {
            final double n32 = this.frameLen / 20.0;
            this.rotate(this.lastXRot * n32, this.lastYRot * n32);
            b = false;
        }
        if (this.viewStates != null) {
            this.drawPhasors(graphics2, this.viewStates);
        }
        graphics.drawImage(this.dbimage, 0, 0, this);
        if (!b) {
            this.cv.repaint(this.pause);
        }
    }
    
    double getScaler() {
        double n = this.viewX.width * this.zoom / 2.0;
        final double n2 = this.viewX.height * this.zoom / 2.0;
        final double n3 = this.viewX.width / this.viewX.height;
        if (n3 >= 1.0) {
            n /= n3;
        }
        return 1.0 / (2.0 * n / AtomViewerFrame.viewDistance / (50.0 * (this.scaleBar.getValue() / 50.0)));
    }
    
    public void centerString(final Graphics graphics, final String s, final int n) {
        graphics.drawString(s, (this.winSize.width - this.fontMetrics.stringWidth(s)) / 2, n);
    }
    
    boolean visibleFace(final int n, final int n2, final int n3) {
        return (n - AtomViewerFrame.viewDistance * this.rotmatrix[2]) * n + (n2 - AtomViewerFrame.viewDistance * this.rotmatrix[5]) * n2 + (n3 - AtomViewerFrame.viewDistance * this.rotmatrix[8]) * n3 < 0.0;
    }
    
    void drawPhasors(final Graphics graphics, final View view) {
        for (int i = 0; i != this.phasorCount; ++i) {
            final Phasor phasor = this.phasors[i];
            final State state = phasor.state;
            final int width = phasor.width;
            final int n = width / 2;
            final int n2 = phasor.x + n;
            final int n3 = phasor.y + n;
            graphics.setColor((this.selectedState == state) ? Color.yellow : ((((Complex)state).mag == 0.0) ? this.gray2 : Color.white));
            graphics.drawOval(n2 - n, n3 - n, width, width);
            final int n4 = (int)(((Complex)state).re * n);
            final int n5 = (int)(-((Complex)state).im * n);
            graphics.drawLine(n2, n3, n2 + n4, n3 + n5);
            graphics.drawLine(n2 + n4 - 1, n3 + n5, n2 + n4 + 1, n3 + n5);
            graphics.drawLine(n2 + n4, n3 + n5 - 1, n2 + n4, n3 + n5 + 1);
        }
    }
    
    void drawFunction(final Graphics graphics, final View view, final int n, final double[] array, final int n2, final int n3, final boolean b) {
        double n4 = 0.0;
        double n5 = 0.0;
        double n6 = 0.0;
        double n7 = 0.0;
        final int n8 = this.winSize.width / 3;
        final int n9 = n8 * 4 / 5;
        final int n11;
        final int n10 = (n11 = (b ? (n9 / (n2 - 1)) : (n9 * (n2 / 2) / (n2 - 1)))) + n8 * n;
        for (int i = 0; i != n2; ++i) {
            final int n12 = n9 * i / (n2 - 1);
            final double n13 = array[i];
            final double n14 = n13 * n13;
            if (n14 > n6) {
                n6 = n14;
            }
            final int n15 = n12 - n11;
            n4 += n14 * n15;
            n5 += n14 * n15 * n15;
            n7 += n14;
        }
        final int n16 = n10;
        final double n17 = n4 / n7;
        final double n18 = n5 / n7;
        final double sqrt = Math.sqrt(n6);
        Math.sqrt(n18 - n17 * n17);
        int n19 = 0;
        view.scale = 1.0 / sqrt;
        if (view.scale > 1.0E8) {
            view.scale = 1.0E8;
        }
        graphics.setColor(Color.gray);
        graphics.drawLine(n10, view.y, n10, view.y + view.height);
        final double n20 = 0.9 * view.height;
        final int n21 = view.y + view.height / 2 + (int)n20 / 2;
        final double n22 = n20 * view.scale;
        graphics.setColor(Color.white);
        int n23 = -1;
        for (int j = 0; j != n2; ++j) {
            final int n24 = n9 * j / (n2 - 1) + n8 * n;
            final int n25 = n21 - (int)(n22 * array[j]);
            if (j % n3 == 1) {
                graphics.setColor(Color.gray);
                graphics.drawLine(n24, n21, n24, n21 + 4);
                graphics.setColor(Color.white);
            }
            if (n23 != -1) {
                graphics.drawLine(n23, n19, n24, n25);
            }
            n23 = n24;
            n19 = n25;
        }
        if (n6 > 0.0) {
            final double n26 = n17 + (n16 + 0.5);
            graphics.setColor(Color.red);
            graphics.drawLine((int)n26, view.y, (int)n26, view.y + view.height);
        }
    }
    
    void drawRadialFunction(final Graphics graphics, final double[] array, final int n, final double n2) {
        final View viewRadial = this.viewRadial;
        double n3 = 0.0;
        final int n4 = this.winSize.width / 2;
        for (int i = 0; i != n; ++i) {
            if (array[i] > n3) {
                n3 = array[i];
            }
        }
        int n5 = 0;
        viewRadial.scale = 1.0 / n3;
        final double n6 = 0.9 * viewRadial.height;
        final int n7 = viewRadial.y + viewRadial.height / 2 + (int)n6 / 2;
        final double n8 = n6 * viewRadial.scale;
        graphics.setColor(Color.white);
        int n9 = -1;
        final int n10 = n / 2;
        int n11 = 0;
        for (int j = 0; j != n; ++j) {
            final int n12 = n4 + n4 * (j - n10) / n10;
            final int n13 = n7 - (int)(n8 * array[j]);
            if (n2 * j / n >= n11) {
                graphics.setColor(Color.gray);
                graphics.drawLine(n12, n7, n12, n7 + 4);
                graphics.setColor(Color.white);
                ++n11;
            }
            if (n9 != -1) {
                graphics.drawLine(n9, n5, n12, n13);
            }
            n9 = n12;
            n5 = n13;
        }
    }
    
    void drawCube(final Graphics graphics, final boolean b) {
        final int selectedIndex = this.sliceChooser.getSelectedIndex();
        int sliceFaceCount = 0;
        for (int i = 0; i != 6; ++i) {
            final int n = (i == 0) ? -1 : ((i == 1) ? 1 : 0);
            final int n2 = (i == 2) ? -1 : ((i == 3) ? 1 : 0);
            final int n3 = (i == 4) ? -1 : ((i == 5) ? 1 : 0);
            if (b || this.visibleFace(n, n2, n3)) {
                final double[] array = new double[3];
                for (int j = 0; j != 4; ++j) {
                    this.computeFace(i, j, array);
                    this.map3d(array[0], array[1], array[2], this.xpoints, this.ypoints, j, this.viewX);
                }
                graphics.setColor(Color.gray);
                graphics.drawPolygon(this.xpoints, this.ypoints, 4);
                if (selectedIndex != 0 && i / 2 != selectedIndex - 1) {
                    if (this.selectedSlice) {
                        graphics.setColor(Color.yellow);
                    }
                    this.computeFace(i, 0, array);
                    array[selectedIndex - 1] = this.sliceval;
                    this.map3d(array[0], array[1], array[2], this.slicerPoints[0], this.slicerPoints[1], sliceFaceCount, this.viewX);
                    this.computeFace(i, 2, array);
                    array[selectedIndex - 1] = this.sliceval;
                    this.map3d(array[0], array[1], array[2], this.slicerPoints[0], this.slicerPoints[1], sliceFaceCount + 1, this.viewX);
                    graphics.drawLine(this.slicerPoints[0][sliceFaceCount], this.slicerPoints[1][sliceFaceCount], this.slicerPoints[0][sliceFaceCount + 1], this.slicerPoints[1][sliceFaceCount + 1]);
                    this.sliceFaces[sliceFaceCount / 2][0] = n;
                    this.sliceFaces[sliceFaceCount / 2][1] = n2;
                    this.sliceFaces[sliceFaceCount / 2][2] = n3;
                    sliceFaceCount += 2;
                }
            }
        }
        this.sliceFaceCount = sliceFaceCount;
    }
    
    void computeFace(final int n, int n2, final double[] array) {
        final int n3 = n >> 1;
        array[n3] = (((n & 0x1) == 0x0) ? -1 : 1);
        for (int i = 0; i != 3; ++i) {
            if (i != n3) {
                array[i] = (((n2 >> 1 ^ (n2 & 0x1)) == 0x0) ? -1 : 1);
                n2 >>= 1;
            }
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
        final double n7 = AtomViewerFrame.viewDistance - (n * rotmatrix[2] + n2 * rotmatrix[5] + n3 * rotmatrix[8]);
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
        final double n8 = AtomViewerFrame.viewDistance * rotmatrix[2];
        final double n9 = AtomViewerFrame.viewDistance * rotmatrix[5];
        final double n10 = AtomViewerFrame.viewDistance * rotmatrix[8];
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
    
    public void adjustmentValueChanged(final AdjustmentEvent adjustmentEvent) {
        System.out.print(String.valueOf(((Scrollbar)adjustmentEvent.getSource()).getValue()) + "\n");
        if (adjustmentEvent.getSource() == this.scaleBar) {
            if (this.scaleBar.getValue() == this.scaleValue) {
                return;
            }
            this.scaleValue = this.scaleBar.getValue();
            this.precomputeAll();
            this.manualScale = true;
        }
        if (adjustmentEvent.getSource() == this.brightnessBar) {
            this.userBrightMult = Math.exp(this.brightnessBar.getValue() / 100.0) / this.bestBrightness;
        }
        if (adjustmentEvent.getSource() == this.resolutionBar) {
            this.setResolution();
        }
        this.setupSimpson();
        this.cv.repaint(this.pause);
    }
    
    public void mouseDragged(final MouseEvent mouseEvent) {
        this.dragging = true;
        this.changingDerivedStates = false;
        this.edit(mouseEvent);
        this.dragX = mouseEvent.getX();
        this.dragY = mouseEvent.getY();
    }
    
    boolean csInRange(final int n, final int n2, final int n3) {
        if (n2 < n3) {
            return n >= n2 - 5 && n <= n3 + 5;
        }
        return n >= n3 - 5 && n <= n2 + 5;
    }
    
    void checkSlice(final int n, final int n2) {
        if (this.sliceChooser.getSelectedIndex() == 0) {
            this.selectedSlice = false;
            return;
        }
        this.selectedSlice = false;
        for (int i = 0; i != this.sliceFaceCount; i += 2) {
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
                        return;
                    }
                }
            }
        }
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
            this.checkSlice(mouseEvent.getX(), mouseEvent.getY());
        }
        else if (this.viewPotential.contains(x, y)) {
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
        this.selectedState.convertBasisToDerived();
        ((Complex)this.selectedState).set(1.0);
        this.selectedState.convertDerivedToBasis();
        this.createOrbitals();
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
        this.mouseDown = true;
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
        this.mouseDown = false;
        if (this.dragging) {
            this.cv.repaint();
        }
        final boolean b = false;
        this.changingDerivedStates = b;
        this.dragging = b;
    }
    
    public void itemStateChanged(final ItemEvent itemEvent) {
        if (itemEvent.getItemSelectable() == this.cubicItem) {
            this.setLValue();
            this.setupDisplay();
            this.orbitalChanged();
            this.cv.repaint(this.pause);
            return;
        }
        if (itemEvent.getItemSelectable() instanceof CheckboxMenuItem) {
            int n;
            for (n = 0; n != this.samplesNums.length && this.samplesItems[n] != itemEvent.getItemSelectable(); ++n) {}
            if (n != this.samplesNums.length) {
                for (int i = 0; i != this.samplesNums.length; ++i) {
                    this.samplesItems[i].setState(n == i);
                }
                this.setupSimpson();
            }
            this.setupDisplay();
            this.cv.repaint(this.pause);
            return;
        }
        if (itemEvent.getItemSelectable() == this.nChooser) {
            this.setNValue();
            this.orbitalChanged();
        }
        else if (itemEvent.getItemSelectable() == this.lChooser) {
            this.setLValue();
            this.orbitalChanged();
        }
        else if (itemEvent.getItemSelectable() == this.mChooser) {
            this.orbitalChanged();
        }
        else if (itemEvent.getItemSelectable() == this.viewChooser) {
            this.setLValue();
            this.orbitalChanged();
            this.setupDisplay();
            this.setInitialOrbital();
        }
        else if (itemEvent.getItemSelectable() == this.cubicItem) {
            this.setLValue();
            this.orbitalChanged();
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
        int selectedIndex = this.modeChooser.getSelectedIndex();
        if (this.selectedSlice) {
            selectedIndex = 5;
        }
        if (selectedIndex == 0) {
            this.rotate(this.lastXRot = (this.dragX - n) / 40.0, this.lastYRot = -(this.dragY - n2) / 40.0);
            final double sqrt = Math.sqrt(this.lastXRot * this.lastXRot + this.lastYRot * this.lastYRot);
            if (sqrt > 0.06) {
                final double n3 = sqrt / 0.06;
                this.lastXRot /= n3;
                this.lastYRot /= n3;
            }
            this.cv.repaint(this.pause);
            return;
        }
        if (selectedIndex == 1) {
            this.rotateXY((this.dragX - n + this.dragY - n2) / 40.0, true);
            return;
        }
        if (selectedIndex == 2) {
            this.rotateXY((this.dragX - n + this.dragY - n2) / 40.0, false);
            return;
        }
        if (selectedIndex == 3) {
            this.rotateZ((this.dragX - n + this.dragY - n2) / 40.0);
            return;
        }
        if (selectedIndex == 5) {
            final double[] array = new double[3];
            this.unmap3d(array, n, n2, this.sliceFace, this.sliceFace);
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
        if (this.selectedState instanceof DerivedState) {
            this.selectedState.convertDerivedToBasis();
            this.changingDerivedStates = true;
        }
        this.cv.repaint(this.pause);
        this.createOrbitals();
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
        this.createOrbitals();
    }
    
    void calcLxy(final double[] array, final int n, final int n2, final int n3, final boolean b, final boolean b2) {
        int n4 = n / 2;
        for (int i = 0; i != n; ++i) {
            array[i] = 0.0;
        }
        if (b2) {
            n4 = 1;
        }
        for (int j = 0; j != this.basisCount; ++j) {
            final AlternateBasis alternateBasis = this.basisList[j];
            if (alternateBasis.n != 0) {
                if (alternateBasis.xAxis == b) {
                    alternateBasis.convertBasisToDerived();
                    for (int k = 0; k != alternateBasis.altStateCount; ++k) {
                        final DerivedState derivedState = alternateBasis.altStates[k];
                        if (b2) {
                            final int n5 = n4 + derivedState.m * derivedState.m * n3;
                            array[n5] += ((Complex)derivedState).magSquared();
                        }
                        else {
                            final int n6 = n4 + derivedState.m * n3;
                            array[n6] += ((Complex)derivedState).magSquared();
                        }
                    }
                }
            }
        }
        for (int l = 0; l != this.stateCount; ++l) {
            if (this.states[l].l == 0) {
                final int n7 = n4;
                array[n7] += ((Complex)this.states[l]).magSquared();
            }
        }
        for (int n8 = 0; n8 != n; ++n8) {
            array[n8] = Math.sqrt(array[n8]);
        }
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
    
    void rotateXY(final double n, final boolean b) {
        for (int i = 0; i != this.basisCount; ++i) {
            final AlternateBasis alternateBasis = this.basisList[i];
            if (alternateBasis.n != 0) {
                if (alternateBasis.xAxis == b) {
                    alternateBasis.convertBasisToDerived();
                    for (int j = 0; j != alternateBasis.altStateCount; ++j) {
                        final DerivedState derivedState = alternateBasis.altStates[j];
                        ((Complex)derivedState).rotate(n * derivedState.m);
                    }
                }
            }
        }
        for (int k = 0; k != this.stateCount; ++k) {
            if (this.states[k].l > 0) {
                ((Complex)this.states[k]).set(0.0);
            }
        }
        for (int l = 0; l != this.basisCount; ++l) {
            final AlternateBasis alternateBasis2 = this.basisList[l];
            if (alternateBasis2.n != 0) {
                if (alternateBasis2.xAxis == b) {
                    alternateBasis2.convertDerivedToBasis(false);
                }
            }
        }
        this.createOrbitals();
        this.cv.repaint(this.pause);
    }
    
    void rotateZ(final double n) {
        for (int i = 0; i != this.stateCount; ++i) {
            final BasisState basisState = this.states[i];
            ((Complex)basisState).rotate(n * basisState.m);
        }
        this.cv.repaint(this.pause);
    }
    
    void createOrbitals() {
        int orbCount = 0;
        boolean b = false;
        for (int i = 0; i != this.stateCount; ++i) {
            final BasisState basisState = this.states[i];
            if (basisState.m == 0) {
                if (((Complex)basisState).mag != 0.0) {
                    ++orbCount;
                    if (basisState.orb == null) {
                        b = true;
                    }
                }
                else if (basisState.orb != null) {
                    b = true;
                }
            }
            else if (basisState.m > 0) {
                if (((Complex)basisState).mag != 0.0 || ((Complex)this.getState(basisState.n, basisState.l, -basisState.m)).mag != 0.0) {
                    ++orbCount;
                    if (basisState.orb == null) {
                        b = true;
                    }
                }
                else if (basisState.orb != null) {
                    b = true;
                }
            }
        }
        if (!b) {
            return;
        }
        this.orbCount = orbCount;
        this.orbitals = new Orbital[this.orbCount];
        int n = 0;
        for (int j = 0; j != this.stateCount; ++j) {
            final BasisState basisState2 = this.states[j];
            if ((basisState2.m == 0 && ((Complex)basisState2).mag != 0.0) || (basisState2.m > 0 && (((Complex)basisState2).mag != 0.0 || ((Complex)this.getState(basisState2.n, basisState2.l, -basisState2.m)).mag != 0.0))) {
                if (basisState2.orb == null) {
                    Orbital orb;
                    if (basisState2.l == 0) {
                        orb = new SOrbital(basisState2);
                    }
                    else if (basisState2.m == 0) {
                        orb = new MZeroOrbital(basisState2);
                    }
                    else {
                        orb = new PairedOrbital(basisState2);
                    }
                    orb.precompute();
                    basisState2.orb = orb;
                }
                this.orbitals[n++] = basisState2.orb;
            }
            else {
                basisState2.orb = null;
            }
        }
        System.out.println(this.orbCount);
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
        }
        this.cv.repaint(this.pause);
    }
    
    void orbitalChanged() {
        if (this.viewChooser.getSelectedIndex() > 1) {
            return;
        }
        this.doClear();
        if (this.viewChooser.getSelectedIndex() == 0) {
            int selectedIndex = this.mChooser.getSelectedIndex();
            if (selectedIndex == 0) {
                ((Complex)this.getState(this.getN(), this.getL(), 0)).set(1.0, 0.0);
            }
            else if (this.getL() == 3 && this.cubicItem.getState()) {
                for (int i = 0; i != 7; ++i) {
                    final int n = selectedIndex * 14 + i * 2;
                    ((Complex)this.getState(this.getN(), 3, i - 3)).set(this.l3CubicArray[n], this.l3CubicArray[n + 1]);
                }
            }
            else {
                final int n2 = --selectedIndex / 2 + 1;
                final double pow = Math.pow(-1.0, n2);
                if ((selectedIndex & 0x1) == 0x0) {
                    ((Complex)this.getState(this.getN(), this.getL(), n2)).set(pow * 0.7071067811865476);
                    ((Complex)this.getState(this.getN(), this.getL(), -n2)).set(0.7071067811865476);
                }
                else {
                    ((Complex)this.getState(this.getN(), this.getL(), n2)).set(0.0, -pow * 0.7071067811865476);
                    ((Complex)this.getState(this.getN(), this.getL(), -n2)).set(0.0, 0.7071067811865476);
                }
            }
        }
        else {
            ((Complex)this.getState(this.getN(), this.getL(), this.getM())).set(1.0, 0.0);
        }
        this.createOrbitals();
        this.manualScale = false;
    }
    
    BasisState getState(final int n, final int n2, final int n3) {
        final int n4 = n - 1;
        return this.states[n4 * (n4 + 1) * (2 * n4 + 1) / 6 + n2 * n2 + n2 + n3];
    }
    
    void setBrightness(final double n) {
        double n2 = 0.0;
        double n3 = 0.0;
        double n4 = 1.0E30;
        for (int i = 0; i != this.orbCount; ++i) {
            final Orbital orbital = this.orbitals[i];
            final double brightness = orbital.getBrightness();
            if (brightness < n4) {
                n4 = brightness;
            }
            final BasisState state = orbital.state;
            double n5 = ((Complex)state).magSquared() * n;
            if (orbital.state.m != 0) {
                n5 += ((Complex)this.getState(state.n, state.l, -state.m)).magSquared() * n;
            }
            n3 += n5;
            n2 += n5 * brightness;
        }
        this.bestBrightness = 113.9 / (Math.sqrt(n4) * n3);
        this.brightnessBar.setValue((int)(Math.log(this.bestBrightness * this.userBrightMult) * 100.0));
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
        AtomViewerFrame.maxModes = 10;
        AtomViewerFrame.maxDispCoefs = 8;
        AtomViewerFrame.viewDistance = 12;
    }
    
    abstract class Orbital
    {
        BasisState state;
        int n;
        int l;
        int m;
        float reMult;
        float imMult;
        float[] dataR;
        float[] dataTh;
        float[] dataPhiR;
        float[] dataPhiI;
        int dshalf;
        double brightnessCache;
        final int distmult = 4;
        
        Orbital(final BasisState state) {
            this.n = state.n;
            this.l = state.l;
            this.m = state.m;
            this.state = state;
        }
        
        void setupFrame(final double n) {
            this.reMult = (float)(this.state.re * n);
            this.imMult = (float)(this.state.im * n);
        }
        
        double getBoundRadius(final double n) {
            int n2 = 1;
            final double n3 = 1.0 / this.sphericalNorm(this.l, (this.m < 0) ? (-this.m) : this.m);
            final double n4 = n3 * n3 * n;
            for (int i = 0; i != AtomViewerFrame.this.dataSize; ++i) {
                if (this.dataR[i] * this.dataR[i] * n4 > 32.0) {
                    n2 = i;
                }
            }
            return n2 / (AtomViewerFrame.this.dataSize / 2.0);
        }
        
        double getScaleRadius() {
            final double n = -this.n * this.n * 2;
            return 0.5 * (-n + Math.sqrt(n * n - 4.0 * (this.l * (this.l + 1) * this.n * this.n)));
        }
        
        void precompute() {
            this.dshalf = AtomViewerFrame.this.dataSize / 2;
            final double n = AtomViewerFrame.this.scaleBar.getValue() / 50.0;
            final int n2 = (this.m < 0) ? (-this.m) : this.m;
            final double pow = Math.pow(-1.0, this.m);
            final double n3 = this.radialNorm(this.n, this.l) * this.sphericalNorm(this.l, n2);
            this.dataR = new float[AtomViewerFrame.this.dataSize];
            for (int i = 0; i != AtomViewerFrame.this.dataSize; ++i) {
                final double n4 = 2.0 * (i * AtomViewerFrame.this.resadj + 1.0E-8) * n / this.n;
                this.dataR[i] = (float)(AtomViewerFrame.this.hypser(this.l + 1 - this.n, 2 * this.l + 2, n4) * (Math.pow(n4, this.l) * n3) * Math.exp(-n4 / 2.0));
            }
            if (this.l > 0) {
                this.dataTh = new float[AtomViewerFrame.this.dataSize + 1];
                for (int j = 0; j != AtomViewerFrame.this.dataSize + 1; ++j) {
                    this.dataTh[j] = (float)(pow * AtomViewerFrame.this.plgndr(this.l, n2, (j - this.dshalf) / this.dshalf));
                }
            }
            if (this.m != 0) {
                this.dataPhiR = new float[8 * (AtomViewerFrame.this.dataSize + 1)];
                this.dataPhiI = new float[8 * (AtomViewerFrame.this.dataSize + 1)];
                int n5 = 0;
                for (int k = 0; k != 8; ++k) {
                    for (int l = 0; l <= AtomViewerFrame.this.dataSize; ++l, ++n5) {
                        final double n6 = k * 3.141592653589793 / 4.0 + l * 0.7853981633974483 / AtomViewerFrame.this.dataSize;
                        this.dataPhiR[n5] = (float)Math.cos(n6 * n2);
                        this.dataPhiI[n5] = (float)Math.sin(n6 * n2);
                    }
                }
            }
            this.brightnessCache = 0.0;
        }
        
        double getBrightness() {
            if (this.brightnessCache != 0.0) {
                return this.brightnessCache;
            }
            double n = 0.0;
            double n2 = 0.0;
            final double n3 = 1.0 / this.sphericalNorm(this.l, (this.m < 0) ? (-this.m) : this.m);
            for (int i = 0; i != AtomViewerFrame.this.dataSize; ++i) {
                final double n4 = this.dataR[i] * n3;
                final double n5 = n4 * n4;
                n += n5 * n5 * i * i;
                n2 += i * i;
            }
            return this.brightnessCache = n / n2;
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
        
        abstract void computePoint(final int p0, final int p1);
    }
    
    class SOrbital extends Orbital
    {
        SOrbital(final BasisState basisState) {
            super(basisState);
        }
        
        void computePoint(final int n, final int n2) {
            try {
                final float n3 = super.dataR[n];
                AtomViewerFrame.this.funcr = super.reMult * n3;
                AtomViewerFrame.this.funci = super.imMult * n3;
            }
            catch (Exception ex) {
                final AtomViewerFrame this$0 = AtomViewerFrame.this;
                final AtomViewerFrame this$2 = AtomViewerFrame.this;
                final float n4 = 0.0f;
                this$2.funci = n4;
                this$0.funcr = n4;
                System.out.println("bad " + n + " " + n2);
            }
        }
    }
    
    class MZeroOrbital extends Orbital
    {
        MZeroOrbital(final BasisState basisState) {
            super(basisState);
        }
        
        void computePoint(final int n, final int n2) {
            try {
                final float n3 = super.dataR[n] * super.dataTh[n2];
                AtomViewerFrame.this.funcr = n3 * super.reMult;
                AtomViewerFrame.this.funci = n3 * super.imMult;
            }
            catch (Exception ex) {
                final AtomViewerFrame this$0 = AtomViewerFrame.this;
                final AtomViewerFrame this$2 = AtomViewerFrame.this;
                final float n4 = 0.0f;
                this$2.funci = n4;
                this$0.funcr = n4;
                System.out.println("bad " + n + " " + n2);
            }
        }
    }
    
    class PairedOrbital extends Orbital
    {
        BasisState negstate;
        float f1;
        float f2;
        float f3;
        float f4;
        
        PairedOrbital(final BasisState basisState) {
            super(basisState);
            this.negstate = AtomViewerFrame.this.getState(basisState.n, basisState.l, -basisState.m);
        }
        
        void setupFrame(final double n) {
            final double n2 = super.state.re * n;
            final double n3 = super.state.im * n;
            final double n4 = this.negstate.re * n;
            final double n5 = this.negstate.im * n;
            final double pow = Math.pow(-1.0, super.m);
            final double n6 = n2 * pow;
            final double n7 = n3 * pow;
            this.f1 = (float)(n6 + n4);
            this.f2 = (float)(n5 - n7);
            this.f3 = (float)(n7 + n5);
            this.f4 = (float)(n6 - n4);
        }
        
        void computePoint(final int n, final int n2) {
            try {
                final float n3 = super.dataR[n] * super.dataTh[n2];
                final float n4 = super.dataPhiR[AtomViewerFrame.this.phiIndex];
                final float n5 = super.dataPhiI[AtomViewerFrame.this.phiIndex];
                AtomViewerFrame.this.funcr = n3 * (this.f1 * n4 + this.f2 * n5);
                AtomViewerFrame.this.funci = n3 * (this.f3 * n4 + this.f4 * n5);
            }
            catch (Exception ex) {
                final AtomViewerFrame this$0 = AtomViewerFrame.this;
                final AtomViewerFrame this$2 = AtomViewerFrame.this;
                final float n6 = 0.0f;
                this$2.funci = n6;
                this$0.funcr = n6;
                System.out.println("bad " + n + " " + n2);
            }
        }
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
        
        void convertDerivedToBasis() {
        }
        
        void convertBasisToDerived() {
        }
        
        void setBasisActive() {
        }
        
        abstract String getText();
    }
    
    class BasisState extends State
    {
        int n;
        int l;
        int m;
        Orbital orb;
        
        String getText() {
            return "n = " + this.n + ", l = " + this.l + ", m = " + this.m;
        }
    }
    
    class DerivedState extends State
    {
        int count;
        int m;
        AlternateBasis basis;
        String text;
        BasisState[] bstates;
        Complex[] coefs;
        
        void convertDerivedToBasis() {
            this.basis.convertDerivedToBasis();
        }
        
        void convertBasisToDerived() {
            this.basis.convertBasisToDerived();
        }
        
        void setBasisActive() {
            this.basis.active = true;
        }
        
        String getText() {
            return this.text;
        }
    }
    
    class AlternateBasis
    {
        DerivedState[] altStates;
        int altStateCount;
        boolean active;
        int n;
        int l;
        boolean xAxis;
        
        AlternateBasis() {
            AtomViewerFrame.this.basisList[AtomViewerFrame.this.basisCount++] = this;
        }
        
        void convertDerivedToBasis() {
            this.convertDerivedToBasis(true);
        }
        
        void convertDerivedToBasis(final boolean b) {
            if (b) {
                for (int i = 0; i != AtomViewerFrame.this.stateCount; ++i) {
                    ((Complex)AtomViewerFrame.this.states[i]).set(0.0);
                }
            }
            final Complex complex = new Complex();
            for (int j = 0; j != this.altStateCount; ++j) {
                final DerivedState derivedState = this.altStates[j];
                for (int k = 0; k != derivedState.count; ++k) {
                    complex.set(derivedState.coefs[k]);
                    complex.conjugate();
                    complex.mult((Complex)derivedState);
                    ((Complex)derivedState.bstates[k]).add(complex);
                }
            }
            double mag = 0.0;
            for (int l = 0; l != AtomViewerFrame.this.stateCount; ++l) {
                if (AtomViewerFrame.this.states[l].mag > mag) {
                    mag = AtomViewerFrame.this.states[l].mag;
                }
            }
            if (mag > 1.0) {
                final double n = 1.0 / mag;
                for (int n2 = 0; n2 != AtomViewerFrame.this.stateCount; ++n2) {
                    ((Complex)AtomViewerFrame.this.states[n2]).mult(n);
                }
            }
        }
        
        void convertBasisToDerived() {
            final Complex complex = new Complex();
            final Complex complex2 = new Complex();
            double mag = 0.0;
            for (int i = 0; i != this.altStateCount; ++i) {
                final DerivedState derivedState = this.altStates[i];
                complex.set(0.0);
                try {
                    for (int j = 0; j != derivedState.count; ++j) {
                        complex2.set(derivedState.coefs[j]);
                        complex2.mult((Complex)derivedState.bstates[j]);
                        complex.add(complex2);
                    }
                }
                catch (Exception ex) {
                    System.out.print("Exception at " + i + "\n");
                }
                if (complex.mag < 0.01) {
                    complex.set(0.0);
                }
                ((Complex)derivedState).set(complex);
                if (complex.mag > mag) {
                    mag = derivedState.mag;
                }
            }
            if (mag > 1.0) {
                final double n = 1.0 / mag;
                for (int k = 0; k != this.altStateCount; ++k) {
                    ((Complex)this.altStates[k]).mult(n);
                }
            }
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
