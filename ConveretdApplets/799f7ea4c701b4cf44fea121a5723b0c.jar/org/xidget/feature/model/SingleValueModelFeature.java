// 
// Decompiled by Procyon v0.5.30
// 

package org.xidget.feature.model;

import java.util.List;
import org.xidget.ifeature.IBindFeature;
import org.xmodel.IModelObject;
import org.xidget.IXidget;
import org.xidget.ifeature.model.ISingleValueModelFeature;

public class SingleValueModelFeature implements ISingleValueModelFeature
{
    protected IXidget xidget;
    private IModelObject node;
    private String variable;
    
    public SingleValueModelFeature(final IXidget xidget) {
        this.xidget = xidget;
    }
    
    @Override
    public void setSourceNode(final IModelObject node) {
        if (this.node == node) {
            return;
        }
        this.node = node;
    }
    
    @Override
    public void setSourceVariable(final String variable) {
        this.variable = variable;
    }
    
    @Override
    public Object getValue() {
        if (this.node != null) {
            return this.node.getValue();
        }
        if (this.variable != null) {
            final Object value = this.xidget.getFeature(IBindFeature.class).getBoundContext().get(this.variable);
            if (!(value instanceof List)) {
                return value;
            }
        }
        return null;
    }
    
    @Override
    public void setValue(final Object value) {
        if (this.node != null) {
            this.node.setValue(value);
        }
        if (this.variable != null) {
            this.xidget.getFeature(IBindFeature.class).getBoundContext().getScope().set(this.variable, value);
        }
    }
}
