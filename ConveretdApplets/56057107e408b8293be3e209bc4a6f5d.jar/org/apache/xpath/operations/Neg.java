// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xpath.operations;

import javax.xml.transform.TransformerException;
import org.apache.xpath.objects.XNumber;
import org.apache.xpath.objects.XObject;

public class Neg extends UnaryOperation
{
    public XObject operate(final XObject right) throws TransformerException {
        return new XNumber(-right.num());
    }
}
