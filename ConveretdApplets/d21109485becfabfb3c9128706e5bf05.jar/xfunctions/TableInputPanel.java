// 
// Decompiled by Procyon v0.5.30
// 

package xfunctions;

import xfunctions.functions.MathSymbol;
import java.awt.Event;
import xfunctions.functions.Utilities;
import java.awt.Label;
import java.awt.FlowLayout;
import java.awt.Component;
import java.awt.GridLayout;
import java.awt.Panel;
import java.awt.LayoutManager;
import java.awt.BorderLayout;
import java.awt.Font;
import xfunctions.graphs.NumberInputPanel;
import xfunctions.functions.Expression;
import xfunctions.graphs.Drawable;
import xfunctions.graphs.Axes;
import java.awt.Color;
import xfunctions.functions.TableFunction;
import java.awt.CheckboxGroup;
import java.awt.Checkbox;
import java.awt.List;
import java.awt.TextField;
import xfunctions.functions.Variable;
import xfunctions.graphs.Graph1D;
import xfunctions.graphs.DisplayCanvas;
import xfunctions.graphs.CoordinateRect;
import xfunctions.functions.Parser;
import java.awt.Button;

class TableInputPanel extends GenericPanel
{
    Button setLimitsButton;
    Button quitButton;
    Button clearButton;
    Button doneButton;
    Button deletePointButton;
    Button insertPointButton;
    xFunctionsPanel owner;
    FunctionList functions;
    Parser parser;
    CoordinateRect coords;
    DisplayCanvas canvas;
    Graph1D graph;
    Variable xVar;
    double[] limits;
    TextField xInput;
    TextField yInput;
    List pointList;
    Checkbox stepStyle;
    Checkbox linearStyle;
    Checkbox smoothStyle;
    CheckboxGroup styleButtons;
    TextField functionName;
    TableFunction editFunction;
    TableFunction saveFunction;
    int selectedItem;
    private static final String blanks = "            ";
    
    TableInputPanel(final xFunctionsPanel owner, final Parser parser, final FunctionList functions) {
        this.limits = new double[] { -5.0, 5.0, -5.0, 5.0 };
        this.setBackground(Color.lightGray);
        this.owner = owner;
        this.parser = parser;
        this.functions = functions;
        (this.coords = new CoordinateRect()).add(new Axes());
        (this.canvas = new DisplayCanvas()).setCoords(this.coords);
        this.xVar = new Variable("x");
        this.graph = new Graph1D(null, this.xVar);
        this.coords.add(this.graph);
        (this.functionName = new TextField(10)).setBackground(Color.white);
        (super.numberInput = new NumberInputPanel()).addRealRange("x");
        super.numberInput.addRealRange("y");
        super.numberInput.setValues(this.limits);
        this.styleButtons = new CheckboxGroup();
        this.stepStyle = new Checkbox("Step Function", this.styleButtons, true);
        this.linearStyle = new Checkbox("Piecewise Linear Function", this.styleButtons, false);
        this.smoothStyle = new Checkbox("Smooth Function", this.styleButtons, false);
        this.setLimitsButton = new Button("Set Axes Limits");
        this.quitButton = new Button("Cancel");
        this.clearButton = new Button("Clear");
        this.doneButton = new Button("Done");
        this.deletePointButton = new Button("Delete Selected Pt.");
        this.insertPointButton = new Button("Add (x,y) to Table");
        (this.xInput = new TextField(12)).setBackground(Color.white);
        (this.yInput = new TextField(12)).setBackground(Color.white);
        (this.pointList = new List()).setBackground(Color.white);
        this.pointList.setFont(new Font("Courier", 0, 12));
        this.setLayout(new BorderLayout(7, 7));
        final Panel panel = new Panel();
        panel.setLayout(new GridLayout(1, 4, 3, 3));
        panel.add(this.doneButton);
        panel.add(this.quitButton);
        panel.add(this.clearButton);
        panel.add(this.setLimitsButton);
        this.add("South", panel);
        final Panel panel2 = new Panel();
        panel2.setLayout(new GridLayout(1, 2, 5, 5));
        final Panel panel3 = new Panel();
        final Panel panel4 = new Panel();
        panel2.add(panel3);
        panel2.add(panel4);
        this.add("Center", panel2);
        panel4.setLayout(new GridLayout(2, 1, 5, 5));
        panel4.add(this.pointList);
        panel4.add(this.canvas);
        panel3.setLayout(new FlowLayout(1, 10000, 0));
        final Panel panel5 = new Panel();
        panel3.add(panel5);
        final Panel panel6 = new Panel();
        panel3.add(new Label(" "));
        panel3.add(panel6);
        panel3.add(super.numberInput);
        panel5.setLayout(new BorderLayout(5, 5));
        final Panel panel7 = new Panel();
        panel7.setLayout(new FlowLayout(1));
        panel7.add(new Label("Name of Function: "));
        panel7.add(this.functionName);
        panel5.add("North", panel7);
        final Panel panel8 = new Panel();
        panel8.setLayout(new GridLayout(2, 2, 3, 3));
        panel8.add(new Label("Input x:"));
        panel8.add(new Label("Input y:"));
        panel8.add(this.xInput);
        panel8.add(this.yInput);
        panel5.add("Center", panel8);
        final Panel panel9 = new Panel();
        panel9.setLayout(new GridLayout(1, 2, 5, 5));
        panel9.add(this.insertPointButton);
        panel9.add(this.deletePointButton);
        panel5.add("South", panel9);
        panel6.setLayout(new GridLayout(3, 1, 0, 0));
        panel6.add(this.stepStyle);
        panel6.add(this.linearStyle);
        panel6.add(this.smoothStyle);
    }
    
    void setFunction(final TableFunction saveFunction) {
        this.saveFunction = saveFunction;
    }
    
    public void aboutToShow() {
        if (this.saveFunction == null) {
            this.editFunction = new TableFunction();
            int n;
            for (n = 1; this.parser.getSymbol("T" + n) != null; ++n) {}
            this.functionName.setText("T" + n);
            this.functionName.setEditable(true);
            this.functionName.selectAll();
            this.functionName.requestFocus();
        }
        else {
            this.editFunction = new TableFunction(this.saveFunction);
            this.functionName.setEditable(false);
            this.functionName.setText(this.saveFunction.getName());
        }
        this.makePointList(this.editFunction);
        this.graph.setExpression(this.editFunction.getFunctionExpression(this.xVar));
        this.coords.setLimits(this.editFunction.preferredXmin, this.editFunction.preferredXmax, this.editFunction.preferredYmin, this.editFunction.preferredYmax);
        this.limits = this.coords.getLimits();
        super.numberInput.setValues(this.limits);
        switch (this.editFunction.getStyle()) {
            case 0: {
                this.styleButtons.setCurrent(this.stepStyle);
                break;
            }
            case 1: {
                this.styleButtons.setCurrent(this.linearStyle);
                break;
            }
            case 2: {
                this.styleButtons.setCurrent(this.smoothStyle);
                break;
            }
        }
        this.canvas.invalidateCanvas();
    }
    
    private void makePointList(final TableFunction tableFunction) {
        this.pointList.clear();
        final int pointCount = tableFunction.getPointCount();
        for (int i = 0; i < pointCount; ++i) {
            this.pointList.addItem(this.makePointString(tableFunction.getX(i), tableFunction.getY(i)));
        }
        if (pointCount > 1) {
            this.pointList.select(0);
            this.selectPoint(0);
        }
        else {
            this.xInput.setText("");
            this.yInput.setText("");
            this.xInput.requestFocus();
            this.selectedItem = -1;
        }
    }
    
    private void selectPoint(final int selectedItem) {
        this.selectedItem = selectedItem;
        final int pointCount = this.editFunction.getPointCount();
        if (selectedItem < -1 || selectedItem >= pointCount) {
            return;
        }
        this.xInput.setText(Utilities.realToString(this.editFunction.getX(selectedItem)));
        this.yInput.setText(Utilities.realToString(this.editFunction.getY(selectedItem)));
        this.yInput.selectAll();
        this.yInput.requestFocus();
    }
    
    private void getPointFromInput() {
        final String trim = this.xInput.getText().trim();
        final String trim2 = this.yInput.getText().trim();
        if (trim == null || trim.length() == 0 || trim2 == null || trim2.length() == 0) {
            this.canvas.setErrorMessage("Please enter values for x and y before adding (x,y) to the list of points.");
            return;
        }
        double doubleValue;
        try {
            final Double n = new Double(trim);
            if (n.isInfinite()) {
                throw new NumberFormatException();
            }
            doubleValue = n;
        }
        catch (NumberFormatException ex) {
            this.canvas.setErrorMessage("The value in the x-input box is not a legal real number.");
            this.xInput.selectAll();
            this.xInput.requestFocus();
            return;
        }
        double doubleValue2;
        try {
            final Double n2 = new Double(trim2);
            if (n2.isInfinite()) {
                throw new NumberFormatException();
            }
            doubleValue2 = n2;
        }
        catch (NumberFormatException ex2) {
            this.canvas.setErrorMessage("The value in the y-input box is not a legal real number.");
            this.yInput.selectAll();
            this.yInput.requestFocus();
            return;
        }
        if (this.selectedItem >= 0 && this.selectedItem < this.pointList.countItems()) {
            this.pointList.deselect(this.selectedItem);
        }
        this.selectedItem = -1;
        int selectedItem = this.editFunction.changeY(doubleValue, doubleValue2);
        if (selectedItem >= 0) {
            this.pointList.replaceItem(this.makePointString(doubleValue, doubleValue2), selectedItem);
        }
        else {
            selectedItem = this.editFunction.newPoint(doubleValue, doubleValue2);
            this.pointList.addItem(this.makePointString(doubleValue, doubleValue2), selectedItem);
        }
        this.pointList.select(selectedItem);
        this.selectedItem = selectedItem;
        if (this.editFunction.checkLimits()) {
            this.limits[0] = this.editFunction.preferredXmin;
            this.limits[1] = this.editFunction.preferredXmax;
            this.limits[2] = this.editFunction.preferredYmin;
            this.limits[3] = this.editFunction.preferredYmax;
            super.numberInput.setValues(this.limits);
            this.coords.setLimits(this.limits);
        }
        this.xInput.selectAll();
        this.xInput.requestFocus();
        this.graph.reset();
        this.canvas.invalidateCanvas();
    }
    
    private void deletePoint() {
        final int selectedItem = this.selectedItem;
        final int pointCount = this.editFunction.getPointCount();
        if (selectedItem < 0 || selectedItem >= pointCount) {
            this.canvas.setErrorMessage("You must select a point in the list before you can delete it.");
            return;
        }
        this.editFunction.deletePoint(selectedItem);
        this.pointList.delItem(selectedItem);
        this.selectedItem = -1;
        this.graph.reset();
        this.canvas.invalidateCanvas();
    }
    
    private String makePointString(final double n, final double n2) {
        String s = Utilities.realToString(n);
        String s2 = Utilities.realToString(n2);
        if (s.length() < 11) {
            s = String.valueOf("            ".substring(0, 11 - s.length())) + s;
        }
        if (s2.length() < 11) {
            s2 = String.valueOf("            ".substring(0, 11 - s2.length())) + s2;
        }
        return String.valueOf(s) + " " + s2;
    }
    
    public boolean handleEvent(final Event event) {
        if (event.id == 701) {
            if (this.selectedItem >= 0 && this.selectedItem < this.pointList.countItems()) {
                this.pointList.deselect(this.selectedItem);
            }
            final int intValue = (int)event.arg;
            if (intValue >= 0 && intValue < this.pointList.countItems()) {
                this.selectPoint(intValue);
            }
            return true;
        }
        return super.handleEvent(event);
    }
    
    private boolean doSetLimits() {
        final double[] values = super.numberInput.getValues(this.canvas);
        if (values != null) {
            this.editFunction.preferredXmin = values[0];
            this.editFunction.preferredXmax = values[1];
            this.editFunction.preferredYmin = values[2];
            this.editFunction.preferredYmax = values[3];
            this.coords.setLimits(values);
            this.limits = values;
            this.canvas.invalidateCanvas();
            return true;
        }
        return false;
    }
    
    private void setGraphStyle() {
        final Checkbox current = this.styleButtons.getCurrent();
        if (current == this.stepStyle) {
            if (this.editFunction.getStyle() == 0) {
                return;
            }
            this.editFunction.setStyle(0);
        }
        else if (current == this.linearStyle) {
            if (this.editFunction.getStyle() == 1) {
                return;
            }
            this.editFunction.setStyle(1);
        }
        else if (current == this.smoothStyle) {
            if (this.editFunction.getStyle() == 2) {
                return;
            }
            this.editFunction.setStyle(2);
        }
        this.graph.reset();
        this.canvas.invalidateCanvas();
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
        this.pointList.clear();
        this.coords.setLimits(-5.0, 5.0, -5.0, 5.0);
        this.limits = this.coords.getLimits();
        super.numberInput.setValues(this.limits);
        this.styleButtons.setCurrent(this.stepStyle);
        this.editFunction = new TableFunction();
        this.xInput.setText("");
        this.yInput.setText("");
        this.xInput.requestFocus();
        this.graph.setExpression(this.editFunction.getFunctionExpression(this.xVar));
        this.canvas.invalidateCanvas();
    }
    
    private void doDone() {
        if (this.editFunction.getPointCount() < 2) {
            this.canvas.setErrorMessage("Function must have at least two points.  (Note: Use Cancel button to quit.)");
            return;
        }
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
        if (event.target == this.insertPointButton || event.target == this.xInput || event.target == this.yInput) {
            this.getPointFromInput();
            return true;
        }
        if (event.target instanceof TextField && event.target != this.functionName) {
            this.doSetLimits();
            return true;
        }
        if (event.target == this.deletePointButton) {
            this.deletePoint();
            return true;
        }
        if (event.target == this.smoothStyle || event.target == this.linearStyle || event.target == this.stepStyle) {
            this.setGraphStyle();
            return true;
        }
        return super.action(event, o);
    }
}
