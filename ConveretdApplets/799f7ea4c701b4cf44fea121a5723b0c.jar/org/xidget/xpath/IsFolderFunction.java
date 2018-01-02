// 
// Decompiled by Procyon v0.5.30
// 

package org.xidget.xpath;

import java.util.List;
import org.xmodel.xpath.expression.ExpressionException;
import java.util.Iterator;
import org.xmodel.Xlate;
import org.xmodel.IModelObject;
import java.io.File;
import org.xmodel.xpath.expression.IContext;
import org.xmodel.xpath.expression.IExpression;
import org.xmodel.xpath.function.Function;

public class IsFolderFunction extends Function
{
    public static final String name = "xi:is-folder";
    
    @Override
    public String getName() {
        return "xi:is-folder";
    }
    
    @Override
    public ResultType getType() {
        return ResultType.BOOLEAN;
    }
    
    @Override
    public boolean evaluateBoolean(final IContext context) throws ExpressionException {
        this.assertArgs(1, 1);
        final IExpression argument = this.getArgument(0);
        final IExpression.ResultType type = argument.getType(context);
        if (type == ResultType.STRING) {
            return new File(this.getArgument(0).evaluateString(context)).isDirectory();
        }
        if (type == ResultType.NODES) {
            final Iterator<IModelObject> iterator = argument.evaluateNodes(context).iterator();
            while (iterator.hasNext()) {
                if (!new File(Xlate.get((IModelObject)iterator.next(), "")).isDirectory()) {
                    return false;
                }
            }
            return true;
        }
        return false;
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
    public void notifyValue(final IExpression expression, final IContext[] array, final IModelObject modelObject, final Object o, final Object o2) {
        this.getParent().notifyChange(this, array[0]);
    }
    
    @Override
    public void notifyChange(final IExpression expression, final IContext context, String s, String s2) {
        if (s2 == null) {
            s2 = "";
        }
        if (s == null) {
            s = "";
        }
        final File file = new File(s2);
        final File file2 = new File(s);
        final boolean directory = file.isDirectory();
        final boolean directory2 = file2.isDirectory();
        if (!directory && directory2) {
            this.getParent().notifyChange(this, context, true);
        }
        if (directory && !directory2) {
            this.getParent().notifyChange(this, context, false);
        }
    }
}
