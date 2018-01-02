// 
// Decompiled by Procyon v0.5.30
// 

package com.nephos6.platform.ui.xaction;

import javax.jms.JMSException;
import com.nephos6.platform.jms.JmsLinkRegistry;
import org.xmodel.xpath.expression.IContext;
import org.xmodel.xaction.XActionException;
import org.xmodel.Xlate;
import org.xmodel.xaction.XActionDocument;
import org.xmodel.xpath.expression.IExpression;
import com.stonewall.cornerstone.xidget.JmsLink;
import org.xmodel.xaction.GuardedAction;

public class JmsLinkAction extends GuardedAction
{
    private JmsLink link;
    private String inQueue;
    private String outQueue;
    private IExpression timeoutExpr;
    
    @Override
    public void configure(final XActionDocument document) {
        super.configure(document);
        this.inQueue = Xlate.get(document.getRoot(), "in", (String)null);
        if (this.inQueue == null) {
            throw new XActionException(document, "Incoming queue not specifed");
        }
        this.outQueue = Xlate.get(document.getRoot(), "out", (String)null);
        if (this.outQueue == null) {
            throw new XActionException(document, "Outgoing queue not specifed");
        }
        this.timeoutExpr = document.getExpression("timeout", true);
    }
    
    @Override
    protected Object[] doAction(final IContext context) {
        final int timeout = (int)this.timeoutExpr.evaluateNumber(context);
        try {
            (this.link = new JmsLink(this.inQueue, this.outQueue, timeout)).open();
            JmsLinkRegistry.register(this.link);
        }
        catch (JMSException e) {
            final String message = String.format("Unable to open JMS queues: %s and/or %s.", this.inQueue, this.outQueue);
            throw new XActionException(message, e);
        }
        return null;
    }
}
