import java.lang.reflect.Method;
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

class StringWaveFrame extends Frame implements ComponentListener, ActionListener, AdjustmentListener, MouseMotionListener, MouseListener, ItemListener
{
    PlayThread playThread;
    Dimension winSize;
    Image dbimage;
    Random random;
    int maxTerms;
    int maxMaxTerms;
    int sampleCount;
    double[][] sinTable;
    public static final double epsilon = 1.0E-5;
    public static final double epsilon2 = 0.003;
    Button sineButton;
    Button triangleButton;
    Button blankButton;
    Button resonanceButton;
    Checkbox stoppedCheck;
    Checkbox forceCheck;
    Checkbox soundCheck;
    Checkbox touchCheck;
    Checkbox backwardsCheck;
    Checkbox logCheck;
    Choice modeChooser;
    Choice displayChooser;
    Scrollbar dampingBar;
    Scrollbar speedBar;
    Scrollbar forceBar;
    Scrollbar loadBar;
    Scrollbar tensionBar;
    double[] magcoef;
    double dampcoef;
    double[] phasecoef;
    double[] phasecoefcos;
    double[] phasecoefadj;
    double[] forcebasiscoef;
    double[] omega;
    static final double pi = 3.141592653589793;
    double step;
    double[] func;
    double[] funci;
    int selectedCoef;
    int magnitudesY;
    static final int SEL_NONE = 0;
    static final int SEL_FUNC = 1;
    static final int SEL_MAG = 2;
    static final int MODE_PLUCK = 0;
    static final int MODE_SHAPE = 1;
    static final int MODE_TOUCH = 997;
    static final int MODE_FORCE = 998;
    static final int MODE_BOW = 999;
    static final int DISP_PHASE = 0;
    static final int DISP_LEFTRIGHT = 1;
    static final int DISP_PHASECOS = 2;
    static final int DISP_PHASORS = 3;
    static final int DISP_MODES = 4;
    int selection;
    int dragX;
    int dragY;
    boolean dragging;
    boolean bowing;
    boolean bowCaught;
    boolean forceApplied;
    double t;
    double forceMag;
    int pause;
    int forceBarValue;
    double forceTimeZero;
    int tensionBarValue;
    Color gray1;
    Color gray2;
    StringWaveCanvas cv;
    StringWave applet;
    boolean java2;
    long lastTime;
    double logep2;
    static /* synthetic */ Class class$java$lang$Class;
    
    public String getAppletInfo() {
        return "StringWave Series by Paul Falstad";
    }
    
    int getrand(final int n) {
        int nextInt = this.random.nextInt();
        if (nextInt < 0) {
            nextInt = -nextInt;
        }
        return nextInt % n;
    }
    
    StringWaveFrame(final StringWave applet) {
        super("Loaded String Applet v1.5");
        this.maxTerms = 60;
        this.maxMaxTerms = 160;
        this.gray1 = new Color(76, 76, 76);
        this.gray2 = new Color(127, 127, 127);
        this.logep2 = 0.0;
        this.applet = applet;
    }
    
    public void init() {
        if (new Double(System.getProperty("java.class.version")) >= 48.0) {
            this.java2 = true;
        }
        this.selectedCoef = -1;
        this.setLayout(new StringWaveLayout());
        (this.cv = new StringWaveCanvas(this)).addComponentListener(this);
        this.cv.addMouseMotionListener(this);
        this.cv.addMouseListener(this);
        this.add(this.cv);
        this.add(this.sineButton = new Button("Fundamental"));
        this.sineButton.addActionListener(this);
        this.add(this.triangleButton = new Button("Center Pluck"));
        this.triangleButton.addActionListener(this);
        this.add(this.blankButton = new Button("Clear"));
        this.blankButton.addActionListener(this);
        (this.stoppedCheck = new Checkbox("Stopped")).addItemListener(this);
        this.add(this.stoppedCheck);
        (this.forceCheck = new Checkbox("Driving Force", false)).addItemListener(this);
        this.add(this.forceCheck);
        (this.soundCheck = new Checkbox("Sound", false)).addItemListener(this);
        if (this.java2) {
            this.add(this.soundCheck);
        }
        (this.touchCheck = new Checkbox("Touched in Center", false)).addItemListener(this);
        (this.backwardsCheck = new Checkbox("Run Backwards", false)).addItemListener(this);
        (this.logCheck = new Checkbox("Log View", false)).addItemListener(this);
        this.add(this.logCheck);
        (this.modeChooser = new Choice()).add("Mouse = Pluck string");
        this.modeChooser.add("Mouse = Shape string");
        this.modeChooser.addItemListener(this);
        this.add(this.modeChooser);
        (this.displayChooser = new Choice()).add("Display Phases");
        this.displayChooser.add("Display Left+Right");
        this.displayChooser.add("Display Phase Cosines");
        this.displayChooser.add("Display Phasors");
        this.displayChooser.add("Display Modes");
        this.displayChooser.addItemListener(this);
        this.add(this.displayChooser);
        this.add(new Label("Simulation Speed", 1));
        this.add(this.speedBar = new Scrollbar(0, 85, 1, 1, 200));
        this.speedBar.addAdjustmentListener(this);
        this.add(new Label("Damping", 1));
        this.add(this.dampingBar = new Scrollbar(0, 10, 1, 0, 400));
        this.dampingBar.addAdjustmentListener(this);
        this.add(new Label("Force Frequency", 1));
        this.forceBarValue = 5;
        this.add(this.forceBar = new Scrollbar(0, this.forceBarValue, 1, 1, 30));
        this.forceBar.addAdjustmentListener(this);
        this.add(this.resonanceButton = new Button("Resonance Freq"));
        this.resonanceButton.addActionListener(this);
        this.add(new Label("Number of Loads", 1));
        this.add(this.loadBar = new Scrollbar(0, this.maxTerms, 1, 2, this.maxMaxTerms));
        this.loadBar.addAdjustmentListener(this);
        this.setLoadCount();
        this.tensionBarValue = 16;
        this.add(new Label("Tension", 1));
        this.add(this.tensionBar = new Scrollbar(0, this.tensionBarValue, 1, 1, 100));
        this.tensionBar.addAdjustmentListener(this);
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
        this.forcebasiscoef = new double[this.maxMaxTerms];
        this.func = new double[this.maxMaxTerms + 1];
        this.funci = new double[this.maxMaxTerms + 1];
        this.random = new Random();
        this.setDamping();
        this.reinit();
        this.cv.setBackground(Color.black);
        this.cv.setForeground(Color.lightGray);
        this.resize(800, 600);
        this.handleResize();
        final Dimension size = this.getSize();
        final Dimension screenSize = this.getToolkit().getScreenSize();
        this.setLocation((screenSize.width - size.width) / 2, (screenSize.height - size.height) / 2);
        this.show();
    }
    
    void reinit() {
        this.doSine();
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
    
    void doSine() {
        for (int i = 0; i != this.sampleCount; ++i) {
            this.func[i] = Math.sin(i * this.step);
        }
        this.func[this.sampleCount] = this.func[0];
        this.transform(true);
    }
    
    void doTriangle() {
        for (int i = 0; i <= this.sampleCount / 2; ++i) {
            this.func[this.sampleCount - i] = (this.func[i] = 2.0 * i / this.sampleCount);
        }
        this.func[this.sampleCount] = this.func[0];
        this.transform(true);
    }
    
    void doBlank() {
        for (int i = 0; i <= this.sampleCount; ++i) {
            this.func[i] = 0.0;
        }
        this.transform(true);
    }
    
    void transform(final boolean b) {
        this.t = 0.0;
        for (int i = 1; i != this.maxTerms; ++i) {
            double n = 0.0;
            double n2 = 0.0;
            for (int j = 1; j != this.sampleCount; ++j) {
                n += this.sinTable[j][i] * this.func[j];
                n2 -= this.sinTable[j][i] * this.funci[j];
            }
            double n3 = n * (2.0 / this.sampleCount);
            double n4 = n2 * (2.0 / (this.sampleCount * this.omega[i]));
            if (n3 < 1.0E-5 && n3 > -1.0E-5) {
                n3 = 0.0;
            }
            if (n4 < 1.0E-5 && n4 > -1.0E-5) {
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
        this.updateSound();
    }
    
    void updateSound() {
        if (this.playThread != null) {
            this.playThread.soundChanged();
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
    
    public void updateStringWave(final Graphics graphics) {
        if (this.winSize == null || this.winSize.width == 0) {
            return;
        }
        final Graphics graphics2 = this.dbimage.getGraphics();
        boolean b = true;
        double exp = 1.0;
        if (!this.stoppedCheck.getState()) {
            if (this.bowing) {
                this.doBow();
                b = false;
            }
            final int value = this.speedBar.getValue();
            if (this.forceCheck.getState()) {
                this.doForce();
                b = false;
            }
            else {
                this.forceMag = 0.0;
            }
            final long currentTimeMillis = System.currentTimeMillis();
            double n = 0.0;
            if (this.lastTime != 0L) {
                n = Math.exp(value / 20.0) * 6.666666666666667E-5 * (currentTimeMillis - this.lastTime);
            }
            if (this.backwardsCheck.getState()) {
                this.t -= n;
            }
            else {
                this.t += n;
            }
            this.lastTime = currentTimeMillis;
            exp = Math.exp(this.dampcoef * n);
        }
        else {
            this.lastTime = 0L;
        }
        graphics2.setColor(this.cv.getBackground());
        graphics2.fillRect(0, 0, this.winSize.width, this.winSize.height);
        graphics2.setColor(this.cv.getForeground());
        int n2 = -1;
        int n3 = -1;
        final int panelHeight = this.getPanelHeight();
        final int n4 = panelHeight / 2;
        final int n5 = panelHeight / 2;
        final double n6 = 0.75 * n5;
        for (int i = -1; i <= 1; ++i) {
            graphics2.setColor((i == 0) ? this.gray2 : this.gray1);
            graphics2.drawLine(0, n4 + i * (int)n6, this.winSize.width, n4 + i * (int)n6);
        }
        graphics2.setColor(this.gray2);
        graphics2.drawLine(this.winSize.width / 2, n4 - (int)n6, this.winSize.width / 2, n4 + (int)n6);
        if (this.dragging && this.selection == 1) {
            graphics2.setColor(Color.cyan);
            b = true;
            for (int j = 0; j != this.sampleCount + 1; ++j) {
                final int n7 = this.winSize.width * j / this.sampleCount;
                final int n8 = n4 - (int)(n6 * this.func[j]);
                if (n2 != -1) {
                    graphics2.drawLine(n2, n3, n7, n8);
                }
                n2 = n7;
                n3 = n8;
            }
        }
        if (!this.stoppedCheck.getState()) {
            if (this.touchCheck.getState()) {
                this.doTouch();
            }
            for (int k = 1; k != this.maxTerms; ++k) {
                final double[] magcoef = this.magcoef;
                final int n9 = k;
                magcoef[n9] *= exp;
            }
        }
        double[] magcoef2 = this.magcoef;
        double[] phasecoef = this.phasecoef;
        double[] phasecoefcos = this.phasecoefcos;
        if (this.dragging && this.selection == 1) {
            this.lastTime = 0L;
        }
        else {
            graphics2.setColor(Color.white);
            int n10 = -1;
            for (int l = 1; l != this.maxTerms; ++l) {
                if (this.magcoef[l] < 1.0E-5 && this.magcoef[l] > -1.0E-5) {
                    final double[] magcoef3 = this.magcoef;
                    final int n11 = l;
                    final double[] phasecoef2 = this.phasecoef;
                    final int n12 = l;
                    final double[] phasecoefadj = this.phasecoefadj;
                    final int n13 = l;
                    final double n14 = 0.0;
                    phasecoefadj[n13] = n14;
                    magcoef3[n11] = (phasecoef2[n12] = n14);
                }
                else {
                    b = false;
                    this.phasecoef[l] = (this.omega[l] * this.t + this.phasecoefadj[l]) % 6.283185307179586;
                    if (this.phasecoef[l] > 3.141592653589793) {
                        final double[] phasecoef3 = this.phasecoef;
                        final int n15 = l;
                        phasecoef3[n15] -= 6.283185307179586;
                    }
                    else if (this.phasecoef[l] < -3.141592653589793) {
                        final double[] phasecoef4 = this.phasecoef;
                        final int n16 = l;
                        phasecoef4[n16] += 6.283185307179586;
                    }
                    this.phasecoefcos[l] = Math.cos(this.phasecoef[l]);
                }
            }
            if (this.forceApplied) {
                b = false;
                magcoef2 = new double[this.maxTerms];
                phasecoef = new double[this.maxTerms];
                phasecoefcos = new double[this.maxTerms];
                for (int n17 = 1; n17 < this.maxTerms; ++n17) {
                    final double n18 = this.phasecoef[n17];
                    final double n19 = this.magcoef[n17] * this.phasecoefcos[n17];
                    final double n20 = this.magcoef[n17] * Math.sin(n18);
                    final double n21 = n19 + this.forcebasiscoef[n17];
                    final double sqrt = Math.sqrt(n21 * n21 + n20 * n20);
                    magcoef2[n17] = sqrt;
                    final double atan2 = Math.atan2(n20, n21);
                    final double[] array = phasecoef;
                    final int n22 = n17;
                    array[n22] += atan2;
                    phasecoefcos[n17] = ((sqrt > 0.0) ? (n21 / sqrt) : 0.0);
                }
            }
            final int n23 = (this.sampleCount < 40) ? 5 : 0;
            final int n24 = (this.forceMag == 0.0) ? -1 : (this.sampleCount / 2);
            for (int n25 = 0; n25 != this.sampleCount + 1; ++n25) {
                final int n26 = this.winSize.width * n25 / this.sampleCount;
                double n27 = 0.0;
                for (int n28 = 1; n28 != this.maxTerms; ++n28) {
                    n27 += magcoef2[n28] * this.sinTable[n25][n28] * phasecoefcos[n28];
                }
                this.func[n25] = n27;
                final int n29 = n4 - (int)(n6 * n27);
                if (n10 != -1) {
                    graphics2.drawLine(n10, n3, n26, n29);
                }
                if (n23 > 0 && n25 != 0 && n25 != this.sampleCount) {
                    graphics2.fillOval(n26 - n23 / 2, n29 - n23 / 2, n23, n23);
                }
                if (n25 == n24) {
                    final int n30 = (int)(n6 * this.forceMag * 8.0);
                    if (n30 > 7 || n30 < -7) {
                        final int n31 = n29 - n30;
                        final int n32 = (this.forceMag < 0.0) ? -1 : 1;
                        graphics2.drawLine(n26, n29, n26, n31);
                        graphics2.drawLine(n26, n31, n26 + 5, n31 + 5 * n32);
                        graphics2.drawLine(n26, n31, n26 - 5, n31 + 5 * n32);
                    }
                }
                n10 = n26;
                n3 = n29;
            }
        }
        if (this.selectedCoef != -1 && !this.dragging && (magcoef2[this.selectedCoef] > 0.04 || magcoef2[this.selectedCoef] < -0.04)) {
            graphics2.setColor(Color.yellow);
            int n33 = -1;
            final double n34 = n6 * magcoef2[this.selectedCoef];
            for (int n35 = 0; n35 != this.sampleCount + 1; ++n35) {
                final int n36 = this.winSize.width * n35 / this.sampleCount;
                final int n37 = n4 - (int)(n34 * (this.sinTable[n35][this.selectedCoef] * phasecoefcos[this.selectedCoef]));
                if (n33 != -1) {
                    graphics2.drawLine(n33, n3, n36, n37);
                }
                n33 = n36;
                n3 = n37;
            }
        }
        final int termWidth = this.getTermWidth();
        final double n38 = 0.6 * n5;
        graphics2.setColor(Color.white);
        if (this.displayChooser.getSelectedIndex() == 0 || this.displayChooser.getSelectedIndex() == 2) {
            this.magnitudesY = panelHeight;
        }
        else {
            this.magnitudesY = panelHeight * 2;
        }
        final int n39 = this.magnitudesY + panelHeight / 2 + (int)n38 / 2;
        this.centerString(graphics2, "Harmonics: Magnitudes", this.magnitudesY + (int)(panelHeight * 0.16));
        graphics2.setColor(this.gray2);
        graphics2.drawLine(0, n39, this.winSize.width, n39);
        graphics2.setColor(this.gray1);
        graphics2.drawLine(0, n39 - (int)n38, this.winSize.width, n39 - (int)n38);
        graphics2.drawLine(0, n39 + (int)n38, this.winSize.width, n39 + (int)n38);
        int n40 = termWidth - 3;
        if (n40 < 3) {
            n40 = 3;
        }
        if (n40 > 9) {
            n40 = 9;
        }
        for (int n41 = 1; n41 != this.maxTerms; ++n41) {
            final int n42 = termWidth * (n41 - 1) + termWidth / 2;
            final int n43 = n39 - (int)(this.logcoef(magcoef2[n41]) * n38);
            graphics2.setColor((n41 == this.selectedCoef) ? Color.yellow : Color.white);
            graphics2.drawLine(n42, n39, n42, n43);
            graphics2.fillOval(n42 - n40 / 2, n43 - n40 / 2, n40, n40);
        }
        if (this.displayChooser.getSelectedIndex() == 0 || this.displayChooser.getSelectedIndex() == 2) {
            graphics2.setColor(Color.white);
            final boolean b2 = this.displayChooser.getSelectedIndex() == 2;
            this.centerString(graphics2, b2 ? "Harmonics: Phase Cosines" : "Harmonics: Phases", (int)(panelHeight * 2.1));
            double n44 = 0.75 * n5;
            final int n45 = panelHeight * 5 / 2;
            for (int n46 = -2; n46 <= 2; ++n46) {
                if (b2) {
                    if (n46 == 1) {
                        continue;
                    }
                    if (n46 == -1) {
                        continue;
                    }
                }
                graphics2.setColor((n46 == 0) ? this.gray2 : this.gray1);
                graphics2.drawLine(0, n45 + n46 * (int)n44 / 2, this.winSize.width, n45 + n46 * (int)n44 / 2);
            }
            if (!b2) {
                n44 /= 3.141592653589793;
            }
            for (int n47 = 1; n47 != this.maxTerms; ++n47) {
                final int n48 = termWidth * (n47 - 1) + termWidth / 2;
                double n49 = b2 ? phasecoefcos[n47] : phasecoef[n47];
                if (this.magcoef[n47] > -7.5E-4 && magcoef2[n47] < 7.5E-4) {
                    n49 = 0.0;
                }
                final int n50 = n45 - (int)(n49 * n44);
                graphics2.setColor((n47 == this.selectedCoef) ? Color.yellow : Color.white);
                graphics2.drawLine(n48, n45, n48, n50);
                graphics2.fillOval(n48 - n40 / 2, n50 - n40 / 2, n40, n40);
            }
        }
        else if (this.displayChooser.getSelectedIndex() == 1) {
            final int n51 = panelHeight + panelHeight / 2;
            final double n52 = 0.75 * (panelHeight / 2);
            for (int n53 = -1; n53 <= 1; ++n53) {
                graphics2.setColor((n53 == 0) ? this.gray2 : this.gray1);
                graphics2.drawLine(0, n51 + n53 * (int)n52, this.winSize.width, n51 + n53 * (int)n52);
            }
            graphics2.setColor(this.gray2);
            graphics2.drawLine(this.winSize.width / 2, n51 - (int)n52, this.winSize.width / 2, n51 + (int)n52);
            int n54 = -1;
            int n55 = -1;
            for (int n56 = 4, n57 = 0; n57 != this.sampleCount * n56 + 1; ++n57) {
                final int n58 = this.winSize.width * n57 / (n56 * this.sampleCount);
                double n59 = 0.0;
                double n60 = 0.0;
                final double n61 = this.step * n57 / n56;
                for (int n62 = 1; n62 != this.maxTerms; ++n62) {
                    if (magcoef2[n62] != 0.0) {
                        final double n63 = n61 * n62;
                        final double n64 = magcoef2[n62] * 0.5;
                        final double n65 = phasecoef[n62];
                        n59 += n64 * Math.sin(n63 + n65);
                        n60 += n64 * Math.sin(n63 - n65);
                    }
                }
                final int n66 = n51 - (int)(n52 * n59);
                final int n67 = n51 - (int)(n52 * n60);
                if (n54 != -1) {
                    graphics2.setColor(Color.cyan);
                    graphics2.drawLine(n54, n3, n58, n66);
                    graphics2.setColor(Color.green);
                    graphics2.drawLine(n54, n55, n58, n67);
                }
                n54 = n58;
                n3 = n66;
                n55 = n67;
            }
        }
        else if (this.displayChooser.getSelectedIndex() == 3) {
            final int n69;
            final int n68 = n69 = (this.winSize.width - 25) / 3;
            final int n70 = panelHeight + (panelHeight - n69) / 2;
            final int n71 = 5;
            for (int n72 = 1; n72 <= 3; ++n72) {
                graphics2.setColor(this.gray2);
                final int n73 = (n68 + 10) * (n72 - 1);
                final int n74 = n73 + n68 / 2;
                final int n75 = n70 + n69 / 2;
                graphics2.drawLine(n73, n75, n73 + n68, n75);
                graphics2.drawLine(n74, n70, n74, n70 + n69);
                graphics2.setColor(this.gray1);
                graphics2.drawOval(n74 - n68 / 2, n75 - n69 / 2, n68, n69);
                graphics2.setColor((n72 == this.selectedCoef) ? Color.yellow : Color.white);
                graphics2.drawRect(n73, n70, n68, n69);
                final boolean b3 = this.forceApplied || this.forceCheck.getState();
                final int n76 = (int)(phasecoefcos[n72] * magcoef2[n72] * n68 * 0.5);
                final int n77 = (int)(Math.sin(phasecoef[n72]) * magcoef2[n72] * n69 * 0.5);
                graphics2.drawLine(n74 + (b3 ? ((int)(this.forcebasiscoef[n72] * n68 * 0.5)) : 0), n75, n74 + n76, n75 - n77);
                graphics2.fillOval(n74 + n76 - n71 / 2, n75 - n77 - n71 / 2, n71, n71);
            }
        }
        else if (this.displayChooser.getSelectedIndex() == 4) {
            final int n78 = (this.winSize.width - 25) / 3;
            final int n79 = (int)(n78 / 3.141592653589793);
            int n80 = panelHeight;
            int n81 = 0;
            for (int n82 = 1; n82 < this.sampleCount; ++n82) {
                if (magcoef2[n82] > 0.06 || magcoef2[n82] < -0.06) {
                    graphics2.setColor(this.gray2);
                    final int n83 = n81 + n78 / 2;
                    final int n84 = n80 + n79 / 2;
                    graphics2.drawLine(n81, n84, n81 + n78, n84);
                    graphics2.drawLine(n83, n80, n83, n80 + n79);
                    graphics2.setColor((n82 == this.selectedCoef) ? Color.yellow : Color.white);
                    graphics2.drawRect(n81, n80, n78, n79);
                    int n85 = -1;
                    final double n86 = n79 * 0.5 * magcoef2[n82];
                    for (int n87 = 0; n87 != this.sampleCount + 1; ++n87) {
                        final int n88 = n81 + n78 * n87 / this.sampleCount;
                        final int n89 = n84 - (int)(n86 * (this.sinTable[n87][n82] * phasecoefcos[n82]));
                        if (n85 != -1) {
                            graphics2.drawLine(n85, n3, n88, n89);
                        }
                        n85 = n88;
                        n3 = n89;
                    }
                    n81 += n78 + 10;
                    if (n81 + n78 > this.winSize.width) {
                        n81 = 0;
                        n80 += n79 + 10;
                        if (n80 + n79 > panelHeight * 2) {
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
    
    int getTermWidth() {
        int n = this.winSize.width / this.maxTerms;
        if (n < 2) {
            n = 2;
        }
        final int n2 = this.winSize.width / 30;
        if (n > n2) {
            n = n2;
        }
        return n & 0xFFFFFFFE;
    }
    
    void getVelocities() {
        for (int i = 0; i != this.sampleCount; ++i) {
            double n = 0.0;
            for (int j = 0; j != this.sampleCount; ++j) {
                n += this.magcoef[j] * this.sinTable[i][j] * Math.sin(this.phasecoef[j]) * this.omega[j];
            }
            this.funci[i] = -n;
        }
    }
    
    void setForce() {
        final double n = this.forceBarValue * this.omega[1] / 20.0;
        this.forceBarValue = this.forceBar.getValue();
        this.forceTimeZero = this.t - n * (this.t - this.forceTimeZero) / (this.forceBarValue * this.omega[1] / 20.0);
    }
    
    void doForce() {
        this.forceMag = Math.cos((this.t - this.forceTimeZero) * (this.forceBar.getValue() * this.omega[1] / 20.0)) * 0.06;
        if (this.forceBar.getValue() == 1) {
            this.forceMag *= 2.0;
        }
        this.applyForce(this.maxTerms / 2, this.forceMag);
    }
    
    void doTouch() {
        final int n = this.sampleCount / 2;
        final double n2 = 0.1;
        final double n3 = this.func[n];
        double n4;
        if (n3 > n2) {
            n4 = -(n3 - n2);
        }
        else {
            if (n3 >= -n2) {
                return;
            }
            n4 = -(n3 + n2);
        }
        for (int i = 1; i != this.maxTerms; ++i) {
            double n5 = 0.0;
            for (int j = 1; j != this.sampleCount; ++j) {
                n5 += this.sinTable[j][i] * ((j <= n) ? (n4 * j / n) : (n4 * (this.sampleCount - j) / (this.sampleCount - n)));
            }
            final double n6 = n5 * (2.0 / this.sampleCount);
            final double n7 = this.phasecoefadj[i] + this.omega[i] * this.t;
            final double n8 = this.magcoef[i] * Math.cos(n7);
            final double n9 = this.magcoef[i] * Math.sin(n7);
            final double n10 = n8 + n6;
            this.magcoef[i] = Math.sqrt(n10 * n10 + n9 * n9);
            final double atan2 = Math.atan2(n9, n10);
            final double[] phasecoefadj = this.phasecoefadj;
            final int n11 = i;
            phasecoefadj[n11] += atan2 - n7;
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
        this.updateSound();
        this.cv.repaint(this.pause);
    }
    
    void editFunc(int i, int n) {
        if (this.modeChooser.getSelectedIndex() == 0) {
            this.editFuncPluck(i, n);
            return;
        }
        if (this.modeChooser.getSelectedIndex() == 997) {
            this.editFuncTouch(i, n);
            return;
        }
        if (this.modeChooser.getSelectedIndex() == 999) {
            this.editFuncBow(i, n);
            return;
        }
        if (this.modeChooser.getSelectedIndex() == 998) {
            this.forceCheck.setState(false);
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
    
    void editFuncTouch(final int n, final int n2) {
        this.dragging = false;
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
        if (n7 < -1.0) {}
        if (n6 < 1 || n6 >= this.sampleCount) {
            return;
        }
        for (int i = 1; i != this.maxTerms; ++i) {
            double n8 = this.sinTable[n6][i];
            if (n8 < 0.0) {
                n8 = -n8;
            }
            double n9 = this.magcoef[i] * n8;
            if (n9 < 0.0) {
                n9 = -n9;
            }
            final double n10 = 0.02;
            if (n9 >= n10) {
                this.magcoef[i] = ((this.magcoef[i] < 0.0) ? -1 : 1) * n10 / n8;
            }
        }
    }
    
    void editFuncForce(final int n, final int n2) {
        this.dragging = false;
        final int panelHeight = this.getPanelHeight();
        final int n3 = panelHeight / 2;
        final int n4 = panelHeight / 2;
        final int width = this.winSize.width;
        final double n5 = 0.75 * n4;
        final int n6 = n * this.sampleCount / width;
        if (n6 < 1 || n6 >= this.sampleCount) {
            return;
        }
        double n7 = (n3 - n2) / n5;
        if (n7 > 1.0) {
            n7 = 1.0;
        }
        if (n7 < -1.0) {
            n7 = -1.0;
        }
        this.soundCheck.setState(false);
        this.applyForce(n6, n7);
        this.cv.repaint(this.pause);
    }
    
    void applyForce(final int n, final double n2) {
        for (int i = 1; i != this.maxTerms; ++i) {
            double n3 = 0.0;
            for (int j = 1; j != this.sampleCount; ++j) {
                n3 += this.sinTable[j][i] * ((j <= n) ? (n2 * j / n) : (n2 * (this.sampleCount - j) / (this.sampleCount - n)));
            }
            final double n4 = n3 * (2.0 / this.sampleCount);
            final double n5 = this.phasecoefadj[i] + this.omega[i] * this.t;
            double n6 = this.magcoef[i] * this.phasecoefcos[i];
            final double n7 = this.magcoef[i] * Math.sin(n5);
            if (this.forceApplied) {
                n6 += this.forcebasiscoef[i];
            }
            final double n8 = n6 - n4;
            double sqrt = Math.sqrt(n8 * n8 + n7 * n7);
            if (sqrt > 2.0) {
                sqrt = 2.0;
            }
            this.magcoef[i] = sqrt;
            final double atan2 = Math.atan2(n7, n8);
            final double[] phasecoefadj = this.phasecoefadj;
            final int n9 = i;
            phasecoefadj[n9] += atan2 - n5;
            this.forcebasiscoef[i] = n4;
        }
        this.forceApplied = true;
    }
    
    void forceAppliedOff() {
        if (!this.forceApplied) {
            return;
        }
        this.forceApplied = false;
        for (int i = 1; i < this.maxTerms; ++i) {
            final double n = this.phasecoefadj[i] + this.omega[i] * this.t;
            final double n2 = this.magcoef[i] * Math.cos(n);
            final double n3 = this.magcoef[i] * Math.sin(n);
            final double n4 = n2 + this.forcebasiscoef[i];
            this.magcoef[i] = Math.sqrt(n4 * n4 + n3 * n3);
            final double atan2 = Math.atan2(n3, n4);
            final double[] phasecoefadj = this.phasecoefadj;
            final int n5 = i;
            phasecoefadj[n5] += atan2 - n;
        }
    }
    
    void editFuncBow(final int dragX, final int dragY) {
        this.dragging = false;
        this.bowing = true;
        this.dragX = dragX;
        this.dragY = dragY;
        this.bowCaught = true;
        this.cv.repaint(this.pause);
    }
    
    void doBow() {
        if (!this.bowCaught) {
            return;
        }
        final int panelHeight = this.getPanelHeight();
        final int n = panelHeight / 2;
        final int n2 = panelHeight / 2;
        final int width = this.winSize.width;
        final double n3 = 0.75 * n2;
        final int n4 = this.dragX * this.sampleCount / width;
        double n5 = (n - this.dragY) / n3;
        if (n5 < 0.0) {
            n5 = -n5;
        }
        final double n6 = 0.4;
        if (this.bowCaught && this.func[n4] > n5) {
            this.bowCaught = false;
            this.forceAppliedOff();
            return;
        }
        this.applyForce(n4, this.func[n4] + n6);
    }
    
    double logcoef(final double n) {
        if (!this.logCheck.getState()) {
            return n;
        }
        if (n == 0.0) {
            return n;
        }
        final int n2 = (n < 0.0) ? -1 : 1;
        final double n3 = 1.0 + Math.log(n * n2) * 0.1;
        if (n3 < 0.0) {
            return 0.0;
        }
        return n2 * n3;
    }
    
    double unlogcoef(final double n) {
        if (!this.logCheck.getState()) {
            return n;
        }
        if (n == 0.0) {
            return n;
        }
        final int n2 = (n < 0.0) ? -1 : 1;
        return Math.exp((n * n2 - 1.0) * 10.0) * n2;
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
            this.func[i] = n7;
            this.funci[i] = 0.0;
            ++i;
        }
        this.func[this.sampleCount] = this.func[0];
        this.cv.repaint(this.pause);
        if (!this.soundCheck.getState()) {
            this.transform(false);
        }
    }
    
    void editFuncPluck(final int n, final int n2) {
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
        for (int i = 0; i <= n6; ++i) {
            this.func[i] = n7 * i / n6;
        }
        final int n8 = this.sampleCount - n6;
        for (int j = n6 + 1; j < this.sampleCount; ++j) {
            this.func[j] = n7 * (this.sampleCount - j) / n8;
        }
        for (int k = 0; k <= this.sampleCount; ++k) {
            this.funci[k] = 0.0;
        }
        this.func[this.sampleCount] = this.func[0];
        this.cv.repaint(this.pause);
        if (!this.soundCheck.getState()) {
            this.transform(false);
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
        if (actionEvent.getSource() == this.triangleButton) {
            this.doTriangle();
            this.cv.repaint();
        }
        if (actionEvent.getSource() == this.sineButton) {
            this.doSine();
            this.cv.repaint();
        }
        if (actionEvent.getSource() == this.blankButton) {
            this.doBlank();
            this.cv.repaint();
        }
        if (actionEvent.getSource() == this.resonanceButton) {
            this.forceBar.setValue(20);
            this.setForce();
        }
    }
    
    public void adjustmentValueChanged(final AdjustmentEvent adjustmentEvent) {
        System.out.print(((Scrollbar)adjustmentEvent.getSource()).getValue() + "\n");
        if (adjustmentEvent.getSource() == this.dampingBar || adjustmentEvent.getSource() == this.speedBar) {
            this.setDamping();
        }
        if (adjustmentEvent.getSource() == this.loadBar) {
            this.setLoadCount();
            this.updateSound();
        }
        if (adjustmentEvent.getSource() == this.forceBar) {
            this.setForce();
        }
        if (adjustmentEvent.getSource() == this.tensionBar) {
            this.setTension();
            this.updateSound();
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
    
    void setTension() {
        final int tensionBarValue = this.tensionBarValue;
        this.tensionBarValue = this.tensionBar.getValue();
        final double sqrt = Math.sqrt(tensionBarValue / this.tensionBarValue);
        final double sqrt2 = Math.sqrt(this.tensionBarValue);
        for (int i = 1; i != this.maxTerms; ++i) {
            final double[] magcoef = this.magcoef;
            final int n = i;
            magcoef[n] *= sqrt;
            final double n2 = this.omega[i] * this.t;
            this.omega[i] = 5.0 * sqrt2 * Math.sin(i * (3.14159265 / (2 * (this.maxTerms + 1))));
            this.phasecoefadj[i] = (this.phasecoefadj[i] + n2 - this.omega[i] * this.t) % 6.283185307179586;
        }
    }
    
    void setLoadCount() {
        final int value = this.loadBar.getValue();
        this.maxTerms = value;
        this.sampleCount = value;
        this.step = 3.141592653589793 / this.sampleCount;
        this.sinTable = new double[this.sampleCount + 1][this.maxTerms];
        for (int i = 1; i != this.maxTerms; ++i) {
            for (int j = 0; j != this.sampleCount + 1; ++j) {
                this.sinTable[j][i] = Math.sin(this.step * j * i);
            }
        }
        this.omega = new double[this.maxTerms];
        for (int k = 1; k != this.maxTerms; ++k) {
            this.omega[k] = Math.sin(k * (3.14159265 / (2 * (this.maxTerms + 1))));
        }
        final double n = 1.0 / this.omega[1];
        for (int l = 1; l != this.maxTerms; ++l) {
            final double[] omega = this.omega;
            final int n2 = l;
            omega[n2] *= n;
        }
        this.setDamping();
    }
    
    void setDamping() {
        double exp = Math.exp(this.dampingBar.getValue() / 40 - 8);
        if (this.dampingBar.getValue() <= 2) {
            exp = 0.0;
        }
        this.dampcoef = -exp;
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
            this.selectedCoef = x / this.getTermWidth() + 1;
            if (this.selectedCoef >= this.maxTerms) {
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
            for (int i = 1; i != this.maxTerms; ++i) {
                if (this.selectedCoef != i) {
                    this.magcoef[i] = 0.0;
                }
            }
            this.magcoef[this.selectedCoef] = 1.0;
            this.updateSound();
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
        if (this.forceApplied || this.bowing) {
            final boolean b = false;
            this.bowCaught = b;
            this.bowing = b;
            this.forceAppliedOff();
        }
        else if (this.dragging && this.selection == 1) {
            this.transform(false);
        }
        this.dragging = false;
        this.cv.repaint(this.pause);
    }
    
    public void itemStateChanged(final ItemEvent itemEvent) {
        if (itemEvent.getItemSelectable() == this.stoppedCheck) {
            this.cv.repaint(this.pause);
            return;
        }
        if (itemEvent.getItemSelectable() == this.forceCheck) {
            this.forceTimeZero = this.t;
            this.cv.repaint(this.pause);
            this.forceAppliedOff();
            this.soundCheck.setState(false);
            return;
        }
        if (itemEvent.getItemSelectable() == this.soundCheck && this.soundCheck.getState() && this.playThread == null) {
            this.playThread = new PlayThread();
            this.speedBar.setValue(150);
            this.dampingBar.setValue(100);
            this.setDamping();
            this.playThread.start();
        }
        if (itemEvent.getItemSelectable() == this.displayChooser) {
            this.cv.repaint(this.pause);
        }
    }
    
    static /* synthetic */ Class class$(final String s) {
        try {
            return Class.forName(s);
        }
        catch (ClassNotFoundException ex) {
            throw new NoClassDefFoundError(ex.getMessage());
        }
    }
    
    class PlayThread extends Thread
    {
        boolean changed;
        final int rate = 22050;
        
        public void soundChanged() {
            this.changed = true;
        }
        
        public void run() {
            try {
                this.doRun();
            }
            catch (Exception ex) {
                ex.printStackTrace();
            }
            StringWaveFrame.this.playThread = null;
            StringWaveFrame.this.soundCheck.setState(false);
        }
        
        void doRun() {
            Object invoke;
            Method method;
            try {
                final Class<?> forName = Class.forName("javax.sound.sampled.AudioFormat");
                final Object instance = forName.getConstructor(Float.TYPE, Integer.TYPE, Integer.TYPE, Boolean.TYPE, Boolean.TYPE).newInstance(new Float(22050.0f), new Integer(16), new Integer(1), new Boolean(true), new Boolean(true));
                final Class<?> forName2 = Class.forName("javax.sound.sampled.DataLine$Info");
                final Class<?> forName3 = Class.forName("javax.sound.sampled.SourceDataLine");
                invoke = Class.forName("javax.sound.sampled.AudioSystem").getMethod("getLine", Class.forName("javax.sound.sampled.Line$Info")).invoke(null, forName2.getConstructor((StringWaveFrame.class$java$lang$Class == null) ? (StringWaveFrame.class$java$lang$Class = StringWaveFrame.class$("java.lang.Class")) : StringWaveFrame.class$java$lang$Class, forName).newInstance(forName3, instance));
                forName3.getMethod("open", forName, Integer.TYPE).invoke(invoke, instance, new Integer(4096));
                forName3.getMethod("start", (Class[])null).invoke(invoke, (Object[])null);
                method = forName3.getMethod("write", new byte[] { 0 }.getClass(), Integer.TYPE, Integer.TYPE);
            }
            catch (Exception ex) {
                ex.printStackTrace();
                return;
            }
            final int n = 16384;
            final FFT fft = new FFT(n);
            double[] array = null;
            final byte[] array2 = new byte[4096];
            int n2 = 0;
            int n3 = 0;
            double n4 = 0.2;
            while (StringWaveFrame.this.soundCheck.getState() && StringWaveFrame.this.applet.ogf != null) {
                final double n5 = StringWaveFrame.this.dampcoef * 0.01;
                if (array == null || this.changed) {
                    array = new double[n * 2];
                    final double n6 = 125.66370614359172 * Math.sqrt(StringWaveFrame.this.tensionBarValue) / StringWaveFrame.this.omega[1];
                    this.changed = false;
                    n4 = 0.2;
                    for (int i = 1; i != StringWaveFrame.this.maxTerms; ++i) {
                        final int n7 = (int)(n6 * StringWaveFrame.this.omega[i]);
                        if (n7 >= n) {
                            break;
                        }
                        array[n7] = StringWaveFrame.this.magcoef[i];
                    }
                    fft.transform(array, true);
                    for (int j = 0; j != n; ++j) {
                        final double n8 = array[j * 2] * Math.exp(n5 * j);
                        if (n8 > n4) {
                            n4 = n8;
                        }
                        if (n8 < -n4) {
                            n4 = -n8;
                        }
                    }
                    n2 = (n3 = 0);
                }
                final double n9 = 32767.0 / n4;
                final int n10 = array2.length / 2;
                for (int k = 0; k != n10; ++k) {
                    final short n11 = (short)(array[(k + n2) * 2] * n9 * Math.exp(n5 * n3++));
                    array2[k * 2] = (byte)(n11 / 256);
                    array2[k * 2 + 1] = (byte)(n11 & 0xFF);
                }
                n2 += n10;
                if (n2 == array.length / 2) {
                    n2 = 0;
                }
                try {
                    method.invoke(invoke, array2, new Integer(0), new Integer(array2.length));
                    continue;
                }
                catch (Exception ex2) {
                    ex2.printStackTrace();
                }
                break;
            }
        }
    }
}
