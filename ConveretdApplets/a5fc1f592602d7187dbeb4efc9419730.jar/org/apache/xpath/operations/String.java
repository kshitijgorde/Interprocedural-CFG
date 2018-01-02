// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xpath.operations;

import javax.xml.transform.TransformerException;
import org.apache.xpath.objects.XString;
import org.apache.xpath.objects.XObject;

public class String extends UnaryOperation
{
    public XObject operate(final XObject right) throws TransformerException {
        return (XString)right.xstr();
    }
}
