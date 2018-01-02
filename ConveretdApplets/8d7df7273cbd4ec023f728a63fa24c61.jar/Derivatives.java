import edu.hws.jcm.awt.Computable;
import java.awt.Label;
import edu.hws.jcm.draw.CoordinateRect;
import edu.hws.jcm.data.ParseError;
import java.util.StringTokenizer;
import edu.hws.jcm.draw.DrawBorder;
import edu.hws.jcm.data.ExpressionProgram;
import edu.hws.jcm.data.ValueMath;
import edu.hws.jcm.draw.DrawString;
import edu.hws.jcm.draw.Crosshair;
import edu.hws.jcm.draw.TangentLine;
import edu.hws.jcm.awt.ErrorReporter;
import edu.hws.jcm.draw.Grid;
import edu.hws.jcm.draw.Drawable;
import edu.hws.jcm.draw.Panner;
import edu.hws.jcm.draw.Graph1D;
import edu.hws.jcm.functions.WrapperFunction;
import edu.hws.jcm.data.SimpleFunction;
import java.awt.Container;
import edu.hws.jcm.data.Value;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Color;
import java.awt.Button;
import java.awt.Component;
import java.awt.LayoutManager;
import java.awt.BorderLayout;
import java.awt.Panel;
import edu.hws.jcm.awt.VariableSlider;
import edu.hws.jcm.awt.Tieable;
import edu.hws.jcm.awt.Tie;
import java.util.Hashtable;
import edu.hws.jcm.awt.VariableInput;
import edu.hws.jcm.awt.Controller;
import edu.hws.jcm.data.Expression;
import edu.hws.jcm.data.Function;

// 
// Decompiled by Procyon v0.5.30
// 

public class Derivatives extends GenericGraphApplet
{
    private String functionName;
    private Function func;
    private Function deriv;
    private Expression derivExpression;
    private Function deriv2;
    private Controller subController;
    private VariableInput xInput;
    
    protected void setUpParameterDefaults() {
        (super.parameterDefaults = new Hashtable()).put("Function", " tan(" + this.getParameter("Variable", "x") + ")");
    }
    
    protected void setUpMainPanel() {
        super.setUpMainPanel();
        if (super.limitsPanel != null) {
            super.limitsPanel.addCoords(super.canvas.getCoordinateRect(1));
            if (this.deriv2 != null) {
                super.limitsPanel.addCoords(super.canvas.getCoordinateRect(2));
            }
        }
        else {
            final Tie syncWith = new Tie(super.canvas.getCoordinateRect(0), super.canvas.getCoordinateRect(1));
            if (this.deriv2 != null) {
                syncWith.add(super.canvas.getCoordinateRect(2));
            }
            super.canvas.getCoordinateRect(0).setSyncWith(syncWith);
            super.canvas.getCoordinateRect(1).setSyncWith(syncWith);
            if (this.deriv2 != null) {
                super.canvas.getCoordinateRect(2).setSyncWith(syncWith);
            }
        }
        final Value valueObject = super.canvas.getCoordinateRect().getValueObject(0);
        final Value valueObject2 = super.canvas.getCoordinateRect().getValueObject(1);
        super.canvas.getCoordinateRect().setOnChange(this.subController);
        final VariableSlider variableSlider = new VariableSlider(valueObject, valueObject2);
        variableSlider.setOnUserAction(this.subController);
        this.xInput.setOnTextChange(this.subController);
        this.subController.add(variableSlider);
        this.subController.add(this.xInput);
        this.subController.add(new Tie(variableSlider, this.xInput));
        Container container = new Panel();
        container.setLayout(new BorderLayout(5, 5));
        container.add(this.xInput.withLabel(), "West");
        container.add(variableSlider, "Center");
        if (super.limitsPanel == null && !"no".equalsIgnoreCase(this.getParameter("UseRestoreButton", "no"))) {
            final Button button = new Button("Restore Limits");
            container.add(button, "East");
            button.setBackground(Color.lightGray);
            button.addActionListener(new ActionListener() {
                public void actionPerformed(final ActionEvent actionEvent) {
                    Derivatives.this.canvas.getCoordinateRect(0).restore();
                    Derivatives.this.canvas.getCoordinateRect(1).restore();
                    if (Derivatives.this.deriv2 != null) {
                        Derivatives.this.canvas.getCoordinateRect(2).restore();
                    }
                }
            });
        }
        if ("yes".equalsIgnoreCase(this.getParameter("ShowFormula", "yes"))) {
            final Panel panel = new Panel();
            panel.setLayout(new GridLayout(2, 1, 3, 3));
            panel.add(container);
            final ExprLbl exprLbl = new ExprLbl(" " + this.functionName + "'(" + super.xVar.getName() + ") = ");
            super.mainController.add(exprLbl);
            panel.add(exprLbl);
            container = panel;
        }
        if (super.inputPanel == null) {
            container.setBackground(this.getColorParam("PanelBackground", Color.lightGray));
            super.mainPanel.add(container, "South");
        }
        else {
            super.inputPanel.add(container, "South");
        }
    }
    
    protected void setUpCanvas() {
        final boolean b = "no".equalsIgnoreCase(this.getParameter("SecondDerivative", "no")) ^ true;
        this.xInput = new VariableInput(super.xVar.getName(), this.getParameter("X", "1"));
        if (super.functionInput != null) {
            this.func = super.functionInput.getFunction(super.xVar);
            this.derivExpression = super.functionInput.getExpression().derivative(super.xVar);
        }
        else {
            final ExpressionProgram parse = super.parser.parse(this.getParameter("Function"));
            final SimpleFunction simpleFunction = new SimpleFunction(parse, super.xVar);
            this.derivExpression = parse.derivative(super.xVar);
            this.func = new WrapperFunction(simpleFunction);
        }
        final Graph1D graph1D = new Graph1D(this.func);
        final Color colorParam = this.getColorParam("GraphColor", Color.black);
        graph1D.setColor(colorParam);
        this.deriv = this.func.derivative(1);
        final Graph1D graph1D2 = new Graph1D(this.deriv);
        graph1D2.setColor(colorParam);
        Graph1D graph1D3 = null;
        if (b) {
            this.deriv2 = this.deriv.derivative(1);
            graph1D3 = new Graph1D(this.deriv2);
            graph1D3.setColor(colorParam);
        }
        if (b) {
            super.canvas.addNewCoordinateRect(0.0, 0.3333333333333333, 0.0, 1.0);
            super.canvas.addNewCoordinateRect(0.3333333333333333, 0.6666666666666666, 0.0, 1.0);
            super.canvas.addNewCoordinateRect(0.6666666666666666, 1.0, 0.0, 1.0);
        }
        else {
            super.canvas.addNewCoordinateRect(0.0, 0.5, 0.0, 1.0);
            super.canvas.addNewCoordinateRect(0.5, 1.0, 0.0, 1.0);
        }
        final Color colorParam2 = this.getColorParam("CanvasColor");
        if (colorParam2 != null) {
            super.canvas.setBackground(colorParam2);
        }
        if (!"no".equalsIgnoreCase(this.getParameter("UsePanner", "no"))) {
            super.canvas.add(new Panner(), 0);
            super.canvas.add(new Panner(), 1);
            if (b) {
                super.canvas.add(new Panner(), 2);
            }
        }
        if (!"no".equalsIgnoreCase(this.getParameter("UseGrid", "no"))) {
            final Grid grid = new Grid();
            final Color colorParam3 = this.getColorParam("GridColor");
            if (colorParam3 != null) {
                grid.setColor(colorParam3);
            }
            super.canvas.add(grid, 0);
            final Grid grid2 = new Grid();
            final Color colorParam4 = this.getColorParam("GridColor");
            if (colorParam4 != null) {
                grid2.setColor(colorParam4);
            }
            super.canvas.add(grid2, 1);
            if (b) {
                final Grid grid3 = new Grid();
                final Color colorParam5 = this.getColorParam("GridColor");
                if (colorParam5 != null) {
                    grid3.setColor(colorParam5);
                }
                super.canvas.add(grid3, 2);
            }
        }
        super.canvas.add(this.makeAxes(), 0);
        super.canvas.add(this.makeAxes(), 1);
        if (b) {
            super.canvas.add(this.makeAxes(), 2);
        }
        if (!"no".equalsIgnoreCase(this.getParameter("UseMouseZoom", "no"))) {
            super.canvas.setHandleMouseZooms(true);
        }
        if ("yes".equalsIgnoreCase(this.getParameter("UseOffscreenCanvas", "yes"))) {
            super.canvas.setUseOffscreenCanvas(true);
        }
        super.mainController.setErrorReporter(super.canvas);
        super.mainPanel.add(super.canvas, "Center");
        super.canvas.add(graph1D, 0);
        super.canvas.add(graph1D2, 1);
        if (b) {
            super.canvas.add(graph1D3, 2);
        }
        final Color colorParam6 = this.getColorParam("TangentColor", Color.red);
        final Color colorParam7 = this.getColorParam("TangentColor2", new Color(0, 180, 0));
        super.mainController.remove(super.canvas);
        super.mainController.add(graph1D);
        super.mainController.add(graph1D2);
        if (b) {
            super.mainController.add(graph1D3);
        }
        this.subController = new Controller();
        super.mainController.add(this.subController);
        final TangentLine tangentLine = new TangentLine(this.xInput, this.func);
        final Crosshair crosshair = new Crosshair(this.xInput, this.deriv);
        tangentLine.setColor(colorParam6);
        crosshair.setColor(colorParam6);
        super.canvas.add(tangentLine, 0);
        super.canvas.add(crosshair, 1);
        this.subController.add(tangentLine);
        this.subController.add(crosshair);
        if (b) {
            final TangentLine tangentLine2 = new TangentLine(this.xInput, this.deriv);
            final Crosshair crosshair2 = new Crosshair(this.xInput, this.deriv2);
            tangentLine2.setColor(colorParam7);
            crosshair2.setColor(colorParam7);
            super.canvas.add(tangentLine2, 1);
            super.canvas.add(crosshair2, 2);
            this.subController.add(tangentLine2);
            this.subController.add(crosshair2);
        }
        this.functionName = this.getParameter("FunctionName", "f");
        final String parameter = this.getParameter("YName", "y");
        final Color colorParam8 = this.getColorParam("TextColor", Color.black);
        final Color colorParam9 = this.getColorParam("TextBackground", Color.white);
        if ("yes".equalsIgnoreCase(this.getParameter("ShowGraphLabels", "yes"))) {
            final DrawString drawString = new DrawString(String.valueOf(parameter) + " = " + this.functionName + "(" + super.xVar.getName() + ")");
            drawString.setColor(colorParam8);
            drawString.setBackgroundColor(colorParam9);
            drawString.setFrameWidth(1);
            super.canvas.add(drawString, 0);
            final DrawString drawString2 = new DrawString(String.valueOf(parameter) + " = " + this.functionName + " ' (" + super.xVar.getName() + ")");
            drawString2.setColor(colorParam8);
            drawString2.setBackgroundColor(colorParam9);
            drawString2.setFrameWidth(1);
            super.canvas.add(drawString2, 1);
            if (b) {
                final DrawString drawString3 = new DrawString(String.valueOf(parameter) + " = " + this.functionName + " ' ' (" + super.xVar.getName() + ")");
                drawString3.setColor(colorParam8);
                drawString3.setBackgroundColor(colorParam9);
                drawString3.setFrameWidth(1);
                super.canvas.add(drawString3, 2);
            }
        }
        if ("yes".equalsIgnoreCase(this.getParameter("ShowValues", "yes"))) {
            final DrawString drawString4 = new DrawString(String.valueOf(this.functionName) + "(#) = #", 8, new Value[] { this.xInput, new ValueMath(this.func, this.xInput) });
            drawString4.setColor(colorParam8);
            drawString4.setBackgroundColor(colorParam9);
            drawString4.setFrameWidth(1);
            drawString4.setNumSize(7);
            super.canvas.add(drawString4, 0);
            this.subController.add(drawString4);
            final DrawString drawString5 = new DrawString(String.valueOf(this.functionName) + " ' (#) = #", 8, new Value[] { this.xInput, new ValueMath(this.deriv, this.xInput) });
            drawString5.setColor(colorParam8);
            drawString5.setBackgroundColor(colorParam9);
            drawString5.setFrameWidth(1);
            drawString5.setNumSize(7);
            super.canvas.add(drawString5, 1);
            this.subController.add(drawString5);
            if (b) {
                final DrawString drawString6 = new DrawString(String.valueOf(this.functionName) + " ' ' (#) = #", 8, new Value[] { this.xInput, new ValueMath(this.deriv2, this.xInput) });
                drawString6.setColor(colorParam8);
                drawString6.setBackgroundColor(colorParam9);
                drawString6.setFrameWidth(1);
                drawString6.setNumSize(7);
                super.canvas.add(drawString6, 2);
                this.subController.add(drawString6);
            }
        }
    }
    
    protected void addCanvasBorder() {
        final double[] numericParam = this.getNumericParam("BorderWidth");
        int n;
        if (numericParam == null || numericParam.length == 0 || numericParam[0] > 25.0) {
            n = 2;
        }
        else {
            n = (int)Math.round(numericParam[0]);
        }
        if (n > 0) {
            super.canvas.add(new DrawBorder(this.getColorParam("BorderColor", Color.black), n), 0);
            super.canvas.add(new DrawBorder(this.getColorParam("BorderColor", Color.black), n), 1);
            if (this.deriv2 != null) {
                super.canvas.add(new DrawBorder(this.getColorParam("BorderColor", Color.black), n), 2);
            }
        }
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
                        this.xInput.setVal(new Double(stringTokenizer.nextToken()));
                    }
                    catch (NumberFormatException ex2) {}
                }
            }
        }
        if (super.functionInput != null) {
            super.functionInput.setText(substring);
        }
        else {
            try {
                final ExpressionProgram parse = super.parser.parse(substring);
                this.derivExpression = parse.derivative(super.xVar);
                ((WrapperFunction)this.func).setFunction(new SimpleFunction(parse, super.xVar));
            }
            catch (ParseError parseError) {}
        }
        final CoordinateRect coordinateRect = super.canvas.getCoordinateRect(0);
        coordinateRect.setLimits(limits);
        coordinateRect.setRestoreBuffer();
        super.canvas.getCoordinateRect(1).setRestoreBuffer();
        if (this.deriv2 != null) {
            super.canvas.getCoordinateRect(0).setRestoreBuffer();
        }
        super.mainController.compute();
    }
    
    public Derivatives() {
        this.subController = new Controller();
    }
    
    private class ExprLbl extends Label implements Computable
    {
        String label;
        
        ExprLbl(final String label) {
            this.label = label;
            this.compute();
        }
        
        public void compute() {
            this.setText(String.valueOf(this.label) + Derivatives.this.derivExpression.toString());
        }
    }
}
