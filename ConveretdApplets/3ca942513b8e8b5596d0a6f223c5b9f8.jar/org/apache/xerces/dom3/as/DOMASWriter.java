// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xerces.dom3.as;

import java.io.OutputStream;
import org.w3c.dom.ls.LSSerializer;

public interface DOMASWriter extends LSSerializer
{
    void writeASModel(final OutputStream p0, final ASModel p1) throws Exception;
}
