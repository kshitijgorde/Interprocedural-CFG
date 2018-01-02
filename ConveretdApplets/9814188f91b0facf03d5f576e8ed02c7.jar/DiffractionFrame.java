import java.awt.Event;
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
import java.awt.Color;
import java.awt.Label;
import java.awt.Component;
import java.awt.LayoutManager;
import java.util.Vector;
import java.awt.image.MemoryImageSource;
import java.awt.Scrollbar;
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

class DiffractionFrame extends Frame implements ComponentListener, ActionListener, AdjustmentListener, MouseMotionListener, MouseListener, ItemListener
{
    Thread engine;
    Dimension winSize;
    Dimension fullWinSize;
    Image dbimage;
    Random random;
    int gridSizeX;
    int gridSizeY;
    Button defaultsButton;
    Checkbox colorCheck;
    Checkbox reversedCheck;
    Checkbox memoryImageSourceCheck;
    Checkbox sizeCheck;
    Choice apertureChooser;
    Scrollbar gridBar;
    Scrollbar lengthBar;
    Scrollbar zoomBar;
    Scrollbar brightnessBar;
    double colorMult;
    double zbase;
    static final double pi = 3.141592653589793;
    static final double pi2 = 6.283185307179586;
    double[][][] func;
    boolean functionChanged;
    boolean dragging;
    MemoryImageSource imageSource;
    int[] pixels;
    DiffractionCanvas cv;
    Vector apertureList;
    Aperture aperture;
    Diffraction applet;
    int angleSteps;
    int angleStepsMask;
    double angleStep;
    double[] angcos1;
    double[] angsin1;
    int[] angdirx1;
    int[] angdiry1;
    long[] angcos2;
    long[] angsin2;
    int angleSteps2;
    int angleSteps2Mask;
    static final int fixedPoint = 256;
    int[] accumR;
    int[] accumI;
    double apertureArgMult;
    double apertureArgMultRed;
    double apertureArgMultBlue;
    double[] colorLenMults;
    boolean reversed;
    boolean color;
    int selection;
    double zoomFactor;
    int oldZoom;
    static final double[] sn;
    static final double[] sd;
    static final double[] cn;
    static final double[] cd;
    static final double[] fn;
    static final double[] fd;
    static final double[] gn;
    static final double[] gd;
    static final double PI = 3.141592653589793;
    static final double PIBYTWO = 1.5707963267948966;
    
    public String getAppletInfo() {
        return "Diffraction by Paul Falstad";
    }
    
    int getrand(final int n) {
        int nextInt = this.random.nextInt();
        if (nextInt < 0) {
            nextInt = -nextInt;
        }
        return nextInt % n;
    }
    
    DiffractionFrame(final Diffraction applet) {
        super("Diffraction Applet");
        this.engine = null;
        this.gridSizeX = 200;
        this.gridSizeY = 200;
        this.dragging = false;
        this.selection = -1;
        this.oldZoom = 200;
        this.applet = applet;
    }
    
    public void init() {
        this.apertureList = new Vector();
        for (Aperture next = new CircularAperture(); next != null; next = next.createNext()) {
            this.apertureList.addElement(next);
        }
        this.setLayout(new DiffractionLayout());
        (this.cv = new DiffractionCanvas(this)).addComponentListener(this);
        this.cv.addMouseMotionListener(this);
        this.cv.addMouseListener(this);
        this.add(this.cv);
        this.add(this.defaultsButton = new Button("Set to Defaults"));
        this.defaultsButton.addActionListener(this);
        (this.colorCheck = new Checkbox("Tri-chromatic")).addItemListener(this);
        this.add(this.colorCheck);
        (this.reversedCheck = new Checkbox("Reversed")).addItemListener(this);
        this.add(this.reversedCheck);
        (this.sizeCheck = new Checkbox("Show Dimensions")).addItemListener(this);
        this.add(this.sizeCheck);
        final String property = System.getProperty("os.name");
        final String property2 = System.getProperty("java.version");
        boolean b = false;
        if (property.indexOf("Windows") == 0 && property2.indexOf("1.1") == 0) {
            b = true;
        }
        (this.memoryImageSourceCheck = new Checkbox("Alternate Rendering", b)).addItemListener(this);
        this.add(this.memoryImageSourceCheck);
        this.apertureChooser = new Choice();
        for (int i = 0; i != this.apertureList.size(); ++i) {
            this.apertureChooser.add("Aperture: " + ((Aperture)this.apertureList.elementAt(i)).getName());
        }
        this.add(this.apertureChooser);
        this.aperture = this.apertureList.elementAt(0);
        this.apertureChooser.addItemListener(this);
        this.add(new Label("Aperture Scale", 1));
        this.add(this.lengthBar = new Scrollbar(0, 260, 1, 35, 500));
        this.lengthBar.addAdjustmentListener(this);
        this.add(new Label("Zoom", 1));
        this.add(this.zoomBar = new Scrollbar(0, 200, 1, 30, 400));
        this.zoomBar.addAdjustmentListener(this);
        this.add(new Label("Brightness", 1));
        this.add(this.brightnessBar = new Scrollbar(0, 50, 1, 1, 500));
        this.brightnessBar.addAdjustmentListener(this);
        this.add(new Label("Image Resolution", 1));
        this.add(this.gridBar = new Scrollbar(0, 90, 2, 10, 300));
        this.gridBar.addAdjustmentListener(this);
        this.add(new Label("http://www.falstad.com", 1));
        this.random = new Random();
        this.functionChanged = true;
        this.reinit();
        this.cv.setBackground(Color.black);
        this.cv.setForeground(Color.white);
        this.zbase = 1.0 / Math.exp(4.0);
        this.resize(550, 450);
        this.handleResize();
        this.show();
    }
    
    void reinit() {
        this.handleResize();
    }
    
    void handleResize() {
        this.winSize = this.cv.getSize();
        if (this.winSize.width == 0) {
            return;
        }
        final Dimension size = this.cv.getSize();
        this.fullWinSize = size;
        final Dimension dimension = size;
        final int n = (this.winSize.width > this.winSize.height) ? this.winSize.height : this.winSize.width;
        final Dimension winSize = this.winSize;
        final Dimension winSize2 = this.winSize;
        final int n2 = n;
        winSize2.height = n2;
        winSize.width = n2;
        this.dbimage = this.createImage(dimension.width, dimension.height);
        final Dimension winSize3 = this.winSize;
        this.pixels = new int[winSize3.width * winSize3.height];
        this.imageSource = new MemoryImageSource(winSize3.width, winSize3.height, this.pixels, 0, winSize3.width);
    }
    
    void computeFunction() {
        this.accumR = new int[3];
        this.accumI = new int[3];
        this.aperture = this.apertureList.elementAt(this.apertureChooser.getSelectedIndex());
        final int n = this.gridBar.getValue() & 0xFFFFFFFE;
        this.gridSizeY = n;
        this.gridSizeX = n;
        if (this.aperture.oneDimensional()) {
            this.gridSizeX *= 2;
            this.gridSizeY = 1;
        }
        this.func = new double[this.gridSizeX][this.gridSizeY][3];
        this.color = this.colorCheck.getState();
        this.angleSteps = ((this.gridBar.getValue() >= 195) ? 1024 : 256);
        if (this.aperture.oneDimensional()) {
            this.angleSteps = ((this.gridBar.getValue() >= 195) ? 2048 : 1024);
        }
        this.angleStepsMask = this.angleSteps - 1;
        this.angleStep = 6.283185307179586 / this.angleSteps;
        this.zoomFactor = Math.exp(this.zoomBar.getValue() / 50.0) * this.zbase;
        final double n2 = Math.exp(this.lengthBar.getValue() / 110.0) / this.zoomFactor;
        this.angcos1 = new double[this.angleSteps];
        this.angsin1 = new double[this.angleSteps];
        this.angdirx1 = new int[this.angleSteps];
        this.angdiry1 = new int[this.angleSteps];
        for (int i = 0; i != this.angleSteps; ++i) {
            this.angcos1[i] = Math.cos(i * 6.283185307179586 / this.angleSteps);
            this.angsin1[i] = Math.sin(i * 6.283185307179586 / this.angleSteps);
            this.angdirx1[i] = ((this.angcos1[i] < 0.0) ? -1 : 1);
            this.angdiry1[i] = ((this.angsin1[i] < 0.0) ? -1 : 1);
        }
        this.angleSteps2 = 4096;
        this.angleSteps2Mask = this.angleSteps2 - 1;
        this.angcos2 = new long[this.angleSteps2];
        this.angsin2 = new long[this.angleSteps2];
        this.reversed = this.reversedCheck.getState();
        final float n3 = this.reversed ? -1.0f : 1.0f;
        this.colorLenMults = new double[3];
        for (int j = 0; j != this.angleSteps2; ++j) {
            this.angcos2[j] = (long)(Math.cos(j * 6.283185307179586 / this.angleSteps2) * 256.0 * n3);
            this.angsin2[j] = (long)(Math.sin(j * 6.283185307179586 / this.angleSteps2) * 256.0 * n3);
        }
        this.colorLenMults[0] = n2 / 1.2745098039215685;
        this.colorLenMults[1] = n2;
        this.colorLenMults[2] = n2 / 0.9313725490196079;
        this.apertureArgMult = this.angleSteps2 * 0.25;
        this.apertureArgMult *= n2 * n2;
        this.apertureArgMultRed = this.apertureArgMult / 1.6243752402921954;
        this.apertureArgMultBlue = this.apertureArgMult / 0.8674548250672818;
        this.aperture.compute();
        final int n4 = this.aperture.hasXSymmetry() ? (this.gridSizeX / 2) : this.gridSizeX;
        final int n5 = this.aperture.hasYSymmetry() ? (this.gridSizeY / 2) : this.gridSizeY;
        final int n6 = this.color ? 0 : 1;
        final int n7 = this.color ? 2 : 1;
        if (this.aperture.hasDiagonalSymmetry()) {
            for (int k = n6; k <= n7; ++k) {
                for (int l = 0; l != n4; ++l) {
                    for (int n8 = 0; n8 <= l; ++n8) {
                        this.func[n8][l][k] = this.func[l][n8][k];
                    }
                }
            }
        }
        if (this.aperture.hasXSymmetry()) {
            for (int n9 = n6; n9 <= n7; ++n9) {
                for (int n10 = 0; n10 != n4; ++n10) {
                    for (int n11 = 0; n11 != n5; ++n11) {
                        this.func[this.gridSizeX - 1 - n10][n11][n9] = this.func[n10][n11][n9];
                    }
                }
            }
        }
        if (this.aperture.hasYSymmetry()) {
            for (int n12 = n6; n12 <= n7; ++n12) {
                for (int n13 = 0; n13 != this.gridSizeX; ++n13) {
                    for (int n14 = 0; n14 != n5; ++n14) {
                        this.func[n13][this.gridSizeX - 1 - n14][n12] = this.func[n13][n14][n12];
                    }
                }
            }
        }
        this.functionChanged = false;
    }
    
    void setFunction(final int n, final int n2) {
        int n3 = 1;
        int n4 = 1;
        if (this.color) {
            n3 = 0;
            n4 = 2;
        }
        for (int i = n3; i <= n4; ++i) {
            final double n5 = this.accumR[i] / (this.angleSteps * 256);
            final double n6 = this.accumI[i] / (this.angleSteps * 256);
            this.func[n][n2][i] = n5 * n5 + n6 * n6;
        }
    }
    
    void clearAccum() {
        for (int i = 0; i != 3; ++i) {
            this.accumR[i] = (this.accumI[i] = 0);
        }
    }
    
    void apertureStart(final double n) {
        final double n2 = n * n;
        final int n3 = (int)(n2 * this.apertureArgMult) & this.angleSteps2Mask;
        final int[] accumR = this.accumR;
        final int n4 = 1;
        accumR[n4] -= (int)this.angcos2[n3];
        final int[] accumI = this.accumI;
        final int n5 = 1;
        accumI[n5] -= (int)this.angsin2[n3];
        if (this.color) {
            final int n6 = (int)(n2 * this.apertureArgMultRed) & this.angleSteps2Mask;
            final int[] accumR2 = this.accumR;
            final int n7 = 0;
            accumR2[n7] -= (int)this.angcos2[n6];
            final int[] accumI2 = this.accumI;
            final int n8 = 0;
            accumI2[n8] -= (int)this.angsin2[n6];
            final int n9 = (int)(n2 * this.apertureArgMultBlue) & this.angleSteps2Mask;
            final int[] accumR3 = this.accumR;
            final int n10 = 2;
            accumR3[n10] -= (int)this.angcos2[n9];
            final int[] accumI3 = this.accumI;
            final int n11 = 2;
            accumI3[n11] -= (int)this.angsin2[n9];
        }
    }
    
    void apertureStop(final double n) {
        final double n2 = n * n;
        final int n3 = (int)(n2 * this.apertureArgMult) & this.angleSteps2Mask;
        final int[] accumR = this.accumR;
        final int n4 = 1;
        accumR[n4] += (int)this.angcos2[n3];
        final int[] accumI = this.accumI;
        final int n5 = 1;
        accumI[n5] += (int)this.angsin2[n3];
        if (this.color) {
            final int n6 = (int)(n2 * this.apertureArgMultRed) & this.angleSteps2Mask;
            final int[] accumR2 = this.accumR;
            final int n7 = 0;
            accumR2[n7] += (int)this.angcos2[n6];
            final int[] accumI2 = this.accumI;
            final int n8 = 0;
            accumI2[n8] += (int)this.angsin2[n6];
            final int n9 = (int)(n2 * this.apertureArgMultBlue) & this.angleSteps2Mask;
            final int[] accumR3 = this.accumR;
            final int n10 = 2;
            accumR3[n10] += (int)this.angcos2[n9];
            final int[] accumI3 = this.accumI;
            final int n11 = 2;
            accumI3[n11] += (int)this.angsin2[n9];
        }
    }
    
    void apertureStartOrigin(boolean b) {
        if (this.reversed) {
            b = !b;
        }
        if (b) {
            final int[] accumR = this.accumR;
            final int n = 1;
            accumR[n] -= 256 * this.angleSteps;
            if (this.color) {
                final int[] accumR2 = this.accumR;
                final int n2 = 0;
                accumR2[n2] -= 256 * this.angleSteps;
                final int[] accumR3 = this.accumR;
                final int n3 = 2;
                accumR3[n3] -= 256 * this.angleSteps;
            }
        }
    }
    
    int sign(final double n) {
        return (n < 0.0) ? -1 : 1;
    }
    
    public void paint(final Graphics graphics) {
        this.cv.repaint();
    }
    
    public void updateDiffraction(final Graphics graphics) {
        final boolean b = this.dragging && this.aperture.hideWhileDragging();
        if (this.functionChanged) {
            graphics.setColor(this.cv.getBackground());
            final FontMetrics fontMetrics = graphics.getFontMetrics();
            final String s = "Calculating...";
            graphics.fillRect(0, this.fullWinSize.height - 30, 20 + fontMetrics.stringWidth(s), 30);
            graphics.setColor(Color.white);
            graphics.drawString(s, 10, this.fullWinSize.height - 10);
            this.computeFunction();
        }
        if (this.winSize == null || this.winSize.width == 0) {
            return;
        }
        final boolean state = this.memoryImageSourceCheck.getState();
        final Graphics graphics2 = this.dbimage.getGraphics();
        graphics2.setColor(this.cv.getBackground());
        graphics2.fillRect(0, 0, this.fullWinSize.width, this.fullWinSize.height);
        graphics2.setColor(this.cv.getForeground());
        this.colorMult = 70.0 * Math.exp(this.brightnessBar.getValue() / 50.0);
        if (!b) {
            for (int i = 0; i != this.gridSizeX; ++i) {
                for (int j = 0; j != this.gridSizeY; ++j) {
                    final int n = i * this.winSize.width / this.gridSizeX;
                    final int n2 = j * this.winSize.height / this.gridSizeY;
                    final int n3 = (i + 1) * this.winSize.width / this.gridSizeX;
                    final int n4 = (j + 1) * this.winSize.height / this.gridSizeY;
                    int n5;
                    if (!this.color) {
                        n5 = (0xFF000000 | this.getColorValue(i, j, 1) * 65793);
                    }
                    else {
                        n5 = (-16777216 + (this.getColorValue(i, j, 0) << 16) | this.getColorValue(i, j, 1) << 8 | this.getColorValue(i, j, 2));
                    }
                    if (state) {
                        for (int k = n; k < n3; ++k) {
                            for (int l = n2; l < n4; ++l) {
                                this.pixels[k + l * this.winSize.width] = n5;
                            }
                        }
                    }
                    else {
                        graphics2.setColor(new Color(n5));
                        graphics2.fillRect(n, n2, n3 - n, n4 - n2);
                    }
                }
            }
        }
        if (state && !b) {
            graphics2.drawImage(this.cv.createImage(this.imageSource), 0, 0, null);
        }
        graphics2.setColor(Color.red);
        this.aperture.drawGeometricShadow(graphics2);
        if (this.sizeCheck.getState()) {
            graphics2.setColor(this.cv.getBackground());
            final FontMetrics fontMetrics2 = graphics.getFontMetrics();
            final double n6 = 5.1E-7;
            final NumberFormat instance = NumberFormat.getInstance();
            instance.setMaximumFractionDigits(2);
            final double n7 = this.aperture.getDimension() * (Math.exp(this.lengthBar.getValue() / 110.0) / this.zoomFactor) * Math.sqrt(n6 * 2.0);
            final String s2 = "width = ";
            String s3;
            if (n7 > 0.001) {
                s3 = s2 + instance.format(n7 * 1000.0) + " mm";
            }
            else if (n7 > 1.0E-6) {
                s3 = s2 + instance.format(n7 * 1000000.0) + " µm";
            }
            else {
                s3 = s2 + instance.format(n7 * 1.0E9) + " nm";
            }
            final int stringWidth = fontMetrics2.stringWidth(s3);
            if (n7 > 0.0) {
                graphics2.fillRect(this.fullWinSize.width - (20 + stringWidth), this.fullWinSize.height - 30, 20 + stringWidth, 30);
                graphics2.setColor(Color.white);
                graphics2.drawString(s3, this.fullWinSize.width - (10 + stringWidth), this.fullWinSize.height - 10);
            }
        }
        graphics.drawImage(this.dbimage, 0, 0, this);
    }
    
    int getColorValue(final int n, final int n2, final int n3) {
        int n4 = (int)(this.func[n][n2][n3] * this.colorMult);
        if (n4 > 255) {
            n4 = 255;
        }
        return n4;
    }
    
    void doZoom() {
        final double n = Math.exp(this.zoomBar.getValue() / 50.0) * this.zbase / (Math.exp(this.oldZoom / 50.0) * this.zbase);
        this.oldZoom = this.zoomBar.getValue();
        this.aperture.rezoom(n);
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
        if (actionEvent.getSource() == this.defaultsButton) {
            this.colorCheck.setState(false);
            this.reversedCheck.setState(false);
            this.lengthBar.setValue(260);
            this.gridBar.setValue(90);
            this.zoomBar.setValue(this.oldZoom = 200);
            this.functionChanged = true;
            this.brightnessBar.setValue(this.aperture.defaultBrightness());
            this.aperture.setToDefaults();
            this.cv.repaint();
        }
    }
    
    public void adjustmentValueChanged(final AdjustmentEvent adjustmentEvent) {
        System.out.print(((Scrollbar)adjustmentEvent.getSource()).getValue() + "\n");
        if (adjustmentEvent.getSource() != this.brightnessBar) {
            this.functionChanged = true;
        }
        if (adjustmentEvent.getSource() == this.zoomBar) {
            this.doZoom();
        }
        this.cv.repaint(100L);
    }
    
    public void mouseDragged(final MouseEvent mouseEvent) {
        if (this.selection != -1) {
            this.dragging = true;
            if (this.aperture.drag(mouseEvent.getX(), mouseEvent.getY())) {
                this.cv.repaint();
            }
        }
    }
    
    public void mouseMoved(final MouseEvent mouseEvent) {
        if ((mouseEvent.getModifiers() & 0x10) != 0x0) {
            if (this.selection != -1) {
                this.dragging = true;
                if (this.aperture.drag(mouseEvent.getX(), mouseEvent.getY())) {
                    this.cv.repaint();
                }
            }
            return;
        }
        final int selection = this.selection;
        this.selection = this.aperture.getSelection(mouseEvent.getX(), mouseEvent.getY());
        if (this.selection != selection) {
            this.cv.repaint();
        }
    }
    
    public void mouseClicked(final MouseEvent mouseEvent) {
    }
    
    public void mouseEntered(final MouseEvent mouseEvent) {
    }
    
    public void mouseExited(final MouseEvent mouseEvent) {
        if (!this.dragging && this.selection != -1) {
            this.selection = -1;
            this.cv.repaint();
        }
    }
    
    public void mousePressed(final MouseEvent mouseEvent) {
        if ((mouseEvent.getModifiers() & 0x10) == 0x0) {
            return;
        }
        if (this.selection != -1) {
            this.dragging = true;
            if (this.aperture.drag(mouseEvent.getX(), mouseEvent.getY())) {
                this.cv.repaint();
            }
        }
    }
    
    public void mouseReleased(final MouseEvent mouseEvent) {
        if (this.dragging) {
            this.functionChanged = true;
            this.cv.repaint();
        }
        this.dragging = false;
    }
    
    public void itemStateChanged(final ItemEvent itemEvent) {
        if (itemEvent.getSource() != this.memoryImageSourceCheck && itemEvent.getSource() != this.sizeCheck) {
            this.functionChanged = true;
        }
        if (itemEvent.getSource() == this.apertureChooser) {
            this.aperture = this.apertureList.elementAt(this.apertureChooser.getSelectedIndex());
            this.brightnessBar.setValue(this.aperture.defaultBrightness());
            this.zoomBar.setValue(this.oldZoom = 200);
        }
        this.cv.repaint(100L);
    }
    
    public boolean handleEvent(final Event event) {
        if (event.id == 201) {
            this.applet.destroyFrame();
            return true;
        }
        return super.handleEvent(event);
    }
    
    int fresnl(final double n, final double[] array) {
        final double abs = Math.abs(n);
        final double n2 = abs * abs;
        double n4;
        double n5;
        if (n2 < 2.5625) {
            final double n3 = n2 * n2;
            n4 = abs * n2 * this.polevl(n3, DiffractionFrame.sn, 5) / this.p1evl(n3, DiffractionFrame.sd, 6);
            n5 = abs * this.polevl(n3, DiffractionFrame.cn, 5) / this.polevl(n3, DiffractionFrame.cd, 6);
        }
        else if (abs > 36974.0) {
            n5 = 0.5;
            n4 = 0.5;
        }
        else {
            final double n6 = abs * abs;
            final double n7 = 3.141592653589793 * n6;
            final double n8 = 1.0 / (n7 * n7);
            final double n9 = 1.0 / n7;
            final double n10 = 1.0 - n8 * this.polevl(n8, DiffractionFrame.fn, 9) / this.p1evl(n8, DiffractionFrame.fd, 10);
            final double n11 = n9 * this.polevl(n8, DiffractionFrame.gn, 10) / this.p1evl(n8, DiffractionFrame.gd, 11);
            final double n12 = 1.5707963267948966 * n6;
            final double cos = Math.cos(n12);
            final double sin = Math.sin(n12);
            final double n13 = 3.141592653589793 * abs;
            n5 = 0.5 + (n10 * sin - n11 * cos) / n13;
            n4 = 0.5 - (n10 * cos + n11 * sin) / n13;
        }
        if (n < 0.0) {
            n5 = -n5;
            n4 = -n4;
        }
        array[0] = n5;
        array[1] = n4;
        return 0;
    }
    
    double polevl(final double n, final double[] array, int n2) {
        int n3 = 0;
        double n4 = array[n3++];
        do {
            n4 = n4 * n + array[n3++];
        } while (--n2 > 0);
        return n4;
    }
    
    double p1evl(final double n, final double[] array, final int n2) {
        int n3 = 0;
        double n4 = n + array[n3++];
        int n5 = n2 - 1;
        do {
            n4 = n4 * n + array[n3++];
        } while (--n5 > 0);
        return n4;
    }
    
    static {
        sn = new double[] { -2991.8191940101983, 708840.0452577386, -6.297414862058625E7, 2.5489088057337637E9, -4.429795180596978E10, 3.180162978765678E11 };
        sd = new double[] { 281.3762688899943, 45584.78108065326, 5173438.887700964, 4.193202458981112E8, 2.2441179564534092E10, 6.073663894900846E11 };
        cn = new double[] { -4.9884311457357354E-8, 9.504280628298596E-6, -6.451914356839651E-4, 0.018884331939670384, -0.20552590095501388, 1.0 };
        cd = new double[] { 3.99982968972496E-12, 9.154392157746574E-10, 1.2500186247959882E-7, 1.2226278902417902E-5, 8.680295429417843E-4, 0.04121420907221998, 1.0 };
        fn = new double[] { 0.4215435550436775, 0.1434079197807589, 0.011522095507358577, 3.45017939782574E-4, 4.6361374928786735E-6, 3.055689837902576E-8, 1.0230451416490724E-10, 1.7201074326816183E-13, 1.3428327623306275E-16, 3.763297112699879E-20 };
        fd = new double[] { 0.7515863983533789, 0.11688892585919138, 0.0064405152650885865, 1.5593440916415301E-4, 1.8462756734893055E-6, 1.1269922476399903E-8, 3.6014002958937136E-11, 5.887545336215784E-14, 4.5200143407412973E-17, 1.2544323709001127E-20 };
        gn = new double[] { 0.5044420736433832, 0.1971028335255234, 0.018764858409257526, 6.840793809153931E-4, 1.1513882611188428E-5, 9.828524436884223E-8, 4.4534441586175015E-10, 1.0826804113902088E-12, 1.375554606332618E-15, 8.363544356306774E-19, 1.8695871016278324E-22 };
        gd = new double[] { 1.4749575992512833, 0.33774898912002, 0.02536037414203388, 8.146791071843061E-4, 1.2754507566772912E-5, 1.0431458965757199E-7, 4.6068072814652043E-10, 1.1027321506624028E-12, 1.3879653125957886E-15, 8.391588162831187E-19, 1.8695871016278324E-22 };
    }
    
    abstract class Aperture
    {
        abstract String getName();
        
        abstract void compute();
        
        abstract Aperture createNext();
        
        abstract void drawGeometricShadow(final Graphics p0);
        
        abstract int getSelection(final int p0, final int p1);
        
        abstract boolean drag(final int p0, final int p1);
        
        abstract void setToDefaults();
        
        int defaultBrightness() {
            return 50;
        }
        
        boolean oneDimensional() {
            return false;
        }
        
        boolean hasXSymmetry() {
            return false;
        }
        
        boolean hasYSymmetry() {
            return false;
        }
        
        boolean hasDiagonalSymmetry() {
            return false;
        }
        
        boolean hideWhileDragging() {
            return true;
        }
        
        void rezoom(final double n) {
        }
        
        abstract double getDimension();
        
        Aperture() {
            this.setToDefaults();
        }
    }
    
    class CircularAperture extends Aperture
    {
        double radius;
        
        String getName() {
            return "circle";
        }
        
        Aperture createNext() {
            return new HalfPlaneAperture();
        }
        
        boolean hasXSymmetry() {
            return true;
        }
        
        boolean hasYSymmetry() {
            return true;
        }
        
        boolean hasDiagonalSymmetry() {
            return true;
        }
        
        void setToDefaults() {
            this.radius = 0.25;
        }
        
        void rezoom(final double n) {
            this.radius *= n;
        }
        
        void compute() {
            for (int i = 0; i != DiffractionFrame.this.gridSizeX / 2; ++i) {
                for (int j = 0; j <= i; ++j) {
                    DiffractionFrame.this.clearAccum();
                    final double n = i / DiffractionFrame.this.gridSizeX - 0.5;
                    final double n2 = j / DiffractionFrame.this.gridSizeY - 0.5;
                    final double n3 = -n;
                    final double n4 = -n2;
                    final double n5 = n3 * n3 + n4 * n4;
                    final double radius = this.radius;
                    final double n6 = n5 - radius * radius;
                    final double n7 = n6 * 4.0;
                    double n8 = 0.0;
                    double n9 = 6.283185307179586;
                    if (n6 > 0.0) {
                        final double sqrt = Math.sqrt(n5);
                        final double n10 = n3 / sqrt;
                        final double n11 = n4 / sqrt;
                        final double atan2 = Math.atan2(n4 - n10 * radius, n3 + n11 * radius);
                        final double atan3 = Math.atan2(n4 + n10 * radius, n3 - n11 * radius);
                        n8 = ((atan2 < atan3) ? atan2 : atan3);
                        n9 = ((atan2 > atan3) ? atan2 : atan3);
                        if (n9 - n8 > 3.141592653589793) {
                            n8 = ((atan2 > atan3) ? atan2 : atan3);
                            n9 = ((atan2 < atan3) ? atan2 : atan3) + 6.283185307179586;
                        }
                    }
                    int k;
                    int n12;
                    for (k = (int)(n8 * DiffractionFrame.this.angleSteps / 6.283185307179586), n12 = (int)(n9 * DiffractionFrame.this.angleSteps / 6.283185307179586); k < 0; k += DiffractionFrame.this.angleSteps, n12 += DiffractionFrame.this.angleSteps) {}
                    for (int l = k; l < n12; ++l) {
                        final double n13 = -2.0 * (DiffractionFrame.this.angcos1[l & DiffractionFrame.this.angleStepsMask] * n3 + DiffractionFrame.this.angsin1[l & DiffractionFrame.this.angleStepsMask] * n4);
                        final double n14 = n13 * n13 - n7;
                        if (n14 >= 0.0) {
                            final double sqrt2 = Math.sqrt(n14);
                            final double n15 = 0.5 * (-n13 - sqrt2);
                            final double n16 = 0.5 * (-n13 + sqrt2);
                            if (n15 >= 0.0 || n16 >= 0.0) {
                                if (n15 > 0.0) {
                                    DiffractionFrame.this.apertureStart(n15);
                                }
                                DiffractionFrame.this.apertureStop(n16);
                            }
                        }
                    }
                    DiffractionFrame.this.apertureStartOrigin(n6 < 0.0);
                    DiffractionFrame.this.setFunction(i, j);
                }
            }
        }
        
        void drawGeometricShadow(final Graphics graphics) {
            if (DiffractionFrame.this.selection == 1) {
                graphics.setColor(Color.yellow);
            }
            final int n = (int)(DiffractionFrame.this.winSize.width * this.radius);
            graphics.drawOval(DiffractionFrame.this.winSize.width / 2 - n, DiffractionFrame.this.winSize.height / 2 - n, n * 2, n * 2);
        }
        
        int getSelection(final int n, final int n2) {
            final int n3 = DiffractionFrame.this.winSize.width / 2 - n;
            final int n4 = DiffractionFrame.this.winSize.height / 2 - n2;
            return (Math.abs(Math.sqrt(n3 * n3 + n4 * n4) / DiffractionFrame.this.winSize.width - this.radius) < 5.0 / DiffractionFrame.this.winSize.width) ? 1 : -1;
        }
        
        boolean drag(final int n, final int n2) {
            final int n3 = DiffractionFrame.this.winSize.width / 2 - n;
            final int n4 = DiffractionFrame.this.winSize.height / 2 - n2;
            final double radius = Math.sqrt(n3 * n3 + n4 * n4) / DiffractionFrame.this.winSize.width;
            if (radius == this.radius) {
                return false;
            }
            this.radius = radius;
            return true;
        }
        
        double getDimension() {
            return this.radius * 2.0;
        }
    }
    
    abstract class OneDimensionalAperture extends Aperture
    {
        public double[] lineLocations;
        public int lineCount;
        
        boolean oneDimensional() {
            return true;
        }
        
        void compute() {
            final double[] array = new double[2];
            int n = 1;
            int n2 = 1;
            if (DiffractionFrame.this.color) {
                n = 0;
                n2 = 2;
            }
            final int n3 = this.hasXSymmetry() ? (DiffractionFrame.this.gridSizeX / 2) : DiffractionFrame.this.gridSizeX;
            double n4 = DiffractionFrame.this.reversedCheck.getState() ? -1.0 : 0.0;
            if (this.lineCount == 1) {
                n4 += 0.5;
            }
            for (int i = 0; i != n3; ++i) {
                final double n5 = i / DiffractionFrame.this.gridSizeX - 0.5;
                for (int j = n; j <= n2; ++j) {
                    final double n6 = DiffractionFrame.this.colorLenMults[j];
                    double n7 = n4;
                    double n8 = n4;
                    int n9 = 1;
                    for (int k = 0; k != this.lineCount; ++k) {
                        DiffractionFrame.this.fresnl((n5 - this.lineLocations[k]) * n6, array);
                        n7 += n9 * array[0];
                        n8 += n9 * array[1];
                        n9 = -n9;
                    }
                    DiffractionFrame.this.func[i][0][j] = 0.5 * (n7 * n7 + n8 * n8);
                }
            }
        }
        
        double getDimension() {
            return this.lineLocations[this.lineCount - 1] - this.lineLocations[0];
        }
        
        void drawGeometricShadow(final Graphics graphics) {
            int n = -1;
            if (DiffractionFrame.this.selection != -1 && this.hasXSymmetry()) {
                n = this.lineCount - 1 - DiffractionFrame.this.selection;
            }
            for (int i = 0; i != this.lineCount; ++i) {
                final int n2 = (int)((this.lineLocations[i] + 0.5) * DiffractionFrame.this.winSize.width);
                graphics.setColor((DiffractionFrame.this.selection == i || n == i) ? Color.yellow : Color.red);
                graphics.drawLine(n2, 0, n2, DiffractionFrame.this.winSize.height - 1);
            }
        }
        
        int getSelection(final int n, final int n2) {
            final double n3 = n / DiffractionFrame.this.winSize.width - 0.5;
            double n4 = 3.0 / DiffractionFrame.this.winSize.width;
            int n5 = -1;
            for (int i = 0; i != this.lineCount; ++i) {
                final double abs = Math.abs(this.lineLocations[i] - n3);
                if (abs < n4) {
                    n5 = i;
                    n4 = abs;
                }
            }
            return n5;
        }
        
        boolean drag(final int n, final int n2) {
            final double n3 = n / DiffractionFrame.this.winSize.width - 0.5;
            if (DiffractionFrame.this.selection > 0 && n3 <= this.lineLocations[DiffractionFrame.this.selection - 1]) {
                return false;
            }
            if (DiffractionFrame.this.selection < this.lineCount - 1 && n3 >= this.lineLocations[DiffractionFrame.this.selection + 1]) {
                return false;
            }
            if (this.hasXSymmetry() && DiffractionFrame.this.sign(this.lineLocations[DiffractionFrame.this.selection]) != DiffractionFrame.this.sign(n3)) {
                return false;
            }
            this.lineLocations[DiffractionFrame.this.selection] = n3;
            if (this.hasXSymmetry()) {
                this.lineLocations[this.lineCount - 1 - DiffractionFrame.this.selection] = -n3;
            }
            return DiffractionFrame.this.functionChanged = true;
        }
        
        boolean hideWhileDragging() {
            return false;
        }
        
        void rezoom(final double n) {
            for (int i = 0; i != this.lineCount; ++i) {
                final double[] lineLocations = this.lineLocations;
                final int n2 = i;
                lineLocations[n2] *= n;
            }
        }
    }
    
    class HalfPlaneAperture extends OneDimensionalAperture
    {
        void setToDefaults() {
            final int lineCount = 1;
            super.lineCount = lineCount;
            (super.lineLocations = new double[lineCount])[0] = 0.0;
        }
        
        String getName() {
            return "half plane";
        }
        
        Aperture createNext() {
            return new SlitAperture();
        }
        
        double getDimension() {
            return 0.5 - super.lineLocations[0];
        }
    }
    
    class SlitAperture extends OneDimensionalAperture
    {
        void setToDefaults() {
            final int lineCount = 2;
            super.lineCount = lineCount;
            (super.lineLocations = new double[lineCount])[0] = -0.06;
            super.lineLocations[1] = 0.06;
        }
        
        int defaultBrightness() {
            return 200;
        }
        
        String getName() {
            return "slit";
        }
        
        Aperture createNext() {
            return new DoubleSlitAperture();
        }
        
        boolean hasXSymmetry() {
            return true;
        }
    }
    
    class DoubleSlitAperture extends OneDimensionalAperture
    {
        void setToDefaults() {
            final int lineCount = 4;
            super.lineCount = lineCount;
            (super.lineLocations = new double[lineCount])[0] = -0.17;
            super.lineLocations[1] = -0.125;
            super.lineLocations[2] = 0.125;
            super.lineLocations[3] = 0.17;
        }
        
        int defaultBrightness() {
            return 140;
        }
        
        String getName() {
            return "double slit";
        }
        
        Aperture createNext() {
            return new TripleSlitAperture();
        }
        
        boolean hasXSymmetry() {
            return true;
        }
    }
    
    class TripleSlitAperture extends OneDimensionalAperture
    {
        void setToDefaults() {
            final int lineCount = 6;
            super.lineCount = lineCount;
            (super.lineLocations = new double[lineCount])[0] = -0.1533;
            super.lineLocations[1] = -0.1133;
            super.lineLocations[2] = -0.02;
            super.lineLocations[3] = 0.02;
            super.lineLocations[4] = 0.1133;
            super.lineLocations[5] = 0.1533;
        }
        
        int defaultBrightness() {
            return 210;
        }
        
        String getName() {
            return "triple slit";
        }
        
        Aperture createNext() {
            return new SquareAperture();
        }
        
        boolean hasXSymmetry() {
            return true;
        }
    }
    
    class SquareAperture extends BlockAperture
    {
        String getName() {
            return "square";
        }
        
        Aperture createNext() {
            return new RectangularAperture();
        }
        
        boolean hasXSymmetry() {
            return true;
        }
        
        boolean hasYSymmetry() {
            return true;
        }
        
        boolean hasDiagonalSymmetry() {
            return true;
        }
        
        void setToDefaults() {
            final double n = 0.25;
            final int n2 = 5;
            super.blockCountY = n2;
            super.blockCountX = n2;
            super.blocks = new boolean[super.blockCountX][super.blockCountY];
            super.blocks[2][2] = true;
            super.lineXLocations = new double[super.blockCountX];
            super.lineYLocations = new double[super.blockCountY];
            super.lineXLocations[1] = -n;
            super.lineXLocations[3] = n;
            super.lineYLocations[1] = -n;
            super.lineYLocations[3] = n;
        }
        
        void setupRects() {
            super.rectCount = 1;
            super.rects = new double[1][5];
            final double n = super.lineXLocations[3];
            super.rects[0][0] = -n;
            super.rects[0][1] = -n;
            super.rects[0][2] = n;
            super.rects[0][3] = n;
            super.rects[0][4] = 0.5;
        }
    }
    
    class RectangularAperture extends BlockAperture
    {
        String getName() {
            return "rectangle";
        }
        
        Aperture createNext() {
            return new CornerAperture();
        }
        
        boolean hasXSymmetry() {
            return true;
        }
        
        boolean hasYSymmetry() {
            return true;
        }
        
        void setToDefaults() {
            final int n = 5;
            super.blockCountY = n;
            super.blockCountX = n;
            super.blocks = new boolean[super.blockCountX][super.blockCountY];
            super.blocks[2][2] = true;
            super.lineXLocations = new double[super.blockCountX];
            super.lineYLocations = new double[super.blockCountY];
            super.lineXLocations[1] = -0.25;
            super.lineXLocations[3] = 0.25;
            super.lineYLocations[1] = -0.4;
            super.lineYLocations[3] = 0.4;
        }
        
        void setupRects() {
            super.rectCount = 1;
            super.rects = new double[1][5];
            super.rects[0][0] = super.lineXLocations[1];
            super.rects[0][1] = super.lineYLocations[1];
            super.rects[0][2] = super.lineXLocations[3];
            super.rects[0][3] = super.lineYLocations[3];
            super.rects[0][4] = 0.5;
        }
    }
    
    class CornerAperture extends BlockAperture
    {
        String getName() {
            return "corner";
        }
        
        Aperture createNext() {
            return new CrossAperture();
        }
        
        boolean hasDiagonalSymmetry() {
            return true;
        }
        
        void setToDefaults() {
            final int n = 3;
            super.blockCountY = n;
            super.blockCountX = n;
            super.blocks = new boolean[super.blockCountX][super.blockCountY];
            super.blocks[2][2] = true;
            super.lineXLocations = new double[super.blockCountX];
            super.lineYLocations = new double[super.blockCountY];
            super.lineXLocations[1] = 0.0;
            super.lineYLocations[1] = 0.0;
        }
        
        void setupRects() {
            super.rectCount = 1;
            super.rects = new double[1][5];
            final double n = super.lineXLocations[1];
            super.rects[0][0] = n;
            super.rects[0][1] = n;
            super.rects[0][2] = 1.0E8;
            super.rects[0][3] = 1.0E8;
            super.rects[0][4] = 0.5;
        }
        
        double getDimension() {
            return 0.5 - super.lineXLocations[1];
        }
    }
    
    class CrossAperture extends BlockAperture
    {
        String getName() {
            return "cross";
        }
        
        Aperture createNext() {
            return new RectanglesAperture();
        }
        
        boolean hasXSymmetry() {
            return true;
        }
        
        boolean hasYSymmetry() {
            return true;
        }
        
        boolean hasDiagonalSymmetry() {
            return true;
        }
        
        void setToDefaults() {
            final double n = 0.0625;
            final int n2 = 5;
            super.blockCountY = n2;
            super.blockCountX = n2;
            super.blocks = new boolean[super.blockCountX][super.blockCountY];
            final boolean[] array = super.blocks[0];
            final int n3 = 2;
            final boolean[] array2 = super.blocks[2];
            final int n4 = 2;
            final boolean[] array3 = super.blocks[4];
            final int n5 = 2;
            final boolean[] array4 = super.blocks[2];
            final int n6 = 0;
            final boolean[] array5 = super.blocks[2];
            final int n7 = 4;
            final boolean b = true;
            array5[n7] = b;
            array3[n5] = (array4[n6] = b);
            array[n3] = (array2[n4] = b);
            super.lineXLocations = new double[super.blockCountX];
            super.lineYLocations = new double[super.blockCountY];
            super.lineXLocations[1] = -n;
            super.lineXLocations[3] = n;
            super.lineYLocations[1] = -n;
            super.lineYLocations[3] = n;
        }
        
        void setupRects() {
            super.rectCount = 3;
            super.rects = new double[3][5];
            final double n = super.lineXLocations[3];
            super.rects[0][0] = -n;
            super.rects[0][1] = -1.0E8;
            super.rects[0][2] = n;
            super.rects[0][3] = 1.0E8;
            super.rects[0][4] = 0.5;
            super.rects[1][0] = -1.0E8;
            super.rects[1][1] = -n;
            super.rects[1][2] = 1.0E8;
            super.rects[1][3] = n;
            super.rects[1][4] = 0.5;
            super.rects[2][0] = -n;
            super.rects[2][1] = -n;
            super.rects[2][2] = n;
            super.rects[2][3] = n;
            super.rects[2][4] = -0.5;
        }
        
        double getDimension() {
            return 1.0;
        }
    }
    
    class RectanglesAperture extends BlockAperture
    {
        String getName() {
            return "2 rectangles";
        }
        
        Aperture createNext() {
            return new FrameAperture();
        }
        
        boolean hasXSymmetry() {
            return true;
        }
        
        boolean hasYSymmetry() {
            return true;
        }
        
        void setToDefaults() {
            super.blockCountX = 9;
            super.blockCountY = 5;
            super.blocks = new boolean[super.blockCountX][super.blockCountY];
            super.blocks[2][2] = (super.blocks[6][2] = true);
            super.lineXLocations = new double[super.blockCountX];
            super.lineYLocations = new double[super.blockCountY];
            super.lineXLocations[1] = -0.375;
            super.lineXLocations[3] = -0.125;
            super.lineXLocations[5] = 0.125;
            super.lineXLocations[7] = 0.375;
            super.lineYLocations[1] = -0.25;
            super.lineYLocations[3] = 0.25;
        }
        
        void setupRects() {
            super.rectCount = 2;
            super.rects = new double[2][5];
            final double n = super.lineXLocations[1];
            super.rects[0][0] = super.lineXLocations[1];
            super.rects[0][1] = super.lineYLocations[1];
            super.rects[0][2] = super.lineXLocations[3];
            super.rects[0][3] = super.lineYLocations[3];
            super.rects[0][4] = 0.5;
            super.rects[1][0] = super.lineXLocations[5];
            super.rects[1][1] = super.lineYLocations[1];
            super.rects[1][2] = super.lineXLocations[7];
            super.rects[1][3] = super.lineYLocations[3];
            super.rects[1][4] = 0.5;
        }
    }
    
    class FrameAperture extends BlockAperture
    {
        String getName() {
            return "frame";
        }
        
        Aperture createNext() {
            return new PlusAperture();
        }
        
        boolean hasXSymmetry() {
            return true;
        }
        
        boolean hasYSymmetry() {
            return true;
        }
        
        boolean hasDiagonalSymmetry() {
            return true;
        }
        
        void setToDefaults() {
            final int n = 9;
            super.blockCountY = n;
            super.blockCountX = n;
            super.blocks = new boolean[super.blockCountX][super.blockCountY];
            for (int i = 2; i <= 6; i += 2) {
                final boolean[] array = super.blocks[i];
                final int n2 = 2;
                final boolean[] array2 = super.blocks[i];
                final int n3 = 6;
                final boolean[] array3 = super.blocks[2];
                final int n4 = i;
                final boolean[] array4 = super.blocks[6];
                final int n5 = i;
                final boolean b = true;
                array3[n4] = (array4[n5] = b);
                array[n2] = (array2[n3] = b);
            }
            super.lineXLocations = new double[super.blockCountX];
            super.lineYLocations = new double[super.blockCountY];
            super.lineXLocations[1] = -0.375;
            super.lineXLocations[3] = -0.125;
            super.lineXLocations[5] = 0.125;
            super.lineXLocations[7] = 0.375;
            super.lineYLocations[1] = -0.375;
            super.lineYLocations[3] = -0.125;
            super.lineYLocations[5] = 0.125;
            super.lineYLocations[7] = 0.375;
        }
        
        void setupRects() {
            super.rectCount = 2;
            super.rects = new double[2][5];
            super.rects[0][0] = super.lineXLocations[1];
            super.rects[0][1] = super.lineYLocations[1];
            super.rects[0][2] = super.lineXLocations[7];
            super.rects[0][3] = super.lineYLocations[7];
            super.rects[0][4] = 0.5;
            super.rects[1][0] = super.lineXLocations[3];
            super.rects[1][1] = super.lineYLocations[3];
            super.rects[1][2] = super.lineXLocations[5];
            super.rects[1][3] = super.lineYLocations[5];
            super.rects[1][4] = -0.5;
        }
    }
    
    class PlusAperture extends BlockAperture
    {
        String getName() {
            return "plus";
        }
        
        Aperture createNext() {
            return new IntersectingSquaresAperture();
        }
        
        boolean hasXSymmetry() {
            return true;
        }
        
        boolean hasYSymmetry() {
            return true;
        }
        
        boolean hasDiagonalSymmetry() {
            return true;
        }
        
        void setToDefaults() {
            final int n = 9;
            super.blockCountY = n;
            super.blockCountX = n;
            super.blocks = new boolean[super.blockCountX][super.blockCountY];
            for (int i = 2; i <= 6; i += 2) {
                super.blocks[i][4] = (super.blocks[4][i] = true);
            }
            super.lineXLocations = new double[super.blockCountX];
            super.lineYLocations = new double[super.blockCountY];
            super.lineXLocations[1] = -0.375;
            super.lineXLocations[3] = -0.125;
            super.lineXLocations[5] = 0.125;
            super.lineXLocations[7] = 0.375;
            super.lineYLocations[1] = -0.375;
            super.lineYLocations[3] = -0.125;
            super.lineYLocations[5] = 0.125;
            super.lineYLocations[7] = 0.375;
        }
        
        void setupRects() {
            super.rectCount = 3;
            super.rects = new double[3][5];
            super.rects[0][0] = super.lineXLocations[1];
            super.rects[0][1] = super.lineYLocations[3];
            super.rects[0][2] = super.lineXLocations[7];
            super.rects[0][3] = super.lineYLocations[5];
            super.rects[0][4] = 0.5;
            super.rects[1][0] = super.lineXLocations[3];
            super.rects[1][1] = super.lineYLocations[1];
            super.rects[1][2] = super.lineXLocations[5];
            super.rects[1][3] = super.lineYLocations[7];
            super.rects[1][4] = 0.5;
            super.rects[2][0] = super.lineXLocations[3];
            super.rects[2][1] = super.lineYLocations[3];
            super.rects[2][2] = super.lineXLocations[5];
            super.rects[2][3] = super.lineYLocations[5];
            super.rects[2][4] = -0.5;
        }
    }
    
    class IntersectingSquaresAperture extends BlockAperture
    {
        String getName() {
            return "2 squares";
        }
        
        Aperture createNext() {
            return new DoubleCircleAperture();
        }
        
        boolean hasDiagonalSymmetry() {
            return true;
        }
        
        void setToDefaults() {
            final int n = 9;
            super.blockCountY = n;
            super.blockCountX = n;
            super.blocks = new boolean[super.blockCountX][super.blockCountY];
            for (int i = 2; i <= 6; i += 2) {
                super.blocks[i][4] = (super.blocks[4][i] = true);
            }
            super.blocks[2][2] = (super.blocks[6][6] = true);
            super.lineXLocations = new double[super.blockCountX];
            super.lineYLocations = new double[super.blockCountY];
            super.lineXLocations[1] = -0.375;
            super.lineXLocations[3] = -0.125;
            super.lineXLocations[5] = 0.125;
            super.lineXLocations[7] = 0.375;
            super.lineYLocations[1] = -0.375;
            super.lineYLocations[3] = -0.125;
            super.lineYLocations[5] = 0.125;
            super.lineYLocations[7] = 0.375;
        }
        
        void setupRects() {
            super.rectCount = 3;
            super.rects = new double[3][5];
            super.rects[0][0] = super.lineXLocations[1];
            super.rects[0][1] = super.lineYLocations[1];
            super.rects[0][2] = super.lineXLocations[5];
            super.rects[0][3] = super.lineYLocations[5];
            super.rects[0][4] = 0.5;
            super.rects[1][0] = super.lineXLocations[3];
            super.rects[1][1] = super.lineYLocations[3];
            super.rects[1][2] = super.lineXLocations[7];
            super.rects[1][3] = super.lineYLocations[7];
            super.rects[1][4] = 0.5;
            super.rects[2][0] = super.lineXLocations[3];
            super.rects[2][1] = super.lineYLocations[3];
            super.rects[2][2] = super.lineXLocations[5];
            super.rects[2][3] = super.lineYLocations[5];
            super.rects[2][4] = -0.5;
        }
    }
    
    abstract class BlockAperture extends Aperture
    {
        int blockCountX;
        int blockCountY;
        boolean[][] blocks;
        double[] lineXLocations;
        double[] lineYLocations;
        int rectCount;
        double[][] rects;
        
        abstract void setupRects();
        
        void compute() {
            this.setupRects();
            final double[] array = new double[2];
            final double[] array2 = new double[2];
            final double[] array3 = new double[2];
            final double[] array4 = new double[2];
            int n = 1;
            int n2 = 1;
            if (DiffractionFrame.this.color) {
                n = 0;
                n2 = 2;
            }
            final double n3 = DiffractionFrame.this.reversedCheck.getState() ? -1.0 : 0.0;
            final int n4 = this.hasXSymmetry() ? (DiffractionFrame.this.gridSizeX / 2) : DiffractionFrame.this.gridSizeX;
            int n5 = this.hasYSymmetry() ? (DiffractionFrame.this.gridSizeY / 2) : DiffractionFrame.this.gridSizeY;
            for (int i = 0; i != n4; ++i) {
                if (this.hasDiagonalSymmetry()) {
                    n5 = i + 1;
                }
                final double n6 = i / DiffractionFrame.this.gridSizeX - 0.5;
                for (int j = 0; j != n5; ++j) {
                    final double n7 = j / DiffractionFrame.this.gridSizeY - 0.5;
                    for (int k = n; k <= n2; ++k) {
                        final double n8 = DiffractionFrame.this.colorLenMults[k];
                        double n9 = 0.0;
                        double n10 = n3;
                        for (int l = 0; l != this.rectCount; ++l) {
                            DiffractionFrame.this.fresnl((this.rects[l][0] - n6) * n8, array);
                            DiffractionFrame.this.fresnl((this.rects[l][2] - n6) * n8, array2);
                            DiffractionFrame.this.fresnl((this.rects[l][1] - n7) * n8, array3);
                            DiffractionFrame.this.fresnl((this.rects[l][3] - n7) * n8, array4);
                            final double n11 = array[0] - array2[0];
                            final double n12 = array[1] - array2[1];
                            final double n13 = array3[0] - array4[0];
                            final double n14 = array3[1] - array4[1];
                            n9 += this.rects[l][4] * (n11 * n13 - n12 * n14);
                            n10 += this.rects[l][4] * (n11 * n14 + n12 * n13);
                        }
                        DiffractionFrame.this.func[i][j][k] = n9 * n9 + n10 * n10;
                    }
                }
            }
        }
        
        void drawGeometricShadow(final Graphics graphics) {
            for (int i = 1; i < this.blockCountX; i += 2) {
                for (int j = 0; j < this.blockCountY; j += 2) {
                    if (this.blocks[i - 1][j] != this.blocks[i + 1][j]) {
                        final int n = (int)((this.lineXLocations[i] + 0.5) * DiffractionFrame.this.winSize.width);
                        int n2 = 0;
                        int height = DiffractionFrame.this.winSize.height;
                        try {
                            n2 = (int)((this.lineYLocations[j - 1] + 0.5) * DiffractionFrame.this.winSize.height);
                        }
                        catch (Exception ex) {}
                        try {
                            height = (int)((this.lineYLocations[j + 1] + 0.5) * DiffractionFrame.this.winSize.height);
                        }
                        catch (Exception ex2) {}
                        graphics.setColor(this.isSelected(i, -1) ? Color.yellow : Color.red);
                        graphics.drawLine(n, n2, n, height);
                    }
                }
            }
            for (int k = 0; k < this.blockCountX; k += 2) {
                for (int l = 1; l < this.blockCountY; l += 2) {
                    if (this.blocks[k][l - 1] != this.blocks[k][l + 1]) {
                        final int n3 = (int)((this.lineYLocations[l] + 0.5) * DiffractionFrame.this.winSize.height);
                        int n4 = 0;
                        int width = DiffractionFrame.this.winSize.width;
                        try {
                            n4 = (int)((this.lineXLocations[k - 1] + 0.5) * DiffractionFrame.this.winSize.width);
                        }
                        catch (Exception ex3) {}
                        try {
                            width = (int)((this.lineXLocations[k + 1] + 0.5) * DiffractionFrame.this.winSize.width);
                        }
                        catch (Exception ex4) {}
                        graphics.setColor(this.isSelected(-1, l) ? Color.yellow : Color.red);
                        graphics.drawLine(n4, n3, width, n3);
                    }
                }
            }
        }
        
        boolean isSelected(final int n, final int n2) {
            return this.isSelected(n, n2, 0);
        }
        
        boolean isSelected(final int n, final int n2, final int n3) {
            return DiffractionFrame.this.selection != -1 && (DiffractionFrame.this.selection == n + 100 || DiffractionFrame.this.selection == n2 + 200 || (this.hasXSymmetry() && n3 < 1 && this.blockCountX > 3 && this.isSelected(this.blockCountX - 1 - n, n2, 1)) || (this.hasYSymmetry() && n3 < 2 && this.blockCountY > 3 && this.isSelected(n, this.blockCountY - 1 - n2, 2)) || (this.hasDiagonalSymmetry() && n3 < 3 && this.isSelected(n2, n, 3)));
        }
        
        int getSelection(final int n, final int n2) {
            final double n3 = n / DiffractionFrame.this.winSize.width - 0.5;
            final double n4 = n2 / DiffractionFrame.this.winSize.width - 0.5;
            double n5 = 3.0 / DiffractionFrame.this.winSize.width;
            int n6 = -1;
            for (int i = 1; i < this.blockCountX; i += 2) {
                final double abs = Math.abs(this.lineXLocations[i] - n3);
                if (abs < n5) {
                    n6 = 100 + i;
                    n5 = abs;
                }
            }
            for (int j = 1; j < this.blockCountY; j += 2) {
                final double abs2 = Math.abs(this.lineYLocations[j] - n4);
                if (abs2 < n5) {
                    n6 = 200 + j;
                    n5 = abs2;
                }
            }
            return n6;
        }
        
        boolean drag(final int n, final int n2) {
            final double n3 = n / DiffractionFrame.this.winSize.width - 0.5;
            final double n4 = n2 / DiffractionFrame.this.winSize.width - 0.5;
            if (DiffractionFrame.this.selection >= 200) {
                return this.dragLine(-1, DiffractionFrame.this.selection - 200, n4, 0);
            }
            return this.dragLine(DiffractionFrame.this.selection - 100, -1, n3, 0);
        }
        
        void rezoom(final double n) {
            for (int i = 1; i < this.blockCountX; i += 2) {
                final double[] lineXLocations = this.lineXLocations;
                final int n2 = i;
                lineXLocations[n2] *= n;
            }
            for (int j = 1; j < this.blockCountY; j += 2) {
                final double[] lineYLocations = this.lineYLocations;
                final int n3 = j;
                lineYLocations[n3] *= n;
            }
        }
        
        boolean dragLine(final int n, final int n2, final double n3, final int n4) {
            if (n != -1) {
                if (this.hasXSymmetry() && DiffractionFrame.this.sign(this.lineXLocations[n]) != DiffractionFrame.this.sign(n3)) {
                    return false;
                }
                if (n > 1 && n3 <= this.lineXLocations[n - 2]) {
                    return false;
                }
                if (n < this.blockCountX - 2 && n3 >= this.lineXLocations[n + 2]) {
                    return false;
                }
            }
            if (n2 != -1) {
                if (this.hasYSymmetry() && DiffractionFrame.this.sign(this.lineYLocations[n2]) != DiffractionFrame.this.sign(n3)) {
                    return false;
                }
                if (n2 > 1 && n3 <= this.lineYLocations[n2 - 2]) {
                    return false;
                }
                if (n2 < this.blockCountY - 2 && n3 >= this.lineYLocations[n2 + 2]) {
                    return false;
                }
            }
            if (n != -1 && this.hasXSymmetry() && n4 < 1) {
                this.dragLine(this.blockCountX - 1 - n, n2, -n3, 1);
            }
            if (n2 != -1 && this.hasYSymmetry() && n4 < 2) {
                this.dragLine(n, this.blockCountY - 1 - n2, -n3, 2);
            }
            if (this.hasDiagonalSymmetry() && n4 < 3) {
                this.dragLine(n2, n, n3, 3);
            }
            if (n != -1) {
                this.lineXLocations[n] = n3;
            }
            if (n2 != -1) {
                this.lineYLocations[n2] = n3;
            }
            return true;
        }
        
        double getDimension() {
            return this.lineXLocations[this.blockCountX - 2] - this.lineXLocations[1];
        }
    }
    
    class DoubleCircleAperture extends Aperture
    {
        double radius;
        double offset;
        
        String getName() {
            return "2 circles";
        }
        
        Aperture createNext() {
            return null;
        }
        
        boolean hasXSymmetry() {
            return true;
        }
        
        boolean hasYSymmetry() {
            return true;
        }
        
        void setToDefaults() {
            this.radius = 0.3;
            this.offset = 0.25;
        }
        
        double getDimension() {
            return (this.radius + this.offset) * 2.0;
        }
        
        void compute() {
            final double[] array = new double[4];
            double offset = this.offset;
            double sqrt = 0.0;
            final boolean b = this.offset < this.radius;
            if (b) {
                sqrt = Math.sqrt(this.radius * this.radius - this.offset * this.offset);
                final double n = this.radius - 2.0 * this.offset;
                if (n > 0.0) {
                    offset = this.offset + n;
                }
            }
            for (int i = 0; i != DiffractionFrame.this.gridSizeX / 2; ++i) {
                for (int j = 0; j != DiffractionFrame.this.gridSizeY / 2; ++j) {
                    DiffractionFrame.this.clearAccum();
                    final double n2 = i / DiffractionFrame.this.gridSizeX - 0.5;
                    final double n3 = j / DiffractionFrame.this.gridSizeY - 0.5;
                    final double n4 = -n2 + this.offset;
                    final double n5 = -n2 - this.offset;
                    final double n6 = -n3;
                    final double n7 = n4 * n4 + n6 * n6;
                    final double n8 = n5 * n5 + n6 * n6;
                    final double n9 = this.radius * this.radius;
                    final double n10 = n7 - n9;
                    final double n11 = n8 - n9;
                    final double n12 = n10 * 4.0;
                    final double n13 = n11 * 4.0;
                    final double n14 = 0.0;
                    final double n15 = 6.283185307179586;
                    int k;
                    int n16;
                    for (k = (int)(n14 * DiffractionFrame.this.angleSteps / 6.283185307179586), n16 = (int)(n15 * DiffractionFrame.this.angleSteps / 6.283185307179586); k < 0; k += DiffractionFrame.this.angleSteps, n16 += DiffractionFrame.this.angleSteps) {}
                    final boolean b2 = n10 < 0.0 || n11 < 0.0;
                    DiffractionFrame.this.apertureStartOrigin(b2);
                    for (int l = k; l < n16; ++l) {
                        final double n17 = DiffractionFrame.this.angcos1[l & DiffractionFrame.this.angleStepsMask];
                        final double n18 = DiffractionFrame.this.angsin1[l & DiffractionFrame.this.angleStepsMask];
                        final double n19 = -2.0 * (n17 * n4 + n18 * n6);
                        final double n20 = -2.0 * (n17 * n5 + n18 * n6);
                        final double n21 = n19 * n19 - n12;
                        final double n22 = n20 * n20 - n13;
                        if (n21 >= 0.0 || n22 >= 0.0) {
                            int n23 = 0;
                            if (n21 >= 0.0) {
                                final double sqrt2 = Math.sqrt(n21);
                                array[n23++] = 0.5 * (-n19 - sqrt2);
                                array[n23++] = 0.5 * (-n19 + sqrt2);
                            }
                            if (n22 >= 0.0) {
                                final double sqrt3 = Math.sqrt(n22);
                                array[n23++] = 0.5 * (-n20 - sqrt3);
                                array[n23++] = 0.5 * (-n20 + sqrt3);
                            }
                            for (int n24 = 1; n24 < n23; ++n24) {
                                final double n25 = array[n24];
                                int n26 = n24;
                                while (array[n26 - 1] > n25) {
                                    array[n26] = array[n26 - 1];
                                    if (--n26 <= 0) {
                                        break;
                                    }
                                }
                                array[n26] = n25;
                            }
                            int n27 = b2 ? 1 : 0;
                            for (int n28 = 0; n28 != n23; ++n28) {
                                final double n29 = array[n28];
                                if (n29 >= 0.0) {
                                    final double n30 = n2 + n17 * n29;
                                    final double n31 = n3 + n18 * n29;
                                    if (b && n30 > -offset && n30 < offset && n31 > -sqrt && n31 < sqrt) {
                                        final double n32 = n31 * n31;
                                        if ((n30 - this.offset) * (n30 - this.offset) + n32 < n9) {
                                            continue;
                                        }
                                        if ((n30 + this.offset) * (n30 + this.offset) + n32 < n9) {
                                            continue;
                                        }
                                    }
                                    if (n27 == 0) {
                                        DiffractionFrame.this.apertureStart(array[n28]);
                                        n27 = 1;
                                    }
                                    else {
                                        DiffractionFrame.this.apertureStop(array[n28]);
                                        n27 = 0;
                                    }
                                }
                            }
                        }
                    }
                    DiffractionFrame.this.setFunction(i, j);
                }
            }
        }
        
        void drawGeometricShadow(final Graphics graphics) {
            final int n = (int)(DiffractionFrame.this.winSize.width * this.radius);
            final int n2 = (int)(DiffractionFrame.this.winSize.width * this.offset);
            if (DiffractionFrame.this.selection != -1) {
                graphics.setColor((DiffractionFrame.this.selection == 0) ? Color.yellow : Color.red);
                final int n3 = 5;
                graphics.fillOval(DiffractionFrame.this.winSize.width / 2 - n2 - n3, DiffractionFrame.this.winSize.height / 2 - n3, n3 * 2, n3 * 2);
                graphics.fillOval(DiffractionFrame.this.winSize.width / 2 + n2 - n3, DiffractionFrame.this.winSize.height / 2 - n3, n3 * 2, n3 * 2);
            }
            graphics.setColor((DiffractionFrame.this.selection > 0) ? Color.yellow : Color.red);
            int n4 = 0;
            if (this.offset < this.radius) {
                n4 = (int)(Math.acos(this.offset / this.radius) * 57.29577951308232);
            }
            graphics.drawArc(DiffractionFrame.this.winSize.width / 2 - n - n2, DiffractionFrame.this.winSize.height / 2 - n, n * 2, n * 2, n4, 360 - 2 * n4);
            graphics.drawArc(DiffractionFrame.this.winSize.width / 2 - n + n2, DiffractionFrame.this.winSize.height / 2 - n, n * 2, n * 2, 180 + n4, 360 - 2 * n4);
        }
        
        int getSelection(final int n, final int n2) {
            final int n3 = (int)(DiffractionFrame.this.winSize.width * this.offset);
            final int n4 = DiffractionFrame.this.winSize.width / 2 - n3 - n;
            final int n5 = DiffractionFrame.this.winSize.width / 2 + n3 - n;
            final int n6 = DiffractionFrame.this.winSize.height / 2 - n2;
            final double n7 = Math.sqrt(n4 * n4 + n6 * n6) / DiffractionFrame.this.winSize.width;
            if (Math.abs(n7 - this.radius) < 5.0 / DiffractionFrame.this.winSize.width) {
                return 1;
            }
            if (n7 < 5.0 / DiffractionFrame.this.winSize.width) {
                return 0;
            }
            final double n8 = Math.sqrt(n5 * n5 + n6 * n6) / DiffractionFrame.this.winSize.width;
            if (Math.abs(n8 - this.radius) < 5.0 / DiffractionFrame.this.winSize.width) {
                return 2;
            }
            if (n8 < 5.0 / DiffractionFrame.this.winSize.width) {
                return 0;
            }
            return -1;
        }
        
        boolean drag(final int n, final int n2) {
            if (DiffractionFrame.this.selection == 0) {
                final double abs = Math.abs(n / DiffractionFrame.this.winSize.width - 0.5);
                if (abs == this.offset) {
                    return false;
                }
                this.offset = abs;
                return true;
            }
            else {
                final int n3 = (int)(DiffractionFrame.this.winSize.width * this.offset);
                final int n4 = DiffractionFrame.this.winSize.width / 2 - n3 - n;
                final int n5 = DiffractionFrame.this.winSize.width / 2 + n3 - n;
                final int n6 = DiffractionFrame.this.winSize.height / 2 - n2;
                double n7;
                if (DiffractionFrame.this.selection == 2) {
                    n7 = n5 * n5;
                }
                else {
                    n7 = n4 * n4;
                }
                final double radius = Math.sqrt(n7 + n6 * n6) / DiffractionFrame.this.winSize.width;
                if (radius == this.radius) {
                    return false;
                }
                this.radius = radius;
                return true;
            }
        }
        
        void rezoom(final double n) {
            this.radius *= n;
            this.offset *= n;
        }
    }
}
