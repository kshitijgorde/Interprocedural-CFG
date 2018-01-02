// 
// Decompiled by Procyon v0.5.30
// 

package gui.wizard;

import javax.swing.JComponent;

public interface WizardPage
{
    boolean checkPage();
    
    void showInformationDialog(final String p0);
    
    JComponent getPageComponent();
}
