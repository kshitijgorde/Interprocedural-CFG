// 
// Decompiled by Procyon v0.5.30
// 

package com.itt.J2KViewer.gui;

import javax.swing.Icon;
import java.awt.event.ActionEvent;
import com.itt.J2KViewer.util.Helper;
import java.awt.LayoutManager;
import java.awt.BorderLayout;
import com.itt.J2KViewer.controller.ViewCentral;
import javax.swing.ImageIcon;
import java.awt.Component;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import javax.swing.JPanel;

public class InfoPanel extends JPanel implements ActionListener
{
    private static final long serialVersionUID = 1L;
    private JButton minimizeButton;
    private Component component;
    private ImageIcon downIcon;
    private ImageIcon rightIcon;
    private String name;
    private ViewCentral viewCentral;
    
    public InfoPanel(final String name, final Component component, final ViewCentral viewCentral) {
        this.component = component;
        this.name = name;
        this.viewCentral = viewCentral;
        this.setLayout(new BorderLayout());
        this.downIcon = Helper.loadImage("DownArrow.gif", "down");
        this.rightIcon = Helper.loadImage("RightArrow.gif", "right");
        (this.minimizeButton = new JButton(name)).setIconTextGap(30);
        this.minimizeButton.setHorizontalAlignment(2);
        this.minimizeButton.addActionListener(this);
        boolean b = false;
        if ("Location".equals(name)) {
            b = this.viewCentral.getPropertyManager().getIsShowGeoLocationPanel();
        }
        else if ("Status".equals(name)) {
            b = this.viewCentral.getPropertyManager().getIsShowStatusPanel();
        }
        else if ("Dynamic Range".equals(name)) {
            b = this.viewCentral.getPropertyManager().getIsShowDynamicRangePanel();
        }
        else if (name.startsWith("Overview")) {
            b = this.viewCentral.getPropertyManager().isShowOverviewImage();
        }
        this.showComponent(b);
        this.add(this.minimizeButton, "North");
        this.add(component, "Center");
    }
    
    public void actionPerformed(final ActionEvent actionEvent) {
        this.showComponent(!this.component.isVisible());
    }
    
    private void showComponent(final boolean b) {
        if (b) {
            this.minimizeButton.setIcon(this.downIcon);
            this.minimizeButton.setToolTipText("Collapse this panel");
            if (this.name.startsWith("Overview")) {
                this.minimizeButton.setText("Overview Image On");
                this.viewCentral.getOverviewImagePanel().setShowOverviewImage(true);
                if (this.viewCentral.getPropertyManager().isOpen()) {
                    this.viewCentral.getOverviewImagePanel().restartImageStream();
                }
            }
        }
        else {
            this.minimizeButton.setIcon(this.rightIcon);
            this.minimizeButton.setToolTipText("Expand this panel");
            if (this.name.startsWith("Overview")) {
                this.minimizeButton.setText("Overview Image Off");
                this.viewCentral.getOverviewImagePanel().setShowOverviewImage(false);
            }
        }
        this.component.setVisible(b);
        if ("Location".equals(this.name)) {
            this.viewCentral.getPropertyManager().setIsShowGeoLocationPanel(b);
        }
        else if ("Status".equals(this.name)) {
            this.viewCentral.getPropertyManager().setIsShowStatusPanel(b);
        }
        else if ("Dynamic Range".equals(this.name)) {
            this.viewCentral.getPropertyManager().setIsShowDynamicRangePanel(b);
        }
        this.updateUI();
    }
}
