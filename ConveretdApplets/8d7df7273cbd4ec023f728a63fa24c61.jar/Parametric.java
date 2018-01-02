import edu.hws.jcm.draw.CoordinateRect;
import edu.hws.jcm.data.ParseError;
import java.util.StringTokenizer;
import edu.hws.jcm.awt.Computable;
import edu.hws.jcm.awt.Controller;
import java.awt.event.ActionListener;
import java.awt.Button;
import edu.hws.jcm.data.Variable;
import java.awt.Panel;
import edu.hws.jcm.draw.Drawable;
import edu.hws.jcm.data.ValueMath;
import edu.hws.jcm.data.Constant;
import java.awt.Component;
import java.awt.Label;
import java.awt.Color;
import edu.hws.jcm.awt.JCMPanel;
import edu.hws.jcm.data.Value;
import edu.hws.jcm.functions.WrapperFunction;
import edu.hws.jcm.data.Expression;
import edu.hws.jcm.data.SimpleFunction;
import java.util.Hashtable;
import edu.hws.jcm.awt.ExpressionInput;
import edu.hws.jcm.awt.VariableInput;
import edu.hws.jcm.draw.Crosshair;
import edu.hws.jcm.awt.Animator;
import edu.hws.jcm.draw.ParametricCurve;
import edu.hws.jcm.data.Function;

// 
// Decompiled by Procyon v0.5.30
// 

public class Parametric extends GenericGraphApplet
{
    private Function xFunc;
    private Function yFunc;
    private ParametricCurve graph;
    private Animator tracer;
    private Crosshair crosshair;
    private VariableInput tMin;
    private VariableInput tMax;
    private VariableInput tIntervals;
    private ExpressionInput functionInput2;
    
    protected void setUpParameterDefaults() {
        (super.parameterDefaults = new Hashtable()).put("TwoLimitsColumns", "yes");
        super.parameterDefaults.put("Variable", "t");
        super.parameterDefaults.put("XName", "x");
        super.parameterDefaults.put("FunctionLabel", "  " + this.getParameter("XName") + "(" + this.getParameter("Variable") + ") = ");
        super.parameterDefaults.put("FunctionLabel2", "  " + this.getParameter("YName", "y") + "(" + this.getParameter("Variable") + ") = ");
    }
    
    protected void setUpCanvas() {
        super.setUpCanvas();
        if (super.functionInput != null) {
            this.xFunc = super.functionInput.getFunction(super.xVar);
            this.yFunc = this.functionInput2.getFunction(super.xVar);
        }
        else {
            final String string = " cos(" + super.xVar.getName() + ") + cos(3*" + super.xVar.getName() + ")";
            final String string2 = " sin(4*" + super.xVar.getName() + ") - sin(2*" + super.xVar.getName() + ")";
            final String parameter = this.getParameter("Function", string);
            final String parameter2 = this.getParameter("Function2", string2);
            this.xFunc = new WrapperFunction(new SimpleFunction(super.parser.parse(parameter), super.xVar));
            this.yFunc = new WrapperFunction(new SimpleFunction(super.parser.parse(parameter2), super.xVar));
        }
        this.graph = new ParametricCurve(this.xFunc, this.yFunc);
        final Color colorParam = this.getColorParam("CurveColor");
        if (colorParam != null) {
            this.graph.setColor(colorParam);
        }
        if ("no".equalsIgnoreCase(this.getParameter("UseParamInputs", "yes"))) {
            this.tMin = new VariableInput(String.valueOf(super.xVar.getName()) + "Start", this.getParameter("ParameterMin", "-2"));
            this.tMax = new VariableInput(String.valueOf(super.xVar.getName()) + "End", this.getParameter("ParameterMax", "2"));
            (this.tIntervals = new VariableInput("Intervals", this.getParameter("Intervals", "200"))).setInputStyle(2);
            this.tIntervals.setMin(1.0);
            this.tIntervals.setMax(5000.0);
            this.tMin.setOnUserAction(super.mainController);
            this.tMax.setOnUserAction(super.mainController);
            this.tIntervals.setOnUserAction(super.mainController);
            this.graph.setTMin(this.tMin);
            this.graph.setTMax(this.tMax);
            this.graph.setIntervals(this.tIntervals);
            if (super.limitsPanel != null) {
                super.mainController.add(this.tMin);
                super.mainController.add(this.tMax);
                super.mainController.add(this.tIntervals);
            }
            else {
                final JCMPanel jcmPanel = new JCMPanel(9, 0);
                jcmPanel.setBackground(this.getColorParam("PanelBackground", Color.lightGray));
                jcmPanel.add(new Label(this.tMin.getName()));
                jcmPanel.add(this.tMin);
                jcmPanel.add(new Label());
                jcmPanel.add(new Label(this.tMax.getName()));
                jcmPanel.add(this.tMax);
                jcmPanel.add(new Label());
                jcmPanel.add(new Label(this.tIntervals.getName()));
                jcmPanel.add(this.tIntervals);
                jcmPanel.add(new Label());
                super.mainPanel.add(jcmPanel, "East");
            }
        }
        else {
            try {
                this.graph.setTMin(new Constant(new Double(this.getParameter("ParameterMin", "-2"))));
                this.graph.setTMax(new Constant(new Double(this.getParameter("ParameterMax", "2"))));
                this.graph.setIntervals(new Constant(new Double(this.getParameter("Intervals", "25"))));
            }
            catch (NumberFormatException ex) {}
        }
        if (!"no".equalsIgnoreCase(this.getParameter("UseTracer", "yes"))) {
            (this.tracer = new Animator()).setMin(this.graph.getTMin());
            this.tracer.setMax(this.graph.getTMax());
            this.tracer.setUndefinedWhenNotRunning(true);
            this.tracer.setStartButtonName("Trace Curve!");
            final double[] numericParam = this.getNumericParam("TracerIntervals");
            int intervals;
            if (numericParam == null || numericParam.length != 1) {
                intervals = 100;
            }
            else {
                intervals = (int)Math.round(numericParam[0]);
            }
            if (intervals <= 0) {
                this.tracer.setIntervals(this.graph.getIntervals());
            }
            else {
                this.tracer.setIntervals(intervals);
            }
            final Variable valueAsVariable = this.tracer.getValueAsVariable();
            (this.crosshair = new Crosshair(new ValueMath(this.xFunc, valueAsVariable), new ValueMath(this.yFunc, valueAsVariable))).setLineWidth(3);
            this.crosshair.setColor(this.getColorParam("CrosshairColor", Color.gray));
            super.canvas.add(this.crosshair);
            if (super.inputPanel != null) {
                super.inputPanel.add(this.tracer, "West");
            }
            else if (super.limitsPanel == null) {
                final Panel panel = new Panel();
                panel.add(this.tracer);
                super.mainPanel.add(panel, "South");
            }
        }
        super.canvas.add(this.graph);
    }
    
    protected void setUpLimitsPanel() {
        super.setUpLimitsPanel();
        if (super.limitsPanel != null && this.tMin != null) {
            super.limitsPanel.addComponentPair(this.tMin, this.tMax);
            super.limitsPanel.addComponent(this.tIntervals);
        }
        if (super.inputPanel == null && this.tracer != null && super.limitsPanel != null) {
            super.limitsPanel.addComponent(this.tracer);
        }
    }
    
    protected void setUpBottomPanel() {
        if (!"no".equalsIgnoreCase(this.getParameter("UseFunctionInput", "yes"))) {
            (super.inputPanel = new JCMPanel()).setBackground(this.getColorParam("PanelBackground", Color.lightGray));
            final JCMPanel jcmPanel = new JCMPanel(2, 1);
            super.inputPanel.add(jcmPanel, "Center");
            if (!"no".equalsIgnoreCase(this.getParameter("UseComputeButton", "yes"))) {
                super.computeButton = new Button(this.getParameter("ComputeButtonName", "New Functions"));
                super.inputPanel.add(super.computeButton, "East");
                super.computeButton.addActionListener(this);
            }
            final String parameter = this.getParameter("Variable");
            String s = this.getParameter("Function");
            if (s == null) {
                s = "cos(" + parameter + ") + cos(3*" + parameter + ")";
            }
            super.functionInput = new ExpressionInput(s, super.parser);
            final String parameter2 = this.getParameter("FunctionLabel");
            if ("none".equalsIgnoreCase(parameter2)) {
                jcmPanel.add(super.functionInput);
            }
            else {
                final JCMPanel jcmPanel2 = new JCMPanel();
                jcmPanel2.add(super.functionInput, "Center");
                jcmPanel2.add(new Label(parameter2), "West");
                jcmPanel.add(jcmPanel2);
            }
            String s2 = this.getParameter("Function2");
            if (s2 == null) {
                s2 = "sin(4*" + parameter + ") - sin(2*" + parameter + ")";
            }
            this.functionInput2 = new ExpressionInput(s2, super.parser);
            final String parameter3 = this.getParameter("FunctionLabel2");
            if ("none".equalsIgnoreCase(parameter3)) {
                jcmPanel.add(this.functionInput2);
            }
            else {
                final JCMPanel jcmPanel3 = new JCMPanel();
                jcmPanel3.add(this.functionInput2, "Center");
                jcmPanel3.add(new Label(parameter3), "West");
                jcmPanel.add(jcmPanel3);
            }
            super.mainPanel.add(super.inputPanel, "South");
            super.functionInput.setOnUserAction(super.mainController);
            this.functionInput2.setOnUserAction(super.mainController);
        }
    }
    
    protected void setUpMainPanel() {
        super.setUpMainPanel();
        if (this.tracer == null) {
            return;
        }
        final Controller onChange = new Controller();
        onChange.add(this.tracer);
        onChange.add(this.crosshair);
        this.tracer.setOnChange(onChange);
    }
    
    protected void doLoadExample(String substring) {
        if (this.tracer != null) {
            this.tracer.stop();
        }
        final int index = substring.indexOf(";");
        if (index == -1) {
            return;
        }
        String text = substring.substring(index + 1);
        substring = substring.substring(0, index);
        final int index2 = text.indexOf(";");
        final double[] limits = { -5.0, 5.0, -5.0, 5.0 };
        if (index2 > 0) {
            final String substring2 = text.substring(index2 + 1);
            text = text.substring(0, index2);
            final StringTokenizer stringTokenizer = new StringTokenizer(substring2, " ,");
            if (stringTokenizer.countTokens() >= 4) {
                for (int i = 0; i < 4; ++i) {
                    try {
                        limits[i] = new Double(stringTokenizer.nextToken());
                    }
                    catch (NumberFormatException ex) {}
                }
            }
            if (stringTokenizer.hasMoreTokens()) {
                try {
                    final double doubleValue = new Double(stringTokenizer.nextToken());
                    if (this.tMin == null) {
                        this.graph.setTMin(new Constant(doubleValue));
                        if (this.tracer != null) {
                            this.tracer.setMin(doubleValue);
                        }
                    }
                    else {
                        this.tMin.setVal(doubleValue);
                    }
                }
                catch (NumberFormatException ex2) {}
            }
            if (stringTokenizer.hasMoreTokens()) {
                try {
                    final double doubleValue2 = new Double(stringTokenizer.nextToken());
                    if (this.tMax == null) {
                        this.graph.setTMax(new Constant(doubleValue2));
                        if (this.tracer != null) {
                            this.tracer.setMax(doubleValue2);
                        }
                    }
                    else {
                        this.tMax.setVal(doubleValue2);
                    }
                }
                catch (NumberFormatException ex3) {}
            }
            if (stringTokenizer.hasMoreTokens()) {
                try {
                    final int intervals = (int)Math.round(new Double(stringTokenizer.nextToken()));
                    if (this.tIntervals == null) {
                        if (this.tracer != null && this.tracer.getIntervals() == this.graph.getIntervals()) {
                            this.tracer.setIntervals(intervals);
                        }
                        this.graph.setIntervals(new Constant(intervals));
                    }
                    else {
                        this.tIntervals.setVal(intervals);
                    }
                }
                catch (NumberFormatException ex4) {}
            }
        }
        if (super.functionInput != null) {
            super.functionInput.setText(substring);
            this.functionInput2.setText(text);
        }
        else {
            try {
                ((WrapperFunction)this.xFunc).setFunction(new SimpleFunction(super.parser.parse(substring), super.xVar));
                ((WrapperFunction)this.yFunc).setFunction(new SimpleFunction(super.parser.parse(text), super.xVar));
            }
            catch (ParseError parseError) {}
        }
        final CoordinateRect coordinateRect = super.canvas.getCoordinateRect(0);
        coordinateRect.setLimits(limits);
        coordinateRect.setRestoreBuffer();
        super.mainController.compute();
    }
    
    public void stop() {
        if (this.tracer != null) {
            this.tracer.stop();
        }
        super.stop();
    }
}
