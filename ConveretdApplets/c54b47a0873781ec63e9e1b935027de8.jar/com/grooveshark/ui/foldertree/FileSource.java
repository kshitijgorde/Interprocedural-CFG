// 
// Decompiled by Procyon v0.5.30
// 

package com.grooveshark.ui.foldertree;

import java.io.File;

public interface FileSource
{
    public static final String SYSTEM_ROOT = "ROOTS";
    
    File[] listChildren(final String p0);
    
    int getTotalChildren(final String p0);
}
