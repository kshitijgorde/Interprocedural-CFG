// 
// Decompiled by Procyon v0.5.30
// 

package org.xmodel.xaction.trigger;

import org.xmodel.xaction.XActionDocument;
import java.util.HashSet;
import java.util.Collection;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.xmodel.xpath.expression.IContext;
import org.xmodel.xpath.expression.ExpressionListener;
import org.xmodel.log.Log;
import org.xmodel.IModelObject;
import java.util.Set;
import org.xmodel.xpath.expression.StatefulContext;
import org.xmodel.xaction.ScriptAction;
import org.xmodel.util.Aggregator;
import org.xmodel.xpath.expression.IExpression;
import org.xmodel.external.NonSyncingListener;
import org.xmodel.xpath.expression.IExpressionListener;

public class EntityTrigger extends AbstractTrigger
{
    final IExpressionListener X;
    private NonSyncingListener R;
    private final Runnable T;
    private IExpression V;
    private Aggregator P;
    private int S;
    private ScriptAction W;
    private StatefulContext Q;
    private Set<IModelObject> O;
    private static Log U;
    
    static {
        EntityTrigger.U = Log.getLog("org.xmodel.xaction.trigger");
    }
    
    public EntityTrigger() {
        this.X = new ExpressionListener() {
            @Override
            public void notifyAdd(final IExpression expression, final IContext context, final List<IModelObject> list) {
                final Iterator<IModelObject> iterator = list.iterator();
                while (iterator.hasNext()) {
                    EntityTrigger.this.R.install(iterator.next());
                }
                EntityTrigger.this.P.dispatch(EntityTrigger.this.S);
            }
            
            @Override
            public void notifyRemove(final IExpression expression, final IContext context, final List<IModelObject> list) {
                final Iterator<IModelObject> iterator = list.iterator();
                while (iterator.hasNext()) {
                    EntityTrigger.this.R.uninstall(iterator.next());
                }
                EntityTrigger.this.P.dispatch(EntityTrigger.this.S);
            }
        };
        this.R = new NonSyncingListener() {
            @Override
            public void notifyAddChild(final IModelObject modelObject, final IModelObject modelObject2, final int n) {
                super.notifyAddChild(modelObject, modelObject2, n);
                EntityTrigger.this.O.add(modelObject);
                EntityTrigger.this.P.dispatch(EntityTrigger.this.S);
            }
            
            @Override
            public void notifyRemoveChild(final IModelObject modelObject, final IModelObject modelObject2, final int n) {
                super.notifyRemoveChild(modelObject, modelObject2, n);
                EntityTrigger.this.O.add(modelObject);
                EntityTrigger.this.P.dispatch(EntityTrigger.this.S);
            }
            
            @Override
            public void notifyChange(final IModelObject modelObject, final String s, final Object o, final Object o2) {
                EntityTrigger.this.O.add(modelObject);
                EntityTrigger.this.P.dispatch(EntityTrigger.this.S);
            }
            
            @Override
            public void notifyClear(final IModelObject modelObject, final String s, final Object o) {
                EntityTrigger.this.O.add(modelObject);
                EntityTrigger.this.P.dispatch(EntityTrigger.this.S);
            }
        };
        this.T = new Runnable() {
            @Override
            public void run() {
                EntityTrigger.U.debugf("Trigger notifyUpdate(): %s", EntityTrigger.this.toString());
                EntityTrigger.this.Q.set("changes", new ArrayList<IModelObject>(EntityTrigger.this.O));
                EntityTrigger.this.W.run(EntityTrigger.this.Q);
                EntityTrigger.this.O.clear();
            }
        };
        this.O = new HashSet<IModelObject>();
    }
    
    @Override
    public void configure(final XActionDocument xActionDocument) {
        super.configure(xActionDocument);
        this.V = xActionDocument.getExpression("entity", true);
        this.W = xActionDocument.createScript("entity");
        this.P = new Aggregator(50L);
        this.S = this.P.add(xActionDocument.getRoot().getModel().getDispatcher(), this.T);
    }
    
    @Override
    public void activate(final IContext context) {
        if (context instanceof StatefulContext) {
            this.Q = (StatefulContext)context;
        }
        this.P.start();
        this.V.addNotifyListener(context, this.X);
    }
    
    @Override
    public void deactivate(final IContext context) {
        this.V.removeListener(context, this.X);
        this.P.stop();
    }
}
