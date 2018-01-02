import java.awt.event.ItemEvent;
import java.awt.event.MouseEvent;
import java.awt.event.AdjustmentEvent;
import java.awt.event.ActionEvent;
import java.awt.event.ComponentEvent;
import java.awt.FontMetrics;
import java.text.NumberFormat;
import java.awt.image.ImageObserver;
import java.awt.image.ImageProducer;
import java.awt.Graphics;
import java.awt.Event;
import java.awt.Component;
import java.awt.LayoutManager;
import java.awt.Color;
import java.awt.Rectangle;
import java.awt.image.MemoryImageSource;
import java.awt.Label;
import java.awt.Scrollbar;
import java.util.Vector;
import java.awt.Choice;
import java.awt.Checkbox;
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

class AntennaFrame extends Frame implements ComponentListener, ActionListener, AdjustmentListener, MouseMotionListener, MouseListener, ItemListener
{
    Thread engine;
    Dimension winSize;
    Image dbimage;
    Random random;
    int windowWidth;
    int windowHeight;
    int wallWidth;
    Checkbox stoppedCheck;
    Checkbox memoryImageSourceCheck;
    Checkbox intensityCheck;
    Checkbox graphCheck;
    Checkbox infoCheck;
    Choice setupChooser;
    Vector setupList;
    Setup setup;
    Scrollbar zoomBar;
    Scrollbar angleBar;
    Scrollbar freqBar;
    Scrollbar resBar;
    Scrollbar speedBar;
    Scrollbar brightnessBar;
    double colorMult;
    Label[] auxLabels;
    Scrollbar[] auxBars;
    static final int auxBarCount = 5;
    static final double pi = 3.141592653589793;
    double[][][] colorFunc;
    double[] apertureR;
    double[] apertureI;
    int dragX;
    int dragY;
    int[] xpoints;
    int[] ypoints;
    boolean dragging;
    boolean dragClear;
    boolean dragSet;
    boolean recompute;
    double t;
    double wavelength;
    int pause;
    MemoryImageSource imageSource;
    int[] pixels;
    Rectangle viewFourier;
    Rectangle viewGraph;
    double[] fourierFunc;
    int arrayCount;
    int arrayStart;
    int arraySep;
    static final int phaseColorCount = 480;
    Color[] phaseColors;
    AntennaCanvas cv;
    Antenna applet;
    boolean calculateNotice;
    int resBarValue;
    double bessj0;
    double bessy0;
    
    public String getAppletInfo() {
        return "Antenna by Paul Falstad";
    }
    
    int getrand(final int n) {
        int nextInt = this.random.nextInt();
        if (nextInt < 0) {
            nextInt = -nextInt;
        }
        return nextInt % n;
    }
    
    AntennaFrame(final Antenna applet) {
        super("Antenna Applet");
        this.engine = null;
        this.windowWidth = 50;
        this.windowHeight = 50;
        this.arrayCount = 0;
        this.resBarValue = -1;
        this.applet = applet;
    }
    
    public void init() {
        this.setupList = new Vector();
        for (Setup next = new LinearSetup(); next != null; next = next.createNext()) {
            this.setupList.addElement(next);
        }
        final String property = System.getProperty("os.name");
        final String property2 = System.getProperty("java.version");
        int n = 80;
        boolean b = false;
        if (property.indexOf("Windows") == 0) {
            n = 120;
            if (property2.indexOf("1.1") == 0) {
                b = true;
            }
        }
        this.fourierFunc = new double[181];
        this.setLayout(new AntennaLayout());
        (this.cv = new AntennaCanvas(this)).addComponentListener(this);
        this.cv.addMouseMotionListener(this);
        this.cv.addMouseListener(this);
        this.add(this.cv);
        this.setupChooser = new Choice();
        for (int i = 0; i != this.setupList.size(); ++i) {
            this.setupChooser.add("Setup: " + ((Setup)this.setupList.elementAt(i)).getName());
        }
        this.setup = this.setupList.elementAt(0);
        this.setupChooser.addItemListener(this);
        this.add(this.setupChooser);
        (this.intensityCheck = new Checkbox("Show Intensity", true)).addItemListener(this);
        this.add(this.intensityCheck);
        (this.graphCheck = new Checkbox("Show Graph", false)).addItemListener(this);
        this.add(this.graphCheck);
        (this.infoCheck = new Checkbox("Show Info", true)).addItemListener(this);
        this.add(this.infoCheck);
        (this.stoppedCheck = new Checkbox("Stopped")).addItemListener(this);
        this.stoppedCheck.disable();
        this.add(this.stoppedCheck);
        (this.memoryImageSourceCheck = new Checkbox("Alternate Rendering", b)).addItemListener(this);
        this.add(this.memoryImageSourceCheck);
        this.add(new Label("Speed", 1));
        this.add(this.speedBar = new Scrollbar(0, 14, 1, 1, 40));
        this.speedBar.addAdjustmentListener(this);
        this.speedBar.disable();
        this.add(new Label("Zoom Out", 1));
        this.add(this.zoomBar = new Scrollbar(0, 10, 1, 10, 200));
        this.zoomBar.addAdjustmentListener(this);
        this.add(new Label("Resolution", 1));
        this.add(this.resBar = new Scrollbar(0, n, 5, 120, 600));
        this.resBar.addAdjustmentListener(this);
        this.add(new Label("Source Frequency", 1));
        this.add(this.freqBar = new Scrollbar(0, 120, 1, 1, 236));
        this.freqBar.addAdjustmentListener(this);
        this.add(new Label("Brightness", 1));
        this.add(this.brightnessBar = new Scrollbar(0, 27, 1, 1, 1000));
        this.brightnessBar.addAdjustmentListener(this);
        this.auxLabels = new Label[5];
        this.auxBars = new Scrollbar[5];
        for (int j = 0; j != 5; ++j) {
            this.add(this.auxLabels[j] = new Label("Aux " + (j + 1), 1));
            this.add(this.auxBars[j] = new Scrollbar(0, 50, 1, 0, 100));
            this.auxBars[j].addAdjustmentListener(this);
        }
        this.add(new Label("http://www.falstad.com"));
        try {
            final String parameter = this.applet.getParameter("PAUSE");
            if (parameter != null) {
                this.pause = Integer.parseInt(parameter);
            }
        }
        catch (Exception ex) {}
        this.phaseColors = new Color[481];
        for (int k = 0; k != 480; ++k) {
            final int n2 = 80;
            final int n3 = k % n2 * 255 / n2;
            final int n4 = 255 - n3;
            Color color = null;
            switch (k / n2) {
                case 0: {
                    color = new Color(255, n3, 0);
                    break;
                }
                case 1: {
                    color = new Color(n4, 255, 0);
                    break;
                }
                case 2: {
                    color = new Color(0, 255, n3);
                    break;
                }
                case 3: {
                    color = new Color(0, n4, 255);
                    break;
                }
                case 4: {
                    color = new Color(n3, 0, 255);
                    break;
                }
                case 5: {
                    color = new Color(255, 0, n4);
                    break;
                }
            }
            this.phaseColors[k] = color;
        }
        this.phaseColors[480] = this.phaseColors[0];
        this.random = new Random();
        this.setResolution();
        this.reinit();
        this.setup = this.setupList.elementAt(0);
        this.cv.setBackground(Color.black);
        this.cv.setForeground(Color.lightGray);
        this.resize(750, 600);
        this.handleResize();
        this.show();
    }
    
    void reinit() {
        this.doSetup();
    }
    
    void apertureChanged() {
        this.clearAperture();
        this.setup.doAperture();
        double n = 0.0;
        for (int i = 0; i != this.wallWidth; ++i) {
            final double sqrt = Math.sqrt(this.apertureR[i] * this.apertureR[i] + this.apertureI[i] * this.apertureI[i]);
            if (sqrt > n) {
                n = sqrt;
            }
        }
        for (int j = 0; j != this.wallWidth; ++j) {
            final double[] apertureR = this.apertureR;
            final int n2 = j;
            apertureR[n2] /= n;
            final double[] apertureI = this.apertureI;
            final int n3 = j;
            apertureI[n3] /= n;
        }
        this.recompute = true;
    }
    
    void handleResize() {
        final Dimension size = this.cv.getSize();
        this.winSize = size;
        final Dimension dimension = size;
        if (this.winSize.width == 0) {
            return;
        }
        this.dbimage = this.createImage(dimension.width, dimension.height);
        this.pixels = new int[dimension.width * dimension.height];
        for (int i = 0; i != dimension.width * dimension.height; ++i) {
            this.pixels[i] = -16777216;
        }
        this.imageSource = new MemoryImageSource(dimension.width, dimension.height, this.pixels, 0, dimension.width);
        this.viewFourier = new Rectangle(this.winSize.width - 100, this.winSize.height - 100, 100, 100);
    }
    
    public boolean handleEvent(final Event event) {
        if (event.id == 201) {
            this.applet.destroyFrame();
            return true;
        }
        return super.handleEvent(event);
    }
    
    void centerString(final Graphics graphics, final String s, final int n) {
        graphics.drawString(s, (this.winSize.width - graphics.getFontMetrics().stringWidth(s)) / 2, n);
    }
    
    public void paint(final Graphics graphics) {
        this.cv.repaint();
    }
    
    public void updateAntenna(final Graphics graphics) {
        if (this.winSize == null || this.winSize.width == 0) {
            this.handleResize();
            return;
        }
        if (!this.calculateNotice && this.recompute) {
            final FontMetrics fontMetrics = graphics.getFontMetrics();
            graphics.setColor(Color.black);
            final String s = "Calculating...";
            graphics.fillRect(0, this.winSize.height - 30, 20 + fontMetrics.stringWidth(s), 30);
            graphics.setColor(Color.white);
            graphics.drawString(s, 10, this.winSize.height - 10);
            this.cv.repaint(0L);
            this.calculateNotice = true;
            return;
        }
        this.calculateNotice = false;
        final boolean state = this.memoryImageSourceCheck.getState();
        final Graphics graphics2 = this.dbimage.getGraphics();
        if (!state) {
            graphics2.setColor(this.cv.getBackground());
            graphics2.fillRect(0, 0, this.winSize.width, this.winSize.height);
        }
        if (!this.stoppedCheck.getState()) {
            final int value = this.speedBar.getValue();
            this.t += (value * 0.05 + value * this.getrand(20) * 2.7279275E-5) * (this.freqBar.getValue() / 34.0);
        }
        final double cos = Math.cos(this.t);
        final double sin = Math.sin(this.t);
        if (this.stoppedCheck.getState()) {}
        if (this.recompute) {
            this.recompute = false;
            final double n = this.zoomBar.getValue() / 10.0;
            int n2 = -1;
            int n3 = -1;
            final int n4 = (int)(n * this.windowWidth);
            for (int i = 0; i != this.wallWidth; ++i) {
                if (this.apertureR[i] != 0.0 || this.apertureI[i] != 0.0) {
                    n3 = i;
                    if (n2 == -1) {
                        n2 = i;
                    }
                }
            }
            if (n2 == -1) {
                n3 = (n2 = this.wallWidth / 2);
            }
            int n5;
            int n6;
            int j;
            for (n5 = this.wallWidth / 2 - n3, n6 = this.wallWidth / 2 + n4 / 2 - n2, j = 1; j < n4 + n6 - n5; j *= 2) {}
            final int n7 = j * 2 - 1;
            final double[] array = new double[j * 2];
            final double[] array2 = new double[j * 2];
            final double[] array3 = new double[j * 2];
            for (int k = 0; k != this.wallWidth; ++k) {
                if (this.apertureR[k] != 0.0 || this.apertureI[k] != 0.0) {
                    final int n8 = (k - this.wallWidth / 2) * 2;
                    final int n9 = k - this.wallWidth / 2;
                    array2[n8 & n7] = this.apertureR[k];
                    array2[n8 + 1 & n7] = this.apertureI[k];
                }
            }
            this.four1(array2, j, 1);
            final double n10 = this.freqBar.getValue() / 120.0;
            Math.sqrt(n10);
            this.wavelength = 6.283185307179586 / n10;
            final int n11 = this.windowHeight / 2;
            final boolean b = this.setup instanceof LinearSetup;
            for (int l = 0; l <= n11; ++l) {
                for (int n12 = 0; n12 != j * 2; ++n12) {
                    array[n12] = 0.0;
                }
                final int n13 = l - n11;
                final double n14 = n13 * n13 * n * n;
                final int n15 = j * 2;
                int n17;
                for (int n16 = n17 = 0; n17 <= n6; ++n17, n16 += 2) {
                    final double sqrt = Math.sqrt(n17 * n17 + n14);
                    final double n18 = sqrt * n10;
                    final double n19 = 1.0 / (sqrt + 1.0E-4);
                    array[n16] = Math.cos(n18) * n19;
                    array[n16 + 1] = -Math.sin(n18) * n19;
                    if (b) {
                        final double n20 = n13 * n + 1.0;
                        final double sqrt2 = Math.sqrt(n17 * n17 + n20 * n20);
                        final double n21 = sqrt2 * n10;
                        final double n22 = 1.0 / (sqrt2 + 1.0E-4);
                        final double[] array4 = array;
                        final int n23 = n16;
                        array4[n23] -= Math.cos(n21) * n22;
                        final double[] array5 = array;
                        final int n24 = n16 + 1;
                        array5[n24] += Math.sin(n21) * n22;
                    }
                    if (n17 > 0) {
                        array[n15 - n16] = array[n16];
                        array[n15 - n16 + 1] = array[n16 + 1];
                    }
                }
                this.four1(array, j, 1);
                for (int n25 = 0; n25 != j; ++n25) {
                    final int n26 = n25 * 2;
                    final double n27 = array[n26] * array2[n26] - array[n26 + 1] * array2[n26 + 1];
                    final double n28 = array[n26 + 1] * array2[n26] + array[n26] * array2[n26 + 1];
                    array[n26] = n27;
                    array[n26 + 1] = n28;
                }
                this.four1(array, j, -1);
                final double n29 = 400.0 / j;
                for (int n30 = 0; n30 != this.windowWidth; ++n30) {
                    final double n31 = (int)((n30 - this.windowWidth / 2) * n);
                    final double n32 = (int)((n30 - this.windowWidth / 2 + 1) * n);
                    final int n33 = (int)n31;
                    final int n34 = (int)n32;
                    if (this.intensityCheck.getState()) {
                        this.colorFunc[n30][l][0] = 0.0;
                    }
                    else {
                        this.colorFunc[n30][l][0] = 0.0;
                        this.colorFunc[n30][l][1] = 0.0;
                    }
                    for (int n35 = n33; n35 <= n34; ++n35) {
                        final double n36 = array[n7 & n35 * 2] * n29;
                        final double n37 = array[n7 & n35 * 2 + 1] * n29;
                        double n38 = 0.001;
                        if (n35 == n33) {
                            n38 *= 1.0 - (n31 - n33);
                        }
                        else if (n35 == n34) {
                            n38 *= n32 - n34;
                        }
                        if (this.intensityCheck.getState()) {
                            final double[] array6 = this.colorFunc[n30][l];
                            final int n39 = 0;
                            array6[n39] += (n36 * n36 + n37 * n37) * n38;
                        }
                        else {
                            final double[] array7 = this.colorFunc[n30][l];
                            final int n40 = 0;
                            array7[n40] += n36 * n38;
                            final double[] array8 = this.colorFunc[n30][l];
                            final int n41 = 1;
                            array8[n41] += n37 * n38;
                        }
                    }
                    if (this.intensityCheck.getState()) {
                        final double[] array9 = this.colorFunc[n30][l];
                        final int n42 = 0;
                        array9[n42] /= n32 - n31;
                    }
                    else {
                        final double[] array10 = this.colorFunc[n30][l];
                        final int n43 = 0;
                        array10[n43] /= n32 - n31;
                        final double[] array11 = this.colorFunc[n30][l];
                        final int n44 = 1;
                        array11[n44] /= n32 - n31;
                    }
                }
            }
            final int n45 = (this.intensityCheck.getState() || !b) ? 1 : -1;
            for (int n46 = 0; n46 != n11; ++n46) {
                final int n47 = this.windowHeight - 1 - n46;
                for (int n48 = 0; n48 != this.windowWidth; ++n48) {
                    this.colorFunc[n48][n47][0] = n45 * this.colorFunc[n48][n46][0];
                    this.colorFunc[n48][n47][1] = n45 * this.colorFunc[n48][n46][1];
                }
            }
        }
        this.colorMult = 30.0 * Math.exp(this.brightnessBar.getValue() / 50.0 - 10.0);
        if (!this.intensityCheck.getState()) {
            this.colorMult *= 1.5;
        }
        for (int n49 = 0; n49 != this.windowHeight; ++n49) {
            for (int n50 = 0; n50 != this.windowWidth; ++n50) {
                final int n51 = n50 * this.winSize.width / this.windowWidth;
                final int n52 = n49 * this.winSize.height / this.windowHeight;
                final int n53 = (n50 + 1) * this.winSize.width / this.windowWidth;
                final int n54 = (n49 + 1) * this.winSize.height / this.windowHeight;
                int n55;
                if (this.intensityCheck.getState()) {
                    n55 = -16777216 + (this.getColorValue(n50, n49, 0) << 8);
                }
                else {
                    final double n56 = (this.colorFunc[n50][n49][0] * cos - this.colorFunc[n50][n49][1] * sin) * this.colorMult;
                    if (n56 > 0.0) {
                        int n57 = (int)(n56 * 255.0);
                        if (n57 > 255) {
                            n57 = 255;
                        }
                        n55 = -16777216 + (n57 << 8);
                    }
                    else {
                        int n58 = (int)(-n56 * 255.0);
                        if (n58 > 255) {
                            n58 = 255;
                        }
                        n55 = -16777216 + (n58 << 16);
                    }
                }
                if (state) {
                    for (int n59 = n51; n59 < n53; ++n59) {
                        for (int n60 = n52; n60 < n54; ++n60) {
                            this.pixels[n59 + n60 * this.winSize.width] = n55;
                        }
                    }
                }
                else {
                    graphics2.setColor(new Color(n55));
                    graphics2.fillRect(n51, n52, n53 - n51, n54 - n52);
                }
            }
        }
        final double n61 = this.zoomBar.getValue() / 10.0;
        for (int n62 = 0; n62 != this.windowWidth; ++n62) {
            final double n63 = (int)((n62 - this.windowWidth / 2) * n61);
            final double n64 = (int)((n62 - this.windowWidth / 2 + 1) * n61);
            final int n65 = (int)n63;
            final int n66 = (int)n64;
            double n67 = 0.0;
            double n68 = 0.0;
            for (int n69 = n65; n69 <= n66; ++n69) {
                final int n70 = n69 + this.wallWidth / 2;
                if (n70 >= 0) {
                    if (n70 < this.wallWidth) {
                        double n71 = 1.0;
                        if (n69 == n65) {
                            n71 *= 1.0 - (n63 - n65);
                        }
                        else if (n69 == n66) {
                            n71 *= n64 - n66;
                        }
                        n68 += (this.apertureR[n70] * this.apertureR[n70] + this.apertureI[n70] * this.apertureI[n70]) * n71;
                        final double n72 = Math.atan2(this.apertureI[n70], this.apertureR[n70]) / 3.141592653589793;
                        if (n72 < 0.0) {
                            n67 += (2.0 + n72) * n71;
                        }
                        else {
                            n67 += n72 * n71;
                        }
                    }
                }
            }
            final double n73 = n68 / (n64 - n63);
            final double n74 = n67 / ((n64 - n63) * 2.0);
            int n75 = (int)(n73 * 255.0);
            int n76 = (int)(n74 * n73 * 255.0);
            if (n75 < 64) {
                n75 = 64;
            }
            if (n75 > 255) {
                n75 = 255;
            }
            if (n76 < 0) {
                n76 = 0;
            }
            if (n76 > 255) {
                n76 = 255;
            }
            if (n73 != 0.0) {
                final int n77 = -16777216 + (n76 << 16) | n75;
                final int n78 = n62 * this.winSize.width / this.windowWidth;
                final int n79 = (n62 + 1) * this.winSize.width / this.windowWidth;
                final int n80 = this.windowHeight / 2 * this.winSize.height / this.windowHeight;
                final int n81 = (this.windowHeight / 2 + 1) * this.winSize.height / this.windowHeight;
                if (state) {
                    for (int n82 = n78; n82 < n79; ++n82) {
                        for (int n83 = n80; n83 < n81; ++n83) {
                            this.pixels[n82 + n83 * this.winSize.width] = n77;
                        }
                    }
                }
                else {
                    graphics2.setColor(new Color(n77));
                    graphics2.fillRect(n78, n80, n79 - n78, n81 - n80);
                }
            }
        }
        if (state) {
            graphics2.drawImage(this.cv.createImage(this.imageSource), 0, 0, this);
        }
        if (this.setup instanceof FourierFunctionSetup) {
            graphics2.setColor(this.cv.getBackground());
            graphics2.fillRect(this.viewFourier.x, this.viewFourier.y, this.viewFourier.width, this.viewFourier.height);
            final int n84 = this.viewFourier.x + this.viewFourier.width / 2;
            final int n85 = this.viewFourier.y + this.viewFourier.height - 10;
            final int n86 = 40;
            int n87 = -1;
            int n88 = -1;
            graphics2.setColor(Color.darkGray);
            graphics2.drawLine(this.viewFourier.x, n85, this.viewFourier.x + this.viewFourier.width, n85);
            graphics2.drawLine(n84, this.viewFourier.y, n84, n85);
            graphics2.setColor(Color.white);
            for (int n89 = 0; n89 <= 180; ++n89) {
                final double n90 = (180 - n89) * 3.141592653589793 / 180.0;
                final int n91 = n84 + (int)(Math.cos(n90) * n86 * this.fourierFunc[n89]);
                final int n92 = n85 - (int)(Math.sin(n90) * n86 * this.fourierFunc[n89]);
                if (n87 != -1) {
                    graphics2.drawLine(n87, n88, n91, n92);
                }
                n87 = n91;
                n88 = n92;
            }
        }
        int n93 = 100;
        if (this.infoCheck.getState()) {
            final NumberFormat instance = NumberFormat.getInstance();
            instance.setMaximumFractionDigits(2);
            final String info = this.setup.getInfo(instance);
            if (info != null) {
                graphics2.setColor(Color.black);
                final int n94 = 20 + graphics2.getFontMetrics().stringWidth(info);
                graphics2.fillRect(this.winSize.width - n94, this.winSize.height - 30, n94, 30);
                graphics2.setColor(Color.white);
                graphics2.drawString(info, this.winSize.width - n94 + 10, this.winSize.height - 10);
                n93 = n94;
            }
        }
        if (this.graphCheck.getState()) {
            this.viewGraph = new Rectangle(0, this.winSize.height - 100, this.winSize.width - n93, 100);
            graphics2.setColor(this.cv.getBackground());
            graphics2.fillRect(this.viewGraph.x, this.viewGraph.y, this.viewGraph.width, this.viewGraph.height);
            final int n95 = this.viewGraph.x + this.viewGraph.width / 2;
            final int n96 = this.viewGraph.y + this.viewGraph.height - 10;
            graphics2.setColor(Color.darkGray);
            graphics2.drawLine(this.viewGraph.x, n96, this.viewGraph.x + this.viewGraph.width, n96);
            graphics2.drawLine(n95, this.viewGraph.y, n95, this.viewGraph.y + this.viewGraph.height);
            graphics2.setColor(Color.white);
            final int n97 = this.viewGraph.height - 20;
            final int n98 = this.viewGraph.width / this.arrayCount;
            for (int n99 = 0; n99 != this.arrayCount; ++n99) {
                int n100 = n99 * this.viewGraph.width / this.arrayCount;
                if (n98 > 1) {
                    n100 = n99 * n98 + n98 / 2 + (this.viewGraph.width - n98 * this.arrayCount) / 2;
                }
                final int n101 = this.arrayStart + n99 * this.arraySep;
                final int n102 = n96 - (int)(n97 * Math.sqrt(this.apertureR[n101] * this.apertureR[n101] + this.apertureI[n101] * this.apertureI[n101]));
                graphics2.setColor(this.phaseColors[(int)((Math.atan2(this.apertureI[n101], this.apertureR[n101]) + 3.141592653589793) * 480.0 / 6.483185307179586)]);
                graphics2.drawLine(n100, n102, n100, n96);
            }
        }
        graphics.drawImage(this.dbimage, 0, 0, this);
        if (!this.intensityCheck.getState() && !this.stoppedCheck.getState()) {
            this.cv.repaint(this.pause);
        }
    }
    
    int getColorValue(final int n, final int n2, final int n3) {
        int n4 = (int)(this.colorFunc[n][n2][n3] * this.colorMult);
        if (n4 > 255) {
            n4 = 255;
        }
        return n4;
    }
    
    int abs(final int n) {
        return (n < 0) ? (-n) : n;
    }
    
    int sign(final int n) {
        return (n < 0) ? -1 : ((n == 0) ? 0 : 1);
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
    }
    
    public void adjustmentValueChanged(final AdjustmentEvent adjustmentEvent) {
        System.out.print(((Scrollbar)adjustmentEvent.getSource()).getValue() + "\n");
        if (adjustmentEvent.getSource() == this.resBar && this.resBar.getValue() != this.resBarValue) {
            this.setResolution();
        }
        for (int i = 0; i != 5; ++i) {
            if (adjustmentEvent.getSource() == this.auxBars[i]) {
                this.apertureChanged();
                break;
            }
        }
        if (adjustmentEvent.getSource() == this.zoomBar || adjustmentEvent.getSource() == this.freqBar) {
            this.apertureChanged();
        }
        this.cv.repaint(this.pause);
    }
    
    void setResolution() {
        final int value = this.resBar.getValue();
        this.windowHeight = value;
        this.windowWidth = value;
        this.resBarValue = value;
        if ((this.windowWidth & 0x1) == 0x1) {
            final int n = this.resBarValue - 1;
            this.windowHeight = n;
            this.windowWidth = n;
        }
        ++this.windowHeight;
        this.colorFunc = new double[this.windowWidth][this.windowHeight][2];
        this.wallWidth = 512;
        this.apertureR = new double[this.wallWidth];
        this.apertureI = new double[this.wallWidth];
        System.out.print(this.windowWidth + " " + this.windowHeight + "\n");
        this.apertureChanged();
    }
    
    void setResolution(final int value) {
        this.resBar.setValue(value);
        this.setResolution();
    }
    
    public void mouseDragged(final MouseEvent mouseEvent) {
        this.dragging = true;
        this.edit(mouseEvent);
        this.cv.repaint(this.pause);
    }
    
    public void mouseMoved(final MouseEvent mouseEvent) {
        if ((mouseEvent.getModifiers() & 0x10) != 0x0) {
            return;
        }
        final int x = mouseEvent.getX();
        final int y = mouseEvent.getY();
        this.dragX = x;
        this.dragY = y;
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
        this.apertureChanged();
        this.cv.repaint();
    }
    
    void edit(final MouseEvent mouseEvent) {
        final int x = mouseEvent.getX();
        final int y = mouseEvent.getY();
        if (!this.viewFourier.contains(x, y)) {
            return;
        }
        final int n = this.viewFourier.x + this.viewFourier.width / 2;
        final int n2 = this.viewFourier.y + this.viewFourier.height - 10;
        final int n3 = 40;
        final int n4 = x - n;
        final int n5 = y - n2;
        int n6 = (int)(180.0 - Math.atan2(-n5, n4) * 180.0 / 3.141592653589793);
        final int n7 = (int)(180.0 - Math.atan2(-(this.dragY - n2), this.dragX - n) * 180.0 / 3.141592653589793);
        this.dragX = mouseEvent.getX();
        this.dragY = mouseEvent.getY();
        while (n6 >= 0 && n6 <= 180) {
            this.fourierFunc[n6] = Math.sqrt(n4 * n4 + n5 * n5) / n3;
            if (n6 == n7) {
                return;
            }
            if (n7 < n6) {
                --n6;
            }
            else {
                ++n6;
            }
        }
    }
    
    public void itemStateChanged(final ItemEvent itemEvent) {
        if (itemEvent.getItemSelectable() == this.intensityCheck) {
            this.setResolution();
            this.recompute = true;
            this.cv.repaint();
            if (this.intensityCheck.getState()) {
                this.stoppedCheck.disable();
                this.speedBar.disable();
            }
            else {
                this.stoppedCheck.enable();
                this.speedBar.enable();
            }
            return;
        }
        if (itemEvent.getItemSelectable() == this.stoppedCheck || itemEvent.getItemSelectable() == this.graphCheck || itemEvent.getItemSelectable() == this.infoCheck) {
            this.cv.repaint();
            return;
        }
        if (itemEvent.getItemSelectable() == this.memoryImageSourceCheck) {
            this.dbimage = this.createImage(this.winSize.width, this.winSize.height);
        }
        if (itemEvent.getItemSelectable() == this.setupChooser) {
            this.doSetup();
            this.cv.repaint(this.pause);
        }
    }
    
    void clearAperture() {
        for (int i = 0; i != this.wallWidth; ++i) {
            this.apertureR[i] = (this.apertureI[i] = 0.0);
        }
    }
    
    void doSetup() {
        for (int i = 0; i != 5; ++i) {
            this.auxBars[i].setValue(10);
        }
        this.freqBar.setValue(120);
        this.zoomBar.setValue(10);
        this.setup = this.setupList.elementAt(this.setupChooser.getSelectedIndex());
        this.brightnessBar.setValue(500);
        this.setup.select();
        this.wavelength = 6.283185307179586 / (this.freqBar.getValue() / 120.0);
        this.apertureChanged();
        int j;
        for (j = 0; j < this.setup.getAuxBarCount(); ++j) {
            this.auxLabels[j].show();
            this.auxBars[j].show();
        }
        while (j < 5) {
            this.auxLabels[j].hide();
            this.auxBars[j].hide();
            ++j;
        }
        this.validate();
    }
    
    void four1(final double[] array, final int n, final int n2) {
        final int i = n << 1;
        int n3 = 1;
        for (int j = 1; j < i; j += 2) {
            if (n3 > j) {
                final double n4 = array[n3 - 1];
                array[n3 - 1] = array[j - 1];
                array[j - 1] = n4;
                final double n5 = array[n3];
                array[n3] = array[j];
                array[j] = n5;
            }
            int n6;
            for (n6 = i >> 1; n6 >= 2 && n3 > n6; n3 -= n6, n6 >>= 1) {}
            n3 += n6;
        }
        int n8;
        for (int n7 = 2; i > n7; n7 = n8) {
            n8 = 2 * n7;
            final double n9 = 6.28318530717959 / (n2 * n7);
            final double sin = Math.sin(0.5 * n9);
            final double n10 = -2.0 * sin * sin;
            final double sin2 = Math.sin(n9);
            double n11 = 1.0;
            double n12 = 0.0;
            for (int k = 1; k < n7; k += 2) {
                for (int l = k; l <= i; l += n8) {
                    final int n13 = l + n7;
                    final double n14 = n11 * array[n13 - 1] - n12 * array[n13];
                    final double n15 = n11 * array[n13] + n12 * array[n13 - 1];
                    array[n13 - 1] = array[l - 1] - n14;
                    array[n13] = array[l] - n15;
                    final int n16 = l - 1;
                    array[n16] += n14;
                    final int n17 = l;
                    array[n17] += n15;
                }
                final double n18;
                n11 += (n18 = n11) * n10 - n12 * sin2;
                n12 += n12 * n10 + n18 * sin2;
            }
        }
    }
    
    void computeBessel(final double n) {
        if (n < 8.0) {
            final double n2 = n * n;
            this.bessj0 = (5.7568490574E10 + n2 * (-1.3362590354E10 + n2 * (6.516196407E8 + n2 * (-1.121442418E7 + n2 * (77392.33017 + n2 * -184.9052456))))) / (5.7568490411E10 + n2 * (1.029532985E9 + n2 * (9494680.718 + n2 * (59272.64853 + n2 * (267.8532712 + n2 * 1.0)))));
            this.bessy0 = (-2.957821389E9 + n2 * (7.062834065E9 + n2 * (-5.123598036E8 + n2 * (1.087988129E7 + n2 * (-86327.92757 + n2 * 228.4622733))))) / (4.0076544269E10 + n2 * (7.452499648E8 + n2 * (7189466.438 + n2 * (47447.2647 + n2 * (226.1030244 + n2 * 1.0))))) + 0.636619772 * this.bessj0 * Math.log(n);
        }
        else {
            final double n3 = 8.0 / n;
            final double n4 = n3 * n3;
            final double n5 = n - 0.785398164;
            final double n6 = 1.0 + n4 * (-0.001098628627 + n4 * (2.734510407E-5 + n4 * (-2.073370639E-6 + n4 * 2.093887211E-7)));
            final double n7 = -0.01562499995 + n4 * (1.430488765E-4 + n4 * (-6.911147651E-6 + n4 * (7.621095161E-7 - n4 * 9.34935152E-8)));
            final double sqrt = Math.sqrt(0.636619772 / n);
            final double cos = Math.cos(n5);
            final double sin = Math.sin(n5);
            this.bessj0 = sqrt * (cos * n6 - n3 * sin * n7);
            this.bessy0 = sqrt * (sin * n6 + n3 * cos * (-0.01562499995 + n4 * (1.430488765E-4 + n4 * (-6.911147651E-6 + n4 * (7.621095161E-7 + n4 * -9.34945152E-8)))));
        }
    }
    
    abstract class Setup
    {
        abstract String getName();
        
        abstract void select();
        
        abstract void doAperture();
        
        abstract Setup createNext();
        
        String getInfo(final NumberFormat numberFormat) {
            return null;
        }
        
        int getAuxBarCount() {
            return 2;
        }
    }
    
    class LinearSetup extends Setup
    {
        String getName() {
            return "Linear Antenna (End-Fed)";
        }
        
        void select() {
            AntennaFrame.this.auxLabels[0].setText("Length");
            AntennaFrame.this.brightnessBar.setValue(580);
            AntennaFrame.this.freqBar.setValue(19);
        }
        
        void doAperture() {
            int arrayCount = AntennaFrame.this.auxBars[0].getValue() * 2;
            if (arrayCount == 0) {
                arrayCount = 2;
            }
            final double n = AntennaFrame.this.freqBar.getValue() / 120.0;
            for (int i = 0; i != arrayCount; ++i) {
                AntennaFrame.this.apertureR[AntennaFrame.this.wallWidth / 2 - arrayCount / 2 + i] = Math.sin((arrayCount - i) * n);
            }
            AntennaFrame.this.arrayCount = arrayCount;
            AntennaFrame.this.arraySep = 1;
            AntennaFrame.this.arrayStart = AntennaFrame.this.wallWidth / 2 - arrayCount / 2;
        }
        
        String getInfo(final NumberFormat numberFormat) {
            return "Length/wavelength = " + numberFormat.format(AntennaFrame.this.auxBars[0].getValue() * 2 / AntennaFrame.this.wavelength);
        }
        
        int getAuxBarCount() {
            return 1;
        }
        
        Setup createNext() {
            return new LinearCenterSetup();
        }
    }
    
    class LinearCenterSetup extends LinearSetup
    {
        String getName() {
            return "Linear Antenna (Center-Fed)";
        }
        
        void select() {
            AntennaFrame.this.auxLabels[0].setText("Length");
            AntennaFrame.this.auxLabels[1].setText("Feed Separation");
            AntennaFrame.this.auxBars[1].setValue(0);
            AntennaFrame.this.brightnessBar.setValue(580);
            AntennaFrame.this.freqBar.setValue(19);
            AntennaFrame.this.auxBars[0].setValue(20);
        }
        
        void doAperture() {
            int value = AntennaFrame.this.auxBars[0].getValue();
            final int value2 = AntennaFrame.this.auxBars[1].getValue();
            if (value == 0) {
                value = 2;
            }
            final double n = AntennaFrame.this.freqBar.getValue() / 120.0;
            for (int i = 0; i != value; ++i) {
                AntennaFrame.this.apertureR[AntennaFrame.this.wallWidth / 2 - value2 / 2 - i] = (AntennaFrame.this.apertureR[AntennaFrame.this.wallWidth / 2 + value2 / 2 + i] = Math.sin((value - i) * n));
            }
            AntennaFrame.this.arrayCount = value * 2 + value2;
            AntennaFrame.this.arraySep = 1;
            AntennaFrame.this.arrayStart = AntennaFrame.this.wallWidth / 2 - value2 / 2 - value;
        }
        
        String getInfo(final NumberFormat numberFormat) {
            return "Total length/wavelength = " + numberFormat.format(AntennaFrame.this.auxBars[0].getValue() * 2 / AntennaFrame.this.wavelength);
        }
        
        int getAuxBarCount() {
            return 2;
        }
        
        Setup createNext() {
            return new LoopSetup();
        }
    }
    
    class LoopSetup extends Setup
    {
        String getName() {
            return "Loop Cross Section";
        }
        
        void select() {
            AntennaFrame.this.auxLabels[0].setText("Size");
            AntennaFrame.this.brightnessBar.setValue(620);
            AntennaFrame.this.freqBar.setValue(17);
        }
        
        void doAperture() {
            final int n = AntennaFrame.this.auxBars[0].getValue() + 1;
            AntennaFrame.this.apertureR[AntennaFrame.this.wallWidth / 2 + n] = 1.0;
            AntennaFrame.this.apertureR[AntennaFrame.this.wallWidth / 2 - n] = -1.0;
        }
        
        int getAuxBarCount() {
            return 1;
        }
        
        String getInfo(final NumberFormat numberFormat) {
            return "Diameter/wavelength = " + numberFormat.format((AntennaFrame.this.auxBars[0].getValue() + 1) * 2 / AntennaFrame.this.wavelength);
        }
        
        Setup createNext() {
            return new BroadsideArraySetup();
        }
    }
    
    abstract class UniformArraySetup extends Setup
    {
        int spacing;
        
        void select() {
            AntennaFrame.this.auxLabels[0].setText("Separation");
            AntennaFrame.this.auxLabels[1].setText("Phase Difference");
            AntennaFrame.this.auxLabels[2].setText("Count");
            AntennaFrame.this.brightnessBar.setValue(550);
        }
        
        void doAperture() {
            int value = AntennaFrame.this.auxBars[0].getValue();
            final double n = AntennaFrame.this.auxBars[1].getValue() * 2 * 3.141592653589793 / 100.0;
            int value2 = AntennaFrame.this.auxBars[2].getValue();
            if (value2 == 0) {
                value2 = 1;
            }
            if (value == 0) {
                value = 1;
            }
            if (value * value2 > AntennaFrame.this.wallWidth) {
                value = AntennaFrame.this.wallWidth / value2;
            }
            this.spacing = value;
            for (int i = 0; i != value2; ++i) {
                final int arrayStart = AntennaFrame.this.wallWidth / 2 - (value2 * value - value + 1) / 2 + i * value;
                if (i == 0) {
                    AntennaFrame.this.arrayStart = arrayStart;
                }
                AntennaFrame.this.apertureR[arrayStart] = Math.cos(n * i);
                AntennaFrame.this.apertureI[arrayStart] = Math.sin(n * i);
            }
            AntennaFrame.this.arrayCount = value2;
            AntennaFrame.this.arraySep = value;
        }
        
        String getInfo(final NumberFormat numberFormat) {
            return "Separation/wavelength = " + numberFormat.format(this.spacing / AntennaFrame.this.wavelength);
        }
        
        int getAuxBarCount() {
            return 3;
        }
    }
    
    class BroadsideArraySetup extends UniformArraySetup
    {
        String getName() {
            return "Broadside Array";
        }
        
        void select() {
            super.select();
            AntennaFrame.this.freqBar.setValue(77);
            AntennaFrame.this.auxBars[0].setValue(3);
            AntennaFrame.this.auxBars[1].setValue(0);
            AntennaFrame.this.auxBars[2].setValue(10);
        }
        
        Setup createNext() {
            return new EndFireArraySetup();
        }
    }
    
    class EndFireArraySetup extends UniformArraySetup
    {
        String getName() {
            return "End-Fire Array";
        }
        
        void select() {
            super.select();
            AntennaFrame.this.freqBar.setValue(77);
            AntennaFrame.this.auxBars[0].setValue(3);
            AntennaFrame.this.auxBars[1].setValue(33);
            AntennaFrame.this.auxBars[2].setValue(10);
        }
        
        Setup createNext() {
            return new BinomialArraySetup();
        }
    }
    
    class BinomialArraySetup extends Setup
    {
        int spacing;
        
        String getName() {
            return "Binomial Array";
        }
        
        void select() {
            AntennaFrame.this.freqBar.setValue(77);
            AntennaFrame.this.auxLabels[0].setText("Separation");
            AntennaFrame.this.auxLabels[1].setText("Phase Difference");
            AntennaFrame.this.auxLabels[2].setText("Count");
            AntennaFrame.this.auxBars[0].setValue(3);
            AntennaFrame.this.auxBars[1].setValue(0);
            AntennaFrame.this.auxBars[2].setValue(10);
            AntennaFrame.this.brightnessBar.setValue(600);
        }
        
        void doAperture() {
            int value = AntennaFrame.this.auxBars[0].getValue();
            final double n = AntennaFrame.this.auxBars[1].getValue() * 2 * 3.141592653589793 / 100.0;
            int value2 = AntennaFrame.this.auxBars[2].getValue();
            if (value2 == 0) {
                value2 = 1;
            }
            if (value == 0) {
                value = 1;
            }
            if (value * value2 > AntennaFrame.this.wallWidth) {
                value = AntennaFrame.this.wallWidth / value2;
            }
            this.spacing = value;
            final double binom = this.binom(value2, value2 / 2);
            for (int i = 0; i != value2; ++i) {
                final int arrayStart = AntennaFrame.this.wallWidth / 2 - (value2 * value - value + 1) / 2 + i * value;
                if (i == 0) {
                    AntennaFrame.this.arrayStart = arrayStart;
                }
                final double n2 = this.binom(value2, i) / binom;
                AntennaFrame.this.apertureR[arrayStart] = Math.cos(n * i) * n2;
                AntennaFrame.this.apertureI[arrayStart] = Math.sin(n * i) * n2;
            }
            AntennaFrame.this.arrayCount = value2;
            AntennaFrame.this.arraySep = value;
        }
        
        double binom(final int n, final int n2) {
            double n3 = 1.0;
            for (int i = 1; i <= n2; ++i) {
                n3 *= (n - i) / i;
            }
            return n3;
        }
        
        String getInfo(final NumberFormat numberFormat) {
            return "Separation/wavelength = " + numberFormat.format(this.spacing / AntennaFrame.this.wavelength);
        }
        
        int getAuxBarCount() {
            return 3;
        }
        
        Setup createNext() {
            return new SchelkunoffSetup();
        }
    }
    
    class SchelkunoffSetup extends Setup
    {
        String getName() {
            return "Schelkunoff Polynomial Array";
        }
        
        void select() {
            AntennaFrame.this.auxLabels[0].setText("Separation");
            AntennaFrame.this.auxLabels[1].setText("Zero 1");
            AntennaFrame.this.auxLabels[2].setText("Zero 2");
            AntennaFrame.this.auxBars[0].setValue(3);
            AntennaFrame.this.auxBars[1].setValue(25);
            AntennaFrame.this.auxBars[2].setValue(75);
            AntennaFrame.this.brightnessBar.setValue(723);
        }
        
        void doAperture() {
            final int value = AntennaFrame.this.auxBars[0].getValue();
            final double n = 6.283185307179586 * value / AntennaFrame.this.wavelength;
            final double n2 = (1.0 - AntennaFrame.this.auxBars[1].getValue() / 50.0) * n;
            final double n3 = (1.0 - AntennaFrame.this.auxBars[2].getValue() / 50.0) * n;
            final double cos = Math.cos(n2);
            final double sin = Math.sin(n2);
            final double cos2 = Math.cos(n3);
            final double sin2 = Math.sin(n3);
            AntennaFrame.this.apertureR[AntennaFrame.this.wallWidth / 2 - value] = 1.0;
            AntennaFrame.this.apertureR[AntennaFrame.this.wallWidth / 2] = -2.0 * (cos + cos2);
            AntennaFrame.this.apertureI[AntennaFrame.this.wallWidth / 2] = -2.0 * (sin + sin2);
            AntennaFrame.this.apertureR[AntennaFrame.this.wallWidth / 2 + value] = cos * cos2 - sin * sin2;
            AntennaFrame.this.apertureI[AntennaFrame.this.wallWidth / 2 + value] = cos * sin2 + cos2 * sin;
            AntennaFrame.this.arrayCount = 3;
            AntennaFrame.this.arraySep = value;
            AntennaFrame.this.arrayStart = AntennaFrame.this.wallWidth / 2 - value;
        }
        
        int getAuxBarCount() {
            return 3;
        }
        
        Setup createNext() {
            return new FourierSectoralSetup();
        }
    }
    
    class FourierSectoralSetup extends Setup
    {
        String getName() {
            return "Sectoral (Fourier)";
        }
        
        void select() {
            AntennaFrame.this.auxLabels[0].setText("Size");
            AntennaFrame.this.auxLabels[1].setText("Angle");
            AntennaFrame.this.auxLabels[2].setText("Beam Width");
            AntennaFrame.this.auxBars[0].setValue(40);
            AntennaFrame.this.auxBars[1].setValue(35);
            AntennaFrame.this.auxBars[2].setValue(65);
            AntennaFrame.this.zoomBar.setValue(50);
            AntennaFrame.this.brightnessBar.setValue(750);
        }
        
        void doAperture() {
            int value = AntennaFrame.this.auxBars[0].getValue();
            final double n = (AntennaFrame.this.auxBars[1].getValue() / 50.0 - 1.0) * 3.141592653589793;
            final double n2 = AntennaFrame.this.auxBars[2].getValue() / 300.0 * 3.141592653589793 + 0.5235987755982988;
            double n3 = n - n2;
            double n4 = n + n2;
            if (n4 > 3.141592653589793) {
                n4 = 3.141592653589793;
            }
            if (n3 < -3.141592653589793) {
                n3 = -3.141592653589793;
            }
            for (int i = 0; i <= 180; ++i) {
                final double n5 = (i - 90) * 3.141592653589793 / 90.0;
                AntennaFrame.this.fourierFunc[i] = ((n5 >= n3 && n5 <= n4) ? 1.0 : 0.0);
            }
            int arraySep = (int)(AntennaFrame.this.wavelength / 2.0);
            if (arraySep == 0) {
                arraySep = 1;
            }
            if (value * arraySep > AntennaFrame.this.wallWidth / 2) {
                value = AntennaFrame.this.wallWidth / 2 / arraySep;
            }
            for (int j = -value; j <= value; ++j) {
                final int n6 = AntennaFrame.this.wallWidth / 2 + j * arraySep;
                if (n6 >= 0) {
                    if (n6 < AntennaFrame.this.wallWidth) {
                        double n7 = -(Math.sin(-j * n4) - Math.sin(-j * n3)) / j;
                        double n8 = (Math.cos(j * n4) - Math.cos(j * n3)) / j;
                        if (j == 0) {
                            n7 = n4 - n3;
                            n8 = 0.0;
                        }
                        AntennaFrame.this.apertureR[n6] = n7;
                        AntennaFrame.this.apertureI[n6] = n8;
                    }
                }
            }
            AntennaFrame.this.arrayCount = value * 2 + 1;
            AntennaFrame.this.arraySep = arraySep;
            AntennaFrame.this.arrayStart = AntennaFrame.this.wallWidth / 2 - value * arraySep;
        }
        
        int getAuxBarCount() {
            return 3;
        }
        
        Setup createNext() {
            return new FourierFunctionSetup();
        }
    }
    
    class FourierFunctionSetup extends Setup
    {
        String getName() {
            return "Fourier Function";
        }
        
        void select() {
            AntennaFrame.this.auxLabels[0].setText("Size");
            AntennaFrame.this.auxBars[0].setValue(40);
            for (int i = 0; i <= 180; ++i) {
                AntennaFrame.this.fourierFunc[i] = ((i > 90) ? 0.0 : (i / 90.0));
            }
            AntennaFrame.this.zoomBar.setValue(50);
            AntennaFrame.this.brightnessBar.setValue(750);
        }
        
        void doAperture() {
            int value = AntennaFrame.this.auxBars[0].getValue();
            int arraySep = (int)(AntennaFrame.this.wavelength / 2.0);
            if (arraySep == 0) {
                arraySep = 1;
            }
            if (value * arraySep > AntennaFrame.this.wallWidth / 2) {
                value = AntennaFrame.this.wallWidth / 2 / arraySep;
            }
            for (int i = -value; i <= value; ++i) {
                final int n = AntennaFrame.this.wallWidth / 2 + i * arraySep;
                if (n >= 0) {
                    if (n < AntennaFrame.this.wallWidth) {
                        double n2 = 0.0;
                        double n3 = 0.0;
                        for (int j = 0; j <= 180; ++j) {
                            final double n4 = (j - 90) * 3.141592653589793 / 90.0 * i;
                            final double cos = Math.cos(n4);
                            final double sin = Math.sin(-n4);
                            n2 += cos * AntennaFrame.this.fourierFunc[j];
                            n3 += sin * AntennaFrame.this.fourierFunc[j];
                        }
                        AntennaFrame.this.apertureR[n] = n2;
                        AntennaFrame.this.apertureI[n] = n3;
                    }
                }
            }
            AntennaFrame.this.arrayCount = value * 2 + 1;
            AntennaFrame.this.arraySep = arraySep;
            AntennaFrame.this.arrayStart = AntennaFrame.this.wallWidth / 2 - value * arraySep;
        }
        
        int getAuxBarCount() {
            return 1;
        }
        
        Setup createNext() {
            return null;
        }
    }
}
