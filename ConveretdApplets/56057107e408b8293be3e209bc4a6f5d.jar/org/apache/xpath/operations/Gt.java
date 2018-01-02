// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xpath.operations;

import javax.xml.transform.TransformerException;
import org.apache.xpath.objects.XBoolean;
import org.apache.xpath.objects.XObject;

public class Gt extends Operation
{
    public XObject operate(final XObject left, final XObject right) throws TransformerException {
        return left.greaterThan(right) ? XBoolean.S_TRUE : XBoolean.S_FALSE;
    }
}
