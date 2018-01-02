// 
// Decompiled by Procyon v0.5.30
// 

package org.xidget.xaction;

import org.xmodel.IModelObject;
import org.xmodel.xpath.function.Function;
import org.xmodel.xpath.function.custom.DelegateFunction;
import org.xmodel.xpath.function.FunctionFactory;
import org.xmodel.Xlate;
import org.xmodel.xpath.expression.IContext;
import org.xmodel.xaction.GuardedAction;

public class DeclareAction extends GuardedAction
{
    @Override
    protected Object[] doAction(final IContext context) {
        final IModelObject root = this.getDocument().getRoot();
        final String value = Xlate.get(root, "name", (String)null);
        final String value2 = Xlate.get(root, (String)null);
        if (value != null && value2 != null) {
            FunctionFactory.getInstance().register(value, new DelegateFunction(value, value2));
        }
        return null;
    }
}
