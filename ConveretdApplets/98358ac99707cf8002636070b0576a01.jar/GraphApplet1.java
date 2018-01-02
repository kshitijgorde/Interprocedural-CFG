import edu.hws.jcm.awt.ErrorReporter;
import edu.hws.jcm.draw.Drawable;
import edu.hws.jcm.draw.Axes;
import java.awt.Color;
import java.awt.LayoutManager;
import java.awt.BorderLayout;
import java.awt.Component;
import edu.hws.jcm.awt.JCMPanel;
import edu.hws.jcm.draw.Graph1D;
import edu.hws.jcm.awt.ExpressionInput;
import edu.hws.jcm.draw.LimitControlPanel;
import edu.hws.jcm.draw.DisplayCanvas;
import edu.hws.jcm.data.MathObject;
import edu.hws.jcm.data.Variable;
import edu.hws.jcm.data.Parser;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class GraphApplet1 extends Applet
{
    public void init() {
        final Parser parser = new Parser();
        final Variable variable = new Variable("x");
        parser.add(variable);
        final DisplayCanvas displayCanvas = new DisplayCanvas();
        displayCanvas.setUseOffscreenCanvas(false);
        displayCanvas.setHandleMouseZooms(true);
        final LimitControlPanel limitControlPanel = new LimitControlPanel();
        limitControlPanel.addCoords(displayCanvas);
        final ExpressionInput expressionInput = new ExpressionInput("sin(x)+2*cos(3*x)", parser);
        final Graph1D graph1D = new Graph1D(expressionInput.getFunction(variable));
        final JCMPanel jcmPanel = new JCMPanel();
        jcmPanel.add(displayCanvas, "Center");
        jcmPanel.add(expressionInput, "South");
        jcmPanel.add(limitControlPanel, "East");
        jcmPanel.setInsetGap(3);
        this.setLayout(new BorderLayout());
        this.add(jcmPanel, "Center");
        this.setBackground(Color.lightGray);
        displayCanvas.add(new Axes());
        displayCanvas.add(graph1D);
        jcmPanel.getController().setErrorReporter(displayCanvas);
        limitControlPanel.setErrorReporter(displayCanvas);
        jcmPanel.gatherInputs();
    }
}
