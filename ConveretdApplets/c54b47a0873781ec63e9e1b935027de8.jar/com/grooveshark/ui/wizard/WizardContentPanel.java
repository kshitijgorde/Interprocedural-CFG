// 
// Decompiled by Procyon v0.5.30
// 

package com.grooveshark.ui.wizard;

import javax.swing.JPanel;

public abstract class WizardContentPanel extends JPanel
{
    private static final long serialVersionUID = -6940889491682066090L;
    private WizardPanel parent;
    private String description;
    private String title;
    
    public WizardContentPanel(final String title, final String description) {
        this.title = title;
        this.description = description;
    }
    
    public String getDescription() {
        return this.description;
    }
    
    public String getTitle() {
        return this.title;
    }
    
    protected void updateReadyStatus() {
        if (this.parent == null) {
            return;
        }
        this.parent.setStepComplete(this.isReady());
    }
    
    public void setParent(final WizardPanel parent) {
        this.parent = parent;
    }
    
    public abstract boolean isReady();
}
