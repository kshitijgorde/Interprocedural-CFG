// 
// Decompiled by Procyon v0.5.30
// 

package org.xmodel.diff;

import java.util.Iterator;
import java.util.Collection;
import java.util.HashSet;
import org.xmodel.IChangeSet;
import org.xmodel.xpath.XPath;
import java.util.ArrayList;
import org.xmodel.IModelObject;
import java.util.Set;
import org.xmodel.IPath;
import java.util.List;

public class PathXmlMatcher extends DefaultXmlMatcher
{
    List<IPath> L;
    List<IPath> M;
    Set<IModelObject> O;
    Set<IModelObject> N;
    
    public PathXmlMatcher() {
        this.L = new ArrayList<IPath>();
        this.M = new ArrayList<IPath>();
    }
    
    public void defineList(final String s) {
        this.defineList(XPath.createPath(s));
    }
    
    public void defineList(final IPath path) {
        if (!this.L.contains(path)) {
            this.L.add(path);
        }
    }
    
    public void ignorePath(final String s) {
        this.ignorePath(XPath.createPath(s));
    }
    
    public void ignorePath(final IPath path) {
        if (!this.M.contains(path)) {
            this.M.add(path);
        }
    }
    
    @Override
    public void startDiff(final IModelObject modelObject, final IModelObject modelObject2, final IChangeSet set) {
        this.O = new HashSet<IModelObject>();
        this.N = new HashSet<IModelObject>();
        for (final IPath path : this.L) {
            this.N.addAll(path.query(modelObject, null));
            this.N.addAll(path.query(modelObject2, null));
        }
        for (final IPath path2 : this.M) {
            this.O.addAll(path2.query(modelObject, null));
            this.O.addAll(path2.query(modelObject2, null));
        }
    }
    
    @Override
    public void endDiff(final IModelObject modelObject, final IModelObject modelObject2, final IChangeSet set) {
        this.O = null;
        this.N = null;
    }
    
    @Override
    public boolean isList(final IModelObject modelObject) {
        return this.N.contains(modelObject);
    }
    
    @Override
    public boolean shouldDiff(final IModelObject modelObject, final boolean b) {
        return !this.O.contains(modelObject);
    }
}
