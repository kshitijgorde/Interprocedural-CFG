import edu.hws.jcm.data.ParseError;
import java.util.StringTokenizer;
import edu.hws.jcm.draw.CoordinateRect;
import edu.hws.jcm.awt.Tieable;
import edu.hws.jcm.awt.Tie;
import edu.hws.jcm.awt.Controller;
import edu.hws.jcm.data.Constant;
import java.awt.Component;
import java.awt.Label;
import edu.hws.jcm.awt.JCMPanel;
import edu.hws.jcm.awt.DisplayLabel;
import edu.hws.jcm.data.Value;
import edu.hws.jcm.data.ValueMath;
import edu.hws.jcm.awt.VariableSlider;
import java.awt.Color;
import edu.hws.jcm.draw.Drawable;
import edu.hws.jcm.functions.WrapperFunction;
import edu.hws.jcm.data.Expression;
import edu.hws.jcm.data.SimpleFunction;
import edu.hws.jcm.draw.DrawGeometric;
import edu.hws.jcm.draw.Graph1D;
import edu.hws.jcm.data.Function;
import edu.hws.jcm.awt.VariableInput;

// 
// Decompiled by Procyon v0.5.30
// 

public class SimpleGraph extends GenericGraphApplet
{
    private VariableInput xInput;
    private Function func;
    private Graph1D graph;
    private DrawGeometric point;
    private DrawGeometric vLine;
    private DrawGeometric hLine;
    
    protected void setUpCanvas() {
        super.setUpCanvas();
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
        if (!"no".equalsIgnoreCase(this.getParameter("ShowPoint", "yes"))) {
            this.vLine = new DrawGeometric();
            this.hLine = new DrawGeometric();
            this.point = new DrawGeometric();
            super.canvas.add(this.vLine);
            super.canvas.add(this.hLine);
            super.canvas.add(this.point);
        }
        super.canvas.add(this.graph);
    }
    
    protected void setUpMainPanel() {
        super.setUpMainPanel();
        if ("no".equalsIgnoreCase(this.getParameter("ShowPoint", "yes"))) {
            return;
        }
        (this.xInput = new VariableInput()).setInputStyle(1);
        final CoordinateRect coordinateRect = super.canvas.getCoordinateRect();
        final VariableSlider variableSlider = new VariableSlider(coordinateRect.getValueObject(0), coordinateRect.getValueObject(1));
        final ValueMath valueMath = new ValueMath(this.func, variableSlider);
        final DisplayLabel displayLabel = new DisplayLabel(" y = #", valueMath);
        final JCMPanel jcmPanel = new JCMPanel(1, 3);
        jcmPanel.setBackground(this.getColorParam("PanelBackground", Color.lightGray));
        final JCMPanel jcmPanel2 = new JCMPanel();
        jcmPanel2.add(new Label(" " + this.getParameter("Variable", "x") + " = ", 1), "West");
        jcmPanel2.add(this.xInput, "Center");
        jcmPanel.add(variableSlider);
        jcmPanel.add(jcmPanel2);
        jcmPanel.add(displayLabel);
        if (super.inputPanel == null) {
            super.mainPanel.add(jcmPanel, "South");
        }
        else {
            super.inputPanel.setBackground(this.getColorParam("PanelBackground", Color.lightGray));
            super.inputPanel.add(jcmPanel, "South");
        }
        this.hLine.setPoints(new Constant(0.0), valueMath, variableSlider, valueMath);
        this.hLine.setPoints(new Constant(0.0), valueMath, variableSlider, valueMath);
        this.point.setShape(11);
        this.point.setPoints(variableSlider, valueMath, 5, 5);
        this.point.setLineWidth(3);
        this.vLine.setPoints(variableSlider, new Constant(0.0), variableSlider, valueMath);
        final Color colorParam = this.getColorParam("LineColor", Color.lightGray);
        this.vLine.setColor(colorParam);
        this.hLine.setColor(colorParam);
        this.point.setColor(this.getColorParam("DotColor", Color.gray));
        final Controller onChange = new Controller();
        this.xInput.setOnTextChange(onChange);
        variableSlider.setOnUserAction(onChange);
        coordinateRect.setOnChange(onChange);
        onChange.add(this.xInput);
        onChange.add(variableSlider);
        onChange.add(new Tie(variableSlider, this.xInput));
        onChange.add(this.hLine);
        onChange.add(this.vLine);
        onChange.add(this.point);
        onChange.add(displayLabel);
        super.mainController.add(onChange);
        super.mainController.remove(super.canvas);
        super.mainController.add(this.graph);
    }
    
    protected void doLoadExample(String substring) {
        final int index = substring.indexOf(";");
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
                if (stringTokenizer.countTokens() > 0 && this.xInput != null) {
                    try {
                        this.xInput.setVal(new Double(stringTokenizer.nextToken()));
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
