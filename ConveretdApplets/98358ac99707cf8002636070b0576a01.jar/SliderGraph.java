import edu.hws.jcm.awt.Controller;
import java.awt.LayoutManager;
import java.awt.BorderLayout;
import edu.hws.jcm.awt.ErrorReporter;
import java.awt.Label;
import edu.hws.jcm.draw.DrawBorder;
import java.awt.Color;
import edu.hws.jcm.draw.Axes;
import edu.hws.jcm.awt.ComputeButton;
import edu.hws.jcm.draw.Graph1D;
import edu.hws.jcm.awt.ExpressionInput;
import edu.hws.jcm.draw.LimitControlPanel;
import edu.hws.jcm.draw.Drawable;
import edu.hws.jcm.draw.Panner;
import edu.hws.jcm.data.MathObject;
import edu.hws.jcm.data.Variable;
import edu.hws.jcm.data.Parser;
import edu.hws.jcm.awt.DisplayLabel;
import edu.hws.jcm.data.Value;
import java.awt.Component;
import edu.hws.jcm.awt.JCMPanel;
import edu.hws.jcm.awt.VariableSlider;
import edu.hws.jcm.draw.DisplayCanvas;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class SliderGraph extends Applet
{
    private DisplayCanvas canvas;
    
    public void stop() {
        this.canvas.releaseResources();
    }
    
    JCMPanel makeSliderPanel(final VariableSlider variableSlider) {
        final JCMPanel jcmPanel = new JCMPanel();
        jcmPanel.add(variableSlider, "Center");
        jcmPanel.add(new DisplayLabel(variableSlider.getName() + " = #", new Value[] { variableSlider }), "East");
        return jcmPanel;
    }
    
    public void init() {
        final Parser parser = new Parser();
        final Variable variable = new Variable("x");
        parser.add(variable);
        final VariableSlider variableSlider = new VariableSlider("a", null, null, parser);
        final VariableSlider variableSlider2 = new VariableSlider("b", null, null, parser);
        final VariableSlider variableSlider3 = new VariableSlider("c", null, null, parser);
        (this.canvas = new DisplayCanvas()).setHandleMouseZooms(true);
        this.canvas.add(new Panner());
        final LimitControlPanel limitControlPanel = new LimitControlPanel(35, false);
        limitControlPanel.addCoords(this.canvas);
        final ExpressionInput expressionInput = new ExpressionInput("a*x^2 + b*x + c", parser);
        final Graph1D graph1D = new Graph1D(expressionInput.getFunction(variable));
        final ComputeButton computeButton = new ComputeButton("Graph it!");
        this.canvas.add(new Axes());
        this.canvas.add(graph1D);
        this.canvas.add(new DrawBorder(Color.darkGray, 2));
        final JCMPanel jcmPanel = new JCMPanel();
        jcmPanel.setInsetGap(3);
        jcmPanel.add(this.canvas, "Center");
        jcmPanel.add(limitControlPanel, "East");
        final JCMPanel jcmPanel2 = new JCMPanel(5, 1);
        jcmPanel.add(jcmPanel2, "South");
        jcmPanel2.add(new Label("Enter a function f(x), which can use the constants a, b, and c:"));
        final JCMPanel jcmPanel3 = new JCMPanel();
        jcmPanel2.add(jcmPanel3);
        jcmPanel3.add(expressionInput, "Center");
        jcmPanel3.add(computeButton, "East");
        jcmPanel2.add(this.makeSliderPanel(variableSlider));
        jcmPanel2.add(this.makeSliderPanel(variableSlider2));
        jcmPanel2.add(this.makeSliderPanel(variableSlider3));
        final Controller controller = jcmPanel.getController();
        controller.setErrorReporter(this.canvas);
        limitControlPanel.setErrorReporter(this.canvas);
        jcmPanel.gatherInputs();
        computeButton.setOnUserAction(controller);
        this.setBackground(Color.lightGray);
        this.setLayout(new BorderLayout());
        this.add(jcmPanel, "Center");
    }
}
