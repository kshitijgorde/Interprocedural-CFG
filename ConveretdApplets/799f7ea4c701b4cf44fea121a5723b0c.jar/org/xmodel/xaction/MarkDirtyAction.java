// 
// Decompiled by Procyon v0.5.30
// 

package org.xmodel.xaction;

import java.util.Iterator;
import org.xmodel.external.IExternalReference;
import org.xmodel.ModelAlgorithms;
import org.xmodel.IModelObject;
import java.util.List;
import org.xmodel.xpath.expression.IContext;
import org.xmodel.xpath.expression.IExpression;

public class MarkDirtyAction extends GuardedAction
{
    private IExpression º;
    
    @Override
    public void configure(final XActionDocument xActionDocument) {
        super.configure(xActionDocument);
        this.º = xActionDocument.getExpression(xActionDocument.getRoot());
    }
    
    @Override
    protected Object[] doAction(final IContext context) {
        final Iterator<IModelObject> iterator = this.º.query(context, null).iterator();
        while (iterator.hasNext()) {
            final IModelObject dereference = ModelAlgorithms.dereference(iterator.next());
            if (dereference instanceof IExternalReference) {
                ((IExternalReference)dereference).clearCache();
            }
        }
        return null;
    }
}
