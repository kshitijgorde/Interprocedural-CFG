import java.awt.Event;
import java.awt.event.ItemEvent;
import java.awt.event.MouseEvent;
import java.awt.event.AdjustmentEvent;
import java.awt.event.ActionEvent;
import java.awt.event.ComponentEvent;
import java.awt.image.ImageObserver;
import java.awt.Graphics;
import java.util.StringTokenizer;
import java.net.URL;
import java.io.FilterInputStream;
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

class MOViewerFrame extends Frame implements ComponentListener, ActionListener, AdjustmentListener, MouseMotionListener, MouseListener, ItemListener
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
    Checkbox memoryImageSourceCheck;
    CheckboxMenuItem colorCheck;
    CheckboxMenuItem eCheckItem;
    CheckboxMenuItem eSepCheckItem;
    CheckboxMenuItem xCheckItem;
    CheckboxMenuItem alwaysNormItem;
    CheckboxMenuItem nuclearItem;
    CheckboxMenuItem showAtomsItem;
    CheckboxMenuItem dimensionsItem;
    CheckboxMenuItem axesItem;
    MenuItem exitItem;
    Choice sliceChooser;
    Choice stateChooser;
    Choice sampleChooser;
    CheckboxMenuItem[] samplesItems;
    int[] samplesNums;
    static final int SLICE_NONE = 0;
    static final int SLICE_X = 1;
    static final int SLICE_Y = 2;
    static final int SLICE_Z = 3;
    Scrollbar resolutionBar;
    Scrollbar internalResBar;
    Scrollbar brightnessBar;
    Scrollbar scaleBar;
    Scrollbar sampleBar;
    Scrollbar separationBar;
    View viewPotential;
    View viewPotentialSep;
    View viewX;
    View[] viewList;
    int viewCount;
    int stateNum;
    Orbital[] orbitals;
    int orbCount;
    Orbital[] orbListLeft;
    Orbital[] orbListRight;
    Orbital[] orbListCenter;
    int orbCountOffset;
    int orbCountCenter;
    double[][] evalues;
    int basisCount;
    boolean changingDerivedStates;
    double dragZoomStart;
    double zoom;
    double[] rotmatrix;
    double sep2;
    double colorMult;
    Rectangle viewAxes;
    static final double pi = 3.141592653589793;
    static final double pi2 = 6.283185307179586;
    static final float root2 = 1.4142135f;
    static final float root2inv = 0.70710677f;
    static final double baseEnergy = 0.55;
    int[] xpoints;
    int[] ypoints;
    int selectedPaneHandle;
    double[][][] func;
    PhaseColor[] phaseColors;
    double resadj;
    boolean dragging;
    MemoryImageSource imageSource;
    int[] pixels;
    int sampleCount;
    int dataSize;
    float[] modes;
    static int maxModes;
    static int maxDispCoefs;
    static int viewDistance;
    int pause;
    MOViewer applet;
    int selection;
    static final int SEL_NONE = 0;
    static final int SEL_POTENTIAL = 1;
    static final int SEL_X = 2;
    static final int SEL_STATES = 3;
    static final int SEL_HANDLE = 4;
    static final int MODE_ANGLE = 0;
    static final int MODE_SLICE = 1;
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
    int phiIndex;
    boolean manualScale;
    Color gray2;
    FontMetrics fontMetrics;
    MOViewerCanvas cv;
    boolean useBufferedImage;
    int precount;
    int scaleValue;
    int sepValue;
    
    public String getAppletInfo() {
        return "MOViewer by Paul Falstad";
    }
    
    int getrand(final int n) {
        int nextInt = this.random.nextInt();
        if (nextInt < 0) {
            nextInt = -nextInt;
        }
        return nextInt % n;
    }
    
    MOViewerFrame(final MOViewer applet) {
        super("Molecular Orbital Viewer v1.5");
        this.gridSizeX = 200;
        this.gridSizeY = 200;
        this.samplesNums = new int[] { 9, 15, 25, 35, 45, 55 };
        this.dragging = false;
        this.selection = -1;
        this.useBufferedImage = false;
        this.scaleValue = -1;
        this.sepValue = -1;
        this.applet = applet;
    }
    
    public void init() {
        this.gray2 = new Color(127, 127, 127);
        if (new Double(System.getProperty("java.class.version")) >= 48.0) {
            this.useBufferedImage = true;
        }
        final int n = 68;
        this.setLayout(new MOViewerLayout());
        (this.cv = new MOViewerCanvas(this)).addComponentListener(this);
        this.cv.addMouseMotionListener(this);
        this.cv.addMouseListener(this);
        this.add(this.cv);
        final MenuBar menuBar = new MenuBar();
        final Menu menu = new Menu("File");
        menuBar.add(menu);
        menu.add(this.exitItem = this.getMenuItem("Exit"));
        final Menu menu2 = new Menu("View");
        menuBar.add(menu2);
        menu2.add(this.eSepCheckItem = this.getCheckItem("Energy"));
        this.eSepCheckItem.setState(true);
        menu2.add(this.xCheckItem = this.getCheckItem("Position"));
        this.xCheckItem.setState(true);
        this.xCheckItem.disable();
        menu2.addSeparator();
        menu2.add(this.colorCheck = this.getCheckItem("Phase as Color"));
        this.colorCheck.setState(true);
        final Menu menu3 = new Menu("Options");
        menuBar.add(menu3);
        menu3.add(this.nuclearItem = this.getCheckItem("Include Nuclear E"));
        this.nuclearItem.setState(true);
        menu3.add(this.showAtomsItem = this.getCheckItem("Show Nuclei"));
        this.showAtomsItem.setState(true);
        menu3.add(this.dimensionsItem = this.getCheckItem("Show Dimensions"));
        menu3.add(this.axesItem = this.getCheckItem("Show Axes"));
        this.axesItem.setState(true);
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
        (this.stateChooser = new Choice()).add("sigma g 1s");
        this.stateChooser.add("sigma*u 1s");
        this.stateChooser.add("pi u 2px");
        this.stateChooser.add("pi u 2py");
        this.stateChooser.add("sigma g 2s");
        this.stateChooser.add("sigma g 2pz");
        this.stateChooser.add("sigma*u 2s");
        this.stateChooser.add("pi*g 2px");
        this.stateChooser.add("pi*g 2py");
        this.stateChooser.add("sigma*u 2pz");
        this.stateChooser.addItemListener(this);
        this.add(this.stateChooser);
        (this.sliceChooser = new Choice()).add("No Slicing");
        this.sliceChooser.add("Show X Slice");
        this.sliceChooser.add("Show Y Slice");
        this.sliceChooser.add("Show Z Slice");
        this.sliceChooser.addItemListener(this);
        this.add(this.sliceChooser);
        this.add(new Label("Brightness", 1));
        this.add(this.brightnessBar = new Scrollbar(0, 1385, 1, 1000, 1800));
        this.brightnessBar.addAdjustmentListener(this);
        this.add(new Label("Image Resolution", 1));
        this.add(this.resolutionBar = new Scrollbar(0, n, 2, 20, 200));
        this.resolutionBar.addAdjustmentListener(this);
        this.add(new Label("Scale", 1));
        this.add(this.scaleBar = new Scrollbar(0, 24, 1, 5, 52));
        this.scaleBar.addAdjustmentListener(this);
        this.add(new Label("Separation", 1));
        this.add(this.separationBar = new Scrollbar(0, 12, 1, 0, 21));
        this.separationBar.addAdjustmentListener(this);
        this.add(new Label("http://www.falstad.com"));
        try {
            final String parameter = this.applet.getParameter("PAUSE");
            if (parameter != null) {
                this.pause = Integer.parseInt(parameter);
            }
        }
        catch (Exception ex) {}
        this.phaseColors = new PhaseColor[400];
        for (int i = 0; i != 8; ++i) {
            for (int j = 0; j != 50; ++j) {
                this.phaseColors[i * 50 + j] = this.genPhaseColor(i, Math.atan(j / 50.0));
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
        this.rotate(-1.5707963267948966, 0.0);
        this.rotate(0.0, 1.5707963267948966);
        this.xpoints = new int[4];
        this.ypoints = new int[4];
        this.setupSimpson();
        this.random = new Random();
        this.readModes();
        this.getEnergyValues();
        this.createOrbitals();
        this.reinit();
        this.cv.setBackground(Color.black);
        this.cv.setForeground(Color.white);
        this.resize(550, 530);
        this.handleResize();
        final Dimension size = this.getSize();
        final Dimension screenSize = this.getToolkit().getScreenSize();
        this.setLocation((screenSize.width - size.width) / 2, (screenSize.height - size.height) / 2);
        this.show();
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
    
    void setupDisplay() {
        if (this.winSize == null) {
            return;
        }
        final int n = (this.viewPotentialSep == null) ? 100 : this.viewPotentialSep.height;
        final View viewX = null;
        this.viewPotentialSep = viewX;
        this.viewPotential = viewX;
        this.viewX = viewX;
        this.viewList = new View[10];
        int viewCount = 0;
        if (this.eSepCheckItem.getState()) {
            this.viewList[viewCount++] = (this.viewPotentialSep = new View());
        }
        if (this.xCheckItem.getState()) {
            this.viewList[viewCount++] = (this.viewX = new View());
        }
        this.viewCount = viewCount;
        int viewCount2 = this.viewCount;
        int height = this.winSize.height;
        if (n > 0 && this.viewPotentialSep != null) {
            --viewCount2;
            height -= n;
        }
        final int n2 = height - 8 * (this.viewCount - 1);
        int n3 = 0;
        for (int i = 0; i != this.viewCount; ++i) {
            final View view = this.viewList[i];
            int height2 = (viewCount2 == 0) ? n2 : (n2 / viewCount2);
            if (view == this.viewPotentialSep && n > 0) {
                height2 = n;
            }
            if ((view.paneY = n3) > 0) {
                n3 += 4;
            }
            view.x = 0;
            view.width = this.winSize.width;
            view.y = n3;
            view.height = height2;
            n3 += height2 + 4;
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
        System.out.print("setResolution " + this.dataSize + " " + this.gridSizeX + "\n");
        this.resadj = 50.0 / this.dataSize;
        this.precomputeAll();
        this.func = new double[this.gridSizeX][this.gridSizeY][3];
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
        final double n9 = 1.22;
        final double n10 = n9 * n9;
        final double n11 = this.sep2 * 0.5 / (n6 * (this.scaleBar.getValue() / 50.0) * this.resadj);
        for (int j = 0; j != this.gridSizeX; ++j) {
            for (int k = 0; k != this.gridSizeY; ++k) {
                final double n12 = (2 * j / this.gridSizeX - 1.0) * n7;
                final double n13 = -(2 * k / this.gridSizeY - 1.0) * n8;
                final double n14 = rotmatrix[2] * MOViewerFrame.viewDistance;
                final double n15 = rotmatrix[5] * MOViewerFrame.viewDistance;
                final double n16 = rotmatrix[8] * MOViewerFrame.viewDistance;
                final double n17 = rotmatrix[0] * n12 + rotmatrix[1] * n13 - rotmatrix[2];
                final double n18 = rotmatrix[3] * n12 + rotmatrix[4] * n13 - rotmatrix[5];
                final double n19 = rotmatrix[6] * n12 + rotmatrix[7] * n13 - rotmatrix[8];
                final double sqrt = Math.sqrt(n12 * n12 + n13 * n13 + 1.0);
                float n20 = 0.0f;
                float n21 = 0.0f;
                final double n22 = n17 * n17 + n18 * n18 + n19 * n19;
                final double n23 = 2.0 * (n17 * n14 + n18 * n15 + n19 * n16);
                final double n24 = n23 * n23 - 4.0 * n22 * (n14 * n14 + n15 * n15 + n16 * n16 - n10);
                final double[] array = this.func[j][k];
                final int n25 = 0;
                final double[] array2 = this.func[j][k];
                final int n26 = 1;
                final double[] array3 = this.func[j][k];
                final int n27 = 2;
                final double n28 = 0.0;
                array3[n27] = n28;
                array[n25] = (array2[n26] = n28);
                if (n24 < 0.0) {
                    this.fillSquare(j, k, 0.0f, 0.0f, 0.0f);
                }
                else {
                    final double sqrt2 = Math.sqrt(n24);
                    double n29 = (-n23 - sqrt2) / (2.0 * n22);
                    double n30 = (-n23 + sqrt2) / (2.0 * n22);
                    if (selectedIndex != 0) {
                        double n31 = -100.0;
                        switch (selectedIndex) {
                            case 1: {
                                n31 = (this.sliceval - n14) / n17;
                                break;
                            }
                            case 2: {
                                n31 = (this.sliceval - n15) / n18;
                                break;
                            }
                            case 3: {
                                n31 = (this.sliceval - n16) / n19;
                                break;
                            }
                        }
                        if (n31 < n29 || n31 > n30) {
                            this.fillSquare(j, k, 0.0f, 0.0f, 0.0f);
                            continue;
                        }
                        n30 = (n29 = n31);
                    }
                    final double n32 = (n30 - n29) / (this.sampleCount - 1);
                    double n33 = (n30 - n29) * sqrt;
                    int n34 = this.sampleCount - 1;
                    int l = 1;
                    double n35 = (n14 + n17 * n29) * n4;
                    double n36 = (n15 + n18 * n29) * n5;
                    double n37 = (n16 + n19 * n29) * n6;
                    if (selectedIndex != 0) {
                        n34 = 1;
                        l = 0;
                        n33 = 2.0;
                        if (n35 > n4 || n36 > n5 || n37 > n6 || n35 < -n4 || n36 < -n5 || n37 < -n6) {
                            this.fillSquare(j, k, 0.0f, 0.0f, 0.0f);
                            continue;
                        }
                    }
                    final double n38 = n17 * (n32 * n4);
                    final double n39 = n18 * (n32 * n5);
                    final double n40 = n19 * (n32 * n6);
                    final int n41 = this.dataSize / 2;
                    final double n42 = n11 * n6;
                    while (l < n34) {
                        final double n43 = n35 * n35 + n36 * n36;
                        final double n44 = n37 + n42;
                        final double sqrt3 = Math.sqrt(n43 + n44 * n44);
                        final double n45 = n44 / sqrt3;
                        final int n46 = (int)sqrt3;
                        final int n47 = (int)(n45 * n41 + n41);
                        float n48 = 0.0f;
                        this.calcPhiComponent(n35, n36);
                        for (int n49 = 0; n49 != this.orbCountOffset; ++n49) {
                            n48 += this.orbListLeft[n49].computePoint(n46, n47);
                        }
                        final double n50 = n37 - n42;
                        final double sqrt4 = Math.sqrt(n43 + n50 * n50);
                        final double n51 = n50 / sqrt4;
                        final int n52 = (int)sqrt4;
                        final int n53 = (int)(n51 * n41 + n41);
                        for (int n54 = 0; n54 != this.orbCountOffset; ++n54) {
                            n48 += this.orbListRight[n54].computePoint(n52, n53);
                        }
                        if (this.orbCountCenter != 0) {
                            final double sqrt5 = Math.sqrt(n43 + n37 * n37);
                            final double n55 = n37 / sqrt5;
                            final int n56 = (int)sqrt5;
                            final int n57 = (int)(n55 * n41 + n41);
                            for (int n58 = 0; n58 != this.orbCountCenter; ++n58) {
                                n48 += this.orbListCenter[n58].computePoint(n56, n57);
                            }
                        }
                        final float n59 = n48 * n48 * this.sampleMult[l];
                        if (state) {
                            if (n48 > 0.0f) {
                                n20 += n59;
                            }
                            else {
                                n21 += n59;
                            }
                        }
                        else {
                            n21 = (n20 = n21 + n59);
                        }
                        n35 += n38;
                        n36 += n39;
                        n37 += n40;
                        ++l;
                    }
                    final float n60 = (float)(n20 * (n33 / l));
                    final float n61 = (float)(n21 * (n33 / l));
                    this.fillSquare(j, k, n60, n61, n61);
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
    
    void readModes() {
        try {
            final FilterInputStream filterInputStream = (FilterInputStream)new URL(String.valueOf(this.applet.getCodeBase()) + "states.txt").getContent();
            final byte[] array = new byte[42000];
            int n = 0;
            while (true) {
                final int read = filterInputStream.read(array, n, 2048);
                if (read <= 0) {
                    break;
                }
                n += read;
            }
            final int n2 = n;
            int n3 = 0;
            this.modes = new float[10101];
            int j;
            for (int i = 0; i < n2; i += j) {
                for (j = 0; j != n2 - i; ++j) {
                    if (array[j + i] == 10) {
                        ++j;
                        break;
                    }
                }
                final StringTokenizer stringTokenizer = new StringTokenizer(new String(array, i, j - 1));
                while (stringTokenizer.hasMoreTokens()) {
                    this.modes[n3++] = new Float(stringTokenizer.nextToken());
                }
            }
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    
    void precomputeAll() {
        for (int i = 0; i != this.orbCount; ++i) {
            this.orbitals[i].precompute();
        }
        this.sep2 = this.separationBar.getValue() / 2.0;
        if (this.sep2 < 0.0) {
            this.sep2 = 0.0;
        }
        if (this.sep2 > 10.0) {
            this.sep2 = 10.0;
        }
        int n;
        for (n = 0; this.modes[n] != 99999.0f && this.modes[n] != 99000.0 + this.sep2; ++n) {}
        if (this.modes[n] == 99999.0f) {
            return;
        }
        ++n;
        this.stateNum = 0;
        this.orbitals[4].setReal();
        this.orbitals[5].setReal();
        this.orbitals[9].setReal();
        switch (this.stateChooser.getSelectedIndex()) {
            case 0: {
                this.stateNum = 0;
                break;
            }
            case 1: {
                this.stateNum = 1;
                break;
            }
            case 2: {
                this.stateNum = 2;
                break;
            }
            case 3: {
                this.stateNum = 2;
                this.orbitals[4].setIm();
                this.orbitals[5].setIm();
                this.orbitals[9].setIm();
                break;
            }
            case 4: {
                this.stateNum = 3;
                break;
            }
            case 5: {
                this.stateNum = 4;
                break;
            }
            case 6: {
                this.stateNum = 5;
                break;
            }
            case 7: {
                this.stateNum = 6;
                break;
            }
            case 8: {
                this.stateNum = 6;
                this.orbitals[4].setIm();
                this.orbitals[5].setIm();
                this.orbitals[9].setIm();
                break;
            }
            case 9: {
                this.stateNum = 7;
                break;
            }
        }
        for (int j = 0; j != this.orbCount; ++j) {
            this.orbitals[j].used = false;
        }
        while (this.modes[n] < 99000.0f && this.modes[n] != this.stateNum) {
            n += 59;
        }
        if (this.modes[n] >= 99000.0f) {
            return;
        }
        ++n;
        this.sep2 = this.modes[n++];
        int n2 = 1;
        if (this.sep2 < 0.0) {
            this.sep2 = -this.sep2;
            n2 = -1;
        }
        ++n;
        this.precount = 0;
        this.orbitals[0].precomputeR(1.0, 1, n2 * this.modes[n++]);
        this.orbitals[1].precomputeR(1.0, 1, n2 * this.modes[n++]);
        this.orbitals[0].precomputeR(1.5, 1, n2 * this.modes[n++]);
        this.orbitals[1].precomputeR(1.5, 1, n2 * this.modes[n++]);
        this.orbitals[0].precomputeR(2.0, 1, n2 * this.modes[n++]);
        this.orbitals[1].precomputeR(2.0, 1, n2 * this.modes[n++]);
        this.orbitals[0].precomputeR(0.4, 1, n2 * this.modes[n++]);
        this.orbitals[1].precomputeR(0.4, 1, n2 * this.modes[n++]);
        this.orbitals[0].precomputeR(0.7, 1, n2 * this.modes[n++]);
        this.orbitals[1].precomputeR(0.7, 1, n2 * this.modes[n++]);
        this.orbitals[0].precomputeR(1.0, 2, n2 * this.modes[n++]);
        this.orbitals[1].precomputeR(1.0, 2, n2 * this.modes[n++]);
        this.orbitals[0].precomputeR(1.5, 2, n2 * this.modes[n++]);
        this.orbitals[1].precomputeR(1.5, 2, n2 * this.modes[n++]);
        this.orbitals[0].precomputeR(2.0, 2, n2 * this.modes[n++]);
        this.orbitals[1].precomputeR(2.0, 2, n2 * this.modes[n++]);
        this.orbitals[0].precomputeR(0.4, 2, n2 * this.modes[n++]);
        this.orbitals[1].precomputeR(0.4, 2, n2 * this.modes[n++]);
        this.orbitals[0].precomputeR(0.7, 2, n2 * this.modes[n++]);
        this.orbitals[1].precomputeR(0.7, 2, n2 * this.modes[n++]);
        this.orbitals[4].precomputeR(1.0, 2, n2 * this.modes[n++]);
        this.orbitals[5].precomputeR(1.0, 2, n2 * this.modes[n++]);
        this.orbitals[4].precomputeR(1.5, 2, n2 * this.modes[n++]);
        this.orbitals[5].precomputeR(1.5, 2, n2 * this.modes[n++]);
        this.orbitals[4].precomputeR(2.0, 2, n2 * this.modes[n++]);
        this.orbitals[5].precomputeR(2.0, 2, n2 * this.modes[n++]);
        this.orbitals[4].precomputeR(0.4, 2, n2 * this.modes[n++]);
        this.orbitals[5].precomputeR(0.4, 2, n2 * this.modes[n++]);
        this.orbitals[4].precomputeR(0.7, 2, n2 * this.modes[n++]);
        this.orbitals[5].precomputeR(0.7, 2, n2 * this.modes[n++]);
        this.orbitals[2].precomputeR(1.0, 2, n2 * this.modes[n++]);
        this.orbitals[3].precomputeR(1.0, 2, n2 * this.modes[n++]);
        this.orbitals[2].precomputeR(1.5, 2, n2 * this.modes[n++]);
        this.orbitals[3].precomputeR(1.5, 2, n2 * this.modes[n++]);
        this.orbitals[2].precomputeR(2.0, 2, n2 * this.modes[n++]);
        this.orbitals[3].precomputeR(2.0, 2, n2 * this.modes[n++]);
        this.orbitals[2].precomputeR(0.4, 2, n2 * this.modes[n++]);
        this.orbitals[3].precomputeR(0.4, 2, n2 * this.modes[n++]);
        this.orbitals[2].precomputeR(0.7, 2, n2 * this.modes[n++]);
        this.orbitals[3].precomputeR(0.7, 2, n2 * this.modes[n++]);
        this.orbitals[6].precomputeR(1.5, 1, n2 * this.modes[n++]);
        this.orbitals[6].precomputeR(2.0, 1, n2 * this.modes[n++]);
        this.orbitals[6].precomputeR(1.5, 2, n2 * this.modes[n++]);
        this.orbitals[6].precomputeR(2.0, 2, n2 * this.modes[n++]);
        this.orbitals[7].precomputeR(1.5, 2, n2 * this.modes[n++]);
        this.orbitals[7].precomputeR(2.0, 2, n2 * this.modes[n++]);
        this.orbitals[7].precomputeR(1.5, 3, n2 * this.modes[n++]);
        this.orbitals[7].precomputeR(2.0, 3, n2 * this.modes[n++]);
        this.orbitals[8].precomputeR(1.5, 3, n2 * this.modes[n++]);
        this.orbitals[8].precomputeR(2.0, 3, n2 * this.modes[n++]);
        this.orbitals[9].precomputeR(1.5, 3, -n2 * this.modes[n++]);
        this.orbitals[9].precomputeR(2.0, 3, -n2 * this.modes[n++]);
        this.orbitals[7].precomputeR(1.5, 4, n2 * this.modes[n++]);
        this.orbitals[7].precomputeR(2.0, 4, n2 * this.modes[n++]);
        this.orbitals[10].precomputeR(1.5, 4, n2 * this.modes[n++]);
        this.orbitals[10].precomputeR(2.0, 4, n2 * this.modes[n++]);
        final boolean b = false;
        this.orbCountCenter = (b ? 1 : 0);
        this.orbCountOffset = (b ? 1 : 0);
        this.orbListLeft = new Orbital[3];
        this.orbListRight = new Orbital[3];
        this.orbListCenter = new Orbital[5];
        for (int k = 0; k != 6; k += 2) {
            if (this.orbitals[k].used) {
                this.orbListLeft[this.orbCountOffset] = this.orbitals[k];
                this.orbListRight[this.orbCountOffset] = this.orbitals[k + 1];
                ++this.orbCountOffset;
            }
        }
        for (int l = 6; l != 11; ++l) {
            if (this.orbitals[l].used) {
                this.orbListCenter[this.orbCountCenter] = this.orbitals[l];
                ++this.orbCountCenter;
            }
        }
    }
    
    void getEnergyValues() {
        int n = 0;
        this.evalues = new double[21][8];
        while (this.modes[n] != 99999.0f) {
            final int n2 = (int)((this.modes[n] - 99000.0f) * 2.0f);
            ++n;
            while (this.modes[n] < 99000.0f) {
                this.evalues[n2][(int)this.modes[n]] = this.modes[n + 2];
                n += 59;
            }
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
    
    public void updateMOViewer(final Graphics graphics) {
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
        final boolean b = this.sliceChooser.getSelectedIndex() != 0;
        this.zoom = (b ? 8.0 : 16.55);
        this.colorMult = Math.exp(this.brightnessBar.getValue() / 100.0 - 2.0);
        this.computeView(1.0);
        for (int i = 1; i != this.viewCount; ++i) {
            graphics2.setColor((i == this.selectedPaneHandle) ? Color.yellow : Color.gray);
            graphics2.drawLine(0, this.viewList[i].paneY, this.winSize.width, this.viewList[i].paneY);
        }
        if (this.viewPotential != null) {
            final int n = (int)(this.sep2 - 1.0);
            final double n2 = this.viewPotential.height * 1.9;
            graphics2.setColor(Color.darkGray);
            final int n3 = this.viewPotential.y + this.viewPotential.height / 2;
            for (int j = 0; j != 21; ++j) {
                final int n4 = n3 - (int)(n2 * this.evalues[n][j]);
                graphics2.drawLine(0, n4, this.winSize.width, n4);
            }
            this.getScaler();
        }
        if (this.viewPotentialSep != null) {
            int y = this.viewPotentialSep.y + this.viewPotentialSep.height / 2;
            final double n5 = this.viewPotentialSep.height;
            double n6;
            if (this.nuclearItem.getState()) {
                n6 = n5 * 0.7;
            }
            else {
                n6 = n5 * 0.5;
                y = this.viewPotentialSep.y;
            }
            for (int k = 0; k != 8; ++k) {
                this.drawEnergyLine(graphics2, k, y, n6);
            }
            this.drawEnergyLine(graphics2, this.stateNum, y, n6);
            graphics2.setColor(Color.yellow);
            final int n7 = (int)(this.sep2 * this.winSize.width / 10.0);
            graphics2.drawLine(n7, this.viewPotentialSep.y, n7, this.viewPotentialSep.y + this.viewPotentialSep.height - 1);
        }
        if (this.imageSource != null) {
            this.imageSource.newPixels();
        }
        graphics2.drawImage(this.memimage, this.viewX.x, this.viewX.y, null);
        if (this.showAtomsItem.getState()) {
            final double n8 = this.sep2 * 0.5 / (this.dataSize / 2.0 * (this.scaleBar.getValue() / 50.0) * this.resadj);
            graphics2.setColor(Color.yellow);
            this.map3d(0.0, 0.0, n8, this.xpoints, this.ypoints, 0, this.viewX);
            graphics2.drawOval(this.xpoints[0] - 2, this.ypoints[0] - 2, 4, 4);
            this.map3d(0.0, 0.0, -n8, this.xpoints, this.ypoints, 0, this.viewX);
            graphics2.drawOval(this.xpoints[0] - 2, this.ypoints[0] - 2, 4, 4);
        }
        graphics2.setColor(Color.white);
        if (b) {
            this.drawCube(graphics2, false);
        }
        if (this.axesItem.getState()) {
            this.drawAxes(graphics2);
        }
        graphics2.setColor(Color.yellow);
        if (this.dimensionsItem.getState()) {
            this.centerString(graphics2, "Separation = " + (int)(this.sep2 * 52.9463) + " pm (" + this.sep2 + " a0)", this.viewX.y + this.viewX.height - 5);
        }
        graphics.drawImage(this.dbimage, 0, 0, this);
    }
    
    void drawEnergyLine(final Graphics graphics, final int n, final int n2, final double n3) {
        int n4 = -1;
        int n5 = -1;
        graphics.setColor((this.stateNum == n) ? Color.yellow : Color.darkGray);
        for (int i = 0; i != 21; ++i) {
            final int n6 = i * this.winSize.width / 20;
            double n7 = 0.0;
            if (this.nuclearItem.getState()) {
                n7 = ((i == 0) ? 10.0 : (1.0 / (i * 0.5)));
            }
            final int n8 = n2 - (int)(n3 * (this.evalues[i][n] + n7));
            if (n4 != -1) {
                graphics.drawLine(n4, n5, n6, n8);
            }
            n4 = n6;
            n5 = n8;
        }
    }
    
    double getScaler() {
        double n = this.viewX.width * this.zoom / 2.0;
        final double n2 = this.viewX.height * this.zoom / 2.0;
        final double n3 = this.viewX.width / this.viewX.height;
        if (n3 >= 1.0) {
            n /= n3;
        }
        return 1.0 / (2.0 * n / MOViewerFrame.viewDistance / (50.0 * (this.scaleBar.getValue() / 50.0)));
    }
    
    public void centerString(final Graphics graphics, final String s, final int n) {
        graphics.drawString(s, (this.winSize.width - this.fontMetrics.stringWidth(s)) / 2, n);
    }
    
    boolean visibleFace(final int n, final int n2, final int n3) {
        return (n - MOViewerFrame.viewDistance * this.rotmatrix[2]) * n + (n2 - MOViewerFrame.viewDistance * this.rotmatrix[5]) * n2 + (n3 - MOViewerFrame.viewDistance * this.rotmatrix[8]) * n3 < 0.0;
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
        final double n7 = MOViewerFrame.viewDistance - (n * rotmatrix[2] + n2 * rotmatrix[5] + n3 * rotmatrix[8]);
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
        final double n8 = MOViewerFrame.viewDistance * rotmatrix[2];
        final double n9 = MOViewerFrame.viewDistance * rotmatrix[5];
        final double n10 = MOViewerFrame.viewDistance * rotmatrix[8];
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
        if (adjustmentEvent.getSource() == this.separationBar) {
            if (this.separationBar.getValue() == this.sepValue) {
                return;
            }
            this.sepValue = this.separationBar.getValue();
            this.precomputeAll();
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
        final boolean selectedSlice = this.selectedSlice;
        this.selectedPaneHandle = -1;
        this.selection = 0;
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
        else if (this.viewPotential != null && this.viewPotential.contains(x, y)) {
            this.selection = 1;
        }
        if (selectedPaneHandle != this.selectedPaneHandle || selection != this.selection || selectedSlice != this.selectedSlice) {
            this.cv.repaint(this.pause);
        }
    }
    
    public void mouseClicked(final MouseEvent mouseEvent) {
    }
    
    public void mouseEntered(final MouseEvent mouseEvent) {
    }
    
    public void mouseExited(final MouseEvent mouseEvent) {
        if (!this.dragging && this.selection != 0) {
            this.selectedPaneHandle = -1;
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
        this.changingDerivedStates = b;
        this.dragging = b;
    }
    
    public void itemStateChanged(final ItemEvent itemEvent) {
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
        if (itemEvent.getItemSelectable() == this.stateChooser) {
            this.precomputeAll();
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
        boolean b = false;
        if (this.selectedSlice) {
            b = true;
        }
        if (!b) {
            this.rotate((this.dragX - n) / 40.0, -(this.dragY - n2) / 40.0);
            this.cv.repaint(this.pause);
            return;
        }
        if (b) {
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
    
    void createOrbitals() {
        if (this.orbCount == 11) {
            return;
        }
        this.orbCount = 11;
        (this.orbitals = new Orbital[this.orbCount])[0] = (Orbital)new SOrbital();
        this.orbitals[1] = (Orbital)new SOrbital();
        this.orbitals[2] = (Orbital)new MZeroOrbital(1);
        this.orbitals[3] = (Orbital)new MZeroOrbital(1);
        this.orbitals[4] = (Orbital)new ReImOrbital(1);
        this.orbitals[5] = (Orbital)new ReImOrbital(1);
        this.orbitals[6] = (Orbital)new SOrbital();
        this.orbitals[7] = (Orbital)new MZeroOrbital(1);
        this.orbitals[8] = (Orbital)new MZeroOrbital(2);
        this.orbitals[9] = (Orbital)new ReImOrbital(2);
        this.orbitals[10] = (Orbital)new MZeroOrbital(3);
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
        MOViewerFrame.maxModes = 10;
        MOViewerFrame.maxDispCoefs = 8;
        MOViewerFrame.viewDistance = 12;
    }
    
    abstract class Orbital
    {
        int l;
        int m;
        float reMult;
        float imMult;
        boolean used;
        float[] dataR;
        float[] dataTh;
        float[] dataPhiR;
        float[] dataPhiI;
        int dshalf;
        double brightnessCache;
        final int distmult = 4;
        
        void setupFrame(final double n) {
            this.reMult = 1.0f;
            this.imMult = 0.0f;
        }
        
        void setReal() {
        }
        
        void setIm() {
        }
        
        double getBoundRadius(final double n) {
            int n2 = 1;
            final double n3 = 1.0 / this.sphericalNorm(this.l, (this.m < 0) ? (-this.m) : this.m);
            final double n4 = n3 * n3 * n;
            for (int i = 0; i != MOViewerFrame.this.dataSize; ++i) {
                if (this.dataR[i] * this.dataR[i] * n4 > 32.0) {
                    n2 = i;
                }
            }
            return n2 / (MOViewerFrame.this.dataSize / 2.0);
        }
        
        double getScaleRadius() {
            final int n = 1;
            final double n2 = -n * n * 2;
            return 0.5 * (-n2 + Math.sqrt(n2 * n2 - 4.0 * (this.l * (this.l + 1) * n * n)));
        }
        
        void precompute() {
            this.dshalf = MOViewerFrame.this.dataSize / 2;
            MOViewerFrame.this.scaleBar.getValue();
            final int n = (this.m < 0) ? (-this.m) : this.m;
            final double pow = Math.pow(-1.0, this.m);
            this.dataR = new float[MOViewerFrame.this.dataSize];
            if (this.l > 0) {
                this.dataTh = new float[MOViewerFrame.this.dataSize + 1];
                for (int i = 0; i != MOViewerFrame.this.dataSize + 1; ++i) {
                    this.dataTh[i] = (float)(pow * MOViewerFrame.this.plgndr(this.l, n, (i - this.dshalf) / this.dshalf));
                }
            }
            if (this.m != 0) {
                this.dataPhiR = new float[8 * (MOViewerFrame.this.dataSize + 1)];
                this.dataPhiI = new float[8 * (MOViewerFrame.this.dataSize + 1)];
                int n2 = 0;
                for (int j = 0; j != 8; ++j) {
                    for (int k = 0; k <= MOViewerFrame.this.dataSize; ++k, ++n2) {
                        final double n3 = j * 3.141592653589793 / 4.0 + k * 0.7853981633974483 / MOViewerFrame.this.dataSize;
                        this.dataPhiR[n2] = (float)Math.cos(n3 * n);
                        this.dataPhiI[n2] = (float)Math.sin(n3 * n);
                    }
                }
            }
            this.brightnessCache = 0.0;
        }
        
        void precomputeR(final double n, final int n2, final double n3) {
            if (Math.abs(n3) < 0.06) {
                final MOViewerFrame this$0 = MOViewerFrame.this;
                ++this$0.precount;
                return;
            }
            this.used = true;
            final MOViewerFrame this$2 = MOViewerFrame.this;
            ++this$2.precount;
            this.dshalf = MOViewerFrame.this.dataSize / 2;
            final double n4 = MOViewerFrame.this.scaleBar.getValue() / 50.0;
            final double n5 = this.radialNorm(n2, this.l, n) * this.sphericalNorm(this.l, (this.m < 0) ? (-this.m) : this.m) * n3;
            for (int i = 0; i != MOViewerFrame.this.dataSize; ++i) {
                final double n6 = 2.0 * n * (i * MOViewerFrame.this.resadj + 1.0E-8) * n4 / n2;
                final double n7 = Math.pow(n6, this.l) * n5;
                final float[] dataR = this.dataR;
                final int n8 = i;
                dataR[n8] += (float)(MOViewerFrame.this.hypser(this.l + 1 - n2, 2 * this.l + 2, n6) * n7 * Math.exp(-n6 / 2.0));
            }
        }
        
        double getBrightness() {
            if (this.brightnessCache != 0.0) {
                return this.brightnessCache;
            }
            double n = 0.0;
            double n2 = 0.0;
            final double n3 = 1.0 / this.sphericalNorm(this.l, (this.m < 0) ? (-this.m) : this.m);
            for (int i = 0; i != MOViewerFrame.this.dataSize; ++i) {
                final double n4 = this.dataR[i] * n3;
                final double n5 = n4 * n4;
                n += n5 * n5 * i * i;
                n2 += i * i;
            }
            return this.brightnessCache = n / n2;
        }
        
        double radialNorm(final int n, final int n2, final double n3) {
            this.factorial(n + n2);
            return Math.sqrt(4.0 * n3 * n3 * n3 * this.factorial(n + n2) / (n * n * n * n * this.factorial(n - n2 - 1))) / this.factorial(2 * n2 + 1);
        }
        
        double sphericalNorm(final int n, final int n2) {
            return Math.sqrt((2 * n + 1) * this.factorial(n - n2) / (12.566370614359172 * this.factorial(n + n2)));
        }
        
        double factorial(int i) {
            double n;
            for (n = 1.0; i > 1; n *= i--) {}
            return n;
        }
        
        abstract float computePoint(final int p0, final int p1);
    }
    
    class SOrbital extends Orbital
    {
        float computePoint(final int n, final int n2) {
            try {
                return super.reMult * ((n < MOViewerFrame.this.dataSize) ? super.dataR[n] : 0.0f);
            }
            catch (Exception ex) {
                return 0.0f;
            }
        }
    }
    
    class MZeroOrbital extends Orbital
    {
        MZeroOrbital(final int l) {
            super.l = l;
        }
        
        float computePoint(final int n, final int n2) {
            try {
                return ((n < MOViewerFrame.this.dataSize) ? (super.dataR[n] * super.dataTh[n2]) : 0.0f) * super.reMult;
            }
            catch (Exception ex) {
                return 0.0f;
            }
        }
    }
    
    class ReImOrbital extends Orbital
    {
        float[] dataPhi;
        
        ReImOrbital(final int l) {
            super.l = l;
            super.m = 1;
        }
        
        void setReal() {
            this.dataPhi = super.dataPhiR;
        }
        
        void setIm() {
            this.dataPhi = super.dataPhiI;
        }
        
        float computePoint(final int n, final int n2) {
            try {
                final float n3 = this.dataPhi[MOViewerFrame.this.phiIndex];
                if (n < MOViewerFrame.this.dataSize) {
                    return super.dataR[n] * super.dataTh[n2] * n3 * 1.4142135f;
                }
                return 0.0f;
            }
            catch (Exception ex) {
                return 0.0f;
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
}
