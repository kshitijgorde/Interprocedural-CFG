// 
// Decompiled by Procyon v0.5.30
// 

package org.xmodel.xaction.trigger;

import org.xmodel.Xlate;
import org.xmodel.xaction.XActionDocument;
import org.xmodel.IModelObject;
import java.util.List;
import org.xmodel.xpath.expression.IContext;
import org.xmodel.xpath.expression.ExpressionListener;
import org.xmodel.log.Log;
import org.xmodel.xaction.ScriptAction;
import org.xmodel.xpath.expression.IExpression;
import org.xmodel.xpath.expression.IExpressionListener;

public class WhenTrigger extends AbstractTrigger
{
    final IExpressionListener F;
    private IExpression E;
    private ScriptAction B;
    private boolean A;
    private boolean G;
    private boolean D;
    private static Log C;
    
    static {
        WhenTrigger.C = Log.getLog("org.xmodel.xaction.trigger");
    }
    
    public WhenTrigger() {
        this.F = new ExpressionListener() {
            @Override
            public void notifyAdd(final IExpression expression, final IContext context, final List<IModelObject> list) {
            }
            
            @Override
            public void notifyRemove(final IExpression expression, final IContext context, final List<IModelObject> list) {
            }
            
            @Override
            public void notifyChange(final IExpression expression, final IContext b, final boolean c) {
                if (WhenTrigger.this.D) {
                    return;
                }
                if (c) {
                    WhenTrigger.A(WhenTrigger.this, true);
                    try {
                        final _A a = new _A((_A)null);
                        a.B = b;
                        a.C = c;
                        b.getModel().dispatch(a);
                    }
                    finally {
                        WhenTrigger.A(WhenTrigger.this, false);
                    }
                    WhenTrigger.A(WhenTrigger.this, false);
                }
            }
            
            @Override
            public void notifyChange(final IExpression expression, final IContext context, final double n, final double n2) {
            }
            
            @Override
            public void notifyChange(final IExpression expression, final IContext context, final String s, final String s2) {
            }
            
            @Override
            public void notifyValue(final IExpression expression, final IContext[] array, final IModelObject modelObject, final Object o, final Object o2) {
            }
            
            @Override
            public boolean requiresValueNotification() {
                return false;
            }
        };
    }
    
    @Override
    public void configure(final XActionDocument xActionDocument) {
        super.configure(xActionDocument);
        this.E = xActionDocument.getExpression("when", true);
        this.B = xActionDocument.createScript("initialize", "finalize", "source", "when");
        this.A = Xlate.get(xActionDocument.getRoot(), "initialize", false);
        this.G = Xlate.get(xActionDocument.getRoot(), "finalize", false);
        this.A = Xlate.get(xActionDocument.getRoot().getFirstChild("initialize"), this.A);
        this.G = Xlate.get(xActionDocument.getRoot().getFirstChild("finalize"), this.G);
    }
    
    @Override
    public void activate(final IContext context) {
        if (this.A) {
            this.E.addNotifyListener(context, this.F);
        }
        else {
            this.E.addListener(context, this.F);
        }
    }
    
    @Override
    public void deactivate(final IContext context) {
        if (this.G) {
            this.E.removeNotifyListener(context, this.F);
        }
        else {
            this.E.removeListener(context, this.F);
        }
    }
    
    static /* synthetic */ void A(final WhenTrigger whenTrigger, final boolean d) {
        whenTrigger.D = d;
    }
    
    private class _A implements Runnable
    {
        IContext B;
        boolean C;
        
        @Override
        public void run() {
            WhenTrigger.C.debugf("Trigger notifyChange( %s): %s", Boolean.toString(this.C), WhenTrigger.this.toString());
            WhenTrigger.this.B.run(this.B);
        }
    }
}
