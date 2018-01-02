// 
// Decompiled by Procyon v0.5.30
// 

package org.xidget.swing.dialog;

import java.awt.Component;
import org.xidget.ifeature.IWidgetCreationFeature;
import java.awt.LayoutManager;
import org.xidget.swing.layout.AdapterLayoutManager;
import java.awt.BorderLayout;
import javax.swing.JDialog;
import javax.swing.JMenuBar;
import org.xidget.IXidget;
import org.xidget.swing.feature.GenericContainerFeature;

public class JDialogContainerFeature extends GenericContainerFeature
{
    public JDialogContainerFeature(final IXidget xidget) {
        super(xidget);
    }
    
    @Override
    public void addWidget(final int n, final IXidget xidget) {
        final JMenuBar jMenuBar = xidget.getFeature(JMenuBar.class);
        if (jMenuBar != null) {
            this.xidget.getFeature(JDialog.class).setJMenuBar(jMenuBar);
        }
        else {
            final JDialog dialog = this.xidget.getFeature(JDialog.class);
            dialog.getContentPane().setLayout(new AdapterLayoutManager(xidget, new BorderLayout()));
            final Object[] lastWidgets = xidget.getFeature(IWidgetCreationFeature.class).getLastWidgets();
            if (lastWidgets.length > 0) {
                dialog.getContentPane().add((Component)lastWidgets[0], n);
            }
        }
    }
}
