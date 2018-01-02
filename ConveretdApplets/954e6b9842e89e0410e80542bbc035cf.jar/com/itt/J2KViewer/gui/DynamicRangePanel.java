// 
// Decompiled by Procyon v0.5.30
// 

package com.itt.J2KViewer.gui;

import com.itt.J2KViewer.imagetools.DRAManager;
import java.beans.PropertyChangeEvent;
import java.awt.Component;
import java.awt.Insets;
import java.awt.GridBagConstraints;
import java.awt.LayoutManager;
import java.awt.GridBagLayout;
import javax.swing.JLabel;
import com.itt.J2KViewer.controller.PropertyManager;
import com.itt.J2KViewer.controller.ViewCentral;
import java.beans.PropertyChangeListener;
import javax.swing.JPanel;

public class DynamicRangePanel extends JPanel implements PropertyChangeListener
{
    private static final long serialVersionUID = 1L;
    private ViewCentral viewCentral;
    private PropertyManager propertyManager;
    private JLabel redLabel;
    private JLabel greenLabel;
    private JLabel blueLabel;
    private JLabel stretchMinLabel;
    private JLabel stretchMaxLabel;
    private JLabel rangeLabel;
    private JLabel redStretchMin;
    private JLabel greenStretchMin;
    private JLabel blueStretchMin;
    private JLabel redStretchMax;
    private JLabel greenStretchMax;
    private JLabel blueStretchMax;
    private JLabel redRangeLabel;
    private JLabel greenRangeLabel;
    private JLabel blueRangeLabel;
    private JPanel spacer;
    
    public DynamicRangePanel(final ViewCentral viewCentral) {
        this.viewCentral = null;
        this.propertyManager = null;
        this.viewCentral = viewCentral;
        this.propertyManager = viewCentral.getPropertyManager();
        viewCentral.eventController().addPropertyChangeListener(this);
        this.initPanel();
    }
    
    private void initPanel() {
        this.redLabel = new JLabel("Red:");
        this.greenLabel = new JLabel("Green:");
        this.blueLabel = new JLabel("Blue:");
        this.stretchMinLabel = new JLabel("Min");
        this.stretchMaxLabel = new JLabel("Max");
        this.rangeLabel = new JLabel("Range");
        this.redRangeLabel = new JLabel();
        this.greenRangeLabel = new JLabel();
        this.blueRangeLabel = new JLabel();
        this.redStretchMin = new JLabel();
        this.greenStretchMin = new JLabel();
        this.blueStretchMin = new JLabel();
        this.redStretchMax = new JLabel();
        this.greenStretchMax = new JLabel();
        this.blueStretchMax = new JLabel();
        this.redRangeLabel = new JLabel();
        this.greenRangeLabel = new JLabel();
        this.blueRangeLabel = new JLabel();
        this.spacer = new JPanel();
        this.setLayout(new GridBagLayout());
        final GridBagConstraints gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.weightx = 0.0;
        gridBagConstraints.anchor = 13;
        gridBagConstraints.fill = 0;
        gridBagConstraints.insets = new Insets(0, 2, 0, 2);
        final GridBagConstraints gridBagConstraints2 = new GridBagConstraints();
        gridBagConstraints2.weightx = 0.0;
        gridBagConstraints2.anchor = 17;
        gridBagConstraints2.insets = new Insets(0, 2, 0, 2);
        final GridBagConstraints gridBagConstraints3 = new GridBagConstraints();
        gridBagConstraints3.weightx = 0.0;
        gridBagConstraints3.anchor = 17;
        gridBagConstraints3.gridwidth = 0;
        gridBagConstraints3.insets = new Insets(0, 2, 0, 2);
        final GridBagConstraints gridBagConstraints4 = new GridBagConstraints();
        gridBagConstraints4.gridx = 0;
        gridBagConstraints4.weightx = 1.0;
        gridBagConstraints4.anchor = 17;
        gridBagConstraints4.gridwidth = 0;
        gridBagConstraints4.fill = 1;
        int gridy = 0;
        gridBagConstraints2.gridy = gridy;
        gridBagConstraints2.gridx = 1;
        this.add(this.stretchMinLabel, gridBagConstraints2);
        gridBagConstraints2.gridx = 2;
        this.add(this.stretchMaxLabel, gridBagConstraints2);
        gridBagConstraints2.gridx = 3;
        this.add(this.rangeLabel, gridBagConstraints2);
        ++gridy;
        gridBagConstraints.gridy = gridy;
        gridBagConstraints.gridx = 0;
        this.add(this.redLabel, gridBagConstraints);
        gridBagConstraints2.gridy = gridy;
        gridBagConstraints2.gridx = 1;
        this.add(this.redStretchMin, gridBagConstraints2);
        gridBagConstraints2.gridx = 2;
        this.add(this.redStretchMax, gridBagConstraints2);
        gridBagConstraints2.gridy = gridy;
        gridBagConstraints2.gridx = 3;
        this.add(this.redRangeLabel, gridBagConstraints2);
        ++gridy;
        gridBagConstraints.gridy = gridy;
        gridBagConstraints.gridx = 0;
        this.add(this.greenLabel, gridBagConstraints);
        gridBagConstraints2.gridy = gridy;
        gridBagConstraints2.gridx = 1;
        this.add(this.greenStretchMin, gridBagConstraints2);
        gridBagConstraints2.gridx = 2;
        this.add(this.greenStretchMax, gridBagConstraints2);
        gridBagConstraints2.gridy = gridy;
        gridBagConstraints2.gridx = 3;
        this.add(this.greenRangeLabel, gridBagConstraints2);
        ++gridy;
        gridBagConstraints.gridy = gridy;
        gridBagConstraints.gridx = 0;
        this.add(this.blueLabel, gridBagConstraints);
        gridBagConstraints2.gridy = gridy;
        gridBagConstraints2.gridx = 1;
        this.add(this.blueStretchMin, gridBagConstraints2);
        gridBagConstraints2.gridx = 2;
        this.add(this.blueStretchMax, gridBagConstraints2);
        gridBagConstraints2.gridy = gridy;
        gridBagConstraints2.gridx = 3;
        this.add(this.blueRangeLabel, gridBagConstraints2);
        ++gridy;
        gridBagConstraints4.gridy = gridy;
        this.add(this.spacer, gridBagConstraints4);
        this.reset();
    }
    
    private void reset() {
        this.redStretchMin.setText("");
        this.greenStretchMin.setText("");
        this.blueStretchMin.setText("");
        this.redStretchMax.setText("");
        this.greenStretchMax.setText("");
        this.blueStretchMax.setText("");
        this.redRangeLabel.setText("");
        this.greenRangeLabel.setText("");
        this.blueRangeLabel.setText("");
    }
    
    public void propertyChange(final PropertyChangeEvent propertyChangeEvent) {
        final String propertyName = propertyChangeEvent.getPropertyName();
        if (propertyName.equals("Open")) {
            if (this.propertyManager.isOpen()) {
                this.viewCentral.getDRAManager().init();
                this.setDRAValues();
                this.showRGB(this.propertyManager.isShowRGB());
            }
            else {
                this.reset();
            }
        }
        if (propertyName.equals("NewDataRange")) {
            if (this.propertyManager.isOpen()) {
                this.setDRAValues();
            }
        }
        else if (propertyName.equals("NewDRA")) {
            if (this.propertyManager.isOpen()) {
                this.setDRAValues();
            }
        }
        else if (propertyName.equals("BytesTransferred")) {
            if (this.propertyManager.isOpen()) {
                this.setDRAValues();
            }
        }
        else if (propertyName.equals("RGBMap") && this.propertyManager.isOpen()) {
            this.setDRAValues();
            this.showRGB(this.propertyManager.isShowRGB());
        }
    }
    
    private void setDRAValues() {
        final DRAManager draManager = this.viewCentral.getDRAManager();
        if (draManager != null) {
            this.redStretchMin.setText(String.valueOf(draManager.getStretchMinAsInt(0)));
            this.greenStretchMin.setText(String.valueOf(draManager.getStretchMinAsInt(1)));
            this.blueStretchMin.setText(String.valueOf(draManager.getStretchMinAsInt(2)));
            this.redStretchMax.setText(String.valueOf(draManager.getStretchMaxAsInt(0)));
            this.greenStretchMax.setText(String.valueOf(draManager.getStretchMaxAsInt(1)));
            this.blueStretchMax.setText(String.valueOf(draManager.getStretchMaxAsInt(2)));
            this.redRangeLabel.setText(this.getMinMaxText(0));
            this.greenRangeLabel.setText(this.getMinMaxText(1));
            this.blueRangeLabel.setText(this.getMinMaxText(2));
        }
    }
    
    private String getMinMaxText(final int n) {
        final int[] dataRange = this.viewCentral.getDRAManager().getDataRange(n);
        final String value = String.valueOf(dataRange[0]);
        final String value2 = String.valueOf(dataRange[1]);
        String s;
        if (value.length() + value2.length() < 8) {
            s = value + "/" + value2;
        }
        else {
            s = value + "/" + value2;
        }
        return s;
    }
    
    private void showRGB(final boolean b) {
        this.greenLabel.setVisible(b);
        this.greenStretchMin.setVisible(b);
        this.greenStretchMax.setVisible(b);
        this.greenRangeLabel.setVisible(b);
        this.blueLabel.setVisible(b);
        this.blueStretchMin.setVisible(b);
        this.blueStretchMax.setVisible(b);
        this.blueRangeLabel.setVisible(b);
        if (b) {
            this.redLabel.setText("Red:");
        }
        else {
            this.redLabel.setText("Band " + (this.propertyManager.getRGBMap()[0] + 1) + ":");
        }
    }
}
