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

class QuantumBoxFrame extends Frame implements ComponentListener, ActionListener, AdjustmentListener, MouseMotionListener, MouseListener, ItemListener
{
    Thread engine;
    Dimension winSize;
    Image dbimage;
    Random random;
    int maxTerms;
    int maxDispTerms;
    int sampleCount;
    static final double epsilon = 1.0E-5;
    static final double epsilon2 = 0.003;
    static final int panePad = 4;
    Button groundButton;
    Button blankButton;
    Button normalizeButton;
    Button maximizeButton;
    Checkbox stoppedCheck;
    CheckboxMenuItem eCheckItem;
    CheckboxMenuItem xCheckItem;
    CheckboxMenuItem pCheckItem;
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
    MenuItem exitItem;
    Choice mouseChooser;
    Scrollbar speedBar;
    Scrollbar forceBar;
    Scrollbar resBar;
    Scrollbar pZoomBar;
    Scrollbar phasorBar;
    Scrollbar aspectBar;
    Scrollbar brightnessBar;
    View viewPotential;
    View viewX;
    View viewP;
    View viewStates;
    View viewCurrent;
    View viewXMap;
    View viewPMap;
    View viewStatesMap;
    View[] viewList;
    int viewCount;
    boolean showMode;
    boolean editingFunc;
    boolean dragStop;
    double aspectRatio;
    double[][] magcoef;
    double[][] phasecoef;
    double[][] phasecoefcos;
    double[][] phasecoefsin;
    double[][] phasecoefadj;
    double modephasecos;
    double[][] elevels;
    float[] data;
    static final double pi = 3.141592653589793;
    double step;
    float[][] func;
    float[][] funci;
    float[][] pfuncr;
    float[][] pfunci;
    PhaseColor[][] phaseColors;
    PhaseColor whitePhaseColor;
    Color[] grayLevels;
    static final int phaseColorCount = 50;
    int[] xpoints;
    int[] ypoints;
    int[] floorValues;
    int selectedCoefX;
    int selectedCoefY;
    int selectedGridX;
    int selectedGridY;
    int selectedPaneHandle;
    double selectedGridFunc;
    static final int SEL_NONE = 0;
    static final int SEL_POTENTIAL = 1;
    static final int SEL_X = 2;
    static final int SEL_P = 3;
    static final int SEL_STATES = 4;
    static final int SEL_HANDLE = 5;
    static final int MOUSE_EIGEN = 0;
    static final int MOUSE_GAUSS = 1;
    static final int MOUSE_GAUSSP = 2;
    static final int MOUSE_SQUARE = 3;
    static final int MOUSE_CIRCLE = 4;
    int selection;
    int dragX;
    int dragY;
    int dragStartX;
    int dragStartY;
    boolean dragSet;
    boolean dragClear;
    double magDragStart;
    boolean dragging;
    double t;
    int pause;
    QuantumBoxCanvas cv;
    QuantumBox applet;
    boolean useBufferedImage;
    long lastTime;
    double logep2;
    double lastGaussWx;
    double lastGaussWy;
    double momentumX;
    double momentumY;
    
    public String getAppletInfo() {
        return "QuantumBox by Paul Falstad";
    }
    
    int getrand(final int n) {
        int nextInt = this.random.nextInt();
        if (nextInt < 0) {
            nextInt = -nextInt;
        }
        return nextInt % n;
    }
    
    QuantumBoxFrame(final QuantumBox applet) {
        super("Quantum 2-D Box Applet v1.5");
        this.engine = null;
        this.maxTerms = 16;
        this.maxDispTerms = 10;
        this.aspectRatio = 1.0;
        this.useBufferedImage = false;
        this.logep2 = 0.0;
        this.lastGaussWx = -0.03;
        this.lastGaussWy = -0.03;
        this.applet = applet;
    }
    
    public void init() {
        if (new Double(System.getProperty("java.class.version")) >= 48.0) {
            this.useBufferedImage = true;
        }
        final int n = -1;
        this.selectedCoefY = n;
        this.selectedCoefX = n;
        this.setLayout(new QuantumBoxLayout());
        (this.cv = new QuantumBoxCanvas(this)).addComponentListener(this);
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
        menu2.add(this.pCheckItem = this.getCheckItem("Momentum"));
        menu2.add(this.statesCheckItem = this.getCheckItem("State Phasors"));
        this.statesCheckItem.setState(true);
        menu2.addSeparator();
        menu2.add(this.expectCheckItem = this.getCheckItem("Expectation Values"));
        this.expectCheckItem.setState(true);
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
        this.setMenuBar(menuBar);
        final Menu menu5 = new Menu("Options");
        menuBar.add(menu5);
        menu5.add(this.alwaysNormItem = this.getCheckItem("Always Normalize"));
        menu5.add(this.alwaysMaxItem = this.getCheckItem("Always Maximize"));
        this.alwaysMaxItem.setState(true);
        this.setMenuBar(menuBar);
        (this.mouseChooser = new Choice()).add("Mouse = Set Eigenstate");
        this.mouseChooser.add("Mouse = Create Gaussian");
        this.mouseChooser.add("Mouse = Gaussian w/ Momentum");
        this.mouseChooser.add("Mouse = Create Square");
        this.mouseChooser.add("Mouse = Create Circle");
        this.mouseChooser.addItemListener(this);
        this.add(this.mouseChooser);
        this.mouseChooser.select(1);
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
        this.add(this.speedBar = new Scrollbar(0, 109, 1, 1, 300));
        this.speedBar.addAdjustmentListener(this);
        this.add(new Label("Brightness", 1));
        this.add(this.brightnessBar = new Scrollbar(0, 1100, 1, 700, 2000));
        this.brightnessBar.addAdjustmentListener(this);
        this.add(new Label("Resolution", 1));
        this.add(this.resBar = new Scrollbar(0, 6, 1, 4, 9));
        this.resBar.addAdjustmentListener(this);
        this.add(new Label("Momentum Zoom", 1));
        this.add(this.pZoomBar = new Scrollbar(0, 1, 1, 5, 100));
        this.pZoomBar.addAdjustmentListener(this);
        this.add(new Label("Phasor Count", 1));
        this.add(this.phasorBar = new Scrollbar(0, 10, 1, 5, 30));
        this.phasorBar.addAdjustmentListener(this);
        this.add(new Label("Aspect Ratio", 1));
        this.add(this.aspectBar = new Scrollbar(0, 10, 1, 5, 31));
        this.aspectBar.addAdjustmentListener(this);
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
        this.magcoef[1][3] = 1.0;
        this.magcoef[3][1] = 1.0;
        this.phasecoefadj[3][1] = 1.5707963267948966;
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
        final View view = null;
        this.viewP = view;
        this.viewX = view;
        this.viewStates = view;
        this.viewPotential = view;
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
        final View view = null;
        this.viewPMap = view;
        this.viewXMap = view;
        this.viewStatesMap = null;
        if (this.viewStates != null) {
            this.viewStatesMap = new View(this.viewStates);
            this.viewStatesMap.x = (this.winSize.width - this.viewStatesMap.height) / 2;
            final View viewStatesMap = this.viewStatesMap;
            viewStatesMap.width -= this.viewStatesMap.x * 2;
        }
        if (this.viewX != null) {
            this.processMap(this.viewXMap = new View(this.viewX), this.aspectRatio);
        }
        if (this.viewP != null) {
            this.processMap(this.viewPMap = new View(this.viewP), 1.0 / this.aspectRatio);
        }
        this.floorValues = null;
    }
    
    void processMap(final View view, final double n) {
        int width;
        int height;
        if (n > view.width / view.height) {
            width = view.width - 2;
            height = (int)(width / n);
        }
        else {
            height = view.height - 2;
            width = (int)(height * n);
        }
        view.x += (view.width - width) / 2 + 1;
        view.y += (view.height - height) / 2 + 1;
        view.width = width;
        view.height = height;
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
    
    int min(final int n, final int n2) {
        return (n < n2) ? n : n2;
    }
    
    void doGround() {
        for (int i = 0; i != this.sampleCount; ++i) {
            for (int j = 0; j != this.sampleCount; ++j) {
                this.magcoef[i][j] = 0.0;
            }
        }
        this.magcoef[1][1] = 1.0;
    }
    
    void doBlank() {
        for (int i = 0; i <= this.sampleCount; ++i) {
            for (int j = 0; j <= this.sampleCount; ++j) {
                this.func[i][j] = 0.0f;
            }
        }
        this.transform(true);
    }
    
    void normalize() {
        double n = 0.0;
        for (int i = 0; i != this.sampleCount; ++i) {
            for (int j = 0; j != this.sampleCount; ++j) {
                n += this.magcoef[i][j] * this.magcoef[i][j];
            }
        }
        if (n == 0.0) {
            return;
        }
        final double n2 = 1.0 / Math.sqrt(n);
        for (int k = 0; k != this.sampleCount; ++k) {
            for (int l = 0; l != this.sampleCount; ++l) {
                final double[] array = this.magcoef[k];
                final int n3 = l;
                array[n3] *= n2;
            }
        }
        this.cv.repaint(this.pause);
    }
    
    void maximize() {
        double n = 0.0;
        for (int i = 0; i != this.sampleCount; ++i) {
            for (int j = 0; j != this.sampleCount; ++j) {
                if (this.magcoef[i][j] > n) {
                    n = this.magcoef[i][j];
                }
            }
        }
        if (n == 0.0) {
            return;
        }
        for (int k = 0; k != this.sampleCount; ++k) {
            for (int l = 0; l != this.sampleCount; ++l) {
                final double[] array = this.magcoef[k];
                final int n2 = l;
                array[n2] *= 1.0 / n;
            }
        }
        this.cv.repaint(this.pause);
    }
    
    void measureE() {
        this.normalize();
        double nextDouble = this.random.nextDouble();
        int n = -1;
        int n2 = -1;
        for (int i = 0; i < this.sampleCount; ++i) {
            for (int j = 0; j < this.sampleCount; ++j) {
                nextDouble -= this.magcoef[i][j] * this.magcoef[i][j];
                if (nextDouble < 0.0) {
                    n = i;
                    n2 = j;
                    i = this.sampleCount;
                    break;
                }
            }
        }
        if (n == -1) {
            return;
        }
        for (int k = 0; k != this.sampleCount; ++k) {
            for (int l = 0; l != this.sampleCount; ++l) {
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
    
    void transform(final boolean b) {
        this.t = 0.0;
        final int[] array = new int[2];
        array[0] = (array[1] = this.maxTerms * 2);
        final int n = this.maxTerms * 4;
        final int n2 = this.maxTerms * 2;
        final float n3 = -1.0f;
        for (int i = 0; i != this.maxTerms * this.maxTerms * 8; ++i) {
            this.data[i] = 0.0f;
        }
        for (int j = 1; j < this.sampleCount; ++j) {
            for (int k = 1; k < this.sampleCount; ++k) {
                final float n4 = b ? 0.0f : this.funci[j][k];
                this.data[j * 2 + k * n] = this.func[j][k];
                this.data[j * 2 + k * n + 1] = n4;
                this.data[(n2 - j) * 2 + k * n] = n3 * this.func[j][k];
                this.data[(n2 - j) * 2 + k * n + 1] = n3 * n4;
                this.data[(n2 - j) * 2 + (n2 - k) * n] = this.func[j][k];
                this.data[(n2 - j) * 2 + (n2 - k) * n + 1] = n4;
                this.data[j * 2 + (n2 - k) * n] = n3 * this.func[j][k];
                this.data[j * 2 + (n2 - k) * n + 1] = n3 * n4;
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
                this.magcoef[l][n6] = Math.sqrt(n7 * n7 + n8 * n8);
                final double atan2 = Math.atan2(n8, n7);
                this.phasecoefadj[l][n6] = atan2;
                this.phasecoef[l][n6] = atan2;
            }
        }
        this.cv.repaint(this.pause);
        if (this.alwaysNormItem.getState()) {
            this.normalize();
        }
        else if (this.alwaysMaxItem.getState()) {
            this.maximize();
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
    
    public void updateQuantumBox(final Graphics graphics) {
        final Graphics graphics2 = this.dbimage.getGraphics();
        if (this.winSize == null || this.winSize.width == 0) {
            this.handleResize();
            return;
        }
        boolean b = true;
        if (!this.stoppedCheck.getState() && !this.dragging) {
            final double n = Math.exp(this.speedBar.getValue() / 20.0) * 0.02;
            final long currentTimeMillis = System.currentTimeMillis();
            if (this.lastTime == 0L) {
                this.lastTime = currentTimeMillis;
            }
            this.t += n * ((currentTimeMillis - this.lastTime) * 0.002);
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
        this.xpoints = new int[3];
        this.xpoints = new int[3];
        if (this.dragStop) {
            this.t = 0.0;
        }
        double n2 = 0.0;
        double n3 = 0.0;
        if (!this.editingFunc) {
            for (int j = 0; j != this.maxTerms; ++j) {
                for (int k = 0; k != this.maxTerms; ++k) {
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
            this.genFunc((float)Math.sqrt(n3));
        }
        final double exp = Math.exp(this.brightnessBar.getValue() / 200.0 - 5.0);
        if (n2 == 0.0) {
            n3 = 0.0;
        }
        if (this.dragStop) {
            b = true;
        }
        if (this.showMode) {
            b = false;
            this.modephasecos = Math.cos(this.elevels[this.selectedCoefX][this.selectedCoefY] * this.t + this.phasecoefadj[this.selectedCoefX][this.selectedCoefY]);
            if (this.magcoef[this.selectedCoefX][this.selectedCoefY] < 0.0) {
                this.modephasecos = -this.modephasecos;
            }
        }
        final int n8 = this.sampleCount / 2;
        if (this.viewPotential != null) {
            final int n9 = this.viewPotential.y + this.viewPotential.height - 5;
            final double n10 = 200.0;
            if (this.floorValues == null) {
                this.floorValues = new int[n9 + 1];
            }
            for (int l = 0; l <= n9; ++l) {
                this.floorValues[l] = 0;
            }
            for (int n11 = 1; n11 != this.sampleCount; ++n11) {
                for (int n12 = 1; n12 != this.sampleCount; ++n12) {
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
                for (int n22 = 1; n22 != this.sampleCount; ++n22) {
                    for (int n23 = 1; n23 != this.sampleCount; ++n23) {
                        final double n24 = this.magcoef[n22][n23] * this.magcoef[n22][n23] * n3;
                        n20 += n24 * this.elevels[n22][n23];
                        n21 += n24 * this.elevels[n22][n23] * this.elevels[n22][n23];
                    }
                }
                double sqrt = Math.sqrt(n21 - n20 * n20);
                if (this.uncertaintyCheckItem.getState()) {
                    if (sqrt < 0.0) {
                        sqrt = 0.0;
                    }
                    graphics2.setColor(Color.blue);
                    final int n25 = n9 - (int)(n10 * (n20 + sqrt));
                    graphics2.drawLine(0, n25, this.winSize.width, n25);
                    final int n26 = n9 - (int)(n10 * (n20 - sqrt));
                    if (n20 - sqrt >= 0.0) {
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
        if (this.viewXMap != null) {
            this.updateMapView(graphics2, this.viewXMap, this.func, this.funci, this.maxTerms, exp);
        }
        if (this.viewPMap != null) {
            final int n29 = this.maxTerms * 2;
            for (int n30 = 0; n30 != this.maxTerms * this.maxTerms * 8; ++n30) {
                this.data[n30] = 0.0f;
            }
            final int n31 = n29 * 2;
            final int[] array4 = new int[2];
            array4[0] = (array4[1] = n29);
            final int n32 = n29 - 1;
            final int maxTerms = this.maxTerms;
            final int n33 = (n29 - this.sampleCount) / 2;
            for (int n34 = 0; n34 != this.sampleCount; ++n34) {
                for (int n35 = 0; n35 != this.sampleCount; ++n35) {
                    final int n36 = (n34 + n33 + maxTerms & n32) * 2 + (n35 + n33 + maxTerms & n32) * n31;
                    this.data[n36] = this.func[n34][n35];
                    this.data[n36 + 1] = this.funci[n34][n35];
                }
            }
            this.ndfft(this.data, array4, 2, 1);
            final float n37 = 1.0f / (this.sampleCount * 2);
            final int n38 = (101 - this.pZoomBar.getValue()) * n29 / 100;
            final int n39 = (n29 - n38 + 2) / 2;
            if (this.pfuncr == null) {
                this.pfuncr = new float[n29 + 1][n29 + 1];
                this.pfunci = new float[n29 + 1][n29 + 1];
            }
            for (int n40 = 0; n40 <= n38; ++n40) {
                for (int n41 = 0; n41 <= n38; ++n41) {
                    final int n42 = (n38 - 1 - n40 + n39 + maxTerms & n32) * 2 + (n38 - 1 - n41 + n39 + maxTerms & n32) * n31;
                    this.pfuncr[n40][n41] = this.data[n42] * n37;
                    this.pfunci[n40][n41] = this.data[n42 + 1] * n37;
                }
            }
            this.updateMapView(graphics2, this.viewPMap, this.pfuncr, this.pfunci, n38, exp);
        }
        else {
            final float[][] array5 = null;
            this.pfunci = array5;
            this.pfuncr = array5;
        }
        if (this.viewStatesMap != null) {
            final int termWidth;
            final int n43 = termWidth = this.getTermWidth();
            final int n44 = n43 / 2;
            for (int n45 = 1; n45 <= this.maxDispTerms; ++n45) {
                for (int n46 = 1; n46 <= this.maxDispTerms; ++n46) {
                    final int n47 = this.viewStatesMap.x + (n45 - 1) * n43 + n44;
                    final int n48 = this.viewStatesMap.y + (n46 - 1) * n43 + n44;
                    graphics2.setColor((this.selectedCoefX != -1 && this.elevels[this.selectedCoefX][this.selectedCoefY] == this.elevels[n45][n46]) ? Color.yellow : ((this.magcoef[n45][n46] == 0.0) ? color2 : Color.white));
                    graphics2.drawOval(n47 - n44, n48 - n44, termWidth, termWidth);
                    final int n49 = (int)(this.magcoef[n45][n46] * this.phasecoefcos[n45][n46] * n44);
                    final int n50 = (int)(-this.magcoef[n45][n46] * this.phasecoefsin[n45][n46] * n44);
                    graphics2.drawLine(n47, n48, n47 + n49, n48 + n50);
                    graphics2.drawLine(n47 + n49 - 1, n48 + n50, n47 + n49 + 1, n48 + n50);
                    graphics2.drawLine(n47 + n49, n48 + n50 - 1, n47 + n49, n48 + n50 + 1);
                }
            }
            graphics2.setColor(Color.white);
            if (this.viewStatesMap.x > n43 * 3 / 2 && this.aspectRatio == 1.0) {
                final int n51 = this.winSize.width - termWidth;
                final int n52 = this.viewStatesMap.y + this.viewStatesMap.height / 2;
                final double cos = Math.cos(-this.elevels[1][1] * this.t / 2.0 + 1.5707963267948966);
                final double sin = Math.sin(-this.elevels[1][1] * this.t / 2.0 + 1.5707963267948966);
                final int n53 = (int)(cos * n44);
                final int n54 = (int)(-sin * n44);
                graphics2.drawOval(n51 - n44, n52 - n44, termWidth, termWidth);
                graphics2.drawLine(n51, n52, n51 + n53, n52 + n54);
                graphics2.drawLine(n51 + n53 - 1, n52 + n54, n51 + n53 + 1, n52 + n54);
                graphics2.drawLine(n51 + n53, n52 + n54 - 1, n51 + n53, n52 + n54 + 1);
            }
        }
        if (this.selectedCoefX != -1 && this.viewXMap != null) {
            graphics2.setColor(Color.yellow);
            for (int n55 = 0; n55 != this.selectedCoefX; ++n55) {
                for (int n56 = 0; n56 != this.selectedCoefY; ++n56) {
                    final int n57 = this.viewXMap.x + this.viewXMap.width * n55 / this.selectedCoefX;
                    final int n58 = this.viewXMap.width / this.selectedCoefX;
                    final int n59 = this.viewXMap.y + this.viewXMap.height * n56 / this.selectedCoefY;
                    final int n60 = this.viewXMap.height / this.selectedCoefY;
                    graphics2.drawOval(n57 + n58 * 20 / 100, n59 + n60 * 20 / 100, n58 * 60 / 100, n60 * 60 / 100);
                }
            }
        }
        graphics.drawImage(this.dbimage, 0, 0, this);
        if (!this.stoppedCheck.getState() && !b) {
            this.cv.repaint(this.pause);
        }
    }
    
    void updateMapView(final Graphics graphics, final View view, final float[][] array, final float[][] array2, final int n, final double n2) {
        graphics.setColor(Color.white);
        graphics.drawRect(view.x - 1, view.y - 1, view.width + 2, view.height + 2);
        double n3 = 0.0;
        double n4 = 0.0;
        double n5 = 0.0;
        double n6 = 0.0;
        double n7 = 0.0;
        double n8 = 0.0;
        final int n9 = n / 2;
        for (int i = 0; i <= n; ++i) {
            for (int j = 0; j <= n; ++j) {
                final double n10 = array[j][i];
                final double n11 = array2[j][i];
                final double n12 = n10 * n10 + n11 * n11;
                if (n12 > n3) {
                    n3 = n12;
                }
                final int n13 = j - n9;
                n4 += n12 * n13;
                n5 += n12 * n13 * n13;
                final int n14 = i - n9;
                n6 += n12 * n14;
                n7 += n12 * n14 * n14;
                n8 += n12;
            }
        }
        final double n15 = n4 / n8;
        final double n16 = n5 / n8;
        final double n17 = n6 / n8;
        final double n18 = n7 / n8;
        final double sqrt = Math.sqrt(n3);
        final double sqrt2 = Math.sqrt(n16 - n15 * n15);
        final double sqrt3 = Math.sqrt(n18 - n17 * n17);
        double scale;
        if (this.probCheckItem.getState() || this.probPhaseCheckItem.getState()) {
            scale = 1.0 / n3;
        }
        else {
            scale = 1.0 / sqrt;
        }
        view.scale *= 1.1;
        view.scale = scale;
        if (view.scale > 1.0E8) {
            view.scale = 1.0E8;
        }
        final int n19 = n + 1;
        for (int k = 0; k <= n; ++k) {
            for (int l = 0; l <= n; ++l) {
                final double n20 = array[l][k];
                final double n21 = array2[l][k];
                double sqrt4 = n20 * n20 + n21 * n21;
                if (this.magPhaseCheckItem.getState()) {
                    sqrt4 = Math.sqrt(sqrt4);
                }
                double n22 = sqrt4 * (255.0 * view.scale * n2);
                final PhaseColor phaseColor = this.getPhaseColor(n20, n21);
                if (n22 > 255.0) {
                    n22 = 255.0;
                }
                final int n23 = 0xFF000000 | (int)(phaseColor.r * n22) << 16 | (int)(phaseColor.g * n22) << 8 | (int)(phaseColor.b * n22);
                final int n24 = l * view.width / n19;
                final int n25 = k * view.height / n19;
                final int n26 = (l + 1) * view.width / n19;
                final int n27 = (k + 1) * view.height / n19;
                for (int n28 = n24 + n25 * view.width, n29 = 0; n29 != n26 - n24; ++n29, ++n28) {
                    for (int n30 = 0; n30 != n27 - n25; ++n30) {
                        view.pixels[n28 + n30 * view.width] = n23;
                    }
                }
            }
        }
        if (view.imageSource != null) {
            view.imageSource.newPixels();
        }
        graphics.drawImage(view.memimage, view.x, view.y, null);
        if (this.uncertaintyCheckItem.getState()) {
            graphics.setColor(Color.blue);
            final int n31 = (int)(view.width * (n15 + n9 - sqrt2 + 0.5) / n19 + view.x);
            final int n32 = (int)(view.width * (n15 + n9 + sqrt2 + 0.5) / n19 + view.x);
            final int n33 = (int)(view.height * (n17 + n9 - sqrt3 + 0.5) / n19 + view.y);
            graphics.drawRect(n31, n33, n32 - n31, (int)(view.height * (n17 + n9 + sqrt3 + 0.5) / n19 + view.y) - n33);
        }
        if (this.expectCheckItem.getState()) {
            graphics.setColor(Color.red);
            final int n34 = (int)(view.width * (n15 + n9 + 0.5) / n19 + view.x);
            graphics.drawLine(n34, view.y, n34, view.y + view.height);
            final int n35 = (int)(view.height * (n17 + n9 + 0.5) / n19 + view.y);
            graphics.drawLine(view.x, n35, view.x + view.width, n35);
        }
    }
    
    void genFunc(float n) {
        final int[] array = new int[2];
        array[0] = (array[1] = this.maxTerms * 2);
        final int n2 = this.maxTerms * 4;
        final int n3 = this.maxTerms * 2;
        final float n4 = -1.0f;
        for (int i = 0; i != this.maxTerms * this.maxTerms * 8; ++i) {
            this.data[i] = 0.0f;
        }
        for (int j = 0; j != this.sampleCount; ++j) {
            for (int k = 0; k != this.sampleCount; ++k) {
                final float n5 = (float)this.phasecoefcos[j][k];
                final float n6 = (float)this.phasecoefsin[j][k];
                final float n7 = (float)(-0.25 * this.magcoef[j][k]);
                final float n8 = 0.0f;
                final float n9 = n7 * n5 - n8 * n6;
                final float n10 = n8 * n5 + n7 * n6;
                this.data[j * 2 + k * n2] = n9;
                this.data[j * 2 + k * n2 + 1] = n10;
                if (j > 0) {
                    this.data[(n3 - j) * 2 + k * n2] = n4 * n9;
                    this.data[(n3 - j) * 2 + k * n2 + 1] = n4 * n10;
                    if (k > 0) {
                        this.data[(n3 - j) * 2 + (n3 - k) * n2] = n9;
                        this.data[(n3 - j) * 2 + (n3 - k) * n2 + 1] = n10;
                    }
                }
                if (k > 0) {
                    this.data[j * 2 + (n3 - k) * n2] = n4 * n9;
                    this.data[j * 2 + (n3 - k) * n2 + 1] = n4 * n10;
                }
            }
        }
        this.ndfft(this.data, array, 2, -1);
        n *= 0.0625f;
        for (int l = 0; l <= this.sampleCount; ++l) {
            for (int n11 = 0; n11 <= this.sampleCount; ++n11) {
                this.func[l][n11] = this.data[l * 2 + n11 * n2] * n;
                this.funci[l][n11] = this.data[l * 2 + n11 * n2 + 1] * n;
            }
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
    
    int getTermWidth() {
        return this.viewStatesMap.height / this.maxDispTerms;
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
            case 4: {
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
            case 3: {
                this.editP(x, y);
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
        final int n4 = termWidth * (this.selectedCoefX - 1) + n3 + this.viewStatesMap.x;
        final int n5 = termWidth * (this.selectedCoefY - 1) + n3 + this.viewStatesMap.y;
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
    
    void editX(int i, int j) {
        final int selectedGridX = this.selectedGridX;
        final int selectedGridY = this.selectedGridY;
        switch (this.mouseChooser.getSelectedIndex()) {
            case 1: {
                this.editXGauss(i, j);
            }
            case 2: {
                this.editXGaussP(i, j);
            }
            case 3: {
                this.editXSquare(i, j);
            }
            case 4: {
                this.editXCircle(i, j);
            }
            case 0: {
                this.findGridPoint2D(this.viewXMap, i, j);
                this.editXEigen();
            }
            default: {
                this.findGridPoint2D(this.viewXMap, i, j);
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
        }
    }
    
    void editP(final int n, final int n2) {
        final int selectedGridX = this.selectedGridX;
        final int selectedGridY = this.selectedGridY;
        switch (this.mouseChooser.getSelectedIndex()) {
            case 1: {
                this.editPGauss(n, n2);
            }
            case 0: {
                this.findGridPoint2D(this.viewPMap, n, n2);
                this.editPEigen(n, n2);
            }
            default: {}
        }
    }
    
    int sign(final int n) {
        return (n < 0) ? -1 : ((n == 0) ? 0 : 1);
    }
    
    int abs(final int n) {
        return (n < 0) ? (-n) : n;
    }
    
    void editFuncPoint(final int n, final int n2, final double n3) {
        if (!this.dragSet && !this.dragClear) {
            this.dragClear = (this.func[n][n2] > 0.1);
            this.dragSet = !this.dragClear;
        }
        this.func[n][n2] = (this.dragSet ? ((float)n3) : 0.0f);
        final boolean b = true;
        this.editingFunc = b;
        this.dragStop = b;
        this.cv.repaint(this.pause);
    }
    
    void editXEigen() {
        for (int i = 0; i != this.maxTerms; ++i) {
            for (int j = 0; j != this.maxTerms; ++j) {
                this.func[i][j] = 0.0f;
            }
        }
        this.func[this.selectedGridX][this.selectedGridY] = 1.0f;
        this.transform(true);
    }
    
    void editPEigen(final int n, final int n2) {
        this.getMomentumCoords(this.viewPMap, n, n2);
        for (int i = 0; i <= this.maxTerms; ++i) {
            for (int j = 0; j <= this.maxTerms; ++j) {
                final int n3 = i - this.maxTerms / 2;
                final int n4 = j - this.maxTerms / 2;
                final double cos = Math.cos(this.momentumX * n3);
                final double cos2 = Math.cos(this.momentumY * n4);
                final double sin = Math.sin(this.momentumX * n3);
                final double sin2 = Math.sin(this.momentumY * n4);
                this.func[i][j] = (float)(cos * cos2 - sin * sin2);
                this.funci[i][j] = (float)(cos * sin2 + cos2 * sin);
            }
        }
        this.transform(false);
    }
    
    void editXGauss(final int n, final int n2) {
        final int n3 = n - this.dragX + 5;
        final int n4 = n2 - this.dragY + 5;
        final double n5 = 1.0 / (this.abs(n3) + 1.0E-4);
        final double n6 = 1.0 / (this.abs(n4) + 1.0E-4);
        final double lastGaussWx = -n5 * n5 * 10.0;
        final double lastGaussWy = -n6 * n6 * 10.0;
        this.lastGaussWx = lastGaussWx;
        this.lastGaussWy = lastGaussWy;
        final double n7 = 32.0 / this.sampleCount;
        final double n8 = n7 * n7;
        final double n9 = lastGaussWx * (this.aspectRatio * this.aspectRatio);
        for (int i = 0; i != this.maxTerms; ++i) {
            for (int j = 0; j != this.maxTerms; ++j) {
                final int n10 = i - this.selectedGridX;
                final int n11 = j - this.selectedGridY;
                this.func[i][j] = (float)Math.exp(n8 * (n9 * n10 * n10 + lastGaussWy * n11 * n11));
            }
        }
        this.transform(true);
    }
    
    void editXGaussP(final int n, final int n2) {
        this.getMomentumCoords(this.viewXMap, n - this.dragX + this.viewXMap.x + this.viewXMap.width / 2, n2 - this.dragY + this.viewXMap.y + this.viewXMap.height / 2);
        final double lastGaussWx = this.lastGaussWx;
        final double lastGaussWy = this.lastGaussWy;
        final double n3 = lastGaussWx * (this.aspectRatio * this.aspectRatio);
        final double n4 = 32.0 / this.sampleCount;
        final double n5 = n4 * n4;
        for (int i = 0; i <= this.maxTerms; ++i) {
            for (int j = 0; j <= this.maxTerms; ++j) {
                final int n6 = i - this.selectedGridX;
                final int n7 = j - this.selectedGridY;
                final double exp = Math.exp(n5 * (n3 * n6 * n6 + lastGaussWy * n7 * n7));
                final double cos = Math.cos(this.momentumX * n6);
                final double cos2 = Math.cos(this.momentumY * n7);
                final double sin = Math.sin(this.momentumX * n6);
                final double sin2 = Math.sin(this.momentumY * n7);
                this.func[i][j] = (float)(exp * (cos * cos2 - sin * sin2));
                this.funci[i][j] = (float)(exp * (cos * sin2 + cos2 * sin));
            }
        }
        this.transform(false);
    }
    
    void editXSquare(final int n, final int n2) {
        this.doBlank();
        final int n3 = this.maxTerms + 1;
        int n4 = (n - this.viewXMap.x) * n3 / this.viewXMap.width;
        int n5 = (n2 - this.viewXMap.y) * n3 / this.viewXMap.height;
        int n6 = (this.dragX - this.viewXMap.x) * n3 / this.viewXMap.width;
        int n7 = (this.dragY - this.viewXMap.y) * n3 / this.viewXMap.height;
        if (n6 < n4) {
            final int n8 = n4;
            n4 = n6;
            n6 = n8;
        }
        if (n7 < n5) {
            final int n9 = n5;
            n5 = n7;
            n7 = n9;
        }
        for (int i = n4; i <= n6; ++i) {
            for (int j = n5; j <= n7; ++j) {
                this.func[i][j] = 1.0f;
            }
        }
        this.transform(true);
    }
    
    void editXCircle(final int n, final int n2) {
        this.doBlank();
        final int n3 = this.maxTerms + 1;
        final int n4 = (n - this.viewXMap.x) * n3 / this.viewXMap.width;
        final int n5 = (n2 - this.viewXMap.y) * n3 / this.viewXMap.height;
        final int n6 = (this.dragX - this.viewXMap.x) * n3 / this.viewXMap.width;
        final int n7 = (this.dragY - this.viewXMap.y) * n3 / this.viewXMap.height;
        final double n8 = 1.0 / this.abs(n4 - n6);
        final double n9 = 1.0 / this.abs(n5 - n7);
        final double n10 = n8 * n8;
        final double n11 = n9 * n9;
        for (int i = 1; i != this.sampleCount; ++i) {
            for (int j = 1; j != this.sampleCount; ++j) {
                if ((i - n6) * (i - n6) * n10 + (j - n7) * (j - n7) * n11 <= 1.0) {
                    this.func[i][j] = 1.0f;
                }
            }
        }
        this.transform(true);
    }
    
    void getMomentumCoords(final View view, final int n, final int n2) {
        final int n3 = (101 - this.pZoomBar.getValue()) * (this.maxTerms * 2) / 100;
        this.momentumX = ((n - view.x - 1) * n3 / (view.width - 2) - n3 / 2) * 3.141592653589793 / this.maxTerms;
        this.momentumY = ((n2 - view.y - 1) * n3 / (view.height - 2) - n3 / 2) * 3.141592653589793 / this.maxTerms;
        final double n4 = 2.6;
        if (this.momentumX > n4) {
            this.momentumX = n4;
        }
        if (this.momentumY > n4) {
            this.momentumY = n4;
        }
        if (this.momentumX < -n4) {
            this.momentumX = -n4;
        }
        if (this.momentumY < -n4) {
            this.momentumY = -n4;
        }
    }
    
    void editPGauss(final int n, final int n2) {
        final int n3 = n - this.dragX;
        final int n4 = n2 - this.dragY;
        final double n5 = this.aspectRatio / (this.abs(n3) + 1.0E-4);
        final double n6 = 1.0 / (this.abs(n4) + 1.0E-4);
        final double n7 = -n5 * n5 * 10.0;
        final double n8 = -n6 * n6 * 10.0;
        this.getMomentumCoords(this.viewPMap, this.dragX, this.dragY);
        for (int i = 0; i <= this.maxTerms; ++i) {
            for (int j = 0; j <= this.maxTerms; ++j) {
                final int n9 = i - this.maxTerms / 2;
                final int n10 = j - this.maxTerms / 2;
                final double exp = Math.exp(n7 * n9 * n9 + n8 * n10 * n10);
                final double cos = Math.cos(this.momentumX * n9);
                final double cos2 = Math.cos(this.momentumY * n10);
                final double sin = Math.sin(this.momentumX * n9);
                final double sin2 = Math.sin(this.momentumY * n10);
                this.func[i][j] = (float)(exp * (cos * cos2 - sin * sin2));
                this.funci[i][j] = (float)(exp * (cos * sin2 + cos2 * sin));
            }
        }
        this.transform(false);
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
    }
    
    public void adjustmentValueChanged(final AdjustmentEvent adjustmentEvent) {
        System.out.print(((Scrollbar)adjustmentEvent.getSource()).getValue() + "\n");
        if (adjustmentEvent.getSource() == this.resBar) {
            this.setResolution();
        }
        if (adjustmentEvent.getSource() == this.aspectBar) {
            this.setResolution();
        }
        if (adjustmentEvent.getSource() == this.phasorBar) {
            this.maxDispTerms = this.phasorBar.getValue();
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
        if (this.sampleCount < 8) {
            this.sampleCount = 8;
        }
        final int maxTerms = this.maxTerms;
        this.maxTerms = this.sampleCount;
        System.out.print("sampleCount = " + this.maxTerms + "\n");
        final double[][] magcoef = this.magcoef;
        final double[][] phasecoefadj = this.phasecoefadj;
        this.magcoef = new double[this.maxTerms][this.maxTerms];
        this.phasecoef = new double[this.maxTerms][this.maxTerms];
        this.phasecoefcos = new double[this.maxTerms][this.maxTerms];
        this.phasecoefsin = new double[this.maxTerms][this.maxTerms];
        this.phasecoefadj = new double[this.maxTerms][this.maxTerms];
        this.func = new float[this.maxTerms + 1][this.maxTerms + 1];
        this.funci = new float[this.maxTerms + 1][this.maxTerms + 1];
        final float[][] array = null;
        this.pfunci = array;
        this.pfuncr = array;
        this.step = 3.141592653589793 / this.sampleCount;
        this.aspectRatio = this.aspectBar.getValue() / 10.0;
        this.data = new float[this.maxTerms * this.maxTerms * 2 * 4];
        this.elevels = new double[this.maxTerms][this.maxTerms];
        for (int i = 0; i != this.maxTerms; ++i) {
            for (int j = 0; j != this.maxTerms; ++j) {
                this.elevels[i][j] = i * i / (this.aspectRatio * this.aspectRatio) + j * j;
            }
        }
        final double n = 0.01 / this.elevels[1][1];
        for (int k = 0; k != this.maxTerms; ++k) {
            for (int l = 0; l != this.maxTerms; ++l) {
                final double[] array2 = this.elevels[k];
                final int n2 = l;
                array2[n2] *= n;
            }
        }
        if (magcoef != null) {
            for (int n3 = 0; n3 != maxTerms && n3 != this.maxTerms; ++n3) {
                for (int n4 = 0; n4 != maxTerms && n4 != this.maxTerms; ++n4) {
                    this.magcoef[n3][n4] = magcoef[n3][n4];
                    this.phasecoefadj[n3][n4] = phasecoefadj[n3][n4];
                }
            }
        }
        this.setupDisplay();
    }
    
    void findGridPoint2D(final View view, final int n, final int n2) {
        final int n3 = this.maxTerms + 1;
        this.selectedGridX = (n - view.x) * n3 / view.width;
        this.selectedGridY = (n2 - view.y) * n3 / view.height;
        final int n4 = 1;
        if (this.selectedGridX < n4) {
            this.selectedGridX = n4;
        }
        if (this.selectedGridY < n4) {
            this.selectedGridY = n4;
        }
        if (this.selectedGridX > this.sampleCount - n4) {
            this.selectedGridX = this.sampleCount - n4;
        }
        if (this.selectedGridY > this.sampleCount - n4) {
            this.selectedGridY = this.sampleCount - n4;
        }
        this.selectedGridFunc = this.func[this.selectedGridX][this.selectedGridY];
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
        else if (this.viewPMap != null && this.viewPMap.inside(x, y)) {
            this.selection = 3;
        }
        else if (this.viewPotential != null && this.viewPotential.contains(x, y)) {
            this.selection = 1;
            this.findStateByEnergy(y);
        }
        else if (this.viewStatesMap != null && this.viewStatesMap.inside(x, y)) {
            final int termWidth = this.getTermWidth();
            this.selectedCoefX = (x - this.viewStatesMap.x) / termWidth + 1;
            this.selectedCoefY = (y - this.viewStatesMap.y) / termWidth + 1;
            if (this.selectedCoefX > this.maxDispTerms) {
                final int n2 = -1;
                this.selectedCoefY = n2;
                this.selectedCoefX = n2;
            }
            if (this.selectedCoefY > this.maxDispTerms) {
                final int n3 = -1;
                this.selectedCoefY = n3;
                this.selectedCoefX = n3;
            }
            if (this.selectedCoefX <= 0 || this.selectedCoefY <= 0) {
                final int n4 = -1;
                this.selectedCoefY = n4;
                this.selectedCoefX = n4;
            }
            if (this.selectedCoefX != -1 && this.selectedCoefY != -1) {
                this.selection = 4;
            }
        }
        if (this.selectedCoefX != selectedCoefX || this.selectedCoefY != selectedCoefY) {
            this.cv.repaint(this.pause);
        }
    }
    
    void findStateByEnergy(final int n) {
        final int n2 = this.viewPotential.y + this.viewPotential.height - 5;
        final double n3 = 200.0;
        double n4 = 100.0;
        for (int i = 1; i != this.sampleCount; ++i) {
            for (int j = 1; j != this.sampleCount; ++j) {
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
        if (this.selection == 4) {
            this.editMagClick();
        }
        if (mouseEvent.getClickCount() == 2 && this.selectedCoefX != -1) {
            this.enterSelectedState();
        }
    }
    
    void enterSelectedState() {
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
        this.mouseMoved(mouseEvent);
        if ((mouseEvent.getModifiers() & 0x10) == 0x0) {
            return;
        }
        if (this.selection == 2) {
            this.findGridPoint2D(this.viewXMap, mouseEvent.getX(), mouseEvent.getY());
        }
        else if (this.selection == 3) {
            this.findGridPoint2D(this.viewPMap, mouseEvent.getX(), mouseEvent.getY());
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
        if (itemEvent.getItemSelectable() instanceof CheckboxMenuItem) {
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
    
    void ndfft(final float[] array, final int[] array2, final int n, final int n2) {
        int n3 = 1;
        int n4 = 1;
        final float n5 = (float)(n2 * 2 * 3.141592653589793);
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
                            final float n14 = array[n12];
                            final float n15 = array[n12 + 1];
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
                final float n19 = n5 / (n18 / n8);
                float n20 = 1.0f;
                float n21 = 0.0f;
                final float n22 = (float)Math.sin(0.5 * n19);
                final float n23 = (float)(n22 * (n22 * -2.0));
                final float n24 = (float)Math.sin(n19);
                for (int n25 = 0; n25 < n17; n25 += n8) {
                    for (int n26 = n25; n26 < n25 + n8; n26 += 2) {
                        for (int n27 = n26; n27 < n10; n27 += n18) {
                            final int n28 = n27 + 1;
                            final int n29 = n27 + n17;
                            final int n30 = n29 + 1;
                            final float n31 = n20 * array[n29] - n21 * array[n30];
                            final float n32 = n20 * array[n30] + n21 * array[n29];
                            array[n29] = array[n27] - n31;
                            array[n30] = array[n28] - n32;
                            final int n33 = n27;
                            array[n33] += n31;
                            final int n34 = n28;
                            array[n34] += n32;
                        }
                    }
                    final float n35 = n20;
                    n20 += n20 * n23 - n21 * n24;
                    n21 += n21 * n23 + n35 * n24;
                }
            }
            n4 *= n6;
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
