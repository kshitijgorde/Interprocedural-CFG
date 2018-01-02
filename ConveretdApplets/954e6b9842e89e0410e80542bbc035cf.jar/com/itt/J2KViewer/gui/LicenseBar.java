// 
// Decompiled by Procyon v0.5.30
// 

package com.itt.J2KViewer.gui;

import com.itt.J2KViewer.util.LicenseUtil;
import java.beans.PropertyChangeEvent;
import java.awt.Frame;
import java.awt.Component;
import javax.swing.Box;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.LayoutManager;
import java.awt.Container;
import javax.swing.BoxLayout;
import java.util.Calendar;
import java.awt.Color;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JFrame;
import com.itt.J2KViewer.controller.ViewCentral;
import java.beans.PropertyChangeListener;
import javax.swing.JPanel;

public class LicenseBar extends JPanel implements PropertyChangeListener
{
    private ViewCentral viewCentral;
    private String licenseVendor;
    private String licenseContent;
    private int licenseYear;
    private JFrame mainFrame;
    private JLabel licenseLabel;
    private JButton licenseButton;
    private Font labelFont;
    private Color labelColor;
    
    public LicenseBar(final ViewCentral viewCentral) {
        this.licenseVendor = "Unknown";
        this.licenseContent = null;
        this.licenseYear = Calendar.getInstance().get(1);
        this.licenseLabel = new JLabel("");
        this.licenseButton = new JButton("View License");
        this.labelFont = new Font("SansSerif", 1, 12);
        this.labelColor = Color.white;
        this.mainFrame = viewCentral.getJ2KViewerFrame();
        this.viewCentral = viewCentral;
        this.viewCentral.eventController().addPropertyChangeListener(this);
        this.initPanel();
    }
    
    public void initPanel() {
        this.setVisible(false);
        this.setLayout(new BoxLayout(this, 2));
        this.setBackground(this.labelColor);
        this.licenseLabel.setFont(this.labelFont);
        this.licenseButton.setVisible(false);
        this.licenseButton.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent actionEvent) {
                LicenseBar.this.buttonActionPerformed(actionEvent);
            }
        });
        this.add(Box.createRigidArea(new Dimension(0, 30)));
        this.add(Box.createHorizontalGlue());
        this.add(this.licenseLabel);
        this.add(Box.createRigidArea(new Dimension(10, 30)));
        this.add(this.licenseButton);
        this.add(Box.createHorizontalGlue());
    }
    
    private void buttonActionPerformed(final ActionEvent actionEvent) {
        final LicenseDialog licenseDialog = new LicenseDialog(this.mainFrame, true, this.licenseContent);
        licenseDialog.setLocationRelativeTo(this.mainFrame);
        licenseDialog.setVisible(true);
    }
    
    public void propertyChange(final PropertyChangeEvent propertyChangeEvent) {
        final String propertyName = propertyChangeEvent.getPropertyName();
        if (propertyName.equals("XmlDataParsed")) {
            final LicenseUtil licenseUtil = new LicenseUtil(this.viewCentral);
            this.licenseContent = licenseUtil.getLicenseContent();
            if (this.licenseContent != null && this.licenseContent.trim().length() > 0) {
                this.licenseButton.setVisible(true);
                this.displayLicenseInfo();
            }
            final String licenseVendor = licenseUtil.getLicenseVendor();
            if (licenseVendor != null && licenseVendor.trim().length() > 0) {
                this.licenseVendor = licenseVendor;
                this.displayLicenseInfo();
            }
        }
        if (propertyName.equals("Open") && !this.viewCentral.getPropertyManager().isOpen()) {
            this.setVisible(false);
        }
    }
    
    private void displayLicenseInfo() {
        this.licenseLabel.setText("© " + this.licenseYear + " " + this.licenseVendor);
        this.setVisible(true);
    }
}
