// 
// Decompiled by Procyon v0.5.30
// 

package com.stonewall.cornerstone.xaction;

import java.util.Iterator;
import org.xmodel.xpath.variable.IVariableScope;
import org.xmodel.IChangeSet;
import org.xmodel.IModelObjectFactory;
import org.xmodel.ModelAlgorithms;
import org.xmodel.IModelObject;
import java.util.List;
import org.xmodel.xpath.expression.IContext;
import org.xmodel.Xlate;
import org.xmodel.xaction.XActionDocument;
import org.xmodel.xpath.XPath;
import com.stonewall.cornerstone.utility.IdentityFactory;
import org.xmodel.xpath.expression.IExpression;
import com.stonewall.cornerstone.utility.ILog;
import org.xmodel.xaction.XAction;

public class IdAction extends XAction implements ILog
{
    private static final IExpression selfExpr;
    private IdentityFactory factory;
    private IExpression targetExpr;
    private String variable;
    
    static {
        selfExpr = XPath.createExpression(".");
    }
    
    @Override
    public void configure(final XActionDocument document) {
        super.configure(document);
        this.factory = new IdentityFactory();
        this.variable = Xlate.get(document.getRoot(), "assign", (String)null);
        this.targetExpr = document.getExpression("target", false);
        if (this.targetExpr == null) {
            this.targetExpr = document.getExpression(document.getRoot());
        }
    }
    
    @Override
    public Object[] doRun(final IContext context) {
        if (this.variable != null) {
            final IVariableScope scope = context.getScope();
            scope.set(this.variable, this.factory.next());
        }
        else if (this.targetExpr == null) {
            this.targetExpr = IdAction.selfExpr;
        }
        if (this.targetExpr != null) {
            List<IModelObject> targets = this.targetExpr.query(context, null);
            if (targets.size() == 0) {
                ModelAlgorithms.createPathSubtree(context, this.targetExpr, null, null);
                targets = this.targetExpr.query(context, null);
            }
            for (final IModelObject target : targets) {
                target.setValue(this.factory.next());
            }
        }
        return null;
    }
}
