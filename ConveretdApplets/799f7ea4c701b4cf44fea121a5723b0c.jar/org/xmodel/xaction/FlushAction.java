// 
// Decompiled by Procyon v0.5.30
// 

package org.xmodel.xaction;

import java.util.Iterator;
import org.xmodel.external.IExternalReference;
import org.xmodel.IModelObject;
import java.util.List;
import org.xmodel.xpath.expression.IContext;
import org.xmodel.xpath.expression.IExpression;

public class FlushAction extends GuardedAction
{
    private IExpression \u00e8;
    
    @Override
    public void configure(final XActionDocument xActionDocument) {
        super.configure(xActionDocument);
        this.\u00e8 = xActionDocument.getExpression();
    }
    
    @Override
    protected Object[] doAction(final IContext context) {
        for (final IModelObject modelObject : this.\u00e8.query(context, null)) {
            if (modelObject instanceof IExternalReference) {
                try {
                    final IExternalReference externalReference = (IExternalReference)modelObject;
                }
                catch (Exception ex) {
                    throw new XActionException(String.format("Unable to flush changes to reference, %s.", modelObject.getType()), ex);
                }
            }
        }
        return null;
    }
}
