// 
// Decompiled by Procyon v0.5.30
// 

package org.xidget.feature.model;

import org.xmodel.xpath.expression.IContext;
import org.xmodel.xpath.expression.StatefulContext;
import org.xidget.ifeature.IScriptFeature;
import org.xidget.ifeature.IBindFeature;
import org.xidget.ifeature.model.ISingleValueModelFeature;
import org.xidget.ifeature.model.ISingleValueWidgetFeature;
import org.xmodel.xpath.expression.IExpression;
import org.xidget.IXidget;
import org.xidget.ifeature.model.ISingleValueUpdateFeature;

public class SingleValueUpdateFeature implements ISingleValueUpdateFeature
{
    protected IXidget xidget;
    private IExpression commitExpr;
    private IExpression displayExpr;
    private boolean updating;
    
    public SingleValueUpdateFeature(final IXidget xidget) {
        this.xidget = xidget;
    }
    
    @Override
    public void updateModel() {
        if (this.updating) {
            return;
        }
        this.updating = true;
        try {
            this.commit(this.xidget.getFeature(ISingleValueWidgetFeature.class).getValue());
        }
        finally {
            this.updating = false;
        }
        this.updating = false;
    }
    
    @Override
    public void updateWidget() {
        if (this.updating) {
            return;
        }
        this.updating = true;
        try {
            this.display(this.xidget.getFeature(ISingleValueModelFeature.class).getValue());
        }
        finally {
            this.updating = false;
        }
        this.updating = false;
    }
    
    @Override
    public void setDisplayTransform(final IExpression displayExpr) {
        this.displayExpr = displayExpr;
    }
    
    @Override
    public void setCommitTransform(final IExpression commitExpr) {
        this.commitExpr = commitExpr;
    }
    
    @Override
    public void commit(Object model) {
        model = this.toModel(model);
        if (model != null) {
            final ISingleValueModelFeature singleValueModelFeature = this.xidget.getFeature(ISingleValueModelFeature.class);
            final Object value = singleValueModelFeature.getValue();
            if (value == null || model == null) {
                if (value != model) {
                    singleValueModelFeature.setValue(model);
                }
            }
            else if (!value.equals(model)) {
                singleValueModelFeature.setValue(model);
            }
        }
    }
    
    @Override
    public void display(Object display) {
        display = this.toDisplay(display);
        if (display != null) {
            final ISingleValueWidgetFeature singleValueWidgetFeature = this.xidget.getFeature(ISingleValueWidgetFeature.class);
            final Object value = singleValueWidgetFeature.getValue();
            if (value == null || display == null) {
                if (value != display) {
                    singleValueWidgetFeature.setValue(display);
                }
            }
            else if (!value.equals(display)) {
                singleValueWidgetFeature.setValue(display);
            }
        }
    }
    
    @Override
    public Object toDisplay(Object performTransform) {
        final StatefulContext boundContext = this.xidget.getFeature(IBindFeature.class).getBoundContext();
        if (boundContext == null) {
            return null;
        }
        if (this.displayExpr != null) {
            boundContext.getScope().set("value", performTransform);
            performTransform = this.performTransform(boundContext, this.displayExpr, performTransform);
        }
        final IScriptFeature scriptFeature = this.xidget.getFeature(IScriptFeature.class);
        if (scriptFeature.hasScript("onGet")) {
            boundContext.getScope().set("value", performTransform);
            final Object[] runScript = scriptFeature.runScript("onGet", boundContext);
            performTransform = ((runScript != null && runScript.length > 0) ? runScript[0] : null);
        }
        return performTransform;
    }
    
    @Override
    public Object toModel(Object performTransform) {
        final StatefulContext boundContext = this.xidget.getFeature(IBindFeature.class).getBoundContext();
        if (boundContext == null) {
            return null;
        }
        if (this.commitExpr != null) {
            boundContext.getScope().set("value", performTransform);
            performTransform = this.performTransform(boundContext, this.commitExpr, performTransform);
        }
        final IScriptFeature scriptFeature = this.xidget.getFeature(IScriptFeature.class);
        if (scriptFeature.hasScript("onSet")) {
            boundContext.getScope().set("value", performTransform);
            final Object[] runScript = scriptFeature.runScript("onSet", boundContext);
            performTransform = ((runScript != null && runScript.length > 0) ? runScript[0] : null);
        }
        return performTransform;
    }
    
    private Object performTransform(final StatefulContext statefulContext, final IExpression expression, final Object o) {
        switch (expression.getType(statefulContext)) {
            case NUMBER: {
                return expression.evaluateNumber(statefulContext);
            }
            case BOOLEAN: {
                return expression.evaluateBoolean(statefulContext);
            }
            case NODES:
            case STRING: {
                return expression.evaluateString(statefulContext);
            }
            default: {
                return null;
            }
        }
    }
}
