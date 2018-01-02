// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xalan.extensions;

import org.apache.xpath.functions.FuncExtFunctionAvailable;
import org.apache.xpath.functions.FuncExtFunction;
import org.apache.xpath.functions.Function;
import org.apache.xpath.ExpressionOwner;
import org.apache.xalan.templates.StylesheetRoot;
import org.apache.xpath.XPathVisitor;

public class ExpressionVisitor extends XPathVisitor
{
    private StylesheetRoot m_sroot;
    
    public ExpressionVisitor(final StylesheetRoot sroot) {
        this.m_sroot = sroot;
    }
    
    public boolean visitFunction(final ExpressionOwner owner, final Function func) {
        if (func instanceof FuncExtFunction) {
            final String namespace = ((FuncExtFunction)func).getNamespace();
            this.m_sroot.getExtensionNamespacesManager().registerExtension(namespace);
        }
        else if (func instanceof FuncExtFunctionAvailable) {
            final String arg = ((FuncExtFunctionAvailable)func).getArg0().toString();
            if (arg.indexOf(":") > 0) {
                final String prefix = arg.substring(0, arg.indexOf(":"));
                final String namespace2 = this.m_sroot.getNamespaceForPrefix(prefix);
                this.m_sroot.getExtensionNamespacesManager().registerExtension(namespace2);
            }
        }
        return true;
    }
}
