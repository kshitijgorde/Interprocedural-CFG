// 
// Decompiled by Procyon v0.5.30
// 

package org.xidget.swing.applet;

import javax.swing.JApplet;
import org.xidget.ifeature.IWidgetCreationFeature;

public class JAppletWidgetCreationFeature implements IWidgetCreationFeature
{
    private JApplet applet;
    
    public JAppletWidgetCreationFeature(final JApplet applet) {
        this.applet = applet;
    }
    
    @Override
    public void createWidgets() {
    }
    
    @Override
    public void destroyWidgets() {
    }
    
    @Override
    public Object[] getLastWidgets() {
        return new Object[] { this.applet };
    }
    
    public JApplet getJApplet() {
        return this.applet;
    }
}
