// 
// Decompiled by Procyon v0.5.30
// 

package com.grooveshark.ui.component;

import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;

public class MouseToActionListener extends MouseAdapter
{
    private static final ActionListener NONE;
    private ActionListener actionListener;
    private String actionCommand;
    private boolean isEnabled;
    
    public MouseToActionListener() {
        this(MouseToActionListener.NONE, "");
    }
    
    public void setEnabled(final boolean enabled) {
        this.isEnabled = enabled;
    }
    
    public MouseToActionListener(final ActionListener action, final String actionCommand) {
        this.isEnabled = true;
        this.actionListener = action;
        this.actionCommand = actionCommand;
    }
    
    public ActionListener getActionListener() {
        return this.actionListener;
    }
    
    public void setActionListener(final ActionListener actionListener) {
        this.actionListener = actionListener;
    }
    
    public String getActionCommand() {
        return this.actionCommand;
    }
    
    public void setActionCommand(final String actionCommand) {
        this.actionCommand = actionCommand;
    }
    
    public void mouseClicked(final MouseEvent e) {
        if (this.isEnabled) {
            this.actionListener.actionPerformed(new ActionEvent(this, 1001, this.actionCommand));
        }
    }
    
    static {
        NONE = new ActionListener() {
            public void actionPerformed(final ActionEvent e) {
            }
        };
    }
}
