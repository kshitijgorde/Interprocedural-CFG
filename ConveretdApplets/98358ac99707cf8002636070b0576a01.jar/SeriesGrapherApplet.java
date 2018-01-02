import edu.hws.jcm.data.Cases;
import edu.hws.jcm.data.Expression;
import java.awt.Insets;
import edu.hws.jcm.awt.DisplayLabel;
import edu.hws.jcm.awt.ErrorReporter;
import java.awt.Label;
import java.awt.Component;
import java.awt.GridLayout;
import java.awt.Panel;
import java.awt.LayoutManager;
import java.awt.BorderLayout;
import edu.hws.jcm.awt.Computable;
import edu.hws.jcm.awt.Controller;
import edu.hws.jcm.draw.DrawBorder;
import edu.hws.jcm.data.Function;
import edu.hws.jcm.draw.Graph1D;
import edu.hws.jcm.draw.Drawable;
import edu.hws.jcm.draw.Axes;
import java.awt.Font;
import java.awt.Color;
import edu.hws.jcm.draw.DrawString;
import edu.hws.jcm.awt.ComputeButton;
import edu.hws.jcm.awt.ExpressionInput;
import edu.hws.jcm.data.MathObject;
import edu.hws.jcm.data.Variable;
import edu.hws.jcm.data.Parser;
import edu.hws.jcm.data.Value;
import edu.hws.jcm.awt.VariableSlider;
import edu.hws.jcm.data.Constant;
import edu.hws.jcm.draw.LimitControlPanel;
import edu.hws.jcm.draw.CoordinateRect;
import edu.hws.jcm.draw.DisplayCanvas;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class SeriesGrapherApplet extends Applet
{
    private DisplayCanvas termCanvas;
    private DisplayCanvas seriesCanvas;
    
    public void stop() {
        this.termCanvas.releaseResources();
        this.seriesCanvas.releaseResources();
    }
    
    public void init() {
        this.termCanvas = new DisplayCanvas(new CoordinateRect(-15.0, 15.0, -3.0, 3.0));
        this.seriesCanvas = new DisplayCanvas(new CoordinateRect(-15.0, 15.0, -3.0, 3.0));
        final LimitControlPanel limitControlPanel = new LimitControlPanel(1, true);
        limitControlPanel.addCoords(this.termCanvas);
        limitControlPanel.addCoords(this.seriesCanvas);
        final VariableSlider variableSlider = new VariableSlider(new Constant(0.0), new Constant(10.0));
        variableSlider.setIntegerValued(true);
        variableSlider.setVal(0.0);
        final VariableSlider variableSlider2 = new VariableSlider(variableSlider, new Constant(20.0));
        variableSlider2.setIntegerValued(true);
        final VariableSlider variableSlider3 = new VariableSlider(variableSlider, variableSlider2);
        variableSlider3.setIntegerValued(true);
        variableSlider3.setVal(0.0);
        final Parser parser = new Parser(1120);
        final Variable variable = new Variable("k");
        final Variable variable2 = new Variable("x");
        parser.add(variable);
        parser.add(variable2);
        final ExpressionInput expressionInput = new ExpressionInput("(-1)^k * x^(2*k+1) / (2*k+1)!", parser);
        final ComputeButton computeButton = new ComputeButton("New");
        final DrawString drawString = new DrawString("Sum from k = # to #", 0, new Value[] { variableSlider, variableSlider2 });
        drawString.setColor(new Color(0, 120, 0));
        drawString.setFont(new Font("SansSerif", 1, 10));
        final DrawString drawString2 = new DrawString("Term with k = #", 0, new Value[] { variableSlider3 });
        drawString2.setColor(new Color(0, 120, 0));
        drawString2.setFont(new Font("SansSerif", 1, 10));
        this.seriesCanvas.add(new Axes());
        this.seriesCanvas.add(new Graph1D(new SeriesFunc(expressionInput.getExpression(), variable2, variable, variableSlider, variableSlider2)));
        this.seriesCanvas.add(new DrawBorder());
        this.seriesCanvas.add(drawString);
        this.termCanvas.add(new Axes());
        this.termCanvas.add(new Graph1D(expressionInput.getFunction(variable2)));
        this.termCanvas.add(new DrawBorder());
        this.termCanvas.add(drawString2);
        final Controller onUserAction = new Controller();
        onUserAction.add(new Computable() {
            public void compute() {
                variable.setVal(variableSlider3.getVal());
            }
        });
        onUserAction.add(variableSlider3);
        onUserAction.add(this.termCanvas);
        variableSlider3.setOnUserAction(onUserAction);
        final Controller controller = new Controller();
        controller.add(variableSlider);
        controller.add(variableSlider2);
        controller.add(this.seriesCanvas);
        controller.add(expressionInput);
        controller.add(onUserAction);
        variableSlider.setOnUserAction(controller);
        variableSlider2.setOnUserAction(controller);
        expressionInput.setOnUserAction(controller);
        computeButton.setOnUserAction(controller);
        this.setBackground(Color.lightGray);
        this.setLayout(new BorderLayout(3, 3));
        final Panel panel = new Panel();
        panel.setLayout(new GridLayout(2, 1, 3, 3));
        panel.add(this.seriesCanvas);
        panel.add(this.termCanvas);
        this.add(panel, "Center");
        final Panel panel2 = new Panel();
        panel2.setLayout(new GridLayout(1, 2, 3, 3));
        final Panel panel3 = new Panel();
        panel3.setLayout(new GridLayout(5, 1, 3, 3));
        panel2.add(panel3);
        panel2.add(limitControlPanel);
        this.add(panel2, "South");
        panel3.add(new Label("Enter the formula for a term, f(x,k): "));
        final Panel panel4 = new Panel();
        panel4.setLayout(new BorderLayout(3, 3));
        panel4.add(expressionInput, "Center");
        panel4.add(computeButton, "East");
        panel3.add(panel4);
        panel3.add(this.slidePanel("Lower limit k = #", variableSlider, controller));
        panel3.add(this.slidePanel("Upper limit k = #", variableSlider2, controller));
        panel3.add(this.slidePanel("Show term for k = #", variableSlider3, onUserAction));
        controller.setErrorReporter(this.termCanvas);
        limitControlPanel.setErrorReporter(this.termCanvas);
    }
    
    Panel slidePanel(final String s, final VariableSlider variableSlider, final Controller controller) {
        final Panel panel = new Panel();
        panel.setLayout(new GridLayout(1, 2, 3, 3));
        final DisplayLabel displayLabel = new DisplayLabel(s, new Value[] { variableSlider });
        panel.add(variableSlider);
        panel.add(displayLabel);
        controller.add(displayLabel);
        return panel;
    }
    
    public Insets getInsets() {
        return new Insets(3, 3, 3, 3);
    }
    
    private static class SeriesFunc implements Function
    {
        Expression func;
        Variable variable;
        Variable index;
        Value start;
        Value stop;
        
        SeriesFunc(final Expression func, final Variable variable, final Variable index, final Value start, final Value stop) {
            this.func = func;
            this.variable = variable;
            this.index = index;
            this.start = start;
            this.stop = stop;
        }
        
        public int getArity() {
            return 1;
        }
        
        public double getValueWithCases(final double[] array, final Cases cases) {
            double n = 0.0;
            final int n2 = (int)Math.round(this.start.getVal());
            final int n3 = (int)Math.round(this.stop.getVal());
            final double val = this.index.getVal();
            this.variable.setVal(array[0]);
            for (int i = n2; i <= n3; ++i) {
                this.index.setVal(i);
                n += this.func.getValueWithCases(cases);
            }
            this.index.setVal(val);
            return n;
        }
        
        public double getVal(final double[] array) {
            return this.getValueWithCases(array, null);
        }
        
        public Function derivative(final int n) {
            return null;
        }
        
        public Function derivative(final Variable variable) {
            return null;
        }
        
        public boolean dependsOn(final Variable variable) {
            return false;
        }
        
        public String getName() {
            return null;
        }
        
        public void setName(final String s) {
        }
    }
}
