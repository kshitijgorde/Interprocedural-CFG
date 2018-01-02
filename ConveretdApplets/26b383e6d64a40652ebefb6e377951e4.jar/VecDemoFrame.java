import java.awt.event.ItemEvent;
import java.awt.Event;
import java.net.URL;
import java.awt.event.ActionEvent;
import java.awt.event.ComponentEvent;
import java.awt.event.MouseEvent;
import java.text.NumberFormat;
import java.awt.image.ImageObserver;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.image.ImageProducer;
import java.awt.Component;
import java.awt.LayoutManager;
import java.util.Vector;
import java.awt.TextField;
import java.awt.Color;
import java.awt.Label;
import java.awt.Choice;
import java.awt.Button;
import java.awt.Checkbox;
import java.util.Random;
import java.awt.image.MemoryImageSource;
import java.awt.Image;
import java.awt.Rectangle;
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

class VecDemoFrame extends Frame implements ComponentListener, ActionListener, MouseMotionListener, MouseListener, ItemListener, DecentScrollbarListener
{
    Thread engine;
    Dimension winSize;
    Rectangle viewMain;
    Rectangle viewAxes;
    Image dbimage;
    Image backimage;
    MemoryImageSource imageSource;
    int[] pixels;
    VecDemo applet;
    Random random;
    static final double pi = 3.141592653589793;
    VecDemoCanvas cv;
    Checkbox stoppedCheck;
    Button resetButton;
    Button kickButton;
    Checkbox reverseCheck;
    Button infoButton;
    Choice functionChooser;
    Choice dispChooser;
    static final int DISP_PART_VELOC = 0;
    static final int DISP_PART_FORCE = 1;
    static final int DISP_VECTORS = 2;
    static final int DISP_NONE = 3;
    static final int DISP_CURLERS = 4;
    Label partCountLabel;
    Label textFieldLabel;
    Label strengthLabel;
    DecentScrollbar partCountBar;
    DecentScrollbar strengthBar;
    DecentScrollbar aux1Bar;
    DecentScrollbar aux2Bar;
    DecentScrollbar aux3Bar;
    double fieldStrength;
    double barFieldStrength;
    Color darkYellow;
    final double lineWidth = 0.001;
    AuxBar[] auxBars;
    Label vecDensityLabel;
    DecentScrollbar vecDensityBar;
    Label potentialLabel;
    DecentScrollbar potentialBar;
    Label lineDensityLabel;
    DecentScrollbar lineDensityBar;
    Choice modeChooser;
    Choice floorColorChooser;
    Choice floorLineChooser;
    TextField[] textFields;
    int reverse;
    int[] xpoints;
    int[] ypoints;
    GridElement[][] grid;
    Particle[] particles;
    FieldVector[] vectors;
    int vecCount;
    int[][] density;
    Checkbox flatCheck;
    boolean isFlat;
    double viewAngle;
    double viewAngleDragStart;
    double viewZoom;
    double viewZoomDragStart;
    double viewAngleCos;
    double viewAngleSin;
    double viewHeight;
    double viewHeightDragStart;
    double viewDistance;
    int integralX;
    int integralY;
    int vectorSpacing;
    int currentStep;
    boolean showA;
    boolean parseError;
    Color[] fieldColors;
    static final int gridsize = 80;
    static final int densitygridsize = 16;
    static final double densitygroupsize = 0.125;
    static final int maxParticleCount = 2500;
    boolean functionChanged;
    boolean backgroundChanged;
    boolean dragging;
    boolean draggingView;
    int oldDragX;
    int oldDragY;
    int dragX;
    int dragY;
    int dragStartX;
    int dragStartY;
    double dragZoomStart;
    Vector functionList;
    VecFunction curfunc;
    int pause;
    static final int MOT_VELOCITY = 0;
    static final int MOT_FORCE = 1;
    static final int MOT_CURLERS = 2;
    static final int MOT_EQUIPOTENTIAL = 3;
    static final int FC_FIELD = 0;
    static final int FC_POTENTIAL = 1;
    static final int FC_NONE = 2;
    static final int FC_DIV = 3;
    static final int FC_CURL = 4;
    static final int FL_NONE = 0;
    static final int FL_GRID = 1;
    static final int FL_EQUIP = 2;
    static final int FL_LINES = 3;
    static final int MODE_VIEW_ROTATE = 0;
    static final int MODE_VIEW_ZOOM = 1;
    static final int MODE_LINE_INT = 2;
    static final int MODE_SURF_INT = 3;
    boolean useBufferedImage;
    double divOffset;
    double divRange;
    int[] shadowBufferTop;
    int[] shadowBufferBottom;
    int[] shadowBufferTop2;
    int[] shadowBufferBottom2;
    final double floorBrightMult = 2.0;
    static final double root2 = 1.4142135623730951;
    double scalex;
    double scaley;
    long lastTime;
    double timeStep;
    double partMult;
    boolean slowDragView;
    static int frames;
    static int framerate;
    static long firsttime;
    double wooft;
    int rediscount;
    boolean boundCheck;
    double[] oldY;
    double[] rk_k1;
    double[] rk_k2;
    double[] rk_k3;
    double[] rk_k4;
    double[] rk_yn;
    double[] rk_Y;
    double[] rk_Yhalf;
    double[] rk_oldY;
    double[] ls_fieldavg;
    
    public String getAppletInfo() {
        return "VecDemo by Paul Falstad";
    }
    
    int getrand(final int n) {
        int nextInt = this.random.nextInt();
        if (nextInt < 0) {
            nextInt = -nextInt;
        }
        return nextInt % n;
    }
    
    VecDemoFrame(final VecDemo applet) {
        super("2-D Vector Fields Applet v1.4");
        this.engine = null;
        this.darkYellow = new Color(144, 144, 0);
        this.viewZoom = 1.6;
        this.viewAngleCos = 1.0;
        this.viewAngleSin = 0.0;
        this.viewHeight = 2.0;
        this.viewDistance = 5.0;
        this.integralX = -1;
        this.vectorSpacing = 16;
        this.pause = 20;
        this.useBufferedImage = false;
        this.slowDragView = true;
        this.wooft = 0.0;
        this.rk_k1 = new double[6];
        this.rk_k2 = new double[6];
        this.rk_k3 = new double[6];
        this.rk_k4 = new double[6];
        this.rk_yn = new double[6];
        this.rk_Y = new double[6];
        this.rk_Yhalf = new double[6];
        this.rk_oldY = new double[6];
        this.ls_fieldavg = new double[3];
        this.applet = applet;
    }
    
    public void init() {
        try {
            final String parameter = this.applet.getParameter("PAUSE");
            if (parameter != null) {
                this.pause = Integer.parseInt(parameter);
            }
        }
        catch (Exception ex) {}
        if (new Double(System.getProperty("java.class.version")) >= 48.0) {
            this.useBufferedImage = true;
        }
        this.functionList = new Vector();
        VecFunction next = new InverseRadial();
        final boolean b = false;
        while (next != null) {
            this.functionList.addElement(next);
            next = next.createNext();
            if ((b ? 1 : 0) == 1000) {
                System.out.print("setup loop\n");
                return;
            }
        }
        final Color[] array = new Color[27];
        for (int i = 0; i != 27; ++i) {
            array[i] = new Color((i % 3 + 1) * 85, (i / 3 % 3 + 1) * 85, (i / 9 % 3 + 1) * 85);
        }
        this.random = new Random();
        this.particles = new Particle[2500];
        for (int j = 0; j != 2500; ++j) {
            this.particles[j] = new Particle();
            this.particles[j].color = array[j % 27];
        }
        this.xpoints = new int[4];
        this.ypoints = new int[4];
        this.density = new int[16][16];
        this.setLayout(new VecDemoLayout());
        (this.cv = new VecDemoCanvas(this)).addComponentListener(this);
        this.cv.addMouseMotionListener(this);
        this.cv.addMouseListener(this);
        this.add(this.cv);
        this.functionChooser = new Choice();
        for (int k = 0; k != this.functionList.size(); ++k) {
            this.functionChooser.add("Setup: " + ((VecFunction)this.functionList.elementAt(k)).getName());
        }
        this.add(this.functionChooser);
        this.functionChooser.addItemListener(this);
        (this.floorColorChooser = new Choice()).add("Color: field magnitude");
        this.floorColorChooser.add("Color: potential");
        this.floorColorChooser.add("Color: none");
        this.floorColorChooser.add("Color: divergence");
        this.floorColorChooser.add("Color: curl z");
        this.floorColorChooser.addItemListener(this);
        this.add(this.floorColorChooser);
        (this.floorLineChooser = new Choice()).add("Floor: no lines");
        this.floorLineChooser.add("Floor: grid");
        this.floorLineChooser.add("Floor: equipotentials");
        this.floorLineChooser.add("Floor: streamlines");
        this.floorLineChooser.addItemListener(this);
        this.add(this.floorLineChooser);
        this.floorLineChooser.select(2);
        (this.flatCheck = new Checkbox("Flat View")).addItemListener(this);
        this.add(this.flatCheck);
        (this.dispChooser = new Choice()).addItemListener(this);
        this.setupDispChooser(true);
        this.add(this.dispChooser);
        (this.modeChooser = new Choice()).add("Mouse = Adjust Angle");
        this.modeChooser.add("Mouse = Adjust Zoom");
        this.modeChooser.add("Mouse = Line Integral");
        this.modeChooser.add("Mouse = Surface Integral");
        this.modeChooser.addItemListener(this);
        this.add(this.modeChooser);
        (this.stoppedCheck = new Checkbox("Stopped")).addItemListener(this);
        this.add(this.stoppedCheck);
        (this.reverseCheck = new Checkbox("Reverse")).addItemListener(this);
        this.add(this.reverseCheck);
        this.add(this.resetButton = new Button("Reset"));
        this.resetButton.addActionListener(this);
        this.add(this.kickButton = new Button("Kick"));
        this.kickButton.addActionListener(this);
        this.kickButton.disable();
        this.add(this.strengthLabel = new Label("Field Strength", 1));
        this.add(this.strengthBar = new DecentScrollbar(this, 80, 1, 120));
        this.add(this.partCountLabel = new Label("Number of Particles", 1));
        this.add(this.partCountBar = new DecentScrollbar(this, 500, 1, 2500));
        this.add(this.vecDensityLabel = new Label("Vector Density", 1));
        this.add(this.vecDensityBar = new DecentScrollbar(this, 32, 2, 64));
        this.add(this.potentialLabel = new Label("Potential", 1));
        this.add(this.potentialBar = new DecentScrollbar(this, 250, 0, 1000));
        this.auxBars = new AuxBar[3];
        final Label label;
        this.add(label = new Label("Aux 1", 1));
        this.add(this.aux1Bar = new DecentScrollbar(this, 0, 0, 100));
        this.auxBars[0] = new AuxBar(label, this.aux1Bar);
        final Label label2;
        this.add(label2 = new Label("Aux 2", 1));
        this.add(this.aux2Bar = new DecentScrollbar(this, 0, 0, 100));
        this.auxBars[1] = new AuxBar(label2, this.aux2Bar);
        final Label label3;
        this.add(label3 = new Label("Aux 3", 1));
        this.add(this.aux3Bar = new DecentScrollbar(this, 0, 0, 100));
        this.auxBars[2] = new AuxBar(label3, this.aux3Bar);
        this.add(this.textFieldLabel = new Label("", 1));
        this.textFields = new TextField[2];
        for (int l = 0; l != 2; ++l) {
            this.add(this.textFields[l] = new TextField());
            this.textFields[l].addActionListener(this);
        }
        this.fieldColors = new Color[513];
        final int n = 76;
        for (int n2 = 0; n2 != 256; ++n2) {
            final int n3 = n + (128 - n) * n2 / 255;
            this.fieldColors[n2] = new Color(0xFF000000 | n + (255 - n) * n2 / 255 << 8 | n3 << 16 | n3);
        }
        for (int n4 = 0; n4 != 256; ++n4) {
            this.fieldColors[n4 + 256] = new Color(0xFF00FF00 | (n4 / 2 + 128) * 65537);
        }
        this.fieldColors[512] = this.fieldColors[511];
        this.add(new Label("http://www.falstad.com", 1));
        this.reinit();
        this.cv.setBackground(Color.black);
        this.cv.setForeground(Color.lightGray);
        this.resize(650, 500);
        this.handleResize();
        final Dimension screenSize = this.getToolkit().getScreenSize();
        final Dimension size = this.getSize();
        this.setLocation((screenSize.width - size.width) / 2, (screenSize.height - size.height) / 2);
        this.functionChanged();
        this.dispChooserChanged();
        this.show();
    }
    
    void handleResize() {
        final Dimension size = this.cv.getSize();
        this.winSize = size;
        final Dimension dimension = size;
        if (this.winSize.width == 0) {
            return;
        }
        this.dbimage = this.createImage(dimension.width, dimension.height);
        this.scaleworld();
        this.viewMain = new Rectangle(this.winSize);
        this.viewAxes = new Rectangle(this.winSize.width - 100, 0, 100, 100);
        this.backgroundChanged = true;
        this.pixels = null;
        if (this.useBufferedImage) {
            try {
                final Class<?> forName = Class.forName("java.awt.image.BufferedImage");
                final Class<?> forName2 = Class.forName("java.awt.image.DataBufferInt");
                final Class<?> forName3 = Class.forName("java.awt.image.Raster");
                this.backimage = (Image)forName.getConstructor(Integer.TYPE, Integer.TYPE, Integer.TYPE).newInstance(new Integer(dimension.width), new Integer(dimension.height), new Integer(1));
                this.pixels = (int[])forName2.getMethod("getData", (Class[])null).invoke(forName3.getMethod("getDataBuffer", (Class<?>[])null).invoke(forName.getMethod("getRaster", (Class[])null).invoke(this.backimage, (Object[])null), (Object[])null), (Object[])null);
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
            this.backimage = this.cv.createImage(this.imageSource);
        }
    }
    
    void resetDensityGroups() {
        for (int i = 0; i != 16; ++i) {
            for (int j = 0; j != 16; ++j) {
                this.density[i][j] = 0;
            }
        }
        int particleCount;
        int k;
        for (particleCount = this.getParticleCount(), k = 0; k != particleCount; ++k) {
            this.addToDensityGroup(this.particles[k]);
        }
        while (k != 2500) {
            this.particles[k].lifetime = -100.0;
            ++k;
        }
    }
    
    int addToDensityGroup(final Particle particle) {
        final int n = (int)((particle.pos[0] + 1.0) * 8.0);
        final int n2 = (int)((particle.pos[1] + 1.0) * 8.0);
        int n3 = 0;
        try {
            n3 = ++this.density[n][n2];
            if (n3 > 2500) {
                System.out.print(n + " " + n2 + " " + this.density[n][n2] + "\n");
            }
        }
        catch (Exception ex) {
            System.out.print(particle.pos[0] + " " + particle.pos[1] + "\n");
            ex.printStackTrace();
        }
        return n3;
    }
    
    void removeFromDensityGroup(final Particle particle) {
        final int n = (int)((particle.pos[0] + 1.0) * 8.0);
        final int n2 = (int)((particle.pos[1] + 1.0) * 8.0);
        try {
            final int[] array = this.density[n];
            final int n3 = n2;
            final int n4 = array[n3] - 1;
            array[n3] = n4;
            if (n4 < 0) {
                System.out.print(n + " " + n2 + " " + this.density[n][n2] + "\n");
            }
        }
        catch (Exception ex) {
            System.out.print(particle.pos[0] + " " + particle.pos[1] + "\n");
            ex.printStackTrace();
        }
    }
    
    void positionParticle(final Particle particle) {
        int n = 0;
        int n2 = 0;
        int n3 = 10000;
        final int getrand = this.getrand(16);
        final int getrand2 = this.getrand(16);
        for (int i = 0; i != 16; ++i) {
            for (int j = 0; j != 16; ++j) {
                final int n4 = (getrand + i) % 16;
                final int n5 = (getrand2 + j) % 16;
                if (this.density[n4][n5] <= n3) {
                    n = n4;
                    n2 = n5;
                    n3 = this.density[n4][n5];
                }
            }
        }
        particle.pos[0] = n * 0.125 + this.getrand(100) * 0.125 / 100.0 - 1.0;
        particle.pos[1] = n2 * 0.125 + this.getrand(100) * 0.125 / 100.0 - 1.0;
        particle.lifetime = (this.curfunc.redistribute() ? 500.0 : 5000.0);
        particle.stepsize = 1.0;
        particle.theta = (this.getrand(101) - 50) * 3.141592653589793 / 50.0;
        particle.phi = (this.getrand(101) - 50) * 3.141592653589793 / 50.0;
        for (int k = 0; k != 3; ++k) {
            particle.vel[k] = 0.0;
        }
    }
    
    int getParticleCount() {
        return this.partCountBar.getValue();
    }
    
    void resetParticles() {
        for (int particleCount = this.getParticleCount(), i = 0; i != particleCount; ++i) {
            final Particle particle = this.particles[i];
            for (int j = 0; j != 2; ++j) {
                particle.pos[j] = this.getrand(200) / 100.0 - 1.0;
                particle.vel[j] = 0.0;
            }
            particle.pos[2] = 0.0;
            particle.lifetime = i * 2;
            particle.stepsize = 1.0;
        }
        this.integralX = -1;
        this.resetDensityGroups();
    }
    
    void kickParticles() {
        for (int i = 0; i != this.getParticleCount(); ++i) {
            final Particle particle = this.particles[i];
            for (int j = 0; j != 2; ++j) {
                final double[] vel = particle.vel;
                final int n = j;
                vel[n] += (this.getrand(100) / 99.0 - 0.5) * 0.04;
            }
        }
    }
    
    void generateFunction() {
        if (this.grid == null) {
            this.grid = new GridElement[81][81];
        }
        this.curfunc.setupFrame();
        this.divOffset = this.curfunc.getDivOffset();
        this.divRange = this.curfunc.getDivRange();
        this.curfunc.getLevelHeight();
        for (int i = 0; i != 81; ++i) {
            for (int j = 0; j != 81; ++j) {
                final GridElement[] array = this.grid[i];
                final int n = j;
                final GridElement gridElement = new GridElement();
                array[n] = gridElement;
                final GridElement gridElement4;
                final GridElement gridElement3;
                final GridElement gridElement2 = gridElement3 = (gridElement4 = gridElement);
                final double curl = 0.0;
                gridElement2.height = curl;
                gridElement3.div = curl;
                gridElement4.curl = curl;
                this.curfunc.setGrid(gridElement2, i, j);
            }
        }
        this.curfunc.calcDivergence();
        final double n2 = 0.025;
        for (int k = 0; k != 80; ++k) {
            for (int l = 0; l != 80; ++l) {
                final GridElement gridElement5 = this.grid[l][k];
                final double n3 = this.grid[l + 1][k].height - gridElement5.height;
                final double n4 = this.grid[l][k + 1].height - gridElement5.height;
                gridElement5.normdot = (n3 + n4 + n2) * 0.5780346820809249 / Math.sqrt(n3 * n3 + n4 * n4 + n2 * n2);
            }
        }
        for (int n5 = 0; n5 != 81; ++n5) {
            this.grid[80][n5] = this.grid[79][n5];
            this.grid[n5][80] = this.grid[n5][79];
        }
        this.functionChanged = false;
        this.backgroundChanged = true;
    }
    
    int computeColor(final GridElement gridElement, double n) {
        if (n < 0.0) {
            n = 0.0;
        }
        if (n > 1.0) {
            n = 1.0;
        }
        n = 0.5 + n * 0.5;
        double n2 = 0.0;
        double divRange = 10.0;
        double divOffset = 4.0;
        switch (this.floorColorChooser.getSelectedIndex()) {
            case 0: {
                n2 = gridElement.vecX * gridElement.vecX + gridElement.vecY * gridElement.vecY;
                divOffset = 10.0;
                divRange = 16.0;
                if (!gridElement.valid) {
                    return -16777088;
                }
                break;
            }
            case 1: {
                n2 = gridElement.height - this.curfunc.getLevelHeight();
                divOffset = 1.0;
                divRange = 2.0;
                break;
            }
            case 4: {
                n2 = gridElement.curl;
                divOffset = 4.0;
                divRange = 10.0;
                break;
            }
            case 3: {
                n2 = gridElement.div;
                divOffset = this.divOffset;
                divRange = this.divRange;
                break;
            }
            case 2: {
                if (!gridElement.valid) {
                    return -16777088;
                }
                break;
            }
        }
        final double n3 = n2 * 2.0;
        double n4 = (n3 < 0.0) ? ((Math.log(-n3) + divOffset) / divRange) : 0.0;
        double n5 = (n3 > 0.0) ? ((Math.log(n3) + divOffset) / divRange) : 0.0;
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
        final double n6 = (1.0 - (n4 + n5)) * n;
        final double n7 = 0.6;
        return 0xFF000000 | (int)((n * n4 + n7 * n6) * 255.0) << 16 | (int)((n * n5 + n7 * n6) * 255.0) << 8 | (int)(n7 * n6 * 255.0);
    }
    
    void reinit() {
        this.handleResize();
        this.resetParticles();
        final boolean b = true;
        this.backgroundChanged = b;
        this.functionChanged = b;
    }
    
    void centerString(final Graphics graphics, final String s, final int n) {
        graphics.drawString(s, (this.winSize.width - graphics.getFontMetrics().stringWidth(s)) / 2, n);
    }
    
    void drawBackground() {
        if (this.isFlat) {
            for (int i = 0; i < 80; ++i) {
                for (int j = 0; j < 80; ++j) {
                    final GridElement gridElement = this.grid[j][i];
                    this.fillRectangle(j * this.winSize.width / 80, this.winSize.height - (i + 1) * this.winSize.height / 80, (j + 1) * this.winSize.width / 80, this.winSize.height - i * this.winSize.height / 80, this.computeColor(gridElement, 0.0));
                    gridElement.visible = true;
                }
            }
            this.drawFloor();
            final boolean b = false;
            this.backgroundChanged = b;
            this.functionChanged = b;
            if (this.imageSource != null) {
                this.imageSource.newPixels();
            }
            return;
        }
        this.scaleworld();
        final int n = 80;
        int n2;
        int n3;
        int n4;
        if (this.viewAngleCos < 0.0) {
            n2 = n;
            n3 = 0;
            n4 = -1;
        }
        else {
            n2 = 0;
            n3 = n;
            n4 = 1;
        }
        int n5;
        int n6;
        int n7;
        if (this.viewAngleSin < 0.0) {
            n5 = 0;
            n6 = n;
            n7 = 1;
        }
        else {
            n5 = n;
            n6 = 0;
            n7 = -1;
        }
        final boolean b2 = -this.viewAngleSin * n7 > this.viewAngleCos * n4;
        this.shadowBufferBottom = new int[this.winSize.width];
        this.shadowBufferTop = new int[this.winSize.width];
        this.shadowBufferBottom2 = new int[this.winSize.width];
        this.shadowBufferTop2 = new int[this.winSize.width];
        for (int k = 0; k != this.winSize.width; ++k) {
            this.shadowBufferBottom[k] = (this.shadowBufferBottom2[k] = 0);
            this.shadowBufferTop[k] = (this.shadowBufferTop2[k] = this.winSize.height - 1);
        }
        for (int l = 0; l != this.winSize.width * this.winSize.height; ++l) {
            this.pixels[l] = -16777216;
        }
        final int n8 = (n7 == 1) ? 0 : -1;
        final int n9 = (n4 == 1) ? 0 : -1;
        for (int n10 = n5; n10 != n6; n10 += n7) {
            for (int n11 = n2; n11 != n3; n11 += n4) {
                if (!b2) {
                    n10 = n5;
                }
                while (n10 != n6) {
                    final double n12 = n10 * 0.025 - 1.0;
                    final double n13 = n11 * 0.025 - 1.0;
                    final double n14 = (n10 + n7) * 0.025 - 1.0;
                    final double n15 = (n11 + n4) * 0.025 - 1.0;
                    this.map3d(n12, n13, this.grid[n10][n11].height, this.xpoints, this.ypoints, 0);
                    this.map3d(n14, n13, this.grid[n10 + n7][n11].height, this.xpoints, this.ypoints, 1);
                    this.map3d(n12, n15, this.grid[n10][n11 + n4].height, this.xpoints, this.ypoints, 2);
                    this.map3d(n14, n15, this.grid[n10 + n7][n11 + n4].height, this.xpoints, this.ypoints, 3);
                    final GridElement gridElement2 = this.grid[n10 + n8][n11 + n9];
                    final int computeColor = this.computeColor(gridElement2, gridElement2.normdot);
                    this.fillTriangle(this.xpoints[0], this.ypoints[0], this.xpoints[1], this.ypoints[1], this.xpoints[3], this.ypoints[3], computeColor);
                    this.fillTriangle(this.xpoints[0], this.ypoints[0], this.xpoints[2], this.ypoints[2], this.xpoints[3], this.ypoints[3], computeColor);
                    final int n16 = (this.xpoints[0] + this.xpoints[3]) / 2;
                    final int n17 = (this.ypoints[0] + this.ypoints[3]) / 2;
                    boolean visible = false;
                    if (n16 >= 0 && n16 < this.winSize.width && n17 <= this.shadowBufferTop[n16] && n17 >= 0) {
                        visible = true;
                    }
                    gridElement2.visible = visible;
                    if (b2) {
                        break;
                    }
                    n10 += n7;
                }
                if (!b2) {
                    for (int n18 = 0; n18 != this.winSize.width; ++n18) {
                        this.shadowBufferTop[n18] = this.shadowBufferTop2[n18];
                        this.shadowBufferBottom[n18] = this.shadowBufferBottom2[n18];
                    }
                }
            }
            if (!b2) {
                break;
            }
            for (int n19 = 0; n19 != this.winSize.width; ++n19) {
                this.shadowBufferTop[n19] = this.shadowBufferTop2[n19];
                this.shadowBufferBottom[n19] = this.shadowBufferBottom2[n19];
            }
        }
        this.drawFloor();
        final boolean b3 = false;
        this.backgroundChanged = b3;
        this.functionChanged = b3;
        if (this.imageSource != null) {
            this.imageSource.newPixels();
        }
    }
    
    void drawFloor() {
        switch (this.floorLineChooser.getSelectedIndex()) {
            case 1: {
                for (int i = 0; i != 80; ++i) {
                    for (int j = 0; j != 80; j += 10) {
                        final double n = i * 0.025 - 1.0;
                        final double n2 = (i + 1) * 0.025 - 1.0;
                        final double n3 = j * 0.025 - 1.0;
                        if (this.grid[i][j].visible) {
                            this.map3d(n, n3, this.grid[i][j].height, this.xpoints, this.ypoints, 0);
                            this.map3d(n2, n3, this.grid[i + 1][j].height, this.xpoints, this.ypoints, 1);
                            this.drawLine(this.xpoints[0], this.ypoints[0], this.xpoints[1], this.ypoints[1]);
                        }
                        if (this.grid[j][i].visible) {
                            this.map3d(n3, n, this.grid[j][i].height, this.xpoints, this.ypoints, 0);
                            this.map3d(n3, n2, this.grid[j][i + 1].height, this.xpoints, this.ypoints, 1);
                            this.drawLine(this.xpoints[0], this.ypoints[0], this.xpoints[1], this.ypoints[1]);
                        }
                    }
                }
                break;
            }
            case 2: {
                if (!this.curfunc.nonGradient()) {
                    this.renderEquips();
                    break;
                }
                break;
            }
            case 3: {
                this.genLines();
                break;
            }
        }
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
            if (this.shadowBufferTop2[i] > j) {
                this.shadowBufferTop2[i] = j;
            }
            if (this.shadowBufferBottom2[i] < interp) {
                this.shadowBufferBottom2[i] = interp;
            }
            final int n9 = this.shadowBufferTop[i];
            final int n10 = this.shadowBufferBottom[i];
            if (j < n9 || interp > n10) {
                while (j <= interp) {
                    if (j < n9 || j > n10) {
                        this.pixels[i + j * this.winSize.width] = n6;
                    }
                    ++j;
                }
            }
            i += n7;
            if (i < 0 || i >= this.winSize.width) {
                return;
            }
        }
    }
    
    void fillRectangle(final int n, final int n2, final int n3, final int n4, final int n5) {
        for (int i = n2; i < n4; ++i) {
            for (int j = n; j < n3; ++j) {
                this.pixels[j + i * this.winSize.width] = n5;
            }
        }
    }
    
    void drawLine(final int n, final int n2, final int n3, final int n4) {
        if (n == n3 && n2 == n4) {
            return;
        }
        if (this.abs(n4 - n2) > this.abs(n3 - n)) {
            for (int sign = this.sign(n4 - n2), i = n2; i != n4 + sign; i += sign) {
                final int n5 = n + (n3 - n) * (i - n2) / (n4 - n2);
                if (n5 >= 0 && i >= 0 && n5 < this.winSize.width && i < this.winSize.height) {
                    this.pixels[n5 + i * this.winSize.width] = -4144960;
                }
            }
        }
        else {
            for (int sign2 = this.sign(n3 - n), j = n; j != n3 + sign2; j += sign2) {
                final int n6 = n2 + (n4 - n2) * (j - n) / (n3 - n);
                if (j >= 0 && n6 >= 0 && j < this.winSize.width && n6 < this.winSize.height) {
                    this.pixels[j + n6 * this.winSize.width] = -4144960;
                }
            }
        }
    }
    
    int abs(final int n) {
        return (n < 0) ? (-n) : n;
    }
    
    int sign(final int n) {
        return (n < 0) ? -1 : ((n == 0) ? 0 : 1);
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
    
    void renderEquips() {
        for (int i = 0; i != 80; ++i) {
            for (int j = 0; j != 80; ++j) {
                if (this.grid[i][j].visible) {
                    this.tryEdge(i, j, i + 1, j, i, j + 1, i + 1, j + 1);
                    this.tryEdge(i, j, i + 1, j, i, j, i, j + 1);
                    this.tryEdge(i, j, i + 1, j, i + 1, j, i + 1, j + 1);
                    this.tryEdge(i, j, i, j + 1, i + 1, j, i + 1, j + 1);
                    this.tryEdge(i, j, i, j + 1, i, j + 1, i + 1, j + 1);
                    this.tryEdge(i + 1, j, i + 1, j + 1, i, j + 1, i + 1, j + 1);
                }
            }
        }
    }
    
    void interpPoint(final GridElement gridElement, final GridElement gridElement2, final int n, final int n2, final int n3, final int n4, final double n5, final FloatPair floatPair) {
        final double n6 = (n5 - gridElement.height) / (gridElement2.height - gridElement.height);
        final double n7 = 1.0 - n6;
        floatPair.x = (n * n7 + n3 * n6) * 2.0 / 80.0 - 1.0;
        floatPair.y = (n2 * n7 + n4 * n6) * 2.0 / 80.0 - 1.0;
    }
    
    boolean spanning(final GridElement gridElement, final GridElement gridElement2, final double n) {
        return gridElement.height != gridElement2.height && (gridElement.height >= n || gridElement2.height >= n) && (gridElement.height <= n || gridElement2.height <= n);
    }
    
    void tryEdge(final int n, final int n2, final int n3, final int n4, final int n5, final int n6, final int n7, final int n8) {
        final double n9 = 1.0 / (40.0 * 5.0 * 0.1);
        final GridElement gridElement = this.grid[n][n2];
        final GridElement gridElement2 = this.grid[n3][n4];
        final GridElement gridElement3 = this.grid[n5][n6];
        final GridElement gridElement4 = this.grid[n7][n8];
        double min = this.min(this.min(gridElement.height, gridElement2.height), this.min(gridElement3.height, gridElement4.height));
        double max = this.max(this.max(gridElement.height, gridElement2.height), this.max(gridElement3.height, gridElement4.height));
        if (min < -5.0) {
            min = -5.0;
        }
        if (max > 5.0) {
            max = 5.0;
        }
        final int n10 = (int)(min / n9);
        for (int n11 = (int)(max / n9), i = n10; i <= n11; ++i) {
            final double n12 = i * n9;
            if (this.spanning(gridElement, gridElement2, n12)) {
                if (this.spanning(gridElement3, gridElement4, n12)) {
                    final FloatPair floatPair = new FloatPair();
                    final FloatPair floatPair2 = new FloatPair();
                    this.interpPoint(gridElement, gridElement2, n, n2, n3, n4, n12, floatPair);
                    this.interpPoint(gridElement3, gridElement4, n5, n6, n7, n8, n12, floatPair2);
                    this.map3d(floatPair.x, floatPair.y, n12, this.xpoints, this.ypoints, 0);
                    this.map3d(floatPair2.x, floatPair2.y, n12, this.xpoints, this.ypoints, 1);
                    this.drawLine(this.xpoints[0], this.ypoints[0], this.xpoints[1], this.ypoints[1]);
                }
            }
        }
    }
    
    void drawLineBackground(final Graphics graphics) {
        for (int i = 0; i != 80; ++i) {
            for (int j = 0; j != 80; ++j) {
                this.grid[i][j].visible = true;
            }
        }
        if (this.isFlat) {
            return;
        }
        for (int k = 79; k >= 0; --k) {
            for (int l = 0; l < 80; l += 5) {
                final double n = k * 0.025 - 1.0;
                final double n2 = (l + 1) * 0.025 - 1.0;
                final double n3 = (k + 1) * 0.025 - 1.0;
                this.map3d(n2, n, this.grid[l][k].height, this.xpoints, this.ypoints, 1);
                this.map3d(n2, n3, this.grid[l][k + 1].height, this.xpoints, this.ypoints, 2);
                this.ypoints[1] = this.bound_y(this.ypoints[1]);
                this.ypoints[2] = this.bound_y(this.ypoints[2]);
                graphics.drawLine(this.xpoints[1], this.ypoints[1], this.xpoints[2], this.ypoints[2]);
            }
        }
        for (int n4 = 0; n4 < 80; n4 += 5) {
            for (int n5 = 79; n5 >= 0; --n5) {
                final double n6 = n5 * 0.025 - 1.0;
                final double n7 = (n5 + 1) * 0.025 - 1.0;
                final double n8 = (n4 + 1) * 0.025 - 1.0;
                this.map3d(n6, n8, this.grid[n5][n4].height, this.xpoints, this.ypoints, 3);
                this.map3d(n7, n8, this.grid[n5 + 1][n4].height, this.xpoints, this.ypoints, 2);
                this.ypoints[3] = this.bound_y(this.ypoints[3]);
                this.ypoints[2] = this.bound_y(this.ypoints[2]);
                graphics.drawLine(this.xpoints[3], this.ypoints[3], this.xpoints[2], this.ypoints[2]);
            }
        }
    }
    
    int bound_y(int n) {
        if (n < -100) {
            n = -100;
        }
        if (n > this.winSize.height + 100) {
            n = this.winSize.height + 100;
        }
        return n;
    }
    
    public void paint(final Graphics graphics) {
        this.cv.repaint();
    }
    
    void map3d(final double n, final double n2, final double n3, final int[] array, final int[] array2, final int n4) {
        this.map3d(n, n2, n3, array, array2, n4, this.viewMain);
    }
    
    void map3d(final double n, final double n2, double n3, final int[] array, final int[] array2, final int n4, final Rectangle rectangle) {
        if (this.isFlat) {
            array[n4] = rectangle.x + (int)((n + 1.0) * rectangle.width / 2.0);
            array2[n4] = rectangle.y + (int)((1.0 - n2) * rectangle.height / 2.0);
            return;
        }
        if (n3 < -1000.0) {
            n3 = -1000.0;
        }
        if (n3 > 1000.0) {
            n3 = 1000.0;
        }
        final double n5 = n * this.viewAngleCos + n2 * this.viewAngleSin;
        final double n6 = n3 - this.viewHeight;
        final double n7 = n2 * this.viewAngleCos - n * this.viewAngleSin + this.viewDistance;
        this.scalex = this.viewZoom * (rectangle.width / 4) * this.viewDistance;
        this.scaley = this.scalex;
        final int n8 = (int)(this.scaley * (this.viewHeight - this.curfunc.getLevelHeight()) / this.viewDistance);
        array[n4] = rectangle.x + rectangle.width / 2 + (int)(this.scalex * n5 / n7);
        array2[n4] = rectangle.y + rectangle.height / 2 - n8 - (int)(this.scaley * n6 / n7);
    }
    
    void scaleworld() {
    }
    
    double getHeight(double n, double n2) {
        n = (n + 1.0) * 40.0;
        n2 = (n2 + 1.0) * 40.0;
        final int n3 = (int)n;
        final int n4 = (int)n2;
        if (n3 >= 80 || n4 >= 80) {
            return this.grid[n3][n4].height;
        }
        final double n5 = n - n3;
        final double n6 = n2 - n4;
        return this.grid[n3][n4].height * (1.0 - n5) * (1.0 - n6) + this.grid[n3 + 1][n4].height * n5 * (1.0 - n6) + this.grid[n3][n4 + 1].height * (1.0 - n5) * n6 + this.grid[n3 + 1][n4 + 1].height * n5 * n6;
    }
    
    void sayCalculating(final Graphics graphics) {
        graphics.setColor(this.cv.getBackground());
        final FontMetrics fontMetrics = graphics.getFontMetrics();
        final String s = "Calculating...";
        graphics.fillRect(0, this.winSize.height - 30, 20 + fontMetrics.stringWidth(s), 30);
        graphics.setColor(Color.white);
        graphics.drawString(s, 10, this.winSize.height - 10);
    }
    
    public void updateVecDemo(final Graphics graphics) {
        final Graphics graphics2 = this.dbimage.getGraphics();
        if (this.winSize == null || this.winSize.width == 0) {
            return;
        }
        if (this.xpoints == null) {
            return;
        }
        this.checkFlatState();
        final double exp = Math.exp((this.strengthBar.getValue() - 50) / 10.0);
        this.fieldStrength = exp;
        this.barFieldStrength = exp;
        if (this.functionChanged || this.backgroundChanged) {
            if (this.functionChanged) {
                this.sayCalculating(graphics);
                this.generateFunction();
            }
            if (!this.slowDragView || !this.draggingView) {
                final long currentTimeMillis = System.currentTimeMillis();
                this.sayCalculating(graphics);
                this.drawBackground();
                this.slowDragView = (System.currentTimeMillis() - currentTimeMillis > 40L);
            }
        }
        this.scaleworld();
        if ((this.draggingView && this.slowDragView) || this.functionChanged) {
            graphics2.setColor(this.isFlat ? this.fieldColors[0] : this.cv.getBackground());
            graphics2.fillRect(0, 0, this.winSize.width, this.winSize.height);
            graphics2.setColor(this.cv.getForeground());
            this.drawLineBackground(graphics2);
        }
        else {
            graphics2.drawImage(this.backimage, 0, 0, this);
        }
        boolean b = true;
        this.curfunc.setupFrame();
        this.fieldStrength = this.barFieldStrength;
        this.partMult = this.fieldStrength * this.reverse * this.timeStep;
        final int selectedIndex = this.dispChooser.getSelectedIndex();
        this.timeStep = 1.0;
        if (!this.stoppedCheck.getState()) {
            if (this.lastTime > 0L) {
                this.timeStep = (System.currentTimeMillis() - this.lastTime) * 0.03;
            }
            if (this.timeStep > 3.0) {
                this.timeStep = 3.0;
            }
            this.lastTime = System.currentTimeMillis();
            if (selectedIndex != 2 && selectedIndex != 3) {
                this.moveParticles();
                b = false;
            }
            this.currentStep += this.reverse;
            if (this.currentStep < 0) {
                this.currentStep += 800;
            }
        }
        else {
            this.lastTime = 0L;
        }
        if (selectedIndex == 2) {
            this.drawVectors(graphics2);
        }
        else if (selectedIndex != 3) {
            this.drawParticles(graphics2);
        }
        graphics2.setColor(Color.gray);
        if (!this.isFlat) {
            this.drawAxes(graphics2);
        }
        this.curfunc.finishFrame();
        final int selectedIndex2 = this.modeChooser.getSelectedIndex();
        if (selectedIndex2 == 2) {
            this.lineIntegral(graphics2, true);
        }
        else if (selectedIndex2 == 3) {
            this.lineIntegral(graphics2, false);
        }
        if (this.parseError) {
            this.centerString(graphics2, "Can't parse expression", this.winSize.height - 20);
        }
        graphics.drawImage(this.dbimage, 0, 0, this);
        final long currentTimeMillis2 = System.currentTimeMillis();
        ++VecDemoFrame.frames;
        if (VecDemoFrame.firsttime == 0L) {
            VecDemoFrame.firsttime = currentTimeMillis2;
        }
        else if (currentTimeMillis2 - VecDemoFrame.firsttime > 1000L) {
            VecDemoFrame.framerate = VecDemoFrame.frames;
            VecDemoFrame.firsttime = currentTimeMillis2;
            VecDemoFrame.frames = 0;
        }
        if (!this.stoppedCheck.getState() && !b) {
            this.cv.repaint(this.pause);
        }
    }
    
    void drawAxes(final Graphics graphics) {
        graphics.setColor(Color.white);
        this.map3d(0.0, 0.0, 0.0, this.xpoints, this.ypoints, 0, this.viewAxes);
        this.map3d(1.0, 0.0, 0.0, this.xpoints, this.ypoints, 1, this.viewAxes);
        this.drawArrow(graphics, "x", this.xpoints[0], this.ypoints[0], this.xpoints[1], this.ypoints[1]);
        this.map3d(0.0, 1.0, 0.0, this.xpoints, this.ypoints, 1, this.viewAxes);
        this.drawArrow(graphics, "y", this.xpoints[0], this.ypoints[0], this.xpoints[1], this.ypoints[1]);
        this.map3d(0.0, 0.0, 1.0, this.xpoints, this.ypoints, 1, this.viewAxes);
        this.drawArrow(graphics, "z", this.xpoints[0], this.ypoints[0], this.xpoints[1], this.ypoints[1]);
    }
    
    void drawVectors(final Graphics g) {
        final DrawData drawData = new DrawData();
        drawData.mult = this.barFieldStrength * 40.0;
        drawData.g = g;
        drawData.field = new double[3];
        drawData.vv = new double[3];
        this.vectorSpacing = this.vecDensityBar.getValue();
        final double[] array = new double[3];
        this.vecCount = 0;
        for (int i = 0; i != this.vectorSpacing; ++i) {
            array[0] = i * (2.0 / (this.vectorSpacing - 1)) - 1.0;
            for (int j = 0; j != this.vectorSpacing; ++j) {
                array[1] = j * (2.0 / (this.vectorSpacing - 1)) - 1.0;
                this.drawVector(drawData, array);
            }
        }
    }
    
    void lineIntegral(final Graphics graphics, final boolean b) {
        if (this.integralX == -1) {
            return;
        }
        if (this.dragStartX == this.integralX || this.dragStartY == this.integralY) {
            return;
        }
        final int min = this.min(this.dragStartX, this.integralX);
        final int min2 = this.min(this.dragStartY, this.integralY);
        final int max = this.max(this.dragStartX, this.integralX);
        final int max2 = this.max(this.dragStartY, this.integralY);
        final int n = 15;
        final double[] rk_k2 = this.rk_k2;
        if (!b) {
            graphics.setColor(Color.white);
            graphics.drawRect(min, min2, max - min + 1, max2 - min2 + 1);
        }
        final double n2 = 1.0 - 2.0 * min2 / this.winSize.height;
        final double n3 = 1.0 - 2.0 * max2 / this.winSize.height;
        for (int i = min; i <= max; i += n) {
            int n4 = max - i;
            if (n4 > n) {
                n4 = n;
            }
            rk_k2[0] = 2.0 * i / this.winSize.width - 1.0;
            rk_k2[1] = n2;
            this.lineIntegralStep(graphics, i, min2, rk_k2, n4, 0, b);
            rk_k2[1] = n3;
            this.lineIntegralStep(graphics, i + n4, max2, rk_k2, -n4, 0, b);
        }
        final double n5 = 2.0 * min / this.winSize.width - 1.0;
        final double n6 = 2.0 * max / this.winSize.width - 1.0;
        for (int j = max2; j >= min2; j -= n) {
            int n7 = j - min2;
            if (n7 > n) {
                n7 = n;
            }
            rk_k2[0] = n5;
            rk_k2[1] = 1.0 - 2.0 * j / this.winSize.height;
            this.lineIntegralStep(graphics, min, j, rk_k2, 0, n7, b);
            rk_k2[0] = n6;
            this.lineIntegralStep(graphics, max, j - n7, rk_k2, 0, -n7, b);
        }
        this.boundCheck = false;
        rk_k2[1] = n2;
        final double numIntegrate = this.numIntegrate(rk_k2, 0, n5, n6, b);
        rk_k2[1] = n3;
        final double numIntegrate2 = this.numIntegrate(rk_k2, 0, n5, n6, b);
        rk_k2[0] = n5;
        final double numIntegrate3 = this.numIntegrate(rk_k2, 1, n2, n3, b);
        rk_k2[0] = n6;
        double n8 = -numIntegrate + numIntegrate2 + numIntegrate3 - this.numIntegrate(rk_k2, 1, n2, n3, b);
        final NumberFormat instance = NumberFormat.getInstance();
        instance.setMaximumFractionDigits(3);
        if (n8 < 1.0E-7 && n8 > -1.0E-7) {
            n8 = 0.0;
        }
        final String string = (b ? "Circulation = " : "Flux = ") + instance.format(n8 * this.reverse * 100000.0);
        graphics.setColor(this.cv.getBackground());
        graphics.fillRect(0, this.winSize.height - 30, 20 + graphics.getFontMetrics().stringWidth(string), 30);
        graphics.setColor(Color.white);
        graphics.drawString(string, 10, this.winSize.height - 10);
    }
    
    double numIntegrate(final double[] array, final int n, final double n2, final double n3, final boolean b) {
        int i = 8;
        double n4 = 0.0;
        final int n5 = b ? n : (1 - n);
        double n8;
        do {
            final double n6 = (n3 - n2) / i;
            double n7 = 0.0;
            for (int j = 0; j <= i; ++j) {
                array[n] = n2 + j * n6;
                final double[] rk_k1 = this.rk_k1;
                this.curfunc.getField(rk_k1, array);
                n7 += rk_k1[n5] * n6 * ((j == 0 || j == i) ? 1 : (((j & 0x1) == 0x1) ? 4 : 2));
            }
            n8 = n7 / 3.0;
            if (Math.abs(n4 - n8) < 1.0E-7) {
                break;
            }
            n4 = n8;
            i *= 2;
        } while (i != 65536);
        if (!b && n == 0) {
            n8 = -n8;
        }
        return n8;
    }
    
    void lineIntegralStep(final Graphics graphics, int n, int n2, final double[] array, final int n3, final int n4, final boolean b) {
        final double[] rk_k1 = this.rk_k1;
        this.curfunc.getField(rk_k1, array);
        final double n5 = (b ? (rk_k1[0] * n3 + rk_k1[1] * n4) : (rk_k1[0] * n4 - rk_k1[1] * n3)) * this.reverse;
        double abs = Math.abs(n5 * 100.0);
        if (abs > 1.0) {
            abs = 1.0;
        }
        final int n6 = (int)(abs * 128.0 + 127.0);
        final int n7 = (int)(127.0 - abs * 127.0);
        if (!b) {
            n += n3 / 2;
            n2 -= n4 / 2;
        }
        if (n5 == 0.0) {
            graphics.setColor(new Color(n7, n7, n7));
            graphics.drawLine(n, n2, n + n3, n2 - n4);
        }
        else if (n5 > 0.0) {
            graphics.setColor(new Color(n6, n7, n7));
            if (b) {
                this.drawArrow(graphics, null, n, n2, n + n3, n2 - n4);
            }
            else {
                this.drawArrow(graphics, null, n, n2, n + n4, n2 + n3);
            }
        }
        else {
            graphics.setColor(new Color(n7, n6, n7));
            if (b) {
                this.drawArrow(graphics, null, n + n3, n2 - n4, n, n2);
            }
            else {
                this.drawArrow(graphics, null, n, n2, n - n4, n2 - n3);
            }
        }
    }
    
    void genLines() {
        int n = 8;
        if (n < 3) {
            n = 3;
        }
        if (n > 8) {
            n = 8;
        }
        final int n2 = n * 2;
        final int n3 = 30 * n2 * n2;
        final double n4 = 80.0 * this.barFieldStrength;
        this.fieldStrength = 10.0;
        final boolean[][] array = new boolean[n2][n2];
        final double n5 = n2 / 2.0;
        final double[] array2 = new double[3];
        final double[] array3 = new double[3];
        final Particle particle = new Particle();
        particle.lifetime = -1.0;
        particle.stepsize = 10.0;
        int n6 = -1;
        int n7 = 0;
        double n8 = 0.0;
    Label_0235_Outer:
        for (int i = 0; i != n3; ++i) {
            Label_0307: {
                if (particle.lifetime < 0.0) {
                    particle.lifetime = 1.0;
                    particle.stepsize = 10.0;
                    n7 = 0;
                    n8 = 0.0;
                    if (n6 == 1) {
                        for (int j = 0; j != 3; ++j) {
                            particle.pos[j] = array2[j];
                        }
                        n6 = -1;
                        continue Label_0235_Outer;
                    }
                    n6 = 1;
                    int n9 = 0;
                    int n10 = 0;
                    while (true) {
                        while (array[n9][n10]) {
                            if (++n9 < n2) {
                                continue Label_0235_Outer;
                            }
                            n9 = 0;
                            if (++n10 < n2) {
                                continue Label_0235_Outer;
                            }
                            if (n10 == n2) {
                                break Label_0235_Outer;
                            }
                            array[n9][n10] = true;
                            final double n11 = 0.5 / n5;
                            array2[0] = (particle.pos[0] = n9 / n5 - 1.0 + n11);
                            array2[1] = (particle.pos[1] = n10 / n5 - 1.0 + n11);
                            break Label_0307;
                        }
                        continue;
                    }
                }
            }
            final double n12 = particle.pos[0];
            final double n13 = particle.pos[1];
            final double height = this.getHeight(n12, n13);
            if (!this.grid[(int)((n12 + 1.0) * 80.0 / 2.0)][(int)((n13 + 1.0) * 80.0 / 2.0)].visible) {
                particle.lifetime = -1.0;
            }
            else {
                final double[] pos = particle.pos;
                this.lineSegment(particle, n6);
                if (particle.lifetime >= 0.0) {
                    final int n14 = (int)((pos[0] + 1.0) * n5);
                    final int n15 = (int)((pos[1] + 1.0) * n5);
                    if (!array[n14][n15]) {
                        --n7;
                    }
                    array[n14][n15] = true;
                    if (!this.grid[(int)((particle.pos[0] + 1.0) * 80.0 / 2.0)][(int)((particle.pos[1] + 1.0) * 80.0 / 2.0)].visible) {
                        particle.lifetime = -1.0;
                    }
                    else {
                        if (n4 * particle.phi > 2.0) {}
                        this.map3d(n12, n13, height, this.xpoints, this.ypoints, 0);
                        this.map3d(particle.pos[0], particle.pos[1], this.getHeight(particle.pos[0], particle.pos[1]), this.xpoints, this.ypoints, 1);
                        this.drawLine(this.xpoints[0], this.ypoints[0], this.xpoints[1], this.ypoints[1]);
                        final double dist2 = this.dist2(array2, pos);
                        if (dist2 > n8) {
                            n8 = dist2;
                        }
                        else {
                            ++n7;
                        }
                        if (n7 > 10 || dist2 < 0.001) {
                            particle.lifetime = -1.0;
                        }
                    }
                }
            }
        }
    }
    
    void drawVector(final DrawData drawData, final double[] array) {
        final double[] field = drawData.field;
        this.curfunc.getField(field, array);
        final double sqrt = Math.sqrt(field[0] * field[0] + field[1] * field[1]);
        final double n = sqrt * this.reverse;
        if (sqrt > 0.0) {
            final double[] array2 = field;
            final int n2 = 0;
            array2[n2] /= n;
            final double[] array3 = field;
            final int n3 = 1;
            array3[n3] /= n;
        }
        double n4 = sqrt * drawData.mult;
        if (n4 > 2.0) {
            n4 = 2.0;
        }
        final int n5 = (int)(n4 * 255.0);
        final double n6 = 1.0 / (this.vectorSpacing - 1);
        this.map3d(array[0], array[1], 0.0, this.xpoints, this.ypoints, 0);
        this.map3d(array[0] + n6 * field[0], array[1] + n6 * field[1], 0.0, this.xpoints, this.ypoints, 1);
        drawData.g.setColor(this.fieldColors[n5]);
        this.drawArrow(drawData.g, null, this.xpoints[0], this.ypoints[0], this.xpoints[1], this.ypoints[1], 2);
    }
    
    void moveParticles() {
        int n = 0;
        for (int particleCount = this.getParticleCount(), i = 0; i != particleCount; ++i) {
            final Particle particle = this.particles[i];
            this.removeFromDensityGroup(particle);
            this.moveParticle(particle);
            final double[] pos = particle.pos;
            Label_0107: {
                if (pos[0] >= -1.0 && pos[0] < 1.0 && pos[1] >= -1.0 && pos[1] < 1.0) {
                    final Particle particle2 = particle;
                    final double lifetime = particle2.lifetime - this.timeStep;
                    particle2.lifetime = lifetime;
                    if (lifetime >= 0.0) {
                        break Label_0107;
                    }
                }
                this.positionParticle(particle);
            }
            final int addToDensityGroup = this.addToDensityGroup(particle);
            if (addToDensityGroup > n) {
                n = addToDensityGroup;
            }
        }
        final boolean b = this.dispChooser.getSelectedIndex() == 1;
        final int n2 = 6 * this.getParticleCount() / 256;
        if (!b && this.curfunc.redistribute() && n > n2) {
            this.redistribute(n);
        }
    }
    
    void drawParticles(final Graphics graphics) {
        graphics.setColor(Color.white);
        final int selectedIndex = this.dispChooser.getSelectedIndex();
        if (selectedIndex == 2) {
            for (int i = 0; i != this.vecCount; ++i) {
                final FieldVector fieldVector = this.vectors[i];
                graphics.setColor(this.fieldColors[fieldVector.col]);
                this.drawArrow(graphics, null, fieldVector.sx1, fieldVector.sy1, fieldVector.sx2, fieldVector.sy2, 2);
            }
            return;
        }
        int particleCount = this.getParticleCount();
        this.wooft += 0.3;
        if (selectedIndex == 4) {
            particleCount = (particleCount + 4) / 5;
        }
        for (int j = 0; j < particleCount; ++j) {
            final Particle particle = this.particles[j];
            final double[] pos = particle.pos;
            final GridElement gridElement = this.grid[(int)((pos[0] + 1.0) * 80.0 / 2.0)][(int)((pos[1] + 1.0) * 80.0 / 2.0)];
            this.map3d(pos[0], pos[1], this.getHeight(pos[0], pos[1]), this.xpoints, this.ypoints, 0);
            if (this.xpoints[0] >= 0 && this.xpoints[0] < this.winSize.width && this.ypoints[0] >= 0) {
                if (this.ypoints[0] < this.winSize.height) {
                    if (selectedIndex == 4) {
                        graphics.setColor(particle.color);
                        final double n = Math.cos(particle.theta) * 0.02;
                        final double n2 = Math.sin(particle.theta) * 0.02;
                        final double n3 = n;
                        final double n4 = n2;
                        final double curlcalc = this.curlcalc(particle.pos[0] + n3, particle.pos[1] + n4, -n2, n);
                        final double curlcalc2 = this.curlcalc(particle.pos[0] - n4, particle.pos[1] + n3, -n, -n2);
                        final double curlcalc3 = this.curlcalc(particle.pos[0] - n3, particle.pos[1] - n4, n2, -n);
                        final double curlcalc4 = this.curlcalc(particle.pos[0] + n4, particle.pos[1] - n3, n, n2);
                        final Particle particle2 = particle;
                        particle2.theta += (curlcalc + curlcalc2 + curlcalc3 + curlcalc4) / 0.0016;
                        this.map3d(particle.pos[0] - n3, particle.pos[1] - n4, 0.0, this.xpoints, this.ypoints, 0);
                        this.map3d(particle.pos[0] + n3, particle.pos[1] + n4, 0.0, this.xpoints, this.ypoints, 1);
                        this.map3d(particle.pos[0] - n4, particle.pos[1] + n3, 0.0, this.xpoints, this.ypoints, 2);
                        this.map3d(particle.pos[0] + n4, particle.pos[1] - n3, 0.0, this.xpoints, this.ypoints, 3);
                        graphics.drawLine(this.xpoints[0], this.ypoints[0], this.xpoints[1], this.ypoints[1]);
                        graphics.drawLine(this.xpoints[2], this.ypoints[2], this.xpoints[3], this.ypoints[3]);
                        graphics.fillOval(this.xpoints[0] - 1, this.ypoints[0] - 1, 3, 3);
                    }
                    else if (gridElement.visible && gridElement.valid) {
                        graphics.fillRect(this.xpoints[0], this.ypoints[0] - 1, 2, 2);
                    }
                }
            }
        }
    }
    
    void drawPlane(final Graphics graphics, final double n, final double n2, final double n3) {
        graphics.setColor(this.darkYellow);
        this.map3d(-n, -n2, n3, this.xpoints, this.ypoints, 0);
        this.map3d(n, -n2, n3, this.xpoints, this.ypoints, 1);
        this.map3d(n, n2, n3, this.xpoints, this.ypoints, 2);
        this.map3d(-n, n2, n3, this.xpoints, this.ypoints, 3);
        graphics.fillPolygon(this.xpoints, this.ypoints, 4);
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
    
    void redistribute(final int n) {
        if (n < 5) {
            return;
        }
        ++this.rediscount;
        final int n2 = 6 * this.getParticleCount() / 256;
        int n3 = 0;
        for (int particleCount = this.getParticleCount(), i = this.rediscount % 4; i < particleCount; i += 4) {
            final Particle particle = this.particles[i];
            if (this.density[(int)((particle.pos[0] + 1.0) * 8.0)][(int)((particle.pos[1] + 1.0) * 8.0)] > n2) {
                particle.lifetime = -1.0;
                ++n3;
            }
        }
    }
    
    double curlcalc(final double n, final double n2, final double n3, final double n4) {
        this.rk_yn[0] = n;
        this.rk_yn[1] = n2;
        this.curfunc.getField(this.rk_k1, this.rk_yn);
        return this.partMult * (this.rk_k1[0] * n3 + this.rk_k1[1] * n4);
    }
    
    double distance(final Particle particle) {
        return this.distance(particle.pos[0], particle.pos[1]);
    }
    
    double distance(final double[] array) {
        return this.distance(array[0], array[1]);
    }
    
    double distance(final double n, final double n2) {
        return Math.sqrt(n * n + n2 * n2 + 1.0E-9);
    }
    
    void rotateParticleAdd(final double[] array, final double[] array2, final double n, final double n2, final double n3) {
        final int n4 = 0;
        array[n4] += -n * (array2[1] - n3);
        final int n5 = 1;
        array[n5] += n * (array2[0] - n2);
    }
    
    void rotateParticle(final double[] array, final double[] array2, final double n) {
        array[0] = -n * array2[1];
        array[1] = n * array2[0];
        array[2] = 0.0;
    }
    
    void edit(final MouseEvent mouseEvent) {
        this.editView(mouseEvent.getX(), mouseEvent.getY());
    }
    
    void editView(final int integralX, final int integralY) {
        if (this.modeChooser.getSelectedIndex() == 0) {
            if (this.isFlat) {
                return;
            }
            this.viewAngle = (this.dragStartX - integralX) / 40.0 + this.viewAngleDragStart;
            while (this.viewAngle < 0.0) {
                this.viewAngle += 6.283185307179586;
            }
            while (this.viewAngle >= 6.283185307179586) {
                this.viewAngle -= 6.283185307179586;
            }
            this.viewAngleCos = Math.cos(this.viewAngle);
            this.viewAngleSin = Math.sin(this.viewAngle);
            this.viewHeight = -(this.dragStartY - integralY) / 10.0 + this.viewHeightDragStart;
            if (this.viewHeight > 9.0) {
                this.viewHeight = 9.0;
            }
            if (this.viewHeight < -9.0) {
                this.viewHeight = -9.0;
            }
            final boolean b = true;
            this.backgroundChanged = b;
            this.draggingView = b;
            this.cv.repaint(this.pause);
        }
        else {
            if (this.modeChooser.getSelectedIndex() != 1) {
                if (this.modeChooser.getSelectedIndex() == 2 || this.modeChooser.getSelectedIndex() == 3) {
                    this.integralX = integralX;
                    this.integralY = integralY;
                    this.cv.repaint(this.pause);
                }
                return;
            }
            if (this.isFlat) {
                return;
            }
            this.viewZoom = (integralX - this.dragStartX) / 40.0 + this.viewZoomDragStart;
            if (this.viewZoom < 0.1) {
                this.viewZoom = 0.1;
            }
            final boolean b2 = true;
            this.backgroundChanged = b2;
            this.draggingView = b2;
            this.cv.repaint(this.pause);
        }
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
        this.vectors = null;
        if (actionEvent.getSource() == this.resetButton) {
            this.resetParticles();
        }
        if (actionEvent.getSource() == this.kickButton) {
            this.kickParticles();
        }
        if (actionEvent.getSource() == this.infoButton) {
            final String name = this.curfunc.getClass().getName();
            try {
                this.applet.getAppletContext().showDocument(new URL(this.applet.getCodeBase(), "functions.html#" + name.substring(name.lastIndexOf(46) + 1)), "functionHelp");
            }
            catch (Exception ex) {}
        }
        this.curfunc.actionPerformed();
        this.cv.repaint(this.pause);
    }
    
    public boolean handleEvent(final Event event) {
        if (event.id == 201) {
            this.applet.destroyFrame();
            return true;
        }
        return super.handleEvent(event);
    }
    
    public void scrollbarValueChanged(final DecentScrollbar decentScrollbar) {
        this.vectors = null;
        System.out.print(decentScrollbar.getValue() + "\n");
        if (decentScrollbar == this.partCountBar) {
            this.resetDensityGroups();
        }
        if (decentScrollbar == this.aux1Bar || decentScrollbar == this.aux2Bar || decentScrollbar == this.aux3Bar) {
            this.functionChanged = true;
            this.draggingView = true;
        }
        this.cv.repaint(this.pause);
    }
    
    public void scrollbarFinished(final DecentScrollbar decentScrollbar) {
        this.draggingView = false;
        this.cv.repaint(this.pause);
    }
    
    public void mouseDragged(final MouseEvent mouseEvent) {
        this.dragging = true;
        this.edit(mouseEvent);
    }
    
    public void mouseMoved(final MouseEvent mouseEvent) {
    }
    
    public void mouseClicked(final MouseEvent mouseEvent) {
    }
    
    public void mouseEntered(final MouseEvent mouseEvent) {
    }
    
    public void mouseExited(final MouseEvent mouseEvent) {
    }
    
    public void mousePressed(final MouseEvent mouseEvent) {
        if ((mouseEvent.getModifiers() & 0x10) == 0x0) {
            return;
        }
        this.dragStartX = mouseEvent.getX();
        this.dragStartY = mouseEvent.getY();
        this.viewAngleDragStart = this.viewAngle;
        this.viewHeightDragStart = this.viewHeight;
        this.viewZoomDragStart = this.viewZoom;
        this.dragging = true;
        this.edit(mouseEvent);
    }
    
    public void mouseReleased(final MouseEvent mouseEvent) {
        if ((mouseEvent.getModifiers() & 0x10) == 0x0) {
            return;
        }
        final boolean b = false;
        this.draggingView = b;
        this.dragging = b;
        this.cv.repaint(this.pause);
    }
    
    void dispChooserChanged() {
        final int selectedIndex = this.dispChooser.getSelectedIndex();
        this.showA = false;
        if (selectedIndex == 1) {
            this.kickButton.enable();
        }
        else {
            this.kickButton.disable();
        }
        this.potentialLabel.hide();
        this.potentialBar.hide();
        this.vecDensityLabel.hide();
        this.vecDensityBar.hide();
        this.partCountLabel.hide();
        this.partCountBar.hide();
        this.strengthLabel.show();
        this.strengthBar.show();
        if (selectedIndex == 2) {
            this.vecDensityLabel.show();
            this.vecDensityBar.show();
        }
        else {
            this.partCountLabel.show();
            this.partCountBar.show();
        }
        this.validate();
        this.resetParticles();
    }
    
    public void itemStateChanged(final ItemEvent itemEvent) {
        this.vectors = null;
        this.cv.repaint(this.pause);
        this.reverse = (this.reverseCheck.getState() ? -1 : 1);
        if (itemEvent.getItemSelectable() == this.dispChooser) {
            this.dispChooserChanged();
            this.resetParticles();
        }
        if (itemEvent.getItemSelectable() == this.functionChooser) {
            this.functionChanged();
        }
        if (itemEvent.getItemSelectable() == this.reverseCheck) {
            this.functionChanged = true;
        }
        if (itemEvent.getItemSelectable() == this.floorColorChooser || itemEvent.getItemSelectable() == this.floorLineChooser) {
            this.backgroundChanged = true;
        }
    }
    
    void checkFlatState() {
        final boolean isFlat = this.isFlat;
        final int selectedIndex = this.dispChooser.getSelectedIndex();
        this.isFlat = (this.flatCheck.getState() || this.curfunc.nonGradient() || selectedIndex == 2 || selectedIndex == 4);
        final int selectedIndex2 = this.modeChooser.getSelectedIndex();
        if (selectedIndex2 == 2 || selectedIndex2 == 3) {
            this.isFlat = true;
        }
        if (this.isFlat != isFlat) {
            this.backgroundChanged = true;
        }
    }
    
    void functionChanged() {
        this.reverse = 1;
        this.reverseCheck.setState(false);
        this.parseError = false;
        this.curfunc = this.functionList.elementAt(this.functionChooser.getSelectedIndex());
        for (int i = 0; i != 3; ++i) {
            this.auxBars[i].label.hide();
            this.auxBars[i].bar.hide();
        }
        for (int j = 0; j != 2; ++j) {
            this.textFields[j].hide();
        }
        if (this.textFieldLabel != null) {
            this.textFieldLabel.hide();
        }
        this.strengthBar.setValue(80);
        this.curfunc.setup();
        this.validate();
        this.resetParticles();
        this.dispChooserChanged();
        this.functionChanged = true;
        this.integralX = -1;
    }
    
    void setupDispChooser(final boolean b) {
        this.dispChooser.removeAll();
        this.dispChooser.add("Display: Particles (Vel.)");
        this.dispChooser.add("Display: Particles (Force)");
        this.dispChooser.add("Display: Field Vectors");
        this.dispChooser.add("Display: None");
        this.dispChooser.add("Display: Curl Detectors");
    }
    
    void setupBar(final int n, final String text, final int value) {
        this.auxBars[n].label.setText(text);
        this.auxBars[n].label.show();
        this.auxBars[n].bar.setValue(value);
        this.auxBars[n].bar.show();
    }
    
    void cross(final double[] array, final double[] array2, final double[] array3) {
        array[0] = array2[1] * array3[2] - array2[2] * array3[1];
        array[1] = array2[2] * array3[0] - array2[0] * array3[2];
        array[2] = array2[0] * array3[1] - array2[1] * array3[0];
    }
    
    double dot(final double[] array, final double[] array2) {
        return array[0] * array2[0] + array[1] * array2[1];
    }
    
    void rk(final int n, final double n2, final double[] array, final double n3) {
        if (n == 2) {
            final double n4 = n3 * this.partMult;
            for (int i = 0; i != n; ++i) {
                this.rk_yn[i] = array[i];
            }
            this.curfunc.getField(this.rk_k1, this.rk_yn);
            for (int j = 0; j != n; ++j) {
                this.rk_yn[j] = array[j] + 0.5 * n4 * this.rk_k1[j];
            }
            this.curfunc.getField(this.rk_k2, this.rk_yn);
            for (int k = 0; k != n; ++k) {
                this.rk_yn[k] = array[k] + 0.5 * n4 * this.rk_k2[k];
            }
            this.curfunc.getField(this.rk_k3, this.rk_yn);
            for (int l = 0; l != n; ++l) {
                this.rk_yn[l] = array[l] + n4 * this.rk_k3[l];
            }
            this.curfunc.getField(this.rk_k4, this.rk_yn);
            for (int n5 = 0; n5 != n; ++n5) {
                array[n5] += n4 * (this.rk_k1[n5] + 2.0 * (this.rk_k2[n5] + this.rk_k3[n5]) + this.rk_k4[n5]) / 6.0;
            }
            array[2] = this.rk_k4[2];
        }
        else {
            final double n6 = n3 * this.partMult;
            for (int n7 = 0; n7 != n; ++n7) {
                this.rk_yn[n7] = array[n7];
            }
            this.getForceField(this.rk_k1, this.rk_yn, n3, n6);
            for (int n8 = 0; n8 != n; ++n8) {
                this.rk_yn[n8] = array[n8] + 0.5 * this.rk_k1[n8];
            }
            this.getForceField(this.rk_k2, this.rk_yn, n3, n6);
            for (int n9 = 0; n9 != n; ++n9) {
                this.rk_yn[n9] = array[n9] + 0.5 * this.rk_k2[n9];
            }
            this.getForceField(this.rk_k3, this.rk_yn, n3, n6);
            for (int n10 = 0; n10 != n; ++n10) {
                this.rk_yn[n10] = array[n10] + this.rk_k3[n10];
            }
            this.getForceField(this.rk_k4, this.rk_yn, n3, n6);
            for (int n11 = 0; n11 != n; ++n11) {
                array[n11] += (this.rk_k1[n11] + 2.0 * (this.rk_k2[n11] + this.rk_k3[n11]) + this.rk_k4[n11]) / 6.0;
            }
            array[4] = this.rk_k4[4];
        }
    }
    
    void getForceField(final double[] array, final double[] array2, final double n, final double n2) {
        this.curfunc.getField(array, array2);
        array[4] = array[2];
        for (int i = 0; i != 2; ++i) {
            array[i + 2] = n2 * array[i] * 0.1;
        }
        for (int j = 0; j != 2; ++j) {
            array[j] = n * this.timeStep * this.rk_yn[j + 2];
        }
    }
    
    void moveParticle(final Particle particle) {
        final int selectedIndex = this.dispChooser.getSelectedIndex();
        final double n = 1.0;
        final double n2 = 0.001;
        final boolean b = selectedIndex == 1;
        final int n3 = b ? 4 : 2;
        final double[] rk_Y = this.rk_Y;
        final double[] rk_Yhalf = this.rk_Yhalf;
        this.oldY = this.rk_oldY;
        for (int i = 0; i != 2; ++i) {
            final double[] oldY = this.oldY;
            final int n4 = i;
            final double[] array = rk_Y;
            final int n5 = i;
            final double[] array2 = rk_Yhalf;
            final int n6 = i;
            final double n7 = particle.pos[i];
            array2[n6] = n7;
            oldY[n4] = (array[n5] = n7);
        }
        if (b) {
            for (int j = 0; j != 2; ++j) {
                rk_Y[j + 2] = (rk_Yhalf[j + 2] = particle.vel[j]);
            }
        }
        double n8 = 0.0;
        if (!this.curfunc.useRungeKutta()) {
            this.boundCheck = false;
            this.curfunc.getField(rk_Yhalf, rk_Y);
            if (this.boundCheck && (!b || this.curfunc.checkBoundsWithForce())) {
                particle.pos[0] = -100.0;
                return;
            }
            final double partMult = this.partMult;
            if (b) {
                final double n9 = partMult * 0.1;
                for (int k = 0; k != 2; ++k) {
                    final double[] vel = particle.vel;
                    final int n10 = k;
                    vel[n10] += n9 * rk_Yhalf[k];
                    final double[] pos = particle.pos;
                    final int n11 = k;
                    pos[n11] += particle.vel[k] * this.timeStep;
                }
            }
            else {
                for (int l = 0; l != 2; ++l) {
                    final double[] pos2 = particle.pos;
                    final int n12 = l;
                    pos2[n12] += partMult * rk_Yhalf[l];
                }
            }
            particle.pos[2] = rk_Yhalf[2];
            for (int n13 = 0; n13 != 2; ++n13) {
                rk_Y[n13] = particle.pos[n13];
            }
            if (this.curfunc.checkBounds(rk_Y, this.oldY)) {
                particle.pos[0] = -100.0;
            }
        }
        else {
            final boolean useAdaptiveRungeKutta = this.curfunc.useAdaptiveRungeKutta();
            double stepsize = useAdaptiveRungeKutta ? particle.stepsize : 1.0;
            int n14 = 0;
            final double n15 = 1.0E-4;
            while (n8 >= 0.0 && n8 < 1.0) {
                if (n8 + stepsize > 1.0) {
                    stepsize = 1.0 - n8;
                }
                this.boundCheck = false;
                this.rk(n3, 0.0, rk_Y, stepsize);
                if (!useAdaptiveRungeKutta) {
                    break;
                }
                this.rk(n3, 0.0, rk_Yhalf, stepsize * 0.5);
                this.rk(n3, 0.0, rk_Yhalf, stepsize * 0.5);
                if (this.boundCheck && (!b || this.curfunc.checkBoundsWithForce())) {
                    particle.pos[0] = -100.0;
                    return;
                }
                final double n16 = Math.abs(rk_Y[0] - rk_Yhalf[0]) + Math.abs(rk_Y[1] - rk_Yhalf[1]);
                if (n16 > n2 && stepsize > n15) {
                    stepsize *= 0.75;
                    if (stepsize < n15) {
                        stepsize = n15;
                    }
                    for (int n17 = 0; n17 != n3; ++n17) {
                        rk_Y[n17] = (rk_Yhalf[n17] = this.oldY[n17]);
                    }
                }
                else {
                    if (n16 < n2 * 0.5) {
                        stepsize *= 1.25;
                        if (stepsize > n) {
                            stepsize = n;
                        }
                    }
                    for (int n18 = 0; n18 != n3; ++n18) {
                        this.oldY[n18] = (rk_Yhalf[n18] = rk_Y[n18]);
                    }
                    n8 += stepsize;
                    ++n14;
                }
            }
            if (this.boundCheck && (!b || this.curfunc.checkBoundsWithForce())) {
                particle.pos[0] = -100.0;
                return;
            }
            particle.stepsize = stepsize;
            for (int n19 = 0; n19 != 3; ++n19) {
                particle.pos[n19] = rk_Y[n19];
            }
            if (b) {
                for (int n20 = 0; n20 != 2; ++n20) {
                    particle.vel[n20] = rk_Y[n20 + 2];
                }
                particle.pos[2] = rk_Y[4];
            }
        }
    }
    
    double dist2(final double[] array, final double[] array2) {
        final double n = array[0] - array2[0];
        final double n2 = array[1] - array2[1];
        return n * n + n2 * n2;
    }
    
    void lineSegment(final Particle particle, final int n) {
        final double n2 = 20.0;
        final double n3 = 0.001;
        final int n4 = 2;
        final double[] rk_Y = this.rk_Y;
        final double[] rk_Yhalf = this.rk_Yhalf;
        this.oldY = this.rk_oldY;
        for (int i = 0; i != 2; ++i) {
            final double[] oldY = this.oldY;
            final int n5 = i;
            final double[] array = rk_Y;
            final int n6 = i;
            final double[] array2 = rk_Yhalf;
            final int n7 = i;
            final double n8 = particle.pos[i];
            array2[n7] = n8;
            oldY[n5] = (array[n6] = n8);
        }
        double stepsize = particle.stepsize;
        final double[] ls_fieldavg = this.ls_fieldavg;
        final int n9 = 0;
        final double[] ls_fieldavg2 = this.ls_fieldavg;
        final int n10 = 1;
        final double[] ls_fieldavg3 = this.ls_fieldavg;
        final int n11 = 2;
        final double n12 = 0.0;
        ls_fieldavg3[n11] = n12;
        ls_fieldavg[n9] = (ls_fieldavg2[n10] = n12);
        int n13 = 0;
        final double n14 = 0.1;
        final double n15 = 0.0125;
        final double n16 = n15 / 4.0;
        double n17 = 0.0;
        int n18 = 0;
        while (true) {
            this.boundCheck = false;
            if (++n13 > 100) {
                System.out.print("maxsteps\n");
                particle.lifetime = -1.0;
                return;
            }
            this.rk(n4, 0.0, rk_Y, n * stepsize);
            this.rk(n4, 0.0, rk_Yhalf, n * stepsize * 0.5);
            this.rk(n4, 0.0, rk_Yhalf, n * stepsize * 0.5);
            if (this.boundCheck) {
                for (int j = 0; j != n4; ++j) {
                    rk_Y[j] = (rk_Yhalf[j] = this.oldY[j]);
                }
                stepsize /= 2.0;
                if (stepsize < n14) {
                    particle.lifetime = -1.0;
                    return;
                }
                continue;
            }
            else if (rk_Y[0] < -1.0 || rk_Y[0] >= 0.999 || rk_Y[1] < -1.0 || rk_Y[1] >= 0.999) {
                for (int k = 0; k != n4; ++k) {
                    rk_Y[k] = (rk_Yhalf[k] = this.oldY[k]);
                }
                stepsize /= 2.0;
                if (stepsize < n14) {
                    particle.lifetime = -1.0;
                    return;
                }
                continue;
            }
            else {
                final double n19 = Math.abs(rk_Y[0] - rk_Yhalf[0]) + Math.abs(rk_Y[1] - rk_Yhalf[1]);
                if (n19 > n3 && stepsize > n14) {
                    stepsize *= 0.75;
                    if (stepsize < n14) {
                        stepsize = n14;
                    }
                    for (int l = 0; l != n4; ++l) {
                        rk_Y[l] = (rk_Yhalf[l] = this.oldY[l]);
                    }
                }
                else {
                    if (n19 < n3 * 0.5) {
                        stepsize *= 1.25;
                        if (stepsize > n2) {
                            stepsize = n2;
                        }
                    }
                    final double dist2 = this.dist2(particle.pos, rk_Y);
                    if (dist2 - n17 <= 1.0E-10) {
                        particle.lifetime = -1.0;
                        return;
                    }
                    if (dist2 > n15) {
                        stepsize /= 2.0;
                        if (stepsize < n14) {
                            particle.lifetime = -1.0;
                            return;
                        }
                        for (int n20 = 0; n20 != n4; ++n20) {
                            rk_Y[n20] = (rk_Yhalf[n20] = this.oldY[n20]);
                        }
                    }
                    else {
                        final double[] ls_fieldavg4 = this.ls_fieldavg;
                        final int n21 = 0;
                        ls_fieldavg4[n21] += this.rk_k1[0];
                        final double[] ls_fieldavg5 = this.ls_fieldavg;
                        final int n22 = 1;
                        ls_fieldavg5[n22] += this.rk_k1[1];
                        ++n18;
                        if (dist2 > n16) {
                            particle.stepsize = stepsize;
                            for (int n23 = 0; n23 != 3; ++n23) {
                                particle.pos[n23] = rk_Y[n23];
                            }
                            particle.phi = Math.sqrt(this.ls_fieldavg[0] * this.ls_fieldavg[0] + this.ls_fieldavg[1] * this.ls_fieldavg[1]) / n18;
                            return;
                        }
                        n17 = dist2;
                        for (int n24 = 0; n24 != n4; ++n24) {
                            this.oldY[n24] = (rk_Yhalf[n24] = rk_Y[n24]);
                        }
                    }
                }
            }
        }
    }
    
    int doubleToGrid(final double n) {
        return (int)((n + 1.0) * 80.0 / 2.0);
    }
    
    double gridToDouble(final int n) {
        return n * 2.0 / 80.0 - 1.0;
    }
    
    void getDirectionField(final double[] array, final double[] array2, final double n) {
        final double sin = Math.sin(n);
        final double cos = Math.cos(n);
        if (!this.showA) {
            array[0] = 3.0E-4 * cos;
            array[1] = 3.0E-4 * sin;
            array[2] = -0.4 * (array2[0] * cos + array2[1] * sin);
        }
        else {
            final double[] array3 = { cos, sin, 0.0 };
            final double dot = this.dot(array3, array2);
            final double[] array4 = new double[3];
            for (int i = 0; i != 2; ++i) {
                array4[i] = 6.0E-4 * (array2[i] - array3[i] * dot);
            }
            this.cross(array, array3, array4);
        }
    }
    
    static {
        VecDemoFrame.frames = 0;
        VecDemoFrame.framerate = 0;
        VecDemoFrame.firsttime = 0L;
    }
    
    class AuxBar
    {
        DecentScrollbar bar;
        Label label;
        
        AuxBar(final Label label, final DecentScrollbar bar) {
            this.label = label;
            this.bar = bar;
        }
    }
    
    class FloatPair
    {
        public double x;
        public double y;
    }
    
    abstract class VecFunction
    {
        abstract String getName();
        
        abstract VecFunction createNext();
        
        boolean nonGradient() {
            return false;
        }
        
        boolean useRungeKutta() {
            return true;
        }
        
        boolean useAdaptiveRungeKutta() {
            return true;
        }
        
        boolean checkBoundsWithForce() {
            return true;
        }
        
        boolean checkBounds(final double[] array, final double[] array2) {
            return false;
        }
        
        abstract void getField(final double[] p0, final double[] p1);
        
        boolean redistribute() {
            return true;
        }
        
        void setup() {
        }
        
        void setupFrame() {
        }
        
        void finishFrame() {
        }
        
        void actionPerformed() {
        }
        
        void calcDivergence() {
        }
        
        double getLevelHeight() {
            return 0.0;
        }
        
        void setGrid(final GridElement gridElement, final int n, final int n2) {
            final double[] rk_k1 = VecDemoFrame.this.rk_k1;
            final double[] rk_k2 = VecDemoFrame.this.rk_k2;
            final double[] rk_k3 = VecDemoFrame.this.rk_k3;
            rk_k1[0] = n * 2.0 / 80.0 - 1.0;
            rk_k1[1] = n2 * 2.0 / 80.0 - 1.0;
            rk_k1[2] = 0.0;
            VecDemoFrame.this.boundCheck = false;
            this.getField(rk_k2, rk_k1);
            gridElement.vecX = VecDemoFrame.this.reverse * rk_k2[0] * 70.0;
            gridElement.vecY = VecDemoFrame.this.reverse * rk_k2[1] * 70.0;
            gridElement.height = VecDemoFrame.this.reverse * rk_k2[2] * 0.625;
            gridElement.valid = !VecDemoFrame.this.boundCheck;
            final double n3 = rk_k1[0];
            final double[] array = rk_k1;
            final int n4 = 0;
            array[n4] += 1.0E-8;
            this.getField(rk_k3, rk_k1);
            gridElement.div = rk_k3[0] - rk_k2[0];
            gridElement.curl = rk_k3[1] - rk_k2[1];
            rk_k1[0] = n3;
            final double[] array2 = rk_k1;
            final int n5 = 1;
            array2[n5] += 1.0E-8;
            this.getField(rk_k3, rk_k1);
            gridElement.div = (gridElement.div + rk_k3[1] - rk_k2[1]) * 1.0E10 * VecDemoFrame.this.reverse;
            gridElement.curl = (gridElement.curl - (rk_k3[0] - rk_k2[0])) * 1.0E10 * VecDemoFrame.this.reverse;
        }
        
        double getDivOffset() {
            return 4.0;
        }
        
        double getDivRange() {
            return 11.0;
        }
    }
    
    class InverseRadial extends VecFunction
    {
        double lineLen;
        
        String getName() {
            return "1/r single line";
        }
        
        void getField(final double[] array, final double[] array2) {
            final double distance = VecDemoFrame.this.distance(array2[0], array2[1]);
            if (distance < 0.001) {
                VecDemoFrame.this.boundCheck = true;
            }
            final double n = distance * distance;
            array[0] = -2.0E-4 * array2[0] / n;
            array[1] = -2.0E-4 * array2[1] / n;
            array[2] = 0.4 * Math.log(distance + 1.0E-300);
        }
        
        void setup() {
            this.lineLen = 1.0;
        }
        
        VecFunction createNext() {
            return new InverseRadialDouble();
        }
    }
    
    class InverseRadialDouble extends VecFunction
    {
        double sign;
        
        InverseRadialDouble() {
            this.sign = 1.0;
        }
        
        String getName() {
            return "1/r double lines";
        }
        
        void getField(final double[] array, final double[] array2) {
            final double gridToDouble = VecDemoFrame.this.gridToDouble(40 + VecDemoFrame.this.aux1Bar.getValue() * 80 / 200);
            final double n = array2[0] - gridToDouble;
            final double n2 = array2[0] + gridToDouble;
            final double distance = VecDemoFrame.this.distance(n, array2[1]);
            final double distance2 = VecDemoFrame.this.distance(n2, array2[1]);
            if (distance < 0.001 || distance2 < 0.001) {
                VecDemoFrame.this.boundCheck = true;
            }
            final double n3 = 2.0E-4;
            final double n4 = 1.0 / (distance * distance);
            final double n5 = 1.0 / (distance2 * distance2 * this.sign);
            array[0] = n3 * (-n * n4 - n2 * n5);
            array[1] = n3 * (-array2[1] * n4 - array2[1] * n5);
            array[2] = 0.2 * (Math.log(distance + 1.0E-20) + this.sign * Math.log(distance2 + 1.0E-20));
        }
        
        void setup() {
            VecDemoFrame.this.setupBar(0, "Line Separation", 30);
        }
        
        VecFunction createNext() {
            return new InverseSquaredRadial();
        }
    }
    
    class InverseSquaredRadial extends VecFunction
    {
        static final double chargeSize = 0.001;
        
        String getName() {
            return "1/r^2 single";
        }
        
        void getField(final double[] array, final double[] array2) {
            final double distance = VecDemoFrame.this.distance(array2);
            if (distance < 0.001) {
                VecDemoFrame.this.boundCheck = true;
            }
            final double n = 3.0E-4 / (distance * distance * distance);
            array[0] = -array2[0] * n;
            array[1] = -array2[1] * n;
            array[2] = -0.3 / distance;
        }
        
        VecFunction createNext() {
            return new InverseSquaredRadialDouble();
        }
    }
    
    class InverseSquaredRadialDouble extends InverseSquaredRadial
    {
        double sign2;
        
        String getName() {
            return "1/r^2 double";
        }
        
        void getField(final double[] array, final double[] array2) {
            final double gridToDouble = VecDemoFrame.this.gridToDouble(40 + VecDemoFrame.this.aux1Bar.getValue() * 80 / 200);
            final double n = array2[0] - gridToDouble;
            final double n2 = array2[0] + gridToDouble;
            final double distance = VecDemoFrame.this.distance(n, array2[1]);
            if (distance < 0.001) {
                VecDemoFrame.this.boundCheck = true;
            }
            final double distance2 = VecDemoFrame.this.distance(n2, array2[1]);
            if (distance2 < 0.001) {
                VecDemoFrame.this.boundCheck = true;
            }
            final double n3 = 3.0E-4;
            final double n4 = n3 / (distance * distance * distance);
            final double n5 = n3 / (distance2 * distance2 * distance2) * this.sign2;
            array[0] = -n * n4 - n2 * n5;
            array[1] = -array2[1] * n4 - array2[1] * n5;
            array[2] = -0.05 / distance - 0.05 * this.sign2 / distance2;
            if (this.sign2 == -1.0) {
                final int n6 = 2;
                array[n6] *= 2.0;
            }
        }
        
        void setup() {
            this.sign2 = 1.0;
            VecDemoFrame.this.setupBar(0, "Charge Separation", 30);
        }
        
        VecFunction createNext() {
            return new InverseRotational();
        }
    }
    
    class InverseRotational extends InverseRadial
    {
        String getName() {
            return "1/r rotational";
        }
        
        void getField(final double[] array, final double[] array2) {
            final double distance = VecDemoFrame.this.distance(array2[0], array2[1]);
            if (VecDemoFrame.this.showA) {
                array[0] = (array[1] = 0.0);
                array[2] = -0.001 * (Math.log(distance) - 0.5);
            }
            else {
                if (distance < 0.002) {
                    VecDemoFrame.this.boundCheck = true;
                }
                VecDemoFrame.this.rotateParticle(array, array2, 1.0E-4 / (distance * distance));
            }
        }
        
        boolean nonGradient() {
            return true;
        }
        
        VecFunction createNext() {
            return new InverseRotationalPotential();
        }
    }
    
    class InverseRotationalPotential extends VecFunction
    {
        String getName() {
            return "1/r rotational potential";
        }
        
        void getField(final double[] array, final double[] array2) {
            final double distance = VecDemoFrame.this.distance(array2[0], array2[1]);
            VecDemoFrame.this.rotateParticle(array, array2, 1.0E-4 / (distance * distance));
            if (distance < 0.002) {
                VecDemoFrame.this.boundCheck = true;
            }
            else if (array2[0] >= 0.0 && array2[1] < 0.001 && array2[1] > -0.025) {
                VecDemoFrame.this.boundCheck = true;
                if (array2[1] == 0.0) {
                    array[1] = 1.0E8;
                }
            }
            double atan2 = Math.atan2(array2[1], array2[0]);
            if (atan2 < 0.0) {
                atan2 += 6.283185307179586;
            }
            array[2] = (3.141592653589793 - atan2) * 0.3;
        }
        
        VecFunction createNext() {
            return new InverseRotationalDouble();
        }
    }
    
    class InverseRotationalDouble extends InverseRadialDouble
    {
        int dir2;
        boolean ext;
        
        InverseRotationalDouble() {
            this.dir2 = 1;
            this.ext = false;
        }
        
        String getName() {
            return "1/r rotational double";
        }
        
        void getField(final double[] array, final double[] array2) {
            final double gridToDouble = VecDemoFrame.this.gridToDouble(40 + VecDemoFrame.this.aux1Bar.getValue() * 80 / 200);
            final double distance = VecDemoFrame.this.distance(array2[0] - gridToDouble, array2[1]);
            final double distance2 = VecDemoFrame.this.distance(array2[0] + gridToDouble, array2[1]);
            if (this.ext) {
                final double n = VecDemoFrame.this.aux3Bar.getValue() * 3.141592653589793 / 50.0;
                final double n2 = VecDemoFrame.this.aux2Bar.getValue() / 6.0;
                VecDemoFrame.this.getDirectionField(array, array2, n);
                final int n3 = 0;
                array[n3] *= n2;
                final int n4 = 1;
                array[n4] *= n2;
                final int n5 = 2;
                array[n5] *= n2;
            }
            else {
                final int n6 = 0;
                final int n7 = 1;
                final int n8 = 2;
                final double n9 = 0.0;
                array[n8] = n9;
                array[n6] = (array[n7] = n9);
            }
            if (VecDemoFrame.this.showA) {
                if (this.dir2 == 1) {
                    final int n10 = 2;
                    array[n10] += -0.001 * (Math.log(distance) + Math.log(distance2) - 1.0);
                }
                else {
                    final int n11 = 2;
                    array[n11] += 0.001 * (Math.log(distance) - Math.log(distance2));
                }
            }
            else {
                if (distance < 0.002) {
                    VecDemoFrame.this.boundCheck = true;
                }
                VecDemoFrame.this.rotateParticleAdd(array, array2, 1.0E-4 / (distance * distance), gridToDouble, 0.0);
                if (distance2 < 0.002) {
                    VecDemoFrame.this.boundCheck = true;
                }
                VecDemoFrame.this.rotateParticleAdd(array, array2, this.dir2 * 1.0E-4 / (distance2 * distance2), -gridToDouble, 0.0);
            }
        }
        
        void setup() {
            VecDemoFrame.this.setupBar(0, "Line Separation", 30);
            if (this.ext) {
                VecDemoFrame.this.setupBar(1, "Ext. Strength", 7);
                VecDemoFrame.this.setupBar(2, "Ext. Direction", 0);
            }
        }
        
        boolean nonGradient() {
            return true;
        }
        
        VecFunction createNext() {
            return new InverseRotationalDoubleExt();
        }
    }
    
    class InverseRotationalDoubleExt extends InverseRotationalDouble
    {
        InverseRotationalDoubleExt() {
            super.ext = true;
        }
        
        String getName() {
            return "1/r rot double + ext";
        }
        
        VecFunction createNext() {
            return new InverseRotationalDipole();
        }
    }
    
    class InverseRotationalDipole extends InverseRotationalDouble
    {
        InverseRotationalDipole() {
            super.dir2 = -1;
        }
        
        String getName() {
            return "1/r rotational dipole";
        }
        
        VecFunction createNext() {
            return new InverseRotationalDipoleExt();
        }
    }
    
    class InverseRotationalDipoleExt extends InverseRotationalDouble
    {
        InverseRotationalDipoleExt() {
            super.dir2 = -1;
            super.ext = true;
        }
        
        void setup() {
            super.setup();
            VecDemoFrame.this.aux2Bar.setValue(3);
            VecDemoFrame.this.aux3Bar.setValue(25);
        }
        
        String getName() {
            return "1/r rot dipole + ext";
        }
        
        VecFunction createNext() {
            return new OneDirectionFunction();
        }
    }
    
    class OneDirectionFunction extends VecFunction
    {
        String getName() {
            return "one direction";
        }
        
        void getField(final double[] array, final double[] array2) {
            VecDemoFrame.this.getDirectionField(array, array2, VecDemoFrame.this.aux1Bar.getValue() * 3.141592653589793 / 50.0);
        }
        
        void setup() {
            VecDemoFrame.this.setupBar(0, "Theta", 0);
        }
        
        VecFunction createNext() {
            return new InverseSquaredRadialSphere();
        }
    }
    
    class InverseSquaredRadialSphere extends VecFunction
    {
        String getName() {
            return "1/r^2 sphere";
        }
        
        double getSize() {
            return (VecDemoFrame.this.aux1Bar.getValue() + 1) / 110.0;
        }
        
        void getField(final double[] array, final double[] array2) {
            double distance = VecDemoFrame.this.distance(array2);
            if (distance < 0.01) {
                VecDemoFrame.this.boundCheck = true;
            }
            final double size = this.getSize();
            array[2] = 0.2 * ((distance > size) ? (-1.0 / distance) : (-3.0 / (2.0 * size) + distance * distance / (2.0 * size * size * size)));
            if (distance < size) {
                distance = size;
            }
            final double n = 3.0E-4 / (distance * distance * distance);
            array[0] = -array2[0] * n;
            array[1] = -array2[1] * n;
        }
        
        void setup() {
            VecDemoFrame.this.setupBar(0, "Sphere Size", 30);
        }
        
        boolean checkBoundsWithForce() {
            return false;
        }
        
        VecFunction createNext() {
            return new ConstRadial();
        }
    }
    
    class ConstRadial extends InverseSquaredRadial
    {
        String getName() {
            return "const radial";
        }
        
        void getField(final double[] array, final double[] array2) {
            final double distance = VecDemoFrame.this.distance(array2);
            if (distance < 0.001) {
                VecDemoFrame.this.boundCheck = true;
            }
            final double n = 6.0E-4 / distance;
            array[0] = -n * array2[0];
            array[1] = -n * array2[1];
            array[2] = distance;
        }
        
        boolean checkBoundsWithForce() {
            return false;
        }
        
        VecFunction createNext() {
            return new LinearRadial();
        }
    }
    
    class LinearRadial extends InverseSquaredRadial
    {
        String getName() {
            return "linear radial";
        }
        
        void getField(final double[] array, final double[] array2) {
            final double distance = VecDemoFrame.this.distance(array2);
            if (distance < 0.001) {
                VecDemoFrame.this.boundCheck = true;
            }
            final double n = 9.0E-4;
            array[0] = -array2[0] * n;
            array[1] = -array2[1] * n;
            array[2] = distance * distance - 1.0;
        }
        
        boolean checkBoundsWithForce() {
            return false;
        }
        
        VecFunction createNext() {
            return new ConstantToYAxis();
        }
    }
    
    class ConstantToYAxis extends InverseRadial
    {
        String getName() {
            return "constant to y axis";
        }
        
        void getField(final double[] array, final double[] array2) {
            final double n = 4.0E-4;
            if (array2[0] > -0.01 && array2[0] < 0.01) {
                VecDemoFrame.this.boundCheck = true;
            }
            array[0] = ((array2[0] <= 0.0) ? n : (-n));
            array[1] = 0.0;
            array[2] = Math.abs(array2[0]) - 1.0;
        }
        
        boolean checkBoundsWithForce() {
            return false;
        }
        
        VecFunction createNext() {
            return new LinearToYAxis();
        }
    }
    
    class LinearToYAxis extends InverseRadial
    {
        String getName() {
            return "linear to y axis";
        }
        
        void getField(final double[] array, final double[] array2) {
            final double abs = Math.abs(array2[0]);
            if (abs < 0.001) {
                VecDemoFrame.this.boundCheck = true;
            }
            array[0] = -array2[0] * 9.0E-4;
            array[1] = 0.0;
            array[2] = abs * abs - 1.0;
        }
        
        boolean checkBoundsWithForce() {
            return false;
        }
        
        VecFunction createNext() {
            return new LinearToXYAxes();
        }
    }
    
    class LinearToXYAxes extends VecFunction
    {
        String getName() {
            return "2-dimensional oscillator";
        }
        
        void getField(final double[] array, final double[] array2) {
            final double n = 6.0E-4;
            final double sqrt = Math.sqrt((VecDemoFrame.this.aux1Bar.getValue() + 1) / 51.0);
            array[0] = -n * sqrt * array2[0];
            array[1] = -n / sqrt * array2[1];
            array[2] = array2[0] * array2[0] * sqrt + array2[1] * array2[1] / sqrt - 1.0;
        }
        
        void setup() {
            VecDemoFrame.this.setupBar(0, "X/Y Ratio", 50);
        }
        
        boolean checkBoundsWithForce() {
            return false;
        }
        
        VecFunction createNext() {
            return new InverseToYAxis();
        }
    }
    
    class InverseToYAxis extends VecFunction
    {
        String getName() {
            return "inverse to y axis";
        }
        
        void getField(final double[] array, final double[] array2) {
            if (array2[0] > -0.01 && array2[0] < 0.01) {
                VecDemoFrame.this.boundCheck = true;
            }
            final double n = 3.0E-4;
            double n2 = array2[0];
            if (n2 == 0.0) {
                n2 = 1.0E-5;
            }
            array[0] = -n / n2;
            array[1] = 0.0;
            array[2] = -0.01 / (n2 * n2);
        }
        
        VecFunction createNext() {
            return new InverseSquareRotational();
        }
    }
    
    class InverseSquareRotational extends InverseRadial
    {
        String getName() {
            return "1/r^2 rotational";
        }
        
        void getField(final double[] array, final double[] array2) {
            final double distance = VecDemoFrame.this.distance(array2[0], array2[1]);
            if (distance < 0.002) {
                VecDemoFrame.this.boundCheck = true;
            }
            VecDemoFrame.this.rotateParticle(array, array2, 1.0E-4 / (distance * distance * distance));
        }
        
        boolean nonGradient() {
            return true;
        }
        
        VecFunction createNext() {
            return new LinearRotational();
        }
    }
    
    class LinearRotational extends InverseRadial
    {
        String getName() {
            return "linear rotational";
        }
        
        void getField(final double[] array, final double[] array2) {
            final double n = 6.0E-4;
            array[0] = -n * array2[1];
            array[1] = n * array2[0];
            array[2] = 0.0;
        }
        
        boolean nonGradient() {
            return true;
        }
        
        VecFunction createNext() {
            return new ConstantRotational();
        }
    }
    
    class ConstantRotational extends InverseRadial
    {
        String getName() {
            return "constant rotational";
        }
        
        void getField(final double[] array, final double[] array2) {
            VecDemoFrame.this.rotateParticle(array, array2, 6.0E-4 / VecDemoFrame.this.distance(array2[0], array2[1]));
        }
        
        boolean nonGradient() {
            return true;
        }
        
        VecFunction createNext() {
            return new FxEqualsYField();
        }
    }
    
    class FxEqualsYField extends VecFunction
    {
        String getName() {
            return "(y,0)";
        }
        
        void getField(final double[] array, final double[] array2) {
            array[2] = (array[1] = 0.0);
            array[0] = array2[1] * 9.0E-4;
        }
        
        boolean nonGradient() {
            return true;
        }
        
        VecFunction createNext() {
            return new FxEqualsY2();
        }
    }
    
    class FxEqualsY2 extends VecFunction
    {
        String getName() {
            return "(y^2,0)";
        }
        
        void getField(final double[] array, final double[] array2) {
            array[2] = (array[1] = 0.0);
            array[0] = array2[1] * array2[1] * 0.002;
        }
        
        boolean nonGradient() {
            return true;
        }
        
        VecFunction createNext() {
            return new Saddle();
        }
    }
    
    class Saddle extends VecFunction
    {
        String getName() {
            return "saddle";
        }
        
        void getField(final double[] array, final double[] array2) {
            final double n = 0.001;
            array[0] = -n * array2[0];
            array[1] = n * array2[1] * 0.5;
            array[2] = 1.0 * (2.0 * array2[0] * array2[0] - array2[1] * array2[1]);
        }
        
        VecFunction createNext() {
            return new RotationalExpansion();
        }
    }
    
    class RotationalExpansion extends VecFunction
    {
        String getName() {
            return "rotation + expansion";
        }
        
        void getField(final double[] array, final double[] array2) {
            final double n = 6.0E-4;
            array[0] = n * (array2[0] - array2[1]);
            array[1] = n * (array2[0] + array2[1]);
            array[2] = 0.0;
        }
        
        boolean nonGradient() {
            return true;
        }
        
        VecFunction createNext() {
            return new Function4Field();
        }
    }
    
    class Function4Field extends VecFunction
    {
        String getName() {
            return "(x^2-y,x+y^2)";
        }
        
        void getField(final double[] array, final double[] array2) {
            final double n = 3.0E-4;
            array[0] = n * (array2[0] * array2[0] - array2[1]);
            array[1] = n * (array2[0] + array2[1] * array2[1]);
            array[2] = 0.0;
        }
        
        boolean nonGradient() {
            return true;
        }
        
        VecFunction createNext() {
            return new Function5Field();
        }
    }
    
    class Function5Field extends VecFunction
    {
        String getName() {
            return "(x+y^2,x^2-y)";
        }
        
        void getField(final double[] array, final double[] array2) {
            final double n = 3.0E-4;
            array[0] = n * (array2[0] + array2[1] * array2[1]);
            array[1] = n * (array2[0] * array2[0] - array2[1]);
            array[2] = 0.0;
        }
        
        boolean nonGradient() {
            return true;
        }
        
        VecFunction createNext() {
            return new Function6Field();
        }
    }
    
    class Function6Field extends VecFunction
    {
        String getName() {
            return "(x,x^2)";
        }
        
        void getField(final double[] array, final double[] array2) {
            final double n = 6.0E-4;
            array[0] = n * array2[0];
            array[1] = n * array2[0] * array2[0];
            array[2] = 0.0;
        }
        
        boolean nonGradient() {
            return true;
        }
        
        VecFunction createNext() {
            return new Function7Field();
        }
    }
    
    class Function7Field extends VecFunction
    {
        String getName() {
            return "u=x^2+y";
        }
        
        void getField(final double[] array, final double[] array2) {
            final double n = 3.0E-4;
            array[0] = -2.0 * array2[0] * n;
            array[1] = -n;
            array[2] = (array2[0] * array2[0] + array2[1]) * 1.0;
        }
        
        VecFunction createNext() {
            return new PendulumPotential();
        }
    }
    
    class PendulumPotential extends VecFunction
    {
        String getName() {
            return "pendulum potential";
        }
        
        void getField(final double[] array, final double[] array2) {
            final double n = 6.0E-4;
            final double n2 = array2[0] * 3.1;
            final double n3 = array2[1] * 3.1;
            final double cos = Math.cos(n2);
            final double cos2 = Math.cos(n3);
            final double sin = Math.sin(n2);
            final double sin2 = Math.sin(n3);
            array[0] = -n * sin * cos2;
            array[1] = -n * cos * sin2;
            array[2] = -cos * cos2 * 0.5;
        }
        
        VecFunction createNext() {
            return new Function8Field();
        }
    }
    
    class Function8Field extends VecFunction
    {
        String getName() {
            return "sin(r2)/r2";
        }
        
        void getField(final double[] array, final double[] array2) {
            final double n = 23.0 * (array2[0] * array2[0] + array2[1] * array2[1]) + 1.0E-8;
            array[2] = Math.sin(n) / n;
            Math.sqrt(n);
            final double n2 = (Math.sin(n) / (n * n) - Math.cos(n) / n) * 0.01;
            array[0] = array2[0] * n2;
            array[1] = array2[1] * n2;
        }
        
        VecFunction createNext() {
            return new UserDefinedPotential();
        }
    }
    
    class UserDefinedPotential extends VecFunction
    {
        Expr expr;
        double[] y0;
        
        String getName() {
            return "user-defined potential";
        }
        
        void setup() {
            VecDemoFrame.this.textFields[0].setText("x*x");
            VecDemoFrame.this.textFields[0].show();
            VecDemoFrame.this.textFieldLabel.setText("Potential Function");
            VecDemoFrame.this.textFieldLabel.show();
            this.actionPerformed();
            this.y0 = new double[3];
        }
        
        void actionPerformed() {
            VecDemoFrame.this.parseError = false;
            final ExprParser exprParser = new ExprParser(VecDemoFrame.this.textFields[0].getText());
            this.expr = exprParser.parseExpression();
            if (exprParser.gotError()) {
                VecDemoFrame.this.parseError = true;
            }
            VecDemoFrame.this.functionChanged = true;
        }
        
        void getField(final double[] array, final double[] array2) {
            final double n = 1.0E-5;
            for (int i = 0; i != 3; ++i) {
                this.y0[i] = array2[i];
            }
            final double eval = this.expr.eval(this.y0);
            final double[] y0 = this.y0;
            final int n2 = 0;
            y0[n2] += n;
            array[0] = eval - this.expr.eval(this.y0);
            this.y0[0] = array2[0];
            final double[] y2 = this.y0;
            final int n3 = 1;
            y2[n3] += n;
            array[1] = eval - this.expr.eval(this.y0);
            this.y0[1] = array2[1];
            array[2] = eval * 0.01;
            for (int j = 0; j != 2; ++j) {
                if (array[j] <= -10.0 || array[j] >= 10.0) {
                    VecDemoFrame.this.boundCheck = true;
                }
            }
        }
        
        VecFunction createNext() {
            return new UserDefinedFunction();
        }
    }
    
    class UserDefinedFunction extends VecFunction
    {
        Expr[] exprs;
        
        String getName() {
            return "user-defined field";
        }
        
        void setup() {
            this.exprs = new Expr[3];
            VecDemoFrame.this.textFields[0].setText("x");
            VecDemoFrame.this.textFields[1].setText("y");
            VecDemoFrame.this.textFieldLabel.setText("Field Functions");
            VecDemoFrame.this.textFieldLabel.show();
            for (int i = 0; i != 2; ++i) {
                VecDemoFrame.this.textFields[i].show();
            }
            this.actionPerformed();
        }
        
        void actionPerformed() {
            VecDemoFrame.this.parseError = false;
            for (int i = 0; i != 2; ++i) {
                final ExprParser exprParser = new ExprParser(VecDemoFrame.this.textFields[i].getText());
                this.exprs[i] = exprParser.parseExpression();
                if (exprParser.gotError()) {
                    VecDemoFrame.this.parseError = true;
                }
            }
            VecDemoFrame.this.functionChanged = true;
        }
        
        void getField(final double[] array, final double[] array2) {
            final double n = 2.0E-4;
            for (int i = 0; i != 2; ++i) {
                array[i] = n * this.exprs[i].eval(array2);
                if (array[i] <= -10.0 || array[i] >= 10.0) {
                    VecDemoFrame.this.boundCheck = true;
                }
            }
            array[2] = 0.0;
        }
        
        boolean nonGradient() {
            return true;
        }
        
        VecFunction createNext() {
            return null;
        }
    }
    
    class DrawData
    {
        public Graphics g;
        public double mult;
        public double[] field;
        public double[] vv;
    }
    
    class Particle
    {
        public double[] pos;
        public double[] vel;
        public double lifetime;
        public double phi;
        public double theta;
        public double phiv;
        public double thetav;
        public double stepsize;
        public Color color;
        
        Particle() {
            this.pos = new double[3];
            this.vel = new double[3];
            this.stepsize = 1.0;
        }
    }
    
    class FieldVector
    {
        public int sx1;
        public int sy1;
        public int sx2;
        public int sy2;
        public double[] p1;
        public double[] p2;
        public int col;
        public int viewPri;
    }
    
    class GridElement
    {
        public double height;
        public double div;
        public double curl;
        public double normdot;
        public double vecX;
        public double vecY;
        public boolean visible;
        public boolean valid;
    }
}
