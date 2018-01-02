// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xpath.operations;

import javax.xml.transform.TransformerException;
import org.apache.xpath.objects.XBoolean;
import org.apache.xpath.objects.XObject;

public class Lte extends Operation
{
    static final long serialVersionUID = 6945650810527140228L;
    
    public XObject operate(final XObject left, final XObject right) throws TransformerException {
        return left.lessThanOrEqual(right) ? XBoolean.S_TRUE : XBoolean.S_FALSE;
    }
}
