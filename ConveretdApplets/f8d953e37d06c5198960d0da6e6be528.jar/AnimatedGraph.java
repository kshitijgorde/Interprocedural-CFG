import edu.hws.jcm.draw.CoordinateRect;
import edu.hws.jcm.data.ParseError;
import java.util.StringTokenizer;
import edu.hws.jcm.draw.DrawString;
import edu.hws.jcm.awt.Computable;
import java.awt.Label;
import edu.hws.jcm.awt.JCMPanel;
import edu.hws.jcm.data.Value;
import edu.hws.jcm.draw.Drawable;
import java.awt.Color;
import edu.hws.jcm.data.Expression;
import edu.hws.jcm.data.SimpleFunction;
import java.awt.Component;
import java.util.Hashtable;
import edu.hws.jcm.data.MathObject;
import edu.hws.jcm.awt.VariableInput;
import edu.hws.jcm.data.Variable;
import edu.hws.jcm.awt.Animator;
import edu.hws.jcm.draw.Graph1D;
import edu.hws.jcm.data.Function;

// 
// Decompiled by Procyon v0.5.30
// 

public class AnimatedGraph extends GenericGraphApplet
{
    private Function func;
    private Graph1D graph;
    private Animator animator;
    private Variable kVar;
    private VariableInput kMin;
    private VariableInput kMax;
    private VariableInput kIntervals;
    
    protected void setUpParser() {
        int n = 133;
        if (!"no".equalsIgnoreCase(this.getParameter("UseNextAndPrev", "yes"))) {
            n |= 0x30;
        }
        this.animator = new Animator(n);
        this.kVar = this.animator.getValueAsVariable(this.getParameter("Parameter", "k"));
        super.parser.add(this.kVar);
        super.setUpParser();
        (super.parameterDefaults = new Hashtable()).put("Function", super.xVar.getName() + " / (" + this.kVar.getName() + " - " + super.xVar.getName() + "^2)");
        if (!"no".equalsIgnoreCase(this.getParameter("UseAnimatorInputs"))) {
            super.parameterDefaults.put("TwoLimitsColumns", "yes");
        }
    }
    
    protected void setUpBottomPanel() {
        super.setUpBottomPanel();
        if (super.inputPanel != null) {
            super.inputPanel.add(this.animator, "South");
        }
        else {
            super.mainPanel.add(this.animator, "South");
        }
    }
    
    protected void setUpCanvas() {
        super.setUpCanvas();
        if (super.functionInput != null) {
            this.func = super.functionInput.getFunction(super.xVar);
        }
        else {
            this.func = new SimpleFunction(super.parser.parse(this.getParameter("Function")), super.xVar);
        }
        (this.graph = new Graph1D(this.func)).setColor(this.getColorParam("GraphColor", Color.magenta));
        super.canvas.add(this.graph);
        if (!"no".equalsIgnoreCase(this.getParameter("UseAnimatorInputs"))) {
            this.kMin = new VariableInput(this.kVar.getName() + "Start", this.getParameter("ParameterMin", "-2"));
            this.kMax = new VariableInput(this.kVar.getName() + "End", this.getParameter("ParameterMax", "2"));
            (this.kIntervals = new VariableInput("Intervals", this.getParameter("Intervals", "25"))).setInputStyle(2);
            this.kIntervals.setMin(1.0);
            this.kIntervals.setMax(1000.0);
            this.kMin.setOnUserAction(super.mainController);
            this.kMax.setOnUserAction(super.mainController);
            this.kIntervals.setOnUserAction(super.mainController);
            this.animator.setMin(this.kMin);
            this.animator.setMax(this.kMax);
            this.animator.setIntervals(this.kIntervals);
            if (super.limitsPanel != null) {
                super.mainController.add(this.kMin);
                super.mainController.add(this.kMax);
                super.mainController.add(this.kIntervals);
            }
            else {
                final JCMPanel jcmPanel = new JCMPanel(9, 0);
                jcmPanel.setBackground(this.getColorParam("PanelBackground", Color.lightGray));
                jcmPanel.add(new Label(this.kMin.getName()));
                jcmPanel.add(this.kMin);
                jcmPanel.add(new Label());
                jcmPanel.add(new Label(this.kMax.getName()));
                jcmPanel.add(this.kMax);
                jcmPanel.add(new Label());
                jcmPanel.add(new Label(this.kIntervals.getName()));
                jcmPanel.add(this.kIntervals);
                jcmPanel.add(new Label());
                super.mainPanel.add(jcmPanel, "East");
            }
        }
        else {
            try {
                this.animator.setMin(new Double(this.getParameter("ParameterMin", "-2")));
                this.animator.setMax(new Double(this.getParameter("ParameterMax", "2")));
                this.animator.setIntervals((int)Math.round(new Double(this.getParameter("Intervals", "25"))));
            }
            catch (NumberFormatException ex) {}
        }
        this.animator.setOnChange(super.mainController);
        if (!"no".equalsIgnoreCase(this.getParameter("ShowParameter", "yes"))) {
            final DrawString drawString = new DrawString(this.kVar.getName() + " = #", 8, new Value[] { this.kVar });
            drawString.setBackgroundColor(super.canvas.getBackground());
            drawString.setColor(this.getColorParam("ParameterColor", Color.black));
            super.canvas.add(drawString);
        }
    }
    
    protected void setUpLimitsPanel() {
        super.setUpLimitsPanel();
        if (super.limitsPanel != null && this.kMin != null) {
            super.limitsPanel.addComponentPair(this.kMin, this.kMax);
            super.limitsPanel.addComponent(this.kIntervals);
        }
    }
    
    protected void doLoadExample(String substring) {
        this.animator.stop();
        final int index = substring.indexOf(";");
        int n = 0;
        final double[] limits = { -5.0, 5.0, -5.0, 5.0 };
        if (index > 0) {
            final String substring2 = substring.substring(index + 1);
            substring = substring.substring(0, index);
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
                    if (this.kMin == null) {
                        this.animator.setMin(doubleValue);
                    }
                    else {
                        this.kMin.setVal(doubleValue);
                    }
                }
                catch (NumberFormatException ex2) {}
            }
            if (stringTokenizer.hasMoreTokens()) {
                try {
                    final double doubleValue2 = new Double(stringTokenizer.nextToken());
                    if (this.kMax == null) {
                        this.animator.setMax(doubleValue2);
                    }
                    else {
                        this.kMax.setVal(doubleValue2);
                    }
                }
                catch (NumberFormatException ex3) {}
            }
            if (stringTokenizer.hasMoreTokens()) {
                try {
                    final int intervals = (int)Math.round(new Double(stringTokenizer.nextToken()));
                    if (this.kIntervals == null) {
                        this.animator.setIntervals(intervals);
                    }
                    else {
                        this.kIntervals.setVal(intervals);
                    }
                }
                catch (NumberFormatException ex4) {}
            }
            if (stringTokenizer.hasMoreTokens()) {
                try {
                    this.animator.setLoopStyle((int)Math.round(new Double(stringTokenizer.nextToken())));
                }
                catch (NumberFormatException ex5) {}
            }
            if (stringTokenizer.hasMoreTokens()) {
                try {
                    n = (((int)Math.round(new Double(stringTokenizer.nextToken())) == 1) ? 1 : 0);
                }
                catch (NumberFormatException ex6) {}
            }
        }
        if (super.functionInput != null) {
            super.functionInput.setText(substring);
        }
        else {
            try {
                this.func = new SimpleFunction(super.parser.parse(substring), super.xVar);
                this.graph.setFunction(this.func);
            }
            catch (ParseError parseError) {}
        }
        final CoordinateRect coordinateRect = super.canvas.getCoordinateRect(0);
        coordinateRect.setLimits(limits);
        coordinateRect.setRestoreBuffer();
        super.mainController.compute();
        if (n != 0) {
            try {
                synchronized (this) {
                    this.wait(250L);
                }
            }
            catch (InterruptedException ex7) {}
            this.animator.start();
        }
    }
    
    public void stop() {
        this.animator.stop();
        super.stop();
    }
}
