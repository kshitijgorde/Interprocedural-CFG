// 
// Decompiled by Procyon v0.5.30
// 

package org.xmodel.xaction;

import org.xmodel.xpath.variable.IVariableScope;
import org.xmodel.IModelObjectFactory;
import java.util.Iterator;
import org.xmodel.xpath.expression.IContext;
import org.xmodel.Xlate;
import java.util.ArrayList;
import org.xmodel.IModelObject;
import org.xmodel.xpath.XPath;
import java.util.List;
import org.xmodel.xpath.expression.IExpression;
import org.xmodel.log.Log;

public class TryAction extends GuardedAction
{
    private static Log \u00d6;
    private final IExpression \u00d5;
    private ScriptAction \u00d4;
    private List<_A> \u00d8;
    private ScriptAction \u00d9;
    
    static {
        TryAction.\u00d6 = Log.getLog("org.xmodel.xaction");
    }
    
    public TryAction() {
        this.\u00d5 = XPath.createExpression("*[ name() = 'catch']");
    }
    
    @Override
    public void configure(final XActionDocument xActionDocument) {
        super.configure(xActionDocument);
        this.\u00d4 = xActionDocument.createScript("catch", "finally");
        final IModelObject firstChild = xActionDocument.getRoot().getFirstChild("finally");
        if (firstChild != null) {
            this.\u00d9 = xActionDocument.createScript(firstChild, new String[0]);
        }
        final List<IModelObject> query = this.\u00d5.query(xActionDocument.getRoot(), null);
        this.\u00d8 = new ArrayList<_A>(query.size());
        for (int i = 0; i < query.size(); ++i) {
            final IModelObject modelObject = query.get(i);
            final _A a = new _A((_A)null);
            final String value = Xlate.get(modelObject, "class", "java.lang.Throwable");
            try {
                a.C = (Class<Throwable>)xActionDocument.getClassLoader().loadClass(value);
                this.\u00d8.add(a);
            }
            catch (Exception ex) {
                TryAction.\u00d6.exception(ex);
            }
            a.B = xActionDocument.createScript(modelObject, new String[0]);
        }
    }
    
    @Override
    protected Object[] doAction(final IContext context) {
        Object[] run = null;
        try {
            return this.\u00d4.run(context);
        }
        catch (XActionException ex) {
            final Throwable cause = ex.getCause();
            final _A a = this.A(cause);
            if (a != null) {
                this.A(context, cause);
                return a.B.run(context);
            }
            return run;
        }
        catch (Throwable t) {
            final _A a2 = this.A(t);
            if (a2 != null) {
                this.A(context, t);
                return a2.B.run(context);
            }
            return run;
        }
        finally {
            if (this.\u00d9 != null) {
                run = this.\u00d9.run(context);
            }
        }
        return run;
    }
    
    private _A A(final Throwable t) {
        for (final _A a : this.\u00d8) {
            if (a.C.isAssignableFrom(t.getClass())) {
                return a;
            }
        }
        return null;
    }
    
    private void A(final IContext context, final Throwable t) {
        final IVariableScope scope = context.getScope();
        if (scope != null) {
            scope.set("exception", XActionException.createExceptionFragment(t, null));
        }
    }
    
    private class _A
    {
        Class<Throwable> C;
        ScriptAction B;
    }
}
