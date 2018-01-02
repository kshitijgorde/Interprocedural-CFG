import java.awt.Rectangle;
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
import java.awt.Label;
import java.awt.MenuBar;
import java.awt.Component;
import java.awt.LayoutManager;
import java.awt.Color;
import java.awt.Scrollbar;
import java.awt.Choice;
import java.awt.MenuItem;
import java.awt.Menu;
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

class QuantumCircFrame extends Frame implements ComponentListener, ActionListener, AdjustmentListener, MouseMotionListener, MouseListener, ItemListener
{
    Thread engine;
    Dimension winSize;
    Image dbimage;
    Random random;
    int maxSampleCount;
    int maxDispPhasorsR;
    int maxDispPhasorsTh;
    int sampleCountR;
    int sampleCountTh;
    int modeCountR;
    int modeCountTh;
    int modeCountM;
    FFT fftTh;
    public static final double epsilon = 1.0E-5;
    public static final double epsilon2 = 0.003;
    static final int panePad = 4;
    Button groundButton;
    Button blankButton;
    Button normalizeButton;
    Button maximizeButton;
    Checkbox stoppedCheck;
    CheckboxMenuItem eCheckItem;
    CheckboxMenuItem xCheckItem;
    CheckboxMenuItem pCheckItem;
    CheckboxMenuItem lCheckItem;
    CheckboxMenuItem statesCheckItem;
    CheckboxMenuItem expectCheckItem;
    CheckboxMenuItem uncertaintyCheckItem;
    CheckboxMenuItem probCheckItem;
    CheckboxMenuItem probPhaseCheckItem;
    CheckboxMenuItem magPhaseCheckItem;
    CheckboxMenuItem alwaysNormItem;
    CheckboxMenuItem alwaysMaxItem;
    Menu waveFunctionMenu;
    MenuItem measureEItem;
    MenuItem measureLItem;
    MenuItem exitItem;
    Choice mouseChooser;
    Checkbox colorCheck;
    Scrollbar brightnessBar;
    Scrollbar speedBar;
    Scrollbar forceBar;
    Scrollbar resBar;
    Scrollbar phasorBar;
    Scrollbar pZoomBar;
    int pZoomBarValue;
    View viewPotential;
    View viewX;
    View viewP;
    View viewStates;
    View viewXMap;
    View viewPMap;
    View viewStatesMap;
    View viewL;
    View[] viewList;
    int viewCount;
    boolean editingFunc;
    boolean dragStop;
    double[][] magcoef;
    double[][] phasecoef;
    double[][] phasecoefcos;
    double[][] phasecoefsin;
    double[][] phasecoefadj;
    double[] angle1SinTab;
    double[] angle1CosTab;
    double[] angle2SinTab;
    double[] angle2CosTab;
    double[][] elevels;
    double[] xformbuf;
    double[] lzspectrum;
    static final double pi = 3.141592653589793;
    double step;
    double[][] func;
    double[][] funci;
    double[][] pfunc;
    double[][] pfunci;
    PhaseColor[][] phaseColors;
    PhaseColor whitePhaseColor;
    Color[] grayLevels;
    static final int phaseColorCount = 50;
    int[] xpoints;
    int[] ypoints;
    int[] floorValues;
    double[][][] xStates;
    double[][][] pStates;
    int selectedCoefX;
    int selectedCoefY;
    double selectedGridX;
    double selectedGridY;
    int selectedPaneHandle;
    static final int SEL_NONE = 0;
    static final int SEL_POTENTIAL = 1;
    static final int SEL_X = 2;
    static final int SEL_STATES = 3;
    static final int SEL_L = 4;
    static final int SEL_HANDLE = 5;
    static final int MOUSE_GAUSS = 0;
    static final int MOUSE_GAUSSP = 1;
    static final int MOUSE_ROTATE = 2;
    int selection;
    int dragX;
    int dragY;
    int dragStartX;
    int dragStartY;
    boolean dragSet;
    boolean dragClear;
    double viewZoom;
    double viewZoomDragStart;
    double scaleHeight;
    double viewHeight;
    double viewHeightDragStart;
    double viewDistance;
    double magDragStart;
    boolean dragging;
    double t;
    int pause;
    double scalex;
    double scaley;
    int centerX3d;
    int centerY3d;
    double topz;
    double brightmult;
    QuantumCircCanvas cv;
    QuantumCirc applet;
    boolean useBufferedImage;
    final int lspacing = 3;
    long lastTime;
    double lastGaussWx;
    double lastGaussWy;
    
    public String getAppletInfo() {
        return "QuantumCirc Series by Paul Falstad";
    }
    
    int getrand(final int n) {
        int nextInt = this.random.nextInt();
        if (nextInt < 0) {
            nextInt = -nextInt;
        }
        return nextInt % n;
    }
    
    QuantumCircFrame(final QuantumCirc applet) {
        super("Quantum Circular Box Applet v1.5");
        this.engine = null;
        this.maxSampleCount = 70;
        this.maxDispPhasorsR = 10;
        this.maxDispPhasorsTh = 21;
        this.viewZoom = 1.0;
        this.scaleHeight = 6.0;
        this.viewHeight = -14.0;
        this.topz = 3.0;
        this.useBufferedImage = false;
        this.lastGaussWx = -8.0;
        this.lastGaussWy = -8.0;
        this.applet = applet;
    }
    
    public void init() {
        if (new Double(System.getProperty("java.class.version")) >= 48.0) {
            this.useBufferedImage = true;
        }
        final int n = -1;
        this.selectedCoefY = n;
        this.selectedCoefX = n;
        this.setLayout(new QuantumCircLayout());
        (this.cv = new QuantumCircCanvas(this)).addComponentListener(this);
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
        menu2.add(this.pCheckItem = this.getCheckItem("Linear Momentum"));
        menu2.add(this.lCheckItem = this.getCheckItem("Angular Momentum"));
        menu2.add(this.statesCheckItem = this.getCheckItem("State Phasors"));
        this.statesCheckItem.setState(true);
        menu2.addSeparator();
        menu2.add(this.expectCheckItem = this.getCheckItem("Expectation Values"));
        menu2.add(this.uncertaintyCheckItem = this.getCheckItem("Uncertainties"));
        final Menu waveFunctionMenu = new Menu("Wave Function");
        this.waveFunctionMenu = waveFunctionMenu;
        final Menu menu3 = waveFunctionMenu;
        menu2.add(menu3);
        menu3.add(this.probCheckItem = this.getCheckItem("Probability"));
        menu3.add(this.probPhaseCheckItem = this.getCheckItem("Probability + Phase"));
        menu3.add(this.magPhaseCheckItem = this.getCheckItem("Magnitude + Phase"));
        this.magPhaseCheckItem.setState(true);
        final Menu menu4 = new Menu("Measure");
        menuBar.add(menu4);
        menu4.add(this.measureEItem = this.getMenuItem("Measure Energy"));
        menu4.add(this.measureLItem = this.getMenuItem("Measure Angular Momentum"));
        this.setMenuBar(menuBar);
        final Menu menu5 = new Menu("Options");
        menuBar.add(menu5);
        menu5.add(this.alwaysNormItem = this.getCheckItem("Always Normalize"));
        menu5.add(this.alwaysMaxItem = this.getCheckItem("Always Maximize"));
        this.alwaysMaxItem.setState(true);
        this.setMenuBar(menuBar);
        (this.mouseChooser = new Choice()).add("Mouse = Create Gaussian");
        this.mouseChooser.add("Mouse = Gaussian w/ Momentum");
        this.mouseChooser.add("Mouse = Rotate Function");
        this.mouseChooser.addItemListener(this);
        this.add(this.mouseChooser);
        this.mouseChooser.select(0);
        this.add(this.blankButton = new Button("Clear"));
        this.blankButton.addActionListener(this);
        this.add(this.normalizeButton = new Button("Normalize"));
        this.normalizeButton.addActionListener(this);
        this.add(this.maximizeButton = new Button("Maximize"));
        this.maximizeButton.addActionListener(this);
        this.add(this.groundButton = new Button("Ground State"));
        this.groundButton.addActionListener(this);
        (this.stoppedCheck = new Checkbox("Stopped")).addItemListener(this);
        this.add(this.stoppedCheck);
        this.add(new Label("Simulation Speed", 1));
        this.add(this.speedBar = new Scrollbar(0, 105, 1, 1, 300));
        this.speedBar.addAdjustmentListener(this);
        this.add(new Label("Brightness", 1));
        this.add(this.brightnessBar = new Scrollbar(0, 980, 1, 700, 2000));
        this.brightnessBar.addAdjustmentListener(this);
        this.add(new Label("Resolution", 1));
        this.add(this.resBar = new Scrollbar(0, 16, 1, 2, this.maxSampleCount / 2));
        this.resBar.addAdjustmentListener(this);
        this.add(new Label("Momentum Zoom", 1));
        this.add(this.pZoomBar = new Scrollbar(0, 166, 1, 45, 260));
        this.pZoomBar.addAdjustmentListener(this);
        this.add(new Label("Phasor Count", 1));
        this.add(this.phasorBar = new Scrollbar(0, 10, 1, 5, this.maxSampleCount / 2));
        this.phasorBar.addAdjustmentListener(this);
        this.setResolution();
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
        this.whitePhaseColor = new PhaseColor(1.0, 1.0, 1.0);
        this.grayLevels = new Color[256];
        for (int k = 0; k != 256; ++k) {
            this.grayLevels[k] = new Color(k, k, k);
        }
        this.random = new Random();
        this.reinit();
        this.cv.setBackground(Color.black);
        this.cv.setForeground(Color.lightGray);
        this.resize(640, 600);
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
    
    void reinit() {
        this.doBlank();
        this.magcoef[1][0] = 1.0;
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
        if (this.winSize == null) {
            return;
        }
        final int n = (this.viewPotential == null) ? 50 : this.viewPotential.height;
        final int n2 = (this.viewStates == null) ? 150 : this.viewStates.height;
        final View viewX = null;
        this.viewStates = viewX;
        this.viewPotential = viewX;
        this.viewP = viewX;
        this.viewL = viewX;
        this.viewX = viewX;
        this.viewList = new View[10];
        int viewCount = 0;
        if (this.eCheckItem.getState()) {
            this.viewList[viewCount++] = (this.viewPotential = new View());
        }
        if (this.xCheckItem.getState()) {
            this.viewList[viewCount++] = (this.viewX = new View());
        }
        if (this.pCheckItem.getState()) {
            this.viewList[viewCount++] = (this.viewP = new View());
        }
        if (this.lCheckItem.getState()) {
            this.viewList[viewCount++] = (this.viewL = new View());
        }
        if (this.statesCheckItem.getState()) {
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
            final View view = this.viewList[i];
            int height2 = n3 / viewCount2;
            if (view == this.viewPotential && n > 0) {
                height2 = n;
            }
            else if (view == this.viewStates && n2 > 0) {
                height2 = n2;
            }
            if ((view.paneY = n4) > 0) {
                n4 += 4;
            }
            view.x = 0;
            view.width = this.winSize.width;
            view.y = n4;
            view.height = height2;
            n4 += height2 + 4;
        }
        this.setSubViews();
    }
    
    void setSubViews() {
        this.viewXMap = null;
        this.viewStatesMap = null;
        if (this.viewStates != null) {
            this.viewStatesMap = new View(this.viewStates);
            final double n = this.viewStates.width / this.viewStates.height;
            final double n2 = this.modeCountTh / this.modeCountR;
            int width;
            if (n2 > n) {
                width = this.viewStates.width - 2;
            }
            else {
                width = (int)((this.viewStates.height - 2) * n2);
            }
            final View viewStatesMap = this.viewStatesMap;
            viewStatesMap.x += (this.viewStatesMap.width - width) / 2 + 1;
            this.viewStatesMap.width = width;
        }
        if (this.viewX != null) {
            this.processMap(this.viewXMap = new View(this.viewX));
        }
        if (this.viewP != null) {
            this.processMap(this.viewPMap = new View(this.viewP));
        }
        if (this.viewL != null) {
            final View viewL = this.viewL;
            viewL.mid_y = viewL.y + viewL.height / 2;
            viewL.ymult = 0.9 * viewL.height / 2.0;
            viewL.lower_y = (int)(viewL.mid_y + viewL.ymult);
            viewL.ymult2 = viewL.ymult * 2.0;
        }
        this.floorValues = null;
    }
    
    void processMap(final View view) {
        int width;
        int height;
        if (1.0 > view.width / view.height) {
            height = (width = view.width - 2);
        }
        else {
            height = (width = view.height - 2);
        }
        view.x += (view.width - width) / 2 + 1;
        view.y += (view.height - height) / 2 + 1;
        view.width = width;
        view.height = height;
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
    
    int min(final int n, final int n2) {
        return (n < n2) ? n : n2;
    }
    
    void doGround() {
        this.doBlank();
        this.magcoef[0][0] = 1.0;
        this.t = 0.0;
    }
    
    void normalize() {
        double n = 0.0;
        for (int i = 0; i != this.modeCountTh; ++i) {
            for (int j = 0; j != this.modeCountR; ++j) {
                n += this.magcoef[i][j] * this.magcoef[i][j];
            }
        }
        if (n == 0.0) {
            return;
        }
        final double n2 = 1.0 / Math.sqrt(n);
        for (int k = 0; k != this.modeCountTh; ++k) {
            for (int l = 0; l != this.modeCountR; ++l) {
                final double[] array = this.magcoef[k];
                final int n3 = l;
                array[n3] *= n2;
            }
        }
        this.cv.repaint(this.pause);
    }
    
    void maximize() {
        double abs = 0.0;
        for (int i = 0; i != this.modeCountTh; ++i) {
            for (int j = 0; j != this.modeCountR; ++j) {
                if (Math.abs(this.magcoef[i][j]) > abs) {
                    abs = Math.abs(this.magcoef[i][j]);
                }
            }
        }
        if (abs == 0.0) {
            return;
        }
        for (int k = 0; k != this.modeCountTh; ++k) {
            for (int l = 0; l != this.modeCountR; ++l) {
                final double[] array = this.magcoef[k];
                final int n = l;
                array[n] *= 1.0 / abs;
            }
        }
        this.cv.repaint(this.pause);
    }
    
    void measureE() {
        this.normalize();
        double nextDouble = this.random.nextDouble();
        int n = -1;
        int n2 = -1;
        for (int i = 0; i < this.modeCountTh; ++i) {
            for (int j = 0; j < this.modeCountR; ++j) {
                nextDouble -= this.magcoef[i][j] * this.magcoef[i][j];
                if (nextDouble < 0.0) {
                    n = i;
                    n2 = j;
                    i = 10000;
                    break;
                }
            }
        }
        if (n == -1) {
            return;
        }
        for (int k = 0; k != this.modeCountTh; ++k) {
            for (int l = 0; l != this.modeCountR; ++l) {
                if (this.elevels[k][l] != this.elevels[n][n2]) {
                    this.magcoef[k][l] = 0.0;
                }
            }
        }
        if (this.alwaysNormItem.getState()) {
            this.normalize();
        }
        else {
            this.maximize();
        }
    }
    
    void calcLSpectrum() {
        final int n = this.modeCountTh * 3;
        if (this.lzspectrum == null) {
            this.lzspectrum = new double[n];
        }
        for (int i = 0; i != n; ++i) {
            this.lzspectrum[i] = 0.0;
        }
        final int n2 = n / 2;
        for (int j = 0; j != this.modeCountTh; ++j) {
            final int n3 = (j % 2 == 0) ? (j / 2 * 3 + n2) : (n2 - 3 * (j + 1) / 2);
            for (int k = 0; k != this.modeCountR; ++k) {
                final double[] lzspectrum = this.lzspectrum;
                final int n4 = n3;
                lzspectrum[n4] += this.magcoef[j][k] * this.magcoef[j][k];
            }
        }
    }
    
    void measureL() {
        this.normalize();
        this.calcLSpectrum();
        double nextDouble = this.random.nextDouble();
        int n = -1;
        final int n2 = this.modeCountTh * 3;
        for (int i = 0; i != n2; ++i) {
            nextDouble -= this.lzspectrum[i];
            if (nextDouble < 0.0) {
                n = i;
                break;
            }
        }
        if (n == -1) {
            return;
        }
        final int n3 = n2 / 2;
        for (int j = 0; j != this.modeCountTh; ++j) {
            for (int k = 0; k != this.modeCountR; ++k) {
                if (((j % 2 == 0) ? (j / 2 * 3 + n3) : (n3 - 3 * (j + 1) / 2)) != n) {
                    this.magcoef[j][k] = 0.0;
                }
            }
        }
        if (this.alwaysNormItem.getState()) {
            this.normalize();
        }
        else {
            this.maximize();
        }
    }
    
    void doBlank() {
        for (int i = 0; i != this.modeCountTh; ++i) {
            for (int j = 0; j != this.modeCountR; ++j) {
                this.magcoef[i][j] = 0.0;
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
    
    public void updateQuantumCirc(final Graphics graphics) {
        final Graphics graphics2 = this.dbimage.getGraphics();
        if (this.winSize == null || this.winSize.width == 0 || this.dbimage == null) {
            return;
        }
        boolean b = true;
        if (!this.stoppedCheck.getState() && !this.dragging) {
            final double n = Math.exp(this.speedBar.getValue() / 20.0) * 0.02;
            final long currentTimeMillis = System.currentTimeMillis();
            if (this.lastTime == 0L) {
                this.lastTime = currentTimeMillis;
            }
            this.t += n * ((currentTimeMillis - this.lastTime) * 0.0058823529411764705);
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
        for (int i = 1; i != this.viewCount; ++i) {
            graphics2.setColor((i == this.selectedPaneHandle) ? Color.yellow : Color.gray);
            graphics2.drawLine(0, this.viewList[i].paneY, this.winSize.width, this.viewList[i].paneY);
        }
        if (this.dragStop) {
            this.t = 0.0;
        }
        double n2 = 0.0;
        double sqrt = 0.0;
        double n3 = 0.0;
        if (!this.editingFunc) {
            for (int j = 0; j != this.modeCountTh; ++j) {
                for (int k = 0; k != this.modeCountR; ++k) {
                    if (this.magcoef[j][k] < 1.0E-5 && this.magcoef[j][k] > -1.0E-5) {
                        final double[] array = this.magcoef[j];
                        final int n4 = k;
                        final double[] array2 = this.phasecoef[j];
                        final int n5 = k;
                        final double[] array3 = this.phasecoefadj[j];
                        final int n6 = k;
                        final double n7 = 0.0;
                        array3[n6] = n7;
                        array[n4] = (array2[n5] = n7);
                    }
                    else {
                        b = false;
                        this.phasecoef[j][k] = (-this.elevels[j][k] * this.t + this.phasecoefadj[j][k]) % 6.283185307179586;
                        this.phasecoefcos[j][k] = Math.cos(this.phasecoef[j][k]);
                        this.phasecoefsin[j][k] = Math.sin(this.phasecoef[j][k]);
                        n2 += this.magcoef[j][k] * this.magcoef[j][k];
                    }
                }
            }
            n3 = 1.0 / n2;
            if (n2 == 0.0) {
                n3 = 0.0;
            }
            sqrt = Math.sqrt(n3);
            this.genFunc(sqrt, true);
        }
        this.brightmult = Math.exp(this.brightnessBar.getValue() / 200.0 - 5.0);
        if (n2 == 0.0) {
            n3 = (sqrt = 0.0);
        }
        final int n8 = this.sampleCountTh / 2;
        this.xpoints = new int[4];
        this.ypoints = new int[4];
        if (this.viewPotential != null) {
            final int n9 = this.viewPotential.y + this.viewPotential.height - 5;
            final double n10 = 200.0;
            if (this.floorValues == null) {
                this.floorValues = new int[n9 + 1];
            }
            for (int l = 0; l <= n9; ++l) {
                this.floorValues[l] = 0;
            }
            for (int n11 = 0; n11 != this.modeCountTh; ++n11) {
                for (int n12 = 0; n12 != this.modeCountR; ++n12) {
                    final double n13 = this.elevels[n11][n12];
                    final int n14 = (int)(224.0 * (this.magcoef[n11][n12] * this.magcoef[n11][n12])) + 1;
                    final int n15 = n9 - (int)(n10 * n13);
                    if (n15 >= 0 && n15 <= n9) {
                        final int[] floorValues = this.floorValues;
                        final int n16 = n15;
                        floorValues[n16] += n14;
                    }
                }
            }
            for (int n17 = 0; n17 <= n9; ++n17) {
                if (this.floorValues[n17] != 0) {
                    int n18 = this.floorValues[n17] + 32;
                    if (n18 > 255) {
                        n18 = 255;
                    }
                    graphics2.setColor(this.grayLevels[n18]);
                    graphics2.drawLine(0, n17, this.winSize.width, n17);
                }
            }
            graphics2.setColor(Color.white);
            graphics2.drawLine(this.viewXMap.x, 0, this.viewXMap.x, n9);
            final int n19 = this.viewXMap.x + this.viewXMap.width;
            graphics2.drawLine(n19, 0, n19, n9);
            graphics2.drawLine(this.viewXMap.x, n9, n19, n9);
            if (n2 != 0.0 && (this.expectCheckItem.getState() || this.uncertaintyCheckItem.getState())) {
                double n20 = 0.0;
                double n21 = 0.0;
                for (int n22 = 0; n22 != this.modeCountTh; ++n22) {
                    for (int n23 = 0; n23 != this.modeCountR; ++n23) {
                        final double n24 = this.magcoef[n22][n23] * this.magcoef[n22][n23] * n3;
                        n20 += n24 * this.elevels[n22][n23];
                        n21 += n24 * this.elevels[n22][n23] * this.elevels[n22][n23];
                    }
                }
                double sqrt2 = Math.sqrt(n21 - n20 * n20);
                if (this.uncertaintyCheckItem.getState()) {
                    if (sqrt2 < 0.0) {
                        sqrt2 = 0.0;
                    }
                    graphics2.setColor(Color.blue);
                    final int n25 = n9 - (int)(n10 * (n20 + sqrt2));
                    graphics2.drawLine(0, n25, this.winSize.width, n25);
                    final int n26 = n9 - (int)(n10 * (n20 - sqrt2));
                    if (n20 - sqrt2 >= 0.0) {
                        graphics2.drawLine(0, n26, this.winSize.width, n26);
                    }
                }
                if (this.expectCheckItem.getState()) {
                    final int n27 = n9 - (int)(n10 * n20);
                    graphics2.setColor(Color.red);
                    graphics2.drawLine(0, n27, this.winSize.width, n27);
                }
            }
            if (this.selectedCoefX != -1 && !this.dragging) {
                graphics2.setColor(Color.yellow);
                final int n28 = n9 - (int)(n10 * this.elevels[this.selectedCoefX][this.selectedCoefY]);
                graphics2.drawLine(0, n28, this.winSize.width, n28);
            }
        }
        if (this.viewX != null) {
            this.drawRadial(graphics2, this.viewXMap, this.func, this.funci);
        }
        if (this.viewP != null) {
            this.genFunc(sqrt, false);
            this.drawRadial(graphics2, this.viewPMap, this.pfunc, this.pfunci);
        }
        if (this.viewL != null) {
            final int n29 = this.modeCountTh * 3;
            this.calcLSpectrum();
            for (int n30 = 0; n30 != n29; ++n30) {
                this.lzspectrum[n30] = Math.sqrt(this.lzspectrum[n30]);
            }
            this.drawFunction(graphics2, this.viewL, this.lzspectrum, null, n29, 0);
        }
        if (this.viewStatesMap != null) {
            final int termWidth;
            final int n31 = termWidth = this.getTermWidth();
            final int n32 = n31 / 2;
            for (int n33 = 0; n33 < this.modeCountTh && n33 < this.maxDispPhasorsTh; ++n33) {
                for (int n34 = 0; n34 < this.modeCountR && n34 < this.maxDispPhasorsR; ++n34) {
                    final int n35 = this.viewStatesMap.x + n33 * n31 + n32;
                    final int n36 = this.viewStatesMap.y + n34 * n31 + n32;
                    graphics2.setColor((this.selectedCoefX != -1 && this.elevels[this.selectedCoefX][this.selectedCoefY] == this.elevels[n33][n34]) ? Color.yellow : ((this.magcoef[n33][n34] == 0.0) ? color2 : Color.white));
                    graphics2.drawOval(n35 - n32, n36 - n32, termWidth, termWidth);
                    final int n37 = (int)(this.magcoef[n33][n34] * this.phasecoefcos[n33][n34] * n32);
                    final int n38 = (int)(-this.magcoef[n33][n34] * this.phasecoefsin[n33][n34] * n32);
                    graphics2.drawLine(n35, n36, n35 + n37, n36 + n38);
                    graphics2.drawLine(n35 + n37 - 1, n36 + n38, n35 + n37 + 1, n36 + n38);
                    graphics2.drawLine(n35 + n37, n36 + n38 - 1, n35 + n37, n36 + n38 + 1);
                }
            }
            graphics2.setColor(Color.white);
        }
        if (this.selectedCoefX != -1) {
            graphics2.setColor(Color.yellow);
            int n39 = (this.selectedCoefX + 1) / 2;
            if ((this.selectedCoefX & 0x1) != 0x0) {
                n39 = -n39;
            }
            if (this.viewStatesMap != null && this.viewX != null) {
                this.centerString(graphics2, "nr = " + this.selectedCoefY + ", m = " + n39, this.viewX.y + this.viewX.height - 10);
            }
            if (this.viewL != null) {
                final int n40 = this.modeCountTh * 3;
                final int n41 = this.viewL.width * (n39 * 3 + n40 / 2) / (n40 - 1);
                graphics2.drawLine(n41, this.viewL.y, n41, this.viewL.y + this.viewL.height);
            }
        }
        graphics.drawImage(this.dbimage, 0, 0, this);
        if (this.dragStop) {
            b = true;
        }
        if (!this.stoppedCheck.getState() && !b) {
            this.cv.repaint(this.pause);
        }
    }
    
    void drawRadial(final Graphics graphics, final View view, final double[][] array, final double[][] array2) {
        final int n = view.width / 2;
        final int n2 = view.height / 2;
        final int n3 = view.width / 2;
        double n4 = 0.0;
        double n5 = 0.0;
        double n6 = 0.0;
        double n7 = 0.0;
        double n8 = 0.0;
        double n9 = 0.0;
        for (int i = 0; i <= this.sampleCountR; ++i) {
            for (int j = 0; j != this.sampleCountTh; ++j) {
                final double n10 = array[j][i];
                final double n11 = array2[j][i];
                double sqrt = n10 * n10 + n11 * n11;
                final double n12 = i * this.angle1CosTab[j];
                final double n13 = i * this.angle1SinTab[j];
                n5 += sqrt * i * n12;
                n7 += sqrt * i * n13;
                n6 += sqrt * i * n12 * n12;
                n8 += sqrt * i * n13 * n13;
                n9 += sqrt * i;
                if (this.magPhaseCheckItem.getState()) {
                    sqrt = Math.sqrt(sqrt);
                }
                if (sqrt > n4) {
                    n4 = sqrt;
                }
            }
        }
        final double n14 = n5 / n9;
        final double n15 = n7 / n9;
        final double n16 = n6 / n9;
        final double n17 = n8 / n9;
        final double n18 = 255.0 * this.brightmult / n4;
        final double n19 = -n3 / this.sampleCountR;
        for (int k = 0; k != this.sampleCountR; ++k) {
            final double n20 = n19 * k;
            final double n21 = n19 * (k + 1);
            this.xpoints[0] = (int)(n + n20);
            this.ypoints[0] = n2;
            this.xpoints[3] = (int)(n + n21);
            this.ypoints[3] = n2;
            for (int l = 0; l != this.sampleCountTh; ++l) {
                final double n22 = array[l][k];
                final double n23 = array2[l][k];
                double sqrt2 = n22 * n22 + n23 * n23;
                if (this.magPhaseCheckItem.getState()) {
                    sqrt2 = Math.sqrt(sqrt2);
                }
                double n24 = sqrt2 * n18;
                final PhaseColor phaseColor = this.getPhaseColor(n22, n23);
                if (n24 > 255.0) {
                    n24 = 255.0;
                }
                final int n25 = 0xFF000000 | (int)(phaseColor.r * n24) << 16 | (int)(phaseColor.g * n24) << 8 | (int)(phaseColor.b * n24);
                graphics.setColor(new Color(n25));
                this.xpoints[1] = (int)(n + n20 * this.angle2CosTab[l]);
                this.ypoints[1] = (int)(n2 - n20 * this.angle2SinTab[l]);
                this.xpoints[2] = (int)(n + n21 * this.angle2CosTab[l]);
                this.ypoints[2] = (int)(n2 - n21 * this.angle2SinTab[l]);
                this.fillTriangle(view, this.xpoints[0], this.ypoints[0], this.xpoints[1], this.ypoints[1], this.xpoints[2], this.ypoints[2], n25);
                this.fillTriangle(view, this.xpoints[0], this.ypoints[0], this.xpoints[2], this.ypoints[2], this.xpoints[3], this.ypoints[3], n25);
                this.xpoints[0] = this.xpoints[1];
                this.ypoints[0] = this.ypoints[1];
                this.xpoints[3] = this.xpoints[2];
                this.ypoints[3] = this.ypoints[2];
            }
        }
        if (view.imageSource != null) {
            view.imageSource.newPixels();
        }
        graphics.drawImage(view.memimage, view.x, view.y, null);
        final int n26 = n + view.x;
        final int n27 = n2 + view.y;
        if (this.expectCheckItem.getState()) {
            final int n28 = (int)(n26 + n14 * n19);
            final int n29 = (int)(n27 - n15 * n19);
            graphics.setColor(Color.red);
            graphics.drawLine(n28, view.y, n28, view.y + view.height);
            graphics.drawLine(view.x, n29, view.x + view.width, n29);
        }
        if (this.uncertaintyCheckItem.getState()) {
            final double sqrt3 = Math.sqrt(n16 - n14 * n14);
            final double sqrt4 = Math.sqrt(n17 - n15 * n15);
            final int n30 = (int)(n26 + (n14 + sqrt3) * n19);
            final int n31 = (int)(n26 + (n14 - sqrt3) * n19);
            final int n32 = (int)(n27 - (n15 - sqrt4) * n19);
            final int n33 = (int)(n27 - (n15 + sqrt4) * n19);
            graphics.setColor(Color.blue);
            graphics.drawRect(n30, n32, n31 - n30, n33 - n32);
        }
        graphics.setColor(Color.white);
        graphics.drawOval(view.x, view.y, view.width, view.height);
    }
    
    void fillTriangle(final View view, final int n, final int n2, final int n3, final int n4, final int n5, final int n6, final int n7) {
        if (n > n3) {
            if (n3 > n5) {
                final int interp = this.interp(n, n2, n5, n6, n3);
                this.fillTriangle1(view, n5, n6, n3, n4, interp, n7);
                this.fillTriangle1(view, n, n2, n3, n4, interp, n7);
            }
            else if (n > n5) {
                final int interp2 = this.interp(n, n2, n3, n4, n5);
                this.fillTriangle1(view, n3, n4, n5, n6, interp2, n7);
                this.fillTriangle1(view, n, n2, n5, n6, interp2, n7);
            }
            else {
                final int interp3 = this.interp(n5, n6, n3, n4, n);
                this.fillTriangle1(view, n3, n4, n, n2, interp3, n7);
                this.fillTriangle1(view, n5, n6, n, n2, interp3, n7);
            }
        }
        else if (n > n5) {
            final int interp4 = this.interp(n3, n4, n5, n6, n);
            this.fillTriangle1(view, n5, n6, n, n2, interp4, n7);
            this.fillTriangle1(view, n3, n4, n, n2, interp4, n7);
        }
        else if (n3 > n5) {
            final int interp5 = this.interp(n3, n4, n, n2, n5);
            this.fillTriangle1(view, n, n2, n5, n6, interp5, n7);
            this.fillTriangle1(view, n3, n4, n5, n6, interp5, n7);
        }
        else {
            final int interp6 = this.interp(n5, n6, n, n2, n3);
            this.fillTriangle1(view, n, n2, n3, n4, interp6, n7);
            this.fillTriangle1(view, n5, n6, n3, n4, interp6, n7);
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
    
    void fillTriangle1(final View view, final int n, final int n2, final int n3, int n4, int n5, final int n6) {
        final int n7 = (n > n3) ? -1 : 1;
        int i = n;
        if (i < 0) {
            i = 0;
            if (n3 < 0) {
                return;
            }
        }
        if (i >= view.width) {
            i = view.width - 1;
            if (n3 >= view.width) {
                return;
            }
        }
        if (n4 > n5) {
            final int n8 = n4;
            n4 = n5;
            n5 = n8;
        }
        while (i != n3 + n7) {
            int interp = this.interp(n, n2, n3, n4, i);
            int interp2 = this.interp(n, n2, n3, n5, i);
            if (interp < 0) {
                interp = 0;
            }
            if (interp2 >= view.height) {
                interp2 = view.height - 1;
            }
            for (int j = i + interp * view.width; j <= i + interp2 * view.width; j += view.width) {
                view.pixels[j] = n6;
            }
            i += n7;
            if (i < 0 || i >= view.width) {
                return;
            }
        }
    }
    
    void drawFunction(final Graphics graphics, final View view, final double[] array, final double[] array2, final int n, final int n2) {
        double n3 = 0.0;
        double n4 = 0.0;
        double n5 = 0.0;
        double n6 = 0.0;
        final int n7 = this.winSize.width / 2;
        for (int i = 0; i != n; ++i) {
            final int n8 = this.winSize.width * i / (n - 1);
            final int n9 = i + n2;
            final double n10 = array[n9];
            final double n11 = (array2 == null) ? 0.0 : array2[n9];
            final double n12 = n10 * n10 + n11 * n11;
            if (n12 > n5) {
                n5 = n12;
            }
            final int n13 = n8 - n7;
            n3 += n12 * n13;
            n4 += n12 * n13 * n13;
            n6 += n12;
        }
        final double n14 = n3 / n6;
        final double n15 = n4 / n6;
        final double sqrt = Math.sqrt(n5);
        final double sqrt2 = Math.sqrt(n15 - n14 * n14);
        int n16 = -1;
        int n17 = 0;
        double scale;
        if (array2 != null && (this.probCheckItem.getState() || this.probPhaseCheckItem.getState())) {
            scale = 1.0 / n5;
        }
        else {
            scale = 1.0 / sqrt;
        }
        view.scale = scale;
        if (view.scale > 1.0E8) {
            view.scale = 1.0E8;
        }
        graphics.setColor(Color.gray);
        final int n18 = this.winSize.width * (n / 2) / (n - 1);
        graphics.drawLine(n18, view.y, n18, view.y + view.height);
        final int lower_y = view.lower_y;
        final double n19 = view.ymult2 * view.scale;
        if (array2 != null) {
            graphics.setColor(Color.blue);
            for (int j = 0; j != n; ++j) {
                final int n20 = this.winSize.width * j / (n - 1);
                final int n21 = lower_y - (int)(n19 * array2[j + n2]);
                if (n16 != -1) {
                    graphics.drawLine(n16, n17, n20, n21);
                }
                n16 = n20;
                n17 = n21;
            }
        }
        graphics.setColor(Color.white);
        int n22 = -1;
        for (int k = 0; k != n; ++k) {
            final int n23 = this.winSize.width * k / (n - 1);
            final int n24 = lower_y - (int)(n19 * array[k + n2]);
            if (n22 != -1) {
                graphics.drawLine(n22, n17, n23, n24);
            }
            n22 = n23;
            n17 = n24;
        }
        if (n5 > 0.0) {
            final double n25 = n14 + n7;
            if (this.uncertaintyCheckItem.getState()) {
                graphics.setColor(Color.blue);
                graphics.drawLine((int)(n25 - sqrt2), view.y, (int)(n25 - sqrt2), view.y + view.height);
                graphics.drawLine((int)(n25 + sqrt2), view.y, (int)(n25 + sqrt2), view.y + view.height);
            }
            if (this.expectCheckItem.getState()) {
                graphics.setColor(Color.red);
                graphics.drawLine((int)n25, view.y, (int)n25, view.y + view.height);
            }
        }
    }
    
    Color computeColor(final int n, final int n2, double n3) {
        double n4 = this.func[n][n2];
        if (!this.colorCheck.getState()) {
            n4 = 0.0;
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
        final double n7 = (1.0 - (n5 + n6)) * n3;
        final double n8 = 0.6;
        return new Color((int)((n3 * n5 + n8 * n7) * 255.0), (int)((n3 * n6 + n8 * n7) * 255.0), (int)(n8 * n7 * 255.0));
    }
    
    void genFunc(final double n, final boolean b) {
        final int n2 = this.sampleCountTh * 2;
        final int n3 = n2 - 1;
        final double[][][] array = b ? this.xStates : this.pStates;
        final double[][] array2 = b ? this.func : this.pfunc;
        final double[][] array3 = b ? this.funci : this.pfunci;
        for (int i = 0; i <= this.sampleCountR; ++i) {
            for (int j = 0; j != n2; ++j) {
                this.xformbuf[j] = 0.0;
            }
            double n4 = 0.0;
            double n5 = 0.0;
            for (int k = 0; k != this.modeCountR; ++k) {
                n4 += array[0][k][i] * this.magcoef[0][k] * this.phasecoefcos[0][k];
                n5 += array[0][k][i] * this.magcoef[0][k] * this.phasecoefsin[0][k];
            }
            this.xformbuf[0] = n4;
            this.xformbuf[1] = n5;
            for (int l = 1; l < this.modeCountTh; l += 2) {
                double n6 = 0.0;
                double n7 = 0.0;
                double n8 = 0.0;
                double n9 = 0.0;
                final int n10 = (l + 1) / 2;
                for (int n11 = 0; n11 != this.modeCountR; ++n11) {
                    n6 += array[n10][n11][i] * this.magcoef[l][n11] * this.phasecoefcos[l][n11];
                    n8 += array[n10][n11][i] * this.magcoef[l][n11] * this.phasecoefsin[l][n11];
                    n7 += array[n10][n11][i] * this.magcoef[l + 1][n11] * this.phasecoefcos[l + 1][n11];
                    n9 += array[n10][n11][i] * this.magcoef[l + 1][n11] * this.phasecoefsin[l + 1][n11];
                }
                if (!b) {
                    final double n12 = 1.5707963267948966 * n10;
                    final double cos = Math.cos(n12);
                    final double sin = Math.sin(n12);
                    final double n13 = n6;
                    final double n14 = n8;
                    n6 = n13 * cos + n14 * sin;
                    n8 = -n13 * sin + n14 * cos;
                    final double n15 = n7;
                    final double n16 = n9;
                    n7 = n15 * cos + n16 * sin;
                    n9 = -n15 * sin + n16 * cos;
                }
                this.xformbuf[n10 * 2] = n7;
                this.xformbuf[n10 * 2 + 1] = n9;
                this.xformbuf[n3 & n2 - n10 * 2] = n6;
                this.xformbuf[n3 & n2 - n10 * 2 + 1] = n8;
            }
            this.fftTh.transform(this.xformbuf);
            for (int n17 = 0; n17 != this.sampleCountTh; ++n17) {
                array2[n17][i] = this.xformbuf[n17 * 2] * n;
                array3[n17][i] = this.xformbuf[n17 * 2 + 1] * n;
            }
            array2[this.sampleCountTh][i] = array2[0][i];
            array3[this.sampleCountTh][i] = array3[0][i];
        }
    }
    
    PhaseColor getPhaseColor(final double n, final double n2) {
        if (this.probCheckItem.getState()) {
            return this.whitePhaseColor;
        }
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
    
    int getTermWidth() {
        final int n = this.viewStatesMap.width / this.min(this.modeCountTh, this.maxDispPhasorsTh);
        final int n2 = this.viewStatesMap.height / this.min(this.modeCountR, this.maxDispPhasorsR);
        return (n < n2) ? n : n2;
    }
    
    void edit(final MouseEvent mouseEvent) {
        if (this.selection == 0) {
            return;
        }
        final int x = mouseEvent.getX();
        final int y = mouseEvent.getY();
        switch (this.selection) {
            case 5: {
                this.editHandle(y);
                break;
            }
            case 3: {
                this.editMag(x, y);
                break;
            }
            case 1: {
                this.findStateByEnergy(y);
                this.enterSelectedState();
                break;
            }
            case 2: {
                this.editX(x, y);
                break;
            }
            case 4: {
                this.editL(x, y);
                break;
            }
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
    
    void editMag(int n, int n2) {
        if (this.selectedCoefX == -1) {
            return;
        }
        final int termWidth = this.getTermWidth();
        final int n3 = termWidth / 2;
        final int n4 = termWidth * this.selectedCoefX + n3 + this.viewStatesMap.x;
        final int n5 = termWidth * this.selectedCoefY + n3 + this.viewStatesMap.y;
        n -= n4;
        n2 -= n5;
        double n6 = Math.sqrt(n * n + n2 * n2) / n3;
        final double atan2 = Math.atan2(-n2, n);
        final double n7 = -this.elevels[this.selectedCoefX][this.selectedCoefY] * this.t % 6.283185307179586;
        if (n6 > 10.0) {
            n6 = 0.0;
        }
        if (n6 > 1.0) {
            n6 = 1.0;
        }
        this.magcoef[this.selectedCoefX][this.selectedCoefY] = n6;
        this.phasecoefadj[this.selectedCoefX][this.selectedCoefY] = (atan2 - n7) % 6.283185307179586;
        if (this.phasecoefadj[this.selectedCoefX][this.selectedCoefY] > 3.141592653589793) {
            final double[] array = this.phasecoefadj[this.selectedCoefX];
            final int selectedCoefY = this.selectedCoefY;
            array[selectedCoefY] -= 6.283185307179586;
        }
        if (this.alwaysNormItem.getState()) {
            this.normalize();
        }
        this.cv.repaint(this.pause);
    }
    
    void editMagClick() {
        if (this.selectedCoefX == -1) {
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
    }
    
    void editX(final int n, final int n2) {
        switch (this.mouseChooser.getSelectedIndex()) {
            case 0: {
                this.editXGauss(n, n2);
            }
            case 1: {
                this.editXGaussP(n, n2);
            }
            case 2: {
                this.editRotate(n, n2);
            }
            default: {}
        }
    }
    
    void editL(final int n, final int n2) {
        final int n3 = n * this.modeCountTh / this.winSize.width - this.modeCountTh / 2;
        for (int i = 0; i <= this.sampleCountR; ++i) {
            for (int j = 0; j <= this.sampleCountTh; ++j) {
                if (i == 0 && n3 != 0) {
                    this.func[j][0] = (this.funci[j][0] = 0.0);
                }
                else {
                    final double n4 = j * 2 * 3.141592653589793 / this.sampleCountTh;
                    this.func[j][i] = Math.cos(n4 * n3);
                    this.funci[j][i] = Math.sin(n4 * n3);
                }
            }
        }
        this.transform();
        this.cv.repaint(this.pause);
    }
    
    void editXGauss(int i, int j) {
        final int n = i - this.dragX + 8;
        final int n2 = j - this.dragY + 8;
        final double n3 = 1.0 / (this.abs(n) + 1.0E-4);
        final double n4 = 1.0 / (this.abs(n2) + 1.0E-4);
        final double lastGaussWx = -n3 * n3 * 2000.0;
        final double lastGaussWy = -n4 * n4 * 2000.0;
        this.lastGaussWx = lastGaussWx;
        this.lastGaussWy = lastGaussWy;
        double n5;
        double n6;
        double n7;
        for (i = 0; i != this.sampleCountR; ++i) {
            for (j = 0; j != this.sampleCountTh; ++j) {
                n5 = j * 2 * 3.141592653589793 / this.sampleCountTh;
                n6 = -Math.cos(n5) * i / this.sampleCountR - this.selectedGridX;
                n7 = -Math.sin(n5) * i / this.sampleCountR - this.selectedGridY;
                this.func[j][i] = Math.exp(lastGaussWx * n6 * n6 + lastGaussWy * n7 * n7);
                this.funci[j][i] = 0.0;
            }
        }
        this.transform();
        this.cv.repaint(this.pause);
    }
    
    void editXGaussP(int i, int j) {
        final double lastGaussWx = this.lastGaussWx;
        final double lastGaussWy = this.lastGaussWy;
        final double n = (i - this.dragX) * 0.1;
        final double n2 = -(j - this.dragY) * 0.1;
        double n3;
        double n4;
        double n5;
        double cos;
        double cos2;
        double sin;
        double sin2;
        double exp;
        for (i = 0; i != this.sampleCountR; ++i) {
            for (j = 0; j != this.sampleCountTh; ++j) {
                n3 = j * 2 * 3.141592653589793 / this.sampleCountTh;
                n4 = -Math.cos(n3) * i / this.sampleCountR - this.selectedGridX;
                n5 = -Math.sin(n3) * i / this.sampleCountR - this.selectedGridY;
                cos = Math.cos(n * n4);
                cos2 = Math.cos(n2 * n5);
                sin = Math.sin(n * n4);
                sin2 = Math.sin(n2 * n5);
                exp = Math.exp(lastGaussWx * n4 * n4 + lastGaussWy * n5 * n5);
                this.func[j][i] = exp * (cos * cos2 - sin * sin2);
                this.funci[j][i] = exp * (cos * sin2 + cos2 * sin);
            }
        }
        this.transform();
        this.cv.repaint(this.pause);
    }
    
    void editRotate(final int dragX, final int dragY) {
        final int n = this.viewXMap.x + this.viewXMap.width / 2;
        final int n2 = this.viewXMap.y + this.viewXMap.height / 2;
        final double n3 = Math.atan2(-(dragY - n2), dragX - n) - Math.atan2(-(this.dragY - n2), this.dragX - n);
        for (int i = 1; i < this.modeCountTh; ++i) {
            for (int j = 0; j < this.modeCountR; ++j) {
                int n4 = (i + 1) / 2;
                if (i % 2 == 0) {
                    n4 = -n4;
                }
                final double[] array = this.phasecoefadj[i];
                final int n5 = j;
                array[n5] += n3 * n4;
            }
        }
        this.dragX = dragX;
        this.dragY = dragY;
        this.cv.repaint(this.pause);
    }
    
    void transform() {
        this.t = 0.0;
        for (int i = 0; i != this.modeCountTh; ++i) {
            for (int j = 0; j != this.modeCountR; ++j) {
                this.phasecoefcos[i][j] = (this.phasecoefsin[i][j] = 0.0);
            }
        }
        for (int k = 0; k <= this.sampleCountR; ++k) {
            for (int l = 0; l != this.sampleCountTh * 2; ++l) {
                this.xformbuf[l] = 0.0;
            }
            for (int n = 0; n != this.sampleCountTh; ++n) {
                this.xformbuf[n * 2] = this.func[n][k] * k;
                this.xformbuf[n * 2 + 1] = this.funci[n][k] * k;
            }
            this.fftTh.transform(this.xformbuf);
            for (int n2 = 0; n2 != this.modeCountR; ++n2) {
                final double[] array = this.phasecoefcos[0];
                final int n3 = n2;
                array[n3] += this.xStates[0][n2][k] * this.xformbuf[0];
                final double[] array2 = this.phasecoefsin[0];
                final int n4 = n2;
                array2[n4] += this.xStates[0][n2][k] * this.xformbuf[1];
            }
            final int n5 = this.sampleCountTh * 2 - 1;
            for (int n6 = 1; n6 < this.modeCountTh; n6 += 2) {
                for (int n7 = 0; n7 != this.modeCountR; ++n7) {
                    final int n8 = n6 + 1;
                    final int n9 = n8 / 2;
                    final double[] array3 = this.phasecoefcos[n6];
                    final int n10 = n7;
                    array3[n10] += this.xStates[n9][n7][k] * this.xformbuf[n8];
                    final double[] array4 = this.phasecoefsin[n6];
                    final int n11 = n7;
                    array4[n11] += this.xStates[n9][n7][k] * this.xformbuf[n8 + 1];
                    final double[] array5 = this.phasecoefcos[n6 + 1];
                    final int n12 = n7;
                    array5[n12] += this.xStates[n9][n7][k] * this.xformbuf[n5 & -n8];
                    final double[] array6 = this.phasecoefsin[n6 + 1];
                    final int n13 = n7;
                    array6[n13] += this.xStates[n9][n7][k] * this.xformbuf[n5 & -n8 + 1];
                }
            }
        }
        for (int n14 = 0; n14 != this.modeCountTh; ++n14) {
            for (int n15 = 0; n15 != this.modeCountR; ++n15) {
                double n16 = this.phasecoefcos[n14][n15];
                double n17 = this.phasecoefsin[n14][n15];
                if (n16 < 1.0E-5 && n16 > -1.0E-5) {
                    n16 = 0.0;
                }
                if (n17 < 1.0E-5 && n17 > -1.0E-5) {
                    n17 = 0.0;
                }
                this.magcoef[n14][n15] = Math.sqrt(n16 * n16 + n17 * n17);
                this.phasecoefadj[n14][n15] = Math.atan2(n17, n16);
            }
        }
        if (this.alwaysNormItem.getState()) {
            this.normalize();
        }
        else if (this.alwaysMaxItem.getState()) {
            this.maximize();
        }
    }
    
    int sign(final int n) {
        return (n < 0) ? -1 : ((n == 0) ? 0 : 1);
    }
    
    int abs(final int n) {
        return (n < 0) ? (-n) : n;
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
        if (actionEvent.getSource() == this.exitItem) {
            this.applet.destroyFrame();
            return;
        }
        this.cv.repaint();
        if (actionEvent.getSource() == this.groundButton) {
            this.doGround();
        }
        if (actionEvent.getSource() == this.blankButton) {
            this.doBlank();
        }
        if (actionEvent.getSource() == this.normalizeButton) {
            this.normalize();
        }
        if (actionEvent.getSource() == this.maximizeButton) {
            this.maximize();
        }
        if (actionEvent.getSource() == this.measureEItem) {
            this.measureE();
        }
        if (actionEvent.getSource() == this.measureLItem) {
            this.measureL();
        }
    }
    
    public void adjustmentValueChanged(final AdjustmentEvent adjustmentEvent) {
        System.out.print(((Scrollbar)adjustmentEvent.getSource()).getValue() + "\n");
        if (adjustmentEvent.getSource() == this.resBar && this.resBar.getValue() != this.modeCountR) {
            this.setResolution();
        }
        if (adjustmentEvent.getSource() == this.pZoomBar) {
            this.calcPStates();
        }
        if (adjustmentEvent.getSource() == this.phasorBar) {
            this.maxDispPhasorsR = this.phasorBar.getValue();
            this.maxDispPhasorsTh = this.maxDispPhasorsR * 2 + 1;
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
        final int modeCountTh = this.modeCountTh;
        final int modeCountR = this.modeCountR;
        final int value = this.resBar.getValue();
        this.sampleCountR = value;
        this.modeCountR = value;
        this.sampleCountR *= 4;
        final int n = this.resBar.getValue() * 2;
        this.sampleCountTh = 1;
        while (this.sampleCountTh < n) {
            this.sampleCountTh *= 2;
        }
        this.modeCountTh = this.sampleCountTh + 1;
        this.modeCountM = this.sampleCountTh / 2 + 1;
        this.sampleCountTh *= 2;
        this.fftTh = new FFT(this.sampleCountTh);
        final double[][] magcoef = this.magcoef;
        this.magcoef = new double[this.modeCountTh][this.modeCountR];
        this.phasecoef = new double[this.modeCountTh][this.modeCountR];
        this.phasecoefcos = new double[this.modeCountTh][this.modeCountR];
        this.phasecoefsin = new double[this.modeCountTh][this.modeCountR];
        this.phasecoefadj = new double[this.modeCountTh][this.modeCountR];
        this.xformbuf = new double[this.sampleCountTh * 2];
        this.func = new double[this.sampleCountTh + 1][this.sampleCountR + 1];
        this.funci = new double[this.sampleCountTh + 1][this.sampleCountR + 1];
        this.pfunc = new double[this.sampleCountTh + 1][this.sampleCountR + 1];
        this.pfunci = new double[this.sampleCountTh + 1][this.sampleCountR + 1];
        this.lzspectrum = null;
        System.out.print("grid: " + this.sampleCountTh + " " + this.sampleCountR + " " + this.sampleCountTh * this.sampleCountR + "\n");
        this.scaleHeight = 6.0;
        this.step = 3.141592653589793 / this.sampleCountTh;
        this.viewDistance = 50.0;
        this.elevels = new double[this.modeCountTh][this.modeCountR];
        final double n2 = this.step * 2.0;
        System.out.print("calc omegas...\n");
        for (int i = 0; i != this.modeCountTh; ++i) {
            for (int j = 0; j != this.modeCountR; ++j) {
                this.elevels[i][j] = this.zeroj((i + 1) / 2, j + 1) / this.sampleCountR;
            }
        }
        System.out.print("calc omegas...done\n");
        final double[] array = new double[this.modeCountM + 1];
        this.xStates = new double[this.modeCountM][this.modeCountR][this.sampleCountR + 1];
        System.out.print("calc modes...\n");
        for (int k = 0; k != this.modeCountM; ++k) {
            for (int l = 0; l != this.modeCountR; ++l) {
                double n3 = 0.0;
                double n4 = 0.0;
                for (int n5 = 0; n5 <= this.sampleCountR; ++n5) {
                    if (n5 == 0) {
                        array[k + 1] = ((k == 0) ? 1.0 : 0.0);
                    }
                    else {
                        this.bess(k, n5 * this.elevels[k * 2][l], array);
                    }
                    final double[] array2 = this.xStates[k][l];
                    final int n6 = n5;
                    final double n7 = array[k + 1];
                    array2[n6] = n7;
                    final double n8 = n7;
                    if (n8 > n3) {
                        n3 = n8;
                    }
                    if (n8 < -n3) {
                        n3 = -n8;
                    }
                    n4 += n8 * n8 * n5;
                }
                final double sqrt = Math.sqrt(n4);
                for (int n9 = 0; n9 <= this.sampleCountR; ++n9) {
                    final double[] array3 = this.xStates[k][l];
                    final int n10 = n9;
                    array3[n10] /= sqrt;
                }
            }
        }
        final double n11 = 0.01 / (this.elevels[0][0] * this.elevels[0][0]);
        for (int n12 = 0; n12 != this.modeCountTh; ++n12) {
            for (int n13 = 0; n13 != this.modeCountR; ++n13) {
                final double[] array4 = this.elevels[n12];
                final int n14 = n13;
                array4[n14] *= this.elevels[n12][n13] * n11;
            }
        }
        System.out.print("calc modes...done\n");
        if (magcoef != null) {
            for (int n15 = 0; n15 != modeCountTh && n15 != this.modeCountTh; ++n15) {
                for (int n16 = 0; n16 != modeCountR && n16 != this.modeCountR; ++n16) {
                    this.magcoef[n15][n16] = magcoef[n15][n16];
                }
            }
        }
        this.pZoomBarValue = -1;
        this.calcPStates();
        this.angle1SinTab = new double[this.sampleCountTh + 1];
        this.angle1CosTab = new double[this.sampleCountTh + 1];
        this.angle2SinTab = new double[this.sampleCountTh + 1];
        this.angle2CosTab = new double[this.sampleCountTh + 1];
        for (int n17 = 0; n17 <= this.sampleCountTh; ++n17) {
            final double n18 = 6.283185307179586 * n17 / this.sampleCountTh;
            final double n19 = 6.283185307179586 * (n17 + 1) / this.sampleCountTh + 0.001;
            this.angle1SinTab[n17] = Math.sin(n18);
            this.angle1CosTab[n17] = Math.cos(n18);
            this.angle2SinTab[n17] = Math.sin(n19);
            this.angle2CosTab[n17] = Math.cos(n19);
        }
    }
    
    void calcPStates() {
        if (this.pZoomBar.getValue() == this.pZoomBarValue) {
            return;
        }
        this.pZoomBarValue = this.pZoomBar.getValue();
        final double n = this.pZoomBar.getValue() / (5.0 * this.sampleCountR);
        final double[] array = new double[this.modeCountM + 1];
        final double[] array2 = new double[this.modeCountM + 1];
        System.out.print("calc pstates\n");
        this.pStates = new double[this.modeCountM][this.modeCountR][this.sampleCountR + 1];
        for (int i = 0; i != this.modeCountM; ++i) {
            final int n2 = (i == 0) ? 1 : i;
            for (int j = 0; j != this.modeCountR; ++j) {
                final double zeroj = this.zeroj(i, j + 1);
                this.bess(n2, zeroj, array2);
                array2[0] = -array2[2];
                for (int k = 0; k != this.sampleCountR; ++k) {
                    final double n3 = n * k;
                    if (k == 0) {
                        if (i == 0) {
                            array[1] = 1.0;
                            array[0] = 0.0;
                        }
                        else {
                            array[i + 1] = 0.0;
                            array[i] = ((i == 1) ? 1.0 : 0.0);
                        }
                    }
                    else {
                        this.bess(n2, n3, array);
                        array[0] = -array[2];
                    }
                    this.pStates[i][j][k] = zeroj * array2[i] * array[i + 1] / (n3 * n3 - zeroj * zeroj);
                }
            }
        }
        System.out.print("calc pstates, done\n");
    }
    
    double zeroj(final int n, final int n2) {
        if (n >= 48 && n2 == 1) {
            switch (n) {
                case 48: {
                    return 55.0283;
                }
                case 49: {
                    return 56.0729;
                }
                case 50: {
                    return 57.1169;
                }
                case 51: {
                    return 58.1603;
                }
                case 52: {
                    return 59.2032;
                }
                case 53: {
                    return 60.2456;
                }
                case 54: {
                    return 61.2875;
                }
                case 55: {
                    return 62.3288;
                }
                case 56: {
                    return 63.3697;
                }
                case 57: {
                    return 64.4102;
                }
                case 58: {
                    return 65.4501;
                }
                case 59: {
                    return 66.4897;
                }
                case 60: {
                    return 67.5288;
                }
                case 61: {
                    return 68.5675;
                }
                case 62: {
                    return 69.6058;
                }
                case 63: {
                    return 70.6437;
                }
                case 64: {
                    return 71.6812;
                }
            }
        }
        if (n >= 62 && n2 == 2) {
            switch (n) {
                case 62: {
                    return 75.6376;
                }
                case 63: {
                    return 76.7021;
                }
                case 64: {
                    return 77.7659;
                }
            }
        }
        final double n3 = (n2 + 0.5 * n - 0.25) * 3.141592654;
        final double n4 = 4 * n * n;
        final double n5 = 8.0 * n3;
        final double n6 = n5 * n5;
        final double n7 = n6 * n6;
        double n8 = n3 - (n4 - 1.0) / n5 - 4.0 * (n4 - 1.0) * (7.0 * n4 - 31.0) / (3.0 * n6 * n5) - 32.0 * (n4 - 1.0) * (83.0 * n4 * n4 - 982.0 * n4 + 3779.0) / (15.0 * n7 * n5) - 64.0 * (n4 - 1.0) * (6949.0 * n4 * n4 * n4 - 153855.0 * n4 * n4 + 1585743.0 * n4 - 6277237.0) / (105.0 * n7 * n6 * n5);
        final double[] array = new double[n + 3];
        for (int i = 1; i <= 5; ++i) {
            this.bess(n + 1, n8, array);
            n8 -= array[n + 1] / (-array[n + 2] + n / n8 * array[n + 1]);
        }
        return n8;
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
    
    void findGridPoint2D(final View view, final int n, final int n2) {
        final int n3 = view.x + view.width / 2;
        final int n4 = view.y + view.height / 2;
        final int n5 = view.width / 2;
        this.selectedGridX = (n - n3) / n5;
        this.selectedGridY = -(n2 - n4) / n5;
        final double sqrt = Math.sqrt(this.selectedGridX * this.selectedGridX + this.selectedGridY * this.selectedGridY);
        if (sqrt > 1.0) {
            this.selectedGridX /= sqrt;
            this.selectedGridY /= sqrt;
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
        this.selectedPaneHandle = -1;
        this.selection = 0;
        for (int i = 1; i != this.viewCount; ++i) {
            final int n = y - this.viewList[i].paneY;
            if (n >= -3 && n <= 3) {
                this.selectedPaneHandle = i;
                this.selection = 5;
            }
        }
        if (this.viewXMap != null && this.viewXMap.inside(x, y)) {
            this.selection = 2;
        }
        else if (this.viewPotential != null && this.viewPotential.contains(x, y)) {
            this.selection = 1;
            this.findStateByEnergy(y);
        }
        else if (this.viewStatesMap != null && this.viewStatesMap.inside(x, y)) {
            final int termWidth = this.getTermWidth();
            this.selectedCoefX = (x - this.viewStatesMap.x) / termWidth;
            this.selectedCoefY = (y - this.viewStatesMap.y) / termWidth;
            if (this.selectedCoefX >= this.modeCountTh || this.selectedCoefX >= this.maxDispPhasorsTh) {
                final int n2 = -1;
                this.selectedCoefY = n2;
                this.selectedCoefX = n2;
            }
            if (this.selectedCoefY >= this.modeCountR || this.selectedCoefY >= this.maxDispPhasorsR) {
                final int n3 = -1;
                this.selectedCoefY = n3;
                this.selectedCoefX = n3;
            }
            if (this.selectedCoefX < 0 || this.selectedCoefY < 0) {
                final int n4 = -1;
                this.selectedCoefY = n4;
                this.selectedCoefX = n4;
            }
            if (this.selectedCoefX != -1 && this.selectedCoefY != -1) {
                this.selection = 3;
            }
        }
        else if (this.viewL != null && this.viewL.contains(x, y)) {
            this.selection = 4;
        }
        if (this.selectedCoefX != selectedCoefX || this.selectedCoefY != selectedCoefY) {
            this.cv.repaint(this.pause);
        }
    }
    
    void findStateByEnergy(final int n) {
        final int n2 = this.viewPotential.y + this.viewPotential.height - 5;
        final double n3 = 200.0;
        double n4 = 100.0;
        for (int i = 0; i != this.modeCountTh; ++i) {
            for (int j = 0; j != this.modeCountR; ++j) {
                final double n5 = Math.abs(n - (n2 - (int)(n3 * this.elevels[i][j])));
                if (n5 < n4) {
                    n4 = n5;
                    this.selectedCoefX = i;
                    this.selectedCoefY = j;
                }
            }
        }
    }
    
    public void mouseClicked(final MouseEvent mouseEvent) {
        if (this.selection == 3) {
            this.editMagClick();
        }
        if (mouseEvent.getClickCount() == 2 && this.selectedCoefX != -1) {
            this.enterSelectedState();
        }
    }
    
    void enterSelectedState() {
        for (int i = 0; i != this.modeCountTh; ++i) {
            for (int j = 0; j != this.modeCountR; ++j) {
                if (this.selectedCoefX != i || this.selectedCoefY != j) {
                    this.magcoef[i][j] = 0.0;
                }
            }
        }
        this.magcoef[this.selectedCoefX][this.selectedCoefY] = 1.0;
        this.cv.repaint(this.pause);
    }
    
    public void mouseEntered(final MouseEvent mouseEvent) {
    }
    
    public void mouseExited(final MouseEvent mouseEvent) {
        if (!this.dragging) {
            if (this.selectedCoefX != -1) {
                final int n = -1;
                this.selectedCoefY = n;
                this.selectedCoefX = n;
                this.cv.repaint(this.pause);
            }
            if (this.selectedPaneHandle != -1) {
                this.selectedPaneHandle = -1;
                this.cv.repaint(this.pause);
            }
        }
    }
    
    public void mousePressed(final MouseEvent mouseEvent) {
        if ((mouseEvent.getModifiers() & 0x10) == 0x0) {
            return;
        }
        this.mouseMoved(mouseEvent);
        if (this.selection == 2) {
            this.findGridPoint2D(this.viewXMap, mouseEvent.getX(), mouseEvent.getY());
        }
        this.dragStartX = mouseEvent.getX();
        this.dragStartY = mouseEvent.getY();
        if (this.selectedCoefX != -1) {
            this.magDragStart = this.magcoef[this.selectedCoefX][this.selectedCoefY];
        }
        this.dragging = true;
        this.edit(mouseEvent);
    }
    
    public void mouseReleased(final MouseEvent mouseEvent) {
        if ((mouseEvent.getModifiers() & 0x10) == 0x0) {
            return;
        }
        final boolean dragging = false;
        this.dragStop = dragging;
        this.editingFunc = dragging;
        this.dragging = dragging;
        final boolean b = false;
        this.dragClear = b;
        this.dragSet = b;
        this.mouseMoved(mouseEvent);
        this.cv.repaint(this.pause);
    }
    
    public void itemStateChanged(final ItemEvent itemEvent) {
        if (itemEvent.getItemSelectable() == this.stoppedCheck) {
            this.cv.repaint(this.pause);
            return;
        }
        if (itemEvent.getItemSelectable() == this.xCheckItem || itemEvent.getItemSelectable() == this.pCheckItem || itemEvent.getItemSelectable() == this.lCheckItem || itemEvent.getItemSelectable() == this.eCheckItem || itemEvent.getItemSelectable() == this.statesCheckItem) {
            this.handleResize();
            this.cv.repaint(this.pause);
        }
        if (itemEvent.getItemSelectable() == this.alwaysNormItem && this.alwaysNormItem.getState()) {
            this.normalize();
            this.alwaysMaxItem.setState(false);
            this.cv.repaint(this.pause);
        }
        if (itemEvent.getItemSelectable() == this.alwaysMaxItem && this.alwaysMaxItem.getState()) {
            this.maximize();
            this.alwaysNormItem.setState(false);
            this.cv.repaint(this.pause);
        }
        for (int i = 0; i != this.waveFunctionMenu.countItems(); ++i) {
            if (itemEvent.getItemSelectable() == this.waveFunctionMenu.getItem(i)) {
                ((CheckboxMenuItem)this.waveFunctionMenu.getItem(i)).setState(true);
                for (int j = 0; j != this.waveFunctionMenu.countItems(); ++j) {
                    if (i != j) {
                        ((CheckboxMenuItem)this.waveFunctionMenu.getItem(j)).setState(false);
                    }
                }
            }
        }
    }
    
    class FFT
    {
        double[] wtab;
        int size;
        
        FFT(final int size) {
            this.size = size;
            if ((this.size & this.size - 1) != 0x0) {
                System.out.println("size must be power of two!");
            }
            this.calcWTable();
        }
        
        void calcWTable() {
            this.wtab = new double[this.size];
            for (int i = 0; i != this.size; i += 2) {
                final double n = 3.141592653589793 * i / this.size;
                this.wtab[i] = Math.cos(n);
                this.wtab[i + 1] = Math.sin(n);
            }
        }
        
        void transform(final double[] array) {
            int n = 0;
            final int n2 = this.size * 2;
            for (int i = 0; i != n2; i += 2) {
                if (i > n) {
                    final double n3 = array[i];
                    array[i] = array[n];
                    array[n] = n3;
                    final double n4 = array[i + 1];
                    array[i + 1] = array[n + 1];
                    array[n + 1] = n4;
                }
                int size;
                for (size = this.size; (size & n) != 0x0; n &= ~size, size >>= 1) {}
                n |= size;
            }
            int n5 = n2;
            for (int j = 4; j <= n2; j <<= 1) {
                final int n6 = j >> 1;
                n5 >>= 1;
                for (int k = 0; k < n2; k += j) {
                    for (int n7 = 0, l = k; l != k + n6; l += 2, n7 += n5) {
                        final double n8 = this.wtab[n7];
                        final double n9 = this.wtab[n7 + 1];
                        final double n10 = array[l];
                        final double n11 = array[l + 1];
                        final int n12 = l + n6;
                        final double n13 = array[n12];
                        final double n14 = array[n12 + 1];
                        final double n15 = n13 * n8 - n14 * n9;
                        final double n16 = n13 * n9 + n14 * n8;
                        array[l] = n10 + n15;
                        array[l + 1] = n11 + n16;
                        array[n12] = n10 - n15;
                        array[n12 + 1] = n11 - n16;
                    }
                }
            }
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
        
        Color getColor() {
            return new Color((int)(this.r * 255.0), (int)(this.g * 255.0), (int)(this.b * 255.0));
        }
    }
    
    class View extends Rectangle
    {
        double ymult;
        double ymult2;
        double scale;
        int mid_y;
        int lower_y;
        int paneY;
        MemoryImageSource imageSource;
        Image memimage;
        int[] pixels;
        
        View() {
        }
        
        View(final View view) {
            super(view);
        }
    }
}
