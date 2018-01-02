// 
// Decompiled by Procyon v0.5.30
// 

package xfunctions;

import java.awt.Point;
import java.awt.Event;
import xfunctions.functions.Expression;
import java.awt.Label;
import java.awt.GridLayout;
import java.awt.Component;
import java.awt.LayoutManager;
import java.awt.BorderLayout;
import java.awt.Panel;
import xfunctions.graphs.NumberInputPanel;
import xfunctions.functions.ParseError;
import xfunctions.graphs.HiliteDisplayCanvas;
import xfunctions.graphs.Drawable;
import xfunctions.graphs.Axes;
import java.awt.Color;
import java.awt.Choice;
import java.awt.Button;
import java.awt.TextField;
import xfunctions.functions.Variable;
import xfunctions.graphs.ParametricCurve;
import xfunctions.graphs.CoordinateRect;
import xfunctions.functions.Parser;

public class ParametricCurvesPanel extends GenericPanel implements Runnable
{
    Parser parser;
    CoordinateRect coords;
    ParametricCurve[] graph;
    Variable tVar;
    TextField xFunctionInput;
    TextField yFunctionInput;
    double[] parameterValues;
    String[][] functionDefs;
    SCanvas colorPatch;
    Button traceButton;
    Choice graphChoice;
    int currentFunctionNumber;
    Color[] graphColors;
    private boolean running;
    private Thread runner;
    private ParametricCurve traceGraph;
    
    public ParametricCurvesPanel() {
        this((Parser)null);
    }
    
    public ParametricCurvesPanel(final Parser parser) {
        this.graph = new ParametricCurve[8];
        this.parameterValues = new double[] { -5.0, 5.0, -5.0, 5.0, 0.0, 6.3, 200.0 };
        this.functionDefs = new String[8][2];
        this.currentFunctionNumber = 0;
        this.graphColors = new Color[] { Color.magenta, new Color(0, 180, 0), Color.red, new Color(0, 200, 200), Color.orange, Color.gray, Color.blue, Color.pink };
        if (parser == null) {
            this.parser = new Parser();
        }
        else {
            this.parser = new Parser(parser);
        }
        this.tVar = this.parser.defineVariable("t");
        (this.coords = new CoordinateRect()).add(new Axes());
        (super.canvas = new HiliteDisplayCanvas()).setCoords(this.coords);
        final String s = "2*sin(3*t)";
        final String s2 = "3*cos(5*t)";
        Expression parse = null;
        Expression parse2 = null;
        try {
            parse = this.parser.parse(s);
            parse2 = this.parser.parse(s2);
        }
        catch (ParseError parseError) {
            System.out.println("Unexpected parse error in init.");
        }
        (this.graph[0] = new ParametricCurve(parse, parse2, this.tVar, this.parameterValues[4], this.parameterValues[5], (int)Math.round(this.parameterValues[6]))).setGraphColor(this.graphColors[0]);
        this.functionDefs[0][0] = s;
        this.functionDefs[0][1] = s2;
        this.coords.add(this.graph[0]);
        (super.numberInput = new NumberInputPanel()).addRealRange("x");
        super.numberInput.addRealRange("y");
        super.numberInput.addRealRange("t");
        super.numberInput.addSingleInteger("Points on curve", 2, 1000);
        super.numberInput.setValues(this.parameterValues);
        super.buttons = new ButtonPanel(this, new Button[] { new Button("Graph!"), new Button("Remove"), new Button("Clear All") });
        (this.xFunctionInput = new TextField(s)).setBackground(Color.white);
        (this.yFunctionInput = new TextField(s2)).setBackground(Color.white);
        (this.colorPatch = new SCanvas()).setBackground(this.graphColors[0]);
        this.graphChoice = new Choice();
        for (int i = 1; i <= 8; ++i) {
            this.graphChoice.addItem("No. " + i);
        }
        final Panel panel = new Panel();
        panel.setLayout(new BorderLayout(5, 5));
        panel.add("West", this.graphChoice);
        panel.add("Center", this.colorPatch);
        final Panel panel2 = new Panel();
        panel2.setLayout(new GridLayout(1, 2, 3, 3));
        final Panel panel3 = new Panel();
        panel3.setLayout(new BorderLayout(3, 3));
        panel3.add("West", new Label("x(t) ="));
        panel3.add("Center", this.xFunctionInput);
        panel2.add(panel3);
        final Panel panel4 = new Panel();
        panel4.setLayout(new BorderLayout(3, 3));
        panel4.add("West", new Label("y(t) ="));
        panel4.add("Center", this.yFunctionInput);
        panel2.add(panel4);
        final Panel panel5 = new Panel();
        panel5.setLayout(new BorderLayout(3, 3));
        panel5.add("West", panel);
        panel5.add("Center", panel2);
        final Panel panel6 = new Panel();
        panel6.setLayout(new GridLayout(3, 1, 3, 3));
        this.traceButton = new Button("Trace");
        final Panel panel7 = new Panel();
        panel7.setLayout(new BorderLayout());
        panel7.add("Center", new Label("Select curve number, enter two functions of t, press return:"));
        panel7.add("East", this.traceButton);
        panel6.add(panel7);
        panel6.add(panel5);
        panel6.add(super.buttons);
        this.setBackground(Color.lightGray);
        this.setLayout(new BorderLayout(3, 3));
        this.add("Center", super.canvas);
        this.add("South", panel6);
        this.add("West", super.numberInput);
        this.xFunctionInput.requestFocus();
    }
    
    synchronized void installExample(final Object[] array) {
        final double[] array2 = (double[])array[2];
        super.numberInput.setValues(array2);
        this.coords.setLimits(array2);
        int n = 0;
        final String[][] array3 = (String[][])array[3];
        for (int i = 0; i < array3.length; ++i) {
            try {
                final Expression parse = this.parser.parse(array3[i][0]);
                final Expression parse2 = this.parser.parse(array3[i][1]);
                if (this.graph[n] == null) {
                    (this.graph[n] = new ParametricCurve(parse, parse2, this.tVar, array2[4], array2[5], (int)Math.round(array2[6]))).setGraphColor(this.graphColors[n]);
                    this.coords.add(this.graph[n]);
                }
                else {
                    this.graph[n].setExpressions(parse, parse2);
                }
                this.functionDefs[n][0] = array3[i][0];
                this.functionDefs[n][1] = array3[i][1];
                ++n;
            }
            catch (Exception ex) {}
        }
        for (int j = n; j < 8; ++j) {
            if (this.graph[j] != null) {
                this.graph[j].setExpressions(null, null);
                this.graph[j].setCurveData(array2[4], array2[5], (int)Math.round(array2[6]));
            }
            this.functionDefs[j][0] = null;
            this.functionDefs[j][1] = null;
        }
        this.currentFunctionNumber = 0;
        this.graphChoice.select(0);
        this.colorPatch.setBackground(this.graphColors[0]);
        this.colorPatch.repaint();
        this.xFunctionInput.setText(this.functionDefs[0][0]);
        this.yFunctionInput.setText(this.functionDefs[0][1]);
        this.xFunctionInput.requestFocus();
        for (int k = 0; k < 7; ++k) {
            this.parameterValues[k] = array2[k];
        }
        super.canvas.invalidateCanvas();
    }
    
    boolean doButtonCommand(final String s) {
        this.stopAnimation();
        if ("Graph!".equals(s)) {
            if (this.checkData(true)) {
                super.canvas.invalidateCanvas();
            }
        }
        else if ("Remove".equals(s)) {
            if (this.graph[this.currentFunctionNumber] != null) {
                this.coords.remove(this.graph[this.currentFunctionNumber]);
                this.xFunctionInput.setText("");
                this.yFunctionInput.setText("");
                this.functionDefs[this.currentFunctionNumber][0] = null;
                this.functionDefs[this.currentFunctionNumber][1] = null;
                this.graph[this.currentFunctionNumber] = null;
                this.xFunctionInput.requestFocus();
                super.canvas.invalidateCanvas();
            }
        }
        else {
            if (!"Clear All".equals(s)) {
                return false;
            }
            for (int i = 0; i < 8; ++i) {
                if (this.graph[i] != null) {
                    this.coords.remove(this.graph[i]);
                }
                this.graph[i] = null;
                this.functionDefs[i][0] = null;
                this.functionDefs[i][1] = null;
            }
            this.graphChoice.select(0);
            this.colorPatch.setBackground(this.graphColors[0]);
            this.colorPatch.repaint();
            this.currentFunctionNumber = 0;
            this.xFunctionInput.setText("");
            this.yFunctionInput.setText("");
            this.xFunctionInput.requestFocus();
            super.canvas.invalidateCanvas();
        }
        return true;
    }
    
    private void selectFunction(final int currentFunctionNumber) {
        if (currentFunctionNumber == this.currentFunctionNumber) {
            return;
        }
        final String trim = this.xFunctionInput.getText().trim();
        final String trim2 = this.yFunctionInput.getText().trim();
        if ((!trim.equals("") || !trim2.equals("")) && !this.checkData(true)) {
            this.graphChoice.select(this.currentFunctionNumber);
            return;
        }
        super.canvas.invalidateCanvas();
        this.currentFunctionNumber = currentFunctionNumber;
        if (this.functionDefs[currentFunctionNumber][0] != null) {
            this.xFunctionInput.setText(this.functionDefs[currentFunctionNumber][0]);
        }
        else {
            this.xFunctionInput.setText("");
        }
        if (this.functionDefs[currentFunctionNumber][1] != null) {
            this.yFunctionInput.setText(this.functionDefs[currentFunctionNumber][1]);
        }
        else {
            this.yFunctionInput.setText("");
        }
        this.colorPatch.setBackground(this.graphColors[currentFunctionNumber]);
        this.colorPatch.repaint();
        this.xFunctionInput.selectAll();
        this.xFunctionInput.requestFocus();
    }
    
    private boolean checkData(final boolean b) {
        this.stopAnimation();
        final double[] values = super.numberInput.getValues(super.canvas);
        if (values == null) {
            return false;
        }
        final String trim = this.xFunctionInput.getText().trim();
        final String trim2 = this.yFunctionInput.getText().trim();
        if (b || trim.length() > 0 || trim2.length() > 0) {
            Label_0380: {
                if (this.functionDefs[this.currentFunctionNumber][0] != null && this.functionDefs[this.currentFunctionNumber][1] != null && trim.equals(this.functionDefs[this.currentFunctionNumber][0])) {
                    if (trim2.equals(this.functionDefs[this.currentFunctionNumber][1])) {
                        break Label_0380;
                    }
                }
                Expression parse;
                try {
                    parse = this.parser.parse(trim);
                }
                catch (ParseError parseError) {
                    super.canvas.setErrorMessage("The definition of your function x(t) contains an error: " + parseError.getMessage());
                    this.xFunctionInput.select(parseError.errorPosition, parseError.errorPosition);
                    this.xFunctionInput.requestFocus();
                    return false;
                }
                Expression parse2;
                try {
                    parse2 = this.parser.parse(trim2);
                }
                catch (ParseError parseError2) {
                    super.canvas.setErrorMessage("The definition of your function y(t) contains an error: " + parseError2.getMessage());
                    this.yFunctionInput.select(parseError2.errorPosition, parseError2.errorPosition);
                    this.yFunctionInput.requestFocus();
                    return false;
                }
                if (this.graph[this.currentFunctionNumber] == null) {
                    (this.graph[this.currentFunctionNumber] = new ParametricCurve(parse, parse2, this.tVar, values[4], values[5], (int)Math.round(values[6]))).setGraphColor(this.graphColors[this.currentFunctionNumber]);
                    this.coords.add(this.graph[this.currentFunctionNumber]);
                }
                else {
                    this.graph[this.currentFunctionNumber].setExpressions(parse, parse2);
                }
            }
            this.functionDefs[this.currentFunctionNumber][0] = trim;
            this.functionDefs[this.currentFunctionNumber][1] = trim2;
        }
        this.coords.setLimits(values);
        for (int i = 0; i < this.graph.length; ++i) {
            if (this.graph[i] != null) {
                this.graph[i].setCurveData(values[4], values[5], (int)Math.round(values[6]));
            }
        }
        this.parameterValues = values;
        return true;
    }
    
    private synchronized void nextFunction() {
        this.stopAnimation();
        int n = this.currentFunctionNumber + 1;
        if (n >= this.functionDefs.length) {
            n = 0;
        }
        this.graphChoice.select(n);
        this.selectFunction(n);
    }
    
    public synchronized boolean action(final Event event, final Object o) {
        if (event.target instanceof TextField) {
            this.stopAnimation();
            if (event.target == this.xFunctionInput || event.target == this.yFunctionInput) {
                final String trim = this.xFunctionInput.getText().trim();
                final String trim2 = this.yFunctionInput.getText().trim();
                if (trim.equals("") && trim2.equals("")) {
                    this.nextFunction();
                    return true;
                }
            }
            if (this.checkData(event.target == this.xFunctionInput || event.target == this.yFunctionInput)) {
                super.canvas.invalidateCanvas();
            }
            return true;
        }
        if (event.target == this.graphChoice) {
            this.stopAnimation();
            this.selectFunction(this.graphChoice.getSelectedIndex());
            return true;
        }
        if (event.target == this.traceButton) {
            if (this.running) {
                this.stopAnimation();
            }
            else {
                this.startAnimation();
            }
            return true;
        }
        return super.action(event, o);
    }
    
    public synchronized boolean mouseDown(final Event event, final int n, final int n2) {
        if (event.target != super.canvas) {
            return true;
        }
        this.stopAnimation();
        if (event.metaDown()) {
            final Point location = super.canvas.location();
            final double[] zoomInOnPixel = this.coords.zoomInOnPixel(n - location.x, n2 - location.y);
            super.canvas.invalidateCanvas();
            if (zoomInOnPixel != null) {
                super.numberInput.setValues(zoomInOnPixel);
            }
        }
        else if ((event.modifiers & 0x8) != 0x0) {
            final Point location2 = super.canvas.location();
            final double[] zoomOutFromPixel = this.coords.zoomOutFromPixel(n - location2.x, n2 - location2.y);
            super.canvas.invalidateCanvas();
            if (zoomOutFromPixel != null) {
                super.numberInput.setValues(zoomOutFromPixel);
            }
        }
        return true;
    }
    
    public void aboutToHide() {
        this.stopAnimation();
        super.aboutToHide();
    }
    
    private synchronized void startAnimation() {
        if (this.running) {
            return;
        }
        super.canvas.invalidateCanvas();
        int currentFunctionNumber = this.currentFunctionNumber;
        while (this.graph[currentFunctionNumber] == null) {
            if (++currentFunctionNumber == 8) {
                currentFunctionNumber = 0;
            }
            if (currentFunctionNumber == this.currentFunctionNumber && !this.checkData(true)) {
                super.canvas.setErrorMessage("No curve has been graphed, so there is nothing to trace!");
                return;
            }
        }
        this.traceGraph = this.graph[currentFunctionNumber];
        if (currentFunctionNumber != this.currentFunctionNumber) {
            this.selectFunction(currentFunctionNumber);
            this.graphChoice.select(currentFunctionNumber);
            this.colorPatch.setBackground(this.graphColors[currentFunctionNumber]);
        }
        this.traceButton.setLabel("Stop");
        this.runner = new Thread(this);
        this.running = true;
        this.runner.start();
    }
    
    private void stopAnimation() {
        if (!this.running) {
            return;
        }
        synchronized (this) {
            if (this.runner.isAlive()) {
                this.running = false;
                this.notify();
                try {
                    this.wait(300L);
                }
                catch (InterruptedException ex) {}
                if (this.runner.isAlive()) {
                    this.runner.stop();
                    ((HiliteDisplayCanvas)super.canvas).crossHairOff();
                }
            }
        }
        this.running = false;
        this.traceButton.setLabel("Trace");
        this.runner = null;
        this.traceGraph = null;
    }
    
    public void run() {
        final int n = 120;
        final double n2 = this.parameterValues[4];
        final double n3 = (this.parameterValues[5] - n2) / n;
        for (int i = 0; i <= n; ++i) {
            final double n4 = n2 + i * n3;
            ((HiliteDisplayCanvas)super.canvas).crossHairAt(this.traceGraph.getX(n4), this.traceGraph.getY(n4), true);
            synchronized (this) {
                try {
                    this.wait(50L);
                }
                catch (InterruptedException ex) {}
            }
            if (!this.running) {
                break;
            }
        }
        synchronized (this) {
            ((HiliteDisplayCanvas)super.canvas).crossHairOff();
            this.traceButton.setLabel("Trace");
            this.running = false;
            this.traceGraph = null;
            this.notify();
        }
    }
}
