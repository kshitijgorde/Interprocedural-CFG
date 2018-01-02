// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xpath;

import org.apache.xpath.objects.XNumber;
import org.apache.xpath.objects.XString;
import org.apache.xpath.patterns.UnionPattern;
import org.apache.xpath.patterns.StepPattern;
import org.apache.xpath.functions.Function;
import org.apache.xpath.operations.Variable;
import org.apache.xpath.operations.UnaryOperation;
import org.apache.xpath.operations.Operation;
import org.apache.xpath.patterns.NodeTest;
import org.apache.xpath.axes.UnionPathIterator;
import org.apache.xpath.axes.LocPathIterator;

public class XPathVisitor
{
    public boolean visitLocationPath(final ExpressionOwner owner, final LocPathIterator path) {
        return true;
    }
    
    public boolean visitUnionPath(final ExpressionOwner owner, final UnionPathIterator path) {
        return true;
    }
    
    public boolean visitStep(final ExpressionOwner owner, final NodeTest step) {
        return true;
    }
    
    public boolean visitPredicate(final ExpressionOwner owner, final Expression pred) {
        return true;
    }
    
    public boolean visitBinaryOperation(final ExpressionOwner owner, final Operation op) {
        return true;
    }
    
    public boolean visitUnaryOperation(final ExpressionOwner owner, final UnaryOperation op) {
        return true;
    }
    
    public boolean visitVariableRef(final ExpressionOwner owner, final Variable var) {
        return true;
    }
    
    public boolean visitFunction(final ExpressionOwner owner, final Function func) {
        return true;
    }
    
    public boolean visitMatchPattern(final ExpressionOwner owner, final StepPattern pattern) {
        return true;
    }
    
    public boolean visitUnionPattern(final ExpressionOwner owner, final UnionPattern pattern) {
        return true;
    }
    
    public boolean visitStringLiteral(final ExpressionOwner owner, final XString str) {
        return true;
    }
    
    public boolean visitNumberLiteral(final ExpressionOwner owner, final XNumber num) {
        return true;
    }
}
