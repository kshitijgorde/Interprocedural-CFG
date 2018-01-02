// 
// Decompiled by Procyon v0.5.30
// 

package com.nephos6.platform.ui.xaction;

import org.xmodel.xaction.XActionException;
import com.stonewall.cornerstone.dm.DeviceServerLight;
import org.xmodel.IModelObject;
import org.xmodel.xpath.expression.IContext;
import org.xmodel.xaction.XActionDocument;
import org.xmodel.xpath.expression.IExpression;
import org.xmodel.xaction.GuardedAction;

public class StartAgentAction extends GuardedAction
{
    private IExpression bootstrapExpr;
    
    @Override
    public void configure(final XActionDocument document) {
        super.configure(document);
        this.bootstrapExpr = document.getExpression();
    }
    
    @Override
    protected Object[] doAction(final IContext context) {
        final IModelObject bootstrap = this.bootstrapExpr.queryFirst(context);
        final Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    final DeviceServerLight server = new DeviceServerLight(bootstrap);
                    server.init();
                }
                catch (Exception e) {
                    throw new XActionException("Caught exception starting agent.", e);
                }
            }
        });
        thread.setDaemon(true);
        thread.start();
        return null;
    }
}
