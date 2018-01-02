// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xpath.functions;

import java.util.Vector;
import javax.xml.transform.TransformerException;
import org.apache.xpath.objects.XBoolean;
import org.apache.xpath.objects.XObject;
import org.apache.xpath.XPathContext;

public class FuncTrue extends Function
{
    public XObject execute(final XPathContext xctxt) throws TransformerException {
        return XBoolean.S_TRUE;
    }
    
    public void fixupVariables(final Vector vars, final int globalsSize) {
    }
}
