// 
// Decompiled by Procyon v0.5.30
// 

package xfunctions;

import java.awt.Event;
import xfunctions.functions.MathSymbol;
import xfunctions.functions.ParseError;
import java.awt.Insets;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Label;
import java.awt.Component;
import java.awt.BorderLayout;
import java.awt.LayoutManager;
import java.awt.GridLayout;
import java.awt.Panel;
import xfunctions.graphs.NumberInputPanel;
import xfunctions.graphs.DisplayCanvas;
import xfunctions.functions.Expression;
import xfunctions.graphs.Drawable;
import xfunctions.graphs.Axes;
import java.awt.Color;
import xfunctions.functions.ExpressionFunction;
import xfunctions.graphs.CoordinateRect;
import xfunctions.graphs.Graph1D;
import xfunctions.functions.Variable;
import xfunctions.functions.Parser;
import java.awt.Button;
import java.awt.TextField;

class ExpressionInputPanel extends GenericPanel
{
    private static final int inputCount = 8;
    TextField[] inputs;
    String[] inputStrings;
    TextField functionName;
    Button testButton;
    Button setLimitsButton;
    Button quitButton;
    Button clearButton;
    Button doneButton;
    xFunctionsPanel owner;
    FunctionList functions;
    Parser parser;
    Parser mainParser;
    Variable xVar;
    Graph1D graph;
    CoordinateRect coords;
    ExpressionFunction editFunction;
    double[] params;
    
    ExpressionInputPanel(final xFunctionsPanel owner, final Parser mainParser, final FunctionList functions) {
        this.inputs = new TextField[8];
        this.params = new double[] { -5.0, 5.0, -5.0, 5.0 };
        this.setBackground(Color.lightGray);
        this.owner = owner;
        this.functions = functions;
        this.mainParser = mainParser;
        this.parser = new Parser(this.mainParser);
        (this.coords = new CoordinateRect()).add(new Axes());
        this.xVar = this.parser.defineVariable("x");
        this.graph = new Graph1D(null, this.xVar);
        this.coords.add(this.graph);
        (super.canvas = new DisplayCanvas()).setCoords(this.coords);
        (super.numberInput = new NumberInputPanel()).addRealRange("x");
        super.numberInput.addRealRange("y");
        super.numberInput.setValues(this.params);
        final Panel panel = new Panel();
        panel.setLayout(new GridLayout(2, 1, 5, 5));
        final Panel panel2 = new Panel();
        panel2.setLayout(new BorderLayout());
        panel2.add("Center", super.numberInput);
        panel2.add("South", this.setLimitsButton = new Button("Set Axes Limits"));
        panel.add(panel2);
        panel.add(super.canvas);
        final Panel panel3 = new Panel();
        panel3.add(new Label("Name of function:"));
        panel3.add(this.functionName = new TextField(10));
        this.functionName.setBackground(Color.white);
        final Panel panel4 = new Panel();
        panel4.setLayout(new GridLayout(1, 3));
        panel4.add(this.quitButton = new Button("Cancel"));
        panel4.add(this.clearButton = new Button("Clear"));
        panel4.add(this.testButton = new Button("Test"));
        for (int i = 0; i < 8; ++i) {
            (this.inputs[i] = new TextField()).setBackground(Color.white);
        }
        final GridBagLayout layout = new GridBagLayout();
        this.setLayout(layout);
        final GridBagConstraints gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.insets = new Insets(3, 3, 3, 3);
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.weightx = 5.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = 1;
        layout.setConstraints(panel3, gridBagConstraints);
        this.add(panel3);
        gridBagConstraints.gridwidth = 1;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.fill = 2;
        for (int j = 0; j < 8; ++j) {
            gridBagConstraints.gridx = 0;
            gridBagConstraints.gridy = j + 1;
            gridBagConstraints.weightx = 0.0;
            Label label;
            if (j % 2 == 1) {
                label = new Label("provided: ", 2);
            }
            else if (j == 0) {
                label = new Label("Let y = ", 2);
            }
            else {
                label = new Label("or y = ", 2);
            }
            layout.setConstraints(label, gridBagConstraints);
            this.add(label);
            gridBagConstraints.gridx = 1;
            gridBagConstraints.weightx = 5.0;
            layout.setConstraints(this.inputs[j], gridBagConstraints);
            this.add(this.inputs[j]);
        }
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.weightx = 5.0;
        gridBagConstraints.weighty = 0.0;
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 9;
        gridBagConstraints.fill = 1;
        layout.setConstraints(this.doneButton = new Button("Done"), gridBagConstraints);
        this.add(this.doneButton);
        gridBagConstraints.gridy = 10;
        layout.setConstraints(panel4, gridBagConstraints);
        this.add(panel4);
        gridBagConstraints.insets = new Insets(3, 13, 3, 3);
        gridBagConstraints.gridheight = 11;
        gridBagConstraints.gridwidth = 1;
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.weightx = 1.8;
        layout.setConstraints(panel, gridBagConstraints);
        this.add(panel);
    }
    
    private Expression getExpression() {
        this.inputStrings = new String[8];
        for (int i = 0; i < 8; ++i) {
            this.inputStrings[i] = this.inputs[i].getText();
        }
        if (this.inputStrings[0] == null || this.inputStrings[0].trim().length() == 0) {
            super.canvas.setErrorMessage("You must at least fill in the first expression.  (Note: Click Cancel to quit.)");
            this.inputs[0].requestFocus();
            return null;
        }
        try {
            return this.parser.parseExpressionWithCases(this.inputStrings);
        }
        catch (ParseError parseError) {
            final int errorInStringNumber = parseError.errorInStringNumber;
            this.inputs[errorInStringNumber].select(parseError.errorPosition, parseError.errorPosition);
            this.inputs[errorInStringNumber].requestFocus();
            super.canvas.setErrorMessage(parseError.getMessage());
            return null;
        }
    }
    
    private void doDefineFunction() {
        final Expression expression = this.getExpression();
        if (expression == null) {
            return;
        }
        if (!this.doSetLimits()) {
            return;
        }
        ExpressionFunction editFunction;
        if (this.editFunction == null) {
            final String trim = this.functionName.getText().trim();
            if (trim.equals("")) {
                super.canvas.setErrorMessage("You must enter a name for the function");
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
                super.canvas.setErrorMessage("Name must begin with a letter and consist entirely of letters and digits.");
                this.functionName.selectAll();
                this.functionName.requestFocus();
                return;
            }
            if (this.mainParser.getSymbol(trim) != null) {
                super.canvas.setErrorMessage("The name \"" + trim + "\" is already in use.  Choose another name.");
                this.functionName.selectAll();
                this.functionName.requestFocus();
                return;
            }
            editFunction = new ExpressionFunction(trim, this.xVar, expression);
        }
        else {
            this.editFunction.setDefinition(expression, this.xVar);
            editFunction = this.editFunction;
        }
        editFunction.definitionStrings = this.inputStrings;
        editFunction.preferredXmin = this.params[0];
        editFunction.preferredXmax = this.params[1];
        editFunction.preferredYmin = this.params[2];
        editFunction.preferredYmax = this.params[3];
        if (this.editFunction == null) {
            this.functions.addFunction(editFunction);
            this.functions.selectByName(editFunction.getName());
            this.mainParser.getSymbolTable().add(editFunction);
        }
        this.editFunction = null;
        super.canvas.clearErrorMessage();
        super.canvas.releaseResources();
        this.owner.backToMainPanel();
    }
    
    private void doClear() {
        for (int i = 0; i < 8; ++i) {
            this.inputs[i].setText("");
        }
        this.inputStrings = null;
        this.params[0] = -5.0;
        this.params[1] = 5.0;
        this.params[2] = -5.0;
        this.params[3] = 5.0;
        this.coords.setLimits(this.params);
        super.numberInput.setValues(this.params);
        this.graph.setExpression(null);
        super.canvas.invalidateCanvas();
    }
    
    private void doTest() {
        final Expression expression = this.getExpression();
        if (expression == null) {
            return;
        }
        if (!this.doSetLimits()) {
            return;
        }
        this.graph.setExpression(expression);
        super.canvas.invalidateCanvas();
    }
    
    private void doCancel() {
        this.editFunction = null;
        this.graph.setExpression(null);
        super.canvas.clearErrorMessage();
        super.canvas.releaseResources();
        this.owner.backToMainPanel();
    }
    
    private boolean doSetLimits() {
        final double[] values = super.numberInput.getValues(super.canvas);
        if (values != null) {
            this.coords.setLimits(values);
            this.params = values;
            super.canvas.invalidateCanvas();
            return true;
        }
        return false;
    }
    
    void setFunction(final ExpressionFunction editFunction) {
        this.editFunction = editFunction;
    }
    
    void aboutToShow() {
        if (this.editFunction != null) {
            final ExpressionFunction editFunction = this.editFunction;
            for (int i = 0; i < 8; ++i) {
                this.inputs[i].setText(editFunction.definitionStrings[i]);
            }
            this.functionName.setText(editFunction.getName());
            this.functionName.setEditable(false);
            final double[] array = { editFunction.preferredXmin, editFunction.preferredXmax, editFunction.preferredYmin, editFunction.preferredYmax };
            this.coords.setLimits(array);
            super.numberInput.setValues(array);
            this.graph.setExpression(editFunction.getDefinition());
            super.canvas.invalidateCanvas();
            this.functionName.setEditable(false);
        }
        else {
            this.doClear();
            int n;
            for (n = 1; this.mainParser.getSymbol("F" + n) != null; ++n) {}
            this.functionName.setText("F" + n);
            this.functionName.setEditable(true);
            this.functionName.selectAll();
            this.functionName.requestFocus();
        }
    }
    
    public boolean action(final Event event, final Object o) {
        if (event.target instanceof TextField) {
            if (event.target == this.functionName) {
                return true;
            }
            for (int i = 0; i < 8; ++i) {
                if (event.target == this.inputs[i]) {
                    this.inputs[(i + 1) % 8].requestFocus();
                    return true;
                }
            }
            this.doSetLimits();
            return true;
        }
        else {
            if (event.target == this.testButton) {
                this.doTest();
                return true;
            }
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
                this.doDefineFunction();
                return true;
            }
            return super.action(event, o);
        }
    }
}
