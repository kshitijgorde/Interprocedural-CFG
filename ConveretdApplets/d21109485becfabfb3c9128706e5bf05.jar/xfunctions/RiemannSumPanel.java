// 
// Decompiled by Procyon v0.5.30
// 

package xfunctions;

import java.awt.Event;
import xfunctions.functions.Utilities;
import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.LayoutManager;
import java.awt.GridLayout;
import java.awt.Panel;
import xfunctions.graphs.NumberInputPanel;
import xfunctions.graphs.Axes;
import xfunctions.graphs.Drawable;
import xfunctions.functions.ParseError;
import java.awt.Color;
import xfunctions.functions.Expression;
import java.awt.Button;
import java.awt.Label;
import java.awt.Choice;
import java.awt.TextField;
import xfunctions.functions.Variable;
import xfunctions.graphs.Graph1D;
import xfunctions.graphs.CoordinateRect;
import xfunctions.functions.Parser;

public class RiemannSumPanel extends GenericPanel
{
    Parser parser;
    CoordinateRect coords;
    Graph1D graph;
    Variable xVar;
    TextField functionInput;
    double[] parameterValues;
    String functionDef;
    Choice methodChoice;
    int currentMethod;
    Label[] sumText;
    String[] methodNames;
    double[] endpointVals;
    double[] maxVals;
    double[] minVals;
    double[] midpointVals;
    int intervalCount;
    Button clearButton;
    Button divideButton;
    Button computeButton;
    RiemannSumRects rects;
    Expression exp;
    Expression deriv;
    Color rectColor;
    static final int maxIntervals = 512;
    
    public RiemannSumPanel() {
        this((Parser)null);
    }
    
    public RiemannSumPanel(final Parser parser) {
        this.parameterValues = new double[] { -1.0, 2.0, -0.2, 1.2, 5.0 };
        this.functionDef = "exp(-x^2)";
        this.currentMethod = 0;
        this.sumText = new Label[6];
        this.methodNames = new String[] { "Left Endpoints", "Right Endpoints", "Midpoints", "~Circumscribed", "~Inscribed", "Trapezoids" };
        this.rectColor = new Color(180, 255, 180);
        if (parser == null) {
            this.parser = new Parser();
        }
        else {
            this.parser = new Parser(parser);
        }
        this.xVar = this.parser.defineVariable("x");
        try {
            this.exp = this.parser.parse(this.functionDef);
        }
        catch (ParseError parseError) {}
        this.deriv = this.exp.derivative(this.xVar);
        (this.coords = new CoordinateRect()).setLimits(this.parameterValues);
        this.coords.setGap(10);
        this.rects = new RiemannSumRects();
        this.coords.add(this.rects);
        this.coords.add(new Axes());
        (this.graph = new Graph1D(this.exp, this.xVar)).setGraphColor(Color.black);
        this.coords.add(this.graph);
        (super.canvas = new RiemannSumCanvas()).setCoords(this.coords);
        (super.numberInput = new NumberInputPanel(false)).addRealRange("x");
        super.numberInput.addRealRange("y");
        super.numberInput.addSingleInteger("Intervals", 1, 512);
        super.numberInput.setValues(this.parameterValues);
        (this.functionInput = new TextField(this.functionDef)).setBackground(Color.white);
        this.computeButton = new Button("Compute!");
        this.clearButton = new Button("Clear Intervals");
        this.divideButton = new Button("Divide Intervals");
        this.methodChoice = new Choice();
        for (int i = 0; i < 6; ++i) {
            this.methodChoice.addItem(this.methodNames[i]);
        }
        this.intervalCount = (int)this.parameterValues[4];
        this.setBackground(Color.lightGray);
        final Panel panel = new Panel();
        panel.setLayout(new GridLayout(3, 1, 3, 3));
        final Panel panel2 = new Panel();
        panel2.setLayout(new GridLayout(1, 2));
        panel2.add(new Label("  Enter a function of x:"));
        panel2.add(new Label("(Try clicking on the graph!) ", 2));
        panel.add(panel2);
        final Panel panel3 = new Panel();
        panel3.setLayout(new BorderLayout());
        panel3.add("West", new Label("  y = "));
        panel3.add("Center", this.functionInput);
        panel.add(panel3);
        final Panel panel4 = new Panel();
        panel4.setLayout(new GridLayout(1, 5));
        panel4.add(this.computeButton);
        panel4.add(this.clearButton);
        panel4.add(this.divideButton);
        panel4.add(new Label("Select Display: ", 2));
        panel4.add(this.methodChoice);
        panel.add(panel4);
        final Panel panel5 = new Panel();
        panel5.setLayout(new GridLayout(12, 1));
        for (int j = 0; j < 6; ++j) {
            panel5.add(new Label(String.valueOf(this.methodNames[j]) + ":"));
            (this.sumText[j] = new Label(" ")).setForeground(Color.red);
            panel5.add(this.sumText[j]);
        }
        this.setLayout(new BorderLayout(7, 3));
        this.add("Center", super.canvas);
        this.add("West", super.numberInput);
        this.add("South", panel);
        this.add("East", panel5);
        this.setSumData();
    }
    
    void aboutToHide() {
        final double[] array = null;
        this.midpointVals = array;
        this.minVals = array;
        this.maxVals = array;
        this.endpointVals = array;
        this.rects.setRectHeights(null);
        super.aboutToHide();
    }
    
    synchronized void installExample(final Object[] array) {
        final double[] array2 = (double[])array[2];
        final String text = (String)array[3];
        try {
            this.exp = this.parser.parse(text);
            this.deriv = this.exp.derivative(this.xVar);
            this.graph.setExpression(this.exp);
            this.functionInput.setText(text);
            super.numberInput.setValues(array2);
            this.coords.setLimits(array2);
            final int intValue = (int)array[4];
            this.currentMethod = intValue - 1;
            this.methodChoice.select(intValue - 1);
            this.setIntervalCount((int)array2[4]);
            this.setRectData();
            this.functionInput.requestFocus();
            for (int i = 0; i < 5; ++i) {
                this.parameterValues[i] = array2[i];
            }
            super.canvas.invalidateCanvas();
        }
        catch (ParseError parseError) {}
    }
    
    void setSumData() {
        this.endpointVals = new double[this.intervalCount + 1];
        this.maxVals = new double[this.intervalCount];
        this.minVals = new double[this.intervalCount];
        this.midpointVals = new double[this.intervalCount];
        double xmin = this.coords.getXmin();
        final double n = (this.coords.getXmax() - xmin) / this.intervalCount;
        this.xVar.setValue(xmin);
        this.endpointVals[0] = this.exp.value();
        int n2 = 200 / this.intervalCount;
        double n3;
        if (n2 < 1) {
            n2 = 1;
            n3 = n;
        }
        else {
            n3 = n / n2;
        }
        boolean b = this.deriv.value() > 0.0;
        for (int i = 1; i <= this.intervalCount; ++i) {
            xmin += n;
            this.xVar.setValue(xmin);
            this.endpointVals[i] = this.exp.value();
            this.xVar.setValue(xmin - n / 2.0);
            this.midpointVals[i - 1] = this.exp.value();
            double n5;
            double n4 = n5 = this.endpointVals[i - 1];
            for (int j = 1; j <= n2; ++j) {
                final boolean b2 = b;
                final double value = xmin - n + j * n3;
                this.xVar.setValue(value);
                b = (this.deriv.value() > 0.0);
                if (b2 != b) {
                    if (b2) {
                        final double searchMax = this.searchMax(value - n3, value, 1);
                        if (searchMax > n5) {
                            n5 = searchMax;
                        }
                    }
                    else {
                        final double searchMin = this.searchMin(value - n3, value, 1);
                        if (searchMin < n4) {
                            n4 = searchMin;
                        }
                    }
                }
            }
            if (this.endpointVals[i] > n5) {
                n5 = this.endpointVals[i];
            }
            else if (this.endpointVals[i] < n4) {
                n4 = this.endpointVals[i];
            }
            this.minVals[i - 1] = n4;
            this.maxVals[i - 1] = n5;
        }
        final double n6 = this.endpointVals[0];
        double n7 = 0.0;
        double n8 = 0.0;
        double n9 = 0.0;
        double n10 = 0.0;
        for (int k = 0; k < this.intervalCount; ++k) {
            n7 += this.endpointVals[k];
            n8 += this.midpointVals[k];
            n9 += this.maxVals[k];
            n10 += this.minVals[k];
        }
        final double n11 = n7 - this.endpointVals[0] + this.endpointVals[this.intervalCount];
        this.sumText[0].setText("  " + Utilities.realToString(n7 * n));
        this.sumText[1].setText("  " + Utilities.realToString(n11 * n));
        this.sumText[2].setText("  " + Utilities.realToString(n8 * n));
        this.sumText[3].setText("  " + Utilities.realToString(n9 * n));
        this.sumText[4].setText("  " + Utilities.realToString(n10 * n));
        this.sumText[5].setText("  " + Utilities.realToString((n7 + n11) / 2.0 * n));
        this.setRectData();
    }
    
    double searchMin(final double n, final double n2, final int n3) {
        final double value = (n + n2) / 2.0;
        this.xVar.setValue(value);
        if (n3 >= 13) {
            return this.exp.value();
        }
        if (this.deriv.value() < 0.0) {
            return this.searchMin(value, n2, n3 + 1);
        }
        return this.searchMin(n, value, n3 + 1);
    }
    
    double searchMax(final double n, final double n2, final int n3) {
        final double value = (n + n2) / 2.0;
        this.xVar.setValue(value);
        if (n3 >= 13) {
            return this.exp.value();
        }
        if (this.deriv.value() > 0.0) {
            return this.searchMin(value, n2, n3 + 1);
        }
        return this.searchMin(n, value, n3 + 1);
    }
    
    void setRectData() {
        this.rects.setMethod(this.currentMethod);
        if (this.currentMethod == 3) {
            this.rects.setRectHeights(this.maxVals);
        }
        else if (this.currentMethod == 4) {
            this.rects.setRectHeights(this.minVals);
        }
        else if (this.currentMethod == 2) {
            this.rects.setRectHeights(this.midpointVals);
        }
        else {
            this.rects.setRectHeights(this.endpointVals);
        }
    }
    
    void setMethodChoice(final int currentMethod) {
        if (currentMethod == this.currentMethod) {
            return;
        }
        if (!this.checkData()) {
            this.methodChoice.select(this.currentMethod);
            return;
        }
        this.currentMethod = currentMethod;
        this.setRectData();
        super.canvas.invalidateCanvas();
    }
    
    void setIntervalCount(int intervalCount) {
        if (intervalCount >= 512) {
            intervalCount = 512;
            this.divideButton.disable();
        }
        else if (2 * intervalCount <= 512) {
            this.divideButton.enable();
        }
        super.numberInput.setValue(4, intervalCount);
        this.parameterValues[4] = intervalCount;
        this.intervalCount = intervalCount;
        this.setSumData();
    }
    
    boolean checkData() {
        final double[] values = super.numberInput.getValues(super.canvas);
        if (values == null) {
            return false;
        }
        final String trim = this.functionInput.getText().trim();
        if (trim.equals(this.functionDef) && this.endpointVals != null) {
            if (values[0] == this.parameterValues[0] && values[1] == this.parameterValues[1] && values[2] == this.parameterValues[2] && values[3] == this.parameterValues[3] && values[4] == this.parameterValues[4]) {
                return true;
            }
        }
        else {
            Expression parse;
            try {
                parse = this.parser.parse(trim);
            }
            catch (ParseError parseError) {
                super.canvas.setErrorMessage("The definition of your function contains an error: " + parseError.getMessage());
                this.functionInput.select(parseError.errorPosition, parseError.errorPosition);
                this.functionInput.requestFocus();
                return false;
            }
            this.exp = parse;
            this.deriv = this.exp.derivative(this.xVar);
            this.graph.setExpression(this.exp);
            this.functionDef = trim;
        }
        this.coords.setLimits(values);
        this.parameterValues = values;
        if (values[4] != this.intervalCount || this.endpointVals == null) {
            this.setIntervalCount((int)values[4]);
        }
        else {
            this.setSumData();
        }
        return true;
    }
    
    public synchronized boolean action(final Event event, final Object o) {
        if (event.target instanceof TextField || event.target == this.computeButton) {
            if (this.checkData()) {
                super.canvas.invalidateCanvas();
            }
            return true;
        }
        if (event.target == this.methodChoice) {
            this.setMethodChoice(this.methodChoice.getSelectedIndex());
            return true;
        }
        if (event.target == this.clearButton) {
            if (this.checkData()) {
                this.setIntervalCount(1);
                super.canvas.invalidateCanvas();
            }
            return true;
        }
        if (event.target == this.divideButton) {
            if (this.checkData()) {
                this.setIntervalCount(2 * this.intervalCount);
                super.canvas.invalidateCanvas();
            }
            return true;
        }
        return super.action(event, o);
    }
    
    public boolean mouseDown(final Event event, final int n, final int n2) {
        if (event.target != super.canvas) {
            return true;
        }
        synchronized (super.canvas) {
            if (!this.checkData()) {
                // monitorexit(super.canvas)
                return true;
            }
            final double pixelToX = this.coords.pixelToX(n - super.canvas.location().x);
            final double xmin = this.coords.getXmin();
            final double n3 = (this.coords.getXmax() - xmin) / this.intervalCount;
            final int n4 = (int)((pixelToX - xmin) / n3);
            if (n4 < 0 || n4 >= this.intervalCount) {
                // monitorexit(super.canvas)
                return true;
            }
            double n5 = 0.0;
            switch (this.currentMethod) {
                case 0:
                case 5: {
                    n5 = this.endpointVals[n4];
                    break;
                }
                case 1: {
                    n5 = this.endpointVals[n4 + 1];
                    break;
                }
                case 2: {
                    n5 = this.midpointVals[n4];
                    break;
                }
                case 3: {
                    n5 = this.maxVals[n4];
                    break;
                }
                case 4: {
                    n5 = this.minVals[n4];
                    break;
                }
            }
            ((RiemannSumCanvas)super.canvas).setDisplayData(this.methodNames[this.currentMethod], n4 + 1, xmin + n3 * n4, xmin + n3 * (n4 + 1), n5, this.endpointVals[n4 + 1], this.currentMethod == 5);
            // monitorexit(super.canvas)
            return true;
        }
    }
}
