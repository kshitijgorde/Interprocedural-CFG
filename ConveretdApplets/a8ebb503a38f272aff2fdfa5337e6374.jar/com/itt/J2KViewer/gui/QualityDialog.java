// 
// Decompiled by Procyon v0.5.30
// 

package com.itt.J2KViewer.gui;

import javax.swing.event.ChangeEvent;
import com.itt.J2KViewer.controller.PropertyManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.FlowLayout;
import java.awt.Component;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import java.awt.Insets;
import java.awt.LayoutManager;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.beans.PropertyVetoException;
import java.awt.Frame;
import javax.swing.JSlider;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JPanel;
import com.itt.J2KViewer.controller.ViewCentral;
import com.itt.J2KViewer.util.Log;
import javax.swing.event.ChangeListener;
import javax.swing.JDialog;

public class QualityDialog extends JDialog implements ChangeListener
{
    private static final long serialVersionUID = 1L;
    private static Log log;
    private ViewCentral viewCentral;
    private JPanel slidePanel;
    private JPanel btnPanel;
    private JButton cancelBtn;
    private JLabel lblType;
    private JButton okBtn;
    private JSlider qualityLayers;
    private JSlider overviewQualityLayers;
    private int numberOfQualityLayers;
    private int chosenLayer;
    private int chosenLayerOverview;
    static /* synthetic */ Class class$com$itt$J2KViewer$gui$BandsDialog;
    
    public QualityDialog(final Frame frame, final boolean b, final ViewCentral viewCentral) {
        super(frame, b);
        this.viewCentral = null;
        this.viewCentral = viewCentral;
        this.initComponents();
        this.getRootPane().setDefaultButton(this.okBtn);
    }
    
    private boolean saveQualityLevel() {
        int qualityLayers = this.viewCentral.getPropertyManager().getQualityLayers();
        int overviewQualityLayers = this.viewCentral.getPropertyManager().getOverviewQualityLayers();
        try {
            if (this.qualityLayers != null) {
                qualityLayers = this.chosenLayer;
                overviewQualityLayers = this.chosenLayerOverview;
            }
            this.viewCentral.getPropertyManager().setOverviewQualityLayers(overviewQualityLayers);
            this.viewCentral.getPropertyManager().setQualityLayers(qualityLayers);
        }
        catch (PropertyVetoException ex) {
            QualityDialog.log.error("Error setting quality layer.", ex);
            return false;
        }
        return true;
    }
    
    private void initComponents() {
        final PropertyManager propertyManager = this.viewCentral.getPropertyManager();
        this.numberOfQualityLayers = propertyManager.getTotalQualityLayers();
        this.chosenLayer = propertyManager.getQualityLayers();
        this.chosenLayerOverview = propertyManager.getOverviewQualityLayers();
        final GridBagConstraints gridBagConstraints = new GridBagConstraints();
        this.setDefaultCloseOperation(2);
        this.setTitle("Quality Layer");
        (this.slidePanel = new JPanel()).setLayout(new GridBagLayout());
        this.slidePanel.setBorder(new EmptyBorder(new Insets(3, 10, 10, 10)));
        (this.lblType = new JLabel()).setText("<html>Set the maximum number of quality layers <br>to display for this image.</html> ");
        final JLabel label = new JLabel("Main Image");
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        this.slidePanel.add(this.lblType, gridBagConstraints);
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = 17;
        gridBagConstraints.insets = new Insets(5, 5, 0, 5);
        this.slidePanel.add(label, gridBagConstraints);
        final int numberOfQualityLayers = this.numberOfQualityLayers;
        final boolean b = true;
        (this.qualityLayers = new JSlider(0, b ? 1 : 0, numberOfQualityLayers, this.chosenLayer)).setToolTipText("Select the maximum quality layer... Selecting a smaller value results in less use of bandwidth.");
        this.qualityLayers.addChangeListener(this);
        this.qualityLayers.setMajorTickSpacing(2);
        this.qualityLayers.setMinorTickSpacing(1);
        this.qualityLayers.setPaintTicks(true);
        this.qualityLayers.setPaintLabels(true);
        this.qualityLayers.setSnapToTicks(true);
        this.qualityLayers.setPaintTrack(true);
        (this.overviewQualityLayers = new JSlider(0, b ? 1 : 0, numberOfQualityLayers, this.chosenLayerOverview)).setToolTipText("Select the maximum quality layer... Selecting a smaller value results in less use of bandwidth.");
        this.overviewQualityLayers.addChangeListener(this);
        this.overviewQualityLayers.setMajorTickSpacing(2);
        this.overviewQualityLayers.setMinorTickSpacing(1);
        this.overviewQualityLayers.setPaintTicks(true);
        this.overviewQualityLayers.setPaintLabels(true);
        this.overviewQualityLayers.setSnapToTicks(true);
        this.overviewQualityLayers.setPaintTrack(true);
        gridBagConstraints.gridwidth = 0;
        gridBagConstraints.anchor = 17;
        final JLabel label2 = new JLabel("Overview Image");
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        this.slidePanel.add(this.qualityLayers, gridBagConstraints);
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        this.slidePanel.add(label2, gridBagConstraints);
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 4;
        this.slidePanel.add(this.overviewQualityLayers, gridBagConstraints);
        this.getContentPane().add(this.slidePanel, "Center");
        (this.btnPanel = new JPanel()).setLayout(new FlowLayout(2));
        this.btnPanel.setBorder(new EmptyBorder(new Insets(0, 3, 3, 0)));
        (this.okBtn = new JButton()).setMnemonic('O');
        this.okBtn.setText("OK");
        this.okBtn.setToolTipText("Close dialog and save changes");
        this.okBtn.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent actionEvent) {
                QualityDialog.this.okBtnActionPerformed(actionEvent);
            }
        });
        this.btnPanel.add(this.okBtn);
        (this.cancelBtn = new JButton()).setMnemonic('C');
        this.cancelBtn.setText("Cancel");
        this.cancelBtn.setToolTipText("Close dialog and cancel changes");
        this.cancelBtn.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent actionEvent) {
                QualityDialog.this.cancelBtnActionPerformed(actionEvent);
            }
        });
        this.btnPanel.add(this.cancelBtn);
        this.getContentPane().add(this.btnPanel, "South");
        this.qualityLayers.setVisible(true);
        this.pack();
    }
    
    public void stateChanged(final ChangeEvent changeEvent) {
        final JSlider slider = (JSlider)changeEvent.getSource();
        if (slider == this.qualityLayers) {
            this.chosenLayer = slider.getValue();
        }
        if (slider == this.overviewQualityLayers) {
            this.chosenLayerOverview = slider.getValue();
        }
    }
    
    private void cancelBtnActionPerformed(final ActionEvent actionEvent) {
        this.closeDialog(false);
    }
    
    private void okBtnActionPerformed(final ActionEvent actionEvent) {
        this.closeDialog(true);
    }
    
    private void closeDialog(final boolean b) {
        if (b && !this.saveQualityLevel()) {
            QualityDialog.log.info("Save failed. Returning to dialog.");
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
        QualityDialog.log = new Log((QualityDialog.class$com$itt$J2KViewer$gui$BandsDialog == null) ? (QualityDialog.class$com$itt$J2KViewer$gui$BandsDialog = class$("com.itt.J2KViewer.gui.BandsDialog")) : QualityDialog.class$com$itt$J2KViewer$gui$BandsDialog);
    }
}
