// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.mina.core.file;

import java.nio.channels.FileChannel;

public interface FileRegion
{
    FileChannel getFileChannel();
    
    long getPosition();
    
    void update(final long p0);
    
    long getRemainingBytes();
}
