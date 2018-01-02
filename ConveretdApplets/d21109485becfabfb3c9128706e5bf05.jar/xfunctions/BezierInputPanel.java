// 
// Decompiled by Procyon v0.5.30
// 

package xfunctions;

import java.awt.Event;
import xfunctions.graphs.DisplayCanvas;
import xfunctions.functions.MathSymbol;
import java.awt.Label;
import java.awt.BorderLayout;
import java.awt.LayoutManager;
import java.awt.GridLayout;
import java.awt.Component;
import java.awt.TextArea;
import java.awt.Panel;
import xfunctions.graphs.NumberInputPanel;
import xfunctions.graphs.Drawable;
import xfunctions.graphs.Axes;
import java.awt.Color;
import xfunctions.graphs.BezierEditDisplayCanvas;
import java.awt.TextField;
import xfunctions.functions.BezierFunction;
import xfunctions.graphs.CoordinateRect;
import xfunctions.functions.Parser;
import java.awt.Button;

class BezierInputPanel extends GenericPanel
{
    Button setLimitsButton;
    Button quitButton;
    Button clearButton;
    Button doneButton;
    Button insertPointButton;
    xFunctionsPanel owner;
    FunctionList functions;
    Parser parser;
    CoordinateRect coords;
    BezierFunction editFunction;
    BezierFunction saveFunction;
    TextField functionName;
    TextField insertPointInput;
    double[] limits;
    BezierEditDisplayCanvas canvas;
    
    BezierInputPanel(final xFunctionsPanel owner, final Parser parser, final FunctionList functions) {
        this.limits = new double[] { -5.0, 5.0, -5.0, 5.0 };
        this.setBackground(Color.lightGray);
        this.owner = owner;
        this.parser = parser;
        this.functions = functions;
        (this.coords = new CoordinateRect()).setGap(18);
        this.coords.add(new Axes());
        (this.canvas = new BezierEditDisplayCanvas()).setCoords(this.coords);
        (this.functionName = new TextField(10)).setBackground(Color.white);
        (super.numberInput = new NumberInputPanel()).addRealRange("x");
        super.numberInput.addRealRange("y");
        super.numberInput.setValues(this.limits);
        this.setLimitsButton = new Button("Set Axes Limits");
        this.quitButton = new Button("Cancel");
        this.clearButton = new Button("Clear");
        this.doneButton = new Button("Done");
        this.insertPointButton = new Button("Insert new point");
        (this.insertPointInput = new TextField("0", 6)).setBackground(Color.white);
        final Panel panel = new Panel();
        final TextArea textArea = new TextArea("BRIEF INSTRUCTIONS\n\nDrag point or handle\n   to modify graph.\nDouble-click or\n   control-click graph\n   to add a point.\nDouble-click or\n   control-click point\n   to remove it.\nRight-click or\n   shift-click a point\n   to toggle continuity\n   at that point.\nRight-click or\n   shift-click a handle\n   to toggle smoothness.\n", 10, 20);
        textArea.setEditable(false);
        textArea.setBackground(Color.white);
        panel.add(textArea);
        panel.setLayout(new GridLayout(2, 1, 5, 5));
        final Panel panel2 = new Panel();
        panel2.setLayout(new BorderLayout(10, 10));
        panel2.add("Center", super.numberInput);
        panel2.add("South", this.setLimitsButton);
        final Panel panel3 = new Panel();
        panel3.setLayout(new BorderLayout(5, 5));
        panel3.add("Center", this.insertPointButton);
        final Panel panel4 = new Panel();
        panel4.add(new Label("at x ="));
        panel4.add(this.insertPointInput);
        panel3.add("South", panel4);
        panel2.add("North", panel3);
        panel.add(panel2);
        final Panel panel5 = new Panel();
        panel5.setLayout(new BorderLayout(5, 5));
        final Panel panel6 = new Panel();
        panel6.add(new Label("Name of function:"));
        panel6.add(this.functionName);
        panel5.add("North", panel6);
        panel5.add("Center", this.canvas);
        final Panel panel7 = new Panel();
        panel7.setLayout(new GridLayout(1, 3, 3, 3));
        panel7.add(this.doneButton);
        panel7.add(this.quitButton);
        panel7.add(this.clearButton);
        panel5.add("South", panel7);
        this.setLayout(new BorderLayout(5, 5));
        this.add("East", panel);
        this.add("Center", panel5);
    }
    
    public void setFunction(final BezierFunction saveFunction) {
        if (saveFunction == null) {
            this.saveFunction = null;
        }
        else {
            this.saveFunction = saveFunction;
        }
    }
    
    public void aboutToShow() {
        if (this.saveFunction == null) {
            this.editFunction = new BezierFunction();
            int n;
            for (n = 1; this.parser.getSymbol("G" + n) != null; ++n) {}
            this.functionName.setText("G" + n);
            this.functionName.setEditable(true);
            this.functionName.selectAll();
            this.functionName.requestFocus();
        }
        else {
            this.editFunction = new BezierFunction(this.saveFunction);
            this.functionName.setEditable(false);
            this.functionName.setText(this.saveFunction.getName());
        }
        this.canvas.setFunction(this.editFunction);
        this.coords.setLimits(this.editFunction.preferredXmin, this.editFunction.preferredXmax, this.editFunction.preferredYmin, this.editFunction.preferredYmax);
        this.limits = this.coords.getLimits();
        super.numberInput.setValues(this.limits);
    }
    
    private void doCancel() {
        this.editFunction = null;
        this.saveFunction = null;
        this.canvas.clearErrorMessage();
        this.canvas.releaseResources();
        this.owner.backToMainPanel();
    }
    
    private void doClear() {
        if (this.canvas.getErrorMessage() != null) {
            this.canvas.clearErrorMessage();
            return;
        }
        this.coords.setLimits(-5.0, 5.0, -5.0, 5.0);
        this.limits = this.coords.getLimits();
        super.numberInput.setValues(this.limits);
        this.editFunction = new BezierFunction();
        this.canvas.setFunction(this.editFunction);
        this.canvas.invalidateCanvas();
    }
    
    private void doDone() {
        if (this.saveFunction != null) {
            this.saveFunction.stealDataFrom(this.editFunction);
        }
        else {
            final String trim = this.functionName.getText().trim();
            if (trim.equals("")) {
                this.canvas.setErrorMessage("You must enter a name for the function");
                this.functionName.requestFocus();
                return;
            }
            boolean b = true;
            if (!Character.isLetter(trim.charAt(0))) {
                b = false;
            }
            for (int n = 1; b && n < trim.length(); ++n) {
                if (!Character.isLetterOrDigit(trim.charAt(n))) {
                    b = false;
                }
            }
            if (!b) {
                this.canvas.setErrorMessage("Name must begin with a letter and consist entirely of letters and digits.");
                this.functionName.selectAll();
                this.functionName.requestFocus();
                return;
            }
            if (this.parser.getSymbol(trim) != null) {
                this.canvas.setErrorMessage("The name \"" + trim + "\" is already in use.  Choose another name.");
                this.functionName.selectAll();
                this.functionName.requestFocus();
                return;
            }
            this.editFunction.setName(trim);
            this.functions.addFunction(this.editFunction);
            this.functions.selectByName(trim);
            this.parser.getSymbolTable().add(this.editFunction);
        }
        this.editFunction = null;
        this.saveFunction = null;
        this.canvas.clearErrorMessage();
        this.canvas.releaseResources();
        this.owner.backToMainPanel();
    }
    
    private void doInsertPoint() {
        if (this.editFunction == null) {
            return;
        }
        final String text = this.insertPointInput.getText();
        double n;
        try {
            n = Integer.parseInt(text);
        }
        catch (NumberFormatException ex) {
            this.canvas.setErrorMessage("The data in the 'at x =' box is not a legal number.");
            return;
        }
        if (n <= this.limits[0] || n >= this.limits[1]) {
            this.canvas.setErrorMessage("The specified x-value is not with the limits " + this.limits[0] + " <= x <= " + this.limits[1] + ".");
            return;
        }
        if (this.editFunction.doNewPoint(n, this.editFunction.eval(n), this.coords.getPixelWidth(), this.coords.getPixelHeight())) {
            this.canvas.invalidateCanvas();
        }
        else {
            this.canvas.setErrorMessage("There is already a point at or too close to the specified x-value.");
        }
    }
    
    private boolean doSetLimits() {
        final double[] values = super.numberInput.getValues(this.canvas);
        if (values != null) {
            this.editFunction.rescale(this.limits, values);
            this.coords.setLimits(values);
            this.limits = values;
            this.canvas.invalidateCanvas();
            return true;
        }
        return false;
    }
    
    public boolean action(final Event event, final Object o) {
        if (event.target == this.setLimitsButton) {
            this.doSetLimits();
            return true;
        }
        if (event.target == this.quitButton) {
            this.doCancel();
            return true;
        }
        if (event.target == this.clearButton) {
            this.doClear();
            return true;
        }
        if (event.target == this.doneButton) {
            this.doDone();
            return true;
        }
        if (event.target == this.insertPointButton || event.target == this.insertPointInput) {
            this.doInsertPoint();
            return true;
        }
        if (event.target instanceof TextField) {
            this.doSetLimits();
            return true;
        }
        return super.action(event, o);
    }
}
