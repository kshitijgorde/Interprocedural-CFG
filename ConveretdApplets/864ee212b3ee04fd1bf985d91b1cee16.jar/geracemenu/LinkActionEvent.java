// 
// Decompiled by Procyon v0.5.30
// 

package geracemenu;

import java.awt.event.ActionEvent;

public class LinkActionEvent extends ActionEvent
{
    public static final int LINKACTION_CLICKED = 2000;
    public static final int LINKACTION_HOVERED = 2001;
    private String actionTarget;
    private String actionTitle;
    
    public String getActionTitle() {
        return this.actionTitle;
    }
    
    public String getActionTarget() {
        return this.actionTarget;
    }
    
    public LinkActionEvent(final Object o, final int n, final String s) {
        this(o, n, s, null, null);
    }
    
    public LinkActionEvent(final Object o, final int n, final String s, final String s2) {
        this(o, n, s, s2, null);
    }
    
    public LinkActionEvent(final Object o, final int n, final String s, final String actionTitle, final String actionTarget) {
        super(o, n, s);
        this.actionTitle = actionTitle;
        this.actionTarget = actionTarget;
    }
}
