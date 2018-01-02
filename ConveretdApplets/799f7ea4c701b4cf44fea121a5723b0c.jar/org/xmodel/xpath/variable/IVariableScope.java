// 
// Decompiled by Procyon v0.5.30
// 

package org.xmodel.xpath.variable;

import org.xmodel.A.B;
import org.xmodel.xpath.expression.ExpressionException;
import org.xmodel.xpath.expression.IContext;
import org.xmodel.xpath.expression.IExpression;
import org.xmodel.IModelObject;
import java.util.List;
import java.util.Collection;

public interface IVariableScope
{
    void internal_setSource(final IVariableSource p0);
    
    IVariableSource getSource();
    
    int getPrecedence();
    
    String getName();
    
    Collection<String> getVariables();
    
    Object set(final String p0, final Object p1);
    
    void insert(final String p0, final Object p1);
    
    void insert(final String p0, final Object p1, final int p2);
    
    void remove(final String p0, final Object p1);
    
    void remove(final String p0, final int p1);
    
    String set(final String p0, final String p1);
    
    Number set(final String p0, final Number p1);
    
    Boolean set(final String p0, final Boolean p1);
    
    List<IModelObject> set(final String p0, final List<IModelObject> p1);
    
    List<IModelObject> set(final String p0, final IModelObject p1);
    
    void define(final String p0, final IExpression p1);
    
    Object get(final String p0);
    
    Object get(final String p0, final IContext p1) throws ExpressionException;
    
    void clear(final String p0);
    
    Collection<String> getAll();
    
    void copyFrom(final IVariableScope p0);
    
    boolean isDefined(final String p0);
    
    boolean isBound(final String p0);
    
    IExpression.ResultType getType(final String p0);
    
    IExpression.ResultType getType(final String p0, final IContext p1);
    
    void addListener(final String p0, final IContext p1, final IVariableListener p2);
    
    void removeListener(final String p0, final IContext p1, final IVariableListener p2);
    
    void revert(final B p0);
    
    void restore(final B p0);
    
    IVariableScope cloneOne();
}
