import edu.hws.jcm.draw.CoordinateRect;
import edu.hws.jcm.data.ParseError;
import java.util.StringTokenizer;
import edu.hws.jcm.awt.Tie;
import edu.hws.jcm.awt.Tieable;
import edu.hws.jcm.awt.Controller;
import java.awt.Component;
import java.awt.Label;
import java.awt.LayoutManager;
import java.awt.GridLayout;
import java.awt.Panel;
import java.awt.Font;
import edu.hws.jcm.draw.DrawString;
import edu.hws.jcm.data.ValueMath;
import edu.hws.jcm.draw.Drawable;
import edu.hws.jcm.draw.TangentLine;
import edu.hws.jcm.data.Value;
import edu.hws.jcm.draw.DrawGeometric;
import edu.hws.jcm.draw.DraggablePoint;
import java.awt.Color;
import edu.hws.jcm.draw.Graph1D;
import edu.hws.jcm.functions.WrapperFunction;
import edu.hws.jcm.data.Expression;
import edu.hws.jcm.data.SimpleFunction;
import java.util.Hashtable;
import edu.hws.jcm.awt.VariableInput;
import edu.hws.jcm.data.Function;

// 
// Decompiled by Procyon v0.5.30
// 

public class SecantTangent extends GenericGraphApplet
{
    private Function func;
    VariableInput x1Input;
    VariableInput x2Input;
    
    protected void setUpParameterDefaults() {
        (super.parameterDefaults = new Hashtable()).put("Function", " e ^ " + this.getParameter("Variable", "x"));
    }
    
    protected void setUpCanvas() {
        super.setUpCanvas();
        if (super.functionInput != null) {
            this.func = super.functionInput.getFunction(super.xVar);
        }
        else {
            this.func = new WrapperFunction(new SimpleFunction(super.parser.parse(this.getParameter("Function")), super.xVar));
        }
        final Graph1D graph1D = new Graph1D(this.func);
        graph1D.setColor(this.getColorParam("GraphColor", Color.black));
        final Color colorParam = this.getColorParam("TangentColor", Color.red);
        final Color colorParam2 = this.getColorParam("SecantColor", new Color(0, 200, 0));
        final DraggablePoint draggablePoint = new DraggablePoint();
        final DraggablePoint draggablePoint2 = new DraggablePoint();
        draggablePoint.clampY(this.func);
        draggablePoint2.clampY(this.func);
        draggablePoint.setColor(colorParam);
        draggablePoint.setGhostColor(this.lighten(colorParam));
        draggablePoint2.setColor(colorParam2);
        draggablePoint2.setGhostColor(this.lighten(colorParam2));
        final DrawGeometric drawGeometric = new DrawGeometric(1, draggablePoint.getXVar(), draggablePoint.getYVar(), draggablePoint2.getXVar(), draggablePoint2.getYVar());
        drawGeometric.setColor(colorParam2);
        final TangentLine tangentLine = new TangentLine(draggablePoint.getXVar(), this.func);
        tangentLine.setColor(colorParam);
        super.canvas.add(draggablePoint);
        super.canvas.add(draggablePoint2);
        super.canvas.add(tangentLine);
        super.canvas.add(drawGeometric);
        super.canvas.add(graph1D);
        final ValueMath valueMath = new ValueMath(this.func.derivative(1), draggablePoint.getXVar());
        final ValueMath valueMath2 = new ValueMath(new ValueMath(draggablePoint2.getYVar(), draggablePoint.getYVar(), '-'), new ValueMath(draggablePoint2.getXVar(), draggablePoint.getXVar(), '-'), '/');
        DrawString drawString;
        if ("no".equalsIgnoreCase(this.getParameter("ShowTangentSlope", "yes"))) {
            drawString = new DrawString("Secant Slope = #", 0, new Value[] { valueMath2 });
        }
        else {
            drawString = new DrawString("Secant Slope = #\nTangent Slope = #", 0, new Value[] { valueMath2, valueMath });
        }
        drawString.setFont(new Font("Monospaced", 0, 10));
        drawString.setNumSize(7);
        drawString.setColor(this.getColorParam("SlopeTextColor", Color.black));
        drawString.setBackgroundColor(this.getColorParam("SlopeTextBackground", Color.white));
        drawString.setFrameWidth(1);
        super.canvas.add(drawString);
        final Panel panel = new Panel();
        panel.setBackground(this.getColorParam("PanelColor", Color.lightGray));
        panel.setLayout(new GridLayout(1, 4, 3, 3));
        panel.add(new Label("Tangent at " + super.xVar.getName() + " = ", 2));
        panel.add(this.x1Input);
        panel.add(new Label("Secant to  " + super.xVar.getName() + " = ", 2));
        panel.add(this.x2Input);
        if (super.inputPanel == null) {
            super.mainPanel.add(panel, "South");
        }
        else {
            super.inputPanel.add(panel, "South");
        }
        final Controller controller = new Controller();
        super.mainController.remove(super.canvas);
        super.mainController.add(graph1D);
        super.mainController.add(controller);
        controller.add(this.x1Input);
        controller.add(this.x2Input);
        controller.add(draggablePoint);
        controller.add(draggablePoint2);
        controller.add(tangentLine);
        controller.add(drawGeometric);
        controller.add(drawString);
        draggablePoint.setOnUserAction(controller);
        draggablePoint2.setOnUserAction(controller);
        this.x1Input.setOnTextChange(controller);
        this.x2Input.setOnTextChange(controller);
        controller.add(new Tie((Tieable)draggablePoint.getXVar(), this.x1Input));
        controller.add(new Tie((Tieable)draggablePoint2.getXVar(), this.x2Input));
        final double[] numericParam = this.getNumericParam("X1");
        final double val = (numericParam != null && numericParam.length == 1) ? numericParam[0] : 0.0;
        this.x1Input.setVal(val);
        draggablePoint.setLocation(val, 0.0);
        final double[] numericParam2 = this.getNumericParam("X2");
        final double val2 = (numericParam2 != null && numericParam2.length == 1) ? numericParam2[0] : 1.0;
        this.x2Input.setVal(val2);
        draggablePoint2.setLocation(val2, 0.0);
    }
    
    private Color lighten(final Color color) {
        final int red = color.getRed();
        final int green = color.getGreen();
        final int blue = color.getBlue();
        int n;
        int n2;
        int n3;
        if (red <= 200 || green <= 200 || blue <= 200) {
            n = 255 - (255 - blue) / 3;
            n2 = 255 - (255 - green) / 3;
            n3 = 255 - (255 - red) / 3;
        }
        else {
            n = blue / 2;
            n2 = green / 2;
            n3 = red / 2;
        }
        return new Color(n3, n2, n);
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
                if (stringTokenizer.countTokens() > 0) {
                    try {
                        this.x1Input.setVal(new Double(stringTokenizer.nextToken()));
                    }
                    catch (NumberFormatException ex2) {}
                }
                if (stringTokenizer.countTokens() > 0) {
                    try {
                        this.x2Input.setVal(new Double(stringTokenizer.nextToken()));
                    }
                    catch (NumberFormatException ex3) {}
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
    
    public SecantTangent() {
        this.x1Input = new VariableInput();
        this.x2Input = new VariableInput();
    }
}
