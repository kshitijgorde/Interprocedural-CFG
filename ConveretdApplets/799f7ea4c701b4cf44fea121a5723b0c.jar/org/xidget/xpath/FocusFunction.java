// 
// Decompiled by Procyon v0.5.30
// 

package org.xidget.xpath;

import org.xmodel.xpath.expression.ExpressionException;
import org.xidget.IXidget;
import java.util.Collections;
import org.xidget.Creator;
import org.xidget.ifeature.IFocusFeature;
import org.xmodel.IModelObject;
import java.util.List;
import org.xmodel.xpath.expression.IContext;
import org.xmodel.xpath.expression.IExpression;
import org.xmodel.xpath.function.Function;

public class FocusFunction extends Function
{
    public static final String name = "xi:focus";
    
    @Override
    public String getName() {
        return "xi:focus";
    }
    
    @Override
    public ResultType getType() {
        return ResultType.NODES;
    }
    
    @Override
    public List<IModelObject> evaluateNodes(final IContext context) throws ExpressionException {
        this.assertArgs(0, 0);
        final IFocusFeature focusFeature = Creator.getToolkit().getFeature(IFocusFeature.class);
        if (focusFeature != null) {
            final IXidget focus = focusFeature.getFocus();
            if (focus != null) {
                return Collections.singletonList(focus.getConfig());
            }
        }
        return Collections.emptyList();
    }
    
    @Override
    public void bind(final IContext context) {
        final IFocusFeature focusFeature = Creator.getToolkit().getFeature(IFocusFeature.class);
        if (focusFeature != null) {
            focusFeature.addFocusListener(new FocusListener(context));
        }
    }
    
    @Override
    public void unbind(final IContext context) {
        final IFocusFeature focusFeature = Creator.getToolkit().getFeature(IFocusFeature.class);
        if (focusFeature != null) {
            focusFeature.removeFocusListener(new FocusListener(context));
        }
    }
    
    private class FocusListener implements IFocusFeature.IFocusListener
    {
        private IContext context;
        
        public FocusListener(final IContext context) {
            this.context = context;
        }
        
        @Override
        public void notifyFocus(final IXidget xidget, final IXidget xidget2) {
            FocusFunction.this.getParent().notifyRemove(FocusFunction.this, this.context, Collections.singletonList(xidget2.getConfig()));
            FocusFunction.this.getParent().notifyAdd(FocusFunction.this, this.context, Collections.singletonList(xidget.getConfig()));
        }
        
        @Override
        public boolean equals(final Object o) {
            return o instanceof FocusListener && ((FocusListener)o).context.equals(this.context);
        }
        
        @Override
        public int hashCode() {
            return this.context.hashCode();
        }
    }
}
