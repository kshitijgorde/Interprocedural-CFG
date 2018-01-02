// 
// Decompiled by Procyon v0.5.30
// 

package medusa.extended;

import medusa.display.BasicGraphPanel;
import java.awt.event.ItemEvent;
import java.util.Iterator;
import medusa.graph.Edge;
import medusa.graph.Node;
import java.util.ArrayList;
import java.applet.Applet;
import netscape.javascript.JSObject;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.FlowLayout;
import medusa.MedusaSettings;
import medusa.graph.Graph;
import java.awt.Container;
import javax.swing.JPanel;
import java.awt.Component;
import javax.swing.JScrollPane;
import java.awt.LayoutManager;
import java.awt.BorderLayout;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import java.awt.Color;
import medusa.applet.MedusaLite;

public class ExtendedApplet extends MedusaLite
{
    final Color babyBlue;
    public ExtendedPanel panel;
    JCheckBox relax;
    JCheckBox pretty;
    JCheckBox directed;
    JCheckBox names;
    JButton lgbutton;
    JButton restbutton;
    JButton frButton;
    JButton extButton;
    JCheckBox confidence;
    public String somevalue;
    
    public ExtendedApplet() {
        this.babyBlue = new Color(230, 226, 230);
        this.relax = new JCheckBox("Relax", false);
        this.pretty = new JCheckBox("Edge colours", true);
        this.directed = new JCheckBox("Directed", true);
        this.names = new JCheckBox("Labels", true);
        this.lgbutton = new JButton("Open/Close Legend");
        this.restbutton = new JButton("Restore Graph");
        this.frButton = new JButton("Reorganize Graph");
        this.extButton = new JButton("Add Genes");
        this.confidence = new JCheckBox("Confidence", true);
        this.somevalue = null;
    }
    
    public void init() {
        this.initPanel();
        final Container contentPane = this.getContentPane();
        contentPane.setLayout(new BorderLayout());
        final Graph parameters = new ExtendedDataLoader().readParameters(this.getParameter("edges"), this.getParameter("nodes"));
        System.out.println(parameters.report());
        System.out.println("panel is ");
        if (this.getPanel() == null) {
            System.out.println("null");
        }
        else {
            System.out.println("not null");
        }
        this.getPanel().setGraph(parameters);
        System.out.println("graph loaded");
        this.setPrefs();
        this.setXParam((this.getParameter("X") == null) ? 400 : Integer.parseInt(this.getParameter("X")));
        this.setYParam((this.getParameter("Y") == null) ? 400 : Integer.parseInt(this.getParameter("Y")));
        this.getPanel().setSize(this.getXParam(), this.getYParam());
        this.getPanel().setTimeFrameXY(this.getPanel().getWidth(), this.getPanel().getHeight());
        this.setJScrollPane(new JScrollPane(this.getPanel()));
        contentPane.add("Center", this.getJScrollPane());
        this.controlPanel = new JPanel();
        this.populateControlPanel();
        contentPane.add("North", this.controlPanel);
        System.out.println("Setting visible");
        this.setVisible(true);
        this.getJScrollPane().validate();
        final String parameter = this.getParameter("layout");
        if (parameter != null && parameter.compareTo("true") == 0) {
            this.getPanel().energy();
        }
    }
    
    public void initPanel() {
        System.out.println("Initializing AppletPanel");
        final MedusaSettings medusaSettings = new MedusaSettings(this.getParameter("settings"));
        final String parameter = this.getParameter("linkStart");
        final String s = "";
        final String parameter2 = this.getParameter("refURL");
        System.out.println(parameter);
        final ExtendedPanel extendedPanel = new ExtendedPanel(medusaSettings, this, parameter, s, parameter2);
        this.setPanel(new ExtendedPanel(medusaSettings, this, parameter, s, parameter2));
        System.out.println(extendedPanel);
    }
    
    public void setPrefs() {
        this.getPanel().setPretty(true);
        this.getPanel().setArrows(true);
        this.getPanel().setLabel(true);
        this.getPanel().setConfidence(true);
        this.getPanel().setCool(true);
        this.getPanel().repaint();
    }
    
    public void populateControlPanel() {
        this.controlPanel.setLayout(new FlowLayout());
        this.controlPanel.setBackground(this.babyBlue);
        this.relax.setBackground(this.babyBlue);
        this.pretty.setBackground(this.babyBlue);
        this.names.setBackground(this.babyBlue);
        this.lgbutton.setBackground(this.babyBlue);
        this.restbutton.setBackground(this.babyBlue);
        this.frButton.setBackground(this.babyBlue);
        this.extButton.setBackground(this.babyBlue);
        this.controlPanel.add(this.lgbutton);
        this.lgbutton.addActionListener(this);
        this.controlPanel.add(this.restbutton);
        this.restbutton.addActionListener(this);
        this.controlPanel.add(this.frButton);
        this.frButton.addActionListener(this);
        this.controlPanel.add(this.extButton);
        this.extButton.addActionListener(this);
    }
    
    public void actionPerformed(final ActionEvent actionEvent) {
        final Object source = actionEvent.getSource();
        if (source == this.lgbutton) {
            JSObject.getWindow((Applet)this).call("openLegend", (Object[])null);
        }
        if (source == this.restbutton) {
            JSObject.getWindow((Applet)this).call("restoregraph", (Object[])null);
        }
        if (source == this.frButton) {
            this.relax.setSelected(false);
            this.getPanel().energy();
        }
        if (source == this.extButton) {
            this.getPanel().expandGraph();
        }
    }
    
    public String exportGraph() {
        return this.getPanel().saveEvent();
    }
    
    public String exportImage(final String s) {
        return this.getPanel().getImage(s);
    }
    
    public void handleEdgeEvent(final int n, final boolean b) {
        this.getPanel().setEdgeDisplay(n - 1, b);
        this.getPanel().repaint();
    }
    
    public void modifyThreshold(final int threshold) {
        this.getPanel().setThreshold(threshold);
        this.getPanel().repaint();
    }
    
    public void displayGeneSubset(final String somevalue) {
        final Graph graph = this.getPanel().graph;
        final String[] split = somevalue.split(",");
        final ArrayList<Node> list = new ArrayList<Node>();
        for (int i = 0; i < split.length; ++i) {
            final Node node = graph.getNode(split[i]);
            if (node != null) {
                list.add(node);
            }
        }
        final Iterator<Node> nodesIterator = graph.nodesIterator();
        while (nodesIterator.hasNext()) {
            final Node node2 = nodesIterator.next();
            if (list.size() > 0 && !list.contains(node2)) {
                node2.setDisplay(false);
            }
            else {
                node2.setDisplay(true);
            }
        }
        final Iterator<Edge> edgesIterator = graph.edgesIterator();
        while (edgesIterator.hasNext()) {
            final Edge edge = edgesIterator.next();
            final String[] split2 = edge.toString().split("---");
            final Node node3 = graph.getNode(split2[0]);
            final Node node4 = graph.getNode(split2[1]);
            if (list.size() > 0 && (!list.contains(node3) || !list.contains(node4))) {
                edge.setDisplay(false);
            }
            else {
                edge.setDisplay(true);
            }
        }
        this.somevalue = somevalue;
        this.getPanel().repaint();
    }
    
    public void itemStateChanged(final ItemEvent itemEvent) {
        final Object source = itemEvent.getSource();
        if (source == this.relax) {
            if (this.relax.isSelected()) {
                this.getPanel().start();
            }
            else {
                this.getPanel().stop();
            }
        }
        if (source == this.pretty) {
            this.getPanel().setPretty(this.pretty.isSelected());
            this.getPanel().repaint();
        }
        if (source == this.names) {
            this.getPanel().setLabel(this.names.isSelected());
            this.getPanel().repaint();
        }
        if (source == this.directed) {
            this.getPanel().setArrows(this.directed.isSelected());
            this.getPanel().repaint();
        }
    }
    
    public void setPanel(final ExtendedPanel panel) {
        this.panel = panel;
    }
    
    public ExtendedPanel getPanel() {
        return this.panel;
    }
}
