// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.mina.core.file;

import java.nio.channels.FileChannel;
import java.io.File;

public final class FilenameFileRegion extends DefaultFileRegion
{
    public FilenameFileRegion(final File file, final FileChannel fileChannel, final long n, final long n2) {
        super(fileChannel, 0L, n2);
        if (file == null) {
            throw new IllegalArgumentException("file can not be null");
        }
    }
}
