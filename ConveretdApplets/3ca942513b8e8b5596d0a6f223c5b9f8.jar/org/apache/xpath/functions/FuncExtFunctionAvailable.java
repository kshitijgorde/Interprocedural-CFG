// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xpath.functions;

import javax.xml.transform.TransformerException;
import org.apache.xpath.ExtensionsProvider;
import org.apache.xpath.objects.XBoolean;
import org.apache.xpath.objects.XObject;
import org.apache.xpath.XPathContext;
import org.apache.xpath.compiler.FunctionTable;

public class FuncExtFunctionAvailable extends FunctionOneArg
{
    static final long serialVersionUID = 5118814314918592241L;
    private transient FunctionTable m_functionTable;
    
    public FuncExtFunctionAvailable() {
        this.m_functionTable = null;
    }
    
    public XObject execute(final XPathContext xctxt) throws TransformerException {
        final String fullName = super.m_arg0.execute(xctxt).str();
        final int indexOfNSSep = fullName.indexOf(58);
        String namespace;
        String methName;
        if (indexOfNSSep < 0) {
            final String prefix = "";
            namespace = "http://www.w3.org/1999/XSL/Transform";
            methName = fullName;
        }
        else {
            final String prefix = fullName.substring(0, indexOfNSSep);
            namespace = xctxt.getNamespaceContext().getNamespaceForPrefix(prefix);
            if (null == namespace) {
                return XBoolean.S_FALSE;
            }
            methName = fullName.substring(indexOfNSSep + 1);
        }
        if (namespace.equals("http://www.w3.org/1999/XSL/Transform")) {
            try {
                if (null == this.m_functionTable) {
                    this.m_functionTable = new FunctionTable();
                }
                return this.m_functionTable.functionAvailable(methName) ? XBoolean.S_TRUE : XBoolean.S_FALSE;
            }
            catch (Exception e) {
                return XBoolean.S_FALSE;
            }
        }
        final ExtensionsProvider extProvider = (ExtensionsProvider)xctxt.getOwnerObject();
        return extProvider.functionAvailable(namespace, methName) ? XBoolean.S_TRUE : XBoolean.S_FALSE;
    }
    
    public void setFunctionTable(final FunctionTable aTable) {
        this.m_functionTable = aTable;
    }
}
