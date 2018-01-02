// 
// Decompiled by Procyon v0.5.30
// 

package gui.dialog;

import javax.swing.JDialog;

public class SimpleWizardContentPane extends DialogContentPane implements IWizardSuitable
{
    public SimpleWizardContentPane(final JAPDialog japDialog, final String s, final Layout layout, final DialogContentPaneOptions dialogContentPaneOptions) {
        super(japDialog, s, layout, dialogContentPaneOptions);
    }
    
    public SimpleWizardContentPane(final JDialog dialog, final String s, final Layout layout, final DialogContentPaneOptions dialogContentPaneOptions) {
        super(dialog, s, layout, dialogContentPaneOptions);
    }
    
    public SimpleWizardContentPane(final JAPDialog japDialog, final String s, final Layout layout, final int n, final DialogContentPane dialogContentPane) {
        super(japDialog, s, layout, new DialogContentPaneOptions(n, dialogContentPane));
    }
    
    public SimpleWizardContentPane(final JAPDialog japDialog, final String s, final DialogContentPane dialogContentPane) {
        super(japDialog, s, new Layout(), new DialogContentPaneOptions(-1, dialogContentPane));
    }
}
