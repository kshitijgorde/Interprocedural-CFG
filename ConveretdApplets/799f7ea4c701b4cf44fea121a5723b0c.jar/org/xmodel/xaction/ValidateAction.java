// 
// Decompiled by Procyon v0.5.30
// 

package org.xmodel.xaction;

import org.xmodel.xpath.variable.IVariableScope;
import java.util.Iterator;
import org.xmodel.xsd.check.SchemaError;
import org.xmodel.xsd.Schema;
import java.util.List;
import java.util.ArrayList;
import org.xmodel.xpath.expression.IContext;
import org.xmodel.IModelObject;
import org.xmodel.xpath.expression.IExpression;
import org.xmodel.IModelObjectFactory;

public class ValidateAction extends GuardedAction
{
    private IModelObjectFactory \u00f3;
    private String \u00f4;
    private IExpression \u00f5;
    private IExpression \u00f6;
    
    @Override
    public void configure(final XActionDocument xActionDocument) {
        super.configure(xActionDocument);
        final IModelObject root = xActionDocument.getRoot();
        this.\u00f3 = this.getFactory(root);
        this.\u00f4 = Conventions.getVarName(root, true, "assign");
        this.\u00f5 = xActionDocument.getExpression("source", false);
        this.\u00f6 = xActionDocument.getExpression("schema", false);
    }
    
    @Override
    protected Object[] doAction(final IContext context) {
        final IModelObject queryFirst = this.\u00f6.queryFirst(context);
        if (queryFirst == null) {
            throw new IllegalArgumentException("Schema root is null in ValidateAction: " + this);
        }
        final ArrayList<IModelObject> list = new ArrayList<IModelObject>();
        final Iterator<IModelObject> iterator = this.\u00f5.query(context, null).iterator();
        while (iterator.hasNext()) {
            final List<SchemaError> validate = Schema.validate(queryFirst, iterator.next(), false);
            if (validate != null) {
                final Iterator<SchemaError> iterator2 = validate.iterator();
                while (iterator2.hasNext()) {
                    this.\u00f3.createObject(null, "error").setValue(iterator2.next().toString());
                }
            }
        }
        final IVariableScope scope = context.getScope();
        if (scope == null) {
            throw new IllegalArgumentException("Action executed in scope which does not support variable assignment: " + this);
        }
        scope.set(this.\u00f4, list);
        return null;
    }
}
