// 
// Decompiled by Procyon v0.5.30
// 

package com.nephos6.platform.ui.xaction;

import com.stonewall.cornerstone.xidget.JmsLink;
import java.io.IOException;
import org.xmodel.xaction.XActionException;
import org.xmodel.xpath.expression.StatefulContext;
import org.xmodel.net.ILink;
import com.nephos6.platform.jms.JmsLinkRegistry;
import org.xmodel.xpath.expression.IContext;
import java.util.Iterator;
import org.xmodel.ModelObject;
import org.xmodel.Xlate;
import org.xmodel.xaction.XActionDocument;
import org.xmodel.net.Session;
import org.xmodel.IModelObject;
import org.xmodel.xpath.expression.IExpression;
import org.xmodel.xaction.GuardedAction;

public class RunRemoteAction extends GuardedAction
{
    private IExpression timeoutExpr;
    private IExpression scriptExpr;
    private IModelObject inline;
    private String[] vars;
    private Session session;
    
    @Override
    public void configure(final XActionDocument document) {
        super.configure(document);
        this.timeoutExpr = Xlate.get(document.getRoot(), "timeout", (IExpression)null);
        this.vars = parseVariables(document);
        this.scriptExpr = document.getExpression();
        if (this.scriptExpr == null) {
            this.inline = new ModelObject("script");
            for (final IModelObject child : document.getRoot().getChildren()) {
                this.inline.addChild(child.cloneTree());
            }
        }
    }
    
    private static String[] parseVariables(final XActionDocument document) {
        final String value = Xlate.get(document.getRoot(), "vars", "");
        return value.split("\\s*,\\s*");
    }
    
    @Override
    protected Object[] doAction(final IContext context) {
        final int timeout = (this.timeoutExpr != null) ? ((int)this.timeoutExpr.evaluateNumber(context)) : 60000;
        final IModelObject script = (this.scriptExpr != null) ? this.scriptExpr.queryFirst(context) : this.inline;
        try {
            if (this.session == null) {
                final JmsLink link = JmsLinkRegistry.getLinkByQueue("queue.xidget.ps");
                this.session = link.getProtocol().openSession(link);
            }
            return this.session.execute((StatefulContext)context, this.vars, script, timeout);
        }
        catch (IOException e) {
            throw new XActionException(this.document, "Remote invocation failed", e);
        }
    }
}
