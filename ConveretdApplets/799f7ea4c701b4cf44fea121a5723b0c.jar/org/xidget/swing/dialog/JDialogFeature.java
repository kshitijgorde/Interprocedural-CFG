// 
// Decompiled by Procyon v0.5.30
// 

package org.xidget.swing.dialog;

import java.awt.Rectangle;
import java.awt.Dimension;
import javax.swing.JDialog;
import org.xmodel.xpath.expression.StatefulContext;
import org.xidget.IXidget;
import org.xidget.ifeature.dialog.IDialogFeature;

public class JDialogFeature implements IDialogFeature
{
    private IXidget xidget;
    
    public JDialogFeature(final IXidget xidget) {
        this.xidget = xidget;
    }
    
    @Override
    public void open(final StatefulContext statefulContext) {
        final JDialog dialog = this.xidget.getFeature(JDialog.class);
        final Rectangle bounds = dialog.getBounds();
        dialog.pack();
        if (bounds.width < 0) {
            bounds.width = dialog.getWidth();
        }
        if (bounds.height < 0) {
            bounds.height = dialog.getHeight();
        }
        dialog.setSize(new Dimension(bounds.width, bounds.height));
        dialog.setVisible(true);
    }
    
    @Override
    public void close(final StatefulContext statefulContext) {
        this.xidget.getFeature(JDialog.class).setVisible(false);
    }
}
