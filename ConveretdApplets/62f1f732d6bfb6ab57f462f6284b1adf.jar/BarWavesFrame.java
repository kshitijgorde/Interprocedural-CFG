import java.io.InputStream;
import sun.audio.AudioDataStream;
import sun.audio.AudioData;
import sun.audio.AudioPlayer;
import java.awt.event.ItemEvent;
import java.awt.Event;
import java.awt.event.AdjustmentEvent;
import java.awt.event.ActionEvent;
import java.awt.event.ComponentEvent;
import java.awt.event.MouseEvent;
import java.awt.image.ImageObserver;
import java.awt.Graphics;
import java.awt.Label;
import java.awt.Component;
import java.awt.LayoutManager;
import java.awt.Color;
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

class BarWavesFrame extends Frame implements ComponentListener, ActionListener, AdjustmentListener, MouseMotionListener, MouseListener, ItemListener
{
    Thread engine;
    Dimension winSize;
    Image dbimage;
    Random random;
    int maxTerms;
    int modeCount;
    int maxMaxTerms;
    int sampleCount;
    double[][] modeTable;
    double[] modeNorms;
    public static final double epsilon = 1.0E-7;
    public static final double epsilon2 = 0.003;
    Button sineButton;
    Button blankButton;
    Checkbox stoppedCheck;
    Checkbox soundCheck;
    Choice modeChooser;
    Choice setupChooser;
    Vector setupList;
    Setup setup;
    Choice displayChooser;
    Scrollbar dampingBar;
    Scrollbar speedBar;
    Scrollbar loadBar;
    Scrollbar baseFreqBar;
    Scrollbar stiffnessBar;
    double[] magcoef;
    double[] dampcoef;
    double[] phasecoef;
    double[] phasecoefcos;
    double[] phasecoefadj;
    double[] omega;
    static final double pi = 3.141592653589793;
    double step;
    double[] func;
    double[] funci;
    int[] thickness;
    int[] xpoints;
    int[] ypoints;
    int selectedCoef;
    int magnitudesY;
    static final int SEL_NONE = 0;
    static final int SEL_FUNC = 1;
    static final int SEL_MAG = 2;
    static final int MODE_SHAPE = 0;
    static final int MODE_FORCE = 1;
    static final int MODE_THICKNESS = 2;
    static final int DISP_PHASE = 0;
    static final int DISP_PHASECOS = 1;
    static final int DISP_MODES = 2;
    static final int BOUND_HINGED = 0;
    static final int BOUND_FREE = 1;
    static final int BOUND_CLAMPED = 2;
    int selection;
    int dragX;
    int dragY;
    boolean dragging;
    double t;
    int pause;
    Color gray1;
    Color gray2;
    BarWavesCanvas cv;
    BarWaves applet;
    double logep2;
    static final int[] to_ulaw;
    double sndmin;
    double sndmax;
    
    public String getAppletInfo() {
        return "BarWaves by Paul Falstad";
    }
    
    int getrand(final int n) {
        int nextInt = this.random.nextInt();
        if (nextInt < 0) {
            nextInt = -nextInt;
        }
        return nextInt % n;
    }
    
    BarWavesFrame(final BarWaves applet) {
        super("Bar Waves Applet");
        this.engine = null;
        this.maxTerms = 50;
        this.maxMaxTerms = 90;
        this.gray1 = new Color(76, 76, 76);
        this.gray2 = new Color(127, 127, 127);
        this.logep2 = 0.0;
        this.applet = applet;
    }
    
    public void init() {
        this.setupList = new Vector();
        for (Setup next = new FreeBarSetup(); next != null; next = next.createNext()) {
            this.setupList.addElement(next);
        }
        this.selectedCoef = -1;
        this.setLayout(new BarWavesLayout());
        (this.cv = new BarWavesCanvas(this)).addComponentListener(this);
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
        this.add(this.sineButton = new Button("Fundamental"));
        this.sineButton.addActionListener(this);
        this.add(this.blankButton = new Button("Clear"));
        this.blankButton.addActionListener(this);
        (this.stoppedCheck = new Checkbox("Stopped")).addItemListener(this);
        this.add(this.stoppedCheck);
        (this.soundCheck = new Checkbox("Sound", false)).addItemListener(this);
        this.add(this.soundCheck);
        (this.modeChooser = new Choice()).add("Mouse = Shape bar");
        this.modeChooser.add("Mouse = Apply static force");
        this.modeChooser.addItemListener(this);
        this.add(this.modeChooser);
        (this.displayChooser = new Choice()).add("Display Phases");
        this.displayChooser.add("Display Phase Cosines");
        this.displayChooser.add("Display Modes");
        this.displayChooser.addItemListener(this);
        this.add(this.displayChooser);
        this.add(new Label("Simulation Speed", 1));
        this.add(this.speedBar = new Scrollbar(0, 166, 1, 24, 300));
        this.speedBar.addAdjustmentListener(this);
        this.add(new Label("Damping", 1));
        this.add(this.dampingBar = new Scrollbar(0, 10, 1, 0, 400));
        this.dampingBar.addAdjustmentListener(this);
        this.add(new Label("Resolution", 1));
        this.add(this.loadBar = new Scrollbar(0, this.maxTerms, 1, 40, this.maxMaxTerms));
        this.loadBar.addAdjustmentListener(this);
        this.setLoadCount();
        this.add(new Label("Base Frequency", 1));
        this.add(this.baseFreqBar = new Scrollbar(0, 84, 12, 30, 168));
        this.baseFreqBar.addAdjustmentListener(this);
        this.baseFreqBar.disable();
        this.add(new Label("String Stiffness", 1));
        this.add(this.stiffnessBar = new Scrollbar(0, 10, 1, 0, 100));
        this.stiffnessBar.addAdjustmentListener(this);
        this.stiffnessBar.disable();
        try {
            final String parameter = this.applet.getParameter("PAUSE");
            if (parameter != null) {
                this.pause = Integer.parseInt(parameter);
            }
        }
        catch (Exception ex) {}
        this.magcoef = new double[this.maxMaxTerms];
        this.phasecoef = new double[this.maxMaxTerms];
        this.phasecoefcos = new double[this.maxMaxTerms];
        this.phasecoefadj = new double[this.maxMaxTerms];
        this.func = new double[this.maxMaxTerms + 1];
        this.funci = new double[this.maxMaxTerms + 1];
        this.xpoints = new int[4];
        this.ypoints = new int[4];
        this.random = new Random();
        this.setDamping();
        this.reinit();
        this.cv.setBackground(Color.black);
        this.cv.setForeground(Color.lightGray);
        this.resize(500, 500);
        this.handleResize();
        this.show();
    }
    
    void reinit() {
        this.doFundamental();
    }
    
    void handleResize() {
        final Dimension size = this.cv.getSize();
        this.winSize = size;
        final Dimension dimension = size;
        if (this.winSize.width == 0) {
            return;
        }
        this.dbimage = this.createImage(dimension.width, dimension.height);
    }
    
    void doFundamental() {
        this.doBlank();
        this.magcoef[0] = 1.0;
        if (this.soundCheck.getState()) {
            this.doPlay();
        }
    }
    
    void doBlank() {
        for (int i = 0; i <= this.sampleCount; ++i) {
            this.func[i] = 0.0;
        }
        this.transform(true);
    }
    
    void transform(final boolean b) {
        this.t = 0.0;
        for (int i = 0; i != this.modeCount; ++i) {
            double n = 0.0;
            double n2 = 0.0;
            for (int j = 1; j != this.sampleCount; ++j) {
                n += this.modeTable[j][i] * this.func[j];
                n2 -= this.modeTable[j][i] * this.funci[j];
            }
            double n3 = n / this.modeNorms[i];
            double n4 = n2 / (this.omega[i] * this.modeNorms[i]);
            if (n3 < 1.0E-7 && n3 > -1.0E-7) {
                n3 = 0.0;
            }
            if (n4 < 1.0E-7 && n4 > -1.0E-7) {
                n4 = 0.0;
            }
            if (b) {
                n4 = 0.0;
            }
            this.magcoef[i] = Math.sqrt(n3 * n3 + n4 * n4);
            final double atan2 = Math.atan2(n4, n3);
            this.phasecoefadj[i] = atan2;
            this.phasecoef[i] = atan2;
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
    
    public void updateBarWaves(final Graphics graphics) {
        if (this.winSize == null || this.winSize.width == 0) {
            return;
        }
        final Graphics graphics2 = this.dbimage.getGraphics();
        boolean b = true;
        if (!this.stoppedCheck.getState()) {
            this.t += Math.exp((this.speedBar.getValue() - 100) / 20.0) * 0.002 * (1.0 + this.getrand(300) * 0.00191171);
        }
        graphics2.setColor(this.cv.getBackground());
        graphics2.fillRect(0, 0, this.winSize.width, this.winSize.height);
        graphics2.setColor(this.cv.getForeground());
        final int panelHeight = this.getPanelHeight();
        final int n = panelHeight / 2;
        final int n2 = panelHeight / 2;
        final double n3 = 0.75 * n2;
        for (int i = -1; i <= 1; ++i) {
            graphics2.setColor((i == 0) ? this.gray2 : this.gray1);
            graphics2.drawLine(0, n + i * (int)n3, this.winSize.width, n + i * (int)n3);
        }
        graphics2.setColor(this.gray2);
        graphics2.drawLine(this.winSize.width / 2, n - (int)n3, this.winSize.width / 2, n + (int)n3);
        final int n4 = (this.setup.leftBoundary() == 1) ? 1 : 0;
        final int n5 = this.sampleCount - ((this.setup.rightBoundary() == 1) ? 1 : 0);
        if (this.dragging && this.selection == 1) {
            graphics2.setColor(Color.cyan);
            b = true;
            for (int j = n4; j <= n5; ++j) {
                this.drawBarPiece(graphics2, this.winSize.width * j / this.sampleCount, n - (int)(n3 * this.func[j]), j, n4);
            }
        }
        if (!this.stoppedCheck.getState() && !this.dragging) {
            for (int k = 0; k != this.modeCount; ++k) {
                final double[] magcoef = this.magcoef;
                final int n6 = k;
                magcoef[n6] *= this.dampcoef[k];
            }
        }
        final double[] magcoef2 = this.magcoef;
        final double[] phasecoef = this.phasecoef;
        final double[] phasecoefcos = this.phasecoefcos;
        if (!this.dragging || this.selection != 1) {
            graphics2.setColor(Color.white);
            for (int l = 0; l != this.modeCount; ++l) {
                if (this.magcoef[l] < 1.0E-7 && this.magcoef[l] > -1.0E-7) {
                    final double[] magcoef3 = this.magcoef;
                    final int n7 = l;
                    final double[] phasecoef2 = this.phasecoef;
                    final int n8 = l;
                    final double[] phasecoefadj = this.phasecoefadj;
                    final int n9 = l;
                    final double n10 = 0.0;
                    phasecoefadj[n9] = n10;
                    magcoef3[n7] = (phasecoef2[n8] = n10);
                }
                else {
                    b = false;
                    this.phasecoef[l] = (this.omega[l] * this.t + this.phasecoefadj[l]) % 6.283185307179586;
                    if (this.phasecoef[l] > 3.141592653589793) {
                        final double[] phasecoef3 = this.phasecoef;
                        final int n11 = l;
                        phasecoef3[n11] -= 6.283185307179586;
                    }
                    else if (this.phasecoef[l] < -3.141592653589793) {
                        final double[] phasecoef4 = this.phasecoef;
                        final int n12 = l;
                        phasecoef4[n12] += 6.283185307179586;
                    }
                    this.phasecoefcos[l] = Math.cos(this.phasecoef[l]);
                }
            }
            for (int n13 = n4; n13 <= n5; ++n13) {
                final int n14 = this.winSize.width * n13 / this.sampleCount;
                double n15 = 0.0;
                for (int n16 = 0; n16 != this.modeCount; ++n16) {
                    n15 += magcoef2[n16] * this.modeTable[n13][n16] * phasecoefcos[n16];
                }
                this.func[n13] = n15;
                this.drawBarPiece(graphics2, n14, n - (int)(n3 * n15), n13, n4);
            }
            if (this.setup.getThickness() == 0) {
                if (this.setup.leftBoundary() == 1) {
                    this.drawPin(graphics2, 1, n, n3);
                }
                if (this.setup.rightBoundary() == 1) {
                    this.drawPin(graphics2, this.sampleCount - 1, n, n3);
                }
            }
        }
        if (this.selectedCoef != -1 && !this.dragging && (magcoef2[this.selectedCoef] > 0.04 || magcoef2[this.selectedCoef] < -0.04)) {
            graphics2.setColor(Color.yellow);
            final double n17 = n3 * magcoef2[this.selectedCoef];
            for (int n18 = n4; n18 <= n5; ++n18) {
                this.drawBarPiece(graphics2, this.winSize.width * n18 / this.sampleCount, n - (int)(n17 * (this.modeTable[n18][this.selectedCoef] * phasecoefcos[this.selectedCoef])), n18, n4);
            }
        }
        if (this.selectedCoef != -1) {
            final int freq = this.getFreq(this.selectedCoef);
            graphics2.setColor(Color.yellow);
            this.centerString(graphics2, freq + " Hz", panelHeight);
        }
        else if (this.soundCheck.getState()) {
            final int freq2 = this.getFreq(0);
            graphics2.setColor(Color.white);
            this.centerString(graphics2, "Fundamental = " + freq2 + " Hz", panelHeight);
        }
        final int termWidth = this.getTermWidth();
        final double n19 = 0.6 * n2;
        graphics2.setColor(Color.white);
        if (this.displayChooser.getSelectedIndex() == 0 || this.displayChooser.getSelectedIndex() == 1) {
            this.magnitudesY = panelHeight;
        }
        else {
            this.magnitudesY = panelHeight * 2;
        }
        final int n20 = this.magnitudesY + panelHeight / 2 + (int)n19 / 2;
        graphics2.setColor(this.gray2);
        graphics2.drawLine(0, n20, this.winSize.width, n20);
        graphics2.setColor(this.gray1);
        graphics2.drawLine(0, n20 - (int)n19, this.winSize.width, n20 - (int)n19);
        graphics2.drawLine(0, n20 + (int)n19, this.winSize.width, n20 + (int)n19);
        graphics2.drawLine(0, n20 - (int)n19 / 4, this.winSize.width, n20 - (int)n19 / 4);
        graphics2.drawLine(0, n20 + (int)n19 / 4, this.winSize.width, n20 + (int)n19 / 4);
        int n21 = termWidth - 3;
        if (n21 < 3) {
            n21 = 3;
        }
        for (int n22 = 0; n22 != this.modeCount; ++n22) {
            final int n23 = termWidth * n22 + termWidth / 2;
            final int n24 = n20 - (int)(this.logcoef(magcoef2[n22]) * n19);
            graphics2.setColor((n22 == this.selectedCoef) ? Color.yellow : Color.white);
            graphics2.drawLine(n23, n20, n23, n24);
            graphics2.fillOval(n23 - n21 / 2, n24 - n21 / 2, n21, n21);
        }
        if (this.displayChooser.getSelectedIndex() == 0 || this.displayChooser.getSelectedIndex() == 1) {
            graphics2.setColor(Color.white);
            final boolean b2 = this.displayChooser.getSelectedIndex() == 1;
            double n25 = 0.75 * n2;
            final int n26 = panelHeight * 5 / 2;
            for (int n27 = -2; n27 <= 2; ++n27) {
                if (b2) {
                    if (n27 == 1) {
                        continue;
                    }
                    if (n27 == -1) {
                        continue;
                    }
                }
                graphics2.setColor((n27 == 0) ? this.gray2 : this.gray1);
                graphics2.drawLine(0, n26 + n27 * (int)n25 / 2, this.winSize.width, n26 + n27 * (int)n25 / 2);
            }
            if (!b2) {
                n25 /= 3.141592653589793;
            }
            for (int n28 = 0; n28 != this.modeCount; ++n28) {
                final int n29 = termWidth * n28 + termWidth / 2;
                double n30 = b2 ? phasecoefcos[n28] : phasecoef[n28];
                if (this.magcoef[n28] > -7.5E-4 && magcoef2[n28] < 7.5E-4) {
                    n30 = 0.0;
                }
                final int n31 = n26 - (int)(n30 * n25);
                graphics2.setColor((n28 == this.selectedCoef) ? Color.yellow : Color.white);
                graphics2.drawLine(n29, n26, n29, n31);
                graphics2.fillOval(n29 - n21 / 2, n31 - n21 / 2, n21, n21);
            }
        }
        else if (this.displayChooser.getSelectedIndex() == 2) {
            final int n32 = (this.winSize.width - 25) / 3;
            final int n33 = (int)(n32 / 3.141592653589793);
            int n34 = panelHeight;
            int n35 = 0;
            int n36 = -1;
            for (int n37 = 0; n37 != this.modeCount; ++n37) {
                if (magcoef2[n37] > 0.06 || magcoef2[n37] < -0.06) {
                    graphics2.setColor(this.gray2);
                    final int n38 = n35 + n32 / 2;
                    final int n39 = n34 + n33 / 2;
                    graphics2.drawLine(n35, n39, n35 + n32, n39);
                    graphics2.drawLine(n38, n34, n38, n34 + n33);
                    graphics2.setColor((n37 == this.selectedCoef) ? Color.yellow : Color.white);
                    graphics2.drawRect(n35, n34, n32, n33);
                    int n40 = -1;
                    final double n41 = n33 * 0.5 * magcoef2[n37];
                    for (int n42 = n4; n42 <= n5; ++n42) {
                        final int n43 = n35 + n32 * n42 / this.sampleCount;
                        final int n44 = n39 - (int)(n41 * (this.modeTable[n42][n37] * phasecoefcos[n37]));
                        if (n40 != -1) {
                            graphics2.drawLine(n40, n36, n43, n44);
                        }
                        n40 = n43;
                        n36 = n44;
                    }
                    n35 += n32 + 10;
                    if (n35 + n32 > this.winSize.width) {
                        n35 = 0;
                        n34 += n33 + 10;
                        if (n34 + n33 > panelHeight * 2) {
                            break;
                        }
                    }
                }
            }
        }
        graphics.drawImage(this.dbimage, 0, 0, this);
        if (!this.stoppedCheck.getState() && !b) {
            this.cv.repaint(this.pause);
        }
    }
    
    void drawPin(final Graphics graphics, final int n, final int n2, final double n3) {
        final int n4 = this.winSize.width * n / this.sampleCount;
        graphics.setColor(this.gray2);
        graphics.drawLine(n4, (int)(n2 - n3), n4, (int)(n2 + n3));
        graphics.setColor(Color.white);
        graphics.fillOval(n4 - 2, n2 - (int)(this.func[n] * n3) - 2, 5, 5);
    }
    
    int getTermWidth() {
        int n = this.winSize.width / this.modeCount;
        final int n2 = this.winSize.width / 30;
        if (n > n2) {
            n = n2;
        }
        return n & 0xFFFFFFFE;
    }
    
    void getVelocities() {
        for (int i = 0; i != this.sampleCount; ++i) {
            double n = 0.0;
            for (int j = 0; j != this.modeCount; ++j) {
                n += this.magcoef[j] * this.modeTable[i][j] * Math.sin(this.phasecoef[j]) * this.omega[j];
            }
            this.funci[i] = -n;
        }
    }
    
    void drawBarPiece(final Graphics graphics, final int n, final int n2, final int n3, final int n4) {
        final int thickness = this.setup.getThickness();
        this.xpoints[0] = this.xpoints[3];
        this.ypoints[0] = this.ypoints[3];
        this.xpoints[1] = this.xpoints[2];
        this.ypoints[1] = this.ypoints[2];
        this.xpoints[2] = n;
        this.ypoints[2] = n2 - thickness;
        this.xpoints[3] = n;
        this.ypoints[3] = n2 + thickness;
        if (n3 != n4) {
            if (thickness == 0) {
                graphics.drawLine(this.xpoints[0], this.ypoints[0], this.xpoints[2], this.ypoints[2]);
            }
            else {
                graphics.fillPolygon(this.xpoints, this.ypoints, 4);
            }
        }
    }
    
    void edit(final MouseEvent mouseEvent) {
        if (this.selection == 0) {
            return;
        }
        final int x = mouseEvent.getX();
        final int y = mouseEvent.getY();
        switch (this.selection) {
            case 2: {
                this.editMag(x, y);
                break;
            }
            case 1: {
                this.editFunc(x, y);
                break;
            }
        }
    }
    
    void editMag(final int n, final int n2) {
        if (this.selectedCoef == -1) {
            return;
        }
        final int panelHeight = this.getPanelHeight();
        final double n3 = 0.6 * panelHeight / 2.0;
        double unlogcoef = this.unlogcoef(-(n2 - (this.magnitudesY + panelHeight / 2 + (int)n3 / 2)) / n3);
        if (unlogcoef < -1.0) {
            unlogcoef = -1.0;
        }
        if (unlogcoef > 1.0) {
            unlogcoef = 1.0;
        }
        if (this.magcoef[this.selectedCoef] == unlogcoef) {
            return;
        }
        this.magcoef[this.selectedCoef] = unlogcoef;
        this.cv.repaint(this.pause);
    }
    
    void editFunc(int i, int n) {
        if (this.modeChooser.getSelectedIndex() == 1) {
            this.editFuncForce(i, n);
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
    
    double logcoef(double n) {
        if (n >= 0.25 || n <= -0.25) {
            return n;
        }
        n *= 4.0;
        final double n2 = 0.003;
        final int n3 = (n < 0.0) ? -1 : 1;
        n *= n3;
        if (n < n2) {
            return 0.0;
        }
        if (this.logep2 == 0.0) {
            this.logep2 = -Math.log(2.0 * n2);
        }
        return 0.25 * n3 * (Math.log(n + n2) + this.logep2) / this.logep2;
    }
    
    double unlogcoef(final double n) {
        if (n >= 0.25 || n <= -0.25) {
            return n;
        }
        final double n2 = 0.003;
        final int n3 = (n < 0.0) ? -1 : 1;
        return 0.25 * n3 * (Math.exp(4.0 * n * n3 * this.logep2 - this.logep2) - n2);
    }
    
    void editFuncPoint(final int n, final int n2) {
        final int panelHeight = this.getPanelHeight();
        final int n3 = panelHeight / 2;
        final int n4 = panelHeight / 2;
        final int width = this.winSize.width;
        final double n5 = 0.75 * n4;
        int i = n * this.sampleCount / width;
        int n6 = ((n + 1) * this.sampleCount - 1) / width;
        double n7 = (n3 - n2) / n5;
        if (n7 > 1.0) {
            n7 = 1.0;
        }
        if (n7 < -1.0) {
            n7 = -1.0;
        }
        if (i < 1) {
            i = 1;
        }
        if (n6 >= this.sampleCount) {
            n6 = this.sampleCount - 1;
        }
        while (i <= n6) {
            if (this.modeChooser.getSelectedIndex() == 2) {
                this.thickness[i] = ((n3 < n2) ? ((n2 - n3) * 2) : ((n3 - n2) * 2));
                if (this.thickness[i] == 0) {
                    this.thickness[i] = 1;
                }
            }
            else {
                this.func[i] = n7;
                this.funci[i] = 0.0;
            }
            ++i;
        }
        this.func[this.sampleCount] = this.func[0];
        this.cv.repaint(this.pause);
        if (!this.soundCheck.getState()) {
            this.transform(false);
        }
    }
    
    void editFuncForce(final int n, final int n2) {
        final int panelHeight = this.getPanelHeight();
        final int n3 = panelHeight / 2;
        final int n4 = panelHeight / 2;
        final int width = this.winSize.width;
        final double n5 = 0.75 * n4;
        final int n6 = n * this.sampleCount / width;
        double n7 = (n3 - n2) / n5;
        if (n7 > 1.0) {
            n7 = 1.0;
        }
        if (n7 < -1.0) {
            n7 = -1.0;
        }
        if (n6 < 1 || n6 >= this.sampleCount) {
            return;
        }
        final double[] array = new double[this.modeCount];
        for (int i = 0; i != this.modeCount; ++i) {
            array[i] = this.modeTable[n6][i] / (this.omega[i] * this.omega[i] * this.modeNorms[i]);
        }
        for (int j = 0; j != this.sampleCount; ++j) {
            double n8 = 0.0;
            for (int k = 0; k != this.modeCount; ++k) {
                n8 += array[k] * this.modeTable[j][k];
            }
            this.func[j] = n8;
        }
        final double n9 = n7 / this.func[n6];
        for (int l = 0; l <= this.sampleCount; ++l) {
            final double[] func = this.func;
            final int n10 = l;
            func[n10] *= n9;
            this.funci[l] = 0.0;
        }
        this.cv.repaint(this.pause);
        if (!this.soundCheck.getState()) {
            this.transform(true);
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
        if (actionEvent.getSource() == this.sineButton) {
            this.doFundamental();
            this.cv.repaint();
        }
        if (actionEvent.getSource() == this.blankButton) {
            this.doBlank();
            this.cv.repaint();
        }
    }
    
    public void adjustmentValueChanged(final AdjustmentEvent adjustmentEvent) {
        System.out.print(((Scrollbar)adjustmentEvent.getSource()).getValue() + "\n");
        if (adjustmentEvent.getSource() == this.dampingBar || adjustmentEvent.getSource() == this.speedBar) {
            this.setDamping();
        }
        if (adjustmentEvent.getSource() == this.loadBar) {
            this.setLoadCount();
        }
        if (adjustmentEvent.getSource() == this.stiffnessBar) {
            this.genModes();
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
    
    void setLoadCount() {
        this.setup = this.setupList.elementAt(this.setupChooser.getSelectedIndex());
        final int value = this.loadBar.getValue();
        this.maxTerms = value;
        this.sampleCount = value;
        this.step = 3.141592653589793 / this.sampleCount;
        this.thickness = new int[this.sampleCount + 1];
        for (int i = 0; i <= this.sampleCount; ++i) {
            this.thickness[i] = 5;
        }
        this.genModes();
        this.setDamping();
    }
    
    void setDamping() {
        this.dampcoef = new double[this.modeCount];
        final double n = Math.exp((this.speedBar.getValue() - 100) / 20.0) * 0.002;
        for (int i = 0; i != this.modeCount; ++i) {
            double n2 = Math.exp(this.dampingBar.getValue() / 40 - 3) * 30.0;
            if (this.dampingBar.getValue() <= 2) {
                n2 = 0.0;
            }
            this.dampcoef[i] = Math.exp(-(this.omega[i] * Math.sqrt(Math.sqrt(1.0 + n2 * n2 / (this.omega[i] * this.omega[i])) - 1.0)) * n * 0.004);
        }
    }
    
    public void mouseDragged(final MouseEvent mouseEvent) {
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
        final int panelHeight = this.getPanelHeight();
        final int selectedCoef = this.selectedCoef;
        this.selectedCoef = -1;
        this.selection = 0;
        if (y < panelHeight) {
            this.selection = 1;
        }
        if (y >= this.magnitudesY && y < this.magnitudesY + panelHeight) {
            this.selectedCoef = x / this.getTermWidth();
            if (this.selectedCoef >= this.modeCount) {
                this.selectedCoef = -1;
            }
            if (this.selectedCoef != -1) {
                this.selection = 2;
            }
        }
        if (this.selectedCoef != selectedCoef) {
            this.cv.repaint(this.pause);
        }
    }
    
    public void mouseClicked(final MouseEvent mouseEvent) {
        if (mouseEvent.getClickCount() == 2 && this.selectedCoef != -1) {
            for (int i = 0; i != this.modeCount; ++i) {
                if (this.selectedCoef != i) {
                    this.magcoef[i] = 0.0;
                }
            }
            this.magcoef[this.selectedCoef] = 1.0;
            this.cv.repaint(this.pause);
        }
    }
    
    public void mouseEntered(final MouseEvent mouseEvent) {
    }
    
    public void mouseExited(final MouseEvent mouseEvent) {
        if (!this.dragging && this.selectedCoef != -1) {
            this.selectedCoef = -1;
            this.cv.repaint(this.pause);
        }
    }
    
    public void mousePressed(final MouseEvent mouseEvent) {
        if ((mouseEvent.getModifiers() & 0x10) == 0x0) {
            return;
        }
        if (this.selection == 1) {
            this.getVelocities();
        }
        this.dragging = true;
        this.edit(mouseEvent);
    }
    
    public void mouseReleased(final MouseEvent mouseEvent) {
        if ((mouseEvent.getModifiers() & 0x10) == 0x0) {
            return;
        }
        if (this.dragging && this.selection == 1) {
            if (this.modeChooser.getSelectedIndex() == 2) {
                this.genModes();
            }
            else {
                this.transform(false);
                if (this.soundCheck.getState()) {
                    this.doPlay();
                }
            }
        }
        if (this.dragging && this.selection == 2 && this.soundCheck.getState()) {
            this.doPlay();
        }
        this.dragging = false;
        this.cv.repaint(this.pause);
    }
    
    public void itemStateChanged(final ItemEvent itemEvent) {
        if (itemEvent.getItemSelectable() == this.stoppedCheck) {
            this.cv.repaint(this.pause);
            return;
        }
        if (itemEvent.getItemSelectable() == this.soundCheck) {
            if (this.soundCheck.getState()) {
                this.speedBar.setValue(250);
                this.dampingBar.setValue(170);
                this.baseFreqBar.enable();
                this.setDamping();
                this.doPlay();
            }
            else {
                this.baseFreqBar.disable();
            }
        }
        if (itemEvent.getItemSelectable() == this.displayChooser) {
            this.cv.repaint(this.pause);
        }
        if (itemEvent.getItemSelectable() == this.setupChooser) {
            this.setLoadCount();
            if (this.setup instanceof StiffStringSetup) {
                this.stiffnessBar.enable();
            }
            else {
                this.stiffnessBar.disable();
            }
        }
    }
    
    void dodiff(final double[][] array, final int n, final int n2, final int n3, final double n4) {
        if (n2 < 1 && this.setup.leftBoundary() == 0) {
            return;
        }
        if (n2 > this.sampleCount - 1 && this.setup.rightBoundary() == 0) {
            return;
        }
        if (n3 == 2 && !(this.setup instanceof StringSetup)) {
            if (n2 <= 1 && this.setup.leftBoundary() == 1) {
                return;
            }
            if (n2 >= this.sampleCount - 1 && this.setup.rightBoundary() == 1) {
                return;
            }
        }
        if (n3 > 0) {
            this.dodiff(array, n, n2 - 1, n3 - 2, -n4);
            this.dodiff(array, n, n2 + 1, n3 - 2, -n4);
            this.dodiff(array, n, n2, n3 - 2, n4 * 2.0);
            return;
        }
        if (n2 >= 1 && n2 <= this.sampleCount - 1) {
            final double[] array2 = array[n];
            array2[n2] += n4;
        }
    }
    
    void genModes() {
        final int n = this.sampleCount - 1;
        final double[][] array = new double[n + 1][n + 1];
        final double[] array2 = new double[n + 1];
        final double[] array3 = new double[n + 1];
        for (int i = 1; i <= n; ++i) {
            this.setup.doMatrixStep(array, i, n);
        }
        if (this.setup instanceof StringSetup) {
            if (this.setup.leftBoundary() == 1) {
                final double[] array4 = array[1];
                final int n2 = 1;
                --array4[n2];
            }
            if (this.setup.rightBoundary() == 1) {
                final double[] array5 = array[n];
                final int n3 = n;
                --array5[n3];
            }
        }
        this.tred2(array, n, array2, array3);
        this.tqli(array2, array3, n, array);
        this.modeCount = this.sampleCount - 1;
        this.omega = new double[this.modeCount];
        final int[] array6 = new int[this.sampleCount];
        int j;
        for (int n4 = j = 0; j != n; ++j) {
            if (array2[j + 1] < 1.0E-8) {
                --this.modeCount;
            }
            else {
                this.omega[n4] = Math.sqrt(array2[j + 1]);
                array6[n4] = j;
                ++n4;
            }
        }
        for (int k = 1; k < this.modeCount; ++k) {
            final double n5 = this.omega[k];
            final int n6 = array6[k];
            int n7 = k;
            while (this.omega[n7 - 1] > n5) {
                this.omega[n7] = this.omega[n7 - 1];
                array6[n7] = array6[n7 - 1];
                if (--n7 <= 0) {
                    break;
                }
            }
            this.omega[n7] = n5;
            array6[n7] = n6;
        }
        this.modeTable = new double[this.sampleCount + 1][this.modeCount];
        this.modeNorms = new double[this.modeCount];
        for (int l = 0; l != this.modeCount; ++l) {
            final int n8 = array6[l] + 1;
            double n9 = 0.0;
            for (int n10 = 0; n10 != this.sampleCount; ++n10) {
                this.modeTable[n10][l] = array[n10][n8];
                if (this.modeTable[n10][l] > n9) {
                    n9 = this.modeTable[n10][l];
                }
                if (-this.modeTable[n10][l] > n9) {
                    n9 = -this.modeTable[n10][l];
                }
            }
            this.modeNorms[l] = 1.0 / (n9 * n9);
            for (int n11 = 0; n11 != this.sampleCount; ++n11) {
                final double[] array7 = this.modeTable[n11];
                final int n12 = l;
                array7[n12] /= n9;
            }
        }
        final double n13 = 1.0 / this.omega[0];
        for (int n14 = 0; n14 != this.modeCount; ++n14) {
            final double[] omega = this.omega;
            final int n15 = n14;
            omega[n15] *= n13;
        }
    }
    
    void tred2(final double[][] array, final int n, final double[] array2, final double[] array3) {
        for (int i = n; i >= 2; --i) {
            final int n2 = i - 1;
            double n4;
            double n3 = n4 = 0.0;
            if (n2 > 1) {
                for (int j = 1; j <= n2; ++j) {
                    n3 += Math.abs(array[i][j]);
                }
                if (n3 == 0.0) {
                    array3[i] = array[i][n2];
                }
                else {
                    for (int k = 1; k <= n2; ++k) {
                        final double[] array4 = array[i];
                        final int n5 = k;
                        array4[n5] /= n3;
                        n4 += array[i][k] * array[i][k];
                    }
                    final double n6 = array[i][n2];
                    final double n7 = (n6 >= 0.0) ? (-Math.sqrt(n4)) : Math.sqrt(n4);
                    array3[i] = n3 * n7;
                    n4 -= n6 * n7;
                    array[i][n2] = n6 - n7;
                    double n8 = 0.0;
                    for (int l = 1; l <= n2; ++l) {
                        array[l][i] = array[i][l] / n4;
                        double n9 = 0.0;
                        for (int n10 = 1; n10 <= l; ++n10) {
                            n9 += array[l][n10] * array[i][n10];
                        }
                        for (int n11 = l + 1; n11 <= n2; ++n11) {
                            n9 += array[n11][l] * array[i][n11];
                        }
                        array3[l] = n9 / n4;
                        n8 += array3[l] * array[i][l];
                    }
                    final double n12 = n8 / (n4 + n4);
                    for (int n13 = 1; n13 <= n2; ++n13) {
                        final double n14 = array[i][n13];
                        final double n15 = array3[n13] -= n12 * n14;
                        for (int n16 = 1; n16 <= n13; ++n16) {
                            final double[] array5 = array[n13];
                            final int n17 = n16;
                            array5[n17] -= n14 * array3[n16] + n15 * array[i][n16];
                        }
                    }
                }
            }
            else {
                array3[i] = array[i][n2];
            }
            array2[i] = n4;
        }
        array3[1] = (array2[1] = 0.0);
        for (int n18 = 1; n18 <= n; ++n18) {
            final int n19 = n18 - 1;
            if (array2[n18] != 0.0) {
                for (int n20 = 1; n20 <= n19; ++n20) {
                    double n21 = 0.0;
                    for (int n22 = 1; n22 <= n19; ++n22) {
                        n21 += array[n18][n22] * array[n22][n20];
                    }
                    for (int n23 = 1; n23 <= n19; ++n23) {
                        final double[] array6 = array[n23];
                        final int n24 = n20;
                        array6[n24] -= n21 * array[n23][n18];
                    }
                }
            }
            array2[n18] = array[n18][n18];
            array[n18][n18] = 1.0;
            for (int n25 = 1; n25 <= n19; ++n25) {
                array[n25][n18] = (array[n18][n25] = 0.0);
            }
        }
    }
    
    void tqli(final double[] array, final double[] array2, final int n, final double[][] array3) {
        for (int i = 2; i <= n; ++i) {
            array2[i - 1] = array2[i];
        }
        array2[n] = 0.0;
        for (int j = 1; j <= n; ++j) {
            int n2 = 0;
            int k;
            do {
                for (k = j; k <= n - 1; ++k) {
                    final double n3 = Math.abs(array[k]) + Math.abs(array[k + 1]);
                    if (Math.abs(array2[k]) + n3 == n3) {
                        break;
                    }
                }
                if (k != j) {
                    if (n2++ == 30) {
                        System.out.print("Too many iterations in tqli\n");
                    }
                    final double n4 = (array[j + 1] - array[j]) / (2.0 * array2[j]);
                    double pythag = this.pythag(n4, 1.0);
                    double n5 = array[k] - array[j] + array2[j] / (n4 + this.SIGN(pythag, n4));
                    double n7;
                    double n6 = n7 = 1.0;
                    double n8 = 0.0;
                    int l;
                    for (l = k - 1; l >= j; --l) {
                        final double n9 = n7 * array2[l];
                        final double n10 = n6 * array2[l];
                        pythag = (array2[l + 1] = this.pythag(n9, n5));
                        if (pythag == 0.0) {
                            final int n11 = l + 1;
                            array[n11] -= n8;
                            array2[k] = 0.0;
                            break;
                        }
                        n7 = n9 / pythag;
                        n6 = n5 / pythag;
                        final double n12 = array[l + 1] - n8;
                        pythag = (array[l] - n12) * n7 + 2.0 * n6 * n10;
                        array[l + 1] = n12 + (n8 = n7 * pythag);
                        n5 = n6 * pythag - n10;
                        for (int n13 = 1; n13 <= n; ++n13) {
                            final double n14 = array3[n13][l + 1];
                            array3[n13][l + 1] = n7 * array3[n13][l] + n6 * n14;
                            array3[n13][l] = n6 * array3[n13][l] - n7 * n14;
                        }
                    }
                    if (pythag == 0.0 && l >= j) {
                        continue;
                    }
                    final int n15 = j;
                    array[n15] -= n8;
                    array2[j] = n5;
                    array2[k] = 0.0;
                }
            } while (k != j);
        }
    }
    
    double SIGN(final double n, final double n2) {
        return (n2 >= 0.0) ? Math.abs(n) : (-Math.abs(n));
    }
    
    double SQR(final double n) {
        return n * n;
    }
    
    double pythag(final double n, final double n2) {
        final double abs = Math.abs(n);
        final double abs2 = Math.abs(n2);
        if (abs > abs2) {
            return abs * Math.sqrt(1.0 + this.SQR(abs2 / abs));
        }
        return (abs2 == 0.0) ? 0.0 : (abs2 * Math.sqrt(1.0 + this.SQR(abs / abs2)));
    }
    
    int getFreq(final int n) {
        return (int)(Math.exp(this.baseFreqBar.getValue() * (Math.log(2.0) / 12.0)) * this.omega[n]);
    }
    
    void doPlay() {
        final byte[] array = new byte[8000];
        final double n = 6.283185307179586 * Math.exp(this.baseFreqBar.getValue() * (Math.log(2.0) / 12.0)) / 8000.0 / this.omega[0];
        double n2;
        int modeCount;
        for (n2 = 3.141592653589793 / n, modeCount = this.modeCount; modeCount > 0 && this.omega[modeCount - 1] > n2; --modeCount) {}
        if (modeCount == 0) {
            return;
        }
        int n3 = 0;
        for (double n4 = 125.66370614359172 / (8000.0 * n); n3 < modeCount && this.omega[n3] < n4; ++n3) {}
        if (n3 == modeCount) {
            return;
        }
        final int n5 = 200;
        int n6 = 0;
        final double n7 = 1000.0;
        final double[] array2 = new double[this.modeCount];
        for (int i = 0; i != this.modeCount; ++i) {
            array2[i] = this.magcoef[i];
        }
        while (true) {
            boolean b = false;
            double n8 = (-this.sndmin > this.sndmax) ? (-this.sndmin) : this.sndmax;
            if (n8 < 0.02) {
                n8 = 0.02;
            }
            double n9 = 126.0 / n8;
            if (n9 > n7) {
                n9 = n7;
            }
            final double n10 = 0.0;
            this.sndmax = n10;
            this.sndmin = n10;
            for (int j = 0; j != n5; ++j) {
                double n11 = 0.0;
                final int n12 = j + n6;
                for (int k = n3; k != modeCount; ++k) {
                    n11 += array2[k] * Math.sin(n12 * n * this.omega[k]) * n9;
                }
                if (n11 < this.sndmin) {
                    this.sndmin = n11;
                }
                if (n11 > this.sndmax) {
                    this.sndmax = n11;
                }
                if (n11 < -127.0 || n11 > 127.0) {
                    b = true;
                }
                else {
                    array[n12] = (byte)BarWavesFrame.to_ulaw[128 + (int)n11];
                }
            }
            this.sndmin /= n9;
            this.sndmax /= n9;
            if (b) {
                continue;
            }
            n6 += n5;
            for (int l = 0; l != this.modeCount; ++l) {
                final double[] array3 = array2;
                final int n13 = l;
                array3[n13] *= this.dampcoef[l];
            }
            if (n6 >= 8000) {
                break;
            }
        }
        AudioPlayer.player.start(new AudioDataStream(new AudioData(array)));
        this.cv.repaint();
    }
    
    static {
        to_ulaw = new int[] { 0, 0, 0, 0, 0, 1, 1, 1, 1, 2, 2, 2, 2, 3, 3, 3, 3, 4, 4, 4, 4, 5, 5, 5, 5, 6, 6, 6, 6, 7, 7, 7, 7, 8, 8, 8, 8, 9, 9, 9, 9, 10, 10, 10, 10, 11, 11, 11, 11, 12, 12, 12, 12, 13, 13, 13, 13, 14, 14, 14, 14, 15, 15, 15, 15, 16, 16, 17, 17, 18, 18, 19, 19, 20, 20, 21, 21, 22, 22, 23, 23, 24, 24, 25, 25, 26, 26, 27, 27, 28, 28, 29, 29, 30, 30, 31, 31, 32, 33, 34, 35, 36, 37, 38, 39, 40, 41, 42, 43, 44, 45, 46, 47, 49, 51, 53, 55, 57, 59, 61, 63, 66, 70, 74, 78, 84, 92, 104, 254, 231, 219, 211, 205, 201, 197, 193, 190, 188, 186, 184, 182, 180, 178, 176, 175, 174, 173, 172, 171, 170, 169, 168, 167, 166, 165, 164, 163, 162, 161, 160, 159, 159, 158, 158, 157, 157, 156, 156, 155, 155, 154, 154, 153, 153, 152, 152, 151, 151, 150, 150, 149, 149, 148, 148, 147, 147, 146, 146, 145, 145, 144, 144, 143, 143, 143, 143, 142, 142, 142, 142, 141, 141, 141, 141, 140, 140, 140, 140, 139, 139, 139, 139, 138, 138, 138, 138, 137, 137, 137, 137, 136, 136, 136, 136, 135, 135, 135, 135, 134, 134, 134, 134, 133, 133, 133, 133, 132, 132, 132, 132, 131, 131, 131, 131, 130, 130, 130, 130, 129, 129, 129, 129, 128, 128, 128, 128 };
    }
    
    abstract class Setup
    {
        abstract String getName();
        
        abstract Setup createNext();
        
        abstract int leftBoundary();
        
        abstract int rightBoundary();
        
        int getThickness() {
            return 3;
        }
        
        void doMatrixStep(final double[][] array, final int n, final int n2) {
            BarWavesFrame.this.dodiff(array, n, n, 4, 1.0);
        }
    }
    
    class FreeBarSetup extends Setup
    {
        String getName() {
            return "bar, free";
        }
        
        Setup createNext() {
            return new HingedBarSetup();
        }
        
        int leftBoundary() {
            return 1;
        }
        
        int rightBoundary() {
            return 1;
        }
    }
    
    class HingedBarSetup extends Setup
    {
        String getName() {
            return "bar, hinged";
        }
        
        Setup createNext() {
            return new ClampedBarSetup();
        }
        
        int leftBoundary() {
            return 0;
        }
        
        int rightBoundary() {
            return 0;
        }
    }
    
    class ClampedBarSetup extends Setup
    {
        String getName() {
            return "bar, clamped";
        }
        
        Setup createNext() {
            return new ClampedFreeBarSetup();
        }
        
        int leftBoundary() {
            return 2;
        }
        
        int rightBoundary() {
            return 2;
        }
    }
    
    class ClampedFreeBarSetup extends Setup
    {
        String getName() {
            return "bar, clamped/free";
        }
        
        Setup createNext() {
            return new HingedClampedBarSetup();
        }
        
        int leftBoundary() {
            return 2;
        }
        
        int rightBoundary() {
            return 1;
        }
    }
    
    class HingedClampedBarSetup extends Setup
    {
        String getName() {
            return "bar, hinged/clamped";
        }
        
        Setup createNext() {
            return new StringSetup();
        }
        
        int leftBoundary() {
            return 0;
        }
        
        int rightBoundary() {
            return 2;
        }
    }
    
    class StringSetup extends Setup
    {
        String getName() {
            return "string, pinned";
        }
        
        void doMatrixStep(final double[][] array, final int n, final int n2) {
            BarWavesFrame.this.dodiff(array, n, n, 2, 1.0);
        }
        
        Setup createNext() {
            return new String1FreeSetup();
        }
        
        int leftBoundary() {
            return 0;
        }
        
        int rightBoundary() {
            return 0;
        }
        
        int getThickness() {
            return 0;
        }
    }
    
    class String1FreeSetup extends StringSetup
    {
        String getName() {
            return "string, pinned/free";
        }
        
        Setup createNext() {
            return new String2FreeSetup();
        }
        
        int rightBoundary() {
            return 1;
        }
    }
    
    class String2FreeSetup extends String1FreeSetup
    {
        String getName() {
            return "string, free/free";
        }
        
        Setup createNext() {
            return new StiffStringSetup();
        }
        
        int leftBoundary() {
            return 1;
        }
    }
    
    class StiffStringSetup extends StringSetup
    {
        String getName() {
            return "stiff string, pinned";
        }
        
        void doMatrixStep(final double[][] array, final int n, final int n2) {
            BarWavesFrame.this.dodiff(array, n, n, 2, 1.0);
            BarWavesFrame.this.dodiff(array, n, n, 4, BarWavesFrame.this.stiffnessBar.getValue() * 0.1);
        }
        
        Setup createNext() {
            return new StiffStringClampedSetup();
        }
    }
    
    class StiffStringClampedSetup extends StiffStringSetup
    {
        String getName() {
            return "stiff string, clamped";
        }
        
        Setup createNext() {
            return null;
        }
        
        int leftBoundary() {
            return 2;
        }
        
        int rightBoundary() {
            return 2;
        }
    }
}
