import java.awt.event.ItemEvent;
import java.awt.event.MouseEvent;
import java.awt.event.AdjustmentEvent;
import java.awt.Event;
import java.net.URL;
import java.awt.event.ActionEvent;
import java.awt.event.ComponentEvent;
import java.awt.image.ImageObserver;
import java.awt.Graphics;
import java.awt.Component;
import java.awt.LayoutManager;
import java.util.Vector;
import java.awt.TextField;
import java.awt.Color;
import java.awt.Scrollbar;
import java.awt.Label;
import java.awt.Choice;
import java.awt.Button;
import java.awt.Checkbox;
import java.util.Random;
import java.awt.Image;
import java.awt.Rectangle;
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

class Vec3DemoFrame extends Frame implements ComponentListener, ActionListener, AdjustmentListener, MouseMotionListener, MouseListener, ItemListener
{
    Thread engine;
    Dimension winSize;
    Rectangle viewMain;
    Rectangle viewAxes;
    Image dbimage;
    Vec3Demo applet;
    Random random;
    static final double pi = 3.141592653589793;
    Vec3DemoCanvas cv;
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
    static final int DISP_LINES = 3;
    static final int DISP_EQUIPS = 4;
    static final int DISP_PART_VELOC_A = -1;
    static final int DISP_VECTORS_A = -2;
    static final int DISP_PART_MAG = -3;
    static final int DISP_VIEW_PAPER = -4;
    Choice sliceChooser;
    static final int SLICE_NONE = 0;
    static final int SLICE_X = 1;
    static final int SLICE_Y = 2;
    static final int SLICE_Z = 3;
    Label partCountLabel;
    Label textFieldLabel;
    Label strengthLabel;
    Scrollbar partCountBar;
    Scrollbar strengthBar;
    Scrollbar aux1Bar;
    Scrollbar aux2Bar;
    Scrollbar aux3Bar;
    double fieldStrength;
    double partMult;
    Color darkYellow;
    final double lineWidth = 0.01;
    AuxBar[] auxBars;
    Label vecDensityLabel;
    Scrollbar vecDensityBar;
    Label potentialLabel;
    Scrollbar potentialBar;
    Label lineDensityLabel;
    Scrollbar lineDensityBar;
    Choice modeChooser;
    TextField[] textFields;
    static final int MODE_ANGLE = 0;
    static final int MODE_ZOOM = 1;
    static final int MODE_SLICE = 2;
    int reverse;
    int[] xpoints;
    int[] ypoints;
    int[][] slicerPoints;
    double[][] sliceFaces;
    double[] sliceFace;
    Particle[] particles;
    FieldVector[] vectors;
    int vecCount;
    int[][][] density;
    double sliceval;
    double[] rotmatrix;
    double[] cameraPos;
    double[] intersection;
    double intersectionDistance;
    int vectorSpacing;
    int currentStep;
    boolean selectedSlice;
    boolean mouseDown;
    boolean getPot;
    boolean showA;
    boolean parseError;
    Color[] fieldColors;
    Color[] equipColors;
    static final double densitygroupsize = 0.5;
    static final int densitygridsize = 4;
    static final int maxParticleCount = 5000;
    double zoom;
    boolean dragging;
    int oldDragX;
    int oldDragY;
    int dragX;
    int dragY;
    int dragStartX;
    int dragStartY;
    double dragZoomStart;
    double lastXRot;
    double lastYRot;
    Vector functionList;
    VecFunction curfunc;
    int pause;
    static final double root2 = 1.4142135623730951;
    double scalex;
    double scaley;
    static final double viewDistance = 5.0;
    long lastTime;
    double timeStep;
    double[] potfield;
    static final int maxVectors = 10000;
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
        return "Vec3Demo by Paul Falstad";
    }
    
    int getrand(final int n) {
        int nextInt = this.random.nextInt();
        if (nextInt < 0) {
            nextInt = -nextInt;
        }
        return nextInt % n;
    }
    
    Vec3DemoFrame(final Vec3Demo applet) {
        super("3-D Vector Fields Applet v1.3a");
        this.engine = null;
        this.darkYellow = new Color(144, 144, 0);
        this.sliceval = 0.0;
        this.vectorSpacing = 16;
        this.zoom = 3.0;
        this.pause = 20;
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
        this.functionList = new Vector();
        for (VecFunction next = new InverseSquaredRadial(); next != null; next = next.createNext()) {
            this.functionList.addElement(next);
        }
        this.random = new Random();
        this.particles = new Particle[5000];
        for (int i = 0; i != 5000; ++i) {
            this.particles[i] = new Particle();
        }
        this.xpoints = new int[4];
        this.ypoints = new int[4];
        this.slicerPoints = new int[2][10];
        this.sliceFaces = new double[4][3];
        this.rotmatrix = new double[9];
        this.setXYView();
        this.density = new int[4][4][4];
        this.setLayout(new Vec3DemoLayout());
        (this.cv = new Vec3DemoCanvas(this)).addComponentListener(this);
        this.cv.addMouseMotionListener(this);
        this.cv.addMouseListener(this);
        this.add(this.cv);
        this.add(new Label("Field selection:"));
        this.functionChooser = new Choice();
        for (int j = 0; j != this.functionList.size(); ++j) {
            this.functionChooser.add(((VecFunction)this.functionList.elementAt(j)).getName());
        }
        this.add(this.functionChooser);
        this.functionChooser.addItemListener(this);
        (this.dispChooser = new Choice()).addItemListener(this);
        this.setupDispChooser(true);
        this.add(this.dispChooser);
        (this.modeChooser = new Choice()).add("Mouse = Adjust Angle");
        this.modeChooser.add("Mouse = Adjust Zoom");
        this.modeChooser.addItemListener(this);
        this.add(this.modeChooser);
        (this.sliceChooser = new Choice()).add("No Slicing");
        this.sliceChooser.add("Show X Slice");
        this.sliceChooser.add("Show Y Slice");
        this.sliceChooser.add("Show Z Slice");
        this.sliceChooser.addItemListener(this);
        this.add(this.sliceChooser);
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
        this.add(this.strengthBar = new Scrollbar(0, 10, 1, 0, 100));
        this.strengthBar.addAdjustmentListener(this);
        this.add(this.partCountLabel = new Label("Number of Particles", 1));
        this.add(this.partCountBar = new Scrollbar(0, 500, 1, 1, 5000));
        this.partCountBar.addAdjustmentListener(this);
        this.add(this.vecDensityLabel = new Label("Vector Density", 1));
        this.add(this.vecDensityBar = new Scrollbar(0, 16, 1, 2, 64));
        this.vecDensityBar.addAdjustmentListener(this);
        this.add(this.lineDensityLabel = new Label("Streamline Density", 1));
        this.add(this.lineDensityBar = new Scrollbar(0, 5, 1, 3, 16));
        this.lineDensityBar.addAdjustmentListener(this);
        this.add(this.potentialLabel = new Label("Potential", 1));
        this.add(this.potentialBar = new Scrollbar(0, 250, 1, 0, 1000));
        this.potentialBar.addAdjustmentListener(this);
        this.auxBars = new AuxBar[3];
        final Label label;
        this.add(label = new Label("Aux 1", 1));
        this.add(this.aux1Bar = new Scrollbar(0, 0, 1, 0, 100));
        this.aux1Bar.addAdjustmentListener(this);
        this.auxBars[0] = new AuxBar(label, this.aux1Bar);
        final Label label2;
        this.add(label2 = new Label("Aux 2", 1));
        this.add(this.aux2Bar = new Scrollbar(0, 0, 1, 0, 100));
        this.aux2Bar.addAdjustmentListener(this);
        this.auxBars[1] = new AuxBar(label2, this.aux2Bar);
        final Label label3;
        this.add(label3 = new Label("Aux 3", 1));
        this.add(this.aux3Bar = new Scrollbar(0, 0, 1, 0, 100));
        this.aux3Bar.addAdjustmentListener(this);
        this.auxBars[2] = new AuxBar(label3, this.aux3Bar);
        this.add(this.textFieldLabel = new Label("", 1));
        this.textFields = new TextField[3];
        for (int k = 0; k != 3; ++k) {
            this.add(this.textFields[k] = new TextField());
            this.textFields[k].addActionListener(this);
        }
        this.fieldColors = new Color[513];
        for (int l = 0; l != 256; ++l) {
            this.fieldColors[l] = new Color(0xFF000000 | l << 8);
        }
        for (int n = 0; n != 256; ++n) {
            this.fieldColors[n + 256] = new Color(0xFF00FF00 | n * 65537);
        }
        this.fieldColors[512] = this.fieldColors[511];
        this.equipColors = new Color[513];
        for (int n2 = 0; n2 != 256; ++n2) {
            final int n3 = 255 - n2 / 2;
            final int n4 = n2 / 2;
            this.equipColors[n2] = new Color(0xFF000000 | n3 << 16 | n4 << 8 | n4);
        }
        for (int n5 = 0; n5 != 256; ++n5) {
            final int n6 = 128 + n5 / 2;
            final int n7 = 128 - n5 / 2;
            this.equipColors[n5 + 256] = new Color(0xFF000000 | n7 << 16 | n6 << 8 | n7);
        }
        this.equipColors[512] = this.equipColors[511];
        this.add(new Label("http://www.falstad.com", 1));
        this.intersection = new double[3];
        this.reinit();
        this.cv.setBackground(Color.black);
        this.cv.setForeground(Color.lightGray);
        this.resize(500, 500);
        this.handleResize();
        final Dimension screenSize = this.getToolkit().getScreenSize();
        final Dimension size = this.getSize();
        this.setLocation((screenSize.width - size.width) / 2, (screenSize.height - size.height) / 2);
        this.functionChanged();
        this.dispChooserChanged();
        this.show();
        this.requestFocus();
    }
    
    void setViewMatrix(final double n, final double n2) {
        for (int i = 0; i != 9; ++i) {
            this.rotmatrix[i] = 0.0;
        }
        final double[] rotmatrix = this.rotmatrix;
        final int n3 = 0;
        final double[] rotmatrix2 = this.rotmatrix;
        final int n4 = 4;
        final double[] rotmatrix3 = this.rotmatrix;
        final int n5 = 8;
        final double n6 = 1.0;
        rotmatrix3[n5] = n6;
        rotmatrix[n3] = (rotmatrix2[n4] = n6);
        this.rotate(n, n2);
        final double n7 = 0.0;
        this.lastYRot = n7;
        this.lastXRot = n7;
    }
    
    void setXYView() {
        this.setViewMatrix(0.0, 0.28559933214452665);
    }
    
    void setXYViewExact() {
        this.setViewMatrix(0.0, 0.0);
    }
    
    void setXZView() {
        this.setViewMatrix(0.0, -1.2851969946503699);
    }
    
    void setXZViewExact() {
        this.setViewMatrix(0.0, -1.5707963267948966);
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
    }
    
    void resetDensityGroups() {
        for (int i = 0; i != 4; ++i) {
            for (int j = 0; j != 4; ++j) {
                for (int k = 0; k != 4; ++k) {
                    this.density[i][j][k] = 0;
                }
            }
        }
        final int selectedIndex = this.sliceChooser.getSelectedIndex();
        final boolean b = selectedIndex > 0;
        int particleCount;
        int l;
        for (particleCount = this.getParticleCount(), l = 0; l != particleCount; ++l) {
            final Particle particle = this.particles[l];
            if (b) {
                particle.pos[selectedIndex - 1] = this.sliceval;
            }
            this.addToDensityGroup(particle);
        }
        while (l != 5000) {
            this.particles[l].lifetime = -100.0;
            ++l;
        }
    }
    
    int addToDensityGroup(final Particle particle) {
        final int n = (int)((particle.pos[0] + 1.0) * 2.0);
        final int n2 = (int)((particle.pos[1] + 1.0) * 2.0);
        final int n3 = (int)((particle.pos[2] + 1.0) * 2.0);
        int n4 = 0;
        try {
            n4 = ++this.density[n][n2][n3];
            if (n4 > 5000) {
                System.out.print(n + " " + n2 + " " + n3 + " " + this.density[n][n2][n3] + "\n");
            }
        }
        catch (Exception ex) {
            System.out.print(particle.pos[0] + " " + particle.pos[1] + " " + particle.pos[2] + "\n");
            ex.printStackTrace();
        }
        return n4;
    }
    
    void removeFromDensityGroup(final Particle particle) {
        final int n = (int)((particle.pos[0] + 1.0) * 2.0);
        final int n2 = (int)((particle.pos[1] + 1.0) * 2.0);
        final int n3 = (int)((particle.pos[2] + 1.0) * 2.0);
        try {
            final int[] array = this.density[n][n2];
            final int n4 = n3;
            final int n5 = array[n4] - 1;
            array[n4] = n5;
            if (n5 < 0) {
                System.out.print(n + " " + n2 + " " + n3 + " " + this.density[n][n2][n3] + "\n");
            }
        }
        catch (Exception ex) {
            System.out.print(particle.pos[0] + " " + particle.pos[1] + " " + particle.pos[2] + "\n");
            ex.printStackTrace();
        }
    }
    
    void positionParticle(final Particle particle) {
        int n = 0;
        int n2 = 0;
        int n3 = 0;
        int n4 = 10000;
        final int getrand = this.getrand(4);
        final int getrand2 = this.getrand(4);
        final int getrand3 = this.getrand(4);
        for (int i = 0; i != 4; ++i) {
            for (int j = 0; j != 4; ++j) {
                for (int k = 0; k != 4; ++k) {
                    final int n5 = (getrand + i) % 4;
                    final int n6 = (getrand2 + j) % 4;
                    final int n7 = (getrand3 + k) % 4;
                    if (this.density[n5][n6][n7] <= n4) {
                        n = n5;
                        n2 = n6;
                        n3 = n7;
                        n4 = this.density[n5][n6][n7];
                    }
                }
            }
        }
        particle.pos[0] = n * 0.5 + this.getrand(100) * 0.5 / 100.0 - 1.0;
        particle.pos[1] = n2 * 0.5 + this.getrand(100) * 0.5 / 100.0 - 1.0;
        particle.pos[2] = n3 * 0.5 + this.getrand(100) * 0.5 / 100.0 - 1.0;
        particle.lifetime = (this.curfunc.redistribute() ? 500.0 : 5000.0);
        particle.stepsize = 1.0;
        particle.theta = (this.getrand(101) - 50) * 3.141592653589793 / 50.0;
        particle.phi = (this.getrand(101) - 50) * 3.141592653589793 / 50.0;
        for (int l = 0; l != 3; ++l) {
            particle.vel[l] = 0.0;
        }
    }
    
    int getParticleCount() {
        return this.partCountBar.getValue();
    }
    
    void resetParticles() {
        for (int particleCount = this.getParticleCount(), i = 0; i != particleCount; ++i) {
            final Particle particle = this.particles[i];
            for (int j = 0; j != 3; ++j) {
                particle.pos[j] = this.getrand(200) / 100.0 - 1.0;
                particle.vel[j] = 0.0;
            }
            particle.lifetime = i * 2;
            particle.stepsize = 1.0;
        }
        this.resetDensityGroups();
    }
    
    void kickParticles() {
        for (int i = 0; i != this.getParticleCount(); ++i) {
            final Particle particle = this.particles[i];
            for (int j = 0; j != 3; ++j) {
                final double[] vel = particle.vel;
                final int n = j;
                vel[n] += (this.getrand(100) / 99.0 - 0.5) * 0.04;
            }
        }
    }
    
    void rotate(final double n, final double n2) {
        final double cos = Math.cos(n);
        final double sin = Math.sin(n);
        final double cos2 = Math.cos(n2);
        final double sin2 = Math.sin(n2);
        this.rotate(new double[] { cos, -sin * sin2, cos2 * sin, 0.0, cos2, sin2, -sin, -cos * sin2, cos * cos2 });
    }
    
    void rotate(final double[] array) {
        final double[] rotmatrix = this.rotmatrix;
        this.rotmatrix = new double[9];
        for (int i = 0; i != 3; ++i) {
            for (int j = 0; j != 3; ++j) {
                double n = 0.0;
                for (int k = 0; k != 3; ++k) {
                    n += rotmatrix[k + i * 3] * array[j + k * 3];
                }
                this.rotmatrix[j + i * 3] = n;
            }
        }
    }
    
    void reinit() {
        this.handleResize();
        this.resetParticles();
    }
    
    void centerString(final Graphics graphics, final String s, final int n) {
        graphics.drawString(s, (this.winSize.width - graphics.getFontMetrics().stringWidth(s)) / 2, n);
    }
    
    public void paint(final Graphics graphics) {
        this.cv.repaint();
    }
    
    void map3d(final double n, final double n2, final double n3, final int[] array, final int[] array2, final int n4) {
        this.map3d(n, n2, n3, array, array2, n4, this.viewMain);
    }
    
    void map3d(final double n, final double n2, final double n3, final int[] array, final int[] array2, final int n4, final Rectangle rectangle) {
        final double[] rotmatrix = this.rotmatrix;
        final double n5 = n * rotmatrix[0] + n2 * rotmatrix[3] + n3 * rotmatrix[6];
        final double n6 = n * rotmatrix[1] + n2 * rotmatrix[4] + n3 * rotmatrix[7];
        final double n7 = 5.0 - (n * rotmatrix[2] + n2 * rotmatrix[5] + n3 * rotmatrix[8]);
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
    
    double getScalingFactor(final double n, final double n2, final double n3) {
        final double[] rotmatrix = this.rotmatrix;
        final double n4 = 5.0 - (n * rotmatrix[2] + n2 * rotmatrix[5] + n3 * rotmatrix[8]);
        double n5 = this.winSize.width * this.zoom / 2.0;
        final double n6 = this.winSize.height * this.zoom / 2.0;
        final double n7 = this.winSize.width / this.winSize.height;
        if (n7 >= 1.0) {
            n5 /= n7;
        }
        return n5 / n4;
    }
    
    void unmap3d(final double[] array, final int n, final int n2, final double n3, final Rectangle rectangle) {
        double n4 = rectangle.width * this.zoom / 2.0;
        double n5 = rectangle.height * this.zoom / 2.0;
        final double n6 = rectangle.width / rectangle.height;
        if (n6 < 1.0) {
            n5 *= n6;
        }
        else {
            n4 /= n6;
        }
        final double n7 = 5.0 - n3;
        final double n8 = (n - rectangle.width / 2) * n7 / n4;
        final double n9 = -(n2 - rectangle.height / 2) * n7 / n5;
        final double[] rotmatrix = this.rotmatrix;
        array[0] = n8 * rotmatrix[0] + n9 * rotmatrix[1] + n3 * rotmatrix[2];
        array[1] = n8 * rotmatrix[3] + n9 * rotmatrix[4] + n3 * rotmatrix[5];
        array[2] = n8 * rotmatrix[6] + n9 * rotmatrix[7] + n3 * rotmatrix[8];
    }
    
    void unmap3d(final double[] array, final int n, final int n2, final double[] array2, final double[] array3, final Rectangle rectangle) {
        double n3 = rectangle.width * this.zoom / 2.0;
        double n4 = rectangle.height * this.zoom / 2.0;
        final double n5 = rectangle.width / rectangle.height;
        if (n5 < 1.0) {
            n4 *= n5;
        }
        else {
            n3 /= n5;
        }
        final double n6 = (n - rectangle.width / 2) / n3;
        final double n7 = -(n2 - rectangle.height / 2) / n4;
        final double[] rotmatrix = this.rotmatrix;
        final double n8 = n6 * rotmatrix[0] + n7 * rotmatrix[1] - rotmatrix[2];
        final double n9 = n6 * rotmatrix[3] + n7 * rotmatrix[4] - rotmatrix[5];
        final double n10 = n6 * rotmatrix[6] + n7 * rotmatrix[7] - rotmatrix[8];
        final double n11 = ((array3[0] - this.cameraPos[0]) * array2[0] + (array3[1] - this.cameraPos[1]) * array2[1] + (array3[2] - this.cameraPos[2]) * array2[2]) / (array2[0] * n8 + array2[1] * n9 + array2[2] * n10);
        array[0] = this.cameraPos[0] + n8 * n11;
        array[1] = this.cameraPos[1] + n9 * n11;
        array[2] = this.cameraPos[2] + n10 * n11;
    }
    
    void scaleworld() {
        this.scalex = this.winSize.width / 2;
        this.scaley = this.winSize.height / 2;
    }
    
    public void updateVec3Demo(final Graphics graphics) {
        final Graphics graphics2 = this.dbimage.getGraphics();
        if (this.winSize == null || this.winSize.width == 0) {
            return;
        }
        if (this.xpoints == null) {
            return;
        }
        graphics2.setColor(this.cv.getBackground());
        graphics2.fillRect(0, 0, this.winSize.width, this.winSize.height);
        graphics2.setColor(this.cv.getForeground());
        boolean b = false;
        this.curfunc.setupFrame();
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
            if (selectedIndex != 2 && selectedIndex != -2 && selectedIndex != 3 && selectedIndex != 4) {
                this.moveParticles();
                b = false;
            }
            this.currentStep = (int)(this.reverse * (this.lastTime / 30L) % 800L);
            if (this.currentStep < 0) {
                this.currentStep += 800;
            }
        }
        else {
            final double n = 0.0;
            this.lastYRot = n;
            this.lastXRot = n;
            this.lastTime = 0L;
        }
        this.drawCube(graphics2, true);
        this.unmap3d(this.cameraPos = new double[3], this.winSize.width / 2, this.winSize.height / 2, 5.0, this.viewMain);
        if (selectedIndex == 2 || selectedIndex == -2) {
            this.drawVectors(graphics2);
        }
        else if (selectedIndex == 3) {
            this.genLines();
            this.drawLines(graphics2);
        }
        else if (selectedIndex == 4) {
            this.genEquips();
            this.drawLines(graphics2);
        }
        else if (selectedIndex == -4) {
            this.drawViewPaper(graphics2);
        }
        else {
            this.drawParticles(graphics2);
        }
        graphics2.setColor(Color.gray);
        this.drawCube(graphics2, false);
        this.drawAxes(graphics2);
        this.curfunc.finishFrame();
        if (this.parseError) {
            this.centerString(graphics2, "Can't parse expression", this.winSize.height - 20);
        }
        graphics.drawImage(this.dbimage, 0, 0, this);
        final long currentTimeMillis = System.currentTimeMillis();
        ++Vec3DemoFrame.frames;
        if (Vec3DemoFrame.firsttime == 0L) {
            Vec3DemoFrame.firsttime = currentTimeMillis;
        }
        else if (currentTimeMillis - Vec3DemoFrame.firsttime > 1000L) {
            Vec3DemoFrame.framerate = Vec3DemoFrame.frames;
            Vec3DemoFrame.firsttime = currentTimeMillis;
            Vec3DemoFrame.frames = 0;
        }
        if (this.mouseDown) {
            final double n2 = 0.0;
            this.lastYRot = n2;
            this.lastXRot = n2;
        }
        else if (this.lastXRot != 0.0 || this.lastYRot != 0.0) {
            this.rotate(this.lastXRot * this.timeStep, this.lastYRot * this.timeStep);
            b = false;
        }
        if (!this.stoppedCheck.getState() && !b) {
            this.cv.repaint(this.pause);
        }
    }
    
    void drawCurrentArrow(final Graphics graphics, final int n, final int n2, final int n3, final int n4) {
        if (this.reverse == 1) {
            this.drawArrow(graphics, null, n, n2, n3, n4, 7);
        }
        else {
            this.drawArrow(graphics, null, n3, n4, n, n2, 7);
        }
    }
    
    void drawCurrentLine(final Graphics graphics, int n, int n2, int n3, int n4, int n5, final boolean b, final int n6) {
        if (n6 == -1) {
            final int n7 = n;
            final int n8 = n2;
            n = n3;
            n2 = n4;
            n3 = n7;
            n4 = n8;
        }
        int n9 = n;
        int n10 = n2;
        n5 *= 3;
        for (int i = 1; i <= n5; ++i) {
            final int n11 = (n3 - n) * i / n5 + n;
            final int n12 = (n4 - n2) * i / n5 + n2;
            graphics.setColor(Color.yellow);
            if (i == n5 && b && this.reverse == 1) {
                this.drawCurrentArrow(graphics, n9, n10, n11, n12);
            }
            else if (i == 1 && b && this.reverse == -1) {
                this.drawCurrentArrow(graphics, n9, n10, n11, n12);
            }
            else {
                graphics.setColor(this.getCurrentColor(i));
                graphics.drawLine(n9, n10, n11, n12);
            }
            n9 = n11;
            n10 = n12;
        }
    }
    
    Color getCurrentColor(final int n) {
        return ((this.currentStep / 2 + 400 - n & 0x4) > 0) ? Color.yellow : Color.darkGray;
    }
    
    void drawSphere(final Graphics graphics, final double n, final boolean b) {
        for (int n2 = 10, i = 0; i != n2; ++i) {
            final double n3 = 6.283185307179586 * i / n2;
            final double n4 = 6.283185307179586 * (i + 1) / n2;
            final double n5 = n * Math.sin(n3);
            final double n6 = n * Math.cos(n3);
            final double n7 = n * Math.sin(n4);
            final double n8 = n * Math.cos(n4);
            if (this.backFacing(n6, n5, 0.0, n6, n5, 0.0) == b) {
                this.map3d(n6, n5, 0.0, this.xpoints, this.ypoints, 0);
                this.map3d(n8, n7, 0.0, this.xpoints, this.ypoints, 1);
                graphics.drawLine(this.xpoints[0], this.ypoints[0], this.xpoints[1], this.ypoints[1]);
            }
            if (this.backFacing(0.0, n6, n5, 0.0, n6, n5) == b) {
                this.map3d(0.0, n6, n5, this.xpoints, this.ypoints, 0);
                this.map3d(0.0, n8, n7, this.xpoints, this.ypoints, 1);
                graphics.drawLine(this.xpoints[0], this.ypoints[0], this.xpoints[1], this.ypoints[1]);
            }
            if (this.backFacing(n6, 0.0, n5, n6, 0.0, n5) == b) {
                this.map3d(n6, 0.0, n5, this.xpoints, this.ypoints, 0);
                this.map3d(n8, 0.0, n7, this.xpoints, this.ypoints, 1);
                graphics.drawLine(this.xpoints[0], this.ypoints[0], this.xpoints[1], this.ypoints[1]);
            }
        }
    }
    
    void fillSphere(final Graphics graphics, final double n, final double n2) {
        for (int n3 = 20, i = 0; i != n3; ++i) {
            final double n4 = 3.141592653589793 * i / n3;
            final double n5 = 3.141592653589793 * (i + 1) / n3;
            final double n6 = n * Math.cos(n4);
            final double n7 = n * Math.sin(n4);
            final double n8 = n * Math.cos(n5);
            final double n9 = n * Math.sin(n5);
            double n10 = 1.0;
            double n11 = 0.0;
            for (int j = 0; j != n3; ++j) {
                final double n12 = 6.283185307179586 * (j + 1) / n3;
                final double cos = Math.cos(n12);
                final double sin = Math.sin(n12);
                final double n13 = n7 * n10;
                final double n14 = n7 * n11;
                final double n15 = n6;
                final double n16 = (this.cameraPos[0] - (n13 + n2)) * n13 + (this.cameraPos[1] - n14) * n14 + (this.cameraPos[2] - n15) * n15;
                if (n16 > 0.0) {
                    int n17 = (int)(n16 / n * 40.0);
                    if (n17 > 255) {
                        n17 = 255;
                    }
                    graphics.setColor(new Color(n17, n17, 0));
                    this.map3d(n2 + n13, n14, n15, this.xpoints, this.ypoints, 0);
                    this.map3d(n2 + n7 * cos, n7 * sin, n6, this.xpoints, this.ypoints, 1);
                    this.map3d(n2 + n9 * cos, n9 * sin, n8, this.xpoints, this.ypoints, 2);
                    this.map3d(n2 + n9 * n10, n9 * n11, n8, this.xpoints, this.ypoints, 3);
                    graphics.fillPolygon(this.xpoints, this.ypoints, 4);
                }
                n10 = cos;
                n11 = sin;
            }
        }
    }
    
    void drawCylinder(final Graphics graphics, final double n, final double n2, final boolean b) {
        for (int n3 = 10, i = 0; i != n3; ++i) {
            final double n4 = 6.283185307179586 * i / n3;
            final double n5 = 6.283185307179586 * (i + 1) / n3;
            final double n6 = n * Math.sin(n4);
            final double n7 = n * Math.cos(n4);
            final double n8 = n * Math.sin(n5);
            final double n9 = n * Math.cos(n5);
            if (this.backFacing(n7, n6, 0.0, n7, n6, 0.0) == b) {
                this.map3d(n2 + n7, n6, -1.0, this.xpoints, this.ypoints, 0);
                this.map3d(n2 + n9, n8, -1.0, this.xpoints, this.ypoints, 1);
                this.map3d(n2 + n9, n8, 1.0, this.xpoints, this.ypoints, 2);
                this.map3d(n2 + n7, n6, 1.0, this.xpoints, this.ypoints, 3);
                graphics.drawPolygon(this.xpoints, this.ypoints, 4);
            }
        }
    }
    
    void setFaceColor(final Graphics graphics, final double n) {
        int n2 = 32 + (int)(n * 40.0);
        if (n2 > 255) {
            n2 = 255;
        }
        graphics.setColor(new Color(n2, n2, 0));
    }
    
    void fillCylinder(final Graphics graphics, final double n, final double n2) {
        final int n3 = 30;
        final int[][] array = new int[4][n3];
        for (int i = 0; i != n3; ++i) {
            final double n4 = 6.283185307179586 * i / n3;
            final double n5 = 6.283185307179586 * (i + 1) / n3;
            final double n6 = n * Math.sin(n4);
            final double n7 = n * Math.cos(n4);
            final double n8 = n * Math.sin(n5);
            final double n9 = n * Math.cos(n5);
            final double n10 = (this.cameraPos[0] - (n2 + n7)) * n7 + (this.cameraPos[1] - n6) * n6;
            if (n10 > 0.0) {
                this.setFaceColor(graphics, n10 / n);
            }
            this.map3d(n2 + n7, n6, -1.0, this.xpoints, this.ypoints, 0);
            this.map3d(n2 + n9, n8, -1.0, this.xpoints, this.ypoints, 1);
            this.map3d(n2 + n9, n8, 1.0, this.xpoints, this.ypoints, 2);
            this.map3d(n2 + n7, n6, 1.0, this.xpoints, this.ypoints, 3);
            array[0][i] = this.xpoints[0];
            array[1][i] = this.ypoints[0];
            array[2][i] = this.xpoints[3];
            array[3][i] = this.ypoints[3];
            if (n10 > 0.0) {
                graphics.fillPolygon(this.xpoints, this.ypoints, 4);
            }
        }
        if (!this.backFacing(0.0, 0.0, 1.0, 0.0, 0.0, 1.0)) {
            this.setFaceColor(graphics, this.cameraPos[2]);
            graphics.fillPolygon(array[2], array[3], n3);
        }
        else if (!this.backFacing(0.0, 0.0, -1.0, 0.0, 0.0, -1.0)) {
            this.setFaceColor(graphics, -this.cameraPos[2]);
            graphics.fillPolygon(array[0], array[1], n3);
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
    
    void drawViewPaper(final Graphics graphics) {
        final int n = 24 + this.vecDensityBar.getValue() * 56 / 64;
        final double sliceval = this.sliceval;
        final double[] array = new double[3];
        final double[] array2 = new double[3];
        int n2 = this.sliceChooser.getSelectedIndex() - 1;
        if (n2 < 0) {
            n2 = 0;
        }
        final int n3 = (n2 == 0) ? 1 : 0;
        final int n4 = (n2 == 2) ? 1 : 2;
        for (int i = 0; i != n; ++i) {
            final double n5 = i * 2.0 / n - 1.0;
            final double n6 = (i + 1.0) * 2.0 / n - 1.0;
            for (int j = 0; j != n; ++j) {
                final double n7 = j * 2.0 / n - 1.0;
                final double n8 = (j + 1.0) * 2.0 / n - 1.0;
                array[n3] = n5;
                array[n4] = n7;
                array[n2] = sliceval;
                this.curfunc.getField(array2, array);
                int n9 = (int)((Math.sqrt(array2[n3] * array2[n3] + array2[n4] * array2[n4]) / 2.0 - ((array2[n2] < 0.0) ? (-array2[n2]) : array2[n2])) * this.strengthBar.getValue() * 20000.0 + 128.0);
                if (n9 < 0) {
                    n9 = 0;
                }
                if (n9 > 255) {
                    n9 = 255;
                }
                graphics.setColor(new Color(0, n9, 0));
                this.map3d(array[0], array[1], array[2], this.xpoints, this.ypoints, 0);
                array[n3] = n6;
                this.map3d(array[0], array[1], array[2], this.xpoints, this.ypoints, 1);
                array[n4] = n8;
                this.map3d(array[0], array[1], array[2], this.xpoints, this.ypoints, 2);
                array[n3] = n5;
                this.map3d(array[0], array[1], array[2], this.xpoints, this.ypoints, 3);
                graphics.fillPolygon(this.xpoints, this.ypoints, 4);
            }
        }
    }
    
    void drawVectors(final Graphics g) {
        final DrawData drawData = new DrawData();
        drawData.mult = this.strengthBar.getValue() * 80.0;
        drawData.g = g;
        drawData.field = new double[3];
        drawData.vv = new double[3];
        this.vectorSpacing = this.vecDensityBar.getValue();
        final int selectedIndex = this.sliceChooser.getSelectedIndex();
        final boolean b = selectedIndex > 0;
        final double[] array = new double[3];
        if (this.vectors == null && b) {
            this.vectors = new FieldVector[this.vectorSpacing * this.vectorSpacing];
        }
        this.vecCount = 0;
        if (!b) {
            this.vectorSpacing /= 2;
            if (this.vectors == null) {
                this.vectors = new FieldVector[this.vectorSpacing * this.vectorSpacing * this.vectorSpacing];
            }
            for (int i = 0; i != this.vectorSpacing; ++i) {
                array[0] = i * (2.0 / (this.vectorSpacing - 1)) - 1.0;
                for (int j = 0; j != this.vectorSpacing; ++j) {
                    array[1] = j * (2.0 / (this.vectorSpacing - 1)) - 1.0;
                    for (int k = 0; k != this.vectorSpacing; ++k) {
                        array[2] = k * (2.0 / (this.vectorSpacing - 1)) - 1.0;
                        this.drawVector(drawData, array);
                    }
                }
            }
        }
        else {
            final int n = (selectedIndex == 1) ? 1 : 0;
            final int n2 = (selectedIndex == 3) ? 1 : 2;
            array[selectedIndex - 1] = this.sliceval;
            for (int l = 0; l != this.vectorSpacing; ++l) {
                array[n] = l * (2.0 / (this.vectorSpacing - 1)) - 1.0;
                for (int n3 = 0; n3 != this.vectorSpacing; ++n3) {
                    array[n2] = n3 * (2.0 / (this.vectorSpacing - 1)) - 1.0;
                    this.drawVector(drawData, array);
                }
            }
        }
        this.curfunc.render(g);
    }
    
    void genLines() {
        if (this.vectors != null) {
            return;
        }
        final double n = 10.0;
        this.fieldStrength = n;
        this.partMult = n;
        this.vecCount = 0;
        int value = this.lineDensityBar.getValue();
        if (value < 3) {
            value = 3;
        }
        if (value > 16) {
            value = 16;
        }
        final int selectedIndex = this.sliceChooser.getSelectedIndex();
        final boolean b = selectedIndex > 0;
        if (b) {
            value *= 2;
        }
        final int n2 = b ? (30 * value * value) : (30 * value * value * value);
        this.vectors = new FieldVector[n2];
        final double n3 = 160 * this.strengthBar.getValue();
        final boolean[][][] array = new boolean[value][value][value];
        final double n4 = value / 2.0;
        if (b) {
            final int n5 = (int)((this.sliceval + 1.0) * n4);
            for (int i = 0; i != value; ++i) {
                for (int j = 0; j != value; ++j) {
                    for (int k = 0; k != value; ++k) {
                        switch (selectedIndex) {
                            case 1: {
                                array[i][j][k] = (i != n5);
                                break;
                            }
                            case 2: {
                                array[i][j][k] = (j != n5);
                                break;
                            }
                            case 3: {
                                array[i][j][k] = (k != n5);
                                break;
                            }
                        }
                    }
                }
            }
        }
        final double[] array2 = new double[3];
        final double[] array3 = new double[3];
        final Particle particle = new Particle();
        particle.lifetime = -1.0;
        particle.stepsize = 10.0;
        int n6 = -1;
        int n7 = 0;
        double n8 = 0.0;
    Label_0495_Outer:
        for (int l = 0; l != n2; ++l) {
            Label_0618: {
                if (particle.lifetime < 0.0) {
                    particle.lifetime = 1.0;
                    particle.stepsize = 10.0;
                    n7 = 0;
                    n8 = 0.0;
                    if (n6 == 1) {
                        for (int n9 = 0; n9 != 3; ++n9) {
                            particle.pos[n9] = array2[n9];
                        }
                        n6 = -1;
                        continue Label_0495_Outer;
                    }
                    n6 = 1;
                    int n10 = 0;
                    int n11 = 0;
                    int n12 = 0;
                    while (true) {
                        while (array[n10][n11][n12]) {
                            if (++n10 < value) {
                                continue Label_0495_Outer;
                            }
                            n10 = 0;
                            if (++n11 < value) {
                                continue Label_0495_Outer;
                            }
                            n11 = 0;
                            if (++n12 < value) {
                                continue Label_0495_Outer;
                            }
                            if (n12 == value) {
                                break Label_0495_Outer;
                            }
                            array[n10][n11][n12] = true;
                            final double n13 = 0.5 / n4;
                            array2[0] = (particle.pos[0] = n10 / n4 - 1.0 + n13);
                            array2[1] = (particle.pos[1] = n11 / n4 - 1.0 + n13);
                            array2[2] = (particle.pos[2] = n12 / n4 - 1.0 + n13);
                            if (b) {
                                array2[selectedIndex - 1] = (particle.pos[selectedIndex - 1] = this.sliceval);
                            }
                            break Label_0618;
                        }
                        continue;
                    }
                }
            }
            FieldVector fieldVector = this.vectors[this.vecCount];
            if (fieldVector == null) {
                final FieldVector[] vectors = this.vectors;
                final int vecCount = this.vecCount;
                final FieldVector fieldVector2 = new FieldVector();
                vectors[vecCount] = fieldVector2;
                fieldVector = fieldVector2;
                fieldVector.p1 = new double[3];
                fieldVector.p2 = new double[3];
            }
            ++this.vecCount;
            fieldVector.p1[0] = particle.pos[0];
            fieldVector.p1[1] = particle.pos[1];
            fieldVector.p1[2] = particle.pos[2];
            final double[] pos = particle.pos;
            this.lineSegment(particle, n6);
            if (particle.lifetime < 0.0) {
                --this.vecCount;
            }
            else {
                final int n14 = (int)((pos[0] + 1.0) * n4);
                final int n15 = (int)((pos[1] + 1.0) * n4);
                final int n16 = (int)((pos[2] + 1.0) * n4);
                if (!array[n14][n15][n16]) {
                    --n7;
                }
                array[n14][n15][n16] = true;
                fieldVector.p2[0] = particle.pos[0];
                fieldVector.p2[1] = particle.pos[1];
                fieldVector.p2[2] = particle.pos[2];
                double n17 = n3 * particle.phi;
                if (n17 > 2.0) {
                    n17 = 2.0;
                }
                fieldVector.col = (int)(n17 * 255.0);
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
    
    void drawLines(final Graphics graphics) {
        for (int i = 0; i != this.vecCount; ++i) {
            final FieldVector fieldVector = this.vectors[i];
            final double[] p = fieldVector.p1;
            this.map3d(p[0], p[1], p[2], this.xpoints, this.ypoints, 0);
            final int viewPri = this.curfunc.getViewPri(this.cameraPos, p);
            final double[] p2 = fieldVector.p2;
            this.map3d(p2[0], p2[1], p2[2], this.xpoints, this.ypoints, 1);
            fieldVector.sx1 = this.xpoints[0];
            fieldVector.sy1 = this.ypoints[0];
            fieldVector.sx2 = this.xpoints[1];
            fieldVector.sy2 = this.ypoints[1];
            final int viewPri2 = this.curfunc.getViewPri(this.cameraPos, p2);
            fieldVector.viewPri = ((viewPri > viewPri2) ? viewPri : viewPri2);
        }
        this.curfunc.render(graphics);
    }
    
    boolean canSubdivide(final EquipPoint equipPoint, final EquipPoint equipPoint2) {
        return this.dist2(equipPoint.pos, equipPoint2.pos) > 0.0016;
    }
    
    void genEquips() {
        if (this.vectors != null) {
            return;
        }
        final double n = 10.0;
        this.fieldStrength = n;
        this.partMult = n;
        this.vecCount = 0;
        final int selectedIndex = this.sliceChooser.getSelectedIndex();
        this.vectors = new FieldVector[10000];
        this.potfield = new double[3];
        final EquipPoint[] array = new EquipPoint[4];
        for (int i = 0; i != 4; ++i) {
            array[i] = new EquipPoint();
        }
        if (selectedIndex == 0) {
            final int n2 = 3;
            for (int j = -n2; j <= n2; ++j) {
                this.genEquipPlane(array, j / n2, 1);
            }
            for (int k = -n2; k <= n2; ++k) {
                this.genEquipPlane(array, k / n2, 2);
            }
            for (int l = -n2; l <= n2; ++l) {
                this.genEquipPlane(array, l / n2, 3);
            }
        }
        else {
            this.genEquipPlane(array, this.sliceval, selectedIndex);
        }
    }
    
    void genEquipPlane(final EquipPoint[] array, final double n, int n2) {
        final int n3 = (n2 == 1) ? 1 : 0;
        final int n4 = (n2 == 3) ? 1 : 2;
        --n2;
        final int n5 = (this.sliceChooser.getSelectedIndex() == 0) ? 12 : 24;
        final double n6 = 2.0 / n5;
        final double[][] array2 = new double[n5 + 1][n5 + 1];
        for (int i = 0; i <= n5; ++i) {
            for (int j = 0; j <= n5; ++j) {
                array[0].set(n3, n4, n2, i * n6 - 1.0, j * n6 - 1.0, n);
                this.curfunc.getField(this.potfield, array[0].pos);
                array2[i][j] = this.reverse * this.potfield[0];
            }
        }
        for (int k = 0; k != n5; ++k) {
            for (int l = 0; l != n5; ++l) {
                final double n7 = k * n6 - 1.0;
                final double n8 = l * n6 - 1.0;
                final double n9 = (k + 1) * n6 - 1.0;
                final double n10 = (l + 1) * n6 - 1.0;
                array[0].set(n3, n4, n2, n7, n8, n);
                array[1].set(n3, n4, n2, n9, n8, n);
                array[2].set(n3, n4, n2, n7, n10, n);
                array[3].set(n3, n4, n2, n9, n10, n);
                array[0].setPot(array2[k][l]);
                array[1].setPot(array2[k + 1][l]);
                array[2].setPot(array2[k][l + 1]);
                array[3].setPot(array2[k + 1][l + 1]);
                this.tryEdges(array[0], array[1], array[2], array[3]);
            }
        }
    }
    
    double max(final double n, final double n2) {
        return (n > n2) ? n : n2;
    }
    
    double min(final double n, final double n2) {
        return (n < n2) ? n : n2;
    }
    
    boolean shouldSubdivide(final EquipPoint equipPoint, final EquipPoint equipPoint2, final EquipPoint equipPoint3, final EquipPoint equipPoint4) {
        return !equipPoint.inRange() || !equipPoint2.inRange() || !equipPoint3.inRange() || !equipPoint4.inRange() || this.max(this.max(equipPoint.pot, equipPoint2.pot), this.max(equipPoint3.pot, equipPoint4.pot)) - this.min(this.min(equipPoint.pot, equipPoint2.pot), this.min(equipPoint3.pot, equipPoint4.pot)) > 0.3;
    }
    
    void tryEdges(final EquipPoint equipPoint, final EquipPoint equipPoint2, final EquipPoint equipPoint3, final EquipPoint equipPoint4) {
        if (this.shouldSubdivide(equipPoint, equipPoint2, equipPoint3, equipPoint4) && this.canSubdivide(equipPoint, equipPoint2)) {
            final EquipPoint equipPoint5 = new EquipPoint(equipPoint, equipPoint2);
            final EquipPoint equipPoint6 = new EquipPoint(equipPoint, equipPoint3);
            final EquipPoint equipPoint7 = new EquipPoint(equipPoint2, equipPoint4);
            final EquipPoint equipPoint8 = new EquipPoint(equipPoint3, equipPoint4);
            final EquipPoint equipPoint9 = new EquipPoint(equipPoint5, equipPoint8);
            this.tryEdges(equipPoint, equipPoint5, equipPoint6, equipPoint9);
            this.tryEdges(equipPoint5, equipPoint2, equipPoint9, equipPoint7);
            this.tryEdges(equipPoint6, equipPoint9, equipPoint3, equipPoint8);
            this.tryEdges(equipPoint9, equipPoint7, equipPoint8, equipPoint4);
            return;
        }
        this.tryEdge(equipPoint, equipPoint2, equipPoint3, equipPoint4);
        this.tryEdge(equipPoint, equipPoint2, equipPoint, equipPoint3);
        this.tryEdge(equipPoint, equipPoint2, equipPoint2, equipPoint4);
        this.tryEdge(equipPoint, equipPoint3, equipPoint2, equipPoint4);
        this.tryEdge(equipPoint, equipPoint3, equipPoint3, equipPoint4);
        this.tryEdge(equipPoint2, equipPoint4, equipPoint3, equipPoint4);
    }
    
    boolean spanning(final EquipPoint equipPoint, final EquipPoint equipPoint2, final double n) {
        return equipPoint.pot != equipPoint2.pot && equipPoint.valid() && equipPoint2.valid() && (equipPoint.pot >= n || equipPoint2.pot >= n) && (equipPoint.pot <= n || equipPoint2.pot <= n);
    }
    
    void interpPoint(final EquipPoint equipPoint, final EquipPoint equipPoint2, final double n, final double[] array) {
        final double n2 = (n - equipPoint.pot) / (equipPoint2.pot - equipPoint.pot);
        final double n3 = 1.0 - n2;
        for (int i = 0; i != 3; ++i) {
            array[i] = equipPoint.pos[i] * n3 + equipPoint2.pos[i] * n2;
        }
    }
    
    void tryEdge(final EquipPoint equipPoint, final EquipPoint equipPoint2, final EquipPoint equipPoint3, final EquipPoint equipPoint4) {
        if (this.sliceChooser.getSelectedIndex() == 0) {
            this.tryEdge(equipPoint, equipPoint2, equipPoint3, equipPoint4, (this.potentialBar.getValue() - 500) / 500.0);
        }
        else {
            for (int i = -20; i <= 20; ++i) {
                this.tryEdge(equipPoint, equipPoint2, equipPoint3, equipPoint4, i / 20.0);
            }
        }
    }
    
    void tryEdge(final EquipPoint equipPoint, final EquipPoint equipPoint2, final EquipPoint equipPoint3, final EquipPoint equipPoint4, final double n) {
        if (!this.spanning(equipPoint, equipPoint2, n) || !this.spanning(equipPoint3, equipPoint4, n)) {
            return;
        }
        if (this.vecCount == 10000) {
            return;
        }
        FieldVector fieldVector = this.vectors[this.vecCount];
        if (fieldVector == null) {
            final FieldVector[] vectors = this.vectors;
            final int vecCount = this.vecCount;
            final FieldVector fieldVector2 = new FieldVector();
            vectors[vecCount] = fieldVector2;
            fieldVector = fieldVector2;
            fieldVector.p1 = new double[3];
            fieldVector.p2 = new double[3];
        }
        ++this.vecCount;
        this.interpPoint(equipPoint, equipPoint2, n, fieldVector.p1);
        this.interpPoint(equipPoint3, equipPoint4, n, fieldVector.p2);
        fieldVector.col = 255 + (int)(255.0 * n);
    }
    
    void drawVector(final DrawData drawData, final double[] array) {
        final double[] field = drawData.field;
        this.curfunc.getField(field, array);
        final double sqrt = Math.sqrt(field[0] * field[0] + field[1] * field[1] + field[2] * field[2]);
        final double n = sqrt * this.reverse;
        if (sqrt > 0.0) {
            final double[] array2 = field;
            final int n2 = 0;
            array2[n2] /= n;
            final double[] array3 = field;
            final int n3 = 1;
            array3[n3] /= n;
            final double[] array4 = field;
            final int n4 = 2;
            array4[n4] /= n;
        }
        double n5 = sqrt * drawData.mult;
        if (n5 > 2.0) {
            n5 = 2.0;
        }
        final int col = (int)(n5 * 255.0);
        final double n6 = 1.0 / (this.vectorSpacing - 1);
        this.map3d(array[0], array[1], array[2], this.xpoints, this.ypoints, 0);
        final double[] vv = drawData.vv;
        vv[0] = array[0] + n6 * field[0];
        vv[1] = array[1] + n6 * field[1];
        vv[2] = array[2] + n6 * field[2];
        this.map3d(vv[0], vv[1], vv[2], this.xpoints, this.ypoints, 1);
        FieldVector fieldVector = this.vectors[this.vecCount];
        if (fieldVector == null) {
            final FieldVector[] vectors = this.vectors;
            final int vecCount = this.vecCount;
            final FieldVector fieldVector2 = new FieldVector();
            vectors[vecCount] = fieldVector2;
            fieldVector = fieldVector2;
        }
        fieldVector.sx1 = this.xpoints[0];
        fieldVector.sy1 = this.ypoints[0];
        fieldVector.sx2 = this.xpoints[1];
        fieldVector.sy2 = this.ypoints[1];
        fieldVector.col = col;
        ++this.vecCount;
        final int viewPri = this.curfunc.getViewPri(this.cameraPos, array);
        if (!this.curfunc.noSplitFieldVectors()) {
            fieldVector.viewPri = viewPri;
        }
        else {
            fieldVector.viewPri = ((viewPri == this.curfunc.getViewPri(this.cameraPos, vv)) ? viewPri : -1);
        }
    }
    
    void drawParticles(final Graphics graphics) {
        for (int particleCount = this.getParticleCount(), i = 0; i < particleCount; ++i) {
            final Particle particle = this.particles[i];
            particle.viewPri = this.curfunc.getViewPri(this.cameraPos, particle.pos);
        }
        this.curfunc.render(graphics);
    }
    
    void moveParticles() {
        this.fieldStrength = this.strengthBar.getValue();
        int n = 0;
        final int particleCount = this.getParticleCount();
        final int selectedIndex = this.sliceChooser.getSelectedIndex();
        final boolean b = selectedIndex > 0;
        this.partMult = this.fieldStrength * this.reverse * this.timeStep;
        for (int i = 0; i != particleCount; ++i) {
            final Particle particle = this.particles[i];
            this.removeFromDensityGroup(particle);
            this.moveParticle(particle);
            final double[] pos = particle.pos;
            Label_0179: {
                if (pos[0] >= -1.0 && pos[0] < 1.0 && pos[1] >= -1.0 && pos[1] < 1.0 && pos[2] >= -1.0 && pos[2] < 1.0) {
                    final Particle particle2 = particle;
                    final double lifetime = particle2.lifetime - this.timeStep;
                    particle2.lifetime = lifetime;
                    if (lifetime >= 0.0) {
                        break Label_0179;
                    }
                }
                this.positionParticle(particle);
            }
            if (b) {
                pos[selectedIndex - 1] = this.sliceval;
            }
            final int addToDensityGroup = this.addToDensityGroup(particle);
            if (addToDensityGroup > n) {
                n = addToDensityGroup;
            }
        }
        final boolean b2 = this.dispChooser.getSelectedIndex() == 1;
        int n2 = 10 * this.getParticleCount() / 64;
        if (b) {
            n2 = 4 * this.getParticleCount() / 16;
        }
        if (!b2 && this.curfunc.redistribute() && n > n2) {
            this.redistribute(n);
        }
    }
    
    void drawCube(final Graphics graphics, final boolean b) {
        final int selectedIndex = this.sliceChooser.getSelectedIndex();
        int n = b ? 0 : 8;
        for (int i = 0; i != 6; ++i) {
            final int n2 = (i == 0) ? -1 : ((i == 1) ? 1 : 0);
            final int n3 = (i == 2) ? -1 : ((i == 3) ? 1 : 0);
            final int n4 = (i == 4) ? -1 : ((i == 5) ? 1 : 0);
            if (b || !this.backFacing(n2, n3, n4, n2, n3, n4)) {
                final double[] array = new double[3];
                for (int j = 0; j != 4; ++j) {
                    this.computeFace(i, j, array);
                    this.map3d(array[0], array[1], array[2], this.xpoints, this.ypoints, j);
                }
                graphics.setColor(Color.gray);
                graphics.drawPolygon(this.xpoints, this.ypoints, 4);
                if (selectedIndex != 0 && i / 2 != selectedIndex - 1) {
                    if (this.selectedSlice) {
                        graphics.setColor(Color.yellow);
                    }
                    final boolean b2 = selectedIndex == 1;
                    final int n5 = (selectedIndex == 3) ? 1 : 2;
                    this.computeFace(i, 0, array);
                    array[selectedIndex - 1] = this.sliceval;
                    this.map3d(array[0], array[1], array[2], this.slicerPoints[0], this.slicerPoints[1], n);
                    this.computeFace(i, 2, array);
                    array[selectedIndex - 1] = this.sliceval;
                    this.map3d(array[0], array[1], array[2], this.slicerPoints[0], this.slicerPoints[1], n + 1);
                    graphics.drawLine(this.slicerPoints[0][n], this.slicerPoints[1][n], this.slicerPoints[0][n + 1], this.slicerPoints[1][n + 1]);
                    if (b) {
                        this.sliceFaces[n / 2][0] = n2;
                        this.sliceFaces[n / 2][1] = n3;
                        this.sliceFaces[n / 2][2] = n4;
                        n += 2;
                    }
                }
            }
        }
    }
    
    void computeFace(final int n, int n2, final double[] array) {
        final int n3 = n >> 1;
        array[n3] = (((n & 0x1) == 0x0) ? -1.0 : 1.0);
        for (int i = 0; i != 3; ++i) {
            if (i != n3) {
                array[i] = (((n2 >> 1 ^ (n2 & 0x1)) == 0x0) ? -1.0 : 1.0);
                n2 >>= 1;
            }
        }
    }
    
    void renderItems(final Graphics graphics, final int n) {
        graphics.setColor(Color.white);
        final int selectedIndex = this.dispChooser.getSelectedIndex();
        if (selectedIndex == 2 || selectedIndex == -2) {
            for (int i = 0; i != this.vecCount; ++i) {
                final FieldVector fieldVector = this.vectors[i];
                if (fieldVector.viewPri == n) {
                    graphics.setColor(this.fieldColors[fieldVector.col]);
                    this.drawArrow(graphics, null, fieldVector.sx1, fieldVector.sy1, fieldVector.sx2, fieldVector.sy2, 2);
                }
            }
            return;
        }
        if (selectedIndex == 3 || selectedIndex == 4) {
            graphics.setColor(Color.white);
            final Color[] array = (selectedIndex == 4) ? this.equipColors : this.fieldColors;
            for (int j = 0; j != this.vecCount; ++j) {
                final FieldVector fieldVector2 = this.vectors[j];
                if (fieldVector2.viewPri == n) {
                    if (fieldVector2.sx1 != fieldVector2.sx2 || fieldVector2.sy1 != fieldVector2.sy2) {
                        graphics.setColor(array[fieldVector2.col]);
                        graphics.drawLine(fieldVector2.sx1, fieldVector2.sy1, fieldVector2.sx2, fieldVector2.sy2);
                    }
                }
            }
            return;
        }
        final int particleCount = this.getParticleCount();
        this.wooft += 0.3;
        for (int k = 0; k < particleCount; ++k) {
            final Particle particle = this.particles[k];
            if (particle.viewPri == n) {
                final double[] pos = particle.pos;
                this.map3d(pos[0], pos[1], pos[2], this.xpoints, this.ypoints, 0);
                if (this.xpoints[0] >= 0 && this.xpoints[0] < this.winSize.width && this.ypoints[0] >= 0) {
                    if (this.ypoints[0] < this.winSize.height) {
                        if (selectedIndex == -3) {
                            final double cos = Math.cos(particle.phi);
                            final double sin = Math.sin(particle.phi);
                            final double cos2 = Math.cos(particle.theta);
                            final double sin2 = Math.sin(particle.theta);
                            final double n2 = 0.08;
                            this.map3d(pos[0] + sin2 * cos * n2, pos[1] + sin2 * sin * n2, pos[2] + cos2 * n2, this.xpoints, this.ypoints, 1);
                            this.drawArrow(graphics, null, this.xpoints[0], this.ypoints[0], this.xpoints[1], this.ypoints[1], 2);
                        }
                        else {
                            graphics.fillRect(this.xpoints[0], this.ypoints[0] - 1, 2, 2);
                        }
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
    
    boolean backFacing(final double n, final double n2, final double n3, final double n4, final double n5, final double n6) {
        return (this.cameraPos[0] - n) * n4 + (this.cameraPos[1] - n2) * n5 + (this.cameraPos[2] - n3) * n6 <= 0.0;
    }
    
    int intersectSphere(final double[] array, final double n, final double n2, final double n3, final double n4) {
        return this.intersectSphere(array, n, n2, n3, 0.0, 0.0, 0.0, n4);
    }
    
    int intersectSphere(final double[] array, final double n, final double n2, final double n3, final double n4, final double n5, final double n6, final double n7) {
        final double n8 = n - array[0];
        final double n9 = n2 - array[1];
        final double n10 = n3 - array[2];
        final double n11 = array[0] - n4;
        final double n12 = array[1] - n5;
        final double n13 = array[2] - n6;
        final double n14 = n8 * n8 + n9 * n9 + n10 * n10;
        final double n15 = 2.0 * (n8 * n11 + n9 * n12 + n10 * n13);
        final double n16 = n15 * n15 - 4.0 * n14 * (n11 * n11 + n12 * n12 + n13 * n13 - n7 * n7);
        if (n16 < 0.0) {
            return 0;
        }
        final double sqrt = Math.sqrt(n16);
        final double n17 = (-n15 - sqrt) / (2.0 * n14);
        final double n18 = (-n15 + sqrt) / (2.0 * n14);
        if (n17 < 1.0 && this.inViewBox(n17, array, n8, n9, n10)) {
            return (n18 < 1.0) ? 2 : 1;
        }
        return 0;
    }
    
    double intersectZPlane(final double[] array, final double n, final double n2, final double n3, final double n4) {
        final double n5 = n2 - array[0];
        final double n6 = n3 - array[1];
        final double n7 = n4 - array[2];
        final double intersectionDistance = -(array[2] + n) / n7;
        this.intersectionDistance = intersectionDistance;
        final double n8 = intersectionDistance;
        if (n8 > 1.0) {
            return 0.0;
        }
        if (!this.inViewBox(n8, array, n5, n6, n7)) {
            return 0.0;
        }
        return 2.0;
    }
    
    boolean inViewBox(final double n, final double[] array, final double n2, final double n3, final double n4) {
        if (n < 0.0) {
            return false;
        }
        final double[] intersection = this.intersection;
        final int n5 = 0;
        final double n6 = array[0] + n2 * n;
        intersection[n5] = n6;
        final double n7 = n6;
        final double[] intersection2 = this.intersection;
        final int n8 = 1;
        final double n9 = array[1] + n3 * n;
        intersection2[n8] = n9;
        final double n10 = n9;
        final double[] intersection3 = this.intersection;
        final int n11 = 2;
        final double n12 = array[2] + n4 * n;
        intersection3[n11] = n12;
        final double n13 = n12;
        return n7 >= -1.0 && n7 <= 1.0 && n10 >= -1.0 && n10 <= 1.0 && n13 >= -1.0 && n13 <= 1.0;
    }
    
    int intersectCylinder(final double[] array, final double n, final double n2, final double n3, final double n4, final boolean b) {
        return this.intersectCylinder(array, n, n2, n3, 0.0, 0.0, n4, b);
    }
    
    int intersectCylinder(final double[] array, final double n, final double n2, final double n3, final double n4, final double n5, final double n6, final boolean b) {
        final double n7 = n - array[0];
        final double n8 = n2 - array[1];
        final double n9 = array[0] - n4;
        final double n10 = array[1] - n5;
        final double n11 = n7 * n7 + n8 * n8;
        final double n12 = 2.0 * (n7 * n9 + n8 * n10);
        final double n13 = n12 * n12 - 4.0 * n11 * (n9 * n9 + n10 * n10 - n6 * n6);
        if (n13 < 0.0) {
            return 0;
        }
        final double sqrt = Math.sqrt(n13);
        final double n14 = (-n12 - sqrt) / (2.0 * n11);
        final double n15 = (-n12 + sqrt) / (2.0 * n11);
        if (n14 > 1.0) {
            return 0;
        }
        if (!b || this.inViewBox(n14, array, n7, n8, n3 - array[2])) {
            return (n15 < 1.0) ? 2 : 1;
        }
        if (n15 > 1.0) {
            return 2;
        }
        if (this.inViewBox(n15, array, n7, n8, n3 - array[2])) {
            return 2;
        }
        return 0;
    }
    
    void redistribute(final int n) {
        if (n < 5) {
            return;
        }
        ++this.rediscount;
        final int n2 = 10 * this.getParticleCount() / 64;
        int n3 = 0;
        for (int particleCount = this.getParticleCount(), i = this.rediscount % 4; i < particleCount; i += 4) {
            final Particle particle = this.particles[i];
            if (this.density[(int)((particle.pos[0] + 1.0) * 2.0)][(int)((particle.pos[1] + 1.0) * 2.0)][(int)((particle.pos[2] + 1.0) * 2.0)] > n2) {
                particle.lifetime = -1.0;
                ++n3;
            }
        }
    }
    
    double distance(final Particle particle) {
        return this.distance(particle.pos[0], particle.pos[1], particle.pos[2]);
    }
    
    double distance(final double[] array) {
        return this.distance(array[0], array[1], array[2]);
    }
    
    double distance(final double n, final double n2, final double n3) {
        return Math.sqrt(n * n + n2 * n2 + n3 * n3 + 1.0E-9);
    }
    
    double distance(final double n, final double n2) {
        return Math.sqrt(n * n + n2 * n2 + 1.0E-9);
    }
    
    void rotateParticleAdd(final double[] array, final double[] array2, final double n, final double n2, final double n3) {
        final int n4 = 0;
        array[n4] += -n * (array2[1] - n3);
        final int n5 = 1;
        array[n5] += n * (array2[0] - n2);
        final int n6 = 2;
        array[n6] += 0.0;
    }
    
    void rotateParticle(final double[] array, final double[] array2, final double n) {
        array[0] = -n * array2[1];
        array[1] = n * array2[0];
        array[2] = 0.0;
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
    }
    
    public boolean handleEvent(final Event event) {
        if (event.id == 201) {
            this.applet.destroyFrame();
            return true;
        }
        return super.handleEvent(event);
    }
    
    public void adjustmentValueChanged(final AdjustmentEvent adjustmentEvent) {
        this.vectors = null;
        System.out.print(((Scrollbar)adjustmentEvent.getSource()).getValue() + "\n");
        if (adjustmentEvent.getSource() == this.partCountBar) {
            this.resetDensityGroups();
        }
        this.cv.repaint(this.pause);
    }
    
    public void mouseDragged(final MouseEvent mouseEvent) {
        this.dragging = true;
        this.oldDragX = this.dragX;
        this.oldDragY = this.dragY;
        this.dragX = mouseEvent.getX();
        this.dragY = mouseEvent.getY();
        int selectedIndex = this.modeChooser.getSelectedIndex();
        if (this.selectedSlice) {
            selectedIndex = 2;
        }
        if (selectedIndex == 0) {
            this.rotate(this.lastXRot = (this.oldDragX - this.dragX) / 40.0, this.lastYRot = -(this.oldDragY - this.dragY) / 40.0);
            final double sqrt = Math.sqrt(this.lastXRot * this.lastXRot + this.lastYRot * this.lastYRot);
            if (sqrt > 0.06) {
                final double n = sqrt / 0.06;
                this.lastXRot /= n;
                this.lastYRot /= n;
            }
            this.cv.repaint(this.pause);
        }
        else if (selectedIndex == 1) {
            this.zoom = this.dragZoomStart + (this.dragX - this.dragStartX) / 20.0;
            if (this.zoom < 0.1) {
                this.zoom = 0.1;
            }
            this.cv.repaint(this.pause);
        }
        else if (selectedIndex == 2) {
            final double[] array = new double[3];
            this.unmap3d(array, this.dragX, this.dragY, this.sliceFace, this.sliceFace, this.viewMain);
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
            this.resetDensityGroups();
            this.cv.repaint(this.pause);
            this.vectors = null;
        }
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
        for (int i = 0; i != 8; i += 2) {
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
                        break;
                    }
                }
            }
        }
    }
    
    public void mouseMoved(final MouseEvent mouseEvent) {
        this.dragX = mouseEvent.getX();
        this.dragY = mouseEvent.getY();
        this.dragStartX = this.dragX;
        this.dragStartY = this.dragY;
        this.dragZoomStart = this.zoom;
        final boolean selectedSlice = this.selectedSlice;
        this.checkSlice(this.dragX, this.dragY);
        if (selectedSlice != this.selectedSlice) {
            this.cv.repaint(this.pause);
        }
    }
    
    public void mouseClicked(final MouseEvent mouseEvent) {
    }
    
    public void mouseEntered(final MouseEvent mouseEvent) {
    }
    
    public void mouseExited(final MouseEvent mouseEvent) {
    }
    
    public void mousePressed(final MouseEvent mouseEvent) {
        this.mouseDown = true;
    }
    
    public void mouseReleased(final MouseEvent mouseEvent) {
        this.mouseDown = false;
    }
    
    void dispChooserChanged() {
        final int selectedIndex = this.dispChooser.getSelectedIndex();
        this.showA = (selectedIndex == -1 || selectedIndex == -2);
        this.getPot = (selectedIndex == 4);
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
        this.lineDensityLabel.hide();
        this.lineDensityBar.hide();
        this.partCountLabel.hide();
        this.partCountBar.hide();
        this.strengthLabel.show();
        this.strengthBar.show();
        if (selectedIndex == 2 || selectedIndex == -2 || selectedIndex == -4) {
            this.vecDensityLabel.show();
            this.vecDensityBar.show();
        }
        else if (selectedIndex == 3) {
            this.lineDensityLabel.show();
            this.lineDensityBar.show();
        }
        else if (selectedIndex == 4) {
            this.potentialLabel.show();
            this.potentialBar.show();
        }
        else {
            this.partCountLabel.show();
            this.partCountBar.show();
        }
        this.vecDensityLabel.setText((selectedIndex == -4) ? "Resolution" : "Vector Density");
        if (selectedIndex == 4) {
            this.strengthLabel.hide();
            this.strengthBar.hide();
        }
        if ((selectedIndex == -4 || selectedIndex == 4) && this.sliceChooser.getSelectedIndex() == 0) {
            this.sliceChooser.select(this.curfunc.getBestSlice());
            this.potentialBar.disable();
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
        if (itemEvent.getItemSelectable() == this.sliceChooser) {
            this.resetParticles();
            if (this.modeChooser.getSelectedIndex() == 2) {
                this.modeChooser.select(0);
            }
            if (this.sliceChooser.getSelectedIndex() == 0) {
                this.potentialBar.enable();
            }
            else {
                this.potentialBar.disable();
            }
        }
        if (itemEvent.getStateChange() != 1) {
            return;
        }
        if (itemEvent.getItemSelectable() == this.functionChooser) {
            this.functionChanged();
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
            this.textFields[i].hide();
            if (this.textFieldLabel != null) {
                this.textFieldLabel.hide();
            }
        }
        this.strengthBar.setValue(20);
        final int selectedIndex = this.dispChooser.getSelectedIndex();
        this.setupDispChooser(!this.curfunc.nonGradient());
        try {
            this.dispChooser.select(selectedIndex);
        }
        catch (Exception ex) {}
        this.curfunc.setup();
        this.sliceChooser.select(0);
        this.validate();
        this.resetParticles();
        this.dispChooserChanged();
    }
    
    void setupDispChooser(final boolean b) {
        this.dispChooser.removeAll();
        this.dispChooser.add("Display: Particles (Vel.)");
        this.dispChooser.add("Display: Particles (Force)");
        this.dispChooser.add("Display: Field Vectors");
        this.dispChooser.add("Display: Streamlines");
        if (b) {
            this.dispChooser.add("Display: Equipotentials");
        }
    }
    
    void setupBar(final int n, final String text, final int value) {
        this.auxBars[n].label.setText(text);
        this.auxBars[n].label.show();
        this.auxBars[n].bar.setValue(value);
        this.auxBars[n].bar.show();
    }
    
    boolean useMagnetMove() {
        return this.dispChooser.getSelectedIndex() == -3;
    }
    
    void cross(final double[] array, final double[] array2, final double[] array3) {
        array[0] = array2[1] * array3[2] - array2[2] * array3[1];
        array[1] = array2[2] * array3[0] - array2[0] * array3[2];
        array[2] = array2[0] * array3[1] - array2[1] * array3[0];
    }
    
    double dot(final double[] array, final double[] array2) {
        return array[0] * array2[0] + array[1] * array2[1] + array[2] * array2[2];
    }
    
    void rk(final int n, final double n2, final double[] array, final double n3) {
        if (n == 3) {
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
        }
    }
    
    void getForceField(final double[] array, final double[] array2, final double n, final double n2) {
        this.curfunc.getField(array, array2);
        for (int i = 0; i != 3; ++i) {
            array[i + 3] = 0.1 * n2 * array[i];
        }
        for (int j = 0; j != 3; ++j) {
            array[j] = n * this.timeStep * this.rk_yn[j + 3];
        }
    }
    
    void moveParticle(final Particle particle) {
        final int selectedIndex = this.dispChooser.getSelectedIndex();
        final double n = 1.0;
        final double n2 = 0.001;
        final boolean b = selectedIndex == 1;
        final int n3 = b ? 6 : 3;
        final double[] rk_Y = this.rk_Y;
        final double[] rk_Yhalf = this.rk_Yhalf;
        this.oldY = this.rk_oldY;
        for (int i = 0; i != 3; ++i) {
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
            for (int j = 0; j != 3; ++j) {
                rk_Y[j + 3] = (rk_Yhalf[j + 3] = particle.vel[j]);
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
                for (int k = 0; k != 3; ++k) {
                    final double[] vel = particle.vel;
                    final int n10 = k;
                    vel[n10] += n9 * rk_Yhalf[k];
                    final double[] pos = particle.pos;
                    final int n11 = k;
                    pos[n11] += this.timeStep * particle.vel[k];
                }
            }
            else {
                for (int l = 0; l != 3; ++l) {
                    final double[] pos2 = particle.pos;
                    final int n12 = l;
                    pos2[n12] += partMult * rk_Yhalf[l];
                }
            }
            for (int n13 = 0; n13 != 3; ++n13) {
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
                final double n16 = Math.abs(rk_Y[0] - rk_Yhalf[0]) + Math.abs(rk_Y[1] - rk_Yhalf[1]) + Math.abs(rk_Y[2] - rk_Yhalf[2]);
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
                for (int n20 = 0; n20 != 3; ++n20) {
                    particle.vel[n20] = rk_Y[n20 + 3];
                }
            }
        }
    }
    
    double dist2(final double[] array, final double[] array2) {
        final double n = array[0] - array2[0];
        final double n2 = array[1] - array2[1];
        final double n3 = array[2] - array2[2];
        return n * n + n2 * n2 + n3 * n3;
    }
    
    void lineSegment(final Particle particle, final int n) {
        final double n2 = 20.0;
        final double n3 = 0.001;
        final int n4 = 3;
        final double[] rk_Y = this.rk_Y;
        final double[] rk_Yhalf = this.rk_Yhalf;
        this.oldY = this.rk_oldY;
        int selectedIndex = this.sliceChooser.getSelectedIndex();
        final boolean b = selectedIndex > 0;
        --selectedIndex;
        for (int i = 0; i != 3; ++i) {
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
        final double n15 = 0.0016;
        final double n16 = 0.0064;
        double n17 = 0.0;
        int n18 = 0;
        while (true) {
            this.boundCheck = false;
            if (++n13 > 100) {
                particle.lifetime = -1.0;
                break;
            }
            this.rk(n4, 0.0, rk_Y, n * stepsize);
            this.rk(n4, 0.0, rk_Yhalf, n * stepsize * 0.5);
            this.rk(n4, 0.0, rk_Yhalf, n * stepsize * 0.5);
            if (b) {
                rk_Y[selectedIndex] = (rk_Yhalf[selectedIndex] = this.sliceval);
            }
            if (this.boundCheck) {
                for (int j = 0; j != n4; ++j) {
                    rk_Y[j] = (rk_Yhalf[j] = this.oldY[j]);
                }
                stepsize /= 2.0;
                if (stepsize < n14) {
                    particle.lifetime = -1.0;
                    break;
                }
                continue;
            }
            else if (rk_Y[0] < -1.0 || rk_Y[0] >= 0.999 || rk_Y[1] < -1.0 || rk_Y[1] >= 0.999 || rk_Y[2] < -1.0 || rk_Y[2] >= 0.999) {
                for (int k = 0; k != n4; ++k) {
                    rk_Y[k] = (rk_Yhalf[k] = this.oldY[k]);
                }
                stepsize /= 2.0;
                if (stepsize < n14) {
                    particle.lifetime = -1.0;
                    break;
                }
                continue;
            }
            else {
                final double n19 = Math.abs(rk_Y[0] - rk_Yhalf[0]) + Math.abs(rk_Y[1] - rk_Yhalf[1]) + Math.abs(rk_Y[2] - rk_Yhalf[2]);
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
                        break;
                    }
                    if (dist2 > n16) {
                        stepsize /= 2.0;
                        if (stepsize < n14) {
                            break;
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
                        final double[] ls_fieldavg6 = this.ls_fieldavg;
                        final int n23 = 2;
                        ls_fieldavg6[n23] += this.rk_k1[2];
                        ++n18;
                        if (dist2 > n15) {
                            break;
                        }
                        n17 = dist2;
                        for (int n24 = 0; n24 != n4; ++n24) {
                            this.oldY[n24] = (rk_Yhalf[n24] = rk_Y[n24]);
                        }
                    }
                }
            }
        }
        particle.stepsize = stepsize;
        for (int n25 = 0; n25 != 3; ++n25) {
            particle.pos[n25] = rk_Y[n25];
        }
        particle.phi = Math.sqrt(this.ls_fieldavg[0] * this.ls_fieldavg[0] + this.ls_fieldavg[1] * this.ls_fieldavg[1] + this.ls_fieldavg[2] * this.ls_fieldavg[2]) / n18;
    }
    
    void getDirectionField(final double[] array, final double[] array2, final double n, final double n2) {
        final double sin = Math.sin(n);
        final double cos = Math.cos(n);
        final double sin2 = Math.sin(n2);
        final double cos2 = Math.cos(n2);
        if (!this.showA) {
            if (this.getPot) {
                array[0] = -0.4 * (array2[0] * sin * cos2 + array2[1] * sin * sin2 + array2[2] * cos);
                return;
            }
            array[0] = 3.0E-4 * sin * cos2;
            array[1] = 3.0E-4 * sin * sin2;
            array[2] = 3.0E-4 * cos;
        }
        else {
            final double[] array3 = { sin * cos2, sin * sin2, cos };
            final double dot = this.dot(array3, array2);
            final double[] array4 = new double[3];
            for (int i = 0; i != 3; ++i) {
                array4[i] = 6.0E-4 * (array2[i] - array3[i] * dot);
            }
            this.cross(array, array3, array4);
        }
    }
    
    static {
        Vec3DemoFrame.frames = 0;
        Vec3DemoFrame.framerate = 0;
        Vec3DemoFrame.firsttime = 0L;
    }
    
    class AuxBar
    {
        Scrollbar bar;
        Label label;
        
        AuxBar(final Label label, final Scrollbar bar) {
            this.label = label;
            this.bar = bar;
        }
    }
    
    class EquipPoint
    {
        double[] pos;
        double pot;
        
        EquipPoint() {
            this.pos = new double[3];
        }
        
        EquipPoint(final EquipPoint equipPoint, final EquipPoint equipPoint2) {
            this.pos = new double[3];
            for (int i = 0; i != 3; ++i) {
                this.pos[i] = 0.5 * (equipPoint.pos[i] + equipPoint2.pos[i]);
            }
            Vec3DemoFrame.this.curfunc.getField(Vec3DemoFrame.this.potfield, this.pos);
            this.pot = Vec3DemoFrame.this.reverse * Vec3DemoFrame.this.potfield[0];
        }
        
        void set(final int n, final int n2, final int n3, final double n4, final double n5, final double n6) {
            this.pos[n] = n4;
            this.pos[n2] = n5;
            this.pos[n3] = n6;
        }
        
        boolean valid() {
            return !Double.isNaN(this.pot) && !Double.isInfinite(this.pot);
        }
        
        boolean inRange() {
            return this.pot >= -2.0 && this.pot <= 2.0;
        }
        
        void setPot(final double pot) {
            this.pot = pot;
        }
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
        
        boolean noSplitFieldVectors() {
            return true;
        }
        
        int getViewPri(final double[] array, final double[] array2) {
            return 0;
        }
        
        void render(final Graphics graphics) {
            Vec3DemoFrame.this.renderItems(graphics, 0);
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
        
        int getBestSlice() {
            final double[] array = new double[3];
            final double[] array2 = new double[3];
            final double[] array3 = new double[3];
            final double[] array4 = new double[3];
            final double[] array5 = array;
            final int n = 0;
            final double[] array6 = array;
            final int n2 = 1;
            final double[] array7 = array;
            final int n3 = 2;
            final double n4 = 0.9;
            array7[n3] = n4;
            array5[n] = (array6[n2] = n4);
            Vec3DemoFrame.this.curfunc.getField(array2, array);
            array[0] = 0.91;
            Vec3DemoFrame.this.curfunc.getField(array3, array);
            array[0] = 0.9;
            array[1] = 0.91;
            Vec3DemoFrame.this.curfunc.getField(array4, array);
            if (array2[0] == array3[0] && array2[1] == array3[1] && array2[2] == array3[2]) {
                return 1;
            }
            if (array2[0] == array4[0] && array2[1] == array4[1] && array2[2] == array4[2]) {
                return 2;
            }
            return 3;
        }
        
        void renderSphere(final Graphics graphics, final double n) {
            Vec3DemoFrame.this.renderItems(graphics, 2);
            graphics.setColor(Vec3DemoFrame.this.darkYellow);
            Vec3DemoFrame.this.drawSphere(graphics, n, true);
            Vec3DemoFrame.this.renderItems(graphics, 1);
            graphics.setColor(Vec3DemoFrame.this.darkYellow);
            Vec3DemoFrame.this.map3d(0.0, 0.0, 0.0, Vec3DemoFrame.this.xpoints, Vec3DemoFrame.this.ypoints, 0);
            final int n2 = (int)(Vec3DemoFrame.this.getScalingFactor(0.0, 0.0, 0.0) * n);
            graphics.drawOval(Vec3DemoFrame.this.xpoints[0] - n2, Vec3DemoFrame.this.ypoints[0] - n2, n2 * 2, n2 * 2);
            Vec3DemoFrame.this.drawSphere(graphics, n, false);
            Vec3DemoFrame.this.renderItems(graphics, 0);
        }
    }
    
    class InverseSquaredRadial extends VecFunction
    {
        static final double chargeSize = 0.06;
        
        String getName() {
            return "1/r^2 single";
        }
        
        void getField(final double[] array, final double[] array2) {
            final double distance = Vec3DemoFrame.this.distance(array2);
            if (distance < 0.06) {
                Vec3DemoFrame.this.boundCheck = true;
            }
            if (Vec3DemoFrame.this.getPot) {
                array[0] = -0.1 / distance;
                return;
            }
            final double n = 3.0E-4 / (distance * distance * distance);
            array[0] = -array2[0] * n;
            array[1] = -array2[1] * n;
            array[2] = -array2[2] * n;
        }
        
        void drawCharge(final Graphics graphics, final double n, final double n2, final double n3) {
            this.drawCharge(graphics, n, n2, n3, 0);
        }
        
        void drawCharge(final Graphics graphics, final double n, final double n2, final double n3, final int n4) {
            Vec3DemoFrame.this.map3d(n, n2, n3, Vec3DemoFrame.this.xpoints, Vec3DemoFrame.this.ypoints, 0);
            Vec3DemoFrame.this.map3d(n, n2, n3 + 0.3 * n4 * Vec3DemoFrame.this.reverse, Vec3DemoFrame.this.xpoints, Vec3DemoFrame.this.ypoints, 1);
            graphics.setColor(Vec3DemoFrame.this.darkYellow);
            final int n5 = (int)(Vec3DemoFrame.this.getScalingFactor(n, n2, n3) * 0.06);
            graphics.fillOval(Vec3DemoFrame.this.xpoints[0] - n5, Vec3DemoFrame.this.ypoints[0] - n5, n5 * 2, n5 * 2);
            if (n4 != 0) {
                Vec3DemoFrame.this.drawArrow(graphics, null, Vec3DemoFrame.this.xpoints[0], Vec3DemoFrame.this.ypoints[0], Vec3DemoFrame.this.xpoints[1], Vec3DemoFrame.this.ypoints[1], 5);
            }
        }
        
        void render(final Graphics graphics) {
            this.drawCharge(graphics, 0.0, 0.0, 0.0);
            Vec3DemoFrame.this.renderItems(graphics, 1);
        }
        
        int getViewPri(final double[] array, final double[] array2) {
            final int intersectSphere = Vec3DemoFrame.this.intersectSphere(array, array2[0], array2[1], array2[2], 0.06);
            if (intersectSphere == 0) {
                return 1;
            }
            if (intersectSphere == 1) {
                return -1;
            }
            return 0;
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
        
        int getBestSlice() {
            return 2;
        }
        
        void getField(final double[] array, final double[] array2) {
            final double n = Vec3DemoFrame.this.aux1Bar.getValue() / 100.0;
            final double n2 = array2[0] - n;
            final double n3 = array2[0] + n;
            final double distance = Vec3DemoFrame.this.distance(n2, array2[1], array2[2]);
            if (distance < 0.06) {
                Vec3DemoFrame.this.boundCheck = true;
            }
            final double distance2 = Vec3DemoFrame.this.distance(n3, array2[1], array2[2]);
            if (distance2 < 0.06) {
                Vec3DemoFrame.this.boundCheck = true;
            }
            if (Vec3DemoFrame.this.getPot) {
                array[0] = -0.05 / distance - 0.05 * this.sign2 / distance2;
                if (this.sign2 == -1.0) {
                    final int n4 = 0;
                    array[n4] *= 2.0;
                }
                return;
            }
            final double n5 = 3.0E-4;
            final double n6 = n5 / (distance * distance * distance);
            final double n7 = n5 / (distance2 * distance2 * distance2) * this.sign2;
            array[0] = -n2 * n6 - n3 * n7;
            array[1] = -array2[1] * n6 - array2[1] * n7;
            array[2] = -array2[2] * n6 - array2[2] * n7;
        }
        
        void setup() {
            Vec3DemoFrame.this.setXZView();
            this.sign2 = 1.0;
            Vec3DemoFrame.this.setupBar(0, "Charge Separation", 30);
        }
        
        void render(final Graphics graphics) {
            final double n = Vec3DemoFrame.this.aux1Bar.getValue() / 100.0;
            this.drawCharge(graphics, n, 0.0, 0.0);
            this.drawCharge(graphics, -n, 0.0, 0.0);
            Vec3DemoFrame.this.renderItems(graphics, 1);
        }
        
        int getViewPri(final double[] array, final double[] array2) {
            final double n = Vec3DemoFrame.this.aux1Bar.getValue() / 100.0;
            if (Vec3DemoFrame.this.intersectSphere(array, array2[0], array2[1], array2[2], n, 0.0, 0.0, 0.06) == 0 && Vec3DemoFrame.this.intersectSphere(array, array2[0], array2[1], array2[2], -n, 0.0, 0.0, 0.06) == 0) {
                return 1;
            }
            return 0;
        }
        
        VecFunction createNext() {
            return new InverseRadial();
        }
    }
    
    class InverseRadial extends VecFunction
    {
        double lineLen;
        
        String getName() {
            return "1/r single line";
        }
        
        void getField(final double[] array, final double[] array2) {
            final double distance = Vec3DemoFrame.this.distance(array2[0], array2[1], 0.0);
            if (distance < 0.01) {
                Vec3DemoFrame.this.boundCheck = true;
            }
            if (Vec3DemoFrame.this.getPot) {
                array[0] = 0.4 * Math.log(distance + 1.0E-20);
                return;
            }
            final double n = distance * distance;
            array[0] = -3.0E-4 * array2[0] / n;
            array[1] = -3.0E-4 * array2[1] / n;
            array[2] = 0.0;
        }
        
        void setup() {
            Vec3DemoFrame.this.setXZView();
            this.lineLen = 1.0;
        }
        
        void render(final Graphics graphics) {
            graphics.setColor(Vec3DemoFrame.this.darkYellow);
            Vec3DemoFrame.this.map3d(0.0, 0.0, -this.lineLen, Vec3DemoFrame.this.xpoints, Vec3DemoFrame.this.ypoints, 0);
            Vec3DemoFrame.this.map3d(0.0, 0.0, this.lineLen, Vec3DemoFrame.this.xpoints, Vec3DemoFrame.this.ypoints, 1);
            graphics.drawLine(Vec3DemoFrame.this.xpoints[0], Vec3DemoFrame.this.ypoints[0], Vec3DemoFrame.this.xpoints[1], Vec3DemoFrame.this.ypoints[1]);
            Vec3DemoFrame.this.renderItems(graphics, 1);
        }
        
        int getViewPri(final double[] array, final double[] array2) {
            if (Vec3DemoFrame.this.intersectCylinder(array, array2[0], array2[1], array2[2], 0.01, true) == 0) {
                return 1;
            }
            if (Vec3DemoFrame.this.intersection[2] >= -this.lineLen && Vec3DemoFrame.this.intersection[2] <= this.lineLen) {
                return 0;
            }
            return 1;
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
            final double n = Vec3DemoFrame.this.aux1Bar.getValue() / 100.0;
            final double n2 = array2[0] - n;
            final double n3 = array2[0] + n;
            final double distance = Vec3DemoFrame.this.distance(n2, array2[1]);
            final double distance2 = Vec3DemoFrame.this.distance(n3, array2[1]);
            if (distance < 0.01 || distance2 < 0.01) {
                Vec3DemoFrame.this.boundCheck = true;
            }
            if (Vec3DemoFrame.this.getPot) {
                array[0] = 0.2 * (Math.log(distance + 1.0E-20) + this.sign * Math.log(distance2 + 1.0E-20));
                return;
            }
            final double n4 = 3.0E-4;
            final double n5 = 1.0 / (distance * distance);
            final double n6 = 1.0 / (distance2 * distance2 * this.sign);
            array[0] = n4 * (-n2 * n5 - n3 * n6);
            array[1] = n4 * (-array2[1] * n5 - array2[1] * n6);
            array[2] = 0.0;
        }
        
        void setup() {
            Vec3DemoFrame.this.setupBar(0, "Line Separation", 30);
            Vec3DemoFrame.this.setXZView();
        }
        
        void render(final Graphics graphics) {
            final double n = Vec3DemoFrame.this.aux1Bar.getValue() / 100.0;
            graphics.setColor(Vec3DemoFrame.this.darkYellow);
            for (int i = -1; i <= 1; i += 2) {
                Vec3DemoFrame.this.map3d(n * i, 0.0, -1.0, Vec3DemoFrame.this.xpoints, Vec3DemoFrame.this.ypoints, 0);
                Vec3DemoFrame.this.map3d(n * i, 0.0, 1.0, Vec3DemoFrame.this.xpoints, Vec3DemoFrame.this.ypoints, 1);
                graphics.drawLine(Vec3DemoFrame.this.xpoints[0], Vec3DemoFrame.this.ypoints[0], Vec3DemoFrame.this.xpoints[1], Vec3DemoFrame.this.ypoints[1]);
            }
            Vec3DemoFrame.this.renderItems(graphics, 1);
        }
        
        int getViewPri(final double[] array, final double[] array2) {
            final double n = Vec3DemoFrame.this.aux1Bar.getValue() / 100.0;
            for (int i = -1; i <= 1; i += 2) {
                if (Vec3DemoFrame.this.intersectCylinder(array, array2[0], array2[1], array2[2], i * n, 0.0, 0.01, true) != 0) {
                    return 0;
                }
            }
            return 1;
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
        
        void setup() {
            Vec3DemoFrame.this.setXYViewExact();
        }
        
        void getField(final double[] array, final double[] array2) {
            final double distance = Vec3DemoFrame.this.distance(array2[0], array2[1]);
            if (Vec3DemoFrame.this.showA) {
                array[0] = (array[1] = 0.0);
                array[2] = -0.001 * (Math.log(distance) - 0.5);
            }
            else {
                if (distance < 0.02) {
                    Vec3DemoFrame.this.boundCheck = true;
                }
                Vec3DemoFrame.this.rotateParticle(array, array2, 1.0E-4 / (distance * distance));
            }
        }
        
        void render(final Graphics graphics) {
            graphics.setColor(Vec3DemoFrame.this.darkYellow);
            Vec3DemoFrame.this.map3d(0.0, 0.0, -1.0, Vec3DemoFrame.this.xpoints, Vec3DemoFrame.this.ypoints, 0);
            Vec3DemoFrame.this.map3d(0.0, 0.0, 1.0, Vec3DemoFrame.this.xpoints, Vec3DemoFrame.this.ypoints, 1);
            Vec3DemoFrame.this.drawCurrentLine(graphics, Vec3DemoFrame.this.xpoints[0], Vec3DemoFrame.this.ypoints[0], Vec3DemoFrame.this.xpoints[1], Vec3DemoFrame.this.ypoints[1], 12, true, 1);
            Vec3DemoFrame.this.renderItems(graphics, 1);
        }
        
        boolean nonGradient() {
            return true;
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
            final double n = Vec3DemoFrame.this.aux1Bar.getValue() / 100.0;
            final double distance = Vec3DemoFrame.this.distance(array2[0] - n, array2[1]);
            final double distance2 = Vec3DemoFrame.this.distance(array2[0] + n, array2[1]);
            if (this.ext) {
                final double n2 = Vec3DemoFrame.this.aux3Bar.getValue() * 3.141592653589793 / 50.0;
                final double n3 = Vec3DemoFrame.this.aux2Bar.getValue() / 30.0;
                Vec3DemoFrame.this.getDirectionField(array, array2, 1.5707963267948966, n2);
                final int n4 = 0;
                array[n4] *= n3;
                final int n5 = 1;
                array[n5] *= n3;
                final int n6 = 2;
                array[n6] *= n3;
            }
            else {
                final int n7 = 0;
                final int n8 = 1;
                final int n9 = 2;
                final double n10 = 0.0;
                array[n9] = n10;
                array[n7] = (array[n8] = n10);
            }
            if (Vec3DemoFrame.this.showA) {
                if (this.dir2 == 1) {
                    final int n11 = 2;
                    array[n11] += -0.001 * (Math.log(distance) + Math.log(distance2) - 1.0);
                }
                else {
                    final int n12 = 2;
                    array[n12] += 0.001 * (Math.log(distance) - Math.log(distance2));
                }
            }
            else {
                if (distance < 0.02) {
                    Vec3DemoFrame.this.boundCheck = true;
                }
                Vec3DemoFrame.this.rotateParticleAdd(array, array2, 1.0E-4 / (distance * distance), n, 0.0);
                if (distance2 < 0.02) {
                    Vec3DemoFrame.this.boundCheck = true;
                }
                Vec3DemoFrame.this.rotateParticleAdd(array, array2, this.dir2 * 1.0E-4 / (distance2 * distance2), -n, 0.0);
            }
        }
        
        void setup() {
            Vec3DemoFrame.this.setupBar(0, "Line Separation", 30);
            if (this.ext) {
                Vec3DemoFrame.this.setupBar(1, "Ext. Strength", 28);
                Vec3DemoFrame.this.setupBar(2, "Ext. Direction", 0);
            }
            Vec3DemoFrame.this.setXYViewExact();
        }
        
        void render(final Graphics graphics) {
            final double n = Vec3DemoFrame.this.aux1Bar.getValue() / 100.0;
            graphics.setColor(Vec3DemoFrame.this.darkYellow);
            for (int i = -1; i <= 1; i += 2) {
                Vec3DemoFrame.this.map3d(n * i, 0.0, -1.0, Vec3DemoFrame.this.xpoints, Vec3DemoFrame.this.ypoints, 0);
                Vec3DemoFrame.this.map3d(n * i, 0.0, 1.0, Vec3DemoFrame.this.xpoints, Vec3DemoFrame.this.ypoints, 1);
                Vec3DemoFrame.this.drawCurrentLine(graphics, Vec3DemoFrame.this.xpoints[0], Vec3DemoFrame.this.ypoints[0], Vec3DemoFrame.this.xpoints[1], Vec3DemoFrame.this.ypoints[1], 12, true, (i == -1) ? 1 : this.dir2);
            }
            Vec3DemoFrame.this.renderItems(graphics, 1);
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
            this.ext = true;
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
            this.dir2 = -1;
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
            this.dir2 = -1;
            this.ext = true;
        }
        
        void setup() {
            super.setup();
            Vec3DemoFrame.this.aux2Bar.setValue(17);
            Vec3DemoFrame.this.aux3Bar.setValue(25);
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
            Vec3DemoFrame.this.getDirectionField(array, array2, Vec3DemoFrame.this.aux1Bar.getValue() * 3.141592653589793 / 50.0, Vec3DemoFrame.this.aux2Bar.getValue() * 3.141592653589793 / 50.0);
        }
        
        void setup() {
            Vec3DemoFrame.this.setupBar(0, "Theta", 25);
            Vec3DemoFrame.this.setupBar(1, "Phi", 0);
            Vec3DemoFrame.this.setXYView();
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
            return (Vec3DemoFrame.this.aux1Bar.getValue() + 1) / 110.0;
        }
        
        void getField(final double[] array, final double[] array2) {
            double distance = Vec3DemoFrame.this.distance(array2);
            if (distance < 0.01) {
                Vec3DemoFrame.this.boundCheck = true;
            }
            final double size = this.getSize();
            if (Vec3DemoFrame.this.getPot) {
                array[0] = 0.1 * ((distance > size) ? (-1.0 / distance) : (-3.0 / (2.0 * size) + distance * distance / (2.0 * size * size * size)));
                return;
            }
            if (distance < size) {
                distance = size;
            }
            final double n = 3.0E-4 / (distance * distance * distance);
            array[0] = -array2[0] * n;
            array[1] = -array2[1] * n;
            array[2] = -array2[2] * n;
        }
        
        void setup() {
            Vec3DemoFrame.this.setupBar(0, "Sphere Size", 70);
        }
        
        void render(final Graphics graphics) {
            this.renderSphere(graphics, this.getSize());
        }
        
        int getViewPri(final double[] array, final double[] array2) {
            return Vec3DemoFrame.this.intersectSphere(array, array2[0], array2[1], array2[2], this.getSize());
        }
        
        boolean noSplitFieldVectors() {
            return false;
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
            final double distance = Vec3DemoFrame.this.distance(array2);
            if (distance < 0.06) {
                Vec3DemoFrame.this.boundCheck = true;
            }
            final double n = 3.0E-4 / distance;
            if (Vec3DemoFrame.this.getPot) {
                array[0] = distance - 1.0;
                return;
            }
            array[0] = -n * array2[0];
            array[1] = -n * array2[1];
            array[2] = -n * array2[2];
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
            final double distance = Vec3DemoFrame.this.distance(array2);
            if (distance < 0.06) {
                Vec3DemoFrame.this.boundCheck = true;
            }
            if (Vec3DemoFrame.this.getPot) {
                array[0] = distance * distance - 1.0;
                return;
            }
            final double n = 3.0E-4;
            array[0] = -array2[0] * n;
            array[1] = -array2[1] * n;
            array[2] = -array2[2] * n;
        }
        
        boolean checkBoundsWithForce() {
            return false;
        }
        
        VecFunction createNext() {
            return new ConstantToZAxis();
        }
    }
    
    class ConstantToZAxis extends InverseRadial
    {
        String getName() {
            return "constant to z axis";
        }
        
        void getField(final double[] array, final double[] array2) {
            final double distance = Vec3DemoFrame.this.distance(array2[0], array2[1]);
            if (distance < 0.01) {
                Vec3DemoFrame.this.boundCheck = true;
            }
            if (Vec3DemoFrame.this.getPot) {
                array[0] = distance - 1.0;
                return;
            }
            final double n = 3.0E-4 / distance;
            array[0] = -array2[0] * n;
            array[1] = -array2[1] * n;
            array[2] = 0.0;
        }
        
        void setup() {
            Vec3DemoFrame.this.setXYView();
        }
        
        boolean checkBoundsWithForce() {
            return false;
        }
        
        VecFunction createNext() {
            return new ConstantToXYPlane();
        }
    }
    
    class ConstantToXYPlane extends VecFunction
    {
        String getName() {
            return "constant to xy plane";
        }
        
        void getField(final double[] array, final double[] array2) {
            final double n = 3.0E-4;
            if (array2[2] > -0.01 && array2[2] < 0.01) {
                Vec3DemoFrame.this.boundCheck = true;
            }
            if (Vec3DemoFrame.this.getPot) {
                array[0] = Math.abs(array2[2]) - 1.0;
                return;
            }
            array[1] = (array[0] = 0.0);
            array[2] = ((array2[2] < 0.0) ? n : (-n));
        }
        
        void setup() {
            Vec3DemoFrame.this.setXZView();
        }
        
        void render(final Graphics graphics) {
            Vec3DemoFrame.this.renderItems(graphics, 1);
            Vec3DemoFrame.this.drawPlane(graphics, 1.0, 1.0, 0.0);
            Vec3DemoFrame.this.renderItems(graphics, 0);
        }
        
        int getViewPri(final double[] array, final double[] array2) {
            if (Vec3DemoFrame.this.intersectZPlane(array, 0.0, array2[0], array2[1], array2[2]) == 0.0) {
                return 0;
            }
            return 1;
        }
        
        boolean checkBoundsWithForce() {
            return false;
        }
        
        VecFunction createNext() {
            return new LinearToZAxis();
        }
    }
    
    class LinearToZAxis extends InverseRadial
    {
        String getName() {
            return "linear to z axis";
        }
        
        void getField(final double[] array, final double[] array2) {
            final double distance = Vec3DemoFrame.this.distance(array2[0], array2[1]);
            if (distance < 0.01) {
                Vec3DemoFrame.this.boundCheck = true;
            }
            if (Vec3DemoFrame.this.getPot) {
                array[0] = distance * distance - 1.0;
                return;
            }
            final double n = 3.0E-4;
            array[0] = -array2[0] * n;
            array[1] = -array2[1] * n;
            array[2] = 0.0;
        }
        
        void setup() {
            Vec3DemoFrame.this.setXYView();
        }
        
        boolean checkBoundsWithForce() {
            return false;
        }
        
        VecFunction createNext() {
            return new LinearToXYPlane();
        }
    }
    
    class LinearToXYPlane extends ConstantToXYPlane
    {
        String getName() {
            return "linear to xy plane";
        }
        
        void getField(final double[] array, final double[] array2) {
            if (Vec3DemoFrame.this.getPot) {
                array[0] = array2[2] * array2[2] - 1.0;
                return;
            }
            if (array2[2] > -0.01 && array2[2] < 0.01) {
                Vec3DemoFrame.this.boundCheck = true;
            }
            final double n = 3.0E-4;
            array[1] = (array[0] = 0.0);
            array[2] = -n * array2[2];
        }
        
        void setup() {
            Vec3DemoFrame.this.setXYView();
        }
        
        boolean checkBoundsWithForce() {
            return false;
        }
        
        VecFunction createNext() {
            return new LinearToYZXZPlane();
        }
    }
    
    class LinearToYZXZPlane extends VecFunction
    {
        String getName() {
            return "linear to yz, xz planes";
        }
        
        void getField(final double[] array, final double[] array2) {
            if (array2[0] > -0.01 && array2[0] < 0.01) {
                Vec3DemoFrame.this.boundCheck = true;
            }
            if (array2[1] > -0.01 && array2[1] < 0.01) {
                Vec3DemoFrame.this.boundCheck = true;
            }
            final double n = 3.0E-4;
            final double sqrt = Math.sqrt((Vec3DemoFrame.this.aux1Bar.getValue() + 1) / 51.0);
            if (Vec3DemoFrame.this.getPot) {
                array[0] = array2[0] * array2[0] * sqrt + array2[1] * array2[1] / sqrt - 1.0;
                return;
            }
            array[0] = -n * sqrt * array2[0];
            array[1] = -n / sqrt * array2[1];
            array[2] = 0.0;
        }
        
        void setup() {
            Vec3DemoFrame.this.setXYView();
            Vec3DemoFrame.this.setupBar(0, "X/Y Ratio", 50);
        }
        
        boolean checkBoundsWithForce() {
            return false;
        }
        
        VecFunction createNext() {
            return new LinearToYZXZXYPlane();
        }
    }
    
    class LinearToYZXZXYPlane extends VecFunction
    {
        String getName() {
            return "linear to yz, xz, xy planes";
        }
        
        void getField(final double[] array, final double[] array2) {
            if (array2[2] > -0.01 && array2[2] < 0.01) {
                Vec3DemoFrame.this.boundCheck = true;
            }
            final double n = 3.0E-4;
            final double n2 = (Vec3DemoFrame.this.aux1Bar.getValue() + 1) / 51.0;
            final double n3 = (Vec3DemoFrame.this.aux2Bar.getValue() + 1) / 51.0;
            if (Vec3DemoFrame.this.getPot) {
                array[0] = array2[0] * array2[0] * n2 + array2[1] * array2[1] + array2[2] * array2[2] / n3 - 1.0;
                return;
            }
            array[0] = -n * n2 * array2[0];
            array[1] = -n * array2[1];
            array[2] = -n / n3 * array2[2];
        }
        
        void setup() {
            Vec3DemoFrame.this.setXYView();
            Vec3DemoFrame.this.setupBar(0, "X/Y Ratio", 50);
            Vec3DemoFrame.this.setupBar(1, "Y/Z Ratio", 50);
        }
        
        boolean checkBoundsWithForce() {
            return false;
        }
        
        VecFunction createNext() {
            return new InverseToXYPlane();
        }
    }
    
    class InverseToXYPlane extends ConstantToXYPlane
    {
        String getName() {
            return "inverse to xy plane";
        }
        
        void getField(final double[] array, final double[] array2) {
            if (array2[2] > -0.01 && array2[2] < 0.01) {
                Vec3DemoFrame.this.boundCheck = true;
            }
            final double n = 3.0E-4;
            double n2 = array2[2];
            if (Vec3DemoFrame.this.getPot) {
                array[0] = -0.01 / (n2 * n2);
                return;
            }
            if (n2 == 0.0) {
                n2 = 1.0E-5;
            }
            array[1] = (array[0] = 0.0);
            array[2] = -n / n2;
        }
        
        void setup() {
            Vec3DemoFrame.this.setXZView();
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
            final double distance = Vec3DemoFrame.this.distance(array2[0], array2[1]);
            if (distance < 0.02) {
                Vec3DemoFrame.this.boundCheck = true;
            }
            Vec3DemoFrame.this.rotateParticle(array, array2, 1.0E-4 / (distance * distance * distance));
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
        
        void setup() {
            Vec3DemoFrame.this.setXYViewExact();
        }
        
        void getField(final double[] array, final double[] array2) {
            final double n = 3.0E-4;
            array[0] = -n * array2[1];
            array[1] = n * array2[0];
            array[2] = 0.0;
        }
        
        boolean nonGradient() {
            return true;
        }
        
        VecFunction createNext() {
            return new LinearRotationalA();
        }
    }
    
    class LinearRotationalA extends InverseRadial
    {
        String getName() {
            return "fz=-(x^2+y^2)";
        }
        
        void setup() {
            Vec3DemoFrame.this.setXZView();
        }
        
        void getField(final double[] array, final double[] array2) {
            final double n = 3.0E-4;
            array[0] = (array[1] = 0.0);
            array[2] = -n * (array2[0] * array2[0] + array2[1] * array2[1]);
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
        
        void setup() {
            Vec3DemoFrame.this.setXYViewExact();
        }
        
        void getField(final double[] array, final double[] array2) {
            Vec3DemoFrame.this.rotateParticle(array, array2, 3.0E-4 / Vec3DemoFrame.this.distance(array2[0], array2[1]));
        }
        
        boolean nonGradient() {
            return true;
        }
        
        VecFunction createNext() {
            return new ConstantRotationalA();
        }
    }
    
    class ConstantRotationalA extends InverseRadial
    {
        String getName() {
            return "(0,0,-r)";
        }
        
        void setup() {
            Vec3DemoFrame.this.setXZView();
        }
        
        void getField(final double[] array, final double[] array2) {
            final double distance = Vec3DemoFrame.this.distance(array2[0], array2[1]);
            array[0] = (array[1] = 0.0);
            array[2] = -6.0E-4 * distance;
        }
        
        boolean nonGradient() {
            return true;
        }
        
        VecFunction createNext() {
            return new Helical();
        }
    }
    
    class Helical extends InverseRadial
    {
        String getName() {
            return "helical";
        }
        
        void setup() {
            Vec3DemoFrame.this.setXZView();
            Vec3DemoFrame.this.setupBar(0, "Z Speed", 30);
        }
        
        void getField(final double[] array, final double[] array2) {
            final double n = 3.0E-4;
            array[0] = -n * array2[1];
            array[1] = n * array2[0];
            array[2] = 1.0E-5 * Vec3DemoFrame.this.aux1Bar.getValue();
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
            return "fx=y";
        }
        
        void getField(final double[] array, final double[] array2) {
            array[2] = (array[1] = 0.0);
            array[0] = array2[1] * 6.0E-4;
        }
        
        void setup() {
            Vec3DemoFrame.this.setXYView();
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
            return "fx=y2";
        }
        
        void getField(final double[] array, final double[] array2) {
            array[2] = (array[1] = 0.0);
            array[0] = array2[1] * array2[1] * 0.001;
        }
        
        void setup() {
            Vec3DemoFrame.this.setXYView();
        }
        
        boolean nonGradient() {
            return true;
        }
        
        VecFunction createNext() {
            return new LinearZRotational();
        }
    }
    
    class LinearZRotational extends VecFunction
    {
        String getName() {
            return "(-yz,xz,0)";
        }
        
        void setup() {
            Vec3DemoFrame.this.setXZView();
        }
        
        void getField(final double[] array, final double[] array2) {
            final double n = 0.001 * array2[2];
            array[0] = -n * array2[1];
            array[1] = n * array2[0];
            array[2] = 0.0;
        }
        
        boolean nonGradient() {
            return true;
        }
        
        VecFunction createNext() {
            return new YzXz0Field();
        }
    }
    
    class YzXz0Field extends VecFunction
    {
        String getName() {
            return "(yz,xz,0)";
        }
        
        void setup() {
            Vec3DemoFrame.this.setXZView();
        }
        
        void getField(final double[] array, final double[] array2) {
            final double n = 6.0E-4 * array2[2];
            array[0] = n * array2[1];
            array[1] = n * array2[0];
            array[2] = 0.0;
        }
        
        boolean nonGradient() {
            return true;
        }
        
        VecFunction createNext() {
            return new XY_2ZField();
        }
    }
    
    class XY_2ZField extends VecFunction
    {
        String getName() {
            return "(-x,-y,2z)";
        }
        
        void setup() {
            Vec3DemoFrame.this.setXZView();
        }
        
        void getField(final double[] array, final double[] array2) {
            final double n = 6.0E-4;
            array[0] = n * array2[0];
            array[1] = n * array2[1];
            array[2] = -2.0 * n * array2[2];
        }
        
        boolean nonGradient() {
            return true;
        }
        
        VecFunction createNext() {
            return new XY0Field();
        }
    }
    
    class XY0Field extends VecFunction
    {
        String getName() {
            return "(-x,y,0)";
        }
        
        void setup() {
            Vec3DemoFrame.this.setXYView();
        }
        
        void getField(final double[] array, final double[] array2) {
            final double n = 6.0E-4;
            array[0] = -n * array2[0];
            array[1] = n * array2[1];
            array[2] = 0.0;
        }
        
        boolean nonGradient() {
            return true;
        }
        
        VecFunction createNext() {
            return new RotationalExpansion();
        }
    }
    
    class RotationalExpansion extends VecFunction
    {
        String getName() {
            return "(x-y,x+y,0)";
        }
        
        void getField(final double[] array, final double[] array2) {
            final double n = 3.0E-4;
            array[0] = n * (array2[0] - array2[1]);
            array[1] = n * (array2[0] + array2[1]);
            array[2] = 0.0;
        }
        
        void setup() {
            Vec3DemoFrame.this.setXYView();
        }
        
        boolean nonGradient() {
            return true;
        }
        
        VecFunction createNext() {
            return new RotationalExpansion3D();
        }
    }
    
    class RotationalExpansion3D extends VecFunction
    {
        String getName() {
            return "(x-y,x+y,2z)";
        }
        
        void getField(final double[] array, final double[] array2) {
            final double n = 3.0E-4;
            array[0] = n * (array2[0] - array2[1]);
            array[1] = n * (array2[0] + array2[1]);
            array[2] = n * (array2[2] * 2.0);
        }
        
        void setup() {
            Vec3DemoFrame.this.setXYView();
        }
        
        boolean nonGradient() {
            return true;
        }
        
        VecFunction createNext() {
            return new RosslerAttractor();
        }
    }
    
    class RosslerAttractor extends VecFunction
    {
        String getName() {
            return "Rossler attractor";
        }
        
        void getField(final double[] array, final double[] array2) {
            final int n = Vec3DemoFrame.this.aux2Bar.getValue() * 2 + 20;
            final double n2 = array2[0] * 24.0;
            final double n3 = array2[1] * 24.0;
            final double n4 = (array2[2] + 0.75) * n;
            final double n5 = 2.0E-5;
            final double n6 = Vec3DemoFrame.this.aux1Bar.getValue() * 0.1;
            array[0] = -(n3 + n4) * n5;
            array[1] = n5 * (n2 + 0.2 * n3);
            array[2] = n5 * (0.2 + n2 * n4 - n6 * n4);
        }
        
        void setup() {
            Vec3DemoFrame.this.setXZView();
            Vec3DemoFrame.this.setupBar(0, "c", 80);
            Vec3DemoFrame.this.setupBar(1, "Z Scale", 36);
            Vec3DemoFrame.this.strengthBar.setValue(75);
        }
        
        boolean nonGradient() {
            return true;
        }
        
        boolean redistribute() {
            return false;
        }
        
        VecFunction createNext() {
            return new LorenzAttractor();
        }
    }
    
    class LorenzAttractor extends VecFunction
    {
        String getName() {
            return "Lorenz attractor";
        }
        
        void setup() {
            Vec3DemoFrame.this.setXZView();
            Vec3DemoFrame.this.setupBar(0, "Scale", 24);
        }
        
        void getField(final double[] array, final double[] array2) {
            final int n = Vec3DemoFrame.this.aux1Bar.getValue() / 2 + 23;
            final double n2 = array2[0] * n;
            final double n3 = array2[1] * n;
            final double n4 = array2[2] * n + n;
            final double n5 = 2.0E-5;
            array[0] = (-10.0 * n2 + 10.0 * n3) * n5;
            array[1] = n5 * (28.0 * n2 - n3 - n2 * n4);
            array[2] = n5 * (-2.6666666666666665 * n4 + n2 * n3);
        }
        
        boolean nonGradient() {
            return true;
        }
        
        boolean redistribute() {
            return false;
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
            Vec3DemoFrame.this.setXZView();
            Vec3DemoFrame.this.textFields[0].setText("x*x-z*z");
            Vec3DemoFrame.this.textFields[0].show();
            Vec3DemoFrame.this.textFieldLabel.setText("Potential Function");
            Vec3DemoFrame.this.textFieldLabel.show();
            this.actionPerformed();
            this.y0 = new double[3];
        }
        
        void actionPerformed() {
            Vec3DemoFrame.this.parseError = false;
            final ExprParser exprParser = new ExprParser(Vec3DemoFrame.this.textFields[0].getText());
            this.expr = exprParser.parseExpression();
            if (exprParser.gotError()) {
                Vec3DemoFrame.this.parseError = true;
            }
        }
        
        void getField(final double[] array, final double[] array2) {
            final double n = 1.0E-5;
            for (int i = 0; i != 3; ++i) {
                this.y0[i] = array2[i];
            }
            final double eval = this.expr.eval(this.y0);
            if (Vec3DemoFrame.this.getPot) {
                array[0] = eval * 0.01;
                return;
            }
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
            final double[] y3 = this.y0;
            final int n4 = 2;
            y3[n4] += n;
            array[2] = eval - this.expr.eval(this.y0);
            for (int j = 0; j != 3; ++j) {
                if (array[j] <= -10.0 || array[j] >= 10.0) {
                    Vec3DemoFrame.this.boundCheck = true;
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
            Vec3DemoFrame.this.setXZView();
            this.exprs = new Expr[3];
            Vec3DemoFrame.this.textFields[0].setText("x");
            Vec3DemoFrame.this.textFields[1].setText("y");
            Vec3DemoFrame.this.textFields[2].setText("z");
            Vec3DemoFrame.this.textFieldLabel.setText("Field Functions");
            Vec3DemoFrame.this.textFieldLabel.show();
            for (int i = 0; i != 3; ++i) {
                Vec3DemoFrame.this.textFields[i].show();
            }
            this.actionPerformed();
        }
        
        void actionPerformed() {
            Vec3DemoFrame.this.parseError = false;
            for (int i = 0; i != 3; ++i) {
                final ExprParser exprParser = new ExprParser(Vec3DemoFrame.this.textFields[i].getText());
                this.exprs[i] = exprParser.parseExpression();
                if (exprParser.gotError()) {
                    Vec3DemoFrame.this.parseError = true;
                }
            }
        }
        
        void getField(final double[] array, final double[] array2) {
            final double n = 2.0E-4;
            for (int i = 0; i != 3; ++i) {
                array[i] = n * this.exprs[i].eval(array2);
                if (array[i] <= -10.0 || array[i] >= 10.0) {
                    Vec3DemoFrame.this.boundCheck = true;
                }
            }
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
        public int viewPri;
        public double lifetime;
        public double phi;
        public double theta;
        public double phiv;
        public double thetav;
        public double stepsize;
        
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
}
