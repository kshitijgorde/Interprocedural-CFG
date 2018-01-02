import java.awt.event.WindowListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.ItemEvent;
import java.awt.event.ActionEvent;
import edu.hws.jcm.functions.TableFunction;
import edu.hws.jcm.functions.ExpressionFunction;
import edu.hws.jcm.data.ParserContext;
import edu.hws.jcm.data.ParseError;
import java.util.StringTokenizer;
import edu.hws.jcm.data.MathObject;
import edu.hws.jcm.functions.SummationParser;
import javax.accessibility.Accessible;
import java.awt.Font;
import java.awt.Label;
import edu.hws.jcm.draw.DrawBorder;
import edu.hws.jcm.draw.Axes;
import edu.hws.jcm.awt.ErrorReporter;
import edu.hws.jcm.draw.Grid;
import edu.hws.jcm.draw.Drawable;
import edu.hws.jcm.draw.Panner;
import java.awt.TextArea;
import java.awt.Component;
import java.awt.LayoutManager;
import java.awt.BorderLayout;
import java.awt.Color;
import java.util.Vector;
import java.awt.Frame;
import java.awt.Choice;
import java.util.Hashtable;
import java.awt.Button;
import edu.hws.jcm.data.Variable;
import edu.hws.jcm.awt.ExpressionInput;
import edu.hws.jcm.draw.LimitControlPanel;
import edu.hws.jcm.draw.DisplayCanvas;
import edu.hws.jcm.awt.Controller;
import edu.hws.jcm.awt.JCMPanel;
import edu.hws.jcm.data.Parser;
import java.awt.event.ItemListener;
import java.awt.event.ActionListener;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class GenericGraphApplet extends Applet implements ActionListener, ItemListener
{
    protected Parser parser;
    protected JCMPanel mainPanel;
    protected Controller mainController;
    protected DisplayCanvas canvas;
    protected LimitControlPanel limitsPanel;
    protected ExpressionInput functionInput;
    protected Variable xVar;
    protected JCMPanel inputPanel;
    protected JCMPanel exampleMenuPanel;
    protected Button computeButton;
    protected String frameTitle;
    protected int[] defaultFrameSize;
    protected Hashtable parameterDefaults;
    private Choice exampleMenu;
    private Button loadExampleButton;
    private Button launchButton;
    private String launchButtonName;
    private Frame frame;
    private Vector exampleStrings;
    private String[] colorNames;
    private Color[] colors;
    
    public GenericGraphApplet() {
        this.defaultFrameSize = new int[] { 550, 400 };
        this.colorNames = new String[] { "black", "red", "blue", "green", "yellow", "cyan", "magenta", "gray", "darkgray", "lightgray", "pink", "orange", "white" };
        this.colors = new Color[] { Color.black, Color.red, Color.blue, Color.green, Color.yellow, Color.cyan, Color.magenta, Color.gray, Color.darkGray, Color.lightGray, Color.pink, Color.orange, Color.white };
    }
    
    public void init() {
        this.setUpParameterDefaults();
        this.frameTitle = this.getParameter("FrameTitle");
        if (this.frameTitle == null) {
            this.frameTitle = this.getClass().getName();
            final int lastIndex = this.frameTitle.lastIndexOf(46);
            if (lastIndex > -1) {
                this.frameTitle = this.frameTitle.substring(lastIndex + 1);
            }
        }
        this.setLayout(new BorderLayout());
        final int height = this.getSize().height;
        this.launchButtonName = this.getParameter("LaunchButtonName");
        if ((height > 0 && height < 100) || this.launchButtonName != null) {
            if (this.launchButtonName == null) {
                this.launchButtonName = "Launch " + this.frameTitle;
            }
            this.add(this.launchButton = new Button(this.launchButtonName), "Center");
            this.launchButton.addActionListener(this);
        }
        else {
            this.mainPanel = new JCMPanel();
            try {
                this.setUpMainPanel();
                this.add(this.mainPanel, "Center");
            }
            catch (Exception ex) {
                System.out.println("Error while opening applet:");
                ex.printStackTrace();
                final TextArea textArea = new TextArea("An error occurred while setting up the applet:\n\n");
                textArea.append(ex.toString());
                this.add(textArea, "Center");
            }
        }
    }
    
    protected void setUpMainPanel() {
        this.parser = new Parser(null, 0);
        this.setUpParser();
        this.setUpExampleMenu();
        final double[] numericParam = this.getNumericParam("Insets");
        if (numericParam == null || numericParam.length == 0 || numericParam[0] < 0.0 || numericParam[0] > 50.0) {
            this.mainPanel.setInsetGap(3);
        }
        else {
            this.mainPanel.setInsetGap((int)Math.round(numericParam[0]));
        }
        this.mainPanel.setBackground(this.getColorParam("BackgroundColor", Color.gray));
        this.mainPanel.setForeground(this.getColorParam("ForegroundColor", Color.black));
        this.canvas = new DisplayCanvas();
        final double[] numericParam2 = this.getNumericParam("Limits");
        if (numericParam2 != null && numericParam2.length >= 4) {
            this.canvas.getCoordinateRect().setLimits(numericParam2);
        }
        if ("yes".equalsIgnoreCase(this.getParameter("UseLimitsPanel", "yes"))) {
            String s = this.getParameter("XName");
            if (s == null) {
                s = this.getParameter("Variable", "x");
            }
            final String parameter = this.getParameter("YName", "y");
            this.limitsPanel = new LimitControlPanel(s + "min", s + "max", parameter + "min", parameter + "max", 0, false);
        }
        this.mainController = this.mainPanel.getController();
        this.setUpBottomPanel();
        this.setUpTopPanel();
        this.setUpCanvas();
        this.addCanvasBorder();
        if (this.limitsPanel != null) {
            this.setUpLimitsPanel();
        }
        final String s2 = (this.loadExampleButton == null) ? "yes" : "no";
        if (this.exampleStrings != null && this.exampleStrings.size() > 0 && !"no".equalsIgnoreCase(this.getParameter("LoadFirstExample", s2))) {
            this.doLoadExample(this.exampleStrings.elementAt(0));
        }
    }
    
    protected void setUpCanvas() {
        final Color colorParam = this.getColorParam("CanvasColor");
        if (colorParam != null) {
            this.canvas.setBackground(colorParam);
        }
        if (!"no".equalsIgnoreCase(this.getParameter("UsePanner", "no"))) {
            this.canvas.add(new Panner());
        }
        if (!"no".equalsIgnoreCase(this.getParameter("UseGrid", "no"))) {
            final Grid grid = new Grid();
            final Color colorParam2 = this.getColorParam("GridColor");
            if (colorParam2 != null) {
                grid.setColor(colorParam2);
            }
            this.canvas.add(grid);
        }
        this.canvas.add(this.makeAxes());
        if (!"no".equalsIgnoreCase(this.getParameter("UseMouseZoom", "no"))) {
            this.canvas.setHandleMouseZooms(true);
        }
        if ("yes".equalsIgnoreCase(this.getParameter("UseOffscreenCanvas", "yes"))) {
            this.canvas.setUseOffscreenCanvas(true);
        }
        this.mainController.setErrorReporter(this.canvas);
        this.mainPanel.add(this.canvas, "Center");
    }
    
    protected Axes makeAxes() {
        final Axes axes = new Axes();
        final Color colorParam = this.getColorParam("AxesColor");
        if (colorParam != null) {
            axes.setAxesColor(colorParam);
        }
        final Color colorParam2 = this.getColorParam("AxesLightColor");
        if (colorParam2 != null) {
            axes.setLightAxesColor(colorParam2);
        }
        final String parameter = this.getParameter("XLabel");
        if (parameter != null) {
            axes.setXLabel(parameter);
        }
        axes.setYLabel(this.getParameter("YLabel"));
        final Color colorParam3 = this.getColorParam("LabelColor");
        if (colorParam3 != null) {
            axes.setLabelColor(colorParam3);
        }
        return axes;
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
            this.canvas.add(new DrawBorder(this.getColorParam("BorderColor", Color.black), n));
        }
    }
    
    protected void setUpBottomPanel() {
        if ("yes".equalsIgnoreCase(this.getParameter("UseFunctionInput", "yes"))) {
            String s = this.getParameter("Function");
            final String name = this.xVar.getName();
            if (s == null) {
                s = "abs(" + name + " ) ^ " + name;
            }
            this.functionInput = new ExpressionInput(s, this.parser);
            (this.inputPanel = new JCMPanel()).setBackground(this.getColorParam("PanelBackground", Color.lightGray));
            this.inputPanel.add(this.functionInput, "Center");
            if ("yes".equalsIgnoreCase(this.getParameter("UseComputeButton", "yes"))) {
                this.computeButton = new Button(this.getParameter("ComputeButtonName", "New Function"));
                this.inputPanel.add(this.computeButton, "East");
                this.computeButton.addActionListener(this);
            }
            String s2 = this.getParameter("FunctionLabel");
            if (s2 == null) {
                s2 = " f(" + name + ") = ";
            }
            if (!"none".equalsIgnoreCase(s2)) {
                this.inputPanel.add(new Label(s2), "West");
            }
            this.mainPanel.add(this.inputPanel, "South");
            this.functionInput.setOnUserAction(this.mainPanel.getController());
        }
    }
    
    protected void setUpLimitsPanel() {
        this.limitsPanel.addCoords(this.canvas);
        if (!"no".equalsIgnoreCase(this.getParameter("TwoLimitsColumns", "no"))) {
            this.limitsPanel.setUseTwoColumnsIfPossible(true);
        }
        int n = 0;
        if ("yes".equalsIgnoreCase(this.getParameter("UseSetLimitsButton", "yes"))) {
            n |= 0x1;
        }
        if (!"no".equalsIgnoreCase(this.getParameter("UseZoomButtons", "no"))) {
            n |= 0xC;
        }
        if (!"no".equalsIgnoreCase(this.getParameter("UseEqualizeButton", "no"))) {
            n |= 0x2;
        }
        if (!"no".equalsIgnoreCase(this.getParameter("UseRestoreButton", "no"))) {
            n |= 0x20;
        }
        if (n != 0) {
            this.limitsPanel.addButtons(n);
        }
        this.limitsPanel.setBackground(this.getColorParam("PanelBackground", Color.lightGray));
        if (!"yes".equalsIgnoreCase(this.getParameter("LimitsOnLeft", "no"))) {
            this.mainPanel.add(this.limitsPanel, "East");
        }
        else {
            this.mainPanel.add(this.limitsPanel, "West");
        }
        this.limitsPanel.setErrorReporter(this.canvas);
    }
    
    protected void setUpTopPanel() {
        if (this.exampleMenuPanel != null) {
            this.mainPanel.add(this.exampleMenuPanel, "North");
        }
        else {
            final String parameter = this.getParameter("PanelTitle");
            if (parameter != null) {
                final Label label = new Label(parameter, 1);
                label.setForeground(this.getColorParam("TitleForeground", new Color(200, 0, 0)));
                label.setBackground(this.getColorParam("TitleBackground", Color.lightGray));
                label.setFont(new Font("Serif", 0, 14));
                this.mainPanel.add(label, "North");
            }
        }
    }
    
    protected void setUpExampleMenu() {
        final Vector exampleStrings = new Vector<String>();
        final Vector vector = new Vector<String>();
        int n = 0;
        String s = this.getParameter("Example");
        if (s == null) {
            ++n;
            s = this.getParameter("Example" + n);
        }
        while (s != null) {
            final int index = s.indexOf(59);
            if (index < 0) {
                exampleStrings.addElement(s);
                vector.addElement(s);
            }
            else {
                exampleStrings.addElement(s.substring(index + 1));
                vector.addElement(s.substring(0, index));
            }
            ++n;
            s = this.getParameter("Example" + n);
        }
        if (exampleStrings.size() == 0) {
            return;
        }
        (this.exampleStrings = exampleStrings).trimToSize();
        this.exampleMenuPanel = new JCMPanel();
        if ("yes".equalsIgnoreCase(this.getParameter("UseLoadButton", "yes"))) {
            (this.loadExampleButton = new Button("Load Example: ")).setBackground(Color.lightGray);
            this.loadExampleButton.addActionListener(this);
        }
        Accessible exampleMenu;
        if (vector.size() == 1) {
            exampleMenu = new Label(vector.elementAt(0), 1);
        }
        else {
            this.exampleMenu = new Choice();
            exampleMenu = this.exampleMenu;
            for (int i = 0; i < vector.size(); ++i) {
                this.exampleMenu.add(vector.elementAt(i));
            }
            if (this.loadExampleButton == null) {
                this.exampleMenu.addItemListener(this);
            }
        }
        ((Component)exampleMenu).setBackground(Color.white);
        this.exampleMenuPanel.add((Component)exampleMenu, "Center");
        if (this.loadExampleButton != null) {
            this.exampleMenuPanel.add(this.loadExampleButton, "West");
        }
    }
    
    protected void setUpParser() {
        if ("yes".equalsIgnoreCase(this.getParameter("StandardFunctions", "yes"))) {
            this.parser.addOptions(1024);
        }
        if ("yes".equalsIgnoreCase(this.getParameter("Booleans", "yes"))) {
            this.parser.addOptions(32);
        }
        if (!"no".equalsIgnoreCase(this.getParameter("OptionalStars", "no"))) {
            this.parser.addOptions(2);
        }
        if (!"no".equalsIgnoreCase(this.getParameter("OptionalParens", "no"))) {
            this.parser.addOptions(512);
        }
        if (!"no".equalsIgnoreCase(this.getParameter("Factorials", "no"))) {
            this.parser.addOptions(64);
        }
        if ("yes".equalsIgnoreCase(this.getParameter("Summations", "yes"))) {
            this.parser.add(new SummationParser());
        }
        final String parameter = this.getParameter("Define");
        if (parameter != null) {
            this.define(parameter);
        }
        int n = 1;
        while (true) {
            final String parameter2 = this.getParameter("Define" + n);
            if (parameter2 == null) {
                break;
            }
            this.define(parameter2);
            ++n;
        }
        this.xVar = new Variable(this.getParameter("Variable", "x"));
        this.parser.add(this.xVar);
    }
    
    protected void doLoadExample(final String s) {
    }
    
    protected void setUpParameterDefaults() {
    }
    
    public String getParameter(final String s) {
        String parameter = super.getParameter(s);
        if (parameter == null && this.parameterDefaults != null) {
            parameter = this.parameterDefaults.get(s);
        }
        return parameter;
    }
    
    protected String getParameter(final String s, final String s2) {
        final String parameter = this.getParameter(s);
        return (parameter == null) ? s2 : parameter;
    }
    
    protected double[] getNumericParam(final String s) {
        return this.getNumericParam(s, null);
    }
    
    protected double[] getNumericParam(final String s, final double[] array) {
        final String parameter = this.getParameter(s);
        if (parameter == null) {
            return array;
        }
        final StringTokenizer stringTokenizer = new StringTokenizer(parameter, " \t,;");
        final int countTokens = stringTokenizer.countTokens();
        if (countTokens == 0) {
            return array;
        }
        final double[] array2 = new double[countTokens];
        for (int i = 0; i < countTokens; ++i) {
            try {
                array2[i] = new Double(stringTokenizer.nextToken());
            }
            catch (NumberFormatException ex) {
                return array;
            }
        }
        return array2;
    }
    
    protected Color getColorParam(final String s) {
        return this.getColorParam(s, null);
    }
    
    protected Color getColorParam(final String s, final Color color) {
        final String parameter = this.getParameter(s);
        if (parameter == null || parameter.trim().length() == 0) {
            return color;
        }
        final String trim = parameter.trim();
        if (Character.isLetter(trim.charAt(0))) {
            for (int i = 0; i < this.colorNames.length; ++i) {
                if (trim.equalsIgnoreCase(this.colorNames[i])) {
                    return this.colors[i];
                }
            }
            return color;
        }
        final double[] numericParam = this.getNumericParam(s, null);
        if (numericParam == null || numericParam.length < 3) {
            return color;
        }
        if (numericParam[0] < 0.0 || numericParam[0] > 255.0 || numericParam[1] < 0.0 || numericParam[1] > 255.0 || numericParam[2] < 0.0 || numericParam[2] > 255.0) {
            return color;
        }
        return new Color((int)Math.round(numericParam[0]), (int)Math.round(numericParam[1]), (int)Math.round(numericParam[2]));
    }
    
    public void stop() {
        if (this.canvas != null && this.frame == null) {
            this.canvas.releaseResources();
        }
    }
    
    public synchronized void destroy() {
        if (this.frame != null) {
            this.frame.dispose();
        }
    }
    
    private void define(final String s) {
        try {
            final int index = s.indexOf("=");
            if (index < 0) {
                throw new ParseError("Missing \"=\"", (ParserContext)null);
            }
            final String trim = s.substring(index + 1).trim();
            final String substring = s.substring(0, index);
            if (trim.toLowerCase().startsWith("table")) {
                String trim2 = substring;
                final int index2 = trim2.indexOf("(");
                if (index2 > 0) {
                    trim2 = trim2.substring(0, index2).trim();
                }
                final TableFunction tableFuncDef = this.parseTableFuncDef(trim);
                tableFuncDef.setName(trim2);
                this.parser.add(tableFuncDef);
            }
            else {
                final int index3 = substring.indexOf("(");
                if (index3 < 0) {
                    throw new ParseError("Missing \"(\"", (ParserContext)null);
                }
                final String trim3 = substring.substring(0, index3).trim();
                if (trim3.length() == 0) {
                    throw new ParseError("Missing function name", (ParserContext)null);
                }
                final String substring2 = substring.substring(index3 + 1);
                final int index4 = substring2.indexOf(")");
                if (index4 < 0) {
                    throw new ParseError("Missing \")\"", (ParserContext)null);
                }
                final String trim4 = substring2.substring(0, index4).trim();
                if (trim4.length() == 0) {
                    throw new ParseError("Missing parameter names", (ParserContext)null);
                }
                final StringTokenizer stringTokenizer = new StringTokenizer(trim4, ",");
                final int countTokens = stringTokenizer.countTokens();
                final String[] array = new String[countTokens];
                for (int i = 0; i < countTokens; ++i) {
                    array[i] = stringTokenizer.nextToken();
                }
                new ExpressionFunction(trim3, array, trim, this.parser);
            }
        }
        catch (ParseError parseError) {
            throw new IllegalArgumentException("Error parsing function \"" + s + "\":" + parseError.getMessage());
        }
    }
    
    protected TableFunction parseTableFuncDef(final String s) {
        try {
            final TableFunction tableFunction = new TableFunction();
            final StringTokenizer stringTokenizer = new StringTokenizer(s, " \t,");
            String s2 = null;
            if (stringTokenizer.hasMoreTokens()) {
                s2 = stringTokenizer.nextToken();
                if (s2.equalsIgnoreCase("table") && stringTokenizer.hasMoreTokens()) {
                    s2 = stringTokenizer.nextToken();
                }
            }
            if ("step".equalsIgnoreCase(s2)) {
                tableFunction.setStyle(2);
                if (stringTokenizer.hasMoreTokens()) {
                    s2 = stringTokenizer.nextToken();
                }
            }
            else if ("linear".equalsIgnoreCase(s2)) {
                tableFunction.setStyle(1);
                if (stringTokenizer.hasMoreTokens()) {
                    s2 = stringTokenizer.nextToken();
                }
            }
            else if ("smooth".equalsIgnoreCase(s2) && stringTokenizer.hasMoreTokens() && stringTokenizer.hasMoreTokens()) {
                s2 = stringTokenizer.nextToken();
            }
            final boolean equalsIgnoreCase = "intervals".equalsIgnoreCase(s2);
            if (equalsIgnoreCase && stringTokenizer.hasMoreTokens()) {
                s2 = stringTokenizer.nextToken();
            }
            final double[] array = new double[stringTokenizer.countTokens() + 1];
            try {
                array[0] = new Double(s2);
            }
            catch (NumberFormatException ex2) {
                throw new ParseError("Unexpected token \"" + s2 + "\".", (ParserContext)null);
            }
            try {
                for (int i = 1; i < array.length; ++i) {
                    array[i] = new Double(stringTokenizer.nextToken());
                }
            }
            catch (NumberFormatException ex3) {
                throw new ParseError("Illegal number.", (ParserContext)null);
            }
            if (equalsIgnoreCase) {
                int n = (array.length == 0) ? 6 : ((int)Math.round(array[0]));
                if (n < 1 || n > 500) {
                    n = 6;
                }
                final double n2 = (array.length < 2) ? -5.0 : array[1];
                final double n3 = (array.length < 3) ? (n2 + 10.0) : array[2];
                if (n3 <= n2) {
                    throw new ParseError("xmax in table must be greater than xmin", (ParserContext)null);
                }
                tableFunction.addIntervals(n, n2, n3);
                for (int n4 = 3; n4 < array.length && n4 - 3 <= n; ++n4) {
                    if (n4 - 3 < n) {
                        tableFunction.setY(n4 - 3, array[n4]);
                    }
                }
            }
            else {
                if (array.length < 4) {
                    throw new ParseError("At least two points must be provided for table function.", (ParserContext)null);
                }
                if (array.length % 2 == 1) {
                    throw new ParseError("Can't define an table function with an odd number of values.", (ParserContext)null);
                }
                for (int j = 0; j < array.length / 2; ++j) {
                    tableFunction.addPoint(array[2 * j], array[2 * j + 1]);
                }
            }
            return tableFunction;
        }
        catch (Exception ex) {
            throw new ParseError("Error while parsing table function: " + ex.getMessage(), (ParserContext)null);
        }
    }
    
    public void actionPerformed(final ActionEvent actionEvent) {
        final Object source = actionEvent.getSource();
        if (source == this.computeButton && this.computeButton != null) {
            this.mainController.compute();
        }
        else if (source == this.launchButton && this.launchButton != null) {
            this.doLaunchButton();
        }
        else if (source == this.loadExampleButton && this.exampleStrings != null) {
            if (this.exampleStrings.size() == 1) {
                this.doLoadExample(this.exampleStrings.elementAt(0));
            }
            else {
                this.doLoadExample(this.exampleStrings.elementAt(this.exampleMenu.getSelectedIndex()));
            }
        }
    }
    
    public void itemStateChanged(final ItemEvent itemEvent) {
        if (itemEvent.getSource() == this.exampleMenu) {
            this.doLoadExample(this.exampleStrings.elementAt(this.exampleMenu.getSelectedIndex()));
        }
    }
    
    private synchronized void doLaunchButton() {
        this.launchButton.setEnabled(false);
        if (this.frame == null) {
            this.frame = new Frame(this.frameTitle);
            this.mainPanel = new JCMPanel();
            try {
                this.setUpMainPanel();
                this.frame.add(this.mainPanel, "Center");
            }
            catch (Throwable t) {
                System.out.println("Error while opening window:");
                t.printStackTrace();
                final TextArea textArea = new TextArea("An error occurred while setting up this window:\n\n");
                textArea.append(t.toString());
                this.frame.add(textArea, "Center");
            }
            this.frame.addWindowListener(new WindowAdapter() {
                public void windowClosing(final WindowEvent windowEvent) {
                    GenericGraphApplet.this.frame.dispose();
                }
                
                public void windowClosed(final WindowEvent windowEvent) {
                    GenericGraphApplet.this.frameClosed();
                }
            });
            final double[] numericParam = this.getNumericParam("FrameSize");
            if (numericParam == null || numericParam.length < 2 || numericParam[0] < 100.0 || numericParam[0] > 800.0 || numericParam[1] < 100.0 || numericParam[1] > 600.0) {
                this.frame.setSize(this.defaultFrameSize[0], this.defaultFrameSize[1]);
            }
            else {
                this.frame.setSize((int)Math.round(numericParam[0]), (int)Math.round(numericParam[1]));
            }
            this.frame.setLocation(50, 50);
            this.frame.show();
            this.launchButton.setLabel("Close Window");
            this.launchButton.setEnabled(true);
        }
        else {
            this.frame.dispose();
        }
    }
    
    private synchronized void frameClosed() {
        this.frame = null;
        this.launchButton.setLabel(this.launchButtonName);
        this.launchButton.setEnabled(true);
        this.mainPanel = null;
        this.canvas = null;
        this.limitsPanel = null;
        this.inputPanel = null;
        this.exampleMenuPanel = null;
        this.loadExampleButton = null;
        this.computeButton = null;
        this.parser = null;
    }
}
