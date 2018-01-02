// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby.runtime.encoding;

import org.jcodings.Encoding;

public interface EncodingCapable
{
    Encoding getEncoding();
    
    void setEncoding(final Encoding p0);
}
