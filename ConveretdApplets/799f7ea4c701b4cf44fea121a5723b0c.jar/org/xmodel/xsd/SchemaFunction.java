// 
// Decompiled by Procyon v0.5.30
// 

package org.xmodel.xsd;

import java.util.Iterator;
import org.xmodel.xpath.expression.ExpressionException;
import java.util.ArrayList;
import java.util.Collections;
import org.xmodel.IModelObject;
import java.util.List;
import org.xmodel.xpath.expression.IContext;
import org.xmodel.xpath.XPath;
import org.xmodel.xpath.expression.IExpression;
import org.xmodel.xpath.function.Function;

public class SchemaFunction extends Function
{
    public static final String name = "schema";
    private IExpression ¥;
    private IExpression ª;
    
    public SchemaFunction() {
        this.¥ = XPath.createExpression("element[ @type = $name or @name = $name]");
        this.ª = XPath.createExpression("children/element[ @name = $name]");
    }
    
    @Override
    public String getName() {
        return "schema";
    }
    
    @Override
    public IExpression.ResultType getType() {
        return IExpression.ResultType.NODES;
    }
    
    @Override
    public List<IModelObject> evaluateNodes(final IContext context) throws ExpressionException {
        this.assertArgs(2, 2);
        this.assertType(context, 1, IExpression.ResultType.NODES);
        final IModelObject queryFirst = this.getArgument(0).queryFirst(context);
        if (queryFirst == null) {
            return Collections.emptyList();
        }
        final ArrayList<IModelObject> list = new ArrayList<IModelObject>();
        final IExpression.ResultType type = this.getArgument(1).getType(context);
        if (type == IExpression.ResultType.NODES) {
            final Iterator<IModelObject> iterator = this.getArgument(1).evaluateNodes(context).iterator();
            while (iterator.hasNext()) {
                final IModelObject schema = Schema.findSchema(queryFirst, iterator.next());
                if (schema != null) {
                    list.add(schema);
                }
            }
        }
        else {
            if (type != IExpression.ResultType.STRING) {
                throw new ExpressionException(this, "Second argument must return node-set or string.");
            }
            final String[] split = this.getArgument(1).evaluateString(context).split("/");
            this.¥.setVariable("name", split[0]);
            IModelObject queryFirst2 = this.¥.queryFirst(queryFirst);
            for (int i = 1; i < split.length; ++i) {
                final IModelObject a = this.A(queryFirst2, split[i]);
                if (a == null) {
                    break;
                }
                queryFirst2 = a;
            }
            if (queryFirst2 != null) {
                list.add(queryFirst2);
            }
        }
        return list;
    }
    
    private IModelObject A(final IModelObject modelObject, final String s) {
        this.ª.setVariable("name", s);
        return this.ª.queryFirst(modelObject);
    }
    
    @Override
    public void notifyAdd(final IExpression expression, final IContext context, final List<IModelObject> list) {
        this.getParent().notifyChange(this, context);
    }
    
    @Override
    public void notifyRemove(final IExpression expression, final IContext context, final List<IModelObject> list) {
        this.getParent().notifyChange(this, context);
    }
    
    @Override
    public void notifyChange(final IExpression expression, final IContext context, final String s, final String s2) {
        this.getParent().notifyChange(this, context);
    }
}
