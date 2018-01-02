// 
// Decompiled by Procyon v0.5.30
// 

package gui.dialog;

import anon.util.JAPMessages;

public class FinishedContentPane extends DialogContentPane implements IWizardSuitable
{
    private static final String MSG_FINISHING;
    static /* synthetic */ Class class$gui$dialog$FinishedContentPane;
    
    public FinishedContentPane(final JAPDialog japDialog, final String s, final DialogContentPane dialogContentPane) {
        this(japDialog, s, JAPMessages.getString(FinishedContentPane.MSG_FINISHING), dialogContentPane);
    }
    
    public FinishedContentPane(final JAPDialog japDialog, final String s, final String s2, final DialogContentPane dialogContentPane) {
        super(japDialog, s, new Layout(s2, 1), new DialogContentPaneOptions(dialogContentPane));
        this.setDefaultButtonOperation(41216);
    }
    
    static /* synthetic */ Class class$(final String s) {
        try {
            return Class.forName(s);
        }
        catch (ClassNotFoundException ex) {
            throw new NoClassDefFoundError(ex.getMessage());
        }
    }
    
    static {
        MSG_FINISHING = ((FinishedContentPane.class$gui$dialog$FinishedContentPane == null) ? (FinishedContentPane.class$gui$dialog$FinishedContentPane = class$("gui.dialog.FinishedContentPane")) : FinishedContentPane.class$gui$dialog$FinishedContentPane).getName() + "_finishing";
    }
}
