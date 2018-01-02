// 
// Decompiled by Procyon v0.5.30
// 

package prefuse.data.expression;

public interface ExpressionVisitor
{
    void visitExpression(final Expression p0);
    
    void down();
    
    void up();
}
