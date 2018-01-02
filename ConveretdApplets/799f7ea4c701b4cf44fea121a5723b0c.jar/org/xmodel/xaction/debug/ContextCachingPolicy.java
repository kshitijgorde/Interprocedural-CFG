// 
// Decompiled by Procyon v0.5.30
// 

package org.xmodel.xaction.debug;

import org.xmodel.xpath.expression.IExpression;
import org.xmodel.external.CachingException;
import java.util.Iterator;
import org.xmodel.xpath.variable.IVariableScope;
import org.xmodel.IModelObject;
import java.util.List;
import org.xmodel.ModelObject;
import org.xmodel.external.IExternalReference;
import org.xmodel.external.ICache;
import org.xmodel.external.UnboundedCache;
import org.xmodel.xpath.expression.IContext;
import org.xmodel.external.AbstractCachingPolicy;

public class ContextCachingPolicy extends AbstractCachingPolicy
{
    private IContext £;
    private static /* synthetic */ int[] ¢;
    
    public ContextCachingPolicy(final IContext £) {
        super(new UnboundedCache());
        this.£ = £;
    }
    
    @Override
    public void sync(final IExternalReference externalReference) throws CachingException {
        final IVariableScope scope = this.£.getScope();
        final String id = externalReference.getID();
        final ModelObject modelObject = new ModelObject(externalReference.getType(), id);
        switch (A()[scope.getType(id).ordinal()]) {
            case 1: {
                final Iterator iterator = ((List)scope.get(id)).iterator();
                while (iterator.hasNext()) {
                    modelObject.addChild(iterator.next().cloneTree());
                }
                break;
            }
            case 2:
            case 3:
            case 4: {
                modelObject.setValue(scope.get(id));
                break;
            }
        }
        this.update(externalReference, modelObject);
    }
    
    static /* synthetic */ int[] A() {
        final int[] ¢ = ContextCachingPolicy.¢;
        if (¢ != null) {
            return ¢;
        }
        final int[] ¢2 = new int[IExpression.ResultType.values().length];
        try {
            ¢2[IExpression.ResultType.BOOLEAN.ordinal()] = 4;
        }
        catch (NoSuchFieldError noSuchFieldError) {}
        try {
            ¢2[IExpression.ResultType.NODES.ordinal()] = 1;
        }
        catch (NoSuchFieldError noSuchFieldError2) {}
        try {
            ¢2[IExpression.ResultType.NUMBER.ordinal()] = 3;
        }
        catch (NoSuchFieldError noSuchFieldError3) {}
        try {
            ¢2[IExpression.ResultType.STRING.ordinal()] = 2;
        }
        catch (NoSuchFieldError noSuchFieldError4) {}
        try {
            ¢2[IExpression.ResultType.UNDEFINED.ordinal()] = 5;
        }
        catch (NoSuchFieldError noSuchFieldError5) {}
        return ContextCachingPolicy.¢ = ¢2;
    }
}
