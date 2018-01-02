import edu.hws.jcm.draw.CoordinateRect;
import edu.hws.jcm.data.ParseError;
import java.util.StringTokenizer;
import edu.hws.jcm.draw.DrawString;
import edu.hws.jcm.draw.Drawable;
import edu.hws.jcm.draw.DrawGeometric;
import edu.hws.jcm.data.ValueMath;
import edu.hws.jcm.functions.WrapperFunction;
import edu.hws.jcm.data.Expression;
import edu.hws.jcm.data.SimpleFunction;
import edu.hws.jcm.data.Value;
import edu.hws.jcm.data.Constant;
import java.awt.Label;
import edu.hws.jcm.awt.Tieable;
import edu.hws.jcm.awt.Tie;
import java.awt.Component;
import java.awt.Color;
import edu.hws.jcm.awt.JCMPanel;
import edu.hws.jcm.draw.Graph1D;
import edu.hws.jcm.data.Function;
import edu.hws.jcm.data.Variable;
import edu.hws.jcm.awt.Controller;
import edu.hws.jcm.awt.VariableSlider;
import edu.hws.jcm.awt.VariableInput;

// 
// Decompiled by Procyon v0.5.30
// 

public class EpsilonDelta extends GenericGraphApplet
{
    private VariableInput xInput;
    private VariableInput epsilonInput;
    private VariableInput deltaInput;
    private VariableInput limitInput;
    private VariableSlider xSlider;
    private VariableSlider epsilonSlider;
    private VariableSlider deltaSlider;
    private VariableSlider limitSlider;
    private Controller subController;
    private Variable xValue;
    private Variable limitValue;
    private Function func;
    private Graph1D graph;
    
    protected void setUpBottomPanel() {
        super.setUpBottomPanel();
        this.subController = new Controller();
        super.mainController.add(this.subController);
        final JCMPanel jcmPanel = new JCMPanel(3);
        this.subController.add(jcmPanel);
        jcmPanel.setBackground(this.getColorParam("PanelBackground", Color.lightGray));
        if (super.inputPanel == null) {
            super.mainPanel.add(jcmPanel, "South");
        }
        else {
            super.inputPanel.add(jcmPanel, "South");
        }
        final JCMPanel jcmPanel2 = new JCMPanel(0, 1, 2);
        final JCMPanel jcmPanel3 = new JCMPanel(0, 1, 2);
        final JCMPanel jcmPanel4 = new JCMPanel(0, 1, 2);
        jcmPanel.add(jcmPanel4, "Center");
        jcmPanel.add(jcmPanel2, "West");
        jcmPanel.add(jcmPanel3, "East");
        final double[] numericParam = this.getNumericParam("AValue");
        final double val = (numericParam == null || numericParam.length < 1) ? 0.0 : numericParam[0];
        if ("yes".equalsIgnoreCase(this.getParameter("UseAInput", "yes"))) {
            this.xSlider = new VariableSlider();
            (this.xInput = new VariableInput()).setVal(val);
            this.xSlider.setVal(val);
            this.xInput.setThrowErrors(false);
            this.subController.add(new Tie(this.xSlider, this.xInput));
            this.xValue = this.xInput.getVariable();
            jcmPanel2.add(new Label("limit at a = ", 2));
            jcmPanel4.add(this.xSlider);
            jcmPanel3.add(this.xInput);
        }
        else {
            (this.xValue = new Variable()).setVal(val);
        }
        final double[] numericParam2 = this.getNumericParam("LimitValue");
        final double val2 = (numericParam2 == null || numericParam2.length < 1) ? 1.0 : numericParam2[0];
        if ("yes".equalsIgnoreCase(this.getParameter("UseLimitInput", "yes"))) {
            this.limitSlider = new VariableSlider();
            (this.limitInput = new VariableInput()).setVal(val2);
            this.limitSlider.setVal(val2);
            this.limitInput.setThrowErrors(false);
            this.subController.add(new Tie(this.limitSlider, this.limitInput));
            this.limitValue = this.limitInput.getVariable();
            jcmPanel2.add(new Label(" test limit L = ", 2));
            jcmPanel4.add(this.limitSlider);
            jcmPanel3.add(this.limitInput);
        }
        else {
            (this.limitValue = new Variable()).setVal(val2);
        }
        final double[] numericParam3 = this.getNumericParam("EpsilonValue");
        final double n = (numericParam3 == null || numericParam3.length < 1) ? 0.25 : numericParam3[0];
        this.epsilonSlider = new VariableSlider(new Constant(0.0), new Constant(2.0));
        (this.epsilonInput = new VariableInput()).setVal(n);
        this.epsilonSlider.setVal(n);
        this.epsilonInput.setThrowErrors(false);
        this.subController.add(new Tie(this.epsilonSlider, this.epsilonInput));
        jcmPanel2.add(new Label("epsilon = ", 2));
        jcmPanel4.add(this.epsilonSlider);
        jcmPanel3.add(this.epsilonInput);
        final double[] numericParam4 = this.getNumericParam("DeltaValue");
        final double n2 = (numericParam4 == null || numericParam4.length < 1) ? 1.0 : numericParam4[0];
        this.deltaSlider = new VariableSlider(new Constant(0.0), new Constant(2.0));
        (this.deltaInput = new VariableInput()).setVal(n2);
        this.deltaSlider.setVal(n2);
        this.deltaInput.setThrowErrors(false);
        this.subController.add(new Tie(this.deltaSlider, this.deltaInput));
        jcmPanel2.add(new Label("delta = ", 2));
        jcmPanel4.add(this.deltaSlider);
        jcmPanel3.add(this.deltaInput);
    }
    
    protected void setUpCanvas() {
        if (super.functionInput != null) {
            this.func = super.functionInput.getFunction(super.xVar);
        }
        else {
            this.func = new WrapperFunction(new SimpleFunction(super.parser.parse(this.getParameter("Function", "abs(" + super.xVar.getName() + ") ^ " + super.xVar.getName())), super.xVar));
        }
        (this.graph = new Graph1D(this.func)).setColor(this.getColorParam("GraphColor", Color.black));
        final ValueMath valueMath = new ValueMath(this.xValue, this.deltaInput, '-');
        final ValueMath valueMath2 = new ValueMath(this.xValue, this.deltaInput, '+');
        final ValueMath valueMath3 = new ValueMath(this.limitValue, this.epsilonInput, '-');
        final ValueMath valueMath4 = new ValueMath(this.limitValue, this.epsilonInput, '+');
        final Value valueObject = super.canvas.getCoordinateRect().getValueObject(0);
        final Value valueObject2 = super.canvas.getCoordinateRect().getValueObject(1);
        final Value valueObject3 = super.canvas.getCoordinateRect().getValueObject(2);
        final Value valueObject4 = super.canvas.getCoordinateRect().getValueObject(3);
        if (this.xSlider != null) {
            this.xSlider.setMin(valueObject);
            this.xSlider.setMax(valueObject2);
        }
        if (this.limitSlider != null) {
            this.limitSlider.setMin(valueObject3);
            this.limitSlider.setMax(valueObject4);
        }
        final DrawGeometric drawGeometric = new DrawGeometric(2, valueMath, valueObject3, valueMath2, valueObject4);
        drawGeometric.setFillColor(new Color(225, 255, 225));
        drawGeometric.setLineWidth(0);
        final DrawGeometric drawGeometric2 = new DrawGeometric(2, valueObject, valueMath3, valueObject2, valueMath4);
        drawGeometric2.setFillColor(new Color(255, 230, 230));
        drawGeometric2.setLineWidth(0);
        final DrawGeometric drawGeometric3 = new DrawGeometric(2, valueMath, valueMath3, valueMath2, valueMath4);
        drawGeometric3.setFillColor(new Color(255, 255, 225));
        drawGeometric3.setColor(Color.yellow);
        final DrawGeometric drawGeometric4 = new DrawGeometric(0, this.xValue, valueObject3, this.xValue, valueObject4);
        drawGeometric4.setColor(new Color(130, 255, 130));
        final DrawGeometric drawGeometric5 = new DrawGeometric(0, valueObject, this.limitValue, valueObject2, this.limitValue);
        drawGeometric5.setColor(new Color(255, 150, 150));
        super.canvas.add(drawGeometric);
        super.canvas.add(drawGeometric2);
        super.canvas.add(drawGeometric3);
        super.canvas.add(drawGeometric4);
        super.canvas.add(drawGeometric5);
        final DrawString drawString = new DrawString("a = #\nL = #\nf(a) = #", 0, new Value[] { this.xValue, this.limitValue, new ValueMath(this.func, this.xValue) });
        drawString.setBackgroundColor(Color.white);
        drawString.setFrameWidth(1);
        this.subController.add(drawString);
        this.subController.add(drawGeometric);
        this.subController.add(drawGeometric2);
        this.subController.add(drawGeometric3);
        this.subController.add(drawGeometric4);
        this.subController.add(drawGeometric5);
        super.mainController.remove(super.canvas);
        super.mainController.add(this.graph);
        super.canvas.getCoordinateRect().setOnChange(super.mainController);
        this.deltaSlider.setOnUserAction(this.subController);
        this.epsilonSlider.setOnUserAction(this.subController);
        this.deltaInput.setOnTextChange(this.subController);
        this.epsilonInput.setOnTextChange(this.subController);
        this.subController.add(this.deltaSlider);
        this.subController.add(this.epsilonSlider);
        this.subController.add(this.deltaInput);
        this.subController.add(this.epsilonInput);
        if (this.xInput != null) {
            this.xSlider.setOnUserAction(this.subController);
            this.xInput.setOnTextChange(this.subController);
            this.subController.add(this.xSlider);
            this.subController.add(this.xInput);
        }
        if (this.limitInput != null) {
            this.limitSlider.setOnUserAction(this.subController);
            this.limitInput.setOnTextChange(this.subController);
            this.subController.add(this.limitSlider);
            this.subController.add(this.limitInput);
        }
        super.setUpCanvas();
        super.canvas.add(this.graph);
        super.canvas.add(drawString);
    }
    
    protected void doLoadExample(String substring) {
        final int index = substring.indexOf(";");
        final double[] limits = { -5.0, 5.0, -5.0, 5.0 };
        if (index > 0) {
            final String substring2 = substring.substring(index + 1);
            substring = substring.substring(0, index);
            final StringTokenizer stringTokenizer = new StringTokenizer(substring2, " ,");
            final double[] array = new double[stringTokenizer.countTokens()];
            for (int i = 0; i < array.length; ++i) {
                try {
                    array[i] = new Double(stringTokenizer.nextToken());
                }
                catch (Exception ex) {
                    array[i] = Double.NaN;
                }
            }
            for (int j = 0; j < 4; ++j) {
                if (array.length >= j && !Double.isNaN(array[j])) {
                    limits[j] = array[j];
                }
            }
            if (array.length > 4 && !Double.isNaN(array[4])) {
                this.xValue.setVal(array[4]);
            }
            else {
                this.xValue.setVal((limits[0] + limits[1]) / 2.0);
            }
            if (array.length > 5 && !Double.isNaN(array[5])) {
                this.limitValue.setVal(array[5]);
            }
            else {
                this.limitValue.setVal((limits[0] + limits[1]) / 2.0);
            }
            if (array.length > 8 && !Double.isNaN(array[8])) {
                this.epsilonSlider.setMax(new Constant(array[8]));
            }
            else {
                this.epsilonSlider.setMax(new Constant(Math.abs(limits[2] - limits[3]) / 2.0));
            }
            if (array.length > 9 && !Double.isNaN(array[9])) {
                this.deltaSlider.setMax(new Constant(array[9]));
            }
            else {
                this.deltaSlider.setMax(new Constant(Math.abs(limits[0] - limits[1]) / 2.0));
            }
            if (array.length > 6 && !Double.isNaN(array[6])) {
                this.epsilonInput.setVal(array[6]);
                this.epsilonSlider.setVal(array[6]);
            }
            else {
                this.epsilonInput.setVal(Math.abs(limits[2] - limits[3]) / 8.0);
                this.epsilonSlider.setVal(Math.abs(limits[2] - limits[3]) / 8.0);
            }
            if (array.length > 7 && !Double.isNaN(array[7])) {
                this.deltaInput.setVal(array[7]);
                this.deltaSlider.setVal(array[7]);
            }
            else {
                this.deltaInput.setVal(Math.abs(limits[0] - limits[1]) / 8.0);
                this.deltaSlider.setVal(Math.abs(limits[0] - limits[1]) / 8.0);
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
