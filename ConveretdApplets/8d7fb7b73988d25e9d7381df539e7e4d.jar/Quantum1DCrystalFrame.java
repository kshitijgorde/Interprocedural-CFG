import java.awt.Rectangle;
import java.awt.event.ItemEvent;
import org.netlib.lapack.Dsyevd;
import org.netlib.util.intW;
import java.awt.Event;
import java.awt.event.ActionEvent;
import java.awt.event.ComponentEvent;
import java.awt.event.MouseEvent;
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

class Quantum1DCrystalFrame extends Frame implements ComponentListener, ActionListener, MouseMotionListener, MouseListener, ItemListener, DecentScrollbarListener
{
    Thread engine;
    Dimension winSize;
    Image dbimage;
    Random random;
    int stateCount;
    int elevelCount;
    int maxStateCount;
    int sampleCount;
    int cellSampleCount;
    int potSampleCount;
    int pSampleCount;
    double[][] modes;
    double[][] modesLeft;
    double[][] dispersion;
    double expecte;
    double selectedK;
    double[] potTrans;
    double[] blochTrans;
    int selectedBand;
    public static final double epsilon = 1.0E-5;
    public static final double epsilon2 = 0.003;
    public static final double phasorBaseEnergy = 1.0;
    Button groundButton;
    Checkbox stoppedCheck;
    CheckboxMenuItem eCheckItem;
    CheckboxMenuItem xCheckItem;
    CheckboxMenuItem pCheckItem;
    CheckboxMenuItem blochCheckItem;
    CheckboxMenuItem dispersionCheckItem;
    CheckboxMenuItem expectCheckItem;
    CheckboxMenuItem probCheckItem;
    CheckboxMenuItem probPhaseCheckItem;
    CheckboxMenuItem reImCheckItem;
    CheckboxMenuItem magPhaseCheckItem;
    CheckboxMenuItem extendedZonesItem;
    CheckboxMenuItem reducedZonesItem;
    CheckboxMenuItem repeatedZonesItem;
    Menu waveFunctionMenu;
    Menu zoneMenu;
    MenuItem exitItem;
    Choice mouseChooser;
    Choice setupChooser;
    Vector setupList;
    Setup setup;
    DecentScrollbar forceBar;
    DecentScrollbar speedBar;
    DecentScrollbar wellCountBar;
    DecentScrollbar energyScaleBar;
    DecentScrollbar massBar;
    DecentScrollbar aux1Bar;
    DecentScrollbar aux2Bar;
    DecentScrollbar aux3Bar;
    DecentScrollbar aux4Bar;
    Label aux1Label;
    Label aux2Label;
    Label aux3Label;
    Label aux4Label;
    View viewPotential;
    View viewX;
    View viewP;
    View viewDispersion;
    View viewBloch;
    View[] viewList;
    int viewCount;
    double[] elevels;
    double[] dispmax;
    static final double pi = 3.141592653589793;
    double step;
    double[] func;
    double[] funci;
    double[] blochr;
    double[] blochi;
    double[] pdata;
    double[] pdatar;
    double[] pdatai;
    double[] pot;
    double mass;
    double escale;
    int selectedCoef;
    int selectedPaneHandle;
    int kUpdateState;
    int kUpdateSkip;
    static final int stateBuffer = 5;
    static final int SEL_NONE = 0;
    static final int SEL_POTENTIAL = 1;
    static final int SEL_X = 2;
    static final int SEL_P = 3;
    static final int SEL_STATES = 4;
    static final int SEL_HANDLE = 5;
    static final int SEL_DISPERSION = 6;
    static final int MOUSE_EIGEN = 0;
    static final int MOUSE_EDIT = 1;
    int selection;
    int dragX;
    int dragY;
    int[] xpoints;
    int[] ypoints;
    boolean dragging;
    boolean startup;
    boolean selectGround;
    boolean levelsChanged;
    boolean stateChanged;
    boolean setupModified;
    double t;
    int pause;
    static final int phaseColorCount = 480;
    Color[] phaseColors;
    FFT fft;
    Quantum1DCrystalCanvas cv;
    Quantum1DCrystal applet;
    NumberFormat showFormat;
    long lastTime;
    int stateColSize;
    int stateSize;
    
    public String getAppletInfo() {
        return "Quantum1DCrystal by Paul Falstad";
    }
    
    int getrand(final int n) {
        int nextInt = this.random.nextInt();
        if (nextInt < 0) {
            nextInt = -nextInt;
        }
        return nextInt % n;
    }
    
    Quantum1DCrystalFrame(final Quantum1DCrystal applet) {
        super("1-d Quantum Crystal Applet v1.0b");
        this.engine = null;
        this.maxStateCount = 500;
        this.sampleCount = 320;
        this.cellSampleCount = 32;
        this.potSampleCount = 128;
        this.applet = applet;
    }
    
    public void init() {
        this.startup = true;
        this.xpoints = new int[5];
        this.ypoints = new int[5];
        this.setupList = new Vector();
        for (Setup next = new FiniteWellSetup(); next != null; next = next.createNext()) {
            this.setupList.addElement(next);
        }
        this.selectedCoef = -1;
        this.setLayout(new Quantum1DCrystalLayout());
        (this.cv = new Quantum1DCrystalCanvas(this)).addComponentListener(this);
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
        menu2.add(this.blochCheckItem = this.getCheckItem("Bloch Function"));
        menu2.add(this.dispersionCheckItem = this.getCheckItem("Dispersion"));
        this.dispersionCheckItem.setState(true);
        menu2.addSeparator();
        final Menu waveFunctionMenu = new Menu("Wave Function");
        this.waveFunctionMenu = waveFunctionMenu;
        final Menu menu3 = waveFunctionMenu;
        menu2.add(menu3);
        menu3.add(this.probCheckItem = this.getCheckItem("Probability"));
        menu3.add(this.probPhaseCheckItem = this.getCheckItem("Probability + Phase"));
        this.probPhaseCheckItem.setState(true);
        menu3.add(this.reImCheckItem = this.getCheckItem("Real + Imaginary Parts"));
        menu3.add(this.magPhaseCheckItem = this.getCheckItem("Magnitude + Phase"));
        final Menu zoneMenu = new Menu("Zones");
        this.zoneMenu = zoneMenu;
        final Menu menu4 = zoneMenu;
        menuBar.add(menu4);
        menu4.add(this.extendedZonesItem = this.getCheckItem("Extended Zone Scheme"));
        menu4.add(this.reducedZonesItem = this.getCheckItem("Reduced Zone Scheme"));
        this.reducedZonesItem.setState(true);
        menu4.add(this.repeatedZonesItem = this.getCheckItem("Repeated Zone Scheme"));
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
        this.mouseChooser.addItemListener(this);
        this.add(this.mouseChooser);
        this.mouseChooser.select(0);
        this.add(this.groundButton = new Button("Ground State"));
        this.groundButton.addActionListener(this);
        (this.stoppedCheck = new Checkbox("Stopped")).addItemListener(this);
        this.add(this.stoppedCheck);
        this.add(new Label("Simulation Speed", 1));
        this.add(this.speedBar = new DecentScrollbar(this, 50, 1, 150));
        this.add(new Label("Particle Mass", 1));
        this.add(this.massBar = new DecentScrollbar(this, 100, 10, 500));
        this.add(new Label("# of Wells Shown", 1));
        this.add(this.wellCountBar = new DecentScrollbar(this, 5, 1, 50));
        this.add(new Label("Energy Scale", 1));
        this.add(this.energyScaleBar = new DecentScrollbar(this, 100, 1, 100));
        this.add(this.aux1Label = new Label("Aux 1", 1));
        this.add(this.aux1Bar = new DecentScrollbar(this, 50, 1, 100));
        this.add(this.aux2Label = new Label("Aux 2", 1));
        this.add(this.aux2Bar = new DecentScrollbar(this, 50, 1, 100));
        this.add(this.aux3Label = new Label("Aux 3", 1));
        this.add(this.aux3Bar = new DecentScrollbar(this, 50, 1, 100));
        this.add(this.aux4Label = new Label("Aux 4", 1));
        this.add(this.aux4Bar = new DecentScrollbar(this, 50, 1, 100));
        try {
            final String parameter = this.applet.getParameter("PAUSE");
            if (parameter != null) {
                this.pause = Integer.parseInt(parameter);
            }
        }
        catch (Exception ex) {}
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
        final int n = 20;
        final int n2 = (this.viewPotential == null) ? 0 : (this.viewPotential.height + n);
        final int n3 = (this.viewDispersion == null) ? 0 : (this.viewDispersion.height + n);
        final View viewX = null;
        this.viewBloch = viewX;
        this.viewPotential = viewX;
        this.viewDispersion = viewX;
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
        if (this.blochCheckItem.getState()) {
            this.viewList[viewCount++] = (this.viewBloch = new View());
        }
        if (this.pCheckItem.getState()) {
            this.viewList[viewCount++] = (this.viewP = new View());
        }
        if (this.dispersionCheckItem.getState()) {
            this.viewList[viewCount++] = (this.viewDispersion = new View());
        }
        this.viewCount = viewCount;
        int viewCount2 = this.viewCount;
        int height = this.winSize.height;
        if (n2 > 0 && this.viewPotential != null) {
            --viewCount2;
            height -= n2;
        }
        if (n3 > 0 && this.viewDispersion != null) {
            --viewCount2;
            height -= n3;
        }
        int handle = 0;
        for (int i = 0; i != this.viewCount; ++i) {
            final View view = this.viewList[i];
            int n4 = height / viewCount2;
            if (view == this.viewPotential && n2 > 0) {
                n4 = n2;
            }
            else if (view == this.viewDispersion && n3 > 0) {
                n4 = n3;
            }
            view.x = 0;
            view.width = this.winSize.width;
            view.y = handle + n;
            view.height = n4 - n;
            view.handle = handle;
            handle += n4;
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
        this.select(0.0, 0);
    }
    
    void doBlank() {
        this.t = 0.0;
        if (this.winSize != null && this.winSize.width > 0) {
            this.dbimage = this.createImage(this.winSize.width, this.winSize.height);
        }
    }
    
    void centerString(final Graphics graphics, final String s, final int n) {
        graphics.drawString(s, (this.winSize.width - graphics.getFontMetrics().stringWidth(s)) / 2, n);
    }
    
    public void paint(final Graphics graphics) {
        this.cv.repaint();
    }
    
    public void updateQuantum1DCrystal(final Graphics graphics) {
        final Graphics graphics2 = this.dbimage.getGraphics();
        if (this.winSize == null || this.winSize.width == 0) {
            return;
        }
        double n = 0.0;
        if (!this.stoppedCheck.getState() && !this.dragging) {
            final double n2 = Math.exp(this.speedBar.getValue() / 20.0) * 0.02;
            final long currentTimeMillis = System.currentTimeMillis();
            if (this.lastTime == 0L) {
                this.lastTime = currentTimeMillis;
            }
            n = n2 * ((currentTimeMillis - this.lastTime) * 0.058823529411764705);
            this.lastTime = currentTimeMillis;
        }
        else {
            this.lastTime = 0L;
        }
        final Color color = new Color(76, 76, 76);
        final Color color2 = new Color(127, 127, 127);
        graphics2.setColor(this.cv.getBackground());
        graphics2.fillRect(0, 0, this.winSize.width, this.winSize.height);
        graphics2.setColor(this.cv.getForeground());
        int n3 = -1;
        for (int i = 1; i != this.viewCount; ++i) {
            graphics2.setColor((i == this.selectedPaneHandle) ? Color.yellow : Color.gray);
            graphics2.drawLine(0, this.viewList[i].handle, this.winSize.width, this.viewList[i].handle);
        }
        if (this.levelsChanged || this.stateChanged) {
            this.cv.setCursor(Cursor.getPredefinedCursor(3));
            if (this.levelsChanged) {
                this.genStates();
            }
            else {
                this.getBands(true, this.selectedK, this.selectedBand);
            }
            this.cv.setCursor(null);
            final boolean b = false;
            this.stateChanged = b;
            this.levelsChanged = b;
        }
        else {
            final long currentTimeMillis2 = System.currentTimeMillis();
            while (this.updateK() && System.currentTimeMillis() < currentTimeMillis2 + 50L) {}
        }
        int n4 = -1;
        if (this.viewPotential != null) {
            final int mid_y = this.viewPotential.mid_y;
            final double ymult = this.viewPotential.ymult;
            graphics2.setColor(Color.gray);
            for (int j = 0; j < this.elevelCount; j += 2) {
                graphics2.setColor(Color.darkGray);
                final double n5 = this.elevels[j];
                final int energyY = this.getEnergyY(this.elevels[j + 1], this.viewPotential);
                int n6 = this.getEnergyY(n5, this.viewPotential) - energyY;
                if (n6 <= 0) {
                    n6 = 1;
                }
                graphics2.fillRect(0, energyY, this.winSize.width, n6);
            }
            graphics2.setColor(Color.white);
            final int value = this.wellCountBar.getValue();
            final double n7 = this.winSize.width / (this.potSampleCount * value);
            for (int k = 0; k != this.potSampleCount * value; ++k) {
                final int n8 = (int)(n7 * k);
                final int energyY2 = this.getEnergyY(this.pot[k & this.potSampleCount - 1], this.viewPotential);
                if (n4 != -1) {
                    graphics2.drawLine(n4, n3, n8, energyY2);
                }
                n4 = n8;
                n3 = energyY2;
            }
            final int energyY3 = this.getEnergyY(this.expecte, this.viewPotential);
            graphics2.setColor(Color.red);
            graphics2.drawLine(0, energyY3, this.winSize.width, energyY3);
        }
        graphics2.setColor(Color.white);
        double n9 = 0.0;
        for (int l = 0; l != this.sampleCount; ++l) {
            final int n10 = this.winSize.width * l / this.sampleCount;
            final double n11 = this.func[l];
            final double n12 = this.funci[l];
            final double n13 = n11 * n11 + n12 * n12;
            if (n13 > n9) {
                n9 = n13;
            }
        }
        final double n14 = (this.expecte + 1.0) * n;
        final double cos = Math.cos(n14);
        final double n15 = -Math.sin(n14);
        for (int n16 = 0; n16 != this.sampleCount; ++n16) {
            final double n17 = this.func[n16];
            final double n18 = this.funci[n16];
            this.func[n16] = n17 * cos - n18 * n15;
            this.funci[n16] = n17 * n15 + n18 * cos;
            final double n19 = this.blochr[n16];
            final double n20 = this.blochi[n16];
            this.blochr[n16] = n19 * cos - n20 * n15;
            this.blochi[n16] = n19 * n15 + n20 * cos;
        }
        if (this.viewX != null) {
            this.viewX.drawLabel(graphics2, "Wave Function (Position)");
            final int mid_y2 = this.viewX.mid_y;
            final double ymult2 = this.viewX.ymult;
            this.drawFunction(graphics2, this.viewX, this.func, this.funci, this.sampleCount, 0);
            if (this.selectedCoef != -1 && !this.dragging) {
                graphics2.setColor(Color.yellow);
                int n21 = -1;
                for (int n22 = 0; n22 != this.sampleCount; ++n22) {
                    final int n23 = this.winSize.width * n22 / this.sampleCount;
                    final int n24 = mid_y2 - (int)(ymult2 * (this.modes[this.selectedCoef][n22] / this.dispmax[this.selectedCoef]));
                    if (n21 != -1) {
                        graphics2.drawLine(n21, n3, n23, n24);
                    }
                    n21 = n23;
                    n3 = n24;
                }
            }
        }
        if (this.viewP != null) {
            this.viewP.drawLabel(graphics2, "Momentum");
            graphics2.setColor(color2);
            graphics2.drawLine(this.winSize.width / 2, this.viewP.mid_y - (int)this.viewP.ymult, this.winSize.width / 2, this.viewP.mid_y + (int)this.viewP.ymult);
            for (int n25 = 0; n25 != this.pdatar.length; ++n25) {
                this.pdatar[n25] = (this.pdatai[n25] = 0.0);
            }
            final int n26 = this.blochTrans.length / 4;
            final int n27 = this.blochTrans.length - 1;
            final int n28 = 8 * -(((this.selectedBand & 0x1) == 0x1) ? 1 : -1);
            final int n29 = this.pdatar.length / 2 + (int)(this.selectedK * n28);
            for (int n30 = 16, n31 = 0; n31 != n30; ++n31) {
                this.pdatar[n29 + n28 * n31] = this.blochTrans[n31 * 2];
                this.pdatar[n29 - n28 * n31] = this.blochTrans[n27 & -n31 * 2];
                this.pdatai[n29 + n28 * n31] = this.blochTrans[n31 * 2 + 1];
                this.pdatai[n29 - n28 * n31] = this.blochTrans[n27 & -n31 * 2 + 1];
            }
            for (int n32 = this.pdatar.length - 1; n32 > 0; --n32) {
                if (this.pdatar[n32] == 0.0 && this.pdatai[n32] == 0.0) {
                    this.pdatar[n32] = this.pdatar[n32 - 1] * 1.0E-16;
                    this.pdatai[n32] = this.pdatai[n32 - 1] * 1.0E-16;
                }
            }
            this.drawFunction(graphics2, this.viewP, this.pdatar, this.pdatai, this.pSampleCount / 2, this.pSampleCount / 4);
        }
        if (this.viewDispersion != null) {
            this.viewDispersion.drawLabel(graphics2, "Dispersion (E vs. k)");
            graphics2.setColor(Color.gray);
            final int mid_y3 = this.viewDispersion.mid_y;
            final double ymult3 = this.viewDispersion.ymult;
            final double n33 = this.elevels[0] + 1.0;
            final int n34 = this.viewDispersion.y + 5;
            for (int n35 = 0; n35 < this.elevelCount; n35 += 2) {
                graphics2.setColor(Color.darkGray);
                final double n36 = this.elevels[n35] - n33;
                int energyY4 = this.getEnergyY(this.elevels[n35 + 1] - n33, this.viewDispersion);
                final int energyY5 = this.getEnergyY(n36, this.viewDispersion);
                if (energyY5 >= n34) {
                    if (energyY4 < n34) {
                        energyY4 = n34;
                    }
                    int n37 = energyY5 - energyY4;
                    if (n37 <= 0) {
                        n37 = 1;
                    }
                    graphics2.fillRect(0, energyY4, this.winSize.width, n37);
                }
            }
            graphics2.setColor(Color.white);
            if (this.repeatedZonesItem.getState()) {
                this.viewDispersion.cellw2 = this.viewDispersion.width / 10;
                final int n38 = this.viewDispersion.width / 2 / this.viewDispersion.cellw2 + 1;
                for (int n39 = 0; n39 != this.dispersion.length; ++n39) {
                    for (int n40 = -n38; n40 <= n38; ++n40) {
                        this.drawDispersion(graphics2, n39, n40, true, true);
                    }
                }
            }
            if (this.reducedZonesItem.getState()) {
                this.viewDispersion.cellw2 = this.viewDispersion.width / 3;
                for (int n41 = 0; n41 != this.dispersion.length; ++n41) {
                    this.drawDispersion(graphics2, n41, 0, true, true);
                }
            }
            if (this.extendedZonesItem.getState()) {
                this.viewDispersion.cellw2 = this.viewDispersion.width / 10;
                boolean b2 = true;
                for (int n42 = 0; n42 != this.dispersion.length; ++n42) {
                    this.drawDispersion(graphics2, n42, (n42 + 1) / 2, b2, !b2);
                    this.drawDispersion(graphics2, n42, -(n42 + 1) / 2, !b2, b2);
                    b2 = !b2;
                }
            }
            final int energyY6 = this.getEnergyY(this.expecte - n33, this.viewDispersion);
            graphics2.setColor(Color.red);
            if (energyY6 >= this.viewDispersion.y) {
                graphics2.drawLine(0, energyY6, this.winSize.width, energyY6);
            }
            final int n43 = (int)(this.viewDispersion.cellw2 * this.selectedK * 2.0 + this.viewDispersion.width / 2);
            if (!this.extendedZonesItem.getState()) {
                graphics2.drawLine(n43, n34, n43, this.viewDispersion.y + this.viewDispersion.height);
            }
        }
        if (this.viewBloch != null) {
            this.viewBloch.drawLabel(graphics2, "Bloch Function");
            this.drawFunction(graphics2, this.viewBloch, this.blochr, this.blochi, this.blochr.length, 0);
        }
        graphics.drawImage(this.dbimage, 0, 0, this);
        if (!this.stoppedCheck.getState()) {
            this.cv.repaint(this.pause);
        }
    }
    
    int getEnergyY(double n, final View view) {
        this.escale = this.energyScaleBar.getValue() / 100.0;
        n = (n + 1.0) * this.escale - 1.0;
        return view.mid_y - (int)(view.ymult * n);
    }
    
    void drawDispersion(final Graphics graphics, final int n, final int n2, final boolean b, final boolean b2) {
        int n3 = -1;
        int n4 = -1;
        final double n5 = this.elevels[0] + 1.0;
        final int cellw2 = this.viewDispersion.cellw2;
        final int n6 = cellw2 * 2;
        final int n7 = this.viewDispersion.y + 5;
        for (int i = 0; i != this.dispersion[0].length; ++i) {
            final int n8 = n2 * n6 + this.viewDispersion.width / 2;
            final int n9 = i * cellw2 / (this.dispersion[0].length - 1);
            final int energyY = this.getEnergyY(this.dispersion[n][i] - n5, this.viewDispersion);
            if (n3 != -1 && energyY > n7 && n4 > n7) {
                if (b) {
                    graphics.drawLine(n8 + n3, n4, n8 + n9, energyY);
                }
                if (b2) {
                    graphics.drawLine(n8 - n3, n4, n8 - n9, energyY);
                }
            }
            n3 = n9;
            n4 = energyY;
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
        Math.sqrt(n15 - n14 * n14);
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
        graphics.setColor(Color.gray);
        if ((this.probCheckItem.getState() || this.probPhaseCheckItem.getState() || this.magPhaseCheckItem.getState()) && array2 != null) {
            graphics.setColor(Color.white);
            final double n18 = view.ymult2 * view.scale;
            for (int j = 0; j != n; ++j) {
                final int n19 = this.winSize.width * j / (n - 1);
                final int n20 = j + n2;
                double sqrt2;
                if (!this.magPhaseCheckItem.getState()) {
                    sqrt2 = array[n20] * array[n20] + array2[n20] * array2[n20];
                }
                else {
                    sqrt2 = Math.sqrt(array[n20] * array[n20] + array2[n20] * array2[n20]);
                }
                if (!this.probCheckItem.getState()) {
                    graphics.setColor(this.phaseColors[(int)((Math.atan2(array2[n20], array[n20]) + 3.141592653589793) * 480.0 / 6.483185307179586)]);
                }
                int y = view.lower_y - (int)(n18 * sqrt2);
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
            graphics.setColor(Color.darkGray);
            final int mid_y = view.mid_y;
            graphics.drawLine(0, mid_y, this.winSize.width, mid_y);
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
        }
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
            case 6: {
                this.editDispersion(x, y);
                break;
            }
            default: {
                this.editFunc(x, y);
                break;
            }
        }
    }
    
    void editHandle(final int n) {
        final int n2 = n - this.viewList[this.selectedPaneHandle].handle;
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
        view6.handle += n2;
        this.setGraphLines();
        this.cv.repaint(this.pause);
    }
    
    void editFunc(int i, int n) {
        if (this.mouseChooser.getSelectedIndex() == 0) {
            if (this.selection == 1) {
                this.selectStateByEnergy(n);
            }
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
    }
    
    void editFuncPoint(final int n, final int n2) {
        if (this.selection != 1) {
            return;
        }
        final View view = (this.selection == 2) ? this.viewX : this.viewPotential;
        final int n3 = this.potSampleCount * this.wellCountBar.getValue();
        int i = n * n3 / this.winSize.width;
        int n4 = ((n + 1) * n3 - 1) / this.winSize.width;
        double n5 = (view.mid_y - n2) / view.ymult;
        double n6 = (view.lower_y - n2) / view.ymult2;
        if (n5 > 1.0) {
            n5 = 1.0;
        }
        if (n5 < -1.0) {
            n5 = -1.0;
        }
        if (n6 > 1.0) {
            n6 = 1.0;
        }
        if (n6 < 0.0) {
            n6 = 0.0;
        }
        double n7 = (n5 + 1.0) / this.escale - 1.0;
        final double n8 = (n6 + 1.0) / this.escale - 1.0;
        if (n7 > 1.0) {
            n7 = 1.0;
        }
        if (n8 > 1.0) {}
        if (i < 1) {
            i = 1;
        }
        if (n4 >= this.sampleCount - 1) {
            n4 = this.sampleCount - 2;
        }
        while (i <= n4) {
            this.pot[i % this.potSampleCount] = n7;
            this.setupModified = true;
            this.getPotTrans();
            this.levelsChanged = true;
            ++i;
        }
        this.cv.repaint(this.pause);
    }
    
    void editDispersion(int n, final int n2) {
        n -= this.viewDispersion.width / 2;
        double n3 = n * 0.5 / this.viewDispersion.cellw2;
        if (this.extendedZonesItem.getState()) {
            int n4;
            for (n4 = 0; n3 > 0.5; n3 -= 0.5, ++n4) {}
            while (n3 < -0.5) {
                n3 += 0.5;
                ++n4;
            }
            if ((n4 & 0x1) != 0x0) {
                n3 = ((n3 >= 0.0) ? (0.5 - n3) : (-0.5 - n3));
            }
            this.select(n3, n4);
            return;
        }
        if (this.repeatedZonesItem.getState()) {
            while (n3 > 0.5) {
                --n3;
            }
            while (n3 < -0.5) {
                ++n3;
            }
        }
        if (n3 > 0.5) {
            n3 = 0.5;
        }
        if (n3 < -0.5) {
            n3 = -0.5;
        }
        int n5 = 1000;
        int n6 = 0;
        final double n7 = this.elevels[0] + 1.0;
        for (int i = 0; i != this.dispersion.length; ++i) {
            final int energyY = this.getEnergyY(this.elevels[i * 2] - n7, this.viewDispersion);
            final int energyY2 = this.getEnergyY(this.elevels[i * 2 + 1] - n7, this.viewDispersion);
            if (n2 < energyY && n2 > energyY2) {
                n6 = i;
                break;
            }
            final int abs = Math.abs(n2 - energyY);
            if (abs < n5) {
                n5 = abs;
                n6 = i;
            }
            final int abs2 = Math.abs(n2 - energyY2);
            if (abs2 < n5) {
                n5 = abs2;
                n6 = i;
            }
        }
        this.select(n3, n6);
    }
    
    void select(final double selectedK, final int selectedBand) {
        this.selectedK = selectedK;
        this.selectedBand = selectedBand;
        this.stateChanged = true;
        this.cv.repaint(this.pause);
    }
    
    void getPotTrans() {
        this.potTrans = new double[this.potSampleCount * 2];
        for (int i = 0; i != this.potSampleCount; ++i) {
            this.potTrans[i * 2] = this.pot[i];
        }
        new FFT(this.potSampleCount).transform(this.potTrans, false);
    }
    
    double getPState(final int n) {
        return (n * this.pSampleCount / 2 / this.winSize.width - this.pSampleCount / 4) * 3.141592653589793 / (this.pSampleCount / 2);
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
    }
    
    public void scrollbarValueChanged(final DecentScrollbar decentScrollbar) {
        System.out.print(decentScrollbar.getValue() + "\n");
        if (decentScrollbar == this.massBar) {
            this.levelsChanged = true;
        }
        if (decentScrollbar == this.aux1Bar || decentScrollbar == this.aux2Bar || decentScrollbar == this.aux3Bar || decentScrollbar == this.aux4Bar) {
            this.setup.drawPotential();
            this.getPotTrans();
            this.levelsChanged = true;
        }
        if (decentScrollbar == this.wellCountBar) {
            this.setResolution();
            this.setup.drawPotential();
            this.stateChanged = true;
        }
        this.cv.repaint(this.pause);
    }
    
    public void scrollbarFinished(final DecentScrollbar decentScrollbar) {
    }
    
    public boolean handleEvent(final Event event) {
        if (event.id == 201) {
            this.applet.destroyFrame();
            return true;
        }
        return super.handleEvent(event);
    }
    
    void setResolution() {
        final int value = this.wellCountBar.getValue();
        this.cellSampleCount = 8;
        while (this.cellSampleCount < 700 / value) {
            this.cellSampleCount *= 2;
        }
        this.sampleCount = this.cellSampleCount * value;
        this.func = new double[this.sampleCount];
        this.funci = new double[this.sampleCount];
        this.blochr = new double[this.sampleCount];
        this.blochi = new double[this.sampleCount];
        this.pot = new double[this.potSampleCount];
        this.stateChanged = true;
        this.pSampleCount = 512;
        this.pdatar = new double[this.pSampleCount];
        this.pdatai = new double[this.pSampleCount];
        this.fft = new FFT(this.pSampleCount);
    }
    
    void genStates() {
        this.levelsChanged = false;
        if (this.potTrans == null) {
            this.getPotTrans();
        }
        final double[] bands = this.getBands(false, 0.0, 0);
        final double[] bands2 = this.getBands(false, 0.5, 0);
        this.elevelCount = bands.length * 2;
        this.elevels = new double[this.elevelCount];
        this.stateCount = this.elevelCount;
        for (int i = 0; i != bands.length; ++i) {
            final int n = i & 0x1;
            this.elevels[i * 2 + n] = bands[i];
            this.elevels[i * 2 + 1 - n] = bands2[i];
        }
        final int n2 = 65;
        final int length = bands.length;
        this.dispersion = new double[length][n2];
        for (int j = 0; j != length; ++j) {
            this.dispersion[j][0] = bands[j];
            this.dispersion[j][n2 - 1] = bands2[j];
            for (int k = 1; k < n2 - 1; ++k) {
                final double n3 = k / (n2 - 1.0);
                this.dispersion[j][k] = bands[j] * (1.0 - n3) + bands2[j] * n3;
            }
        }
        this.kUpdateState = n2 / 2;
        this.kUpdateSkip = this.kUpdateState * 2;
        this.getBands(true, this.selectedK, this.selectedBand);
    }
    
    boolean updateK() {
        if (this.kUpdateSkip <= 1) {
            return false;
        }
        final int length = this.dispersion[0].length;
        final double[] bands = this.getBands(false, this.kUpdateState / (2.0 * (length - 1)), 0);
        for (int i = 0; i != bands.length; ++i) {
            this.dispersion[i][this.kUpdateState] = bands[i];
            for (int n = this.kUpdateSkip / 2, j = 1; j < n; ++j) {
                final double n2 = j / n;
                this.dispersion[i][this.kUpdateState - j] = (1.0 - n2) * this.dispersion[i][this.kUpdateState] + n2 * this.dispersion[i][this.kUpdateState - n];
                this.dispersion[i][this.kUpdateState + j] = (1.0 - n2) * this.dispersion[i][this.kUpdateState] + n2 * this.dispersion[i][this.kUpdateState + n];
            }
        }
        this.kUpdateState += this.kUpdateSkip;
        if (this.kUpdateState >= length) {
            this.kUpdateSkip /= 2;
            if (this.kUpdateSkip == 1) {
                return false;
            }
            this.kUpdateState = this.kUpdateSkip / 2;
        }
        return true;
    }
    
    int getFourierIndex(final int n, final int n2) {
        return (n > n2 / 2) ? (n2 / 2 - n) : n;
    }
    
    double[] getBands(final boolean b, final double n, final int n2) {
        final int n3 = 48;
        final int n4 = n3 * 2;
        final double[] array = new double[n4 * n4];
        this.mass = this.massBar.getValue() * 2.0E-4;
        final double n5 = 1.0 / this.mass;
        this.mass *= 1596875.0;
        for (int i = 0; i != n3; ++i) {
            final int fourierIndex = this.getFourierIndex(i, n3);
            final double n6 = fourierIndex + n;
            array[i + n3 + n4 * (i + n3)] = (array[i + n4 * i] = n5 * n6 * n6 * 0.01);
            for (int j = 0; j != n3; ++j) {
                int n7 = this.getFourierIndex(j, n3) - fourierIndex;
                if (n7 < 0) {
                    n7 += this.potTrans.length / 2;
                }
                final double n8 = this.potTrans[n7 * 2] / this.potSampleCount;
                final double n9 = this.potTrans[n7 * 2 + 1] / this.potSampleCount;
                final double[] array2 = array;
                final int n10 = i + n4 * j;
                array2[n10] += n8;
                final double[] array3 = array;
                final int n11 = i + n3 + n4 * (j + n3);
                array3[n11] += n8;
                final double[] array4 = array;
                final int n12 = i + n3 + j * n4;
                array4[n12] -= n9;
                final double[] array5 = array;
                final int n13 = i + n4 * (j + n3);
                array5[n13] += n9;
            }
        }
        final double[] array6 = new double[1 + 5 * n4 + 5 * n4 * n4];
        final int[] array7 = new int[3 + 5 * n4];
        final intW intW = new intW(0);
        final double[] array8 = new double[n4];
        Dsyevd.dsyevd(b ? "V" : "N", "U", n4, array, 0, n4, array8, 0, array6, 0, array6.length, array7, 0, array7.length, intW);
        final double[] array9 = new double[n4];
        for (int k = 0; k != n4; ++k) {
            array9[k] = array8[k];
        }
        for (int l = 1; l < n4; ++l) {
            final double n14 = array9[l];
            int n15 = l;
            while (array9[n15 - 1] > n14) {
                array9[n15] = array9[n15 - 1];
                if (--n15 <= 0) {
                    break;
                }
            }
            array9[n15] = n14;
        }
        final double[] array10 = new double[20];
        for (int n16 = 0; n16 != 20; ++n16) {
            array10[n16] = array9[n16 * 2];
        }
        if (!b) {
            return array10;
        }
        int n17 = 0;
        for (int n18 = 0; n18 != this.stateCount; ++n18) {
            int n19;
            for (n19 = 0; n19 != n3 && array9[n18] != array8[n19]; ++n19) {}
            if (n19 == n3) {
                System.out.print("can't find elevels! " + n18 + " " + array9[n18] + "\n");
            }
            else {
                array8[n19] = -1.0;
                if (n17++ >= n2 * 2) {
                    final double[] array11 = new double[this.cellSampleCount * 2];
                    final int n20 = n4 * n19;
                    this.blochTrans = new double[this.cellSampleCount * 2];
                    final int n21 = (array[n20 + 1] < 0.0) ? -1 : 1;
                    for (int n22 = 0; n22 != n3; ++n22) {
                        int fourierIndex2 = this.getFourierIndex(n22, n3);
                        if (Math.abs(fourierIndex2) < this.cellSampleCount / 2) {
                            if (fourierIndex2 < 0) {
                                fourierIndex2 += array11.length / 2;
                            }
                            this.blochTrans[fourierIndex2 * 2] = (array11[fourierIndex2 * 2] = n21 * array[n22 + n20]);
                            this.blochTrans[fourierIndex2 * 2 + 1] = (array11[fourierIndex2 * 2 + 1] = n21 * array[n22 + n3 + n20]);
                        }
                    }
                    new FFT(this.cellSampleCount).transform(array11, true);
                    final double n23 = 6.283185307179586 * n / this.cellSampleCount;
                    final int n24 = ((n2 & 0x1) == 0x1) ? 1 : -1;
                    for (int n25 = 0; n25 != this.sampleCount; ++n25) {
                        final int n26 = n25 % this.cellSampleCount;
                        final double n27 = array11[n26 * 2];
                        final double n28 = array11[n26 * 2 + 1];
                        final double cos = Math.cos(n25 * n23);
                        final double n29 = -Math.sin(n25 * n23);
                        this.func[n25] = n27 * cos - n28 * n29;
                        this.funci[n25] = n24 * (n27 * n29 + n28 * cos);
                        this.blochr[n25] = n27;
                        this.blochi[n25] = n24 * n28;
                    }
                    this.expecte = array9[n2 * 2];
                    break;
                }
            }
        }
        return null;
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
        this.selectedPaneHandle = -1;
        this.selection = 0;
        for (int i = 1; i != this.viewCount; ++i) {
            final int n = y - this.viewList[i].handle;
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
            this.cv.repaint(this.pause);
        }
        else if (this.viewPotential != null && this.viewPotential.contains(x, y)) {
            this.selection = 1;
        }
        else if (this.viewDispersion != null && this.viewDispersion.contains(x, y)) {
            this.selection = 6;
        }
        this.cv.setCursor(predefinedCursor);
        if (this.selection != selection || this.selectedCoef != selectedCoef) {
            this.cv.repaint(this.pause);
        }
    }
    
    void selectStateByEnergy(final int n) {
        int n2 = 0;
        double n3 = 1000.0;
        double n4 = 0.0;
        for (int i = 0; i != this.dispersion.length; ++i) {
            for (int j = 0; j != this.dispersion[0].length; ++j) {
                final double abs = Math.abs(n - (this.getEnergyY(this.dispersion[i][j], this.viewPotential) - this.dispersion[i][j] * 1.0E-8));
                if (abs < n3) {
                    n3 = abs;
                    n2 = i;
                    n4 = j * 0.5 / (this.dispersion[0].length - 1);
                }
            }
        }
        this.select(n4, n2);
    }
    
    public void mouseClicked(final MouseEvent mouseEvent) {
        if (mouseEvent.getClickCount() == 2 && this.selectedCoef != -1) {
            this.enterSelectedState();
        }
    }
    
    void enterSelectedState() {
    }
    
    public void mouseEntered(final MouseEvent mouseEvent) {
    }
    
    public void mouseExited(final MouseEvent mouseEvent) {
        if (!this.dragging) {
            if (this.selectedCoef != -1) {
                this.selectedCoef = -1;
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
        this.dragging = false;
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
            final CheckboxMenuItem checkboxMenuItem = (CheckboxMenuItem)itemEvent.getItemSelectable();
            if (checkboxMenuItem != this.extendedZonesItem && checkboxMenuItem != this.reducedZonesItem && checkboxMenuItem != this.repeatedZonesItem) {
                this.handleResize();
            }
            this.cv.repaint(this.pause);
        }
        this.doRadio(this.waveFunctionMenu, itemEvent);
        this.doRadio(this.zoneMenu, itemEvent);
    }
    
    void doRadio(final Menu menu, final ItemEvent itemEvent) {
        for (int i = 0; i != menu.countItems(); ++i) {
            if (itemEvent.getItemSelectable() == menu.getItem(i)) {
                ((CheckboxMenuItem)menu.getItem(i)).setState(true);
                for (int j = 0; j != menu.countItems(); ++j) {
                    if (i != j) {
                        ((CheckboxMenuItem)menu.getItem(j)).setState(false);
                    }
                }
            }
        }
    }
    
    void doSetup() {
        this.doBlank();
        for (int i = 0; i != this.potSampleCount; ++i) {
            this.pot[i] = 0.0;
        }
        this.setup = this.setupList.elementAt(this.setupChooser.getSelectedIndex());
        this.aux1Bar.setValue(100);
        this.aux2Bar.setValue(100);
        this.aux3Bar.setValue(100);
        this.aux4Bar.setValue(100);
        this.selectGround = true;
        this.setup.select();
        this.setup.drawPotential();
        this.getPotTrans();
        this.setupModified = false;
        this.levelsChanged = true;
        if (this.setup.getAuxBarCount() >= 2) {
            this.aux2Label.show();
            this.aux2Bar.show();
        }
        else {
            this.aux2Label.hide();
            this.aux2Bar.hide();
        }
        if (this.setup.getAuxBarCount() >= 3) {
            this.aux3Label.show();
            this.aux3Bar.show();
        }
        else {
            this.aux3Label.hide();
            this.aux3Bar.hide();
        }
        if (this.setup.getAuxBarCount() >= 4) {
            this.aux4Label.show();
            this.aux4Bar.show();
        }
        else {
            this.aux4Label.hide();
            this.aux4Bar.hide();
        }
        this.validate();
        this.selectedCoef = -1;
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
    
    class FiniteWellSetup extends Setup
    {
        String getName() {
            return "Finite Wells";
        }
        
        void select() {
            Quantum1DCrystalFrame.this.aux1Label.setText("Well Width");
            Quantum1DCrystalFrame.this.aux1Bar.setValue(98);
            Quantum1DCrystalFrame.this.aux2Label.setText("Well Depth");
        }
        
        int getWidth() {
            return (Quantum1DCrystalFrame.this.potSampleCount - 1) * Quantum1DCrystalFrame.this.aux1Bar.getValue() / 100;
        }
        
        double getTop() {
            return -1.0 + (Quantum1DCrystalFrame.this.aux2Bar.getValue() - 1) / 49.5;
        }
        
        void drawPotential() {
            final int width = this.getWidth();
            final double top = this.getTop();
            int i;
            for (i = 0; i != width; ++i) {
                Quantum1DCrystalFrame.this.pot[i] = -1.0;
            }
            while (i < Quantum1DCrystalFrame.this.potSampleCount) {
                Quantum1DCrystalFrame.this.pot[i] = top;
                ++i;
            }
        }
        
        Setup createNext() {
            return new FiniteWellPairSetup();
        }
    }
    
    class FiniteWellPairSetup extends Setup
    {
        String getName() {
            return "Well Pairs";
        }
        
        void select() {
            Quantum1DCrystalFrame.this.aux1Label.setText("Well Width");
            Quantum1DCrystalFrame.this.aux2Bar.setValue(80);
            Quantum1DCrystalFrame.this.aux2Label.setText("Well Separation");
            Quantum1DCrystalFrame.this.aux2Bar.setValue(100);
            Quantum1DCrystalFrame.this.aux3Label.setText("Well Depth 1");
            Quantum1DCrystalFrame.this.aux3Bar.setValue(100);
            Quantum1DCrystalFrame.this.aux4Label.setText("Well Depth 2");
            Quantum1DCrystalFrame.this.aux4Bar.setValue(90);
        }
        
        int getAuxBarCount() {
            return 4;
        }
        
        int getWidth() {
            return Quantum1DCrystalFrame.this.aux1Bar.getValue() * 3 / 4 * (Quantum1DCrystalFrame.this.potSampleCount / 2) / 110 + 1;
        }
        
        void drawPotential() {
            final int width = this.getWidth();
            final double n = Quantum1DCrystalFrame.this.aux2Bar.getValue() / 100.0;
            final int n2 = (int)((1 + width) * (1.0 - n) + Quantum1DCrystalFrame.this.potSampleCount / 2 * n);
            final double n3 = 1.0 - Quantum1DCrystalFrame.this.aux3Bar.getValue() / 50.0;
            final double n4 = 1.0 - Quantum1DCrystalFrame.this.aux4Bar.getValue() / 50.0;
            final double n5 = 1.0;
            final double n6 = (n3 < n4) ? (n3 + 1.0) : (n4 + 1.0);
            final double n7 = n5 - n6;
            final double n8 = n3 - n6;
            final double n9 = n4 - n6;
            for (int i = 0; i != Quantum1DCrystalFrame.this.potSampleCount; ++i) {
                Quantum1DCrystalFrame.this.pot[i] = n7;
            }
            for (int j = 0; j != width; ++j) {
                Quantum1DCrystalFrame.this.pot[j] = n8;
                Quantum1DCrystalFrame.this.pot[j + n2] = n9;
            }
        }
        
        Setup createNext() {
            return new FiniteWellPairCoupledSetup();
        }
    }
    
    class FiniteWellPairCoupledSetup extends Setup
    {
        String getName() {
            return "Coupled Well Pairs";
        }
        
        void select() {
            Quantum1DCrystalFrame.this.aux1Label.setText("Well Separation");
            Quantum1DCrystalFrame.this.aux1Bar.setValue(1);
            Quantum1DCrystalFrame.this.aux2Label.setText("Wall Potential");
            Quantum1DCrystalFrame.this.aux2Bar.setValue(50);
        }
        
        int getWidth() {
            return Quantum1DCrystalFrame.this.potSampleCount / 4;
        }
        
        double getWallEnergy() {
            return -1.0 + (Quantum1DCrystalFrame.this.aux2Bar.getValue() - 1) / 50.0;
        }
        
        void drawPotential() {
            final int width = this.getWidth();
            final double n = Quantum1DCrystalFrame.this.aux1Bar.getValue() / 100.0;
            final int n2 = (int)((1 + width) * (1.0 - n) + Quantum1DCrystalFrame.this.potSampleCount / 2 * n);
            double n3 = -1.0 + (Quantum1DCrystalFrame.this.aux2Bar.getValue() - 1) / 49.0;
            if (n3 > 1.0) {
                n3 = 1.0;
            }
            for (int i = 0; i != Quantum1DCrystalFrame.this.potSampleCount; ++i) {
                Quantum1DCrystalFrame.this.pot[i] = 1.0;
            }
            for (int j = 0; j != width; ++j) {
                Quantum1DCrystalFrame.this.pot[j] = (Quantum1DCrystalFrame.this.pot[j + n2] = -1.0);
            }
            for (int k = width; k != n2; ++k) {
                Quantum1DCrystalFrame.this.pot[k] = n3;
            }
        }
        
        Setup createNext() {
            return new HarmonicWellSetup();
        }
    }
    
    class HarmonicWellSetup extends Setup
    {
        String getName() {
            return "Harmonic";
        }
        
        void select() {
            Quantum1DCrystalFrame.this.aux1Label.setText("Well Width");
            Quantum1DCrystalFrame.this.aux2Label.setText("Well Depth");
        }
        
        int getAuxBarCount() {
            return 2;
        }
        
        double getFloor() {
            return 1.0 - Quantum1DCrystalFrame.this.aux2Bar.getValue() / 50.0;
        }
        
        double getTop() {
            return -1.0 + Quantum1DCrystalFrame.this.aux2Bar.getValue() / 50.0;
        }
        
        void drawPotential() {
            final double top = this.getTop();
            final int n = Quantum1DCrystalFrame.this.aux1Bar.getValue() * (Quantum1DCrystalFrame.this.potSampleCount - 1) / 100;
            final double n2 = (top + 1.0) / (n / 2 * (n / 2.0));
            int i;
            for (i = 0; i != n; ++i) {
                final double n3 = i - n / 2.0;
                Quantum1DCrystalFrame.this.pot[i] = -1.0 + n3 * n3 * n2;
                if (Quantum1DCrystalFrame.this.pot[i] > top) {
                    Quantum1DCrystalFrame.this.pot[i] = top;
                }
            }
            while (i < Quantum1DCrystalFrame.this.potSampleCount) {
                Quantum1DCrystalFrame.this.pot[i] = top;
                ++i;
            }
        }
        
        Setup createNext() {
            return new CoulombWellArraySetup();
        }
    }
    
    class CoulombWellArraySetup extends Setup
    {
        String getName() {
            return "Coulomb-Like";
        }
        
        void select() {
            Quantum1DCrystalFrame.this.aux1Label.setText("Well Width");
            Quantum1DCrystalFrame.this.aux1Bar.setValue(40);
        }
        
        int getAuxBarCount() {
            return 1;
        }
        
        void drawPotential() {
            final double n = Quantum1DCrystalFrame.this.aux1Bar.getValue() / 200.0;
            final int potSampleCount = Quantum1DCrystalFrame.this.potSampleCount;
            final double n2 = n * (potSampleCount / 2);
            final double n3 = -2 * potSampleCount * n2 / (2.0 * n2 - potSampleCount);
            final double n4 = 1.0 + 2.0 * n3 / potSampleCount;
            final double n5 = n4 + (1.0 - (n4 - 2.0 * n3 / (potSampleCount / 2.0)));
            for (int i = 0; i != Quantum1DCrystalFrame.this.potSampleCount; ++i) {
                final double n6 = i - potSampleCount / 2.0;
                Quantum1DCrystalFrame.this.pot[i] = n5 - n3 / Math.abs(n6) - n3 / (potSampleCount - Math.abs(n6));
                if (Quantum1DCrystalFrame.this.pot[i] < -1.0) {
                    Quantum1DCrystalFrame.this.pot[i] = -1.0;
                }
            }
        }
        
        Setup createNext() {
            return new FreeParticleSetup();
        }
    }
    
    class FreeParticleSetup extends Setup
    {
        String getName() {
            return "Free Particle";
        }
        
        void select() {
        }
        
        int getAuxBarCount() {
            return 0;
        }
        
        void drawPotential() {
            for (int i = 0; i != Quantum1DCrystalFrame.this.potSampleCount; ++i) {
                Quantum1DCrystalFrame.this.pot[i] = -1.0;
            }
        }
        
        Setup createNext() {
            return new SinusoidalLatticeSetup();
        }
    }
    
    class SinusoidalLatticeSetup extends Setup
    {
        String getName() {
            return "Sinusoidal";
        }
        
        void select() {
            Quantum1DCrystalFrame.this.aux1Label.setText("Well Depth");
        }
        
        int getAuxBarCount() {
            return 1;
        }
        
        void drawPotential() {
            final double n = Quantum1DCrystalFrame.this.aux1Bar.getValue() / 100.0;
            final int n2 = Quantum1DCrystalFrame.this.potSampleCount / 10;
            for (int i = 0; i != Quantum1DCrystalFrame.this.potSampleCount; ++i) {
                Quantum1DCrystalFrame.this.pot[i] = -1.0 + n * (1.0 - Math.cos(i * 2 * 3.141592653589793 / Quantum1DCrystalFrame.this.potSampleCount));
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
        int handle;
        double ymult;
        double ymult2;
        double scale;
        int cellw2;
        
        void drawLabel(final Graphics graphics, final String s) {
            graphics.setColor(Color.white);
            Quantum1DCrystalFrame.this.centerString(graphics, s, super.y - 5);
        }
    }
}
