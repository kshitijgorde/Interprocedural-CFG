// 
// Decompiled by Procyon v0.5.30
// 

package org.xmodel.xaction;

import java.util.Iterator;
import org.xmodel.xaction.trigger.ITrigger;
import org.xmodel.IModelObject;
import java.util.List;
import org.xmodel.xpath.expression.IContext;
import org.xmodel.xpath.expression.IExpression;

public class CancelTriggerAction extends GuardedAction
{
    private IExpression \u00ea;
    
    @Override
    public void configure(final XActionDocument xActionDocument) {
        super.configure(xActionDocument);
        this.\u00ea = xActionDocument.getExpression();
    }
    
    @Override
    protected Object[] doAction(final IContext context) {
        final Iterator<IModelObject> iterator = this.\u00ea.query(context, null).iterator();
        while (iterator.hasNext()) {
            final ITrigger trigger = (ITrigger)iterator.next().getValue();
            if (trigger != null) {
                trigger.deactivate(context);
            }
        }
        return null;
    }
}
