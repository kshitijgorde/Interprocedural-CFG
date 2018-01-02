// 
// Decompiled by Procyon v0.5.30
// 

package org.xidget.xaction;

import java.util.Iterator;
import java.util.List;
import org.xmodel.IModelObject;
import org.xmodel.xpath.expression.StatefulContext;
import org.xidget.NamedContexts;
import org.xmodel.xpath.expression.IContext;
import org.xmodel.Xlate;
import org.xmodel.xaction.XActionDocument;
import org.xmodel.xpath.expression.IExpression;
import org.xmodel.xaction.GuardedAction;

public class CreateNamedContext extends GuardedAction
{
    public static final String contextPathVariable = "named.context.path";
    private IExpression nameExpr;
    private IExpression parentExpr;
    private IExpression contextExpr;
    private String contextSpec;
    private IExpression variablesExpr;
    
    @Override
    public void configure(final XActionDocument xActionDocument) {
        super.configure(xActionDocument);
        this.nameExpr = xActionDocument.getExpression("name", true);
        this.parentExpr = xActionDocument.getExpression("parent", true);
        this.contextExpr = xActionDocument.getExpression("context", true);
        this.contextSpec = Xlate.childGet(xActionDocument.getRoot(), "context", (String)null);
        this.variablesExpr = xActionDocument.getExpression("variables", true);
    }
    
    @Override
    protected Object[] doAction(final IContext context) {
        final String evaluateString = this.nameExpr.evaluateString(context);
        final StatefulContext value = NamedContexts.get(this.parentExpr.evaluateString(context));
        final IModelObject queryFirst = this.contextExpr.queryFirst(context);
        if (value != null) {
            final StatefulContext statefulContext = new StatefulContext(value, queryFirst);
            statefulContext.set("named.context.path", this.contextSpec);
            this.createVariables(context, statefulContext);
            NamedContexts.set(evaluateString, statefulContext);
        }
        else {
            final StatefulContext statefulContext2 = new StatefulContext(queryFirst);
            statefulContext2.set("named.context.path", this.contextSpec);
            this.createVariables(context, statefulContext2);
            NamedContexts.set(evaluateString, statefulContext2);
        }
        return null;
    }
    
    private void createVariables(final IContext context, final StatefulContext statefulContext) {
        for (final IModelObject modelObject : this.variablesExpr.query(context, null)) {
            final String value = Xlate.get(modelObject, "name", (String)null);
            final IExpression value2 = Xlate.get(modelObject, (IExpression)null);
            if (value2 != null) {
                switch (value2.getType(context)) {
                    default: {
                        continue;
                    }
                    case NODES: {
                        statefulContext.set(value, value2.evaluateNodes(context));
                        continue;
                    }
                    case STRING: {
                        statefulContext.set(value, value2.evaluateString(context));
                        continue;
                    }
                    case NUMBER: {
                        statefulContext.set(value, value2.evaluateNumber(context));
                        continue;
                    }
                    case BOOLEAN: {
                        statefulContext.set(value, Boolean.valueOf(value2.evaluateBoolean(context)));
                        continue;
                    }
                }
            }
        }
    }
}
