// 
// Decompiled by Procyon v0.5.30
// 

package xfunctions;

import java.awt.Event;
import java.awt.Label;
import java.awt.GridLayout;
import java.awt.Component;
import java.awt.LayoutManager;
import java.awt.BorderLayout;
import java.awt.Panel;
import java.awt.Color;
import xfunctions.graphs.NumberInputPanel;
import xfunctions.graphs.Drawable;
import xfunctions.graphs.Axes;
import xfunctions.functions.ParseError;
import java.awt.Button;
import xfunctions.graphs.DirectionField;
import java.awt.CheckboxGroup;
import java.awt.Checkbox;
import xfunctions.functions.Expression;
import java.awt.TextField;
import xfunctions.functions.Variable;
import xfunctions.graphs.CoordinateRect;
import xfunctions.functions.Parser;
import java.util.Vector;

public class IntegralCurvesPanel extends GenericPanel implements Runnable
{
    private static final int EULER = 0;
    private static final int RUNGE_KUTTA_2 = 1;
    private static final int RUNGE_KUTTA_4 = 2;
    private Vector curves;
    Parser parser;
    CoordinateRect coords;
    Variable xVar;
    Variable yVar;
    TextField dxFunctionInput;
    TextField dyFunctionInput;
    Expression dxExp;
    Expression dyExp;
    double[] parameterValues;
    boolean fieldShown;
    Checkbox eulerCheckbox;
    Checkbox rungeKutta2Checkbox;
    Checkbox rungeKutta4Checkbox;
    CheckboxGroup methodButtons;
    Checkbox bidirectional;
    DirectionField directionField;
    Thread runner;
    Button stopButton;
    Button newCurveButton;
    boolean runnerwait;
    
    public IntegralCurvesPanel() {
        this((Parser)null);
    }
    
    public IntegralCurvesPanel(final Parser parser) {
        this.parameterValues = new double[] { -5.0, 5.0, -5.0, 5.0, 0.0, 4.0, 0.1 };
        if (parser == null) {
            this.parser = new Parser();
        }
        else {
            this.parser = new Parser(parser);
        }
        final String s = "y - 0.1*x";
        final String s2 = "-x - 0.1*y";
        this.xVar = this.parser.defineVariable("x");
        this.yVar = this.parser.defineVariable("y");
        try {
            this.dxExp = this.parser.parse(s);
            this.dyExp = this.parser.parse(s2);
        }
        catch (ParseError parseError) {}
        this.curves = new Vector();
        this.directionField = new DirectionField(this.dxExp, this.dyExp, this.xVar, this.yVar);
        this.fieldShown = true;
        this.coords = new CoordinateRect();
        (super.canvas = new ICPDisplayCanvas(this)).setCoords(this.coords);
        this.coords.add(new Axes());
        this.coords.add(this.directionField);
        (super.numberInput = new NumberInputPanel()).addRealRange("x");
        super.numberInput.addRealRange("y");
        super.numberInput.addTwoReals("Start x", "Start y");
        super.numberInput.addSingleReal("dt");
        super.numberInput.setValues(this.parameterValues);
        (this.stopButton = new Button("Stop Curves")).disable();
        this.newCurveButton = new Button("New Curve");
        super.buttons = new ButtonPanel(this, new Button[] { new Button("Compute!"), new Button("Clear"), this.stopButton });
        (this.dxFunctionInput = new TextField(s)).setBackground(Color.white);
        (this.dyFunctionInput = new TextField(s2)).setBackground(Color.white);
        this.methodButtons = new CheckboxGroup();
        this.eulerCheckbox = new Checkbox("Euler's Method", this.methodButtons, false);
        this.rungeKutta2Checkbox = new Checkbox("Runge-Kutta Order 2", this.methodButtons, false);
        this.rungeKutta4Checkbox = new Checkbox("Runge-Kutta Order 4", this.methodButtons, true);
        this.bidirectional = new Checkbox("Extend curves in both directions");
        final Panel panel = new Panel();
        this.setBackground(Color.lightGray);
        this.setLayout(new BorderLayout(5, 5));
        this.add("Center", super.canvas);
        final Panel panel2 = new Panel();
        this.add("West", panel2);
        final Panel panel3 = new Panel();
        this.add("South", panel3);
        panel2.setLayout(new BorderLayout());
        panel2.add("Center", super.numberInput);
        final Panel panel4 = new Panel();
        panel4.setLayout(new GridLayout(3, 1));
        panel4.add(this.eulerCheckbox);
        panel4.add(this.rungeKutta2Checkbox);
        panel4.add(this.rungeKutta4Checkbox);
        panel2.add("South", panel4);
        panel3.setLayout(new BorderLayout(2, 2));
        panel3.add("South", super.buttons);
        final Panel panel5 = new Panel();
        panel5.setLayout(new GridLayout(2, 2, 10, 2));
        final Panel panel6 = new Panel();
        panel6.setLayout(new BorderLayout());
        panel6.add("West", new Label("dx/dt = "));
        panel6.add("Center", this.dxFunctionInput);
        final Panel panel7 = new Panel();
        panel7.setLayout(new BorderLayout());
        panel7.add("West", new Label("dy/dt = "));
        panel7.add("Center", this.dyFunctionInput);
        panel5.add(panel6);
        panel.setLayout(new BorderLayout(5, 5));
        panel.add("West", this.newCurveButton);
        panel.add("Center", new Label("(or just click at start point)"));
        panel5.add(panel);
        panel5.add(panel7);
        panel5.add(this.bidirectional);
        panel3.add("Center", panel5);
        this.dyFunctionInput.requestFocus();
    }
    
    synchronized void installExample(final Object[] array) {
        final double[] array2 = (double[])array[2];
        final String text = (String)array[3];
        final String text2 = (String)array[4];
        final int method = (int)array[5] - 1;
        final boolean booleanValue = (boolean)array[6];
        final double[][] array3 = (double[][])array[7];
        try {
            this.stopAllCurves();
            final Expression parse = this.parser.parse(text);
            final Expression parse2 = this.parser.parse(text2);
            this.dxExp = parse;
            this.dyExp = parse2;
            this.directionField.setExpressions(this.dxExp, this.dyExp);
            this.fieldShown = true;
            if (method == 0) {
                this.methodButtons.setCurrent(this.eulerCheckbox);
            }
            else if (method == 1) {
                this.methodButtons.setCurrent(this.rungeKutta2Checkbox);
            }
            else {
                this.methodButtons.setCurrent(this.rungeKutta4Checkbox);
            }
            this.bidirectional.setState(booleanValue);
            this.dxFunctionInput.setText(text);
            this.dyFunctionInput.setText(text2);
            super.numberInput.setValues(array2);
            this.coords.setLimits(array2);
            this.dxFunctionInput.requestFocus();
            for (int i = 0; i < 7; ++i) {
                this.parameterValues[i] = array2[i];
            }
            super.canvas.invalidateCanvas();
            if (array3.length > 0) {
                for (int j = 0; j < array3.length; ++j) {
                    final IntegralCurveData integralCurveData = new IntegralCurveData();
                    integralCurveData.dt = array2[6];
                    integralCurveData.method = method;
                    final IntegralCurveData integralCurveData2 = integralCurveData;
                    final IntegralCurveData integralCurveData3 = integralCurveData;
                    final double n = array3[j][0];
                    integralCurveData3.xOmega = n;
                    integralCurveData2.xAlpha = n;
                    final IntegralCurveData integralCurveData4 = integralCurveData;
                    final IntegralCurveData integralCurveData5 = integralCurveData;
                    final double n2 = array3[j][1];
                    integralCurveData5.yOmega = n2;
                    integralCurveData4.yAlpha = n2;
                    this.curves.addElement(integralCurveData);
                }
                this.runnerwait = true;
                if (this.runner == null || !this.runner.isAlive()) {
                    (this.runner = new Thread(this)).start();
                }
                else {
                    this.notify();
                }
                this.stopButton.enable();
            }
        }
        catch (ParseError parseError) {}
    }
    
    private boolean doCompute() {
        this.stopAllCurves();
        this.fieldShown = false;
        final double[] values = super.numberInput.getValues(super.canvas, 4);
        if (values == null) {
            return false;
        }
        final String trim = this.dxFunctionInput.getText().trim();
        final String trim2 = this.dyFunctionInput.getText().trim();
        Expression parse;
        try {
            parse = this.parser.parse(trim);
        }
        catch (ParseError parseError) {
            super.canvas.setErrorMessage("Your definition of the derivative dx/dt contains an error: " + parseError.getMessage());
            this.dxFunctionInput.select(parseError.errorPosition, parseError.errorPosition);
            this.dxFunctionInput.requestFocus();
            return false;
        }
        Expression parse2;
        try {
            parse2 = this.parser.parse(trim2);
        }
        catch (ParseError parseError2) {
            super.canvas.setErrorMessage("Your definition of the derivative dy/dt contains an error: " + parseError2.getMessage());
            this.dyFunctionInput.select(parseError2.errorPosition, parseError2.errorPosition);
            this.dyFunctionInput.requestFocus();
            return false;
        }
        this.dxExp = parse;
        this.dyExp = parse2;
        this.directionField.setExpressions(parse, parse2);
        this.coords.setLimits(values);
        super.canvas.invalidateCanvas();
        return this.fieldShown = true;
    }
    
    private void doNewCurve() {
        if (!this.fieldShown && !this.doCompute()) {
            return;
        }
        final double[] values = super.numberInput.getValues(super.canvas, 4, 6);
        if (values == null) {
            return;
        }
        if (values[2] <= 0.0) {
            super.canvas.setErrorMessage("The dt value must be greater than zero.");
            return;
        }
        this.startCurve(values[0], values[1], values[2]);
    }
    
    private void extendCurves(final ICPDisplayCanvas icpDisplayCanvas) {
        if (this.dxExp == null || this.dyExp == null) {
            this.stopAllCurves();
            return;
        }
        final boolean state = this.bidirectional.getState();
        final double[] array = new double[2];
        boolean b = true;
        for (int i = 0; i < this.curves.size(); ++i) {
            final IntegralCurveData integralCurveData = this.curves.elementAt(i);
            if (!integralCurveData.deadForward) {
                switch (integralCurveData.method) {
                    case 0: {
                        this.nextEuler(integralCurveData.xOmega, integralCurveData.yOmega, integralCurveData.dt, array);
                        break;
                    }
                    case 1: {
                        this.nextRK2(integralCurveData.xOmega, integralCurveData.yOmega, integralCurveData.dt, array);
                        break;
                    }
                    case 2: {
                        this.nextRK4(integralCurveData.xOmega, integralCurveData.yOmega, integralCurveData.dt, array);
                        break;
                    }
                }
                if (icpDisplayCanvas.putLine(integralCurveData.xOmega, integralCurveData.yOmega, array[0], array[1])) {
                    integralCurveData.xOmega = array[0];
                    integralCurveData.yOmega = array[1];
                    b = false;
                }
                else {
                    integralCurveData.deadForward = true;
                }
            }
            if (state && !integralCurveData.deadBackward) {
                switch (integralCurveData.method) {
                    case 0: {
                        this.nextEuler(integralCurveData.xAlpha, integralCurveData.yAlpha, -integralCurveData.dt, array);
                        break;
                    }
                    case 1: {
                        this.nextRK2(integralCurveData.xAlpha, integralCurveData.yAlpha, -integralCurveData.dt, array);
                        break;
                    }
                    case 2: {
                        this.nextRK4(integralCurveData.xAlpha, integralCurveData.yAlpha, -integralCurveData.dt, array);
                        break;
                    }
                }
                if (icpDisplayCanvas.putLine(integralCurveData.xAlpha, integralCurveData.yAlpha, array[0], array[1])) {
                    integralCurveData.xAlpha = array[0];
                    integralCurveData.yAlpha = array[1];
                    b = false;
                }
                else {
                    integralCurveData.deadBackward = true;
                }
            }
        }
        if (b) {
            this.stopAllCurves();
        }
    }
    
    public void aboutToHide() {
        this.stopAllCurves();
        if (this.runner != null && this.runner.isAlive()) {
            this.runner.stop();
        }
        this.runner = null;
        super.aboutToHide();
    }
    
    private void doClear() {
        if (!this.fieldShown) {
            return;
        }
        this.fieldShown = false;
        this.directionField.setExpressions(null, null);
        super.canvas.invalidateCanvas();
    }
    
    public boolean doButtonCommand(final String s) {
        if ("Compute!".equals(s)) {
            this.doCompute();
        }
        else if ("Clear".equals(s)) {
            this.doClear();
        }
        else {
            if (!"Stop Curves".equals(s)) {
                return false;
            }
            this.stopAllCurves();
        }
        return true;
    }
    
    public synchronized boolean action(final Event event, final Object o) {
        if (event.target instanceof TextField && (event.target == this.dxFunctionInput || event.target == this.dyFunctionInput || super.numberInput.hasTextField((TextField)event.target))) {
            this.doCompute();
            return true;
        }
        if (event.target == this.newCurveButton) {
            this.doNewCurve();
            return true;
        }
        return super.action(event, o);
    }
    
    synchronized void mouseDownOnCanvas(final int n, final int n2) {
        if (!this.fieldShown && !this.doCompute()) {
            return;
        }
        final double[] values = super.numberInput.getValues(super.canvas, 6, 6);
        if (values == null) {
            return;
        }
        if (values[0] < 0.0) {
            super.canvas.setErrorMessage("The dt value must be greater than zero.");
            return;
        }
        final double pixelToX = this.coords.pixelToX(n);
        final double pixelToY = this.coords.pixelToY(n2);
        super.numberInput.setValue(4, pixelToX);
        super.numberInput.setValue(5, pixelToY);
        this.startCurve(pixelToX, pixelToY, values[0]);
    }
    
    synchronized void stopAllCurves() {
        this.curves.removeAllElements();
        this.stopButton.disable();
    }
    
    synchronized void startCurve(final double n, final double n2, final double dt) {
        final IntegralCurveData integralCurveData = new IntegralCurveData();
        integralCurveData.dt = dt;
        final Checkbox current = this.methodButtons.getCurrent();
        if (current == this.eulerCheckbox) {
            integralCurveData.method = 0;
        }
        else if (current == this.rungeKutta2Checkbox) {
            integralCurveData.method = 1;
        }
        else {
            integralCurveData.method = 2;
        }
        final IntegralCurveData integralCurveData2 = integralCurveData;
        integralCurveData.xOmega = n;
        integralCurveData2.xAlpha = n;
        final IntegralCurveData integralCurveData3 = integralCurveData;
        integralCurveData.yOmega = n2;
        integralCurveData3.yAlpha = n2;
        this.curves.addElement(integralCurveData);
        if (this.runner == null || !this.runner.isAlive()) {
            (this.runner = new Thread(this)).start();
        }
        this.stopButton.enable();
        this.notify();
    }
    
    public void run() {
        while (true) {
            synchronized (this) {
                while (this.curves.size() == 0) {
                    try {
                        this.wait();
                        this.wait(50L);
                    }
                    catch (InterruptedException ex) {}
                }
            }
            synchronized (this) {
                if (this.runnerwait) {
                    try {
                        this.wait(1000L);
                    }
                    catch (InterruptedException ex2) {}
                    this.runnerwait = false;
                }
                final ICPDisplayCanvas icpDisplayCanvas = (ICPDisplayCanvas)super.canvas;
                icpDisplayCanvas.startDrawing();
                this.extendCurves(icpDisplayCanvas);
                icpDisplayCanvas.doneDrawing();
                try {
                    this.wait(20L);
                }
                catch (InterruptedException ex3) {}
            }
        }
    }
    
    private void nextEuler(final double value, final double value2, final double n, final double[] array) {
        this.xVar.setValue(value);
        this.yVar.setValue(value2);
        final double value3 = this.dxExp.value();
        final double value4 = this.dyExp.value();
        array[0] = value + n * value3;
        array[1] = value2 + n * value4;
    }
    
    private void nextRK2(final double value, final double value2, final double n, final double[] array) {
        this.xVar.setValue(value);
        this.yVar.setValue(value2);
        final double value3 = this.dxExp.value();
        final double value4 = this.dyExp.value();
        final double value5 = value + n * value3;
        final double value6 = value2 + n * value4;
        this.xVar.setValue(value5);
        this.yVar.setValue(value6);
        final double value7 = this.dxExp.value();
        final double value8 = this.dyExp.value();
        array[0] = value + 0.5 * n * (value3 + value7);
        array[1] = value2 + 0.5 * n * (value4 + value8);
    }
    
    private void nextRK4(final double value, final double value2, final double n, final double[] array) {
        this.xVar.setValue(value);
        this.yVar.setValue(value2);
        final double value3 = this.dxExp.value();
        final double value4 = this.dyExp.value();
        final double value5 = value + 0.5 * n * value3;
        final double value6 = value2 + 0.5 * n * value4;
        this.xVar.setValue(value5);
        this.yVar.setValue(value6);
        final double value7 = this.dxExp.value();
        final double value8 = this.dyExp.value();
        final double value9 = value + 0.5 * n * value7;
        final double value10 = value2 + 0.5 * n * value8;
        this.xVar.setValue(value9);
        this.yVar.setValue(value10);
        final double value11 = this.dxExp.value();
        final double value12 = this.dyExp.value();
        final double value13 = value + n * value11;
        final double value14 = value2 + n * value12;
        this.xVar.setValue(value13);
        this.yVar.setValue(value14);
        final double value15 = this.dxExp.value();
        final double value16 = this.dyExp.value();
        array[0] = value + n / 6.0 * (value3 + 2.0 * value7 + 2.0 * value11 + value15);
        array[1] = value2 + n / 6.0 * (value4 + 2.0 * value8 + 2.0 * value12 + value16);
    }
}
