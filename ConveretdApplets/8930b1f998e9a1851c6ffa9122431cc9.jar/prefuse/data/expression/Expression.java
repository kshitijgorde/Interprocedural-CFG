// 
// Decompiled by Procyon v0.5.30
// 

package prefuse.data.expression;

import prefuse.data.event.ExpressionListener;
import prefuse.data.Tuple;
import prefuse.data.Schema;

public interface Expression
{
    Class getType(final Schema p0);
    
    void visit(final ExpressionVisitor p0);
    
    Object get(final Tuple p0);
    
    int getInt(final Tuple p0);
    
    long getLong(final Tuple p0);
    
    float getFloat(final Tuple p0);
    
    double getDouble(final Tuple p0);
    
    boolean getBoolean(final Tuple p0);
    
    void addExpressionListener(final ExpressionListener p0);
    
    void removeExpressionListener(final ExpressionListener p0);
}
