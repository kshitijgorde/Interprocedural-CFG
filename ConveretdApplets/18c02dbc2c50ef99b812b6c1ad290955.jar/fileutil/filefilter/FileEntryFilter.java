// 
// Decompiled by Procyon v0.5.30
// 

package fileutil.filefilter;

import fileutil.SFile;

public class FileEntryFilter implements SFileFilter
{
    public boolean accept(final SFile sFile) {
        return !sFile.isHidden() && (sFile.isFile() || sFile.isDirectory()) && sFile.isDirectory();
    }
}
