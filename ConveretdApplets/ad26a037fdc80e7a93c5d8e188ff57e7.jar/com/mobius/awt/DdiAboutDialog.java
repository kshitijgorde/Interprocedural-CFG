// 
// Decompiled by Procyon v0.5.30
// 

package com.mobius.awt;

import java.awt.event.ActionEvent;
import java.awt.event.WindowEvent;
import java.awt.event.WindowAdapter;
import java.awt.Point;
import java.awt.Insets;
import java.awt.Dimension;
import java.awt.event.ActionListener;
import java.awt.event.WindowListener;
import java.awt.Component;
import java.awt.Color;
import java.awt.LayoutManager;
import java.awt.Frame;
import java.awt.TextArea;
import java.awt.Button;
import java.awt.Dialog;

public class DdiAboutDialog extends Dialog
{
    protected Button okButton;
    protected TextArea DisplayAboutInfo;
    boolean fComponentsAdjusted;
    
    public DdiAboutDialog(final Frame frame, final String s, final String label) {
        super(frame, s, false);
        this.okButton = new Button();
        this.DisplayAboutInfo = new TextArea("", 50, 50, 1);
        this.fComponentsAdjusted = false;
        this.setLayout(null);
        this.setBackground(Color.lightGray);
        this.setSize(400, 250);
        this.setVisible(false);
        this.add(this.okButton);
        this.okButton.setBounds(150, 204, 100, 30);
        this.okButton.setLabel(label);
        this.add(this.DisplayAboutInfo);
        this.DisplayAboutInfo.setBackground(Color.white);
        this.DisplayAboutInfo.setBounds(10, 12, 380, 175);
        this.DisplayAboutInfo.setEditable(false);
        final Dimension size = this.getSize();
        final Dimension screenSize = this.getToolkit().getScreenSize();
        this.setLocation((screenSize.width - size.width) / 2, (screenSize.height - size.height) / 2);
        this.addWindowListener(new WindowAction());
        this.okButton.addActionListener(new ButtonAction());
    }
    
    public void setText(final String text) {
        this.DisplayAboutInfo.setText(text);
    }
    
    public void addNotify() {
        final Dimension size = this.getSize();
        super.addNotify();
        if (this.fComponentsAdjusted) {
            return;
        }
        final Insets insets = this.getInsets();
        this.setSize(insets.left + insets.right + size.width, insets.top + insets.bottom + size.height);
        final Component[] components = this.getComponents();
        for (int i = 0; i < components.length; ++i) {
            final Point location = components[i].getLocation();
            location.translate(insets.left, insets.top);
            components[i].setLocation(location);
        }
        this.fComponentsAdjusted = true;
    }
    
    public void setVisible(final boolean visible) {
        if (visible) {
            final Dimension size = this.getSize();
            final Dimension screenSize = this.getToolkit().getScreenSize();
            this.setLocation((screenSize.width - size.width) / 2, (screenSize.height - size.height) / 2);
        }
        super.setVisible(visible);
    }
    
    class WindowAction extends WindowAdapter
    {
        public void windowClosing(final WindowEvent windowEvent) {
            if (windowEvent.getSource() == DdiAboutDialog.this) {
                DdiAboutDialog.this.setVisible(false);
            }
        }
    }
    
    class ButtonAction implements ActionListener
    {
        public void actionPerformed(final ActionEvent actionEvent) {
            if (actionEvent.getSource() == DdiAboutDialog.this.okButton) {
                DdiAboutDialog.this.setVisible(false);
            }
        }
    }
}
