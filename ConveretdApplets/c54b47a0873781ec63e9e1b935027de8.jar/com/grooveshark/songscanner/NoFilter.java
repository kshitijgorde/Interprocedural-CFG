// 
// Decompiled by Procyon v0.5.30
// 

package com.grooveshark.songscanner;

import java.io.File;
import java.io.FileFilter;

class NoFilter implements FileFilter
{
    public boolean accept(final File file) {
        return true;
    }
}
