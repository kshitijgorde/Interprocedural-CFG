// 
// Decompiled by Procyon v0.5.30
// 

package org.xmodel;

import org.xmodel.A.G;
import org.xmodel.A.B;
import java.util.Set;
import java.util.List;
import java.util.Collection;

public class Reference implements IModelObject
{
    private IModelObject _;
    private IModelObject a;
    
    public Reference(final IModelObject a) {
        this.a = a;
    }
    
    @Override
    public IModel getModel() {
        return this.a.getModel();
    }
    
    @Override
    public String getID() {
        return this.a.getID();
    }
    
    @Override
    public void setID(final String id) {
        this.a.setID(id);
    }
    
    @Override
    public String getType() {
        return this.a.getType();
    }
    
    @Override
    public boolean isType(final String s) {
        return this.a.isType(s);
    }
    
    @Override
    public boolean isDirty() {
        return this.a.isDirty();
    }
    
    @Override
    public Object setAttribute(final String attribute) {
        return this.a.setAttribute(attribute);
    }
    
    @Override
    public Object setAttribute(final String s, final Object o) {
        return this.a.setAttribute(s, o);
    }
    
    @Override
    public Object getAttribute(final String s) {
        return this.a.getAttribute(s);
    }
    
    @Override
    public IModelObject getAttributeNode(final String s) {
        return this.a.getAttributeNode(s);
    }
    
    @Override
    public Collection<String> getAttributeNames() {
        return this.a.getAttributeNames();
    }
    
    @Override
    public Object removeAttribute(final String s) {
        return this.a.removeAttribute(s);
    }
    
    @Override
    public Object setValue(final Object value) {
        return this.a.setValue(value);
    }
    
    @Override
    public Object getValue() {
        return this.a.getValue();
    }
    
    @Override
    public Object getChildValue(final String s) {
        return this.a.getChildValue(s);
    }
    
    @Override
    public void addChild(final IModelObject modelObject) {
        this.a.addChild(modelObject);
    }
    
    @Override
    public void addChild(final IModelObject modelObject, final int n) {
        this.a.addChild(modelObject, n);
    }
    
    @Override
    public IModelObject removeChild(final int n) {
        return this.a.removeChild(n);
    }
    
    @Override
    public void removeChild(final IModelObject modelObject) {
        this.a.removeChild(modelObject);
    }
    
    @Override
    public void removeChildren() {
        this.a.removeChildren();
    }
    
    @Override
    public void removeChildren(final String s) {
        this.a.removeChildren(s);
    }
    
    @Override
    public void removeFromParent() {
        if (this._ != null) {
            this._.removeChild(this);
        }
    }
    
    @Override
    public IModelObject getChild(final int n) {
        return this.a.getChild(n);
    }
    
    @Override
    public IModelObject getFirstChild(final String s) {
        return this.a.getFirstChild(s);
    }
    
    @Override
    public IModelObject getChild(final String s, final String s2) {
        return this.a.getChild(s, s2);
    }
    
    @Override
    public IModelObject getCreateChild(final String s) {
        return this.a.getCreateChild(s);
    }
    
    @Override
    public IModelObject getCreateChild(final String s, final String s2) {
        return this.a.getCreateChild(s, s2);
    }
    
    @Override
    public List<IModelObject> getChildren(final String s, final String s2) {
        return this.a.getChildren(s, s2);
    }
    
    @Override
    public List<IModelObject> getChildren() {
        return this.a.getChildren();
    }
    
    @Override
    public List<IModelObject> getChildren(final String s) {
        return this.a.getChildren(s);
    }
    
    @Override
    public Set<String> getTypesOfChildren() {
        return this.a.getTypesOfChildren();
    }
    
    @Override
    public int getNumberOfChildren() {
        return this.a.getNumberOfChildren();
    }
    
    @Override
    public int getNumberOfChildren(final String s) {
        return this.a.getNumberOfChildren(s);
    }
    
    @Override
    public IModelObject internal_setParent(final IModelObject _) {
        final IModelObject _2 = this._;
        this._ = _;
        return _2;
    }
    
    @Override
    public void internal_notifyParent(final IModelObject modelObject, final IModelObject modelObject2) {
    }
    
    @Override
    public void internal_notifyAddChild(final IModelObject modelObject, final int n) {
        this.a.internal_notifyAddChild(modelObject, n);
    }
    
    @Override
    public void internal_notifyRemoveChild(final IModelObject modelObject, final int n) {
        this.a.internal_notifyRemoveChild(modelObject, n);
    }
    
    @Override
    public void internal_addChild(final IModelObject modelObject, final int n) {
        this.a.internal_addChild(modelObject, n);
    }
    
    @Override
    public IModelObject internal_removeChild(final int n) {
        return this.a.internal_removeChild(n);
    }
    
    @Override
    public IModelObject getParent() {
        return this._;
    }
    
    @Override
    public IModelObject getAncestor(final String s) {
        if (this._.isType(s)) {
            return this._;
        }
        return this._.getAncestor(s);
    }
    
    @Override
    public IModelObject getRoot() {
        if (this._ == null) {
            return this;
        }
        return this._.getRoot();
    }
    
    @Override
    public void addModelListener(final IModelListener modelListener) {
        this.a.addModelListener(modelListener);
    }
    
    @Override
    public void removeModelListener(final IModelListener modelListener) {
        this.a.removeModelListener(modelListener);
    }
    
    @Override
    public F getModelListeners() {
        return this.a.getModelListeners();
    }
    
    @Override
    public C getPathListeners() {
        return this.a.getPathListeners();
    }
    
    @Override
    public void addAncestorListener(final IAncestorListener ancestorListener) {
        this.a.addAncestorListener(ancestorListener);
    }
    
    @Override
    public void removeAncestorListener(final IAncestorListener ancestorListener) {
        this.a.removeAncestorListener(ancestorListener);
    }
    
    @Override
    public IModelObject cloneObject() {
        return this.a.cloneObject();
    }
    
    @Override
    public IModelObject cloneTree() {
        return this.a.cloneTree();
    }
    
    @Override
    public IModelObject createObject(final String s) {
        return this.a.createObject(s);
    }
    
    @Override
    public IModelObject getReferent() {
        return this.a;
    }
    
    @Override
    public void revertUpdate(final B b) {
        if (b instanceof G) {
            this._ = ((G)b).O;
        }
        else {
            this.a.revertUpdate(b);
        }
    }
    
    @Override
    public void restoreUpdate(final B b) {
        if (b instanceof G) {
            this._ = ((G)b).P;
        }
        else {
            this.a.restoreUpdate(b);
        }
    }
    
    public static IModelObject getReferent(IModelObject modelObject) {
        IModelObject modelObject2;
        for (modelObject2 = modelObject.getReferent(); modelObject2 != modelObject; modelObject = modelObject2, modelObject2 = modelObject.getReferent()) {}
        return modelObject2;
    }
    
    @Override
    public boolean equals(final Object o) {
        try {
            if (this.a == null) {
                return super.equals(o);
            }
            return getReferent(this.a) == getReferent((IModelObject)o);
        }
        catch (ClassCastException ex) {
            return false;
        }
    }
    
    @Override
    public int hashCode() {
        if (this.a == null) {
            return super.hashCode();
        }
        return getReferent(this.a).hashCode();
    }
    
    @Override
    public String toString() {
        return "&" + this.a;
    }
}
