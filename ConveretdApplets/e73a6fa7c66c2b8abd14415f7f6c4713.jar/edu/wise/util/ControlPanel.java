// 
// Decompiled by Procyon v0.5.30
// 

package edu.wise.util;

import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.Color;
import java.awt.event.MouseMotionListener;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.LayoutManager;
import edu.wise.graph.StyleSheet;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Label;
import java.awt.TextField;
import java.awt.Button;
import edu.wise.stats.distributions.Distribution;
import java.awt.Panel;

public class ControlPanel extends Panel
{
    protected static Distribution noiseDist;
    protected static Distribution signalDist;
    protected int fieldWidth;
    protected Button setButton;
    protected Button helpButton;
    protected TextField G1MeanTf;
    protected TextField G2MeanTf;
    protected TextField G1SdTf;
    protected TextField G2SdTf;
    protected TextField G1NTf;
    protected TextField G2NTf;
    protected TextField U11Tf;
    protected TextField U12Tf;
    protected TextField U21Tf;
    protected TextField U22Tf;
    protected Label MLabel;
    protected Label SLabel;
    protected Label NLabel;
    protected Label G1Label;
    protected Label G2Label;
    protected Label ULabel;
    protected Label G1Label2;
    protected Label G2Label2;
    String decRule1;
    String decRule2;
    String decRule3;
    protected Panel decPanel;
    protected GridBagLayout gbl;
    protected GridBagConstraints gbc;
    protected UtilApplet utilApplet;
    
    public ControlPanel() {
        this.fieldWidth = 5;
        this.setButton = new Button("Set Values");
        this.helpButton = new Button("Help");
        this.G1MeanTf = new TextField("", this.fieldWidth);
        this.G2MeanTf = new TextField("", this.fieldWidth);
        this.G1SdTf = new TextField("", this.fieldWidth);
        this.G2SdTf = new TextField("", this.fieldWidth);
        this.G1NTf = new TextField("", this.fieldWidth);
        this.G2NTf = new TextField("", this.fieldWidth);
        this.U11Tf = new TextField("", this.fieldWidth);
        this.U12Tf = new TextField("", this.fieldWidth);
        this.U21Tf = new TextField("", this.fieldWidth);
        this.U22Tf = new TextField("", this.fieldWidth);
        this.MLabel = new Label("Mean");
        this.SLabel = new Label("SD");
        this.NLabel = new Label("Base Rate");
        this.G1Label = new Label("Actual Group 1");
        this.G2Label = new Label("Actual Group 2");
        this.ULabel = new Label("Utility of classifying as");
        this.G1Label2 = new Label("Group 1");
        this.G2Label2 = new Label("Group 2");
        this.decRule1 = "";
        this.decRule2 = "";
        this.decRule3 = "";
        this.decPanel = new Panel();
        this.setBackground(StyleSheet.ENABLED_BACKGROUND);
        this.gbl = new GridBagLayout();
        this.gbc = new GridBagConstraints();
        this.setLayout(this.gbl);
    }
    
    public void update() {
    }
    
    protected void initComponents() throws Exception {
    }
    
    public void paint(final Graphics graphics) {
        graphics.drawRect(0, 0, this.getSize().width - 1, this.getSize().height - 1);
    }
    
    public class InnerPanel extends Panel
    {
        protected TextField tf;
        protected Label lab;
        public String description;
        
        InnerPanel(final ControlPanel controlPanel, final Label label, final TextField textField, final boolean editable, final String description) {
            this(controlPanel, label, textField);
            this.description = description;
            textField.setEditable(editable);
        }
        
        InnerPanel(final Label lab, final TextField tf) {
            this.description = "";
            this.lab = lab;
            this.tf = tf;
            this.setBackground(StyleSheet.ENABLED_BACKGROUND);
            this.lab.setFont(StyleSheet.LABEL_FONT);
            this.tf.setFont(StyleSheet.REGULAR_FONT);
            this.add(this.lab);
            this.add(this.tf);
            this.addMouseMotionListener(new MouseMotionControls());
        }
        
        public void setColors(final Color background, final Color foreground, final Color background2, final Color foreground2, final Color background3, final Color foreground3) {
            try {
                this.setBackground(background);
                this.setForeground(foreground);
                this.lab.setBackground(background2);
                this.lab.setForeground(foreground2);
                this.tf.setBackground(background3);
                this.tf.setForeground(foreground3);
            }
            catch (Exception ex) {}
        }
        
        public void setText(final String description) {
            this.description = description;
        }
        
        public void paint(final Graphics graphics) {
            graphics.setColor(Color.lightGray);
            graphics.drawRect(0, 0, this.getSize().width - 1, this.getSize().height - 1);
        }
        
        class MouseMotionControls extends MouseMotionAdapter implements MouseMotionListener
        {
            public void mouseMoved(final MouseEvent mouseEvent) {
            }
        }
    }
}
