import edu.hws.jcm.data.Variable;
import edu.hws.jcm.data.Parser;
import edu.hws.jcm.awt.ExpressionInput;
import java.awt.Graphics;
import java.awt.Dimension;
import java.awt.Canvas;
import edu.hws.jcm.draw.CoordinateRect;
import edu.hws.jcm.data.ParseError;
import edu.hws.jcm.draw.Drawable;
import java.awt.Container;
import edu.hws.jcm.awt.DisplayLabel;
import edu.hws.jcm.data.Function;
import edu.hws.jcm.data.Expression;
import edu.hws.jcm.data.SimpleFunction;
import java.awt.LayoutManager;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.awt.Button;
import java.awt.Label;
import java.awt.Component;
import edu.hws.jcm.awt.JCMPanel;
import java.awt.Panel;
import edu.hws.jcm.data.Value;
import edu.hws.jcm.awt.VariableSlider;
import edu.hws.jcm.data.Constant;
import java.util.StringTokenizer;
import java.awt.Color;
import edu.hws.jcm.draw.Graph1D;
import java.util.Vector;

// 
// Decompiled by Procyon v0.5.30
// 

public class MultiGraph extends GenericGraphApplet
{
    private Vector sliders;
    private ExprIn[] inputs;
    private Graph1D[] graphs;
    private int functionCt;
    private Color[] graphColors;
    
    protected void setUpParser() {
        this.sliders = new Vector();
        int n = 0;
        String s = this.getParameter("Parameter");
        if (s == null) {
            ++n;
            s = this.getParameter("Parameter" + n);
        }
        while (s != null) {
            this.addParameter(s);
            ++n;
            s = this.getParameter("Parameter" + n);
        }
        super.setUpParser();
    }
    
    private void addParameter(String trim) {
        double doubleValue = -5.0;
        double doubleValue2 = 5.0;
        double doubleValue3 = 0.0;
        trim = trim.trim();
        int n = trim.indexOf(59);
        if (n < 0) {
            n = trim.indexOf(32);
        }
        String trim2;
        if (n < 0) {
            trim2 = trim;
        }
        else {
            final String substring = trim.substring(n + 1);
            trim2 = trim.substring(0, n).trim();
            final StringTokenizer stringTokenizer = new StringTokenizer(substring, " ,\t");
            try {
                if (stringTokenizer.hasMoreElements()) {
                    doubleValue = new Double(stringTokenizer.nextToken());
                }
                if (stringTokenizer.hasMoreElements()) {
                    doubleValue2 = new Double(stringTokenizer.nextToken());
                }
                if (stringTokenizer.hasMoreElements()) {
                    doubleValue3 = new Double(stringTokenizer.nextToken());
                }
            }
            catch (NumberFormatException ex) {
                doubleValue = -5.0;
                doubleValue2 = 5.0;
                doubleValue3 = 0.0;
            }
        }
        final VariableSlider variableSlider = new VariableSlider(trim2, new Constant(doubleValue), new Constant(doubleValue2), super.parser);
        variableSlider.setVal(doubleValue3);
        this.sliders.addElement(variableSlider);
    }
    
    private void getColors() {
        final Vector vector = new Vector<Color>();
        int n = 0;
        Color color = this.getColorParam("GraphColor");
        if (color == null) {
            ++n;
            color = this.getColorParam("GraphColor" + n);
        }
        while (color != null) {
            vector.addElement(color);
            ++n;
            color = this.getColorParam("GraphColor" + n);
        }
        if (vector.size() > 0) {
            this.graphColors = new Color[vector.size()];
            for (int i = 0; i < vector.size(); ++i) {
                this.graphColors[i] = vector.elementAt(i);
            }
        }
    }
    
    private Vector getFunctions() {
        final Vector vector = new Vector<String>();
        int n = 0;
        String s = this.getParameter("Function");
        if (s == null) {
            ++n;
            s = this.getParameter("Function" + n);
        }
        while (s != null) {
            vector.addElement(s);
            ++n;
            s = this.getParameter("Function" + n);
        }
        if (vector.size() == 0) {
            vector.addElement(" abs( " + super.xVar.getName() + ") ^ " + super.xVar.getName());
        }
        final double[] numericParam = this.getNumericParam("FunctionCount");
        if (numericParam == null || numericParam.length == 0 || numericParam[0] <= 0.5) {
            this.functionCt = vector.size();
        }
        else {
            this.functionCt = (int)Math.round(numericParam[0]);
            if (this.functionCt < vector.size()) {
                this.functionCt = vector.size();
            }
            else {
                for (int n2 = this.functionCt - vector.size(), i = 0; i < n2; ++i) {
                    vector.addElement("");
                }
            }
        }
        return vector;
    }
    
    private Panel makeFunctionInput(final Vector vector, final int n) {
        final Graph1D graph1D = new Graph1D();
        graph1D.setColor(this.graphColors[n % this.graphColors.length]);
        final ExprIn exprIn = new ExprIn(vector.elementAt(n), super.parser, graph1D, super.xVar);
        exprIn.setOnUserAction(super.mainController);
        final JCMPanel jcmPanel = new JCMPanel();
        jcmPanel.add(exprIn, "Center");
        String s;
        if (vector.size() > 1) {
            s = " " + this.getParameter("FunctionName", "f") + (n + 1) + "(" + super.xVar.getName() + ") = ";
        }
        else {
            s = " " + this.getParameter("FunctionName", "f") + "(" + super.xVar.getName() + ") = ";
        }
        jcmPanel.add(new Label(s), "West");
        if (this.graphColors.length > 1 && vector.size() > 1) {
            jcmPanel.add(new ColorPatch(this.graphColors[n % this.graphColors.length]), "East");
        }
        this.inputs[n] = exprIn;
        return jcmPanel;
    }
    
    protected void setUpBottomPanel() {
        final boolean equalsIgnoreCase = "yes".equalsIgnoreCase(this.getParameter("UseFunctionInput", "yes"));
        if (equalsIgnoreCase && "yes".equalsIgnoreCase(this.getParameter("UseComputeButton", "yes"))) {
            (super.computeButton = new Button(this.getParameter("ComputeButtonName", "New Functions"))).addActionListener(this);
        }
        Container container = null;
        this.getColors();
        final Vector functions = this.getFunctions();
        if (!equalsIgnoreCase && this.sliders.size() == 0) {
            return;
        }
        final JCMPanel jcmPanel = new JCMPanel();
        if (!"no".equalsIgnoreCase(this.getParameter("TwoInputColumns", "no"))) {
            jcmPanel.setLayout(new GridLayout(0, 2, 12, 3));
        }
        else {
            jcmPanel.setLayout(new GridLayout(0, 1, 3, 3));
        }
        jcmPanel.setBackground(this.getColorParam("PanelBackground", Color.lightGray));
        if (equalsIgnoreCase) {
            this.inputs = new ExprIn[functions.size()];
            for (int i = 0; i < functions.size(); ++i) {
                final Panel functionInput = this.makeFunctionInput(functions, i);
                if (container == null) {
                    container = functionInput;
                }
                jcmPanel.add(functionInput);
            }
        }
        else {
            this.graphs = new Graph1D[functions.size()];
            for (int j = 0; j < functions.size(); ++j) {
                (this.graphs[j] = new Graph1D()).setColor(this.graphColors[j % this.graphColors.length]);
                final String trim = functions.elementAt(j).trim();
                if (trim.length() > 0) {
                    this.graphs[j].setFunction(new SimpleFunction(super.parser.parse(trim), super.xVar));
                }
            }
        }
        for (int k = 0; k < this.sliders.size(); ++k) {
            final JCMPanel jcmPanel2 = new JCMPanel();
            final VariableSlider variableSlider = this.sliders.elementAt(k);
            jcmPanel2.add(variableSlider, "Center");
            jcmPanel2.add(new DisplayLabel("  " + variableSlider.getName() + " = # ", new Value[] { variableSlider.getVariable() }), "East");
            jcmPanel.add(jcmPanel2);
            variableSlider.setOnUserAction(super.mainController);
        }
        if (super.computeButton != null) {
            if (functions.size() == 1) {
                container.add(super.computeButton, "East");
            }
            else if (super.limitsPanel == null) {
                final Panel panel = new Panel();
                panel.add(super.computeButton);
                jcmPanel.add(panel);
            }
        }
        super.mainPanel.add(jcmPanel, "South");
    }
    
    protected void setUpLimitsPanel() {
        super.setUpLimitsPanel();
        if (super.limitsPanel != null && super.computeButton != null && this.functionCt != 1) {
            super.limitsPanel.addComponent(super.computeButton);
        }
    }
    
    protected void setUpCanvas() {
        super.setUpCanvas();
        if (this.graphs != null) {
            for (int i = 0; i < this.graphs.length; ++i) {
                super.canvas.add(this.graphs[i]);
            }
        }
        else {
            for (int j = 0; j < this.inputs.length; ++j) {
                super.canvas.add(this.inputs[j].graph);
            }
        }
    }
    
    protected void doLoadExample(String substring) {
        final int index = substring.indexOf(";");
        final double[] limits = { -5.0, 5.0, -5.0, 5.0 };
        if (index > 0) {
            final String substring2 = substring.substring(0, index);
            substring = substring.substring(index + 1);
            final StringTokenizer stringTokenizer = new StringTokenizer(substring2, " ,");
            if (stringTokenizer.countTokens() >= 4) {
                for (int i = 0; i < 4; ++i) {
                    try {
                        limits[i] = new Double(stringTokenizer.nextToken());
                    }
                    catch (NumberFormatException ex) {}
                }
            }
            for (int n = 0; n < this.sliders.size() && stringTokenizer.hasMoreElements(); ++n) {
                try {
                    final double doubleValue = new Double(stringTokenizer.nextToken());
                    final double doubleValue2 = new Double(stringTokenizer.nextToken());
                    final double doubleValue3 = new Double(stringTokenizer.nextToken());
                    final VariableSlider variableSlider = this.sliders.elementAt(n);
                    variableSlider.setMin(new Constant(doubleValue));
                    variableSlider.setMax(new Constant(doubleValue2));
                    variableSlider.setVal(doubleValue3);
                }
                catch (Exception ex2) {}
            }
        }
        final StringTokenizer stringTokenizer2 = new StringTokenizer(substring, ";");
        for (int j = 0; j < this.functionCt; ++j) {
            if (stringTokenizer2.hasMoreElements()) {
                final String nextToken = stringTokenizer2.nextToken();
                if (this.graphs != null) {
                    try {
                        this.graphs[j].setFunction(new SimpleFunction(super.parser.parse(nextToken), super.xVar));
                    }
                    catch (ParseError parseError) {
                        this.graphs[j].setFunction(null);
                    }
                }
                else {
                    this.inputs[j].setText(nextToken);
                }
            }
            else if (this.graphs != null) {
                this.graphs[j].setFunction(null);
            }
            else {
                this.inputs[j].setText("");
            }
        }
        final CoordinateRect coordinateRect = super.canvas.getCoordinateRect(0);
        coordinateRect.setLimits(limits);
        coordinateRect.setRestoreBuffer();
        super.mainController.compute();
    }
    
    public MultiGraph() {
        this.graphColors = new Color[] { Color.magenta, new Color(0, 180, 0), Color.red, new Color(0, 200, 200), Color.orange, Color.gray, Color.blue, Color.pink };
    }
    
    private static class ColorPatch extends Canvas
    {
        ColorPatch(final Color background) {
            this.setBackground(background);
        }
        
        public Dimension getPreferredSize() {
            return new Dimension(25, 10);
        }
        
        public void paint(final Graphics graphics) {
            graphics.drawRect(0, 0, this.getSize().width - 1, this.getSize().height - 1);
        }
    }
    
    private static class ExprIn extends ExpressionInput
    {
        Graph1D graph;
        Function func;
        
        ExprIn(final String s, final Parser parser, final Graph1D graph, final Variable variable) {
            super(s, parser);
            this.graph = graph;
            this.func = this.getFunction(variable);
            if (s.trim().length() > 0) {
                this.graph.setFunction(this.func);
            }
        }
        
        public void checkInput() {
            if (!super.hasChanged) {
                return;
            }
            if (this.getText().trim().length() == 0) {
                if (this.graph != null) {
                    this.graph.setFunction(null);
                }
                super.hasChanged = false;
            }
            else {
                super.checkInput();
                if (this.graph != null) {
                    this.graph.setFunction(this.func);
                }
            }
        }
    }
}
