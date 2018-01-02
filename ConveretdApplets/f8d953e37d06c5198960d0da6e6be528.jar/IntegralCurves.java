import java.awt.Graphics;
import edu.hws.jcm.data.ParseError;
import java.util.StringTokenizer;
import edu.hws.jcm.draw.DrawTemp;
import edu.hws.jcm.awt.JCMError;
import java.awt.event.ActionEvent;
import java.awt.Container;
import java.awt.LayoutManager;
import java.awt.BorderLayout;
import java.awt.Panel;
import edu.hws.jcm.awt.Controller;
import edu.hws.jcm.awt.InputObject;
import edu.hws.jcm.awt.Computable;
import java.awt.Label;
import java.awt.event.ActionListener;
import java.awt.Component;
import edu.hws.jcm.awt.JCMPanel;
import java.awt.event.MouseListener;
import edu.hws.jcm.draw.CoordinateRect;
import java.awt.event.MouseEvent;
import java.awt.event.MouseAdapter;
import edu.hws.jcm.draw.Drawable;
import edu.hws.jcm.functions.WrapperFunction;
import edu.hws.jcm.data.Expression;
import edu.hws.jcm.data.SimpleFunction;
import java.util.Hashtable;
import edu.hws.jcm.data.MathObject;
import java.awt.Color;
import java.awt.Button;
import java.awt.Choice;
import edu.hws.jcm.awt.VariableInput;
import java.util.Vector;
import edu.hws.jcm.awt.Animator;
import edu.hws.jcm.draw.VectorField;
import edu.hws.jcm.awt.ExpressionInput;
import edu.hws.jcm.data.Function;
import edu.hws.jcm.data.Variable;

// 
// Decompiled by Procyon v0.5.30
// 

public class IntegralCurves extends GenericGraphApplet
{
    private Variable yVar;
    private Function xFunc;
    private Function yFunc;
    private ExpressionInput functionInput2;
    private VectorField field;
    private Animator animator;
    private Vector curves;
    private VariableInput deltaT;
    double dt;
    private VariableInput xStart;
    private VariableInput yStart;
    private Choice methodChoice;
    private Button startCurveButton;
    private Button clearButton;
    private Color curveColor;
    private Draw curveDrawer;
    private double[] nextPoint;
    private double[] params;
    private static final int RK4 = 0;
    private static final int RK2 = 1;
    private static final int EULER = 2;
    
    public IntegralCurves() {
        this.curves = new Vector();
        this.dt = 0.1;
        this.curveDrawer = new Draw();
        this.nextPoint = new double[2];
        this.params = new double[2];
    }
    
    protected void setUpParser() {
        this.yVar = new Variable(this.getParameter("Variable2", "y"));
        super.parser.add(this.yVar);
        super.setUpParser();
        (super.parameterDefaults = new Hashtable()).put("FunctionLabel", " f1(" + super.xVar.getName() + "," + this.yVar.getName() + ") = ");
        super.parameterDefaults.put("FunctionLabel2", " f2(" + super.xVar.getName() + "," + this.yVar.getName() + ") = ");
        super.parameterDefaults.put("Function", " " + this.yVar.getName() + " - 0.1*" + super.xVar.getName());
        super.parameterDefaults.put("Function2", " - " + super.xVar.getName() + " - 0.1*" + this.yVar.getName());
        super.defaultFrameSize = new int[] { 580, 440 };
    }
    
    protected void setUpCanvas() {
        super.setUpCanvas();
        if (super.functionInput != null) {
            this.xFunc = super.functionInput.getFunction(new Variable[] { super.xVar, this.yVar });
            this.yFunc = this.functionInput2.getFunction(new Variable[] { super.xVar, this.yVar });
        }
        else {
            final String parameter = this.getParameter("Function");
            final String parameter2 = this.getParameter("Function2");
            this.xFunc = new WrapperFunction(new SimpleFunction(super.parser.parse(parameter), new Variable[] { super.xVar, this.yVar }));
            this.yFunc = new WrapperFunction(new SimpleFunction(super.parser.parse(parameter2), new Variable[] { super.xVar, this.yVar }));
        }
        final String upperCase = (this.getParameter("VectorStyle", "") + "A").toUpperCase();
        int n = 0;
        switch (upperCase.charAt(0)) {
            case 'A': {
                n = 0;
                break;
            }
            case 'L': {
                n = 1;
                break;
            }
            case 'S': {
                n = 4;
                break;
            }
        }
        this.field = new VectorField(this.xFunc, this.yFunc, n);
        final Color colorParam = this.getColorParam("VectorColor");
        if (colorParam != null) {
            this.field.setColor(colorParam);
        }
        int pixelSpacing = (n == 1) ? 20 : 30;
        final double[] numericParam = this.getNumericParam("VectorSpacing");
        if (numericParam != null && numericParam.length > 0 && numericParam[0] >= 1.0) {
            pixelSpacing = (int)Math.round(numericParam[0]);
        }
        this.field.setPixelSpacing(pixelSpacing);
        super.canvas.add(this.field);
        this.curveColor = this.getColorParam("CurveColor", Color.magenta);
        if ("yes".equalsIgnoreCase(this.getParameter("MouseStartsCurves", "yes")) && "yes".equalsIgnoreCase(this.getParameter("DoCurves", "yes"))) {
            super.canvas.addMouseListener(new MouseAdapter() {
                public void mousePressed(final MouseEvent mouseEvent) {
                    final CoordinateRect coordinateRect = IntegralCurves.this.canvas.getCoordinateRect();
                    final double pixelToX = coordinateRect.pixelToX(mouseEvent.getX());
                    final double pixelToY = coordinateRect.pixelToY(mouseEvent.getY());
                    if (IntegralCurves.this.xStart != null) {
                        IntegralCurves.this.xStart.setVal(pixelToX);
                    }
                    if (IntegralCurves.this.yStart != null) {
                        IntegralCurves.this.yStart.setVal(pixelToY);
                    }
                    IntegralCurves.this.startCurve(pixelToX, pixelToY);
                }
            });
        }
    }
    
    protected void setUpBottomPanel() {
        final double[] numericParam = this.getNumericParam("DeltaT");
        if (numericParam != null && numericParam.length != 0 && numericParam[0] > 0.0) {
            this.dt = numericParam[0];
        }
        final boolean equalsIgnoreCase = "yes".equalsIgnoreCase(this.getParameter("DoCurves", "yes"));
        final boolean equalsIgnoreCase2 = "yes".equalsIgnoreCase(this.getParameter("UseFunctionInput", "yes"));
        if (!equalsIgnoreCase && !equalsIgnoreCase2) {
            return;
        }
        (super.inputPanel = new JCMPanel()).setBackground(this.getColorParam("PanelBackground", Color.lightGray));
        super.mainPanel.add(super.inputPanel, "South");
        Container container = null;
        Container container2 = null;
        if (equalsIgnoreCase2) {
            if ("yes".equalsIgnoreCase(this.getParameter("UseComputeButton", "yes"))) {
                (super.computeButton = new Button(this.getParameter("ComputeButtonName", "New Functions"))).addActionListener(this);
            }
            super.functionInput = new ExpressionInput(this.getParameter("Function"), super.parser);
            container = new JCMPanel();
            container.add(super.functionInput, "Center");
            container.add(new Label(this.getParameter("FunctionLabel")), "West");
            super.functionInput.setOnUserAction(super.mainController);
            this.functionInput2 = new ExpressionInput(this.getParameter("Function2"), super.parser);
            container2 = new JCMPanel();
            container2.add(this.functionInput2, "Center");
            container2.add(new Label(this.getParameter("FunctionLabel2")), "West");
            this.functionInput2.setOnUserAction(super.mainController);
        }
        if (!equalsIgnoreCase) {
            final JCMPanel jcmPanel = new JCMPanel(2, 1, 3);
            jcmPanel.add(container);
            jcmPanel.add(container2);
            super.inputPanel.add(jcmPanel, "Center");
            if (super.computeButton != null) {
                super.inputPanel.add(super.computeButton, "East");
            }
            return;
        }
        (this.animator = new Animator(8)).setStopButtonName("Stop Curves");
        this.animator.setOnChange(new Computable() {
            public void compute() {
                IntegralCurves.this.extendCurves();
            }
        });
        super.mainController.add(new InputObject() {
            public void checkInput() {
                IntegralCurves.this.curves.setSize(0);
                IntegralCurves.this.animator.stop();
            }
            
            public void notifyControllerOnChange(final Controller controller) {
            }
        });
        (this.clearButton = new Button("Clear")).addActionListener(this);
        Container container3 = null;
        if ("yes".equalsIgnoreCase(this.getParameter("UseStartInputs", "yes"))) {
            (this.xStart = new VariableInput()).addActionListener(this);
            (this.yStart = new VariableInput()).addActionListener(this);
            container3 = new Panel();
            (this.startCurveButton = new Button("Start curve at:")).addActionListener(this);
            container3.add(this.startCurveButton);
            container3.add(new Label(super.xVar.getName() + " ="));
            container3.add(this.xStart);
            container3.add(new Label(this.yVar.getName() + " ="));
            container3.add(this.yStart);
        }
        final boolean equalsIgnoreCase3 = "yes".equalsIgnoreCase(this.getParameter("UseMethodChoice", "yes"));
        final boolean equalsIgnoreCase4 = "yes".equalsIgnoreCase(this.getParameter("UseDeltaInput", "yes"));
        if (equalsIgnoreCase3 || equalsIgnoreCase4) {
            final Panel panel = new Panel();
            if (equalsIgnoreCase4) {
                panel.add(new Label("dt ="));
                panel.add(this.deltaT = new VariableInput(null, "" + this.dt));
            }
            if (equalsIgnoreCase3) {
                panel.add(new Label("Method:"));
                (this.methodChoice = new Choice()).add("Runge-Kutta 4");
                this.methodChoice.add("Runge-Kutta 2");
                this.methodChoice.add("Euler");
                panel.add(this.methodChoice);
            }
            panel.add(this.animator);
            panel.add(this.clearButton);
            if (container3 == null) {
                container3 = panel;
            }
            else {
                final Panel panel2 = new Panel();
                panel2.setLayout(new BorderLayout());
                panel2.add(panel, "North");
                panel2.add(container3, "Center");
                container3 = panel2;
            }
        }
        else {
            if (container3 == null) {
                container3 = new Panel();
            }
            container3.add(this.animator);
            container3.add(this.clearButton);
        }
        super.inputPanel.add(container3, "Center");
        if (container == null) {
            return;
        }
        JCMPanel jcmPanel2 = new JCMPanel(1, 2);
        jcmPanel2.add(container);
        jcmPanel2.add(container2);
        if (super.computeButton != null) {
            final JCMPanel jcmPanel3 = new JCMPanel();
            jcmPanel3.add(jcmPanel2, "Center");
            jcmPanel3.add(super.computeButton, "East");
            jcmPanel2 = jcmPanel3;
        }
        super.inputPanel.add(jcmPanel2, "North");
    }
    
    public void actionPerformed(final ActionEvent actionEvent) {
        final Object source = actionEvent.getSource();
        if (source == this.clearButton) {
            super.canvas.clearErrorMessage();
            this.curves.setSize(0);
            this.animator.stop();
            super.canvas.compute();
        }
        else if (source == this.xStart || source == this.yStart || source == this.startCurveButton) {
            super.canvas.clearErrorMessage();
            try {
                this.xStart.checkInput();
                final double val = this.xStart.getVal();
                this.yStart.checkInput();
                this.startCurve(val, this.yStart.getVal());
                if (this.deltaT != null) {
                    this.deltaT.checkInput();
                    this.dt = this.deltaT.getVal();
                    if (this.dt <= 0.0) {
                        this.deltaT.requestFocus();
                        throw new JCMError("dt must be positive", this.deltaT);
                    }
                }
            }
            catch (JCMError jcmError) {
                this.curves.setSize(0);
                this.animator.stop();
                super.canvas.setErrorMessage(null, "Illegal Data For Curve.  " + jcmError.getMessage());
            }
        }
        else {
            super.actionPerformed(actionEvent);
        }
    }
    
    public void startCurve(final double x, final double y) {
        synchronized (this.curves) {
            if (this.deltaT != null) {
                try {
                    this.deltaT.checkInput();
                    this.dt = this.deltaT.getVal();
                    if (this.dt <= 0.0) {
                        this.deltaT.requestFocus();
                        throw new JCMError("dt must be positive", this.deltaT);
                    }
                }
                catch (JCMError jcmError) {
                    this.curves.setSize(0);
                    this.animator.stop();
                    super.canvas.setErrorMessage(null, "Illegal Data For Curve.  " + jcmError.getMessage());
                    return;
                }
            }
            final Curve curve = new Curve();
            curve.dt = this.dt;
            curve.method = ((this.methodChoice == null) ? 0 : this.methodChoice.getSelectedIndex());
            curve.x = x;
            curve.y = y;
            this.curves.addElement(curve);
            this.animator.start();
        }
    }
    
    public void extendCurves() {
        synchronized (this.curves) {
            if (super.canvas == null || super.canvas.getCoordinateRect() == null) {
                return;
            }
            while (super.canvas.getCoordinateRect().getWidth() <= 0) {
                try {
                    Thread.sleep(200L);
                }
                catch (InterruptedException ex) {}
            }
            final int size = this.curves.size();
            for (int i = 0; i < size; ++i) {
                final Curve curve = this.curves.elementAt(i);
                curve.lastX = curve.x;
                curve.lastY = curve.y;
                this.nextPoint(curve.x, curve.y, curve.dt, curve.method);
                curve.x = this.nextPoint[0];
                curve.y = this.nextPoint[1];
            }
            final CoordinateRect coordinateRect = super.canvas.getCoordinateRect();
            final double n = 100000.0 * coordinateRect.getPixelWidth();
            final double n2 = 100000.0 * coordinateRect.getPixelHeight();
            for (int j = size - 1; j >= 0; --j) {
                final Curve curve2 = this.curves.elementAt(j);
                if (Double.isNaN(curve2.x) || Double.isNaN(curve2.y) || Math.abs(curve2.x) > n || Math.abs(curve2.y) > n) {
                    this.curves.removeElementAt(j);
                }
            }
            if (this.curves.size() > 0) {
                super.canvas.drawTemp(this.curveDrawer);
            }
            else {
                this.animator.stop();
            }
        }
    }
    
    private void nextPoint(final double n, final double n2, final double n3, final int n4) {
        switch (n4) {
            case 2: {
                this.nextEuler(n, n2, n3);
                break;
            }
            case 1: {
                this.nextRK2(n, n2, n3);
                break;
            }
            case 0: {
                this.nextRK4(n, n2, n3);
                break;
            }
        }
    }
    
    private void nextEuler(final double n, final double n2, final double n3) {
        this.params[0] = n;
        this.params[1] = n2;
        final double val = this.xFunc.getVal(this.params);
        final double val2 = this.yFunc.getVal(this.params);
        this.nextPoint[0] = n + n3 * val;
        this.nextPoint[1] = n2 + n3 * val2;
    }
    
    private void nextRK2(final double n, final double n2, final double n3) {
        this.params[0] = n;
        this.params[1] = n2;
        final double val = this.xFunc.getVal(this.params);
        final double val2 = this.yFunc.getVal(this.params);
        final double n4 = n + n3 * val;
        final double n5 = n2 + n3 * val2;
        this.params[0] = n4;
        this.params[1] = n5;
        final double val3 = this.xFunc.getVal(this.params);
        final double val4 = this.yFunc.getVal(this.params);
        this.nextPoint[0] = n + 0.5 * n3 * (val + val3);
        this.nextPoint[1] = n2 + 0.5 * n3 * (val2 + val4);
    }
    
    private void nextRK4(final double n, final double n2, final double n3) {
        this.params[0] = n;
        this.params[1] = n2;
        final double val = this.xFunc.getVal(this.params);
        final double val2 = this.yFunc.getVal(this.params);
        final double n4 = n + 0.5 * n3 * val;
        final double n5 = n2 + 0.5 * n3 * val2;
        this.params[0] = n4;
        this.params[1] = n5;
        final double val3 = this.xFunc.getVal(this.params);
        final double val4 = this.yFunc.getVal(this.params);
        final double n6 = n + 0.5 * n3 * val3;
        final double n7 = n2 + 0.5 * n3 * val4;
        this.params[0] = n6;
        this.params[1] = n7;
        final double val5 = this.xFunc.getVal(this.params);
        final double val6 = this.yFunc.getVal(this.params);
        final double n8 = n + n3 * val5;
        final double n9 = n2 + n3 * val6;
        this.params[0] = n8;
        this.params[1] = n9;
        final double val7 = this.xFunc.getVal(this.params);
        final double val8 = this.yFunc.getVal(this.params);
        this.nextPoint[0] = n + n3 / 6.0 * (val + 2.0 * val3 + 2.0 * val5 + val7);
        this.nextPoint[1] = n2 + n3 / 6.0 * (val2 + 2.0 * val4 + 2.0 * val6 + val8);
    }
    
    protected void doLoadExample(String substring) {
        if (this.animator != null) {
            this.curves.setSize(0);
            this.animator.stop();
        }
        final int index = substring.indexOf(";");
        if (index == -1) {
            return;
        }
        String text = substring.substring(index + 1);
        substring = substring.substring(0, index);
        final int index2 = text.indexOf(";");
        final double[] limits = { -5.0, 5.0, -5.0, 5.0 };
        StringTokenizer stringTokenizer = null;
        if (index2 > 0) {
            final String substring2 = text.substring(index2 + 1);
            text = text.substring(0, index2);
            stringTokenizer = new StringTokenizer(substring2, " ,");
            if (stringTokenizer.countTokens() >= 4) {
                for (int i = 0; i < 4; ++i) {
                    try {
                        limits[i] = new Double(stringTokenizer.nextToken());
                    }
                    catch (NumberFormatException ex) {}
                }
            }
            if (stringTokenizer.hasMoreTokens()) {
                double doubleValue = Double.NaN;
                try {
                    doubleValue = new Double(stringTokenizer.nextToken());
                }
                catch (NumberFormatException ex2) {}
                if (Double.isNaN(doubleValue) || doubleValue <= 0.0 || doubleValue > 100.0) {
                    doubleValue = 0.1;
                }
                if (this.deltaT != null) {
                    this.deltaT.setVal(doubleValue);
                }
                else {
                    this.dt = doubleValue;
                }
            }
        }
        if (super.functionInput != null) {
            super.functionInput.setText(substring);
            this.functionInput2.setText(text);
        }
        else {
            try {
                ((WrapperFunction)this.xFunc).setFunction(new SimpleFunction(super.parser.parse(substring), super.xVar));
                ((WrapperFunction)this.yFunc).setFunction(new SimpleFunction(super.parser.parse(text), super.xVar));
            }
            catch (ParseError parseError) {}
        }
        final CoordinateRect coordinateRect = super.canvas.getCoordinateRect(0);
        coordinateRect.setLimits(limits);
        coordinateRect.setRestoreBuffer();
        super.mainController.compute();
        if (this.animator != null && stringTokenizer != null) {
            final int n = 2 * (stringTokenizer.countTokens() / 2);
            if (n > 0) {
                synchronized (this.curves) {
                    for (int j = 0; j < n; ++j) {
                        try {
                            this.startCurve(new Double(stringTokenizer.nextToken()), new Double(stringTokenizer.nextToken()));
                        }
                        catch (Exception ex3) {}
                    }
                    if (this.curves.size() > 0) {
                        try {
                            Thread.sleep(500L);
                        }
                        catch (InterruptedException ex4) {}
                    }
                }
            }
        }
    }
    
    public void stop() {
        if (this.animator != null) {
            this.curves.setSize(0);
            this.animator.stop();
        }
        super.stop();
    }
    
    private class Curve
    {
        double dt;
        int method;
        double x;
        double y;
        double lastX;
        double lastY;
        
        private Curve() {
            this.lastX = Double.NaN;
        }
    }
    
    private class Draw implements DrawTemp
    {
        public void draw(final Graphics graphics, final CoordinateRect coordinateRect) {
            final int size = IntegralCurves.this.curves.size();
            graphics.setColor(IntegralCurves.this.curveColor);
            for (int i = 0; i < size; ++i) {
                final Curve curve = IntegralCurves.this.curves.elementAt(i);
                if (!Double.isNaN(curve.x) && !Double.isNaN(curve.y) && !Double.isNaN(curve.lastX) && !Double.isNaN(curve.lastY)) {
                    graphics.drawLine(coordinateRect.xToPixel(curve.lastX), coordinateRect.yToPixel(curve.lastY), coordinateRect.xToPixel(curve.x), coordinateRect.yToPixel(curve.y));
                }
            }
        }
    }
}
