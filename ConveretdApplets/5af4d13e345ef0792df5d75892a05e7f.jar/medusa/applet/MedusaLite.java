// 
// Decompiled by Procyon v0.5.30
// 

package medusa.applet;

import java.awt.Graphics;
import java.awt.event.ItemEvent;
import java.awt.event.ActionEvent;
import java.awt.FlowLayout;
import medusa.graph.Graph;
import java.awt.Container;
import java.awt.Component;
import medusa.dataio.DataLoader;
import java.awt.LayoutManager;
import java.awt.BorderLayout;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import medusa.MedusaSettings;
import medusa.display.BasicGraphPanel;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ItemListener;
import javax.swing.JApplet;

public class MedusaLite extends JApplet implements ItemListener, ActionListener
{
    final Color babyBlue;
    public BasicGraphPanel panel;
    private MedusaSettings stringSettings;
    private JScrollPane jScrollPane;
    public JPanel controlPanel;
    private int xParam;
    private int yParam;
    JCheckBox relax;
    JCheckBox pretty;
    JCheckBox names;
    JButton frButton;
    JButton imageButton;
    
    public MedusaLite() {
        this.babyBlue = new Color(230, 226, 230);
        this.controlPanel = new JPanel();
        this.relax = new JCheckBox("Relax", false);
        this.pretty = new JCheckBox("Edge colours", true);
        this.names = new JCheckBox("Labels", true);
        this.frButton = new JButton("Layout");
        this.imageButton = new JButton("Export");
    }
    
    public void initPanel() {
        System.out.println("Initializing MedusaAppletPanel");
        this.setLocalStringSettings(new MedusaSettings(this.getParameter("settings")));
        final String parameter = this.getParameter("linkStart");
        final String parameter2 = this.getParameter("linkEnd");
        System.out.println(parameter);
        final MedusaAppletPanel medusaAppletPanel = new MedusaAppletPanel(this.getLocalStringSettings(), this, parameter, parameter2);
        this.setPanel(new MedusaAppletPanel(this.getLocalStringSettings(), this, parameter, parameter2));
        this.getPanel().stop();
    }
    
    public void init() {
        this.initPanel();
        final Container contentPane = this.getContentPane();
        contentPane.setLayout(new BorderLayout());
        final Graph parameters = new DataLoader().readParameters(this.getParameter("edges"), this.getParameter("nodes"));
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
        contentPane.add("South", this.controlPanel);
        System.out.println("Setting visible");
        this.setVisible(true);
        this.getJScrollPane().validate();
        final String parameter = this.getParameter("layout");
        if (parameter != null && parameter.compareTo("true") == 0) {
            this.getPanel().energy();
        }
    }
    
    public void setPrefs() {
        this.getPanel().setPretty(true);
        this.getPanel().setArrows(false);
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
        this.frButton.setBackground(this.babyBlue);
        this.controlPanel.add(this.relax);
        this.relax.addItemListener(this);
        this.controlPanel.add(this.pretty);
        this.pretty.addItemListener(this);
        this.controlPanel.add(this.names);
        this.names.addItemListener(this);
        this.controlPanel.add(this.frButton);
        this.frButton.addActionListener(this);
    }
    
    public void destroy() {
        this.remove(this.panel);
    }
    
    public void start() {
    }
    
    public void stop() {
        this.panel.stop();
    }
    
    public String getAppletInfo() {
        return "Title: MedusaLite \nAuthor: Sean Hooper";
    }
    
    public void actionPerformed(final ActionEvent actionEvent) {
        if (actionEvent.getSource() == this.frButton) {
            this.relax.setSelected(false);
            this.getPanel().energy();
        }
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
    }
    
    public void paint(final Graphics graphics) {
        super.paint(graphics);
    }
    
    public void setLocalStringSettings(final MedusaSettings stringSettings) {
        this.stringSettings = stringSettings;
    }
    
    public void setPanel(final BasicGraphPanel panel) {
        this.panel = panel;
    }
    
    public BasicGraphPanel getPanel() {
        return this.panel;
    }
    
    public int getXParam() {
        return this.xParam;
    }
    
    public void setXParam(final int xParam) {
        this.xParam = xParam;
    }
    
    public int getYParam() {
        return this.yParam;
    }
    
    public void setYParam(final int yParam) {
        this.yParam = yParam;
    }
    
    public MedusaSettings getLocalStringSettings() {
        return this.stringSettings;
    }
    
    public JScrollPane getJScrollPane() {
        return this.jScrollPane;
    }
    
    public void setJScrollPane(final JScrollPane jScrollPane) {
        this.jScrollPane = jScrollPane;
    }
}
