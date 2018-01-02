// 
// Decompiled by Procyon v0.5.30
// 

package com.itt.J2KViewer.gui;

import java.beans.PropertyChangeEvent;
import com.itt.J2KViewer.util.SecurityUtil;
import java.awt.Component;
import javax.swing.Box;
import java.awt.Dimension;
import java.awt.LayoutManager;
import java.awt.Container;
import javax.swing.BoxLayout;
import com.itt.J2KViewer.controller.ViewCentral;
import java.awt.Color;
import java.awt.Font;
import javax.swing.JLabel;
import com.itt.J2KViewer.util.Log;
import java.beans.PropertyChangeListener;
import javax.swing.JPanel;

public class SecurityBar extends JPanel implements PropertyChangeListener
{
    private Log log;
    JLabel classificationLabel;
    JLabel releasabilityLabel;
    JLabel collectionDateLabel;
    Font labelFont;
    Font labelFont2;
    Color securityColor;
    ViewCentral viewCentral;
    static /* synthetic */ Class class$com$itt$J2KViewer$gui$SecurityBar;
    
    public SecurityBar(final ViewCentral viewCentral) {
        this.log = new Log((SecurityBar.class$com$itt$J2KViewer$gui$SecurityBar == null) ? (SecurityBar.class$com$itt$J2KViewer$gui$SecurityBar = class$("com.itt.J2KViewer.gui.SecurityBar")) : SecurityBar.class$com$itt$J2KViewer$gui$SecurityBar);
        this.classificationLabel = new JLabel();
        this.releasabilityLabel = new JLabel();
        this.collectionDateLabel = new JLabel();
        this.labelFont = new Font("SansSerif", 0, 12);
        this.labelFont2 = new Font("SansSerif", 1, 12);
        this.securityColor = Color.white;
        this.viewCentral = viewCentral;
        this.viewCentral.eventController().addPropertyChangeListener(this);
        this.initPanel();
    }
    
    public void initPanel() {
        this.setLayout(new BoxLayout(this, 2));
        this.classificationLabel.setFont(this.labelFont2);
        this.releasabilityLabel.setFont(this.labelFont2);
        this.collectionDateLabel.setFont(this.labelFont2);
        this.add(Box.createRigidArea(new Dimension(7, 25)));
        this.add(this.classificationLabel);
        this.add(Box.createRigidArea(new Dimension(7, 25)));
        this.add(this.releasabilityLabel);
        this.add(Box.createHorizontalGlue());
        this.add(this.collectionDateLabel);
        this.add(Box.createRigidArea(new Dimension(7, 25)));
        final String securitySegment = this.viewCentral.getPropertyManager().getSecuritySegment();
        final String s = new String();
        final String s2 = new String();
        String s3;
        String s4;
        if ("IMAGE".equalsIgnoreCase(securitySegment)) {
            s3 = "Image";
            s4 = "Image";
        }
        else if ("BOTH".equalsIgnoreCase(securitySegment)) {
            s3 = "File";
            s4 = "Image";
        }
        else {
            s3 = "File";
            s4 = "File";
        }
        this.setToolTipText("<html><b>Security Segment</b><br> Viewer : " + s3 + "<br> Saved Image : " + s4 + "</html>");
    }
    
    public void setSecurityClassification(final String s) {
        this.classificationLabel.setText(SecurityUtil.getClassificationString(s));
        this.securityColor = SecurityUtil.getClassificationColor(s);
        this.setLabelBackground();
    }
    
    private void setSecurityReleasability(final String s) {
        if (s != null && s.trim().length() != 0) {
            this.releasabilityLabel.setText(":  " + s);
        }
    }
    
    private void setCollectionDate(final String s) {
        if (s != null && s.trim().length() != 0) {
            this.collectionDateLabel.setText("Collection Date : " + s);
        }
        else {
            this.collectionDateLabel.setText("");
        }
    }
    
    private void setLabelBackground() {
        this.setBackground(this.securityColor);
        this.classificationLabel.setBackground(this.securityColor);
        this.releasabilityLabel.setBackground(this.securityColor);
        this.collectionDateLabel.setBackground(this.securityColor);
    }
    
    public void propertyChange(final PropertyChangeEvent propertyChangeEvent) {
        final String propertyName = propertyChangeEvent.getPropertyName();
        if (propertyName.equals("XmlDataParsed")) {
            final String securitySegment = this.viewCentral.getPropertyManager().getSecuritySegment();
            this.log.debug("Security Banner Segment : " + securitySegment);
            if ("IMAGE".equalsIgnoreCase(securitySegment)) {
                this.setSecurityClassification(SecurityUtil.getSecurityClassification(this.viewCentral, "IMAGE"));
                this.setSecurityReleasability(SecurityUtil.getSecurityReleasability(this.viewCentral, "IMAGE"));
            }
            else {
                this.setSecurityClassification(SecurityUtil.getSecurityClassification(this.viewCentral, "FILE"));
                this.setSecurityReleasability(SecurityUtil.getSecurityReleasability(this.viewCentral, "FILE"));
            }
            this.setCollectionDate(SecurityUtil.getCollectionDate(this.viewCentral));
            this.setVisible(true);
        }
        if (propertyName.equals("Open") && !this.viewCentral.getPropertyManager().isOpen()) {
            this.setVisible(false);
        }
    }
    
    static /* synthetic */ Class class$(final String s) {
        try {
            return Class.forName(s);
        }
        catch (ClassNotFoundException ex) {
            throw new NoClassDefFoundError().initCause(ex);
        }
    }
}
