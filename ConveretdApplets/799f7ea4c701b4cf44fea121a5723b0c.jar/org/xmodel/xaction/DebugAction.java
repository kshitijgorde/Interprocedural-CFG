// 
// Decompiled by Procyon v0.5.30
// 

package org.xmodel.xaction;

import java.io.IOException;
import org.xmodel.net.Client;
import org.xmodel.xpath.expression.IContext;
import org.xmodel.xpath.expression.IExpression;
import org.xmodel.net.Session;

public class DebugAction extends GuardedAction
{
    private static ThreadLocal<Session> ¥;
    private IExpression ¢;
    private IExpression z;
    private IExpression ¤;
    private IExpression £;
    
    @Override
    public void configure(final XActionDocument xActionDocument) {
        super.configure(xActionDocument);
        this.¢ = xActionDocument.getExpression("host", true);
        this.z = xActionDocument.getExpression("port", true);
        this.£ = xActionDocument.getExpression("timeout", true);
        this.¤ = xActionDocument.getExpression("op", true);
        if (this.¤ == null) {
            this.¤ = xActionDocument.getExpression();
        }
        if (DebugAction.¥ == null) {
            DebugAction.¥ = new ThreadLocal<Session>();
        }
    }
    
    @Override
    protected Object[] doAction(final IContext context) {
        final String lowerCase = this.¤.evaluateString(context).trim().toLowerCase();
        if (DebugAction.¥.get() == null) {
            final String s = (this.¢ != null) ? this.¢.evaluateString(context) : "127.0.0.1";
            final int n = (this.z != null) ? ((int)this.z.evaluateNumber(context)) : 27700;
            final int n2 = (this.£ != null) ? ((int)this.£.evaluateNumber(context)) : Integer.MAX_VALUE;
            try {
                DebugAction.¥.set(new Client(s, n, n2, true).connect(n2));
            }
            catch (IOException ex) {
                throw new XActionException(String.format("Unable to connect to %s:%d.", s, n), ex);
            }
        }
        final Session session = DebugAction.¥.get();
        try {
            if (lowerCase.equals("go")) {
                session.debugGo();
            }
            else if (lowerCase.equals("stop")) {
                session.debugStop();
            }
            else if (lowerCase.equals("stepin")) {
                session.debugStepIn();
            }
            else if (lowerCase.equals("stepover")) {
                session.debugStepOver();
            }
            else if (lowerCase.equals("stepout")) {
                session.debugStepOut();
            }
            else {
                if (lowerCase.length() == 0) {
                    throw new XActionException("Empty debug operation.");
                }
                throw new XActionException("Undefined debug operation: " + lowerCase);
            }
        }
        catch (IOException ex2) {
            throw new XActionException("Unable to execute debug operation.", ex2);
        }
        return null;
    }
}
