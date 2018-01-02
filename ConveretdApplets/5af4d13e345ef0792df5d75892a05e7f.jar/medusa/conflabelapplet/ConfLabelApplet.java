// 
// Decompiled by Procyon v0.5.30
// 

package medusa.conflabelapplet;

import java.awt.event.ItemEvent;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemListener;
import java.awt.Component;
import java.awt.LayoutManager;
import java.awt.FlowLayout;
import medusa.display.BasicGraphPanel;
import medusa.MedusaSettings;
import java.awt.Color;
import javax.swing.JScrollPane;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import medusa.applet.MedusaLite;

public final class ConfLabelApplet extends MedusaLite
{
    JCheckBox relax;
    JCheckBox names;
    JButton frButton;
    JScrollPane jScrollPane;
    final Color christian;
    
    public ConfLabelApplet() {
        this.relax = new JCheckBox("Relax", true);
        this.names = new JCheckBox("Labels", true);
        this.frButton = new JButton("Layout");
        this.christian = new Color(230, 226, 230);
    }
    
    @Override
    public void initPanel() {
        System.out.println("Initializing panel");
        final MedusaSettings medusaSettings = new MedusaSettings(this.getParameter("settings"));
        final String parameter = this.getParameter("linkStart");
        final String parameter2 = this.getParameter("linkEnd");
        System.out.println(parameter);
        this.setPanel(new ConfLabelAppletPanel(medusaSettings, this, parameter, parameter2));
        System.out.println(this.getPanel());
    }
    
    @Override
    public void populateControlPanel() {
        this.controlPanel.setLayout(new FlowLayout());
        this.controlPanel.setBackground(this.christian);
        this.relax.setBackground(this.christian);
        this.names.setBackground(this.christian);
        this.frButton.setBackground(this.christian);
        this.controlPanel.add(this.relax);
        this.relax.addItemListener(this);
        this.controlPanel.add(this.frButton);
        this.frButton.addActionListener(this);
    }
    
    @Override
    public void actionPerformed(final ActionEvent actionEvent) {
        if (actionEvent.getSource() == this.frButton) {
            this.relax.setSelected(false);
            this.panel.energy();
        }
    }
    
    @Override
    public void itemStateChanged(final ItemEvent itemEvent) {
        if (itemEvent.getSource() == this.relax) {
            if (this.relax.isSelected()) {
                this.panel.start();
            }
            else {
                this.panel.stop();
            }
        }
    }
}
