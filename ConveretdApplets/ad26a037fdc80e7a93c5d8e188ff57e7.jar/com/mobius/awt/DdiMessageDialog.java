// 
// Decompiled by Procyon v0.5.30
// 

package com.mobius.awt;

import java.awt.event.ActionEvent;
import java.awt.event.WindowEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyAdapter;
import java.awt.Point;
import java.awt.Insets;
import java.awt.Dimension;
import java.awt.event.KeyListener;
import java.awt.event.ActionListener;
import java.awt.event.WindowListener;
import java.awt.Component;
import java.awt.Color;
import java.awt.LayoutManager;
import java.awt.Frame;
import java.awt.TextArea;
import java.awt.Button;
import java.awt.Dialog;

public class DdiMessageDialog extends Dialog
{
    protected Button okButton;
    protected TextArea displayMessageText;
    boolean fComponentsAdjusted;
    
    public DdiMessageDialog(final Dialog dialog, final String s, final String s2) {
        super(dialog, s, false);
        this.okButton = new Button();
        this.displayMessageText = new TextArea();
        this.fComponentsAdjusted = false;
        this.initialize(s2);
    }
    
    public DdiMessageDialog(final Frame frame, final String s, final String s2) {
        super(frame, s, false);
        this.okButton = new Button();
        this.displayMessageText = new TextArea();
        this.fComponentsAdjusted = false;
        this.initialize(s2);
    }
    
    private void initialize(final String label) {
        this.setLayout(null);
        this.setBackground(Color.lightGray);
        this.setSize(400, 250);
        this.setVisible(false);
        this.add(this.okButton);
        this.okButton.setBounds(150, 204, 100, 30);
        this.okButton.setLabel(label);
        this.add(this.displayMessageText);
        this.displayMessageText.setBackground(Color.white);
        this.displayMessageText.setBounds(10, 12, 380, 175);
        this.displayMessageText.setEditable(false);
        final Dimension size = this.getSize();
        final Dimension screenSize = this.getToolkit().getScreenSize();
        this.setLocation((screenSize.width - size.width) / 2, (screenSize.height - size.height) / 2);
        this.addWindowListener(new WindowAction());
        this.okButton.addActionListener(new ButtonAction());
        this.okButton.addKeyListener(new KeyAction());
    }
    
    public void setText(final String s) {
        this.displayMessageText.setText(this.convertNewLines(s));
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
    
    private String convertNewLines(final String s) {
        final StringBuffer sb = new StringBuffer(s.length());
        for (int i = 0; i < s.length(); ++i) {
            final char char1 = s.charAt(i);
            if (char1 == '\\') {
                if (s.charAt(i + 1) == 'n') {
                    sb.append('\n');
                    ++i;
                }
                else {
                    sb.append(char1);
                }
            }
            else {
                sb.append(char1);
            }
        }
        return new String(sb);
    }
    
    class KeyAction extends KeyAdapter
    {
        public void keyPressed(final KeyEvent keyEvent) {
            final int keyCode = keyEvent.getKeyCode();
            if (27 == keyCode || 10 == keyCode) {
                DdiMessageDialog.this.setVisible(false);
            }
        }
    }
    
    class WindowAction extends WindowAdapter
    {
        public void windowClosing(final WindowEvent windowEvent) {
            if (windowEvent.getSource() == DdiMessageDialog.this) {
                DdiMessageDialog.this.setVisible(false);
            }
        }
    }
    
    class ButtonAction implements ActionListener
    {
        public void actionPerformed(final ActionEvent actionEvent) {
            if (actionEvent.getSource() == DdiMessageDialog.this.okButton) {
                DdiMessageDialog.this.setVisible(false);
            }
        }
    }
}
