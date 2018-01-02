// 
// Decompiled by Procyon v0.5.30
// 

package com.itt.J2KViewer.gui;

import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import javax.swing.AbstractButton;
import javax.swing.ButtonGroup;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import java.awt.Insets;
import java.awt.LayoutManager;
import java.awt.GridBagLayout;
import com.itt.J2KViewer.controller.PropertyManager;
import java.beans.PropertyVetoException;
import java.awt.Component;
import java.awt.Frame;
import javax.swing.JComboBox;
import javax.swing.JRadioButton;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JPanel;
import com.itt.J2KViewer.controller.ViewCentral;
import com.itt.J2KViewer.util.Log;
import javax.swing.JDialog;

public class BandsDialog extends JDialog
{
    private static final long serialVersionUID = 1L;
    private static Log log;
    private ViewCentral viewCentral;
    private JPanel typePanel;
    private JPanel bandPanel;
    private JPanel btnPanel;
    private JButton cancelBtn;
    private JLabel lblType;
    private JRadioButton typeGrayScale;
    private JRadioButton typeRGB;
    private JLabel lblRed;
    private JLabel lblGreen;
    private JLabel lblBlue;
    private JButton okBtn;
    private JComboBox redBand;
    private JComboBox greenBand;
    private JComboBox blueBand;
    private int numberOfBandsInImage;
    private int[] startingRgbMap;
    private int[] lastColorBands;
    private int lastGrayScaleBand;
    static /* synthetic */ Class class$com$itt$J2KViewer$gui$BandsDialog;
    
    public BandsDialog(final Frame frame, final boolean b, final ViewCentral viewCentral) {
        super(frame, b);
        this.viewCentral = null;
        this.viewCentral = viewCentral;
        this.initComponents();
        this.getRootPane().setDefaultButton(this.okBtn);
        this.loadBands();
    }
    
    private void loadBands() {
        if (this.redBand != null && this.greenBand != null && this.blueBand != null && this.startingRgbMap != null) {
            this.redBand.setSelectedIndex(this.startingRgbMap[0]);
            if (this.startingRgbMap.length == 3) {
                this.greenBand.setSelectedIndex(this.startingRgbMap[1]);
                this.blueBand.setSelectedIndex(this.startingRgbMap[2]);
            }
        }
    }
    
    private boolean saveBands() {
        final PropertyManager propertyManager = this.viewCentral.getPropertyManager();
        try {
            if (this.redBand != null && this.greenBand != null && this.blueBand != null) {
                boolean selected = false;
                if (this.typePanel.isVisible()) {
                    selected = this.typeRGB.isSelected();
                }
                int[] rgbMap;
                if (selected) {
                    rgbMap = new int[] { this.redBand.getSelectedIndex(), this.greenBand.getSelectedIndex(), this.blueBand.getSelectedIndex() };
                    if (rgbMap[0] == rgbMap[1] || rgbMap[0] == rgbMap[2] || rgbMap[1] == rgbMap[2]) {
                        this.viewCentral.reportError(this, "Band Selection Error", "Red, green, and blue band values must be unique.");
                        return false;
                    }
                    propertyManager.setLastColorBands(rgbMap.clone());
                }
                else {
                    rgbMap = new int[] { this.redBand.getSelectedIndex() };
                    propertyManager.setLastGrayScaleBand(rgbMap[0]);
                }
                propertyManager.setShowRGB(selected);
                propertyManager.setRGBMap(rgbMap);
            }
        }
        catch (PropertyVetoException ex) {
            BandsDialog.log.error("Error setting new band mapping.", ex);
            return false;
        }
        return true;
    }
    
    private void initComponents() {
        final PropertyManager propertyManager = this.viewCentral.getPropertyManager();
        this.numberOfBandsInImage = propertyManager.getNumberOfComponents();
        this.startingRgbMap = propertyManager.getRGBMap();
        this.lastColorBands = propertyManager.getLastColorBands();
        this.lastGrayScaleBand = propertyManager.getLastGrayScaleBand();
        final Integer[] array = new Integer[this.numberOfBandsInImage];
        for (int i = 0; i < this.numberOfBandsInImage; ++i) {
            array[i] = new Integer(i + 1);
        }
        this.setDefaultCloseOperation(2);
        this.setTitle("Bands");
        (this.typePanel = new JPanel()).setLayout(new GridBagLayout());
        this.typePanel.setBorder(new EmptyBorder(new Insets(10, 10, 10, 10)));
        (this.lblType = new JLabel()).setText("Display:");
        this.lblRed = new JLabel();
        this.lblGreen = new JLabel();
        this.lblBlue = new JLabel();
        (this.typeRGB = new JRadioButton("RGB Color")).setToolTipText("Display image in RGB color");
        this.typeRGB.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent actionEvent) {
                BandsDialog.this.showRGB(true);
                BandsDialog.this.lastGrayScaleBand = BandsDialog.this.redBand.getSelectedIndex();
                BandsDialog.this.redBand.setSelectedIndex(BandsDialog.this.lastColorBands[0]);
                BandsDialog.this.greenBand.setSelectedIndex(BandsDialog.this.lastColorBands[1]);
                BandsDialog.this.blueBand.setSelectedIndex(BandsDialog.this.lastColorBands[2]);
            }
        });
        (this.typeGrayScale = new JRadioButton("Gray Scale")).setToolTipText("Display image as a gray scale");
        this.typeGrayScale.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent actionEvent) {
                BandsDialog.this.showRGB(false);
                BandsDialog.this.lastColorBands[0] = BandsDialog.this.redBand.getSelectedIndex();
                BandsDialog.this.lastColorBands[1] = BandsDialog.this.greenBand.getSelectedIndex();
                BandsDialog.this.lastColorBands[2] = BandsDialog.this.blueBand.getSelectedIndex();
                BandsDialog.this.redBand.setSelectedIndex(BandsDialog.this.lastGrayScaleBand);
                BandsDialog.this.greenBand.setSelectedIndex(BandsDialog.this.lastGrayScaleBand);
                BandsDialog.this.blueBand.setSelectedIndex(BandsDialog.this.lastGrayScaleBand);
            }
        });
        final ButtonGroup buttonGroup = new ButtonGroup();
        buttonGroup.add(this.typeGrayScale);
        buttonGroup.add(this.typeRGB);
        this.typePanel.add(this.lblType, 0);
        this.typePanel.add(this.typeRGB, 1);
        this.typePanel.add(this.typeGrayScale, 2);
        this.getContentPane().add(this.typePanel, "North");
        (this.bandPanel = new JPanel()).setLayout(new GridBagLayout());
        this.bandPanel.setBorder(new EmptyBorder(new Insets(10, 10, 10, 10)));
        this.redBand = new JComboBox(array);
        (this.greenBand = new JComboBox(array)).setToolTipText("Select the band to be used as the green component");
        (this.blueBand = new JComboBox(array)).setToolTipText("Select the band to be used as the blue component");
        final GridBagConstraints gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.anchor = 13;
        gridBagConstraints.insets = new Insets(5, 0, 5, 5);
        this.bandPanel.add(this.lblRed, gridBagConstraints);
        final GridBagConstraints gridBagConstraints2 = new GridBagConstraints();
        gridBagConstraints2.gridwidth = 0;
        gridBagConstraints2.anchor = 17;
        gridBagConstraints2.insets = new Insets(5, 0, 5, 5);
        this.bandPanel.add(this.redBand, gridBagConstraints2);
        this.lblGreen.setText("Green Band:");
        final GridBagConstraints gridBagConstraints3 = new GridBagConstraints();
        gridBagConstraints3.anchor = 13;
        gridBagConstraints3.insets = new Insets(5, 0, 5, 5);
        this.bandPanel.add(this.lblGreen, gridBagConstraints3);
        final GridBagConstraints gridBagConstraints4 = new GridBagConstraints();
        gridBagConstraints4.gridwidth = 0;
        gridBagConstraints4.anchor = 17;
        gridBagConstraints4.insets = new Insets(5, 0, 5, 5);
        this.bandPanel.add(this.greenBand, gridBagConstraints4);
        this.lblBlue.setText("Blue Band:");
        final GridBagConstraints gridBagConstraints5 = new GridBagConstraints();
        gridBagConstraints5.anchor = 13;
        gridBagConstraints5.insets = new Insets(5, 0, 5, 5);
        this.bandPanel.add(this.lblBlue, gridBagConstraints5);
        final GridBagConstraints gridBagConstraints6 = new GridBagConstraints();
        gridBagConstraints6.gridwidth = 0;
        gridBagConstraints6.anchor = 17;
        gridBagConstraints6.insets = new Insets(5, 0, 5, 5);
        this.bandPanel.add(this.blueBand, gridBagConstraints6);
        this.getContentPane().add(this.bandPanel, "Center");
        (this.btnPanel = new JPanel()).setLayout(new FlowLayout(2));
        this.btnPanel.setBorder(new EmptyBorder(new Insets(3, 3, 3, 3)));
        (this.okBtn = new JButton()).setMnemonic('O');
        this.okBtn.setText("OK");
        this.okBtn.setToolTipText("Close dialog and save changes");
        this.okBtn.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent actionEvent) {
                BandsDialog.this.okBtnActionPerformed(actionEvent);
            }
        });
        this.btnPanel.add(this.okBtn);
        (this.cancelBtn = new JButton()).setMnemonic('C');
        this.cancelBtn.setText("Cancel");
        this.cancelBtn.setToolTipText("Close dialog and cancel changes");
        this.cancelBtn.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent actionEvent) {
                BandsDialog.this.cancelBtnActionPerformed(actionEvent);
            }
        });
        this.btnPanel.add(this.cancelBtn);
        this.getContentPane().add(this.btnPanel, "South");
        if (this.numberOfBandsInImage >= 3) {
            this.typePanel.setVisible(true);
            this.showRGB(propertyManager.isShowRGB());
        }
        else {
            this.typePanel.setVisible(false);
            this.showRGB(false);
        }
    }
    
    private void showRGB(final boolean b) {
        if (b) {
            this.typeRGB.setSelected(true);
            this.lblRed.setText("Red Band:");
            this.lblGreen.setVisible(true);
            this.lblBlue.setVisible(true);
            this.redBand.setToolTipText("Select the band to be used as the red component");
            this.greenBand.setVisible(true);
            this.blueBand.setVisible(true);
        }
        else {
            this.typeGrayScale.setSelected(true);
            this.lblRed.setText("Band:");
            this.lblGreen.setVisible(false);
            this.lblBlue.setVisible(false);
            this.redBand.setToolTipText("Select the band to be used as the gray scale component");
            this.greenBand.setVisible(false);
            this.blueBand.setVisible(false);
        }
        this.pack();
    }
    
    private void cancelBtnActionPerformed(final ActionEvent actionEvent) {
        this.closeDialog(false);
    }
    
    private void okBtnActionPerformed(final ActionEvent actionEvent) {
        this.closeDialog(true);
    }
    
    private void closeDialog(final boolean b) {
        if (b && !this.saveBands()) {
            BandsDialog.log.info("Save failed. Returning to dialog.");
            return;
        }
        this.dispose();
    }
    
    static /* synthetic */ Class class$(final String s) {
        try {
            return Class.forName(s);
        }
        catch (ClassNotFoundException ex) {
            throw new NoClassDefFoundError().initCause(ex);
        }
    }
    
    static {
        BandsDialog.log = new Log((BandsDialog.class$com$itt$J2KViewer$gui$BandsDialog == null) ? (BandsDialog.class$com$itt$J2KViewer$gui$BandsDialog = class$("com.itt.J2KViewer.gui.BandsDialog")) : BandsDialog.class$com$itt$J2KViewer$gui$BandsDialog);
    }
}
