import edu.hws.jcm.data.ParseError;
import java.util.StringTokenizer;
import java.awt.event.ActionEvent;
import java.awt.event.ItemEvent;
import edu.hws.jcm.awt.ErrorReporter;
import edu.hws.jcm.awt.Computable;
import java.awt.Label;
import java.awt.GridLayout;
import java.awt.LayoutManager;
import java.awt.BorderLayout;
import java.awt.Panel;
import edu.hws.jcm.draw.DrawBorder;
import edu.hws.jcm.draw.Panner;
import edu.hws.jcm.draw.DrawString;
import edu.hws.jcm.draw.TangentLine;
import edu.hws.jcm.draw.DrawGeometric;
import edu.hws.jcm.data.Constant;
import edu.hws.jcm.data.Value;
import edu.hws.jcm.data.ValueMath;
import edu.hws.jcm.draw.DraggablePoint;
import edu.hws.jcm.functions.ExpressionFunction;
import edu.hws.jcm.data.MathObject;
import edu.hws.jcm.data.Expression;
import edu.hws.jcm.data.SimpleFunction;
import edu.hws.jcm.draw.Drawable;
import edu.hws.jcm.draw.Grid;
import edu.hws.jcm.draw.CoordinateRect;
import java.awt.Component;
import edu.hws.jcm.draw.DisplayCanvas;
import edu.hws.jcm.data.Parser;
import java.awt.Color;
import edu.hws.jcm.awt.Controller;
import edu.hws.jcm.functions.WrapperFunction;
import edu.hws.jcm.functions.TableFunctionGraph;
import edu.hws.jcm.functions.TableFunction;
import edu.hws.jcm.draw.Graph1D;
import edu.hws.jcm.data.Function;
import edu.hws.jcm.awt.ExpressionInput;
import java.awt.Checkbox;
import edu.hws.jcm.data.Variable;
import java.awt.Button;
import java.awt.event.ItemListener;
import java.awt.event.ActionListener;

// 
// Decompiled by Procyon v0.5.30
// 

public class FunctionComposition extends GenericGraphApplet implements ActionListener, ItemListener
{
    Button zoomInButton;
    Button zoomOutButton;
    Button restoreButton;
    Button equalizeButton;
    Button fComputeButton;
    Button gComputeButton;
    Variable pointX;
    Checkbox fCheck;
    Checkbox gCheck;
    ExpressionInput fInput;
    ExpressionInput gInput;
    Function fFunc;
    Function gFunc;
    Graph1D fGraph;
    Graph1D gGraph;
    TableFunction fTable;
    TableFunction gTable;
    TableFunctionGraph fTableGraph;
    TableFunctionGraph gTableGraph;
    boolean fTableShown;
    boolean gTableShown;
    String fSaveText;
    String gSaveText;
    WrapperFunction fWrapper;
    WrapperFunction gWrapper;
    
    public void setUpMainPanel() {
        super.mainController = new Controller();
        super.defaultFrameSize = new int[] { 606, 306 };
        final Color colorParam = this.getColorParam("TextColor", Color.black);
        final Color colorParam2 = this.getColorParam("CanvasColor", Color.white);
        final boolean b = "no".equalsIgnoreCase(this.getParameter("UseFunctionInput", "yes")) ^ true;
        final double[] numericParam = this.getNumericParam("Insets");
        if (numericParam == null || numericParam.length == 0 || numericParam[0] < 0.0 || numericParam[0] > 50.0) {
            super.mainPanel.setInsetGap(3);
        }
        else {
            super.mainPanel.setInsetGap((int)Math.round(numericParam[0]));
        }
        super.parser = new Parser(null, 0);
        this.setUpParser();
        this.setUpExampleMenu();
        this.setUpTopPanel();
        super.mainPanel.setBackground(this.getColorParam("BackgroundColor", Color.gray));
        super.mainPanel.setForeground(this.getColorParam("ForegroundColor", Color.black));
        double[] numericParam2 = this.getNumericParam("Limits");
        if (numericParam2 == null || numericParam2.length < 4) {
            numericParam2 = new double[] { -5.0, 5.0, -5.0, 5.0 };
        }
        super.canvas = new DisplayCanvas();
        super.mainPanel.add(super.canvas, "Center");
        super.canvas.setBackground(colorParam2);
        if (!"no".equalsIgnoreCase(this.getParameter("UseMouseZoom", "no"))) {
            super.canvas.setHandleMouseZooms(true);
        }
        if (!"no".equalsIgnoreCase(this.getParameter("UseOffscreenCanvas", "yes"))) {
            super.canvas.setUseOffscreenCanvas(true);
        }
        super.canvas.addCoordinateRect(new CoordinateRect(numericParam2[0], numericParam2[1], numericParam2[2], numericParam2[3]), 0.0, 0.3333333333333333, 0.0, 1.0, null);
        super.canvas.addCoordinateRect(new CoordinateRect(numericParam2[0], numericParam2[1], numericParam2[2], numericParam2[3]), 0.3333333333333333, 0.6666666666666666, 0.0, 1.0, null);
        super.canvas.addCoordinateRect(new CoordinateRect(numericParam2[0], numericParam2[1], numericParam2[2], numericParam2[3]), 0.6666666666666666, 1.0, 0.0, 1.0, null);
        if (!"no".equalsIgnoreCase(this.getParameter("UseGrid", "no"))) {
            final Color colorParam3 = this.getColorParam("GridColor");
            final Grid grid = new Grid();
            if (colorParam3 != null) {
                grid.setColor(colorParam3);
            }
            super.canvas.add(grid, 0);
            final Grid grid2 = new Grid();
            if (colorParam3 != null) {
                grid2.setColor(colorParam3);
            }
            super.canvas.add(grid2, 1);
            final Grid grid3 = new Grid();
            if (colorParam3 != null) {
                grid3.setColor(colorParam3);
            }
            super.canvas.add(grid3, 2);
        }
        super.canvas.add(this.makeAxes(), 0);
        super.canvas.add(this.makeAxes(), 1);
        super.canvas.add(this.makeAxes(), 2);
        this.fSaveText = this.getParameter("Function", " 3 - " + super.xVar.getName() + "^2/2");
        this.gSaveText = this.getParameter("SecondFunction", " sin(" + super.xVar.getName() + ")");
        if (b) {
            this.fInput = new ExpressionInput(this.fSaveText, super.parser);
            this.gInput = new ExpressionInput(this.gSaveText, super.parser);
            this.fFunc = this.fInput.getFunction(super.xVar);
            this.gFunc = this.gInput.getFunction(super.xVar);
        }
        else {
            this.fFunc = new SimpleFunction(super.parser.parse(this.fSaveText), super.xVar);
            this.gFunc = new SimpleFunction(super.parser.parse(this.gSaveText), super.xVar);
        }
        this.fGraph = new Graph1D(this.fFunc);
        this.gGraph = new Graph1D(this.gFunc);
        (this.fWrapper = new WrapperFunction(this.fFunc)).setName("f");
        (this.gWrapper = new WrapperFunction(this.gFunc)).setName("g");
        final Parser parser = new Parser();
        parser.add(this.fWrapper);
        parser.add(this.gWrapper);
        final ExpressionFunction expressionFunction = new ExpressionFunction("h", new String[] { "x" }, "g(f(" + super.xVar.getName() + "))", parser);
        final Graph1D graph1D = new Graph1D(expressionFunction);
        final boolean b2 = false;
        this.gTableShown = b2;
        this.fTableShown = b2;
        String parameter = this.getParameter("TableFunction");
        if (parameter != null) {
            try {
                this.fTable = this.parseTableFuncDef(parameter);
            }
            catch (Exception ex) {
                parameter = null;
            }
        }
        if (parameter == null) {
            (this.fTable = new TableFunction()).addIntervals(6, -5.0, 5.0);
        }
        (this.fTableGraph = new TableFunctionGraph(this.fTable)).setInteractive(true);
        if (this.getParameter("Function") == null && parameter != null) {
            this.fGraph.setVisible(false);
            this.fTableShown = true;
            this.fWrapper.setFunction(this.fTable);
            if (this.fInput != null) {
                this.fInput.setEnabled(false);
                this.fInput.setThrowErrors(false);
                this.fInput.setText("Drag points to modify function.");
            }
        }
        else {
            this.fTableGraph.setVisible(false);
        }
        String parameter2 = this.getParameter("SecondTableFunction");
        if (parameter2 != null) {
            try {
                this.gTable = this.parseTableFuncDef(parameter2);
            }
            catch (Exception ex2) {
                parameter2 = null;
            }
        }
        if (parameter2 == null) {
            (this.gTable = new TableFunction()).addIntervals(6, -5.0, 5.0);
        }
        (this.gTableGraph = new TableFunctionGraph(this.gTable)).setInteractive(true);
        if (this.getParameter("SecondFunction") == null && parameter2 != null) {
            this.gGraph.setVisible(false);
            this.gTableShown = true;
            this.gWrapper.setFunction(this.gTable);
            if (this.gInput != null) {
                this.gInput.setEnabled(false);
                this.gInput.setThrowErrors(false);
                this.gInput.setText("Drag points to modify function.");
            }
        }
        else {
            this.gTableGraph.setVisible(false);
        }
        final DraggablePoint draggablePoint = new DraggablePoint(1);
        final Color colorParam4 = this.getColorParam("PointColor1", Color.red);
        final Color colorParam5 = this.getColorParam("PointColor2", new Color(0, 200, 0));
        final Color colorParam6 = this.getColorParam("PointColor3", new Color(100, 100, 255));
        draggablePoint.setColor(colorParam4);
        draggablePoint.clampY(0.0);
        draggablePoint.setLocation(1.0, 0.0);
        super.canvas.add(draggablePoint, 0);
        this.pointX = draggablePoint.getXVar();
        final ValueMath valueMath = new ValueMath(this.fWrapper, this.pointX);
        final ValueMath valueMath2 = new ValueMath(this.gWrapper, valueMath);
        final DrawGeometric drawGeometric = new DrawGeometric(0, this.pointX, new Constant(0.0), this.pointX, valueMath);
        drawGeometric.setColor(colorParam4);
        super.canvas.add(drawGeometric, 0);
        final DrawGeometric drawGeometric2 = new DrawGeometric(0, this.pointX, valueMath, new Constant(0.0), valueMath);
        drawGeometric2.setColor(colorParam5);
        super.canvas.add(drawGeometric2, 0);
        final DrawGeometric drawGeometric3 = new DrawGeometric(0, valueMath, new Constant(0.0), valueMath, valueMath2);
        drawGeometric3.setColor(colorParam5);
        super.canvas.add(drawGeometric3, 1);
        final DrawGeometric drawGeometric4 = new DrawGeometric(0, valueMath, valueMath2, new Constant(0.0), valueMath2);
        drawGeometric4.setColor(colorParam6);
        super.canvas.add(drawGeometric4, 1);
        final DrawGeometric drawGeometric5 = new DrawGeometric(0, this.pointX, new Constant(0.0), this.pointX, valueMath2);
        drawGeometric5.setColor(colorParam4);
        super.canvas.add(drawGeometric5, 2);
        final DrawGeometric drawGeometric6 = new DrawGeometric(0, this.pointX, valueMath2, new Constant(0.0), valueMath2);
        drawGeometric6.setColor(colorParam6);
        super.canvas.add(drawGeometric6, 2);
        drawGeometric.setLineWidth(2);
        drawGeometric2.setLineWidth(2);
        drawGeometric3.setLineWidth(2);
        drawGeometric4.setLineWidth(2);
        drawGeometric5.setLineWidth(2);
        drawGeometric6.setLineWidth(2);
        final Color colorParam7 = this.getColorParam("GraphColor", Color.magenta);
        this.fGraph.setColor(colorParam7);
        this.gGraph.setColor(colorParam7);
        this.fTableGraph.setColor(colorParam7);
        this.gTableGraph.setColor(colorParam7);
        graph1D.setColor(colorParam7);
        super.canvas.add(this.fGraph, 0);
        super.canvas.add(this.fTableGraph, 0);
        super.canvas.add(this.gGraph, 1);
        super.canvas.add(this.gTableGraph, 1);
        super.canvas.add(graph1D, 2);
        DrawGeometric drawGeometric7 = null;
        DrawGeometric drawGeometric8 = null;
        DrawGeometric drawGeometric9 = null;
        DrawString drawString = null;
        DrawString drawString2 = null;
        DrawString drawString3 = null;
        if (!"no".equalsIgnoreCase(this.getParameter("ShowTangents", "no"))) {
            final Color colorParam8 = this.getColorParam("TangentColor", Color.gray);
            drawGeometric7 = new TangentLine(this.pointX, this.fWrapper);
            drawGeometric7.setColor(colorParam8);
            super.canvas.add(drawGeometric7, 0);
            drawGeometric8 = new TangentLine(valueMath, this.gWrapper);
            drawGeometric8.setColor(colorParam8);
            super.canvas.add(drawGeometric8, 1);
            drawGeometric9 = new TangentLine(this.pointX, expressionFunction);
            drawGeometric9.setColor(colorParam8);
            super.canvas.add(drawGeometric9, 2);
            if ("yes".equalsIgnoreCase(this.getParameter("ShowSlopes", "yes"))) {
                drawString = new DrawString("slope = #", 2, new Value[] { new ValueMath(this.fWrapper.derivative(1), this.pointX) });
                drawString.setColor(colorParam);
                drawString.setNumSize(6);
                super.canvas.add(drawString, 0);
                drawString2 = new DrawString("slope = #", 2, new Value[] { new ValueMath(this.gWrapper.derivative(1), valueMath) });
                drawString2.setColor(colorParam);
                drawString2.setNumSize(6);
                super.canvas.add(drawString2, 1);
                drawString3 = new DrawString("slope = #", 2, new Value[] { new ValueMath(expressionFunction.derivative(1), this.pointX) });
                drawString3.setColor(colorParam);
                drawString3.setNumSize(6);
                super.canvas.add(drawString3, 2);
            }
        }
        if ("yes".equalsIgnoreCase(this.getParameter("ShowFunctionNames", "yes"))) {
            final DrawString drawString4 = new DrawString("y=f(" + super.xVar.getName() + ")");
            drawString4.setColor(colorParam);
            super.canvas.add(drawString4, 0);
            final DrawString drawString5 = new DrawString("y=g(" + super.xVar.getName() + ")");
            drawString5.setColor(colorParam);
            super.canvas.add(drawString5, 1);
            final DrawString drawString6 = new DrawString("y=g(f(" + super.xVar.getName() + "))");
            drawString6.setColor(colorParam);
            super.canvas.add(drawString6, 2);
        }
        DrawString drawString7 = null;
        DrawString drawString8 = null;
        DrawString drawString9 = null;
        if ("yes".equalsIgnoreCase(this.getParameter("ShowCoordinates", "yes"))) {
            drawString7 = new DrawString("f(#) = #", 9, new Value[] { this.pointX, valueMath });
            drawString7.setNumSize(6);
            drawString7.setColor(colorParam);
            drawString7.setBackgroundColor(colorParam2);
            super.canvas.add(drawString7, 0);
            drawString8 = new DrawString("g(#) = #", 9, new Value[] { valueMath, valueMath2 });
            drawString8.setNumSize(6);
            drawString8.setColor(colorParam);
            drawString8.setBackgroundColor(colorParam2);
            super.canvas.add(drawString8, 1);
            drawString9 = new DrawString("g(f(#)) = #", 9, new Value[] { this.pointX, valueMath2 });
            drawString9.setNumSize(6);
            drawString9.setColor(colorParam);
            drawString9.setBackgroundColor(colorParam2);
            super.canvas.add(drawString9, 2);
        }
        if (!"no".equalsIgnoreCase(this.getParameter("UsePanner", "no"))) {
            super.canvas.add(new Panner(), 0);
            super.canvas.add(new Panner(), 1);
            super.canvas.add(new Panner(), 2);
        }
        final double[] numericParam3 = this.getNumericParam("BorderWidth");
        int n;
        if (numericParam3 == null || numericParam3.length == 0 || numericParam3[0] > 25.0) {
            n = 1;
        }
        else {
            n = (int)Math.round(numericParam3[0]);
        }
        if (n > 0) {
            final Color colorParam9 = this.getColorParam("BorderColor", Color.black);
            super.canvas.add(new DrawBorder(colorParam9, n), 0);
            super.canvas.add(new DrawBorder(colorParam9, n), 1);
            super.canvas.add(new DrawBorder(colorParam9, n), 2);
        }
        if (b) {
            final Panel panel = new Panel();
            panel.setLayout(new BorderLayout(3, 3));
            panel.setBackground(this.getColorParam("PanelBackground", Color.lightGray));
            super.mainPanel.add(panel, "South");
            final Panel panel2 = new Panel();
            panel2.setLayout(new GridLayout(0, 1));
            panel.add(panel2, "Center");
            final Panel panel3 = new Panel();
            panel3.setLayout(new GridLayout(0, 2));
            panel.add(panel3, "East");
            final Panel panel4 = new Panel();
            panel4.setLayout(new BorderLayout());
            panel4.add(new Label(" f(" + super.xVar.getName() + ") = "), "West");
            panel4.add(this.fInput, "Center");
            final Panel panel5 = new Panel();
            panel5.setLayout(new GridLayout(1, 2));
            this.fCheck = new Checkbox("Use Mouse");
            if (this.fTableShown) {
                this.fCheck.setState(true);
            }
            this.fCheck.addItemListener(this);
            panel5.add(this.fCheck);
            (this.fComputeButton = new Button("New f(" + super.xVar.getName() + ")")).addActionListener(this);
            panel5.add(this.fComputeButton);
            panel4.add(panel5, "East");
            panel2.add(panel4);
            final Panel panel6 = new Panel();
            panel6.setLayout(new BorderLayout());
            panel6.add(new Label(" g(" + super.xVar.getName() + ") = "), "West");
            panel6.add(this.gInput, "Center");
            final Panel panel7 = new Panel();
            panel7.setLayout(new GridLayout(1, 2));
            this.gCheck = new Checkbox("Use Mouse");
            if (this.gTableShown) {
                this.gCheck.setState(true);
            }
            this.gCheck.addItemListener(this);
            panel7.add(this.gCheck);
            (this.gComputeButton = new Button("New g(" + super.xVar.getName() + ")")).addActionListener(this);
            panel7.add(this.gComputeButton);
            panel6.add(panel7, "East");
            panel2.add(panel6);
            panel3.add(this.zoomInButton = new Button("Zoom In"));
            this.zoomInButton.addActionListener(this);
            panel3.add(this.zoomOutButton = new Button("Zoom Out"));
            this.zoomOutButton.addActionListener(this);
            (this.equalizeButton = new Button("EqualizeAxes")).addActionListener(this);
            panel3.add(this.equalizeButton);
            panel3.add(this.restoreButton = new Button("Restore Limits"));
            this.restoreButton.addActionListener(this);
            this.fInput.setOnUserAction(super.mainController);
            this.gInput.setOnUserAction(super.mainController);
            super.mainController.add(this.fInput);
            super.mainController.add(this.gInput);
        }
        this.fTableGraph.setOnDrag(super.mainController);
        this.gTableGraph.setOnDrag(super.mainController);
        super.mainController.add(super.canvas);
        super.mainController.setErrorReporter(super.canvas);
        final Controller onUserAction = new Controller();
        super.mainController.add(onUserAction);
        draggablePoint.setOnUserAction(onUserAction);
        onUserAction.add(draggablePoint);
        onUserAction.add(drawGeometric);
        onUserAction.add(drawGeometric2);
        onUserAction.add(drawGeometric3);
        onUserAction.add(drawGeometric4);
        onUserAction.add(drawGeometric5);
        onUserAction.add(drawGeometric6);
        if (drawString7 != null) {
            onUserAction.add(drawString7);
            onUserAction.add(drawString8);
            onUserAction.add(drawString9);
        }
        if (drawGeometric7 != null) {
            onUserAction.add(drawGeometric7);
            onUserAction.add(drawGeometric8);
            onUserAction.add(drawGeometric9);
        }
        if (drawString != null) {
            onUserAction.add(drawString);
            onUserAction.add(drawString2);
            onUserAction.add(drawString3);
        }
    }
    
    public void itemStateChanged(final ItemEvent itemEvent) {
        final Object source = itemEvent.getSource();
        if (source == this.fCheck) {
            final boolean state = this.fCheck.getState();
            if (state == this.fTableShown) {
                return;
            }
            this.fTableShown = state;
            this.fGraph.setVisible(this.fTableShown ^ true);
            this.fTableGraph.setVisible(this.fTableShown);
            if (this.fTableShown) {
                this.fWrapper.setFunction(this.fTable);
                this.fSaveText = this.fInput.getText();
                this.fInput.setText("Drag points to modify function.");
                this.fInput.setThrowErrors(false);
                this.fInput.setEnabled(false);
            }
            else {
                this.fWrapper.setFunction(this.fFunc);
                this.fInput.setText(this.fSaveText);
                this.fInput.setThrowErrors(true);
                this.fInput.setEnabled(true);
            }
            super.mainController.compute();
        }
        else if (source == this.gCheck) {
            final boolean state2 = this.gCheck.getState();
            if (state2 == this.gTableShown) {
                return;
            }
            this.gTableShown = state2;
            this.gGraph.setVisible(this.gTableShown ^ true);
            this.gTableGraph.setVisible(this.gTableShown);
            if (this.gTableShown) {
                this.gWrapper.setFunction(this.gTable);
                this.gSaveText = this.gInput.getText();
                this.gInput.setText("Drag points to modify function.");
                this.gInput.setThrowErrors(false);
                this.gInput.setEnabled(false);
            }
            else {
                this.gWrapper.setFunction(this.gFunc);
                this.gInput.setText(this.gSaveText);
                this.gInput.setThrowErrors(true);
                this.gInput.setEnabled(true);
            }
            super.mainController.compute();
        }
    }
    
    public void actionPerformed(final ActionEvent actionEvent) {
        final Object source = actionEvent.getSource();
        if (source == this.zoomInButton) {
            super.canvas.getCoordinateRect(0).zoomIn();
            super.canvas.getCoordinateRect(1).zoomIn();
            super.canvas.getCoordinateRect(2).zoomIn();
        }
        else if (source == this.zoomOutButton) {
            super.canvas.getCoordinateRect(0).zoomOut();
            super.canvas.getCoordinateRect(1).zoomOut();
            super.canvas.getCoordinateRect(2).zoomOut();
        }
        else if (source == this.restoreButton) {
            super.canvas.getCoordinateRect(0).restore();
            super.canvas.getCoordinateRect(1).restore();
            super.canvas.getCoordinateRect(2).restore();
        }
        else if (source == this.equalizeButton) {
            super.canvas.getCoordinateRect(0).equalizeAxes();
            super.canvas.getCoordinateRect(1).equalizeAxes();
            super.canvas.getCoordinateRect(2).equalizeAxes();
        }
        else if (source == this.fComputeButton) {
            if (this.fTableShown) {
                final int pointCount = this.fTable.getPointCount();
                double ymin;
                if (super.canvas.getCoordinateRect(0).getYmin() > 0.0 || super.canvas.getCoordinateRect(0).getYmax() < 0.0) {
                    ymin = super.canvas.getCoordinateRect(0).getYmin();
                }
                else {
                    ymin = 0.0;
                }
                for (int i = 0; i < pointCount; ++i) {
                    this.fTable.setY(i, ymin);
                }
            }
            super.mainController.compute();
        }
        else if (source == this.gComputeButton) {
            if (this.gTableShown) {
                final int pointCount2 = this.gTable.getPointCount();
                double ymin2;
                if (super.canvas.getCoordinateRect(1).getYmin() > 0.0 || super.canvas.getCoordinateRect(1).getYmax() < 0.0) {
                    ymin2 = super.canvas.getCoordinateRect(1).getYmin();
                }
                else {
                    ymin2 = 0.0;
                }
                for (int j = 0; j < pointCount2; ++j) {
                    this.gTable.setY(j, ymin2);
                }
            }
            super.mainController.compute();
        }
        else {
            super.actionPerformed(actionEvent);
        }
    }
    
    protected void doLoadExample(String trim) {
        final int index = trim.indexOf(";");
        if (index == -1) {
            System.out.println("Illegal example -- must have two functions");
            return;
        }
        String text = trim.substring(index + 1);
        trim = trim.substring(0, index).trim();
        final int index2 = text.indexOf(";");
        final double[] limits = { -5.0, 5.0, -5.0, 5.0 };
        if (index2 > 0) {
            final String substring = text.substring(index2 + 1);
            text = text.substring(0, index2).trim();
            final StringTokenizer stringTokenizer = new StringTokenizer(substring, " ,");
            if (stringTokenizer.countTokens() >= 4) {
                for (int i = 0; i < 4; ++i) {
                    try {
                        limits[i] = new Double(stringTokenizer.nextToken());
                    }
                    catch (NumberFormatException ex) {}
                }
            }
            if (stringTokenizer.hasMoreTokens()) {
                try {
                    this.pointX.setVal(new Double(stringTokenizer.nextToken()));
                }
                catch (NumberFormatException ex2) {}
            }
        }
        if (trim.startsWith("table")) {
            try {
                final TableFunction tableFuncDef = this.parseTableFuncDef(trim);
                this.fTable = tableFuncDef;
                this.fTableGraph.setFunction(tableFuncDef);
                this.fWrapper.setFunction(tableFuncDef);
                if (!this.fTableShown) {
                    if (this.fCheck != null) {
                        this.fCheck.setState(true);
                    }
                    this.fGraph.setVisible(false);
                    this.fTableGraph.setVisible(true);
                    this.fTableShown = true;
                    if (this.fInput != null) {
                        this.fSaveText = this.fInput.getText();
                        this.fInput.setText("Drag points to modify function.");
                        this.fInput.setThrowErrors(false);
                        this.fInput.setEnabled(false);
                    }
                }
            }
            catch (ParseError parseError) {
                System.out.println("Illegal table function for f(x) in example.");
            }
        }
        else {
            try {
                if (this.fInput != null) {
                    this.fInput.setText(trim);
                }
                else {
                    final SimpleFunction function = new SimpleFunction(super.parser.parse(trim), super.xVar);
                    this.fFunc = function;
                    this.fGraph.setFunction(function);
                    this.fWrapper.setFunction(function);
                }
                if (this.fTableShown) {
                    if (this.fCheck != null) {
                        this.fCheck.setState(false);
                    }
                    this.fGraph.setVisible(true);
                    this.fTableGraph.setVisible(false);
                    this.fTableShown = false;
                    if (this.fInput != null) {
                        this.fInput.setThrowErrors(true);
                        this.fInput.setEnabled(true);
                    }
                }
            }
            catch (ParseError parseError2) {
                System.out.println("Parse error for f(x) in example.");
            }
        }
        if (text.startsWith("table")) {
            try {
                final TableFunction tableFuncDef2 = this.parseTableFuncDef(text);
                this.gTable = tableFuncDef2;
                this.gTableGraph.setFunction(tableFuncDef2);
                this.gWrapper.setFunction(tableFuncDef2);
                if (!this.gTableShown) {
                    if (this.gCheck != null) {
                        this.gCheck.setState(true);
                    }
                    this.gGraph.setVisible(false);
                    this.gTableGraph.setVisible(true);
                    this.gTableShown = true;
                    if (this.gInput != null) {
                        this.gSaveText = this.gInput.getText();
                        this.gInput.setText("Drag points to modify function.");
                        this.gInput.setThrowErrors(false);
                        this.gInput.setEnabled(false);
                    }
                }
            }
            catch (ParseError parseError3) {
                System.out.println("Illegal table function for g(x) in example.");
            }
        }
        else {
            try {
                if (this.gInput != null) {
                    this.gInput.setText(text);
                }
                else {
                    final SimpleFunction function2 = new SimpleFunction(super.parser.parse(text), super.xVar);
                    this.gFunc = function2;
                    this.gGraph.setFunction(function2);
                    this.gWrapper.setFunction(function2);
                }
                if (this.gTableShown) {
                    if (this.gCheck != null) {
                        this.gCheck.setState(false);
                    }
                    this.gGraph.setVisible(true);
                    this.gTableGraph.setVisible(false);
                    this.gTableShown = false;
                    if (this.gInput != null) {
                        this.gInput.setThrowErrors(true);
                        this.gInput.setEnabled(true);
                    }
                }
            }
            catch (ParseError parseError4) {
                System.out.println("Parse error for g(x) in example.");
            }
        }
        final CoordinateRect coordinateRect = super.canvas.getCoordinateRect(0);
        coordinateRect.setLimits(limits);
        coordinateRect.setRestoreBuffer();
        final CoordinateRect coordinateRect2 = super.canvas.getCoordinateRect(1);
        coordinateRect2.setLimits(limits);
        coordinateRect2.setRestoreBuffer();
        final CoordinateRect coordinateRect3 = super.canvas.getCoordinateRect(2);
        coordinateRect3.setLimits(limits);
        coordinateRect3.setRestoreBuffer();
        super.mainController.compute();
    }
}
