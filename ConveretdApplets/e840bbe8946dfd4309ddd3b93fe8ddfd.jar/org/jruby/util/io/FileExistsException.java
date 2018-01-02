// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby.util.io;

public class FileExistsException extends Throwable
{
    public FileExistsException(final String path) {
        super("file exists: " + path);
    }
}
