// 
// Decompiled by Procyon v0.5.30
// 

package org.xidget.xaction;

import org.xidget.IToolkit;
import org.xmodel.xaction.XActionException;
import org.xidget.Creator;
import org.xidget.IPlugin;
import org.xmodel.xpath.expression.IContext;
import org.xmodel.xaction.XActionDocument;
import org.xmodel.xpath.expression.IExpression;
import org.xmodel.xaction.GuardedAction;

public class PluginAction extends GuardedAction
{
    private IExpression pluginExpr;
    
    @Override
    public void configure(final XActionDocument xActionDocument) {
        super.configure(xActionDocument);
        this.pluginExpr = xActionDocument.getExpression();
    }
    
    @Override
    protected Object[] doAction(final IContext context) {
        final String evaluateString = this.pluginExpr.evaluateString(context);
        try {
            final IPlugin plugin = (IPlugin)this.getDocument().getClassLoader().loadClass(evaluateString).newInstance();
            final IToolkit toolkit = Creator.getToolkit();
            if (toolkit == null) {
                throw new XActionException("Toolkit must be defined before plugins can be loaded.");
            }
            plugin.configure(toolkit);
        }
        catch (Exception ex) {
            throw new XActionException("Unable to load implementation of IPlugin: " + evaluateString, ex);
        }
        return null;
    }
}
