// 
// Decompiled by Procyon v0.5.30
// 

package gui.wizard;

import java.util.Vector;

public class BasicWizard implements Wizard
{
    private WizardHost wizardHost;
    protected Vector m_Pages;
    private String m_strTitle;
    protected int m_PageIndex;
    
    public BasicWizard() {
        this.wizardHost = null;
        this.m_Pages = new Vector();
        this.m_PageIndex = 0;
    }
    
    public void setHost(final WizardHost wizardHost) {
        this.wizardHost = wizardHost;
    }
    
    public WizardHost getHost() {
        return this.wizardHost;
    }
    
    public void help() {
    }
    
    public WizardPage invokeWizard() {
        this.wizardHost.setBackEnabled(false);
        this.wizardHost.setFinishEnabled(false);
        this.wizardHost.showWizardPage(0);
        this.m_PageIndex = 0;
        return null;
    }
    
    public WizardPage next() {
        ++this.m_PageIndex;
        this.wizardHost.setBackEnabled(true);
        if (this.m_PageIndex == this.m_Pages.size() - 1) {
            this.wizardHost.setFinishEnabled(true);
            this.wizardHost.setNextEnabled(false);
        }
        this.wizardHost.showWizardPage(this.m_PageIndex);
        return null;
    }
    
    public WizardPage back() {
        --this.m_PageIndex;
        this.wizardHost.setNextEnabled(true);
        this.wizardHost.setFinishEnabled(false);
        if (this.m_PageIndex == 0) {
            this.wizardHost.setBackEnabled(false);
        }
        this.wizardHost.showWizardPage(this.m_PageIndex);
        return null;
    }
    
    public void addWizardPage(final int n, final WizardPage wizardPage) {
        this.m_Pages.insertElementAt(wizardPage, n);
        this.wizardHost.addWizardPage(n, wizardPage);
    }
    
    public int initTotalSteps() {
        return this.m_Pages.size();
    }
    
    public WizardPage finish() {
        return null;
    }
    
    public void wizardCompleted() {
    }
    
    public void setWizardTitle(final String strTitle) {
        this.m_strTitle = strTitle;
    }
    
    public String getWizardTitle() {
        return this.m_strTitle;
    }
}
