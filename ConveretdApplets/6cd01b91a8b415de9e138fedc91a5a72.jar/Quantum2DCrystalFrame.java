import java.awt.Rectangle;
import java.awt.event.ItemEvent;
import org.netlib.lapack.Dsyevd;
import org.netlib.util.intW;
import java.awt.Event;
import java.awt.event.ActionEvent;
import java.awt.event.ComponentEvent;
import java.awt.event.MouseEvent;
import java.awt.Point;
import java.awt.image.ImageObserver;
import java.awt.Cursor;
import java.awt.image.DataBufferInt;
import java.awt.image.BufferedImage;
import java.awt.Color;
import java.awt.MenuBar;
import java.awt.Component;
import java.awt.LayoutManager;
import java.awt.Graphics;
import java.text.NumberFormat;
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

class Quantum2DCrystalFrame extends Frame implements ComponentListener, ActionListener, MouseMotionListener, MouseListener, ItemListener, DecentScrollbarListener
{
    Thread engine;
    Dimension winSize;
    Image dbimage;
    Random random;
    int stateCount;
    int elevelCount;
    int maxStateCount;
    int sampleCountX;
    int sampleCountY;
    int sampleCountXY;
    int cellSampleCount;
    int potSampleCount;
    int pSampleCount;
    int dispCount;
    double[][] modes;
    double[][] modesLeft;
    double[][][] dispersion;
    double expecte;
    double selectedKX;
    double selectedKY;
    double[] potTrans;
    double[] blochTrans;
    double equipStep;
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
    CheckboxMenuItem recipCheckItem;
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
    DecentScrollbar contourCountBar;
    DecentScrollbar aspectBar;
    DecentScrollbar angleBar;
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
    View viewXMap;
    View viewRecip;
    View viewRecipMap;
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
    double[] pdata;
    double[] pdatar;
    double[] pdatai;
    double[] pot;
    double[] pot1d;
    double mass;
    double escale;
    double axisx;
    double axisy;
    double axisr1x;
    double axisr1y;
    double axisr2x;
    double axisr2y;
    int recipSizeX;
    int recipSizeY;
    double recipWidth;
    double recipHeight;
    int selectedCoef;
    int selectedPaneHandle;
    int kUpdateStateX;
    int kUpdateStateY;
    int kUpdateSkip;
    static final int stateBuffer = 5;
    static final int SEL_NONE = 0;
    static final int SEL_POTENTIAL = 1;
    static final int SEL_X = 2;
    static final int SEL_P = 3;
    static final int SEL_STATES = 4;
    static final int SEL_HANDLE = 5;
    static final int SEL_DISPERSION = 6;
    static final int SEL_RECIP = 7;
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
    PhaseColor[][] phaseColors;
    PhaseColor whitePhaseColor;
    static final int phaseColorCount = 50;
    Quantum2DCrystalCanvas cv;
    Quantum2DCrystal applet;
    NumberFormat showFormat;
    boolean shown;
    long lastTime;
    Graphics recipg;
    View recipv;
    short[] recipLevelMap;
    boolean expectEdge;
    int stateColSize;
    int stateSize;
    
    public String getAppletInfo() {
        return "Quantum2DCrystal by Paul Falstad";
    }
    
    int getrand(final int n) {
        int nextInt = this.random.nextInt();
        if (nextInt < 0) {
            nextInt = -nextInt;
        }
        return nextInt % n;
    }
    
    Quantum2DCrystalFrame(final Quantum2DCrystal applet) {
        super("2-d Quantum Crystal Applet v1.0b");
        this.engine = null;
        this.maxStateCount = 500;
        this.sampleCountX = 320;
        this.sampleCountY = 64;
        this.cellSampleCount = 32;
        this.potSampleCount = 64;
        this.recipWidth = 3.0;
        this.recipHeight = 2.0;
        this.shown = false;
        this.expectEdge = false;
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
        this.setLayout(new Quantum2DCrystalLayout());
        (this.cv = new Quantum2DCrystalCanvas(this)).addComponentListener(this);
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
        menu2.add(this.xCheckItem = this.getCheckItem("Position"));
        this.xCheckItem.setState(true);
        menu2.add(this.dispersionCheckItem = this.getCheckItem("Dispersion"));
        this.dispersionCheckItem.setState(true);
        menu2.add(this.recipCheckItem = this.getCheckItem("Reciprocal Lattice"));
        this.recipCheckItem.setState(true);
        this.setMenuBar(menuBar);
        this.setupChooser = new Choice();
        for (int i = 0; i != this.setupList.size(); ++i) {
            this.setupChooser.add("Potential: " + ((Setup)this.setupList.elementAt(i)).getName());
        }
        this.setup = this.setupList.elementAt(0);
        this.setupChooser.addItemListener(this);
        this.add(this.setupChooser);
        (this.mouseChooser = new Choice()).add("Mouse = Set Eigenstate");
        this.mouseChooser.add("Mouse = Edit Function");
        this.mouseChooser.addItemListener(this);
        this.mouseChooser.select(0);
        this.add(this.groundButton = new Button("Ground State"));
        this.groundButton.addActionListener(this);
        (this.stoppedCheck = new Checkbox("Stopped")).addItemListener(this);
        this.add(this.stoppedCheck);
        this.add(new Label("Simulation Speed", 1));
        this.add(this.speedBar = new DecentScrollbar(this, 20, 1, 150));
        this.add(new Label("Particle Mass", 1));
        this.add(this.massBar = new DecentScrollbar(this, 100, 10, 500));
        this.add(new Label("# of Wells Shown", 1));
        this.add(this.wellCountBar = new DecentScrollbar(this, 5, 1, 50));
        this.add(new Label("Energy Scale", 1));
        this.add(this.energyScaleBar = new DecentScrollbar(this, 50, 1, 100));
        this.add(new Label("Contour Count", 1));
        this.add(this.contourCountBar = new DecentScrollbar(this, 20, 1, 100));
        new Label("Angle", 1);
        this.angleBar = new DecentScrollbar(this, 0, 0, 100);
        this.add(new Label("Cell Height", 1));
        this.add(this.aspectBar = new DecentScrollbar(this, 40, 40, 100));
        this.add(this.aux1Label = new Label("Aux 1", 1));
        this.add(this.aux1Bar = new DecentScrollbar(this, 50, 1, 100));
        this.add(this.aux2Label = new Label("Aux 2", 1));
        this.add(this.aux2Bar = new DecentScrollbar(this, 50, 1, 100));
        this.add(this.aux3Label = new Label("Aux 3", 1));
        this.add(this.aux3Bar = new DecentScrollbar(this, 50, 1, 100));
        this.add(this.aux4Label = new Label("Aux 4", 1));
        this.add(this.aux4Bar = new DecentScrollbar(this, 50, 1, 100));
        this.add(new Label("http://www.falstad.com"));
        try {
            final String parameter = this.applet.getParameter("PAUSE");
            if (parameter != null) {
                this.pause = Integer.parseInt(parameter);
            }
        }
        catch (Exception ex) {}
        this.dispmax = new double[this.maxStateCount];
        this.setResolution();
        this.phaseColors = new PhaseColor[8][51];
        for (int j = 0; j != 8; ++j) {
            for (int k = 0; k <= 50; ++k) {
                this.phaseColors[j][k] = this.genPhaseColor(j, Math.atan(k / 50.0));
            }
        }
        this.whitePhaseColor = new PhaseColor(1.0, 1.0, 1.0);
        this.random = new Random();
        this.reinit();
        this.cv.setBackground(Color.black);
        this.cv.setForeground(Color.lightGray);
        (this.showFormat = NumberFormat.getInstance()).setMaximumFractionDigits(2);
        this.resize(800, 640);
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
        final int n = 20;
        int n2 = (this.viewPotential == null) ? 0 : (this.viewPotential.height + n);
        int n3 = (this.viewDispersion == null) ? 0 : (this.viewDispersion.height + n);
        final View view = null;
        this.viewRecip = view;
        this.viewBloch = view;
        this.viewPotential = view;
        this.viewDispersion = view;
        this.viewP = view;
        this.viewX = view;
        this.viewList = new View[20];
        int viewCount = 0;
        if (this.eCheckItem.getState()) {
            this.viewList[viewCount++] = (this.viewPotential = new View());
        }
        if (this.xCheckItem.getState()) {
            this.viewList[viewCount++] = (this.viewX = new View());
        }
        if (this.dispersionCheckItem.getState()) {
            this.viewList[viewCount++] = (this.viewDispersion = new View());
        }
        if (this.recipCheckItem.getState()) {
            this.viewList[viewCount++] = (this.viewRecip = new View());
        }
        this.viewCount = viewCount;
        int n4 = this.viewCount;
        int n5 = this.winSize.height;
        if (n2 > 0 && this.viewPotential != null) {
            --n4;
            n5 -= n2;
        }
        if (n3 > 0 && this.viewDispersion != null) {
            --n4;
            n5 -= n3;
        }
        if (n4 == 0 || n5 <= 0) {
            n5 = this.winSize.height;
            n4 = this.viewCount;
            n3 = (n2 = 0);
        }
        int handle = 0;
        for (int i = 0; i != this.viewCount; ++i) {
            final View view2 = this.viewList[i];
            int n6 = n5 / n4;
            if (view2 == this.viewPotential && n2 > 0) {
                n6 = n2;
            }
            else if (view2 == this.viewDispersion && n3 > 0) {
                n6 = n3;
            }
            view2.x = 0;
            view2.width = this.winSize.width;
            view2.y = handle + n;
            view2.height = n6 - n;
            view2.handle = handle;
            handle += n6;
        }
        this.setGraphLines();
        this.dbimage = this.createImage(dimension.width, dimension.height);
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
        try {
            final BufferedImage memimage = new BufferedImage(view.width, view.height, 1);
            view.memimage = memimage;
            view.pixels = ((DataBufferInt)memimage.getRaster().getDataBuffer()).getData();
        }
        catch (Exception ex) {
            System.out.println("BufferedImage failed " + width + " " + height);
        }
    }
    
    void setGraphLines() {
        this.setAspect();
        for (int i = 0; i != this.viewCount; ++i) {
            final View view = this.viewList[i];
            view.mid_y = view.y + view.height / 2;
            view.ymult = 0.9 * view.height / 2.0;
            view.lower_y = (int)(view.mid_y + view.ymult);
            view.ymult2 = view.ymult * 2.0;
        }
        if (this.viewX != null) {
            this.processMap(this.viewXMap = new View(this.viewX), this.sampleCountX / this.cellSampleCount / (this.sampleCountY / this.cellSampleCount * this.axisy));
        }
        if (this.viewRecip != null) {
            this.viewRecipMap = new View(this.viewRecip);
            final int n = 3;
            this.recipSizeY = n;
            this.recipSizeX = n;
            if (this.aspectBar.getValue() == 40) {
                this.recipSizeY = 2;
            }
            this.recipWidth = this.axisr1x * this.recipSizeX;
            this.recipHeight = this.axisr2y * this.recipSizeY;
            this.processMap(this.viewRecipMap, this.recipWidth / this.recipHeight);
        }
    }
    
    void doGround() {
        this.select(0.0, 0.0, 0);
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
    
    public void updateQuantum2DCrystal(final Graphics graphics) {
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
                this.getBands(true, this.selectedKX, this.selectedKY, this.selectedBand);
            }
            this.calcLevels();
            this.cv.setCursor(null);
            final boolean b = false;
            this.stateChanged = b;
            this.levelsChanged = b;
        }
        else {
            final long currentTimeMillis2 = System.currentTimeMillis();
            while (this.updateK() && System.currentTimeMillis() < currentTimeMillis2 + 100L) {}
            this.calcLevels();
        }
        int n4 = -1;
        if (this.viewPotential != null) {
            final int mid_y = this.viewPotential.mid_y;
            final double ymult = this.viewPotential.ymult;
            graphics2.setColor(Color.gray);
            for (int j = 0; j < this.elevelCount; j += 2) {
                graphics2.setColor(Color.darkGray);
                final double n5 = this.elevels[j];
                int energyY = this.getEnergyY(this.elevels[j + 1], this.viewPotential);
                final int energyY2 = this.getEnergyY(n5, this.viewPotential);
                if (j == this.elevelCount - 2) {
                    energyY = 0;
                }
                int n6 = energyY2 - energyY;
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
                final int energyY3 = this.getEnergyY(this.pot1d[k & this.potSampleCount - 1], this.viewPotential);
                if (n4 != -1) {
                    graphics2.drawLine(n4, n3, n8, energyY3);
                }
                n4 = n8;
                n3 = energyY3;
            }
            final int energyY4 = this.getEnergyY(this.expecte, this.viewPotential);
            graphics2.setColor(Color.red);
            graphics2.drawLine(0, energyY4, this.winSize.width, energyY4);
        }
        graphics2.setColor(Color.white);
        final double n9 = (this.expecte + 1.0) * n;
        final double cos = Math.cos(n9);
        final double n10 = -Math.sin(n9);
        for (int l = 0; l != this.sampleCountXY; ++l) {
            final double n11 = this.func[l];
            final double n12 = this.funci[l];
            this.func[l] = n11 * cos - n12 * n10;
            this.funci[l] = n11 * n10 + n12 * cos;
        }
        if (this.viewX != null) {
            this.viewX.drawLabel(graphics2, "Wave Function (Position)");
            final int mid_y2 = this.viewX.mid_y;
            final double ymult2 = this.viewX.ymult;
            this.updateMapView(graphics2, this.viewXMap, this.func, this.funci, this.sampleCountX, this.sampleCountY, 1.0E-8);
            graphics2.setColor(Color.darkGray);
            final int n13 = this.sampleCountX / this.cellSampleCount;
            final int n14 = this.sampleCountY / this.cellSampleCount;
            final double n15 = this.viewXMap.width / n13;
            final double n16 = this.viewXMap.height / n14;
            for (int n17 = 0; n17 != n13; ++n17) {
                for (int n18 = 0; n18 != n14; ++n18) {
                    this.setup.draw(graphics2, this.viewXMap.x + (int)(n15 * n17), this.viewXMap.y + (int)(n16 * n18), (int)n15, (int)n16);
                }
            }
        }
        if (this.viewDispersion != null) {
            this.viewDispersion.drawLabel(graphics2, "Dispersion");
            graphics2.setColor(Color.gray);
            final int mid_y3 = this.viewDispersion.mid_y;
            final double ymult3 = this.viewDispersion.ymult;
            final double n19 = 0.0;
            final int n20 = this.viewDispersion.y + 5;
            for (int n21 = 0; n21 < this.elevelCount; n21 += 2) {
                graphics2.setColor(Color.darkGray);
                final double n22 = this.elevels[n21] - n19;
                int energyY5 = this.getEnergyY(this.elevels[n21 + 1] - n19, this.viewDispersion);
                final int energyY6 = this.getEnergyY(n22, this.viewDispersion);
                if (energyY6 >= n20) {
                    if (energyY5 < n20 || n21 == this.elevelCount - 2) {
                        energyY5 = n20;
                    }
                    int n23 = energyY6 - energyY5;
                    if (n23 <= 0) {
                        n23 = 1;
                    }
                    graphics2.fillRect(0, energyY5, this.winSize.width, n23);
                }
            }
            graphics2.setColor(Color.white);
            this.viewDispersion.cellw2 = (this.viewDispersion.width - 3) / 5;
            final int n24 = this.dispersion[0][0].length - 1;
            this.drawDispersion(graphics2, 0, n24, 0, -1, 0);
            this.drawDispersion(graphics2, 1, 0, 0, 1, 0);
            this.drawDispersion(graphics2, 2, n24, 0, 0, 1);
            this.drawDispersion(graphics2, 3, n24, n24, -1, -1);
            this.drawDispersion(graphics2, 4, 0, 0, 0, 1);
            final int energyY7 = this.getEnergyY(this.expecte - n19, this.viewDispersion);
            graphics2.setColor(Color.red);
            if (energyY7 >= this.viewDispersion.y) {
                graphics2.drawLine(0, energyY7, this.winSize.width, energyY7);
            }
        }
        if (this.viewRecip != null) {
            this.viewRecip.drawLabel(graphics2, "Energy Contours in K Space");
            this.drawRecip(graphics2, this.viewRecip);
        }
        graphics.drawImage(this.dbimage, 0, 0, this);
        if (!this.stoppedCheck.getState()) {
            this.cv.repaint(this.pause);
        }
    }
    
    void drawRecipDot(final Graphics graphics, final Color color, final double n, final double n2) {
        graphics.setColor(color);
        final int n3 = (int)(n * this.viewRecipMap.width / this.recipWidth);
        final int n4 = (int)(-n2 * this.axisr2y * this.viewRecipMap.height / this.recipHeight);
        final int n5 = n3 + this.viewRecipMap.x;
        final int n6 = n4 + this.viewRecipMap.y;
        final int n7 = n5 + this.viewRecipMap.width / 2;
        final int n8 = n6 + this.viewRecipMap.height / 2;
        final int n9 = 7;
        graphics.fillOval(n7 - n9 / 2, n8 - n9 / 2, n9, n9);
    }
    
    void updateMapView(final Graphics graphics, final View view, final double[] array, final double[] array2, final int n, final int n2, final double n3) {
        graphics.setColor(Color.white);
        graphics.drawRect(view.x - 1, view.y - 1, view.width + 2, view.height + 2);
        final double n4 = 0.0;
        Math.sqrt(n4);
        final double scale = 1.0 / n4;
        view.scale *= 1.1;
        view.scale = scale;
        if (view.scale > 1.0E8) {
            view.scale = 1.0E8;
        }
        final int n5 = n + 1;
        final int n6 = n2 + 1;
        int n7;
        for (int i = n7 = 0; i < n2; ++i) {
            for (int j = 0; j < n; ++j, ++n7) {
                final double n8 = array[n7];
                final double n9 = array2[n7];
                double n10 = (n8 * n8 + n9 * n9) * (255.0 * view.scale * n3);
                final PhaseColor phaseColor = this.getPhaseColor(n8, n9);
                if (n10 > 255.0) {
                    n10 = 255.0;
                }
                final int n11 = 0xFF000000 | (int)(phaseColor.r * n10) << 16 | (int)(phaseColor.g * n10) << 8 | (int)(phaseColor.b * n10);
                final int n12 = j * view.width / n5;
                final int n13 = i * view.height / n6;
                final int n14 = (j + 1) * view.width / n5;
                final int n15 = (i + 1) * view.height / n6;
                for (int n16 = n12 + n13 * view.width, k = 0; k != n14 - n12; ++k, ++n16) {
                    for (int l = 0; l != n15 - n13; ++l) {
                        view.pixels[n16 + l * view.width] = n11;
                    }
                }
            }
        }
        graphics.drawImage(view.memimage, view.x, view.y, null);
    }
    
    int getEnergyY(double n, final View view) {
        final double n2 = this.elevels[1] + 1.0;
        this.escale = 2.0 / (n2 + (this.elevels[this.elevels.length - 1] + 1.0 - n2) * this.energyScaleBar.getValue() / 100.0);
        n = (n + 1.0) * this.escale - 1.0;
        return view.mid_y - (int)(view.ymult * n);
    }
    
    void drawDispersion(final Graphics graphics, final int n, final int n2, final int n3, final int n4, final int n5) {
        final double n6 = 0.0;
        final int cellw2 = this.viewDispersion.cellw2;
        final int n7 = this.viewDispersion.y + 5;
        final int length = this.dispersion[0][0].length;
        int n8 = cellw2 * (n + 1);
        switch (n) {
            case 0: {
                graphics.setColor(Color.gray);
                break;
            }
            case 1: {
                graphics.setColor(Color.magenta);
                break;
            }
            case 2: {
                graphics.setColor(Color.cyan);
                break;
            }
            case 3: {
                graphics.setColor(Color.gray);
                break;
            }
            case 4: {
                graphics.setColor(Color.blue);
                --n8;
                break;
            }
        }
        graphics.drawLine(n8, this.viewDispersion.y, n8, this.viewDispersion.y + this.viewDispersion.height - 1);
        graphics.setColor(Color.white);
        for (int i = 0; i != this.dispersion.length; ++i) {
            int n10;
            int n9 = n10 = -1;
            int n11 = n2;
            int n12 = n3;
            for (int j = 0; j != length; ++j) {
                final int n13 = n * cellw2;
                final int n14 = j * cellw2 / (length - 1);
                final int energyY = this.getEnergyY(this.dispersion[i][n11][n12] - n6, this.viewDispersion);
                if (n10 != -1 && energyY > n7 && n9 > n7) {
                    graphics.drawLine(n13 + n10, n9, n13 + n14, energyY);
                }
                n10 = n14;
                n9 = energyY;
                n11 += n4;
                n12 += n5;
            }
        }
    }
    
    void drawRecip(final Graphics recipg, final View recipv) {
        this.recipg = recipg;
        this.recipv = recipv;
        this.equipStep = 3.0 / this.contourCountBar.getValue() * (this.elevels[4] - this.elevels[0]);
        this.renderEquips();
    }
    
    int getExtendedKLevel(final double n, final double n2) {
        int n3 = 0;
        for (int i = -3; i <= 3; ++i) {
            for (int j = -3; j <= 3; ++j) {
                if (i != 0 || j != 0) {
                    final double n4 = this.axisr1x * i + this.axisr2x * j;
                    final double n5 = this.axisr1y * i + this.axisr2y * j;
                    if ((n * n4 + n2 * n5) / (n4 * n4 + n5 * n5) > 0.5) {
                        ++n3;
                    }
                }
            }
        }
        return n3;
    }
    
    void renderEquips() {
        for (int i = 0; i != this.viewRecipMap.width * this.viewRecipMap.height; ++i) {
            this.viewRecipMap.pixels[i] = -16777216;
        }
        final double n = this.dispersion[0][0][0];
        final double n2 = this.dispersion[3][0][0];
        final int n3 = this.viewRecipMap.width * this.viewRecipMap.height;
        if (this.recipLevelMap == null || this.recipLevelMap.length != n3) {
            this.recipLevelMap = new short[n3];
            int n4 = 0;
            for (int j = 0; j != this.viewRecipMap.width; ++j) {
                for (int k = 0; k != this.viewRecipMap.height; ++k) {
                    double n5 = this.recipWidth * j / this.viewRecipMap.width - this.recipWidth * 0.5;
                    double n6 = this.recipHeight * 0.5 - this.recipHeight * k / this.viewRecipMap.height;
                    if (n5 < 0.0) {
                        n5 = -n5;
                    }
                    if (n6 < 0.0) {
                        n6 = -n6;
                    }
                    this.recipLevelMap[n4++] = (short)this.getExtendedKLevel(n5, n6);
                }
            }
        }
        int n7 = 0;
        double n8 = 1.0E8;
        double n9 = 0.0;
        double n10 = 0.0;
        final double selectedKX = this.selectedKX;
        final double selectedKY = this.selectedKY;
        for (int l = 0; l != this.viewRecipMap.width; ++l) {
            for (int n11 = 0; n11 != this.viewRecipMap.height; ++n11) {
                double n12 = this.recipWidth * l / this.viewRecipMap.width - this.recipWidth * 0.5;
                double n13 = this.recipHeight * 0.5 - this.recipHeight * n11 / this.viewRecipMap.height;
                final short n14 = this.recipLevelMap[n7++];
                if (n14 <= 5) {
                    final double n15 = n12;
                    final double n16 = n13;
                    while (n12 > 0.5) {
                        --n12;
                    }
                    while (n12 < -0.5) {
                        ++n12;
                    }
                    while (n13 > this.axisr2y * 0.5) {
                        n13 -= this.axisr2y;
                    }
                    while (n13 < this.axisr2y * -0.5) {
                        n13 += this.axisr2y;
                    }
                    double n17 = n13 / this.axisr2y;
                    final double n18 = selectedKX - n12;
                    final double n19 = selectedKY - n17;
                    if (n14 == this.selectedBand) {
                        final double n20 = n18 * n18 + n19 * n19;
                        if (n20 < n8) {
                            n8 = n20;
                            n9 = n15;
                            n10 = n16 / this.axisr2y;
                        }
                    }
                    if (n12 < 0.0) {
                        n12 = -n12;
                    }
                    if (n17 < 0.0) {
                        n17 = -n17;
                    }
                    final int n21 = (int)(n12 * 2.0 * (this.dispCount - 2));
                    final int n22 = (int)(n17 * 2.0 * (this.dispCount - 2));
                    int n23 = (int)(255.0 * ((this.dispersion[n14][n21][n22] - n) / (n2 - n)));
                    if (n23 < 0) {
                        n23 = 0;
                    }
                    if (n23 > 511) {
                        n23 = 511;
                    }
                    int n24;
                    if (n23 < 256) {
                        n24 = (0xFF000000 | 256 * n23);
                    }
                    else {
                        n24 = (0xFF00FF00 | 65536 * (n23 - 256));
                    }
                    if (this.selectedBand == n14) {
                        n24 |= ((n14 == 0) ? 96 : 160);
                    }
                    if (this.tryEdge(n14, n21, n22, n21 + 1, n22 + 1)) {
                        n24 = (this.expectEdge ? -65536 : -1);
                    }
                    this.viewRecipMap.pixels[l + n11 * this.viewRecipMap.width] = n24;
                }
            }
        }
        this.recipg.drawImage(this.viewRecipMap.memimage, this.viewRecipMap.x, this.viewRecipMap.y, null);
        this.drawBraggPlanes(this.recipg, this.viewRecipMap);
        this.drawRecipDot(this.recipg, Color.gray, 0.0, 0.0);
        this.drawRecipDot(this.recipg, Color.magenta, 0.5, 0.0);
        this.drawRecipDot(this.recipg, Color.blue, 0.0, 0.5);
        this.drawRecipDot(this.recipg, Color.cyan, 0.5, 0.5);
        if (n8 < 1.0E8) {
            this.drawRecipDot(this.recipg, Color.red, n9, n10);
        }
    }
    
    double sign(final double n) {
        return (n < 0.0) ? -1.0 : 1.0;
    }
    
    void drawBraggPlanes(final Graphics graphics, final View view) {
        final int n = view.width / this.recipSizeX / 2;
        final int n2 = view.height / this.recipSizeY / 2;
        final int n3 = view.x + view.width / 2;
        final int n4 = view.y + view.height / 2;
        graphics.setColor(Color.black);
        for (int i = -3; i <= 3; ++i) {
            for (int j = -3; j <= 3; ++j) {
                final double n5 = view.x + (i * 0.5 + this.recipWidth * 0.5) * this.viewRecipMap.width / this.recipWidth;
                final double n6 = view.y + (this.recipHeight * 0.5 + j * 0.5 * this.axisr2y) * this.viewRecipMap.height / this.recipHeight;
                final double n7 = -j * n2;
                final double n8 = i * n;
                final double[] array = new double[4];
                final int[] array2 = new int[4];
                array[0] = (view.x - n5) / n7;
                array[1] = (view.x + view.width - n5) / n7;
                array[2] = (view.y - n6) / n8;
                array[3] = (view.y + view.height - n6) / n8;
                int n9 = 0;
                for (int k = 0; k != 4; ++k) {
                    if (k >= 2 || view.contains(view.x, (int)(n6 + n8 * array[k]))) {
                        if (k < 2 || view.contains((int)(n5 + n7 * array[k]), view.y)) {
                            if (n9 == 4) {
                                break;
                            }
                            array2[n9++] = (int)(n5 + n7 * array[k]);
                            array2[n9++] = (int)(n6 + n8 * array[k]);
                        }
                    }
                }
                if (n9 == 4) {
                    graphics.drawLine(array2[0], array2[1], array2[2], array2[3]);
                }
            }
        }
    }
    
    void interpPoint(final double n, final double n2, final int n3, final int n4, final int n5, final int n6, final double n7, final Point point) {
        final double n8 = (n7 - n) / (n2 - n);
        final double n9 = 1.0 - n8;
        final double n10 = this.recipv.width / (2 * this.dispCount);
        final double n11 = this.recipv.height / (2 * this.dispCount);
        point.x = (int)((n3 + 0.5) * n10 * n9 + (n5 + 0.5) * n10 * n8);
        point.y = (int)((n4 + 0.5) * n11 * n9 + (n6 + 0.5) * n11 * n8);
    }
    
    boolean spanning(final double n, final double n2, final double n3) {
        return n != n2 && (n >= n3 || n2 >= n3) && (n <= n3 || n2 <= n3);
    }
    
    int min(final int n, final int n2) {
        return (n < n2) ? n : n2;
    }
    
    int max(final int n, final int n2) {
        return (n > n2) ? n : n2;
    }
    
    double min(final double n, final double n2) {
        return (n < n2) ? n : n2;
    }
    
    double max(final double n, final double n2) {
        return (n > n2) ? n : n2;
    }
    
    boolean tryEdge(final int n, final int n2, final int n3, final int n4, final int n5) {
        final double equipStep = this.equipStep;
        final double n6 = this.dispersion[n][n2][n3];
        final double n7 = this.dispersion[n][n4][n5];
        final double n8 = this.dispersion[n][n2][n5];
        final double n9 = this.dispersion[n][n4][n3];
        final double min = this.min(this.min(n6, n7), this.min(n8, n9));
        final double max = this.max(this.max(n6, n7), this.max(n8, n9));
        final int n10 = (int)(min / equipStep);
        final int n11 = (int)(max / equipStep);
        final int n12 = this.recipv.x + this.recipv.width / 2;
        final int n13 = this.recipv.y + this.recipv.height / 2;
        this.expectEdge = false;
        if (this.spanning(min, max, this.expecte)) {
            return this.expectEdge = true;
        }
        return n10 != n11;
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
        return 1.0;
    }
    
    double convertEnergy(double n, final boolean b) {
        final double n2 = 0.023502;
        if (b) {
            n += this.setup.getBaseEnergy();
        }
        return n * n2 / 0.0018801;
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
            case 7: {
                this.editRecip(x, y);
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
        this.cv.repaint(this.pause);
    }
    
    void editDispersion(final int n, final int n2) {
        final double n3 = n % this.viewDispersion.cellw2 / (2.0 * this.viewDispersion.cellw2);
        final int n4 = n / this.viewDispersion.cellw2;
        double n5 = 0.0;
        double n6 = 0.0;
        switch (n4) {
            case 0: {
                n5 = -0.5 + n3;
                break;
            }
            case 1: {
                n5 = n3;
                break;
            }
            case 2: {
                n5 = 0.5;
                n6 = n3;
                break;
            }
            case 3: {
                n6 = (n5 = 0.5 - n3);
                break;
            }
            case 4: {
                n6 = n3;
                break;
            }
        }
        final int n7 = this.dispersion[0][0].length - 1;
        int n8 = (int)(n5 * 2.0 * n7);
        if (n8 < 0) {
            n8 = -n8;
        }
        final int n9 = (int)(n6 * 2.0 * n7);
        double n10 = 1000.0;
        int n11 = 0;
        for (int i = 0; i != this.dispersion.length; ++i) {
            final double n12 = Math.abs(n2 - this.getEnergyY(this.dispersion[i][n8][n9], this.viewDispersion));
            if (n12 < n10) {
                n10 = n12;
                n11 = i;
            }
        }
        this.select(n5, n6, n11);
    }
    
    void editRecip(int n, int n2) {
        n -= this.viewRecipMap.x;
        n2 -= this.viewRecipMap.y;
        n -= this.viewRecipMap.width / 2;
        n2 -= this.viewRecipMap.height / 2;
        double n3 = n * this.recipWidth / this.viewRecipMap.width;
        final double n4 = -n2 * this.recipHeight / this.viewRecipMap.height;
        final int extendedKLevel = this.getExtendedKLevel(n3, n4);
        if (extendedKLevel > 5) {
            return;
        }
        while (n3 > 0.5) {
            --n3;
        }
        while (n3 < -0.5) {
            ++n3;
        }
        double n5;
        for (n5 = n4 / this.axisr2y; n5 > 0.5; --n5) {}
        while (n5 < -0.5) {
            ++n5;
        }
        this.select(n3, n5, extendedKLevel);
    }
    
    void select(final double selectedKX, final double selectedKY, final int selectedBand) {
        this.selectedKX = selectedKX;
        this.selectedKY = selectedKY;
        this.selectedBand = selectedBand;
        this.stateChanged = true;
        this.cv.repaint(this.pause);
    }
    
    void getPotTrans() {
        this.potTrans = new double[this.potSampleCount * this.potSampleCount * 2];
        for (int i = 0; i != this.potSampleCount * this.potSampleCount; ++i) {
            this.potTrans[i * 2] = this.pot[i];
        }
        final int[] array = new int[2];
        array[0] = (array[1] = this.potSampleCount);
        this.ndfft(this.potTrans, array, 2, 1);
        this.pot1d = new double[this.potSampleCount];
        for (int j = 0; j != this.potSampleCount; ++j) {
            double n = this.pot[j];
            for (int k = 1; k != this.potSampleCount; ++k) {
                if (this.pot[j + k * this.potSampleCount] < n) {
                    n = this.pot[j + k * this.potSampleCount];
                }
            }
            this.pot1d[j] = n;
        }
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
        if (decentScrollbar == this.massBar || decentScrollbar == this.aspectBar || decentScrollbar == this.angleBar) {
            this.levelsChanged = true;
        }
        if (decentScrollbar == this.aspectBar) {
            this.setGraphLines();
        }
        if (decentScrollbar == this.aux1Bar || decentScrollbar == this.aux2Bar || decentScrollbar == this.aux3Bar || decentScrollbar == this.aux4Bar || decentScrollbar == this.aspectBar) {
            this.setup.setPotential();
            this.getPotTrans();
            this.levelsChanged = true;
        }
        if (decentScrollbar == this.wellCountBar) {
            this.setResolution();
            this.setup.setPotential();
            this.getPotTrans();
            this.setGraphLines();
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
        while (this.cellSampleCount < 700 / value && this.cellSampleCount < 256) {
            this.cellSampleCount *= 2;
        }
        this.sampleCountX = this.cellSampleCount * value;
        this.sampleCountY = this.cellSampleCount * 3;
        this.sampleCountXY = this.sampleCountX * this.sampleCountY;
        this.func = new double[this.sampleCountXY];
        this.funci = new double[this.sampleCountXY];
        this.pot = new double[this.potSampleCount * this.potSampleCount];
        this.stateChanged = true;
    }
    
    void setAspect() {
        final double axisx = this.angleBar.getValue() / 100.0;
        final double axisy = this.aspectBar.getValue() / 40.0;
        this.axisx = axisx;
        this.axisy = axisy;
        this.axisr1x = 1.0;
        this.axisr1y = -this.axisx / this.axisy;
        this.axisr2x = 0.0;
        this.axisr2y = 1.0 / this.axisy;
    }
    
    void genStates() {
        this.levelsChanged = false;
        this.setAspect();
        if (this.potTrans == null) {
            this.getPotTrans();
        }
        final double[] bands = this.getBands(false, 0.0, 0.0, 0);
        final double[] bands2 = this.getBands(false, 0.5, 0.0, 0);
        final double[] bands3 = this.getBands(false, 0.0, 0.5, 0);
        final double[] bands4 = this.getBands(false, 0.5, 0.5, 0);
        this.elevelCount = bands.length * 2;
        this.elevels = new double[this.elevelCount];
        this.stateCount = this.elevelCount;
        this.dispCount = 65;
        final int length = bands.length;
        this.dispersion = new double[length][this.dispCount][this.dispCount];
        for (int i = 0; i != length; ++i) {
            this.dispersion[i][0][0] = bands[i];
            this.dispersion[i][this.dispCount - 1][0] = bands2[i];
            this.dispersion[i][0][this.dispCount - 1] = bands3[i];
            this.dispersion[i][this.dispCount - 1][this.dispCount - 1] = bands4[i];
            for (int j = 0; j < this.dispCount; ++j) {
                for (int k = 0; k < this.dispCount; ++k) {
                    if (j == 0 || j == this.dispCount - 1) {
                        if (k == 0) {
                            continue;
                        }
                        if (k == this.dispCount - 1) {
                            continue;
                        }
                    }
                    final double n = j / (this.dispCount - 1.0);
                    final double n2 = k / (this.dispCount - 1.0);
                    this.dispersion[i][j][k] = bands[i] * (1.0 - n) * (1.0 - n2) + bands2[i] * n * (1.0 - n2) + bands3[i] * (1.0 - n) * n2 + bands4[i] * n * n2;
                }
            }
        }
        this.kUpdateSkip = this.dispCount / 2;
        this.kUpdateStateX = this.kUpdateSkip;
        this.kUpdateStateY = 0;
        this.getBands(true, this.selectedKX, this.selectedKY, this.selectedBand);
    }
    
    void calcLevels() {
        for (int i = 0; i != this.dispersion.length; ++i) {
            double n = 1.0E8;
            double n2 = -1.0E8;
            for (int j = 0; j != this.dispersion[0].length; ++j) {
                for (int k = 0; k != this.dispersion[0].length; ++k) {
                    final double n3 = this.dispersion[i][j][k];
                    if (n3 > n2) {
                        n2 = n3;
                    }
                    if (n3 < n) {
                        n = n3;
                    }
                }
            }
            this.elevels[i * 2] = n;
            this.elevels[i * 2 + 1] = n2;
        }
    }
    
    boolean updateK() {
        if (this.kUpdateSkip <= 1) {
            return false;
        }
        final double[] bands = this.getBands(false, this.kUpdateStateX / (2.0 * (this.dispCount - 1)), this.kUpdateStateY / (2.0 * (this.dispCount - 1)), 0);
        for (int i = 0; i != bands.length; ++i) {
            this.dispersion[i][this.kUpdateStateX][this.kUpdateStateY] = bands[i];
            final int n = this.kUpdateSkip / 2;
            if (this.kUpdateStateX > 0 && this.kUpdateStateY > 0) {
                final int kUpdateStateX = this.kUpdateStateX;
                final int n2 = this.kUpdateStateX - this.kUpdateSkip;
                final int kUpdateStateY = this.kUpdateStateY;
                final int n3 = this.kUpdateStateY - this.kUpdateSkip;
                for (int j = 0; j <= this.kUpdateSkip; ++j) {
                    for (int k = 0; k <= this.kUpdateSkip; ++k) {
                        if (j == 0 || j == this.dispCount - 1) {
                            if (k == 0) {
                                continue;
                            }
                            if (k == this.dispCount - 1) {
                                continue;
                            }
                        }
                        final double n4 = j / this.kUpdateSkip;
                        final double n5 = k / this.kUpdateSkip;
                        this.dispersion[i][this.kUpdateStateX - j][this.kUpdateStateY - k] = this.dispersion[i][kUpdateStateX][kUpdateStateY] * (1.0 - n4) * (1.0 - n5) + this.dispersion[i][n2][kUpdateStateY] * n4 * (1.0 - n5) + this.dispersion[i][kUpdateStateX][n3] * (1.0 - n4) * n5 + this.dispersion[i][n2][n3] * n4 * n5;
                    }
                }
            }
        }
        final int n6 = this.kUpdateSkip * 2;
        final boolean b = this.kUpdateStateY % n6 == 0;
        this.kUpdateStateX += (b ? n6 : this.kUpdateSkip);
        if (this.kUpdateStateX >= this.dispCount) {
            this.kUpdateStateX = (b ? 0 : this.kUpdateSkip);
            this.kUpdateStateY += this.kUpdateSkip;
            if (this.kUpdateStateY >= this.dispCount) {
                this.kUpdateSkip /= 2;
                if (this.kUpdateSkip == 1) {
                    return false;
                }
                this.kUpdateStateX = this.kUpdateSkip;
                this.kUpdateStateY = 0;
            }
        }
        return true;
    }
    
    int getFourierIndexX(final int n, final int n2) {
        final int n3 = n % n2;
        return (n3 > n2 / 2) ? (n2 / 2 - n3) : n3;
    }
    
    int getFourierIndexY(final int n, final int n2) {
        final int n3 = n / n2;
        return (n3 > n2 / 2) ? (n2 / 2 - n3) : n3;
    }
    
    double[] getBands(final boolean b, double n, final double n2, final int n3) {
        final int n4 = 7;
        final int n5 = n4 * n4;
        final int n6 = n5 * 2;
        final double[] array = new double[n6 * n6];
        this.mass = this.massBar.getValue() * 2.0E-4;
        final double n7 = 1.0 / this.mass;
        this.mass *= 1596875.0;
        final double n8 = 1.0 / (this.potSampleCount * this.potSampleCount);
        n = -n;
        for (int i = 0; i != n5; ++i) {
            final int fourierIndexX = this.getFourierIndexX(i, n4);
            final int fourierIndexY = this.getFourierIndexY(i, n4);
            final double n9 = n + fourierIndexX;
            final double n10 = (n2 + fourierIndexY) / this.axisy - this.axisx * (n + fourierIndexX) / this.axisy;
            array[i + n5 + n6 * (i + n5)] = (array[i + n6 * i] = n7 * (n9 * n9 + n10 * n10) * 0.01);
            for (int j = 0; j != n5; ++j) {
                final int fourierIndexX2 = this.getFourierIndexX(j, n4);
                final int fourierIndexY2 = this.getFourierIndexY(j, n4);
                int n11 = fourierIndexX2 - fourierIndexX;
                if (n11 < 0) {
                    n11 += this.potSampleCount;
                }
                int n12 = fourierIndexY2 - fourierIndexY;
                if (n12 < 0) {
                    n12 += this.potSampleCount;
                }
                final int n13 = (n11 + n12 * this.potSampleCount) * 2;
                final double n14 = this.potTrans[n13] * n8;
                final double n15 = this.potTrans[n13 + 1] * n8;
                final double[] array2 = array;
                final int n16 = i + n6 * j;
                array2[n16] += n14;
                final double[] array3 = array;
                final int n17 = i + n5 + n6 * (j + n5);
                array3[n17] += n14;
                final double[] array4 = array;
                final int n18 = i + n5 + j * n6;
                array4[n18] -= n15;
                final double[] array5 = array;
                final int n19 = i + n6 * (j + n5);
                array5[n19] += n15;
            }
        }
        final double[] array6 = new double[1 + 5 * n6 + 5 * n6 * n6];
        final int[] array7 = new int[3 + 5 * n6];
        final intW intW = new intW(0);
        final double[] array8 = new double[n6];
        Dsyevd.dsyevd(b ? "V" : "N", "U", n6, array, 0, n6, array8, 0, array6, 0, array6.length, array7, 0, array7.length, intW);
        final double[] array9 = new double[n6];
        for (int k = 0; k != n6; ++k) {
            array9[k] = array8[k];
        }
        for (int l = 1; l < n6; ++l) {
            final double n20 = array9[l];
            int n21 = l;
            while (array9[n21 - 1] > n20) {
                array9[n21] = array9[n21 - 1];
                if (--n21 <= 0) {
                    break;
                }
            }
            array9[n21] = n20;
        }
        final double[] array10 = new double[20];
        for (int n22 = 0; n22 != 20; ++n22) {
            array10[n22] = array9[n22 * 2];
        }
        if (!b) {
            return array10;
        }
        int n23 = 0;
        for (int n24 = 0; n24 != this.stateCount; ++n24) {
            int n25;
            for (n25 = 0; n25 != n5 && array9[n24] != array8[n25]; ++n25) {}
            if (n25 == n5) {
                System.out.print("can't find elevels! " + n24 + " " + array9[n24] + "\n");
            }
            else {
                array8[n25] = -1.0;
                if (n23++ >= n3 * 2) {
                    final double[] array11 = new double[this.cellSampleCount * this.cellSampleCount * 2];
                    final int n26 = n6 * n25;
                    final int n27 = (array[n26 + 1] < 0.0) ? -1 : 1;
                    for (int n28 = 0; n28 != n5; ++n28) {
                        int fourierIndexX3 = this.getFourierIndexX(n28, n4);
                        int fourierIndexY3 = this.getFourierIndexY(n28, n4);
                        if (Math.abs(fourierIndexX3) < this.cellSampleCount / 2) {
                            if (Math.abs(fourierIndexY3) < this.cellSampleCount / 2) {
                                if (fourierIndexX3 < 0) {
                                    fourierIndexX3 += this.cellSampleCount;
                                }
                                if (fourierIndexY3 < 0) {
                                    fourierIndexY3 += this.cellSampleCount;
                                }
                                final int n29 = (fourierIndexX3 + fourierIndexY3 * this.cellSampleCount) * 2;
                                array11[n29] = n27 * array[n28 + n26];
                                array11[n29 + 1] = n27 * array[n28 + n5 + n26];
                            }
                        }
                    }
                    final int[] array12 = new int[2];
                    array12[0] = (array12[1] = this.cellSampleCount);
                    this.ndfft(array11, array12, 2, -1);
                    final double n30 = 6.283185307179586 * n / this.cellSampleCount;
                    final double n31 = 6.283185307179586 * n2 / this.cellSampleCount;
                    int n34;
                    int n33;
                    for (int n32 = n33 = (n34 = 0); n33 != this.sampleCountY; ++n33, ++n34) {
                        int n35 = 0;
                        int n36 = 0;
                        if (n34 == this.cellSampleCount) {
                            n34 = 0;
                        }
                        int n37 = n34 * this.cellSampleCount * 2;
                        final double n38 = n33 * n31;
                        double cos = Math.cos(n38);
                        double n39 = -Math.sin(n38);
                        final double cos2 = Math.cos(n30);
                        final double n40 = -Math.sin(n30);
                        while (n36 != this.sampleCountX) {
                            if (n35 == this.cellSampleCount) {
                                n35 = 0;
                                n37 -= this.cellSampleCount * 2;
                            }
                            final double n41 = array11[n37];
                            final double n42 = array11[n37 + 1];
                            this.func[n32] = n41 * cos - n42 * n39;
                            this.funci[n32] = n41 * n39 + n42 * cos;
                            final double n43 = cos * cos2 - n39 * n40;
                            n39 = cos * n40 + n39 * cos2;
                            cos = n43;
                            ++n36;
                            ++n35;
                            ++n32;
                            n37 += 2;
                        }
                    }
                    this.expecte = array9[n3 * 2];
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
        else if (this.viewRecip != null && this.viewRecip.contains(x, y)) {
            this.selection = 7;
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
                final double abs = Math.abs(n - (this.getEnergyY(this.dispersion[i][j][0], this.viewPotential) - this.dispersion[i][j][0] * 1.0E-8));
                if (abs < n3) {
                    n3 = abs;
                    n2 = i;
                    n4 = j * 0.5 / (this.dispersion[0].length - 1);
                }
            }
        }
        this.select(n4, 0.0, n2);
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
        this.setup.setPotential();
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
    
    void ndfft(final double[] array, final int[] array2, final int n, final int n2) {
        int n3 = 1;
        int n4 = 1;
        final double n5 = n2 * 2 * 3.141592653589793;
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
                            final double n14 = array[n12];
                            final double n15 = array[n12 + 1];
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
                final double n19 = n5 / (n18 / n8);
                double n20 = 1.0;
                double n21 = 0.0;
                final double sin = Math.sin(0.5 * n19);
                final double n22 = sin * (sin * -2.0);
                final double sin2 = Math.sin(n19);
                for (int n23 = 0; n23 < n17; n23 += n8) {
                    for (int n24 = n23; n24 < n23 + n8; n24 += 2) {
                        for (int n25 = n24; n25 < n10; n25 += n18) {
                            final int n26 = n25 + 1;
                            final int n27 = n25 + n17;
                            final int n28 = n27 + 1;
                            final double n29 = n20 * array[n27] - n21 * array[n28];
                            final double n30 = n20 * array[n28] + n21 * array[n27];
                            array[n27] = array[n25] - n29;
                            array[n28] = array[n26] - n30;
                            final int n31 = n25;
                            array[n31] += n29;
                            final int n32 = n26;
                            array[n32] += n30;
                        }
                    }
                    final double n33 = n20;
                    n20 += n20 * n22 - n21 * sin2;
                    n21 += n21 * n22 + n33 * sin2;
                }
            }
            n4 *= n6;
        }
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
        
        void draw(final Graphics graphics, final int n, final int n2, final int n3, final int n4) {
        }
        
        abstract void setPotential();
        
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
            return "Rectangular Wells";
        }
        
        void select() {
            Quantum2DCrystalFrame.this.aux1Label.setText("Well Width");
            Quantum2DCrystalFrame.this.aux1Bar.setValue(98);
            Quantum2DCrystalFrame.this.aux2Label.setText("Well Height");
            Quantum2DCrystalFrame.this.aux2Bar.setValue(98);
            Quantum2DCrystalFrame.this.aux3Label.setText("Well Depth");
        }
        
        int getAuxBarCount() {
            return 3;
        }
        
        int getWidth() {
            return (Quantum2DCrystalFrame.this.potSampleCount - 1) * Quantum2DCrystalFrame.this.aux1Bar.getValue() / 100;
        }
        
        int getHeight() {
            return (Quantum2DCrystalFrame.this.potSampleCount - 1) * Quantum2DCrystalFrame.this.aux2Bar.getValue() / 100;
        }
        
        double getTop() {
            return -1.0 + (Quantum2DCrystalFrame.this.aux3Bar.getValue() - 1) / 49.5;
        }
        
        void setPotential() {
            final int width = this.getWidth();
            final int height = this.getHeight();
            final double top = this.getTop();
            for (int i = 0; i != Quantum2DCrystalFrame.this.potSampleCount; ++i) {
                for (int j = 0; j != Quantum2DCrystalFrame.this.potSampleCount; ++j) {
                    Quantum2DCrystalFrame.this.pot[i + j * Quantum2DCrystalFrame.this.potSampleCount] = top;
                }
            }
            for (int k = 0; k != width; ++k) {
                for (int l = 0; l != height; ++l) {
                    Quantum2DCrystalFrame.this.pot[k + l * Quantum2DCrystalFrame.this.potSampleCount] = -1.0;
                }
            }
        }
        
        void draw(final Graphics graphics, final int n, final int n2, final int n3, final int n4) {
            graphics.drawRect(n, n2, this.getWidth() * n3 / Quantum2DCrystalFrame.this.potSampleCount, this.getHeight() * n4 / Quantum2DCrystalFrame.this.potSampleCount);
        }
        
        Setup createNext() {
            return new CircularWellSetup();
        }
    }
    
    class CircularWellSetup extends Setup
    {
        String getName() {
            return "Circular Wells";
        }
        
        void select() {
            Quantum2DCrystalFrame.this.aux1Label.setText("Well Width");
            Quantum2DCrystalFrame.this.aux1Bar.setValue(98);
            Quantum2DCrystalFrame.this.aux2Label.setText("Well Depth");
            Quantum2DCrystalFrame.this.aux2Bar.setValue(40);
        }
        
        int getWidth() {
            return (Quantum2DCrystalFrame.this.potSampleCount - 1) * Quantum2DCrystalFrame.this.aux1Bar.getValue() / 100;
        }
        
        double getTop() {
            return -1.0 + (Quantum2DCrystalFrame.this.aux2Bar.getValue() - 1) / 49.5;
        }
        
        void setPotential() {
            final int n = this.getWidth() / 2;
            final double top = this.getTop();
            for (int i = 0; i != Quantum2DCrystalFrame.this.potSampleCount; ++i) {
                for (int j = 0; j != Quantum2DCrystalFrame.this.potSampleCount; ++j) {
                    final int n2 = i - Quantum2DCrystalFrame.this.potSampleCount / 2;
                    final double n3 = (j - Quantum2DCrystalFrame.this.potSampleCount / 2) * Quantum2DCrystalFrame.this.axisy;
                    Quantum2DCrystalFrame.this.pot[i + j * Quantum2DCrystalFrame.this.potSampleCount] = ((n2 * n2 + n3 * n3 <= n * n) ? -1.0 : top);
                }
            }
        }
        
        void draw(final Graphics graphics, final int n, final int n2, final int n3, final int n4) {
            final int n5 = this.getWidth() * n3 / Quantum2DCrystalFrame.this.potSampleCount;
            graphics.drawOval(n + (n3 - n5) / 2, n2 + (n4 - n5) / 2, n5, n5);
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
            Quantum2DCrystalFrame.this.aux1Label.setText("Well Width");
            Quantum2DCrystalFrame.this.aux2Label.setText("Well Height");
            Quantum2DCrystalFrame.this.aux3Label.setText("Well Depth");
        }
        
        int getAuxBarCount() {
            return 3;
        }
        
        double getFloor() {
            return 1.0 - Quantum2DCrystalFrame.this.aux3Bar.getValue() / 50.0;
        }
        
        double getTop() {
            return -1.0 + Quantum2DCrystalFrame.this.aux3Bar.getValue() / 50.0;
        }
        
        int getWidth() {
            return Quantum2DCrystalFrame.this.aux1Bar.getValue() * (Quantum2DCrystalFrame.this.potSampleCount - 1) / 100;
        }
        
        int getHeight() {
            return Quantum2DCrystalFrame.this.aux2Bar.getValue() * (Quantum2DCrystalFrame.this.potSampleCount - 1) / 100;
        }
        
        void setPotential() {
            final double top = this.getTop();
            final int width = this.getWidth();
            final int height = this.getHeight();
            final double n = (top + 1.0) / (width / 2 * (width / 2.0));
            for (int i = 0; i < Quantum2DCrystalFrame.this.potSampleCount * Quantum2DCrystalFrame.this.potSampleCount; ++i) {
                Quantum2DCrystalFrame.this.pot[i] = top;
            }
            for (int j = 0; j != width; ++j) {
                for (int k = 0; k != height; ++k) {
                    final double n2 = j - width / 2.0;
                    final double n3 = k - height / 2.0;
                    final int n4 = j + k * Quantum2DCrystalFrame.this.potSampleCount;
                    Quantum2DCrystalFrame.this.pot[n4] = -1.0 + n2 * n2 * n + n3 * n3 * n;
                    if (Quantum2DCrystalFrame.this.pot[n4] > top) {
                        Quantum2DCrystalFrame.this.pot[n4] = top;
                    }
                }
            }
        }
        
        void draw(final Graphics graphics, final int n, final int n2, final int n3, final int n4) {
            graphics.drawOval(n, n2, this.getWidth() * n3 / Quantum2DCrystalFrame.this.potSampleCount, this.getHeight() * n4 / Quantum2DCrystalFrame.this.potSampleCount);
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
        
        void setPotential() {
            for (int i = 0; i != Quantum2DCrystalFrame.this.potSampleCount * Quantum2DCrystalFrame.this.potSampleCount; ++i) {
                Quantum2DCrystalFrame.this.pot[i] = -1.0;
            }
        }
        
        Setup createNext() {
            return null;
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
        int mid_y;
        int lower_y;
        int handle;
        double ymult;
        double ymult2;
        double scale;
        int cellw2;
        Image memimage;
        int[] pixels;
        
        View() {
        }
        
        View(final View view) {
            super(view);
        }
        
        void drawLabel(final Graphics graphics, final String s) {
            graphics.setColor(Color.white);
            Quantum2DCrystalFrame.this.centerString(graphics, s, this.y - 5);
        }
    }
}
