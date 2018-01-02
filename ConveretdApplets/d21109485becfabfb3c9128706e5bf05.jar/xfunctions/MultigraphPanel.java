// 
// Decompiled by Procyon v0.5.30
// 

package xfunctions;

import java.awt.Point;
import java.awt.Event;
import xfunctions.functions.Expression;
import java.awt.BorderLayout;
import java.awt.Label;
import java.awt.Component;
import java.awt.LayoutManager;
import java.awt.GridLayout;
import java.awt.Panel;
import java.awt.Button;
import xfunctions.graphs.NumberInputPanel;
import xfunctions.functions.ParseError;
import xfunctions.graphs.DisplayCanvas;
import xfunctions.graphs.Drawable;
import xfunctions.graphs.Axes;
import java.awt.Color;
import java.awt.Choice;
import java.awt.Canvas;
import java.awt.TextField;
import xfunctions.functions.Variable;
import xfunctions.graphs.Graph1D;
import xfunctions.graphs.CoordinateRect;
import xfunctions.functions.Parser;

public class MultigraphPanel extends GenericPanel
{
    Parser parser;
    CoordinateRect coords;
    Graph1D[] graph;
    Variable xVar;
    TextField functionInput;
    double[] parameterValues;
    String[] functionDefs;
    Canvas colorPatch;
    Choice graphChoice;
    int currentFunctionNumber;
    Color[] graphColors;
    
    public MultigraphPanel() {
        this((Parser)null);
    }
    
    public MultigraphPanel(final Parser parser) {
        this.graph = new Graph1D[8];
        this.parameterValues = new double[] { -5.0, 5.0, -5.0, 5.0 };
        this.functionDefs = new String[8];
        this.currentFunctionNumber = 0;
        this.graphColors = new Color[] { Color.magenta, new Color(0, 180, 0), Color.red, new Color(0, 200, 200), Color.orange, Color.gray, Color.blue, Color.pink };
        if (parser == null) {
            this.parser = new Parser();
        }
        else {
            this.parser = new Parser(parser);
        }
        this.xVar = this.parser.defineVariable("x");
        (this.coords = new CoordinateRect()).add(new Axes());
        (super.canvas = new DisplayCanvas()).setCoords(this.coords);
        Expression parse = null;
        try {
            parse = this.parser.parse("tan(x)");
        }
        catch (ParseError parseError) {}
        (this.graph[0] = new Graph1D(parse, this.xVar)).setGraphColor(this.graphColors[0]);
        this.functionDefs[0] = "tan(x)";
        this.coords.add(this.graph[0]);
        (super.numberInput = new NumberInputPanel(false)).addRealRange("x");
        super.numberInput.addRealRange("y");
        super.numberInput.setValues(this.parameterValues);
        super.buttons = new ButtonPanel(this, new Button[] { new Button("Graph!"), new Button("Remove"), new Button("Clear All") });
        (this.functionInput = new TextField(this.functionDefs[0])).setBackground(Color.white);
        (this.colorPatch = new Canvas()).setBackground(this.graphColors[0]);
        this.graphChoice = new Choice();
        for (int i = 1; i <= 8; ++i) {
            this.graphChoice.addItem("No. " + i);
        }
        final Panel panel = new Panel();
        panel.setLayout(new GridLayout(1, 2, 3, 3));
        panel.add(this.colorPatch);
        panel.add(new Label(" y = ", 2));
        final Panel panel2 = new Panel();
        panel2.setLayout(new GridLayout(1, 2, 3, 3));
        panel2.add(this.graphChoice);
        panel2.add(panel);
        final Panel panel3 = new Panel();
        panel3.setLayout(new BorderLayout(3, 3));
        panel3.add("West", panel2);
        panel3.add("Center", this.functionInput);
        final Panel panel4 = new Panel();
        panel4.setLayout(new GridLayout(3, 1, 3, 3));
        panel4.add(new Label("Select function number, enter a function of x, press return:"));
        panel4.add(panel3);
        panel4.add(super.buttons);
        this.setBackground(Color.lightGray);
        this.setLayout(new BorderLayout(3, 3));
        this.add("Center", super.canvas);
        this.add("South", panel4);
        this.add("West", super.numberInput);
        this.functionInput.requestFocus();
    }
    
    synchronized void installExample(final Object[] array) {
        final double[] array2 = (double[])array[2];
        super.numberInput.setValues(array2);
        this.coords.setLimits(array2);
        int n = 0;
        final String[] array3 = (String[])array[3];
        for (int i = 0; i < array3.length; ++i) {
            try {
                final Expression parse = this.parser.parse(array3[i]);
                if (this.graph[n] == null) {
                    (this.graph[n] = new Graph1D(parse, this.xVar)).setGraphColor(this.graphColors[n]);
                    this.coords.add(this.graph[n]);
                }
                else {
                    this.graph[n].setExpression(parse);
                }
                this.functionDefs[n] = array3[i];
                ++n;
            }
            catch (Exception ex) {}
        }
        for (int j = n; j < 8; ++j) {
            if (this.graph[j] != null) {
                this.graph[j].setExpression(null);
            }
            this.functionDefs[j] = null;
        }
        this.currentFunctionNumber = 0;
        this.graphChoice.select(0);
        this.colorPatch.setBackground(this.graphColors[0]);
        this.colorPatch.repaint();
        this.functionInput.setText(this.functionDefs[0]);
        this.functionInput.requestFocus();
        super.canvas.invalidateCanvas();
    }
    
    boolean doButtonCommand(final String s) {
        if ("Graph!".equals(s)) {
            if (this.checkData(true)) {
                super.canvas.invalidateCanvas();
            }
        }
        else if ("Remove".equals(s)) {
            if (this.graph[this.currentFunctionNumber] != null) {
                this.coords.remove(this.graph[this.currentFunctionNumber]);
                this.functionInput.setText("");
                this.functionDefs[this.currentFunctionNumber] = null;
                this.graph[this.currentFunctionNumber] = null;
                this.functionInput.requestFocus();
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
                this.functionDefs[i] = null;
            }
            this.graphChoice.select(0);
            this.currentFunctionNumber = 0;
            this.functionInput.setText("");
            this.functionInput.requestFocus();
            super.canvas.invalidateCanvas();
        }
        return true;
    }
    
    private void selectFunction(final int currentFunctionNumber) {
        if (currentFunctionNumber == this.currentFunctionNumber) {
            return;
        }
        if (!this.functionInput.getText().trim().equals("") && !this.checkData(true)) {
            this.graphChoice.select(this.currentFunctionNumber);
            return;
        }
        super.canvas.invalidateCanvas();
        this.currentFunctionNumber = currentFunctionNumber;
        if (this.functionDefs[currentFunctionNumber] != null) {
            this.functionInput.setText(this.functionDefs[currentFunctionNumber]);
        }
        else {
            this.functionInput.setText("");
        }
        this.colorPatch.setBackground(this.graphColors[currentFunctionNumber]);
        this.colorPatch.repaint();
        this.functionInput.selectAll();
        this.functionInput.requestFocus();
    }
    
    private boolean checkData(final boolean b) {
        final double[] values = super.numberInput.getValues(super.canvas);
        if (values == null) {
            return false;
        }
        final String trim = this.functionInput.getText().trim();
        if (b || trim.length() > 0) {
            Label_0228: {
                if (this.functionDefs[this.currentFunctionNumber] != null) {
                    if (trim.equals(this.functionDefs[this.currentFunctionNumber])) {
                        break Label_0228;
                    }
                }
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
                if (this.graph[this.currentFunctionNumber] == null) {
                    (this.graph[this.currentFunctionNumber] = new Graph1D(parse, this.xVar)).setGraphColor(this.graphColors[this.currentFunctionNumber]);
                    this.coords.add(this.graph[this.currentFunctionNumber]);
                }
                else {
                    this.graph[this.currentFunctionNumber].setExpression(parse);
                }
            }
            this.functionDefs[this.currentFunctionNumber] = trim;
        }
        this.coords.setLimits(values);
        return true;
    }
    
    private synchronized void nextFunction() {
        int n = this.currentFunctionNumber + 1;
        if (n >= this.functionDefs.length) {
            n = 0;
        }
        this.graphChoice.select(n);
        this.selectFunction(n);
    }
    
    public synchronized boolean action(final Event event, final Object o) {
        if (event.target instanceof TextField) {
            if (event.target == this.functionInput && this.functionInput.getText().trim().equals("")) {
                this.nextFunction();
                return true;
            }
            if (this.checkData(event.target == this.functionInput)) {
                super.canvas.invalidateCanvas();
                if (event.target == this.functionInput) {
                    this.functionInput.selectAll();
                    this.functionInput.requestFocus();
                }
            }
            return true;
        }
        else {
            if (event.target == this.graphChoice) {
                this.selectFunction(this.graphChoice.getSelectedIndex());
                return true;
            }
            return super.action(event, o);
        }
    }
    
    public synchronized boolean mouseDown(final Event event, final int n, final int n2) {
        if (event.target != super.canvas) {
            return true;
        }
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
}
