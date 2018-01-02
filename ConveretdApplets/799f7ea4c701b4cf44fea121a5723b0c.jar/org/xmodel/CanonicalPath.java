// 
// Decompiled by Procyon v0.5.30
// 

package org.xmodel;

import org.xmodel.xpath.expression.IExpression;

public class CanonicalPath extends D
{
    public CanonicalPath() {
    }
    
    public CanonicalPath(final IPath path) {
        this(path, 0, path.length());
    }
    
    public CanonicalPath(final IPath path, final int n, int length) {
        if (length < 0 || length > path.length()) {
            length = path.length();
        }
        for (int i = n; i < length; ++i) {
            this.addElement(path.getPathElement(i).clone());
        }
    }
    
    public CanonicalPath(final IExpression parent) {
        this.setParent(parent);
    }
    
    @Override
    public String toString() {
        return ModelAlgorithms.pathToString(this);
    }
}
