// 
// Decompiled by Procyon v0.5.30
// 

package org.xidget.swing.applet;

import java.awt.Component;
import org.xidget.ifeature.IWidgetCreationFeature;
import java.awt.LayoutManager;
import org.xidget.swing.layout.AdapterLayoutManager;
import java.awt.BorderLayout;
import javax.swing.JMenuBar;
import javax.swing.JApplet;
import org.xidget.IXidget;
import org.xidget.swing.feature.GenericContainerFeature;

public class JAppletContainerFeature extends GenericContainerFeature
{
    public JAppletContainerFeature(final IXidget xidget) {
        super(xidget);
    }
    
    @Override
    public void addWidget(final int n, final IXidget xidget) {
        final JApplet applet = this.xidget.getFeature(JApplet.class);
        final JMenuBar jMenuBar = xidget.getFeature(JMenuBar.class);
        if (jMenuBar != null) {
            applet.setJMenuBar(jMenuBar);
        }
        else {
            applet.getContentPane().setLayout(new AdapterLayoutManager(xidget, new BorderLayout()));
            final Object[] lastWidgets = xidget.getFeature(IWidgetCreationFeature.class).getLastWidgets();
            if (lastWidgets.length > 0) {
                applet.getContentPane().add((Component)lastWidgets[0], n);
            }
        }
    }
}
