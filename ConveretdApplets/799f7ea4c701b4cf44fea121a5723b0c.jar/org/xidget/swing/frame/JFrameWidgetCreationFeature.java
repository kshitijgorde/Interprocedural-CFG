// 
// Decompiled by Procyon v0.5.30
// 

package org.xidget.swing.frame;

import java.util.Iterator;
import java.awt.event.WindowListener;
import org.xidget.Creator;
import java.awt.event.WindowEvent;
import java.awt.event.WindowAdapter;
import java.awt.LayoutManager;
import org.xidget.swing.layout.AdapterLayoutManager;
import java.awt.BorderLayout;
import org.xidget.swing.form.JPanelXidget;
import javax.swing.JFrame;
import org.xidget.IXidget;
import org.xidget.ifeature.IWidgetCreationFeature;

public class JFrameWidgetCreationFeature implements IWidgetCreationFeature
{
    private IXidget xidget;
    private JFrame jframe;
    
    public JFrameWidgetCreationFeature(final IXidget xidget) {
        this.xidget = xidget;
    }
    
    @Override
    public void createWidgets() {
        (this.jframe = new JFrame()).setLocationByPlatform(true);
        this.jframe.setDefaultCloseOperation(2);
        for (final IXidget xidget : this.xidget.getChildren()) {
            if (xidget instanceof JPanelXidget) {
                this.jframe.getContentPane().setLayout(new AdapterLayoutManager(xidget, new BorderLayout()));
            }
        }
        this.jframe.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosed(final WindowEvent windowEvent) {
                Creator.getInstance().destroy(JFrameWidgetCreationFeature.this.xidget);
            }
        });
    }
    
    @Override
    public void destroyWidgets() {
        if (this.jframe != null) {
            this.jframe.dispose();
        }
        this.jframe = null;
    }
    
    @Override
    public Object[] getLastWidgets() {
        return new Object[] { this.jframe };
    }
    
    public JFrame getFrame() {
        return this.jframe;
    }
}
