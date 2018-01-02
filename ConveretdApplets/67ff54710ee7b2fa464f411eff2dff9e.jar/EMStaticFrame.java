import java.awt.event.ItemEvent;
import java.awt.event.AdjustmentEvent;
import java.awt.event.ActionEvent;
import java.awt.event.ComponentEvent;
import java.awt.event.MouseEvent;
import java.awt.Point;
import java.awt.FontMetrics;
import java.text.NumberFormat;
import java.awt.image.ImageObserver;
import java.awt.Graphics;
import java.awt.Event;
import java.awt.Color;
import java.awt.Component;
import java.awt.LayoutManager;
import java.awt.Label;
import java.awt.Scrollbar;
import java.util.Vector;
import java.awt.Choice;
import java.awt.Checkbox;
import java.awt.Button;
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

class EMStaticFrame extends Frame implements ComponentListener, ActionListener, AdjustmentListener, MouseMotionListener, MouseListener, ItemListener
{
    Thread engine;
    Dimension winSize;
    Image dbimage;
    int gridSizeX;
    int gridSizeY;
    int windowWidth;
    int windowHeight;
    int windowOffsetX;
    int windowOffsetY;
    int chargeRadius;
    public static final double chargeAmt = 0.5;
    Button blankButton;
    Checkbox stoppedCheck;
    Checkbox currentCheck;
    Checkbox equipCheck;
    Choice modeChooser;
    Choice viewChooser;
    Choice setupChooser;
    Choice accuracyChooser;
    Vector setupList;
    Setup setup;
    Scrollbar resBar;
    Scrollbar brightnessBar;
    Scrollbar adjustBar;
    Scrollbar equipBar;
    Label adjustLabel;
    GridElement[][] grid;
    SolverGrid[] solverGrids;
    Charge[] charges;
    static final int chargeMax = 20;
    static final int MODE_MOVE = 0;
    static final int MODE_DELETE = 1;
    static final int MODE_FQPLUS = 2;
    static final int MODE_FQMINUS = 3;
    static final int MODE_CLEAR = 4;
    static final int MODE_CONDUCTOR = 5;
    static final int MODE_CPLUS = 6;
    static final int MODE_CMINUS = 7;
    static final int MODE_QPLUS = 8;
    static final int MODE_QMINUS = 9;
    static final int MODE_DIELEC = 10;
    static final int MODE_FLOAT = 11;
    static final int MODE_ADJUST = 12;
    static final int MODE_ADJ_CONDUCT = 12;
    static final int MODE_ADJ_DIELEC = 13;
    static final int MODE_ADJ_POT = 14;
    static final int MODE_ADJ_CHARGE = 15;
    static final int VIEW_E = 0;
    static final int VIEW_E_LINES = 1;
    static final int VIEW_POT = 2;
    static final int VIEW_A = 3;
    static final int VIEW_B = 4;
    static final int VIEW_J = 5;
    static final int VIEW_Q = 6;
    static final int VIEW_D = 7;
    static final int VIEW_P = 8;
    static final int VIEW_P_CHARGE = 9;
    static final int VIEW_TYPE = 10;
    static final int VIEW_Q_J = 11;
    static final int VIEW_E_Q = 12;
    static final int VIEW_E_LINES_Q = 13;
    static final int VIEW_E_J = 14;
    static final int VIEW_E_LINES_J = 15;
    static final int VIEW_E_Q_J = 16;
    static final int VIEW_E_LINES_Q_J = 17;
    static final int VIEW_E_POT = 18;
    static final int VIEW_E_LINES_POT = 19;
    static final int VIEW_E_POT_COND = 20;
    static final int VIEW_E_LINES_POT_COND = 21;
    static final int VIEW_E_POT_J = 22;
    static final int VIEW_E_LINES_POT_J = 23;
    static final int VIEW_B_J = 24;
    static final int VIEW_E_B_Q_J = 25;
    static final int VIEW_E_LINES_B_Q_J = 26;
    static final int VIEW_EX = 27;
    static final int VIEW_EY = 28;
    static final int VIEW_DX = 29;
    static final int VIEW_DY = 30;
    static final int VIEW_NONE = -1;
    int dragX;
    int dragY;
    int selectedCharge;
    boolean dragging;
    boolean stopCalc;
    boolean dragClear;
    boolean dragSet;
    boolean[][] objDragMap;
    boolean changedCharges;
    boolean changedConductors;
    boolean changedMagField;
    double t;
    int pause;
    int chargeCount;
    int adjustSelectX1;
    int adjustSelectY1;
    int adjustSelectX2;
    int adjustSelectY2;
    EMStaticCanvas cv;
    EMStatic applet;
    boolean calculateNotice;
    boolean solveCurrent;
    double floatCap;
    double floatCharge;
    double floatExtCharge;
    byte[][] linegrid;
    int dragObjX;
    int dragObjY;
    int dragBoundX1;
    int dragBoundX2;
    int dragBoundY1;
    int dragBoundY2;
    
    public String getAppletInfo() {
        return "EMStatic by Paul Falstad";
    }
    
    EMStaticFrame(final EMStatic applet) {
        super("Electrostatics Applet");
        this.engine = null;
        this.windowWidth = 50;
        this.windowHeight = 50;
        this.windowOffsetX = 0;
        this.windowOffsetY = 0;
        this.chargeRadius = 1;
        this.chargeCount = 0;
        this.floatCharge = 0.0;
        this.applet = applet;
    }
    
    public void init() {
        this.setupList = new Vector();
        Setup next = new SingleChargeSetup();
        int n = 0;
        while (next != null) {
            this.setupList.addElement(next);
            next = next.createNext();
            if (n++ == 300) {
                System.out.print("setup loop?\n");
                break;
            }
        }
        this.charges = new Charge[20];
        this.setLayout(new EMStaticLayout());
        (this.cv = new EMStaticCanvas(this)).addComponentListener(this);
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
        (this.modeChooser = new Choice()).add("Mouse = Move Object");
        this.modeChooser.add("Mouse = Delete Object");
        this.modeChooser.add("Mouse = Add + Draggable Charge");
        this.modeChooser.add("Mouse = Add - Draggable Charge");
        this.modeChooser.add("Mouse = Clear Square");
        this.modeChooser.add("Mouse = Add Conductor (Gnd)");
        this.modeChooser.add("Mouse = Add + Conductor");
        this.modeChooser.add("Mouse = Add - Conductor");
        this.modeChooser.add("Mouse = Add + Charge Square");
        this.modeChooser.add("Mouse = Add - Charge Square");
        this.modeChooser.add("Mouse = Add Dielectric");
        this.modeChooser.add("Mouse = Make Floater");
        this.modeChooser.add("Mouse = Adjust Conductivity");
        this.modeChooser.add("Mouse = Adjust Dielectric");
        this.modeChooser.add("Mouse = Adjust Potential");
        this.modeChooser.add("Mouse = Adjust Charge");
        this.modeChooser.addItemListener(this);
        this.modeChooser.select(0);
        this.add(this.modeChooser);
        (this.viewChooser = new Choice()).add("Show Electric Field (E)");
        this.viewChooser.add("Show E lines");
        this.viewChooser.add("Show Potential (Phi)");
        this.viewChooser.add("Show Vector Potential (A)");
        this.viewChooser.add("Show Magnetic Field (B)");
        this.viewChooser.add("Show Current (j)");
        this.viewChooser.add("Show Charge (rho)");
        this.viewChooser.add("Show Displacement (D)");
        this.viewChooser.add("Show Polarization (P)");
        this.viewChooser.add("Show Polarization Charge");
        this.viewChooser.add("Show Material Type");
        this.viewChooser.add("Show rho/j");
        this.viewChooser.add("Show E/rho");
        this.viewChooser.add("Show E lines/rho");
        this.viewChooser.add("Show E/j");
        this.viewChooser.add("Show E lines/j");
        this.viewChooser.add("Show E/rho/j");
        this.viewChooser.add("Show E lines/rho/j");
        this.viewChooser.add("Show E/Phi");
        this.viewChooser.add("Show E lines/Phi");
        this.viewChooser.add("Show E/Phi in conductors");
        this.viewChooser.add("Show E lines/Phi in cond.");
        this.viewChooser.add("Show E/Phi/j");
        this.viewChooser.add("Show E lines/Phi/j");
        this.viewChooser.add("Show B/j");
        this.viewChooser.add("Show E/B/rho/j");
        this.viewChooser.add("Show E lines/B/rho/j");
        this.viewChooser.add("Show Ex");
        this.viewChooser.add("Show Ey");
        this.viewChooser.add("Show Dx");
        this.viewChooser.add("Show Dy");
        this.viewChooser.addItemListener(this);
        this.add(this.viewChooser);
        this.viewChooser.select(16);
        (this.accuracyChooser = new Choice()).add("Low Accuracy");
        this.accuracyChooser.add("Medium Accuracy");
        this.accuracyChooser.add("High Accuracy");
        this.accuracyChooser.add("Highest Accuracy");
        this.accuracyChooser.select(1);
        this.accuracyChooser.addItemListener(this);
        this.add(this.accuracyChooser);
        this.add(this.blankButton = new Button("Clear All"));
        this.blankButton.addActionListener(this);
        (this.stoppedCheck = new Checkbox("Stop Calculation")).addItemListener(this);
        this.add(this.stoppedCheck);
        (this.currentCheck = new Checkbox("Enable Current", false)).addItemListener(this);
        this.add(this.currentCheck);
        (this.equipCheck = new Checkbox("Draw Equipotentials", true)).addItemListener(this);
        this.add(this.equipCheck);
        this.add(new Label("Resolution", 1));
        this.add(this.resBar = new Scrollbar(0, 44, 4, 24, 90));
        this.resBar.addAdjustmentListener(this);
        this.setResolution();
        this.add(new Label("Brightness", 1));
        this.add(this.brightnessBar = new Scrollbar(0, 10, 1, 1, 2000));
        this.brightnessBar.addAdjustmentListener(this);
        this.add(new Label("Equipotential Count", 1));
        this.add(this.equipBar = new Scrollbar(0, 10, 1, 2, 30));
        this.equipBar.addAdjustmentListener(this);
        this.add(this.adjustLabel = new Label("", 1));
        this.add(this.adjustBar = new Scrollbar(0, 50, 1, 0, 102));
        this.adjustBar.addAdjustmentListener(this);
        this.add(new Label("http://www.falstad.com"));
        try {
            final String parameter = this.applet.getParameter("PAUSE");
            if (parameter != null) {
                this.pause = Integer.parseInt(parameter);
            }
        }
        catch (Exception ex) {}
        this.reinit();
        this.setModeChooser();
        this.setup = this.setupList.elementAt(0);
        this.cv.setBackground(Color.black);
        this.cv.setForeground(Color.lightGray);
        this.resize(660, 500);
        this.handleResize();
        this.show();
    }
    
    void reinit() {
        this.chargeCount = 0;
        this.adjustSelectX1 = -1;
        this.grid = new GridElement[this.gridSizeX][this.gridSizeY];
        for (int i = 0; i != this.gridSizeX; ++i) {
            for (int j = 0; j != this.gridSizeY; ++j) {
                this.grid[i][j] = new GridElement();
            }
        }
        this.solverGrids = new SolverGrid[16];
        for (int k = 0; k != 16; ++k) {
            this.solverGrids[k] = new SolverGrid();
        }
        this.doSetup();
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
    
    public boolean handleEvent(final Event event) {
        if (event.id == 201) {
            this.applet.destroyFrame();
            return true;
        }
        return super.handleEvent(event);
    }
    
    void doBlank() {
        for (int i = 0; i < this.gridSizeX; ++i) {
            for (int j = 0; j < this.gridSizeY; ++j) {
                this.grid[i][j].clear();
            }
        }
        this.chargeCount = 0;
        this.floatCharge = 0.0;
        final boolean b = true;
        this.changedConductors = b;
        this.changedCharges = b;
    }
    
    void doDielec(final double dielec) {
        for (int i = 0; i < this.gridSizeX; ++i) {
            for (int j = this.gridSizeY / 2; j < this.gridSizeY; ++j) {
                this.grid[i][j].dielec = dielec;
            }
        }
        this.changedConductors = true;
    }
    
    void addUniformField() {
        this.conductFillRect(0, this.windowOffsetY, this.gridSizeX - 1, this.windowOffsetY, 1.0, 1.0);
        final int n = this.windowOffsetY + this.windowHeight - 1;
        this.conductFillRect(0, n, this.gridSizeX - 1, n, -1.0, 1.0);
    }
    
    void calcExceptions() {
        for (int i = 0; i < this.gridSizeX; ++i) {
            for (int j = 0; j < this.windowOffsetY; ++j) {
                this.copyConductor(i, j, i, this.windowOffsetY);
                this.copyConductor(i, this.gridSizeY - j - 1, i, this.windowOffsetY + this.windowHeight - 1);
            }
        }
        for (int k = 0; k < this.gridSizeY; ++k) {
            for (int l = 0; l < this.windowOffsetX; ++l) {
                this.copyConductor(l, k, this.windowOffsetX, k);
                this.copyConductor(this.gridSizeX - l - 1, k, this.windowOffsetX + this.windowWidth - 1, k);
            }
        }
        for (int n = 1; n != this.gridSizeX - 1; ++n) {
            for (int n2 = 1; n2 != this.gridSizeY - 1; ++n2) {
                final GridElement gridElement = this.grid[n][n2 - 1];
                final GridElement gridElement2 = this.grid[n][n2 + 1];
                final GridElement gridElement3 = this.grid[n - 1][n2];
                final GridElement gridElement4 = this.grid[n + 1][n2];
                final GridElement gridElement5 = this.grid[n][n2];
                gridElement5.boundary = (gridElement.dielec != gridElement5.dielec || gridElement2.dielec != gridElement5.dielec || gridElement3.dielec != gridElement5.dielec || gridElement4.dielec != gridElement5.dielec || gridElement.conductor != gridElement5.conductor || gridElement2.conductor != gridElement5.conductor || gridElement3.conductor != gridElement5.conductor || gridElement4.conductor != gridElement5.conductor);
            }
        }
    }
    
    void copyConductor(final int n, final int n2, final int n3, final int n4) {
        this.grid[n][n2].conductor = this.grid[n3][n4].conductor;
        this.grid[n][n2].floater = this.grid[n3][n4].floater;
        this.grid[n][n2].conductivity = this.grid[n3][n4].conductivity;
        if (this.grid[n][n2].conductor) {
            this.grid[n][n2].pot = this.grid[n3][n4].pot;
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
    
    public void updateEMStatic(final Graphics graphics) {
        if (this.winSize == null || this.winSize.width == 0) {
            return;
        }
        final Graphics graphics2 = this.dbimage.getGraphics();
        if (!this.calculateNotice && !this.stoppedCheck.getState() && !this.stopCalc && (this.changedConductors || this.changedCharges)) {
            final FontMetrics fontMetrics = graphics2.getFontMetrics();
            graphics2.setColor(Color.black);
            final String s = "Calculating...";
            graphics2.fillRect(0, this.winSize.height - 30, 20 + fontMetrics.stringWidth(s), 30);
            graphics2.setColor(Color.white);
            graphics2.drawString(s, 10, this.winSize.height - 10);
            this.cv.repaint(0L);
            this.calculateNotice = true;
            graphics.drawImage(this.dbimage, 0, 0, this);
            return;
        }
        this.calculateNotice = false;
        graphics2.setColor(this.cv.getBackground());
        graphics2.fillRect(0, 0, this.winSize.width, this.winSize.height);
        final double n = this.brightnessBar.getValue() / 5.0;
        int n5;
        int n4;
        int n3;
        int n2 = n3 = (n4 = (n5 = -1));
        final int selectedIndex = this.viewChooser.getSelectedIndex();
        boolean b = false;
        boolean b2 = false;
        boolean b3 = false;
        switch (selectedIndex) {
            case 2:
            case 6:
            case 9:
            case 10: {
                n2 = (n3 = selectedIndex);
                break;
            }
            case 4:
            case 27:
            case 28:
            case 29:
            case 30: {
                n2 = (n3 = selectedIndex);
                b3 = true;
                break;
            }
            case 0:
            case 5:
            case 7:
            case 8: {
                n5 = (n4 = selectedIndex);
                break;
            }
            case 3: {
                n5 = (n4 = selectedIndex);
                b3 = true;
                break;
            }
            case 14: {
                n4 = 0;
                n5 = 5;
                break;
            }
            case 15: {
                n5 = 5;
                b = true;
                break;
            }
            case 1: {
                b2 = (b = true);
                break;
            }
            case 11: {
                n2 = (n3 = 6);
                n5 = (n4 = 5);
                break;
            }
            case 12: {
                n2 = (n3 = 6);
                n5 = (n4 = 0);
                break;
            }
            case 13: {
                n2 = (n3 = 6);
                b2 = (b = true);
                break;
            }
            case 26: {
                n3 = 4;
                n2 = 6;
                n5 = 5;
                b = true;
                b3 = true;
                break;
            }
            case 18: {
                n2 = (n3 = 2);
                n5 = (n4 = 0);
                break;
            }
            case 19: {
                n2 = (n3 = 2);
                b2 = (b = true);
                break;
            }
            case 22: {
                n2 = (n3 = 2);
                n4 = 0;
                n5 = 5;
                break;
            }
            case 23: {
                n2 = (n3 = 2);
                n5 = 5;
                b = true;
                break;
            }
            case 20: {
                n3 = -1;
                n2 = 2;
                n4 = 0;
                break;
            }
            case 21: {
                n3 = -1;
                n2 = 2;
                b = true;
                break;
            }
            case 16: {
                n2 = (n3 = 6);
                n4 = 0;
                n5 = 5;
                break;
            }
            case 17: {
                n2 = (n3 = 6);
                n5 = 5;
                b = true;
                break;
            }
            case 24: {
                n2 = (n3 = 4);
                n5 = (n4 = 5);
                b3 = true;
                break;
            }
            case 25: {
                n3 = 4;
                n2 = 6;
                n4 = 0;
                n5 = 5;
                b3 = true;
                break;
            }
        }
        this.doCalc(b3);
        if (this.stopCalc) {
            n5 = (n4 = (n3 = -1));
            if (n2 != 2) {
                n2 = -1;
            }
        }
        for (int i = 0; i != this.windowHeight; ++i) {
            final int n6 = this.winSize.width * (i * this.winSize.height / this.windowHeight);
            for (int j = 0; j != this.windowWidth; ++j) {
                final int n7 = j * this.winSize.width / this.windowWidth;
                final int n8 = i * this.winSize.height / this.windowHeight;
                final int n9 = (j + 1) * this.winSize.width / this.windowWidth;
                final int n10 = (i + 1) * this.winSize.height / this.windowHeight;
                final int n11 = j + this.windowOffsetX;
                final int n12 = i + this.windowOffsetY;
                int n13 = n3;
                int n14 = n4;
                int n15 = 0;
                int n16 = 0;
                int n17 = 0;
                final GridElement gridElement = this.grid[n11][n12];
                if (gridElement.conductor || gridElement.dielec != 1.0 || gridElement.charge != 0.0) {
                    n16 = (n15 = (n17 = 64));
                    if (this.objDragMap != null) {
                        try {
                            if (this.objDragMap[n11 - this.dragObjX][n12 - this.dragObjY]) {
                                n16 = (n15 = (n17 = 128));
                            }
                        }
                        catch (Exception ex) {}
                    }
                    if (gridElement.conductor) {
                        n14 = n5;
                        n13 = n2;
                    }
                }
                if (n13 != -1) {
                    double n18 = 0.0;
                    switch (n13) {
                        case 2: {
                            n18 = gridElement.pot * 0.2 * n;
                            break;
                        }
                        case 6: {
                            n18 = 0.4 * this.getCharge(n11, n12) * n;
                            break;
                        }
                        case 27: {
                            n18 = this.getEField(gridElement, this.grid[n11 - 1][n12], this.grid[n11 + 1][n12]) * n;
                            break;
                        }
                        case 28: {
                            n18 = this.getEField(gridElement, this.grid[n11][n12 - 1], this.grid[n11][n12 + 1]) * n;
                            break;
                        }
                        case 29: {
                            n18 = this.getDField(gridElement, this.grid[n11 - 1][n12], this.grid[n11 + 1][n12], 0.0) * n;
                            break;
                        }
                        case 30: {
                            n18 = this.getDField(gridElement, this.grid[n11][n12 - 1], this.grid[n11][n12 + 1], 0.0) * n;
                            break;
                        }
                        case 4: {
                            n18 = (this.grid[n11 - 1][n12].ay - this.grid[n11 + 1][n12].ay + (this.grid[n11][n12 + 1].ax - this.grid[n11][n12 - 1].ax)) * n;
                            break;
                        }
                        case 9: {
                            n18 = (this.getPCharge(gridElement, this.grid[n11 - 1][n12], this.grid[n11 + 1][n12]) + this.getPCharge(gridElement, this.grid[n11][n12 - 1], this.grid[n11][n12 + 1])) * 0.4 * n;
                            n13 = 6;
                            break;
                        }
                    }
                    if (n18 < -1.0) {
                        n18 = -1.0;
                    }
                    if (n18 > 1.0) {
                        n18 = 1.0;
                    }
                    if (n13 == 10) {
                        double n19 = 0.0;
                        double conductivity = 0.0;
                        double n20 = 0.0;
                        if (gridElement.conductor) {
                            if (gridElement.floater > 0) {
                                n20 = (n19 = 1.0);
                            }
                            else {
                                n20 = (conductivity = gridElement.conductivity);
                            }
                        }
                        else if (gridElement.dielec != 1.0) {
                            n19 = gridElement.dielec / 10.0;
                            conductivity = n19 * 0.5;
                        }
                        else if (gridElement.charge != 0.0) {
                            final double n21 = gridElement.charge * n;
                            if (n21 < 0.0) {
                                n17 += (int)(-n21 * (255 - n17));
                            }
                            else {
                                n15 += (int)(n21 * (255 - n15));
                                n16 += (int)(n21 * (255 - n16));
                            }
                        }
                        n15 += (int)(this.clamp(n19) * (255 - n15));
                        n16 += (int)(this.clamp(conductivity) * (255 - n16));
                        n17 += (int)(this.clamp(n20) * (255 - n17));
                    }
                    else if (n13 == 6) {
                        if (n18 < 0.0) {
                            n17 += (int)(-n18 * (255 - n17));
                        }
                        else {
                            n15 += (int)(n18 * (255 - n15));
                            n16 += (int)(n18 * (255 - n16));
                        }
                    }
                    else if (n18 < 0.0) {
                        n15 += (int)(-n18 * (255 - n15));
                    }
                    else {
                        n16 += (int)(n18 * (255 - n16));
                    }
                }
                final int col = 0xFF000000 | n15 << 16 | n16 << 8 | n17;
                graphics2.setColor(new Color(col));
                graphics2.fillRect(n7, n8, n9 - n7, n10 - n8);
                gridElement.col = col;
                if (n14 != -1) {
                    double n22 = 0.0;
                    double n23 = 0.0;
                    switch (n14) {
                        case 0: {
                            if (!gridElement.boundary) {
                                n22 = -this.grid[n11 + 1][n12].pot + this.grid[n11 - 1][n12].pot;
                                n23 = -this.grid[n11][n12 + 1].pot + this.grid[n11][n12 - 1].pot;
                                break;
                            }
                            n22 = this.getEField(gridElement, this.grid[n11 - 1][n12], this.grid[n11 + 1][n12]);
                            n23 = this.getEField(gridElement, this.grid[n11][n12 - 1], this.grid[n11][n12 + 1]);
                            break;
                        }
                        case 7: {
                            n22 = this.getDField(gridElement, this.grid[n11 - 1][n12], this.grid[n11 + 1][n12], 0.0);
                            n23 = this.getDField(gridElement, this.grid[n11][n12 - 1], this.grid[n11][n12 + 1], 0.0);
                            break;
                        }
                        case 8: {
                            n22 = this.getDField(gridElement, this.grid[n11 - 1][n12], this.grid[n11 + 1][n12], 1.0);
                            n23 = this.getDField(gridElement, this.grid[n11][n12 - 1], this.grid[n11][n12 + 1], 1.0);
                            break;
                        }
                        case 5: {
                            n22 = gridElement.jx;
                            n23 = gridElement.jy;
                            break;
                        }
                        case 3: {
                            n22 = gridElement.ax * 0.3;
                            n23 = gridElement.ay * 0.3;
                            break;
                        }
                    }
                    final double sqrt = Math.sqrt(n22 * n22 + n23 * n23);
                    if (sqrt > 0.0) {
                        n22 /= sqrt;
                        n23 /= sqrt;
                    }
                    double n24 = sqrt * n;
                    int n26;
                    if (n14 == 5) {
                        if (n24 > 1.0) {
                            if (n24 > 2.0) {
                                n24 = 2.0;
                            }
                            final double n25 = n24 - 1.0;
                            n26 = (n15 = 255);
                            n17 += (int)(n25 * (255 - n17));
                        }
                        else {
                            n15 += (int)(n24 * (255 - n15));
                            n26 = n16 + (int)(n24 * (255 - n16));
                        }
                    }
                    else if (n24 > 1.0) {
                        if (n24 > 2.0) {
                            n24 = 2.0;
                        }
                        final double n27 = n24 - 1.0;
                        n26 = 255;
                        n15 += (int)(n27 * (255 - n15));
                        n17 += (int)(n27 * (255 - n17));
                    }
                    else {
                        n26 = n16 + (int)(n24 * (255 - n16));
                    }
                    final int n28 = 0xFF000000 | n15 << 16 | n26 << 8 | n17;
                    final int n29 = (n9 - n7) / 2;
                    final int n30 = (n10 - n8) / 2;
                    graphics2.setColor(new Color(n28));
                    final int n31 = n7 + n29 - (int)(n29 * n22);
                    final int n32 = n8 + n30 - (int)(n30 * n23);
                    final int n33 = n7 + n29 + (int)(n29 * n22);
                    final int n34 = n8 + n30 + (int)(n30 * n23);
                    graphics2.drawLine(n31, n32, n33, n34);
                    final int n35 = 3;
                    graphics2.drawLine(n33, n34, (int)(n23 * n35 - n22 * n35 + n33), (int)(-n22 * n35 - n23 * n35 + n34));
                    graphics2.drawLine(n33, n34, (int)(-n23 * n35 - n22 * n35 + n33), (int)(n22 * n35 - n23 * n35 + n34));
                }
            }
        }
        if (!this.stopCalc) {
            if (b) {
                this.renderLines(graphics2, b2);
            }
            if (this.equipCheck.getState()) {
                this.renderEquips(graphics2);
            }
        }
        this.chargeRadius = this.winSize.width * 5 / (this.windowWidth * 4);
        for (int k = 0; k < this.chargeCount; ++k) {
            final Charge charge = this.charges[k];
            final int screenX = charge.getScreenX();
            final int screenY = charge.getScreenY();
            final int chargeRadius = this.chargeRadius;
            final double n36 = charge.v * n * 0.4;
            if (n36 < 0.0) {
                int n37 = (int)(-n36 * 191.0) + 64;
                if (n37 > 255) {
                    n37 = 255;
                }
                graphics2.setColor(new Color(64, 64, n37));
            }
            else {
                int n38 = (int)(n36 * 191.0) + 64;
                if (n38 > 255) {
                    n38 = 255;
                }
                graphics2.setColor(new Color(n38, n38, 64));
            }
            graphics2.fillOval(screenX - chargeRadius, screenY - chargeRadius, chargeRadius * 2, chargeRadius * 2);
            if (k == this.selectedCharge) {
                graphics2.setColor(Color.white);
                graphics2.drawOval(screenX - chargeRadius, screenY - chargeRadius, chargeRadius * 2, chargeRadius * 2);
            }
            graphics2.setColor(Color.black);
            graphics2.drawLine(screenX - chargeRadius / 2, screenY, screenX + chargeRadius / 2, screenY);
            if (charge.v > 0.0) {
                graphics2.drawLine(screenX, screenY - chargeRadius / 2, screenX, screenY + chargeRadius / 2);
            }
        }
        if (this.adjustSelectX1 != -1) {
            graphics2.setColor(Color.cyan);
            final int n39 = this.adjustSelectX1 * this.winSize.width / this.windowWidth;
            final int n40 = this.adjustSelectY1 * this.winSize.height / this.windowHeight;
            graphics2.drawRect(n39, n40, (this.adjustSelectX2 + 1) * this.winSize.width / this.windowWidth - n39 - 1, (this.adjustSelectY2 + 1) * this.winSize.height / this.windowHeight - n40 - 1);
        }
        if (this.objDragMap != null) {
            final NumberFormat instance = NumberFormat.getInstance();
            instance.setMaximumFractionDigits(3);
            final FontMetrics fontMetrics2 = graphics2.getFontMetrics();
            graphics2.setColor(Color.black);
            final String string = "Q = " + instance.format(this.getSelObjCharge());
            graphics2.fillRect(0, this.winSize.height - 30, 20 + fontMetrics2.stringWidth(string), 30);
            graphics2.setColor(Color.white);
            graphics2.drawString(string, 10, this.winSize.height - 10);
        }
        graphics.drawImage(this.dbimage, 0, 0, this);
    }
    
    double clamp(final double n) {
        return (n < 0.0) ? 0.0 : ((n > 1.0) ? 1.0 : n);
    }
    
    void doCalc(final boolean b) {
        if (this.stoppedCheck.getState() || this.stopCalc) {
            if (this.changedConductors || this.changedCharges) {
                for (int i = 0; i != this.gridSizeX; ++i) {
                    for (int j = 0; j != this.gridSizeY; ++j) {
                        final GridElement gridElement5;
                        final GridElement gridElement4;
                        final GridElement gridElement3;
                        final GridElement gridElement2;
                        final GridElement gridElement = gridElement2 = (gridElement3 = (gridElement4 = (gridElement5 = this.grid[i][j])));
                        final double n = 0.0;
                        gridElement2.ay = n;
                        gridElement3.ax = n;
                        gridElement4.jy = n;
                        gridElement5.jx = n;
                        if (!gridElement.conductor) {
                            gridElement.pot = 0.0;
                        }
                    }
                }
            }
            return;
        }
        boolean currentPath = false;
        if (this.changedConductors) {
            this.calcExceptions();
            currentPath = this.findCurrentPath();
        }
        final SolverElement[][] array = new SolverElement[this.gridSizeX][this.gridSizeY];
        if (currentPath) {
            for (int k = 0; k != this.gridSizeX; ++k) {
                for (int l = 0; l != this.gridSizeY; ++l) {
                    final GridElement gridElement6 = this.grid[k][l];
                    final SolverElement[] array2 = array[k];
                    final int n2 = l;
                    final SolverElement solverElement = new SolverElement();
                    array2[n2] = solverElement;
                    final SolverElement solverElement2 = solverElement;
                    solverElement2.charge = 0.0;
                    solverElement2.boundary = true;
                    if (gridElement6.currentPath) {
                        solverElement2.pot = ((l == 0) ? 1.0 : ((l == this.gridSizeY - 1) ? -1.0 : 0.0));
                        solverElement2.conductor = (l == 0 || l == this.gridSizeY - 1);
                        solverElement2.ignore = false;
                        solverElement2.dielec = gridElement6.conductivity;
                    }
                    else {
                        solverElement2.ignore = true;
                        solverElement2.dielec = 0.0;
                        solverElement2.pot = 0.0;
                    }
                }
            }
            this.solveCurrent = true;
            this.doSolve(array, 0, this.gridSizeX);
            for (int n3 = 0; n3 != this.gridSizeX; ++n3) {
                for (int n4 = 0; n4 != this.gridSizeY; ++n4) {
                    final GridElement gridElement7 = this.grid[n3][n4];
                    final SolverElement solverElement3 = array[n3][n4];
                    if (gridElement7.currentPath && n3 > 0 && n3 < this.gridSizeX - 1 && n4 > 0 && n4 < this.gridSizeY - 1) {
                        gridElement7.pot = solverElement3.pot;
                        final double n5 = this.grid[n3 - 1][n4].currentPath ? array[n3 - 1][n4].pot : gridElement7.pot;
                        final double n6 = this.grid[n3 + 1][n4].currentPath ? array[n3 + 1][n4].pot : gridElement7.pot;
                        final double n7 = this.grid[n3][n4 - 1].currentPath ? array[n3][n4 - 1].pot : gridElement7.pot;
                        final double n8 = this.grid[n3][n4 + 1].currentPath ? array[n3][n4 + 1].pot : gridElement7.pot;
                        gridElement7.jx = (n5 - gridElement7.pot) * this.grid[n3 - 1][n4].conductivity + (gridElement7.pot - n6) * gridElement7.conductivity;
                        gridElement7.jy = (n7 - gridElement7.pot) * this.grid[n3][n4 - 1].conductivity + (gridElement7.pot - n8) * gridElement7.conductivity;
                    }
                    else {
                        final GridElement gridElement8 = gridElement7;
                        final GridElement gridElement9 = gridElement7;
                        final double n9 = 0.0;
                        gridElement9.jy = n9;
                        gridElement8.jx = n9;
                        if (gridElement7.conductor) {
                            gridElement7.pot = 0.0;
                        }
                    }
                }
            }
            final boolean b2 = true;
            this.changedConductors = b2;
            this.changedMagField = b2;
        }
        else if (this.changedConductors) {
            this.changedMagField = false;
            for (int n10 = 0; n10 != this.gridSizeX; ++n10) {
                for (int n11 = 0; n11 != this.gridSizeY; ++n11) {
                    final GridElement gridElement12;
                    final GridElement gridElement11;
                    final GridElement gridElement10 = gridElement11 = (gridElement12 = this.grid[n10][n11]);
                    final double n12 = 0.0;
                    gridElement10.jy = n12;
                    gridElement10.jx = n12;
                    gridElement11.ay = n12;
                    gridElement12.ax = n12;
                }
            }
        }
        if (this.changedConductors || this.changedCharges) {
            boolean b3 = false;
            for (int n13 = 0; n13 != this.gridSizeX; ++n13) {
                for (int n14 = 0; n14 != this.gridSizeY; ++n14) {
                    final GridElement gridElement13 = this.grid[n13][n14];
                    final SolverElement[] array3 = array[n13];
                    final int n15 = n14;
                    final SolverElement solverElement4 = new SolverElement();
                    array3[n15] = solverElement4;
                    final SolverElement solverElement5 = solverElement4;
                    solverElement5.dielec = gridElement13.dielec;
                    if (gridElement13.conductor) {
                        gridElement13.charge = 0.0;
                        if (gridElement13.floater > 0) {
                            gridElement13.pot = 0.0;
                            b3 = true;
                        }
                        if (n13 < this.gridSizeX - 1 && !this.grid[n13 + 1][n14].conductor) {
                            solverElement5.dielec = this.grid[n13 + 1][n14].dielec;
                        }
                        else if (n14 < this.gridSizeY - 1 && !this.grid[n13][n14 + 1].conductor) {
                            solverElement5.dielec = this.grid[n13][n14 + 1].dielec;
                        }
                    }
                    solverElement5.charge = gridElement13.charge;
                    solverElement5.ignore = false;
                    solverElement5.pot = gridElement13.pot;
                    solverElement5.conductor = gridElement13.conductor;
                    solverElement5.boundary = gridElement13.boundary;
                }
            }
            this.solveCurrent = false;
            this.doSolve(array, 0, this.gridSizeX);
            for (int n16 = 0; n16 != this.gridSizeX; ++n16) {
                for (int n17 = 0; n17 != this.gridSizeY; ++n17) {
                    this.grid[n16][n17].pot = array[n16][n17].pot;
                }
            }
            if (b3) {
                this.doFloater(array);
            }
            else {
                this.floatCharge = 0.0;
            }
        }
        if (this.changedMagField && b) {
            for (int n18 = 0; n18 != this.gridSizeX; ++n18) {
                for (int n19 = 0; n19 != this.gridSizeY; ++n19) {
                    final GridElement gridElement14 = this.grid[n18][n19];
                    final SolverElement[] array4 = array[n18];
                    final int n20 = n19;
                    final SolverElement solverElement6 = new SolverElement();
                    array4[n20] = solverElement6;
                    final SolverElement solverElement7 = solverElement6;
                    solverElement7.charge = gridElement14.jx * 0.01;
                    solverElement7.dielec = 1.0;
                }
            }
            this.doSolve(array, 0, this.gridSizeX);
            for (int n21 = 0; n21 != this.gridSizeX; ++n21) {
                for (int n22 = 0; n22 != this.gridSizeY; ++n22) {
                    final GridElement gridElement15 = this.grid[n21][n22];
                    final SolverElement solverElement8 = array[n21][n22];
                    gridElement15.ax = solverElement8.pot;
                    solverElement8.charge = gridElement15.jy * 0.01;
                    solverElement8.pot = 0.0;
                }
            }
            this.doSolve(array, 0, this.gridSizeX);
            for (int n23 = 0; n23 != this.gridSizeX; ++n23) {
                for (int n24 = 0; n24 != this.gridSizeY; ++n24) {
                    this.grid[n23][n24].ay = array[n23][n24].pot;
                }
            }
            this.changedMagField = false;
        }
        final boolean b4 = false;
        this.changedCharges = b4;
        this.changedConductors = b4;
    }
    
    void checkAdjConductor(final int x, final int y, final Point point) {
        if (point.x == -2) {
            return;
        }
        if (this.grid[x][y].conductor && this.grid[x][y].floater == 0) {
            if (point.x >= 0 && this.grid[x][y].pot != this.grid[point.x][point.y].pot) {
                point.x = -2;
            }
            else {
                point.x = x;
                point.y = y;
            }
        }
    }
    
    void doFloater(final SolverElement[][] array) {
        this.floatExtCharge = 0.0;
        final Point point = new Point(-1, 0);
        for (int i = 0; i != this.gridSizeX; ++i) {
            for (int j = 0; j != this.gridSizeY; ++j) {
                final GridElement gridElement = this.grid[i][j];
                if (gridElement.floater > 0) {
                    final double pot = gridElement.pot;
                    this.floatExtCharge += this.getCharge(i, j);
                    this.checkAdjConductor(i + 1, j, point);
                    this.checkAdjConductor(i - 1, j, point);
                    this.checkAdjConductor(i, j + 1, point);
                    this.checkAdjConductor(i, j - 1, point);
                }
            }
        }
        double pot2 = 0.0;
        boolean b = false;
        if (point.x == -2) {
            System.out.print("two floating potentials!\n");
        }
        else if (point.x != -1) {
            b = true;
            pot2 = this.grid[point.x][point.y].pot;
        }
        if (this.changedConductors) {
            for (int k = 0; k != this.gridSizeX; ++k) {
                for (int l = 0; l != this.gridSizeY; ++l) {
                    final GridElement gridElement2 = this.grid[k][l];
                    final SolverElement solverElement = array[k][l];
                    solverElement.pot = ((gridElement2.conductor && gridElement2.floater > 0) ? 1.0 : 0.0);
                    solverElement.charge = 0.0;
                }
            }
            this.solveCurrent = false;
            this.doSolve(array, 0, this.gridSizeX);
            this.floatCap = 0.0;
            for (int n = 0; n != this.gridSizeX; ++n) {
                for (int n2 = 0; n2 != this.gridSizeY; ++n2) {
                    final GridElement gridElement3 = this.grid[n][n2];
                    final SolverElement solverElement2 = array[n][n2];
                    final GridElement gridElement4 = gridElement3;
                    final double pot3 = solverElement2.pot;
                    gridElement4.floatPot = pot3;
                    final double n3 = pot3;
                    if (gridElement3.floater > 0) {
                        if (!this.grid[n + 1][n2].conductor) {
                            this.floatCap -= array[n + 1][n2].pot - n3;
                        }
                        if (!this.grid[n - 1][n2].conductor) {
                            this.floatCap -= array[n - 1][n2].pot - n3;
                        }
                        if (!this.grid[n][n2 + 1].conductor) {
                            this.floatCap -= array[n][n2 + 1].pot - n3;
                        }
                        if (!this.grid[n][n2 - 1].conductor) {
                            this.floatCap -= array[n][n2 - 1].pot - n3;
                        }
                    }
                }
            }
        }
        double n4;
        if (b) {
            n4 = pot2;
        }
        else {
            n4 = (this.floatCharge - this.floatExtCharge) / this.floatCap;
        }
        for (int n5 = 0; n5 != this.gridSizeX; ++n5) {
            for (int n6 = 0; n6 != this.gridSizeY; ++n6) {
                final GridElement gridElement5;
                gridElement5.pot += (gridElement5 = this.grid[n5][n6]).floatPot * n4;
            }
        }
        if (b) {
            double floatCharge = 0.0;
            for (int n7 = 0; n7 != this.gridSizeX; ++n7) {
                for (int n8 = 0; n8 != this.gridSizeY; ++n8) {
                    if (this.grid[n7][n8].floater > 0) {
                        floatCharge += this.getCharge(n7, n8);
                    }
                }
            }
            this.floatCharge = floatCharge;
        }
    }
    
    void doSolve(final SolverElement[][] array, final int n, final int n2) {
        final int n3 = n2 - 1;
        if (n2 > 3) {
            final int n4 = n2 / 2 + 1;
            SolverElement[][] grid = this.solverGrids[n].grid;
            if (grid == null) {
                final SolverGrid solverGrid = this.solverGrids[n];
                final SolverElement[][] grid2 = new SolverElement[n4][n4];
                solverGrid.grid = grid2;
                grid = grid2;
            }
            for (int i = 0; i != n4; ++i) {
                for (int j = 0; j != n4; ++j) {
                    int n5 = i * 2;
                    int n6 = j * 2;
                    if (n5 >= n2) {
                        n5 = n3;
                    }
                    if (n6 >= n2) {
                        n6 = n3;
                    }
                    if (grid[i][j] == null) {
                        grid[i][j] = new SolverElement();
                    }
                    double charge = array[n5][n6].charge;
                    double dielec = array[n5][n6].dielec;
                    boolean boundary = array[n5][n6].boundary;
                    int ignore = array[n5][n6].ignore ? 1 : 0;
                    int n7 = 1;
                    if (n5 < n3) {
                        charge += array[n5 + 1][n6].charge;
                        dielec += array[n5 + 1][n6].dielec;
                        boundary |= array[n5 + 1][n6].boundary;
                        ignore += (array[n5 + 1][n6].ignore ? 1 : 0);
                        ++n7;
                        if (n6 < n3) {
                            charge += array[n5 + 1][n6 + 1].charge;
                            dielec += array[n5 + 1][n6 + 1].dielec;
                            boundary |= array[n5 + 1][n6 + 1].boundary;
                            ignore += (array[n5 + 1][n6 + 1].ignore ? 1 : 0);
                            ++n7;
                        }
                    }
                    if (n6 < n3) {
                        charge += array[n5][n6 + 1].charge;
                        dielec += array[n5][n6 + 1].dielec;
                        boundary |= array[n5][n6 + 1].boundary;
                        ignore += (array[n5][n6 + 1].ignore ? 1 : 0);
                        ++n7;
                    }
                    grid[i][j].charge = charge;
                    grid[i][j].dielec = dielec / n7;
                    grid[i][j].boundary = boundary;
                    grid[i][j].ignore = (ignore == n7);
                    if (this.solveCurrent) {
                        grid[i][j].dielec = ((ignore == n7) ? 0.0 : (dielec / (4 - ignore)));
                    }
                    int n8 = 0;
                    double n9 = 0.0;
                    if (array[n5][n6].conductor) {
                        ++n8;
                        n9 += array[n5][n6].pot;
                    }
                    if (n5 < n3 && array[n5 + 1][n6].conductor) {
                        ++n8;
                        n9 += array[n5 + 1][n6].pot;
                    }
                    if (n6 < n3 && array[n5][n6 + 1].conductor) {
                        ++n8;
                        n9 += array[n5][n6 + 1].pot;
                    }
                    if (n5 < n3 && n6 < n3 && array[n5 + 1][n6 + 1].conductor) {
                        ++n8;
                        n9 += array[n5 + 1][n6 + 1].pot;
                    }
                    if (n8 > 0 && grid[i][j].charge == 0.0) {
                        grid[i][j].conductor = true;
                        grid[i][j].pot = n9 / n8;
                    }
                    else {
                        grid[i][j].conductor = false;
                        grid[i][j].pot = 0.0;
                    }
                }
            }
            this.doSolve(grid, n + 1, n4);
            for (int k = 1; k != n3; ++k) {
                for (int l = 1; l != n3; ++l) {
                    if (!array[k][l].conductor) {
                        array[k][l].pot = grid[k / 2][l / 2].pot;
                    }
                }
            }
        }
        int n10 = 0;
        double n11 = 0.0;
        int n12 = 200;
        switch (this.accuracyChooser.getSelectedIndex()) {
            case 0: {
                n11 = 1.5000000000000001E-4;
                break;
            }
            case 1: {
                n11 = 7.500000000000001E-5;
                break;
            }
            case 2: {
                n11 = 1.5E-5;
                n12 = 400;
                break;
            }
            case 3: {
                n11 = 1.0E-7;
                if (n == 0) {
                    n12 = 20000;
                    break;
                }
                n12 = 1000;
                break;
            }
        }
        if (n > 1) {
            if (n12 < 400) {
                n12 = 400;
            }
            n11 /= 5.0;
        }
        if (n == 0 && n12 < 1000) {
            n11 /= 2.0;
        }
        double n13;
        do {
            n13 = 0.0;
            for (int n14 = 1; n14 != n3; ++n14) {
                for (int n15 = 1; n15 != n3; ++n15) {
                    final SolverElement solverElement = array[n14][n15];
                    if (!solverElement.conductor) {
                        if (!solverElement.ignore) {
                            double pot;
                            if (solverElement.boundary) {
                                double n16 = array[n14 - 1][n15].pot * array[n14 - 1][n15].dielec;
                                double n17 = array[n14 + 1][n15].pot * array[n14][n15].dielec;
                                double n18 = array[n14][n15 - 1].pot * array[n14][n15 - 1].dielec;
                                double n19 = array[n14][n15 + 1].pot * array[n14][n15].dielec;
                                double n20 = array[n14 - 1][n15].dielec + array[n14][n15].dielec + array[n14][n15 - 1].dielec + array[n14][n15].dielec;
                                if (this.solveCurrent) {
                                    if (array[n14 - 1][n15].ignore) {
                                        n16 = 0.0;
                                        n20 -= array[n14 - 1][n15].dielec;
                                    }
                                    if (array[n14 + 1][n15].ignore) {
                                        n17 = 0.0;
                                        n20 -= array[n14][n15].dielec;
                                    }
                                    if (array[n14][n15 - 1].ignore) {
                                        n18 = 0.0;
                                        n20 -= array[n14][n15 - 1].dielec;
                                    }
                                    if (array[n14][n15 + 1].ignore) {
                                        n19 = 0.0;
                                        n20 -= array[n14][n15].dielec;
                                    }
                                }
                                pot = (n17 + n16 + n19 + n18) / n20 + solverElement.charge / solverElement.dielec;
                            }
                            else {
                                pot = (array[n14 + 1][n15].pot + array[n14 - 1][n15].pot + array[n14][n15 + 1].pot + array[n14][n15 - 1].pot) * 0.25 + solverElement.charge / solverElement.dielec;
                            }
                            n13 += ((pot > solverElement.pot) ? (pot - solverElement.pot) : (solverElement.pot - pot));
                            solverElement.pot = pot;
                        }
                    }
                }
            }
            ++n10;
        } while (n13 / (n2 * n2) >= n11 && n10 != n12);
    }
    
    boolean findCurrentPath() {
        if (!this.currentCheck.getState()) {
            return false;
        }
        for (int i = 0; i != this.gridSizeY; ++i) {
            for (int j = 0; j != this.gridSizeX; ++j) {
                this.grid[j][i].currentPath = false;
            }
        }
        return this.currentPathSearch(0, 1) | this.currentPathSearch(this.gridSizeY - 1, -1);
    }
    
    boolean currentPathSearch(final int n, final int n2) {
        Vector vector = null;
        for (int i = 0; i != this.gridSizeX; ++i) {
            if (this.grid[i][n].conductor) {
                if (vector == null) {
                    vector = new Vector<Point>();
                }
                vector.addElement(new Point(i, n));
            }
        }
        if (vector == null) {
            return false;
        }
        boolean b = false;
        while (vector.size() > 0) {
            final Point point = vector.elementAt(vector.size() - 1);
            vector.removeElementAt(vector.size() - 1);
            final GridElement gridElement = this.grid[point.x][point.y];
            if (gridElement.conductor) {
                if (gridElement.currentPath) {
                    continue;
                }
                gridElement.currentPath = true;
                gridElement.pot = n2;
                if (point.x > 0) {
                    vector.addElement(new Point(point.x - 1, point.y));
                }
                if (point.y > 0) {
                    vector.addElement(new Point(point.x, point.y - 1));
                }
                else if (n != 0) {
                    b = true;
                }
                if (point.x < this.gridSizeX - 1) {
                    vector.addElement(new Point(point.x + 1, point.y));
                }
                if (point.y < this.gridSizeY - 1) {
                    vector.addElement(new Point(point.x, point.y + 1));
                }
                else {
                    if (n != 0) {
                        continue;
                    }
                    b = true;
                }
            }
        }
        return b;
    }
    
    double getCharge(final int n, final int n2) {
        final GridElement gridElement = this.grid[n][n2];
        if (!gridElement.conductor) {
            return gridElement.charge * 3.72;
        }
        return gridElement.charge * 3.72 - (this.grid[n + 1][n2].pot - gridElement.pot) * this.grid[n + 1][n2].dielec - (this.grid[n - 1][n2].pot - gridElement.pot) * this.grid[n - 1][n2].dielec - (this.grid[n][n2 + 1].pot - gridElement.pot) * this.grid[n][n2 + 1].dielec - (this.grid[n][n2 - 1].pot - gridElement.pot) * this.grid[n][n2 - 1].dielec;
    }
    
    double getEField(final GridElement gridElement, final GridElement gridElement2, final GridElement gridElement3) {
        if (gridElement.conductor && !gridElement3.conductor && !gridElement2.conductor) {
            return -gridElement3.pot + gridElement2.pot;
        }
        if (gridElement.dielec != gridElement2.dielec || gridElement.conductor != gridElement2.conductor) {
            return 2.0 * (gridElement.pot - gridElement3.pot);
        }
        if (gridElement.conductor != gridElement3.conductor) {
            return 2.0 * (gridElement2.pot - gridElement.pot);
        }
        return -gridElement3.pot + gridElement2.pot;
    }
    
    double getDField(final GridElement gridElement, final GridElement gridElement2, final GridElement gridElement3, final double n) {
        if (gridElement.conductor && !gridElement3.conductor && !gridElement2.conductor) {
            return (gridElement.pot - gridElement3.pot) * (gridElement.dielec - n) + (gridElement2.pot - gridElement.pot) * (gridElement2.dielec - n);
        }
        if (gridElement.dielec != gridElement2.dielec || gridElement.conductor != gridElement2.conductor) {
            return 2.0 * (gridElement.pot - gridElement3.pot) * (gridElement.dielec - n);
        }
        if (gridElement.conductor != gridElement3.conductor) {
            return 2.0 * (gridElement2.pot - gridElement.pot) * (gridElement2.dielec - n);
        }
        return (gridElement.pot - gridElement3.pot) * (gridElement.dielec - n) + (gridElement2.pot - gridElement.pot) * (gridElement2.dielec - n);
    }
    
    double getPCharge(final GridElement gridElement, final GridElement gridElement2, final GridElement gridElement3) {
        if (gridElement.dielec == gridElement2.dielec) {
            return 0.0;
        }
        return (gridElement.dielec - 1.0) * (gridElement3.pot - gridElement.pot) - (gridElement2.dielec - 1.0) * (gridElement.pot - gridElement2.pot);
    }
    
    int abs(final int n) {
        return (n < 0) ? (-n) : n;
    }
    
    int sign(final int n) {
        return (n < 0) ? -1 : ((n == 0) ? 0 : 1);
    }
    
    void renderLines(final Graphics graphics, final boolean b) {
        double n = 0.0;
        double n2 = 0.0;
        graphics.setColor(Color.white);
        final int n3 = (int)(this.windowWidth * 1.5);
        final int n4 = (int)(this.windowHeight * 1.5);
        if (this.linegrid == null) {
            this.linegrid = new byte[n3 + 1][n4 + 1];
        }
        double n5 = -1.0;
        double n6 = 0.0;
        int n7 = 0;
        final double n8 = this.brightnessBar.getValue() / 5.0;
        int n9 = 0;
        int n10 = 1;
        double n11 = -1.0;
        int n12 = -1;
        int n13 = 0;
        int n14 = 0;
        for (int i = 0; i != n3; ++i) {
            for (int j = 0; j != n4; ++j) {
                this.linegrid[i][j] = 0;
            }
        }
    Block_9:
        while (true) {
            if (n7-- == 0 || n == 0.0) {
                Label_0249: {
                    if (n10 == 1) {
                        while (true) {
                            while (this.linegrid[n13][n14] != 0) {
                                if (++n13 == n3) {
                                    if (++n14 == n4) {
                                        if (n13 == n3 && n14 == n4) {
                                            break Block_9;
                                        }
                                        n5 = n13 / 1.5;
                                        n6 = n14 / 1.5;
                                        break Label_0249;
                                    }
                                    else {
                                        n13 = 0;
                                    }
                                }
                            }
                            continue;
                        }
                    }
                }
                n = n5 + 0.3333333333333333;
                n2 = n6 + 0.3333333333333333;
                n7 = 40;
                n9 = ((n10 == -1) ? 1 : 0);
                n10 = -n10;
            }
            if (n < 0.0 || n2 < 0.0 || n >= this.windowWidth || n2 >= this.windowHeight) {
                n = 0.0;
            }
            else {
                final int n15 = (int)(n * 1.5);
                final int n16 = (int)(n2 * 1.5);
                final byte[] array = this.linegrid[n15];
                final int n17 = n16;
                if (++array[n17] > 2) {
                    n = 0.0;
                }
                else {
                    if (this.linegrid[n15][n16] == 1) {
                        n9 = 1;
                    }
                    final int n18 = this.windowOffsetX + (int)n;
                    final int n19 = this.windowOffsetY + (int)n2;
                    final GridElement gridElement = this.grid[n18][n19];
                    if (!b && gridElement.conductor) {
                        n = 0.0;
                    }
                    else {
                        double eField;
                        double eField2;
                        if (!gridElement.boundary) {
                            eField = -this.grid[n18 + 1][n19].pot + this.grid[n18 - 1][n19].pot;
                            eField2 = -this.grid[n18][n19 + 1].pot + this.grid[n18][n19 - 1].pot;
                        }
                        else {
                            eField = this.getEField(gridElement, this.grid[n18 - 1][n19], this.grid[n18 + 1][n19]);
                            eField2 = this.getEField(gridElement, this.grid[n18][n19 - 1], this.grid[n18][n19 + 1]);
                        }
                        final double sqrt = Math.sqrt(eField * eField + eField2 * eField2);
                        if (sqrt == 0.0) {
                            n = 0.0;
                        }
                        else {
                            final double n20 = eField / sqrt;
                            final double n21 = eField2 / sqrt;
                            final double n22 = n;
                            final double n23 = n2;
                            n += 0.5 * n20 * n10;
                            n2 += 0.5 * n21 * n10;
                            double n24 = sqrt * n8;
                            final int col = this.grid[n18][n19].col;
                            if (n24 != n11 || col != n12) {
                                int n25 = col >> 16 & 0xFF;
                                final int n26 = col >> 8 & 0xFF;
                                int n27 = col & 0xFF;
                                int n28;
                                if (n24 > 1.0) {
                                    if (n24 > 2.0) {
                                        n24 = 2.0;
                                    }
                                    --n24;
                                    n28 = 255;
                                    n25 += (int)(n24 * (255 - n25));
                                    n27 += (int)(n24 * (255 - n27));
                                }
                                else {
                                    n28 = n26 + (int)(n24 * (255 - n26));
                                }
                                final int n29 = 0xFF000000 | n25 << 16 | n28 << 8 | n27;
                                graphics.setColor(new Color(n29));
                                n11 = n24;
                                n12 = n29;
                            }
                            final int n30 = (int)(n22 * this.winSize.width / this.windowWidth);
                            final int n31 = (int)(n23 * this.winSize.height / this.windowHeight);
                            final int n32 = (int)(n * this.winSize.width / this.windowWidth);
                            final int n33 = (int)(n2 * this.winSize.height / this.windowHeight);
                            graphics.drawLine(n30, n31, n32, n33);
                            if (n9 == 0) {
                                continue;
                            }
                            n9 = 0;
                            if ((n15 & 0x3) != 0x0 || (n16 & 0x3) != 0x0) {
                                continue;
                            }
                            final int n34 = 5;
                            graphics.drawLine(n32, n33, (int)(n21 * n34 - n20 * n34 + n32), (int)(-n20 * n34 - n21 * n34 + n33));
                            graphics.drawLine(n32, n33, (int)(-n21 * n34 - n20 * n34 + n32), (int)(n20 * n34 - n21 * n34 + n33));
                        }
                    }
                }
            }
        }
    }
    
    void renderEquips(final Graphics graphics) {
        graphics.setColor(Color.lightGray);
        for (int i = 0; i != this.windowWidth; ++i) {
            for (int j = 0; j != this.windowHeight; ++j) {
                this.tryEdge(graphics, i, j, i + 1, j, i, j + 1, i + 1, j + 1);
                this.tryEdge(graphics, i, j, i + 1, j, i, j, i, j + 1);
                this.tryEdge(graphics, i, j, i + 1, j, i + 1, j, i + 1, j + 1);
                this.tryEdge(graphics, i, j, i, j + 1, i + 1, j, i + 1, j + 1);
                this.tryEdge(graphics, i, j, i, j + 1, i, j + 1, i + 1, j + 1);
                this.tryEdge(graphics, i + 1, j, i + 1, j + 1, i, j + 1, i + 1, j + 1);
            }
        }
    }
    
    void interpPoint(final GridElement gridElement, final GridElement gridElement2, final int n, final int n2, final int n3, final int n4, final double n5, final Point point) {
        final double n6 = (n5 - gridElement.pot) / (gridElement2.pot - gridElement.pot);
        final double n7 = 1.0 - n6;
        point.x = (int)((n + 0.5) * this.winSize.width * n7 / this.windowWidth + (n3 + 0.5) * this.winSize.width * n6 / this.windowWidth);
        point.y = (int)((n2 + 0.5) * this.winSize.height * n7 / this.windowHeight + (n4 + 0.5) * this.winSize.height * n6 / this.windowHeight);
    }
    
    boolean spanning(final GridElement gridElement, final GridElement gridElement2, final double n) {
        return gridElement.pot != gridElement2.pot && (gridElement.pot >= n || gridElement2.pot >= n) && (gridElement.pot <= n || gridElement2.pot <= n);
    }
    
    void tryEdge(final Graphics graphics, final int n, final int n2, final int n3, final int n4, final int n5, final int n6, final int n7, final int n8) {
        final double n9 = 1.0 / (this.brightnessBar.getValue() * (this.equipBar.getValue() * 0.1) * 0.1);
        final GridElement gridElement = this.grid[n + this.windowOffsetX][n2 + this.windowOffsetY];
        final GridElement gridElement2 = this.grid[n3 + this.windowOffsetX][n4 + this.windowOffsetY];
        final GridElement gridElement3 = this.grid[n5 + this.windowOffsetX][n6 + this.windowOffsetY];
        final GridElement gridElement4 = this.grid[n7 + this.windowOffsetX][n8 + this.windowOffsetY];
        final double min = this.min(this.min(gridElement.pot, gridElement2.pot), this.min(gridElement3.pot, gridElement4.pot));
        final double max = this.max(this.max(gridElement.pot, gridElement2.pot), this.max(gridElement3.pot, gridElement4.pot));
        final int n10 = (int)(min / n9);
        for (int n11 = (int)(max / n9), i = n10; i <= n11; ++i) {
            final double n12 = i * n9;
            if (this.spanning(gridElement, gridElement2, n12)) {
                if (this.spanning(gridElement3, gridElement4, n12)) {
                    final Point point = new Point();
                    final Point point2 = new Point();
                    this.interpPoint(gridElement, gridElement2, n, n2, n3, n4, n12, point);
                    this.interpPoint(gridElement3, gridElement4, n5, n6, n7, n8, n12, point2);
                    graphics.drawLine(point.x, point.y, point2.x, point2.y);
                }
            }
        }
    }
    
    void dragCharge(int x, int y) {
        final Charge charge = this.charges[this.selectedCharge];
        if (x < 0 || y < 0 || x >= this.windowWidth || y >= this.windowHeight) {
            return;
        }
        x += this.windowOffsetX;
        y += this.windowOffsetY;
        if (x == charge.x && y == charge.y) {
            return;
        }
        if (!this.legalChargePos(x, y, this.selectedCharge)) {
            return;
        }
        this.grid[charge.x][charge.y].charge = 0.0;
        charge.x = x;
        charge.y = y;
        this.grid[charge.x][charge.y].charge = charge.v;
        this.changedCharges = true;
        this.cv.repaint(this.pause);
    }
    
    boolean emptySquare(final int n, final int n2) {
        return !this.grid[n][n2].conductor && this.grid[n][n2].charge == 0.0;
    }
    
    double getSelObjCharge() {
        double n = 0.0;
        for (int i = 0; i != this.gridSizeX; ++i) {
            for (int j = 0; j != this.gridSizeY; ++j) {
                if (this.objDragMap[i][j]) {
                    n += this.getCharge(i + this.dragObjX, j + this.dragObjY);
                }
            }
        }
        return n;
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
    
    void edit(final MouseEvent mouseEvent) {
        final int x = mouseEvent.getX();
        final int y = mouseEvent.getY();
        if (this.selectedCharge != -1) {
            this.dragCharge(x * this.windowWidth / this.winSize.width, y * this.windowHeight / this.winSize.height);
            return;
        }
        switch (this.modeChooser.getSelectedIndex()) {
            case 0:
            case 2:
            case 3:
            case 11: {}
            default: {
                if (this.modeChooser.getSelectedIndex() < 12) {
                    if (this.dragX == x && this.dragY == y) {
                        this.editFuncPoint(x, y);
                    }
                    else if (this.abs(y - this.dragY) > this.abs(x - this.dragX)) {
                        final int n = (y < this.dragY) ? x : this.dragX;
                        final int n2 = (y < this.dragY) ? y : this.dragY;
                        final int n3 = (y > this.dragY) ? x : this.dragX;
                        final int n4 = (y > this.dragY) ? y : this.dragY;
                        this.dragX = x;
                        this.dragY = y;
                        for (int i = n2; i <= n4; ++i) {
                            this.editFuncPoint(n + (n3 - n) * (i - n2) / (n4 - n2), i);
                        }
                    }
                    else {
                        final int n5 = (x < this.dragX) ? x : this.dragX;
                        final int n6 = (x < this.dragX) ? y : this.dragY;
                        final int n7 = (x > this.dragX) ? x : this.dragX;
                        final int n8 = (x > this.dragX) ? y : this.dragY;
                        this.dragX = x;
                        this.dragY = y;
                        for (int j = n5; j <= n7; ++j) {
                            this.editFuncPoint(j, n6 + (n8 - n6) * (j - n5) / (n7 - n5));
                        }
                    }
                    return;
                }
                final int n9 = x * this.windowWidth / this.winSize.width;
                final int n10 = y * this.windowHeight / this.winSize.height;
                if (this.adjustSelectX1 == -1) {
                    final int n11 = n9;
                    this.adjustSelectX2 = n11;
                    this.adjustSelectX1 = n11;
                    final int n12 = n10;
                    this.adjustSelectY2 = n12;
                    this.adjustSelectY1 = n12;
                    this.adjustBar.enable();
                    return;
                }
                this.adjustSelectX1 = this.max(0, this.min(n9, this.adjustSelectX1));
                this.adjustSelectX2 = this.min(this.windowWidth - 1, this.max(n9, this.adjustSelectX2));
                this.adjustSelectY1 = this.max(0, this.min(n10, this.adjustSelectY1));
                this.adjustSelectY2 = this.min(this.windowHeight - 1, this.max(n10, this.adjustSelectY2));
                this.adjustBar.enable();
                this.cv.repaint(this.pause);
            }
        }
    }
    
    void clearFloaters() {
        for (int i = 0; i != this.gridSizeX; ++i) {
            for (int j = 0; j != this.gridSizeY; ++j) {
                this.grid[i][j].floater = 0;
            }
        }
        this.changedConductors = true;
    }
    
    void editFuncPoint(final int n, final int n2) {
        final int n3 = n * this.windowWidth / this.winSize.width + this.windowOffsetX;
        final int n4 = n2 * this.windowHeight / this.winSize.height + this.windowOffsetY;
        final GridElement gridElement = this.grid[n3][n4];
        if (!this.dragSet && !this.dragClear) {
            this.dragClear = (gridElement.conductor || gridElement.charge != 0.0 || gridElement.dielec != 1.0);
            this.dragSet = !this.dragClear;
        }
        if (gridElement.conductor && gridElement.floater > 0) {
            this.clearFloaters();
        }
        gridElement.conductor = false;
        final GridElement gridElement2 = gridElement;
        final GridElement gridElement3 = gridElement;
        final GridElement gridElement4 = gridElement;
        final double jx = 0.0;
        gridElement4.charge = jx;
        gridElement3.jy = jx;
        gridElement2.jx = jx;
        gridElement.dielec = 1.0;
        this.stopCalc = true;
        switch (this.modeChooser.getSelectedIndex()) {
            case 4: {
                this.dragClear = true;
                this.dragSet = false;
                break;
            }
            case 5: {
                if (this.dragSet) {
                    this.addConductor(n3, n4, 0.0);
                    break;
                }
                break;
            }
            case 6: {
                if (this.dragSet) {
                    this.addConductor(n3, n4, 1.0);
                    break;
                }
                break;
            }
            case 7: {
                if (this.dragSet) {
                    this.addConductor(n3, n4, -1.0);
                    break;
                }
                break;
            }
            case 10: {
                if (this.dragSet) {
                    gridElement.dielec = 2.0;
                    break;
                }
                break;
            }
            case 8: {
                if (this.dragSet) {
                    gridElement.charge = 0.5;
                    break;
                }
                break;
            }
            case 9: {
                if (this.dragSet) {
                    gridElement.charge = -0.5;
                    break;
                }
                break;
            }
        }
        final boolean b = true;
        this.changedConductors = b;
        this.changedCharges = b;
        this.cv.repaint(this.pause);
    }
    
    void addCharge(final int n, final int n2, final double charge) {
        if (this.chargeCount == 20) {
            return;
        }
        if (!this.legalChargePos(n, n2, -1)) {
            return;
        }
        this.charges[this.chargeCount++] = new Charge(n, n2, charge);
        this.grid[n][n2].charge = charge;
        this.changedCharges = true;
        this.cv.repaint(this.pause);
    }
    
    void deleteCharge(int i) {
        final Charge charge = this.charges[i];
        this.grid[charge.x][charge.y].charge = 0.0;
        while (i < this.chargeCount) {
            this.charges[i] = this.charges[i + 1];
            ++i;
        }
        --this.chargeCount;
        this.changedCharges = true;
        this.selectedCharge = -1;
        this.cv.repaint(this.pause);
    }
    
    boolean legalChargePos(final int n, final int n2, final int n3) {
        final Charge charge = (n3 == -1) ? null : this.charges[n3];
        for (int i = -1; i <= 1; ++i) {
            for (int j = -1; j <= 1; ++j) {
                if (charge == null || charge.x != n + i || charge.y != n2 + j) {
                    if (!this.emptySquare(n + i, n2 + j)) {
                        return false;
                    }
                }
            }
        }
        for (int k = 0; k != this.chargeCount; ++k) {
            if (k != n3) {
                final Charge charge2 = this.charges[k];
                if (this.abs(charge2.x - n) <= 2 && this.abs(charge2.y - n2) <= 2) {
                    return false;
                }
            }
        }
        return true;
    }
    
    void selectCharge(final MouseEvent mouseEvent) {
        final int x = mouseEvent.getX();
        final int y = mouseEvent.getY();
        final int selectedCharge = this.selectedCharge;
        this.selectedCharge = -1;
        for (int i = 0; i != this.chargeCount; ++i) {
            final Charge charge = this.charges[i];
            final int screenX = charge.getScreenX();
            final int screenY = charge.getScreenY();
            if (this.chargeRadius * this.chargeRadius > (screenX - x) * (screenX - x) + (screenY - y) * (screenY - y)) {
                this.selectedCharge = i;
                break;
            }
        }
        if (selectedCharge != this.selectedCharge) {
            this.cv.repaint(this.pause);
        }
    }
    
    boolean matchElement(final GridElement gridElement, final GridElement gridElement2) {
        return (gridElement.conductor && gridElement2.conductor && (gridElement.pot == gridElement2.pot || this.currentCheck.getState()) && gridElement.floater == gridElement2.floater && gridElement.conductivity == gridElement2.conductivity) || (gridElement.charge != 0.0 && gridElement.charge == gridElement2.charge) || (gridElement.dielec != 1.0 && gridElement.dielec == gridElement2.dielec);
    }
    
    void selectObject(final int n, final int n2) {
        final boolean b = false;
        this.dragObjY = (b ? 1 : 0);
        this.dragObjX = (b ? 1 : 0);
        final int n3 = n * this.windowWidth / this.winSize.width + this.windowOffsetX;
        final int n4 = n2 * this.windowHeight / this.winSize.height + this.windowOffsetY;
        final boolean b2 = this.objDragMap != null;
        final boolean b3 = b2 && this.objDragMap[n3][n4];
        final GridElement gridElement = this.grid[n3][n4];
        if (!gridElement.conductor && gridElement.dielec == 1.0 && gridElement.charge == 0.0) {
            this.objDragMap = null;
            if (b2) {
                this.cv.repaint(this.pause);
            }
            return;
        }
        if (this.objDragMap != null && this.objDragMap[n3][n4]) {
            return;
        }
        this.objDragMap = new boolean[this.gridSizeX][this.gridSizeY];
        final Vector vector = new Vector<Point>();
        vector.addElement(new Point(n3, n4));
        while (vector.size() > 0) {
            final Point point = vector.elementAt(vector.size() - 1);
            vector.removeElementAt(vector.size() - 1);
            if (this.objDragMap[point.x][point.y]) {
                continue;
            }
            if (!this.matchElement(this.grid[point.x][point.y], gridElement)) {
                continue;
            }
            if (point.x == this.windowOffsetX || point.x == this.windowOffsetX + this.windowWidth - 1 || point.y == this.windowOffsetY || point.y == this.windowOffsetY + this.windowHeight - 1) {
                this.objDragMap = null;
                if (b2) {
                    this.cv.repaint(this.pause);
                }
                return;
            }
            this.objDragMap[point.x][point.y] = true;
            vector.addElement(new Point(point.x - 1, point.y));
            vector.addElement(new Point(point.x, point.y - 1));
            vector.addElement(new Point(point.x + 1, point.y));
            vector.addElement(new Point(point.x, point.y + 1));
        }
        this.dragBoundX1 = 1000;
        this.dragBoundY1 = 1000;
        this.dragBoundX2 = 0;
        this.dragBoundY2 = 0;
        for (int i = 0; i != this.gridSizeX; ++i) {
            for (int j = 0; j != this.gridSizeY; ++j) {
                if (this.objDragMap[i][j]) {
                    if (i < this.dragBoundX1) {
                        this.dragBoundX1 = i;
                    }
                    if (j < this.dragBoundY1) {
                        this.dragBoundY1 = j;
                    }
                    if (i > this.dragBoundX2) {
                        this.dragBoundX2 = i;
                    }
                    if (j > this.dragBoundY2) {
                        this.dragBoundY2 = j;
                    }
                }
            }
        }
        if (!b3) {
            this.cv.repaint(this.pause);
        }
    }
    
    void dragObject(final int n, final int n2) {
        final int n3 = n * this.windowWidth / this.winSize.width + this.windowOffsetX;
        final int n4 = n2 * this.windowHeight / this.winSize.height + this.windowOffsetY;
        final int n5 = this.dragX * this.windowWidth / this.winSize.width + this.windowOffsetX;
        final int n6 = this.dragY * this.windowHeight / this.winSize.height + this.windowOffsetY;
        int dragObjX = n3 - n5;
        int dragObjY = n4 - n6;
        if (dragObjX == this.dragObjX && dragObjY == this.dragObjY) {
            return;
        }
        Label_0241: {
            if (!this.tryDrag(dragObjX, dragObjY)) {
                do {
                    if (dragObjX != this.dragObjX) {
                        dragObjX = ((dragObjX > this.dragObjX) ? (dragObjX - 1) : (dragObjX + 1));
                        if (this.tryDrag(dragObjX, dragObjY)) {
                            break Label_0241;
                        }
                    }
                    if (dragObjY != this.dragObjY) {
                        dragObjY = ((dragObjY > this.dragObjY) ? (dragObjY - 1) : (dragObjY + 1));
                        if (this.tryDrag(dragObjX, dragObjY)) {
                            break Label_0241;
                        }
                        continue;
                    }
                } while (dragObjX != this.dragObjX || dragObjY != this.dragObjY);
                return;
            }
        }
        GridElement copy = null;
        for (int i = this.dragBoundX1; i <= this.dragBoundX2; ++i) {
            for (int j = this.dragBoundY1; j <= this.dragBoundY2; ++j) {
                final int n7 = i + this.dragObjX;
                final int n8 = j + this.dragObjY;
                if (this.objDragMap[i][j]) {
                    final GridElement gridElement = this.grid[n7][n8];
                    copy = gridElement.copy();
                    gridElement.clear();
                }
            }
        }
        for (int k = this.dragBoundX1; k <= this.dragBoundX2; ++k) {
            for (int l = this.dragBoundY1; l <= this.dragBoundY2; ++l) {
                final int n9 = k + dragObjX;
                final int n10 = l + dragObjY;
                if (this.objDragMap[k][l]) {
                    this.grid[n9][n10].set(copy);
                }
            }
        }
        this.dragObjX = dragObjX;
        this.dragObjY = dragObjY;
        this.changedConductors = true;
        this.cv.repaint(this.pause);
    }
    
    boolean tryDrag(final int n, final int n2) {
        if (this.dragBoundX1 + n <= this.windowOffsetX || this.dragBoundY1 + n2 <= this.windowOffsetY || this.dragBoundX2 + n >= this.windowOffsetX + this.windowWidth - 1 || this.dragBoundY2 + n2 >= this.windowOffsetY + this.windowHeight - 1) {
            return false;
        }
        for (int i = this.dragBoundX1; i <= this.dragBoundX2; ++i) {
            for (int j = this.dragBoundY1; j <= this.dragBoundY2; ++j) {
                final int n3 = i + n - this.dragObjX;
                final int n4 = j + n2 - this.dragObjY;
                final int n5 = i + n;
                final int n6 = j + n2;
                try {
                    if (!this.objDragMap[n3][n4] && this.objDragMap[i][j] && (this.grid[n5][n6].conductor || this.grid[n5][n6].dielec != 1.0 || this.grid[n5][n6].charge != 0.0)) {
                        return false;
                    }
                    if (this.objDragMap[i][j]) {
                        for (int k = 0; k != this.chargeCount; ++k) {
                            final Charge charge = this.charges[k];
                            if (this.abs(charge.x - n5) <= 1 && this.abs(charge.y - n6) <= 1) {
                                return false;
                            }
                        }
                    }
                }
                catch (Exception ex) {
                    return false;
                }
            }
        }
        return true;
    }
    
    void deleteObject(final int n, final int n2) {
        final Vector vector = new Vector<Point>();
        vector.addElement(new Point(n, n2));
        final GridElement copy = this.grid[n][n2].copy();
        while (vector.size() > 0) {
            final Point point = vector.elementAt(vector.size() - 1);
            vector.removeElementAt(vector.size() - 1);
            if (point.x >= 0 && point.x < this.gridSizeX && point.y >= 0) {
                if (point.y >= this.gridSizeY) {
                    continue;
                }
                final GridElement gridElement = this.grid[point.x][point.y];
                if (!this.matchElement(gridElement, copy)) {
                    continue;
                }
                gridElement.clear();
                vector.addElement(new Point(point.x - 1, point.y));
                vector.addElement(new Point(point.x, point.y - 1));
                vector.addElement(new Point(point.x + 1, point.y));
                vector.addElement(new Point(point.x, point.y + 1));
            }
        }
        this.changedConductors = true;
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
        this.handleResize();
        this.cv.repaint(100L);
    }
    
    public void actionPerformed(final ActionEvent actionEvent) {
        if (actionEvent.getSource() == this.blankButton) {
            this.doBlank();
            this.cv.repaint(this.pause);
        }
    }
    
    public void adjustmentValueChanged(final AdjustmentEvent adjustmentEvent) {
        System.out.print(((Scrollbar)adjustmentEvent.getSource()).getValue() + "\n");
        this.cv.repaint(this.pause);
        if (adjustmentEvent.getSource() == this.resBar) {
            this.setResolution();
            this.reinit();
        }
        if (adjustmentEvent.getSource() == this.adjustBar) {
            this.doAdjust();
        }
    }
    
    void setResolution() {
        final int n = this.resBar.getValue() + 1;
        this.windowHeight = n;
        this.windowWidth = n;
        final int n2 = 20;
        this.windowOffsetY = n2;
        this.windowOffsetX = n2;
        this.gridSizeX = this.windowWidth + this.windowOffsetX * 2;
        this.gridSizeY = this.windowHeight + this.windowOffsetY * 2;
        this.linegrid = null;
    }
    
    void setResolution(final int value) {
        this.resBar.setValue(value);
        this.setResolution();
        this.reinit();
    }
    
    void doAdjust() {
        if (this.adjustSelectX1 == -1) {
            return;
        }
        int value = this.adjustBar.getValue();
        if (value < 1) {
            value = 1;
        }
        if (value > 99) {
            value = 100;
        }
        float n = value / 100.0f;
        boolean b = true;
        for (int i = this.adjustSelectY1; i <= this.adjustSelectY2; ++i) {
            for (int j = this.adjustSelectX1; j <= this.adjustSelectX2; ++j) {
                final GridElement gridElement = this.grid[j + this.windowOffsetX][i + this.windowOffsetY];
                if (gridElement.conductor || gridElement.dielec != 1.0) {
                    b = false;
                }
            }
        }
        boolean b2 = false;
        double pot = 0.0;
        for (int k = this.adjustSelectY1; k <= this.adjustSelectY2; ++k) {
            for (int l = this.adjustSelectX1; l <= this.adjustSelectX2; ++l) {
                final GridElement gridElement2 = this.grid[l + this.windowOffsetX][k + this.windowOffsetY];
                switch (this.modeChooser.getSelectedIndex()) {
                    case 12: {
                        if (gridElement2.conductor) {
                            gridElement2.conductivity = n;
                        }
                        this.changedConductors = true;
                        break;
                    }
                    case 13: {
                        if (gridElement2.dielec != 1.0 || b) {
                            gridElement2.dielec = (value - 1) / 10.0 + 1.1;
                        }
                        this.changedConductors = true;
                        break;
                    }
                    case 15: {
                        if (value <= 1) {
                            n = 0.0f;
                        }
                        if (value == 50) {
                            n = 0.51f;
                        }
                        if (gridElement2.charge != 0.0) {
                            gridElement2.charge = n * 2.0f - 1.0f;
                        }
                        this.changedConductors = true;
                        break;
                    }
                    case 14: {
                        if (value <= 1) {
                            n = 0.0f;
                        }
                        pot = n * 2.0f - 1.0f;
                        if (b) {
                            this.addConductor(l + this.windowOffsetX, k + this.windowOffsetY);
                        }
                        if (!gridElement2.conductor) {
                            break;
                        }
                        if (gridElement2.floater > 0) {
                            b2 = true;
                            break;
                        }
                        gridElement2.pot = pot;
                        this.changedConductors = true;
                        break;
                    }
                }
            }
        }
        if (b2) {
            this.floatCharge = this.floatExtCharge + this.floatCap * pot;
            this.changedCharges = true;
        }
        this.cv.repaint(this.pause);
        if (this.modeChooser.getSelectedIndex() == 15) {
            for (int n2 = 0; n2 != this.chargeCount; ++n2) {
                final Charge charge = this.charges[n2];
                charge.v = this.grid[charge.x][charge.y].charge;
            }
        }
    }
    
    public void mouseDragged(final MouseEvent mouseEvent) {
        this.dragging = true;
        if (this.objDragMap != null && this.modeChooser.getSelectedIndex() == 0) {
            this.dragObject(mouseEvent.getX(), mouseEvent.getY());
        }
        else {
            this.edit(mouseEvent);
        }
    }
    
    public void mouseMoved(final MouseEvent mouseEvent) {
        if ((mouseEvent.getModifiers() & 0x10) != 0x0) {
            return;
        }
        final int x = mouseEvent.getX();
        final int y = mouseEvent.getY();
        this.dragX = x;
        this.dragY = y;
        this.getPanelHeight();
        this.selectCharge(mouseEvent);
        final int selectedIndex = this.modeChooser.getSelectedIndex();
        if ((selectedIndex == 0 || selectedIndex == 1 || selectedIndex == 11) && this.selectedCharge == -1) {
            this.selectObject(x, y);
        }
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
        this.adjustSelectX1 = -1;
        this.adjustBar.disable();
        final int n = mouseEvent.getX() * this.windowWidth / this.winSize.width + this.windowOffsetX;
        final int n2 = mouseEvent.getY() * this.windowHeight / this.winSize.height + this.windowOffsetY;
        switch (this.modeChooser.getSelectedIndex()) {
            case 2: {
                if (this.selectedCharge == -1) {
                    this.addCharge(n, n2, 0.5);
                    break;
                }
                break;
            }
            case 3: {
                if (this.selectedCharge == -1) {
                    this.addCharge(n, n2, -0.5);
                    break;
                }
                break;
            }
            case 0: {
                this.dragging = true;
                break;
            }
            case 11: {
                if (this.objDragMap != null) {
                    this.clearFloaters();
                    double floatCharge = 0.0;
                    for (int i = 0; i != this.gridSizeX; ++i) {
                        for (int j = 0; j != this.gridSizeY; ++j) {
                            if (this.objDragMap[i][j]) {
                                this.grid[i][j].floater = 1;
                                floatCharge += this.getCharge(i, j);
                            }
                        }
                    }
                    this.floatCharge = floatCharge;
                    this.changedConductors = true;
                    this.cv.repaint(this.pause);
                    break;
                }
                break;
            }
            case 1: {
                if (this.selectedCharge != -1) {
                    this.deleteCharge(this.selectedCharge);
                    break;
                }
                this.deleteObject(n, n2);
                break;
            }
            default: {
                this.dragging = true;
                this.edit(mouseEvent);
                break;
            }
        }
    }
    
    public void mouseReleased(final MouseEvent mouseEvent) {
        if ((mouseEvent.getModifiers() & 0x10) == 0x0) {
            return;
        }
        final boolean b = false;
        this.stopCalc = b;
        this.dragClear = b;
        this.dragSet = b;
        this.dragging = b;
        if (this.objDragMap != null) {
            this.objDragMap = null;
            this.selectObject(mouseEvent.getX(), mouseEvent.getY());
        }
        this.cv.repaint();
    }
    
    public void itemStateChanged(final ItemEvent itemEvent) {
        this.cv.repaint(this.pause);
        if (itemEvent.getItemSelectable() == this.setupChooser) {
            this.doSetup();
        }
        if (itemEvent.getItemSelectable() == this.modeChooser) {
            this.setModeChooser();
        }
        if (itemEvent.getItemSelectable() == this.accuracyChooser || itemEvent.getItemSelectable() == this.currentCheck) {
            this.changedConductors = true;
        }
    }
    
    void setModeChooser() {
        if (this.modeChooser.getSelectedIndex() < 12) {
            this.adjustLabel.hide();
            this.adjustBar.hide();
            this.validate();
            this.adjustSelectX1 = -1;
            return;
        }
        switch (this.modeChooser.getSelectedIndex()) {
            case 12: {
                this.adjustLabel.setText("Conductivity");
                break;
            }
            case 13: {
                this.adjustLabel.setText("Dielectric Constant");
                break;
            }
            case 14: {
                this.adjustLabel.setText("Potential");
                break;
            }
            case 15: {
                this.adjustLabel.setText("Charge");
                break;
            }
        }
        this.adjustLabel.show();
        this.adjustBar.show();
        if (this.adjustSelectX1 == -1) {
            this.adjustBar.disable();
        }
        else {
            this.adjustBar.enable();
        }
        this.validate();
    }
    
    void doSetup() {
        this.t = 0.0;
        this.doBlank();
        this.currentCheck.setState(false);
        this.brightnessBar.setValue(90);
        this.modeChooser.select(0);
        this.setModeChooser();
        (this.setup = this.setupList.elementAt(this.setupChooser.getSelectedIndex())).select();
    }
    
    void doCylinder(final double n, final int n2) {
        final int n3 = this.gridSizeX / 2;
        final int n4 = this.gridSizeY / 2;
        for (int n5 = 8, i = -n5 + 1; i < n5; ++i) {
            for (int n6 = (int)Math.sqrt(n5 * n5 - i * i - 0.01), j = -n6; j != n6; ++j) {
                this.addConductor(n3 + i, n4 + j, n);
                this.grid[n3 + i][n4 + j].floater = (byte)n2;
            }
        }
    }
    
    void doCylinderCharge(final double n, final int n2, final int n3) {
        final int n4 = this.gridSizeX / 2;
        final int n5 = this.gridSizeY / 2;
        for (int i = -n2 + 1; i < n2; ++i) {
            for (int n6 = (int)Math.sqrt(n2 * n2 - i * i - 0.01), j = -n6; j != n6; ++j) {
                final GridElement gridElement = this.grid[n4 + i + n3][n5 + j];
                gridElement.charge += n;
            }
        }
    }
    
    void doDielecCylinder() {
        final int n = this.gridSizeX / 2;
        final int n2 = this.gridSizeY / 2;
        for (int n3 = 8, i = -n3 + 1; i < n3; ++i) {
            for (int n4 = (int)Math.sqrt(n3 * n3 - i * i - 0.01), j = -n4; j != n4; ++j) {
                this.grid[n + i][n2 + j].dielec = 5.0;
            }
        }
    }
    
    void addConductor(final int n, final int n2) {
        this.addConductor(n, n2, 0.0, 1.0);
    }
    
    void addConductor(final int n, final int n2, final double n3) {
        this.addConductor(n, n2, n3, 1.0);
    }
    
    void addConductor(final int n, final int n2, final double pot, final double conductivity) {
        final GridElement gridElement = this.grid[n][n2];
        gridElement.conductor = true;
        gridElement.pot = pot;
        gridElement.conductivity = conductivity;
        gridElement.floater = 0;
    }
    
    void conductFillRect(final int n, final int n2, final int n3, final int n4, final double n5, final double n6) {
        for (int i = n; i <= n3; ++i) {
            for (int j = n2; j <= n4; ++j) {
                this.addConductor(i, j, n5, n6);
            }
        }
    }
    
    void conductDrawRect(final int n, final int n2, final int n3, final int n4, final double n5, final double n6) {
        for (int i = n; i <= n3; ++i) {
            this.addConductor(i, n2, n5, n6);
            this.addConductor(i, n4, n5, n6);
        }
        for (int j = n2; j <= n4; ++j) {
            this.addConductor(n, j, n5, n6);
            this.addConductor(n3, j, n5, n6);
        }
    }
    
    class Charge
    {
        int x;
        int y;
        double v;
        
        Charge(final int x, final int y, final double v) {
            this.x = x;
            this.y = y;
            this.v = v;
        }
        
        int getScreenX() {
            return ((this.x - EMStaticFrame.this.windowOffsetX) * EMStaticFrame.this.winSize.width + EMStaticFrame.this.winSize.width / 2) / EMStaticFrame.this.windowWidth;
        }
        
        int getScreenY() {
            return ((this.y - EMStaticFrame.this.windowOffsetY) * EMStaticFrame.this.winSize.height + EMStaticFrame.this.winSize.height / 2) / EMStaticFrame.this.windowHeight;
        }
    }
    
    class GridElement
    {
        double pot;
        double jx;
        double jy;
        double ax;
        double ay;
        double dielec;
        double conductivity;
        double charge;
        double floatPot;
        int col;
        boolean conductor;
        boolean boundary;
        boolean currentPath;
        byte floater;
        
        void clear() {
            final double n = 0.0;
            this.charge = n;
            this.pot = n;
            final double n2 = 1.0;
            this.conductivity = n2;
            this.dielec = n2;
            this.conductor = false;
            this.floater = 0;
        }
        
        GridElement copy() {
            final GridElement gridElement = new GridElement();
            gridElement.pot = this.pot;
            gridElement.dielec = this.dielec;
            gridElement.conductivity = this.conductivity;
            gridElement.conductor = this.conductor;
            gridElement.charge = this.charge;
            gridElement.floater = this.floater;
            return gridElement;
        }
        
        void set(final GridElement gridElement) {
            this.pot = gridElement.pot;
            this.dielec = gridElement.dielec;
            this.conductivity = gridElement.conductivity;
            this.conductor = gridElement.conductor;
            this.charge = gridElement.charge;
            this.floater = gridElement.floater;
        }
    }
    
    class SolverElement
    {
        double charge;
        double dielec;
        double pot;
        boolean conductor;
        boolean boundary;
        boolean ignore;
    }
    
    class SolverGrid
    {
        SolverElement[][] grid;
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
        
        abstract Setup createNext();
    }
    
    class SingleChargeSetup extends Setup
    {
        String getName() {
            return "Single Charge";
        }
        
        void select() {
            EMStaticFrame.this.addCharge(EMStaticFrame.this.gridSizeX / 2, EMStaticFrame.this.gridSizeY / 2, 0.5);
        }
        
        Setup createNext() {
            return new DoubleChargeSetup();
        }
    }
    
    class DoubleChargeSetup extends Setup
    {
        String getName() {
            return "Double Charge";
        }
        
        void select() {
            final int n = EMStaticFrame.this.gridSizeX / 2;
            final int n2 = EMStaticFrame.this.gridSizeY / 2;
            EMStaticFrame.this.addCharge(n, n2 - 6, 0.5);
            EMStaticFrame.this.addCharge(n, n2 + 6, 0.5);
        }
        
        Setup createNext() {
            return new DipoleChargeSetup();
        }
    }
    
    class DipoleChargeSetup extends Setup
    {
        String getName() {
            return "Dipole Charge";
        }
        
        void select() {
            final int n = EMStaticFrame.this.gridSizeX / 2;
            final int n2 = EMStaticFrame.this.gridSizeY / 2;
            EMStaticFrame.this.addCharge(n, n2 - 5, 0.5);
            EMStaticFrame.this.addCharge(n, n2 + 5, -0.5);
        }
        
        Setup createNext() {
            return new ChargePlaneSetup();
        }
    }
    
    class ChargePlaneSetup extends Setup
    {
        String getName() {
            return "Charge + Plane";
        }
        
        void select() {
            final int n = EMStaticFrame.this.gridSizeX / 2;
            final int n2 = EMStaticFrame.this.gridSizeY / 2;
            EMStaticFrame.this.addCharge(n, n2 - 5, 0.5);
            EMStaticFrame.this.conductFillRect(EMStaticFrame.this.windowOffsetX + 1, n2, EMStaticFrame.this.windowOffsetX + EMStaticFrame.this.windowWidth - 2, n2 + 2, 0.0, 1.0);
        }
        
        Setup createNext() {
            return new DipoleUniformSetup();
        }
    }
    
    class DipoleUniformSetup extends Setup
    {
        String getName() {
            return "Dipole + Uniform";
        }
        
        void select() {
            final int n = EMStaticFrame.this.gridSizeX / 2;
            final int n2 = EMStaticFrame.this.gridSizeY / 2;
            EMStaticFrame.this.addCharge(n, n2 - 4, 0.5);
            EMStaticFrame.this.addCharge(n, n2 + 4, -0.5);
            EMStaticFrame.this.addUniformField();
        }
        
        Setup createNext() {
            return new QuadChargeSetup();
        }
    }
    
    class QuadChargeSetup extends Setup
    {
        String getName() {
            return "Quadrupole Charge";
        }
        
        void select() {
            final int n = EMStaticFrame.this.gridSizeX / 2;
            final int n2 = EMStaticFrame.this.gridSizeY / 2;
            EMStaticFrame.this.addCharge(n + 4, n2 - 4, 0.5);
            EMStaticFrame.this.addCharge(n + 4, n2 + 4, -0.5);
            EMStaticFrame.this.addCharge(n - 4, n2 - 4, -0.5);
            EMStaticFrame.this.addCharge(n - 4, n2 + 4, 0.5);
        }
        
        Setup createNext() {
            return new ConductingPlanesSetup();
        }
    }
    
    class ConductingPlanesSetup extends Setup
    {
        String getName() {
            return "Conducting Planes";
        }
        
        void select() {
            final int n = EMStaticFrame.this.gridSizeX / 2;
            final int n2 = EMStaticFrame.this.gridSizeY / 2;
            final int n3 = 4;
            final int n4 = EMStaticFrame.this.windowWidth * 2 / 6;
            EMStaticFrame.this.conductFillRect(n - n4, n2 - n3 - 2, n + n4, n2 - n3, 1.0, 1.0);
            EMStaticFrame.this.conductFillRect(n - n4, n2 + n3, n + n4, n2 + n3 + 2, -1.0, 1.0);
            EMStaticFrame.this.brightnessBar.setValue(35);
        }
        
        Setup createNext() {
            return new ChargedPlanesSetup();
        }
    }
    
    class ChargedPlanesSetup extends Setup
    {
        String getName() {
            return "Charged Planes";
        }
        
        void select() {
            final int n = EMStaticFrame.this.gridSizeX / 2;
            final int n2 = EMStaticFrame.this.gridSizeY / 2;
            final int n3 = 4;
            final int n4 = EMStaticFrame.this.windowWidth * 2 / 6;
            final double charge = 1.0 / (n4 * 3);
            for (int i = 0; i != 3; ++i) {
                for (int j = -n4; j <= n4; ++j) {
                    EMStaticFrame.this.grid[n + j][n2 - n3 - i].charge = charge;
                    EMStaticFrame.this.grid[n + j][n2 + n3 + i].charge = -charge;
                }
            }
            EMStaticFrame.this.brightnessBar.setValue(35);
        }
        
        Setup createNext() {
            return new ConductingCylinderSetup();
        }
    }
    
    class ConductingCylinderSetup extends Setup
    {
        String getName() {
            return "Conducting Cylinder";
        }
        
        void select() {
            EMStaticFrame.this.doCylinder(1.0, 0);
        }
        
        Setup createNext() {
            return new GroundedCylinderSetup();
        }
    }
    
    class GroundedCylinderSetup extends Setup
    {
        String getName() {
            return "Grounded Cyl + Charge";
        }
        
        void select() {
            EMStaticFrame.this.doCylinder(0.0, 0);
            EMStaticFrame.this.addCharge(EMStaticFrame.this.gridSizeX / 2, EMStaticFrame.this.gridSizeY / 2 + 7 * 2, 0.5);
        }
        
        Setup createNext() {
            return new GroundedCylinderUniformSetup();
        }
    }
    
    class GroundedCylinderUniformSetup extends Setup
    {
        String getName() {
            return "Grounded Cyl + Field";
        }
        
        void select() {
            EMStaticFrame.this.doCylinder(0.0, 0);
            EMStaticFrame.this.addUniformField();
        }
        
        Setup createNext() {
            return new ChargedCylinderSetup();
        }
    }
    
    class ChargedCylinderSetup extends Setup
    {
        String getName() {
            return "Charged Cylinder";
        }
        
        void select() {
            EMStaticFrame.this.doCylinderCharge(0.005, 10, 0);
            EMStaticFrame.this.brightnessBar.setValue(50);
        }
        
        Setup createNext() {
            return new ChargedHollowCylinder1Setup();
        }
    }
    
    class ChargedHollowCylinder1Setup extends Setup
    {
        String getName() {
            return "Charged Hollow Cyl 1";
        }
        
        void select() {
            EMStaticFrame.this.doCylinderCharge(0.005, 10, 0);
            EMStaticFrame.this.doCylinderCharge(-0.005, 5, 0);
        }
        
        Setup createNext() {
            return new ChargedHollowCylinder2Setup();
        }
    }
    
    class ChargedHollowCylinder2Setup extends Setup
    {
        String getName() {
            return "Charged Hollow Cyl 2";
        }
        
        void select() {
            EMStaticFrame.this.doCylinderCharge(0.005, 10, 0);
            EMStaticFrame.this.doCylinderCharge(-0.005, 5, 2);
        }
        
        Setup createNext() {
            return new FloatingCylinderSetup();
        }
    }
    
    class FloatingCylinderSetup extends Setup
    {
        String getName() {
            return "Floating Cyl + Charge";
        }
        
        void select() {
            EMStaticFrame.this.doCylinder(1.0, 1);
            EMStaticFrame.this.addCharge(EMStaticFrame.this.gridSizeX / 2 + 7, EMStaticFrame.this.gridSizeY / 2 + 7, 0.5);
        }
        
        Setup createNext() {
            return new FloatingCylinder2Setup();
        }
    }
    
    class FloatingCylinder2Setup extends Setup
    {
        String getName() {
            return "Floating Cyl + Plates";
        }
        
        void select() {
            EMStaticFrame.this.doCylinder(1.0, 1);
            EMStaticFrame.this.conductFillRect(EMStaticFrame.this.gridSizeX / 2 - EMStaticFrame.this.windowWidth / 3, EMStaticFrame.this.windowOffsetY, EMStaticFrame.this.gridSizeX / 2 + EMStaticFrame.this.windowWidth / 3, EMStaticFrame.this.windowOffsetY + 2, 1.0, 1.0);
            EMStaticFrame.this.conductFillRect(EMStaticFrame.this.gridSizeX / 2 - EMStaticFrame.this.windowWidth / 3, EMStaticFrame.this.windowOffsetY + EMStaticFrame.this.windowHeight - 3, EMStaticFrame.this.gridSizeX / 2 + EMStaticFrame.this.windowWidth / 3, EMStaticFrame.this.windowOffsetY + EMStaticFrame.this.windowHeight - 1, -1.0, 1.0);
        }
        
        Setup createNext() {
            return new ConductingBoxSetup();
        }
    }
    
    class ConductingBoxSetup extends Setup
    {
        String getName() {
            return "Conducting Box";
        }
        
        void select() {
            for (int n = EMStaticFrame.this.windowWidth / 5, i = n - 2; i <= n; ++i) {
                EMStaticFrame.this.conductDrawRect(EMStaticFrame.this.gridSizeX / 2 - i, EMStaticFrame.this.gridSizeY / 2 - i, EMStaticFrame.this.gridSizeX / 2 + i, EMStaticFrame.this.gridSizeY / 2 + i, 1.0, 1.0);
            }
        }
        
        Setup createNext() {
            return new SharpPointSetup();
        }
    }
    
    class SharpPointSetup extends Setup
    {
        String getName() {
            return "Sharp Point";
        }
        
        void select() {
            EMStaticFrame.this.conductFillRect(EMStaticFrame.this.gridSizeX / 2 - 1, EMStaticFrame.this.gridSizeY / 2 - 1, EMStaticFrame.this.gridSizeX / 2 + 1, EMStaticFrame.this.gridSizeY - 1, 1.0, 1.0);
        }
        
        Setup createNext() {
            return new CornerSetup();
        }
    }
    
    class CornerSetup extends Setup
    {
        String getName() {
            return "Corner";
        }
        
        void select() {
            EMStaticFrame.this.conductFillRect(EMStaticFrame.this.gridSizeX / 2 - 1, EMStaticFrame.this.gridSizeY / 2 - 1, EMStaticFrame.this.gridSizeX / 2 + 1, EMStaticFrame.this.gridSizeY - 1, 1.0, 1.0);
            EMStaticFrame.this.conductFillRect(EMStaticFrame.this.gridSizeX / 2 - 1, EMStaticFrame.this.gridSizeY / 2 - 1, EMStaticFrame.this.gridSizeX - 1, EMStaticFrame.this.gridSizeY / 2 + 1, 1.0, 1.0);
        }
        
        Setup createNext() {
            return new Angle45Setup();
        }
    }
    
    class Angle45Setup extends Setup
    {
        String getName() {
            return "45 Degrees";
        }
        
        void select() {
            final int n = 4;
            for (int i = -1; i != EMStaticFrame.this.windowWidth / 2 + n * 2; ++i) {
                EMStaticFrame.this.conductFillRect(EMStaticFrame.this.gridSizeX / 2 + i - n - 2, EMStaticFrame.this.gridSizeY / 2 + n - i, EMStaticFrame.this.gridSizeX / 2 + i - n + 1, EMStaticFrame.this.gridSizeY / 2 + n - i, 1.0, 1.0);
            }
            EMStaticFrame.this.conductFillRect(EMStaticFrame.this.gridSizeX / 2 - n, EMStaticFrame.this.gridSizeY / 2 - 1 + n, EMStaticFrame.this.gridSizeX - 1, EMStaticFrame.this.gridSizeY / 2 + 1 + n, 1.0, 1.0);
        }
        
        Setup createNext() {
            return new Angle135Setup();
        }
    }
    
    class Angle135Setup extends Setup
    {
        String getName() {
            return "135 Degrees";
        }
        
        void select() {
            final int n = 0;
            for (int i = -1; i != EMStaticFrame.this.windowWidth / 2 + 2; ++i) {
                EMStaticFrame.this.conductFillRect(EMStaticFrame.this.gridSizeX / 2 + i - n - 2, EMStaticFrame.this.gridSizeY / 2 + n - i, EMStaticFrame.this.gridSizeX / 2 + i - n + 1, EMStaticFrame.this.gridSizeY / 2 + n - i, 1.0, 1.0);
            }
            EMStaticFrame.this.conductFillRect(0, EMStaticFrame.this.gridSizeY / 2 - 1 + n, EMStaticFrame.this.gridSizeX / 2 - n, EMStaticFrame.this.gridSizeY / 2 + 1 + n, 1.0, 1.0);
        }
        
        Setup createNext() {
            return new DielectricCylinderSetup();
        }
    }
    
    class DielectricCylinderSetup extends Setup
    {
        String getName() {
            return "Dielectric Cylinder";
        }
        
        void select() {
            EMStaticFrame.this.doDielecCylinder();
            final int n = EMStaticFrame.this.gridSizeX / 2;
            final int n2 = EMStaticFrame.this.gridSizeY / 2;
            final int n3 = 8;
            EMStaticFrame.this.addCharge(n + n3 * 3 / 2, n2 + n3 * 3 / 2, 0.5);
        }
        
        Setup createNext() {
            return new DielectricCylinderFieldSetup();
        }
    }
    
    class DielectricCylinderFieldSetup extends Setup
    {
        String getName() {
            return "Dielectric Cyl + Field";
        }
        
        void select() {
            EMStaticFrame.this.doDielecCylinder();
            EMStaticFrame.this.addUniformField();
        }
        
        Setup createNext() {
            return new Dielectric1Setup();
        }
    }
    
    class Dielectric1Setup extends Setup
    {
        String getName() {
            return "Dielectric 1";
        }
        
        void select() {
            EMStaticFrame.this.doDielec(6.0);
            EMStaticFrame.this.addCharge(EMStaticFrame.this.gridSizeX / 2, EMStaticFrame.this.gridSizeY / 2 - 5, 0.5);
            EMStaticFrame.this.brightnessBar.setValue(250);
        }
        
        Setup createNext() {
            return new Dielectric2Setup();
        }
    }
    
    class Dielectric2Setup extends Setup
    {
        String getName() {
            return "Dielectric 2";
        }
        
        void select() {
            EMStaticFrame.this.doDielec(6.0);
            EMStaticFrame.this.addCharge(EMStaticFrame.this.gridSizeX / 2, EMStaticFrame.this.gridSizeY / 2 + 5, 0.5);
            EMStaticFrame.this.brightnessBar.setValue(250);
        }
        
        Setup createNext() {
            return new DielectricDipoleSetup();
        }
    }
    
    class DielectricDipoleSetup extends Setup
    {
        String getName() {
            return "Dielectric + Dipole";
        }
        
        void select() {
            EMStaticFrame.this.doDielec(3.0);
            final int n = EMStaticFrame.this.gridSizeX / 2;
            final int n2 = EMStaticFrame.this.gridSizeY / 2;
            EMStaticFrame.this.addCharge(n + 8, n2 - 4, 0.5);
            EMStaticFrame.this.addCharge(n - 8, n2 + 4, -0.5);
        }
        
        Setup createNext() {
            return new DielecCapSetup();
        }
    }
    
    class DielecCapSetup extends Setup
    {
        String getName() {
            return "Dielectric Capacitor";
        }
        
        void select() {
            final int n = EMStaticFrame.this.gridSizeX / 2;
            final int n2 = EMStaticFrame.this.gridSizeY / 2;
            final int n3 = 2;
            final int n4 = EMStaticFrame.this.windowWidth / 4;
            EMStaticFrame.this.conductFillRect(n - n4, n2 - n3 - 2, n + n4, n2 - n3, 1.0, 1.0);
            EMStaticFrame.this.conductFillRect(n - n4, n2 + n3, n + n4, n2 + n3 + 2, -1.0, 1.0);
            for (int i = -n4 + 2; i <= n4 - 2; ++i) {
                for (int j = -n3 + 1; j < n3; ++j) {
                    EMStaticFrame.this.grid[n + i][n2 + j].dielec = 5.0;
                }
            }
            EMStaticFrame.this.brightnessBar.setValue(12);
        }
        
        Setup createNext() {
            return new ConductingPlanesGapSetup();
        }
    }
    
    class ConductingPlanesGapSetup extends Setup
    {
        String getName() {
            return "Conducting Planes w/ Gap";
        }
        
        void select() {
            final int n = EMStaticFrame.this.gridSizeY / 2;
            final int n2 = 4;
            EMStaticFrame.this.conductFillRect(0, n - 1, EMStaticFrame.this.gridSizeX / 2 - n2 - 1, n + 1, 1.0, 1.0);
            EMStaticFrame.this.conductFillRect(EMStaticFrame.this.gridSizeX / 2 + n2, n - 1, EMStaticFrame.this.gridSizeX - 1, n + 1, -1.0, 1.0);
        }
        
        Setup createNext() {
            return new SlottedConductingPlaneSetup();
        }
    }
    
    class SlottedConductingPlaneSetup extends Setup
    {
        String getName() {
            return "Slotted Conducting Plane";
        }
        
        void select() {
            final int n = EMStaticFrame.this.gridSizeY / 2;
            final int n2 = 4;
            EMStaticFrame.this.conductFillRect(0, n - 1, EMStaticFrame.this.gridSizeX / 2 - n2 - 1, n + 1, 0.0, 1.0);
            EMStaticFrame.this.conductFillRect(EMStaticFrame.this.gridSizeX / 2 + n2, n - 1, EMStaticFrame.this.gridSizeX - 1, n + 1, 0.0, 1.0);
            EMStaticFrame.this.conductFillRect(0, EMStaticFrame.this.windowOffsetY, EMStaticFrame.this.gridSizeX - 1, EMStaticFrame.this.windowOffsetY, 1.0, 1.0);
            EMStaticFrame.this.brightnessBar.setValue(960);
        }
        
        Setup createNext() {
            return new Shielding1Setup();
        }
    }
    
    class Shielding1Setup extends Setup
    {
        String getName() {
            return "Shielding 1";
        }
        
        void select() {
            for (int i = 6; i <= 8; ++i) {
                EMStaticFrame.this.conductDrawRect(EMStaticFrame.this.gridSizeX / 2 - i, EMStaticFrame.this.gridSizeY / 2 - i, EMStaticFrame.this.gridSizeX / 2 + i, EMStaticFrame.this.gridSizeY / 2 + i, 0.0, 1.0);
            }
            EMStaticFrame.this.addUniformField();
        }
        
        Setup createNext() {
            return new Shielding2Setup();
        }
    }
    
    class Shielding2Setup extends Setup
    {
        String getName() {
            return "Shielding 2";
        }
        
        void select() {
            final int n = EMStaticFrame.this.windowWidth / 4;
            for (int n2 = n + 2, i = n; i <= n2; ++i) {
                EMStaticFrame.this.conductDrawRect(EMStaticFrame.this.gridSizeX / 2 - i, EMStaticFrame.this.gridSizeY / 2 - i, EMStaticFrame.this.gridSizeX / 2 + i, EMStaticFrame.this.gridSizeY / 2 + i, 0.0, 1.0);
            }
            EMStaticFrame.this.addCharge(EMStaticFrame.this.gridSizeX / 2, EMStaticFrame.this.gridSizeY / 2, 0.5);
        }
        
        Setup createNext() {
            return new BoxOneSideSetup();
        }
    }
    
    class BoxOneSideSetup extends Setup
    {
        String getName() {
            return "Box w/ One Live Side";
        }
        
        void select() {
            final int n = EMStaticFrame.this.windowWidth / 4;
            final int n2 = n + 2;
            final int n3 = EMStaticFrame.this.gridSizeX / 2;
            final int n4 = EMStaticFrame.this.gridSizeY / 2;
            for (int i = n; i <= n2; ++i) {
                EMStaticFrame.this.conductDrawRect(n3 - i, n4 - i, n3 + i, n4 + i, 0.0, 1.0);
                EMStaticFrame.this.grid[n3 - n + 1][n4 - i].conductor = false;
                EMStaticFrame.this.grid[n3 + n - 1][n4 - i].conductor = false;
            }
            EMStaticFrame.this.conductFillRect(n3 - n + 2, n4 - n2, n3 + n - 2, n4 - n, 1.0, 1.0);
        }
        
        Setup createNext() {
            return new QuadrupoleLensSetup();
        }
    }
    
    class QuadrupoleLensSetup extends Setup
    {
        String getName() {
            return "Quadrupole Lens";
        }
        
        void select() {
            final int n = EMStaticFrame.this.gridSizeX / 2 - 1;
            final int n2 = EMStaticFrame.this.windowWidth / 4;
            final int n3 = EMStaticFrame.this.gridSizeX / 2;
            final int n4 = EMStaticFrame.this.gridSizeY / 2;
            for (int i = -n; i <= n; ++i) {
                for (int j = (int)Math.sqrt(i * i + n2 * n2); j <= n; ++j) {
                    EMStaticFrame.this.addConductor(n3 + i, n4 + j, -1.0);
                    EMStaticFrame.this.addConductor(n3 + i, n4 - j, -1.0);
                    EMStaticFrame.this.addConductor(n3 + j, n4 + i, 1.0);
                    EMStaticFrame.this.addConductor(n3 - j, n4 + i, 1.0);
                }
            }
            EMStaticFrame.this.brightnessBar.setValue(24);
        }
        
        Setup createNext() {
            return new ConductingWireSetup();
        }
    }
    
    class ConductingWireSetup extends Setup
    {
        String getName() {
            return "Wire w/ Current";
        }
        
        void select() {
            final int n = EMStaticFrame.this.gridSizeX / 2;
            final int n2 = 8;
            EMStaticFrame.this.conductFillRect(n - n2 / 2, 0, n + n2 / 2, EMStaticFrame.this.gridSizeY - 1, 0.0, 1.0);
            EMStaticFrame.this.currentCheck.setState(true);
        }
        
        Setup createNext() {
            return new ResistorSetup();
        }
    }
    
    class ResistorSetup extends Setup
    {
        String getName() {
            return "Resistor";
        }
        
        void select() {
            final int n = EMStaticFrame.this.gridSizeX / 2;
            final int n2 = 8;
            EMStaticFrame.this.conductFillRect(n - n2 / 2, 0, n + n2 / 2, EMStaticFrame.this.gridSizeY / 2 - 6, 0.0, 1.0);
            EMStaticFrame.this.conductFillRect(n - n2 / 2, EMStaticFrame.this.gridSizeY / 2 + 6, n + n2 / 2, EMStaticFrame.this.gridSizeY - 1, 0.0, 1.0);
            EMStaticFrame.this.conductFillRect(n - n2 / 2 + 1, EMStaticFrame.this.gridSizeY / 2 - 5, n + n2 / 2 - 1, EMStaticFrame.this.gridSizeY / 2 + 5, 0.0, 0.1);
            EMStaticFrame.this.currentCheck.setState(true);
        }
        
        Setup createNext() {
            return new ResistorsParallelSetup();
        }
    }
    
    class ResistorsParallelSetup extends Setup
    {
        String getName() {
            return "Resistors in Parallel";
        }
        
        void select() {
            final int n = EMStaticFrame.this.gridSizeX / 2;
            final int n2 = 8;
            final int n3 = n2 / 2;
            EMStaticFrame.this.conductFillRect(n - n3, 0, n + n3, EMStaticFrame.this.gridSizeY / 2 - n3 - 1, 0.0, 1.0);
            EMStaticFrame.this.conductFillRect(n - n3, EMStaticFrame.this.gridSizeY / 2 + n3 + 1, n + n3, EMStaticFrame.this.gridSizeY - 1, 0.0, 1.0);
            EMStaticFrame.this.conductFillRect(n - EMStaticFrame.this.windowWidth / 4, EMStaticFrame.this.gridSizeY / 2 - n2, n + EMStaticFrame.this.windowWidth / 4, EMStaticFrame.this.gridSizeY / 2 - n3 - 1, 0.0, 1.0);
            EMStaticFrame.this.conductFillRect(n - EMStaticFrame.this.windowWidth / 4, EMStaticFrame.this.gridSizeY / 2 + n3 + 1, n + EMStaticFrame.this.windowWidth / 4, EMStaticFrame.this.gridSizeY / 2 + n2, 0.0, 1.0);
            EMStaticFrame.this.conductFillRect(n - EMStaticFrame.this.windowWidth / 4, EMStaticFrame.this.gridSizeY / 2 - n3, n - EMStaticFrame.this.windowWidth / 4 + 4, EMStaticFrame.this.gridSizeY / 2 + n3, 0.0, 0.6);
            EMStaticFrame.this.conductFillRect(n + EMStaticFrame.this.windowWidth / 4 - 4, EMStaticFrame.this.gridSizeY / 2 - n3, n + EMStaticFrame.this.windowWidth / 4, EMStaticFrame.this.gridSizeY / 2 + n3, 0.0, 0.1);
            EMStaticFrame.this.conductFillRect(n - 2, EMStaticFrame.this.gridSizeY / 2 - n3, n + 2, EMStaticFrame.this.gridSizeY / 2 + n3, 0.0, 0.04);
            EMStaticFrame.this.currentCheck.setState(true);
        }
        
        Setup createNext() {
            return new Current2D1Setup();
        }
    }
    
    class Current2D1Setup extends Setup
    {
        String getName() {
            return "Current in 2D 1";
        }
        
        void select() {
            final int n = EMStaticFrame.this.gridSizeX / 2;
            final int n2 = EMStaticFrame.this.gridSizeY / 2;
            final int n3 = EMStaticFrame.this.windowWidth / 3;
            final int n4 = 4;
            EMStaticFrame.this.conductFillRect(n - n3, n2 - n3, n + n3, n2 + n3, 0.0, 1.0);
            EMStaticFrame.this.conductFillRect(n - n3, 0, n - n3 + n4, n2, 0.0, 1.0);
            EMStaticFrame.this.conductFillRect(n + n3 - n4, 0, n + n3, EMStaticFrame.this.gridSizeY - 1, 0.0, 1.0);
            for (int i = -3; i <= 3; ++i) {
                for (int j = -3; j <= 3; ++j) {
                    EMStaticFrame.this.grid[n + i][n2 + j].conductor = false;
                }
            }
            EMStaticFrame.this.currentCheck.setState(true);
        }
        
        Setup createNext() {
            return new Current2D2Setup();
        }
    }
    
    class Current2D2Setup extends Setup
    {
        String getName() {
            return "Current in 2D 2";
        }
        
        void select() {
            final int n = EMStaticFrame.this.gridSizeX / 2;
            final int n2 = 8;
            for (int i = 0; i != n2; ++i) {
                for (int j = 0; j != EMStaticFrame.this.gridSizeY; ++j) {
                    EMStaticFrame.this.addConductor(n + i - n2 / 2, j);
                }
            }
            for (int k = -EMStaticFrame.this.windowWidth / 4; k < EMStaticFrame.this.windowWidth / 4; ++k) {
                for (int l = EMStaticFrame.this.gridSizeY / 2 - n2; l <= EMStaticFrame.this.gridSizeY / 2 + n2; ++l) {
                    EMStaticFrame.this.addConductor(n + k, l);
                }
            }
            EMStaticFrame.this.currentCheck.setState(true);
        }
        
        Setup createNext() {
            return null;
        }
    }
}
