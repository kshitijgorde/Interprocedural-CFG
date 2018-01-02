// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.commons.httpclient.methods.multipart;

import java.io.IOException;
import java.io.InputStream;

public interface PartSource
{
    String getFileName();
    
    long getLength();
    
    InputStream createInputStream() throws IOException;
}
