// 
// Decompiled by Procyon v0.5.30
// 

package xfunctions;

import xfunctions.functions.Utilities;
import java.awt.Event;
import xfunctions.graphs.DisplayCanvas;
import java.awt.Dimension;
import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Component;
import java.awt.LayoutManager;
import java.awt.GridLayout;
import java.awt.Color;
import xfunctions.graphs.Drawable;
import xfunctions.graphs.Axes;
import xfunctions.graphs.NumberInputPanel;
import xfunctions.functions.ParseError;
import java.awt.TextField;
import java.awt.Panel;
import java.awt.Label;
import xfunctions.graphs.HiliteDisplayCanvas;
import xfunctions.graphs.CoordinateRect;
import xfunctions.graphs.Graph1D;
import xfunctions.functions.Expression;
import xfunctions.functions.Variable;
import xfunctions.functions.Parser;

public class DerivativesPanel extends GenericPanel
{
    Parser parser;
    Variable xVar;
    Expression exp;
    Expression deriv;
    Expression deriv2;
    Graph1D expGraph;
    Graph1D derivGraph;
    Graph1D deriv2Graph;
    CoordinateRect expCoords;
    CoordinateRect derivCoords;
    CoordinateRect deriv2Coords;
    HiliteDisplayCanvas expCanvas;
    HiliteDisplayCanvas derivCanvas;
    HiliteDisplayCanvas deriv2Canvas;
    Label expLabel;
    Label derivLabel;
    Label deriv2Label;
    Label xLabel;
    Panel functionBar;
    Panel buttonBar;
    Panel labelPanel;
    Panel canvasPanel;
    Panel messageBar;
    TextField functionInput;
    String functionDefinition;
    double[] parameterValues;
    private HiliteDisplayCanvas draggingOn;
    
    public DerivativesPanel() {
        this((Parser)null);
    }
    
    public DerivativesPanel(final Parser parser) {
        this.functionDefinition = "sin(2*x) + 2*cos(x)";
        this.parameterValues = new double[] { -5.0, 5.0, -5.0, 5.0 };
        if (parser == null) {
            this.parser = new Parser();
        }
        else {
            this.parser = new Parser(parser);
        }
        this.xVar = this.parser.defineVariable("x");
        try {
            this.exp = this.parser.parse(this.functionDefinition);
            this.deriv = this.exp.derivative(this.xVar);
            this.deriv2 = this.deriv.derivative(this.xVar);
        }
        catch (ParseError parseError) {}
        (super.numberInput = new NumberInputPanel()).addRealRange("x");
        super.numberInput.addRealRange("y");
        super.numberInput.setValues(this.parameterValues);
        this.functionInput = new TextField(this.functionDefinition);
        this.expGraph = new Graph1D(this.exp, this.xVar);
        this.derivGraph = new Graph1D(this.deriv, this.xVar);
        this.deriv2Graph = new Graph1D(this.deriv2, this.xVar);
        (this.expCoords = new CoordinateRect()).add(new Axes());
        this.expCoords.add(this.expGraph);
        (this.derivCoords = new CoordinateRect()).add(new Axes());
        this.derivCoords.add(this.derivGraph);
        (this.deriv2Coords = new CoordinateRect()).add(new Axes());
        this.deriv2Coords.add(this.deriv2Graph);
        this.expCoords.setLimits(-5.0, 5.0, -5.0, 5.0);
        this.derivCoords.setLimits(-5.0, 5.0, -5.0, 5.0);
        this.deriv2Coords.setLimits(-5.0, 5.0, -5.0, 5.0);
        (this.expCanvas = new HiliteDisplayCanvas()).setCoords(this.expCoords);
        this.expCanvas.setGraph(this.expGraph);
        this.expCanvas.setDerivData(this.exp, this.xVar, this.deriv);
        (this.derivCanvas = new HiliteDisplayCanvas()).setCoords(this.derivCoords);
        this.derivCanvas.setGraph(this.derivGraph);
        this.derivCanvas.setDerivData(this.deriv, this.xVar, this.deriv2);
        (this.deriv2Canvas = new HiliteDisplayCanvas()).setCoords(this.deriv2Coords);
        this.deriv2Canvas.setGraph(this.deriv2Graph);
        this.xLabel = new Label("             ");
        this.expLabel = new Label("The Function", 1);
        this.derivLabel = new Label("First Derivative", 1);
        this.deriv2Label = new Label("Second Derivative", 1);
        this.setBackground(Color.lightGray);
        (this.messageBar = new Panel()).setLayout(new GridLayout(1, 2));
        this.messageBar.add(this.xLabel);
        this.messageBar.add(new Label("(Click and Drag on the graphs!)", 2));
        (this.buttonBar = new Panel()).setLayout(new GridLayout(1, 4));
        this.buttonBar.add(new Button("Compute"));
        this.buttonBar.add(new Button("Zoom In"));
        this.buttonBar.add(new Button("Zoom Out"));
        this.buttonBar.add(new Button("Equalize"));
        this.functionBar = new Panel();
        this.functionInput.setBackground(Color.white);
        this.functionBar.setLayout(new GridLayout(2, 1));
        this.functionBar.add(new Label("  Enter a function of x:"));
        final Panel panel = new Panel();
        panel.setLayout(new BorderLayout());
        panel.add("West", new Label("   y = "));
        panel.add("Center", this.functionInput);
        this.functionBar.add(panel);
        (this.canvasPanel = new Panel()).setBackground(Color.lightGray);
        this.canvasPanel.setLayout(new GridLayout(1, 3, 3, 3));
        this.canvasPanel.add(this.expCanvas);
        this.canvasPanel.add(this.derivCanvas);
        this.canvasPanel.add(this.deriv2Canvas);
        (this.labelPanel = new Panel()).setBackground(Color.lightGray);
        this.labelPanel.setLayout(new GridLayout(1, 3, 3, 3));
        this.labelPanel.add(this.expLabel);
        this.labelPanel.add(this.derivLabel);
        this.labelPanel.add(this.deriv2Label);
        this.setLayout(null);
        this.add(this.functionBar);
        this.add(this.buttonBar);
        this.add(super.numberInput);
        this.add(this.messageBar);
        this.add(this.canvasPanel);
        this.add(this.labelPanel);
    }
    
    public void aboutToShow() {
        super.aboutToShow();
        this.expCoords.reset();
        this.derivCoords.reset();
        this.deriv2Coords.reset();
        this.invalidateCanvasses();
        if (this.exp != null) {
            this.setFunction(this.exp);
        }
    }
    
    public void reshape(final int n, final int n2, final int n3, final int n4) {
        super.reshape(n, n2, n3, n4);
        int height = this.labelPanel.preferredSize().height;
        if (height > n4 / 12) {
            height = n4 / 12;
        }
        this.labelPanel.reshape(3, n4 - 3 - height, n3 - 6, height);
        final int n5 = n4 - 9 - 2 * height;
        final Dimension preferredSize = super.numberInput.preferredSize();
        if (preferredSize.width + 3 > n3 / 2) {
            preferredSize.width = n3 / 2 - 3;
        }
        if (preferredSize.height + 3 > n5 / 2) {
            preferredSize.height = n5 / 2 - 3;
        }
        super.numberInput.reshape(n3 - preferredSize.width - 3, 3, preferredSize.width, preferredSize.height);
        this.canvasPanel.reshape(3, preferredSize.height + 9 + height, n3 - 6, n5 - preferredSize.height - 6);
        this.messageBar.reshape(3, preferredSize.height + 6, n3 - 6, height);
        final int n6 = preferredSize.height + 6;
        final int n7 = n3 - preferredSize.width - 6;
        int height2 = this.functionBar.preferredSize().height;
        int height3 = this.buttonBar.preferredSize().height;
        if (height2 + height3 + 6 > n6) {
            final double n8 = (n6 - 6) / (height2 + height3);
            height2 *= (int)n8;
            height3 *= (int)n8;
        }
        int n9 = (n6 - (height2 + height3) + 1) / 3;
        if (n9 < 0) {
            n9 = 0;
        }
        this.functionBar.reshape(3, n9, n7 - 6, height2);
        this.buttonBar.reshape(3, n9 * 2 + height2, n7 - 6, height3);
    }
    
    synchronized void installExample(final Object[] array) {
        final double[] array2 = (double[])array[2];
        final String s = (String)array[3];
        try {
            this.setFunction(this.parser.parse(s));
            this.invalidateCanvasses();
            for (int i = 0; i < 4; ++i) {
                this.parameterValues[i] = array2[i];
            }
            this.functionDefinition = s;
            this.expCoords.setLimits(array2);
            this.derivCoords.setLimits(array2);
            this.deriv2Coords.setLimits(array2);
            this.functionInput.setText(s);
            this.functionInput.requestFocus();
            super.numberInput.setValues(array2);
        }
        catch (ParseError parseError) {}
    }
    
    private void setFunction(final Expression exp) {
        this.exp = exp;
        this.deriv = exp.derivative(this.xVar);
        this.deriv2 = this.deriv.derivative(this.xVar);
        this.expCanvas.setDerivData(this.exp, this.xVar, this.deriv);
        this.derivCanvas.setDerivData(this.deriv, this.xVar, this.deriv2);
        this.expGraph.setExpression(this.exp);
        this.derivGraph.setExpression(this.deriv);
        this.deriv2Graph.setExpression(this.deriv2);
    }
    
    private void invalidateCanvasses() {
        this.expCanvas.invalidateCanvas();
        this.derivCanvas.invalidateCanvas();
        this.deriv2Canvas.invalidateCanvas();
    }
    
    private boolean checkData() {
        final double[] values = super.numberInput.getValues(this.expCanvas);
        if (values == null) {
            return false;
        }
        final String trim = this.functionInput.getText().trim();
        if (!trim.equals(this.functionDefinition)) {
            Expression parse;
            try {
                parse = this.parser.parse(trim);
            }
            catch (ParseError parseError) {
                this.expCanvas.setErrorMessage("The definition of your function contains an error: " + parseError.getMessage());
                this.functionInput.select(parseError.errorPosition, parseError.errorPosition);
                this.functionInput.requestFocus();
                return false;
            }
            this.setFunction(parse);
        }
        this.parameterValues = values;
        this.functionDefinition = trim;
        this.expCoords.setLimits(values);
        this.derivCoords.setLimits(values);
        this.deriv2Coords.setLimits(values);
        return true;
    }
    
    public synchronized boolean action(final Event event, final Object o) {
        if (event.target instanceof TextField) {
            if (this.checkData()) {
                this.invalidateCanvasses();
            }
            return true;
        }
        if (event.target instanceof Button) {
            if ("Compute".equals(o)) {
                if (this.checkData()) {
                    this.invalidateCanvasses();
                }
            }
            else {
                double[] array = null;
                if ("Zoom In".equals(o)) {
                    array = this.expCoords.zoomIn();
                }
                else if ("Zoom Out".equals(o)) {
                    array = this.expCoords.zoomOut();
                }
                else if ("Equalize".equals(o)) {
                    array = this.expCoords.equalizeAxes();
                }
                if (array != null) {
                    super.numberInput.setValues(array);
                    this.expCoords.setLimits(array);
                    this.derivCoords.setLimits(array);
                    this.deriv2Coords.setLimits(array);
                    this.invalidateCanvasses();
                }
            }
            return true;
        }
        return super.action(event, o);
    }
    
    private void doZoomIn(final HiliteDisplayCanvas hiliteDisplayCanvas, final int n, final int n2) {
        final double[] zoomInOnPixel = this.expCoords.zoomInOnPixel(n, n2);
        if (zoomInOnPixel != null) {
            super.numberInput.setValues(zoomInOnPixel);
            this.expCoords.setLimits(zoomInOnPixel);
            this.derivCoords.setLimits(zoomInOnPixel);
            this.deriv2Coords.setLimits(zoomInOnPixel);
            this.invalidateCanvasses();
        }
    }
    
    private void doZoomOut(final HiliteDisplayCanvas hiliteDisplayCanvas, final int n, final int n2) {
        final double[] zoomOutFromPixel = this.expCoords.zoomOutFromPixel(n, n2);
        if (zoomOutFromPixel != null) {
            super.numberInput.setValues(zoomOutFromPixel);
            this.expCoords.setLimits(zoomOutFromPixel);
            this.derivCoords.setLimits(zoomOutFromPixel);
            this.deriv2Coords.setLimits(zoomOutFromPixel);
            this.invalidateCanvasses();
        }
    }
    
    private void doBeginDrag(final HiliteDisplayCanvas draggingOn, final int n) {
        this.draggingOn = draggingOn;
        this.doContinueDrag(n);
    }
    
    private void doContinueDrag(final int n) {
        final double pixelToX = this.expCoords.pixelToX(n);
        this.xVar.setValue(pixelToX);
        final String realToString = Utilities.realToString(pixelToX);
        final String realToString2 = Utilities.realToString(this.exp.value());
        final String realToString3 = Utilities.realToString(this.deriv.value());
        final String realToString4 = Utilities.realToString(this.deriv2.value());
        this.xLabel.setText("  x = " + realToString);
        this.expLabel.setText("y = " + realToString2);
        this.derivLabel.setText("y' = " + realToString3);
        this.deriv2Label.setText("y'' = " + realToString4);
        if (this.draggingOn == this.expCanvas) {
            this.expCanvas.tangentAt(pixelToX);
            this.derivCanvas.crossHairAt(pixelToX);
        }
        else if (this.draggingOn == this.derivCanvas) {
            this.derivCanvas.tangentAt(pixelToX);
            this.deriv2Canvas.crossHairAt(pixelToX);
        }
        else if (this.draggingOn == this.deriv2Canvas) {
            this.expCanvas.tangentAt(pixelToX);
            this.derivCanvas.tangentAt(pixelToX);
            this.deriv2Canvas.crossHairAt(pixelToX);
        }
    }
    
    private void doFinishDrag(final int n) {
        final double n2 = Double.NaN;
        this.xLabel.setText(" ");
        this.expLabel.setText("The Function");
        this.derivLabel.setText("First Derivative");
        this.deriv2Label.setText("Second Derivative");
        if (this.draggingOn == this.expCanvas) {
            this.expCanvas.tangentAt(n2);
            this.derivCanvas.crossHairAt(n2);
        }
        else if (this.draggingOn == this.derivCanvas) {
            this.derivCanvas.tangentAt(n2);
            this.deriv2Canvas.crossHairAt(n2);
        }
        else if (this.draggingOn == this.deriv2Canvas) {
            this.expCanvas.tangentAt(n2);
            this.derivCanvas.tangentAt(n2);
            this.deriv2Canvas.crossHairAt(n2);
        }
        this.draggingOn = null;
    }
    
    public synchronized boolean mouseDown(final Event event, int n, int n2) {
        this.draggingOn = null;
        if (!(event.target instanceof HiliteDisplayCanvas)) {
            return true;
        }
        final HiliteDisplayCanvas hiliteDisplayCanvas = (HiliteDisplayCanvas)event.target;
        if (hiliteDisplayCanvas != this.expCanvas) {
            this.expCanvas.clearErrorMessage();
        }
        n = n - hiliteDisplayCanvas.location().x - this.canvasPanel.location().x;
        n2 = n2 - hiliteDisplayCanvas.location().y - this.canvasPanel.location().y;
        if ((event.modifiers & 0xF) == 0x0) {
            this.doBeginDrag(hiliteDisplayCanvas, n);
        }
        else if (event.metaDown()) {
            this.doZoomIn(hiliteDisplayCanvas, n, n2);
        }
        else if ((event.modifiers & 0x8) != 0x0) {
            this.doZoomOut(hiliteDisplayCanvas, n, n2);
        }
        return true;
    }
    
    public synchronized boolean mouseDrag(final Event event, int n, final int n2) {
        if (this.draggingOn == event.target) {
            n = n - ((Component)event.target).location().x - this.canvasPanel.location().x;
            this.doContinueDrag(n);
        }
        return true;
    }
    
    public synchronized boolean mouseUp(final Event event, int n, final int n2) {
        if (this.draggingOn == event.target) {
            n = n - ((Component)event.target).location().x - this.canvasPanel.location().x;
            this.doFinishDrag(n);
        }
        return true;
    }
}
