// 
// Decompiled by Procyon v0.5.30
// 

package org.xmodel.xaction;

import java.util.Iterator;
import java.util.List;
import org.xmodel.IChangeSet;
import org.xmodel.ModelAlgorithms;
import org.xmodel.xpath.expression.IContext;
import org.xmodel.IModelObject;
import org.xmodel.Xlate;
import org.xmodel.IModelObjectFactory;
import org.xmodel.xpath.expression.IExpression;

public class MoveAction extends GuardedAction
{
    private IExpression i;
    private IExpression k;
    private IExpression g;
    private IModelObjectFactory f;
    private boolean h;
    private boolean j;
    
    @Override
    public void configure(final XActionDocument xActionDocument) {
        super.configure(xActionDocument);
        final IModelObject root = xActionDocument.getRoot();
        this.f = this.getFactory(root);
        this.i = xActionDocument.getExpression("source", true);
        this.k = xActionDocument.getExpression("target", true);
        this.g = xActionDocument.getExpression("index", true);
        if (this.i == null) {
            this.i = xActionDocument.getExpression();
        }
        if (this.k == null) {
            this.k = xActionDocument.getExpression();
        }
        this.h = Xlate.get(root, "create", Xlate.childGet(root, "create", false));
        this.j = Xlate.get(root, "unique", Xlate.childGet(root, "unique", false));
    }
    
    @Override
    protected Object[] doAction(final IContext context) {
        if (this.h) {
            ModelAlgorithms.createPathSubtree(context, this.k, this.f, null);
        }
        final List<IModelObject> query = this.i.query(context, null);
        if (query.size() == 0) {
            return null;
        }
        final List<IModelObject> query2 = this.k.query(context, null);
        if (query2.size() == 0) {
            return null;
        }
        int n = -1;
        if (this.g != null) {
            n = (int)this.g.evaluateNumber(context);
        }
        for (final IModelObject modelObject : query2) {
            int n2 = (n < 0) ? modelObject.getNumberOfChildren() : n;
            for (final IModelObject modelObject2 : query) {
                if (!this.j || modelObject.getChild(modelObject2.getType(), modelObject2.getID()) == null) {
                    modelObject.addChild(modelObject2, n2);
                }
                ++n2;
            }
        }
        return null;
    }
}
