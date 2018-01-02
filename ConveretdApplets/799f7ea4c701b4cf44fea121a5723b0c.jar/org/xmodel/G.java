// 
// Decompiled by Procyon v0.5.30
// 

package org.xmodel;

import org.xmodel.xpath.expression.IExpressionListener;
import org.xmodel.xpath.expression.IContext;

public interface G
{
    void A(final IPath p0);
    
    boolean A(final IContext p0, final IPath p1, final IModelObject p2);
    
    boolean isAbsolute(final IContext p0);
    
    void A(final IExpressionListener p0);
    
    void B(final IExpressionListener p0);
    
    void bind(final IContext p0);
    
    void unbind(final IContext p0);
    
    Object clone();
}
