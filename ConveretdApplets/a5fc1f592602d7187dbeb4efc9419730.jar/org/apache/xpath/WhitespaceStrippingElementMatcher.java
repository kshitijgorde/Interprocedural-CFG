// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xpath;

import javax.xml.transform.TransformerException;
import org.w3c.dom.Element;

public interface WhitespaceStrippingElementMatcher
{
    boolean shouldStripWhiteSpace(final XPathContext p0, final Element p1) throws TransformerException;
    
    boolean canStripWhiteSpace();
}
