import edu.hws.jcm.draw.CoordinateRect;
import edu.hws.jcm.data.ParseError;
import java.util.StringTokenizer;
import java.awt.Component;
import java.awt.Label;
import java.awt.LayoutManager;
import java.awt.FlowLayout;
import edu.hws.jcm.awt.JCMPanel;
import java.awt.Color;
import edu.hws.jcm.draw.DrawString;
import edu.hws.jcm.draw.Drawable;
import edu.hws.jcm.data.Value;
import java.awt.event.ItemListener;
import edu.hws.jcm.functions.WrapperFunction;
import edu.hws.jcm.data.Expression;
import edu.hws.jcm.data.SimpleFunction;
import java.util.Hashtable;
import java.awt.event.ItemEvent;
import edu.hws.jcm.draw.RiemannSumRects;
import edu.hws.jcm.draw.Graph1D;
import edu.hws.jcm.data.Function;
import java.awt.Choice;
import edu.hws.jcm.awt.VariableInput;
import edu.hws.jcm.data.Variable;

// 
// Decompiled by Procyon v0.5.30
// 

public class RiemannSums extends GenericGraphApplet
{
    private Variable intervals;
    private VariableInput intCtInput;
    private Choice methodChoice;
    private Function func;
    private Graph1D graph;
    private RiemannSumRects sums;
    
    public void itemStateChanged(final ItemEvent itemEvent) {
        if (itemEvent.getSource() == this.methodChoice) {
            this.sums.setMethod(this.methodChoice.getSelectedIndex());
            super.mainController.compute();
        }
        else {
            super.itemStateChanged(itemEvent);
        }
    }
    
    protected void setUpParameterDefaults() {
        (super.parameterDefaults = new Hashtable()).put("Function", " 3 / (1 + " + this.getParameter("Variable", "x") + "^2)");
        super.parameterDefaults.put("ComputeButtonName", "Compute!");
    }
    
    protected void setUpCanvas() {
        if (super.functionInput != null) {
            this.func = super.functionInput.getFunction(super.xVar);
        }
        else {
            this.func = new WrapperFunction(new SimpleFunction(super.parser.parse(this.getParameter("Function", " abs(" + super.xVar.getName() + ") ^ " + super.xVar.getName())), super.xVar));
        }
        this.graph = new Graph1D(this.func);
        final Color colorParam = this.getColorParam("GraphColor");
        if (colorParam != null) {
            this.graph.setColor(colorParam);
        }
        double[] numericParam = this.getNumericParam("IntervalCount");
        if (numericParam == null || numericParam.length < 1) {
            numericParam = new double[] { 5.0 };
        }
        else if (Double.isNaN(numericParam[0]) || numericParam[0] < 1.0 || numericParam[0] > 5000.0) {
            numericParam[0] = 5.0;
        }
        final int n = (int)(numericParam[0] + 0.5);
        if ("yes".equalsIgnoreCase(this.getParameter("UseIntervalInput", "yes"))) {
            (this.intCtInput = new VariableInput(null, String.valueOf(n))).setInputStyle(2);
            this.intCtInput.setMin(1.0);
            this.intCtInput.setMax(5000.0);
            this.intervals = this.intCtInput.getVariable();
        }
        else {
            this.intervals = new Variable(null, n);
        }
        int method = 0;
        final String parameter = this.getParameter("Method");
        if (parameter != null && parameter.trim().length() > 0) {
            switch (parameter.trim().charAt(0)) {
                case 'L':
                case 'l': {
                    method = 0;
                    break;
                }
                case 'R':
                case 'r': {
                    method = 1;
                    break;
                }
                case 'M':
                case 'm': {
                    method = 2;
                    break;
                }
                case 'C':
                case 'c': {
                    method = 3;
                    break;
                }
                case 'I':
                case 'i': {
                    method = 4;
                    break;
                }
                case 'T':
                case 't': {
                    method = 5;
                    break;
                }
            }
        }
        if ("yes".equalsIgnoreCase(this.getParameter("UseMethodInput", "yes"))) {
            (this.methodChoice = new Choice()).add("Left Endpoint");
            this.methodChoice.add("Right Endpoint");
            this.methodChoice.add("Midpoint");
            this.methodChoice.add("~Circumscribed");
            this.methodChoice.add("~Inscribed");
            this.methodChoice.add("Trapezoid");
            this.methodChoice.select(method);
            this.methodChoice.addItemListener(this);
        }
        (this.sums = new RiemannSumRects(this.func, this.intervals)).setMethod(method);
        super.canvas.add(this.sums);
        final Color colorParam2 = this.getColorParam("RectColor");
        if (colorParam2 != null) {
            this.sums.setColor(colorParam2);
        }
        final Color colorParam3 = this.getColorParam("OutlineColor");
        if (colorParam3 != null) {
            this.sums.setOutlineColor(colorParam3);
        }
        super.setUpCanvas();
        super.canvas.getCoordinateRect().setGap(10);
        super.canvas.add(this.graph);
        final DrawString drawString = new DrawString("sum = #", 0, new Value[] { this.sums.getValueObject(-1) });
        drawString.setBackgroundColor(this.getColorParam("TextBackground", Color.white));
        drawString.setColor(this.getColorParam("TextColor", Color.black));
        drawString.setFrameWidth(1);
        super.canvas.add(drawString);
        super.mainController.add(drawString);
        super.mainController.add(this.sums);
        if (this.intCtInput != null) {
            this.intCtInput.setOnUserAction(super.mainController);
        }
        super.canvas.getCoordinateRect().setOnChange(super.mainController);
    }
    
    protected void setUpMainPanel() {
        super.setUpMainPanel();
        if (this.methodChoice == null && this.intCtInput == null) {
            return;
        }
        final JCMPanel jcmPanel = new JCMPanel();
        jcmPanel.setLayout(new FlowLayout());
        jcmPanel.setBackground(this.getColorParam("PanelBackground", Color.lightGray));
        if (this.intCtInput != null) {
            jcmPanel.add(new Label("Intervals:"));
            jcmPanel.add(this.intCtInput);
        }
        if (this.methodChoice != null) {
            jcmPanel.add(new Label("Method:"));
            jcmPanel.add(this.methodChoice);
        }
        if (super.inputPanel == null) {
            super.mainPanel.add(jcmPanel, "South");
        }
        else {
            super.inputPanel.setBackground(this.getColorParam("PanelBackground", Color.lightGray));
            super.inputPanel.add(jcmPanel, "South");
        }
    }
    
    protected void doLoadExample(String substring) {
        final int index = substring.indexOf(";");
        final double[] limits = { -5.0, 5.0, -5.0, 5.0 };
        if (index > 0) {
            String s = substring.substring(index + 1);
            substring = substring.substring(0, index);
            final int index2 = s.indexOf(";");
            if (index2 > 0) {
                final String trim = s.substring(index2 + 1).trim();
                s = s.substring(0, index2);
                if (trim.length() > 0) {
                    int method = 0;
                    switch (trim.charAt(0)) {
                        case 'L':
                        case 'l': {
                            method = 0;
                            break;
                        }
                        case 'R':
                        case 'r': {
                            method = 1;
                            break;
                        }
                        case 'M':
                        case 'm': {
                            method = 2;
                            break;
                        }
                        case 'C':
                        case 'c': {
                            method = 3;
                            break;
                        }
                        case 'I':
                        case 'i': {
                            method = 4;
                            break;
                        }
                        case 'T':
                        case 't': {
                            method = 5;
                            break;
                        }
                        default: {
                            method = -1;
                            break;
                        }
                    }
                    if (method >= 0) {
                        this.sums.setMethod(method);
                        if (this.methodChoice != null) {
                            this.methodChoice.select(method);
                        }
                    }
                }
            }
            final StringTokenizer stringTokenizer = new StringTokenizer(s, " ,");
            if (stringTokenizer.countTokens() >= 4) {
                for (int i = 0; i < 4; ++i) {
                    try {
                        limits[i] = new Double(stringTokenizer.nextToken());
                    }
                    catch (NumberFormatException ex) {}
                }
                if (stringTokenizer.countTokens() > 0) {
                    try {
                        double doubleValue = new Double(stringTokenizer.nextToken());
                        if (doubleValue < 1.0) {
                            doubleValue = 1.0;
                        }
                        else if (doubleValue > 5000.0) {
                            doubleValue = 5000.0;
                        }
                        this.intervals.setVal((int)(doubleValue + 0.5));
                    }
                    catch (NumberFormatException ex2) {}
                }
            }
        }
        if (super.functionInput != null) {
            super.functionInput.setText(substring);
        }
        else {
            try {
                ((WrapperFunction)this.func).setFunction(new SimpleFunction(super.parser.parse(substring), super.xVar));
            }
            catch (ParseError parseError) {}
        }
        final CoordinateRect coordinateRect = super.canvas.getCoordinateRect(0);
        coordinateRect.setLimits(limits);
        coordinateRect.setRestoreBuffer();
        super.mainController.compute();
    }
}
