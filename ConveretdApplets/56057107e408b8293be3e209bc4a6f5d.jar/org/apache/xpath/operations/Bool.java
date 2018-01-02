// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xpath.operations;

import javax.xml.transform.TransformerException;
import org.apache.xpath.objects.XBoolean;
import org.apache.xpath.objects.XObject;

public class Bool extends UnaryOperation
{
    public XObject operate(final XObject right) throws TransformerException {
        if (right.getType() == 1) {
            return right;
        }
        return right.bool() ? XBoolean.S_TRUE : XBoolean.S_FALSE;
    }
}
