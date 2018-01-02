// 
// Decompiled by Procyon v0.5.30
// 

package xfunctions;

import java.awt.Event;
import xfunctions.functions.ParseError;
import xfunctions.functions.Expression;
import xfunctions.functions.Variable;
import java.awt.Label;
import java.awt.Color;
import java.awt.Button;
import java.awt.GridLayout;
import java.awt.Component;
import java.awt.LayoutManager;
import java.awt.BorderLayout;
import java.awt.Panel;
import xfunctions.graphs.NumberInputPanel;
import java.awt.Scrollbar;
import java.awt.CheckboxGroup;
import java.awt.Checkbox;
import java.awt.TextField;
import xfunctions.graphs.CoordinateRect;
import xfunctions.functions.Parser;

class Graph3DPanel extends GenericPanel
{
    Parser parser;
    CoordinateRect coords;
    Graph3DCanvas graphcanvas;
    TextField functionInput;
    double[] parameterValues;
    static final int WIREFRAME = 0;
    static final int HIDDEN_LINES_REMOVED = 1;
    static final int SHADED = 2;
    static final int SHADED_WITH_WIRES = 3;
    Checkbox[] styleCB;
    CheckboxGroup styleButtons;
    Scrollbar vScroll;
    Scrollbar hScroll;
    boolean clear;
    int currentStyle;
    
    public Graph3DPanel() {
        this((Parser)null);
    }
    
    public Graph3DPanel(final Parser parser) {
        this.parameterValues = new double[] { -2.0, 2.0, -2.0, 2.0, -4.0, 4.0, 20.0 };
        if (parser == null) {
            this.parser = new Parser();
        }
        else {
            this.parser = new Parser(parser);
        }
        final String s = "sin(x^2 + y^2)";
        final Variable defineVariable = this.parser.defineVariable("x");
        final Variable defineVariable2 = this.parser.defineVariable("y");
        this.coords = new CoordinateRect();
        this.graphcanvas = new Graph3DCanvas(defineVariable, defineVariable2);
        (super.canvas = this.graphcanvas).setCoords(this.coords);
        (super.numberInput = new NumberInputPanel()).addRealRange("x");
        super.numberInput.addRealRange("y");
        super.numberInput.addRealRange("z");
        super.numberInput.addSingleInteger("Grid Size", 8, 64);
        super.numberInput.setValues(this.parameterValues);
        this.vScroll = new Scrollbar(1, 20, 1, -80, 80);
        this.hScroll = new Scrollbar(0, -25, 1, -180, 180);
        this.vScroll.setPageIncrement(10);
        this.hScroll.setPageIncrement(15);
        final Panel panel = new Panel();
        panel.setLayout(new BorderLayout());
        panel.add("Center", super.canvas);
        panel.add("East", this.vScroll);
        panel.add("South", this.hScroll);
        final Panel panel2 = new Panel();
        panel2.setLayout(new GridLayout(1, 4));
        panel2.add(new Button("Graph it!"));
        panel2.add(new Button("Clear"));
        panel2.add(new Button("Zoom In"));
        panel2.add(new Button("Zoom Out"));
        (this.functionInput = new TextField(s)).setBackground(Color.white);
        this.styleButtons = new CheckboxGroup();
        (this.styleCB = new Checkbox[4])[0] = new Checkbox("Wire Frame Model", this.styleButtons, false);
        this.styleCB[1] = new Checkbox("No Hidden Lines", this.styleButtons, true);
        this.styleCB[2] = new Checkbox("Shaded Model", this.styleButtons, false);
        this.styleCB[3] = new Checkbox("Shaded, with Wires", this.styleButtons, false);
        final Panel panel3 = new Panel();
        panel3.setLayout(new GridLayout(2, 2));
        for (int i = 0; i < 4; ++i) {
            panel3.add(this.styleCB[i]);
        }
        final Panel panel4 = new Panel();
        panel4.setLayout(new GridLayout(2, 1));
        panel4.add(new Label("Enter a function of x and y:"));
        final Panel panel5 = new Panel();
        panel5.setLayout(new BorderLayout());
        panel5.add("West", new Label(" z = "));
        panel5.add("Center", this.functionInput);
        panel4.add(panel5);
        final Panel panel6 = new Panel();
        panel6.setLayout(new BorderLayout(5, 5));
        panel6.add("South", panel2);
        final Panel panel7 = new Panel();
        panel7.setLayout(new GridLayout(1, 2, 5, 5));
        panel7.add(panel4);
        panel7.add(panel3);
        panel6.add("Center", panel7);
        final Panel panel8 = new Panel();
        panel8.setLayout(new BorderLayout(5, 5));
        panel8.add("West", super.numberInput);
        panel8.add("Center", panel);
        this.setBackground(Color.lightGray);
        this.setLayout(new BorderLayout(5, 5));
        this.add("Center", panel8);
        this.add("South", panel6);
        this.clear = true;
        this.currentStyle = 1;
        this.graphcanvas.setLimits(this.parameterValues);
        this.graphcanvas.setRotation(-25, 20);
        this.functionInput.requestFocus();
    }
    
    synchronized void installExample(final Object[] array) {
        final double[] array2 = (double[])array[2];
        final String s = (String)array[3];
        this.functionInput.setText(s);
        this.doClear();
        super.numberInput.setValues(array2);
        this.graphcanvas.setLimits(array2);
        final int intValue = (int)array[4];
        this.graphcanvas.setStyle(intValue - 1);
        this.styleButtons.setCurrent(this.styleCB[intValue - 1]);
        this.functionInput.setText(s);
        this.functionInput.requestFocus();
    }
    
    public void aboutToShow() {
        super.aboutToShow();
        this.doClear();
    }
    
    private void doCompute() {
        this.graphcanvas.setExpression(null);
        this.clear = true;
        final double[] values = super.numberInput.getValues(super.canvas);
        if (values == null) {
            return;
        }
        final String trim = this.functionInput.getText().trim();
        if (trim == null || trim.length() == 0) {
            this.doClear();
            return;
        }
        Expression parse;
        try {
            parse = this.parser.parse(trim);
        }
        catch (ParseError parseError) {
            super.canvas.setErrorMessage("Your definition of z contains an error: " + parseError.getMessage());
            this.functionInput.select(parseError.errorPosition, parseError.errorPosition);
            this.functionInput.requestFocus();
            return;
        }
        this.graphcanvas.setLimits(values);
        this.graphcanvas.setExpression(parse);
        this.clear = false;
    }
    
    private void doClear() {
        if (super.canvas.getErrorMessage() == null) {
            this.graphcanvas.setExpression(null);
            this.graphcanvas.setRotation(-25, 20);
            this.hScroll.setValue(-25);
            this.vScroll.setValue(20);
            this.clear = true;
        }
        else {
            super.canvas.clearErrorMessage();
        }
    }
    
    private void doScroll() {
        this.graphcanvas.setRotation(this.hScroll.getValue(), this.vScroll.getValue());
    }
    
    private void doZoom(final boolean b) {
        final double xmin = this.graphcanvas.xmin;
        final double xmax = this.graphcanvas.xmax;
        final double ymin = this.graphcanvas.ymin;
        final double ymax = this.graphcanvas.ymax;
        final double zmin = this.graphcanvas.zmin;
        final double zmax = this.graphcanvas.zmax;
        double n;
        double n2;
        double n3;
        if (b) {
            n = (xmax - xmin) / 4.0;
            n2 = (ymax - ymin) / 4.0;
            n3 = (zmax - zmin) / 4.0;
        }
        else {
            n = xmax - xmin;
            n2 = ymax - ymin;
            n3 = zmax - zmin;
        }
        final double n4 = (xmin + xmax) / 2.0;
        final double n5 = (ymin + ymax) / 2.0;
        final double n6 = (zmin + zmax) / 2.0;
        final double n7 = n4 - n;
        final double n8 = n4 + n;
        final double n9 = n5 - n2;
        final double n10 = n5 + n2;
        final double n11 = n6 - n3;
        final double n12 = n6 + n3;
        super.numberInput.setValue(0, n7);
        super.numberInput.setValue(1, n8);
        super.numberInput.setValue(2, n9);
        super.numberInput.setValue(3, n10);
        super.numberInput.setValue(4, n11);
        super.numberInput.setValue(5, n12);
        this.graphcanvas.setLimits(new double[] { n7, n8, n9, n10, n11, n12 });
    }
    
    private void getStyle() {
        int n = -1;
        final Checkbox current = this.styleButtons.getCurrent();
        for (int i = 0; i < 4; ++i) {
            if (current == this.styleCB[i]) {
                n = i;
            }
        }
        if (n == -1 || n == this.currentStyle) {
            return;
        }
        this.currentStyle = n;
        this.graphcanvas.setStyle(n);
        if (!this.clear) {
            this.doCompute();
        }
    }
    
    public synchronized boolean action(final Event event, final Object o) {
        if (event.target instanceof TextField) {
            this.doCompute();
        }
        else if (event.target instanceof Button) {
            final String s = (String)o;
            if (o.equals("Graph it!")) {
                this.doCompute();
            }
            else if (o.equals("Clear")) {
                this.doClear();
            }
            else if (o.equals("Zoom In") || o.equals("Zoom Out")) {
                this.doZoom(o.equals("Zoom In"));
            }
        }
        else {
            if (!(event.target instanceof Checkbox)) {
                return super.action(event, o);
            }
            this.getStyle();
        }
        return true;
    }
    
    public synchronized boolean handleEvent(final Event event) {
        if (event.id == 601 || event.id == 602 || event.id == 603 || event.id == 604 || event.id == 605) {
            this.doScroll();
            return true;
        }
        return super.handleEvent(event);
    }
}
