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

class EMWave1Frame extends Frame implements ComponentListener, ActionListener, AdjustmentListener, MouseMotionListener, MouseListener, ItemListener
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
    Scrollbar EBrightnessBar;
    Scrollbar lineDensityBar;
    Scrollbar auxBar;
    Label auxLabel;
    double forceTimeZero;
    double sourceMult;
    static final double pi = 3.141592653589793;
    OscElement[] grid;
    int gw;
    OscSource[] sources;
    static final int MODE_PERF_CONDUCTOR = 0;
    static final int MODE_M_POS = 1;
    static final int MODE_M_NEG = 2;
    static final int MODE_CLEAR = 3;
    static final int VIEW_E = 0;
    static final int VIEW_E_LINES = 1;
    static final int VIEW_B = 2;
    static final int VIEW_Q = 3;
    static final int VIEW_J = 4;
    static final int VIEW_E_B = 5;
    static final int VIEW_E_LINES_B = 6;
    static final int VIEW_E_B_Q_J = 7;
    static final int VIEW_E_LINES_B_Q_J = 8;
    static final int VIEW_E_Q = 9;
    static final int VIEW_E_LINES_Q = 10;
    static final int VIEW_E_B_J = 11;
    static final int VIEW_E_LINES_B_J = 12;
    static final int VIEW_POYNTING = 13;
    static final int VIEW_ENERGY = 14;
    static final int VIEW_POYNTING_ENERGY = 15;
    static final int VIEW_DISP_CUR = 16;
    static final int VIEW_DISP_J = 17;
    static final int VIEW_DISP_J_B = 18;
    static final int VIEW_DB_DT = 19;
    static final int VIEW_NONE = -1;
    static final int TYPE_CONDUCTOR = 1;
    static final int TYPE_CURRENT = 2;
    static final int TYPE_NONE = 0;
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
    int sourceType;
    int auxFunction;
    boolean sourcePacket;
    static final int SRC_NONE = 0;
    static final int SRC_1PLANE = 1;
    static final int SRC_2PLANE = 2;
    static final int SRC_1PLANE_PACKET = 3;
    static final int SRC_1ANTENNA = 4;
    static final int SRC_2ANTENNA = 5;
    static final int SRC_1LOOP = 6;
    static final int SRC_1LOOP_PACKET = 7;
    static final int SRC_PLANE = 1;
    static final int SRC_ANTENNA = 2;
    static final int SRC_LOOP = 3;
    EMWave1Canvas cv;
    EMWave1 applet;
    boolean useBufferedImage;
    long lastTime;
    byte[] linegrid;
    int filterCount;
    
    public String getAppletInfo() {
        return "EMWave1 by Paul Falstad";
    }
    
    int getrand(final int n) {
        int nextInt = this.random.nextInt();
        if (nextInt < 0) {
            nextInt = -nextInt;
        }
        return nextInt % n;
    }
    
    EMWave1Frame(final EMWave1 applet) {
        super("TE Electrodynamics Applet v1.4");
        this.engine = null;
        this.windowWidth = 50;
        this.windowHeight = 50;
        this.windowOffsetX = 0;
        this.windowOffsetY = 0;
        this.sourceCount = -1;
        this.sourcePacket = false;
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
        Setup next = new PlaneWaveSetup();
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
        final int n2 = 40;
        this.sources = new OscSource[4];
        this.setLayout(new EMWave1Layout());
        (this.cv = new EMWave1Canvas(this)).addComponentListener(this);
        this.cv.addMouseMotionListener(this);
        this.cv.addMouseListener(this);
        this.add(this.cv);
        this.setupChooser = new Choice();
        for (int i = 0; i != this.setupList.size(); ++i) {
            this.setupChooser.add("Setup: " + ((Setup)this.setupList.elementAt(i)).getName());
        }
        this.setupChooser.select(4);
        this.setup = this.setupList.elementAt(4);
        this.setupChooser.addItemListener(this);
        this.add(this.setupChooser);
        (this.sourceChooser = new Choice()).add("No Sources");
        this.sourceChooser.add("1 Plane Src");
        this.sourceChooser.add("2 Plane Srcs");
        this.sourceChooser.add("1 Plane Src (Packets)");
        this.sourceChooser.add("1 Antenna Src");
        this.sourceChooser.add("2 Antenna Srcs");
        this.sourceChooser.add("1 Loop Src");
        this.sourceChooser.add("1 Loop Src (Packets)");
        this.sourceChooser.select(1);
        this.sourceChooser.addItemListener(this);
        this.add(this.sourceChooser);
        (this.modeChooser = new Choice()).add("Mouse = Add Perf. Conductor");
        this.modeChooser.add("Mouse = Clear");
        this.modeChooser.addItemListener(this);
        this.add(this.modeChooser);
        (this.viewChooser = new Choice()).add("Show Electric Field (E)");
        this.viewChooser.add("Show E lines");
        this.viewChooser.add("Show Magnetic Field (B)");
        this.viewChooser.add("Show Charge (rho)");
        this.viewChooser.add("Show Current (j)");
        this.viewChooser.add("Show E/B");
        this.viewChooser.add("Show E lines/B");
        this.viewChooser.add("Show E/B/rho/j");
        this.viewChooser.add("Show E lines/B/rho/j");
        this.viewChooser.add("Show E/rho");
        this.viewChooser.add("Show E lines/rho");
        this.viewChooser.add("Show E/B/j");
        this.viewChooser.add("Show E lines/B/j");
        this.viewChooser.add("Show Poynting Vector");
        this.viewChooser.add("Show Energy Density");
        this.viewChooser.add("Show Poynting/Energy");
        this.viewChooser.add("Show Disp Current");
        this.viewChooser.add("Show Disp + j");
        this.viewChooser.add("Show Disp + j/B");
        this.viewChooser.add("Show dB/dt");
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
        this.add(this.resBar = new Scrollbar(0, n2, 5, 16, 140));
        this.resBar.addAdjustmentListener(this);
        this.setResolution();
        this.add(new Label("Source Frequency", 1));
        this.add(this.forceBar = new Scrollbar(0, this.forceBarValue = 10, 1, 1, 40));
        this.forceBar.addAdjustmentListener(this);
        this.add(new Label("Brightness", 1));
        this.add(this.brightnessBar = new Scrollbar(0, 10, 1, 1, 2000));
        this.brightnessBar.addAdjustmentListener(this);
        this.add(new Label("E Field Brightness", 1));
        this.add(this.EBrightnessBar = new Scrollbar(0, 100, 1, 1, 800));
        this.EBrightnessBar.addAdjustmentListener(this);
        this.add(new Label("Line Density", 1));
        this.add(this.lineDensityBar = new Scrollbar(0, 50, 1, 10, 100));
        this.lineDensityBar.addAdjustmentListener(this);
        this.add(this.auxLabel = new Label("", 1));
        this.add(this.auxBar = new Scrollbar(0, 1, 1, 1, 40));
        this.auxBar.addAdjustmentListener(this);
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
        this.grid = new OscElement[this.gridSizeXY];
        for (int i = 0; i != this.gridSizeXY; ++i) {
            this.grid[i] = new OscElement();
        }
        this.doSetup();
    }
    
    void setDamping() {
        for (int i = 0; i != this.gridSizeXY; ++i) {
            this.grid[i].damp = 1.0;
        }
        for (int j = 0; j != this.windowOffsetX; ++j) {
            for (int k = 0; k != this.gridSizeX; ++k) {
                final double exp = Math.exp(-(this.windowOffsetX - j) * 0.0022);
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
                this.pixels = (int[])forName2.getMethod("getData", (Class[])null).invoke(forName3.getMethod("getDataBuffer", (Class<?>[])null).invoke(forName.getMethod("getRaster", (Class[])null).invoke(this.dbimage, (Object[])null), (Object[])null), (Object[])null);
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
        for (int i = 0; i != this.gridSizeXY; ++i) {
            final OscElement oscElement = this.grid[i];
            final OscElement oscElement2 = this.grid[i];
            final double n = 0.0;
            oscElement2.dazdt = n;
            oscElement.az = n;
        }
        this.t = 0.0;
        this.doFilter();
    }
    
    void doClearAll() {
        for (int i = 0; i != this.gridSizeXY; ++i) {
            final OscElement oscElement = this.grid[i];
            final OscElement oscElement2 = this.grid[i];
            final double n = 0.0;
            oscElement2.jy = n;
            oscElement.jx = n;
            final OscElement oscElement3 = this.grid[i];
            final OscElement oscElement4 = this.grid[i];
            final double n2 = 0.0;
            oscElement4.dazdt = n2;
            oscElement3.az = n2;
            this.grid[i].boundary = false;
            this.grid[i].gray = false;
            this.grid[i].conductor = false;
            this.grid[i].col = 0;
        }
        this.setDamping();
        this.sourceChooser.select(0);
        this.setSources();
    }
    
    void calcBoundaries() {
        int n = 0;
        for (int i = 0; i < this.gridSizeX; ++i) {
            for (int j = 0; j < this.windowOffsetY; ++j) {
                this.grid[i + j * this.gw].conductor = this.grid[i + this.windowOffsetY * this.gw].conductor;
                this.grid[i + this.gw * (this.gridSizeY - j - 1)].conductor = this.grid[i + this.gw * (this.gridSizeY - this.windowOffsetY - 1)].conductor;
            }
        }
        for (int k = 0; k < this.gridSizeY; ++k) {
            for (int l = 0; l < this.windowOffsetX; ++l) {
                this.grid[l + this.gw * k].conductor = this.grid[this.windowOffsetX + this.gw * k].conductor;
                this.grid[this.gridSizeX - l - 1 + this.gw * k].conductor = this.grid[this.gridSizeX - this.windowOffsetX - 1 + this.gw * k].conductor;
            }
        }
        for (int n2 = 1; n2 < this.gridSizeX - 1; ++n2) {
            for (int n3 = 1; n3 < this.gridSizeY - 1; ++n3) {
                final int n4 = n2 + this.gw * n3;
                final OscElement oscElement = this.grid[n4];
                final boolean conductor = oscElement.conductor;
                final OscElement oscElement2 = this.grid[n4 - 1];
                final OscElement oscElement3 = this.grid[n4 + 1];
                final OscElement oscElement4 = this.grid[n4 - this.gw];
                final OscElement oscElement5 = this.grid[n4 + this.gw];
                oscElement.gray = oscElement.conductor;
                if (oscElement2.conductor != conductor || oscElement3.conductor != conductor || oscElement4.conductor != conductor || oscElement5.conductor != conductor) {
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
    
    public void updateEMWave1(final Graphics graphics) {
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
                final double n6 = n * n;
                final double n7 = 1.0;
                for (int i = 1; i != n4; ++i) {
                    int j = i * this.gw + 1;
                    final int n8 = j + n3 - 1;
                    OscElement oscElement = this.grid[j - 1];
                    OscElement oscElement2 = this.grid[j];
                    while (j != n8) {
                        final OscElement oscElement3 = oscElement;
                        oscElement = oscElement2;
                        oscElement2 = this.grid[j + 1];
                        if (!oscElement.conductor) {
                            final OscElement oscElement4 = this.grid[j - this.gw];
                            final OscElement oscElement5 = this.grid[j + this.gw];
                            double n13;
                            if (oscElement.boundary) {
                                final double az = oscElement.az;
                                double n9 = oscElement3.az - az;
                                if (oscElement3.conductor) {
                                    n9 = (oscElement2.conductor ? 0.0 : (oscElement2.az - az));
                                }
                                double n10 = oscElement2.az - az;
                                if (oscElement2.conductor) {
                                    n10 = (oscElement3.conductor ? 0.0 : (oscElement3.az - az));
                                }
                                double n11 = oscElement4.az - az;
                                if (oscElement4.conductor) {
                                    n11 = (oscElement5.conductor ? 0.0 : (oscElement5.az - az));
                                }
                                double n12 = oscElement5.az - az;
                                if (oscElement5.conductor) {
                                    n12 = (oscElement4.conductor ? 0.0 : (oscElement4.az - az));
                                }
                                n13 = (n10 + n9 + n12 + n11) * 0.25 + (oscElement5.jx - oscElement4.jx + oscElement3.jy - oscElement2.jy);
                            }
                            else {
                                n13 = oscElement5.jx - oscElement4.jx + oscElement3.jy - oscElement2.jy - (oscElement.az - (oscElement2.az + oscElement3.az + oscElement5.az + oscElement4.az) * 0.25);
                            }
                            final double dazdt = oscElement.dazdt;
                            oscElement.dazdt = oscElement.dazdt * oscElement.damp + n13 * n7;
                            oscElement.dazdt2 = oscElement.dazdt - dazdt;
                        }
                        ++j;
                    }
                }
                for (int k = 1; k != n4; ++k) {
                    for (int l = k * this.gw + 1; l != l - 1 + n3; ++l) {
                        final OscElement oscElement6;
                        oscElement6.az += (oscElement6 = this.grid[l]).dazdt * n6;
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
        final int n14 = (this.gridSizeY / 2 - this.windowOffsetY) * this.winSize.height / this.windowHeight;
        for (int n15 = 0; n15 < this.sourceCount; ++n15) {
            final OscSource oscSource = this.sources[n15];
            final int screenX = oscSource.getScreenX();
            final int screenY = oscSource.getScreenY();
            int n16 = -1;
            if (this.sourceType == 2 && n15 % 2 == 0) {
                n16 = -256;
            }
            this.plotSource(n15, screenX, screenY, n16);
        }
        if (this.imageSource != null) {
            this.imageSource.newPixels();
        }
        graphics.drawImage(this.dbimage, 0, 0, this);
        if (!this.stoppedCheck.getState()) {
            this.cv.repaint(this.pause);
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
    
    void plotSource(final int n, final int n2, final int n3, int n4) {
        final int n5 = 7;
        if (n == this.selectedSource) {
            n4 ^= 0x808080;
        }
        for (int i = 0; i <= n5; ++i) {
            final int n6 = (int)(Math.sqrt(n5 * n5 - i * i) + 0.5);
            this.plotPixel(n2 + i, n3 + n6, n4);
            this.plotPixel(n2 + n6, n3 + i, n4);
            this.plotPixel(n2 + i, n3 - n6, n4);
            this.plotPixel(n2 - n6, n3 + i, n4);
            this.plotPixel(n2 - i, n3 + n6, n4);
            this.plotPixel(n2 + n6, n3 - i, n4);
            this.plotPixel(n2 - i, n3 - n6, n4);
            this.plotPixel(n2 - n6, n3 - i, n4);
            this.plotPixel(n2, n3 + i, n4);
            this.plotPixel(n2, n3 - i, n4);
            this.plotPixel(n2 + i, n3, n4);
            this.plotPixel(n2 - i, n3, n4);
        }
    }
    
    void renderGrid() {
        final double n = this.brightnessBar.getValue() / 50.0;
        final double n2 = this.EBrightnessBar.getValue() / 100.0;
        final int selectedIndex = this.viewChooser.getSelectedIndex();
        boolean b = false;
        int n6;
        int n5;
        int n4;
        int n3 = n4 = (n5 = (n6 = -1));
        switch (selectedIndex) {
            case 2:
            case 3:
            case 14:
            case 19: {
                n3 = (n4 = selectedIndex);
                break;
            }
            case 0:
            case 4:
            case 13:
            case 16:
            case 17: {
                n6 = (n5 = selectedIndex);
                break;
            }
            case 18: {
                n6 = (n5 = 17);
                n4 = 2;
                break;
            }
            case 1: {
                b = true;
                break;
            }
            case 5: {
                n3 = (n4 = 2);
                n6 = (n5 = 0);
                break;
            }
            case 6: {
                n3 = (n4 = 2);
                b = true;
                break;
            }
            case 9: {
                n3 = (n4 = 3);
                n6 = (n5 = 0);
                break;
            }
            case 10: {
                n3 = (n4 = 3);
                b = true;
                break;
            }
            case 11: {
                n3 = (n4 = 2);
                n5 = 0;
                n6 = 4;
                break;
            }
            case 12: {
                n3 = (n4 = 2);
                n5 = 0;
                n6 = 4;
                b = true;
                break;
            }
            case 8: {
                n4 = 2;
                n3 = 3;
                n6 = 4;
                b = true;
                break;
            }
            case 7: {
                n4 = 2;
                n3 = 3;
                n5 = 0;
                n6 = 4;
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
                if (oscElement.gray || oscElement.jx != 0.0 || oscElement.jy != 0.0) {
                    n18 = (n17 = (n19 = 64));
                    n16 = n6;
                    n15 = n3;
                }
                if (n15 != -1) {
                    double n20 = 0.0;
                    switch (n15) {
                        case 2: {
                            n20 = oscElement.az * 0.2;
                            break;
                        }
                        case 19: {
                            n20 = oscElement.dazdt;
                            break;
                        }
                        case 3: {
                            n20 = 0.0;
                            if (oscElement.conductor) {
                                if (!this.grid[n8 + this.gw].conductor) {
                                    n20 = this.getEField(this.grid[n8 + this.gw], this.grid[n8 + this.gw - 1], this.grid[n8 + this.gw + 1]);
                                }
                                if (!this.grid[n8 - this.gw].conductor) {
                                    n20 += this.getEField(this.grid[n8 - this.gw], this.grid[n8 - this.gw + 1], this.grid[n8 - this.gw - 1]);
                                }
                                if (!this.grid[n8 + 1].conductor) {
                                    n20 += this.getEField(this.grid[n8 + 1], this.grid[n8 + this.gw + 1], this.grid[n8 - this.gw + 1]);
                                }
                                if (!this.grid[n8 - 1].conductor) {
                                    n20 += this.getEField(this.grid[n8 - 1], this.grid[n8 - this.gw - 1], this.grid[n8 + this.gw - 1]);
                                }
                                n20 *= 0.6;
                                break;
                            }
                            break;
                        }
                        case 14: {
                            final double eField = this.getEField(oscElement, this.grid[n8 + this.gw], this.grid[n8 - this.gw]);
                            final double eField2 = this.getEField(oscElement, this.grid[n8 - 1], this.grid[n8 + 1]);
                            n20 = 0.4 * (Math.sqrt(eField * eField + eField2 * eField2) * 3.0 + oscElement.az * oscElement.az * 0.05);
                            break;
                        }
                    }
                    double n21 = n20 * n;
                    if (n21 < -1.0) {
                        n21 = -1.0;
                    }
                    if (n21 > 1.0) {
                        n21 = 1.0;
                    }
                    if (n15 == 3) {
                        if (n21 < 0.0) {
                            n19 += (int)(-n21 * (255 - n19));
                        }
                        else {
                            n17 += (int)(n21 * (255 - n17));
                            n18 += (int)(n21 * (255 - n18));
                        }
                    }
                    else if (n21 < 0.0) {
                        n17 += (int)(-n21 * (255 - n17));
                    }
                    else {
                        n18 += (int)(n21 * (255 - n18));
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
                    double n22 = 0.0;
                    double n23 = 0.0;
                    switch (n16) {
                        case 0: {
                            n22 = this.getEField(oscElement, this.grid[n8 + this.gw], this.grid[n8 - this.gw]) * n2;
                            n23 = this.getEField(oscElement, this.grid[n8 - 1], this.grid[n8 + 1]) * n2;
                            break;
                        }
                        case 16:
                        case 17: {
                            n22 = this.getdEdt(oscElement, this.grid[n8 + this.gw], this.grid[n8 - this.gw]) * 100.0;
                            n23 = this.getdEdt(oscElement, this.grid[n8 - 1], this.grid[n8 + 1]) * 100.0;
                            if (n16 == 16) {
                                break;
                            }
                        }
                        case 4: {
                            if (!oscElement.conductor) {
                                n22 += oscElement.jx * 0.2;
                                n23 += oscElement.jy * 0.2;
                                break;
                            }
                            if (!this.grid[n8 + this.gw].conductor) {
                                n22 += -this.grid[n8 + this.gw].az * 0.2;
                            }
                            if (!this.grid[n8 - this.gw].conductor) {
                                n22 += this.grid[n8 - this.gw].az * 0.2;
                            }
                            if (!this.grid[n8 + 1].conductor) {
                                n23 += this.grid[n8 + 1].az * 0.2;
                            }
                            if (!this.grid[n8 - 1].conductor) {
                                n23 += -this.grid[n8 - 1].az * 0.2;
                                break;
                            }
                            break;
                        }
                        case 13: {
                            final double n24 = 3.6 * oscElement.az;
                            n23 = -n24 * this.getEField(oscElement, this.grid[n8 - this.gw], this.grid[n8 + this.gw]);
                            n22 = n24 * this.getEField(oscElement, this.grid[n8 + 1], this.grid[n8 - 1]);
                            break;
                        }
                    }
                    final double sqrt = Math.sqrt(n22 * n22 + n23 * n23);
                    if (sqrt > 0.0) {
                        n22 /= sqrt;
                        n23 /= sqrt;
                    }
                    double n25 = sqrt * n;
                    int n27;
                    if (n16 == 4) {
                        if (n25 > 1.0) {
                            if (n25 > 2.0) {
                                n25 = 2.0;
                            }
                            final double n26 = n25 - 1.0;
                            n27 = (n17 = 255);
                            n19 += (int)(n26 * (255 - n19));
                        }
                        else {
                            n17 += (int)(n25 * (255 - n17));
                            n27 = n18 + (int)(n25 * (255 - n18));
                        }
                    }
                    else if (n25 > 1.0) {
                        if (n25 > 2.0) {
                            n25 = 2.0;
                        }
                        final double n28 = n25 - 1.0;
                        n27 = 255;
                        n17 += (int)(n28 * (255 - n17));
                        n19 += (int)(n28 * (255 - n19));
                    }
                    else {
                        n27 = n18 + (int)(n25 * (255 - n18));
                    }
                    final int n29 = 0xFF000000 | n17 << 16 | n27 << 8 | n19;
                    final int n30 = (n11 - n9) / 2;
                    final int n31 = (n12 - n10) / 2;
                    final int n32 = n9 + n30 - (int)(n30 * n22);
                    final int n33 = n10 + n31 - (int)(n31 * n23);
                    final int n34 = n9 + n30 + (int)(n30 * n22);
                    final int n35 = n10 + n31 + (int)(n31 * n23);
                    this.drawLine(n32, n33, n34, n35, n29);
                    final int n36 = 3;
                    this.drawLine(n34, n35, (int)(n23 * n36 - n22 * n36 + n34), (int)(-n22 * n36 - n23 * n36 + n35), n29);
                    this.drawLine(n34, n35, (int)(-n23 * n36 - n22 * n36 + n34), (int)(n22 * n36 - n23 * n36 + n35), n29);
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
    
    double getEField(final OscElement oscElement, final OscElement oscElement2, final OscElement oscElement3) {
        if (oscElement.conductor) {
            return 0.0;
        }
        if (oscElement2.conductor) {
            return 0.66 * (oscElement.dazdt - oscElement3.dazdt);
        }
        if (oscElement3.conductor) {
            return 0.66 * (oscElement2.dazdt - oscElement.dazdt);
        }
        return 0.33 * (-oscElement3.dazdt + oscElement2.dazdt);
    }
    
    double getdEdt(final OscElement oscElement, final OscElement oscElement2, final OscElement oscElement3) {
        if (oscElement.conductor) {
            return 0.0;
        }
        if (oscElement2.conductor) {
            return 2.0 * (oscElement.dazdt2 - oscElement3.dazdt2);
        }
        if (oscElement3.conductor) {
            return 2.0 * (oscElement2.dazdt2 - oscElement.dazdt2);
        }
        return -oscElement3.dazdt2 + oscElement2.dazdt2;
    }
    
    double clamp(final double n) {
        return (n < 0.0) ? 0.0 : ((n > 1.0) ? 1.0 : n);
    }
    
    void doSources(final double n, final boolean b) {
        if (this.sourceCount == 0) {
            return;
        }
        final double n2 = this.forceBar.getValue() * (this.t - this.forceTimeZero) * 0.01166665;
        int n3 = this.auxBar.getValue() - 1;
        if (n3 > 38) {
            n3 = 38;
        }
        final double n4 = n2 + n3 * 0.08267349088394192;
        double sin = 0.0;
        double sin2;
        if (!this.sourcePacket) {
            sin2 = Math.sin(n2);
            if (this.sourceCount >= 2) {
                sin = Math.sin(n4);
            }
        }
        else {
            final double n5 = n2 % 6.283185307179586 / (0.01166665 * this.forceBar.getValue()) - 10.0;
            sin2 = Math.exp(-0.01 * n5 * n5) * Math.sin(n5 * 0.2);
            if (n5 < 0.0) {
                this.doFilter();
            }
        }
        if (b) {
            sin = (sin2 = 0.0);
        }
        final OscSource oscSource = this.sources[0];
        final OscSource oscSource2 = this.sources[2];
        final double n6 = (float)(2.0 * sin2 * this.sourceMult);
        oscSource2.v = n6;
        oscSource.v = n6;
        final OscSource oscSource3 = this.sources[1];
        final OscSource oscSource4 = this.sources[3];
        final double n7 = (float)(2.0 * sin * this.sourceMult);
        oscSource4.v = n7;
        oscSource3.v = n7;
        if (this.sourceType == 1) {
            for (int i = 0; i != this.sourceCount / 2; ++i) {
                final OscSource oscSource5 = this.sources[i * 2];
                final OscSource oscSource6 = this.sources[i * 2 + 1];
                this.drawPlaneSource(oscSource5.x, oscSource5.y, oscSource6.x, oscSource6.y, this.sources[i].v * 0.1);
            }
        }
        else if (this.sourceType == 2) {
            for (int j = 0; j != this.sourceCount / 2; ++j) {
                final OscSource oscSource7 = this.sources[j * 2];
                final OscSource oscSource8 = this.sources[j * 2 + 1];
                this.drawAntennaSource(oscSource7.x, oscSource7.y, oscSource8.x, oscSource8.y, this.sources[j].v * 0.1);
            }
        }
        else if (this.sourceType == 3) {
            final int min = this.min(this.sources[0].x, this.sources[1].x);
            final int max = this.max(this.sources[0].x, this.sources[1].x);
            final int min2 = this.min(this.sources[0].y, this.sources[1].y);
            final int max2 = this.max(this.sources[0].y, this.sources[1].y);
            double jx;
            double jy = jx = this.sources[0].v * 0.1;
            if (min == max) {
                jx = 0.0;
            }
            if (min2 == max2) {
                jy = 0.0;
            }
            for (int k = min + 1; k < max; ++k) {
                this.grid[k + this.gw * min2].jx = jx;
                this.grid[k + this.gw * max2].jx = -jx;
            }
            this.grid[min + this.gw * min2].jx = 0.5 * jx;
            this.grid[max + this.gw * min2].jx = 0.5 * jx;
            this.grid[min + this.gw * max2].jx = -0.5 * jx;
            this.grid[max + this.gw * max2].jx = -0.5 * jx;
            for (int l = min2 + 1; l < max2; ++l) {
                this.grid[min + this.gw * l].jy = -jy;
                this.grid[max + this.gw * l].jy = jy;
            }
            this.grid[min + this.gw * min2].jy = -0.5 * jy;
            this.grid[min + this.gw * max2].jy = -0.5 * jy;
            this.grid[max + this.gw * min2].jy = 0.5 * jy;
            this.grid[max + this.gw * max2].jy = 0.5 * jy;
        }
    }
    
    int abs(final int n) {
        return (n < 0) ? (-n) : n;
    }
    
    void drawPlaneSource(int n, int n2, int n3, int n4, final double n5) {
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
        final double sqrt = Math.sqrt((n3 - n) * (n3 - n) + (n4 - n2) * (n4 - n2));
        final double n6 = (n3 - n) / sqrt;
        final double n7 = (n4 - n2) / sqrt;
        if (n != n3 || n2 != n4) {
            if (this.abs(n4 - n2) > this.abs(n3 - n)) {
                for (int sign = this.sign(n4 - n2), i = n2; i != n4 + sign; i += sign) {
                    final int n8 = n + (n3 - n) * (i - n2) / (n4 - n2);
                    this.grid[n8 + i * this.gw].jx = n5 * n6;
                    this.grid[n8 + i * this.gw].jy = n5 * n7;
                }
            }
            else {
                for (int sign2 = this.sign(n3 - n), j = n; j != n3 + sign2; j += sign2) {
                    final int n9 = n2 + (n4 - n2) * (j - n) / (n3 - n);
                    this.grid[j + n9 * this.gw].jx = n5 * n6;
                    this.grid[j + n9 * this.gw].jy = n5 * n7;
                }
            }
        }
    }
    
    void drawAntennaSource(int n, int n2, int n3, int n4, final double n5) {
        final double n6 = this.forceBar.getValue() * 0.0224;
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
        final double sqrt = Math.sqrt((n3 - n) * (n3 - n) + (n4 - n2) * (n4 - n2));
        final double n7 = 0.0;
        final double n8 = (n3 - n) / sqrt;
        final double n9 = (n4 - n2) / sqrt;
        if (n != n3 || n2 != n4) {
            if (this.abs(n4 - n2) > this.abs(n3 - n)) {
                for (int sign = this.sign(n4 - n2), i = n2; i != n4 + sign; i += sign) {
                    final int n10 = n + (n3 - n) * (i - n2) / (n4 - n2);
                    final double n11 = Math.sin((n7 + (i - n2) / n9) * n6) * n5;
                    this.grid[n10 + i * this.gw].jx = n11 * n8;
                    this.grid[n10 + i * this.gw].jy = n11 * n9;
                }
            }
            else {
                for (int sign2 = this.sign(n3 - n), j = n; j != n3 + sign2; j += sign2) {
                    final int n12 = n2 + (n4 - n2) * (j - n) / (n3 - n);
                    final double n13 = Math.sin((n7 + (j - n) / n8) * n6) * n5;
                    this.grid[j + n12 * this.gw].jx = n13 * n8;
                    this.grid[j + n12 * this.gw].jy = n13 * n9;
                }
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
        final double n8 = this.brightnessBar.getValue() * this.EBrightnessBar.getValue() / 5000.0;
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
                Label_0240: {
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
                                        break Label_0240;
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
                if (oscElement.gray || oscElement.jx != 0.0 || oscElement.jy != 0.0) {
                    n = 0.0;
                }
                else {
                    final double eField = this.getEField(oscElement, this.grid[n20 + this.gw], this.grid[n20 - this.gw]);
                    final double eField2 = this.getEField(oscElement, this.grid[n20 - 1], this.grid[n20 + 1]);
                    final double sqrt = Math.sqrt(eField * eField + eField2 * eField2);
                    if (sqrt == 0.0) {
                        n = 0.0;
                    }
                    else {
                        final double n21 = eField / sqrt;
                        final double n22 = eField2 / sqrt;
                        final double n23 = n;
                        final double n24 = n2;
                        n += 0.5 * n21 * n9;
                        n2 += 0.5 * n22 * n9;
                        double n25 = sqrt * n8;
                        int col = this.grid[n20].col;
                        if (n25 != n10 || col != n11) {
                            int n26 = col >> 16 & 0xFF;
                            final int n27 = col >> 8 & 0xFF;
                            int n28 = col & 0xFF;
                            int n29;
                            if (n25 > 1.0) {
                                if (n25 > 2.0) {
                                    n25 = 2.0;
                                }
                                --n25;
                                n29 = 255;
                                n26 += (int)(n25 * (255 - n26));
                                n28 += (int)(n25 * (255 - n28));
                            }
                            else {
                                n29 = n27 + (int)(n25 * (255 - n27));
                            }
                            col = (0xFF000000 | n26 << 16 | n29 << 8 | n28);
                            n10 = n25;
                            n11 = col;
                        }
                        final int n30 = (int)(n23 * this.winSize.width / this.windowWidth);
                        final int n31 = (int)(n24 * this.winSize.height / this.windowHeight);
                        final int n32 = (int)(n * this.winSize.width / this.windowWidth);
                        final int n33 = (int)(n2 * this.winSize.height / this.windowHeight);
                        this.drawLine(n30, n31, n32, n33, col);
                        if (!b || this.linegrid[n17 + value * n18] != 1 || (n17 & 0x3) != 0x0 || (n18 & 0x3) != 0x0) {
                            continue;
                        }
                        final int n34 = 5;
                        this.drawLine(n32, n33, (int)(n22 * n34 - n21 * n34 + n32), (int)(-n21 * n34 - n22 * n34 + n33), col);
                        this.drawLine(n32, n33, (int)(-n22 * n34 - n21 * n34 + n32), (int)(n21 * n34 - n22 * n34 + n33), col);
                    }
                }
            }
        }
    }
    
    void filterGrid() {
        if ((this.filterCount++ & 0x3) != 0x0) {
            return;
        }
        if (this.filterCount > 200) {
            return;
        }
        final double n = (this.forceBar.getValue() > 7 && this.sourceCount > 0 && !this.sourcePacket) ? 40.0 : 8.0;
        final double n2 = 4.0 + n;
        for (int i = 1; i < this.gridSizeY - 1; ++i) {
            for (int j = 1; j < this.gridSizeX - 1; ++j) {
                final int n3 = j + i * this.gw;
                final OscElement oscElement = this.grid[n3];
                if (oscElement.jx == 0.0 && oscElement.jy == 0.0 && !oscElement.boundary) {
                    if (!oscElement.conductor) {
                        oscElement.az = (oscElement.az * n + this.grid[n3 - 1].az + this.grid[n3 + 1].az + this.grid[n3 - this.gw].az + this.grid[n3 + this.gw].az) / n2;
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
            this.dragClear = (oscElement.conductor || oscElement.jx != 0.0 || oscElement.jy != 0.0);
            this.dragSet = !this.dragClear;
        }
        oscElement.conductor = false;
        if (this.dragClear) {
            return;
        }
        switch (this.modeChooser.getSelectedIndex()) {
            case 0: {
                this.addConductor(n5, n6, 1.0);
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
            if (this.resBar.getValue() == this.windowWidth) {
                return;
            }
            this.setResolution();
            this.reinit();
            this.cv.repaint(this.pause);
        }
        if (adjustmentEvent.getSource() == this.brightnessBar || adjustmentEvent.getSource() == this.EBrightnessBar || adjustmentEvent.getSource() == this.lineDensityBar) {
            this.cv.repaint(this.pause);
        }
        if (adjustmentEvent.getSource() == this.lineDensityBar) {
            this.linegrid = null;
        }
        if (adjustmentEvent.getSource() == this.forceBar) {
            this.setForce();
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
        final int n = 30;
        this.windowOffsetY = n;
        this.windowOffsetX = n;
        this.gridSizeX = this.windowWidth + this.windowOffsetX * 2;
        this.gridSizeY = this.windowHeight + this.windowOffsetY * 2;
        this.gridSizeXY = this.gridSizeX * this.gridSizeY;
        this.gw = this.gridSizeX;
        this.linegrid = null;
    }
    
    void setResolution(final int value) {
        this.resBar.setValue(value);
        this.setResolution();
        this.reinit();
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
    }
    
    void doSetup() {
        this.t = 0.0;
        this.doClearAll();
        this.sourceCount = -1;
        this.filterCount = 0;
        this.sourceChooser.select(0);
        this.setForceBar(10);
        this.brightnessBar.setValue(100);
        this.EBrightnessBar.setValue(100);
        this.auxBar.setValue(1);
        (this.setup = this.setupList.elementAt(this.setupChooser.getSelectedIndex())).select();
        this.setup.doSetupSources();
        this.calcBoundaries();
        this.setDamping();
    }
    
    void addMedium() {
    }
    
    void addCondMedium(final double n) {
        this.conductFillRect(0, this.gridSizeY / 2, this.gridSizeX - 1, this.gridSizeY - 1, n);
    }
    
    void setSources() {
        if (this.sourceCount > 0) {
            this.doSources(1.0, true);
        }
        this.sourceMult = 1.0;
        final int sourceCount = this.sourceCount;
        this.sourceCount = 0;
        this.sourceType = 1;
        this.sourcePacket = false;
        switch (this.sourceChooser.getSelectedIndex()) {
            case 0: {
                this.sourceCount = 0;
                break;
            }
            case 1: {
                this.sourceCount = 1;
                break;
            }
            case 2: {
                this.sourceCount = 2;
                break;
            }
            case 3: {
                this.sourceCount = 1;
                this.sourcePacket = true;
                break;
            }
            case 4: {
                this.sourceCount = 1;
                this.sourceType = 2;
                break;
            }
            case 5: {
                this.sourceCount = 2;
                this.sourceType = 2;
                break;
            }
            case 6: {
                this.sourceCount = 1;
                this.sourceType = 3;
                break;
            }
            case 7: {
                this.sourceCount = 1;
                this.sourceType = 3;
                this.sourcePacket = true;
                break;
            }
        }
        if (this.sourceCount == 2) {
            this.auxBar.setValue(1);
            this.auxLabel.setText("Phase Difference");
            this.auxBar.show();
            this.auxLabel.show();
        }
        else {
            this.auxBar.hide();
            this.auxLabel.hide();
        }
        this.validate();
        this.sourceCount *= 2;
        if (sourceCount != this.sourceCount) {
            final int n = this.windowOffsetX + this.windowWidth - 1;
            final int n2 = this.windowOffsetY + this.windowHeight - 1;
            this.sources[0] = new OscSource(this.windowOffsetX, this.windowOffsetY);
            this.sources[1] = new OscSource(n, this.windowOffsetY);
            this.sources[2] = new OscSource(this.windowOffsetX, n2);
            this.sources[3] = new OscSource(n, n2);
        }
    }
    
    void setupMode(final int n, final int n2, final int n3, final int n4, final int n5, final int n6) {
        for (int i = 0; i < n3; ++i) {
            for (int j = 0; j < n4; ++j) {
                this.grid[i + n + this.gw * (j + n2)].az = 2.0 * (Math.cos(3.141592653589793 * n5 * i / (n3 - 1)) * Math.cos(3.141592653589793 * n6 * j / (n4 - 1)));
                this.grid[i + n + this.gw * (j + n2)].dazdt = 0.0;
            }
        }
        this.noFilter();
    }
    
    void addMode(final int n, final int n2, final int n3, final int n4, final int n5, final int n6) {
        for (int i = 0; i < n3; ++i) {
            for (int j = 0; j < n4; ++j) {
                final OscElement oscElement = this.grid[i + n + this.gw * (j + n2)];
                oscElement.az += 2.0 * (Math.cos(3.141592653589793 * n5 * i / (n3 - 1)) * Math.cos(3.141592653589793 * n6 * j / (n4 - 1)));
            }
        }
        this.noFilter();
    }
    
    void addModeI(final int n, final int n2, final int n3, final int n4, final int n5, final int n6) {
        final double n7 = 12.566370614359172 * Math.sqrt(n5 * n5 / ((n3 - 1) * (n3 - 1)) + n6 * n6 / ((n4 - 1) * (n4 - 1)));
        for (int i = 0; i < n3; ++i) {
            for (int j = 0; j < n4; ++j) {
                final OscElement oscElement = this.grid[i + n + this.gw * (j + n2)];
                oscElement.dazdt += n7 * (Math.cos(3.141592653589793 * n5 * i / (n3 - 1)) * Math.cos(3.141592653589793 * n6 * j / (n4 - 1)));
            }
        }
        this.noFilter();
    }
    
    void findMode(final int n, final int n2, final int n3, final int n4) {
        double n5 = 0.0;
        for (int n6 = 1000, i = 0; i != n6; ++i) {
            int n7 = 0;
            for (int j = n; j <= n3; ++j) {
                for (int k = n2; k <= n4; ++k) {
                    final int n8 = j + k * this.gw;
                    final OscElement oscElement = this.grid[n8 - 1];
                    final OscElement oscElement2 = this.grid[n8 + 1];
                    final OscElement oscElement3 = this.grid[n8 - this.gw];
                    final OscElement oscElement4 = this.grid[n8 + this.gw];
                    final OscElement oscElement5 = this.grid[n8];
                    if (!oscElement5.conductor) {
                        if (oscElement5.col != 0) {
                            oscElement5.dazdt = oscElement5.az;
                        }
                        else {
                            final double az = oscElement5.az;
                            double az2 = oscElement.az;
                            if (oscElement.conductor) {
                                az2 = (oscElement2.conductor ? az : oscElement2.az);
                            }
                            double az3 = oscElement2.az;
                            if (oscElement2.conductor) {
                                az3 = (oscElement.conductor ? az : oscElement.az);
                            }
                            double az4 = oscElement3.az;
                            if (oscElement3.conductor) {
                                az4 = (oscElement4.conductor ? az : oscElement4.az);
                            }
                            double az5 = oscElement4.az;
                            if (oscElement4.conductor) {
                                az5 = (oscElement3.conductor ? az : oscElement3.az);
                            }
                            oscElement5.dazdt = 0.125 * (az3 + az2 + az5 + az4 + 4.0 * az);
                            n5 += Math.abs(oscElement5.dazdt - az);
                            ++n7;
                        }
                    }
                }
            }
            n5 /= n7;
            for (int l = n; l <= n3; ++l) {
                for (int n9 = n2; n9 <= n4; ++n9) {
                    final OscElement oscElement6 = this.grid[l + n9 * this.gw];
                    oscElement6.az = oscElement6.dazdt;
                    oscElement6.dazdt = 0.0;
                }
            }
        }
    }
    
    void addConductor(final int n, final int n2, final double n3) {
        final OscElement oscElement = this.grid[n + this.gw * n2];
        oscElement.conductor = (n3 != 0.0);
        if (n3 == 1.0) {
            final OscElement oscElement2 = oscElement;
            final OscElement oscElement3 = oscElement;
            final double n4 = 0.0;
            oscElement3.dazdt = n4;
            oscElement2.az = n4;
        }
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
            return ((this.x - EMWave1Frame.this.windowOffsetX) * EMWave1Frame.this.winSize.width + EMWave1Frame.this.winSize.width / 2) / EMWave1Frame.this.windowWidth;
        }
        
        int getScreenY() {
            return ((this.y - EMWave1Frame.this.windowOffsetY) * EMWave1Frame.this.winSize.height + EMWave1Frame.this.winSize.height / 2) / EMWave1Frame.this.windowHeight;
        }
    }
    
    class OscElement
    {
        boolean conductor;
        double jx;
        double jy;
        double damp;
        double az;
        double dazdt;
        double dazdt2;
        int col;
        boolean boundary;
        boolean gray;
        
        int getType() {
            if (this.conductor) {
                return 1;
            }
            if (this.jx != 0.0 || this.jy != 0.0) {
                return 2;
            }
            return 0;
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
            EMWave1Frame.this.setSources();
        }
        
        abstract Setup createNext();
    }
    
    class PlaneWaveSetup extends Setup
    {
        String getName() {
            return "Plane Wave";
        }
        
        void select() {
            EMWave1Frame.this.sourceChooser.select(1);
            EMWave1Frame.this.brightnessBar.setValue(225);
            EMWave1Frame.this.setForceBar(30);
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
            EMWave1Frame.this.brightnessBar.setValue(126);
            EMWave1Frame.this.setForceBar(34);
        }
        
        void doSetupSources() {
            EMWave1Frame.this.sourceChooser.select(2);
            EMWave1Frame.this.setSources();
            final OscSource oscSource = EMWave1Frame.this.sources[0];
            final OscSource oscSource2 = EMWave1Frame.this.sources[1];
            final int windowOffsetY = EMWave1Frame.this.windowOffsetY;
            oscSource2.y = windowOffsetY;
            oscSource.y = windowOffsetY;
            EMWave1Frame.this.sources[0].x = EMWave1Frame.this.windowOffsetX + 1;
            final OscSource oscSource3 = EMWave1Frame.this.sources[2];
            final OscSource oscSource4 = EMWave1Frame.this.sources[3];
            final int windowOffsetX = EMWave1Frame.this.windowOffsetX;
            oscSource4.x = windowOffsetX;
            oscSource3.x = windowOffsetX;
            EMWave1Frame.this.sources[2].y = EMWave1Frame.this.windowOffsetY + 1;
            EMWave1Frame.this.sources[3].y = EMWave1Frame.this.windowOffsetY + EMWave1Frame.this.windowHeight - 1;
        }
        
        Setup createNext() {
            return new ConductReflectSetup();
        }
    }
    
    class ConductReflectSetup extends Setup
    {
        String getName() {
            return "Reflection At Conductor";
        }
        
        void select() {
            EMWave1Frame.this.sourceChooser.select(7);
            EMWave1Frame.this.addCondMedium(1.0);
            EMWave1Frame.this.setForceBar(4);
            EMWave1Frame.this.brightnessBar.setValue(1600);
        }
        
        void doSetupSources() {
            EMWave1Frame.this.setSources();
            EMWave1Frame.this.sources[0].x = EMWave1Frame.this.gridSizeX / 2 - 1;
            EMWave1Frame.this.sources[1].x = EMWave1Frame.this.gridSizeX / 2 + 1;
            EMWave1Frame.this.sources[0].y = EMWave1Frame.this.windowOffsetY;
            EMWave1Frame.this.sources[1].y = EMWave1Frame.this.windowOffsetY + 2;
        }
        
        Setup createNext() {
            return new OscDipoleSetup();
        }
    }
    
    class OscDipoleSetup extends Setup
    {
        String getName() {
            return "Oscillating Dipole";
        }
        
        void select() {
            EMWave1Frame.this.setForceBar(10);
            EMWave1Frame.this.brightnessBar.setValue(1066);
            EMWave1Frame.this.EBrightnessBar.setValue(300);
        }
        
        void doSetupSources() {
            EMWave1Frame.this.sourceChooser.select(1);
            EMWave1Frame.this.setSources();
            final int n = EMWave1Frame.this.gridSizeX / 2;
            final int n2 = EMWave1Frame.this.gridSizeY / 2;
            final OscSource oscSource = EMWave1Frame.this.sources[0];
            final OscSource oscSource2 = EMWave1Frame.this.sources[1];
            final int n3 = n;
            oscSource2.x = n3;
            oscSource.x = n3;
            EMWave1Frame.this.sources[0].y = n2 - 1;
            EMWave1Frame.this.sources[1].y = n2 + 1;
        }
        
        Setup createNext() {
            return new HalfWaveAnt1Setup();
        }
    }
    
    class HalfWaveAnt1Setup extends Setup
    {
        String getName() {
            return "Half Wave Antenna";
        }
        
        void select() {
            EMWave1Frame.this.setForceBar(10);
            EMWave1Frame.this.brightnessBar.setValue(390);
            EMWave1Frame.this.EBrightnessBar.setValue(350);
        }
        
        void doSetupSources() {
            EMWave1Frame.this.sourceChooser.select(4);
            EMWave1Frame.this.setSources();
            final int n = EMWave1Frame.this.gridSizeX / 2;
            final int n2 = EMWave1Frame.this.gridSizeY / 2;
            final OscSource oscSource = EMWave1Frame.this.sources[0];
            final OscSource oscSource2 = EMWave1Frame.this.sources[1];
            final int n3 = n;
            oscSource2.x = n3;
            oscSource.x = n3;
            EMWave1Frame.this.sources[0].y = n2 + 7;
            EMWave1Frame.this.sources[1].y = n2 - 7;
        }
        
        Setup createNext() {
            return new FullWaveAnt1Setup();
        }
    }
    
    class FullWaveAnt1Setup extends Setup
    {
        String getName() {
            return "Full Wave Ant (End-Driven)";
        }
        
        void select() {
            EMWave1Frame.this.setForceBar(25);
            EMWave1Frame.this.brightnessBar.setValue(390);
        }
        
        void doSetupSources() {
            EMWave1Frame.this.sourceChooser.select(4);
            EMWave1Frame.this.setSources();
            final int n = EMWave1Frame.this.gridSizeX / 2;
            final int n2 = EMWave1Frame.this.gridSizeY / 2;
            final OscSource oscSource = EMWave1Frame.this.sources[0];
            final OscSource oscSource2 = EMWave1Frame.this.sources[1];
            final int n3 = n;
            oscSource2.x = n3;
            oscSource.x = n3;
            EMWave1Frame.this.sources[0].y = n2 + 6;
            EMWave1Frame.this.sources[1].y = n2 - 5;
        }
        
        Setup createNext() {
            return new FullWaveAnt2Setup();
        }
    }
    
    class FullWaveAnt2Setup extends Setup
    {
        String getName() {
            return "Full Wave Ant (Center-Driven)";
        }
        
        void select() {
            EMWave1Frame.this.setForceBar(25);
            EMWave1Frame.this.brightnessBar.setValue(390);
        }
        
        void doSetupSources() {
            EMWave1Frame.this.sourceChooser.select(5);
            EMWave1Frame.this.setSources();
            final int n = EMWave1Frame.this.gridSizeX / 2;
            final int y = EMWave1Frame.this.gridSizeY / 2;
            final OscSource oscSource = EMWave1Frame.this.sources[0];
            final OscSource oscSource2 = EMWave1Frame.this.sources[1];
            final int n2 = n;
            oscSource2.x = n2;
            oscSource.x = n2;
            final OscSource oscSource3 = EMWave1Frame.this.sources[2];
            final OscSource oscSource4 = EMWave1Frame.this.sources[3];
            final int n3 = n;
            oscSource4.x = n3;
            oscSource3.x = n3;
            EMWave1Frame.this.sources[0].y = y + 1;
            EMWave1Frame.this.sources[1].y = y + 6;
            EMWave1Frame.this.sources[2].y = y;
            EMWave1Frame.this.sources[3].y = y - 5;
            EMWave1Frame.this.auxBar.setValue(40);
        }
        
        Setup createNext() {
            return new OscCurrentLoop();
        }
    }
    
    class OscCurrentLoop extends Setup
    {
        String getName() {
            return "Current Loop";
        }
        
        void select() {
            EMWave1Frame.this.sourceChooser.select(6);
            EMWave1Frame.this.setSources();
            EMWave1Frame.this.sources[0].x = EMWave1Frame.this.gridSizeX / 2 - 1;
            EMWave1Frame.this.sources[0].y = EMWave1Frame.this.gridSizeY / 2 - 1;
            EMWave1Frame.this.sources[1].x = EMWave1Frame.this.gridSizeX / 2 + 1;
            EMWave1Frame.this.sources[1].y = EMWave1Frame.this.gridSizeY / 2 + 1;
            EMWave1Frame.this.brightnessBar.setValue(270);
            EMWave1Frame.this.setForceBar(34);
        }
        
        Setup createNext() {
            return new BigMode01Setup();
        }
    }
    
    class BigMode01Setup extends Setup
    {
        String getName() {
            return "Big TE01 Mode";
        }
        
        void select() {
            EMWave1Frame.this.sourceChooser.select(0);
            final int n = EMWave1Frame.this.windowWidth * 3 / 4;
            final int n2 = EMWave1Frame.this.windowOffsetX + EMWave1Frame.this.windowWidth / 2 - n / 2;
            final int n3 = EMWave1Frame.this.windowOffsetY + EMWave1Frame.this.windowHeight / 2 - n / 2;
            for (int i = 1; i != 4; ++i) {
                EMWave1Frame.this.conductDrawRect(n2 - i, n3 - i, n2 + n + i - 1, n3 + n + i - 1, 1.0);
            }
            EMWave1Frame.this.setupMode(n2, n3, n, n, 0, 1);
            EMWave1Frame.this.brightnessBar.setValue(200);
            EMWave1Frame.this.EBrightnessBar.setValue(400);
        }
        
        Setup createNext() {
            return new BigMode10Setup();
        }
    }
    
    class BigMode10Setup extends Setup
    {
        String getName() {
            return "Big TE10 Mode";
        }
        
        void select() {
            EMWave1Frame.this.sourceChooser.select(0);
            final int n = EMWave1Frame.this.windowWidth * 3 / 4;
            final int n2 = EMWave1Frame.this.windowOffsetX + EMWave1Frame.this.windowWidth / 2 - n / 2;
            final int n3 = EMWave1Frame.this.windowOffsetY + EMWave1Frame.this.windowHeight / 2 - n / 2;
            for (int i = 1; i != 4; ++i) {
                EMWave1Frame.this.conductDrawRect(n2 - i, n3 - i, n2 + n + i - 1, n3 + n + i - 1, 1.0);
            }
            EMWave1Frame.this.setupMode(n2, n3, n, n, 1, 0);
            EMWave1Frame.this.brightnessBar.setValue(200);
            EMWave1Frame.this.EBrightnessBar.setValue(400);
        }
        
        Setup createNext() {
            return new BigMode1001Setup();
        }
    }
    
    class BigMode1001Setup extends Setup
    {
        String getName() {
            return "Big TE10+TE01 Mode";
        }
        
        void select() {
            EMWave1Frame.this.sourceChooser.select(0);
            final int n = EMWave1Frame.this.windowWidth * 3 / 4;
            final int n2 = EMWave1Frame.this.windowOffsetX + EMWave1Frame.this.windowWidth / 2 - n / 2;
            final int n3 = EMWave1Frame.this.windowOffsetY + EMWave1Frame.this.windowHeight / 2 - n / 2;
            for (int i = 1; i != 4; ++i) {
                EMWave1Frame.this.conductDrawRect(n2 - i, n3 - i, n2 + n + i - 1, n3 + n + i - 1, 1.0);
            }
            EMWave1Frame.this.setupMode(n2, n3, n, n, 1, 0);
            EMWave1Frame.this.addMode(n2, n3, n, n, 0, 1);
            EMWave1Frame.this.brightnessBar.setValue(200);
            EMWave1Frame.this.EBrightnessBar.setValue(250);
        }
        
        Setup createNext() {
            return new BigMode1001iSetup();
        }
    }
    
    class BigMode1001iSetup extends Setup
    {
        String getName() {
            return "Big TE10+TE01i Mode";
        }
        
        void select() {
            EMWave1Frame.this.sourceChooser.select(0);
            final int n = EMWave1Frame.this.windowWidth * 3 / 4;
            final int n2 = EMWave1Frame.this.windowOffsetX + EMWave1Frame.this.windowWidth / 2 - n / 2;
            final int n3 = EMWave1Frame.this.windowOffsetY + EMWave1Frame.this.windowHeight / 2 - n / 2;
            for (int i = 1; i != 4; ++i) {
                EMWave1Frame.this.conductDrawRect(n2 - i, n3 - i, n2 + n + i - 1, n3 + n + i - 1, 1.0);
            }
            EMWave1Frame.this.setupMode(n2, n3, n, n, 1, 0);
            EMWave1Frame.this.addModeI(n2, n3, n, n, 0, 1);
            EMWave1Frame.this.brightnessBar.setValue(200);
            EMWave1Frame.this.EBrightnessBar.setValue(250);
        }
        
        Setup createNext() {
            return new BigMode2Setup();
        }
    }
    
    class BigMode2Setup extends Setup
    {
        String getName() {
            return "Big TE11 Mode";
        }
        
        void select() {
            EMWave1Frame.this.sourceChooser.select(0);
            final int n = EMWave1Frame.this.windowWidth * 3 / 4;
            final int n2 = EMWave1Frame.this.windowOffsetX + EMWave1Frame.this.windowWidth / 2 - n / 2;
            final int n3 = EMWave1Frame.this.windowOffsetY + EMWave1Frame.this.windowHeight / 2 - n / 2;
            for (int i = 1; i != 4; ++i) {
                EMWave1Frame.this.conductDrawRect(n2 - i, n3 - i, n2 + n + i - 1, n3 + n + i - 1, 1.0);
            }
            EMWave1Frame.this.setupMode(n2, n3, n, n, 1, 1);
            EMWave1Frame.this.brightnessBar.setValue(200);
            EMWave1Frame.this.EBrightnessBar.setValue(300);
        }
        
        Setup createNext() {
            return new OneByOneModesSetup();
        }
    }
    
    class OneByOneModesSetup extends Setup
    {
        String getName() {
            return "TE10 Modes";
        }
        
        void select() {
            EMWave1Frame.this.sourceChooser.select(0);
            for (int n = 1, n2 = 5; n + n2 < EMWave1Frame.this.windowHeight; n += n2 + 2) {
                final int n3 = (n + n2) * (EMWave1Frame.this.windowWidth - 8) / EMWave1Frame.this.windowHeight + 6;
                final int n4 = n + EMWave1Frame.this.windowOffsetY;
                final int n5 = EMWave1Frame.this.windowOffsetX + 1;
                EMWave1Frame.this.conductDrawRect(n5 - 1, n4 - 1, n5 + n3, n4 + n2, 1.0);
                EMWave1Frame.this.setupMode(n5, n4, n3, n2, 1, 0);
            }
            EMWave1Frame.this.brightnessBar.setValue(250);
            EMWave1Frame.this.EBrightnessBar.setValue(300);
        }
        
        Setup createNext() {
            return new NByZeroModesSetup();
        }
    }
    
    class NByZeroModesSetup extends Setup
    {
        String getName() {
            return "TEn0 Modes";
        }
        
        void select() {
            EMWave1Frame.this.sourceChooser.select(0);
            int n = 1;
            final int n2 = 6;
            final int n3 = EMWave1Frame.this.windowWidth - 2;
            for (int n4 = 1; n + n2 < EMWave1Frame.this.windowHeight; n += n2 + 2, ++n4) {
                final int n5 = n + EMWave1Frame.this.windowOffsetY;
                final int n6 = EMWave1Frame.this.windowOffsetX + 1;
                EMWave1Frame.this.conductDrawRect(n6 - 1, n5 - 1, n6 + n3, n5 + n2, 1.0);
                EMWave1Frame.this.setupMode(n6, n5, n3, n2, n4, 0);
            }
            EMWave1Frame.this.brightnessBar.setValue(200);
            EMWave1Frame.this.EBrightnessBar.setValue(128);
        }
        
        Setup createNext() {
            return new NByOneModesSetup();
        }
    }
    
    class NByOneModesSetup extends Setup
    {
        String getName() {
            return "TEn1 Modes";
        }
        
        void select() {
            EMWave1Frame.this.sourceChooser.select(0);
            int n = 1;
            final int n2 = 10;
            final int n3 = EMWave1Frame.this.windowWidth - 2;
            for (int n4 = 1; n + n2 < EMWave1Frame.this.windowHeight; n += n2 + 2, ++n4) {
                final int n5 = n + EMWave1Frame.this.windowOffsetY;
                final int n6 = EMWave1Frame.this.windowOffsetX + 1;
                EMWave1Frame.this.conductDrawRect(n6 - 1, n5 - 1, n6 + n3, n5 + n2, 1.0);
                EMWave1Frame.this.setupMode(n6, n5, n3, n2, n4, 1);
            }
            EMWave1Frame.this.brightnessBar.setValue(150);
        }
        
        Setup createNext() {
            return new NByNModesSetup();
        }
    }
    
    class NByNModesSetup extends Setup
    {
        String getName() {
            return "TEnn Modes";
        }
        
        void select() {
            EMWave1Frame.this.sourceChooser.select(0);
            int n = 3;
            if (EMWave1Frame.this.resBar.getValue() >= 70) {
                ++n;
            }
            if (EMWave1Frame.this.resBar.getValue() >= 100) {
                ++n;
            }
            final int n2 = EMWave1Frame.this.windowHeight / n - 2;
            final int n3 = EMWave1Frame.this.windowWidth / n - 2;
            for (int i = 1; i <= n; ++i) {
                for (int j = 1; j <= n; ++j) {
                    if (i != 1 || j != 1) {
                        final int n4 = EMWave1Frame.this.windowOffsetX + 1 + (n2 + 2) * (j - 1);
                        final int n5 = EMWave1Frame.this.windowOffsetY + 1 + (n3 + 2) * (i - 1);
                        EMWave1Frame.this.conductDrawRect(n4 - 1, n5 - 1, n4 + n3, n5 + n2, 1.0);
                        EMWave1Frame.this.setupMode(n4, n5, n3, n2, i - 1, j - 1);
                    }
                }
            }
            EMWave1Frame.this.brightnessBar.setValue(300);
        }
        
        Setup createNext() {
            return new ZeroByNModeCombosSetup();
        }
    }
    
    class ZeroByNModeCombosSetup extends Setup
    {
        String getName() {
            return "TEn0 Mode Combos";
        }
        
        void select() {
            EMWave1Frame.this.sourceChooser.select(0);
            int n = 1;
            final int n2 = 8;
            final int n3 = EMWave1Frame.this.windowWidth - 2;
            while (n + n2 < EMWave1Frame.this.windowHeight) {
                final int i = EMWave1Frame.this.getrand(8) + 1;
                int n4;
                do {
                    n4 = EMWave1Frame.this.getrand(8) + 1;
                } while (i == n4);
                final int n5 = n + EMWave1Frame.this.windowOffsetY;
                final int n6 = EMWave1Frame.this.windowOffsetX + 1;
                EMWave1Frame.this.conductDrawRect(n6 - 1, n5 - 1, n6 + n3, n5 + n2, 1.0);
                final boolean b = !(this instanceof ZeroByNModeCombosSetup);
                for (int j = 0; j != n3; ++j) {
                    for (int k = 0; k != n2; ++k) {
                        EMWave1Frame.this.grid[j + n6 + EMWave1Frame.this.gw * (k + n5)].az = 2.0 * (Math.cos(i * 3.141592653589793 * j / (n3 - 1)) * Math.cos(3.141592653589793 * (b ? 1 : 0) * k / (n2 - 1)) * 0.5 + Math.cos(n4 * 3.141592653589793 * j / (n3 - 1)) * Math.cos(3.141592653589793 * (b ? 1 : 0) * k / (n2 - 1)) * 0.5);
                        EMWave1Frame.this.grid[j + n6 + EMWave1Frame.this.gw * (k + n5)].dazdt = 0.0;
                    }
                }
                n += n2 + 2;
            }
            EMWave1Frame.this.noFilter();
            EMWave1Frame.this.brightnessBar.setValue(310);
        }
        
        Setup createNext() {
            return new OneByNModeCombosSetup();
        }
    }
    
    class OneByNModeCombosSetup extends ZeroByNModeCombosSetup
    {
        String getName() {
            return "TEn1 Mode Combos";
        }
        
        Setup createNext() {
            return new NByNModeCombosSetup();
        }
    }
    
    class NByNModeCombosSetup extends Setup
    {
        String getName() {
            return "TEnn Mode Combos";
        }
        
        void select() {
            EMWave1Frame.this.sourceChooser.select(0);
            int n = 2;
            if (EMWave1Frame.this.resBar.getValue() >= 70) {
                ++n;
            }
            if (EMWave1Frame.this.resBar.getValue() >= 100) {
                ++n;
            }
            final int n2 = EMWave1Frame.this.windowHeight / n - 2;
            final int n3 = EMWave1Frame.this.windowWidth / n - 2;
            for (int i = 1; i <= n; ++i) {
                for (int j = 1; j <= n; ++j) {
                    final int getrand = EMWave1Frame.this.getrand(4);
                    final int n4 = EMWave1Frame.this.getrand(4) + 1;
                    int n5;
                    int getrand2;
                    do {
                        n5 = EMWave1Frame.this.getrand(4) + 1;
                        getrand2 = EMWave1Frame.this.getrand(4);
                    } while (getrand == n5 && n4 == getrand2);
                    final int n6 = EMWave1Frame.this.windowOffsetX + 1 + (n2 + 2) * (i - 1);
                    final int n7 = EMWave1Frame.this.windowOffsetY + 1 + (n3 + 2) * (j - 1);
                    EMWave1Frame.this.conductDrawRect(n6 - 1, n7 - 1, n6 + n3, n7 + n2, 1.0);
                    for (int k = 0; k != n3; ++k) {
                        for (int l = 0; l != n2; ++l) {
                            EMWave1Frame.this.grid[k + n6 + EMWave1Frame.this.gw * (l + n7)].az = 2.0 * (Math.cos(getrand * 3.141592653589793 * k / (n3 - 1)) * Math.cos(n4 * 3.141592653589793 * l / (n2 - 1)) * 0.5 + Math.cos(n5 * 3.141592653589793 * k / (n3 - 1)) * Math.cos(getrand2 * 3.141592653589793 * l / (n2 - 1)) * 0.5);
                            EMWave1Frame.this.grid[k + n6 + EMWave1Frame.this.gw * (l + n7)].dazdt = 0.0;
                        }
                    }
                }
            }
            EMWave1Frame.this.brightnessBar.setValue(370);
            EMWave1Frame.this.noFilter();
        }
        
        Setup createNext() {
            return new Waveguides1Setup();
        }
    }
    
    class Waveguides1Setup extends Setup
    {
        String getName() {
            return "Waveguides";
        }
        
        void select() {
            EMWave1Frame.this.sourceChooser.select(1);
            int n = 1;
            int n2 = 5;
            final int n3 = EMWave1Frame.this.windowOffsetY + 1;
            while (n + n2 < EMWave1Frame.this.windowWidth) {
                final int n4 = n + EMWave1Frame.this.windowOffsetX;
                EMWave1Frame.this.conductDrawRect(n4 - 1, n3 - 1, n4 - 1, EMWave1Frame.this.gridSizeY - 1, 1.0);
                EMWave1Frame.this.conductDrawRect(n4 + n2, n3 - 1, n4 + n2, EMWave1Frame.this.gridSizeY - 1, 1.0);
                n2 += 2;
                n += n2;
            }
            EMWave1Frame.this.conductDrawRect(n - 1 + EMWave1Frame.this.windowOffsetX, n3, EMWave1Frame.this.gridSizeX - 1, n3, 1.0);
            EMWave1Frame.this.brightnessBar.setValue(215);
            EMWave1Frame.this.setForceBar(28);
        }
        
        Setup createNext() {
            return new CapacitorSetup();
        }
    }
    
    class CapacitorSetup extends Setup
    {
        String getName() {
            return "Capacitor";
        }
        
        void select() {
            EMWave1Frame.this.sourceChooser.select(0);
            final int n = ((EMWave1Frame.this.windowWidth > 45) ? 45 : EMWave1Frame.this.windowWidth) * 3 / 4;
            final int n2 = EMWave1Frame.this.windowOffsetX + EMWave1Frame.this.windowWidth / 2;
            final int n3 = EMWave1Frame.this.windowOffsetY + EMWave1Frame.this.windowHeight / 2;
            final int n4 = n2 - n / 2;
            final int n5 = n3 - n / 2;
            for (int i = 1; i != 4; ++i) {
                EMWave1Frame.this.conductDrawRect(n4 - i, n5 - i, n4 + n + i - 1, n5 + n + i - 1, 1.0);
            }
            EMWave1Frame.this.setupMode(n4, n5, n, n, 1, 0);
            EMWave1Frame.this.conductFillRect(n4, n5, n4 + n, n5 + 4, 1.0);
            EMWave1Frame.this.conductFillRect(n4, n5 + n - 4, n4 + n, n5 + n - 1, 1.0);
            final int n6 = 4;
            EMWave1Frame.this.conductFillRect(n2 - 2, n5, n2 + 2, n3 - n6, 1.0);
            EMWave1Frame.this.conductFillRect(n2 - 2, n3 + n6, n2 + 2, n5 + n, 1.0);
            EMWave1Frame.this.conductFillRect(n2 - 5, n3 - (n6 + 1), n2 + 5, n3 - (n6 - 1), 1.0);
            EMWave1Frame.this.conductFillRect(n2 - 5, n3 + (n6 - 1), n2 + 5, n3 + (n6 + 1), 1.0);
            EMWave1Frame.this.brightnessBar.setValue(700);
            EMWave1Frame.this.EBrightnessBar.setValue(200);
            EMWave1Frame.this.findMode(n4, n5, n4 + n, n5 + n);
            EMWave1Frame.this.noFilter();
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
            EMWave1Frame.this.sourceChooser.select(1);
            int i = 1;
            final int n = 3;
            final int n2 = EMWave1Frame.this.windowOffsetY + 11;
            while (i + n < EMWave1Frame.this.windowWidth) {
                final int n3 = (i + n) * (EMWave1Frame.this.windowHeight - 18) / EMWave1Frame.this.windowWidth + 6;
                final int n4 = i + EMWave1Frame.this.windowOffsetX;
                for (int j = 0; j != n3 + 2; ++j) {
                    final OscElement oscElement = EMWave1Frame.this.grid[n4 - 1 + EMWave1Frame.this.gw * (n2 + j - 1)];
                    final OscElement oscElement2 = EMWave1Frame.this.grid[n4 + n + EMWave1Frame.this.gw * (n2 + j - 1)];
                    final boolean b = true;
                    oscElement2.conductor = b;
                    oscElement.conductor = b;
                }
                for (int k = 0; k != n + 2; ++k) {
                    final OscElement oscElement3 = EMWave1Frame.this.grid[n4 + k - 1 + EMWave1Frame.this.gw * (n2 - 1)];
                    final OscElement oscElement4 = EMWave1Frame.this.grid[n4 + k - 1 + EMWave1Frame.this.gw * (n2 + n3)];
                    final boolean b2 = true;
                    oscElement4.conductor = b2;
                    oscElement3.conductor = b2;
                }
                EMWave1Frame.this.grid[n4 + n / 2 + EMWave1Frame.this.gw * (n2 - 1)].conductor = false;
                i += n + 2;
            }
            --i;
            while (i < EMWave1Frame.this.windowWidth) {
                EMWave1Frame.this.grid[i + EMWave1Frame.this.windowOffsetX + EMWave1Frame.this.gw * (n2 - 1)].conductor = true;
                ++i;
            }
            EMWave1Frame.this.brightnessBar.setValue(120);
            EMWave1Frame.this.setForceBar(15);
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
            EMWave1Frame.this.sourceChooser.select(1);
            final int n = EMWave1Frame.this.gridSizeX / 2;
            final int n2 = EMWave1Frame.this.windowOffsetY + 4;
            EMWave1Frame.this.conductFillRect(0, n2, EMWave1Frame.this.gridSizeX - 1, n2 + 2, 1.0);
            EMWave1Frame.this.conductFillRect(n - 7, n2, n + 7, n2 + 2, 0.0);
            EMWave1Frame.this.brightnessBar.setValue(275);
            EMWave1Frame.this.setForceBar(35);
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
            EMWave1Frame.this.sourceChooser.select(1);
            final int n = EMWave1Frame.this.gridSizeX / 2;
            final int n2 = EMWave1Frame.this.windowOffsetY + 4;
            EMWave1Frame.this.conductFillRect(0, n2, EMWave1Frame.this.gridSizeX - 1, n2 + 2, 1.0);
            EMWave1Frame.this.conductFillRect(n - 7, n2, n - 5, n2 + 2, 0.0);
            EMWave1Frame.this.conductFillRect(n + 5, n2, n + 7, n2 + 2, 0.0);
            EMWave1Frame.this.brightnessBar.setValue(366);
            EMWave1Frame.this.setForceBar(35);
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
            EMWave1Frame.this.sourceChooser.select(1);
            final int n = EMWave1Frame.this.gridSizeX / 2;
            final int n2 = EMWave1Frame.this.windowOffsetY + 4;
            EMWave1Frame.this.conductFillRect(0, n2, EMWave1Frame.this.gridSizeX - 1, n2 + 2, 1.0);
            EMWave1Frame.this.conductFillRect(n - 13, n2, n - 11, n2 + 2, 0.0);
            EMWave1Frame.this.conductFillRect(n - 1, n2, n + 1, n2 + 2, 0.0);
            EMWave1Frame.this.conductFillRect(n + 11, n2, n + 13, n2 + 2, 0.0);
            EMWave1Frame.this.brightnessBar.setValue(310);
            EMWave1Frame.this.setForceBar(35);
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
            EMWave1Frame.this.sourceChooser.select(1);
            final int n = EMWave1Frame.this.gridSizeX / 2;
            final int n2 = EMWave1Frame.this.windowOffsetY + 6;
            EMWave1Frame.this.conductFillRect(n - 7, n2, n + 7, n2 + 2, 1.0);
            EMWave1Frame.this.brightnessBar.setValue(400);
            EMWave1Frame.this.setForceBar(35);
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
            EMWave1Frame.this.sourceChooser.select(1);
            final int n = EMWave1Frame.this.windowOffsetX + EMWave1Frame.this.windowWidth / 2;
            EMWave1Frame.this.conductFillRect(EMWave1Frame.this.windowOffsetX + EMWave1Frame.this.windowWidth * 2 / 3, EMWave1Frame.this.windowOffsetY + 3, EMWave1Frame.this.windowOffsetY + EMWave1Frame.this.windowWidth - 1, EMWave1Frame.this.windowOffsetY + 5, 1.0);
            EMWave1Frame.this.brightnessBar.setValue(250);
            EMWave1Frame.this.setForceBar(35);
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
            EMWave1Frame.this.sourceChooser.select(6);
            EMWave1Frame.this.setSources();
            EMWave1Frame.this.sources[0].x = EMWave1Frame.this.windowOffsetX;
            EMWave1Frame.this.sources[0].y = EMWave1Frame.this.windowOffsetY + EMWave1Frame.this.windowHeight * 3 / 4 - 1;
            EMWave1Frame.this.sources[1].x = EMWave1Frame.this.windowOffsetX + 2;
            EMWave1Frame.this.sources[1].y = EMWave1Frame.this.windowOffsetY + EMWave1Frame.this.windowHeight * 3 / 4 + 1;
            EMWave1Frame.this.brightnessBar.setValue(250);
            EMWave1Frame.this.setForceBar(40);
            EMWave1Frame.this.conductDrawRect(0, EMWave1Frame.this.windowOffsetY + EMWave1Frame.this.windowHeight - 1, EMWave1Frame.this.gridSizeX - 1, EMWave1Frame.this.windowOffsetY + EMWave1Frame.this.windowHeight - 1, 1.0);
        }
        
        void doSetupSources() {
        }
        
        Setup createNext() {
            return null;
        }
    }
}
