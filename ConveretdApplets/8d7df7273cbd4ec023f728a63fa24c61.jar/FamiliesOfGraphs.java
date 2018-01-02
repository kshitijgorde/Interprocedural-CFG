import edu.hws.jcm.draw.CoordinateRect;
import edu.hws.jcm.data.ParseError;
import edu.hws.jcm.draw.Drawable;
import edu.hws.jcm.data.Expression;
import edu.hws.jcm.data.SimpleFunction;
import edu.hws.jcm.awt.DisplayLabel;
import java.awt.Component;
import java.awt.Color;
import java.awt.LayoutManager;
import java.awt.GridLayout;
import edu.hws.jcm.awt.JCMPanel;
import edu.hws.jcm.data.Value;
import edu.hws.jcm.data.Constant;
import java.util.StringTokenizer;
import edu.hws.jcm.awt.VariableSlider;
import java.util.Hashtable;
import java.util.Vector;
import edu.hws.jcm.draw.Graph1D;
import edu.hws.jcm.data.Function;

// 
// Decompiled by Procyon v0.5.30
// 

public class FamiliesOfGraphs extends GenericGraphApplet
{
    private Function func;
    private Graph1D graph;
    private Vector sliders;
    
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
        if (this.sliders.size() == 0) {
            this.addParameter("k");
        }
        super.setUpParser();
        (super.parameterDefaults = new Hashtable()).put("Function", this.getParameter("Function", "sin(" + this.sliders.elementAt(0).getName() + " * " + super.xVar.getName() + ")"));
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
    
    protected void setUpBottomPanel() {
        super.setUpBottomPanel();
        final JCMPanel jcmPanel = new JCMPanel();
        jcmPanel.setLayout(new GridLayout(0, 1, 3, 3));
        jcmPanel.setBackground(this.getColorParam("PanelBackground", Color.lightGray));
        for (int i = 0; i < this.sliders.size(); ++i) {
            final JCMPanel jcmPanel2 = new JCMPanel();
            final VariableSlider variableSlider = this.sliders.elementAt(i);
            jcmPanel2.add(variableSlider, "Center");
            jcmPanel2.add(new DisplayLabel("  " + variableSlider.getName() + " = # ", new Value[] { variableSlider.getVariable() }), "East");
            jcmPanel.add(jcmPanel2);
            variableSlider.setOnUserAction(super.mainController);
        }
        if (super.inputPanel != null) {
            super.inputPanel.add(jcmPanel, "South");
        }
        else {
            super.mainPanel.add(jcmPanel, "South");
        }
    }
    
    protected void setUpCanvas() {
        super.setUpCanvas();
        if (super.functionInput != null) {
            this.func = super.functionInput.getFunction(super.xVar);
        }
        else {
            this.func = new SimpleFunction(super.parser.parse(this.getParameter("Function")), super.xVar);
        }
        (this.graph = new Graph1D(this.func)).setColor(this.getColorParam("GraphColor", Color.magenta));
        super.canvas.add(this.graph);
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
        if (super.functionInput != null) {
            super.functionInput.setText(substring);
        }
        else {
            try {
                this.func = new SimpleFunction(super.parser.parse(substring), super.xVar);
                this.graph.setFunction(this.func);
            }
            catch (ParseError parseError) {}
        }
        final CoordinateRect coordinateRect = super.canvas.getCoordinateRect(0);
        coordinateRect.setLimits(limits);
        coordinateRect.setRestoreBuffer();
        super.mainController.compute();
    }
}
