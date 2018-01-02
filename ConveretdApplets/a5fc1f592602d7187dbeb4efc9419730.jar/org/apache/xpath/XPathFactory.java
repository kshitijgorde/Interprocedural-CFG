// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xpath;

import org.apache.xml.utils.PrefixResolver;
import javax.xml.transform.SourceLocator;

public interface XPathFactory
{
    XPath create(final String p0, final SourceLocator p1, final PrefixResolver p2, final int p3);
}
