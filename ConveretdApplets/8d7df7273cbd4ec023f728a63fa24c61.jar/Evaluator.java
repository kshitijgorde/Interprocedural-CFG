import java.util.StringTokenizer;
import java.awt.event.WindowListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.ActionEvent;
import edu.hws.jcm.data.ParseError;
import edu.hws.jcm.data.Value;
import edu.hws.jcm.awt.DisplayLabel;
import edu.hws.jcm.awt.VariableInput;
import java.awt.Label;
import edu.hws.jcm.data.MathObject;
import edu.hws.jcm.functions.SummationParser;
import edu.hws.jcm.data.Parser;
import edu.hws.jcm.awt.JCMPanel;
import java.awt.Component;
import java.awt.LayoutManager;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Button;
import java.awt.Frame;
import java.awt.event.ActionListener;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class Evaluator extends Applet implements ActionListener
{
    private Frame frame;
    private String frameTitle;
    private Button launchButton;
    private String launchButtonName;
    private String[] colorNames;
    private Color[] colors;
    
    public void init() {
        this.frameTitle = this.getParameter("FrameTitle");
        if (this.frameTitle == null) {
            this.frameTitle = "Calculator";
            final int lastIndex = this.frameTitle.lastIndexOf(46);
            if (lastIndex > -1) {
                this.frameTitle = this.frameTitle.substring(lastIndex + 1);
            }
        }
        this.setLayout(new BorderLayout());
        final int height = this.getSize().height;
        this.launchButtonName = this.getParameter("LaunchButtonName");
        if ((height > 0 && height <= 35) || this.launchButtonName != null) {
            if (this.launchButtonName == null) {
                this.launchButtonName = "Launch " + this.frameTitle;
            }
            this.add(this.launchButton = new Button(this.launchButtonName), "Center");
            this.launchButton.addActionListener(this);
        }
        else {
            this.add(this.makeMainPanel(), "Center");
        }
    }
    
    public JCMPanel makeMainPanel() {
        final Color colorParam = this.getColorParam("BackgroundColor", Color.gray);
        final Color colorParam2 = this.getColorParam("LabelBackground", new Color(225, 225, 225));
        final Color colorParam3 = this.getColorParam("LabelForeground", new Color(0, 0, 200));
        final Color colorParam4 = this.getColorParam("AnswerBackground", colorParam2);
        final Color colorParam5 = this.getColorParam("AnswerForeground", Color.red);
        final Color colorParam6 = this.getColorParam("InputBackground", Color.white);
        final Color colorParam7 = this.getColorParam("InputForeground", Color.black);
        final JCMPanel jcmPanel = new JCMPanel(5);
        jcmPanel.setBackground(colorParam);
        jcmPanel.setInsetGap(3);
        this.setLayout(new BorderLayout());
        this.add(jcmPanel, "Center");
        final JCMPanel jcmPanel2 = new JCMPanel(0, 1, 3);
        jcmPanel.add(jcmPanel2, "Center");
        final JCMPanel jcmPanel3 = new JCMPanel(0, 1, 3);
        jcmPanel.add(jcmPanel3, "East");
        final Parser parser = new Parser();
        parser.addOptions(64);
        parser.add(new SummationParser());
        int n = 0;
        String s = this.getParameter("Variable");
        if (s == null) {
            s = this.getParameter("Variable1");
            if (s == null) {
                s = "x";
            }
            else {
                n = 1;
            }
        }
        final String s2 = s;
        while (s != null) {
            String trim = "0";
            String s3 = s.trim();
            final int index = s3.indexOf(" ");
            if (index > 0) {
                trim = s3.substring(index + 1).trim();
                s3 = s3.substring(0, index);
            }
            final Label label = new Label(" Input:  " + s3 + " =  ", 2);
            label.setBackground(colorParam2);
            label.setForeground(colorParam3);
            jcmPanel2.add(label);
            final VariableInput variableInput = new VariableInput(s3, trim, parser);
            variableInput.setBackground(colorParam6);
            variableInput.setForeground(colorParam7);
            variableInput.setThrowErrors(false);
            variableInput.setOnTextChange(jcmPanel.getController());
            variableInput.setOnUserAction(jcmPanel.getController());
            jcmPanel3.add(variableInput);
            ++n;
            s = this.getParameter("Variable" + n);
        }
        int n2 = 0;
        String s4 = this.getParameter("Expression");
        if (s4 == null) {
            s4 = this.getParameter("Expression1");
            if (s4 == null) {
                s4 = "log2(" + s2 + ")";
            }
            else {
                n2 = 1;
            }
        }
        while (s4 != null) {
            final Label label2 = new Label(" " + s4 + " =  ", 2);
            label2.setBackground(colorParam2);
            label2.setForeground(colorParam3);
            jcmPanel2.add(label2);
            try {
                final DisplayLabel displayLabel = new DisplayLabel("#", parser.parse(s4));
                displayLabel.setBackground(colorParam4);
                displayLabel.setForeground(colorParam5);
                displayLabel.setAlignment(1);
                jcmPanel3.add(displayLabel);
            }
            catch (ParseError parseError) {
                jcmPanel3.add(new Label("invalid function"));
            }
            ++n2;
            s4 = this.getParameter("Expression" + n2);
        }
        return jcmPanel;
    }
    
    public synchronized void actionPerformed(final ActionEvent actionEvent) {
        if (actionEvent.getSource() == this.launchButton && this.launchButton != null) {
            this.launchButton.setEnabled(false);
            if (this.frame == null) {
                (this.frame = new Frame(this.frameTitle)).add(this.makeMainPanel());
                this.frame.addWindowListener(new WindowAdapter() {
                    public void windowClosing(final WindowEvent windowEvent) {
                        Evaluator.this.frame.dispose();
                    }
                    
                    public void windowClosed(final WindowEvent windowEvent) {
                        Evaluator.this.frameClosed();
                    }
                });
                this.frame.pack();
                this.frame.setLocation(50, 50);
                this.frame.show();
                this.launchButton.setLabel("Close Window");
                this.launchButton.setEnabled(true);
            }
            else {
                this.frame.dispose();
            }
        }
    }
    
    private synchronized void frameClosed() {
        this.frame = null;
        this.launchButton.setLabel(this.launchButtonName);
        this.launchButton.setEnabled(true);
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
        final StringTokenizer stringTokenizer = new StringTokenizer(trim, " \t,;");
        if (stringTokenizer.countTokens() < 3) {
            return color;
        }
        final double[] array = new double[3];
        for (int j = 0; j < 3; ++j) {
            try {
                array[j] = new Double(stringTokenizer.nextToken());
            }
            catch (NumberFormatException ex) {
                return color;
            }
        }
        if (array[0] < 0.0 || array[0] > 255.0 || array[1] < 0.0 || array[1] > 255.0 || array[2] < 0.0 || array[2] > 255.0) {
            return color;
        }
        return new Color((int)Math.round(array[0]), (int)Math.round(array[1]), (int)Math.round(array[2]));
    }
    
    public Evaluator() {
        this.colorNames = new String[] { "black", "red", "blue", "green", "yellow", "cyan", "magenta", "gray", "darkgray", "lightgray", "pink", "orange", "white" };
        this.colors = new Color[] { Color.black, Color.red, Color.blue, Color.green, Color.yellow, Color.cyan, Color.magenta, Color.gray, Color.darkGray, Color.lightGray, Color.pink, Color.orange, Color.white };
    }
}
