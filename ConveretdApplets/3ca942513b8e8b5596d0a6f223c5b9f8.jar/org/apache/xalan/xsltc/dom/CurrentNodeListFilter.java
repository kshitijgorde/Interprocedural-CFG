// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xalan.xsltc.dom;

import org.apache.xml.dtm.DTMAxisIterator;
import org.apache.xalan.xsltc.runtime.AbstractTranslet;

public interface CurrentNodeListFilter
{
    boolean test(final int p0, final int p1, final int p2, final int p3, final AbstractTranslet p4, final DTMAxisIterator p5);
}
