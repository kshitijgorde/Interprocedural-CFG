// 
// Decompiled by Procyon v0.5.30
// 

package xfunctions;

import xfunctions.functions.Utilities;
import java.awt.Point;
import java.awt.Event;
import java.awt.GridLayout;
import java.awt.Component;
import java.awt.LayoutManager;
import java.awt.BorderLayout;
import java.awt.Panel;
import java.awt.Color;
import xfunctions.graphs.NumberInputPanel;
import xfunctions.graphs.CoordinateRect;
import xfunctions.graphs.DisplayCanvas;
import xfunctions.functions.ParseError;
import xfunctions.graphs.Drawable;
import xfunctions.graphs.Axes;
import java.awt.Label;
import java.awt.Button;
import java.awt.TextField;
import java.awt.Checkbox;
import xfunctions.functions.Expression;
import xfunctions.functions.Variable;
import xfunctions.graphs.Graph1D;
import xfunctions.graphs.CoordinateRectWithFrames;
import xfunctions.functions.Parser;

public class AnimatorPanel extends GenericPanel implements Runnable
{
    Parser parser;
    CoordinateRectWithFrames coords;
    Graph1D graph;
    Variable kVar;
    Variable xVar;
    Expression exp;
    Checkbox backAndForth;
    TextField functionInput;
    private Button stopButton;
    private Button goButton;
    private Button nextButton;
    private Thread runner;
    private static final int START = 0;
    private static final int FRAME = 1;
    private static final int RUN = 2;
    private static final int STOP = 3;
    private static final int PAUSE = 4;
    private static final int IDLE = 5;
    private static final int WAITSTART = 6;
    private int runnerCommand;
    private double[] parameterValues;
    private String functionDefinition;
    private Label message;
    
    public AnimatorPanel() {
        this((Parser)null);
    }
    
    public AnimatorPanel(final Parser parser) {
        this.runnerCommand = 5;
        this.functionDefinition = "1 / (x^2 - k)";
        if (parser == null) {
            this.parser = new Parser();
        }
        else {
            this.parser = new Parser(parser);
        }
        this.kVar = this.parser.defineVariable("k");
        this.xVar = this.parser.defineVariable("x");
        (this.coords = new CoordinateRectWithFrames()).setParamData(this.kVar, -1.0, 1.0);
        this.coords.add(new Axes());
        Expression parse;
        try {
            parse = this.parser.parse(this.functionDefinition);
        }
        catch (ParseError parseError) {
            parse = null;
        }
        this.graph = new Graph1D(parse, this.xVar);
        this.coords.add(this.graph);
        (super.canvas = new DisplayCanvas()).setCoords(this.coords);
        final Button[] array2;
        final Button[] array = array2 = new Button[3];
        final int n = 0;
        final Button goButton = new Button("Go");
        array2[n] = goButton;
        this.goButton = goButton;
        final Button[] array3 = array;
        final int n2 = 1;
        final Button stopButton = new Button("Stop");
        array3[n2] = stopButton;
        this.stopButton = stopButton;
        final Button[] array4 = array;
        final int n3 = 2;
        final Button nextButton = new Button("Next");
        array4[n3] = nextButton;
        this.nextButton = nextButton;
        super.buttons = new ButtonPanel(this, array);
        (super.numberInput = new NumberInputPanel()).addRealRange("x");
        super.numberInput.addRealRange("y");
        super.numberInput.addStartToFinishReal("k");
        super.numberInput.addSingleInteger("Intervals", 1, 1000);
        (this.parameterValues = new double[7])[0] = (this.parameterValues[2] = -5.0);
        this.parameterValues[1] = (this.parameterValues[3] = 5.0);
        this.parameterValues[4] = -1.0;
        this.parameterValues[5] = 1.0;
        this.parameterValues[6] = 40.0;
        super.numberInput.setValues(this.parameterValues);
        this.coords.setLimits(this.parameterValues);
        this.backAndForth = new Checkbox("Loop Back and Forth");
        (this.functionInput = new TextField(this.functionDefinition)).setBackground(Color.white);
        this.message = new Label(" Frame 0, k = -1            ");
        final Panel panel = new Panel();
        panel.setLayout(new BorderLayout());
        panel.add("West", new Label("  y  =  "));
        panel.add("Center", this.functionInput);
        final Panel panel2 = new Panel();
        panel2.setLayout(new GridLayout(3, 1, 5, 5));
        panel2.add(new Label("  Enter a function of x and k:", 0));
        panel2.add(panel);
        panel2.add(super.buttons);
        final Panel panel3 = new Panel();
        panel3.setLayout(new BorderLayout(3, 3));
        final Panel panel4 = new Panel();
        panel4.setLayout(new GridLayout(2, 1));
        panel4.add(this.backAndForth);
        panel4.add(this.message);
        panel3.add("Center", super.numberInput);
        panel3.add("South", panel4);
        this.setBackground(Color.lightGray);
        this.setLayout(new BorderLayout(3, 3));
        this.add("Center", super.canvas);
        this.add("East", panel3);
        this.add("South", panel2);
    }
    
    synchronized void installExample(final Object[] array) {
        final double[] array2 = (double[])array[2];
        final String s = (String)array[3];
        try {
            this.doStop();
            this.graph.setExpression(this.parser.parse(s));
            this.functionInput.setText(s);
            super.numberInput.setValues(array2);
            this.coords.setLimits(array2);
            this.functionInput.requestFocus();
            for (int i = 0; i < 7; ++i) {
                this.parameterValues[i] = array2[i];
            }
            this.coords.setParamData(this.kVar, array2[4], array2[5]);
            this.coords.setIntervalCount((int)array2[6]);
            this.functionDefinition = s;
            super.canvas.invalidateCanvas();
            final boolean b = this.runner == null || !this.runner.isAlive();
            this.doGo();
            if (b) {
                this.runnerCommand = 6;
            }
            this.backAndForth.setState((boolean)array[4]);
        }
        catch (ParseError parseError) {}
    }
    
    void aboutToShow() {
        this.functionInput.selectAll();
        this.functionInput.requestFocus();
        if (this.runnerCommand != 6) {
            this.goButton.enable();
            this.stopButton.disable();
            this.nextButton.enable();
        }
    }
    
    void aboutToHide() {
        if (this.runner != null && this.runner.isAlive()) {
            synchronized (this) {
                this.runnerCommand = 3;
                this.notify();
                try {
                    this.wait(1000L);
                }
                catch (InterruptedException ex) {}
            }
            if (this.runner.isAlive()) {
                this.runner.stop();
            }
        }
        super.aboutToHide();
        this.runnerCommand = 5;
        this.runner = null;
        this.graph.reset();
    }
    
    synchronized void doGo() {
        this.goButton.disable();
        this.stopButton.enable();
        this.nextButton.disable();
        if (this.runner == null || !this.runner.isAlive()) {
            this.runner = new Thread(this);
            this.runnerCommand = 0;
            this.runner.start();
        }
        else if (this.runnerCommand == 5) {
            this.runnerCommand = 2;
            this.notify();
        }
        else {
            this.runnerCommand = 0;
            this.notify();
        }
    }
    
    synchronized void doNext() {
        if (this.runner == null || !this.runner.isAlive()) {
            this.runner = new Thread(this);
            this.runnerCommand = 1;
            this.runner.start();
        }
        else {
            this.runnerCommand = 1;
            this.notify();
        }
    }
    
    synchronized void doStop() {
        if (this.runner != null && this.runner.isAlive()) {
            this.runnerCommand = 4;
            this.notify();
        }
    }
    
    boolean checkData() {
        final double[] values = super.numberInput.getValues(super.canvas);
        if (values == null) {
            this.doStop();
            return false;
        }
        final String trim = this.functionInput.getText().trim();
        if (!trim.equals(this.functionDefinition)) {
            Expression parse;
            try {
                parse = this.parser.parse(trim);
            }
            catch (ParseError parseError) {
                super.canvas.setErrorMessage("The definition of your function contains an error: " + parseError.getMessage());
                this.functionInput.select(parseError.errorPosition, parseError.errorPosition);
                this.functionInput.requestFocus();
                this.doStop();
                return false;
            }
            this.graph.setExpression(parse);
        }
        this.coords.setLimits(values);
        if (values[4] != this.parameterValues[4] || values[5] != this.parameterValues[5]) {
            this.coords.setParamData(this.kVar, values[4], values[5]);
        }
        this.coords.setIntervalCount((int)values[6]);
        this.parameterValues = values;
        this.functionDefinition = trim;
        return true;
    }
    
    public synchronized boolean action(final Event event, final Object o) {
        if (event.target instanceof TextField) {
            if (this.checkData()) {
                super.canvas.invalidateCanvas();
            }
            return true;
        }
        return super.action(event, o);
    }
    
    boolean doButtonCommand(final String s) {
        if (s.equals("Go")) {
            if (this.runnerCommand != 2 && this.checkData()) {
                this.doGo();
            }
            return true;
        }
        if (s.equals("Stop")) {
            this.doStop();
            return true;
        }
        if (s.equals("Next")) {
            if (this.runnerCommand != 2 && this.checkData()) {
                this.doNext();
            }
            return true;
        }
        return false;
    }
    
    public synchronized boolean mouseDown(final Event event, final int n, final int n2) {
        if (event.target != super.canvas) {
            return true;
        }
        if (event.metaDown()) {
            final Point location = super.canvas.location();
            final double[] zoomInOnPixel = this.coords.zoomInOnPixel(n - location.x, n2 - location.y);
            super.canvas.invalidateCanvas();
            if (zoomInOnPixel != null) {
                super.numberInput.setValues(zoomInOnPixel);
            }
        }
        else if ((event.modifiers & 0x8) != 0x0) {
            final Point location2 = super.canvas.location();
            final double[] zoomOutFromPixel = this.coords.zoomOutFromPixel(n - location2.x, n2 - location2.y);
            super.canvas.invalidateCanvas();
            if (zoomOutFromPixel != null) {
                super.numberInput.setValues(zoomOutFromPixel);
            }
        }
        return true;
    }
    
    public void run() {
        int n = 1;
        int currentFrame = 0;
        long currentTimeMillis = 0L;
        int n2 = this.runnerCommand;
        while (true) {
            if (n2 != 5) {
                n2 = this.runnerCommand;
                if (n2 == 3) {
                    break;
                }
                if (n2 == 4) {
                    this.goButton.enable();
                    this.stopButton.disable();
                    this.nextButton.enable();
                    final int runnerCommand = 5;
                    this.runnerCommand = runnerCommand;
                    n2 = runnerCommand;
                }
                else {
                    if (n2 == 0 || n2 == 6) {
                        if (n2 == 6) {
                            try {
                                Thread.sleep(2000L);
                            }
                            catch (InterruptedException ex) {}
                        }
                        n = 1;
                        currentFrame = 0;
                        final int runnerCommand2 = 2;
                        this.runnerCommand = runnerCommand2;
                        n2 = runnerCommand2;
                        currentTimeMillis = 0L;
                    }
                    if (n2 == 2 || n2 == 1) {
                        if (n != 0 || currentFrame > this.parameterValues[6]) {
                            if (++currentFrame > this.parameterValues[6]) {
                                if (this.backAndForth.getState()) {
                                    n = 0;
                                    currentFrame = (int)this.parameterValues[6] - 1;
                                }
                                else {
                                    currentFrame = 0;
                                }
                            }
                        }
                        else if (--currentFrame < 0) {
                            currentFrame = 1;
                            n = 1;
                        }
                        this.coords.setCurrentFrame(currentFrame);
                        this.message.setText(" Frame " + currentFrame + ", k = " + Utilities.realToString(this.parameterValues[4] + (this.parameterValues[5] - this.parameterValues[4]) * currentFrame / this.parameterValues[6]));
                        super.canvas.invalidateCanvas();
                    }
                    if (n2 == 1) {
                        n2 = (this.runnerCommand = 5);
                    }
                    else {
                        if (n2 != 2) {
                            continue;
                        }
                        long n3 = currentTimeMillis + 80L - System.currentTimeMillis();
                        if (n3 < 20L) {
                            n3 = 20L;
                        }
                        try {
                            synchronized (this) {
                                this.wait(n3);
                            }
                        }
                        catch (InterruptedException ex2) {}
                        currentTimeMillis = System.currentTimeMillis();
                    }
                }
            }
            else {
                synchronized (this) {
                    try {
                        this.wait();
                    }
                    catch (InterruptedException ex3) {}
                    n2 = this.runnerCommand;
                }
            }
        }
        synchronized (this) {
            this.notify();
        }
    }
}
