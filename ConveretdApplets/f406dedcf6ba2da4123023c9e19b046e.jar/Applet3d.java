import java.util.NoSuchElementException;
import java.awt.Event;
import java.awt.Graphics;
import java.awt.Color;
import java.awt.Label;
import java.awt.Panel;
import java.awt.Component;
import java.awt.LayoutManager;
import java.awt.BorderLayout;
import java.util.StringTokenizer;
import java.util.Hashtable;
import java.awt.Button;
import java.awt.Choice;
import java.awt.TextField;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class Applet3d extends Applet
{
    TextField transformValues;
    TextField windowValues;
    Choice transform;
    Choice modelChoice;
    Button reset;
    Viewer viewer;
    Hashtable parameters;
    
    public Applet3d() {
        this.parameters = new Hashtable();
    }
    
    Viewer makeViewer() {
        final Viewer viewer = new Viewer(this);
        viewer.setWindow(-6.0, -6.0, 12.0, 12.0);
        return viewer;
    }
    
    public void init() {
        final String[][] defaultParameters = this.getDefaultParameters();
        for (int i = 0; i < defaultParameters.length; ++i) {
            if (super.getParameter(defaultParameters[i][0]) == null) {
                this.parameters.put(defaultParameters[i][0], defaultParameters[i][1]);
            }
        }
        final String string = this.getDocumentBase().toString();
        final int index = string.indexOf(63);
        if (index != -1) {
            final StringTokenizer stringTokenizer = new StringTokenizer(this.urlDecode(string.substring(index + 1)), "&");
            while (stringTokenizer.hasMoreTokens()) {
                final String nextToken = stringTokenizer.nextToken();
                final int index2 = nextToken.indexOf(61);
                if (index2 != -1) {
                    this.parameters.put(nextToken.substring(0, index2), nextToken.substring(index2 + 1));
                }
            }
        }
        final Color colorParameter = this.getColorParameter("bgcolor");
        if (colorParameter != null) {
            this.setBackground(colorParameter);
        }
        this.setLayout(new BorderLayout());
        this.add("Center", this.viewer = this.makeViewer());
        if (this.getParameter("transforms") != null) {
            final Panel panel = new Panel();
            panel.setLayout(new BorderLayout());
            final Panel panel2 = new Panel();
            panel2.add(this.reset = new Button("Reset"));
            panel2.add(new Label("Window"));
            panel2.add(this.windowValues = new TextField("-6 -6 12 12", 20));
            panel.add("North", panel2);
            final Panel panel3 = new Panel();
            (this.transform = new Choice()).addItem("translation");
            this.transform.addItem("scale");
            this.transform.addItem("rotation x");
            this.transform.addItem("rotation y");
            this.transform.addItem("rotation z");
            panel3.add(this.transform);
            panel3.add(this.transformValues = new TextField("0 0 0", 20));
            panel.add("South", panel3);
            this.add("North", panel);
        }
        this.modelChoice = this.createModelChoice();
        this.viewer.setModel(this.defaultModel());
        if (this.modelChoice != null) {
            this.add("South", this.modelChoice);
        }
    }
    
    public void start() {
        this.getGraphics().clearRect(0, 0, this.size().width, this.size().height);
        this.viewer.start();
    }
    
    public void paint(final Graphics graphics) {
        this.viewer.paint(graphics);
    }
    
    public void update(final Graphics graphics) {
        this.paint(graphics);
    }
    
    public boolean mouseEnter(final Event event, final int n, final int n2) {
        this.showStatus("Hold the mouse button down and move the mouse to rotate the logo");
        return true;
    }
    
    public boolean mouseExit(final Event event, final int n, final int n2) {
        this.showStatus("");
        return true;
    }
    
    public boolean action(final Event event, final Object o) {
        if (event.target.equals(this.reset) || event.target.equals(this.modelChoice)) {
            this.viewer.setModel(this.selectModel(this.modelChoice.getSelectedItem()));
        }
        else if (event.target.equals(this.windowValues)) {
            final String s = (String)o;
            try {
                final StringTokenizer stringTokenizer = new StringTokenizer(s);
                final float[] array = new float[4];
                for (int i = 0; i < array.length; ++i) {
                    array[i] = Float.valueOf(stringTokenizer.nextToken());
                }
                this.viewer.setWindow(array[0], array[1], array[2] - array[0], array[3] - array[1]);
            }
            catch (NoSuchElementException ex) {
                this.showStatus("Enter four numbers separated by spaces");
            }
            catch (NumberFormatException ex2) {
                this.showStatus("Enter four numbers separated by spaces");
            }
        }
        return true;
    }
    
    public Choice createModelChoice() {
        return null;
    }
    
    public Object3dList selectModel(final String s) {
        return this.defaultModel();
    }
    
    public Object3dList defaultModel() {
        return null;
    }
    
    public String urlDecode(final String s) {
        final StringBuffer sb = new StringBuffer();
        int i = 0;
        while (i < s.length()) {
            if (s.charAt(i) == '%') {
                sb.append((char)Integer.parseInt(s.substring(i + 1, i + 3), 16));
                i += 3;
            }
            else {
                if (s.charAt(i) == '+') {
                    sb.append(' ');
                }
                else {
                    sb.append(s.charAt(i));
                }
                ++i;
            }
        }
        return sb.toString();
    }
    
    public String getParameter(final String s) {
        final String s2 = this.parameters.get(s);
        if (s2 == null) {
            return super.getParameter(s);
        }
        return s2;
    }
    
    public String[][] getDefaultParameters() {
        return new String[][] { { "bgcolor", "ffffff" } };
    }
    
    protected Color getColorParameter(final String s) {
        final String parameter = this.getParameter(s);
        int int1;
        try {
            int1 = Integer.parseInt(parameter, 16);
        }
        catch (NumberFormatException ex) {
            return null;
        }
        return new Color(int1);
    }
    
    public double getDoubleParameter(final String s, final double n) {
        final String parameter = this.getParameter(s);
        double doubleValue;
        try {
            doubleValue = Double.valueOf(parameter);
        }
        catch (NumberFormatException ex) {
            return n;
        }
        return doubleValue;
    }
    
    public int getIntParameter(final String s, final int n) {
        final String parameter = this.getParameter(s);
        int int1;
        try {
            int1 = Integer.parseInt(parameter);
        }
        catch (NumberFormatException ex) {
            return n;
        }
        return int1;
    }
    
    public boolean getBoolParameter(final String s) {
        final String parameter = this.getParameter(s);
        return parameter != null && parameter.equals("on");
    }
    
    public String[][] getParameterInfo() {
        return new String[][] { { "bgcolor", "hexadecimal colour value", "background colour" } };
    }
}
