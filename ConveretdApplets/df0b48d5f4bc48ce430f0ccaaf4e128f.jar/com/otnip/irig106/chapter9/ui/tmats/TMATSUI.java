// 
// Decompiled by Procyon v0.5.30
// 

package com.otnip.irig106.chapter9.ui.tmats;

import java.awt.Color;
import java.awt.BorderLayout;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.Dimension;
import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import java.awt.LayoutManager;
import java.awt.FlowLayout;
import java.awt.Component;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.JTabbedPane;
import com.otnip.irig106.chapter9.TMATS;
import javax.swing.JPanel;

public class TMATSUI extends JPanel
{
    private static final long serialVersionUID = 0L;
    private TMATS tmats;
    private TMATSTextUI textUI;
    private TMATSTreeUI treeUI;
    private JTabbedPane jTabbedPane1;
    private JPanel mainPanel;
    private JPanel textUIPanel;
    private JPanel treeUIPanel;
    private JComboBox viewOptionsComboBox;
    private JLabel viewOptionsLabel;
    private JPanel viewOptionsPanel;
    
    public TMATSUI(final TMATS tmats) {
        this.initComponents();
        this.tmats = tmats;
        try {
            this.textUI = new TMATSTextUI(tmats);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        this.treeUI = new TMATSTreeUI(tmats);
        this.treeUIPanel.add(this.treeUI);
        this.textUIPanel.add(this.textUI);
    }
    
    private void initComponents() {
        this.viewOptionsPanel = new JPanel();
        this.viewOptionsLabel = new JLabel();
        this.viewOptionsComboBox = new JComboBox();
        this.mainPanel = new JPanel();
        this.jTabbedPane1 = new JTabbedPane();
        this.textUIPanel = new JPanel();
        this.treeUIPanel = new JPanel();
        this.viewOptionsPanel.setLayout(new FlowLayout(2));
        this.viewOptionsLabel.setIcon(new ImageIcon(this.getClass().getResource("/com/otnip/media/IconExperience/icons/24/shadow/eyeglasses.png")));
        this.viewOptionsLabel.setText("View");
        this.viewOptionsPanel.add(this.viewOptionsLabel);
        this.viewOptionsComboBox.setModel(new DefaultComboBoxModel<String>(new String[] { "Raw Text", "Tree" }));
        this.viewOptionsComboBox.setPreferredSize(new Dimension(100, 23));
        this.viewOptionsComboBox.addItemListener(new ItemListener() {
            public void itemStateChanged(final ItemEvent evt) {
                TMATSUI.this.viewOptionsComboBoxItemStateChanged(evt);
            }
        });
        this.viewOptionsPanel.add(this.viewOptionsComboBox);
        this.mainPanel.setLayout(new BorderLayout());
        this.setBackground(new Color(128, 128, 128));
        this.setLayout(new BorderLayout());
        this.textUIPanel.setLayout(new BorderLayout());
        this.jTabbedPane1.addTab("Formatted Text View", new ImageIcon(this.getClass().getResource("/com/otnip/media/IconExperience/icons/24/shadow/text_code_colored.png")), this.textUIPanel);
        this.treeUIPanel.setLayout(new BorderLayout());
        this.jTabbedPane1.addTab("Tree View", new ImageIcon(this.getClass().getResource("/com/otnip/media/IconExperience/icons/24/shadow/text_tree.png")), this.treeUIPanel);
        this.add(this.jTabbedPane1, "Center");
    }
    
    private void viewOptionsComboBoxItemStateChanged(final ItemEvent evt) {
        try {
            final String item = (String)this.viewOptionsComboBox.getSelectedItem();
            if (item != null) {
                this.mainPanel.removeAll();
                if (item.equals("Raw Text")) {
                    this.mainPanel.add(this.textUI);
                }
                else if (item.equals("Tree")) {
                    this.mainPanel.add(this.treeUI);
                }
                this.mainPanel.validate();
                this.mainPanel.repaint();
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}
