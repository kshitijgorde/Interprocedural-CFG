// 
// Decompiled by Procyon v0.5.30
// 

package org.xidget.swing.frame;

import java.awt.Component;
import org.xidget.ifeature.IWidgetCreationFeature;
import java.awt.LayoutManager;
import org.xidget.swing.layout.AdapterLayoutManager;
import java.awt.BorderLayout;
import javax.swing.JMenuBar;
import javax.swing.JFrame;
import org.xidget.IXidget;
import org.xidget.swing.feature.GenericContainerFeature;

public class JFrameContainerFeature extends GenericContainerFeature
{
    public JFrameContainerFeature(final IXidget xidget) {
        super(xidget);
    }
    
    @Override
    public void addWidget(final int n, final IXidget xidget) {
        final JFrame frame = this.xidget.getFeature(JFrame.class);
        final JMenuBar jMenuBar = xidget.getFeature(JMenuBar.class);
        if (jMenuBar != null) {
            frame.setJMenuBar(jMenuBar);
        }
        else {
            frame.getContentPane().setLayout(new AdapterLayoutManager(xidget, new BorderLayout()));
            final Object[] lastWidgets = xidget.getFeature(IWidgetCreationFeature.class).getLastWidgets();
            if (lastWidgets.length > 0) {
                frame.getContentPane().add((Component)lastWidgets[0], n);
            }
        }
    }
}
