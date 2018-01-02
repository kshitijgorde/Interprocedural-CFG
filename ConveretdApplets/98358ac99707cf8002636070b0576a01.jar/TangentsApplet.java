import edu.hws.jcm.data.Expression;
import edu.hws.jcm.awt.Computable;
import edu.hws.jcm.awt.Controller;
import edu.hws.jcm.data.Function;
import edu.hws.jcm.awt.Tie;
import edu.hws.jcm.awt.Tieable;
import java.awt.LayoutManager;
import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Label;
import edu.hws.jcm.awt.JCMPanel;
import edu.hws.jcm.awt.ComputeButton;
import edu.hws.jcm.draw.DrawBorder;
import java.awt.Color;
import edu.hws.jcm.draw.DrawString;
import edu.hws.jcm.data.ValueMath;
import edu.hws.jcm.draw.Crosshair;
import edu.hws.jcm.data.Value;
import edu.hws.jcm.draw.TangentLine;
import edu.hws.jcm.draw.MouseTracker;
import edu.hws.jcm.draw.Graph1D;
import edu.hws.jcm.awt.ExpressionInput;
import edu.hws.jcm.data.MathObject;
import edu.hws.jcm.data.Variable;
import edu.hws.jcm.data.Parser;
import edu.hws.jcm.awt.ErrorReporter;
import edu.hws.jcm.draw.LimitControlPanel;
import edu.hws.jcm.draw.Drawable;
import edu.hws.jcm.draw.Axes;
import edu.hws.jcm.draw.DisplayCanvas;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class TangentsApplet extends Applet
{
    DisplayCanvas canvas;
    
    public void stop() {
        this.canvas.releaseResources();
    }
    
    public void init() {
        (this.canvas = new DisplayCanvas()).addNewCoordinateRect(0.0, 0.5, 0.0, 1.0);
        this.canvas.addNewCoordinateRect(0.5, 1.0, 0.0, 1.0);
        this.canvas.add(new Axes(), 0);
        this.canvas.add(new Axes(), 1);
        final LimitControlPanel limitControlPanel = new LimitControlPanel();
        limitControlPanel.addCoords(this.canvas.getCoordinateRect(0));
        limitControlPanel.addCoords(this.canvas.getCoordinateRect(1));
        limitControlPanel.setErrorReporter(this.canvas);
        final Parser parser = new Parser();
        final Variable variable = new Variable("x");
        parser.add(variable);
        final ExpressionInput expressionInput = new ExpressionInput("sin(x)*cos(2*x) + x/3", parser);
        final Function function = expressionInput.getFunction(variable);
        final Function derivative = function.derivative(1);
        final Graph1D graph1D = new Graph1D(function);
        final Graph1D graph1D2 = new Graph1D(derivative);
        this.canvas.add(graph1D, 0);
        this.canvas.add(graph1D2, 1);
        final MouseTracker mouseTracker = new MouseTracker();
        final MouseTracker mouseTracker2 = new MouseTracker();
        final Variable xVar = mouseTracker.getXVar();
        xVar.setVal(0.0);
        final Variable xVar2 = mouseTracker2.getXVar();
        xVar2.setVal(0.0);
        final TangentLine tangentLine = new TangentLine(xVar, function);
        final Crosshair crosshair = new Crosshair(xVar2, derivative);
        this.canvas.add(mouseTracker, 0);
        this.canvas.add(mouseTracker2, 1);
        this.canvas.add(tangentLine, 0);
        this.canvas.add(crosshair, 1);
        final DrawString drawString = new DrawString("x = #\nf(x) = #\nf'(x) = #", 8, new Value[] { xVar, new ValueMath(function, xVar), new ValueMath(derivative, xVar) });
        drawString.setNumSize(6);
        drawString.setColor(new Color(0, 100, 0));
        this.canvas.add(drawString, 1);
        this.canvas.add(new DrawString("y = f(x)"), 0);
        this.canvas.add(new DrawString("y = f'(x)"), 1);
        this.canvas.add(new DrawBorder(), 0);
        this.canvas.add(new DrawBorder(), 1);
        final ComputeButton computeButton = new ComputeButton("Draw graph");
        final JCMPanel jcmPanel = new JCMPanel(3);
        jcmPanel.add(new Label(" f(x) = "), "West");
        jcmPanel.add(expressionInput, "Center");
        jcmPanel.add(computeButton, "East");
        final JCMPanel jcmPanel2 = new JCMPanel(2, 1);
        jcmPanel2.add(jcmPanel);
        jcmPanel2.add(new FuncLabel(expressionInput.getExpression().derivative(variable)));
        final JCMPanel jcmPanel3 = new JCMPanel(3);
        jcmPanel3.setInsetGap(3);
        jcmPanel3.add(jcmPanel2, "South");
        jcmPanel3.add(this.canvas, "Center");
        jcmPanel3.add(limitControlPanel, "East");
        this.setBackground(Color.lightGray);
        this.setLayout(new BorderLayout(3, 3));
        this.add(jcmPanel3, "Center");
        final Controller controller = jcmPanel3.getController();
        controller.setErrorReporter(this.canvas);
        controller.add(new Tie((Tieable)xVar, (Tieable)xVar2));
        computeButton.setOnUserAction(controller);
        jcmPanel3.gatherInputs();
    }
    
    static class FuncLabel extends Label implements Computable
    {
        Expression exp;
        
        FuncLabel(final Expression exp) {
            super(" f'(x) = " + exp);
            this.exp = exp;
        }
        
        public void compute() {
            this.setText(" f'(x) = " + this.exp);
        }
    }
}
