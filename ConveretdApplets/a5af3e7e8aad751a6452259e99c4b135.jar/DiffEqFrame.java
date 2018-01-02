import java.awt.FontMetrics;
import java.awt.Canvas;
import java.awt.event.ItemEvent;
import java.awt.event.AdjustmentEvent;
import java.awt.Event;
import java.awt.event.ActionEvent;
import java.awt.event.ComponentEvent;
import java.awt.event.MouseEvent;
import java.awt.image.ImageObserver;
import java.awt.Graphics;
import java.awt.Color;
import java.awt.Component;
import java.awt.LayoutManager;
import java.util.Vector;
import java.awt.Choice;
import java.text.NumberFormat;
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

class DiffEqFrame extends Frame implements ComponentListener, ActionListener, AdjustmentListener, MouseMotionListener, MouseListener, ItemListener
{
    Thread engine;
    Dimension winSize;
    Image dbimage;
    Random random;
    int maxTerms;
    int maxMaxTerms;
    int sampleCount;
    int midy;
    double ymult;
    DiffEq applet;
    double[][] sinTable;
    public static final double epsilon = 1.0E-5;
    public static final double epsilon2 = 0.003;
    static final double yMax = 10.0;
    Button clearButton;
    ValueEditCanvas[] lhsValues;
    ValueEditCanvas[] rhsValues;
    ValueEditCanvas[] initialValues;
    ValueEditCanvas[] solutionValues;
    int selectedCoef;
    int magnitudesY;
    boolean funcSelected;
    static final int SEL_NONE = 0;
    static final int SEL_FUNC = 1;
    static final int SEL_MAG = 2;
    static final int MODE_PLUCK = 0;
    static final int MODE_SHAPE = 1;
    static final int MODE_TOUCH = 2;
    static final int MODE_FORCE = 3;
    static final int MODE_BOW = 999;
    static final int DISP_PHASE = 0;
    static final int DISP_LEFTRIGHT = 1;
    static final int DISP_PHASECOS = 2;
    static final int DISP_PHASORS = 3;
    static final int DISP_MODES = 4;
    static final int VEC_NONNEG = 1;
    static final int VEC_INTEGER = 2;
    static final int VEC_NONZERO = 4;
    static final int VEC_CONSTANT = 8;
    static final int VEC_HALFINTEGER = 16;
    int selection;
    int dragX;
    int dragY;
    boolean dragging;
    boolean bowing;
    boolean bowCaught;
    boolean forceApplied;
    double t;
    double forceMag;
    double[][] points;
    int forceBarValue;
    double forceTimeZero;
    int tensionBarValue;
    NumberFormat nf;
    double[] func;
    double[] forceFunc;
    LhsFunc lhsfunc;
    RhsFunc rhsfunc;
    Choice lhsChooser;
    Choice rhsChooser;
    double xRangeStart;
    double xRangeEnd;
    double xRangeWidth;
    Vector lhsList;
    Vector rhsList;
    boolean lhsChanged;
    boolean rhsChanged;
    static final int pause = 0;
    DiffEqCanvas cv;
    
    DiffEqFrame(final DiffEq applet) {
        super("Differential Equation applet");
        this.engine = null;
        this.maxTerms = 30;
        this.maxMaxTerms = 500;
        this.applet = applet;
    }
    
    public String getAppletInfo() {
        return "DiffEq by Paul Falstad";
    }
    
    int getrand(final int n) {
        int nextInt = this.random.nextInt();
        if (nextInt < 0) {
            nextInt = -nextInt;
        }
        return nextInt % n;
    }
    
    public void init() {
        this.lhsList = new Vector();
        for (LhsFunc next = new OscillatorLhsFunc(); next != null; next = next.createNext()) {
            this.lhsList.addElement(next);
        }
        this.rhsList = new Vector();
        for (RhsFunc next2 = new ZeroRhsFunc(); next2 != null; next2 = next2.createNext()) {
            this.rhsList.addElement(next2);
        }
        this.selectedCoef = -1;
        this.setLayout(new DiffEqLayout());
        (this.cv = new DiffEqCanvas(this)).addComponentListener(this);
        this.cv.addMouseMotionListener(this);
        this.cv.addMouseListener(this);
        this.add(this.cv);
        this.lhsChooser = new Choice();
        for (int i = 0; i != this.lhsList.size(); ++i) {
            this.lhsChooser.add("LHS = " + ((LhsFunc)this.lhsList.elementAt(i)).getName());
        }
        this.add(this.lhsChooser);
        this.lhsfunc = this.lhsList.elementAt(0);
        this.lhsChooser.addItemListener(this);
        this.rhsChooser = new Choice();
        for (int j = 0; j != this.rhsList.size(); ++j) {
            this.rhsChooser.add("RHS = " + ((RhsFunc)this.rhsList.elementAt(j)).getName());
        }
        this.add(this.rhsChooser);
        this.rhsfunc = this.rhsList.elementAt(0);
        this.rhsChooser.addItemListener(this);
        this.add(this.clearButton = new Button("Clear Function"));
        this.clearButton.addActionListener(this);
        this.lhsValues = new ValueEditCanvas[5];
        this.rhsValues = new ValueEditCanvas[2];
        this.initialValues = new ValueEditCanvas[3];
        this.solutionValues = new ValueEditCanvas[4];
        for (int k = 0; k != 5; ++k) {
            this.add(this.lhsValues[k] = new ValueEditCanvas());
        }
        for (int l = 0; l != 2; ++l) {
            this.add(this.rhsValues[l] = new ValueEditCanvas());
        }
        for (int n = 0; n != 3; ++n) {
            this.add(this.initialValues[n] = new ValueEditCanvas());
            this.initialValues[n].setValue(0.0);
        }
        for (int n2 = 0; n2 != 4; ++n2) {
            this.add(this.solutionValues[n2] = new ValueEditCanvas());
        }
        final boolean b = true;
        this.rhsChanged = b;
        this.lhsChanged = b;
        this.setLoadCount();
        this.points = new double[1][2];
        this.points[0][0] = 1.0;
        this.points[0][1] = 1.0;
        (this.nf = NumberFormat.getInstance()).setMaximumFractionDigits(5);
        this.random = new Random();
        this.reinit();
        this.cv.setBackground(Color.black);
        this.cv.setForeground(Color.lightGray);
        this.resize(500, 400);
        this.handleResize();
        this.show();
    }
    
    void handleResize() {
        this.reinit();
    }
    
    void reinit() {
        final Dimension size = this.cv.getSize();
        this.winSize = size;
        final Dimension dimension = size;
        if (this.winSize.width == 0) {
            return;
        }
        this.dbimage = this.createImage(dimension.width, dimension.height);
    }
    
    int getPanelHeight() {
        return this.winSize.height - 40;
    }
    
    void centerString(final Graphics graphics, final String s, final int n) {
        graphics.drawString(s, (this.winSize.width - graphics.getFontMetrics().stringWidth(s)) / 2, n);
    }
    
    public void paint(final Graphics graphics) {
        this.cv.repaint();
    }
    
    void doClear() {
        for (int i = 0; i != this.sampleCount; ++i) {
            this.forceFunc[i] = 0.0;
        }
    }
    
    void valueChanged(final ValueEditCanvas valueEditCanvas) {
        for (int i = 0; i != 4; ++i) {
            if (valueEditCanvas == this.solutionValues[i]) {
                this.lhsfunc.setSolution();
                break;
            }
        }
        this.cv.repaint();
    }
    
    void rk(final double n, final double[] array, final double n2) {
        final int order = this.lhsfunc.getOrder();
        final double[] array2 = new double[order];
        final double[] array3 = new double[order];
        final double[] array4 = new double[order];
        final double[] array5 = new double[order];
        final double[] array6 = new double[order];
        final double rhsScale = this.lhsfunc.getRhsScale();
        for (int i = 0; i != order; ++i) {
            array2[i] = array[i];
        }
        for (int j = 0; j != order - 1; ++j) {
            array3[j] = n2 * array2[j + 1];
        }
        array3[order - 1] = n2 * this.lhsfunc.calculateDiffs(n, array2, rhsScale * this.rhsfunc.calculate(n, array2));
        for (int k = 0; k != order; ++k) {
            array2[k] = array[k] + 0.5 * array3[k];
        }
        for (int l = 0; l != order - 1; ++l) {
            array4[l] = n2 * array2[l + 1];
        }
        final double n3 = n + n2 * 0.5;
        array4[order - 1] = n2 * this.lhsfunc.calculateDiffs(n3, array2, rhsScale * this.rhsfunc.calculate(n3, array2));
        for (int n4 = 0; n4 != order; ++n4) {
            array2[n4] = array[n4] + 0.5 * array4[n4];
        }
        for (int n5 = 0; n5 != order - 1; ++n5) {
            array5[n5] = n2 * array2[n5 + 1];
        }
        array5[order - 1] = n2 * this.lhsfunc.calculateDiffs(n3, array2, rhsScale * this.rhsfunc.calculate(n3, array2));
        final double n6 = n + n2;
        for (int n7 = 0; n7 != order; ++n7) {
            array2[n7] = array[n7] + array5[n7];
        }
        for (int n8 = 0; n8 != order - 1; ++n8) {
            array6[n8] = n2 * array2[n8 + 1];
        }
        array6[order - 1] = n2 * this.lhsfunc.calculateDiffs(n6, array2, rhsScale * this.rhsfunc.calculate(n6, array2));
        for (int n9 = 0; n9 != order; ++n9) {
            array[n9] += (array3[n9] + 2.0 * (array4[n9] + array5[n9]) + array6[n9]) / 6.0;
        }
    }
    
    void rungeKutta(final double n, final double n2) {
        final double n3 = this.xRangeWidth / this.sampleCount;
        final double n4 = 0.001;
        final int order = this.lhsfunc.getOrder();
        final double[] array = new double[order];
        final double[] array2 = new double[order];
        double n5 = n3;
        array[0] = (array2[0] = this.points[0][1]);
        if (order > 1) {
            array[1] = (array2[1] = this.initialValues[0].getValue());
        }
        if (order > 2) {
            array[2] = (array2[2] = this.initialValues[1].getValue());
        }
        if (order > 3) {
            array[3] = (array2[3] = this.initialValues[2].getValue());
        }
        double n6 = n;
        int n7 = 0;
        int i = (int)((n6 - this.xRangeStart) * this.sampleCount / this.xRangeWidth);
        this.func[i] = array[0];
        final double n8 = 0.01;
        while (n6 >= this.xRangeStart && n6 <= this.xRangeEnd) {
            this.rk(n6, array, n5 * n2);
            this.rk(n6, array2, n5 * 0.5 * n2);
            this.rk(n6, array2, n5 * 0.5 * n2);
            final double abs = Math.abs(array[0] - array2[0]);
            if (array[0] <= 1000000.0) {
                if (array[0] >= -1000000.0) {
                    if (abs > n4) {
                        n5 *= 0.75;
                        if (n5 < n8) {
                            n5 = n8;
                        }
                    }
                    else if (abs < n4 * 0.5) {
                        n5 *= 1.25;
                        if (n5 > n3) {
                            n5 = n3;
                        }
                    }
                }
            }
            for (int j = 0; j != order; ++j) {
                array2[j] = array[j];
            }
            int sampleCount = (int)((n6 - this.xRangeStart) * this.sampleCount / this.xRangeWidth);
            if (n2 > 0.0) {
                if (sampleCount > i) {
                    if (sampleCount > this.sampleCount) {
                        sampleCount = this.sampleCount;
                    }
                    while (i < sampleCount) {
                        this.func[++i] = array[0];
                    }
                }
            }
            else if (sampleCount < i) {
                if (sampleCount < 0) {
                    sampleCount = 0;
                }
                while (i > sampleCount) {
                    this.func[--i] = array[0];
                }
            }
            n6 += n5 * n2;
            ++n7;
        }
    }
    
    public void updateDiffEq(final Graphics graphics) {
        final Graphics graphics2 = this.dbimage.getGraphics();
        if (this.winSize == null || this.winSize.width == 0) {
            return;
        }
        final Color color = new Color(76, 76, 76);
        final Color color2 = new Color(127, 127, 127);
        graphics2.setColor(this.cv.getBackground());
        graphics2.fillRect(0, 0, this.winSize.width, this.winSize.height);
        graphics2.setColor(this.cv.getForeground());
        this.lhsfunc = this.lhsList.elementAt(this.lhsChooser.getSelectedIndex());
        if (this.lhsChanged) {
            for (int i = 0; i != 5; ++i) {
                this.lhsValues[i].hide();
            }
            for (int j = 0; j != 3; ++j) {
                this.initialValues[j].hide();
                this.initialValues[j].setValue(0.0);
            }
            this.lhsfunc.newFunc();
            if (this.lhsfunc.getOrder() > 1) {
                this.initialValues[0].setLabel("Initial y'");
            }
            if (this.lhsfunc.getOrder() > 2) {
                this.initialValues[1].setLabel("Initial y''");
            }
            if (this.lhsfunc.getOrder() > 3) {
                this.initialValues[2].setLabel("Initial y'''");
            }
            this.rhsChooser.select(0);
            this.rhsChanged = true;
        }
        this.lhsfunc.setup();
        this.rhsfunc = this.rhsList.elementAt(this.rhsChooser.getSelectedIndex());
        if (this.rhsChanged) {
            for (int k = 0; k != 2; ++k) {
                this.rhsValues[k].hide();
            }
            this.rhsfunc.newFunc();
            if (this.rhsfunc.isCustom()) {
                this.clearButton.enable();
            }
            else {
                this.clearButton.disable();
            }
            for (int l = 0; l != 4; ++l) {
                this.solutionValues[l].hide();
            }
            if (this.rhsfunc instanceof ZeroRhsFunc) {
                this.lhsfunc.newFuncSolution();
            }
        }
        this.rhsfunc.setup();
        this.validate();
        final boolean b = false;
        this.rhsChanged = b;
        this.lhsChanged = b;
        final int panelHeight = this.getPanelHeight();
        this.midy = panelHeight / 2;
        final double n = 0.75 * (panelHeight / 2);
        this.ymult = n / 10.0;
        for (int n2 = -1; n2 <= 1; ++n2) {
            graphics2.setColor((n2 == 0) ? color2 : color);
            graphics2.drawLine(0, this.midy + n2 * (int)n, this.winSize.width, this.midy + n2 * (int)n);
        }
        graphics2.setColor(color2);
        if (!this.lhsfunc.positiveOnly()) {
            graphics2.drawLine(this.winSize.width / 2, this.midy - (int)n, this.winSize.width / 2, this.midy + (int)n);
        }
        graphics2.setColor(Color.white);
        this.xRangeWidth = this.lhsfunc.getRangeWidth();
        this.xRangeStart = this.lhsfunc.getRangeStart();
        this.xRangeEnd = this.lhsfunc.getRangeEnd();
        final boolean useRungeKutta = this.lhsfunc.useRungeKutta();
        if (useRungeKutta) {
            this.rungeKutta(this.points[0][0], 1.0);
            this.rungeKutta(this.points[0][0], -1.0);
        }
        for (int n3 = 0; n3 != this.sampleCount; ++n3) {
            final double n4 = n3 * this.xRangeWidth / this.sampleCount + this.xRangeStart;
            this.forceFunc[n3] = this.rhsfunc.calculate(n4, null);
            if (!useRungeKutta) {
                this.func[n3] = this.lhsfunc.calculate(n4);
            }
        }
        graphics2.setColor(Color.red);
        this.drawGraph(graphics2, this.forceFunc);
        graphics2.setColor(Color.white);
        this.drawGraph(graphics2, this.func);
        graphics2.setColor(Color.red);
        for (int n5 = 0; n5 != 1; ++n5) {
            final int n6 = (int)((this.points[n5][0] - this.xRangeStart) / this.xRangeWidth * this.winSize.width);
            final int n7 = this.midy - (int)(this.points[n5][1] * this.ymult);
            graphics2.drawLine(n6 - 3, n7, n6 + 3, n7);
            graphics2.drawLine(n6, n7 - 3, n6, n7 + 3);
        }
        graphics2.setColor(Color.white);
        this.centerString(graphics2, this.lhsfunc.getDescription() + " = " + this.lhsfunc.getRhsScaleDescription() + this.rhsfunc.getDescription(), panelHeight + 10);
        if (this.rhsfunc instanceof ZeroRhsFunc) {
            this.centerString(graphics2, this.lhsfunc.getSolution(), panelHeight + 30);
        }
        graphics.drawImage(this.dbimage, 0, 0, this);
    }
    
    void drawGraph(final Graphics graphics, final double[] array) {
        int n = -1;
        int n2 = -1;
        for (int i = 0; i != this.sampleCount; ++i) {
            final int n3 = this.winSize.width * i / this.sampleCount;
            final double n4 = i * this.xRangeWidth / this.sampleCount + this.xRangeStart;
            final double n5 = array[i];
            int n6 = this.midy - (int)(this.ymult * n5);
            if (Double.isNaN(n5) || Double.isInfinite(n5)) {
                n = n3;
                if (n6 >= this.midy) {
                    final int n7 = this.midy * 2;
                }
            }
            else {
                if (n6 < 0 || n5 > 1000000.0) {
                    if (n2 == 0) {
                        n = n3;
                        continue;
                    }
                    n6 = 0;
                }
                if (n6 > this.midy * 2 || n5 < -1000000.0) {
                    if (n2 == this.midy * 2) {
                        n = n3;
                        continue;
                    }
                    n6 = this.midy * 2;
                }
                if (n != -1) {
                    graphics.drawLine(n, n2, n3, n6);
                }
                n = n3;
                n2 = n6;
            }
        }
    }
    
    void setPoint(final double n, final double n2) {
        this.points[0][0] = n;
        this.points[0][1] = n2;
    }
    
    void edit(final MouseEvent mouseEvent) {
        final int x = mouseEvent.getX();
        final int y = mouseEvent.getY();
        if (this.funcSelected) {
            if (x == this.dragX) {
                this.editFuncPoint(x, y);
                this.dragY = y;
            }
            else {
                final int n = (x < this.dragX) ? x : this.dragX;
                final int n2 = (x < this.dragX) ? y : this.dragY;
                final int n3 = (x > this.dragX) ? x : this.dragX;
                final int n4 = (x > this.dragX) ? y : this.dragY;
                this.dragX = x;
                this.dragY = y;
                for (int i = n; i <= n3; ++i) {
                    this.editFuncPoint(i, n2 + (n4 - n2) * (i - n) / (n3 - n));
                }
            }
            this.cv.repaint(0L);
            return;
        }
        double n5 = x * this.xRangeWidth / this.winSize.width + this.xRangeStart;
        final double n6 = (this.midy - y) / this.ymult;
        if (n5 < this.xRangeStart) {
            n5 = this.xRangeStart;
        }
        if (n5 >= this.xRangeEnd) {
            n5 = this.xRangeEnd;
        }
        this.points[0][0] = n5;
        this.points[0][1] = n6;
        this.cv.repaint(0L);
    }
    
    void editFuncPoint(final int n, final int n2) {
        final double n3 = (this.midy - n2) / this.ymult;
        int i = n * this.sampleCount / this.winSize.width;
        int sampleCount = (n + 1) * this.sampleCount / this.winSize.width;
        if (sampleCount > this.sampleCount) {
            sampleCount = this.sampleCount;
        }
        if (i < 0) {
            i = 0;
        }
        while (i < sampleCount) {
            this.forceFunc[i] = n3;
            ++i;
        }
    }
    
    public void componentHidden(final ComponentEvent componentEvent) {
    }
    
    public void componentMoved(final ComponentEvent componentEvent) {
    }
    
    public void componentShown(final ComponentEvent componentEvent) {
        this.cv.repaint(0L);
    }
    
    public void componentResized(final ComponentEvent componentEvent) {
        this.handleResize();
        this.cv.repaint(0L);
    }
    
    public void actionPerformed(final ActionEvent actionEvent) {
        if (actionEvent.getSource() == this.clearButton) {
            this.doClear();
            this.cv.repaint();
        }
    }
    
    public boolean handleEvent(final Event event) {
        if (event.id == 201) {
            this.applet.destroyFrame();
            return true;
        }
        return super.handleEvent(event);
    }
    
    public void adjustmentValueChanged(final AdjustmentEvent adjustmentEvent) {
        this.cv.repaint(0L);
    }
    
    void setLoadCount() {
        final int maxMaxTerms = this.maxMaxTerms;
        this.maxTerms = maxMaxTerms;
        this.sampleCount = maxMaxTerms;
        this.func = new double[this.sampleCount + 1];
        this.forceFunc = new double[this.sampleCount + 1];
    }
    
    public void mouseDragged(final MouseEvent mouseEvent) {
        this.dragging = true;
        this.edit(mouseEvent);
    }
    
    public void mouseMoved(final MouseEvent mouseEvent) {
        this.dragX = mouseEvent.getX();
        this.dragY = mouseEvent.getY();
        if ((mouseEvent.getModifiers() & 0x10) != 0x0) {
            return;
        }
        if (this.rhsfunc.isCustom()) {
            final int n = (int)((this.points[0][0] - this.xRangeStart) / this.xRangeWidth * this.winSize.width);
            final int n2 = this.midy - (int)(this.points[0][1] * this.ymult);
            final int n3 = n - mouseEvent.getX();
            final int n4 = n2 - mouseEvent.getY();
            this.funcSelected = (n3 * n3 + n4 * n4 > 10);
        }
        else {
            this.funcSelected = false;
        }
    }
    
    public void mouseClicked(final MouseEvent mouseEvent) {
    }
    
    public void mouseEntered(final MouseEvent mouseEvent) {
    }
    
    public void mouseExited(final MouseEvent mouseEvent) {
        if (!this.dragging && this.selectedCoef != -1) {
            this.selectedCoef = -1;
            this.cv.repaint(0L);
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
        this.cv.repaint(0L);
    }
    
    public void itemStateChanged(final ItemEvent itemEvent) {
        if (itemEvent.getItemSelectable() == this.lhsChooser) {
            this.lhsChanged = true;
        }
        if (itemEvent.getItemSelectable() == this.rhsChooser) {
            this.rhsChanged = true;
        }
        this.cv.repaint(0L);
    }
    
    void solveForConstants(final double n, final double n2, final double n3, final double n4) {
        final double n5 = n * n4 - n2 * n3;
        if (n5 == 0.0) {
            System.out.print("solveForConstants: det = 0\n");
            return;
        }
        final double value = (n4 * this.points[0][1] - n2 * this.initialValues[0].getValue()) / n5;
        final double value2 = (-n3 * this.points[0][1] + n * this.initialValues[0].getValue()) / n5;
        this.solutionValues[0].setValue(value);
        this.solutionValues[1].setValue(value2);
    }
    
    void bessjy(double n, final double n2, final double[] array) {
        if (n == 0.0) {
            n = 1.0E-20;
        }
        if (n <= 0.0 || n2 < 0.0) {
            System.out.print("bad arguments in bessjy\n");
            return;
        }
        int n3 = (n < 2.0) ? ((int)(n2 + 0.5)) : ((int)(n2 - n + 1.5));
        if (n3 < 0) {
            n3 = 0;
        }
        final double n4 = n2 - n3;
        final double n5 = n4 * n4;
        final double n6 = 1.0 / n;
        final double n7 = 2.0 * n6;
        final double n8 = n7 / 3.141592653589793;
        int n9 = 1;
        double n10 = n2 * n6;
        if (n10 < 1.0E-30) {
            n10 = 1.0E-30;
        }
        double n11 = n7 * n2;
        double n12 = 0.0;
        double n13 = n10;
        int n14;
        for (n14 = 1; n14 <= 10000.0; ++n14) {
            n11 += n7;
            double n15 = n11 - n12;
            if (Math.abs(n15) < 1.0E-30) {
                n15 = 1.0E-30;
            }
            n13 = n11 - 1.0 / n13;
            if (Math.abs(n13) < 1.0E-30) {
                n13 = 1.0E-30;
            }
            n12 = 1.0 / n15;
            final double n16 = n13 * n12;
            n10 *= n16;
            if (n12 < 0.0) {
                n9 = -n9;
            }
            if (Math.abs(n16 - 1.0) < 1.0E-16) {
                break;
            }
        }
        if (n14 > 10000.0) {
            System.out.print("x too large in bessjy; try asymptotic expansion\n");
            return;
        }
        double n17 = n9 * 1.0E-30;
        double n18 = n10 * n17;
        final double n19 = n17;
        final double n20 = n18;
        double n21 = n2 * n6;
        for (int i = n3; i >= 1; --i) {
            final double n22 = n21 * n17 + n18;
            n21 -= n6;
            n18 = n21 * n22 - n17;
            n17 = n22;
        }
        if (n17 == 0.0) {
            n17 = 1.0E-16;
        }
        final double n23 = n18 / n17;
        double n42;
        double n43;
        double n44;
        if (n < 2.0) {
            final double n24 = 0.5 * n;
            final double n25 = 3.141592653589793 * n4;
            final double n26 = (Math.abs(n25) < 1.0E-16) ? 1.0 : (n25 / Math.sin(n25));
            final double n27 = -Math.log(n24);
            final double n28 = n4 * n27;
            final double n29 = (Math.abs(n28) < 1.0E-16) ? 1.0 : ((Math.exp(n28) - Math.exp(-n28)) / (2.0 * n28));
            final double[] array2 = new double[4];
            this.beschb(n4, array2);
            double n30 = 0.6366197723675814 * n26 * (array2[0] * (Math.exp(n28) + Math.exp(-n28)) / 2.0 + array2[1] * n29 * n27);
            final double exp = Math.exp(n28);
            double n31 = exp / (array2[2] * 3.141592653589793);
            double n32 = 1.0 / (exp * 3.141592653589793 * array2[3]);
            final double n33 = 0.5 * n25;
            final double n34 = (Math.abs(n33) < 1.0E-16) ? 1.0 : (Math.sin(n33) / n33);
            final double n35 = 3.141592653589793 * n33 * n34 * n34;
            double n36 = 1.0;
            final double n37 = -n24 * n24;
            double n38 = n30 + n35 * n32;
            double n39 = n31;
            int n40;
            for (n40 = 1; n40 <= 10000.0; ++n40) {
                n30 = (n40 * n30 + n31 + n32) / (n40 * n40 - n5);
                n36 *= n37 / n40;
                n31 /= n40 - n4;
                n32 /= n40 + n4;
                final double n41 = n36 * (n30 + n35 * n32);
                n38 += n41;
                n39 += n36 * n31 - n40 * n41;
                if (Math.abs(n41) < (1.0 + Math.abs(n38)) * 1.0E-16) {
                    break;
                }
            }
            if (n40 > 10000.0) {
                System.out.print("bessy series failed to converge\n");
                return;
            }
            n42 = -n38;
            n43 = -n39 * n7;
            n44 = n8 / (n4 * n6 * n42 - n43 - n23 * n42);
        }
        else {
            double n45 = 0.25 - n5;
            final double n46 = -0.5 * n6;
            final double n47 = 1.0;
            final double n48 = 2.0 * n;
            double n49 = 2.0;
            final double n50 = n45 * n6 / (n46 * n46 + n47 * n47);
            double n51 = n48 + n47 * n50;
            double n52 = n49 + n46 * n50;
            final double n53 = n48 * n48 + n49 * n49;
            double n54 = n48 / n53;
            double n55 = -n49 / n53;
            final double n56 = n51 * n54 - n52 * n55;
            final double n57 = n51 * n55 + n52 * n54;
            final double n58 = n46 * n56 - n47 * n57;
            double n59 = n46 * n57 + n47 * n56;
            double n60 = n58;
            int n61;
            for (n61 = 2; n61 <= 10000.0; ++n61) {
                n45 += 2 * (n61 - 1);
                n49 += 2.0;
                double n62 = n45 * n54 + n48;
                final double n63 = n45 * n55 + n49;
                if (Math.abs(n62) + Math.abs(n63) < 1.0E-30) {
                    n62 = 1.0E-30;
                }
                final double n64 = n45 / (n51 * n51 + n52 * n52);
                n51 = n48 + n51 * n64;
                n52 = n49 - n52 * n64;
                if (Math.abs(n51) + Math.abs(n52) < 1.0E-30) {
                    n51 = 1.0E-30;
                }
                final double n65 = n62 * n62 + n63 * n63;
                n54 = n62 / n65;
                n55 = n63 / -n65;
                final double n66 = n51 * n54 - n52 * n55;
                final double n67 = n51 * n55 + n52 * n54;
                final double n68 = n60 * n66 - n59 * n67;
                n59 = n60 * n67 + n59 * n66;
                n60 = n68;
                if (Math.abs(n66 - 1.0) + Math.abs(n67) < 1.0E-16) {
                    break;
                }
            }
            if (n61 > 10000.0) {
                System.out.print("cf2 failed in bessjy\n");
                return;
            }
            final double n69 = (n60 - n23) / n59;
            final double sqrt = Math.sqrt(n8 / ((n60 - n23) * n69 + n59));
            n44 = ((n17 > 0.0) ? sqrt : (-sqrt));
            n42 = n44 * n69;
            n43 = n4 * n6 * n42 - n42 * (n60 + n59 / n69);
        }
        final double n70 = n44 / n17;
        array[0] = n19 * n70;
        array[1] = n20 * n70;
        for (int j = 1; j <= n3; ++j) {
            final double n71 = (n4 + j) * n7 * n43 - n42;
            n42 = n43;
            n43 = n71;
        }
        array[3] = n2 * n6 * (array[2] = n42) - n43;
    }
    
    void beschb(final double n, final double[] array) {
        final double[] array2 = { -1.142022680371172, 0.006516511267076, 3.08709017308E-4, -3.470626964E-6, 6.943764E-9, 3.678E-11, -1.36E-13 };
        final double[] array3 = { 1.843740587300906, -0.076852840844786, 0.001271927136655, -4.971736704E-6, -3.312612E-8, 2.4231E-10, -1.7E-13, -1.0E-15 };
        final double n2 = 8.0 * n * n - 1.0;
        array[0] = this.chebev(-1.0, 1.0, array2, 7, n2);
        array[1] = this.chebev(-1.0, 1.0, array3, 8, n2);
        array[2] = array[1] - n * array[0];
        array[3] = array[1] + n * array[0];
    }
    
    double chebev(final double n, final double n2, final double[] array, final int n3, final double n4) {
        double n5 = 0.0;
        double n6 = 0.0;
        if ((n4 - n) * (n4 - n2) > 0.0) {
            System.out.print("x not in range in routine CHEBEV\n");
            return 0.0;
        }
        final double n8;
        final double n7 = 2.0 * (n8 = (2.0 * n4 - n - n2) / (n2 - n));
        for (int i = n3 - 1; i >= 1; --i) {
            final double n9 = n5;
            n5 = n7 * n5 - n6 + array[i];
            n6 = n9;
        }
        return n8 * n5 - n6 + 0.5 * array[0];
    }
    
    void legendreP(final int n, final double n2, final double[] array, final double[] array2) {
        double n3 = 1.0;
        double n4 = n2;
        array[0] = 1.0;
        array[1] = n2;
        array2[0] = 0.0;
        array2[1] = 1.0;
        for (int i = 2; i <= n; ++i) {
            final double n5 = (2 * i - 1.0) / i * n2 * n4 - (i - 1.0) / i * n3;
            array[i] = n5;
            if (n2 == 1.0 || n2 == -1.0) {
                array2[i] = 0.5 * Math.pow(n2, i + 1) * i * (i + 1);
            }
            else {
                array2[i] = i * (n4 - n2 * n5) / (1.0 - n2 * n2);
            }
            n3 = n4;
            n4 = n5;
        }
    }
    
    void legendreQ(final int n, double n2, final double[] array, final double[] array2) {
        if (n2 == 1.0) {
            n2 = 0.999999999999999;
        }
        if (n2 == -1.0) {
            n2 = -0.99999999999999;
        }
        double n3 = 0.5 * Math.log((1.0 + n2) / (1.0 - n2));
        double n4 = n2 * n3 - 1.0;
        array[0] = n3;
        array[1] = n4;
        array2[0] = 1.0 / (1.0 - n2 * n2);
        array2[1] = array[0] + n2 * array2[0];
        for (int i = 2; i <= n; ++i) {
            final double n5 = ((2 * i - 1.0) * n2 * n4 - (i - 1) * n3) / i;
            array[i] = n5;
            array2[i] = (array[i - 1] - n2 * n5) * i / (1.0 - n2 * n2);
            n3 = n4;
            n4 = n5;
        }
    }
    
    abstract class LhsFunc
    {
        void setup() {
        }
        
        void newFunc() {
        }
        
        void newFuncSolution() {
        }
        
        boolean positiveOnly() {
            return false;
        }
        
        boolean useRungeKutta() {
            return true;
        }
        
        double getRangeWidth() {
            return 100.0;
        }
        
        double calculate(final double n) {
            return 0.0;
        }
        
        double calculateDiffs(final double n, final double[] array, final double n2) {
            return 0.0;
        }
        
        double getRhsScale() {
            return 1.0;
        }
        
        String getRhsScaleDescription() {
            return "";
        }
        
        abstract int getOrder();
        
        abstract String getDescription();
        
        abstract LhsFunc createNext();
        
        abstract String getName();
        
        double getRangeStart() {
            return DiffEqFrame.this.lhsfunc.positiveOnly() ? 0.0 : (-this.getRangeWidth() / 2.0);
        }
        
        double getRangeEnd() {
            return this.getRangeStart() + this.getRangeWidth();
        }
        
        String getSolution() {
            return "";
        }
        
        void setSolution() {
        }
    }
    
    class Order2LhsFunc extends LhsFunc
    {
        double va;
        double vb;
        double vc;
        boolean complexDisc;
        boolean linear;
        
        String getName() {
            return "ay''+by'+cy";
        }
        
        int getOrder() {
            return 2;
        }
        
        void setup() {
            this.va = DiffEqFrame.this.lhsValues[0].getValue();
            this.vb = DiffEqFrame.this.lhsValues[1].getValue();
            this.vc = DiffEqFrame.this.lhsValues[2].getValue();
        }
        
        double calculateDiffs(final double n, final double[] array, final double n2) {
            return -(this.vb * array[1] + this.vc * array[0] - n2) / this.va;
        }
        
        String getDescription() {
            return "ay'' + by' + cy";
        }
        
        void newFunc() {
            DiffEqFrame.this.lhsValues[0].setup("a", 1.0, 4);
            DiffEqFrame.this.lhsValues[1].setup("b", 0.07, 0);
            DiffEqFrame.this.lhsValues[2].setup("c", 1.0, 0);
            DiffEqFrame.this.setPoint(this.getRangeStart(), 10.0);
        }
        
        void newFuncSolution() {
            DiffEqFrame.this.solutionValues[0].setLabel("c1");
            DiffEqFrame.this.solutionValues[1].setLabel("c2");
            DiffEqFrame.this.solutionValues[2].setLabel("c3");
            DiffEqFrame.this.solutionValues[2].setFlags(8);
            DiffEqFrame.this.solutionValues[3].setLabel("c4");
            DiffEqFrame.this.solutionValues[3].setFlags(8);
        }
        
        String getSolution() {
            double n = this.vb * this.vb - 4.0 * this.va * this.vc;
            this.complexDisc = false;
            this.linear = false;
            if (n < 0.0) {
                n = -n;
                this.complexDisc = true;
            }
            final double sqrt = Math.sqrt(n);
            double n2 = 0.0;
            double value = 0.0;
            double value2 = 0.0;
            final double n3 = DiffEqFrame.this.points[0][0];
            double exp;
            double exp2;
            double n4;
            double n5;
            if (this.complexDisc) {
                value = (n2 = -this.vb / (2.0 * this.va));
                value2 = sqrt / (2.0 * this.va);
                exp = Math.exp(n2 * n3) * Math.sin(value2 * n3);
                exp2 = Math.exp(n2 * n3) * Math.cos(value2 * n3);
                n4 = Math.exp(n2 * n3) * (n2 * Math.sin(value2 * n3) + value2 * Math.cos(value2 * n3));
                n5 = Math.exp(n2 * n3) * (n2 * Math.cos(value2 * n3) - value2 * Math.sin(value2 * n3));
            }
            else if (this.vb == 0.0 && this.vc == 0.0) {
                this.linear = true;
                exp = DiffEqFrame.this.points[0][0];
                n4 = (exp2 = 1.0);
                n5 = 0.0;
            }
            else {
                n2 = (-this.vb + sqrt) / (2.0 * this.va);
                value = (-this.vb - sqrt) / (2.0 * this.va);
                exp = Math.exp(n2 * n3);
                exp2 = Math.exp(value * n3);
                n4 = n2 * Math.exp(n2 * n3);
                n5 = value * Math.exp(value * n3);
            }
            DiffEqFrame.this.solveForConstants(exp, exp2, n4, n5);
            if (this.complexDisc) {
                DiffEqFrame.this.solutionValues[2].setValue(n2);
                DiffEqFrame.this.solutionValues[3].setValue(value2);
                return "y = exp(c3 x) (c1 sin(c4 x)+c2 cos(c4 x))";
            }
            if (this.linear) {
                return "y = c1 x + c2";
            }
            DiffEqFrame.this.solutionValues[2].setValue(n2);
            DiffEqFrame.this.solutionValues[3].setValue(value);
            if (n2 == 0.0) {
                return "y = c1 + c2 exp(c4 x)";
            }
            return "y = c1 exp(c3 x) + c2 exp(c4 x)";
        }
        
        void setSolution() {
            final double n = DiffEqFrame.this.points[0][0];
            if (this.complexDisc) {
                final double value = DiffEqFrame.this.solutionValues[0].getValue();
                final double value2 = DiffEqFrame.this.solutionValues[1].getValue();
                final double value3 = DiffEqFrame.this.solutionValues[2].getValue();
                final double value4 = DiffEqFrame.this.solutionValues[3].getValue();
                DiffEqFrame.this.points[0][1] = Math.exp(value3 * n) * (value * Math.sin(value4 * n) + value2 * Math.cos(value4 * n));
                DiffEqFrame.this.initialValues[0].setValue(Math.exp(value3 * n) * ((value * value3 - value2 * value4) * Math.sin(value4 * n) + (value2 * value3 + value * value4) * Math.cos(value4 * n)));
            }
            else if (this.linear) {
                final double value5 = DiffEqFrame.this.solutionValues[0].getValue();
                DiffEqFrame.this.points[0][1] = value5 * n + DiffEqFrame.this.solutionValues[1].getValue();
                DiffEqFrame.this.initialValues[0].setValue(value5);
            }
            else {
                final double value6 = DiffEqFrame.this.solutionValues[0].getValue();
                final double value7 = DiffEqFrame.this.solutionValues[1].getValue();
                final double value8 = DiffEqFrame.this.solutionValues[2].getValue();
                final double value9 = DiffEqFrame.this.solutionValues[3].getValue();
                DiffEqFrame.this.points[0][1] = value6 * Math.exp(value8 * n) + value7 * Math.exp(value9 * n);
                DiffEqFrame.this.initialValues[0].setValue(value6 * value8 * Math.exp(value8 * n) + value7 * value9 * Math.exp(value9 * n));
            }
        }
        
        LhsFunc createNext() {
            return new Order1LhsFunc();
        }
    }
    
    class OscillatorLhsFunc extends Order2LhsFunc
    {
        String getName() {
            return "harmonic oscillator";
        }
        
        double getRhsScale() {
            return super.vc;
        }
        
        String getRhsScaleDescription() {
            return "w² ";
        }
        
        void setup() {
            final double value = DiffEqFrame.this.lhsValues[0].getValue();
            final double value2 = DiffEqFrame.this.lhsValues[1].getValue();
            super.va = 1.0;
            super.vb = 2.0 * value;
            super.vc = value2 * value2;
        }
        
        String getDescription() {
            return "y'' + 2by' + w² y";
        }
        
        void newFunc() {
            DiffEqFrame.this.lhsValues[0].setup("b", 0.035, 1);
            DiffEqFrame.this.lhsValues[1].setup("w", 1.0, 5);
            DiffEqFrame.this.lhsValues[1].setMax(7.0);
            DiffEqFrame.this.setPoint(this.getRangeStart(), 10.0);
        }
        
        void newFuncSolution() {
            DiffEqFrame.this.solutionValues[0].setLabel("c1");
            DiffEqFrame.this.solutionValues[1].setLabel("c2");
            DiffEqFrame.this.solutionValues[2].setLabel("c3");
            DiffEqFrame.this.solutionValues[3].setLabel("c4");
            DiffEqFrame.this.solutionValues[2].setFlags(8);
            DiffEqFrame.this.solutionValues[3].setFlags(8);
        }
        
        LhsFunc createNext() {
            return new Order2LhsFunc();
        }
    }
    
    class Order1LhsFunc extends LhsFunc
    {
        double va;
        double vb;
        
        String getName() {
            return "ay'+by";
        }
        
        int getOrder() {
            return 1;
        }
        
        void setup() {
            this.va = DiffEqFrame.this.lhsValues[0].getValue();
            this.vb = DiffEqFrame.this.lhsValues[1].getValue();
        }
        
        double getRhsScale() {
            return this.vb;
        }
        
        String getRhsScaleDescription() {
            return "b ";
        }
        
        double calculateDiffs(final double n, final double[] array, final double n2) {
            return (-this.vb * array[0] + n2) / this.va;
        }
        
        String getDescription() {
            return "ay' + by";
        }
        
        void newFunc() {
            DiffEqFrame.this.lhsValues[0].setup("a", 23.0, 4);
            DiffEqFrame.this.lhsValues[1].setup("b", 1.0, 0);
            DiffEqFrame.this.setPoint(0.0, 1.0);
        }
        
        void newFuncSolution() {
            DiffEqFrame.this.solutionValues[0].setLabel("c");
            DiffEqFrame.this.solutionValues[1].setLabel("d");
            DiffEqFrame.this.solutionValues[1].setFlags(8);
        }
        
        String getSolution() {
            final double value = -this.vb / this.va;
            DiffEqFrame.this.solutionValues[0].setValue(DiffEqFrame.this.points[0][1] / Math.exp(value * DiffEqFrame.this.points[0][0]));
            DiffEqFrame.this.solutionValues[1].setValue(value);
            return "y = c exp(d x)";
        }
        
        void setSolution() {
            DiffEqFrame.this.points[0][1] = DiffEqFrame.this.solutionValues[0].getValue() * Math.exp(DiffEqFrame.this.solutionValues[1].getValue() * DiffEqFrame.this.points[0][0]);
        }
        
        LhsFunc createNext() {
            return new FreeFallLhsFunc();
        }
    }
    
    class FreeFallLhsFunc extends LhsFunc
    {
        double va;
        double vb;
        double vc;
        
        String getName() {
            return "falling object";
        }
        
        int getOrder() {
            return 2;
        }
        
        void setup() {
            this.va = DiffEqFrame.this.lhsValues[0].getValue();
            this.vb = DiffEqFrame.this.lhsValues[1].getValue();
            this.vc = DiffEqFrame.this.lhsValues[2].getValue() * this.va;
        }
        
        double calculateDiffs(final double n, final double[] array, final double n2) {
            return -(this.vb * array[1] + this.vc - n2) / this.va;
        }
        
        String getDescription() {
            return "my'' + by' + mg";
        }
        
        void newFunc() {
            DiffEqFrame.this.lhsValues[0].setup("m", 1.0, 5);
            DiffEqFrame.this.lhsValues[1].setup("b", 0.07, 1);
            DiffEqFrame.this.lhsValues[2].setup("g", 0.098, 0);
            DiffEqFrame.this.setPoint(this.getRangeStart(), 10.0);
        }
        
        void newFuncSolution() {
            DiffEqFrame.this.solutionValues[0].setLabel("c1");
            DiffEqFrame.this.solutionValues[1].setLabel("c2");
            DiffEqFrame.this.solutionValues[1].setFlags(8);
            DiffEqFrame.this.solutionValues[2].setLabel("c3");
            DiffEqFrame.this.solutionValues[2].setFlags(8);
            DiffEqFrame.this.solutionValues[3].setLabel("c4");
        }
        
        String getSolution() {
            final double n = DiffEqFrame.this.points[0][0];
            double value = -this.vb / this.va;
            double value2 = -this.vc / this.vb;
            double value3;
            double value4;
            if (this.vb == 0.0) {
                value3 = 0.0;
                value = -this.vc / 2.0;
                value2 = DiffEqFrame.this.initialValues[0].getValue() - 2.0 * value * n;
                value4 = DiffEqFrame.this.points[0][1] - value2 * n - value * n * n;
            }
            else {
                value3 = (DiffEqFrame.this.initialValues[0].getValue() - value2) / (value * Math.exp(value * n));
                value4 = DiffEqFrame.this.points[0][1] - value2 * n - value3 * Math.exp(value * n);
            }
            DiffEqFrame.this.solutionValues[0].setValue(value3);
            DiffEqFrame.this.solutionValues[1].setValue(value);
            DiffEqFrame.this.solutionValues[2].setValue(value2);
            DiffEqFrame.this.solutionValues[3].setValue(value4);
            if (this.vb == 0.0) {
                return "y = c2 x² + c3 x + c4";
            }
            return "y = c1 exp(c2 x) + c3 x + c4";
        }
        
        void setSolution() {
            final double n = DiffEqFrame.this.points[0][0];
            final double value = DiffEqFrame.this.solutionValues[0].getValue();
            final double value2 = DiffEqFrame.this.solutionValues[1].getValue();
            final double value3 = DiffEqFrame.this.solutionValues[2].getValue();
            final double value4 = DiffEqFrame.this.solutionValues[3].getValue();
            if (this.vb == 0.0) {
                DiffEqFrame.this.points[0][1] = value2 * n * n + value3 * n + value4;
                DiffEqFrame.this.initialValues[0].setValue(2.0 * value2 * n + value3);
            }
            else {
                DiffEqFrame.this.points[0][1] = value * Math.exp(value2 * n) + value3 * n + value4;
                DiffEqFrame.this.initialValues[0].setValue(value * value2 * Math.exp(value2 * n) + value3);
            }
        }
        
        LhsFunc createNext() {
            return new BesselLhsFunc();
        }
    }
    
    class BesselLhsFunc extends LhsFunc
    {
        double va;
        double c1;
        double c2;
        double[] vals;
        
        String getName() {
            return "Bessel";
        }
        
        int getOrder() {
            return 2;
        }
        
        boolean positiveOnly() {
            return true;
        }
        
        boolean useRungeKutta() {
            return false;
        }
        
        void setup() {
            this.va = DiffEqFrame.this.lhsValues[0].getValue();
            this.va *= this.va;
            this.vals = new double[4];
            this.getSolution();
            this.c1 = DiffEqFrame.this.solutionValues[0].getValue();
            this.c2 = DiffEqFrame.this.solutionValues[1].getValue();
        }
        
        double calculate(final double n) {
            DiffEqFrame.this.bessjy(n, DiffEqFrame.this.lhsValues[0].getValue(), this.vals);
            return this.vals[0] * this.c1 + this.vals[2] * this.c2;
        }
        
        String getDescription() {
            return "x² y'' + xy' + (x²-p²)y";
        }
        
        void newFunc() {
            DiffEqFrame.this.lhsValues[0].setup("p", 0.0, 1);
            DiffEqFrame.this.setPoint(0.0, 10.0);
        }
        
        void newFuncSolution() {
            DiffEqFrame.this.solutionValues[0].setLabel("c1");
            DiffEqFrame.this.solutionValues[1].setLabel("c2");
        }
        
        String getSolution() {
            final double n = DiffEqFrame.this.points[0][0];
            final double value = DiffEqFrame.this.lhsValues[0].getValue();
            final double[] array = new double[4];
            DiffEqFrame.this.bessjy(n, value, array);
            DiffEqFrame.this.solveForConstants(array[0], array[2], array[1], array[3]);
            return "y = c1 Jp(x) + c2 Yp(x)";
        }
        
        void setSolution() {
            final double n = DiffEqFrame.this.points[0][0];
            final double value = DiffEqFrame.this.lhsValues[0].getValue();
            final double[] array = new double[4];
            DiffEqFrame.this.bessjy(n, value, array);
            final double value2 = DiffEqFrame.this.solutionValues[0].getValue();
            final double value3 = DiffEqFrame.this.solutionValues[1].getValue();
            DiffEqFrame.this.points[0][1] = array[0] * value2 + array[2] * value3;
            DiffEqFrame.this.initialValues[0].setValue(array[1] * value2 + array[3] * value3);
        }
        
        LhsFunc createNext() {
            return new BesselIntegerLhsFunc();
        }
    }
    
    class BesselIntegerLhsFunc extends BesselLhsFunc
    {
        String getName() {
            return "Bessel (integer p)";
        }
        
        void newFunc() {
            super.newFunc();
            DiffEqFrame.this.lhsValues[0].setFlags(3);
        }
        
        LhsFunc createNext() {
            return new BesselHalfIntegerLhsFunc();
        }
    }
    
    class BesselHalfIntegerLhsFunc extends BesselLhsFunc
    {
        String getName() {
            return "Bessel (half-integer p)";
        }
        
        void newFunc() {
            super.newFunc();
            DiffEqFrame.this.lhsValues[0].setValue(0.5);
            DiffEqFrame.this.lhsValues[0].setFlags(17);
            DiffEqFrame.this.setPoint(this.getRangeWidth() / 2.0, 1.0);
        }
        
        LhsFunc createNext() {
            return new LegendreLhsFunc();
        }
    }
    
    class LegendreLhsFunc extends LhsFunc
    {
        double va;
        
        String getName() {
            return "Legendre";
        }
        
        int getOrder() {
            return 2;
        }
        
        double getRangeWidth() {
            return 2.0;
        }
        
        void setup() {
            this.va = DiffEqFrame.this.lhsValues[0].getValue();
            this.va *= this.va + 1.0;
        }
        
        double calculateDiffs(final double n, final double[] array, final double n2) {
            return -(-2.0 * n * array[1] + this.va * array[0] - n2) / (1.0 - n * n);
        }
        
        String getDescription() {
            return "(1-x²)y'' -2xy' + p(p+1)y";
        }
        
        void newFunc() {
            DiffEqFrame.this.lhsValues[0].setup("p", 7.0, 1);
            DiffEqFrame.this.lhsValues[0].setMax(40.0);
            DiffEqFrame.this.setPoint(0.0, -5.0);
        }
        
        LhsFunc createNext() {
            return new LegendreIntegerLhsFunc();
        }
    }
    
    class LegendreIntegerLhsFunc extends LegendreLhsFunc
    {
        String getName() {
            return "Legendre (integer order)";
        }
        
        void newFunc() {
            super.newFunc();
            DiffEqFrame.this.lhsValues[0].setup("n", 7.0, 3);
            DiffEqFrame.this.lhsValues[0].setMax(40.0);
        }
        
        void newFuncSolution() {
            DiffEqFrame.this.solutionValues[0].setLabel("c1");
            DiffEqFrame.this.solutionValues[1].setLabel("c2");
        }
        
        String getSolution() {
            final double n = DiffEqFrame.this.points[0][0];
            final int n2 = (int)DiffEqFrame.this.lhsValues[0].getValue();
            final double[] array = new double[n2 + 2];
            final double[] array2 = new double[n2 + 2];
            DiffEqFrame.this.legendreP(n2, n, array, array2);
            final double n3 = array[n2];
            final double n4 = array2[n2];
            DiffEqFrame.this.legendreQ(n2, n, array, array2);
            DiffEqFrame.this.solveForConstants(n3, array[n2], n4, array2[n2]);
            return "y = c1 Pn(x) + c2 Qn(x)";
        }
        
        void setSolution() {
            final double n = DiffEqFrame.this.points[0][0];
            final int n2 = (int)DiffEqFrame.this.lhsValues[0].getValue();
            final double[] array = new double[n2 + 2];
            final double[] array2 = new double[n2 + 2];
            DiffEqFrame.this.legendreP(n2, n, array, array2);
            final double n3 = array[n2];
            final double n4 = array2[n2];
            DiffEqFrame.this.legendreQ(n2, n, array, array2);
            final double n5 = array[n2];
            final double n6 = array2[n2];
            final double value = DiffEqFrame.this.solutionValues[0].getValue();
            final double value2 = DiffEqFrame.this.solutionValues[1].getValue();
            DiffEqFrame.this.points[0][1] = n3 * value + n5 * value2;
            DiffEqFrame.this.initialValues[0].setValue(n4 * value + n6 * value2);
        }
        
        LhsFunc createNext() {
            return new HermiteLhsFunc();
        }
    }
    
    class HermiteLhsFunc extends LhsFunc
    {
        double va;
        double vb;
        double vc;
        
        String getName() {
            return "Hermite";
        }
        
        int getOrder() {
            return 2;
        }
        
        double getRangeWidth() {
            return 4.0;
        }
        
        void setup() {
            this.va = DiffEqFrame.this.lhsValues[0].getValue();
        }
        
        double calculateDiffs(final double n, final double[] array, final double n2) {
            return 2.0 * n * array[1] - 2.0 * this.va * array[0] + n2;
        }
        
        String getDescription() {
            return "y''-2xy'+2ny";
        }
        
        void newFunc() {
            DiffEqFrame.this.lhsValues[0].setup("n", 12.0, 1);
            DiffEqFrame.this.lhsValues[0].setMax(2000.0);
            DiffEqFrame.this.setPoint(0.0, -5.0);
        }
        
        LhsFunc createNext() {
            return new JacobiLhsFunc();
        }
    }
    
    class JacobiLhsFunc extends LhsFunc
    {
        double va;
        double vb;
        double vn;
        
        String getName() {
            return "Jacobi";
        }
        
        int getOrder() {
            return 2;
        }
        
        double getRangeWidth() {
            return 2.0;
        }
        
        void setup() {
            this.vn = DiffEqFrame.this.lhsValues[0].getValue();
            this.va = DiffEqFrame.this.lhsValues[1].getValue();
            this.vb = DiffEqFrame.this.lhsValues[2].getValue();
        }
        
        double calculateDiffs(final double n, final double[] array, final double n2) {
            return -((this.vb - this.va - (this.va + this.vb + 2.0) * n) * array[1] + this.vn * (this.vn + this.va + this.vb + 1.0) * array[0] + n2) / (1.0 - n * n);
        }
        
        String getDescription() {
            return "(1-x²)y''+(b-a-(a+b+2)x)y'+n(n+a+b+1)y";
        }
        
        void newFunc() {
            DiffEqFrame.this.lhsValues[0].setup("n", 10.0, 0);
            DiffEqFrame.this.lhsValues[1].setup("a", 1.0, 0);
            DiffEqFrame.this.lhsValues[2].setup("b", 1.0, 0);
            DiffEqFrame.this.setPoint(0.0, 2.0);
        }
        
        LhsFunc createNext() {
            return new Order4LhsFunc();
        }
    }
    
    class Order4LhsFunc extends LhsFunc
    {
        double va;
        double vb;
        double vc;
        double vd;
        double ve;
        
        String getName() {
            return "ay''''+by'''+cy''+dy'+ey";
        }
        
        int getOrder() {
            return 4;
        }
        
        void setup() {
            this.va = DiffEqFrame.this.lhsValues[0].getValue();
            this.vb = DiffEqFrame.this.lhsValues[1].getValue();
            this.vc = DiffEqFrame.this.lhsValues[2].getValue();
            this.vd = DiffEqFrame.this.lhsValues[3].getValue();
            this.ve = DiffEqFrame.this.lhsValues[4].getValue();
        }
        
        double calculateDiffs(final double n, final double[] array, final double n2) {
            return -(this.vb * array[3] + this.vc * array[2] + this.vd * array[1] + this.ve * array[0] - n2) / this.va;
        }
        
        String getDescription() {
            return "ay'''' + by''' + cy'' + dy' + ey";
        }
        
        void newFunc() {
            DiffEqFrame.this.lhsValues[0].setup("a", 6.0, 0);
            DiffEqFrame.this.lhsValues[1].setup("b", 0.0, 0);
            DiffEqFrame.this.lhsValues[2].setup("c", 1.0, 0);
            DiffEqFrame.this.lhsValues[3].setup("d", 0.0, 0);
            DiffEqFrame.this.lhsValues[4].setup("e", 0.0, 0);
            DiffEqFrame.this.setPoint(0.0, 0.0);
            DiffEqFrame.this.initialValues[0].setValue(0.1);
            DiffEqFrame.this.initialValues[1].setValue(0.5);
        }
        
        LhsFunc createNext() {
            return new EquidimensionalOrder2LhsFunc();
        }
    }
    
    class EquidimensionalOrder2LhsFunc extends LhsFunc
    {
        double va;
        double vb;
        double vc;
        
        String getName() {
            return "equidimensional";
        }
        
        int getOrder() {
            return 2;
        }
        
        boolean positiveOnly() {
            return true;
        }
        
        void setup() {
            this.va = DiffEqFrame.this.lhsValues[0].getValue();
            this.vb = DiffEqFrame.this.lhsValues[1].getValue();
            this.vc = DiffEqFrame.this.lhsValues[2].getValue();
        }
        
        double calculateDiffs(final double n, final double[] array, final double n2) {
            return -(this.vb * array[1] * n + this.vc * array[0] - n2) / (this.va * n * n);
        }
        
        String getDescription() {
            return "ax² y'' + bx y' + c y";
        }
        
        void newFunc() {
            DiffEqFrame.this.lhsValues[0].setup("a", 0.5, 0);
            DiffEqFrame.this.lhsValues[1].setup("b", 0.0, 0);
            DiffEqFrame.this.lhsValues[2].setup("c", 20.0, 0);
            DiffEqFrame.this.setPoint(this.getRangeWidth() / 2.0, 5.0);
        }
        
        LhsFunc createNext() {
            return new PendulumLhsFunc();
        }
    }
    
    class PendulumLhsFunc extends LhsFunc
    {
        double beta;
        double omega;
        double omega2;
        
        String getName() {
            return "pendulum";
        }
        
        int getOrder() {
            return 2;
        }
        
        void setup() {
            this.beta = DiffEqFrame.this.lhsValues[0].getValue();
            this.omega = DiffEqFrame.this.lhsValues[1].getValue();
            this.omega2 = this.omega * this.omega;
        }
        
        double getRhsScale() {
            return this.omega2;
        }
        
        String getRhsScaleDescription() {
            return "w² ";
        }
        
        double calculateDiffs(final double n, final double[] array, final double n2) {
            return -2.0 * this.beta * array[1] - this.omega2 * Math.sin(array[0]) + n2;
        }
        
        String getDescription() {
            return "y'' + 2by' + w² sin(y)";
        }
        
        void newFunc() {
            DiffEqFrame.this.lhsValues[0].setup("b", 0.035, 1);
            DiffEqFrame.this.lhsValues[1].setup("w", 1.0, 5);
            DiffEqFrame.this.setPoint(this.getRangeStart(), 3.1);
        }
        
        LhsFunc createNext() {
            return new DuffingLhsFunc();
        }
    }
    
    class DuffingLhsFunc extends LhsFunc
    {
        double va;
        double vb;
        double vc;
        double vd;
        
        String getName() {
            return "Duffing";
        }
        
        int getOrder() {
            return 2;
        }
        
        void setup() {
            this.va = DiffEqFrame.this.lhsValues[0].getValue();
            this.vb = DiffEqFrame.this.lhsValues[1].getValue();
            this.vc = DiffEqFrame.this.lhsValues[2].getValue();
            this.vd = DiffEqFrame.this.lhsValues[3].getValue();
        }
        
        double calculateDiffs(final double n, final double[] array, final double n2) {
            return -(this.vb * array[1] + this.vc * array[0] + this.vd * array[0] * array[0] * array[0] - n2) / this.va;
        }
        
        String getDescription() {
            return "ay'' + by' + cy + dy³";
        }
        
        void newFunc() {
            DiffEqFrame.this.lhsValues[0].setup("a", 6.0, 0);
            DiffEqFrame.this.lhsValues[1].setup("b", 0.6, 0);
            DiffEqFrame.this.lhsValues[2].setup("c", 1.0, 0);
            DiffEqFrame.this.lhsValues[3].setup("d", 0.77, 0);
            DiffEqFrame.this.setPoint(this.getRangeStart(), 10.0);
        }
        
        LhsFunc createNext() {
            return new VanDerPolFunc();
        }
    }
    
    class VanDerPolFunc extends LhsFunc
    {
        double va;
        double vb;
        double vc;
        double vd;
        
        String getName() {
            return "Van der Pol";
        }
        
        int getOrder() {
            return 2;
        }
        
        void setup() {
            this.va = DiffEqFrame.this.lhsValues[0].getValue();
            this.vb = DiffEqFrame.this.lhsValues[1].getValue();
            this.vc = DiffEqFrame.this.lhsValues[2].getValue();
            this.vd = DiffEqFrame.this.lhsValues[3].getValue();
        }
        
        double calculateDiffs(final double n, final double[] array, final double n2) {
            return (this.vb * (this.vc * this.vc - array[0] * array[0]) * array[1] - this.vd * array[0] + n2) / this.va;
        }
        
        String getDescription() {
            return "ay''+b(c²-y²)y'+dy";
        }
        
        void newFunc() {
            DiffEqFrame.this.lhsValues[0].setup("a", 6.0, 0);
            DiffEqFrame.this.lhsValues[1].setup("b", 0.04, 0);
            DiffEqFrame.this.lhsValues[2].setup("c", 3.0, 0);
            DiffEqFrame.this.lhsValues[3].setup("d", 21.0, 0);
            DiffEqFrame.this.setPoint(this.getRangeStart(), 1.0);
        }
        
        LhsFunc createNext() {
            return null;
        }
    }
    
    abstract class RhsFunc
    {
        void setup() {
        }
        
        boolean isCustom() {
            return false;
        }
        
        abstract double calculate(final double p0, final double[] p1);
        
        abstract RhsFunc createNext();
        
        abstract String getName();
        
        abstract String getDescription();
        
        void newFunc() {
        }
    }
    
    class ZeroRhsFunc extends RhsFunc
    {
        String getName() {
            return "zero";
        }
        
        double calculate(final double n, final double[] array) {
            return 0.0;
        }
        
        RhsFunc createNext() {
            return new ImpulseRhsFunc();
        }
        
        String getDescription() {
            return "0";
        }
    }
    
    class ImpulseRhsFunc extends RhsFunc
    {
        double vd;
        double ve;
        
        String getName() {
            return "impulse";
        }
        
        void setup() {
            this.vd = DiffEqFrame.this.rhsValues[0].getValue();
            this.ve = DiffEqFrame.this.rhsValues[1].getValue();
        }
        
        double calculate(final double n, final double[] array) {
            return (n < 0.0) ? 0.0 : ((n < this.vd) ? this.ve : 0.0);
        }
        
        RhsFunc createNext() {
            return new StepRhsFunc();
        }
        
        String getDescription() {
            return "h(H(x-g)-H(x))";
        }
        
        void newFunc() {
            DiffEqFrame.this.rhsValues[0].setup("g", 20.0, 0);
            DiffEqFrame.this.rhsValues[1].setup("h", 3.0, 0);
        }
    }
    
    class StepRhsFunc extends RhsFunc
    {
        double ve;
        
        void setup() {
            this.ve = DiffEqFrame.this.rhsValues[0].getValue();
        }
        
        String getName() {
            return "step";
        }
        
        double calculate(final double n, final double[] array) {
            return (n > 0.0) ? this.ve : 0.0;
        }
        
        String getDescription() {
            return "h H(x)";
        }
        
        RhsFunc createNext() {
            return new SquareWaveRhsFunc();
        }
        
        void newFunc() {
            DiffEqFrame.this.rhsValues[0].setup("h", 3.0, 0);
        }
    }
    
    class SquareWaveRhsFunc extends RhsFunc
    {
        double vd;
        double ve;
        
        String getName() {
            return "square wave";
        }
        
        void setup() {
            this.vd = 6.2831853 / DiffEqFrame.this.rhsValues[0].getValue();
            this.ve = DiffEqFrame.this.rhsValues[1].getValue();
        }
        
        double calculate(final double n, final double[] array) {
            return (((int)n + 100) % this.vd > this.vd / 2.0) ? (-this.ve) : this.ve;
        }
        
        String getDescription() {
            return "h Square(gx)";
        }
        
        RhsFunc createNext() {
            return new SineWaveRhsFunc();
        }
        
        void newFunc() {
            DiffEqFrame.this.rhsValues[0].setup("g", 0.25, 0);
            DiffEqFrame.this.rhsValues[1].setup("h", 3.0, 0);
        }
    }
    
    class SineWaveRhsFunc extends RhsFunc
    {
        double vd;
        double ve;
        
        String getName() {
            return "sine wave";
        }
        
        void setup() {
            this.vd = DiffEqFrame.this.rhsValues[0].getValue();
            this.ve = DiffEqFrame.this.rhsValues[1].getValue();
        }
        
        double calculate(final double n, final double[] array) {
            return Math.sin(n * this.vd) * this.ve;
        }
        
        String getDescription() {
            return "h sin(gx)";
        }
        
        RhsFunc createNext() {
            return new SawtoothRhsFunc();
        }
        
        void newFunc() {
            DiffEqFrame.this.rhsValues[0].setup("g", 0.25, 0);
            DiffEqFrame.this.rhsValues[1].setup("h", 3.0, 0);
        }
    }
    
    class SawtoothRhsFunc extends RhsFunc
    {
        double vd;
        double ve;
        
        String getName() {
            return "sawtooth";
        }
        
        void setup() {
            this.vd = 6.2831853 / DiffEqFrame.this.rhsValues[0].getValue();
            this.ve = DiffEqFrame.this.rhsValues[1].getValue();
        }
        
        double calculate(double n, final double[] array) {
            n = (n + 100.0) % this.vd;
            return (2.0 * n / this.vd - 1.0) * this.ve;
        }
        
        String getDescription() {
            return "h Saw(gx)";
        }
        
        RhsFunc createNext() {
            return new TriangleRhsFunc();
        }
        
        void newFunc() {
            DiffEqFrame.this.rhsValues[0].setup("g", 0.25, 0);
            DiffEqFrame.this.rhsValues[1].setup("h", 3.0, 0);
        }
    }
    
    class TriangleRhsFunc extends RhsFunc
    {
        double vd;
        double ve;
        
        String getName() {
            return "triangle";
        }
        
        void setup() {
            this.vd = 3.14159265 / DiffEqFrame.this.rhsValues[0].getValue();
            this.ve = DiffEqFrame.this.rhsValues[1].getValue();
        }
        
        double calculate(double n, final double[] array) {
            n = (n + 100.0) % (this.vd * 2.0);
            if (n >= this.vd) {
                return -(2.0 * (n - this.vd) / this.vd - 1.0) * this.ve;
            }
            return (2.0 * n / this.vd - 1.0) * this.ve;
        }
        
        String getDescription() {
            return "h Tri(gx)";
        }
        
        void newFunc() {
            DiffEqFrame.this.rhsValues[0].setup("g", 0.25, 0);
            DiffEqFrame.this.rhsValues[1].setup("h", 3.0, 0);
        }
        
        RhsFunc createNext() {
            return new LinearRhsFunc();
        }
    }
    
    class LinearRhsFunc extends RhsFunc
    {
        double vd;
        
        String getName() {
            return "linear";
        }
        
        void setup() {
            this.vd = DiffEqFrame.this.rhsValues[0].getValue();
        }
        
        double calculate(final double n, final double[] array) {
            return this.vd * n;
        }
        
        RhsFunc createNext() {
            return new ExponentialRhsFunc();
        }
        
        String getDescription() {
            return "hx";
        }
        
        void newFunc() {
            DiffEqFrame.this.rhsValues[0].setup("h", 0.1, 0);
        }
    }
    
    class ExponentialRhsFunc extends RhsFunc
    {
        double vg;
        double vh;
        
        String getName() {
            return "exponential";
        }
        
        void setup() {
            this.vg = DiffEqFrame.this.rhsValues[0].getValue();
            this.vh = DiffEqFrame.this.rhsValues[1].getValue();
        }
        
        double calculate(final double n, final double[] array) {
            return Math.exp(n * this.vg) * this.vh;
        }
        
        RhsFunc createNext() {
            return new CustomRhsFunc();
        }
        
        String getDescription() {
            return "h exp(gx)";
        }
        
        void newFunc() {
            DiffEqFrame.this.rhsValues[0].setup("g", 0.035, 0);
            DiffEqFrame.this.rhsValues[1].setup("h", 0.33, 0);
        }
    }
    
    class CustomRhsFunc extends RhsFunc
    {
        String getName() {
            return "custom function";
        }
        
        boolean isCustom() {
            return true;
        }
        
        RhsFunc createNext() {
            return new CustomYRhsFunc();
        }
        
        double calculate(final double n, final double[] array) {
            int n2 = (int)((n - DiffEqFrame.this.xRangeStart) / DiffEqFrame.this.xRangeWidth * DiffEqFrame.this.sampleCount);
            if (n2 < 0) {
                n2 = 0;
            }
            if (n2 >= DiffEqFrame.this.sampleCount) {
                n2 = DiffEqFrame.this.sampleCount - 1;
            }
            return DiffEqFrame.this.forceFunc[n2];
        }
        
        String getDescription() {
            return "f(x)";
        }
    }
    
    class CustomYRhsFunc extends RhsFunc
    {
        String getName() {
            return "custom function * y";
        }
        
        boolean isCustom() {
            return true;
        }
        
        RhsFunc createNext() {
            return new CustomYPRhsFunc();
        }
        
        double calculate(final double n, final double[] array) {
            int n2 = (int)((n - DiffEqFrame.this.xRangeStart) / DiffEqFrame.this.xRangeWidth * DiffEqFrame.this.sampleCount);
            if (n2 < 0) {
                n2 = 0;
            }
            if (n2 >= DiffEqFrame.this.sampleCount) {
                n2 = DiffEqFrame.this.sampleCount - 1;
            }
            return (array == null) ? DiffEqFrame.this.forceFunc[n2] : (DiffEqFrame.this.forceFunc[n2] * array[0]);
        }
        
        String getDescription() {
            return "f(x)y";
        }
    }
    
    class CustomYPRhsFunc extends RhsFunc
    {
        String getName() {
            return "custom function * y'";
        }
        
        boolean isCustom() {
            return true;
        }
        
        RhsFunc createNext() {
            return null;
        }
        
        double calculate(final double n, final double[] array) {
            int n2 = (int)((n - DiffEqFrame.this.xRangeStart) / DiffEqFrame.this.xRangeWidth * DiffEqFrame.this.sampleCount);
            if (n2 < 0) {
                n2 = 0;
            }
            if (n2 >= DiffEqFrame.this.sampleCount) {
                n2 = DiffEqFrame.this.sampleCount - 1;
            }
            return (array == null) ? DiffEqFrame.this.forceFunc[n2] : (DiffEqFrame.this.forceFunc[n2] * array[1]);
        }
        
        String getDescription() {
            return "f(x)y'";
        }
    }
    
    class ValueEditCanvas extends Canvas implements MouseListener, MouseMotionListener
    {
        boolean selectedNumber;
        boolean selectedSign;
        boolean dragging;
        int dragX;
        int dragY;
        int flags;
        int labelWidth;
        int signWidth;
        int storedChange;
        double value;
        double oldValue;
        double max;
        String label;
        static final int intscale = 4;
        
        ValueEditCanvas() {
            this.addMouseMotionListener(this);
            this.addMouseListener(this);
            this.value = 1.0;
            this.label = "";
        }
        
        void setLabel(final String s) {
            this.label = s + " = ";
            this.flags = 0;
            this.max = 1.0E80;
            this.show();
            this.repaint();
        }
        
        void setMax(final double max) {
            this.max = max;
        }
        
        void setup(final String label, final double value, final int flags) {
            this.setLabel(label);
            this.setFlags(flags);
            this.setValue(value);
        }
        
        double getValue() {
            return this.value;
        }
        
        void setValue(final double value) {
            if (this.dragging) {
                return;
            }
            this.value = value;
            this.round();
            this.repaint();
        }
        
        void round() {
            if (this.isInteger() || this.isHalfInteger()) {
                this.value = (int)this.value;
                if (this.isHalfInteger()) {
                    if (this.value < 0.0) {
                        this.value -= 0.5;
                    }
                    else {
                        this.value += 0.5;
                    }
                }
            }
        }
        
        boolean isNonNegative() {
            return (this.flags & 0x1) != 0x0;
        }
        
        boolean isInteger() {
            return (this.flags & 0x2) != 0x0;
        }
        
        boolean isHalfInteger() {
            return (this.flags & 0x10) != 0x0;
        }
        
        boolean isNonZero() {
            return (this.flags & 0x4) != 0x0;
        }
        
        boolean isConst() {
            return (this.flags & 0x8) != 0x0;
        }
        
        void setFlags(final int flags) {
            this.flags = flags;
            if (this.isNonNegative() && this.value < 0.0) {
                this.value = -this.value;
            }
            if (this.isNonZero() && this.value == 0.0) {
                this.value = 1.0;
            }
            this.repaint();
        }
        
        public Dimension getPreferredSize() {
            return new Dimension(200, 20);
        }
        
        public void paint(final Graphics graphics) {
            final FontMetrics fontMetrics = graphics.getFontMetrics();
            this.labelWidth = fontMetrics.stringWidth(this.label);
            this.signWidth = fontMetrics.stringWidth("+ ");
            graphics.drawString(this.label, 0, 15);
            graphics.setColor(this.selectedSign ? Color.red : Color.blue);
            graphics.drawString((this.value > 0.0) ? "+" : ((this.value == 0.0) ? "0" : "-"), this.labelWidth, 15);
            if (this.value != 0.0) {
                graphics.setColor((this.selectedNumber || this.dragging) ? Color.red : Color.blue);
                final double abs = Math.abs(this.value);
                String s = DiffEqFrame.this.nf.format(abs);
                if (s.equals("0")) {
                    final String string = Double.toString(abs);
                    s = string.substring(0, 5) + string.substring(string.indexOf(69));
                }
                graphics.drawString(s, this.labelWidth + this.signWidth, 15);
            }
        }
        
        public void mouseDragged(final MouseEvent mouseEvent) {
            if (this.isConst()) {
                return;
            }
            int n = mouseEvent.getX() - this.dragX;
            if (this.value == 0.0) {
                this.value = ((n > 0) ? 0.01 : -0.01);
                if (this.isNonNegative() && n < 0) {
                    this.value = 0.0;
                }
            }
            if (this.isInteger() || this.isHalfInteger()) {
                final int n2 = n + this.storedChange;
                this.storedChange = n2 % 4;
                this.value += n2 / 4;
                if (this.isNonNegative() && this.value < 0.0) {
                    this.value = 0.0;
                }
                if (this.isNonZero() && this.value == 0.0) {
                    this.value = 1.0;
                }
            }
            else {
                if (this.value < 0.0) {
                    n = -n;
                }
                this.value *= Math.exp(n * 0.05);
            }
            if (this.value > this.max) {
                this.value = this.max;
            }
            if (Math.abs(this.value) < 1.0E-5 && !this.isNonZero()) {
                this.value = 0.0;
            }
            this.round();
            if (this.value != 0.0) {
                this.oldValue = this.value;
            }
            DiffEqFrame.this.valueChanged(this);
            this.dragX = mouseEvent.getX();
            this.dragY = mouseEvent.getY();
            this.dragging = true;
            this.repaint();
        }
        
        public void mouseMoved(final MouseEvent mouseEvent) {
            if (this.isConst()) {
                return;
            }
            if (mouseEvent.getX() < this.labelWidth + this.signWidth) {
                if (!this.selectedSign) {
                    this.selectedSign = true;
                    this.selectedNumber = false;
                    this.repaint();
                }
            }
            else if (!this.selectedNumber) {
                this.selectedNumber = true;
                this.selectedSign = false;
                this.repaint();
            }
        }
        
        public void mouseClicked(final MouseEvent mouseEvent) {
            if (this.selectedSign) {
                if (mouseEvent.getClickCount() == 2 && !this.isNonZero()) {
                    this.oldValue = this.value;
                    this.value = 0.0;
                }
                else if (this.value == 0.0) {
                    this.value = this.oldValue;
                }
                else if (!this.isNonNegative()) {
                    this.value = -this.value;
                }
                DiffEqFrame.this.valueChanged(this);
                this.repaint();
            }
        }
        
        public void mouseEntered(final MouseEvent mouseEvent) {
            if (this.isConst()) {
                return;
            }
            if (mouseEvent.getX() < this.labelWidth + this.signWidth) {
                this.selectedSign = true;
            }
            else {
                this.selectedNumber = true;
            }
            this.repaint();
        }
        
        public void mouseExited(final MouseEvent mouseEvent) {
            if (this.isConst()) {
                return;
            }
            final boolean b = false;
            this.selectedSign = b;
            this.selectedNumber = b;
            this.repaint();
        }
        
        public void mousePressed(final MouseEvent mouseEvent) {
            this.dragX = mouseEvent.getX();
            this.dragY = mouseEvent.getY();
            this.storedChange = 0;
        }
        
        public void mouseReleased(final MouseEvent mouseEvent) {
            if (this.isConst()) {
                return;
            }
            this.dragging = false;
            this.repaint();
        }
    }
}
