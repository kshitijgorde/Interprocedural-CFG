// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xalan.templates;

import org.apache.xpath.operations.Variable;
import org.apache.xpath.functions.FuncExtFunction;
import org.apache.xpath.functions.FuncCurrent;
import org.apache.xpath.functions.Function;
import org.apache.xpath.ExpressionOwner;
import org.apache.xpath.axes.LocPathIterator;
import org.apache.xpath.XPathVisitor;

public class AbsPathChecker extends XPathVisitor
{
    private boolean m_isAbs;
    
    public AbsPathChecker() {
        this.m_isAbs = true;
    }
    
    public boolean checkAbsolute(final LocPathIterator path) {
        this.m_isAbs = true;
        path.callVisitors(null, this);
        return this.m_isAbs;
    }
    
    public boolean visitFunction(final ExpressionOwner owner, final Function func) {
        if (func instanceof FuncCurrent || func instanceof FuncExtFunction) {
            this.m_isAbs = false;
        }
        return true;
    }
    
    public boolean visitVariableRef(final ExpressionOwner owner, final Variable var) {
        this.m_isAbs = false;
        return true;
    }
}
