import edu.hws.jcm.data.ParseError;
import edu.hws.jcm.data.Expression;
import edu.hws.jcm.data.SimpleFunction;
import java.util.StringTokenizer;
import edu.hws.jcm.data.Constant;
import java.awt.event.AdjustmentEvent;
import java.awt.event.ActionEvent;
import edu.hws.jcm.draw.CoordinateRect;
import edu.hws.jcm.awt.ErrorReporter;
import edu.hws.jcm.awt.Tieable;
import edu.hws.jcm.awt.Tie;
import java.awt.LayoutManager;
import java.awt.BorderLayout;
import java.awt.Panel;
import java.awt.Component;
import java.awt.Label;
import edu.hws.jcm.draw.Axes;
import java.awt.Color;
import edu.hws.jcm.draw.Graph1D;
import edu.hws.jcm.data.Value;
import edu.hws.jcm.draw.Drawable;
import edu.hws.jcm.data.MathObject;
import edu.hws.jcm.draw.LimitControlPanel;
import java.util.Hashtable;
import edu.hws.jcm.draw.DrawGeometric;
import edu.hws.jcm.awt.DisplayLabel;
import edu.hws.jcm.draw.DraggablePoint;
import edu.hws.jcm.awt.VariableSlider;
import edu.hws.jcm.awt.VariableInput;
import edu.hws.jcm.data.ValueMath;
import edu.hws.jcm.functions.WrapperFunction;
import edu.hws.jcm.data.Function;
import edu.hws.jcm.functions.ExpressionFunction;
import edu.hws.jcm.awt.ExpressionInput;
import edu.hws.jcm.data.Variable;
import edu.hws.jcm.data.Parser;
import edu.hws.jcm.awt.Controller;
import java.awt.Button;
import edu.hws.jcm.draw.DisplayCanvas;
import edu.hws.jcm.awt.JCMPanel;
import java.awt.event.AdjustmentListener;
import java.awt.event.ActionListener;

// 
// Decompiled by Procyon v0.5.30
// 

public class Newton extends GenericGraphApplet implements ActionListener, AdjustmentListener
{
    int MAXITERATIONS;
    int DOTSIZE;
    int BIG_DOTSIZE;
    int DOTSHAPE;
    int BIG_DOTSHAPE;
    int PRECISION;
    JCMPanel mainPanel;
    DisplayCanvas canvas;
    Button copyButton;
    Controller controller;
    Parser internalParser;
    Parser parser;
    Variable xVar;
    ExpressionInput funcInput;
    ExpressionFunction nextX;
    ExpressionFunction identity;
    Function func;
    Function deriv;
    WrapperFunction funcW;
    WrapperFunction derivW;
    int iterations;
    ValueMath[] est;
    VariableInput seedInput;
    VariableInput iterationsInput;
    VariableSlider seedSlider;
    VariableSlider iterationsSlider;
    DraggablePoint seed;
    DisplayLabel finalEstimateLabel;
    DrawGeometric[] startPoint;
    DrawGeometric[] endPoint;
    DrawGeometric[] tangentSeg;
    DrawGeometric[] vertSeg;
    
    public Newton() {
        this.MAXITERATIONS = 8;
        this.DOTSIZE = 3;
        this.BIG_DOTSIZE = 4;
        this.DOTSHAPE = 10;
        this.BIG_DOTSHAPE = 9;
        this.PRECISION = 15;
        this.mainPanel = new JCMPanel();
        this.canvas = new DisplayCanvas();
        this.internalParser = new Parser();
        this.parser = new Parser();
        this.xVar = new Variable("x");
        this.iterations = 0;
        this.est = new ValueMath[this.MAXITERATIONS + 1];
        this.startPoint = new DrawGeometric[this.MAXITERATIONS + 1];
        this.endPoint = new DrawGeometric[this.MAXITERATIONS + 1];
        this.tangentSeg = new DrawGeometric[this.MAXITERATIONS + 1];
        this.vertSeg = new DrawGeometric[this.MAXITERATIONS + 1];
    }
    
    protected void setUpParameterDefaults() {
        (super.parameterDefaults = new Hashtable()).put("MaxIterations", "8");
        super.parameterDefaults.put("DotSize", "3");
        super.parameterDefaults.put("BigDotSize", "4");
        super.parameterDefaults.put("DotShape", "square");
        super.parameterDefaults.put("BigDotShape", "circle");
        super.parameterDefaults.put("Precision", "15");
        super.parameterDefaults.put("InitColor", "green");
        super.parameterDefaults.put("NextColor", "red");
        super.parameterDefaults.put("SeedColor", "magenta");
        super.parameterDefaults.put("LineColor", "cyan");
        super.parameterDefaults.put("LightLineColor", "lightGray");
        super.parameterDefaults.put("GraphColor", "black");
        super.parameterDefaults.put("FrameTitle", "Newton's Method");
        super.parameterDefaults.put("UseZoomButtons", "yes");
        super.parameterDefaults.put("UseRestoreButton", "yes");
        super.parameterDefaults.put("UseEqualizeButton", "yes");
        super.parameterDefaults.put("UseMouseZoom", "yes");
        super.parameterDefaults.put("UseComputeButton", "no");
        super.parameterDefaults.put("PanelTitle", "Newton's Method");
        super.parameterDefaults.put("Example1", "sin(x^2) - sin(x)^2; 1.5,0, -6 6,-3 3");
        super.parameterDefaults.put("UseLoadButton", "no");
        super.parameterDefaults.put("LoadFirstExample", "yes");
        if ("circle".equalsIgnoreCase(this.getParameter("DotShape", "circle"))) {
            this.DOTSHAPE = 10;
        }
        else {
            this.DOTSHAPE = 9;
        }
        if ("circle".equalsIgnoreCase(this.getParameter("BigDotShape", "circle"))) {
            this.BIG_DOTSHAPE = 10;
        }
        else {
            this.BIG_DOTSHAPE = 9;
        }
        this.DOTSIZE = (int)this.getNumericParam("DotSize")[0];
        this.BIG_DOTSIZE = (int)this.getNumericParam("BigDotSize")[0];
    }
    
    public void init() {
        this.setUpParameterDefaults();
        super.setUpExampleMenu();
        this.canvas.setHandleMouseZooms(true);
        final LimitControlPanel limitControlPanel = new LimitControlPanel();
        limitControlPanel.addButtons(0x1 | 0x2 | 0x4 | 0x8 | 0x10 | 0x20);
        limitControlPanel.addCoords(this.canvas);
        this.parser.addOptions(64);
        this.parser.addOptions(32);
        this.parser.addOptions(1);
        this.internalParser.addOptions(64);
        this.internalParser.addOptions(32);
        this.internalParser.addOptions(1);
        final Variable max = new Variable("maxI", this.MAXITERATIONS);
        final Variable min = new Variable("minI", 0.0);
        this.parser.add(this.xVar);
        this.internalParser.add(this.xVar);
        this.seedInput = new VariableInput();
        final CoordinateRect coordinateRect = this.canvas.getCoordinateRect(0);
        this.seedSlider = new VariableSlider(coordinateRect.getValueObject(0), coordinateRect.getValueObject(1));
        this.seedInput.addActionListener(this);
        this.seedInput.setForeground(this.getColorParam("SeedColor"));
        this.seedInput.setVal(1.0);
        this.seedSlider.setVal(1.0);
        this.seedSlider.addAdjustmentListener(this);
        if ("circle".equalsIgnoreCase(this.getParameter("BIG_DOTSHAPE", "circle"))) {
            this.seed = new DraggablePoint(0);
        }
        else {
            this.seed = new DraggablePoint(1);
        }
        this.seed.setColor(this.getColorParam("SeedColor"));
        this.seed.clampY(0.0);
        this.canvas.add(this.seed);
        (this.iterationsInput = new VariableInput()).setInputStyle(2);
        this.iterationsInput.addActionListener(this);
        (this.iterationsSlider = new VariableSlider()).setIntegerValued(true);
        this.iterationsSlider.setMax(max);
        this.iterationsSlider.setMin(min);
        this.iterationsSlider.addAdjustmentListener(this);
        this.funcInput = new ExpressionInput("sin(x^2) - sin(x)^2", this.parser);
        this.func = this.funcInput.getFunction(this.xVar);
        this.deriv = this.func.derivative(1);
        (this.funcW = new WrapperFunction(this.func)).setName("f");
        this.internalParser.add(this.funcW);
        (this.derivW = new WrapperFunction(this.deriv)).setName("D");
        this.internalParser.add(this.derivW);
        this.nextX = new ExpressionFunction("N", new String[] { "x" }, "x - (f(x)/D(x))", this.internalParser);
        this.identity = new ExpressionFunction("id", new String[] { "x" }, "x", this.internalParser);
        this.est[0] = new ValueMath(this.identity, this.seedInput.getVariable());
        this.startPoint[0] = new DrawGeometric();
        this.endPoint[0] = new DrawGeometric();
        this.tangentSeg[0] = new DrawGeometric();
        this.vertSeg[0] = new DrawGeometric();
        for (int i = 1; i <= this.MAXITERATIONS; ++i) {
            this.est[i] = new ValueMath(this.nextX, this.est[i - 1]);
            this.startPoint[i] = this.curPoint(this.est[i - 1]);
            this.endPoint[i] = this.nextPoint(this.est[i - 1]);
            this.tangentSeg[i] = this.nextLine(this.est[i - 1]);
            this.vertSeg[i] = this.vertLine(this.est[i - 1]);
            this.canvas.add(this.startPoint[i]);
            this.canvas.add(this.endPoint[i]);
            this.canvas.add(this.tangentSeg[i]);
            this.canvas.add(this.vertSeg[i]);
        }
        (this.finalEstimateLabel = new DisplayLabel("#", new ValueMath(this.identity, this.est[this.iterations]))).setForeground(this.getColorParam("NextColor"));
        this.finalEstimateLabel.setNumSize(this.PRECISION);
        this.updateIterations(this.iterations);
        (this.copyButton = new Button("copy to initial approx.")).addActionListener(this);
        final Graph1D graph1D = new Graph1D(this.func);
        graph1D.setColor(this.getColorParam("GraphColor", Color.black));
        this.canvas.add(new Axes());
        this.canvas.add(graph1D);
        final JCMPanel jcmPanel = new JCMPanel(4, 3);
        jcmPanel.setInsetGap(3);
        jcmPanel.add(new Label("function:"));
        jcmPanel.add(this.funcInput);
        jcmPanel.add(new Panel());
        jcmPanel.add(new Label("initial approx: "));
        jcmPanel.add(this.seedInput);
        jcmPanel.add(this.seedSlider);
        jcmPanel.add(new Label("final approx: "));
        jcmPanel.add(this.finalEstimateLabel);
        jcmPanel.add(this.copyButton);
        jcmPanel.add(new Label("# of iterations: "));
        jcmPanel.add(this.iterationsInput);
        jcmPanel.add(this.iterationsSlider);
        this.mainPanel.setInsetGap(3);
        this.mainPanel.add(this.canvas, "Center");
        this.mainPanel.add(jcmPanel, "South");
        this.mainPanel.add(limitControlPanel, "East");
        this.mainPanel.add(super.exampleMenuPanel, "North");
        this.setLayout(new BorderLayout());
        this.add(this.mainPanel, "Center");
        this.setBackground(this.getColorParam("LightLineColor", Color.lightGray));
        this.controller = this.mainPanel.getController();
        this.seedSlider.setOnUserAction(this.controller);
        this.seedInput.setOnTextChange(this.controller);
        this.seed.setOnUserAction(this.controller);
        this.iterationsSlider.setOnUserAction(this.controller);
        this.iterationsInput.setOnTextChange(this.controller);
        final Tie tie = new Tie();
        tie.add(this.seedSlider);
        tie.add(this.seedInput);
        tie.add((Tieable)this.seed.getXVar());
        this.controller.add(tie);
        this.controller.add(new Tie(this.iterationsSlider, this.iterationsInput));
        this.controller.setErrorReporter(this.canvas);
        limitControlPanel.setErrorReporter(this.canvas);
        this.mainPanel.gatherInputs();
    }
    
    public void actionPerformed(final ActionEvent actionEvent) {
        final Object source = actionEvent.getSource();
        if (source == this.iterationsInput) {
            this.updateIterations((int)this.iterationsInput.getVal());
        }
        if (source == this.copyButton) {
            this.seedInput.setVal(new ValueMath(this.identity, this.est[this.iterations]).getVal());
            this.finalEstimateLabel.compute();
        }
    }
    
    public void adjustmentValueChanged(final AdjustmentEvent adjustmentEvent) {
        if (adjustmentEvent.getSource() == this.iterationsSlider) {
            this.updateIterations((int)this.iterationsSlider.getVal());
        }
    }
    
    DrawGeometric nextLine(final Value x1) {
        final DrawGeometric drawGeometric = new DrawGeometric();
        drawGeometric.setShape(0);
        drawGeometric.setX1(x1);
        drawGeometric.setY1(new ValueMath(this.func, x1));
        drawGeometric.setX2(new ValueMath(this.nextX, x1));
        drawGeometric.setY2(new Constant(0.0));
        drawGeometric.setColor(this.getColorParam("LineColor"));
        drawGeometric.setLineWidth(2);
        return drawGeometric;
    }
    
    DrawGeometric vertLine(final Value value) {
        final DrawGeometric drawGeometric = new DrawGeometric();
        drawGeometric.setShape(0);
        drawGeometric.setX1(value);
        drawGeometric.setY1(new ValueMath(this.func, value));
        drawGeometric.setX2(value);
        drawGeometric.setY2(new Constant(0.0));
        drawGeometric.setColor(this.getColorParam("LightLineColor", Color.lightGray));
        drawGeometric.setLineWidth(1);
        return drawGeometric;
    }
    
    DrawGeometric curPoint(final Value x1) {
        final DrawGeometric drawGeometric = new DrawGeometric();
        drawGeometric.setShape(this.DOTSHAPE);
        drawGeometric.setX1(x1);
        drawGeometric.setY1(new ValueMath(this.func, x1));
        drawGeometric.setH(this.DOTSIZE);
        drawGeometric.setV(this.DOTSIZE);
        drawGeometric.setColor(this.getColorParam("InitColor"));
        drawGeometric.setFillColor(this.getColorParam("InitColor"));
        return drawGeometric;
    }
    
    DrawGeometric nextPoint(final Value value) {
        final DrawGeometric drawGeometric = new DrawGeometric();
        drawGeometric.setShape(this.DOTSHAPE);
        drawGeometric.setX1(new ValueMath(this.nextX, value));
        drawGeometric.setY1(new Constant(0.0));
        drawGeometric.setH(this.DOTSIZE);
        drawGeometric.setV(this.DOTSIZE);
        drawGeometric.setColor(this.getColorParam("NextColor"));
        drawGeometric.setFillColor(this.getColorParam("NextColor"));
        return drawGeometric;
    }
    
    void makeLineVisible(final int n, final boolean b) {
        if (n > 0 && n <= this.MAXITERATIONS) {
            this.startPoint[n].setVisible(b);
            this.endPoint[n].setVisible(b);
            this.tangentSeg[n].setVisible(b);
            this.vertSeg[n].setVisible(b);
        }
    }
    
    void makeLineVisible(final int n) {
        this.makeLineVisible(n, true);
    }
    
    void updateIterations(final int iterations) {
        this.endPoint[this.iterations].setV(this.DOTSIZE);
        this.endPoint[this.iterations].setH(this.DOTSIZE);
        this.endPoint[this.iterations].setShape(this.DOTSHAPE);
        this.iterations = iterations;
        if (this.iterations > this.MAXITERATIONS) {
            this.iterations = this.MAXITERATIONS;
        }
        if (this.iterations < 0) {
            this.iterations = 0;
        }
        this.iterationsInput.setVal(this.iterations);
        this.iterationsSlider.setVal(this.iterations);
        this.finalEstimateLabel.setValues(new Value[] { this.est[this.iterations] });
        this.endPoint[this.iterations].setV(this.BIG_DOTSIZE);
        this.endPoint[this.iterations].setH(this.BIG_DOTSIZE);
        this.endPoint[this.iterations].setShape(this.BIG_DOTSHAPE);
        int i;
        for (i = 1; i <= this.iterations; ++i) {
            this.makeLineVisible(i, true);
        }
        while (i <= this.MAXITERATIONS) {
            this.makeLineVisible(i, false);
            ++i;
        }
        this.makeLineVisible(this.MAXITERATIONS + 1, false);
        this.makeLineVisible(0, false);
    }
    
    protected void doLoadExample(String substring) {
        final int index = substring.indexOf(";");
        final double[] limits = { -5.0, 5.0, -5.0, 5.0 };
        double doubleValue = 0.0;
        int n = 0;
        if (index > 0) {
            final String substring2 = substring.substring(index + 1);
            substring = substring.substring(0, index);
            final StringTokenizer stringTokenizer = new StringTokenizer(substring2, " ,");
            if (stringTokenizer.countTokens() >= 4) {
                for (int i = 0; i < 4; ++i) {
                    try {
                        limits[i] = new Double(stringTokenizer.nextToken());
                    }
                    catch (NumberFormatException ex) {}
                }
            }
            if (stringTokenizer.countTokens() >= 1) {
                try {
                    doubleValue = new Double(stringTokenizer.nextToken());
                }
                catch (NumberFormatException ex2) {}
            }
            if (stringTokenizer.countTokens() >= 1) {
                try {
                    n = (int)(double)new Double(stringTokenizer.nextToken());
                }
                catch (NumberFormatException ex3) {}
            }
        }
        if (this.funcInput != null) {
            this.funcInput.setText(substring);
        }
        else {
            try {
                ((WrapperFunction)this.func).setFunction(new SimpleFunction(this.parser.parse(substring), this.xVar));
            }
            catch (ParseError parseError) {}
        }
        final CoordinateRect coordinateRect = this.canvas.getCoordinateRect(0);
        coordinateRect.setLimits(limits);
        coordinateRect.setRestoreBuffer();
        this.controller.compute();
        this.seedInput.setVal(doubleValue);
        this.controller.compute();
        this.updateIterations(n);
        this.controller.compute();
    }
}
