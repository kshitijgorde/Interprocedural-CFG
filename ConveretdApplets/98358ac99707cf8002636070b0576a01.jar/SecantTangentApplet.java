import java.awt.Insets;
import edu.hws.jcm.data.Function;
import edu.hws.jcm.awt.Tie;
import edu.hws.jcm.awt.Tieable;
import edu.hws.jcm.awt.Controller;
import java.awt.FlowLayout;
import java.awt.Label;
import java.awt.Component;
import java.awt.LayoutManager;
import java.awt.BorderLayout;
import java.awt.Panel;
import edu.hws.jcm.draw.Axes;
import edu.hws.jcm.draw.Drawable;
import edu.hws.jcm.draw.Grid;
import edu.hws.jcm.awt.ErrorReporter;
import edu.hws.jcm.draw.LimitControlPanel;
import edu.hws.jcm.draw.CoordinateRect;
import java.awt.Font;
import edu.hws.jcm.draw.DrawString;
import edu.hws.jcm.data.ValueMath;
import edu.hws.jcm.draw.TangentLine;
import edu.hws.jcm.data.Value;
import edu.hws.jcm.draw.DrawGeometric;
import edu.hws.jcm.draw.DraggablePoint;
import java.awt.Color;
import edu.hws.jcm.draw.Graph1D;
import edu.hws.jcm.awt.ExpressionInput;
import edu.hws.jcm.awt.VariableInput;
import edu.hws.jcm.awt.ComputeButton;
import edu.hws.jcm.data.MathObject;
import edu.hws.jcm.data.Variable;
import edu.hws.jcm.data.Parser;
import edu.hws.jcm.draw.DisplayCanvas;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class SecantTangentApplet extends Applet
{
    DisplayCanvas canvas;
    
    public void stop() {
        this.canvas.releaseResources();
    }
    
    public void init() {
        final Parser parser = new Parser();
        final Variable variable = new Variable("x");
        parser.add(variable);
        final ComputeButton computeButton = new ComputeButton("New Function");
        final VariableInput variableInput = new VariableInput();
        final VariableInput variableInput2 = new VariableInput();
        final ExpressionInput expressionInput = new ExpressionInput(" sqrt(x)", parser);
        final Function function = expressionInput.getFunction(variable);
        final Graph1D graph1D = new Graph1D(function);
        graph1D.setColor(Color.black);
        final DraggablePoint draggablePoint = new DraggablePoint();
        final DraggablePoint draggablePoint2 = new DraggablePoint();
        draggablePoint.clampY(function);
        draggablePoint2.clampY(function);
        draggablePoint.setLocation(1.0, 0.0);
        draggablePoint2.setLocation(3.0, 0.0);
        draggablePoint.setColor(Color.red);
        draggablePoint.setGhostColor(Color.pink);
        draggablePoint2.setColor(new Color(0, 200, 0));
        draggablePoint2.setGhostColor(new Color(180, 225, 180));
        final DrawGeometric drawGeometric = new DrawGeometric(1, draggablePoint.getXVar(), draggablePoint.getYVar(), draggablePoint2.getXVar(), draggablePoint2.getYVar());
        drawGeometric.setColor(new Color(0, 200, 0));
        final TangentLine tangentLine = new TangentLine(draggablePoint.getXVar(), function);
        tangentLine.setColor(Color.red);
        final DrawString drawString = new DrawString("Tangent Slope = #\n Secant Slope = #", 0, new Value[] { new ValueMath(function.derivative(1), draggablePoint.getXVar()), new ValueMath(new ValueMath(draggablePoint2.getYVar(), draggablePoint.getYVar(), '-'), new ValueMath(draggablePoint2.getXVar(), draggablePoint.getXVar(), '-'), '/') });
        drawString.setFont(new Font("Monospaced", 0, 10));
        drawString.setBackgroundColor(Color.lightGray);
        drawString.setFrameWidth(1);
        this.canvas = new DisplayCanvas(new CoordinateRect(-1.0, 5.0, -1.0, 3.0));
        final LimitControlPanel limitControlPanel = new LimitControlPanel(47, false);
        limitControlPanel.addCoords(this.canvas);
        limitControlPanel.setBackground(Color.lightGray);
        limitControlPanel.setErrorReporter(this.canvas);
        this.canvas.add(new Grid());
        this.canvas.add(new Axes());
        this.canvas.add(draggablePoint);
        this.canvas.add(draggablePoint2);
        this.canvas.add(tangentLine);
        this.canvas.add(drawGeometric);
        this.canvas.add(graph1D);
        this.canvas.add(drawString);
        final Panel panel = new Panel();
        panel.setLayout(new BorderLayout(3, 3));
        final Panel panel2 = new Panel();
        panel2.setBackground(Color.lightGray);
        panel2.setLayout(new BorderLayout(3, 3));
        panel2.add(expressionInput, "Center");
        panel2.add(computeButton, "East");
        panel2.add(new Label(" f(x) = "), "West");
        panel.add(panel2, "North");
        final Panel panel3 = new Panel();
        panel3.setLayout(new FlowLayout(1, 5, 5));
        panel3.setBackground(Color.lightGray);
        panel3.add(new Label("Tangent at x = "));
        panel3.add(variableInput);
        panel3.add(new Label("  Secant to x = "));
        panel3.add(variableInput2);
        panel.add(panel3, "Center");
        this.setLayout(new BorderLayout(3, 3));
        this.setBackground(Color.darkGray);
        this.add(this.canvas, "Center");
        this.add(limitControlPanel, "East");
        this.add(panel, "South");
        final Controller controller = new Controller();
        final Controller controller2 = new Controller();
        controller.add(expressionInput);
        controller.add(graph1D);
        controller.add(controller2);
        expressionInput.setOnUserAction(controller);
        computeButton.setOnUserAction(controller);
        controller.setErrorReporter(this.canvas);
        controller2.add(variableInput);
        controller2.add(variableInput2);
        controller2.add(draggablePoint);
        controller2.add(draggablePoint2);
        controller2.add(tangentLine);
        controller2.add(drawGeometric);
        controller2.add(drawString);
        draggablePoint.setOnUserAction(controller2);
        draggablePoint2.setOnUserAction(controller2);
        variableInput.setOnTextChange(controller2);
        variableInput2.setOnTextChange(controller2);
        controller2.add(new Tie((Tieable)draggablePoint.getXVar(), variableInput));
        controller2.add(new Tie((Tieable)draggablePoint2.getXVar(), variableInput2));
    }
    
    public Insets getInsets() {
        return new Insets(3, 3, 3, 3);
    }
}
