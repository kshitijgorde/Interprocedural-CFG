// 
// Decompiled by Procyon v0.5.30
// 

package gui.wizard;

public interface Wizard
{
    void setHost(final WizardHost p0);
    
    WizardHost getHost();
    
    WizardPage invokeWizard();
    
    WizardPage finish();
    
    WizardPage next();
    
    WizardPage back();
    
    void help();
    
    int initTotalSteps();
    
    void wizardCompleted();
    
    String getWizardTitle();
}
