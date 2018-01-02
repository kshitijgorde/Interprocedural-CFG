import java.awt.event.ItemEvent;
import java.awt.event.AdjustmentEvent;
import java.awt.event.ActionEvent;
import java.awt.event.ComponentEvent;
import java.awt.FontMetrics;
import java.awt.image.ImageObserver;
import java.awt.Graphics;
import java.awt.Label;
import java.awt.Component;
import java.awt.LayoutManager;
import java.text.NumberFormat;
import java.awt.Color;
import java.util.Vector;
import java.awt.Choice;
import java.awt.Scrollbar;
import java.awt.Checkbox;
import java.awt.Button;
import java.util.Random;
import java.awt.Image;
import java.awt.Dimension;
import java.awt.event.ItemListener;
import java.awt.event.AdjustmentListener;
import java.awt.event.ActionListener;
import java.awt.event.ComponentListener;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class Gas extends Applet implements ComponentListener, ActionListener, AdjustmentListener, ItemListener
{
    Thread engine;
    int molCount;
    Dimension winSize;
    Image dbimage;
    public static final int defaultPause = 10;
    int heaterSize;
    int pause;
    Random random;
    public static int gridEltWidth;
    public static int gridEltHeight;
    int gridWidth;
    int gridHeight;
    Molecule[] mols;
    Molecule[][] grid;
    Molecule bigmol;
    Button resetButton;
    Button expandButton;
    Checkbox stoppedCheck;
    Checkbox heaterCheck;
    Checkbox energyCheck;
    Scrollbar heaterTempBar;
    Scrollbar gravityBar;
    Scrollbar speedBar;
    Scrollbar molCountBar;
    Scrollbar colorBar;
    Choice setupChooser;
    Vector setupList;
    Setup setup;
    double gravity;
    double colorMult;
    int upperBound;
    double topWallPos;
    double topWallVel;
    int areaHeight;
    double heatstate;
    double heaterTemp;
    double heaterMove;
    double wallF;
    double wallFMeasure;
    Color heaterColor;
    Color[] colors;
    int heaterTop;
    int heaterLeft;
    int heaterRight;
    final int maxMolCount = 1000;
    NumberFormat showFormat;
    GasCanvas cv;
    HistogramCanvas hist_cv;
    static final int SPEED_RANDOM = 0;
    static final int SPEED_EQUAL = 1;
    static final int SPEED_EXTREME = 2;
    long secTime;
    long lastTime;
    double t;
    double lastSecT;
    double totalKE;
    double temp;
    double totalV;
    int graphmax;
    
    public Gas() {
        this.engine = null;
        this.graphmax = 20;
    }
    
    public String getAppletInfo() {
        return "Gas Molecules by Paul Falstad";
    }
    
    int getrand(final int n) {
        int nextInt = this.random.nextInt();
        if (nextInt < 0) {
            nextInt = -nextInt;
        }
        return nextInt % n;
    }
    
    public void init() {
        this.setupList = new Vector();
        for (Setup next = new Setup1Random(); next != null; next = next.createNext()) {
            this.setupList.addElement(next);
        }
        (this.showFormat = NumberFormat.getInstance()).setMaximumFractionDigits(3);
        int n = 0;
        this.heatstate = 0.0;
        (this.colors = new Color[16])[n++] = new Color(46, 120, 255);
        this.colors[n++] = new Color(79, 140, 254);
        this.colors[n++] = new Color(113, 142, 253);
        this.colors[n++] = new Color(147, 145, 252);
        this.colors[n++] = new Color(181, 105, 178);
        this.colors[n++] = new Color(215, 64, 103);
        this.colors[n++] = new Color(249, 23, 28);
        this.colors[n++] = new Color(250, 101, 44);
        this.colors[n++] = new Color(251, 139, 33);
        this.colors[n++] = new Color(252, 178, 22);
        this.colors[n++] = new Color(253, 216, 11);
        this.colors[n++] = new Color(255, 255, 0);
        this.colors[n++] = new Color(255, 255, 63);
        this.colors[n++] = new Color(255, 255, 127);
        this.colors[n++] = new Color(255, 255, 191);
        this.colors[n++] = new Color(255, 255, 255);
        this.gravity = 0.0;
        this.setLayout(new GasLayout());
        (this.cv = new GasCanvas(this)).addComponentListener(this);
        this.add(this.cv);
        (this.hist_cv = new HistogramCanvas(this)).addComponentListener(this);
        this.add(this.hist_cv);
        this.setupChooser = new Choice();
        for (int i = 0; i != this.setupList.size(); ++i) {
            this.setupChooser.add("Setup: " + ((Setup)this.setupList.elementAt(i)).getName());
        }
        this.setupChooser.addItemListener(this);
        this.add(this.setupChooser);
        (this.stoppedCheck = new Checkbox("Stopped")).addItemListener(this);
        this.add(this.stoppedCheck);
        (this.heaterCheck = new Checkbox("Heater")).addItemListener(this);
        this.add(this.heaterCheck);
        (this.energyCheck = new Checkbox("Energy Distribution")).addItemListener(this);
        this.add(this.energyCheck);
        this.add(this.resetButton = new Button("Reset"));
        this.resetButton.addActionListener(this);
        this.add(this.expandButton = new Button("Expand"));
        this.expandButton.addActionListener(this);
        this.add(new Label("Simulation Speed", 1));
        this.add(this.speedBar = new Scrollbar(0, 50, 1, 0, 100));
        this.speedBar.addAdjustmentListener(this);
        this.add(new Label("Molecule Count", 1));
        this.add(this.molCountBar = new Scrollbar(0, 500, 1, 1, 1000));
        this.molCountBar.addAdjustmentListener(this);
        this.add(new Label("Color Scale", 1));
        this.add(this.colorBar = new Scrollbar(0, 150, 1, 1, 300));
        this.colorBar.addAdjustmentListener(this);
        this.add(new Label("Heater Temperature", 1));
        this.add(this.heaterTempBar = new Scrollbar(0, 35, 1, 0, 100));
        this.heaterTempBar.addAdjustmentListener(this);
        this.add(new Label("Gravity", 1));
        this.add(this.gravityBar = new Scrollbar(0, 20, 1, 0, 100));
        this.gravityBar.addAdjustmentListener(this);
        this.cv.setBackground(Color.black);
        this.cv.setForeground(this.heaterColor = Color.lightGray);
        this.hist_cv.setBackground(Color.black);
        this.hist_cv.setForeground(Color.lightGray);
        this.random = new Random();
        this.pause = 10;
        this.adjustColors();
        this.adjustHeaterTemp();
        this.enableItems();
        try {
            final String parameter = this.getParameter("PAUSE");
            if (parameter != null) {
                this.pause = Integer.parseInt(parameter);
            }
        }
        catch (Exception ex) {}
        this.reinit(true);
        this.repaint();
    }
    
    void reinit(final boolean b) {
        if (this.cv.getSize().width == 0 || this.gravityBar == null || this.setupChooser == null) {
            return;
        }
        System.out.println("winsize " + this.winSize);
        this.bigmol = null;
        this.setup = this.setupList.elementAt(this.setupChooser.getSelectedIndex());
        this.gravityBar.setValue(0);
        if (b) {
            this.speedBar.setValue(20);
            this.molCountBar.setValue(500);
            this.colorBar.setValue(160);
            this.setup.select();
        }
        this.setup.reinit();
        this.adjustColors();
    }
    
    void expand() {
        this.topWallPos -= 50.0;
        if (this.topWallPos < 0.0) {
            this.topWallPos = 0.0;
        }
        this.enableItems();
    }
    
    void initMolecules(final int n) {
        final Dimension size = this.cv.getSize();
        this.winSize = size;
        final Dimension dimension = size;
        this.molCount = this.molCountBar.getValue();
        this.upperBound = (int)(this.winSize.height * (1.0 - this.setup.getVolume()) - 1.0);
        this.topWallPos = this.upperBound;
        this.areaHeight = this.winSize.height - this.upperBound;
        this.mols = new Molecule[1000];
        this.dbimage = this.createImage(dimension.width, dimension.height);
        this.gridWidth = dimension.width / Gas.gridEltWidth + 1;
        this.gridHeight = dimension.height / Gas.gridEltHeight + 1;
        this.grid = new Molecule[this.gridWidth][this.gridHeight];
        for (int i = 0; i != this.gridWidth; ++i) {
            for (int j = 0; j != this.gridHeight; ++j) {
                this.grid[i][j] = new Molecule();
                this.grid[i][j].listHead = true;
            }
        }
        for (int k = 0; k != 1000; ++k) {
            final Molecule color = new Molecule();
            this.mols[k] = color;
            color.x = this.getrand(this.winSize.width * 10) * 0.1;
            color.y = this.getrand(this.areaHeight * 10) * 0.1 + this.upperBound;
            color.dx = this.getrand(100) / 99.0 - 0.5;
            color.dy = Math.sqrt(1.0 - color.dx * color.dx);
            if (this.getrand(10) > 4) {
                color.dy = -color.dy;
            }
            if (n == 2) {
                final double n2 = ((k & 0x2) > 0) ? 3.0 : 0.1;
                final Molecule molecule = color;
                molecule.dx *= n2;
                final Molecule molecule2 = color;
                molecule2.dy *= n2;
            }
            if (n == 0) {
                final double n3 = this.getrand(101) / 50.0;
                final Molecule molecule3 = color;
                molecule3.dx *= n3;
                final Molecule molecule4 = color;
                molecule4.dy *= n3;
            }
            if (Double.isNaN(color.dx) || Double.isNaN(color.dy)) {
                System.out.println("nan1");
            }
            this.setColor(color);
            if (k < this.molCount) {
                this.gridAdd(color);
            }
        }
        this.heaterTop = this.winSize.height - 5;
        this.heaterSize = this.winSize.width / 4;
        this.heaterLeft = (this.winSize.width - this.heaterSize * 3) / 2;
        this.heaterRight = (this.winSize.width + this.heaterSize * 3) / 2;
        this.enableItems();
        this.cv.repaint();
        this.hist_cv.repaint();
    }
    
    void setMoleculeTypes(final double n, final int n2) {
        for (int i = 0; i != 1000; ++i) {
            final Molecule molecule;
            final Molecule color = molecule = this.mols[i];
            molecule.r *= (int)n;
            final Molecule molecule2 = color;
            molecule2.mass *= n * n;
            if (n2 > 1) {
                final int type = i % n2;
                if ((color.type = type) == 2) {
                    final Molecule molecule3 = color;
                    molecule3.r *= 3;
                    final Molecule molecule4 = color;
                    molecule4.mass *= 9.0;
                }
                else if (type == 1) {
                    final Molecule molecule5 = color;
                    molecule5.r *= 2;
                    final Molecule molecule6 = color;
                    molecule6.mass *= 4.0;
                }
            }
            this.setColor(color);
        }
    }
    
    public void updateGas(final Graphics graphics) {
        if (this.winSize == null) {
            return;
        }
        final Graphics graphics2 = this.dbimage.getGraphics();
        graphics2.setColor(this.cv.getBackground());
        graphics2.fillRect(0, 0, this.winSize.width, this.winSize.height);
        double n = this.speedBar.getValue() / 100.0;
        if (!this.stoppedCheck.getState()) {
            final long currentTimeMillis = System.currentTimeMillis();
            if (this.lastTime != 0L) {
                n *= (int)(currentTimeMillis - this.lastTime) / 8.0;
            }
            if (currentTimeMillis - this.secTime >= 1000L) {
                if (this.t > 0.0) {
                    this.wallF /= this.t - this.lastSecT;
                }
                this.wallFMeasure = this.wallF;
                this.wallF = 0.0;
                this.secTime = currentTimeMillis;
                this.lastSecT = this.t;
            }
            this.lastTime = currentTimeMillis;
        }
        else {
            this.lastTime = 0L;
        }
        for (int i = 0; i != this.molCount; i = (short)(i + 1)) {
            final Molecule molecule = this.mols[i];
            int n2 = 0;
            final int n3 = (int)molecule.x;
            final int n4 = (int)molecule.y;
            for (int j = this.stoppedCheck.getState() ? 5 : 0; j < 5; ++j) {
                final Molecule molecule2 = molecule;
                molecule2.dy += this.gravity * n;
                final Molecule molecule3 = molecule;
                molecule3.x += molecule.dx * n;
                final Molecule molecule4 = molecule;
                molecule4.y += molecule.dy * n;
                if (Double.isNaN(molecule.dx) || Double.isNaN(molecule.dy)) {
                    System.out.println("nan2");
                }
                final int r = molecule.r;
                if (molecule.x < r || molecule.x >= this.winSize.width - r) {
                    this.wallF += Math.abs(molecule.dx) * molecule.mass;
                    molecule.dx = -molecule.dx;
                    if (molecule.x < molecule.r) {
                        molecule.x = molecule.r;
                    }
                    if (molecule.x >= this.winSize.width - r) {
                        molecule.x = this.winSize.width - r - 1;
                    }
                    this.setColor(molecule);
                    n2 = 1;
                }
                if (molecule.y < this.upperBound + r || molecule.y >= this.winSize.height - r) {
                    this.wallF += Math.abs(molecule.dy) * molecule.mass;
                    if (molecule.y < this.upperBound + r) {
                        molecule.y = this.upperBound + r;
                    }
                    if (molecule.y >= this.winSize.height - r) {
                        molecule.y = this.winSize.height - r - 1;
                    }
                    if (molecule.y != this.upperBound + r || molecule.dy < 0.0) {}
                    molecule.dy = -molecule.dy;
                    this.setColor(molecule);
                    n2 = 1;
                }
                final int n5 = (int)molecule.x;
                final int n6 = (int)molecule.y;
                if (n2 == 0 && n5 >= this.heaterLeft && n5 <= this.heaterRight && n6 >= this.heaterTop - 1 && this.heaterCheck.getState()) {
                    final double sqrt = Math.sqrt(molecule.dx * molecule.dx + molecule.dy * molecule.dy);
                    final double dy = molecule.dy;
                    final double sqrt2 = Math.sqrt(3.0 * this.heaterTemp / molecule.mass);
                    final double n7 = this.getrand(100) / 99.0;
                    final double n8 = 0.0;
                    final double n9 = sqrt * n8 + sqrt2 * (1.0 - n8);
                    molecule.dx = this.getrand(101) / 50.0 - 1.0;
                    molecule.dy = -Math.sqrt(1.0 - molecule.dx * molecule.dx) * n9;
                    final Molecule molecule5 = molecule;
                    molecule5.dx *= n9;
                    if (Double.isNaN(molecule.dx) || Double.isNaN(molecule.dy)) {
                        System.out.println("nan3");
                    }
                    this.wallF += (dy - molecule.dy) * molecule.mass;
                    this.setColor(molecule);
                    n2 = 1;
                    molecule.y = this.heaterTop - 2;
                    final int n10 = (int)molecule.y;
                }
                final Molecule molecule6 = (n2 != 0) ? null : this.checkCollision(molecule);
                if (molecule6 != null) {
                    if (molecule.dx == molecule6.dx && molecule.dy == molecule6.dy) {
                        if (molecule.dx == 0.0 && molecule.dy == 0.0) {
                            continue;
                        }
                        final Molecule molecule7 = molecule;
                        molecule7.dx += 0.001;
                    }
                    final double n11 = molecule.dx - molecule6.dx;
                    final double n12 = molecule.x - molecule6.x;
                    final double n13 = molecule.dy - molecule6.dy;
                    final double n14 = molecule.y - molecule6.y;
                    final int n15 = molecule.r + molecule6.r;
                    final double n16 = n11 * n11 + n13 * n13;
                    final double n17 = 2.0 * (n12 * n11 + n14 * n13);
                    final double n18 = n12 * n12 + n14 * n14 - n15 * n15;
                    double n19 = (-n17 - Math.sqrt(n17 * n17 - 4.0 * n16 * n18)) / n16;
                    final double n20 = (-n17 + Math.sqrt(n17 * n17 - 4.0 * n16 * n18)) / n16;
                    if (Math.abs(n19) > Math.abs(n20)) {
                        n19 = n20;
                    }
                    if (Double.isNaN(n19)) {
                        System.out.print("nan " + molecule.dx + " " + molecule.dy + " " + molecule6.dx + " " + molecule6.dy + " " + n16 + " " + n17 + " " + n18 + " " + n19 + " " + n20 + "\n");
                    }
                    final Molecule molecule8 = molecule;
                    molecule8.x += n19 * molecule.dx;
                    final Molecule molecule9 = molecule;
                    molecule9.y += n19 * molecule.dy;
                    final double n21 = molecule.x - molecule6.x;
                    final double n22 = molecule.y - molecule6.y;
                    final double sqrt3 = Math.sqrt(n21 * n21 + n22 * n22);
                    final double n23 = n21 / sqrt3;
                    final double n24 = n22 / sqrt3;
                    final double n25 = molecule.mass + molecule6.mass;
                    final double n26 = (molecule.dx - (molecule.mass * molecule.dx + molecule6.mass * molecule6.dx) / n25) * n23 + (molecule.dy - (molecule.mass * molecule.dy + molecule6.mass * molecule6.dy) / n25) * n24;
                    final double n27 = 2.0 * n23 * n26;
                    final double n28 = 2.0 * n24 * n26;
                    final Molecule molecule10 = molecule;
                    molecule10.dx -= n27;
                    final Molecule molecule11 = molecule;
                    molecule11.dy -= n28;
                    if (Double.isNaN(molecule.dx)) {
                        System.out.println("nan0 " + sqrt3 + " " + n26);
                    }
                    final double n29 = molecule.mass / molecule6.mass;
                    final Molecule molecule12 = molecule6;
                    molecule12.dx += n27 * n29;
                    final Molecule molecule13 = molecule6;
                    molecule13.dy += n28 * n29;
                    if (n19 < 0.0) {
                        final Molecule molecule14 = molecule;
                        molecule14.x -= n19 * molecule.dx;
                        final Molecule molecule15 = molecule;
                        molecule15.y -= n19 * molecule.dy;
                    }
                    if (molecule.x < r) {
                        molecule.x = r;
                    }
                    if (molecule.x >= this.winSize.width - r) {
                        molecule.x = this.winSize.width - r;
                    }
                    if (molecule.y < this.upperBound + r) {
                        molecule.y = this.upperBound + r;
                    }
                    if (molecule.y >= this.winSize.height - r) {
                        molecule.y = this.winSize.height - r - 1;
                    }
                    if (Double.isNaN(molecule.dx) || Double.isNaN(molecule.dy)) {
                        System.out.println("nan4");
                    }
                    if (Double.isNaN(molecule6.dx) || Double.isNaN(molecule6.dy)) {
                        System.out.println("nan5");
                    }
                    this.setColor(molecule);
                }
            }
            graphics2.setColor(molecule.color);
            graphics2.fillOval((int)molecule.x - molecule.r, (int)molecule.y - molecule.r, molecule.r * 2, molecule.r * 2);
            this.gridRemove(molecule);
            this.gridAdd(molecule);
        }
        this.t += n * 5.0;
        this.totalKE = 0.0;
        this.totalV = 0.0;
        for (int k = 0; k != this.molCount; k = (short)(k + 1)) {
            final Molecule molecule16 = this.mols[k];
            this.totalKE += molecule16.mass * (molecule16.dx * molecule16.dx + molecule16.dy * molecule16.dy);
            this.totalV += molecule16.r * molecule16.r;
        }
        this.totalV *= 3.141592653589793;
        this.totalKE *= 0.5;
        this.temp = 0.6666666666666666 * this.totalKE / this.molCount;
        if (this.topWallVel > 0.5) {
            this.topWallVel = 0.5;
        }
        this.topWallPos += this.topWallVel * 5.0;
        if (this.topWallPos < 0.0) {
            this.topWallPos = 0.0;
            if (this.topWallVel < 0.0) {
                this.topWallVel = 0.0;
            }
        }
        if (this.topWallPos > this.winSize.height * 4 / 5) {
            this.topWallPos = this.winSize.height * 4 / 5;
            if (this.topWallVel > 0.0) {
                this.topWallVel = 0.0;
            }
        }
        this.upperBound = (int)this.topWallPos;
        int n30 = (int)this.heatstate;
        if (this.heaterCheck.getState()) {
            for (int l = 0; l != this.heaterSize; ++l, ++n30) {
                final int n31 = this.heaterLeft + l * 3;
                int n32 = n30 & 0x3;
                if ((n30 & 0x4) == 0x4) {
                    n32 = 4 - n32;
                }
                graphics2.setColor(this.heaterColor);
                graphics2.fillRect(n31, this.heaterTop + n32, 2, 2);
            }
        }
        graphics2.setColor(Color.lightGray);
        graphics2.drawRect(0, this.upperBound, this.winSize.width - 1, this.winSize.height - 1 - this.upperBound);
        graphics2.fillRect(this.winSize.width / 2 - 20, 0, 40, this.upperBound);
        graphics.drawImage(this.dbimage, 0, 0, this);
        if (!this.stoppedCheck.getState()) {
            this.heatstate += this.heaterMove;
            this.cv.repaint(this.pause);
            this.hist_cv.repaint(this.pause);
        }
    }
    
    void gridAdd(final Molecule molecule) {
        final Molecule next = this.grid[(int)(molecule.x / Gas.gridEltWidth)][(int)(molecule.y / Gas.gridEltHeight)];
        molecule.next = next;
        molecule.prev = next.prev;
        next.prev = molecule;
        molecule.prev.next = molecule;
    }
    
    void gridRemove(final Molecule molecule) {
        molecule.next.prev = molecule.prev;
        molecule.prev.next = molecule.next;
    }
    
    Molecule checkCollision(final Molecule molecule) {
        if (this.bigmol != null) {
            final Molecule checkCollision = this.checkCollision(molecule, this.grid[(int)(this.bigmol.x / Gas.gridEltWidth)][(int)(this.bigmol.y / Gas.gridEltHeight)]);
            if (checkCollision != null) {
                return checkCollision;
            }
        }
        final int n = (int)(molecule.x / Gas.gridEltWidth);
        final int n2 = (int)(molecule.y / Gas.gridEltHeight);
        for (int i = -1; i <= 1; ++i) {
            for (int j = -1; j <= 1; ++j) {
                if (n + i >= 0 && n2 + j >= 0 && n + i < this.gridWidth) {
                    if (n2 + j < this.gridHeight) {
                        final Molecule checkCollision2 = this.checkCollision(molecule, this.grid[n + i][n2 + j]);
                        if (checkCollision2 != null) {
                            return checkCollision2;
                        }
                    }
                }
            }
        }
        return null;
    }
    
    Molecule checkCollision(final Molecule molecule, final Molecule molecule2) {
        Molecule molecule3 = molecule2.next;
        int n = 0;
        while (!molecule3.listHead) {
            if (molecule != molecule3) {
                ++n;
                final int n2 = molecule.r + molecule3.r;
                final double n3 = molecule.x - molecule3.x;
                final double n4 = molecule.y - molecule3.y;
                if (n3 <= n2 && n4 <= n2 && n3 >= -n2) {
                    if (n4 >= -n2) {
                        if (Math.sqrt(n3 * n3 + n4 * n4) <= n2) {
                            return molecule3;
                        }
                    }
                }
            }
            molecule3 = molecule3.next;
        }
        return null;
    }
    
    void setColor(final Molecule molecule) {
        molecule.vel = Math.sqrt(molecule.dx * molecule.dx + molecule.dy * molecule.dy);
        molecule.ke = molecule.mass * molecule.vel * molecule.vel;
        int n = (int)(molecule.ke * this.colorMult);
        final int n2 = this.colors.length - 1;
        if (n > n2) {
            n = n2;
        }
        molecule.color = this.colors[n];
    }
    
    public void updateHistogram(final Graphics graphics) {
        if (this.winSize == null) {
            return;
        }
        final Dimension size = this.hist_cv.size();
        final Graphics graphics2 = this.dbimage.getGraphics();
        graphics2.setColor(this.hist_cv.getBackground());
        graphics2.fillRect(0, 0, size.width, size.height);
        graphics2.setColor(this.hist_cv.getForeground());
        final int n = size.width / 2;
        final int[] array = new int[n];
        int graphmax = 0;
        final int histogramCount = this.setup.getHistogramCount();
        final boolean state = this.energyCheck.getState();
        for (int i = 0; i != histogramCount; ++i) {
            final int n2 = size.height * i / histogramCount;
            final int n3 = size.height * (i + 1) / histogramCount - 1;
            final int n4 = n3 - n2;
            final double n5 = state ? 70.0 : 15.0;
            for (int j = 0; j != n; ++j) {
                array[j] = 0;
            }
            double mass = 1.0;
            int n6 = 0;
            for (int k = 0; k != this.molCount; ++k) {
                final Molecule molecule = this.mols[k];
                if (molecule.type == i) {
                    ++n6;
                    mass = molecule.mass;
                    final int n7 = (int)((state ? molecule.ke : molecule.vel) * n / n5);
                    if (n7 < n) {
                        final int[] array2 = array;
                        final int n8 = n7;
                        ++array2[n8];
                    }
                }
            }
            final double n9 = n5 + 0.5;
            final int n10 = this.colors.length - 1;
            for (int l = 0; l != n; ++l) {
                if (array[l] != 0) {
                    if (array[l] > graphmax) {
                        graphmax = array[l];
                    }
                    int n11 = n3 - array[l] * n4 / this.graphmax;
                    if (n11 < n2) {
                        n11 = n2;
                    }
                    double n12 = l * n9 / n;
                    if (!state) {
                        n12 *= mass * n12;
                    }
                    int n13 = (int)(n12 * this.colorMult);
                    if (n13 > n10) {
                        n13 = n10;
                    }
                    graphics2.setColor(this.colors[n13]);
                    graphics2.fillRect(l * 2, n11, 2, n3 - n11 + 1);
                }
            }
            int n14 = -1;
            int n15 = -1;
            graphics2.setColor(Color.lightGray);
            if (!this.energyCheck.getState()) {
                for (int n16 = 0; n16 != n; ++n16) {
                    final double n17 = n16 * n9 / n;
                    final double n18 = n9 / n;
                    int n19 = n3 - (int)(0.5 * n6 * (this.maxwellDist(n17, mass) + this.maxwellDist(n17 + n18, mass)) * n18) * n4 / this.graphmax;
                    if (n19 < n2) {
                        n19 = n2;
                    }
                    final int n20 = n16 * 2;
                    if (n14 != -1 && (n19 != n15 || n15 != n3)) {
                        graphics2.drawLine(n14, n15, n20, n19);
                    }
                    n14 = n20;
                    n15 = n19;
                }
            }
        }
        if (graphmax > this.graphmax) {
            this.graphmax = graphmax;
        }
        if (graphmax < this.graphmax / 2 && this.graphmax > 1) {
            this.graphmax /= 2;
        }
        final FontMetrics fontMetrics = graphics2.getFontMetrics();
        graphics2.setColor(Color.white);
        final int n21 = this.winSize.width * 2 / 3;
        final double n22 = 4.0E-4;
        final double n23 = (this.winSize.width - 2) * (this.winSize.height - this.upperBound - 2) * n22;
        graphics2.drawString("V = " + this.showFormat.format(n23), n21, fontMetrics.getAscent());
        graphics2.drawString("n = " + this.molCount, n21, fontMetrics.getAscent() + fontMetrics.getHeight());
        final double n24 = 10000.0 * this.wallFMeasure / (3.0 * (2 * (this.winSize.width + (this.winSize.height - this.upperBound) - 4)));
        graphics2.drawString("P = " + this.showFormat.format(n24), n21, fontMetrics.getAscent() + 2 * fontMetrics.getHeight());
        graphics2.drawString("kT = " + this.showFormat.format(this.temp), n21, fontMetrics.getAscent() + 3 * fontMetrics.getHeight());
        graphics2.drawString("PV/nkT = " + this.showFormat.format(n24 * n23 / (this.molCount * this.temp)), n21, fontMetrics.getAscent() + 4 * fontMetrics.getHeight());
        graphics2.drawString("P(V-nb)/nkT = " + this.showFormat.format(n24 * (n23 - this.totalV * n22) / (this.molCount * this.temp)), n21, fontMetrics.getAscent() + 5 * fontMetrics.getHeight());
        graphics.drawImage(this.dbimage, 0, 0, this);
    }
    
    double maxwellDist(final double n, final double n2) {
        if (this.energyCheck.getState()) {
            return Math.exp(-n / this.temp) / this.temp;
        }
        return n2 / this.temp * n * Math.exp(-n2 * n * n / (2.0 * this.temp));
    }
    
    public void componentHidden(final ComponentEvent componentEvent) {
    }
    
    public void componentMoved(final ComponentEvent componentEvent) {
    }
    
    public void componentShown(final ComponentEvent componentEvent) {
    }
    
    public void componentResized(final ComponentEvent componentEvent) {
        this.reinit(false);
        this.cv.repaint(100L);
        this.hist_cv.repaint(100L);
    }
    
    public void actionPerformed(final ActionEvent actionEvent) {
        System.out.println(actionEvent);
        if (actionEvent.getSource() == this.resetButton) {
            this.reinit(false);
            this.cv.repaint();
        }
        if (actionEvent.getSource() == this.expandButton) {
            this.expand();
            this.cv.repaint();
        }
    }
    
    public void adjustmentValueChanged(final AdjustmentEvent adjustmentEvent) {
        System.out.println(((Scrollbar)adjustmentEvent.getSource()).getValue());
        if (adjustmentEvent.getSource() == this.gravityBar) {
            this.gravity = this.gravityBar.getValue() * 5.0E-5;
        }
        if (adjustmentEvent.getSource() == this.heaterTempBar) {
            this.adjustHeaterTemp();
        }
        if (adjustmentEvent.getSource() == this.molCountBar) {
            this.adjustMolCount();
        }
        if (adjustmentEvent.getSource() == this.colorBar) {
            this.adjustColors();
        }
    }
    
    void adjustHeaterTemp() {
        this.heaterTemp = this.heaterTempBar.getValue() * 0.029111971 * 30.0 + 0.01;
        this.heaterMove = this.heaterTempBar.getValue() * 0.029111971 + 0.3;
        this.heaterMove /= 2.0;
        int n = (int)(1.5 * this.heaterTemp * this.colorMult);
        final int n2 = this.colors.length - 1;
        if (n > n2) {
            n = n2;
        }
        this.heaterColor = this.colors[n];
        System.out.println("htemp = " + this.heaterTemp);
    }
    
    void adjustColors() {
        this.colorMult = Math.exp((this.colorBar.getValue() / 150.0 - 1.0) * 4.0) * 0.7;
        for (int i = 0; i != this.molCount; ++i) {
            this.setColor(this.mols[i]);
        }
    }
    
    void enableItems() {
        this.heaterTempBar.setEnabled(this.heaterCheck.getState());
        this.expandButton.setEnabled(this.topWallPos > 0.0);
    }
    
    public void itemStateChanged(final ItemEvent itemEvent) {
        this.enableItems();
        if (itemEvent.getItemSelectable() == this.stoppedCheck) {
            this.cv.repaint();
            return;
        }
        if (itemEvent.getItemSelectable() == this.setupChooser) {
            this.reinit(true);
        }
    }
    
    void adjustMolCount() {
        final int molCount = this.molCount;
        this.molCount = this.molCountBar.getValue();
        if (this.molCount == molCount) {
            return;
        }
        if (molCount > this.molCount) {
            for (int i = this.molCount; i != molCount; ++i) {
                this.gridRemove(this.mols[i]);
            }
        }
        else {
            for (int j = molCount; j != this.molCount; ++j) {
                this.gridAdd(this.mols[j]);
            }
        }
    }
    
    static {
        Gas.gridEltWidth = 10;
        Gas.gridEltHeight = 10;
    }
    
    class Molecule
    {
        public double x;
        public double y;
        public double dx;
        public double dy;
        public double mass;
        public double ke;
        public double vel;
        public int r;
        public int type;
        public Color color;
        public Molecule next;
        public Molecule prev;
        public boolean listHead;
        
        Molecule() {
            this.r = 2;
            this.type = 0;
            this.mass = 2.0;
            this.prev = this;
            this.next = this;
        }
    }
    
    abstract class Setup
    {
        abstract String getName();
        
        void select() {
        }
        
        void reinit() {
        }
        
        void deselect() {
        }
        
        int getHistogramCount() {
            return 1;
        }
        
        double getVolume() {
            return 1.0;
        }
        
        abstract Setup createNext();
    }
    
    class Setup1Random extends Setup
    {
        String getName() {
            return "1 Gas, Random Speeds";
        }
        
        void reinit() {
            Gas.this.initMolecules(0);
            Gas.this.setMoleculeTypes(2.0, 1);
        }
        
        Setup createNext() {
            return new Setup1Equal();
        }
    }
    
    class Setup1Equal extends Setup
    {
        String getName() {
            return "1 Gas, Equal Speeds";
        }
        
        void select() {
            Gas.this.speedBar.setValue(3);
        }
        
        void reinit() {
            Gas.this.initMolecules(1);
            Gas.this.setMoleculeTypes(2.0, 1);
        }
        
        Setup createNext() {
            return new Setup1Extreme();
        }
    }
    
    class Setup1Extreme extends Setup
    {
        String getName() {
            return "1 Gas, Extreme Speeds";
        }
        
        void select() {
            Gas.this.speedBar.setValue(3);
        }
        
        void reinit() {
            Gas.this.initMolecules(2);
            Gas.this.setMoleculeTypes(2.0, 1);
        }
        
        Setup createNext() {
            return new Setup1Single();
        }
    }
    
    class Setup1Single extends Setup
    {
        String getName() {
            return "1 Gas, One Moving Molecule";
        }
        
        void select() {
            Gas.this.speedBar.setValue(10);
        }
        
        void reinit() {
            Gas.this.initMolecules(1);
            for (int i = 1; i != 1000; ++i) {
                final Molecule molecule = Gas.this.mols[i];
                final Molecule molecule2 = Gas.this.mols[i];
                final double n = 0.0;
                molecule2.dy = n;
                molecule.dx = n;
            }
            final Molecule molecule3 = Gas.this.mols[0];
            molecule3.dx *= Math.sqrt(Gas.this.molCount);
            final Molecule molecule4 = Gas.this.mols[0];
            molecule4.dy *= Math.sqrt(Gas.this.molCount);
            Gas.this.setMoleculeTypes(2.0, 1);
        }
        
        Setup createNext() {
            return new Setup1Small();
        }
    }
    
    class Setup1Small extends Setup
    {
        String getName() {
            return "1 Gas, Small";
        }
        
        void select() {
            Gas.this.colorBar.setValue(215);
            Gas.this.speedBar.setValue(36);
        }
        
        void reinit() {
            Gas.this.initMolecules(0);
            Gas.this.setMoleculeTypes(1.0, 1);
        }
        
        Setup createNext() {
            return new Setup2Random();
        }
    }
    
    class Setup2Random extends Setup
    {
        String getName() {
            return "2 Gases, Random Speeds";
        }
        
        void reinit() {
            Gas.this.initMolecules(0);
            Gas.this.setMoleculeTypes(1.0, 2);
        }
        
        int getHistogramCount() {
            return 2;
        }
        
        Setup createNext() {
            return new Setup2Equal();
        }
    }
    
    class Setup2Equal extends Setup
    {
        String getName() {
            return "2 Gases, Equal Speeds";
        }
        
        void select() {
            Gas.this.speedBar.setValue(3);
        }
        
        void reinit() {
            Gas.this.initMolecules(1);
            Gas.this.setMoleculeTypes(1.0, 2);
        }
        
        int getHistogramCount() {
            return 2;
        }
        
        Setup createNext() {
            return new Setup3Random();
        }
    }
    
    class Setup3Random extends Setup
    {
        String getName() {
            return "3 Gases, Random Speeds";
        }
        
        void reinit() {
            Gas.this.initMolecules(0);
            Gas.this.setMoleculeTypes(1.0, 3);
        }
        
        int getHistogramCount() {
            return 3;
        }
        
        Setup createNext() {
            return new Setup3Equal();
        }
    }
    
    class Setup3Equal extends Setup
    {
        String getName() {
            return "3 Gases, Equal Speeds";
        }
        
        void select() {
            Gas.this.speedBar.setValue(3);
        }
        
        void reinit() {
            Gas.this.initMolecules(1);
            Gas.this.setMoleculeTypes(1.0, 3);
        }
        
        int getHistogramCount() {
            return 3;
        }
        
        Setup createNext() {
            return new SetupBrownian();
        }
    }
    
    class SetupBrownian extends Setup
    {
        String getName() {
            return "Brownian Motion";
        }
        
        void select() {
            Gas.this.speedBar.setValue(70);
            Gas.this.colorBar.setValue(210);
        }
        
        void reinit() {
            Gas.this.initMolecules(0);
            Gas.this.bigmol = Gas.this.mols[0];
            Gas.this.bigmol.r = 30;
            Gas.this.bigmol.mass = Gas.this.bigmol.r * Gas.this.bigmol.r / 2;
            final Molecule bigmol = Gas.this.bigmol;
            final Molecule bigmol2 = Gas.this.bigmol;
            final double n = 0.0;
            bigmol2.dy = n;
            bigmol.dx = n;
        }
        
        Setup createNext() {
            return new SetupExpansion();
        }
    }
    
    class SetupExpansion extends Setup
    {
        String getName() {
            return "Free Expansion";
        }
        
        void select() {
            Gas.this.molCountBar.setValue(250);
            Gas.this.speedBar.setValue(45);
            Gas.this.colorBar.setValue(210);
        }
        
        void reinit() {
            Gas.this.initMolecules(0);
            Gas.this.setMoleculeTypes(1.0, 1);
        }
        
        double getVolume() {
            return 0.5;
        }
        
        Setup createNext() {
            return null;
        }
    }
}
