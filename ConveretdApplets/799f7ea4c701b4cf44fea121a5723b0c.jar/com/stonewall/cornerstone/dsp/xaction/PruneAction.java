// 
// Decompiled by Procyon v0.5.30
// 

package com.stonewall.cornerstone.dsp.xaction;

import java.util.Iterator;
import java.util.List;
import com.stonewall.cornerstone.entity.policy.ContentExpander;
import org.xmodel.IModelObject;
import org.xmodel.xpath.expression.IContext;
import org.xmodel.xaction.XActionDocument;
import org.xmodel.xpath.expression.IExpression;
import com.stonewall.cornerstone.utility.ILog;
import org.xmodel.xaction.GuardedAction;

public class PruneAction extends GuardedAction implements ILog
{
    private IExpression nodeSet;
    
    @Override
    public void configure(final XActionDocument document) {
        this.nodeSet = document.getExpression();
    }
    
    @Override
    protected Object[] doAction(final IContext context) {
        final List<IModelObject> nodes = this.nodeSet.evaluateNodes(context);
        for (final IModelObject o : nodes) {
            final ContentExpander r = new ContentExpander();
            r.prune(o);
        }
        return null;
    }
}
