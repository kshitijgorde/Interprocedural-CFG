// 
// Decompiled by Procyon v0.5.30
// 

package org.xmodel.xaction;

import java.io.IOException;
import org.xmodel.IModelObject;
import org.xmodel.xpath.expression.StatefulContext;
import org.xmodel.xpath.expression.IContext;
import org.xmodel.xpath.expression.IExpression;
import org.xmodel.net.Server;
import org.xmodel.IModelObjectFactory;
import org.xmodel.log.Log;

public class StartServerAction extends GuardedAction
{
    private static Log V;
    private IModelObjectFactory W;
    private Server T;
    private String Z;
    private IExpression X;
    private IExpression S;
    private IExpression R;
    private IExpression U;
    private IExpression Y;
    
    static {
        StartServerAction.V = Log.getLog("org.xmodel.xaction");
    }
    
    @Override
    public void configure(final XActionDocument xActionDocument) {
        super.configure(xActionDocument);
        this.W = this.getFactory(xActionDocument.getRoot());
        this.Z = Conventions.getVarName(xActionDocument.getRoot(), true, "assign");
        this.X = xActionDocument.getExpression("host", true);
        this.S = xActionDocument.getExpression("port", true);
        this.R = xActionDocument.getExpression("timeout", true);
        this.U = xActionDocument.getExpression("debug", true);
        this.Y = xActionDocument.getExpression();
    }
    
    @Override
    protected Object[] doAction(final IContext context) {
        final IModelObject modelObject = (this.Y != null) ? this.Y.queryFirst(context) : null;
        final boolean b = this.U != null && this.U.evaluateBoolean(context);
        try {
            (this.T = new Server((this.X != null) ? this.X.evaluateString(context) : "127.0.0.1", (this.S != null) ? ((int)this.S.evaluateNumber(context)) : 27700, (this.R != null) ? ((int)this.R.evaluateNumber(context)) : Integer.MAX_VALUE, b)).setServerContext((modelObject != null) ? new StatefulContext(context.getScope(), modelObject) : context);
            this.T.start(true);
            final StatefulContext statefulContext = (StatefulContext)context;
            final IModelObject object = this.W.createObject(null, "server");
            object.setValue(this.T);
            statefulContext.set(this.Z, object);
        }
        catch (IOException ex) {
            StartServerAction.V.exception(ex);
        }
        return null;
    }
}
