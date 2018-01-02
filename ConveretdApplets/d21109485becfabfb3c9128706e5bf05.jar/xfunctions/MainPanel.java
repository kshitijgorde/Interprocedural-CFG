// 
// Decompiled by Procyon v0.5.30
// 

package xfunctions;

import java.awt.Point;
import xfunctions.functions.ExpressionFunction;
import xfunctions.functions.TableFunction;
import xfunctions.functions.BezierFunction;
import java.awt.Event;
import xfunctions.functions.Utilities;
import xfunctions.functions.StandardFunction;
import xfunctions.graphs.NumberInputPanel;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.Component;
import xfunctions.graphs.Drawable;
import xfunctions.graphs.Axes;
import xfunctions.functions.Expression;
import xfunctions.graphs.HiliteDisplayCanvas;
import java.awt.LayoutManager;
import java.awt.BorderLayout;
import java.applet.Applet;
import xfunctions.functions.Function;
import xfunctions.functions.MathSymbol;
import java.awt.TextField;
import java.awt.Label;
import java.awt.Panel;
import java.awt.Button;
import xfunctions.functions.Variable;
import xfunctions.functions.Parser;
import xfunctions.graphs.CoordinateRect;
import xfunctions.graphs.Graph1D;

class MainPanel extends GenericPanel
{
    xFunctionsPanel owner;
    FunctionList functions;
    Graph1D graph;
    CoordinateRect coords;
    Parser parser;
    Variable xVar;
    Button graphButton;
    Button tableButton;
    Button expressionButton;
    Button editButton;
    Button setButton;
    Panel displayPanel;
    Label functionOutputLabel;
    TextField functionInputField;
    MathSymbol currentDisplay;
    Function currentFunction;
    
    MainPanel(final xFunctionsPanel owner, final Parser parser, final String s, final Applet applet) {
        this.owner = owner;
        this.parser = new Parser(parser);
        this.xVar = this.parser.defineVariable("x");
        this.setLayout(new BorderLayout(5, 5));
        (this.displayPanel = new Panel()).setLayout(new BorderLayout(6, 6));
        this.coords = new CoordinateRect();
        super.canvas = new HiliteDisplayCanvas();
        this.graph = new Graph1D(null, this.xVar);
        ((HiliteDisplayCanvas)super.canvas).setGraph(this.graph);
        this.coords.add(new Axes());
        this.coords.add(this.graph);
        super.canvas.setCoords(this.coords);
        this.displayPanel.add("Center", super.canvas);
        final Panel panel = new Panel();
        panel.setLayout(new GridLayout(2, 1));
        panel.add(this.functionOutputLabel = new Label("", 1));
        final Panel panel2 = new Panel();
        panel2.add(new Label("x = "));
        panel2.add(this.functionInputField = new TextField(10));
        this.functionInputField.setBackground(Color.white);
        panel2.add(this.setButton = new Button("Set"));
        panel.add(panel2);
        this.displayPanel.add("South", panel);
        this.add("Center", this.displayPanel);
        final Panel panel3 = new Panel();
        panel3.setLayout(new BorderLayout(3, 3));
        (super.numberInput = new NumberInputPanel(false)).addRealRange("x");
        super.numberInput.addRealRange("y");
        panel3.add("Center", super.numberInput);
        panel3.add("South", super.buttons = new ButtonPanel(this, new Button[] { new Button("Restore") }, false));
        this.add("East", panel3);
        final Panel panel4 = new Panel();
        panel4.setLayout(new BorderLayout(3, 3));
        panel4.add("Center", this.functions = new FunctionList());
        final Panel panel5 = new Panel();
        panel5.setLayout(new GridLayout(4, 1));
        panel5.add(this.editButton = new Button("Edit"));
        panel5.add(this.expressionButton = new Button("New Expr."));
        panel5.add(this.graphButton = new Button("New Graph"));
        panel5.add(this.tableButton = new Button("New Table"));
        panel4.add("South", panel5);
        this.add("West", panel4);
    }
    
    void aboutToShow() {
        final MathSymbol selectedFunction = this.functions.getSelectedFunction();
        if (selectedFunction != this.currentDisplay) {
            this.setDisplayFunction(selectedFunction);
        }
        else if (this.currentDisplay != null) {
            final double[] array = new double[4];
            array[0] = (array[2] = -5.0);
            array[1] = (array[3] = 5.0);
            if (this.currentFunction != null) {
                array[0] = this.currentFunction.preferredXmin;
                array[1] = this.currentFunction.preferredXmax;
                array[2] = this.currentFunction.preferredYmin;
                array[3] = this.currentFunction.preferredYmax;
            }
            super.numberInput.setValues(array);
            this.coords.setLimits(array);
            this.graph.reset();
            super.canvas.invalidateCanvas();
        }
    }
    
    private void setDisplayFunction(final MathSymbol currentDisplay) {
        if (currentDisplay == this.currentDisplay) {
            return;
        }
        this.currentDisplay = currentDisplay;
        if (currentDisplay instanceof Function) {
            this.currentFunction = (Function)this.currentDisplay;
        }
        else {
            this.currentFunction = null;
        }
        try {
            this.graph.setExpression(this.parser.parse(String.valueOf(currentDisplay.getName()) + "(x)"));
        }
        catch (Exception ex) {
            this.currentDisplay = null;
            this.currentFunction = null;
            this.graph.setExpression(null);
            super.canvas.setErrorMessage(ex.toString());
        }
        if (this.currentDisplay == null) {
            this.functionOutputLabel.setText("?????");
        }
        else {
            this.setValueDisplay(Double.NaN);
        }
        if (this.currentDisplay == null || this.currentDisplay instanceof StandardFunction) {
            this.editButton.disable();
        }
        else {
            this.editButton.enable();
        }
        if (this.currentDisplay != null) {
            final double[] array = new double[4];
            array[0] = (array[2] = -5.0);
            array[1] = (array[3] = 5.0);
            if (this.currentFunction != null) {
                array[0] = this.currentFunction.preferredXmin;
                array[1] = this.currentFunction.preferredXmax;
                array[2] = this.currentFunction.preferredYmin;
                array[3] = this.currentFunction.preferredYmax;
            }
            this.coords.setLimits(array);
            super.numberInput.setValues(array);
        }
        super.canvas.invalidateCanvas();
    }
    
    boolean doButtonCommand(final String s) {
        if ("Restore".equals(s) && this.currentDisplay != null) {
            final double[] array = new double[4];
            array[0] = (array[2] = -5.0);
            array[1] = (array[3] = 5.0);
            if (this.currentFunction != null) {
                array[0] = this.currentFunction.preferredXmin;
                array[1] = this.currentFunction.preferredXmax;
                array[2] = this.currentFunction.preferredYmin;
                array[3] = this.currentFunction.preferredYmax;
            }
            this.coords.setLimits(array);
            super.numberInput.setValues(array);
            super.canvas.invalidateCanvas();
            return true;
        }
        return false;
    }
    
    void setFunctionOutputLabel(final double n) {
        super.canvas.clearErrorMessage();
        if (this.currentDisplay == null) {
            return;
        }
        if (Double.isNaN(n)) {
            this.functionOutputLabel.setText("Enter a value of x or click on graph.");
        }
        else {
            final String name = this.currentDisplay.getName();
            final String realToString = Utilities.realToString(n);
            String s;
            if (this.currentFunction != null) {
                s = Utilities.realToString(this.currentFunction.eval(n));
            }
            else {
                s = Utilities.realToString(((StandardFunction)this.currentDisplay).eval(n));
            }
            this.functionOutputLabel.setText(String.valueOf(name) + '(' + realToString + ") = " + s);
            ((HiliteDisplayCanvas)super.canvas).crossHairAt(n);
        }
    }
    
    void setValueDisplay(final double functionOutputLabel) {
        if (this.currentDisplay == null) {
            return;
        }
        if (Double.isNaN(functionOutputLabel)) {
            this.functionInputField.setText("");
        }
        else {
            this.functionInputField.setText(Utilities.realToString(functionOutputLabel));
        }
        this.setFunctionOutputLabel(functionOutputLabel);
    }
    
    public boolean action(final Event event, final Object o) {
        if (event.target == this.graphButton) {
            this.owner.showBezierInput(null);
            return true;
        }
        if (event.target == this.tableButton) {
            this.owner.showTableInput(null);
            return true;
        }
        if (event.target == this.expressionButton) {
            this.owner.showExpressionInput(null);
            return true;
        }
        if (event.target == this.editButton) {
            final Function function = (Function)this.functions.getSelectedFunction();
            if (function instanceof ExpressionFunction) {
                this.owner.showExpressionInput((ExpressionFunction)function);
            }
            else if (function instanceof BezierFunction) {
                this.owner.showBezierInput((BezierFunction)function);
            }
            else {
                this.owner.showTableInput((TableFunction)function);
            }
            return true;
        }
        if (event.target == this.functionInputField || event.target == this.setButton) {
            final String text = this.functionInputField.getText();
            Double n;
            try {
                n = new Double(text);
                if (n.isInfinite() || n.isNaN()) {
                    n = null;
                }
            }
            catch (NumberFormatException ex) {
                super.canvas.setErrorMessage("The value in the function input field is not a legal number.");
                n = null;
                this.functionInputField.selectAll();
                this.functionInputField.requestFocus();
            }
            if (n != null && this.currentDisplay != null) {
                this.setFunctionOutputLabel(n);
            }
            return true;
        }
        if (event.target instanceof TextField) {
            final double[] values = super.numberInput.getValues(super.canvas);
            if (values != null) {
                this.coords.setLimits(values);
                super.canvas.invalidateCanvas();
            }
            return true;
        }
        return super.action(event, o);
    }
    
    public boolean handleEvent(final Event event) {
        if (event.target == this.functions) {
            if (event.id == 701) {
                this.setDisplayFunction(this.functions.getSelectedFunction());
            }
            return true;
        }
        return super.handleEvent(event);
    }
    
    public boolean mouseDown(final Event event, final int n, final int n2) {
        if (event.target != super.canvas) {
            return true;
        }
        if (event.metaDown()) {
            final Point location = this.displayPanel.location();
            final double[] zoomInOnPixel = this.coords.zoomInOnPixel(n - location.x, n2 - location.y);
            super.canvas.invalidateCanvas();
            if (zoomInOnPixel != null) {
                super.numberInput.setValues(zoomInOnPixel);
            }
        }
        else if ((event.modifiers & 0x8) != 0x0) {
            final Point location2 = this.displayPanel.location();
            final double[] zoomOutFromPixel = this.coords.zoomOutFromPixel(n - location2.x, n2 - location2.y);
            super.canvas.invalidateCanvas();
            if (zoomOutFromPixel != null) {
                super.numberInput.setValues(zoomOutFromPixel);
            }
        }
        else {
            this.mouseDrag(event, n, n2);
        }
        return true;
    }
    
    public boolean mouseDrag(final Event event, final int n, final int n2) {
        if (event.metaDown() || (event.modifiers & 0x8) != 0x0) {
            return true;
        }
        if (event.target != super.canvas) {
            return true;
        }
        this.setValueDisplay(this.coords.pixelToX(n - this.displayPanel.location().x));
        return true;
    }
}
