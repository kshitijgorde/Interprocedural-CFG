// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.crimson.tree;

import java.io.IOException;

public interface XmlWritable
{
    void writeXml(final XmlWriteContext p0) throws IOException;
    
    void writeChildrenXml(final XmlWriteContext p0) throws IOException;
}
