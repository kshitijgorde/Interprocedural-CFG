import java.awt.Rectangle;
import java.awt.event.ItemEvent;
import org.netlib.lapack.Dstedc;
import org.netlib.util.intW;
import java.awt.Event;
import java.awt.event.ActionEvent;
import java.awt.event.ComponentEvent;
import java.awt.event.MouseEvent;
import java.awt.FontMetrics;
import java.awt.image.ImageObserver;
import java.awt.Cursor;
import java.awt.Graphics;
import java.awt.MenuBar;
import java.awt.Component;
import java.awt.LayoutManager;
import java.text.NumberFormat;
import java.awt.Color;
import java.awt.Label;
import java.util.Vector;
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
import java.awt.event.ActionListener;
import java.awt.event.ComponentListener;
import java.awt.Frame;

// 
// Decompiled by Procyon v0.5.30
// 

class QuantumStatesFrame extends Frame implements ComponentListener, ActionListener, MouseMotionListener, MouseListener, ItemListener, DecentScrollbarListener
{
    Thread engine;
    Dimension winSize;
    Image dbimage;
    Random random;
    int stateCount;
    int elevelCount;
    int maxStateCount;
    int sampleCount;
    int pSampleCount;
    double[][] modes;
    double[][] modesLeft;
    public static final double epsilon = 1.0E-5;
    public static final double epsilon2 = 0.003;
    public static final double phasorBaseEnergy = 1.0;
    public static final double infiniteEnergy = 1000.0;
    Button blankButton;
    Button groundButton;
    Button normalizeButton;
    Button maximizeButton;
    Button rescaleButton;
    Checkbox stoppedCheck;
    CheckboxMenuItem eCheckItem;
    CheckboxMenuItem xCheckItem;
    CheckboxMenuItem pCheckItem;
    CheckboxMenuItem densityCheckItem;
    CheckboxMenuItem sumAllCheckItem;
    CheckboxMenuItem parityCheckItem;
    CheckboxMenuItem currentCheckItem;
    CheckboxMenuItem leftRightCheckItem;
    CheckboxMenuItem infoCheckItem;
    CheckboxMenuItem statesCheckItem;
    CheckboxMenuItem expectCheckItem;
    CheckboxMenuItem uncertaintyCheckItem;
    CheckboxMenuItem probCheckItem;
    CheckboxMenuItem probPhaseCheckItem;
    CheckboxMenuItem reImCheckItem;
    CheckboxMenuItem magPhaseCheckItem;
    CheckboxMenuItem alwaysNormItem;
    CheckboxMenuItem alwaysMaxItem;
    CheckboxMenuItem adiabaticItem;
    Menu waveFunctionMenu;
    MenuItem measureEItem;
    MenuItem measureXItem;
    MenuItem exitItem;
    Choice mouseChooser;
    Choice setupChooser;
    Vector setupList;
    Setup setup;
    DecentScrollbar forceBar;
    DecentScrollbar speedBar;
    DecentScrollbar resBar;
    DecentScrollbar massBar;
    DecentScrollbar aux1Bar;
    DecentScrollbar aux2Bar;
    DecentScrollbar aux3Bar;
    Label aux1Label;
    Label aux2Label;
    Label aux3Label;
    View viewPotential;
    View viewX;
    View viewP;
    View viewParity;
    View viewStates;
    View viewSumAll;
    View viewDensity;
    View viewCurrent;
    View viewLeft;
    View viewRight;
    View viewInfo;
    View[] viewList;
    int viewCount;
    double[] magcoef;
    double[] phasecoef;
    double[] phasecoefcos;
    double[] phasecoefsin;
    double[] phasecoefadj;
    double[] elevels;
    double[] dispmax;
    static final double pi = 3.141592653589793;
    double step;
    double[] func;
    double[] funci;
    double[] pdata;
    double[] pdatar;
    double[] pdatai;
    double[] currentData;
    double[] parityData;
    double[] pot;
    double mass;
    int selectedCoef;
    int selectedPaneHandle;
    static final int stateBuffer = 5;
    double selectedPState;
    static final int SEL_NONE = 0;
    static final int SEL_POTENTIAL = 1;
    static final int SEL_X = 2;
    static final int SEL_P = 3;
    static final int SEL_STATES = 4;
    static final int SEL_HANDLE = 5;
    static final int MOUSE_EIGEN = 0;
    static final int MOUSE_EDIT = 1;
    static final int MOUSE_GAUSS = 2;
    static final int MOUSE_TRANSLATE = 3;
    int selection;
    int dragX;
    int dragY;
    int[] xpoints;
    int[] ypoints;
    boolean dragging;
    boolean startup;
    boolean selectGround;
    boolean statesChanged;
    boolean adjustingStates;
    boolean adjustingWaveFunc;
    boolean setupModified;
    double t;
    int pause;
    static final int phaseColorCount = 480;
    Color[] phaseColors;
    FFT fft;
    QuantumStatesCanvas cv;
    QuantumStates applet;
    NumberFormat showFormat;
    long lastTime;
    int stateColSize;
    int stateSize;
    
    public String getAppletInfo() {
        return "QuantumStates by Paul Falstad";
    }
    
    int getrand(final int n) {
        int nextInt = this.random.nextInt();
        if (nextInt < 0) {
            nextInt = -nextInt;
        }
        return nextInt % n;
    }
    
    QuantumStatesFrame(final QuantumStates applet) {
        super("1-d Quantum States Applet v1.6a");
        this.engine = null;
        this.maxStateCount = 500;
        this.sampleCount = 80;
        this.applet = applet;
    }
    
    public void init() {
        this.startup = true;
        this.xpoints = new int[5];
        this.ypoints = new int[5];
        this.setupList = new Vector();
        for (Setup next = new InfiniteWellSetup(); next != null; next = next.createNext()) {
            this.setupList.addElement(next);
        }
        this.selectedCoef = -1;
        this.setLayout(new QuantumStatesLayout());
        (this.cv = new QuantumStatesCanvas(this)).addComponentListener(this);
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
        menu2.add(this.pCheckItem = this.getCheckItem("Momentum"));
        this.pCheckItem.setState(true);
        menu2.add(this.sumAllCheckItem = this.getCheckItem("Sum All States"));
        menu2.add(this.parityCheckItem = this.getCheckItem("Parity"));
        menu2.add(this.currentCheckItem = this.getCheckItem("Probability Current"));
        menu2.add(this.leftRightCheckItem = this.getCheckItem("Left/Right Waves"));
        menu2.add(this.infoCheckItem = this.getCheckItem("Values/Dimensions"));
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
        this.probPhaseCheckItem.setState(true);
        menu3.add(this.reImCheckItem = this.getCheckItem("Real + Imaginary Parts"));
        menu3.add(this.magPhaseCheckItem = this.getCheckItem("Magnitude + Phase"));
        final Menu menu4 = new Menu("Measure");
        menuBar.add(menu4);
        menu4.add(this.measureEItem = this.getMenuItem("Measure Energy"));
        menu4.add(this.measureXItem = this.getMenuItem("Measure Position"));
        this.setMenuBar(menuBar);
        final Menu menu5 = new Menu("Options");
        menuBar.add(menu5);
        menu5.add(this.alwaysNormItem = this.getCheckItem("Always Normalize"));
        menu5.add(this.alwaysMaxItem = this.getCheckItem("Always Maximize"));
        menu5.add(this.adiabaticItem = this.getCheckItem("Adiabatic Changes"));
        this.adiabaticItem.setState(true);
        this.setMenuBar(menuBar);
        this.setupChooser = new Choice();
        for (int i = 0; i != this.setupList.size(); ++i) {
            this.setupChooser.add("Setup: " + ((Setup)this.setupList.elementAt(i)).getName());
        }
        this.setup = this.setupList.elementAt(0);
        this.setupChooser.addItemListener(this);
        this.add(this.setupChooser);
        (this.mouseChooser = new Choice()).add("Mouse = Set Eigenstate");
        this.mouseChooser.add("Mouse = Edit Function");
        this.mouseChooser.add("Mouse = Create Gaussian");
        this.mouseChooser.add("Mouse = Translate Function");
        this.mouseChooser.addItemListener(this);
        this.add(this.mouseChooser);
        this.mouseChooser.select(2);
        this.add(this.blankButton = new Button("Clear"));
        this.blankButton.addActionListener(this);
        this.add(this.normalizeButton = new Button("Normalize"));
        this.normalizeButton.addActionListener(this);
        this.add(this.maximizeButton = new Button("Maximize"));
        this.maximizeButton.addActionListener(this);
        this.add(this.groundButton = new Button("Ground State"));
        this.groundButton.addActionListener(this);
        this.add(this.rescaleButton = new Button("Rescale Graphs"));
        this.rescaleButton.addActionListener(this);
        (this.stoppedCheck = new Checkbox("Stopped")).addItemListener(this);
        this.add(this.stoppedCheck);
        this.add(new Label("Simulation Speed", 1));
        this.add(this.speedBar = new DecentScrollbar(this, 80, 1, 300));
        this.add(new Label("Resolution", 1));
        this.add(this.resBar = new DecentScrollbar(this, 260, 180, this.maxStateCount));
        this.add(new Label("Particle Mass", 1));
        this.add(this.massBar = new DecentScrollbar(this, 16, 1, 100));
        this.add(this.aux1Label = new Label("Aux 1", 1));
        this.add(this.aux1Bar = new DecentScrollbar(this, 50, 1, 100));
        this.add(this.aux2Label = new Label("Aux 2", 1));
        this.add(this.aux2Bar = new DecentScrollbar(this, 50, 1, 100));
        this.add(this.aux3Label = new Label("Aux 3", 1));
        this.add(this.aux3Bar = new DecentScrollbar(this, 50, 1, 100));
        try {
            final String parameter = this.applet.getParameter("PAUSE");
            if (parameter != null) {
                this.pause = Integer.parseInt(parameter);
            }
        }
        catch (Exception ex) {}
        this.magcoef = new double[this.maxStateCount];
        this.phasecoef = new double[this.maxStateCount];
        this.phasecoefcos = new double[this.maxStateCount];
        this.phasecoefsin = new double[this.maxStateCount];
        this.phasecoefadj = new double[this.maxStateCount];
        this.dispmax = new double[this.maxStateCount];
        this.setResolution();
        this.phaseColors = new Color[481];
        for (int j = 0; j != 480; ++j) {
            final int n = 80;
            final int n2 = j % n * 255 / n;
            final int n3 = 255 - n2;
            Color color = null;
            switch (j / n) {
                case 0: {
                    color = new Color(255, n2, 0);
                    break;
                }
                case 1: {
                    color = new Color(n3, 255, 0);
                    break;
                }
                case 2: {
                    color = new Color(0, 255, n2);
                    break;
                }
                case 3: {
                    color = new Color(0, n3, 255);
                    break;
                }
                case 4: {
                    color = new Color(n2, 0, 255);
                    break;
                }
                case 5: {
                    color = new Color(255, 0, n3);
                    break;
                }
            }
            this.phaseColors[j] = color;
        }
        this.phaseColors[480] = this.phaseColors[0];
        this.random = new Random();
        this.reinit();
        this.cv.setBackground(Color.black);
        this.cv.setForeground(Color.lightGray);
        (this.showFormat = NumberFormat.getInstance()).setMaximumFractionDigits(2);
        this.resize(750, 600);
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
    
    void reinit() {
        this.doSetup();
    }
    
    void handleResize() {
        final Dimension size = this.cv.getSize();
        this.winSize = size;
        final Dimension dimension = size;
        if (this.winSize.width == 0) {
            return;
        }
        final int n = (this.viewPotential == null) ? 0 : this.viewPotential.height;
        final int n2 = (this.viewStates == null) ? 50 : this.viewStates.height;
        final View viewX = null;
        this.viewDensity = viewX;
        this.viewSumAll = viewX;
        this.viewInfo = viewX;
        this.viewPotential = viewX;
        this.viewStates = viewX;
        this.viewRight = viewX;
        this.viewLeft = viewX;
        this.viewCurrent = viewX;
        this.viewParity = viewX;
        this.viewP = viewX;
        this.viewX = viewX;
        this.viewList = new View[20];
        int viewCount = 0;
        if (this.eCheckItem.getState()) {
            this.viewList[viewCount++] = (this.viewPotential = new View());
        }
        if (this.xCheckItem.getState()) {
            this.viewList[viewCount++] = (this.viewX = new View());
        }
        if (this.sumAllCheckItem.getState()) {
            this.viewList[viewCount++] = (this.viewSumAll = new View());
        }
        if (this.pCheckItem.getState()) {
            this.viewList[viewCount++] = (this.viewP = new View());
        }
        if (this.parityCheckItem.getState()) {
            this.viewList[viewCount++] = (this.viewParity = new View());
        }
        if (this.currentCheckItem.getState()) {
            this.viewList[viewCount++] = (this.viewCurrent = new View());
        }
        if (this.leftRightCheckItem.getState() && this.setup.allowLeftRight()) {
            this.viewList[viewCount++] = (this.viewLeft = new View());
            this.viewList[viewCount++] = (this.viewRight = new View());
        }
        if (this.infoCheckItem.getState()) {
            this.viewList[viewCount++] = (this.viewInfo = new View());
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
        final int n3 = 65;
        if (this.viewInfo != null) {
            --viewCount2;
            height -= n3;
        }
        int y = 0;
        for (int i = 0; i != this.viewCount; ++i) {
            final View view = this.viewList[i];
            int height2 = height / viewCount2;
            if (view == this.viewPotential && n > 0) {
                height2 = n;
            }
            else if (view == this.viewStates && n2 > 0) {
                height2 = n2;
            }
            else if (view == this.viewInfo) {
                height2 = n3;
            }
            view.x = 0;
            view.width = this.winSize.width;
            view.y = y;
            view.height = height2;
            y += height2;
        }
        this.setGraphLines();
        this.dbimage = this.createImage(dimension.width, dimension.height);
    }
    
    void setGraphLines() {
        for (int i = 0; i != this.viewCount; ++i) {
            final View view = this.viewList[i];
            view.mid_y = view.y + view.height / 2;
            view.ymult = 0.9 * view.height / 2.0;
            view.lower_y = (int)(view.mid_y + view.ymult);
            view.ymult2 = view.ymult * 2.0;
        }
    }
    
    void doGround() {
        for (int i = 0; i != this.stateCount; ++i) {
            this.magcoef[i] = 0.0;
        }
        this.magcoef[0] = 1.0;
        this.t = 0.0;
        this.rescaleGraphs();
    }
    
    void doBlank() {
        this.t = 0.0;
        if (this.winSize != null && this.winSize.width > 0) {
            this.dbimage = this.createImage(this.winSize.width, this.winSize.height);
        }
        for (int i = 0; i != this.sampleCount; ++i) {
            this.func[i] = (this.funci[i] = 0.0);
        }
        for (int j = 0; j != this.stateCount; ++j) {
            this.magcoef[j] = 0.0;
        }
    }
    
    void normalize() {
        double n = 0.0;
        for (int i = 0; i != this.stateCount; ++i) {
            n += this.magcoef[i] * this.magcoef[i];
        }
        if (n == 0.0) {
            return;
        }
        final double n2 = 1.0 / Math.sqrt(n);
        for (int j = 0; j != this.stateCount; ++j) {
            final double[] magcoef = this.magcoef;
            final int n3 = j;
            magcoef[n3] *= n2;
        }
        this.cv.repaint(this.pause);
    }
    
    void maximize() {
        double n = 0.0;
        for (int i = 0; i != this.stateCount; ++i) {
            if (this.magcoef[i] > n) {
                n = this.magcoef[i];
            }
        }
        if (n == 0.0) {
            return;
        }
        for (int j = 0; j != this.stateCount; ++j) {
            final double[] magcoef = this.magcoef;
            final int n2 = j;
            magcoef[n2] *= 1.0 / n;
        }
        this.cv.repaint(this.pause);
    }
    
    void rescaleGraphs() {
        for (int i = 0; i != this.viewCount; ++i) {
            this.viewList[i].scale = 0.0;
        }
    }
    
    void transform() {
        this.t = 0.0;
        for (int i = 0; i != this.stateCount; ++i) {
            double n = 0.0;
            double n2 = 0.0;
            for (int j = 1; j != this.sampleCount; ++j) {
                n += this.modes[i][j] * this.func[j];
                n2 += this.modes[i][j] * this.funci[j];
            }
            if (n < 1.0E-5 && n > -1.0E-5) {
                n = 0.0;
            }
            if (n2 < 1.0E-5 && n2 > -1.0E-5) {
                n2 = 0.0;
            }
            this.magcoef[i] = Math.sqrt(n * n + n2 * n2);
            final double atan2 = Math.atan2(n2, n);
            this.phasecoefadj[i] = atan2;
            this.phasecoef[i] = atan2;
        }
        if (this.alwaysNormItem.getState()) {
            this.normalize();
        }
        if (this.alwaysMaxItem.getState()) {
            this.maximize();
        }
    }
    
    void centerString(final Graphics graphics, final String s, final int n) {
        graphics.drawString(s, (this.winSize.width - graphics.getFontMetrics().stringWidth(s)) / 2, n);
    }
    
    public void paint(final Graphics graphics) {
        this.cv.repaint();
    }
    
    public void updateQuantumStates(final Graphics graphics) {
        final Graphics graphics2 = this.dbimage.getGraphics();
        if (this.winSize == null || this.winSize.width == 0) {
            return;
        }
        boolean b = true;
        if (!this.stoppedCheck.getState() && !this.dragging && !this.adjustingStates) {
            final double n = Math.exp(this.speedBar.getValue() / 20.0) * 0.02;
            final long currentTimeMillis = System.currentTimeMillis();
            if (this.lastTime == 0L) {
                this.lastTime = currentTimeMillis;
            }
            this.t += n * (currentTimeMillis - this.lastTime) * 0.058823529411764705;
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
        int n2 = -1;
        for (int i = 1; i != this.viewCount; ++i) {
            graphics2.setColor((i == this.selectedPaneHandle) ? Color.yellow : Color.gray);
            graphics2.drawLine(0, this.viewList[i].y, this.winSize.width, this.viewList[i].y);
        }
        if (this.statesChanged) {
            this.cv.setCursor(Cursor.getPredefinedCursor(3));
            if (this.adjustingStates) {
                this.genStates(false);
            }
            else {
                graphics.setColor(this.cv.getBackground());
                final FontMetrics fontMetrics = graphics.getFontMetrics();
                final String s = "Calculating...";
                graphics.fillRect(0, this.winSize.height - 30, 20 + fontMetrics.stringWidth(s), 30);
                graphics.setColor(Color.white);
                graphics.drawString(s, 10, this.winSize.height - 10);
                this.genStates(true);
                if (!this.adiabaticItem.getState()) {
                    this.transform();
                }
                else {
                    this.rescaleGraphs();
                }
            }
            this.cv.setCursor(null);
            this.statesChanged = false;
            if (this.startup) {
                this.magcoef[0] = (this.magcoef[1] = 1.0);
                this.startup = false;
            }
            else if (this.selectGround) {
                this.magcoef[0] = 1.0;
                this.selectGround = false;
            }
        }
        int n3 = -1;
        double n4 = 0.0;
        if (!this.adjustingStates) {
            for (int j = 0; j != this.stateCount; ++j) {
                if (this.magcoef[j] < 1.0E-5 && this.magcoef[j] > -1.0E-5) {
                    final double[] magcoef = this.magcoef;
                    final int n5 = j;
                    final double[] phasecoef = this.phasecoef;
                    final int n6 = j;
                    final double[] phasecoefadj = this.phasecoefadj;
                    final int n7 = j;
                    final double n8 = 0.0;
                    phasecoefadj[n7] = n8;
                    magcoef[n5] = (phasecoef[n6] = n8);
                }
                else {
                    this.phasecoef[j] = (-(this.elevels[j] + 1.0) * this.t + this.phasecoefadj[j]) % 6.283185307179586;
                    if (this.phasecoef[j] > 3.141592653589793) {
                        final double[] phasecoef2 = this.phasecoef;
                        final int n9 = j;
                        phasecoef2[n9] -= 6.283185307179586;
                    }
                    else if (this.phasecoef[j] < -3.141592653589793) {
                        final double[] phasecoef3 = this.phasecoef;
                        final int n10 = j;
                        phasecoef3[n10] += 6.283185307179586;
                    }
                    this.phasecoefcos[j] = Math.cos(this.phasecoef[j]);
                    this.phasecoefsin[j] = Math.sin(this.phasecoef[j]);
                    n4 += this.magcoef[j] * this.magcoef[j];
                }
            }
        }
        double n11 = 1.0 / n4;
        double sqrt = Math.sqrt(n11);
        if (n4 == 0.0) {
            n11 = (sqrt = 0.0);
        }
        if (this.viewPotential != null) {
            final int mid_y = this.viewPotential.mid_y;
            final double ymult = this.viewPotential.ymult;
            this.viewPotential.scale = 1.0;
            graphics2.setColor(color2);
            graphics2.drawLine(this.winSize.width / 2, mid_y - (int)ymult, this.winSize.width / 2, mid_y + (int)ymult);
            graphics2.setColor(Color.gray);
            for (int k = 0; k != this.elevelCount; ++k) {
                if (k == this.stateCount) {
                    graphics2.setColor(Color.darkGray);
                }
                final int n12 = mid_y - (int)(ymult * this.elevels[k]);
                graphics2.drawLine(0, n12, this.winSize.width, n12);
            }
            graphics2.setColor(Color.white);
            for (int l = 0; l != this.sampleCount; ++l) {
                final int n13 = this.winSize.width * l / this.sampleCount;
                final int n14 = mid_y - (int)(ymult * this.pot[l]);
                if (n3 != -1) {
                    graphics2.drawLine(n3, n2, n13, n14);
                }
                n3 = n13;
                n2 = n14;
            }
            if (!this.adjustingStates && n4 != 0.0 && (this.expectCheckItem.getState() || this.uncertaintyCheckItem.getState())) {
                double n15 = 0.0;
                double n16 = 0.0;
                for (int n17 = 0; n17 != this.stateCount; ++n17) {
                    final double n18 = this.magcoef[n17] * this.magcoef[n17] * n11;
                    n15 += n18 * this.elevels[n17];
                    n16 += n18 * this.elevels[n17] * this.elevels[n17];
                }
                double sqrt2 = Math.sqrt(n16 - n15 * n15);
                if (this.uncertaintyCheckItem.getState()) {
                    if (sqrt2 < 0.0) {
                        sqrt2 = 0.0;
                    }
                    graphics2.setColor(Color.blue);
                    final int n19 = mid_y - (int)(ymult * (n15 + sqrt2));
                    graphics2.drawLine(0, n19, this.winSize.width, n19);
                    final int n20 = mid_y - (int)(ymult * (n15 - sqrt2));
                    if (n15 - sqrt2 >= -1.0) {
                        graphics2.drawLine(0, n20, this.winSize.width, n20);
                    }
                }
                if (this.expectCheckItem.getState()) {
                    final int n21 = mid_y - (int)(ymult * n15);
                    graphics2.setColor(Color.red);
                    graphics2.drawLine(0, n21, this.winSize.width, n21);
                }
            }
            if (this.selectedCoef != -1 && !this.dragging) {
                graphics2.setColor(Color.yellow);
                final int n22 = mid_y - (int)(ymult * this.elevels[this.selectedCoef]);
                graphics2.drawLine(0, n22, this.winSize.width, n22);
            }
        }
        graphics2.setColor(Color.white);
        double n23 = 0.0;
        double n24 = 0.0;
        if (!this.adjustingStates && !this.adjustingWaveFunc) {
            for (int n25 = 0; n25 != this.sampleCount; ++n25) {
                final int n26 = this.winSize.width * n25 / this.sampleCount;
                double n27 = 0.0;
                double n28 = 0.0;
                for (int n29 = 0; n29 != this.stateCount; ++n29) {
                    n27 += this.magcoef[n29] * this.modes[n29][n25] * this.phasecoefcos[n29];
                    n28 += this.magcoef[n29] * this.modes[n29][n25] * this.phasecoefsin[n29];
                }
                final double n30 = n27 * sqrt;
                final double n31 = n28 * sqrt;
                this.func[n25] = n30;
                this.funci[n25] = n31;
                final double n32 = n30 * n30 + n31 * n31;
                n24 += n32 * n26;
                if (n32 > n23) {
                    n23 = n32;
                }
            }
        }
        else {
            for (int n33 = 0; n33 != this.sampleCount; ++n33) {
                final int n34 = this.winSize.width * n33 / this.sampleCount;
                final double n35 = this.func[n33];
                final double n36 = this.funci[n33];
                final double n37 = n35 * n35 + n36 * n36;
                n24 += n37 * n34;
                if (n37 > n23) {
                    n23 = n37;
                }
            }
        }
        if (this.viewX != null) {
            final int mid_y2 = this.viewX.mid_y;
            final double ymult2 = this.viewX.ymult;
            this.drawFunction(graphics2, this.viewX, this.func, this.funci, this.sampleCount, 0);
            if (this.selectedCoef != -1 && !this.dragging) {
                graphics2.setColor(Color.yellow);
                int n38 = -1;
                for (int n39 = 0; n39 != this.sampleCount; ++n39) {
                    final int n40 = this.winSize.width * n39 / this.sampleCount;
                    final int n41 = mid_y2 - (int)(ymult2 * (this.modes[this.selectedCoef][n39] / this.dispmax[this.selectedCoef]));
                    if (n38 != -1) {
                        graphics2.drawLine(n38, n2, n40, n41);
                    }
                    n38 = n40;
                    n2 = n41;
                }
            }
            if (this.selectedPState != 0.0) {
                graphics2.setColor(Color.yellow);
                int n42 = -1;
                for (int n43 = this.sampleCount * 2, n44 = 0; n44 != n43; ++n44) {
                    final int n45 = this.winSize.width * n44 / n43;
                    final int n46 = mid_y2 - (int)(ymult2 * Math.cos(this.selectedPState * (n44 - this.sampleCount) * 0.5));
                    if (n42 != -1) {
                        graphics2.drawLine(n42, n2, n45, n46);
                    }
                    n42 = n45;
                    n2 = n46;
                }
            }
        }
        if (this.viewP != null) {
            for (int n47 = 0; n47 != this.pSampleCount * 2; ++n47) {
                this.pdata[n47] = 0.0;
            }
            for (int n48 = 0; n48 != this.sampleCount; ++n48) {
                final int n49 = (n48 <= this.sampleCount / 2) ? ((this.sampleCount / 2 - n48) * 2) : ((this.pSampleCount - (n48 - this.sampleCount / 2)) * 2);
                this.pdata[n49] = this.func[this.sampleCount - 1 - n48];
                this.pdata[n49 + 1] = this.funci[this.sampleCount - 1 - n48];
            }
            this.fft.transform(this.pdata, false);
            final double n50 = 1.0 / Math.sqrt(this.pSampleCount);
            for (int n51 = 0; n51 != this.pSampleCount; ++n51) {
                final int n52 = (n51 <= this.pSampleCount / 2) ? ((this.pSampleCount / 2 - n51) * 2) : ((this.pSampleCount - (n51 - this.pSampleCount / 2)) * 2);
                this.pdatar[n51] = this.pdata[n52] * n50;
                this.pdatai[n51] = this.pdata[n52 + 1] * n50;
            }
            this.drawFunction(graphics2, this.viewP, this.pdatar, this.pdatai, this.pSampleCount / 2, this.pSampleCount / 4);
        }
        if (this.viewParity != null) {
            double n53 = 0.0;
            double n54 = 0.0;
            for (int n55 = 0; n55 != this.sampleCount; ++n55) {
                final double n56 = this.func[n55];
                final double n57 = this.funci[n55];
                final double n58 = this.func[this.sampleCount - 1 - n55];
                final double n59 = this.funci[this.sampleCount - 1 - n55];
                final double n60 = (n56 + n58) * (n56 + n58) + (n57 + n59) * (n57 + n59);
                final double n61 = (n56 - n58) * (n56 - n58) + (n57 - n59) * (n57 - n59);
                n53 += n60;
                n54 += n61;
            }
            this.parityData[90] = Math.sqrt(n53) / 2.0;
            this.parityData[10] = Math.sqrt(n54) / 2.0;
            this.drawFunction(graphics2, this.viewParity, this.parityData, null, 100, 0);
        }
        if (this.viewCurrent != null) {
            for (int n62 = 0; n62 != this.sampleCount - 1; ++n62) {
                this.currentData[n62] = this.func[n62] * (this.funci[n62 + 1] - this.funci[n62]) - this.funci[n62] * (this.func[n62 + 1] - this.func[n62]);
            }
            this.drawFunction(graphics2, this.viewCurrent, this.currentData, null, this.sampleCount, 0);
        }
        if (this.viewLeft != null && this.viewRight != null && !this.setupModified) {
            if (this.viewX != null) {
                final View viewLeft = this.viewLeft;
                final View viewRight = this.viewRight;
                final double scale = this.viewX.scale;
                viewRight.scale = scale;
                viewLeft.scale = scale;
            }
            if (this.setup instanceof HarmonicOscillatorSetup) {
                this.doOscLeftRight(graphics2);
            }
            else if (this.setup instanceof InfiniteWellSetup) {
                this.doBoxLeftRight(graphics2, sqrt);
            }
        }
        if (this.viewSumAll != null && !this.adjustingStates) {
            final double[] array = new double[this.sampleCount];
            for (int n63 = 0; n63 != this.stateCount; ++n63) {
                for (int n64 = 0; n64 != this.sampleCount; ++n64) {
                    final double[] array2 = array;
                    final int n65 = n64;
                    array2[n65] += this.modes[n63][n64] * this.modes[n63][n64];
                }
            }
            this.drawFunction(graphics2, this.viewSumAll, array, null, this.sampleCount, 0);
        }
        if (this.viewInfo != null) {
            final String[] array3 = new String[5];
            this.setup.getInfo(array3, 2);
            double n66 = 0.0;
            for (int n67 = 0; n67 != this.stateCount; ++n67) {
                n66 += this.magcoef[n67] * this.magcoef[n67] * n11 * this.elevels[n67];
            }
            graphics2.setColor(Color.white);
            array3[0] = "<E> = " + this.getEnergyText(n66, true);
            array3[1] = "t = " + this.getUnitText(this.t * 6.58212E-16 / this.convertEnergy(1.0, false), "s");
            String s2 = ", m = " + this.getUnitText(this.mass, "eV") + "/c^2";
            if (this.mass == 511000.0) {
                s2 += " (electron)";
            }
            final StringBuffer sb = new StringBuffer();
            final String[] array4 = array3;
            final int n68 = 0;
            array4[n68] = sb.append(array4[n68]).append(s2).toString();
            for (int n69 = 0; array3[n69] != null; ++n69) {
                final int n70 = (n69 + 1) * 15;
                if (n70 + 4 < this.viewInfo.height) {
                    this.centerString(graphics2, array3[n69], n70 + this.viewInfo.y);
                }
            }
        }
        if (this.viewStates != null && !this.adjustingStates) {
            this.stateColSize = this.winSize.width / 10;
            if (this.stateColSize < 20) {
                this.stateColSize = 20;
            }
            for (int stateColSize = this.stateColSize - 1; stateColSize >= 8; --stateColSize) {
                if (this.winSize.width / stateColSize * ((this.stateCount + stateColSize - 1) / stateColSize) <= this.viewStates.height - 5) {
                    this.stateColSize = stateColSize;
                }
            }
            this.stateSize = this.winSize.width / this.stateColSize;
            final int n71 = this.stateSize / 2;
            for (int n72 = 0; n72 != this.stateCount; ++n72) {
                final int n73 = this.stateSize * (n72 % this.stateColSize) + n71;
                final int n74 = this.stateSize * (n72 / this.stateColSize) + n71 + this.viewStates.y + 5;
                graphics2.setColor((n72 == this.selectedCoef) ? Color.yellow : ((this.magcoef[n72] == 0.0) ? color2 : Color.white));
                graphics2.drawOval(n73 - n71, n74 - n71, this.stateSize, this.stateSize);
                final int n75 = (int)(this.magcoef[n72] * this.phasecoefcos[n72] * n71);
                final int n76 = (int)(-this.magcoef[n72] * this.phasecoefsin[n72] * n71);
                graphics2.drawLine(n73, n74, n73 + n75, n74 + n76);
                graphics2.drawLine(n73 + n75 - 1, n74 + n76, n73 + n75 + 1, n74 + n76);
                graphics2.drawLine(n73 + n75, n74 + n76 - 1, n73 + n75, n74 + n76 + 1);
            }
        }
        graphics.drawImage(this.dbimage, 0, 0, this);
        if (!this.stoppedCheck.getState() && !b) {
            this.cv.repaint(this.pause);
        }
    }
    
    String getUnitText(final double n, final String s) {
        final double abs = Math.abs(n);
        if (abs < 1.0E-17) {
            return "0 " + s;
        }
        if (abs < 1.0E-12) {
            return this.showFormat.format(n * 1.0E15) + " f" + s;
        }
        if (abs < 1.0E-9) {
            return this.showFormat.format(n * 1.0E12) + " p" + s;
        }
        if (abs < 1.0E-6) {
            return this.showFormat.format(n * 1.0E9) + " n" + s;
        }
        if (abs < 0.001) {
            return this.showFormat.format(n * 1000000.0) + " u" + s;
        }
        if (abs < 1.0) {
            return this.showFormat.format(n * 1000.0) + " m" + s;
        }
        if (abs < 1000.0) {
            return this.showFormat.format(n) + " " + s;
        }
        if (abs < 1000000.0) {
            return this.showFormat.format(n * 0.001) + " k" + s;
        }
        if (abs < 1.0E9) {
            return this.showFormat.format(n * 1.0E-6) + " M" + s;
        }
        if (abs < 1.0E12) {
            return this.showFormat.format(n * 1.0E-9) + " G" + s;
        }
        if (abs < 1.0E15) {
            return this.showFormat.format(n * 1.0E-12) + " T" + s;
        }
        return n + " " + s;
    }
    
    String getEnergyText(final double n, final boolean b) {
        return this.getUnitText(this.convertEnergy(n, b), "eV");
    }
    
    String getLengthText(final double n) {
        return this.getUnitText(this.pointsToLength(n), "m");
    }
    
    double pointsToLength(final double n) {
        return 4.0E-9 * n / (this.sampleCount - 2.0);
    }
    
    double convertEnergy(double n, final boolean b) {
        final double n2 = 0.023502;
        if (b) {
            n += this.setup.getBaseEnergy();
        }
        return n * n2 / 0.0018801;
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
        if (!this.adjustingWaveFunc) {
            view.scale *= 1.001;
            if (view.scale > scale || view.scale == 0.0) {
                view.scale = scale;
            }
            if (view.scale > 1.0E8) {
                view.scale = 1.0E8;
            }
        }
        graphics.setColor(Color.gray);
        if ((this.probCheckItem.getState() || this.probPhaseCheckItem.getState() || this.magPhaseCheckItem.getState()) && array2 != null) {
            graphics.setColor(Color.white);
            final double n18 = view.ymult2 * view.scale;
            for (int j = 0; j != n; ++j) {
                final int n19 = this.winSize.width * j / (n - 1);
                final int n20 = j + n2;
                double sqrt3;
                if (!this.magPhaseCheckItem.getState()) {
                    sqrt3 = array[n20] * array[n20] + array2[n20] * array2[n20];
                }
                else {
                    sqrt3 = Math.sqrt(array[n20] * array[n20] + array2[n20] * array2[n20]);
                }
                if (!this.probCheckItem.getState()) {
                    graphics.setColor(this.phaseColors[(int)((Math.atan2(array2[n20], array[n20]) + 3.141592653589793) * 480.0 / 6.483185307179586)]);
                }
                int y = view.lower_y - (int)(n18 * sqrt3);
                if (y < view.y) {
                    y = view.y;
                }
                if (n16 != -1) {
                    this.xpoints[0] = n16;
                    this.ypoints[0] = view.lower_y + 1;
                    this.xpoints[1] = n16;
                    this.ypoints[1] = n17;
                    this.xpoints[2] = n19;
                    this.ypoints[2] = y;
                    this.xpoints[3] = n19;
                    this.ypoints[3] = view.lower_y + 1;
                    graphics.fillPolygon(this.xpoints, this.ypoints, 4);
                }
                n16 = n19;
                n17 = y;
            }
        }
        else {
            final int mid_y = view.mid_y;
            final double n21 = view.ymult * view.scale;
            if (array2 != null) {
                graphics.setColor(Color.blue);
                for (int k = 0; k != n; ++k) {
                    final int n22 = this.winSize.width * k / (n - 1);
                    final int n23 = mid_y - (int)(n21 * array2[k + n2]);
                    if (n16 != -1) {
                        graphics.drawLine(n16, n17, n22, n23);
                    }
                    n16 = n22;
                    n17 = n23;
                }
            }
            graphics.setColor(Color.white);
            int n24 = -1;
            for (int l = 0; l != n; ++l) {
                final int n25 = this.winSize.width * l / (n - 1);
                final int n26 = mid_y - (int)(n21 * array[l + n2]);
                if (n24 != -1) {
                    graphics.drawLine(n24, n17, n25, n26);
                }
                n24 = n25;
                n17 = n26;
            }
        }
        if (n5 > 0.0 && array2 != null) {
            final double n27 = n14 + n7;
            if (this.uncertaintyCheckItem.getState()) {
                graphics.setColor(Color.blue);
                graphics.drawLine((int)(n27 - sqrt2), view.y, (int)(n27 - sqrt2), view.y + view.height);
                graphics.drawLine((int)(n27 + sqrt2), view.y, (int)(n27 + sqrt2), view.y + view.height);
            }
            if (this.expectCheckItem.getState()) {
                graphics.setColor(Color.red);
                graphics.drawLine((int)n27, view.y, (int)n27, view.y + view.height);
            }
        }
    }
    
    void doOscLeftRight(final Graphics graphics) {
        for (int i = 0; i != this.pSampleCount * 2; ++i) {
            this.pdata[i] = 0.0;
        }
        for (int j = 0; j != this.sampleCount; ++j) {
            final int n = (j <= this.sampleCount / 2) ? ((this.sampleCount / 2 - j) * 2) : ((this.pSampleCount - (j - this.sampleCount / 2)) * 2);
            this.pdata[n] = this.func[j];
            this.pdata[n + 1] = this.funci[j];
        }
        this.fft.transform(this.pdata, false);
        for (int k = 2; k != this.pSampleCount; ++k) {
            this.pdata[k] = 0.0;
        }
        this.fft.transform(this.pdata, true);
        final double n2 = 1.0 / this.pSampleCount;
        for (int l = 0; l != this.sampleCount; ++l) {
            final int n3 = (l <= this.sampleCount / 2) ? ((this.sampleCount / 2 - l) * 2) : ((this.pSampleCount - (l - this.sampleCount / 2)) * 2);
            this.pdatar[l] = this.pdata[n3] * n2;
            this.pdatai[l] = this.pdata[n3 + 1] * n2;
        }
        this.drawFunction(graphics, this.viewLeft, this.pdatar, this.pdatai, this.sampleCount, 0);
        for (int n4 = 0; n4 != this.sampleCount; ++n4) {
            this.pdatar[n4] = this.func[n4] - this.pdatar[n4];
            this.pdatai[n4] = this.funci[n4] - this.pdatai[n4];
        }
        this.drawFunction(graphics, this.viewRight, this.pdatar, this.pdatai, this.sampleCount, 0);
    }
    
    void doBoxLeftRight(final Graphics graphics, double n) {
        if (this.adjustingStates) {
            return;
        }
        if (this.modesLeft == null) {
            final int n2 = ((InfiniteWellSetup)this.setup).getOffset() - 1;
            this.modesLeft = new double[this.stateCount][this.sampleCount];
            for (int i = 0; i != this.stateCount; ++i) {
                final int n3 = i + 1;
                final double n4 = ((this.modes[i][n2] > 0.0) ? 1.0 : -1.0) * this.dispmax[i];
                final double n5 = 3.141592653589793 / (this.sampleCount - n2 * 2 - 1);
                for (int j = n2; j != this.sampleCount - n2; ++j) {
                    this.modesLeft[i][j] = Math.cos((j - n2) * n5 * n3) * n4;
                }
            }
        }
        n *= 0.5;
        for (int k = 0; k != this.sampleCount; ++k) {
            double n6 = 0.0;
            double n7 = 0.0;
            for (int l = 0; l != this.stateCount; ++l) {
                final double n8 = this.magcoef[l] * this.phasecoefcos[l];
                final double n9 = this.magcoef[l] * this.phasecoefsin[l];
                n6 += this.modes[l][k] * n8 - this.modesLeft[l][k] * n9;
                n7 += this.modes[l][k] * n9 + this.modesLeft[l][k] * n8;
            }
            this.pdatar[k] = n6 * n;
            this.pdatai[k] = n7 * n;
        }
        this.drawFunction(graphics, this.viewLeft, this.pdatar, this.pdatai, this.sampleCount, 0);
        for (int n10 = 0; n10 != this.sampleCount; ++n10) {
            this.pdatar[n10] = this.func[n10] - this.pdatar[n10];
            this.pdatai[n10] = this.funci[n10] - this.pdatai[n10];
        }
        this.drawFunction(graphics, this.viewRight, this.pdatar, this.pdatai, this.sampleCount, 0);
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
            default: {
                this.editFunc(x, y);
                break;
            }
        }
    }
    
    void editHandle(final int n) {
        final int n2 = n - this.viewList[this.selectedPaneHandle].y;
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
        this.setGraphLines();
        this.cv.repaint(this.pause);
    }
    
    void editMag(int n, int n2) {
        if (this.selectedCoef == -1) {
            return;
        }
        final int n3 = this.stateSize / 2;
        final int n4 = this.stateSize * (this.selectedCoef % this.stateColSize) + n3;
        final int n5 = this.stateSize * (this.selectedCoef / this.stateColSize) + n3 + this.viewStates.y + 5;
        n -= n4;
        n2 -= n5;
        double n6 = Math.sqrt(n * n + n2 * n2) / n3;
        final double atan2 = Math.atan2(-n2, n);
        final double n7 = -(this.elevels[this.selectedCoef] + 1.0) * this.t % 6.283185307179586;
        if (n6 > 10.0) {
            n6 = 0.0;
        }
        if (n6 > 1.0) {
            n6 = 1.0;
        }
        this.magcoef[this.selectedCoef] = n6;
        this.phasecoefadj[this.selectedCoef] = (atan2 - n7) % 6.283185307179586;
        if (this.phasecoefadj[this.selectedCoef] > 3.141592653589793) {
            final double[] phasecoefadj = this.phasecoefadj;
            final int selectedCoef = this.selectedCoef;
            phasecoefadj[selectedCoef] -= 6.283185307179586;
        }
        if (this.alwaysNormItem.getState()) {
            this.normalize();
        }
        this.cv.repaint(this.pause);
    }
    
    void editFunc(int i, int n) {
        if (this.mouseChooser.getSelectedIndex() == 0) {
            if (this.selection == 2) {
                this.editXState(i, n);
                return;
            }
            if (this.selection == 3) {
                this.editPState(i, n);
                return;
            }
            if (this.selection == 1) {
                this.findStateByEnergy(n);
                this.enterSelectedState();
            }
        }
        else {
            if (this.mouseChooser.getSelectedIndex() == 2) {
                if (this.selection == 2) {
                    this.editXGauss(i, n);
                }
                if (this.selection == 3) {
                    this.editPGauss(i, n);
                }
                if (this.selection == 1) {
                    this.findStateByEnergy(n);
                    this.enterSelectedState();
                }
                return;
            }
            if (this.mouseChooser.getSelectedIndex() == 3) {
                if (this.selection == 2) {
                    this.translateXGauss(i);
                }
                if (this.selection == 3) {
                    this.translatePGauss(i);
                }
                return;
            }
            if (this.selection == 3) {
                return;
            }
            if (this.dragX == i) {
                this.editFuncPoint(i, n);
                this.dragY = n;
            }
            else {
                final int n2 = (i < this.dragX) ? i : this.dragX;
                final int n3 = (i < this.dragX) ? n : this.dragY;
                final int n4 = (i > this.dragX) ? i : this.dragX;
                final int n5 = (i > this.dragX) ? n : this.dragY;
                this.dragX = i;
                this.dragY = n;
                for (i = n2; i <= n4; ++i) {
                    n = n3 + (n5 - n3) * (i - n2) / (n4 - n2);
                    this.editFuncPoint(i, n);
                }
            }
            if (this.adjustingWaveFunc) {
                this.transform();
                if (this.alwaysNormItem.getState()) {
                    this.normalize();
                }
                else {
                    this.maximize();
                }
            }
        }
    }
    
    void editXGauss(final int n, final int n2) {
        final int n3 = n * this.sampleCount / this.winSize.width;
        final double exp = Math.exp(-(n2 - this.viewX.mid_y) * 0.03 - 4.0);
        for (int i = 0; i != this.sampleCount; ++i) {
            final int n4 = i - n3;
            this.func[i] = Math.exp(-n4 * n4 * exp);
            this.funci[i] = 0.0;
        }
        this.transform();
        if (this.alwaysNormItem.getState()) {
            this.normalize();
        }
        else {
            this.maximize();
        }
        this.rescaleGraphs();
    }
    
    void editPGauss(final int n, final int n2) {
        final int n3 = n * this.sampleCount / this.winSize.width;
        final double exp = Math.exp((n2 - this.viewP.mid_y) * 0.03 - 4.0);
        final double pState = this.getPState(n);
        final int n4 = this.sampleCount / 2;
        for (int i = 0; i != this.sampleCount; ++i) {
            final int n5 = i - n4;
            final double exp2 = Math.exp(-n5 * n5 * exp);
            this.func[i] = Math.cos(pState * n5) * exp2;
            this.funci[i] = Math.sin(pState * n5) * exp2;
        }
        this.selectedPState = 0.0;
        this.transform();
        if (this.alwaysNormItem.getState()) {
            this.normalize();
        }
        else {
            this.maximize();
        }
        this.rescaleGraphs();
    }
    
    void translateXGauss(final int dragX) {
        final int n = dragX - this.dragX;
        if (n == 0) {
            return;
        }
        this.dragX = dragX;
        final int n2 = n * this.sampleCount / this.winSize.width;
        if (n2 > 0) {
            for (int i = this.sampleCount - n2 - 1; i >= 0; --i) {
                this.func[i + n2] = this.func[i];
                this.funci[i + n2] = this.funci[i];
            }
        }
        else {
            for (int j = -n2; j != this.sampleCount; ++j) {
                this.func[j + n2] = this.func[j];
                this.funci[j + n2] = this.funci[j];
            }
        }
        this.transform();
        this.cv.repaint(this.pause);
    }
    
    void translatePGauss(final int dragX) {
        final double n = this.getPState(dragX) - this.getPState(this.dragX);
        this.dragX = dragX;
        for (int i = 0; i != this.sampleCount; ++i) {
            final double n2 = Math.cos(n * i) * this.func[i] - Math.sin(n * i) * this.funci[i];
            final double n3 = Math.cos(n * i) * this.funci[i] + Math.sin(n * i) * this.func[i];
            this.func[i] = n2;
            this.funci[i] = n3;
        }
        this.transform();
        this.cv.repaint(this.pause);
    }
    
    void editFuncPoint(final int n, final int n2) {
        final View view = (this.selection == 2) ? this.viewX : this.viewPotential;
        int i = n * this.sampleCount / this.winSize.width;
        int n3 = ((n + 1) * this.sampleCount - 1) / this.winSize.width;
        double n4 = (view.mid_y - n2) / view.ymult;
        double n5 = (view.lower_y - n2) / view.ymult2;
        if (n4 > 1.0) {
            n4 = 1.0;
        }
        if (n4 < -1.0) {
            n4 = -1.0;
        }
        if (n5 > 1.0) {
            n5 = 1.0;
        }
        if (n5 < 0.0) {
            n5 = 0.0;
        }
        final double n6 = n4 / view.scale;
        final double n7 = n5 / view.scale;
        if (i < 1) {
            i = 1;
        }
        if (n3 >= this.sampleCount - 1) {
            n3 = this.sampleCount - 2;
        }
        while (i <= n3) {
            if (this.selection == 1) {
                this.pot[i] = n6;
                this.setupModified = true;
                final boolean b = true;
                this.statesChanged = b;
                this.adjustingStates = b;
            }
            else {
                if (this.probCheckItem.getState() || this.probPhaseCheckItem.getState()) {
                    final double sqrt = Math.sqrt(n7);
                    double sqrt2 = Math.sqrt(this.func[i] * this.func[i] + this.funci[i] * this.funci[i]);
                    if (sqrt2 == 0.0) {
                        this.func[i] = 1.0;
                        sqrt2 = 1.0;
                    }
                    this.func[i] = sqrt * this.func[i] / sqrt2;
                    this.funci[i] = sqrt * this.funci[i] / sqrt2;
                }
                else if (this.magPhaseCheckItem.getState()) {
                    double sqrt3 = Math.sqrt(this.func[i] * this.func[i] + this.funci[i] * this.funci[i]);
                    if (sqrt3 == 0.0) {
                        this.func[i] = 1.0;
                        sqrt3 = 1.0;
                    }
                    this.func[i] = n7 * this.func[i] / sqrt3;
                    this.funci[i] = n7 * this.funci[i] / sqrt3;
                }
                else {
                    this.func[i] = n6;
                }
                this.adjustingWaveFunc = true;
            }
            ++i;
        }
        this.cv.repaint(this.pause);
    }
    
    void editXState(final int n, final int n2) {
        final int n3 = n * this.sampleCount / this.winSize.width;
        if (n3 < 1 || n3 >= this.sampleCount) {
            return;
        }
        for (int i = 0; i != this.sampleCount; ++i) {
            this.func[i] = (this.funci[i] = 0.0);
        }
        this.func[n3] = 1.0;
        this.transform();
        this.rescaleGraphs();
        if (!this.alwaysNormItem.getState()) {
            this.maximize();
        }
        this.cv.repaint(this.pause);
    }
    
    double getPState(final int n) {
        return (n * this.pSampleCount / 2 / this.winSize.width - this.pSampleCount / 4) * 3.141592653589793 / (this.pSampleCount / 2);
    }
    
    void editPState(final int n, final int n2) {
        final double pState = this.getPState(n);
        final int n3 = this.sampleCount / 2;
        for (int i = 0; i != this.sampleCount; ++i) {
            this.func[i] = Math.cos(pState * (i - n3));
            this.funci[i] = Math.sin(pState * (i - n3));
        }
        this.transform();
        this.rescaleGraphs();
        if (!this.alwaysNormItem.getState()) {
            this.maximize();
        }
        this.selectedPState = 0.0;
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
        if (actionEvent.getSource() == this.exitItem) {
            this.applet.destroyFrame();
            return;
        }
        this.cv.repaint();
        if (actionEvent.getSource() == this.measureEItem) {
            this.measureE();
        }
        if (actionEvent.getSource() == this.measureXItem) {
            this.measureX();
        }
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
        if (actionEvent.getSource() == this.rescaleButton) {
            this.rescaleGraphs();
        }
    }
    
    public void scrollbarValueChanged(final DecentScrollbar decentScrollbar) {
        System.out.print(decentScrollbar.getValue() + "\n");
        if (decentScrollbar == this.massBar) {
            final boolean b = true;
            this.adjustingStates = b;
            this.statesChanged = b;
            this.cv.repaint(this.pause);
        }
        if (decentScrollbar == this.aux1Bar || decentScrollbar == this.aux2Bar || decentScrollbar == this.aux3Bar) {
            this.adjustingStates = true;
            this.setup.drawPotential();
            this.statesChanged = true;
            this.cv.repaint(this.pause);
        }
        if (decentScrollbar == this.resBar) {
            this.adjustingStates = true;
        }
    }
    
    public void scrollbarFinished(final DecentScrollbar decentScrollbar) {
        if (decentScrollbar == this.resBar) {
            this.adjustingStates = false;
            this.setResolution();
            this.reinit();
            this.cv.repaint(this.pause);
        }
        if (decentScrollbar == this.massBar || decentScrollbar == this.aux1Bar || decentScrollbar == this.aux2Bar || decentScrollbar == this.aux3Bar) {
            this.adjustingStates = false;
            this.statesChanged = true;
            this.cv.repaint(this.pause);
        }
    }
    
    public boolean handleEvent(final Event event) {
        if (event.id == 201) {
            this.applet.destroyFrame();
            return true;
        }
        return super.handleEvent(event);
    }
    
    void setResolution() {
        this.sampleCount = this.resBar.getValue();
        ++this.sampleCount;
        this.func = new double[this.sampleCount];
        this.funci = new double[this.sampleCount];
        this.pot = new double[this.sampleCount];
        this.statesChanged = true;
        final int n = 8 * this.sampleCount;
        this.pSampleCount = 1;
        while (this.pSampleCount < n) {
            this.pSampleCount *= 2;
        }
        this.pdata = new double[this.pSampleCount * 2];
        this.pdatar = new double[this.pSampleCount];
        this.pdatai = new double[this.pSampleCount];
        this.parityData = new double[100];
        this.currentData = new double[this.sampleCount];
        this.fft = new FFT(this.pSampleCount);
    }
    
    void genStates(final boolean b) {
        System.out.println("genstates");
        this.statesChanged = false;
        final int sampleCount = this.sampleCount;
        final double[] array = new double[sampleCount];
        final double[] array2 = new double[sampleCount];
        final double[] array3 = new double[sampleCount * sampleCount];
        this.mass = this.massBar.getValue() * 0.02;
        final double n = 1.0 / (this.mass * 1.006);
        this.mass *= 1596875.0;
        double n2 = -20.0;
        final double n3 = this.sampleCount / 128.0;
        final double n4 = n * (n3 * n3);
        for (int i = 0; i != sampleCount; ++i) {
            if (i < sampleCount - 1) {
                array2[i] = -n4;
            }
            array[i] = 2.0 * n4 + this.pot[i];
            if (this.pot[i] > n2) {
                n2 = this.pot[i];
            }
            final int n5 = i * sampleCount;
            for (int j = 0; j != sampleCount; ++j) {
                array3[n5 + j] = 0.0;
            }
            array3[n5 + i] = 1.0;
        }
        final double[] array4 = new double[1 + 4 * sampleCount + sampleCount * sampleCount];
        final int[] array5 = new int[3 + 5 * sampleCount];
        Dstedc.dstedc(b ? "I" : "N", sampleCount, array, 0, array2, 0, array3, 0, sampleCount, array4, 0, array4.length, array5, 0, array5.length, new intW(0));
        this.elevels = new double[sampleCount];
        this.elevelCount = sampleCount;
        for (int k = 0; k != sampleCount; ++k) {
            this.elevels[k] = array[k];
        }
        for (int l = 1; l < sampleCount; ++l) {
            final double n6 = this.elevels[l];
            int n7 = l;
            while (this.elevels[n7 - 1] > n6) {
                this.elevels[n7] = this.elevels[n7 - 1];
                if (--n7 <= 0) {
                    break;
                }
            }
            this.elevels[n7] = n6;
        }
        while (n2 > 0.0 && this.elevels[this.elevelCount - 1] > n2) {
            --this.elevelCount;
        }
        this.stateCount = this.elevelCount;
        final int stateCount = this.sampleCount * 3 / 8;
        if (this.stateCount > stateCount && b) {
            this.stateCount = stateCount;
        }
        if (!b) {
            return;
        }
        this.modes = new double[this.stateCount][this.sampleCount];
        for (int n8 = 0; n8 != this.stateCount; ++n8) {
            int n9;
            for (n9 = 0; n9 != sampleCount && this.elevels[n8] != array[n9]; ++n9) {}
            if (n9 == sampleCount) {
                System.out.print("can't find elevels! " + n8 + " " + this.elevels[n8] + "\n");
            }
            else {
                array[n9] = -1.0;
                this.dispmax[n8] = 0.0;
                for (int n10 = 0; n10 != sampleCount; ++n10) {
                    this.modes[n8][n10] = array3[n9 * sampleCount + n10];
                    if (this.modes[n8][n10] > this.dispmax[n8]) {
                        this.dispmax[n8] = this.modes[n8][n10];
                    }
                    else if (-this.modes[n8][n10] > this.dispmax[n8]) {
                        this.dispmax[n8] = -this.modes[n8][n10];
                    }
                }
            }
        }
        this.modesLeft = null;
        if (!this.setupModified) {
            this.setup.fudgeLevels();
        }
        System.out.println("done");
    }
    
    public void mouseDragged(final MouseEvent mouseEvent) {
        this.dragging = true;
        this.edit(mouseEvent);
    }
    
    public void mouseMoved(final MouseEvent mouseEvent) {
        final int x = mouseEvent.getX();
        final int y = mouseEvent.getY();
        this.dragX = x;
        this.dragY = y;
        final int selectedCoef = this.selectedCoef;
        final int selection = this.selection;
        this.selectedCoef = -1;
        this.selectedPState = 0.0;
        this.selectedPaneHandle = -1;
        this.selection = 0;
        for (int i = 1; i != this.viewCount; ++i) {
            final int n = y - this.viewList[i].y;
            if (n >= -3 && n <= 3) {
                this.selectedPaneHandle = i;
                this.selection = 5;
            }
        }
        Cursor predefinedCursor = null;
        if (this.selection == 5) {
            predefinedCursor = Cursor.getPredefinedCursor(8);
        }
        else if (this.viewX != null && this.viewX.contains(x, y)) {
            this.selection = 2;
        }
        else if (this.viewP != null && this.viewP.contains(x, y)) {
            this.selection = 3;
            this.selectedPState = this.getPState(x);
            this.cv.repaint(this.pause);
        }
        else if (this.viewPotential != null && this.viewPotential.contains(x, y)) {
            this.selection = 1;
            if (this.mouseChooser.getSelectedIndex() != 1) {
                this.findStateByEnergy(y);
            }
        }
        else if (this.viewStates != null && this.viewStates.contains(x, y)) {
            this.selectedCoef = x / this.stateSize + (y - (this.viewStates.y + 5)) / this.stateSize * this.stateColSize;
            if (this.selectedCoef >= this.stateCount) {
                this.selectedCoef = -1;
            }
            if (this.selectedCoef != -1) {
                this.selection = 4;
            }
        }
        this.cv.setCursor(predefinedCursor);
        if (this.selection != selection || this.selectedCoef != selectedCoef) {
            this.cv.repaint(this.pause);
        }
    }
    
    void findStateByEnergy(final int n) {
        if (this.adjustingStates || this.statesChanged) {
            return;
        }
        final double n2 = (this.viewPotential.mid_y - n) / this.viewPotential.ymult;
        double n3 = 100.0;
        for (int i = 0; i != this.stateCount; ++i) {
            final double abs = Math.abs(this.elevels[i] - n2);
            if (abs < n3) {
                n3 = abs;
                this.selectedCoef = i;
            }
        }
    }
    
    public void mouseClicked(final MouseEvent mouseEvent) {
        if (mouseEvent.getClickCount() == 2 && this.selectedCoef != -1) {
            this.enterSelectedState();
        }
    }
    
    void enterSelectedState() {
        for (int i = 0; i != this.stateCount; ++i) {
            if (this.selectedCoef != i) {
                this.magcoef[i] = 0.0;
            }
        }
        this.magcoef[this.selectedCoef] = 1.0;
        this.cv.repaint(this.pause);
        this.rescaleGraphs();
    }
    
    public void mouseEntered(final MouseEvent mouseEvent) {
    }
    
    public void mouseExited(final MouseEvent mouseEvent) {
        if (!this.dragging) {
            if (this.selectedCoef != -1) {
                this.selectedCoef = -1;
                this.cv.repaint(this.pause);
            }
            if (this.selectedPState != 0.0) {
                this.selectedPState = 0.0;
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
        this.dragging = true;
        this.edit(mouseEvent);
    }
    
    public void mouseReleased(final MouseEvent mouseEvent) {
        if ((mouseEvent.getModifiers() & 0x10) == 0x0) {
            return;
        }
        if (this.mouseChooser.getSelectedIndex() == 1 && this.selection == 1) {
            this.adjustingStates = false;
            this.statesChanged = true;
        }
        if (this.selection == 4 && this.alwaysMaxItem.getState()) {
            this.maximize();
        }
        final boolean b = false;
        this.adjustingWaveFunc = b;
        this.dragging = b;
        this.cv.repaint(this.pause);
    }
    
    public void itemStateChanged(final ItemEvent itemEvent) {
        if (itemEvent.getItemSelectable() == this.stoppedCheck) {
            this.cv.repaint(this.pause);
            return;
        }
        if (itemEvent.getItemSelectable() == this.setupChooser) {
            this.doSetup();
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
                this.rescaleGraphs();
            }
        }
    }
    
    void doSetup() {
        this.doBlank();
        for (int i = 0; i != this.sampleCount; ++i) {
            final double[] func = this.func;
            final int n = i;
            final double[] funci = this.funci;
            final int n2 = i;
            final double[] pot = this.pot;
            final int n3 = i;
            final double n4 = 0.0;
            pot[n3] = n4;
            func[n] = (funci[n2] = n4);
        }
        this.setup = this.setupList.elementAt(this.setupChooser.getSelectedIndex());
        this.aux1Bar.setValue(100);
        this.aux2Bar.setValue(100);
        this.aux3Bar.setValue(100);
        this.selectGround = true;
        this.setup.select();
        this.setup.drawPotential();
        this.setupModified = false;
        this.statesChanged = true;
        if (this.setup.getAuxBarCount() >= 2) {
            this.aux2Label.show();
            this.aux2Bar.show();
        }
        else {
            this.aux2Label.hide();
            this.aux2Bar.hide();
        }
        if (this.setup.getAuxBarCount() == 3) {
            this.aux3Label.show();
            this.aux3Bar.show();
        }
        else {
            this.aux3Label.hide();
            this.aux3Bar.hide();
        }
        if (this.setup.allowLeftRight()) {
            this.leftRightCheckItem.enable();
        }
        else {
            this.leftRightCheckItem.disable();
            this.leftRightCheckItem.setState(false);
        }
        this.handleResize();
        this.validate();
        this.selectedCoef = -1;
    }
    
    void measureE() {
        this.normalize();
        double nextDouble = this.random.nextDouble();
        int i;
        for (i = 0; i != this.stateCount; ++i) {
            nextDouble -= this.magcoef[i] * this.magcoef[i];
            if (nextDouble < 0.0) {
                break;
            }
        }
        if (i == this.stateCount) {
            return;
        }
        final int n = i;
        for (int j = 0; j != this.stateCount; ++j) {
            this.magcoef[j] = 0.0;
        }
        this.magcoef[n] = 1.0;
        this.rescaleGraphs();
    }
    
    void measureX() {
        double nextDouble = this.random.nextDouble();
        int i;
        for (i = 0; i != this.sampleCount; ++i) {
            nextDouble -= this.func[i] * this.func[i] + this.funci[i] * this.funci[i];
            if (nextDouble < 0.0) {
                break;
            }
        }
        if (i == this.sampleCount) {
            return;
        }
        final int n = i;
        for (int j = 0; j != this.sampleCount; ++j) {
            this.func[j] = (this.funci[j] = 0.0);
        }
        this.func[n] = 1.0;
        this.transform();
        this.rescaleGraphs();
        this.normalize();
    }
    
    class FFT
    {
        double[] wtabf;
        double[] wtabi;
        int size;
        
        FFT(final int size) {
            this.size = size;
            if ((this.size & this.size - 1) != 0x0) {
                System.out.println("size must be power of two!");
            }
            this.calcWTable();
        }
        
        void calcWTable() {
            this.wtabf = new double[this.size];
            this.wtabi = new double[this.size];
            for (int i = 0; i != this.size; i += 2) {
                final double n = 3.1415926535 * i / this.size;
                this.wtabf[i] = Math.cos(n);
                this.wtabf[i + 1] = Math.sin(n);
                this.wtabi[i] = this.wtabf[i];
                this.wtabi[i + 1] = -this.wtabf[i + 1];
            }
        }
        
        void transform(final double[] array, final boolean b) {
            int n = 0;
            final int n2 = this.size * 2;
            if ((this.size & this.size - 1) != 0x0) {
                System.out.println("size must be power of two!");
            }
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
            final int n5 = this.size << 1;
            final double[] array2 = b ? this.wtabi : this.wtabf;
            for (int j = 0; j != n2; j += 4) {
                final double n6 = array[j];
                final double n7 = array[j + 1];
                final double n8 = array[j + 2];
                final double n9 = array[j + 3];
                array[j] = n6 + n8;
                array[j + 1] = n7 + n9;
                array[j + 2] = n6 - n8;
                array[j + 3] = n7 - n9;
            }
            final int n10 = n5 >> 1;
            final int n11 = b ? -1 : 1;
            for (int k = 0; k != n2; k += 8) {
                final double n12 = array[k];
                final double n13 = array[k + 1];
                final double n14 = array[k + 4];
                final double n15 = array[k + 5];
                array[k] = n12 + n14;
                array[k + 1] = n13 + n15;
                array[k + 4] = n12 - n14;
                array[k + 5] = n13 - n15;
                final double n16 = array[k + 2];
                final double n17 = array[k + 3];
                final double n18 = array[k + 6] * n11;
                final double n19 = array[k + 7] * n11;
                array[k + 2] = n16 - n19;
                array[k + 3] = n17 + n18;
                array[k + 6] = n16 + n19;
                array[k + 7] = n17 - n18;
            }
            int n20 = n10 >> 1;
            for (int l = 16; l <= n2; l <<= 1) {
                final int n21 = l >> 1;
                n20 >>= 1;
                for (int n22 = 0; n22 != 1000; ++n22) {}
                for (int n23 = 0; n23 < n2; n23 += l) {
                    for (int n24 = 0, n25 = n23; n25 != n23 + n21; n25 += 2, n24 += n20) {
                        final double n26 = array2[n24];
                        final double n27 = array2[n24 + 1];
                        final double n28 = array[n25];
                        final double n29 = array[n25 + 1];
                        final int n30 = n25 + n21;
                        final double n31 = array[n30];
                        final double n32 = array[n30 + 1];
                        final double n33 = n31 * n26 - n32 * n27;
                        final double n34 = n31 * n27 + n32 * n26;
                        array[n25] = n28 + n33;
                        array[n25 + 1] = n29 + n34;
                        array[n30] = n28 - n33;
                        array[n30 + 1] = n29 - n34;
                    }
                }
            }
        }
    }
    
    abstract class Setup
    {
        abstract String getName();
        
        abstract void select();
        
        abstract Setup createNext();
        
        void fudgeLevels() {
        }
        
        boolean allowLeftRight() {
            return false;
        }
        
        abstract void drawPotential();
        
        int getAuxBarCount() {
            return 2;
        }
        
        void getInfo(final String[] array, final int n) {
        }
        
        double getBaseEnergy() {
            return -1.0;
        }
    }
    
    class InfiniteWellSetup extends Setup
    {
        String getName() {
            return "Infinite Well";
        }
        
        void select() {
            QuantumStatesFrame.this.aux1Label.setText("Well Width");
        }
        
        double getBaseEnergy() {
            return 1.0;
        }
        
        boolean allowLeftRight() {
            return !QuantumStatesFrame.this.setupModified;
        }
        
        void drawPotential() {
            final int offset = this.getOffset();
            for (int i = 0; i != QuantumStatesFrame.this.sampleCount; ++i) {
                QuantumStatesFrame.this.pot[i] = 1000.0;
            }
            for (int j = offset; j <= QuantumStatesFrame.this.sampleCount - 1 - offset; ++j) {
                QuantumStatesFrame.this.pot[j] = -1.0;
            }
        }
        
        int getOffset() {
            return (100 - QuantumStatesFrame.this.aux1Bar.getValue()) * (QuantumStatesFrame.this.sampleCount / 2) / 110 + 1;
        }
        
        int getAuxBarCount() {
            return 1;
        }
        
        void fudgeLevels() {
            for (int i = 1; i != QuantumStatesFrame.this.stateCount; ++i) {
                QuantumStatesFrame.this.elevels[i] = (QuantumStatesFrame.this.elevels[0] + 1.0) * (i + 1) * (i + 1) - 1.0;
            }
        }
        
        void getInfo(final String[] array, final int n) {
            array[n] = "Well width = " + QuantumStatesFrame.this.getLengthText(QuantumStatesFrame.this.sampleCount - this.getOffset() * 2);
        }
        
        Setup createNext() {
            return new FiniteWellSetup();
        }
    }
    
    class FiniteWellSetup extends Setup
    {
        String getName() {
            return "Finite Well";
        }
        
        void select() {
            QuantumStatesFrame.this.aux1Label.setText("Well Width");
            QuantumStatesFrame.this.aux1Bar.setValue(100);
            QuantumStatesFrame.this.aux2Label.setText("Well Depth");
        }
        
        int getOffset() {
            return (100 - QuantumStatesFrame.this.aux1Bar.getValue() * 6 / 10) * (QuantumStatesFrame.this.sampleCount / 2) / 110;
        }
        
        double getFloor() {
            return 1.0 - QuantumStatesFrame.this.aux2Bar.getValue() / 50.0;
        }
        
        void drawPotential() {
            final int offset = this.getOffset();
            final double floor = this.getFloor();
            for (int i = 0; i != QuantumStatesFrame.this.sampleCount; ++i) {
                QuantumStatesFrame.this.pot[i] = 1.0;
            }
            for (int j = offset; j <= QuantumStatesFrame.this.sampleCount - 1 - offset; ++j) {
                QuantumStatesFrame.this.pot[j] = floor;
            }
        }
        
        void getInfo(final String[] array, final int n) {
            array[n] = "Well width = " + QuantumStatesFrame.this.getLengthText(QuantumStatesFrame.this.sampleCount - this.getOffset() * 2);
            array[n] = array[n] + ", Well depth = " + QuantumStatesFrame.this.getEnergyText(1.0 - this.getFloor(), false);
        }
        
        Setup createNext() {
            return new HarmonicOscillatorSetup();
        }
    }
    
    class HarmonicOscillatorSetup extends Setup
    {
        double a;
        
        String getName() {
            return "Harmonic Oscillator";
        }
        
        boolean allowLeftRight() {
            return !QuantumStatesFrame.this.setupModified;
        }
        
        void select() {
            QuantumStatesFrame.this.aux1Label.setText("Spring Constant");
            QuantumStatesFrame.this.aux1Bar.setValue(0);
            QuantumStatesFrame.this.aux2Label.setText("Offset");
            QuantumStatesFrame.this.aux2Bar.setValue(50);
        }
        
        void drawPotential() {
            final double n = QuantumStatesFrame.this.sampleCount / 2 / (0.062333 * QuantumStatesFrame.this.aux1Bar.getValue() + 1.1);
            final int n2 = (50 - QuantumStatesFrame.this.aux2Bar.getValue()) * (QuantumStatesFrame.this.sampleCount / 2) / 100;
            this.a = 2.0 / (n * n);
            for (int i = 0; i != QuantumStatesFrame.this.sampleCount; ++i) {
                final int n3 = n2 + i - QuantumStatesFrame.this.sampleCount / 2;
                QuantumStatesFrame.this.pot[i] = this.a * n3 * n3 - 1.0;
            }
            QuantumStatesFrame.this.pot[0] = (QuantumStatesFrame.this.pot[QuantumStatesFrame.this.sampleCount - 1] = 1000.0);
        }
        
        double getBaseEnergy() {
            return 1.0;
        }
        
        void getInfo(final String[] array, final int n) {
            final double n2 = 1.0 / QuantumStatesFrame.this.pointsToLength(1.0);
            final double n3 = this.a * 2.0 * QuantumStatesFrame.this.convertEnergy(1.0, false) * n2 * n2;
            array[n] = "k = " + QuantumStatesFrame.this.getUnitText(n3 * 1.0E-18, "eV") + "/nm^2";
            array[n] = array[n] + ", period = " + QuantumStatesFrame.this.getUnitText(1.0 / (Math.sqrt(n3 / (QuantumStatesFrame.this.mass / 8.988E16)) / 6.283185307179586), "s");
        }
        
        void fudgeLevels() {
            if (QuantumStatesFrame.this.stateCount < 10) {
                return;
            }
            double n = 0.0;
            for (int i = 0; i != 10; ++i) {
                n += QuantumStatesFrame.this.elevels[i + 1] - QuantumStatesFrame.this.elevels[i];
            }
            final double n2 = n / 10.0;
            for (int n3 = 1; n3 != QuantumStatesFrame.this.stateCount && (QuantumStatesFrame.this.elevels[n3] - QuantumStatesFrame.this.elevels[n3 - 1]) / n2 <= 2.0; ++n3) {
                QuantumStatesFrame.this.elevels[n3] = QuantumStatesFrame.this.elevels[n3 - 1] + n2;
            }
        }
        
        Setup createNext() {
            return new FiniteWellPairSetup();
        }
    }
    
    class FiniteWellPairSetup extends Setup
    {
        String getName() {
            return "Well Pair";
        }
        
        void select() {
            QuantumStatesFrame.this.aux1Label.setText("Well Width");
            QuantumStatesFrame.this.aux2Bar.setValue(10);
            QuantumStatesFrame.this.aux2Label.setText("Well Separation");
            QuantumStatesFrame.this.aux3Label.setText("Well Depth");
        }
        
        int getAuxBarCount() {
            return 3;
        }
        
        double getFloor() {
            return 1.0 - QuantumStatesFrame.this.aux3Bar.getValue() / 50.0;
        }
        
        int getWidth() {
            return QuantumStatesFrame.this.aux1Bar.getValue() * 3 / 4 * (QuantumStatesFrame.this.sampleCount / 2) / 110 + 1;
        }
        
        int getSep() {
            return (QuantumStatesFrame.this.sampleCount / 2 - this.getWidth()) * QuantumStatesFrame.this.aux2Bar.getValue() / 150 + 1;
        }
        
        void drawPotential() {
            final int width = this.getWidth();
            final int sep = this.getSep();
            final double floor = this.getFloor();
            for (int i = 0; i != QuantumStatesFrame.this.sampleCount; ++i) {
                QuantumStatesFrame.this.pot[i] = 1.0;
            }
            for (int j = 0; j != width; ++j) {
                QuantumStatesFrame.this.pot[QuantumStatesFrame.this.sampleCount / 2 - j - sep] = (QuantumStatesFrame.this.pot[QuantumStatesFrame.this.sampleCount / 2 + j + sep] = floor);
            }
        }
        
        void getInfo(final String[] array, final int n) {
            array[n] = "Well width = " + QuantumStatesFrame.this.getLengthText(this.getWidth());
            array[n] = array[n] + ", Well depth = " + QuantumStatesFrame.this.getEnergyText(1.0 - this.getFloor(), false);
            array[n + 1] = "Well separation = " + QuantumStatesFrame.this.getLengthText(this.getSep() * 2);
        }
        
        Setup createNext() {
            return new FiniteWellPairCoupledSetup();
        }
    }
    
    class FiniteWellPairCoupledSetup extends Setup
    {
        String getName() {
            return "Coupled Well Pair";
        }
        
        void select() {
            QuantumStatesFrame.this.aux1Label.setText("Well Separation");
            QuantumStatesFrame.this.aux1Bar.setValue(1);
            QuantumStatesFrame.this.aux2Label.setText("Wall Potential");
            QuantumStatesFrame.this.aux2Bar.setValue(50);
        }
        
        int getWidth() {
            return QuantumStatesFrame.this.sampleCount / 4;
        }
        
        int getSep() {
            return QuantumStatesFrame.this.aux1Bar.getValue() * (this.getWidth() - 1) / 150 + 1;
        }
        
        double getWallEnergy() {
            return -1.0 + (QuantumStatesFrame.this.aux2Bar.getValue() - 1) / 50.0;
        }
        
        void drawPotential() {
            final int width = this.getWidth();
            final int sep = this.getSep();
            final double n = -1.0;
            final double n2 = -1.0 + (QuantumStatesFrame.this.aux2Bar.getValue() - 1) / 50.0;
            for (int i = 0; i != QuantumStatesFrame.this.sampleCount; ++i) {
                QuantumStatesFrame.this.pot[i] = 1.0;
            }
            for (int j = 0; j != width; ++j) {
                QuantumStatesFrame.this.pot[QuantumStatesFrame.this.sampleCount / 2 - j - sep] = (QuantumStatesFrame.this.pot[QuantumStatesFrame.this.sampleCount / 2 + j + sep] = n);
            }
            for (int k = 0; k < sep; ++k) {
                QuantumStatesFrame.this.pot[QuantumStatesFrame.this.sampleCount / 2 - k] = (QuantumStatesFrame.this.pot[QuantumStatesFrame.this.sampleCount / 2 + k] = n2);
            }
        }
        
        void getInfo(final String[] array, final int n) {
            array[n] = "Well width = " + QuantumStatesFrame.this.getLengthText(this.getWidth());
            array[n] = array[n] + ", Well depth = " + QuantumStatesFrame.this.getEnergyText(2.0, false);
            array[n + 1] = "Well separation = " + QuantumStatesFrame.this.getLengthText(this.getSep() * 2);
            final StringBuffer sb = new StringBuffer();
            final int n2 = n + 1;
            array[n2] = sb.append(array[n2]).append(", Wall potential = ").append(QuantumStatesFrame.this.getEnergyText(this.getWallEnergy(), true)).toString();
        }
        
        Setup createNext() {
            return new AsymmetricWellSetup();
        }
    }
    
    class AsymmetricWellSetup extends Setup
    {
        int w1;
        double floor2;
        
        String getName() {
            return "Asymmetric Well";
        }
        
        void select() {
            QuantumStatesFrame.this.aux1Label.setText("Width Difference");
            QuantumStatesFrame.this.aux1Bar.setValue(60);
            QuantumStatesFrame.this.aux2Label.setText("Depth Difference");
            QuantumStatesFrame.this.aux2Bar.setValue(12);
        }
        
        void getInfo(final String[] array, final int n) {
            array[n] = "Left well width = " + QuantumStatesFrame.this.getLengthText(this.w1);
            array[n] = array[n] + ", energy = " + QuantumStatesFrame.this.getEnergyText(-1.0, true);
            array[n + 1] = "Right well width = " + QuantumStatesFrame.this.getLengthText(QuantumStatesFrame.this.sampleCount - 2 - this.w1);
            final StringBuffer sb = new StringBuffer();
            final int n2 = n + 1;
            array[n2] = sb.append(array[n2]).append(", energy = ").append(QuantumStatesFrame.this.getEnergyText(this.floor2, true)).toString();
        }
        
        void drawPotential() {
            QuantumStatesFrame.this.pot[0] = (QuantumStatesFrame.this.pot[QuantumStatesFrame.this.sampleCount - 1] = 1000.0);
            this.w1 = QuantumStatesFrame.this.aux1Bar.getValue() * (QuantumStatesFrame.this.sampleCount - 2) / 100;
            this.floor2 = QuantumStatesFrame.this.aux2Bar.getValue() * 2 / 100.0 - 1.0;
            for (int i = 1; i != this.w1; ++i) {
                QuantumStatesFrame.this.pot[i] = -1.0;
            }
            for (int j = this.w1; j < QuantumStatesFrame.this.sampleCount - 1; ++j) {
                QuantumStatesFrame.this.pot[j] = this.floor2;
            }
        }
        
        double getBaseEnergy() {
            return 1.0;
        }
        
        Setup createNext() {
            return new InfiniteWellFieldSetup();
        }
    }
    
    class InfiniteWellFieldSetup extends Setup
    {
        int width;
        double field;
        
        String getName() {
            return "Infinite Well + Field";
        }
        
        void select() {
            QuantumStatesFrame.this.aux1Label.setText("Well Width");
            QuantumStatesFrame.this.aux2Label.setText("Field Strength");
        }
        
        void getInfo(final String[] array, final int n) {
            final int n2 = QuantumStatesFrame.this.sampleCount - this.width * 2;
            array[n] = "Well width = " + QuantumStatesFrame.this.getLengthText(n2);
            QuantumStatesFrame.this.pointsToLength(n2);
            array[n] = array[n] + ", voltage difference = " + QuantumStatesFrame.this.getUnitText(QuantumStatesFrame.this.convertEnergy(QuantumStatesFrame.this.pot[this.width] - QuantumStatesFrame.this.pot[QuantumStatesFrame.this.sampleCount - 1 - this.width], false), "V");
            array[n + 1] = "(Particle charge = electron charge)";
        }
        
        void drawPotential() {
            this.width = (100 - QuantumStatesFrame.this.aux1Bar.getValue()) * (QuantumStatesFrame.this.sampleCount / 2) / 110 + 1;
            for (int i = 0; i != QuantumStatesFrame.this.sampleCount; ++i) {
                QuantumStatesFrame.this.pot[i] = 1000.0;
            }
            this.field = -(QuantumStatesFrame.this.aux2Bar.getValue() - 50) / (50.0 * QuantumStatesFrame.this.sampleCount / 2.0);
            for (int j = this.width; j <= QuantumStatesFrame.this.sampleCount - 1 - this.width; ++j) {
                QuantumStatesFrame.this.pot[j] = (j - QuantumStatesFrame.this.sampleCount / 2) * this.field;
            }
        }
        
        double getBaseEnergy() {
            return 0.0;
        }
        
        Setup createNext() {
            return new WellPairCoupledFieldSetup();
        }
    }
    
    class WellPairCoupledFieldSetup extends Setup
    {
        String getName() {
            return "Coupled Wells + Field";
        }
        
        void select() {
            QuantumStatesFrame.this.aux1Label.setText("Well Separation");
            QuantumStatesFrame.this.aux1Bar.setValue(1);
            QuantumStatesFrame.this.aux2Label.setText("Field Strength");
        }
        
        double getBaseEnergy() {
            return 0.25;
        }
        
        void drawPotential() {
            final int n = QuantumStatesFrame.this.sampleCount / 8;
            final int n2 = QuantumStatesFrame.this.aux1Bar.getValue() * (n - 1) / 101 + 1;
            final double n3 = -1.0 + this.getBaseEnergy();
            final double n4 = 1.0;
            for (int i = 0; i != QuantumStatesFrame.this.sampleCount; ++i) {
                QuantumStatesFrame.this.pot[i] = 1000.0;
            }
            final double n5 = -(QuantumStatesFrame.this.aux2Bar.getValue() - 50) / (100.0 * QuantumStatesFrame.this.sampleCount / 2.0);
            for (int j = 0; j != n; ++j) {
                final double n6 = (j + n2) * n5;
                QuantumStatesFrame.this.pot[QuantumStatesFrame.this.sampleCount / 2 - j - n2] = n3 + n6;
                QuantumStatesFrame.this.pot[QuantumStatesFrame.this.sampleCount / 2 + j + n2] = n3 - n6;
            }
            for (int k = 0; k < n2; ++k) {
                QuantumStatesFrame.this.pot[QuantumStatesFrame.this.sampleCount / 2 - k] = (QuantumStatesFrame.this.pot[QuantumStatesFrame.this.sampleCount / 2 + k] = n4);
            }
        }
        
        Setup createNext() {
            return new CoulombSetup();
        }
    }
    
    class CoulombSetup extends Setup
    {
        String getName() {
            return "Coulomb";
        }
        
        void select() {
            QuantumStatesFrame.this.aux1Label.setText("Charge");
            QuantumStatesFrame.this.aux1Bar.setValue(8);
        }
        
        void drawPotential() {
            final double n = QuantumStatesFrame.this.aux1Bar.getValue() * (QuantumStatesFrame.this.sampleCount / 2) / 110.0;
            for (int i = 0; i != QuantumStatesFrame.this.sampleCount; ++i) {
                int n2 = i - QuantumStatesFrame.this.sampleCount / 2;
                if (n2 < 0) {
                    n2 = -n2;
                }
                double n3 = 1.0 - n / n2;
                if (n3 < -1.0) {
                    n3 = -1.0;
                }
                QuantumStatesFrame.this.pot[i] = n3;
            }
            QuantumStatesFrame.this.pot[0] = (QuantumStatesFrame.this.pot[QuantumStatesFrame.this.sampleCount - 1] = 1000.0);
        }
        
        int getAuxBarCount() {
            return 1;
        }
        
        Setup createNext() {
            return new QuarticOscillatorSetup();
        }
    }
    
    class QuarticOscillatorSetup extends Setup
    {
        double a;
        
        String getName() {
            return "Quartic Oscillator";
        }
        
        void select() {
            QuantumStatesFrame.this.aux1Label.setText("Spring Constant");
            QuantumStatesFrame.this.aux1Bar.setValue(0);
            QuantumStatesFrame.this.aux2Label.setText("Offset");
            QuantumStatesFrame.this.aux2Bar.setValue(50);
        }
        
        void drawPotential() {
            final double n = QuantumStatesFrame.this.sampleCount / 2 / (0.062333 * QuantumStatesFrame.this.aux1Bar.getValue() + 1.1);
            final int n2 = (50 - QuantumStatesFrame.this.aux2Bar.getValue()) * (QuantumStatesFrame.this.sampleCount / 2) / 100;
            this.a = 2.0 / (n * n * n * n);
            for (int i = 0; i != QuantumStatesFrame.this.sampleCount; ++i) {
                final int n3 = n2 + i - QuantumStatesFrame.this.sampleCount / 2;
                QuantumStatesFrame.this.pot[i] = this.a * n3 * n3 * n3 * n3 - 1.0;
            }
            QuantumStatesFrame.this.pot[0] = (QuantumStatesFrame.this.pot[QuantumStatesFrame.this.sampleCount - 1] = 1000.0);
        }
        
        double getBaseEnergy() {
            return 1.0;
        }
        
        Setup createNext() {
            return new FiniteWellArraySetup();
        }
    }
    
    class FiniteWellArraySetup extends Setup
    {
        int width;
        int wellCount;
        
        String getName() {
            return "Well Array (Square)";
        }
        
        void select() {
            QuantumStatesFrame.this.aux1Label.setText("Well Count");
            QuantumStatesFrame.this.aux2Label.setText("Well Depth");
            QuantumStatesFrame.this.aux3Label.setText("Well Width");
        }
        
        int getAuxBarCount() {
            return 3;
        }
        
        double getFloor() {
            return 1.0 - QuantumStatesFrame.this.aux2Bar.getValue() / 50.0;
        }
        
        void getInfo(final String[] array, final int n) {
            array[n] = "Well width = " + QuantumStatesFrame.this.getLengthText(this.width);
            array[n] = array[n] + ", Well count = " + this.wellCount;
            array[n + 1] = "Well depth = " + QuantumStatesFrame.this.getEnergyText(1.0 - this.getFloor(), false);
        }
        
        void drawPotential() {
            int n = 10;
            if (QuantumStatesFrame.this.sampleCount > 260) {
                n = QuantumStatesFrame.this.sampleCount / 26;
            }
            int n2 = QuantumStatesFrame.this.sampleCount * 7 / (n * 8) * (QuantumStatesFrame.this.aux3Bar.getValue() + 60) / 160;
            int n3 = QuantumStatesFrame.this.sampleCount / (n * 8);
            if (n3 == 0) {
                ++n3;
                --n2;
            }
            this.wellCount = QuantumStatesFrame.this.aux1Bar.getValue() * n / 101 + 1;
            final double floor = this.getFloor();
            final int n4 = (QuantumStatesFrame.this.sampleCount - this.wellCount * n2) / 2;
            this.width = n2 - n3;
            for (int i = 0; i != QuantumStatesFrame.this.sampleCount; ++i) {
                if (i < n4) {
                    QuantumStatesFrame.this.pot[i] = 1.0;
                }
                else {
                    final int n5 = i - n4;
                    QuantumStatesFrame.this.pot[i] = ((n5 % n2 < n3 || n5 / n2 >= this.wellCount) ? 1.0 : floor);
                }
            }
        }
        
        Setup createNext() {
            return new HarmonicWellArraySetup();
        }
    }
    
    class HarmonicWellArraySetup extends FiniteWellArraySetup
    {
        String getName() {
            return "Well Array (Harmonic)";
        }
        
        void drawPotential() {
            int n = 5;
            if (QuantumStatesFrame.this.sampleCount > 260) {
                n = QuantumStatesFrame.this.sampleCount / 52;
            }
            int n2 = QuantumStatesFrame.this.sampleCount * 7 / (n * 8) * (QuantumStatesFrame.this.aux3Bar.getValue() + 60) / 160;
            int n3 = QuantumStatesFrame.this.sampleCount / (n * 8);
            if (n3 == 0) {
                ++n3;
                --n2;
            }
            this.wellCount = QuantumStatesFrame.this.aux1Bar.getValue() * n / 101 + 1;
            final double floor = this.getFloor();
            final int n4 = (QuantumStatesFrame.this.sampleCount - this.wellCount * n2) / 2;
            this.width = n2 - n3;
            final double n5 = (1.0 - floor) / (this.width / 2 * (this.width / 2.0));
            for (int i = 0; i != QuantumStatesFrame.this.sampleCount; ++i) {
                if (i < n4) {
                    QuantumStatesFrame.this.pot[i] = 1.0;
                }
                else {
                    final int n6 = i - n4;
                    final int n7 = n6 % n2;
                    if (n7 < n3 || n6 / n2 >= this.wellCount) {
                        QuantumStatesFrame.this.pot[i] = 1.0;
                    }
                    else {
                        final double n8 = n7 - n3 - this.width / 2.0;
                        QuantumStatesFrame.this.pot[i] = floor + n8 * n8 * n5;
                        if (QuantumStatesFrame.this.pot[i] > 1.0) {
                            QuantumStatesFrame.this.pot[i] = 1.0;
                        }
                    }
                }
            }
        }
        
        Setup createNext() {
            return new CoulombWellArraySetup();
        }
    }
    
    class CoulombWellArraySetup extends FiniteWellArraySetup
    {
        String getName() {
            return "Well Array (Coulomb)";
        }
        
        void select() {
            super.select();
            QuantumStatesFrame.this.aux2Bar.setValue(48);
        }
        
        void getInfo(final String[] array, final int n) {
            array[n] = "Well width = " + QuantumStatesFrame.this.getLengthText(this.width);
            array[n] = array[n] + ", Well count = " + this.wellCount;
            array[n + 1] = "Well depth = " + QuantumStatesFrame.this.getEnergyText(2.0, false);
        }
        
        void drawPotential() {
            int n = 5;
            if (QuantumStatesFrame.this.sampleCount > 260) {
                n = QuantumStatesFrame.this.sampleCount / 52;
            }
            int n2 = QuantumStatesFrame.this.sampleCount * 7 / (n * 8) * (QuantumStatesFrame.this.aux3Bar.getValue() + 60) / 160;
            int n3 = QuantumStatesFrame.this.sampleCount / (n * 8);
            if (n3 == 0) {
                ++n3;
                --n2;
            }
            this.wellCount = QuantumStatesFrame.this.aux1Bar.getValue() * n / 101 + 1;
            final double n4 = QuantumStatesFrame.this.aux2Bar.getValue() / 400.0;
            final int n5 = (QuantumStatesFrame.this.sampleCount - this.wellCount * n2) / 2;
            this.width = n2 - n3;
            final double n6 = n4 * (this.width / 2);
            final double n7 = -2 * this.width * n6 / (2.0 * n6 - this.width);
            final double n8 = 1.0 + 2.0 * n7 / this.width;
            for (int i = 0; i != QuantumStatesFrame.this.sampleCount; ++i) {
                if (i < n5) {
                    QuantumStatesFrame.this.pot[i] = 1.0;
                }
                else {
                    final int n9 = i - n5;
                    final int n10 = n9 % n2;
                    if (n10 < n3 || n9 / n2 >= this.wellCount) {
                        QuantumStatesFrame.this.pot[i] = 1.0;
                    }
                    else {
                        QuantumStatesFrame.this.pot[i] = n8 - n7 / Math.abs(n10 - n3 - this.width / 2.0);
                        if (QuantumStatesFrame.this.pot[i] < -1.0) {
                            QuantumStatesFrame.this.pot[i] = -1.0;
                        }
                    }
                }
            }
        }
        
        Setup createNext() {
            return new FiniteWellArrayFieldSetup();
        }
    }
    
    class FiniteWellArrayFieldSetup extends Setup
    {
        int width;
        int wellCount;
        
        String getName() {
            return "Well Array + Field";
        }
        
        void select() {
            QuantumStatesFrame.this.aux1Label.setText("Well Count");
            QuantumStatesFrame.this.aux2Label.setText("Well Width");
            QuantumStatesFrame.this.aux3Label.setText("Field Strength");
            QuantumStatesFrame.this.aux3Bar.setValue(90);
            if (QuantumStatesFrame.this.massBar.getValue() == 16 && QuantumStatesFrame.this.resBar.getValue() <= 260) {
                QuantumStatesFrame.this.selectGround = false;
                QuantumStatesFrame.this.magcoef[3] = 0.067;
                QuantumStatesFrame.this.magcoef[4] = 0.2629;
                QuantumStatesFrame.this.magcoef[5] = 0.6771;
                QuantumStatesFrame.this.magcoef[6] = 1.0;
                QuantumStatesFrame.this.magcoef[7] = 0.586156;
                QuantumStatesFrame.this.magcoef[8] = 0.1058;
                QuantumStatesFrame.this.magcoef[9] = 0.078;
                for (int i = 0; i != QuantumStatesFrame.this.stateCount; ++i) {
                    QuantumStatesFrame.this.phasecoefadj[i] = ((i == 6) ? 0.0 : 3.141592653589793);
                }
            }
        }
        
        int getAuxBarCount() {
            return 3;
        }
        
        void getInfo(final String[] array, final int n) {
        }
        
        void drawPotential() {
            int n = 10;
            if (QuantumStatesFrame.this.sampleCount > 260) {
                n = QuantumStatesFrame.this.sampleCount / 26;
            }
            int n2 = QuantumStatesFrame.this.sampleCount * 7 / (n * 8) * (QuantumStatesFrame.this.aux2Bar.getValue() + 60) / 160;
            int n3 = QuantumStatesFrame.this.sampleCount / (n * 8);
            if (n3 == 0) {
                ++n3;
                --n2;
            }
            final double n4 = -(QuantumStatesFrame.this.aux3Bar.getValue() - 50) / (200.0 * QuantumStatesFrame.this.sampleCount / 2.0);
            this.wellCount = QuantumStatesFrame.this.aux1Bar.getValue() * n / 101 + 1;
            final double n5 = -0.75;
            final int n6 = (QuantumStatesFrame.this.sampleCount - this.wellCount * n2) / 2;
            this.width = n2 - n3;
            for (int i = 0; i != QuantumStatesFrame.this.sampleCount; ++i) {
                if (i < n6) {
                    QuantumStatesFrame.this.pot[i] = 0.75;
                }
                else {
                    final int n7 = i - n6;
                    QuantumStatesFrame.this.pot[i] = ((n7 % n2 < n3 || n7 / n2 >= this.wellCount) ? 0.75 : n5);
                }
                final double[] pot = QuantumStatesFrame.this.pot;
                final int n8 = i;
                pot[n8] += (i - QuantumStatesFrame.this.sampleCount / 2) * n4;
            }
            QuantumStatesFrame.this.pot[0] = (QuantumStatesFrame.this.pot[QuantumStatesFrame.this.sampleCount - 1] = 1000.0);
        }
        
        Setup createNext() {
            return new FiniteWellArrayImpureSetup();
        }
    }
    
    class FiniteWellArrayImpureSetup extends FiniteWellArraySetup
    {
        String getName() {
            return "Well Array w/ Impurity";
        }
        
        void select() {
            QuantumStatesFrame.this.aux1Label.setText("Impurity Position");
            QuantumStatesFrame.this.aux2Label.setText("Well Depth");
            QuantumStatesFrame.this.aux3Label.setText("Impurity Depth");
            QuantumStatesFrame.this.aux1Bar.setValue(75);
            QuantumStatesFrame.this.aux3Bar.setValue(85);
        }
        
        void getInfo(final String[] array, final int n) {
        }
        
        void drawPotential() {
            int wellCount = 10;
            if (QuantumStatesFrame.this.sampleCount > 260) {
                wellCount = QuantumStatesFrame.this.sampleCount / 26;
            }
            int n = QuantumStatesFrame.this.sampleCount * 7 / (wellCount * 8);
            int n2 = QuantumStatesFrame.this.sampleCount / (wellCount * 8);
            if (n2 == 0) {
                ++n2;
                --n;
            }
            this.wellCount = wellCount;
            final double n3 = 1.0 - QuantumStatesFrame.this.aux2Bar.getValue() / 50.0;
            final double n4 = 1.0 - QuantumStatesFrame.this.aux3Bar.getValue() / 50.0;
            final int n5 = QuantumStatesFrame.this.aux1Bar.getValue() * wellCount / 101;
            final int n6 = (QuantumStatesFrame.this.sampleCount - this.wellCount * n) / 2;
            this.width = n - n2;
            for (int i = 0; i != QuantumStatesFrame.this.sampleCount; ++i) {
                if (i < n6) {
                    QuantumStatesFrame.this.pot[i] = 1.0;
                }
                else {
                    final int n7 = i - n6;
                    final int n8 = n7 % n;
                    final double n9 = (n7 / n == n5) ? n4 : n3;
                    QuantumStatesFrame.this.pot[i] = ((n8 < n2 || n7 / n >= this.wellCount) ? 1.0 : n9);
                }
            }
        }
        
        double getFloor() {
            return -1.0;
        }
        
        Setup createNext() {
            return new FiniteWellArrayDislocSetup();
        }
    }
    
    class FiniteWellArrayDislocSetup extends FiniteWellArraySetup
    {
        String getName() {
            return "Well Array w/ Dislocation";
        }
        
        void select() {
            QuantumStatesFrame.this.aux1Label.setText("Impurity Position");
            QuantumStatesFrame.this.aux1Bar.setValue(75);
            QuantumStatesFrame.this.aux2Label.setText("Well Depth");
            QuantumStatesFrame.this.aux3Label.setText("Impurity Offset");
            QuantumStatesFrame.this.aux3Bar.setValue(80);
        }
        
        void getInfo(final String[] array, final int n) {
        }
        
        void drawPotential() {
            final int wellCount = 8;
            int n = QuantumStatesFrame.this.sampleCount * 7 / (wellCount * 8);
            int n2 = QuantumStatesFrame.this.sampleCount / (wellCount * 4);
            if (n2 == 0) {
                ++n2;
                --n;
            }
            this.wellCount = wellCount;
            final double n3 = 1.0 - QuantumStatesFrame.this.aux2Bar.getValue() / 50.0;
            final int n4 = QuantumStatesFrame.this.aux1Bar.getValue() * wellCount / 101;
            final int n5 = (QuantumStatesFrame.this.sampleCount - this.wellCount * n) / 2;
            final int n6 = (50 - QuantumStatesFrame.this.aux3Bar.getValue()) * (n2 - 1) / 50;
            this.width = n - n2;
            for (int i = 0; i != QuantumStatesFrame.this.sampleCount; ++i) {
                if (i < n5) {
                    QuantumStatesFrame.this.pot[i] = 1.0;
                }
                else {
                    final int n7 = i - n5;
                    QuantumStatesFrame.this.pot[i] = ((n7 % n < n2 || n7 / n == n4 || n7 / n >= this.wellCount) ? 1.0 : n3);
                    final int n8 = n7 + n6;
                    final int n9 = n8 % n;
                    if (n8 / n == n4 && n9 >= n2) {
                        QuantumStatesFrame.this.pot[i] = n3;
                    }
                }
            }
        }
        
        double getFloor() {
            return -1.0;
        }
        
        Setup createNext() {
            return new RandomWellArraySetup();
        }
    }
    
    class RandomWellArraySetup extends FiniteWellArraySetup
    {
        String getName() {
            return "Random Well Array";
        }
        
        void select() {
            QuantumStatesFrame.this.aux1Label.setText("Randomness");
        }
        
        void getInfo(final String[] array, final int n) {
        }
        
        int getAuxBarCount() {
            return 1;
        }
        
        void drawPotential() {
            int i;
            for (i = 0; i != 10; ++i) {
                QuantumStatesFrame.this.pot[i] = 1.0;
            }
            final int n = 8;
            while (i < QuantumStatesFrame.this.sampleCount - (15 + n)) {
                for (int j = 0; j != n; ++j) {
                    QuantumStatesFrame.this.pot[i++] = -1.0;
                }
                for (int n2 = 8 + (QuantumStatesFrame.this.getrand(15) - 7) * QuantumStatesFrame.this.aux1Bar.getValue() / 100, k = 0; k != n2; ++k) {
                    QuantumStatesFrame.this.pot[i++] = 1.0;
                }
            }
            while (i != QuantumStatesFrame.this.sampleCount) {
                QuantumStatesFrame.this.pot[i] = 1.0;
                ++i;
            }
        }
        
        Setup createNext() {
            return new FiniteWell2ArraySetup();
        }
    }
    
    class FiniteWell2ArraySetup extends Setup
    {
        int width;
        int wellCount;
        
        String getName() {
            return "2 Well Array";
        }
        
        void select() {
            QuantumStatesFrame.this.aux1Label.setText("Well Count");
            QuantumStatesFrame.this.aux2Label.setText("Well Depth");
            QuantumStatesFrame.this.aux3Label.setText("Well Width");
        }
        
        int getAuxBarCount() {
            return 3;
        }
        
        double getFloor() {
            return 1.0 - QuantumStatesFrame.this.aux2Bar.getValue() / 50.0;
        }
        
        void getInfo(final String[] array, final int n) {
        }
        
        void drawPotential() {
            int n = 10;
            if (QuantumStatesFrame.this.sampleCount > 260) {
                n = QuantumStatesFrame.this.sampleCount / 26;
            }
            final int n2 = n | 0x1;
            int n3 = QuantumStatesFrame.this.sampleCount * 7 / (n2 * 8) * (QuantumStatesFrame.this.aux3Bar.getValue() + 60) / 160;
            int n4 = QuantumStatesFrame.this.sampleCount / (n2 * 8);
            if (n4 == 0) {
                ++n4;
                --n3;
            }
            this.wellCount = QuantumStatesFrame.this.aux1Bar.getValue() * n2 / 101 + 1;
            final double floor = this.getFloor();
            final int n5 = (QuantumStatesFrame.this.sampleCount - this.wellCount * n3) / 2;
            this.width = n3 - n4;
            for (int i = 0; i != QuantumStatesFrame.this.sampleCount; ++i) {
                if (i < n5) {
                    QuantumStatesFrame.this.pot[i] = 1.0;
                }
                else {
                    final int n6 = i - n5;
                    final int n7 = n6 % n3;
                    final double n8 = (n6 / n3 % 2 == 0) ? floor : -0.5;
                    QuantumStatesFrame.this.pot[i] = ((n7 < n4 || n6 / n3 >= this.wellCount) ? 1.0 : n8);
                }
            }
        }
        
        Setup createNext() {
            return new FiniteWellCoupledArraySetup();
        }
    }
    
    class FiniteWellCoupledArraySetup extends Setup
    {
        int width;
        int wellCount;
        
        String getName() {
            return "Coupled Well Array";
        }
        
        void select() {
            QuantumStatesFrame.this.aux1Label.setText("Well Count");
            QuantumStatesFrame.this.aux2Label.setText("Wall Potential");
            QuantumStatesFrame.this.aux2Bar.setValue(50);
        }
        
        int getAuxBarCount() {
            return 2;
        }
        
        double getWallPot() {
            return 1.0 - QuantumStatesFrame.this.aux2Bar.getValue() / 50.0;
        }
        
        void getInfo(final String[] array, final int n) {
        }
        
        void drawPotential() {
            int n = 10;
            if (QuantumStatesFrame.this.sampleCount > 260) {
                n = QuantumStatesFrame.this.sampleCount / 26;
            }
            final int n2 = n | 0x1;
            int n3 = QuantumStatesFrame.this.sampleCount * 7 / (n2 * 8);
            int n4 = QuantumStatesFrame.this.sampleCount / (n2 * 8);
            if (n4 == 0) {
                ++n4;
                --n3;
            }
            this.wellCount = QuantumStatesFrame.this.aux1Bar.getValue() * n2 / 102 + 2;
            this.wellCount &= 0xFFFFFFFE;
            final int n5 = (QuantumStatesFrame.this.sampleCount - this.wellCount * n3) / 2;
            this.width = n3 - n4;
            for (int i = 0; i != QuantumStatesFrame.this.sampleCount; ++i) {
                if (i < n5 || i > QuantumStatesFrame.this.sampleCount - n5) {
                    QuantumStatesFrame.this.pot[i] = 1.0;
                }
                else {
                    final int n6 = i - n5;
                    final int n7 = n6 % n3;
                    final double n8 = (n6 / n3 % 2 == 0) ? 1.0 : this.getWallPot();
                    QuantumStatesFrame.this.pot[i] = ((n7 < n4 || n6 / n3 >= this.wellCount) ? n8 : -1.0);
                }
            }
        }
        
        Setup createNext() {
            return new DeltaArraySetup();
        }
    }
    
    class DeltaArraySetup extends Setup
    {
        String getName() {
            return "Delta Fn Array";
        }
        
        void select() {
            QuantumStatesFrame.this.aux1Label.setText("Well Count");
            QuantumStatesFrame.this.aux1Bar.setValue(32);
            QuantumStatesFrame.this.aux2Label.setText("Well Separation");
            QuantumStatesFrame.this.aux2Bar.setValue(30);
        }
        
        void drawPotential() {
            final int n = 30;
            final int n2 = QuantumStatesFrame.this.aux2Bar.getValue() / 5 + 2;
            int n3 = QuantumStatesFrame.this.aux1Bar.getValue() * n / 101 + 1;
            final int n4 = (QuantumStatesFrame.this.sampleCount - (n3 - 1) * n2 + 1) / 2;
            for (int i = 1; i != QuantumStatesFrame.this.sampleCount; ++i) {
                if (i < n4) {
                    QuantumStatesFrame.this.pot[i] = 1.0;
                }
                else {
                    final int n5 = (i - n4) % n2;
                    QuantumStatesFrame.this.pot[i] = 1.0;
                    if (n5 == 0 && n3-- > 0) {
                        QuantumStatesFrame.this.pot[i] = -1.0;
                    }
                }
            }
            for (int j = 0; j != n2; ++j) {
                QuantumStatesFrame.this.pot[j] = (QuantumStatesFrame.this.pot[QuantumStatesFrame.this.sampleCount - 1 - j] = 1.0);
            }
        }
        
        Setup createNext() {
            return null;
        }
    }
    
    class View extends Rectangle
    {
        int mid_y;
        int lower_y;
        double ymult;
        double ymult2;
        double scale;
    }
}
