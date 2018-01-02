import edu.hws.jcm.data.Function;
import edu.hws.jcm.draw.CoordinateRect;
import edu.hws.jcm.awt.ErrorReporter;
import edu.hws.jcm.awt.Tieable;
import edu.hws.jcm.awt.Tie;
import edu.hws.jcm.awt.Controller;
import edu.hws.jcm.draw.DrawBorder;
import edu.hws.jcm.draw.Axes;
import java.awt.Label;
import java.awt.Component;
import java.awt.Panel;
import java.awt.LayoutManager;
import java.awt.BorderLayout;
import edu.hws.jcm.awt.ComputeButton;
import java.awt.Font;
import edu.hws.jcm.draw.DrawString;
import java.awt.Color;
import edu.hws.jcm.draw.DrawGeometric;
import edu.hws.jcm.data.Constant;
import edu.hws.jcm.data.Value;
import edu.hws.jcm.data.ValueMath;
import edu.hws.jcm.awt.VariableSlider;
import edu.hws.jcm.awt.VariableInput;
import edu.hws.jcm.draw.Graph1D;
import edu.hws.jcm.awt.ExpressionInput;
import edu.hws.jcm.draw.LimitControlPanel;
import edu.hws.jcm.draw.Drawable;
import edu.hws.jcm.draw.Panner;
import edu.hws.jcm.data.MathObject;
import edu.hws.jcm.data.Variable;
import edu.hws.jcm.data.Parser;
import edu.hws.jcm.draw.DisplayCanvas;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class GraphApplet3 extends Applet
{
    private DisplayCanvas canvas;
    
    public void stop() {
        this.canvas.releaseResources();
    }
    
    public void init() {
        final Parser parser = new Parser();
        final Variable variable = new Variable("x");
        parser.add(variable);
        (this.canvas = new DisplayCanvas()).setHandleMouseZooms(true);
        this.canvas.add(new Panner());
        final CoordinateRect coordinateRect = this.canvas.getCoordinateRect();
        final LimitControlPanel limitControlPanel = new LimitControlPanel(33, false);
        limitControlPanel.addCoords(this.canvas);
        final ExpressionInput expressionInput = new ExpressionInput("sin(x)+2*cos(3*x)", parser);
        final Function function = expressionInput.getFunction(variable);
        final Graph1D graph1D = new Graph1D(function);
        final VariableInput variableInput = new VariableInput();
        final VariableSlider variableSlider = new VariableSlider(coordinateRect.getValueObject(0), coordinateRect.getValueObject(1));
        final ValueMath valueMath = new ValueMath(function, variableSlider);
        final DrawGeometric drawGeometric = new DrawGeometric(0, variableSlider, new Constant(0.0), variableSlider, valueMath);
        final DrawGeometric drawGeometric2 = new DrawGeometric(0, new Constant(0.0), valueMath, variableSlider, valueMath);
        final DrawGeometric drawGeometric3 = new DrawGeometric(10, variableSlider, valueMath, 3, 3);
        drawGeometric.setColor(Color.lightGray);
        drawGeometric2.setColor(Color.lightGray);
        drawGeometric3.setColor(Color.magenta);
        drawGeometric3.setFillColor(Color.magenta);
        final DrawString drawString = new DrawString("x = #\nf(x) = #", 0, new Value[] { variableSlider, valueMath });
        drawString.setFont(new Font("SansSerif", 1, 12));
        drawString.setColor(new Color(0, 100, 0));
        drawString.setOffset(10);
        final ComputeButton computeButton = new ComputeButton("Graph It!");
        this.setLayout(new BorderLayout(3, 3));
        this.setBackground(Color.lightGray);
        final Panel panel = new Panel();
        panel.setLayout(new BorderLayout(3, 3));
        final Panel panel2 = new Panel();
        panel2.setLayout(new BorderLayout(3, 3));
        this.add(this.canvas, "Center");
        this.add(limitControlPanel, "East");
        this.add(panel2, "South");
        this.add(panel, "North");
        panel.add(expressionInput, "Center");
        panel.add(new Label(" f(x) = "), "West");
        panel.add(computeButton, "East");
        panel2.add(variableSlider, "Center");
        panel2.add(variableInput, "East");
        panel2.add(new Label("  x = "), "West");
        this.canvas.add(new Axes());
        this.canvas.add(drawGeometric2);
        this.canvas.add(drawGeometric);
        this.canvas.add(drawGeometric3);
        this.canvas.add(graph1D);
        this.canvas.add(drawString);
        this.canvas.add(new DrawBorder(Color.darkGray, 2));
        final Controller onChange = new Controller();
        variableInput.setOnUserAction(onChange);
        variableSlider.setOnUserAction(onChange);
        coordinateRect.setOnChange(onChange);
        onChange.add(new Tie(variableSlider, variableInput));
        onChange.add(drawGeometric2);
        onChange.add(drawGeometric);
        onChange.add(drawGeometric3);
        onChange.add(drawString);
        onChange.add(variableInput);
        onChange.add(variableSlider);
        final Controller controller = new Controller();
        expressionInput.setOnUserAction(controller);
        computeButton.setOnUserAction(controller);
        controller.add(expressionInput);
        controller.add(graph1D);
        controller.add(onChange);
        controller.setErrorReporter(this.canvas);
        limitControlPanel.setErrorReporter(this.canvas);
    }
}
