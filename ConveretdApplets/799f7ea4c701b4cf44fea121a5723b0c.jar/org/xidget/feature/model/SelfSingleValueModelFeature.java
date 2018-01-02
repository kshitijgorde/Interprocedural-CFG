// 
// Decompiled by Procyon v0.5.30
// 

package org.xidget.feature.model;

import org.xmodel.external.NonSyncingListener;
import org.xidget.ifeature.model.ISingleValueUpdateFeature;
import org.xmodel.diff.IXmlMatcher;
import org.xmodel.diff.DefaultXmlMatcher;
import org.xmodel.diff.XmlDiffer;
import org.xmodel.IModelObject;
import org.xidget.IXidget;
import org.xidget.ifeature.model.ISingleValueModelFeature;

public class SelfSingleValueModelFeature implements ISingleValueModelFeature
{
    private IXidget xidget;
    private IModelObject node;
    private XmlDiffer differ;
    private Listener listener;
    
    public SelfSingleValueModelFeature(final IXidget xidget) {
        this.xidget = xidget;
        this.differ = new XmlDiffer(new DefaultXmlMatcher(true));
        this.listener = new Listener((Listener)null);
    }
    
    @Override
    public void setSourceNode(final IModelObject node) {
        if (this.node == node) {
            return;
        }
        if (this.node != null) {
            this.listener.uninstall(this.node);
        }
        if (node != null) {
            this.listener.install(node);
        }
        this.node = node;
        this.xidget.getFeature(ISingleValueUpdateFeature.class).display(this.getValue());
    }
    
    @Override
    public void setSourceVariable(final String s) {
    }
    
    @Override
    public Object getValue() {
        return this.node;
    }
    
    @Override
    public void setValue(final Object o) {
        if (!(o instanceof IModelObject)) {
            return;
        }
        this.differ.diffAndApply(this.node, (IModelObject)o);
    }
    
    private class Listener extends NonSyncingListener
    {
        @Override
        public void notifyAddChild(final IModelObject modelObject, final IModelObject modelObject2, final int n) {
            super.notifyAddChild(modelObject, modelObject2, n);
            SelfSingleValueModelFeature.this.xidget.getFeature(ISingleValueUpdateFeature.class).updateWidget();
        }
        
        @Override
        public void notifyRemoveChild(final IModelObject modelObject, final IModelObject modelObject2, final int n) {
            super.notifyRemoveChild(modelObject, modelObject2, n);
            SelfSingleValueModelFeature.this.xidget.getFeature(ISingleValueUpdateFeature.class).updateWidget();
        }
        
        @Override
        public void notifyChange(final IModelObject modelObject, final String s, final Object o, final Object o2) {
            SelfSingleValueModelFeature.this.xidget.getFeature(ISingleValueUpdateFeature.class).updateWidget();
        }
        
        @Override
        public void notifyClear(final IModelObject modelObject, final String s, final Object o) {
            SelfSingleValueModelFeature.this.xidget.getFeature(ISingleValueUpdateFeature.class).updateWidget();
        }
    }
}
