// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xalan.templates;

import org.apache.xpath.operations.Variable;
import org.apache.xpath.ExpressionOwner;
import org.apache.xml.utils.QName;
import java.util.Vector;
import org.apache.xpath.XPathVisitor;

public class VarNameCollector extends XPathVisitor
{
    Vector m_refs;
    
    public VarNameCollector() {
        this.m_refs = new Vector();
    }
    
    public void reset() {
        this.m_refs.removeAllElements();
    }
    
    public int getVarCount() {
        return this.m_refs.size();
    }
    
    boolean doesOccur(final QName refName) {
        return this.m_refs.contains(refName);
    }
    
    public boolean visitVariableRef(final ExpressionOwner owner, final Variable var) {
        this.m_refs.addElement(var.getQName());
        return true;
    }
}
