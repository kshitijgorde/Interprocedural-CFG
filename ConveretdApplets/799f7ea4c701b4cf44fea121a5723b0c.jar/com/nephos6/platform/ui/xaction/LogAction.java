// 
// Decompiled by Procyon v0.5.30
// 

package com.nephos6.platform.ui.xaction;

import org.xmodel.xpath.expression.IContext;
import org.xmodel.log.Log;
import org.xmodel.Xlate;
import org.xmodel.xaction.XActionDocument;
import org.xmodel.xpath.expression.IExpression;
import org.xmodel.xaction.GuardedAction;

public class LogAction extends GuardedAction
{
    private int level;
    private String log;
    private IExpression messageExpr;
    
    @Override
    public void configure(final XActionDocument document) {
        super.configure(document);
        this.log = Xlate.get(document.getRoot(), "name", "com.nephos6.platform.ui");
        this.level = Log.getLevelIndex(Xlate.get(document.getRoot(), "level", "verbose"));
        this.messageExpr = document.getExpression();
    }
    
    @Override
    protected Object[] doAction(final IContext context) {
        Log.getLog(this.log).log(this.level, this.messageExpr.evaluateString(context));
        return null;
    }
}
