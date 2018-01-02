// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xalan.templates;

import org.apache.xpath.XPathVisitor;

public class XSLTVisitor extends XPathVisitor
{
    public boolean visitInstruction(final ElemTemplateElement elem) {
        return true;
    }
    
    public boolean visitStylesheet(final ElemTemplateElement elem) {
        return true;
    }
    
    public boolean visitTopLevelInstruction(final ElemTemplateElement elem) {
        return true;
    }
    
    public boolean visitTopLevelVariableOrParamDecl(final ElemTemplateElement elem) {
        return true;
    }
    
    public boolean visitVariableOrParamDecl(final ElemVariable elem) {
        return true;
    }
    
    public boolean visitLiteralResultElement(final ElemLiteralResult elem) {
        return true;
    }
    
    public boolean visitAVT(final AVT elem) {
        return true;
    }
    
    public boolean visitExtensionElement(final ElemExtensionCall elem) {
        return true;
    }
}
