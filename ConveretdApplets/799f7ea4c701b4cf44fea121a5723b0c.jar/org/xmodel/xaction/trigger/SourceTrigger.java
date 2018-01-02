// 
// Decompiled by Procyon v0.5.30
// 

package org.xmodel.xaction.trigger;

import org.xmodel.xpath.variable.IVariableScope;
import java.util.Collections;
import org.xmodel.Xlate;
import org.xmodel.xaction.XActionDocument;
import java.util.Collection;
import java.util.ArrayList;
import org.xmodel.IModelObject;
import java.util.List;
import org.xmodel.xpath.expression.IContext;
import org.xmodel.xpath.expression.ExpressionListener;
import org.xmodel.log.Log;
import org.xmodel.xaction.ScriptAction;
import org.xmodel.xpath.expression.IExpression;
import org.xmodel.xpath.expression.IExpressionListener;

public class SourceTrigger extends AbstractTrigger
{
    final IExpressionListener N;
    private IExpression L;
    private ScriptAction I;
    private boolean H;
    private boolean M;
    private boolean K;
    private static Log J;
    
    static {
        SourceTrigger.J = Log.getLog("org.xmodel.xaction.trigger");
    }
    
    public SourceTrigger() {
        this.N = new ExpressionListener() {
            @Override
            public void notifyAdd(final IExpression expression, final IContext c, final List<IModelObject> list) {
                if (SourceTrigger.this.K) {
                    return;
                }
                SourceTrigger.A(SourceTrigger.this, true);
                try {
                    final _E e = new _E((_E)null);
                    e.C = c;
                    e.B = new ArrayList<IModelObject>(list);
                    c.getModel().dispatch(e);
                }
                finally {
                    SourceTrigger.A(SourceTrigger.this, false);
                }
                SourceTrigger.A(SourceTrigger.this, false);
            }
            
            @Override
            public void notifyRemove(final IExpression expression, final IContext c, final List<IModelObject> list) {
                if (SourceTrigger.this.K) {
                    return;
                }
                SourceTrigger.A(SourceTrigger.this, true);
                try {
                    final _B b = new _B((_B)null);
                    b.C = c;
                    b.B = new ArrayList<IModelObject>(list);
                    c.getModel().dispatch(b);
                }
                finally {
                    SourceTrigger.A(SourceTrigger.this, false);
                }
                SourceTrigger.A(SourceTrigger.this, false);
            }
            
            @Override
            public void notifyChange(final IExpression expression, final IContext b, final boolean c) {
                if (SourceTrigger.this.K) {
                    return;
                }
                SourceTrigger.A(SourceTrigger.this, true);
                try {
                    final _C c2 = new _C((_C)null);
                    c2.B = b;
                    c2.C = c;
                    b.getModel().dispatch(c2);
                }
                finally {
                    SourceTrigger.A(SourceTrigger.this, false);
                }
                SourceTrigger.A(SourceTrigger.this, false);
            }
            
            @Override
            public void notifyChange(final IExpression expression, final IContext c, final double d, final double b) {
                if (SourceTrigger.this.K) {
                    return;
                }
                SourceTrigger.A(SourceTrigger.this, true);
                try {
                    final _F f = new _F((_F)null);
                    f.C = c;
                    f.D = d;
                    f.B = b;
                    c.getModel().dispatch(f);
                }
                finally {
                    SourceTrigger.A(SourceTrigger.this, false);
                }
                SourceTrigger.A(SourceTrigger.this, false);
            }
            
            @Override
            public void notifyChange(final IExpression expression, final IContext c, final String d, final String b) {
                if (SourceTrigger.this.K) {
                    return;
                }
                SourceTrigger.A(SourceTrigger.this, true);
                try {
                    final _D d2 = new _D((_D)null);
                    d2.C = c;
                    d2.D = d;
                    d2.B = b;
                    c.getModel().dispatch(d2);
                }
                finally {
                    SourceTrigger.A(SourceTrigger.this, false);
                }
                SourceTrigger.A(SourceTrigger.this, false);
            }
            
            @Override
            public void notifyValue(final IExpression expression, final IContext[] array, final IModelObject d, final Object o, final Object o2) {
                if (SourceTrigger.this.K) {
                    return;
                }
                SourceTrigger.A(SourceTrigger.this, true);
                try {
                    final _A a = new _A((_A)null);
                    a.C = array[0];
                    a.D = d;
                    a.E = ((o != null) ? o.toString() : "");
                    a.B = ((o2 != null) ? o2.toString() : "");
                    array[0].getModel().dispatch(a);
                }
                finally {
                    SourceTrigger.A(SourceTrigger.this, false);
                }
                SourceTrigger.A(SourceTrigger.this, false);
            }
            
            @Override
            public boolean requiresValueNotification() {
                return true;
            }
        };
    }
    
    @Override
    public void configure(final XActionDocument xActionDocument) {
        super.configure(xActionDocument);
        this.L = xActionDocument.getExpression("source", true);
        this.I = xActionDocument.createScript("initialize", "finalize", "source");
        this.H = Xlate.get(xActionDocument.getRoot(), "initialize", false);
        this.M = Xlate.get(xActionDocument.getRoot(), "finalize", false);
        this.H = Xlate.get(xActionDocument.getRoot().getFirstChild("initialize"), this.H);
        this.M = Xlate.get(xActionDocument.getRoot().getFirstChild("finalize"), this.M);
    }
    
    @Override
    public void activate(final IContext context) {
        if (this.H) {
            this.L.addNotifyListener(context, this.N);
        }
        else {
            this.L.addListener(context, this.N);
        }
    }
    
    @Override
    public void deactivate(final IContext context) {
        if (this.M) {
            this.L.removeNotifyListener(context, this.N);
        }
        else {
            this.L.removeListener(context, this.N);
        }
    }
    
    private void A(final IContext context, final String s, final List<IModelObject> list) {
        final IVariableScope scope = context.getScope();
        if (scope != null) {
            scope.set("added", Collections.emptyList());
            scope.set("removed", Collections.emptyList());
            scope.set("updated", "");
            scope.set(s, list);
        }
    }
    
    static /* synthetic */ void A(final SourceTrigger sourceTrigger, final boolean k) {
        sourceTrigger.K = k;
    }
    
    private class _E implements Runnable
    {
        IContext C;
        List<IModelObject> B;
        
        @Override
        public void run() {
            SourceTrigger.J.debugf("Trigger notifyAdd( %d nodes): %s", this.B.size(), SourceTrigger.this);
            SourceTrigger.this.A(this.C, "added", this.B);
            SourceTrigger.this.I.run(this.C);
        }
    }
    
    private class _C implements Runnable
    {
        IContext B;
        boolean C;
        
        @Override
        public void run() {
            SourceTrigger.J.debugf("Trigger notifyChange( %s): %s", Boolean.toString(this.C), SourceTrigger.this);
            SourceTrigger.this.I.run(this.B);
        }
    }
    
    private class _F implements Runnable
    {
        IContext C;
        double B;
        double D;
        
        @Override
        public void run() {
            SourceTrigger.J.debugf("Trigger notifyChange( %f, %f): %s", this.D, this.B, SourceTrigger.this);
            SourceTrigger.this.I.run(this.C);
        }
    }
    
    private class _B implements Runnable
    {
        IContext C;
        List<IModelObject> B;
        
        @Override
        public void run() {
            SourceTrigger.J.debugf("Trigger notifyRemove( %d nodes): %s", this.B.size(), SourceTrigger.this);
            SourceTrigger.this.A(this.C, "removed", this.B);
            SourceTrigger.this.I.run(this.C);
        }
    }
    
    private class _D implements Runnable
    {
        IContext C;
        String B;
        String D;
        
        @Override
        public void run() {
            SourceTrigger.J.debugf("Trigger notifyChange( %s, %s): %s", this.D, this.B, SourceTrigger.this);
            SourceTrigger.this.I.run(this.C);
        }
    }
    
    private class _A implements Runnable
    {
        IContext C;
        IModelObject D;
        String B;
        String E;
        
        @Override
        public void run() {
            SourceTrigger.J.debugf("Trigger notifyChange( %s, %s): %s", this.E, this.B, SourceTrigger.this);
            SourceTrigger.this.A(this.C, "updated", Collections.singletonList(this.D));
            SourceTrigger.this.I.run(this.C);
        }
    }
}
