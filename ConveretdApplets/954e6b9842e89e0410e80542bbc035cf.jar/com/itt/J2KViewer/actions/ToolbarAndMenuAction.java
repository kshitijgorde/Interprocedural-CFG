// 
// Decompiled by Procyon v0.5.30
// 

package com.itt.J2KViewer.actions;

import java.awt.event.ActionEvent;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.AbstractAction;

public class ToolbarAndMenuAction extends AbstractAction
{
    private static final long serialVersionUID = 1L;
    private ToolbarAndMenuActionHandler actionHandler;
    private String actionType;
    
    public ToolbarAndMenuAction(final String actionType, final String s, final ImageIcon imageIcon, final String s2, final Integer n) {
        super(s, imageIcon);
        this.actionHandler = null;
        this.actionType = null;
        this.actionType = actionType;
        super.putValue("ShortDescription", s2);
        super.putValue("MnemonicKey", n);
    }
    
    public void setHandler(final ToolbarAndMenuActionHandler actionHandler) {
        this.actionHandler = actionHandler;
    }
    
    public void actionPerformed(final ActionEvent actionEvent) {
        if (this.actionHandler != null) {
            System.out.println("#### Action Performed by " + this.actionType);
            this.actionHandler.handleRequest(this.actionType);
            return;
        }
        throw new IllegalStateException("ActionHandler is null in actionPerformed.");
    }
}
