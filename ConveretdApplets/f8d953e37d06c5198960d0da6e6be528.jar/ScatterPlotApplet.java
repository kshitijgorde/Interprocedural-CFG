import java.awt.event.WindowListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.ActionEvent;
import java.io.InputStream;
import java.io.Reader;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.Vector;
import java.awt.Container;
import java.awt.Font;
import edu.hws.jcm.awt.DisplayLabel;
import edu.hws.jcm.data.Value;
import java.awt.Label;
import edu.hws.jcm.awt.ComputeButton;
import edu.hws.jcm.awt.ErrorReporter;
import edu.hws.jcm.draw.Drawable;
import edu.hws.jcm.draw.Axes;
import edu.hws.jcm.awt.ExpressionInput;
import edu.hws.jcm.data.Parser;
import java.awt.Color;
import edu.hws.jcm.awt.JCMPanel;
import java.awt.Panel;
import java.awt.Component;
import java.awt.LayoutManager;
import java.awt.BorderLayout;
import edu.hws.jcm.awt.Controller;
import java.awt.Choice;
import edu.hws.jcm.draw.DisplayCanvas;
import edu.hws.jcm.draw.ScatterPlot;
import edu.hws.jcm.awt.DataTableInput;
import java.awt.Button;
import java.awt.Frame;
import java.awt.event.ActionListener;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class ScatterPlotApplet extends Applet implements ActionListener
{
    private Frame frame;
    private String frameTitle;
    private Button launchButton;
    private String launchButtonName;
    private DataTableInput table;
    private ScatterPlot scatterPlot;
    private DisplayCanvas canvas;
    private Button loadFileButton;
    private Choice fileMenu;
    private String[] fileNames;
    private Controller mainController;
    
    public void init() {
        this.frameTitle = this.getParameter("FrameTitle");
        if (this.frameTitle == null) {
            this.frameTitle = "Scatter Plots";
            final int lastIndex = this.frameTitle.lastIndexOf(46);
            if (lastIndex > -1) {
                this.frameTitle = this.frameTitle.substring(lastIndex + 1);
            }
        }
        this.setLayout(new BorderLayout());
        final int height = this.getSize().height;
        this.launchButtonName = this.getParameter("LaunchButtonName");
        if ((height > 0 && height <= 50) || this.launchButtonName != null) {
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
    
    public Panel makeMainPanel() {
        final JCMPanel jcmPanel = new JCMPanel(2);
        this.mainController = jcmPanel.getController();
        jcmPanel.setBackground(new Color(0, 0, 180));
        jcmPanel.setInsetGap(2);
        this.setLayout(new BorderLayout());
        (this.table = new DataTableInput(null, 2)).setColumnName(0, this.getParameter("ColumnName1", "X"));
        this.table.setColumnName(1, this.getParameter("ColumnName2", "Y"));
        this.table.setThrowErrors(true);
        if ("yes".equalsIgnoreCase(this.getParameter("ShowColumnTitles", "yes"))) {
            this.table.setShowColumnTitles(true);
        }
        if ("yes".equalsIgnoreCase(this.getParameter("ShowRowNumbers", "yes"))) {
            this.table.setShowRowNumbers(true);
        }
        final Parser parser = new Parser();
        this.table.addVariablesToParser(parser);
        final ExpressionInput expressionInput = new ExpressionInput(this.table.getColumnName(0), parser);
        expressionInput.setOnUserAction(this.mainController);
        final ExpressionInput expressionInput2 = new ExpressionInput(this.table.getColumnName(1), parser);
        expressionInput2.setOnUserAction(this.mainController);
        this.scatterPlot = new ScatterPlot(this.table, expressionInput.getExpression(), expressionInput2.getExpression());
        if (!"yes".equalsIgnoreCase(this.getParameter("ShowRegressionLine", "yes"))) {
            this.scatterPlot.setShowRegressionLine(false);
        }
        if (!"yes".equalsIgnoreCase(this.getParameter("MissingValueIsError", "yes"))) {
            this.scatterPlot.setMissingValueIsError(false);
        }
        (this.canvas = new DisplayCanvas()).add(new Axes());
        this.canvas.add(this.scatterPlot);
        this.mainController.setErrorReporter(this.canvas);
        final ComputeButton computeButton = new ComputeButton("Update Display");
        computeButton.setOnUserAction(this.mainController);
        computeButton.setBackground(Color.lightGray);
        final Panel makefileMenu = this.makefileMenu();
        Container container = null;
        if ("yes".equalsIgnoreCase(this.getParameter("UseExpressionInputs", "yes"))) {
            final JCMPanel jcmPanel2 = new JCMPanel(1, 2);
            jcmPanel2.setBackground(Color.lightGray);
            final JCMPanel jcmPanel3 = new JCMPanel();
            jcmPanel3.add(new Label("  Plot:  "), "West");
            jcmPanel3.add(expressionInput, "Center");
            jcmPanel2.add(jcmPanel3);
            final JCMPanel jcmPanel4 = new JCMPanel();
            jcmPanel4.add(new Label(" versus: "), "West");
            jcmPanel4.add(expressionInput2, "Center");
            jcmPanel2.add(jcmPanel4);
            container = new JCMPanel(new BorderLayout(12, 3));
            container.add(jcmPanel2, "Center");
            container.add(computeButton, "East");
        }
        if (this.scatterPlot.getShowRegressionLine() && "yes".equalsIgnoreCase(this.getParameter("ShowStats", "yes"))) {
            final DisplayLabel displayLabel = new DisplayLabel("Slope = #;  Intercept = #;  Correlation = #", new Value[] { this.scatterPlot.getValueObject(1), this.scatterPlot.getValueObject(0), this.scatterPlot.getValueObject(5) });
            displayLabel.setAlignment(1);
            displayLabel.setBackground(Color.lightGray);
            displayLabel.setForeground(new Color(200, 0, 0));
            displayLabel.setFont(new Font("Serif", 0, 14));
            if (container != null) {
                container.add(displayLabel, "South");
            }
            else {
                container = new JCMPanel(new BorderLayout(12, 3));
                container.add(displayLabel, "Center");
                container.add(computeButton, "East");
            }
        }
        if (container == null) {
            if (makefileMenu != null) {
                makefileMenu.add(computeButton, "East");
            }
            else {
                container = new Panel();
                container.add(computeButton);
            }
        }
        jcmPanel.add(this.canvas, "Center");
        jcmPanel.add(this.table, "West");
        if (container != null) {
            jcmPanel.add(container, "South");
        }
        if (makefileMenu != null) {
            jcmPanel.add(makefileMenu, "North");
        }
        else {
            final String parameter = this.getParameter("PanelTitle");
            if (parameter != null) {
                final Label label = new Label(parameter, 1);
                label.setBackground(Color.lightGray);
                label.setForeground(new Color(200, 0, 0));
                label.setFont(new Font("Serif", 0, 14));
                jcmPanel.add(label, "North");
            }
        }
        return jcmPanel;
    }
    
    private Panel makefileMenu() {
        final Vector vector = new Vector<String>();
        this.fileMenu = new Choice();
        String s = this.getParameter("File");
        int n = 1;
        if (s == null) {
            s = this.getParameter("File1");
            n = 2;
        }
        while (s != null) {
            String s2 = s.trim();
            final int index = s2.indexOf(";");
            String trim;
            if (index == -1) {
                trim = s2;
            }
            else {
                trim = s2.substring(0, index).trim();
                s2 = s2.substring(index + 1).trim();
            }
            vector.addElement(s2);
            this.fileMenu.add(trim);
            s = this.getParameter("File" + n);
            ++n;
        }
        if (vector.size() == 0) {
            this.fileMenu = null;
            return null;
        }
        this.fileNames = new String[vector.size()];
        for (int i = 0; i < vector.size(); ++i) {
            this.fileNames[i] = vector.elementAt(i);
        }
        final Panel panel = new Panel();
        panel.setBackground(Color.lightGray);
        panel.setLayout(new BorderLayout(5, 5));
        panel.add(this.fileMenu, "Center");
        (this.loadFileButton = new Button("Load Data File: ")).addActionListener(this);
        panel.add(this.loadFileButton, "West");
        this.fileMenu.setBackground(Color.white);
        return panel;
    }
    
    private void doLoadFile(final String s) {
        InputStream openStream;
        try {
            openStream = new URL(this.getDocumentBase(), s).openStream();
        }
        catch (Exception ex) {
            this.canvas.setErrorMessage(null, "Unable to open file named \"" + s + "\": " + ex);
            return;
        }
        final InputStreamReader inputStreamReader = new InputStreamReader(openStream);
        try {
            this.table.readFromStream(inputStreamReader);
            inputStreamReader.close();
        }
        catch (Exception ex2) {
            this.canvas.setErrorMessage(null, "Unable to get data from file \"" + s + "\": " + ex2.getMessage());
            return;
        }
        this.mainController.compute();
    }
    
    public synchronized void actionPerformed(final ActionEvent actionEvent) {
        final Object source = actionEvent.getSource();
        if (this.loadFileButton != null && source == this.loadFileButton) {
            this.doLoadFile(this.fileNames[this.fileMenu.getSelectedIndex()]);
        }
        else if (source == this.launchButton && this.launchButton != null) {
            this.launchButton.setEnabled(false);
            if (this.frame == null) {
                (this.frame = new Frame(this.frameTitle)).add(this.makeMainPanel());
                this.frame.addWindowListener(new WindowAdapter() {
                    public void windowClosing(final WindowEvent windowEvent) {
                        ScatterPlotApplet.this.frame.dispose();
                    }
                    
                    public void windowClosed(final WindowEvent windowEvent) {
                        ScatterPlotApplet.this.frameClosed();
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
    
    protected String getParameter(final String s, final String s2) {
        final String parameter = this.getParameter(s);
        return (parameter == null) ? s2 : parameter;
    }
}
