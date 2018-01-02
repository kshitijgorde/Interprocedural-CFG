// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xpath.operations;

import javax.xml.transform.TransformerException;
import org.apache.xpath.objects.XNumber;
import org.apache.xpath.objects.XObject;

public class Mult extends Operation
{
    public XObject operate(final XObject left, final XObject right) throws TransformerException {
        return new XNumber(left.num() * right.num());
    }
}
