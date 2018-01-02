// 
// Decompiled by Procyon v0.5.30
// 

package gui.wizard;

import gui.dialog.JAPDialog;

public interface WizardHost
{
    JAPDialog getDialogParent();
    
    void setFinishEnabled(final boolean p0);
    
    void setNextEnabled(final boolean p0);
    
    void setCancelEnabled(final boolean p0);
    
    void setBackEnabled(final boolean p0);
    
    void setHelpEnabled(final boolean p0);
    
    void addWizardPage(final int p0, final WizardPage p1);
    
    void showWizardPage(final int p0);
}
