// 
// Decompiled by Procyon v0.5.30
// 

package dataGraph;

import edu.davidson.tools.SStepable;
import java.util.Enumeration;
import edu.davidson.tools.SDataConnection;
import java.awt.image.ImageObserver;
import java.awt.MediaTracker;
import java.net.URL;
import java.awt.Image;
import edu.davidson.display.Thing;
import edu.davidson.display.ImageThing;
import edu.davidson.display.CaptionThing;
import edu.davidson.display.TextThing;
import edu.davidson.display.ArrowThing;
import edu.davidson.display.ShellThing;
import edu.davidson.display.MarkerThing;
import edu.davidson.display.CircleThing;
import edu.davidson.display.TangentThing;
import edu.davidson.display.RectangleThing;
import edu.davidson.display.SScalable;
import edu.davidson.display.BoxThing;
import edu.davidson.tools.SUtil;
import java.awt.Component;
import java.awt.event.ComponentListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Color;
import java.awt.LayoutManager;
import java.awt.GridLayout;
import java.awt.FlowLayout;
import java.awt.Panel;
import java.awt.Label;
import edu.davidson.display.SNumber;
import java.awt.TextField;
import java.awt.BorderLayout;
import java.awt.Button;
import edu.davidson.display.SGraph;
import edu.davidson.graphics.EtchedBorder;
import edu.davidson.tools.SApplet;

public class DataGraph extends SApplet
{
    boolean isStandalone;
    boolean firstTime;
    boolean showControls;
    boolean autoX;
    boolean autoY;
    String function;
    double xmin;
    double xmax;
    double ymin;
    double ymax;
    String dataFile;
    EtchedBorder etchedBorder1;
    SGraph graph;
    Button clearSeriesBtn;
    BorderLayout borderLayout1;
    EtchedBorder etchedBorder2;
    EtchedBorder etchedBorder3;
    Button addDatumBtn;
    Button addFuncBtn;
    Button clearFuncBtn;
    TextField funcField;
    SNumber yField;
    SNumber xField;
    BorderLayout borderLayout2;
    Label label1;
    Panel border1;
    FlowLayout flowLayout1;
    BorderLayout borderLayout3;
    Panel border2;
    Panel border3;
    Label label2;
    Label label3;
    BorderLayout borderLayout4;
    FlowLayout flowLayout2;
    Panel panel1;
    Panel panel2;
    GridLayout gridLayout1;
    BorderLayout borderLayout5;
    BorderLayout borderLayout6;
    Button button4;
    
    public String getParameter(final String s, final String s2) {
        return this.isStandalone ? System.getProperty(s, s2) : ((this.getParameter(s) != null) ? this.getParameter(s) : s2);
    }
    
    public DataGraph() {
        this.isStandalone = false;
        this.firstTime = true;
        this.showControls = true;
        this.etchedBorder1 = new EtchedBorder();
        this.graph = new SGraph(this);
        this.clearSeriesBtn = new Button();
        this.borderLayout1 = new BorderLayout();
        this.etchedBorder2 = new EtchedBorder();
        this.etchedBorder3 = new EtchedBorder();
        this.addDatumBtn = new Button();
        this.addFuncBtn = new Button();
        this.clearFuncBtn = new Button();
        this.funcField = new TextField();
        this.yField = new SNumber();
        this.xField = new SNumber();
        this.borderLayout2 = new BorderLayout();
        this.label1 = new Label();
        this.border1 = new Panel();
        this.flowLayout1 = new FlowLayout();
        this.borderLayout3 = new BorderLayout();
        this.border2 = new Panel();
        this.border3 = new Panel();
        this.label2 = new Label();
        this.label3 = new Label();
        this.borderLayout4 = new BorderLayout();
        this.flowLayout2 = new FlowLayout();
        this.panel1 = new Panel();
        this.panel2 = new Panel();
        this.gridLayout1 = new GridLayout();
        this.borderLayout5 = new BorderLayout();
        this.borderLayout6 = new BorderLayout();
        this.button4 = new Button();
    }
    
    public void init() {
        try {
            this.function = this.getParameter("Function", "");
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
        try {
            this.xmin = Double.valueOf(this.getParameter("XMin", "-1"));
        }
        catch (Exception ex2) {
            ex2.printStackTrace();
        }
        try {
            this.xmax = Double.valueOf(this.getParameter("XMax", "1"));
        }
        catch (Exception ex3) {
            ex3.printStackTrace();
        }
        try {
            this.ymin = Double.valueOf(this.getParameter("YMin", "-1"));
        }
        catch (Exception ex4) {
            ex4.printStackTrace();
        }
        try {
            this.ymax = Double.valueOf(this.getParameter("YMax", "1"));
        }
        catch (Exception ex5) {
            ex5.printStackTrace();
        }
        try {
            this.dataFile = this.getParameter("DataFile", "");
        }
        catch (Exception ex6) {
            ex6.printStackTrace();
        }
        try {
            this.autoX = Boolean.valueOf(this.getParameter("AutoScaleX", "true"));
        }
        catch (Exception ex7) {
            ex7.printStackTrace();
        }
        try {
            this.autoY = Boolean.valueOf(this.getParameter("AutoScaleY", "true"));
        }
        catch (Exception ex8) {
            ex8.printStackTrace();
        }
        try {
            this.showControls = Boolean.valueOf(this.getParameter("ShowControls", "true"));
        }
        catch (Exception ex9) {
            ex9.printStackTrace();
        }
        try {
            this.jbInit();
        }
        catch (Exception ex10) {
            ex10.printStackTrace();
        }
        if (!this.showControls) {
            this.etchedBorder1.setVisible(false);
        }
        if (this.function != null && !this.function.equals("")) {
            this.funcField.setText(this.function);
        }
        this.graph.setEnableMouse(true);
        this.graph.setAutoscaleX(this.autoX);
        this.graph.setAutoscaleY(this.autoY);
        this.graph.setMinMaxX(this.xmin, this.xmax);
        this.graph.setMinMaxY(this.ymin, this.ymax);
        if (this.dataFile != null && !this.dataFile.equals("")) {
            this.graph.loadFile(1, this.dataFile);
        }
    }
    
    private void jbInit() throws Exception {
        this.etchedBorder1.setLayout(this.borderLayout2);
        this.etchedBorder1.setBackground(Color.lightGray);
        this.graph.setSampleData(false);
        this.graph.setLabelX("x");
        this.graph.setBorders("0,10,15,0");
        this.graph.setLabelY("y");
        this.clearSeriesBtn.setLabel("Clear");
        this.etchedBorder3.setLayout(this.borderLayout3);
        this.etchedBorder2.setLayout(this.borderLayout4);
        this.addDatumBtn.setLabel("Add");
        this.addDatumBtn.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent actionEvent) {
                DataGraph.this.addDatumBtn_actionPerformed(actionEvent);
            }
        });
        this.addFuncBtn.setLabel("Plot");
        this.addFuncBtn.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent actionEvent) {
                DataGraph.this.addFuncBtn_actionPerformed(actionEvent);
            }
        });
        this.addFuncBtn.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent actionEvent) {
                DataGraph.this.addFuncBtn_actionPerformed(actionEvent);
            }
        });
        this.addFuncBtn.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent actionEvent) {
                DataGraph.this.addFuncBtn_actionPerformed(actionEvent);
            }
        });
        this.clearFuncBtn.setLabel("Clear");
        this.clearFuncBtn.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent actionEvent) {
                DataGraph.this.clearFuncBtn_actionPerformed(actionEvent);
            }
        });
        this.funcField.setText("sin(2*pi*x)");
        this.label1.setAlignment(2);
        this.label1.setText("F(x) =");
        this.flowLayout1.setHgap(0);
        this.flowLayout1.setVgap(0);
        this.border2.setLayout(this.flowLayout2);
        this.border3.setLayout(this.gridLayout1);
        this.label2.setAlignment(2);
        this.label2.setText("x = ");
        this.label3.setAlignment(2);
        this.label3.setText("y = ");
        this.flowLayout2.setHgap(0);
        this.flowLayout2.setVgap(0);
        this.button4.setLabel("Reset");
        this.button4.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent actionEvent) {
                DataGraph.this.button4_actionPerformed(actionEvent);
            }
        });
        this.panel1.setLayout(this.borderLayout6);
        this.panel2.setLayout(this.borderLayout5);
        this.border1.setLayout(this.flowLayout1);
        this.clearSeriesBtn.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent actionEvent) {
                DataGraph.this.clearSeriesBtn_actionPerformed(actionEvent);
            }
        });
        this.setLayout(this.borderLayout1);
        this.addComponentListener(new ComponentAdapter() {});
        this.add(this.etchedBorder1, "South");
        this.etchedBorder1.add(this.etchedBorder3, "South");
        this.etchedBorder3.add(this.border1, "East");
        this.border1.add(this.addFuncBtn, null);
        this.border1.add(this.clearFuncBtn, null);
        this.etchedBorder3.add(this.label1, "West");
        this.etchedBorder3.add(this.funcField, "Center");
        this.etchedBorder1.add(this.etchedBorder2, "North");
        this.etchedBorder2.add(this.border3, "Center");
        this.border3.add(this.panel2, null);
        this.panel2.add(this.label2, "West");
        this.panel2.add(this.xField, "Center");
        this.border3.add(this.panel1, null);
        this.panel1.add(this.label3, "West");
        this.panel1.add(this.yField, "Center");
        this.etchedBorder2.add(this.border2, "East");
        this.border2.add(this.addDatumBtn, null);
        this.border2.add(this.clearSeriesBtn, null);
        this.etchedBorder2.add(this.button4, "West");
        this.add(this.graph, "Center");
    }
    
    public void destroy() {
        if (super.clock.isRunning()) {
            super.clock.stopClock();
        }
        this.graph.destroy();
        super.destroy();
    }
    
    public void start() {
        super.start();
        if (this.firstTime) {
            this.firstTime = false;
            this.graph.setOwner(this);
            if (this.function != null && !this.function.equals("")) {
                this.graph.addFunction(this.function);
            }
        }
    }
    
    public void stop() {
        super.stop();
    }
    
    public String getAppletInfo() {
        return "Applet Information";
    }
    
    public String[][] getParameterInfo() {
        return new String[][] { { "Function", "String", "A function to plot." }, { "XMin", "double", "Minumum X value" }, { "XMax", "double", "Maximum X value" }, { "YMin", "double", "Minimum Y value" }, { "YMax", "double", "Maximum Y value" }, { "AutoScaleX", "boolean", "Autoscale the x axis." }, { "AutoScaleY", "boolean", "Autoscale the y axis." }, { "ShowControls", "boolean", "Show the control buttons at the bottom of the applet." }, { "DataFile", "String", "Data points file" } };
    }
    
    void clearSeriesBtn_actionPerformed(final ActionEvent actionEvent) {
        this.clearSeries(1);
    }
    
    public int getGraphID() {
        return this.graph.hashCode();
    }
    
    public int getSeriesID(final int n) {
        return this.graph.getIDFromSID(n);
    }
    
    public int getRegressionID(final int n, final int n2, final int n3) {
        return this.graph.getRegressionID(n, n2, n3);
    }
    
    public void addData(final int n, final double[] array, final double[] array2) {
        this.graph.addData(n, array, array2);
    }
    
    public void addDatum(final int n, final double n2, final double n3) {
        this.graph.addDatum(n, n2, n3);
    }
    
    public int addCursor(final double n, final double n2) {
        return this.graph.addCursor(this, 10, n, n2);
    }
    
    public int addConnectorLine(final int n, final int n2) {
        return this.graph.addConnectorLine(this, n, n2);
    }
    
    public synchronized int addObject(String s, String removeWhitespace) {
        Thing thing = null;
        double n = 0.0;
        double n2 = 0.0;
        int n3 = 20;
        int n4 = 20;
        int n5 = 10;
        s = s.toLowerCase().trim();
        s = SUtil.removeWhitespace(s);
        final String trim = removeWhitespace.trim();
        removeWhitespace = SUtil.removeWhitespace(removeWhitespace);
        if (s.equals("box")) {
            if (SUtil.parameterExist(removeWhitespace, "x=")) {
                n = SUtil.getParam(removeWhitespace, "x=");
            }
            if (SUtil.parameterExist(removeWhitespace, "y=")) {
                n2 = SUtil.getParam(removeWhitespace, "y=");
            }
            if (SUtil.parameterExist(removeWhitespace, "w=")) {
                n3 = (int)SUtil.getParam(removeWhitespace, "w=");
            }
            if (SUtil.parameterExist(removeWhitespace, "h=")) {
                n4 = (int)SUtil.getParam(removeWhitespace, "h=");
            }
            thing = new BoxThing(this, this.graph, n, n2, n3, n4);
        }
        else if (s.equals("rectangle")) {
            if (SUtil.parameterExist(removeWhitespace, "x=")) {
                n = SUtil.getParam(removeWhitespace, "x=");
            }
            if (SUtil.parameterExist(removeWhitespace, "y=")) {
                n2 = SUtil.getParam(removeWhitespace, "y=");
            }
            if (SUtil.parameterExist(removeWhitespace, "w=")) {
                n3 = (int)SUtil.getParam(removeWhitespace, "w=");
            }
            if (SUtil.parameterExist(removeWhitespace, "h=")) {
                n4 = (int)SUtil.getParam(removeWhitespace, "h=");
            }
            thing = new RectangleThing(this, this.graph, n, n2, n3, n4);
        }
        else if (s.equals("tangent")) {
            double param = 20.0;
            double param2 = 20.0;
            if (SUtil.parameterExist(removeWhitespace, "x=")) {
                n = SUtil.getParam(removeWhitespace, "x=");
            }
            if (SUtil.parameterExist(removeWhitespace, "y=")) {
                n2 = SUtil.getParam(removeWhitespace, "y=");
            }
            if (SUtil.parameterExist(removeWhitespace, "w=")) {
                param2 = SUtil.getParam(removeWhitespace, "w=");
            }
            if (SUtil.parameterExist(removeWhitespace, "h=")) {
                param = SUtil.getParam(removeWhitespace, "h=");
            }
            thing = new TangentThing(this, this.graph, n, n2, param2, param);
        }
        else if (s.equals("circle")) {
            if (SUtil.parameterExist(removeWhitespace, "x=")) {
                n = SUtil.getParam(removeWhitespace, "x=");
            }
            if (SUtil.parameterExist(removeWhitespace, "y=")) {
                n2 = SUtil.getParam(removeWhitespace, "y=");
            }
            if (SUtil.parameterExist(removeWhitespace, "r=")) {
                n5 = (int)SUtil.getParam(removeWhitespace, "r=");
            }
            thing = new CircleThing(this, this.graph, n, n2, n5);
        }
        else if (s.equals("cursor")) {
            if (SUtil.parameterExist(removeWhitespace, "x=")) {
                n = SUtil.getParam(removeWhitespace, "x=");
            }
            if (SUtil.parameterExist(removeWhitespace, "y=")) {
                n2 = SUtil.getParam(removeWhitespace, "y=");
            }
            if (SUtil.parameterExist(removeWhitespace, "r=")) {
                n5 = (int)SUtil.getParam(removeWhitespace, "r=");
            }
            thing = new MarkerThing(this, this.graph, 2 * n5 + 1, n, n2);
        }
        else if (s.equals("shell")) {
            if (SUtil.parameterExist(removeWhitespace, "x=")) {
                n = SUtil.getParam(removeWhitespace, "x=");
            }
            if (SUtil.parameterExist(removeWhitespace, "y=")) {
                n2 = SUtil.getParam(removeWhitespace, "y=");
            }
            if (SUtil.parameterExist(removeWhitespace, "r=")) {
                n5 = (int)SUtil.getParam(removeWhitespace, "r=");
            }
            thing = new ShellThing(this, this.graph, n, n2, n5);
        }
        else if (s.equals("arrow")) {
            double param3 = 1.0;
            double param4 = 1.0;
            int n6 = 4;
            if (SUtil.parameterExist(removeWhitespace, "s=")) {
                n6 = (int)SUtil.getParam(removeWhitespace, "s=");
            }
            if (SUtil.parameterExist(removeWhitespace, "x=")) {
                n = SUtil.getParam(removeWhitespace, "x=");
            }
            if (SUtil.parameterExist(removeWhitespace, "y=")) {
                n2 = SUtil.getParam(removeWhitespace, "y=");
            }
            if (SUtil.parameterExist(removeWhitespace, "h=")) {
                param3 = SUtil.getParam(removeWhitespace, "h=");
            }
            if (SUtil.parameterExist(removeWhitespace, "v=")) {
                param4 = SUtil.getParam(removeWhitespace, "v=");
            }
            thing = new ArrowThing(this, this.graph, n6, param3, param4, n, n2);
        }
        else if (s.equals("text")) {
            String s2 = "";
            String paramStr = "";
            if (SUtil.parameterExist(removeWhitespace, "x=")) {
                n = SUtil.getParam(removeWhitespace, "x=");
            }
            if (SUtil.parameterExist(removeWhitespace, "y=")) {
                n2 = SUtil.getParam(removeWhitespace, "y=");
            }
            if (SUtil.parameterExist(removeWhitespace, "txt=")) {
                s2 = SUtil.getParamStr(trim, "txt=");
            }
            if (SUtil.parameterExist(removeWhitespace, "text=")) {
                s2 = SUtil.getParamStr(trim, "text=");
            }
            if (SUtil.parameterExist(removeWhitespace, "calc=")) {
                paramStr = SUtil.getParamStr(removeWhitespace, "calc=");
            }
            thing = new TextThing(this, this.graph, s2, paramStr, n, n2);
        }
        else if (s.equals("caption")) {
            String s3 = "";
            if (SUtil.parameterExist(removeWhitespace, "x=")) {
                n = SUtil.getParam(removeWhitespace, "x=");
            }
            if (SUtil.parameterExist(removeWhitespace, "y=")) {
                n2 = SUtil.getParam(removeWhitespace, "y=");
            }
            if (SUtil.parameterExist(removeWhitespace, "txt=")) {
                s3 = SUtil.getParamStr(trim, "txt=");
            }
            if (SUtil.parameterExist(removeWhitespace, "text=")) {
                s3 = SUtil.getParamStr(trim, "text=");
            }
            thing = new CaptionThing(this, this.graph, s3, n, n2);
        }
        else {
            if (s.equals("function")) {
                String paramStr2 = "0";
                String paramStr3 = "x";
                if (SUtil.parameterExist(removeWhitespace, "f=")) {
                    paramStr2 = SUtil.getParamStr(removeWhitespace, "f=");
                }
                if (SUtil.parameterExist(removeWhitespace, "var=")) {
                    paramStr3 = SUtil.getParamStr(removeWhitespace, "var=");
                }
                final int addFunction = this.addFunction(paramStr3, paramStr2);
                if (SUtil.parameterExist(removeWhitespace, "xmin=") && SUtil.parameterExist(removeWhitespace, "xmax=")) {
                    int max = Math.max(this.graph.getSize().width / 2, 100);
                    if (SUtil.parameterExist(removeWhitespace, "n=")) {
                        max = (int)SUtil.getParam(removeWhitespace, "n=");
                    }
                    this.graph.setFunctionRange(addFunction, SUtil.getParam(removeWhitespace, "xmin="), SUtil.getParam(removeWhitespace, "xmax="), max);
                }
                if (SUtil.parameterExist(removeWhitespace, "ymin=") && SUtil.parameterExist(removeWhitespace, "ymax=")) {
                    this.graph.setFunctionClip(addFunction, SUtil.getParam(removeWhitespace, "ymin="), SUtil.getParam(removeWhitespace, "ymax="));
                }
                return addFunction;
            }
            if (s.equals("cfunction")) {
                String s4 = "0";
                final String s5 = "0";
                String paramStr4 = "x";
                if (SUtil.parameterExist(removeWhitespace, "re=")) {
                    s4 = SUtil.getParamStr(removeWhitespace, "re=");
                }
                if (SUtil.parameterExist(removeWhitespace, "im=")) {
                    s4 = SUtil.getParamStr(removeWhitespace, "im=");
                }
                if (SUtil.parameterExist(removeWhitespace, "var=")) {
                    paramStr4 = SUtil.getParamStr(removeWhitespace, "var=");
                }
                final int addCFunction = this.addCFunction(paramStr4, s4, s5);
                if (SUtil.parameterExist(removeWhitespace, "xmin=") && SUtil.parameterExist(removeWhitespace, "xmax=")) {
                    Math.max(this.graph.getSize().width / 2, 100);
                    if (SUtil.parameterExist(removeWhitespace, "n=")) {
                        final int n7 = (int)SUtil.getParam(removeWhitespace, "n=");
                    }
                    this.graph.setFunctionRange(addCFunction, SUtil.getParam(removeWhitespace, "xmin="), SUtil.getParam(removeWhitespace, "xmax="), 400);
                }
                return addCFunction;
            }
            if (s.equals("image")) {
                String s6 = " ";
                if (SUtil.parameterExist(removeWhitespace, "x=")) {
                    n = SUtil.getParam(removeWhitespace, "x=");
                }
                if (SUtil.parameterExist(removeWhitespace, "y=")) {
                    n2 = SUtil.getParam(removeWhitespace, "y=");
                }
                if (SUtil.parameterExist(removeWhitespace, "gif=")) {
                    s6 = SUtil.getParamStr(removeWhitespace, "gif=");
                }
                if (SUtil.parameterExist(removeWhitespace, "file=")) {
                    s6 = SUtil.getParamStr(removeWhitespace, "file=");
                }
                if (s6 == null) {
                    return 0;
                }
                final Image image = this.getImage(s6);
                if (image != null) {
                    thing = new ImageThing(this, this.graph, image, n, n2);
                }
                else {
                    thing = null;
                }
            }
        }
        if (thing == null) {
            System.out.println(String.valueOf(String.valueOf(new StringBuffer("Object not created. name:").append(s).append("parameter list:").append(removeWhitespace))));
            return 0;
        }
        return this.graph.addThing(thing);
    }
    
    private Image getImage(final String s) {
        Image image;
        try {
            image = this.getImage(this.getCodeBase(), s);
        }
        catch (Exception ex) {
            image = null;
        }
        if (image == null) {
            try {
                image = this.getImage(this.getDocumentBase(), s);
            }
            catch (Exception ex2) {
                image = null;
            }
        }
        if (image == null) {
            try {
                image = this.getImage(new URL(s));
            }
            catch (Exception ex3) {
                image = null;
            }
        }
        if (image == null) {
            System.out.println("Failed to load image file.");
            return image;
        }
        final MediaTracker mediaTracker = new MediaTracker(this);
        try {
            mediaTracker.addImage(image, 1);
            mediaTracker.waitForID(1, 5000L);
        }
        catch (Exception ex4) {
            return null;
        }
        if (mediaTracker.isErrorAny()) {
            return null;
        }
        if (image.getHeight(this) < 1) {
            return null;
        }
        return image;
    }
    
    public synchronized boolean set(final int n, String s, String removeWhitespace) {
        s = s.toLowerCase().trim();
        s = SUtil.removeWhitespace(s);
        removeWhitespace.trim();
        removeWhitespace = SUtil.removeWhitespace(removeWhitespace);
        String s2 = "true";
        if (s.equals("scale")) {
            if (SUtil.parameterExist(removeWhitespace, "autoscalex")) {
                if (SUtil.parameterExist(removeWhitespace, "autoscalex=")) {
                    s2 = SUtil.getParamStr(removeWhitespace, "autoscalex=");
                }
                s2 = SUtil.removeWhitespace(s2.toLowerCase());
                if (s2.equals("false")) {
                    this.graph.setAutoscaleX(false);
                }
                else {
                    this.graph.setAutoscaleX(true);
                }
            }
            if (SUtil.parameterExist(removeWhitespace, "noautoscalex")) {
                this.graph.setAutoscaleX(false);
            }
            if (SUtil.parameterExist(removeWhitespace, "autoscaley")) {
                if (SUtil.parameterExist(removeWhitespace, "autoscaley=")) {
                    s2 = SUtil.getParamStr(removeWhitespace, "autoscaley=");
                }
                if (SUtil.removeWhitespace(s2.toLowerCase()).equals("false")) {
                    this.graph.setAutoscaleY(false);
                }
                else {
                    this.graph.setAutoscaleY(true);
                }
            }
            if (SUtil.parameterExist(removeWhitespace, "noautoscaley")) {
                this.graph.setAutoscaleY(false);
            }
            double n2 = this.graph.getMinX();
            double n3 = this.graph.getMaxX();
            if (SUtil.parameterExist(removeWhitespace, "xmin=")) {
                n2 = SUtil.getParam(removeWhitespace, "xmin=");
            }
            if (SUtil.parameterExist(removeWhitespace, "xmax=")) {
                n3 = SUtil.getParam(removeWhitespace, "xmax=");
            }
            if (SUtil.parameterExist(removeWhitespace, "xmin=") || SUtil.parameterExist(removeWhitespace, "xmax=")) {
                this.graph.setMinMaxX(n2, n3);
            }
            double n4 = this.graph.getMinY();
            double n5 = this.graph.getMaxY();
            if (SUtil.parameterExist(removeWhitespace, "ymin=")) {
                n4 = SUtil.getParam(removeWhitespace, "ymin=");
            }
            if (SUtil.parameterExist(removeWhitespace, "ymax=")) {
                n5 = SUtil.getParam(removeWhitespace, "ymax=");
            }
            if (SUtil.parameterExist(removeWhitespace, "ymin=") || SUtil.parameterExist(removeWhitespace, "ymax=")) {
                this.graph.setMinMaxY(n4, n5);
            }
            return true;
        }
        if (this.graph.getThing(n) == null) {
            System.out.println(String.valueOf(String.valueOf(new StringBuffer("Object property not set.  Property name=").append(s).append("Property value=").append(removeWhitespace))));
            return false;
        }
        return false;
    }
    
    public boolean setAnimationSlave(final int n, final int n2) {
        final Thing thing = this.graph.getThing(n);
        final Thing thing2 = this.graph.getThing(n2);
        if (thing == null || thing2 == null) {
            return false;
        }
        thing.addSlave(thing2);
        if (super.autoRefresh) {
            this.graph.repaint();
        }
        return true;
    }
    
    public int addFunction(final String s, final String text) {
        if (text != null) {
            this.funcField.setText(text);
        }
        return this.graph.addFunction(s, text);
    }
    
    public int addCFunction(final String s, final String s2, final String s3) {
        return this.graph.addCFunction(s, s2, s3);
    }
    
    public void deleteFunction(final int n) {
        this.graph.deleteFunction(n);
    }
    
    public void deleteAllFunctions() {
        this.graph.deleteAllFunctions();
    }
    
    public int createSeries(final int n) {
        this.graph.createSeries(n);
        return this.graph.getIDFromSID(n);
    }
    
    public void deleteSeries(final int n) {
        this.graph.deleteSeries(n);
    }
    
    public void deleteAllSeries() {
        this.graph.deleteAllSeries();
    }
    
    public void clearSeries(final int n) {
        this.graph.clearSeriesData(n);
    }
    
    public void clearAllSeries() {
        this.graph.clearAllSeries();
    }
    
    public void updateDataConnections() {
        final Enumeration<SDataConnection> elements = super.dataConnections.elements();
        while (elements.hasMoreElements()) {
            final SDataConnection sDataConnection = elements.nextElement();
            sDataConnection.clearData();
            sDataConnection.registerDatum();
        }
    }
    
    public void loadDataFile(final int n, final String s) {
        this.graph.loadFile(n, s);
    }
    
    public int plotRegression(final int n, final int n2, final int n3) {
        return this.graph.plotRegression(n, n2, n3);
    }
    
    public void setAutoRefresh(final boolean b) {
        super.autoRefresh = b;
        this.graph.setAutoRefresh(b);
    }
    
    public void setAddRepeatedDatum(final int n, final boolean b) {
        this.graph.setAddRepeatedDatum(n, b);
    }
    
    public void setAutoscaleX(final boolean autoscaleX) {
        this.graph.setAutoscaleX(autoscaleX);
    }
    
    public void setAutoscaleY(final boolean autoscaleY) {
        this.graph.setAutoscaleY(autoscaleY);
    }
    
    public void setDrawGrid(final boolean drawGrid) {
        this.graph.setDrawGrid(drawGrid);
    }
    
    public void setDrawZero(final boolean drawZero) {
        this.graph.setDrawZero(drawZero);
    }
    
    public void setDefault() {
        super.clock.stopClock();
        super.clock.setTime(0.0);
        this.graph.setTimeDisplay(false);
        super.clock.removeClockListener(this.graph);
        this.graph.deleteAllSeries();
        this.graph.deleteAllFunctions();
        this.graph.clearAllThings();
        this.deleteDataConnections();
    }
    
    public boolean setVisibility(final int n, final boolean visible) {
        final Thing thing = this.graph.getThing(n);
        if (thing == null) {
            return false;
        }
        thing.setVisible(visible);
        return true;
    }
    
    public boolean setDisplayOffset(final int n, final int n2, final int n3) {
        final Thing thing = this.graph.getThing(n);
        if (thing == null) {
            return false;
        }
        thing.setDisplayOff(n2, n3);
        if (super.autoRefresh) {
            this.graph.repaint();
        }
        return true;
    }
    
    public boolean setDragable(final int n, final boolean dragable) {
        final Thing thing = this.graph.getThing(n);
        if (thing == null) {
            return false;
        }
        thing.setDragable(dragable);
        return true;
    }
    
    public boolean setResizable(final int n, final boolean resizable) {
        final Thing thing = this.graph.getThing(n);
        if (thing == null) {
            return false;
        }
        thing.setResizable(resizable);
        return true;
    }
    
    public int setSketchMode(final boolean sketchMode) {
        return this.graph.setSketchMode(sketchMode);
    }
    
    public void setEnableMouse(final boolean enableMouse) {
        this.graph.setEnableMouse(enableMouse);
    }
    
    public void setFunctionRange(final int n, final double n2, final double n3, final int n4) {
        this.graph.setFunctionRange(n, n2, n3, n4);
    }
    
    public void setFunctionClip(final int n, final double n2, final double n3) {
        this.graph.setFunctionClip(n, n2, n3);
    }
    
    public void setYScaleFromFunction(final int yScaleFromFunction) {
        this.graph.setYScaleFromFunction(yScaleFromFunction);
    }
    
    public boolean setFunctionString(final int n, final String text) {
        if (text != null) {
            this.funcField.setText(text);
        }
        return this.graph.setFunctionString(n, text);
    }
    
    public synchronized boolean swapZOrder(final int n, final int n2) {
        return this.graph.swapZOrder(n, n2);
    }
    
    public String getFunctionString(final int n) {
        return this.graph.getFunctionString(n);
    }
    
    public boolean setFunctionVariable(final int n, final String s) {
        return this.graph.setFunctionVariable(n, s);
    }
    
    public synchronized void setGutters(final int n, final int n2, final int n3, final int n4) {
        this.graph.setGutters(n, n2, n3, n4);
    }
    
    public void setFormat(final String format) {
        this.graph.setFormat(format);
    }
    
    public boolean setFormat(final int n, final String s) {
        final Thing thing = this.graph.getThing(n);
        if (thing == null && (n == 0 || n == this.graph.hashCode())) {
            this.graph.setFormat(s);
            return true;
        }
        final boolean setFormat = thing.setFormat(s);
        if (super.autoRefresh) {
            this.graph.repaint();
        }
        return setFormat;
    }
    
    public void setLabelX(final String labelX) {
        this.graph.setLabelX(labelX);
    }
    
    public void setLabelY(final String labelY) {
        this.graph.setLabelY(labelY);
    }
    
    public void setMarkerSize(final int n, final double n2) {
        this.graph.setMarkerSize(n, n2);
    }
    
    public void setMinMaxX(final double n, final double n2) {
        this.graph.setMinMaxX(n, n2);
    }
    
    public void setMinMaxY(final double n, final double n2) {
        this.graph.setMinMaxY(n, n2);
    }
    
    public void setMinXRange(final boolean b, final double n, final double n2) {
        this.graph.setMinXRange(b, n, n2);
    }
    
    public void setMinYRange(final boolean b, final double n, final double n2) {
        this.graph.setMinYRange(b, n, n2);
    }
    
    public synchronized void setSeriesLegend(final int n, final int n2, final int n3, final String s) {
        this.graph.setSeriesLegend(n, n2, n3, s);
    }
    
    public synchronized void setSeriesLegendRGB(final int n, final int n2, final int n3, final int n4) {
        this.graph.setSeriesLegendColor(n, new Color(n2, n3, n4));
    }
    
    public synchronized void setSeriesStyle(final int n, final boolean b, final int n2) {
        this.graph.setSeriesStyle(n, b, n2);
    }
    
    public synchronized void setSeriesRGB(final int n, final int n2, final int n3, final int n4) {
        this.graph.setSeriesColor(n, new Color(n2, n3, n4));
    }
    
    public synchronized void setRGB(final int n, final int n2, final int n3, final int n4) {
        this.graph.setObjectColor(n, new Color(n2, n3, n4));
    }
    
    public synchronized void setAutoReplaceData(final int n, final boolean b) {
        this.graph.setAutoReplaceData(n, b);
    }
    
    public synchronized void setLastPointMarker(final int n, final boolean b) {
        this.graph.setLastPointMarker(n, b);
    }
    
    public void setTitle(final String title) {
        this.graph.setTitle(title);
    }
    
    public void setTimeDisplay(final boolean timeDisplay) {
        this.graph.setTimeDisplay(timeDisplay);
        if (super.autoRefresh) {
            this.graph.repaint();
        }
    }
    
    public synchronized void setShowAxes(final boolean showAxes) {
        if (this.graph.isShowAxis() == showAxes) {
            return;
        }
        if (showAxes) {
            this.graph.setGutters(20, 20, 20, 20);
            this.graph.drawgrid = true;
        }
        else {
            this.graph.setGutters(0, 0, 0, 0);
            this.graph.drawgrid = false;
        }
        this.graph.setShowAxes(showAxes);
    }
    
    public synchronized void setSquare(final boolean square) {
        this.graph.setSquare(square);
    }
    
    public synchronized void setSorted(final int n, final boolean b) {
        this.graph.setSeriesSorted(n, b);
    }
    
    public synchronized void setStripChart(final int n, final int n2, final boolean b) {
        this.graph.setSeriesStripChart(n, n2, b);
    }
    
    void addFuncBtn_actionPerformed(final ActionEvent actionEvent) {
        this.graph.deleteAllFunctions();
        this.addFunction("x", this.funcField.getText());
    }
    
    void addDatumBtn_actionPerformed(final ActionEvent actionEvent) {
        this.addDatum(1, this.xField.getValue(), this.yField.getValue());
    }
    
    void button4_actionPerformed(final ActionEvent actionEvent) {
        this.graph.clearAllData();
    }
    
    void clearFuncBtn_actionPerformed(final ActionEvent actionEvent) {
        this.graph.deleteAllFunctions();
    }
}
