// 
// Decompiled by Procyon v0.5.30
// 

package com.itt.J2KViewer.gui;

import com.itt.J2KViewer.georvm.NITFGeoUtils;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import java.awt.Insets;
import java.awt.LayoutManager;
import java.awt.GridBagLayout;
import java.beans.PropertyVetoException;
import com.itt.J2KViewer.controller.PropertyManager;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.ComponentEvent;
import java.awt.Frame;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import com.itt.J2KViewer.controller.ViewCentral;
import com.itt.J2KViewer.util.Log;
import java.awt.event.ComponentListener;
import javax.swing.JDialog;

public class AnnotationsDialog extends JDialog implements ComponentListener
{
    private static Log log;
    private ViewCentral viewCentral;
    private JPanel annotationsPanel;
    private JPanel btnPanel;
    private JLabel instructionsLabel;
    private JCheckBox northArrowCheckBox;
    private JCheckBox userLocationCheckBox;
    private JCheckBox waypointsCheckBox;
    private JButton okBtn;
    private JButton cancelBtn;
    static /* synthetic */ Class class$com$itt$J2KViewer$gui$AnnotationsDialog;
    
    public AnnotationsDialog(final Frame frame, final boolean b, final ViewCentral viewCentral) {
        super(frame, b);
        this.viewCentral = null;
        this.viewCentral = viewCentral;
        this.initComponents();
        this.getRootPane().setDefaultButton(this.okBtn);
        this.loadChoices();
        this.addComponentListener(this);
    }
    
    public void componentMoved(final ComponentEvent componentEvent) {
    }
    
    public void componentShown(final ComponentEvent componentEvent) {
    }
    
    public void componentHidden(final ComponentEvent componentEvent) {
    }
    
    public void componentResized(final ComponentEvent componentEvent) {
        final Component component = componentEvent.getComponent();
        final Dimension size = component.getSize();
        final Dimension minimumSize = component.getMinimumSize();
        final Dimension size2 = new Dimension(size);
        boolean b = false;
        if (size.width < minimumSize.width) {
            b = true;
            size2.width = minimumSize.width;
        }
        if (size.height < minimumSize.height) {
            b = true;
            size2.height = minimumSize.height;
        }
        if (b) {
            component.setSize(size2);
        }
    }
    
    public Dimension getMinimumSize() {
        final Dimension preferredSize;
        final Dimension dimension = preferredSize = this.getContentPane().getPreferredSize();
        preferredSize.height += 40;
        return dimension;
    }
    
    private void loadChoices() {
        final PropertyManager propertyManager = this.viewCentral.getPropertyManager();
        final boolean showNorthArrow = propertyManager.isShowNorthArrow();
        final boolean showCrosshair = propertyManager.isShowCrosshair();
        final boolean showWaypoints = propertyManager.isShowWaypoints();
        this.northArrowCheckBox.setSelected(showNorthArrow);
        this.userLocationCheckBox.setSelected(showCrosshair);
        this.waypointsCheckBox.setSelected(showWaypoints);
    }
    
    private boolean saveChoices() {
        final PropertyManager propertyManager = this.viewCentral.getPropertyManager();
        try {
            if (this.northArrowCheckBox.isEnabled()) {
                propertyManager.setShowNorthArrow(this.northArrowCheckBox.isSelected());
            }
            propertyManager.setShowCrosshair(this.userLocationCheckBox.isSelected());
            propertyManager.setShowWaypoints(this.waypointsCheckBox.isSelected());
        }
        catch (PropertyVetoException ex) {
            AnnotationsDialog.log.error("Error setting new annotation choices.", ex);
            return false;
        }
        return true;
    }
    
    private void initComponents() {
        this.setDefaultCloseOperation(2);
        this.setTitle("Annotations");
        (this.annotationsPanel = new JPanel()).setLayout(new GridBagLayout());
        this.annotationsPanel.setBorder(new EmptyBorder(new Insets(10, 10, 10, 10)));
        this.instructionsLabel = new JLabel("Select the annotations to be displayed:");
        (this.northArrowCheckBox = new JCheckBox("North Arrow")).setToolTipText("Display the north arrow");
        final NITFGeoUtils nitfGeoUtils = this.viewCentral.getNITFGeoUtils();
        if (nitfGeoUtils == null || !nitfGeoUtils.isReady()) {
            this.northArrowCheckBox.setEnabled(false);
        }
        (this.userLocationCheckBox = new JCheckBox("Geo-Jump Location")).setToolTipText("Display the last 'Geo-Jump' location marker");
        (this.waypointsCheckBox = new JCheckBox("Waypoints")).setToolTipText("Display the waypoint markers");
        final GridBagConstraints gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridwidth = 0;
        gridBagConstraints.anchor = 17;
        gridBagConstraints.insets = new Insets(5, 0, 5, 5);
        this.annotationsPanel.add(this.instructionsLabel, gridBagConstraints);
        final GridBagConstraints gridBagConstraints2 = new GridBagConstraints();
        gridBagConstraints2.gridwidth = 0;
        gridBagConstraints2.anchor = 17;
        gridBagConstraints2.insets = new Insets(5, 0, 5, 5);
        this.annotationsPanel.add(this.northArrowCheckBox, gridBagConstraints2);
        final GridBagConstraints gridBagConstraints3 = new GridBagConstraints();
        gridBagConstraints3.gridwidth = 0;
        gridBagConstraints3.anchor = 17;
        gridBagConstraints3.insets = new Insets(5, 0, 5, 5);
        this.annotationsPanel.add(this.userLocationCheckBox, gridBagConstraints3);
        final GridBagConstraints gridBagConstraints4 = new GridBagConstraints();
        gridBagConstraints4.gridwidth = 0;
        gridBagConstraints4.anchor = 17;
        gridBagConstraints4.insets = new Insets(5, 0, 5, 5);
        this.annotationsPanel.add(this.waypointsCheckBox, gridBagConstraints4);
        this.getContentPane().add(this.annotationsPanel, "North");
        (this.btnPanel = new JPanel()).setLayout(new FlowLayout(2));
        this.btnPanel.setBorder(new EmptyBorder(new Insets(3, 3, 3, 3)));
        (this.okBtn = new JButton()).setMnemonic('O');
        this.okBtn.setText("OK");
        this.okBtn.setToolTipText("Close dialog and save changes");
        this.okBtn.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent actionEvent) {
                AnnotationsDialog.this.okBtnActionPerformed(actionEvent);
            }
        });
        this.btnPanel.add(this.okBtn);
        (this.cancelBtn = new JButton()).setMnemonic('C');
        this.cancelBtn.setText("Cancel");
        this.cancelBtn.setToolTipText("Close dialog and cancel changes");
        this.cancelBtn.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent actionEvent) {
                AnnotationsDialog.this.cancelBtnActionPerformed(actionEvent);
            }
        });
        this.btnPanel.add(this.cancelBtn);
        this.getContentPane().add(this.btnPanel, "South");
        this.pack();
    }
    
    private void cancelBtnActionPerformed(final ActionEvent actionEvent) {
        this.closeDialog(false);
    }
    
    private void okBtnActionPerformed(final ActionEvent actionEvent) {
        this.closeDialog(true);
    }
    
    private void closeDialog(final boolean b) {
        if (b && !this.saveChoices()) {
            AnnotationsDialog.log.info("Save failed. Returning to dialog.");
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
        AnnotationsDialog.log = new Log((AnnotationsDialog.class$com$itt$J2KViewer$gui$AnnotationsDialog == null) ? (AnnotationsDialog.class$com$itt$J2KViewer$gui$AnnotationsDialog = class$("com.itt.J2KViewer.gui.AnnotationsDialog")) : AnnotationsDialog.class$com$itt$J2KViewer$gui$AnnotationsDialog);
    }
}
