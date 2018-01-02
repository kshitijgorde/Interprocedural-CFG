// 
// Decompiled by Procyon v0.5.30
// 

package com.nephos6.platform.ui.xaction;

import org.xmodel.IModelObject;
import com.nephos6.platform.ui.xpath.XPathLibrary;
import com.stonewall.cornerstone.dm.Bootstrap;
import org.xmodel.xaction.XActionException;
import org.xmodel.xpath.expression.IContext;
import org.xmodel.xaction.XActionDocument;
import org.xmodel.xpath.expression.IExpression;
import org.xmodel.xaction.GuardedAction;

public class AppStartupAction extends GuardedAction
{
    private IExpression bootstrapExpr;
    
    @Override
    public void configure(final XActionDocument document) {
        super.configure(document);
        this.bootstrapExpr = document.getExpression();
    }
    
    @Override
    protected Object[] doAction(final IContext context) {
        System.setProperty("java.protocol.handler.pkgs", "com.stonewall.cornerstone");
        System.setProperty("cornerstone.logger.url", "http://www.xidgets.com/Nephos6/logs");
        try {
            final IModelObject bootstrapRoot = this.bootstrapExpr.queryFirst(context);
            if (bootstrapRoot == null) {
                throw new XActionException(String.format("Bootstrap not found on path, %s.", this.bootstrapExpr.toString()));
            }
            final Bootstrap bootstrap = new Bootstrap(bootstrapRoot.cloneTree());
            bootstrap.init();
            XPathLibrary.register();
        }
        catch (Exception e) {
            throw new XActionException("Application initialization failed.", e);
        }
        return null;
    }
}
