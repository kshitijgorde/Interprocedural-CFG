// 
// Decompiled by Procyon v0.5.30
// 

package xfunctions;

import java.awt.Event;
import xfunctions.functions.TableFunction;
import xfunctions.functions.BezierFunction;
import xfunctions.functions.ExpressionFunction;
import java.awt.Container;
import java.awt.Component;
import java.awt.LayoutManager;
import java.awt.BorderLayout;
import java.awt.Color;
import java.applet.Applet;
import xfunctions.functions.Parser;
import java.awt.CardLayout;
import java.awt.Choice;
import java.awt.Panel;

public class xFunctionsPanel extends Panel
{
    public static final String versionDate = "27 October 1999";
    Choice panelChoice;
    CardLayout cardLayout;
    Panel cardPanel;
    int currentChoice;
    GenericPanel currentPanel;
    Panel normalPanel;
    CardLayout topLevelLayout;
    ExpressionInputPanel expressionInputPanel;
    BezierInputPanel bezierInputPanel;
    TableInputPanel tableInputPanel;
    Parser parser;
    FunctionList functions;
    Object[][] examples;
    String[] panelNames;
    GenericPanel[] panels;
    
    public xFunctionsPanel(final String s, final Applet applet) {
        this.panelNames = new String[] { "Main Screen", "Multigraph Utility", "Animate Utility", "Parametric Curves Utility", "Derivatives Utility", "Riemann Sums Utility", "Integral Curves Utility", "Graph 3D Utility" };
        this.panels = new GenericPanel[this.panelNames.length];
        this.setBackground(Color.darkGray);
        (this.normalPanel = new Panel()).setLayout(new BorderLayout(2, 2));
        this.parser = new Parser();
        this.panelChoice = new Choice();
        for (int i = 0; i < this.panelNames.length; ++i) {
            this.panelChoice.addItem(this.panelNames[i]);
        }
        this.currentChoice = 0;
        final Panel panel = new Panel();
        panel.setLayout(new BorderLayout());
        panel.setBackground(Color.lightGray);
        panel.add("Center", this.panelChoice);
        this.normalPanel.add("North", panel);
        (this.cardPanel = new Panel()).setBackground(Color.lightGray);
        this.cardLayout = new CardLayout();
        this.cardPanel.setLayout(this.cardLayout);
        this.normalPanel.add("Center", this.cardPanel);
        this.panels[0] = new MainPanel(this, this.parser, s, applet);
        this.functions = ((MainPanel)this.panels[0]).functions;
        this.cardPanel.add(this.panelNames[0], this.panels[0]);
        this.currentChoice = 0;
        this.currentPanel = this.panels[0];
        this.setLayout(this.topLevelLayout = new CardLayout(2, 2));
        this.add("Normal Panel", this.normalPanel);
        try {
            if (s == null && applet == null) {
                this.examples = null;
            }
            else {
                this.examples = Examples.getExamples(s, applet, this.parser, this.functions);
            }
            if (this.examples != null) {
                for (int j = 0; j < this.examples.length; ++j) {
                    this.panelChoice.addItem("Example:  " + (String)this.examples[j][1]);
                }
            }
        }
        catch (Exception ex) {
            System.out.println("Exception while getting examples in init().");
        }
    }
    
    public void stop() {
        if (this.currentPanel != null) {
            this.currentPanel.aboutToHide();
        }
    }
    
    public void start() {
        if (this.currentPanel != null) {
            this.currentPanel.aboutToShow();
        }
        this.panelChoice.enable();
    }
    
    private void makePanel(final int n) {
        if (this.panels[n] != null) {
            return;
        }
        switch (n) {
            case 1: {
                this.panels[1] = new MultigraphPanel(this.parser);
                break;
            }
            case 2: {
                this.panels[2] = new AnimatorPanel(this.parser);
                break;
            }
            case 3: {
                this.panels[3] = new ParametricCurvesPanel(this.parser);
                break;
            }
            case 4: {
                this.panels[4] = new DerivativesPanel(this.parser);
                break;
            }
            case 5: {
                this.panels[5] = new RiemannSumPanel(this.parser);
                break;
            }
            case 6: {
                this.panels[6] = new IntegralCurvesPanel(this.parser);
                break;
            }
            case 7: {
                this.panels[7] = new Graph3DPanel(this.parser);
                break;
            }
        }
        this.cardPanel.add(this.panelNames[n], this.panels[n]);
    }
    
    void backToMainPanel() {
        this.panels[0].aboutToShow();
        this.topLevelLayout.show(this, "Normal Panel");
    }
    
    private void showPanel() {
        final int selectedIndex = this.panelChoice.getSelectedIndex();
        if (selectedIndex > 7) {
            this.showExample(selectedIndex);
            return;
        }
        if (selectedIndex == this.currentChoice) {
            return;
        }
        this.currentPanel.aboutToHide();
        this.makePanel(this.currentChoice = selectedIndex);
        (this.currentPanel = this.panels[selectedIndex]).aboutToShow();
        this.cardLayout.show(this.cardPanel, this.panelNames[this.currentChoice]);
    }
    
    private void showExample(final int n) {
        try {
            final Object[] array = this.examples[n - 8];
            final int intValue = (int)array[0];
            this.makePanel(intValue);
            this.panels[intValue].installExample(array);
            if (this.panels[intValue] != this.currentPanel) {
                this.currentPanel.aboutToHide();
                (this.currentPanel = this.panels[intValue]).aboutToShow();
                this.cardLayout.show(this.cardPanel, this.panelNames[intValue]);
                this.currentChoice = intValue;
            }
        }
        catch (Exception ex) {
            System.out.println("Error while trying to show example: " + ex);
        }
    }
    
    void showExpressionInput(final ExpressionFunction function) {
        if (this.expressionInputPanel == null) {
            this.add("Expression Input", this.expressionInputPanel = new ExpressionInputPanel(this, this.parser, this.functions));
        }
        this.expressionInputPanel.setFunction(function);
        this.expressionInputPanel.aboutToShow();
        this.panels[0].aboutToHide();
        this.topLevelLayout.show(this, "Expression Input");
    }
    
    void showBezierInput(final BezierFunction function) {
        if (this.bezierInputPanel == null) {
            this.add("Bezier Input", this.bezierInputPanel = new BezierInputPanel(this, this.parser, this.functions));
        }
        this.bezierInputPanel.setFunction(function);
        this.bezierInputPanel.aboutToShow();
        this.panels[0].aboutToHide();
        this.topLevelLayout.show(this, "Bezier Input");
    }
    
    void showTableInput(final TableFunction function) {
        if (this.tableInputPanel == null) {
            this.add("Table Input", this.tableInputPanel = new TableInputPanel(this, this.parser, this.functions));
        }
        this.tableInputPanel.setFunction(function);
        this.tableInputPanel.aboutToShow();
        this.panels[0].aboutToHide();
        this.topLevelLayout.show(this, "Table Input");
    }
    
    public boolean action(final Event event, final Object o) {
        if (event.target == this.panelChoice) {
            this.showPanel();
            return true;
        }
        return super.action(event, o);
    }
}
