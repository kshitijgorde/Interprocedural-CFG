// 
// Decompiled by Procyon v0.5.30
// 

package geracemenu.richtext;

import java.awt.event.ActionEvent;
import java.awt.AWTEventMulticaster;
import java.awt.event.ActionListener;

public class ClickableTextAction
{
    public static final int CTA_NOTHING = -1;
    public static final int CTA_HREF = 0;
    protected String actionCommand;
    protected String actionTitle;
    protected String actionTarget;
    protected int id;
    protected transient ActionListener actionListener;
    
    public String getActionCommand() {
        return this.actionCommand;
    }
    
    public String getActionTitle() {
        return this.actionTitle;
    }
    
    public String getActionTarget() {
        return this.actionTarget;
    }
    
    public int getActionID() {
        return this.id;
    }
    
    public boolean hasActionListener() {
        return this.actionListener != null;
    }
    
    public synchronized void addActionListener(final ActionListener actionListener) {
        this.actionListener = AWTEventMulticaster.add(this.actionListener, actionListener);
    }
    
    public synchronized void removeActionListener(final ActionListener actionListener) {
        this.actionListener = AWTEventMulticaster.remove(this.actionListener, actionListener);
    }
    
    public void performClickableAction() {
        this.performClickableTextAction(this);
    }
    
    public void performClickableTextAction(final Object o) {
        if (this.actionListener == null) {
            return;
        }
        this.actionListener.actionPerformed(new ActionEvent(o, 1001, this.actionCommand));
    }
    
    public ClickableTextAction(final int n, final String s) {
        this(n, s, null, null);
    }
    
    public ClickableTextAction(final int n, final String s, final String s2) {
        this(n, s, s2, null);
    }
    
    public ClickableTextAction(final int id, final String actionCommand, final String actionTitle, final String actionTarget) {
        this.actionTitle = null;
        this.actionTarget = null;
        if (actionCommand == null) {
            throw new NullPointerException("ClickableTextAction(): null actionCommand");
        }
        this.id = id;
        this.actionListener = null;
        this.actionCommand = actionCommand;
        this.actionTitle = actionTitle;
        this.actionTarget = actionTarget;
    }
}
