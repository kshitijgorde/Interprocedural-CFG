// 
// Decompiled by Procyon v0.5.30
// 

package org.xmodel;

import org.xmodel.A.B;
import java.util.Set;
import java.util.List;
import java.util.Collections;
import java.util.Collection;

public class NullObject implements IModelObject
{
    @Override
    public boolean isDirty() {
        return false;
    }
    
    @Override
    public void addAncestorListener(final IAncestorListener ancestorListener) {
    }
    
    @Override
    public void addChild(final IModelObject modelObject) {
        throw new UnsupportedOperationException();
    }
    
    @Override
    public void addChild(final IModelObject modelObject, final int n) {
        throw new UnsupportedOperationException();
    }
    
    @Override
    public void addModelListener(final IModelListener modelListener) {
    }
    
    @Override
    public IModelObject cloneObject() {
        return new ModelObject(this.getType());
    }
    
    @Override
    public IModelObject cloneTree() {
        return this.cloneObject();
    }
    
    @Override
    public IModelObject createObject(final String s) {
        throw new UnsupportedOperationException();
    }
    
    @Override
    public IModelObject getAncestor(final String s) {
        return null;
    }
    
    @Override
    public Object getAttribute(final String s) {
        return null;
    }
    
    @Override
    public Collection<String> getAttributeNames() {
        return (Collection<String>)Collections.emptySet();
    }
    
    @Override
    public IModelObject getAttributeNode(final String s) {
        return null;
    }
    
    @Override
    public IModelObject getChild(final int n) {
        return null;
    }
    
    @Override
    public IModelObject getChild(final String s, final String s2) {
        return null;
    }
    
    @Override
    public Object getChildValue(final String s) {
        return "";
    }
    
    @Override
    public List<IModelObject> getChildren(final String s, final String s2) {
        return Collections.emptyList();
    }
    
    @Override
    public List<IModelObject> getChildren() {
        return Collections.emptyList();
    }
    
    @Override
    public List<IModelObject> getChildren(final String s) {
        return Collections.emptyList();
    }
    
    @Override
    public IModelObject getCreateChild(final String s) {
        throw new UnsupportedOperationException();
    }
    
    @Override
    public IModelObject getCreateChild(final String s, final String s2) {
        throw new UnsupportedOperationException();
    }
    
    @Override
    public IModelObject getFirstChild(final String s) {
        return null;
    }
    
    @Override
    public String getID() {
        return "";
    }
    
    @Override
    public IModel getModel() {
        return ModelRegistry.getInstance().getModel();
    }
    
    @Override
    public F getModelListeners() {
        return new F();
    }
    
    @Override
    public int getNumberOfChildren() {
        return 0;
    }
    
    @Override
    public int getNumberOfChildren(final String s) {
        return 0;
    }
    
    @Override
    public IModelObject getParent() {
        return null;
    }
    
    @Override
    public C getPathListeners() {
        return new C();
    }
    
    @Override
    public IModelObject getReferent() {
        return this;
    }
    
    @Override
    public IModelObject getRoot() {
        return null;
    }
    
    @Override
    public String getType() {
        return "";
    }
    
    @Override
    public Set<String> getTypesOfChildren() {
        return Collections.emptySet();
    }
    
    @Override
    public Object getValue() {
        return null;
    }
    
    @Override
    public IModelObject internal_setParent(final IModelObject modelObject) {
        throw new UnsupportedOperationException();
    }
    
    @Override
    public void internal_addChild(final IModelObject modelObject, final int n) {
        throw new UnsupportedOperationException();
    }
    
    @Override
    public void internal_notifyAddChild(final IModelObject modelObject, final int n) {
        throw new UnsupportedOperationException();
    }
    
    @Override
    public void internal_notifyParent(final IModelObject modelObject, final IModelObject modelObject2) {
        throw new UnsupportedOperationException();
    }
    
    @Override
    public void internal_notifyRemoveChild(final IModelObject modelObject, final int n) {
        throw new UnsupportedOperationException();
    }
    
    @Override
    public IModelObject internal_removeChild(final int n) {
        throw new UnsupportedOperationException();
    }
    
    @Override
    public boolean isType(final String s) {
        return s == null || s.length() == 0;
    }
    
    @Override
    public void removeAncestorListener(final IAncestorListener ancestorListener) {
    }
    
    @Override
    public Object removeAttribute(final String s) {
        return null;
    }
    
    @Override
    public IModelObject removeChild(final int n) {
        return null;
    }
    
    @Override
    public void removeChild(final IModelObject modelObject) {
    }
    
    @Override
    public void removeChildren() {
    }
    
    @Override
    public void removeChildren(final String s) {
    }
    
    @Override
    public void removeFromParent() {
    }
    
    @Override
    public void removeModelListener(final IModelListener modelListener) {
    }
    
    @Override
    public void restoreUpdate(final B b) {
    }
    
    @Override
    public void revertUpdate(final B b) {
    }
    
    @Override
    public Object setAttribute(final String s) {
        throw new UnsupportedOperationException();
    }
    
    @Override
    public Object setAttribute(final String s, final Object o) {
        throw new UnsupportedOperationException();
    }
    
    @Override
    public void setID(final String s) {
        throw new UnsupportedOperationException();
    }
    
    @Override
    public Object setValue(final Object o) {
        throw new UnsupportedOperationException();
    }
}
