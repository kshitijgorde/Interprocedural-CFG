// 
// Decompiled by Procyon v0.5.30
// 

package org.xidget.swing.dialog;

import javax.swing.JDialog;
import org.xidget.IXidget;
import org.xidget.ifeature.ITitleFeature;

public class JDialogTitleFeature implements ITitleFeature
{
    private IXidget xidget;
    
    public JDialogTitleFeature(final IXidget xidget) {
        this.xidget = xidget;
    }
    
    @Override
    public void setTitle(final String title) {
        final JDialog dialog = this.xidget.getFeature(JDialog.class);
        if (dialog != null) {
            dialog.setTitle(title);
        }
    }
}
