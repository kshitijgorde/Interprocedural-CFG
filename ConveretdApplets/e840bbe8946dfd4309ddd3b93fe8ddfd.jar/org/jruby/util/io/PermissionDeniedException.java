// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby.util.io;

import java.io.FileNotFoundException;

public class PermissionDeniedException extends FileNotFoundException
{
    public PermissionDeniedException() {
    }
    
    public PermissionDeniedException(final String msg) {
        super(msg);
    }
}
