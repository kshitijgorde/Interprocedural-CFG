// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xpath.functions;

import javax.xml.transform.TransformerException;
import org.apache.xpath.objects.XNull;
import org.apache.xpath.ExtensionsProvider;
import org.apache.xpath.objects.XObject;
import org.apache.xpath.XPathContext;

public class FuncCoreExtFunction extends FuncExtFunction
{
    static final long serialVersionUID = 8776968851259518578L;
    
    public FuncCoreExtFunction(final String namespace, final String extensionName, final Object methodKey) {
        super(namespace, extensionName, methodKey);
    }
    
    public XObject execute(final XPathContext xctxt) throws TransformerException {
        final ExtensionsProvider provider = (ExtensionsProvider)xctxt.getOwnerObject();
        if (provider == null || !provider.functionAvailable(this.getNamespace(), this.getFunctionName())) {
            this.error(xctxt, "ER_COULDNOT_FIND_FUNCTION", new Object[] { this.getFunctionName() });
            return new XNull();
        }
        return super.execute(xctxt);
    }
}
