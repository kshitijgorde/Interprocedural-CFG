// 
// Decompiled by Procyon v0.5.30
// 

package org.xmodel.xpath;

import org.xmodel.A.B;
import java.util.Set;
import org.xmodel.C;
import org.xmodel.F;
import java.util.Collections;
import java.util.List;
import java.util.ArrayList;
import java.util.Collection;
import org.xmodel.IPathListener;
import org.xmodel.IPath;
import org.xmodel.IModelListener;
import org.xmodel.IAncestorListener;
import org.xmodel.IModel;
import org.xmodel.IModelObject;

public class AttributeHistoryNode implements IModelObject
{
    String A;
    Object B;
    
    public AttributeHistoryNode(final String a, final Object b) {
        this.A = a;
        this.B = b;
    }
    
    @Override
    public IModel getModel() {
        return null;
    }
    
    @Override
    public String getID() {
        throw new UnsupportedOperationException();
    }
    
    @Override
    public void setID(final String s) {
    }
    
    @Override
    public void addAncestorListener(final IAncestorListener ancestorListener) {
    }
    
    @Override
    public void addChild(final IModelObject modelObject) {
    }
    
    @Override
    public void addChild(final IModelObject modelObject, final int n) {
    }
    
    @Override
    public IModelObject getChild(final int n) {
        return null;
    }
    
    @Override
    public IModelObject removeChild(final int n) {
        return null;
    }
    
    @Override
    public void addModelListener(final IModelListener modelListener) {
    }
    
    public void addModelListener(final int n, final IModelListener modelListener) {
    }
    
    public void addPathListener(final IPath path, final IPathListener pathListener) {
    }
    
    @Override
    public IModelObject cloneObject() {
        return new AttributeHistoryNode(this.A, this.B);
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
    public IModelObject getReferent() {
        return this;
    }
    
    @Override
    public IModelObject getAncestor(final String s) {
        return null;
    }
    
    @Override
    public Object getAttribute(final String s) {
        if (s.length() == 0 || s.equals(this.A)) {
            return this.B;
        }
        return null;
    }
    
    @Override
    public IModelObject getAttributeNode(final String s) {
        throw new UnsupportedOperationException();
    }
    
    @Override
    public Collection<String> getAttributeNames() {
        final ArrayList<String> list = new ArrayList<String>(1);
        list.add(this.A);
        return list;
    }
    
    @Override
    public IModelObject getChild(final String s, final String s2) {
        return null;
    }
    
    @Override
    public List<IModelObject> getChildren() {
        return Collections.emptyList();
    }
    
    @Override
    public List<IModelObject> getChildren(final String s, final String s2) {
        return Collections.emptyList();
    }
    
    @Override
    public List<IModelObject> getChildren(final String s) {
        return Collections.emptyList();
    }
    
    @Override
    public Object getValue() {
        return this.B;
    }
    
    public IModelObject getValueNode() {
        throw new UnsupportedOperationException();
    }
    
    @Override
    public Object getChildValue(final String s) {
        return null;
    }
    
    @Override
    public IModelObject getCreateChild(final String s, final String s2) {
        return null;
    }
    
    @Override
    public IModelObject getCreateChild(final String s) {
        return null;
    }
    
    @Override
    public IModelObject getFirstChild(final String s) {
        return null;
    }
    
    @Override
    public F getModelListeners() {
        return null;
    }
    
    @Override
    public C getPathListeners() {
        return null;
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
    public IModelObject getRoot() {
        return null;
    }
    
    @Override
    public String getType() {
        return this.A;
    }
    
    @Override
    public Set<String> getTypesOfChildren() {
        return Collections.emptySet();
    }
    
    @Override
    public IModelObject internal_setParent(final IModelObject modelObject) {
        return null;
    }
    
    @Override
    public void internal_notifyParent(final IModelObject modelObject, final IModelObject modelObject2) {
    }
    
    @Override
    public void internal_notifyAddChild(final IModelObject modelObject, final int n) {
    }
    
    @Override
    public void internal_notifyRemoveChild(final IModelObject modelObject, final int n) {
    }
    
    @Override
    public void internal_addChild(final IModelObject modelObject, final int n) {
    }
    
    @Override
    public IModelObject internal_removeChild(final int n) {
        return null;
    }
    
    @Override
    public boolean isDirty() {
        return false;
    }
    
    @Override
    public boolean isType(final String s) {
        return s.equals(this.A);
    }
    
    @Override
    public void removeAncestorListener(final IAncestorListener ancestorListener) {
    }
    
    @Override
    public Object removeAttribute(final String s) {
        if (s.equals(this.A)) {
            return this.B;
        }
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
    
    public void removePathListener(final IPath path, final IPathListener pathListener) {
    }
    
    @Override
    public Object setAttribute(final String s, final Object o) {
        if (s.equals(this.A)) {
            return this.B;
        }
        return null;
    }
    
    @Override
    public Object setAttribute(final String s) {
        if (s.equals(this.A)) {
            return this.B;
        }
        return null;
    }
    
    @Override
    public Object setValue(final Object o) {
        return this.B;
    }
    
    @Override
    public void revertUpdate(final B b) {
        throw new UnsupportedOperationException();
    }
    
    @Override
    public void restoreUpdate(final B b) {
        throw new UnsupportedOperationException();
    }
    
    @Override
    public boolean equals(final Object o) {
        if (o instanceof IModelObject) {
            return ((IModelObject)o).isType(this.A);
        }
        return super.equals(o);
    }
    
    @Override
    public int hashCode() {
        return this.A.hashCode();
    }
    
    @Override
    public String toString() {
        return (this.B == null) ? "" : this.B.toString();
    }
}
