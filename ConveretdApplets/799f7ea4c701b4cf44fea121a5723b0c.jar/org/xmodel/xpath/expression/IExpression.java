// 
// Decompiled by Procyon v0.5.30
// 

package org.xmodel.xpath.expression;

import org.xmodel.IChangeSet;
import org.xmodel.IModelObjectFactory;
import org.xmodel.xpath.variable.IVariableSource;
import org.xmodel.IModelObject;
import java.util.List;

public interface IExpression
{
    String getName();
    
    ResultType getType();
    
    ResultType getType(final IContext p0);
    
    void addArgument(final IExpression p0);
    
    void removeArgument(final IExpression p0);
    
    List<IExpression> getArguments();
    
    IExpression getArgument(final int p0);
    
    void internal_setParent(final IExpression p0);
    
    IExpression getParent();
    
    IExpression getRoot();
    
    boolean isAncestor(final IExpression p0);
    
    boolean isAbsolute(final IContext p0);
    
    void setVariable(final String p0, final String p1);
    
    void setVariable(final String p0, final Number p1);
    
    void setVariable(final String p0, final Boolean p1);
    
    void setVariable(final String p0, final IModelObject p1);
    
    void setVariable(final String p0, final List<IModelObject> p1);
    
    void setVariable(final String p0, final IExpression p1);
    
    IVariableSource getVariableSource();
    
    boolean requiresOrdinalContext();
    
    List<IModelObject> evaluateNodes() throws ExpressionException;
    
    String evaluateString() throws ExpressionException;
    
    double evaluateNumber() throws ExpressionException;
    
    boolean evaluateBoolean() throws ExpressionException;
    
    List<IModelObject> evaluateNodes(final IContext p0) throws ExpressionException;
    
    String evaluateString(final IContext p0) throws ExpressionException;
    
    double evaluateNumber(final IContext p0) throws ExpressionException;
    
    boolean evaluateBoolean(final IContext p0) throws ExpressionException;
    
    List<IModelObject> evaluateNodes(final IContext p0, final List<IModelObject> p1);
    
    String evaluateString(final IContext p0, final String p1);
    
    double evaluateNumber(final IContext p0, final double p1);
    
    boolean evaluateBoolean(final IContext p0, final boolean p1);
    
    void createSubtree(final IContext p0, final IModelObjectFactory p1, final IChangeSet p2);
    
    List<IModelObject> query(final IModelObject p0, final List<IModelObject> p1);
    
    IModelObject queryFirst(final IModelObject p0);
    
    List<IModelObject> query(final List<IModelObject> p0);
    
    IModelObject queryFirst();
    
    List<IModelObject> query(final IContext p0, final List<IModelObject> p1);
    
    IModelObject queryFirst(final IContext p0);
    
    void addListener(final IContext p0, final IExpressionListener p1);
    
    void removeListener(final IContext p0, final IExpressionListener p1);
    
    void addNotifyListener(final IContext p0, final IExpressionListener p1);
    
    void removeNotifyListener(final IContext p0, final IExpressionListener p1);
    
    ExpressionListenerList getListeners();
    
    void bind(final IContext p0);
    
    void unbind(final IContext p0);
    
    void notifyAdd(final IExpression p0, final IContext p1, final List<IModelObject> p2);
    
    void notifyRemove(final IExpression p0, final IContext p1, final List<IModelObject> p2);
    
    void notifyChange(final IExpression p0, final IContext p1, final String p2, final String p3);
    
    void notifyChange(final IExpression p0, final IContext p1, final double p2, final double p3);
    
    void notifyChange(final IExpression p0, final IContext p1, final boolean p2);
    
    void notifyChange(final IExpression p0, final IContext p1);
    
    void notifyValue(final IExpression p0, final IContext[] p1, final IModelObject p2, final Object p3, final Object p4);
    
    void handleException(final IExpression p0, final IContext p1, final Exception p2);
    
    boolean requiresValueNotification(final IExpression p0);
    
    Object clone();
    
    public enum ResultType
    {
        NODES("NODES", 0), 
        STRING("STRING", 1), 
        NUMBER("NUMBER", 2), 
        BOOLEAN("BOOLEAN", 3), 
        UNDEFINED("UNDEFINED", 4);
        
        static {
            A = new ResultType[] { ResultType.NODES, ResultType.STRING, ResultType.NUMBER, ResultType.BOOLEAN, ResultType.UNDEFINED };
        }
        
        private ResultType(final String s, final int n) {
        }
    }
}
