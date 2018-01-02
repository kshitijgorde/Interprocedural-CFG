// 
// Decompiled by Procyon v0.5.30
// 

package org.xidget.swing.dialog;

import org.xmodel.IModelObject;
import java.awt.LayoutManager;
import org.xidget.swing.layout.AdapterLayoutManager;
import java.awt.BorderLayout;
import org.xmodel.Xlate;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentAdapter;
import javax.swing.JDialog;
import org.xidget.IXidget;
import java.awt.event.ComponentListener;
import org.xidget.ifeature.IWidgetCreationFeature;

public class JDialogWidgetCreationFeature implements IWidgetCreationFeature
{
    private final ComponentListener moveListener;
    private IXidget xidget;
    private JDialog jDialog;
    
    public JDialogWidgetCreationFeature(final IXidget xidget) {
        this.moveListener = new ComponentAdapter() {
            @Override
            public void componentMoved(final ComponentEvent componentEvent) {
            }
            
            @Override
            public void componentResized(final ComponentEvent componentEvent) {
                JDialogWidgetCreationFeature.this.jDialog.getContentPane();
            }
        };
        this.xidget = xidget;
    }
    
    @Override
    public void createWidgets() {
        final IModelObject config = this.xidget.getConfig();
        (this.jDialog = new JDialog()).setLocationByPlatform(true);
        this.jDialog.setModal(Xlate.get(config, "modal", true));
        this.jDialog.addComponentListener(this.moveListener);
        if (this.xidget.getChildren().size() > 0) {
            this.jDialog.getContentPane().setLayout(new AdapterLayoutManager(this.xidget.getChildren().get(0), new BorderLayout()));
        }
    }
    
    @Override
    public void destroyWidgets() {
        this.jDialog.dispose();
        this.jDialog = null;
    }
    
    @Override
    public Object[] getLastWidgets() {
        return new Object[] { this.jDialog };
    }
    
    public JDialog getJDialog() {
        return this.jDialog;
    }
}
