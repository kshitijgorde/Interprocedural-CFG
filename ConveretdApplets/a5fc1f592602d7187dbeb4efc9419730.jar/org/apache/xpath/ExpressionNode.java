// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xpath;

import javax.xml.transform.SourceLocator;

public interface ExpressionNode extends SourceLocator
{
    void exprSetParent(final ExpressionNode p0);
    
    ExpressionNode exprGetParent();
    
    void exprAddChild(final ExpressionNode p0, final int p1);
    
    ExpressionNode exprGetChild(final int p0);
    
    int exprGetNumChildren();
}
