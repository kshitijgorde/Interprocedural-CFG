import java.awt.event.ItemEvent;
import java.awt.Event;
import java.awt.event.AdjustmentEvent;
import java.awt.event.ActionEvent;
import java.awt.event.ComponentEvent;
import java.awt.event.MouseEvent;
import java.awt.image.ImageObserver;
import java.awt.Graphics;
import java.awt.Color;
import java.awt.Label;
import java.awt.Component;
import java.awt.LayoutManager;
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

class CoupledOscFrame extends Frame implements ComponentListener, ActionListener, AdjustmentListener, MouseMotionListener, MouseListener, ItemListener
{
    Thread engine;
    Dimension winSize;
    Image dbimage;
    Random random;
    int maxLoads;
    int maxMaxLoads;
    public static final double epsilon = 1.0E-5;
    public static final double epsilon2 = 0.003;
    Button blankButton;
    Button resetMassButton;
    Button resetSpringsButton;
    Checkbox stoppedCheck;
    Checkbox elasticCheck;
    Checkbox lissaCheck;
    Choice modeChooser;
    Choice setupChooser;
    Scrollbar dampingBar;
    Scrollbar speedBar;
    Scrollbar loadBar;
    double[] magcoef;
    double[] dampcoef;
    double[] phasecoef;
    double[] phasecoefcos;
    double[] phasecoefadj;
    double[] omega;
    double[] loadAtRest;
    double[][] modes;
    double[][] modesInverse;
    double[] springs;
    double[] masses;
    boolean[] collided;
    int[] loadSize;
    int[] loadSizeOffset;
    int adjustedWidth;
    int maxLoadSize;
    static final double pi = 3.141592653589793;
    double step;
    double[] loadDisp;
    double[] loadPos;
    int selectedCoef;
    int selectedLoad;
    static final int SEL_NONE = 0;
    static final int SEL_FUNC = 1;
    static final int SEL_MAG = 2;
    static final int MODE_PULL = 0;
    static final int MODE_SHAPE = 1;
    static final int MODE_MASS = 2;
    static final int MODE_SPRING = 3;
    int selection;
    int dragX;
    int dragY;
    boolean dragging;
    double t;
    int pause;
    CoupledOsc applet;
    CoupledOscCanvas cv;
    long lastTime;
    double logep2;
    
    public String getAppletInfo() {
        return "CoupledOsc Series by Paul Falstad";
    }
    
    int getrand(final int n) {
        int nextInt = this.random.nextInt();
        if (nextInt < 0) {
            nextInt = -nextInt;
        }
        return nextInt % n;
    }
    
    CoupledOscFrame(final CoupledOsc applet) {
        super("Coupled Oscillations Applet v1.3");
        this.engine = null;
        this.maxLoads = 30;
        this.maxMaxLoads = 30;
        this.selectedLoad = -1;
        this.lastTime = 0L;
        this.logep2 = 0.0;
        this.applet = applet;
    }
    
    public void init() {
        this.selectedCoef = -1;
        this.setLayout(new CoupledOscLayout());
        (this.cv = new CoupledOscCanvas(this)).addComponentListener(this);
        this.cv.addMouseMotionListener(this);
        this.cv.addMouseListener(this);
        this.add(this.cv);
        (this.setupChooser = new Choice()).add("Setup: 5 masses");
        this.setupChooser.add("Setup: 2 masses");
        this.setupChooser.add("Setup: Weak Coupling 1");
        this.setupChooser.add("Setup: Weak Coupling 2");
        this.setupChooser.addItemListener(this);
        this.add(this.setupChooser);
        this.add(this.blankButton = new Button("Reset Positions"));
        this.blankButton.addActionListener(this);
        this.add(this.resetMassButton = new Button("Reset Masses"));
        this.resetMassButton.addActionListener(this);
        this.add(this.resetSpringsButton = new Button("Reset Springs"));
        this.resetSpringsButton.addActionListener(this);
        (this.stoppedCheck = new Checkbox("Stopped")).addItemListener(this);
        this.add(this.stoppedCheck);
        (this.elasticCheck = new Checkbox("Elastic Collisions")).addItemListener(this);
        this.add(this.elasticCheck);
        (this.lissaCheck = new Checkbox("Lissajous Figures")).addItemListener(this);
        this.add(this.lissaCheck);
        (this.modeChooser = new Choice()).add("Mouse: Pull string");
        this.modeChooser.add("Mouse: Move load");
        this.modeChooser.add("Mouse: Modify masses");
        this.modeChooser.add("Mouse: Modify springs");
        this.modeChooser.addItemListener(this);
        this.add(this.modeChooser);
        this.add(new Label("Simulation Speed", 1));
        this.add(this.speedBar = new Scrollbar(0, 35, 1, 1, 200));
        this.speedBar.addAdjustmentListener(this);
        this.add(new Label("Damping", 1));
        this.add(this.dampingBar = new Scrollbar(0, 0, 1, 0, 1000));
        this.dampingBar.addAdjustmentListener(this);
        this.add(new Label("Number of Loads", 1));
        this.add(this.loadBar = new Scrollbar(0, 5, 1, 1, this.maxMaxLoads));
        this.loadBar.addAdjustmentListener(this);
        this.add(new Label("http://www.falstad.com", 1));
        try {
            final String parameter = this.applet.getParameter("PAUSE");
            if (parameter != null) {
                this.pause = Integer.parseInt(parameter);
            }
        }
        catch (Exception ex) {}
        this.magcoef = new double[this.maxMaxLoads];
        this.phasecoef = new double[this.maxMaxLoads];
        this.phasecoefcos = new double[this.maxMaxLoads];
        this.phasecoefadj = new double[this.maxMaxLoads];
        this.loadAtRest = new double[this.maxMaxLoads];
        this.loadDisp = new double[this.maxMaxLoads];
        this.loadPos = new double[this.maxMaxLoads];
        this.springs = new double[this.maxMaxLoads + 1];
        this.masses = new double[this.maxMaxLoads];
        this.collided = new boolean[this.maxMaxLoads];
        for (int i = 0; i != this.maxMaxLoads; ++i) {
            this.springs[i] = (this.masses[i] = 1.0);
        }
        this.springs[this.maxMaxLoads] = 1.0;
        this.setLoadCount();
        this.reinit();
        this.random = new Random();
        this.cv.setBackground(Color.black);
        this.cv.setForeground(Color.lightGray);
        this.resize(800, 500);
        final Dimension size = this.getSize();
        final Dimension screenSize = this.getToolkit().getScreenSize();
        this.setLocation((screenSize.width - size.width) / 2, (screenSize.height - size.height) / 2);
        this.show();
    }
    
    void reinit() {
        final Dimension size = this.cv.getSize();
        this.winSize = size;
        final Dimension dimension = size;
        if (this.winSize.width == 0) {
            return;
        }
        this.dbimage = this.createImage(dimension.width, dimension.height);
        this.doSetup();
    }
    
    void doBlank() {
        for (int i = 0; i < this.maxLoads; ++i) {
            this.loadDisp[i] = 0.0;
        }
        this.transform();
    }
    
    void transform() {
        this.t = 0.0;
        this.lastTime = 0L;
        for (int i = 0; i != this.maxLoads; ++i) {
            double n = 0.0;
            for (int j = 0; j != this.maxLoads; ++j) {
                n += this.loadDisp[j] * this.modesInverse[j][i];
            }
            if (n < 1.0E-5 && n > -1.0E-5) {
                n = 0.0;
            }
            this.magcoef[i] = n;
            this.phasecoef[i] = 0.0;
            this.phasecoefadj[i] = 0.0;
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
    
    public void updateCoupledOsc(final Graphics graphics) {
        final Graphics graphics2 = this.dbimage.getGraphics();
        if (this.winSize == null || this.winSize.width == 0) {
            return;
        }
        if (!this.stoppedCheck.getState()) {
            final long currentTimeMillis = System.currentTimeMillis();
            if (this.lastTime != 0L) {
                this.t += this.speedBar.getValue() * 2.0E-4 * (int)(currentTimeMillis - this.lastTime);
            }
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
        final int panelHeight = this.getPanelHeight();
        int maxLoadSize = panelHeight / 2;
        final int n = panelHeight / 2;
        final boolean state = this.lissaCheck.getState();
        if (state) {
            maxLoadSize = this.maxLoadSize;
        }
        if (!this.stoppedCheck.getState()) {
            for (int i = 0; i != this.maxLoads; ++i) {
                final double[] magcoef = this.magcoef;
                final int n2 = i;
                magcoef[n2] *= this.dampcoef[i];
            }
        }
        final boolean b = this.dragging && this.selection == 1;
        final boolean b2 = this.modeChooser.getSelectedIndex() == 3;
        boolean b3 = true;
        int n3 = -1;
        int n4 = -1;
        int n5 = 0;
        if (!b) {
            for (int j = 0; j != this.maxLoads; ++j) {
                if (this.magcoef[j] < 1.0E-5 && this.magcoef[j] > -1.0E-5) {
                    this.phasecoef[j] = (this.phasecoefadj[j] = 0.0);
                }
                else {
                    if (++n5 == 1) {
                        n3 = j;
                    }
                    else if (n5 == 2) {
                        n4 = j;
                    }
                    b3 = false;
                    this.phasecoef[j] = (this.omega[j] * this.t + this.phasecoefadj[j]) % 6.283185307179586;
                    if (this.phasecoef[j] > 3.141592653589793) {
                        this.phasecoef[j] = -(6.283185307179586 - this.phasecoef[j]);
                    }
                    this.phasecoefcos[j] = Math.cos(this.phasecoef[j]);
                }
            }
            this.genDisps();
            this.checkCollisions();
        }
        int n6 = 0;
        graphics2.setColor(color2);
        for (int k = 0; k != this.maxLoads; ++k) {
            this.loadPos[k] = this.loadDisp[k] + this.loadAtRest[k];
            final int n7 = (int)(this.adjustedWidth * this.loadAtRest[k]) + this.loadSizeOffset[k];
            final int n8 = this.loadSize[k];
            graphics2.drawLine(n7, maxLoadSize - n8 / 2 - 1, n7, maxLoadSize + n8 / 2 + 1);
        }
        for (int l = 0; l != this.maxLoads; ++l) {
            final int n9 = this.loadSize[l];
            final int n10 = this.loadSizeOffset[l] + (int)(this.adjustedWidth * this.loadPos[l]);
            graphics2.setColor(this.getSpringColor(l));
            graphics2.drawLine(n6, maxLoadSize, n10, maxLoadSize);
            graphics2.setColor(this.collided[l] ? Color.red : ((b && !b2 && l == this.selectedLoad) ? Color.cyan : Color.white));
            graphics2.fillOval(n10 - n9 / 2, maxLoadSize - n9 / 2, n9, n9);
            n6 = n10 + n9 - n9 / 2;
        }
        graphics2.setColor(this.getSpringColor(this.maxLoads));
        graphics2.drawLine(n6, maxLoadSize, this.winSize.width - 1, maxLoadSize);
        if (state && (n5 == 2 || this.maxLoads == 2)) {
            if (n5 != 2) {
                n3 = 0;
                n4 = 1;
            }
            graphics2.setColor(Color.white);
            final int n11 = panelHeight - this.maxLoadSize * 4;
            final int n12 = n11 / 2 + this.maxLoadSize * 4;
            final double n13 = 0.9 * n11 / 2.0;
            double n14 = 0.0;
            double n15 = 0.0;
            int n17;
            int n16 = n17 = -1;
            for (int n18 = -20; n18 <= 0; ++n18) {
                final int n19 = 255 + 12 * n18;
                graphics2.setColor(new Color(n19, n19, n19));
                n14 = this.magcoef[n3] * Math.cos((this.omega[n3] * (this.t + n18 * 0.08) + this.phasecoefadj[n3]) % 6.283185307179586);
                n15 = this.magcoef[n4] * Math.cos((this.omega[n4] * (this.t + n18 * 0.08) + this.phasecoefadj[n4]) % 6.283185307179586);
                final int n20 = (int)(this.winSize.width / 2 + n14 * n13);
                final int n21 = (int)(n12 - n15 * n13);
                if (n17 != -1) {
                    graphics2.drawLine(n17, n16, n20, n21);
                }
                n17 = n20;
                n16 = n21;
            }
            graphics2.fillOval((int)(this.winSize.width / 2 + n14 * n13) - 3, (int)(n12 - n15 * n13) - 3, 7, 7);
        }
        if (this.selectedCoef != -1 && !this.dragging && (this.magcoef[this.selectedCoef] > 0.004 || this.magcoef[this.selectedCoef] < -0.004)) {
            graphics2.setColor(Color.yellow);
            final int n22 = maxLoadSize + this.maxLoadSize + 2;
            for (int n23 = 0; n23 != this.maxLoads; ++n23) {
                final int n24 = this.loadSizeOffset[n23] + (int)(this.adjustedWidth * (this.magcoef[this.selectedCoef] * this.modes[this.selectedCoef][n23] * this.phasecoefcos[this.selectedCoef] + this.loadAtRest[n23]));
                final int n25 = this.loadSize[n23];
                graphics2.fillOval(n24 - n25 / 2, n22 - n25 / 2, n25, n25);
            }
        }
        final int termWidth = this.getTermWidth();
        final double n26 = 0.6 * n;
        final int n27 = panelHeight * 3 / 2 + (int)n26 / 2;
        graphics2.setColor(Color.white);
        this.centerString(graphics2, "Normal Modes: Magnitudes", (int)(panelHeight * 1.16));
        this.centerString(graphics2, "Normal Modes: Phases", (int)(panelHeight * 2.1));
        graphics2.setColor(color2);
        graphics2.drawLine(0, n27, this.winSize.width, n27);
        graphics2.setColor(color);
        graphics2.drawLine(0, n27 - (int)n26, this.winSize.width, n27 - (int)n26);
        graphics2.drawLine(0, n27 + (int)n26, this.winSize.width, n27 + (int)n26);
        final int n28 = termWidth - 3;
        for (int n29 = 0; n29 != this.maxLoads; ++n29) {
            final int n30 = termWidth * n29 + termWidth / 2;
            final int n31 = n27 - (int)(this.logcoef(this.magcoef[n29]) * n26);
            graphics2.setColor((n29 == this.selectedCoef) ? Color.yellow : Color.white);
            graphics2.drawLine(n30, n27, n30, n31);
            graphics2.fillOval(n30 - n28 / 2, n31 - n28 / 2, n28, n28);
        }
        final double n32 = 0.75 * n;
        final int n33 = panelHeight * 5 / 2;
        for (int n34 = -2; n34 <= 2; ++n34) {
            graphics2.setColor((n34 == 0) ? color2 : color);
            graphics2.drawLine(0, n33 + n34 * (int)n32 / 2, this.winSize.width, n33 + n34 * (int)n32 / 2);
        }
        final double n35 = n32 / 3.141592653589793;
        for (int n36 = 0; n36 != this.maxLoads; ++n36) {
            final int n37 = termWidth * n36 + termWidth / 2;
            double n38 = this.phasecoef[n36];
            if (this.magcoef[n36] > -7.5E-4 && this.magcoef[n36] < 7.5E-4) {
                n38 = 0.0;
            }
            final int n39 = n33 - (int)(n38 * n35);
            graphics2.setColor((n36 == this.selectedCoef) ? Color.yellow : Color.white);
            graphics2.drawLine(n37, n33, n37, n39);
            graphics2.fillOval(n37 - n28 / 2, n39 - n28 / 2, n28, n28);
        }
        graphics.drawImage(this.dbimage, 0, 0, this);
        if (!this.stoppedCheck.getState() && !b3) {
            this.cv.repaint(this.pause);
        }
    }
    
    int getTermWidth() {
        int n = this.winSize.width / this.maxMaxLoads;
        final int n2 = this.winSize.width / 30;
        if (n > n2) {
            n = n2;
        }
        if (n > 12) {
            n = 12;
        }
        return n & 0xFFFFFFFE;
    }
    
    void checkCollisions() {
        for (int i = 0; i < this.maxLoads; ++i) {
            this.collided[i] = false;
        }
        for (int j = 1; j < this.maxLoads; ++j) {
            if (this.loadPos[j - 1] > this.loadPos[j]) {
                this.collided[j - 1] = (this.collided[j] = true);
                final double[] velocities = this.getVelocities();
                final double n = (this.loadPos[j - 1] * this.masses[j - 1] + this.loadPos[j] * this.masses[j]) / (this.masses[j - 1] + this.masses[j]);
                final double n2 = (velocities[j - 1] * this.masses[j - 1] + velocities[j] * this.masses[j]) / (this.masses[j - 1] + this.masses[j]);
                this.loadPos[j - 1] = (this.loadPos[j] = n);
                if (this.elasticCheck.getState()) {
                    if (velocities[j - 1] > n2) {
                        velocities[j - 1] = 2.0 * n2 - velocities[j - 1];
                    }
                    if (velocities[j] < n2) {
                        velocities[j] = 2.0 * n2 - velocities[j];
                    }
                }
                else {
                    velocities[j - 1] = (velocities[j] = n2);
                }
                this.loadDisp[j - 1] = this.loadPos[j - 1] - this.loadAtRest[j - 1];
                this.loadDisp[j] = this.loadPos[j] - this.loadAtRest[j];
                this.retransform(velocities);
                this.genDisps();
            }
        }
        if (this.loadPos[0] < 0.0) {
            this.collided[0] = true;
            final double[] velocities2 = this.getVelocities();
            final double[] loadDisp = this.loadDisp;
            final int n3 = 0;
            loadDisp[n3] -= this.loadPos[0];
            if (this.elasticCheck.getState()) {
                if (velocities2[0] < 0.0) {
                    velocities2[0] = -velocities2[0];
                }
            }
            else {
                velocities2[0] = 0.0;
            }
            this.retransform(velocities2);
            this.genDisps();
        }
        if (this.loadPos[this.maxLoads - 1] > 1.0) {
            final double[] velocities3 = this.getVelocities();
            final int n4 = this.maxLoads - 1;
            this.collided[n4] = true;
            final double[] loadDisp2 = this.loadDisp;
            final int n5 = n4;
            loadDisp2[n5] += 1.0 - this.loadPos[n4];
            if (this.elasticCheck.getState()) {
                if (velocities3[n4] > 0.0) {
                    velocities3[n4] = -velocities3[n4];
                }
            }
            else {
                velocities3[n4] = 0.0;
            }
            this.retransform(velocities3);
            this.genDisps();
        }
    }
    
    void retransform(final double[] array) {
        for (int i = 0; i != this.maxLoads; ++i) {
            double n = 0.0;
            double n2 = 0.0;
            for (int j = 0; j != this.maxLoads; ++j) {
                n += this.modesInverse[j][i] * this.loadDisp[j];
                n2 -= this.modesInverse[j][i] * array[j];
            }
            final double n3 = n2 / this.omega[i];
            final double n4 = this.phasecoefadj[i] + this.omega[i] * this.t;
            this.magcoef[i] = Math.sqrt(n * n + n3 * n3);
            final double atan2 = Math.atan2(n3, n);
            final double[] phasecoefadj = this.phasecoefadj;
            final int n5 = i;
            phasecoefadj[n5] += atan2 - n4;
            this.phasecoef[i] = atan2;
            this.phasecoefcos[i] = Math.cos(atan2);
        }
    }
    
    void genDisps() {
        for (int i = 0; i != this.maxLoads; ++i) {
            double n = 0.0;
            for (int j = 0; j != this.maxLoads; ++j) {
                n += this.magcoef[j] * this.modes[j][i] * this.phasecoefcos[j];
            }
            this.loadDisp[i] = n;
            this.loadPos[i] = n + this.loadAtRest[i];
        }
    }
    
    double[] getVelocities() {
        final double[] array = new double[this.maxLoads];
        for (int i = 0; i != this.maxLoads; ++i) {
            double n = 0.0;
            for (int j = 0; j != this.maxLoads; ++j) {
                n += this.magcoef[j] * this.modes[j][i] * Math.sin(this.phasecoef[j]) * this.omega[j];
            }
            array[i] = -n;
        }
        return array;
    }
    
    Color getSpringColor(final int n) {
        double n2 = 0.0;
        double n3 = 1.0;
        double n4 = 0.0;
        double n5 = 1.0;
        if (n > 0) {
            n2 = this.loadPos[n - 1];
            n4 = this.loadAtRest[n - 1];
        }
        if (n < this.maxLoads) {
            n3 = this.loadPos[n];
            n5 = this.loadAtRest[n];
        }
        int n6 = (int)((2.0 - (n3 - n2) / (n5 - n4)) * 127.0);
        if (n6 < 64) {
            n6 = 64;
        }
        if (n6 > 255) {
            n6 = 255;
        }
        int n7 = (int)(n6 * (1.0 - this.springs[n] / 3.0));
        if (n7 < 0) {
            n7 = 0;
        }
        if (n7 > n6) {
            n7 = n6;
        }
        return new Color(n6, n7, n7);
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
        double unlogcoef = this.unlogcoef(-(n2 - (panelHeight * 3 / 2 + (int)n3 / 2)) / n3);
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
    
    void editFunc(final int n, final int n2) {
        if (this.modeChooser.getSelectedIndex() == 0) {
            this.editFuncPull(n, n2);
            return;
        }
        if (this.modeChooser.getSelectedIndex() == 2) {
            this.editFuncMass(n, n2);
            return;
        }
        if (this.modeChooser.getSelectedIndex() == 3) {
            this.editFuncSpring(n, n2);
            return;
        }
        this.editFuncPoint(n, n2);
    }
    
    int findParticle(final int n) {
        int n2 = -1;
        int n3 = 1000;
        for (int i = 0; i != this.maxLoads; ++i) {
            final int n4 = this.loadSizeOffset[i] + (int)(this.adjustedWidth * this.loadPos[i]);
            final int n5 = (n4 > n) ? (n4 - n) : (n - n4);
            if (n5 <= n3) {
                n3 = n5;
                n2 = i;
            }
        }
        return n2;
    }
    
    void editFuncMass(final int n, final int n2) {
        if (this.selectedLoad == -1) {
            this.selectedLoad = this.findParticle(n);
        }
        double n3 = n2 / this.getPanelHeight();
        if (n3 < 0.0) {
            n3 = 0.0;
        }
        if (n3 > 1.0) {
            n3 = 1.0;
        }
        double n4;
        if (n3 < 0.5) {
            n4 = 0.25 + n3 * 1.5;
        }
        else {
            n4 = (n3 - 0.5) * 10.0 + 1.0;
        }
        this.masses[this.selectedLoad] = n4;
        this.genModes();
        this.cv.repaint(this.pause);
    }
    
    double convertX(final int n) {
        return (n - this.loadSizeOffset[this.selectedLoad]) / this.adjustedWidth;
    }
    
    void editFuncSpring(final int n, final int n2) {
        if (this.selectedLoad == -1) {
            this.selectedLoad = this.findParticle(n);
            if (this.convertX(n) > this.loadPos[this.selectedLoad]) {
                ++this.selectedLoad;
            }
        }
        this.convertX(n);
        double n3 = n2 / this.getPanelHeight();
        if (n3 < 0.0) {
            n3 = 0.0;
        }
        if (n3 > 1.0) {
            n3 = 1.0;
        }
        double n4;
        if (n3 < 0.5) {
            n4 = 0.2 + n3 * 1.6;
        }
        else {
            n4 = (n3 - 0.5) * 7.0 + 1.0;
        }
        this.springs[this.selectedLoad] = n4;
        this.genModes();
        this.cv.repaint(this.pause);
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
        if (this.selectedLoad == -1) {
            this.selectedLoad = this.findParticle(n);
        }
        final int selectedLoad = this.selectedLoad;
        double n3 = this.convertX(n) - this.loadAtRest[selectedLoad];
        final double n4 = this.loadAtRest[selectedLoad];
        final double n5 = n4 + n3;
        double n6 = 0.0;
        double n7 = 1.0;
        if (selectedLoad > 0) {
            n6 = this.loadPos[selectedLoad - 1];
        }
        if (selectedLoad < this.maxLoads - 1) {
            n7 = this.loadPos[selectedLoad + 1];
        }
        if (n5 < n6) {
            n3 = n6 - n4;
        }
        if (n5 > n7) {
            n3 = n7 - n4;
        }
        this.loadDisp[this.selectedLoad] = n3;
        this.cv.repaint(this.pause);
    }
    
    void editFuncPull(final int n, final int n2) {
        if (this.selectedLoad == -1) {
            this.selectedLoad = this.findParticle(n);
        }
        final double convertX = this.convertX(n);
        final double n3 = this.loadAtRest[this.selectedLoad];
        int i;
        for (i = 0; i < this.selectedLoad; ++i) {
            this.loadPos[i] = convertX * this.loadAtRest[i] / n3;
        }
        this.loadPos[i++] = convertX;
        while (i < this.maxLoads) {
            this.loadPos[i] = (convertX * (1.0 - this.loadAtRest[i]) + this.loadAtRest[i] - n3) / (1.0 - n3);
            ++i;
        }
        for (int j = 0; j != this.maxLoads; ++j) {
            this.loadDisp[j] = this.loadPos[j] - this.loadAtRest[j];
        }
        this.cv.repaint(this.pause);
    }
    
    public void componentHidden(final ComponentEvent componentEvent) {
    }
    
    public void componentMoved(final ComponentEvent componentEvent) {
    }
    
    public void componentShown(final ComponentEvent componentEvent) {
        this.cv.repaint();
    }
    
    public void componentResized(final ComponentEvent componentEvent) {
        this.reinit();
        this.cv.repaint(100L);
    }
    
    public void actionPerformed(final ActionEvent actionEvent) {
        if (actionEvent.getSource() == this.blankButton) {
            this.doBlank();
            this.cv.repaint();
        }
        if (actionEvent.getSource() == this.resetMassButton) {
            for (int i = 0; i != this.maxMaxLoads; ++i) {
                this.masses[i] = 1.0;
            }
            this.genModes();
            this.cv.repaint();
        }
        if (actionEvent.getSource() == this.resetSpringsButton) {
            for (int j = 0; j != this.maxMaxLoads + 1; ++j) {
                this.springs[j] = 1.0;
            }
            this.genModes();
            this.cv.repaint();
        }
    }
    
    public void adjustmentValueChanged(final AdjustmentEvent adjustmentEvent) {
        System.out.println(((Scrollbar)adjustmentEvent.getSource()).getValue());
        if (adjustmentEvent.getSource() == this.dampingBar || adjustmentEvent.getSource() == this.speedBar) {
            this.setDamping();
        }
        if (adjustmentEvent.getSource() == this.loadBar) {
            this.setLoadCount();
        }
        this.cv.repaint(this.pause);
    }
    
    void setLoadCount() {
        this.maxLoads = this.loadBar.getValue();
        this.step = 3.141592653589793 / this.maxLoads;
        this.genModes();
        this.setDamping();
    }
    
    public boolean handleEvent(final Event event) {
        if (event.id == 201) {
            this.applet.destroyFrame();
            return true;
        }
        return super.handleEvent(event);
    }
    
    void setDamping() {
        this.dampcoef = new double[this.maxMaxLoads];
        final double n = this.speedBar.getValue() / 10.0;
        for (int i = 0; i != this.maxLoads; ++i) {
            this.dampcoef[i] = Math.exp(-this.omega[i] * n * (this.dampingBar.getValue() * 1.0E-4));
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
        if (y >= panelHeight && y < panelHeight * 2) {
            this.selectedCoef = x / this.getTermWidth();
            if (this.selectedCoef >= this.maxLoads) {
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
            for (int i = 0; i != this.maxLoads; ++i) {
                if (this.selectedCoef != i) {
                    this.magcoef[i] = 0.0;
                }
            }
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
        this.dragging = true;
        this.edit(mouseEvent);
    }
    
    public void mouseReleased(final MouseEvent mouseEvent) {
        if ((mouseEvent.getModifiers() & 0x10) == 0x0) {
            return;
        }
        this.dragging = false;
        this.selectedLoad = -1;
        if (this.selection == 1) {
            this.transform();
        }
        this.cv.repaint();
    }
    
    public void itemStateChanged(final ItemEvent itemEvent) {
        if (itemEvent.getItemSelectable() == this.stoppedCheck) {
            this.cv.repaint();
            return;
        }
        if (itemEvent.getItemSelectable() == this.setupChooser) {
            this.doSetup();
        }
    }
    
    void doSetup() {
        if (this.masses == null) {
            return;
        }
        for (int i = 0; i != this.maxMaxLoads; ++i) {
            this.masses[i] = 1.0;
            this.magcoef[i] = 0.0;
        }
        for (int j = 0; j != this.maxMaxLoads + 1; ++j) {
            this.springs[j] = 1.0;
        }
        switch (this.setupChooser.getSelectedIndex()) {
            case 0: {
                this.loadBar.setValue(5);
                this.magcoef[0] = 0.9;
                break;
            }
            case 1: {
                this.loadBar.setValue(2);
                this.magcoef[1] = 0.9;
                break;
            }
            case 2: {
                this.loadBar.setValue(2);
                this.springs[0] = (this.springs[2] = 4.5);
                this.springs[1] = 0.2;
                this.magcoef[0] = (this.magcoef[1] = 0.5);
                break;
            }
            case 3: {
                this.loadBar.setValue(3);
                this.magcoef[1] = (this.magcoef[2] = 0.45);
                this.masses[1] = 6.0;
                break;
            }
        }
        this.setLoadCount();
        this.cv.repaint();
    }
    
    double SIGN(final double n, final double n2) {
        return (n2 >= 0.0) ? Math.abs(n) : (-Math.abs(n));
    }
    
    void getEquilibrium() {
        final double[] array = new double[this.maxLoads];
        final double[] array2 = new double[this.maxLoads - 1];
        final double[] loadAtRest = new double[this.maxLoads];
        for (int i = 0; i != this.maxLoads; ++i) {
            array[i] = this.springs[i] + this.springs[i + 1];
        }
        for (int j = 0; j != this.maxLoads - 1; ++j) {
            array2[j] = -this.springs[j + 1];
            loadAtRest[j] = 0.0;
        }
        loadAtRest[this.maxLoads - 1] = this.springs[this.maxLoads];
        for (int k = 0; k != this.maxLoads - 1; ++k) {
            final double n = -array2[k] / array[k];
            final double[] array3 = array;
            final int n2 = k + 1;
            array3[n2] += n * array2[k];
        }
        for (int l = this.maxLoads - 1; l >= 1; --l) {
            loadAtRest[l - 1] = -array2[l - 1] / array[l] * loadAtRest[l];
            final double[] array4 = loadAtRest;
            final int n3 = l;
            array4[n3] /= array[l];
        }
        final double[] array5 = loadAtRest;
        final int n4 = 0;
        array5[n4] /= array[0];
        this.loadAtRest = loadAtRest;
        for (int n5 = 0; n5 != this.maxLoads; ++n5) {
            this.loadAtRest[n5] = (this.loadAtRest[n5] + 2 * (n5 + 1) / (this.maxLoads + 1.0)) / 3.0;
        }
    }
    
    void getSizes() {
        this.loadSize = new int[this.maxLoads];
        (this.loadSizeOffset = new int[this.maxLoads + 1])[0] = 0;
        final double n = (this.maxMaxLoads - this.maxLoads) * 12.0 / 22.0 + 2.0;
        this.maxLoadSize = 0;
        for (int i = 0; i != this.maxLoads; ++i) {
            int maxLoadSize = (int)(this.masses[i] * (n / 3.7));
            if (maxLoadSize > n) {
                maxLoadSize = (int)n;
            }
            maxLoadSize += 3;
            this.loadSize[i] = maxLoadSize;
            this.loadSizeOffset[i + 1] = this.loadSizeOffset[i] + maxLoadSize;
            final int[] loadSizeOffset = this.loadSizeOffset;
            final int n2 = i;
            loadSizeOffset[n2] += maxLoadSize / 2;
            if (maxLoadSize > this.maxLoadSize) {
                this.maxLoadSize = maxLoadSize;
            }
        }
        if (this.winSize != null) {
            this.adjustedWidth = this.winSize.width - this.loadSizeOffset[this.maxLoads];
        }
    }
    
    void genModes() {
        this.getEquilibrium();
        this.getSizes();
        final int value = this.loadBar.getValue();
        final double[] array = new double[value + 1];
        final double[] array2 = new double[value + 1];
        final double[][] array3 = new double[value + 1][value + 1];
        for (int i = 1; i <= value; ++i) {
            if (i < value) {
                array2[i] = -this.springs[i] / Math.sqrt(this.masses[i] * this.masses[i - 1]);
            }
            array[i] = (this.springs[i] + this.springs[i - 1]) / this.masses[i - 1];
            for (int j = 1; j <= value; ++j) {
                array3[i][j] = 0.0;
            }
            array3[i][i] = 1.0;
        }
        this.tqli(array, array2, value, array3);
        this.omega = new double[value];
        for (int k = 0; k != value; ++k) {
            if (k < value - 1 && array[k] == array[k + 1]) {
                System.out.print("degeneracy! " + k + "\n");
            }
            this.omega[k] = (array[k + 1] = Math.sqrt(array[k + 1]));
        }
        for (int l = 1; l < value; ++l) {
            final double n = this.omega[l];
            int n2 = l;
            while (this.omega[n2 - 1] > n) {
                this.omega[n2] = this.omega[n2 - 1];
                if (--n2 <= 0) {
                    break;
                }
            }
            this.omega[n2] = n;
        }
        this.modes = new double[value][value];
        this.modesInverse = new double[value][value];
        for (int n3 = 0; n3 != value; ++n3) {
            int n4;
            for (n4 = 0; n4 != value && this.omega[n3] != array[n4 + 1]; ++n4) {}
            if (n4 == value) {
                System.out.print("can't find omega! " + n3 + " " + this.omega[n3] + "\n");
            }
            array[n4 + 1] = -1.0;
            for (int n5 = 0; n5 != value; ++n5) {
                this.modes[n3][n5] = array3[n5 + 1][n4 + 1] / Math.sqrt(this.masses[n5]);
            }
            double n6 = 1.0;
            for (int n7 = 0; n7 != value; ++n7) {
                if (n7 == 0) {
                    final double abs = Math.abs(-this.loadAtRest[n7] / this.modes[n3][n7]);
                    if (abs < n6) {
                        n6 = abs;
                    }
                }
                if (n7 > 0 && this.modes[n3][n7] != this.modes[n3][n7 - 1]) {
                    final double abs2 = Math.abs((this.loadAtRest[n7 - 1] - this.loadAtRest[n7]) / (this.modes[n3][n7] - this.modes[n3][n7 - 1]));
                    if (abs2 < n6) {
                        n6 = abs2;
                    }
                }
                if (n7 == value - 1) {
                    final double abs3 = Math.abs((1.0 - this.loadAtRest[n7]) / this.modes[n3][n7]);
                    if (abs3 < n6) {
                        n6 = abs3;
                    }
                }
                if (n7 < value - 1 && this.modes[n3][n7] != this.modes[n3][n7 + 1]) {
                    final double abs4 = Math.abs((this.loadAtRest[n7 + 1] - this.loadAtRest[n7]) / (this.modes[n3][n7] - this.modes[n3][n7 + 1]));
                    if (abs4 < n6) {
                        n6 = abs4;
                    }
                }
            }
            for (int n8 = 0; n8 != value; ++n8) {
                final double[] array4 = this.modes[n3];
                final int n9 = n8;
                array4[n9] *= n6;
                this.modesInverse[n3][n8] = this.modes[n3][n8];
            }
        }
        this.gaussj(this.modesInverse, value);
    }
    
    void tqli(final double[] array, final double[] array2, final int n, final double[][] array3) {
        array2[n] = 0.0;
        for (int i = 1; i <= n; ++i) {
            int n2 = 0;
            int j;
            do {
                for (j = i; j <= n - 1; ++j) {
                    final double n3 = Math.abs(array[j]) + Math.abs(array[j + 1]);
                    if (Math.abs(array2[j]) + n3 == n3) {
                        break;
                    }
                }
                if (j != i) {
                    if (n2++ == 30) {
                        System.out.print("Too many iterations in tqli\n");
                    }
                    final double n4 = (array[i + 1] - array[i]) / (2.0 * array2[i]);
                    double pythag = this.pythag(n4, 1.0);
                    double n5 = array[j] - array[i] + array2[i] / (n4 + this.SIGN(pythag, n4));
                    double n7;
                    double n6 = n7 = 1.0;
                    double n8 = 0.0;
                    int k;
                    for (k = j - 1; k >= i; --k) {
                        final double n9 = n7 * array2[k];
                        final double n10 = n6 * array2[k];
                        pythag = (array2[k + 1] = this.pythag(n9, n5));
                        if (pythag == 0.0) {
                            final int n11 = k + 1;
                            array[n11] -= n8;
                            array2[j] = 0.0;
                            break;
                        }
                        n7 = n9 / pythag;
                        n6 = n5 / pythag;
                        final double n12 = array[k + 1] - n8;
                        pythag = (array[k] - n12) * n7 + 2.0 * n6 * n10;
                        array[k + 1] = n12 + (n8 = n7 * pythag);
                        n5 = n6 * pythag - n10;
                        for (int l = 1; l <= n; ++l) {
                            final double n13 = array3[l][k + 1];
                            array3[l][k + 1] = n7 * array3[l][k] + n6 * n13;
                            array3[l][k] = n6 * array3[l][k] - n7 * n13;
                        }
                    }
                    if (pythag == 0.0 && k >= i) {
                        continue;
                    }
                    final int n14 = i;
                    array[n14] -= n8;
                    array2[i] = n5;
                    array2[j] = 0.0;
                }
            } while (j != i);
        }
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
    
    double gaussj(final double[][] array, final int n) {
        int n2 = 0;
        int n3 = 0;
        final int[] array2 = new int[n];
        final int[] array3 = new int[n];
        final int[] array4 = new int[n];
        for (int i = 0; i < n; ++i) {
            array2[i] = 0;
        }
        for (int j = 0; j < n; ++j) {
            double abs = 0.0;
            for (int k = 0; k < n; ++k) {
                if (array2[k] != 1) {
                    for (int l = 0; l < n; ++l) {
                        if (array2[l] == 0) {
                            if (Math.abs(array[k][l]) >= abs) {
                                abs = Math.abs(array[k][l]);
                                n2 = k;
                                n3 = l;
                            }
                            else if (array2[l] > 1) {
                                System.out.print("error 1\n");
                                return 1.0;
                            }
                        }
                    }
                }
            }
            final int[] array5 = array2;
            final int n4 = n3;
            ++array5[n4];
            if (n2 != n3) {
                for (int n5 = 0; n5 < n; ++n5) {
                    final double n6 = array[n2][n5];
                    array[n2][n5] = array[n3][n5];
                    array[n3][n5] = n6;
                }
            }
            array3[j] = n2;
            array4[j] = n3;
            if (array[n3][n3] == 0.0) {
                System.out.print("singular matrix\n");
                return 1.0;
            }
            final double n7 = 1.0 / array[n3][n3];
            array[n3][n3] = 1.0;
            for (int n8 = 0; n8 < n; ++n8) {
                final double[] array6 = array[n3];
                final int n9 = n8;
                array6[n9] *= n7;
            }
            for (int n10 = 0; n10 < n; ++n10) {
                if (n10 != n3) {
                    final double n11 = array[n10][n3];
                    array[n10][n3] = 0.0;
                    for (int n12 = 0; n12 < n; ++n12) {
                        final double[] array7 = array[n10];
                        final int n13 = n12;
                        array7[n13] -= array[n3][n12] * n11;
                    }
                }
            }
        }
        for (int n14 = 0; n14 < n; ++n14) {
            final int n15 = n - 1 - n14;
            if (array3[n15] != array4[n15]) {
                for (int n16 = 0; n16 < n; ++n16) {
                    final double n17 = array[n16][array3[n15]];
                    array[n16][array3[n15]] = array[n16][array4[n15]];
                    array[n16][array4[n15]] = n17;
                }
            }
        }
        return 0.0;
    }
}
