// 
// Decompiled by Procyon v0.5.30
// 

package org.xidget.swing.feature;

import org.xidget.swing.tabs.CustomTab;
import javax.swing.JTabbedPane;
import org.xmodel.IModelObject;
import org.xmodel.xpath.expression.IExpression;
import org.xmodel.Xlate;
import org.xidget.IXidget;
import org.xidget.ifeature.ITitleFeature;

public class SwingTitleFeature implements ITitleFeature
{
    private IXidget xidget;
    
    public SwingTitleFeature(final IXidget xidget) {
        this.xidget = xidget;
    }
    
    public String getTitle() {
        final IModelObject config = this.xidget.getConfig();
        return Xlate.childGet(config, "title", Xlate.get(config, "title", (IExpression)null)).evaluateString();
    }
    
    @Override
    public void setTitle(final String title) {
        final IXidget parent = this.xidget.getParent();
        if (parent == null) {
            return;
        }
        if (parent.getConfig().isType("tabs")) {
            final JTabbedPane tabbedPane = parent.getFeature(JTabbedPane.class);
            final int index = parent.getChildren().indexOf(this.xidget);
            if (index < tabbedPane.getTabCount()) {
                ((CustomTab)tabbedPane.getTabComponentAt(index)).setTitle(title);
            }
        }
    }
}
