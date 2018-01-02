import edu.hws.jcm.awt.Controller;
import edu.hws.jcm.data.Function;
import edu.hws.jcm.draw.CoordinateRect;
import edu.hws.jcm.awt.Tieable;
import edu.hws.jcm.awt.Tie;
import edu.hws.jcm.draw.DrawBorder;
import edu.hws.jcm.draw.Crosshair;
import edu.hws.jcm.draw.Axes;
import java.awt.LayoutManager;
import java.awt.BorderLayout;
import java.awt.Label;
import java.awt.Component;
import edu.hws.jcm.awt.JCMPanel;
import edu.hws.jcm.awt.ComputeButton;
import java.awt.Color;
import java.awt.Font;
import edu.hws.jcm.draw.DrawString;
import edu.hws.jcm.data.ValueMath;
import edu.hws.jcm.data.Value;
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

public class GraphApplet2 extends Applet
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
        final DrawString drawString = new DrawString("x = #\nf(x) = #", 0, new Value[] { variableSlider, new ValueMath(function, variableSlider) });
        drawString.setFont(new Font("SansSerif", 1, 12));
        drawString.setColor(new Color(0, 100, 0));
        drawString.setOffset(10);
        final ComputeButton computeButton = new ComputeButton("Graph It!");
        final JCMPanel jcmPanel = new JCMPanel();
        final JCMPanel jcmPanel2 = new JCMPanel();
        final JCMPanel jcmPanel3 = new JCMPanel();
        jcmPanel.add(this.canvas, "Center");
        jcmPanel.add(limitControlPanel, "East");
        jcmPanel.add(jcmPanel3, "South");
        jcmPanel.add(jcmPanel2, "North");
        jcmPanel.setInsetGap(3);
        jcmPanel2.add(expressionInput, "Center");
        jcmPanel2.add(new Label(" f(x) = "), "West");
        jcmPanel2.add(computeButton, "East");
        jcmPanel3.add(variableSlider, "Center");
        jcmPanel3.add(variableInput, "East");
        jcmPanel3.add(new Label("  x = "), "West");
        this.setLayout(new BorderLayout());
        this.add(jcmPanel, "Center");
        this.setBackground(Color.lightGray);
        this.canvas.add(new Axes());
        this.canvas.add(graph1D);
        this.canvas.add(new Crosshair(variableSlider, function));
        this.canvas.add(drawString);
        this.canvas.add(new DrawBorder(Color.darkGray, 2));
        jcmPanel.gatherInputs();
        final Controller controller = jcmPanel.getController();
        computeButton.setOnUserAction(controller);
        coordinateRect.setOnChange(controller);
        controller.add(new Tie(variableSlider, variableInput));
    }
}
